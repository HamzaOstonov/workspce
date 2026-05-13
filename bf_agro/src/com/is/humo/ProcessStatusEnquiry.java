package com.is.humo;

import globus.IssuingWS.IssuingPortProxy;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import org.apache.axis.Message;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPEnvelope;

import com.is.ConnectionPool;

public class ProcessStatusEnquiry {

	// public static final String issuingWsUrl =
	// "http://localhost:9191/issuingws-trunk/services/Issuing";
	
	public static final String issuingWsUrl = "https://128.10.10.210:8443/ws/services/Issuing?wsdl";

	static IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
			ConnectionPool.getValue("HUMO_HOST"),
			ConnectionPool.getValue("HUMO_USERNAME"),
			ConnectionPool.getValue("HUMO_PASSWORD"));

	public static void main(String[] args) throws Exception {

		System.out.println("# Process Status Enquiry");
		// prepare SOAP message
		MessageFactory messageFactory = MessageFactory.newInstance();
		Message requestMessage = (Message) messageFactory.createMessage();

		// call IssuingWS
		SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection connection = soapConnFactory.createConnection();
		Message responseMessage = (Message) connection.call(requestMessage,
				issuingWsUrl);
		System.out.println("# Received response from IssuingWS: \n"
				+ responseMessage.getSOAPPartAsString());
		connection.close();
	}
}
