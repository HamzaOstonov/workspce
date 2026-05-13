package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.file_reciever_srv.simple.reciever_class.Sender_class;
import com.is.utils.CheckNull;

public class Sender extends Sender_class
{
	private static Logger logger = ISLogger.getLogger();
	@Override
	public void run()
	{
		try
		{
			ISLogger.getLogger().info("running request_senders");
			Request_service.process_requests(Request_service.get_requests_to_proceed());
			Request_service.do_transactions(Request_service.get_tr_pays_to_do_transactions());
			Request_service.create_out_files();
			
			Reversal_service.process_reversals(Reversal_service.get_reversals_to_process());
			
			Approval_service.process_cb_approvals(Approval_service.get_cb_approvals_to_process());
		}
		catch(Exception e)
		{
			logger.error(CheckNull.getPstr(e));
		}
	}
}
