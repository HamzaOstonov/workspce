package com.is.korona_pay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.clientcrm.ClientA;
import com.is.clientcrm.ClientAService;
import com.is.dper_info.DperInfoDao;
import com.is.dper_info.Dper_infoViewCtrl;
import com.is.dper_info.model.dper_infoFilter;
import com.is.dper_info.render.DperInfoRenderer;
import com.is.dper_info.service.dper_infoService;
import com.is.korona_pay.KoronaPayDBHelper;
import com.is.korona_pay.model.Account;
import com.is.korona_pay.model.dper_info;
import com.is.korona_pay.service.AccountService;
import com.is.tieto_globuz.terminals.TerminalService;
import com.is.userreport.RepTempl;
import com.is.userreport.RepTempl2;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import org.zkoss.zul.Textbox;

public class KoronaPayViewCtrl extends GenericForwardComposer {

	static long serialVersionUID = 1L;
	private static final boolean isDebugEnabled = true;
	private Window edit_transfers, centsum, wzoloto, wunion, NewClient;
	private Toolbarbutton centsum$ok, centsum$btn_close, centsum$search, edit_transfers$btn_close, edit_transfers$btn_change;
	private Textbox perevod, searchButton, statetransfer, DepAcc, centsum$mtcn, centsum$val1, centsum$val2, centsum$summa, 
	        centsum$course_value, centsum$course_forex, centsum$DepAcc2, edit_transfers$absclientid, edit_transfers$uin, edit_transfers$amount,
	        edit_transfers$s_name, edit_transfers$s_family, edit_transfers$s_fatname, edit_transfers$g_name, edit_transfers$g_family, edit_transfers$g_fatname, 
	        edit_transfers$from_country, edit_transfers$to_country, edit_transfers$state;
	
	//private Decimalbox;
	private RefCBox acc_dep;
	private Decimalbox out_tmp;
	private Include newDper;
    private Iframe  fzoloto;
    
    private String clientId;
    private String currency;;

	private Listbox order;
    private Combobox orderbox;
    //public static String id = "12";
    private int uid;
    private String branch1;
    private String pwd;
    private String un;
    private String alias;
    private String distrCode;
    private String g;
    private String name_acc;
    
    
    private String accDepSelected;
	private List<Account> accDepList;
	
	private ClientA client = new ClientA();
    private Account account;
	
	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.GenericForwardComposer#doAfterCompose(org.zkoss.zk.ui.Component)
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		un = (String) session.getAttribute("un");
	   // this.alert("LOGIN == " + un);
		ISLogger.getLogger().error("1 un  === " + un);
	    System.out.println("LOGIN == " + un);
	    
	    alias = (String) session.getAttribute("alias");
	   //  this.alert("ALIAS == " + alias);
	    System.out.println("alias == " + alias);
	    ISLogger.getLogger().error("2 alias  === " + alias);
		
		pwd= (String)session.getAttribute("pwd");
	   //	this.alert("PWD   ==  " + pwd);
		System.out.println("PWD == " + pwd);
		ISLogger.getLogger().error("3 pwd  === " + pwd);
		 
		branch1 = String.valueOf(session.getAttribute("branch"));
		ISLogger.getLogger().error("4 branch1  === " + branch1);
        System.out.println("branch1 == " + branch1);
     //   this.alert("branch1== " + branch1);
        
        g = String.valueOf(session.getAttribute("uid"));
        uid = Integer.parseInt(g);
        ISLogger.getLogger().error("5 uid  === " + g);
        System.out.println("UID  == " + uid);
      //  this.alert("UID== " + uid);
        
        fzoloto.setSrc("/rr/ncb.html?" + "uid="+g +"&un="+un +"&pwd="+pwd+"&alias="+alias);
	}
    
	
	public void setClientId(String id) {
		this.clientId = id;
	}

	/*
	 * public String getClientId() { return clientId; }
	 */

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void onClick$getAddress() {
		alert(KoronaPayDBHelper.getAddress());
	}

	public void onClick$btn_transfer() {
		edit_transfers.setVisible(true);
		//alert("Изменения : " + KoronaPayDBHelper.getPerevod(statetransfer.getValue().trim()));
	}
	
	public void onChange$uin$edit_transfers() {
		HandleOperationRequest resp = KoronaPayDBHelper.getPerevod(edit_transfers$uin.getValue().trim());

		String[] fullName = resp.getSenderFullName().split(" ");
		System.out.println(resp.getSenderFullName());
		String[] fullName2 = resp.getReceiverFullName().split(" ");
		System.out.println(resp.getReceiverFullName());

		edit_transfers$absclientid.setValue(resp.getAbsClientId());
		edit_transfers$amount.setValue(String.valueOf(resp.getPayAmount()));

		edit_transfers$s_name.setValue(fullName[0]);
		edit_transfers$s_family.setValue(fullName[1]);
		edit_transfers$s_fatname.setValue(fullName.length > 2 ? fullName[2] : "N");

		edit_transfers$g_name.setValue(fullName2[0]);
		edit_transfers$g_family.setValue(fullName2[1]);
		edit_transfers$g_fatname.setValue(fullName2.length > 2 ? fullName2[2] : "N");

		edit_transfers$from_country.setValue(resp.getFromCountryIso());
		edit_transfers$to_country.setValue(resp.getToCountryIso());
		edit_transfers$state.setValue(resp.getAction().equals("1") ? "Подтвержден" : "Не подтвержден");

	}
	
	public void onClick$btn_change$edit_transfers () {
		String uin = edit_transfers$uin.getValue();
		String absclientid = edit_transfers$absclientid.getValue();
		alert(ActionService.change(uin, absclientid));
	}
	
	public void onClick$btn_close$edit_transfers () {
		edit_transfers.setVisible(false);
	}
	
	public void onClick$btn_return() {
		alert(KoronaPayDBHelper.btn_return(statetransfer.getValue().trim(), un, pwd, alias, branch1));
	}
	
	public void onClick$btn_statetransfer() {   //Подтверждение перевода
		alert(KoronaPayDBHelper.getStateTransfers(statetransfer.getValue().trim(), un, pwd, alias, branch1));			
	}
	
	public void onClick$btn_stateAcc() {
		setAcc_dep();
	}
	
	public String onChange$statetransfer() {
		clientId = getUinData(statetransfer.getValue()).getClient();
		currency = getUinData(statetransfer.getValue()).getCurrency();		
		account = AccountService.getAccount(branch1, getUinData(statetransfer.getValue()).getClient(), alias);
		if (getUinData(statetransfer.getValue().trim()).getMtcn().equals("Перевод не найден")) {
			alert("Перевод не найден");
		} else if(getUinData(statetransfer.getValue().trim()).getMtcn().equals("MTCN")){
			if (account.getAcc_dep().isEmpty()) {
				alert("Депозитный счет не найден");
			} else {DepAcc.setValue(account.getAcc_dep());}
		}
		//onSelect$acc_dep();
		//setAcc_dep();
		return statetransfer.getValue();	
	}
	
	/* ----------- DepAcc  ------------ */ 
	
	public dper_info getUinData(String uin) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		dper_info dper_info = new dper_info();
		String[] fullName = null;
		
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from korona_pay_transfers where uin = ?");
			ps.setString(1, uin);
			rs = ps.executeQuery();

			if (rs.next()) {
				
				dper_info.setVeoper("42216");
				dper_info.setEval(rs.getString("PAYCUR"));
				dper_info.setCurrency(rs.getString("PAYCUR"));
				//dper_info.setAcc_dep(acc_dep);
				//dper_info.setName_acc(name_acc);
				//dper_info.setOut_tmp(out_tmp);
				
				dper_info.setClient(rs.getString("ABSCLIENTID"));
				//clientId = rs.getString("ABSCLIENTID");
				  if (rs.getString("OPERATION").equals("0")) {
					 fullName = rs.getString("RECEIVERFULLNAME").split(" ");
				    dper_info.setClient_name1(fullName[0]);
			    	dper_info.setClient_name2(fullName[1]);
				    dper_info.setClient_name3(fullName.length > 2 ? fullName[2] : "");
				    dper_info.setMtcn("MTCN");
			    } else if (rs.getString("OPERATION").equals("1")) {
			    	fullName = rs.getString("SENDERFULLNAME").split(" ");
					dper_info.setClient_name1(fullName[0]);
					dper_info.setClient_name2(fullName[1]);
					dper_info.setClient_name3(fullName.length > 2 ? fullName[2] : "");
					dper_info.setMtcn("MTCN");
				}
			} else {
				dper_info.setMtcn("Перевод не найден");
			}

		} catch (Exception e) {
			//dper_info.setMtcn("Перевод не найден");
			e.printStackTrace();
			ISLogger.getLogger().error("getUinData:     " + e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				//dper_info.setMtcn("Перевод не найден");
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionPool.close(c);
		}
		return dper_info;	
	}
	
    private void setDepBox(List<Account> list){
		
		List<RefData> refDataList = new ArrayList<RefData>();
		
		for(int i = 0; i < list.size(); i++){
			
			refDataList.add(new RefData(Integer.toString(i), list.get(i).getAcc_dep()));
		}

		acc_dep.setModel(new ListModelList(refDataList));
	}
	
	
	
	private void setAcc_dep() {
		String accBal = AccountService.getAccBal("42216", "840",
				branch1, alias);
		System.out.println("clientID >>> " + clientId);
		List<Account> list = AccountService.getListAccount(branch1, "42216", currency, clientId, accBal, alias);
		if (list.isEmpty()) {
			System.out.println("start list is empty: ");
			openAcc(AccountService.getAccBal("42216", "840",
					branch1, alias));
		}else{
			//setDepBox(list);
			try {
			alert("Депозитный счет -> " + list.get(0).getAcc_dep());
			acc_dep.setValue(list.get(0).getAcc_dep());
			System.out.println("ACC_DEP >>> " + list.get(0).getAcc_dep());
			
			setS_out(list.get(0).getS_out_tmp());
			name_acc = (list.get(0).getName());			
			//***
			accDepList = list;
			accDepSelected = list.get(0).getAcc_dep();
			} catch (NullPointerException e) {
				
			}
			
		}
	}
	

	public void onSelect$acc_dep() {
		if (acc_dep.getItems().size() > 1) {
			String accBal = AccountService.getAccBal("42216", "840",
					branch1, alias);
			List<Account> list = AccountService.getListAccount(branch1, "42216",
					 "840", clientId, accBal, alias);
			int index = acc_dep.getSelectedIndex();
			setS_out(list.get(index).getS_out_tmp());
			name_acc = (list.get(index).getName());
		}
		
	}
	
	private void setS_out(long sout){
		double tmp = (double)sout/100;
		out_tmp.setValue(new BigDecimal(tmp));
	}
	/******************************************************************************
	 * 
	 * Open new acc_dep for customer
	 * 
	 ******************************************************************************* */
	private void openAcc(String accBal) {
		
		if(clientId != null){
			//client = ClientDao.getInstance(alias).getItem(clientId);
			client = ClientAService.getItemByStringId(branch1, clientId, un, pwd, alias);
			
		}
		
		String code = dper_infoService.user_mbranchCode(alias);
		int count = dper_infoService.user_mbranchCount(alias);
		String eval = "840";
		String msg = "";
		if (CheckNull.isEmpty(accBal) || accBal.equals("0")
				|| CheckNull.isEmpty(clientId) || CheckNull.isEmpty(eval)) {
			if (count == 0) {
				alert("(3440) Пользователь не найден в модуле Сберкассы-Центры-Банки\n или не все данные введены в Настройке счето\n"
						+ "eAccBal="
						+ accBal
						+ "  eAccVal="
						+ eval
						+ "  eClientCode=" + clientId);
				return;
			} else {
				if (count == 1)
					msg = " найден в " + code;
				else
					msg = " найден в двух минибанках";
				alert("(3440) Пользователь в модуле Сберкассы-Центры-Банки\n"
						+ msg
						+ " или не все данные введены в Настройке счето\n"
						+ "eAccBal=" + accBal + "  eAccVal=" + eval
						+ "  eClientCode=" + clientId);
				return;
			}
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Денежные переводы- ").append((client!=null&&client!=null&&client.getP_family()!=null)?client.getP_family():"NEW")
			.append(" ").append((client!=null&&client.getP_first_name()!=null)?client.getP_first_name():"NEW")
			.append(" ").append((client!=null&&client.getP_patronymic()!=null)?client.getP_patronymic():"NEW");
			
			ISLogger.getLogger().error("dper account ::: "+accBal + eval + "0" + clientId);
			ISLogger.getLogger().error("dper accountName ::: "+sb.toString());
			Res res = AccountService.dper_openAcc(accBal + eval + "0" + clientId,
					sb.toString(),un,pwd, alias);
			if(res.getCode() == 1){
				alert(res.getName());
				return;
			}
			List<Account> list = AccountService.getListAccount(branch1,
					"42216", "840", accBal, clientId,
					alias);
			if (!list.isEmpty()) {
				setAcc_dep();
			}
		}
	}
    
	
	        
	/* ----------------------------------- */ 
	       //Окно выдачи в сумах
	
	public void onClick$centsumma () {
		centsum.setVisible(true);
	}
	
	public void onClick$btn_close$centsum () {
		centsum.setVisible(false);
	}
	
	public void onClick$ok$centsum () {
    	System.out.println("ok" + centsum$mtcn.getValue());
    	
    	String mtcn = centsum$mtcn.getValue().trim();
    	ISLogger.getLogger().error("MTCN  === " + mtcn);
    	
    	double sum = Double.parseDouble(centsum$course_value.getValue());
    	
    	System.out.println("qwertyuip" + sum);
    	ISLogger.getLogger().error("qwertyuip" + sum);

    	double sum3 = Double.parseDouble(centsum$val2.getValue());
    	double sum1 = Double.parseDouble(centsum$val1.getValue());
    	
    	double summa = Double.parseDouble(centsum$summa.getValue());
    	
		System.out.println("SUM3: " + sum3);
		ISLogger.getLogger().error("SUM3: " + sum3);
    	
    	alert(KoronaPayDBHelper.centSumOk(mtcn, sum, sum3, sum1, summa, un, pwd, alias, branch1));
    	ISLogger.getLogger().error("mtcn : "+mtcn+" , sum : "+sum+" , sum3 :"+sum3+" , sum1 :"+sum1);
    	
	}
	
    
    public void onClick$search$centsum () {
    
    	String mtcn = centsum$mtcn.getValue().trim();
    	
    	KoronaPayDBHelper.centSum(mtcn);
    	System.out.println("PEREVOD NUM: ====== " + KoronaPayDBHelper.centSum(mtcn));
		
		String d1 = new BigDecimal(KoronaPayDBHelper.centSum(mtcn)).divide(new BigDecimal(100)).toString();
    	System.out.println("d1 === " + d1);
		
		
    	int cur = KoronaPayDBHelper.current(mtcn);
    	System.out.println("cur === " + cur);
    	int uzs_course = KoronaPayDBHelper.centSumma(mtcn);
    	centsum$course_forex.setValue(String.valueOf(uzs_course));
    	
    	double uz_sum = Double.parseDouble(centsum$val2.getValue());
    	
    	double resultsum = uz_sum * uzs_course;
    	
    	System.out.println("resultsumm === " + resultsum);
    	
    	centsum$course_value.setValue(String.valueOf(resultsum));
    	
    	
	}
	
    
    public String onChange$mtcn$centsum() {
    	
    	clientId = getUinData(centsum$mtcn.getValue()).getClient();
		account = AccountService.getAccount(branch1, getUinData(centsum$mtcn.getValue()).getClient(), alias);
		if (getUinData(centsum$mtcn.getValue().trim()).getMtcn().equals("Перевод не найден")) {
			alert("Перевод не найден");
		} else if(getUinData(centsum$mtcn.getValue().trim()).getMtcn().equals("MTCN")){
			if (account.getAcc_dep().isEmpty()) {
				alert("Депозитный счет не найден");
			} else {centsum$DepAcc2.setValue(account.getAcc_dep());}
		}
		
    	String d1 = new BigDecimal(KoronaPayDBHelper.centSum(centsum$mtcn.getValue())).divide(new BigDecimal(100)).toString();
    	System.out.println("d1 === " + d1);
    	
    	String forex_course = new BigDecimal(KoronaPayDBHelper.centSum(centsum$mtcn.getValue())).divide(new BigDecimal(100)).toString();
    	centsum$val1.setValue(d1);
    	centsum$val2.setValue("0");
    	
    	centsum$summa.setValue(d1);
    	
    	String mtcn = centsum$mtcn.getValue();
    	
    	return centsum$mtcn.getValue();
	}
    
    
    public String onChange$val2$centsum() {
		String result =  "";
		System.out.println("val2 ===  " + result);
		
		double sum1 = new BigDecimal(KoronaPayDBHelper.centSum(centsum$mtcn.getValue())).divide(new BigDecimal(100)).doubleValue();
		
		double sum2 = Double.parseDouble(centsum$val2.getValue()); 
		
		/*if (sum1 < sum2) {
			result = "Введённая сумма больше суммы указанной в переводе";
			System.out.println("result ===  " + result);
			alert("Введённая сумма больше суммы указанной в переводе");
		}*/
		return centsum$val2.getValue();
	}
    
    public void onSelect$orderbox(Event event) {
    	
    	String uin = statetransfer.getValue();	
    	ISLogger.getLogger().error("PrintOrders  === " + uin);
    	System.out.println("PrintOrders  === " + uin);
		KoronaPayDBHelper kgb = new KoronaPayDBHelper();
		PrintOrders print = kgb.getPrints(uin);
		alert(print.getMessage());
		System.out.println("print  === " + print);
		ISLogger.getLogger().error("print  === " + print);
		
		Map<String, Object> params =  new HashMap<String, Object>();
		  try {
		    params = RepTempl.objToMap(print, params);
		    
		  } catch (Exception e) {		    
		    e.printStackTrace();
		    ISLogger.getLogger().error("e.getMessage  === " + e.getMessage());
		  }
		  ISLogger.getLogger().error("params  === " + params);
		  System.out.println("param === " + params);
    	
    	if(orderbox.getValue().equals("Ariza Otpravka"))    		
		{
			AMedia amedia = RepTempl2.getRepmdAriza2(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/ARIZA_OTPR.docx"), "test1"+params.get("p_number"));
		System.out.println("amedia === " + amedia);
		ISLogger.getLogger().error("amedia  === " + amedia);
		  if (amedia!=null){
		    Filedownload.save(amedia);
		  }else{
		    alert("Файл не сформирован");
		  }
		} 
    	else if(orderbox.getValue().equals("Ariza Poluchenie"))    		
		{
			AMedia amedia = RepTempl2.getRepmdAriza2(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/ARIZA_POLUCH.docx"), "test1"+params.get("p_number"));
		System.out.println("amedia === " + amedia);
		ISLogger.getLogger().error("amedia  === " + amedia);
		  if (amedia!=null){
		    Filedownload.save(amedia);
		  }else{
		    alert("Файл не сформирован");
		  }
		}
    	else if(orderbox.getValue().equals("Ariza Uz"))    		
		{
			AMedia amedia = RepTempl2.getRepmdAriza(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/ARIZA_UZ.docx"), "test1"+params.get("p_number"));
		System.out.println("amedia === " + amedia);
		ISLogger.getLogger().error("amedia  === " + amedia);
		  if (amedia!=null){
		    Filedownload.save(amedia);
		  }else{
		    alert("Файл не сформирован");
		  }
		}
		else if(orderbox.getValue().equals("Order")) 
		{
			AMedia amedia = RepTempl2.getRepmdKoroni(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/koroni.docx"), "test1"+params.get("p_number"));
			System.out.println("amedia === " + amedia);
			ISLogger.getLogger().error("amedia  === " + amedia);
			  if (amedia!=null){
			    Filedownload.save(amedia);
			  }else{
			    alert("Файл не сформирован");
			  }
		}
		else if(orderbox.getValue().equals("Приходный")) {
			AMedia amedia = RepTempl2.getRepmdKoroni(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/prihod.docx"), "test1"+params.get("p_number"));
			System.out.println("amedia === " + amedia);
			ISLogger.getLogger().error("amedia  === " + amedia);
			  if (amedia!=null){
			    Filedownload.save(amedia);
			  }else{
			    alert("Файл не сформирован");
			  }
		}
		else if(orderbox.getValue().equals("Расходный")) {
			AMedia amedia = RepTempl2.getRepmdKoroni(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/rashod.docx"), "test1"+params.get("p_number"));
			System.out.println("amedia === " + amedia);
			ISLogger.getLogger().error("amedia  === " + amedia);
			  if (amedia!=null){
			    Filedownload.save(amedia);
			  }else{
			    alert("Файл не сформирован");
			  }
		}
		else if(orderbox.getValue().equals("ПриходныйСум")) {
			AMedia amedia = RepTempl2.getRepmdKoroni(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/prihodsum.docx"), "test1"+params.get("p_number"));
			System.out.println("amedia === " + amedia);
			ISLogger.getLogger().error("amedia  === " + amedia);
			  if (amedia!=null){
			    Filedownload.save(amedia);
			  }else{
			    alert("Файл не сформирован");
			  }
		}
		else if(orderbox.getValue().equals("РасходныйСум")) {
			AMedia amedia = RepTempl2.getRepmdKoroni(params, Executions.getCurrent().getDesktop().getWebApp().getRealPath("/reports/rashodsum.docx"), "test1"+params.get("p_number"));
			System.out.println("amedia === " + amedia);
			ISLogger.getLogger().error("amedia  === " + amedia);
			  if (amedia!=null){
			    Filedownload.save(amedia);
			  }else{
			    alert("Файл не сформирован");
			  }
		}
    	
    }
    
}
