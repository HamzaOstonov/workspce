/**
 * 
 */
package com.is.customer;

import issuing_v_01_02_xsd.OperationConnectionInfo;

import issuing_v_01_02_xsd.KeyType_Agreement;
import issuing_v_01_02_xsd.ListType_AccountInfo;
import issuing_v_01_02_xsd.ListType_CardInfo;
import issuing_v_01_02_xsd.OperationResponseInfo;
import issuing_v_01_02_xsd.RowType_AccountInfo;
import issuing_v_01_02_xsd.RowType_AccountInfo_Additional;
import issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import issuing_v_01_02_xsd.RowType_Agreement;
import issuing_v_01_02_xsd.RowType_CardInfo;
import issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import issuing_v_01_02_xsd.RowType_Customer;
import issuing_v_01_02_xsd.RowType_CustomerCustomInfo;
import issuing_v_01_02_xsd.RowType_EditCustomer_Request;
import issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import issuing_v_01_02_xsd.RowType_ReplaceCard;
import issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder;
import issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request;
import issuing_v_01_02_xsd.RowType_CloseAccount_Request;
import issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request;
import issuing_v_01_02_xsd.RowType_EditAgreement_Request;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import IssuingWS.IssuingPortProxy;

import client.NCI.com.ipakyulibank.client_p.BAPIRET2;
import client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerContent;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce;
import client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut;
import client.NCI.com.ipakyulibank.client_p.Customer_ReqestOutProxy;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.accountti.Account;
import com.is.accountti.AccountFilter;
import com.is.accountti.AccountService;
import com.is.accountti.AccountService.actions_for_acc;
import com.is.customer.CustomerService.link;
import com.is.report.DPrint;
import com.is.tieto.AccInfo;
import com.is.tieto.Agreement;
import com.is.tieto.PagingListModel;
import com.is.tieto.Tclient;
import com.is.tieto.TclientFilter;
import com.is.tieto.TclientService;
import com.is.tieto.TietoCardSetting;
import com.is.userti.UserActionsLog;
import com.is.userti.UserService;
import com.is.utilsti.CheckNull;
import com.is.utilsti.RefCBox;
import com.is.utilsti.RefData;
import com.is.utilsti.Res;
import com.is.trpay.TrPay;
import com.is.trpay.TrPayViewCtrl.Cur_cont;
import org.apache.commons.codec.binary.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpGet;

/**
 * @author Sergey
 * 
 */
public class AddCstViewCtrl extends GenericForwardComposer {

	private Window customermain, addCust, addTieto, printwnd, accwnd,
			addCustomer, accounts, add_everywhere, addwnd, tclientmain,
			show_cards, blockwnd, bt_rest_confirmation_wnd;
	public CustomerFilter filter = new CustomerFilter();
	public CustomerFilter bfilter = new CustomerFilter();
	private Paging customerPaging, tclientPaging, bcustomerPaging;
	private com.is.customer.PagingListModel model = null;
	private com.is.customer.PagingListModel bmodel = null;
	private com.is.tieto.PagingListModel tmodel = null;
	private ListModelList lmodel = null;
	// private ListModelList<CashBank> lst = new
	// ListModelList<CashBank>(iCashBankService.getAllCashBank());
	// private ListModelList<RefData> lst = new ListModelList<RefData>();
	private Menupopup editPopup;
	private Menuitem pop_open_cl;
	private Menupopup confirmPopup;
	private Menuitem pop_confirm;
	private Tabbox mainTabbox;
	private Window popolnenieWnd;
	private Div divPopolnenie;
	private Include includeUtverjdenie;
	
	private AnnotateDataBinder binder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private Listbox addCust$dataGrid, addCustomer$dataGrid, bank_customers,
			head_customers, branch_customers, addwnd$accGrid,
			show_cards$accGrid, accounts$accGrid;
	private Textbox addTieto$aclient, addTieto$abank_c, addTieto$aclient_b,
			addTieto$acl_type, addTieto$acln_cat, addTieto$af_names,
			addTieto$asurname, addTieto$atitle, addTieto$am_name,
			addTieto$ar_street, addTieto$ar_city, addTieto$ar_cntry,
			addTieto$ausrid, addTieto$actime, addTieto$astatus,
			addTieto$asearch_name, addTieto$asex, addTieto$aserial_no,
			addTieto$aissued_by, addTieto$astatus_change_date,
			blockwnd$txtstopcauses, fclient;
	private Textbox addCustomer$add_cst_id, addCust$ap_birth_place,
			addCust$aid, addCust$abranch, addCust$aid_client, addCust$aname,
			addCust$acode_subject, addCust$asign_registr, addCust$acode_form,
			addCust$astate, addCust$ap_post_address,
			addCust$ap_passport_serial, addCust$ap_passport_number,
			addCust$ap_passport_place_registration,
			addCust$ap_number_tax_registration,
			addCust$ap_capacity_status_date, addCust$ap_capacity_status_place,
			addCust$ap_num_certif_capacity, addCust$ap_phone_home,
			addCust$ap_phone_mobile, addCust$ap_email_address,
			addCust$ap_pension_sertif_serial, addCust$ap_inps,
			addCust$ap_family, addCust$ap_first_name, addCust$ap_patronymic;
	private RefCBox addCust$acode_resident, addCust$acode_type,
			addCust$acode_country, addCust$ap_passport_type,
			addCust$ap_code_tax_org, addCust$ap_code_bank,
			addCust$ap_code_class_credit, addCust$ap_code_citizenship,
			addCust$ap_code_capacity, addCust$ap_code_gender,
			addCust$ap_code_nation, addCust$ap_code_birth_region,
			addCust$ap_code_birth_distr, addCust$ap_type_document,
			addCust$ap_code_adr_region, addCust$ap_code_adr_distr,
			blockwnd$sstopcauses;
	private Datebox adate_open, adate_close, ap_passport_date_registration,
			ap_passport_date_expiration, ap_birthday, fp_birthday,
			addTieto$arec_date, addTieto$ab_date, addTieto$adoc_since,
			add_everywhere$ap_passport_date_registration,
			add_everywhere$ap_passport_date_expiration,
			add_everywhere$ap_birthday;

	private RefCBox add_everywhere$acode_resident, add_everywhere$acode_type,
			add_everywhere$acode_country, add_everywhere$ap_passport_type,
			add_everywhere$ap_code_tax_org, add_everywhere$ap_code_bank,
			add_everywhere$ap_code_class_credit,
			add_everywhere$ap_code_citizenship,
			add_everywhere$ap_code_capacity, add_everywhere$ap_code_gender,
			add_everywhere$ap_code_nation, add_everywhere$ap_code_birth_region,
			add_everywhere$ap_code_birth_distr,
			add_everywhere$ap_type_document, add_everywhere$ap_code_adr_region,
			add_everywhere$ap_code_adr_distr;

	private Textbox add_everywhere$aclient, add_everywhere$abank_c,
			add_everywhere$aclient_b, add_everywhere$acl_type,
			add_everywhere$acln_cat, add_everywhere$af_names,
			add_everywhere$asurname, add_everywhere$atitle,
			add_everywhere$am_name, add_everywhere$ar_street,
			add_everywhere$ar_city, add_everywhere$ar_cntry,
			add_everywhere$ausrid, add_everywhere$actime,
			add_everywhere$astatus, add_everywhere$asearch_name,
			add_everywhere$asex, add_everywhere$aserial_no,
			add_everywhere$aissued_by, add_everywhere$astatus_change_date;

	private Textbox add_everywhere$ap_birth_place, add_everywhere$aid,
			add_everywhere$abranch, add_everywhere$aid_client,
			add_everywhere$aname, add_everywhere$acode_subject,
			add_everywhere$asign_registr, add_everywhere$acode_form,
			add_everywhere$astate, add_everywhere$ap_post_address,
			add_everywhere$ap_passport_serial,
			add_everywhere$ap_passport_number,
			add_everywhere$ap_passport_place_registration,
			add_everywhere$ap_number_tax_registration,
			add_everywhere$ap_capacity_status_date,
			add_everywhere$ap_capacity_status_place,
			add_everywhere$ap_num_certif_capacity,
			add_everywhere$ap_phone_home, add_everywhere$ap_phone_mobile,
			add_everywhere$ap_email_address,
			add_everywhere$ap_pension_sertif_serial, add_everywhere$ap_inps,
			add_everywhere$ap_family, add_everywhere$ap_first_name,
			add_everywhere$ap_patronymic, add_everywhere$acode_tel,
			add_everywhere$ap_pinfl;

	private Textbox addCustomer$p_birth_place, addCustomer$id,
			addCustomer$branch, addCustomer$id_client, addCustomer$name,
			addCustomer$code_country, addCustomer$code_type,
			addCustomer$code_resident, addCustomer$code_subject,
			addCustomer$sign_registr, addCustomer$code_form,
			addCustomer$date_open, addCustomer$date_close, addCustomer$state,
			addCustomer$p_birthday, addCustomer$p_post_address,
			addCustomer$p_passport_serial, addCustomer$p_passport_number,
			addCustomer$p_passport_place_registration,
			addCustomer$p_passport_date_registration,
			addCustomer$p_number_tax_registration,
			addCustomer$p_capacity_status_date,
			addCustomer$p_capacity_status_place,
			addCustomer$p_num_certif_capacity, addCustomer$p_phone_home,
			addCustomer$p_phone_mobile, addCustomer$p_email_address,
			addCustomer$p_pension_sertif_serial,
			addCustomer$p_passport_date_expiration, addCustomer$p_inps,
			addCustomer$p_family, addCustomer$p_first_name,
			addCustomer$p_patronymic;
	private Textbox addCustomer$ap_birth_place, addCustomer$aid,
			addCustomer$abranch, addCustomer$aid_client, addCustomer$aname,
			addCustomer$acode_subject, addCustomer$asign_registr,
			addCustomer$acode_form, addCustomer$astate,
			addCustomer$ap_post_address, addCustomer$ap_passport_serial,
			addCustomer$ap_passport_number,
			addCustomer$ap_passport_place_registration,
			addCustomer$ap_number_tax_registration,
			addCustomer$ap_capacity_status_date,
			addCustomer$ap_capacity_status_place,
			addCustomer$ap_num_certif_capacity, addCustomer$ap_phone_home,
			addCustomer$ap_phone_mobile, addCustomer$ap_email_address,
			addCustomer$ap_pension_sertif_serial, addCustomer$ap_inps,
			addCustomer$ap_family, addCustomer$ap_first_name,
			addCustomer$ap_patronymic, addCustomer$ap_pinfl;
	private Textbox addCustomer$fid, addCustomer$fbranch,
			addCustomer$fid_client, addCustomer$fname,
			addCustomer$fcode_country, addCustomer$fcode_type,
			addCustomer$fcode_resident, addCustomer$fcode_subject,
			addCustomer$fsign_registr, addCustomer$fcode_form,
			addCustomer$fdate_open, addCustomer$fdate_close,
			addCustomer$fstate, addCustomer$fp_birthday,
			addCustomer$fp_post_address, addCustomer$fp_passport_serial,
			addCustomer$fp_passport_number,
			addCustomer$fp_passport_place_registration,
			addCustomer$fp_passport_date_registration,
			addCustomer$fp_number_tax_registration, addCustomer$fp_birth_place,
			addCustomer$fp_capacity_status_date,
			addCustomer$fp_capacity_status_place,
			addCustomer$fp_num_certif_capacity, addCustomer$fp_phone_home,
			addCustomer$fp_phone_mobile, addCustomer$fp_email_address,
			addCustomer$fp_pension_sertif_serial,
			addCustomer$fp_passport_date_expiration, addCustomer$fp_inps,
			addCustomer$fp_family, addCustomer$fp_first_name,
			addCustomer$fp_patronymic, card;
	private Paging addCustomer$customerPaging;
	private RefCBox addCustomer$p_passport_type, addCustomer$p_code_tax_org,
			addCustomer$p_code_bank, addCustomer$p_code_class_credit,
			addCustomer$p_code_citizenship, addCustomer$p_code_capacity,
			addCustomer$p_code_gender, addCustomer$p_code_nation,
			addCustomer$p_code_birth_region, addCustomer$p_code_birth_distr,
			addCustomer$p_type_document, addCustomer$p_code_adr_region,
			addCustomer$p_code_adr_distr;
	private RefCBox addCustomer$acode_resident, addCustomer$acode_type,
			addCustomer$acode_country, addCustomer$ap_passport_type,
			addCustomer$ap_code_tax_org, addCustomer$ap_code_bank,
			addCustomer$ap_code_class_credit, addCustomer$ap_code_citizenship,
			addCustomer$ap_code_capacity, addCustomer$ap_code_gender,
			addCustomer$ap_code_nation, addCustomer$ap_code_birth_region,
			addCustomer$ap_code_birth_distr, addCustomer$ap_type_document,
			addCustomer$ap_code_adr_region, addCustomer$ap_code_adr_distr;
	private RefCBox addCustomer$fp_passport_type, addCustomer$fp_code_tax_org,
			addCustomer$fp_code_bank, addCustomer$fp_code_class_credit,
			addCustomer$fp_code_citizenship, addCustomer$fp_code_capacity,
			addCustomer$fp_code_gender, addCustomer$fp_code_nation,
			addCustomer$fp_code_birth_region, addCustomer$fp_code_birth_distr,
			addCustomer$fp_type_document, addCustomer$fp_code_adr_region,
			addCustomer$fp_code_adr_distr;
	private Datebox addCustomer$adate_open, addCustomer$adate_close,
			addCustomer$ap_passport_date_registration,
			addCustomer$ap_passport_date_expiration, addCustomer$ap_birthday;

	private Toolbarbutton bth = null;
	private Toolbarbutton btb = null;
	private Toolbarbutton btt = null;
	private Toolbarbutton btbreak = null;
	private Toolbarbutton btedit = null;
	private Toolbarbutton btacc = null;
	private Toolbarbutton btblock_card = null;
	private Toolbarbutton btunblock_card = null;
	private Toolbarbutton btrefresh_card = null;
	private Toolbarbutton bt_acc_act = null;
	private Toolbarbutton bt_copy_for_ho = null;
	private Toolbarbutton addwnd$btn_add, show_cards$btn_add;

	private RefCBox addwnd$sproduct;

	public TclientFilter tfilter = new TclientFilter();
	private Customer current = new Customer();
	private Customer bcustomer = new Customer();
	private Customer tcustomer = new Customer();
	private Tclient tietocl = new Tclient();
	private TietoCustomer tcust = new TietoCustomer();
	private TietoCustomer tmpTCust;
	private Tclient atcust = new Tclient();
	private int _pageSize = 6;
	private int _startPageNumber = 0;
	private int _starttPageNumber = 0;
	private int _startbPageNumber = 0;
	private int _totalSize = 0;
	private int _ttotalSize = 0;
	private int _btotalSize = 0;
	private Div step1, step2, step3, step4;
	private Grid addCust$addgrdl, addCust$addgrdr, addTieto$addtgrdl,
			addTieto$addtgrdr, addCustomer$addgrdl, addCustomer$addgrdr,
			add_everywhere$addgrdl, add_everywhere$addgrdr;
	private String un, pwd, branch, alias;
	private Iframe printwnd$rpframe;
	private RefCBox accwnd$scurracc;
	private Toolbarbutton accwnd$btn_print, btn_tieto_search,
			btrefresh_card_app, bt_block_card_acc, bt_unblock_card_acc,
			bt_close_card_acc;
	private Toolbarbutton addCustomer$add_to_link, accounts$btn_add;
	private String cur_branch;
	private String cur_tieto_id;
	private int add_to_ho;
	private String ho_id;
	private static HashMap<String, String> _tstopCauses = null;
	private static List<RefData> nationMapList = null;
	private AccInfo accinfo = new AccInfo();
	private Customer cur_HO_customer, cur_branch_customer;
	private boolean is_ti = false, is_ho = false, is_br = false,
			is_acc = false;
	private boolean add_ti = false, add_ho = false, add_br = false;
	private boolean edit_ti = false, edit_ho = false, edit_br = false,
			fl_edit = false;
	private boolean fl_filter_card_set = false, edit_agree = false;
	private String cur_cl_acc, new_card_acc;
	private String curip, contract_nmb;
	private int uid;
	private AccInfo cur_card;
	private static HashMap<Integer, Integer> used_ids;
	private int agre_nom_upd;
	private AccInfo cur_acc_info;
	private String cur_contract;
	private Confirmation_rep_data cfrd;
	// private int open_in_ho;
	// private String open_in_branch;
	IssuingPortProxy pp;
	private String code_distr = "", localPort="";

	public String getCur_cl_acc() {
		return cur_cl_acc;
	}

	public void setCur_cl_acc(String cur_cl_acc) {
		this.cur_cl_acc = cur_cl_acc;
	}

	public AccInfo getAccinfo() {
		return accinfo;
	}

	public void setAccinfo(AccInfo accinfo) {
		this.accinfo = accinfo;
	}

	public Customer getCur_HO_customer() {
		return cur_HO_customer;
	}

	public void setCur_HO_customer(Customer cur_HO_customer) {
		this.cur_HO_customer = cur_HO_customer;
	}

	public Customer getCur_branch_customer() {
		return cur_branch_customer;
	}

	public void setCur_branch_customer(Customer cur_branch_customer) {
		this.cur_branch_customer = cur_branch_customer;
	}

	/**
	 *
	 * 
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub

		/*
		 * editPopup.addEventListener(Events.ON_OPEN, new EventListener() {
		 * 
		 * @Override public void onEvent(OpenEvent event) throws Exception {
		 * event.getReference(); editPopup.setAttribute("branch",
		 * (String)(event.getReference().getAttribute("branch"));
		 * editPopup.setAttribute("client_id",
		 * (String)event.getTarget().getAttribute("client_id"));
		 * editPopup.setAttribute("alias",
		 * (String)event.getTarget().getAttribute("alias")); }
		 * 
		 * });
		 */
		// open_with_new_branch = 0;

		binder = new AnnotateDataBinder(comp);
		self.setAttribute("binder", binder);
		// binder.bindBean("current", this.current);
		// binder.bindBean("atcust", this.atcust);
		binder.bindBean("tietocl", this.tietocl);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = 6;// _pageSize = Integer.parseInt( parameter[0])/36-5;
			// dataGrid.setRows(Integer.parseInt( parameter[0])/36-5);
		}

		parameter = (String[]) param.get("current_client_id");
		if (parameter != null) {
			System.out.println("current_client_id=" + parameter[0]);
			//filter.setCustomer(parameter[0]);
			tfilter.setClient_b(parameter[0]);
			fclient.setValue(parameter[0]);
		}

		parameter = (String[]) param.get("branch");
		if (parameter != null) {
			System.out.println("branch=" + parameter[0]);
		}
		
		
		uid = (Integer) session.getAttribute("uid");
		curip = (String) session.getAttribute("curip");
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		// uid= (Integer) session.getAttribute("uid");
		branch = (String) session.getAttribute("branch");
		tcust.setBranch(branch);
		atcust.setM_name("test");
		contract_nmb = null;
		
		Execution exec = Executions.getCurrent();
		localPort=""+exec.getLocalPort();

		_tstopCauses = com.is.utilsti.RefDataService.getHTstopCauses();
		// System.out.println("branch "+ConnectionPool.getValue("HO")+"  "+branch);

		add_everywhere$ap_type_document.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getType_document(alias))));
		// ap_passport_type.setModel((new
		// ListModelList(com.is.utilsti.RefDataService.getType_document())));
		add_everywhere$ap_code_tax_org.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getTax(alias))));
		// add_everywhere$ap_code_bank.setModel((new ListModelList(
		// com.is.utilsti.RefDataService.getMfo(alias))));
		// ap_code_class_credit.setModel((new
		// ListModelList(com.is.utilsti.RefDataService.getClassCR())));
		add_everywhere$ap_code_citizenship.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getCountry(alias))));
		add_everywhere$acode_country.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getCountry(alias))));
		// ap_birth_place.setModel((new
		// ListModelList(com.is.utilsti.RefDataService.getDistr())));
		// ap_code_capacity.setModel((new
		// ListModelList(com.is.utilsti.RefDataService.getCapacity())));
		add_everywhere$ap_code_gender.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getGender(alias))));
		add_everywhere$ap_code_nation.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getNation(alias))));
		add_everywhere$ap_code_birth_region.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getRegion(alias))));
		add_everywhere$ap_code_birth_distr.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getDistr(alias))));
		// ap_type_document.setModel((new
		// ListModelList(com.is.utilsti.RefDataService.getType_document())));
		add_everywhere$ap_code_adr_region.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getRegion(alias))));
		add_everywhere$ap_code_adr_distr.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getDistr(alias))));
		// add_everywhere$ap_code_adr_distr.setModel(lst);
		// acode_type.setModel((new
		// ListModelList(com.is.utilsti.RefDataService.getType_client())));
		add_everywhere$acode_resident.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getRezCl(alias))));
		addwnd$sproduct.setModel((new ListModelList(com.is.utilsti.RefDataService
				.getOfrProd(alias, "P", branch, uid))));

		// used_ids
		accounts$accGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Account pAccInfo = (Account) data;

				List<actions_for_acc> act = AccountService.getactions_for_acc(
						pAccInfo.getState(), alias);

				Listcell h_edit_cell = new Listcell();

				for (int i = 0; i < act.size(); i++) {
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel(act.get(i).getName());
					bt_acc_act.setAttribute("deal_group_id", act.get(i)
							.getDeal_group());
					bt_acc_act.setAttribute("deal_id", act.get(i).getDeal_id());
					bt_acc_act.setAttribute("action_id", act.get(i)
							.getAction_id());
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setAttribute("capt", act.get(i).getName());
					bt_acc_act.setImage("/images/down3.png");
					bt_acc_act.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									String halias = CustomerService
											.get_alias_ho(alias);
									/*
									 * Res res =
									 * AccountService.doAction_acc_ho(un, pwd,
									 * (Integer)event.getTarget().getAttribute(
									 * "deal_group_id"),
									 * (Integer)event.getTarget
									 * ().getAttribute("deal_id"),
									 * (Account)event
									 * .getTarget().getAttribute("acc"),
									 * (Integer
									 * )event.getTarget().getAttribute("action_id"
									 * ), halias,
									 * branch.compareTo(ConnectionPool
									 * .getValue("HO", alias))==0);
									 */
									Res res = AccountService.doAction_acc_ho(
											un,
											pwd,
											(Integer) event.getTarget()
													.getAttribute(
															"deal_group_id"),
											(Integer) event.getTarget()
													.getAttribute("deal_id"),
											(Account) event.getTarget()
													.getAttribute("acc"),
											(Integer) event.getTarget()
													.getAttribute("action_id"),
											alias, true);

									if (res.getCode() != 0) {
										alert(res.getName());
										return;
									}

									UserService
											.UsrLog(new UserActionsLog(
													uid,
													un,
													curip,
													7,
													1,
													"Выполнено действие ["
															+ (String) event
																	.getTarget()
																	.getAttribute(
																			"capt")
															+ "] для счета ["
															+ ((Account) event
																	.getTarget()
																	.getAttribute(
																			"acc"))
																	.getId()
															+ "]", branch));

									AccountFilter acfilter = new AccountFilter();
									acfilter.setClient(cur_branch_customer
											.getId_client());
									acfilter.setAcc_bal("20206");
									acfilter.setBranch(branch);
									acfilter.setCurrency("840");
									com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
											0, 100, acfilter, alias);
									accounts$accGrid
											.setModel((ListModel) acc_model);
								}

							});
					if ((act.get(i).getAction_id() == 1)
							|| (act.get(i).getAction_id() == 2)
							|| (act.get(i).getAction_id() == 20))
						h_edit_cell.appendChild(bt_acc_act);
				}

				Listcell h_add_acc_cell = new Listcell();
				if (pAccInfo.getState() == 2) {
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel("Сохранить");
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setImage("/images/save.png");
					bt_acc_act.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									String halias = CustomerService
											.get_alias_ho(alias);
									// CustomerService.update_lnk_set_acc("00444",
									// cur_HO_customer.getId_client()
									// ,((Account)event.getTarget().getAttribute("acc")).getId(),
									// halias);
									CustomerService.update_lnk_set_acc(un, pwd,
											branch, cur_HO_customer
													.getId_client(),
											((Account) event.getTarget()
													.getAttribute("acc"))
													.getId(), alias);

									// String halias =
									// CustomerService.get_alias_ho(alias);
									String cl_n = "";
									Customer lg_c = CustomerService
											.getCustomerById(cur_HO_customer
													.getId_client(),
													TclientService.getV_HO(),
													halias);
									if (lg_c.getName() != null) {
										cl_n = lg_c.getName() + " "
												+ lg_c.getP_birthday();
									}

									UserService.UsrLog(new UserActionsLog(
											uid,
											un,
											curip,
											7,
											1,
											"Обновлена связка клиента ["
													+ cl_n
													+ "] с id в ГО ["
													+ cur_HO_customer
															.getId_client()
													+ "] филиал ["
													+ branch
													+ "] установлен счет ["
													+ ((Account) event
															.getTarget()
															.getAttribute("acc"))
															.getId() + "]",
											branch));
									refreshModel(_startPageNumber);
									accounts.setVisible(false);
								}

							});

					h_add_acc_cell.appendChild(bt_acc_act);
				}

				row.setValue(pAccInfo);
				row.appendChild(new Listcell(pAccInfo.getBranch()));
				row.appendChild(new Listcell(pAccInfo.getClient()));
				row.appendChild(new Listcell(pAccInfo.getId()));
				row.appendChild(new Listcell(pAccInfo.getName()));
				row.appendChild(new Listcell(pAccInfo.getCurrency()));
				row.appendChild(new Listcell(df.format(pAccInfo.getDate_open())));
				row.appendChild(new Listcell(pAccInfo.getId_order()));
				row.appendChild(new Listcell(AccountService
						.get_account_state_caption(pAccInfo.getState(), alias)));
				// row.appendChild(new
				// Listcell(Integer.toString(pAccInfo.getState())));
				row.appendChild(h_edit_cell);
				row.appendChild(h_add_acc_cell);
			}
		});

		show_cards$accGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				AccInfo pAccInfo = (AccInfo) data;
				Listcell bl_unbl = new Listcell();
				Listcell acc_act = new Listcell();
				row.setValue(pAccInfo);
				Toolbarbutton bt_refresh_with_new = new Toolbarbutton();
				Toolbarbutton bt_rest_confirmation = new Toolbarbutton();
				btunblock_card = new Toolbarbutton();
				btblock_card = new Toolbarbutton();
				btrefresh_card = new Toolbarbutton();
				btrefresh_card_app = new Toolbarbutton();
				bt_block_card_acc = new Toolbarbutton();
				bt_close_card_acc = new Toolbarbutton();
				bt_unblock_card_acc = new Toolbarbutton();

				btunblock_card.setWidth("100%");
				btblock_card.setWidth("100%");
				btrefresh_card.setWidth("100%");
				btrefresh_card_app.setWidth("100%");
				bt_block_card_acc.setWidth("100%");
				bt_close_card_acc.setWidth("100%");
				bt_unblock_card_acc.setWidth("100%");
				bt_refresh_with_new.setWidth("100%");
				bt_rest_confirmation.setWidth("100%");

				btunblock_card.setImage("/images/+.png");
				btblock_card.setImage("/images/-.png");
				btunblock_card.setLabel("Разблокировать карту");
				btblock_card.setLabel("Блокировать карту");
				bt_rest_confirmation.setLabel("Документы о счете");
				bt_block_card_acc.setLabel("Перевести в неактивный");
				bt_block_card_acc.setImage("/images/+.png");
				bt_close_card_acc.setImage("/images/+.png");
				bt_close_card_acc.setLabel("Закрыть");
				bt_unblock_card_acc.setLabel("Открыть");
				bt_unblock_card_acc.setImage("/images/+.png");

				btrefresh_card.setImage("/images/front1.png");
				btrefresh_card.setLabel("Перевыпустить карту");
				btrefresh_card_app.setImage("/images/file.png");
				bt_rest_confirmation.setImage("/images/file.png");
				btrefresh_card_app.setLabel("Заявление на перевыпуск карты");
				bt_refresh_with_new.setImage("/images/front1.png");
				bt_refresh_with_new
						.setLabel("Перевыпустить карту с новыми данными");

				bt_rest_confirmation.setAttribute("acc", pAccInfo);
				bt_rest_confirmation.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								cfrd = new Confirmation_rep_data();
								cfrd.acc = (AccInfo) event.getTarget()
										.getAttribute("acc");
								bt_rest_confirmation_wnd.setVisible(true);
							}

						});

				btunblock_card.setAttribute("acc", pAccInfo);
				btblock_card.setAttribute("acc", pAccInfo);
				btrefresh_card.setAttribute("acc", pAccInfo);
				btrefresh_card_app.setAttribute("acc", pAccInfo);
				bt_block_card_acc.setAttribute("acc", pAccInfo);
				bt_close_card_acc.setAttribute("acc", pAccInfo);
				bt_unblock_card_acc.setAttribute("acc", pAccInfo);
				bt_refresh_with_new.setAttribute("acc", pAccInfo);
				btblock_card.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								cur_card = (AccInfo) event.getTarget()
										.getAttribute("acc");
								blockwnd$sstopcauses
										.setModel(new ListModelList(
												com.is.utilsti.RefDataService
														.getTstopCauses()));
								blockwnd.setVisible(true);
							}

						});

				btrefresh_card_app.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								if (tietocl == null) {
									alert("Клиент tieto не выбран");
									return;
								}

								HashMap<String, Object> params = new HashMap<String, Object>();
								params.put("CARD_NUM", ((AccInfo) event
										.getTarget().getAttribute("acc"))
										.getCard());
								// TietoCardSetting ctcs =
								// TclientService.getTietoCardSetting(((AccInfo)event.getTarget().getAttribute("acc")).getCard(),
								// alias);
								params.put("CARD_PROD_NAME", ((AccInfo) event
										.getTarget().getAttribute("acc"))
										.getProduct_name());
								if (((((AccInfo) event.getTarget()
										.getAttribute("acc")).getCard_type())
										.compareTo("005") == 0)
										|| ((((AccInfo) event.getTarget()
												.getAttribute("acc"))
												.getCard_type())
												.compareTo("009") == 0))
									fill_cl_report(tietocl,
											"TI_APPL_VISA_EXCHANGE_REPLACE",
											params);
								else
									fill_cl_report(tietocl,
											"TI_APPL_VISA_CAP_REPLACE", params);
							}
						});

				bt_refresh_with_new.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								if (tietocl == null) {
									alert("Клиент tieto не выбран");
									return;
								}

								Tclient ntc;
								ntc = tietocl;

								TietoCardSetting tcs = TclientService.getTietoCardSetting(
										Integer.parseInt(((AccInfo) event
												.getTarget()
												.getAttribute("acc"))
												.getCard_type()), alias);
								// 13.10.1017
								// Res res =
								// com.is.tieto.TclientService.check_card(((AccInfo)event.getTarget().getAttribute("acc")).getCard_type(),TclientService.getAccInfo_active(ntc.getClient()),
								// alias);
								// if(res.getCode() != 0)
								// {
								// alert(res.getName());
								// return;
								// }

								contract_nmb = null;
								agre_nom_upd = ((AccInfo) event.getTarget()
										.getAttribute("acc"))
										.getAgreement_key();
								String RT = null;
								edit_agree = true;
								// cur_contract =
								// ((AccInfo)event.getTarget().getAttribute("acc")).getContract();
								cur_acc_info = (AccInfo) event.getTarget()
										.getAttribute("acc");
								contract_nmb = ((AccInfo) event.getTarget()
										.getAttribute("acc")).getContract();
								open_card(((AccInfo) event.getTarget()
										.getAttribute("acc")).getCard_type(),
										((AccInfo) event.getTarget()
												.getAttribute("acc"))
												.getCard_acct(), true,
										((AccInfo) event.getTarget()
												.getAttribute("acc"))
												.getTranz_acct());
								contract_nmb = null;

							}
						});

				bt_block_card_acc.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {

								OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
								OperationResponseInfo orInfo = null;
								// RowType_RemoveCardFromStop_Request parameters
								// = new RowType_RemoveCardFromStop_Request();

								OperationResponseInfo ori = new OperationResponseInfo();
								OperationResponseInfoHolder orih = new OperationResponseInfoHolder(
										ori);
								// hjk
								RowType_DormantAccountByCard_Request dac = new RowType_DormantAccountByCard_Request();

								// RowType_ReplaceCard parameters = new
								// RowType_ReplaceCard();

								try {
									connectionInfo.setBANK_C(TclientService.getV_bankc());
									connectionInfo.setGROUPC(TclientService.getV_groupc());

									dac.setCARD(((AccInfo) event.getTarget()
											.getAttribute("acc")).getCard());
									dac.setDORMANT_MODE("0");
									dac.setBANK_C(TclientService.getV_bankc());
									dac.setGROUPC(TclientService.getV_groupc());
									dac.setTEXT("Dormant by the TYETO Ipak Yuli user");
									// hjk
									OperationResponseInfo resp = pp
											.dormantAccountByCard(
													connectionInfo, dac);

									if (resp.getResponse_code().intValue() != 0) {
										alert(resp.getError_description());
										return;
									}

									alert("Статус счета изменен на неактивный");
									UserService
											.UsrLog(new UserActionsLog(
													uid,
													un,
													curip,
													7,
													1,
													"Карта No ["
															+ ((AccInfo) event
																	.getTarget()
																	.getAttribute(
																			"acc"))
																	.getCard()
															+ "] dormant",
													branch));

								} catch (Exception e) {
									LtLogger.getLogger().error(
											CheckNull.getPstr(e));
									alert(e.getMessage());
									e.printStackTrace();
								} finally {
									tcust.setTieto_customer_id(tietocl
											.getClient());
									tmpTCust = CustomerService
											.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(
											TclientService.getAccInfo(tietocl
													.getClient()), false));
								}
							}

						});

				bt_unblock_card_acc.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {

								OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
								OperationResponseInfo orInfo = null;
								// RowType_RemoveCardFromStop_Request parameters
								// = new RowType_RemoveCardFromStop_Request();

								OperationResponseInfo ori = new OperationResponseInfo();
								OperationResponseInfoHolder orih = new OperationResponseInfoHolder(
										ori);
								// hjk
								RowType_ActivateAccountByCard_Request dac = new RowType_ActivateAccountByCard_Request();

								// RowType_ReplaceCard parameters = new
								// RowType_ReplaceCard();

								try {
									connectionInfo.setBANK_C(TclientService.getV_bankc());
									connectionInfo.setGROUPC(TclientService.getV_groupc());

									dac.setCARD(((AccInfo) event.getTarget()
											.getAttribute("acc")).getCard());
									// dac.setDORMANT_MODE("0");
									dac.setBANK_C(TclientService.getV_bankc());
									dac.setGROUPC(TclientService.getV_groupc());
									// hjk
									OperationResponseInfo resp = pp
											.activateAccountByCard(
													connectionInfo, dac);

									if (resp.getResponse_code().intValue() != 0) {
										alert(resp.getError_description());
										return;
									}

									alert("Счет активирован");
									UserService
											.UsrLog(new UserActionsLog(
													uid,
													un,
													curip,
													7,
													1,
													"Карта No ["
															+ ((AccInfo) event
																	.getTarget()
																	.getAttribute(
																			"acc"))
																	.getCard()
															+ "] dormant",
													branch));

								} catch (Exception e) {
									LtLogger.getLogger().error(
											CheckNull.getPstr(e));
									alert(e.getMessage());
									e.printStackTrace();
								} finally {
									tcust.setTieto_customer_id(tietocl
											.getClient());
									tmpTCust = CustomerService
											.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(
											TclientService.getAccInfo(tietocl
													.getClient()), false));
								}
							}

						});

				bt_close_card_acc.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {

								OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
								OperationResponseInfo orInfo = null;
								// RowType_RemoveCardFromStop_Request parameters
								// = new RowType_RemoveCardFromStop_Request();

								OperationResponseInfo ori = new OperationResponseInfo();
								OperationResponseInfoHolder orih = new OperationResponseInfoHolder(
										ori);
								// hjk
								RowType_CloseAccount_Request dac = new RowType_CloseAccount_Request();

								// RowType_ReplaceCard parameters = new
								// RowType_ReplaceCard();

								try {
									connectionInfo.setBANK_C(TclientService.getV_bankc());
									connectionInfo.setGROUPC(TclientService.getV_groupc());

									dac.setCARD_ACCT(((AccInfo) event
											.getTarget().getAttribute("acc"))
											.getCard_acct());
									dac.setCCY("USD");
									dac.setCOMMENT("Closed by the TYETO Ipak Yuli user");

									OperationResponseInfo resp = pp
											.closeAccount(connectionInfo, dac);

									if (resp.getResponse_code().intValue() != 0) {
										alert(resp.getError_description());
										return;
									}
									alert("Счет закрыт");
									UserService
											.UsrLog(new UserActionsLog(
													uid,
													un,
													curip,
													7,
													1,
													"Карта No ["
															+ ((AccInfo) event
																	.getTarget()
																	.getAttribute(
																			"acc"))
																	.getCard()
															+ "] dormant",
													branch));

								} catch (Exception e) {
									LtLogger.getLogger().error(
											CheckNull.getPstr(e));
									alert(e.getMessage());
									e.printStackTrace();
								} finally {
									tcust.setTieto_customer_id(tietocl
											.getClient());
									tmpTCust = CustomerService
											.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(
											TclientService.getAccInfo(tietocl
													.getClient()), false));
								}
							}

						});

				btrefresh_card.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {

								OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
								OperationResponseInfo orInfo = null;
								// RowType_RemoveCardFromStop_Request parameters
								// = new RowType_RemoveCardFromStop_Request();

								RowType_EditAgreement_Request rtea = new RowType_EditAgreement_Request();
								// rtea.setBRANCH("001");
								Agreement agr = TclientService
										.getCardAgreement(((AccInfo) event
												.getTarget()
												.getAttribute("acc"))
												.getAgreement_key());

								rtea.setAGRE_NOM(BigDecimal.valueOf(agr
										.getAGRE_NOM()));
								rtea.setB_BR_ID(BigDecimal.valueOf(agr
										.getB_BR_ID()));

								// jkljlkkl
								rtea.setBANK_CODE(agr.getBANK_CODE());
								rtea.setBINCOD(agr.getBINCOD());

								Res res = CustomerService.getTieto_branch(
										branch, uid, alias);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}
								rtea.setBRANCH(res.getName());

								rtea.setCITY(agr.getCITY());
								rtea.setCLIENT(agr.getCLIENT());
								rtea.setCOMENT(agr.getCOMENT());
								rtea.setCONTRACT(agr.getCONTRACT());
								rtea.setCOUNTRY(agr.getCOUNTRY());
								Calendar cal1 = Calendar.getInstance();
								cal1.setTime(agr.getCTIME());
								rtea.setCTIME(cal1);
								rtea.setDISTRIB_MODE(agr.getDISTRIB_MODE());
								rtea.setE_MAILS(agr.getE_MAILS());
								Calendar cal2 = Calendar.getInstance();
								cal2.setTime(agr.getENROLLED());
								rtea.setENROLLED(cal2);
								rtea.setIN_FILE_NUM(BigDecimal.valueOf(agr
										.getIN_FILE_NUM()));
								rtea.setISURANCE_TYPE(agr.getISURANCE_TYPE());
								// rtea.setOFFICE(agr.getOFFICE());
								// rtea.setOFFICE_ID(BigDecimal.valueOf(agr.getOFFICE_ID()));
								rtea.setPOST_IND(agr.getPOST_IND());
								rtea.setPRODUCT(agr.getPRODUCT());
								rtea.setREP_LANG(agr.getREP_LANG());
								rtea.setRISK_LEVEL(agr.getRISK_LEVEL());
								rtea.setSTATUS(agr.getSTATUS());
								rtea.setSTREET(agr.getSTREET());
								rtea.setU_COD4(agr.getU_COD4());
								rtea.setU_CODE5(agr.getU_CODE5());
								rtea.setU_CODE6(agr.getU_CODE6());
								rtea.setU_FIELD3(agr.getU_FIELD3());
								rtea.setU_FIELD4(agr.getU_FIELD4());
								rtea.setUSRID(agr.getUSRID());

								OperationResponseInfo ori = new OperationResponseInfo();
								try {
									connectionInfo.setBANK_C(TclientService.getV_bankc());
									connectionInfo.setGROUPC(TclientService.getV_groupc());

									pp.editAgreement(connectionInfo, rtea);
								} catch (Exception e) {
									LtLogger.getLogger().error(
											CheckNull.getPstr(e));
									alert(e.getMessage());
									e.printStackTrace();
								} finally {
									tcust.setTieto_customer_id(tietocl
											.getClient());
									tmpTCust = CustomerService
											.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(
											TclientService.getAccInfo(tietocl
													.getClient()), false));
								}

								// OperationResponseInfo ori = new
								// OperationResponseInfo();
								OperationResponseInfoHolder orih = new OperationResponseInfoHolder(
										ori);
								RowType_ReplaceCard parameters = new RowType_ReplaceCard();

								try {
									connectionInfo.setBANK_C(TclientService.getV_bankc());
									connectionInfo.setGROUPC(TclientService.getV_groupc());

									parameters.setCARD(((AccInfo) event
											.getTarget().getAttribute("acc"))
											.getCard());

									RowType_ReplaceCardHolder rrch = new RowType_ReplaceCardHolder(
											parameters);

									pp.replaceCard(connectionInfo, parameters,
											orih, rrch);
									if (orih.value.getResponse_code()
											.intValue() != 0) {
										throw new Exception(orih.value
												.getError_description());
									}
									alert("Карта перевыпущена");
									UserService
											.UsrLog(new UserActionsLog(
													uid,
													un,
													curip,
													7,
													1,
													"Карта No ["
															+ ((AccInfo) event
																	.getTarget()
																	.getAttribute(
																			"acc"))
																	.getCard()
															+ "] перевыпущена",
													branch));

								} catch (Exception e) {
									LtLogger.getLogger().error(
											CheckNull.getPstr(e));
									alert(e.getMessage());
									e.printStackTrace();
								} finally {
									tcust.setTieto_customer_id(tietocl
											.getClient());
									tmpTCust = CustomerService
											.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(
											TclientService.getAccInfo(tietocl
													.getClient()), false));
								}

							}

						});

				btunblock_card.addEventListener(Events.ON_CLICK,
						new EventListener() {
							@Override
							public void onEvent(Event event) throws Exception {
								OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
								OperationResponseInfo orInfo = null;
								RowType_RemoveCardFromStop_Request parameters = new RowType_RemoveCardFromStop_Request();

								try {
									connectionInfo.setBANK_C(TclientService.getV_bankc());
									connectionInfo.setGROUPC(TclientService.getV_groupc());

									parameters.setBANK_C(TclientService.getV_bankc());
									parameters.setGROUPC(TclientService.getV_groupc());
									parameters.setTEXT("Card UnBlocked");
									parameters.setCARD(((AccInfo) event
											.getTarget().getAttribute("acc"))
											.getCard());

									orInfo = pp.removeCardFromStop(
											connectionInfo, parameters);
									if (orInfo.getResponse_code().intValue() != 0) {
										alert(orInfo.getError_action() + "\r\n"
												+ orInfo.getError_description());
									}
									UserService
											.UsrLog(new UserActionsLog(
													uid,
													un,
													curip,
													7,
													1,
													"Карта No ["
															+ ((AccInfo) event
																	.getTarget()
																	.getAttribute(
																			"acc"))
																	.getCard()
															+ "] разблокирована",
													branch));
								} catch (Exception e) {
									LtLogger.getLogger().error(
											CheckNull.getPstr(e));
									alert(e.getMessage());
									e.printStackTrace();
								} finally {
									tcust.setTieto_customer_id(tietocl
											.getClient());
									tmpTCust = CustomerService
											.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(
											TclientService.getAccInfo(tietocl
													.getClient()), false));
								}
							}

						});

				if (pAccInfo.getStatus1().equals("0")) {
					bl_unbl.appendChild(btblock_card);
					bl_unbl.appendChild(btrefresh_card_app);
				} else {
					// Res res =
					// com.is.tieto.TclientService.check_card(pAccInfo.getProduct(),TclientService.getAccInfo_active(pAccInfo.getClient()),
					// alias);
					// if(res.getCode() != 0)
					// {
					// alert(res.getName());
					// return;
					// }
					// else
					{
						bl_unbl.appendChild(btunblock_card);
						bl_unbl.appendChild(btrefresh_card);
						bl_unbl.appendChild(btrefresh_card_app);
						bl_unbl.appendChild(bt_refresh_with_new);
					}
				}

				if (pAccInfo.getAc_status().compareTo("0") == 0)
					acc_act.appendChild(bt_block_card_acc);
				if (pAccInfo.getAc_status().compareTo("3") == 0)
					acc_act.appendChild(bt_close_card_acc);
				if (pAccInfo.getAc_status().compareTo("3") == 0)
					acc_act.appendChild(bt_unblock_card_acc);
				if (pAccInfo.getAc_status().compareTo("4") == 0)
					acc_act.appendChild(bt_unblock_card_acc);

				String state = "";
				if (pAccInfo.getAc_status().compareTo("0") == 0)
					state = "Активен";
				if (pAccInfo.getAc_status().compareTo("3") == 0)
					state = "Не активен";
				if (pAccInfo.getAc_status().compareTo("4") == 0)
					state = "Закрыт";

				row.appendChild(new Listcell(pAccInfo.getCard()));
				row.appendChild(new Listcell(df.format(pAccInfo
						.getAb_expirity())));

				Listcell lc = new Listcell();
				Label lb = new Label(pAccInfo.getTranz_acct());
				lc.appendChild(lb);
				lc.appendChild(bt_rest_confirmation);

				row.appendChild(lc);
				row.appendChild(new Listcell(_tstopCauses.get(pAccInfo
						.getStop_cause())));
				row.appendChild(new Listcell(pAccInfo.getProduct_name()));
				// row.appendChild(new Listcell(pAccInfo.getProduct()));
				if (pAccInfo.getStatus1().compareTo("0") == 0)
					row.setStyle("background-color:#D2F7CA; font-weight:bold;");

				row.appendChild(bl_unbl);
				row.appendChild(new Listcell(pAccInfo.getCard_acct()));
				row.appendChild(new Listcell(state));
				row.appendChild(acc_act);
			}
		});

		addwnd$accGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Account pAccInfo = (Account) data;

				/*
				 * if (!CheckNull.isEmpty(addwnd$sproduct.getValue())) if
				 * ((addwnd$sproduct.getValue().compareTo("009") !=
				 * 0)&&(addwnd$sproduct.getValue().compareTo("005") != 0)) if
				 * (pAccInfo.getId_order().compareTo("901") == 0) return;
				 */

				List<actions_for_acc> act = AccountService.getactions_for_acc(
						pAccInfo.getState(), alias);

				Listcell h_edit_cell = new Listcell();

				for (int i = 0; i < act.size(); i++) {
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel(act.get(i).getName());
					bt_acc_act.setAttribute("deal_group_id", act.get(i)
							.getDeal_group());
					bt_acc_act.setAttribute("deal_id", act.get(i).getDeal_id());
					bt_acc_act.setAttribute("action_id", act.get(i)
							.getAction_id());
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setImage("/images/down3.png");
					bt_acc_act.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									AccountService.doAction_acc(
											un,
											pwd,
											(Integer) event.getTarget()
													.getAttribute(
															"deal_group_id"),
											(Integer) event.getTarget()
													.getAttribute("deal_id"),
											(Account) event.getTarget()
													.getAttribute("acc"),
											(Integer) event.getTarget()
													.getAttribute("action_id"),
											alias, true);

									AccountFilter acfilter = new AccountFilter();
									acfilter.setClient(cur_branch_customer
											.getId_client());
									acfilter.setAcc_bal(cur_branch_customer
											.getCode_subject().equals("J") ? "22619"
											: "22618");
									acfilter.setBranch(branch);
									// if
									// (addwnd$sproduct.getValue().equals("020"))
									// {
									// alert(addwnd$sproduct.getValue()+
									// " +++++++++++");
									// acfilter.setCurrency("000");
									// }
									// else {
									//
									acfilter.setCurrency("840");

									// }

									com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
											0, 100, acfilter, alias);
									addwnd$accGrid
											.setModel((ListModel) acc_model);
								}

							});

					// if (pAccInfo.getId_order().compareTo("901") == 0)
					if ((act.get(i).getAction_id() == 1)
							|| (act.get(i).getAction_id() == 2)
							|| (act.get(i).getAction_id() == 20))
						h_edit_cell.appendChild(bt_acc_act);
				}

				Listcell h_add_acc_cell = new Listcell();
				if (pAccInfo.getState() == 2) {
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel("Открыть карту");
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setImage("/images/credit_card1.png");
					bt_acc_act.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									Res res;
									if (CheckNull.isEmpty(addwnd$sproduct
											.getValue())) {
										alert("Продукт не выбран");
										return;
									}
									
									//--проверка срок паспорта
									String client_id = ((Account) event
											.getTarget().getAttribute("acc"))
											.getClient();
									
									Customer cst = CustomerService.getCustomerById(client_id,
											TclientService.getV_HO(), CustomerService.get_alias_ho(alias));
									if (cst.getP_passport_date_expiration() != null ) {
										//Date currDate = new Date();
										
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
										Date dateWithoutTime = sdf.parse(sdf.format(new Date()));										
										
										//if (date1.after(date2)) {
								        //    System.out.println("Date1 is after Date2");
										if (dateWithoutTime.after(cst.getP_passport_date_expiration())) {
  										    alert ("Истек срок действия документа(паспорта). " + cst.getP_passport_date_expiration());
											return;
										}
									}
                                    //----
									
									String new_card_acc = ((Account) event
											.getTarget().getAttribute("acc"))
											.getId();
									BigDecimal agre_nom = null;
									String RT = null;
									if (addwnd$sproduct.getValue().compareTo("004") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("004"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"004",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("001"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"001",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("004", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("001", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("016") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("016"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"016",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("001"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"001",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("016", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("001", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("019") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("019"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"019",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("001"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"001",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("019", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("001", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("020") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("020"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"020",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("009"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"009",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("020", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("009", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("021") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("021"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"021",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("009"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"009",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("021", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("009", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("024") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("024"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"024",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("009"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"009",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("024", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("009", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("025") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("025"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"025",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("009"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"009",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("025", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("009", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("022") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("022"), alias);

										res = com.is.tieto.TclientService.check_card(
														"022",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("002"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"002",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("022", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("002", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("023") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("023"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"023",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(Integer.parseInt("002"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"002",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("023", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("002", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("860") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("860"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"860",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("860"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"860",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("860", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										// open_card("860", RT, true,
										// new_card_acc);
										contract_nmb = null;
									}
									else if (addwnd$sproduct.getValue().compareTo("017") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("004"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"017",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(Integer.parseInt("018"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"018",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("017", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("018", RT, true, new_card_acc);
										contract_nmb = null;
									}
									/*
									 * else if
									 * (addwnd$sproduct.getValue().compareTo
									 * ("001") == 0) { Tclient ntc; ntc =
									 * tietocl; TietoCardSetting tcs =
									 * TclientService
									 * .getTietoCardSetting(Integer
									 * .parseInt("004"), alias);
									 * 
									 * res =
									 * com.is.tieto.TclientService.check_card
									 * ("004"
									 * ,TclientService.getAccInfo_active(ntc
									 * .getClient()), alias); if(res.getCode()
									 * != 0) { alert(res.getName()); return; }
									 * tcs =
									 * TclientService.getTietoCardSetting(Integer
									 * .parseInt("001"), alias);
									 * 
									 * res =
									 * com.is.tieto.TclientService.check_card
									 * ("001"
									 * ,TclientService.getAccInfo_active(ntc
									 * .getClient()), alias); if(res.getCode()
									 * != 0) { alert(res.getName()); return; }
									 * contract_nmb = null; RT =
									 * open_card("001", RT, false,
									 * new_card_acc); if (RT == null) return;
									 * open_card("004", RT, true, new_card_acc);
									 * contract_nmb = null; }
									 */
									else if (addwnd$sproduct.getValue().compareTo("008") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("008"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"008",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("002"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"002",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("008", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("002", RT, true, new_card_acc);
										contract_nmb = null;
									} else if (addwnd$sproduct.getValue().compareTo("007") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("007"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"007",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("002"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"002",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("007", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("002", RT, true, new_card_acc);
										contract_nmb = null;
									}

									else if (addwnd$sproduct.getValue().compareTo("015") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("015"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"015",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("002"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"002",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("015", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("002", RT, true, new_card_acc);
										contract_nmb = null;
									}

									// 005 & 009
									/*
									 * else if
									 * (addwnd$sproduct.getValue().compareTo
									 * ("009") == 0) { Tclient ntc; ntc =
									 * tietocl; TietoCardSetting tcs =
									 * TclientService
									 * .getTietoCardSetting(Integer
									 * .parseInt("005"), alias);
									 * 
									 * res =
									 * com.is.tieto.TclientService.check_card
									 * ("005"
									 * ,TclientService.getAccInfo_active(ntc
									 * .getClient()), alias); if(res.getCode()
									 * != 0) { alert(res.getName()); return; }
									 * tcs =
									 * TclientService.getTietoCardSetting(Integer
									 * .parseInt("009"), alias);
									 * 
									 * res =
									 * com.is.tieto.TclientService.check_card
									 * ("009"
									 * ,TclientService.getAccInfo_active(ntc
									 * .getClient()), alias); if(res.getCode()
									 * != 0) { alert(res.getName()); return; }
									 * contract_nmb = null; RT =
									 * open_card("005", RT, false,
									 * new_card_acc); if (RT == null) return;
									 * open_card("009", RT, true, new_card_acc);
									 * contract_nmb = null; }
									 */

									else if (addwnd$sproduct.getValue().compareTo("005") == 0) {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("005"), alias);
										res = com.is.tieto.TclientService
												.check_card(
														"005",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}

										tcs = TclientService.getTietoCardSetting(
												Integer.parseInt("009"), alias);

										res = com.is.tieto.TclientService
												.check_card(
														"009",
														TclientService
																.getAccInfo_active(ntc
																		.getClient()),
														alias);
										if (res.getCode() != 0) {
											alert(res.getName());
											return;
										}
										contract_nmb = null;
										RT = open_card("005", RT, false,
												new_card_acc);
										if (RT == null)
											return;
										open_card("009", RT, true, new_card_acc);
										contract_nmb = null;
									}
									// end 005 & 009
									/*
									 * else if
									 * (addwnd$sproduct.getValue().compareTo
									 * ("002") == 0) { Tclient ntc; ntc =
									 * tietocl; TietoCardSetting tcs =
									 * TclientService
									 * .getTietoCardSetting(Integer
									 * .parseInt("007"), alias);
									 * 
									 * res =
									 * com.is.tieto.TclientService.check_card
									 * ("007"
									 * ,TclientService.getAccInfo_active(ntc
									 * .getClient()), alias); if(res.getCode()
									 * != 0) { alert(res.getName()); return; }
									 * tcs =
									 * TclientService.getTietoCardSetting(Integer
									 * .parseInt("002"), alias);
									 * 
									 * res =
									 * com.is.tieto.TclientService.check_card
									 * ("002"
									 * ,TclientService.getAccInfo_active(ntc
									 * .getClient()), alias); if(res.getCode()
									 * != 0) { alert(res.getName()); return; }
									 * contract_nmb = null; RT =
									 * open_card("002", RT, false,
									 * new_card_acc); if (RT == null) return;
									 * open_card("007", RT, true, new_card_acc);
									 * contract_nmb = null; }
									 */
									 else if (addwnd$sproduct.getValue().compareTo("031") == 0) {
											Tclient ntc;
											ntc = tietocl;
											TietoCardSetting tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("031"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"031",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}

											tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("001"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"001",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}
											contract_nmb = null;
											RT = open_card("031", RT, false,
													new_card_acc);
											if (RT == null)
												return;
											open_card("001", RT, true, new_card_acc);
											contract_nmb = null;
										}									
									 else if (addwnd$sproduct.getValue().compareTo("032") == 0) {
											Tclient ntc;
											ntc = tietocl;
											TietoCardSetting tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("032"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"032",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}

											tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("002"), alias);

											res = com.is.tieto.TclientService.check_card("002",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}
											contract_nmb = null;
											RT = open_card("032", RT, false,
													new_card_acc);
											if (RT == null)
												return;
											open_card("002", RT, true, new_card_acc);
											contract_nmb = null;
										}
									 else if (addwnd$sproduct.getValue().compareTo("033") == 0) {
											Tclient ntc;
											ntc = tietocl;
											TietoCardSetting tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("033"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"033",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}

											tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("002"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"002",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}
											contract_nmb = null;
											RT = open_card("033", RT, false,
													new_card_acc);
											if (RT == null)
												return;
											open_card("002", RT, true, new_card_acc);
											contract_nmb = null;
										}
									 else if (addwnd$sproduct.getValue().compareTo("034") == 0) {
											Tclient ntc;
											ntc = tietocl;
											TietoCardSetting tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("034"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"034",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}

											tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("002"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"002",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}
											contract_nmb = null;
											RT = open_card("034", RT, false,
													new_card_acc);
											if (RT == null)
												return;
											open_card("002", RT, true, new_card_acc);
											contract_nmb = null;
										}
									 else if (addwnd$sproduct.getValue().compareTo("040") == 0) {
											Tclient ntc;
											ntc = tietocl;
											TietoCardSetting tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("040"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"040",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}

											tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("002"), alias);
											res = com.is.tieto.TclientService
													.check_card(
															"002",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}
											contract_nmb = null;
											RT = open_card("040", RT, false,
													new_card_acc);
											if (RT == null)
												return;
											open_card("002", RT, true, new_card_acc);
											contract_nmb = null;
										}
									 else if (addwnd$sproduct.getValue().compareTo("041") == 0) {
											Tclient ntc;
											ntc = tietocl;
											TietoCardSetting tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("041"), alias);

											res = com.is.tieto.TclientService
													.check_card(
															"041",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}

											tcs = TclientService.getTietoCardSetting(
													Integer.parseInt("002"), alias);
											res = com.is.tieto.TclientService
													.check_card(
															"002",
															TclientService
																	.getAccInfo_active(ntc
																			.getClient()),
															alias);
											if (res.getCode() != 0) {
												alert(res.getName());
												return;
											}
											contract_nmb = null;
											RT = open_card("041", RT, false,
													new_card_acc);
											if (RT == null)
												return;
											open_card("002", RT, true, new_card_acc);
											contract_nmb = null;
										}
									 else {
										Tclient ntc;
										ntc = tietocl;
										TietoCardSetting tcs = TclientService.getTietoCardSetting(
												Integer.parseInt(addwnd$sproduct
														.getValue()), alias);

										res = com.is.tieto.TclientService.check_card(
												addwnd$sproduct.getValue(),
												TclientService
														.getAccInfo_active(ntc
																.getClient()),
												alias);
										if (res.getCode() != 0) {

											alert(res.getName());
											return;
										}

										contract_nmb = null;
										open_card(addwnd$sproduct.getValue(),
												RT, false, new_card_acc);
										contract_nmb = null;
									}
									addwnd.setVisible(false);
								}

							});

					h_add_acc_cell.appendChild(bt_acc_act);
				}

				row.setValue(pAccInfo);
				row.appendChild(new Listcell(pAccInfo.getBranch()));
				row.appendChild(new Listcell(pAccInfo.getClient()));
				row.appendChild(new Listcell(pAccInfo.getId()));
				row.appendChild(new Listcell(pAccInfo.getName()));
				row.appendChild(new Listcell(pAccInfo.getCurrency()));
				row.appendChild(new Listcell(
						pAccInfo.getDate_open() != null ? df.format(pAccInfo
								.getDate_open()) : "--//--"));
				row.appendChild(new Listcell(pAccInfo.getId_order()));
				row.appendChild(new Listcell(AccountService
						.get_account_state_caption(pAccInfo.getState(), alias)));
				row.appendChild(h_edit_cell);
				row.appendChild(h_add_acc_cell);
			}
		});

		// branch_customers.getHeads().

		branch_customers.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				boolean fl_show = true;
				Listcell acc_edit_cell = new Listcell();
				Customer pCustomer = (Customer) data;
				link lnk = CustomerService.get_link_branch(
						pCustomer.getId_client(), branch, alias);
				row.setAttribute("row", row);
				// branch_customers.sets
				row.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						is_ti = (Integer) event.getTarget().getAttribute(
								"is_ti") == 1 ? true : false;
						is_ho = (Integer) event.getTarget().getAttribute(
								"is_ho") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute(
								"is_br") == 1 ? true : false;

						cur_HO_customer = (Customer) event.getTarget()
								.getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget()
								.getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute(
								"ti_cl");
						bank_customers.clearSelection();
						head_customers.clearSelection();
						// ((Listitem)event.getTarget().getAttribute("row")).setStyle("background-color:green;");
					}

				});

				if (lnk != null)
					if (used_ids.containsKey(lnk.id)) {
						if (used_ids.containsKey(lnk.id)) {
							fl_show = false;
							row.setVisible(false);
						} else {
							used_ids.put(lnk.id, 1);
							fl_show = true;
						}
					}
				if (fl_show) {

					is_br = true;

					Listcell br_cell = new Listcell();

					btbreak = new Toolbarbutton();
					btbreak.setLabel("");
					btbreak.setImage("/images/link_break16.png");
					btbreak.setTooltiptext("Разделить связку");
					// btbreak.setTooltip("Удалить связку");
					// btbreak.setAttribute("ti_cl", pTclient);
					btbreak.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									int lnk_id = (Integer) event.getTarget()
											.getAttribute("lnk_id");

									Res res = CustomerService.removeTc(un, pwd,
											lnk_id, alias);

									if (res.getCode() != 0) {
										alert(res.getName());
										return;
									}
									refreshModel(_starttPageNumber);
								}

							});
					br_cell.appendChild(btbreak);
					// btedit = null;
					row.appendChild(br_cell);

					Listcell edit_cell = new Listcell();

					btedit = new Toolbarbutton();
					btedit.setLabel("");
					btedit.setImage("/images/edit.png");
					btedit.setAttribute("br_cl", pCustomer);
					btedit.setAttribute("is_br", true);
					btedit.setAttribute("ho_cl", null);
					btedit.setAttribute("ti_cl", null);
					btedit.setAttribute("is_ti", false);
					btedit.setAttribute("is_ho", false);
					btedit.setAttribute("edit_ho", false);
					btedit.setAttribute("edit_br", false);
					btedit.setAttribute("edit_ti", false);
					btedit.setTooltiptext("Редактировать везде");
					btedit.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									is_ti = (Boolean) event.getTarget()
											.getAttribute("is_ti");
									is_ho = (Boolean) event.getTarget()
											.getAttribute("is_ho");
									is_br = (Boolean) event.getTarget()
											.getAttribute("is_br");

									cur_HO_customer = (Customer) event
											.getTarget().getAttribute("ho_cl");
									cur_branch_customer = (Customer) event
											.getTarget().getAttribute("br_cl");
									tietocl = (Tclient) event.getTarget()
											.getAttribute("ti_cl");
									if ((cur_HO_customer != null && cur_HO_customer
											.getCode_subject().equals("J"))
											|| (cur_branch_customer != null && cur_branch_customer
													.getCode_subject().equals(
															"J"))) {
										alert("Невозможно редактрование клиента ИП");
										return;
									}
									edit_ti = (Boolean) event.getTarget()
											.getAttribute("is_ti");
									edit_ho = (Boolean) event.getTarget()
											.getAttribute("is_ho");
									edit_br = (Boolean) event.getTarget()
											.getAttribute("is_br");

									CheckNull.clearForm(add_everywhere$addgrdl);
									CheckNull.clearForm(add_everywhere$addgrdr);
									if (cur_branch_customer != null)
										fill_form(cur_branch_customer, tietocl);
									else if (cur_HO_customer != null)
										fill_form(cur_HO_customer, tietocl);
									else if (tietocl != null)
										fill_form(tietocl);
									add_everywhere
											.setTitle("Редактирование клиента");
									fl_edit = true;
									add_everywhere.setVisible(true);
								}

							});
					edit_cell.appendChild(btedit);
					row.appendChild(edit_cell);

					Listcell b_edit_cell = new Listcell();
					row.setValue(pCustomer);

					Listcell h_edit_cell = new Listcell();
					bth = new Toolbarbutton();
					bth.setLabel("");
					bth.setImage("/images/edit.png");
					bth.setAttribute("br_cl", pCustomer);
					bth.setTooltiptext("Редактировать ГО");
					row.setAttribute("br_cl", pCustomer);
					bth.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							is_ti = (Integer) event.getTarget().getAttribute(
									"is_ti") == 1 ? true : false;
							is_ho = (Integer) event.getTarget().getAttribute(
									"is_ho") == 1 ? true : false;
							is_br = (Integer) event.getTarget().getAttribute(
									"is_br") == 1 ? true : false;

							cur_HO_customer = (Customer) event.getTarget()
									.getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget()
									.getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute(
									"ti_cl");
							add_ti = false;
							add_ho = true;
							add_br = false;
							set_default();
							session.setAttribute("branch_filter",
									TclientService.getV_HO());
							session.setAttribute("alias_filter",
									CustomerService.get_alias_ho(alias));
							add_to_ho = 1;
							fl_edit = false;
							addCustomer.setVisible(true);
						}

					});
					h_edit_cell.appendChild(bth);

					Listcell t_edit_cell = new Listcell();
					btt = new Toolbarbutton();
					btt.setLabel("");
					btt.setImage("/images/+.png");
					btt.setAttribute("br_cl", pCustomer);
					btt.setTooltiptext("Редактировать tieto");
					btt.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							is_ti = (Integer) event.getTarget().getAttribute(
									"is_ti") == 1 ? true : false;
							is_ho = (Integer) event.getTarget().getAttribute(
									"is_ho") == 1 ? true : false;
							is_br = (Integer) event.getTarget().getAttribute(
									"is_br") == 1 ? true : false;

							cur_HO_customer = (Customer) event.getTarget()
									.getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget()
									.getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute(
									"ti_cl");
							add_ti = true;
							add_ho = false;
							add_br = false;
							set_default();
							fl_edit = false;
							add_everywhere.setTitle("Открытие клиента [TIETO]");
							add_everywhere.setVisible(true);
						}

					});
					t_edit_cell.appendChild(btt);

					btacc = new Toolbarbutton();
					// btacc.setLabel("acc");
					btacc.setImage("/images/edit.png");
					btacc.setAttribute("br_cl", pCustomer);
					btacc.setTooltiptext("Редактировать счет 20206");
					btacc.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									is_ti = (Integer) event.getTarget()
											.getAttribute("is_ti") == 1 ? true
											: false;
									is_ho = (Integer) event.getTarget()
											.getAttribute("is_ho") == 1 ? true
											: false;
									is_br = (Integer) event.getTarget()
											.getAttribute("is_br") == 1 ? true
											: false;

									cur_HO_customer = (Customer) event
											.getTarget().getAttribute("ho_cl");
									cur_branch_customer = (Customer) event
											.getTarget().getAttribute("br_cl");
									tietocl = (Tclient) event.getTarget()
											.getAttribute("ti_cl");
									add_ti = false;
									add_ho = false;
									add_br = true;

									AccountFilter acfilter = new AccountFilter();
									acfilter.setClient(cur_branch_customer
											.getId_client());
									acfilter.setAcc_bal(// "20206"
									cur_branch_customer.getCode_subject()
											.equals("J") ? "23104" : "20206");
									acfilter.setBranch(branch);
									acfilter.setCurrency("840");
									com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
											0, 100, acfilter, alias);
									accounts$accGrid
											.setModel((ListModel) acc_model);
									accounts$btn_add.setDisabled(false);
									accounts.setVisible(true);
									accounts$btn_add
											.setVisible(!cur_branch_customer
													.getCode_subject().equals(
															"J"));
									// set_default();
									// session.setAttribute("branch_filter",
									// branch);
									// session.setAttribute("alias_filter",
									// alias);
									// addCustomer.setVisible(true);
								}

							});
					acc_edit_cell.appendChild(btacc);

					if (lnk != null) {
						btbreak.setAttribute("lnk_id", lnk.id);
						if (lnk.Tieto_id != null) {
							is_ti = true;
							tietocl = TclientService
									.getTclient_by_id(lnk.Tieto_id);
							row.appendChild(new Listcell(lnk.Tieto_id));
							if (tietocl != null) {
								btedit.setAttribute("is_ti", true);
								btedit.setAttribute("ti_cl", tietocl);
								row.appendChild(new Listcell(
										(tietocl.getF_names() != null ? tietocl
												.getF_names() : "")
												+ " "
												+ (tietocl.getSurname() != null ? tietocl
														.getSurname() : "")));
								row.appendChild(new Listcell(tietocl
										.getB_date() != null ? df
										.format(tietocl.getB_date()) : "--//--"));
								bth.setAttribute("ti_cl", tietocl);
								btt.setAttribute("ti_cl", tietocl);
								btacc.setAttribute("ti_cl", tietocl);
								row.setAttribute("ti_cl", tietocl);
							} else {
								is_ti = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						} else {
							is_ti = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}

						row.appendChild(t_edit_cell);

						if ((lnk.Head_id != null)) {
							is_ho = true;
							String halias = CustomerService.get_alias_ho(alias);
							Customer branch_cst = com.is.customer.CustomerService
									.getCustomerById(lnk.Head_id,
											TclientService.getV_HO(),
											halias);
							row.appendChild(new Listcell(lnk.Head_id));
							if (branch_cst != null) {
								btedit.setAttribute("is_hi", true);

								Listcell lc1 = new Listcell(branch_cst
										.getName() != null ? branch_cst
										.getName() : "--//--");
								Listcell lc2 = new Listcell((branch_cst
										.getP_birthday() != null) ? df
										.format(branch_cst.getP_birthday())
										: "--//--");
								// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL

								if (branch_cst.getState() != 2) {

									lc1.setStyle("background-color:#dadada;");
									lc2.setStyle("background-color:#dadada;");

									
									if (branch_cst.getState() == 1) {
										lc1.setContext(confirmPopup);
										lc2.setContext(confirmPopup);
										
									} else
									{
  									  lc1.setContext(editPopup);
	  								  lc2.setContext(editPopup);
									}
									lc1.setAttribute("branch",
											branch_cst.getBranch());
									lc1.setAttribute("client_id",
											branch_cst.getId_client());
									lc1.setAttribute("alias", halias);
									lc2.setAttribute("branch",
											branch_cst.getBranch());
									lc2.setAttribute("client_id",
											branch_cst.getId_client());
									lc2.setAttribute("alias", halias);
									
								}

								btedit.setAttribute("ho_cl", branch_cst);
								row.appendChild(lc1);
								row.appendChild(lc2);
								bth.setAttribute("ho_cl", branch_cst);
								btt.setAttribute("ho_cl", branch_cst);
								btacc.setAttribute("ho_cl", branch_cst);
								row.setAttribute("ho_cl", branch_cst);
							} else {
								is_ho = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						} else {
							is_ho = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
					} else {
						btbreak.setVisible(false);
						is_ho = false;
						is_ti = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));

						row.appendChild(t_edit_cell);

						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
					}

					row.appendChild(h_edit_cell);
					// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
					Listcell lc1 = new Listcell(pCustomer.getId_client());
					Listcell lc2 = new Listcell(
							pCustomer.getName() != null ? pCustomer.getName()
									: "--//--");
					Listcell lc3 = new Listcell(
							pCustomer.getP_birthday() != null ? df
									.format(pCustomer.getP_birthday())
									: "--//--");

					if (pCustomer.getState() != 2) {
						lc2.setStyle("background-color:#dadada;");
						lc3.setStyle("background-color:#dadada;");
						if (pCustomer.getState() == 1) {
							lc1.setContext(confirmPopup);
							lc2.setContext(confirmPopup);
						} else {
						
						lc1.setContext(editPopup);
						lc2.setContext(editPopup);
						}
						lc1.setAttribute("branch", pCustomer.getBranch());
						lc1.setAttribute("client_id", pCustomer.getId_client());
						lc1.setAttribute("alias", alias);
						lc2.setAttribute("branch", pCustomer.getBranch());
						lc2.setAttribute("client_id", pCustomer.getId_client());
						lc2.setAttribute("alias", alias);
					}

					row.appendChild(lc1);
					row.appendChild(lc2);
					row.appendChild(lc3);
					row.appendChild(new Listcell());

					// row.appendChild(new
					// Listcell("["+pCustomer.getBranch()+"]"));
					bth.setAttribute("is_ti", is_ti ? 1 : 0);
					bth.setAttribute("is_ho", is_ho ? 1 : 0);
					bth.setAttribute("is_br", is_br ? 1 : 0);
					btt.setAttribute("is_ti", is_ti ? 1 : 0);
					btt.setAttribute("is_ho", is_ho ? 1 : 0);
					btt.setAttribute("is_br", is_br ? 1 : 0);
					btacc.setAttribute("is_ti", is_ti ? 1 : 0);
					btacc.setAttribute("is_ho", is_ho ? 1 : 0);
					btacc.setAttribute("is_br", is_br ? 1 : 0);
					row.setAttribute("is_ti", is_ti ? 1 : 0);
					row.setAttribute("is_ho", is_ho ? 1 : 0);
					row.setAttribute("is_br", is_br ? 1 : 0);

					// row.appendChild(new Listcell((is_ti?"1":"0") +
					// (is_ho?"1":"0") + (is_br?"1":"0")));
					if (lnk != null) {
						if (lnk.Cur_acc != null) {
							row.appendChild(new Listcell(lnk.Cur_acc));
							is_acc = true;
							if (is_ti && is_ho && is_br)
								row.setStyle("background-color:#D2F7CA; font-weight:bold;");
						} else {
							row.appendChild(new Listcell());
						}
						if (is_ti && is_br && is_ho) {
							row.appendChild(acc_edit_cell);
						} else {
							row.appendChild(new Listcell());
						}
					} else {
						row.appendChild(new Listcell());
						row.appendChild(new Listcell());
					}
					if (is_ti)
						btt.setVisible(false);
					if (is_ho)
						bth.setVisible(false);
					// if(is_br)btb.setVisible(false);
				}

			}
		});

		head_customers.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				boolean fl_show = true;
				Listcell acc_edit_cell = null;
				Customer pCustomer = (Customer) data;
				link lnk = CustomerService.get_link_ho(
						pCustomer.getId_client(), branch, alias);

				if (lnk != null)
					if (used_ids.containsKey(lnk.id)) {
						if (used_ids.containsKey(lnk.id)) {
							fl_show = false;
							row.setVisible(false);
						} else {
							used_ids.put(lnk.id, 1);
							fl_show = true;
						}
					}
				if (fl_show) {
					row.setValue(pCustomer);
					// row.setValue(null);

					Listcell br_cell = new Listcell();

					btbreak = new Toolbarbutton();
					btbreak.setLabel("");
					btbreak.setImage("/images/link_break16.png");
					btbreak.setTooltiptext("Разделить связку");
					// btbreak.setTooltip("Удалить связку");
					// btbreak.setAttribute("ti_cl", pTclient);
					btbreak.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									int lnk_id = (Integer) event.getTarget()
											.getAttribute("lnk_id");

									Res res = CustomerService.removeTc(un, pwd,
											lnk_id, alias);

									if (res.getCode() != 0) {
										alert(res.getName());
										return;
									}
									refreshModel(_starttPageNumber);
								}

							});
					br_cell.appendChild(btbreak);
					// btedit = null;
					row.appendChild(br_cell);

					Listcell edit_cell = new Listcell();

					btedit = new Toolbarbutton();
					btedit.setLabel("");
					btedit.setImage("/images/edit.png");
					btedit.setAttribute("ho_cl", pCustomer);
					btedit.setAttribute("is_ho", true);
					btedit.setAttribute("br_cl", null);
					btedit.setAttribute("ti_cl", null);
					btedit.setAttribute("is_ti", false);
					btedit.setAttribute("is_br", false);
					btedit.setAttribute("edit_ho", false);
					btedit.setAttribute("edit_br", false);
					btedit.setAttribute("edit_ti", false);
					btedit.setTooltiptext("Редактировать везде");
					btedit.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									is_ti = (Boolean) event.getTarget()
											.getAttribute("is_ti");
									is_ho = (Boolean) event.getTarget()
											.getAttribute("is_ho");
									is_br = (Boolean) event.getTarget()
											.getAttribute("is_br");

									cur_HO_customer = (Customer) event
											.getTarget().getAttribute("ho_cl");
									cur_branch_customer = (Customer) event
											.getTarget().getAttribute("br_cl");
									tietocl = (Tclient) event.getTarget()
											.getAttribute("ti_cl");
									if ((cur_HO_customer != null && cur_HO_customer
											.getCode_subject().equals("J"))
											|| (cur_branch_customer != null && cur_branch_customer
													.getCode_subject().equals(
															"J"))) {
										alert("Невозможно редактрование клиента ИП");
										return;
									}
									edit_ti = (Boolean) event.getTarget()
											.getAttribute("is_ti");
									edit_ho = (Boolean) event.getTarget()
											.getAttribute("is_ho");
									edit_br = (Boolean) event.getTarget()
											.getAttribute("is_br");

									CheckNull.clearForm(add_everywhere$addgrdl);
									CheckNull.clearForm(add_everywhere$addgrdr);
									if (cur_branch_customer != null)
										fill_form(cur_branch_customer, tietocl);
									else if (cur_HO_customer != null)
										fill_form(cur_HO_customer, tietocl);
									else if (tietocl != null)
										fill_form(tietocl);
									add_everywhere
											.setTitle("Редактирование клиента");
									fl_edit = true;
									add_everywhere.setVisible(true);
								}

							});
					edit_cell.appendChild(btedit);
					row.appendChild(edit_cell);

					row.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							is_ti = (Integer) event.getTarget().getAttribute(
									"is_ti") == 1 ? true : false;
							is_ho = (Integer) event.getTarget().getAttribute(
									"is_ho") == 1 ? true : false;
							is_br = (Integer) event.getTarget().getAttribute(
									"is_br") == 1 ? true : false;

							cur_HO_customer = (Customer) event.getTarget()
									.getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget()
									.getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute(
									"ti_cl");
							bank_customers.clearSelection();
							branch_customers.clearSelection();
						}

					});

					Listcell b_edit_cell = new Listcell();
					btb = new Toolbarbutton();
					btb.setLabel("");
					btb.setImage("/images/edit.png");
					btb.setAttribute("ho_cl", pCustomer);
					btb.setTooltiptext("Редактировать филиал");
					btb.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							is_ti = (Integer) event.getTarget().getAttribute(
									"is_ti") == 1 ? true : false;
							is_ho = (Integer) event.getTarget().getAttribute(
									"is_ho") == 1 ? true : false;
							is_br = (Integer) event.getTarget().getAttribute(
									"is_br") == 1 ? true : false;

							cur_HO_customer = (Customer) event.getTarget()
									.getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget()
									.getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute(
									"ti_cl");
							add_ti = false;
							add_ho = false;
							add_br = true;
							set_default();
							fl_edit = false;
							session.setAttribute("branch_filter", branch);
							session.setAttribute("alias_filter", alias);
							addCustomer.setVisible(true);
							// addCustomer.
						}

					});
					b_edit_cell.appendChild(btb);

					Listcell t_edit_cell = new Listcell();
					btt = new Toolbarbutton();
					btt.setLabel("");
					btt.setImage("/images/+.png");
					btt.setAttribute("ho_cl", pCustomer);
					btt.setTooltiptext("Редактировать tieto");
					btt.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							is_ti = (Integer) event.getTarget().getAttribute(
									"is_ti") == 1 ? true : false;
							is_ho = (Integer) event.getTarget().getAttribute(
									"is_ho") == 1 ? true : false;
							is_br = (Integer) event.getTarget().getAttribute(
									"is_br") == 1 ? true : false;

							cur_HO_customer = (Customer) event.getTarget()
									.getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget()
									.getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute(
									"ti_cl");
							add_ti = true;
							add_ho = false;
							add_br = false;
							fl_edit = false;
							set_default();
							add_everywhere.setTitle("Открытие клиента [TIETO]");
							add_everywhere.setVisible(true);
						}

					});
					t_edit_cell.appendChild(btt);

					btacc = new Toolbarbutton();
					// btacc.setLabel("acc");
					btacc.setImage("/images/edit.png");
					btacc.setAttribute("ti_cl", tietocl);
					btacc.setTooltiptext("Редактировать счет 20206");
					btacc.addEventListener(Events.ON_CLICK,
							new EventListener() {
								@Override
								public void onEvent(Event event)
										throws Exception {
									is_ti = (Integer) event.getTarget()
											.getAttribute("is_ti") == 1 ? true
											: false;
									is_ho = (Integer) event.getTarget()
											.getAttribute("is_ho") == 1 ? true
											: false;
									is_br = (Integer) event.getTarget()
											.getAttribute("is_br") == 1 ? true
											: false;

									cur_HO_customer = (Customer) event
											.getTarget().getAttribute("ho_cl");
									cur_branch_customer = (Customer) event
											.getTarget().getAttribute("br_cl");
									tietocl = (Tclient) event.getTarget()
											.getAttribute("ti_cl");
									add_ti = false;
									add_ho = false;
									add_br = true;

									AccountFilter acfilter = new AccountFilter();
									acfilter.setClient(cur_branch_customer
											.getId_client());
									acfilter.setAcc_bal(// "20206"
									cur_branch_customer.getCode_subject()
											.equals("J") ? "23104" : "20206");
									acfilter.setBranch(branch);
									acfilter.setCurrency("840");
									com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
											0, 100, acfilter, alias);
									accounts$accGrid
											.setModel((ListModel) acc_model);
									accounts$btn_add.setDisabled(false);
									accounts.setVisible(true);
									accounts$btn_add
											.setVisible(!cur_branch_customer
													.getCode_subject().equals(
															"J"));
									// set_default();
									// session.setAttribute("branch_filter",
									// branch);
									// session.setAttribute("alias_filter",
									// alias);
									// addCustomer.setVisible(true);
								}

							});

					is_ho = true;

					if (lnk != null) {
						btbreak.setAttribute("lnk_id", lnk.id);
						// System.out.print("||head:"+lnk.Head_id+"||tieto:"+lnk.Tieto_id+"||branch:"+lnk.Branch_id);

						if (lnk.Tieto_id != null) {
							is_ti = true;

							tietocl = TclientService
									.getTclient_by_id(lnk.Tieto_id);
							// System.out.print("||head:"+head_cst.getName());
							row.appendChild(new Listcell(lnk.Tieto_id));
							if (tietocl != null) {
								btedit.setAttribute("is_ti", true);
								btedit.setAttribute("ti_cl", tietocl);
								// row.setValue(tietocl);
								row.appendChild(new Listcell(
										(tietocl.getF_names() != null ? tietocl
												.getF_names() : "")
												+ " "
												+ (tietocl.getSurname() != null ? tietocl
														.getSurname() : "")));
								row.appendChild(new Listcell(tietocl
										.getB_date() != null ? df
										.format(tietocl.getB_date()) : "--//--"));
								btb.setAttribute("ti_cl", tietocl);
								btt.setAttribute("ti_cl", tietocl);
								row.setAttribute("ti_cl", tietocl);
								btacc.setAttribute("ti_cl", tietocl);
							} else {
								is_ti = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						} else {

							is_ti = false;

							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
					} else {
						btbreak.setVisible(false);
						String pre_ti = CustomerService.get_tieto_by_ho(
								pCustomer.getId_client(), alias);
						if (pre_ti != null) {
							tietocl = TclientService.getTclient_by_id(pre_ti);
							// System.out.print("||head:"+head_cst.getName());
							row.appendChild(new Listcell(pre_ti));
							if (tietocl != null) {
								btedit.setAttribute("ti_cl", tietocl);
								row.appendChild(new Listcell(
										(tietocl.getF_names() != null ? tietocl
												.getF_names() : "")
												+ " "
												+ (tietocl.getSurname() != null ? tietocl
														.getSurname() : "")));
								row.appendChild(new Listcell(tietocl
										.getB_date() != null ? df
										.format(tietocl.getB_date()) : "--//--"));
								btb.setAttribute("ti_cl", tietocl);
								btt.setAttribute("ti_cl", tietocl);
								row.setAttribute("ti_cl", tietocl);
							} else {
								is_ti = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						} else {
							is_br = false;
							is_ti = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
					}
					row.appendChild(t_edit_cell);

					Listcell lc1 = new Listcell(
							pCustomer.getName() != null ? pCustomer.getName()
									: "--//--");
					Listcell lc2 = new Listcell(
							pCustomer.getP_birthday() != null ? df
									.format(pCustomer.getP_birthday())
									: "--//--");
					// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL

					if (pCustomer.getState() != 2) {
						lc1.setStyle("background-color:#dadada;");
						lc2.setStyle("background-color:#dadada;");
						if (pCustomer.getState() == 1) {
						lc1.setContext(confirmPopup);
						lc2.setContext(confirmPopup);
						} else {
							lc1.setContext(editPopup);
							lc2.setContext(editPopup);
							
						}
						lc1.setAttribute("branch", pCustomer.getBranch());
						lc1.setAttribute("client_id", pCustomer.getId_client());
						lc1.setAttribute("alias",
								ConnectionPool.getValue("HO_ALIAS", alias));
						lc2.setAttribute("branch", pCustomer.getBranch());
						lc2.setAttribute("client_id", pCustomer.getId_client());
						lc2.setAttribute("alias",
								ConnectionPool.getValue("HO_ALIAS", alias));
					}

					row.appendChild(new Listcell(pCustomer.getId_client()));
					row.appendChild(lc1);
					row.appendChild(lc2);
					row.appendChild(new Listcell());

					if (lnk != null) {
						if ((lnk.Branch_id != null)) {
							is_br = true;

							Customer branch_cst = com.is.customer.CustomerService
									.getCustomerById(lnk.Branch_id, branch,
											alias);
							row.appendChild(new Listcell(lnk.Branch_id));
							if (branch_cst != null) {
								Listcell lc11 = new Listcell(branch_cst
										.getName() != null ? branch_cst
										.getName() : "--//--");
								Listcell lc21 = new Listcell((branch_cst
										.getP_birthday() != null) ? df
										.format(branch_cst.getP_birthday())
										: "--//--");
								// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL

								if (branch_cst.getState() != 2) {
									lc11.setStyle("background-color:#dadada;");
									lc21.setStyle("background-color:#dadada;");
									if (branch_cst.getState() == 1) {
									lc1.setContext(confirmPopup);
									lc2.setContext(confirmPopup);
									} else {
										lc1.setContext(editPopup);
										lc2.setContext(editPopup);
										
									}
									
									lc1.setAttribute("branch",
											branch_cst.getBranch());
									lc1.setAttribute("client_id",
											branch_cst.getId_client());
									lc1.setAttribute("alias", alias);
									lc2.setAttribute("branch",
											branch_cst.getBranch());
									lc2.setAttribute("client_id",
											branch_cst.getId_client());
									lc2.setAttribute("alias", alias);
								}

								btedit.setAttribute("is_br", true);
								btedit.setAttribute("br_cl", branch_cst);
								row.appendChild(lc11);
								row.appendChild(lc21);
								btb.setAttribute("br_cl", branch_cst);
								btt.setAttribute("br_cl", branch_cst);
								btacc.setAttribute("br_cl", branch_cst);
								row.setAttribute("br_cl", branch_cst);
							} else {
								is_br = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						} else {
							is_br = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}

						acc_edit_cell = new Listcell();
						if (is_ti && is_ho && is_br) {
							acc_edit_cell.appendChild(btacc);
						}

					} else {
						is_ti = false;
						is_br = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
					}
					row.appendChild(b_edit_cell);

					btt.setAttribute("is_ti", is_ti ? 1 : 0);
					btt.setAttribute("is_ho", is_ho ? 1 : 0);
					btt.setAttribute("is_br", is_br ? 1 : 0);
					btb.setAttribute("is_ti", is_ti ? 1 : 0);
					btb.setAttribute("is_ho", is_ho ? 1 : 0);
					btb.setAttribute("is_br", is_br ? 1 : 0);
					btacc.setAttribute("is_ti", is_ti ? 1 : 0);
					btacc.setAttribute("is_ho", is_ho ? 1 : 0);
					btacc.setAttribute("is_br", is_br ? 1 : 0);
					row.setAttribute("is_ti", is_ti ? 1 : 0);
					row.setAttribute("is_ho", is_ho ? 1 : 0);
					row.setAttribute("is_br", is_br ? 1 : 0);
					// row.appendChild(new Listcell("["+branch+"]"));
					// row.appendChild(new Listcell((is_ti?"1":"0") +
					// (is_ho?"1":"0") + (is_br?"1":"0")));
					if (lnk != null) {
						if (lnk.Cur_acc != null) {
							row.appendChild(new Listcell(lnk.Cur_acc));
							is_acc = true;
							if (is_ti && is_ho && is_br)
								row.setStyle("background-color:#D2F7CA;");
						} else {
							row.appendChild(new Listcell());
						}
						if (is_ti && is_br && is_ho) {
							row.appendChild(acc_edit_cell);
						} else {
							row.appendChild(new Listcell());
						}
					} else {
						row.appendChild(new Listcell());
						row.appendChild(new Listcell());
					}
					if (is_ti)
						btt.setVisible(false);
					// if(is_ho)bth.setVisible(false);
					if (is_br)
						btb.setVisible(false);
				}

			}

		});

		bank_customers.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {

				is_acc = false;
				Tclient pTclient = (Tclient) data;
				link lnk = CustomerService.get_link_tieto(pTclient.getClient(),
						branch, alias);
				if (lnk != null)
					used_ids.put(lnk.id, 1);

				row.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						head_customers.clearSelection();
						branch_customers.clearSelection();
						cur_branch_customer = (Customer) event.getTarget()
								.getAttribute("br_cl");
						cur_HO_customer = (Customer) event.getTarget()
								.getAttribute("ho_cl");
					}

				});

				Listcell br_cell = new Listcell();

				btbreak = new Toolbarbutton();
				btbreak.setLabel("");
				btbreak.setImage("/images/link_break16.png");
				btbreak.setTooltiptext("Разделить связку");
				// btbreak.setTooltip("Удалить связку");
				btbreak.setAttribute("ti_cl", pTclient);
				btbreak.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						int lnk_id = (Integer) event.getTarget().getAttribute(
								"lnk_id");

						Res res = CustomerService.removeTc(un, pwd, lnk_id,
								alias);

						if (res.getCode() != 0) {
							alert(res.getName());
							return;
						}
						refreshModel(_starttPageNumber);
					}

				});
				br_cell.appendChild(btbreak);
				// btedit = null;
				row.appendChild(br_cell);

				Listcell edit_cell = new Listcell();

				btedit = new Toolbarbutton();
				btedit.setLabel("");
				btedit.setImage("/images/edit.png");
				btedit.setAttribute("ti_cl", pTclient);
				btedit.setAttribute("is_ti", true);
				btedit.setAttribute("br_cl", null);
				btedit.setAttribute("ho_cl", null);
				btedit.setAttribute("is_br", false);
				btedit.setAttribute("is_ho", false);
				btedit.setAttribute("edit_ho", false);
				btedit.setAttribute("edit_br", false);
				btedit.setAttribute("edit_ti", false);
				btedit.setTooltiptext("Редактировать везде");
				btedit.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						is_ti = (Boolean) event.getTarget().getAttribute(
								"is_ti");
						is_ho = (Boolean) event.getTarget().getAttribute(
								"is_ho");
						is_br = (Boolean) event.getTarget().getAttribute(
								"is_br");

						cur_HO_customer = (Customer) event.getTarget()
								.getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget()
								.getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute(
								"ti_cl");

						if ((cur_HO_customer != null && cur_HO_customer
								.getCode_subject().equals("J"))
								|| (cur_branch_customer != null && cur_branch_customer
										.getCode_subject().equals("J"))) {
							alert("Невозможно редактрование клиента ИП");
							return;
						}

						edit_ti = (Boolean) event.getTarget().getAttribute(
								"is_ti");
						edit_ho = (Boolean) event.getTarget().getAttribute(
								"is_ho");
						edit_br = (Boolean) event.getTarget().getAttribute(
								"is_br");

						CheckNull.clearForm(add_everywhere$addgrdl);
						CheckNull.clearForm(add_everywhere$addgrdr);
						if (cur_branch_customer != null)
							fill_form(cur_branch_customer, tietocl);
						else if (cur_HO_customer != null)
							fill_form(cur_HO_customer, tietocl);
						else if (tietocl != null)
							fill_form(tietocl);
						add_everywhere.setTitle("Редактирование клиента");
						fl_edit = true;
						add_everywhere.setVisible(true);
					}

				});
				edit_cell.appendChild(btedit);
				row.appendChild(edit_cell);

				Listcell h_edit_cell = new Listcell();
				bth = new Toolbarbutton();
				bth.setLabel("");
				bth.setImage("/images/edit.png");
				bth.setAttribute("ti_cl", pTclient);
				bth.setTooltiptext("Редактировать ГО");
				bth.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						is_ti = (Integer) event.getTarget().getAttribute(
								"is_ti") == 1 ? true : false;
						is_ho = (Integer) event.getTarget().getAttribute(
								"is_ho") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute(
								"is_br") == 1 ? true : false;

						cur_HO_customer = (Customer) event.getTarget()
								.getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget()
								.getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute(
								"ti_cl");
						add_ti = false;
						add_ho = true;
						add_br = false;
						set_default();
						fl_edit = false;
						session.setAttribute("branch_filter",
								TclientService.getV_HO());
						session.setAttribute("alias_filter",
								CustomerService.get_alias_ho(alias));
						addCustomer.setVisible(true);
						// addCustomer.
					}

				});
				h_edit_cell.appendChild(bth);

				is_ti = true;

				Listcell b_edit_cell = new Listcell();
				btb = new Toolbarbutton();
				btb.setLabel("");
				btb.setImage("/images/edit.png");
				btb.setAttribute("ti_cl", pTclient);
				btb.setTooltiptext("Редактировать филиал");
				btb.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						is_ti = (Integer) event.getTarget().getAttribute(
								"is_ti") == 1 ? true : false;
						is_ho = (Integer) event.getTarget().getAttribute(
								"is_ho") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute(
								"is_br") == 1 ? true : false;

						cur_HO_customer = (Customer) event.getTarget()
								.getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget()
								.getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute(
								"ti_cl");
						add_ti = false;
						add_ho = false;
						add_br = true;
						set_default();
						fl_edit = false;
						session.setAttribute("branch_filter", branch);
						session.setAttribute("alias_filter", alias);
						addCustomer.setVisible(true);
					}

				});
				b_edit_cell.appendChild(btb);

				Listcell acc_edit_cell = new Listcell();
				btacc = new Toolbarbutton();
				// btacc.setLabel("acc");
				btacc.setImage("/images/edit.png");
				btacc.setAttribute("ti_cl", pTclient);
				btacc.setTooltiptext("Редактировать счет 20206");
				btacc.addEventListener(Events.ON_CLICK, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						is_ti = (Integer) event.getTarget().getAttribute(
								"is_ti") == 1 ? true : false;
						is_ho = (Integer) event.getTarget().getAttribute(
								"is_ho") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute(
								"is_br") == 1 ? true : false;

						cur_HO_customer = (Customer) event.getTarget()
								.getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget()
								.getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute(
								"ti_cl");
						add_ti = false;
						add_ho = false;
						add_br = true;

						AccountFilter acfilter = new AccountFilter();
						acfilter.setClient(cur_branch_customer.getId_client());
						acfilter.setAcc_bal(// "20206"
						cur_branch_customer.getCode_subject().equals("J") ? "23104"
								: "20206");
						acfilter.setBranch(branch);
						acfilter.setCurrency("840");
						com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
								0, 100, acfilter, alias);
						accounts$accGrid.setModel((ListModel) acc_model);
						accounts$btn_add.setDisabled(false);
						accounts.setVisible(true);
						accounts$btn_add.setVisible(!cur_branch_customer
								.getCode_subject().equals("J"));
						// set_default();
						// session.setAttribute("branch_filter", branch);
						// session.setAttribute("alias_filter", alias);
						// addCustomer.setVisible(true);
					}

				});
				acc_edit_cell.appendChild(btacc);

				row.setValue(pTclient);
				row.appendChild(new Listcell(
						pTclient.getClient() != null ? pTclient.getClient()
								: "--//--"));
				row.appendChild(new Listcell(
						(pTclient.getF_names() != null ? pTclient.getF_names()
								: "")
								+ " "
								+ (pTclient.getSurname() != null ? pTclient
										.getSurname() : "")));

				row.appendChild(new Listcell(pTclient.getB_date() != null ? df
						.format(pTclient.getB_date()) : "--//--"));
				// row.appendChild(t_edit_cell);
				row.appendChild(new Listcell());
				// System.out.print(">>"+pTclient.getClient()+"||"+pTclient.getSurname());

				// link lnk =
				// CustomerService.get_link_tieto(pTclient.getClient(), branch,
				// alias);
				// if (lnk==null) lnk =
				// CustomerService.get_link_tieto(pTclient.getClient(), alias);
				// if ((lnk.Branch != branch)&&(lnk.Head_id == null)) lnk=null;
				if (lnk != null) {
					btbreak.setAttribute("lnk_id", lnk.id);
					// System.out.print("||head:"+lnk.Head_id+"||tieto:"+lnk.Tieto_id+"||branch:"+lnk.Branch_id);

					if (lnk.Head_id != null) {

						is_ho = true;
						String halias = CustomerService.get_alias_ho(alias);
						Customer head_cst = com.is.customer.CustomerService
								.getCustomerById(lnk.Head_id,
										TclientService.getV_HO(),
										halias);
						// System.out.print("||head:"+head_cst.getName());

						btedit.setAttribute("is_ho", true);
						row.appendChild(new Listcell(lnk.Head_id));
						if (head_cst != null) {
							btedit.setAttribute("ho_cl", head_cst);

							Listcell lc1 = new Listcell(
									head_cst.getName() != null ? head_cst
											.getName() : "--//--");
							Listcell lc2 = new Listcell(head_cst
									.getP_birthday() != null ? df
									.format(head_cst.getP_birthday())
									: "--//--");
							// HERE LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL

							if (head_cst.getState() != 2) {
								lc1.setStyle("background-color:#dadada;");
								lc2.setStyle("background-color:#dadada;");
								if (head_cst.getState() == 1) {
								lc1.setContext(confirmPopup);
								lc2.setContext(confirmPopup);
								} else {
									lc1.setContext(editPopup);
									lc2.setContext(editPopup);
								}
								lc1.setAttribute("branch", head_cst.getBranch());
								lc1.setAttribute("client_id",
										head_cst.getId_client());
								lc1.setAttribute("alias", halias);
								lc2.setAttribute("branch", head_cst.getBranch());
								lc2.setAttribute("client_id",
										head_cst.getId_client());
								lc2.setAttribute("alias", halias);
							}

							row.appendChild(lc1);
							// row.appendChild(new
							// Listcell(pTclient.getSurname()));
							row.appendChild(lc2);
							bth.setAttribute("ho_cl", head_cst);
							btb.setAttribute("ho_cl", head_cst);
							btacc.setAttribute("ho_cl", head_cst);
							row.setAttribute("ho_cl", head_cst);
						} else {
							// btacc.setVisible(false);
							is_ho = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
					} else {
						// btacc.setVisible(false);
						is_ho = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						// row.appendChild(new Listcell(pTclient.getSurname()));
						row.appendChild(new Listcell("--//--"));
					}

					row.appendChild(h_edit_cell);

					if ((lnk.Branch_id != null))// &&(lnk.Branch == branch))
					{
						Customer branch_cst = com.is.customer.CustomerService
								.getCustomerById(lnk.Branch_id, branch, alias);
						// System.out.print("||branch:"+branch_cst.getName());
						row.appendChild(new Listcell(lnk.Branch_id));
						btedit.setAttribute("is_br", true);
						if (branch_cst != null) {
							btedit.setAttribute("br_cl", branch_cst);
							is_br = true;
							Listcell lc1 = new Listcell(
									branch_cst.getName() != null ? branch_cst
											.getName() : "--//--");
							Listcell lc2 = new Listcell((branch_cst
									.getP_birthday() != null) ? df
									.format(branch_cst.getP_birthday())
									: "--//--");

							// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
							if (branch_cst.getState() != 2) {
								lc1.setStyle("background-color:#dadada;");
								lc2.setStyle("background-color:#dadada;");
								if (branch_cst.getState() == 1) {
								lc1.setContext(confirmPopup);
								lc2.setContext(confirmPopup);
								} else {
									lc1.setContext(editPopup);
									lc2.setContext(editPopup);
									
								}
								
								lc1.setAttribute("branch",
										branch_cst.getBranch());
								lc1.setAttribute("client_id",
										branch_cst.getId_client());
								lc1.setAttribute("alias", alias);
								lc2.setAttribute("branch",
										branch_cst.getBranch());
								lc2.setAttribute("client_id",
										branch_cst.getId_client());
								lc2.setAttribute("alias", alias);
							}

							row.appendChild(lc1);
							// row.appendChild(new
							// Listcell(pTclient.getSurname()));
							row.appendChild(lc2);
							bth.setAttribute("br_cl", branch_cst);
							btb.setAttribute("br_cl", branch_cst);
							btacc.setAttribute("br_cl", branch_cst);
							row.setAttribute("br_cl", branch_cst);
						} else {
							btacc.setVisible(false);
							is_br = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
						// btb.setAttribute("cur_ho_id", lnk.Head_id);
					} else {
						btacc.setVisible(false);
						is_br = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						// row.appendChild(new Listcell(pTclient.getSurname()));
						row.appendChild(new Listcell("--//--"));
						// btb.setAttribute("cur_ho_id", "");
					}

					// btb.setAttribute("cur_branch", lnk.Branch);
					// btb.setAttribute("cur_tieto_id", lnk.Tieto_id);

					row.appendChild(b_edit_cell);
					// btb.setAttribute("open_in_new_branch",
					// lnk.Branch!=branch?1:0);
					// row.appendChild(new Listcell(lnk.Branch == branch ?
					// branch : "["+lnk.Branch+"]"));

					if (lnk.Cur_acc != null) {
						row.appendChild(new Listcell(lnk.Cur_acc));
						is_acc = true;
						if (is_ti && is_ho && is_br)
							row.setStyle("background-color:#D2F7CA; font-weight:bold;");// e0ffe0//96c58d
					} else {
						row.appendChild(new Listcell());
					}

				} else {
					btbreak.setVisible(false);
					btacc.setVisible(false);
					String pre_ho = CustomerService.get_HO_by_tieto(
							pTclient.getClient(), alias);
					if (pre_ho != null) {
						is_br = false;
						is_ho = true;

						String halias = CustomerService.get_alias_ho(alias);
						Customer head_cst = com.is.customer.CustomerService
								.getCustomerById(pre_ho,
										TclientService.getV_HO(),
										halias);
						// System.out.print("||head:"+head_cst.getName());
						row.appendChild(new Listcell(pre_ho));
						if (head_cst != null) {
							btedit.setAttribute("ho_cl", head_cst);
							row.appendChild(new Listcell(
									head_cst.getName() != null ? head_cst
											.getName() : "--//--"));
							// row.appendChild(new
							// Listcell(pTclient.getSurname()));
							row.appendChild(new Listcell(head_cst
									.getP_birthday() != null ? df
									.format(head_cst.getP_birthday())
									: "--//--"));
							bth.setAttribute("ho_cl", head_cst);
							btb.setAttribute("ho_cl", head_cst);
							btacc.setAttribute("ho_cl", head_cst);
						} else {
							is_ho = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}

						row.appendChild(h_edit_cell);

						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						btb.setAttribute("cur_branch", branch);
						btacc.setAttribute("cur_branch", branch);
						btb.setAttribute("cur_tieto_id", pTclient.getClient());
						btacc.setAttribute("cur_tieto_id", pTclient.getClient());
						row.appendChild(b_edit_cell);
						// row.appendChild(new Listcell("--//--"));
					} else {
						is_br = false;
						is_ho = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(h_edit_cell);

						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						btb.setAttribute("cur_branch", branch);
						btb.setAttribute("cur_tieto_id", pTclient.getClient());
						btacc.setAttribute("cur_branch", branch);
						btacc.setAttribute("cur_tieto_id", pTclient.getClient());
						row.appendChild(b_edit_cell);
						// row.appendChild(new Listcell("--//--"));
					}
					row.appendChild(new Listcell());
				}
				bth.setAttribute("is_ti", is_ti ? 1 : 0);
				bth.setAttribute("is_ho", is_ho ? 1 : 0);
				bth.setAttribute("is_br", is_br ? 1 : 0);
				btb.setAttribute("is_ti", is_ti ? 1 : 0);
				btb.setAttribute("is_ho", is_ho ? 1 : 0);
				btb.setAttribute("is_br", is_br ? 1 : 0);
				btacc.setAttribute("is_ti", is_ti ? 1 : 0);
				btacc.setAttribute("is_ho", is_ho ? 1 : 0);
				btacc.setAttribute("is_br", is_br ? 1 : 0);
				row.appendChild(acc_edit_cell);
				// if(is_ti)btt.setVisible(false);
				if (is_ho)
					bth.setVisible(false);
				if (is_br)
					btb.setVisible(false);
			}
		});

		pp = new IssuingPortProxy(ConnectionPool.getValue("TIETO_HOST", alias),
				ConnectionPool.getValue("TIETO_HOST_USERNAME", alias),
				ConnectionPool.getValue("TIETO_HOST_PASSWORD", alias));
		
		onClick$tbtn_search();
		

	} //-- doaftercompose end

	/*
	 * public void onPaging$customerPaging(ForwardEvent event){ final
	 * PagingEvent pe = (PagingEvent) event.getOrigin(); _startPageNumber =
	 * pe.getActivePage(); refreshModel(_startPageNumber); }
	 */

	
	private void refreshModel(int activePage) {

		used_ids = new HashMap<Integer, Integer>();
		String halias = CustomerService.get_alias_ho(alias);
		fl_filter_card_set = !CheckNull.isEmpty(card.getValue());

		if (!fl_filter_card_set)
			model = new com.is.customer.PagingListModel(activePage, _pageSize,
					filter, halias);

		if (!fl_filter_card_set)
			bmodel = new com.is.customer.PagingListModel(activePage, _pageSize,
					bfilter, alias);
		// if (!fl_filter_card_set) _totalSize = model.getTotalSize(filter,
		// alias);

		tmodel = new com.is.tieto.PagingListModel(activePage, _pageSize,
				tfilter);
		// _ttotalSize = tmodel.getTotalSize(tfilter, alias);

		bank_customers.setModel((ListModel) tmodel);

		if (!fl_filter_card_set)
			head_customers.setModel((ListModel) model);
		else {
			if (head_customers.getItemCount() > 0)
				head_customers.getItems().clear();
		}

		if (!fl_filter_card_set)
			branch_customers.setModel((ListModel) bmodel);
		else {
			if (branch_customers.getItemCount() > 0)
				branch_customers.getItems().clear();
		}

		if (!fl_filter_card_set)
			if (model.getSize() > 0) {
				sendSelEvt();
			}
		if (tmodel.getSize() > 0) {
			sendtSelEvt();
		}
	}

	public Customer getCurrent() {
		return current;
	}

	public void setCurrent(Customer current) {
		this.current = current;
	}

	public Customer getTcustomer() {
		return tcustomer;
	}

	public void setTcustomer(Customer tcustomer) {
		this.tcustomer = tcustomer;
	}

	public Tclient getTietocl() {
		return tietocl;
	}

	public void setTietocl(Tclient tietocl) {
		this.tietocl = tietocl;
	}

	/*
	 * public Tclient getAtcust() { return atcust; }
	 */

	public void setAtcust(Tclient atcust) {
		this.atcust = atcust;
	}

	public void onDoubleClick$bank_customers() {

		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		try {
		    str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tietocl);
	        } catch (Exception e22) {
		    str1 = " onDoubleClick$bank_customers=error tietocl";
	        } finally {
	        }
	        com.is.ISLogger.getLogger().error("** not err onDoubleClick$bank_customers tietocl  **************" + str1);

	        /*try {
			    str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tcust);
		        } catch (Exception e22) {
			    str1 = " onDoubleClick$bank_customers=error tcust";
		        } finally {
		        }
		        com.is.ISLogger.getLogger().error("** not err onDoubleClick$bank_customers tcust  **************" + str1);*/
		        
		tcust.setTieto_customer_id(tietocl.getClient());
		
		tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
	    
	        
		show_cards$accGrid.setModel(new BindingListModelList(TclientService
				.getAccInfo(tietocl.getClient()), false));
		show_cards.setVisible(true);
	}

	public void onDoubleClick$head_customers() {
		// if (!is_ti)return;
		if (tietocl == null)
			return;
		tcust.setTieto_customer_id(tietocl.getClient());
		tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
		show_cards$accGrid.setModel(new BindingListModelList(TclientService
				.getAccInfo(tietocl.getClient()), false));
		show_cards.setVisible(true);
	}

	public void onDoubleClick$branch_customers() {
		// if (!is_ti)return;
		if (tietocl == null)
			return;
		tcust.setTieto_customer_id(tietocl.getClient());
		tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
		show_cards$accGrid.setModel(new BindingListModelList(TclientService
				.getAccInfo(tietocl.getClient()), false));
		show_cards.setVisible(true);
	}

	private void set_cur_h_client() {
		refreshModel(_startPageNumber);
	}

	private void set_cur_t_client() {
		tcust.setTieto_customer_id(tietocl.getClient());

		refreshModel(_startPageNumber);
	}

	private void set_cur_b_client() {
		refreshModel(_startPageNumber);
	}

	private void sendtSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", bank_customers,
				bank_customers.getSelectedItems());
		Events.sendEvent(evt);
	}

	private void sendSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", bank_customers,
				bank_customers.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_searchb() {
		refreshModel(_startPageNumber);
	}

	public void onClick$tbtn_search() {
		filter.setP_birthday(tfilter.getB_date());
		filter.setName(tfilter.getSearch_name());
		filter.setP_first_name(tfilter.getF_names());
		filter.setP_family(tfilter.getSurname());
		filter.setId_client(tfilter.getClient_b());
		filter.setBranch(TclientService.getV_HO());
		bfilter = new CustomerFilter(filter);
		bfilter.setBranch(branch);
		refreshModel(_startPageNumber);
	}

	public void onClick$btn_add_everywhere() {
		add_ti = true;
		add_ho = true;
		add_br = true;

		// add_everywhere$acode_tel.setValue("");
		CheckNull.clearForm(add_everywhere$addgrdr);
		CheckNull.clearForm(add_everywhere$addgrdl);

		add_everywhere.setTitle("Открытие клиента [ГО] - [ФИЛИАЛ] - [TIETO]");
		add_everywhere$acode_resident.setSelecteditem("1");
		add_everywhere$ap_code_citizenship.setSelecteditem("860");
		add_everywhere$acode_country.setSelecteditem("860");

		add_everywhere.setVisible(true);
		fl_edit = false;
	}

	public void onClick$tbtn_add() {
		atcust = getTFromBank(current, atcust);
		atcust.setBank_c(addTieto$abank_c.getValue());
		alert("Открывается в Tieto : " + atcust.getSearch_name() + " l "
				+ addTieto$asearch_name.getValue());
		addTieto.setVisible(true);
	}

	public void onClick$btn_add() {
		tcustomer.setName(tietocl.getSearch_name());
		tcustomer.setBranch(branch);
		tcustomer.setCode_country("860");
		tcustomer.setP_code_class_credit("1");
		tcustomer.setP_passport_type("N");
		tcustomer.setCode_subject("P");
		tcustomer.setSign_registr(2);
		tcustomer.setCode_form("");
		tcustomer.setCode_type("08");
		// tcustomer.setP_passport_serial(ap_passport_serial.getValue());
		tcustomer.setP_passport_number(tietocl.getSerial_no());
		tcustomer.setP_first_name(tietocl.getF_names());
		tcustomer.setP_family(tietocl.getM_name());
		tcustomer.setP_birthday(tietocl.getB_date());
		sendSelEvt();
		tcustomer = getBFromTieto(tietocl, tcustomer);

		addCust.setVisible(true);

	}

	public void onClick$btn_addb() {

		tcustomer.setName(tietocl.getSearch_name());
		tcustomer.setBranch(branch);
		tcustomer.setCode_country("860");
		tcustomer.setP_code_class_credit("1");
		tcustomer.setP_passport_type("N");
		tcustomer.setCode_subject("P");
		tcustomer.setSign_registr(2);
		tcustomer.setCode_form("");
		tcustomer.setCode_type("08");
		// tcustomer.setP_passport_serial(ap_passport_serial.getValue());
		tcustomer.setP_passport_number(tietocl.getSerial_no());
		tcustomer.setP_first_name(tietocl.getF_names());
		tcustomer.setP_family(tietocl.getM_name());
		tcustomer.setP_birthday(tietocl.getB_date());
		sendSelEvt();
		addCust.setVisible(true);

	}

	public void onClick$tnbtn_add$addTieto() {

		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;

		RowType_Customer rtc = new RowType_Customer();
		rtc.setF_NAMES(current.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(current.getId_client());
		rtc.setSURNAME(current.getP_family());
		rtc.setM_NAME(current.getP_patronymic());
		Calendar cal = Calendar.getInstance();
		cal.setTime(current.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal);
		cal.setTime(current.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT("1");
		rtc.setSTATUS("10");
		rtc.setSEX("1");

		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);

		RowType_CustomerCustomInfo[] cci = {};
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();

		try {
			connectionInfo.setBANK_C(TclientService.getV_bankc());
			connectionInfo.setGROUPC(TclientService.getV_groupc());
			orInfo = pp.newCustomer(connectionInfo, customerInfo,
					customListInfo);

			alert(orInfo.getError_action() + "\r\n"
					+ orInfo.getError_description());
			System.out.println("resp " + orInfo.getResponse_code()
					+ "  client " + customerInfo.value.getCLIENT());

		} catch (RemoteException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addTieto.setVisible(false);
	}

	public void onClick$tnbtn_cancel$addTieto() {
		CheckNull.clearForm(addTieto$addtgrdl);
		CheckNull.clearForm(addTieto$addtgrdr);
		addTieto.setVisible(false);
	}

	public void onClick$bnbtn_add$addCust() {
		tcustomer.setName(tcustomer.getP_first_name() + " "
				+ tcustomer.getP_family());
		tcustomer.setP_code_class_credit("1");
		tcustomer.setP_passport_type("N");
		tcustomer.setCode_subject("P");
		tcustomer.setSign_registr(2);
		tcustomer.setCode_form("");
		tcustomer.setCode_type("08");
		tcustomer.setP_passport_serial(addCust$ap_passport_serial.getValue());
		tcustomer.setP_passport_number(addCust$ap_passport_number.getValue());

		Res res = CustomerService.doAction(session.getAttribute("un")
				.toString(), session.getAttribute("pwd").toString(), tcustomer,
				1, 2, alias, true);
		if (res.getCode() != 0) {
			alert(res.getName());
		} else {
			filter.setP_passport_number(tcustomer.getP_passport_number());
			filter.setP_birthday(tcustomer.getP_birthday());
			refreshModel(_startPageNumber);
			addCust.setVisible(false);
			alert("Клиент добавлен");
		}

	}

	public void onClick$bnbtn_cancel$addCust() {
		CheckNull.clearForm(addCust$addgrdl);
		CheckNull.clearForm(addCust$addgrdr);
		// addCust$dataGrid.getItems().clear();//.setModel(null);
		// lkdsjlf
		addCust.setVisible(false);
	}

	public void onClick$btn_bind() {
		CustomerService.create(tcust, alias);
		alert("Связаны банковский " + tcust.getHead_customer_id() + " и Тието "
				+ tcust.getTieto_customer_id());
	}

	public void onOK$ff_names() {
		onClick$tbtn_search();
	}

	public void onOK$fsearch_name() {
		onClick$tbtn_search();
	}

	public void onOK$fsurname() {
		onClick$tbtn_search();
	}

	public void onOK$fb_date() {
		onClick$tbtn_search();
	}

	public void onOK$fserial_no() {
		onClick$tbtn_search();
	}

	public void onOK$card() {
		onClick$tbtn_search();
	}

	public void onOK$fclient() {
		onClick$tbtn_search();
	}

	/*
	 * public void onClick$btn_cancel$printwnd(){ printwnd.setVisible(false); }
	 */

	public void onClick$btn_cancel$accwnd() {
		accwnd.setVisible(false);
	}

	public void onClick$btn_addacc$accwnd() {
		Account account = new Account();
		account.setBranch(current.getBranch());
		account.setAcc_bal("20206");
		account.setCurrency("840");
		account.setName(current.getName());
		account.setId_order("001");
		account.setSgn("P");
		account.setBal("B");
		account.setAcc_group_id(Integer.parseInt(ConnectionPool.getValue(
				"tieto_20206_acc", branch)));
		account.setSign_registr(2);
		account.setClient(current.getId_client());
		Res res = AccountService.doAction(un, pwd, account, 1, alias, true);
		if (res.getCode() == 0) {
			account.setId(res.getName());
			res = AccountService.doAction(un, pwd, account, 2, alias, true);
			alert(res.getName());
		} else {

			alert(res.getName());
		}

		accwnd$scurracc.setModel((new ListModelList(com.is.utilsti.RefDataService
				.getCurrAcc(current.getId_client(), alias))));

		// accwnd.setVisible(false);
		// printwnd.setVisible(true);
	}

	public void onClick$btn_print$accwnd() {
		printwnd.setVisible(true);
		accwnd.setVisible(false);
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("P_BRANCH", current.getBranch());
		params.put("P_CLIENT_ID", current.getId_client());
		params.put("P_ACCOUNT", accwnd$scurracc.getValue());
		printwnd$rpframe.setContent(DPrint.getRepPdf("TI_APPLICATION.pdf",
				application.getRealPath("reports/TI_APPLICATION.jasper"),
				params, alias));
	}

	public void onClick$btn_prn_app() {
		accwnd$scurracc.setModel((new ListModelList(com.is.utilsti.RefDataService
				.getCurrAcc(current.getId_client(), alias))));
		accwnd.setVisible(true);

	}

	public void onClick$btn_prn_cap() {
		if (tietocl == null) {
			alert("Клиент не выбран либо не объединен.");
			return;
		}
		printwnd.setVisible(true);

		// String cl_id = CustomerService.get_HO_by_tieto(tietocl.getClient(),
		// alias);

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PHONE_PASSWORD", "PASSWORD");
		params.put("CARD_TYPE", "VISA GOLD");
		params.put("P_BRANCH", branch);
		params.put("P_CLIENT_ID", tietocl.getClient());

		printwnd$rpframe.setContent(DPrint.getRepPdf(
				"TI_APPLICATION_VASA_CAP.pdf", application
						.getRealPath("reports/TI_APPLICATION_VASA_CAP.jasper"),
				params, alias));
	}

	public void onClick$btn_prn_prm() {
		printwnd.setVisible(true);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PHONE_PASSWORD", "PASSWORD");
		params.put("CARD_TYPE", "VISA GOLD");
		params.put("P_BRANCH", current.getBranch());
		params.put("P_CLIENT_ID", current.getId_client());

		printwnd$rpframe.setContent(DPrint.getRepPdf(
				"TI_DOGOVOR_VISA_PREMIUM.pdf", application
						.getRealPath("reports/TI_DOGOVOR_VISA_PREMIUM.jasper"),
				params, alias));
	}

	public void onSelect$ap_code_birth_region$addCust(Event evt) {
		addCust$ap_code_birth_distr.setSelectedIndex(-1);
		addCust$ap_code_birth_distr.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getDistr(
						addCust$ap_code_birth_region.getValue(), alias))));
	}

	public void onSelect$ap_code_adr_region$addCust(Event evt) {
		addCust$ap_code_adr_distr.setSelectedIndex(-1);
		addCust$ap_code_adr_distr.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getDistr(
						addCust$ap_code_adr_region.getValue(), alias))));
	}

	// accwnd$btn_print

	private Tclient getTFromBank(Customer cust, Tclient tcust) {

		tcust.setBank_c(TclientService.getV_bankc());
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
		// tcust.setStatus,
		tcust.setSearch_name(cust.getName());
		tcust.setSex(cust.getP_code_gender());
		tcust.setSerial_no(cust.getP_passport_number());
		tcust.setDoc_since(cust.getP_passport_date_registration());
		tcust.setIssued_by(cust.getP_passport_place_registration());

		addTieto$ab_date.setValue(tcust.getB_date());
		addTieto$aclient.setValue(tcust.getClient());
		addTieto$abank_c.setValue(tcust.getBank_c());
		addTieto$aclient_b.setValue(tcust.getClient_b());
		addTieto$acl_type.setValue(tcust.getCl_type());
		addTieto$acln_cat.setValue(tcust.getCln_cat());
		addTieto$af_names.setValue(tcust.getF_names());
		addTieto$asurname.setValue(tcust.getSurname());
		addTieto$atitle.setValue(tcust.getTitle());
		addTieto$am_name.setValue(tcust.getM_name());
		addTieto$ar_street.setValue(tcust.getR_street());
		addTieto$ar_city.setValue(tcust.getR_city());
		addTieto$ar_cntry.setValue(tcust.getR_cntry());
		addTieto$ausrid.setValue(tcust.getUsrid());
		// addTieto$actime.setValue(tcust.getCtime());
		addTieto$astatus.setValue(tcust.getStatus());
		addTieto$asearch_name.setValue(tcust.getSearch_name());
		addTieto$asex.setValue(tcust.getSex());
		addTieto$aserial_no.setValue(tcust.getSerial_no());
		addTieto$adoc_since.setValue(tcust.getDoc_since());
		addTieto$aissued_by.setValue(tcust.getIssued_by());
		return tcust;
	}

	private Customer getBFromTieto(Tclient tcust, Customer cust) {
		cust.setName(tcust.getSearch_name());
		cust.setBranch(branch);
		cust.setCode_country("860");
		cust.setP_code_class_credit("1");
		cust.setP_passport_type("N");
		cust.setCode_subject("P");
		cust.setSign_registr(2);
		cust.setCode_form("");
		cust.setCode_type("08");
		cust.setP_patronymic(tcust.getSurname());
		// cust.setP_passport_serial(ap_passport_serial.getValue());
		cust.setP_passport_number(tcust.getSerial_no());
		cust.setP_passport_serial(tcust.getSerial_no());
		cust.setP_passport_place_registration(tcust.getIssued_by());
		cust.setP_first_name(tcust.getF_names());
		cust.setP_family(tcust.getM_name());
		cust.setP_birthday(tcust.getB_date());
		cust.setP_post_address(tcust.getR_street());
		// System.out.println("cust : "+cust.getP_first_name()+" l "+cust.getP_family());
		if (!CheckNull.isEmpty(cust.getP_family())) {
			addCust$ap_family.setValue(cust.getP_family());
		}
		if (!CheckNull.isEmpty(cust.getP_first_name())) {
			addCust$ap_first_name.setValue(cust.getP_first_name());
		}
		if (!CheckNull.isEmpty(cust.getP_patronymic())) {
			addCust$ap_patronymic.setValue(cust.getP_patronymic());
		}
		if (!CheckNull.isEmpty(cust.getP_post_address())) {
			addCust$ap_post_address.setValue(cust.getP_post_address());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_serial())) {
			addCust$ap_passport_serial.setValue(cust.getP_passport_serial());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_number())) {
			addCust$ap_passport_number.setValue(cust.getP_passport_number());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_place_registration())) {
			addCust$ap_passport_place_registration.setValue(cust
					.getP_passport_place_registration());
		}

		return cust;
	}

	public void onClick$add_to_link$addCustomer() {

		String log = null;
		String cur_acc = null, cl_n = "";
		if (!add_ti && add_ho && !add_br) {
			if (is_br) {
				CustomerService.update_lnk_ho_by_br(branch,
						addCustomer$add_cst_id.getValue(),
						cur_branch_customer.getId_client(), alias);

				String halias = CustomerService.get_alias_ho(alias);
				Customer lg_c = CustomerService.getCustomerById(
						addCustomer$add_cst_id.getValue(),
						TclientService.getV_HO(), halias);
				if (lg_c.getName() != null) {
					cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
				}

				log = "Обновлена связка клиента с id в филиале ["
						+ cur_branch_customer.getId_client()
						+ "] для филиала [" + branch
						+ "]: выбран клиент в ГО ["
						+ addCustomer$add_cst_id.getValue() + "] [" + cl_n
						+ "]";
			} else if (is_ti) {
				CustomerService.update_lnk_ho_by_ti(branch,
						addCustomer$add_cst_id.getValue(), tietocl.getClient(),
						alias);

				String halias = CustomerService.get_alias_ho(alias);
				Customer lg_c = CustomerService.getCustomerById(
						addCustomer$add_cst_id.getValue(),
						TclientService.getV_HO(), halias);
				if (lg_c.getName() != null) {
					cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
				}

				log = "Обновлена связка клиента с tieto id ["
						+ tietocl.getClient() + "] для филиала [" + branch
						+ "]: выбран клиент в ГО ["
						+ addCustomer$add_cst_id.getValue() + "] [" + cl_n
						+ "]";
			}
			// Customer ncr =
			// com.is.customer.CustomerService.getCustomerById(addCustomer$add_cst_id.getValue(),
			// branch, alias);
			// cur_acc = AccountService.doAction_create_acc_in_br(un, pwd,
			// "20206", addCustomer$add_cst_id.getValue(), "840", null,
			// (ncr.getName().length()>80?ncr.getName().substring(0,
			// 79):ncr.getName()), alias);
		}
		if (!add_ti && !add_ho && add_br) {
			if (is_ho) {
				CustomerService.update_lnk_br_by_ho(branch,
						addCustomer$add_cst_id.getValue(),
						cur_HO_customer.getId_client(), alias);

				// String halias = CustomerService.get_alias_ho(alias);
				Customer lg_c = CustomerService.getCustomerById(
						addCustomer$add_cst_id.getValue(), branch, alias);
				if (lg_c.getName() != null) {
					cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
				}

				log = "Обновлена связка клиента с id в ГО ["
						+ cur_HO_customer.getId_client() + "] для филиала ["
						+ branch + "]: установлен клиент в филиале ["
						+ addCustomer$add_cst_id.getValue() + "] [" + cl_n
						+ "]";
			} else if (is_ti) {
				CustomerService.update_lnk_br_by_ti(branch,
						addCustomer$add_cst_id.getValue(), tietocl.getClient(),
						alias);

				Customer lg_c = CustomerService.getCustomerById(
						addCustomer$add_cst_id.getValue(), branch, alias);
				if (lg_c.getName() != null) {
					cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
				}

				log = "Обновлена связка клиента с tieto id ["
						+ tietocl.getClient() + "] для филиала [" + branch
						+ "]: установлен id в филиале ["
						+ addCustomer$add_cst_id.getValue() + "] [" + cl_n
						+ "]";
			}
		}

		UserService
				.UsrLog(new UserActionsLog(uid, un, curip, 7, 1, log, branch));

		addCustomer.setVisible(false);

		CheckNull.clearForm(addCust$addgrdl);
		CheckNull.clearForm(addCust$addgrdr);
		addCustomer$dataGrid.getItems().clear();// .setModel(null);
		// lkdsjlf
		// addCust.setVisible(false);

		refreshModel(_starttPageNumber);
	}

	public void onClick$close$addCustomer() {
		CheckNull.clearForm(addCust$addgrdl);
		CheckNull.clearForm(addCust$addgrdr);
		addCustomer$dataGrid.getItems().clear();// .setModel(null);
		// lkdsjlf
		// addCust.setVisible(false);
		addCustomer.setVisible(false);
	}

	public void onClick$close_btn$add_everywhere() {
		add_everywhere.setVisible(false);
		fl_edit = false;
	}

	public void onClick$add_btn$add_everywhere() throws SAPException,
			RemoteException {
		boolean fl_err = false;
		String err = "";

		if ((!((add_everywhere$ap_passport_number.getValue()).matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_passport_number.getValue().length() > 9)) {
			fl_err = true;
			err += "\nНомер паспорта";
		}
		if ((!((add_everywhere$ap_passport_serial.getValue()).matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_passport_serial.getValue().length() > 9)) {
			fl_err = true;
			err += "\nСерия паспорта";
		}
		if ((!((add_everywhere$ap_passport_place_registration.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")))
				|| (add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200)) {
			fl_err = true;
			err += "\nМесто регистрации паспорта";
		}
		if ((!((add_everywhere$ap_family.getValue()).matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_family.getValue().length() > 34)) {
			fl_err = true;
			err += "\nФамилия";
		}
		if ((!((add_everywhere$ap_first_name.getValue()).matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_first_name.getValue().length() > 20)) {
			fl_err = true;
			err += "\nИмя";
		}
		if ((!((add_everywhere$ap_patronymic.getValue()).matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_patronymic.getValue().length() > 20)) {
			fl_err = true;
			err += "\nОтчество";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_type_document.getValue()))) {
			fl_err = true;
			err += "\nТип документа";
		}
		if ((!((add_everywhere$ap_number_tax_registration.getValue()).matches("[0-9]*")))
				|| (add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9)) {
			fl_err = true;
			err += "\nИНН";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue()))) {
			fl_err = true;
			err += "\nГражданство";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_country.getValue()))) {
			fl_err = true;
			err += "\nСтрана";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_resident.getValue()))) {
			fl_err = true;
			err += "\nРезидент";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue()))) {
			fl_err = true;
			err += "\nРегион проживания";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue()))) {
			fl_err = true;
			err += "\nРайон проживания";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue()))) {
			fl_err = true;
			err += "\nДата регистрации паспорта";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_birthday.getValue()))) {
			fl_err = true;
			err += "\nДата рождения";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_tel.getValue()))) {
			fl_err = true;
			err += "\nПароль для телефонных разговоров";
		}
		if ((!((add_everywhere$ap_post_address.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")))
				|| (add_everywhere$ap_post_address.getValue().length() > 95)) {
			fl_err = true;
			err += "\nПочтовый адрес";
		}
		if ((add_everywhere$ap_birth_place.getValue() == null || add_everywhere$ap_birth_place
				.getValue().length() == 0)
				|| (!((add_everywhere$ap_birth_place.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*")))
				|| (add_everywhere$ap_birth_place.getValue().length() > 200)) {
			fl_err = true;
			err += "\nМесто рождения";
		}
		if ((!((add_everywhere$ap_phone_mobile.getValue()).matches("998[0-9]*")))
				|| (add_everywhere$ap_phone_mobile.getValue().length() != 12)) {
			fl_err = true;
			err += "\nНомер телефона. 998*********";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		String cur_acc = null;
		Customer new_customer = new Customer();

		new_customer.setP_passport_number(add_everywhere$ap_passport_number.getValue());
		new_customer.setP_passport_type(add_everywhere$ap_type_document.getValue());
		new_customer.setP_type_document(add_everywhere$ap_type_document.getValue());
		new_customer.setP_passport_serial(add_everywhere$ap_passport_serial.getValue());
		new_customer.setP_passport_place_registration(add_everywhere$ap_passport_place_registration.getValue());
		new_customer.setP_family(add_everywhere$ap_family.getValue());
		new_customer.setP_first_name(add_everywhere$ap_first_name.getValue());
		new_customer.setName(add_everywhere$ap_family.getValue() + " "
				+ add_everywhere$ap_first_name.getValue() + " "
				+ add_everywhere$ap_patronymic.getValue());
		new_customer.setP_birthday(add_everywhere$ap_birthday.getValue());
		new_customer.setCode_country(add_everywhere$acode_country.getValue());
		new_customer.setCode_resident(add_everywhere$acode_resident.getValue());
		new_customer.setP_post_address(add_everywhere$ap_post_address
				.getValue());

		new_customer.setAcode_tel(add_everywhere$acode_tel.getValue());
		new_customer.setP_patronymic(add_everywhere$ap_patronymic.getValue());

		new_customer.setP_family_local(add_everywhere$ap_family.getValue());
		new_customer.setP_first_name_local(add_everywhere$ap_first_name.getValue());
		new_customer.setP_patronymic_local(add_everywhere$ap_patronymic.getValue());
						
		new_customer.setP_passport_date_expiration(add_everywhere$ap_passport_date_expiration.getValue());
		new_customer.setP_passport_date_registration(add_everywhere$ap_passport_date_registration.getValue());
		new_customer.setP_code_birth_region(CheckNull
				.isEmpty(add_everywhere$ap_code_birth_region.getValue()) ? null
				: add_everywhere$ap_code_birth_region.getValue());
		new_customer.setP_code_birth_distr(add_everywhere$ap_code_birth_distr.getValue());
		new_customer.setP_birth_place(add_everywhere$ap_birth_place.getValue());
		new_customer.setP_code_gender(add_everywhere$ap_code_gender.getValue());
		new_customer.setP_code_nation(add_everywhere$ap_code_nation.getValue());
		new_customer.setP_code_adr_region(add_everywhere$ap_code_adr_region.getValue());
		new_customer.setP_code_adr_distr(add_everywhere$ap_code_adr_distr.getValue());
		new_customer.setP_code_tax_org(add_everywhere$ap_code_tax_org.getValue());
		new_customer.setP_number_tax_registration(add_everywhere$ap_number_tax_registration.getValue());
		new_customer.setP_code_citizenship(add_everywhere$ap_code_citizenship.getValue());
		new_customer.setP_phone_mobile(add_everywhere$ap_phone_mobile.getValue());
		new_customer.setP_email_address(add_everywhere$ap_email_address.getValue());
		new_customer.setP_phone_home(add_everywhere$ap_phone_home.getValue());
		new_customer.setP_inps(add_everywhere$ap_inps.getValue());
		new_customer.setP_pinfl(add_everywhere$ap_pinfl.getValue());

		new_customer.setBranch(TclientService.getV_HO());
		new_customer.setP_code_bank(TclientService.getV_HO());

		new_customer.setP_code_class_credit("1");
		new_customer.setP_passport_type("N");
		new_customer.setCode_subject("P");
		new_customer.setSign_registr(2);
		new_customer.setCode_form("");
		new_customer.setCode_type("08");

		if (!fl_edit) { // новый клиент
			String HO_id = cur_HO_customer != null ? cur_HO_customer.getId_client() : null;
			String Branch_id = cur_branch_customer != null ? cur_branch_customer.getId_client() : null;
			String Tieto_id = tietocl != null ? tietocl.getClient() : null;

			// //////////////////////////////// OPEN IN HO
			// //////////////////////////////

			if (add_ho) {
				String halias = CustomerService.get_alias_ho(alias);
				
				Res res = CustomerService.doAction(session.getAttribute("un").toString(),
							session.getAttribute("pwd").toString(),
							new_customer, 1, 0, halias, branch.compareTo(TclientService.getV_HO()) == 0);

				if (res.getCode() != 0) {
					alert("ОШИБКА\nОткрытие клиента в ГО:\n" + res.getName());
					fl_edit = false;
					return;
				} else {
					alert("Клиент добавлен(ГО): " + res.getName());
					Customer cst = com.is.customer.CustomerService
							.getCustomerById_tbl(res.getName(),
									TclientService.getV_HO(),
									halias);
					HO_id = cst.getId_client();
					UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
							"Открыт клиент в ГО id [" + HO_id + "]", branch));

					if ((!add_br) && (!add_ti)) {
						refreshModel(_starttPageNumber);
						add_everywhere.setVisible(false);
						fl_edit = false;
						return;
					}

					// отправка клиента в SAP 2019-01-22
					// Customer_ReqestOutProxy pp = new
					// Customer_ReqestOutProxy("customerEndpoint", "_username",
					// "_password");
                    
					String uurrll = ConnectionPool.getValue("SAP_WS_ENDPOINT",
							alias);
					uurrll = uurrll.replace("Organization_ReqestOut",
							"Customer_ReqestOut");
					LtLogger.getLogger().error(
							"** not err sap 00 replaced to: " + uurrll);

					Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(
							uurrll, ConnectionPool.getValue(
									"SAP_WS_ENDPOINT_USERNAME", alias),
							ConnectionPool.getValue("SAP_WS_ENDPOINT_PASSWORD",
									alias));
					Customer_ReqestOut ro = pp.getCustomer_ReqestOut();

					LtLogger.getLogger().error("** not err sap 1.");
					BusinessPartnerContent cn = mapCustomerToContent(cst,
							branch);
					cn.setOperation("1");
					cn.setOperation_origin("2");
					BusinessParnerComplex comp_cn = new BusinessParnerComplex(
							cn, null, null);
					BusinessPartnerResponce resp = ro.customer_Reqest(comp_cn);
					BAPIRET2[] responses = resp.getMessages();
					
					String str1 = "";
					ObjectMapper objectMapper = new ObjectMapper();
					try {
					 str1 =
					objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responses);
					} catch (Exception e22) {
					str1 = " str1=error responses1";
					} finally {
					}
					LtLogger.getLogger().error( "** not err responses1  ************** " + str1);

					try {
						parseExceptions(responses);
						LtLogger.getLogger().error("** not err sap 4.1.");
					} catch (I014Exception e) {
						cst.setI014(true);	
						LtLogger.getLogger().error("** not err sap 4.2.");
					}
					BusinessPartnerResponceHeader header = resp.getHeader();
					if (header.getId_client_sap() == null)
					{
						//удалить клиента
						new_customer.setId_client(cst.getId_client());
						new_customer.setId(cst.getId());
						res = CustomerService.doAction_utv(session.getAttribute("un").toString(),
								session.getAttribute("pwd").toString(),
								new_customer, 6, halias, branch.compareTo(TclientService.getV_HO()) == 0);
					    if (res.getCode() != 0) {
					    	//alert("ОШИБКА\nУдаление клиента в ГО:\n" + res.getName());
							throw new SAPException(
							"\n1.SAP CRM Id has not been assigned \n2.Удаление клиента в ГО:\n" + res.getName());
					    } 	
						throw new SAPException(
						"\nSAP CRM Id has not been assigned");
					}
					cst.setIdSap(header.getId_client_sap());
					
					//утвердить клиента
					new_customer.setId_client(cst.getId_client());
					new_customer.setId(cst.getId());
					res = CustomerService.doAction_utv(session.getAttribute("un").toString(),
							session.getAttribute("pwd").toString(),
							new_customer, 2, halias, branch.compareTo(TclientService.getV_HO()) == 0);
				    if (res.getCode() != 0) {
					   alert("ОШИБКА\nУтверждение клиента в ГО:\n" + res.getName());
					   fl_edit = false;
					   return;
				    } 	
				}
			}  // if (add_ho)

			new_customer.setBranch(branch);
			new_customer.setP_code_bank(branch);

			// ////////////////////////////// OPEN IN BRANCH   // ////////////////////////

			if (add_br) {
				if (branch.compareTo(TclientService.getV_HO()) != 0) {  // branch!=GolovnayaSxema
					Res res = CustomerService.doAction(
							session.getAttribute("un").toString(), session.getAttribute("pwd").toString(),
							new_customer, 1, 0, alias, true);

					if (res.getCode() != 0) {
						alert("ОШИБКА\nОткрытие клиента в филиале:\n"
								+ res.getName());
						fl_edit = false;
						return;
					} else {
						alert("Клиент добавлен(филиал): " + res.getName());
						Customer cst = com.is.customer.CustomerService
								.getCustomerById_tbl(res.getName(), branch,
										alias);
						Branch_id = cst.getId_client();
						UserService.UsrLog(new UserActionsLog(uid, un, curip,
								7, 1, "Открыт клиент в филиале [" + branch
										+ "] id [" + Branch_id + "]", branch));

						// отправка клиента в SAP 2019-06-27
						// Customer_ReqestOutProxy pp = new
						// Customer_ReqestOutProxy("customerEndpoint",
						// "_username", "_password");
						
						String uurrll = ConnectionPool.getValue("SAP_WS_ENDPOINT", alias);
						uurrll = uurrll.replace("Organization_ReqestOut",	"Customer_ReqestOut");
						LtLogger.getLogger().error(	"** not err sap f00 replaced to: " + uurrll);

						Customer_ReqestOutProxy pp = new Customer_ReqestOutProxy(
								uurrll, ConnectionPool.getValue(
										"SAP_WS_ENDPOINT_USERNAME", alias),
								ConnectionPool.getValue(
										"SAP_WS_ENDPOINT_PASSWORD", alias));
						Customer_ReqestOut ro = pp.getCustomer_ReqestOut();

						LtLogger.getLogger().error("** not err sap f1.");
						BusinessPartnerContent cn = mapCustomerToContent(cst,	branch);
						cn.setOperation("1");
						cn.setOperation_origin("2");
						BusinessParnerComplex comp_cn = new BusinessParnerComplex(cn, null, null);
						BusinessPartnerResponce resp = ro
								.customer_Reqest(comp_cn);
						BAPIRET2[] responses = resp.getMessages();
						
						try {
							parseExceptions(responses);
							LtLogger.getLogger().error("** not err sap f4.1.");
						} catch (I014Exception e) {
							cst.setI014(true);
							LtLogger.getLogger().error("** not err sap f4.2.");
						}
						BusinessPartnerResponceHeader header = resp.getHeader();
						if (header.getId_client_sap() == null) {
							//удалить клиента
							new_customer.setId_client(cst.getId_client());
							new_customer.setId(cst.getId());
							res = CustomerService.doAction_utv(
									session.getAttribute("un").toString(), session.getAttribute("pwd").toString(),
									new_customer, 6, alias, true);

							if (res.getCode() != 0) {
								//alert("ОШИБКА\nУдаление клиента в филиале:\n"
								//		+ res.getName());
								throw new SAPException(
								"\n1.SAP CRM Id has not been assigned. \n2.Удаление клиента в филиале:\n"+res.getName());
							}
							throw new SAPException(
							"\nSAP CRM Id has not been assigned");
							
						}
						cst.setIdSap(header.getId_client_sap());
						LtLogger.getLogger().error("** not err sap f5.");

						//утвердить клиента
						new_customer.setId_client(cst.getId_client());
						new_customer.setId(cst.getId());
						res = CustomerService.doAction_utv(
								session.getAttribute("un").toString(), session.getAttribute("pwd").toString(),
								new_customer, 2, alias, true);

						if (res.getCode() != 0) {
							alert("ОШИБКА\nУтверждение клиента в филиале:\n"
									+ res.getName());
							fl_edit = false;
							return;
						}
					}

				} else {
					Branch_id = HO_id;
				}
				// ///////////////////////////// CREATE ACCOUNT	// ///////////////////////////

				Customer ncr = com.is.customer.CustomerService.getCustomerById(
						Branch_id, branch, alias);

				Res res = AccountService.doAction_create_acc_in_br(un, pwd,
						"20206", Branch_id, "840", null, (ncr.getName()
								.length() > 80 ? ncr.getName().substring(0, 79)
								: ncr.getName()), 101, alias, branch, true);

				if (res.getCode() != 0) {
					alert("ОШИБКА\nОткрытие счета 20206 в Филиале:\n"
							+ res.getName());
					return;
				}
				cur_acc = res.getName();
				// = AccountService.doAction_create_acc_in_br(un, pwd, "20206",
				// Branch_id, "840", null,
				// (ncr.getName().length()>80?ncr.getName().substring(0,
				// 79):ncr.getName()), 101, alias, branch, true);

				UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
						"Открыт счет [" + cur_acc + "] в филиале [" + branch
								+ "] для клиента id [" + Branch_id + "]",
						branch));

				if (!add_ti) {
					refreshModel(_starttPageNumber);
					add_everywhere.setVisible(false);
					fl_edit = false;
					return;
				}
				if ((branch.compareTo(TclientService.getV_HO()) == 0)
						&& add_br && add_ho) {
					Branch_id = HO_id;
				}

			} // if (add_br) 

			// ///////////////////////// ADD TO TIETO 	// //////////////////////////////

			if (add_ti) {
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				OperationResponseInfo orInfo = null;

				RowType_Customer rtc = new RowType_Customer();
				Calendar cal_p = Calendar.getInstance();
				Calendar cal = Calendar.getInstance();

				rtc.setF_NAMES(new_customer.getP_first_name());
				rtc.setCL_TYPE("1");
				if (!add_ho)
					rtc.setCLIENT_B(cur_HO_customer.getId_client());
				else
					rtc.setCLIENT_B(HO_id);
				rtc.setSURNAME(new_customer.getP_family());
				rtc.setM_NAME(new_customer.getP_patronymic());
				cal_p.setTime(new_customer.getP_passport_date_registration());
				rtc.setDOC_SINCE(cal_p);
				cal.setTime(new_customer.getP_birthday());
				rtc.setB_DATE(cal);
				rtc.setRESIDENT(new_customer.getCode_resident());
				rtc.setSTATUS("10");
				rtc.setSEX(new_customer.getP_code_gender());
				rtc.setSERIAL_NO(new_customer.getP_passport_serial());
				rtc.setID_CARD(new_customer.getP_passport_number());
				
				rtc.setR_CITY(add_everywhere$ar_city.getValue());
				rtc.setR_STREET(add_everywhere$ap_post_address.getValue());
				rtc.setR_E_MAILS(add_everywhere$ap_email_address.getValue());
				rtc.setR_MOB_PHONE(add_everywhere$ap_phone_mobile.getValue());
				rtc.setR_PHONE(add_everywhere$ap_phone_home.getValue());
				rtc.setR_CNTRY((add_everywhere$acode_country.getValue()
						.compareTo("860") == 0) ? "UZB" : null);
				rtc.setISSUED_BY(add_everywhere$ap_passport_place_registration
						.getValue());
				rtc.setPERSON_CODE(add_everywhere$acode_tel.getValue());
		
				rtc.setDOC_TYPE("001");

				RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(
						rtc);

				RowType_CustomerCustomInfo[] cci = {};
				ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();

				try {
					connectionInfo.setBANK_C(TclientService.getV_bankc());
					connectionInfo.setGROUPC(TclientService.getV_groupc());

					orInfo = pp.newCustomer(connectionInfo, customerInfo,
							customListInfo);

					if (orInfo.getError_description() != null) {
						alert(orInfo.getError_action() + "\r\n"
								+ orInfo.getError_description());
						System.out.println("resp " + orInfo.getResponse_code()
								+ "  client " + customerInfo.value.getCLIENT());
					} else {
						alert("Клиент добавлен (tieto)");
						Tieto_id = customerInfo.value.getCLIENT();
						UserService.UsrLog(new UserActionsLog(uid, un, curip,
								7, 1, "Открыт клиент в tieto id [" + Tieto_id
										+ "]", branch));
					}

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					LtLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
				}
				fl_edit = false;

			}  // if (add_ti)

			// ////////////////////////////// CREATE A LINK  // ////////////////////////////
			if (add_ti && add_ho && add_br) {
				CustomerService.create_lnk(branch, HO_id, Branch_id, Tieto_id,
						cur_acc, alias);
				UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
						"Создана связка id в ГО [" + HO_id + "] филиал ["
								+ Branch_id + "] тието [" + Tieto_id
								+ "] счет [" + cur_acc + "] для филиала ["
								+ branch + "]", branch));
			}

			if (add_ti && !add_ho && !add_br) {

				Tclient lg_tcl = TclientService.getTclient_by_id(Tieto_id);
				String tcl_name = lg_tcl.getF_names() + " "
						+ lg_tcl.getSurname() + " " + lg_tcl.getB_date();

				if (is_ho && is_br) {
					CustomerService.update_lnk_ti_by_ho(branch,
							cur_HO_customer.getId_client(), Tieto_id, alias);

					UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
							"Обновлена связка id в ГО ["
									+ cur_HO_customer.getId_client()
									+ "] для филиала [" + branch
									+ "]: выбран клиент tieto [" + Tieto_id
									+ "] [" + tcl_name + "]", branch));
				}
				if (is_ho && !is_br) {
					CustomerService.update_lnk_ti_by_ho(branch,
							cur_HO_customer.getId_client(), Tieto_id, alias);

					UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
							"Обновлена связка id в ГО ["
									+ cur_HO_customer.getId_client()
									+ "] для филиала [" + branch
									+ "]: выбран клиент tieto [" + Tieto_id
									+ "] [" + tcl_name + "]", branch));
				}
				if (!is_ho && is_br) {
					CustomerService.update_lnk_ti_by_br(branch,
									cur_branch_customer.getId_client(),
									Tieto_id, alias);

					UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
							"Обновлена связка id в филиале ["
									+ cur_branch_customer.getId_client()
									+ "] для филиала [" + branch
									+ "]: выбран клиент id tieto [" + Tieto_id
									+ "] [" + tcl_name + "]", branch));
				}
			}
		} // конец новый клиента 
		else 
		{ // корректировать сушествуюшего клиента
			Boolean tmp_edit_ho = edit_ho;
			if (edit_ho) {
				new_customer.setBranch(TclientService.getV_HO());
				new_customer.setId(cur_HO_customer.getId());
				new_customer.setId_client(cur_HO_customer.getId_client());

				String halias = CustomerService.get_alias_ho(alias);
				Res res = CustomerService
						.doAction(session.getAttribute("un").toString(),
								session.getAttribute("pwd").toString(),
								new_customer, 19, 0, halias, branch
										.compareTo(TclientService.getV_HO()) == 0);
				if (res.getCode() != 0) {
					alert(res.getName());
					return;
				}
				edit_ho = false;
				alert("Клиент отредактирован(ГО):");
				UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
						"Отредактирован клиент МФО ["
								+ TclientService.getV_HO()
								+ "], ID клиента ["
								+ cur_HO_customer.getId_client() + "] ["
								+ new_customer.getName() + "]", branch));
				// add_everywhere.setVisible(false);
			}
			if (edit_br) {
				new_customer.setBranch(branch);
				new_customer.setId(cur_branch_customer.getId());
				new_customer.setId_client(cur_branch_customer.getId_client());

				// String halias = CustomerService.get_alias_ho(alias);
				Res res = CustomerService.doAction(session.getAttribute("un")
						.toString(), session.getAttribute("pwd").toString(),
						new_customer, 19, 0, alias, true);
				if (res.getCode() != 0) {
					alert(res.getName());
					return;
				}
				// fl_edit = false;
				edit_br = false;
				alert("Клиент отредактирован(филиал):");
				UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
						"Отредактирован клиент МФО [" + branch
								+ "], ID клиента ["
								+ cur_HO_customer.getId_client() + "] ["
								+ new_customer.getName() + "]", branch));

			}
			if (edit_ti) {
				OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
				OperationResponseInfo orInfo = null;

				RowType_Customer rtc = new RowType_Customer();
				Calendar cal_p = Calendar.getInstance();
				Calendar cal = Calendar.getInstance();

				rtc.setF_NAMES(new_customer.getP_first_name());
				rtc.setCL_TYPE("1");
				rtc.setCLIENT_B(tietocl.getClient_b());
				// rtc.setCLIENT_B(new_customer.getId_client());
				rtc.setSURNAME(new_customer.getP_family());
				rtc.setM_NAME(new_customer.getP_patronymic());
				cal_p.setTime(new_customer.getP_passport_date_registration());
				rtc.setDOC_SINCE(cal_p);
				cal.setTime(new_customer.getP_birthday());
				rtc.setB_DATE(cal);
				rtc.setRESIDENT(new_customer.getCode_resident());
				rtc.setSTATUS("10");
				rtc.setSEX(new_customer.getP_code_gender());
				rtc.setSERIAL_NO(new_customer.getP_passport_serial());
				rtc.setID_CARD(new_customer.getP_passport_number());
				rtc.setCLIENT(tietocl.getClient());
				// rtc.set

				rtc.setR_CITY(add_everywhere$ar_city.getValue());
				rtc.setR_STREET(add_everywhere$ap_post_address.getValue());
				rtc.setR_E_MAILS(add_everywhere$ap_email_address.getValue());
				rtc.setR_MOB_PHONE(add_everywhere$ap_phone_mobile.getValue());
				rtc.setR_PHONE(add_everywhere$ap_phone_home.getValue());
				rtc.setR_CNTRY((add_everywhere$acode_country.getValue()
						.compareTo("860") == 0) ? "UZB" : null);
				rtc.setISSUED_BY(add_everywhere$ap_passport_place_registration
						.getValue());
				rtc.setPERSON_CODE(add_everywhere$acode_tel.getValue());
				// rtc.set

				rtc.setDOC_TYPE("001");

				// RowType_CustomerHolder customerInfo = new
				// RowType_CustomerHolder(rtc);
				RowType_EditCustomer_Request customerRequest = new RowType_EditCustomer_Request(
						rtc.getCLIENT(), TclientService.getV_bankc(), rtc.getCLIENT_B(), rtc.getCL_TYPE(),
						rtc.getCLN_CAT(), rtc.getREC_DATE(), rtc.getF_NAMES(),
						rtc.getSURNAME(), rtc.getTITLE(), rtc.getM_NAME(),
						rtc.getB_DATE(), rtc.getRESIDENT(), rtc.getID_CARD(),
						rtc.getDOC_TYPE(), rtc.getR_PHONE(), rtc.getEMP_NAME(),
						rtc.getPOSITION(), rtc.getEMP_DATE(),
						rtc.getWORK_PHONE(), rtc.getR_STREET(),
						rtc.getR_CITY(), rtc.getR_CNTRY(), rtc.getR_PCODE(),
						rtc.getC_SINCE(), rtc.getCMP_NAME(),
						rtc.getCMPG_NAME(), rtc.getCO_POSITON(),
						rtc.getCONTACT(), rtc.getCNT_TITLE(),
						rtc.getCNT_PHONE(), rtc.getCNT_FAX(),
						rtc.getMNG_POSIT(), rtc.getMNG_NAME(),
						rtc.getMNG_PHONE(), rtc.getMNG_TITLE(),
						rtc.getMNG_FAX(), rtc.getREG_NR(), rtc.getCR_STREET(),
						rtc.getCR_CITY(), rtc.getCR_CNTRY(), rtc.getCR_PCODE(),
						rtc.getCOMENT(), rtc.getREGION(), rtc.getPERSON_CODE(),
						rtc.getRESIDENT_SINCE(), rtc.getYEAR_INC(),
						rtc.getCCY_FOR_INCOM(), rtc.getIMM_PROP_VALUE(),
						rtc.getSEARCH_NAME(), rtc.getMARITAL_STATUS(),
						rtc.getCO_CODE(), rtc.getEMP_CODE(), rtc.getSEX(),
						rtc.getSERIAL_NO(), rtc.getDOC_SINCE(),
						rtc.getISSUED_BY(), rtc.getEMP_ADR(), rtc.getEMP_FAX(),
						rtc.getEMP_E_MAILS(), rtc.getR_E_MAILS(),
						rtc.getR_MOB_PHONE(), rtc.getR_FAX(),
						rtc.getCNT_E_MAILS(), rtc.getCNT_MOB_PHONE(),
						rtc.getMNG_MOB_PHONE(), rtc.getMNG_E_MAILS(),
						rtc.getCR_E_MAILS(), rtc.getIN_FILE_NUM(),
						rtc.getU_COD1(), rtc.getU_COD2(), rtc.getU_COD3(),
						rtc.getU_FIELD1(), rtc.getU_FIELD2(), rtc.getCALL_ID(),
						rtc.getHOME_NUMBER(), rtc.getBUILDING(),
						rtc.getSTREET1(), rtc.getAPARTMENT(),
						rtc.getMIDLE_NAME(), rtc.getSTATUS(), null,
						rtc.getAMEX_MEMBER_SINCE(), rtc.getDCI_MEMBER_SINCE(),
						rtc.getREWARD_NO());
				RowType_CustomerCustomInfo[] cci = {};
				ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();

				try {
					connectionInfo.setBANK_C(TclientService.getV_bankc());
					connectionInfo.setGROUPC(TclientService.getV_groupc());

					orInfo = pp.editCustomer(connectionInfo, customerRequest);
					// .newCustomer(connectionInfo, customerInfo,
					// customListInfo);

					if (orInfo.getError_description() != null) {
						alert(orInfo.getError_action() + "\r\n"
								+ orInfo.getError_description());
						System.out.println("resp " + orInfo.getResponse_code()
								+ "  client " + rtc.getCLIENT());
					} else {
						alert("Клиент отредактирован (Tieto)");
						String Tieto_id = rtc.getCLIENT();
						UserService.UsrLog(new UserActionsLog(uid, un, curip,
								7, 1, "Отредактирован клиент  в TIETO ["
										+ rtc.getCLIENT() + "] ["
										+ rtc.getSURNAME() + " "
										+ rtc.getF_NAMES() + " "
										+ rtc.getM_NAME() + "]", branch));
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					LtLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
					alert(e.getMessage());
				}
			}
		}// конец корректировка

		fl_edit = false;
		refreshModel(_starttPageNumber);
		add_everywhere.setVisible(false);
	}

	protected BusinessPartnerContent mapCustomerToContent(Customer customer,
			String userBranch) {
		BusinessPartnerContent content = new BusinessPartnerContent();
		content.setBranch(customer.getBranch());
		// content.setId_client(Long.toString(customer.getId()));
		content.setId_client(customer.getId_client());
		content.setId_client_sap(customer.getIdSap());
		// content.setName(customer.getName() != null ?
		// customer.getName().toUpperCase() : customer.getName());
		content.setCode_country(customer.getCode_country());
		// content.setCode_form(customer.getCode_form());
		content.setCode_resident(customer.getCode_resident());
		// content.setCode_subject(customer.getCode_subject());
		// content.setCode_type(customer.getCode_type());
		// content.setDate_open(customer.getDate_open());
		// content.setDate_close(customer.getDate_close());
		content.setP_birth_place(customer.getP_birth_place() != null ? customer
				.getP_birth_place().toUpperCase() : customer.getP_birth_place());
		content.setP_birthday(customer.getP_birthday());
		content.setP_capacity_status_date(customer.getP_capacity_status_date());
		content.setP_capacity_status_place(customer
				.getP_capacity_status_place() != null ? customer
				.getP_capacity_status_place().toUpperCase() : customer
				.getP_capacity_status_place());
		content.setP_code_adr_distr(customer.getP_code_adr_distr());
		content.setP_code_adr_region(customer.getP_code_adr_region());
		// content.setP_code_bank(customer.getP_code_bank());
		content.setP_code_birth_distr(customer.getP_code_birth_distr());
		content.setP_code_birth_region(customer.getP_code_birth_region());
		content.setP_code_capacity(customer.getP_code_capacity());
		content.setP_code_citizenship(customer.getP_code_citizenship());
		content.setP_code_class_credit(customer.getP_code_class_credit());
		content.setP_code_gender(customer.getP_code_gender());
		content.setP_code_nation(customer.getP_code_nation());
		content.setP_code_tax_org(customer.getP_code_tax_org());
		content.setP_email_address(customer.getP_email_address() != null ? customer
				.getP_email_address().toLowerCase() : null);
		content.setP_family(customer.getP_family() != null ? customer
				.getP_family().toUpperCase() : customer.getP_family());
		content.setP_first_name(customer.getP_first_name() != null ? customer
				.getP_first_name().toUpperCase() : customer.getP_first_name());
		content.setP_patronymic(customer.getP_patronymic() != null ? customer
				.getP_patronymic().toUpperCase() : customer.getP_patronymic());
		content.setP_inps(customer.getP_inps());
		content.setP_num_certif_capacity(customer.getP_num_certif_capacity());
		content.setP_pension_sertif_serial(customer
				.getP_pension_sertif_serial() != null ? customer
				.getP_pension_sertif_serial().toUpperCase() : customer
				.getP_pension_sertif_serial());
		content.setP_number_tax_registration(customer
				.getP_number_tax_registration());
		content.setP_passport_date_registration(customer
				.getP_passport_date_registration());
		content.setP_passport_date_expiration(customer
				.getP_passport_date_expiration());
		content.setP_passport_number(customer.getP_passport_number());
		content.setP_passport_place_registration(customer
				.getP_passport_place_registration() != null ? customer
				.getP_passport_place_registration().toUpperCase() : customer
				.getP_passport_place_registration());
		content.setP_passport_serial(customer.getP_passport_serial() != null ? customer
				.getP_passport_serial().toUpperCase() : customer
				.getP_passport_serial());
		content.setP_passport_type(customer.getP_passport_type());
		content.setP_phone_home(customer.getP_phone_home());
		content.setP_phone_mobile(customer.getP_phone_mobile());
		content.setP_post_address(customer.getP_post_address());
		content.setP_type_document(customer.getP_type_document());
		// content.setSign_registr(customer.getSign_registr());
		// content.setState((customer.getState() == null ||
		// customer.getState().isEmpty()) ? 0 :
		// Integer.parseInt(customer.getState()));
		// content.setFirst_name(customer.getP_first_name_local() != null ?
		// customer.getP_first_name_local().toUpperCase() :
		// customer.getP_first_name_local());
		// content.setLast_name(customer.getP_family_local() != null ?
		// customer.getP_family_local().toUpperCase() :
		// customer.getP_family_local());
		// content.setMid_name(customer.getP_patronymic_local() != null ?
		// customer.getP_patronymic_local().toUpperCase() :
		// customer.getP_patronymic_local());
		content.setFirst_name(customer.getP_first_name() != null ? customer
				.getP_first_name().toUpperCase() : customer.getP_first_name());
		content.setLast_name(customer.getP_family() != null ? customer
				.getP_family().toUpperCase() : customer.getP_family());
		content.setMid_name(customer.getP_patronymic() != null ? customer
				.getP_patronymic().toUpperCase() : customer.getP_patronymic());

		content.setDul_country_code(customer.getP_type_document() != null
				&& !customer.getP_type_document().equals("4") ? "860"
				: customer.getP_code_citizenship());
		content.setDul_region(customer.getP_pass_place_region());
		content.setDul_district(customer.getP_pass_place_district());
		content.setAdr_district(customer.getP_post_address_quarter() != null ? customer
				.getP_post_address_quarter().toUpperCase() : customer
				.getP_post_address_quarter());
		content.setAdr_street(customer.getP_post_address_street() != null ? customer
				.getP_post_address_street().toUpperCase() : customer
				.getP_post_address_street());
		content.setAdr_building(customer.getP_post_address_house() != null ? customer
				.getP_post_address_house().toUpperCase() : customer
				.getP_post_address_house());
		content.setAdr_room(customer.getP_post_address_flat() != null ? customer
				.getP_post_address_flat().toUpperCase() : customer
				.getP_post_address_flat());

		InternalControl internalControl = customer.getInternalControl();
		content.setUvk_init_date(internalControl != null ? internalControl
				.getInitDate() : null);
		content.setUvk_valid_from(internalControl != null ? internalControl
				.getValidDateFrom() : null);
		content.setUvk_valid_to(internalControl != null ? internalControl
				.getValidDateTo() : null);
		content.setUvk_reason(internalControl != null ? internalControl
				.getReason() : null);
		content.setUvk_risk_lavel(internalControl != null ? internalControl
				.getRiskLevel() : null);
		content.setSom_opers(internalControl != null ? internalControl
				.getS_notes() : null);
		content.setPod_opers(internalControl != null ? internalControl
				.getP_notes() : null);
		// content.setProfile_author("getProfileAuthor()");
		// content.setProfile_author(customer.getBranch() +
		// Integer.toString((Integer) session.getAttribute("uid")));
		content.setProfile_author(userBranch
				+ Integer.toString((Integer) session.getAttribute("uid")));
		return content;
	}

	private void parseExceptions(BAPIRET2[] responses) throws SAPException {
		List<Customer> duplicatedCustomers = new ArrayList<Customer>();
		if (responses != null) {
			for (int i = 0; i < responses.length; i++) {
				BAPIRET2 response = responses[i];
				if (response != null
						&& response.getTYPE().equalsIgnoreCase("E")) {
					if (response.getID() != null
							&& response.getID().equalsIgnoreCase("ZBP_NCI")
							&& response.getNUMBER() != null
							&& response.getNUMBER().equalsIgnoreCase("009")) {
						Customer duplication = new Customer();
						duplication.setIdSap(response.getMESSAGE_V1());
						duplicatedCustomers.add(duplication);
					} else
						throw new SAPException("\n SAP Ошибка \n"
								+ response.getMESSAGE());
				}
				// Если признак существует то после этого вызвать процедуру
				if (response != null
						&& response.getTYPE().equalsIgnoreCase("I")) {
					if (response.getID() != null
							&& response.getID().equalsIgnoreCase("ZBP_NCI")
							&& response.getNUMBER() != null
							&& response.getNUMBER().equalsIgnoreCase("014")) {
						throw new I014Exception();
					}
				}
			}
		}
		if (duplicatedCustomers.size() > 0)
			throw new SAPDuplicationException("Нашлись дупликаты ",
					duplicatedCustomers);
	}

	private class I014Exception extends RuntimeException {

	}

	public void onClick$fill_client_form_btn$add_everywhere() {
		try {
			String number = add_everywhere$ap_passport_number.getValue();
			String seria = add_everywhere$ap_passport_serial.getValue();
			String pin = add_everywhere$ap_pinfl.getValue();
			if (number != null && !number.equals("") && seria != null
					&& !seria.equals("")) {
				StringBuilder url = new StringBuilder(ConnectionPool.getValue(
						"GetPhysicalServiceUrl", alias) + "?");
				String branch = (String) session.getAttribute("branch");
				url.append("bank=" + CustomerUtils.getBankType(branch, alias)
						+ "");
				url.append("&lang=3");
				url.append("&branch=" + branch);
				url.append("&passport_seria=" + seria);
				url.append("&passport_number=" + number);
				url.append("&pin=" + pin);
				Long id = CustomerUtils.getNextId(alias);
				url.append("&id=" + id);
				System.out.println(url.toString());
				LtLogger.getLogger().error("url: " + url.toString());
				try {
					// current.setP_family("TTTTTTTTTT");
					// add_everywhere$ap_family.setValue("OSTANOV");
					// add_everywhere$ap_first_name.setValue("XAMZA");

					sendUrlRequest(url.toString());
					LtLogger.getLogger().error("url finish");
					PhysicalAutoCompleteModel customerComplete = CustomerUtils
							.getCustomerModel(id, alias);
					LtLogger.getLogger().error("url getCustomerModel");
					// System.out.println(customerComplete.getNationality()+
					// "-"+ customerComplete.getNationality_desc());
					if (customerComplete != null) {
						// System.out.println(customerComplete.getNationality()+
						// "-"+ customerComplete.getNationality_desc());

						add_everywhere$ap_family.setValue(customerComplete
								.getLast_name());
						add_everywhere$ap_first_name.setValue(customerComplete
								.getFirst_name());
						add_everywhere$ap_patronymic.setValue(customerComplete
								.getPatronym());
						add_everywhere$ap_family.setValue(customerComplete
								.getSurname());
						add_everywhere$ap_first_name.setValue(customerComplete
								.getGivenname());

						if (customerComplete.getGive_place() != null
								&& customerComplete.getGive_place().length() == 5) {
							PhysicalAutoCompleteModel temp = CustomerUtils.getSomeData(customerComplete.getGive_place(), alias);
							if (temp!=null) {
								if (temp.getGive_place() != null)
								  add_everywhere$ap_passport_place_registration.setValue(CustomerUtils.toTranslitNew(temp
											.getGive_place()) + " IIB");
							    if (temp.getInn_registrated_gni() != null)
								  add_everywhere$ap_code_tax_org
										.setSelecteditem(temp
												.getInn_registrated_gni());
							}
						}
						add_everywhere$ap_passport_date_registration
								.setValue(customerComplete.getDate_issue());
						add_everywhere$ap_passport_date_expiration
								.setValue(customerComplete.getDate_expiry());
						add_everywhere$ap_code_gender
								.setSelecteditem(customerComplete.getSex() != null ? customerComplete
										.getSex() + ""
										: null);
						if (nationMapList == null)
							nationMapList = com.is.utilsti.RefDataService
									.get_nation_map(alias);
						if (customerComplete.getNationality() != null) {
							for (int i = 0; i < nationMapList.size(); i++) {
								if (((RefData) nationMapList.get(i)).getData()
										.equals(customerComplete
												.getNationality())
										|| ((RefData) nationMapList.get(i))
												.getData() == customerComplete
												.getNationality()) {
									add_everywhere$ap_code_nation
											.setSelecteditem(((RefData) nationMapList
													.get(i)).getLabel());
									break;
								}
							}
						}

						add_everywhere$ap_birthday.setValue(customerComplete
								.getBirth_date());
						add_everywhere$ap_birth_place.setValue(customerComplete
								.getBirth_place());
						add_everywhere$ap_code_adr_region
								.setSelecteditem(customerComplete
										.getDomicile_region() != null ? customerComplete
										.getDomicile_region() + ""
										: null);
						if (customerComplete.getDomicile_district() != null)
							code_distr = customerComplete
									.getDomicile_district();

						onSelect$ap_code_adr_region$add_everywhere();
						if (customerComplete.getDomicile_district() != null) {
							tcustomer.setP_code_adr_distr(customerComplete
									.getDomicile_district());
						}
						String place_desc = customerComplete
								.getDomicile_place_desc() == null ? ""
								: customerComplete.getDomicile_place_desc();
						String street_desc = customerComplete
								.getDomicile_street_desc() == null ? ""
								: customerComplete.getDomicile_street_desc();
						String address = customerComplete.getDomicile_address() == null ? ""
								: customerComplete.getDomicile_address();
						address += customerComplete.getDomicile_house() == null ? ""
								: ", " + customerComplete.getDomicile_house();
						address += customerComplete.getDomicile_flat() == null ? ""
								: ", " + customerComplete.getDomicile_flat();
						add_everywhere$ap_post_address.setValue(CustomerUtils
								.toTranslitNew(place_desc + " " + street_desc
										+ " " + address));
						add_everywhere$ap_number_tax_registration
								.setValue(customerComplete.getInn());
						add_everywhere$ap_pinfl.setValue(customerComplete
								.getPin());
						binder.loadAll();
					}
				} catch (Exception e) {
					e.printStackTrace();
					LtLogger.getLogger().error(CheckNull.getPstr(e));
				}

			} else
				alert("Серия или номер паспорта не заполнены");
		} catch (WrongValueException e) {
			alert("Серия или номер паспорта не заполнены");
			e.printStackTrace();
		}
	}

	public void onClick$fill_client_form_btn2$add_everywhere() {

		System.out.println("2 count in combobox = "
				+ add_everywhere$ap_code_adr_distr.getItemCount());// тут
																	// показывает
																	// что 203
																	// штук. все
																	// районы
																	// респ узб.
																	// при
																	// загрузке
																	// страницы
																	// первым
																	// загружается
																	// все
																	// регионы.
		if (add_everywhere$ap_code_adr_distr.getModel() != null)
			System.out.println("2 count in model ="
					+ add_everywhere$ap_code_adr_distr.getModel().getSize()); // здес
																				// показывает
																				// 13,
																				// это
																				// правильно

	}

	@SuppressWarnings("deprecation")
	private static void sendUrlRequest(String urlString) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		LtLogger.getLogger().error("req 1");
		HttpGet request = new HttpGet(urlString);
		LtLogger.getLogger().error("req 2");
		HttpResponse response = client.execute(request);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());
		LtLogger.getLogger().error(
				"Response Code : " + response.getStatusLine().getStatusCode());
		// System.out.println(EntityUtils.toString(response.getEntity(),
		// "UTF-8"));
	}

	public void onClick$btn_add$addwnd() {
		if (CheckNull.isEmpty(addwnd$sproduct.getValue())) {
			alert("Продукт не выбран");
			return;
		}
		TietoCardSetting tcs = TclientService.getTietoCardSetting(
				Integer.parseInt(addwnd$sproduct.getValue()), alias);
		String ord = tcs.getId_order_account();
		// if (tcs.getId_order_account().compareTo("901") != 0) // if should use
		// next ord
		// {
		// ord = null;
		// }
		if (addwnd$sproduct.getValue().equals("860")) {
			open22618_acc1(
					tcs.getId_order_max(),
					ord,
					tcs.getAcc_name_postfix(),
					(branch.compareTo(TclientService.getV_HO()) == 0) ? tcs
							.getHo_acc_group() : tcs.getBr_acc_group());
		} else {
			open22618_acc(
					tcs.getId_order_max(),
					ord,
					tcs.getAcc_name_postfix(),
					(branch.compareTo(TclientService.getV_HO()) == 0) ? tcs
							.getHo_acc_group() : tcs.getBr_acc_group());
		}
		onSelect$sproduct$addwnd();
	}

	private String open_card(String card_prod_id, String RT, boolean sec,
			String new_card_acc) {

		// if ((new_card_acc.substring(0, 5)).compareTo("22618")!=0)
		// {
		// alert("Неверный счет карты:\n"+new_card_acc);
		// return "";
		// }
		Tclient ntc;
		ntc = tietocl;

		String halias = CustomerService.get_alias_ho(alias);

		TietoCardSetting tcs = TclientService.getTietoCardSetting(
				Integer.parseInt(card_prod_id), alias);
		TietoCustomer tc;
		tc = com.is.customer.CustomerService.getTietoCustomer(ntc.getClient(),
				branch, alias);

		BigDecimal agrnum;
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		RowType_CardInfo rtci = new RowType_CardInfo();
		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		RowType_CardInfo_EMV_Data EMV_data = new RowType_CardInfo_EMV_Data();

		RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder();
		RowType_Agreement avalue = new RowType_Agreement();

		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		RowType_AccountInfo rtai = null;

		Calendar cal = Calendar.getInstance();

		// String str1 = "";
		// ObjectMapper objectMapper = new ObjectMapper();
		// try {
		// str1 =
		// objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tcs);
		// } catch (Exception e22) {
		// str1 = " str1=error tcs";
		// } finally {
		// }
		// LtLogger.getLogger().info( "** not err trp  ************** " + str1);

		try {

			connectionInfo.setBANK_C(tcs.getBank_c());
			connectionInfo.setGROUPC(tcs.getGroup_c());

			avalue.setBINCOD(tcs.getBin());
			avalue.setBANK_CODE(TclientService.getV_bankc());

			Res res = CustomerService.getTieto_branch(branch, uid, alias);
			if (res.getCode() != 0) {
				alert(res.getName());
				return null;
			}
			avalue.setBRANCH(res.getName());

			avalue.setCITY(!CheckNull.isEmpty(ntc.getR_city()) ? ntc
					.getR_city() : "UZB");
			// !!!!!!!avalue.setPRODUCT("00"+tcs.getCode());
			avalue.setPRODUCT(card_prod_id);
			avalue.setREP_LANG("1");
			avalue.setRISK_LEVEL(tcs.getRisk_level());
			avalue.setSTREET(!CheckNull.isEmpty(ntc.getR_street()) ? ntc
					.getR_street() : "STREET");
			avalue.setSTATUS("10");
			if (contract_nmb == null)
				contract_nmb = CustomerService.get_contract(branch,
						card_prod_id, alias);
			avalue.setCONTRACT(contract_nmb);
			avalue.setENROLLED(cal);
			avalue.setDISTRIB_MODE("01");
			avalue.setCLIENT(ntc.getClient());
			// avalue.set
			avalue.setCOUNTRY(!CheckNull.isEmpty(ntc.getR_cntry()) ? ntc
					.getR_cntry() : "UZB");
			agreementInfo.value = avalue;

			base_info.setC_ACCNT_TYPE("00");
			if (card_prod_id == "860") {
				base_info.setCCY("UZS");

			} else {
				base_info.setCCY("USD");

			}
			base_info.setSTAT_CHANGE("1");
			base_info.setMIN_BAL(BigDecimal.valueOf(0));
			base_info.setTRANZ_ACCT(new_card_acc);
			String a = new_card_acc;
			base_info.setACC_PRTY("1");
			base_info.setCOND_SET(tcs.getFinancial_conditions());
			base_info.setCYCLE("0");
			base_info.setCRD(BigDecimal.valueOf(0));
			base_info.setNON_REDUCE_BAL(tcs.getMinimum_balance());
			base_info.setSTATUS("0");
			// base_info.set
			base_info.setLIM_INTR(BigDecimal.valueOf(0));
			if (sec)
				base_info.setCARD_ACCT(RT);
			rtai = new RowType_AccountInfo(base_info,
					new RowType_AccountInfo_Additional());

			RowType_AccountInfo[] rtaia = new RowType_AccountInfo[] { rtai };
			ListType_AccountInfo ltai = new ListType_AccountInfo();
			ltai.setRow(rtaia);

			accountsListInfo = new ListType_AccountInfoHolder(ltai);

			logicalCard.setCOND_SET(tcs.getCard_condition());
			logicalCard
					.setRANGE_ID(BigDecimal.valueOf((long) tcs.getRange_id()));
			logicalCard.setNO_NAME("");
			logicalCard.setRISK_LEVEL(tcs.getRisk_level());
			logicalCard.setBASE_SUPP("1");
			logicalCard.setF_NAMES(ntc.getF_names());
			logicalCard.setSURNAME(ntc.getSurname());
			// logicalCard.setr
			// logicalCard.set
			physicalCard
					.setCARD_NAME((ntc.getF_names() + " " + ntc.getSurname())
							.length() > 23 ? (ntc.getSurname() + " " + (ntc
							.getF_names()).substring(0, 1).toUpperCase())
							: (ntc.getF_names() + " " + ntc.getSurname()));
			physicalCard.setSTATUS1("0");
			physicalCard.setDESIGN_ID(tcs.getDesign_id());
			// physicalCard.set
			EMV_data.setCHIP_APP_ID(tcs.getId_chip_app());

			// logicalCard.set
			rtci.setLogicalCard(logicalCard);
			rtci.setPhysicalCard(physicalCard);
			rtci.setEMV_Data(EMV_data);
			RowType_CardInfo[] rtcia = { rtci };
			ListType_CardInfo lgtci = new ListType_CardInfo();
			lgtci.setRow(rtcia);

			cardsListInfo = new ListType_CardInfoHolder(lgtci);
			// pp.editAgreement(connectionInfo, agreementInfo, accountsListInfo,
			// cardsListInfo);
			RowType_EditAgreement_Request re = new RowType_EditAgreement_Request(
					BigDecimal.valueOf(agre_nom_upd),
					// avalue.getAGRE_NOM(),
					avalue.getBINCOD(), avalue.getBANK_CODE(),
					avalue.getBRANCH(), avalue.getB_BR_ID(),
					avalue.getOFFICE(), avalue.getOFFICE_ID(),
					avalue.getCITY(), avalue.getCLIENT(), avalue.getCOMENT(),
					avalue.getCONTRACT(), avalue.getCOUNTRY(),
					avalue.getCTIME(), avalue.getDISTRIB_MODE(),
					avalue.getENROLLED(), avalue.getE_MAILS(),
					avalue.getIN_FILE_NUM(), avalue.getISURANCE_TYPE(),
					avalue.getPOST_IND(), avalue.getPRODUCT(),
					avalue.getREP_LANG(), avalue.getRISK_LEVEL(),
					avalue.getSTATUS(), avalue.getSTREET(), avalue.getUSRID(),
					avalue.getU_COD4(), avalue.getU_CODE5(),
					avalue.getU_CODE6(), avalue.getU_FIELD3(),
					avalue.getU_FIELD4());
			re.setAGRE_NOM(BigDecimal.valueOf(agre_nom_upd));
			// перевыпуск с заменой
			if (edit_agree) {
				// LtLogger.getLogger().info(
				// "** not err edit_agree  ************** " + str1);

				orInfo = pp.editAgreement(connectionInfo, re);
				connectionInfo.setBANK_C(TclientService.getV_bankc());
				connectionInfo.setGROUPC(TclientService.getV_groupc());

				OperationResponseInfo ori = new OperationResponseInfo();

				mainAgreementInfo.setAGRE_NOM(BigDecimal.valueOf(agre_nom_upd));

				orInfo = pp.addInfo4Agreement(connectionInfo,
						mainAgreementInfo, new ListType_AccountInfoHolder(),
						cardsListInfo);
			} else
				orInfo = pp.newAgreement(connectionInfo, agreementInfo,
						accountsListInfo, cardsListInfo);
			// System.out.println("Tieto response:"+orInfo.getResponse_code().intValue()+"||agre response:"+agreementInfo.value+"||");
			edit_agree = false;
			if ((orInfo.getError_description() != null)
					|| (orInfo.getResponse_code().intValue() != 0)) {
				alert(orInfo.getError_description());
				return null;
			}

			RT = accountsListInfo.value.getRow(0).getBase_info().getCARD_ACCT();
			// try {
			// str1 =
			// objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(RT);
			// } catch (Exception e22) {
			// str1 = " str1=error rt";
			// } finally {
			// }
			// LtLogger.getLogger().info( "** not err rt  ************** " +
			// str1);

			// System.out.println("Tieto response:"+orInfo.getResponse_code().intValue()+"||agre response:"+agreementInfo.value+"||");

			if ((orInfo.getError_description() != null)
					|| (orInfo.getResponse_code().intValue() != 0)) {
				alert(orInfo.getError_description());
				return null;
			}
			if (agreementInfo.value != null) {
				agrnum = agreementInfo.value.getAGRE_NOM();
				String new_card_num = cardsListInfo.value.getRow(0)
						.getLogicalCard().getCARD();
				UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
						"Открыта карта [" + new_card_num
								+ "] для клиента (tieto id [" + ntc.getClient()
								+ "]) [" + ntc.getF_names() + " "
								+ ntc.getSurname() + "]", branch));
				System.out.println("AGRE_NOM : "
						+ agreementInfo.value.getAGRE_NOM());
			}

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			e.printStackTrace();
			return null;
		}
		alert("Карта открыта: " + tcs.getName());
		addwnd.setVisible(false);
		return RT;
	}

	public void onClick$btn_cancel$addwnd() {
		addwnd.setVisible(false);
	}

	public void onClick$btn_cancel$show_cards() {
		show_cards.setVisible(false);
	}

	public void onClick$tbtn_add_card() {
		if (tietocl == null) {
			alert("Клиент не выбран");
			return;
		}
		String cl_id = CustomerService.get_HO_by_tieto(tietocl.getClient(),
				alias);
		if (TclientService.check_user(alias, branch, tietocl.getClient()) == "") {
			alert("Клиент не объединен");
			return;
		}

		AccountFilter acfilter = new AccountFilter();
		acfilter.setClient(cl_id);
		acfilter.setAcc_bal(cur_branch_customer.getCode_subject().equals("J") ? "22619"
				: "22618");
		acfilter.setBranch(TclientService.getV_HO());
		acfilter.setCurrency("840");
		String halias = CustomerService.get_alias_ho(alias);
		com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
				0, 100, acfilter, halias);
		addwnd$accGrid.setModel((ListModel) acc_model);
		addwnd$sproduct.setSelecteditem(null);
		addwnd$btn_add.setVisible(true);
		addwnd$sproduct.setVisible(true);
		addwnd$sproduct.setModel((new ListModelList(com.is.utilsti.RefDataService
				.getOfrProd(alias, cur_branch_customer.getCode_subject()
						.equals("J") ? "I" : "P", branch, uid))));
		addwnd$btn_add.setVisible(!cur_branch_customer.getCode_subject()
				.equals("J"));
		addwnd.setVisible(true);
	}

	private void set_default() {

		CheckNull.clearForm(addCustomer$addgrdl);
		CheckNull.clearForm(addCustomer$addgrdr);
		CheckNull.clearForm(add_everywhere$addgrdl);
		CheckNull.clearForm(add_everywhere$addgrdr);

		if (add_ti) {
			if (is_ho) {
				fill_form(cur_HO_customer, null);
			} else if (is_br) {
				fill_form(cur_branch_customer, null);
			}
		} else if (add_ho) {
			if (is_br) {
				fill_bank_form(cur_branch_customer);
			} else if (is_ti) {

			}
		} else if (add_br) {
			if (is_ho) {
				fill_bank_form(cur_HO_customer);
			} else if (is_ti) {

			}
		}
	}

	private void fill_bank_form(Customer base_cl) {

		if (base_cl.getP_passport_number() != null)
			addCustomer$ap_passport_number.setValue(base_cl
					.getP_passport_number());
		if (base_cl.getP_type_document() != null)
			addCustomer$ap_type_document.setSelecteditem(base_cl
					.getP_type_document());
		if (base_cl.getP_passport_serial() != null)
			addCustomer$ap_passport_serial.setValue(base_cl
					.getP_passport_serial());
		if (base_cl.getP_passport_date_registration() != null)
			addCustomer$ap_passport_date_registration.setValue(base_cl
					.getP_passport_date_registration());
		if (base_cl.getP_passport_place_registration() != null)
			addCustomer$ap_passport_place_registration.setValue(base_cl
					.getP_passport_place_registration());
		if (base_cl.getP_passport_date_expiration() != null)
			addCustomer$ap_passport_date_expiration.setValue(base_cl
					.getP_passport_date_expiration());

		if (base_cl.getP_family() != null)
			addCustomer$ap_family.setValue(base_cl.getP_family());
		if (base_cl.getP_first_name() != null)
			addCustomer$ap_first_name.setValue(base_cl.getP_first_name());
		if (base_cl.getP_patronymic() != null)
			addCustomer$ap_patronymic.setValue(base_cl.getP_patronymic());

		if (base_cl.getP_birthday() != null)
			addCustomer$ap_birthday.setValue(base_cl.getP_birthday());
		if (base_cl.getP_code_birth_region() != null)
			addCustomer$ap_code_birth_region.setSelecteditem(base_cl
					.getP_code_birth_region());
		if (base_cl.getP_code_birth_distr() != null)
			addCustomer$ap_code_birth_distr.setSelecteditem(base_cl
					.getP_code_birth_distr());
		if (base_cl.getP_birth_place() != null)
			addCustomer$ap_birth_place.setValue(base_cl.getP_birth_place());
		if (base_cl.getP_code_gender() != null)
			addCustomer$ap_code_gender.setSelecteditem(base_cl
					.getP_code_gender());
		if (base_cl.getP_code_nation() != null)
			addCustomer$ap_code_nation.setSelecteditem(base_cl
					.getP_code_nation());
		if (base_cl.getP_code_adr_region() != null)
			addCustomer$ap_code_adr_region.setSelecteditem(base_cl
					.getP_code_adr_region());
		if (base_cl.getP_code_adr_distr() != null)
			addCustomer$ap_code_adr_distr.setSelecteditem(base_cl
					.getP_code_adr_distr());
		if (base_cl.getP_code_tax_org() != null)
			addCustomer$ap_code_tax_org.setSelecteditem(base_cl
					.getP_code_tax_org());
		if (base_cl.getP_number_tax_registration() != null)
			addCustomer$ap_number_tax_registration.setValue(base_cl
					.getP_number_tax_registration());
		if (base_cl.getP_code_citizenship() != null)
			addCustomer$ap_code_citizenship.setSelecteditem(base_cl
					.getP_code_citizenship());
		if (base_cl.getCode_country() != null)
			addCustomer$acode_country
					.setSelecteditem(base_cl.getCode_country());
		if (base_cl.getCode_resident() != null)
			addCustomer$acode_resident.setSelecteditem(base_cl
					.getCode_resident());
		if (base_cl.getP_phone_mobile() != null)
			addCustomer$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
		if (base_cl.getP_email_address() != null)
			addCustomer$ap_email_address.setValue(base_cl.getP_email_address());
		if (base_cl.getP_phone_home() != null)
			addCustomer$ap_phone_home.setValue(base_cl.getP_phone_home());
		if (base_cl.getP_inps() != null)
			addCustomer$ap_inps.setValue(base_cl.getP_inps());
		if (base_cl.getP_pinfl() != null)
			addCustomer$ap_pinfl.setValue(base_cl.getP_pinfl());
		if (base_cl.getP_post_address() != null)
			addCustomer$ap_post_address.setValue(base_cl.getP_post_address());
	}

	private void fill_form(Customer base_cl, Tclient tieto_cl) {
		add_everywhere$acode_tel.setValue("");
		if (tieto_cl != null) {
			fill_form(tieto_cl);
		}

		if (base_cl.getP_passport_number() != null)
			add_everywhere$ap_passport_number.setValue(base_cl
					.getP_passport_number());
		if (base_cl.getP_type_document() != null)
			add_everywhere$ap_type_document.setSelecteditem(base_cl
					.getP_type_document());
		if (base_cl.getP_passport_serial() != null)
			add_everywhere$ap_passport_serial.setValue(base_cl
					.getP_passport_serial());
		if (base_cl.getP_passport_date_registration() != null)
			add_everywhere$ap_passport_date_registration.setValue(base_cl
					.getP_passport_date_registration());
		if (base_cl.getP_passport_place_registration() != null)
			add_everywhere$ap_passport_place_registration.setValue(base_cl
					.getP_passport_place_registration());
		if (base_cl.getP_passport_date_expiration() != null)
			add_everywhere$ap_passport_date_expiration.setValue(base_cl
					.getP_passport_date_expiration());

		if (base_cl.getP_family() != null)
			add_everywhere$ap_family.setValue(base_cl.getP_family());
		if (base_cl.getP_first_name() != null)
			add_everywhere$ap_first_name.setValue(base_cl.getP_first_name());
		if (base_cl.getP_patronymic() != null)
			add_everywhere$ap_patronymic.setValue(base_cl.getP_patronymic());

		if (base_cl.getP_birthday() != null)
			add_everywhere$ap_birthday.setValue(base_cl.getP_birthday());
		if (base_cl.getP_code_birth_region() != null)
			add_everywhere$ap_code_birth_region.setSelecteditem(base_cl
					.getP_code_birth_region());
		if (base_cl.getP_code_birth_distr() != null)
			add_everywhere$ap_code_birth_distr.setSelecteditem(base_cl
					.getP_code_birth_distr());
		if (base_cl.getP_birth_place() != null)
			add_everywhere$ap_birth_place.setValue(base_cl.getP_birth_place());
		if (base_cl.getP_code_gender() != null)
			add_everywhere$ap_code_gender.setSelecteditem(base_cl
					.getP_code_gender());
		if (base_cl.getP_code_nation() != null)
			add_everywhere$ap_code_nation.setSelecteditem(base_cl
					.getP_code_nation());
		if (base_cl.getP_code_adr_region() != null)
			add_everywhere$ap_code_adr_region.setSelecteditem(base_cl
					.getP_code_adr_region());
		if (base_cl.getP_code_adr_distr() != null)
			add_everywhere$ap_code_adr_distr.setSelecteditem(base_cl
					.getP_code_adr_distr());
		if (base_cl.getP_code_tax_org() != null)
			add_everywhere$ap_code_tax_org.setSelecteditem(base_cl
					.getP_code_tax_org());
		if (base_cl.getP_number_tax_registration() != null)
			add_everywhere$ap_number_tax_registration.setValue(base_cl
					.getP_number_tax_registration());
		if (base_cl.getP_code_citizenship() != null)
			add_everywhere$ap_code_citizenship.setSelecteditem(base_cl
					.getP_code_citizenship());
		if (base_cl.getCode_country() != null)
			add_everywhere$acode_country.setSelecteditem(base_cl
					.getCode_country());
		if (base_cl.getCode_resident() != null)
			add_everywhere$acode_resident.setSelecteditem(base_cl
					.getCode_resident());
		if (base_cl.getP_phone_mobile() != null)
			add_everywhere$ap_phone_mobile
					.setValue(base_cl.getP_phone_mobile());
		if (base_cl.getP_email_address() != null)
			add_everywhere$ap_email_address.setValue(base_cl
					.getP_email_address());
		if (base_cl.getP_phone_home() != null)
			add_everywhere$ap_phone_home.setValue(base_cl.getP_phone_home());
		if (base_cl.getP_inps() != null)
			add_everywhere$ap_inps.setValue(base_cl.getP_inps());
		if (base_cl.getP_post_address() != null)
			add_everywhere$ap_post_address
					.setValue(base_cl.getP_post_address());
		// add_everywhere$acode_tel.setValue("");
	}

	private void fill_form(Tclient base_cl) {
		if (base_cl.getSerial_no() != null)
			add_everywhere$ap_passport_number.setValue(base_cl.getSerial_no());
		if (base_cl.getId_card() != null)
			add_everywhere$ap_passport_serial.setValue(base_cl.getId_card());
		if (base_cl.getDoc_since() != null)
			add_everywhere$ap_passport_date_registration.setValue(base_cl
					.getDoc_since());
		if (base_cl.getIssued_by() != null)
			add_everywhere$ap_passport_place_registration.setValue(base_cl
					.getIssued_by());
		if (base_cl.getSurname() != null)
			add_everywhere$ap_family.setValue(base_cl.getSurname());
		if (base_cl.getF_names() != null)
			add_everywhere$ap_first_name.setValue(base_cl.getF_names());
		if (base_cl.getM_name() != null)
			add_everywhere$ap_patronymic.setValue(base_cl.getM_name());

		if (base_cl.getB_date() != null)
			add_everywhere$ap_birthday.setValue(base_cl.getB_date());
		if (base_cl.getSex() != null)
			add_everywhere$ap_code_gender.setSelecteditem(base_cl.getSex());
		add_everywhere$acode_country.setSelecteditem((base_cl.getR_cntry()
				.compareTo("UZB") == 0) ? "860" : null);
		if (base_cl.getResident() != null)
			add_everywhere$acode_resident
					.setSelecteditem(base_cl.getResident());
		if (base_cl.getRmob_phone() != null)
			add_everywhere$ap_phone_mobile.setValue(base_cl.getRmob_phone());
		if (base_cl.getR_emails() != null)
			add_everywhere$ap_email_address.setValue(base_cl.getR_emails());
		if (base_cl.getR_phone() != null)
			add_everywhere$ap_phone_home.setValue(base_cl.getR_phone());
		if (base_cl.getR_street() != null)
			add_everywhere$ap_post_address.setValue(base_cl.getR_street());
		if (base_cl.getPersone_code() != null)
			add_everywhere$acode_tel.setValue(base_cl.getPersone_code());
		if (base_cl.getR_city() != null)
			add_everywhere$ar_city.setValue(base_cl.getR_city());
	}

	public void onSelect$ap_code_adr_region$add_everywhere() {
		add_everywhere$ap_code_adr_distr.setModel((new ListModelList(
				com.is.utilsti.RefDataService.getDistr(
						add_everywhere$ap_code_adr_region.getValue(), alias))));
		add_everywhere$ap_code_adr_distr.setSelectedIndex(-1);
	}

	public void onAfterRender$ap_code_adr_distr$add_everywhere() {
		// System.out.println("1="+add_everywhere$ap_code_adr_distr.getItemCount());
		if (code_distr != "")
			add_everywhere$ap_code_adr_distr.setSelecteditem(code_distr);
		code_distr = "";
	}

	public void onSelect$ap_code_birth_region$add_everywhere() {
		add_everywhere$ap_code_birth_distr
				.setModel((new ListModelList(com.is.utilsti.RefDataService
						.getDistr(
								add_everywhere$ap_code_birth_region.getValue(),
								alias))));
	}

	/*
	 * public void onClick$btn_test() { AccountFilter acfilter = new
	 * AccountFilter(); acfilter.setClient(cur_branch_customer.getId_client());
	 * acfilter.setAcc_bal("20206"); acfilter.setBranch(branch);
	 * acfilter.setCurrency("840"); com.is.accountti.PagingListModel acc_model =
	 * new com.is.accountti.PagingListModel(0, 100, acfilter, alias);
	 * accounts$accGrid.setModel((ListModel)acc_model);
	 * accounts.setVisible(true); }
	 */

	public void onClick$btn_cancel$accounts() {
		accounts.setVisible(false);
	}

	public void onClick$btn_add$accounts() {
		Customer ncr = com.is.customer.CustomerService.getCustomerById(
				cur_branch_customer.getId_client(), branch, alias);

		Res res = AccountService.doAction_create_acc_in_br(un, pwd, "20206",
				cur_branch_customer.getId_client(), "840", null,
				(ncr.getName().length() > 80 ? ncr.getName().substring(0, 79)
						: ncr.getName()), 101, alias, branch, true);

		if (res.getCode() != 0) {
			alert("ОШИБКА\nОткрытие счета 20206 в филиале:\n" + res.getName());
			return;
		}
		String tranzacc = res.getName();

		UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
				"Открыт счет [" + tranzacc + "] в филиале [" + branch
						+ "] для клиента ["
						+ cur_branch_customer.getId_client() + "] ["
						+ ncr.getName() + " " + ncr.getP_birthday() + "]",
				branch));
		accounts$btn_add.setDisabled(true);
		// refreshModel(_starttPageNumber);

		AccountFilter acfilter = new AccountFilter();
		acfilter.setClient(cur_branch_customer.getId_client());
		acfilter.setAcc_bal("20206");
		acfilter.setBranch(TclientService.getV_HO());
		acfilter.setCurrency("840");
		String halias = CustomerService.get_alias_ho(alias);
		com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
				0, 100, acfilter, halias);
		accounts$accGrid.setModel((ListModel) acc_model);
	}

	public void onSelect$sproduct$addwnd() {
		TietoCardSetting tcs = TclientService.getTietoCardSetting(
				Integer.parseInt(addwnd$sproduct.getValue()), alias);

		String cl_id = CustomerService.get_HO_by_tieto(tietocl.getClient(),
				alias);
		/*
		 * AccountFilter acfilter = new AccountFilter();
		 * acfilter.setClient(cl_id); acfilter.setAcc_bal("22618");
		 * acfilter.setBranch("00444"); acfilter.setCurrency("840");
		 * acfilter.setId_order(tcs.getId_order_account());
		 */

		if (addwnd$sproduct.getValue().equals("860")) {
			List<Account> aModel = AccountService.get_card_accounts_new_card1(
					cl_id, branch, addwnd$sproduct.getValue(),
					tietocl.getClient());
			addwnd$accGrid.setModel(new ListModelList(aModel));
		} else {
			List<Account> aModel = AccountService.get_card_accounts_new_card(
					cl_id, branch, addwnd$sproduct.getValue(),
					tietocl.getClient());
			addwnd$accGrid.setModel(new ListModelList(aModel));
		}

		/*
		 * String halias = CustomerService.get_alias_ho(alias);
		 * com.is.accountti.PagingListModel acc_model = new
		 * com.is.accountti.PagingListModel(0, 100, acfilter, halias);
		 */
		/*
		 * if (aModel.size() != 0) { if
		 * ((addwnd$sproduct.getValue().compareTo("009"
		 * )=0)||(addwnd$sproduct.getValue().compareTo("005")=0)) {
		 * addwnd$btn_add.setDisabled(true); } else {
		 * addwnd$btn_add.setDisabled(false);
		 * 
		 * } } else { addwnd$btn_add.setDisabled(false); }
		 */
	}

	public void open22618_acc(String id_order_max, String ord, String postfix,
			int group) {

		com.is.LtLogger.getLogger().error(
				"open22618_acc == " + addwnd$sproduct.getValue());
		String cl_id = CustomerService.get_HO_by_tieto(tietocl.getClient(),
				alias);
		String halias = CustomerService.get_alias_ho(alias);
		Customer cst = CustomerService.getCustomerById(cl_id,
				TclientService.getV_HO(), halias);

		String ord_1 = AccountService.Get_acc_hole("22618", ord, id_order_max,
				cst.getId_client(), "00444", halias).getName();

		Res res = AccountService.doAction_create_acc_in_br(un, pwd, "22618",
				cst.getId_client(), "840", ord_1,
				((cst.getName() + " " + postfix).length() > 80 ? (cst.getName()
						+ " " + postfix).substring(0, 79) : (cst.getName()
						+ " " + postfix)), group, halias,
				TclientService.getV_HO(),
				branch.compareTo(TclientService.getV_HO()) == 0);

		if (res.getCode() != 0) {
			alert("ОШИБКА\nОткрытие счета 22618 в ГО:\n" + res.getName());
			return;
		}
		new_card_acc = res.getName();

		UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
				"Открыт счет [" + new_card_acc + "] для клиента ["
						+ cst.getId_client() + "] [" + cst.getName() + " "
						+ cst.getP_birthday() + "]", branch));

		// refreshModel(_starttPageNumber);

		AccountFilter acfilter = new AccountFilter();
		acfilter.setClient(cl_id);
		acfilter.setAcc_bal("22618");
		acfilter.setBranch(TclientService.getV_HO());
		acfilter.setCurrency("840");

		com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
				0, 100, acfilter, halias);
		addwnd$accGrid.setModel((ListModel) acc_model);
	}

	public void open22618_acc1(String id_order_max, String ord, String postfix,
			int group) {

		com.is.LtLogger.getLogger().error(
				"open22618_acc1 == " + addwnd$sproduct.getValue());
		String cl_id = CustomerService.get_HO_by_tieto(tietocl.getClient(),
				alias);
		String halias = CustomerService.get_alias_ho(alias);
		Customer cst = CustomerService.getCustomerById(cl_id,
				TclientService.getV_HO(), halias);

		String ord_1 = AccountService.Get_acc_hole1("22618", ord, id_order_max,
				cst.getId_client(), "00444", halias).getName();

		Res res = AccountService.doAction_create_acc_in_br(un, pwd, "22618",
				cst.getId_client(), "000", ord_1,
				((cst.getName() + " " + postfix).length() > 80 ? (cst.getName()
						+ " " + postfix).substring(0, 79) : (cst.getName()
						+ " " + postfix)), group, halias,
				TclientService.getV_HO(),
				branch.compareTo(TclientService.getV_HO()) == 0);

		if (res.getCode() != 0) {
			alert("ОШИБКА\nОткрытие счета 22618 в ГО:\n" + res.getName());
			return;
		}
		new_card_acc = res.getName();

		UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
				"Открыт счет [" + new_card_acc + "] для клиента ["
						+ cst.getId_client() + "] [" + cst.getName() + " "
						+ cst.getP_birthday() + "]", branch));

		// refreshModel(_starttPageNumber);

		AccountFilter acfilter = new AccountFilter();
		acfilter.setClient(cl_id);
		acfilter.setAcc_bal("22618");
		acfilter.setBranch(TclientService.getV_HO());
		acfilter.setCurrency("000");

		com.is.accountti.PagingListModel acc_model = new com.is.accountti.PagingListModel(
				0, 100, acfilter, halias);
		addwnd$accGrid.setModel((ListModel) acc_model);
	}

	public void onClick$btn_block$blockwnd() {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();

		try {
			connectionInfo.setBANK_C(TclientService.getV_bankc());
			connectionInfo.setGROUPC(TclientService.getV_groupc());

			parameters.setBANK_C(TclientService.getV_bankc());
			parameters.setGROUPC(TclientService.getV_groupc());
			parameters.setSTOP_CAUSE(blockwnd$sstopcauses.getValue());// parameters.setSTOP_CAUSE("1");
			parameters.setTEXT(blockwnd$txtstopcauses.getValue());
			parameters.setCARD(cur_card.getCard());

			orInfo = pp.addCardToStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0) {
				alert(orInfo.getError_action() + "\r\n"
						+ orInfo.getError_description());
			}
			UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
					"Карта No [" + cur_card.getCard() + "] заблокирована",
					branch));

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			e.printStackTrace();
		} finally {
			tcust.setTieto_customer_id(tietocl.getClient());
			tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
			show_cards$accGrid.setModel(new BindingListModelList(TclientService
					.getAccInfo(tietocl.getClient()), false));
			blockwnd.setVisible(false);
		}

	}

	public void onClick$btn_cancel$blockwnd() {
		blockwnd.setVisible(false);
	}

	public void onClick$btn_cancel$printwnd() {
		printwnd.setVisible(false);
	}

	public void onClick$btn_visa_cup_app$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("P_IMG1", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/1.jpg");
		params.put("P_IMG2", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/2.jpg");
		params.put("P_IMG3", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/3.jpg");
		params.put("P_IMG4", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/4.jpg");

		fill_cl_report(tietocl, "TI_APPLICATION_VISA_CAP_IM", params);
	}

	public void onClick$btn_visa_cup_d$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_CAP", params);
	}

	public void onClick$btn_exchange_app$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_APPL_VISA_EXCHANGE_IP", params);
	}

	public void onClick$btn_exchange_app_fl$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_APPL_VISA_EXCHANGE_FL", params);
	}

	public void onClick$btn_exchange_d$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_EXCHANGE_IP", params);
	}

	public void onClick$btn_u_exchange_d$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_EXCHANGE_FL", params);
	}

	public void onClick$btn_au_pt_d$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}

	public void onClick$btn_au_pt_app$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_APPL_VISA_PREMIUM", params);
	}

	public void onClick$btn_visa_cup_app$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("P_IMG1", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/1.jpg");
		params.put("P_IMG2", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/2.jpg");
		params.put("P_IMG3", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/3.jpg");
		params.put("P_IMG4", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/card_report_images/4.jpg");

		fill_cl_report(tcl, "TI_APPLICATION_VISA_CAP_IM", params);
	}

	public void onClick$btn_visa_cup_d$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tcl, "TI_DOGOVOR_VISA_CAP", params);
	}

	public void onClick$btn_exchange_app$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tcl, "TI_APPL_VISA_EXCHANGE_IP", params);
	}

	public void onClick$btn_exchange_app_fl$add_everywhere() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tietocl, "TI_APPL_VISA_EXCHANGE_FL", params);
	}

	public void onClick$btn_exchange_d$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tcl, "TI_DOGOVOR_VISA_EXCHANGE_IP", params);
	}

	public void onClick$btn_u_exchange_d$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tcl, "TI_DOGOVOR_VISA_EXCHANGE_FL", params);
	}

	public void onClick$btn_au_pt_d$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tcl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}

	public void onClick$btn_au_pt_app$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();

		fill_cl_report(tcl, "TI_APPL_VISA_PREMIUM", params);
	}

	public void onClick$btn_add_acc_app$add_everywhere() {
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null)
			return;

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr()
				+ ":5600/bf/images/logo_ipak.jpg");

		fill_cl_report(tcl, "TI_APPLICATION1", params);
	}

	public void onClick$btn_add_acc_app$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
		//		+ ((Integer) session.getAttribute("port")).toString()
		//		+ "/bf/images/logo_ipak.jpg");
		if (session.getAttribute("port")!=null)
			params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
					+ ((Integer) session.getAttribute("port")).toString()
					+ "/bf/images/logo_ipak.jpg");
		else
			params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
						+ localPort
						+ "/bf/images/logo_ipak.jpg");
		
		// System.out.println("http://"+session.getLocalAddr()+":"+
		// ((StringBuffer)session.getAttribute("port")).toString()+
		// "/bf/images/logo_ipak.jpg");

		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);

			// kjasgfj
			Account acc_20206 = AccountService.getAccount(
					AccountService.GetCur20206_tclient(tietocl.getClient(),
					// cfrd.acc.getTranz_acct().substring(9, 17),
							branch, c), alias, branch, c);
			
			//params.put("acc_20206", acc_20206.getId().length() < 1 ? "20206" : acc_20206.getId());
			if (acc_20206==null || acc_20206.getId()==null || acc_20206.getId().length() < 1 )  
  			  params.put("acc_20206",  "20206");
			else
				params.put("acc_20206", acc_20206.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (Exception e) {
			}
		}
		fill_cl_report(tietocl, "TI_APPLICATION1", params);
	}

	/*public void onClick$btn_universal_rep_test() {
		if (tietocl == null) {
		//	alert("Клиент tieto не выбран");
		//	return;
			tietocl = new Tclient();
			
		}

		Execution exec = Executions.getCurrent();

		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
		//		+ ((Integer) session.getAttribute("port")).toString()
		//		+ "/bf/images/logo_ipak.jpg");
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
				+ exec.getLocalPort()
				+ "/bf/images/logo_ipak.jpg");

		
		fill_cl_report(tietocl, "TI_UNIVERSAL", params);
	}*/
	
	public void onClick$btn_universal_rep$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		Execution exec = Executions.getCurrent();

		HashMap<String, Object> params = new HashMap<String, Object>();
		//params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
		//		+ ((Integer) session.getAttribute("port")).toString()
		//		+ "/bf/images/logo_ipak.jpg");
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
				+ exec.getLocalPort()
				+ "/bf/images/logo_ipak.jpg");

		// System.out.println("http://"+session.getLocalAddr()+":"+
		// ((StringBuffer)session.getAttribute("port")).toString()+
		// "/bf/images/logo_ipak.jpg");

		// Connection c = null;
		// try
		// {
		// c = ConnectionPool.getConnection(alias);
		//
		// Account acc_20206 = AccountService.getAccount(
		// AccountService.GetCur20206_tclient(
		// tietocl.getClient(),
		// //cfrd.acc.getTranz_acct().substring(9, 17),
		// branch,c), alias, branch, c);
		// params.put("acc_20206",
		// acc_20206.getId().length()<1?"20206":acc_20206.getId());
		// } catch (SQLException e)
		// {
		// e.printStackTrace();
		// }
		// finally
		// {
		// try{if(c != null)c.close();}catch(Exception e){}
		// }
		fill_cl_report(tietocl, "TI_UNIVERSAL", params);
	}

	public void onClick$btn_TI_APPLICATION_AUTOCARD_ACC$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
				+ ((Integer) session.getAttribute("port")).toString()
				+ "/bf/images/logo_ipak.jpg");

		fill_cl_report(tietocl, "TI_APPLICATION_AUTOCARD_ACC", params);
	}

	public void onClick$btn_TI_DOGOVOR_AUTOCARD$show_cards() {
		if (tietocl == null) {
			alert("Клиент tieto не выбран");
			return;
		}

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":"
				+ ((Integer) session.getAttribute("port")).toString()
				+ "/bf/images/logo_ipak.jpg");

		fill_cl_report(tietocl, "TI_DOGOVOR_AUTOCARD", params);
	}

	private Tclient get_data_from_add_everywhere() {
		Tclient rtc = new Tclient();

		boolean fl_err = false;
		String err = "";

		if ((!((add_everywhere$ap_passport_number.getValue())
				.matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_passport_number.getValue().length() > 9)) {
			fl_err = true;
			err += "\nНомер паспорта";
		}
		if ((!((add_everywhere$ap_passport_serial.getValue())
				.matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_passport_serial.getValue().length() > 9)) {
			fl_err = true;
			err += "\nСерия паспорта";
		}
		if ((!((add_everywhere$ap_passport_place_registration.getValue())
				.matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")))
				|| (add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200)) {
			fl_err = true;
			err += "\nМесто регистрации паспорта";
		}
		if ((!((add_everywhere$ap_family.getValue()).matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_family.getValue().length() > 34)) {
			fl_err = true;
			err += "\nФамилия";
		}
		if ((!((add_everywhere$ap_first_name.getValue())
				.matches("[a-zA-Z0-9]+")))
				|| (add_everywhere$ap_first_name.getValue().length() > 20)) {
			fl_err = true;
			err += "\nИмя";
		}
		if ((!((add_everywhere$ap_patronymic.getValue())
				.matches("[a-zA-Z0-9]*")))
				|| (add_everywhere$ap_patronymic.getValue().length() > 20)) {
			fl_err = true;
			err += "\nОтчество";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_type_document.getValue()))) {
			fl_err = true;
			err += "\nТип документа";
		}
		if ((!((add_everywhere$ap_number_tax_registration.getValue())
				.matches("[0-9]*")))
				|| (add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9)) {
			fl_err = true;
			err += "\nИНН";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue()))) {
			fl_err = true;
			err += "\nГражданство";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_country.getValue()))) {
			fl_err = true;
			err += "\nСтрана";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_resident.getValue()))) {
			fl_err = true;
			err += "\nРезидент";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue()))) {
			fl_err = true;
			err += "\nДата регистрации паспорта";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_birthday.getValue()))) {
			fl_err = true;
			err += "\nДата рождения";
		}
		if ((!((add_everywhere$ap_post_address.getValue())
				.matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")))
				|| (add_everywhere$ap_post_address.getValue().length() > 95)) {
			fl_err = true;
			err += "\nПочтовый адрес";
		}
		if ((add_everywhere$ap_birth_place.getValue() == null || add_everywhere$ap_birth_place
				.getValue().length() == 0)
				|| (!((add_everywhere$ap_birth_place.getValue())
						.matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*")))
				|| (add_everywhere$ap_birth_place.getValue().length() > 200)) {
			fl_err = true;
			err += "\nМесто рождения";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return null;
		}

		rtc.setF_names(add_everywhere$ap_first_name.getValue());
		rtc.setSurname(add_everywhere$ap_family.getValue());
		rtc.setM_name(add_everywhere$ap_patronymic.getValue());
		rtc.setDoc_since(add_everywhere$ap_passport_date_registration
				.getValue());
		rtc.setB_date(add_everywhere$ap_birthday.getValue());
		rtc.setResident(add_everywhere$acode_resident.getValue());
		rtc.setSex(add_everywhere$ap_code_gender.getValue());
		rtc.setSerial_no(add_everywhere$ap_passport_serial.getValue());
		rtc.setId_card(add_everywhere$ap_passport_number.getValue());
		rtc.setR_city(add_everywhere$ar_city.getValue());
		rtc.setR_street(add_everywhere$ap_post_address.getValue());
		rtc.setR_emails(add_everywhere$ap_email_address.getValue());
		rtc.setRmob_phone(add_everywhere$ap_phone_mobile.getValue());
		rtc.setR_phone(add_everywhere$ap_phone_home.getValue());
		rtc.setR_cntry((add_everywhere$acode_country.getValue()
				.compareTo("860") == 0) ? "UZB" : null);
		rtc.setIssued_by(add_everywhere$ap_passport_place_registration
				.getValue());
		rtc.setPersone_code(add_everywhere$acode_tel.getValue());
		return rtc;
	}

	private void fill_cl_report(Tclient tietocl, String rep_name,
			HashMap<String, Object> params) {
		/******************** CLIENT DATA *******************/
		params.put("CLIENT_NAME1",
				tietocl.getSurname() == null ? "" : tietocl.getSurname());
		params.put("CLIENT_NAME2",
				tietocl.getF_names() == null ? "" : tietocl.getF_names());
		params.put("CLIENT_NAME3",
				tietocl.getM_name() == null ? "" : tietocl.getM_name());
		params.put("CLIENT_FULL_NAME", (tietocl.getSurname() == null ? ""
				: tietocl.getSurname())
				+ " "
				+ (tietocl.getF_names() == null ? "" : tietocl.getF_names())
				+ " "
				+ (tietocl.getM_name() == null ? "" : tietocl.getM_name()));

		// params.put("DOC_NUMBER",
		// tietocl.getSerial_no()==null?"":tietocl.getSerial_no());
		// params.put("DOC_SERIAL",
		// tietocl.getId_card()==null?"":tietocl.getId_card());

		params.put("DOC_SERIAL",
				tietocl.getSerial_no() == null ? "" : tietocl.getSerial_no());
		params.put("DOC_NUMBER",
				tietocl.getId_card() == null ? "" : tietocl.getId_card());

		params.put(
				"DOC_DATE_ISSUE",
				tietocl.getDoc_since() == null ? "" : df.format(tietocl
						.getDoc_since()));
		params.put("DOC_ISSUE",
				tietocl.getIssued_by() == null ? "" : tietocl.getIssued_by());
		params.put(
				"CLIENT_BIRTHDAY",
				tietocl.getB_date() == null ? "" : df.format(tietocl
						.getB_date()));
		params.put("PHONE_PASSWORD", tietocl.getPersone_code() == null ? ""
				: tietocl.getPersone_code());
		params.put("POST_ADDRESS",
				tietocl.getR_street() == null ? "" : tietocl.getR_street());
		params.put("CITIZENSHIP", tietocl.getR_cntry() == null ? ""
				: TclientService.getCountryNameISO3(tietocl.getR_cntry()));
		params.put("JOB_PLACE", "");
		params.put("WORK_PLACE", "");
		params.put("PHONE_WORK", "");
		params.put("EMAIL",
				tietocl.getR_emails() == null ? "" : tietocl.getR_emails());
		params.put("PHONE_NUMBER",
				tietocl.getR_phone() == null ? "" : tietocl.getR_phone());
		String halias = CustomerService.get_alias_ho(alias);

		/********************* HO DATA *********************/
		params.put(
				"HO_BRANCH_NAME",
				com.is.utilsti.RefDataService
						.getMfo_name(TclientService.getV_HO(),
								halias).get(0).getLabel());
		params.put(
				"HO_BRANCH_ADDRESS",
				com.is.utilsti.RefDataService
						.getMfo_addr(TclientService.getV_HO(),
								halias).get(0).getLabel());
		params.put("HO_BANK_INN", ConnectionPool.getValue("HO_INN", alias));
		params.put("HO_BANK_OKONH", ConnectionPool.getValue("HO_OKONH", alias));
		params.put("HO_BRANCH", TclientService.getV_HO());

		/******************* BRANCH DATA *******************/
		params.put("BRANCH_NAME",
				com.is.utilsti.RefDataService.getMfo_name(branch, halias).get(0)
						.getLabel());
		params.put("BRANCH_ADDRESS",
				com.is.utilsti.RefDataService.getMfo_addr(branch, halias).get(0)
						.getLabel());
		params.put("BRANCH", branch);
		params.put("CARD_DEPARTMENT_EXEC",
				UserService.getUser(uid, branch, alias).getFull_name());
		params.put("BANK_PERSON", "");
		params.put("MICRO_DEPARTMENT_EXEC", "");

		/******************** CARD DATA ********************/
		params.put("CARD_VID", "");

		params.put("CUR_DATE", df.format(new java.util.Date()));

		com.is.ISLogger.getLogger().error("** not err alias=" + alias+". "+rep_name + ".pdf ");
		com.is.ISLogger.getLogger().error("** not err getRealPath=" +application.getRealPath("reports/" + rep_name + ".jasper"));
		
		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		    str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(params);
	        } catch (Exception e22) {
		    str1 = " params=error ";
	        } finally {
	        }
	        com.is.ISLogger.getLogger().error("** not err params ************** " + str1);
	        
		printwnd$rpframe.setContent(DPrint.getRepPdf(rep_name + ".pdf",
				application.getRealPath("reports/" + rep_name + ".jasper"),
				params, alias));

		printwnd.setVisible(true);
		// show_cards$accGrid.
	}

	public void onClick$acode_resident$add_everywhere() {
		add_everywhere$acode_resident.select();
		// add_everywhere$ap_code_citizenship.setSelecteditem(null);
		// add_everywhere$acode_country.setSelecteditem(null);
	}

	public void onClick$ap_code_citizenship$add_everywhere() {
		add_everywhere$ap_code_citizenship.select();
		// add_everywhere$acode_resident.setSelecteditem(null);
		// add_everywhere$acode_country.setSelecteditem(null);
	}

	public void onClick$acode_country$add_everywhere() {
		add_everywhere$acode_country.select();
		// add_everywhere$ap_code_citizenship.setSelecteditem(null);
		// add_everywhere$acode_resident.setSelecteditem(null);
	}

	public void onClick$pop_open_cl() {
		Res res = CustomerService.doAction_open_closed(un, pwd,
				(String) editPopup.getAttribute("client_id"), 20,
				(String) editPopup.getAttribute("branch"),
				(String) editPopup.getAttribute("alias"), branch);
		if (res.getCode() != 0) {
			alert(res.getName());
			return;
		}
		alert("Клиент открыт");
		refreshModel(_starttPageNumber);
	}

	public void onOpen$editPopup(OpenEvent event) throws Exception {
		if (event.isOpen()) {
			editPopup.setAttribute("branch", (String) event.getReference()
					.getAttribute("branch"));
			editPopup.setAttribute("client_id", (String) event.getReference()
					.getAttribute("client_id"));
			editPopup.setAttribute("alias", (String) event.getReference()
					.getAttribute("alias"));
		}

	}

	public void onClick$pop_confirm() {
		Res res = CustomerService.doAction_open_closed(un, pwd,
				(String) editPopup.getAttribute("client_id"), 2,
				(String) editPopup.getAttribute("branch"),
				(String) editPopup.getAttribute("alias"), branch);
		if (res.getCode() != 0) {
			alert(res.getName());
			return;
		}
		alert("Клиент утвержден");
		refreshModel(_starttPageNumber);
	}
	
	public void onOpen$confirmPopup(OpenEvent event) throws Exception {
		if (event.isOpen()) {
			editPopup.setAttribute("branch", (String) event.getReference()
					.getAttribute("branch"));
			editPopup.setAttribute("client_id", (String) event.getReference()
					.getAttribute("client_id"));
			editPopup.setAttribute("alias", (String) event.getReference()
					.getAttribute("alias"));
		}
	}
	
	public void onClick$btn_balance_confirmation_without_card_number$bt_rest_confirmation_wnd() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		fill_account_balance_report("TI_rests", params);
	}

	public void onClick$btn_balance_confirmation_with_card_number$bt_rest_confirmation_wnd() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		fill_account_balance_report("TI_rests_with_card", params);
	}

	public void onClick$btn_TI_small$bt_rest_confirmation_wnd() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		fill_account_balance_report("TI_small", params);
	}

	private void fill_account_balance_report(String rep_name,
			HashMap<String, Object> params) {
		Connection c = null;

		try {
			c = ConnectionPool.getConnection("IY00444");
			params.put("CARD_ACCOUNT", cfrd.acc.getTranz_acct());

			c.prepareCall("alter session set current_schema=" + alias)
					.execute();

			Account acc_20206 = AccountService.getAccount(AccountService
					.GetCur20206(cfrd.acc.getTranz_acct().substring(9, 17),
							branch, c), alias, branch, c);

			DecimalFormat df = new DecimalFormat();

			c.prepareCall("alter session set current_schema=IY00444").execute();
			params.put("ACCOUNT_20206", acc_20206.getId());
			Account card_acc = AccountService.getAccount(
					cfrd.acc.getTranz_acct(), "IY00444", "00444", c);
			BigDecimal usd_balance = BigDecimal.valueOf(card_acc.getS_out());
			usd_balance = usd_balance.divide(BigDecimal.valueOf(100));
			params.put("USD_BALANCE", df.format(usd_balance));

			BigDecimal USD_BALANCE_20206 = BigDecimal.valueOf(acc_20206
					.getS_out());
			USD_BALANCE_20206 = USD_BALANCE_20206.divide(BigDecimal
					.valueOf(100));
			params.put("USD_BALANCE_20206", df.format(USD_BALANCE_20206));

			params.put("USD_BALANCE_STR",
					AccountService.multimoneytostr(usd_balance, "02", "840", c));
			params.put("USD_BALANCE_STR_20206", AccountService.multimoneytostr(
					USD_BALANCE_20206, "02", "840", c));
			params.put("USD_BALANCE_STR_20206_UZ", AccountService
					.multimoneytostr(USD_BALANCE_20206, "100", "840", c));

			double rate = AccountService.GetCourse("840", "000", c);

			BigDecimal SUM_BALANCE = BigDecimal.valueOf(card_acc.getS_out());
			SUM_BALANCE = SUM_BALANCE.multiply(BigDecimal.valueOf(rate))
					.divide(BigDecimal.valueOf(100));

			BigDecimal SUM_BALANCE_20206 = USD_BALANCE_20206;
			SUM_BALANCE_20206 = SUM_BALANCE_20206.multiply(
					BigDecimal.valueOf(rate)).divide(BigDecimal.valueOf(100));

			params.put("SUM_BALANCE", df.format(SUM_BALANCE));
			params.put("SUM_BALANCE_20206", df.format(SUM_BALANCE_20206));
			params.put("SUM_BALANCE_STR",
					AccountService.multimoneytostr(SUM_BALANCE, "02", "000", c));
			params.put("SUM_BALANCE_STR_20206", AccountService.multimoneytostr(
					SUM_BALANCE_20206, "02", "000", c));
			params.put("RATE", Double.toString(rate));
			params.put("BANK_MANAGER_NAME", ConnectionPool.getBranchValue(
					"TIETO_BANK_MANAGER_NAME", alias, branch));
			params.put("TIETO_BANK_PHONE", ConnectionPool.getBranchValue(
					"TIETO_BANK_PHONE", alias, branch));
			params.put("EMP_NAME", UserService.getUser(uid, branch, alias)
					.getFull_name());
			params.put("EMP_NAME_TRANS", UserService
					.getUser(uid, branch, alias).getTrans_name());
			params.put("CARD_NUMBER", cfrd.acc.getCard().substring(0, 4)
					+ "********" + cfrd.acc.getCard().substring(12));
			params.put("MONTH_UZ", get_uzbek_month(new Date()));
			params.put("YEAR_LAST_DIGIT",
					new SimpleDateFormat("yyyy").format(new Date())
							.substring(3));
			params.put("DAY_IN_MONTH",
					new SimpleDateFormat("dd").format(new Date()));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (Exception e) {
			}
		}
		fill_cl_report(tietocl, rep_name, params);
	}

	public static String get_uzbek_month(Date date) {
		String monthString = "";

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);

		switch (month) {
		case 0:
			monthString = "yanvar";
			break;
		case 1:
			monthString = "fevral";
			break;
		case 2:
			monthString = "mart";
			break;
		case 3:
			monthString = "aprel";
			break;
		case 4:
			monthString = "may";
			break;
		case 5:
			monthString = "iyun";
			break;
		case 6:
			monthString = "iyul";
			break;
		case 7:
			monthString = "avgust";
			break;
		case 8:
			monthString = "sentabr";
			break;
		case 9:
			monthString = "oktabr";
			break;
		case 10:
			monthString = "noyabr";
			break;
		case 11:
			monthString = "dekabr";
			break;
		default:
			monthString = "Invalid month";
			break;
		}

		return monthString;
	}
	
	 public void onSelect$mainTabbox(SelectEvent event) throws InterruptedException {
	        Tab component = (Tab) event.getTarget();
	        if (component.getId().equalsIgnoreCase("clientsMainTab")) {
	            //internalControlInclude.setVisible(true);
	            //internalControlInclude.setSrc(null);
	            //internalControlInclude.setSrc(
	            //        "clientaddinfo.zul?branch=" + composer.getCustomer().getBranch() +
	            //                "&client_id=" + composer.getCustomer().getId() +
	            //                "&code_subject=P&alias=" + sessionAttributes.getSchema());
	        } else if (component.getId().equalsIgnoreCase("fillTab")) {
	            //onClick$btn_getFile();
	        	divPopolnenie.getChildren().clear();
	            popolnenieWnd =
	                    (Window) Executions.createComponents("Tclient.zul",
	                    		divPopolnenie, null);
	            //confirmWnd.setClosable(true);
	            popolnenieWnd.setVisible(true);
	            //Events.sendEvent("onUploadApps", confirmWnd, composer.getCustomer());
	        } else if (component.getId().equalsIgnoreCase("confirmTab")) {
	            includeUtverjdenie.setSrc("trpay.zul?search_clients=" + "hamza))" + "&alias=" + alias);			
	        }
	    }
	 
	/*
	 * public void onClick$test() { Account acc = new Account();
	 * 
	 * 
	 * acc.setBal("B"); acc.setSgn("P"); acc.setAcc_bal("20206");
	 * acc.setCurrency("840"); acc.setId_order("001");
	 * acc.setId("20206840199313762001"); acc.setAcc_group_id(101);
	 * acc.setName("makhmudov jasur anvarovich".toUpperCase());
	 * acc.setClient("99313762"); acc.setBranch(branch); acc.setSign_registr(2);
	 * 
	 * AccountService.doAction_br(un,pwd,acc,2,alias, true); }
	 */
}
