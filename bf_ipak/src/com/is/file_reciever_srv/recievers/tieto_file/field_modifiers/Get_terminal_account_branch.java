package com.is.file_reciever_srv.recievers.tieto_file.field_modifiers;

import java.util.HashMap;

import com.is.ti_terminal_accounts.Terminal_accounts_service;

public class Get_terminal_account_branch extends Abstract_modifier
{
	@Override
	public Object modify(HashMap<String, String> params,
			String result_field_name, String parameter_field_name) throws Exception
	{
		String in_param = params.get(parameter_field_name);
		in_param=in_param.trim();
		return Terminal_accounts_service.get_terminal_account(in_param).getBranch();
		//Terminal_account ta;
        //ta=Terminal_accounts_service.get_terminal_account(in_param);
		//return ta.getBranch();
	}
}
