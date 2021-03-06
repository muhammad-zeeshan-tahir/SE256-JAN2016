library(fpp)
filepath <- "~/Dropbox/SERC/SEMESTER 2/SSDS/Project/Forecast/R_Dataset/"
temp <- scan(file = paste(filepath,"Temperature.txt", sep = ""))
n <- length(temp)
t <- ts(temp[1:ceiling(n/2)], start=0, deltat = 1)
end1 <- ceiling(n/4)-0.1
start1 <- ceiling(n/4)
train <- window(t, end=end1)
test <- window(t, start=start1)
fit <- auto.arima(train)
refit <- Arima(t, model=fit)
fc <- window(fitted(refit), start=start1)
accuracy(fc, test)

col1 <- matrix(test, nrow = length(test), ncol = 1)
col2 <- matrix(fc, nrow = length(fc), ncol = 1)
diff <- abs(fc-test)
col3 <- matrix(diff, nrow = length(diff), ncol = 1)
final_res <- cbind(col1, col2, diff)
write(final_res, paste(filepath,"TemperatureForecast.txt", sep = ""))
write.csv(final_res, paste(filepath,"TemperatureForecast.csv", sep = ""))