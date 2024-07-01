package com.is.providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;


import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import org.json.JSONObject;

import org.zkoss.json.*;



public class Sarkor extends BaseProvider{

        @Override
        public PayResp check(Credentials cr, Payment pay) {
                JSONObject resultJson = new JSONObject();
                try {
                resultJson.put("paysys_login",cr.getUn());
                resultJson.put("paysys_pass",cr.getPw());
                resultJson.put("operation",30);
                resultJson.put("client_id",Integer.parseInt(pay.getP_number()));
                System.out.println(resultJson.toString());

                PostMethod post = new PostMethod(this.getEndPoint()+"?type=json");

                RequestEntity entity = new StringRequestEntity(resultJson.toString());
                post.setRequestEntity( entity);
                HttpClient hclient = new HttpClient();
                int statusCode = hclient.executeMethod(post);
                //System.out.println("statusCode "+statusCode);
            //System.out.println(post.getResponseBodyAsString());
            
            resultJson = new JSONObject(post.getResponseBodyAsString());
            
            System.out.println("status:"+resultJson.getInt("status"));
            System.out.println("message:"+resultJson.getString("message"));

                } catch (Exception e) {
                        e.printStackTrace();
                }

                return getPres();
        }

        @Override
        public PayResp pay(Credentials cr, Payment pay, AddInfo addInfo) {
                // TODO Auto-generated method stub
                return null;
        }

        @Override
        public CheckTrResp checkTr(Credentials cr, long id) {
                // TODO Auto-generated method stub
                return null;
        }

}