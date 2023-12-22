package com.is.tf.sign;

import com.is.ISLogger;
import com.is.login.SessionController;
import com.is.tf.sign.Canonicalizer;
import com.is.tf.sign.SignResult;
import com.is.tf.sign.SignUtils;
import com.is.utils.CheckNull;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.handlers.BasicHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class SignTFHandler extends BasicHandler {
	static final long serialVersionUID = 1561653314L;
    private static final String SECURITY_ELEMENT = "Security";
    private static final String ACCESS_ID_ELEMENT = "AccessID";
    private static final String TIMESTAMP_ELEMENT = "Timestamp";
    private static final String SIGNATURE_ELEMENT = "Signature";
    private static SessionController sc = new SessionController();
	private static String tflog = SignUtils.getBF_SETS("TF_SIGN_LOG");
    @Override
    public void init() {
        System.out.println("init called");
        super.init();
        System.out.println("init called");
    }

    @Override
    public void cleanup() {
        super.cleanup();
        System.out.println("cleanup called");
    }

    @Override
    public void invoke(MessageContext context) throws AxisFault {
    	//String clientIP = ((HttpServletRequest) messageContext.get(MessageContext.SERVLET_REQUEST)).getRemoteAddr();
    	//sc.getSessionObject("");
    	System.out.println("********************************************");
    	String methodName = context.getOperation().getName();
    	//String _methodName =  context.getOperation().getMethod().getName();
        System.out.println(methodName);//+" - "+ _methodName
        SignResult signResult = null;
        String respclass = "";
        //context.getAllPropertyNames();
        boolean request = (context.getRequestMessage() != null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        	//context.setEncodingStyle("cp1251");
        	//String string = new String(context.getRequestMessage().getSOAPBody().toString().getBytes(), Charset.forName("UTF-8"));
        	//System.out.println("+++"+string);
        	//System.out.println("+++"+context.getRequestMessage().getSOAPBody().toString()+" enc = "+context.getProperty("requestEncoding"));//context
            System.out.println("********************************************");
        	if (request) {
        		try {
        			respclass = context.getRequestMessage().getMessageContext().getOperation().getReturnParamDesc().getJavaType().getName();
        			System.out.println(context.getRequestMessage().getMessageContext().getOperation().getReturnParamDesc().getJavaType().getName());
        		} catch (Exception e) {
					e.printStackTrace(); ISLogger.getLogger().warn(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage());
				}
        		if (!CheckNull.isEmpty(respclass) && respclass.startsWith("com.sbs.service")) {
        			System.out.println("is com.sbs.service request");
        			ISLogger.getLogger().warn("sign " + methodName +" is com.sbs.service" + (request ? " request " : " response "));
        			SOAPMessage soapMessage = context.getMessage();
        			SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
		            SOAPHeader soapHeader = soapEnvelope.getHeader();
		            SOAPBody soapBody = soapEnvelope.getBody();
		            
		            //System.out.println(soapEnvelope.toString());
		            
		            signResult = SignUtils.signTF(soapBody, (String)sc.getSessionObject("un"), (String)sc.getSessionObject("pwd"), (String)sc.getSessionObject("alias"));
		            SignResult res = signResult;
		            System.out.println(signResult.getCode()+" - "+signResult.getMessage());
		            ISLogger.getLogger().warn("resp sign " + signResult.getCode()+" - "+signResult.getMessage() +"|"+ res.getCode()+" - "+res.getMessage());
		            if (signResult.getCode() == 0) {
		            	//throw new Exception(signResult.getMessage());
		                if (soapHeader == null) {
		                    soapHeader = soapEnvelope.addHeader();
		                }
		                
		                com.is.tf.sign.QName qname = getQName(soapBody, SECURITY_ELEMENT);
		                
			            SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement((Name)qname);
			            SOAPElement soapElement = soapHeaderElement.addChildElement(ACCESS_ID_ELEMENT);
			            soapElement.addTextNode(signResult.getSerialNumber());
			            soapElement = soapHeaderElement.addChildElement(TIMESTAMP_ELEMENT);
			            soapElement.addTextNode(String.valueOf(signResult.getTimeStamp()));
			            soapElement = soapHeaderElement.addChildElement(SIGNATURE_ELEMENT);
			            soapElement.addTextNode(String.valueOf(signResult.getSignature()));
			            soapMessage.saveChanges();
			            ISLogger.getLogger().warn("sign in " + methodName + signResult.getCode()+" - "+signResult.getMessage()+"|"+ res.getCode()+" - "+res.getMessage());
			            //ISLogger.getLogger().warn("sign in " + methodName +"|"+ res.getCode()+" - "+res.getMessage()+"|"+ (request ? " request " : " response ")+" code: " + ((signResult == null || CheckNull.isEmpty(signResult.getCode()))?" null":(signResult.getCode() + signResult.getMessage())));
			        }
        		}
            }
            context.getMessage().writeTo(out);
            ISLogger.getLogger().warn("sign out \n" + "======== " + methodName + (request ? " request" : " response") + " ========\n" + out.toString("utf-8"));
            
        } catch (SOAPException e) {
        	e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
        	ISLogger.getLogger().warn("sign " + methodName + (request ? " request " : " response ")
        			+" code: " + (((signResult == null || CheckNull.isEmpty(signResult.getCode()))?" null":(signResult.getCode() + signResult.getMessage())))
        			+"; err: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
        } catch (IOException e) {
        	e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
        	ISLogger.getLogger().warn("sign " + methodName + (request ? " request " : " response ")+" code: " + (((signResult == null || CheckNull.isEmpty(signResult.getCode()))?" null":(signResult.getCode() + signResult.getMessage())))+"; err: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
        } catch (Exception e) {
        	e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
        	ISLogger.getLogger().warn("sign " + methodName + (request ? " request " : " response ")+" code: " + (((signResult == null || CheckNull.isEmpty(signResult.getCode()))?" null":(signResult.getCode() + signResult.getMessage())))+"; err: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
        }
        	
        /*
    	boolean response = !(Boolean) context.getOperation().getMethod()
        		
        		(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        	System.out.println("********************************************");
        	if (!response) {
                SOAPMessage soapMessage = context.getMessage();
                SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnvelope.getHeader();
                SOAPBody soapBody = soapEnvelope.getBody();
                SignResult signResult = SignUtils.signData("1111", Canonicalizer.toText(soapBody));

                if (soapHeader == null) {
                    soapHeader = soapEnvelope.addHeader();
                }
                QName qname = getQName(soapBody, SECURITY_ELEMENT);
                
                
                SOAPHeaderElement soapHeaderElement = soapHeader.addHeaderElement((Name)qname);
                SOAPElement soapElement = soapHeaderElement.addChildElement(ACCESS_ID_ELEMENT);
                soapElement.addTextNode(signResult.getSerialNumber());
                soapElement = soapHeaderElement.addChildElement(TIMESTAMP_ELEMENT);
                soapElement.addTextNode(String.valueOf(signResult.getTimeStamp()));
                soapElement = soapHeaderElement.addChildElement(SIGNATURE_ELEMENT);
                soapElement.addTextNode(String.valueOf(signResult.getSignature()));
                soapMessage.saveChanges();
            }
            context.getMessage().writeTo(out);
            System.out.println("\n" + "======== " + methodName + (response ? " response" : " request") + " ========\n" + out.toString("utf-8"));
        } catch (SOAPException e) {
            System.out.println(e);
            //return false;
        } catch (IOException e) {
            System.out.println(e);
            //return false;
        } catch (Exception e) {
            System.out.println(e);
            //return false;
        }
        return true;
        */
    }

    public Set<QName> getHeaders() {
    	//System.out.println("********************************************");
    	return Collections.EMPTY_SET;
    }
    /*
    public boolean handleMessage(SOAPMessageContext context) {
    	
    	return true;
    }
	*/
    private com.is.tf.sign.QName getQName(SOAPBody body, String localName) {
    	System.out.println("********************************************");
    	Iterator i = body.getChildElements();
        if (!i.hasNext())
            return null;
        SOAPElement methodElement = (SOAPElement) i.next();
        String namespaceURI = methodElement.getNamespaceURI();
        String prefix = methodElement.getPrefix();
        System.out.println(methodElement+" - "+namespaceURI+" - "+prefix);
        if (CheckNull.isEmpty(prefix)) {
        	return new com.is.tf.sign.QName(namespaceURI, localName);
        }
        return new com.is.tf.sign.QName(namespaceURI, localName, prefix);
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
    	//System.out.println("********************************************");
    	return true;
    }

    public void close(MessageContext context) {
    	//System.out.println("********************************************");
    	
    }
}