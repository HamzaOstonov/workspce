package com.is.kernel.trans;

import com.is.kernel.KernelException;

public class TransNotFoundException extends KernelException
{
	public TransNotFoundException(String message)
	{
		super(message);
	}
	
	public TransNotFoundException()
	{
		super();
	}
}
