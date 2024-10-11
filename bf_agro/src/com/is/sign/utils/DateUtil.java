package com.is.sign.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date addDays(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, count);
	    return cal.getTime();
	}
    
    public static int getWeekDay(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.DAY_OF_WEEK);
	}
    
    public static int getDay(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.DAY_OF_MONTH);
	}
	
    public static Date setDay(Date date, int day) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, day);
	    return  cal.getTime();
	}
	
    public static Date addMonths(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MONTH, count);
	    return cal.getTime();
	}
    
    public static int getMonth(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.MONTH);
	}
    
    public static Date setMonth(Date date, int month) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, month);
	    return  cal.getTime();
	}
	
    public static Date addYears(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.YEAR, count);
	    return cal.getTime();
	}

    public static int getYear(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.YEAR);
	}
    
    public static Date setYear(Date date, int year) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.YEAR, year);
	    return  cal.getTime();
	}
    
    public static Date addHours(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.HOUR, count);
	    return cal.getTime();
	}

    public static int getHour(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.HOUR_OF_DAY);
	}
    
    public static Date setHour(Date date, int hour) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, hour);
	    return  cal.getTime();
	}
    
    public static Date addMinutes(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MINUTE, count);
	    return cal.getTime();
	}

    public static int getMinute(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.MINUTE);
	}
    
    public static Date setMinute(Date date, int minute) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MINUTE, minute);
	    return  cal.getTime();
	}
}
