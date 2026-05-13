package com.is.tieto_visa.tieto;

public class WebServiceUtils {
	public static String getUniqueConstant(){
		String result;
		String addNumber;
		String nowTime;
		
		addNumber = String.valueOf((int) (Math.random() * 101));
		nowTime = String.valueOf(System.currentTimeMillis());
		result = nowTime + addNumber;
		
		return result;
	}
}
