package com.is.login;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.util.ExecutionInit;


public class AppExecution implements ExecutionInit{

	//private static HashMap<Integer, Session_> active_sessions = new HashMap<Integer, Session_>();
	
//	private static users_sessions us = new users_sessions();
	
	private SessionController sessionController = new SessionController();
	/*AppExecution()
	{
		//super();
		//active_sessions = new HashMap<String, Integer>();
		System.out.println("Init");
	}*/
	
	
	
	@Override
	public void init(Execution exec, Execution parent) throws Exception
	{
		if (!exec.getSession().hasAttribute("uid"))return;
		
		String req = "http://"+exec.getLocalAddr()+":"+exec.getLocalPort() +
				"/bf/Users_control?uid="+
		(Integer)sessionController.getSessionObject("uid")+
		"&session="+
		exec.getSession().getNativeSession().hashCode()+
		"&action=action";
		
		URL url = new URL(req);
		
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		String strres = getResp(connection.getInputStream(), "cp1251");
		
	//	System.out.println("Request:"+req);
	//	System.out.println("Response:"+strres.substring(0, 1)+"--");
		
		if (Integer.parseInt(strres.substring(0, 1)) == 0)
		{
			sessionController.setSessionObject("isLoggedIn", false);
            sessionController.setSessionObject("CurrUser", null);
            sessionController.setSessionObject("uid", null);
            exec.sendRedirect("/");
		}
		
		//System.out.println ("addr:"+exec.getLocalAddr()+":"+exec.getLocalPort()+"/bf");
		
		
		// TODO Auto-generated method stub
		//System.out.println("user "+ exec.getSession().getAttribute("CurrUser") +" session "+exec.getSession().getNativeSession().hashCode());
	/*	if (!exec.getSession().hasAttribute("uid"))return;
		
		int user = (Integer)sessionController.getSessionObject("uid");
		int session = exec.getSession().getNativeSession().hashCode();
		
		if (exit_if_should(exec, session, user)) return;
		
		if ((!us.contains_session(session))&&(!us.contains_user(user)))
		{
			us.put(user, session, false);
			return;
		}
		
		if (us.contains_session(session))
		{
			if ((us.get_session(user) == session)&&(us.get_user(session) == user)) return;
			us.set_val_session(session, true);
		}
		
		if (us.contains_user(user))
		{
			if ((us.get_session(user) == session)&&(us.get_user(session) == user)) return;
			us.set_val_user(user, true);
		}*/
	}
	
	public String getResp(InputStream stream,String cp)
	{
		StringWriter writer = new StringWriter();
		try {
		IOUtils.copy(stream, writer, cp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
/*	private boolean exit_if_should(Execution exec, int session, int user)
	{
		if (us.contains_session(session))
		{
			if (us.should_brake_session(session))
			{
				sessionController.setSessionObject("isLoggedIn", false);
                sessionController.setSessionObject("CurrUser", null);
                sessionController.setSessionObject("uid", null);
                exec.sendRedirect("/");
				//System.out.println("breaks session");
                us.remove_by_session(session);
				return true;
			}
		}
		if (us.contains_user(user))
		{
			if (us.should_brake_user(user))
			{
				sessionController.setSessionObject("isLoggedIn", false);
                sessionController.setSessionObject("CurrUser", null);
                sessionController.setSessionObject("uid", null);
                exec.sendRedirect("/");
				//System.out.println("breaks session");
                us.remove_by_session(session);
				return true;
			}
		}
		return false;
	}
	*/
	
	
}
