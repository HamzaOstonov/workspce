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
import java.util.logging.Logger;
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

public class MassOpenCardRenew {
	
	
	
	private String HUMO_BANK_C;
	private String HUMO_GROUPC;
	private String HUMO_BINCOD;
	
	private Long HUMO_CHIPAPPID;
	private String HUMO_BRANCHID;
	private Long HUMO_RANGEID;
	
	private globus.IssuingWS.IssuingPortProxy issuingPortProxy = null;
	private OperationConnectionInfo connectionInfo = null;
	
	public MassOpenCardRenew() {
		
		}
		
	
	
//	private String sql = "select a.* , s.code as code_s, s.description as description_s,s.branch as branch_s ,s.range_id,s.branch_id ,s.bin,s.group_c,s.bank_c,s.chip_app_id from ("+
//"SELECT nvl((select c.client "+
//"             from humo_cards c "+
//"            where c.branch = j.branch "+
//"              and c.client_b = j.id "+
//"              and rownum = 1), "+
//"           0) as client, "+
//"       h.card_type, "+
//"       decode(h.card_type,'PYMM',2,'GYMM',2,'KYMM',2,'YMM',2,'PMU', 3, 'GMU', 3,'KMU', 3,'MU', 3, 0) as cardTypeNumber,"+
//"       h.branch as branch_hco, "+
//"       h.client as client_hco, "+
//"       h.name as name_hco, "+
//"       h.account  as account_hco, "+
//"       h.range_id as range_hco, "+
//"       h.card_type||' '||Transymbol2(j.short_name) as short_name2, "+ 
//"       substr(Transymbol2(j.director_name),1,34) as director_name2, "+        
//"       j.* "+
//"  FROM humo_card_open_renew h, CLIENT_J j  "+
//" where h.branch = j.branch "+
//"   and h.client = j.id and h.state_n=0) a, SS_HUMO_TYPE_OF_CARD s "+
//"   where a.cardTypeNumber=s.code "+
//"   and a.branch = s.branch";
	
	
	
	private Customer customer;
	private PreparedStatement psInsertAgreement=null;
	private PreparedStatement psInsertRange=null;
	private PreparedStatement psInsertClient=null;
	private PreparedStatement psNewAgremment=null;
	private PreparedStatement psInsertHumoCards =null;
	private PreparedStatement psNewAccounts=null;
	private PreparedStatement psUpdateHumoCards=null;
	String bankC=null;
	String groupC=null;
	
	
	
	public void openCard(int userId, String branch) throws SQLException{
		
		 String sql = "select a.* , s.code as code_s, s.description as description_s,s.branch as branch_s ,s.range_id,s.branch_id ,s.bin,s.group_c,s.bank_c,s.chip_app_id from ("+
		"SELECT nvl((select c.client "+
		            " from humo_cards c "+
		            " where c.branch = j.branch "+
		             " and c.client_b = j.id "+
		             " and rownum = 1), "+
		           " 0) as client, "+
		      " h.card_type, "+
		      " decode(h.card_type,'PYMM',2,'GYMM',2,'KYMM',2,'YMM',2,'PMU', 3, 'GMU', 3,'KMU', 3,'MU', 3, 0) as cardTypeNumber, "+
		      " h.branch as branch_hco, "+
		      " h.client as client_hco, "+
		      " h.name as name_hco, "+
		      " h.account  as account_hco, "+
		      " h.range_id as range_hco, "+
		      " h.card_type||' '||Transymbol2(j.short_name) as short_name2,  "+
		      " substr(Transymbol2(j.director_name),1,34) as director_name2, "+        
		      " j.* "+
		 " FROM humo_card_open_renew h, CLIENT_J j  "+
		" where h.branch = j.branch "+
		 "  and h.client = j.id and h.state_n=0 "+
		   "   and h.branch="+branch +
		  " and h.user_id="+userId+")" +
		  " a, SS_HUMO_TYPE_OF_CARD s "+
		  " where a.cardTypeNumber=s.code "+
		  " and a.branch = s.branch";
		
		
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			init(c);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				customer = getCustomer(rs);
				 HUMO_BINCOD = rs.getString("bin");
				 HUMO_CHIPAPPID= rs.getLong("CHIP_APP_ID");
				 HUMO_BRANCHID = rs.getString("BRANCH_ID");
				 HUMO_RANGEID = rs.getLong("RANGE_ID");
				
				
				
				
				if (rs.getInt("client")==0){
//				    createCardsHumo(c,"bank9004",rs.getString("account_hco"),
//				    		rs.getString("branch_hco"), rs.getString("client_hco"), rs.getString("code_s"),
//							"123",rs.getString("short_name2"), 	 customer,rs.getString("card_type"));
//
			ISLogger.getLogger().error("Для этого клиента такой карта не открывался:"+"МФО:"+rs.getString("branch_hco")+" ClientId:"+rs.getString("client_hco")+" Тип карты:"+rs.getString("card_type"));
				
				}else{
					newAgreementHumo( c,rs,"bank9004",
							rs.getString("branch_hco"), rs.getString("client_hco"), rs.getString("code_s"),"123",
							rs.getString("short_name2"),  customer, rs.getString("client"),rs.getString("account_hco"),rs.getString("card_type"));
				}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
       finally{
    	   
    	   c.commit();
    	   ConnectionPool.close(c);
    	   
       }
	
		
	}
	
	public void init( Connection c) throws Exception{
		
		//SS_HUMO_TYPE_OF_CARD cardType=com.is.humo.HumoCardsService.getCardType(vidCard, branch,c);
		// HUMO_BANK_C = cardType.getBank_c();
		// HUMO_GROUPC = cardType.getGroup_c();
		// HUMO_BINCOD = cardType.getBin();
		// HUMO_CHIPAPPID= cardType.getChip_app_id();
		// HUMO_BRANCHID = cardType.getBranch_id();
		// HUMO_RANGEID = cardType.getRange_id();
         HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
         HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
         HUMO_BINCOD = ConnectionPool.getValue("HUMO_BINCOD");

		 psInsertAgreement = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
		 psInsertRange = c.prepareStatement("update HUMO_CARD_OPEN_RENEW set range_id=?, branch_id=?,state_n=?,err_n=?, err_d=?,state_d=?  where client=? and branch=? and card_type=?");
		 psInsertClient = c.prepareStatement("insert into bf_EMPC_clients(client,F_NAMES,CL_TYPE,CLIENT_B,"
					+ "SURNAME,M_NAME,DOC_SINCE,B_DATE,RESIDENT,STATUS,SEX,SERIAL_NO,ID_CARD,R_CITY,R_STREET,R_E_MAILS"
					+ ",R_MOB_PHONE,R_PHONE,R_CNTRY,ISSUED_BY,PERSON_CODE,DOC_TYPE,REC_DATE) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 psNewAgremment = c.prepareStatement("insert into bf_empc_AGREEMENT (agre_nom, client, groupc, bincod, bank_code, branch, bank_c, product) values (?,?,?,?,?,?,?,?)");
		 psInsertHumoCards = c.prepareStatement("insert into humo_cards(CLIENT,CLIENT_b,card,branch,status1,status2,expiry1,expirity2,"
					+ "renew,card_name,mc_name,m_name,stop_cause,renewed_card,design_id,INSTANT,card_acct,tranz_acct,account_no) values"
					+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 psNewAccounts = c.prepareStatement("insert into bf_EMPC_accounts(CLIENT,ACCOUNT_NO,CARD_ACCT,TRANZ_ACCT,AB_EXPIRITY,CREATED_DATE) values"
					+ " (?,?,?,?,?,?)");
		 
		 psUpdateHumoCards = c.prepareStatement("update humo_cards set card=?,expiry1=?,account_no=?,real_card='' "
					+ " where client_b=? and branch=? and card_name like ?"  );

			 issuingPortProxy = initNp();
			 HumoCardsService.initCards(c);
			connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(HUMO_BANK_C);
			connectionInfo.setGROUPC(HUMO_GROUPC);
			
	           bankC = ConnectionPool.getValue("HUMO_BANK_C");
	           groupC = ConnectionPool.getValue("HUMO_GROUPC");
		
		
		
	}
	
	
	

	
	public  ResponsInfo createCardsHumo(Connection c,ResultSet rs,String alias,String acc,
			String branch, String clientId, String cardCode,String personCode,String cardName, Customer	 customer,String cardType) throws Exception {
		
		String cl_type="2";
		//Customer	 customer =  HumoCardsService.getCustomerJ(c, branch,clientId);	
		ResponsInfo responsInfo=new ResponsInfo();
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		 
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(
				com.is.humo.HumoCardsService.getRowType_Customer(customer,personCode,cardCode,cl_type,c,rs));
		
		RowType_AccountInfo accountInfo = new RowType_AccountInfo();

		accountInfo.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_Base(acc,customer.getBranch(),c,rs));
		ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
		ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
		
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		accountsListInfo.value = ltaccounts;
		RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
		
		eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(HUMO_CHIPAPPID));
		
		
		RowType_CardInfo cardInfo = new RowType_CardInfo();
		cardInfo.setLogicalCard(com.is.humo.HumoCardsService.getRowType_CardInfo_LogicalCard(customer,HUMO_RANGEID));
		cardInfo.setPhysicalCard(com.is.humo.HumoCardsService.getRowType_CardInfo_PhysicalCard(customer,cardName));
		cardInfo.setEMV_Data(eMV_Data);
		ListType_CardInfo cards = new ListType_CardInfo();
		cards.setRow(new RowType_CardInfo[] { cardInfo });
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		cardsListInfo.value = cards;

		try {
			String externalSesionId=getExternalSession();
			
			connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

			RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
					com.is.humo.HumoCardsService.getRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,customer));
			System.out.println("Connection new customer"+!c.isClosed());
			OperationResponseInfo orInfo2=null;
			
			////////////////////////////////////////////////////////////////////////////////////
					 orInfo2 = issuingPortProxy
						.newCustomerAndAgreement(connectionInfo, customerInfo,
								customListInfo, agreementInfo, accountsListInfo,
								cardsListInfo);	
			///////////////////////////////////////////////////////////////////////////////////////		 
					 responsInfo.setSuccessful(true);
					 
					 

					 
					 if (orInfo2.getError_description() != null) {
				
				responsInfo.setResponseCode(orInfo2.getResponse_code());
				responsInfo.setErrorDescription(orInfo2.getError_description());
				responsInfo.setErrorAction(orInfo2.getError_action());
				responsInfo.setSuccessful(false);
				
				System.out.println("Ошибка "+responsInfo.getErrorDescription());
				
				
				insertRangAndBranchId(clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,responsInfo.getErrorDescription(),"","0","0");
			} 
			
					 else{
				
				insertNewClient(com.is.humo.HumoCardsService.getRowType_Customer(customer,personCode,cardCode,cl_type,c,rs), customerInfo.value.getCLIENT(),c);
				insertNewClientAgreement(connectionInfo, agreementInfo,c);
				insertNewClientAccounts(accountsListInfo, cardsListInfo, acc,c,"");
				
				
				if(cardName!=null){
					
					cardName=HumoCardsService.toTranslit(cardName);
					cardName.replace("XXX", "");
					cardName=cardName.replace("KHKHKH", "");
				
					cardName=cardName;
			
				
					cardName=cardName.trim();
			if(cardName.length()>24){
					
				cardName=cardName.substring(0,24);
				
				}
				}
				
				
				
				if(isExsistCard(c, clientId, branch, cardName)!=null){
					
					updateHumoCards(cardsListInfo, clientId, branch, c, accountsListInfo, cardName);
					System.out.println("isExsistCard true");
				}
				else{
					insertNewClientHumoCards(cardsListInfo, clientId, customer.getBranch(),alias,acc,c,"",accountsListInfo);
					}
				
				
				responsInfo.setSuccessful(true);
				insertRangAndBranchId(clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"","","0","2");
				 
				
				System.out.println("insert noviy karta");
			}
		
		}
		
		catch (RemoteException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		
		
		}
		return responsInfo;
	}
	
	
	
	  public  ResponsInfo newAgreementHumo(Connection c,ResultSet rs,String alias,
				String branch, String clientId, String cardCode,String personCode,
				String firstCardName, Customer	 customer, String clientIdHumo,String acc,String cardType) throws Exception {
			String	cl_type="2";
				System.out.println("777777newAgreementHumo7777777777777777");
				
				// customer =  HumoCardsService.getCustomerJ(c, branch,clientId);	
				 
			
			
			

			ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
			
			 ResponsInfo responsInfo=new ResponsInfo();
		

			
			try {

				
				String externalSesionId=getExternalSession();
				connectionInfo.setEXTERNAL_SESSION_ID(externalSesionId);

				RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder(
						com.is.humo.HumoCardsService.getNewRowType_Agreement(HUMO_BINCOD,HUMO_BANK_C,HUMO_BRANCHID,cardCode,customer,alias,clientIdHumo));
				RowType_AccountInfo accountInfo = new RowType_AccountInfo();

				accountInfo.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_Base(acc,customer.getBranch(),c,rs));
				ListType_AccountInfo ltaccounts = new ListType_AccountInfo();
				ltaccounts.setRow(new RowType_AccountInfo[] { accountInfo });
				
				ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
				//accountsListInfo.value = ltaccounts;
				ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder(); 
				
				
				OperationResponseInfo operationResponsInfo = issuingPortProxy
				.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
				
			
				
				
				if(operationResponsInfo.getError_description()==null){
					KeyType_Agreement mainAgreementInfo= new KeyType_Agreement();
					mainAgreementInfo.setAGRE_NOM(agreementInfo.value.getAGRE_NOM());
					
					RowType_CardInfo_EMV_Data eMV_Data = new RowType_CardInfo_EMV_Data();
					eMV_Data.setCHIP_APP_ID(BigDecimal.valueOf(HUMO_CHIPAPPID));
					
					RowType_AccountInfo accountInfoDobKarta = new RowType_AccountInfo();

					//accountInfoDobKarta.setBase_info(com.is.humo.HumoCardsService.getRowType_AccountInfo_Base(acc,customer.getBranch(),c));
					accountInfoDobKarta.setBase_info(com.is.humo.HumoCardsService.getRenewRowType_AccountInfo_Base(customer.getId_client(),customer.getBranch(),cardType.trim(),c));
					
					ListType_AccountInfo ltaccountsDobKarta = new ListType_AccountInfo();
					ltaccountsDobKarta.setRow(new RowType_AccountInfo[] { accountInfoDobKarta });
					
					ListType_AccountInfoHolder accountsListInfoDobKarta = new ListType_AccountInfoHolder();
					accountsListInfoDobKarta.value = ltaccountsDobKarta;
					
					RowType_CardInfo cardInfoDobKarta = new RowType_CardInfo();
					cardInfoDobKarta.setLogicalCard(HumoCardsService.getRowType_CardInfo_LogicalCard(customer,HUMO_RANGEID));
					cardInfoDobKarta.setPhysicalCard(HumoCardsService.getRowType_CardInfo_PhysicalCard(customer,firstCardName));
					cardInfoDobKarta.setEMV_Data(eMV_Data);
					ListType_CardInfo cards = new ListType_CardInfo();
					cards.setRow(new RowType_CardInfo[] { cardInfoDobKarta });
					ListType_CardInfoHolder cardsListInfoDobKarta = new ListType_CardInfoHolder();
					cardsListInfoDobKarta.value = cards;
					
					
					
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
					
					System.out.println("****************connectionInfo****************");
					System.out.println(connectionInfo.toString());
					System.out.println("****************mainAgreementInfo****************");
					System.out.println(mainAgreementInfo.toString());
					System.out.println("****************accountsListInfoDobKarta****************");
					System.out.println(accountsListInfoDobKarta.toString());
					System.out.println("****************cardsListInfoDobKarta****************");
					System.out.println(cardsListInfoDobKarta.toString());
					
					OperationResponseInfo operationResponsInfoAdd = issuingPortProxy
					.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfoDobKarta, cardsListInfoDobKarta);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
					
					BigDecimal agrNom=agreementInfo.value.getAGRE_NOM();
					int resClient=0,resAgreement=0,resAccount=0,resCards=0;
					
					//resAgreement=insertAgreement(connectionInfo,agreementInfo,clientIdHumo,agrNom);
					
					
					if(operationResponsInfoAdd.getError_description()==null){
					
					
					responsInfo.setSuccessful(true);
					
					System.out.println("New Agreement создан:"+agreementInfo.value.getAGRE_NOM()+" client: "+agreementInfo.value.getCLIENT());
					
					insertRangAndBranchId( clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"","","2","1");
					
					insertNewClientAgreement(connectionInfo, agreementInfo,c);
					insertNewClientAccounts(accountsListInfoDobKarta, cardsListInfoDobKarta, acc,c,clientIdHumo);
					
					if(firstCardName!=null){
						
						firstCardName=HumoCardsService.toTranslit(firstCardName);
						firstCardName.replace("XXX", "");
						firstCardName=firstCardName.replace("KHKHKH", "");
					
						firstCardName=firstCardName;
				
					
						firstCardName=firstCardName.trim();
				if(firstCardName.length()>24){
						
					firstCardName=firstCardName.substring(0,24);
					
					}
					}
					
					
					//String pan=isExsistCard(c, clientId, branch, firstCardName);
					String pan=isExsistCard(c, clientId, branch, cardType);
					
					System.out.println("ExsistCard:"+clientId+" "+branch+" "+firstCardName+" "+pan);
					ISLogger.getLogger().info("ExsistCard:"+clientId+" "+branch+" "+firstCardName+" "+pan);
					
					
					if(pan!=null){
						ISLogger.getLogger().info("update card");
						System.out.println("startStopList");
						HumoCardsService.addStopList(pan, issuingPortProxy, bankC, groupC);
						System.out.println("endStopList");
						System.out.println("StartUpdate");
						updateHumoCards(cardsListInfoDobKarta, clientId, branch, c, accountsListInfoDobKarta, cardType);
						System.out.println("isExsistCard true");
						
					
					}
					else{
						ISLogger.getLogger().info("insert card");
						//insertNewClientHumoCards(cardsListInfoDobKarta, clientId, customer.getBranch(),alias,acc,c,"",accountsListInfoDobKarta);
						insertNewClientHumoCards(cardsListInfoDobKarta, clientId, customer.getBranch(),alias,acc,c,clientIdHumo,accountsListInfoDobKarta);
						
						}
					
					responsInfo.setSuccessful(true);
					System.out.println("insert dob karta");
					
					}else{
						System.out.println("ERROR addInfo4Agreement111:"+operationResponsInfoAdd.getError_description());
						insertRangAndBranchId( clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"",operationResponsInfoAdd.getError_description(),"0","0");
					}
				}
//				
				else {
					System.out.println("EROR newAgreement222:"+operationResponsInfo.getError_description());
					insertRangAndBranchId( clientId, branch, HUMO_BRANCHID, HUMO_RANGEID.toString(),cardType.trim(),c,"",operationResponsInfo.getError_description(),"0","0");
				}
			
			}
			
			catch (RemoteException e) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();
			
			
			}
			return responsInfo;
		}
	
	
	
	
	private static globus.IssuingWS.IssuingPortProxy initNp()throws ClientProtocolException, IOException,
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
		ConnectionPool.getValue("HUMO_HOST"),
		ConnectionPool.getValue("HUMO_USERNAME"),
		ConnectionPool.getValue("HUMO_PASSWORD"));
}

	private Customer getCustomer(ResultSet rs) throws SQLException{
		
		Customer customer = new Customer(rs.getLong("id"),
				rs.getString("branch"), 
				rs.getString("client_hco"),
				rs.getString("short_name2"), 
				"",//rs.getString("code_country"),
				"",//rs.getString("code_type"),
				"",//rs.getString("code_resident"),
				"",//rs.getString("code_subject"),
				0,//rs.getInt("sign_registr"), 
				"",//rs.getString("code_form"),
				null,//rs.getDate("open"), 
				null,//rs.getDate("close"),
				0,//rs.getInt("main_state"), 
				null,//rs.getDate("DIRECTOR_BIRTHDAY"),
				"",//rs.getString("DIRECTOR_ADDRESS"),
				"",//rs.getString("DIRECTOR_TYPE_DOCUMENT"),
				"",//rs.getString("DIRECTOR_PASSP_SERIAL"),
				"",//rs.getString("DIRECTOR_PASSP_NUMBER"),
				"",//rs.getString("DIRECTOR_PASSP_PLACE_REG"),
				null,//rs.getDate("DIRECTOR_PASSP_DATE_REG"),
				"",//rs.getString("code_tax_org"),
				"",//rs.getString("number_tax_registration"),
				"",//rs.getString("code_bank"),
				"",//rs.getString("code_class_credit"),
				"",//rs.getString("DIRECTOR_CODE_CITIZENSHIP"),
				"",//rs.getString("DIRECTOR_BIRTH_PLACE"),
				null,//p_code_capacity
				null,//p_capacity_status_date
				null,//p_capacity_status_place
				null,//p_num_certif_capacity
				rs.getString("PHONE"),
				null,//p_phone_mobile
				rs.getString("email"),
				null,//p_pension_sertif_serial
				null,//p_code_gender
				null,//p_code_nation
				"",//rs.getString("DIRECTOR_BIRTH_PLACE"),
				"",//rs.getString("DIRECTOR_ADDRESS"),//p_code_birth_distr
				null,//p_type_document
				null,//rs.getDate("DIRECTOR_PASSP_DATE_END"),
				"",//rs.getString("DIRECTOR_ADDRESS"),
				"",//rs.getString("DIRECTOR_ADDRESS"),//p_code_adr_distr
				null, //p_inps
				null,//p_family
				rs.getString("DIRECTOR_NAME2"),//p_first_name
				null);
		return customer;
	}
	
	
	
	  public  void insertNewClientAccounts(
				ListType_AccountInfoHolder accountsListInfo,
				ListType_CardInfoHolder cardsListInfo, String acc,Connection c,String clientIdHumo) {
			
			try {
				
				

				
				

				psNewAccounts.setString(1, clientIdHumo==""?cardsListInfo.value.getRow(0).getLogicalCard()
						.getCLIENT():clientIdHumo);
				psNewAccounts.setBigDecimal(2, accountsListInfo.value.getRow(0).getBase_info()
						.getACCOUNT_NO()== null ? null : accountsListInfo.value.getRow(0).getBase_info()
								.getACCOUNT_NO());
//				ps.setString(3, accountsListInfo.value.getRow(0).getBase_info()
//						.getCARD_ACCT()== null ? "" : accountsListInfo.value.getRow(0).getBase_info()
//								.getCARD_ACCT());
				
				psNewAccounts.setString(3, acc== null ? "" : acc);
				
				psNewAccounts.setString(4, acc== null ? "" : acc);

				psNewAccounts.setDate(5, accountsListInfo.value.getRow(0)
						.getBase_info().getAB_EXPIRITY()== null ? null : new java.sql.Date(accountsListInfo.value.getRow(0)
								.getBase_info().getAB_EXPIRITY().getTimeInMillis()));
				psNewAccounts.setDate(6, accountsListInfo.value.getRow(0)
						.getBase_info().getCREATED_DATE()== null ? null : new java.sql.Date(accountsListInfo.value.getRow(0)
								.getBase_info().getCREATED_DATE().getTimeInMillis()));
	            
				psNewAccounts.execute();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));

			}
		}

		public  void insertNewClientHumoCards(ListType_CardInfoHolder cardsListInfo,
				String clientId, String branch, String alias,String acc,Connection c,String clientIdHumo,ListType_AccountInfoHolder accountsListInfo) {
	
			try {
		
				psInsertHumoCards.setString(1, clientIdHumo==""?cardsListInfo.value.getRow(0).getLogicalCard()
									.getCLIENT():clientIdHumo);
				psInsertHumoCards.setString(2, clientId== null ? "" : clientId);
				psInsertHumoCards.setString(3, cardsListInfo.value.getRow(0).getLogicalCard()
						.getCARD()== null ? "" : cardsListInfo.value.getRow(0).getLogicalCard()
								.getCARD());
				psInsertHumoCards.setString(4, branch== null ? "" : branch);
				psInsertHumoCards.setString(5, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getSTATUS1()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getSTATUS1());
				psInsertHumoCards.setString(6, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getSTATUS2()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getSTATUS2());
				psInsertHumoCards.setDate(7,cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
								.getPhysicalCard().getEXPIRY1().getTimeInMillis()));
				psInsertHumoCards.setDate(8, cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRITY2()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
						.getPhysicalCard().getEXPIRITY2().getTimeInMillis()));
				
				
				
				
				psInsertHumoCards.setString(9, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getRENEW()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getRENEW());
				
				String psevdaPAN=cardsListInfo.value.getRow(0).getPhysicalCard()
				.getCARD_NAME()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
						.getCARD_NAME();
				psInsertHumoCards.setString(10,psevdaPAN);
//				ps.setString(11, cardsListInfo.value.getRow(0).getPhysicalCard()
//						.getMC_NAME()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
//								.getMC_NAME());
//				ps.setString(12, cardsListInfo.value.getRow(0).getPhysicalCard()
//						.getMC_NAME()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
//								.getMC_NAME());
				
				psInsertHumoCards.setString(11, "");
				psInsertHumoCards.setString(12, "");
				
				psInsertHumoCards.setString(13, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getSTOP_CAUSE()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getSTOP_CAUSE());
				psInsertHumoCards.setString(14, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getRENEWED_CARD()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getRENEWED_CARD());
				psInsertHumoCards.setBigDecimal(15, cardsListInfo.value.getRow(0)
						.getPhysicalCard().getDESIGN_ID()== null ? null : cardsListInfo.value.getRow(0)
								.getPhysicalCard().getDESIGN_ID());
				psInsertHumoCards.setString(16, cardsListInfo.value.getRow(0).getPhysicalCard()
						.getINSTANT()== null ? "" : cardsListInfo.value.getRow(0).getPhysicalCard()
								.getINSTANT());
				
				psInsertHumoCards.setString(17, acc);
				psInsertHumoCards.setString(18,acc);
				psInsertHumoCards.setBigDecimal(19,accountsListInfo.value.getRow(0).getBase_info()
						.getACCOUNT_NO()== null ? null : accountsListInfo.value.getRow(0).getBase_info()
								.getACCOUNT_NO());
				
				
				//ps.setString(17,getRealCardNumber(psevdaPAN, initNp(alias), HUMO_BANK_C, HUMO_GROUPC));


				psInsertHumoCards.execute();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} 
		}

		public  void insertNewClientAgreement(OperationConnectionInfo connectionInfo,
				RowType_AgreementHolder agreementInfo,Connection c) {
		
			try {
			
				psNewAgremment.setString(1, agreementInfo.value.getAGRE_NOM().toString()== null ? "" : agreementInfo.value.getAGRE_NOM().toString());
				psNewAgremment.setString(2, agreementInfo.value.getCLIENT()== null ? "" : agreementInfo.value.getCLIENT());
				psNewAgremment.setString(3, connectionInfo.getGROUPC()== null ? "" : connectionInfo.getGROUPC());
				psNewAgremment.setString(4, agreementInfo.value.getBINCOD()== null ? "" : agreementInfo.value.getBINCOD());
				psNewAgremment.setString(5, agreementInfo.value.getBANK_CODE()== null ? "" : agreementInfo.value.getBANK_CODE());
				psNewAgremment.setString(6, agreementInfo.value.getBRANCH()== null ? "" : agreementInfo.value.getBRANCH());
				psNewAgremment.setString(7, connectionInfo.getBANK_C()== null ? "" : connectionInfo.getBANK_C());
				psNewAgremment.setString(8, agreementInfo.value.getPRODUCT()== null ? "" : agreementInfo.value.getPRODUCT());
				psNewAgremment.execute();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} 
		}

		public  void insertNewClient(RowType_Customer customerInfo, String client,Connection c)
				throws Exception {
		
			boolean bool = false;
			try {
			
				
				psInsertClient.setString(1, client== null ? "" : client);
				psInsertClient.setString(2, customerInfo.getF_NAMES()== null ? "" : customerInfo.getF_NAMES());
				psInsertClient.setString(3, customerInfo.getCL_TYPE()== null ? "" : customerInfo.getCL_TYPE());
				psInsertClient.setString(4, customerInfo.getCLIENT_B()== null ? "" : customerInfo.getCLIENT_B());
				psInsertClient.setString(5, customerInfo.getSURNAME()== null ? "" : customerInfo.getSURNAME());
				psInsertClient.setString(6, customerInfo.getM_NAME()== null ? "" : customerInfo.getM_NAME());
				psInsertClient.setDate(7, customerInfo.getDOC_SINCE()== null ? null : new java.sql.Date(customerInfo.getDOC_SINCE()
								.getTimeInMillis()));
				psInsertClient.setDate(8, customerInfo.getB_DATE()== null ? null : new java.sql.Date(customerInfo.getB_DATE()
								.getTimeInMillis()));
				psInsertClient.setString(9, customerInfo.getRESIDENT()== null ? "" : customerInfo.getRESIDENT());
				psInsertClient.setString(10, customerInfo.getSTATUS()== null ? "" : customerInfo.getSTATUS());
				psInsertClient.setString(11, customerInfo.getSEX()== null ? "" : customerInfo.getSEX());
				psInsertClient.setString(12, customerInfo.getSERIAL_NO()== null ? "" : customerInfo.getSERIAL_NO());
				psInsertClient.setString(13, customerInfo.getID_CARD()== null ? "" : customerInfo.getID_CARD());
				psInsertClient.setString(14, customerInfo.getR_CITY()== null ? "" : customerInfo.getR_CITY());
				psInsertClient.setString(15, customerInfo.getR_STREET()== null ? "" : com.is.humo.HumoCardsService.toTranslit(customerInfo.getR_STREET()));
				psInsertClient.setString(16, customerInfo.getR_E_MAILS()== null ? "" : customerInfo.getR_E_MAILS());
				psInsertClient.setString(17, customerInfo.getR_MOB_PHONE()== null ? "" : customerInfo.getR_MOB_PHONE());
				psInsertClient.setString(18, customerInfo.getR_PHONE()== null ? "" : customerInfo.getR_PHONE());
				psInsertClient.setString(19, customerInfo.getR_CNTRY()== null ? "" : customerInfo.getR_CNTRY());
				psInsertClient.setString(20, customerInfo.getISSUED_BY()== null ? "" : customerInfo.getISSUED_BY());
				psInsertClient.setString(21, customerInfo.getPERSON_CODE()== null ? "" : customerInfo.getPERSON_CODE());
				psInsertClient.setString(22, customerInfo.getDOC_TYPE()== null ? "" : customerInfo.getDOC_TYPE());
				// ps.setDate(23, customerInfo.value.getREC_DATE().getTime() == null
				// ? null
				// : new java.sql.Date(customerInfo.value
				// .getREC_DATE().getTime().getTime()));
				psInsertClient.setDate(23, customerInfo.getREC_DATE()== null ? null : new java.sql.Date(customerInfo.getREC_DATE()
								.getTimeInMillis()));

				psInsertClient.execute();
				c.commit();
				bool = true;
			
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
		
		public  int insertAgreement(OperationConnectionInfo connectionInfo,RowType_AgreementHolder agreementInfo,
				String clintIdHumo,BigDecimal agrNom, Connection c) {
	
			int res=0;
			
				
			try {
				
				
				
				System.out.println("agrNom: "+agrNom.toString());
				psInsertAgreement.setString(1, agrNom.toString());
				
				
				//ps.setString(2, agreementInfo.value.getCLIENT()== null ? "0" : agreementInfo.value.getCLIENT());
				psInsertAgreement.setString(2, clintIdHumo);
				System.out.println("9999cliintIdHumo: "+clintIdHumo);
				psInsertAgreement.setString(3, connectionInfo.getGROUPC()== null ? "" : connectionInfo.getGROUPC());
				psInsertAgreement.setString(4, agreementInfo.value.getBINCOD()== null ? "0" : agreementInfo.value.getBINCOD());
				psInsertAgreement.setString(5, agreementInfo.value.getBANK_CODE()== null ? "0" : agreementInfo.value.getBANK_CODE());
				psInsertAgreement.setString(6, agreementInfo.value.getBRANCH()== null ? "0" : agreementInfo.value.getBRANCH());
				psInsertAgreement.setString(7, connectionInfo.getBANK_C()== null ? "0" : connectionInfo.getBANK_C());
				psInsertAgreement.setString(8, agreementInfo.value.getPRODUCT()== null ? "0" : agreementInfo.value.getPRODUCT());
				res=psInsertAgreement.executeUpdate();
				c.commit();
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(e));
				
			}
			
			return res;
		}


	
		public  void insertRangAndBranchId(String clientId,String branch,String branchId,String rangeId,String cardType,Connection c,String err_n,String err_d,String state_d,String state_n){
			
			
			int res=0;
		
			try {
		
				
				psInsertRange.setString(1, rangeId);
				psInsertRange.setString(2,branchId);
				psInsertRange.setString(3,state_n);
				psInsertRange.setString(4,err_n);
				psInsertRange.setString(5,err_d);
				psInsertRange.setString(6,state_d);
				psInsertRange.setString(7,clientId);
				psInsertRange.setString(8,branch);
				psInsertRange.setString(9,cardType);
				
				System.out.println("clientId:"+clientId+" branch:"+branch+" cardType:"+cardType);
				
				
		//	
		        
				res=psInsertRange.executeUpdate();
				c.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			} 
			
			
			
			
		}
	
	public static String isExsistCard(Connection c,String clientB, String mfo, String cardType){
		
		String pan=null;
		PreparedStatement ps=null;
		boolean isExsestCard=false;

		try {
			
			ps = c.prepareStatement("SELECT * FROM HUMO_CARDS where client_B=? and branch=? and card_name like = ?");
			ps.setString(1, clientB);
			ps.setString(2, mfo);
			ps.setString(3,cardType+"%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isExsestCard=true;
				pan=rs.getString("card");
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			//ConnectionPool.close(c);
		}
		return pan;
		
		
		
	}

	public  void updateHumoCards(ListType_CardInfoHolder cardsListInfo,
			String clientId, String branch,Connection c,ListType_AccountInfoHolder accountsListInfo,String cardType) {

		try {
	
			psUpdateHumoCards.setString(1, cardsListInfo.value.getRow(0).getLogicalCard()
					.getCARD()== null ? "" : cardsListInfo.value.getRow(0).getLogicalCard()
							.getCARD());
			
			
			psUpdateHumoCards.setDate(2,cardsListInfo.value.getRow(0).getPhysicalCard().getEXPIRY1()== null ? null : new java.sql.Date(cardsListInfo.value.getRow(0)
							.getPhysicalCard().getEXPIRY1().getTimeInMillis()));
			
			//System.out.println("EXPIRY1: "+new java.sql.Date(cardsListInfo.value.getRow(0)
			//		.getPhysicalCard().getEXPIRY1().getTimeInMillis()));
					
			psUpdateHumoCards.setBigDecimal(3,accountsListInfo.value.getRow(0).getBase_info()
					.getACCOUNT_NO()== null ? null : accountsListInfo.value.getRow(0).getBase_info()
							.getACCOUNT_NO());
			 
			psUpdateHumoCards.setString(4,  clientId);
			psUpdateHumoCards.setString(5,  branch);
			psUpdateHumoCards.setString(6,  cardType+"%");
			psUpdateHumoCards.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} 
	}
	public static String getExternalSession() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		return dateFormat.format(new Date());
	}
}
