package com.is.sign.asclient;

import java.net.URL;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sign.Res;
import com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapStub;
import com.is.utils.CheckNull;

public class ASClientService {
	private static String urlASClient = ConnectionPool.getValue("SIGN_ASCLIENT_URL");
	private static Logger log = ISLogger.getLogger();
	
	public static Res checkASClientSign(String cms, String docnum) {
		Res res = new Res();
		if (CheckNull.isEmpty(urlASClient)) {
			urlASClient = ConnectionPool.getValue("SIGN_ASCLIENT_URL");
		}
		try {
			CBRUTCryptAuthServerSoapStub stub = new CBRUTCryptAuthServerSoapStub(new URL(urlASClient), new org.apache.axis.client.Service());
			if (stub.verifyCmsAS(cms, docnum)) {
				res = new Res(0, "Sign successfully verified.");
			} else {
				res = new Res(1, "Sign not verified!");
			}
			
			String[] str = stub.verifyCmsExAS(cms, docnum);
			for (int i = 0; i < str.length; i++) {
				System.out.println(i+") "+str[i]+";");
			}
			int codepage = 1;
			str = stub.verifyAndGetCmsContentAS(cms, docnum, codepage);
			for (int i = 0; i < str.length; i++) {
				System.out.println(i+") "+str[i]+";");
			}
			int output = 1;
			str = stub.verifyAndGetCmsContentASEx(cms, docnum, codepage, output);
			for (int i = 0; i < str.length; i++) {
				System.out.println(i+") "+str[i]+";");
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
			res = new Res(1, "Sign verify end with error: "+(CheckNull.isEmpty(e.getMessage())?CheckNull.getPstr(e):e.getMessage()));
		} finally {
			
		}
		return res;
	}
}
