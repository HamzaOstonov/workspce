package com.is.clients.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.clients.ebp.models.Res;
import com.is.clients.models.AccountsResponse;
import com.is.clients.models.LockedAccountsResponse;
import com.is.clients.models.SubjectByInnResponse;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.service.model.FizDocsRequest;
import com.is.customer_.service.model.FizDocsResponse;
import com.is.utils.CheckNull;

public class UtilityService {
	private String alias;
	private String branch;
	
	private static Logger logger = Logger.getLogger(UtilityService.class);
	
	public UtilityService(String alias) {
		this.alias = alias;
	}
	
	public static UtilityService getInstance(String alias) {
		return new UtilityService(alias);
	}
	
	public boolean isMfo(String mfo) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			if(alias == null) {
				c = ConnectionPool.getConnection();
			} else {
				c = ConnectionPool.getConnection(alias);
			}
			ps = c.prepareStatement("select count(*) from ss_dblink_branch where branch=?");
			ps.setString(1, mfo);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);;
		}
		
		return count > 0;
	}
	
	public int roleProtocol(String eventid, String addRole, String removeRole) {
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			ps = c.prepareStatement("insert into SAP_ROLES_PROTOCOL(branch, id" +
															", emp_id,emp_login" +
															", event_id,add_role" +
															", remove_role) " +
												"values(info.getBranch, seq_sap_prot.nextval" +
												", info.getEmpId(), info.getUserName(info.getBranch,info.getEmpId())" +
												",?,?,?)");
			ps.setString(1, eventid);
			ps.setString(2, addRole);
			ps.setString(3, removeRole);
			
			count = ps.executeUpdate();
			c.commit();
			
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
			DbUtils.closeStmt(ps);
			DbUtils.closeStmt(cs);
		}
		return count;
	}
	
	public static SubjectByInnResponse nibbdSubjectByInn(String branch, String p_inn) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SubjectByInnResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		url = DictionaryKeeper.getNibbdJurBankUrl();
		
		url = url+"getSubjectByInn/014/00444";
		
		String p_data="{ "+
		        " \"inn\": \"" + p_inn+ "\"" +
		        "};";
		
		String content = sendData(url, p_data);

		/*String contentTemp="{"+
"\"result\":{"+
"\"code\":\"02000\","+
"\"message\":\"Успешно\""+
"},"+
"\"header\":{"+
"\"query_id\":\"1826X3L250O0M16055NU\","+
"\"inquire\":\"07.09.2019 16:05:26\","+
"\"respond\":\"07.09.2019 16:05:27\""+
"},"+
"\"response\":{"+
"\"client\":\"00770170\","+
"\"client_type\":\"11\","+
"\"client_state\":\"1\","+
"\"account_state\":\"1\","+
"\"name\":\"ООО «Саидов и сыновья»\","+
"\"opened\":\"10.07.2020\","+
"\"closed\":\"\""+
"}"+
"}";*/
		
		if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, SubjectByInnResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"SubjectByInnResponse objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"SubjectByInnResponse objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("SubjectByInnResponse objectMapper.readValue error: "+ e.getMessage());
	       }
	    else
	    	ISLogger.getLogger().error(	"SubjectByInnResponse objectMapper.readValue. content is \"\" or null." );
		
		//myObj = objectMapper.readValue(contentTemp, SubjectByInnResponse.class);

		return myObj;
	}
	
	public static SubjectByInnResponse nibbdSubjectByPinfl(String branch, String p_pinfl) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SubjectByInnResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		url = DictionaryKeeper.getNibbdJurBankUrl();
		
		url = url+"getSubjectByPin/014/00444";
		
		String p_data="{ "+
		        " \"pin\": \"" + p_pinfl+ "\"" +
		        "};";
		
		String content = sendData(url, p_data);

		/*String contentTemp="{"+
"\"result\":{"+
"\"code\":\"02000\","+
"\"message\":\"Успешно\""+
"},"+
"\"header\":{"+
"\"query_id\":\"1826X3L250O0M16055NU\","+
"\"inquire\":\"07.09.2019 16:05:26\","+
"\"respond\":\"07.09.2019 16:05:27\""+
"},"+
"\"response\":{"+
"\"client\":\"00770170\","+
"\"client_type\":\"11\","+
"\"client_state\":\"1\","+
"\"account_state\":\"1\","+
"\"name\":\"ООО «Саидов и сыновья»\","+
"\"opened\":\"10.07.2020\","+
"\"closed\":\"\""+
"}"+
"}";*/
		
		if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, SubjectByInnResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"SubjectByInnResponse(Pin) objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"SubjectByInnResponse(Pin) objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("SubjectByInnResponse(Pin) objectMapper.readValue error: "+ e.getMessage());
	       }
	    else
	    	ISLogger.getLogger().error(	"SubjectByInnResponse(Pin) objectMapper.readValue. content is \"\" or null." );
		
		//myObj = objectMapper.readValue(contentTemp, SubjectByInnResponse.class);

		return myObj;
	}
	
	public static AccountsResponse nibbdAccounts(String branch, String p_client, String p_coa) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		AccountsResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		url = DictionaryKeeper.getNibbdJurBankUrl();
		
		url = url+"getAccounts/014/00444";
		
		String p_data="{ "+
		        " \"client\": \"" + p_client+ "\"," +
		        " \"coa\": \"" + p_coa+ "\"" +
		        "};";
		
		//String content = sendData(url, p_data);
		/*String contentTemp="{                                                "+
				"  \"result\": {                                  "+
				"    \"code\": \"02000\",                         "+
				"    \"message\": \"Успешно\"                     "+
				"  },                                             "+
				"  \"header\": {                                  "+
				"    \"query_id\": \"1826X3L250O0M16055NU\",      "+
				"    \"inquire\": \"07.09.2020 16:05:26\",        "+
				"    \"respond\": \"07.09.2035 16:05:27\"         "+
				"  },                                             "+
				"  \"response\": {                                "+
				"    \"client\": \"00770170\",                    "+
				"    \"main\": {                                  "+
				"      \"bank\": \"002\",                         "+
				"      \"branch\": \"00074\",                     "+
				"      \"account\": \"20218000900770170001\",     "+
				"      \"account_state\": \"1\",                  "+
				"      \"opened\": \"01.01.2013\"                 "+
				"    },                                           "+
				"    \"accounts\": [                              "+
				"      {                                          "+
				"        \"bank\": \"002\",                       "+
				"        \"branch\": \"00074\",                   "+
				"        \"office\": \"00074\",                   "+
				"        \"account\": \"20218000100770170002\",   "+
				"        \"account_state\": \"1\",                "+
				"        \"opened\": \"01.01.2013\"               "+
				"      }                                          "+
				"    ]                                            "+
				"  }                                              "+
				"}                                                "
; */
		//String contentTemp="{\"result\":{\"code\":\"02000\",\"message\":\"Успешно\"},\"header\":{\"query_id\":\"2428HP6006ZBF1734DOT\",\"inquire\":\"06.01.2024 17:34:28\",\"respond\":\"06.01.2024 17:34:31\"},\"response\":{\"client\":\"00101289\",\"main\":{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000400101289001\",\"account_state\":\"1\",\"opened\":\"23.02.2004\",\"lock_info\":[]},\"accounts\":[{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208840900101289002\",\"account_state\":\"1\",\"opened\":\"08.10.2003\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000500101289888\",\"account_state\":\"1\",\"opened\":\"24.02.2004\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614840200101289001\",\"account_state\":\"1\",\"opened\":\"15.09.2004\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614978000101289001\",\"account_state\":\"1\",\"opened\":\"19.07.2005\",\"lock_info\":[]},{\"bank\":\"004\",\"branch\":\"00394\",\"office\":\"00394\",\"account\":\"20208840600101289005\",\"account_state\":\"1\",\"opened\":\"09.12.2008\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000600101289001\",\"account_state\":\"1\",\"opened\":\"16.06.2011\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000800101289200\",\"account_state\":\"1\",\"opened\":\"28.09.2019\",\"lock_info\":[]}]}}";
		String contentTemp="{\"result\":{\"code\":\"02000\",\"message\":\"Успешно\"},\"header\":{\"query_id\":\"2428HP6006ZBF1734DOT\",\"inquire\":\"06.01.2024 17:34:28\",\"respond\":\"06.01.2024 17:34:31\"},\"response\":{\"client\":\"00101289\",\"main\":{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000400101289001\",\"account_state\":\"1\",\"opened\":\"23.02.2004\",\"lock_info\":[  {  \"type\": \"01\", \"sort\": \"\", \"doc_n\": \"2\", \"doc_d\": \"27.07.2023\", \"locked\": \"15.11.2023\"  },  { \"id\": \"23533190000157\", \"type\": \"03\",  \"doc_n\": \"1\", \"doc_d\": \"15.11.2023\", \"locked\": \"15.11.2023\"  } ]},\"accounts\":[{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208840900101289002\",\"account_state\":\"1\",\"opened\":\"08.10.2003\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000500101289888\",\"account_state\":\"1\",\"opened\":\"24.02.2004\",\"lock_info\":[  { \"id\": \"23363190000155\", \"type\": \"01\", \"sort\": \"\", \"doc_n\": \"2\", \"doc_d\": \"27.07.2023\", \"locked\": \"15.11.2023\"  },  { \"id\": \"23533190000157\", \"type\": \"03\", \"sort\": \"\", \"doc_n\": \"1\", \"doc_d\": \"15.11.2023\", \"locked\": \"15.11.2023\"  } ]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614840200101289001\",\"account_state\":\"1\",\"opened\":\"15.09.2004\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614978000101289001\",\"account_state\":\"1\",\"opened\":\"19.07.2005\",\"lock_info\":[]},{\"bank\":\"004\",\"branch\":\"00394\",\"office\":\"00394\",\"account\":\"20208840600101289005\",\"account_state\":\"1\",\"opened\":\"09.12.2008\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000600101289001\",\"account_state\":\"1\",\"opened\":\"16.06.2011\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000800101289200\",\"account_state\":\"1\",\"opened\":\"28.09.2019\",\"lock_info\":[  { \"id\": \"23363190000155\", \"type\": \"01\", \"sort\": \"\", \"doc_n\": \"2\", \"doc_d\": \"27.07.2023\", \"locked\": \"15.11.2023\"  },  { \"id\": \"23533190000157\", \"type\": \"03\", \"sort\": \"\", \"doc_n\": \"1\", \"doc_d\": \"15.11.2023\", \"locked\": \"15.11.2023\"  } ]}]}}";
		
		
		/*if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, AccountsResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"AccountsResponse objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"AccountsResponse objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("AccountsResponse objectMapper.readValue error: "+ e.getMessage());
	       }
	    else
	    	ISLogger.getLogger().error(	"AccountsResponse objectMapper.readValue. content is \"\" or null." );
		*/
		myObj = objectMapper.readValue(contentTemp, AccountsResponse.class);

		return myObj;
	}
	
	public static LockedAccountsResponse nibbdLockedAccounts(String branch, String p_client, String p_account) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		LockedAccountsResponse myObj=null;
		Res res = new Res();
		res.setCode(-1);
		String url = "";

		url = DictionaryKeeper.getNibbdJurBankUrl();
		
		url = url+"getLockAccounts/014/00444";
		
		String p_data="{ "+
		        " \"client\": \"" + p_client+ "\"," +
		        " \"account\": \"" + p_account+ "\"" +
		        "};";
		
		//String content = sendData(url, p_data);
		
		//String contentTemp="{\"result\":{\"code\":\"02000\",\"message\":\"Успешно\"},\"header\":{\"query_id\":\"2428HP6006ZBF1734DOT\",\"inquire\":\"06.01.2024 17:34:28\",\"respond\":\"06.01.2024 17:34:31\"},\"response\":{\"client\":\"00101289\",\"main\":{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000400101289001\",\"account_state\":\"1\",\"opened\":\"23.02.2004\",\"lock_info\":[]},\"accounts\":[{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208840900101289002\",\"account_state\":\"1\",\"opened\":\"08.10.2003\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000500101289888\",\"account_state\":\"1\",\"opened\":\"24.02.2004\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614840200101289001\",\"account_state\":\"1\",\"opened\":\"15.09.2004\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614978000101289001\",\"account_state\":\"1\",\"opened\":\"19.07.2005\",\"lock_info\":[]},{\"bank\":\"004\",\"branch\":\"00394\",\"office\":\"00394\",\"account\":\"20208840600101289005\",\"account_state\":\"1\",\"opened\":\"09.12.2008\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000600101289001\",\"account_state\":\"1\",\"opened\":\"16.06.2011\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000800101289200\",\"account_state\":\"1\",\"opened\":\"28.09.2019\",\"lock_info\":[]}]}}";
		String contentTemp="{\"result\":{\"code\":\"02000\",\"message\":\"Успешно\"},\"header\":{\"query_id\":\"2428HP6006ZBF1734DOT\",\"inquire\":\"06.01.2024 17:34:28\",\"respond\":\"06.01.2024 17:34:31\"},\"response\":{\"client\":\"00101289\",\"main\":{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000400101289001\",\"account_state\":\"1\",\"opened\":\"23.02.2004\",\"lock_info\":[  {  \"type\": \"01\", \"sort\": \"\", \"doc_n\": \"2\", \"doc_d\": \"27.07.2023\", \"locked\": \"15.11.2023\"  },  { \"id\": \"23533190000157\", \"type\": \"03\",  \"doc_n\": \"1\", \"doc_d\": \"15.11.2023\", \"locked\": \"15.11.2023\"  } ]},\"accounts\":[{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208840900101289002\",\"account_state\":\"1\",\"opened\":\"08.10.2003\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"20208000500101289888\",\"account_state\":\"1\",\"opened\":\"24.02.2004\",\"lock_info\":[  { \"id\": \"23363190000155\", \"type\": \"01\", \"sort\": \"\", \"doc_n\": \"2\", \"doc_d\": \"27.07.2023\", \"locked\": \"15.11.2023\"  },  { \"id\": \"23533190000157\", \"type\": \"03\", \"sort\": \"\", \"doc_n\": \"1\", \"doc_d\": \"15.11.2023\", \"locked\": \"15.11.2023\"  } ]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614840200101289001\",\"account_state\":\"1\",\"opened\":\"15.09.2004\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"22614978000101289001\",\"account_state\":\"1\",\"opened\":\"19.07.2005\",\"lock_info\":[]},{\"bank\":\"004\",\"branch\":\"00394\",\"office\":\"00394\",\"account\":\"20208840600101289005\",\"account_state\":\"1\",\"opened\":\"09.12.2008\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000600101289001\",\"account_state\":\"1\",\"opened\":\"16.06.2011\",\"lock_info\":[]},{\"bank\":\"014\",\"branch\":\"00444\",\"office\":\"00444\",\"account\":\"23106000800101289200\",\"account_state\":\"1\",\"opened\":\"28.09.2019\",\"lock_info\":[  { \"id\": \"23363190000155\", \"type\": \"01\", \"sort\": \"\", \"doc_n\": \"2\", \"doc_d\": \"27.07.2023\", \"locked\": \"15.11.2023\"  },  { \"id\": \"23533190000157\", \"type\": \"03\", \"sort\": \"\", \"doc_n\": \"1\", \"doc_d\": \"15.11.2023\", \"locked\": \"15.11.2023\"  } ]}]}}";
		
		
		/*if (!content.equals(""))
			try {
				myObj = objectMapper.readValue(content, AccountsResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(	"AccountsResponse objectMapper.readValue. content: " + content);
	    	    ISLogger.getLogger().error(	"AccountsResponse objectMapper.readValue error: " + e.getMessage());	    	  
	    	    throw new Exception("AccountsResponse objectMapper.readValue error: "+ e.getMessage());
	       }
	    else
	    	ISLogger.getLogger().error(	"AccountsResponse objectMapper.readValue. content is \"\" or null." );
		*/
		myObj = objectMapper.readValue(contentTemp, LockedAccountsResponse.class);

		return myObj;
	}
	
	public static String sendData(String p_url, String p_data) {
		ISLogger.getLogger().error("url(j) = " + p_url);		
		ISLogger.getLogger().error("sendData data(j) : "+p_data);
		String message = "";
		String message_err = "";
		int responseCode = 0;
		try {
			URL url = new URL(p_url);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");

			//String auth = "piuser" + ":" + "user_for_pi!1";
			//byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(/*StandardCharsets.UTF_8*/"UTF-8"));
			//String authHeaderValue = "Basic " + new String(encodedAuth);						
			//connection.setRequestProperty("Authorization", authHeaderValue);
			
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
			
			ISLogger.getLogger().error(	"response code(j): " + responseCode + ". body(j): " + message);
		} catch (Exception e) {
			ISLogger.getLogger().error("url(j) = " + p_url);		
			ISLogger.getLogger().error("sendData data(j) : "+p_data);
			ISLogger.getLogger().error("responseCode(j): " + responseCode);
			ISLogger.getLogger().error("sendData err Message(j): "+e.getMessage());
			e.printStackTrace();
		}
		return message;// writer.toString();
	}
	
}
