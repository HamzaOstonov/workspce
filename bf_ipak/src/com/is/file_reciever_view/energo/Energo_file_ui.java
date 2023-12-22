package com.is.file_reciever_view.energo;

import com.is.file_reciever.ui_classes.Service;
import com.is.file_reciever_view.simple.Simple_ui_class;

public class Energo_file_ui extends Simple_ui_class
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
		return "energo_ui.zul";
	}

}
