
package com.is.providers;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang3.StringUtils;

import ErrorReportElements_pkg.ErrorReportElements;
import RemainderElements_pkg.RemainderElements;
import SupplierReportElements_pkg.SupplierReportElements;

import com.cb.munis.EnergoDivisions;
import com.cb.munis.Errors;
import com.cb.munis.GasDivisions;
import com.cb.munis.GnkDivisions;
import com.cb.munis.Remainder;
import com.cb.munis.Requires;
import com.cb.munis.Settlements;
import com.cb.munis.SupplierGroups;
import com.cb.munis.Suppliers;
import com.cb.munis.WsProxy;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.NilProvider;


public class Munis1 extends BaseProvider{
	private static String url = "http://10.0.50.53:80/cb-munis/wService";
	private static String SignUrl = "http://190.44.130.5/afrmunis/client/";
	private static Boolean proxy = false;
	private static String proxyHost = "128.10.10.210";
	private static String proxyPort = "8080";
	private static String nonProxyHosts = "127.0.0.1|locslhost|128.10.10.111";
	private SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss"); 
	private SimpleDateFormat dparse = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private SimpleDateFormat docdf = new SimpleDateFormat("dd.MM.yyyy");
	private NilProvider np = null;
	private Integer errCode = 8;
	private Errors errors = null;
	private Settlements settlements = null;
	private SupplierGroups supplierGroups = null;
	private Suppliers suppliers= null;
	private EnergoDivisions refEnergoDivisions= null;
	private GasDivisions refGasDivisions = null;
	private GnkDivisions refGNKDivisions= null;
	private Remainder remainders= null;
	private Requires supplierRequest = null;
	private Requires supplierResponse = null;

	public Munis1(){
		super();
		loadReferences();
		url = com.is.munisCB.MunisSrv1.getBF_SETS("MUNIS_URL");
		SignUrl = com.is.munisCB.MunisSrv1.getBF_SETS("MUNIS_SIGN_URL");
		proxy = com.is.munisCB.MunisSrv1.getBF_SETS_Boolean("MUNIS_PROXY");
		if(proxy){
			proxyHost = com.is.munisCB.MunisSrv1.getBF_SETS("MUNIS_PROXY_HOST");
			proxyPort = com.is.munisCB.MunisSrv1.getBF_SETS("MUNIS_PROXY_PORT");
			nonProxyHosts = com.is.munisCB.MunisSrv1.getBF_SETS("MUNIS_NONPROXY_HOSTS");
		}
	}
	
	
	private HashMap<String,Integer> responces = new HashMap<String, Integer>()
			{
			    {
					put("0", 0);//	Успешно
					put("10001", 8);//	Не верно имя файла!
					put("10002", 8);//	Структура файла не верна!
					put("10003", 8);//	Не верно указаны параметры!
					put("10004", 8);//	Работа в системе МУНИС запрещена!
					put("10005", 8);//	Инициатор запроса не определен
					put("10006", 8);//	Биллинговая система Поставщика услуг отклонила платеж!
					put("10007", 8);//	Банк может работать только на прием
					put("10101", 8);//	Ошибка в реквизитах Плательщика
					put("10102", 8);//	Отделение банка (МФО) Плательщика не принадлежит банку Инициатору платежа
					put("10103", 8);//	Ошибка в реквизите Плательщика <Код отделение (МФО)>
					put("10104", 8);//	Ошибка в реквизите Плательщика <Лицевой счет>
					put("10105", 8);//	Ошибка в реквизите Плательщика <Ключ лицевого счета>
					put("10106", 8);//	Ошибка в реквизите Плательщика <Наименование>
					put("10107", 8);//	Ошибка в реквизите Плательщика <Идентификационный номер налогоплательщика (ИНН)>
					put("10111", 8);//	Ошибка в реквизитах Получатель
					put("10112", 8);//	Отделение банка (МФО) Получатель не принадлежит банку
					put("10113", 8);//	Ошибка в реквизите Получателя <Код отделение (МФО)>
					put("10114", 8);//	Ошибка в реквизите Получателя <Лицевой счет>
					put("10115", 8);//	Ошибка в реквизите Получателя <Ключ лицевого счета>
					put("10116", 8);//	Ошибка в реквизите Получателя <Наименование>
					put("10117", 8);//	Ошибка в реквизите Получателя <Идентификационный номер налогоплательщика (ИНН)>
					put("10121", 8);//	Ошибка в реквизитах Документа (Номер,Дата)
					put("10122", 8);//	Ошибка в реквизите Номер документа
					put("10123", 8);//	Ошибка в реквизите Дата документа
					put("10125", 8);//	Ошибка в реквизитах <Детали платежа>
					put("10126", 8);//	Ошибка в реквизите <Код назначение платежа>
					put("10127", 8);//	Ошибка в реквизите <Деталь платежа>
					put("10128", 8);//	Ошибка в валюте платежа
					put("10129", 8);//	Ошибка в сумме платежа
					put("10130", 8);//	Ошибка в коде поставщика
					put("10131", 8);//	Ошибка в коде оплаты
					put("10132", 8);//	Ошибка в реквизитах Субъекта
					put("10133", 5);//	Биллинговая система Поставщика услуг не отвечает
					put("10134", 8);//	Ошибка в биллинговой системе Поставщика услуг
					put("10135", 3);//	Субъект не идентифицирован в биллинговой системе Поставщика услуг
					put("10140", 8);//	Фальсификация электронного бланка
					put("10141", 8);//	Электронный бланк уже обработан системой МУНИС
					put("10142", 8);//	Ошибка в системном номере электронного платежа в банке
					put("10143", 8);//	Запрос уже принят, ждите ответа
					put("10144", 8);//	Платежный документ с системном номере электронного платежа в банке не найден
					put("10145", 8);//	Платежный документ с системном номере электронного бланка не найден
					put("10146", 8);//	Не правильная дата запроса
					put("10150", 8);//	Платежный документ не должен содержать код ошибки в реквизите Подтверждения
					put("10151", 8);//	Дата в реквизите Системная информация не соответствует дате в документе, созданном системой Мунис
					put("10152", 8);//	Дата в реквизите Системная информация должна быть равна текущему дню
					put("10153", 8);//	Элемент Дата в реквизите Системная информация не должен быть пустым
					put("10154", 8);//	Время в реквизите Системная информация не соответствует времени в документе, созданном системой Мунис
					put("10155", 8);//	Элемент время в реквизите Системная информация не должен быть пустым
					put("10156", 8);//	Системный номер документа от Мунис в реквизите Системная информация не является системным номером системы Мунис
					put("10157", 8);//	Системный номер документа от Мунис в реквизите Системная информация не должен быть пустым
					put("10158", 8);//	Системный транзакционный номер от системы Мунис в реквизите Системная информация не должен быть заполнен
					put("10159", 8);//	Системный номер документа от ИАБС КБ в реквизите Системная информация не должен быть пустым
					put("10160", 8);//	Отделение банка (МФО) Плательщика не соответствует МФО в документе, созданном системой Мунис
					put("10161", 8);//	Отделение банка (МФО) Плательщика должно быть указано
					put("10163", 8);//	Счет Плательщика не соответствует счету в документе, созданном системой Мунис
					put("10164", 8);//	Счет Плательщика должен быть указан
					put("10165", 8);//	Наименование счета Плательщика не соответствует наименованию счета в документе, созданном системой Мунис
					put("10166", 8);//	Наименование счета Плательщика должно быть указано
					put("10167", 8);//	ИНН Плательщика не соответствует ИНН в документе, созданном системой Мунис
					put("10168", 8);//	ИНН Плательщика не должно быть пустым и должно содержать 9 цифровых символов
					put("10169", 8);//	Отделение банка (МФО) Получателя не соответствует МФО в документе, созданном системой Мунис
					put("10170", 8);//	Отделение банка (МФО) Получателя должно быть указано
					put("10172", 8);//	Счет Получателя не соответствует счету в документе, созданном системой Мунис
					put("10173", 8);//	Счет Получателя должен быть указан
					put("10174", 8);//	Наименование счета Получателя не соответствует наименованию счета в документе, созданном системой Мунис
					put("10175", 8);//	Наименование счета Получателя должно быть указано
					put("10176", 8);//	ИНН Получателя не соответствует ИНН в документе, созданном системой Мунис
					put("10177", 8);//	ИНН Получателя не должно быть пустым и должно содержать 9 цифровых символов
					put("10178", 8);//	Код в реквизите Назначение платежа не должен быть пустым
					put("10180", 8);//	Текст в реквизите Назначение платежа не должен быть пустым
					put("10181", 8);//	Код валюты в реквизите Сумма платежа не должен быть пустым
					put("10183", 8);//	Элемент Сумма в реквизите Сумма платежа не должен быть пустым
					put("10185", 8);//	Номер документа в Реквизитах документа должен быть указан
					put("10187", 8);//	Элемент Дата документа в Реквизитах документа не должен быть пустым
					put("10188", 8);//	Код поставщика услуг в реквизите Вид оплаты не должен быть пустым
					put("10190", 8);//	Код поставщика услуг в реквизите Вид оплаты не соответствует коду поставщика услуг в документе, созданном системой Мунис
					put("10191", 8);//	Код вида оплаты в реквизите Вид оплаты не должен быть пустым
					put("10193", 8);//	Код вида оплаты в реквизите Вид оплаты не соответствует коду вида оплаты в документе, созданном системой Мунис
					put("10194", 8);//	Элементы из реквизита Информация о плательщике для поставщика услуг при идентификации не соответствует справочнику
					put("10195", 8);//	Элементы из реквизита Информация о плательщике для поставщика услуг при идентификации не соответствуют элементам в документе, созданном системой Мунис
					put("10196", 8);//	Элементы из реквизита Информация о плательщике от поставщика услуг не соответствует справочнику
					put("10197", 8);//	Элементы из реквизита Информация о плательщике от поставщика услуг не соответствуют элементам в документе, созданном системой Мунис
					put("10198", 8);//	Элемент блока электронно-цифровой подписи должен быть заполнен
					put("10199", 8);//	Элемент блока электронно-цифровой подписи не соответствует контейнеру с цифровой подписью
					put("10200", 8);//	Цифровая подпись не банка отправителя
					put("10201", 8);//	Контейнер с цифровой подписью поврежден
					put("10202", 8);//	Цифровая подпись не идентифицируется
					put("10203", 8);//	Владелец цифровой подписи не соответствует инициатору платежного документа
					put("10204", 8);//	Банк-получатель не отвечает
					put("10999", 8);//	Не определенная ошибка
					
			    }
			};
	private HashMap<Integer,String> selfresponces = new SelfResponce().getResponces();
	
	private subjectinfo_pkg.Subjectinfo[] addElement(subjectinfo_pkg.Subjectinfo[] org, subjectinfo_pkg.Subjectinfo added) {
		subjectinfo_pkg.Subjectinfo[] result = Arrays.copyOf(org, org.length +1);
	    result[org.length] = added;
	    return result;
	}
	
	private HashMap<String,String> blankToAddInfo(com.cb.munis.Blank blank){
		HashMap<String,String> ai = new HashMap<String, String>();
    	ai.put("confirm_code",blank.getConfirm().getCode());
    	ai.put("confirm_message",blank.getConfirm().getMessage());
    	ai.put("amount_currency",blank.getAmount().getCurrency());
    	ai.put("amount_value",blank.getAmount().getValue());
    	ai.put("document_docd",blank.getDocument().getDoc_d());
    	ai.put("document_docn",blank.getDocument().getDoc_n());
    	ai.put("payer_account",blank.getPayer().getAccount());
    	ai.put("payer_branch",blank.getPayer().getBranch());
    	ai.put("payer_inn",blank.getPayer().getInn());
    	ai.put("payer_name",blank.getPayer().getName());
    	ai.put("payee_account",blank.getPayee().getAccount());
    	ai.put("payee_branch",blank.getPayee().getBranch());
    	ai.put("payee_inn",blank.getPayee().getInn());
    	ai.put("payee_name",blank.getPayee().getName());
    	ai.put("purpose_code",blank.getPurpose().getCode());
    	ai.put("purpose_text",blank.getPurpose().getText());
    	ai.put("settlement_code",blank.getSettlement().getCode());
    	ai.put("settlement_supplier",blank.getSettlement().getSupplier());
    	ai.put("sysinfo_bid",blank.getSysinfo().getBid());
    	ai.put("sysinfo_sid",blank.getSysinfo().getSid());
    	ai.put("sysinfo_tid",blank.getSysinfo().getTid());
    	ai.put("sysinfo_data",blank.getSysinfo().getData());
    	ai.put("sysinfo_time",blank.getSysinfo().getTime());
    	ai.put("sysinfo_hash",blank.getSysinfo().getHash());
    	if(blank.getSubject() != null){
	    	for (int i = 0; i < blank.getSubject().length; i++){
	    		if(blank.getSubject()[i].getElement() != null){
	    			ai.put("subject_"+StringUtils.leftPad(String.valueOf(i), 3, "0")+"_"+blank.getSubject()[i].getElement(),blank.getSubject()[i].getValue());
	    		}
	    	}
    	}
    	if(blank.getInfoservice() != null){
	    	for (int i = 0; i < blank.getInfoservice().length; i++){
	    		if(blank.getInfoservice()[i].getElement() != null){
	    			ai.put("infoservice_"+StringUtils.leftPad(String.valueOf(i), 3, "0")+"_"+blank.getInfoservice()[i].getElement(),blank.getInfoservice()[i].getValue());
	    		}
	    	}
    	}
		return ai;
	}
	
	private com.cb.munis.Blank addInfoToBlank(HashMap<String,String> ai){
		com.cb.munis.Blank blank = new com.cb.munis.Blank();
		com.cb.munis.Confirm conf = new com.cb.munis.Confirm();
		conf.setCode(ai.get("confirm_code"));
		conf.setMessage(ai.get("confirm_message"));
		blank.setConfirm(conf);
		com.cb.munis.Amount a = new com.cb.munis.Amount();
		a.setCurrency(ai.get("amount_currency"));
		a.setValue(ai.get("amount_value"));
		blank.setAmount(a);
		com.cb.munis.Document d = new com.cb.munis.Document();
		d.setDoc_d(ai.get("document_docd"));
		d.setDoc_n(ai.get("document_docn"));
		blank.setDocument(d);
		com.cb.munis.Payer pr = new com.cb.munis.Payer();
		pr.setAccount(ai.get("payer_account"));
		pr.setBranch(ai.get("payer_branch"));
		pr.setInn(ai.get("payer_inn"));
		pr.setName(ai.get("payer_name"));
		blank.setPayer(pr);
		com.cb.munis.Payee pe = new com.cb.munis.Payee();
		pe.setAccount(ai.get("payee_account"));
		pe.setBranch(ai.get("payee_branch"));
		pe.setInn(ai.get("payee_inn"));
		pe.setName(ai.get("payee_name"));
		blank.setPayee(pe);
		com.cb.munis.Purpose p = new com.cb.munis.Purpose();
		p.setCode(ai.get("purpose_code"));
		p.setText(ai.get("purpose_text"));
		blank.setPurpose(p);
		com.cb.munis.Settlement s = new com.cb.munis.Settlement();
		s.setCode(ai.get("settlement_code"));
		s.setSupplier(ai.get("settlement_supplier"));
		blank.setSettlement(s);
		com.cb.munis.Sysinfo si = new com.cb.munis.Sysinfo();
		si.setBid(ai.get("sysinfo_bid"));
		si.setSid(ai.get("sysinfo_sid"));
		si.setTid(ai.get("sysinfo_tid"));
		si.setData(ai.get("sysinfo_data"));
		si.setTime(ai.get("sysinfo_time"));
		si.setHash(ai.get("sysinfo_hash"));
		blank.setSysinfo(si);
		subjectinfo_pkg.Subjectinfo[] subj = {};
		subjectinfo_pkg.Subjectinfo[] inf = {};
		subjectinfo_pkg.Subjectinfo sub = null;
		int is = 0;
		int ii = 0;
		
		for (int keyid = 0; keyid < ai.size(); keyid++){
			for ( String key : ai.keySet() ) {
			    if(key.contains("subject_"+StringUtils.leftPad(String.valueOf(is), 3, "0")+"_")){
			    	sub = new subjectinfo_pkg.Subjectinfo();
			    	sub.setElement(key.substring(12));
			    	sub.setValue(ai.get(key));
			    	subj = addElement(subj, sub);
			    	is++;
			    }
			    if(key.contains("infoservice_"+StringUtils.leftPad(String.valueOf(ii), 3, "0")+"_")){
			    	sub = new subjectinfo_pkg.Subjectinfo();
			    	sub.setElement(key.substring(16));
			    	sub.setValue(ai.get(key));
			    	inf = addElement(inf, sub);
			    	ii++;
			    }
			}
		}
		blank.setInfoservice(inf);
		blank.setSubject(subj);
		return blank;
	}
	
	private com.cb.munis.Blank paymentToBlank(com.cb.munis.Payment payment){
		com.cb.munis.Blank blank = new com.cb.munis.Blank();
		blank.setAmount(payment.getAmount());
		blank.setConfirm(payment.getConfirm());
		blank.setDocument(payment.getDocument());
		blank.setInfoservice(payment.getInfoservice());
		blank.setPayee(payment.getPayee());
		blank.setPayer(payment.getPayer());
		blank.setPurpose(payment.getPurpose());
		blank.setSettlement(payment.getSettlement());
		blank.setSubject(payment.getSubject());
		blank.setSysinfo(payment.getSysinfo());
    	return blank;
	}
	
	public static String getDsign(com.cb.munis.Payment payment) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		StringHolder dsign = new StringHolder();
		StringHolder error_code = new StringHolder();
		StringHolder error_desc = new StringHolder();
		try{
			 
			org.cbru.munafr.authsrv.CBRUAfrMunAuthClientSoapProxy signStub = new org.cbru.munafr.authsrv.CBRUAfrMunAuthClientSoapProxy("http://190.44.130.5/afrmunis/client/");//SignUrl);
			signStub.sign(	new org.cbru.munafr.authsrv.Sysinfo(payment.getSysinfo().getData(),payment.getSysinfo().getTime(),payment.getSysinfo().getBid(),payment.getSysinfo().getTid(), payment.getSysinfo().getSid(),payment.getSysinfo().getHash()), 
							new org.cbru.munafr.authsrv.Payer(payment.getPayer().getBranch(),payment.getPayer().getAccount(),payment.getPayer().getName(),payment.getPayer().getInn()), 
							new org.cbru.munafr.authsrv.Payee(payment.getPayee().getBranch(),payment.getPayee().getAccount(),payment.getPayee().getName(),payment.getPayee().getInn()), 
							new org.cbru.munafr.authsrv.Amount(payment.getAmount().getCurrency(),payment.getAmount().getValue()), 
							new org.cbru.munafr.authsrv.Document(payment.getDocument().getDoc_n(),payment.getDocument().getDoc_d()),
							error_code,
							error_desc,
							dsign); 
		    //System.out.println("***SIGN***: "+dsign.value);
		    //System.out.println("***error_code***: "+error_code.value);
		    //System.out.println("***error_desc***: "+error_desc.value);
		    //ISLogger.getLogger().error("***SIGN*** error_code: "+error_code.value+"; error_desc: "+error_desc.value+";");
		} catch (Exception e) {
			//System.out.println("***error***: ");
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		//System.getProperties().put("http.proxyHost", "");
		//System.getProperties().put("http.proxyPort", "");
		/*
		if (dsign.value == null){
			dsign.value = "";
		}
		*/
		return dsign.value;
	}
	
	@Override
	public PayResp check(Credentials cr, Payment pay) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Blank blank = null;
		com.cb.munis.Confirm conf = null;
		try {
			com.cb.munis.Bank bank = new com.cb.munis.Bank();
			bank.setCode(com.is.munisCB.MunisSrv1.getHeadBranch(pay.getBranch()));
			//System.out.println("branch = "+com.is.munis.MunisSrv1.getHeadBranch(pay.getBranch()));
			WsProxy stub = new WsProxy(url);
			conf = (com.cb.munis.Confirm) stub.getAccess(bank); 
			//System.out.println("*** code: "+conf.getCode()+"; message: "+conf.getMessage());
			if (conf.getCode().equals("0")){
				//bank.setCode(com.is.munis.MunisSrv1.getHeadBranch(pay.getBranch()));
		        com.cb.munis.Payer payer = new com.cb.munis.Payer();
		        payer = com.is.munisCB.MunisSrv1.getPayer(pay.getBranch(),pay.getUser_id(), pay.getOperation_id());
		        if (CheckNull.isEmpty(payer.getAccount())){
		        	throw new Exception("Payer wasn't found!");
		        }
		        /*
		        payer.setName("Операционная  касса     031"); 
		        payer.setBranch("00394");
		        payer.setAccount("19997000000000394010");//29896000000000394004
		        payer.setInn("201200124");
		        */
		        com.cb.munis.Settlement settlement = new com.cb.munis.Settlement();
		        settlement = com.is.munisCB.MunisSrv1.getSettlement(pay.getService_id());
		        /*
		        settlement.setCode("01");
		        settlement.setSupplier("0601");
		        */
		        subjectinfo_pkg.Subjectinfo[] subji = {};
		        subjectinfo_pkg.Subjectinfo si = null;
		        if (settlement.getSupplier().equals("0301")){ // Сотовая связь beeline
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("PREFIX");
					si.setValue(pay.getP_number().substring(0,2));
					subji = addElement(subji, si);
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("PHONE");
					si.setValue(pay.getP_number().substring(2));
					subji = addElement(subji, si);
		        } else if (settlement.getSupplier().equals("0101")){// МинЭнерго
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("SOATO");
					si.setValue(getSoato(pay.getDistrict()));
					subji = addElement(subji, si);
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("CUSTOMER_TYPE");
					si.setValue("2");
					subji = addElement(subji, si);
					si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("CUSTOMER");
					si.setValue(pay.getP_number());
					subji = addElement(subji, si);
		        } else if (settlement.getSupplier().equals("0102")){// ГАЗ
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("CODE_GP");
					si.setValue(getGAS(pay.getDistrict()));
					subji = addElement(subji, si);
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("ABONENTLIC");
					si.setValue(pay.getP_number());
					subji = addElement(subji, si);
		        } else if (settlement.getSupplier().equals("0201")){// ГНИ
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("GNI");
					si.setValue(getGNI(pay.getDistrict()));
					subji = addElement(subji, si);
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("INN");
					si.setValue(pay.getP_number());
					subji = addElement(subji, si);
					si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("CODE_OBJ");
					si.setValue(pay.getAddress());
					subji = addElement(subji, si);
		        } else {// Интернет
		        	si = new subjectinfo_pkg.Subjectinfo();
		        	si.setElement("PHONE");
					si.setValue(pay.getP_number());
					subji = addElement(subji, si);
		        }
		        blank = (com.cb.munis.Blank) stub.getBlank(bank, payer, settlement, subji);
		        //System.out.println("*** code: "+blank.getConfirm().getCode()+"; message: "+blank.getConfirm().getMessage());
				com.is.munisCB.XMLSerializer.write(blank, "c:/blank_rsv.xml");
		        Integer code = responces.get(blank.getConfirm().getCode());
		        if (code == null){
		        	throw new Exception("Payment wasn't created!");
		        }
		        this.getRes().setCode(code);
	    		this.getRes().setName(selfresponces.get(code));
	    		getPres().setRs(getRes());
	    		HashMap<String,String> ai = blankToAddInfo(blank);
		    	getPres().setAddInfo(ai);
	    		getPres().setPaym(pay);
			} else {
				throw new Exception("Access denied!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();ISLogger.getLogger().error(CheckNull.getPstr(e)); 
			if(e.getMessage() == null){
				this.getRes().setCode(errCode);
				try{ this.getRes().setName(selfresponces.get(errCode)+" (Код ошибки - "+blank.getConfirm().getCode()+" - "+blank.getConfirm().getMessage()+")");} catch (Exception ex) {try{ this.getRes().setName(selfresponces.get(errCode)+" (Код ошибки - "+conf.getCode()+" - "+conf.getMessage()+")");} catch (Exception ex1) {this.getRes().setName(selfresponces.get(errCode));}}
			} else if(e.getMessage().equals("Payer wasn't found!")) {
				this.getRes().setCode(15);
	    		this.getRes().setName(selfresponces.get(15));
			} else if(e.getMessage().equals("Access denied!")) {
				this.getRes().setCode(13);
	    		try{ this.getRes().setName(selfresponces.get(13)+" (Код ошибки - "+conf.getCode()+" - "+conf.getMessage()+")");} catch (Exception ex) {this.getRes().setName(selfresponces.get(13));}
			} else if(e.getMessage().equals("Payment wasn't created!")) {
				this.getRes().setCode(16);
	    		try{ this.getRes().setName(selfresponces.get(16)+" (Код ошибки - "+blank.getConfirm().getCode()+" - "+blank.getConfirm().getMessage()+")");} catch (Exception ex) {this.getRes().setName(selfresponces.get(16));}
			} else {
				this.getRes().setCode(18);
	    		this.getRes().setName(selfresponces.get(18));
			}
			getPres().setRs(getRes());
			//pay.setP_name(selfresponces.get(errCode));
			getPres().setPaym(pay);
			HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return getPres();
	}

	@Override
	public PayResp pay(Credentials cr, Payment pay, HashMap<String, String> addInfo) {
		com.cb.munis.Payment payResponse = null;
		com.cb.munis.Blank blank = null;
		PayResp checkResp = null;
		try {
			checkResp = check(cr, pay);
			if (checkResp.getRs().getCode() == 0) {
				if (proxy){
					System.getProperties().put("http.proxyHost", proxyHost);
					System.getProperties().put("http.proxyPort", proxyPort);
					System.setProperty("http.nonProxyHosts", nonProxyHosts);
				}
				WsProxy stub = new WsProxy(url);
				blank = addInfoToBlank(checkResp.getAddInfo());//addInfo);
				//com.is.munis.XMLSerializer.write(blank, "c:/blank.xml");
				com.cb.munis.Payment payment = new com.cb.munis.Payment();
			    com.cb.munis.Amount amnt = new com.cb.munis.Amount();
				amnt.setCurrency(pay.getCurrency());
				amnt.setValue(""+pay.getAmount());
				payment.setAmount(amnt);
				payment.setConfirm(blank.getConfirm()); 
				com.cb.munis.Document doc = new com.cb.munis.Document();
				doc.setDoc_d(docdf.format(pay.getTime_stamp()));
				doc.setDoc_n(""+pay.getId());
				payment.setDocument(doc); 
				payment.setPayer(blank.getPayer());
				payment.setPayee(blank.getPayee());
				payment.setPurpose(com.is.munisCB.MunisSrv1.getPurpose(pay.getService_id()));
				payment.setSettlement(blank.getSettlement());
				payment.setSubject(blank.getSubject());
				blank.getSysinfo().setSid(""+pay.getId());
				payment.setSysinfo(blank.getSysinfo());//new com.cb.munis.Sysinfo()
				payment.setInfoservice(blank.getInfoservice());
				//payment.setDsign("PD94bWwgdmVyc2lvbj0iMS4wIj8+CjxTaWduYXR1cmVzIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIj4KCQk8U2lnbmF0dXJlIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIiBJZD0iU2lnbjEiPgoJCQk8U2lnbmVkSW5mbz4KCQkJCTxDYW5vbmljYWxpemF0aW9uTWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvVFIvMjAwMS9SRUMteG1sLWMxNG4tMjAwMTAzMTUiLz4KCQkJCTxTaWduYXR1cmVNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LmlldGYub3JnL3JmYy9yZmM1ODMyLnR4dCIvPgoJCQkJPFJlZmVyZW5jZSBVUkk9Ik11bmlzT2JqZWN0Ij4KCQkJCQk8VHJhbnNmb3Jtcz4KCQkJCQkJPFRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNudWxsIi8+CgkJCQkJPC9UcmFuc2Zvcm1zPgoJCQkJCTxEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LmlldGYub3JnL3JmYy9yZmM1ODMxLnR4dCIvPgoJCQkJCTxEaWdlc3RWYWx1ZT4wY2E4YzVlNjM2YWRiYTk5NzYzZWM3ZmRjYzY1NzllNmE0YzBmOGFkYTVjOWQ0MWZlNzJhYzhiMDhkZmI1Mjg2PC9EaWdlc3RWYWx1ZT4KCQkJCTwvUmVmZXJlbmNlPgoJCQkJPFJlZmVyZW5jZSBVUkk9IiNTaWduMXByb3AiPgoJCQkJCTxUcmFuc2Zvcm1zPgoJCQkJCQk8VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI251bGwiLz4KCQkJCQk8L1RyYW5zZm9ybXM+CgkJCQkJPERpZ2VzdE1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cuaWV0Zi5vcmcvcmZjL3JmYzU4MzEudHh0Ii8+CgkJCQkJPERpZ2VzdFZhbHVlPjQ4MmYyMGFjMzJkYmVkOGNiZDM5OTBjYzY4MDYzYTU2YzZjODZlNWU4ZjVkNDJjMzdjYTNiY2ZkOTg5Yjk0MGQ8L0RpZ2VzdFZhbHVlPgoJCQkJPC9SZWZlcmVuY2U+CgkJCTwvU2lnbmVkSW5mbz4KCQkJPFNpZ25hdHVyZVZhbHVlPmJmNzA3NzAxMDZiMTg2ZTY2Nzk4ZGM0ZGI4YmVmZjgzZTM1NTQzZDVlMzI3MTFkYmFiODI2MmQzNGRjNjI0NzcyNmMyYjBmN2Y0N2VjMDE5ODBjOGYxNjg0ZjA5YTQ3NTgwZGUzMmVmM2UyZGQ0YTY3NWQ1ZjFmMjViMjE3YmJmPC9TaWduYXR1cmVWYWx1ZT4KCQkJPE9iamVjdCBJZD0iU2lnbjFwcm9wIj4KCQkJPFNpZ25hdHVyZVByb3BlcnRpZXMgeG1sbnM6ZHNwPSJodHRwOi8vd3d3LnczLm9yZy8yMDA5L3htbGRzaWctcHJvcGVydGllcyI+CgkJCQk8U2lnbmF0dXJlUHJvcGVydHkgSWQ9InByZWZzMSIgVGFyZ2V0PSIjU2lnbjEiPgoJCQkJCTxhZnJwcmVmcyB4bWxuczphZnI9Imh0dHA6Ly9BRlJzZXJ2ZXIvRFNJRy94bWxkc2lnLWFmci1wcm9wZXJ0aWVzIj4KCQkJCQkJPHRpbWVzdGFtcD4xNDAzMTExODQ3NTZaPC90aW1lc3RhbXA+CgkJCQkJCTxhZnJ2ZXI+YWZyLWNsaWVudC0wLjkuMS8wLjkuNTg8L2FmcnZlcj4KCQkJCQkJPGRvY19uPjQ3OTE8L2RvY19uPgoJCQkJCQk8ZG9jX2Q+MTEuMDMuMjAxNDwvZG9jX2Q+CgkJCQkJCTxhZnJ0a25pZD4wMDowMDowMDowMDowMDowMDowMDowMDowMDowMDowMDowMDowMDowMDowMDowMDwvYWZydGtuaWQ+CgkJCQkJCTxjcnRmaW5nZXJwcmludD42MzU1ZmNlM2Q1ZThlMWU4NDliNGU2ZmJlNjNiMjFlOWZmMTkxMmMzPC9jcnRmaW5nZXJwcmludD4KCQkJCQkJPGNydHNlcmlhbD40RDo3NTo2RTo2OTo3MzoyRDo0Mzo0NTwvY3J0c2VyaWFsPgoJCQkJCQk8Y3J0c3Viaj4vQz1VWi9TVD1UYXNoa2VudC9MPVRhc2hrZW50L089Q0JSVS9PVT0wOTAxNC9DTj1NdW5pcy1JcGFrWXVsaS9lbWFpbEFkZHJlc3M9aXBha3l1bGlAbXVuaXMuY2JydS51ejwvY3J0c3Viaj4KCQkJCQkJPGNydHN1YmpoYXNoPjk4ZjlkOWU4PC9jcnRzdWJqaGFzaD4KCQkJCQkJPGNydGlzc3Vlcj4vQz1VWi9TVD1UYXNoa2VudC9MPVRhc2hrZW50L089Q0JSVS9PVT1BRlJzZXJ2ZXIvQ049Q0EtTVVOL2VtYWlsQWRkcmVzcz1jYW11bkBhZnJzZXJ2ZXIuY2JydS5vcmc8L2NydGlzc3Vlcj4KCQkJCQkJPGNydGlzc3Vlcmhhc2g+M2FkMTgxYTk8L2NydGlzc3Vlcmhhc2g+CgkJCQkJCTxjcnRzdGFydGRhdGU+MTMxMDA0MTMyOTU2WjwvY3J0c3RhcnRkYXRlPgoJCQkJCQk8Y3J0ZW5kZGF0ZT4xNDEwMDQxMzI5NTZaPC9jcnRlbmRkYXRlPgoJCQkJCQk8eDUwOT4tLS0tLUJFR0lOIENFUlRJRklDQVRFLS0tLS0KTUlJQ2p6Q0NBanlnQXdJQkFnSUlUWFZ1YVhNdFEwVXdDZ1lHS29VREFnSURCUUF3Z1pBeEN6QUpCZ05WQkFZVApBbFZhTVJFd0R3WURWUVFJREFoVVlYTm9hMlZ1ZERFUk1BOEdBMVVFQnd3SVZHRnphR3RsYm5ReERUQUxCZ05WCkJBb01CRU5DVWxVeEVqQVFCZ05WQkFzTUNVRkdVbk5sY25abGNqRVBNQTBHQTFVRUF3d0dRMEV0VFZWT01TY3cKSlFZSktvWklodmNOQVFrQkZoaGpZVzExYmtCaFpuSnpaWEoyWlhJdVkySnlkUzV2Y21jd0hoY05NVE14TURBMApNVE15T1RVMldoY05NVFF4TURBME1UTXlPVFUyV2pDQmtqRUxNQWtHQTFVRUJoTUNWVm94RVRBUEJnTlZCQWdNCkNGUmhjMmhyWlc1ME1SRXdEd1lEVlFRSERBaFVZWE5vYTJWdWRERU5NQXNHQTFVRUNnd0VRMEpTVlRFT01Bd0cKQTFVRUN3d0ZNRGt3TVRReEZ6QVZCZ05WQkFNTURrMTFibWx6TFVsd1lXdFpkV3hwTVNVd0l3WUpLb1pJaHZjTgpBUWtCRmhacGNHRnJlWFZzYVVCdGRXNXBjeTVqWW5KMUxuVjZNR013SEFZR0tvVURBZ0lUTUJJR0J5cUZBd0lDCkl3RUdCeXFGQXdJQ0hnRURRd0FFUUxUZVVTZlJxTnpKR2t3emlzeFE0blI5WUM0aU5kWlV0OGd3RGZUai9OMlkKRk12VU1wcmFhMnVPc0kvdm1BbmhXb2FzUVkyVTVicmNwaUtESWNRRUFxaWpjakJ3TUF3R0ExVWRFd0VCL3dRQwpNQUF3RVFZSllJWklBWWI0UWdFQkJBUURBZ1dnTUIwR0ExVWRKUVFXTUJRR0NDc0dBUVVGQndNQ0JnZ3JCZ0VGCkJRY0RCREFoQmdOVkhSRUVHakFZZ1JacGNHRnJlWFZzYVVCdGRXNXBjeTVqWW5KMUxuVjZNQXNHQTFVZER3UUUKQXdJRThEQUtCZ1lxaFFNQ0FnTUZBQU5CQUdZZTdKcC9rRzJrdWcxV2htUWlhUytMeW9QUTRub09PRzZ5ZW9pMQpHNGV0YkJERWRabnNGV2tqK3NYaVF1bk9zMlRJbkRqTUhxSXY2blhPSHV5NktmRT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo8L3g1MDk+CgkJCQkJCTxjYT4tLS0tLUJFR0lOIENFUlRJRklDQVRFLS0tLS0KTUlJQ2NqQ0NBaCtnQXdJQkFnSUpBS3UyTmRqb0pDZkRNQW9HQmlxRkF3SUNBd1VBTUlHUU1Rc3dDUVlEVlFRRwpFd0pWV2pFUk1BOEdBMVVFQ0F3SVZHRnphR3RsYm5ReEVUQVBCZ05WQkFjTUNGUmhjMmhyWlc1ME1RMHdDd1lEClZRUUtEQVJEUWxKVk1SSXdFQVlEVlFRTERBbEJSbEp6WlhKMlpYSXhEekFOQmdOVkJBTU1Ca05CTFUxVlRqRW4KTUNVR0NTcUdTSWIzRFFFSkFSWVlZMkZ0ZFc1QVlXWnljMlZ5ZG1WeUxtTmljblV1YjNKbk1CNFhEVEV6TVRBdwpOREV5TWpreU1Wb1hEVEUxTURJeE5qRXlNamt5TVZvd2daQXhDekFKQmdOVkJBWVRBbFZhTVJFd0R3WURWUVFJCkRBaFVZWE5vYTJWdWRERVJNQThHQTFVRUJ3d0lWR0Z6YUd0bGJuUXhEVEFMQmdOVkJBb01CRU5DVWxVeEVqQVEKQmdOVkJBc01DVUZHVW5ObGNuWmxjakVQTUEwR0ExVUVBd3dHUTBFdFRWVk9NU2N3SlFZSktvWklodmNOQVFrQgpGaGhqWVcxMWJrQmhabkp6WlhKMlpYSXVZMkp5ZFM1dmNtY3dZekFjQmdZcWhRTUNBaE13RWdZSEtvVURBZ0lqCkFRWUhLb1VEQWdJZUFRTkRBQVJBRTVsR1d4bWF6Nkc4UTE3dnJQY0U2QmhsS0RsT281NGZSZnR3dlR5T1dvaFQKaHh5Wm1uc1FvYWh6RGQxLzlBdEFLZmZqTm1LMzBna3VpNlBiR3dtSHM2TldNRlF3SFFZRFZSME9CQllFRkJIaAp1T0NxNThIK0NKU29yV1orU0RhZ0pxV2pNQjhHQTFVZEl3UVlNQmFBRkJIaHVPQ3E1OEgrQ0pTb3JXWitTRGFnCkpxV2pNQklHQTFVZEV3RUIvd1FJTUFZQkFmOENBUUF3Q2dZR0tvVURBZ0lEQlFBRFFRQ2hyeXE5OWVTaUZUQkkKVkU2eWtNT3ZCS1FQa2UrTWFZS2pNdndTZHBOaDFUMkpKSlVmdk1rc0lCRUhjZThNcFV6Q1l1cTg0RkFmZTRPTgpBcWZ2VU5uUgotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCjwvY2E+CgkJCQkJCTxjcmw+LS0tLS1CRUdJTiBYNTA5IENSTC0tLS0tCk1JSUJJakNCMEFJQkFUQUtCZ1lxaFFNQ0FnTUZBRENCa0RFTE1Ba0dBMVVFQmhNQ1ZWb3hFVEFQQmdOVkJBZ00KQ0ZSaGMyaHJaVzUwTVJFd0R3WURWUVFIREFoVVlYTm9hMlZ1ZERFTk1Bc0dBMVVFQ2d3RVEwSlNWVEVTTUJBRwpBMVVFQ3d3SlFVWlNjMlZ5ZG1WeU1ROHdEUVlEVlFRRERBWkRRUzFOVlU0eEp6QWxCZ2txaGtpRzl3MEJDUUVXCkdHTmhiWFZ1UUdGbWNuTmxjblpsY2k1alluSjFMbTl5WnhjTk1UTXhNREV3TWpJeU5qQTVXaGNOTVRNeE1UQTUKTWpJeU5qQTVXcUFPTUF3d0NnWURWUjBVQkFNQ0FRRXdDZ1lHS29VREFnSURCUUFEUVFBZkpBNm5JYmdCZkZ4VQo0QnNpTU9BaEY2Q09RVFlCRGtWZENxMzc1VHdMVExyb0ZvSC9LUlMwZTlkZHlFejNhb0UvazZvYm9BOFpnWDBNCnhIZk5WazhxCi0tLS0tRU5EIFg1MDkgQ1JMLS0tLS0KPC9jcmw+CgkJCQkJPC9hZnJwcmVmcz4KCQkJCTwvU2lnbmF0dXJlUHJvcGVydHk+CgkJCTwvU2lnbmF0dXJlUHJvcGVydGllcz4KCQkJPC9PYmplY3Q+CgkJPC9TaWduYXR1cmU+Cgk8U2lnbmF0dXJlIElkPSJTaWduMiI+Cgk8U2lnbmVkSW5mbz4KCQk8Q2Fub25pY2FsaXphdGlvbk1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnL1RSLzIwMDEvUkVDLXhtbC1jMTRuLTIwMDEwMzE1Ii8+CgkJPFNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cuaWV0Zi5vcmcvcmZjL3JmYzU4MzIudHh0Ii8+CgkJPFJlZmVyZW5jZSBVUkk9Ik11bmlzT2JqZWN0Ij4KCQkJPFRyYW5zZm9ybXM+CgkJCQk8VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI251bGwiLz4KCQkJPC9UcmFuc2Zvcm1zPgoJCQk8RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy5pZXRmLm9yZy9yZmMvcmZjNTgzMS50eHQiLz4KCQkJPERpZ2VzdFZhbHVlPjY4ZjY1ODk5Njc5YzRiMGY0NzczYjQ5NzM0MTQ2MzRmYWYzOGMxYmViYjg4Yzc1NjZiNmY5ZmU5NzE1MjViN2M8L0RpZ2VzdFZhbHVlPgoJCTwvUmVmZXJlbmNlPgoJCTxSZWZlcmVuY2UgVVJJPSIjU2lnbjEiPgoJCQk8VHJhbnNmb3Jtcz4KCQkJCTxUcmFuc2Zvcm0gQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjbnVsbCIvPgoJCQk8L1RyYW5zZm9ybXM+CgkJCTxEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LmlldGYub3JnL3JmYy9yZmM1ODMxLnR4dCIvPgoJCQk8RGlnZXN0VmFsdWU+NzBiYjhmYTZkZjQ4NDFiY2M3ZGYxYmZmM2ZiNWNmMWUxOTcwNjJmZWVmMmZiODczNjMzZjdmNmRlYTEyODI5YTwvRGlnZXN0VmFsdWU+CgkJPC9SZWZlcmVuY2U+CgkJPFJlZmVyZW5jZSBVUkk9IiNTaWduMnByb3AiPgoJCQk8VHJhbnNmb3Jtcz4KCQkJCTxUcmFuc2Zvcm0gQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjbnVsbCIvPgoJCQk8L1RyYW5zZm9ybXM+CgkJCTxEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LmlldGYub3JnL3JmYy9yZmM1ODMxLnR4dCIvPgoJCQk8RGlnZXN0VmFsdWU+Mjg5ZTVlNDlmM2MwOTkyNjQ0NmZhZjRhNzM2ODdmNWYzNjNkMGFiZDhkMjJkNGUzZmI1MzljNTVjMzg5ZmVjOTwvRGlnZXN0VmFsdWU+CgkJPC9SZWZlcmVuY2U+Cgk8L1NpZ25lZEluZm8+Cgk8U2lnbmF0dXJlVmFsdWU+ZmQ4MDMyNTU1MjkxZDUwMGU4YzY3MTNiYjUwZjg5ZmE5MzM1NWQzNDAzY2VlYWE3MzhjOTAxMDNhNzMyNDM3OWE5ODhhYTdkNDY3YmNiYWNkZWEzMjA0MTcyZjM4N2EwODYzZGY3OTE3MTk0OTI1YzMyOTE2MDYyYzRmZTRmNjA8L1NpZ25hdHVyZVZhbHVlPgoJPE9iamVjdCBJZD0iU2lnbjJwcm9wIj4KCQk8U2lnbmF0dXJlUHJvcGVydGllcyB4bWxuczpkc3A9Imh0dHA6Ly93d3cudzMub3JnLzIwMDkveG1sZHNpZy1wcm9wZXJ0aWVzIj4KCQkJPFNpZ25hdHVyZVByb3BlcnR5IElkPSJwcmVmczIiIFRhcmdldD0iI1NpZ24yIj4KCQkJCTxhZnJwcmVmcyB4bWxuczphZnI9Imh0dHA6Ly9BRlJzZXJ2ZXIvRFNJRy94bWxkc2lnLWFmci1wcm9wZXJ0aWVzIj4KCQkJCQk8dGltZXN0YW1wPjE0MDMxMTEzNDYxNlo8L3RpbWVzdGFtcD4KCQkJCQk8YWZydmVyPmFmci1zZXJ2ZXItMC45LjEvMC45LjU4PC9hZnJ2ZXI+CgkJCQkJPGRvY19uPjQ3OTE8L2RvY19uPgoJCQkJCTxkb2NfZD4xMS4wMy4yMDE0PC9kb2NfZD4KCQkJCQk8YWZydGtuaWQ+MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA6MDA8L2FmcnRrbmlkPgoJCQkJCTxjcnRmaW5nZXJwcmludD5kOWNiOGIwOGRkMTc2MjYyOGQyZmUzMGJhM2FmZjk2NTg1OWVjZDY2PC9jcnRmaW5nZXJwcmludD4KCQkJCQk8Y3J0c2VyaWFsPjREOjc1OjZFOjY5OjczOjJEOjQzOjQxPC9jcnRzZXJpYWw+CgkJCQkJPGNydHN1Ymo+L0M9VVovU1Q9VGFzaGtlbnQvTD1UYXNoa2VudC9PPUNCUlUvT1U9QUZSc2VydmVyL0NOPU11bmlzLUFGUi1TcnYvZW1haWxBZGRyZXNzPWFmcnNydkBtdW5pcy5jYnJ1LnV6PC9jcnRzdWJqPgoJCQkJCTxjcnRzdWJqaGFzaD43NDQ0OTgyNDwvY3J0c3Viamhhc2g+CgkJCQkJPGNydGlzc3Vlcj4vQz1VWi9TVD1UYXNoa2VudC9MPVRhc2hrZW50L089Q0JSVS9PVT1BRlJzZXJ2ZXIvQ049Q0EtTVVOL2VtYWlsQWRkcmVzcz1jYW11bkBhZnJzZXJ2ZXIuY2JydS5vcmc8L2NydGlzc3Vlcj4KCQkJCQk8Y3J0aXNzdWVyaGFzaD4zYWQxODFhOTwvY3J0aXNzdWVyaGFzaD4KCQkJCQk8Y3J0c3RhcnRkYXRlPjEzMTAwNDEyMzAzOFo8L2NydHN0YXJ0ZGF0ZT4KCQkJCQk8Y3J0ZW5kZGF0ZT4xNDEwMDQxMjMwMzhaPC9jcnRlbmRkYXRlPgoJCQkJCTx4NTA5Pi0tLS0tQkVHSU4gQ0VSVElGSUNBVEUtLS0tLQpNSUlDampDQ0FqdWdBd0lCQWdJSVRYVnVhWE10UTBFd0NnWUdLb1VEQWdJREJRQXdnWkF4Q3pBSkJnTlZCQVlUCkFsVmFNUkV3RHdZRFZRUUlEQWhVWVhOb2EyVnVkREVSTUE4R0ExVUVCd3dJVkdGemFHdGxiblF4RFRBTEJnTlYKQkFvTUJFTkNVbFV4RWpBUUJnTlZCQXNNQ1VGR1VuTmxjblpsY2pFUE1BMEdBMVVFQXd3R1EwRXRUVlZPTVNjdwpKUVlKS29aSWh2Y05BUWtCRmhoallXMTFia0JoWm5KelpYSjJaWEl1WTJKeWRTNXZjbWN3SGhjTk1UTXhNREEwCk1USXpNRE00V2hjTk1UUXhNREEwTVRJek1ETTRXakNCa3pFTE1Ba0dBMVVFQmhNQ1ZWb3hFVEFQQmdOVkJBZ00KQ0ZSaGMyaHJaVzUwTVJFd0R3WURWUVFIREFoVVlYTm9hMlZ1ZERFTk1Bc0dBMVVFQ2d3RVEwSlNWVEVTTUJBRwpBMVVFQ3d3SlFVWlNjMlZ5ZG1WeU1SWXdGQVlEVlFRRERBMU5kVzVwY3kxQlJsSXRVM0oyTVNNd0lRWUpLb1pJCmh2Y05BUWtCRmhSaFpuSnpjblpBYlhWdWFYTXVZMkp5ZFM1MWVqQmpNQndHQmlxRkF3SUNFekFTQmdjcWhRTUMKQWlNQkJnY3FoUU1DQWg0QkEwTUFCRUNGbUk4V2hGK2IzbXYrVkMwYjl0RGw4K2ZhWGtyaWh3S2hiTzgramlkLwpKNXpvckxhNDNvemx2SUFnNmlZZ0JyL0U4RHpvN0VLYnN3cXlzWElLank3c28zQXdiakFNQmdOVkhSTUJBZjhFCkFqQUFNQkVHQ1dDR1NBR0crRUlCQVFRRUF3SUZvREFkQmdOVkhTVUVGakFVQmdnckJnRUZCUWNEQWdZSUt3WUIKQlFVSEF3UXdId1lEVlIwUkJCZ3dGb0VVWVdaeWMzSjJRRzExYm1sekxtTmljblV1ZFhvd0N3WURWUjBQQkFRRApBZ1R3TUFvR0JpcUZBd0lDQXdVQUEwRUF0Z0dnZHZRSWFSUHRJUzdyYWQrb09OYTZVUUxGN2U3ZjY5VGUxWEswCnZUQnI5azFFU2s3Mm5qb1dHNnJFNmpldUhPYWRJQkp6bnFaajJLOGVad1BKV2c9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCjwveDUwOT4KCQkJCQk8Y2E+LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUNjakNDQWgrZ0F3SUJBZ0lKQUt1Mk5kam9KQ2ZETUFvR0JpcUZBd0lDQXdVQU1JR1FNUXN3Q1FZRFZRUUcKRXdKVldqRVJNQThHQTFVRUNBd0lWR0Z6YUd0bGJuUXhFVEFQQmdOVkJBY01DRlJoYzJoclpXNTBNUTB3Q3dZRApWUVFLREFSRFFsSlZNUkl3RUFZRFZRUUxEQWxCUmxKelpYSjJaWEl4RHpBTkJnTlZCQU1NQmtOQkxVMVZUakVuCk1DVUdDU3FHU0liM0RRRUpBUllZWTJGdGRXNUFZV1p5YzJWeWRtVnlMbU5pY25VdWIzSm5NQjRYRFRFek1UQXcKTkRFeU1qa3lNVm9YRFRFMU1ESXhOakV5TWpreU1Wb3dnWkF4Q3pBSkJnTlZCQVlUQWxWYU1SRXdEd1lEVlFRSQpEQWhVWVhOb2EyVnVkREVSTUE4R0ExVUVCd3dJVkdGemFHdGxiblF4RFRBTEJnTlZCQW9NQkVOQ1VsVXhFakFRCkJnTlZCQXNNQ1VGR1VuTmxjblpsY2pFUE1BMEdBMVVFQXd3R1EwRXRUVlZPTVNjd0pRWUpLb1pJaHZjTkFRa0IKRmhoallXMTFia0JoWm5KelpYSjJaWEl1WTJKeWRTNXZjbWN3WXpBY0JnWXFoUU1DQWhNd0VnWUhLb1VEQWdJagpBUVlIS29VREFnSWVBUU5EQUFSQUU1bEdXeG1hejZHOFExN3ZyUGNFNkJobEtEbE9vNTRmUmZ0d3ZUeU9Xb2hUCmh4eVptbnNRb2FoekRkMS85QXRBS2Zmak5tSzMwZ2t1aTZQYkd3bUhzNk5XTUZRd0hRWURWUjBPQkJZRUZCSGgKdU9DcTU4SCtDSlNvcldaK1NEYWdKcVdqTUI4R0ExVWRJd1FZTUJhQUZCSGh1T0NxNThIK0NKU29yV1orU0RhZwpKcVdqTUJJR0ExVWRFd0VCL3dRSU1BWUJBZjhDQVFBd0NnWUdLb1VEQWdJREJRQURRUUNocnlxOTllU2lGVEJJClZFNnlrTU92QktRUGtlK01hWUtqTXZ3U2RwTmgxVDJKSkpVZnZNa3NJQkVIY2U4TXBVekNZdXE4NEZBZmU0T04KQXFmdlVOblIKLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo8L2NhPgoJCQkJCTxjcmw+PC9jcmw+CgkJCQk8L2FmcnByZWZzPgoJCQk8L1NpZ25hdHVyZVByb3BlcnR5PgoJCTwvU2lnbmF0dXJlUHJvcGVydGllcz4KCTwvT2JqZWN0Pgo8L1NpZ25hdHVyZT48L1NpZ25hdHVyZXM+Cg==");//
				payment.setDsign(getDsign(payment));//
				com.is.munisCB.XMLSerializer.write(payment, "c:/payment_send.xml");
				payResponse = (com.cb.munis.Payment) stub.sendPayment(payment);
				//System.out.println("***** - 1");
				com.is.munisCB.XMLSerializer.write(payResponse, "c:/payment_rsv.xml");
				//System.out.println("***** - 2");
				//System.out.println("*** code: "+payResponse.getConfirm().getCode()+"; message: "+payResponse.getConfirm().getMessage());
				Integer code = responces.get(payResponse.getConfirm().getCode());
			    if (code == null){
			        throw new Exception("Payment wasn't committed!");
			    }
			    this.getRes().setCode(code);
		    	this.getRes().setName(selfresponces.get(code));
		    	getPres().setRs(getRes());
		    	HashMap<String,String> ai = blankToAddInfo(paymentToBlank(payResponse));
			    getPres().setAddInfo(ai);
		    	getPres().setPaym(pay);
			} else {
				setPres(checkResp);
			}
		} catch (Exception e) {
			e.printStackTrace();ISLogger.getLogger().error(CheckNull.getPstr(e)); 
			if(e.getMessage() == null){
				this.getRes().setCode(errCode);
				try{ this.getRes().setName(selfresponces.get(errCode)+" (Код ошибки - "+payResponse.getConfirm().getCode()+" - "+payResponse.getConfirm().getMessage()+")");} catch (Exception ex) {this.getRes().setName(selfresponces.get(errCode));}
				//this.getRes().setName(selfresponces.get(errCode));
			} else if(e.getMessage().equals("Payment wasn't committed!")) {
				this.getRes().setCode(17);
	    		try{ this.getRes().setName(selfresponces.get(17)+" (Код ошибки - "+payResponse.getConfirm().getCode()+" - "+payResponse.getConfirm().getMessage()+")");} catch (Exception ex) {this.getRes().setName(selfresponces.get(17));}
			} else {
				this.getRes().setCode(18);
	    		this.getRes().setName(selfresponces.get(18));
			}
			getPres().setRs(getRes());
			//pay.setP_name(selfresponces.get(errCode));
			getPres().setPaym(pay);
			HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return getPres();
	}

	@Override
	public PayResp checkTr(Credentials cr, long id) {
		return null;
	}

	@Override
	public CheckPerResp checkPer(Credentials cr, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListTrResp listTr(Credentials cr, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PayResp checkTrBySid(Credentials cr, String branch, String sid) {
		com.cb.munis.Payment payment = null;
		try {
			if (proxy){
				System.getProperties().put("http.proxyHost", proxyHost);
				System.getProperties().put("http.proxyPort", proxyPort);
				System.setProperty("http.nonProxyHosts", nonProxyHosts);
			}
			WsProxy stub = new WsProxy(url);
			com.cb.munis.Bank bank = new com.cb.munis.Bank();
			bank.setCode(com.is.munisCB.MunisSrv1.getHeadBranch(branch));
			
			//com.is.munis.XMLSerializer.write(blank, "c:/blank.xml");
			payment = (com.cb.munis.Payment) stub.getPaymentOnSid(bank, sid);
			com.is.munisCB.XMLSerializer.write(payment, "c:/payment.xml");
			//System.out.println("*** code: "+payment.getConfirm().getCode()+"; message: "+payment.getConfirm().getMessage());
			Integer code = responces.get(payment.getConfirm().getCode());
		    if (code == null){
		        throw new Exception("ERROR: Неизвестный код ошибки - "+payment.getConfirm().getCode()+" - "+payment.getConfirm().getMessage());
		    }
		    this.getRes().setCode(code);
	    	this.getRes().setName(selfresponces.get(code));
	    	getPres().setRs(getRes());
	    	HashMap<String,String> ai = blankToAddInfo(paymentToBlank(payment));
		    getPres().setAddInfo(ai);
	    	//getPres().setPaym(payment);
		} catch (Exception e) {
			e.printStackTrace();ISLogger.getLogger().error(CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
			this.getRes().setName(selfresponces.get(errCode));
			getPres().setRs(getRes());
			//pay.setP_name(selfresponces.get(errCode));
			//getPres().setPaym(pay);
			HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
		return getPres();
	}
	
	public PayResp checkTrByBid(Credentials cr, String branch, String bid) {
		com.cb.munis.Payment payment = null;
		com.cb.munis.Bank bank = null;
		try {
			if (proxy){
				System.getProperties().put("http.proxyHost", proxyHost);
				System.getProperties().put("http.proxyPort", proxyPort);
				System.setProperty("http.nonProxyHosts", nonProxyHosts);
			}
			WsProxy stub = new WsProxy(url);
			bank.setCode(com.is.munisCB.MunisSrv1.getHeadBranch(branch));
			//com.is.munis.XMLSerializer.write(blank, "c:/blank.xml");
			payment = (com.cb.munis.Payment) stub.getPaymentOnSid(bank, bid);
			com.is.munisCB.XMLSerializer.write(payment, "c:/payment.xml");
			//System.out.println("*** code: "+payment.getConfirm().getCode()+"; message: "+payment.getConfirm().getMessage());
			Integer code = responces.get(payment.getConfirm().getCode());
		    if (code == null){
		        throw new Exception("ERROR: Неизвестный код ошибки - "+payment.getConfirm().getCode()+" - "+payment.getConfirm().getMessage());
		    }
		    this.getRes().setCode(code);
	    	this.getRes().setName(selfresponces.get(code));
	    	getPres().setRs(getRes());
	    	HashMap<String,String> ai = blankToAddInfo(paymentToBlank(payment));
		    getPres().setAddInfo(ai);
	    	//getPres().setPaym(pay);
		} catch (Exception e) {
			e.printStackTrace();ISLogger.getLogger().error(CheckNull.getPstr(e)); 
			this.getRes().setCode(errCode);
			this.getRes().setName(selfresponces.get(errCode));
			getPres().setRs(getRes());
			//pay.setP_name(selfresponces.get(errCode));
			//getPres().setPaym(pay);
			HashMap<String,String> ai = new HashMap<String, String>();
	    	ai.put(""+errCode, selfresponces.get(errCode));
	    	getPres().setAddInfo(ai);
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
		return getPres();
	}
	
	private void initNp(){
        if (np==null){
              np = new NilProvider();
          np.init();
        }
    }
	
	public com.cb.munis._final getFinalReport(String branch) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		Connection c = null;
		Statement s = null;
		PreparedStatement ps = null;
		PreparedStatement pssup = null;
		ResultSet rs = null;
		com.cb.munis.Bank bank = null;
		com.cb.munis._final final_ = null;
		Long id = null;
		try{
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("select SEQ_bf_muniscb_final_report.nextval id from dual");
			if (rs.next()){
				id = rs.getLong("id");
				ps = c.prepareStatement(" insert into bf_muniscb_final_report (ID, PERIOD_BEGIN_DATA,PERIOD_BEGIN_TIME,PERIOD_END_DATA,PERIOD_END_TIME,BRANCH,DATA,TIME,ACC_CODE,ACC_BEGIN,ACC_END,ACC_DEBET,ACC_DEBET_COUNT,ACC_CREDIT,ACC_CREDIT_COUNT,CONFIRM_CODE,CONFIRM_MESSAGE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				pssup = c.prepareStatement(" insert into bf_muniscb_final_report_sup (ID,SUPPLIER,SUPPLIER_CODE,IN_COUNT,IN_SUMMA,OUT_COUNT,OUT_SUMMA) values (?,?,?,?,?,?,?) ");
				bank = new com.cb.munis.Bank();
				WsProxy stub = new WsProxy(url);
			    bank.setCode(com.is.munisCB.MunisSrv1.getHeadBranch(branch));
			    final_ = stub.getFinalReport(bank); 
			    //----------------------getAccount_19997----------------------
			    ps.setLong(1, id);
			    //getPeriod	    
			    ps.setString(2,final_.getPeriod().getPeriod_begin().getData());
			    ps.setString(3,final_.getPeriod().getPeriod_begin().getTime());
			    ps.setString(4,final_.getPeriod().getPeriod_end().getData());
			    ps.setString(5,final_.getPeriod().getPeriod_end().getTime());
			    //
			    ps.setString(6,final_.getRemainder().getBranch());
			    ps.setString(7,final_.getRemainder().getData());
			    ps.setString(8,final_.getRemainder().getTime());
			    //getRemainder
			    RemainderElements re = final_.getRemainder().getAccount_19997();
			    ps.setString(9,re.getCode());
			    ps.setString(10,re.getBegin());
			    ps.setString(11,re.getEnd());
			    ps.setString(12,re.getDebet());
			    ps.setString(13,re.getDebet_count());
			    ps.setString(14,re.getCredit());
			    ps.setString(15,re.getCredit_count());
			    //getConfirm
			    ps.setString(16,final_.getConfirm().getCode());
			    ps.setString(17,final_.getConfirm().getMessage());
			    ps.executeUpdate();
			    //----------------------getAccount_29896----------------------
			    ps.setLong(1, id);
			    //getPeriod	    
			    ps.setString(2,final_.getPeriod().getPeriod_begin().getData());
			    ps.setString(3,final_.getPeriod().getPeriod_begin().getTime());
			    ps.setString(4,final_.getPeriod().getPeriod_end().getData());
			    ps.setString(5,final_.getPeriod().getPeriod_end().getTime());
			    //
			    ps.setString(6,final_.getRemainder().getBranch());
			    ps.setString(7,final_.getRemainder().getData());
			    ps.setString(8,final_.getRemainder().getTime());
			    //getRemainder
			    re = final_.getRemainder().getAccount_29896();
			    ps.setString(9,re.getCode());
			    ps.setString(10,re.getBegin());
			    ps.setString(11,re.getEnd());
			    ps.setString(12,re.getDebet());
			    ps.setString(13,re.getDebet_count());
			    ps.setString(14,re.getCredit());
			    ps.setString(15,re.getCredit_count());
			    //getConfirm
			    ps.setString(16,final_.getConfirm().getCode());
			    ps.setString(17,final_.getConfirm().getMessage());
			    ps.executeUpdate();
			    //----------------------getSupp_reports----------------------
			    //getSupp_reports
			    SupplierReportElements[] sre = final_.getSupp_reports();
			    for (int i = 0; i < sre.length; i++){
			    	pssup.setLong(1, id);
			    	pssup.setString(2,sre[i].getSupplier().getSupplier());
			    	pssup.setString(3,sre[i].getSupplier().getCode());
			    	pssup.setString(4,sre[i].getIn_count());
			    	pssup.setString(5,sre[i].getIn_summa());
			    	pssup.setString(6,sre[i].getOut_count());
			    	pssup.setString(7,sre[i].getOut_summa());
			    	pssup.executeUpdate();
			    }
			    //----------------------getErr_reports----------------------
			    //getErr_reports
			    ErrorReportElements[] ere = final_.getErr_reports();
			    File file = new File("c:/munis/finalReports/errors_"+id+".xml");
			    if(!file.getParentFile().exists()) {
				     file.getParentFile().mkdirs();
				}
			    com.is.munisCB.XMLSerializer.write(ere, "c:/munis/finalReports/errors_"+id+".xml");
			    /*
			    for (int i = 0; i < ere.length; i++){
			    	ere[i].getCode();
			    	ere[i].getCounts();
			    }
			    */
			    c.commit();
			    
			    file = new File("c:/munis/finalReports/final_report_"+id+"_"+final_.getPeriod().getPeriod_begin().getData()+"-"+final_.getPeriod().getPeriod_end().getData()+".xml");
			    if(!file.getParentFile().exists()) {
				     file.getParentFile().mkdirs();
				}
			    com.is.munisCB.XMLSerializer.write(ere, "c:/munis/finalReports/final_report_"+id+"_"+final_.getPeriod().getPeriod_begin().getData()+"-"+final_.getPeriod().getPeriod_end().getData()+".xml");
			}
			
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			//try{c.rollback();}catch (Exception ex) {ex.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(ex));}
		} finally {
			//ConnectionPool.close(c);
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return final_;
	}
	
	//===========================================references===========================================
	public Errors getErrors(){
		return errors;
	}
	public Settlements getSettlements(){
		return settlements;
	}
	public SupplierGroups getSupplierGroups(){
		return supplierGroups;
	}
	public Suppliers getSuppliers(){
		return suppliers;
	}
	public EnergoDivisions getEnergoDivisions(){
		return refEnergoDivisions;
	}
	public GasDivisions getRefGasDivisions(){
		return refGasDivisions;
	}
	public GnkDivisions getRefGNKDivisions(){
		return refGNKDivisions;
	}
	public Remainder getRemainders(){
		return remainders;
	}
	public Requires getSupplierResponse(){
		return supplierResponse;
	}
	public Requires getSupplierRequest(){
		return supplierRequest;
	}
	
	public void loadReferences(){
		try{
			File file = new File("c:/munis/referances/errors.xml");
			if(!file.getParentFile().exists()) {
			     throw new Exception("References not found!!! (c:/munis/referances isn't exists)");
			}
			errors = (com.cb.munis.Errors) com.is.munisCB.XMLSerializer.read("c:/munis/referances/errors.xml");
			settlements = (Settlements) com.is.munisCB.XMLSerializer.read("c:/munis/referances/settlements.xml");
			supplierGroups = (com.cb.munis.SupplierGroups) com.is.munisCB.XMLSerializer.read("c:/munis/referances/supplierGroups.xml");
			suppliers= (com.cb.munis.Suppliers) com.is.munisCB.XMLSerializer.read("c:/munis/referances/suppliers.xml");
			refEnergoDivisions= (com.cb.munis.EnergoDivisions) com.is.munisCB.XMLSerializer.read("c:/munis/referances/refEnergoDivisions.xml");
			refGasDivisions = (com.cb.munis.GasDivisions) com.is.munisCB.XMLSerializer.read("c:/munis/referances/refGasDivisions.xml");
			refGNKDivisions= (com.cb.munis.GnkDivisions) com.is.munisCB.XMLSerializer.read("c:/munis/referances/refGNKDivisions.xml");
			remainders= (com.cb.munis.Remainder) com.is.munisCB.XMLSerializer.read("c:/munis/referances/remainders.xml");
			supplierResponse = (com.cb.munis.Requires) com.is.munisCB.XMLSerializer.read("c:/munis/referances/supplierResponse.xml");
			supplierRequest = (com.cb.munis.Requires) com.is.munisCB.XMLSerializer.read("c:/munis/referances/supplierRequest.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void updateReferences(String branch){
		try{
			File file = new File("c:/munis/referances/errors.xml");
			if(!file.getParentFile().exists()) {
			     file.getParentFile().mkdirs();
			}
			com.cb.munis.Bank bank = new com.cb.munis.Bank();
			bank.setCode(com.is.munisCB.MunisSrv1.getHeadBranch(branch));
			errors = getErrors(bank);
			settlements = getSettlements(bank);
			supplierGroups = getSupplierGroups(bank);
			suppliers = getSuppliers(bank);
			refEnergoDivisions = getRefEnergoDivisions(bank);
			refGasDivisions = getRefGasDivisions(bank);
			refGNKDivisions = getRefGNKDivisions(bank);
			remainders = getRemainders(bank);
			supplierResponse = getSupplierResponse(bank);
			supplierRequest = getSupplierRequest(bank);
			
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	private com.cb.munis.Errors getErrors(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Errors err = null;
		Connection c = null;
		Statement s = null;
		PreparedStatement ps = null;
		try{
			WsProxy stub = new WsProxy(url);
		    err = stub.getRefErrors(bank); 
		    c = ConnectionPool.getConnection();
			s = c.createStatement();
			s.executeUpdate("delete from BF_MUNIS_CBREFERENCE_LIST");
		    ps = c.prepareStatement("insert into BF_MUNIS_CBREFERENCE_LIST( ser_id, id, name) values (?,?,?)");
			for (int i = 0; i < err.getErrors().length; i++){
				ps.setString(1, "er");
		    	ps.setString(2, err.getErrors()[i].getCode());
		    	ps.setString(3, err.getErrors()[i].getMessage());
		    	if (ps.executeUpdate() != 1) {
		    		throw new Exception("insert into BF_MUNIS_CBREFERENCE_LIST finished with ERROR");
		    	}
		    	//System.out.println("code: "+err.getErrors()[i].getCode()+"; message: "+err.getErrors()[i].getMessage());
		    }
		    c.commit();
		    com.is.munisCB.XMLSerializer.write(err, "c:/munis/referances/errors.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			try{c.rollback();}catch (Exception ex) {ex.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(ex));}
		} finally {
			ConnectionPool.close(c);
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return err;
	}
	
	private com.cb.munis.Settlements getSettlements(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Settlements s = null;
		Connection c = null;
		PreparedStatement ps = null;
		try{
			WsProxy stub = new WsProxy(url);
		    s = stub.getRefSettlements(bank); 
		    c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into BF_MUNIS_CBREFERENCE_LIST( id, name, name_ru, opened, region, distr, state, ser_id) values (?,?,?,?,?,?,?,?)");
			for (int i = 0; i < s.getSettlements().length; i++){
		    	ps.setString(1, s.getSettlements()[i].getCode());
		    	ps.setString(2, s.getSettlements()[i].getName());
		    	ps.setString(3, s.getSettlements()[i].getName_rus());
		    	ps.setString(4, s.getSettlements()[i].getOpened());
		    	ps.setString(5, s.getSettlements()[i].getSupplier().substring(0,2));
		    	ps.setString(6, s.getSettlements()[i].getSupplier().substring(2));
		    	ps.setString(7, s.getSettlements()[i].getState());
		    	ps.setString(8, "s");
		    	if (ps.executeUpdate() != 1) {
		    		throw new Exception("insert into BF_MUNIS_CBREFERENCE_LIST finished with ERROR");
		    	}
		    	//System.out.println("code: "+err.getErrors()[i].getCode()+"; message: "+err.getErrors()[i].getMessage());
		    }
		    c.commit();
		    com.is.munisCB.XMLSerializer.write(s, "c:/munis/referances/settlements.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return s;
	}
	
	private com.cb.munis.SupplierGroups getSupplierGroups(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.SupplierGroups err = null;
		try{
			WsProxy stub = new WsProxy(url);
		    err = stub.getRefSupplierGroups(bank); 
		    com.is.munisCB.XMLSerializer.write(err, "c:/munis/referances/supplierGroups.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return err;
	}
	
	private com.cb.munis.Requires getSupplierRequest(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Requires err = null;
		try{
			WsProxy stub = new WsProxy(url);
		    err = stub.getRefSupplierRequest(bank); 
		    
		    //System.out.println("***Confirm*** code: "+conf.getCode()+"; message: "+conf.getMessage());
		    com.is.munisCB.XMLSerializer.write(err, "c:/munis/referances/supplierRequest.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return err;
	}
	
	private com.cb.munis.Requires getSupplierResponse(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Requires err = null;
		try{
			WsProxy stub = new WsProxy(url);
		    err = stub.getRefSupplierResponse(bank); 
		    
		    //System.out.println("***Confirm*** code: "+conf.getCode()+"; message: "+conf.getMessage());
		    com.is.munisCB.XMLSerializer.write(err, "c:/munis/referances/supplierResponse.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return err;
	}
	
	private com.cb.munis.Suppliers getSuppliers(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Suppliers s = null;
		try{
			WsProxy stub = new WsProxy(url);
		    s = stub.getRefSuppliers(bank); 
		    com.is.munisCB.XMLSerializer.write(s, "c:/munis/referances/suppliers.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return s;
	}
	
	
	private com.cb.munis.Paylists getPaymentList(com.cb.munis.Bank bank, String data) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Paylists plist = null;
		try{
			WsProxy stub = new WsProxy(url);
		    plist = stub.getPaymentList(bank, data); 
		    
		    //System.out.println("***Confirm*** code: "+conf.getCode()+"; message: "+conf.getMessage());
		    com.is.munisCB.XMLSerializer.write(plist, "c:/getReestr.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return plist;
	}
	
	private com.cb.munis.EnergoDivisions getRefEnergoDivisions(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.EnergoDivisions ed = null;
		Connection c = null;
		PreparedStatement ps = null;
		try{
			WsProxy stub = new WsProxy(url);
		    ed = stub.getRefEnergoDivisions(bank); 
		    c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into BF_MUNIS_CBREFERENCE_LIST( id, name, name_ru, opened, region, distr, state, ser_id) values (?,?,?,?,?,?,?,?)");
			for (int i = 0; i < ed.getEnergolist().length; i++){
		    	ps.setString(1, ed.getEnergolist()[i].getCode());
		    	ps.setString(2, ed.getEnergolist()[i].getName());
		    	ps.setString(3, ed.getEnergolist()[i].getName_rus());
		    	ps.setString(4, ed.getEnergolist()[i].getOpened());
		    	ps.setString(5, ed.getEnergolist()[i].getRegion());
		    	ps.setString(6, ed.getEnergolist()[i].getLocal_region());
		    	ps.setString(7, ed.getEnergolist()[i].getState());
		    	ps.setString(8, "ed");
		    	if (ps.executeUpdate() != 1) {
		    		throw new Exception("insert into BF_MUNIS_CBREFERENCE_LIST finished with ERROR");
		    	}
		    	//System.out.println("code: "+err.getErrors()[i].getCode()+"; message: "+err.getErrors()[i].getMessage());
		    }
		    c.commit();
		    com.is.munisCB.XMLSerializer.write(ed, "c:/munis/referances/refEnergoDivisions.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return ed;
	}
	
	private com.cb.munis.GasDivisions getRefGasDivisions(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.GasDivisions gd = null;
		Connection c = null;
		PreparedStatement ps = null;
		try{
			WsProxy stub = new WsProxy(url);
		    gd = stub.getRefGasDivisions(bank); 
		    c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into BF_MUNIS_CBREFERENCE_LIST( id, name, name_ru, opened, region, distr, state, ser_id) values (?,?,?,?,?,?,?,?)");
			for (int i = 0; i < gd.getGaslist().length; i++){
		    	ps.setString(1, gd.getGaslist()[i].getCode());
		    	ps.setString(2, gd.getGaslist()[i].getName());
		    	ps.setString(3, gd.getGaslist()[i].getName_rus());
		    	ps.setString(4, gd.getGaslist()[i].getOpened());
		    	ps.setString(5, gd.getGaslist()[i].getRegion());
		    	ps.setString(6, gd.getGaslist()[i].getLocal_region());
		    	ps.setString(7, gd.getGaslist()[i].getState());
		    	ps.setString(8, "gd");
		    	if (ps.executeUpdate() != 1) {
		    		throw new Exception("insert into BF_MUNIS_CBREFERENCE_LIST finished with ERROR");
		    	}
		    	//System.out.println("code: "+err.getErrors()[i].getCode()+"; message: "+err.getErrors()[i].getMessage());
		    }
		    c.commit();
		    com.is.munisCB.XMLSerializer.write(gd, "c:/munis/referances/refGasDivisions.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return gd;
	}
	
	private com.cb.munis.GnkDivisions getRefGNKDivisions(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.GnkDivisions gnkd = null;
		Connection c = null;
		PreparedStatement ps = null;
		try{
			WsProxy stub = new WsProxy(url);
			gnkd = stub.getRefGNKDivisions(bank); 
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into BF_MUNIS_CBREFERENCE_LIST( id, name, name_ru, opened, region, distr, state, ser_id) values (?,?,?,?,?,?,?,?)");
			for (int i = 0; i < gnkd.getGNKlist().length; i++){
		    	ps.setString(1, gnkd.getGNKlist()[i].getCode());
		    	ps.setString(2, gnkd.getGNKlist()[i].getName());
		    	ps.setString(3, gnkd.getGNKlist()[i].getName_rus());
		    	ps.setString(4, gnkd.getGNKlist()[i].getOpened());
		    	ps.setString(5, gnkd.getGNKlist()[i].getRegion());
		    	ps.setString(6, gnkd.getGNKlist()[i].getLocal_region());
		    	ps.setString(7, gnkd.getGNKlist()[i].getState());
		    	ps.setString(8, "nd");
		    	if (ps.executeUpdate() != 1) {
		    		throw new Exception("insert into BF_MUNIS_CBREFERENCE_LIST finished with ERROR");
		    	}
		    	//System.out.println("code: "+err.getErrors()[i].getCode()+"; message: "+err.getErrors()[i].getMessage());
		    }
		    c.commit();
		    com.is.munisCB.XMLSerializer.write(gnkd, "c:/munis/referances/refGNKDivisions.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return gnkd;
	}
	
	private com.cb.munis.Remainder getRemainders(com.cb.munis.Bank bank) {
		if (proxy){
			System.getProperties().put("http.proxyHost", proxyHost);
			System.getProperties().put("http.proxyPort", proxyPort);
			System.setProperty("http.nonProxyHosts", nonProxyHosts);
		}
		com.cb.munis.Remainder r = null;
		try{
			WsProxy stub = new WsProxy(url);
			r = stub.getRemainders(bank); 
		    com.is.munisCB.XMLSerializer.write(r, "c:/munis/referances/remainders.xml");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		if (proxy){
			System.getProperties().put("http.proxyHost", "");
			System.getProperties().put("http.proxyPort", "");
		}
	    return r;
	}
	
	private String getSoato(String distr){
        String res = "00000";
        if(distr.equals("016")){res = "03202";}
        else if(distr.equals("006")){res = "03203";}
        else if(distr.equals("008")){res = "03206";}
        else if(distr.equals("010")){res = "03210";}
        else if(distr.equals("011")){res = "03211";}
        else if(distr.equals("012")){res = "03214";}
        else if(distr.equals("210")){res = "03217";}
        else if(distr.equals("014")){res = "03220";}
        else if(distr.equals("007")){res = "03224";}
        else if(distr.equals("015")){res = "03227";}
        else if(distr.equals("214")){res = "03230";}
        else if(distr.equals("005")){res = "03231";}
        else if(distr.equals("017")){res = "03232";}
        else if(distr.equals("018")){res = "03236";}
        else if(distr.equals("002")){res = "03405";}
        else if(distr.equals("003")){res = "03408";}
        else if(distr.equals("019")){res = "06204";}
        else if(distr.equals("022")){res = "06207";}
        else if(distr.equals("020")){res = "06212";}
        else if(distr.equals("021")){res = "06215";}
        else if(distr.equals("029")){res = "06219";}
        else if(distr.equals("023")){res = "06230";}
        else if(distr.equals("082")){res = "06232";}
        else if(distr.equals("027")){res = "06240";}
        else if(distr.equals("024")){res = "06242";}
        else if(distr.equals("025")){res = "06246";}
        else if(distr.equals("026")){res = "06258";}
        else if(distr.equals("030")){res = "06401";}
        else if(distr.equals("041")){res = "08201";}
        else if(distr.equals("037")){res = "08204";}
        else if(distr.equals("033")){res = "08209";}
        else if(distr.equals("040")){res = "08212";}
        else if(distr.equals("034")){res = "08215";}
        else if(distr.equals("036")){res = "08218";}
        else if(distr.equals("042")){res = "08220";}
        else if(distr.equals("035")){res = "08223";}
        else if(distr.equals("039")){res = "08225";}
        else if(distr.equals("032")){res = "08228";}
        else if(distr.equals("038")){res = "08235";}
        else if(distr.equals("217")){res = "08237";}
        else if(distr.equals("031")){res = "08402";}
        else if(distr.equals("209")){res = "35204";}
        else if(distr.equals("191")){res = "35207";}
        else if(distr.equals("193")){res = "35211";}
        else if(distr.equals("182")){res = "35212";}
        else if(distr.equals("184")){res = "35215";}
        else if(distr.equals("192")){res = "35218";}
        else if(distr.equals("185")){res = "35222";}
        else if(distr.equals("216")){res = "35225";}
        else if(distr.equals("187")){res = "35230";}
        else if(distr.equals("188")){res = "35233";}
        else if(distr.equals("189")){res = "35236";}
        else if(distr.equals("190")){res = "35240";}
        else if(distr.equals("183")){res = "35243";}
        else if(distr.equals("194")){res = "35250";}
        else if(distr.equals("180")){res = "35401";}
        else if(distr.equals("181")){res = "35410";}
        else if(distr.equals("045")){res = "10207";}
        else if(distr.equals("046")){res = "10212";}
        else if(distr.equals("047")){res = "10220";}
        else if(distr.equals("053")){res = "10224";}
        else if(distr.equals("048")){res = "10229";}
        else if(distr.equals("057")){res = "10231";}
        else if(distr.equals("052")){res = "10232";}
        else if(distr.equals("221")){res = "10233";}
        else if(distr.equals("056")){res = "10234";}
        else if(distr.equals("054")){res = "10235";}
        else if(distr.equals("053")){res = "10237";}
        else if(distr.equals("050")){res = "10242";}
        else if(distr.equals("213")){res = "10245";}
        else if(distr.equals("049")){res = "10246";}
        else if(distr.equals("051")){res = "10250";}
        else if(distr.equals("043")){res = "10401";}
        else if(distr.equals("062")){res = "12211";}
        else if(distr.equals("063")){res = "12216";}
        else if(distr.equals("064")){res = "12230";}
        else if(distr.equals("062")){res = "12234";}
        else if(distr.equals("065")){res = "12238";}
        else if(distr.equals("067")){res = "12244";}
        else if(distr.equals("211")){res = "12248";}
        else if(distr.equals("066")){res = "12251";}
        else if(distr.equals("058")){res = "12401";}
        else if(distr.equals("059")){res = "12408";}
        else if(distr.equals("069")){res = "14204";}
        else if(distr.equals("070")){res = "14207";}
        else if(distr.equals("078")){res = "14212";}
        else if(distr.equals("071")){res = "14216";}
        else if(distr.equals("072")){res = "14219";}
        else if(distr.equals("073")){res = "14224";}
        else if(distr.equals("074")){res = "14229";}
        else if(distr.equals("075")){res = "14234";}
        else if(distr.equals("079")){res = "14236";}
        else if(distr.equals("076")){res = "14237";}
        else if(distr.equals("077")){res = "14242";}
        else if(distr.equals("068")){res = "14401";}
        else if(distr.equals("034")){res = "14402";}
        else if(distr.equals("092")){res = "18000";}
        else if(distr.equals("080")){res = "18203";}
        else if(distr.equals("081")){res = "18206";}
        else if(distr.equals("083")){res = "18209";}
        else if(distr.equals("084")){res = "18212";}
        else if(distr.equals("085")){res = "18215";}
        else if(distr.equals("086")){res = "18216";}
        else if(distr.equals("087")){res = "18218";}
        else if(distr.equals("091")){res = "18224";}
        else if(distr.equals("095")){res = "18225";}
        else if(distr.equals("089")){res = "18227";}
        else if(distr.equals("082")){res = "18228";}
        else if(distr.equals("090")){res = "18230";}
        else if(distr.equals("034")){res = "18233";}
        else if(distr.equals("088")){res = "18235";}
        else if(distr.equals("094")){res = "18236";}
        else if(distr.equals("046")){res = "18237";}
        else if(distr.equals("093")){res = "18238";}
        else if(distr.equals("215")){res = "18405";}
        else if(distr.equals("111")){res = "22201";}
        else if(distr.equals("107")){res = "22202";}
        else if(distr.equals("099")){res = "22204";}
        else if(distr.equals("112")){res = "22205";}
        else if(distr.equals("102")){res = "22207";}
        else if(distr.equals("100")){res = "22210";}
        else if(distr.equals("101")){res = "22212";}
        else if(distr.equals("109")){res = "22214";}
        else if(distr.equals("108")){res = "22215";}
        else if(distr.equals("106")){res = "22217";}
        else if(distr.equals("110")){res = "22220";}
        else if(distr.equals("105")){res = "22221";}
        else if(distr.equals("103")){res = "22223";}
        else if(distr.equals("104")){res = "22226";}
        else if(distr.equals("098")){res = "22401";}
        else if(distr.equals("118")){res = "24206";}
        else if(distr.equals("119")){res = "24212";}
        else if(distr.equals("123")){res = "24216";}
        else if(distr.equals("120")){res = "24220";}
        else if(distr.equals("121")){res = "24226";}
        else if(distr.equals("126")){res = "24228";}
        else if(distr.equals("122")){res = "24231";}
        else if(distr.equals("124")){res = "24235";}
        else if(distr.equals("114")){res = "24401";}
        else if(distr.equals("116")){res = "24410";}
        else if(distr.equals("115")){res = "24413";}
        else if(distr.equals("197")){res = "26000";}
        else if(distr.equals("198")){res = "26262";}
        else if(distr.equals("199")){res = "26264";}
        else if(distr.equals("200")){res = "26266";}
        else if(distr.equals("201")){res = "26269";}
        else if(distr.equals("202")){res = "26273";}
        else if(distr.equals("203")){res = "26277";}
        else if(distr.equals("204")){res = "26280";}
        else if(distr.equals("205")){res = "26283";}
        else if(distr.equals("206")){res = "26287";}
        else if(distr.equals("207")){res = "26290";}
        else if(distr.equals("208")){res = "26294";}
        else if(distr.equals("132")){res = "27206";}
        else if(distr.equals("128")){res = "27404";}
        else if(distr.equals("133")){res = "27212";}
        else if(distr.equals("137")){res = "27413";}
        else if(distr.equals("135")){res = "27228";}
        else if(distr.equals("131")){res = "27233";}
        else if(distr.equals("145")){res = "27239";}
        else if(distr.equals("143")){res = "27253";}
        else if(distr.equals("039")){res = "27220";}
        else if(distr.equals("136")){res = "27237";}
        else if(distr.equals("138")){res = "27248";}
        else if(distr.equals("139")){res = "27249";}
        else if(distr.equals("141")){res = "27250";}
        else if(distr.equals("142")){res = "27255";}
        else if(distr.equals("144")){res = "27256";}
        else if(distr.equals("129")){res = "27407";}
        else if(distr.equals("131")){res = "27419";}
        else if(distr.equals("147")){res = "27426";}
        else if(distr.equals("146")){res = "27259";}
        else if(distr.equals("158")){res = "30203";}
        else if(distr.equals("159")){res = "30206";}
        else if(distr.equals("153")){res = "30209";}
        else if(distr.equals("154")){res = "30212";}
        else if(distr.equals("152")){res = "30215";}
        else if(distr.equals("157")){res = "30218";}
        else if(distr.equals("164")){res = "30221";}
        else if(distr.equals("160")){res = "30224";}
        else if(distr.equals("161")){res = "30226";}
        else if(distr.equals("162")){res = "30227";}
        else if(distr.equals("163")){res = "30230";}
        else if(distr.equals("165")){res = "30233";}
        else if(distr.equals("152")){res = "30236";}
        else if(distr.equals("166")){res = "30238";}
        else if(distr.equals("150")){res = "30401";}
        else if(distr.equals("167")){res = "30402";}
        else if(distr.equals("151")){res = "30408";}
        else if(distr.equals("149")){res = "30412";}
        else if(distr.equals("172")){res = "33204";}
        else if(distr.equals("171")){res = "33208";}
        else if(distr.equals("176")){res = "33212";}
        else if(distr.equals("178")){res = "33217";}
        else if(distr.equals("173")){res = "33220";}
        else if(distr.equals("174")){res = "33223";}
        else if(distr.equals("212")){res = "33226";}
        else if(distr.equals("177")){res = "33230";}
        else if(distr.equals("175")){res = "33233";}
        else if(distr.equals("179")){res = "33236";}
        else if(distr.equals("168")){res = "33401";}
        else if(distr.equals("170")){res = "33402";}
        else {res = "00000";}
        return res;
	}
	
	private String getGAS(String distr) {
		String res = "000";
		if(distr.equals("001")){res = "122";}
		else if(distr.equals("002")){res = "124";}
		else if(distr.equals("006")){res = "123";}
		else if(distr.equals("007")){res = "125";}
		else if(distr.equals("008")){res = "126";}
		else if(distr.equals("009")){res = "127";}
		else if(distr.equals("010")){res = "128";}
		else if(distr.equals("011")){res = "129";}
		else if(distr.equals("012")){res = "130";}
		else if(distr.equals("014")){res = "131";}
		else if(distr.equals("015")){res = "134";}
		else if(distr.equals("016")){res = "135";}
		else if(distr.equals("017")){res = "136";}
		else if(distr.equals("018")){res = "138";}
		else if(distr.equals("019")){res = "183";}
		else if(distr.equals("020")){res = "179";}
		else if(distr.equals("021")){res = "176";}
		else if(distr.equals("022")){res = "177";}
		else if(distr.equals("023")){res = "182";}
		else if(distr.equals("024")){res = "174";}
		else if(distr.equals("025")){res = "178";}
		else if(distr.equals("026")){res = "181";}
		else if(distr.equals("027")){res = "180";}
		else if(distr.equals("028")){res = "184";}
		else if(distr.equals("029")){res = "175";}
		else if(distr.equals("030")){res = "172";}
		else if(distr.equals("031")){res = "056";}
		else if(distr.equals("032")){res = "060";}
		else if(distr.equals("033")){res = "058";}
		else if(distr.equals("034")){res = "062";}
		else if(distr.equals("035")){res = "064";}
		else if(distr.equals("036")){res = "066";}
		else if(distr.equals("037")){res = "059";}
		else if(distr.equals("038")){res = "068";}
		else if(distr.equals("039")){res = "061";}
		else if(distr.equals("040")){res = "057";}
		else if(distr.equals("041")){res = "063";}
		else if(distr.equals("042")){res = "065";}
		else if(distr.equals("043")){res = "141";}
		else if(distr.equals("044")){res = "148";}
		else if(distr.equals("045")){res = "147";}
		else if(distr.equals("046")){res = "154";}
		else if(distr.equals("047")){res = "146";}
		else if(distr.equals("048")){res = "150";}
		else if(distr.equals("050")){res = "145";}
		else if(distr.equals("051")){res = "144";}
		else if(distr.equals("052")){res = "143";}
		else if(distr.equals("053")){res = "151";}
		else if(distr.equals("054")){res = "149";}
		else if(distr.equals("056")){res = "153";}
		else if(distr.equals("058")){res = "190";}
		else if(distr.equals("059")){res = "186";}
		else if(distr.equals("061")){res = "187";}
		else if(distr.equals("062")){res = "189";}
		else if(distr.equals("063")){res = "188";}
		else if(distr.equals("064")){res = "191";}
		else if(distr.equals("065")){res = "192";}
		else if(distr.equals("066")){res = "193";}
		else if(distr.equals("068")){res = "108";}
		else if(distr.equals("069")){res = "117";}
		else if(distr.equals("070")){res = "110";}
		else if(distr.equals("071")){res = "111";}
		else if(distr.equals("072")){res = "116";}
		else if(distr.equals("073")){res = "115";}
		else if(distr.equals("074")){res = "119";}
		else if(distr.equals("075")){res = "112";}
		else if(distr.equals("076")){res = "114";}
		else if(distr.equals("077")){res = "120";}
		else if(distr.equals("077")){res = "094";}
		else if(distr.equals("078")){res = "118";}
		else if(distr.equals("079")){res = "113";}
		else if(distr.equals("081")){res = "073";}
		else if(distr.equals("083")){res = "074";}
		else if(distr.equals("084")){res = "076";}
		else if(distr.equals("085")){res = "082";}
		else if(distr.equals("087")){res = "080";}
		else if(distr.equals("088")){res = "078";}
		else if(distr.equals("089")){res = "075";}
		else if(distr.equals("090")){res = "083";}
		else if(distr.equals("091")){res = "081";}
		else if(distr.equals("092")){res = "072";}
		else if(distr.equals("093")){res = "084";}
		else if(distr.equals("094")){res = "085";}
		else if(distr.equals("095")){res = "228";}
		else if(distr.equals("096")){res = "071";}
		else if(distr.equals("097")){res = "077";}
		else if(distr.equals("098")){res = "167";}
		else if(distr.equals("099")){res = "157";}
		else if(distr.equals("100")){res = "160";}
		else if(distr.equals("101")){res = "161";}
		else if(distr.equals("102")){res = "164";}
		else if(distr.equals("103")){res = "169";}
		else if(distr.equals("104")){res = "170";}
		else if(distr.equals("105")){res = "171";}
		else if(distr.equals("106")){res = "166";}
		else if(distr.equals("107")){res = "156";}
		else if(distr.equals("108")){res = "162";}
		else if(distr.equals("109")){res = "163";}
		else if(distr.equals("110")){res = "168";}
		else if(distr.equals("111")){res = "165";}
		else if(distr.equals("112")){res = "158";}
		else if(distr.equals("113")){res = "051";}
		else if(distr.equals("114")){res = "044";}
		else if(distr.equals("115")){res = "045";}
		else if(distr.equals("116")){res = "046";}
		else if(distr.equals("119")){res = "048";}
		else if(distr.equals("120")){res = "049";}
		else if(distr.equals("121")){res = "226";}
		else if(distr.equals("122")){res = "050";}
		else if(distr.equals("125")){res = "052";}
		else if(distr.equals("126")){res = "054";}
		else if(distr.equals("130")){res = "027";}
		else if(distr.equals("132")){res = "026";}
		else if(distr.equals("133")){res = "025";}
		else if(distr.equals("134")){res = "029";}
		else if(distr.equals("135")){res = "028";}
		else if(distr.equals("137")){res = "030";}
		else if(distr.equals("138")){res = "033";}
		else if(distr.equals("139")){res = "034";}
		else if(distr.equals("140")){res = "036";}
		else if(distr.equals("141")){res = "035";}
		else if(distr.equals("143")){res = "037";}
		else if(distr.equals("144")){res = "039";}
		else if(distr.equals("145")){res = "040";}
		else if(distr.equals("146")){res = "041";}
		else if(distr.equals("148")){res = "091";}
		else if(distr.equals("149")){res = "088";}
		else if(distr.equals("150")){res = "089";}
		else if(distr.equals("151")){res = "090";}
		else if(distr.equals("152")){res = "093";}
		else if(distr.equals("153")){res = "092";}
		else if(distr.equals("155")){res = "095";}
		else if(distr.equals("156")){res = "096";}
		else if(distr.equals("157")){res = "106";}
		else if(distr.equals("158")){res = "097";}
		else if(distr.equals("160")){res = "099";}
		else if(distr.equals("161")){res = "100";}
		else if(distr.equals("162")){res = "101";}
		else if(distr.equals("163")){res = "105";}
		else if(distr.equals("164")){res = "102";}
		else if(distr.equals("165")){res = "103";}
		else if(distr.equals("166")){res = "104";}
		else if(distr.equals("168")){res = "195";}
		else if(distr.equals("171")){res = "203";}
		else if(distr.equals("172")){res = "200";}
		else if(distr.equals("173")){res = "205";}
		else if(distr.equals("174")){res = "198";}
		else if(distr.equals("175")){res = "201";}
		else if(distr.equals("176")){res = "204";}
		else if(distr.equals("177")){res = "199";}
		else if(distr.equals("178")){res = "196";}
		else if(distr.equals("179")){res = "202";}
		else if(distr.equals("182")){res = "210";}
		else if(distr.equals("183")){res = "215";}
		else if(distr.equals("184")){res = "218";}
		else if(distr.equals("185")){res = "219";}
		else if(distr.equals("187")){res = "213";}
		else if(distr.equals("189")){res = "214";}
		else if(distr.equals("190")){res = "211";}
		else if(distr.equals("191")){res = "223";}
		else if(distr.equals("192")){res = "216";}
		else if(distr.equals("193")){res = "212";}
		else if(distr.equals("194")){res = "222";}
		else if(distr.equals("198")){res = "009";}
		else if(distr.equals("199")){res = "013";}
		else if(distr.equals("200")){res = "007";}
		else if(distr.equals("201")){res = "004";}
		else if(distr.equals("202")){res = "003";}
		else if(distr.equals("203")){res = "012";}
		else if(distr.equals("204")){res = "006";}
		else if(distr.equals("205")){res = "010";}
		else if(distr.equals("206")){res = "005";}
		else if(distr.equals("207")){res = "220";}
		else if(distr.equals("207")){res = "011";}
		else if(distr.equals("208")){res = "008";}
		else if(distr.equals("210")){res = "137";}
		else if(distr.equals("212")){res = "197";}
		else if(distr.equals("213")){res = "142";}
		else if(distr.equals("214")){res = "139";}
		else if(distr.equals("216")){res = "209";}
		else if(distr.equals("217")){res = "069";}
		else if(distr.equals("221")){res = "152";}
		else {res = "000";}
        return res;
	}
	
	private String getGNI(String distr) {
		String res = "0000";
		if(distr.equals("001")){res = "0301";}
		else if(distr.equals("002")){res = "0302";}
		else if(distr.equals("003")){res = "0303";}
		else if(distr.equals("004")){res = "0305";}
		else if(distr.equals("006")){res = "0307";}
		else if(distr.equals("007")){res = "0315";}
		else if(distr.equals("008")){res = "0308";}
		else if(distr.equals("009")){res = "0309";}
		else if(distr.equals("010")){res = "0310";}
		else if(distr.equals("011")){res = "0311";}
		else if(distr.equals("012")){res = "0312";}
		else if(distr.equals("014")){res = "0314";}
		else if(distr.equals("015")){res = "0316";}
		else if(distr.equals("016")){res = "0306";}
		else if(distr.equals("017")){res = "0318";}
		else if(distr.equals("018")){res = "0319";}
		else if(distr.equals("019")){res = "0604";}
		else if(distr.equals("020")){res = "0606";}
		else if(distr.equals("021")){res = "0607";}
		else if(distr.equals("022")){res = "0605";}
		else if(distr.equals("023")){res = "0610";}
		else if(distr.equals("024")){res = "0612";}
		else if(distr.equals("025")){res = "0608";}
		else if(distr.equals("026")){res = "0613";}
		else if(distr.equals("027")){res = "0611";}
		else if(distr.equals("028")){res = "0614";}
		else if(distr.equals("029")){res = "0609";}
		else if(distr.equals("030")){res = "0601";}
		else if(distr.equals("031")){res = "0812";}
		else if(distr.equals("032")){res = "0810";}
		else if(distr.equals("033")){res = "0803";}
		else if(distr.equals("034")){res = "0805";}
		else if(distr.equals("035")){res = "0809";}
		else if(distr.equals("036")){res = "0807";}
		else if(distr.equals("037")){res = "0802";}
		else if(distr.equals("038")){res = "0811";}
		else if(distr.equals("039")){res = "0808";}
		else if(distr.equals("040")){res = "0804";}
		else if(distr.equals("041")){res = "0801";}
		else if(distr.equals("042")){res = "0806";}
		else if(distr.equals("043")){res = "1001";}
		else if(distr.equals("044")){res = "1007";}
		else if(distr.equals("045")){res = "1004";}
		else if(distr.equals("046")){res = "1005";}
		else if(distr.equals("047")){res = "1006";}
		else if(distr.equals("048")){res = "1008";}
		else if(distr.equals("050")){res = "1014";}
		else if(distr.equals("051")){res = "1016";}
		else if(distr.equals("052")){res = "1009";}
		else if(distr.equals("053")){res = "1012";}
		else if(distr.equals("054")){res = "1011";}
		else if(distr.equals("055")){res = "1013";}
		else if(distr.equals("056")){res = "1010";}
		else if(distr.equals("058")){res = "1201";}
		else if(distr.equals("059")){res = "1202";}
		else if(distr.equals("060")){res = "1203";}
		else if(distr.equals("061")){res = "1211";}
		else if(distr.equals("062")){res = "1204";}
		else if(distr.equals("063")){res = "1205";}
		else if(distr.equals("064")){res = "1208";}
		else if(distr.equals("065")){res = "1210";}
		else if(distr.equals("066")){res = "1209";}
		else if(distr.equals("067")){res = "1207";}
		else if(distr.equals("068")){res = "1401";}
		else if(distr.equals("069")){res = "1407";}
		else if(distr.equals("070")){res = "1408";}
		else if(distr.equals("071")){res = "1410";}
		else if(distr.equals("072")){res = "1411";}
		else if(distr.equals("073")){res = "1412";}
		else if(distr.equals("074")){res = "1413";}
		else if(distr.equals("075")){res = "1414";}
		else if(distr.equals("076")){res = "1416";}
		else if(distr.equals("077")){res = "1417";}
		else if(distr.equals("078")){res = "1409";}
		else if(distr.equals("079")){res = "1415";}
		else if(distr.equals("080")){res = "1808";}
		else if(distr.equals("081")){res = "1809";}
		else if(distr.equals("082")){res = "1810";}
		else if(distr.equals("083")){res = "1811";}
		else if(distr.equals("084")){res = "1812";}
		else if(distr.equals("085")){res = "1813";}
		else if(distr.equals("086")){res = "1814";}
		else if(distr.equals("087")){res = "1815";}
		else if(distr.equals("088")){res = "1821";}
		else if(distr.equals("089")){res = "1818";}
		else if(distr.equals("090")){res = "1819";}
		else if(distr.equals("091")){res = "1817";}
		else if(distr.equals("092")){res = "1820";}
		else if(distr.equals("093")){res = "1804";}
		else if(distr.equals("094")){res = "1822";}
		else if(distr.equals("095")){res = "1816";}
		else if(distr.equals("096")){res = "1801";}
		else if(distr.equals("097")){res = "1806";}
		else if(distr.equals("098")){res = "2201";}
		else if(distr.equals("099")){res = "2205";}
		else if(distr.equals("100")){res = "2207";}
		else if(distr.equals("101")){res = "2208";}
		else if(distr.equals("102")){res = "2206";}
		else if(distr.equals("103")){res = "2213";}
		else if(distr.equals("104")){res = "2214";}
		else if(distr.equals("105")){res = "2215";}
		else if(distr.equals("106")){res = "2211";}
		else if(distr.equals("107")){res = "2203";}
		else if(distr.equals("108")){res = "2210";}
		else if(distr.equals("109")){res = "2209";}
		else if(distr.equals("110")){res = "2212";}
		else if(distr.equals("111")){res = "2204";}
		else if(distr.equals("112")){res = "2216";}
		else if(distr.equals("113")){res = "2414";}
		else if(distr.equals("114")){res = "2410";}
		else if(distr.equals("115")){res = "2413";}
		else if(distr.equals("116")){res = "2412";}
		else if(distr.equals("117")){res = "2411";}
		else if(distr.equals("118")){res = "2401";}
		else if(distr.equals("119")){res = "2402";}
		else if(distr.equals("120")){res = "2403";}
		else if(distr.equals("121")){res = "2409";}
		else if(distr.equals("122")){res = "2407";}
		else if(distr.equals("123")){res = "2406";}
		else if(distr.equals("124")){res = "2408";}
		else if(distr.equals("126")){res = "2405";}
		else if(distr.equals("127")){res = "2719";}
		else if(distr.equals("128")){res = "2718";}
		else if(distr.equals("129")){res = "2716";}
		else if(distr.equals("130")){res = "2717";}
		else if(distr.equals("131")){res = "2720";}
		else if(distr.equals("132")){res = "2707";}
		else if(distr.equals("133")){res = "2708";}
		else if(distr.equals("134")){res = "2703";}
		else if(distr.equals("135")){res = "2702";}
		else if(distr.equals("136")){res = "2704";}
		else if(distr.equals("137")){res = "2701";}
		else if(distr.equals("138")){res = "2706";}
		else if(distr.equals("139")){res = "2709";}
		else if(distr.equals("140")){res = "2714";}
		else if(distr.equals("141")){res = "2710";}
		else if(distr.equals("142")){res = "2711";}
		else if(distr.equals("143")){res = "2712";}
		else if(distr.equals("144")){res = "2713";}
		else if(distr.equals("145")){res = "2705";}
		else if(distr.equals("146")){res = "2715";}
		else if(distr.equals("148")){res = "3003";}
		else if(distr.equals("149")){res = "3004";}
		else if(distr.equals("150")){res = "3005";}
		else if(distr.equals("151")){res = "3001";}
		else if(distr.equals("152")){res = "3006";}
		else if(distr.equals("153")){res = "3007";}
		else if(distr.equals("154")){res = "3008";}
		else if(distr.equals("155")){res = "3009";}
		else if(distr.equals("156")){res = "3010";}
		else if(distr.equals("157")){res = "3011";}
		else if(distr.equals("158")){res = "3012";}
		else if(distr.equals("159")){res = "3013";}
		else if(distr.equals("160")){res = "3014";}
		else if(distr.equals("161")){res = "3015";}
		else if(distr.equals("162")){res = "3016";}
		else if(distr.equals("163")){res = "3017";}
		else if(distr.equals("164")){res = "3018";}
		else if(distr.equals("165")){res = "3019";}
		else if(distr.equals("166")){res = "3020";}
		else if(distr.equals("168")){res = "3301";}
		else if(distr.equals("169")){res = "3302";}
		else if(distr.equals("171")){res = "3307";}
		else if(distr.equals("172")){res = "3311";}
		else if(distr.equals("173")){res = "3306";}
		else if(distr.equals("174")){res = "3312";}
		else if(distr.equals("175")){res = "3309";}
		else if(distr.equals("176")){res = "3310";}
		else if(distr.equals("177")){res = "3308";}
		else if(distr.equals("178")){res = "3304";}
		else if(distr.equals("179")){res = "3313";}
		else if(distr.equals("180")){res = "3501";}
		else if(distr.equals("181")){res = "3504";}
		else if(distr.equals("182")){res = "3511";}
		else if(distr.equals("183")){res = "3520";}
		else if(distr.equals("184")){res = "3512";}
		else if(distr.equals("185")){res = "3514";}
		else if(distr.equals("187")){res = "3516";}
		else if(distr.equals("188")){res = "3517";}
		else if(distr.equals("189")){res = "3518";}
		else if(distr.equals("190")){res = "3519";}
		else if(distr.equals("191")){res = "3509";}
		else if(distr.equals("192")){res = "3513";}
		else if(distr.equals("193")){res = "3522";}
		else if(distr.equals("194")){res = "3521";}
		else if(distr.equals("195")){res = "3510";}
		else if(distr.equals("198")){res = "2610";}
		else if(distr.equals("199")){res = "2611";}
		else if(distr.equals("200")){res = "2603";}
		else if(distr.equals("201")){res = "2602";}
		else if(distr.equals("202")){res = "2601";}
		else if(distr.equals("203")){res = "2605";}
		else if(distr.equals("204")){res = "2609";}
		else if(distr.equals("205")){res = "2607";}
		else if(distr.equals("206")){res = "2604";}
		else if(distr.equals("207")){res = "2608";}
		else if(distr.equals("208")){res = "2606";}
		else if(distr.equals("209")){res = "3508";}
		else if(distr.equals("210")){res = "0313";}
		else if(distr.equals("212")){res = "3305";}
		else if(distr.equals("215")){res = "1803";}
		else if(distr.equals("216")){res = "3515";}
		else if(distr.equals("217")){res = "0813";}
		else if(distr.equals("218")){res = "1805";}
		else if(distr.equals("219")){res = "1807";}
		else if(distr.equals("220")){res = "0603";}
		else if(distr.equals("221")){res = "1017";}
		else {res = "0000";}
        return res;
	}

}
