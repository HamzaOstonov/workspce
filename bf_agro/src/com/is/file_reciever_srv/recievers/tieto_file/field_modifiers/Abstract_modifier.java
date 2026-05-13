package com.is.file_reciever_srv.recievers.tieto_file.field_modifiers;

import java.util.HashMap;

import com.is.kernel.parameter.Parameters;


public abstract class Abstract_modifier
{
	abstract public Object modify(HashMap<String, String> string_params, String result_field_name, 
			String parameter_field_name) throws Exception;
}
