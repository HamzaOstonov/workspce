package com.is.openwayj;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.is.utils.NilProvider;
import com.is.utils.Res;
import com.is.ISLogger;

public class PostUtils {
	private static NilProvider np = null;
	public Res sendData(String p_url, String p_data) {
		// StringWriter writer = new StringWriter();
		// 2025.02.02 sertifikatga ruxsat berish. manimcha bu kod yordam bermadi. keyin apexbank uzini url sini https dan http ga uzgartirdi.
		p_data = p_data.replaceAll("‘", "\'");
		ISLogger.getLogger().error("sendData, url = " + p_url+"\ndata = " + p_data);		
		//if (np == null) {
		//	np = new NilProvider();
		//	np.init();
		//}
		String message = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");

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
				message = "code: "+ responseCode + ", message: "
						+ connection.getResponseMessage();
			}

		} catch (Exception e) {
			ISLogger.getLogger().error("responseCode " + responseCode);
			ISLogger.getLogger().error("sendData 09 "+e.getMessage());
			ISLogger.getLogger().error("sendData 10 "+e.getCause());
			e.printStackTrace();
		}
		return new Res(responseCode, message);// writer.toString();
	}

}
