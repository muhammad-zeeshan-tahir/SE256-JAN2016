import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
	
public class RsvpSpout implements IRichSpout
{
   static JSONParser jsonParser = new JSONParser();
   
   private String filePath ;
   
   private FileReader fileReader;
   private BufferedReader bufferedReader;
	 private SpoutOutputCollector collector;
   private TopologyContext context;

   private Integer idx = 0;

   public RsvpSpout(String filePath) 
   {
 		super();
 		this.filePath = filePath;
 	}
   
   public void open(Map conf, TopologyContext context, SpoutOutputCollector collector)
   {
      this.context = context;
      
      this.collector = collector;
	   try
	   {
		   fileReader = new FileReader(filePath);
		   bufferedReader = new BufferedReader(fileReader);
	   }
	   catch (IOException e)
	   {
    	   System.out.println("Could not open file: " + e.getMessage());
       }
   }

   public void nextTuple() {
	   try
	   {
            String rsvp;

               while((rsvp = bufferedReader.readLine())!=null /* && idx < 100 */)
               {
                   try
                   {
                	   JSONObject jsonObject  = (JSONObject) jsonParser.parse(rsvp);
                	   // Long rsvp_id = (Long) jsonObject.get("rsvp_id");
                	   String rsvpResponse = (String) jsonObject.get("response");
                	   
                	   if(rsvpResponse.equals("yes"))
                	   {
                		   collector.emit(new Values(1));
                	   }
                	   else
                	   {
                		   collector.emit(new Values(0));
                	   }
                       idx++;
                   }
                   catch (Exception e) 
                   {
                       System.out.println("Error parsing message from meetup: " + e.getMessage());
                   }
               }
       }
	   catch (IOException e)
	   {
    	   System.out.println("Could not open file: " + e.getMessage());
       }
      }


   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declare(new Fields("rsvpResponse"));
   }

   public void close()
   {
       try
       {
    	   bufferedReader.close();
       }
       catch (IOException e)
       {
    	   System.out.println("Could not open file: " + e.getMessage());
       }
   }

   public boolean isDistributed() {
      return false;
   }

   
   public void activate() {}

    
   public void deactivate() {}

   
   public void ack(Object msgId) {}

   
   public void fail(Object msgId) {}

   
   public Map<String, Object> getComponentConfiguration() {
      return null;
   }
}
