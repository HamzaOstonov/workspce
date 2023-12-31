package com.is.clienta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.zkoss.json.JSONObject;
import org.zkoss.json.parser.JSONParser;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.clienta.utils.Util;
import com.is.clienta.utils.ZkUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")

public class ClientAViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	private static final boolean isDebugEnabled = true;

	// private AbstractSapOrganizationService<ClientJ> organizationService;
	// private AbstractSapOrganizationService<ClientJ> sapOrganizationService;
	// private AbstractSapOrganizationService<ClientJ> localOrganizationService;

	enum CltPaths {
		LEGAL_ENTITY("�����������"),
		// IP("�������������� ���������������"),
		LEGAL_ENTITY_NIBBD("�����������, ������� �������� ���� � ������ �����"),
		// IP_ENTITY_NIBBD("�������������� ���������������, ������� ��������
		// ���� � ������ �����");
		PHYSICAL("����������");

		private String desc;

		private CltPaths(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	protected DictionaryKeeper dictionaryKeeper;
	protected ClientAService clientService;

	private Window dp_wnd;
	private Include dp_wnd$incl_cp;

	private EventQueue eq;

	private String err_msg;

	protected enum SearchMode {
		DIR_NEW, ACC_NEW, DIR_FOR_CURRENT, ACC_FOR_CURRENT;
	}

	protected Window sap_bapiret, sector_wnd, nibbd_modal, nibbd_wnd, account_wnd, fields_diff_wnd, searchWnd,
			clientmain, specclt_wnd, clt_dlg_wnd, director_search_wnd, appsWnd;
	protected Div grd, frm, filterdiv, addgrd, account_parent, apersons_div, specclt_div, appsDiv, j_view, i_view,
			ipView, j_acc_grid, ip_acc_grid, director_grb, accounter_grb, j_contact_grid, client_bank__grid_main,
			client_bank_grid_acc, p_view, p_header, p_capacity_div, p_acc_grid, j_header, i_acc_grid, fp_view;
	protected Grid fields_diff_wnd$diff_grid, gr_fjView, gr_grpfltmain;
	protected Rows fields_diff_wnd$diff_rows;
	protected Toolbar actions_bar;
	protected Radiogroup clt_dlg_wnd$paths;

	protected Listbox dataGrid, history, sap_bapiret$messages, atachments, searchWnd$searchListbox;
	protected Tabbox cl_tabs;
	protected Tab acc_tab, specclt_tab, attach_tab, history_tab;// ,
																// cards_tab;
	protected Tabpanel acc_tab_panel, specclt_tab_panel, attach_tab_panel, history_tab_panel;// ,
	protected Include incl_acc, incl_cards, director_search_wnd$search_incl;

	protected Groupbox aj_view, aj_acc_grid, aj_other_grid, aj_contact_grid, aipView, aipView2, aclient_bank_grid_acc,
			aclient_bank_grid_main, aaccounter_grb, adirector_grb;
	protected Groupbox fjView, fipView, fipView2, facc_grb, fother_grb, fcontacs_grb, directors_grb, grpfltmain;
	protected Row aactivity_type_row, row_non_resident, row_non_resident_new, row_OKED_new, rowIpViewPatentExpNew;
	protected Toolbarbutton btn_last, btn_next, btn_prev, btn_first, btn_save, btn_clear, btn_back, btn_get_ip,
			btn_sendFile, btn_new;
	protected Toolbar tb, top_tb;

	private Caption adir_caption, aacc_caption;
	private Window soogunWnd = null;
	private Radiogroup type_radio_open1, type_radio_close1, filterradiogrp;

	protected Checkbox j_sign_trade, j_small_business, j_sign_dep_acc, fields_diff_wnd$check_all_sap,
			fields_diff_wnd$check_all_ebp;
	protected Datebox j_date_registration, j_patent_expiration, aj_patent_expiration, documentDate;

	protected Textbox p_passport_place_registration, p_passport_serial, p_passport_number, description,
			type_non_residentValue, iopfValue, iformValue, isectorValue, iorgan_directValue, swift_idValue,
			ftax_orgValue, fcl_activity_type_idValue, fformValue, fopfValue, atype_non_residentValue, aswift_idValue,
			adirector_type_document_text, adirector_pass_place_region_text, adirector_pass_place_distr_text,
			adirector_code_citizenship_text, adirector_code_country_text, adirector_code_adr_region_text,
			adirector_code_adr_distr_text, adirector_code_tax_org_text, aaccountant_type_document_text,
			aaccountant_pass_place_region_text, aaccountant_pass_place_distr_text, aaccountant_code_citizenship_text,
			aaccountant_code_country_text, aaccountant_code_adr_region_text, aaccountant_code_adr_distr_text,
			aaccountant_code_tax_org_text, class_creditValue, a_formValue, a_organ_directValue;
	protected Datebox p_birthday, p_passport_date_registration, p_passport_date_expiration;
	protected RefCBox p_code_nation, ai_form, ai_sector, ai_organ_direct, a_type_document;
	protected Textbox p_birth_place, p_post_address, p_code_adr_distr_text, p_tax_orgValue, p_code_adr_region_text,
			p_citizenshipValue, p_pass_distrValue, p_pass_regionValue, p_code_citizenship_text, p_type_document_text,
			a_code_adr_region_text, a_code_adr_distr_text, p_type_docValue, a_code_citizenship_text,
			fp_code_citizenship_text, fp_type_document_text, fp_code_adr_distr_text, fp_code_adr_region_text,
			fp_code_capacity_text, ap_first_name, ap_family, p_first_name, p_family, p_code_tax_org_text;

	protected Textbox j_short_name, j_place_regist_name, name, j_number_registration_doc, j_number_tax_registration,
			j_code_head_organization, j_account, j_post_address, j_phone, j_fax, j_email, j_soato, j_okpo,
			j_inn_head_organization, j_responsible_emp, a_type_document_text;
	protected Textbox typeValue, regionValue, tax_orgValue, opfValue, formValue, oldsectorValue, organ_directValue,
			distrValue, sectorValue, bankValue, code_country_value, aiopfValue, aiformValue, aisectorValue,
			aiorgan_directValue, a_tax_orgValue, a_opfValue, a_sectorValue;

	private RefCBox code_resident, code_type, code_form, state, j_code_tax_org, a_code_tax_org, j_code_organ_direct,
			j_code_bank, j_region, j_distr, j_opf, j_code_sector, cl_activity_type_id, a_opf, a_code_form,
			a_code_sector, a_code_organ_direct, a_code_bank, p_code_type, p_code_resident, p_state, ap_state;
	protected RefCBox p_code_tax_org, p_code_gender, p_type_document, p_code_adr_region, p_code_adr_distr,
			p_code_citizenship, code_country, director_type_document, director_pass_place_region,
			director_pass_place_distr, director_code_citizenship, accountant_type_document,
			accountant_pass_place_region, accountant_pass_place_distr, accountant_code_citizenship,
			accountant_code_country, accountant_code_adr_region, accountant_code_adr_distr, accountant_code_tax_org,
			a_code_adr_region, a_code_adr_distr, a_code_citizenship, a_region, a_distr, p_code_country;

	protected Textbox ftypeValue, fregionValue, fsectorValue, fcode_country_value;
	protected RefCBox fcode_country, fcode_resident, fcode_type, fcode_form, fstate, fj_code_tax_org, fsign_registr,
			fj_code_organ_direct, fj_code_class_credit, fj_code_bank, fj_region, fj_distr, fj_opf, fj_code_sector,
			j_code_class_credit, a_code_class_credit, p_code_class_credit;

	protected RefCBox fp_code_tax_org, fp_code_gender, fp_type_document, fp_code_adr_region, fp_code_adr_distr,
			fp_code_citizenship, fp_code_nation, fp_pass_place_region, fp_pass_place_district;
	protected Textbox fid_client;

	protected Textbox aname, aj_short_name, ap_birth_place, ap_number_tax_registration, aj_number_registration_doc,
			aj_place_regist_name, aj_okpo, aj_post_address, aj_number_tax_registration, aj_code_head_organization,
			aj_inn_head_organization, aj_soato, aj_phone, aj_fax, aj_email;
	protected Textbox atypeValue, adistrValue, acode_country_value, aregionValue, aopfValue, atax_orgValue, aformValue,
			aorgan_directValue, abankValue, asectorValue, aoldsectorValue, ap_code_adr_distrValue;
	protected RefCBox acode_country, acode_country1, acode_resident, acode_type, acode_form, aj_code_tax_org,
			aj_code_organ_direct, aj_code_bank, aj_region, aj_distr, aj_opf, aj_code_sector, acl_activity_type_id,
			fcl_activity_type_id;
	protected Checkbox aj_sign_trade, aj_small_business, aj_sign_dep_acc;
	protected Datebox aj_date_registration;
	protected RefCBox acode_subject, abranch, ap_code_citizenship, ap_code_nation, ap_type_document, ap_code_gender,
			ap_code_bank_combobox, ap_code_class_credit, ap_code_adr_region, ap_code_capacity, ap_code_tax_org,
			ap_code_adr_distr, a_code_gender, a_code_nation, p_code_capacity, p_code_bank, fp_code_capacity;
	protected Datebox ap_passport_date_registration, ap_passport_date_expiration, ap_capacity_status_date, ap_birthday;
	protected Textbox ap_passport_number, ap_passport_serial, ap_num_certif_capacity, ap_pension_sertif_serial,
			ap_capacity_status_place, ap_passport_place_registration, ap_post_address_street, ap_post_address_quarter,
			ap_post_address_flat, ap_post_address, ap_post_address_house;
	protected Textbox ap_code_citizenship_text, ap_type_document_text, ap_code_adr_region_text, ap_code_adr_distr_text,
			atype_non_resident, ap_typeValue, ap_code_country_value, ap_code_tax_org_text, ap_bankValue, p_bankValue,
			ap_code_capacity_text, ap_class_creditValue, p_code_capacity_text, p_class_creditValue;

	protected RefCBox acode_resident1, attch_types, ap_code_bank, ap_code_type, ap_code_resident, ap_code_country;

	protected Label acode_countryLabel, aresLabel, aokpo_label;
	protected Label countryLabel, resLabel, okpo_label;

	@SuppressWarnings("unused")
	protected RefCBox swift_id, type_non_resident, ai_code_sector, ai_code_organ_direct, aswift_id;

	protected RefCBox code_resident1;
	protected Textbox acl_activity_type_idValue;

	protected RefCBox adirector_type_document, adirector_code_resident, adirector_code_citizenship,
			adirector_code_country, adirector_code_gender, adirector_code_nation, adirector_code_tax_org,
			adirector_pass_place_region, adirector_pass_place_distr, adirector_code_adr_distr,
			adirector_code_adr_region;
	private Textbox adirector_passport_serial, adirector_passport_number, adirector_passport_place_registration;
	private Datebox adirector_passport_date_expiration, adirector_passport_date_registration, adirector_birthday,
			fdate_open, fdate_open1, fdate_close, fdate_close1;

	protected Checkbox adirector_pass_place_checkbox;

	protected RefCBox aaccountant_type_document, aaccountant_code_resident, aaccountant_code_citizenship,
			aaccountant_code_country, aaccountant_code_gender, aaccountant_code_nation, aaccountant_code_tax_org,
			aaccountant_pass_place_region, aaccountant_pass_place_distr, aaccountant_code_adr_distr,
			aaccountant_code_adr_region, accountant_code_nation, accountant_code_resident, accountant_code_gender;
	protected Textbox director_type_document_text, director_pass_place_region_text, director_pass_place_distr_text,
			director_code_citizenship_text, accountant_type_document_text, accountant_pass_place_region_text,
			accountant_pass_place_distr_text, accountant_code_citizenship_text, accountant_code_country_text,
			accountant_code_adr_region_text, accountant_code_adr_distr_text;
	protected Textbox cl_activity_type_idValue, accountant_code_tax_org_text;
	private Textbox aaccountant_passport_serial, aaccountant_passport_number, aaccountant_passport_place_registration;
	private Datebox aaccountant_passport_date_expiration, aaccountant_passport_date_registration, aaccountant_birthday;
	private Checkbox aaccountant_pass_place_checkbox;

	protected RefCBox sector_wnd$sectorGroups;
	protected Listbox sector_wnd$sectors;
	protected List<RefData> sgList;
	protected List<S_spr_oked> sectorsList;

	protected String sapDocId;
	protected Paging clientAPaging;
	protected int _pageSize = 15;
	protected int _startPageNumber = 0;
	protected int _firstPageNumber = 0;
	protected int _totalSize = 0;
	protected int _oldSelectedIndex = 0;
	protected boolean _needsTotalSizeUpdate = true;
	protected String alias, ses_branch, un, pw;
	// protected HashMap<String, String> attachMap;
	protected HashMap<String, String> clientLettersMap;
	protected HashMap<String, String> clientStates;
	protected Map<Integer, String> actionsMap;
	protected Map<String, String> clientTypes;
	protected ClientA copyOfCurrent;
	public ClientA current = new ClientA();
	
	
	private Include includeDeposits;
	private Include includeClientInfo;
	private Include includeTransfers;
	private Include includeCards;
	private Include includeAccounts;

	
	

	public ClientA getCurrent() {
		return current;
	}

	public void setCurrent(ClientA current) {
		this.current = current;
	}

	public ClientA getCurrentListItem() {
		return currentListItem;
	}

	public void setCurrentListItem(ClientA currentListItem) {
		this.currentListItem = currentListItem;
	}

	public ClientA getNewcl() {
		return newcl;
	}

	public void setNewcl(ClientA newcl) {
		this.newcl = newcl;
	}

	public ClientAFilter getFilter() {
		return filter;
	}

	public void setFilter(ClientAFilter filter) {
		this.filter = filter;
	}

	public ClientA currentListItem = new ClientA();
	public ClientA newcl = new ClientA();
	public ClientAFilter filter = new ClientAFilter();
	protected Dao<ClientA> clientDao;
	protected AnnotateDataBinder binder;
	protected String mode;
	protected SearchMode searchMode;
	private static Logger logger = Logger.getLogger(ClientAViewCtrl.class);

	public ClientAViewCtrl() {
		super('$', false, false);
	}

	public void onPaging$clientAPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber, false);
	}

	// protected void setEventListeners() {
	// aaccountant_passport_date_expiration.addEventListener(Events.ON_FOCUS,
	// new PersonPassDateExpListener(newcl.getAccountant()));
	// adirector_passport_date_expiration.addEventListener(Events.ON_FOCUS, new
	// PersonPassDateExpListener(newcl.getDirector()));
	// }
	@SuppressWarnings("rawtypes")
	protected void initSearchComposer() {
		// List children = director_search_wnd$search_incl.getChildren();
	}

	protected void setModels() {
		// clientActions = dictionaryKeeper.getClientActions();
		clientLettersMap = DictionaryKeeper.getClientLetters();
		clientStates = DictionaryKeeper.getClientStatesMap();
		// attachMap = DictionaryKeeper.getAttachMap();
		actionsMap = DictionaryKeeper.getClientActionsMap();
		clientTypes = Util.listToMap(DictionaryKeeper.getClientTypes());

		type_non_resident.setModel(new ListModelList(DictionaryKeeper.getNonResidentTypes()));

		p_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));
		ap_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));
		fp_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));

		p_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));
		ap_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));
		fp_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));

		p_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		ap_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		fp_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));

		p_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		ap_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		fp_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));

		state.setModel(new ListModelList(DictionaryKeeper.getClientStates()));
		fstate.setModel(new ListModelList(DictionaryKeeper.getClientStates()));
		p_state.setModel(new ListModelList(DictionaryKeeper.getClientStates()));
		ap_state.setModel(new ListModelList(DictionaryKeeper.getClientStates()));

		code_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		p_code_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		ap_code_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		fcode_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));

		code_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		p_code_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		ap_code_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		fcode_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));

		code_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		fcode_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		p_code_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		ap_code_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));

		code_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		// acode_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		// fcode_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		// ai_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		// a_code_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));

		j_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		// a_code_tax_org.setModel(new
		// ListModelList(DictionaryKeeper.getGni()));
		p_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		ap_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));

		j_code_sector.setModel(new ListModelList(DictionaryKeeper.getOked()));
		// aj_code_sector.setModel(new
		// ListModelList(DictionaryKeeper.getOked()));*/
		// fj_code_sector.setModel(new
		// ListModelList(DictionaryKeeper.getOked()));
		// ai_code_sector.setModel(new
		// ListModelList(DictionaryKeeper.getOked()));
		// a_code_sector.setModel(new
		// ListModelList(DictionaryKeeper.getOked()));

		j_code_organ_direct.setModel(new ListModelList(DictionaryKeeper.getSoogun()));
		// aj_code_organ_direct.setModel(new
		// ListModelList(DictionaryKeeper.getSoogun()));
		// ai_code_organ_direct.setModel(new
		// ListModelList(DictionaryKeeper.getSoogun()));
		// a_code_organ_direct.setModel(new
		// ListModelList(DictionaryKeeper.getSoogun()));

		j_code_bank.setModel(new ListModelList(DictionaryKeeper.getBanks()));
		// aj_code_bank.setModel(new
		// ListModelList(DictionaryKeeper.getBanks()));
		// a_code_bank.setModel(new ListModelList(DictionaryKeeper.getBanks()));
		p_code_bank.setModel(new ListModelList(DictionaryKeeper.getBanks()));
		ap_code_bank.setModel(new ListModelList(DictionaryKeeper.getBanks()));

		j_code_class_credit.setModel(new ListModelList(DictionaryKeeper.getCreditklass()));
		// a_code_class_credit.setModel(new
		// ListModelList(DictionaryKeeper.getCreditklass()));
		p_code_class_credit.setModel(new ListModelList(DictionaryKeeper.getCreditklass()));
		ap_code_class_credit.setModel(new ListModelList(DictionaryKeeper.getCreditklass()));

		j_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		p_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		ap_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		fp_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));

		j_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		p_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		ap_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		fp_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));

		j_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));
		// aj_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));*/
		// fj_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));
		// a_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));

		/*
		 * adirector_code_country.setModel(new
		 * ListModelList(DictionaryKeeper.getCountries()));
		 * adirector_code_citizenship.setModel(new
		 * ListModelList(DictionaryKeeper.getCountries()));
		 * adirector_code_resident.setModel(new
		 * ListModelList(DictionaryKeeper.getResidentTypes()));
		 * adirector_type_document.setModel(new
		 * ListModelList(DictionaryKeeper.getPassportTypes()));
		 * adirector_code_gender.setModel(new
		 * ListModelList(DictionaryKeeper.getGender()));
		 * adirector_code_nation.setModel(new
		 * ListModelList(DictionaryKeeper.getNations()));
		 * adirector_code_adr_region.setModel(new
		 * ListModelList(DictionaryKeeper.getRegions()));
		 * adirector_code_adr_distr.setModel(new
		 * ListModelList(DictionaryKeeper.getDistricts()));
		 * adirector_pass_place_region.setModel(new
		 * ListModelList(DictionaryKeeper.getRegions()));
		 * adirector_pass_place_distr.setModel(new
		 * ListModelList(DictionaryKeeper.getDistricts()));
		 * adirector_code_tax_org.setModel(new
		 * ListModelList(DictionaryKeeper.getGni()));
		 * aaccountant_code_country.setModel(new
		 * ListModelList(DictionaryKeeper.getCountries()));
		 * aaccountant_code_citizenship.setModel(new
		 * ListModelList(DictionaryKeeper.getCountries()));
		 * aaccountant_code_resident.setModel(new
		 * ListModelList(DictionaryKeeper.getResidentTypes()));
		 * aaccountant_type_document.setModel(new
		 * ListModelList(DictionaryKeeper.getPassportTypes()));
		 * aaccountant_code_gender.setModel(new
		 * ListModelList(DictionaryKeeper.getGender()));
		 * aaccountant_code_nation.setModel(new
		 * ListModelList(DictionaryKeeper.getNations()));
		 * aaccountant_code_adr_region.setModel(new
		 * ListModelList(DictionaryKeeper.getRegions()));
		 * aaccountant_code_adr_distr.setModel(new
		 * ListModelList(DictionaryKeeper.getDistricts()));
		 * aaccountant_pass_place_region.setModel(new
		 * ListModelList(DictionaryKeeper.getRegions()));
		 * aaccountant_pass_place_distr.setModel(new
		 * ListModelList(DictionaryKeeper.getDistricts()));
		 * aaccountant_code_tax_org.setModel(new
		 * ListModelList(DictionaryKeeper.getGni()));
		 */

		director_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		director_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		accountant_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		accountant_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));

		// attch_types.setModel(new
		// ListModelList(DictionaryKeeper.getAttachTypes()));

		swift_id.setModel(new ListModelList(DictionaryKeeper.getSwift()));
		cl_activity_type_id.setModel(new ListModelList(DictionaryKeeper.getActivityType()));
		// acl_activity_type_id.setModel(new
		// ListModelList(DictionaryKeeper.getActivityType()));*/
		// fcl_activity_type_id.setModel(new
		// ListModelList(DictionaryKeeper.getActivityType()));

		p_code_capacity.setModel(new ListModelList(DictionaryKeeper.getCapacity()));
		ap_code_capacity.setModel(new ListModelList(DictionaryKeeper.getCapacity()));
		fp_code_capacity.setModel(new ListModelList(DictionaryKeeper.getCapacity()));

	}

	protected void refreshModel(int activePage, boolean showFirstPage) {// ,
																		// Boolean
																		// getSs_dblink_branches
		clientAPaging.setPageSize(_pageSize);
		// 2017-11-21

		// ----
		// clientDao.setFilter(filter);
		// List<ClientA> clientList = clientDao.getListWithPaging(activePage,
		// _pageSize);
		// ClientAPaging.setTotalSize(clientDao.getCount());

		List<ClientA> clientList = ClientAService.getListWithPaging(activePage, _pageSize, filter, un, pw, alias);
		clientAPaging.setTotalSize(ClientAService.getCount(filter, un, pw, alias));

		dataGrid.setModel(new BindingListModelList(clientList, true));
		if (clientList.size() > 0) {
			if (_oldSelectedIndex <= dataGrid.getItems().size() - 1)
				dataGrid.setSelectedIndex(_oldSelectedIndex);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
			// this.currentListItem =clientList.get(_oldSelectedIndex);
			// sendSelEvt();
		}

	}

	protected void initNewClientJ() {
		ZkUtils.clearForm(addgrd);
		// btn_save.setLabel(Labels.getLabel("save"));
		// btn_save.setAttribute("action", "open");
		// btn_save.setImage("/images/save.png");
		addgrd.setAttribute("mode", "new");

		newcl.setCode_resident(ClientUtil.CODE_RESIDENT);
		newcl.setSign_registr("" + ClientUtil.SIGN_REGISTR_PRIMARY);
		newcl.setBranch(ses_branch);
		newcl.setCode_country(ClientUtil.COUNTRY_UZ);
		newcl.setJ_code_bank(ses_branch);
		newcl.setAddressCountry(ClientUtil.COUNTRY_UZ);
		aj_sign_trade
				.setChecked(newcl.getJ_sign_trade() != null && newcl.getJ_sign_trade().equals(ClientUtil.CHECKBOX_Y));
		aj_small_business.setChecked(
				newcl.getJ_small_business() != null && newcl.getJ_small_business().equals(ClientUtil.CHECKBOX_Y));
		binder.loadAll();
		showFormForNewClient();
		showCodesForNewcl();
		tb.setVisible(false);
		btn_clear.setVisible(false);
	}

	protected void showCodesForCurrent() {
		typeValue.setValue(current.getCode_type());
		regionValue.setValue(current.getJ_region());
		distrValue.setValue(current.getJ_distr());
		tax_orgValue.setValue(current.getJ_code_tax_org());
		opfValue.setValue(current.getJ_opf());
		formValue.setValue(current.getCode_form());
		sectorValue.setValue(current.getJ_code_sector());
		organ_directValue.setValue(current.getJ_code_organ_direct());
		p_type_document_text.setValue(current.getP_type_document());
		p_code_citizenship_text.setValue(current.getP_code_citizenship());
		p_code_adr_region_text.setValue(current.getP_code_adr_region());
		p_code_adr_distr_text.setValue(current.getP_code_adr_distr());
		bankValue.setValue(current.getJ_code_bank());
		code_country_value.setValue(current.getCode_country());
		((Textbox) cl_activity_type_id.getPreviousSibling()).setValue(current.getJ_type_activity());

	}

	protected void showCodesForNewcl() {
		atypeValue.setValue(newcl.getCode_type());
		acode_country_value.setValue(newcl.getCode_country());
		aregionValue.setValue(newcl.getJ_region());
		adistrValue.setValue(newcl.getJ_distr());
		atax_orgValue.setValue(newcl.getJ_code_tax_org());
		aopfValue.setValue(newcl.getJ_opf());
		aformValue.setValue(newcl.getCode_form());
		asectorValue.setValue(newcl.getJ_code_sector());
		aorgan_directValue.setValue(newcl.getJ_code_organ_direct());
		ap_type_document_text.setValue(newcl.getP_type_document());
		ap_code_citizenship_text.setValue(newcl.getP_code_citizenship());
		ap_code_adr_region_text.setValue(newcl.getP_code_adr_region());
		ap_code_adr_distr_text.setValue(newcl.getP_code_adr_distr());
		abankValue.setValue(newcl.getJ_code_bank());
		acl_activity_type_idValue.setValue(newcl.getJ_type_activity());
	}

	protected void showCodesForNewclP() {
		ap_typeValue.setValue(newcl.getCode_type());
		ap_code_country_value.setValue(newcl.getCode_country());
		ap_code_tax_org_text.setValue(newcl.getJ_code_tax_org());
		ap_type_document_text.setValue(newcl.getP_type_document());
		ap_code_citizenship_text.setValue(newcl.getP_code_citizenship());
		ap_code_adr_region_text.setValue(newcl.getP_code_adr_region());
		ap_code_adr_distr_text.setValue(newcl.getP_code_adr_distr());
		// abankValue.setValue(newcl.getJ_code_bank());
		// acl_activity_type_idValue.setValue(newcl.getJ_type_activity());
	}

	protected void initNewClientP() {
		ZkUtils.clearForm(addgrd);
		// btn_save.setLabel(Labels.getLabel("save"));
		// btn_save.setAttribute("action", "open");
		// btn_save.setImage("/images/save.png");
		addgrd.setAttribute("mode", "new");

		newcl.setCode_resident(ClientUtil.CODE_RESIDENT);
		newcl.setSign_registr("" + ClientUtil.SIGN_REGISTR_PRIMARY);
		newcl.setBranch(ses_branch);
		newcl.setCode_country(ClientUtil.COUNTRY_UZ);
		newcl.setJ_code_bank(ses_branch);
		newcl.setAddressCountry(ClientUtil.COUNTRY_UZ);
		newcl.setCode_type("08");
		newcl.setP_code_citizenship(ClientUtil.COUNTRY_UZ);
		newcl.setP_code_capacity("0801");
		newcl.setState("0");
		newcl.setP_code_bank(ses_branch);
		newcl.setP_passport_type("N");

		binder.loadAll();
		// showFormForNewClient();
		hideAll();
		ap_code_type.setDisabled(true);
		ap_typeValue.setDisabled(true);
		addgrd.setVisible(true);

		showCodesForNewclP();
		tb.setVisible(false);
		btn_clear.setVisible(false);
		drawActions(newcl);
	}

	private void drawActions(ClientA client) {
		// top_tb.getChildren().clear();
		Map<Integer, String> availableActionsMap = dictionaryKeeper.getAvailableActionsMap(client, un, pw, alias);
		List<Component> list = top_tb.getChildren();
		for (Component comp : list) {
			if (comp instanceof Toolbarbutton) {
				if (((Toolbarbutton) comp).getId().contains("act0")) {
					((Toolbarbutton) comp).setDisabled(true);
					for (Map.Entry<Integer, String> entry : availableActionsMap.entrySet()) {
						String ss = entry.getKey().toString();
						ss = StringUtils.leftPad(ss, 3, "0");
						ss = "act" + ss;
						if (((Toolbarbutton) comp).getId().contains(ss)) {
							((Toolbarbutton) comp).setDisabled(false);
						}
					}
				}
			}
		}
		btn_new.setDisabled(false);
	}

	public void onClick$btn_find_dir_n() {
		searchMode = SearchMode.DIR_NEW;
		director_search_wnd.setVisible(true);
	}

	public void onClick$btn_find_accountant_n() {
		searchMode = SearchMode.ACC_NEW;
		director_search_wnd.setVisible(true);
	}

	public void onClick$btn_find_dir_c() {
		searchMode = SearchMode.DIR_FOR_CURRENT;
		director_search_wnd.setVisible(true);
	}

	public void onClick$btn_find_accountant_c() {
		searchMode = SearchMode.ACC_FOR_CURRENT;
		director_search_wnd.setVisible(true);
	}

	public void onClick$btn_query() throws Exception {
		checkScaner(); //rabotaet na servere
	}
	
	public void checkScaner() throws Exception {
		URL url = new URL("http://localhost:9999/?page=1");
		URLConnection con = url.openConnection();
		// String userpass = postalCodeCheckerKey + ":" +
		// postalCodeCheckerSecret;
		// String basicAuth = "Basic " +
		// javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		// con.setRequestProperty("Authorization", basicAuth);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		JSONParser parser = new JSONParser();
		JSONObject map = (JSONObject) parser.parse(in);
		// customer.setAddress((String) map.get("street"));
		// customer.setCity((String) map.get("city"));
		newcl.setP_family((String) map.get("surname"));
		newcl.setP_first_name((String) map.get("name"));
		ap_family.setValue((String) map.get("surname"));
		ap_first_name.setValue((String) map.get("name"));		
		//ap_fam
		if (current!=null)
		{
			current.setP_family((String) map.get("surname"));
			current.setP_first_name((String) map.get("name"));
			p_family.setValue((String) map.get("surname"));
			p_first_name.setValue((String) map.get("name"));
		}
	}

	public void onDoubleClick$dataGrid$grd() {
		showFormForCurrent();
		cl_tabs.setVisible(true);
		cl_tabs.setSelectedIndex(0);
		try {
			if (currentListItem == null) {
				return;
			}
			current = ClientAService.getItemByStringId(currentListItem.getBranch(), currentListItem.getId_client(),
					un, pw, alias);
			if (current != null)
				copyOfCurrent = current.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		if (current != null) {
			showView_form(Integer.parseInt(current.getSign_registr()));
			if (current.getCode_type() != null) {
				showView_frm(current.getCode_type());
			}
			hideTabs(!current.getState().equals("1"));
			// setActionBar();
			j_small_business.setChecked(current.getJ_small_business() != null
					&& current.getJ_small_business().equals(ClientUtil.CHECKBOX_Y));
			j_sign_trade.setChecked(
					current.getJ_sign_trade() != null && current.getJ_sign_trade().equals(ClientUtil.CHECKBOX_Y));
			j_sign_dep_acc.setChecked(
					current.getJ_sign_dep_acc() != null && current.getJ_sign_dep_acc().equals(ClientUtil.CHECKBOX_Y));
			if (current.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
				current.setP_number_tax_registration(current.getJ_number_tax_registration());
				current.setP_code_tax_org(current.getJ_code_tax_org());
			}
			showCodesForCurrent();
			j_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
			Events.sendEvent(code_type, new Event(Events.ON_SELECT));
		}
		binder.loadAll();

		// Events.sendEvent(new Event("onInitRender",j_code_sector_old));
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
	}

	public void onClick$btn_back() {
		if (!grd.isVisible()) {
			hideAll();
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_cancel() {
		if (filterdiv.isVisible()) {
			// filter = new ClientAFilter();
			filter.clearFilterFields();// ����� ����� �� 21,11,2017
		}
		onClick$btn_back();
		refreshModel(_startPageNumber, false);
	}

	public void onClick$btn_refresh() {
		if (grd.isVisible()) {
			if (filter == null)
				filter = new ClientAFilter();
			else
				filter.clearFilterFields();// ����� ����� �� 21,11,2017
			refreshModel(_startPageNumber, true);
		} else if (cl_tabs.isVisible() && cl_tabs.getSelectedIndex() == 0) {
			refreshCurrent();
		}
	}

	protected void refreshCurrent() {
		setCurrent(clientService.getItemByStringId(current.getBranch(), current.getId_client(), un, pw, alias));
		onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_show_search() {
		hideAll();
		filterdiv.setVisible(true);
		btn_save.setLabel(Labels.getLabel("search"));
		btn_save.setImage("/images/search.png");
		btn_save.setAttribute("action", "filter");
		fid_client.setFocus(true);
		fjView.setOpen(false);
		fipView.setOpen(false);
		fipView2.setOpen(false);
		facc_grb.setOpen(false);
		fother_grb.setOpen(false);
		fcontacs_grb.setOpen(false);
		tb.setVisible(true);
		btn_clear.setVisible(true);
	}

	public void onClick$btn_clear() {
		// filter = new ClientAFilter();
		filter.clearFilterFields();// ����� ����� �� 21,11,2017
		ZkUtils.clearForm(filterdiv);
		// fsign_registr1.setChecked(false);
		// fsign_registr3.setChecked(false);
		type_radio_open1.setSelectedIndex(0);
		onCheck$type_radio_open1();
		type_radio_close1.setSelectedIndex(0);
		onCheck$type_radio_close1();
		filterradiogrp.setSelectedIndex(-1);
		onCheck$filterradiogrp();
	}

	public void onClick$btn_show_search_sap() {
		hideAll();

		filterdiv.setVisible(true);
		btn_save.setLabel(Labels.getLabel("search"));
		btn_save.setImage("/images/search.png");
		btn_save.setAttribute("action", "filter");
		fid_client.setFocus(true);
		tb.setVisible(true);
		btn_clear.setVisible(true);
		// Events.sendEvent(new Event(Events.ON_CHECK, filterradiogroup));
		onCheck$filterradiogrp();
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(dataGrid.getItems().size() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (dataGrid.getItems().size() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	protected void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (dataGrid.getItems().size() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);

		if (cl_tabs.isVisible()) {
			onDoubleClick$dataGrid$grd();
		}
		// else {
		// if (currentListItem != null) {
		// current =
		// ClientAService.getItemByStringId(currentListItem.getBranch(),
		// currentListItem.getId_client(),
		// alias);
		// }
		// }
		// if (current != null)
		// drawActions(current);
	}

	public void select_code_type() {
		if (current.getCode_type() != null) {
			// boolean isIp =
			// current.getCode_type().equals(ClientUtil.CODE_TYPE_IP);
			boolean isBankType = current.getCode_type().equals(ClientUtil.CODE_TYPE_BANK);
			showView_frm(current.getCode_type());
			// rowIpViewPatentExp.setVisible(isIp ||
			// (newcl.getCode_type().equals("05")) );
			// rowIpViewPatenExpNew.setVisible(isIp);
			// j_acc_grid.setVisible(!isBankType);
			j_contact_grid.setVisible(!isBankType);
			client_bank__grid_main.setVisible(isBankType);
			client_bank_grid_acc.setVisible(isBankType);
			boolean isAllowed = current.getCode_type().equals(ClientUtil.CODE_TYPE_NON_FINANCIAL)
					|| current.getCode_type().equals(ClientUtil.CODE_TYPE_OTHER);
			// j_patent_expiration.setDisabled(!(isIp || isAllowed));
			// rowIpViewPatentExp.setVisible(isIp || isAllowed);
			Label j_patent_expiration_label = (Label) j_patent_expiration.getPreviousSibling();
			j_patent_expiration_label
					.setValue(isAllowed == true ? "���� �������� ������������" : "���� �������� �������");
		}
	}

	public void onChange$typeValue() {
		current.setCode_type(typeValue.getValue());
		// Events.sendEvent(new Event("onSelect", code_type));
		select_code_type();

	}

	public void onChange$code_type() {
		current.setCode_type(code_type.getValue());
		typeValue.setValue(code_type.getValue());
		// Events.sendEvent(new Event("onSelect", code_type));
		select_code_type();
	}

	public void onChange$regionValue() {
		current.setJ_distr(null);
		j_distr.setSelecteditem(null);
		distrValue.setValue(null);
		current.setJ_region(regionValue.getValue());
		j_region.setSelecteditem(regionValue.getValue());
		// Events.sendEvent(new Event(Events.ON_CHANGE, j_region));
		j_distr.setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, j_region.getValue(), alias)));
	}

	public void onChange$j_region() {
		current.setJ_distr(null);
		j_distr.setSelecteditem(null);
		distrValue.setValue(null);

		current.setJ_region(j_region.getValue());
		regionValue.setValue(j_region.getValue());
		j_distr.setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, j_region.getValue(), alias)));
	}

	public void onChange$distrValue() {
		current.setJ_distr(distrValue.getValue());
		j_distr.setSelecteditem(distrValue.getValue());
		// Events.sendEvent(new Event(Events.ON_CHANGE, j_distr));
	}

	public void onChange$j_distr() {
		current.setJ_distr(j_distr.getValue());
		distrValue.setValue(j_distr.getValue());
		// current.setJ_post_address(Util.getDesctiption(current.getJ_region(),
		// dictionaryKeeper.getRegions()) + " "
		// + Util.getDesctiption(current.getJ_distr(),
		// dictionaryKeeper.getDistricts()));
	}

	public void onChange$j_soato() {
		String soatoString = j_soato.getValue();
		if (j_soato.getValue() != null && j_soato.getValue().length() > 7) {
			soatoString = j_soato.getValue().substring(0, 7);
		}
		soatoString = j_soato.getValue().substring(2, 7);
		Soato soato = DictionaryKeeper.getSoatoMap().get(soatoString);
		if (soato != null) {
			current.setJ_region(soato.getRegion());
			regionValue.setValue(soato.getRegion());
			Events.sendEvent(new Event("onInitRender", j_region));
			current.setJ_distr(soato.getDistr());
			distrValue.setValue(soato.getDistr());
			Events.sendEvent(new Event("onInitRender", j_distr));
			// current.setJ_post_address(Util.getDesctiption(current.getJ_region(),
			// dictionaryKeeper.getRegions()) + " "
			// + Util.getDesctiption(current.getJ_distr(),
			// dictionaryKeeper.getDistricts()));
			current.setJ_code_tax_org(soato.getKod_gni());
			tax_orgValue.setValue(soato.getKod_gni());
			Events.sendEvent(new Event("onInitRender", j_code_tax_org));
		}
	}

	// public void onChange$fcode_type() {
	// if(fcode_type.getValue()!=null){
	// showIpView_filter(fcode_type.getValue().equals("11"));
	// }
	// }
	// public void onChange$fcode_type(){
	// filter.setCode_type(fcode_type.getValue());
	// ftypeValue.setValue(fcode_type.getValue());
	// Events.sendEvent(new Event("onSelect",fcode_type));
	// }

	public void onBlur$aname() {
		if (aj_short_name.getValue() == null || aj_short_name.getValue().isEmpty()) {
			int len = aname.getValue().length();
			aj_short_name.setValue(aname.getValue().substring(0, len < 25 ? len : 25));
			newcl.setJ_short_name(aj_short_name.getValue());
		}
	}

	public void onChange$acode_type() {
		newcl.setCode_type(acode_type.getValue());
		atypeValue.setValue(acode_type.getValue());
		select_acode_type();
	}

	public void onChange$atypeValue() {
		newcl.setCode_type(atypeValue.getValue());
		acode_type.setSelecteditem(atypeValue.getValue());
		// Events.sendEvent(new Event("onSelect", acode_type));
		select_acode_type();
	}

	public void select_acode_type() {

		boolean isBankType = newcl.getCode_type().equals(ClientUtil.CODE_TYPE_BANK);
		boolean isIp = newcl.getCode_type().equals(ClientUtil.CODE_TYPE_IP);
		if (newcl.getCode_type() != null) {
			showIpView_add(isIp);
			if (isIp) {
				newcl.setJ_opf("420");
				newcl.setCode_form("111");
				newcl.setJ_code_organ_direct("90712");
				// newcl.setJ_code_sector("96090");
				newcl.setP_code_citizenship("860");
				newcl.setPost_address_country("860");
				aformValue.setValue("111");
				aopfValue.setValue("420");
				aorgan_directValue.setValue("90712");
				// asectorValue.setValue("96090");
			}
			aopfValue.setValue(newcl.getJ_opf());
			aformValue.setValue(newcl.getCode_form());
			aorgan_directValue.setValue(newcl.getJ_code_organ_direct());
			// rowIpViewPatentExpNew.setVisible(isIp || (newcl.getCode_type() !=
			// null && newcl.getCode_type().equals("05") ));
			aj_view.setVisible(!isBankType);
			aj_acc_grid.setVisible(!isBankType);
			aj_other_grid.setVisible(!isBankType);
			aj_contact_grid.setVisible(!isBankType);
			aclient_bank_grid_main.setVisible(isBankType);
			aclient_bank_grid_acc.setVisible(isBankType);
			adirector_grb.setVisible(!isIp && !isBankType);
			aaccounter_grb.setVisible(!isIp && !isBankType);
			newcl.setCode_subject(isBankType ? ClientUtil.CODE_SUBJECT_I : ClientUtil.CODE_SUBJECT_J);

			boolean isAllowed = newcl.getCode_type().equals(ClientUtil.CODE_TYPE_NON_FINANCIAL)
					|| newcl.getCode_type().equals(ClientUtil.CODE_TYPE_OTHER);
			// aj_patent_expiration.setDisabled(!(isIp || isAllowed));
			rowIpViewPatentExpNew.setVisible(isIp || isAllowed);
			Label j_patent_expiration_label = (Label) aj_patent_expiration.getPreviousSibling();
			String patent_expiration_label_value = isAllowed == true ? "���� �������� ������������"
					: "���� �������� �������";
			j_patent_expiration_label.setValue(patent_expiration_label_value);
		}
	}

	// public void onChange$acode_type() {
	// newcl.setCode_type(acode_type.getValue());
	// atypeValue.setValue(acode_type.getValue());
	// Events.sendEvent(new Event(Events.ON_SELECT, acode_type));
	// }

	public void onChange$aregionValue() {
		newcl.setJ_distr(null);
		aj_distr.setSelecteditem(null);
		adistrValue.setValue(null);

		newcl.setJ_region(aregionValue.getValue());
		aj_region.setSelecteditem(aregionValue.getValue());
		// Events.sendEvent(new Event(Events.ON_CHANGE, aj_region));
		aj_distr.setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, aj_region.getValue(), alias)));
	}

	public void onChange$aj_region() {
		newcl.setJ_distr(null);
		aj_distr.setSelecteditem(null);
		adistrValue.setValue(null);

		newcl.setJ_region(aj_region.getValue());
		aregionValue.setValue(aj_region.getValue());
		aj_distr.setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, aj_region.getValue(), alias)));
		// newcl.setJ_post_address(Util.getDesctiption(newcl.getJ_region(),
		// dictionaryKeeper.getRegions())+" "+
		// Util.getDesctiption(newcl.getJ_distr(),
		// dictionaryKeeper.getDistricts()));
		// aj_post_address.setValue(newcl.getJ_post_address());
	}

	public void onChange$adistrValue() {
		newcl.setJ_distr(adistrValue.getValue());
		aj_distr.setSelecteditem(adistrValue.getValue());
		// Events.sendEvent(new Event(Events.ON_CHANGE, aj_distr));
	}

	public void onChange$aj_distr() {
		newcl.setJ_distr(aj_distr.getValue());
		adistrValue.setValue(aj_distr.getValue());
		// newcl.setJ_post_address(Util.getDesctiption(newcl.getJ_region(),
		// dictionaryKeeper.getRegions()) + " "
		// + Util.getDesctiption(newcl.getJ_distr(),
		// dictionaryKeeper.getDistricts()));
		// aj_post_address.setValue(newcl.getJ_post_address());
	}

	public void onChange$aj_soato() {
		String soatoString = aj_soato.getValue();
		if (soatoString.length() < 7) {
			return;
		}
		if (aj_soato.getValue() != null && aj_soato.getValue().length() > 7) {
			soatoString = aj_soato.getValue().substring(0, 7);
		}
		soatoString = aj_soato.getValue().substring(2, 7);
		Soato soato = DictionaryKeeper.getSoatoMap().get(soatoString);
		if (soato != null) {
			newcl.setJ_region(soato.getRegion());
			aregionValue.setValue(soato.getRegion());
			Events.sendEvent(new Event("onInitRender", aj_region));
			aj_distr.setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, newcl.getJ_region(), alias)));
			newcl.setJ_distr(soato.getDistr());
			adistrValue.setValue(soato.getDistr());
			Events.sendEvent(new Event("onInitRender", aj_distr));
			// newcl.setJ_post_address(Util.getDesctiption(newcl.getJ_region(),
			// dictionaryKeeper.getRegions()) + " "
			// + Util.getDesctiption(newcl.getJ_distr(),
			// dictionaryKeeper.getDistricts()));
			newcl.setJ_code_tax_org(soato.getKod_gni());
			atax_orgValue.setValue(soato.getKod_gni());
			Events.sendEvent(new Event("onInitRender", j_code_tax_org));
		}
	}


	public void onChange$ap_code_bank() {
		ap_bankValue.setValue(ap_code_bank.getValue());
		newcl.setP_code_bank(ap_code_bank.getValue());
	}

	public void onChange$p_code_bank() {
		p_bankValue.setValue(p_code_bank.getValue());
		current.setP_code_bank(p_code_bank.getValue());
	}

	public void onChange$ap_code_class_credit() {
		ap_class_creditValue.setValue(ap_code_class_credit.getValue());
		newcl.setP_code_class_credit(ap_code_class_credit.getValue());
	}

	public void onChange$p_code_class_credit() {
		p_class_creditValue.setValue(p_code_class_credit.getValue());
		current.setP_code_class_credit(p_code_class_credit.getValue());
	}

	public void onCtrlKey$asectorValue() {
		onCtrlKey$aj_code_sector();
	}

	public void onCtrlKey$sectorValue() {
		onCtrlKey$aj_code_sector();
	}

	public void onCtrlKey$aj_code_sector() {
		sector_wnd$sectors.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) throws Exception {
				S_spr_oked oked = (S_spr_oked) data;
				row.setValue(oked);
				row.appendChild(new Listcell(oked.getCode()));
				row.appendChild(new Listcell(oked.getName_ru()));
				if (okedCode != null && oked.getCode().equals(okedCode)) {
					sector_wnd$sectors.setSelectedItem(row);
					sector_wnd$sectors.addItemToSelection(row);
					sector_wnd$sectors.selectItem(row);
					okedCode = null;
				}
			}
		});
		if (sgList == null || sectorsList == null) {
			getSectors();

		}
		sector_wnd.setVisible(true);
	}

	public void getSectors() {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			sgList = ClientDictionaries.getSectorGroups(c);
			sectorsList = ClientDictionaries.getSectors(c);
			sector_wnd$sectorGroups.setModel(new ListModelList(sgList));
			sector_wnd$sectors.setModel(new BindingListModelList(sectorsList, true));
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public void onChange$sectorGroups$sector_wnd() {
		String selItem = sector_wnd$sectorGroups.getValue();
		if (selItem != null) {
			sector_wnd$sectors.setModel(new BindingListModelList(sortSectorsByGroup(selItem), true));
		}
	}

	public void onClick$find_sector$sector_wnd() throws InterruptedException {
		// Messagebox.show(ClientDictionaries.getSectorByName(sector_wnd$item.getValue(),alias).size()
		// + "");
		if (sector_wnd$item.getValue() == null || sector_wnd$item.getValue().isEmpty()) {
			getSectors();
		}
		sector_wnd$sectors.setModel(
				new BindingListModelList(ClientDictionaries.getSectorByName(sector_wnd$item.getValue(), alias), true));
	}

	public void onClick$clear$sector_wnd() {
		sector_wnd$item.setValue(null);
	}

	protected Textbox sector_wnd$item;
	protected String okedCode;

	public void onClick$btn_pick$sector_wnd() {
		S_spr_oked selItem = (S_spr_oked) sector_wnd$sectors.getSelectedItem().getValue();
		if (addgrd.isVisible()) {
			newcl.setJ_code_sector(selItem.getCode());
			asectorValue.setValue(selItem.getCode());
			aj_code_sector.setValue(selItem.getName_ru());
		} else if (cl_tabs.isVisible()) {
			current.setJ_code_sector(selItem.getCode());
			sectorValue.setValue(selItem.getCode());
			j_code_sector.setValue(selItem.getName_ru());
		}
		sector_wnd.setVisible(false);
	}

	public void onCheckField(Event event) {
		Checkbox checkBox = (Checkbox) ((ForwardEvent) event).getOrigin().getTarget();
		if (checkBox.getId().equalsIgnoreCase("ap_pass_place_checkbox")) {
			if (checkBox.isChecked()) {
				newcl.setP_code_adr_distr(null);
				newcl.setP_code_adr_region(null);
			}
		} else if (checkBox.getId().equalsIgnoreCase("p_pass_place_checkbox")) {
			if (checkBox.isChecked()) {
				current.setP_pass_place_district(null);
				current.setP_pass_place_region(null);
			}
		}

	}

	public void onCtrlKey$organ_directValue(KeyEvent keyEvent) throws InterruptedException {
		int keyCode = keyEvent.getKeyCode();
		if (keyCode == 120)
			showSoogunWnd();
	}

	private void showSoogunWnd() throws InterruptedException {
		if (soogunWnd == null)
			soogunWnd = (Window) Executions.createComponents("/soogun.zul", self, null);
		soogunWnd.setVisible(true);
		soogunWnd.doModal();
		soogunWnd.setClosable(true);
		soogunWnd.setTitle("������ ����������");
		soogunWnd.setMaximizable(true);
		soogunWnd.addEventListener("onNotifySoogun", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				/*
				 * soogunWnd.setVisible(false); Soogun soogun = (Soogun)
				 * event.getData();
				 * 
				 * if (addgrd.isVisible()) {
				 * newcl.setJ_code_organ_direct(soogun.getData()); } else if
				 * (cl_tabs.isVisible()) {
				 * current.setJ_code_organ_direct(soogun.getData()); }
				 */

				binder.loadAll();
			}
		});
	}

	public void onCtrlKey$aorgan_directValue(KeyEvent keyEvent) throws InterruptedException {
		int keyCode = keyEvent.getKeyCode();
		if (keyCode == 120)
			showSoogunWnd();
	}

	public void onOKField(Event e) {
		String fieldId = (((ForwardEvent) e).getOrigin().getTarget()).getId();
		logger.error("FIELD _ID " + fieldId + " " + fieldId.isEmpty());
		if (fieldId == null || fieldId.isEmpty())
			return;

		fieldId = fieldId.substring(1);
		switch (ClientFields.valueOf(fieldId.toUpperCase())) {
		case CODE_TYPE:
			if (aipView.isVisible()) {
				aname.focus();
				aname.select();
			} else {
				acode_country_value.focus();
				acode_country_value.select();
			}
			break;
		case CODE_COUNTRY:
			acode_resident.focus();
			acode_resident.select();
			break;
		case CODE_RESIDENT:
			aname.focus();
			aname.select();
			break;
		case NAME:
			aj_short_name.focus();
			aj_short_name.select();
			break;
		case J_SHORT_NAME:
			aj_number_registration_doc.focus();
			aj_number_registration_doc.select();
			break;
		case J_NUMBER_REGISTRATION_DOC:
			aj_date_registration.focus();
			aj_date_registration.select();
			break;
		case J_DATE_REGISTRATION:
			aj_place_regist_name.focus();
			aj_place_regist_name.select();
			break;
		case J_PLACE_REGIST_NAME:
			if (aipView.isVisible()) {
				aopfValue.focus();
				aopfValue.select();
			} else {
				aj_soato.focus();
				aj_soato.select();
			}
			break;
		case J_SOATO:
			aregionValue.focus();
			aregionValue.select();
			break;
		case J_REGION:
			adistrValue.focus();
			adistrValue.select();
			break;
		case J_DISTR:
			aj_post_address.focus();
			aj_post_address.select();
			break;
		case J_POST_ADDRESS:
			aj_number_tax_registration.focus();
			aj_number_tax_registration.select();
			break;
		case J_NUMBER_TAX_REGISTRATION:
			atax_orgValue.focus();
			atax_orgValue.select();
			break;
		case J_CODE_TAX_ORG:
			aj_code_head_organization.focus();
			aj_code_head_organization.select();
			break;
		case J_CODE_HEAD_ORGANIZATION:
			aj_inn_head_organization.focus();
			aj_inn_head_organization.select();
			break;
		case J_INN_HEAD_ORGANIZATION:
			aj_okpo.focus();
			aj_okpo.select();
			break;
		case J_OKPO:
			aopfValue.focus();
			aopfValue.select();
			break;
		case J_OPF:
			aformValue.focus();
			aformValue.select();
			break;
		case CODE_FORM:
			asectorValue.focus();
			asectorValue.select();
			break;
		// case J_CODE_SECTOR_OLD:
		// asectorValue.focus();asectorValue.select();break;
		case J_CODE_SECTOR:
			aorgan_directValue.focus();
			aorgan_directValue.select();
			break;
		case J_CODE_ORGAN_DIRECT:
			if (aipView.isVisible()) {
				ap_type_document_text.focus();
				ap_type_document_text.select();
			} else {
				aj_phone.focus();
				aj_phone.select();
			}
			break;
		case P_TYPE_DOCUMENT:
			ap_passport_serial.focus();
			ap_passport_serial.select();
			break;
		case P_PASSPORT_NUMBER:
			break;
		case P_PASS_PLACE_REGION:
			break;
		case P_PASS_PLACE_DISTRICT:
			ap_passport_place_registration.focus();
			ap_passport_place_registration.select();
			break;
		case P_PASSPORT_PLACE_REGISTRATION:
			ap_passport_date_registration.focus();
			ap_passport_date_registration.select();
			break;
		case P_PASSPORT_DATE_REGISTRATION:
			ap_passport_date_expiration.focus();
			ap_passport_date_expiration.select();
			break;
		case P_PASSPORT_DATE_EXPIRATION:
			ap_code_nation.focus();
			ap_code_nation.select();
			break;
		case P_CODE_NATION:
			ap_code_citizenship_text.focus();
			ap_code_citizenship.select();
			break;
		case P_CODE_CITIZENSHIP:
			acode_resident1.focus();
			acode_resident1.select();
			break;
		case CODE_RESIDENT1:
			ap_code_gender.focus();
			ap_code_gender.select();
			break;
		case P_CODE_GENDER:
			ap_birthday.focus();
			ap_birthday.select();
			break;
		case P_BIRTHDAY:
			ap_birth_place.focus();
			ap_birth_place.select();
			break;
		case P_BIRTH_PLACE:
			break;
		case CODE_COUNTRY1:
			ap_code_adr_region_text.focus();
			ap_code_adr_region_text.select();
			break;
		case P_CODE_ADR_REGION:
			ap_code_adr_distr_text.focus();
			ap_code_adr_distr_text.select();
			break;
		case P_CODE_ADR_DISTR:
			ap_post_address_street.focus();
			ap_post_address_street.select();
			break;
		case P_POST_ADDRESS_STREET:
			ap_post_address_quarter.focus();
			ap_post_address_quarter.select();
			break;
		case P_POST_ADDRESS_QUARTER:
			ap_post_address_house.focus();
			ap_post_address_house.select();
			break;
		case P_POST_ADDRESS_HOUSE:
			ap_post_address_flat.focus();
			ap_post_address_flat.select();
			break;
		case P_POST_ADDRESS_FLAT:
			ap_post_address.focus();
			ap_post_address.select();
			break;
		case P_POST_ADDRESS:
			ap_number_tax_registration.focus();
			ap_number_tax_registration.select();
			break;
		case P_NUMBER_TAX_REGISTRATION:
			break;
		case P_CODE_TAX_ORG:
			aj_phone.focus();
			aj_phone.select();
			break;
		case J_SIGN_TRADE:
			aj_small_business.focus();
			break;
		case J_SMALL_BUSINESS:
			aj_phone.focus();
			aj_phone.select();
			break;
		case J_PHONE:
			aj_fax.focus();
			aj_fax.select();
			break;
		case J_FAX:
			aj_email.focus();
			aj_email.select();
			break;
		default:
			break;
		}
	}

	public void onOKField1(Event e) {
		String fieldId = (((ForwardEvent) e).getOrigin().getTarget()).getId();
		switch (ClientFields.valueOf(fieldId.toUpperCase())) {
		case CODE_TYPE:
			if (ipView.isVisible()) {
				name.focus();
				aname.select();
			} else {
				code_country_value.focus();
				code_country_value.select();
			}
			break;
		case CODE_COUNTRY:
			code_resident.focus();
			code_resident.select();
			break;
		case CODE_RESIDENT:
			name.focus();
			name.select();
			break;
		case NAME:
			j_short_name.focus();
			j_short_name.select();
			break;
		case J_SHORT_NAME:
			j_number_registration_doc.focus();
			j_number_registration_doc.select();
			break;
		case J_NUMBER_REGISTRATION_DOC:
			j_date_registration.focus();
			j_date_registration.select();
			break;
		case J_DATE_REGISTRATION:
			j_place_regist_name.focus();
			j_place_regist_name.select();
			break;
		case J_PLACE_REGIST_NAME:
			if (ipView.isVisible()) {
				opfValue.focus();
				opfValue.select();
			} else {
				j_soato.focus();
				j_soato.select();
			}
			break;
		case J_SOATO:
			regionValue.focus();
			regionValue.select();
			break;
		case J_REGION:
			distrValue.focus();
			distrValue.select();
			break;
		case J_DISTR:
			j_post_address.focus();
			j_post_address.select();
			break;
		case J_POST_ADDRESS:
			j_number_tax_registration.focus();
			j_number_tax_registration.select();
			break;
		case J_NUMBER_TAX_REGISTRATION:
			tax_orgValue.focus();
			tax_orgValue.select();
			break;
		case J_CODE_TAX_ORG:
			j_code_head_organization.focus();
			j_code_head_organization.select();
			break;
		case J_CODE_HEAD_ORGANIZATION:
			j_inn_head_organization.focus();
			j_inn_head_organization.select();
			break;
		case J_INN_HEAD_ORGANIZATION:
			j_okpo.focus();
			j_okpo.select();
			break;
		case J_OKPO:
			opfValue.focus();
			opfValue.select();
			break;
		case J_OPF:
			formValue.focus();
			formValue.select();
			break;
		case CODE_FORM:
			sectorValue.focus();
			sectorValue.select();
			break;
		// case J_CODE_SECTOR_OLD:
		// sectorValue.focus();sectorValue.select();break;
		case J_CODE_SECTOR:
			organ_directValue.focus();
			organ_directValue.select();
			break;
		case J_CODE_ORGAN_DIRECT:
			if (ipView.isVisible()) {
				p_type_docValue.focus();
				p_type_docValue.select();
			} else {
				j_phone.focus();
				j_phone.select();
			}
			break;
		case P_TYPE_DOCUMENT:
			p_passport_serial.focus();
			p_passport_serial.select();
			break;
		case P_PASSPORT_NUMBER:
			p_pass_regionValue.focus();
			p_pass_regionValue.select();
			break;
		case P_PASS_PLACE_REGION:
			p_pass_distrValue.focus();
			p_pass_distrValue.select();
			break;
		case P_PASS_PLACE_DISTRICT:
			p_passport_place_registration.focus();
			p_passport_place_registration.select();
			break;
		case P_PASSPORT_PLACE_REGISTRATION:
			p_passport_date_registration.focus();
			p_passport_date_registration.select();
			break;
		case P_PASSPORT_DATE_REGISTRATION:
			p_passport_date_expiration.focus();
			p_passport_date_expiration.select();
			break;
		case P_PASSPORT_DATE_EXPIRATION:
			p_code_nation.focus();
			p_code_nation.select();
			break;
		case P_CODE_NATION:
			p_citizenshipValue.focus();
			p_citizenshipValue.select();
			break;
		case P_CODE_CITIZENSHIP:
			code_resident1.focus();
			code_resident1.select();
			break;
		case CODE_RESIDENT1:
			p_code_gender.focus();
			p_code_gender.select();
			break;
		case P_CODE_GENDER:
			p_birthday.focus();
			p_birthday.select();
			break;
		case P_BIRTHDAY:
			p_birth_place.focus();
			p_birth_place.select();
			break;
		case P_BIRTH_PLACE:
			break;
		case CODE_COUNTRY1:
			p_code_adr_region_text.focus();
			p_code_adr_region_text.select();
			break;
		case P_CODE_ADR_REGION:
			p_code_adr_distr_text.focus();
			p_code_adr_distr_text.select();
			break;
		case P_CODE_ADR_DISTR:
			break;
		case P_POST_ADDRESS_STREET:
			break;
		case P_POST_ADDRESS_QUARTER:
			break;
		case P_POST_ADDRESS_HOUSE:
			break;
		case P_POST_ADDRESS_FLAT:
			p_post_address.focus();
			p_post_address.select();
			break;
		case P_POST_ADDRESS:
			break;
		case P_NUMBER_TAX_REGISTRATION:
			p_tax_orgValue.focus();
			p_tax_orgValue.select();
			break;
		case P_CODE_TAX_ORG:
			j_phone.focus();
			j_phone.select();
			break;
		case J_SIGN_TRADE:
			j_small_business.focus();
			break;
		case J_SMALL_BUSINESS:
			j_phone.focus();
			j_phone.select();
			break;
		case J_PHONE:
			j_fax.focus();
			j_fax.select();
			break;
		case J_FAX:
			j_email.focus();
			j_email.select();
			break;
		default:
			break;
		}
	}

	protected void hideAll() {
		cl_tabs.setVisible(false);
		grd.setVisible(false);
		filterdiv.setVisible(false);
		addgrd.setVisible(false);
		tb.setVisible(false);
	}

	protected void showFormForCurrent() {
		hideAll();
		cl_tabs.setVisible(true);
		frm.setVisible(true);
		addgrd.setVisible(false);
		// actions_bar.setVisible(true);
		hideTabs(false);
	}

	protected void showFormForNewClient() {
		hideAll();
		addgrd.setVisible(true);
		// tb.setVisible(true);
	}

	// protected void showIpView_frm(boolean isIp) {
	protected void showView_frm(String codeType) {
		boolean isIp = codeType.equals(ClientUtil.CODE_TYPE_IP);
		// boolean isBankType =
		// current.getCode_type().equals(ClientUtil.CODE_TYPE_BANK);
		boolean isForeignBank = (current.getSign_registr().equals("4"));

		ipView.setVisible(isIp);
		row_non_resident.setVisible(!isIp && !current.getSign_registr().equals("2"));
		okpo_label.setVisible(!isIp && !current.getSign_registr().equals("2"));
		j_okpo.setVisible(!isIp && !current.getSign_registr().equals("2"));
		resLabel.setVisible(!isIp && !current.getSign_registr().equals("2"));
		j_acc_grid
				.setVisible(!isIp && !current.getSign_registr().equals("2") && !current.getSign_registr().equals("4"));
		ip_acc_grid.setVisible(isIp);

		director_grb.setVisible(!isForeignBank && !isIp && !current.getSign_registr().equals("2"));
		accounter_grb.setVisible(!isForeignBank && !isIp && !current.getSign_registr().equals("2"));
		j_view.setVisible(!isIp && !isForeignBank && !current.getSign_registr().equals("2"));
		ipView.setVisible(isIp);
		// i_view.setVisible(isForeignBank);
	}

	protected void showView_form(int signRegistr) {
		j_header.setVisible(signRegistr != 2);
		ipView.setVisible(signRegistr == 1 || signRegistr == 3);
		j_acc_grid.setVisible(signRegistr == 1 || signRegistr == 3);
		ip_acc_grid.setVisible(signRegistr == 1 || signRegistr == 3);
		i_acc_grid.setVisible(signRegistr == 4);

		director_grb.setVisible(signRegistr == 1 || signRegistr == 3);
		accounter_grb.setVisible(signRegistr == 1 || signRegistr == 3);
		j_view.setVisible(signRegistr == 1 || signRegistr == 3);

		i_view.setVisible(signRegistr == 4);
		p_header.setVisible(signRegistr == 2);
		p_view.setVisible(signRegistr == 2);
		p_capacity_div.setVisible(signRegistr == 2);
		p_acc_grid.setVisible(signRegistr == 2);
		j_contact_grid.setVisible(signRegistr != 2);
	}

	protected void showIpView_add(boolean isIp) {
		aipView.setVisible(isIp);
		aipView2.setVisible(isIp);

		// aipFio.setVisible(isIp);
		aokpo_label.setVisible(!isIp);
		aj_okpo.setVisible(!isIp);
		// aonlyForJ1.setVisible(!bol);
		// aonlyForJ2.setVisible(!bol);
		// aonlyForJ3.setVisible(!bol);
		// aonlyForJ4.setVisible(!isIp);

		aresLabel.setVisible(!isIp);
		// acode_resident.setVisible(!isIp);
		// acode_countryLabel.setVisible(!isIp);
		// acode_country_value.setVisible(!isIp);
		// acode_country.setVisible(!isIp);
		aactivity_type_row.setVisible(isIp);
		aj_small_business.setChecked(isIp);
		btn_get_ip.setVisible(isIp);
		row_OKED_new.setVisible(!isIp);
	}

	protected List<S_spr_oked> sortSectorsByGroup(String groupId) {
		List<S_spr_oked> sortedList = new ArrayList<S_spr_oked>();
		for (S_spr_oked ok : sectorsList) {
			if (ok.getSection_gr().equals(groupId)) {
				sortedList.add(ok);
			}
		}
		return sortedList;
	}

	public void onChange$p_type_document() {
		if (p_type_document.getValue().equals("1")) {
			acode_resident.setSelecteditem(ClientUtil.CODE_RESIDENT);
			p_code_citizenship_text.setValue(ClientUtil.COUNTRY_UZ);
			p_code_citizenship.setSelecteditem(ClientUtil.COUNTRY_UZ);
			current.setCode_resident(ClientUtil.CODE_RESIDENT);
			current.setP_code_citizenship(ClientUtil.COUNTRY_UZ);
			p_passport_serial.setMaxlength(2);
			p_passport_number.setMaxlength(7);
			Events.postEvent(p_passport_serial, new Event("onRender"));
			Events.postEvent(p_passport_number, new Event("onRender"));
		}
		if (p_type_document.getValue().equals("2")) {
			acode_resident.setSelecteditem(ClientUtil.CODE_RESIDENT);
			current.setCode_resident(ClientUtil.CODE_RESIDENT);
			p_passport_serial.setMaxlength(9);
			p_passport_number.setMaxlength(9);
			Events.postEvent(p_passport_serial, new Event("onRender"));
			Events.postEvent(p_passport_number, new Event("onRender"));
		}
		if (p_type_document.getValue().equals("3")) {

		}
		if (p_type_document.getValue().equals("4")) {
			acode_resident.setSelecteditem(ClientUtil.NOT_RESIDENT);
			current.setCode_resident(ClientUtil.NOT_RESIDENT);
			p_passport_serial.setMaxlength(9);
			p_passport_number.setMaxlength(9);
			Events.postEvent(p_passport_serial, new Event("onRender"));
			Events.postEvent(p_passport_number, new Event("onRender"));
		}
		if (p_type_document.getValue().equals("5")) {
			acode_resident.setSelecteditem(ClientUtil.CODE_RESIDENT);
			current.setCode_resident(ClientUtil.CODE_RESIDENT);
			p_passport_serial.setMaxlength(9);
			p_passport_number.setMaxlength(9);
			Events.postEvent(p_passport_serial, new Event("onRender"));
			Events.postEvent(p_passport_number, new Event("onRender"));
		}
		if (p_type_document.getValue().equals("6")) {
			acode_resident.setSelecteditem(ClientUtil.CODE_RESIDENT);
			p_code_citizenship.setSelecteditem(ClientUtil.COUNTRY_UZ);
			p_code_citizenship_text.setValue(ClientUtil.COUNTRY_UZ);
			current.setP_code_citizenship(ClientUtil.COUNTRY_UZ);
			current.setCode_resident(ClientUtil.CODE_RESIDENT);
			p_passport_serial.setMaxlength(2);
			p_passport_number.setMaxlength(7);
			Events.postEvent(p_passport_serial, new Event("onRender"));
			Events.postEvent(p_passport_number, new Event("onRender"));
		}
		if (p_type_document.equals("9")) {

		}
		p_type_document_text.setValue(p_type_document.getValue());
		current.setP_type_document(p_type_document.getValue());
	}

	public void onChange$p_passport_date_registration() {
		if (p_type_document.getValue() != null) {
			DateTime dt = null;
			if (p_type_document.getValue().equals("1")) {
				if (p_passport_date_expiration.getValue() == null) {
					dt = new DateTime(current.getP_passport_date_registration());
					dt = dt.plusYears(25);
					dt = dt.minusDays(1);
					p_passport_date_expiration.setValue(dt.toDate());
					current.setP_passport_date_expiration(dt.toDate());
				}
			}
			if (p_type_document.getValue().equals("6")) {
				if (p_passport_date_expiration.getValue() == null) {
					dt = new DateTime(current.getP_passport_date_registration());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					p_passport_date_expiration.setValue(dt.toDate());
					current.setP_passport_date_expiration(dt.toDate());
				}
			}
		}
	}

	public void onChange$p_pass_place_region() {
	}

	public void onChange$p_pass_place_district() {
	}

	public void onChange$p_pass_place_region_text() {
		current.setP_pass_place_district(null);
	}

	public void onChange$p_pass_place_distr_text() {
	}

	public void onChange$p_code_adr_region() {
		
		//p_code_adr_distr.setModel(
		//		new ListModelList(ClientDictionaries.getDistrByRegion(null, p_code_adr_region.getValue(), alias)));
		//current.setP_code_adr_region(p_code_adr_region.getValue());
		//onChange$p_post_address_flat();
		p_code_adr_distr.setValue(null);
		p_code_adr_distr_text.setValue(null);
		current.setP_code_adr_distr(null);
		p_code_adr_distr.setModel(
				new ListModelList(ClientDictionaries.getDistrByRegion(null, p_code_adr_region.getValue(), alias)));
		p_code_adr_region_text.setValue(p_code_adr_region.getValue());
		current.setP_code_adr_region(p_code_adr_region.getValue());
	}

	public void onChange$p_code_adr_region_text() {
		//p_code_adr_distr.setModel(
		//		new ListModelList(ClientDictionaries.getDistrByRegion(null, p_code_adr_region_text.getValue(), alias)));
		//p_code_adr_region.setSelecteditem(p_code_adr_region_text.getValue());
		//current.setP_code_adr_region(p_code_adr_region_text.getValue());
		// onChange$p_post_address_flat();
		p_code_adr_distr.setValue(null);
		p_code_adr_distr_text.setValue(null);
		current.setP_code_adr_distr(null);
		p_code_adr_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, p_code_adr_region_text.getValue(), alias)));
		p_code_adr_region.setSelecteditem(p_code_adr_region_text.getValue());
		current.setP_code_adr_region(p_code_adr_region_text.getValue());
	}

	public void onChange$p_code_adr_distr() {
		/*
		 * String distrId = p_code_adr_distr.getValue();
		 * current.setP_code_adr_distr(distrId);
		 * p_code_adr_distr_text.setValue(distrId);
		 * 
		 * String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		 * current.setP_code_tax_org(taxId);
		 * p_code_tax_org.setSelecteditem(taxId);
		 */
		// onChange$p_post_address_flat();
		p_code_adr_distr_text.setValue(p_code_adr_distr.getValue());
		current.setP_code_adr_distr(p_code_adr_distr.getValue());
	}

	public void onChange$p_code_adr_distr_text() {
		/*
		 * String distrId = p_code_adr_distr_text.getValue();
		 * current.setP_code_adr_distr(distrId);
		 * p_code_adr_distr.setSelecteditem(distrId);
		 * 
		 * String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		 * current.setP_code_tax_org(taxId);
		 * p_code_tax_org.setSelecteditem(taxId);
		 */
		// onChange$p_post_address_flat();
		p_code_adr_distr.setSelecteditem(p_code_adr_distr_text.getValue());
		current.setP_code_adr_distr(p_code_adr_distr_text.getValue());
	}

	public void onChange$a_code_adr_region_text() {
		current.setP_code_adr_distr(null);
		a_code_adr_distr.setSelecteditem(null);
		a_code_adr_distr_text.setValue(null);
		current.setP_code_adr_region(a_code_adr_region_text.getValue());
		a_code_adr_region.setSelecteditem(a_code_adr_region_text.getValue());
		// Events.sendEvent(new Event(Events.ON_CHANGE, j_region));
		a_code_adr_distr.setModel(
				new ListModelList(ClientDictionaries.getDistrByRegion(null, a_code_adr_region_text.getValue(), alias)));
	}

	public void onChange$a_code_adr_region() {
		current.setP_code_adr_distr(null);
		a_code_adr_distr.setSelecteditem(null);
		a_code_adr_distr_text.setValue(null);

		current.setP_code_adr_region(a_code_adr_region.getValue());
		regionValue.setValue(a_code_adr_region.getValue());
		a_code_adr_distr.setModel(
				new ListModelList(ClientDictionaries.getDistrByRegion(null, a_code_adr_region.getValue(), alias)));
	}

	public void onChange$a_code_adr_distr_text() {
		current.setP_code_adr_distr(a_code_adr_distr_text.getValue());
		a_code_adr_distr.setSelecteditem(a_code_adr_distr_text.getValue());
		// Events.sendEvent(new Event(Events.ON_CHANGE, j_distr));
	}

	public void onChange$a_code_adr_distr() {
		current.setP_code_adr_distr(a_code_adr_distr.getValue());
		a_code_adr_distr_text.setValue(a_code_adr_distr.getValue());
	}

	public void onChange$ap_post_address_street() {
		onChange$ap_post_address_flat();
	}

	public void onChange$ap_post_address_quarter() {
		onChange$ap_post_address_flat();
	}

	public void onChange$ap_post_address_house() {
		onChange$ap_post_address_flat();
	}

	public void onChange$ap_post_address_flat() {
		ap_post_address.setValue(ap_code_adr_region.getText() + " " + ap_code_adr_distr.getText() + " "
				+ ap_post_address_street.getText() + " " + ap_post_address_quarter.getText() + " "
				+ ap_post_address_house.getText() + " " + ap_post_address_flat.getText());
		newcl.setP_post_address(ap_code_adr_region.getText() + " " + ap_code_adr_distr.getText() + " "
				+ ap_post_address_street.getText() + " " + ap_post_address_quarter.getText() + " "
				+ ap_post_address_house.getText() + " " + ap_post_address_flat.getText());
	}

	// private void makeAP_post_address() {
	// ap_post_address.setValue(
	// ap_code_adr_region.getText() + " " + ap_code_adr_distr.getText() + " " +
	// ap_post_address_street.getText() + " "
	// + ap_post_address_quarter.getText() + " " +
	// ap_post_address_house.getText() + " " + ap_post_address_flat.getText());
	// newcl.setP_post_address(
	// ap_code_adr_region.getText() + " " + ap_code_adr_distr.getText() + " " +
	// ap_post_address_street.getText() + " "
	// + ap_post_address_quarter.getText() + " " +
	// ap_post_address_house.getText() + " " + ap_post_address_flat.getText());
	// }

	public void onChange$ap_type_document() {
		if (ap_type_document.getValue().equals("1")) {
			ap_code_resident.setSelecteditem("1");
			ap_code_citizenship_text.setValue("860");
			ap_code_citizenship.setSelecteditem("860");
			newcl.setCode_resident("1");
			newcl.setP_code_citizenship("860");
			ap_passport_serial.setMaxlength(2);
			ap_passport_number.setMaxlength(7);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("2")) {
			ap_code_resident.setSelecteditem("1");
			newcl.setCode_resident("1");
			ap_passport_serial.setMaxlength(9);
			ap_passport_number.setMaxlength(9);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("3")) {

		}
		if (ap_type_document.getValue().equals("4")) {
			ap_code_resident.setSelecteditem("2");
			newcl.setCode_resident("2");
			ap_passport_serial.setMaxlength(9);
			ap_passport_number.setMaxlength(9);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("5")) {
			ap_code_resident.setSelecteditem("1");
			newcl.setCode_resident("1");
			ap_passport_serial.setMaxlength(9);
			ap_passport_number.setMaxlength(9);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("6")) {
			ap_code_resident.setSelecteditem("1");
			ap_code_citizenship.setSelecteditem("860");
			ap_code_citizenship_text.setValue("860");
			newcl.setP_code_citizenship("860");
			newcl.setCode_resident("1");
			ap_passport_serial.setMaxlength(2);
			ap_passport_number.setMaxlength(7);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.equals("9")) {

		}
		ap_type_document_text.setValue(ap_type_document.getValue());
		newcl.setP_type_document(ap_type_document.getValue());
	}

	public void onChange$ap_passport_date_registration() {
		if (ap_type_document.getValue() != null) {
			DateTime dt = null;
			if (ap_type_document.getValue().equals("1")) {
				if (ap_birthday.getValue() != null) {
					dt = new DateTime(ap_birthday.getValue());
					dt = dt.plusYears(25);
					dt = dt.minusDays(1);
					ap_passport_date_expiration.setValue(dt.toDate());
					newcl.setP_passport_date_expiration(dt.toDate());
				}
			}
			if (ap_type_document.getValue().equals("6")) {
				if (ap_passport_date_registration.getValue() != null) {
					dt = new DateTime(ap_passport_date_registration.getValue());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					ap_passport_date_expiration.setValue(dt.toDate());
					newcl.setP_passport_date_expiration(dt.toDate());
				}
			}
		}
	}

	public void onChange$ap_pass_place_region() {
	}

	public void onChange$ap_pass_place_region_text() {
	}

	public void onChange$ap_pass_place_district() {
	}

	public void onChange$ap_pass_place_distr_text() {
	}

	public void onChange$ap_code_adr_region() {
		ap_code_adr_distr.setValue(null);
		ap_code_adr_distr_text.setValue(null);
		newcl.setP_code_adr_distr(null);
		ap_code_adr_distr.setModel(
				new ListModelList(ClientDictionaries.getDistrByRegion(null, ap_code_adr_region.getValue(), alias)));
		ap_code_adr_region_text.setValue(ap_code_adr_region.getValue());
		newcl.setP_code_adr_region(ap_code_adr_region.getValue());
		// onChange$ap_post_address_flat();
	}

	public void onChange$ap_code_adr_region_text() {
		ap_code_adr_distr.setValue(null);
		ap_code_adr_distr_text.setValue(null);
		newcl.setP_code_adr_distr(null);
		ap_code_adr_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, ap_code_adr_region_text.getValue(), alias)));
		ap_code_adr_region.setSelecteditem(ap_code_adr_region_text.getValue());
		newcl.setP_code_adr_region(ap_code_adr_region_text.getValue());
		// onChange$ap_post_address_flat();
	}

	public void onChange$ap_code_adr_distr() {
		/*
		 * String distrId = ap_code_adr_distr.getValue();
		 * newcl.setP_code_adr_distr(distrId);
		 * ap_code_adr_distr_text.setValue(distrId);
		 * 
		 * String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		 * newcl.setP_code_tax_org(taxId);
		 * ap_code_tax_org.setSelecteditem(taxId);
		 */
		// onChange$ap_post_address_flat();
		ap_code_adr_distr_text.setValue(ap_code_adr_distr.getValue());
		newcl.setP_code_adr_distr(ap_code_adr_distr.getValue());
	}

	public void onChange$ap_code_adr_distr_text() {
		/*
		 * String distrId = ap_code_adr_distr_text.getValue();
		 * newcl.setP_code_adr_distr(distrId);
		 * ap_code_adr_distr.setSelecteditem(distrId);
		 * 
		 * String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		 * newcl.setP_code_tax_org(taxId);
		 * ap_code_tax_org.setSelecteditem(taxId);
		 */
		// onChange$ap_post_address_flat();
		ap_code_adr_distr.setSelecteditem(ap_code_adr_distr_text.getValue());
		newcl.setP_code_adr_distr(ap_code_adr_distr_text.getValue());
	}

	/*
	 * public void onFocus$j_phone() { if(CheckNull.isEmpty(j_phone.getValue()))
	 * { j_phone.setValue("998"); } } public void onFocus$j_fax() {
	 * if(CheckNull.isEmpty(j_fax.getValue())) { j_fax.setValue("998"); } }
	 * public void onFocus$aj_phone() {
	 * if(CheckNull.isEmpty(aj_phone.getValue())) { aj_phone.setValue("998"); }
	 * } public void onFocus$aj_fax() { if(CheckNull.isEmpty(aj_fax.getValue()))
	 * { aj_fax.setValue("998"); } }
	 */

	private Textbox ap_last_name_cyr, ap_first_name_cyr, ap_patronymic_cyr;

	public void onChange$ap_last_name_cyr() {
		newcl.setP_last_name_cyr(ap_last_name_cyr.getValue());
		names(newcl);
	}

	public void onChange$ap_first_name_cyr() {
		newcl.setP_first_name_cyr(ap_first_name_cyr.getValue());
		names(newcl);
	}

	public void onChange$ap_patronymic_cyr() {
		newcl.setP_patronymic_cyr(ap_patronymic_cyr.getValue());
		names(newcl);
	}

	/*
	 * public void onBlur$ap_patronymic_cyr() { Events.sendEvent(new
	 * Event(Events.ON_CHANGE, ap_patronymic_cyr)); }
	 */

	private void names(ClientA client) {
		client.setP_family(ClientUtil.transliterate(client.getP_last_name_cyr()).toUpperCase());
		client.setP_first_name(ClientUtil.transliterate(client.getP_first_name_cyr()).toUpperCase());
		client.setP_patronymic(ClientUtil.transliterate(client.getP_patronymic_cyr()).toUpperCase());
		if (addgrd.isVisible()) {
			StringBuilder sb = new StringBuilder();
			sb.append("YATT '").append(client.getP_family()).append(" ").append(client.getP_first_name()).append(" ")
					.append(client.getP_patronymic()).append("'");
			int len = sb.length();
			client.setJ_short_name(sb.toString().substring(0, len < 25 ? len : 25));
			// if (StringUtils.isEmpty(client.getP_first_name_cyr()))
			client.setName(sb.toString());
		}
	}

	public void onChange$adirector_type_document_text() {
		// newcl.getDirector().setType_document(adirector_type_document_text.getValue());
		adirector_type_document.setSelecteditem(adirector_type_document_text.getValue());
		change_adirector_type_document();
	}

	public void onChange$adirector_type_document() {
		adirector_type_document_text.setValue(adirector_type_document.getValue());
		// newcl.getDirector().setType_document(adirector_type_document.getValue());
		change_adirector_type_document();
	}

	public void change_adirector_type_document() {
		if (adirector_type_document.getValue().equals("1")) {
			adirector_code_resident.setSelecteditem("1");
			adirector_code_citizenship_text.setValue("860");
			adirector_code_citizenship.setSelecteditem("860");
			// newcl.getDirector().setCode_resident("1");
			// newcl.getDirector().setCode_citizenship("860");
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
			adirector_passport_serial.setMaxlength(2);
			adirector_passport_number.setMaxlength(7);
		}
		if (adirector_type_document.getValue().equals("2")) {
			adirector_code_resident.setSelecteditem("1");
			// newcl.getDirector().setCode_resident("1");
			adirector_passport_serial.setMaxlength(9);
			adirector_passport_number.setMaxlength(9);
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
		}
		if (adirector_type_document.getValue().equals("3")) {
			// newcl.getDirector().setCode_citizenship(null);
			adirector_code_citizenship_text.setValue(null);
			adirector_code_citizenship.setSelecteditem(null);
		}
		if (adirector_type_document.getValue().equals("4")) {
			adirector_code_resident.setSelecteditem("2");
			adirector_passport_number.setMaxlength(12);
			// newcl.getDirector().setCode_resident("2");
			// newcl.getDirector().setCode_citizenship(null);
			adirector_code_citizenship_text.setValue(null);
			adirector_code_citizenship.setSelecteditem(null);
			adirector_passport_serial.setMaxlength(9);
			adirector_passport_number.setMaxlength(9);
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
		}
		if (adirector_type_document.getValue().equals("5")) {
			adirector_code_resident.setSelecteditem("1");
			// newcl.getDirector().setCode_resident("1");
			// newcl.getDirector().setCode_citizenship(null);
			adirector_code_citizenship_text.setValue(null);
			adirector_code_citizenship.setSelecteditem(null);
			adirector_passport_serial.setMaxlength(9);
			adirector_passport_number.setMaxlength(9);
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
		}
		if (adirector_type_document.getValue().equals("6")) {
			adirector_code_resident.setSelecteditem("1");
			adirector_code_citizenship_text.setValue("860");
			adirector_code_citizenship.setSelecteditem("860");
			// newcl.getDirector().setCode_resident("1");
			// newcl.getDirector().setCode_citizenship("860");
			adirector_passport_serial.setMaxlength(2);
			adirector_passport_number.setMaxlength(7);
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
		}
		if (adirector_type_document.getValue().equals("9")) {
		}
	}

	public void onCheck$adirector_pass_place_checkbox() {
		adirector_pass_place_region.setDisabled(adirector_pass_place_checkbox.isChecked());
		adirector_pass_place_region_text.setDisabled(adirector_pass_place_checkbox.isChecked());
		adirector_pass_place_distr.setDisabled(adirector_pass_place_checkbox.isChecked());
		adirector_pass_place_distr_text.setDisabled(adirector_pass_place_checkbox.isChecked());
		if (adirector_pass_place_checkbox.isChecked()) {
			// newcl.getDirector().setPass_place_region(null);
			// newcl.getDirector().setPass_place_district(null);
		}
	}

	public void onChange$adirector_code_citizenship_text() {
		// newcl.getDirector().setCode_citizenship(adirector_code_citizenship_text.getValue());
		// newcl.getDirector().setCode_resident(newcl.getDirector().getCode_citizenship().equals("860")
		// ? "1" : "2");
		// adirector_code_citizenship.setSelecteditem(newcl.getDirector().getCode_citizenship());
	}

	public void onChange$adirector_code_citizenship() {
		String country = adirector_code_citizenship.getValue();
		// newcl.getDirector().setCode_citizenship(country);
		// newcl.getDirector().setCode_resident(country != null ?
		// country.equals("860") ? "1" : "2" : null);
		// adirector_code_resident.setSelecteditem(newcl.getDirector().getCode_resident());
		// adirector_code_citizenship_text.setValue(newcl.getDirector().getCode_citizenship());
	}

	public void onChange$adirector_pass_place_region() {
		adirector_pass_place_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, adirector_pass_place_region.getValue(), alias)));
		adirector_pass_place_region_text.setValue(adirector_pass_place_region.getValue());
		// newcl.getDirector().setPass_place_region(adirector_pass_place_region.getValue());
	}

	public void onChange$adirector_pass_place_region_text() {
		adirector_pass_place_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, adirector_pass_place_region_text.getValue(), alias)));
		adirector_pass_place_region.setSelecteditem(adirector_pass_place_region_text.getValue());
		// newcl.getDirector().setPass_place_region(adirector_pass_place_region.getValue());
	}

	public void onChange$adirector_pass_place_distr() {
		adirector_pass_place_distr_text.setValue(adirector_pass_place_distr.getValue());
		adirector_passport_place_registration.setText(
				adirector_pass_place_region.getText() + " " + adirector_pass_place_distr.getText() + " " + "���");
		// newcl.getDirector().setPassport_place_registration(
		// adirector_pass_place_region.getText() + " " +
		// adirector_pass_place_distr.getText() + " " + "���");
		// newcl.getDirector().setPass_place_district(adirector_pass_place_distr.getValue());
		// newcl.getDirector().setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getDirector().getPass_place_district()));
	}

	public void onChange$adirector_pass_place_distr_text() {
		adirector_pass_place_distr.setSelecteditem(adirector_pass_place_distr_text.getValue());
		adirector_passport_place_registration.setText(
				adirector_pass_place_region.getText() + " " + adirector_pass_place_distr.getText() + " " + "���");
		// newcl.getDirector().setPassport_place_registration(
		// adirector_pass_place_region.getText() + " " +
		// adirector_pass_place_distr.getText() + " " + "���");
		// newcl.getDirector().setPass_place_district(adirector_pass_place_distr.getValue());
		// newcl.getDirector().setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getDirector().getPass_place_district()));
	}

	public void onChange$adirector_code_adr_region() {
		adirector_code_adr_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, adirector_code_adr_region.getValue(), alias)));
		// newcl.getDirector().setCode_adr_region(adirector_code_adr_region.getValue());
		adirector_code_adr_region_text.setValue(adirector_code_adr_region.getValue());
	}

	public void onChange$adirector_code_adr_region_text() {
		adirector_code_adr_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, adirector_code_adr_region_text.getValue(), alias)));
		adirector_code_adr_region.setSelecteditem(adirector_code_adr_region_text.getValue());
		// newcl.getDirector().setCode_adr_region(adirector_code_adr_region_text.getValue());
	}

	public void onChange$adirector_code_adr_distr() {
		String distrId = adirector_code_adr_distr.getValue();
		// newcl.getDirector().setCode_adr_distr(distrId);
		adirector_code_adr_distr_text.setValue(distrId);
	}

	public void onChange$adirector_code_adr_distr_text() {
		String distrId = adirector_code_adr_distr_text.getValue();
		// newcl.getDirector().setCode_adr_distr(distrId);
		adirector_code_adr_distr.setSelecteditem(distrId);
	}

	public void onFocus$adirector_passport_date_expiration() {
		if (adirector_type_document.getValue() != null) {
			DateTime dt = null;
			if (adirector_type_document.getValue().equals("1")) {
				if (adirector_birthday.getValue() != null) {
					dt = new DateTime(adirector_birthday.getValue());
					dt = dt.plusYears(25);
					dt = dt.minusDays(1);
					adirector_passport_date_expiration.setValue(dt.toDate());
					// newcl.getDirector().setPassport_date_expiration(dt.toDate());
				}
			}
			if (adirector_type_document.getValue().equals("6")) {
				if (adirector_passport_date_registration.getValue() != null) {
					dt = new DateTime(adirector_passport_date_registration.getValue());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					adirector_passport_date_expiration.setValue(dt.toDate());
					// newcl.getDirector().setPassport_date_expiration(dt.toDate());
				}
			}
		}
	}

	public void onChange$aaccountant_type_document_text() {
		// newcl.getAccountant().setType_document(aaccountant_type_document_text.getValue());
		aaccountant_type_document.setSelecteditem(aaccountant_type_document_text.getValue());
		// onChange$aaccountant_type_document();
		change_aaccountant_type_document();
	}

	public void onChange$aaccountant_type_document() {
		// newcl.getAccountant().setType_document(aaccountant_type_document.getValue());
		aaccountant_type_document_text.setValue(aaccountant_type_document.getValue());
		change_aaccountant_type_document();
	}

	public void change_aaccountant_type_document() {
		if (aaccountant_type_document.getValue().equals("1")) {
			aaccountant_code_resident.setSelecteditem("1");
			aaccountant_code_citizenship_text.setValue("860");
			aaccountant_code_citizenship.setSelecteditem("860");
			// newcl.getAccountant().setCode_resident("1");
			// newcl.getAccountant().setCode_citizenship("860");
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
			aaccountant_passport_serial.setMaxlength(2);
			aaccountant_passport_number.setMaxlength(7);
		}
		if (aaccountant_type_document.getValue().equals("2")) {
			aaccountant_code_resident.setSelecteditem("1");
			// newcl.getAccountant().setCode_resident("1");
			aaccountant_passport_serial.setMaxlength(9);
			aaccountant_passport_number.setMaxlength(9);
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
		}
		if (aaccountant_type_document.getValue().equals("3")) {
			// newcl.getAccountant().setCode_citizenship(null);
			aaccountant_code_citizenship_text.setValue(null);
			aaccountant_code_citizenship.setSelecteditem(null);
		}
		if (aaccountant_type_document.getValue().equals("4")) {
			aaccountant_code_resident.setSelecteditem("2");
			aaccountant_passport_number.setMaxlength(12);
			// newcl.getAccountant().setCode_resident("2");
			// newcl.getAccountant().setCode_citizenship(null);
			aaccountant_code_citizenship_text.setValue(null);
			aaccountant_code_citizenship.setSelecteditem(null);
			aaccountant_passport_serial.setMaxlength(9);
			aaccountant_passport_number.setMaxlength(9);
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
		}
		if (aaccountant_type_document.getValue().equals("5")) {
			aaccountant_code_resident.setSelecteditem("1");
			// newcl.getAccountant().setCode_resident("1");
			// newcl.getAccountant().setCode_citizenship(null);
			aaccountant_code_citizenship_text.setValue(null);
			aaccountant_code_citizenship.setSelecteditem(null);
			aaccountant_passport_serial.setMaxlength(9);
			aaccountant_passport_number.setMaxlength(9);
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
		}
		if (aaccountant_type_document.getValue().equals("6")) {
			aaccountant_code_resident.setSelecteditem("1");
			aaccountant_code_citizenship_text.setValue("860");
			aaccountant_code_citizenship.setSelecteditem("860");
			// newcl.getAccountant().setCode_resident("1");
			// newcl.getAccountant().setCode_citizenship("860");
			aaccountant_passport_serial.setMaxlength(2);
			aaccountant_passport_number.setMaxlength(7);
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
		}
		if (aaccountant_type_document.getValue().equals("9")) {
		}
		// aaccountant_type_document_text.setValue(aaccountant_type_document.getValue());
		// newcl.getAccountant().setType_document(aaccountant_type_document.getValue());
	}

	public void onCheck$aaccountant_pass_place_checkbox() {
		aaccountant_pass_place_region.setDisabled(aaccountant_pass_place_checkbox.isChecked());
		aaccountant_pass_place_region_text.setDisabled(aaccountant_pass_place_checkbox.isChecked());
		aaccountant_pass_place_distr.setDisabled(aaccountant_pass_place_checkbox.isChecked());
		aaccountant_pass_place_distr_text.setDisabled(aaccountant_pass_place_checkbox.isChecked());
		if (aaccountant_pass_place_checkbox.isChecked()) {
			// newcl.getAccountant().setPass_place_region(null);
			// newcl.getAccountant().setPass_place_district(null);
		}
	}

	public void onChange$aaccountant_code_citizenship_text() {
		// newcl.getAccountant().setCode_citizenship(aaccountant_code_citizenship_text.getValue());
		// newcl.getAccountant().setCode_resident(newcl.getAccountant().getCode_citizenship().equals("860")
		// ? "1" : "2");
		// aaccountant_code_citizenship.setSelecteditem(newcl.getAccountant().getCode_citizenship());
	}

	public void onChange$aaccountant_code_citizenship() {
		// newcl.getAccountant().setCode_citizenship(aaccountant_code_citizenship.getValue());
		// newcl.getAccountant().setCode_resident(newcl.getAccountant().getCode_citizenship().equals("860")
		// ? "1" : "2");
		// aaccountant_code_resident.setSelecteditem(newcl.getAccountant().getCode_resident());
		// aaccountant_code_citizenship_text.setValue(newcl.getAccountant().getCode_citizenship());
	}

	public void onChange$aaccountant_pass_place_region() {
		aaccountant_pass_place_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, aaccountant_pass_place_region.getValue(), alias)));
		aaccountant_pass_place_region_text.setValue(aaccountant_pass_place_region.getValue());
		// newcl.getAccountant().setPass_place_region(aaccountant_pass_place_region.getValue());
	}

	public void onChange$aaccountant_pass_place_region_text() {
		aaccountant_pass_place_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, aaccountant_pass_place_region_text.getValue(), alias)));
		aaccountant_pass_place_region.setSelecteditem(aaccountant_pass_place_region_text.getValue());
		// newcl.getAccountant().setPass_place_region(aaccountant_pass_place_region.getValue());
	}

	public void onChange$aaccountant_pass_place_distr() {
		aaccountant_pass_place_distr_text.setValue(aaccountant_pass_place_distr.getValue());
		aaccountant_passport_place_registration.setText(
				aaccountant_pass_place_region.getText() + " " + aaccountant_pass_place_distr.getText() + " " + "���");
		// newcl.getAccountant().setPassport_place_registration(
		// aaccountant_pass_place_region.getText() + " " +
		// aaccountant_pass_place_distr.getText() + " " + "���");
		// newcl.getAccountant().setPass_place_district(aaccountant_pass_place_distr.getValue());
		// newcl.getAccountant()
		// .setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getAccountant().getPass_place_district()));
	}

	public void onChange$aaccountant_pass_place_distr_text() {
		aaccountant_pass_place_distr.setSelecteditem(aaccountant_pass_place_distr_text.getValue());
		aaccountant_passport_place_registration.setText(
				aaccountant_pass_place_region.getText() + " " + aaccountant_pass_place_distr.getText() + " " + "���");
		// newcl.getAccountant().setPassport_place_registration(
		// aaccountant_pass_place_region.getText() + " " +
		// aaccountant_pass_place_distr.getText() + " " + "���");
		// newcl.getAccountant().setPass_place_district(aaccountant_pass_place_distr.getValue());
		// newcl.getAccountant()
		// .setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getAccountant().getPass_place_district()));

	}

	public void onChange$aaccountant_code_adr_region() {
		aaccountant_code_adr_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, aaccountant_code_adr_region.getValue(), alias)));
		// newcl.getAccountant().setCode_adr_region(aaccountant_code_adr_region.getValue());
		aaccountant_code_adr_region_text.setValue(aaccountant_code_adr_region.getValue());
		newcl.setP_code_adr_distr(null);
		binder.loadAll();
	}

	public void onChange$aaccountant_code_adr_region_text() {
		aaccountant_code_adr_distr.setModel(new ListModelList(
				ClientDictionaries.getDistrByRegion(null, aaccountant_code_adr_region_text.getValue(), alias)));
		aaccountant_code_adr_region.setSelecteditem(aaccountant_code_adr_region_text.getValue());
		// newcl.getAccountant().setCode_adr_region(aaccountant_code_adr_region_text.getValue());
	}

	public void onChange$aaccountant_code_adr_distr() {
		String distrId = aaccountant_code_adr_distr.getValue();
		// newcl.getAccountant().setCode_adr_distr(distrId);
		aaccountant_code_adr_distr_text.setValue(distrId);
	}

	public void onChange$aaccountant_code_adr_distr_text() {
		String distrId = aaccountant_code_adr_distr_text.getValue();
		// newcl.getAccountant().setCode_adr_distr(distrId);
		aaccountant_code_adr_distr.setSelecteditem(distrId);

	}
	
	public void onChange$ap_code_resident() {
		newcl.setCode_resident(ap_code_resident.getValue());
	}

	public void onChange$p_code_resident() {
		current.setCode_resident(p_code_resident.getValue());
	}


	public void onFocus$aaccountant_passport_date_expiration() {
		if (aaccountant_type_document.getValue() != null) {
			DateTime dt = null;
			if (aaccountant_type_document.getValue().equals("1")) {
				if (aaccountant_birthday.getValue() != null) {
					dt = new DateTime(aaccountant_birthday.getValue());
					dt = dt.plusYears(25);
					dt = dt.minusDays(1);
					aaccountant_passport_date_expiration.setValue(dt.toDate());
					// newcl.getAccountant().setPassport_date_expiration(dt.toDate());
				}
			}
			if (aaccountant_type_document.getValue().equals("6")) {
				if (aaccountant_passport_date_registration.getValue() != null) {
					dt = new DateTime(aaccountant_passport_date_registration.getValue());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					aaccountant_passport_date_expiration.setValue(dt.toDate());
					// newcl.getAccountant().setPassport_date_expiration(dt.toDate());
				}
			}
		}
	}

	public void onCheck$check_all_sap$fields_diff_wnd() {
		ZkUtils.checkAllRowsUnderSpecificColumn(ClientUtil.COLUMN_ATTR, ClientUtil.SAP_COLUMN,
				fields_diff_wnd$diff_rows, fields_diff_wnd$check_all_sap.isChecked());
	}

	public void onCheck$check_all_ebp$fields_diff_wnd() {
		ZkUtils.checkAllRowsUnderSpecificColumn(ClientUtil.COLUMN_ATTR, ClientUtil.EBP_COLUMN,
				fields_diff_wnd$diff_rows, fields_diff_wnd$check_all_ebp.isChecked());
	}

	public void onClick$btn_apply$fields_diff_wnd() {
		List<String> sapCheckedFields = ZkUtils.getCheckedRowsByAttribute(fields_diff_wnd$diff_rows,
				ClientUtil.SAP_COLUMN, ClientUtil.COLUMN_ATTR, ClientUtil.FIELD_ATTR);
		List<String> ebpCheckedFields = ZkUtils.getCheckedRowsByAttribute(fields_diff_wnd$diff_rows,
				ClientUtil.EBP_COLUMN, ClientUtil.COLUMN_ATTR, ClientUtil.FIELD_ATTR);

		ClientA fetchedClientFromSap = (ClientA) fields_diff_wnd$diff_grid.getAttribute(ClientUtil.SAP_CLIENT_ATTR);
		ClientA fetchedClientFromEbp = (ClientA) fields_diff_wnd$diff_grid.getAttribute(ClientUtil.EBP_CLIENT_ATTR);
		if (!addgrd.isVisible()) {
			// ClientUtil.setSapOrEbpData(current, fetchedClientFromSap,
			// fetchedClientFromEbp, sapCheckedFields,
			// ebpCheckedFields);
		} else {
			// ClientUtil.setSapOrEbpData(newcl, fetchedClientFromSap,
			// fetchedClientFromEbp, sapCheckedFields,
			// ebpCheckedFields);
		}
		binder.loadAll();
		fields_diff_wnd.setVisible(false);
		// sendEventToChildren(addgrd,Events.ON_CHANGE);
	}

	private void hideTabs(boolean bol) {
		// acc_tab.setVisible(bol);
		// attach_tab.setVisible(bol);
		// specclt_tab.setVisible(bol);
		// history_tab.setVisible(bol);
		// cards_tab.setVisible(bol);
	}

	protected void sendEventToChildren(Component comp, String event) {
		if (comp.getChildren().size() == 0) {
			if (comp instanceof RefCBox)
				Events.sendEvent(comp, new Event(event));
			return;
		}

		List<Component> list = comp.getChildren();
		for (Component component : list) {
			sendEventToChildren(component, event);
		}
	}

	public void onChange$code_resident() throws InterruptedException {
		Messagebox.show("");
	}

	public void onCtrlKey$aj_soato() {
		Window soatoWnd = (Window) Executions.createComponents("soato.zul", null, null);
		soatoWnd.addEventListener("onNotifySoato", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Soato soato = (Soato) event.getData();
				newcl.setJ_region(soato.getRegion());
				newcl.setJ_distr(soato.getDistr());
				newcl.setJ_soato(soato.getKod_soat());
				newcl.setJ_code_tax_org(soato.getKod_gni());
				binder.loadAll();
			}
		});
		soatoWnd.setVisible(true);
	}

	public void onCtrlKey$j_soato() {
		Window soatoWnd = (Window) Executions.createComponents("soato.zul", null, null);
		soatoWnd.addEventListener("onNotifySoato", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Soato soato = (Soato) event.getData();
				current.setJ_region(soato.getRegion());
				current.setJ_distr(soato.getDistr());
				current.setJ_soato(soato.getKod_soat());
				current.setJ_code_tax_org(soato.getKod_gni());
				current.setAddressCountry("860");
				binder.loadAll();
			}
		});
		soatoWnd.setVisible(true);
	}

	public void onChange$code_country_value() {
		code_country.setSelecteditem(code_country_value.getValue());
		current.setCode_country(code_country_value.getValue());
	}

	public void onChange$code_country() {
		code_country_value.setValue(code_country.getValue());
		current.setCode_country(code_country.getValue());
	}

	public void onChange$atax_orgValue() {
		aj_code_tax_org.setSelecteditem(atax_orgValue.getValue());
		newcl.setJ_code_tax_org(atax_orgValue.getValue());
	}

	public void onChange$aj_code_tax_org() {
		atax_orgValue.setValue(aj_code_tax_org.getValue());
		newcl.setJ_code_tax_org(aj_code_tax_org.getValue());
	}

	// public void onChange$aopfValue() {
	// aj_opf.setSelecteditem(aopfValue.getValue());
	// }
	public void onChange$aj_opf() {
		aopfValue.setValue(aj_opf.getValue());
		newcl.setJ_opf(aj_opf.getValue());
	}

	public void onChange$aformValue() {
		acode_form.setSelecteditem(aformValue.getValue());
		newcl.setCode_form(aformValue.getValue());
	}

	public void onChange$acode_form() {
		aformValue.setValue(acode_form.getValue());
		newcl.setCode_form(acode_form.getValue());
	}

	public void onChange$asectorValue() {
		aj_code_sector.setSelecteditem(asectorValue.getValue());
		newcl.setJ_code_sector(asectorValue.getValue());
	}

	public void onChange$aj_code_sector() {
		asectorValue.setValue(aj_code_sector.getValue());
		newcl.setJ_code_sector(aj_code_sector.getValue());
	}

	public void onChange$acl_activity_type_idValue() {
		acl_activity_type_id.setSelecteditem(acl_activity_type_idValue.getValue());
		newcl.setJ_type_activity(acl_activity_type_idValue.getValue());
	}

	public void onChange$acl_activity_type_id() {
		acl_activity_type_idValue.setValue(acl_activity_type_id.getValue());
		newcl.setJ_type_activity(acl_activity_type_id.getValue());
	}

	public void onCheck$type_radio_close1() {
		filter.setSign_date_close(type_radio_close1.getSelectedIndex());
		fdate_close1.setDisabled(type_radio_close1.getSelectedIndex() != 2);
		fdate_close.setDisabled(type_radio_close1.getSelectedIndex() != 1 && type_radio_close1.getSelectedIndex() != 2);
	}

	public void onCheck$type_radio_open1() {
		filter.setSign_date_open(type_radio_open1.getSelectedIndex());
		fdate_open1.setDisabled(type_radio_open1.getSelectedIndex() != 2);
		fdate_open.setDisabled(type_radio_open1.getSelectedIndex() != 1 && type_radio_open1.getSelectedIndex() != 2);
	}

	public void onChange$cl_activity_type_idValue() {
		cl_activity_type_id.setSelecteditem(cl_activity_type_idValue.getValue());
		current.setJ_type_activity(cl_activity_type_idValue.getValue());
	}

	public void onChange$cl_activity_type_id() {
		cl_activity_type_idValue.setValue(cl_activity_type_id.getValue());
		current.setJ_type_activity(cl_activity_type_id.getValue());
	}

	public void onChange$j_code_organ_direct() {
		organ_directValue.setValue(j_code_organ_direct.getValue());
		current.setJ_code_organ_direct(j_code_organ_direct.getValue());
	}

	public void onChange$type_non_resident() {
		type_non_residentValue.setValue(type_non_resident.getValue());
		current.setType_non_resident(type_non_resident.getValue());
	}

	public void onChange$p_code_nation() {
		current.setP_code_nation(p_code_nation.getValue());
	}

	public void onChange$p_code_citizenship() {
		p_code_citizenship_text.setValue(p_code_citizenship.getValue());
		current.setP_code_citizenship(p_code_citizenship.getValue());
	}

	public void onChange$code_resident1() {
		current.setCode_resident(code_resident1.getValue());
	}

	public void onChange$fcode_resident() {
		filter.setCode_resident(fcode_resident.getValue());
	}

	public void onChange$fp_code_citizenship() {
		fp_code_citizenship_text.setValue(fp_code_citizenship.getValue());
		filter.setP_code_citizenship(fp_code_citizenship.getValue());
	}

	public void onChange$fp_type_document() {
		fp_type_document_text.setValue(fp_type_document.getValue());
		filter.setP_type_document(fp_type_document.getValue());
	}

	public void onChange$fp_code_nation() {
		filter.setP_code_nation(fp_code_nation.getValue());
	}

	public void onChange$fp_code_gender() {
		filter.setP_code_gender(fp_code_gender.getValue());
	}

	public void onChange$fp_code_adr_region_text() {
		filter.setP_code_adr_distr(null);
		fp_code_adr_distr.setSelecteditem(null);
		fp_code_adr_distr_text.setValue(null);
		filter.setP_code_adr_region(fp_code_adr_region_text.getValue());
		fp_code_adr_region.setSelecteditem(fp_code_adr_region_text.getValue());
		fp_code_adr_distr.setModel(
				new ListModelList(ClientDictionaries.getDistrByRegion(null, fp_code_adr_region.getValue(), alias)));
	}

	public void onChange$fp_code_adr_region() {
		filter.setP_code_adr_distr(null);
		fp_code_adr_distr.setSelecteditem(null);
		fp_code_adr_distr_text.setValue(null);

		filter.setP_code_adr_region(fp_code_adr_region.getValue());
		fp_code_adr_region_text.setValue(fp_code_adr_region.getValue());
		fp_code_adr_distr.setModel(
				new ListModelList(ClientDictionaries.getDistrByRegion(null, fp_code_adr_region.getValue(), alias)));
	}

	public void onChange$fp_code_capacity() {
		fp_code_capacity_text.setValue(fp_code_capacity.getValue());
		filter.setP_code_capacity(fp_code_capacity.getValue());
	}

	public void onChange$fp_code_adr_distr_text() {
		fp_code_adr_distr.setSelecteditem(fp_code_adr_distr_text.getValue());
		current.setP_code_adr_distr(fp_code_adr_distr_text.getValue());
	}

	public void onChange$p_code_gender() {
		current.setP_code_gender(p_code_gender.getValue());
	}

	public void onChange$p_code_tax_org() {
		current.setP_code_tax_org(p_code_tax_org.getValue());
		p_code_tax_org_text.setValue(p_code_tax_org.getValue());
	}

	public void onChange$swift_id() {
		swift_idValue.setValue(swift_id.getValue());
		current.setSwift_id(swift_id.getValue());
	}

	/*
	 * public void onChange$director_code_nation() {
	 * current.getDirector().setCode_nation(director_code_nation.getValue()); }
	 * public void onChange$director_code_resident() {
	 * current.getDirector().setCode_resident(director_code_resident.getValue())
	 * ; } public void onChange$director_code_gender() {
	 * current.getDirector().setCode_gender(director_code_gender.getValue()); }
	 */
	/*
	 * public void onChange$director_code_country() {
	 * current.getDirector().setCode_country(director_code_country.getValue());
	 * }
	 *//*
		 * public void onChange$director_code_tax_org() {
		 * current.getDirector().setCode_tax_org(director_code_tax_org.getValue(
		 * )); }
		 */
	public void onChange$fstate() {
		filter.setState(fstate.getValue());
	}

	public void onChange$fcode_type() {
		ftypeValue.setValue(fcode_type.getValue());
		filter.setCode_type(fcode_type.getValue());
	}

	public void onChange$fcode_country() {
		fcode_country_value.setValue(fcode_country.getValue());
		filter.setCode_country(fcode_country.getValue());
	}

	public void onChange$fj_code_tax_org() {
		ftax_orgValue.setValue(fj_code_tax_org.getValue());
		filter.setJ_code_tax_org(fj_code_tax_org.getValue());
	}

	public void onChange$fj_code_sector() {
		fsectorValue.setValue(fj_code_sector.getValue());
		filter.setJ_code_sector(fj_code_sector.getValue());
	}

	public void onChange$fcl_activity_type_id() {
		fcl_activity_type_idValue.setValue(fcl_activity_type_id.getValue());
		filter.setJ_type_activity(fcl_activity_type_id.getValue());
	}

	public void onChange$fcode_form() {
		fformValue.setValue(fcode_form.getValue());
		filter.setCode_form(fcode_form.getValue());
	}

	public void onChange$fj_opf() {
		fopfValue.setValue(fj_opf.getValue());
		filter.setJ_opf(fj_opf.getValue());
	}

	public void onChange$acode_country() {
		acode_country_value.setValue(acode_country.getValue());
		newcl.setCode_country(acode_country.getValue());
	}

	public void onChange$acode_resident() {
		newcl.setCode_resident(acode_resident.getValue());
	}

	public void onChange$atype_non_resident() {
		atype_non_residentValue.setValue(atype_non_resident.getValue());
		newcl.setType_non_resident(atype_non_resident.getValue());
	}

	public void onChange$aj_code_organ_direct() {
		aorgan_directValue.setValue(aj_code_organ_direct.getValue());
		newcl.setJ_code_organ_direct(aj_code_organ_direct.getValue());
	}

	public void onChange$ap_code_nation() {
		newcl.setP_code_nation(ap_code_nation.getValue());
	}

	public void onChange$ap_code_citizenship() {
		ap_code_citizenship_text.setValue(ap_code_citizenship.getValue());
		newcl.setP_code_citizenship(ap_code_citizenship.getValue());
	}

	public void onChange$acode_resident1() {
		newcl.setCode_resident(acode_resident1.getValue());
	}

	public void onChange$ap_code_gender() {
		newcl.setP_code_gender(ap_code_gender.getValue());
	}

	public void onChange$acode_country1() {
		newcl.setPost_address_country(acode_country1.getValue());
	}

	public void onChange$j_opf() {
		opfValue.setValue(j_opf.getValue());
		current.setJ_opf(j_opf.getValue());
	}

	public void onChange$j_code_tax_org() {
		tax_orgValue.setValue(j_code_tax_org.getValue());
		current.setJ_code_tax_org(j_code_tax_org.getValue());
	}

	public void onChange$code_form() {
		formValue.setValue(code_form.getValue());
		current.setCode_form(code_form.getValue());
	}

	public void onChange$j_code_sector() {
		sectorValue.setValue(j_code_sector.getValue());
		current.setJ_code_sector(j_code_sector.getValue());
	}

	public void onChange$a_code_tax_org() {
		a_tax_orgValue.setValue(a_code_tax_org.getValue());
		current.setJ_code_tax_org(a_code_tax_org.getValue());
	}

	public void onChange$a_type_document() {
		a_type_document_text.setValue(a_type_document.getValue());
		current.setP_type_document(a_type_document.getValue());
	}

	public void onChange$a_opf() {
		a_opfValue.setValue(a_opf.getValue());
		current.setJ_opf(a_opf.getValue());
	}

	public void onChange$a_code_form() {
		a_formValue.setValue(a_code_form.getValue());
		current.setCode_form(a_code_form.getValue());
	}

	public void onChange$a_code_sector() {
		a_sectorValue.setValue(a_code_sector.getValue());
		current.setJ_code_sector(a_code_sector.getValue());
	}

	public void onChange$director_type_document() {
		director_type_document_text.setValue(director_type_document.getValue());
		// current.getDirector().setType_document(director_type_document.getValue());
	}

	public void onChange$director_code_citizenship() {
		director_code_citizenship_text.setValue(director_code_citizenship.getValue());
	}

	public void onChange$accountant_type_document() {
		accountant_type_document_text.setValue(accountant_type_document.getValue());
		// current.getAccountant().setType_document(accountant_type_document.getValue());
	}

	public void onChange$accountant_code_citizenship() {
		accountant_code_citizenship_text.setValue(accountant_code_citizenship.getValue());
	}

	public void onChange$accountant_code_nation() {
		// current.getAccountant().setCode_nation(accountant_code_nation.getValue());
	}

	public void onChange$accountant_code_resident() {
		// current.getAccountant().setCode_resident(accountant_code_resident.getValue());
	}

	public void onChange$accountant_code_gender() {
		// current.getAccountant().setCode_gender(accountant_code_gender.getValue());
	}

	public void onChange$accountant_code_country() {
		// current.getAccountant().setCode_country(accountant_code_country.getValue());
	}

	public void onChange$accountant_code_tax_org() {
		accountant_code_tax_org_text.setValue(accountant_code_tax_org.getValue());
		// current.getAccountant().setCode_tax_org(accountant_code_tax_org.getValue());
	}

	public void onChange$ap_code_tax_org() {
		newcl.setP_code_tax_org(ap_code_tax_org.getValue());
		ap_code_tax_org_text.setValue(ap_code_tax_org.getValue());
	}

	public void onChange$aj_code_bank() {
		abankValue.setValue(aj_code_bank.getValue());
		newcl.setJ_code_bank(aj_code_bank.getValue());
	}

	public void onChange$ai_form() {
		aiformValue.setValue(ai_form.getValue());
		newcl.setI_form(ai_form.getValue());
	}

	public void onChange$ai_code_sector() {
		aisectorValue.setValue(ai_code_sector.getValue());
		newcl.setI_sector(ai_code_sector.getValue());
	}

	public void onChange$ai_code_organ_direct() {
		aiorgan_directValue.setValue(ai_code_organ_direct.getValue());
		newcl.setI_organ_direct(ai_code_organ_direct.getValue());
	}

	public void onChange$aswift_id() {
		aswift_idValue.setValue(aswift_id.getValue());
		newcl.setSwift_id(aswift_id.getValue());
	}

	public void onChange$adirector_code_nation() {
		// newcl.getDirector().setCode_nation(adirector_code_nation.getValue());
	}

	public void onChange$adirector_code_resident() {
		// newcl.getDirector().setCode_resident(adirector_code_resident.getValue());
	}

	public void onChange$adirector_code_gender() {
		// newcl.getDirector().setCode_gender(adirector_code_gender.getValue());
	}

	public void onChange$adirector_code_country() {
		adirector_code_country_text.setValue(adirector_code_country.getValue());
		// newcl.getDirector().setCode_country(adirector_code_country.getValue());
	}

	public void onChange$adirector_code_tax_org() {
		adirector_code_tax_org_text.setValue(adirector_code_tax_org.getValue());
		// newcl.getDirector().setCode_tax_org(adirector_code_tax_org.getValue());
	}

	public void onChange$aaccountant_code_nation() {
		// newcl.getAccountant().setCode_nation(aaccountant_code_nation.getValue());
	}

	public void onChange$aaccountant_code_resident() {
		// newcl.getAccountant().setCode_resident(aaccountant_code_resident.getValue());
	}

	public void onChange$aaccountant_code_gender() {
		// newcl.getAccountant().setCode_gender(aaccountant_code_gender.getValue());
	}

	public void onChange$aaccountant_code_country() {
		aaccountant_code_country_text.setValue(aaccountant_code_country.getValue());
		// newcl.getAccountant().setCode_country(aaccountant_code_country.getValue());
	}

	public void onChange$aaccountant_code_tax_org() {
		aaccountant_code_tax_org_text.setValue(aaccountant_code_tax_org.getValue());
		// newcl.getAccountant().setCode_tax_org(aaccountant_code_tax_org.getValue());
	}

	public void onChange$j_code_class_credit() {
		class_creditValue.setValue(j_code_class_credit.getValue());
	}

	public void onChange$p_code_capacity() {
		p_code_capacity_text.setValue(p_code_capacity.getValue());
		current.setP_code_capacity(p_code_capacity.getValue());
	}

	public void onChange$ap_code_capacity() {
		ap_code_capacity_text.setValue(ap_code_capacity.getValue());
		newcl.setP_code_capacity(ap_code_capacity.getValue());
	}


	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("newcl", this.newcl);
		binder.bindBean("filter", filter);
		binder.bindBean("currentListItem", currentListItem);
		binder.loadAll();

		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}
		parameter = (String[]) param.get("mode");
		if (parameter != null) {
			mode = parameter[0];
		}
		parameter = (String[]) param.get("client_id");
		if (parameter != null) {
			filter.setId_client(parameter[0]);
		}
		ses_branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");
		int userId = (Integer) session.getAttribute("uid");

		// serviceFactory = ServiceFactory.getInstance(alias, un, pw);
		// eq = EventQueues.lookup("IndividualEnterpreneurEQ",
		// EventQueues.DESKTOP, true);
		initServices();

		if (mode == null) {
			initRenderers();
			// refreshModel(_startPageNumber, true); //�����, ������� �
			// 24,11,2017.
			// ������ ��� ���������� ��� ������� ��� ������� ��������, ���� ����
			// ����� ����� ����� �������� �� ������������� ������
			hideAll();
			// --hamza
			// acc_tab.setVisible(false);
			// attach_tab.setVisible(false);
			// specclt_tab.setVisible(false);
			// history_tab.setVisible(false);
			// acc_tab_panel.setVisible(false);
			// specclt_tab_panel.setVisible(false);
			// attach_tab_panel.setVisible(false);
			// history_tab_panel.setVisible(false);
			// ------

			grd.setVisible(true);
			// setAvailableTabs(userId);
		} else if (mode.equals(ClientUtil.MODE_DELTA)) {
			initDeltaMode();
		} else if (mode.equals(ClientUtil.MODE_NIBBD)) {
			parameter = (String[]) param.get("queryOut");
			if (parameter != null) {
				initForNibbd(parameter[0]);
			}
		}

		/*
		 * filterradiogroup.addEventListener(Events.ON_CHECK, new
		 * EventListener() {
		 * 
		 * @Override public void onEvent(Event event) {
		 * fp_view.setVisible(false); Radio selectedItem = filterradiogroup
		 * .getSelectedItem(); if (selectedItem!=null)
		 * onCheck_filterradiogroup(selectedItem); } });
		 */

		onClick$btn_show_search_sap();
	}

	private void setAvailableTabs(int userId) {
		// int[] moduleIds = clientService.getAvailableModules(userId);
		// if (moduleIds == null || moduleIds.length == 0) {
		// return;
		// }
		// acc_tab.setVisible(Util.inInts(BfModules.ACCOUNT.getModuleId(),
		// moduleIds));
		// specclt_tab.setVisible(Util.inInts(BfModules.SPEC_CLT.getModuleId(),
		// moduleIds));
	}

	private void initServices() {
		// dictionaryKeeper = serviceFactory.getDictionaryKeeper();
		dictionaryKeeper = DictionaryKeeper.getInstance(alias);
		dictionaryKeeper.initLists();
		// clientService = serviceFactory.getClientJService();
		// clientDao = serviceFactory.getDaoFactory().getClientDao();
		clientService = ClientAService.getInstance(un, pw, alias);
		initSearchComposer();
		drawRadioButtons();
		setModels();
	}

	private void drawRadioButtons() {
		Radio radio = null;
		for (CltPaths path : CltPaths.values()) {
			radio = new Radio(path.getDesc());
			radio.setValue(path.toString());
			clt_dlg_wnd$paths.appendChild(radio);
		}
	}

	private void initDeltaMode() {
		hideAll();
		top_tb.setVisible(false);
		String[] id_client = (String[]) param.get("client_id");

		filter.setId_client(id_client[0]);
		refreshModel(0, false);
		if (dataGrid.getItems().size() == 0) {
			alert("������ �� ������!");
			return;
		}
		onDoubleClick$dataGrid$grd();
		for (Object object : cl_tabs.getTabs().getChildren()) {
			if (((Tab) object).getIndex() == 0) {
				continue;
			}
			((Tab) object).setVisible(false);
		}
	}

	private void initForNibbd(String nibbdQueryOut) {
		initServices();
		showFormForNewClient();
		initCLientFromNibbd(nibbdQueryOut);
		Events.sendEvent(new Event(Events.ON_SELECT, acode_type));

		aj_code_bank.setDisabled(false);
		top_tb.setVisible(false);
		addgrd.setAttribute("mode", "new");
		btn_save.setAttribute("action", "open");
		tb.setVisible(true);
		btn_clear.setVisible(false);
	}

	private void initRenderers() {
		dataGrid.setItemRenderer(new ClientRenderer(clientStates, clientTypes));
		// history.setItemRenderer(new HistoryRenderer());

		// atachments.setItemRenderer(new AttachmentRenderer(attachMap));
		String schema = (String) session.getAttribute("alias");
		// final AttachmentRenderer attachmentRenderer = new
		// AttachmentRenderer(schema);
		// atachments.setItemRenderer(attachmentRenderer);
		// if (current != null)
		// attachmentRenderer.setClientJ(current);
	}

	private void initCLientFromNibbd(String nibbdQueryOut) {
		String[] tilda = nibbdQueryOut.split("~");
		String[] fields = tilda[2].split(";");

		newcl = new ClientA();
		/*
		 * if (!EmergencyMode.isTrue) { List<BPSearchResponceOrganization>
		 * searchResponse = organizationService
		 * .searchOrganizationByDocId(fields[2],
		 * SapEnum.DOC_TYPE_INN.getSapValue()); if (searchResponse != null &&
		 * !searchResponse.isEmpty()) { BusinessOrganizationComplex boc =
		 * organizationService.getDetailsBySapId(searchResponse.get(0).
		 * getId_client_sap()); setNewcl(Mappers.mapToClientJ(boc)); try { if
		 * (newcl.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
		 * SapHandler.checkIP(newcl); } else {
		 * RelationHandler.instance(ses_branch).fetchDirectors(boc.getBp_list(),
		 * newcl); } } catch (Exception e) { logger.error(CheckNull.getPstr(e));
		 * } } else { newcl = new ClientJ(); } } else{
		 * 
		 * }
		 */
		newcl.setBranch(ses_branch);
		newcl.setSign_registr("" + ClientUtil.SIGN_REGISTR_PRIMARY_NOT);
		logger.error("initCLientFromNibbd   newcl.getSign_registr = " + newcl.getSign_registr());
		newcl.setCode_type(clientLettersMap.get(fields[10]));
		newcl.setId_client(fields[3]);
		newcl.setName(fields[4]);
		newcl.setJ_number_registration_doc(fields[7]);
		newcl.setJ_place_regist_name(fields[8]);
		Events.sendEvent(new Event("onInitRender", acode_country));
		Events.sendEvent(new Event("onInitRender", acode_type));
		if (newcl.getCode_type().equals("11")) {
			newcl.setP_number_tax_registration(fields[2]);
			newcl.setP_code_citizenship(fields[6]);
			newcl.setP_code_adr_region(fields[22]);
			newcl.setP_code_adr_distr(fields[23]);
			// newcl.setCode_resident(NibbdUtils.convertResident(fields[5],
			// false));
			Events.sendEvent(new Event("onInitRender", ap_code_citizenship));
			Events.sendEvent(new Event("onInitRender", acode_resident1));
			Events.sendEvent(new Event("onInitRender", ap_code_adr_region));
			Events.sendEvent(new Event("onInitRender", ap_code_adr_distr));
		}
		newcl.setJ_number_tax_registration(fields[2]);
		newcl.setCode_country(fields[6]);
		// newcl.setCode_resident(NibbdUtils.convertResident(fields[5], false));
		newcl.setJ_region(fields[22]);
		newcl.setJ_distr(fields[23]);
		Events.sendEvent(new Event("onInitRender", acode_country));
		Events.sendEvent(new Event("onInitRender", aj_region));
		Events.sendEvent(new Event("onInitRender", acode_resident));
		// Events.sendEvent(new Event(Events.ON_CHANGE, aj_region));
		Events.sendEvent(new Event("onInitRender", aj_distr));
		try {
			newcl.setJ_date_registration(ClientUtil.parseDate(fields[9]));
		} catch (ParseException e) {
			logger.error(e.getStackTrace());
		}
		newcl.setJ_code_bank(fields[11]);
		newcl.setJ_opf(fields[13]);
		newcl.setCode_form(fields[14]);
		newcl.setJ_soato(fields[15]);
		newcl.setJ_okpo(fields[16]);
		newcl.setJ_code_sector(fields[17]);
		// newcl.setJ_code_sector_old(fields[17]);
		Events.sendEvent(new Event("onInitRender", aj_code_bank));
		Events.sendEvent(new Event("onInitRender", aj_opf));
		Events.sendEvent(new Event("onInitRender", acode_form));
		// Events.sendEvent(new Event("onInitRender", aj_code_sector_old));
		Events.sendEvent(new Event("onInitRender", aj_code_sector));
		Events.sendEvent(new Event("onInitRender", aj_code_organ_direct));
		Events.sendEvent(new Event("onInitRender", aj_code_bank));
		newcl.setJ_code_head_organization(fields[18]);
		newcl.setJ_inn_head_organization(fields[19]);

		int len = newcl.getName().length();
		newcl.setJ_short_name(newcl.getName().substring(0, len < 25 ? len : 25));

		showIpView_add(newcl.getCode_type().equals(ClientUtil.CODE_TYPE_IP));
		aj_small_business.setChecked(
				newcl.getJ_small_business() != null && newcl.getJ_small_business().equals(ClientUtil.CHECKBOX_Y));
		aj_sign_trade
				.setChecked(newcl.getJ_sign_trade() != null && newcl.getJ_sign_trade().equals(ClientUtil.CHECKBOX_Y));

		binder.loadAll();
		showCodesForNewcl();
	}

	public void onSelect$cl_tabs() {
		if (current == null) {
			return;
		}
		switch (cl_tabs.getSelectedIndex()) {
		case 0:
			frm.setVisible(true);
			break;
		case 1:
			//initAccounts();
			includeDeposits.setSrc("sd_books.zul?search_clients=" +current.getId_client() );
			break;
		case 2:
			//initSpecclt();
			includeCards.setSrc("uz_card.zul?search_clients=" + current.getId_client());
			break;
		case 3:
			//refreshAtachmentList();
			includeTransfers.setSrc("dper_info.zul?search_clients=" + current.getId_client());
			/*
			 * { //onClick$btn_getFile(); appsDiv.getChildren().clear(); appsWnd
			 * = (Window) Executions.createComponents("/customer/apps.zul",
			 * appsDiv, null); appsWnd.setClosable(true);
			 * appsWnd.setVisible(true); Events.sendEvent("onUploadApps",
			 * appsWnd, current); //hamza 22.10.2017 }
			 */
			break;
		case 4:
			includeAccounts.setSrc("account.zul?search_clients=" + current.getId_client());
			//history.setModel(new ListModelList(ClientDictionaries.getClientHistory(current.getId_client(), current.getBranch(), alias)));
			break;
		default:
			break;
		}
	}

	public void onClick$btn_new() {
		newcl = new ClientA();
		// clt_dlg_wnd.setVisible(true);
		initNewClientP();
		// aj_small_business.setChecked(false);
		// aj_sign_trade.setChecked(false);
		btn_new.setDisabled(true);
	}

	public void onClick$btn_choose$clt_dlg_wnd() {
		if (clt_dlg_wnd$paths.getSelectedItem() == null)
			return;
		// boolean foundInSAP = isFoundInSAP();
		String selectedPath = clt_dlg_wnd$paths.getSelectedItem().getValue();
		switch (CltPaths.valueOf(selectedPath)) {
		case LEGAL_ENTITY:
			// if (foundInSAP)
			// openClient(1);
			// else
			initNewClientJ();
			break;
		case LEGAL_ENTITY_NIBBD:
			// if (foundInSAP)
			// openClient(50);
			// else
			// initNibbd(NibbdQueries.IDENTIFICATION);
			break;
		case PHYSICAL:
			initNewClientP();
			break;
		default:
			break;
		}
		clt_dlg_wnd.setVisible(false);
	}

	public boolean isFoundInSAP() {
		String action = (String) btn_save.getAttribute("action");
		return action != null && action.equalsIgnoreCase("open_sap");
	}

	public void onClick$btn_save() throws Exception {
		String action = (String) btn_save.getAttribute("action");
		if (action.equals("open")) {
			// onClick$btn_open();
		} else if (action.equals("change")) {
			// applyChanges();
		} else if (action.equals("edit")) {
			// applyEditing();
		} else if (action.equals("filter")) {
			filterList();
		} else if (action.equals("open_sap")) {
			// onClick$btn_open();
			// createSAPOrg();
		}
	}

	public void onCheck_filterradiogroup(Radio myRadio) {
		if (myRadio.getValue() != null && myRadio.getValue().equals("1")) {
			fp_view.setVisible(true);
		}
		// binder.loadAll();
	}

	public void onCheck$filterradiogrp() {
		// filter.setSign_date_open(type_radio_open1.getSelectedIndex());
		// fdate_open1.setDisabled(type_radio_open1.getSelectedIndex() != 2);
		// fdate_open.setDisabled(type_radio_open1.getSelectedIndex() != 1 &&
		// type_radio_open1.getSelectedIndex() != 2);
		fp_view.setVisible(filterradiogrp.getSelectedIndex() == 0);
	}

	public void onClick$btn_get_ip() {
		eq.subscribe(new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				if (!self.getPage().isAlive()) {
					eq.unsubscribe(this);
					return;
				}
				if (arg0.getName().equals(Events.ON_NOTIFY)) {
					// newcl.setIndividualEnterpreneurClean((IndividualEnterpreneur)
					// arg0.getData());
					binder.loadAll();
					dp_wnd.setVisible(false);
					eq.unsubscribe(this);
				}
			}
		});
		dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_IP_SRC + "?action=createIndividualEnterpreneur");
		dp_wnd.setVisible(true);
	}

	/*******************************************************************************************************************************
	 * 8
	 *
	 * SAP handling
	 *
	 *********************************************************************************************************************************/

	public void onClick$btn_find_ip() {
		// if (SapHandler.findBpForIP(newcl)) {
		// alert("found bp!");
		// binder.loadAll();
		// }
	}

	public void onClick$btn_getFile() {
		refreshAtachmentList();
	}

	/*
	 * private List<Attachment> convertToLocalAttachments(
	 * client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment[] attachments) {
	 * List<Attachment> attachmentList = new ArrayList<Attachment>(); if
	 * (attachments != null) for (int i = 0; i < attachments.length; i++) {
	 * BPAttachmentsAttachment bpAttachment = attachments[i]; Attachment
	 * attachment = new Attachment();
	 * attachment.setDoc_type(bpAttachment.getType());
	 * attachment.setDoc_date(bpAttachment.getDoc_date());
	 * attachment.setDoc_number(bpAttachment.getDoc_number());
	 * attachment.setFileName( bpAttachment.getFilename() == null ?
	 * bpAttachment.getName() : bpAttachment.getFilename());
	 * attachment.setDescription(bpAttachment.getDescription());
	 * attachment.setUrl(bpAttachment.getURL());
	 * attachment.setCreatedAt(CustomerUtils.toDate(bpAttachment.getCreated_at()
	 * )); attachmentList.add(attachment); } return attachmentList; }
	 */

	/*
	 * public void onUpload$btn_sendFile(UploadEvent event) { try { Media media
	 * = event.getMedia(); if (CheckNull.isEmpty(attch_types.getValue())) {
	 * Messagebox.show("�������� ��� ���������"); return; } if (media.getName()
	 * == null || media.getName().length() >= 18) { Messagebox.show(
	 * "����� ����� ����� �� ������ ���� ������ ���� ����� 18 ������: " +
	 * media.getName()); return; } if (!FileUtil.isValidName(media.getName())) {
	 * Messagebox.show("�������� �������� �����"); return; } if (media != null)
	 * { byte[] data =
	 * org.apache.commons.io.IOUtils.toByteArray(media.getStreamData());
	 * 
	 * BPAttachmentsAttachment[] attachments = {new BPAttachmentsAttachment(
	 * data, null, attch_types.getValue(), media.getName(), media.getName(),
	 * null, null, null, null)};
	 * 
	 * organizationService.sendAttacments(current, attachments); } } catch
	 * (Exception e) { e.printStackTrace(); alert(e.getMessage()); return; }
	 * refreshAtachmentList(); alert("���� ��������"); }
	 */

	public void onChange$attch_types(InputEvent event) {
		RefCBox rcombobox = (RefCBox) event.getTarget();
		if (event.getValue() == null || event.getValue().isEmpty())
			btn_sendFile.setDisabled(true);
		else
			btn_sendFile.setDisabled(false);
	}

	public void onSelect$dataGrid$grd() {
		if (currentListItem != null) {
			if (current == null || current.getBranch() == null
					|| current.getId_client() != currentListItem.getId_client()) {
				current = ClientAService.getItemByStringId(currentListItem.getBranch(), currentListItem.getId_client(),
						un, pw, alias);
			}
		}
		drawActions(current);
	}

	public void onUpload$btn_sendFile(UploadEvent event) throws Exception {
	}

	public void onClick$btn_nibbd() {
		// initNibbd(null);
	}

	public void onClick$btn_fetch_sap() {

	}

	public void onOkToFilter(Event event) {
		filterList();
	}

	private void filterList() {
		try {
			refreshModel(_startPageNumber, true);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
			onClick$btn_back();
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		}
	}

	public void onClick$act001_open() {
		// Customer customer = actionService.doAction(dealId,
		// composer.getCustomer(), action);
		// composer.refreshCustomer(customer);
		// Messagebox.show("�������");
		// drawActions(current);
		try {
			Messagebox.show("�� ������������� ������ ��������� ��������: �������?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							//if (event.getName().equals( Messagebox.on_OK))
							if (event.getName().equals("onOK"))
								openClient(1);
						}
					});
		} catch (InterruptedException e) {
			logger.error(CheckNull.getPstr(e));
		}
	}

	protected void openClient(int action) {

		if (newcl.getCode_type() == null)
			throw new RuntimeException("�������� ��� ������� ");
		if (!newcl.getCode_type().equals("08")) {
			if (!check_some_fields_to_open()) {
				alert(err_msg);
				return;
			}
		} else {
			if (!check_some_fields_to_open_p()) {
				alert(err_msg);
				return;
			}

		}
		if (!newcl.getCode_type().equals("08")) {
			newcl.setJ_sign_trade(aj_sign_trade.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
			newcl.setJ_small_business(aj_small_business.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
			newcl.setCode_subject(
					newcl.getCode_type().equals("07") ? ClientUtil.CODE_SUBJECT_I : ClientUtil.CODE_SUBJECT_J);
			newcl.setJ_sign_dep_acc(aj_sign_dep_acc.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
			boolean isNibbd = false;
			if ((mode != null && mode.equals(ClientUtil.MODE_NIBBD)) || action == 50) {
				isNibbd = true;
			}
			newcl.resolveSignRegistry(isNibbd);
		} else// ��� ����
		{
			newcl.setCode_subject("P");
			newcl.setSign_registr("2");
		}

		Validator<ClientA> checkHandler = CheckClient.checkCleanClient(alias, ClientUtil.ACTION_OPEN);
		if (!checkHandler.isValid(newcl)) {
			alert(checkHandler.getMessage());
			return;
		}

		Res res = clientService.doAction(newcl, action);
		if (res == null || res.getCode() != 0) {
			alert(res.getCode() + " " + res.getName());
			return;
		}
		String currentId = res.getName();
		// filter = new ClientJFilter();
		filter.clearFilterFields();// ����� ����� �� 21,11,2017
		filter.setId_client(currentId);

		refreshModel(_startPageNumber, true);
		// onDoubleClick$dataGrid$grd();

		drawActions(current);
	}

	public void onClick$act002_utverdit() {
		confirmAction(2);
	}

	public void onClick$act003_zakrit() {
		confirmAction(3);
	}

	public void onClick$act006_udalit() {
		confirmAction(6);
	}

	public void onClick$act019_correct() {
		confirmAction(19);
	}

	public void onClick$act020_utv_zakr() {
		confirmAction(20);
	}

	public void onClick$act021_izm_mob() {
		confirmAction(21);
	}

	void confirmAction(final int action) {
		// if (!cl_tabs.isVisible() || current == null) {
		if (current == null) {
			alert("�������� �������");
			return;
		}
		try {
			Messagebox.show("�� ������������� ������ ��������� ��������:" + actionsMap.get(action) + "?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							//if (event.getName().equals(Messagebox.on_OK)) {
								if (event.getName().equals("onOK")){
								try {
									_oldSelectedIndex = dataGrid.getSelectedIndex();
									executeAction(action);
								} catch (Exception e) {
									Messagebox.show(e.getMessage());
								}
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void executeAction(int action) {
		// ���� ��� ��, �� ������� ������������ �����
		if (current.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
			String fullName = String.format("YATT %s %s %s", current.getP_family(), current.getP_first_name(),
					current.getP_patronymic());
			current.setName(fullName);
		}

		if (action == 4 || action == 19) {
			current.setJ_sign_trade(j_sign_trade.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
			current.setJ_small_business(j_small_business.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
			if (action == 4 && !current.hasObjectiveChanges(copyOfCurrent)) {
				alert("��� ���������� ������ ��� ��������� ����������� ������");
				return;
			}
			if (action == 19 && current.hasObjectiveChanges(copyOfCurrent)) {
				alert("��������� ����������� ������");
				current.rollBackObjectiveChanges(copyOfCurrent);
				current.setName(copyOfCurrent.getName());
				binder.loadAll();
				return;
			}
			if (action == ClientUtil.ACTION_CHANGE
					&& !CheckClient.isTaxNumberValid(current.getJ_number_tax_registration(), alias)) {
				alert("�������� ���");
				return;
			}

			Validator<ClientA> check = CheckClient.checkExisting(alias, action);
			if (!check.isValid(current)) {
				alert("������ ������� ������ :" + check.getMessage());
				return;
			}
			// hamza 2017-11-23
			if (!check_some_fields_to_edit()) {
				alert(err_msg);
				return;
			}

		}
		Res doAction = null;
		// boolean runNibbd = (current.getSign_registr() == 1) && (action == 2
		// || action == 4);
		doAction = clientService.doAction(current, action);
		if (doAction != null && (doAction.getCode() != 0)) {
			alert(doAction.getCode() + " " + doAction.getName());
			return;
		}
		// filter = new ClientJFilter();
		// filter.clearFilterFields();// ����� ����� �� 21,11,2017
		// filter.setJ_number_tax_registration(current.getJ_number_tax_registration());
		// filter.setId_client(current.getId_client());

		// if (grd.isVisible()) {
		// refreshModel(_startPageNumber, true);
		// } else if (cl_tabs.isVisible() && cl_tabs.getSelectedIndex() == 0) {
		// refreshCurrent();
		// }
		refreshModel(_startPageNumber, true);
		if (cl_tabs.isVisible() && cl_tabs.getSelectedIndex() == 0) {
			refreshCurrent();
		}
		drawActions(current);
		// onDoubleClick$dataGrid$grd();
		/*
		 * if (runNibbd) { initNibbd(action == 2 ? NibbdQueries.CLIENT_OPEN :
		 * NibbdQueries.CLIENT_CHANGE); }
		 */
	}

	private void initAccounts() {
		// if (account_wnd == null) {
		// account_wnd = (Window)
		// Executions.createComponents(ClientUtil.ACCOUNT_SRC, account_parent,
		// null);
		// }
		// AccountViewCtrl accountVC = (AccountViewCtrl)
		// account_wnd.getAttribute("accountmain$composer");
		// accountVC.init(current);
	}

	/*
	 * private void initNibbd(NibbdQueries query) { if (nibbd_wnd == null) {
	 * nibbd_wnd = (Window) Executions.createComponents(ClientUtil.NIBBD_SRC,
	 * nibbd_modal, null); nibbd_wnd.addEventListener(Events.ON_NOTIFY, new
	 * EventListener() {
	 * 
	 * @Override public void onEvent(Event event) throws Exception { Nibbd nibbd
	 * = (Nibbd) event.getData(); nibbd_modal.setVisible(false); if (nibbd !=
	 * null) { mode = ClientUtil.MODE_NIBBD;
	 * initCLientFromNibbd(nibbd.getQuery_out()); showFormForNewClient();
	 * Events.sendEvent(new Event(Events.ON_SELECT, acode_type));
	 * btn_save.setLabel(Labels.getLabel("save"));
	 * btn_save.setAttribute("action", "open");
	 * btn_save.setImage("/images/save.png"); addgrd.setAttribute("mode",
	 * "new"); } else Messagebox.show("Nibbd Query Out is Null"); } }); }
	 * //NibbdController nibbdC = (NibbdController)
	 * nibbd_wnd.getAttribute("nibbdmain$composer"); if (query != null) {
	 * //nibbdC.initClientAction(current, query); }
	 * nibbd_modal.setVisible(true); }
	 */

	private void initSpecclt() {
		// if (specclt_wnd == null) {
		// specclt_wnd = (Window)
		// Executions.createComponents(ClientUtil.SPECCLT_SRC, specclt_div,
		// null);
		// }
		// SpecCltViewCtrl speccltVC = (SpecCltViewCtrl)
		// specclt_wnd.getAttribute("specharmain$composer");
		// speccltVC.init(current, serviceFactory);
	}

	public void initFromBpr(String id_client, String branch, String alias) {
	    _pageSize = 5;
	    if (id_client != null) {
	      this.alias = alias;;
	      filter.setId_client(id_client);
	      currentListItem.setId_client(id_client);
	      currentListItem.setBranch(branch);
	      onDoubleClick$dataGrid$grd();
	    }
	    refreshModel(_startPageNumber, true);
	  }

	private void refreshAtachmentList() {
		try {
			// atachments.setModel(new
			// BindingListModelList(organizationService.getAttachments(current),
			// true));
		} catch (Exception e) {
			alert(e.getMessage());
		}
	}

	protected void setActionBar() {
		/*
		 * Map<Integer, String> availableActionsMap =
		 * dictionaryKeeper.getAvailableActionsMap(current); Toolbarbutton
		 * button = null; actions_bar.getChildren().clear(); for
		 * (Map.Entry<Integer, String> entry : availableActionsMap.entrySet()) {
		 * if (entry.getKey() == 1 || entry.getKey() > 26) { continue; } button
		 * = new Toolbarbutton(entry.getValue()); button.setAttribute("action",
		 * entry.getKey());
		 * button.setImage(ActionImages.getImageForOrganizations(entry.getKey())
		 * ); button.addEventListener(Events.ON_CLICK, new EventListener() {
		 * 
		 * @Override public void onEvent(Event arg0) throws Exception {
		 * confirmAction((Integer) arg0.getTarget().getAttribute("action")); }
		 * }); actions_bar.appendChild(button); } button = new Toolbarbutton(
		 * "�������� � ������� SAP � ���");
		 * button.setImage("/images/z-sap.png");
		 * button.addEventListener(Events.ON_CLICK, new EventListener() {
		 * 
		 * @Override public void onEvent(Event arg0) throws Exception {
		 * compare(current); } }); actions_bar.appendChild(button);
		 * 
		 * if (mode != null && mode.equals(ClientUtil.MODE_DELTA)) { button =
		 * new Toolbarbutton("��������� � SAP");
		 * button.addEventListener(Events.ON_CLICK, new EventListener() {
		 * 
		 * @Override public void onEvent(Event arg0) throws Exception { if
		 * (current == null) { alert("client = null"); return; } try { //
		 * organizationService.createIfAbsent(current); alert("�������!"); }
		 * catch (Exception e) { alert(e.getMessage()); } } });
		 * actions_bar.appendChild(button); }
		 */

	}

	public static void main(String[] args) {
		String codeType = "11";
		System.out.println(codeType.equals(ClientUtil.CODE_TYPE_IP));
	}

	public String toXmlString(ClientA clientA) {
		String xmlString = "";
		/*
		 * try { JAXBContext context = JAXBContext.newInstance(Object.class);
		 * Marshaller m = context.createMarshaller();
		 * 
		 * m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To
		 * // format // XML
		 * 
		 * StringWriter sw = new StringWriter(); m.marshal(clientJ, sw);
		 * xmlString = sw.toString();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */ return xmlString;
	}

	public void onCheck$isAccountantCheckbox(CheckEvent checkEvent) {
		if (checkEvent.isChecked()) {
			// newcl.setAccountant(newcl.getDirector());
			binder.loadAll();
		} else {
			// newcl.setAccountant(new Person());
			binder.loadAll();
		}
	}

	public void compare(ClientA currentCustomer) {
		ClientA fetchedFromSap = null;
		ClientA ebpClient = null;
		/*
		 * try { if (!addgrd.isVisible()) { if (currentCustomer.getId_sap() !=
		 * null) { fetchedFromSap = Mappers
		 * .mapToClientJ(organizationService.getDetailsBySapId(currentCustomer.
		 * getId_sap())); } else { fetchedFromSap =
		 * Mappers.mapToClientJ(organizationService
		 * .getDetailsByNciId(currentCustomer.getId_client(),
		 * currentCustomer.getBranch())); } ebpClient =
		 * EbpService.getClientFromEbp(currentCustomer); } else { fetchedFromSap
		 * = Mappers.mapToClientJ(
		 * organizationService.getDetailsByInn(currentCustomer.
		 * getJ_number_tax_registration())); ebpClient =
		 * EbpService.getClientFromEbp(currentCustomer); } } catch (Exception e)
		 * { logger.error(CheckNull.getPstr(e)); }
		 */

		fields_diff_wnd$diff_rows.getChildren().clear();
		fields_diff_wnd$diff_grid.setAttribute(ClientUtil.SAP_CLIENT_ATTR, fetchedFromSap);
		fields_diff_wnd$diff_grid.setAttribute(ClientUtil.EBP_CLIENT_ATTR, ebpClient);

		// ClientUtil.fillRowsIfThereChanges(currentCustomer, fetchedFromSap,
		// ebpClient, fields_diff_wnd$diff_rows);
		fields_diff_wnd.setVisible(fields_diff_wnd$diff_rows.getChildren().size() > 0);
		logger.error("Breakpoint Reached");
	}

	public void onClick$btnCompareWithSapAndEbp() {
		compare(newcl);
	}

	public void onClick$btn_createOrg() throws Exception {
		// onClick$btn_save();
		// Messagebox.show(btn_save.getAttribute("action").toString());
		boolean isFound = isFoundInSAP();
		if (isFound)
			clt_dlg_wnd.setVisible(true);
		else {
			onClick$btn_save();
		}
		// onClick$btn_choose$clt_dlg_wnd();
		/*
		 * Connection c = null; try { c = ConnectionPool.getConnection(alias);
		 * Messagebox.show(clientService.getNextValForSapAction(c)); } catch
		 * (Exception e){
		 * 
		 * } finally { ConnectionPool.close(c); }
		 */
	}

	private void createSAPOrg() throws InterruptedException {
		Messagebox.show("�� ������������� ������ ��������� ��������: �������?", "", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {

					@Override
					public void onEvent(Event event) throws Exception {
						//if (event.getName().equals(Messagebox.onOK))
						if (event.getName().equals("onOK"))
							// openClient(1);
							Messagebox.show("B52");
						return;
					}
				});
	}

	public void iterate(Component comp) {
		/*
		 * do something for the component
		 */
		if (comp instanceof Label) {
			((Label) comp).setValue("Found label!");
		}
		List<Component> list = comp.getChildren();
		for (Component child : list) {
			iterate(child);
		}
	}

	public boolean check_some_fields_to_open() {
		err_msg = "";
		if (!CheckNull.isEmpty(acode_country_value.getValue()) && CheckNull.isEmpty(acode_country.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������";
			return false;
		}
		if (!CheckNull.isEmpty(aregionValue.getValue()) && CheckNull.isEmpty(aj_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������";
			return false;
		}
		if (!CheckNull.isEmpty(adistrValue.getValue()) && CheckNull.isEmpty(aj_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������";
			return false;
		}
		if (!CheckNull.isEmpty(atax_orgValue.getValue()) && CheckNull.isEmpty(aj_code_tax_org.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ���������.";
			return false;
		}
		if (!CheckNull.isEmpty(aopfValue.getValue()) && CheckNull.isEmpty(aj_opf.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ���";
			return false;
		}
		if (!CheckNull.isEmpty(aformValue.getValue()) && CheckNull.isEmpty(acode_form.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����� �������������";
			return false;
		}
		if (!CheckNull.isEmpty(asectorValue.getValue()) && CheckNull.isEmpty(aj_code_sector.getValue())) {
			err_msg = "������������ �������� � ����:  ���� ";
			return false;
		}
		if (!CheckNull.isEmpty(acl_activity_type_idValue.getValue())
				&& CheckNull.isEmpty(acl_activity_type_id.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������������";
			return false;
		}
		if (!CheckNull.isEmpty(aorgan_directValue.getValue()) && CheckNull.isEmpty(aj_code_organ_direct.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(atype_non_residentValue.getValue())
				&& CheckNull.isEmpty(atype_non_resident.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ���������������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_type_document_text.getValue()) && CheckNull.isEmpty(ap_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(ap_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����������� ";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_adr_region_text.getValue())
				&& CheckNull.isEmpty(ap_code_adr_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������-�������������� ";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_adr_distr_text.getValue()) && CheckNull.isEmpty(ap_code_adr_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������-��������������";
			return false;
		}
		if (!CheckNull.isEmpty(abankValue.getValue()) && CheckNull.isEmpty(aj_code_bank.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �����";
			return false;
		}
		// if (!CheckNull.isEmpty(aiopfValue.getValue()) &&
		// CheckNull.isEmpty(ai_opf.getValue())) {
		// err_msg = "������������ �������� � ����: ��� ���";
		// return false;
		// }
		if (!CheckNull.isEmpty(aiformValue.getValue()) && CheckNull.isEmpty(ai_form.getValue())) {
			err_msg = "������������ �������� � ����:  ����� ������������� ";
			return false;
		}
		if (!CheckNull.isEmpty(aisectorValue.getValue()) && CheckNull.isEmpty(ai_sector.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ";
			return false;
		}
		if (!CheckNull.isEmpty(aiorgan_directValue.getValue()) && CheckNull.isEmpty(ai_organ_direct.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����� ���������� ";
			return false;
		}
		if (!CheckNull.isEmpty(aswift_idValue.getValue()) && CheckNull.isEmpty(aswift_id.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �����";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_type_document_text.getValue())
				&& CheckNull.isEmpty(adirector_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_pass_place_region_text.getValue())
				&& CheckNull.isEmpty(adirector_pass_place_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ���������";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_pass_place_distr_text.getValue())
				&& CheckNull.isEmpty(adirector_pass_place_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(adirector_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����������� ���������";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_code_country_text.getValue())
				&& CheckNull.isEmpty(adirector_code_country.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ���������";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_code_adr_region_text.getValue())
				&& CheckNull.isEmpty(adirector_code_adr_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_code_adr_distr_text.getValue())
				&& CheckNull.isEmpty(adirector_code_adr_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ���������";
			return false;
		}
		if (!CheckNull.isEmpty(adirector_code_tax_org_text.getValue())
				&& CheckNull.isEmpty(adirector_code_tax_org.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ���������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_type_document_text.getValue())
				&& CheckNull.isEmpty(aaccountant_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������� ���������� ";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_pass_place_region_text.getValue())
				&& CheckNull.isEmpty(aaccountant_pass_place_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_pass_place_distr_text.getValue())
				&& CheckNull.isEmpty(aaccountant_pass_place_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(aaccountant_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����������� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_code_country_text.getValue())
				&& CheckNull.isEmpty(aaccountant_code_country.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ����������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_code_adr_region_text.getValue())
				&& CheckNull.isEmpty(aaccountant_code_adr_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ������ ����������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_code_adr_distr_text.getValue())
				&& CheckNull.isEmpty(aaccountant_code_adr_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ������ ����������";
			return false;
		}
		if (!CheckNull.isEmpty(aaccountant_code_tax_org_text.getValue())
				&& CheckNull.isEmpty(aaccountant_code_tax_org.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ����������";
			return false;
		}
		return true;
	}

	public boolean check_some_fields_to_edit() {
		err_msg = "";
		if (!CheckNull.isEmpty(code_country_value.getValue()) && CheckNull.isEmpty(code_country.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������";
			return false;
		}
		if (!CheckNull.isEmpty(regionValue.getValue()) && CheckNull.isEmpty(j_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������";
			return false;
		}
		if (!CheckNull.isEmpty(distrValue.getValue()) && CheckNull.isEmpty(j_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������";
			return false;
		}
		if (!CheckNull.isEmpty(tax_orgValue.getValue()) && CheckNull.isEmpty(j_code_tax_org.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ���������.";
			return false;
		}
		if (!CheckNull.isEmpty(opfValue.getValue()) && CheckNull.isEmpty(j_opf.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ���";
			return false;
		}
		if (!CheckNull.isEmpty(formValue.getValue()) && CheckNull.isEmpty(code_form.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����� �������������";
			return false;
		}
		if (!CheckNull.isEmpty(sectorValue.getValue()) && CheckNull.isEmpty(j_code_sector.getValue())) {
			err_msg = "������������ �������� � ����:  ���� ";
			return false;
		}
		if (!CheckNull.isEmpty(cl_activity_type_idValue.getValue())
				&& CheckNull.isEmpty(cl_activity_type_id.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������������";
			return false;
		}
		if (!CheckNull.isEmpty(organ_directValue.getValue()) && CheckNull.isEmpty(j_code_organ_direct.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(type_non_residentValue.getValue()) && CheckNull.isEmpty(type_non_resident.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ���������������";
			return false;
		}
		if (!CheckNull.isEmpty(p_type_document_text.getValue()) && CheckNull.isEmpty(p_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(p_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(p_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����������� ";
			return false;
		}
		if (!CheckNull.isEmpty(p_code_adr_region_text.getValue()) && CheckNull.isEmpty(p_code_adr_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������-�������������� ";
			return false;
		}
		if (!CheckNull.isEmpty(p_code_adr_distr_text.getValue()) && CheckNull.isEmpty(p_code_adr_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������-��������������";
			return false;
		}
		if (!CheckNull.isEmpty(bankValue.getValue()) && CheckNull.isEmpty(j_code_bank.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �����";
			return false;
		}
		// if (!CheckNull.isEmpty(iopfValue.getValue()) &&
		// CheckNull.isEmpty(i_opf.getValue())) {
		// err_msg = "������������ �������� � ����: ��� ���";
		// return false;
		// }
		// if (!CheckNull.isEmpty(iformValue.getValue()) &&
		// CheckNull.isEmpty(i_form.getValue())) {
		// err_msg = "������������ �������� � ����: ����� ������������� ";
		// return false;
		// }
		// if (!CheckNull.isEmpty(isectorValue.getValue()) &&
		// CheckNull.isEmpty(i_sector.getValue())) {
		// err_msg = "������������ �������� � ����: ��� ������� ";
		// return false;
		// }
		// if (!CheckNull.isEmpty(iorgan_directValue.getValue()) &&
		// CheckNull.isEmpty(i_organ_direct.getValue())) {
		// err_msg = "������������ �������� � ����: ��� ����� ���������� ";
		// return false;
		// }
		if (!CheckNull.isEmpty(swift_idValue.getValue()) && CheckNull.isEmpty(swift_id.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �����";
			return false;
		}
		if (!CheckNull.isEmpty(director_type_document_text.getValue())
				&& CheckNull.isEmpty(director_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(director_pass_place_region_text.getValue())
				&& CheckNull.isEmpty(director_pass_place_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ���������";
			return false;
		}
		if (!CheckNull.isEmpty(director_pass_place_distr_text.getValue())
				&& CheckNull.isEmpty(director_pass_place_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ��������� ";
			return false;
		}
		if (!CheckNull.isEmpty(director_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(director_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����������� ���������";
			return false;
		}
		/*
		 * if (!CheckNull.isEmpty(director_code_country_text.getValue()) &&
		 * CheckNull.isEmpty(director_code_country.getValue())) { err_msg =
		 * "������������ �������� � ����:  ��� ������ ���������"; return false;
		 * } if (!CheckNull.isEmpty(director_code_adr_region_text.getValue()) &&
		 * CheckNull.isEmpty(director_code_adr_region.getValue())) { err_msg =
		 * "������������ �������� � ����:  ��� ������� ��������� "; return
		 * false; } if
		 * (!CheckNull.isEmpty(director_code_adr_distr_text.getValue()) &&
		 * CheckNull.isEmpty(director_code_adr_distr.getValue())) { err_msg =
		 * "������������ �������� � ����:  ��� ������ ���������"; return false;
		 * }
		 */
		/*
		 * if (!CheckNull.isEmpty(director_code_tax_org_text.getValue()) &&
		 * CheckNull.isEmpty(director_code_tax_org.getValue())) { err_msg =
		 * "������������ �������� � ����:  ��� ��������� ���������"; return
		 * false; }
		 */
		if (!CheckNull.isEmpty(accountant_type_document_text.getValue())
				&& CheckNull.isEmpty(accountant_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������� ���������� ";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_pass_place_region_text.getValue())
				&& CheckNull.isEmpty(accountant_pass_place_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_pass_place_distr_text.getValue())
				&& CheckNull.isEmpty(accountant_pass_place_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(accountant_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ����������� ����������";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_code_country_text.getValue())
				&& CheckNull.isEmpty(accountant_code_country.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ����������";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_code_adr_region_text.getValue())
				&& CheckNull.isEmpty(accountant_code_adr_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������� ������ ����������";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_code_adr_distr_text.getValue())
				&& CheckNull.isEmpty(accountant_code_adr_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������ ������ ����������";
			return false;
		}
		if (!CheckNull.isEmpty(accountant_code_tax_org_text.getValue())
				&& CheckNull.isEmpty(accountant_code_tax_org.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ����������";
			return false;
		}

		return true;
	}

	public boolean check_some_fields_to_open_p() {
		err_msg = "";
		if (!CheckNull.isEmpty(ap_typeValue.getValue()) && CheckNull.isEmpty(ap_code_type.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_citizenship_text.getValue())
				&& CheckNull.isEmpty(ap_code_citizenship.getValue())) {
			err_msg = "������������ �������� � ����:  �����������";
			return false;
		}
		if (CheckNull.isEmpty(ap_code_resident.getValue())) {
			err_msg = "������������ �������� � ����:  �������������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_type_document_text.getValue()) && CheckNull.isEmpty(ap_type_document.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ��������� ";
			return false;
		}
		if (CheckNull.isEmpty(ap_code_nation.getValue())) {
			err_msg = "������������ �������� � ����:  �������������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_country_value.getValue()) && CheckNull.isEmpty(ap_code_country.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������";
			return false;
		}

		if (!CheckNull.isEmpty(ap_code_adr_region_text.getValue())
				&& CheckNull.isEmpty(ap_code_adr_region.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_adr_distr_text.getValue()) && CheckNull.isEmpty(ap_code_adr_distr.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_capacity_text.getValue()) && CheckNull.isEmpty(ap_code_capacity.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������������������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_bankValue.getValue()) && CheckNull.isEmpty(ap_code_bank.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �����";
			return false;
		}
		if (!CheckNull.isEmpty(ap_class_creditValue.getValue()) && CheckNull.isEmpty(ap_code_class_credit.getValue())) {
			err_msg = "������������ �������� � ����:  ��� ������������������";
			return false;
		}
		if (!CheckNull.isEmpty(ap_code_tax_org_text.getValue()) && CheckNull.isEmpty(ap_code_tax_org.getValue())) {
			err_msg = "������������ �������� � ����:  ��� �������";
			return false;
		}

		return true;
	}
	
	

}
