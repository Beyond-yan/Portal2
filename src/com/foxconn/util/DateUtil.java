package com.foxconn.util;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 
	 * @param date1  
	 * @param date2  
	 * @return
	 */
	public static int getMinites(Date date1,Date date2) { 
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		int hour = cal1.get(Calendar.HOUR_OF_DAY) - cal2.get(Calendar.HOUR_OF_DAY);
		int minite = cal1.get(Calendar.MINUTE) - cal2.get(Calendar.MINUTE);
		
		return hour * 60 + minite;
	}
	
	/**
	 * 
	 * @param date 
	 * @return 
	 */
	public static String getFormatDateString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */
	public static String getFormatDateTimeString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	/**
	 * 
	 * @param date 
	 * @return
	 */
	public static String getDateString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("MM-dd HH:mm").format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String toTimeString(Date date) {
		if(date == null) return "";
		return new SimpleDateFormat("HH:mm").format(date);
	}
	
	/**
	 * 将字符串转指定格式的日期类型
	 * @param date
	 * @return
	 */
	public static Date getDate(String str, String format) {
		try {
			return new SimpleDateFormat(format).parse(str);
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */
	public static Date getDateFromTime(Time time){
		Calendar c = Calendar.getInstance(); 
		try {
			c.setTime(new SimpleDateFormat("HH:mm:ss").parse(time.toString()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		Calendar cal = Calendar.getInstance();
		c.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		return c.getTime();
	}
	
	/**
	 * 比较两个日期的大小
	 * @param date1
	 * @param date2
	 * @return 如果date1大于date2返回1，
	 *             date1小于date2返回-1，
	 *             date1等于date2返回0
	 */
	public static int compareTwoDate(Date date1,Date date2){
		long minusDate1 = date1.getTime();
		long minusDate2 = date2.getTime();
		int flag = minusDate1 > minusDate2 ? 1:-1;
		if(minusDate1==minusDate2){
			flag = 0;
		} 
		return flag;
		
	}
	
	public static String getVersionDate(){
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
}
