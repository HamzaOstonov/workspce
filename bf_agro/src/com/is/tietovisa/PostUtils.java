package com.is.tietovisa;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;

import com.is.ISLogger;

public class PostUtils {

	public String sendData(String p_url, String p_data) {

		ISLogger.getLogger().error("url = " + p_url);
		
		String message = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);
		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");

			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(p_data);
			wr.flush();

			responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(new String(inputLine.getBytes(),
							"utf-8"));
				}
				in.close();
				message = stringBuffer.toString();

			} else {
				message = connection.getResponseMessage() + ", code: " 		+ responseCode;
			}

		} catch (Exception e) {
			ISLogger.getLogger().error("responseCode " + responseCode);
			ISLogger.getLogger().error("sendData 09 "+e.getMessage());
			ISLogger.getLogger().error("sendData 10 "+e.getCause());
			e.printStackTrace();
		}
		return message;// writer.toString();
	}

	public String sendDataAuth(String p_url, String login, String password, String p_data) {

		ISLogger.getLogger().error("url(auth) = " + p_url);
		
		String message = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);
		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/xml");
			
			String auth = login + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(/*StandardCharsets.UTF_8*/"UTF-8"));
			String authHeaderValue = "Basic " + new String(encodedAuth);						
			connection.setRequestProperty("Authorization", authHeaderValue);

			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(p_data);
			wr.flush();

			responseCode = connection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(new String(inputLine.getBytes(),
							"utf-8"));
				}
				in.close();
				message = stringBuffer.toString();

			} else {
				message = connection.getResponseMessage() + ", code: " 		+ responseCode;
			}

		} catch (Exception e) {
			ISLogger.getLogger().error("responseCode(auth) " + responseCode);
			ISLogger.getLogger().error("sendDataAuth 09 "+e.getMessage());
			ISLogger.getLogger().error("sendDataAuth 10 "+e.getCause());
			e.printStackTrace();
		}
		return message;// writer.toString();
	}
}
