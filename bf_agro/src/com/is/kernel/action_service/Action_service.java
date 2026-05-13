package com.is.kernel.action_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import com.is.kernel.Kernel;
import com.is.kernel.KernelException;
import com.is.kernel.actions.Action;
import com.is.kernel.parameter.Parameters;
import com.is.kernel.reactions.Reaction;
import com.is.kernel.task_handler.Abstract_task_handler;
import com.is.kernel.tasks.Task_pk;


public class Action_service
{
	public static Parameters doaction(Action action, Parameters parameters, Kernel kernel, Connection c) throws Exception
	{
		Parameters result_parameters = null;
		
		HashMap<Long, Reaction> reactions = kernel.getReactions().get(action);
		
		SortedSet<Long> keys = new TreeSet<Long>(reactions.keySet());
		for (Long key : keys)
		{
			Reaction curren_reaction = reactions.get(key);
			Abstract_task_handler current_task_handler = kernel.getTasks().get_task_handler(
					new Task_pk(
							curren_reaction.getTask_group_id(), 
							curren_reaction.getTask_id())
					);
			result_parameters = current_task_handler.execute(parameters);
		}
		
		return result_parameters;
	}
	
	private static void update_state(Action action, Long id, Kernel kernel, Connection c) throws SQLException
	{
		PreparedStatement ps = null;
	//	String table_name = kernel.g
		try
		{
			ps = c.prepareStatement("update ");
		}
		catch(SQLException e)
		{
			throw e;
		}
		finally
		{
			if(ps!=null)ps.close();
		}
	}
}
