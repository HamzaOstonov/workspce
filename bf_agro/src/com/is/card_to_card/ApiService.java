package com.is.card_to_card;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.python.antlr.PythonParser.return_stmt_return;
import org.apache.commons.codec.binary.Base64;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import java.net.URLDecoder;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;
//import java.nio.charset.StandardCharsets;
//import java.nio.charset;
import com.is.ISLogger;
import com.is.utils.Res;
import com.is.card_to_card.Ton;

public class ApiService {
	public static String error;
	public static String url;
	public static Long result;
	public static Long natija;
	public static String block_card_result;
	public static String client_code;
	public static String original_response;

	public static Ton getApiStatus(String cardNumber) {
		String status;
		Ton ton = new Ton(0, "", "");
		try {
			String url = "https://fakerapi.it/api/v2/creditCards?_quantity=1";
			// String url =
			// "http://10.10.12.49:9090/humo-cards/api/v2/operations/get-balance/{" +
			// cardNumber + "}";

			URL apiUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				JSONObject jsonResponse = new JSONObject(response.toString());
				status = jsonResponse.getString("status");
				int code = jsonResponse.getInt("code");
				String locale = jsonResponse.getString("locale");
				Object seed = jsonResponse.opt("seed");
				int total = jsonResponse.getInt("total");

				ton.setCode(1);
				ton.setName("Status: " + status + ", Code: " + code + ", Locale: " + locale + ", Seed: " + seed
						+ ", Total: " + total);
			} else {
				throw new RuntimeException("GET request failed. Response Code: " + responseCode);
			}

			connection.disconnect();
		} catch (Exception e) {
			ton.setCode(-1L);
			ton.setName("BALANCE ERROR: " + e.getMessage());
		}
		return ton;
	}

	protected final static String getTokenForGetBalance() {
		return "Basic SFVNTy1BQlMtQ0FSRFM6S25XMyM0NmJASmU3";
	}
	
	protected final static String getTokenForBlockCard() {
		return "Basic SFVNTy1BQlMtQ0FSRFM6S25XMyM0NmJASmU3";
	}


	public static Ton getCardBalance(Card cardNumber, String alias) throws IOException {
//		Ton ton = new Ton(0, "");
		Ton ton = new Ton(0, "", "");
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			c = ConnectionPool.getConnection("HUMO-CARDS-API");
			ps = c.prepareStatement("select value from bf_sets where id = 'CTC_HUMO_CARD_BALANCE'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}

			HttpGet httpGet = new HttpGet(ConnectionPool.getValue("HUMO-CARDS-API") + "/humo-cards/api/v2/operations/get-balance/" + cardNumber.getCard_number());
//			HttpGet httpGet = new HttpGet(value + cardNumber.getCard_number());        
			System.out.println(value + cardNumber.getCard_number());
			httpGet.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "ru-RU");
			httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
			httpGet.addHeader(HttpHeaders.AUTHORIZATION, getTokenForGetBalance());
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			String inputLine = null;
			StringBuffer response = new StringBuffer();
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();
			JSONObject jsonResponse = null;
			try {
				jsonResponse = new JSONObject(response.toString());
			} catch (Exception e1) {
				ton.setCode(0);// было "-1"
//				ton.setName("(e1) :" + response.toString());
				ton.setName("BALANCE ERROR(e1) : " + response.toString() + " | " + e1.getMessage());
				return ton;
			}

			try {
				natija = jsonResponse.getLong("result");
			} catch (Exception e2) {
				ton.setCode(0);// было "-1"
//				ton.setName("(e2) :" + response.toString());
				ton.setName("BALANCE ERROR(e2) :" + response.toString() + " | " + e2.getMessage());
				return ton;
			}

			if (natija != 0) {
				result = natija;
			} else {
				result = 0L;
			}
			return new Ton(1, response.toString(), "success");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
			ton.setCode(0);// было "-1"
			ton.setName("BALANCE ERROR: " + e.getMessage());
		} finally {
			httpClient.close();
		}
		return ton;
	}

	public static Ton extractCardAcct(String jsonString, String ccy, String cond_set) {
	    Ton ton = new Ton();
	    if (jsonString == null) {
	        ton.setCode(0);
	        ton.setName("Noto‘g‘ri kiritish: JSON response null");
	        return ton;
	    }

	    if (jsonString.trim().isEmpty()) {
	        ton.setCode(0);
	        ton.setName("Noto‘g‘ri kiritish: JSON response bo‘sh");
	        return ton;
	    }
	    boolean ccyFound = false;
        boolean condSetMatched = false;
	    try {
	        JSONObject root = new JSONObject(jsonString);
	        if (!root.has("lt_holder") || !root.getJSONObject("lt_holder").has("value")) {
	            ton.setCode(0);
	            ton.setName("Kerakli maʼlumotlar topilmadi, ketma-ketlik biz kutganday emas");
	            return ton;
	        }

	        JSONArray valueArray = root.getJSONObject("lt_holder").getJSONArray("value");

	        for (int i = 0; i < valueArray.length(); i++) {
	            JSONArray innerArray = valueArray.optJSONArray(i);
	            if (innerArray == null)
	                continue;

	            String card_acct = null;
	            

	            for (int j = 0; j < innerArray.length(); j++) {
	                JSONObject item = innerArray.optJSONObject(j);
	                if (item != null) {
	                    if ("CCY".equals(item.optString("name", "")) && ccy.equals(item.optString("value", ""))) {
	                    	ccyFound = true;
	                    }
	                    if ("COND_SET".equals(item.optString("name", "")) && cond_set.equals(item.optString("value", ""))) {
	                        condSetMatched = true;
	                    }
	                    if ("CARD_ACCT".equals(item.optString("name", ""))) {
	                    	card_acct = item.optString("value", "CARD_ACCT qiymat topilmadi");
	                    }
	                }
	            }

	            if (ccyFound && condSetMatched && card_acct != null) {
	                ton.setCode(1);
	                ton.setName(card_acct);
	                return ton; // CCY va COND_SET shartlari bajarilganda ACCOUNT_NO ni qaytaramiz
	            }
	        }
	    } catch (JSONException e) {
	        ISLogger.getLogger().error("jsonString: " + jsonString);
	        ISLogger.getLogger().error("JSONException e.getMessage(): " + e.getMessage());
	        ISLogger.getLogger().error("JSONException e.getCause(): " + e.getCause());
	        ton.setCode(0);
	        ton.setName("Yaroqsiz JSON formati, struktura biz kutganday emas: " + e.getCause());
	        return ton;
	    }

	    ton.setCode(0);
	    if(ccyFound == false) {
	    	ton.setName(ton.getName() + "\nCCY yaroqli emas.");
	    }
	    if(condSetMatched == false) {
	    	ton.setName(ton.getName() + "\nCOND_SET yaroqli emas.");
	    }
	    return ton;
	}

	
	public static Ton extractClientCode(String jsonString) {
		Ton ton = new Ton();
		if (jsonString == null) {
			ton.setCode(0);
			ton.setName("Noto‘g‘ri kiritish: JSON response null");// yo'q bo'lsa => []
			return ton;
		}

		if (jsonString.trim().isEmpty()) {
			ton.setCode(0);
			ton.setName("Noto‘g‘ri kiritish: JSON response bo‘sh");// hech narsa bo'lmasa => ""
			return ton;
		}

		try {
			JSONObject root = new JSONObject(jsonString);
			if (!root.has("lt_holder") || !root.getJSONObject("lt_holder").has("value")) {
				ton.setCode(0);
				ton.setName("Kerakli maʼlumotlar topilmadi, ketma-ketlik biz kutganday emas");// ketma-ketlik biz
																								// kutganday emas
				return ton;
			}

			JSONArray valueArray = root.getJSONObject("lt_holder").getJSONArray("value");

			for (int i = 0; i < valueArray.length(); i++) {
				JSONArray innerArray = valueArray.optJSONArray(i);
				if (innerArray == null)
					continue;

				for (int j = 0; j < innerArray.length(); j++) {
					JSONObject item = innerArray.optJSONObject(j);
					if (item != null && "CLIENT".equals(item.optString("name", ""))) {
						ton.setCode(1);
						ton.setName(item.optString("value", "Value qiymat topilmadi"));
						if (!ton.getName().isEmpty()) {
							return ton; // CLIENT qiymatini topganda darhol qaytaramiz
						}
					}
				}
			}
		} catch (JSONException e) {
			ISLogger.getLogger().error("jsonString: " + jsonString);
			ISLogger.getLogger().error("JSONException e.getMessage(): " + e.getMessage());
			ISLogger.getLogger().error("JSONException e.getCause(): " + e.getCause());
			ton.setCode(0);
			ton.setName("Yaroqsiz JSON formati, struktura biz kutganday emas: " + e.getCause());// struktura biz
																								// kutganday emas
			return ton;
		}
		ton.setCode(0);
		ton.setName("CLIENT BO'SH");
		return ton; // CLIENT topilmasa, shu qaytariladi
	}

	public static String forData1(String client_code1) {
		String data = "{ \"client_B\" : \"" + client_code1 + "\", \"bank_C\": \"01\" }";
		return data;
	}

	public static String forData2(String client_card) {
		String data1 = "{ \"card\" : \"" + client_card + "\", \"bank_C\" : \"01\", \"groupc\" : \"02\"}";
		return data1;
	}

	public static String forData3(String client_code2) {
		String data2 = "{ \"bank_C\": \"01\", \"client\": \"" + client_code2 + "\" }";
		return data2;
	}

	public static String forData4(String client_code3) {
		String data3 = "{ \"client\": \"" + client_code3 + "\" }";
		return data3;
	}

	public static Ton sendData(String p_url, String p_data) {
		ISLogger.getLogger().error("url = " + p_url);
		ISLogger.getLogger().error("sendData data! : " + p_data);
		Ton ton = new Ton();
//        String message = "";
		String message_err = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

			String auth = "piuser" + ":" + "user_for_pi!1";
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(/* StandardCharsets.UTF_8 */"UTF-8"));
			String authHeaderValue = "Basic " + new String(encodedAuth);
			connection.setRequestProperty("Authorization", authHeaderValue);

			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(p_data);
			wr.flush();
			responseCode = connection.getResponseCode();

			BufferedReader br = null;
			if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			} else {
				br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				message_err = connection.getResponseMessage() + ", code: " + responseCode;
			}

			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			} // message = sb.toString();

			ton.setCode(1);
			ton.setName("\turl = " + p_url + "\nsent data: " + p_data + ", \nreceived data: " + sb.toString()); // ton.setName("nimadirlar
																												// keldi
																												// deylik");
			ton.setNode(sb.toString());// original_response keldi deylik
			System.out.println("ton.getNode: " + ton.getNode());
			ISLogger.getLogger().error("\noriginal_response = " + sb.toString());
			ISLogger.getLogger().error("\noriginal_response length = " + sb.toString().length());
			original_response = sb.toString();
			if (!message_err.equals("") && message_err != "") {// message=message+". error result: "+message_err;
				ton.setCode(0);
				ton.setName(ton.getName() + ". error result: " + message_err);// qanaqadir xatolik
			}

			/*
			 * if (responseCode == HttpURLConnection.HTTP_OK) {
			 * ISLogger.getLogger().error("sendData 06 "); BufferedReader in = new
			 * BufferedReader(new InputStreamReader( connection.getInputStream())); String
			 * inputLine; ISLogger.getLogger().error("sendData 06.1. "); StringBuffer
			 * stringBuffer = new StringBuffer();
			 * ISLogger.getLogger().error("sendData 06.2. "); while ((inputLine =
			 * in.readLine()) != null) { byte[] myBytes=inputLine.getBytes();
			 * //stringBuffer.append(new String(inputLine.getBytes(), "UTF-8"));
			 * stringBuffer.append(new String(myBytes, "UTF-8"));
			 * ISLogger.getLogger().info("Resp1...: " + (new String(myBytes, "UTF-8")));
			 * ISLogger.getLogger().info("Resp2...: " + (new String(myBytes,
			 * "WINDOWS-1251"))); ISLogger.getLogger().info("Resp3...: " + (new
			 * String(myBytes))); } ISLogger.getLogger().error("sendData 06.3. ");
			 * in.close(); ISLogger.getLogger().info("Resp4" ); message =
			 * stringBuffer.toString(); ISLogger.getLogger().info("Response: " + message); }
			 * else { ISLogger.getLogger().error("sendData 07 "); message =
			 * connection.getResponseMessage() + ", code: " + responseCode;
			 * ISLogger.getLogger().error("sendData 08 "+message); }
			 */
			// IOUtils.copy(connection.getInputStream(), writer, "utf-8");

			// ISLogger.getLogger().error( "response code: " + responseCode + ". body: " +
			// message);
		} catch (Exception e) {
			ton.setCode(0);
			ton.setName("url = " + p_url + "; \nsendData data : " + p_data + "; \nresponseCode: " + responseCode
					+ "; \nsendData err Message: " + e.getMessage() + "; \nsendData err Cause: " + e.getCause());
			ton.setNode("Xatolik va bu xatolik Message/Cause orqali e'lon qilindi");// add
			ISLogger.getLogger().error("\nurl = " + p_url);
			ISLogger.getLogger().error("\nsendData data : " + p_data);
			ISLogger.getLogger().error("\nresponseCode: " + responseCode);
			ISLogger.getLogger().error("\nsendData err Message: " + e.getMessage());
			ISLogger.getLogger().error("\nsendData err Cause: " + e.getCause());
			e.printStackTrace();
		}
		return ton;// writer.toString();
	}

//	public static Ton getVisaTransactionApiStatus(Card card1) {
//		String status;
//		Ton ton = new Ton(0, "");
//		try {
////			String url = "http://localhost:8081/VisaTiGw/GetRealCard/";
//			String url = "http://localhost:8081/VisaTiGw/GetRealCard/{" + card1.getCard_number() + "}";
//
//			URL apiUrl = new URL(url);
//			HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
//			connection.setRequestMethod("GET");
//			connection.setRequestProperty("Content-Type", "application/json");
//
//			int responseCode = connection.getResponseCode();
//			if (responseCode == HttpURLConnection.HTTP_OK) {
//				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				StringBuilder response = new StringBuilder();
//				String inputLine;
//				while ((inputLine = in.readLine()) != null) {
//					response.append(inputLine);
//				}
//				in.close();
//
//				JSONObject jsonResponse = new JSONObject(response.toString());
//				status = jsonResponse.getString("status");
//				int code = jsonResponse.getInt("code");
//				String locale = jsonResponse.getString("locale");
//				Object seed = jsonResponse.opt("seed");
//				int total = jsonResponse.getInt("total");
//
//				ton.setCode(1);
//				ton.setName("Status: " + status + ", Code: " + code + ", Locale: " + locale + ", Seed: " + seed
//						+ ", Total: " + total);
//			} else {
//				throw new RuntimeException("GET request failed. Response Code: " + responseCode);
//			}
//
//			connection.disconnect();
//		} catch (Exception e) {
//			ton.setCode(-1);
//			ton.setName("BALANCE ERROR: " + e.getMessage());
//		}
//		return ton;
//	}

	public static String getListCustomers(String alias) throws IOException {
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from bf_sets where id = 'CTC_LIST_CUSTOMERS'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
		}
		return value;
	}

	public static String getListAccountsByCard(String alias) throws IOException {
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from bf_sets where id = 'CTC_LIST_ACCOUNTS_BY_CARD'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
		}
		return value;
	}

	public static String getListCustomerCards(String alias) throws IOException {
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from bf_sets where id = 'CTC_LIST_CUSTOMER_CARDS'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
		}
		return value;
	}

	public static String getListAccounts(String alias) throws IOException {
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from bf_sets where id = 'CTC_LIST_ACCOUNTS'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
		}
		return value;
	}
	
	public static String getExecuteTransactions(String alias) throws IOException {
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from bf_sets where id = 'CTC_EXECUTE_TRANSACTION'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
		}
		return value;
	}
	
	public static long result() {
		if (natija != null) {
			return natija;
		} else {
			return 0;
		}

	}

	public static String forData5(String card_acct, String amount, String time, String msg) {
		String data = "{\n"
		        + "    \"bank_C\": \"01\",\n"
		        + "    \"groupc\": \"02\",\n"
		        + "    \"card_ACCT\": \"" + card_acct + "\",\n"
		        + "    \"payment_MODE\": \"3\",\n"
		        + "    \"tran_CCY\": \"UZS\",\n"
		        + "    \"tran_TYPE\": \"513\",\n"
		        + "    \"card_ACCT_CCY\": \"UZS\",\n"
		        + "    \"tran_AMNT\": \"" + amount + "\",\n"
		        + "    \"tran_DATE_TIME\": \"" + time + "\",\n"
		        + "    \"booking_MSG\": \"" + msg + "\",\n"
		        + "    \"deal_DESC\": \"Perevod sredstv iz HUMO v VIZA-SUM\"\n"
		        + "}";
		

	    return data;
	}

	public static String BlockHumoCard(String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		String value = "";
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select value from bf_sets where id like 'HUMO-CARDS-API'");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				value = rs.getString("value");
			}
			value += "/humo-cards/api/v2/operations/block-card";
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage(), e);
		} finally {
		}
		return value;
	}

	public static String forData6(String cardnumber) {
		String data = "{\"cardNumber\": \"" + cardnumber + "\"}";
	    return data;
	}

}
