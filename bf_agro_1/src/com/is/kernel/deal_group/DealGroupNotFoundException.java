package com.is.kernel.deal_group;

import com.is.kernel.KernelException;

public class DealGroupNotFoundException extends KernelException
{
	public DealGroupNotFoundException(String message)
	{
		super(message);
	}
	
	public DealGroupNotFoundException()
	{
		super();
	}
}
