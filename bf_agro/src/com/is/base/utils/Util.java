package com.is.base.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.zkoss.zk.ui.Executions;

import com.is.clients.models.Action;
import com.is.utils.RefData;

public class Util {
	public static boolean inInts(int num,int... numbers) {
		for(int i: numbers){
			if(num == i) {
				return true;
			}
		}
		return false;
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
}
