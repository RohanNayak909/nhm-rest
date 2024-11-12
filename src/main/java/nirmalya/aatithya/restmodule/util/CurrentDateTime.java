package nirmalya.aatithya.restmodule.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDateTime {
	
	static LocalDateTime now = LocalDateTime.now();  
	
	public static String getCurrentDate() {
		String curDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
		curDate = dtf.format(now);
		return curDate;
	}
	
	public static String getCurrentTime() {
		String curTime = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		curTime = dtf.format(now);
		return curTime;
	}
	
	public static String getCurrentDateTime() {
		String curDateTime = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		curDateTime = dtf.format(now);
		return curDateTime;
	}
}
