package com.is.login;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AppInit implements Initiator {
	
	@Override
	public void doInit(Page page, @SuppressWarnings("rawtypes") Map arg) throws Exception {

		if (!page.getDesktop().getRequestPath().equals("/index.zul")) {
			Executions.getCurrent().sendRedirect("/");
		}
	}

	@Override
	public void doAfterCompose(Page page) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean doCatch(Throwable ex) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void doFinally() throws Exception {
		// TODO Auto-generated method stub

	}
}