package com.is.kernel.states;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class States
{
	private HashMap<State_pk, State> states = null;
	
	public States(Connection c) throws SQLException
	{
		states = Service.get_states(c);
	}
	
	public State get(State_pk state_pk) throws StateNotFoundException
	{
		State res = states.get(state_pk);
		if (res == null) throw new StateNotFoundException(state_pk.toString());
		return res;
	}
}
