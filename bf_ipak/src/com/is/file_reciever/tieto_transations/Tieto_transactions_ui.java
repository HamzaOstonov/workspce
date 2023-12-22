package com.is.file_reciever.tieto_transations;

import com.is.file_reciever.simple.Simple_ui_class;
import com.is.file_reciever.ui_classes.Service;

public class Tieto_transactions_ui extends Simple_ui_class
{

	@Override
	public String get_file_state(Long fr_file_id)
	{
		return Service.get_file_State(fr_file_id);
		//return "Загружен";
	}

	@Override
	public String get_file_user_inerface()
	{
		return "tieto_transactions.zul";
	}

}
