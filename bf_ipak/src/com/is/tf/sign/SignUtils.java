package com.is.tf.sign;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;

import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.ser.BeanSerializer;
import org.apache.axis.server.AxisServer;
import org.apache.commons.io.IOUtils;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.NilProvider;
import com.sun.xml.internal.bind.util.AttributesImpl;

public class SignUtils {
	private static SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss"); 
	private static SimpleDateFormat dparse = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
	private static NilProvider np = null;
	private static String endpoint = "http://localhost:3380/signer/Signer";
	
	public static SignResult signTF(SOAPElement element, String un, String pwd, String alias) throws Exception {
		SignResult res = new SignResult();
		try {
			endpoint = getBF_SETS("TF_SIGN_URL");
			if (CheckNull.isEmpty(endpoint)) {
				res.setCode(18);
				res.setMessage("Other error: No endpoint found!");
			}
			/*
			try {
				ISLogger.getLogger().warn(element.toString());
				//ISLogger.getLogger().warn("Data to sign: "+serializeAxisObject(element));
				//ISLogger.getLogger().warn("Data to sign: "+XMLSerializer.write(element));
			} catch (Exception e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			*/
			String data = Canonicalizer.toText(element);
			ISLogger.getLogger().warn("Canonicalizer Data to sign: "+data);
			System.out.println("Canonicalizer Data to sign: "+data);
			res = signData(endpoint, un, pwd, data);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return res;
	}
	
	private static SignResult signData(String endpoint, String un, String pwd, String data) {
		SignResult res = new SignResult();
		StringBuffer sb = new StringBuffer();
		StringBuffer params = new StringBuffer();
		try {
    		sb.append(endpoint);
    		data = URLEncoder.encode(data, "UTF-8");
    		params.append("action=sign");
    		params.append("&username="+URLEncoder.encode(un, "UTF-8"));
    		params.append("&password="+URLEncoder.encode(pwd, "UTF-8"));
    		params.append("&signtype=1");
    		params.append("&pinkey=1111");
    		params.append("&data="+data);
    		
			URL url = new URL(sb.toString());
            
			
			//URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0&PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
			initNp();

			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
				    return true;
				}
			};
			String strres = "";
		    try {
		    	HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
		    	connection.setHostnameVerifier(allHostsValid);
		    	//connection.setRequestMethod("GET");
			    //connection.connect();
			    
			    connection.setRequestMethod("POST");
			    connection.setDoInput(true);
			    connection.setDoOutput(true);
			    connection.getOutputStream().write(params.toString().getBytes());
				connection.connect();

			    strres = getResp(connection.getInputStream(), "utf-8");
		    } catch (Exception e) {
				e.printStackTrace();
				HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
				//connection.setRequestMethod("GET");
			    //connection.connect();
				connection.setRequestMethod("POST");
				/*
				connection.setRequestProperty("action", "sign");
				connection.setRequestProperty("username", un);
				connection.setRequestProperty("password", pwd);
				connection.setRequestProperty("signtype", "1");
				connection.setRequestProperty("pinkey", "1111");
				connection.setRequestProperty("data", data);
				*/
				connection.setDoInput(true);
			    connection.setDoOutput(true);
			    connection.getOutputStream().write(params.toString().getBytes());
				connection.connect();
	            
				strres = getResp(connection.getInputStream(), "utf-8");
			}
		    ISLogger.getLogger().warn("resp : "+strres);
			System.out.println("resp : "+strres);
			System.out.println("code : "+getRespVal(strres, "code"));
			System.out.println("message : "+getRespVal(strres, "message"));
			
    	    res.setCode(Integer.parseInt(getRespVal(strres, "code")));
    	    res.setMessage(getRespVal(strres, "message"));
    	    res.setSerialNumber(getRespVal(strres, "serialnumber"));
    	    res.setSignature(getRespVal(strres, "signature"));
    	    res.setTimeStamp(CheckNull.isEmpty(getRespVal(strres, "timestamp"))?0L:dparse.parse(getRespVal(strres, "timestamp")).getTime());
		} catch (Exception e) {
			e.printStackTrace();ISLogger.getLogger().error(CheckNull.getPstr(e)); 
			res.setCode(18);
			res.setMessage("Other error: "+getOracleErrorMessage(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
		}		
		return res;
	}
	
	public static String getResp(InputStream stream,String cp){
		StringWriter writer = new StringWriter();
		try {
		IOUtils.copy(stream, writer, cp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	public static String getRespVal(String sour,String key){
		try {
			int strt=sour.indexOf(key+">")+key.length()+1;
			int end=(sour.indexOf(key, strt)==-1)?strt:sour.indexOf(key, strt)-2;
			System.out.println(key+" - "+strt+" - "+end+" - "+sour);
			return sour.substring(strt,end);
		} catch (Exception e) {
			return "";
		}
	}
	
	private static void initNp(){
        if (np==null){
              np = new NilProvider();
          np.init();
        }
    }
	
	private static String getOracleErrorMessage(String message) {
		String res = message;
		int beginIndex = message.indexOf("ORA-20000:");
		if (beginIndex > -1) {
			beginIndex += 10;
			int endIndex = message.indexOf("ORA", beginIndex);
			if (endIndex < beginIndex) {
				endIndex = message.length()-1;
			}
			res = message.substring(beginIndex, endIndex);
		}
		//System.out.println("+++"+res);
		//System.out.println("+++"+message);
		return res;
	}
	
	public static String getBF_SETS(String id)  {
		String res = "";
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM BF_SETS where id = '"+id+"'");
			if (rs.next()) {
				res = rs.getString("value");
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
        	try {s.close();} catch (Exception e) {}
        	try {rs.close();} catch (Exception e) {}
            ConnectionPool.close(c);
        }
        return res;
	}
	
	public static void main(String[] args) {
        String pin = "1111";
        String data = "<ns2:saveAccreditiv xmlns:ns2=\"http://service.sbs.com/\"><username>andrey</username><password>111</password><p1T1>123456789123456789123456789</p1T1><accreditiv><p0T21>1</p0T21><p10T21>Êèì Â.Ê.</p10T21><p16T21>0</p16T21><p2T21>VADYA MOLODEC</p2T21><p3T21>2015-10-28T10:48:46.925+05:00</p3T21><p4T21>840</p4T21><p5T21>8.0</p5T21><p6T21>1.2</p6T21><p7T21>2.0</p7T21><p8T21>5.0</p8T21><p9T21>32</p9T21></accreditiv></ns2:saveAccreditiv>";

        try {
            System.out.println(signTF(null,"admin","admin","bank1066").getSignature());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

    }
	
    public static SignResult signData(String pin, String data) throws Exception {
    	SignResult signResult = new SignResult();
    	/*
    	try {
    		File currDir = new File(".");
    	    String path = currDir.getCanonicalPath();
    		String libFile = System.getProperty("os.arch").equals("amd64") ? "jacob-1.18-x64.dll" : "jacob-1.18-x86.dll";
			libFile = path.endsWith("\\")?path+"WebContent\\WEB-INF\\lib\\"+libFile:path+"\\WebContent\\WEB-INF\\lib\\"+libFile;
			System.out.println(libFile);
			InputStream inputStream = new FileInputStream(libFile);
	        File temporaryDll = File.createTempFile("jacob", ".dll");
	        FileOutputStream outputStream = new FileOutputStream(temporaryDll);
	        byte[] array = new byte[8192];
	        for (int i = inputStream.read(array); i != -1; i = inputStream.read(array)) {
	            outputStream.write(array, 0, i);
	        }
	        outputStream.close();
	        System.setProperty(LibraryLoader.JACOB_DLL_PATH, temporaryDll.getAbsolutePath());
	        System.out.println(LibraryLoader.JACOB_DLL_PATH+" = "+temporaryDll.getAbsolutePath());
	        LibraryLoader.loadJacobLibrary();
	        //System.setProperty("jacob.dll.path", "C:\\Java\\jre6\\bin\\");
	        //System.setProperty("-Djava.library.path", "C:\\Java\\jre6\\bin\\");
	        //System.loadLibrary("jacob.dll");
	        ActiveXComponent compCLSID = new ActiveXComponent("clsid:{76FA9E8A-7CD8-4C48-A057-642B7F13F009}");
	        compCLSID.setProperty("LogEnabled", new Variant(true));
	        compCLSID.setProperty("ShowNewCert", new Variant(true));
	        compCLSID.invoke("GetCertificateInfo", new Variant[]{new Variant(pin)});
	        String error = compCLSID.getProperty("LastError").toString();
	        if (!error.isEmpty())
	            throw new Exception(error);
	        final Variant serialNumber = compCLSID.getProperty("SerialNumber");
	        signResult.setSerialNumber(serialNumber.toString());
	        long timeStamp = System.currentTimeMillis();
	        signResult.setTimeStamp(timeStamp);
	        compCLSID.invoke("GetSignature", new Variant[]{new Variant(pin), new Variant(timeStamp + ":" + serialNumber + ":" + data)});
	        error = compCLSID.getProperty("LastError").toString();
	        if (!error.isEmpty())
	            throw new Exception(error);
	
	        String signature = compCLSID.getProperty("Signature64").toString();
	        signResult.setSignature(signature);
	
	        //temporaryDll.deleteOnExit();
        } catch (Exception e) {
			e.printStackTrace();
		}
		*/
        return signResult;
    }
    
    public static String serializeAxisObject(Object obj) throws Exception {
	    if (obj == null) {
	        return null;
	    }
	    StringWriter outStr = new StringWriter();
	    TypeDesc typeDesc = getAxisTypeDesc(obj);
	    QName qname = typeDesc.getXmlType();
	    String lname = qname.getLocalPart();
	    if (lname.startsWith(">") && lname.length() > 1)
	        lname = lname.substring(1);

	    qname = new QName(qname.getNamespaceURI(), lname);
	    AxisServer server = new AxisServer();
	    BeanSerializer ser = new BeanSerializer(obj.getClass(), qname, typeDesc);
	    SerializationContext ctx = new SerializationContext(outStr,
	            new MessageContext(server));
	    ctx.setSendDecl(false);
	    ctx.setDoMultiRefs(false);
	    ctx.setPretty(true);
	    try {
	        ser.serialize(qname, new AttributesImpl(), obj, ctx);
	    } catch (final Exception e) {
	        throw new Exception("Unable to serialize object "
	                + obj.getClass().getName(), e);
	    }

	    String xml = outStr.toString();
	    return xml;
	}

    public static Object deserializeAxisObject(Class<?> cls, String xml) throws Exception {
	    final String SOAP_START = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header /><soapenv:Body>";
	    final String SOAP_START_XSI = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header /><soapenv:Body>";
	    final String SOAP_END = "</soapenv:Body></soapenv:Envelope>";

	    Object result = null;
	    try {
	        Message message = new Message(SOAP_START + xml + SOAP_END);
	        result = message.getSOAPEnvelope().getFirstBody()
	                .getObjectValue(cls);
	    } catch (Exception e) {
	        try {
	            Message message = new Message(SOAP_START_XSI + xml + SOAP_END);
	            result = message.getSOAPEnvelope().getFirstBody()
	                    .getObjectValue(cls);
	        } catch (Exception e1) {
	            throw new Exception(e1);
	        }
	    }
	    return result;
	}

    public static TypeDesc getAxisTypeDesc(Object obj) throws Exception {
	    final Class<? extends Object> objClass = obj.getClass();
	    try {
	        final Method methodGetTypeDesc = objClass.getMethod("getTypeDesc",
	                new Class[] {});
	        final TypeDesc typeDesc = (TypeDesc) methodGetTypeDesc.invoke(obj,
	                new Object[] {});
	        return (typeDesc);
	    } catch (final Exception e) {
	        throw new Exception("Unable to get Axis TypeDesc for "
	                + objClass.getName(), e);
	    }
	}
    
    public static String getMd5Digest(String input) {
    	try
    	{
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		byte[] messageDigest = md.digest(input.getBytes());
    		BigInteger number = new BigInteger(1,messageDigest);
    		String s = number.toString(16);
    		while (s.length() < 32)
    			s = "0" + s;
    		return s;
    	} catch(NoSuchAlgorithmException e) {
    		throw new RuntimeException(e);
    	}
    }
    
    public static String getMD5(byte[] inbytes) throws NoSuchAlgorithmException {
    	if(inbytes == null) return "";
    	StringBuilder md5 = new StringBuilder();
    	MessageDigest algorithm = MessageDigest.getInstance("MD5");
    	algorithm.reset();
    	byte bytes[] = algorithm.digest(inbytes);
    	String hexStr;
    	for(int i = 0; i < bytes.length; i++) {
    		hexStr = Integer.toHexString(0xFF & bytes[i]);
    		if(hexStr.length() == 1) md5.append('0');
    		md5.append(hexStr);
    	}
    	return md5.toString();
    }

    /* convert from UTF-8 encoded HTML-Pages -> internal Java String Format */
    public static String convertFromUTF8(String s) {
      String out = null;
      try {
        out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
      } catch (java.io.UnsupportedEncodingException e) {
        return null;
      }
      return out;
    }

    /* convert from internal Java String Format -> UTF-8 encoded HTML/JSP-Pages  */
    public static String convertToUTF8(String s) {
      String out = null;
      try {
        out = new String(s.getBytes("UTF-8"));
      } catch (java.io.UnsupportedEncodingException e) {
        return null;
      }
      return out;
    }
}