package com.is.file_reciever_srv.file_checks;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.is.file_reciever_srv.exception.Reciever_exception;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


import sun.nio.cs.StandardCharsets;

public class string_checks
{
	public static long get_control_sum(String input_string)
	{
		long res = 0;
		
/*		try
		{*/
			byte[] b = input_string.getBytes();
		//	b = input_string.getBytes();//("CP855");
		//	b = input_string.getBytes("ASCII");
			
			for (int i = 0; i < b.length; i++)
			{
				res +=  ( b[i]<0 ? b[i]+256 : b[i]) *(i+1);
			}
			
/*		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}*/
		
		return res;
	}
	
	public static Parsed_string parse_input_sring(String str) throws Reciever_exception
	{
		Parsed_string res = null;
		
		Pattern p_conrol_summ = Pattern.compile("(.*)"+Character.toString((char) 29)+
				"(.*?)"+Character.toString((char) 29)+"$");
		Matcher p_conrol_summ_matcher = p_conrol_summ.matcher(str);
		
		if (!p_conrol_summ_matcher.find())
		{
			throw new com.is.file_reciever_srv.exception.Reciever_exception("Could not parse string:"+str);
		}
		
		String data = p_conrol_summ_matcher.group(1)+Character.toString((char) 29);
		long summ = 0;
		try{summ  = Long.parseLong(p_conrol_summ_matcher.group(2));}
		catch (NumberFormatException e){throw new com.is.file_reciever_srv.exception.Reciever_exception("Wrong control summ:"+
				p_conrol_summ_matcher.group(2));}
		res = new Parsed_string(data, summ);
		return res;
	}
	
	public static boolean check_string_sum(Parsed_string pas) throws Reciever_exception
	{
		boolean res = false;
		
		long calc_summ = get_control_sum(pas.getData());
		
		return (pas.getControl_summ() == calc_summ);
	}
}
