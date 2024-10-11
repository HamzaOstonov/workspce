package com.is.humo;

import globus.IssuingWS.IssuingPortProxy;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.axis.Message;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPEnvelope;

import com.is.ConnectionPool;

import javax.activation.DataHandler;

public class DataDownload {

	 public static final String issuingWsUrl =
	 "http://128.10.10.203:8080/HumoGV/services/Issuing";

	 
	 public static final String messageStr =
		 "<executeSoapBatch2>\n" +
		 " <Action>3</Action>\n" +
		 " <Options>0</Options>\n" +
		 " <ProcessId>process_1</ProcessId>\n" +
		 "</executeSoapBatch2>\n";
	 
	 
	static IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
			ConnectionPool.getValue("HUMO_HOST"),
			ConnectionPool.getValue("HUMO_USERNAME"),
			ConnectionPool.getValue("HUMO_PASSWORD"));
	
	

	public static void dataDownload() throws Exception {

		System.out.println("# Downloading batch file from IssuingWS.");
		// prepare SOAP message
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage requestMessage =  messageFactory.createMessage();
		
	
		 SOAPEnvelope envelope = ((Message) requestMessage).getSOAPEnvelope();
		 InputStream is = new ByteArrayInputStream(messageStr.getBytes("UTF-8"));
		 SOAPBodyElement body = new SOAPBodyElement(is);
		 body.setNamespaceURI("");
		 envelope.addBodyElement(body);
		
		// call IssuingWS
		SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection connection = soapConnFactory.createConnection();
		Message responseMessage = (Message) connection.call(requestMessage,
				issuingWsUrl);
		System.out.println("# Received response from IssuingWS: \n"
				+ responseMessage.getSOAPPartAsString());
		connection.close();
		// save downloaded batch data to file
		DataHandler dh = null;
		Iterator iter = responseMessage.getAttachments();
		while (iter.hasNext()) {
			AttachmentPart ap = (AttachmentPart) iter.next();
			if (ap.getContentId().equals("HumoData")) {
				dh = ap.getDataHandler();
				dh.writeTo(new FileOutputStream(
						"E:/Humo/HumoBatch/HumoBatch.download"));
				break;
			}
		}
	}
}
