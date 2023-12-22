package com.is.qr_online.send;

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

import sun.misc.BASE64Encoder;
import sun.net.www.protocol.http.HttpURLConnection;

public class Request {

	public static Answer getQRCB(Body request,String docnum) {

		String message = null;
		Answer answer = null;

		try {
			ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(Include.NON_NULL); 
			SSL.trustmanager();
			//String url = "http://128.10.10.210:86/GetQRCB";
			String url = ConnectionPool.getValue("QR_ONLINE_URL");
			String qrMeth="RegQRCode";
			StringBuffer sb=new StringBuffer();
			sb.append(url);
			sb.append(qrMeth);
			System.out.println("url " + sb.toString());
			ISLogger.getLogger().error("url_RegQRCode: "+sb.toString());
			URL obj = new URL(sb.toString());
			BASE64Encoder encoder = new BASE64Encoder();
			String bm_sign=ConnectionPool.getValue("BM_SIGN");
			System.out.println("bm_sign :"+bm_sign);
			com.is.ISLogger.getLogger().error("request 2---sign"+bm_sign);
			CBRUTCryptAuthServerSoapProxy ServerSoapProxy = new CBRUTCryptAuthServerSoapProxy();  
			ServerSoapProxy.setEndpoint(bm_sign);
			//CBRUTCryptAuthServerSoapProxy serverSoapProxy = new CBRUTCryptAuthServerSoapProxy(ConnectionPool.getValue("BM_SIGN"));
		    ISLogger.getLogger().error("request 2---sign");
			String dsign=ServerSoapProxy.signStringAS(encoder.encode(request.toString().getBytes()), docnum);
			//ISLogger.getLogger().error("request Object_REGQRClient: "+request.toString());
			request.setDsign(dsign);
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
			ISLogger.getLogger().info("POST Response RegQRCode :  " + responseCode);
			ISLogger.getLogger().info("POST Response RegQRCode_Message : " + postConnection.getResponseMessage());
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
				 
				 ISLogger.getLogger().info("Response_RegQRCode_bytes: " + bytes.toString());
				ISLogger.getLogger().info("Response_RegQRCode: " + stringBuffer.toString());
				System.out.println(stringBuffer.toString());
				Response input = objectMapper.readValue(stringBuffer.toString(), Response.class);

				message = new String(input.getResult().getMessage().getBytes(), "UTF-8");
				ISLogger.getLogger().info("Message_RegQRCode " + message+", Code_ReqQRCode"+input.getResult().getCode());
				System.out.println(input.getResult().getCode());
				System.out.println(message);
				System.out.println(input.getResponse().getQr());

				answer = new Answer(message, input.getResult().getCode(), input.getResponse().getQr(),input.getResponse().getQrId(),input.getResponse().getDescr());

			} else {
				message = postConnection.getResponseMessage() + ", code: " + responseCode;
				answer = new Answer(message, null, null,null,null);
			}

		} catch (Exception e) {
			message = e.toString();
			answer = new Answer(message, null, null,null,null);
			e.printStackTrace();
		}

		return answer;
	}
}
