package com.is.openway.accpay;

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
import com.is.openway.PostUtils;
import com.is.openway.StringUtils;
import com.is.openway.XmlUtils;
import com.is.openway.model.AccInfo;
import com.is.openway.model.CardInfo;
import com.is.openway.model.Client;
import com.is.openway.model.ClientInfo;
import com.is.openway.model.ContractResp;
import com.is.openway.model.ContractRs;
import com.is.openway.model.UFXMsgAddClient;
import com.is.openway.model.UFXMsgAddClientResp;
import com.is.openway.model.UFXMsgAddContractAcc;
import com.is.openway.model.UFXMsgAddContractAccResp;
import com.is.openway.model.UFXMsgAddContractCard;
import com.is.openway.model.UFXMsgAddContractCardRes;
import com.is.openway.model.UFXMsgReqClient;
import com.is.openway.model.UFXMsgReqClientResp;
import com.is.openway.model.UFXMsgReqContractResp;
import com.is.openway.model.UFXMsgUpdClient;
import com.is.openway.trpay.TrPay;
import com.is.openway.trpay.TrPayService;
import com.is.report.DPrint;
import com.is.openway.Utils;
import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;
import com.is.openway.customer.Customer;
import com.is.openway.customer.CustomerFilter;
import com.is.openway.customer.CustomerService;
import com.is.openway.customer.PagingListModel;

import com.is.openwayutils.user.Action;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.user.UserService;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;
import com.is.openwayutils.utils.RefData;
import com.is.openwayutils.utils.Res;

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
	static String openwayEndpoint;
	private boolean add_way;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private Window paywnd, printwnd;
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

	private Textbox paywnd$search_name, paywnd$address, paywnd$curacc,
			paywnd$curr_acc_uzs, paywnd$inc_ord_num;
	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox branch_customers, accGrid, cardGrid;

	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Toolbarbutton paywnd$btn_block, paywnd$btn_unblock;
	private Toolbarbutton paywnd$fbt, paywnd$lock, paywnd$application;
	private Toolbar paywnd$pay_tlb;
	private int pay_card_doc_id;
	private NumberFormat nf = NumberFormat.getInstance();

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
		if (openwayEndpoint == null || openwayEndpoint == ""
				|| openwayEndpoint.equals(""))
			openwayEndpoint = ConnectionPool.getValue("OPENWAY_ENDPOINT");
		// filter.setEndpoint(openwayEndpoint);

		branch_customers.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) {
				Customer vCustomer = (Customer) data;
				row.setAttribute("row", (Object) row);
				row.appendChild(new Listcell(""));
				UFXMsgReqClientResp clResp = CustomerService
						.getCustomers_openway(vCustomer.getBranch(),
								vCustomer.getId_client(),
								vCustomer.getP_pinfl(), openwayEndpoint, "");
				if (clResp.get_resp_code().equals("0")) {
					vCustomer.setO_city(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getBaseAddress().getCity());
					vCustomer.setO_client_type(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getClientType());
					vCustomer.setO_post_address_fact(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getBaseAddress().getAddressLine2());
					vCustomer.setO_security_name(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getClientInfo().getSecurityName());
					vCustomer.setWay_exist(true);
				}

				row.setValue((Object) vCustomer);

				if (clResp.get_resp_code().equals("0")) {
					row.appendChild(new Listcell(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getClientInfo().getSocialNumber()));
					row.appendChild(new Listcell(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getClientInfo().getShortName()));
					row.appendChild(new Listcell(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getClientInfo().getBirthDate()));
					row.appendChild(new Listcell(clResp.getMsgData()
							.getInformationObject().getDataRs().getClientRs()
							.getClient().getClientInfo().getRegNumber()));

				} else {
					if (clResp.get_resp_code().equals("1930")) { // "Client not found"
																	// xatosi
						row.appendChild(new Listcell(" - "));
						row.appendChild(new Listcell(" - "));
					} else {
						row.appendChild(new Listcell("Код Ошибка: "
								+ clResp.get_resp_code()));
						row.appendChild(new Listcell(clResp.get_resp_text()));
					}
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
				row.appendChild(new Listcell(pAccInfo.getCard()));
				// row.appendChild(new Listcell(df.format(pAccInfo
				// .getAb_expirity())));
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getTranz_acct()));
				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));
				row.appendChild(new Listcell(""));
			}
		});

		binder.loadAll();
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
		/*if (add_card_wnd$o_product_code1.getItems().size() == 0)
			add_card_wnd$o_product_code1.setModel(new ListModelList(
					CustomerService.getSubProduct_code1_way4(alias)));*/
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

			AccountFilter filt = new AccountFilter();
			filt.setBranch(branch);
			filt.setAcc_bal("22618");
			filt.setCurrency("840");
			filt.setClient(current.getId_client());

			List<AccInfo> infoList = new ArrayList<AccInfo>();
			// ABSdan
			List<Account> accList = AccountService.getAccountsFl(0, 100, filt,
					alias);
			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_ACC)
			List<AccInfo> accList2 = CustomerService.getContractAccList_ABS(
					current.getBranch(), current.getId_client(), alias);

			// WAYdan
			UFXMsgReqContractResp clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							openwayEndpoint, "");
			ArrayList<ContractResp> contractAccList = new ArrayList<ContractResp>();

			if (clResp.getResp_code().equals("0")) {
				if (clResp.getMsgDataReq().getApplication().getDataRsObject() != null) {
					ArrayList<ContractRs> contractList = clResp.getMsgDataReq()
							.getApplication().getDataRsObject().getContractRs();
					for (int i = 0; i < contractList.size(); i++) {
						if (contractList.get(i).getContract().getProduct()
								.getAddInfo().getParm().getValue()
								.equals("Account")) {
							contractAccList.add(contractList.get(i)
									.getContract());
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
							.getContractIDT()
							.getCBSNumber()
							.equals(accList.get(i).getBranch()
									+ accList.get(i).getId())) {
						inf.setWay_exist(true);
						inf.setCbsNumber(contractAccList.get(j)
								.getContractIDT().getCBSNumber());
						inf.setContractNumber(contractAccList.get(j)
								.getContractIDT().getContractNumber());
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
				inf.setCbsNumber(contractAccList.get(j).getContractIDT()
						.getCBSNumber());
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
			}

		}

	}

	public void onDoubleClick$accGrid() {

		String client_id;
		/*
		 * client_id = TclientService.check_user(alias, branch,
		 * current.getClient()); if (client_id == "") {
		 * alert("Клиент не объединен"); return; }
		 * 
		 * link lnk = CustomerService.get_link_tieto(current.getClient(),
		 * branch, alias);
		 * 
		 * paywnd$curacc.setValue(lnk.Cur_acc);
		 */

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
	}

	TrPay trp_res = null;

	private void pay(int deal_id, int rep_type_id, int act_id) {

		String client_id;
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
			trp.setCard_acc(curr_acc.getCbsNumber().substring(5));
			trp.setCur_acc("1"); // --default
			trp.setAccount_no(curr_acc.getContractNumber());
			trp.setCl_name(current.getName());
			trp.setEmp_id(uid);
			trp.setPan(TrPayService.getCardNumber_IBS(curr_acc.getBranch(), curr_acc.getClient(), 
					curr_acc.getProductCode1(), curr_acc.getContractNumber()+"%", 
					alias)); //todo getCardNumber_IBS 
			trp.setDoc_num(paywnd$inc_ord_num.getValue());

			System.out.println("curr_acc=" + curr_acc.toString());

			System.out.println("deal_id:=" + deal_id + ", rep_type_id:="
					+ rep_type_id + ", act_id:=" + act_id);
			ISLogger.getLogger().error(
					"deal_id:=" + deal_id + ", rep_type_id:=" + rep_type_id
							+ ", act_id:=" + act_id);
			ISLogger.getLogger().info("Versiya_1");
			Res rs = TrPayService
					.doAction(un, pwd, trp, act_id, deal_id, alias);
			// Res rs = new Res(0, "27059543");
			if (rs.getCode() < 0) {
				ISLogger.getLogger().error("alert:11111");
				alert(rs.getName());
			} else {
				trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()),
						alias);
				trp_res.setCl_name(/* current.getSearch_name() */"");
				trp_res.setIn_name(paywnd$search_name.getValue());
				trp_res.setIn_address(paywnd$address.getValue());

				//printp(trp_res,
				//		AccPayService.getReportTemplate(deal_id, act_id, alias),
				//		deal_id, act_id);

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
