package com.is.file_reciever.task_handlers;

import java.sql.Connection;

import com.is.file_reciever.energo.Ext_in_file_recordsService;
import com.is.kernel.parameter.ParameterNotFoundException;
import com.is.kernel.parameter.Parameters;


public class Transaction_template_create_handler extends com.is.kernel.task_handler.Abstract_task_handler
{
	public Parameters execute(Parameters params) throws Exception
	{
		Connection c = (Connection)params.get("current_connection");
		Long file_id = (Long)params.get("ext_in_file_id");
		Ext_in_file_recordsService.documents_action(file_id, 1l, c);
		return null;
	}
}
