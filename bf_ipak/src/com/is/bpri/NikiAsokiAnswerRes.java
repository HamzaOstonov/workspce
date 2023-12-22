package com.is.bpri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.qr_online.SSL;
import com.is.qr_online.send.Answer;
import com.is.qr_online.send.Response;

import sun.net.www.protocol.http.HttpURLConnection;

public class NikiAsokiAnswerRes {
	
	public static NikiAsokiAnswer1  getAnswerNeq(NikiAsokiReq request){
		String message = null;
		NikiAsokiAnswer1 answer = null;
		try{
			ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(Include.NON_NULL); 
			SSL.trustmanager();
			String url = ConnectionPool.getValue("NIKI_ASOKI_SEND_ADRESS");
			String qrMeth="send";
			StringBuffer sb=new StringBuffer();
			sb.append(url);
			sb.append(qrMeth);
			System.out.println("url " + sb.toString());
			ISLogger.getLogger().error("url_ASOKI_NIKI: "+sb.toString());
			URL obj = new URL(sb.toString());
			ISLogger.getLogger().error("request Object_ASOKI_NIKI: "+request.toString());
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(request.toString().getBytes());
			os.flush();
			os.close();
			System.out.println(request.toString());
			int responseCode = postConnection.getResponseCode();
			ISLogger.getLogger().info("POST Response ASOKI_NIKI :  " + responseCode);
			ISLogger.getLogger().info("POST Response ASOKI_NIKI_Message : " + postConnection.getResponseMessage());
			System.out.println("POST Response Code :  " + responseCode);
			System.out.println("POST Response Message : " + postConnection.getResponseMessage());
			if (responseCode == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(),"UTF-8"));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(inputLine);
				}
				in.close();
				 byte[] bytes=stringBuffer.toString().getBytes("UTF-8");
				 
				ISLogger.getLogger().info("Response_ASOKI_NIKI_bytes: " + bytes.toString());
				ISLogger.getLogger().info("Response_ASOKI_NIKI: " + stringBuffer.toString());
				System.out.println(stringBuffer.toString());
				answer = objectMapper.readValue(stringBuffer.toString(), NikiAsokiAnswer1.class);
				message = new String(answer.getResult().getMessage().getBytes(), "UTF-8");
				ISLogger.getLogger().info("Message_ASOKI_NIKI " + message+", Code_ASOKI_NIKI"+answer.getBureau().getResult().getCode()+", message "+answer.getBureau().getResult().getMessage());
			
	         //   answer = new Result_(input.getBureau().getResult().getMessage(),input.getBureau().getResult().getCode());  		
					
			}
			
					}
		catch(Exception e){
			
			message = e.toString();
		//	answer = new Response(code, null);
			e.printStackTrace();
		}
		
		return answer;
		
	}
	
	
	
	
	
	
	

}
