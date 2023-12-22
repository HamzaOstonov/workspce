package com.is.kernel.reactions;

import com.is.kernel.KernelException;

public class ReactionNotFoundException extends KernelException
{
	public ReactionNotFoundException(String message)
	{
		super(message);
	}
	
	public ReactionNotFoundException()
	{
		super();
	}
}
