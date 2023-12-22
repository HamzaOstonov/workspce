package com.is.clients;

import java.util.HashMap;

public class TimerSession
{
	//TODO
	private static HashMap<String, Object> attributes = new HashMap<String, Object>();
	public static void setAttribute(String name, Object value)
	{
		attributes.put(name, value);
	}
	public static Object getAttribute(String name)
	{
		return attributes.get(name);
	}
}
