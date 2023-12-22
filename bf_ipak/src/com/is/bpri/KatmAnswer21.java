package com.is.bpri;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.qr_online.SSL;

import sun.net.www.protocol.http.HttpURLConnection;

public class KatmAnswer21 {
	
	public static String getKatm21(String branch,Long Claim_id){
		String res = "";
		try{
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL); 
		SSL.trustmanager();
		String url_katm = ConnectionPool.getValue("NIKI_ASOKI_SEND_ADRESS");
		String pLogin = "pLogin="+ConnectionPool.getValue("KATM_LOGIN");
		String pPass = "&pPassword="+ConnectionPool.getValue("KATM_PASSWORD");
		String pHeadBank = "&pHead="+ConnectionPool.getValue("KATM_HEAD_BANK");
		String p_legal = "1";
		String p_ReportId = "21";
		String p_report_format = "0";
		StringBuffer sb=new StringBuffer();
		sb.append(url_katm);
		sb.append(pLogin);
		sb.append(pPass);
		sb.append(pHeadBank);
		sb.append("&pCode="+branch);
		sb.append("&pLegal="+p_legal);
		sb.append("&pClaimId="+Claim_id);
		sb.append("&pReportId="+p_ReportId);
		sb.append("&pReportFormat="+p_report_format);
		System.out.println("url " + sb.toString());
		ISLogger.getLogger().error("url_ASOKI_NIKI: "+sb.toString());
		URL obj = new URL(sb.toString());
		HttpURLConnection getConnection = (HttpURLConnection) obj.openConnection();
		getConnection.setRequestMethod("GET");
		getConnection.setRequestProperty("Content-Type", "application/json");
		// getConnection.setDoOutput(true);

		int responseCode = getConnection.getResponseCode();
		ISLogger.getLogger().info("Get Response KATM_RESP_CODE :  " + responseCode);
		ISLogger.getLogger().info("Get Response KATM_RESP_MESSAGE : " + getConnection.getResponseMessage());
		if (responseCode == HttpsURLConnection.HTTP_OK) {
            res ="OK";		
		}
		else{
			res = getConnection.getResponseMessage(); }
		}
		catch(Exception e){
			res = e.toString();
			//	answer = new Response(code, null);
		    e.printStackTrace();
		}
		
		return res;
		
	}
	
	}
