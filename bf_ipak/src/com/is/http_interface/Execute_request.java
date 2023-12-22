package com.is.http_interface;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.is.kernel.Kernel;
import com.is.ConnectionPool;
import com.is.LtLogger;

/**
 * Servlet implementation class Execute_request
 */
public class Execute_request extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Execute_request() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Kernel kernel = new Kernel();
		Connection c = null;
		StringBuffer res = new StringBuffer();
		try
		{
			c = ConnectionPool.getConnection();
			kernel.init(c);
			res.append(Service.execute_action(request, kernel, c));
			c.commit();
			PrintWriter out = response.getWriter();
		    out.println(res);
		    out.flush();
		    out.close();
		} 
		catch (Exception e)
		{
			try{if(c != null) c.rollback();}catch(Exception e1){}
			LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
			
			res.append("http_interface_start_result<del>");
			res.append("http_interface_request_id="+request.getParameter("request_id")+"<del>");
			res.append("http_interface_error_code=-1<del>");
			res.append("http_interface_error_class="+e.getClass().getName()+"<del>");
			res.append("http_interface_error_message="+com.is.utils.CheckNull.getPstr(e)+"<del>");
			res.append("http_interface_end_result<del>");
		}
		finally
		{
			try{if(c != null) c.close();}catch(Exception e1){}
			PrintWriter out = response.getWriter();
		    out.println(res.toString());
		    out.flush();
		    out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
