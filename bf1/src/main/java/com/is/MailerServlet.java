package com.is;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Main
 */

public class MailerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailerServlet() {
        super();
        //if (ConnectionPool.getValue("COM_USE_CHECK_PAYMENTS_TIMER").compareTo("1")==0)
        //ComJPayTimerClass.start();
       // MailerTimerClass.start();
    }
    public void destroy(){
    	//if (ConnectionPool.getValue("COM_USE_CHECK_PAYMENTS_TIMER").compareTo("1")==0)
        //ComJPayTimerClass.stop();
    	//MailerTimerClass.stop();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(this.getClass().getClassLoader().getResource("").getPath().replace("/WEB-INF/classes/",""));
        //System.out.println(new File("..").getAbsolutePath());
        //System.out.println("path: "+Executions.getCurrent().locate("/reports/Fault_Payments.jasper")+"; ");//params: "+params.size()+";");
        //Parameter jrep = new Parameter();
        //System.out.println("path: "+jrep.getClass().getClassLoader().getResource("").getPath().replace("/WEB-INF/classes/","")+jrep.getDef_par_value()+"; ");//params: "+params.size()+";");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
