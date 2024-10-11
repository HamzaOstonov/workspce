package com.is.file_reciever_srv.recievers.tieto_file.field_modifiers;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.is.ISLogger;

public class TrimFloat extends Abstract_modifier
{
	private static Logger logger = ISLogger.getLogger();
	
	@Override
	public Object modify(HashMap<String, String> params, String result_field_name,
			String parameter_field_name)
	{
		System.out.println("parameter_field_name="+parameter_field_name+", params.get(parameter_field_name)="+params.get(parameter_field_name));
		logger.error("parameter_field_name="+parameter_field_name+", params.get(parameter_field_name)="+params.get(parameter_field_name));		
		String in_param = params.get(parameter_field_name);
		try
		{   
			in_param=in_param.trim();
			//if (in_param.equals("") || in_param=="")
			//	in_param="0";
			BigDecimal amount = new BigDecimal(in_param);
			long res = amount.multiply(BigDecimal.valueOf(1l)).longValue();
			return res;
		}
		catch(RuntimeException e)
		{
			logger.error("input string:"+in_param+", "+ e.getMessage()+ ", "+e.getCause());
			throw new RuntimeException("input string:"+in_param+", "+ e.getMessage()+ ", "+e.getCause());
		}
	}
}
