package com.is;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.is.comjpayment.ComJPayTimerClass;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        //if (ConnectionPool.getValue("COM_USE_CHECK_PAYMENTS_TIMER").compareTo("1")==0)
        //ComJPayTimerClass.start();
    }
    public void destroy(){
    	//if (ConnectionPool.getValue("COM_USE_CHECK_PAYMENTS_TIMER").compareTo("1")==0)
        //ComJPayTimerClass.stop();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
