package com.is.humo;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis.Message;
import org.apache.axis.message.SOAPBodyElement;
import org.apache.axis.message.SOAPEnvelope;

import com.is.ConnectionPool;
import com.is.ISLogger;

public class DataUpload {

	public static final String issuingWsUrl = "https://128.10.10.210:8443/ws/services/Issuing";
	

	static IssuingPortProxy issuingPortProxy = new IssuingPortProxy(
			ConnectionPool.getValue("HUMO_HOST_BATCH"),
			ConnectionPool.getValue("HUMO_USERNAME_BATCH"),
			ConnectionPool.getValue("HUMO_PASSWORD_BATCH"));

	public static final String messageStr =
		 "<executeSoapBatch2>\n" +
		 " <Action>1</Action>\n" +
		 " <Options>0</Options>\n" +
		 " <ProcessId>process_1</ProcessId>\n" +
		 "</executeSoapBatch2>\n";
	
	public static void dataUpload() throws Exception {
		try {
			
			System.out.println("# Uploading batch file to IssuingWS.");
			// prepare SOAP message
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage requestMessage = messageFactory.createMessage();
			// prepare SOAP message
 
			// attach batch to SOAP message
			AttachmentPart ap = requestMessage.createAttachmentPart();
			ap.setContentId("HumoData");
			ap.setContent(new FileInputStream(
					"D:/soap_batch/data_upload.txt"),
					"application/octet-stream");
			requestMessage.addAttachmentPart(ap);
			
			SOAPPart soapPart = requestMessage.getSOAPPart();
			soapPart.getEnvelope();
			requestMessage.saveChanges();
			
			// call IssuingWS
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection connection = soapConnFactory.createConnection();
			Message responseMessage = (Message) connection.call(requestMessage,
					issuingWsUrl);
			System.out.println("# Received response from IssuingWS: \n"
					+ responseMessage.getSOAPPartAsString());
			connection.close();

		} catch (Exception ex) {

			ex.printStackTrace();
			System.out.println(ex);
			ISLogger.getLogger().error(ex);

		}
	}
}