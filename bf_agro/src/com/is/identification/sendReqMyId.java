package com.is.identification;

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

import sun.net.www.protocol.http.HttpURLConnection;

public class sendReqMyId {
	public static AnswerMyId getData(MyId_RequestObj request) {

		String message = null;
		AnswerMyId answer = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(Include.NON_NULL); 
			SSL.trustmanager();
			
			String url = ConnectionPool.getValue("URL_MY_ID");
			ISLogger.getLogger().error("url_RegQRCode: "+url.toString());
			URL obj = new URL(url.toString());
				
			ISLogger.getLogger().error("request Object_REGQRClient: "+request.toString());
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
			ISLogger.getLogger().error("POST Response RegQRCode :  " + responseCode);
			ISLogger.getLogger().error("POST Response RegQRCode_Message : " + postConnection.getResponseMessage());
			System.out.println("POST Response Code :  " + responseCode);
			System.out.println("POST Response Message : " + postConnection.getResponseMessage());

			if (responseCode == HttpsURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(inputLine);
				}
				in.close();
				 byte[] bytes=stringBuffer.toString().getBytes("UTF-8");
				 
			   // ISLogger.getLogger().error("Response_RegQRCode_bytes: " + bytes.toString());
				ISLogger.getLogger().error("Response_MYID: " + stringBuffer.toString());
				System.out.println(stringBuffer.toString());
				AnswerMyId input = objectMapper.readValue(stringBuffer.toString(), AnswerMyId.class);

				message = new String(input.getResultNote().getBytes(), "UTF-8");
				ISLogger.getLogger().error("Message_MYID " + message+", RESCode_MYID"+input.getResultCode());
				System.out.println(input.getResultCode());
				System.out.println(message);
				

				answer = new AnswerMyId(input.getResponseId(), input.getComparisonValue(), input.getResultCode(),message,input.getProfile());

			} else {
				message = postConnection.getResponseMessage() + ", code: " + responseCode;
				answer = new AnswerMyId(null,null, responseCode, message,null);
			}

		} catch (Exception e) {
			message = e.toString();
			answer = new AnswerMyId(null, 0d, -1,message,null);
			e.printStackTrace();
			ISLogger.getLogger().error("Oshibka_MYID " +e.getMessage());
		}

		return answer;
	}
	
	
	
	
	

}
