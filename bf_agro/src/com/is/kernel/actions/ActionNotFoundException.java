package com.is.kernel.actions;

import com.is.kernel.KernelException;

public class ActionNotFoundException extends KernelException
{
	public ActionNotFoundException(String message)
	{
		super(message);
	}
	
	public ActionNotFoundException()
	{
		super();
	}
}
