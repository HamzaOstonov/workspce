package com.is.clientcrm.res_nibbd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.clientcrm.digid.ResponseDigId;
//import com.is.qr_online.SSL;
//import com.is.qr_online.send.SetClientPhone.AnswerSetClientPhone;
//import com.is.qr_online.send.SetClientPhone.SetClientPhone;

import sun.net.www.protocol.http.HttpURLConnection;

public class CRM_setPhysicalByPin {
	
	public static ResNibbd setPhysicalByPin(String bank_type,String branch,RequestSetPhysicalByPin request, String docnum){
		
		String result = null;
		String message = null;
		String code = null;
		ResNibbd answer = null;
		Result res = null;
		int index = 0;
		int end_index = 0;
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SSL.trustmanager();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			String url = ConnectionPool.getValue("NIBBD_PH_BANK_URL");
			String qrMeth = "setPhysicalByPin";
			
			StringBuffer sb = new StringBuffer();
			sb.append(url);
			sb.append(qrMeth);
			sb.append("/");
			sb.append(bank_type);
			sb.append("/");
			sb.append(branch);
			sb.append("/");
			sb.append(docnum);
			System.out.println("url " + sb.toString());
			ISLogger.getLogger().error("url_setPhysicalByPin: " + sb.toString());
			URL obj = new URL(sb.toString());
			
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
			ISLogger.getLogger().info("POST Response Code_setPhysicalByPin :  " + responseCode);
			ISLogger.getLogger().info("POST Response Message_setPhysicalByPin : " + postConnection.getResponseMessage());
			System.out.println("POST Response Code_setPhysicalByPin :  " + responseCode);
			System.out.println("POST Response Message_setPhysicalByPin : " + postConnection.getResponseMessage());

			if (responseCode == HttpsURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream(),"UTF-8"));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(inputLine);
				}
				in.close();
				ISLogger.getLogger().info("setPhysicalByPin_StringBuffer " + stringBuffer.toString());
			//	byte[] bytes = stringBuffer.toString().getBytes("UTF-8");
				if(index<stringBuffer.toString().length()){
			    index = stringBuffer.toString().indexOf("{");
			    end_index = stringBuffer.toString().length();
			    ISLogger.getLogger().info("setPhysicalByPin_StringBuffer " + stringBuffer.toString().substring(index, end_index));
				}
				ResNibbd input = objectMapper.readValue(stringBuffer.toString().substring(index, end_index), ResNibbd.class);
				ISLogger.getLogger().info("Message_setPhysicalByPin_input " + input.toString());

				result = (input.getResult().getCode() != null ? (input.getResult().getCode()) : ("-1"));
				ISLogger.getLogger().info("Response_setPhysicalByPin:" + result);
				message = new String(input.getResult().getMessage().getBytes(), "UTF-8");
				ISLogger.getLogger().info("Message_setPhysicalByPin " + message);
				
				answer = input;
				ISLogger.getLogger().info("Message_setPhysicalByPin_answer " + answer.toString());
			}
			else{
			message = postConnection.getResponseMessage() + ", code_setPhysicalByPin: " + responseCode;
			ISLogger.getLogger().info("Message_setPhysicalByPin_else " + message);
			}
		}
			 catch (Exception e) {
					message = e.toString();
					code = "-1";
					res = new Result(code, message);
					ISLogger.getLogger().info("Message_setPhysicalByPin_last " + message);
					e.printStackTrace();

				}	
		
		return answer;
		
	}

}
