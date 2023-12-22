package com.is.file_reciever_srv.recievers.tieto_file.field_modifiers;

import java.math.BigDecimal;
import java.util.HashMap;

public class Multiplicator100 extends Abstract_modifier
{
	@Override
	
	
	
	
	public Object modify(HashMap<String, String> params, String result_field_name,
			String parameter_field_name)
	{
		String in_param = params.get(parameter_field_name);
		try
		{   
			in_param=in_param.trim();
			BigDecimal amount = new BigDecimal(in_param);
			
			long res = amount.multiply(BigDecimal.valueOf(100l)).longValue();
			
			return res;
		}
		catch(RuntimeException e)
		{
			throw new RuntimeException("input string:"+in_param+ e.getMessage());
		}
	}
}
