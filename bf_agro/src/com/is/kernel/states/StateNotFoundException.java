package com.is.kernel.states;

import com.is.kernel.KernelException;

public class StateNotFoundException extends KernelException
{
	public StateNotFoundException(String message)
	{
		super(message);
	}
	
	public StateNotFoundException()
	{
		super();
	}
}
