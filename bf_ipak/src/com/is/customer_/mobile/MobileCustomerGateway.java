package com.is.customer_.mobile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.customer_.ApplicationConfigurationFactory;
import com.is.customer_.mobile.model.findByBind.MobileCustomerByBindRequest;
import com.is.customer_.mobile.model.send.SendSMSRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.zkoss.zhtml.Messagebox;

import java.io.IOException;

/**
 * Created by Dev1 on 14.11.2018.
 */
public class MobileCustomerGateway {
    private final String CLIENT_HEADER = ApplicationConfigurationFactory.getConfigurationManager().get("MOBILE_HEADER");
    private final String BASIC_HEADER = ApplicationConfigurationFactory.getConfigurationManager().get("MOBILE_BASIC_HEADER");
    private final String URI = ApplicationConfigurationFactory.getConfigurationManager().get("MOBILE_URL");
    private static Logger logger = Logger.getLogger(MobileCustomerGateway.class);

    private HttpClient httpClient;

    public MobileCustomerGateway() {
        httpClient = HttpClientBuilder.create().build();
    }

    public String findByBind(MobileCustomerByBindRequest request) throws IOException {
        HttpEntity httpEntity = Post(new ObjectMapper().writeValueAsString(request), "/m2customer/findByBind");

        if (httpEntity == null) {
            return null;
        }

        return EntityUtils.toString(httpEntity);
    }

    public String register(SendSMSRequest request) throws IOException {
    	//logger.error("mobile2021 e 1");
        HttpEntity httpEntity = Post(new ObjectMapper().writeValueAsString(request), "/m2customer/saveCustomer");
        
        if (httpEntity == null) {
        	//logger.error("mobile2021 e 3");
            return null;
        }
        //logger.error("mobile2021 e 4");
        return EntityUtils.toString(httpEntity);
    }

    public String update(SendSMSRequest request) throws IOException {
        HttpEntity httpEntity = Post(new ObjectMapper().writeValueAsString(request), "/m2customer/update");

        if (httpEntity == null) {
            return null;
        }
        return EntityUtils.toString(httpEntity);
    }

    public String updateState(SendSMSRequest request) throws IOException {
        HttpEntity httpEntity = Post(new ObjectMapper().writeValueAsString(request), "/m2customer/update_state");

        if (httpEntity == null) {
            return null;
        }
        return EntityUtils.toString(httpEntity);
    }

    public String confirmRegistration(String content) throws IOException {
    	//logger.error("mobile2021 y 1 : "+content);
    	HttpEntity httpEntity = Post(content, "/m2customer/confirmRegistration");
    	if (httpEntity == null) {
            return null;
        }
        //logger.error("mobile2021 y 4 "+httpEntity);
        //logger.error("mobile2021 y 5 "+EntityUtils.toString(httpEntity));
        //logger.error("mobile2021 y 6");
        return EntityUtils.toString(httpEntity);
    }

    public String confirmUpdate(String content) throws IOException {
        HttpEntity httpEntity = Post(content, "/m2customer/update_confirmation");

        if (httpEntity == null) {
        	//logger.error("mobile2021 confirmUpdate entity null : "+content+". url: /m2customer/update_confirmation");
            return null;
        }
        return EntityUtils.toString(httpEntity);
    }

    public String confirmUpdateState(String content) throws IOException {
        HttpEntity httpEntity = Post(content, "/m2customer/update_state_confirmation");

        if (httpEntity == null) {
        	//logger.error("mobile2021 confirmUpdateState entity null : "+content+". url: /m2customer/update_state_confirmation");
            return null;
        }
        return EntityUtils.toString(httpEntity);
    }


    private HttpEntity Post(String content, String method) throws IOException {
        HttpPost httpPost = null;
        //logger.error("mobile2021 d 1 : "+content);
        try {
            httpPost = new HttpPost(URI + method);
            httpPost.setHeader("Authorization", "Basic " + BASIC_HEADER);
            httpPost.setHeader("IsbmApi-Client", CLIENT_HEADER);
            //logger.error("mobile2021 d 2");
            httpPost.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));
            //logger.error("mobile2021 d 3");
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            //logger.error("mobile2021 d 4");
            return httpEntity;
        } catch (IOException e) {
        	logger.error("mobile2021 d 5");
            logger.error(e);
            throw e;
        } finally {
            if (httpPost != null)
                httpPost.releaseConnection();
        }
    }

}
