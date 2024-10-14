package com.is.accounts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import org.zkoss.zk.ui.Executions;

import com.is.accounts.Action;
import com.is.clientcrm.utils.FieldHandler;
import com.is.utils.RefData;

public class Utility {
	
	private static final SimpleDateFormat df = new SimpleDateFormat("(dd.MM.yyyy");
	private static final SimpleDateFormat dfWithTime = new SimpleDateFormat("dd-MM-yyyy@HH-mm-ss");
	
	public static boolean inInts(int num,int... numbers) {
		for(int i: numbers){
			if(num == i) {
				return true;
			}
		}
		return false;
	}
	
	public static void interateThroughFields(Class classType, Object local, FieldHandler fieldHandler) {
		for(Field f: classType.getDeclaredFields()) {
			if(Modifier.isStatic(f.getModifiers()) 
					|| Modifier.isFinal(f.getModifiers())) {
				continue;
			}
			try {
				f.setAccessible(true);
				fieldHandler.eval(f, local);
			} catch (IllegalArgumentException e) {
                ISLogger.getLogger().error(CheckNull.getPstr(e));
			} catch (IllegalAccessException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} 
		}
	}
	
	
	public static boolean inChars(char charItem,char... chars) {
		for(int i: chars){
			if(charItem == i) {
				return true;
			}
		}
		return false;
	}
	public static boolean inStrings(String str,String... strings) {
		for(String i: strings){
			if(str != null&& str.equals(i)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNew() {
		HttpSession session = (HttpSession) Executions.getCurrent() .getSession().getNativeSession();
		if (session.getAttribute("isLoggedIn") == null) {
			// Executions.sendRedirect("/");
			return true;
		} else
			return false;	
	}	
        	
	public static boolean hasChanges(Object obj1, Object obj2) {
		return (obj1 != null && obj2 != null && !obj1.equals(obj2)) 
			|| (obj1 != null && obj2 == null) 
			|| (obj1 == null && obj2 != null);
	}
	
	public static String getDesctiption(String str, List<RefData> list) {
		for(RefData rd : list) {
			if(str.equals(rd.getData())) {
				return rd.getLabel();
			}
		}
		return "";
	}
	
	public static boolean isLetter(String str) {
		return str.matches("\\w+");
	}
	public static boolean isDigit(String str) {
		return str.matches("\\d+");
	}
	public static String defaultStringValue(String str) {
		return str != null ? str : "";
	}
	public static java.sql.Date sqlDate(java.util.Date date) {
		return date != null ? new java.sql.Date(date.getTime()) : null;
	}
	
	public static char[] addElementToArray(char[] org, char added) {
		char[] result = Arrays.copyOf(org, org.length +1);
	    result[org.length] = added;
	    return result;
	}
	public static HashMap<String,String> listToMap(List<RefData> list) {
		HashMap<String,String> map = new HashMap<String, String>();
		for(RefData rd: list) {
			map.put(rd.getData(), rd.getLabel());
		}
		return map;
	}
	public static HashMap<Integer,String> listToMapInt(List<RefData> list) {
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		for(RefData rd: list) {
			map.put(Integer.parseInt(rd.getData()), rd.getLabel());
		}
		return map;
	}
	
	
	public static HashMap<Integer,String> actionListToMapInt(int dealId,List<Action> list) {
		HashMap<Integer,String> map = new HashMap<Integer, String>();
		for(Action rd: list) {
			if(rd.getDealId() == dealId){
				map.put(rd.getId(), rd.getName());
			}
		}
		return map;
	}
	
	public static String formatDate(Date date) {
		return df.format(date);
	}
	public static Date parseStringToDate(String date) throws ParseException {
		return df.parse(date);
	}
	public static String formatDateWithTime(Date date) {
		return df.format(date);
	}
	
}
