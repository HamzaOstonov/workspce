package com.is.customer_.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.clients.ebp.models.Res;
import com.is.clients.ebp.models.legalentitydetails.LegalEntityDetails;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.service.model.DataByPinppRequest;
import com.is.customer_.service.model.DataByPinppRequestPhoto;
import com.is.customer_.service.model.DataRequest;
import com.is.customer_.service.model.DataRequestPhoto;
import com.is.customer_.service.model.FizAddressResponse;
import com.is.customer_.service.model.FizDocsRequest;
import com.is.customer_.service.model.FizDocsResponse;
import com.is.customer_.service.model.FizInfoDetailsRequest;
import com.is.customer_.service.model.FizInfoDetailsResponse;
import com.is.customer_.service.model.FizPhotoRequest;
import com.is.customer_.service.model.FizPhotoResponse;
import com.is.customer_.service.model.GetDataByPinppRequest;
import com.is.customer_.service.model.GetDataByPinppRequestPhoto;
import com.is.utils.CheckNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.Base64;
import org.apache.commons.codec.binary.Base64;
//import java.nio.charset.StandardCharsets;
//import java.nio.charset;


public class TheService {

	private static final Logger logger = ISLogger.getLogger();
	//private static final String LE_DETAILS = "legal_entity_details";
	//private static final String IP_DETAILS = "individual_details";
	private static Map<String, Object> map;

	private static SimpleDateFormat dateFormat;

	static {
		dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	public static FizInfoDetailsResponse fizInfoDetailsResponse(String branch, String pinfl, String passp_ser, String passp_num, String p_user_id) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		FizInfoDetailsResponse myObj;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		//details
		url = "http://10.10.12.85:51000/RESTAdapter/FizInfoDetails"; 
		FizInfoDetailsRequest infoReq = new FizInfoDetailsRequest();
		GetDataByPinppRequest getdatareq = new GetDataByPinppRequest();
		DataRequest datareq = new DataRequest();
		DataByPinppRequest dbpr = new DataByPinppRequest();
		dbpr.setPinpp(pinfl);
		dbpr.setDocument(passp_ser+passp_num);
		datareq.setDataByPinppRequest(dbpr);
		getdatareq.setData(datareq);
		infoReq.setGetDataByPinppRequest(getdatareq);
		String p_data= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoReq);
		
		/*String p_data="{ "+
        " \"pinpp\": \"" + pinfl+ "\"" +
        "};";*/
		
		String content = sendData(url, p_data, branch, p_user_id);
		//String contentTemp = "{     \"Result\": 1,     \"Data\": {\"personaldata\": {\"row\":    {        \"queryld\": \"string\",        \"pinpp\": \"31312781080071\",        \"document\": \"AB2429121\",        \"surnamelatin\": \"OSTONOV\",        \"namelatin\": \"HAMZA\",        \"patronymlatin\": \"G‘OFURJONOVICH\",        \"engsurname\": \"OSTONOV\",        \"engname\": \"KHAMZA\",        \"birth_date\": \"1978-12-13\",        \"birthplace\": \"QORAKO‘L TUMANI\",        \"birthplaceid\": \"\",        \"birthcountry\": \"УЗБЕКИСТАН\",        \"birthcountryid\": 860,        \"livestatus\": 1,        \"nationality\": \"УЗБЕК/УЗБЕЧКА\",        \"nationalityid\": \"01\",        \"citizenship\": \"УЗБЕКИСТАН\",        \"citizenshipid\": 860,        \"sex\": 1,        \"docgiveplace\": \"БУХАРСКИЙ ГОВД БУХАРСКОЙ ОБЛАСТИ\",        \"docgiveplaceid\": 6206,        \"docdatebegin\": \"2016-01-11\",        \"docdateend\": \"2026-01-10\"     }}}  }";
		
		myObj = objectMapper.readValue(content, FizInfoDetailsResponse.class);
		//myObj = objectMapper.readValue(contentTemp, FizInfoDetailsResponse.class);
		
		return myObj;
	}

	
	public static FizAddressResponse fizAddressResponse(String branch, String pinfl, String p_user_id) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		FizAddressResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		//adres
		//url = "http://10.10.12.85:51000/RESTAdapter/FizAddress";
		//url = "http://10.10.12.117:50000/RESTAdapter/FizAddress";
		url = CustomerUtils.getFizAddrUrl();
		String p_data="{ "+
        " \"pinpp\": \"" + pinfl+ "\"" +
        "};";
		String content = sendData(url, p_data, branch, p_user_id);
		//String contentTemp = "{   \"Data\":    {      \"PermanentRegistration\":       {         \"Cadastre\": \"10:07:02:01:02:5011:0001:030\",         \"Country\":          {            \"Id\": \"860\",            \"Value\": \"ЎЗБЕКИСТОН\",            \"IdValue\": \"(182) ЎЗБЕКИСТОН\"         },         \"Region\":          {            \"Id\": \"26\",            \"Value\": \"ТОШКЕНТ ШАХРИ\",            \"IdValue\": \"(10) ТОШКЕНТ ШАХРИ\"         },         \"District\":          {            \"Id\": \"200\",            \"Value\": \"ЮНУСОБОД ТУМАНИ\",            \"IdValue\": \"(1010) ЮНУСОБОД ТУМАНИ\"         },         \"Address\": \"г. Ташкент, Юнусабадский район, ул. Чингиз Айтматов, Янгитарнов МСГ, 1- Дом, 30- Квартира\",         \"RegistrationDate\": \"2021-06-11T00:00:00\"      },      \"TemproaryRegistrations\": []   },   \"AnswereId\": 1,   \"AnswereMessage\": \"Ok\",   \"AnswereComment\": \"\"}";
		if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, FizAddressResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"FizAddressResponse objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"FizAddressResponse objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("FizAddressResponse objectMapper.readValue error: "+ e.getMessage());
	        }
	    else
	    	ISLogger.getLogger().error(	"FizAddressResponse objectMapper.readValue. content is \"\" or null." );
		//myObj = objectMapper.readValue(contentTemp, FizAddressResponse.class);
		
		return myObj;
	}
	
	public static FizPhotoResponse fizPhotoResponse(String branch, String pinfl, String docdate, String p_user_id) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		FizPhotoResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		//adres
		//url = "http://10.10.12.85:51000/RESTAdapter/FizPhoto";
		url = "http://10.10.12.117:50000/RESTAdapter/FizPhoto";

		FizPhotoRequest infoReq = new FizPhotoRequest();
		GetDataByPinppRequestPhoto getdatareq = new GetDataByPinppRequestPhoto();
		DataRequestPhoto datareq = new DataRequestPhoto();
		DataByPinppRequestPhoto dbpr = new DataByPinppRequestPhoto();
		dbpr.setPinpp(pinfl);
		dbpr.setDoc_give_date(docdate);
		dbpr.setLangId(1);
		dbpr.setIs_consent_pers_data("Y");
		datareq.setDataByPinppRequest(dbpr);

		getdatareq.setData(datareq);
		infoReq.setGetDataByPinppRequest(getdatareq);
		String p_data= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(infoReq);

		String content = sendData(url, p_data, branch, p_user_id);
		/*StringBuffer strB = new StringBuffer();
		strB.append("{");
		strB.append("\"Result\": 1,");
		strB.append("\"Data\": {\"personaldata\": {\"row\":    {");
		strB.append("\"photo\": \"\",");
        strB.append("\"pinpp\": 42707860260075,");
        strB.append("\"doc_seria\": \"AD\",");
        strB.append("\"doc_number\": \"0899348\",");
        strB.append("\"surnamelatin\": \"NOSIROVA\",");
        strB.append("\"namelatin\": \"NARGIZA\",");
        strB.append("\"patronymlatin\": \"UTKURJANOVNA\",");
        strB.append("\"engsurname\": \"\",");
        strB.append("\"engname\": \"\",");
        strB.append("\"birthdate\": \"1986-07-27\",");
        strB.append("\"birthplace\": \"TOSHKENT\",");
        strB.append("\"birthplaceid\": \"\",");
        strB.append("\"birthcountry\": \"УЗБЕКИСТАН\",");
        strB.append("\"birthcountryid\": 860,");
        strB.append("\"livestatus\": 1,");
        strB.append("\"nationality\": \"УЗБЕК/УЗБЕЧКА\",");
        strB.append("\"nationalityid\": \"01\",");
        strB.append("\"citizenship\": \"УЗБЕКИСТАН\",");
        strB.append("\"citizenshipid\": 860,");
        strB.append("\"sex\": 2,");
        strB.append("\"docgiveplace\": \"ШАЙХАНТАУРСКИЙ РОВД ГОРОДА ТАШКЕНТА\",");
        strB.append("\"docgiveplaceid\": 26277,");
        strB.append("\"docdatebegin\": \"2021-12-10\",");
        strB.append("\"docdateend\": \"2031-12-09\"");		
		strB.append("}}}");
		strB.append("}");*/
		
		/*StringBuffer strB = new StringBuffer();
		strB.append("{");
		strB.append("\"Result\": 1,");
		strB.append("\"Data\": {\"personaldata\": {\"row\":    {");
		strB.append("\"photo\": \"\",");
		strB.append("\"pinpp\": \"31312781080071\",");
		strB.append("\"doc_seria\": \"AB\",");
		strB.append("\"surnamelatin\": \"OSTONOV\",");
		strB.append("\"namelatin\": \"HAMZA\",");
		strB.append("\"patronymlatin\": \"G‘OFURJONOVICH\",");
		strB.append("\"engsurname\": \"OSTONOV\",");
		strB.append("\"engname\": \"KHAMZA\",");
		strB.append("\"birthdate\": \"1978-12-13\",");
		strB.append("\"birthplace\": \"QORAKO‘L TUMANI\",");
		strB.append("\"birthplaceid\": \"\",");
		strB.append("\"birthcountry\": \"УЗБЕКИСТАН\",");
		strB.append("\"birthcountryid\": 182,");
		strB.append("\"livestatus\": 1,");
		strB.append("\"nationality\": \"УЗБЕК/УЗБЕЧКА\",");
		strB.append("\"nationalityid\": 44,");
		strB.append("\"citizenship\": \"УЗБЕКИСТАН\",");
		strB.append("\"citizenshipid\": 182,");
		strB.append("\"sex\": 1,");
		strB.append("\"docgiveplace\": \"БУХАРСКИЙ ГОВД БУХАРСКОЙ ОБЛАСТИ\",");
		strB.append("\"docgiveplaceid\": 27256,");
		strB.append("\"docdatebegin\": \"2016-01-11\",");
		strB.append("\"docdateend\": \"2026-01-10\"");
		strB.append("}}}");
		strB.append("}");*/
		
		
		//StringBuffer strB = new StringBuffer();
		//strB.append("{\"Result\":2,\"Data\":null}");

		if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, FizPhotoResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"FizPhotoResponse objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"FizPhotoResponse objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("FizPhotoResponse objectMapper.readValue error: "+ e.getMessage());
	        }
	    else
	    	ISLogger.getLogger().error(	"FizPhotoResponse objectMapper.readValue. content is \"\" or null." );
		//myObj = objectMapper.readValue(strB.toString(), FizPhotoResponse.class);
		return myObj;
	}
	
	public static FizDocsResponse fizDocsResponse(String branch, FizDocsRequest query, String p_user_id) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		FizDocsResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		//adres
		//url = "http://10.10.12.85:51000/RESTAdapter/FizPhoto";
		//url = "http://10.10.12.117:50000/RESTAdapter/FizDocs";
		//"FizDocs"
		url = CustomerUtils.getFizDocsUrl();

		/*FizPhotoRequest infoReq = new FizPhotoRequest();
		GetDataByPinppRequestPhoto getdatareq = new GetDataByPinppRequestPhoto();
		DataRequestPhoto datareq = new DataRequestPhoto();
		DataByPinppRequestPhoto dbpr = new DataByPinppRequestPhoto();
		dbpr.setPinpp(pinfl);
		dbpr.setDoc_give_date(docdate);
		dbpr.setLangId(1);
		dbpr.setIs_consent_pers_data("Y");
		datareq.setDataByPinppRequest(dbpr);

		getdatareq.setData(datareq);
		infoReq.setGetDataByPinppRequest(getdatareq);*/
		String p_data= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(query);

		String content = sendData(url, p_data, branch, p_user_id);
		/*StringBuffer strB = new StringBuffer();
		strB.append("{");                                                 
		strB.append("   \"result\": 1,                                                           ");                              
		strB.append("   \"data\":    {                                                           ");
		strB.append("      \"transaction_id\": 1,                                                ");
		strB.append("      \"current_pinpp\": \"31312781080071\",                                ");
		strB.append("      \"pinpps\": [\"31312781080071\"],                                     ");
		strB.append("      \"current_document\": \"AB2429121\",                                  ");
		strB.append("      \"documents\": [      {                                               ");
		strB.append("         \"document\": \"AB2429121\",                                       ");
		strB.append("         \"type\": \"IDMS_RECV_CITIZ_DOCUMENTS\",                           ");
		strB.append("         \"docgiveplace\": \"БУХАРСКИЙ ГОВД БУХАРСКОЙ ОБЛАСТИ\",            ");
		strB.append("         \"docgiveplaceid\": 6206,                                          ");
		strB.append("         \"datebegin\": \"2016-01-11\",                                     ");
		strB.append("         \"dateend\": \"2026-01-10\",                                       ");
		strB.append("         \"status\": 2                                                      ");
		strB.append("      }],                                                                   ");
		strB.append("      \"surnamelat\": \"OSTONOV\",                                          ");
		strB.append("      \"namelat\": \"HAMZA\",                                               ");
		strB.append("      \"patronymlat\": \"G‘OFURJONOVICH\",                                  ");
		strB.append("      \"surnamecyr\": \"ОСТОНОВ\",                                          ");
		strB.append("      \"namecyr\": \"?АМЗА\",                                               ");
		strB.append("      \"patronymcyr\": \"?ОФУРЖОНОВИЧ\",                                    ");
		strB.append("      \"engsurname\": \"OSTONOV\",                                          ");
		strB.append("      \"engname\": \"KHAMZA\",                                              ");
		strB.append("      \"birth_date\": \"1978-12-13\",                                       ");
		strB.append("      \"birthplace\": \"QORAKO‘L TUMANI\",                                  ");
		strB.append("      \"birthcountry\": \"УЗБЕКИСТАН\",                                     ");
		strB.append("      \"birthcountryid\": \"860\",                                              ");
		strB.append("      \"livestatus\": 1,                                                    ");
		strB.append("      \"nationality\": \"УЗБЕК/УЗБЕЧКА\",                                   ");
		strB.append("      \"nationalityid\": \"01\",                                            ");
		strB.append("      \"citizenship\": \"УЗБЕКИСТАН\",                                      ");
		strB.append("      \"citizenshipid\": \"860\",                                               ");
		strB.append("      \"sex\": 1,                                                           ");
		strB.append("      \"photo\": \"\"                                                       ");		
		strB.append("   },                                                                       ");
		strB.append("   \"comments\": \"\"                                                       ");
		strB.append("}                                                                           ");
		*/
		
		/*StringBuffer strB = new StringBuffer();
		strB = new StringBuffer();
		strB.append("{                                                                                  ");
		strB.append("   \"result\": 1,                                                                  ");
		strB.append("   \"data\":    {                                                                  ");
		strB.append("      \"transaction_id\": 1,                                                       ");
		strB.append("      \"current_pinpp\": \"51006015260057\",                                       ");
		strB.append("      \"pinpps\": [\"51006015260057\"],                                            ");
		strB.append("      \"current_document\": \"AB7160924\",                                         ");
		strB.append("      \"documents\":       [                                                       ");
		strB.append("                  {                                                                ");
		strB.append("            \"document\": \"FA0653758\",                                           ");
		strB.append("            \"type\": \"IDMS_RECV_IP_DOCUMENTS\",                                  ");
		strB.append("            \"docgiveplace\": \"БУХАРСКИЙ ГОВД БУХАРСКОЙ ОБЛАСТИ\",                ");
		strB.append("            \"docgiveplaceid\": 6206,                                              ");
		strB.append("            \"datebegin\": \"2019-09-19\",                                         ");
		strB.append("            \"dateend\": \"2029-09-18\",                                           ");
		strB.append("            \"status\": 2                                                          ");
		strB.append("         },                                                                        ");
		strB.append("                  {                                                                ");
		strB.append("            \"document\": \"II-BH0012913\",                                        ");
		strB.append("            \"type\": \"IDMS_RECV_MJ_BIRTH_CERTS\",                                ");
		strB.append("            \"docgiveplace\": \"ОТДЕЛ ЗАГС № 2 ГОРОДА БУХАРА\",                    ");
		strB.append("            \"docgiveplaceid\": 739002146,                                         ");
		strB.append("            \"datebegin\": \"2001-06-13\",                                         ");
		strB.append("            \"dateend\": \"\",                                                     ");
		strB.append("            \"status\": 2                                                          ");
		strB.append("         },                                                                        ");
		strB.append("                  {                                                                ");
		strB.append("            \"document\": \"AB7160924\",                                           ");
		strB.append("            \"type\": \"IDMS_RECV_CITIZ_DOCUMENTS\",                               ");
		strB.append("            \"docgiveplace\": \"БУХАРСКИЙ ГОВД БУХАРСКОЙ ОБЛАСТИ\",                ");
		strB.append("            \"docgiveplaceid\": 6206,                                              ");
		strB.append("            \"datebegin\": \"2017-07-08\",                                         ");
		strB.append("            \"dateend\": \"2027-07-07\",                                           ");
		strB.append("            \"status\": 2                                                          ");
		strB.append("         }                                                                         ");
		strB.append("      ],                                                                           ");
		strB.append("      \"surnamelat\": \"G‘AFURJONOV\",                                             ");
		strB.append("      \"namelat\": \"FIRDAVS\",                                                    ");
		strB.append("      \"patronymlat\": \"HAMZA-O‘G‘LI\",                                           ");
		strB.append("      \"surnamecyr\": \"?АФУРЖОНОВ\",                                              ");
		strB.append("      \"namecyr\": \"ФИРДАВС\",                                                    ");
		strB.append("      \"patronymcyr\": \"?АМЗА-Ў?ЛИ\",                                             ");
		strB.append("      \"engsurname\": \"GAFURJONOV\",                                              ");
		strB.append("      \"engname\": \"FIRDAVS\",                                                    ");
		strB.append("      \"birth_date\": \"2001-06-10\",                                              ");
		strB.append("      \"birthplace\": \"BUXORO SHAHRI\",                                           ");
		strB.append("      \"birthcountry\": \"УЗБЕКИСТАН\",                                            ");
		strB.append("      \"birthcountryid\": \"860\",                                                 ");
		strB.append("      \"livestatus\": 1,                                                           ");
		strB.append("      \"nationality\": \"УЗБЕК/УЗБЕЧКА\",                                          ");
		strB.append("      \"nationalityid\": \"01\",                                                   ");
		strB.append("      \"citizenship\": \"УЗБЕКИСТАН\",                                             ");
		strB.append("      \"citizenshipid\": \"860\",                                                  ");
		strB.append("      \"sex\": 1                                                                   ");
		strB.append("   },                                                                              ");
		strB.append("   \"comments\": \"\"                                                              ");
		strB.append("}                                                                                  ");
		*/
		
				
		//StringBuffer strB = new StringBuffer();
		//strB.append("{\"Result\":2,\"Data\":null}");

		if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, FizDocsResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"fizDocsResponse objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"fizDocsResponse objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("fizDocsResponse objectMapper.readValue error: "+ e.getMessage());
	        }
	    else
	    	ISLogger.getLogger().error(	"fizDocsResponse objectMapper.readValue. content is \"\" or null." );
		//myObj = objectMapper.readValue(strB.toString(), FizDocsResponse.class);
		return myObj;
	}
	
	private static String run(String url, String pinfl) throws IOException {
		// 05-03-2018
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder = requestBuilder.setConnectTimeout(10 * 1000);
		requestBuilder = requestBuilder.setConnectionRequestTimeout(10 * 1000);
		requestBuilder = requestBuilder.setSocketTimeout(10 * 1000);

		HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestBuilder.build()).build();

		HttpGet request = new HttpGet(url);
		HttpResponse response;
		String result;

		response = client.execute(request);
		HttpEntity entity = response.getEntity();
		//result = EntityUtils.toString(entity);
		result = EntityUtils.toString(entity, "UTF-8");
		
		request.releaseConnection();
		return result;
	}
	
	public static String sendData(String p_url, String p_data, String p_branch, String p_user_id) {
		ISLogger.getLogger().error("url = " + p_url);		
		ISLogger.getLogger().error("sendData data! : "+p_data+"\nbranch : "+p_branch+"\nuser_id : "+p_user_id);
		String message = "";
		String message_err = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");

			String auth = "piuser" + ":" + "user_for_pi!1";
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(/*StandardCharsets.UTF_8*/"UTF-8"));
			String authHeaderValue = "Basic " + new String(encodedAuth);						
			connection.setRequestProperty("Authorization", authHeaderValue);
			
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(	connection.getOutputStream());
			wr.writeBytes(p_data);
			wr.flush();
			responseCode = connection.getResponseCode();
			
			BufferedReader br = null;
			if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
			    br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			} else {
			    br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			    message_err = connection.getResponseMessage() + ", code: "+ responseCode;
			}

			StringBuilder sb = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
			  sb.append(output);
			}
			message = sb.toString();
			if (!message_err.equals("") && message_err!="" ){
				message=message+". error result: "+message_err;
			}
			
			/*if (responseCode == HttpURLConnection.HTTP_OK) {
				ISLogger.getLogger().error("sendData 06 ");
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;
				ISLogger.getLogger().error("sendData 06.1. ");
				StringBuffer stringBuffer = new StringBuffer();
				ISLogger.getLogger().error("sendData 06.2. ");
				while ((inputLine = in.readLine()) != null) {
					byte[] myBytes=inputLine.getBytes();
					//stringBuffer.append(new String(inputLine.getBytes(), "UTF-8"));
					stringBuffer.append(new String(myBytes, "UTF-8"));
					ISLogger.getLogger().info("Resp1...: " + (new String(myBytes, "UTF-8")));
					ISLogger.getLogger().info("Resp2...: " + (new String(myBytes, "WINDOWS-1251")));
					ISLogger.getLogger().info("Resp3...: " + (new String(myBytes)));
				}
				ISLogger.getLogger().error("sendData 06.3. ");
				in.close();
				ISLogger.getLogger().info("Resp4"  );
				message = stringBuffer.toString();
				ISLogger.getLogger().info("Response: " + message);
			} else {
				ISLogger.getLogger().error("sendData 07 ");
				message = connection.getResponseMessage() + ", code: "
						+ responseCode;
				ISLogger.getLogger().error("sendData 08 "+message);
			}*/
			//IOUtils.copy(connection.getInputStream(), writer, "utf-8");
			
			ISLogger.getLogger().error(	"response code: " + responseCode + ". body: " + message);
		} catch (Exception e) {
			ISLogger.getLogger().error("url = " + p_url);		
			ISLogger.getLogger().error("sendData data : "+p_data+"\nbranch : "+p_branch+"\nuser_id : "+p_user_id);
			ISLogger.getLogger().error("responseCode: " + responseCode);
			ISLogger.getLogger().error("sendData err Message: "+e.getMessage());
			ISLogger.getLogger().error("sendData err Cause: "+e.getCause());
			e.printStackTrace();
		}
		return message;// writer.toString();
	}

}
