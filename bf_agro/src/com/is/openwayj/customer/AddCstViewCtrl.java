package com.is.openwayj.customer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import java.util.Locale;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.python.antlr.PythonParser.else_clause_return;
import org.python.google.common.base.Strings;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.SuspendNotAllowedException;
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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
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
import com.is.openway.accpay.AccPayService;
import com.is.openway.model.UFXMsgAddContractAddress;
import com.is.openway.model.UFXMsgReIssue;
import com.is.openwayj.PostUtils;
import com.is.openwayj.StringUtils;
import com.is.openwayj.XmlUtils;
import com.is.openwayj.model.AccCardRequest;
import com.is.openwayj.model.AccCardResponse;
import com.is.openwayj.model.AccInfo;
import com.is.openwayj.model.Balance;
import com.is.openwayj.model.BalanceCardBody;
import com.is.openwayj.model.BalanceCardRequest;
import com.is.openwayj.model.BalanceCardResponse;
import com.is.openwayj.model.Balances;
import com.is.openwayj.model.CardInfo;
import com.is.openwayj.model.CardInfoRequest;
import com.is.openwayj.model.CardInfoResponse;
import com.is.openwayj.model.ClearPinRequest;
import com.is.openwayj.model.ClearPinResponse;
import com.is.openwayj.model.ClientAccCardRequest;
import com.is.openwayj.model.ClientAccCardResponse;
import com.is.openwayj.model.ClientContractsResponse;
import com.is.openwayj.model.Client_old;
import com.is.openwayj.model.ClientInfo;
import com.is.openwayj.model.ClientInfoResponse;
import com.is.openwayj.model.Contract;
import com.is.openwayj.model.ContractResp;
import com.is.openwayj.model.ContractRs;
import com.is.openwayj.model.InfoContract;
import com.is.openwayj.model.LockCardRequest;
import com.is.openwayj.model.LockCardResponse;
import com.is.openwayj.model.RegCardRequest;
import com.is.openwayj.model.SubProduct;
import com.is.openwayj.model.UFXMsgAddClient;
import com.is.openwayj.model.UFXMsgAddClientResp;
import com.is.openwayj.model.UFXMsgAddContractAcc;
import com.is.openwayj.model.UFXMsgAddContractAccResp;
import com.is.openwayj.model.UFXMsgAddContractCard;
import com.is.openwayj.model.UFXMsgAddContractCardRes;
import com.is.openwayj.model.UFXMsgInqContract;
import com.is.openwayj.model.UFXMsgInqContractResp;
import com.is.openwayj.model.UFXMsgReqClient;
import com.is.openwayj.model.UFXMsgReqClientResp;
import com.is.openwayj.model.UFXMsgReqContractResp;
import com.is.openwayj.model.UFXMsgUpdClient;
import com.is.openwayj.model.UFXMsgUpdContractAcc;
import com.is.openwayj.model.UFXMsgUpdContractAccResp;
import com.is.openwayj.model.UpdateSMSRequest;
import com.is.report.DPrint;
import com.is.openwayj.Utils;
import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;
import com.is.openwayj.customer.CustomerService;

import com.is.openwayutils.user.UserService;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;
import com.is.tietovisa.Cons;
import com.is.tietovisa.model.CommonResponse;
import com.is.tietovisa.model.RowType_AddCardToStopList_Request;
import com.is.tietovisa.model.RowType_RemoveCardFromStop_Request;
import com.is.tietovisa.model.RowType_ResetPINCounter_Request;
import com.is.utils.RefData;
import com.is.utils.Res;

public class AddCstViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CustomerFilter filter = new CustomerFilter();
	public Customer current = new Customer();
	public Customer copyOfCurrent;
	public AccInfo curr_acc = new AccInfo();
	public CardInfo curr_card = new CardInfo();
	private static HashMap<String, String> mapUsersDepartments;
	
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
	static String openwayEndpoint, url_way4_clientinfo, url_way4_client_acc_card, url_way4_clientcontracts, url_way4_acc_card, url_way4_reg_card, url_way4_block_unblock;
	static String url_way4_cardinfo, url_way4_updatesms, url_way4_balance_card, url_way4_clear_pin;
	
	private boolean is_test_mode=false;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private Window add_everywhere, add_account, add_card_wnd, lock_card_wnd, update_sms_wnd_acc, update_sms_wnd_card, showBalanceWindow;
	private Datebox add_everywhere$ap_birthday;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Grid add_everywhere$addgrdl, showBalanceWindow$showBalanceGrid;
	private Grid add_everywhere$addgrdr;
	private Grid add_account$addgrdl;
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
	private RefCBox add_everywhere$ao_category_client;

	private RefCBox add_account$acc_bal, add_everywhere$acc_bal;
	private RefCBox add_account$o_product_code1, add_account$o_card_product_code1, add_everywhere$o_product_code1, add_everywhere$o_card_product_code1;
	private RefCBox add_card_wnd$o_product_code1, lock_card_wnd$rcb_stop_cause;
	private org.zkoss.zul.Row lock_card_wnd$row_stop_cause;
	
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
	private Textbox add_account$id_order, add_everywhere$id_order, add_account$o_postal_code;
	private Textbox add_account$o_comment_text, add_everywhere$o_comment_text;
	private Textbox add_card_wnd$o_rbs_number;
	private Textbox add_card_wnd$o_comment_text, add_everywhere$o_card_comment_text, lock_card_wnd$txt_comment_text, update_sms_wnd_card$txt_acc_phone, update_sms_wnd_acc$txt_acc_phone/*, update_sms_wnd$txt_card_phone*/;
	private Toolbarbutton lock_card_wnd$lock_card_btn, lock_card_wnd$unlock_card_btn, update_sms_wnd_card$sms_on_btn, update_sms_wnd_card$sms_off_btn, update_sms_wnd_acc$sms_on_btn, update_sms_wnd_acc$sms_off_btn, showBalanceWindow$showBalanceCloseBtn;
	private Label update_sms_wnd_card$lbl_acc_rbs_number, update_sms_wnd_acc$lbl_acc_rbs_number/*, update_sms_wnd$lbl_card_rbs_number*/;
	private Listheader lh_sms_acc, lh_sms_card;
	
	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox branch_customers, accGrid, cardGrid;

	private Textbox txbId_client;
	private Textbox txbPinfl;

	DecimalFormat dcf;
	DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());

	// private Textbox txtPassportSerial;
	// private Textbox txtName;
	// private Datebox dbxB_date;

	public AddCstViewCtrl() {
		super('$', false, false);
		this._pageSize = 10;
	}

	public void doAfterCompose(final Component comp) throws Exception,
			ConnectException, SQLException {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);

		binder.bindBean("filter", this.filter);
		binder.bindBean("current", this.current);
		binder.bindBean("curr_acc", this.curr_acc);
		binder.bindBean("curr_card", this.curr_card);

		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		
		if ( uid==39499 && un.equals("admin"))
			is_test_mode=true;
		else
			is_test_mode=false;
		
		//if (openwayEndpoint == null || openwayEndpoint == "" || openwayEndpoint.equals(""))
		//	openwayEndpoint = ConnectionPool.getValue("OPENWAY_ENDPOINT");
		//todo. buerda zaproslar soni kup bulyapti. hammasini bir tablega yozib bir zaprosda olishni yulga quyish kerak
		if (url_way4_clientinfo == null || url_way4_clientinfo == "" || url_way4_clientinfo.equals(""))
			url_way4_clientinfo = ConnectionPool.getValue("URL_WAY4_CLIENTINFO");
		if (url_way4_client_acc_card == null || url_way4_client_acc_card == "" || url_way4_client_acc_card.equals(""))
			url_way4_client_acc_card = ConnectionPool.getValue("URL_WAY4_CLIENTACCCARD");
		if (url_way4_acc_card == null || url_way4_acc_card == "" || url_way4_acc_card.equals(""))
			url_way4_acc_card = ConnectionPool.getValue("URL_WAY4_ACCCARD");
		if (url_way4_reg_card == null || url_way4_reg_card == "" || url_way4_reg_card.equals(""))
			url_way4_reg_card = ConnectionPool.getValue("URL_WAY4_REG_CARD");
		if (url_way4_clientcontracts == null || url_way4_clientcontracts == "" || url_way4_clientcontracts.equals(""))
			url_way4_clientcontracts = ConnectionPool.getValue("URL_WAY4_CLIENTCONTRACTS");
		if (url_way4_block_unblock == null || url_way4_block_unblock == "" || url_way4_block_unblock.equals(""))
			url_way4_block_unblock = ConnectionPool.getValue("URL_WAY4_BLOCK_UNBLOCK");
		if (url_way4_cardinfo == null || url_way4_cardinfo == "" || url_way4_cardinfo.equals(""))
			url_way4_cardinfo = ConnectionPool.getValue("URL_WAY4_CARDINFO");
		if (url_way4_updatesms == null || url_way4_updatesms == "" || url_way4_updatesms.equals(""))
			url_way4_updatesms = ConnectionPool.getValue("URL_WAY4_UPDATESMS");
		if (url_way4_balance_card == null || url_way4_balance_card == "" || url_way4_balance_card.equals(""))
			url_way4_balance_card = ConnectionPool.getValue("URL_WAY4_BALANCE_CARD");
		if (url_way4_clear_pin == null || url_way4_clear_pin == "" || url_way4_clear_pin.equals(""))
			url_way4_clear_pin = ConnectionPool.getValue("URL_WAY4_CLEAR_PIN");

		
		// filter.setP_pinfl("56789012340078");
		// filter.setId_client("60000001");
		filter.setEndpoint(openwayEndpoint);

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
				if (clResp.isSuccess() ) {
					if (clResp.getBody().getAddress()!=null){
						vCustomer.setO_city(clResp.getBody().getAddress().getCity());
						//vCustomer.setO_client_type();
						vCustomer.setO_post_address_fact(clResp.getBody().getAddress().getAddressLine2());
					}
					if (clResp.getBody()!=null)
						vCustomer.setO_security_name(clResp.getBody().getSecurityName());
					vCustomer.setWay_exist(true);
				}
				// knopka redaktirovat vezde
				Listcell edit_cell = new Listcell();
				Toolbarbutton btedit = new Toolbarbutton();
				btedit.setDisabled(true); //vaqtinchaga.!!!
				btedit.setLabel("");
				btedit.setImage("/images/config.png");
				btedit.setAttribute("br_cl", (Object) vCustomer);
				// btedit.setAttribute("is_br", (Object) true);
				btedit.setTooltiptext("Редактировать везде");
				btedit.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								CheckNull.clearForm(add_everywhere$addgrdl);
								CheckNull.clearForm(add_everywhere$addgrdr);
								// if (cur_branch_customer != null) {
								// fill_form(cur_branch_customer, tietocl);
								// } else if (tietocl != null) {
								// fill_form(tietocl);
								// }
								// fill_form(current);
								current = (Customer) event.getTarget()
										.getAttribute("br_cl");
								copyOfCurrent = current.clone(current);
								loadRefData();
								if (current.getP_code_adr_region() != null)
									add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
											CustomerService.getDistrByRegion(
													current.getP_code_adr_region(),
													alias)));
								add_everywhere
										.setTitle("Редактирование клиента [Bank] - [Way4]");
								add_everywhere.setVisible(true);
							}
						});
				edit_cell.appendChild((Component) btedit);
				row.appendChild((Component) edit_cell);
				// knopka end redaktirovat vezde
				row.setValue((Object) vCustomer);

				if (clResp.isSuccess() && clResp.getBody()!=null) {
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
						row.appendChild(new Listcell("Код Ошибка: "
								+ ""));
						row.appendChild(new Listcell(clResp.getMessage()));
					//}
					row.appendChild(new Listcell(" - "));
					row.appendChild(new Listcell(" - "));

				}
				// knopka
				//final Listcell t_edit_cell = new Listcell();
				//Toolbarbutton btt = new Toolbarbutton();
				//btt.setLabel("");
				//btt.setImage("/images/link16.png");
				//btt.setAttribute("br_cl", (Object) vCustomer);
				//btt.setTooltiptext("Редактировать Openway");
				//btt.addEventListener("onClick",
				//		(EventListener) new EventListener() {
				//			public void onEvent(final Event event)
				//					throws Exception {
				//				current = (Customer) event.getTarget()
				//						.getAttribute("br_cl");
				//				copyOfCurrent = current.clone(current);
				//				loadRefData();
				//				if (current.getP_code_adr_region() != null)
				//					add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
				//							CustomerService.getDistrByRegion(
				//									current.getP_code_adr_region(),
				//									alias)));
				//				add_everywhere
				//						.setTitle("Открытие клиента Openway");
				//				add_everywhere.setVisible(true);
				//			}
				//		});
				//t_edit_cell.appendChild((Component) btt);
				//row.appendChild((Component) t_edit_cell);
				row.appendChild(new Listcell(""));
				//

				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getId_client())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getName())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getP_birthday())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getP_pinfl()) ));
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
				// row.appendChild(new Listcell(pAccInfo.getSurname()));
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getTranz_acct()));
				row.appendChild(new Listcell(pAccInfo.getProductCode1()));

				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));
				row.appendChild(new Listcell(pAccInfo.getPhone()));

				//sms ulash/uzish - begin
				Listcell card_sms_cell = new Listcell();
				// sms подключен(не подключен) / номер [отключить СМС] / номер
				// [подключить СМС]

				final Button btnSmsOn = new Button("Включить");
				btnSmsOn.setAttribute("accInfo", (Object) pAccInfo);
				//btnSmsOn.setAttribute("phoneMobile", (Object) labelPhoneMobile);
				btnSmsOn.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// Привязка телефона к карте. Подключение СМС
								// уведомлений на номер
								AccInfo acc1 = (AccInfo) event.getTarget().getAttribute("accInfo");
								acc1.setSocialNumber(current.getP_pinfl());
								smsOnMethod_acc(acc1);
							}
						});
				card_sms_cell.appendChild((Component) btnSmsOn);
				
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
								//deActivateSms(event);
								
							}
						});
				card_sms_cell.appendChild((Component) btnSmsOff);
				
				row.appendChild((Component) card_sms_cell);

				//sms ulash/uzish - end
				
				// knopka otkrit shetevoy kontrakt/redaktirovat
				Listcell edit_contract_cell = new Listcell();
				Toolbarbutton btedit_contr = new Toolbarbutton();
				btedit_contr.setDisabled(true); //vaqtincha!!!
				btedit_contr.setLabel("");
				btedit_contr.setImage("/images/config.png");
				btedit_contr.setAttribute("contr_acc", (Object) pAccInfo);
				// btedit.setAttribute("is_br", (Object) true);
				btedit_contr
						.setTooltiptext("Открыть/Редактировать счетовой контракт");
				btedit_contr.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// CheckNull.clearForm(add_everywhere$addgrdl);
								// CheckNull.clearForm(add_everywhere$addgrdr);

								curr_acc = (AccInfo) event.getTarget()
										.getAttribute("contr_acc");
								// copyOfCurrent = current.clone(current);
								loadRefAccData();

								add_account
										.setTitle("Редактирование счетовой контракт [Way4]");
								add_account.setVisible(true);
								binder.loadComponent(add_account);
							}
						});
				edit_contract_cell.appendChild((Component) btedit_contr);
				row.appendChild((Component) edit_contract_cell);
				// knopka end redaktirovat shetevoy kontrakt
			}
		});

		cardGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				CardInfo pAccInfo = (CardInfo) data;

				row.setValue(pAccInfo);
				// row.appendChild(new Listcell(pAccInfo.getCard()));
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getCardExpiry()));// expiry
				row.appendChild(new Listcell(pAccInfo.getProductCode1()));
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));
				row.appendChild(new Listcell(pAccInfo.getRbsNumberIbs()));
				//row.appendChild(new Listcell(pAccInfo.getSTATUS()));// status

				// --Balance
				Listcell card_balance_cell = new Listcell();
				Button btn_show_bal = new Button();
				//btn_show_bal.setLabel("Баланс...");
				btn_show_bal.setLabel("...");
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
								formatSymbols.setDecimalSeparator('.');
								formatSymbols.setGroupingSeparator(' ');
								dcf = new DecimalFormat("###,###,###,###,##0.00", formatSymbols);

								while (showBalanceWindow$showBalanceGrid.getRows().getChildren().size()>0) 
									showBalanceWindow$showBalanceGrid.getRows().removeChild( (org.zkoss.zul.Row)showBalanceWindow$showBalanceGrid.getRows().getChildren().get(0) );
								
								List < BalanceCardBody > bals = getCardBalances(cardInfo.getRbsNumberIbs());
								if (bals != null) {

									for(int i=0; i<bals.size();i++){
										org.zkoss.zul.Row row = new org.zkoss.zul.Row();
										
									
										Label lb2 = new Label();
										lb2.setValue("Balance");
										row.appendChild(lb2);
										Textbox tb2 = new Textbox();
										tb2.setStyle("text-align: right");
										//tb2.setValue(bals.get(i).getBalance());
										tb2.setValue(dcf.format((new BigDecimal(bals.get(i).getBalance()).divide(new BigDecimal("100")) ))) ; 
										tb2.setReadonly(true);
										row.appendChild(tb2);

										lb2 = new Label();
										lb2.setValue("Lock balance");
										row.appendChild(lb2);
										tb2 = new Textbox();
										tb2.setStyle("text-align: right");
										//tb2.setValue(bals.get(i).getLock_balance());
										tb2.setValue(dcf.format((new BigDecimal(bals.get(i).getLock_balance()).divide(new BigDecimal("100")) ))) ;
										tb2.setReadonly(true);
										row.appendChild(tb2);

										showBalanceWindow$showBalanceGrid.getRows().appendChild(row);
									}
								}
								showBalanceWindow.setVisible(true);
							}
						});
				card_balance_cell.appendChild((Component) btn_show_bal);
				row.appendChild((Component) card_balance_cell); //2025.05.12
				
				
				// --deystviya s kartami
				Listcell card_status_cell = new Listcell();
				card_status_cell.setStyle("text-align: left");
				Toolbarbutton btblock_card = new Toolbarbutton();
				btblock_card.setTooltiptext("Блокировать/закрытие карту");
				btblock_card.setImage("/images/lock1.png");
				btblock_card.setAttribute("card", (Object) pAccInfo);

				btblock_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
							
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								lockCardMethod(card1);

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
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								unlockCardMethod(card1);
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
                                // todo
								reIssueCardMethod(event);
							}
						});
				card_status_cell.appendChild((Component) btReissue_card);

				Toolbarbutton btResetPIN_card = new Toolbarbutton();
				btResetPIN_card.setTooltiptext("Сбросить счетчик ПИН");
				btResetPIN_card.setImage("/images/resetpin.png");
				btResetPIN_card.setAttribute("card", (Object) pAccInfo);

				btResetPIN_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								//alert(card1.getCARD_NAME());
								card1.setSocialNumber(current.getP_pinfl());
								resetPINMethod(card1);
							}
						});
				card_status_cell.appendChild((Component) btResetPIN_card);
				
				Label lbl = new Label();
				lbl.setValue(pAccInfo.getSTATUS());
				card_status_cell.appendChild((Component) lbl);
				
				
				row.appendChild((Component) card_status_cell);
				// -- end deystviya po kartam
				
				row.appendChild(new Listcell(pAccInfo.getSTATUS2()));// status2
				
				//sms ulangan nomerni kursatish
				row.appendChild(new Listcell(CustomerService.getCardSmsPhoneNumber2(branch, current.getId_client(), pAccInfo.getRbsNumberIbs())));
				
				//sms ulash/uzish - begin
				Listcell card_sms_cell = new Listcell();
				// sms подключен(не подключен) / номер [отключить СМС] / номер
				// [подключить СМС]

				final Button btnSmsOn = new Button("Включить");
				btnSmsOn.setAttribute("cardInfo", (Object) pAccInfo);
				//btnSmsOn.setAttribute("phoneMobile", (Object) labelPhoneMobile);
				btnSmsOn.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// Привязка телефона к карте. Подключение СМС
								// уведомлений на номер
								CardInfo acc1 = (CardInfo) event.getTarget().getAttribute("cardInfo");
								acc1.setSocialNumber(current.getP_pinfl());
								smsOnMethod_card(acc1);
							}
						});
				card_sms_cell.appendChild((Component) btnSmsOn);
				
				final Button btnSmsOff = new Button("Отключить");
				btnSmsOff.setAttribute("cardInfo", (Object) pAccInfo);
				// btnSmsOff.setAttribute("lbox", (Object) lbox);
				btnSmsOff.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// Отвязка телефона от карты. Отключение СМС
								// уведомлений
								// с номера
								CardInfo acc1 = (CardInfo) event.getTarget().getAttribute("cardInfo");
								acc1.setSocialNumber(current.getP_pinfl());
								smsOffMethod_card(acc1);
							}
						});
				card_sms_cell.appendChild((Component) btnSmsOff);
				
				row.appendChild((Component) card_sms_cell);

				//sms ulash/uzish - end
				
				
				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));

				// knopka otkrit kartochniy kontrakt/redaktirovat
				Listcell edit_contract_cell = new Listcell();
				Toolbarbutton btedit_contr_card = new Toolbarbutton();
				btedit_contr_card.setLabel("");
				btedit_contr_card.setImage("/images/config.png");
				btedit_contr_card.setAttribute("contr_card", (Object) pAccInfo);
				// btedit.setAttribute("is_br", (Object) true);
				btedit_contr_card
						.setTooltiptext("Открыть/Редактировать карточный контракт");
				btedit_contr_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								// CheckNull.clearForm(add_everywhere$addgrdl);
								// CheckNull.clearForm(add_everywhere$addgrdr);

								curr_card = (CardInfo) event.getTarget()
										.getAttribute("contr_card");
								// copyOfCurrent = current.clone(current);
								loadRefAccData();

								add_card_wnd
										.setTitle("Редактирование карточный контракт [Way4]");
								add_card_wnd.setVisible(true);
								binder.loadComponent(add_card_wnd);
							}
						});
				edit_contract_cell.appendChild((Component) btedit_contr_card);
				// row.appendChild((Component) edit_contract_cell);
				// row.appendChild(new Listcell("Button"));
				// knopka end redaktirovat карточный kontrakt
			}
		});
		lh_sms_acc.setVisible(false);
		binder.loadAll();

	}

	public void onClick$tbtn_search() {
		/*
		 * if (this.filter==null) { this.filter=new CustomerFilter(); }
		 * this.filter.setId_client(txbId_client.getValue());
		 * this.filter.setP_pinfl(txbPinfl.getValue());
		 * this.filter.setName(txtName.getValue());
		 * this.filter.setP_passport_serial(txtPassportSerial.getValue());
		 * this.filter.setP_birthday(this.filter.getB_date());
		 */
		if (filter.getBranch() == null)
			filter.setBranch(branch);
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
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

	
	public void onClick$btn_add_everywhere() {
		current = new Customer();
		curr_acc = new AccInfo();
		curr_card = new CardInfo();
		
		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);
		this.add_everywhere.setTitle("Открытие клиента [БАНК] - [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefData();
		loadRefDataAccAndCard();
		//
		current.setO_client_type("PR");
		current.setCode_resident("1");
		current.setCode_country("860");
		current.setP_code_citizenship("860");
		curr_acc.setAcc_bal("22618");
		//if (is_test_mode) {
		//	CustomerService.prepareFakeValues(current);
		//	curr_acc.setId_order("001");
		//	curr_acc.setProductCode1("DEB_USD");
		//	curr_acc.setCommentText("testing visa");
		//}
		
		this.add_everywhere.setVisible(true);
		binder.loadComponent(add_everywhere);

	}

	public void onClick$btn_add_way() {
		if (current.isWay_exist()) {
			alert("Клиент уже сушествует в системе WAY");
			return;
		}
		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);
		copyOfCurrent = current.clone(current);
		curr_acc = new AccInfo();
		curr_card = new CardInfo();
		// loaddata qilamiz shu yerda
		loadRefData();
		loadRefDataAccAndCard();
		if (current.getP_code_adr_region() != null)
			add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
					CustomerService.getDistrByRegion(
							current.getP_code_adr_region(),
							alias)));

		//
		this.add_everywhere.setTitle("Открытие клиента [WAY4]");
		this.add_everywhere.setVisible(true);
		binder.loadComponent(add_everywhere);
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

	public void loadRefDataAccAndCard() {
		if (add_everywhere$acc_bal.getItems().size() == 0)
			add_everywhere$acc_bal.setModel(new ListModelList(CustomerService
					.getAcc_bal(alias)));
		if (add_everywhere$o_product_code1.getItems().size() == 0)
			add_everywhere$o_product_code1.setModel(new ListModelList(
					CustomerService.getProduct_code1_way4(alias)));
		if (add_everywhere$o_card_product_code1.getItems().size() == 0)
			//add_everywhere$o_card_product_code1.setModel(new ListModelList(CustomerService
			//	.getSubProductByProduct("", alias)));
		add_everywhere$o_card_product_code1.setModel(new ListModelList(CustomerService
				.getSubProduct_code1_way4(alias)));
		
		/*
		 * if (add_card_wnd$o_product_code1.getItems().size() == 0)
		 * add_card_wnd$o_product_code1.setModel(new ListModelList(
		 * CustomerService.getSubProduct_code1_way4(alias)));
		 */

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
		if (add_account$o_card_product_code1.getItems().size() == 0)
			add_account$o_card_product_code1.setModel(new ListModelList(CustomerService
				.getSubProduct_code1_way4(alias)));

	}

	//открытие клиента [Way4] ойнасидаги "Сохранить" тугма:
	public void onClick$add_btn$add_everywhere() throws JsonProcessingException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_msg_16413 = "";
		//System.out.println(current);
		
		err=check_user(branch, Integer.toString(uid), alias);
		if (!err.contains("Ok.")) {
			alert(err);
			return;
		}
		
		err="";
		if (add_everywhere$ao_category_client.getValue() == null
				|| add_everywhere$ao_category_client.getValue().equals("")) {
			fl_err = true;
			err = String.valueOf(err)
					+ "\nНе заполнено поле Тип(категория) клиента";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!add_everywhere$ap_passport_serial.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!add_everywhere$ap_passport_number.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи документа";
		}
		if (!add_everywhere$ap_passport_place_registration.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан Д.У.Л.*";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_pinfl.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (current.getCode_type().equals("08") && add_everywhere$ap_pinfl.getValue().length() != 14) { //ПИНФЛ
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (!current.getCode_type().equals("08") && add_everywhere$ap_pinfl.getValue().length() != 9) { //ИНН
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ(ИНН 9 символ для юрлицо)";
		}
		if (!add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9 ]*")
				|| add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		if (!add_everywhere$ap_number_tax_registration.getValue().matches(
				"[0-9]*")
				|| add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(add_everywhere$ao_security_name.getValue())) {
			fl_err = true;
			err = String.valueOf(err)
					+ "\nКодовое слово для идентификации клиента";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		/* bu uzi aslida komentga olib quyilgan ekan
		 * if (CheckNull.isEmpty(add_everywhere$ap_birth_place.getValue())) {
		 * fl_err = true; err = String.valueOf(err) + "\nМесто рождения"; }
		 */
		if (!add_everywhere$ap_birth_place.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}

		if (CheckNull.isEmpty(add_everywhere$ap_code_gender.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПоловая принадлежность";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер мобилный телефон";
		}
		if ( add_everywhere$ap_phone_mobile.getValue().toString().length() != 12
				|| !add_everywhere$ap_phone_mobile.getValue().toString().matches("[0-9]+")) {
			fl_err = true;
			err = String.valueOf(err) + "\nНеверный номер мобилного телефона. (Только цифры, 12 знак. пример 998901234567)";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_email_address.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nЭлектронная почта";
		}
		if (!add_everywhere$ap_email_address.getValue().contains("@")) {
			fl_err = true;
			err = String.valueOf(err) + "\nЭлектронная почта не содержаший @-символ. ";
		}
		if (!CheckNull.isEmpty(add_everywhere$ap_email_address.getValue())) {
			if (add_everywhere$ap_email_address.getValue().trim().substring(0,1).equals("@")) {
				fl_err = true;
				err = String.valueOf(err) + "\nЭлектронная почта не должно начинаться с @-символ.";
			}
		}
		if (add_everywhere$acode_country.getValue().equals("860")) {
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод области";
			}
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод района";
			}
		}
		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (!add_everywhere$ao_city.getValue().matches(
			"[a-zA-Z0-9\\s\\.\\,_\\/-]+")) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		
		if (CheckNull.isEmpty(add_everywhere$ap_zip_code.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый индекс";
		}
		if (add_everywhere$ap_zip_code.getValue().length() > 7) { 
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый индекс не должно быть больше 7 символ";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				/*|| add_everywhere$ap_post_address.getValue().length() > 95*/) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (add_everywhere$ap_post_address.getValue().length() > 160) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес не должно быть больше 160 смивол";
		}
		if (!add_everywhere$ao_post_address_fact.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				/*|| add_everywhere$ao_post_address_fact.getValue().length() > 95*/) {
			fl_err = true;
			err = String.valueOf(err) + "\nФактический адрес";
		}
		if (add_everywhere$ao_post_address_fact.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nФактический адрес не должно быть больше 95 символ";
		}
		
		if (CheckNull.isEmpty(add_everywhere$ao_address_fact_date.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата активации использования адреса";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_nation.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНациональность";
		}
		/* bu ham avvaldan olib quyilgan ekan
		 * if (CheckNull.isEmpty(add_everywhere$ap_code_tax_org.getValue())) {
		 * fl_err = true; err = String.valueOf(err) +
		 * "\nКод налоговой организации"; }
		 */
		/*if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}*/
		//klient tipini tekshirish
		if (current!=null && current.getCode_type()!=null && current.getCode_type().equals("08") && 
				!(add_everywhere$ao_category_client.getValue() == null
				|| add_everywhere$ao_category_client.getValue().equals("")) && add_everywhere$ao_category_client.getValue().equals("CR")) 		
		{
			fl_err = true;
			err = String.valueOf(err) + "\nНе соответствие \"Категория клиента\"-CR(Corporate resident) с \"Тип клиента\"-Физическое лицо! \" ";
		}
		if (current!=null && current.getCode_type()!=null && !current.getCode_type().equals("08") && 
				!(add_everywhere$ao_category_client.getValue() == null
				|| add_everywhere$ao_category_client.getValue().equals("")) && 
				(add_everywhere$ao_category_client.getValue().equals("PR") || 
				  add_everywhere$ao_category_client.getValue().equals("PNR") || add_everywhere$ao_category_client.getValue().equals("RPR")) ) 		
		{
			fl_err = true;
			err = String.valueOf(err) + "\nНе соответствие \"Категория клиента\"-"+add_everywhere$ao_category_client.getValue()+" с \"Тип клиента\"-"+current.getCode_type()+"! \" ";
		}
		
		//curr_acc tekshirish
		if (add_everywhere$acc_bal.getValue() == null
				|| add_everywhere$acc_bal.getValue().equals("")) {
			fl_err = true;
			err = String.valueOf(err) + "\nНе заполнено Код балансовый счет";
		}
		if (CheckNull.isEmpty(add_everywhere$id_order.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПорядковый номер";
		}
		if (CheckNull.isEmpty(add_everywhere$o_product_code1.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод счетевого продукта";
		}
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
		
		//curr_card tekshirish
		if (CheckNull.isEmpty(add_everywhere$o_product_code1.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод карточного продукта";
		}
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
		
		current.setName(current.getP_family() + " " + current.getP_first_name()
				+ " " + current.getP_patronymic());
		current.setName(current.getName().trim());
		current.setOrder_dprt(getDepartmentByUserID(Integer.toString(uid), alias));
		//ISLogger.getLogger().error(
		//		"current.setOrder_dprt: " +getDepartmentByUserID(Integer.toString(uid), alias) +". userId: "+ Integer.toString(uid)+".");

		Res res = null;

		if (current.getId_client() == null || current.getId_client().equals("")) {
			current.setBranch(branch);
			current.setP_code_bank(branch);
			//uid, uname, curip
			
			res = CustomerService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					current, 1, 2, this.alias, true);
			
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nОткрытие клиента :\n" + res.getName());
				return;
			}
			// current.setId(res.getName());
			v_msg = "Клиент добавлен в НСИ банк. ";
			String bankClientId = CustomerService.getCustomersClientID(
					res.getName(), this.branch, this.alias);
			v_msg = v_msg + "Код клиента: " + bankClientId;
			current.setId_client(bankClientId);
		} else { // edit in the bank

			// agar fizik klient bulsa va malumotlarda uzgarish bulsa 19-deystvie-korrektirovkani
			// ishlatamiz
			if (current.getCode_type().equals("08")) {
				if (current.hasBankChanges(copyOfCurrent)) {
					res = CustomerService.doAction(this.session.getAttribute("un")
							.toString(), this.session.getAttribute("pwd")
							.toString(), current, 19, 0, this.alias, true);
					if (res.getCode() != 0) {
						this.alert("ОШИБКА\nРедактирование клиента в банке:\n"
								+ res.getName());
						return;
					}
					v_msg = "Клиент отредактирован в НСИ банк. ";
				} // end if (current.hasBankChanges(copyOfCurrent))
			}// else {
			//	//yurik klient (va uning mas'ul xodimi bulsa)
			//	//
			//}
		}

		// CustomerService.insertClient() ni ishlatamiz yani bf_openway_clients
		// ga yozamiz
		if (current.getCode_type().equals("08")) 
			res = CustomerService.insertClient_oldVersion(current);
		else {
			 //current.getTieto_customer_id() bu bf_openway_clients da qator bormi yuqmi bildiradi
			if (current.getTieto_customer_id() == null || current.getTieto_customer_id().equals("")) 
				res = CustomerService.insertClient(current);
			else 
				res = CustomerService.updateClient(current);
		}
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}
        
		//curr_acc ni tayyorlashimiz kerak
		if ( (curr_acc.getId() == null || curr_acc.getId().equals("")) ) {
			Account acc = new Account();
			acc.setBranch(branch);
			acc.setSubbranch(current.getOrder_dprt());
			acc.setAcc_bal(curr_acc.getAcc_bal());
			acc.setClient(current.getId_client());
			acc.setName(current.getName());
			acc.setId_order(curr_acc.getId_order());
			//if (add_everywhere$o_product_code1.getValue().equals("DEB_UZS"))
			//	acc.setCurrency("000");
			//else
			//	acc.setCurrency("840");
			acc.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(add_everywhere$o_product_code1.getValue(), alias)).getCurrency().equals("USD") ? "840" : "000" );
			acc.setSgn("P");
			acc.setBal("B");
			acc.setSign_registr(2);
			// String un,String pw, Account account,int actionid, String alias,
			// Boolean selfBranch
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 1, this.alias);
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
				return;
			}
			acc.setId(res.getName());
			curr_acc.setId(res.getName());
			v_msg = "Счет открыть в НСИ банк. ";
			v_msg = v_msg + "Счет клиента: " + res.getName();
			// утвердить
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 2, this.alias);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка Утвердить счета :\n" + res.getName();
				this.alert(v_msg);
				return;
			}
			v_msg = v_msg + "\nСчет утвержден в НСИ банк. ";
            
			//Счет открыть в НСИ банк. Счет клиента: 22618840966456273001
			//Счет утвержден в НСИ банк.
			
		}
		//if (is_test_mode) {
		//	curr_acc.setId("22618840966456273001");
		//}
		
		// CustomerService.insertAccount() ni ishlatamiz yani
		// bf_openway_accounts? ga yozamiz
		curr_acc.setBranch(current.getBranch());
		curr_acc.setClient(current.getId_client());		
		curr_acc.setRbsNumberIbs("V_"+/*curr_acc.getBranch() +*/ curr_acc.getId());
		curr_acc.setContractName(current.getP_first_name() + " " + current.getP_family());
		curr_acc.setSocialNumber(current.getP_pinfl());
		curr_acc.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(curr_acc.getProductCode1(), alias)).getCurrency() );
		curr_acc.setOrder_dprt(current.getOrder_dprt());
		curr_acc.setPhone(current.getP_phone_mobile());
		res = CustomerService.insertAccount(curr_acc);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}

		//curr_card ni tayyorlashimiz kerak
		curr_card.setBranch(current.getBranch());
		curr_card.setClient_id(current.getId_client());
		curr_card.setMasterProductCode1(curr_acc.getProductCode1());
		curr_card.setSocialNumber(current.getP_pinfl());
		//yangi klientda contract_number bulmaydi hali
		//if (curr_acc.getContractNumber()!=null)
		//curr_card.setContractNumber(curr_acc.getContractNumber());
		curr_card.setContractName( current.getP_first_name() + " " + current.getP_family());
		curr_card.setContractName(curr_card.getContractName().trim());
		//Azamat Apexbank, [05.02.2025 12:42]
		//                  V_22618840260000001500 - rbs счет
		//                  1_C_22618840860000001500 - rbs карта

		if (curr_card.getRbsNumberIbs() == null)
			curr_card.setRbsNumberIbs(/*curr_acc.getContractNumber()
					+ "-"
					+ String.format("%07d",
							CustomerService.getSeqCardRbsNumber())*/ 
				CustomerService.getNextCardRbsNumber(current.getBranch(), current.getId_client(), curr_acc.getId(), is_test_mode)+"_C_"+ curr_acc.getId());
		
		curr_card.setLastName(current.getP_family());
		curr_card.setFirstName(current.getP_first_name());
		curr_card.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(curr_acc.getProductCode1(), alias)).getCurrency() );
		curr_card.setProductWay( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(curr_card.getProductCode1(), alias)).getName_subprod_way() );
		curr_card.setProductIbs( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(curr_card.getProductCode1(), alias)).getName_subprod_ibs() );
		curr_card.setOrder_dprt(current.getOrder_dprt());
		// CustomerService.insertCard() ni ishlatamiz yani
		// bf_openway_cards? ga yozamiz
		
		res = CustomerService.insertCard(curr_card);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}

		
		String v_xml = "_";
		if (!current.isWay_exist()) {
			// openwayda ochamiz
			// class yasaymiz, qiymatlar beramiz
			ClientAccCardRequest addClient = CustomerService.makeAddClient(current, curr_acc, curr_card,
					alias, is_test_mode);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeJsonFromObject(addClient);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize xml(addClient) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
		} else { // edit at the openway

			// class yasaymiz, qiymatlar beramiz
			//UFXMsgUpdClient addClient = CustomerService.makeUpdClient(current,
			//		alias);

			// classni xml stringga aylantiramiz
			//XmlUtils xmlUtils = new XmlUtils();
			//try {
			//	v_xml = xmlUtils.serializeJsonFromObject(addClient);
			//} catch (Exception e) {
			//	ISLogger.getLogger().error(
			//			"error serialize json(addClient) " + e.getMessage());
			//	v_msg = v_msg + "\nError serialize json " + e.getMessage();
			//}
			//if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			//	this.alert(v_msg);
			//	return;
			//}
		}

		// post zapros qilamiz
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		// String v_url = "http://213.230.121.32:8090";
		Res v_res2=null;
		//if (!is_test_mode)
		try {
			v_res2 = postUtils.sendData(url_way4_client_acc_card, v_xml);
			// System.out.println("v_res2 = " + v_res2);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"postUtils.sendData(addClient) err " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(addClient) "
					+ e.getMessage();
		}
		//else
		//{
		//	v_res2=new Res(200, "{\"message\":\"Successfully processed\",\"success\":true,\"body\":{\"client\":{\"client_category\":\"Private\"},\"account\":{\"contract_number\":\"9065-P-067746\",\"cbs_number\":\"0039422618840966456273001\",\"contract_name\":\"KARAJANOV NORMAKHAMMAD ABDIEVICH\",\"contract_category\":\"Account\"},\"card\":{\"contract_number\":\"491699______8027\",\"cbs_number\":\"null-0000052\",\"contract_category\":\"Card\",\"card_expiry\":\"3001\",\"first_name\":\"NORMAKHAMMAD\",\"last_name\":\"KARAJANOV\",\"cvc\":null}}}");
		//}
			
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
        //ISLogger.getLogger().error("response addClient " + v_res2.getCode()+", "+v_res2.getName());
		if (v_res2.getCode()!=200) {
			v_msg = v_msg
			+ "\nUnsuccess response (ClientAccCardResponse): "
			+ v_res2.getCode()+", "+v_res2.getName();
			this.alert(v_msg);
			return;
        }
		
		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		ClientAccCardResponse clResp = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			clResp = objMapper.readValue(v_res2.getName(), ClientAccCardResponse.class);
		} catch (Exception e) {
			// Block of code to handle errors
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (ClientAccCardResponse): "
							+ e.getMessage());
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (ClientAccCardResponse): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.isSuccess()) /* success */{
			v_msg = v_msg
					+ "\nКлиент добавлен/редактирован в Openway! Успешный ответ из Openway.";
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 5,
					branch, v_xml, v_res2.getName()), alias);
			// this.alert("Клиент добавлен в Openway! ");
			curr_acc.setCbsNumber(clResp.getBody().getAccount().getContractNumber());
			res = CustomerService.updateAccount(curr_acc);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				return;
			}
			
			curr_card.setCbsNumber(clResp.getBody().getCard().getContractNumber());
			res = CustomerService.updateCard(curr_card);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				return;
			}
			
		} else {
			// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
			v_msg = v_msg
					+ "\nОшибка открытия/редактирования клиента в Openway: "
					+ clResp.getMessage();
		}

		//2026.05.07 shuerga 16413 shet ochishni qushishimiz kerak. try catch qilsak yaxshi manimcha. 
		if (curr_card.getProductCode1().equals("V_PL_DEB_EPIN_USD") || curr_card.getProductCode1().equals("V_IN_DEB_EPIN_USD")) {
			Account acc = new Account();
			acc.setBranch(branch);
			acc.setSubbranch((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
			acc.setAcc_bal("16413");
			acc.setClient(current.getId_client());
			acc.setName(current.getName());
			acc.setId_order("001");
			if (add_account$o_product_code1.getValue().equals("DEB_UZS"))
				acc.setCurrency("000");
			else
				acc.setCurrency("840");
			acc.setSgn("A");
			acc.setBal("B");
			acc.setSign_registr(2);

			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 1, this.alias);
			if (res.getCode() != 0) {
				//this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
				//return;
				v_msg_16413="ОШИБКА Открытие счета 16413% :\n" + res.getName();
			}
			if (!v_msg_16413.equals("")){
				acc.setId(res.getName());
				v_msg_16413 = "Счет "+res.getName()+" успешно открыть в НСИ банк. ";
				// утвердить
				res = AccountService.doAction(this.session.getAttribute("un")
						.toString(), this.session.getAttribute("pwd").toString(),
						acc, 2, this.alias);
				if (res.getCode() != 0) {
					v_msg_16413 = v_msg_16413 + "\nОшибка Утвердить счета :\n" + res.getName();
					//this.alert(v_msg);
					//return;
				} else 
					v_msg_16413 = v_msg_16413 + "\nСчет утвержден в НСИ банк. ";
			}
			
		}
		//2026.05.07 end
		
		this.alert(v_msg+ "\n"+v_msg_16413);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			return;
		}

		this.filter.setId_client(current.getId_client());
		this.filter.setP_pinfl(current.getP_pinfl());
		txbId_client.setValue(current.getId_client());
		txbPinfl.setValue(current.getP_pinfl());

		this.refreshModel(this._startPageNumber);
		this.add_everywhere.setVisible(false);

	}

	public void onClick$close_btn$add_everywhere() {
		this.add_everywhere.setVisible(false); //2025.03.22 da komentni ochdim
		// this.fl_edit = false;
		//binder.loadComponent(add_everywhere); 2025.03.22 da koment qildim

	}

	public void onFocus$ao_category_client$add_everywhere() {
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		if (add_everywhere$acode_resident.getItems().size() == 0
				|| (add_everywhere$acode_resident.getValue() != current
						.getCode_resident())) {
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

	public void onChange$acode_resident$add_everywhere() {
		current.setCode_resident(add_everywhere$acode_resident.getValue());
	}

	public void onChange$ao_category_client$add_everywhere() {
		current.setO_client_type(add_everywhere$ao_category_client.getValue());
	}

	public void onChange$ap_type_document$add_everywhere() {
		current.setP_type_document(add_everywhere$ap_type_document.getValue());
	}

	public void onChange$acode_country$add_everywhere() {
		current.setCode_country(add_everywhere$acode_country.getValue());
	}

	public void onChange$ap_code_citizenship$add_everywhere() {
		current.setP_code_citizenship(add_everywhere$ap_code_citizenship
				.getValue());
	}

	public void onChange$ap_code_gender$add_everywhere() {
		current.setP_code_gender(add_everywhere$ap_code_gender.getValue());
	}

	public void onChange$ap_code_adr_region$add_everywhere() {
		current.setP_code_adr_region(add_everywhere$ap_code_adr_region
				.getValue());
		add_everywhere$ap_code_adr_distr.setSelectedIndex(-1);
		add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
				CustomerService.getDistrByRegion(
						add_everywhere$ap_code_adr_region.getValue(), alias)));
	}

	public void onChange$ap_code_adr_distr$add_everywhere() {
		current.setP_code_adr_distr(add_everywhere$ap_code_adr_distr.getValue());
	}

	public void onChange$ap_code_nation$add_everywhere() {
		current.setP_code_nation(add_everywhere$ap_code_nation.getValue());
	}

	public void onChange$ap_code_tax_org$add_everywhere() {
		current.setP_code_tax_org(add_everywhere$ap_code_tax_org.getValue());
	}

	public void onSelect$branch_customers() {
		if (current != null) {
            
			/* Begin acc list */
			AccountFilter filt = new AccountFilter();
			filt.setBranch(branch);
			filt.setAcc_bal("22618");
			//filt.setCurrency("840");
			filt.setClient(current.getId_client());
			filt.setId_order_not("200");

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
			ArrayList<Contract> contractAccList = new ArrayList<Contract>();

			if (clResp.isSuccess()) {
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
				inf.setSurname(accList.get(i).getName());
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
							.equals(/*accList.get(i).getBranch()*/"V_"
									+ accList.get(i).getId())) {
						inf.setProductCode1(accList2.get(j).getProductCode1());
						inf.setPhone(accList2.get(j).getPhone());
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
				curr_acc=null;
			}
			
			/* End account list */
			
			/* Begin card list*/
			List<CardInfo> carInfoList = new ArrayList<CardInfo>();

			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			/*List<CardInfo> cardList = CustomerService.getContractCardList_ABS(
					current.getBranch(), current.getId_client(),
					curr_acc.getProductCode1(), alias);*/
			List<CardInfo> cardList = CustomerService.getContractCardList_ABS(
					current.getBranch(), current.getId_client(),
					alias);

			// WAYdan
			// tepada bu metod ishlatilgani uchun comment qildim
			/*clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							openwayEndpoint, "");*/
			
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
				inf.setBranch(branch);
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
						// inf.setCbsNumber(contractCardList.get(j)
						// .getContractIDT().getCBSNumber());
						inf.setContractNumber(contractCardList.get(j).getContractNumber());
						if (contractCardList.get(j).getCardExpiry() != null)
							inf.setCardExpiry(contractCardList.get(j).getCardExpiry());
						else
							inf.setCardExpiry("-");
						inf.setSTATUS(contractCardList.get(j).getStatus().getStatusDetail() );
						inf.setSTATUS2(contractCardList.get(j).getStatus().getProductionStatus());
						inf.setSTOP_CAUSE(contractCardList.get(j).getStatus().getStatusCode());
						inf.setOrder_dprt(contractCardList.get(j).getOrderDprt());
						contractCardList.remove(j);
						break;
					}
				}

				//
				carInfoList.add(inf);
			}

			for (int j = 0; j < contractCardList.size(); j++) {
				CardInfo inf = new CardInfo();
				inf.setWay_exist(true);
				inf.setCbsNumber(contractCardList.get(j).getCbsNumber());
				inf.setContractNumber(contractCardList.get(j).getContractNumber());
				if (contractCardList.get(j).getCardExpiry() !=null) 
				     inf.setCardExpiry(contractCardList.get(j).getCardExpiry());
				else
					inf.setCardExpiry("-");
				inf.setSTATUS(contractCardList.get(j).getStatus().getStatusDetail());
				inf.setSTATUS2(contractCardList.get(j).getStatus().getProductionStatus());
				inf.setSTOP_CAUSE(contractCardList.get(j).getStatus().getStatusCode());
				inf.setOrder_dprt(contractCardList.get(j).getOrderDprt());
				carInfoList.add(inf);
			}

			// accGrid.setModel(new ListModelList(infoList));
			cardGrid.setModel(new BindingListModelList(carInfoList, true));
			//

			if (carInfoList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			} else {
				curr_card=null;
			}

			/* End card list */

		}

	}

	/*public void onSelect$accGrid() {
		if (curr_acc != null && curr_acc.getProductCode1() != null) {
			
			List<CardInfo> carInfoList = new ArrayList<CardInfo>();

			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			List<CardInfo> cardList = CustomerService.getContractCardList_ABS(
					current.getBranch(), current.getId_client(),
					curr_acc.getProductCode1(), alias);

			// WAYdan
			UFXMsgReqContractResp clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							openwayEndpoint, "");
			ArrayList<ContractRs> contractCardList = new ArrayList<ContractRs>();
			
			if (clResp.getResp_code().equals("0")) {
				ArrayList<ContractRs> contractList = clResp.getMsgDataReq()
						.getApplication().getDataRsObject().getContractRs();
				for (int i = 0; i < contractList.size(); i++) {
					if (contractList.get(i).getContract().getProduct()
							.getAddInfo().getParm().getValue().equals("Card")) {
						contractCardList.add(contractList.get(i));
					}
				}
			}

			for (int i = 0; i < cardList.size(); i++) {
				CardInfo inf = new CardInfo();
				inf.setBranch(branch);
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
					if (contractCardList.get(j).getContract().getContractIDT().getCBSNumber()
							.equals(cardList.get(i).getRbsNumberIbs())) {
						inf.setWay_exist(true);
						// inf.setCbsNumber(contractCardList.get(j)
						// .getContractIDT().getCBSNumber());
						inf.setContractNumber(contractCardList.get(j).getContract()
								.getContractIDT().getContractNumber());
						if (contractCardList.get(j).getContract().getProductionParmsObject() != null)
							inf.setCardExpiry(contractCardList.get(j).getContract()
									.getProductionParmsObject().getCardExpiry());
						else
							inf.setCardExpiry("-");
						inf.setSTATUS(contractCardList.get(j).getInfo().getStatus().getStatusDetails());
						inf.setSTATUS2(contractCardList.get(j).getInfo().getStatus().getProductionStatus());
						contractCardList.remove(j);
						break;
					}
				}

				//
				carInfoList.add(inf);
			}

			for (int j = 0; j < contractCardList.size(); j++) {
				CardInfo inf = new CardInfo();
				inf.setWay_exist(true);
				inf.setCbsNumber(contractCardList.get(j).getContract().getContractIDT()
						.getCBSNumber());
				inf.setContractNumber(contractCardList.get(j).getContract().getContractIDT()
						.getContractNumber());
				if (contractCardList.get(j).getContract()
						.getProductionParmsObject()!=null) 
				     inf.setCardExpiry(contractCardList.get(j).getContract()
						.getProductionParmsObject().getCardExpiry());
				else
					inf.setCardExpiry("-");
				inf.setSTATUS(contractCardList.get(j).getInfo().getStatus().getStatusDetails());
				inf.setSTATUS2(contractCardList.get(j).getInfo().getStatus().getProductionStatus());
				carInfoList.add(inf);
			}

			// accGrid.setModel(new ListModelList(infoList));
			cardGrid.setModel(new BindingListModelList(carInfoList, true));
			//

			if (carInfoList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			}

		}
	}*/

	public void refresh_cardlist() {
		if (current != null) {
			
			/* Begin card list*/
			List<CardInfo> carInfoList = new ArrayList<CardInfo>();

			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			/*List<CardInfo> cardList = CustomerService.getContractCardList_ABS(
					current.getBranch(), current.getId_client(),
					curr_acc.getProductCode1(), alias);*/
			List<CardInfo> cardList = CustomerService.getContractCardList_ABS(
					current.getBranch(), current.getId_client(),
					alias);

			// WAYdan
			ClientContractsResponse clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							url_way4_clientcontracts, "");
			
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
				inf.setBranch(branch);
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
						// inf.setCbsNumber(contractCardList.get(j)
						// .getContractIDT().getCBSNumber());
						inf.setContractNumber(contractCardList.get(j).getContractNumber());
						if (contractCardList.get(j).getCardExpiry() != null)
							inf.setCardExpiry(contractCardList.get(j).getCardExpiry());
						else
							inf.setCardExpiry("-");
						inf.setSTATUS(contractCardList.get(j).getStatus().getStatusDetail() );
						inf.setSTATUS2(contractCardList.get(j).getStatus().getProductionStatus());
						inf.setSTOP_CAUSE(contractCardList.get(j).getStatus().getStatusCode());
						inf.setOrder_dprt(contractCardList.get(j).getOrderDprt());
						
						contractCardList.remove(j);
						break;
					}
				}

				//
				carInfoList.add(inf);
			}

			for (int j = 0; j < contractCardList.size(); j++) {
				CardInfo inf = new CardInfo();
				inf.setWay_exist(true);
				inf.setCbsNumber(contractCardList.get(j).getCbsNumber());
				inf.setContractNumber(contractCardList.get(j).getContractNumber());
				if (contractCardList.get(j).getCardExpiry() !=null) 
				     inf.setCardExpiry(contractCardList.get(j).getCardExpiry());
				else
					inf.setCardExpiry("-");
				inf.setSTATUS(contractCardList.get(j).getStatus().getStatusDetail());
				inf.setSTATUS2(contractCardList.get(j).getStatus().getProductionStatus());
				inf.setSTOP_CAUSE(contractCardList.get(j).getStatus().getStatusCode());
				inf.setOrder_dprt(contractCardList.get(j).getOrderDprt());
				carInfoList.add(inf);
			}

			// accGrid.setModel(new ListModelList(infoList));
			cardGrid.setModel(new BindingListModelList(carInfoList, true));
			//

			if (carInfoList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			} else {
				curr_card=null;
			}
			/* End card list */
		}
	}
	
	public void refresh_acclist() {
		if (current != null) {
			
			/* Begin acc list */
			AccountFilter filt = new AccountFilter();
			filt.setBranch(branch);
			filt.setAcc_bal("22618");
			//filt.setCurrency("840");
			filt.setClient(current.getId_client());
			filt.setId_order_not("200");

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
			ArrayList<Contract> contractAccList = new ArrayList<Contract>();

			if (clResp.isSuccess()) {
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
				inf.setSurname(accList.get(i).getName());
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
							.equals(/*accList.get(i).getBranch()*/"V_"
									+ accList.get(i).getId())) {
						inf.setProductCode1(accList2.get(j).getProductCode1());
						inf.setPhone(accList2.get(j).getPhone());
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
				curr_acc=null;
			}
			
			/* End account list */
		}
	}
	
	private void lockCardMethod(CardInfo card1) {
		lock_card_wnd.setTitle("Блокировка карты");
		lock_card_wnd$row_stop_cause.setVisible(true);
		lock_card_wnd$lock_card_btn.setVisible(true);
		lock_card_wnd$unlock_card_btn.setVisible(false);
		lock_card_wnd.setAttribute("card", card1);
		if (lock_card_wnd$rcb_stop_cause.getItems().size() == 0)
			lock_card_wnd$rcb_stop_cause.setModel(new ListModelList(
					//CustomerService.getListStopCauses(alias)));
					CustomerService.getListLockStopCauses(alias)));
		if (lock_card_wnd$rcb_stop_cause.getValue() == null
				|| lock_card_wnd$rcb_stop_cause.getValue().equals("")) {
			lock_card_wnd$txt_comment_text.setValue("");
		}
		lock_card_wnd.setVisible(true);	
		binder.loadComponent(lock_card_wnd);
	}

	private void unlockCardMethod(CardInfo card1) {

		String v_xml="";
		String v_msg="";
		
		//apeks bank uchun bitta tekshirish kerak ekan:
		//генерируем Запрос информации по карте - Метод 5.9 на проверку статуса карточного контракта в ПЦ.
		/*
		 *         "status": {
            "status": "Decline",
            "status_code": "05",		(Код в ответе - только с кодом 05 должна быть возможность разблокировки).
            "status_detail": "Card OK",
            "production_status": "Ready",
            "active": true
         }
		 * 
		 * */
		

		//5.9 ga zapros beramiz va cartani status_code sini olamiz.
		CardInfoRequest cardInfoReq = CustomerService.makeCardInfo(card1, alias);
		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeJsonFromObject(cardInfoReq);
			ISLogger.getLogger().error(
					"xmlUtils.serializeJsonFromObject(cardInfoReq) v_xml: "
							+ v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize json(cardInfoReq) " + e.getMessage());
			v_msg = v_msg + "\nError serialize json(cardInfoReq) "
					+ e.getMessage();
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
			v_res2 = postUtils.sendData(url_way4_cardinfo, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger()
					.error("postUtils.sendData err(cardInfoReq) "
							+ e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		ISLogger.getLogger().error(
				"v_res2= "	+ v_res2.getName());
		
		if (v_res2.getCode()!=200) {
			v_msg = v_msg
			+ "\nUnsuccess response (cardInfoReq): "
			+ v_res2.getCode()+", "+v_res2.getName();
			this.alert(v_msg);
			return;
		}

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		CardInfoResponse clResp = null;
		ObjectMapper xmlMapper = new ObjectMapper();
		try {
			clResp = xmlMapper
					.readValue(v_res2.getName(), CardInfoResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (CardInfoResponse): "
							+ e.getMessage());
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (CardInfoResponse): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.isSuccess() ) {
			//v_msg = v_msg + "\nКарта блокирован в Openway! ";
			if (!clResp.getBody().getStatus().getStatusCode().equals("05")) {
				v_msg = v_msg
				+ "\nОшибка. Карта не может быть разблокирован. Статус блокировки: "
				+ clResp.getBody().getStatus().getStatusCode()+". ("+clResp.getBody().getStatus().getStatusDetail()+"-"+clResp.getBody().getStatus().getProductionStatus()+")" ;
			}
		} else {
			v_msg = v_msg
					+ "\nОшибка при запросе об информации по карту в Openway: "
					+ clResp.getMessage() ;
		}
		
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		// end proverka
		lock_card_wnd.setTitle("Разблокировать карту");
		lock_card_wnd$row_stop_cause.setVisible(false);
		lock_card_wnd$lock_card_btn.setVisible(false);
		lock_card_wnd$unlock_card_btn.setVisible(true);
		lock_card_wnd$txt_comment_text.setValue("");
		lock_card_wnd.setAttribute("card", card1);
		lock_card_wnd.setVisible(true);	
		binder.loadComponent(lock_card_wnd);
	}

	private void smsOnMethod_card(CardInfo card1) {
		update_sms_wnd_card.setTitle("Включение услугу SMS информирования");
		//lock_card_wnd$row_stop_cause.setVisible(true);
		update_sms_wnd_card$sms_on_btn.setVisible(true);
		update_sms_wnd_card$sms_off_btn.setVisible(false);
		update_sms_wnd_card.setAttribute("cardInfo", card1);
		//update_sms_wnd.setAttribute("acc_order_dprt", "V_"+tmp);
		String tmp = card1.getRbsNumberIbs().substring(card1.getRbsNumberIbs().length()-20,card1.getRbsNumberIbs().length()); //tmp="22618840466456259002"; 
		update_sms_wnd_card.setAttribute("acc_rbs_number", "V_"+tmp);
		update_sms_wnd_card$lbl_acc_rbs_number.setValue("тел.номер для СМС информирования");
		//update_sms_wnd$lbl_card_rbs_number.setValue("тел.номер для СМС информирования. по карте ("+card1.getRbsNumberIbs()+"): *");
		//update_sms_wnd_card$txt_acc_phone.setValue( card1.getPhone()!=null ? card1.getPhone() : current.getP_phone_mobile());
		update_sms_wnd_card$txt_acc_phone.setValue(null);
		
		update_sms_wnd_card.setVisible(true);	
		binder.loadComponent(update_sms_wnd_card);
	}

	public void onClick$sms_on_btn$update_sms_wnd_card() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_xml = "";
		
		CardInfo card1=(CardInfo)update_sms_wnd_card.getAttribute("cardInfo");
        
		if (card1 == null) {
			alert("Не удалось выполнить запрос. Повторите снова");
			return;
		}
		//acc_rbsnumber ni olamiz. va acc_phone ni olamiz.
		String acc_rbs_number = (String)update_sms_wnd_card.getAttribute("acc_rbs_number");
		//String acc_order_dprt = (String)update_sms_wnd.getAttribute("acc_order_dprt");

		err=check_user(branch, Integer.toString(uid), alias);
		if (!err.contains("Ok.")) {
			alert(err);
			return;
		}
        err = "";

		//if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
		//	return;
		//}
		//refresh_cardlist();
		//this.lock_card_wnd.setVisible(false);
		
		try {

			if (CheckNull.isEmpty(update_sms_wnd_card$txt_acc_phone.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nВведите номер телефона для счетовой контракт!";
			}
			if (update_sms_wnd_card$txt_acc_phone.getValue() == null
					|| update_sms_wnd_card$txt_acc_phone.getValue().toString().length() != 12
					|| !update_sms_wnd_card$txt_acc_phone.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона для счетовой контракт: " + update_sms_wnd_card$txt_acc_phone.getValue());
				return;
			}

			/*if (CheckNull.isEmpty(update_sms_wnd$txt_card_phone.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nВведите номер телефона для карты!";
			}
			if (update_sms_wnd$txt_card_phone.getValue() == null
					|| update_sms_wnd$txt_card_phone.getValue().toString().length() != 12
					|| !update_sms_wnd$txt_card_phone.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона для карты: " + update_sms_wnd$txt_card_phone.getValue());
				return;
			}
			card1.setPhone(update_sms_wnd$txt_card_phone.getValue());*/

			// wayga zapros qilamiz
			// uspeshno bulsa tablisaga yozamiz:klientid, kontraktraqam,
			// smsraqam
			// arxiv tablsaga ham yozamiz. specialacc va specialacc_history ga
			// uxshatamiz

			Res res = null;

			//accInfo.setClientNumber(current.getBranch()
			//		+ current.getId_client());
			//accInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UpdateSMSRequest addSms = CustomerService.makeAddSMS_card_acc(card1, acc_rbs_number, update_sms_wnd_card$txt_acc_phone.getValue(), getDepartmentByUserID(Integer.toString(uid), alias), alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeJsonFromObject(addSms);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize json(addSms) " + e.getMessage());
				v_msg = v_msg + "\nError serialize json " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2=null;
			try {
				v_res2 = postUtils.sendData(url_way4_updatesms, v_xml);
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

	        ISLogger.getLogger().error("response addSms " + v_res2.getCode()+", "+v_res2.getName());
			if (v_res2.getCode()!=200) {
				v_msg = v_msg
				+ "\nUnsuccess response (addSMS): "
				+ v_res2.getCode()+", "+v_res2.getName();
				this.alert(v_msg);
				return;
	        }
			
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			ClientInfoResponse clResp = null;
			ObjectMapper xmlMapper = new ObjectMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						ClientInfoResponse.class);
			} catch (Exception e) {
				// Block of code to handle errors
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (addSMS): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (addSMS): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.isSuccess()) /* success */{
				v_msg = v_msg
						+ "\nSMS подключен в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						6, branch, v_xml, v_res2.getName()), alias);

				// curr_card.setCbsNumber(clResp.getMsgDataReq().getApplication()
				// .getDataRsObject().getContractRs().get(0).getContract()
				// .getContractIDT().getContractNumber());
				res = CustomerService
						.activateSmsService_card_acc(card1, update_sms_wnd_card$txt_acc_phone.getValue(), acc_rbs_number, update_sms_wnd_card$txt_acc_phone.getValue());
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}

			} else {
				// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
				v_msg = v_msg + "\nОшибка подкючения СМС в Openway: "
						+ clResp.getMessage();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}
			
			refresh_cardlist();
			this.update_sms_wnd_card.setVisible(false);


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
		
    }

	private void smsOffMethod_card(CardInfo card1) {
		update_sms_wnd_card.setTitle("Отключение услугу SMS информирования");
		//lock_card_wnd$row_stop_cause.setVisible(true);
		update_sms_wnd_card$sms_on_btn.setVisible(false);
		update_sms_wnd_card$sms_off_btn.setVisible(true);
		update_sms_wnd_card.setAttribute("cardInfo", card1);
		//update_sms_wnd.setAttribute("acc_order_dprt", "V_"+tmp);
		String tmp = card1.getRbsNumberIbs().substring(card1.getRbsNumberIbs().length()-20,card1.getRbsNumberIbs().length()); //tmp="22618840466456259002"; 
		update_sms_wnd_card.setAttribute("acc_rbs_number", "V_"+tmp);
		update_sms_wnd_card$lbl_acc_rbs_number.setValue("тел.номер для СМС информирования");
		//update_sms_wnd$lbl_card_rbs_number.setValue("тел.номер для СМС информирования. по карте ("+card1.getRbsNumberIbs()+"): *");
		//update_sms_wnd_card$txt_acc_phone.setValue( card1.getPhone()!=null ? card1.getPhone() : current.getP_phone_mobile());
		update_sms_wnd_card$txt_acc_phone.setValue(null);
		
		update_sms_wnd_card.setVisible(true);	
		binder.loadComponent(update_sms_wnd_card);
	}

	public void onClick$sms_off_btn$update_sms_wnd_card() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_xml = "";
		
		CardInfo card1=(CardInfo)update_sms_wnd_card.getAttribute("cardInfo");
        
		if (card1 == null) {
			alert("Не удалось выполнить запрос. Повторите снова");
			return;
		}
		//acc_rbsnumber ni olamiz. va acc_phone ni olamiz.
		String acc_rbs_number = (String)update_sms_wnd_card.getAttribute("acc_rbs_number");
		//String acc_order_dprt = (String)update_sms_wnd.getAttribute("acc_order_dprt");

		err=check_user(branch, Integer.toString(uid), alias);
		if (!err.contains("Ok.")) {
			alert(err);
			return;
		}
        err = "";

		//if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
		//	return;
		//}
		//refresh_cardlist();
		//this.lock_card_wnd.setVisible(false);
		
		try {

			//if (CheckNull.isEmpty(update_sms_wnd_card$txt_acc_phone.getValue())) {
			//	fl_err = true;
			//	err = String.valueOf(err) + "\nВведите номер телефона для счетовой контракт!";
			//}
			//if (update_sms_wnd_card$txt_acc_phone.getValue() == null
			//		|| update_sms_wnd_card$txt_acc_phone.getValue().toString().length() != 12
			//		|| !update_sms_wnd_card$txt_acc_phone.getValue().toString().matches("[0-9]+")) {
			//	this.alert("Неверный номер телефона для счетовой контракт: " + update_sms_wnd_card$txt_acc_phone.getValue());
			//	return;
			//}

			/*if (CheckNull.isEmpty(update_sms_wnd$txt_card_phone.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nВведите номер телефона для карты!";
			}
			if (update_sms_wnd$txt_card_phone.getValue() == null
					|| update_sms_wnd$txt_card_phone.getValue().toString().length() != 12
					|| !update_sms_wnd$txt_card_phone.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона для карты: " + update_sms_wnd$txt_card_phone.getValue());
				return;
			}
			card1.setPhone(update_sms_wnd$txt_card_phone.getValue());*/

			// wayga zapros qilamiz
			// uspeshno bulsa tablisaga yozamiz:klientid, kontraktraqam,
			// smsraqam
			// arxiv tablsaga ham yozamiz. specialacc va specialacc_history ga
			// uxshatamiz

			Res res = null;

			//accInfo.setClientNumber(current.getBranch()
			//		+ current.getId_client());
			//accInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UpdateSMSRequest addSms = CustomerService.makeAddSMS_card_acc(card1, acc_rbs_number, "", getDepartmentByUserID(Integer.toString(uid), alias), alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeJsonFromObject(addSms);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize json(addSms) " + e.getMessage());
				v_msg = v_msg + "\nError serialize json " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2=null;
			try {
				v_res2 = postUtils.sendData(url_way4_updatesms, v_xml);
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

	        ISLogger.getLogger().error("response addSms " + v_res2.getCode()+", "+v_res2.getName());
			if (v_res2.getCode()!=200) {
				v_msg = v_msg
				+ "\nUnsuccess response (addSMS): "
				+ v_res2.getCode()+", "+v_res2.getName();
				this.alert(v_msg);
				return;
	        }
			
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			ClientInfoResponse clResp = null;
			ObjectMapper xmlMapper = new ObjectMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						ClientInfoResponse.class);
			} catch (Exception e) {
				// Block of code to handle errors
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (addSMS): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (addSMS): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.isSuccess()) /* success */{
				v_msg = v_msg
						+ "\nSMS отключен в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						6, branch, v_xml, v_res2.getName()), alias);

				// curr_card.setCbsNumber(clResp.getMsgDataReq().getApplication()
				// .getDataRsObject().getContractRs().get(0).getContract()
				// .getContractIDT().getContractNumber());
				res = CustomerService
						.activateSmsService_card_acc(card1, "", acc_rbs_number, "");
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}

			} else {
				// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
				v_msg = v_msg + "\nОшибка отключения СМС в Openway: "
						+ clResp.getMessage();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}
			
			refresh_cardlist();
			this.update_sms_wnd_card.setVisible(false);


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
		
    }
	
	private void smsOnMethod_acc(AccInfo card1) {
		update_sms_wnd_acc.setTitle("Включение услугу SMS информирования");
		//lock_card_wnd$row_stop_cause.setVisible(true);
		update_sms_wnd_acc$sms_on_btn.setVisible(true);
		update_sms_wnd_acc$sms_off_btn.setVisible(false);
		update_sms_wnd_acc.setAttribute("accInfo", card1);
		//update_sms_wnd.setAttribute("acc_order_dprt", "V_"+tmp);
		//String tmp = card1.getRbsNumberIbs().substring(card1.getRbsNumberIbs().length()-20,card1.getRbsNumberIbs().length()); //tmp="22618840466456259002"; 
		//update_sms_wnd.setAttribute("acc_rbs_number", "V_"+tmp);
		update_sms_wnd_acc$lbl_acc_rbs_number.setValue("тел.номер для СМС информирования");
		//update_sms_wnd$lbl_card_rbs_number.setValue("тел.номер для СМС информирования. по карте ("+card1.getRbsNumberIbs()+"): *");
		update_sms_wnd_acc$txt_acc_phone.setValue( card1.getPhone()!=null ? card1.getPhone() : current.getP_phone_mobile());
		
		update_sms_wnd_acc.setVisible(true);	
		binder.loadComponent(update_sms_wnd_acc);
	}
	
	public void onClick$sms_on_btn$update_sms_wnd_acc() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_xml = "";
		
		AccInfo card1=(AccInfo)update_sms_wnd_acc.getAttribute("accInfo");
        
		if (card1 == null) {
			alert("Не удалось выполнить запрос. Повторите снова");
			return;
		}
		//acc_rbsnumber ni olamiz. va acc_phone ni olamiz.
		String acc_rbs_number = (String)update_sms_wnd_acc.getAttribute("acc_rbs_number");
		//String acc_order_dprt = (String)update_sms_wnd.getAttribute("acc_order_dprt");

		err=check_user(branch, Integer.toString(uid), alias);
		if (!err.contains("Ok.")) {
			alert(err);
			return;
		}
        err = "";

		//if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
		//	return;
		//}
		//refresh_cardlist();
		//this.lock_card_wnd.setVisible(false);
		
		
		try {


			if (CheckNull.isEmpty(update_sms_wnd_acc$txt_acc_phone.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nВведите номер телефона для счетовой контракт!";
			}
			if (update_sms_wnd_acc$txt_acc_phone.getValue() == null
					|| update_sms_wnd_acc$txt_acc_phone.getValue().toString().length() != 12
					|| !update_sms_wnd_acc$txt_acc_phone.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона для счетовой контракт: " + update_sms_wnd_acc$txt_acc_phone.getValue());
				return;
			}

			/*if (CheckNull.isEmpty(update_sms_wnd$txt_card_phone.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nВведите номер телефона для карты!";
			}
			if (update_sms_wnd$txt_card_phone.getValue() == null
					|| update_sms_wnd$txt_card_phone.getValue().toString().length() != 12
					|| !update_sms_wnd$txt_card_phone.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона для карты: " + update_sms_wnd$txt_card_phone.getValue());
				return;
			}
			card1.setPhone(update_sms_wnd$txt_card_phone.getValue());*/

			// wayga zapros qilamiz
			// uspeshno bulsa tablisaga yozamiz:klientid, kontraktraqam,
			// smsraqam
			// arxiv tablsaga ham yozamiz. specialacc va specialacc_history ga
			// uxshatamiz

			
			Res res = null;

			//accInfo.setClientNumber(current.getBranch()
			//		+ current.getId_client());
			//accInfo.setSocialNumber(current.getP_pinfl());
			// class yasaymiz, qiymatlar beramiz
			UpdateSMSRequest addSms = CustomerService.makeAddSMS_acc(card1, update_sms_wnd_acc$txt_acc_phone.getValue(), getDepartmentByUserID(Integer.toString(uid), alias), alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeJsonFromObject(addSms);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize json(addSms) " + e.getMessage());
				v_msg = v_msg + "\nError serialize json " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			Res v_res2=null;
			try {
				v_res2 = postUtils.sendData(url_way4_updatesms, v_xml);
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

	        ISLogger.getLogger().error("response addSms " + v_res2.getCode()+", "+v_res2.getName());
			if (v_res2.getCode()!=200) {
				v_msg = v_msg
				+ "\nUnsuccess response (addSMS): "
				+ v_res2.getCode()+", "+v_res2.getName();
				this.alert(v_msg);
				return;
	        }
			
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			ClientInfoResponse clResp = null;
			ObjectMapper xmlMapper = new ObjectMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						ClientInfoResponse.class);
			} catch (Exception e) {
				// Block of code to handle errors
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (addSMS): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (addSMS): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (clResp.isSuccess()) /* success */{
				v_msg = v_msg
						+ "\nSMS подключен в Openway! Успешный ответ из Openway.";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						6, branch, v_xml, v_res2.getName()), alias);

				// curr_card.setCbsNumber(clResp.getMsgDataReq().getApplication()
				// .getDataRsObject().getContractRs().get(0).getContract()
				// .getContractIDT().getContractNumber());
				res = CustomerService
						.activateSmsService_acc(card1, update_sms_wnd_acc$txt_acc_phone.getValue());
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}

			} else {
				// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
				v_msg = v_msg + "\nОшибка подкючения СМС в Openway: "
						+ clResp.getMessage();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}
			
			refresh_acclist();
			this.update_sms_wnd_acc.setVisible(false);


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
		
    }
	
	
	private void reIssueCardMethod(final Event event) {
		try {
		
			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("card");
			if (current == null) {
				alert("Клиент не выбран");
				return;
			}
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
			if ( (cardInfo.getSTOP_CAUSE()!=null) && ( !cardInfo.getSTOP_CAUSE().equals("014") && !cardInfo.getSTOP_CAUSE().equals("41") && !cardInfo.getSTOP_CAUSE().equals("43") ) ) {
				alert("Перевыпуск не разрешено для текущей статус карты! Статус карты: "+cardInfo.getSTOP_CAUSE()+" "+cardInfo.getSTATUS()+" "+cardInfo.getSTATUS2());
				return;
			}
			//инициализация order_dprt
			String v_msg=check_user(branch, Integer.toString(uid), alias);
			if (!v_msg.contains("Ok.")) {
				alert(v_msg);
				return;
			}
			
			String v_xml = "_";
			v_msg = "";
			Res res = null;

			CardInfo new_card = new CardInfo();
			new_card.setBranch(current.getBranch());
			new_card.setClient_id(current.getId_client());
			
			new_card.setProductCode1(cardInfo.getProductCode1());
			new_card.setMasterProductCode1(cardInfo.getMasterProductCode1());
			new_card.setCommentText("Perevipusk karti");
			
			new_card.setOrder_dprt((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
			new_card.setSocialNumber(current.getP_pinfl());
			//new_card.setContractNumber(curr_acc.getContractNumber());
			new_card.setContractName(current.getP_first_name() + " " + current.getP_family() );
			new_card.setContractName(new_card.getContractName().trim());
			if (new_card.getRbsNumberIbs() == null)
			{
				//cardInfo.getRbsNumberIbs() odatda="1_C_22618840466456259002", "2_C_22618840466456259002" va h.k. manimcha "10_C_22618840466456259002", "11_C_22618840466456259002" bulishi ham mumkin
				if (cardInfo.getRbsNumberIbs()==null || cardInfo.getRbsNumberIbs().equals("") || cardInfo.getRbsNumberIbs().length()<24)
				{
					alert("Пустой или неправильное значение поле RBS_NUMBER сушествуюшего карточного контракта.");
					return;
				}
			}	
			String tmp = cardInfo.getRbsNumberIbs();
			tmp = tmp.substring(tmp.length()-20,tmp.length()); //tmp="22618840466456259002"; 
			new_card.setRbsNumberIbs(CustomerService.getNextCardRbsNumber(current.getBranch(), current.getId_client(), tmp, is_test_mode)+"_C_"+ tmp);
			new_card.setPhone(current.getP_phone_mobile());			
			new_card.setLastName(current.getP_family());
			new_card.setFirstName(current.getP_first_name());
			CustomerService.getProduct_code1_way4(alias); //инициализация киламиз. справочник баъзида буш булиши мумкин экан.
			CustomerService.getSubProduct_code1_way4(alias); // инициализация киламиз. справочник баъзида буш булиши мумкин экан.
			new_card.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(cardInfo.getMasterProductCode1(), alias)).getCurrency() );
			new_card.setProductWay( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(new_card.getProductCode1(), alias)).getName_subprod_way() );
			new_card.setProductIbs( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(new_card.getProductCode1(), alias)).getName_subprod_ibs() );
			
			// CustomerService.insertCard() ni ishlatamiz yani
			// bf_openway_cards? ga yozamiz
			
			res = CustomerService.insertCard(new_card);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				return;
			}

			// openwayda kartochniy kontrakt ochamiz
			// class yasaymiz, qiymatlar beramiz
			RegCardRequest addContract = CustomerService
					.makeAddContractCard(new_card, /*curr_acc*/"V_"+tmp, alias);

			
			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeJsonFromObject(addContract);
				ISLogger.getLogger().error(
						"xmlUtils.serializeJsonFromObject(addContractCard reissue) v_xml: "
								+ v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize json(addContractCard reissue) " + e.getMessage());
				v_msg = v_msg + "\nError serialize json(addContractCard) "
						+ e.getMessage();
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
				v_res2 = postUtils.sendData(url_way4_reg_card, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger()
						.error("postUtils.sendData err(addCardContract reissue) "
								+ e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			ISLogger.getLogger().error(
					"v_res2= "	+ v_res2.getName());

			if (v_res2.getCode()!=200) {
				v_msg = v_msg
				+ "\nUnsuccess response (addCardContract reissue): "
				+ v_res2.getCode()+", "+v_res2.getName();
				this.alert(v_msg);
				return;
	        }
			
	
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			AccCardResponse clResp = null;
			ObjectMapper xmlMapper = new ObjectMapper();
			try {
				clResp = xmlMapper
						.readValue(v_res2.getName(), AccCardResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"xmlMapper.readValue err (AccCardResponse reissue): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (AccCardResponse reissue): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}


			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
			// qilamiz
			if (clResp.isSuccess() ) {
				v_msg = v_msg + "\nКонтракт добавлен/редактирован в Openway! (Карта перевыпушена!) ";
				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 8,
						branch, v_xml, v_res2.getName()), alias);
				// <DataRs>.<ContractRs>.<Contract>.<ContractIDT>.<ContractNumber>430142______3543</ContractNumber>
				// buni ABS tablisada update qilamiz
				new_card.setCbsNumber(clResp.getBody().getCard().getCbsNumber());
				if (clResp.getBody().getCard().getCbsNumber().length()!=16 && clResp.getBody().getCard().getContractNumber()!=null && clResp.getBody().getCard().getContractNumber().length()==16) //2025.12.16
					new_card.setCbsNumber(clResp.getBody().getCard().getContractNumber()); //2025.12.16
				res = CustomerService.updateCard(new_card);
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					return;
				}
			} else {
				v_msg = v_msg
						+ "\nОшибка открытия/редактирования/перевыпуска контракта в Openway: "
						+ clResp.getMessage() ;
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}

			refresh_cardlist();


		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}

	
	private void resetPINMethod(CardInfo card1) {
		
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		
		if (card1 == null || card1.getRbsNumberIbs() == null) {
			alert("Карта не выбрана!" );
			return;
		}
		
		
		ClearPinRequest clearPin = CustomerService.makeClearPinRequest(card1, alias); 

		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(clearPin);
		} catch (Exception e) {
			System.out.println("error serialize json from ClearPinRequest: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from ClearPinRequest: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			
			return;
		}
		//ISLogger.getLogger().error("ClearPinRequest query json text: " + v_json);
		//System.out.println("ClearPinRequest query json text: " + v_json);

		Res v_res = null;
		try {
			v_res = postUtils.sendData(
					url_way4_clear_pin, v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"ClearPinRequest postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(ClearPinRequest) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("ResetPINCounter response = " + v_res.getName());
		//System.out.println("ResetPINCounter response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
        //ISLogger.getLogger().error("response addClient " + v_res2.getCode()+", "+v_res2.getName());
		if (v_res.getCode()!=200) {
			v_msg = v_msg
			+ "\nUnsuccess response (ClearPinRequest): "
			+ v_res.getCode()+", "+v_res.getName();
			this.alert(v_msg);
			return;
        }
		
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		ClearPinResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res.getName(), ClearPinResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"ClearPinRequest response mapper err: " + e.getMessage());
			System.out.println("ClearPinRequest response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError ClearPinRequest response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			return;
		}
		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.isSuccess()) /* success */{
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 6,
					branch, v_json, v_res.getName()), alias);
			v_msg = v_msg + "\nСброшена ПИН счетчик карты успешно! ";

			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg
					+ "\nОшибка сброса ПИН счетчик карты: "
					+ rslt.getMessage();
			this.alert(v_msg);
			return;
		}
		this.alert(v_msg);
		//onSelect$accGrid();
	}
	
	public void onClick$btn_add_acc() {
		if (current == null || current.getId_client() == null) {
			alert("Клиент не выбран");
			return;
		}
		curr_acc = new AccInfo();
		curr_acc.setBranch(branch);
		curr_acc.setClient(current.getId_client());
		curr_acc.setPhone(current.getP_phone_mobile());

		curr_card = new CardInfo();

		CheckNull.clearForm(this.add_account$addgrdl);
		this.add_everywhere.setTitle("Открытие счета и счетового контракта и карту [БАНК] - [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefAccData();
		//

		this.add_account.setVisible(true);
		binder.loadComponent(add_account);

	}

	public void onClick$add_acc_btn$add_account()
			throws JsonProcessingException {

		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_msg_16413 = "";

		err=check_user(branch, Integer.toString(uid), alias);
		if (!err.contains("Ok.")) {
			alert(err);
			return;
		}
		
		err="";
		
		if (add_account$acc_bal.getValue() == null
				|| add_account$acc_bal.getValue().equals("")) {
			fl_err = true;
			err = String.valueOf(err) + "\nНе заполнено Код балансовый счет";
		}
		if (CheckNull.isEmpty(add_account$id_order.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПорядковый номер";
		}

		if (CheckNull.isEmpty(add_account$o_product_code1.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод счетевого продукта";
		}

		if (CheckNull.isEmpty(add_account$o_card_product_code1.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод карточного продукта";
		}

		if (CheckNull.isEmpty(add_account$o_postal_code.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер телефона";
		}
		
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		Res res = null;
		if (curr_acc.getId() == null || curr_acc.getId().equals("")) {
			Account acc = new Account();
			acc.setBranch(branch);
			acc.setSubbranch((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
			acc.setAcc_bal(curr_acc.getAcc_bal());
			acc.setClient(current.getId_client());
			acc.setName(current.getName());
			acc.setId_order(curr_acc.getId_order());
			if (add_account$o_product_code1.getValue().equals("DEB_UZS"))
				acc.setCurrency("000");
			else
				acc.setCurrency("840");
			acc.setSgn("P");
			acc.setBal("B");
			acc.setSign_registr(2);
			// String un,String pw, Account account,int actionid, String alias,
			// Boolean selfBranch
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 1, this.alias);
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
				return;
			}
			acc.setId(res.getName());
			curr_acc.setId(res.getName());
			v_msg = "Счет открыть в НСИ банк. ";
			v_msg = v_msg + "Счет клиента: " + res.getName();
			// утвердить
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 2, this.alias);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка Утвердить счета :\n" + res.getName();
				this.alert(v_msg);
				return;
			}

			v_msg = v_msg + "\nСчет утвержден в НСИ банк. ";

		}

		// CustomerService.insertAccount() ni ishlatamiz yani
		// bf_openway_accounts? ga yozamiz
		curr_acc.setOrder_dprt((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
		//curr_acc.setPhone(current.getP_phone_mobile());
		curr_acc.setRbsNumberIbs(/*curr_acc.getBranch()*/"V_" + curr_acc.getId());
		curr_acc.setContractName( current.getP_first_name() + " " + current.getP_family());
		curr_acc.setSocialNumber(current.getP_pinfl());
		curr_acc.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(curr_acc.getProductCode1(), alias)).getCurrency() );
		res = CustomerService.insertAccount(curr_acc);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}
		
		//curr_card ni tayyorlashimiz kerak
		curr_card.setBranch(current.getBranch());
		curr_card.setClient_id(current.getId_client());
		curr_card.setMasterProductCode1(curr_acc.getProductCode1());
		curr_card.setSocialNumber(current.getP_pinfl());
		
		
		//if (curr_acc.getContractNumber()!=null)
		//curr_card.setContractNumber(curr_acc.getContractNumber());
		curr_card.setContractName( current.getP_first_name() + " " + current.getP_family());
		curr_card.setContractName(curr_card.getContractName().trim());

		if (curr_card.getRbsNumberIbs() == null)
			curr_card.setRbsNumberIbs(
				CustomerService.getNextCardRbsNumber(current.getBranch(), current.getId_client(), curr_acc.getId(), is_test_mode)+"_C_"+ curr_acc.getId());
		curr_card.setPhone(curr_acc.getPhone()!=null ? curr_acc.getPhone() : current.getP_phone_mobile());
		curr_card.setLastName(current.getP_family());
		curr_card.setFirstName(current.getP_first_name());
		curr_card.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(curr_acc.getProductCode1(), alias)).getCurrency() );
		curr_card.setProductWay( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(curr_card.getProductCode1(), alias)).getName_subprod_way() );
		curr_card.setProductIbs( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(curr_card.getProductCode1(), alias)).getName_subprod_ibs() );
		curr_card.setOrder_dprt(curr_acc.getOrder_dprt());
		// CustomerService.insertCard() ni ishlatamiz yani
		// bf_openway_cards? ga yozamiz
		
		res = CustomerService.insertCard(curr_card);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}

		String v_xml = "_";
		if (!curr_acc.isWay_exist()) {
			// openwayda shetevoy kontrakt ochamiz
			// class yasaymiz, qiymatlar beramiz
			
			//5.2 metod
			AccCardRequest addContract = CustomerService.makeAddContract(
					curr_acc, curr_card, alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeJsonFromObject(addContract);
				//ISLogger.getLogger().error(
				//		"xmlUtils.serializeJsonFromObject(addContract) v_xml: "
				//				+ v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize json(addContract) " + e.getMessage());
				v_msg = v_msg + "\nError serialize json(addContract) "
						+ e.getMessage();
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
				v_res2 = postUtils.sendData(url_way4_acc_card, v_xml );
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"postUtils.sendData(addAccContract) err "
								+ e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			if (v_res2.getCode()!=200) {
				v_msg = v_msg
				+ "\nUnsuccess response (addAccContract): "
				+ v_res2.getCode()+", "+v_res2.getName();
				this.alert(v_msg);
				return;
	        }
			ISLogger.getLogger().error(
					"addAccContract response: "
							+ v_res2.getName());
			
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			
			AccCardResponse clResp = null;
			ObjectMapper xmlMapper = new ObjectMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						AccCardResponse.class);
			} catch (Exception e) {

				ISLogger.getLogger().error(
						"objectMapper.readValue err (UFXMsgAddContractAccResp): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError objectMapper.readValue (UFXMsgAddContractAccResp): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz

			if (clResp.isSuccess()) { /*--success*/
				v_msg = v_msg + "\nКонтракт добавлен в Openway! ";

				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						3, branch, v_xml, v_res2.getName()), alias);

				// contract_number=9058-P-547326
				// buni ABS tablisada update qilamiz
				curr_acc.setCbsNumber(clResp.getBody().getAccount().getContractNumber());
				res = CustomerService.updateAccount(curr_acc);
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка(updateAccount IBS) " + res.getName();
					// this.alert(v_msg);
					// return;
				}
				
				curr_card.setCbsNumber(clResp.getBody().getCard().getContractNumber());
				res = CustomerService.updateCard(curr_card);
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка(updateCard IBS) " + res.getName();
					//this.alert(v_msg);
					//return;
				}
				
				
			} else {
				v_msg = v_msg + "\nОшибка открытия контракта в Openway: "
						+ clResp.getMessage();
			}

			//2026.05.07 shuerga 16413 shet ochishni qushishimiz kerak. try catch qilsak yaxshi manimcha. 
			if (curr_card.getProductCode1().equals("V_PL_DEB_EPIN_USD") || curr_card.getProductCode1().equals("V_IN_DEB_EPIN_USD")) {
				Account acc = new Account();
				acc.setBranch(branch);
				acc.setSubbranch((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
				acc.setAcc_bal("16413");
				acc.setClient(current.getId_client());
				acc.setName(current.getName());
				acc.setId_order("001");
				if (add_account$o_product_code1.getValue().equals("DEB_UZS"))
					acc.setCurrency("000");
				else
					acc.setCurrency("840");
				acc.setSgn("A");
				acc.setBal("B");
				acc.setSign_registr(2);

				res = AccountService.doAction(this.session.getAttribute("un")
						.toString(), this.session.getAttribute("pwd").toString(),
						acc, 1, this.alias);
				if (res.getCode() != 0) {
					//this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
					//return;
					v_msg_16413="ОШИБКА Открытие счета 16413% :\n" + res.getName();
				}
				if (!v_msg_16413.equals("")){
					acc.setId(res.getName());
					v_msg_16413 = "Счет "+res.getName()+" успешно открыть в НСИ банк. ";
					// утвердить
					res = AccountService.doAction(this.session.getAttribute("un")
							.toString(), this.session.getAttribute("pwd").toString(),
							acc, 2, this.alias);
					if (res.getCode() != 0) {
						v_msg_16413 = v_msg_16413 + "\nОшибка Утвердить счета :\n" + res.getName();
						//this.alert(v_msg);
						//return;
					} else 
						v_msg_16413 = v_msg_16413 + "\nСчет утвержден в НСИ банк. ";
				}
				
			}
			//2026.05.07 end
			
			this.alert(v_msg+"\n"+v_msg_16413);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}

		} else { // edit at the openway the account contract

			// class yasaymiz, qiymatlar beramiz
			UFXMsgUpdContractAcc updContract = CustomerService.makeUpdContract(
					curr_acc, alias);
			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(updContract);
				ISLogger.getLogger().error(
						"xmlUtils.serializeXmlFromObject(updContract) v_xml: "
								+ v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"error serialize xml(updContract) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml(updContract) "
						+ e.getMessage();
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
						"postUtils.sendData(updAccContract) err "
								+ e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			if (v_res2.getCode()!=200) {
				v_msg = v_msg
				+ "\nUnsuccess response (updAccContract): "
				+ v_res2.getCode()+", "+v_res2.getName();
				this.alert(v_msg);
				return;
	        }
			
			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgUpdContractAccResp clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2.getName(),
						UFXMsgUpdContractAccResp.class);
			} catch (Exception e) {

				ISLogger.getLogger().error(
						"xmlMapper.readValue err (UFXMsgUpdContractAccResp): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgUpdContractAccResp): "
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
					|| clResp.getResp_code().equals("0")) { /*--success*/
				v_msg = v_msg + "\nКонтракт редактирован в Openway! ";

				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
						3, branch, v_xml, v_res2.getName()), alias);

				// contract_number=9058-P-547326
				// buni ABS tablisada update qilamiz
				curr_acc.setCbsNumber(clResp.getMsgDataReq().getApplication()
						.getDataRsObject().getContractRs().get(0).getContract()
						.getContractIDT().getContractNumber());
				res = CustomerService.updateAccount(curr_acc);
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					// this.alert(v_msg);
					// return;
				}
			} else {
				v_msg = v_msg + "\nОшибка редактирования контракта в Openway: "
						+ clResp.getResp_text();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}
		}

		// this.refreshModel(this._startPageNumber);
		onSelect$branch_customers();
		this.add_account.setVisible(false);

	}

	public void onClick$btn_add_card() {
		if (current == null || current.getId_client() == null) {
			alert("Клиент не выбран");
			return;
		}

		if (curr_acc == null || curr_acc.getProductCode1() == null) {
			alert("Счетевой контракт не выбран");
			return;
		}

		// if (curr_card==null)
		curr_card = new CardInfo();
		curr_card.setBranch(branch);
		curr_card.setClient_id(current.getId_client());
		curr_card.setMasterProductCode1(curr_acc.getProductCode1());

		CheckNull.clearForm(this.add_card_wnd$addgrdl);
		add_card_wnd$o_product_code1.setModel(new ListModelList(CustomerService
				.getSubProductByProduct(curr_acc.getProductCode1(), alias)));
		this.add_card_wnd.setTitle("Открытие карточного контракта [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefAccData();
		//

		this.add_card_wnd.setVisible(true);
		binder.loadComponent(add_card_wnd);

	}
	
	public void onClick$btn_VIP() throws Exception {
	    final Window win = (Window) Executions.createComponents("way4_vipiska.zul", null, null);

	    win.setWidth("1800px");
	    win.setHeight("200px");
	    win.setSizable(true);
	    win.setClosable(true);
	    win.setBorder("normal");
	    win.setTitle("Выписка");

	    // Стили окна: синий градиент, скругление, тень
	    win.setStyle(
	        "border: 1px solid #EB2705;" +
	        "box-shadow: 0 1 80px rgba(0,0,0,3.6);" +
	        "border-radius: 8px;" +
	        "background: linear-gradient(to bottom, #F20202, #A10303);"
	    );

	    win.doModal();

	    // Отложенное выполнение, чтобы кнопка закрытия успела создаться
	    Events.echoEvent("onLater", win, null);

	    win.addEventListener("onLater", new EventListener() {
	        public void onEvent(Event evt) throws Exception {
	            try {
	                final Button closeBtn = (Button) win.getFellowIfAny("z$btn$close");
	                if (closeBtn != null) {
	                    win.setStyle(
	                            "border: 2px solid #EB2705;" +  // светло-красная рамка
	                            "box-shadow: 0 0 85px rgba(0,0,0,0.3);" +
	                            "border-radius: 6px;" +
	                            "background: linear-gradient(to bottom, #F20202, #A10303);"  // лёгкий градиент красного
	                        );

	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
	}



	public void onClick$add_card_btn$add_card_wnd()
			throws JsonProcessingException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_msg_16413 = "";
		String v_xml = "";

		err=check_user(branch, Integer.toString(uid), alias);
		if (!err.contains("Ok.")) {
			alert(err);
			return;
		}
		
		err="";		
		if (CheckNull.isEmpty(add_card_wnd$o_product_code1.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод карточного продукта";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
		curr_card.setOrder_dprt((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
		curr_card.setSocialNumber(current.getP_pinfl());
		curr_card.setContractNumber(curr_acc.getContractNumber());
		curr_card.setContractName(current.getP_first_name() + " " + current.getP_family() );
		curr_card.setContractName(curr_card.getContractName().trim());
		if (curr_card.getRbsNumberIbs() == null)
			if (curr_acc.getCbsNumber()==null || curr_acc.getCbsNumber().equals("") || curr_acc.getCbsNumber().length()!=22)
			{
				alert("Пустой или неправильное значение поле RBS_NUMBER счетового контракта.");
				return;
			}
			curr_card.setRbsNumberIbs(CustomerService.getNextCardRbsNumber(current.getBranch(), current.getId_client(), curr_acc.getCbsNumber().substring(2,22), is_test_mode)+"_C_"+ curr_acc.getCbsNumber().substring(2,22));
		curr_card.setPhone( curr_acc.getPhone()!=null ?  curr_acc.getPhone() : current.getP_phone_mobile());			
		curr_card.setLastName(current.getP_family());
		curr_card.setFirstName(current.getP_first_name());
		curr_card.setCurrency( ((AccInfo)CustomerService.getMapProduct_code1_way4(curr_acc.getProductCode1(), alias)).getCurrency() );
		curr_card.setProductWay( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(curr_card.getProductCode1(), alias)).getName_subprod_way() );
		curr_card.setProductIbs( ((SubProduct)CustomerService.getMapSubProduct_code1_way4(curr_card.getProductCode1(), alias)).getName_subprod_ibs() );
		
		// CustomerService.insertCard() ni ishlatamiz yani
		// bf_openway_cards? ga yozamiz
		Res res = null;
		res = CustomerService.insertCard(curr_card);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}

		// openwayda kartochniy kontrakt ochamiz
		// class yasaymiz, qiymatlar beramiz
		RegCardRequest addContract = CustomerService
				.makeAddContractCard(curr_card, curr_acc.getCbsNumber(), alias);

		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeJsonFromObject(addContract);
			ISLogger.getLogger().error(
					"xmlUtils.serializeJsonFromObject(addContractCard) v_xml: "
							+ v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize json(addContractCard) " + e.getMessage());
			v_msg = v_msg + "\nError serialize json(addContractCard) "
					+ e.getMessage();
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
			v_res2 = postUtils.sendData(url_way4_reg_card, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger()
					.error("postUtils.sendData err(addCardContract) "
							+ e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		ISLogger.getLogger().error(
				"v_res2= "	+ v_res2.getName());

		if (v_res2.getCode()!=200) {
			v_msg = v_msg
			+ "\nUnsuccess response (addCardContract): "
			+ v_res2.getCode()+", "+v_res2.getName();
			this.alert(v_msg);
			return;
        }

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		AccCardResponse clResp = null;
		ObjectMapper xmlMapper = new ObjectMapper();
		try {
			clResp = xmlMapper
					.readValue(v_res2.getName(), AccCardResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (AccCardResponse): "
							+ e.getMessage());
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (AccCardResponse): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.isSuccess() ) {
			v_msg = v_msg + "\nКонтракт добавлен/редактирован в Openway! ";
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 4,
					branch, v_xml, v_res2.getName()), alias);
			// <DataRs>.<ContractRs>.<Contract>.<ContractIDT>.<ContractNumber>430142______3543</ContractNumber>
			// buni ABS tablisada update qilamiz
			curr_card.setCbsNumber(clResp.getBody().getCard().getCbsNumber());
			if (clResp.getBody().getCard().getCbsNumber().length()!=16 && clResp.getBody().getCard().getContractNumber()!=null && clResp.getBody().getCard().getContractNumber().length()==16) //2025.12.16
				curr_card.setCbsNumber(clResp.getBody().getCard().getContractNumber()); //2025.12.16
			res = CustomerService.updateCard(curr_card);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				return;
			}
		} else {
			v_msg = v_msg
					+ "\nОшибка открытия/редактирования контракта в Openway: "
					+ clResp.getMessage() ;
		}

		//2026.05.07 shuerga 16413 shet ochishni qushishimiz kerak. try catch qilsak yaxshi manimcha. 
		if (curr_card.getProductCode1().equals("V_PL_DEB_EPIN_USD") || curr_card.getProductCode1().equals("V_IN_DEB_EPIN_USD")) {
			Account acc = new Account();
			acc.setBranch(branch);
			acc.setSubbranch((is_test_mode) ? "01196" : getDepartmentByUserID(Integer.toString(uid), alias));
			acc.setAcc_bal("16413");
			acc.setClient(current.getId_client());
			acc.setName(current.getName());
			acc.setId_order("001");
			if (add_account$o_product_code1.getValue().equals("DEB_UZS"))
				acc.setCurrency("000");
			else
				acc.setCurrency("840");
			acc.setSgn("A");
			acc.setBal("B");
			acc.setSign_registr(2);

			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 1, this.alias);
			if (res.getCode() != 0) {
				//this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
				//return;
				v_msg_16413="ОШИБКА Открытие счета 16413% :\n" + res.getName();
			}
			if (!v_msg_16413.equals("")){
				acc.setId(res.getName());
				v_msg_16413 = "Счет "+res.getName()+" успешно открыть в НСИ банк. ";
				// утвердить
				res = AccountService.doAction(this.session.getAttribute("un")
						.toString(), this.session.getAttribute("pwd").toString(),
						acc, 2, this.alias);
				if (res.getCode() != 0) {
					v_msg_16413 = v_msg_16413 + "\nОшибка Утвердить счета :\n" + res.getName();
					//this.alert(v_msg);
					//return;
				} else 
					v_msg_16413 = v_msg_16413 + "\nСчет утвержден в НСИ банк. ";
			}
			
		}
		//2026.05.07 end
		
		this.alert(v_msg+"\n"+v_msg_16413);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			return;
		}

		refresh_cardlist();
		this.add_card_wnd.setVisible(false);

	}

	public void onClick$lock_card_btn$lock_card_wnd() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_xml = "";
		
        CardInfo card1=(CardInfo)lock_card_wnd.getAttribute("card");

		if (card1 == null) {
			alert("Карта не выбрана!" );
			return;
		}

		if (lock_card_wnd$rcb_stop_cause.getValue() == null
				|| lock_card_wnd$rcb_stop_cause.getValue().equals("")) {
			fl_err = true;
			err = String.valueOf(err) + "\nПричина(вид) блокировки";
		}
		if (CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКоментарий(описание) блокировки";
		}
		if ( CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue()) || !lock_card_wnd$txt_comment_text.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
		  || lock_card_wnd$txt_comment_text.getValue().length() > 160 || lock_card_wnd$txt_comment_text.getValue().length() < 8) {
	        fl_err = true;
	        err = String.valueOf(err) + "\nКоментарий(описание) блокировки: Только латинские буквы, минимум 8, максимум 160 символов.";
        }
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
		card1.setSTOP_CAUSE(lock_card_wnd$rcb_stop_cause.getValue());
		card1.setCommentText(lock_card_wnd$txt_comment_text.getValue());
		card1.setSocialNumber(current.getP_pinfl());
		LockCardRequest lockCard = CustomerService.makeLockCard(card1, alias);
		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeJsonFromObject(lockCard);
			ISLogger.getLogger().error(
					"xmlUtils.serializeJsonFromObject(lockCard) v_xml: "
							+ v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize json(lockCard) " + e.getMessage());
			v_msg = v_msg + "\nError serialize json(lockCard) "
					+ e.getMessage();
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
			v_res2 = postUtils.sendData(url_way4_block_unblock, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger()
					.error("postUtils.sendData err(lockCard) "
							+ e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		ISLogger.getLogger().error(
				"v_res2= "	+ v_res2.getName());
		
		if (v_res2.getCode()!=200) {
			v_msg = v_msg
			+ "\nUnsuccess response (lockCard): "
			+ v_res2.getCode()+", "+v_res2.getName();
			this.alert(v_msg);
			return;
		}

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		LockCardResponse clResp = null;
		ObjectMapper xmlMapper = new ObjectMapper();
		try {
			clResp = xmlMapper
					.readValue(v_res2.getName(), LockCardResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (LockCardResponse): "
							+ e.getMessage());
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (LockCardResponse): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.isSuccess() ) {
			v_msg = v_msg + "\nКарта блокирован в Openway! ";
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 7,
					branch, v_xml, v_res2.getName()), alias);
		} else {
			v_msg = v_msg
					+ "\nОшибка блокировки карту в Openway: "
					+ clResp.getMessage() ;
		}
		
		this.alert(v_msg);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			return;
		}
		refresh_cardlist();
		this.lock_card_wnd.setVisible(false);
    }
	
	public void onClick$unlock_card_btn$lock_card_wnd() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_xml = "";

        CardInfo card1=(CardInfo)lock_card_wnd.getAttribute("card");

		if (card1 == null) {
			alert("Карта не выбрана!" );
			return;
		}

		//if (CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue())) {
		//	fl_err = true;
		//	err = String.valueOf(err) + "\nКоментарий(описание) разблокировки";
		//}
		if ( CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue()) || !lock_card_wnd$txt_comment_text.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
		  || lock_card_wnd$txt_comment_text.getValue().length() > 160 || lock_card_wnd$txt_comment_text.getValue().length() < 8) {
	        fl_err = true;
	        err = String.valueOf(err) + "\nКоментарий(описание) разблокировки: Только латинские буквы, длина минимум 8, максимум 160 символов.";
        }
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		card1.setSTOP_CAUSE("00");
		card1.setCommentText(lock_card_wnd$txt_comment_text.getValue());
		card1.setSocialNumber(current.getP_pinfl());
		LockCardRequest unLockCard = CustomerService.makeLockCard(card1, alias);
		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeJsonFromObject(unLockCard);
			ISLogger.getLogger().error(
					"xmlUtils.serializeJsonFromObject(unLockCard) v_xml: "
							+ v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize json(unLockCard) " + e.getMessage());
			v_msg = v_msg + "\nError serialize json(unLockCard) "
					+ e.getMessage();
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
			v_res2 = postUtils.sendData(url_way4_block_unblock, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger()
					.error("postUtils.sendData err(unLockCard) "
							+ e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		ISLogger.getLogger().error(
				"v_res2= "	+ v_res2.getName());
		
		if (v_res2.getCode()!=200) {
			v_msg = v_msg
			+ "\nUnsuccess response (unLockCard): "
			+ v_res2.getCode()+", "+v_res2.getName();
			this.alert(v_msg);
			return;
		}

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		LockCardResponse clResp = null;
		ObjectMapper xmlMapper = new ObjectMapper();
		try {
			clResp = xmlMapper
					.readValue(v_res2.getName(), LockCardResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"xmlMapper.readValue err (LockCardResponse): "
							+ e.getMessage());
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (LockCardResponse): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.isSuccess() ) {
			v_msg = v_msg + "\nКарта разблокирован в Openway! ";
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 7,
					branch, v_xml, v_res2.getName()), alias);
		} else {
			v_msg = v_msg
					+ "\nОшибка разблокировки карту в Openway: "
					+ clResp.getMessage() ;
		}
		
		this.alert(v_msg);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			return;
		}
		refresh_cardlist();
		this.lock_card_wnd.setVisible(false);
    }
	
	public static Balances getCardBalances_old(String cardRbsNumber) {
		Balances bal = new Balances();
		String v_xml;
		// class yasaymiz, qiymatlar beramiz
		UFXMsgInqContract clBal = CustomerService
				.makeClassForBalance_old(cardRbsNumber);

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
	
	public static List <BalanceCardBody> getCardBalances(String cardRbsNumber) {

		List <BalanceCardBody> bal = new ArrayList <BalanceCardBody>();
		String v_xml;
		// class yasaymiz, qiymatlar beramiz
		BalanceCardRequest clBal = CustomerService
				.makeClassForBalance(cardRbsNumber);

		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeJsonFromObject(clBal);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"error serialize json(getBalance) " + e.getMessage());
			return bal;
		}

		// post zapros qilamiz
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		Res v_res2 = null;
		try {
			v_res2 = postUtils.sendData(url_way4_balance_card, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"postUtils.sendData(getBalance) err " + e.getMessage());
			return bal;
		}

		ISLogger.getLogger().error(
				"v_res2= "	+ v_res2.getName());
		
		if (v_res2.getCode()!=200) {
			ISLogger.getLogger().error(
			"Unsuccess response (getBalance): "
			+ v_res2.getCode()+", "+v_res2.getName());
			return bal;
		}
		
		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		BalanceCardResponse clResp = null;
		ObjectMapper xmlMapper = new ObjectMapper();
		try {
			clResp = xmlMapper.readValue(v_res2.getName(), BalanceCardResponse.class);
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
		if (clResp.isSuccess()) /* success */{

			bal= clResp.getBody();
			return bal;

		} else {

			ISLogger.getLogger().error(
					"getting error (UFXMsgInqContractResp): "
							+ clResp.getMessage());
			return bal;
		}

	}
	public String check_user(String branch, String userId, String alias) {
	  if (getDepartmentByUserID(userId, alias)!=null) {
		  return "Ok.";
	  } else {
		  String res1 = setDepartmentByUserID(branch, userId, alias);
		  if (res1.contains("Ok."))
			  return "Ok.";
		  else 
			  return res1;
	  }
	}
	
	public static String getDepartmentByUserID(String user_id, final String alias) {
		String res=null;
		if (mapUsersDepartments == null || mapUsersDepartments.size()==0 ){
			return res;
		} else {
			if (mapUsersDepartments.containsKey(user_id))
				return mapUsersDepartments.get(user_id) ;
		}	
		return res;
	}
	
	public static String setDepartmentByUserID(String branch, String user_id, final String alias) {
		String res=null;
		if (mapUsersDepartments == null ){
			mapUsersDepartments = new HashMap<String, String>();
		} 
		//user_id buyicha bazadan branchni olib mapga quyamiz. natijani(uspeshno yoki yuq) qaytaramz

		int cnt = CustomerService.getCountDepartmentByUserID(branch, user_id, alias);
		if (cnt==-1){
			res= "Error. ss_subsidiary_user Пользовател("+user_id+") get count";
		} else if (cnt==0){
    		res = "Error. Пользовател("+user_id+") не привязан к подразделению(ОБУ)";
    	} else if (cnt==1) {
    		res = "Ok.";
    	} else {
    		res = "Error. Пользовател("+user_id+") привязан больше одного подразделению(ОБУ)";
    	}
        //res ni tekshiramiz
		if (res.contains("Error.")) {
			return res;
		} 
		//res Ok. bulsa agar
		String depCode=CustomerService.getDepartmentByUserID(branch, user_id, alias);
		if (res.contains("Error.")) {
			return res;
		} 
        mapUsersDepartments.put(user_id, depCode);
		return "Ok.";
	}

	public void onClick$showBalanceCloseBtn$showBalanceWindow()	 {
		showBalanceWindow.setVisible(false);
    }

	public void onClick$close_wnd_lock_btn$update_sms_wnd_acc()	 {
		update_sms_wnd_acc.setVisible(false);
    }

	public void onClick$close_wnd_lock_btn$update_sms_wnd_card()	 {
		update_sms_wnd_card.setVisible(false);
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

	public void onChange$acc_bal$add_everywhere() {
		curr_acc.setAcc_bal(add_everywhere$acc_bal.getValue());
	}

	public void onChange$o_product_code1$add_everywhere() {
		curr_acc.setProductCode1(add_everywhere$o_product_code1.getValue());
	}

	public void onChange$o_card_product_code1$add_everywhere() {
		curr_card.setProductCode1(add_everywhere$o_card_product_code1.getValue());
	}

	public void onChange$o_card_product_code1$add_account() {
		curr_card.setProductCode1(add_account$o_card_product_code1.getValue());
	}

	public void onChange$rcb_stop_cause$lock_card_wnd() {
		if (lock_card_wnd$rcb_stop_cause.getValue() == null
				|| lock_card_wnd$rcb_stop_cause.getValue().equals("")) {
			lock_card_wnd$txt_comment_text.setValue("");
		} else if (lock_card_wnd$rcb_stop_cause.getValue().equals("014"))
			lock_card_wnd$txt_comment_text.setValue("Zakritie Staroy Karti");
		else if (lock_card_wnd$rcb_stop_cause.getValue().equals("41"))
			lock_card_wnd$txt_comment_text.setValue("Blokirovka uteryannoy Karti");
		else if (lock_card_wnd$rcb_stop_cause.getValue().equals("43"))
			lock_card_wnd$txt_comment_text.setValue("Blokirovka ukradennoy Karti");
		else
			lock_card_wnd$txt_comment_text.setValue("");
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
