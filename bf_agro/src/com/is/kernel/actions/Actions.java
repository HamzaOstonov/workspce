package com.is.kernel.actions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class Actions
{
	private HashMap<Action_pk, Action> actions;
	
	public Actions(Connection c) throws SQLException
	{
		actions = Service.get_actions(c);
	}
	
	public Action get(Action_pk action_pk) throws ActionNotFoundException
	{
		Action_pk apk1 = new Action_pk(123l, 3l, 78l);
		Action_pk apk2 = new Action_pk(123l, 3l, 78l);
		Action res = actions.get(action_pk);
		if (res == null) throw new ActionNotFoundException(action_pk.toString());
		return res;
	}
}
