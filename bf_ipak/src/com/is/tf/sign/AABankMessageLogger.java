package com.is.tf.sign;

import com.is.tf.sign.Canonicalizer;
import com.is.tf.sign.SignResult;
import com.is.tf.sign.SignUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class AABankMessageLogger implements SOAPHandler<SOAPMessageContext> {
    private static final String SECURITY_ELEMENT = "Security";
    private static final String ACCESS_ID_ELEMENT = "AccessID";
    private static final String TIMESTAMP_ELEMENT = "Timestamp";
    private static final String SIGNATURE_ELEMENT = "Signature";

    public boolean handleMessage(SOAPMessageContext context) {
    	//String clientIP = ((HttpServletRequest) messageContext.get(MessageContext.SERVLET_REQUEST)).getRemoteAddr();
    	System.out.println("********************************************");
    	String methodName = ((QName) context.get(MessageContext.WSDL_OPERATION)).getLocalPart();
        boolean response = !(Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
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
            return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    private QName getQName(SOAPBody body, String localName) {
    	System.out.println("********************************************");
    	Iterator i = body.getChildElements();
        if (!i.hasNext())
            return null;
        SOAPElement methodElement = (SOAPElement) i.next();
        String namespaceURI = methodElement.getNamespaceURI();
        String prefix = methodElement.getPrefix();
        
        return new QName(namespaceURI, localName, prefix);
    }
    
    public Set<QName> getHeaders() {
    	System.out.println("********************************************");
    	return Collections.EMPTY_SET;
    }

    public boolean handleFault(SOAPMessageContext messageContext) {
    	System.out.println("********************************************");
    	return true;
    }

    public void close(MessageContext context) {
    	System.out.println("********************************************");
    	
    }
}