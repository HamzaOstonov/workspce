package com.is.tietovisa.accpay;

 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.python.antlr.PythonParser.else_clause_return;
import org.python.google.common.base.Strings;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Column;
import org.zkoss.zul.api.Panel;
import org.zkoss.zul.api.Vbox;
import org.zkoss.zul.event.PagingEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ibm.db2.jcc.am.o;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;

import com.is.tietovisa.StringUtils;
import com.is.tietovisa.trpay.TrPay;
import com.is.tietovisa.trpay.TrPayService;
import com.is.report.DPrint;

import com.is.tietovisa.model.AccInfo;
import com.is.tietovisa.model.CardIbs;
import com.is.tietovisa.model.Refill_log;

import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;

import com.is.tietovisa.accpay.PagingListModel;
import com.is.tietovisa.customer.Customer;

import com.is.openwayutils.user.Action;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

public class AccPayViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CardIbs filter = new CardIbs();
	public CardIbs current = new CardIbs();
	public Customer copyOfCurrent;
	public AccInfo curr_acc = new AccInfo();
	public CardIbs curr_card = new CardIbs();

	private AnnotateDataBinder binder;

	private int _pageSize;
	private int _startPageNumber = 0;
	private int _totalSize;
	private String un;
	private String pwd;
	private String branch;
	private String alias;
	private String curip, cur_act;
	private int uid;
	static String openwayEndpoint;
	private boolean add_way;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private Window paywnd, printwnd, showBalanceWindow;
	private Tabbox mainTabbox;
    private Tab mainTab, confirmTab;
	private Tabpanel customersTab;
    private Window confirmWnd;
    private Div confirmDiv;
    
	private Datebox add_everywhere$ap_birthday;
	private Iframe printwnd$rpframe;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Decimalbox paywnd$amnt;

	private RefCBox add_everywhere$acode_country;
	private RefCBox add_everywhere$acode_resident;

	private RefCBox add_everywhere$ap_code_adr_region;
	private RefCBox add_everywhere$ap_code_citizenship;
	private RefCBox add_everywhere$ap_code_gender;
	private RefCBox add_everywhere$ap_code_nation;
	private RefCBox add_everywhere$ap_code_tax_org;
	private RefCBox add_everywhere$ap_type_document;
	private RefCBox add_everywhere$ao_category_client;

	private RefCBox add_account$acc_bal;
	private RefCBox add_account$o_product_code1;
	private RefCBox add_card_wnd$o_product_code1;
	private RefCBox paywnd$scurracc, paywnd$curracc_uzs;

	private Textbox paywnd$search_name, paywnd$address, paywnd$branch, paywnd$curacc,
			paywnd$curr_acc_uzs, paywnd$inc_ord_num;
	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox accGrid, cardGrid;
	private Grid showBalanceWindow$showBalanceGrid;

	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Toolbarbutton paywnd$btn_block, paywnd$btn_unblock, paywnd$btn_cancel, showBalanceWindow$showBalanceCloseBtn;
	private Toolbarbutton paywnd$fbt, paywnd$lock, paywnd$application;
	//private Toolbar paywnd$pay_tlb_left, paywnd$pay_tlb_right;
	private Vbox paywnd$pay_tlb_left, paywnd$pay_tlb_right;
	private int pay_card_doc_id;
	private NumberFormat nf = NumberFormat.getInstance();
	TrPay trp_res = null;

	// private Textbox txtPassportSerial;
	// private Textbox txtName;
	// private Datebox dbxB_date;

	public AccPayViewCtrl() {
		super('$', false, false);
		this._pageSize = 10;
		add_way = false;
		add_bnk = false;
	}

	public void doAfterCompose(final Component comp) throws Exception,
			ConnectException, SQLException {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);
        

		String s1 =  session.getLocalAddr();
		String s2 =  session.getRemoteAddr();
		if (s1.equals("0.0.0.0") && s2.equals("0:0:0:0:0:0:0:1")){
			//filter.setReal_card("4187800030162440");
			filter.setReal_card("4187800000343871");
		}
		binder.bindBean("filter", this.filter);
		binder.bindBean("current", this.current);
		// binder.bindBean("curr_acc", this.curr_acc);
		binder.bindBean("curr_card", this.curr_card);

		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		if (curip == null || curip == "" || curip.equals(""))
			curip = "1";
		com.is.tietovisa.customer.CustomerService.initConst(alias);
		if (openwayEndpoint == null || openwayEndpoint == ""
				|| openwayEndpoint.equals(""))
			openwayEndpoint = ConnectionPool.getValue("OPENWAY_ENDPOINT");
		// filter.setEndpoint(openwayEndpoint);
		

		cardGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				CardIbs pAccInfo = (CardIbs) data;

				row.setValue(pAccInfo);
				
				Listcell cell = new Listcell();
				
				//final Button btnSmsOn = new Button("on");
				Button btn = new Button();
				btn.setLabel("Операции...");
				btn.setTooltiptext("Пополнение, списание, ...");
				btn.setAttribute("curr_card", (Object) pAccInfo);

				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
                        //alert("ana man.");
						
						curr_card = (CardIbs) event.getTarget().getAttribute("curr_card");
						
						String client_id;

						List<Action> actions;
						actions = AccPayService.getActions(uid, branch, alias);
						//for(int i=0;i<paywnd$pay_tlb_right.getChildren().size();i++){
						//	  System.out.println("class=" + paywnd$pay_tlb_right.getChildren().get(i).getClass());
						//	  paywnd$pay_tlb_right.removeChild((org.zkoss.zul.Toolbarbutton)paywnd$pay_tlb_right.getChildren().get(i));
						//}

						paywnd$pay_tlb_left.getChildren().clear();
						paywnd$pay_tlb_right.getChildren().clear();
						
						
						//paywnd$pay_tlb_left.setOrient("vertical");
						//paywnd$pay_tlb_right.setOrient("vertical");
						for (int i = 0; i < actions.size(); i++) {
							paywnd$fbt = new Toolbarbutton();
							
							paywnd$fbt.setLabel(actions.get(i).getName());
							paywnd$fbt.setImage(actions.get(i).getIcon());
							paywnd$fbt.setAttribute("act_desc", actions.get(i).getName());// Пополнить
																							// карту
																							// ОПЕРАТОР
																							// (Пополнение
																							// ФИЛИАЛ)
							// paywnd$fbt.setAttribute("state_id", states.get(i).getId());
							paywnd$fbt.setAttribute("deal_id", actions.get(i).getDeal_id());
							paywnd$fbt.setAttribute("act_id", actions.get(i).getId());
							paywnd$fbt.setAttribute("rep_type_id", actions.get(i)
									.getRep_type_id());
							//paywnd$fbt.setWidth("1000px");
							paywnd$fbt.setWidth("270px");
							//paywnd$fbt.setWidth("100%");
							paywnd$fbt.addEventListener(Events.ON_CLICK, new EventListener() {
								@Override
								public void onEvent(Event event) {
									
									cur_act = (String) event.getTarget().getAttribute("act_desc");
									final int deal_id = (Integer) event.getTarget().getAttribute("deal_id");
									final int rep_type_id=(Integer) event.getTarget().getAttribute("rep_type_id");
									final int act_id =(Integer) event.getTarget().getAttribute("act_id");
									
									//sumda popolneniya bulsa sumda yozsin
									if (act_id==10) {
										
										try {
											Messagebox.show("Summani o'zbek so'mida yozdingizmi? '", "Tasdiqlash",
													Messagebox.YES | Messagebox.NO,
													Messagebox.QUESTION,
													new EventListener() {
														public void onEvent(Event e) {
															// if (Messagebox.ON_YES
															// .equals(e.getName()))
															int answer = (Integer) e
																	.getData();
															if (answer == Messagebox.YES) {
																pay(deal_id, rep_type_id, act_id);
															} else if (/*
																		 * Messagebox.ON_NO
																		 * .
																		 * equals(e.getName
																		 * ())
																		 */answer == Messagebox.YES) {
															}
														}
													});

										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										
									} else
									
									pay((Integer) event.getTarget().getAttribute("deal_id"),
											(Integer) event.getTarget().getAttribute(
													"rep_type_id"), (Integer) event.getTarget()
													.getAttribute("act_id"));

								}
							});
							if (actions.get(i).getId()==1 || actions.get(i).getId()==10 || actions.get(i).getId()==25 ) //popolneniyalar
								paywnd$pay_tlb_left.appendChild(paywnd$fbt);
							else
								paywnd$pay_tlb_right.appendChild(paywnd$fbt);
						}
						paywnd$search_name.setValue(curr_card.getCard_name());
						// paywnd$address.setValue(current.getR_street());
						paywnd$search_name.setReadonly(true);
						// paywnd$address.setReadonly(true);
						paywnd$branch.setValue(curr_card.getBranch());
						paywnd$curacc.setValue(curr_card.getTranz_acct());
						// paywnd$lock.setImage("/images/locked.png");
						paywnd.setVisible(true);
						//alert(paywnd$pay_tlb_left.getOrient());
					}
				});
				//btn.setStyle("padding: 0;");
				cell.appendChild(btn);
				row.appendChild(cell);
				
				row.appendChild(new Listcell(pAccInfo.getClient_id() ));
				row.appendChild(new Listcell(pAccInfo.getClient_b()));
				
				row.appendChild(new Listcell(pAccInfo.getBranch()));
				row.appendChild(new Listcell(pAccInfo.getCard()));
				row.appendChild(new Listcell(pAccInfo.getStatus1()));

				row.appendChild(new Listcell(StringUtils.secureNull(pAccInfo.getCard_regist_date())));
				
				row.appendChild(new Listcell(StringUtils.secureNull(pAccInfo.getExpiry1())));
				row.appendChild(new Listcell(StringUtils.secureNull(pAccInfo.getExpirity2())));
				row.appendChild(new Listcell(pAccInfo.getCard_name()));
				row.appendChild(new Listcell(pAccInfo.getReal_card()));
				row.appendChild(new Listcell(pAccInfo.getTranz_acct()));
				row.appendChild(new Listcell(pAccInfo.getCard_acct()));
				row.appendChild(new Listcell(pAccInfo.getPinfl()));
				row.appendChild(new Listcell(pAccInfo.getCond_set()));
				
				//row.appendChild(new Listcell(""));
			}
		});

		binder.loadAll();
	}

	public void onClick$showBalanceCloseBtn$showBalanceWindow()	 {
		showBalanceWindow.setVisible(false);
    }

	
	public void onClick$tbtn_search() {
		if (filter.getBranch() == null)
			filter.setBranch(branch);
		this.refreshModel(/* this._startPageNumber */0);
	}

	private void refreshModel(final int activePage) {
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, this.alias);

		this.cardGrid.setModel((ListModel) this.model);
		
		//if (model.getSize() == 0) {
		//	alert("Не найдено");
		//	return;
		//} else if (model.getSize() >= 2) {
		//	alert("Найдено больше одного запись");
		//	return;
		//} 
		//current=(CardIbs)model.getElementAt(0);
		//onDoubleClick$accGrid();
		
		//if (model.getSize() > 0) {
		//	cardGrid.setSelectedIndex(0);
		//	sendSelEvt();
		//}
		
		if (model.getSize() > 0) {
			cardGrid.setSelectedIndex(0);
			SelectEvent evt = new SelectEvent("onSelect", cardGrid,
					cardGrid.getSelectedItems());
			Events.sendEvent(evt);
		} else {
			curr_card = null;
		}
		
	}

	public void onOkToFilter(Event event) {
		onClick$tbtn_search();
	}

	public void loadRefData() {
		/*
		if (add_everywhere$ao_category_client.getItems().size() == 0)
			add_everywhere$ao_category_client.setModel(new ListModelList(
					CustomerService.getClient_type_way4(alias)));
		if (add_everywhere$ap_type_document.getItems().size() == 0)
			add_everywhere$ap_type_document.setModel(new ListModelList(
					CustomerService.getType_document(alias)));
		if (add_everywhere$ap_code_citizenship.getItems().size() == 0)
			add_everywhere$ap_code_citizenship.setModel(new ListModelList(
					CustomerService.getCountry(this.alias)));
		if (add_everywhere$acode_country.getItems().size() == 0)
			add_everywhere$acode_country.setModel(new ListModelList(
					CustomerService.getCountry(this.alias)));
		if (add_everywhere$ap_code_gender.getItems().size() == 0)
			add_everywhere$ap_code_gender.setModel(new ListModelList(Utils
					.getGender(this.alias)));
		if (add_everywhere$ap_code_nation.getItems().size() == 0)
			add_everywhere$ap_code_nation.setModel(new ListModelList(Utils
					.getNation(this.alias)));
		if (add_everywhere$ap_code_adr_region.getItems().size() == 0)
			add_everywhere$ap_code_adr_region.setModel(new ListModelList(
					CustomerService.getRegion(this.alias)));
		if (add_everywhere$ap_code_tax_org.getItems().size() == 0)
			add_everywhere$ap_code_tax_org.setModel(new ListModelList(
					CustomerService.getTax(this.alias)));
		if (add_everywhere$acode_resident.getItems().size() == 0)
			add_everywhere$acode_resident.setModel(new ListModelList(Utils
					.getRezCl(this.alias)));
        */
	}

	public void loadRefAccData() {
		/*
		if (add_account$acc_bal.getItems().size() == 0)
			add_account$acc_bal.setModel(new ListModelList(CustomerService
					.getAcc_bal(alias)));
		if (add_account$o_product_code1.getItems().size() == 0)
			add_account$o_product_code1.setModel(new ListModelList(
					CustomerService.getProduct_code1_way4(alias)));
		*/
	}

	public void onChange$acc_bal$add_account() {
		curr_acc.setAcc_bal(add_account$acc_bal.getValue());
	}

	public void onChange$o_product_code1$add_account() {
		curr_acc.setProductCode1(add_account$o_product_code1.getValue());
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
	}

	public void onDoubleClick$cardGrid() {

		String client_id;

		List<Action> actions;
		actions = AccPayService.getActions(uid, branch, alias);
		paywnd$pay_tlb_left.getChildren().clear();
		paywnd$pay_tlb_right.getChildren().clear();
		
		//paywnd$pay_tlb_left.setOrient("vertical");
		//paywnd$pay_tlb_right.setOrient("vertical");
		
		for (int i = 0; i < actions.size(); i++) {
			paywnd$fbt = new Toolbarbutton();
			
			paywnd$fbt.setLabel(actions.get(i).getName());
			paywnd$fbt.setImage(actions.get(i).getIcon());
			paywnd$fbt.setAttribute("act_desc", actions.get(i).getName());// Пополнить
																			// карту
																			// ОПЕРАТОР
																			// (Пополнение
																			// ФИЛИАЛ)
			// paywnd$fbt.setAttribute("state_id", states.get(i).getId());
			paywnd$fbt.setAttribute("deal_id", actions.get(i).getDeal_id());
			paywnd$fbt.setAttribute("act_id", actions.get(i).getId());
			paywnd$fbt.setAttribute("rep_type_id", actions.get(i)
					.getRep_type_id());
			//paywnd$fbt.setWidth("1000px");
			paywnd$fbt.setWidth("270px");
			//paywnd$fbt.setWidth("100%");
			paywnd$fbt.addEventListener(Events.ON_CLICK, new EventListener() {
				@Override
				public void onEvent(Event event) {
					cur_act = (String) event.getTarget().getAttribute(
							"act_desc");
					pay((Integer) event.getTarget().getAttribute("deal_id"),
							(Integer) event.getTarget().getAttribute(
									"rep_type_id"), (Integer) event.getTarget()
									.getAttribute("act_id"));

				}
			});
			//paywnd$pay_tlb.appendChild(paywnd$fbt);
			if (actions.get(i).getId()==1 || actions.get(i).getId()==10 || actions.get(i).getId()==25 ) //popolneniyalar
				paywnd$pay_tlb_left.appendChild(paywnd$fbt);
			else
				paywnd$pay_tlb_right.appendChild(paywnd$fbt);

		}

		paywnd$search_name.setValue(curr_card.getCard_name());
		// paywnd$address.setValue(current.getR_street());
		paywnd$search_name.setReadonly(true);
		// paywnd$address.setReadonly(true);
		paywnd$branch.setValue(curr_card.getBranch());
		paywnd$curacc.setValue(curr_card.getTranz_acct());
		
		// paywnd$lock.setImage("/images/locked.png");
		paywnd.setVisible(true);
	}

	public void onClick$btn_cancel$paywnd()	 {
		paywnd.setVisible(false);
    }

	

	
	private void pay(int deal_id, int rep_type_id, int act_id) {

		String client_id;
		String v_msg="";
		/*
		 * client_id = TclientService.check_user(alias, branch,
		 * current.getClient()); if (client_id=="") {
		 * alert("Клиент не объединен"); return; }
		 */

		/*
		 * Res res = AccPayService.check_allowed_card_action(144, deal_id,
		 * act_id, curr_acc.getProduct(), alias); if (res.getCode() != 0) {
		 * alert(res.getName()); return; }
		 */

		/*
		 * OperationConnectionInfo connectionInfo = new
		 * OperationConnectionInfo(); RowType_ExecTransaction_Request parameters
		 * = new RowType_ExecTransaction_Request(); OperationResponseInfoHolder
		 * responseInfo = new OperationResponseInfoHolder();
		 * RowType_ExecTransaction_ResponseHolder details = new
		 * RowType_ExecTransaction_ResponseHolder();
		 */
		try {

			TrPay trp = new TrPay();

			ISLogger.getLogger().error(
					"deal_id(0):=" + deal_id + ", rep_type_id:=" + rep_type_id
							+ ", act_id:=" + act_id);
			
			/*
			 * ID NUMBER sequence BRANCH VARCHAR2(5) 01142 OPERATION_ID NUMBER 1
			 * AMOUNT NUMBER Amount 50 dollar CARD_ACC VARCHAR2(20)
			 * substr(RBSNumber,5,20) 22618840... CUR_ACC VARCHAR2(20) null or 1
			 * DATE_CREATED DATE datetime PARENT_GROUP_ID NUMBER 144 STATE
			 * NUMBER 0 ACCOUNT_NO VARCHAR2(20) Y ContractNumber iz shetevoy
			 * kontrakt 9058-P-459029 CL_NAME VARCHAR2(200) Y client name EMP_ID
			 * NUMBER Y null TIETO_TYPE NUMBER Y null PAN VARCHAR2(19) Y
			 * shetevoy kontraktga tegishli bulgan 1-karta yoki ixtiyoriy
			 * 1-uchragan karta raqam DEAL_GROUP NUMBER Y 144 DEAL_ID NUMBER Y 1
			 * yoki null DOC_NUM VARCHAR2(50) Y null ili --Номер приходного
			 * ордера EQV_AMOUNT NUMBER Y Сумма в эквиваленте SUB_ID NUMBER 0
			 * AMOUNT_T NUMBER Amount 50 dollar Сумма в ТИЕТО SUBBRANCH_ID
			 * VARCHAR2(5) 00000
			 */

			trp.setBranch(branch);
			// trp.setOperation_id(2);
			trp.setAmount(Math.round((paywnd$amnt.getValue().doubleValue()) * 100));
			trp.setAmount_t(Math.round((paywnd$amnt.getValue().doubleValue())));
			trp.setCard_acc(curr_card.getTranz_acct());
			//trp.setCard_acc("22618840999079903001"); //hamza
			trp.setCard_acc_branch(curr_card.getBranch());
			trp.setCur_acc("1"); // --default
			trp.setAccount_no(curr_card.getCard_acct() );
			trp.setCl_name(curr_card.getCard_name());
			trp.setEmp_id(uid);
			//trp.setPan(TrPayService.getCardNumber_IBS(curr_acc.getBranch(),
			//		curr_acc.getClient(), curr_acc.getProductCode1(),
			//		curr_acc.getContractNumber() + "%", alias));
			trp.setPan(curr_card.getCard());
			//trp.setDoc_num(paywnd$inc_ord_num.getValue());
			trp.setDoc_num("123");

			//System.out.println("curr_acc=" + curr_acc.toString());

			System.out.println("deal_id:=" + deal_id + ", rep_type_id:="
					+ rep_type_id + ", act_id:=" + act_id);
			ISLogger.getLogger().error(
					"deal_id:=" + deal_id + ", rep_type_id:=" + rep_type_id
							+ ", act_id:=" + act_id);
			Res rs = TrPayService
					.doAction(un, pwd, trp, act_id, deal_id, alias);
			// Res rs = new Res(0, "27059543");
			if (rs.getCode() < 0) {
				alert(rs.getName());
			} else {
				trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()),
						alias);
				//trp_res.setCl_name(/* current.getSearch_name() */"хАМЗА hAMZA");
				//trp_res.setIn_name(/*paywnd$search_name.getValue()*/"тЕСТ TEST IN_NAME");
				//trp_res.setIn_address(/*paywnd$address.getValue()*/"тЕСТ TEST IN_NAME");

				printp(trp_res,
				 AccPayService.getReportTemplate(deal_id, act_id, alias),
				 deal_id, act_id);

				String oper_desc = TrPayService.getOperation_desc(
						trp_res.getOperation_id(), alias);

				String log = "Операция [" + oper_desc + "] id ["
						+ trp_res.getId() + "] действие [" + cur_act
						+ "] подгруппы ["
						+ TrPayService.getDeal_desc(deal_id, alias)
						+ "] No карты [" + trp_res.getPan() + "] счет карты ["
						+ trp_res.getCard_acc() + "] " + "(Отправитель ["
						+ paywnd$search_name.getValue() + "] Адрес ["
						+ paywnd$address.getValue() + "])";
				UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1,
						log, branch), alias);

				pay_card_doc_id = Integer.parseInt(rs.getName());
				ISLogger.getLogger().error(
						"new document for s pay id:" + pay_card_doc_id);
				ISLogger.getLogger().error(
						"deal_id=" + deal_id + ", act_id=" + act_id + ", uid="
								+ uid + ", un=" + un + ", curip=" + curip
								+ ", branch=" + branch + ", log=" + log);
				v_msg = "Операция прошла успешно в АБС!";
				if (TrPayService.isEndState(trp_res.getId(), alias)) {
					Res res = com.is.tietovisa.trpay.TrPayService.send2tieto(trp_res, alias);
					
					/*parent_state. 0=error, 1=success*/
					Res res1 = com.is.tietovisa.trpay.TrPayService
					.updateParentState(trp_res.getId(), (res.getCode() < 0) ? "0" : "1" );
					if (res1.getCode() < 0) {
						//alert(res1.getName());
						v_msg = v_msg+ "/n"+ "Ошибка изменения статус в bf_tr_pay: "+res1.getName();
					}
					
					Refill_log r_log = new Refill_log();
					r_log.setPay_id(trp_res.getId());
					r_log.setUser_id("0");
					r_log.setUser_name(un);
					r_log.setUser_branch(branch);
					if (res.getCode() < 0) {
						r_log.setErr(res.getName());
						v_msg = v_msg+ "/n"+ "Ошибка выполнения операцию в ТИЕТО";
					} 
					
					Res res2 = com.is.tietovisa.trpay.TrPayService.writeRefillLog(r_log);
					if (res2.getCode() < 0) {
						v_msg = v_msg+ "/n"+ "Ошибка записи в журнал: "+res2.getName();
						//alert(res2.getName());
					}
				}
				//alert("Успешно.");
				alert(v_msg);
			}

		} catch (Exception e) {
			alert(e.getMessage());
			ISLogger.getLogger().error(
					"__________________PAY_ERROR(TIETO):\n"
							+ CheckNull.getPstr(e));
			e.printStackTrace();
		}

		paywnd.setVisible(false);
		// onSelect$accGrid();
	}

	public void printp(TrPay trp, String report_file, int deal_id, int act_id) {

		/*
		 * int count_seq3 = TclientService.getDuplicateOtherMaxId(alias) + 1;
		 * System.out.println("count_seq3 = " + count_seq3); Ti_Duplicate_Other
		 * duplicate_other = null; duplicate_other = new
		 * Ti_Duplicate_Other(count_seq3, branch, report_file,
		 * TclientService.getUser_name(uid, branch, alias), trp.getBranch(),
		 * trp.getIn_name(), df2.format(new Date()), deal_id, act_id);
		 * 
		 * TclientService.createDuplicateOther(duplicate_other, alias);
		 */
		printwnd.setVisible(true);

		AMedia repmd = null;

		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("TR_PAY_ID", trp.getId());
		//params.put("V_DATE", df.format(trp.getDate_created()));

		/*
		KASSA – MFO + kassa
		KASSIR – qaysi parol bilan kirilsa o’sha hodim FIO si yoziladi    
		bank platelshik - MFO    
		filial poluchatel - ?
		filial platelshik - MFO    
		shet poluchatel - 22618%     
		NAimen_poluchatel -  Mijoz FIO    
		sposob oplaty  - nalichiy yoki bank kartasi orqali dep yoziladi
		utgan pul miqdori – so’da kirim/chiqim qilinsa ham so’mda ham dollarda yozilsin agar faqat dollar bo’sa faqat dollarni ko’rsatsin
		KOMIS – hozir komissiya mavjud emas nol – 0 qilib turasiz
		jami tulangan summa – so’da kirim/chiqim qilinsa ham so’mda ham dollarda yozilsin agar faqat dollar bo’sa faqat dollarni ko’rsatsin
		*/

		/* naznacheniyalar:
		Пополнение наличными долларами карты Агробанка ФИО  (1-2)
		Пополнение наличными суммами Виза карты Агробанка ФИО (10-3)
		Списания Виза карты Агробанка ФИО наличным долларами  (15-4)
		Списания с Виза карты Агробанка ФИО на суммовой карту (20-5)
		*/
		//1-da deal_id:=1, rep_type_id:=1, act_id:=1
		
		params.put("PNOMER", Long.toString(trp.getId()));
		params.put("PDATE", new SimpleDateFormat("dd.MM.yyyy").format(trp.getDate_created()));
		//params.put("PTIME", new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date()));
		params.put("PTIME", new SimpleDateFormat("hh:mm:ss").format(new java.util.Date()));

		params.put("PKASSA"  ,         trp.getBranch()+" "+AccPayService.getUser_Title(uid, branch, alias));
		params.put("PKASSIR"        ,  AccPayService.getUser_name2(uid, branch, alias));              
		params.put("PBANK_CL"       ,  trp.getBranch());              
		params.put("PBLANK_NUM"     ,  "");                
		params.put("PBRANCH_CO"     ,  "");                
		params.put("PBRANCH_CL"     ,  trp.getBranch());                
		params.put("PACC_CO"        ,  trp.getCard_acc());             
		params.put("PNAME_CO"       ,  trp.getCl_name());
		if (act_id==20) 
  		    params.put("PPAY_TYPE"      ,  "банковская карта");
		else
			params.put("PPAY_TYPE"      ,  "наличные");
		params.put("PCARD_NUM"      ,  trp.getPan());               
		params.put("PCARD_NAME"     ,  trp.getCl_name());                
		params.put("PBANK_NAME"     ,  "Agrobank");
		if (act_id==1) {
			params.put("PPURPOSE"       ,  "Пополнение наличными долларами карты Агробанка "+trp.getCl_name());
			params.put("PCURRENCY", "USD");
		} else if (act_id==10) {
			params.put("PPURPOSE"       ,  "Пополнение наличными суммами Виза карты Агробанка "+trp.getCl_name());
			params.put("PCURRENCY", "UZS");
		} else if (act_id==15) {
			params.put("PPURPOSE"       ,  "Списания Виза карты Агробанка "+trp.getCl_name()+" наличным долларами ");
			params.put("PCURRENCY", "USD");
		} else if (act_id==20) {
			params.put("PPURPOSE"       ,  "Списания с Виза карты Агробанка "+trp.getCl_name()+" на суммовой карту ");
			params.put("PCURRENCY", "UZS");
		} else
			params.put("PPURPOSE"       ,  "");	
		
		params.put("PAMOUNT"        ,  Long.toString(trp.getAmount()));             
		params.put("PKOMIS"         ,  "0");            
		params.put("PTOTAL_AMOUNT"  ,  Long.toString(trp.getAmount()));                   
		params.put("PCURR_DATE"     ,  new SimpleDateFormat("dd.MM.yyyy").format(new java.util.Date()));
		
		
//		params.put("T_CURRENCY", "USD");
//		params.put("TDP_NUM", trp.getDoc_num());
//		params.put("POST_ADDRESS", trp.getIn_address());
//		params.put("SUMMA6", Double.toString(trp.getAmount() / 100L));
//		params.put("ESUMMA6", nf.format(trp.getEqv_amount() / 100L));
//		params.put("PSUMMA6", com.is.utils.CheckNull.F2Money(
//				trp.getAmount() / 100L, "доллар", "центов"));
//		params.put("PESUMMA6", com.is.utils.CheckNull.F2Money(
//				trp.getEqv_amount() / 100L, "сум", "тийин"));
//		params.put("BRANCH_NAME", (com.is.utils.RefDataService.getMfo_name(
//				branch, alias).get(0)).getLabel());

		java.sql.Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			JasperPrint jasperPrint = net.sf.jasperreports.engine.JasperFillManager
					.fillReport(application.getRealPath(report_file), params, c);
			jasperPrint.setLeftMargin(Integer.valueOf(0));
			jasperPrint.setRightMargin(Integer.valueOf(0));
			jasperPrint.setTopMargin(Integer.valueOf(0));
			jasperPrint.setBottomMargin(Integer.valueOf(0));
			jasperPrint.setPageHeight(595);
			jasperPrint.setPageWidth(842);
			jasperPrint
					.setOrientation(net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE);
			byte[] buf = net.sf.jasperreports.engine.JasperRunManager
					.runReportToPdf(application.getRealPath(report_file),
							params, c);
			java.io.InputStream mediais = new java.io.ByteArrayInputStream(buf);

			repmd = new org.zkoss.util.media.AMedia("current.getName()" + "_"
					+ "current.getId_client()" + ".pdf", "pdf",
					"application/pdf", mediais);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (repmd != null) {
			printwnd$rpframe.setContent(repmd);
		}

		System.out.println("metod printp:");
		System.out.println("trp.getId() = " + trp.getId());
		System.out.println("trp.getBranch() = " + trp.getBranch());
		System.out.println("trp.getOperation_id() = " + trp.getOperation_id());
		System.out.println("trp.getAmount() = " + trp.getAmount());
		System.out.println("trp.getCard_acc() = " + trp.getCard_acc());
		System.out.println("trp.getCur_acc() = " + trp.getCur_acc());
		System.out.println("trp.getDate_created() = " + trp.getDate_created());
		System.out.println("trp.getParent_group_id() = "
				+ trp.getParent_group_id());
		System.out.println("trp.getState() = " + trp.getState());
		System.out.println("trp.getAccount_no() = " + trp.getAccount_no());
		System.out.println("trp.getCl_name() = " + trp.getCl_name());
		System.out.println("trp.getEmp_id() = " + trp.getEmp_id());
		System.out.println("trp.getPan() = " + trp.getPan());
		System.out.println("trp.getDeal_id() = " + trp.getDeal_id());
		System.out.println("trp.getDoc_num() = " + trp.getDoc_num());
		System.out.println("trp.getEqv_amount() = " + trp.getEqv_amount());
		System.out.println("trp.getOperation() = " + trp.getOperation());
		System.out.println("trp.getTieto_type() = " + trp.getTieto_type());
		System.out.println("trp.getAmount_t() = " + trp.getAmount_t());
		System.out.println("trp.getUid() = " + trp.getUid());
		System.out.println("trp.getIn_address() = " + trp.getIn_address());
		System.out.println("trp.getIn_name() = " + trp.getIn_name());
		//System.out.println("getUser_name() = "
		// + AccPayService.getUser_name(uid, branch, alias));
		System.out.println("uid, branch, alias = " + uid + " " + branch + " "
				+ alias);
		// System.out.println("replaceAll = "+trp.getPan().replaceAll(str,
		// "-"));

	}

	public CardIbs getFilter() {
		return filter;
	}

	public void setFilter(CardIbs filter) {
		this.filter = filter;
	}

	public CardIbs getCurrent() {
		return current;
	}

	public void setCurrent(CardIbs current) {
		this.current = current;
	}

	public AccInfo getCurr_acc() {
		return curr_acc;
	}

	public void setCurr_acc(AccInfo curr_acc) {
		this.curr_acc = curr_acc;
	}

	public CardIbs getCurr_card() {
		return curr_card;
	}

	public void setCurr_card(CardIbs curr_card) {
		this.curr_card = curr_card;
	}
	
    public void onSelect$mainTabbox(SelectEvent event) throws InterruptedException {
        Tab component = (Tab) event.getTarget();
        if (component.getId().equalsIgnoreCase("mainTab")) {
            //internalControlInclude.setVisible(true);
            //internalControlInclude.setSrc(null);
            //internalControlInclude.setSrc(
            //        "clientaddinfo.zul?branch=" + composer.getCustomer().getBranch() +
            //                "&client_id=" + composer.getCustomer().getId() +
            //                "&code_subject=P&alias=" + sessionAttributes.getSchema());
        } else if (component.getId().equalsIgnoreCase("confirmTab")) {
            //onClick$btn_getFile();
            confirmDiv.getChildren().clear();
            confirmWnd =
                    (Window) Executions.createComponents("TietoVisa_trpay.zul",
                            confirmDiv, null);
            //confirmWnd.setClosable(true);
            confirmWnd.setVisible(true);
            //Events.sendEvent("onUploadApps", confirmWnd, composer.getCustomer());
        }
    }

}
