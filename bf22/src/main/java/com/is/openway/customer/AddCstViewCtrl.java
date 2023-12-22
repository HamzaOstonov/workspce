package com.is.openway.customer;

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
import com.is.openway.model.UFXMsgUpdContractAcc;
import com.is.openway.model.UFXMsgUpdContractAccResp;
import com.is.report.DPrint;
import com.is.openway.Utils;
import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;
import com.is.openway.customer.CustomerService;

import com.is.openwayutils.user.UserService;
import com.is.openwayutils.user.UserActionsLog;
import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;
import com.is.openwayutils.utils.RefData;
import com.is.openwayutils.utils.Res;

public class AddCstViewCtrl extends GenericForwardComposer {
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
	private String curip;
	private int uid;
	static String openwayEndpoint;
	private boolean add_way;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private Window add_everywhere, add_account, add_card_wnd;
	private Datebox add_everywhere$ap_birthday;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Grid add_everywhere$addgrdl;
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

	private RefCBox add_account$acc_bal;
	private RefCBox add_account$o_product_code1;
	private RefCBox add_card_wnd$o_product_code1;

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
	private Textbox add_account$id_order;
	private Textbox add_account$o_comment_text;
	private Textbox add_card_wnd$o_rbs_number;
	private Textbox add_card_wnd$o_comment_text;

	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox branch_customers, accGrid, cardGrid;

	private Textbox txbId_client;
	private Textbox txbPinfl;

	// private Textbox txtPassportSerial;
	// private Textbox txtName;
	// private Datebox dbxB_date;

	public AddCstViewCtrl() {
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
		if (openwayEndpoint == null || openwayEndpoint == ""
				|| openwayEndpoint.equals(""))
			openwayEndpoint = ConnectionPool.getValue("OPENWAY_ENDPOINT");
		// filter.setP_pinfl("56789012340078");
		// filter.setId_client("60000001");
		filter.setEndpoint(openwayEndpoint);

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
				// knopka redaktirovat vezde
				Listcell edit_cell = new Listcell();
				Toolbarbutton btedit = new Toolbarbutton();
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
								add_way = true;
								add_bnk = true;
								add_everywhere
										.setTitle("Редактирование клиента [Bank] - [Way4]");
								add_everywhere.setVisible(true);
							}
						});
				edit_cell.appendChild((Component) btedit);
				row.appendChild((Component) edit_cell);
				// knopka end redaktirovat vezde
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
				// knopka
				final Listcell t_edit_cell = new Listcell();
				Toolbarbutton btt = new Toolbarbutton();
				btt.setLabel("");
				btt.setImage("/images/link16.png");
				btt.setAttribute("br_cl", (Object) vCustomer);
				btt.setTooltiptext("Редактировать Openway");
				btt.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								current = (Customer) event.getTarget()
										.getAttribute("br_cl");
								copyOfCurrent = current.clone(current);
								loadRefData();
								if (current.getP_code_adr_region() != null)
									add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
											CustomerService.getDistrByRegion(
													current.getP_code_adr_region(),
													alias)));
								add_way = true;
								add_bnk = false;
								add_everywhere
										.setTitle("Открытие клиента Openway");
								add_everywhere.setVisible(true);
							}
						});
				t_edit_cell.appendChild((Component) btt);
				row.appendChild((Component) t_edit_cell);
				//

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
				//row.appendChild(new Listcell(pAccInfo.getSurname()));
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getTranz_acct()));
				row.appendChild(new Listcell(pAccInfo.getProductCode1()));
				
				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));

				// knopka otkrit shetevoy kontrakt/redaktirovat
				Listcell edit_contract_cell = new Listcell();
				Toolbarbutton btedit_contr = new Toolbarbutton();
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
				//row.appendChild(new Listcell(pAccInfo.getCard()));
				row.appendChild(new Listcell(pAccInfo.getContractNumber()));
				row.appendChild(new Listcell(pAccInfo.getCardExpiry()));//expiry
				row.appendChild(new Listcell(pAccInfo.getProductCode1()));
				
				row.appendChild(new Listcell(pAccInfo.getCbsNumber()));

				row.appendChild(new Listcell(pAccInfo.getRbsNumberIbs()));
				row.appendChild(new Listcell(""));//status
				
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
				//row.appendChild((Component) edit_contract_cell);
				row.appendChild(new Listcell("Button"));
				// knopka end redaktirovat карточный kontrakt
			}
		});
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

	public void onClick$btn_add_everywhere() {
		current = new Customer();
		this.add_way = true;
		this.add_bnk = true;

		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);
		this.add_everywhere.setTitle("Открытие клиента [БАНК] - [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefData();
		//
		current.setO_client_type("PR");
		current.setCode_resident("1");
		current.setCode_country("860");
		current.setP_code_citizenship("860");
		// CustomerService.prepareFakeValues(current);

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

	public void onClick$add_btn$add_everywhere() throws JsonProcessingException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
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
		} else if (add_everywhere$ap_pinfl.getValue().length() != 14) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
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
		if (CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		/*
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
		if (CheckNull.isEmpty(add_everywhere$ap_email_address.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nЭлектронная почта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод области";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод района";
		}
		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_zip_code.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый индекс";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (!add_everywhere$ao_post_address_fact.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ao_post_address_fact.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nФактический адрес";
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
		/*
		 * if (CheckNull.isEmpty(add_everywhere$ap_code_tax_org.getValue())) {
		 * fl_err = true; err = String.valueOf(err) +
		 * "\nКод налоговой организации"; }
		 */
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		current.setName(current.getP_family() + " " + current.getP_first_name()
				+ " " + current.getP_patronymic());
		current.setName(current.getName().trim());
		Res res = null;

		if (current.getId_client() == null || current.getId_client().equals("")) {
			current.setBranch(branch);
			current.setP_code_bank(branch);

			res = CustomerService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					current, 1, 2, this.alias, true);
			ISLogger.getLogger().error("res.getName ID: " + res.getName());
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

			// malumotlarda uzgarish bulsa 19-deystivie-korrektirovkani
			// ishlatamiz
			if (current.hasBankChanges(copyOfCurrent)) {
				// todo
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
		}

		// CustomerService.insertClient() ni ishlatamiz yani bf_openway_clients
		// ga yozamiz
		res = CustomerService.insertClient(current);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}

		String v_xml = "_";
		if (!current.isWay_exist()) {
			// openwayda ochamiz
			// class yasaymiz, qiymatlar beramiz
			UFXMsgAddClient addClient = CustomerService.makeAddClient(current,
					alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(addClient);
			} catch (Exception e) {
				ISLogger.getLogger().error("error serialize xml(addClient) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
		} else { // edit at the openway

			// class yasaymiz, qiymatlar beramiz
			UFXMsgUpdClient addClient = CustomerService.makeUpdClient(current,
					alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(addClient);
			} catch (Exception e) {
				ISLogger.getLogger().error("error serialize xml(addClient) " + e.getMessage());
				v_msg = v_msg + "\nError serialize xml " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
		}
		ISLogger.getLogger().error("postUtils.sendData(addClient) v_xml : " + v_xml);
		
		// post zapros qilamiz
		// zapros qilib javobini olamiz
		PostUtils postUtils = new PostUtils();
		// String v_url = "http://213.230.121.32:8090";
		String v_res2 = "";
		try {
			v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			// System.out.println("v_res2 = " + v_res2);
		} catch (Exception e) {
			// Block of code to handle errors
			
			ISLogger.getLogger().error("postUtils.sendData(addClient) err " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(addClient) " + e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		ISLogger.getLogger().error("postUtils.sendData(addClient) v_res2: " + v_res2);
		
		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		UFXMsgAddClientResp clResp = null;
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper.readValue(v_res2, UFXMsgAddClientResp.class);
		} catch (Exception e) {
			// Block of code to handle errors
			ISLogger.getLogger().error("xmlMapper.readValue err (UFXMsgAddClientResp): "
							+ e.getMessage());
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (UFXMsgAddClientResp): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) /* success */{
			v_msg = v_msg + "\nКлиент добавлен/редактирован в Openway! Успешный ответ из Openway.";
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 5, branch, v_xml,
					v_res2), alias);
			// this.alert("Клиент добавлен в Openway! ");
		} else {
			// this.alert("Ошибка открытия клиента в Openway: "+clResp.getResp_text());
			v_msg = v_msg
					+ "\nОшибка открытия/редактирования клиента в Openway: "
					+ clResp.getResp_text();
		}

		this.alert(v_msg);

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
		// this.add_everywhere.setVisible(false);
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

			AccountFilter filt = new AccountFilter();
			filt.setBranch(branch);
			filt.setAcc_bal("22618");
			filt.setCurrency("840");
			filt.setClient(current.getId_client());

			List<AccInfo> infoList = new ArrayList<AccInfo>();
			// ABSdan
			List<Account> accList = AccountService.getAccountsFl(0, 100, filt, alias);
			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_ACC)
			List<AccInfo> accList2 = CustomerService.getContractAccList_ABS(current.getBranch(),
					current.getId_client(), alias);
			
			// WAYdan
			UFXMsgReqContractResp clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							openwayEndpoint, "");
			ArrayList<ContractResp> contractAccList = new ArrayList<ContractResp>();

			if (clResp.getResp_code().equals("0")) {
				if (clResp.getMsgDataReq().getApplication().getDataRsObject()!=null) {
					ArrayList<ContractRs> contractList = clResp.getMsgDataReq()
					.getApplication().getDataRsObject().getContractRs();
					for (int i = 0; i < contractList.size(); i++) {
						if (contractList.get(i).getContract().getProduct()
							.getAddInfo().getParm().getValue()
							.equals("Account")) {
							contractAccList.add(contractList.get(i).getContract());
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
				//ABS dan BF_OPENWAY_CONTRACT_ACC kelgan qushimcha malumotlar b-n inf ga zeb beramiz :)
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

			//accGrid.setModel(new ListModelList(infoList));
			accGrid.setModel(new BindingListModelList(infoList, true));
			//
			
			if (infoList.size() > 0) {
				accGrid.setSelectedIndex(0);
				//this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", accGrid,
						accGrid.getSelectedItems());
				Events.sendEvent(evt);
				//binder.loadComponent(accGrid);
			}

		}

	}
	
	public void onSelect$accGrid() {
		if (curr_acc != null && curr_acc.getProductCode1()!=null) {
			List<CardInfo> infoList = new ArrayList<CardInfo>();
		
			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			List<CardInfo> cardList = CustomerService.getContractCardList_ABS(current.getBranch(),
					current.getId_client(), curr_acc.getProductCode1(), alias);
			
			// WAYdan
			UFXMsgReqContractResp clResp = CustomerService
					.getCustomersContract_openway(current.getBranch(),
							current.getId_client(), current.getP_pinfl(),
							openwayEndpoint, "");
			ArrayList<ContractResp> contractCardList = new ArrayList<ContractResp>();

			if (clResp.getResp_code().equals("0")) {
				ArrayList<ContractRs> contractList = clResp.getMsgDataReq()
						.getApplication().getDataRsObject().getContractRs();
				for (int i = 0; i < contractList.size(); i++) {
					if (contractList.get(i).getContract().getProduct()
							.getAddInfo().getParm().getValue()
							.equals("Card")) {
						contractCardList.add(contractList.get(i).getContract());
					}
				}
			}

			for (int i = 0; i < cardList.size(); i++) {
				CardInfo inf = new CardInfo();
				inf.setBranch(branch);
				inf.setClient_id(cardList.get(i).getClient_id());
				inf.setMasterProductCode1(cardList.get(i).getMasterProductCode1());
				inf.setProductCode1(cardList.get(i).getProductCode1());
				inf.setRbsNumberIbs(cardList.get(i).getRbsNumberIbs());
				inf.setContractName(cardList.get(i).getContractName());
				inf.setCbsNumber(cardList.get(i).getCbsNumber());
				//inf.setAb_expirity(cardList.get(i).getDate_open());
				//inf.setContractNumber("");
				//inf.setAcc_bal(cardList.get(i).getAcc_bal());
				//inf.setId_order(cardList.get(i).getId_order());
				inf.setWay_exist(false);

				// shetimizga mos keladigan kontrakt topsak is_way ni true
				// qilamiz,
				// kerakli polelarni tuldirtiramiz va kontraktlistdan uchirib
				// quyamiz
				for (int j = 0; j < contractCardList.size(); j++) {
					if (contractCardList
							.get(j)
							.getContractIDT()
							.getCBSNumber()
							.equals(cardList.get(i).getRbsNumberIbs())) {
						inf.setWay_exist(true);
						//inf.setCbsNumber(contractCardList.get(j)
						//		.getContractIDT().getCBSNumber());
						inf.setContractNumber(contractCardList.get(j)
								.getContractIDT().getContractNumber());
						inf.setCardExpiry(	contractCardList.get(j)
								.getProductionParmsObject().getCardExpiry());
						contractCardList.remove(j);
						break;
					}
				}				
				
				//
				infoList.add(inf);
			}
           
			for (int j = 0; j < contractCardList.size(); j++) {
				CardInfo inf = new CardInfo();
				inf.setWay_exist(true);
				inf.setCbsNumber(contractCardList.get(j).getContractIDT()
						.getCBSNumber());
				inf.setContractNumber(contractCardList.get(j)
						.getContractIDT().getContractNumber());
				inf.setCardExpiry(	contractCardList.get(j)
				.getProductionParmsObject().getCardExpiry());
				infoList.add(inf);
			}
			
			//accGrid.setModel(new ListModelList(infoList));
			cardGrid.setModel(new BindingListModelList(infoList, true));
			//
			
			if (infoList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				//this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				//binder.loadComponent(accGrid);
			}

		}

	}

	public void onClick$btn_add_acc() {
		if (current == null || current.getId_client() == null) {
			alert("Клиент не выбран");
			return;
		}
		curr_acc = new AccInfo();
		curr_acc.setBranch(branch);
		curr_acc.setClient(current.getId_client());

		CheckNull.clearForm(this.add_account$addgrdl);
		this.add_everywhere
				.setTitle("Открытие счета и счетового контракта [БАНК] - [WAY4]");

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

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		Res res = null;
		if (curr_acc.getId() == null || curr_acc.getId().equals("")) {
			Account acc = new Account();
			acc.setBranch(branch);
			acc.setAcc_bal(curr_acc.getAcc_bal());
			acc.setClient(current.getId_client());
			acc.setName(current.getName());
			acc.setId_order(curr_acc.getId_order());
			acc.setCurrency("840");
			acc.setSgn("P");
			acc.setBal("B");
			acc.setSign_registr(2);
			// String un,String pw, Account account,int actionid, String alias,
			// Boolean selfBranch
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 1, this.alias);
			ISLogger.getLogger().error("res.getName ID: " + res.getName());
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
				return;
			}
			acc.setId(res.getName());
			curr_acc.setId(res.getName());
			v_msg = "Счет открыть в НСИ банк. ";
			v_msg = v_msg + "Счет клиента: " + res.getName();
			// утвердит
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 2, this.alias);
			ISLogger.getLogger().error("res.getName 2: " + res.getName());
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка Утвердить счета :\n" + res.getName();
				this.alert(v_msg);
				return;
			}

			v_msg = v_msg + "\nСчет утвержден в НСИ банк. ";

		}

		// CustomerService.insertAccount() ni ishlatamiz yani
		// bf_openway_accounts? ga yozamiz
		curr_acc.setRbsNumberIbs(curr_acc.getBranch() + curr_acc.getId());
		curr_acc.setContractName(current.getP_family() + " "
				+ current.getP_first_name() + " " + current.getP_patronymic());
		curr_acc.setSocialNumber(current.getP_pinfl());
		res = CustomerService.insertAccount(curr_acc);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			return;
		}

		String v_xml = "_";
		if (!curr_acc.isWay_exist()) {
			// openwayda shetevoy kontrakt ochamiz
			// class yasaymiz, qiymatlar beramiz
			UFXMsgAddContractAcc addContract = CustomerService.makeAddContract(
					curr_acc, alias);

			// classni xml stringga aylantiramiz
			XmlUtils xmlUtils = new XmlUtils();
			try {
				v_xml = xmlUtils.serializeXmlFromObject(addContract);
				ISLogger.getLogger().error("xmlUtils.serializeXmlFromObject(addContract) v_xml: "
								+ v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error("error serialize xml(addContract) "
						+ e.getMessage());
				v_msg = v_msg + "\nError serialize xml(addContract) "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// post zapros qilamiz
			// zapros qilib javobini olamiz
			PostUtils postUtils = new PostUtils();
			String v_res2 = "";
			try {
				v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error("postUtils.sendData(addAccContract) err " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
			ISLogger.getLogger().error("postUtils.sendData(addAccContract) v_res2: " + v_res2);

			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgAddContractAccResp clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2, UFXMsgAddContractAccResp.class);
			} catch (Exception e) {

				ISLogger.getLogger().error("xmlMapper.readValue err (UFXMsgAddContractAccResp): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgAddContractAccResp): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
			// qilamiz

			if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) { /*--success*/
				v_msg = v_msg + "\nКонтракт добавлен в Openway! ";

				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 3, branch, v_xml,
						v_res2), alias);
				
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
				v_msg = v_msg
						+ "\nОшибка открытия контракта в Openway: "
						+ clResp.getResp_text();
			}

			this.alert(v_msg);

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
				ISLogger.getLogger().error("xmlUtils.serializeXmlFromObject(updContract) v_xml: "
								+ v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error("error serialize xml(updContract) "
						+ e.getMessage());
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
			String v_res2 = "";
			try {
				v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error("postUtils.sendData(updAccContract) err " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
			ISLogger.getLogger().error("postUtils.sendData(updAccContract) v_res2: " + v_res2);

			// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
			UFXMsgUpdContractAccResp clResp = null;
			XmlMapper xmlMapper = new XmlMapper();
			try {
				clResp = xmlMapper.readValue(v_res2, UFXMsgUpdContractAccResp.class);
			} catch (Exception e) {

				ISLogger.getLogger().error("xmlMapper.readValue err (UFXMsgUpdContractAccResp): "
								+ e.getMessage());
				v_msg = v_msg
						+ "\nError xmlMapper.readValue (UFXMsgUpdContractAccResp): "
						+ e.getMessage();
			}
			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
			// qilamiz

			if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) { /*--success*/
				v_msg = v_msg + "\nКонтракт редактирован в Openway! ";

				UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 3, branch, v_xml,
						v_res2), alias);
				
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
				v_msg = v_msg
						+ "\nОшибка редактирования контракта в Openway: "
						+ clResp.getResp_text();
			}

			this.alert(v_msg);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				return;
			}			
		}

		//this.refreshModel(this._startPageNumber);
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

		//if (curr_card==null)
		curr_card = new CardInfo();
		curr_card.setBranch(branch);
		curr_card.setClient_id(current.getId_client());
		curr_card.setMasterProductCode1(curr_acc.getProductCode1());

		CheckNull.clearForm(this.add_card_wnd$addgrdl);
		add_card_wnd$o_product_code1.setModel(new ListModelList(
				CustomerService.getSubProductByProduct(curr_acc.getProductCode1(),alias)));
		this.add_card_wnd.setTitle("Открытие карточного контракта [WAY4]");

		// loaddata qilamiz shu yerda
		loadRefAccData();
		//

		this.add_card_wnd.setVisible(true);
		binder.loadComponent(add_card_wnd);

	}

	public void onClick$add_card_btn$add_card_wnd()
			throws JsonProcessingException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String v_xml = "";
	
		if (CheckNull.isEmpty(add_card_wnd$o_product_code1.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод карточного продукта";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
        curr_card.setSocialNumber(current.getP_pinfl());
        curr_card.setContractNumber(curr_acc.getContractNumber());
		curr_card.setContractName(current.getP_family() + " " + current.getP_first_name()
				+ " " + current.getP_patronymic());
        curr_card.setContractName(curr_card.getContractName().trim());
		if (curr_card.getRbsNumberIbs()==null)
            curr_card.setRbsNumberIbs(curr_acc.getContractNumber()+"-"+String.format("%07d", CustomerService.getSeqCardRbsNumber()));
		curr_card.setLastName(current.getP_family());
		curr_card.setFirstName(current.getP_first_name());
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
		UFXMsgAddContractCard addContract = CustomerService
				.makeAddContractCard(curr_card, alias);

		// classni xml stringga aylantiramiz
		XmlUtils xmlUtils = new XmlUtils();
		try {
			v_xml = xmlUtils.serializeXmlFromObject(addContract);
			ISLogger.getLogger().error("xmlUtils.serializeXmlFromObject(addContractCard) v_xml: "
							+ v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error("error serialize xml(addContractCard) "
					+ e.getMessage());
			v_msg = v_msg + "\nError serialize xml(addContractCard) "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// post zapros qilamiz
		// zapros qilib javobini olamiz

		PostUtils postUtils = new PostUtils();
		String v_res2 = "";
		try {
			v_res2 = postUtils.sendData(openwayEndpoint, v_xml);
		} catch (Exception e) {
			ISLogger.getLogger().error("postUtils.sendData err(addCardContract) " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData " + e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		ISLogger.getLogger().error("postUtils.sendData(addCardContract) v_res2: " + v_res2);

		// kelgan javobdagi xml-stringdan java object (klass) yasaymiz
		UFXMsgAddContractCardRes clResp = null;
		XmlMapper xmlMapper = new XmlMapper();
		try {
			clResp = xmlMapper.readValue(v_res2, UFXMsgAddContractCardRes.class);
		} catch (Exception e) {
			ISLogger.getLogger().error("xmlMapper.readValue err (UFXMsgAddContractResp): "
							+ e.getMessage());			
			v_msg = v_msg
					+ "\nError xmlMapper.readValue (UFXMsgAddContractResp): "
					+ e.getMessage();
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (clResp.getResp_code() == "0" || clResp.getResp_code().equals("0")) {
			v_msg = v_msg + "\nКонтракт добавлен/редактирован в Openway! ";
			UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip, 4, branch, v_xml,
					v_res2), alias);
			// <DataRs>.<ContractRs>.<Contract>.<ContractIDT>.<ContractNumber>430142______3543</ContractNumber> 
			// buni ABS tablisada update qilamiz
			curr_card.setCbsNumber(clResp.getMsgDataReq().getApplication()
					.getDataRsObject().getContractRs().get(0).getContract()
					.getContractIDT().getContractNumber());
			res = CustomerService.updateCard(curr_card);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				return;
			}
		} else {
			v_msg = v_msg
					+ "\nОшибка открытия/редактирования контракта в Openway: "
					+ clResp.getResp_text();
		}

		this.alert(v_msg);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			return;
		}
        
		onSelect$accGrid();
		this.add_card_wnd.setVisible(false);		
		
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
