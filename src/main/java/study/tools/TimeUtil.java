package study.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss"; 
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	
	/**
	 * 把时间按照既定格式输出字符串
	 * 
	 * @param t
	 * @param format
	 * @return
	 */
	public static String getStringDateByFormat(Date t, String format) {
		if (null == t) {
			return "";
		}
		Timestamp time = new Timestamp(t.getTime());
		return getStringTimeByFormat(time, format);
	}
	
	/**
	 * 把时间按照既定格式输出字符串
	 * 
	 * @param t
	 * @param format
	 * @return
	 */
	public static String getStringTimeByFormat(Timestamp t, String format) {
		if (null == t) {
			return "";
		}
		String rt = "";
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			rt = sdf.format(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	/**
	 * 给时间加上hour小时
	 * @param t
	 * @param hour
	 * @return
	 */
	public static Date addHour(Date t, int hour) {
		if (t != null) {
			Calendar cal = Calendar.getInstance();   
	        cal.setTime(t);   
	        cal.add(Calendar.HOUR, hour);// 24小时制   
	        t = cal.getTime();
		}
        return t;
	}
	
	/**
	 * 给时间加上minute分钟
	 * @param t
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date t, int minute) {
		if (t != null) {
			Calendar cal = Calendar.getInstance();   
	        cal.setTime(t);   
	        cal.add(Calendar.MINUTE, minute);
	        t = cal.getTime();
		}
        return t;
	}
}
