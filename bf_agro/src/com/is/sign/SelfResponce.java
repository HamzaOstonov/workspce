package com.is.sign;

import java.util.HashMap;

public class SelfResponce {
	
	/*
	private HashMap<Integer,String> responces = new HashMap<Integer, String>()
			{
			    {
			        put(0, "Ok");
			        put(1, "Invalid credentials");
			        put(2, "Small credit");
			        put(3, "Abonent not found");
			        put(4, "Provider not found");
			        put(5, "Provider not responding");
			        put(6, "Transaction in progress");
			        put(7, "Transaction not exists");
			        put(8, "Exception on provider side");//Other error
			    }
			};
	*/
	private HashMap<Integer,String> responces = new HashMap<Integer, String>()
			{
			    {
			    	put(0, "Ok");
			        put(1, "Invalid credentials");
			        put(2, "Supplier not found");
					put(3, "Small credit");
			        put(4, "Abonent not found");
					put(5, "Accepting payments is impossible for that abonent");
			        put(6, "Provider not found");
			        put(7, "Provider not responding");
			        put(8, "Error on provider side");
			        put(9, "Transaction in progress");
			        put(10, "Transaction not exists");
			        put(11, "Transaction succesfully completed");
			        put(12, "Transaction failure");
			        put(13, "Access denied");
			        put(14, "Action is not allowed");
					put(15, "PaymentRequest details not true");
					put(16, "PaymentRequest wasn't created");
					put(17, "PaymentRequest wasn't committed");
					put(18, "Other error");
			    }
			};
	public HashMap<Integer,String> getResponces(){
		return responces;
	}
}
