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
	
	public static String sendData(String p_url, String p_data) {
		ISLogger.getLogger().error("url = " + p_url);		
		ISLogger.getLogger().error("sendData data! : "+p_data);
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
			
			ISLogger.getLogger().error(	"response code: " + responseCode + ". body: " + message);
		} catch (Exception e) {
			ISLogger.getLogger().error("url = " + p_url);		
			ISLogger.getLogger().error("sendData data : "+p_data);
			ISLogger.getLogger().error("responseCode: " + responseCode);
			ISLogger.getLogger().error("sendData err Message: "+e.getMessage());
			ISLogger.getLogger().error("sendData err Cause: "+e.getCause());
			e.printStackTrace();
		}
		return message;// writer.toString();
	}
	
}
