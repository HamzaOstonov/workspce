package com.is.kernel.parameter;

import com.is.kernel.KernelException;

public class ParameterNotFoundException extends KernelException
{
	public ParameterNotFoundException(String message)
	{
		super(message);
	}
	public ParameterNotFoundException()
	{
		super();
	}
}
