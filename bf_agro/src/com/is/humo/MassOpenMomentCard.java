package com.is.humo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.utils.CheckNull;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.KeyType_Agreement;
import globus.issuing_v_01_02_xsd.ListType_AccountInfo;
import globus.issuing_v_01_02_xsd.ListType_CardInfo;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo;
import globus.issuing_v_01_02_xsd.RowType_CardInfo;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;

public class MassOpenMomentCard {
	private globus.IssuingWS.IssuingPortProxy issuingPortProxy = null;
    static PreparedStatement psUpdate=null,psInsert=null; 
	
	public  ResponsInfo createCardsHumo(Connection c,String client_b,String acc,OperationConnectionInfo connectionInfo,String HUMO_HOST,String HUMO_USERNAME,String HUMO_PASSWORD,String HUMO_BANK_C,String HUMO_GROUPC) throws Exception {
		
		

         
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		

		ResponsInfo responsInfo=new ResponsInfo();
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		 
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(
				com.is.humo.HumoCardsService.getRowType_CustomerMoment(client_b,calendar));
		
		RowType_AccountInfo accountInfo = new RowType_AccountInfo();

		accountInfo.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_BaseMoment(acc));
		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
		
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		accountsListInfo.value = ltaccounts;
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		
		eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(219));
		
		
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		cardInfo.setLogicalCard(com.is.humo.HumoCardsService.getRowType_CardInfo_LogicalCardMoment());
		cardInfo.setPhysicalCard(com.is.humo.HumoCardsService.getRowType_CardInfo_PhysicalCardMoment());
		cardInfo.setEMV_Data(eMV_Data);
		ListType_CardInfo cards = new ListType_CardInfo();
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		cardsListInfo.value = cards;

		try {
			
			 issuingPortProxy = initNp(HUMO_HOST,HUMO_USERNAME,HUMO_PASSWORD);
			connectionInfo = new OperationConnectionInfo();
			
			String externalSesionId=getExternalSession();
			 
	         
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);
			connectionInfo.setBANK_C(HUMO_BANK_C);
			connectionInfo.setGROUPC(HUMO_GROUPC);
			
			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
					com.is.humo.HumoCardsService.getRowType_AgreementMoment(calendar));
		
		
			
			////////////////////////////////////////////////////////////////////////////////////
					ISLogger.getLogger().info("start newCustomerAndAgreement");
					OperationResponseInfo  orInfo = issuingPortProxy
						.newCustomerAndAgreement(connectionInfo, customerInfo,
								customListInfo, agreementInfo, accountsListInfo,
								cardsListInfo);	
			         ISLogger.getLogger().info("end newCustomerAndAgreement");
			///////////////////////////////////////////////////////////////////////////////////////		 
					
					 
					 
                    	insertProtocol(c,client_b,acc,cardsListInfo.value.getRow(0).getLogicalCard().getCARD(),externalSesionId,orInfo.getError_description());
			
		
		}
		
		catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		
		
		}
		return responsInfo;
	}
	
	private static globus.IssuingWS.IssuingPortProxy initNp(String HUMO_HOST,String HUMO_USERNAME,String HUMO_PASSWORD)throws ClientProtocolException, IOException,
	NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
globus.IssuingWS.IssuingPortProxy issuingPortProxy;

final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public void checkClientTrusted(
			java.security.cert.X509Certificate[] certs, String authType) {
	}

	public void checkServerTrusted(
			java.security.cert.X509Certificate[] certs, String authType) {
	}
} };
try {
	SSLContext sc = SSLContext.getInstance("TLSv1.2");
	sc.init(null, trustAllCerts, new java.security.SecureRandom());
	HostnameVerifier allHostsValid = new HostnameVerifier() {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
	HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

} catch (Exception ex) {
}

return issuingPortProxy = new globus.IssuingWS.IssuingPortProxy(HUMO_HOST,HUMO_USERNAME,HUMO_PASSWORD);
}

	
		public static String getExternalSession() {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
			return dateFormat.format(new Date());
		}
		
		public static String isExsistCard(Connection c, String clientB, String mfo,
				String cardType) {

			String pan = null;
			PreparedStatement ps = null;
			boolean isExsestCard = false;

			try {

				ps = c.prepareStatement("SELECT * FROM HUMO_CARDS where client_B=? and branch=? and card_name like ?");
				ps.setString(1, clientB);
				ps.setString(2, mfo);
				ps.setString(3, cardType + "%");

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					isExsestCard = true;
					pan = rs.getString("card");
				}

			} catch (Exception e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			} finally {

			}
			return pan;

		}
		
		public  void insertProtocol(Connection c,String client_b,String acc,String card,String sessionid,String err){

			try {
		
				
				psInsert.setString(1, client_b);
				psInsert.setString(2,acc);
				psInsert.setString(3,card);
				psInsert.setString(4,sessionid);
				psInsert.setString(5,err);
		       
				psInsert.executeUpdate();
				c.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} 
			
		}
		
		public static void initProtocol(Connection c) throws SQLException{
			//psUpdate = c.prepareStatement("update HUMO_MOMENT set range_id=?, branch_id=?,state_n=?,err_n=?, err_d=?,state_d=?  where client_b=? and ");
			 psInsert = c.prepareStatement("insert into HUMO_MOMENT (client_b, acc,card, sessionid,err) values (?,?,?,?,?)");
			 
			
		}
}
