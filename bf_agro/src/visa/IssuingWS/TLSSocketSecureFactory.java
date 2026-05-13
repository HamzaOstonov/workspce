package visa.IssuingWS;

import java.io.IOException;
import java.net.Socket;
import java.util.Hashtable;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.axis.components.net.BooleanHolder;
import org.apache.axis.components.net.JSSESocketFactory;

public class TLSSocketSecureFactory extends JSSESocketFactory {

	private final String TLS_VERSION_1_1 = "TLSv1.1";
	private final String TLS_VERSION_1_2 = "TLSv1.2";

	public TLSSocketSecureFactory(@SuppressWarnings("rawtypes") Hashtable attributes) {
	super(attributes);
	}
	
	final TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {

				 public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                     return null;
                 }
                 public void checkClientTrusted(
                     java.security.cert.X509Certificate[] certs, String authType) {
                 }
                 public void checkServerTrusted(
                     java.security.cert.X509Certificate[] certs, String authType) {
                 }
}
};

	@Override
	protected void initFactory() throws IOException {
	SSLContext context;
	try {
	  context = SSLContext.getInstance(TLS_VERSION_1_2);
	  context.init(null, trustAllCerts, null);
	  //context.init(null, null, null);
	  sslFactory = context.getSocketFactory();
	} catch (Exception e ) {
	  //printstacktrace or throw IOException
		e.printStackTrace();
	}
	}

	@Override
	public Socket create(String host, int port, StringBuffer otherHeaders, BooleanHolder useFullURL) throws Exception {
	if (sslFactory == null) {
	  initFactory();
	}
	Socket s = super.create(host, port, otherHeaders, useFullURL);
	((SSLSocket) s).setEnabledProtocols(new String[] {TLS_VERSION_1_1, TLS_VERSION_1_2 });
	return s;
	}
	

}
