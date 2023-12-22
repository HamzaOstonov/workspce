package com.is.tf.sign;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.util.logging.Logger;

import org.w3c.dom.*;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;

import javax.xml.ws.soap.SOAPFaultException;

import javax.xml.ws.handler.soap.SOAPMessageContext;


/*
 * Verifies the signature on inbound messages.
 */
public class SignatureVerificationHandler {//extends BaseHandler

  private static final Logger log = Logger.getLogger("member");

  private static final long MAX_TIME_RANGE_MILLIS = 1000 * 60 * 5;

  public boolean handleInbound(SOAPMessageContext smc) throws SOAPException {
	/*
    SOAPMessage message = smc.getMessage();
    SOAPPart part = message.getSOAPPart();

    SOAPEnvelope envelope = part.getEnvelope();
    
    SOAPBody body = envelope.getBody();
    if (body == null) { 
      throw new RuntimeException("Missing required message body.");
    }
    
    SOAPElement securityElement = getSecurityElement(envelope);
    String accessID = getChildText(securityElement, ACCESS_ID_ELEMENT);
    String timestamp = getChildText(securityElement, TIMESTAMP_ELEMENT);
    Date submittedDate = Timestamp.parse(timestamp);
    
    long timeRangeMillis = 
      Math.abs(System.currentTimeMillis() - submittedDate.getTime());
    if (timeRangeMillis > MAX_TIME_RANGE_MILLIS) {
      SOAPFault fault = 
        getInstance().createFault("Timestamp outside allowed range",
     getQName(body, "InvalidTimestamp"));
      throw new SOAPFaultException(fault);
    }
    
    String submittedSignature = 
      getChildText(securityElement, SIGNATURE_ELEMENT);
    
    String bodyText = Canonicalizer.toText(body);
    String signedText = timestamp + ":" + accessID + ":" + bodyText;
    String secretKey = getSecretKey(accessID);
    String verifiedSignature = Signature.create(secretKey, signedText);
    
    if (! submittedSignature.equals(verifiedSignature)) {
      SOAPFault fault = 
        getInstance().createFault("Failed to verify the message signature",
        getQName(body, "InvalidSignature"));
      throw new SOAPFaultException(fault);
    }
	*/
    return true;
  }

  private SOAPElement getSecurityElement(SOAPEnvelope envelope) 
    throws SOAPException {
	/* 
    SOAPHeader header = envelope.getHeader();
    
    if (header == null) {
      throw new RuntimeException("Missing required message header");
    }

    QName qName = getQName(envelope.getBody(), SECURITY_ELEMENT);
    if (qName == null) {
      throw new RuntimeException("Missing required method element.");
    }

    Iterator i = header.getChildElements(qName);
    if (! i.hasNext()) {
      throw new RuntimeException("Missing required security element.");
    }

    SOAPElement securityElement = (SOAPElement) i.next();
    
    return securityElement;
    */
    return envelope.getBody();
  }

  private String getChildText(SOAPElement element, String name) {
    /*
    Iterator i = element.getChildElements(new QName("", name, ""));
    if (! i.hasNext()) {
      return "";
    }
    
    SOAPElement child = (SOAPElement) i.next();

    Node textNode = child.getFirstChild();
    if (textNode == null) { 
      return ""; 
    }

    if (textNode.getNodeType() != Node.TEXT_NODE) {
      return "";
    }

    return ((CharacterData) textNode).getData();*/
	  return "";
  }

  /**
    * Placeholder method.
    **/
  private String getSecretKey(String accessID) {
    return "secret";
  }
}