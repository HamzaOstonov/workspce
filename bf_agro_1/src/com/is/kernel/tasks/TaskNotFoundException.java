package com.is.kernel.tasks;

import com.is.kernel.KernelException;

public class TaskNotFoundException extends KernelException
{
	public TaskNotFoundException(String message)
	{
		super(message);
	}
	
	public TaskNotFoundException()
	{
		super();
	}
}
