package com.is.utils;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import java.security.SecureRandom;




public class EasySSLProtocolConfigurer {
	public void init() {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[] { new EasyX509TrustManager() }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new EasyHostnameVerifier());
        } catch (Exception e) {
        }
    }
    private static class EasyX509TrustManager implements X509TrustManager {

    	public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    	
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
        public EasyX509TrustManager()
        {
        }

        public boolean isClientTrusted(X509Certificate cert[])
        {
            return true;
        }

        public boolean isServerTrusted(X509Certificate cert[])
        {
            return true;
        }
/*
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[0];
        }
*/                
    }
    private static class EasyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
    
    
    
    public static void disableCertificateValidation() {
        // Create a trust manager that does not check certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
 
        	public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                //return;
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                return;
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        	System.out.println("Error "+e.getMessage());
            //return;
        }
    }    
    
 
   	
    	
    	
   
    
    
}
