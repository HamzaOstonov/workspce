package com.is.kernel.reactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.is.kernel.actions.Action;
import com.is.kernel.actions.Action_pk;


public class Reactions
{
	private HashMap<Action_pk, HashMap<Long, Reaction>> reactions;
	
	public Reactions(Connection c) throws SQLException
	{
		reactions = Service.get_reactions(c);
	}
	
	public HashMap<Long, Reaction> get(Action_pk action_pk) throws ReactionNotFoundException
	{
		HashMap<Long, Reaction> res = reactions.get(action_pk);
		if (res == null) throw new ReactionNotFoundException(action_pk.toString());
		return res;
	}
	
	public HashMap<Long, Reaction> get(Action action) throws ReactionNotFoundException
	{
		Action_pk action_pk = new Action_pk(
				action.getDeal_group_id(), 
				action.getDeal_id(), 
				action.getId());
		return this.get(action_pk);
	}
	
	public Reaction get(Reaction_pk reaction_pk) throws ReactionNotFoundException
	{
		Reaction res = reactions.get(new Action_pk(
				reaction_pk.getDeal_group_id(), 
				reaction_pk.getDeal_id(), 
				reaction_pk.getAction_id())).get(reaction_pk.getOrd());
		if (res == null) throw new ReactionNotFoundException(reaction_pk.toString());
		return res;
	}
}
