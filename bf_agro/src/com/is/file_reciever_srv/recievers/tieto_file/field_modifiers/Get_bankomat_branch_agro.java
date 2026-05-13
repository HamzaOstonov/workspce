package com.is.file_reciever_srv.recievers.tieto_file.field_modifiers;

import java.util.HashMap;

public class Get_bankomat_branch_agro extends Abstract_modifier {
	@Override
	public Object modify(HashMap<String, String> params,
			String result_field_name, String parameter_field_name) throws Exception
	{
		String in_param = params.get(parameter_field_name);
		in_param=in_param.trim();
		if (in_param.length()>=6)
		in_param=in_param.substring(1,6);
		return in_param;
		//Terminal_account ta;
        //ta=Terminal_accounts_service.get_terminal_account(in_param);
		//return ta.getBranch();
	}

}