package com.is.humo;

import java.io.IOException;
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
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.ClientProtocolException;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import globus.issuing_v_01_02_xsd.RowType_ReplaceCard;
import globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder;

public class MassOpenCardRenew3 {

	private String HUMO_BANK_C;
	private String HUMO_GROUPC;
	private String HUMO_BRANCHID;
	private String HUMO_RANGEID;
	private String HUMO_HOST;
	private String HUMO_USERNAME;
	private String HUMO_PASSWORD;
	private String CLIENT_B;
	private String BRANCH;
	private String CARD_TYPE;
	private globus.IssuingWS.IssuingPortProxy issuingPortProxy = null;
	private OperationConnectionInfo connectionInfo = null;
	private PreparedStatement psInsertRange = null;
	private PreparedStatement psdeleteCards=null;
	private PreparedStatement psUpdateCards=null;
	private PreparedStatement psSelectStopCards=null;
	

	String bankC = null;
	String groupC = null;

	public MassOpenCardRenew3() {

	}
	public void openCard(int userId, String branch, String alias)
			throws SQLException {

		String sql = "select a.* , s.code as code_s, s.description as description_s,s.branch as branch_s ,s.range_id,s.branch_id ,s.bin,s.group_c,s.bank_c,s.chip_app_id from ("
				+ "SELECT nvl((select c.client "
				+ " from humo_cards c "
				+ " where c.branch = j.branch "
				+ " and c.client_b = j.id "
				+ " and rownum = 1), "
				+ " 0) as client, "
				+ " h.card_type, "
				+ " decode(h.card_type,'PYMM',2,'GYMM',2,'KYMM',2,'YMM',2,'PMU', 3, 'GMU', 3,'KMU', 3,'MU', 3, 0) as cardTypeNumber, "
				+ " h.branch as branch_hco, "
				+ " h.client as client_hco, "
				+ " h.name as name_hco, "
				+ " h.account  as account_hco, "
				+ " h.range_id as range_hco, "
				+ " h.card_type||' '||Transymbol2(j.short_name) as short_name2,  "
				+ " substr(Transymbol2(j.director_name),1,34) as director_name2, "
				+ " j.* "
				+ " FROM humo_card_open_renew h, CLIENT_J j  "
				+ " where h.branch = j.branch "
				+ "  and h.client = j.id and h.state_n=0 "
				+ "   and h.branch="
				+ branch
				+ " and h.user_id="
				+ userId
				+ ")"
				+ " a, SS_HUMO_TYPE_OF_CARD s "
				+ " where a.cardTypeNumber=s.code "
				+ " and a.branch = s.branch";
		Connection genC = null;
		Connection c = null;

		try {
			genC = ConnectionPool.getConnection(alias);
			Statement s = genC.createStatement();
			ResultSet rs = s.executeQuery(sql);

			HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
			HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
			
			HUMO_HOST = ConnectionPool.getValue("HUMO_HOST");
			HUMO_USERNAME = ConnectionPool.getValue("HUMO_USERNAME");
			HUMO_PASSWORD = ConnectionPool.getValue("HUMO_PASSWORD");
			issuingPortProxy = getPortProxy(HUMO_HOST, HUMO_USERNAME,HUMO_PASSWORD);
			connectionInfo = getConInfo(HUMO_BANK_C, HUMO_GROUPC);

			while (rs.next()) {

				c = ConnectionPool.getConnection(alias);

				HUMO_BRANCHID = rs.getString("BRANCH_ID");
				HUMO_RANGEID = rs.getString("RANGE_ID");
				CLIENT_B = rs.getString("client_hco");
				BRANCH = rs.getString("branch_hco");
				CARD_TYPE = rs.getString("card_type");

				replaceCard(c, issuingPortProxy, connectionInfo, CLIENT_B,
						BRANCH, CARD_TYPE, HUMO_BRANCHID, HUMO_RANGEID,HUMO_BANK_C,HUMO_GROUPC);

				c.commit();
				ConnectionPool.close(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e);
		}

		finally {
			if(genC!=null){
				
				ConnectionPool.close(genC);
			}
		}

	}


	public void replaceCard(Connection c,globus.IssuingWS.IssuingPortProxy portProxy,OperationConnectionInfo conInfo,String clientHco,String branchHco,String cardType,String HUMO_BRANCHID, String HUMO_RANGEID,String bankC,String groupC) throws Exception {
		  
		  String oldCard=isExsistCard(c,clientHco,branchHco,cardType);
		  ReplaceCardInfo cardInfo=null;
		  System.out.println("oldCard:"+oldCard);
		  if(oldCard!=null){
			  
			                 String oldCard2=isStoped(c,clientHco,branchHco,cardType,portProxy,bankC,groupC);
			                 System.out.println("oldCard2:"+oldCard2);
			                 if(oldCard2!=null){
			            	                     
			            	                     cardInfo=getNewCard(oldCard2,portProxy,conInfo);
			                                     if (cardInfo.getError_description()==null){
			                                     String newCard=cardInfo.getCard();	 
			                                     Calendar NEW_EXPIRY=cardInfo.getNEW_EXPIRY();	
				                                 insertRangAndBranchId(c,cardType,clientHco,branchHco,HUMO_BRANCHID,HUMO_RANGEID,"2","1","","");
				                                 updateHumoCards(c,oldCard2,newCard,NEW_EXPIRY);  
			                                     }else{
			                                     insertRangAndBranchId(c,cardType,clientHco,branchHco,HUMO_BRANCHID,HUMO_RANGEID,"0","1","Error_action: "+cardInfo.getError_action()+" Error_description: "+cardInfo.getError_description(),"");
						                         }
		                   }else{
				           insertRangAndBranchId(c,cardType,clientHco,branchHco,HUMO_BRANCHID,HUMO_RANGEID,"0","1","Статус этой карты активной","");
				           }
		  
		  }else{
		  insertRangAndBranchId(c,cardType,clientHco,branchHco,HUMO_BRANCHID,HUMO_RANGEID,"1","1","У этого клиента нет такой карты","");
		  }
		  
		
		}

	public String isStoped(Connection c,String clientB, String mfo,
			String cardType,globus.IssuingWS.IssuingPortProxy portProxy,String bankC,String groupC){

		PreparedStatement ps = null;
		String real_card=null;
	

		try {
            ISLogger.getLogger().error("isStoped: clientB:"+clientB+" mfo:"+mfo+" cardType:"+cardType);
			ps = c.prepareStatement("SELECT * FROM HUMO_CARDS where client_b=? and branch=? and card_name like ? and status1=? and status2=?");
			ps.setString(1, clientB);
			ps.setString(2, mfo);
			ps.setString(3, cardType+"%");
			ps.setString(4, "2");
			ps.setString(5, "2");
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				real_card=rs.getString("real_card");
				ISLogger.getLogger().error("stoped real_card: "+real_card);
				deleteCards(c,real_card,clientB, mfo,cardType,portProxy,bankC,groupC);
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {

		}
		
		return real_card;
		
		
	}
	public ReplaceCardInfo getNewCard(String card,
			globus.IssuingWS.IssuingPortProxy portProxy,
			OperationConnectionInfo conInfo) throws KeyManagementException,
			ClientProtocolException, NoSuchAlgorithmException,
			KeyStoreException, IOException {

		RowType_ReplaceCard parameters = new RowType_ReplaceCard();
		OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
		RowType_ReplaceCardHolder details = new RowType_ReplaceCardHolder();
		ReplaceCardInfo replaceCardInfo = new ReplaceCardInfo();
		BigInteger responseCode;

		parameters.setCARD(card);

		//portProxy.replaceCard(conInfo, parameters, responseInfo, details);
		

		responseCode = responseInfo.value.getResponse_code();

		System.out.println("responseCode: "+responseCode);
		System.out.println("Error_description: "+responseInfo.value.getError_description());
		
		ISLogger.getLogger().error("responseCode: "+responseCode);
		ISLogger.getLogger().error("Error_action: "+responseInfo.value.getError_action());
		ISLogger.getLogger().error("Error_description: "+responseInfo.value.getError_description());
		//if (responseCode.equals(0)) {
		
		if (responseInfo.value.getError_description()==null) {

			replaceCardInfo.setCard(details.value.getNEW_CARD());
			replaceCardInfo.setNEW_EXPIRY(details.value.getNEW_EXPIRY());
			replaceCardInfo.setError_description(null);

		}

		else {
			replaceCardInfo.setResponse_code(responseCode.toString());
			replaceCardInfo.setError_action(responseInfo.value
					.getError_action());
			replaceCardInfo.setError_description(responseInfo.value
					.getError_description());

		}

		return replaceCardInfo;

	}

	public OperationConnectionInfo getConInfo(String HUMO_BANK_C,
			String HUMO_GROUPC) {
		OperationConnectionInfo conInfo = new OperationConnectionInfo();

		conInfo.setBANK_C(HUMO_BANK_C);
		conInfo.setGROUPC(HUMO_GROUPC);
		conInfo.setEXTERNAL_SESSION_ID(getExternalSession());

		return conInfo;

	}

	public static String getExternalSession() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		return dateFormat.format(new Date());
	}

	private static globus.IssuingWS.IssuingPortProxy getPortProxy(
			String HUMO_HOST, String HUMO_USERNAME, String HUMO_PASSWORD)
			throws ClientProtocolException, IOException,
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

		return issuingPortProxy = new globus.IssuingWS.IssuingPortProxy(
				HUMO_HOST, HUMO_USERNAME, HUMO_PASSWORD);
	}

	public void insertRangAndBranchId(Connection c, String cardType,
			String clientId, String branch, String branchId, String rangeId,
			String state_n, String state_d, String err_n, String err_d) {

		int res = 0;

		try {
			 psInsertRange = c.prepareStatement("update HUMO_CARD_OPEN_RENEW set range_id=?, branch_id=?,state_n=?,err_n=?, err_d=?,state_d=?  where client=? and branch=? and card_type=?");
				
			psInsertRange.setString(1, rangeId);
			psInsertRange.setString(2, branchId);
			psInsertRange.setString(3, state_n);
			psInsertRange.setString(4, err_n);
			psInsertRange.setString(5, err_d);
			psInsertRange.setString(6, state_d);
			psInsertRange.setString(7, clientId);
			psInsertRange.setString(8, branch);
			psInsertRange.setString(9, cardType);

			res = psInsertRange.executeUpdate();
			c.commit();

		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

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
			
			System.out.println("clientB: "+clientB+" mfo: "+mfo+" cardType: "+cardType);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard = true;
				pan = rs.getString("real_card");
				
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {

		}
		return pan;

	}

	public void updateHumoCards(Connection c,String oldCard,String newCard,Calendar NEW_EXPIRY) {
		PreparedStatement psUpdateHumoCards = null;
		try {

			psUpdateHumoCards = c
					.prepareStatement("update humo_cards set real_card=?,card='777', EXPIRY1=?,status1='1',status2='0' "
							+ " where real_card=?");

			psUpdateHumoCards.setString(1, newCard);
			psUpdateHumoCards.setDate(2, new java.sql.Date(NEW_EXPIRY.getTimeInMillis()));
			psUpdateHumoCards.setString(3, oldCard);
			
			ISLogger.getLogger().error("oldCard: "+oldCard+"newCard:"+newCard+" EXPIRY1:"+new java.sql.Date(NEW_EXPIRY.getTimeInMillis()));

			psUpdateHumoCards.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void deleteCards(Connection c,String real_card,String clientB, String mfo,String cardType,globus.IssuingWS.IssuingPortProxy portProxy,String bankC,String groupC) {

		int res = 0;
		String realCard=null;

		try {
			psSelectStopCards=c.prepareStatement("select real_card from humo_cards where real_card<> ? and client_b=? and branch=? and card_name like ?");
			psSelectStopCards.setString(1, real_card);
			psSelectStopCards.setString(2, clientB);
			psSelectStopCards.setString(3, mfo);
			psSelectStopCards.setString(4, cardType+"%");
			
			
			ResultSet rs = psSelectStopCards.executeQuery();
			
			while (rs.next()){
				realCard = rs.getString("real_card");
				System.out.println("Stopped old realCard:"+realCard);
				ISLogger.getLogger().error("Stopped realCard:"+realCard);
				addStopList(realCard,portProxy,bankC,groupC);
				
			}
			
			
			
			
			 psUpdateCards = c.prepareStatement("update humo_cards set card='888' where real_card<> ? and client_b=? and branch=? and card_name like ?");
				
			 psUpdateCards.setString(1, real_card);
			 psUpdateCards.setString(2, clientB);
			 psUpdateCards.setString(3, mfo);
			 psUpdateCards.setString(4, cardType+"%");
			

			 psUpdateCards.executeUpdate();
			c.commit();
			
			psdeleteCards=c.prepareStatement("delete from  humo_cards where real_card<> ? and client_b=? and branch=? and card_name like ? and card='888'");
			
			psdeleteCards.setString(1, real_card);
			psdeleteCards.setString(2, clientB);
			psdeleteCards.setString(3, mfo);
			psdeleteCards.setString(4, cardType+"%");
			 
			psdeleteCards.executeUpdate();
			
			c.commit();

		} catch (Exception e) {
			e.printStackTrace();

			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

	}
	 static  OperationResponseInfo addStopList(String realCard, IssuingPortProxy proxy, String bankC, String groupC) throws RemoteException {
		 RowType_GetRealCard_ResponseHolder response = new RowType_GetRealCard_ResponseHolder();
		 OperationResponseInfo res=null;
		 
		 try{
		   OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
	       connectionInfo.setBANK_C(bankC);
	       connectionInfo.setGROUPC(groupC);
	       String externalSesionId=getExternalSession();
			System.out.println("externalSesionId:"+externalSesionId);
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

	    

			RowType_AddCardToStopList_Request param=new RowType_AddCardToStopList_Request();
		
			param.setCARD(realCard);
			param.setSTOP_CAUSE("1");
			param.setTEXT("stolen");
			param.setBANK_C(bankC);
			param.setGROUPC(groupC);
			
			  res=proxy.addCardToStop(connectionInfo, param);
	    
			 ISLogger.getLogger().error(res.getError_description()); 
			  
	       //proxy.getRealCard(connectionInfo, request, new OperationResponseInfoHolder(), response);
	       
	       
	       
	      }catch(Exception e){
	    	  
	    	  ISLogger.getLogger().error(e);
	    	  
	      }
	      
	      
	       return res;
	       
	   }
}
