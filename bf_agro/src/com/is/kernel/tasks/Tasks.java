package com.is.kernel.tasks;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.kernel.task_handler.Abstract_task_handler;


public class Tasks
{
	private HashMap<Task_pk, Task> tasks = null;
	
	public Tasks(Connection c) throws SQLException
	{
		tasks = Service.get_tasks(c);
	}
	
	public Task get(Task_pk task_pk) throws TaskNotFoundException
	{
		Task res = tasks.get(task_pk);
		if (res == null) throw new TaskNotFoundException(task_pk.toString());
		return res;
	}
	
	public Abstract_task_handler get_task_handler(Task_pk task_pk) throws TaskNotFoundException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Task res_task = tasks.get(task_pk);
		if (res_task == null) throw new TaskNotFoundException(task_pk.toString());
		Abstract_task_handler handler = (Abstract_task_handler)Class.forName(
				res_task.getHandler_class_name()).newInstance();
		return handler;
	}
}
