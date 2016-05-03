package in.dreamlab.iisc.trans;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by prateeksha on 25/3/16.
 */
public class CCAnalyticsWebCrawlerPartition extends Partitioner{
    @Override
    public int getPartition(Object arg0, Object arg1, int arg2) {
        // TODO Auto-generated method stub


        String word = arg0.toString();
       // word=word.substring(0,word.indexOf(':'));

//		System.out.println("TESTP word: "+word);
        if(word != null && !word.isEmpty()){
            char letter = word.toLowerCase().charAt(0);

            int partitionNumber = 0;

            switch(letter) {
                case 'a': partitionNumber = 1;  break;
                case 'b': partitionNumber = 2;  break;
                case 'c': partitionNumber = 3;  break;
                case 'd': partitionNumber = 4;  break;
                case 'e': partitionNumber = 5;  break;
                case 'f': partitionNumber = 6;  break;
                case 'g': partitionNumber = 7;  break;
                case 'h': partitionNumber = 8;  break;
                case 'i': partitionNumber = 9;  break;
                case 'j': partitionNumber = 10;  break;
                case 'k': partitionNumber = 11;  break;
                case 'l': partitionNumber = 12;  break;
                case 'm': partitionNumber = 13;  break;
                case 'n': partitionNumber = 14;  break;
                case 'o': partitionNumber = 15;  break;
                case 'p': partitionNumber = 16;  break;
                case 'q': partitionNumber = 17;  break;
                case 'r': partitionNumber = 18;  break;
                case 's': partitionNumber = 19;  break;
                case 't': partitionNumber = 20;  break;
                case 'u': partitionNumber = 21;  break;
                case 'v': partitionNumber = 22;  break;
                case 'w': partitionNumber = 23;  break;
                case 'x': partitionNumber = 24;  break;
                case 'y': partitionNumber = 25;  break;
                case 'z': partitionNumber = 26;  break;
                default: partitionNumber = 0;  break;
            }
            return partitionNumber;
        }
        return 0;

    }

}

