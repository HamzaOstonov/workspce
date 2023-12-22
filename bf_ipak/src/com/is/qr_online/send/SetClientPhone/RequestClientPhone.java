package com.is.qr_online.send.SetClientPhone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapProxy;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.qr_online.SSL;
import com.is.qr_online.send.SetClientPhone.Response;

import sun.misc.BASE64Encoder;
import sun.net.www.protocol.http.HttpURLConnection;

public class RequestClientPhone {

	public static AnswerSetClientPhone getClientPhone(SetClientPhone request, String docnum) {
		String result = null;
		String message = null;
		AnswerSetClientPhone answer = null;
		// String dsign=null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SSL.trustmanager();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			String url = ConnectionPool.getValue("QR_ONLINE_URL");
			String qrMeth = "SetClientPhone";
			StringBuffer sb = new StringBuffer();
			sb.append(url);
			sb.append(qrMeth);
			System.out.println("url " + sb.toString());
			ISLogger.getLogger().error("url_SetClientPhone: " + sb.toString());
			URL obj = new URL(sb.toString());
			BASE64Encoder encoder = new BASE64Encoder();
			String bm_sign = ConnectionPool.getValue("BM_SIGN");
			System.out.println("bm_sign :" + bm_sign);
			com.is.ISLogger.getLogger().error("request 2---sign" + bm_sign);
			CBRUTCryptAuthServerSoapProxy ServerSoapProxy = new CBRUTCryptAuthServerSoapProxy();
			ServerSoapProxy.setEndpoint(bm_sign);
			// CBRUTCryptAuthServerSoapProxy serverSoapProxy = new
			// CBRUTCryptAuthServerSoapProxy(ConnectionPool.getValue("BM_SIGN"));
			com.is.ISLogger.getLogger().error("request 2---sign");

			String dsign = ServerSoapProxy.signStringAS(encoder.encode(request.toString().getBytes()), docnum);
			ISLogger.getLogger().error("request 2---Object_SetClienPhone: " + request.toString());
			request.setDsign(dsign);
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
			ISLogger.getLogger().info("POST Response Code_SETCLIENTPHONE :  " + responseCode);
			ISLogger.getLogger().info("POST Response Message_SETCLIENTPHONE : " + postConnection.getResponseMessage());
			System.out.println("POST Response Code_SETCLIENTPHONE :  " + responseCode);
			System.out.println("POST Response Message_SETCLIENTPHONE : " + postConnection.getResponseMessage());

			if (responseCode == HttpsURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer stringBuffer = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					stringBuffer.append(inputLine);
				}
				in.close();
				byte[] bytes = stringBuffer.toString().getBytes("UTF-8");

				ISLogger.getLogger().info("Response_SETCLIENTPHONE_bytes: " + bytes.toString());

				ISLogger.getLogger().info("Response_SETCLIENTPHONE: " + stringBuffer.toString());
				System.out.println(stringBuffer.toString());
				Response input = objectMapper.readValue(stringBuffer.toString(), Response.class);

				result = (input.getResponse().getPhone() != null ? (input.getResponse().getPhone()) : ("ошибка"));
				ISLogger.getLogger().info("Response_SETCLIENTPHONE:" + result);
				message = new String(input.getResult().getMessage().getBytes(), "UTF-8");
				ISLogger.getLogger().info("Message_SETCLIENTPHONE " + message);
				System.out.println(input.getResult().getCode());
				System.out.println(message);
				answer = new AnswerSetClientPhone(message, input.getResult().getCode());

			} else {
				message = postConnection.getResponseMessage() + ", code_RegQRClient: " + responseCode;
				answer = new AnswerSetClientPhone(message, null);
			}

		} catch (Exception e) {
			message = e.toString();
			answer = new AnswerSetClientPhone(message, null);
			e.printStackTrace();

		}

		return answer;
	}
}
