package com.is.utils;

import java.security.Provider;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import java.security.KeyStore;
import java.security.Security;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;


public class NilProvider extends Provider{
	
	public NilProvider() {
    	super("NilProvider", 1.0, "Trust all certificates" );
    	put( 
    	"TrustManagerFactory.TrustAllCertificates",
    	NilTrustManagerFactory.class.getName() );
    	}    
    	protected static class NilTrustManagerFactory	extends TrustManagerFactorySpi {
    	      public NilTrustManagerFactory() {}
    	      protected void engineInit( KeyStore keystore ) {}
    	      protected void engineInit( 	ManagerFactoryParameters mgrparams ) {}
    	      protected TrustManager[] engineGetTrustManagers() {
    	           return new TrustManager[] {	new NilX509TrustManager()};
    	    }
    	}

    	protected static class NilX509TrustManager 	implements X509TrustManager {
    	public void checkClientTrusted(
    	X509Certificate[] chain, String authType) {}
    	public void checkServerTrusted(
    	X509Certificate[] chain, String authType) {}
    	public X509Certificate[] getAcceptedIssuers() {
    	return null;
    	}
    	}

    	public void init() {
    		Security.addProvider( new NilProvider() );
    		Security.setProperty(
    		"ssl.TrustManagerFactory.algorithm",
    		"TrustAllCertificates");
    	}
    	

    
    
	

}
