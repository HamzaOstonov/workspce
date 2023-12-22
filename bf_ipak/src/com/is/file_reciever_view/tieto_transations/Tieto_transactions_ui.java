package com.is.file_reciever_view.tieto_transations;

import com.is.file_reciever.ui_classes.Service;
import com.is.file_reciever_view.simple.Simple_ui_class;

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
