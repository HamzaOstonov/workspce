// 
// Decompiled by Procyon v0.5.36
// 

package com.is.tieto_globuz.customer;

import globus.IssuingWS.IssuingPortProxy;
import globus.issuing_v_01_02_xsd.KeyType_Agreement;
import globus.issuing_v_01_02_xsd.OperationConnectionInfo;
import globus.issuing_v_01_02_xsd.OperationResponseInfo;
import globus.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import globus.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request;
import globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import globus.issuing_v_01_02_xsd.RowType_Agreement;
import globus.issuing_v_01_02_xsd.RowType_CardInfo;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import globus.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import globus.issuing_v_01_02_xsd.RowType_CloseAccount_Request;
import globus.issuing_v_01_02_xsd.RowType_Customer;
import globus.issuing_v_01_02_xsd.RowType_DeactivateCard_Request;
import globus.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request;
import globus.issuing_v_01_02_xsd.RowType_EditAgreement_Request;
import globus.issuing_v_01_02_xsd.RowType_EditCard_Request;
import globus.issuing_v_01_02_xsd.RowType_EditCustomer_Request;
import globus.issuing_v_01_02_xsd.RowType_Generic;
import globus.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request;
import globus.issuing_v_01_02_xsd.RowType_GetRealCard_Request;
import globus.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request;
import globus.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder;
import globus.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder;

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
import com.ibm.db2.jcc.am.o;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.report.DPrint;
import com.is.tieto_globuz.EmpcSettings;
import com.is.tieto_globuz.Utils;
import com.is.tieto_globuz.tieto.AccInfo;
import com.is.tieto_globuz.tieto.CardInfo;
import com.is.tieto_globuz.tieto.Tclient;
import com.is.tieto_globuz.tieto.TclientFilter;
import com.is.tieto_globuz.tieto.TclientService;
import com.is.tieto_globuz.tieto.TietoCardSetting;
import com.is.tieto_globuz.tietoAccount.GlobuzAccount;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountFilter;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountService;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

public class AddCstViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public static Window customermain;
	private Window addCust;
	private Window addTieto;
	private Window printwnd;
	private Window accwnd;
	private Window addCustomer;
	private Window accounts;
	private Window add_everywhere;
	private Window addwnd;
	private Panel show_cards;
	private org.zkoss.zul.api.Div leftToolbar;
	private Column leftColumn;
	private Window blockwnd;
	private Window cardTypeSel;
	private Window bt_rest_confirmation_wnd;
	private Window blockMsgWindow;
	private Window cardActionsWindow;
	public CustomerFilter filter;
	public CustomerFilter bfilter;
	private Paging bankdataPaging;
	private PagingListModel bmodel;
	private com.is.tieto_globuz.tieto.PagingListModel tmodel;
	private boolean _needsTotalSizeUpdate;
	private String soapEndpointUrl;
	private String soapAction;
	private Menupopup editPopup;
	private AnnotateDataBinder binder;
	private AnnotateDataBinder binderGlobuzAccount;
	private AnnotateDataBinder binderCard, binderCustomer;
	private SimpleDateFormat df;
	private SimpleDateFormat datef;
	private SimpleDateFormat expirySDF;
	private Listbox addCustomer$dataGrid;
	private Listbox bank_customers;
	private Listbox branch_customers;
	private Listbox addwnd$accGrid;
	private Listbox accGrid;
	private Listbox accounts$accGrid;
	private Textbox addTieto$aclient;
	private Textbox addTieto$abank_c;
	private Textbox addTieto$aclient_b;
	private Textbox addTieto$acl_type;
	private Textbox addTieto$acln_cat;
	private Textbox addTieto$af_names;
	private Textbox addTieto$asurname;
	private Textbox addTieto$atitle;
	private Textbox addTieto$am_name;
	private Textbox addTieto$ar_street;
	private Textbox addTieto$ar_city;
	private Textbox addTieto$ar_cntry;
	private Textbox addTieto$ausrid;
	private Textbox addTieto$astatus;
	private Textbox addTieto$asearch_name;
	private Textbox addTieto$asex;
	private Textbox addTieto$aserial_no;
	private Textbox addTieto$aissued_by;
	private Textbox blockwnd$txtstopcauses;
	private Textbox addCustomer$add_cst_id;
	private Textbox addCust$ap_post_address;
	private Textbox addCust$ap_passport_serial;
	private Textbox addCust$ap_passport_number;
	private Textbox addCust$ap_passport_place_registration;
	private Textbox addCust$ap_family;
	private Textbox addCust$ap_first_name;
	private Textbox addCust$ap_patronymic;
	private RefCBox addCust$ap_code_birth_region;
	private RefCBox addCust$ap_code_adr_region;
	private RefCBox addCust$ap_code_adr_distr;
	private RefCBox blockwnd$sstopcauses;
	private RefCBox add_everywhere$cln_cat;
	private Textbox add_everywhere$customerId;
	private Datebox addTieto$ab_date;
	private Datebox addTieto$adoc_since;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_birthday;
	private RefCBox add_everywhere$acode_resident;
	private RefCBox add_everywhere$acode_country;
	private RefCBox add_everywhere$ap_code_tax_org;
	private RefCBox add_everywhere$ap_code_citizenship;
	private RefCBox add_everywhere$ap_code_gender;
	private RefCBox add_everywhere$ap_code_nation;
	private RefCBox add_everywhere$ap_code_birth_region;
	private RefCBox add_everywhere$ap_code_birth_distr;
	private RefCBox add_everywhere$ap_type_document;
	private RefCBox add_everywhere$ap_code_adr_region;
	private RefCBox add_everywhere$ap_code_adr_distr;
	private RefCBox add_everywhere$ar_city;
	private Textbox add_everywhere$ap_birth_place;
	private Textbox add_everywhere$ap_post_address;
	private Textbox add_everywhere$ap_passport_serial;
	private Textbox add_everywhere$ap_passport_number;
	private Textbox add_everywhere$ap_passport_place_registration;
	private Textbox add_everywhere$ap_number_tax_registration;
	private Textbox add_everywhere$ap_phone_home;
	private Textbox add_everywhere$ap_phone_mobile;
	private Textbox add_everywhere$ap_email_address;
	private Textbox add_everywhere$ap_inps;
	private Textbox add_everywhere$ap_family;
	private Textbox add_everywhere$ap_first_name;
	private Textbox add_everywhere$ap_patronymic;
	private Textbox add_everywhere$acode_tel;
	private Textbox add_everywhere$aPinfl;
	private Textbox addCustomer$ap_birth_place;
	private Textbox addCustomer$ap_post_address;
	private Textbox addCustomer$ap_passport_serial;
	private Textbox addCustomer$ap_passport_number;
	private Textbox addCustomer$ap_passport_place_registration;
	private Textbox addCustomer$ap_number_tax_registration;
	private Textbox addCustomer$ap_phone_home;
	private Textbox addCustomer$ap_phone_mobile;
	private Textbox addCustomer$ap_email_address;
	private Textbox addCustomer$ap_inps;
	private Textbox addCustomer$ap_family;
	private Textbox addCustomer$ap_first_name;
	private Textbox addCustomer$ap_patronymic;
	private RefCBox addCustomer$acode_resident;
	private RefCBox addCustomer$acode_country;
	private RefCBox addCustomer$ap_code_tax_org;
	private RefCBox addCustomer$ap_code_citizenship;
	private RefCBox addCustomer$ap_code_gender;
	private RefCBox addCustomer$ap_code_nation;
	private RefCBox addCustomer$ap_code_birth_region;
	private RefCBox addCustomer$ap_code_birth_distr;
	private RefCBox addCustomer$ap_type_document;
	private RefCBox addCustomer$ap_code_adr_region;
	private RefCBox addCustomer$ap_code_adr_distr;
	private RefCBox cardTypeSel$cardTypeSelection;
	private Datebox addCustomer$ap_passport_date_registration;
	private Datebox addCustomer$ap_passport_date_expiration;
	private Datebox addCustomer$ap_birthday;
	private Textbox blockMsgWindow$blockMsg;
	private Textbox customers$customerIdSearchTextbox, customers$okBtn;
	private Toolbarbutton bth;
	private Toolbarbutton btb;
	private Toolbarbutton btt;
	private Toolbarbutton btbreak;
	private Toolbarbutton btedit;
	private Toolbarbutton btacc;
	private Toolbarbutton btblock_card;
	private Toolbarbutton btunblock_card;
	// private Toolbarbutton btrefresh_card;
	private Toolbarbutton bt_acc_act;
	private Toolbarbutton addwnd$btn_add;
	private Toolbarbutton bt_refresh_with_new;
	private Toolbarbutton accounts$btn_openCard;
	private Toolbarbutton btn_cancel;
	private Window customers;
	private Listbox customers$customersList;
	private Button btnResetPin;
	private RefCBox addwnd$sproduct;
	public TclientFilter tfilter;
	private Customer current;
	private Customer tcustomer;
	private GlobuzAccount currentGlobuzAccount;
	private CardInfo currentCard;
	private Tclient tietocl;
	private TietoCustomer tcust;
	private Tclient atcust;
	private int _pageSize;
	private int _startPageNumber;
	private int _starttPageNumber;
	private int _startbPageNumber;
	private int _totalSize;
	private Grid addCust$addgrdl;
	private Grid addCust$addgrdr;
	private Grid addTieto$addtgrdl;
	private Vbox tgrd;
	private Grid addTieto$addtgrdr;
	private Grid addCustomer$addgrdl;
	private Grid addCustomer$addgrdr;
	private Grid add_everywhere$addgrdl;
	private Grid add_everywhere$addgrdr;
	private Listbox cardActionsWindow$cardActionsListbox;
	private String un;
	private String pwd;
	private String branch;
	private String alias;
	private String curip;
	private Iframe printwnd$rpframe;
	private RefCBox accwnd$scurracc;
	private Toolbarbutton btrefresh_card_app;
	private Toolbarbutton bt_block_card_acc;
	private Toolbarbutton bt_unblock_card_acc;
	private Toolbarbutton bt_close_card_acc;
	private Toolbarbutton accounts$btn_add;
	private Toolbarbutton accounts$btn_cancel;
	private Button btrefresh_card;
	private Customer cur_HO_customer;
	private Customer cur_branch_customer;
	private boolean is_ti;
	private boolean is_ho;
	private boolean is_br;
	private boolean add_ti;
	private boolean add_ho;
	private boolean add_br;
	private boolean edit_ti;
	private boolean edit_br;
	private boolean fl_edit;
	private boolean edit_agree;
	private String cur_cl_acc;
	private String contract_nmb;
	private int uid;
	private CardInfo blockcard;
	private static HashMap<Integer, Integer> used_ids;
	private int agre_nom_upd;
	private Confirmation_rep_data cfrd;
	private IssuingPortProxy issuingPortProxy;
	private HashMap<String, String> hashMapCurr;
	private int _startpageGlobuz;
	private static HashMap<String, EmpcSettings> settings;
	private static HashMap<String, EmpcSettings> settingsForEach;
	protected boolean retry;
	private Connection c;
	SimpleDateFormat dfExpiry;
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	private Combobox clientType;
	private Toolbarbutton tbtn_add_excel;
	HostnameVerifier allHostsValid;
	private Window uploadedTable;
	private Button uploadedTable$btn_registerAll,
			uploadedTable$btn_deleteExcel;
	private String selectedExcel;
	private Listbox uploadedTable$tempListBox;
	private Combobox uploadedTable$selectedExcel;
	private Toolbarbutton tbtn_register;
	private String bincod;
	private String regex = "[а-яёА-ЯЁ]+";
	final String order_from = Utils.getValue("HUMO_ACC_ORD_FROM");
	final String order_previous = Utils.getValue("HUMO_ACC_ORD_PREVIOUS");
	private String cardProductType;
	final BigDecimal faultMode = new BigDecimal(0);
	static String HUMO_BANK_C;
	static String HUMO_GROUPC;
	static String HUMO_BINCOD;
	private static String HUMO_HOST;
	private static String HUMO_USERNAME;
	private static String HUMO_PASSWORD;
	private static int humoAccOrdFrom;
	private static int humoAccOrdPrevious;
	private static int humoCreditOrdFrom;
	private static int humoCreditOrdPrevious;
	private static int humoCobrandOrdFrom;
	private static int humoCobrandOrdPrevious;
	private static int humoAccOrdVisaFrom;
	private static int humoAccOrdVisaPrevious;
	private static int humoAccOrdHayriyaFrom;
	private static int humoAccOrdHayriyaPrevious;
	private int humoAccOrdMax;
	private Textbox passportNumber;
	private Customer currentCustomer;
	private Timer cardStatusUpdateTimer;
	private ObjectMapper objectMapper = new ObjectMapper();
	// private TimerTask cardStatusUpdateTask;

	static {
		AddCstViewCtrl.settings = new HashMap<String, EmpcSettings>();
		AddCstViewCtrl.settingsForEach = new HashMap<String, EmpcSettings>();
	}

	public AddCstViewCtrl() {

		this.filter = new CustomerFilter();
		this.bfilter = new CustomerFilter();
		this.bmodel = null;
		this.tmodel = null;
		this._needsTotalSizeUpdate = true;
		this.soapEndpointUrl = Utils.getValue("Humo_sms_info_endpoint");
		this.soapAction = String.valueOf(this.soapEndpointUrl) + "/import";
		this.df = new SimpleDateFormat("dd.MM.yyyy");
		this.datef = new SimpleDateFormat("yyyy-MM-dd");
		this.expirySDF = new SimpleDateFormat("MMdd");
		dfExpiry = new SimpleDateFormat("MM/yy");
		this.bth = null;
		this.btb = null;
		this.btt = null;
		this.btbreak = null;
		this.btedit = null;
		this.btacc = null;
		this.btblock_card = null;
		this.btunblock_card = null;
		this.btrefresh_card = null;
		this.btnResetPin = null;
		this.bt_acc_act = null;
		this.bt_refresh_with_new = null;
		this.tfilter = new TclientFilter();
		this.current = new Customer();
		this.tcustomer = new Customer();
		// this.currentGlobuzAccount = new GlobuzAccount();
		this.tietocl = new Tclient();
		this.tcust = new TietoCustomer();
		this.atcust = new Tclient();
		this._pageSize = 20;
		this._startPageNumber = 0;
		this._starttPageNumber = 0;
		this._startbPageNumber = 0;
		this._totalSize = 0;
		this.cur_HO_customer = new Customer();
		this.cur_branch_customer = new Customer();
		this.currentCard = new CardInfo();
		this.currentCustomer = new Customer();
		// this.currentGlobuzAccount = new GlobuzAccount();
		this.is_ti = false;
		this.is_ho = false;
		this.is_br = false;
		this.add_ti = false;
		this.add_ho = false;
		this.add_br = false;
		this.edit_ti = false;
		this.edit_br = false;
		this.fl_edit = false;
		this.edit_agree = false;
		this.blockcard = new CardInfo();
		this.hashMapCurr = new HashMap<String, String>();
		this._startpageGlobuz = 0;
		this.retry = false;
		this.c = null;
		this.cardStatusUpdateTimer = new Timer();
		this.allHostsValid = new HostnameVerifier() {
			@Override
			public boolean verify(final String arg0, final SSLSession arg1) {
				return false;
			}
		};

	}

	public void doAfterCompose(final Component comp) throws Exception, ConnectException, SQLException {
		super.doAfterCompose(comp);
		this.binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);
		this.binder.bindBean("tietocl", (Object) this.tietocl);
		this.binder.loadAll();
		(this.binderGlobuzAccount = new AnnotateDataBinder(comp)).bindBean("currentGlobuzAccount", (Object) this.currentGlobuzAccount);
		this.binderGlobuzAccount.loadAll();
		(this.binderCard = new AnnotateDataBinder(comp)).bindBean("currentCard", (Object) this.currentCard);
		binderCard.loadAll();
		(this.binderCustomer = new AnnotateDataBinder(comp)).bindBean("currentCustomer", (Object) this.currentCustomer);
		binderCustomer.loadAll();
		TclientService.initSettings();
		AddCstViewCtrl.settings = (HashMap<String, EmpcSettings>) TclientService.getSettings();
		String[] parameter = (String[]) this.param.get("ht");
		this.c = ConnectionPool.getConnection();
		if (parameter != null) {
			this._pageSize = Integer.parseInt(parameter[0]) / 60;
			this.branch_customers.setRows(this._pageSize);
			this.bank_customers.setRows(this._pageSize);
		}

		RealCardTimer realCardTimer = new RealCardTimer();
		Timer timerTmsIdTimer = new Timer();
		timerTmsIdTimer.schedule(realCardTimer, 10800000);

		this.bfilter.setState(2);
		this.filter.setState(2);
		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		this.bfilter.setBranch(this.branch);
		this.filter.setBranch(this.branch);
		this.tcust.setBranch(this.branch);
		this.contract_nmb = null;
		this.hashMapCurr = CustomerService.getCurr(this.alias);
		this.add_everywhere$ap_type_document.setModel((ListModel) new ListModelList((Collection) CustomerService.getType_document(this.alias)));
		this.add_everywhere$ap_code_citizenship.setModel((ListModel) new ListModelList((Collection) CustomerService.getCountry(this.alias)));
		this.add_everywhere$acode_country.setModel((ListModel) new ListModelList((Collection) CustomerService.getCountry(this.alias)));
		this.add_everywhere$ap_code_gender.setModel((ListModel) new ListModelList((Collection) Utils.getGender(this.alias)));
		// this.add_everywhere$ap_code_product.setModel((ListModel)new
		// ListModelList((Collection)Utils.getProduct(this.alias)));
		this.add_everywhere$ap_code_nation.setModel((ListModel) new ListModelList((Collection) Utils.getNation(this.alias)));
		this.add_everywhere$ap_code_adr_distr.setModel((ListModel) new ListModelList((Collection) CustomerService.getDistr(this.alias)));
		this.add_everywhere$acode_resident.setModel((ListModel) new ListModelList((Collection) Utils.getRezCl(this.alias)));
		this.addwnd$sproduct.setModel((ListModel) new ListModelList((Collection) CustomerService.getProduct(this.alias)));
		this.add_everywhere$ap_code_adr_region.setModel((ListModel) new ListModelList((Collection) CustomerService.getRegion(this.alias)));
		this.add_everywhere$ar_city.setModel((ListModel) new ListModelList((Collection) CustomerService.getRegion(this.alias)));
		this.add_everywhere$ap_code_birth_distr.setModel((ListModel) new ListModelList((Collection) CustomerService.getDistr(this.alias)));
		this.add_everywhere$ap_code_birth_region.setModel((ListModel) new ListModelList((Collection) CustomerService.getRegion(this.alias)));
		this.add_everywhere$ap_code_tax_org.setModel((ListModel) new ListModelList((Collection) CustomerService.getTax(this.alias)));
		this.add_everywhere$cln_cat.setModel((ListModel) new ListModelList((Collection) CustomerService.getClnCat(this.alias)));
		// this.add_everywhere$customerId.setModel((ListModel) new
		// ListModelList((Collection) CustomerService.getCustomerId(this.alias,
		// this.branch)));
		HUMO_BANK_C = ConnectionPool.getValue("HUMO_BANK_C");
		HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC");
		HUMO_HOST = ConnectionPool.getValue("HUMO_HOST");
		HUMO_USERNAME = ConnectionPool.getValue("HUMO_USERNAME");
		HUMO_PASSWORD = ConnectionPool.getValue("HUMO_PASSWORD");
		cardTypeSel$cardTypeSelection.setModel((ListModel) new ListModelList((Collection) CustomerService.getCardTypes(this.alias, HUMO_BANK_C)));
		humoAccOrdFrom = Utils.getValue("HUMO_ACC_ORD_FROM").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_FROM"));
		humoAccOrdPrevious = Utils.getValue("HUMO_ACC_ORD_PREVIOUS").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_PREVIOUS"));
		humoCreditOrdFrom = Utils.getValue("HUMO_ACC_ORD_CREDIT_FROM").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_FROM"));
		humoCreditOrdPrevious = Utils.getValue("HUMO_ACC_ORD_CREDIT_PREVIOUS").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_CREDIT_PREVIOUS"));
		humoCobrandOrdFrom = Utils.getValue("HUMO_ACC_ORD_COBRAND_FROM").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_COBRAND_FROM"));
		humoCobrandOrdPrevious = Utils.getValue("HUMO_ACC_ORD_COBRAND_PREVIOUS").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_COBRAND_PREVIOUS"));
		humoAccOrdVisaFrom = Utils.getValue("HUMO_ACC_ORD_VISA_FROM").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_VISA_FROM"));
		humoAccOrdVisaPrevious = Utils.getValue("HUMO_ACC_ORD_VISA_PREVIOUS").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_VISA_PREVIOUS"));
		humoAccOrdHayriyaFrom = Utils.getValue("HUMO_ACC_ORD_HAYRIYA_FROM").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_HAYRIYA_FROM"));
		humoAccOrdHayriyaPrevious = Utils.getValue("HUMO_ACC_ORD_HAYRIYA_PREVIOUS").isEmpty() ? 0
				: Integer.parseInt(Utils.getValue("HUMO_ACC_ORD_HAYRIYA_PREVIOUS"));
		int[] accOrds = new int[] { humoAccOrdPrevious, humoCreditOrdPrevious,
				humoCobrandOrdPrevious, humoAccOrdVisaPrevious };
		List accOrdsList = Arrays.asList(ArrayUtils.toObject(accOrds));
		humoAccOrdMax = Collections.max(accOrdsList);
		ISLogger.getLogger().error("humoAccOrdMax: " + humoAccOrdMax);
		try {
			this.issuingPortProxy = initNp(this.alias);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			Messagebox.show("[Coonection Problem]=>" + e.getLocalizedMessage(), e.getMessage(), 514, "z-msgbox z-msgbox-error", (EventListener) new EventListener() {
				public void onEvent(final Event event) throws Exception {
					final int answer = (Integer) event.getData();
					if (answer == 512) {
						AddCstViewCtrl.access$2(AddCstViewCtrl.this, initNp(AddCstViewCtrl.this.alias));
					}
				}
			});
		}

		customers$customersList.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				final Customer pCustomer = (Customer) data;
				row.setValue((pCustomer == null) ? "" : pCustomer);
				row.appendChild((Component) new Listcell((pCustomer.getId_client() == null) ? ""
						: pCustomer.getId_client()));
				row.appendChild((Component) new Listcell((pCustomer.getName() == null) ? ""
						: pCustomer.getName()));
			}
		});

		uploadedTable$tempListBox.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			// @SuppressWarnings("unchecked")
			public void render(final Listitem row, final Object data) throws Exception {
				final TempClient tempClient = (TempClient) data;

				row.setValue((tempClient == null) ? "" : tempClient);
				row.appendChild((Component) new Listcell((tempClient.getBranch() == null) ? ""
						: tempClient.getBranch()));
				row.appendChild((Component) new Listcell((tempClient.getClient_id() == null) ? ""
						: tempClient.getClient_id()));
				row.appendChild((Component) new Listcell((tempClient.getState() == null) ? ""
						: tempClient.getState()));
				row.appendChild((Component) new Listcell((tempClient.getText() == null) ? ""
						: tempClient.getText()));
				row.appendChild((Component) new Listcell((tempClient.getExcel() == null) ? ""
						: tempClient.getExcel()));
			}
		});
		cardActionsWindow$cardActionsListbox.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				final CardActions cardActions = (CardActions) data;
				row.setValue((cardActions == null) ? "" : cardActions);
				row.appendChild((Component) new Listcell(cardActions.getUser() == null ? ""
						: cardActions.getUser()));
				row.appendChild((Component) new Listcell(cardActions.getDate() == null ? ""
						: cardActions.getDate()));
				row.appendChild((Component) new Listcell(cardActions.getAction() == null ? ""
						: cardActions.getAction()));
			}
		});
		this.accListRender();
		this.addAccRender();
		this.branchCustomersRender();
		this.tietoCustomersRender();
		this.onClick$tbtn_search();

		parameter = (String[]) param.get("search_clients");
		if (parameter != null) {
			this.branch = (String) this.session.getAttribute("branch");
			this.alias = (String) this.session.getAttribute("alias");
			blockMsgWindow.setVisible(false);
			addCust.setVisible(false);
			uploadedTable.setVisible(false);
			add_everywhere.setVisible(false);
			addTieto.setVisible(false);
			printwnd.setVisible(false);
			accwnd.setVisible(false);
			addCustomer.setVisible(false);
			addwnd.setVisible(false);
			cardTypeSel.setVisible(false);
			accounts.setVisible(false);
			blockwnd.setVisible(false);
			cardActionsWindow.setVisible(false);
			bt_rest_confirmation_wnd.setVisible(false);
			this.showCardRender(false);
			System.out.println("params enter");
			System.out.println("params size: " + parameter.length);
			// System.out.println(objectMapper.writeValueAsString(parameter));
			System.out.println("param[0]: " + parameter[0]);
			tietocl.setClient(Utils.getValueFromSql("select client from bf_empc_clients where client_b = '"
					+ this.session.getAttribute("branch")
					+ parameter[0]
					+ "' and rownum = 1", alias));
			System.out.println("tietoclient: " + tietocl.getClient());
			this.issuingPortProxy = initNp(this.alias);
			this.tcust.setTietoCustomerId(this.tietocl.getClient());
			List<AccInfo> showCardsList = TclientService.getAccInfoShowCards(this.branch, this.tietocl.getClient(), this.alias, this.issuingPortProxy);
			this.accGrid.setModel((ListModel) new BindingListModelList((List) this.makeList(showCardsList), false));
			btn_cancel.setVisible(false);
			// show_cards.setClosable(false);
			leftToolbar.setVisible(false);
			leftColumn.setWidth("0%");
			// show_cards.setWidth("125%");
			AddCstViewCtrl.access$103(AddCstViewCtrl.this, new Button());
			btrefresh_card.setVisible(false);
			btrefresh_card.setDisabled(true);
			tgrd.setVisible(false);
			show_cards.setVisible(true);
			btrefresh_card.setVisible(false);
			btrefresh_card.setDisabled(true);
			System.out.println("showCards: " + accGrid.isVisible());
			// return;
		}
	}

	private void accListRender() {

		this.accounts$accGrid.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				final GlobuzAccount pAccInfo = (GlobuzAccount) data;
				final List<GlobuzAccountService.actions_for_acc> act = (List<GlobuzAccountService.actions_for_acc>) GlobuzAccountService.getactions_for_acc(pAccInfo.getState(), AddCstViewCtrl.this.alias);
				final Listcell h_edit_cell = new Listcell();
				for (int i = 0; i < act.size(); ++i) {
					AddCstViewCtrl.access$3(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.bt_acc_act.setLabel(act.get(i).getName());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("deal_group_id", (Object) act.get(i).getDeal_group());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("deal_id", (Object) act.get(i).getDeal_id());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("action_id", (Object) act.get(i).getAction_id());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("acc", (Object) pAccInfo);
					AddCstViewCtrl.this.bt_acc_act.setAttribute("capt", (Object) act.get(i).getName());
					AddCstViewCtrl.this.bt_acc_act.setImage("/images/down3.png");
					AddCstViewCtrl.this.bt_acc_act.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							final Res res = GlobuzAccountService.doAction_acc_ho(AddCstViewCtrl.this.un, AddCstViewCtrl.this.pwd, (Integer) event.getTarget().getAttribute("deal_group_id"), (Integer) event.getTarget().getAttribute("deal_id"), (GlobuzAccount) event.getTarget().getAttribute("acc"), (Integer) event.getTarget().getAttribute("action_id"), AddCstViewCtrl.this.alias, Boolean.valueOf(true));
							if (res.getCode() != 0) {
								alert(res.getName());
								return;
							}
							final GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
							acfilter.setClient(AddCstViewCtrl.this.cur_branch_customer.getId_client());
							acfilter.setAcc_bal("2261");
							acfilter.setBranch(AddCstViewCtrl.this.branch);
							acfilter.setCurrency("000");
							final com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, (Object) acfilter, AddCstViewCtrl.this.alias);
							AddCstViewCtrl.this.accounts$accGrid.setModel((ListModel) acc_model);
						}
					});
					if (act.get(i).getAction_id() == 1
							|| act.get(i).getAction_id() == 2
							|| act.get(i).getAction_id() == 20) {
						h_edit_cell.appendChild((Component) AddCstViewCtrl.this.bt_acc_act);
					}
				}
				/*
				 * final Listcell h_add_acc = new Listcell(); if
				 * (pAccInfo.getState() == 2) {
				 * AddCstViewCtrl.access$12(AddCstViewCtrl.this, new
				 * Toolbarbutton());
				 * AddCstViewCtrl.this.bt_acc_insert.setLabel("Прикрепить счет"
				 * ); AddCstViewCtrl.this.bt_acc_insert.setAttribute("acc",
				 * (Object) pAccInfo);
				 * AddCstViewCtrl.this.bt_acc_insert.setImage
				 * ("/images/credit_card1.png");
				 * AddCstViewCtrl.this.bt_acc_insert.addEventListener("onClick",
				 * (EventListener) new EventListener() { public void
				 * onEvent(final Event event) throws Exception { final
				 * GlobuzAccount acc = (GlobuzAccount)
				 * event.getTarget().getAttribute("acc");
				 * ISLogger.getLogger().error
				 * ("VER10 Prikrepit acc.getId().substring(9, 17): " +
				 * acc.getId().substring(9, 17) +
				 * " cur_branch_customer.getId_client(): " +
				 * cur_branch_customer.getId_client()); if
				 * (acc.getId().substring(9,
				 * 17).equals(cur_branch_customer.getId_client())) { final Res
				 * res =
				 * CustomerService.update_lnk_set_acc(AddCstViewCtrl.this.un,
				 * AddCstViewCtrl.this.pwd, AddCstViewCtrl.this.branch,
				 * AddCstViewCtrl.this.cur_branch_customer.getId_client(), acc,
				 * AddCstViewCtrl.this.alias,
				 * AddCstViewCtrl.this.issuingPortProxy); if (res.getCode() !=
				 * 1) { alert(res.getName()); return; }
				 * AddCstViewCtrl.this.refreshModel
				 * (AddCstViewCtrl.this._startPageNumber);
				 * AddCstViewCtrl.this.accounts.setVisible(false); } else {
				 * alert("Ошибка! Уникальные ID не совпадают."); } } });
				 * //h_add_acc.appendChild((Component)
				 * AddCstViewCtrl.this.bt_acc_insert); } final Listcell
				 * h_add_acc_cell = new Listcell();
				 * 
				 * if (pAccInfo.getState() == 2) {
				 * AddCstViewCtrl.access$3(AddCstViewCtrl.this, new
				 * Toolbarbutton());
				 * AddCstViewCtrl.this.bt_acc_act.setLabel("Открыть карту!!");
				 * AddCstViewCtrl.this.bt_acc_act.setAttribute("acc", (Object)
				 * pAccInfo); AddCstViewCtrl.this.bt_acc_act.setImage(
				 * "/images/credit_card1.png");
				 * AddCstViewCtrl.this.bt_acc_act.addEventListener("onClick",
				 * (EventListener) new EventListener() { public void
				 * onEvent(final Event event) throws Exception { final
				 * GlobuzAccount acc = (GlobuzAccount)
				 * event.getTarget().getAttribute("acc");
				 * 
				 * if (!CustomerService.checkAccount(acc.getId(), branch,
				 * alias)) {
				 * 
				 * final Res res =
				 * CustomerService.update_lnk_set_acc(AddCstViewCtrl.this.un,
				 * AddCstViewCtrl.this.pwd, AddCstViewCtrl.this.branch,
				 * AddCstViewCtrl.this.cur_branch_customer.getId_client(), acc,
				 * AddCstViewCtrl.this.alias,
				 * AddCstViewCtrl.this.issuingPortProxy);
				 * System.out.println("ACC TRANZ ACCT:" + acc.getId());
				 * 
				 * if (res.getCode() == 1) { final Customer lg_c =
				 * CustomerService
				 * .getCustomerById(AddCstViewCtrl.this.cur_branch_customer
				 * .getId_client(), AddCstViewCtrl.this.branch,
				 * AddCstViewCtrl.this.alias); lg_c.getName(); Res resAgreement
				 * = new Res(); System.out.println();
				 * System.out.println("Issuing Port Proxy" +
				 * AddCstViewCtrl.this.issuingPortProxy); final int cnt =
				 * CustomerService.getCountCards(AddCstViewCtrl.this.tietocl,
				 * currentGlobuzAccount); if (cnt == 0) { //resAgreement =
				 * CustomerService.addNewAgreement(settingsForEach,
				 * AddCstViewCtrl.this.branch, lg_c, acc,
				 * AddCstViewCtrl.this.tietocl, AddCstViewCtrl.this.alias,
				 * AddCstViewCtrl.this.issuingPortProxy);
				 * 
				 * AddCstViewCtrl.access$7(AddCstViewCtrl.this,
				 * resAgreement.getName()); if (resAgreement.getCode() != 1) {
				 * return; }
				 * AddCstViewCtrl.this.refreshModel(AddCstViewCtrl.this
				 * ._startPageNumber);
				 * AddCstViewCtrl.this.accounts.setVisible(false); } else {
				 * Messagebox.show(
				 * "У клиента уже есть 1 или более карт, вы уверены что хотите открыть ещё?"
				 * , "Предупреждение", 3, "z-msgbox z-msgbox-exclamation",
				 * (EventListener) new EventListener() { public void
				 * onEvent(final Event event) throws Exception {
				 * System.out.println("name = " + event.getName()); if
				 * (event.getName().equals("onOK")) { Res resAgreement = new
				 * Res(); //resAgreement =
				 * CustomerService.addNewAgreement(settingsForEach,
				 * AddCstViewCtrl.this.branch, lg_c, acc,
				 * AddCstViewCtrl.this.tietocl, AddCstViewCtrl.this.alias,
				 * AddCstViewCtrl.this.issuingPortProxy);
				 * AddCstViewCtrl.access$7(AddCstViewCtrl.this,
				 * resAgreement.getName()); if (resAgreement.getCode() != 1) {
				 * return; }
				 * AddCstViewCtrl.this.refreshModel(AddCstViewCtrl.this
				 * ._startPageNumber);
				 * AddCstViewCtrl.this.accounts.setVisible(false); } } }); }
				 * return; } AddCstViewCtrl.access$7(AddCstViewCtrl.this,
				 * res.getName()); } else {
				 * alert("Данный счёт уже зарегистрирован в UZCARD"); return; }
				 * } }); // //h_add_acc_cell.appendChild((Component)
				 * AddCstViewCtrl.this.bt_acc_act); }
				 */

				row.setValue((Object) pAccInfo);
				row.appendChild((Component) new Listcell(pAccInfo.getBranch()));
				row.appendChild((Component) new Listcell(pAccInfo.getClient()));
				row.appendChild((Component) new Listcell(pAccInfo.getId()));
				row.appendChild((Component) new Listcell(AddCstViewCtrl.getFnm(pAccInfo)));
				row.appendChild((Component) new Listcell(pAccInfo.getCurrency()));
				row.appendChild((Component) new Listcell((pAccInfo.getDate_open() == null) ? ""
						: AddCstViewCtrl.this.df.format(pAccInfo.getDate_open())));
				row.appendChild((Component) new Listcell(pAccInfo.getId_order()));
				row.appendChild((Component) new Listcell(GlobuzAccountService.get_account_state_caption(pAccInfo.getState(), AddCstViewCtrl.this.alias)));
				/*
				 * row.appendChild((Component) h_add_acc);
				 * row.appendChild((Component) h_add_acc_cell);
				 */
			}
		});
	}

	public void getData() {
	}

	public void onClick$btnCloseCardActionsWindow$cardActionsWindow() {
		cardActionsWindow.setVisible(false);
	}

	public void onCtrlKey$ap_family(Event event) {
		customers.setVisible(true);
	}

	public void onCtrlKey$customerId$add_everywhere(Event event) {
		System.out.println("ctrlKey");
		customers.setVisible(true);
	}

	public void onClick$okBtn$customers() {
		customers$customersList.setModel(new BindingListModelList(CustomerService.customersSearch(customers$customerIdSearchTextbox.getValue(), branch, alias), false));
	}

	public void onClick$blockMsgCancel$blockMsgWindow() {
		blockMsgWindow.setVisible(false);
	}

	// Отправка сообщения о причине блокировки //АГРО, Зираат
	public void onClick$blockMsgOk$blockMsgWindow() {
		ISLogger.getLogger().error("BLOCK MSG");
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(blockMsgWindow$blockMsg.getValue());
		String reasonSubstr = "";
		if (blockMsgWindow$blockMsg.getValue().equals("")
				|| blockMsgWindow$blockMsg.getValue().length() < 6) {
			alert("Слишком короткое сообщение");
		}
		if (m.find()) {
			// Кириллица - 57 символов макс. + номер карты
			reasonSubstr = blockMsgWindow$blockMsg.getValue().length() > 57 ? blockMsgWindow$blockMsg.getValue().substring(0, 57)
					: blockMsgWindow$blockMsg.getValue();
		} else {
			// Латиница - 147 символов макс. + номер карты
			reasonSubstr = blockMsgWindow$blockMsg.getValue().length() > 147 ? blockMsgWindow$blockMsg.getValue().substring(0, 147)
					: blockMsgWindow$blockMsg.getValue();
		}
		ISLogger.getLogger().error("reason after substring: " + reasonSubstr);

		if (currentCard != null) {
			final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			OperationResponseInfo orInfo = null;
			final RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();

			try {
				connectionInfo.setBANK_C(settings.get(branch).getBank_c());
				connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
				connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
				parameters.setCARD(currentCard.getCARD());
				parameters.setSTOP_CAUSE("1");
				parameters.setTEXT("Stolen");
				parameters.setBANK_C(settings.get(branch).getBank_c());
				parameters.setGROUPC(settings.get(branch).getGroup_c());
				orInfo = AddCstViewCtrl.this.issuingPortProxy.addCardToStop(connectionInfo, parameters);
				if (orInfo.getResponse_code().intValue() != 0) {
					alert(String.valueOf(orInfo.getError_action()) + "\r\n"
							+ orInfo.getError_description());
				} else {
					currentCard.setSTOP_CAUSE("2");
					currentCard.setSTATUS("2");
					currentCard.setSTATUS2("2");
					CustomerService.updateStateCard(currentCard);
					String phone = CustomerService.getCardSmsPhoneNumber(currentCard.getCARD()) == null ? " "
							: CustomerService.getCardSmsPhoneNumber(currentCard.getCARD()).toString();
					if (phone.equals(" ")) {
						phone = current.getP_phone_mobile();
					}
					ISLogger.getLogger().error("CURRENT CARD BLOCK MSG: "
							+ currentCard.getCARD());
					ISLogger.getLogger().error("phone: " + phone);
					String reason = reasonSubstr + " "
							+ currentCard.getCARD().substring(0, 4) + "****"
							+ currentCard.getCARD().substring(12, 16);
					ISLogger.getLogger().error("reason: " + reason);
					CustomerService.sendBlockReason(phone, reason, un, pwd, alias);
					ISLogger.getLogger().error("curip: " + curip);
					CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Карта No ["
							+ currentCard.getCARD()
							+ "] заблокирована по причине " + reasonSubstr + ""));
					reason = "";
					blockMsgWindow.setVisible(false);
				}
			} catch (Exception e) {
				LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
				alert(e.getMessage());
				e.printStackTrace();
				return;
			} finally {
				currentCard = null;
				AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
				AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
			}
			AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
			AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
		} else {
			alert("Карта не выбрана");
		}
		currentCard = null;
	}

	private void showCardRender(final boolean all) {
		this.accGrid.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				final CardInfo cardInfo = (CardInfo) data;
				row.setValue((Object) cardInfo);
				final Listcell bl_unbl = new Listcell();
				final Listcell acc_act = new Listcell();
				Toolbarbutton cardActionsHistory = new Toolbarbutton();
				cardActionsHistory.setImage("/images/folder.png");
				cardActionsHistory.setLabel("История действий по карте");
				cardActionsHistory.setDisabled(false);
				// AddCstViewCtrl.this.btrefresh_card.setWidth("100%");
				AddCstViewCtrl.access$21(AddCstViewCtrl.this, new Toolbarbutton());
				final Toolbarbutton bt_rest_confirmation = new Toolbarbutton();
				AddCstViewCtrl.access$22(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.access$23(AddCstViewCtrl.this, new Toolbarbutton());
				// AddCstViewCtrl.access$24(AddCstViewCtrl.this, new
				// Toolbarbutton());
				AddCstViewCtrl.access$25(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.access$26(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.access$27(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.access$28(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.access$103(AddCstViewCtrl.this, new Button());
				AddCstViewCtrl.this.btrefresh_card_app.setWidth("100%");
				AddCstViewCtrl.this.bt_block_card_acc.setWidth("100%");
				AddCstViewCtrl.this.bt_close_card_acc.setWidth("100%");
				// AddCstViewCtrl.this.btrefresh_card.setWidth("100%");
				AddCstViewCtrl.this.bt_unblock_card_acc.setWidth("100%");
				AddCstViewCtrl.this.bt_refresh_with_new.setWidth("100%");
				// cardActionsHistory.setWidth("100%");
				cardActionsHistory.setDisabled(false);
				bt_rest_confirmation.setWidth("100%");
				AddCstViewCtrl.this.btunblock_card.setImage("/images/+.png");
				AddCstViewCtrl.this.btblock_card.setImage("/images/-.png");
				AddCstViewCtrl.this.btrefresh_card.setLabel("Перевыпустить карту");
				AddCstViewCtrl.this.btunblock_card.setLabel("Разблокировать карту");

				AddCstViewCtrl.this.btunblock_card.setDisabled(false);
				AddCstViewCtrl.this.btblock_card.setLabel("Блокировать карту");
				AddCstViewCtrl.this.btblock_card.setDisabled(false);
				AddCstViewCtrl.this.btblock_card.setVisible(true);
				bt_rest_confirmation.setLabel("Документы о счете");
				AddCstViewCtrl.this.bt_block_card_acc.setLabel("Перевести в неактивный");
				AddCstViewCtrl.this.bt_block_card_acc.setDisabled(false);
				AddCstViewCtrl.this.bt_block_card_acc.setImage("/images/+.png");
				AddCstViewCtrl.this.bt_close_card_acc.setImage("/images/+.png");
				AddCstViewCtrl.this.bt_close_card_acc.setLabel("Закрыть");
				AddCstViewCtrl.this.bt_unblock_card_acc.setLabel("Перевести в активный");
				AddCstViewCtrl.this.bt_unblock_card_acc.setDisabled(false);
				AddCstViewCtrl.this.bt_unblock_card_acc.setImage("/images/+.png");
				// AddCstViewCtrl.this.btrefresh_card.setImage("/images/front1.png");
				// AddCstViewCtrl.this.btrefresh_card.setLabel("\u041f\u0435\u0440\u0435\u0432\u044b\u043f\u0443\u0441\u0442\u0438\u0442\u044c \u043a\u0430\u0440\u0442\u0443");
				AddCstViewCtrl.this.btrefresh_card_app.setImage("/images/file.png");
				bt_rest_confirmation.setImage("/images/file.png");
				AddCstViewCtrl.this.btrefresh_card_app.setLabel("Заявление на перевыпуск карты");
				AddCstViewCtrl.this.bt_refresh_with_new.setImage("/images/front1.png");
				AddCstViewCtrl.this.bt_refresh_with_new.setLabel("Перевыпустить карту с новыми данными");
				AddCstViewCtrl.this.bt_refresh_with_new.setVisible(true);
				AddCstViewCtrl.this.btrefresh_card_app.setVisible(true);
				AddCstViewCtrl.this.bt_refresh_with_new.setDisabled(false);
				AddCstViewCtrl.this.btrefresh_card_app.setDisabled(false);
				AddCstViewCtrl.this.btblock_card.setAttribute("card", (Object) cardInfo);
				AddCstViewCtrl.this.btunblock_card.setAttribute("card", (Object) cardInfo);
				cardActionsHistory.setAttribute("card", (Object) cardInfo);
				AddCstViewCtrl.this.btrefresh_card_app.setAttribute("acc", (Object) cardInfo);
				AddCstViewCtrl.this.bt_block_card_acc.setAttribute("card", (Object) cardInfo);
				AddCstViewCtrl.this.bt_close_card_acc.setAttribute("card", (Object) cardInfo);
				AddCstViewCtrl.this.bt_unblock_card_acc.setAttribute("card", (Object) cardInfo);
				bt_rest_confirmation.setAttribute("acc", (Object) cardInfo);
				cardActionsHistory.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						if (cardInfo == null) {
							alert("Выберите карту");
						} else {
							List<CardActions> cardActions = CustomerService.getCardActions(cardInfo.getCARD(), branch);
							if (cardActions == null) {
								alert("Нет информации по действиям с данной картой");
								return;
							} else {
								cardActionsWindow$cardActionsListbox.setModel(new BindingListModelList(cardActions, true));
								cardActionsWindow.setVisible(true);
							}
						}
					}
				});

				// Блокировка карты
				AddCstViewCtrl.this.btblock_card.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						if (currentCard == null) {
							alert("Выберите карту");
							return;
						}
						if (settings.get(branch).getBank_c().equals("03")
								|| settings.get(branch).getBank_c().equals("29")) {
							blockMsgWindow.setVisible(true);
						} else {
							final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
							OperationResponseInfo orInfo = null;
							final RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();
							try {
								connectionInfo.setBANK_C(HUMO_BANK_C);
								connectionInfo.setGROUPC(HUMO_GROUPC);
								connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
								AddCstViewCtrl.access$39(AddCstViewCtrl.this, (CardInfo) event.getTarget().getAttribute("card"));
								parameters.setCARD(cardInfo.getCARD());
								parameters.setSTOP_CAUSE("1");
								parameters.setTEXT("Stolen");
								parameters.setBANK_C(HUMO_BANK_C);
								parameters.setGROUPC(HUMO_GROUPC);
								ISLogger.getLogger().error("request addCardToStop "
										+ "\n"
										+ objectMapper.writeValueAsString(connectionInfo)
										+ "\n"
										+ objectMapper.writeValueAsString(parameters));
								orInfo = issuingPortProxy.addCardToStop(connectionInfo, parameters);
								ISLogger.getLogger().error("response addCardToStop: "
										+ "\n"
										+ objectMapper.writeValueAsString(orInfo));
								if (orInfo.getResponse_code().intValue() != 0) {
									alert(String.valueOf(orInfo.getError_action())
											+ "\r\n"
											+ orInfo.getError_description());
								} else {
									cardInfo.setSTOP_CAUSE("2");
									cardInfo.setSTATUS("2");
									cardInfo.setSTATUS2("2");
									CustomerService.updateStateCard(cardInfo);
									CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Блокировка карты No ["
											+ cardInfo.getCARD()));
								}
							} catch (Exception e) {
								ISLogger.getLogger().error("addCardToStop error: ", e);
								alert(e.getMessage());
								e.printStackTrace();
								return;
							} finally {
								AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
								AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
							}
							AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
							AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
						}
					}
				});

				// Разблокировка карты
				AddCstViewCtrl.this.btunblock_card.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
						OperationResponseInfo orInfo = null;
						final RowType_RemoveCardFromStop_Request parameters = new RowType_RemoveCardFromStop_Request();
						try {
							connectionInfo.setBANK_C(HUMO_BANK_C);
							connectionInfo.setGROUPC(HUMO_GROUPC);
							connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
							parameters.setBANK_C(HUMO_BANK_C);
							parameters.setGROUPC(HUMO_GROUPC);
							AddCstViewCtrl.access$39(AddCstViewCtrl.this, (CardInfo) event.getTarget().getAttribute("card"));
							parameters.setCARD(cardInfo.getCARD());
							parameters.setSTOP_CAUSE("0");
							parameters.setTEXT("Active");
							ISLogger.getLogger().error("removeCardFromStop request "
									+ "\n"
									+ objectMapper.writeValueAsString(connectionInfo)
									+ "\n"
									+ objectMapper.writeValueAsString(parameters));
							orInfo = issuingPortProxy.removeCardFromStop(connectionInfo, parameters);
							ISLogger.getLogger().error("removeCardFromStop response "
									+ "\n"
									+ objectMapper.writeValueAsString(orInfo));
							if (orInfo.getResponse_code().intValue() != 0) {
								alert(String.valueOf(orInfo.getError_action())
										+ "\r\n"
										+ orInfo.getError_description());
							} else {
								cardInfo.setSTATUS("0");
								cardInfo.setSTATUS2("0");
								CustomerService.updateStateCard(cardInfo);
								CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Разблокировка карты No ["
										+ cardInfo.getCARD()));
							}
						} catch (Exception e) {
							LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
							alert(e.getMessage());
							e.printStackTrace();
							return;
						} finally {
							AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
							AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
						}
						AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
						AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
					}
				});
				bt_rest_confirmation.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.access$41(AddCstViewCtrl.this, new Confirmation_rep_data());
						AddCstViewCtrl.this.cfrd.cardInfo = (CardInfo) event.getTarget().getAttribute("acc");
						AddCstViewCtrl.this.bt_rest_confirmation_wnd.setVisible(true);
					}
				});
				if (all == false) {
					bt_block_card_acc.setVisible(false);
				}

				// Блокировка счёта, перевод счёта в состояние "неактивный"
				AddCstViewCtrl.this.bt_block_card_acc.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						ISLogger.getLogger().error((Object) "onEvent2 CardInfo");
						String card = "";
						final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
						final RowType_DormantAccountByCard_Request dac = new RowType_DormantAccountByCard_Request();
						try {
							connectionInfo.setBANK_C(HUMO_BANK_C);
							connectionInfo.setGROUPC(HUMO_GROUPC);
							connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
							dac.setCARD(card);
							dac.setDORMANT_MODE("0");
							dac.setINSTL_INTER(BigDecimal.valueOf(0L));
							dac.setBANK_C(HUMO_BANK_C);
							dac.setGROUPC(HUMO_GROUPC);
							dac.setTEXT("["
									+ AddCstViewCtrl.this.df.format(new Date())
									+ "]Blk by " + AddCstViewCtrl.this.branch);
							ISLogger.getLogger().error("dormantAccountByCard request "
									+ "\n"
									+ objectMapper.writeValueAsString(connectionInfo)
									+ "\n"
									+ objectMapper.writeValueAsString(dac));
							final OperationResponseInfo resp = issuingPortProxy.dormantAccountByCard(connectionInfo, dac);
							ISLogger.getLogger().error("dormantAccountByCard response: "
									+ "\n"
									+ objectMapper.writeValueAsString(resp));
							if (resp.getResponse_code().intValue() != 0) {
								alert(String.valueOf(resp.getError_action())
										+ " \n\r "
										+ resp.getError_description());
								return;
							}
							alert("Статус счета изменен на неактивный");
						} catch (Exception e) {
							LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
							alert(e.getMessage());
							e.printStackTrace();
							return;
						} finally {
							AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
							AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
						}
						AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
						AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
					}
				});
				if (all == false) {
					bt_unblock_card_acc.setVisible(false);
				}

				// Разблокировка счёта
				AddCstViewCtrl.this.bt_unblock_card_acc.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						ISLogger.getLogger().error((Object) "onEvent2 CardInfo");
						final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
						final RowType_ActivateAccountByCard_Request dac = new RowType_ActivateAccountByCard_Request();
						try {
							TclientService.initSettingsByBin(branch, CustomerService.getBincode(cardInfo.getCARD()));
							final HashMap<String, EmpcSettings> settingsForEach = (HashMap<String, EmpcSettings>) TclientService.getSettingsByBin();
							connectionInfo.setBANK_C(HUMO_BANK_C);
							connectionInfo.setGROUPC(HUMO_GROUPC);
							connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
							dac.setCARD(((CardInfo) event.getTarget().getAttribute("card")).getCARD());
							dac.setBANK_C(HUMO_BANK_C);
							dac.setGROUPC(HUMO_GROUPC);
							dac.setTEXT("["
									+ AddCstViewCtrl.this.df.format(new Date())
									+ "]activate by "
									+ AddCstViewCtrl.this.branch);
							ISLogger.getLogger().error("activateAccountByCard request: "
									+ "\n"
									+ objectMapper.writeValueAsString(connectionInfo)
									+ "\n"
									+ objectMapper.writeValueAsString(dac));
							final OperationResponseInfo resp = AddCstViewCtrl.this.issuingPortProxy.activateAccountByCard(connectionInfo, dac);
							ISLogger.getLogger().error("activateAccountByCard response: "
									+ "\n"
									+ objectMapper.writeValueAsString(resp));
							if (resp.getResponse_code().intValue() != 0) {
								alert(String.valueOf(resp.getError_action())
										+ " \n\r "
										+ resp.getError_description());
								return;
							}
							alert("Статус счета изменен на активный");
						} catch (Exception e) {
							LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
							alert(e.getMessage());
							e.printStackTrace();
							return;
						} finally {
							AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
							AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
						}
						AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
						AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
					}
				});
				if (all == false) {
					bt_close_card_acc.setVisible(false);
				}

				// Закрытие счёта. Только для неактивных счетов
				AddCstViewCtrl.this.bt_close_card_acc.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
						final RowType_CloseAccount_Request dac = new RowType_CloseAccount_Request();
						try {
							connectionInfo.setBANK_C(HUMO_BANK_C);
							connectionInfo.setGROUPC(HUMO_GROUPC);
							connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
							dac.setCARD_ACCT(((CardInfo) event.getTarget().getAttribute("card")).getCARD_ACCT());
							dac.setCCY(((CardInfo) event.getTarget().getAttribute("card")).getBank_account_Ccy());
							dac.setCOMMENT("["
									+ AddCstViewCtrl.this.df.format(new Date())
									+ "]Closed by "
									+ AddCstViewCtrl.this.branch);
							ISLogger.getLogger().error("closeAccount request "
									+ "\n"
									+ objectMapper.writeValueAsString(connectionInfo)
									+ "\n"
									+ objectMapper.writeValueAsString(dac));
							final OperationResponseInfo resp = AddCstViewCtrl.this.issuingPortProxy.closeAccount(connectionInfo, dac);
							if (resp.getResponse_code().intValue() != 0) {
								alert(String.valueOf(resp.getError_action())
										+ " \n\r "
										+ resp.getError_description());
								return;
							}
							alert("Счет закрыт");
						} catch (Exception e) {
							LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
							alert(e.getMessage());
							e.printStackTrace();
							return;
						} finally {
							AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
							AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
						}
						AddCstViewCtrl.this.tcust.setTietoCustomerId(AddCstViewCtrl.this.tietocl.getClient());
						AddCstViewCtrl.this.accGrid.setModel((ListModel) new BindingListModelList((List) AddCstViewCtrl.this.makeList(TclientService.getAccInfo(AddCstViewCtrl.this.branch, AddCstViewCtrl.this.tietocl.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy)), false));
					}
				});

				btrefresh_card.setAttribute("card", (Object) cardInfo);
				if (all == false) {
					btrefresh_card.setVisible(false);
				}

				// Перевыпуск карты
				btrefresh_card.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						event.getTarget();
						final CardInfo card = (CardInfo) event.getTarget().getAttribute("card");
						String name = Utils.getValueFromSql("select f_names from bf_empc_clients where client = "
								+ card.getCLIENT_ID(), alias);
						String surname = Utils.getValueFromSql("select surname from bf_empc_clients where client = "
								+ card.getCLIENT_ID(), alias);
						String cardString = "";
						if (name != null && surname != null) {
							cardString = (name + " " + surname).length() > 24 ? (name
									+ " " + surname).substring(0, 23)
									: name + " " + surname;
							if (cardString.equals(card.getCARD_String())) {
								String realCard = CustomerService.getRealCard(alias, card.getCARD(), card.getACCOUNT_NO());
								ISLogger.getLogger().error("REAL CARD: "
										+ realCard);
								Connection c = ConnectionPool.getConnection(alias);
								String message = com.is.tieto_globuz.customer.HumoCardsService.replaceCard(c, realCard, settings, branch, alias);
								CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Перевыпуск карты No ["
										+ card.getCARD()));
								ConnectionPool.close(c);
								alert(message);
							} else {
								try {
									Messagebox.show("Данные клиента не совпадают с данными карты. (Клиент): "
											+ cardString
											+ ", (Карта): "
											+ card.getCARD_String()
											+ ". Чтобы перевыпустить карту с новыми данными клиента, нажмите \"Да\", со старыми - \"Нет\"", "Question", Messagebox.YES
											| Messagebox.NO, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
										public void onEvent(Event e) {
											int answer = (Integer) e.getData();
											//if (Messagebox.ON_YES.equals(e.getName())) {
											if (answer == Messagebox.YES) {
												try {
													RowType_EditCard_Request parameters = new RowType_EditCard_Request();
													OperationResponseInfo responseInfo = new OperationResponseInfo();
													OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
													connectionInfo.setBANK_C(settings.get(branch).getBank_c());
													connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
													connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
													String realCard = CustomerService.getRealCard(alias, card.getCARD(), card.getACCOUNT_NO());
													parameters.setCARD(realCard);
													String name = Utils.getValueFromSql("select f_names from bf_empc_clients where client = "
															+ card.getCLIENT_ID(), alias);
													String surname = Utils.getValueFromSql("select surname from bf_empc_clients where client = "
															+ card.getCLIENT_ID(), alias);
													String cardString = (name
															+ " " + surname).length() > 24 ? (name
															+ " " + surname).substring(0, 23)
															: name + " "
																	+ surname;
													parameters.setCARD_NAME(cardString);
													responseInfo = issuingPortProxy.editCard(connectionInfo, parameters);
													if (responseInfo.getError_description() != null) {
														alert("Ошибка! "
																+ responseInfo.getError_description());
													} else {
														Connection c = ConnectionPool.getConnection(alias);
														String message = HumoCardsService.replaceCard(c, realCard, settings, branch, alias);
														ConnectionPool.close(c);
														CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Перевыпуск карты No ["
																+ card.getCARD()));
														alert(message);
													}
													// OK is clicked
												} catch (Exception e3) {
													ISLogger.getLogger().error("editCard error: "
															+ e3.getLocalizedMessage());
													// TODO: handle
													// exception
												}
											} else if (/*Messagebox.ON_NO.equals(e.getName())*/ answer == Messagebox.NO) {
												try {
													String realСard = CustomerService.getRealCard(alias, card.getCARD(), card.getACCOUNT_NO());
													Connection c = ConnectionPool.getConnection(alias);
													String message = HumoCardsService.replaceCard(c, realСard, settings, branch, alias);
													CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Перевыпуск карты No ["
															+ card.getCARD()));
													ConnectionPool.close(c);
													alert(message);
												} catch (Exception e2) {
													ISLogger.getLogger().error("replaceCard with old client data error "
															+ e2.getLocalizedMessage());
													// TODO: handle
													// exception
												}

											}
										}
									});
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						} else {
							String realCard = CustomerService.getRealCard(alias, card.getCARD(), card.getACCOUNT_NO());
							ISLogger.getLogger().error("REAL CARD: " + realCard);
							Connection c = ConnectionPool.getConnection(alias);
							String message = com.is.tieto_globuz.customer.HumoCardsService.replaceCard(c, realCard, settings, branch, alias);
							ConnectionPool.close(c);
							alert(message);
						}
					}
				});
				if (cardInfo.getBank_account_status().compareTo("0") == 0) {
					acc_act.appendChild((Component) AddCstViewCtrl.this.bt_block_card_acc);
				}
				if (cardInfo.getBank_account_status().compareTo("3") == 0) {
					acc_act.appendChild((Component) AddCstViewCtrl.this.bt_close_card_acc);
				}
				if (cardInfo.getBank_account_status().compareTo("3") == 0) {
					acc_act.appendChild((Component) AddCstViewCtrl.this.bt_unblock_card_acc);
				}
				AddCstViewCtrl.this.btunblock_card.setParent((Component) acc_act);
				AddCstViewCtrl.this.btrefresh_card.setParent((Component) acc_act);
				// ///////////////ДОБАВЛЕНИЕ КНОПОК В ОКНО
				// "ОПЕРАЦИИ С КАРТАМИ"////////////////////////////////

				acc_act.appendChild((Component) AddCstViewCtrl.this.btrefresh_card);
				acc_act.appendChild((Component) AddCstViewCtrl.this.btunblock_card);
				acc_act.appendChild((Component) AddCstViewCtrl.this.btblock_card);
				acc_act.appendChild((Component) AddCstViewCtrl.this.bt_close_card_acc);
				AddCstViewCtrl.this.bt_close_card_acc.setParent((Component) acc_act);
				String state = "";
				if (cardInfo.getBank_account_status().compareTo("0") == 0) {
					state = "Активен";
				}
				if (cardInfo.getBank_account_status().compareTo("3") == 0) {
					state = "Не активен";
				}
				if (cardInfo.getBank_account_status().compareTo("4") == 0) {
					state = "Закрыт";
				}
				CustomerService.updateStateCard(cardInfo);
				/*
				 * if (cardInfo.getSTOP_CAUSE().equals("0")) {
				 * bl_unbl.appendChild
				 * ((Component)AddCstViewCtrl.this.btblock_card); } else {
				 * bl_unbl.appendChild((Component)AddCstViewCtrl.this.
				 * btunblock_card); }
				 */
				bl_unbl.appendChild((Component) AddCstViewCtrl.this.btblock_card);
				bl_unbl.appendChild((Component) AddCstViewCtrl.this.btunblock_card);
				bl_unbl.appendChild((Component) AddCstViewCtrl.this.btrefresh_card);
				// bl_unbl.appendChild((Component)
				// AddCstViewCtrl.this.btblockDialogue);
				row.appendChild((Component) new Listcell(cardInfo.getCARD()));
				if (cardInfo.getEXPIRY() != null) {
					Date datet = new Date();
					datet = AddCstViewCtrl.this.datef.parse(cardInfo.getEXPIRY().substring(0, 10));
					row.appendChild((Component) new Listcell(new StringBuilder(String.valueOf(AddCstViewCtrl.this.df.format(datet))).toString()));
				} else {
					row.appendChild((Component) new Listcell("Не указана"));
				}
				final Button btnPINReset = new Button("Сброс счётчика ПИН");
				btnPINReset.setAttribute("card", (Object) cardInfo);
				// Сброс счётчика неправильных попыток ввода PIN
				btnPINReset.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						System.out.println("PIN RESET");
						try {

							IssuingPortProxy issuingPortProxy = new IssuingPortProxy(ConnectionPool.getValue("HUMO_HOST"), ConnectionPool.getValue("HUMO_USERNAME"), ConnectionPool.getValue("HUMO_PASSWORD"));
							ISLogger.getLogger().error((Object) ("PAN CARD: " + (CardInfo) event.getTarget().getAttribute("card")));
							final CardInfo cardForReset = (CardInfo) event.getTarget().getAttribute("card");
							final OperationResponseInfo response = CustomerService.resetPINCounter(CustomerService.getRealCardByCard(alias, cardForReset.getCARD()), issuingPortProxy, HUMO_BANK_C, HUMO_GROUPC, un, pwd, alias, cardForReset.getEXPIRY().substring(0, 4));
							ISLogger.getLogger().error((Object) ("RESPONCE: " + response.getResponse_code()));
							if (response.getError_description() == null
									|| response.getError_description().equals("")) {
								CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Сброс счётчика PIN по карте No ["
										+ cardInfo.getCARD() + "]."));
								alert("ПИН сброшен!");
							} else {
								System.out.println("ОШИБКА: "
										+ response.getError_description());
								alert("Ошибка: "
										+ response.getError_description());

							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				AddCstViewCtrl.access$104(AddCstViewCtrl.this, new Button());
				btnResetPin.setLabel("Сбросить PIN");
				btnResetPin.setAttribute("card", (Object) cardInfo);
				if (all == false) {
					btnResetPin.setVisible(false);
				}
				// Сброс PIN
				btnResetPin.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
						OperationResponseInfo orInfo = null;
						final RowType_DeactivateCard_Request parameters = new RowType_DeactivateCard_Request();
						try {
							connectionInfo.setBANK_C(HUMO_BANK_C);
							connectionInfo.setGROUPC(HUMO_GROUPC);
							connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
							AddCstViewCtrl.access$39(AddCstViewCtrl.this, (CardInfo) event.getTarget().getAttribute("card"));
							parameters.setCARD(AddCstViewCtrl.this.blockcard.getCARD());
							ISLogger.getLogger().error("deactivateCard request "
									+ "\n" + objectMapper.writeValueAsString(connectionInfo)
									+ "\n" + objectMapper.writeValueAsString(parameters));
							orInfo = AddCstViewCtrl.this.issuingPortProxy.deactivateCard(connectionInfo, parameters);
							ISLogger.getLogger().error("deactivateCard response "
									+ "\n" + objectMapper.writeValueAsString(orInfo));
							if (orInfo.getResponse_code().intValue() != 0) {
								alert(String.valueOf(orInfo.getError_action())
										+ "\r\n"
										+ orInfo.getError_description());
							} else {
								CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Сброс PIN по карте No ["
										+ cardInfo.getCARD() + "]."));
								alert("Успешно");
							}
						} catch (Exception e) {
							LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
							alert(e.getMessage());
							e.printStackTrace();
						}
					}
				});
				bl_unbl.appendChild((Component) btnResetPin);
				bl_unbl.appendChild((Component) btnPINReset);
				bl_unbl.appendChild((Component) btrefresh_card);
				cardActionsHistory.setParent((Component) bl_unbl);
				cardActionsHistory.setVisible(true);
				bl_unbl.appendChild((Component) cardActionsHistory);
				// ИПАК IPAK отображение статусов карты
				if (settings.get(branch).getBank_c().equals("17")) {
					// ISLogger.getLogger().error("cardInfo: "+objectMapper.writeValueAsString(cardInfo));
					if (!cardInfo.getSTATUS().equals("0")) {
						if (cardInfo.getSTOP_CAUSE().equals("1")) {
							if (CustomerService.checkHumoCardsHistoryExistence(cardInfo.getCARD())) {
								row.appendChild((Component) new Listcell("Карта недействительна (перевыпущена)"));
							} else {
								row.appendChild((Component) new Listcell("Заблокирована банком"));
							}
						} else if (cardInfo.getSTOP_CAUSE().equals("2")) {
							row.appendChild((Component) new Listcell("Заблокирована пользователем"));
						} else {
							RowType_GetCardPinTryCounter_Request parameters = new RowType_GetCardPinTryCounter_Request();
							OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
							RowType_GetCardPinTryCounter_ResponseHolder details = new RowType_GetCardPinTryCounter_ResponseHolder();
							globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
							parameters.setCARD(cardInfo.getCARD());
							connectionInfo.setBANK_C(HUMO_BANK_C);
							connectionInfo.setGROUPC(HUMO_GROUPC);
							connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
							ISLogger.getLogger().error("getCardPinTryCounter sessionId: "
									+ connectionInfo.getEXTERNAL_SESSION_ID());
							issuingPortProxy.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
							ISLogger.getLogger().error("pinTryCount: "
									+ details.value.getPIN_TRY_COUNT());
							if (details.value.getPIN_TRY_COUNT().intValue() >= 3) {
								row.appendChild((Component) new Listcell("Превышен счетчик попыток ввода ПИН"));
							} else {
								row.appendChild((Component) new Listcell("Not active"));
							}
						}

					} else {
						RowType_GetCardPinTryCounter_Request parameters = new RowType_GetCardPinTryCounter_Request();
						OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
						RowType_GetCardPinTryCounter_ResponseHolder details = new RowType_GetCardPinTryCounter_ResponseHolder();
						globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
						parameters.setCARD(cardInfo.getCARD());
						ISLogger.getLogger().error("card check pin try counter: "
								+ cardInfo.getCARD());
						connectionInfo.setBANK_C(settings.get(branch).getBank_c());
						connectionInfo.setGROUPC(settings.get(branch).getGroup_c());
						connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
						ISLogger.getLogger().error("getCardPinTryCounter sessionId: "
								+ connectionInfo.getEXTERNAL_SESSION_ID());
						issuingPortProxy.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
						if (details.value != null) {
							if (details.value.getPIN_TRY_COUNT() != null) {
								ISLogger.getLogger().error("pinTryCount: "
										+ details.value.getPIN_TRY_COUNT());
								if (details.value.getPIN_TRY_COUNT().intValue() >= 3) {
									row.appendChild((Component) new Listcell("Превышен счетчик попыток ввода ПИН"));
								} else {
									row.appendChild((Component) new Listcell("Active"));
								}
							}
						} else {
							row.appendChild((Component) new Listcell("Active"));
						}
					}
				} else {
					row.appendChild((Component) new Listcell(cardInfo.getSTATUS().equals("0") ? "Active"
							: "Not active"));
				}

				row.appendChild((Component) bl_unbl);
				final Listcell listcell = new Listcell();
				final Longbox lbox = new Longbox();
				lbox.setMaxlength(9);
				final Label label = new Label("Телефон: +998");
				listcell.appendChild((Component) label);
				final Long phoneNumber = CustomerService.getCardSmsPhoneNumber(cardInfo.getCARD());
				if (phoneNumber != null) {
					lbox.setValue(Long.parseLong(phoneNumber.toString().substring(3)));
				}
				listcell.appendChild((Component) lbox);
				final Button btnSmsOn = new Button("on");
				btnSmsOn.setAttribute("cardInfo", (Object) cardInfo);
				btnSmsOn.setAttribute("lbox", (Object) lbox);
				btnSmsOn.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						// Привязка телефона к карте. Подключение СМС
						// уведомлений на номер
						AddCstViewCtrl.this.activateSms(event);
					}
				});
				final Button btnSmsOff = new Button("off");
				btnSmsOff.setAttribute("cardInfo", (Object) cardInfo);
				btnSmsOff.setAttribute("lbox", (Object) lbox);
				btnSmsOff.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						// Отвязка телефона от карты. Отключение СМС уведомлений
						// с номера
						AddCstViewCtrl.this.activateSms(event);
					}
				});
				listcell.appendChild((Component) btnSmsOn);
				listcell.appendChild((Component) btnSmsOff);
				row.appendChild((Component) listcell);
				final Listcell lc = new Listcell();
				lc.appendChild((Component) new Label(cardInfo.getBank_account()));
				row.appendChild((Component) lc);
				row.appendChild((Component) new Listcell(cardInfo.getCARD_ACCT()));
				row.appendChild((Component) new Listcell(state));
				row.appendChild((Component) acc_act);
			}
		});
	}

	private void activateSms(final Event event) {
		try {
			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("cardInfo");
			final Longbox lbox = (Longbox) btn.getAttribute("lbox");
			if (lbox == null) {
				alert("Введите номер телефона!");
				return;
			}
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
			if (lbox.getValue() == null
					|| lbox.getValue().toString().length() != 9
					|| !lbox.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона " + lbox.getValue());
				return;
			}
			ISLogger.getLogger().error("ACTIVATE SMS:" + "\nPHONE_NUMBER: "
					+ lbox.getValue() + "\nEXPIRY: " + cardInfo.getEXPIRY()
					+ "\nEXPIRYSTRING: " + cardInfo.getEXPIRY().substring(5, 7)
					+ cardInfo.getEXPIRY().substring(2, 4) + "\nSTATE_CARD: "
					+ btn.getLabel() + "\nCARD_NUMBER: " + cardInfo.getCARD()
					+ "\nCLIENT_ID: " + cardInfo.getCLIENT_ID());
			/*
			 * Customer customer =
			 * CustomerService.getCustomerNames(cardInfo.getCLIENT_ID()); String
			 * query = "activateSms?PHONE_NUMBER="+"998"+lbox.getValue()
			 * +"&CARD_NUMBER="
			 * +CustomerService.getRealCardNumber(cardInfo.getCARD
			 * (),initNp(alias), settings.get(branch).getBank_c(),
			 * settings.get(branch).getGroup_c())
			 * +"&EXPIRY="+dfExpiry.format(dfExpiry
			 * .parse(cardInfo.getEXPIRY().substring(5, 7)+"/" +
			 * cardInfo.getEXPIRY().substring(2, 4)))
			 * +"&NAME="+customer.getP_first_name() а
			 * +"&SURNAME="+customer.getP_family()
			 * +"&STATE_CARD="+btn.getLabel()
			 * +"&BANK_C="+settings.get(branch).getBank_c()
			 * +"&CLIENT_ID="+cardInfo.getCLIENT_ID();
			 * System.out.println("ActivateSMS request query: "+query);
			 * URLConnection activateSmsRequest = new
			 * URL(Utils.getValue("HUMO_LD")+query).openConnection();
			 * ObjectInputStream in = new
			 * ObjectInputStream(activateSmsRequest.getInputStream()); String
			 * response = (String) in.readObject();
			 * System.out.println("activateSms response: "+response);
			 * if(response.equals("0#")){
			 */
			// SOAP Запрос
			String expiryString = cardInfo.getEXPIRY().substring(5, 7)
					+ cardInfo.getEXPIRY().substring(2, 4);
			final String message = this.operationWithCard(btn.getLabel(), cardInfo, expiryString, "+998"
					+ lbox.getValue(), this.issuingPortProxy);
			if (Strings.isNullOrEmpty(message)) {
				// Логгирование после подключения/отключения СМС уведомлений
				CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un, CustomerService.getIp(), null, 6, 1, "Подключение СМС уведомлений по карте No ["
						+ cardInfo.getCARD()
						+ "]. Действие: СМС "
						+ btn.getLabel() + ""));
			}
			final RowType_Customer rtc = new RowType_Customer();
			OperationResponseInfo orInfo = null;
			final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
			connectionInfo.setBANK_C(AddCstViewCtrl.settings.get(this.branch).getBank_c());
			connectionInfo.setGROUPC(AddCstViewCtrl.settings.get(this.branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			final RowType_EditCustomer_Request customerRequest = new RowType_EditCustomer_Request(cardInfo.getCLIENT_ID(), AddCstViewCtrl.settings.get(this.branch).getBank_c(), rtc.getCLIENT_B(), rtc.getCL_TYPE(), rtc.getCLN_CAT(), rtc.getREC_DATE(), rtc.getF_NAMES(), rtc.getSURNAME(), rtc.getTITLE(), rtc.getM_NAME(), rtc.getB_DATE(), rtc.getRESIDENT(), rtc.getID_CARD(), rtc.getDOC_TYPE(), btn.getLabel().equals("on") ? "+998"
					+ lbox.getValue().toString()
					: "", rtc.getEMP_NAME(), rtc.getPOSITION(), rtc.getEMP_DATE(), rtc.getWORK_PHONE(), rtc.getR_STREET(), rtc.getR_CITY(), rtc.getR_CNTRY(), rtc.getR_PCODE(), rtc.getC_SINCE(), rtc.getCMP_NAME(), rtc.getCMPG_NAME(), rtc.getCO_POSITON(), rtc.getCONTACT(), rtc.getCNT_TITLE(), rtc.getCNT_PHONE(), rtc.getCNT_FAX(), rtc.getMNG_POSIT(), rtc.getMNG_NAME(), rtc.getMNG_PHONE(), rtc.getMNG_TITLE(), rtc.getMNG_FAX(), rtc.getREG_NR(), rtc.getCR_STREET(), rtc.getCR_CITY(), rtc.getCR_CNTRY(), rtc.getCR_PCODE(), rtc.getCOMENT(), rtc.getREGION(), rtc.getPERSON_CODE(), rtc.getRESIDENT_SINCE(), rtc.getYEAR_INC(), rtc.getCCY_FOR_INCOM(), rtc.getIMM_PROP_VALUE(), rtc.getSEARCH_NAME(), rtc.getMARITAL_STATUS(), rtc.getCO_CODE(), rtc.getEMP_CODE(), rtc.getSEX(), rtc.getSERIAL_NO(), rtc.getDOC_SINCE(), rtc.getISSUED_BY(), rtc.getEMP_ADR(), rtc.getEMP_FAX(), rtc.getEMP_E_MAILS(), rtc.getR_E_MAILS(), btn.getLabel().equals("on") ? "+998"
					+ lbox.getValue()
					: "", rtc.getR_FAX(), rtc.getCNT_E_MAILS(), rtc.getCNT_MOB_PHONE(), rtc.getMNG_MOB_PHONE(), rtc.getMNG_E_MAILS(), rtc.getCR_E_MAILS(), rtc.getIN_FILE_NUM(), rtc.getU_COD1(), rtc.getU_COD2(), rtc.getU_COD3(), rtc.getU_FIELD1(), rtc.getU_FIELD2(), rtc.getCALL_ID(), rtc.getHOME_NUMBER(), rtc.getBUILDING(), rtc.getSTREET1(), rtc.getAPARTMENT(), rtc.getMIDLE_NAME(), rtc.getSTATUS(), (String) null, rtc.getAMEX_MEMBER_SINCE(), rtc.getDCI_MEMBER_SINCE(), rtc.getREWARD_NO());
			ObjectMapper mapper = new ObjectMapper();
			ISLogger.getLogger().error("sms editCustomer: "
					+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerRequest));

			// ИЗМЕНЯЕМ НОМЕР В ДАННЫХ КЛИЕНТА ЧЕРЕЗ editCustomer
			try {
				ISLogger.getLogger().error("editCustomer request for humo client " + customerRequest.getCLIENT()
				+ "\n" + objectMapper.writeValueAsString(connectionInfo)
				+ "\n" + objectMapper.writeValueAsString(customerRequest));
				orInfo = this.issuingPortProxy.editCustomer(connectionInfo, customerRequest);
				ISLogger.getLogger().error("editCustomer response for humo client " + customerRequest.getCLIENT()
						+ "\n" + objectMapper.writeValueAsString(orInfo));
			} catch (RemoteException e2) {
				LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e2));
				e2.printStackTrace();
			}
			if ((message == null || message.equals("null"))
					&& orInfo.getError_description() == null) {
				if (btn.getLabel().equals("off")) {

				}
				this.alert("Успешно");
			} else {
				ISLogger.getLogger().error("sms editCustomer error: "
						+ orInfo.getError_description());
				this.alert("Ошибка: " + message + orInfo.getError_description());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
	}

	private void addAccRender() {
		this.addwnd$accGrid.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				final GlobuzAccount pAccInfo = (GlobuzAccount) data;
				final List<GlobuzAccountService.actions_for_acc> act = (List<GlobuzAccountService.actions_for_acc>) GlobuzAccountService.getactions_for_acc(pAccInfo.getState(), AddCstViewCtrl.this.alias);
				final Listcell h_edit_cell = new Listcell();
				for (int i = 0; i < act.size(); ++i) {
					AddCstViewCtrl.access$3(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.bt_acc_act.setLabel(act.get(i).getName());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("deal_group_id", (Object) act.get(i).getDeal_group());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("deal_id", (Object) act.get(i).getDeal_id());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("action_id", (Object) act.get(i).getAction_id());
					AddCstViewCtrl.this.bt_acc_act.setAttribute("acc", (Object) pAccInfo);
					AddCstViewCtrl.this.bt_acc_act.setImage("/images/down3.png");
					// Утверждение счёта утвердить счёт
					AddCstViewCtrl.this.bt_acc_act.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {

							GlobuzAccountService.doAction_acc(AddCstViewCtrl.this.un, AddCstViewCtrl.this.pwd, (Integer) event.getTarget().getAttribute("deal_group_id"), (Integer) event.getTarget().getAttribute("deal_id"), (GlobuzAccount) event.getTarget().getAttribute("acc"), (Integer) event.getTarget().getAttribute("action_id"), AddCstViewCtrl.this.alias, Boolean.valueOf(true));
							System.out.println("Утвердить!");
							final GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
							acfilter.setClient(AddCstViewCtrl.this.cur_branch_customer.getId_client());
							acfilter.setAcc_bal("2261");
							acfilter.setBranch(AddCstViewCtrl.this.branch);
							acfilter.setCurrency("000");
							final com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, (Object) acfilter, AddCstViewCtrl.this.alias);
							AddCstViewCtrl.this.addwnd$accGrid.setModel((ListModel) acc_model);
						}
					});
					if (act.get(i).getAction_id() == 1
							|| act.get(i).getAction_id() == 2
							|| act.get(i).getAction_id() == 20) {
						h_edit_cell.appendChild((Component) AddCstViewCtrl.this.bt_acc_act);
					}
				}
				final Listcell h_add_acc_cell = new Listcell();
				if (pAccInfo.getState() == 2) {
					AddCstViewCtrl.access$3(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.bt_acc_act.setLabel("Открыть карту");
					AddCstViewCtrl.this.bt_acc_act.setAttribute("acc", (Object) pAccInfo);
					AddCstViewCtrl.this.bt_acc_act.setImage("/images/credit_card1.png");
					AddCstViewCtrl.this.bt_acc_act.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							if (CheckNull.isEmpty(AddCstViewCtrl.this.addwnd$sproduct.getValue())) {
								alert("Продукт не выбран");
								return;
							}
							final String new_card_acc = ((GlobuzAccount) event.getTarget().getAttribute("acc")).getId();
							String RT = null;
							if (AddCstViewCtrl.this.addwnd$sproduct.getValue().compareTo("004") == 0) {
								final Tclient ntc = AddCstViewCtrl.this.tietocl;
								Res res = TclientService.check_card("004", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								res = TclientService.check_card("001", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
								RT = AddCstViewCtrl.this.open_card("004", RT, false, new_card_acc);
								if (RT == null) {
									return;
								}
								AddCstViewCtrl.this.open_card("001", RT, true, new_card_acc);
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
							} else if (AddCstViewCtrl.this.addwnd$sproduct.getValue().compareTo("008") == 0) {
								final Tclient ntc = AddCstViewCtrl.this.tietocl;
								Res res = TclientService.check_card("008", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								res = TclientService.check_card("002", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
								RT = AddCstViewCtrl.this.open_card("008", RT, false, new_card_acc);
								if (RT == null) {
									return;
								}
								AddCstViewCtrl.this.open_card("002", RT, true, new_card_acc);
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
							} else if (AddCstViewCtrl.this.addwnd$sproduct.getValue().compareTo("007") == 0) {
								final Tclient ntc = AddCstViewCtrl.this.tietocl;
								Res res = TclientService.check_card("007", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								res = TclientService.check_card("002", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
								RT = AddCstViewCtrl.this.open_card("007", RT, false, new_card_acc);
								if (RT == null) {
									return;
								}
								AddCstViewCtrl.this.open_card("002", RT, true, new_card_acc);
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
							} else if (AddCstViewCtrl.this.addwnd$sproduct.getValue().compareTo("015") == 0) {
								final Tclient ntc = AddCstViewCtrl.this.tietocl;
								Res res = TclientService.check_card("015", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								res = TclientService.check_card("002", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
								RT = AddCstViewCtrl.this.open_card("015", RT, false, new_card_acc);
								if (RT == null) {
									return;
								}
								AddCstViewCtrl.this.open_card("002", RT, true, new_card_acc);
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
							} else if (AddCstViewCtrl.this.addwnd$sproduct.getValue().compareTo("005") == 0) {
								final Tclient ntc = AddCstViewCtrl.this.tietocl;
								Res res = TclientService.check_card("009", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								res = TclientService.check_card("005", TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
								RT = AddCstViewCtrl.this.open_card("009", RT, false, new_card_acc);
								if (RT == null) {
									return;
								}
								AddCstViewCtrl.this.open_card("005", RT, true, new_card_acc);
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
							} else {
								final Tclient ntc = AddCstViewCtrl.this.tietocl;
								final Res res = TclientService.check_card(AddCstViewCtrl.this.addwnd$sproduct.getValue(), TclientService.getAccInfo_active(AddCstViewCtrl.this.branch, ntc.getClient(), AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy), AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
								AddCstViewCtrl.this.open_card(AddCstViewCtrl.this.addwnd$sproduct.getValue(), RT, false, new_card_acc);
								AddCstViewCtrl.access$55(AddCstViewCtrl.this, null);
							}
							AddCstViewCtrl.this.addwnd.setVisible(false);
						}
					});
				}
				row.setValue((Object) pAccInfo);
				row.appendChild((Component) new Listcell(pAccInfo.getBranch()));
				row.appendChild((Component) new Listcell(pAccInfo.getClient()));
				row.appendChild((Component) new Listcell(pAccInfo.getId()));
				String full_name = "";
				final CharSequence ch = " null";
				final CharSequence newch = " ";
				full_name = pAccInfo.getName();
				if (full_name.contains(ch)) {
					full_name = full_name.replace(ch, newch);
				}
				row.appendChild((Component) new Listcell(full_name));
				row.appendChild((Component) new Listcell((String) AddCstViewCtrl.this.hashMapCurr.get(pAccInfo.getCurrency())));
				row.appendChild((Component) new Listcell((pAccInfo.getDate_open() != null) ? AddCstViewCtrl.this.df.format(pAccInfo.getDate_open())
						: "--//--"));
				row.appendChild((Component) new Listcell(pAccInfo.getId_order()));
				row.appendChild((Component) new Listcell(GlobuzAccountService.get_account_state_caption(pAccInfo.getState(), AddCstViewCtrl.this.alias)));
				row.appendChild((Component) h_edit_cell);
				row.appendChild((Component) h_add_acc_cell);
			}
		});
	}

	private void branchCustomersRender() {
		this.branch_customers.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				boolean fl_show = true;
				final Listcell acc_edit_cell = new Listcell();
				final Customer pCustomer = (Customer) data;
				final CustomerService.link lnk = CustomerService.get_link_branch(pCustomer.getId_client(), AddCstViewCtrl.this.branch, AddCstViewCtrl.this.alias);
				row.setAttribute("row", (Object) row);
				row.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
						AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
						AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
						AddCstViewCtrl.this.bank_customers.clearSelection();
					}
				});
				if (lnk != null && AddCstViewCtrl.used_ids.containsKey(lnk.id)) {
					if (AddCstViewCtrl.used_ids.containsKey(lnk.id)) {
						fl_show = false;
						row.setVisible(false);
					} else {
						AddCstViewCtrl.used_ids.put(lnk.id, 1);
						fl_show = true;
					}
				}
				if (fl_show) {
					AddCstViewCtrl.access$60(AddCstViewCtrl.this, true);
					final Listcell br_cell = new Listcell();
					AddCstViewCtrl.access$65(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.btbreak.setLabel("");
					AddCstViewCtrl.this.btbreak.setAttribute("lnk_id", (Object) lnk);
					AddCstViewCtrl.this.btbreak.setImage("/images/locked.png");
					AddCstViewCtrl.this.btbreak.setTooltiptext("\u0420\u0430\u0437\u0434\u0435\u043b\u0438\u0442\u044c \u0441\u0432\u044f\u0437\u043a\u0443");
					AddCstViewCtrl.this.btbreak.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							Messagebox.show("\u0412\u044b \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u0445\u043e\u0442\u0438\u0442\u0435 \u0440\u0430\u0437\u0434\u0435\u043b\u0438\u0442\u044c \u0441\u0432\u044f\u0437\u043a\u0443 \u0411\u0430\u043d\u043a\u0430 \u0441 EMPC(GLOB.UZ) ? ", "\u041f\u0440\u0435\u0434\u0443\u043f\u0440\u0435\u0436\u0434\u0435\u043d\u0438\u0435 \u0431\u0435\u0437\u043e\u043f\u0430\u0441\u043d\u043e\u0441\u0442\u0438", 3, "z-msgbox z-msgbox-question", (EventListener) new EventListener() {
								public void onEvent(final Event event) throws Exception {
									final int answer = (Integer) event.getData();
									if (answer != 1) {
										return;
									}
									final Res res = CustomerService.removeT\u0441(AddCstViewCtrl.this.un, AddCstViewCtrl.this.pwd, lnk_id, AddCstViewCtrl.this.alias);
									if (res.getCode() != 0) {
										alert(res.getName());
										return;
									}
									AddCstViewCtrl.this.refreshModel(AddCstViewCtrl.this._starttPageNumber);
								}
							});
						}
					});
					br_cell.appendChild((Component) AddCstViewCtrl.this.btbreak);
					row.appendChild((Component) br_cell);
					final Listcell edit_cell = new Listcell();
					AddCstViewCtrl.access$68(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.btedit.setLabel("");
					AddCstViewCtrl.this.btedit.setImage("/images/config.png");
					AddCstViewCtrl.this.btedit.setAttribute("br_cl", (Object) pCustomer);
					AddCstViewCtrl.this.btedit.setAttribute("is_br", (Object) true);
					AddCstViewCtrl.this.btedit.setAttribute("ho_cl", (Object) null);
					AddCstViewCtrl.this.btedit.setAttribute("ti_cl", (Object) null);
					AddCstViewCtrl.this.btedit.setAttribute("is_ti", (Object) false);
					AddCstViewCtrl.this.btedit.setAttribute("is_ho", (Object) false);
					AddCstViewCtrl.this.btedit.setAttribute("edit_ho", (Object) false);
					AddCstViewCtrl.this.btedit.setAttribute("edit_br", (Object) false);
					AddCstViewCtrl.this.btedit.setAttribute("edit_ti", (Object) false);
					AddCstViewCtrl.this.btedit.setTooltiptext("Редактировать везде");
					AddCstViewCtrl.this.btedit.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_ti"));
							AddCstViewCtrl.access$70(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_ho"));
							AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_br"));
							AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
							AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
							AddCstViewCtrl.access$71(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_ti"));
							AddCstViewCtrl.access$72(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_br"));
							CheckNull.clearForm(AddCstViewCtrl.this.add_everywhere$addgrdl);
							CheckNull.clearForm(AddCstViewCtrl.this.add_everywhere$addgrdr);
							if (AddCstViewCtrl.this.cur_branch_customer != null) {
								AddCstViewCtrl.this.fill_form(AddCstViewCtrl.this.cur_branch_customer, AddCstViewCtrl.this.tietocl);
							} else if (AddCstViewCtrl.this.tietocl != null) {
								AddCstViewCtrl.this.fill_form(AddCstViewCtrl.this.tietocl);
							}
							AddCstViewCtrl.this.add_everywhere.setTitle("Редактирование клиента");
							AddCstViewCtrl.access$78(AddCstViewCtrl.this, true);
							AddCstViewCtrl.this.add_everywhere.setVisible(true);
						}
					});
					edit_cell.appendChild((Component) AddCstViewCtrl.this.btedit);
					row.appendChild((Component) edit_cell);
					row.setValue((Object) pCustomer);
					final Listcell h_edit_cell = new Listcell();
					AddCstViewCtrl.access$79(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.bth.setLabel("");
					AddCstViewCtrl.this.bth.setImage("/images/edit.png");
					AddCstViewCtrl.this.bth.setAttribute("br_cl", (Object) pCustomer);
					AddCstViewCtrl.this.bth.setTooltiptext("Редактировать ГО");
					row.setAttribute("br_cl", (Object) pCustomer);
					AddCstViewCtrl.this.bth.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
							AddCstViewCtrl.access$70(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ho") == 1);
							AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
							AddCstViewCtrl.access$81(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("ho_cl"));
							AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
							AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
							AddCstViewCtrl.access$82(AddCstViewCtrl.this, false);
							AddCstViewCtrl.access$83(AddCstViewCtrl.this, true);
							AddCstViewCtrl.access$84(AddCstViewCtrl.this, false);
							AddCstViewCtrl.this.set_default();
							AddCstViewCtrl.this.session.setAttribute("branch_filter", (Object) ConnectionPool.getValue("HO", AddCstViewCtrl.this.alias));
							AddCstViewCtrl.this.session.setAttribute("alias_filter", (Object) CustomerService.get_alias_ho(AddCstViewCtrl.this.alias));
							AddCstViewCtrl.access$78(AddCstViewCtrl.this, false);
							AddCstViewCtrl.this.addCustomer.setVisible(true);
						}
					});
					h_edit_cell.appendChild((Component) AddCstViewCtrl.this.bth);
					final Listcell t_edit_cell = new Listcell();
					AddCstViewCtrl.access$88(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.btt.setLabel("");
					AddCstViewCtrl.this.btt.setImage("/images/link16.png");
					AddCstViewCtrl.this.btt.setAttribute("br_cl", (Object) pCustomer);
					AddCstViewCtrl.this.btt.setTooltiptext("Редактировать tieto");
					AddCstViewCtrl.this.btt.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
							AddCstViewCtrl.access$70(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ho") == 1);
							AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
							AddCstViewCtrl.access$81(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("ho_cl"));
							AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
							AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
							AddCstViewCtrl.access$82(AddCstViewCtrl.this, true);
							AddCstViewCtrl.access$83(AddCstViewCtrl.this, false);
							AddCstViewCtrl.access$84(AddCstViewCtrl.this, false);
							AddCstViewCtrl.this.set_default();
							AddCstViewCtrl.access$78(AddCstViewCtrl.this, false);
							AddCstViewCtrl.this.add_everywhere.setTitle("Открытие клиента [НПС HUMO]");
							AddCstViewCtrl.this.add_everywhere.setVisible(true);
						}
					});

					// ISLogger.getLogger().error("Открыть карту "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(acfilter));
					t_edit_cell.appendChild((Component) AddCstViewCtrl.this.btt);
					AddCstViewCtrl.access$90(AddCstViewCtrl.this, new Toolbarbutton());
					AddCstViewCtrl.this.btacc.setImage("/images/edit.png");
					AddCstViewCtrl.this.btacc.setAttribute("br_cl", (Object) pCustomer);
					AddCstViewCtrl.this.btacc.setTooltiptext("Открыть карту на счете 22618");
					AddCstViewCtrl.this.btacc.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
							cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
							// AddCstViewCtrl.access$62(AddCstViewCtrl.this,
							// (Tclient)
							// event.getTarget().getAttribute("ti_cl"));
							AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
							AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
							AddCstViewCtrl.access$82(AddCstViewCtrl.this, false);
							AddCstViewCtrl.access$83(AddCstViewCtrl.this, false);
							AddCstViewCtrl.access$84(AddCstViewCtrl.this, true);
							ObjectMapper mapper = new ObjectMapper();
							final GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
							acfilter.setClient(AddCstViewCtrl.this.cur_branch_customer.getId_client());
							acfilter.setAcc_bal("2261");
							acfilter.setBranch(AddCstViewCtrl.this.branch);
							acfilter.setCurrency("000");
							acfilter.setId_order(String.valueOf(humoAccOrdFrom));
							acfilter.setLast_order(String.valueOf(humoAccOrdMax));
							final com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, (Object) acfilter, AddCstViewCtrl.this.alias);
							AddCstViewCtrl.this.accounts$accGrid.setModel((ListModel) acc_model);
							AddCstViewCtrl.this.accounts$btn_add.setDisabled(false);
							ISLogger.getLogger().error("VER10 acc_model: "
									+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(acc_model));
							ISLogger.getLogger().error("VER10 Открыть карту "
									+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(acfilter));
							ISLogger.getLogger().error("VER10 Current customer "
									+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cur_branch_customer));
							// ISLogger.getLogger().error("VER10 current tietocl: "+objectMapper.writeValueAsString(tietocl));
							accounts$btn_openCard.setVisible(true);

							currentGlobuzAccount = null;
							AddCstViewCtrl.this.accounts.setVisible(true);
							AddCstViewCtrl.this.accounts$btn_add.setVisible(false);
							AddCstViewCtrl.this.accounts$btn_cancel.setVisible(false);
						}
					});
					acc_edit_cell.appendChild((Component) AddCstViewCtrl.this.btacc);
					if (lnk != null) {
						AddCstViewCtrl.this.btbreak.setAttribute("lnk_id", (Object) lnk.id);
						if (lnk.Tieto_id != null) {
							pCustomer.setTieto_customer_id(lnk.Tieto_id);
							AddCstViewCtrl.access$59(AddCstViewCtrl.this, true);
							AddCstViewCtrl.access$62(AddCstViewCtrl.this, TclientService.getTclient_by_id(lnk.Tieto_id, AddCstViewCtrl.this.issuingPortProxy, AddCstViewCtrl.this.branch, AddCstViewCtrl.this.alias));
							row.appendChild((Component) new Listcell(lnk.Tieto_id));
							if (AddCstViewCtrl.this.tietocl != null) {
								AddCstViewCtrl.this.btedit.setAttribute("is_ti", (Object) true);
								AddCstViewCtrl.this.btedit.setAttribute("ti_cl", (Object) AddCstViewCtrl.this.tietocl);
								row.appendChild((Component) new Listcell(String.valueOf((AddCstViewCtrl.this.tietocl.getF_names() != null) ? AddCstViewCtrl.this.tietocl.getF_names()
										: "")
										+ " "
										+ ((AddCstViewCtrl.this.tietocl.getSurname() != null) ? AddCstViewCtrl.this.tietocl.getSurname()
												: "")));
								row.appendChild((Component) new Listcell((AddCstViewCtrl.this.tietocl.getB_date() != null) ? AddCstViewCtrl.this.df.format(AddCstViewCtrl.this.tietocl.getB_date())
										: "--//--"));
								AddCstViewCtrl.this.bth.setAttribute("ti_cl", (Object) AddCstViewCtrl.this.tietocl);
								AddCstViewCtrl.this.btt.setAttribute("ti_cl", (Object) AddCstViewCtrl.this.tietocl);
								AddCstViewCtrl.this.btacc.setAttribute("ti_cl", (Object) AddCstViewCtrl.this.tietocl);
								row.setAttribute("ti_cl", (Object) AddCstViewCtrl.this.tietocl);
							} else {
								AddCstViewCtrl.access$59(AddCstViewCtrl.this, false);
								row.appendChild((Component) new Listcell("--//--"));
								row.appendChild((Component) new Listcell("--//--"));
							}
						} else {
							AddCstViewCtrl.access$59(AddCstViewCtrl.this, false);
							row.appendChild((Component) new Listcell("--//--"));
							row.appendChild((Component) new Listcell("--//--"));
							row.appendChild((Component) new Listcell("--//--"));
						}
						row.appendChild((Component) t_edit_cell);
						if (lnk.Branch_id != null) {
							AddCstViewCtrl.access$70(AddCstViewCtrl.this, true);
							final String halias = CustomerService.get_alias_ho(AddCstViewCtrl.this.alias);
							final Customer branch_cst = CustomerService.getCustomerById(lnk.Branch_id, ConnectionPool.getValue("HO", AddCstViewCtrl.this.alias), halias);
							row.appendChild((Component) new Listcell(lnk.Branch_id));
							if (branch_cst != null) {
								AddCstViewCtrl.this.btedit.setAttribute("is_hi", (Object) true);
								final Listcell lc1 = new Listcell((branch_cst.getName() != null) ? branch_cst.getName()
										: "--//--");
								final Listcell lc2 = new Listcell((branch_cst.getP_birthday() != null) ? AddCstViewCtrl.this.df.format(branch_cst.getP_birthday())
										: "--//--");
								if (branch_cst.getState() != 2) {
									lc1.setStyle("background-color:#dadada;");
									lc2.setStyle("background-color:#dadada;");
									lc1.setContext((Popup) AddCstViewCtrl.this.editPopup);
									lc2.setContext((Popup) AddCstViewCtrl.this.editPopup);
									lc1.setAttribute("branch", (Object) branch_cst.getBranch());
									lc1.setAttribute("client_id", (Object) branch_cst.getId_client());
									lc1.setAttribute("alias", (Object) halias);
									lc2.setAttribute("branch", (Object) branch_cst.getBranch());
									lc2.setAttribute("client_id", (Object) branch_cst.getId_client());
									lc2.setAttribute("alias", (Object) halias);
								}
								AddCstViewCtrl.this.btedit.setAttribute("ho_cl", (Object) branch_cst);
								row.appendChild((Component) lc1);
								row.appendChild((Component) lc2);
								AddCstViewCtrl.this.bth.setAttribute("ho_cl", (Object) branch_cst);
								AddCstViewCtrl.this.btt.setAttribute("ho_cl", (Object) branch_cst);
								AddCstViewCtrl.this.btacc.setAttribute("ho_cl", (Object) branch_cst);
								row.setAttribute("ho_cl", (Object) branch_cst);
							} else {
								AddCstViewCtrl.access$70(AddCstViewCtrl.this, false);
								row.appendChild((Component) new Listcell("--//--"));
								row.appendChild((Component) new Listcell("--//--"));
							}
						} else {
							AddCstViewCtrl.access$70(AddCstViewCtrl.this, false);
							row.appendChild((Component) new Listcell("--//--"));
							row.appendChild((Component) new Listcell("--//--"));
							row.appendChild((Component) new Listcell("--//--"));
						}
					} else {
						AddCstViewCtrl.this.btbreak.setVisible(false);
						AddCstViewCtrl.access$70(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$59(AddCstViewCtrl.this, false);
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) t_edit_cell);
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
					}
					row.appendChild((Component) h_edit_cell);
					final Listcell lc3 = new Listcell(pCustomer.getId_client());
					final Listcell lc4 = new Listcell((pCustomer.getName() != null) ? pCustomer.getName()
							: "--//--");
					final Listcell lc5 = new Listcell((pCustomer.getP_birthday() != null) ? AddCstViewCtrl.this.df.format(pCustomer.getP_birthday())
							: "--//--");
					if (pCustomer.getState() != 2) {
						lc4.setStyle("background-color:#dadada;");
						lc5.setStyle("background-color:#dadada;");
						lc3.setContext((Popup) AddCstViewCtrl.this.editPopup);
						lc4.setContext((Popup) AddCstViewCtrl.this.editPopup);
						lc3.setAttribute("branch", (Object) pCustomer.getBranch());
						lc3.setAttribute("client_id", (Object) pCustomer.getId_client());
						lc3.setAttribute("alias", (Object) AddCstViewCtrl.this.alias);
						lc4.setAttribute("branch", (Object) pCustomer.getBranch());
						lc4.setAttribute("client_id", (Object) pCustomer.getId_client());
						lc4.setAttribute("alias", (Object) AddCstViewCtrl.this.alias);
					}
					row.appendChild((Component) lc3);
					row.appendChild((Component) lc4);
					row.appendChild((Component) lc5);
					row.appendChild((Component) new Listcell());
					AddCstViewCtrl.this.bth.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
							: 0));
					AddCstViewCtrl.this.bth.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
							: 0));
					AddCstViewCtrl.this.bth.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
							: 0));
					AddCstViewCtrl.this.btt.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
							: 0));
					AddCstViewCtrl.this.btt.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
							: 0));
					AddCstViewCtrl.this.btt.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
							: 0));
					AddCstViewCtrl.this.btacc.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
							: 0));
					AddCstViewCtrl.this.btacc.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
							: 0));
					AddCstViewCtrl.this.btacc.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
							: 0));
					row.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
							: 0));
					row.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
							: 0));
					row.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
							: 0));
					if (lnk != null) {
						if (lnk.Cur_acc != null) {
							row.appendChild((Component) new Listcell(lnk.Cur_acc));
							if (AddCstViewCtrl.this.is_ti
									&& AddCstViewCtrl.this.is_ho
									&& AddCstViewCtrl.this.is_br) {
								row.setStyle("background-color:#D2F7CA; font-weight:bold;");
							}
						} else {
							row.appendChild((Component) new Listcell());
						}
						row.appendChild((Component) acc_edit_cell);
					} else {
						row.appendChild((Component) new Listcell());
						row.appendChild((Component) new Listcell());
					}
					if (AddCstViewCtrl.this.is_ti) {
						AddCstViewCtrl.this.btt.setVisible(false);
					}
					if (AddCstViewCtrl.this.is_ho) {
						AddCstViewCtrl.this.bth.setVisible(false);
					}
					final Toolbarbutton sync = new Toolbarbutton();
					sync.setImage("/images/refresh.png");
					sync.setAttribute("customer", (Object) pCustomer);
					sync.setVisible(pCustomer.getTieto_customer_id() == null);
					sync.addEventListener("onClick", (EventListener) new EventListener() {
						public void onEvent(final Event event) throws Exception {
							final Toolbarbutton tb = (Toolbarbutton) event.getTarget();
							final Customer customer = (Customer) tb.getAttribute("customer");
							Tclient client = TclientService.getClientFromTietoById((String) null, String.valueOf(customer.getBranch())
									+ customer.getId_client(), AddCstViewCtrl.this.issuingPortProxy, customer.getBranch(), AddCstViewCtrl.this.alias);
							if (client == null) {
								client = TclientService.getClientFromTietoById((String) null, customer.getId_client(), AddCstViewCtrl.this.issuingPortProxy, customer.getBranch(), AddCstViewCtrl.this.alias);
							}
							if (client != null) {
								ISLogger.getLogger().error((Object) ("Client_B = " + client.getClient_b()));
								ISLogger.getLogger().error((Object) ("CLIENT_B R = " + client.getClient_b().substring(5)));
								ISLogger.getLogger().error((Object) ("Client_T = " + client.getClient()));
								TclientService.synTietoCustomers(client.getClient(), (client.getClient_b().length() == 13) ? client.getClient_b().substring(5)
										: client.getClient_b(), AddCstViewCtrl.this.branch, (Connection) null);
							}
							AddCstViewCtrl.this.refreshModelBank(AddCstViewCtrl.this._startbPageNumber);
						}
					});
					final Listcell lc6 = new Listcell();
					lc6.appendChild((Component) sync);
					row.appendChild((Component) lc6);
				}
			}
		});
	}

	private void tietoCustomersRender() {
		this.bank_customers.setItemRenderer((ListitemRenderer) new ListitemRenderer() {
			public void render(final Listitem row, final Object data) throws Exception {
				final Tclient pTclient = (Tclient) data;
				final CustomerService.link lnk = CustomerService.get_link_tieto(pTclient.getClient(), AddCstViewCtrl.this.branch, AddCstViewCtrl.this.alias);
				if (lnk != null) {
					AddCstViewCtrl.used_ids.put(lnk.id, 1);
				}
				row.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.this.branch_customers.clearSelection();
						AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
						AddCstViewCtrl.access$81(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("ho_cl"));
					}
				});
				final Listcell br_cell = new Listcell();
				AddCstViewCtrl.access$65(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.this.btbreak.setLabel("");
				AddCstViewCtrl.this.btbreak.setImage("/images/locked.png");
				AddCstViewCtrl.this.btbreak.setTooltiptext("Разделить связку");
				AddCstViewCtrl.this.btbreak.setTooltip("Удалить связку");
				AddCstViewCtrl.this.btbreak.setAttribute("ti_cl", (Object) pTclient);
				AddCstViewCtrl.this.btbreak.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
						Messagebox.show("Вы действительно хотите разделить связку Банка с EMPC(GLOB.UZ) ? ", "Предупреждение безопасности", 3, "z-msgbox z-msgbox-question", (EventListener) new EventListener() {
							public void onEvent(final Event event) throws Exception {
								final int answer = (Integer) event.getData();
								if (answer != 1) {
									return;
								}
								final Res res = CustomerService.removeT\u0441(AddCstViewCtrl.this.un, AddCstViewCtrl.this.pwd, lnk_id, AddCstViewCtrl.this.alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								AddCstViewCtrl.this.refreshModel(AddCstViewCtrl.this._starttPageNumber);
							}
						});
					}
				});
				br_cell.appendChild((Component) AddCstViewCtrl.this.btbreak);
				row.appendChild((Component) br_cell);
				final Listcell edit_cell = new Listcell();
				AddCstViewCtrl.access$68(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.this.btedit.setLabel("");
				AddCstViewCtrl.this.btedit.setImage("/images/config.png");
				AddCstViewCtrl.this.btedit.setAttribute("ti_cl", (Object) pTclient);
				AddCstViewCtrl.this.btedit.setAttribute("is_ti", (Object) true);
				AddCstViewCtrl.this.btedit.setAttribute("br_cl", (Object) null);
				AddCstViewCtrl.this.btedit.setAttribute("ho_cl", (Object) null);
				AddCstViewCtrl.this.btedit.setAttribute("is_br", (Object) false);
				AddCstViewCtrl.this.btedit.setAttribute("is_ho", (Object) false);
				AddCstViewCtrl.this.btedit.setAttribute("edit_ho", (Object) false);
				AddCstViewCtrl.this.btedit.setAttribute("edit_br", (Object) false);
				AddCstViewCtrl.this.btedit.setAttribute("edit_ti", (Object) false);
				AddCstViewCtrl.this.btedit.setTooltiptext("Редактировать везде");
				AddCstViewCtrl.this.btedit.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_ti"));
						AddCstViewCtrl.access$70(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_ho"));
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_br"));
						AddCstViewCtrl.access$81(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("ho_cl"));
						AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
						AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
						AddCstViewCtrl.access$71(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_ti"));
						AddCstViewCtrl.access$72(AddCstViewCtrl.this, (Boolean) event.getTarget().getAttribute("is_br"));
						CheckNull.clearForm(AddCstViewCtrl.this.add_everywhere$addgrdl);
						CheckNull.clearForm(AddCstViewCtrl.this.add_everywhere$addgrdr);
						if (AddCstViewCtrl.this.cur_branch_customer != null) {
							AddCstViewCtrl.this.fill_form(AddCstViewCtrl.this.cur_branch_customer, AddCstViewCtrl.this.tietocl);
						} else if (AddCstViewCtrl.this.cur_HO_customer != null) {
							AddCstViewCtrl.this.fill_form(AddCstViewCtrl.this.cur_HO_customer, AddCstViewCtrl.this.tietocl);
						} else if (AddCstViewCtrl.this.tietocl != null) {
							AddCstViewCtrl.this.fill_form(AddCstViewCtrl.this.tietocl);
						}
						AddCstViewCtrl.this.add_everywhere.setTitle("Редактирование клиента");
						AddCstViewCtrl.access$78(AddCstViewCtrl.this, true);
						AddCstViewCtrl.this.add_everywhere.setVisible(true);
					}
				});
				edit_cell.appendChild((Component) AddCstViewCtrl.this.btedit);
				row.appendChild((Component) edit_cell);
				final Listcell h_edit_cell = new Listcell();
				AddCstViewCtrl.access$79(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.this.bth.setLabel("");
				AddCstViewCtrl.this.bth.setImage("/images/edit.png");
				AddCstViewCtrl.this.bth.setAttribute("ti_cl", (Object) pTclient);
				AddCstViewCtrl.this.bth.setTooltiptext("Редактировать ГО");
				AddCstViewCtrl.this.bth.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
						AddCstViewCtrl.access$70(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ho") == 1);
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
						AddCstViewCtrl.access$81(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("ho_cl"));
						AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
						AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
						AddCstViewCtrl.access$82(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$83(AddCstViewCtrl.this, true);
						AddCstViewCtrl.access$84(AddCstViewCtrl.this, false);
						AddCstViewCtrl.this.set_default();
						AddCstViewCtrl.access$78(AddCstViewCtrl.this, false);
						AddCstViewCtrl.this.session.setAttribute("branch_filter", (Object) ConnectionPool.getValue("HO", AddCstViewCtrl.this.alias));
						AddCstViewCtrl.this.session.setAttribute("alias_filter", (Object) CustomerService.get_alias_ho(AddCstViewCtrl.this.alias));
						AddCstViewCtrl.this.addCustomer.setVisible(true);
					}
				});
				h_edit_cell.appendChild((Component) AddCstViewCtrl.this.bth);
				AddCstViewCtrl.access$59(AddCstViewCtrl.this, true);
				final Listcell b_edit_cell = new Listcell();
				AddCstViewCtrl.access$102(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.this.btb.setLabel("");
				AddCstViewCtrl.this.btb.setImage("/images/link16.png");
				AddCstViewCtrl.this.btb.setAttribute("ti_cl", (Object) pTclient);
				AddCstViewCtrl.this.btb.setTooltiptext("Редактировать филиал");
				AddCstViewCtrl.this.btb.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
						AddCstViewCtrl.access$70(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ho") == 1);
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
						AddCstViewCtrl.access$81(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("ho_cl"));
						AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
						AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
						AddCstViewCtrl.access$82(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$83(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$84(AddCstViewCtrl.this, true);
						AddCstViewCtrl.this.set_default();
						AddCstViewCtrl.access$78(AddCstViewCtrl.this, false);
						AddCstViewCtrl.this.session.setAttribute("branch_filter", (Object) AddCstViewCtrl.this.branch);
						AddCstViewCtrl.this.session.setAttribute("alias_filter", (Object) AddCstViewCtrl.this.alias);
						AddCstViewCtrl.this.addCustomer.setVisible(true);
					}
				});
				b_edit_cell.appendChild((Component) AddCstViewCtrl.this.btb);
				final Listcell acc_edit_cell = new Listcell();
				AddCstViewCtrl.access$90(AddCstViewCtrl.this, new Toolbarbutton());
				AddCstViewCtrl.this.btacc.setImage("/images/edit.png");
				AddCstViewCtrl.this.btacc.setAttribute("ti_cl", (Object) pTclient);
				AddCstViewCtrl.this.btacc.setTooltiptext("Открыть карту на счете 22618");
				AddCstViewCtrl.this.btacc.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						AddCstViewCtrl.access$59(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_ti") == 1);
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, (Integer) event.getTarget().getAttribute("is_br") == 1);
						AddCstViewCtrl.access$61(AddCstViewCtrl.this, (Customer) event.getTarget().getAttribute("br_cl"));
						AddCstViewCtrl.access$62(AddCstViewCtrl.this, (Tclient) event.getTarget().getAttribute("ti_cl"));
						AddCstViewCtrl.access$82(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$83(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$84(AddCstViewCtrl.this, true);
						final GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
						acfilter.setClient(AddCstViewCtrl.this.cur_branch_customer.getId_client());
						acfilter.setAcc_bal("2261");
						acfilter.setBranch(AddCstViewCtrl.this.branch);
						acfilter.setCurrency("000");
						acfilter.setId_order(Utils.getValue("199"));
						acfilter.setLast_order(String.valueOf(humoAccOrdVisaPrevious));
						final com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, (Object) acfilter, AddCstViewCtrl.this.alias);
						AddCstViewCtrl.this.accounts$accGrid.setModel((ListModel) acc_model);
						AddCstViewCtrl.this.accounts$btn_add.setDisabled(false);
						AddCstViewCtrl.this.accounts.setVisible(true);
						AddCstViewCtrl.this.accounts$btn_add.setVisible(false);
						AddCstViewCtrl.this.accounts$btn_cancel.setVisible(false);
					}
				});
				acc_edit_cell.appendChild((Component) AddCstViewCtrl.this.btacc);
				row.setValue((Object) pTclient);
				row.appendChild((Component) new Listcell((pTclient.getClient() != null) ? pTclient.getClient()
						: "--//--"));
				row.appendChild((Component) new Listcell(String.valueOf((pTclient.getF_names() != null) ? pTclient.getF_names()
						: "")
						+ " "
						+ ((pTclient.getSurname() != null) ? pTclient.getSurname()
								: "")));
				row.appendChild((Component) new Listcell((pTclient.getB_date() != null) ? AddCstViewCtrl.this.df.format(pTclient.getB_date())
						: "--//--"));
				row.appendChild((Component) new Listcell());
				if (lnk != null) {
					AddCstViewCtrl.this.btbreak.setAttribute("lnk_id", (Object) lnk.id);
					AddCstViewCtrl.access$70(AddCstViewCtrl.this, false);
					row.appendChild((Component) new Listcell("--//--"));
					row.appendChild((Component) new Listcell("--//--"));
					row.appendChild((Component) new Listcell("--//--"));
					row.appendChild((Component) h_edit_cell);
					if (lnk.Branch_id != null) {
						final Customer branch_cst = CustomerService.getCustomerById(lnk.Branch_id, AddCstViewCtrl.this.branch, AddCstViewCtrl.this.alias);
						row.appendChild((Component) new Listcell(lnk.Branch_id));
						AddCstViewCtrl.this.btedit.setAttribute("is_br", (Object) true);
						if (branch_cst != null) {
							AddCstViewCtrl.this.btedit.setAttribute("br_cl", (Object) branch_cst);
							AddCstViewCtrl.access$60(AddCstViewCtrl.this, true);
							final Listcell lc1 = new Listcell((branch_cst.getName() != null) ? branch_cst.getName()
									: "--//--");
							final Listcell lc2 = new Listcell((branch_cst.getP_birthday() != null) ? AddCstViewCtrl.this.df.format(branch_cst.getP_birthday())
									: "--//--");
							if (branch_cst.getState() != 2) {
								lc1.setStyle("background-color:#dadada;");
								lc2.setStyle("background-color:#dadada;");
								lc1.setContext((Popup) AddCstViewCtrl.this.editPopup);
								lc2.setContext((Popup) AddCstViewCtrl.this.editPopup);
								lc1.setAttribute("branch", (Object) branch_cst.getBranch());
								lc1.setAttribute("client_id", (Object) branch_cst.getId_client());
								lc1.setAttribute("alias", (Object) AddCstViewCtrl.this.alias);
								lc2.setAttribute("branch", (Object) branch_cst.getBranch());
								lc2.setAttribute("client_id", (Object) branch_cst.getId_client());
								lc2.setAttribute("alias", (Object) AddCstViewCtrl.this.alias);
							}
							row.appendChild((Component) lc1);
							row.appendChild((Component) lc2);
							AddCstViewCtrl.this.bth.setAttribute("br_cl", (Object) branch_cst);
							AddCstViewCtrl.this.btb.setAttribute("br_cl", (Object) branch_cst);
							AddCstViewCtrl.this.btacc.setAttribute("br_cl", (Object) branch_cst);
							row.setAttribute("br_cl", (Object) branch_cst);
						} else {

							AddCstViewCtrl.this.btacc.setVisible(false);
							AddCstViewCtrl.access$60(AddCstViewCtrl.this, false);
							row.appendChild((Component) new Listcell("--//--"));
							row.appendChild((Component) new Listcell("--//--"));
						}
					} else {
						AddCstViewCtrl.this.btacc.setVisible(false);
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, false);
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
					}
					row.appendChild((Component) b_edit_cell);
					if (lnk.Cur_acc != null) {
						row.appendChild((Component) new Listcell(lnk.Cur_acc));
						if (AddCstViewCtrl.this.is_ti
								&& AddCstViewCtrl.this.is_ho
								&& AddCstViewCtrl.this.is_br) {
							row.setStyle("background-color:#D2F7CA; font-weight:bold;");
						}
					} else {
						row.appendChild((Component) new Listcell());
					}
				} else {
					AddCstViewCtrl.this.btbreak.setVisible(false);
					AddCstViewCtrl.this.btacc.setVisible(false);
					final String pre_ho = CustomerService.get_HO_by_tieto(pTclient.getClient(), AddCstViewCtrl.this.alias);
					if (pre_ho != null) {
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$70(AddCstViewCtrl.this, true);
						final String halias = CustomerService.get_alias_ho(AddCstViewCtrl.this.alias);
						final Customer head_cst = CustomerService.getCustomerById(pre_ho, ConnectionPool.getValue("HO", AddCstViewCtrl.this.alias), halias);
						row.appendChild((Component) new Listcell(pre_ho));
						if (head_cst != null) {
							AddCstViewCtrl.this.btedit.setAttribute("ho_cl", (Object) head_cst);
							row.appendChild((Component) new Listcell((head_cst.getName() != null) ? head_cst.getName()
									: "--//--"));
							row.appendChild((Component) new Listcell((head_cst.getP_birthday() != null) ? AddCstViewCtrl.this.df.format(head_cst.getP_birthday())
									: "--//--"));
							AddCstViewCtrl.this.bth.setAttribute("ho_cl", (Object) head_cst);
							AddCstViewCtrl.this.btb.setAttribute("ho_cl", (Object) head_cst);
							AddCstViewCtrl.this.btacc.setAttribute("ho_cl", (Object) head_cst);
						} else {
							AddCstViewCtrl.access$70(AddCstViewCtrl.this, false);
							row.appendChild((Component) new Listcell("--//--"));
							row.appendChild((Component) new Listcell("--//--"));
						}
						row.appendChild((Component) h_edit_cell);
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						AddCstViewCtrl.this.btb.setAttribute("cur_branch", (Object) AddCstViewCtrl.this.branch);
						AddCstViewCtrl.this.btacc.setAttribute("cur_branch", (Object) AddCstViewCtrl.this.branch);
						AddCstViewCtrl.this.btb.setAttribute("cur_tieto_id", (Object) pTclient.getClient());
						AddCstViewCtrl.this.btacc.setAttribute("cur_tieto_id", (Object) pTclient.getClient());
						row.appendChild((Component) b_edit_cell);
					} else {
						AddCstViewCtrl.access$60(AddCstViewCtrl.this, false);
						AddCstViewCtrl.access$70(AddCstViewCtrl.this, false);
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) h_edit_cell);
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						row.appendChild((Component) new Listcell("--//--"));
						AddCstViewCtrl.this.btb.setAttribute("cur_branch", (Object) AddCstViewCtrl.this.branch);
						AddCstViewCtrl.this.btb.setAttribute("cur_tieto_id", (Object) pTclient.getClient());
						AddCstViewCtrl.this.btacc.setAttribute("cur_branch", (Object) AddCstViewCtrl.this.branch);
						AddCstViewCtrl.this.btacc.setAttribute("cur_tieto_id", (Object) pTclient.getClient());
						row.appendChild((Component) b_edit_cell);
					}
					row.appendChild((Component) new Listcell());
				}
				AddCstViewCtrl.this.bth.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
						: 0));
				AddCstViewCtrl.this.bth.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
						: 0));
				AddCstViewCtrl.this.bth.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
						: 0));
				AddCstViewCtrl.this.btb.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
						: 0));
				AddCstViewCtrl.this.btb.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
						: 0));
				AddCstViewCtrl.this.btb.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
						: 0));
				AddCstViewCtrl.this.btacc.setAttribute("is_ti", (Object) (int) (AddCstViewCtrl.this.is_ti ? 1
						: 0));
				AddCstViewCtrl.this.btacc.setAttribute("is_ho", (Object) (int) (AddCstViewCtrl.this.is_ho ? 1
						: 0));
				AddCstViewCtrl.this.btacc.setAttribute("is_br", (Object) (int) (AddCstViewCtrl.this.is_br ? 1
						: 0));
				row.appendChild((Component) acc_edit_cell);
				if (AddCstViewCtrl.this.is_ho) {
					AddCstViewCtrl.this.bth.setVisible(false);
				}
				if (AddCstViewCtrl.this.is_br) {
					AddCstViewCtrl.this.btb.setVisible(false);
				}
			}
		});
	}

	public void onPaging$bankdataPaging(final ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		this.refreshModelBank(this._startPageNumber = pe.getActivePage());
	}

	public void onClick$cancelBtn$cardTypeSel() {
		cardTypeSel.setVisible(false);
	}

	public void onClick$tbtn_uploaded() {
		uploadedTable$tempListBox.setModel(new ListModelList(CustomerService.getTemp(null, branch), true));
		uploadedTable$selectedExcel.setModel(new ListModelList(CustomerService.getExcelList(branch, null)));
		uploadedTable.setVisible(true);
	}

	public void onClick$btn_registerAll$uploadedTable() {
		clientType.setDisabled(false);
		tbtn_register.setDisabled(true);
		uploadedTable$btn_deleteExcel.setDisabled(true);
		onClick$tbtn_register();
		uploadedTable$selectedExcel.setSelectedIndex(-1);
		uploadedTable$tempListBox.setModel(new ListModelList(CustomerService.getTemp(null, branch), true));
		uploadedTable$selectedExcel.setModel(new ListModelList(CustomerService.getExcelList(branch, null)));
	}

	public void onClick$btn_close_uploadedTable$uploadedTable() {
		uploadedTable.setVisible(false);
	}

	public void onClick$btn_deleteExcel$uploadedTable() {
		CustomerService.deleteExcel(branch, selectedExcel);
		uploadedTable$btn_registerAll.setDisabled(true);
		uploadedTable$btn_deleteExcel.setDisabled(true);
		uploadedTable$selectedExcel.setSelectedIndex(-1);
		uploadedTable$tempListBox.setModel(new ListModelList(CustomerService.getTemp(null, branch), true));
		uploadedTable$selectedExcel.setModel(new ListModelList(CustomerService.getExcelList(branch, null)));
		tbtn_register.setDisabled(false);
		clientType.setDisabled(false);

	}

	public void onSelect$selectedExcel$uploadedTable() {
		selectedExcel = uploadedTable$selectedExcel.getValue();
		uploadedTable$tempListBox.setModel(new ListModelList(CustomerService.getTemp(selectedExcel, branch), true));
		String stateString = CustomerService.checkTableState(branch, selectedExcel);
		ISLogger.getLogger().error("state: " + stateString);
		if (!(stateString.equals("different"))) {
			int state = Integer.parseInt(stateString);

			if (state == 1) {
				uploadedTable$btn_registerAll.setDisabled(false);
				uploadedTable$btn_deleteExcel.setDisabled(true);
			} else if (state == 2 || state == 4) {
				uploadedTable$btn_registerAll.setDisabled(true);
				uploadedTable$btn_deleteExcel.setDisabled(true);
			} else if (state == 3) {
				uploadedTable$btn_registerAll.setDisabled(true);
				uploadedTable$btn_deleteExcel.setDisabled(false);
			}
		} else {
			uploadedTable$btn_registerAll.setDisabled(true);
			uploadedTable$btn_deleteExcel.setDisabled(true);
		}
	}

	public void onChange$cardTypeSelection$cardTypeSel() {
		cardProductType = cardTypeSel$cardTypeSelection.getValue();
	}

	// /////////////////////////ОТКРЫТЬ КАРТУ///////ОТКРЫТИЕ
	// КАРТЫ////////////НОВАЯ КАРТА///////////////////
	public void onClick$selectBtn$cardTypeSel() throws Exception {
		HashMap<String, EmpcSettings> settingsForEach = new HashMap<String, EmpcSettings>();
		cardProductType = cardTypeSel$cardTypeSelection.getValue();
		ISLogger.getLogger().error("currentGlobuzAccount: "
				+ currentGlobuzAccount.getId().substring(9, 17));
		ISLogger.getLogger().error("cur_branch_customer: "
				+ cur_branch_customer.getId_client());
		if (CustomerService.checkAccountAssertion(currentGlobuzAccount.getId(), branch, alias)) {
			if (currentGlobuzAccount.getId().substring(9, 17).equals(cur_branch_customer.getId_client())) {
				int accOrd = Integer.parseInt(currentGlobuzAccount.getId().substring(17));

				// ///////////ФИЗИЧЕСКАЯ КАРТА///////ФИЗИЧЕСКИЕ КАРТЫ///////////
				if (cardProductType.equals("4")) {
					if (!(humoAccOrdFrom != 0 && humoAccOrdPrevious != 0
							&& humoAccOrdFrom <= accOrd
							&& accOrd <= humoAccOrdPrevious && Integer.parseInt(currentGlobuzAccount.getId().substring(0, 5)) != 22617)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}

					// ////////КРЕДИТНАЯ КАРТА/////////КРЕДИТНЫЕ
					// КАРТЫ////////КРЕДИТКА/////////
				} else if (cardProductType.equals("5")) {
					if (!(humoCreditOrdFrom != 0 && humoCreditOrdPrevious != 0
							&& humoCreditOrdFrom <= accOrd
							&& accOrd <= humoCreditOrdPrevious && Integer.parseInt(currentGlobuzAccount.getId().substring(0, 5)) != 22617)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}

					// //////ПЕНСИОННАЯ КАРТА////////ПЕНСИОННЫЕ
					// КАРТЫ//////////ПЕНСИЯ///////////
				} else if (cardProductType.equals("7")) {
					if (!(humoAccOrdFrom != 0 && humoAccOrdPrevious != 0
							&& humoAccOrdFrom <= accOrd
							&& accOrd <= humoAccOrdPrevious && Integer.parseInt(currentGlobuzAccount.getId().substring(0, 5)) == 22617)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}

					// ///////////ИПАК ИНДИВИДУАЛЬНЫЙ ДИЗАЙИН////////////
				} else if (cardProductType.equals("6")) {
					if (!(humoAccOrdFrom != 0 && humoAccOrdPrevious != 0
							&& humoAccOrdFrom <= accOrd
							&& accOrd <= humoAccOrdPrevious && Integer.parseInt(currentGlobuzAccount.getId().substring(0, 5)) != 22617)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}

					// ///////////АГРО КОБРЕНДОВАЯ КАРТА//////////КОБРЕНДОВЫЕ
					// КАРТЫ/////////КОБРЕНД///////COBRAND///////CO-BRAND/////
				} else if (cardProductType.equals("8")) {
					if (!(humoCobrandOrdFrom != 0
							&& humoCobrandOrdPrevious != 0
							&& humoCobrandOrdFrom <= accOrd
							&& accOrd <= humoCobrandOrdPrevious && Integer.parseInt(currentGlobuzAccount.getId().substring(0, 5)) != 22617)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}

					// //////АГРО ВИЗА КОБЕЙДЖИНГ////////////VISA
					// COBADGING/////////CO-BADGE/////CO-BADGING
				} else if (cardProductType.equals("9")) {
					if (!(humoAccOrdVisaFrom != 0
							&& humoAccOrdVisaPrevious != 0
							&& humoAccOrdVisaFrom <= accOrd && accOrd <= humoAccOrdVisaPrevious)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}

					// //АГРО AGRO HAYRIYA ХАЙРИЯ XAYRIYA
				} else if (cardProductType.equals("11")) {
					if (!(humoAccOrdHayriyaFrom != 0
							&& humoAccOrdHayriyaPrevious != 0
							&& humoAccOrdHayriyaFrom <= accOrd && accOrd <= humoAccOrdHayriyaPrevious)) {
						cardTypeSel$cardTypeSelection.setSelectedIndex(-1);
						alert("Неверный тип счёта!");
						return;
					}
				}

				if (!CustomerService.checkAccount(currentGlobuzAccount.getId(), branch, alias)) {
					final Res res = CustomerService.update_lnk_set_acc(AddCstViewCtrl.this.un, AddCstViewCtrl.this.pwd, AddCstViewCtrl.this.branch, AddCstViewCtrl.this.cur_branch_customer.getId_client(), currentGlobuzAccount, AddCstViewCtrl.this.alias, AddCstViewCtrl.this.issuingPortProxy);
					if (res.getCode() == 1) {
						final Customer lg_c = CustomerService.getCustomerById(AddCstViewCtrl.this.cur_branch_customer.getId_client(), AddCstViewCtrl.this.branch, AddCstViewCtrl.this.alias);
						Res resAgreement = new Res();
						final int cnt = CustomerService.getCountCards(tietocl, currentGlobuzAccount);
						if (cnt == 0) {
							Res codeRes = new Res();
							TclientService.initSettingsByCardType(branch, cardProductType);
							//Настройки минибанков для Ипак Йули, Инфина, Давра и Зираата
							if (HUMO_BANK_C.equals("17")
									|| HUMO_BANK_C.equals("26")
									|| HUMO_BANK_C.equals("25")
									|| HUMO_BANK_C.equals("29")) {
								codeRes = CustomerService.getSubsidiaryUserCode(branch, uid, alias);
								if (codeRes.getCode() == 0) {
									if (codeRes.getName().equals("00000")) {
										codeRes.setCode(-1);
										settingsForEach = (HashMap<String, EmpcSettings>) TclientService.getSettingsByCardType();
									} else {
										settingsForEach = (HashMap<String, EmpcSettings>) CustomerService.getSettingsForMinibank(branch, codeRes.getName().substring(2));
										if (settingsForEach.get(branch).getBank_c().equals("0")) {
											codeRes.setCode(-1);
											settingsForEach = (HashMap<String, EmpcSettings>) TclientService.getSettingsByCardType();
										}
									}
								} else {
									settingsForEach = (HashMap<String, EmpcSettings>) TclientService.getSettingsByCardType();
								}
							} else {
								settingsForEach = (HashMap<String, EmpcSettings>) TclientService.getSettingsByCardType();
							}
							ISLogger.getLogger().error("newCard product type: "
									+ cardProductType
									+ "\nbin: "
									+ settingsForEach.get(branch).getBincod()
									+ "\nbranch_id: "
									+ settingsForEach.get(branch).getBranch_id()
									+ "\nrange_id: "
									+ settingsForEach.get(branch).getRange_id());
							resAgreement = CustomerService.addNewAgreement(settingsForEach, branch, lg_c, currentGlobuzAccount, tietocl, alias, issuingPortProxy, uid, un, soapEndpointUrl, soapAction, cardProductType, codeRes);
							alert(resAgreement.getName());
							if (resAgreement.getCode() != 1) {
								return;
							}

							AddCstViewCtrl.this.refreshModel(AddCstViewCtrl.this._startPageNumber);
							AddCstViewCtrl.this.accounts.setVisible(false);
						} else {
							alert("У клиента уже есть карта на данном счёте.");
							return;
						}
						return;
					}
					alert(res.getName());
				} else {
					alert("Счёт присутствует в системе UzCard. Открыть карту на него нельзя.");
				}
			} else {
				alert("Счёт не совпадает");
			}
		} else {
			alert("Счёт не утверждён");
		}
		this.refreshModel(this._startPageNumber);
	}

	public void onClick$closeBtn$cardTypeSel() throws Exception {
		cardTypeSel.setVisible(false);
	}

	private void refreshModelBank(final int activePage) {
		this.bankdataPaging.setPageSize(this._pageSize);
		this.bfilter.setState(2);
		this.bfilter.setBranch(this.branch);
		this.bmodel = new PagingListModel(activePage, this._pageSize, this.bfilter, this.alias);
		this.branch_customers.setModel((ListModel) this.bmodel);
		if (this._needsTotalSizeUpdate) {
			this._totalSize = this.bmodel.getTotalSize(this.bfilter, this.alias);
		}
		this.bankdataPaging.setTotalSize(this._totalSize);
		if (this.bmodel.getSize() > 0) {
			this.sendSelEvt();
		}
	}

	/*
	 * if (this.branch.equals("00394")) { this.firstCardName = "4"; } else if
	 * (this.branch.equals("00391")) { this.firstCardName = "5"; } else {
	 * this.alert("Неверный тип карты"); } final MassOpenCard mo = new
	 * MassOpenCard(); final PacketOpenCard cards = new PacketOpenCard(); final
	 * TclientService tcs = new TclientService(); try {
	 * tcs.newAgreementHumo(this.c, this.alias, this.branch,
	 * this.current.getId_client(), this.firstCardName, this.current,
	 * this.clientIdHumo, this.acc, cards.getCardType()); } catch (Exception e)
	 * { e.printStackTrace(); }
	 */

	public void onClick$btn_fill_globuz() {
		try {
			this.tmodel = new com.is.tieto_globuz.tieto.PagingListModel(this._startpageGlobuz, this._pageSize, (Object) this.tfilter, this.alias, this.issuingPortProxy);
		} catch (Exception e) {
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			e.printStackTrace();
			System.out.println("Exception => " + e.getLocalizedMessage());
			ISLogger.getLogger().error((Object) ("EXCEPTION: " + e.getLocalizedMessage()));
		}
		this.bank_customers.setModel((ListModel) this.tmodel);
		if (this.tmodel.getSize() > 0) {
			this.sendtSelEvt();
		}
	}

	public void onClick$btn_ref_globuz() {
		this.onClick$btn_fill_globuz();
	}

	private void refreshModel(final int activePage) {
		AddCstViewCtrl.used_ids = new HashMap<Integer, Integer>();
		this.refreshModelBank(activePage);
	}

	public Customer getCurrent() {
		return this.current;
	}

	public void setCurrent(final Customer current) {
		this.current = current;
	}

	public Customer getTcustomer() {
		return this.tcustomer;
	}

	public void setTcustomer(final Customer tcustomer) {
		this.tcustomer = tcustomer;
	}

	public Tclient getTietocl() {
		return this.tietocl;
	}

	public void setTietocl1(final Tclient tietocl) {
		this.tietocl = tietocl;
	}

	public void setAtcust(final Tclient atcust) {
		this.atcust = atcust;
	}

	public void onClick$tbtn_card_oprations() {
		this.showCardRender(true);
		try {
			this.issuingPortProxy = initNp(this.alias);
			System.out.println(Utils.getValue("EMPC_TIETO_HOST"));
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			try {
				Messagebox.show("[Coonection Problem]=>"
						+ e.getLocalizedMessage(), e.getMessage(), 514, "z-msgbox z-msgbox-error", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						final int answer = (Integer) event.getData();
						if (answer == 512) {
							AddCstViewCtrl.access$2(AddCstViewCtrl.this, initNp(AddCstViewCtrl.this.alias));
						}
					}
				});
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
		}
		if (this.tietocl != null) {
			this.tcust.setTietoCustomerId(this.tietocl.getClient());
			List<AccInfo> showCardsList = TclientService.getAccInfoShowCards(this.branch, this.tietocl.getClient(), this.alias, this.issuingPortProxy);
			this.accGrid.setModel((ListModel) new BindingListModelList((List) this.makeList(showCardsList), false));
			if (tietocl.getClient_b() != null) {
				ISLogger.getLogger().error("checkCard for bankClient "
						+ tietocl.getClient_b() + " humoId "
						+ tietocl.getClient());
				CustomerService.checkCard(showCardsList, tietocl.getClient_b().length() == 8 ? tietocl.getClient_b()
						: tietocl.getClient_b().substring(5), issuingPortProxy, branch, alias, settings);
			}
			bank_customers.setVisible(false);
			tgrd.setVisible(false);
			AddCstViewCtrl.access$103(AddCstViewCtrl.this, new Button());
			btrefresh_card.setVisible(false);
			this.show_cards.setVisible(true);
		}
	}

	public void onSelect$branch_customers() {
		if (cur_branch_customer != null) {
			try {
				ISLogger.getLogger().error("CURRENT BRANCH CUSTOMER: "
						+ objectMapper.writeValueAsString(cur_branch_customer));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cur_branch_customer.getId_client() != null
					&& cur_branch_customer.getTieto_customer_id() != null) {
				TclientService.getTclient_by_id(cur_branch_customer.getTieto_customer_id(), cur_branch_customer.getId_client(), issuingPortProxy, AddCstViewCtrl.this.branch, AddCstViewCtrl.this.alias);
			} else {
				ISLogger.getLogger().error("cur_branch_customer data is missing");
			}
		} else {
			ISLogger.getLogger().error("cur_branch_customer data is missing");
		}
	}

	public void onDoubleClick$bank_customers() {
		this.onClick$tbtn_card_oprations();
	}

	public void onDoubleClick$head_customers() {
		this.onClick$tbtn_card_oprations();
	}

	public void onDoubleClick$branch_customers() {
		this.onClick$tbtn_card_oprations();
	}

	public void onDoubleClick$customersList$customers() {
		add_everywhere$customerId.setValue(currentCustomer.getId_client());
		customers.setVisible(false);
	}

	private void sendtSelEvt() {
		final SelectEvent evt = new SelectEvent("onSelect", (Component) this.bank_customers, this.bank_customers.getSelectedItems());
		Events.sendEvent((Event) evt);
	}

	private void sendSelEvt() {
		final SelectEvent evt = new SelectEvent("onSelect", (Component) this.branch_customers, this.branch_customers.getSelectedItems());
		Events.sendEvent((Event) evt);
	}

	public void onClick$btn_searchb() {
		this.refreshModel(this._startPageNumber);
	}

	public void onClick$tbtn_search() {
		this.filter.setP_birthday(this.tfilter.getB_date());
		this.filter.setName(this.tfilter.getF_names());
		this.filter.setId_client(this.tfilter.getClient());
		this.filter.setBranch(this.branch);
		this.filter.setCard(this.tfilter.getCard());
		this.filter.setP_passport_serial(passportNumber.getValue().isEmpty() ? ""
				: passportNumber.getValue().substring(0, 2));
		this.filter.setP_passport_number(passportNumber.getValue().isEmpty() ? ""
				: passportNumber.getValue().substring(2));
		this.bfilter = this.filter;
		this.refreshModel(this._startPageNumber);

	}

	public void onClick$btn_add_everywhere() {
		// CustomerService.UsrLog(new UserActionsLog(null, branch, uid, un,
		// CustomerService.getIp(), null, 6, 1,
		// "Карта No ["+22+"] заблокирована по причине "+"22"+""));

		this.add_ti = true;
		this.add_ho = true;
		this.add_br = true;
		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);
		this.add_everywhere.setTitle("Открытие клиента [БАНК] - [НПС HUMO]");
		this.add_everywhere$acode_resident.setSelecteditem("1");
		this.add_everywhere$ap_code_citizenship.setSelecteditem("860");
		this.add_everywhere$acode_country.setSelecteditem("860");
		this.add_everywhere.setVisible(true);
	}

	public void onClick$tbtn_add() {
		(this.atcust = this.getTFromBank(this.current, this.atcust)).setBank_c(this.addTieto$abank_c.getValue());
		this.alert("Открывается в Tieto : " + this.atcust.getSearch_name()
				+ " l " + this.addTieto$asearch_name.getValue());
		this.addTieto.setVisible(true);
	}

	public void onClick$btn_add() {
		System.out.println(" btn_add -----------------------------");
		this.tcustomer.setName(this.tietocl.getSearch_name());
		this.tcustomer.setBranch(this.branch);
		this.tcustomer.setCode_country("860");
		this.tcustomer.setP_code_class_credit("1");
		this.tcustomer.setP_passport_type("N");
		this.tcustomer.setCode_subject("P");
		this.tcustomer.setSign_registr(2);
		this.tcustomer.setCode_form("");
		this.tcustomer.setCode_type("08");
		this.tcustomer.setP_passport_number(this.tietocl.getSerial_no());
		this.tcustomer.setP_first_name(this.tietocl.getF_names());
		this.tcustomer.setP_family(this.tietocl.getM_name());
		this.tcustomer.setP_birthday(this.tietocl.getB_date());
		this.sendSelEvt();
		this.tcustomer = this.getBFromTieto(this.tietocl, this.tcustomer);
		this.addCust.setVisible(true);
	}

	public void onClick$btn_addb() {
		this.tcustomer.setName(this.tietocl.getSearch_name());
		this.tcustomer.setBranch(this.branch);
		this.tcustomer.setCode_country("860");
		this.tcustomer.setP_code_class_credit("1");
		this.tcustomer.setP_passport_type("N");
		this.tcustomer.setCode_subject("P");
		this.tcustomer.setSign_registr(2);
		this.tcustomer.setCode_form("");
		this.tcustomer.setCode_type("08");
		this.tcustomer.setP_passport_number(this.tietocl.getSerial_no());
		this.tcustomer.setP_first_name(this.tietocl.getF_names());
		this.tcustomer.setP_family(this.tietocl.getM_name());
		this.tcustomer.setP_birthday(this.tietocl.getB_date());
		this.sendSelEvt();
		this.addCust.setVisible(true);
	}

	public void onClick$tnbtn_add$addTieto() throws JsonProcessingException {
		final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		final RowType_Customer rtc = new RowType_Customer();
		rtc.setF_NAMES(this.current.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(this.current.getId_client());
		rtc.setSURNAME(this.current.getP_family());
		rtc.setM_NAME(this.current.getP_patronymic());
		final Calendar cal = Calendar.getInstance();
		cal.setTime(this.current.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal);
		cal.setTime(this.current.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT("1");
		rtc.setSTATUS("10");
		rtc.setSEX("1");
		if (this.current.getP_inps() != null) {
			rtc.setPERSON_CODE(this.current.getPinfl());
		}
		final RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		final ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		try {
			connectionInfo.setBANK_C(AddCstViewCtrl.settings.get(this.branch).getBank_c());
			connectionInfo.setGROUPC(AddCstViewCtrl.settings.get(this.branch).getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			ISLogger.getLogger().error("newCustomer request for bank client " + current.getId_client()
					+ "\n" + objectMapper.writeValueAsString(connectionInfo)
					+ "\n" + objectMapper.writeValueAsString(customerInfo));
			orInfo = this.issuingPortProxy.newCustomer(connectionInfo, customerInfo, customListInfo);
			ISLogger.getLogger().error("newCustomer response for bank client " + current.getId_client()
					+ "\n" + objectMapper.writeValueAsString(orInfo));
			this.alert(String.valueOf(orInfo.getError_action()) + "\r\n"
					+ orInfo.getError_description());
		} catch (RemoteException e) {
			LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
		}
		this.addTieto.setVisible(false);
	}

	public void onClick$tnbtn_cancel$addTieto() {
		CheckNull.clearForm(this.addTieto$addtgrdl);
		CheckNull.clearForm(this.addTieto$addtgrdr);
		this.addTieto.setVisible(false);
	}

	public void onClick$bnbtn_add$addCust() {
		this.tcustomer.setName(String.valueOf(this.tcustomer.getP_first_name())
				+ " " + this.tcustomer.getP_family());
		this.tcustomer.setP_code_class_credit("1");
		this.tcustomer.setP_passport_type("N");
		this.tcustomer.setCode_subject("P");
		this.tcustomer.setSign_registr(2);
		this.tcustomer.setCode_form("");
		this.tcustomer.setCode_type("08");
		this.tcustomer.setP_passport_serial(this.addCust$ap_passport_serial.getValue());
		this.tcustomer.setP_passport_number(this.addCust$ap_passport_number.getValue());
		final Res res = CustomerService.doAction(this.session.getAttribute("un").toString(), this.session.getAttribute("pwd").toString(), this.tcustomer, 1, 2, this.alias, true);
		if (res.getCode() != 0) {
			this.alert(res.getName());
		} else {
			this.filter.setP_passport_number(this.tcustomer.getP_passport_number());
			this.filter.setP_birthday(this.tcustomer.getP_birthday());
			this.refreshModel(this._startPageNumber);
			this.addCust.setVisible(false);
			this.alert("Клиент добавлен");
		}
	}

	public void onClick$btn_openCard$accounts() {
		if (currentGlobuzAccount == null) {
			alert("Выберите клиента");
			return;
		}
		if (currentGlobuzAccount.getAcc_bal() != null) {
			System.out.println("accBal: " + currentGlobuzAccount.getAcc_bal());
		}
		if (currentGlobuzAccount.getBranch() != null) {
			System.out.println("branch: " + currentGlobuzAccount.getBranch());
		}
		if (currentGlobuzAccount.getClient() != null) {
			System.out.println("client: " + currentGlobuzAccount.getClient());
		}
		if (currentGlobuzAccount.getId() != null) {
			System.out.println("id: " + currentGlobuzAccount.getId());
		}
		cardTypeSel.setVisible(true);
	}

	public void onClick$bnbtn_cancel$addCust() {
		CheckNull.clearForm(this.addCust$addgrdl);
		CheckNull.clearForm(this.addCust$addgrdr);
		this.addCust.setVisible(false);
	}

	public void onClick$btn_bind() {
		CustomerService.create(this.tcust, un, pwd, this.alias);
		this.alert("Связаны банковский " + this.tcust.getBankCustomerId()
				+ " и Тието " + this.tcust.getTietoCustomerId());
	}

	public void onOK$ff_names() {
		this.onClick$tbtn_search();
	}

	public void onOK$fsearch_name() {
		this.onClick$tbtn_search();
	}

	public void onOK$fsurname() {
		this.onClick$tbtn_search();
	}

	public void onOK$fb_date() {
		this.onClick$tbtn_search();
	}

	public void onOK$fserial_no() {
		this.onClick$tbtn_search();
	}

	public void onOK$card() {
		this.onClick$tbtn_search();
	}

	public void onClick$btn_cancel$accwnd() {
		this.accwnd.setVisible(false);
	}

	public void onClick$btn_addacc$accwnd() {
		final GlobuzAccount globuzAccount = new GlobuzAccount();
		globuzAccount.setBranch(this.current.getBranch());
		globuzAccount.setAcc_bal("20206");
		globuzAccount.setCurrency("840");
		globuzAccount.setName(this.current.getName());
		globuzAccount.setId_order("001");
		globuzAccount.setSgn("P");
		globuzAccount.setBal("B");
		globuzAccount.setSign_registr(2);
		globuzAccount.setClient(this.current.getId_client());
		Res res = GlobuzAccountService.doAction(this.un, this.pwd, globuzAccount, 1, this.alias, Boolean.valueOf(true));
		if (res.getCode() == 0) {
			globuzAccount.setId(res.getName());
			res = GlobuzAccountService.doAction(this.un, this.pwd, globuzAccount, 2, this.alias, Boolean.valueOf(true));
			this.alert(res.getName());
		} else {
			this.alert(res.getName());
		}
		this.accwnd$scurracc.setModel((ListModel) new ListModelList((Collection) Utils.getCurrAcc(this.current.getId_client(), this.alias)));
	}

	public void onClick$btn_print$accwnd() {
		this.printwnd.setVisible(true);
		this.accwnd.setVisible(false);
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_BRANCH", this.current.getBranch());
		params.put("P_CLIENT_ID", this.current.getId_client());
		params.put("P_ACCOUNT", this.accwnd$scurracc.getValue());
		this.printwnd$rpframe.setContent((Media) DPrint.getRepPdf("TI_APPLICATION.pdf", this.application.getRealPath("reports/TI_APPLICATION.jasper"), (Map) params, this.alias));
	}

	public void onClick$btn_prn_app() {
		this.accwnd$scurracc.setModel((ListModel) new ListModelList((Collection) Utils.getCurrAcc(this.current.getId_client(), this.alias)));
		this.accwnd.setVisible(true);
	}

	public void onClick$btn_prn_cap() {
		if (this.tietocl == null) {
			this.alert("Клиент не выбран либо не объединен.");
			return;
		}
		this.printwnd.setVisible(true);
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PHONE_PASSWORD", "PASSWORD");
		params.put("CARD_TYPE", "VISA GOLD");
		params.put("P_BRANCH", this.branch);
		params.put("P_CLIENT_ID", this.tietocl.getClient());
		this.printwnd$rpframe.setContent((Media) DPrint.getRepPdf("TI_APPLICATION_VASA_CAP.pdf", this.application.getRealPath("reports/TI_APPLICATION_VASA_CAP.jasper"), (Map) params, this.alias));
	}

	public void onClick$btn_prn_prm() {
		this.printwnd.setVisible(true);
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PHONE_PASSWORD", "PASSWORD");
		params.put("CARD_TYPE", "VISA GOLD");
		params.put("P_BRANCH", this.current.getBranch());
		params.put("P_CLIENT_ID", this.current.getId_client());
		this.printwnd$rpframe.setContent((Media) DPrint.getRepPdf("TI_DOGOVOR_VISA_PREMIUM.pdf", this.application.getRealPath("reports/TI_DOGOVOR_VISA_PREMIUM.jasper"), (Map) params, this.alias));
	}

	public void onSelect$ap_code_birth_region$addCust(final Event evt) {
		this.addCust$ap_code_birth_region.setSelectedIndex(-1);
		this.addCust$ap_code_birth_region.setModel((ListModel) new ListModelList((Collection) Utils.getDistr(this.addCust$ap_code_birth_region.getValue(), this.alias)));
	}

	public void onSelect$ap_code_adr_region$addCust(final Event evt) {
		this.addCust$ap_code_adr_distr.setSelectedIndex(-1);
		this.addCust$ap_code_adr_distr.setModel((ListModel) new ListModelList((Collection) Utils.getDistr(this.addCust$ap_code_adr_region.getValue(), this.alias)));
	}

	private Tclient getTFromBank(final Customer cust, final Tclient tcust) {
		tcust.setBank_c(AddCstViewCtrl.settings.get(this.branch).getBank_c());
		tcust.setClient_b(cust.getBranch());
		tcust.setCl_type("1");
		tcust.setCln_cat("001");
		tcust.setRec_date(new Date());
		tcust.setF_names(cust.getP_first_name());
		tcust.setSurname(cust.getP_family());
		tcust.setTitle("Client");
		tcust.setM_name(cust.getP_patronymic());
		tcust.setB_date(cust.getP_birthday());
		tcust.setR_street(cust.getP_birth_place());
		tcust.setR_city(cust.getP_birth_place());
		tcust.setR_cntry("UZB");
		tcust.setSearch_name(cust.getName());
		tcust.setSex(cust.getP_code_gender());
		tcust.setSerial_no(cust.getP_passport_number());
		tcust.setDoc_since(cust.getP_passport_date_registration());
		tcust.setIssued_by(cust.getP_passport_place_registration());
		this.addTieto$ab_date.setValue(tcust.getB_date());
		this.addTieto$aclient.setValue(tcust.getClient());
		this.addTieto$abank_c.setValue(tcust.getBank_c());
		this.addTieto$aclient_b.setValue(tcust.getClient_b());
		this.addTieto$acl_type.setValue(tcust.getCl_type());
		this.addTieto$acln_cat.setValue(tcust.getCln_cat());
		this.addTieto$af_names.setValue(tcust.getF_names());
		this.addTieto$asurname.setValue(tcust.getSurname());
		this.addTieto$atitle.setValue(tcust.getTitle());
		this.addTieto$am_name.setValue(tcust.getM_name());
		this.addTieto$ar_street.setValue(tcust.getR_street());
		this.addTieto$ar_city.setValue(tcust.getR_city());
		this.addTieto$ar_cntry.setValue(tcust.getR_cntry());
		this.addTieto$ausrid.setValue(tcust.getUsrid());
		this.addTieto$astatus.setValue(tcust.getStatus());
		this.addTieto$asearch_name.setValue(tcust.getSearch_name());
		this.addTieto$asex.setValue(tcust.getSex());
		this.addTieto$aserial_no.setValue(tcust.getSerial_no());
		this.addTieto$adoc_since.setValue(tcust.getDoc_since());
		this.addTieto$aissued_by.setValue(tcust.getIssued_by());
		return tcust;
	}

	private Customer getBFromTieto(final Tclient tcust, final Customer cust) {
		cust.setName(tcust.getSearch_name());
		cust.setBranch(this.branch);
		cust.setCode_country("860");
		cust.setP_code_class_credit("1");
		cust.setP_passport_type("N");
		cust.setCode_subject("P");
		cust.setSign_registr(2);
		cust.setCode_form("");
		cust.setCode_type("08");
		cust.setP_patronymic(tcust.getSurname());
		cust.setP_passport_number(tcust.getSerial_no());
		cust.setP_passport_serial(tcust.getSerial_no());
		cust.setP_passport_place_registration(tcust.getIssued_by());
		cust.setP_first_name(tcust.getF_names());
		cust.setP_family(tcust.getM_name());
		cust.setP_birthday(tcust.getB_date());
		cust.setP_post_address(tcust.getR_street());
		if (!CheckNull.isEmpty(cust.getP_family())) {
			this.addCust$ap_family.setValue(cust.getP_family());
		}
		if (!CheckNull.isEmpty(cust.getP_first_name())) {
			this.addCust$ap_first_name.setValue(cust.getP_first_name());
		}
		if (!CheckNull.isEmpty(cust.getP_patronymic())) {
			this.addCust$ap_patronymic.setValue(cust.getP_patronymic());
		}
		if (!CheckNull.isEmpty(cust.getP_post_address())) {
			this.addCust$ap_post_address.setValue(cust.getP_post_address());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_serial())) {
			this.addCust$ap_passport_serial.setValue(cust.getP_passport_serial());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_number())) {
			this.addCust$ap_passport_number.setValue(cust.getP_passport_number());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_place_registration())) {
			this.addCust$ap_passport_place_registration.setValue(cust.getP_passport_place_registration());
		}
		return cust;
	}

	public void onClick$add_to_link$addCustomer() {
		String log = null;
		String cl_n = "";
		if (!this.add_ti && !this.add_ho && this.add_br && !this.is_ho
				&& this.is_ti) {
			final Res res = CustomerService.update_lnk_br_by_ti(this.branch, this.addCustomer$add_cst_id.getValue(), this.tietocl.getClient(), this.alias);
			if (res.getCode() == 0) {
				this.alert(res.getName());
				return;
			}
			System.out.println("addCustomer$add_cst_id.getValue()->"
					+ this.addCustomer$add_cst_id.getValue());
			System.out.println("branch->" + this.branch);
			System.out.println("alias->" + this.alias);
			final Customer lg_c = CustomerService.getCustomerById(this.addCustomer$add_cst_id.getValue(), this.branch, this.alias);
			if (lg_c.getName() != null) {
				cl_n = String.valueOf(lg_c.getName()) + " "
						+ lg_c.getP_birthday();
			}
			log = "\u041e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0430 \u0441\u0432\u044f\u0437\u043a\u0430 \u043a\u043b\u0438\u0435\u043d\u0442\u0430 \u0441 tieto id ["
					+ this.tietocl.getClient()
					+ "] \u0434\u043b\u044f ["
					+ this.branch
					+ "]  c id ["
					+ this.addCustomer$add_cst_id.getValue()
					+ "] \u043a\u043b\u0438\u0435\u043d\u0442\u0430 ["
					+ cl_n
					+ "]";
			this.alert(log);
		}
		this.addCustomer.setVisible(false);
		CheckNull.clearForm(this.addCust$addgrdl);
		CheckNull.clearForm(this.addCust$addgrdr);
		this.addCustomer$dataGrid.getItems().clear();
		this.refreshModel(this._starttPageNumber);
	}

	public void onClick$close$addCustomer() {
		CheckNull.clearForm(this.addCust$addgrdl);
		CheckNull.clearForm(this.addCust$addgrdr);
		this.addCustomer$dataGrid.getItems().clear();
		this.addCustomer.setVisible(false);
	}

	public void onClick$close_btn$add_everywhere() {
		this.add_everywhere.setVisible(false);
		this.fl_edit = false;
	}

	public void onSelect$clientType() {
		tbtn_add_excel.setDisabled(false);
		if (clientType.getValue().equals("Пенсионеры")) {
			if (!alias.equals("BANK294")) {
				clientType.setSelectedIndex(-1);
				tbtn_add_excel.setDisabled(true);
			}
		}
	}

	// ПАКЕТНАЯ РЕГИСТРАЦИЯ КЛИЕНТОВ И КАРТ -- ЗАГРУЗКА ЭКСЕЛЯ
	public void onUpload$tbtn_add_excel(final UploadEvent event) throws SQLException {
		Media media = null;
		InputStream in = null;
		Row currentRow = null;
		Connection c = null;
		PreparedStatement ps = null;
		int state = 1;
		String err_msg = "";
		int cnt_errors = 0;
		int cnt_all = 0;
		try {
			media = event.getMedia();
			if (!media.getFormat().equalsIgnoreCase("xlsx")) {
				this.alert("Поддерживаемый формат xlsx, а не "
						+ media.getFormat());
				return;
			}
			if (CustomerService.checkOpenClientsTempExistence(media.getName(), branch)) {
				alert("Таблица с таким именем уже есть в базе.");
				return;
			}
			clientType.setDisabled(true);
			tbtn_register.setDisabled(true);
			// c = ConnectionPool.getConnection();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c = DriverManager.getConnection(ConnectionPool.getValue("HUMO_CONNECT"), ConnectionPool.getValue("HUMO_CON_LOGIN"), ConnectionPool.getValue("HUMO_CON_PAS"));
			c.setAutoCommit(false);
			ps = c.prepareStatement("insert into open_clients_temp (branch,client_id,cln_type,state,tel_pwd,err_msg, excel) values (?,?,?,?,?,?,?)");
			in = media.getStreamData();
			final XSSFWorkbook book = new XSSFWorkbook(in);
			final Sheet datatypeSheet = (Sheet) book.getSheetAt(0);
			final Iterator<Row> iterator = (Iterator<Row>) datatypeSheet.iterator();
			String excelName = media.getName();
			selectedExcel = excelName;
			int index = 0;

			while (iterator.hasNext()) {
				currentRow = iterator.next();
				if (index == 0) {
					++index;
				} else {
					++index;
					err_msg = "";
					state = 1;
					final Cell branchCell = currentRow.getCell(0);
					final Cell clientIdCell = currentRow.getCell(1);
					final Cell clnTypeCell = currentRow.getCell(2);
					final Cell pwdCell = currentRow.getCell(3);
					String budgetAccount = "";
					String budgetInn = "";
					String othersCustomerId = "";
					if (clientType.getValue().equals("Бюджетники")) {
						final Cell budgetAccountCell = currentRow.getCell(4);
						final Cell budgetInnCell = currentRow.getCell(5);
						budgetAccount = getCellValue(budgetAccountCell);
						if (budgetAccount.length() != 27) {
							alert("Данные таблицы не соответствуют шаблону.");
							return;
						}
						budgetInn = getCellValue(budgetInnCell);
					} else if (clientType.getValue().equals("Остальные организации")) {
						Cell customerId = currentRow.getCell(4);
						othersCustomerId = getCellValue(customerId);
						ISLogger.getLogger().error("othersCustomerId.length(): "
								+ othersCustomerId.length()
								+ " "
								+ othersCustomerId);
						if (othersCustomerId.length() != 8) {
							alert("Данные таблицы не соответствуют шаблону.");
							return;
						}
					}

					final String branch = new StringBuilder().append(branchCell).toString();
					if (branch == null || branch.equals("")
							|| !branch.matches("[0-9]+")
							|| branch.length() != 5) {
						err_msg = String.valueOf(err_msg)
								+ "\nНекорректный номер филиала в строке "
								+ index;
						state = 3;
						break;
					}
					final String currentBankType = Utils.getValueFromSql("select bank_type from s_mfo where bank_id = '"
							+ this.branch + "'", this.alias);
					final String bankTypeFromClient = Utils.getValueFromSql("select bank_type from s_mfo where bank_id = '"
							+ branch + "'", this.alias);
					if (!currentBankType.equals(bankTypeFromClient)) {
						err_msg = String.valueOf(err_msg) + "\nФилиал "
								+ branch + " не принадлежит вашему банку";
						state = 3;
					}
					final String currentAliasFromClientBranch = Utils.getValueFromSql("select user_name from ss_dblink_branch where branch = '"
							+ branch + "'", this.alias);
					final String client_id = new BigDecimal(Double.parseDouble(new StringBuilder().append(clientIdCell).toString())).toString();
					if (client_id == null || client_id.equals("")
							|| !client_id.matches("[0-9]+")
							|| client_id.length() != 8) {
						err_msg = String.valueOf(err_msg)
								+ "\nНекорректный номер клиента в строке "
								+ index;
						state = 3;
					}
					final String checkClient = Utils.getValueFromSql("select id from client_p where id = '"
							+ client_id
							+ "' and branch = '"
							+ branch
							+ "' and state = 2", currentAliasFromClientBranch);
					System.out.println("currentAliasFromClientBranch: "
							+ currentAliasFromClientBranch);
					if (checkClient == null) {
						err_msg = String.valueOf(err_msg)
								+ "\nКлиент с номером "
								+ client_id
								+ " в филиале "
								+ branch
								+ " не найден или находится не в состоянии утверждён";
						state = 3;
					}
					final String clnType = this.getCellValue(clnTypeCell);
					if (clnType == null || clnType.equals("")
							|| clnType.length() != 3
							|| !clnType.matches("[0-9]+")) {
						err_msg = String.valueOf(err_msg)
								+ "\nНекорректный код типа клиента в строке "
								+ index;
						state = 3;
					}
					final String pwd = this.getCellValue(pwdCell);
					if (pwd == null || pwd.equals("") || pwd.length() > 30
							|| !pwd.matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")) {
						err_msg = String.valueOf(err_msg)
								+ "\nНекорректный код для телефонных разговоров";
						state = 3;
					}
					ArrayList<String> listBudget = new ArrayList<String>();
					ArrayList<String> listWork = new ArrayList<String>();
					if (clientType.getValue().equals("Бюджетники")) {
						if (!CustomerService.checkS_BudsprExistence(budgetAccount, c)) {
							err_msg = String.valueOf(err_msg) + budgetAccount
									+ " - данный бюджетный счёт не найден.";
							state = 3;
						}

						listBudget.add(0, branch);
						listBudget.add(1, budgetAccount);
						listBudget.add(2, budgetInn);
						listBudget.add(3, client_id);
						map.put(client_id, listBudget);
					} else if (clientType.getValue().equals("Остальные организации")) {
						listWork.add(0, branch);
						listWork.add(1, othersCustomerId);
						listWork.add(2, client_id);
						map.put(client_id, listWork);

					}
					ps.setString(1, branch);
					ps.setString(2, client_id);
					ps.setString(3, clnType);
					ps.setInt(4, state);
					ps.setString(5, pwd);
					ps.setString(6, (err_msg.length() > 1000) ? err_msg.substring(0, 1000)
							: err_msg);
					ps.setString(7, excelName);
					ps.execute();
					++cnt_all;
					if (state == 3) {
						++cnt_errors;
					}
				}

			}
			c.commit();
			String msg = "";
			if (cnt_errors == 0) {
				msg = "Успешно";
			} else {
				msg = "Обработано с ошибками - " + cnt_errors
						+ "\nУспешно обработано - " + (cnt_all - cnt_errors);
			}
			this.alert(msg);
		} catch (Exception e) {
			e.printStackTrace();
			Utils.rollback(c);
			this.alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			return;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
			}
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		try {
			if (in != null) {
				in.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e2));
		}
		Utils.close(ps);
		ConnectionPool.close(c);
		uploadedTable$tempListBox.setModel(new ListModelList(CustomerService.getTemp(selectedExcel, branch), true));
		uploadedTable$selectedExcel.setModel(new ListModelList(state == 1 ? CustomerService.getExcelList(branch, null)
				: CustomerService.getExcelList(branch, selectedExcel)));
		uploadedTable.setVisible(true);
		uploadedTable$btn_deleteExcel.setDisabled(true);
		uploadedTable$btn_registerAll.setDisabled(true);
	}

	// //////////////////////////////ПАКЕТНАЯ
	// РЕГИСТРАЦИЯ/////////////////////////////////////////////////////////
	public void onClick$tbtn_register() {
		this.registerClient();
		this.refreshModelBank(this._startbPageNumber);
	}

	private void registerClient() {
		ISLogger.getLogger().error("PACKET REG");
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Res res = null;
		String branch = (String) this.session.getAttribute("branch");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c = DriverManager.getConnection(ConnectionPool.getValue("HUMO_CONNECT"), ConnectionPool.getValue("HUMO_CON_LOGIN"), ConnectionPool.getValue("HUMO_CON_PAS"));
			c.setAutoCommit(false);
			// c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from open_clients_temp where state = 1 and branch = ? and excel = ?");
			ps.setString(1, branch);
			ps.setString(2, selectedExcel);
			rs = ps.executeQuery();
			while (rs.next()) {
				rs.getString("cln_type");
				final String client_id = rs.getString("client_id");
				final String currentAliasFromClientBranch = Utils.getValueFromSql("select user_name from ss_dblink_branch where branch = '"
						+ rs.getString("branch") + "'", this.alias);
				ISLogger.getLogger().error("current alias: "
						+ currentAliasFromClientBranch);
				if (clientType.getValue().equals("Пенсионеры")) {
					final HashMap<String, EmpcSettings> settingsForEach = (HashMap<String, EmpcSettings>) TclientService.getHSettingsByCardType(branch, "7");
					res = CustomerService.createCustomerAndAgreement(uid, un, pwd, order_from, order_previous, settingsForEach, branch, client_id, rs.getString("cln_type"), rs.getString("tel_pwd"), currentAliasFromClientBranch, c, issuingPortProxy);
				} else {
					res = CustomerService.createCustomerAndAgreement(uid, un, pwd, order_from, order_previous, settings, branch, client_id, rs.getString("cln_type"), rs.getString("tel_pwd"), currentAliasFromClientBranch, c, issuingPortProxy);
				}
				if (clientType.getValue().equals("Бюджетники")) {
					if (map != null && !map.isEmpty()) {
						ArrayList<String> list = new ArrayList<String>(map.get(client_id));
						CustomerService.insertBudgetInfo(list, c);
					} else {
						ISLogger.getLogger().error("EMPTY");
					}
				} else if (clientType.getValue().equals("Остальные организации")) {
					if (map != null && !map.isEmpty()) {
						ISLogger.getLogger().error("NOT EMPTY");
						ArrayList<String> list = new ArrayList<String>(map.get(client_id));
						System.out.println("list: " + list.get(0));
						CustomerService.insertWorkInfo(list, c, order_from, order_previous, alias);
					} else {
						ISLogger.getLogger().error("EMPTY");
					}
				}
				this.updateStateTempTable(client_id, branch, res, c);
				c.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Utils.rollback(c);
			this.alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			return;
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		Utils.close(rs);
		Utils.close(ps);
		ConnectionPool.close(c);
		clientType.setDisabled(false);
	}

	private String registerClient(final String clientId, final String branch, final String cln_cat, final String pwd, final String alias, final Connection c) throws Exception {
		String msg = null;
		if (CustomerService.checkClient(clientId, branch, un, pwd, alias)) {
			return msg;
		}
		OperationResponseInfo orInfo = null;
		final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(AddCstViewCtrl.settings.get(branch).getBank_c());
		connectionInfo.setGROUPC(AddCstViewCtrl.settings.get(branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		final RowType_Customer rtc = new RowType_Customer();
		final Calendar cal_p = Calendar.getInstance();
		final Calendar cal = Calendar.getInstance();
		final Customer new_customer = CustomerService.getCustomerById(clientId, branch, alias);
		if (new_customer.getP_first_name() == null) {
			msg = "Не указано имя клиента";
			return msg;
		}
		if (new_customer.getPinfl() == null) {
			msg = "Не указан ПИНФЛ у клиента";
		}
		final String first_name = Utils.toTranslit(new_customer.getP_first_name());
		if (first_name == null) {
			msg = "Имя клиента (" + new_customer.getP_first_name()
					+ ") неверно";
			return msg;
		}
		rtc.setF_NAMES(first_name);
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(String.valueOf(branch) + clientId);
		final String surname = Utils.toTranslit(new_customer.getP_family());
		if (surname == null) {
			msg = "Фамилия клиента (" + new_customer.getP_family()
					+ ") неверная";
			return msg;
		}
		rtc.setSURNAME(surname);
		cal_p.setTime(new_customer.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal_p);
		cal.setTime(new_customer.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT(new_customer.getCode_resident());
		rtc.setSTATUS("10");
		rtc.setSEX(new_customer.getP_code_gender());
		rtc.setSERIAL_NO(String.valueOf(new_customer.getP_passport_serial()));
		rtc.setID_CARD(new_customer.getP_passport_number());
		rtc.setR_CITY(new_customer.getP_code_citizenship());
		if (new_customer.getP_birth_place() == null) {
			new_customer.setP_birth_place("Uzbekiston");
		}
		final String street = Utils.toTranslit(new_customer.getP_birth_place());
		if (street == null) {
			msg = "Место рождения клиента (" + new_customer.getP_birth_place()
					+ ") неверное";
			return msg;
		}
		rtc.setR_STREET(street);
		rtc.setR_E_MAILS(new_customer.getP_email_address());
		rtc.setR_MOB_PHONE(new_customer.getP_phone_mobile());
		rtc.setR_PHONE(new_customer.getP_phone_home());
		final String cntry = (new_customer.getP_code_citizenship() == null) ? "UZB"
				: ((new_customer.getP_code_citizenship().compareTo("860") == 0) ? "UZB"
						: null);
		rtc.setR_CNTRY(cntry);
		final String place_registr = Utils.toTranslit(new_customer.getP_passport_place_registration());
		if (place_registr == null) {
			msg = "Место выдачи паспорта ("
					+ new_customer.getP_passport_place_registration()
					+ ") неверное";
			return msg;
		}
		rtc.setISSUED_BY(place_registr);
		rtc.setPERSON_CODE(new_customer.getPinfl());
		rtc.setCLN_CAT(cln_cat);
		rtc.setDOC_TYPE("001");
		rtc.setREC_DATE(calendar);
		final RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		final ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		orInfo = this.issuingPortProxy.newCustomer(connectionInfo, customerInfo, customListInfo);
		System.out.println(orInfo);
		if (orInfo.getError_description() != null) {
			msg = String.valueOf(orInfo.getError_action()) + "\r\n"
					+ orInfo.getError_description();
			System.out.println("msg err = " + msg);
			return msg;
		}
		CustomerService.insertClient(customerInfo, c, alias);
		TclientService.insertTietoCustomers(customerInfo.value.getCLIENT(), customerInfo.value.getCLIENT_B(), branch, c);
		return msg;
	}

	private void updateStateTempTable(final String client_id, final String branch, final Res res, final Connection c) {
		PreparedStatement ps = null;
		final String state = (res.getCode() == 1) ? "2" : "4";
		try {
			String err_msg = (res.getName() == null) ? "" : res.getName();
			if (!err_msg.equals("")) {
				err_msg = err_msg.replace("'", "!");
			}
			err_msg = err_msg.length() > 1000 ? err_msg.substring(0, 1000)
					: err_msg;
			final String sql = "update open_clients_temp set state = " + state
					+ ", err_msg = '" + err_msg + "' where client_id = '"
					+ client_id + "' and branch = '" + branch
					+ "' and state = 1";
			System.out.println(sql);
			ps = c.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			return;
		} finally {
			Utils.close(ps);
		}
		Utils.close(ps);
	}

	private String getCellValue(final Cell currentCell) {
		return (currentCell.getCellType() == 0) ? new StringBuilder(String.valueOf(currentCell.getNumericCellValue())).toString().replace(".0", "")
				: currentCell.getStringCellValue();
	}

	public void onClick$add_btn$add_everywhere() throws JsonProcessingException {
		boolean fl_err = false;
		String err = "";
		if (!this.add_everywhere$ap_passport_number.getValue().matches("[a-zA-Z0-9]+")
				|| this.add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (!this.add_everywhere$ap_passport_serial.getValue().matches("[a-zA-Z0-9]+")
				|| this.add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!this.add_everywhere$ap_passport_place_registration.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| this.add_everywhere$ap_passport_place_registration.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан *";
		}
		if (!this.add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| this.add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!this.add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| this.add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!this.add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9 ]*")
				|| this.add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_type_document.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!this.add_everywhere$ap_number_tax_registration.getValue().matches("[0-9]*")
				|| this.add_everywhere$ap_number_tax_registration.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(this.add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(this.add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_passport_date_registration.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (CheckNull.isEmpty(this.add_everywhere$acode_tel.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПароль для телефонных разговоров";
		}
		if (CheckNull.isEmpty(this.add_everywhere$aPinfl.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (this.add_everywhere$aPinfl.getValue().length() != 14) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		}
		if (!this.add_everywhere$ap_post_address.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| this.add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (!this.add_everywhere$ap_birth_place.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| this.add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}
		if (fl_err) {
			this.alert("Ошибка заполнения формы:\nневерно заполнено поле "
					+ err);
			return;
		}
		if (this.add_everywhere$cln_cat.getValue() == null
				|| this.add_everywhere$cln_cat.getValue().equals("")) {
			this.alert("Не заполнено поле Тип клиента");
			return;
		}
		final String cur_acc = null;
		final Customer new_customer = new Customer();
		String bankClientId = (this.cur_branch_customer != null) ? this.cur_branch_customer.getId_client()
				: null;
		String humoClientId = (this.tietocl != null) ? this.tietocl.getClient()
				: null;
		new_customer.setP_passport_number(this.add_everywhere$ap_passport_number.getValue());
		new_customer.setP_passport_type(this.add_everywhere$ap_type_document.getValue());
		new_customer.setP_type_document(this.add_everywhere$ap_type_document.getValue());
		new_customer.setP_passport_serial(this.add_everywhere$ap_passport_serial.getValue());
		new_customer.setP_passport_place_registration(this.add_everywhere$ap_passport_place_registration.getValue());
		new_customer.setP_family(this.add_everywhere$ap_family.getValue());
		new_customer.setP_first_name(this.add_everywhere$ap_first_name.getValue());
		new_customer.setName(String.valueOf(this.add_everywhere$ap_family.getValue())
				+ " "
				+ this.add_everywhere$ap_first_name.getValue()
				+ " "
				+ this.add_everywhere$ap_patronymic.getValue());
		new_customer.setP_birthday(this.add_everywhere$ap_birthday.getValue());
		new_customer.setCode_country(this.add_everywhere$acode_country.getValue());
		new_customer.setCode_resident(this.add_everywhere$acode_resident.getValue());
		new_customer.setP_post_address(this.add_everywhere$ap_post_address.getValue());
		new_customer.setR_CITY(this.add_everywhere$ar_city.getValue());
		new_customer.setAcode_tel(this.add_everywhere$acode_tel.getValue());
		new_customer.setP_patronymic(this.add_everywhere$ap_patronymic.getValue());
		new_customer.setP_passport_date_expiration(this.add_everywhere$ap_passport_date_expiration.getValue());
		new_customer.setP_passport_date_registration(this.add_everywhere$ap_passport_date_registration.getValue());
		new_customer.setP_code_birth_region(CheckNull.isEmpty(this.add_everywhere$ap_code_birth_region.getValue()) ? null
				: this.add_everywhere$ap_code_birth_region.getValue());
		new_customer.setP_code_birth_distr(this.add_everywhere$ap_code_birth_distr.getValue());
		new_customer.setP_birth_place(this.add_everywhere$ap_birth_place.getValue());
		new_customer.setP_code_gender(this.add_everywhere$ap_code_gender.getValue());
		new_customer.setP_code_nation(this.add_everywhere$ap_code_nation.getValue());
		new_customer.setP_code_adr_region(this.add_everywhere$ap_code_adr_region.getValue());
		new_customer.setP_code_adr_distr(this.add_everywhere$ap_code_adr_distr.getValue());
		new_customer.setP_code_tax_org(this.add_everywhere$ap_code_tax_org.getValue());
		new_customer.setP_number_tax_registration(this.add_everywhere$ap_number_tax_registration.getValue());
		new_customer.setP_code_citizenship(this.add_everywhere$ap_code_citizenship.getValue());
		new_customer.setP_phone_mobile(this.add_everywhere$ap_phone_mobile.getValue());
		new_customer.setP_email_address(this.add_everywhere$ap_email_address.getValue());
		new_customer.setP_phone_home(this.add_everywhere$ap_phone_home.getValue());
		// new_customer.setCode_product(this.add_everywhere$ap_code_product.getValue());
		new_customer.setP_inps(this.add_everywhere$ap_inps.getValue());
		new_customer.setBranch(ConnectionPool.getValue("HO", this.alias));
		new_customer.setP_code_bank(ConnectionPool.getValue("HO", this.alias));
		new_customer.setP_code_class_credit("1");
		new_customer.setP_passport_type("N");
		new_customer.setCode_subject("P");
		new_customer.setSign_registr(2);
		new_customer.setCode_form("");
		new_customer.setCode_type("08");
		new_customer.setBranch(this.branch);
		new_customer.setP_code_bank(this.branch);
		new_customer.setPinfl(this.add_everywhere$aPinfl.getValue());
		if (this.cur_branch_customer != null) {
			new_customer.setId(this.cur_branch_customer.getId());
			new_customer.setId_client(this.cur_branch_customer.getId_client());
		}
		final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(AddCstViewCtrl.settings.get(this.branch).getBank_c());
		connectionInfo.setGROUPC(AddCstViewCtrl.settings.get(this.branch).getGroup_c());
		connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		final RowType_Customer rtc = new RowType_Customer();
		final Calendar cal_p = Calendar.getInstance();
		final Calendar cal = Calendar.getInstance();
		rtc.setF_NAMES(new_customer.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(String.valueOf(this.branch) + bankClientId);
		rtc.setSURNAME(new_customer.getP_family());
		rtc.setM_NAME(new_customer.getP_patronymic());
		cal_p.setTime(new_customer.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal_p);
		cal.setTime(new_customer.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT(new_customer.getCode_resident());
		rtc.setSTATUS("10");
		rtc.setSEX(new_customer.getP_code_gender());
		rtc.setSERIAL_NO(String.valueOf(new_customer.getP_passport_serial()));
		rtc.setID_CARD(new_customer.getP_passport_number());
		rtc.setR_CITY(new_customer.getP_code_citizenship());
		rtc.setR_STREET(new_customer.getP_birth_place());
		rtc.setR_E_MAILS(new_customer.getP_email_address());
		rtc.setR_MOB_PHONE(new_customer.getP_phone_mobile());
		rtc.setR_PHONE(new_customer.getP_phone_home());
		rtc.setR_CNTRY((new_customer.getP_code_citizenship().compareTo("860") == 0) ? "UZB"
				: null);
		rtc.setISSUED_BY(new_customer.getP_passport_place_registration());
		rtc.setPERSON_CODE(this.add_everywhere$aPinfl.getValue());
		rtc.setCLN_CAT(this.add_everywhere$cln_cat.getValue());
		rtc.setDOC_TYPE("001");
		rtc.setREC_DATE(calendar);
		if (cur_branch_customer != null) {
			if (cur_branch_customer.getTieto_customer_id() == null) {
				rtc.setCLIENT(null);
			}
		}
		OperationResponseInfo orInfo = null;
		final RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		final ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		Customer cst = null;

		if (!this.fl_edit) {
			if (this.add_br) {
				final Res res = CustomerService.doAction(this.session.getAttribute("un").toString(), this.session.getAttribute("pwd").toString(), new_customer, 1, 2, this.alias, true);
				ISLogger.getLogger().error("res.getName ID: " + res.getName());
				if (res.getCode() != 0) {
					this.alert("ОШИБКА\nОткрытие клиента :\n" + res.getName());
					this.fl_edit = false;
					return;
				}
				this.alert("Клиент добавлен :" + res.getName());
				cst = CustomerService.getCustomerById_tbl(res.getName(), this.branch, this.alias);
				bankClientId = cst.getId_client();
				rtc.setCLIENT_B(String.valueOf(this.branch) + bankClientId);
				if (!add_everywhere$customerId.getValue().isEmpty()) {
					CustomerService.addCompanyEmployee(branch, add_everywhere$customerId.getValue(), cst.getId_client());
				}
				if (!this.add_ti) {
					this.refreshModel(this._starttPageNumber);
					this.add_everywhere.setVisible(false);
					this.fl_edit = false;
					return;
				}
			}

			// Открытие клиента в Хумо. Регистрация клиента в Хумо. Новый клиент
			if (this.add_ti) {
				Label_2445: {
					try {
						ISLogger.getLogger().error("newCustomer request for bank client "
								+ bankClientId
								+ "\n"
								+ objectMapper.writeValueAsString(connectionInfo)
								+ "\n"
								+ objectMapper.writeValueAsString(customerInfo));
						orInfo = this.issuingPortProxy.newCustomer(connectionInfo, customerInfo, customListInfo);
						if (orInfo.getError_description() != null) {
							if (orInfo.getError_description().contains("already exists.")) {
								try {
									CustomerService.getClientFromTietoByClientB(issuingPortProxy, bankClientId, HUMO_BANK_C, HUMO_GROUPC, branch, un, pwd, alias);
									this.alert("Клиент добавлен EMPC(GLOBUZ)");
								} catch (Exception e) {
									ISLogger.getLogger().error("getClientFromTietoByClientB error: "
											+ e.getLocalizedMessage());
								}
							}else {
								this.alert(String.valueOf(orInfo.getError_action())
										+ "\r\n" + orInfo.getError_description());
								return;
							}
						} else {
							Connection c = null;
							try {
								c = ConnectionPool.getConnection(un, pwd, alias);
								ISLogger.getLogger().error("newCustomer response for client "
										+ bankClientId
										+ "\n"
										+ objectMapper.writeValueAsString(customerInfo));
								TietoCustomer tietoCustomer = new TietoCustomer();
								tietoCustomer.setBankCustomerId(bankClientId);
								tietoCustomer.setTietoCustomerId(customerInfo.value.getCLIENT());
								tietoCustomer.setBranch(branch);
								Res resXumoCreate = CustomerService.create(tietoCustomer, un, pwd, alias);
								Res resInsertClient = CustomerService.insertClient(customerInfo, alias);
								if(resXumoCreate.getCode() != 0 || resInsertClient.getCode() != 0) {
									ISLogger.getLogger().error("resXumoCreate for bank client " + bankClientId
											+ "\n" + objectMapper.writeValueAsString(resXumoCreate)
											+ "\nresInsertClient "
											+ "\n" + objectMapper.writeValueAsString(resInsertClient));
									CustomerService.getClientFromTietoByClientB(issuingPortProxy, bankClientId, HUMO_BANK_C, HUMO_GROUPC, 
											branch, un, pwd, alias);
								}
								this.alert("Клиент добавлен EMPC(GLOBUZ)");
								humoClientId = customerInfo.value.getCLIENT();
							} catch (Exception e3) {
								Utils.rollback(c);
								break Label_2445;
							} finally {
								ConnectionPool.close(c);
							}
							ConnectionPool.close(c);
						}
					} catch (RemoteException e) {
						LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e));
						e.printStackTrace();
					}
				}
				this.fl_edit = false;
			}
			if (this.add_ti && this.add_br) {
				if (!Strings.isNullOrEmpty(humoClientId)) {
					CustomerService.create_lnk(this.branch, bankClientId, humoClientId, cur_acc, this.alias);
				}
			}
			if (this.add_ti && !this.add_br && humoClientId != null) {
				if (!Strings.isNullOrEmpty(humoClientId)) {
					CustomerService.update_lnk_ti_by_br(this.branch, this.cur_branch_customer.getId_client(), humoClientId, this.alias);
				}
			}
		} else {
			if (!add_everywhere$customerId.getValue().isEmpty()) {
				if (CustomerService.checkCompanyEmployeeExistence(new_customer.getId_client(), branch)) {
					CustomerService.editCompanyEmployee(branch, add_everywhere$customerId.getValue(), new_customer.getId_client());
				} else {
					CustomerService.addCompanyEmployee(branch, add_everywhere$customerId.getValue(), new_customer.getId_client());
					String account = CustomerService.getNewestAccount(new_customer.getId_client(), branch, humoAccOrdFrom, humoAccOrdPrevious);
					CustomerService.setCompanyEmployeeAccount(account, new_customer.getId_client(), add_everywhere$customerId.getValue(), branch, alias);
				}
			}
			if (this.edit_br) {
				final Res res = CustomerService.doAction(this.session.getAttribute("un").toString(), this.session.getAttribute("pwd").toString(), new_customer, 19, 0, this.alias, true);
				if (res.getCode() != 0) {
					this.alert(res.getName());
					return;
				}
				this.edit_br = false;
				this.alert("Клиент отредактирован в банке");
			}
			if (this.edit_ti) {
				final RowType_EditCustomer_Request customerRequest = new RowType_EditCustomer_Request(humoClientId, AddCstViewCtrl.settings.get(this.branch).getBank_c(), rtc.getCLIENT_B(), rtc.getCL_TYPE(), rtc.getCLN_CAT(), rtc.getREC_DATE(), rtc.getF_NAMES(), rtc.getSURNAME(), rtc.getTITLE(), rtc.getM_NAME(), rtc.getB_DATE(), rtc.getRESIDENT(), rtc.getID_CARD(), rtc.getDOC_TYPE(), rtc.getR_PHONE(), rtc.getEMP_NAME(), rtc.getPOSITION(), rtc.getEMP_DATE(), rtc.getWORK_PHONE(), rtc.getR_STREET(), rtc.getR_CITY(), rtc.getR_CNTRY(), rtc.getR_PCODE(), rtc.getC_SINCE(), rtc.getCMP_NAME(), rtc.getCMPG_NAME(), rtc.getCO_POSITON(), rtc.getCONTACT(), rtc.getCNT_TITLE(), rtc.getCNT_PHONE(), rtc.getCNT_FAX(), rtc.getMNG_POSIT(), rtc.getMNG_NAME(), rtc.getMNG_PHONE(), rtc.getMNG_TITLE(), rtc.getMNG_FAX(), rtc.getREG_NR(), rtc.getCR_STREET(), rtc.getCR_CITY(), rtc.getCR_CNTRY(), rtc.getCR_PCODE(), rtc.getCOMENT(), rtc.getREGION(), rtc.getPERSON_CODE(), rtc.getRESIDENT_SINCE(), rtc.getYEAR_INC(), rtc.getCCY_FOR_INCOM(), rtc.getIMM_PROP_VALUE(), rtc.getSEARCH_NAME(), rtc.getMARITAL_STATUS(), rtc.getCO_CODE(), rtc.getEMP_CODE(), rtc.getSEX(), rtc.getSERIAL_NO(), rtc.getDOC_SINCE(), rtc.getISSUED_BY(), rtc.getEMP_ADR(), rtc.getEMP_FAX(), rtc.getEMP_E_MAILS(), rtc.getR_E_MAILS(), rtc.getR_MOB_PHONE(), rtc.getR_FAX(), rtc.getCNT_E_MAILS(), rtc.getCNT_MOB_PHONE(), rtc.getMNG_MOB_PHONE(), rtc.getMNG_E_MAILS(), rtc.getCR_E_MAILS(), rtc.getIN_FILE_NUM(), rtc.getU_COD1(), rtc.getU_COD2(), rtc.getU_COD3(), rtc.getU_FIELD1(), rtc.getU_FIELD2(), rtc.getCALL_ID(), rtc.getHOME_NUMBER(), rtc.getBUILDING(), rtc.getSTREET1(), rtc.getAPARTMENT(), rtc.getMIDLE_NAME(), rtc.getSTATUS(), (String) null, rtc.getAMEX_MEMBER_SINCE(), rtc.getDCI_MEMBER_SINCE(), rtc.getREWARD_NO());
				try {
					ISLogger.getLogger().error("editCustomer request for client "
							+ humoClientId
							+ "\n"
							+ objectMapper.writeValueAsString(connectionInfo)
							+ "\n"
							+ objectMapper.writeValueAsString(customerRequest));
					orInfo = this.issuingPortProxy.editCustomer(connectionInfo, customerRequest);
					if (orInfo.getError_description() != null) {
						this.alert(String.valueOf(orInfo.getError_action())
								+ "\r\n" + orInfo.getError_description());
					} else {
						CustomerService.updateClient(customerRequest);
						this.alert("Клиент отредактирован (GlobUZ)");
						humoClientId = rtc.getCLIENT();
					}
				} catch (RemoteException e2) {
					LtLogger.getLogger().error((Object) CheckNull.getPstr((Exception) e2));
					e2.printStackTrace();
					this.alert(e2.getMessage());
				}
			}
		}
		this.fl_edit = false;
		this.refreshModel(this._startPageNumber);
		this.add_everywhere.setVisible(false);
	}

	private String open_card(final String card_prod_id, final String RT, final boolean sec, final String new_card_acc) {
		if (new_card_acc.substring(0, 5).compareTo("22618") != 0) {
			this.alert("Неверный счет карты:\n" + new_card_acc);
			return "";
		}
		final Tclient ntc = this.tietocl;
		final TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(card_prod_id), this.alias);
		final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		final KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		final RowType_CardInfo rtci = new RowType_CardInfo();
		final RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		final RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		final RowType_CardInfo_EMV_Data EMV_data = new RowType_CardInfo_EMV_Data();
		final RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder();
		final RowType_Agreement avalue = new RowType_Agreement();
		final ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		final RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		final Calendar cal = Calendar.getInstance();
		try {
			connectionInfo.setBANK_C(tcs.getBank_c());
			connectionInfo.setGROUPC(tcs.getGroup_c());
			connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
			System.out.println("Bank_c" + tcs.getBank_c());
			System.out.println("group" + tcs.getGroup_c());
			avalue.setBINCOD(tcs.getBin());
			avalue.setBANK_CODE(AddCstViewCtrl.settings.get(this.branch).getBank_c());
			final Res res = CustomerService.getTieto_branch(this.branch, this.alias);
			if (res.getCode() != 0) {
				this.alert(res.getName());
				return null;
			}
			avalue.setBRANCH(res.getName());
			avalue.setCITY(CheckNull.isEmpty(ntc.getR_city()) ? "UZB"
					: ntc.getR_city());
			avalue.setPRODUCT(card_prod_id);
			avalue.setREP_LANG("1");
			avalue.setRISK_LEVEL(tcs.getRisk_level());
			avalue.setSTREET(CheckNull.isEmpty(ntc.getR_street()) ? "STREET"
					: ntc.getR_street());
			avalue.setSTATUS("10");
			if (this.contract_nmb == null) {
				this.contract_nmb = CustomerService.get_contract(this.branch, card_prod_id, this.alias);
			}
			avalue.setCONTRACT(this.contract_nmb);
			avalue.setENROLLED(cal);
			avalue.setDISTRIB_MODE("01");
			avalue.setCLIENT(ntc.getClient());
			avalue.setCOUNTRY(CheckNull.isEmpty(ntc.getR_cntry()) ? "UZB"
					: ntc.getR_cntry());
			agreementInfo.value = avalue;
			base_info.setC_ACCNT_TYPE("00");
			base_info.setCCY("UZS");
			base_info.setSTAT_CHANGE("1");
			base_info.setMIN_BAL(BigDecimal.valueOf(0L));
			base_info.setTRANZ_ACCT(new_card_acc);
			base_info.setACC_PRTY("1");
			base_info.setCOND_SET(tcs.getFinancial_conditions());
			base_info.setCYCLE("0");
			base_info.setCRD(BigDecimal.valueOf(0L));
			base_info.setNON_REDUCE_BAL(tcs.getMinimum_balance());
			base_info.setSTATUS("0");
			base_info.setLIM_INTR(BigDecimal.valueOf(0L));
			if (sec) {
				base_info.setCARD_ACCT(RT);
			}
			logicalCard.setCOND_SET(tcs.getCard_condition());
			logicalCard.setRISK_LEVEL(tcs.getRisk_level());
			logicalCard.setBASE_SUPP("1");
			logicalCard.setF_NAMES(ntc.getF_names());
			logicalCard.setSURNAME(ntc.getSurname());
			final String card_name = ((String.valueOf(ntc.getF_names()) + " " + ntc.getSurname()).length() > 24) ? (String.valueOf(ntc.getF_names())
					+ " " + ntc.getSurname()).substring(0, 24)
					: (String.valueOf(ntc.getF_names()) + " " + ntc.getSurname());
			physicalCard.setCARD_NAME(card_name);
			physicalCard.setSTATUS1("0");
			physicalCard.setDESIGN_ID(tcs.getDesign_id());
			EMV_data.setCHIP_APP_ID(tcs.getId_chip_app());
			rtci.setLogicalCard(logicalCard);
			rtci.setPhysicalCard(physicalCard);
			rtci.setEMV_Data(EMV_data);
			cardsListInfo = new ListType_CardInfoHolder();
			final RowType_EditAgreement_Request re = new RowType_EditAgreement_Request(BigDecimal.valueOf(this.agre_nom_upd), avalue.getBINCOD(), avalue.getBANK_CODE(), avalue.getBRANCH(), avalue.getB_BR_ID(), avalue.getOFFICE(), avalue.getOFFICE_ID(), avalue.getCITY(), avalue.getCLIENT(), avalue.getCOMENT(), avalue.getCONTRACT(), avalue.getCOUNTRY(), avalue.getCTIME(), avalue.getDISTRIB_MODE(), avalue.getENROLLED(), avalue.getE_MAILS(), avalue.getIN_FILE_NUM(), avalue.getISURANCE_TYPE(), avalue.getPOST_IND(), avalue.getPRODUCT(), avalue.getREP_LANG(), avalue.getRISK_LEVEL(), avalue.getSTATUS(), avalue.getSTREET(), avalue.getUSRID(), avalue.getU_COD4(), avalue.getU_CODE5(), avalue.getU_CODE6(), avalue.getU_FIELD3(), avalue.getU_FIELD4(), avalue.getCOMBI_TYPE());
			re.setAGRE_NOM(BigDecimal.valueOf(this.agre_nom_upd));
			if (this.edit_agree) {
				orInfo = this.issuingPortProxy.editAgreement(connectionInfo, re);
				connectionInfo.setBANK_C(AddCstViewCtrl.settings.get(this.branch).getBank_c());
				connectionInfo.setGROUPC(AddCstViewCtrl.settings.get(this.branch).getGroup_c());
				System.out.println("bank_c"
						+ AddCstViewCtrl.settings.get(this.branch).getBank_c());
				System.out.println("Group_c_c"
						+ AddCstViewCtrl.settings.get(this.branch).getGroup_c());
				connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
				mainAgreementInfo.setAGRE_NOM(BigDecimal.valueOf(this.agre_nom_upd));
				orInfo = this.issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, new ListType_AccountInfoHolder(), cardsListInfo);
			} else {
				orInfo = this.issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
			}
			this.edit_agree = false;
			if (orInfo.getError_description() != null
					|| orInfo.getResponse_code().intValue() != 0) {
				this.alert(orInfo.getError_description());
				return null;
			}
			if (orInfo.getError_description() != null
					|| orInfo.getResponse_code().intValue() != 0) {
				this.alert(orInfo.getError_description());
				return null;
			}
		} catch (Exception e) {
			LtLogger.getLogger().error((Object) CheckNull.getPstr(e));
			ISLogger.getLogger().error((Object) "Catch:");
			ISLogger.getLogger().error((Object) ("Branch: " + this.branch));
			ISLogger.getLogger().error((Object) ("Bank_c: " + AddCstViewCtrl.settings.get(this.branch).getBank_c()));
			ISLogger.getLogger().error((Object) ("Group_c: " + AddCstViewCtrl.settings.get(this.branch).getGroup_c()));
			ISLogger.getLogger().error((Object) ("Bincode: " + tcs.getBin()));
			this.alert(e.getMessage());
			e.printStackTrace();
			return null;
		}
		this.alert("Карта открыта:" + tcs.getName());
		this.addwnd.setVisible(false);
		return RT;
	}

	public void onClick$btn_cancel$addwnd() {
		this.addwnd.setVisible(false);
	}

	public void onClick$btn_cancel() {
		bank_customers.setVisible(true);
		tgrd.setVisible(true);
		this.show_cards.setVisible(false);
	}

	/*
	 * public void onClick$btn_cancel$show_cards() {
	 * this.show_cards.setVisible(false); }
	 */

	public void onClick$tbtn_add_card() {
		if (this.tietocl == null) {
			this.alert("Клиент из GLOB.UZ не выбран");
			return;
		}
		final String cl_id = CustomerService.get_BR_by_tieto(this.tietocl.getClient(), this.alias);
		Res res = new Res();
		res = TclientService.check_user(this.alias, this.branch, this.tietocl.getClient());
		if (res.getCode() == 0) {
			this.alert(res.getName());
			return;
		}
		final GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
		acfilter.setClient(cl_id);
		acfilter.setAcc_bal("2261");
		acfilter.setBranch(this.branch);
		acfilter.setCurrency("000");
		final com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, (Object) acfilter, this.alias);
		this.addwnd$accGrid.setModel((ListModel) acc_model);
		this.addwnd$sproduct.setSelecteditem((String) null);
		this.addwnd$btn_add.setVisible(true);
		this.addwnd$sproduct.setVisible(true);
		this.addwnd.setVisible(true);
	}

	private void set_default() {
		CheckNull.clearForm(this.addCustomer$addgrdl);
		CheckNull.clearForm(this.addCustomer$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);
		CheckNull.clearForm(this.add_everywhere$addgrdr);
		if (this.add_ti) {
			if (this.is_ho) {
				System.out.println("is_ho");
				this.fill_form(this.cur_HO_customer, null);
			} else if (this.is_br) {
				System.out.println("is_br");
				System.out.println(String.valueOf(this.cur_branch_customer.getP_code_nation())
						+ "  Natsional'nost");
				this.fill_form(this.cur_branch_customer, null);
			}
		} else if (this.add_ho) {
			if (this.is_br) {
				this.fill_bank_form(this.cur_branch_customer);
			}
		} else if (this.add_br && this.is_ho) {
			this.fill_bank_form(this.cur_HO_customer);
		}
	}

	private void fill_bank_form(final Customer base_cl) {
		if (base_cl.getP_passport_number() != null) {
			this.addCustomer$ap_passport_number.setValue(base_cl.getP_passport_number());
		}
		if (base_cl.getP_type_document() != null) {
			this.addCustomer$ap_type_document.setSelecteditem(base_cl.getP_type_document());
		}
		if (base_cl.getP_passport_serial() != null) {
			this.addCustomer$ap_passport_serial.setValue(base_cl.getP_passport_serial());
		}
		if (base_cl.getP_passport_date_registration() != null) {
			this.addCustomer$ap_passport_date_registration.setValue(base_cl.getP_passport_date_registration());
		}
		if (base_cl.getP_passport_place_registration() != null) {
			this.addCustomer$ap_passport_place_registration.setValue(base_cl.getP_passport_place_registration());
		}
		if (base_cl.getP_passport_date_expiration() != null) {
			this.addCustomer$ap_passport_date_expiration.setValue(base_cl.getP_passport_date_expiration());
		}
		if (base_cl.getP_family() != null) {
			this.addCustomer$ap_family.setValue(base_cl.getP_family());
		}
		if (base_cl.getP_first_name() != null) {
			this.addCustomer$ap_first_name.setValue(base_cl.getP_first_name());
		}
		if (base_cl.getP_patronymic() != null) {
			this.addCustomer$ap_patronymic.setValue(base_cl.getP_patronymic());
		}
		if (base_cl.getP_birthday() != null) {
			this.addCustomer$ap_birthday.setValue(base_cl.getP_birthday());
		}
		if (base_cl.getP_code_birth_region() != null) {
			this.addCustomer$ap_code_birth_region.setSelecteditem(base_cl.getP_code_birth_region());
		}
		if (base_cl.getP_code_birth_distr() != null) {
			this.addCustomer$ap_code_birth_distr.setSelecteditem(base_cl.getP_code_birth_distr());
		}
		if (base_cl.getP_birth_place() != null) {
			this.addCustomer$ap_birth_place.setValue(base_cl.getP_birth_place());
		}
		if (base_cl.getP_code_gender() != null) {
			this.addCustomer$ap_code_gender.setSelecteditem(base_cl.getP_code_gender());
		}
		if (base_cl.getP_code_nation() != null) {
			this.addCustomer$ap_code_nation.setSelecteditem(base_cl.getP_code_nation());
		}
		if (base_cl.getP_code_adr_region() != null) {
			this.addCustomer$ap_code_adr_region.setSelecteditem(base_cl.getP_code_adr_region());
		}
		if (base_cl.getP_code_adr_distr() != null) {
			this.addCustomer$ap_code_adr_distr.setSelecteditem(base_cl.getP_code_adr_distr());
		}
		if (base_cl.getP_code_tax_org() != null) {
			this.addCustomer$ap_code_tax_org.setSelecteditem(base_cl.getP_code_tax_org());
		}
		if (base_cl.getP_number_tax_registration() != null) {
			this.addCustomer$ap_number_tax_registration.setValue(base_cl.getP_number_tax_registration());
		}
		if (base_cl.getP_code_citizenship() != null) {
			this.addCustomer$ap_code_citizenship.setSelecteditem(base_cl.getP_code_citizenship());
		}
		if (base_cl.getCode_country() != null) {
			this.addCustomer$acode_country.setSelecteditem(base_cl.getCode_country());
		}
		if (base_cl.getCode_resident() != null) {
			this.addCustomer$acode_resident.setSelecteditem(base_cl.getCode_resident());
		}
		if (base_cl.getP_phone_mobile() != null) {
			this.addCustomer$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
		}
		if (base_cl.getP_email_address() != null) {
			this.addCustomer$ap_email_address.setValue(base_cl.getP_email_address());
		}
		if (base_cl.getP_phone_home() != null) {
			this.addCustomer$ap_phone_home.setValue(base_cl.getP_phone_home());
		}
		if (base_cl.getP_inps() != null) {
			this.addCustomer$ap_inps.setValue(base_cl.getP_inps());
		}
		if (base_cl.getP_post_address() != null) {
			this.addCustomer$ap_post_address.setValue(base_cl.getP_post_address());
		}
	}

	private void fill_form(final Customer base_cl, final Tclient tieto_cl) {
		if (tieto_cl != null) {
			this.fill_form(tieto_cl);
		}
		if (base_cl.getP_passport_number() != null) {
			this.add_everywhere$ap_passport_number.setValue(base_cl.getP_passport_number());
		}
		if (base_cl.getP_type_document() != null) {
			this.add_everywhere$ap_type_document.setSelecteditem(base_cl.getP_type_document());
		}
		if (base_cl.getP_passport_serial() != null) {
			this.add_everywhere$ap_passport_serial.setValue(base_cl.getP_passport_serial());
		}
		if (base_cl.getP_passport_date_registration() != null) {
			this.add_everywhere$ap_passport_date_registration.setValue(base_cl.getP_passport_date_registration());
		}
		if (base_cl.getP_passport_place_registration() != null) {
			this.add_everywhere$ap_passport_place_registration.setValue(base_cl.getP_passport_place_registration());
		}
		if (base_cl.getP_passport_date_expiration() != null) {
			this.add_everywhere$ap_passport_date_expiration.setValue(base_cl.getP_passport_date_expiration());
		}
		if (base_cl.getP_family() != null) {
			this.add_everywhere$ap_family.setValue(base_cl.getP_family());
		}
		if (base_cl.getP_first_name() != null) {
			this.add_everywhere$ap_first_name.setValue(base_cl.getP_first_name());
		}
		if (base_cl.getP_patronymic() != null) {
			this.add_everywhere$ap_patronymic.setValue(base_cl.getP_patronymic());
		}
		if (base_cl.getP_birthday() != null) {
			this.add_everywhere$ap_birthday.setValue(base_cl.getP_birthday());
		}
		if (base_cl.getP_code_birth_region() != null) {
			this.add_everywhere$ap_code_birth_region.setSelecteditem(base_cl.getP_code_birth_region());
		}
		if (base_cl.getP_code_birth_distr() != null) {
			this.add_everywhere$ap_code_birth_distr.setSelecteditem(base_cl.getP_code_birth_distr());
		}
		if (base_cl.getP_birth_place() != null) {
			this.add_everywhere$ap_birth_place.setValue(base_cl.getP_birth_place());
		}
		if (base_cl.getP_code_gender() != null) {
			this.add_everywhere$ap_code_gender.setSelecteditem(base_cl.getP_code_gender());
		}
		if (base_cl.getP_code_nation() != null) {
			this.add_everywhere$ap_code_nation.setSelecteditem(base_cl.getP_code_nation());
		}
		if (base_cl.getP_code_adr_region() != null) {
			this.add_everywhere$ap_code_adr_region.setSelecteditem(base_cl.getP_code_adr_region());
		}
		if (base_cl.getP_code_adr_distr() != null) {
			this.add_everywhere$ap_code_adr_distr.setSelecteditem(base_cl.getP_code_adr_distr());
		}
		if (base_cl.getP_code_tax_org() != null) {
			this.add_everywhere$ap_code_tax_org.setSelecteditem(base_cl.getP_code_tax_org());
		}
		if (base_cl.getP_number_tax_registration() != null) {
			this.add_everywhere$ap_number_tax_registration.setValue(base_cl.getP_number_tax_registration());
		}
		if (base_cl.getP_code_citizenship() != null) {
			this.add_everywhere$ap_code_citizenship.setSelecteditem(base_cl.getP_code_citizenship());
		}
		if (base_cl.getCode_country() != null) {
			this.add_everywhere$acode_country.setSelecteditem(base_cl.getCode_country());
		}
		if (base_cl.getCode_resident() != null) {
			this.add_everywhere$acode_resident.setSelecteditem(base_cl.getCode_resident());
		}
		if (base_cl.getP_phone_mobile() != null) {
			this.add_everywhere$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
		}
		if (base_cl.getP_email_address() != null) {
			this.add_everywhere$ap_email_address.setValue(base_cl.getP_email_address());
		}
		if (base_cl.getP_phone_home() != null) {
			this.add_everywhere$ap_phone_home.setValue(base_cl.getP_phone_home());
		}
		if (base_cl.getP_inps() != null) {
			this.add_everywhere$ap_inps.setValue(base_cl.getP_inps());
		}
		if (base_cl.getP_post_address() != null) {
			this.add_everywhere$ap_post_address.setValue(base_cl.getP_post_address());
		}
		if (base_cl.getP_code_birth_region() != null) {
			this.add_everywhere$ar_city.setSelecteditem(base_cl.getP_code_birth_region());
		}
		if (base_cl.getPinfl() != null) {
			this.add_everywhere$aPinfl.setValue(base_cl.getPinfl());
		}
	}

	private void fill_form(final Tclient base_cl) {
		if (base_cl.getSerial_no() != null) {
			this.add_everywhere$ap_passport_number.setValue(base_cl.getSerial_no());
		}
		if (base_cl.getId_card() != null) {
			this.add_everywhere$ap_passport_serial.setValue(base_cl.getId_card());
		}
		if (base_cl.getDoc_since() != null) {
			this.add_everywhere$ap_passport_date_registration.setValue(base_cl.getDoc_since());
		}
		if (base_cl.getIssued_by() != null) {
			this.add_everywhere$ap_passport_place_registration.setValue(base_cl.getIssued_by());
		}
		if (base_cl.getSurname() != null) {
			this.add_everywhere$ap_family.setValue(base_cl.getSurname());
		}
		if (base_cl.getF_names() != null) {
			this.add_everywhere$ap_first_name.setValue(base_cl.getF_names());
		}
		if (base_cl.getM_name() != null) {
			this.add_everywhere$ap_patronymic.setValue(base_cl.getM_name());
		}
		if (base_cl.getB_date() != null) {
			this.add_everywhere$ap_birthday.setValue(base_cl.getB_date());
		}
		if (base_cl.getSex() != null) {
			this.add_everywhere$ap_code_gender.setSelecteditem(base_cl.getSex());
		}
		if (base_cl.getR_cntry() != null) {
			this.add_everywhere$acode_country.setSelecteditem((base_cl.getR_cntry().compareTo("UZB") == 0) ? "860"
					: null);
		}
		if (base_cl.getResident() != null) {
			this.add_everywhere$acode_resident.setSelecteditem(base_cl.getResident());
		}
		if (base_cl.getRmob_phone() != null) {
			this.add_everywhere$ap_phone_mobile.setValue(base_cl.getRmob_phone());
		}
		if (base_cl.getR_emails() != null) {
			this.add_everywhere$ap_email_address.setValue(base_cl.getR_emails());
		}
		if (base_cl.getR_phone() != null) {
			this.add_everywhere$ap_phone_home.setValue(base_cl.getR_phone());
		}
		if (base_cl.getR_street() != null) {
			this.add_everywhere$ap_post_address.setValue(base_cl.getR_street());
		}
		if (base_cl.getPersone_code() != null) {
			this.add_everywhere$acode_tel.setValue(base_cl.getPersone_code());
		}
	}

	public void onSelect$ap_code_adr_region$add_everywhere() {
		this.add_everywhere$ap_code_adr_distr.setModel((ListModel) new ListModelList((Collection) Utils.getDistr(this.add_everywhere$ap_code_adr_region.getValue(), this.alias)));
	}

	public void onSelect$ap_code_birth_region$add_everywhere() {
		this.add_everywhere$ap_code_birth_distr.setModel((ListModel) new ListModelList((Collection) Utils.getDistr(this.add_everywhere$ap_code_birth_region.getValue(), this.alias)));
	}

	public void onClick$btn_cancel$accounts() {
		this.accounts.setVisible(false);
	}

	public void onClick$btn_add$accounts() {
	}

	public void onSelect$sproduct$addwnd() {
		final String cl_id = CustomerService.get_BR_by_tieto(this.tietocl.getClient(), this.alias);
		System.out.println("CL_ID = " + cl_id);
		System.out.println("card_code = " + this.addwnd$sproduct.getValue());
		final List<GlobuzAccount> aModel = CustomerService.get_card_accounts_new_card(cl_id, this.branch, this.addwnd$sproduct.getValue(), this.tietocl.getClient());
		this.addwnd$accGrid.setModel((ListModel) new ListModelList((Collection) aModel));
	}

	// ОТКРЫТИЕ СЧЁТА///////////ОТКРЫТИЕ СЧЕТА//////////ОТКРЫТЬ
	// СЧЁТ//////////////ОТКРЫТЬ СЧЕТ
	// СЧЕТ////////////////
	public void onClick$btn_add$addwnd() {
		Res res = new Res();
		try {
			if (CheckNull.isEmpty(this.addwnd$sproduct.getValue())) {
				this.alert("Выберите продукт");
				return;
			}
			if (cur_branch_customer == null) {
				alert("Вернитесь в главное окно и выберите клиента");
				return;
			}
			int last_ord = 0;
			final TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(this.addwnd$sproduct.getValue()), this.alias);
			int ord = Integer.parseInt(tcs.getId_order_account());
			ISLogger.getLogger().error((Object) ("Открытие счёта last three digits ord = " + ord));
			if (addwnd$sproduct.getValue().equals("010")
					|| addwnd$sproduct.getValue().equals("012")) {
				ord = humoAccOrdFrom;
				last_ord = humoAccOrdPrevious;
			} else if (addwnd$sproduct.getValue().equals("011")) {
				ord = humoCreditOrdFrom;
				last_ord = humoCreditOrdPrevious;
			} else if (addwnd$sproduct.getValue().equals("013")) {
				if (humoCobrandOrdFrom == 0 || humoCobrandOrdPrevious == 0) {
					alert("Нет значений для счёта по данному типу карт!");
					return;
				} else {
					ord = humoCobrandOrdFrom;
					last_ord = humoCobrandOrdPrevious;
				}
			} else if (addwnd$sproduct.getValue().equals("014")) {
				if (CustomerService.isHumoVisaCreateAccessGranted(branch)) {
					if (humoAccOrdVisaFrom == 0 || humoAccOrdVisaPrevious == 0) {
						alert("Нет значений для счёта по данному типу карт!");
						return;
					} else {
						ord = humoAccOrdVisaFrom;
						last_ord = humoAccOrdVisaPrevious;
					}
				} else {
					alert("Недоступно!");
					return;
				}
			} else if (addwnd$sproduct.getValue().equals("015")) {
				if (humoAccOrdHayriyaFrom == 0
						|| humoAccOrdHayriyaPrevious == 0) {
					alert("Нет значений для счёта по данному типу карт!");
					return;
				} else {
					ord = humoAccOrdHayriyaFrom;
					last_ord = humoAccOrdHayriyaPrevious;
				}
			}
			String acc = addwnd$sproduct.getValue().equals("012") ? "22617"
					: "22618";
			res = this.open_acc(String.valueOf(ord), String.valueOf(last_ord), this.cur_branch_customer.getId_client(), this.branch, 0, acc);
			if (CustomerService.checkCompanyEmployeeExistence(cur_branch_customer.getId_client(), branch)) {
				CustomerService.setCompanyEmployeeAccount(res.getName(), cur_branch_customer.getId_client(), Utils.getValueFromSql("select customer_id from humo_client_work where employee_id = "
						+ cur_branch_customer.getId_client()
						+ " and branch = "
						+ branch + "", alias), branch, alias);
			}
			final GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
			String client = "";
			if (this.tietocl.getClient_b().length() == 13) {
				client = this.tietocl.getClient_b().substring(5);
			} else {
				client = this.tietocl.getClient_b();
			}
			if (!client.isEmpty()) {
				acfilter.setClient(client);
			}
			acfilter.setAcc_bal("2261");
			acfilter.setBranch(this.branch);
			acfilter.setCurrency("000");
			final com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, (Object) acfilter, this.alias);
			this.addwnd$accGrid.setModel((ListModel) acc_model);
			this.alert(res.getName());
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			this.alert(CheckNull.getPstr(e));
		}
	}

	public Res open_acc(String ord, final String last, final String client, final String branch, final int group, String acc) {
		Res res = new Res();
		try {
			final String ccycd = "000";
			final String cl_id = client;
			final String alias = Utils.getALias(branch);
			final Customer cst = CustomerService.getCustomerById(cl_id, branch, alias);

			if (cst == null) {
				res.setCode(-1);
				res.setName("Не найден клиент с номером " + cl_id
						+ " в филиале " + branch + " в схеме " + alias);
				return res;
			}
			ISLogger.getLogger().error("VER10 CLIENT ID: " + cl_id
					+ " CUSTOMER ID: " + cst.getId() + " CUSTOMER ID_CLIENT: "
					+ cst.getId_client() + " CUSTOMER NAME: " + cst.getName());

			final String customerName = (cst.getName().length() > 80) ? cst.getName().substring(0, 79)
					: cst.getName();
			ISLogger.getLogger().error("open acc: " + acc);
			ISLogger.getLogger().error("open ord: " + ord);
			ISLogger.getLogger().error("open last: " + last);
			ISLogger.getLogger().error("open cst.getId_client(): "
					+ cst.getId_client());
			ISLogger.getLogger().error("open branch: " + branch);
			ISLogger.getLogger().error("open alias: " + alias);
			Res ordResult = CustomerService.Get_acc_hole(acc, ord, last, cst.getId_client(), branch, alias);
			if (ordResult.getCode() == -1) {
				return ordResult;
			} else {
				ord = ordResult.getName();
			}
			// ord = CustomerService.Get_acc_hole(acc, ord, last,
			// cst.getId_client(), branch, alias).getName();
			System.out.println("ord create acc: " + ord);
			final String customer_id = cst.getId_client();
			final boolean brcomp = branch.compareTo(ConnectionPool.getValue("HO", alias)) == 0;
			res = GlobuzAccountService.doAction_create_acc_in_br(this.un, this.pwd, acc, customer_id, ccycd, ord, customerName, group, alias, branch, Boolean.valueOf(brcomp));
			if (res.getCode() != 0) {
				return res;
			}
			final String result = res.getName();
			res.setName(result);
		} catch (Exception e) {
			res.setName("ERROR => " + e.getLocalizedMessage());
			res.setCode(-1);
			e.printStackTrace();
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
		}
		return res;
	}

	public void onClick$btn_block$blockwnd() {
		final String reason = this.blockwnd$sstopcauses.getValue();
		final String reason_text = this.blockwnd$txtstopcauses.getValue();
		final Res res = TclientService.block_card(this.branch, this.alias, this.issuingPortProxy, reason, reason_text, this.blockcard.getCARD());
		LtLogger.getLogger().info((Object) res.getName());
		System.out.println(res.getName());
		if (res.getName() != null) {
			this.alert(res.getName());
		}
		this.tcust.setTietoCustomerId(this.tietocl.getClient());
		this.blockwnd.setVisible(false);
	}

	public void onClick$btn_cancel$blockwnd() {
		this.blockwnd.setVisible(false);
	}

	public void onClick$btn_cancel$printwnd() {
		this.printwnd.setVisible(false);
	}

	public void onClick$btn_visa_cup_app$show_cards() {
		if (this.tietocl == null) {
			this.alert("Клиент не выбран");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG1", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/1.jpg");
		params.put("P_IMG2", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/2.jpg");
		params.put("P_IMG3", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/3.jpg");
		params.put("P_IMG4", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/4.jpg");
		this.fill_cl_report(this.tietocl, "TI_APPLICATION_VISA_CAP_IM", params);
	}

	public void onClick$btn_visa_cup_d$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(this.tietocl, "TI_DOGOVOR_VISA_CAP", params);
	}

	public void onClick$btn_exchange_app$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(this.tietocl, "TI_APPL_VISA_EXCHANGE", params);
	}

	public void onClick$btn_exchange_d$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(this.tietocl, "TI_DOGOVOR_VISA_EXCHANGE", params);
	}

	public void onClick$btn_u_exchange_d$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(this.tietocl, "TI_DOGOVOR_VISA_UPEXCHANGE", params);
	}

	public void onClick$btn_au_pt_d$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(this.tietocl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}

	public void onClick$btn_au_pt_app$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(this.tietocl, "TI_APPL_VISA_PREMIUM", params);
	}

	public void onClick$btn_visa_cup_app$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG1", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/1.jpg");
		params.put("P_IMG2", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/2.jpg");
		params.put("P_IMG3", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/3.jpg");
		params.put("P_IMG4", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/card_report_images/4.jpg");
		this.fill_cl_report(tcl, "TI_APPLICATION_VISA_CAP_IM", params);
	}

	public void onClick$btn_visa_cup_d$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(tcl, "TI_DOGOVOR_VISA_CAP", params);
	}

	public void onClick$btn_exchange_app$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(tcl, "TI_APPL_VISA_EXCHANGE", params);
	}

	public void onClick$btn_exchange_d$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(tcl, "TI_DOGOVOR_VISA_EXCHANGE", params);
	}

	public void onClick$btn_u_exchange_d$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(tcl, "TI_DOGOVOR_VISA_UPEXCHANGE", params);
	}

	public void onClick$btn_au_pt_d$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(tcl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}

	public void onClick$btn_au_pt_app$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		this.fill_cl_report(tcl, "TI_APPL_VISA_PREMIUM", params);
	}

	public void onClick$btn_add_acc_app$add_everywhere() {
		final Tclient tcl = this.get_data_from_add_everywhere();
		if (tcl == null) {
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/logo_ipak.jpg");
		this.fill_cl_report(tcl, "TI_APPLICATION1", params);
	}

	public void onClick$btn_add_acc_app$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + this.session.getLocalAddr()
				+ ":5600/ti/images/logo_ipak.jpg");
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(this.alias);
			System.out.println("tietocl.getClient()->"
					+ this.tietocl.getClient());
			System.out.println("branch->" + this.branch);
			final GlobuzAccount acc_20206 = CustomerService.getAccount(CustomerService.GetCur20206_tclient(this.tietocl.getClient(), this.branch, c), this.alias, this.branch, c);
			params.put("acc_20206", (acc_20206.getId().length() < 1) ? "20206"
					: acc_20206.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (Exception ex) {
			}
		}
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception ex2) {
		}
		this.fill_cl_report(this.tietocl, "TI_APPLICATION1", params);
	}

	public void onClick$btn_TI_APPLICATION_AUTOCARD_ACC$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + this.session.getLocalAddr() + ":"
				+ "5600" + "/ti/images/logo_ipak.jpg");
		this.fill_cl_report(this.tietocl, "TI_APPLICATION_AUTOCARD_ACC", params);
	}

	public void onClick$btn_TI_DOGOVOR_AUTOCARD$show_cards() {
		if (this.tietocl == null) {
			this.alert("\u041a\u043b\u0438\u0435\u043d\u0442 GLOB UZ \u043d\u0435 \u0432\u044b\u0431\u0440\u0430\u043d");
			return;
		}
		final HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + this.session.getLocalAddr() + ":"
				+ ((Integer) this.session.getAttribute("port")).toString()
				+ "/ti/images/logo_ipak.jpg");
		this.fill_cl_report(this.tietocl, "TI_DOGOVOR_AUTOCARD", params);
	}

	private Tclient get_data_from_add_everywhere() {
		final Tclient rtc = new Tclient();
		boolean fl_err = false;
		String err = "";
		if (!this.add_everywhere$ap_passport_number.getValue().matches("[a-zA-Z0-9]+")
				|| this.add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (!this.add_everywhere$ap_passport_serial.getValue().matches("[a-zA-Z0-9]+")
				|| this.add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!this.add_everywhere$ap_passport_place_registration.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| this.add_everywhere$ap_passport_place_registration.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто регистрации паспорта";
		}
		if (!this.add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9]+")
				|| this.add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!this.add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9]+")
				|| this.add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!this.add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9]*")
				|| this.add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_type_document.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!this.add_everywhere$ap_number_tax_registration.getValue().matches("[0-9]*")
				|| this.add_everywhere$ap_number_tax_registration.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(this.add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(this.add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_passport_date_registration.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата регистрации паспорта";
		}
		if (CheckNull.isEmpty(this.add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (!this.add_everywhere$ap_post_address.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| this.add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (!this.add_everywhere$ap_birth_place.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| this.add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}
		if (fl_err) {
			this.alert("Ошибка заполнения формы:\nневерно заполнено поле "
					+ err);
			return null;
		}
		rtc.setF_names(this.add_everywhere$ap_first_name.getValue());
		rtc.setSurname(this.add_everywhere$ap_family.getValue());
		rtc.setM_name(this.add_everywhere$ap_patronymic.getValue());
		rtc.setDoc_since(this.add_everywhere$ap_passport_date_registration.getValue());
		rtc.setB_date(this.add_everywhere$ap_birthday.getValue());
		rtc.setResident(this.add_everywhere$acode_resident.getValue());
		rtc.setSex(this.add_everywhere$ap_code_gender.getValue());
		rtc.setSerial_no(this.add_everywhere$ap_passport_serial.getValue());
		rtc.setId_card(this.add_everywhere$ap_passport_number.getValue());
		rtc.setR_city(this.add_everywhere$ar_city.getValue());
		rtc.setR_street(this.add_everywhere$ap_post_address.getValue());
		rtc.setR_emails(this.add_everywhere$ap_email_address.getValue());
		rtc.setRmob_phone(this.add_everywhere$ap_phone_mobile.getValue());
		rtc.setR_phone(this.add_everywhere$ap_phone_home.getValue());
		rtc.setR_cntry((this.add_everywhere$acode_country.getValue().compareTo("860") == 0) ? "UZB"
				: null);
		rtc.setIssued_by(this.add_everywhere$ap_passport_place_registration.getValue());
		rtc.setPersone_code(this.add_everywhere$acode_tel.getValue());
		return rtc;
	}

	private void fill_cl_report(final Tclient tietocl, final String rep_name, final HashMap<String, Object> params, final CardInfo cardInfos) {
		params.put("CLIENT_NAME1", (tietocl.getSurname() == null) ? ""
				: tietocl.getSurname());
		params.put("CLIENT_NAME2", (tietocl.getF_names() == null) ? ""
				: tietocl.getF_names());
		params.put("CLIENT_NAME3", (tietocl.getM_name() == null) ? ""
				: tietocl.getM_name());
		params.put("CLIENT_FULL_NAME", cardInfos.getCARD_String());
		params.put("DOC_SERIAL", (tietocl.getSerial_no() == null) ? ""
				: tietocl.getSerial_no());
		params.put("DOC_NUMBER", (tietocl.getId_card() == null) ? ""
				: tietocl.getId_card());
		params.put("DOC_DATE_ISSUE", (tietocl.getDoc_since() == null) ? ""
				: this.df.format(tietocl.getDoc_since()));
		params.put("DOC_ISSUE", (tietocl.getIssued_by() == null) ? ""
				: tietocl.getIssued_by());
		params.put("CLIENT_BIRTHDAY", (tietocl.getB_date() == null) ? ""
				: this.df.format(tietocl.getB_date()));
		params.put("PHONE_PASSWORD", (tietocl.getPersone_code() == null) ? ""
				: tietocl.getPersone_code());
		params.put("POST_ADDRESS", (tietocl.getR_street() == null) ? ""
				: tietocl.getR_street());
		params.put("CITIZENSHIP", (tietocl.getR_cntry() == null) ? ""
				: TclientService.getCountryNameISO3(tietocl.getR_cntry()));
		params.put("JOB_PLACE", "");
		params.put("WORK_PLACE", "");
		params.put("PHONE_WORK", tietocl.getR_phone());
		params.put("EMAIL", (tietocl.getR_emails() == null) ? ""
				: tietocl.getR_emails());
		params.put("PHONE_NUMBER", (tietocl.getR_phone() == null) ? ""
				: tietocl.getR_phone());
		final String halias = CustomerService.get_alias_ho(this.alias);
		params.put("BRANCH_NAME", Utils.getMfo_name(this.branch, halias).get(0).getLabel());
		params.put("BRANCH", this.branch);
		params.put("CARD_DEPARTMENT_EXEC", UserService.getUser(this.uid, this.branch).getFull_name());
		params.put("BANK_PERSON", "");
		params.put("MICRO_DEPARTMENT_EXEC", "");
		params.put("CARD_VID", "");
		params.put("CUR_DATE", this.df.format(new Date()));
		this.printwnd$rpframe.setContent((Media) DPrint.getRepPdf(String.valueOf(rep_name)
				+ ".pdf", this.application.getRealPath("reports/" + rep_name
				+ ".jasper"), (Map) params, this.alias));
		this.printwnd.setVisible(true);
	}

	private void fill_cl_report(final Tclient tietocl, final String rep_name, final HashMap<String, Object> params) {
		params.put("CLIENT_NAME1", (tietocl.getSurname() == null) ? ""
				: tietocl.getSurname());
		params.put("CLIENT_NAME2", (tietocl.getF_names() == null) ? ""
				: tietocl.getF_names());
		params.put("CLIENT_NAME3", (tietocl.getM_name() == null) ? ""
				: tietocl.getM_name());
		params.put("CLIENT_FULL_NAME", params.get("CLIENT_NAME1") + " "
				+ params.get("CLIENT_NAME2") + " " + params.get("CLIENT_NAME3"));
		params.put("DOC_SERIAL", (tietocl.getSerial_no() == null) ? ""
				: tietocl.getSerial_no());
		params.put("DOC_NUMBER", (tietocl.getId_card() == null) ? ""
				: tietocl.getId_card());
		params.put("DOC_DATE_ISSUE", (tietocl.getDoc_since() == null) ? ""
				: this.df.format(tietocl.getDoc_since()));
		params.put("DOC_ISSUE", (tietocl.getIssued_by() == null) ? ""
				: tietocl.getIssued_by());
		params.put("CLIENT_BIRTHDAY", (tietocl.getB_date() == null) ? ""
				: this.df.format(tietocl.getB_date()));
		params.put("PHONE_PASSWORD", (tietocl.getPersone_code() == null) ? ""
				: tietocl.getPersone_code());
		params.put("POST_ADDRESS", (tietocl.getR_street() == null) ? ""
				: tietocl.getR_street());
		params.put("CITIZENSHIP", (tietocl.getR_cntry() == null) ? ""
				: TclientService.getCountryNameISO3(tietocl.getR_cntry()));
		params.put("JOB_PLACE", "");
		params.put("WORK_PLACE", "");
		params.put("PHONE_WORK", tietocl.getR_phone());
		params.put("EMAIL", (tietocl.getR_emails() == null) ? ""
				: tietocl.getR_emails());
		params.put("PHONE_NUMBER", (tietocl.getR_phone() == null) ? ""
				: tietocl.getR_phone());
		final String halias = CustomerService.get_alias_ho(this.alias);
		params.put("BRANCH_NAME", Utils.getMfo_name(this.branch, halias).get(0).getLabel());
		params.put("BRANCH", this.branch);
		params.put("CARD_DEPARTMENT_EXEC", UserService.getUser(this.uid, this.branch).getFull_name());
		params.put("BANK_PERSON", "");
		params.put("MICRO_DEPARTMENT_EXEC", "");
		params.put("CARD_VID", "");
		params.put("CUR_DATE", this.df.format(new Date()));
		this.printwnd$rpframe.setContent((Media) DPrint.getRepPdf(String.valueOf(rep_name)
				+ ".pdf", this.application.getRealPath("reports/" + rep_name
				+ ".jasper"), (Map) params, this.alias));
		this.printwnd.setVisible(true);
	}

	public void onClick$acode_resident$add_everywhere() {
		this.add_everywhere$acode_resident.select();
	}

	public void onClick$ap_code_citizenship$add_everywhere() {
		this.add_everywhere$ap_code_citizenship.select();
	}

	public void onClick$acode_country$add_everywhere() {
		this.add_everywhere$acode_country.select();
	}

	public void onClick$pop_open_cl() {
		final Res res = CustomerService.doAction_open_closed(this.un, this.pwd, (String) this.editPopup.getAttribute("client_id"), 20, (String) this.editPopup.getAttribute("branch"), (String) this.editPopup.getAttribute("alias"), this.branch);
		if (res.getCode() != 0) {
			this.alert(res.getName());
			return;
		}
		this.alert("Клиент открыт!");
		this.refreshModel(this._starttPageNumber);
	}

	public void onOpen$editPopup(final OpenEvent event) throws Exception {
		if (event.isOpen()) {
			this.editPopup.setAttribute("branch", (Object) event.getReference().getAttribute("branch"));
			this.editPopup.setAttribute("client_id", (Object) event.getReference().getAttribute("client_id"));
			this.editPopup.setAttribute("alias", (Object) event.getReference().getAttribute("alias"));
		}
	}

	public static String get_uzbek_month(final Date date) {
		String monthString = "";
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		final int month = cal.get(2);
		switch (month) {
		case 0: {
			monthString = "yanvar";
			break;
		}
		case 1: {
			monthString = "fevral";
			break;
		}
		case 2: {
			monthString = "mart";
			break;
		}
		case 3: {
			monthString = "aprel";
			break;
		}
		case 4: {
			monthString = "may";
			break;
		}
		case 5: {
			monthString = "iyun";
			break;
		}
		case 6: {
			monthString = "iyul";
			break;
		}
		case 7: {
			monthString = "avgust";
			break;
		}
		case 8: {
			monthString = "sentabr";
			break;
		}
		case 9: {
			monthString = "oktabr";
			break;
		}
		case 10: {
			monthString = "noyabr";
			break;
		}
		case 11: {
			monthString = "dekabr";
			break;
		}
		default: {
			monthString = "Invalid month";
			break;
		}
		}
		return monthString;
	}

	public String getCur_cl_acc() {
		return this.cur_cl_acc;
	}

	public void setCur_cl_acc(final String cur_cl_acc) {
		this.cur_cl_acc = cur_cl_acc;
	}

	public Customer getCur_HO_customer() {
		return this.cur_HO_customer;
	}

	public void setCur_HO_customer(final Customer cur_HO_customer) {
		this.cur_HO_customer = cur_HO_customer;
	}

	public Customer getCur_branch_customer() {
		return this.cur_branch_customer;
	}

	public void setCur_branch_customer(final Customer cur_branch_customer) {
		this.cur_branch_customer = cur_branch_customer;
	}

	private static IssuingPortProxy initNp(final String alias) throws ClientProtocolException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		final IssuingPortProxy issuingPortProxy = new IssuingPortProxy(ConnectionPool.getValue("HUMO_HOST"), ConnectionPool.getValue("HUMO_USERNAME"), ConnectionPool.getValue("HUMO_PASSWORD"));
		ISLogger.getLogger().error("INITNP HUMO HOST: "
				+ ConnectionPool.getValue("HUMO_HOST"));
		ISLogger.getLogger().error("INITNP HUMO UN: "
				+ ConnectionPool.getValue("HUMO_USERNAME"));
		ISLogger.getLogger().error("INITNP HUMO PW: "
				+ ConnectionPool.getValue("HUMO_PASSWORD"));
		return issuingPortProxy;
	}

	public static String getFnm(final GlobuzAccount pAccInfo) {
		String full_name = "";
		final CharSequence ch = " null";
		final CharSequence newch = " ";
		full_name = pAccInfo.getName();
		if (full_name.contains(ch)) {
			full_name = full_name.replace(ch, newch);
		}
		return full_name;
	}

	public List<CardInfo> makeList(final List<AccInfo> list) {
		List<CardInfo> cardInfos = new ArrayList<CardInfo>();
		final List<CardInfo> cards = new ArrayList<CardInfo>();
		for (int i = 0; i < list.size(); ++i) {
			System.out.println("list.get(i).getClient() = "
					+ list.get(i).getClient());
			cardInfos = (List<CardInfo>) list.get(i).getCardlist();
			for (int j = 0; j < cardInfos.size(); ++j) {
				System.out.println("cardInfos.get(j).getEXPIRY() = "
						+ cardInfos.get(j).getEXPIRY());
				cards.add(cardInfos.get(j));
			}
		}
		return cards;
	}

	private String operationWithCard(final String state_card, final CardInfo cardInfo, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy) {
		final String message = this.callSoapWebService(this.soapEndpointUrl, this.soapAction, cardInfo.getCLIENT_ID(), Utils.toTranslit(String.valueOf(this.tietocl.getF_names())
				+ " " + this.tietocl.getSurname()), state_card, cardInfo.getCARD(), expiry, phoneNumber, issuingPortProxy);
		return message;
	}

	private void createSoapEnvelope(final SOAPMessage soapMessage, final String t_client_id, final String card_holder_name, final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy) throws SOAPException, RemoteException, ParseException {
		final SOAPPart soapPart = soapMessage.getSOAPPart();
		final SOAPEnvelope envelope = soapPart.getEnvelope();
		final String myNamespace = "urn";
		final String myNamespaceURI = String.valueOf(myNamespace) + ":"
				+ "AccessGateway";
		envelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/");
		envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
		envelope.addNamespaceDeclaration("xsd", "http://www.w3.org/2001/XMLSchema");
		envelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		final SOAPBody soapBody = envelope.getBody();
		final SOAPElement soapBodyElem = soapBody.addChildElement("import", myNamespace);
		final SOAPElement state = soapBodyElem.addChildElement("state");
		state.addTextNode(state_card);
		final SOAPElement cardholderID = soapBodyElem.addChildElement("cardholderID");
		ISLogger.getLogger().error("settings.get(this.branch).getBank_c(): "
				+ settings.get(this.branch).getBank_c());
		ISLogger.getLogger().error("settings.get(this.branch).getGROUPC(): "
				+ settings.get(this.branch).getGroup_c());
		cardholderID.addTextNode(String.valueOf(t_client_id) + "-"
				+ AddCstViewCtrl.settings.get(this.branch).getBank_c());
		if (state_card.equals("on")) {
			final SOAPElement cardholderName = soapBodyElem.addChildElement("cardholderName");
			cardholderName.addTextNode(card_holder_name);
			final SOAPElement language = soapBodyElem.addChildElement("language");
			language.addTextNode("ru_translit");
			final SOAPElement charge = soapBodyElem.addChildElement("Charge");
			final SOAPElement agreementCharge = charge.addChildElement("agreementCharge");
			agreementCharge.addTextNode("MONTH.FEE.OFF");
			final SOAPElement card = soapBodyElem.addChildElement("Card");
			final SOAPElement card_state = card.addChildElement("state");
			card_state.addTextNode(state_card);
			final SOAPElement card_pan = card.addChildElement("pan");
			String cardReal = "";
			try {
				if (CustomerService.getRealCardByCard(alias, card_number).isEmpty()) {
					final OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
					connectionInfo.setBANK_C(AddCstViewCtrl.settings.get(this.branch).getBank_c());
					connectionInfo.setGROUPC(AddCstViewCtrl.settings.get(this.branch).getGroup_c());
					connectionInfo.setEXTERNAL_SESSION_ID(Utils.getExternalSession());
					final RowType_GetRealCard_Request parameters = new RowType_GetRealCard_Request();
					parameters.setCARD(card_number);
					final OperationResponseInfoHolder responseInfo = new OperationResponseInfoHolder();
					final RowType_GetRealCard_ResponseHolder details = new RowType_GetRealCard_ResponseHolder();
					ObjectMapper objectMapper = new ObjectMapper();
					try {
						ISLogger.getLogger().error("createSoapEnvelope connectionInfo : "
								+ objectMapper.writeValueAsString(connectionInfo));
						ISLogger.getLogger().error("createSoapEnvelope parameters : "
								+ objectMapper.writeValueAsString(parameters));
						ISLogger.getLogger().error("createSoapEnvelope responseInfo : "
								+ objectMapper.writeValueAsString(responseInfo));
						ISLogger.getLogger().error("createSoapEnvelope details : "
								+ objectMapper.writeValueAsString(details));
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					issuingPortProxy.getRealCard(connectionInfo, parameters, responseInfo, details);
					cardReal = details.value.getRCARD();
				} else {
					try {
						cardReal = CustomerService.getRealCardByCard(alias, card_number);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * String real_card = CustomerService.getRealCardNumber(card_number,
			 * issuingPortProxy, AddCstViewCtrl.settings.get(this.branch)
			 * .getBank_c(),
			 * AddCstViewCtrl.settings.get(this.branch).getGroup_c());
			 */

			/*
			 * try {
			 * card_pan.addTextNode(CustomerService.getRealCardByCard(alias,
			 * card_number)); } catch (SQLException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); }
			 */
			card_pan.addTextNode(cardReal);
			final SOAPElement card_expiry = card.addChildElement("expiry");
			// final Date temp_expiry = this.dfExpiry.parse(expiry);
			// card_expiry.addTextNode(dfExpiry.format((temp_expiry)));
			card_expiry.addTextNode(expiry);
			final SOAPElement service = card.addChildElement("Service");
			final SOAPElement serviceID = service.addChildElement("serviceID");
			serviceID.addTextNode("MB-ALL");
			final SOAPElement phone = soapBodyElem.addChildElement("Phone");
			final SOAPElement phone_state = phone.addChildElement("state");
			phone_state.addTextNode("on");
			final SOAPElement phone_msisdn = phone.addChildElement("msisdn");
			phone_msisdn.addTextNode(phoneNumber);
			final SOAPElement phone_deliveryChannel = phone.addChildElement("deliveryChannel");
			phone_deliveryChannel.addTextNode("-");
		}
	}

	private String callSoapWebService(final String soapEndpointUrl, final String soapAction, final String t_client_id, final String card_holder_name, final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy) {
		String message = null;
		Connection c = null;
		try {
			final SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			final SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			Label_0153: {
				try {
					final SOAPMessage soapResponse = soapConnection.call(this.createSOAPRequest(soapAction, t_client_id, card_holder_name, state_card, card_number, expiry, phoneNumber, issuingPortProxy), (Object) soapEndpointUrl);
					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					soapResponse.writeTo((OutputStream) out);
					final String strMsg = new String(out.toByteArray());
					ISLogger.getLogger().error((Object) strMsg);
				} catch (SOAPException e) {
					e.printStackTrace();
					message = e.getMessage();
					ISLogger.getLogger().error("SOAP EXCEPTION: "
							+ (Object) CheckNull.getPstr((Exception) e));
					break Label_0153;
				} finally {
					if (soapConnection != null) {
						soapConnection.close();
					}
				}
			}
			c = ConnectionPool.getConnection();
			CustomerService.saveCardSmsState(card_number, phoneNumber, state_card.equals("on") ? 1
					: 2, c);
			c.commit();
		} catch (Exception e2) {
			e2.printStackTrace();
			Utils.rollback(c);
			ISLogger.getLogger().error("VER 8 JAN GET PSTR E2: "
					+ (Object) CheckNull.getPstr(e2));
			message = "Ошибка";
			return message;
		} finally {
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return message;
	}

	private SOAPMessage createSOAPRequest(final String soapAction, final String t_client_id, final String card_holder_name, final String state_card, final String card_number, final String expiry, final String phoneNumber, final IssuingPortProxy issuingPortProxy) throws Exception {
		final MessageFactory messageFactory = MessageFactory.newInstance();
		final SOAPMessage soapMessage = messageFactory.createMessage();
		this.createSoapEnvelope(soapMessage, t_client_id, card_holder_name, state_card, card_number, expiry, phoneNumber, issuingPortProxy);
		final String loginPassword = String.valueOf(Utils.getValue("EMPC_TIETO_HOST_USERNAME"))
				+ ":" + Utils.getValue("EMPC_TIETO_HOST_PASSWORD");
		final MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		headers.addHeader("Authorization", "Basic "
				+ new String(Base64.encodeBase64(loginPassword.getBytes())));
		soapMessage.saveChanges();
		System.out.println("Request SOAP Message:");
		soapMessage.writeTo((OutputStream) System.out);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		soapMessage.writeTo((OutputStream) out);
		new String(out.toByteArray());
		new ObjectMapper();
		return soapMessage;
	}

	static/* synthetic */void access$2(final AddCstViewCtrl addCstViewCtrl, final IssuingPortProxy issuingPortProxy) {
		addCstViewCtrl.issuingPortProxy = issuingPortProxy;
	}

	static/* synthetic */void access$3(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bt_acc_act) {
		addCstViewCtrl.bt_acc_act = bt_acc_act;
	}

	static/* synthetic */void access$7(final AddCstViewCtrl addCstViewCtrl, final String s) {
		addCstViewCtrl.alert(s);
	}

	static/* synthetic */void access$12(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bt_acc_insert) {
	}

	static/* synthetic */void access$21(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bt_refresh_with_new) {
		addCstViewCtrl.bt_refresh_with_new = bt_refresh_with_new;
	}

	static/* synthetic */void access$22(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btunblock_card) {
		addCstViewCtrl.btunblock_card = btunblock_card;
	}

	static/* synthetic */void access$23(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btblock_card) {
		addCstViewCtrl.btblock_card = btblock_card;
	}

	/*
	 * static synthetic void access$24(final AddCstViewCtrl addCstViewCtrl,
	 * final Toolbarbutton btrefresh_card) { addCstViewCtrl.btrefresh_card =
	 * btrefresh_card; }
	 */

	static/* synthetic */void access$25(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btrefresh_card_app) {
		addCstViewCtrl.btrefresh_card_app = btrefresh_card_app;
	}

	static/* synthetic */void access$26(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bt_block_card_acc) {
		addCstViewCtrl.bt_block_card_acc = bt_block_card_acc;
	}

	static/* synthetic */void access$27(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bt_close_card_acc) {
		addCstViewCtrl.bt_close_card_acc = bt_close_card_acc;
	}

	static/* synthetic */void access$28(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bt_unblock_card_acc) {
		addCstViewCtrl.bt_unblock_card_acc = bt_unblock_card_acc;
	}

	static/* synthetic */void access$39(final AddCstViewCtrl addCstViewCtrl, final CardInfo blockcard) {
		addCstViewCtrl.blockcard = blockcard;
	}

	static/* synthetic */void access$41(final AddCstViewCtrl addCstViewCtrl, final Confirmation_rep_data cfrd) {
		addCstViewCtrl.cfrd = cfrd;
	}

	static/* synthetic */void access$44(final AddCstViewCtrl addCstViewCtrl, final String firstCardName) {
	}

	static/* synthetic */void access$55(final AddCstViewCtrl addCstViewCtrl, final String contract_nmb) {
		addCstViewCtrl.contract_nmb = contract_nmb;
	}

	static/* synthetic */void access$59(final AddCstViewCtrl addCstViewCtrl, final boolean is_ti) {
		addCstViewCtrl.is_ti = is_ti;
	}

	static/* synthetic */void access$60(final AddCstViewCtrl addCstViewCtrl, final boolean is_br) {
		addCstViewCtrl.is_br = is_br;
	}

	static/* synthetic */void access$61(final AddCstViewCtrl addCstViewCtrl, final Customer cur_branch_customer) {
		addCstViewCtrl.cur_branch_customer = cur_branch_customer;
	}

	static/* synthetic */void access$62(final AddCstViewCtrl addCstViewCtrl, final Tclient tietocl) {
		addCstViewCtrl.tietocl = tietocl;
	}

	static/* synthetic */void access$65(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btbreak) {
		addCstViewCtrl.btbreak = btbreak;
	}

	static/* synthetic */void access$68(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btedit) {
		addCstViewCtrl.btedit = btedit;
	}

	static/* synthetic */void access$70(final AddCstViewCtrl addCstViewCtrl, final boolean is_ho) {
		addCstViewCtrl.is_ho = is_ho;
	}

	static/* synthetic */void access$71(final AddCstViewCtrl addCstViewCtrl, final boolean edit_ti) {
		addCstViewCtrl.edit_ti = edit_ti;
	}

	static/* synthetic */void access$72(final AddCstViewCtrl addCstViewCtrl, final boolean edit_br) {
		addCstViewCtrl.edit_br = edit_br;
	}

	static/* synthetic */void access$78(final AddCstViewCtrl addCstViewCtrl, final boolean fl_edit) {
		addCstViewCtrl.fl_edit = fl_edit;
	}

	static/* synthetic */void access$79(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton bth) {
		addCstViewCtrl.bth = bth;
	}

	static/* synthetic */void access$81(final AddCstViewCtrl addCstViewCtrl, final Customer cur_HO_customer) {
		addCstViewCtrl.cur_HO_customer = cur_HO_customer;
	}

	static/* synthetic */void access$82(final AddCstViewCtrl addCstViewCtrl, final boolean add_ti) {
		addCstViewCtrl.add_ti = add_ti;
	}

	static/* synthetic */void access$83(final AddCstViewCtrl addCstViewCtrl, final boolean add_ho) {
		addCstViewCtrl.add_ho = add_ho;
	}

	static/* synthetic */void access$84(final AddCstViewCtrl addCstViewCtrl, final boolean add_br) {
		addCstViewCtrl.add_br = add_br;
	}

	static/* synthetic */void access$88(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btt) {
		addCstViewCtrl.btt = btt;
	}

	static/* synthetic */void access$90(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btacc) {
		addCstViewCtrl.btacc = btacc;
	}

	static/* synthetic */void access$102(final AddCstViewCtrl addCstViewCtrl, final Toolbarbutton btb) {
		addCstViewCtrl.btb = btb;
	}

	static/* synthetic */void access$103(final AddCstViewCtrl addCstViewCtrl, final Button btrefresh_card) {
		addCstViewCtrl.btrefresh_card = btrefresh_card;
	}

	static/* synthetic */void access$104(final AddCstViewCtrl addCstViewCtrl, final Button btnResetPin) {
		addCstViewCtrl.btnResetPin = btnResetPin;
	}

	public GlobuzAccount getCurrentGlobuzAccount() {
		return currentGlobuzAccount;
	}

	public void setCurrentGlobuzAccount(GlobuzAccount currentGlobuzAccount) {
		this.currentGlobuzAccount = currentGlobuzAccount;
	}

	public CardInfo getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(CardInfo currentCard) {
		this.currentCard = currentCard;
	}

	public Button getBtrefresh_card() {
		return btrefresh_card;
	}

	public void setBtrefresh_card(Button btrefresh_card) {
		this.btrefresh_card = btrefresh_card;
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	/*
	 * public void agreNom() { try { final IssuingPortProxy issuingPortProxy =
	 * new IssuingPortProxy(ConnectionPool.getValue("HUMO_HOST"),
	 * ConnectionPool.getValue("HUMO_USERNAME"),
	 * ConnectionPool.getValue("HUMO_PASSWORD")); final String HUMO_BANK_C =
	 * ConnectionPool.getValue("HUMO_BANK_C", this.alias); final String
	 * HUMO_GROUPC = ConnectionPool.getValue("HUMO_GROUPC", this.alias); final
	 * Map<String, String> accounts = getAccountsInHashmap(); for (final
	 * Map.Entry entry : accounts.entrySet()) { final String account_no =
	 * entry.getKey().toString(); final String client =
	 * entry.getValue().toString(); final String card =
	 * HumoCardsService.getPAN(BigInteger.valueOf(Long.parseLong(account_no)),
	 * issuingPortProxy, HUMO_BANK_C, HUMO_GROUPC);
	 * HumoCardsService.updateINHumoCards(account_no, card, client);
	 * System.out.println("account_no" + account_no);
	 * System.out.println("client:" + client);
	 * System.out.println("++++++++++++++++++"); } } catch (Exception e) {
	 * ISLogger.getLogger().error((Object) ("AccountNO" + e));
	 * System.out.println("AccountNO" + e); } }
	 * 
	 * public static Map<String, String> getAccountsInHashmap() throws
	 * SQLException { Connection c = null; PreparedStatement ps = null;
	 * ResultSet rs = null; final Map<String, String> hashMapClient = new
	 * HashMap<String, String>(); try { c = ConnectionPool.getConnection(); ps =
	 * c.prepareStatement(
	 * "Select * From bf_empc_accounts a where not exists (select * from humo_cards c where c.account_no = a.account_no)"
	 * ); rs = ps.executeQuery(); while (rs.next()) {
	 * hashMapClient.put(rs.getString("account_no"), rs.getString("client")); }
	 * } catch (Exception e) { e.printStackTrace(); return hashMapClient; }
	 * finally { rs.close(); ps.close(); c.close(); } rs.close(); ps.close();
	 * c.close(); return hashMapClient; }
	 */

}
