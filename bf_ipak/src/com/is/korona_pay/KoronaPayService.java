package com.is.korona_pay;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Filedownload;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.is.ISLogger;
import com.is.LtLogger;
import com.is.userreport.RepTempl;

public class KoronaPayService {
	
	public KoronaPayService() {
    }
	
	static long serialVersionUID = 1L;
	
	
	public static String getUserInfo(String inputJson, String uid, String un, String pwd, String alias) {
		
		
		System.out.println("uid: " + uid);
		System.out.println("un: " + un);
		System.out.println("pwd: " + pwd);
		System.out.println("alias: " + alias);
		ISLogger.getLogger().error("uid::=" + uid + "un: " + un + "pwd: " + pwd + "alias: " + alias);
		
		Map<String, String> map = KoronaPayDBHelper.getUserInfo(Integer.valueOf(uid), un, pwd, alias); 
		
		System.out.println("UserId= :"+(map.get("AgentId")+""));
		System.out.println("input getUserInfo: " + inputJson);
		String outJson = "{\"RetCode\": 0,\"UserName\":\""+map.get("UserName")+"\",\"FullName\":\""+map.get("FullName")+"\",\"AgentID\":\""+map.get("AgentId")+"\",\"PwdA\":\""+map.get("PWDA")+"\",\"PwdB\":\""+map.get("PWDB")+"\"}";
		System.out.println("output getUserInfo: " + outJson);
		ISLogger.getLogger().error("output getUserInfo::=" + outJson);
		ISLogger.getLogger().error("User_Name:="+(map.get("UserName")+""));
		ISLogger.getLogger().error("AgentId:="+(map.get("AgentId")+""));
		return outJson;
	}

	public static String findClient(String inputJson, String alias) {
		System.out.println("input findClient: " + inputJson);
		ISLogger.getLogger().error("output findClient::=" + inputJson);
		
		ObjectMapper mapper = new ObjectMapper();
		String output = "";
		try {
			FindClientRequest findClient = mapper.readValue(inputJson, FindClientRequest.class);
			FindClientResponse response = KoronaPayDBHelper.findClient(findClient.getDocNumber(), findClient.getDocSeries(), findClient.getFullName(), alias);
			
			output = mapper.writeValueAsString(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("output findClient: " + output);
		ISLogger.getLogger().error("output findClient::=" + output);
		
		return output;
	}
	
	public static String handleOperation(String inputJson, String un, String pwd, String alias) {
		HandleOperationResponse responset = new HandleOperationResponse();
		System.out.println("input handleOperation: " + inputJson);
		ISLogger.getLogger().error("input handleOperation:=" + inputJson);
		ObjectMapper mapper = new ObjectMapper();
		String output = "";
		try {
			HandleOperationRequest handleOperation = mapper.readValue(inputJson, HandleOperationRequest.class);
			ISLogger.getLogger().error("handleOperation start:= " + handleOperation);
			System.out.println("handleOperation start:= " + handleOperation);
			
			HandleOperationResponse response = null;
			if (handleOperation.getOperation() == 0 || handleOperation.getOperation() == 1 || handleOperation.getOperation() == 2) {
				response = KoronaPayDBHelper.insertHandleOperation(handleOperation, un, pwd, alias);
			} else if (handleOperation.getOperation() == 3 ||  handleOperation.getOperation() == 4 ||  handleOperation.getOperation() == 5) {
				response = KoronaPayDBHelper.cancelHandleOperation(handleOperation, alias);
			} else if (handleOperation.getOperation() == 6) {
				response = KoronaPayDBHelper.changeHandleOperation(handleOperation, alias);
			}
			
			ISLogger.getLogger().error("service responce:=" + response);
			output = mapper.writeValueAsString(response);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("handleOperation exception:= " + e.getMessage() +" , message: "+com.is.utils.CheckNull.getPstr(e));
			responset.setAction("Ans_HandleOperation");
			responset.setRetCode(1);
			responset.setRetMsg("Ошибка в БД");
			
			responset.setRetExtMsg(e.getMessage()+" , message: "+com.is.utils.CheckNull.getPstr(e));
		}
		
		System.out.println("output handleOperation: " + output);
		ISLogger.getLogger().error("output handleOperation:=" + output);
		return output;
	}
	
	public static HandleOperationRequest getHadleOperationByUIN(String uin) {
		HandleOperationRequest handleOperation = KoronaPayDBHelper.getHadleOperationByUIN(uin);
		return handleOperation;
	}
	
	
	
	 public static boolean regConfirmation(Map<String, String> map, String alias) throws Exception {
	        System.out.println("start regConfirmation");
	        ISLogger.getLogger().error("KoronaPayService regConfirmation started");
	        boolean b = false;
	        URL url = new URL("http://localhost:8989/in_abs_adapter_ts/services/phttp-abs");
			//URL url = new URL("http://10.4.48.48:8989/in_abs_adapter_ts/services/phttp-abs");
	        ISLogger.getLogger().error("KoronaPayService regConfirmation url: http://10.4.48.48:8989/in_abs_adapter_ts/services/phttp-abs");
	        
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "text/xml");
	        String xml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ReqConfirmation><UIN>" + (String)map.get("uin") + "</UIN><Operation>" + (String)map.get("operation") + "</Operation><PayDocID>" + (String)map.get("nciDocId") + "</PayDocID><PayDocDate>" + (String)map.get("nciDocDate") + "</PayDocDate><ConfirmData><PayerName><LastName>" + (String)map.get("payerLastName") + "</LastName><FirstName>" + (String)map.get("payerFirstName") + "</FirstName><OtherName>" + (String)map.get("payerOtherName") + "</OtherName></PayerName><PayeeName><LastName>" + (String)map.get("payeeLastName") + "</LastName><FirstName>" + (String)map.get("payeeFirstName") + "</FirstName><OtherName>" + (String)map.get("payeeOtherName") + "</OtherName></PayeeName><Funds><Amount>" + (String)map.get("amount") + "</Amount><Exp>" + (String)map.get("exp") + "</Exp><Cur>" + (String)map.get("cur") + "</Cur></Funds></ConfirmData></ReqConfirmation></soap:Body></soap:Envelope>";

	        ISLogger.getLogger().error("KoronaPayService regConfirmation xml: " + xml);
	        con.setDoOutput(true);
	        
	        DataOutputStream dos = new DataOutputStream(con.getOutputStream());
	        System.out.println("con.getOutputStream():   " + dos);
	        
	        //dos.writeBytes(xml);
	        dos.write(xml.getBytes("UTF-8"));
	        dos.flush();
	        dos.close();
	        String responseMessage = con.getResponseMessage();
	        int responseCode = con.getResponseCode();
	        
	        System.out.println("message: " + responseMessage + "\ncode: " + responseCode);
	        ISLogger.getLogger().error("KoronaPayService regConfirmation respose: message: " + responseMessage + " code: " + responseCode);
	        if (responseCode == 200) {
	            BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            StringBuffer sb = new StringBuffer();

	            String inputLine;
	            while((inputLine = bf.readLine()) != null) {
	                sb.append(inputLine);
	            }

	            bf.close();
	            System.out.println(sb.toString());
	            ISLogger.getLogger().error("KoronaPayService regConfirmation respose: " + sb.toString());
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            InputSource is = new InputSource(new StringReader(sb.toString()));
	            Document doc = builder.parse(is);
	            NodeList list = doc.getElementsByTagName("Code");
	            System.out.println(list.item(0).getTextContent());
	            b = KoronaPayDBHelper.setStateHadleOperation((String)map.get("uin"), (String)map.get("absClientId"), alias);
	        }

	        return b;
	        
	    }
	
	
	public static boolean regConfirmation6(Map<String, String> map, String alias) throws Exception { //Операция на изменение получателя
		System.out.println("start recCorrection");
		ISLogger.getLogger().error("KoronaPayService recCorrection started");
		boolean b = false;
	
		
		String ip = KoronaPayDBHelper.getWsTranslatorIP();
		
		URL url = new URL("http://"+ip+":8989/in_abs_adapter_ts/services/phttp-abs");
		ISLogger.getLogger().error("KoronaPayService reсCorrection url: http://"+ip+":8989/in_abs_adapter_ts/services/phttp-abs");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "text/xml");
		
		String xml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ReqCorrection><UIN>"+map.get("uin")+"</UIN><UpdatePayeeInfo><FullName>"+map.get("fullName")+"</FullName><Phone>"+map.get("phone")+"</Phone></UpdatePayeeInfo></ReqCorrection></soap:Body></soap:Envelope>";      
		ISLogger.getLogger().error("KoronaPayService recCorrection xml: " + xml);
		con.setDoOutput(true);
        
        DataOutputStream dos = new DataOutputStream(con.getOutputStream());
        dos.writeBytes(xml);
        dos.flush();
        dos.close();
        String responseMessage = con.getResponseMessage();
        int responseCode = con.getResponseCode();
        
        System.out.println("message: " + responseMessage + "\ncode: " + responseCode);
        ISLogger.getLogger().error("KoronaPayService regConfirmation respose: message: " + responseMessage + " code: " + responseCode);
        
        if (responseCode == 200) {
        	BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer sb = new StringBuffer();
            
            while((inputLine = bf.readLine()) != null) {
            	sb.append(inputLine);
            }
            bf.close();
            
            System.out.println(sb.toString());
            ISLogger.getLogger().error("KoronaPayService regConfirmation respose: " + sb.toString());
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(sb.toString()));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("Code");
            System.out.println(list.item(0).getTextContent());
            b = KoronaPayDBHelper.setStateHadleOperation4(map.get("uin"), alias);
        }
        
		return b;
	}
	
}