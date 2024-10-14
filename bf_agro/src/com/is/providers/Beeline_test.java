package com.is.providers;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.is.ConnectionPool;
import com.is.providers.AddInfo;
import com.is.providers.CheckTrResp;
import com.is.providers.Credentials;
import com.is.providers.PayResp;
import com.is.providers.Payment;
import com.is.providers.Res;
import com.is.utils.CheckNull;
import com.is.utils.NilProvider;

public class Beeline_test extends BaseProvider{
	private SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss"); 
	private SimpleDateFormat dparse = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private SimpleDateFormat dff = new SimpleDateFormat("dd.MM.yyyy");
	private NilProvider np = null;
	private HashMap<String,Integer> responces = new HashMap<String, Integer>()
			{
			    {
					put("-83", 8);//	В биллинговой системе по чеку найдено более одного платежа
			        put("-82", 10);//	Платеж не найден в биллинговой системе
			        put("-60", 8);//	Отказано в смене пароля
			        put("-59", 7);//	Платежная система недоступна.
			        put("-51", 8);//	Ошибка сценария
			        put("-50", 8);//	Ошибка выполнения запроса в БД
			        put("-49", 8);//	Сбой биллинговой системы
			        put("-48", 14);//	Превышено количество попыток платежа
			        put("-46", 9);//	Платеж зарегистрирован, но не проведен
			        put("-41", 5);//	Прием платежей для абонента невозможен
			        put("-40", 4);//	Абонент не найден
			        put("-38", 14);//	Превышено количество попыток проведения платежа
			        put("-35", 13);//	Квота платежей исчерпана
			        put("-34", 13);//	Прием платежей от партнера заблокирован
			        put("-32", 13);//	Неизвестный тип источника платежа
			        put("-31", 3);//	Сумма платежа вне диапазона, заданного для источника платежа
			        put("-27", 10);//	Транзакция не найдена
			        put("-26", 14);//	Операция недопустима для данной транзакции
			        put("-25", 13);//	Доступ запрещен
			        put("-20", 14);//	Создание транзакции невозможно
			        put("-19", 15);//	Неверный формат суммы платежа
			        put("-18", 15);//	Неверный формат даты платежа
			        put("-17", 1);//	Не задан пароль
			        put("-16", 15);//	Задан недопустимый параметр
			        put("-15", 15);//	Не задан идентификатор транзакции
			        put("-14", 15);//	Валюта не поддерживается
			        put("-13", 15);//	Не задана сумма платежа
			        put("-12", 4);//	Номер получателя платежа не задан или неверен
			        put("-11", 14);//	Действие не предусмотрено
			        put("-10", 15);//	Не задан номер чека
			        put("-3", 1); //	Неверный логин/пароль
			        put("-2", 13); //	Доступ с данного IP не предусмотрен
			        put("20", 0); //	Транзакция создана
			        put("21", 0); //	Платеж возможен
			        put("22", 0); //	Платеж подтвержден
			        put("23", 0); //	Платеж отменен
			        put("45", 0); //	Платеж проведен успешно
			        put("60", 0); //	Пароль успешно изменен
			        put("70", 0); //	Состояние транзакции определено
			        put("80", 0); //	Аннулирование платежа успешно проведено
			    }
			};
			private HashMap<String,String> beelineResponces = new HashMap<String, String>()
					{
					    {
							put("-83", "В биллинговой системе по чеку найдено более одного платежа");
					        put("-82", "Платеж не найден в биллинговой системе");
					        put("-60", "Отказано в смене пароля");
					        put("-59", "Платежная система недоступна.");
					        put("-51", "Ошибка сценария");
					        put("-50", "Ошибка выполнения запроса в БД");
					        put("-49", "Сбой биллинговой системы");
					        put("-48", "Превышено количество попыток платежа");
					        put("-46", "Платеж зарегистрирован, но не проведен");
					        put("-41", "Прием платежей для абонента невозможен");
					        put("-40", "Абонент не найден");
					        put("-38", "Превышено количество попыток проведения платежа");
					        put("-35", "Квота платежей исчерпана");
					        put("-34", "Прием платежей от партнера заблокирован");
					        put("-32", "Неизвестный тип источника платежа");
					        put("-31", "Сумма платежа вне диапазона, заданного для источника платежа");
					        put("-27", "Транзакция не найдена");
					        put("-26", "Операция недопустима для данной транзакции");
					        put("-25", "Доступ запрещен");
					        put("-20", "Создание транзакции невозможно");
					        put("-19", "Неверный формат суммы платежа");
					        put("-18", "Неверный формат даты платежа");
					        put("-17", "Не задан пароль");
					        put("-16", "Задан недопустимый параметр");
					        put("-15", "Не задан идентификатор транзакции");
					        put("-14", "Валюта не поддерживается");
					        put("-13", "Не задана сумма платежа");
					        put("-12", "Номер получателя платежа не задан или неверен");
					        put("-11", "Действие не предусмотрено");
					        put("-10", "Не задан номер чека");
					        put("-3", "Неверный логин/пароль");
					        put("-2", "Доступ с данного IP не предусмотрен");
					        put("20", "Транзакция создана");
					        put("21", "Платеж возможен");
					        put("22", "Платеж подтвержден");
					        put("23", "Платеж отменен");
					        put("45", "Платеж проведен успешно");
					        put("60", "Пароль успешно изменен");
					        put("70", "Состояние транзакции определено");
					        put("80", "Аннулирование платежа успешно проведено");
					    }
					};
	private HashMap<Integer,String> selfresponces = new SelfResponce().getResponces();
	private Integer errCode = 8;
	public Beeline_test()
	{
		super();
		//this.setEndPoint("http://128.10.10.117:30001/beeline.aspx");
	}
	
	@Override
	public PayResp check(Credentials cr, Payment pay) {
		/*
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(this.getEndPoint());
			sb.append("?ACT=0");
			sb.append("&USERNAME="+cr.getUn());
			sb.append("&PASSWORD="+cr.getPw());
			sb.append("&MSISDN="+pay.getP_number());
			sb.append("&PAY_AMOUNT="+pay.getAmount());
			sb.append("&CURRENCY_CODE=2");//="+pay.getCurrency());
			sb.append("&BRANCH="+pay.getBranch());
			sb.append("&SOURCE_TYPE=1");
			sb.append("&TRADE_POINT=1");
			//sb.append("&RECEIPT_NUM="+pay.getTr_id());
			//sb.append("&PAY_ID="+pay.getId());
			
			URL url = new URL(sb.toString());
            //URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0&PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
			initNp();


			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
				    return true;
				}
			};
		    HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
		    connection.setHostnameVerifier(allHostsValid);
		    connection.setRequestMethod("GET");
		    connection.connect();
		    String strres = getResp(connection.getInputStream(), "cp1251");
			System.out.println("resp : "+strres);
			System.out.println("status_code : "+getRespVal(strres, "status_code"));
			
			//selfresponces
    	    Integer code = responces.get(getRespVal(strres, "status_code"));
    	    HashMap<String,String> ai = new HashMap<String, String>();
    	    ai.put(getRespVal(strres, "status_code"),"status");
    	    getPres().setAddInfo(ai);
    	    this.getRes().setCode(code);
    		this.getRes().setName(selfresponces.get(code));
    		getPres().setRs(getRes());
    		pay.setTr_id(Integer.parseInt(getRespVal(strres, "pay_id")));
    		getPres().setPaym(pay);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
    		this.getRes().setName(selfresponces.get(errCode));
    		getPres().setRs(getRes());
    		//pay.setP_name(selfresponces.get(errCode));
    		getPres().setPaym(pay);
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}		
		*/
		try {
			this.getRes().setCode(0);
			this.getRes().setName(selfresponces.get(0));
			getPres().setRs(getRes());
			pay.setP_name("+998"+pay.getP_number());
			getPres().setPaym(pay);
			HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
			this.getRes().setName(selfresponces.get(errCode));
			getPres().setRs(getRes());
			//pay.setP_name(selfresponces.get(errCode));
			getPres().setPaym(pay);
			HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		return getPres();
	}

	@Override
	public PayResp pay(Credentials cr, Payment pay, HashMap<String, String> addInfo) {
		StringBuffer sb = new StringBuffer();
		StringBuffer strres = new StringBuffer();
		HashMap<String,String> ai = new HashMap<String, String>();
	    try {
			// pay create
    		sb.append(this.getEndPoint());
			sb.append("?ACT=0");
			sb.append("&USERNAME="+cr.getUn());
			sb.append("&PASSWORD="+cr.getPw());
			sb.append("&MSISDN="+pay.getP_number());
			sb.append("&PAY_AMOUNT="+pay.getAmount()/100);
			sb.append("&CURRENCY_CODE=2");//="+pay.getCurrency());
			sb.append("&BRANCH="+pay.getBranch());
			sb.append("&SOURCE_TYPE=1");
			sb.append("&TRADE_POINT=1");
			//sb.append("&RECEIPT_NUM="+pay.getTr_id());
			//sb.append("&PAY_ID="+pay.getId());
			
			URL url = new URL(sb.toString());
            //URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0&PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
			initNp();


			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
				    return true;
				}
			};
			TrustManager[] trustAllCerts = new TrustManager[] {
				    new X509TrustManager() {
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
						
						@Override
						public void checkServerTrusted(X509Certificate[] arg0, String arg1)	throws CertificateException {
						}
						
						@Override
						public void checkClientTrusted(X509Certificate[] arg0, String arg1)	throws CertificateException {
						}
					}
			};
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			
		    HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
		    connection.setHostnameVerifier(allHostsValid);
		    connection.setSSLSocketFactory(sc.getSocketFactory());
		    connection.setRequestMethod("GET");
		    connection.connect();
		    strres.append(getResp(connection.getInputStream(), "cp1251"));
		    ai.put("ACT0_BEELINE_REQ", sb.toString().replace(cr.getPw(), "*********"));
		    ai.put("ACT0_BEELINE_RES", strres.toString());
		    ai.put("ACT0_STATUS_CODE", getRespVal(strres.toString(), "status_code"));
		    ai.put("ACT0_TIME_STAMP", getRespVal(strres.toString(), "time_stamp"));
		    ai.put("PAY_ID", getRespVal(strres.toString(), "pay_id"));
		    ai.put("USERNAME", cr.getUn());
		    ai.put("CLIENT_ID", pay.getP_number());
		    ai.put("PAY_AMOUNT", ""+pay.getAmount());
		    ai.put("CURRENCY_CODE", "2");
		    ai.put("BRANCH", pay.getBranch());
		    ai.put("SOURCE_TYPE", "1");
		    ai.put("TRADE_POINT", "1");
		    PLogger.getLogger().warn(df.format(new Date()) + " beeline req: "+sb.toString().replace(cr.getPw(), "*********") + "/r/n beeline res: "+strres);
			//System.out.println("resp : "+strres);
			//System.out.println("status_code : "+getRespVal(strres, "status_code"));
			
		    //selfresponces
    	    Integer code = responces.get(getRespVal(strres.toString(), "status_code"));
    	    if (code == 0){
	    	    /*
	    	    HashMap<String,String> ai = new HashMap<String, String>();
	    	    ai.put(getRespVal(strres, "status_code"),"status");
	    	    getPres().setAddInfo(ai);
	    	    this.getRes().setCode(code);
	    		this.getRes().setName(selfresponces.get(code));
	    		getPres().setRs(getRes());
	    		*/
	    		pay.setTr_id(Integer.parseInt(getRespVal(strres.toString(), "pay_id")));
	    		//getPres().setPaym(pay);
	    		// pay commit
	    		sb = new StringBuffer();
				sb.append(this.getEndPoint());
				sb.append("?ACT=1");
				sb.append("&USERNAME="+cr.getUn());
				sb.append("&PASSWORD="+cr.getPw());
				//sb.append("&MSISDN="+pay.getP_number());
				//sb.append("&PAY_AMOUNT="+pay.getAmount()+"&CURRENCY_CODE=1");
				//sb.append("&CURRENCY_CODE="+pay.getCurrency());
				//sb.append("&BRANCH="+pay.getBranch());
				//sb.append("&SOURCE_TYPE=1");
				//sb.append("&TRADE_POINT=1");
				//sb.append("&RECEIPT_NUM="+pay.getTr_id());
				sb.append("&PAY_ID="+pay.getTr_id());
				
				url = new URL(sb.toString());
					//URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0 &PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
				initNp();
	
				connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
			    connection.setHostnameVerifier(allHostsValid);
			    connection.setSSLSocketFactory(sc.getSocketFactory());
			    connection.setRequestMethod("GET");
			    connection.connect();
			    strres = new StringBuffer();
				strres.append(getResp(connection.getInputStream(), "cp1251"));
				
				ai.put("ACT1_BEELINE_REQ", sb.toString().replace(cr.getPw(), "*********"));
			    ai.put("ACT1_BEELINE_RES", strres.toString());
			    ai.put("ACT1_STATUS_CODE", getRespVal(strres.toString(), "status_code"));
			    ai.put("ACT1_TIME_STAMP", getRespVal(strres.toString(), "time_stamp"));
			    ai.put("RECEIPT", getRespVal(strres.toString(), "receipt"));
			    
				PLogger.getLogger().warn(df.format(new Date()) + " beeline req: "+sb.toString().replace(cr.getPw(), "*********") + "/r/n beeline res: "+strres);
				//System.out.println("resp : "+strres);
				//System.out.println("status_code : "+getRespVal(strres, "status_code"));
				//selfresponces
	    	    code = responces.get(getRespVal(strres.toString(), "status_code"));
    	    } 
    	    //HashMap<String,String> ai = new HashMap<String, String>();
    	    //ai.put(getRespVal(strres, "status_code"),"status");
    	    getPres().setAddInfo(ai);
    	    this.getRes().setCode(code);
    		this.getRes().setName(selfresponces.get(code)+" ("+beelineResponces.get(getRespVal(strres.toString(), "status_code"))+")");
    		getPres().setRs(getRes());
    		pay.setP_name(pay.getP_number());
    		getPres().setPaym(pay);
		} catch (Exception e) {
			PLogger.getLogger().error(df.format(new Date()) + " beeline req: "+sb.toString().replace(cr.getPw(), "*********") + "/r/n beeline res: "+strres);
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
    		this.getRes().setName(selfresponces.get(errCode)+" ("+strres==null?"":beelineResponces.get(getRespVal(strres.toString(), "status_code"))+")");
    		getPres().setRs(getRes());
    		//pay.setP_name(pay.getP_number());
    		getPres().setPaym(pay);
    		//HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}		
		return getPres();
	}

	@Override
	public PayResp checkTr(Credentials cr, long id) {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(this.getEndPoint());
			sb.append("?ACT=3");
			sb.append("&USERNAME="+cr.getUn());
			sb.append("&PASSWORD="+cr.getPw());
			//sb.append("&MSISDN="+pay.getP_number());
			//sb.append("&PAY_AMOUNT="+pay.getAmount()+"&CURRENCY_CODE=1");
			//sb.append("&CURRENCY_CODE="+pay.getCurrency());
			//sb.append("&BRANCH="+pay.getBranch());
			//sb.append("&SOURCE_TYPE=1");
			//sb.append("&TRADE_POINT=1");
			//sb.append("&RECEIPT_NUM="+pay.getTr_id());
			sb.append("&PAY_ID="+id);
			
			URL url = new URL(sb.toString());
				//URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0 &PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
			initNp();


			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
				    return true;
				}
			};
		    HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
		    connection.setHostnameVerifier(allHostsValid);
		    connection.setRequestMethod("GET");
		    connection.connect();
			
			StringBuffer strres = new StringBuffer();
			strres.append(getResp(connection.getInputStream(), "cp1251"));
			PLogger.getLogger().warn(df.format(new Date()) + " beeline req: "+sb.toString().replace(cr.getPw(), "*********") + "/r/n beeline res: "+strres);
			System.out.println("resp : "+strres);
			System.out.println("status_code : "+getRespVal(strres.toString(), "status_code"));
			
			//selfresponces
    	    Integer code = responces.get(getRespVal(strres.toString(), "status_code"));
    	    HashMap<String,String> ai = new HashMap<String, String>();
    	    ai.put(getRespVal(strres.toString(), "status_code"),"status");
    	    getPres().setAddInfo(ai);
    	    this.getRes().setCode(code);
    		this.getRes().setName(selfresponces.get(code));
    		getPres().setRs(getRes());
    		//getPres().setPaym(pay);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
    		this.getRes().setName(selfresponces.get(errCode));
    		getPres().setRs(getRes());
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}		
		return getPres();
	}

	public PayResp cancelTr(Credentials cr, long id) {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append(this.getEndPoint());
			sb.append("?ACT=2");
			sb.append("&USERNAME="+cr.getUn());
			sb.append("&PASSWORD="+cr.getPw());
			//sb.append("&MSISDN="+pay.getP_number());
			//sb.append("&PAY_AMOUNT="+pay.getAmount()+"&CURRENCY_CODE=1");
			//sb.append("&CURRENCY_CODE="+pay.getCurrency());
			//sb.append("&BRANCH="+pay.getBranch());
			//sb.append("&SOURCE_TYPE=1");
			//sb.append("&TRADE_POINT=1");
			//sb.append("&RECEIPT_NUM="+pay.getTr_id());
			sb.append("&PAY_ID="+id);
			
			URL url = new URL(sb.toString());
				//URL url = new URL("http://128.10.10.117:30001/beeline.aspx?ACT=0 &PAY_AMOUNT=500&CURRENCY_CODE=1&MSISDN=901878539&PAY_ID=1&USERNAME=admin&PASSWORD=admin&BRANCH=1&SOURCE_TYPE=1&TRADE_POINT=1&RECEIPT_NUM=1");
			initNp();

			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
				    return true;
				}
			};
		    HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
		    connection.setHostnameVerifier(allHostsValid);
		    connection.setRequestMethod("GET");
		    connection.connect();
			
		    StringBuffer strres = new StringBuffer();
			strres.append(getResp(connection.getInputStream(), "cp1251"));
			PLogger.getLogger().error(df.format(new Date()) + " beeline req: "+sb.toString().replace(cr.getPw(), "*********") + "/r/n beeline res: "+strres);
			System.out.println("resp : "+strres);
			System.out.println("status_code : "+getRespVal(strres.toString(), "status_code"));
			
			//selfresponces
    	    Integer code = responces.get(getRespVal(strres.toString(), "status_code"));
    	    HashMap<String,String> ai = new HashMap<String, String>();
    	    ai.put(getRespVal(strres.toString(), "status_code"),"status");
    	    getPres().setAddInfo(ai);
    	    this.getRes().setCode(code);
    		this.getRes().setName(selfresponces.get(code));
    		getPres().setRs(getRes());
    		//getPres().setPaym(pay);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
    		this.getRes().setName(selfresponces.get(errCode));
    		getPres().setRs(getRes());
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}		
		return getPres();
	}
	
	
	@Override
	public CheckPerResp checkPer(Credentials cr,Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListTrResp listTr(Credentials cr,Date startDate, Date endDate) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean append_to_file = false;
		
		try{
			FileWriter write = new FileWriter( "C:/beeline/ipak_"+dff.format(startDate)+".csv" , append_to_file);
			PrintWriter print_line = new PrintWriter( write );
			String textLine = "";
			
			c = ConnectionPool.getConnection();
            ps = c.prepareStatement("  select 'ipak;'|| "+
									"	       ai1.CLIENT_ID||';'|| "+
									"	       j.AMOUNT/100|| "+
									"	       ';2;'|| "+
									"	       ai2.PAY_ID||';'|| "+
									"	       ai3.RECEIPT||';'|| "+
									"	       ai4.ACT0_TIME_STAMP||';'|| "+
									"	       to_char(j.DATE_COMPLETE,'dd.mm.yyyy HH24:mi:ss')||';'|| "+
									"	       j.ID||';'|| "+
									"	       j.BRANCH_ID||';'|| "+
									"	       '1;'|| "+
									"	       'ipak_'||to_char(j.DATE_COMPLETE,'dd.mm.yyyy') "+ 
									"	from  "+
									"	BF_COM_JPAYMENTS j, "+
									"	(select id, a_value CLIENT_ID from BF_COM_JPAY_ADDINFO where a_key = 'CLIENT_ID') ai1, "+
									"	(select id, a_value PAY_ID from BF_COM_JPAY_ADDINFO where a_key = 'PAY_ID') ai2, "+
									"	(select id, a_value RECEIPT from BF_COM_JPAY_ADDINFO where a_key = 'RECEIPT') ai3, "+
									"	(select id, to_char(to_date(a_value,'dd/mm/yyyy HH24:mi:ss'),'dd.mm.yyyy HH24:mi:ss') ACT0_TIME_STAMP from BF_COM_JPAY_ADDINFO where a_key = 'ACT0_TIME_STAMP') ai4 "+
									"	where j.services_list_id = (select com_ser_id from bf_munis_services_list where name = 'Beeline') and j.state=2 "+
									"	and j.date_complete between ? and ? + 1 "+//to_date('28.01.2014','dd.mm.yyyy') and to_date('28.01.2014','dd.mm.yyyy')+1
									"	and ai1.ID (+) = j.id "+
									"	and ai2.ID (+) = j.id "+
									"	and ai3.ID (+) = j.id "+
									"	and ai4.ID (+) = j.id ");
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
            	textLine = rs.getString(1);
				print_line.printf( "%s" + "%n" , textLine);
			}
			print_line.close();
			print_line.flush();
			
			this.getRes().setCode(0);
    		this.getRes().setName("Ok");
    		//getPres().setRs(getRes());
    		getLtr().setRs(getRes());
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+0, "Ok");
	    	ai.put("file_name", "ipak_"+dff.format(startDate)+".csv");
	    	getPres().setAddInfo(ai);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
    		this.getRes().setName(selfresponces.get(errCode));
    		getPres().setRs(getRes());
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		return getLtr();
	}
	
	private void initNp(){
        if (np==null){
              np = new NilProvider();
          np.init();
        }
    }
	
/*	@Override
	public java.io.File createReestr(Credentials cr,Date startDate, Date endDate) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Boolean append_to_file = false;
		
		try{
			File file = new File("C:/beeline/ipak_"+dff.format(startDate)+".csv");
			if(!file.getParentFile().exists()) {
			     file.getParentFile().mkdirs();
			}
			//new File(System.getProperty("C:/beeline/ipak_"+dff.format(startDate)+".csv")).mkdir();
			FileWriter write = new FileWriter( file , append_to_file);//"C:/beeline/ipak_"+dff.format(startDate)+".csv"
			PrintWriter print_line = new PrintWriter( write );
			String textLine = "";
			
			c = ConnectionPool.getConnection();
            ps = c.prepareStatement("  select 'ipak;'|| "+
									"	       ai1.CLIENT_ID||';'|| "+
									"	       j.AMOUNT/100|| "+
									"	       ';2;'|| "+
									"	       ai2.PAY_ID||';'|| "+
									"	       ai3.RECEIPT||';'|| "+
									"	       ai4.ACT0_TIME_STAMP||';'|| "+
									"	       to_char(j.DATE_COMPLETE,'dd.mm.yyyy HH24:mi:ss')||';'|| "+
									"	       j.ID||';'|| "+
									"	       j.BRANCH_ID||';'|| "+
									"	       '1;'|| "+
									"	       'ipak_'||to_char(j.DATE_COMPLETE,'dd.mm.yyyy') "+ 
									"	from  "+
									"	BF_COM_JPAYMENTS j, "+
									"	(select id, a_value CLIENT_ID from BF_COM_JPAY_ADDINFO where a_key = 'CLIENT_ID') ai1, "+
									"	(select id, a_value PAY_ID from BF_COM_JPAY_ADDINFO where a_key = 'PAY_ID') ai2, "+
									"	(select id, a_value RECEIPT from BF_COM_JPAY_ADDINFO where a_key = 'RECEIPT') ai3, "+
									"	(select id, to_char(to_date(a_value,'dd/mm/yyyy HH24:mi:ss'),'dd.mm.yyyy HH24:mi:ss') ACT0_TIME_STAMP from BF_COM_JPAY_ADDINFO where a_key = 'ACT0_TIME_STAMP') ai4 "+
									"	where j.services_list_id = (select com_ser_id from bf_munis_services_list where name = 'Beeline') and j.state=2 "+
									"	and j.date_complete between ? and ? + 1 "+//to_date('28.01.2014','dd.mm.yyyy') and to_date('28.01.2014','dd.mm.yyyy')+1
									"	and ai1.ID (+) = j.id "+
									"	and ai2.ID (+) = j.id "+
									"	and ai3.ID (+) = j.id "+
									"	and ai4.ID (+) = j.id ");
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
            	textLine = rs.getString(1);
				print_line.printf( "%s" + "%n" , textLine);
			}
			print_line.close();
			print_line.flush();
			
			this.getRes().setCode(0);
    		this.getRes().setName("Ok");
    		//getPres().setRs(getRes());
    		getLtr().setRs(getRes());
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+0, "Ok");
	    	ai.put("file_name", "ipak_"+dff.format(startDate)+".csv");
	    	getPres().setAddInfo(ai);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));PLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
    		this.getRes().setName(selfresponces.get(errCode));
    		getPres().setRs(getRes());
    		HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		return new java.io.File("C:/beeline/ipak_"+dff.format(startDate)+".csv");
	}*/
	
	
}
