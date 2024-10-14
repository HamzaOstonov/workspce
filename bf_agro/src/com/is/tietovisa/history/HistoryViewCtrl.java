package com.is.tietovisa.history;

import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;

import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.user.UserService;
import com.is.tieto.TclientService;
import com.is.tietovisa.Cons;
import com.is.tietovisa.PostUtils;
import com.is.tietovisa.StringUtils;
import com.is.tietovisa.Utils;
import com.is.tietovisa.customer.CustomerService;
import com.is.tietovisa.model.AccInfo;
import com.is.tietovisa.model.AccountBalance;
import com.is.tietovisa.model.CardInfo;
import com.is.tietovisa.model.CommonResponse;
import com.is.tietovisa.model.EditCustomerResponse;
import com.is.tietovisa.model.ListType_GenericHolder;
import com.is.tietovisa.model.NewAgreementRequest;
import com.is.tietovisa.model.NewAgreementResponse;
import com.is.tietovisa.model.NewCustomerResponse;
import com.is.tietovisa.model.Product;
import com.is.tietovisa.model.ReplaceCardResponse;
import com.is.tietovisa.model.RowType_AccountInfo;
import com.is.tietovisa.model.RowType_AccountInfo_Additional;
import com.is.tietovisa.model.RowType_AccountInfo_Base;
import com.is.tietovisa.model.RowType_AddCardToStopList_Request;
import com.is.tietovisa.model.RowType_Agreement;
import com.is.tietovisa.model.RowType_CardInfo;
import com.is.tietovisa.model.RowType_CardInfo_EMV_Data;
import com.is.tietovisa.model.RowType_CardInfo_LogicalCard;
import com.is.tietovisa.model.RowType_CardInfo_PhysicalCard;
import com.is.tietovisa.model.RowType_CardInfo_TSM_Data;
import com.is.tietovisa.model.RowType_Customer;
import com.is.tietovisa.model.RowType_EditCustomer_Request;
import com.is.tietovisa.model.RowType_RemoveCardFromStop_Request;
import com.is.tietovisa.model.RowType_ReplaceCard;
import com.is.tietovisa.model.Tclient;
import com.is.tietovisa.model.TransactionHistory;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

import java.io.InputStream;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Filedownload;
import org.zkoss.util.media.AMedia;
import java.io.ByteArrayInputStream;


public class HistoryViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CustomerFilter filter = new CustomerFilter();
	public History current = new History();
	public History curr = new History();
	public History copyOfCurrent;
	public RowType_Agreement curr_agree = new RowType_Agreement();
	public AccInfo curr_acc = new AccInfo();
	public CardInfo curr_card = new CardInfo();
	public History curr_tieto = new History();

	private AnnotateDataBinder binder;

	private int _pageSize;
	private int _startPageNumber = 0;
	private int _totalSize;
	private String un;
	private String pwd;
	private String branch;
	private String alias;
	private String curip;
	private int uid;
	static String listCustomerEndpoint;
	private static HashMap<String, String> statesNciAccount = null;
	private static HashMap<String, String> statesTieto = null;
	private static HashMap<String, String> stopCausesTieto = null;
	//List<RowsItem> items;
	List<TransactionHistory> historyList;
	List<AccountBalance> balanceList;
	List<AccInfo> accList;
	private String cardString, clientName;
	private java.util.Date periodFrom;
	private java.util.Date periodEnd;
	private int i;

	private boolean add_tie;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	//private static SimpleDateFormat bdf = new SimpleDateFormat("dd.mm.yyyy");
	private static SimpleDateFormat lsdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    SimpleDateFormat sdf;
    
    CustomerService customerService;
    
	private Window add_everywhere, add_agree_acc_card, add_card_wnd, lock_card_wnd, reissue_card_wnd, printwnd;
	private Datebox add_everywhere$ap_birthday;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Grid add_everywhere$addgrdl;
	private Grid add_everywhere$addgrdr;
	private Grid tfgrd, add_agree_acc_card$addgrdl;
	private Grid add_card_wnd$addgrdl;

	private RefCBox add_everywhere$acode_country;
	private RefCBox add_everywhere$acode_resident;
	private RefCBox add_everywhere$ap_code_adr_distr;
	private RefCBox add_everywhere$ap_code_adr_region;
	private RefCBox add_everywhere$ap_code_citizenship;
	private RefCBox add_everywhere$ap_code_gender;
	private RefCBox add_everywhere$ap_code_nation;
	private RefCBox add_everywhere$ap_code_tax_org;
	private RefCBox add_everywhere$ap_type_document;
	
	private RefCBox lock_card_wnd$rcb_stop_cause;
	private Row lock_card_wnd$row_stop_cause;
	private Textbox lock_card_wnd$txt_comment_text;
	private Toolbarbutton lock_card_wnd$lock_card_btn, lock_card_wnd$unlock_card_btn;
	
	private RefCBox reissue_card_wnd$card_risk_level, reissue_card_wnd$card_design;
	private Datebox reissue_card_wnd$card_expiry;
	
	private RefCBox add_agree_acc_card$agree_product,
			add_agree_acc_card$agree_bincod, add_agree_acc_card$agree_branch,
			add_agree_acc_card$acc_bal;
	private RefCBox add_agree_acc_card$acc_cond_set,
			add_agree_acc_card$card_risk_level,
			add_agree_acc_card$card_cond_set, add_agree_acc_card$card_design;
	private RefCBox add_agree_acc_card$card_chip;

	// private Textbox add_everywhere$aid_client;
	private Textbox add_everywhere$ao_city;
	private Textbox add_everywhere$ap_zip_code;
	private Textbox add_everywhere$ap_birth_place;
	private Textbox add_everywhere$ap_email_address;
	private Textbox add_everywhere$ap_family;
	private Textbox add_everywhere$ap_first_name;
	// private Textbox add_everywhere$ap_inps;
	private Textbox add_everywhere$ap_number_tax_registration;
	private Textbox add_everywhere$ap_passport_number;
	private Textbox add_everywhere$ap_passport_place_registration;
	private Textbox add_everywhere$ap_passport_serial;
	private Textbox add_everywhere$ap_patronymic;
	// private Textbox add_everywhere$ap_phone_home;
	private Textbox add_everywhere$ap_phone_mobile;
	private Textbox add_everywhere$ap_post_address;
	private Textbox add_everywhere$ap_pinfl;
	// private Textbox add_everywhere$customerId;
	private Textbox add_everywhere$ao_security_name;
	private Textbox add_everywhere$ao_post_address_fact;
	private Textbox add_agree_acc_card$id_order, add_agree_acc_card$tranz_acct, add_agree_acc_card$acc_non_reduce_balance;
	//private Textbox add_agree_acc_card$o_comment_text;
	private Textbox add_card_wnd$o_rbs_number;
	//private Textbox add_card_wnd$o_comment_text;

	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox cardHistoryDataGrid, branch_customers, tietoGrid, accGrid, cardGrid;
    

	private Iframe printwnd$rpframe;
	
	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Textbox txbPassportSerial;
	private Textbox txbName;
	private Datebox dbxB_date;
	private Textbox txbId_tieto, txbCardNumber, txbMobilePhone, txbLastName,
			txbClient_b;

    private Button getCardHistory;
    private Button historyToExcel, historyToPDF;
    private Datebox dateFrom;
    private Datebox dateTo;
    private Textbox cardNumber;

	
	public HistoryViewCtrl() {
		super('$', false, false);
		this._pageSize = 50;
		this.customerService = new CustomerService();
		this.sdf = new SimpleDateFormat("yyyy-MM-dd");

		add_tie = false;
		add_bnk = false;

	}

	public void doAfterCompose(final Component comp) throws Exception,
			ConnectException, SQLException {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);

		binder.bindBean("filter", this.filter);
		binder.bindBean("current", this.current);
		binder.bindBean("curr", this.curr);
		binder.bindBean("curr_acc", this.curr_acc);
		binder.bindBean("curr_card", this.curr_card);
		binder.bindBean("curr_tieto", this.curr_tieto);
		
		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		com.is.tietovisa.customer.CustomerService.initConst(alias);

		//listCustomerEndpoint = HistoryService.mapConst
		//		.get(Cons.url_listcustomers);

		binder.loadAll();
	}

	 public void onClick$getCardHistory() throws JsonParseException, JsonMappingException, IOException {
	        this.cardString = this.cardNumber.getValue();
	        this.periodFrom = this.dateFrom.getValue();
	        this.periodEnd = this.dateTo.getValue();
	        i=0;
	        //alert(this.cardString);
	        this.historyToExcel.setDisabled(false);
	        this.historyToPDF.setDisabled(false);

	        historyList = com.is.tietovisa.customer.CustomerService
			.getCardHistory_tieto(
					cardString, periodFrom, periodEnd, "",
					CustomerService.mapConst
							.get(Cons.url_cardhistory));

	        /*historyList = com.is.tietovisa.customer.CustomerService
			.getCardHistory_tieto_fake(
					cardString, periodFrom, periodEnd, "",
					CustomerService.mapConst
							.get(Cons.url_cardhistory));*/
	        
	        /*List<TransactionHistory>*/ balanceList = com.is.tietovisa.customer.CustomerService
			.getAccountBalance_tieto(
					cardString, periodFrom, periodEnd, "",
					CustomerService.mapConst
							.get(Cons.url_accountbalance));
	        
	        accList = com.is.tietovisa.customer.CustomerService
			.getAccountsByCard_tieto(
					cardString,"",
					CustomerService.mapConst
							.get(Cons.url_listaccountsbycard ));
	        if (accList.size()>=1)
	        clientName=accList.get(0).getCard_name();
	        
	        cardHistoryDataGrid.setModel(new BindingListModelList(historyList, true));
	        this.cardHistoryDataGrid.setItemRenderer((ListitemRenderer)new ListitemRenderer() {
	            public void render(final Listitem row, final Object data) throws Exception {
	                final TransactionHistory history = (TransactionHistory)data;
	                row.appendChild((Component)new Listcell(String.valueOf(++i)));
	                row.appendChild((Component)new Listcell((history.getPOST_DATE() == null) ? "" : history.getPOST_DATE()));
	                row.appendChild((Component)new Listcell((history.getTRAN_DATE_TIME() == null) ? "" : history.getTRAN_DATE_TIME()));
	                //row.appendChild((Component)new Listcell((history.getACQREF_NR() == null) ? "" : history.getACQREF_NR()));
	                row.appendChild((Component)new Listcell((history.getAPR_CODE() == null) ? "" : history.getAPR_CODE()));
	                //row.appendChild((Component)new Listcell((history.getMERCHANT() == null) ? "" : history.getMERCHANT()));
	                row.appendChild((Component)new Listcell(((history.getABVR_NAME() == null) ? "" : history.getABVR_NAME()) + " "+ ((history.getCITY() == null) ? "" : history.getCITY())));
	                row.appendChild((Component)new Listcell((history.getTRAN_TYPE() == null) ? "" : history.getTRAN_TYPE()));
	                
	                row.appendChild((Component)new Listcell((history.getTRAN_AMT() == null) ? "" : String.format("%,.2f", Double.parseDouble(history.getTRAN_AMT())/100)));
	                row.appendChild((Component)new Listcell((history.getTRAN_CCY() == null) ? "" : history.getTRAN_CCY()));
	                row.appendChild((Component)new Listcell((history.getAMOUNT_NET() == null) ? "" : String.format("%,.2f", Double.parseDouble(history.getAMOUNT_NET())/100)));        
	                row.appendChild((Component)new Listcell((history.getACCNT_CCY() == null) ? "" : history.getACCNT_CCY()));
	        
	                row.appendChild((Component)new Listcell((history.getTR_CODE() == null) ? "" : history.getTR_CODE()));
	                row.appendChild((Component)new Listcell((history.getTR_FEE() == null) ? "" : String.format("%,.2f", Double.parseDouble(history.getTR_FEE())/100)));

	            }
	        });
	        
	        this.historyToExcel.addEventListener("onClick", (EventListener)new EventListener() {
	            public void onEvent(final Event event) throws Exception {
	            	System.out.println("-"+balanceList.size()+"-"+historyList.size()+"-"+cardNumber.getValue()+"-"+sdf.format(dateFrom.getValue())+"-"+sdf.format(dateTo.getValue()));
	                final InputStream mediais = new ByteArrayInputStream(customerService.getHistoryExcel(historyList, balanceList, clientName, cardNumber.getValue(), sdf.format(dateFrom.getValue()), sdf.format(dateTo.getValue()), alias));
	                final AMedia b = new AMedia(String.valueOf(String.format("%s_%s_%s", cardString, sdf.format(periodFrom), sdf.format(periodEnd))) + ".xlsx", "xlsx", "application/xlsx", mediais);
	                Filedownload.save((Media)b);
	            }
	        });

	        this.historyToPDF.addEventListener("onClick", (EventListener)new EventListener() {
	            public void onEvent(final Event event) throws Exception {
	            	
	            	System.out.println("-"+balanceList.size()+"-"+historyList.size()+"-"+cardNumber.getValue()+"-"+sdf.format(dateFrom.getValue())+"-"+sdf.format(dateTo.getValue()));
	            	
	            	printwnd.setVisible(true);
	            	AMedia repmd = null;
	        	    
	        	    HashMap<String, Object> params = new HashMap<String, Object>();
	        	    params.put("ReportTitle", "Otchet N элчихонага");
	        	    params.put("Author", "Prepared By Manisha");
	        	    
	        	    params.put("POSTING_PERIOD", "Posting period: "+sdf.format(dateFrom.getValue())+" - "+sdf.format(dateTo.getValue()));
	        	    params.put("ACCOUNT", "Account#: "+balanceList.get(0).getACCOUNT_NO());
	        	    params.put("CLIENT_NAME", "Client name: "+clientName);
	                if (cardString.substring(0, 10).equals("4187800000"))
	                	params.put("ACCOUNT_COND",String.format("Account cond: %s", "Classic"));
	                else if (cardString.substring(0, 10).equals("4187800030"))
	                	params.put("ACCOUNT_COND",String.format("Account cond: %s", "Gold"));
	                else if (cardString.substring(0, 10).equals("4187800060"))
	                	params.put("ACCOUNT_COND",String.format("Account cond: %s", "Platinum"));
	                else
	                	params.put("ACCOUNT_COND",String.format("Account cond: %s", ""));
	                params.put("DATEPARAM", String.format("Date: %s", df.format(Utils.getInfoDate(alias, new com.is.utils.Res()))));
	        	    
	                params.put("CURRENT_BALANCE",String.format("Current balance: %s", (new BigDecimal(balanceList.get(0).getEND_BAL()).divide(new BigDecimal("100")).toString())));
	                params.put("AVAILABLE_AMOUNT",String.format("Available amount: %s", (new BigDecimal(balanceList.get(0).getAVAIL_AMOUNT()).divide(new BigDecimal("100")).toString())));
	                params.put("CREDIT_LIMIT",String.format("Credit limit: %s", ""));
	        	    params.put("PRINTEDTIME", "Printed: "+lsdf.format(new java.util.Date()));
	        	    

	        	    
	        	    java.sql.Connection c = null;
	        	    try {
	        	      c = ConnectionPool.getConnection(alias);
	        	      ArrayList<RefData> dataList =  new ArrayList<RefData>(); 
	        	      dataList.add(new RefData("Manisha", "India"));
	        	      dataList.add(new RefData("Dennis Ritchie", "USA"));
	        	      dataList.add(new RefData("V.Anand", "India"));
	        	      dataList.add(new RefData("Shrinath", "California"));
	        	      
	        	      JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
	        	      
	        	      JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(historyList);
	        	      
	        	      
	        	      //byte[] buf = net.sf.jasperreports.engine.JasperRunManager.runReportToPdf(application.getRealPath("reports/TI_PRIHOD_ORDER.jasper"), params, c);
	        	      byte[] buf = net.sf.jasperreports.engine.JasperRunManager.runReportToPdf(application.getRealPath("reports/visa_history.jasper"), params, dataSource);
	        	      
	        	      java.io.InputStream mediais = new java.io.ByteArrayInputStream(buf);
	        	      repmd = new org.zkoss.util.media.AMedia( "visa_history_" + cardNumber.getValue() + ".pdf", "pdf", "application/pdf", mediais);
	        	    } catch (Exception e) {
	        	      e.printStackTrace();
	        	    }
	        	    if (repmd != null) {
	        		      printwnd$rpframe.setContent(repmd);
	        		    }
	            	
	            }
	        });

	 }

	public void onClick$nbtn_search() {

		/*
		 * if ( (filter.getId_client()==null ||filter.getId_client().equals(""))
		 * && (filter.getP_pinfl()==null ||filter.getP_pinfl().equals("")) &&
		 * (filter.getP_first_name()==null
		 * ||filter.getP_first_name().equals("")) && (filter.getP_family
		 * ()==null ||filter.getP_family().equals("")) && (filter.getP_birthday
		 * ()==null ||filter.getP_birthday().equals("")) &&
		 * (filter.getP_phone_mobile ()==null
		 * ||filter.getP_phone_mobile().equals("")) &&
		 * (filter.getId_tieto()==null ||filter.getId_tieto().equals("")) &&
		 * (filter.getClient_b()==null ||filter.getClient_b().equals("")) ) {
		 * alert("Введите хотябы один параметр поиска!"); return; }
		 */

		
		//ISLogger.getLogger().error(	"onClick$nbtn_search start! ");

        ISLogger.getLogger().error(
		  "onClick$nbtn_search filter : "
				+ txbId_client.getValue()+"-"+filter.getId_client()+ " !!! " 
				+ txbName.getValue()+"-"+filter.getP_first_name()+ " !!! " 
				+ txbLastName.getValue()+"-"+filter.getP_family()+ " !!! " 
				+ dbxB_date.getValue()+"-"+filter.getP_birthday()+ " !!! " 
				+ txbPinfl.getValue()+"-"+filter.getP_pinfl()+ " !!! "	);

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		filter.setFilter_type("N");
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);

		ISLogger.getLogger().error(
				"onClick$nbtn_search end! ");
	}
	
	
	public void onClick$btn_clear_filter() {
		filter = new CustomerFilter();
		binder.loadComponent(tfgrd);
	}

	public void onClick$abtn_search() {

		/*
		 * if ( (filter.getId_client()==null ||filter.getId_client().equals(""))
		 * && (filter.getP_pinfl()==null ||filter.getP_pinfl().equals("")) &&
		 * (filter.getP_first_name()==null
		 * ||filter.getP_first_name().equals("")) && (filter.getP_family
		 * ()==null ||filter.getP_family().equals("")) && (filter.getP_birthday
		 * ()==null ||filter.getP_birthday().equals("")) &&
		 * (filter.getP_phone_mobile ()==null
		 * ||filter.getP_phone_mobile().equals("")) &&
		 * (filter.getId_tieto()==null ||filter.getId_tieto().equals("")) &&
		 * (filter.getClient_b()==null ||filter.getClient_b().equals("")) ) {
		 * alert("Введите хотябы один параметр поиска!"); return; }
		 */

		ISLogger.getLogger().error(
		"onClick$abtn_search start! ");

		ISLogger.getLogger().error(
		"onClick$abtn_search filter : "
				+ txbId_client.getValue()+"-"+filter.getId_client()+ " !!! " 
				+ txbName.getValue()+"-"+filter.getP_first_name()+ " !!! " 
				+ txbLastName.getValue()+"-"+filter.getP_family()+ " !!! " 
				+ dbxB_date.getValue()+"-"+filter.getP_birthday()+ " !!! " 
				+ txbPinfl.getValue()+"-"+filter.getP_pinfl()+ " !!! "	);

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		filter.setFilter_type("A");
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
		ISLogger.getLogger().error(
				"onClick$abtn_search end! ");

	}

	public void onClick$tbtn_search() {

		if ((filter.getP_first_name() == null || filter.getP_first_name()
				.equals(""))
				&& (filter.getP_family() == null || filter.getP_family()
						.equals(""))
				&& (filter.getP_birthday() == null || filter.getP_birthday()
						.equals(""))
				&& (filter.getP_phone_mobile() == null || filter
						.getP_phone_mobile().equals(""))
				&& (filter.getId_tieto() == null || filter.getId_tieto()
						.equals(""))
				&& (filter.getT_client_b() == null || filter.getT_client_b()
						.equals(""))) {
			alert("Введите хотябы один поле по Tieto!");
			return;
		}

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		filter.setFilter_type("T");
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
	}

	private void refreshModel(final int activePage) {
		// nci bankdan va tietodan klientlar ruyxatini olish
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, this.alias);
		//System.out.println(this.model);
		// tietodan ham olamiz va ikkala natijani birlashtirib kursatamiz
		// List<Customer> tieto_customes =
		// CustomerService.getCustomers_tieto(this.filter, this.alias,
		// openwayEndpoint,
		// true);
		// System.out.println(tieto_customes);
		this.branch_customers.setModel((ListModel) this.model);
		if (model.getSize() > 0) {
			branch_customers.setSelectedIndex(0);
			sendSelEvt();
		}
		ISLogger.getLogger().error(
		"refreshModel end! ");
				

	}

	public void onOkToFilter(Event event) {
		onClick$nbtn_search();
	}

	public void onClick$btn_add_everywhere() {
		curr = new History();
		this.add_tie = true;
		this.add_bnk = true;

		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);

		this.add_everywhere.setTitle("Создание клиента [NCIBANK] - [TIETO]");

		// loaddata qilamiz shu yerda
		loadRefData();
		//
		// current.setO_client_type("PR");
		curr.setCode_resident("1");
		curr.setCode_country("860");
		curr.setP_code_citizenship("860");
		// CustomerService.prepareFakeValues(current);

		this.add_everywhere.setVisible(true);
		binder.loadComponent(add_everywhere);

	}

	public void loadRefData() {

		if (add_everywhere$ap_type_document.getItems().size() == 0)
			add_everywhere$ap_type_document.setModel(new ListModelList(
					HistoryService.getType_document(alias)));
		if (add_everywhere$ap_code_citizenship.getItems().size() == 0)
			add_everywhere$ap_code_citizenship.setModel(new ListModelList(
					HistoryService.getCountry(this.alias)));
		if (add_everywhere$acode_country.getItems().size() == 0)
			add_everywhere$acode_country.setModel(new ListModelList(
					HistoryService.getCountry(this.alias)));
		if (add_everywhere$ap_code_gender.getItems().size() == 0)
			add_everywhere$ap_code_gender.setModel(new ListModelList(
					com.is.tietovisa.customer.CustomerService
							.getGender(this.alias)));
		if (add_everywhere$ap_code_nation.getItems().size() == 0)
			add_everywhere$ap_code_nation.setModel(new ListModelList(
					com.is.tietovisa.customer.CustomerService
							.getNation(this.alias)));
		if (add_everywhere$ap_code_adr_region.getItems().size() == 0)
			add_everywhere$ap_code_adr_region.setModel(new ListModelList(
					HistoryService.getRegion(this.alias)));
		if (add_everywhere$ap_code_tax_org.getItems().size() == 0)
			add_everywhere$ap_code_tax_org.setModel(new ListModelList(
					HistoryService.getTax(this.alias)));
		if (add_everywhere$acode_resident.getItems().size() == 0)
			add_everywhere$acode_resident.setModel(new ListModelList(
					com.is.tietovisa.customer.CustomerService
							.getRezCl(this.alias)));

	}

	public void loadRefAccData() {

		/*
		 * if (add_card_wnd$agree_product.getItems().size() == 0)
		 * add_card_wnd$agree_product.setModel(new ListModelList(
		 * CustomerService.getSubProduct_code1_way4(alias)));
		 */

	}

	public void loadRefAgreeData() {
		if (add_agree_acc_card$agree_product.getItems().size() == 0)
			add_agree_acc_card$agree_product.setModel(new ListModelList(
					HistoryService.getAgreeProducts(alias)));

		if (add_agree_acc_card$agree_bincod.getItems().size() == 0)
			add_agree_acc_card$agree_bincod.setModel(new ListModelList(
					HistoryService.getBinCodes(alias)));

		if (add_agree_acc_card$agree_branch.getItems().size() == 0)
			add_agree_acc_card$agree_branch.setModel(new ListModelList(
					HistoryService.getIzdBranches(alias)));

		if (add_agree_acc_card$acc_bal.getItems().size() == 0)
			add_agree_acc_card$acc_bal.setModel(new ListModelList(
					HistoryService.getAcc_bal(alias)));

		if (add_agree_acc_card$acc_cond_set.getItems().size() == 0)
			add_agree_acc_card$acc_cond_set.setModel(new ListModelList(
					HistoryService.getAccCondSets(alias)));

		if (add_agree_acc_card$card_cond_set.getItems().size() == 0)
			add_agree_acc_card$card_cond_set.setModel(new ListModelList(
					HistoryService.getCardCondSets(alias)));
		
		if (add_agree_acc_card$card_risk_level.getItems().size() == 0)
			add_agree_acc_card$card_risk_level.setModel(new ListModelList(
					HistoryService.getRiskLevels(alias)));
		
		if (add_agree_acc_card$card_design.getItems().size() == 0)
			add_agree_acc_card$card_design.setModel(new ListModelList(
					HistoryService.getDesigns(alias)));		
		
	}

	public void onClick$close_btn$add_everywhere() {
		this.add_everywhere.setVisible(false);
		// this.fl_edit = false;

		binder.loadComponent(add_everywhere);

	}

	public void onFocus$ao_category_client$add_everywhere() {
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		/*
		 * if (add_everywhere$acode_resident.getItems().size() == 0 ||
		 * (add_everywhere$acode_resident.getValue() != current
		 * .getCode_resident())) { binder.loadComponent(add_everywhere); }
		 */
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size
		 * ()); while (add_everywhere$acode_resident.getItems().size()==0) { try
		 * { TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); }
		 * }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}

	public void onFocus$ap_type_document$add_everywhere() {
		
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		
		if (add_everywhere$ap_type_document.getItems().size() == 0 ||
		  (add_everywhere$ap_type_document.getValue() != current
		  .getP_type_document() )) { 
			binder.loadComponent(add_everywhere); 
			}
		 
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size
		 * ()); while (add_everywhere$acode_resident.getItems().size()==0) { try
		 * { TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); }
		 * }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}
	
	public void onChange$ap_code_adr_region$add_everywhere() {
		curr.setP_code_adr_region(add_everywhere$ap_code_adr_region
				.getValue());
		add_everywhere$ap_code_adr_distr.setSelectedIndex(-1);
		add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
				HistoryService.getDistrByRegion(
						add_everywhere$ap_code_adr_region.getValue(), alias)));
	}

	public void onChange$ap_code_adr_distr$add_everywhere() {
		curr.setP_code_adr_distr(add_everywhere$ap_code_adr_distr.getValue());
	}

	public void onChange$ap_code_nation$add_everywhere() {
		curr.setP_code_nation(add_everywhere$ap_code_nation.getValue());
	}

	public void onChange$ap_code_tax_org$add_everywhere() {
		curr.setP_code_tax_org(add_everywhere$ap_code_tax_org.getValue());
	}

	public void onChange$ap_code_gender$add_everywhere() {
		curr.setP_code_gender(add_everywhere$ap_code_gender.getValue());
	}

	public void onChange$acode_resident$add_everywhere() {
		curr.setCode_resident(add_everywhere$acode_resident.getValue());
	}

	public void onChange$ap_type_document$add_everywhere() {
		curr.setP_type_document(add_everywhere$ap_type_document.getValue());
	}

	public void onChange$acode_country$add_everywhere() {
		curr.setCode_country(add_everywhere$acode_country.getValue());
	}

	public void onChange$ap_code_citizenship$add_everywhere() {
		curr.setP_code_citizenship(add_everywhere$ap_code_citizenship
				.getValue());
	}

	public void onClick$btn_add_acc() {
		if (current == null || current.getId_tieto() == null) {
			alert("Клиент(TIETO) не выбран");
			return;
		}
		
		if (curr_agree != null) {
			//RowType_Agreement newAgree = new RowType_Agreement();
			//newAgree.setAGRE_NOM(curr_agree.getAGRE_NOM());
			//curr_agree=newAgree;
		} else {
			curr_agree = new RowType_Agreement();
		}		
		if (curr_acc!=null) 
			curr_acc=curr_acc.clone(curr_acc);
		else 
  		  curr_acc = new AccInfo(); 
		curr_acc.setBranch(branch);
		//curr_acc.setClient(current.getId_client());

		if (curr_card!=null) 
			curr_card=curr_card.clone(curr_card);
		else 
  		  curr_card = new CardInfo(); 

		/* CheckNull.clearForm(this.add_agree_acc_card$addgrdl); */
		// this.add_everywhere.setTitle("Открытие агримент, счет и карту [БАНК] - [Т�?ЕТО]");

		// loaddata qilamiz shu yerda
		loadRefAgreeData();
		//
		
		
		//get cookie
		//Cookie [] cookies = ((HttpServletRequest)Executions.getCurrent().getNativeRequest()).getCookies();
		//System.out.println(cookies[0].getName());
		
		this.add_agree_acc_card.setVisible(true);
		binder.loadComponent(add_agree_acc_card);

	}

	public void onClick$btn_add_card() {
		alert("www");

	}

	public void onChange$agree_product$add_agree_acc_card() {
		 curr_agree.setPRODUCT(add_agree_acc_card$agree_product.getValue());
	}
	public void onChange$agree_bincod$add_agree_acc_card() {
		 curr_agree.setBINCOD(add_agree_acc_card$agree_bincod.getValue());
	}
	public void onChange$agree_branch$add_agree_acc_card() {
		 curr_agree.setBRANCH(add_agree_acc_card$agree_branch.getValue());
	}
	public void onChange$acc_cond_set$add_agree_acc_card() {
		 curr_acc.setCond_set(add_agree_acc_card$acc_cond_set.getValue());
	}
	public void onChange$card_risk_level$add_agree_acc_card() {
		 curr_card.setRISK_LEVEL(add_agree_acc_card$card_risk_level.getValue());
	}

	public void onChange$card_cond_set$add_agree_acc_card() {
		 curr_card.setCOND_SET(add_agree_acc_card$card_cond_set.getValue());
	}
	public void onChange$card_design$add_agree_acc_card() {
		 curr_card.setDesign_id(add_agree_acc_card$card_design.getValue());
	}
	public void onChange$card_risk_level$reissue_card_wnd() {
		 curr_card.setRISK_LEVEL(reissue_card_wnd$card_risk_level.getValue());
	}
	public void onChange$card_design$reissue_card_wnd() {
		 curr_card.setDesign_id(reissue_card_wnd$card_design.getValue());
	}

	
	private void sendSelEvt() {
		/*
		 * if (dataGrid.getSelectedIndex()==0){ btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); }else{ btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); }
		 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
		 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
		 * btn_next.setDisabled(false); btn_last.setDisabled(false); }
		 */
		SelectEvent evt = new SelectEvent("onSelect", branch_customers,
				branch_customers.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onSelect$branch_customers() {
		if (current != null) {

			//list tieto customers
			List<History> custList = null;
			if (current.getP_pinfl()!=null && !current.getP_pinfl().equals("") )
			{
				CustomerFilter fc = new CustomerFilter();
				fc.setEndpoint(listCustomerEndpoint);
				fc.setP_pinfl(current.getP_pinfl());
				fc.setT_client_b(branch+"%"); 
				custList= HistoryService.getCustomersTietoFl(0, 20, fc, alias);
			}
			else
			{
				custList = new ArrayList<History>();	
			}
			tietoGrid.setModel(new BindingListModelList(custList, true));
			if (custList.size() > 0) {
				tietoGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", tietoGrid,
						tietoGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			} else {
				curr_acc = null;
				tietoGrid.setModel(new BindingListModelList(
						new ArrayList<History>(), true));
			}


			//end tieto customers
			
			
		}

	}

	public void onSelect$tietoGrid() throws ParseException {

		if (curr_tieto != null && curr_tieto.getId_tieto() != null) {


			// TIETOdan
			/*List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
					.getCards_tieto(
							BigInteger.valueOf(curr_acc.getAccount_no()), "",
							CustomerService.mapConst
									.get(Cons.url_listcardsbyaccount)); */

			List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
			.getCustomerCards_tieto(
					curr_tieto.getId_tieto(), "",
					HistoryService.mapConst
							.get(Cons.url_listcustomercards));
			
			
			cardGrid.setModel(new BindingListModelList(cardList, true));
			//

			if (cardList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			}

		} else {
			cardGrid.setModel(new BindingListModelList(
					new ArrayList<CardInfo>(), true));
		}

	}
	
	public void onSelect$accGrid() throws ParseException{
		if (curr_acc != null && curr_acc.getAccount_no() != null) {

			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			// List<CardInfo> cardList =
			// CustomerService.getContractCardList_ABS(
			// current.getBranch(), current.getId_client(),
			// curr_acc.getProductCode1(), alias);

			// TIETOdan
			List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
					.getCards_tieto(
							BigInteger.valueOf(curr_acc.getAccount_no()), "",
							HistoryService.mapConst
									.get(Cons.url_listcardsbyaccount));

			cardGrid.setModel(new BindingListModelList(cardList, true));
			//

			if (cardList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			}

		} else {
			cardGrid.setModel(new BindingListModelList(
					new ArrayList<CardInfo>(), true));
		}

	}

	public CustomerFilter getFilter() {
		return filter;
	}

	public void setFilter(CustomerFilter filter) {
		this.filter = filter;
	}

	public History getCurrent() {
		return current;
	}

	public void setCurrent(History current) {
		this.current = current;
	}

	public History getCurr_tieto() {
		return curr_tieto;
	}

	public void setCurr_tieto(History curr_tieto) {
		this.curr_tieto = curr_tieto;
	}
	
	public AccInfo getCurr_acc() {
		return curr_acc;
	}

	public void setCurr_acc(AccInfo curr_acc) {
		this.curr_acc = curr_acc;
	}

	public CardInfo getCurr_card() {
		return curr_card;
	}

	public void setCurr_card(CardInfo curr_card) {
		this.curr_card = curr_card;
	}

	public History getCurr() {
		return curr;
	}

	public void setCurr(History curr) {
		this.curr = curr;
	}

}
