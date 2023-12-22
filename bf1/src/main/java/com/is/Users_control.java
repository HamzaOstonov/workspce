package com.is;

import java.io.IOException;
import java.io.PrintWriter;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import users_control.;



/**
 * Servlet implementation class Users_control
 */
public class Users_control extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private users_sessions us = new users_sessions();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Users_control() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user = Integer.parseInt(request.getParameter("uid"));
		int session = Integer.parseInt(request.getParameter("session"));
		String action = request.getParameter("action");
		
		if (action.compareTo("login") == 0)
		{
			if (us.contains_session(session))
				us.remove_by_session(session);
			if (us.contains_user(user))
				us.remove_by_user(user);
			
			us.put(user, session, false);
			return;
		}
		if (action.compareTo("action") == 0)
		{
			boolean res = false;
			if (us.contains_session(session) && us.contains_user(user))
			{
				if ((us.get_user(session) != user)||(us.get_session(user) != session))
				{
					res = false;
					us.remove_by_session(session);
					us.remove_by_user(user);
				}
				else res = true;
			}
			else
			{
				//if (us.contains_session(session))us.remove_by_session(session);
				//if (us.contains_user(user))us.remove_by_user(user);
				res = false;
			}
			
			PrintWriter out = response.getWriter();
	        out.println(res?1:0);
	        out.flush();
	        out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
