package com.is.file_reciever_srv.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.is.ISLogger;
import com.is.file_reciever_srv.common.File_service;

import universal_reader.Universal_reader;


/**
 * Servlet implementation class Main_srv
 */
public class Main_srv extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
	private static Logger logger = ISLogger.getLogger();
	
    public Main_srv()
    {
        super();
        File_service.create_files();
        Timer_contayner.run();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

}
