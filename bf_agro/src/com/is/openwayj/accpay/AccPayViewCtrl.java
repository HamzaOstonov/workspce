package com.is.openwayj.accpay;

import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.RowType_EditCustomer_Request;

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
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
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
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ibm.db2.jcc.am.o;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.openwayj.PostUtils;
import com.is.openwayj.StringUtils;
import com.is.openwayj.XmlUtils;
import com.is.openwayj.model.AccInfo;
import com.is.openwayj.model.Balances;
import com.is.openwayj.model.CardInfo;
import com.is.openwayj.model.ClientContractsResponse;
import com.is.openwayj.model.Client_old;
import com.is.openwayj.model.ClientInfo;
import com.is.openwayj.model.ClientInfoResponse;
import com.is.openwayj.model.Contract;
import com.is.openwayj.model.ContractResp;
import com.is.openwayj.model.ContractRs;
import com.is.openwayj.model.UFXMsgAddClient;
import com.is.openwayj.model.UFXMsgAddClientResp;
import com.is.openwayj.model.UFXMsgAddContractAcc;
import com.is.openwayj.model.UFXMsgAddContractAccResp;
import com.is.openwayj.model.UFXMsgAddContractAddress;
import com.is.openwayj.model.UFXMsgAddContractCard;
import com.is.openwayj.model.UFXMsgAddContractCardRes;
import com.is.openwayj.model.UFXMsgInqContract;
import com.is.openwayj.model.UFXMsgInqContractResp;
import com.is.openwayj.model.UFXMsgReIssue;
import com.is.openwayj.model.UFXMsgReqClient;
import com.is.openwayj.model.UFXMsgReqClientResp;
import com.is.openwayj.model.UFXMsgReqContractResp;
import com.is.openwayj.model.UFXMsgUpdClient;
import com.is.openwayj.model.UFXMsgUpdStatus;
import com.is.openwayj.trpay.TrPay;
import com.is.openwayj.trpay.TrPayService;
import com.is.report.DPrint;
import com.is.tieto_globuz.customer.AddCstViewCtrl;
import com.is.openwayj.Utils;
import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;
import com.is.openwayj.customer.Customer;
import com.is.openwayj.customer.CustomerFilter;
import com.is.openwayj.customer.CustomerService;
import com.is.openwayj.customer.PagingListModel;

import com.is.openwayutils.report.basic.ReportBuilder;
import com.is.openwayutils.user.Action;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.user.UserService;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

public class AccPayViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CustomerFilter filter = new CustomerFilter();
	public Customer current = new Customer();
	public Customer copyOfCurrent;
	public AccInfo curr_acc = new AccInfo();
	public CardInfo curr_card = new CardInfo();

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
	private String is_minibank_user; //"yes", "no"
	private boolean is_test_mode=false;
	private String shet_kassa;
	static String openwayEndpoint, url_way4_clientinfo, url_way4_clientcontracts;
	private boolean add_way;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy");

	
	private Window paywnd, printwnd, showBalanceWindow, sofwnd;
	private Datebox add_everywhere$ap_birthday;
	private Iframe printwnd$rpframe;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Decimalbox paywnd$amnt;

	private RefCBox add_everywhere$acode_country, sofwnd$rcb_sof_id;
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

	private Textbox paywnd$search_name, paywnd$address, paywnd$curacc,
			paywnd$curr_acc_uzs, paywnd$inc_ord_num, sofwnd$txb_comment;
	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox branch_customers, accGrid, cardGrid, sofwnd$payGrid;
	private Grid showBalanceWindow$showBalanceGrid;

	protected Checkbox paywnd$chb_print;
	
	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Toolbarbutton paywnd$btn_block, paywnd$btn_unblock, paywnd$btn_cancel, showBalanceWindow$showBalanceCloseBtn, sofwnd$btn_cancel, sofwnd$btn_save;
	private Toolbarbutton paywnd$fbt, paywnd$lock, paywnd$application;
	private Toolbar paywnd$pay_tlb;
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

		// binder.bindBean("filter", this.filter);
		// binder.bindBean("current", this.current);
		// binder.bindBean("curr_acc", this.curr_acc);
		// binder.bindBean("curr_card", this.curr_card);

		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		if (curip == null || curip == "" || curip.equals(""))
			curip = "1";

		if (url_way4_clientinfo == null || url_way4_clientinfo == "" || url_way4_clientinfo.equals(""))
			url_way4_clientinfo = ConnectionPool.getValue("URL_WAY4_CLIENTINFO");
		if (url_way4_clientcontracts == null || url_way4_clientcontracts == "" || url_way4_clientcontracts.equals(""))
			url_way4_clientcontracts = ConnectionPool.getValue("URL_WAY4_CLIENTCONTRACTS");

		if ( uid==39499 && un.equals("admin"))
			is_test_mode=true;
		else
			is_test_mode=false;

		branch_customers.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) {
				Customer vCustomer = (Customer) data;
				row.setAttribute("row", (Object) row);
				row.appendChild(new Listcell(""));
				ClientInfoResponse clResp = CustomerService
						.getCustomers_openway(vCustomer.getBranch(),
								vCustomer.getId_client(),
								vCustomer.getP_pinfl(), url_way4_clientinfo, "");
				if (clResp.isSuccess()) {
					vCustomer.setO_city(clResp.getBody().getAddress().getCity());
					//vCustomer.setO_client_type();
					vCustomer.setO_post_address_fact(clResp.getBody().getAddress().getAddressLine2());
					if (clResp.getBody()!=null)
						vCustomer.setO_security_name(clResp.getBody().getSecurityName());
					vCustomer.setWay_exist(true);
				}

				row.setValue((Object) vCustomer);

				if (clResp.isSuccess()  && clResp.getBody()!=null) {
					
					row.appendChild(new Listcell(clResp.getBody().getSocialNumber()));
					row.appendChild(new Listcell(clResp.getBody().getLastName()+" "+clResp.getBody().getFirstName()+" "+clResp.getBody().getMiddleName()));
					row.appendChild(new Listcell(clResp.getBody().getBirthDate()));
					row.appendChild(new Listcell(clResp.getBody().getRegistrationNumber() ));

				} else {
					//if (clResp.get_resp_code().equals("1930")) { // "Client not found"
					//												// xatosi
					//	row.appendChild(new Listcell(" - "));
					//	row.appendChild(new Listcell(" - "));
					//} else {
						row.appendChild(new Listcell("Код Ошибка: "	 ));
						row.appendChild(new Listcell(clResp.getMessage()));
					//}
					row.appendChild(new Listcell(" - "));
					row.appendChild(new Listcell(" - "));

				}

				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getId_client())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getName())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getP_birthday())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getP_passport_serial())
						+ StringUtils.secureNull(vCustomer
								.getP_passport_number())));
				row.appendChild(new Listcell(""));

				/*
				 * row.setValue(clResp); if (clResp.get_resp_code()=="0" ||
				 * clResp.get_resp_code().equals("0")) { //success Client client
				 * =
				 * clResp.getMsgData().getInformation().getDataRs().getClientRs
				 * ().getClient(); ClientInfo clInfo= client.getClientInfo();
				 * 
				 * row.appendChild(new Listcell("")); row.appendChild(new
				 * Listcell("")); row.appendChild(new Listcell(StringUtils
				 * .secureNull(clInfo.getFirstName() ))); row.appendChild(new
				 * Listcell(StringUtils .secureNull(clInfo.getLastName()))); }
				 * else { row.appendChild(new Listcell("")); row.appendChild(new
				 * Listcell("")); row.appendChild(new Listcell(StringUtils
				 * .secureNull(clResp.get_resp_code() ))); row.appendChild(new
				 * Listcell(StringUtils .secureNull(clResp.get_resp_text()))); }
				 */

			}

		});

		accGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				AccInfo pAccInfo = (AccInfo) data;

				row.setValue(pAccInfo);
				
				//---knopka operasii
				Listcell cell = new Listcell();
				Button btn = new Button();
				btn.setLabel("Операции...");
				btn.setTooltiptext("Пополнение, списание, ...");
				btn.setAttribute("curr_acc", (Object) pAccInfo);
				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						
						curr_acc = (AccInfo) event.getTarget().getAttribute("curr_acc");
						
						String client_id;
						List<Action> actions;
						actions = AccPayService.getActions(uid, branch, alias);
						paywnd$pay_tlb.getChildren().clear();
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
							paywnd$fbt.setWidth("1000px");
							paywnd$fbt.addEventListener(Events.ON_CLICK, new EventListener() {
								@Override
								public void onEvent(Event event) {
									cur_act = (String) event.getTarget().getAttribute(
											"act_desc");
									pay_acc((Integer) event.getTarget().getAttribute("deal_id"),
											(Integer) event.getTarget().getAttribute(
													"rep_type_id"), (Integer) event.getTarget()
													.getAttribute("act_id"));
								}
							});
							paywnd$pay_tlb.appendChild(paywnd$fbt);
						}
						 paywnd$search_name.setValue(curr_acc.getCard() );
						// paywnd$address.setValue(current.getR_street());
						// paywnd$search_name.setReadonly(true);
						// paywnd$address.setReadonly(true);
						 Res res=AccPayService.getNext_doc_num(alias);
						 if (res.getCode()==0) 
						   paywnd$inc_ord_num.setValue(res.getName());
						 else
						 {  alert("Ошибка при получении номер приходного ордера. "+res.getName());
							 return;
						 }
						 //2026.04.09 todo
						 //daromad_id atributni bor bulsa tozalashimiz kerak.
						 paywnd.setAttribute("daromad_id", "");
						 
						 //-----------
						paywnd.setVisible(true);
						//chb_print.setChecked(true);
						//alert(paywnd$pay_tlb_left.getOrient());
					}
				});
				//btn.setDisabled(true);//shunaqa qarorga kelindi 2025.03.25
				cell.appendChild(btn);
				row.appendChild(cell);
				//----
				row.appendChild(new Listcell(pAccInfo.getCard()));
				// row.appendChild(new Listcell(df.format(pAccInfo
				// .getAb_expirity())));
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getTranz_acct()));

				// --sms status
				Listcell card_sms_cell = new Listcell();
				// sms подключен(не подключен) / номер [отключить СМС] / номер
				// [подключить СМС]
				final Label labelSmsStatus = new Label("Состояние СМС / ");
				card_sms_cell.appendChild((Component) labelSmsStatus);

				final Button btnSmsOff = new Button("Отключить");
				btnSmsOff.setAttribute("accInfo", (Object) pAccInfo);
				// btnSmsOff.setAttribute("lbox", (Object) lbox);
				btnSmsOff.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// Отвязка телефона от карты. Отключение СМС
								// уведомлений
								// с номера
								deActivateSms(event);
								
							}
						});
				card_sms_cell.appendChild((Component) btnSmsOff);
				card_sms_cell.appendChild(new Label(" / "));

				final Label labelPhoneMobile = new Label("Tel:null");
				// todo
				if (current.getP_phone_mobile() != null) {
					labelPhoneMobile.setValue(current.getP_phone_mobile());
				}
				card_sms_cell.appendChild((Component) labelPhoneMobile);
				final Button btnSmsOn = new Button("Включить");
				btnSmsOn.setAttribute("accInfo", (Object) pAccInfo);
				btnSmsOn.setAttribute("phoneMobile", (Object) labelPhoneMobile);
				btnSmsOn.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// Привязка телефона к карте. Подключение СМС
								// уведомлений на номер
								activateSms(event);
							}
						});
				card_sms_cell.appendChild((Component) btnSmsOn);
				row.appendChild((Component) card_sms_cell);

				// --end sms status

				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));

				row.appendChild(new Listcell(""));
			}
		});

		cardGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				CardInfo pAccInfo = (CardInfo) data;

				row.setValue(pAccInfo);
				
				//---knopka operasii
				Listcell cell = new Listcell();
				Button btn = new Button();
				btn.setLabel("Операции...");
				btn.setTooltiptext("Пополнение, списание, ...");
				btn.setAttribute("curr_card", (Object) pAccInfo);
				btn.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						
						curr_card = (CardInfo) event.getTarget().getAttribute("curr_card");
						
						String client_id;
						List<Action> actions;
						actions = AccPayService.getActions(uid, branch, alias);
						paywnd$pay_tlb.getChildren().clear();
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
							paywnd$fbt.setWidth("1000px");
							paywnd$fbt.addEventListener(Events.ON_CLICK, new EventListener() {
								@Override
								public void onEvent(Event event) {
									cur_act = (String) event.getTarget().getAttribute(
											"act_desc");
									pay_card((Integer) event.getTarget().getAttribute("deal_id"),
											(Integer) event.getTarget().getAttribute(
													"rep_type_id"), (Integer) event.getTarget()
													.getAttribute("act_id"));
								}
							});
							paywnd$pay_tlb.appendChild(paywnd$fbt);
						}
						paywnd$search_name.setValue(curr_card.getContractName() );
						paywnd$address.setValue(current.getP_post_address());
						// paywnd$search_name.setReadonly(true);
						// paywnd$address.setReadonly(true);

  					    paywnd.setAttribute("daromad_id", "");

						paywnd.setVisible(true);
						//alert(paywnd$pay_tlb_left.getOrient());
					}
				});
				cell.appendChild(btn);
				row.appendChild(cell); //2025.12.17
				//row.appendChild(new Listcell(""));
				
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getCardExpiry()));// expiry
				
				row.appendChild(new Listcell(pAccInfo.getProductCode1()));
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));
				row.appendChild(new Listcell(pAccInfo.getRbsNumberIbs()));

				// --Balance
				Listcell card_balance_cell = new Listcell();
				Button btn_show_bal = new Button();
				btn_show_bal.setLabel("Баланс...");
				//btblock_card.setImage("/images/-.png");
				btn_show_bal.setAttribute("cardInfo", (Object) pAccInfo);

				btn_show_bal.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {

								final Button btn = (Button) event.getTarget();
								final CardInfo cardInfo = (CardInfo) btn.getAttribute("cardInfo");
								
								if (cardInfo == null) {
									alert("Ошибка");
									return;
								}
								while (showBalanceWindow$showBalanceGrid.getRows().getChildren().size()>0) 
									showBalanceWindow$showBalanceGrid.getRows().removeChild( (org.zkoss.zul.Row)showBalanceWindow$showBalanceGrid.getRows().getChildren().get(0) );
								
								Balances bals = getCardBalances(cardInfo.getRbsNumberIbs());
								if (bals.getBalance()!=null) {

									for(int i=0; i<bals.getBalance().size();i++){
										org.zkoss.zul.Row row = new org.zkoss.zul.Row();
										Label lb = new Label();
										lb.setValue("Name");
										row.appendChild(lb);
										Textbox tb = new Textbox();
										tb.setValue(bals.getBalance().get(i).getName());
										tb.setReadonly(true);
										row.appendChild(tb);

										Label lb1 = new Label();
										lb1.setValue("Type");
										row.appendChild(lb1);
										Textbox tb1 = new Textbox();
										tb1.setValue(bals.getBalance().get(i).getType());
										tb1.setReadonly(true);
										row.appendChild(tb1);

										Label lb2 = new Label();
										lb2.setValue("Amount");
										row.appendChild(lb2);
										Textbox tb2 = new Textbox();
										tb2.setValue(bals.getBalance().get(i).getAmount());
										tb2.setReadonly(true);
										row.appendChild(tb2);

										Label lb3 = new Label();
										lb3.setValue("Currency");
										row.appendChild(lb3);
										Textbox tb3 = new Textbox();
										tb3.setValue(bals.getBalance().get(i).getCurrency());
										tb3.setReadonly(true);
										row.appendChild(tb3);
										showBalanceWindow$showBalanceGrid.getRows().appendChild(row);
									}
								}
								showBalanceWindow.setVisible(true);
							}
						});
				card_balance_cell.appendChild((Component) btn_show_bal);
				//row.appendChild((Component) card_balance_cell); //2025.05.12
				row.appendChild(new Listcell(""));
				// -- end balance

				//row.appendChild(new Listcell(pAccInfo.getSTATUS()));// status(1)
				// --deystviya s kartami
				Listcell card_status_cell = new Listcell();
				card_status_cell.setStyle("text-align: left");
				Toolbarbutton btblock_card = new Toolbarbutton();
				btblock_card.setTooltiptext("Блокировать карту");
				btblock_card.setImage("/images/lock1.png");
				btblock_card.setAttribute("card", (Object) pAccInfo);

				btblock_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
							
								lockCardMethod(event);
							}
						});
				card_status_cell.appendChild((Component) btblock_card);
				
				Toolbarbutton btUnlock_card = new Toolbarbutton();
				btUnlock_card.setTooltiptext("Разблокировать карту");
				btUnlock_card.setImage("/images/unlock1.png");
				btUnlock_card.setAttribute("card", (Object) pAccInfo);

				btUnlock_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {

								unLockCardMethod(event);

							}
						});
				card_status_cell.appendChild((Component) btUnlock_card);

				Toolbarbutton btReissue_card = new Toolbarbutton();
				btReissue_card.setTooltiptext("Перевыпуск карту");
				btReissue_card.setImage("/images/paycheck.png");
				btReissue_card.setAttribute("card", (Object) pAccInfo);

				btReissue_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {

								reIssueCardMethod(event);

							}
						});
				card_status_cell.appendChild((Component) btReissue_card);

				Label lbl = new Label();
				lbl.setValue(pAccInfo.getSTATUS());
				card_status_cell.appendChild((Component) lbl);
				
				
				//row.appendChild((Component) card_status_cell); 2025.05.12
				row.appendChild(new Listcell(""));
				// -- end deystviya po kartam
				
				
				row.appendChild(new Listcell(pAccInfo.getSTATUS2()));// status2

				row.appendChild(new Listcell(""));
			}
		});

		sofwnd$payGrid.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) {
				TrPay trp = (TrPay) data;
				row.setAttribute("row", (Object) row);
				row.setValue((Object) trp);

				row.appendChild(new Listcell(StringUtils.secureNull(trp
						.getBranch())));
				row.appendChild(new Listcell(StringUtils.secureNull(trp
						.getAccount_no())));
				row.appendChild(new Listcell(StringUtils.secureNull(trp
						.getDate_created())));
				row.appendChild(new Listcell(StringUtils.secureNull(trp
						.getAmount())			));
				row.appendChild(new Listcell(StringUtils.secureNull(trp
						.getAmount_t())			));

			}

		});	
		binder.loadAll();
	}

	public void onClick$showBalanceCloseBtn$showBalanceWindow()	 {
		showBalanceWindow.setVisible(false);
    }

	private void activateSms(final Event event) {
		try {
			final Button btn = (Button) event.getTarget();
			final AccInfo accInfo = (AccInfo) btn.getAttribute("accInfo");
			final Label lbox = (Label) btn.getAttribute("phoneMobile");
			if (lbox == null) {
				alert("Введите номер телефона!");
				return;
			}
			if (accInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
			if (lbox.getValue() == null
					|| lbox.getValue().toString().length() != 12
					|| !lbox.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона " + lbox.getValue());
				return;
			}

			// wayga zapros qilamiz
			// uspeshno bulsa tablisaga yozamiz:klientid, kontraktraqam,
			// smsraqam
			// arxiv tablsaga ham yozamiz. specialacc va specialacc_history ga
			// uxshatamiz

			String v_xml = "_";
			String v_msg = "";
			Res res = null;

			accInfo.setClientNumber(current.getBranch()
					+ current.getId_client());
			accInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UFXMsgAddContractAddress addSms = AccPayService.makeAddSMS(accInfo,
					lbox.getValue(), alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(addSms);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize xml(addSms) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2 = null;
			try {
				v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"postUtils.sendData(addSMS) err " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(addSMS) "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
			// ISLogger.getLogger().error(
			// "postUtils.sendData(addSMS) v_res2: " + v_res2);

			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgAddContractAddress clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						UFXMsgAddContractAddress.class);
			} catch (Exception e) {
				// Block of code to handle errors
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (UFXMsgAddContractAddress): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgAddContractAddress): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.getResp_code() == "0"
					|| clResp.getResp_code().equals("0")) /* success */{
				v_msg = v_msg
						+ "\nSMS подключен в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						6, branch, v_xml, v_res2.getName()), alias);

				// curr_card.setCbsNumber(clResp.getMsgDataReq().getApplication()
				// .getDataRsObject().getContractRs().get(0).getContract()
				// .getContractIDT().getContractNumber());
				res = AccPayService
						.activateSmsService(accInfo, lbox.getValue());
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}

			} else {
				// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
				v_msg = v_msg + "\nОшибка подкючения СМС в Openway: "
						+ clResp.getResp_text();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}

	private void deActivateSms(final Event event) {
		try {
			alert("Данная функция в процессе разработке!");
			return;

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}

	private void lockCardMethod(final Event event) {
		try {
			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("card");
			//final Label lbox = (Label) btn.getAttribute("phoneMobile");
			//if (lbox == null) {
			//	alert("Введите номер телефона!");
			//	return;
			//}
			if (current == null) {
				alert("Клиент не выбран");
				return;
			}
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}

			// wayga zapros qilamiz
			// uspeshno bulsa tablisaga yozamiz:klientid, kontraktraqam,
			// smsraqam
			// arxiv tablsaga ham yozamiz. specialacc va specialacc_history ga
			// uxshatamiz

			String v_xml = "_";
			String v_msg = "";
			Res res = null;

			//cardInfo.setClient_id(current.getBranch() + current.getId_client());
			cardInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UFXMsgUpdStatus	updStatus = AccPayService.makeLockCard(cardInfo,
					"41", "KARTA YOQOTILGAN. MIJOZNING ARIZASIGA ASOSAN BLOKLANDI");

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(updStatus);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize xml(updStatus) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2 = null;
			try {
				v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"postUtils.sendData(updStatus) err " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(updStatus) "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgUpdStatus clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						UFXMsgUpdStatus.class);
			} catch (Exception e) {
				// Block of code to handle errors
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (UFXMsgUpdStatus): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgUpdStatus): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.getMsgDataReq().getApplication().getStatusObject().getRespCode() == "0"
					|| clResp.getMsgDataReq().getApplication().getStatusObject().getRespCode().equals("0")) /* success */{
				v_msg = v_msg
						+ "\nКарта блокирован в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						7, branch, v_xml, v_res2.getName()), alias);

				res = AccPayService
						.lockCard(cardInfo, "41", "KARTA YOQOTILGAN. MIJOZNING ARIZASIGA ASOSAN BLOKLANDI");
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}

			} else {
				// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
				v_msg = v_msg + "\nОшибка блокировки карту в Openway: "
						+ clResp.getMsgDataReq().getApplication().getStatusObject().getRespText();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}
	
	private void unLockCardMethod(final Event event) {
		try {
			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("card");
			//final Label lbox = (Label) btn.getAttribute("phoneMobile");
			//if (lbox == null) {
			//	alert("Введите номер телефона!");
			//	return;
			//}
			if (current == null) {
				alert("Клиент не выбран");
				return;
			}
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
		
			// wayga zapros qilamiz
			// uspeshno bulsa tablisaga yozamiz:klientid, kontraktraqam,
			// smsraqam
			// arxiv tablsaga ham yozamiz. specialacc va specialacc_history ga
			// uxshatamiz

			String v_xml = "_";
			String v_msg = "";
			Res res = null;

			//cardInfo.setClient_id(current.getBranch() + current.getId_client());
			cardInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UFXMsgUpdStatus	updStatus = AccPayService.makeLockCard(cardInfo,
					"00", "KARTA BLOKDAN CHIQARILDI");

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(updStatus);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize xml(updStatus) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2 = null;
			try {
				v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"postUtils.sendData(updStatus) err " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(updStatus) "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgUpdStatus clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						UFXMsgUpdStatus.class);
			} catch (Exception e) {
				// Block of code to handle errors
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (UFXMsgUpdStatus): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgUpdStatus): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.getMsgDataReq().getApplication().getStatusObject().getRespCode() == "0"
					|| clResp.getMsgDataReq().getApplication().getStatusObject().getRespCode().equals("0")) /* success */{
				v_msg = v_msg
						+ "\nКарта разблокирован в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						7, branch, v_xml, v_res2.getName()), alias);

			
				res = AccPayService
						.unLockCard(cardInfo, "00", "KARTA YOQOTILGAN. MIJOZNING ARIZASIGA ASOSAN BLOKLANDI");
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}

			} else {
				// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
				v_msg = v_msg + "\nОшибка разблокировки карту в Openway: "
						+ clResp.getMsgDataReq().getApplication().getStatusObject().getRespText();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}

		

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}
	
	private void reIssueCardMethod(final Event event) {
		try {
		
			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("card");
			//final Label lbox = (Label) btn.getAttribute("phoneMobile");
			//if (lbox == null) {
			//	alert("Введите номер телефона!");
			//	return;
			//}
			if (current == null) {
				alert("Клиент не выбран");
				return;
			}
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
		
			// wayga zapros qilamiz
			String v_xml = "_";
			String v_msg = "";
			Res res = null;

			//cardInfo.setClient_id(current.getBranch() + current.getId_client());
			cardInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UFXMsgReIssue reIssue = AccPayService.makeReIssueCard(cardInfo);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(reIssue);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize xml(ReIssue) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2 = null;
			try {
				v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"postUtils.sendData(ReIssue) err " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(ReIssue) "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
	
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgReIssue clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						UFXMsgReIssue.class);
			} catch (Exception e) {
				
				ISLogger.getLogger().error(
						" v_res2 (UFXMsgReIssue): "
								+ v_res2);
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (UFXMsgReIssue): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgReIssue): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}


			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.getMsgDataReq().getApplication().getStatusObject().getRespCode() == "0"
					|| clResp.getMsgDataReq().getApplication().getStatusObject().getRespCode().equals("0")) /* success */{
				v_msg = v_msg
						+ "\nКарта перевыпушен в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						8, branch, v_xml, v_res2.getName()), alias);

				//todo;			
				/*res = AccPayService
						.unLockCard(cardInfo, "00", "KARTA YOQOTILGAN. MIJOZNING ARIZASIGA ASOSAN BLOKLANDI");
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}*/

			} else {
				v_msg = v_msg + "\nОшибка перевыпуска карты в Openway: "
						+ clResp.getMsgDataReq().getApplication().getStatusObject().getRespText();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}
	
	public void onClick$tbtn_search() {
		if (filter.getBranch() == null)
			filter.setBranch(branch);
		this.refreshModel(/* this._startPageNumber */0);
	}

	private void refreshModel(final int activePage) {
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, this.alias);
		this.branch_customers.setModel((ListModel) this.model);
		if (model.getSize() > 0) {
			branch_customers.setSelectedIndex(0);
			sendSelEvt();
		}
	}

	public void onOkToFilter(Event event) {
		onClick$tbtn_search();
	}

	public void loadRefData() {
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
		// if (add_everywhere$ap_code_adr_distr.getItems().size()==0)
		// add_everywhere$ap_code_adr_distr.setModel( new ListModelList(
		// CustomerService.getDistr(this.alias)));
		if (add_everywhere$ap_code_tax_org.getItems().size() == 0)
			add_everywhere$ap_code_tax_org.setModel(new ListModelList(
					CustomerService.getTax(this.alias)));
		if (add_everywhere$acode_resident.getItems().size() == 0)
			add_everywhere$acode_resident.setModel(new ListModelList(Utils
					.getRezCl(this.alias)));

	}

	public void loadRefAccData() {
		if (add_account$acc_bal.getItems().size() == 0)
			add_account$acc_bal.setModel(new ListModelList(CustomerService
					.getAcc_bal(alias)));
		if (add_account$o_product_code1.getItems().size() == 0)
			add_account$o_product_code1.setModel(new ListModelList(
					CustomerService.getProduct_code1_way4(alias)));
		/*
		 * if (add_card_wnd$o_product_code1.getItems().size() == 0)
		 * add_card_wnd$o_product_code1.setModel(new ListModelList(
		 * CustomerService.getSubProduct_code1_way4(alias)));
		 */
	}

	public void onChange$acc_bal$add_account() {
		curr_acc.setAcc_bal(add_account$acc_bal.getValue());
	}

	public void onChange$o_product_code1$add_account() {
		curr_acc.setProductCode1(add_account$o_product_code1.getValue());
	}

	public void onChange$o_product_code1$add_card_wnd() {
		curr_card.setProductCode1(add_card_wnd$o_product_code1.getValue());
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
			/* Shetevoy kontrakt */
			AccountFilter filt = new AccountFilter();
			filt.setBranch(branch);
			filt.setAcc_bal("22618");
			//filt.setCurrency("840");
			filt.setClient(current.getId_client());

			List<AccInfo> infoList = new ArrayList<AccInfo>();
			// ABSdan
			List<Account> accList = AccountService.getAccountsFl(0, 100, filt,
					alias);
			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_ACC)
			List<AccInfo> accList2 = CustomerService.getContractAccList_ABS(
					current.getBranch(), current.getId_client(), alias);

			// WAYdan
			ClientContractsResponse clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							url_way4_clientcontracts, "");
			//ArrayList<ContractResp> contractAccList = new ArrayList<ContractResp>();
			ArrayList<Contract> contractAccList = new ArrayList<Contract>();

			if (clResp.isSuccess() ) {
				System.out.println("clResp.isSuccess()");
				if (clResp.getBody() != null) {
					List<Contract> contractList = clResp.getBody();
					for (int i = 0; i < contractList.size(); i++) {
						if (contractList.get(i).getContractCategory()
								.equals("Account")) {
							contractAccList.add(contractList.get(i));
						}
					}
				}
			}

			for (int i = 0; i < accList.size(); i++) {
				AccInfo inf = new AccInfo();
				inf.setBranch(branch);
				inf.setClient(accList.get(i).getClient());
				inf.setId(accList.get(i).getId());
				inf.setTranz_acct(accList.get(i).getId());
				inf.setCard(accList.get(i).getName());
				inf.setAb_expirity(accList.get(i).getDate_open());
				inf.setContractNumber("");
				inf.setAcc_bal(accList.get(i).getAcc_bal());
				inf.setId_order(accList.get(i).getId_order());
				inf.setWay_exist(false);

				// shetimizga mos keladigan kontrakt topsak is_way ni true
				// qilamiz,
				// kerakli polelarni tuldirtiramiz va kontraktlistdan uchirib
				// quyamiz
				for (int j = 0; j < contractAccList.size(); j++) {
					if (contractAccList
							.get(j)
							.getCbsNumber()
							.equals(/*accList.get(i).getBranch()*/"V_"+accList.get(i).getId())) {
						inf.setWay_exist(true);
						inf.setCbsNumber(contractAccList.get(j).getCbsNumber());
						inf.setContractNumber(contractAccList.get(j).getContractNumber());
						contractAccList.remove(j);
						break;
					}
				}
				// ABS dan BF_OPENWAY_CONTRACT_ACC kelgan qushimcha malumotlar
				// b-n inf ga zeb beramiz :)
				for (int j = 0; j < accList2.size(); j++) {
					if (accList2
							.get(j)
							.getRbsNumberIbs()
							.equals(accList.get(i).getBranch()
									+ accList.get(i).getId())) {
						inf.setProductCode1(accList2.get(j).getProductCode1());
						break;
					}
				}

				//
				infoList.add(inf);
			}

			for (int j = 0; j < contractAccList.size(); j++) {
				AccInfo inf = new AccInfo();
				inf.setWay_exist(true);
				inf.setCbsNumber(contractAccList.get(j).getCbsNumber());
				infoList.add(inf);
			}

			// accGrid.setModel(new ListModelList(infoList));
			accGrid.setModel(new BindingListModelList(infoList, true));
			//

			if (infoList.size() > 0) {
				accGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", accGrid,
						accGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			} else {
				curr_acc = null;
			}

			/* End Shetevoy kontrakt */

			/* Begin Kartochniy kontrakt */

			List<CardInfo> infoCardList = new ArrayList<CardInfo>();

			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			/*
			 * List<CardInfo> cardList =
			 * CustomerService.getContractCardList_ABS( current.getBranch(),
			 * current.getId_client(), curr_acc.getProductCode1(), alias);
			 */
			List<CardInfo> cardList = CustomerService.getContractCardList_ABS(
					current.getBranch(), current.getId_client(), alias);

			// WAYdan
			//clResp = CustomerService.getCustomersContract_openway(
			//		current.getBranch(), current.getId_client(),
			//		current.getP_pinfl(), openwayEndpoint, "");
			ArrayList<Contract> contractCardList = new ArrayList<Contract>();

			if (clResp.isSuccess()) {
				List<Contract> contractList = clResp.getBody();
				for (int i = 0; i < contractList.size(); i++) {
					if (contractList.get(i).getContractCategory().equals("Card")) {
						contractCardList.add(contractList.get(i));
					}
				}
			}

			for (int i = 0; i < cardList.size(); i++) {
				CardInfo inf = new CardInfo();
				inf.setBranch(current.getBranch());
				inf.setClient_id(cardList.get(i).getClient_id());
				inf.setMasterProductCode1(cardList.get(i)
						.getMasterProductCode1());
				inf.setProductCode1(cardList.get(i).getProductCode1());
				inf.setRbsNumberIbs(cardList.get(i).getRbsNumberIbs());
				inf.setContractName(cardList.get(i).getContractName());
				inf.setCbsNumber(cardList.get(i).getCbsNumber());
				// inf.setAb_expirity(cardList.get(i).getDate_open());
				// inf.setContractNumber("");
				// inf.setAcc_bal(cardList.get(i).getAcc_bal());
				// inf.setId_order(cardList.get(i).getId_order());
				inf.setWay_exist(false);

				// shetimizga mos keladigan kontrakt topsak is_way ni true
				// qilamiz,
				// kerakli polelarni tuldirtiramiz va kontraktlistdan uchirib
				// quyamiz
				for (int j = 0; j < contractCardList.size(); j++) {
					if (contractCardList.get(j).getCbsNumber()
							.equals(cardList.get(i).getRbsNumberIbs())) {
						inf.setWay_exist(true);
						inf.setContractName(contractCardList.get(j).getContractName());
						inf.setContractNumber(contractCardList.get(j).getContractNumber());
						if (contractCardList.get(j).getCardExpiry() != null)
							inf.setCardExpiry(contractCardList.get(j).getCardExpiry());
						else
							inf.setCardExpiry("-");
						inf.setSTATUS(contractCardList.get(j).getStatus().getStatus() );
						inf.setSTATUS2(contractCardList.get(j).getStatus().getProductionStatus());
						// todo
						// balansni ham zapros qilamiz
			
						//inf.setACCOUNT_AVAIL_AMOUNT(getCardBalance(inf.getRbsNumberIbs()));

						// end balans query
						contractCardList.remove(j);
						break;
					}
				}

				//
				infoCardList.add(inf);
			}

			for (int j = 0; j < contractCardList.size(); j++) {
				CardInfo inf = new CardInfo();
				inf.setWay_exist(true);
				inf.setBranch(current.getBranch());
				inf.setCbsNumber(contractCardList.get(j).getCbsNumber());
				inf.setContractNumber(contractCardList.get(j).getContractNumber());
				if (contractCardList.get(j).getCardExpiry() != null)
					inf.setCardExpiry(contractCardList.get(j).getCardExpiry());
				else
					inf.setCardExpiry("-");
				inf.setSTATUS(contractCardList.get(j).getStatus()
						.getStatus());
				inf.setSTATUS2(contractCardList.get(j).getStatus()
						.getProductionStatus());
				infoCardList.add(inf);
			}

			// accGrid.setModel(new ListModelList(infoList));
			cardGrid.setModel(new BindingListModelList(infoCardList, true));
			//

			if (infoCardList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			} else {
				curr_card = null;
			}
			/* End kartochniy kontrakt */

		}

	}

	/*public void onDoubleClick$accGrid() {

		String client_id;

		List<Action> actions;
		actions = AccPayService.getActions(uid, branch, alias);
		paywnd$pay_tlb.getChildren().clear();
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
			paywnd$fbt.setWidth("1000px");
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
			paywnd$pay_tlb.appendChild(paywnd$fbt);
		}

		// paywnd$search_name.setValue(current.getSearch_name());
		// paywnd$address.setValue(current.getR_street());
		// paywnd$search_name.setReadonly(true);
		// paywnd$address.setReadonly(true);
		// paywnd$lock.setImage("/images/locked.png");
		paywnd.setVisible(true);
	}*/

	public void onClick$btn_cancel$paywnd()	 {
		paywnd.setVisible(false);
    }

	public void onClick$btn_cancel$sofwnd()	 {
		sofwnd.setVisible(false);
		paywnd.setVisible(false);
    }

	public void onClick$btn_save$sofwnd()	 {
		//todo daromadlarni manbaini kiritish kerak
		
		//String p_branch, String p_client_id, int p_sof_id, String p_comment_txt, String un, String pwd, String alias
		if (sofwnd$rcb_sof_id.getValue()==null || sofwnd$rcb_sof_id.getValue().equals("") ) {
			alert("Выберите источник дохода из списка.");
			return;
		}
		Res res = AccPayService.insert_sof(branch, current.getId_client(), Integer.parseInt(sofwnd$rcb_sof_id.getValue()), sofwnd$txb_comment.getValue(), un, pwd, alias);
		if (res.getCode()==0 ||res.getCode()==-1) {
			alert(res.getName());
			return;
		}
		paywnd.setAttribute("daromad_id", ""+res.getCode());
		sofwnd.setVisible(false);
    }

	
	public static BigDecimal getCardBalance(String cardRbsNumber) {
		BigDecimal bal = new BigDecimal("0");
		String v_xml;
		// class yasaymiz, qiymatlar beramiz
		UFXMsgInqContract clBal = AccPayService
				.makeClassForBalance(cardRbsNumber);

		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeXmlFromObject(clBal);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize xml(getBalance) " + e.getMessage());
			return bal;
		}

		//ISLogger.getLogger().error(	"getBalance send v_xml : " + v_xml);

		// post zapros qilamiz
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		Res v_res2 = null;
		try {
			v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"postUtils.sendData(getBalance) err " + e.getMessage());
			return bal;
		}

		//ISLogger.getLogger().error("getBalance recv v_res2: " + v_res2);

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		UFXMsgInqContractResp clResp = null;
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper.readValue(v_res2.getName(), UFXMsgInqContractResp.class);
		} catch (Exception e) {
			// Block of code to handle errors
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (UFXMsgInqContractResp): "
							+ e.getMessage());
			return bal;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
		// return
		// qilamiz
		if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) /* success */{

			String balStr = clResp.getMsgDataReq().getApplication()
					.getDataRsObject().getContractRs().getInfo()
					.getBalancesObject().getBalance().get(0).getAmount();
			if (balStr == null) {
				return bal;
			} else {
				try {
					bal = new BigDecimal(balStr);
					return bal;
				} catch (Exception e) {

					ISLogger.getLogger().error(
							"balance convert : " + balStr + ", err:"
									+ e.getMessage());
					return bal;
				}
			}

		} else {

			ISLogger.getLogger().error(
					"getting error (UFXMsgInqContractResp): "
							+ clResp.getResp_text());
			return bal;
		}

	}

	public static Balances getCardBalances(String cardRbsNumber) {
		Balances bal = new Balances();
		String v_xml;
		// class yasaymiz, qiymatlar beramiz
		UFXMsgInqContract clBal = AccPayService
				.makeClassForBalance(cardRbsNumber);

		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeXmlFromObject(clBal);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize xml(getBalance) " + e.getMessage());
			return bal;
		}

		// post zapros qilamiz
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		Res v_res2 = null;
		try {
			v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"postUtils.sendData(getBalance) err " + e.getMessage());
			return bal;
		}

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		UFXMsgInqContractResp clResp = null;
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper.readValue(v_res2.getName(), UFXMsgInqContractResp.class);
		} catch (Exception e) {
			// Block of code to handle errors
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (UFXMsgInqContractResp): "
							+ e.getMessage());
			return bal;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
		// return
		// qilamiz
		if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) /* success */{

			bal = clResp.getMsgDataReq().getApplication()
					.getDataRsObject().getContractRs().getInfo()
					.getBalancesObject();
			return bal;

		} else {

			ISLogger.getLogger().error(
					"getting error (UFXMsgInqContractResp): "
							+ clResp.getResp_text());
			return bal;
		}

	}
	
	private void pay_acc(int deal_id, int rep_type_id, int act_id) {

		String client_id;
		/*
		 * Res res = AccPayService.check_allowed_card_action(144, deal_id,
		 * act_id, curr_acc.getProduct(), alias); if (res.getCode() != 0) {
		 * alert(res.getName()); return; }
		 */
        
		//todo bitta variable buladi. uni qiymatini paywnd oynada qaysidir atribut ga yozib quyamiz
        //masalan, daromad ID kiritildimi yani save buldimi degan
        //SAVE qilingan bulsa limit_check ishlatilmaydi., aks holda ishlaydi.
        
        String daromad_id = (String)paywnd.getAttribute("daromad_id");
	
		try {

			TrPay trp = new TrPay();

			trp.setBranch(branch);
			trp.setAmount(Math.round((paywnd$amnt.getValue().doubleValue()) * 100));
			trp.setAmount_t(Math.round((paywnd$amnt.getValue().doubleValue())));
			if (is_test_mode && curr_acc.getCbsNumber()==null)
				curr_acc.setCbsNumber("C_22618840999079903001");
			trp.setCard_acc(/*curr_acc.getCbsNumber().substring(5)*/ curr_acc.getCbsNumber().substring(2)); 
			//trp.setCard_acc("22618840999079903001"); //hamza
			trp.setCur_acc("1"); // --default
			trp.setCl_name(current.getName());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());			
			//trp.setAccount_no(curr_acc.getContractNumber());
			//trp.setPan(TrPayService.getCardNumber_IBS(curr_acc.getBranch(),
			//		curr_acc.getClient(), curr_acc.getProductCode1(),
			//		curr_acc.getContractNumber() + "%", alias));

			String s1=curr_acc.getCbsNumber();
	        String acc_no=null;
	        String pan=null;
	        if (s1.length()>20) {
	            acc_no=s1.substring(s1.length()-20,s1.length());
	            if (s1.length()<=39) {
	                pan=s1.substring(0,s1.length()-20);
	            }
	        } else {
	            acc_no=s1;
	        }
			trp.setAccount_no(acc_no); //(rbs_numberdan oxirgi 20 tasini yozamiz);
			trp.setPan(pan);//(rbs_number ni uzunligi 20 tadan ortiq bulsa boshlanishidan sanab olamiz);
			if (is_minibank_user==null || is_minibank_user.equals("")) {
				is_minibank_user = AccPayService.isMinibank_user(branch, ""+uid);
			}
            
			if (is_minibank_user.equals("yes")){
				if (shet_kassa==null || shet_kassa.equals("")) {
					shet_kassa = AccPayService.getShetKassa(branch, ""+uid, "840");
				}
			}

			//2026.04.09 todo check_limit quyish
			if (daromad_id==null || daromad_id.equals("")) /*daromad_id kiritilmagan ekan, yangi(birinchi) urinish */ {
				Res resCheck = TrPayService.checkLimit(un, pwd, trp, act_id, deal_id, alias);
				
				if (resCheck.getCode()!=0) { //texnik xato
					alert(resCheck.getName());
					return;
				} 
				if (resCheck.getName()!=null) {
					//2 xil xato kelishi mumkin. 1-si texnik xato("Error:" b-n boshlanadi). 2-si Limit oshdi degan manoda.
					//Texnik xato bulsa daromad oynani kursatmaymiz va faqat xatoni kursatamiz 
					if (resCheck.getName().contains("Error:")) {
						alert(resCheck.getName());
					} else {
						//daromadlar kiritish oynasini chiqarib(kursatib) keyin return qilamiz
						//List<TrPay> docs =	TrPayService.getDocs30Day("00394", "99025509", un, pwd, alias);
						List<TrPay> docs =	TrPayService.getDocs30Day(branch, current.getId_client(), un, pwd, alias);
						
						sofwnd$payGrid.setModel(new BindingListModelList(docs, true));
						
						if (sofwnd$rcb_sof_id.getItems().size() == 0)
							sofwnd$rcb_sof_id.setModel(new ListModelList(
									AccPayService.getSofList(alias)));
						
						sofwnd.setVisible(true);
						binder.loadAll();
					}
					return;					
				}
			} 
			//Res resCheck = TrPayService.checkLimit(un, pwd, trp, act_id, deal_id, alias);
			//if (resCheck.getName()!=null) {
			//	alert(resCheck.getName());
			//	//return;
			//}
			
			System.out.println("deal_id:=" + deal_id + ", rep_type_id:="
					+ rep_type_id + ", act_id:=" + act_id);
			ISLogger.getLogger().error(
					"deal_id:=" + deal_id + ", rep_type_id:=" + rep_type_id
							+ ", act_id:=" + act_id);
			Res rs = TrPayService
					.doAction(un, pwd, trp, act_id, deal_id, daromad_id, alias);
				
			// Res rs = new Res(0, "27059543");
			if (rs.getCode() < 0) {
				alert(rs.getName());
			} else {
				trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()),
						alias);
				trp_res.setCl_name(/* current.getSearch_name() */"");
				trp_res.setIn_name(paywnd$search_name.getValue());
				trp_res.setIn_address(paywnd$address.getValue());

				//printp(trp_res,
				// AccPayService.getReportTemplate(deal_id, act_id, alias),
				// deal_id, act_id);
				//print_template(trp_res,
				// AccPayService.getReportTemplate(deal_id, act_id, alias),
				// deal_id, act_id);

				if (paywnd$chb_print.isChecked()) {
					//print
					Map<String, Object> params = initParamsForReport();
					
					params.put("CASH_RECV_ORDER_NUMBER", trp.getDoc_num());
					params.put("DATE", df2.format(trp_res.getDate_created()));
					params.put("DEBT_ACCOUNT_NUM", shet_kassa ); //kassa ekan
					params.put("AMOUNT", Double.toString(trp_res.getAmount() / 100L));
					params.put("DEBT_CLIENT_NAME", trp_res.getCl_name()); 
					params.put("CREDIT_ACCOUNT_NUM", trp.getCard_acc());
					//params.put("AMOUNT_TEXT", "бум бум бум");
					params.put("AMOUNT_TEXT", com.is.utils.CheckNull.F2Money(
							trp.getAmount() / 100L, "сум", "тийин"));
					params.put("PAYMENT_PURPOSE", "картасини тулдириш учун");
					CashRecvOrder.getInstance().getReport(
			                "com.is.openwayj.accpay.CashRecvOrder",
			                "/reports/CashRecv.xls", "CashRecvOrder", params);
				} 
			
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
				alert("Успешно.");
			}

		} catch (Exception e) {
			alert(e.getMessage());
			ISLogger.getLogger().error(
					"__________________PAY_ERROR(openway):\n"
							+ CheckNull.getPstr(e));
			e.printStackTrace();
		}

		paywnd.setVisible(false);
		// onSelect$accGrid();
	}

	private void pay_card(int deal_id, int rep_type_id, int act_id) {

		String client_id;

		/*
		 * Res res = AccPayService.check_allowed_card_action(144, deal_id,
		 * act_id, curr_acc.getProduct(), alias); if (res.getCode() != 0) {
		 * alert(res.getName()); return; }
		 */

		String daromad_id = (String)paywnd.getAttribute("daromad_id");
		
		try {

			TrPay trp = new TrPay();
	
			trp.setBranch(branch);
			// trp.setOperation_id(2);
			trp.setAmount(Math.round((paywnd$amnt.getValue().doubleValue()) * 100));
			trp.setAmount_t(Math.round((paywnd$amnt.getValue().doubleValue())));
			//trp.setCard_acc(/*curr_acc.getCbsNumber().substring(5)*/ curr_card.getCbsNumber().substring(4));
			trp.setCard_acc(curr_card.getRbsNumberIbs().substring(4));
			
			//trp.setCard_acc("22618840999079903001"); //hamza
			trp.setCur_acc("1"); // --default
			trp.setCl_name(current.getName());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());
			
			String s1=curr_card.getCbsNumber(); //bazida curr_card.getCbsNumber(); da 16talik karta raqam kelarkan. bizga rbs_number kerak  
			if (s1.length()<=20)
				s1=curr_card.getRbsNumberIbs();
	        String acc_no=null;
	        String pan=null;
	        if (s1.length()>20) {
	            acc_no=s1.substring(s1.length()-20,s1.length());
	            if (s1.length()<=39) {
	                pan=s1.substring(0,s1.length()-20);
	            }
	        } else {
	            acc_no=s1;
	        }
			trp.setAccount_no(acc_no); //(rbs_numberdan oxirgi 20 tasini yozamiz);
			trp.setPan(pan);//(rbs_number ni uzunligi 20 tadan ortiq bulsa boshlanishidan sanab olamiz);
			

			System.out.println("deal_id:=" + deal_id + ", rep_type_id:="
					+ rep_type_id + ", act_id:=" + act_id);
			ISLogger.getLogger().error(
					"deal_id:=" + deal_id + ", rep_type_id:=" + rep_type_id
							+ ", act_id:=" + act_id);
			
			//2026.04.09 todo check_limit quyish
			if (daromad_id==null || daromad_id.equals("")) /*daromad_id kiritilmagan ekan, yangi(birinchi) urinish */ {
				Res resCheck = TrPayService.checkLimit(un, pwd, trp, act_id, deal_id, alias);
				
				if (resCheck.getCode()!=0) { //texnik xato
					alert(resCheck.getName());
					return;
				} 
				if (resCheck.getName()!=null) {
					//2 xil xato kelishi mumkin. 1-si texnik xato("Error:" b-n boshlanadi). 2-si Limit oshdi degan manoda.
					//Texnik xato bulsa daromad oynani kursatmaymiz va faqat xatoni kursatamiz 
					if (resCheck.getName().contains("Error:")) {
						alert(resCheck.getName());
					} else {
						//daromadlar kiritish oynasini chiqarib(kursatib) keyin return qilamiz
						//List<TrPay> docs =	TrPayService.getDocs30Day("00394", "99025509", un, pwd, alias);
						List<TrPay> docs =	TrPayService.getDocs30Day(branch, current.getId_client(), un, pwd, alias);
						sofwnd$payGrid.setModel(new BindingListModelList(docs, true));
						
						if (sofwnd$rcb_sof_id.getItems().size() == 0)
							sofwnd$rcb_sof_id.setModel(new ListModelList(
									AccPayService.getSofList(alias)));
						
						sofwnd.setVisible(true);
						binder.loadAll();
					}
					return;					
				}
			} 			
			
			Res rs = TrPayService
					.doAction(un, pwd, trp, act_id, deal_id, daromad_id, alias);
			// Res rs = new Res(0, "27059543");
			if (rs.getCode() < 0) {
				alert(rs.getName());
			} else {
				trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()), alias);
				trp_res.setCl_name(/* current.getSearch_name() */"");
				trp_res.setIn_name(paywnd$search_name.getValue());
				trp_res.setIn_address(paywnd$address.getValue());
				
				// printp(trp_res,
				// AccPayService.getReportTemplate(deal_id, act_id, alias),
				// deal_id, act_id);

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
				alert("Успешно.");
			}

		} catch (Exception e) {
			alert(e.getMessage());
			ISLogger.getLogger().error(
					"__________________PAY_ERROR(openway):\n"
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
		params.put("TR_PAY_ID", trp.getId());
		params.put("V_DATE", df.format(trp.getDate_created()));

		params.put("CLIENT_NAME1", trp.getCl_name());
		params.put("CLIENT_NAME2", "");
		params.put("CLIENT_NAME3", "");

		params.put("INCLIENT_NAME1", trp.getIn_name());
		params.put("INCLIENT_NAME2", "");
		params.put("INCLIENT_NAME3", "");
		params.put("T_CURRENCY", "USD");
		params.put("TDP_NUM", trp.getDoc_num());
		params.put("POST_ADDRESS", trp.getIn_address());
		params.put("SUMMA6", Double.toString(trp.getAmount() / 100L));
		params.put("ESUMMA6", nf.format(trp.getEqv_amount() / 100L));
		params.put("PSUMMA6", com.is.utils.CheckNull.F2Money(
				trp.getAmount() / 100L, "доллар", "центов"));
		params.put("PESUMMA6", com.is.utils.CheckNull.F2Money(
				trp.getEqv_amount() / 100L, "сум", "тийин"));
		// params.put("TVEOPER", accinfo.getTranz_acct());
		// params.put("TVOPER", accinfo.getTranz_acct());
		// params.put("OPENDOPER", TclientService.get_Curr_acc(branch, alias));
		// params.put("ACCDOPER1", TclientService.getkass_acc(branch, alias));
		params.put("BRANCH_NAME", (com.is.utils.RefDataService.getMfo_name(
				branch, alias).get(0)).getLabel());

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

			repmd = new org.zkoss.util.media.AMedia(current.getName() + "_"
					+ current.getId_client() + ".pdf", "pdf",
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
		// System.out.println("getUser_name() = "
		// + AccPayService.getUser_name(uid, branch, alias));
		System.out.println("uid, branch, alias = " + uid + " " + branch + " "
				+ alias);
		// System.out.println("replaceAll = "+trp.getPan().replaceAll(str,
		// "-"));

	}

	public void print_template(TrPay trp, String report_file, int deal_id, int act_id) {
		
		//Map<String, Object> params = initParamsForReport();
		//CashReceiptOrder applDog = new CashReceiptOrder();
		//	applDog.getReport("com.is.sd_books.report.ApplDog",
		//			"/reports/SD_Dep_sum.doc", "new file", params);
		
		//new ReportBuilder(new CashRecvOrder(un, pwd,
		//		alias, 0L).setBranch(branch).setFileName("Credit_Order")
		//		.setId(id).setName("Credit_order.xls").setPath(path).build().getReport();
		Map<String, Object> params = initParamsForReport();
		CashRecvOrder.getInstance().getReport(
                "com.is.sd_books.report.CredOrV",
                "/reports/CredOrV.xls", "CredOrV", params);

	}

	private Map<String, Object> initParamsForReport() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("un", un);
		params.put("pwd", pwd);
		params.put("branch", branch);
		params.put("alias", alias);
		//params.put("book_id", current.getB_id());
		
		params.put("CASH_RECV_ORDER_NUMBER", "123");
		params.put("DATE", "2 DATE");
		params.put("DEBT_ACCOUNT_NUM", "1 абсде DEBT_ACCOUNT_NUM");
		params.put("AMOUNT", "123456.87");
		params.put("DEBT_CLIENT_NAME", "оываыв sdfsdfsd");
		params.put("CREDIT_ACCOUNT_NUM", "1 cre");
		params.put("AMOUNT_TEXT", "бум бум бум");
		params.put("PAYMENT_PURPOSE", "картасини тулдириш учун");
		
		System.out.println("------MAP:"+params.get("un").toString());
		return params;
	}

	public void onClick$btn_test() {
		Map<String, Object> params = initParamsForReport();
		CashRecvOrder.getInstance().getReport(
                "com.is.openwayj.accpay.CashRecvOrder",
                "/reports/CashRecv.xls", "CashRecvOrder", params);
	}
	
	public CustomerFilter getFilter() {
		return filter;
	}

	public void setFilter(CustomerFilter filter) {
		this.filter = filter;
	}

	public Customer getCurrent() {
		return current;
	}

	public void setCurrent(Customer current) {
		this.current = current;
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
}
