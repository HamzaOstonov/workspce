package com.is.client_mass_openingCheckResident;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import org.apache.commons.codec.binary.Base64;

public class RequestClient {
	
	
 private ObjectMapper mapper = new ObjectMapper();

	public static Result newCustomerAndAgreement(int client_mass_opening_residentId) {
	    Result result = null;
	    Card card = new Card();
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	      CloseableHttpClient httpClient = HttpClients.createDefault();
	      HttpPost httpPost = new HttpPost("http://128.10.10.105:9090/humo-cards/api/v2/cards/open-card");
	      
	      Client_mass_opening_resident clientResident = Client_mass_opening_residentService.getClient_mass_opening_resident(client_mass_opening_residentId);
	      System.out.println("clientResident RequestClient 46 "+ clientResident);
	      
	      byte[] encoding = Base64.encodeBase64(("HUMOCARDS" + ":" + "qwertyH2013!*").getBytes());
	      
          NewCardRequest cardRequest = new NewCardRequest("00394", "05099649",clientResident.getCard_type(),"mobile",null, clientResident.getCode_organization());
	      
	      System.out.println("RequestClient 52  || " + cardRequest);
	      
	      httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	      httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + new String(encoding));
	      httpPost.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-EN");

	      StringEntity stringEntity = new StringEntity(mapper.writeValueAsString(cardRequest));

	      httpPost.setEntity(stringEntity);
	      CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

	      System.out.println("Post Response Status:: " + httpResponse.getStatusLine().getStatusCode());

	      BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

	      String inputLine;
	      StringBuffer response = new StringBuffer();

	      while ((inputLine = reader.readLine()) != null) {
	        response.append(inputLine);
	      }
	      reader.close();

	      result = mapper.readValue(response.toString(), Result.class);

	      if (result.getResult() != null) {
	        Map<String, String> params = new HashMap<String, String>();
	        params = (Map<String, String>) result.getResult();

	        card = mapper.readValue(mapper.writeValueAsString(params), Card.class);
	      }

	      result.setResult(card);
	      
	      System.out.println(response.toString());
	      httpClient.close();

	      return result;
	    } catch (Exception e) {
	      e.printStackTrace();
	      ISLogger.getLogger().error(e.getMessage(), e);
	      return result;
	    } finally {
	    }
	    
	}
	    
	    public static Result newClientAndMassAgreement(Client_mass_opening_resident client_mass_opening_resident) {
			
		    Result result = null;
		    Card card = new Card();
		    ObjectMapper mapper = new ObjectMapper();
		    try {
		      CloseableHttpClient httpClient = HttpClients.createDefault();
		      HttpPost httpPost = new HttpPost("http://128.10.10.105:9090/humo-cards/api/v2/cards/open-card");

		      byte[] encoding = Base64.encodeBase64(("HUMOCARDS" + ":" + "qwertyH2013!*").getBytes());
		      
		      NewCardRequest cardRequest = new NewCardRequest("00394", client_mass_opening_resident.getId(),client_mass_opening_resident.getCard_type(),"mobile",null, client_mass_opening_resident.getCode_organization());
		      
		      System.out.println("RequestClient 52  || " + cardRequest);

		      httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		      httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + new String(encoding));
		      httpPost.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "ru-RU");

		      StringEntity stringEntity = new StringEntity(mapper.writeValueAsString(cardRequest));

		      httpPost.setEntity(stringEntity);
		      CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

		      System.out.println("Post Response Status:: " + httpResponse.getStatusLine().getStatusCode());

		      BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		      String inputLine;
		      StringBuffer response = new StringBuffer();

		      while ((inputLine = reader.readLine()) != null) {
		        response.append(inputLine);
		      }
		        reader.close();

		      result = mapper.readValue(response.toString(), Result.class);

		      if (result.getResult() != null) {
		        Map<String, String> params = new HashMap<String, String>();
		        params = (Map<String, String>) result.getResult();

		        card = mapper.readValue(mapper.writeValueAsString(params), Card.class);
		      }

		      result.setResult(card);
		      
		      //System.out.println("RequestClient 86 -->"+ response.toString());
		      httpClient.close();

		      return result;
		    } catch (Exception e) {
		      e.printStackTrace();
		      ISLogger.getLogger().error(e.getMessage(), e);
		      return result;
		    } finally {
		    }
		  }
	  
	
	
	
	
	

//	public static ClientHumo getRegClient(ClientHumo request) {
//		String result = null;
//		String message = null;
//		ClientHumo answer = null;
//
//		try {
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			
//
//			String url = ConnectionPool.getValue("ONLINE_URL");
//			String qrMeth = "RegClient";
//			StringBuffer sb = new StringBuffer();
//			sb.append(url);
//			sb.append(qrMeth);
//			System.out.println("url " + sb.toString());
//			ISLogger.getLogger().error("url_regClient: " + sb.toString());
//			URL obj = new URL(sb.toString());
//			
//			//BASE64Encoder encoder = new BASE64Encoder();
//			
//			String bm_sign = ConnectionPool.getValue("BM_SIGN");
//			System.out.println("bm_sign :" + bm_sign);
//			ISLogger.getLogger().error("request 2---sign: " + bm_sign);
//			
//			//CBRUTCryptAuthServerSoapProxy ServerSoapProxy = new CBRUTCryptAuthServerSoapProxy();
//			//ServerSoapProxy.setEndpoint(bm_sign);	
//			// CBRUTCryptAuthServerSoapProxy serverSoapProxy = new
//			// CBRUTCryptAuthServerSoapProxy(ConnectionPool.getValue("BM_SIGN"));
//			//String dsign = ServerSoapProxy.signStringAS(encoder.encode(request.toString().getBytes()), docnum);
//			
//			ISLogger.getLogger().error("request 2---sign");
//			
//			//request.setDsign(dsign);
//			
//			ISLogger.getLogger().error("request 2---Object_RegQRClient: " + request.toString());
//			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
//			postConnection.setRequestMethod("POST");
//			postConnection.setRequestProperty("Content-Type", "application/json");
//			postConnection.setDoOutput(true);
//			OutputStream os = postConnection.getOutputStream();
//			os.write(request.toString().getBytes());
//
//			os.flush();
//			os.close();
//
//			System.out.println(request.toString());
//			int responseCode = postConnection.getResponseCode();
//			ISLogger.getLogger().error("POST Response Code :  " + responseCode);
//			ISLogger.getLogger().error("POST Response Message : " + postConnection.getResponseMessage());
//			
//			System.out.println("POST Response Code :  " + responseCode);
//			System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//
//			if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
//				String inputLine;
//				StringBuffer stringBuffer = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) {
//					stringBuffer.append(inputLine);
//				}
//				in.close();
//
//				//byte[] bytes = stringBuffer.toString().getBytes("UTF-8");
//				
//				//ISLogger.getLogger().info("Response_RegQRClient_bytes: " + bytes.toString());
//				ISLogger.getLogger().error("Response_RegClient: " + stringBuffer.toString());
//				System.out.println(stringBuffer.toString());
//				ClientHumo input = objectMapper.readValue(stringBuffer.toString(), ClientHumo.class);
//
//				result = input.getResponse().getSubj_id();
//				ISLogger.getLogger().error("Response_ClIent:" + result);
//				
//				message = new String(input.getResult().getMessage().getBytes(), "UTF-8");
//				ISLogger.getLogger().error("Message_RegClient " + message);
//				System.out.println(input.getResult().getCode());
//				System.out.println(message);
//				answer = new ClientHumo(message, input.getResult().getCode());
//
//			} else {
//				message = postConnection.getResponseMessage() + ", code_RegClient: " + responseCode;
//				answer = new ClientHumo(message);
//			}
//
//		} catch (Exception e) {
//			message = e.toString();
//			answer = new ClientHumo(message, null);
//			e.printStackTrace();
//			ISLogger.getLogger().error("Oshibka_Response_ClIent:" + e.getMessage());
//
//		}
//		return answer;
//
//	}
	
}
