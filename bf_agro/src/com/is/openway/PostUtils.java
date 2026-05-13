package com.is.openway;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.is.ISLogger;

public class PostUtils {

	public String sendData(String p_url, String p_data) {
		// StringWriter writer = new StringWriter();
		String message = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);
		
			ISLogger.getLogger().error("url = " + p_url);
			// HttpsURLConnection connection = (HttpsURLConnection)
			// url.openConnection();
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			//ISLogger.getLogger().error("sendData 00 ");
			// connection.setHostnameVerifier(allHostsValid);
			connection.setRequestMethod("POST");
			//ISLogger.getLogger().error("sendData 01 ");
			// connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
			connection.setRequestProperty("Content-Type", "application/xml");
			//ISLogger.getLogger().error("sendData 02 ");
			// connection.connect();

			connection.setDoOutput(true);
			//ISLogger.getLogger().error("sendData 03 ");
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(p_data);
			wr.flush();
			//ISLogger.getLogger().error("sendData 04 ");
			responseCode = connection.getResponseCode();
			//ISLogger.getLogger().error("sendData 05 " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				//ISLogger.getLogger().error("sendData 06 ");
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
				//ISLogger.getLogger().info("Response: " + message);
			} else {
				//ISLogger.getLogger().error("sendData 07 ");
				message = connection.getResponseMessage() + ", code: "
						+ responseCode;
				//ISLogger.getLogger().error("sendData 08 "+message);
			}

			// IOUtils.copy(connection.getInputStream(), writer, "utf-8");
			//ISLogger.getLogger().error(
			//		"responce code " + responseCode + "  body " + message);
		} catch (Exception e) {
			ISLogger.getLogger().error("responseCode " + responseCode);
			ISLogger.getLogger().error("sendData 09 "+e.getMessage());
			ISLogger.getLogger().error("sendData 10 "+e.getCause());
			e.printStackTrace();
			
		}
		return message;// writer.toString();
	}

}
