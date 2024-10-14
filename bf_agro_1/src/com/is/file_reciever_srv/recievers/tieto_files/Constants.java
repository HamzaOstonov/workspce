package com.is.file_reciever_srv.recievers.tieto_files;

import java.util.EnumSet;
import java.util.HashMap;

public class Constants {
	
	public static final Long FILE_ACCEPTED = 1L;
	public static final Long FILE_PROCESSED = 2L;
	public static final Long FILE_PROCESSED_WITH_ERRORS = 3L;
	
	public static final Long RECORD_ACCEPTED = 1L;
	public static final Long RECORD_PROCESSED = 2L;
	public static final Long RECORD_ERROR = 3L;
	
	// Table DEAL_GROUP
	public static final Long PARENT_GROUP_ID = 199L;
	
	public static final Integer PARENT_DEAL_ID = 1;
	
	public static final Long ACTION_ID_INPUT = 1L;
	
	// Table TIETO_FILE_TYPES
	public static final Long B_FILE_TYPE_ID = 1L;
	public static final Long XPT_FILE_TYPE_ID = 2L;
	

//	public static enum Operations {
//		BUY(3000), BUY_SUM(3001), SELL(3002), SELL_SUM(3003), CLEARING(3004),
//		TERMINAL(3005), VISA_REFILL(3006), VISA_WITHDRAW(3007);
//		
//		private static HashMap<Long, Operations> lookup = new HashMap<>();
//		private long number;
//
//		static {
//			for (Operations operation: EnumSet.allOf(Operations.class)) {
//				 lookup.put(operation.getNumber(), operation);
//			}
//		}
//		
//		private Operations(long number) {
//			this.number = number;
//		}
//		
//		public long getNumber() {
//			return number;
//		}
//		
//		public static Operations getLong(long number) {
//			return lookup.get(number);
//		}
//	}

}
