package com.is.qr_online.send.RegQRClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapProxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.qr_online.SSL;
//import com.is.qr_online.send.Answer;

//import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.net.www.protocol.http.HttpURLConnection;

public class RequestQRClient {

	public static AnswerRegQRClient getRegQRClient(RegQRClient request, String docnum) {
		String result = null;
		String message = null;
		AnswerRegQRClient answer = null;

		// String docnum="1";
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			SSL.trustmanager();

			String url = ConnectionPool.getValue("QR_ONLINE_URL");
			String qrMeth = "RegQRClient";
			StringBuffer sb = new StringBuffer();
			sb.append(url);
			sb.append(qrMeth);
			System.out.println("url " + sb.toString());
			ISLogger.getLogger().error("url_regQRClient: " + sb.toString());
			URL obj = new URL(sb.toString());
			BASE64Encoder encoder = new BASE64Encoder();
			String bm_sign = ConnectionPool.getValue("BM_SIGN");
			System.out.println("bm_sign :" + bm_sign);
			ISLogger.getLogger().error("request 2---sign: " + bm_sign);
			CBRUTCryptAuthServerSoapProxy ServerSoapProxy = new CBRUTCryptAuthServerSoapProxy();
			ServerSoapProxy.setEndpoint(bm_sign);
			// CBRUTCryptAuthServerSoapProxy serverSoapProxy = new
			// CBRUTCryptAuthServerSoapProxy(ConnectionPool.getValue("BM_SIGN"));
			String dsign = ServerSoapProxy.signStringAS(encoder.encode(request.toString().getBytes()), docnum);
			ISLogger.getLogger().error("request 2---sign");
			request.setDsign(dsign);
			ISLogger.getLogger().error("request 2---Object_RegQRClient: " + request.toString());
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
			ISLogger.getLogger().info("POST Response Code :  " + responseCode);
			ISLogger.getLogger().info("POST Response Message : " + postConnection.getResponseMessage());
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

				//byte[] bytes = stringBuffer.toString().getBytes("UTF-8");
				
				//ISLogger.getLogger().info("Response_RegQRClient_bytes: " + bytes.toString());
				ISLogger.getLogger().info("Response_RegQRClient: " + stringBuffer.toString());
				System.out.println(stringBuffer.toString());
				Response input = objectMapper.readValue(stringBuffer.toString(), Response.class);

				result = input.getResponse().getInn();
				ISLogger.getLogger().info("Response_QRClIent:" + result);
				message = new String(input.getResult().getMessage().getBytes(), "UTF-8");
				ISLogger.getLogger().info("Message_RegQRClient " + message);
				System.out.println(input.getResult().getCode());
				System.out.println(message);
				answer = new AnswerRegQRClient(message, input.getResult().getCode());

			} else {
				message = postConnection.getResponseMessage() + ", code_RegQRClient: " + responseCode;
				answer = new AnswerRegQRClient(message, null);
			}

		} catch (Exception e) {
			message = e.toString();
			answer = new AnswerRegQRClient(message, null);
			e.printStackTrace();

		}
		return answer;

	}
}
