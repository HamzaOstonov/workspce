package com.is.clientcrm.digid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ISLogger;
//import com.is.qr_online.send.SetClientPhone.AnswerSetClientPhone;
//import com.is.qr_online.send.SetClientPhone.Response;

import sun.net.www.protocol.http.HttpURLConnection;
import sun.nio.cs.StandardCharsets;

public class AnswerDigID {

	public static ResponseDigId getAnswerDigId() {

		int result = -1;
		String message = null;
		ResponseDigId res = null;

		try {

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			String url = "http://localhost:50000/api/Identification/DataFromForm";
			ISLogger.getLogger().error("url_ScanPas: " + url.toString());
			URL obj = new URL(url.toString());
			HttpURLConnection getConnection = (HttpURLConnection) obj.openConnection();
			getConnection.setRequestMethod("GET");
			getConnection.setRequestProperty("Content-Type", "application/json");
			// getConnection.setDoOutput(true);

			int responseCode = getConnection.getResponseCode();
			ISLogger.getLogger().info("Get Response Scan_ClientPas :  " + responseCode);
			ISLogger.getLogger().info("Get Response Message_Scan : " + getConnection.getResponseMessage());

			if (responseCode == HttpsURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(getConnection.getInputStream(),"UTF-8"));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(inputLine);
					
				}
				in.close();
				byte[] bytes = stringBuffer.toString().getBytes("UTF-8");
			//	String str = new String (bytes,Charset.forName("cp1251"));
				String str1 = new String (bytes);
				String str2 = URLDecoder.decode(stringBuffer.toString(), "utf-8");
				//String  value = new String (bytes,"ISO-8859-1");
				ISLogger.getLogger().info("Response_ScanPas_bytes_latin: " + str1);
			//	ISLogger.getLogger().info("Response_ScanPas_bytes_UTF8: " + str1);
			//	ISLogger.getLogger().info("Response_ScanPas_bytes1: " + bytes1.toString());
//				ISLogger.getLogger().info("Response_ScanDigId: " + stringBuffer.toString());
				System.out.println("str2: "+stringBuffer.toString());
			//	System.out.println("str: "+str);
			
				ResponseDigId input = objectMapper.readValue(stringBuffer.toString(), ResponseDigId.class);

				result = (input.getAnswere().getAnswereId() != null ? (input.getAnswere().getAnswereId()) : (-1));
				ISLogger.getLogger().info("Response_ScanPasClient:" + result);
				message = new String(input.getAnswere().getAnswereComment().getBytes(), "UTF-8");
				ISLogger.getLogger().info("Message_ScanPasClientMessage " + message);
				System.out.println(input.getAnswere().getAnswereId());
				System.out.println(message);
				res = input;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("oshibka_Scan_Passport: " + e.getMessage());

		}

		return res;

	}
}
