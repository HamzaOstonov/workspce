package com.is.kernel.parameter;

import java.util.HashMap;

public class Parameters
{
	private HashMap<String, Object> parameters;
	
	public Parameters()
	{
		parameters = new HashMap<String, Object>();
	}
	
	public void put(String key, Object value)
	{
		this.parameters.put(key.toUpperCase(), value);
	}
	
	public Object get(String key) throws ParameterNotFoundException
	{
		if (! this.parameters.containsKey(key.toUpperCase()))
			throw new ParameterNotFoundException(key + ":parameter not found");
		return this.parameters.get(key.toUpperCase());
	}
	
	public HashMap<String, Object> getParametersHashmap()
	{
		return parameters;
	}
	
	public boolean contains(String key)
	{
		return this.parameters.containsKey(key.toUpperCase());
	}
}
