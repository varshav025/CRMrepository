package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class javaUtility {
	
	public int generateRandomNumber() {
		Random random= new Random();
		int r= random.nextInt(5000);
		return r;
	}
	
	public String getSystemDateYYYYMMDD() {
		Date dateObj= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
		String currDate = simple.format(dateObj);
		return currDate;
	}
	
	public String getRequiredDateYYYYMMDD(int days) {
		Date dateObj= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
		simple.format(dateObj);
		Calendar cal = simple.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = simple.format(cal.getTime());
		return reqDate;
	}
	
	public String getSysDateForScreenshot() {
		String dateAndTime = new Date().toString().replace(" ", "_").replace(":", "_");
		return dateAndTime;
	}

}
