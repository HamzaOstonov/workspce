package com.is.file_reciever.ui_classes;

import com.is.file_reciever.simple.Simple_ui_class;

public class Ext_file_ui extends Simple_ui_class
{
	public String get_file_state(Long fr_file_id)
	{
		return Service.get_file_State(fr_file_id);
	}

	@Override
	public String get_file_user_inerface()
	{
		return "CB_approval_file_details.zul";
	}
}
