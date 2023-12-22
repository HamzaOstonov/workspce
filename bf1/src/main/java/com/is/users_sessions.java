package com.is;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class users_sessions
{
	private HashMap<Integer, Integer> users_u = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> users_id = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> sessions_s = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> sessions_id = new HashMap<Integer, Integer>();
	private HashMap<Integer, Boolean> values = new HashMap<Integer, Boolean>();
	private static int k = 0;
	
	public int get_user(int session)
	{
		int t = sessions_s.get(session);
		return users_id.get(t);
	}
	public int get_session(int user)
	{
		int t = users_u.get(user);
		return sessions_id.get(t);
	}
	public boolean should_brake_session(int session)
	{
		int t = sessions_s.get(session);
		return values.get(t);
	}
	public boolean should_brake_user(int user)
	{
		int t = users_u.get(user);
		return values.get(t);
	}
	public void put(int user, int session, boolean val)
	{
		//int n = users_u.size();
		users_u.put(user, k);
		users_id.put(k, user);
		sessions_s.put(session, k);
		sessions_id.put(k, session);
		values.put(k, val);
		k++;
	}
	public void remove_by_session (int session)
	{
		int t = sessions_s.get(session);
		sessions_s.remove(session);
		sessions_id.remove(t);
		int user = users_id.get(t);
		users_id.remove(t);
		users_u.remove(user);
		values.remove(t);
	}
	public void remove_by_user (int user)
	{
		int t = users_u.get(user);
		users_u.remove(user);
		users_id.remove(t);
		int session = sessions_id.get(t);
		sessions_id.remove(t);
		sessions_s.remove(session);
		values.remove(t);
	}
	public void set_val_user(int user, boolean val)
	{
		int t = users_u.get(user);
		values.put(t, val);
	}
	public void set_val_session(int session, boolean val)
	{
		int t = sessions_s.get(session);
		values.put(t, val);
	}
	public boolean contains_user(int user)
	{
		return users_u.containsKey(user);
	}
	public boolean contains_session(int session)
	{
		return sessions_s.containsKey(session);
	}
	public String toString()
	{
		String res = "";
		
		Iterator it = users_u.entrySet().iterator();
		
		while (it.hasNext())
		{
			Map.Entry pairs = (Map.Entry)it.next();
			
			res += pairs.getValue();
			res += " " + pairs.getKey();
			res += " " + sessions_id.get(pairs.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
		return res;
	}
}