package com.is.tieto_globuz.customer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ISLogger;
import com.is.tieto_globuz.tieto.TclientService;
import com.is.utils.CheckNull;
import com.is.utils.Res;

/**
 * Servlet implementation class NewCard
 */
public class NewCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		NewCardRequest requestInput = null;
		Res requestOutput = new Res();
		
		try {
			requestInput = objectMapper.readValue(request.getReader(), NewCardRequest.class);
			requestOutput = TclientService.addNewAgreement(requestInput.getSettings(), requestInput.getBranch(), requestInput.getNew_customer(), requestInput.getAcc(), requestInput.getTc(), requestInput.getAlias(), requestInput.getIssuingPortProxy());
		}
		catch (Exception e) {
			requestOutput.setCode(-1);
			requestOutput.setName(e.getMessage());
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		out.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestOutput));

		out.flush();
		out.close();
	}

}
