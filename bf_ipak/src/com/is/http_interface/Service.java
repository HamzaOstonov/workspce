package com.is.http_interface;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.is.kernel.Kernel;
import com.is.kernel.KernelException;
import com.is.kernel.action_service.Action_service;
import com.is.kernel.actions.Action;
import com.is.kernel.actions.Action_pk;
import com.is.kernel.parameter.Parameters;
import com.is.kernel.tasks.TaskNotFoundException;
import com.is.kernel.tasks.Task_pk;
import com.is.utils.CheckNull;


public class Service
{
	public static String execute_action(HttpServletRequest request, Kernel kernel, Connection c)
	{
		StringBuffer res = new StringBuffer();
		Long action_id = Long.parseLong(request.getParameter("id"));
		Long deal_group_id = Long.parseLong(request.getParameter("deal_group_id"));
		Long deal_id = Long.parseLong(request.getParameter("deal_id"));
		String request_id = request.getParameter("request_id");
		//String task_id_str = request.getParameter("task_id");
		try
		{
			Parameters parameters = 
			//kernel.getTasks().get(new Task_pk(deal_group_id, task_id)).execute(parse_parameters(request));
			Action_service.doaction(
					kernel.getActions().get(
							new Action_pk(deal_group_id, deal_id, action_id)),
							parse_parameters(request), 
							kernel, 
							c);
			HashMap<String, Object> out_params_hash_map = parameters.getParametersHashmap();
			Iterator it = out_params_hash_map.entrySet().iterator();
			res.append("http_interface_start_result<del>");
			res.append("http_interface_request_id="+request_id+"<del>");
			res.append("http_interface_error_code=0<del>");
			res.append("http_interface_error_class=<del>");
			res.append("http_interface_error_message=<del>");
			while (it.hasNext())
		    {
				Map.Entry pair = (Map.Entry)it.next();
				res.append(pair.getKey()+"="+pair.getValue()+"<del>");
		    }
			res.append("http_interface_end_result<del>");
		}
		catch(Exception e)
		{
			res.append("http_interface_start_result<del>");
			res.append("http_interface_request_id="+request_id+"<del>");
			res.append("http_interface_error_code=-1<del>");
			res.append("http_interface_error_class="+e.getClass().getName()+"<del>");
			res.append("http_interface_error_message="+ CheckNull.getPstr(e)+"<del>");
			res.append("http_interface_end_result<del>");
		}
		return res.toString();
	}
	
	private static Parameters parse_parameters(HttpServletRequest request)
	{
		Parameters parameters = new Parameters();
		
		Map input_params = request.getParameterMap();
		
		Iterator it = input_params.entrySet().iterator();
	    while (it.hasNext())
	    {
	        Map.Entry pair = (Map.Entry)it.next();
	        parameters.put((String)(pair.getKey()), pair.getValue());
	    }
		return parameters;
	}
}
