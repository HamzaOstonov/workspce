package com.is.kernel;

import java.sql.Connection;
import java.sql.SQLException;

import com.is.kernel.actions.Actions;
import com.is.kernel.parameter.Parameters;
import com.is.kernel.reactions.Reactions;
import com.is.kernel.states.States;
import com.is.kernel.tasks.Tasks;
import com.is.kernel.trans.Transes;


public class Kernel
{
	/*private static States states = null;
	private static Actions actions = null;
	private static Tasks tasks = null;
	private static Reactions reactions = null;
	private static Transes transes = null;
	
	public static void init(Connection c) throws SQLException
	{
		states = new States(c);
		actions = new Actions(c);
		tasks = new Tasks(c);
		reactions = new Reactions(c);
		transes = new Transes(c);
	}*/
	
	private States states = null;
	private Actions actions = null;
	private Tasks tasks = null;
	private Reactions reactions = null;
	private Transes transes = null;
	
	public void init(Connection c) throws SQLException
	{
		states = new States(c);
		actions = new Actions(c);
		tasks = new Tasks(c);
		reactions = new Reactions(c);
		transes = new Transes(c);
	}
/*	
	public static States getStates()
	{
		return states;
	}
	public static void setStates(States states)
	{
		Kernel.states = states;
	}
	public static Actions getActions()
	{
		return actions;
	}
	public static void setActions(Actions actions)
	{
		Kernel.actions = actions;
	}
	public static Tasks getTasks()
	{
		return tasks;
	}
	public static void setTasks(Tasks tasks)
	{
		Kernel.tasks = tasks;
	}
	public static Reactions getReactions()
	{
		return reactions;
	}
	public static void setReactions(Reactions reactions)
	{
		Kernel.reactions = reactions;
	}
	public static Transes getTranses()
	{
		return transes;
	}
	public static void setTranses(Transes transes)
	{
		Kernel.transes = transes;
	}	
	
	*/
	
	public States getStates()
	{
		return states;
	}
	public void setStates(States states)
	{
		this.states = states;
	}
	public Actions getActions()
	{
		return actions;
	}
	public void setActions(Actions actions)
	{
		this.actions = actions;
	}
	public Tasks getTasks()
	{
		return tasks;
	}
	public void setTasks(Tasks tasks)
	{
		this.tasks = tasks;
	}
	public Reactions getReactions()
	{
		return reactions;
	}
	public void setReactions(Reactions reactions)
	{
		this.reactions = reactions;
	}
	public Transes getTranses()
	{
		return transes;
	}
	public void setTranses(Transes transes)
	{
		this.transes = transes;
	}	
	//public static Parameters 
}
