package com.is.providers;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;

//import org.zkoss.json.*;
//import org.zkoss.json.parser.JSONParser;



public class Sarkor extends BaseProvider{

        @Override
        public PayResp check(Credentials cr, Payment pay) {
    		JSONObject resultJson = new JSONObject();
    		try {
    		resultJson.put("paysys_login",cr.getUn());
    		resultJson.put("paysys_pass",cr.getPw());
    		resultJson.put("operation",30);
    		resultJson.put("client_id",pay.getP_number());
    		//resultJson.put("client_id",Integer.parseInt(pay.getP_number()));
    		System.out.println(resultJson.toString());
    //
    		PostMethod post = new PostMethod(this.getEndPoint());
    		//RequestEntity entity = new StringRequestEntity(resultJson.toString(), "application/json", HTTP.UTF_8);
    		RequestEntity entity = new StringRequestEntity(resultJson.toString());
    		//RequestEntity entity = new StringRequestEntity(resultJson.toString(), "text/html", HTTP.UTF_8);
    		post.setRequestEntity( entity);
    		HttpClient hclient = new HttpClient();
    		int statusCode = hclient.executeMethod(post);
    	    System.out.println(StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()));
   		
    	    JSONObject jsonObj = new JSONObject(StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()));
    		
    		this.getRes().setCode(jsonObj.getInt("status"));
    		this.getRes().setName(jsonObj.getString("message"));
    		getPres().setRs(getRes());
    		
    	    //getPres().setRs(new Res(0,StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()),new Date()));
    	    
    		} catch (Exception e) {
    			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    		}	
    		
    		return getPres();
    	}

        @Override
        public PayResp pay(Credentials cr, Payment pay, HashMap<String,String> addInfo) {
    		JSONObject resultJson = new JSONObject();
    		try {
    		resultJson.put("paysys_login",cr.getUn());
    		resultJson.put("paysys_pass",cr.getPw());
    		resultJson.put("operation",10);
    		resultJson.put("client_id",pay.getP_number());
    		resultJson.put("amount",pay.getAmount());
    		resultJson.put("trans_id",pay.getTr_id());
    		System.out.println(resultJson.toString());
    		PostMethod post = new PostMethod(this.getEndPoint());
    		RequestEntity entity = new StringRequestEntity(resultJson.toString());
    		post.setRequestEntity( entity);
    		HttpClient hclient = new HttpClient();
    		int statusCode = hclient.executeMethod(post);
    	    System.out.println(StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()));
    	    JSONObject jsonObj = new JSONObject(StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()));
    		this.getRes().setCode(jsonObj.getInt("status"));
    		this.getRes().setName(jsonObj.getString("message"));
    		getPres().setRs(getRes());
    		} catch (Exception e) {
    			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    		}	
    		
    		return getPres(); 
    		
        }

        @Override
        public PayResp checkTr(Credentials cr, long id) {
    		JSONObject resultJson = new JSONObject();
    		try {
    		resultJson.put("paysys_login",cr.getUn());
    		resultJson.put("paysys_pass",cr.getPw());
    		resultJson.put("operation",31);
    		resultJson.put("trans_id",id);
    		resultJson.put("trans_id_type",0);
    		System.out.println(resultJson.toString());
    		PostMethod post = new PostMethod(this.getEndPoint());
    		RequestEntity entity = new StringRequestEntity(resultJson.toString());
    		post.setRequestEntity( entity);
    		HttpClient hclient = new HttpClient();
    		int statusCode = hclient.executeMethod(post);
    	    System.out.println(StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()));
    	    JSONObject jsonObj = new JSONObject(StringEscapeUtils.unescapeJava(post.getResponseBodyAsString()));
    		this.getRes().setCode(jsonObj.getInt("status"));
    		this.getRes().setName(jsonObj.getString("message"));
    		//getPres().setRs(getRes());
    		getCtr().setRs(getRes());
    		} catch (Exception e) {
    			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    		}	
    		
    		return new PayResp();        }

		@Override
		public ListTrResp listTr(Credentials cr, Date startDate, Date endDate) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CheckPerResp checkPer(Credentials cr, Date startDate,
				Date endDate) {
			// TODO Auto-generated method stub
			return null;
		}

}