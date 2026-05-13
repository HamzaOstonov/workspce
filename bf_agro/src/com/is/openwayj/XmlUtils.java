package com.is.openwayj;

import java.io.IOException;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.is.openwayj.customer.Customer;
import com.is.openwayj.model.ClientIDT;
import com.is.openwayj.model.ClientInfo;
import com.is.openwayj.model.ClientInfoIdt;
import com.is.openwayj.model.UFXMsgReqClient;
import com.is.openwayj.model.InformationReq;
import com.is.openwayj.model.MsgDataReq;
import com.is.openwayj.model.ObjectFor;
import com.is.openwayj.model.Parm;
import com.is.openwayj.model.ResultDtls;
import com.is.openwayj.model.Source;

import com.is.utils.CheckNull;

public class XmlUtils {

	public void whenJavaGotFromXmlStr_thenCorrect(String p_xml) throws IOException {
	    //XmlMapper xmlMapper = new XmlMapper();
	    //SimpleBean value
	    //  = xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);
	    //assertTrue(value.getX() == 1 && value.getY() == 2);
		
		//ObjectMapper objectMapper = new ObjectMapper();
		//String xml="<?xml version='1.0' encoding='UTF-8'?>"+
		//	"<Person>"+
        // " <age>4</age>"+
        // "  <_id>12345</_id>"+
        //"   <name>George</name>"+
        //"</Person>";
		//Person bean = objectMapper.readValue(p_xml, Person.class);
		
		 //XmlMapper xmlMapper = new XmlMapper();
		 //UFXMsg value = xmlMapper.readValue(p_xml, UFXMsg.class);
		
		 //System.out.println(value.get_resp_text());
	}
	
	public String serializeXmlFromObject(Object classObject) throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
	    String xml = xmlMapper.writeValueAsString(classObject);
		return xml;
	}

	public String serializeJsonFromObject(Object classObject) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writerWithDefaultPrettyPrinter()
		.writeValueAsString(classObject);
		return json;
	}

	
	public String serializeXml(String p_xml) throws IOException {
	    XmlMapper xmlMapper = new XmlMapper();
	    UFXMsgReqClient cl = new UFXMsgReqClient();
	    cl.setDirection("Rq");
	    cl.setMsg_type("Application");
	    cl.setScheme("WAY4Appl");
	    cl.setVersion("2.3.81");
	    cl.setMsgId("sssssss");
	    
	    Source src = new Source();
	    src.setApp("UZPSB");
	    cl.setSource(src);
	    
	    MsgDataReq msgData = new MsgDataReq();
	    InformationReq inf = new InformationReq();
	    inf.setRegNumber("12312300345");
	    inf.setInstitution("09058");
	    inf.setOrderDprt("01142");
	    inf.setObjectType("Client");
	    inf.setActionType("Inquiry");
	    ResultDtls rdtl=new ResultDtls();
	    Parm parm = new Parm();
	    parm.setParmCode("Client");
	    parm.setValue("Y");
	    rdtl.setParmObject(parm);
	    inf.setResultDtls(rdtl);
	    ObjectFor objFor = new ObjectFor();
	    ClientIDT clIdt= new ClientIDT();
	    ClientInfoIdt clInfIdt = new ClientInfoIdt();
	    clInfIdt.setClientNumber("60000001");
	    clInfIdt.setSocialNumber("56789012340078");
	    clIdt.setClientInfo(clInfIdt);
	    objFor.setClientIDT(clIdt);
	    inf.setObjectFor(objFor);
        msgData.setInformationObject(inf);
        cl.setMsgDataReq(msgData);
        
	    String xml = xmlMapper.writeValueAsString(cl);
	    
	    //String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cl);
		
		System.out.println("xml = " + xml);
		return xml;
	}
}
