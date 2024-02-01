package com.is.clients.controllers;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.joda.time.DateTime;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.client_personmap.model.Person;
import com.is.client_sap.Mappers;
import com.is.clients.models.ClientJ;
import com.is.clients.models.ClientJFilter;
import com.is.clients.models.NibbdParam;
import com.is.clients.models.S_spr_oked;
import com.is.clients.models.Soato;
import com.is.clients.services.ClientDictionaries;
import com.is.clients.services.ClientJService;
import com.is.clients.services.DictionaryKeeper;
import com.is.clients.utils.ClientFields;
import com.is.clients.utils.ClientUtil;
import com.is.customer_.core.ReferenceDictionary;
import com.is.customer_.core.model.Customer;
import com.is.customer_.mapping.MappingResolver;
import com.is.customer_.sap.service.BusinessPartnerMappingInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.search.SAPSearch.SAPSearchComposer;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.ui.CustomerComposerInteractor;
import com.is.soogun.Soogun;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractClientController extends GenericForwardComposer implements CustomerComposerInteractor {
	private static final long serialVersionUID = 1L;

	protected enum SearchMode {
		DIR_NEW, ACC_NEW, DIR_FOR_CURRENT, ACC_FOR_CURRENT;
	}

	protected Window sap_bapiret, sector_wnd, nibbd_modal, nibbd_wnd, account_wnd, fields_diff_wnd, searchWnd,
			clientmain, persons_wnd, specclt_wnd, clt_dlg_wnd, director_search_wnd, appsWnd, wind_nibbd;
	protected Div grd, frm, filterdiv, sap, addgrd, account_parent, persons_div, apersons_div, specclt_div, appsDiv,
			chooseSearch, clt_dlg_wnd$div_radio_grp;
	protected Grid fields_diff_wnd$diff_grid, gr_fjView, gr_grpfltmain, wind_nibbd$res_grid;
	protected Rows fields_diff_wnd$diff_rows;
	protected Toolbar actions_bar;
	protected Radiogroup clt_dlg_wnd$paths;

	protected Listbox dataGrid, history, sap_list, sap_bapiret$messages, atachments, searchWnd$searchListbox;
	protected Tabbox cl_tabs;
	protected Tab persons_tab, cl_add_tab, acc_tab, specclt_tab, attach_tab, history_tab;// ,
																							// cards_tab;
	protected Include incl_control, incl_acc, incl_cards, director_search_wnd$search_incl,includeAdditionalClientInfo;
	protected Groupbox j_view, j_acc_grid, j_other_grid, j_contact_grid, ipView, ipView2, client_bank_grid_acc,
			client_bank__grid_main;
	protected Groupbox aj_view, aj_acc_grid, aj_other_grid, aj_contact_grid, aipView, aipView2, aclient_bank_grid_acc,
			aclient_bank_grid_main, aaccounter_grb, adirector_grb;
	protected Groupbox fjView, fipView, fipView2, facc_grb, fother_grb, fcontacs_grb, directors_grb, grpfltmain;
	protected Row aonlyForJ1, aonlyForJ2, aonlyForJ3, aonlyForJ4, aipFio_cyr, aipFio, activity_type_row,
			aactivity_type_row, row_OKED, row_non_resident, row_non_resident_new, row_OKED_new, rowIpViewPatentExp,
			rowIpViewPatentExpNew;
	protected Row onlyForJ1, onlyForJ2, onlyForJ3, onlyForJ4, ipFio_cyr, ipFio, name_row, aname_row;
	protected Row wind_nibbd$innRow, wind_nibbd$pinRow, wind_nibbd$coaRow, wind_nibbd$clientRow, wind_nibbd$currencyRow, wind_nibbd$nOrderRow, wind_nibbd$closeTypeRow, wind_nibbd$closedDoc_nRow, wind_nibbd$closedDoc_dRow, wind_nibbd$accountRow;
	protected Toolbarbutton btn_last, btn_next, btn_prev, btn_first, btn_save, btn_clear, btn_back, btn_get_ip,
			btn_sendFile, btn_new, btn_createOrg, btn_word, btn_nibbd, wind_nibbd$btn_send;
	protected Toolbar tb, top_tb;

	private Caption adir_caption, aacc_caption;
	private Window soogunWnd = null;
	private Radiogroup type_radio_open1, type_radio_close1;

	protected Checkbox j_sign_trade, j_small_business, j_sign_dep_acc, fields_diff_wnd$check_all_sap,
			fields_diff_wnd$check_all_ebp, chbLocalSearch;
	protected Datebox j_date_registration, j_patent_expiration, aj_patent_expiration, documentDate;

	protected Textbox p_passport_place_registration, p_passport_serial, p_passport_number, description,
			type_non_residentValue, iopfValue, iformValue, isectorValue, iorgan_directValue, swift_idValue,
			ftax_orgValue, fcl_activity_type_idValue, fformValue, fopfValue, atype_non_residentValue,
			aaddressCountryText, aswift_idValue, adirector_type_document_text, adirector_pass_place_region_text,
			adirector_pass_place_distr_text, adirector_code_citizenship_text, adirector_code_country_text,
			adirector_code_adr_region_text, adirector_code_adr_distr_text, adirector_code_tax_org_text,
			aaccountant_type_document_text, aaccountant_pass_place_region_text, aaccountant_pass_place_distr_text,
			aaccountant_code_citizenship_text, aaccountant_code_country_text, aaccountant_code_adr_region_text,
			aaccountant_code_adr_distr_text, aaccountant_code_tax_org_text, id_client;
	protected Datebox p_birthday, p_passport_date_registration, p_passport_date_expiration, date_doc;
	protected RefCBox p_pass_place_region, p_pass_place_district, p_code_nation, ai_opf, ai_form, ai_sector,
			ai_organ_direct, wind_nibbd$type_close_name;
	protected Textbox p_birth_place, p_post_address_street, p_post_address_quarter, p_post_address_house,
			p_post_address_flat, p_post_address, p_number_tax_registration, p_code_adr_distr_text, p_tax_orgValue,
			p_code_adr_region_text, p_citizenshipValue, p_pass_distrValue, p_pass_regionValue, p_code_citizenship_text,
			p_type_document_text, p_pass_place_region_text, p_pass_place_distr_text, p_code_tax_orgValue,
			p_type_docValue,
			wind_nibbd$account, wind_nibbd$currency, wind_nibbd$id_order, wind_nibbd$type_close_id, wind_nibbd$id_doc, wind_nibbd$acc, wind_nibbd$inn, wind_nibbd$pinfl;

	protected Textbox j_short_name, j_place_regist_name, name, j_number_registration_doc, j_number_tax_registration,
			j_code_head_organization, j_account, j_post_address, j_phone, j_fax, j_email, j_soato, j_okpo,
			j_inn_head_organization, j_responsible_emp;
	protected Textbox typeValue, regionValue, tax_orgValue, opfValue, formValue, oldsectorValue, organ_directValue,
			distrValue, sectorValue, bankValue, code_country_value, addressCountryText, aiopfValue, aiformValue,
			aisectorValue, aiorgan_directValue, file_nameValue, afile_nameValue, subbranchValue, asubbranchValue;

	protected RefCBox code_country1, code_resident, code_type, code_form, state, j_code_tax_org, j_code_organ_direct,
			j_code_bank, j_region, j_distr, j_opf, j_code_sector, cl_activity_type_id, director_code_nation,
			director_code_resident, director_code_gender, director_code_country, director_code_tax_org, file_name, subbranch, afile_name, asubbranch;
	protected RefCBox p_code_tax_org, p_code_gender, p_type_document, p_code_adr_region, p_code_adr_distr,
			p_code_citizenship, code_country, addressCountry, aaddressCountry, director_type_document,
			director_pass_place_region, director_pass_place_distr, director_code_citizenship, director_code_adr_region,
			director_code_adr_distr, accountant_type_document, accountant_pass_place_region,
			accountant_pass_place_distr, accountant_code_citizenship, accountant_code_country,
			accountant_code_adr_region, accountant_code_adr_distr, accountant_code_tax_org;

	protected Textbox ftypeValue, fregionValue, fsectorValue;
	protected RefCBox fcode_country, fcode_resident, fcode_type, fcode_form, fstate, fj_code_tax_org, fsign_registr,
			fj_code_organ_direct, fj_code_class_credit, fj_code_bank, fj_region, fj_distr, fj_opf, fj_code_sector;

	protected RefCBox fp_code_tax_org, fp_code_gender, fp_type_document, fp_code_adr_region, fp_code_adr_distr,
			fp_code_citizenship, fp_code_nation, fp_pass_place_region, fp_pass_place_district;
	protected Textbox fid_client;

	protected Textbox aname, aj_short_name, ap_birth_place, ap_number_tax_registration, aj_number_registration_doc,
			aj_place_regist_name, aj_okpo, aj_post_address, aj_number_tax_registration, aj_code_head_organization,
			aj_inn_head_organization, aj_soato, aj_phone, aj_fax, aj_email;
	protected Textbox atypeValue, adistrValue, acode_country_value, aregionValue, aopfValue, atax_orgValue, aformValue,
			aorgan_directValue, abankValue, asectorValue, aoldsectorValue, ap_code_adr_distrValue, acountryValue1,
			ap_code_tax_orgValue;
	protected RefCBox acode_country, acode_country1, acode_resident, acode_type, acode_form, aj_code_tax_org,
			aj_code_organ_direct, aj_code_bank, aj_region, aj_distr, aj_opf, aj_code_sector, acl_activity_type_id,
			fcl_activity_type_id;
	protected Checkbox aj_sign_trade, aj_small_business, aj_sign_dep_acc;
	protected Datebox aj_date_registration;
	protected RefCBox acode_subject, abranch, ap_code_citizenship, ap_code_nation, ap_pass_place_region,
			ap_pass_place_district, ap_type_document, ap_code_gender, ap_code_bank_combobox, ap_code_class_credit,
			ap_code_adr_region, ap_code_capacity, ap_code_tax_org, ap_code_adr_distr;
	protected Datebox ap_passport_date_registration, ap_passport_date_expiration, ap_capacity_status_date, ap_birthday;
	protected Textbox ap_passport_number, ap_passport_serial, ap_num_certif_capacity, ap_pension_sertif_serial,
			ap_capacity_status_place, ap_passport_place_registration, ap_post_address_street, ap_post_address_quarter,
			ap_post_address_flat, ap_post_address, ap_post_address_house;
	protected Textbox ap_code_citizenship_text, ap_pass_place_region_text, ap_type_document_text,
			ap_pass_place_distr_text, ap_code_adr_region_text, ap_code_adr_distr_text, atype_non_resident;

	protected RefCBox acode_resident1, attch_types;

	protected Label acode_countryLabel, aresLabel, aokpo_label, j_short_name_label, aj_short_name_label;
	protected Label countryLabel, resLabel, okpo_label;

	@SuppressWarnings("unused")
	protected RefCBox i_opf, i_form, i_sector, i_organ_direct, swift_id, type_non_resident, ai_code_sector,
			ai_code_organ_direct, aswift_id;

	protected RefCBox code_resident1;
	protected Textbox countryValue1, acl_activity_type_idValue;

	protected RefCBox adirector_type_document, adirector_code_resident, adirector_code_citizenship,
			adirector_code_country, adirector_code_gender, adirector_code_nation, adirector_code_tax_org,
			adirector_pass_place_region, adirector_pass_place_distr, adirector_code_adr_distr,
			adirector_code_adr_region;
	private Textbox adirector_passport_serial, adirector_passport_number, adirector_passport_place_registration;
	private Datebox adirector_passport_date_expiration, adirector_passport_date_registration, adirector_birthday,
			fdate_open, fdate_open1, fdate_close, fdate_close1;

	protected Checkbox adirector_pass_place_checkbox, fsign_registr1, fsign_registr3;

	protected RefCBox aaccountant_type_document, aaccountant_code_resident, aaccountant_code_citizenship,
			aaccountant_code_country, aaccountant_code_gender, aaccountant_code_nation, aaccountant_code_tax_org,
			aaccountant_pass_place_region, aaccountant_pass_place_distr, aaccountant_code_adr_distr,
			aaccountant_code_adr_region, accountant_code_nation, accountant_code_resident, accountant_code_gender;
	protected Textbox director_type_document_text, director_pass_place_region_text, director_pass_place_distr_text,
			director_code_citizenship_text, director_code_country_text, director_code_adr_region_text,
			director_code_adr_distr_text, director_code_tax_org_text, accountant_type_document_text,
			accountant_pass_place_region_text, accountant_pass_place_distr_text, accountant_code_citizenship_text,
			accountant_code_country_text, accountant_code_adr_region_text, accountant_code_adr_distr_text;
	protected Textbox cl_activity_type_idValue, accountant_code_tax_org_text;
	private Textbox aaccountant_passport_serial, aaccountant_passport_number, aaccountant_passport_place_registration;
	private Datebox aaccountant_passport_date_expiration, aaccountant_passport_date_registration, aaccountant_birthday;
	private Checkbox aaccountant_pass_place_checkbox;
	private Iframe iframe;

	protected Textbox sap_docId, sap_client_name;
	protected RefCBox sap_docType;
	protected RefCBox sector_wnd$sectorGroups;
	protected Listbox sector_wnd$sectors;
	protected List<RefData> sgList;
	protected List<S_spr_oked> sectorsList;
	protected List<RefData> comboboxList;

	protected String sapDocId;
	protected Paging clientJPaging;
	protected int _pageSize = 15;
	protected int _startPageNumber = 0;
	protected int _firstPageNumber = 0;
	protected int _totalSize = 0;
	protected int _oldSelectedIndex = 0;
	protected boolean _needsTotalSizeUpdate = true;
	protected SAPSearchComposer searchComposer;
	protected String alias, ses_branch, un, pw;
	protected int userId;
	protected HashMap<String, String> attachMap;
	protected HashMap<String, String> clientLettersMap;
	protected HashMap<String, String> clientStates;
	protected Map<Integer, String> actionsMap;
	protected Map<String, String> clientTypes;
	protected ClientJ copyOfCurrent;
	@Getter
	@Setter
	public ClientJ current = new ClientJ();
	@Getter
	@Setter
	public ClientJ currentListItem = new ClientJ();
	@Getter
	@Setter
	public ClientJ newcl = new ClientJ();
	@Getter
	@Setter
	public ClientJFilter filter = new ClientJFilter();
	@Getter
	@Setter
	public NibbdParam nibbdparam = new NibbdParam();
	protected Dao<ClientJ> clientDao;
	protected DictionaryKeeper dictionaryKeeper;
	protected ClientJService clientService;
	protected AnnotateDataBinder binder;
	protected String mode;
	protected SearchMode searchMode;
	private static Logger logger = Logger.getLogger(AbstractClientController.class);

	public AbstractClientController() {
		super('$', false, false);
	}

	protected abstract void setActionBar();

	protected abstract void openClient(int action);

	public void onPaging$clientJPaging(ForwardEvent event) {
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
		List children = director_search_wnd$search_incl.getChildren();
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof Window)
				searchComposer = (SAPSearchComposer) ((Window) children.get(i)).getAttribute("SAPSearchWnd$composer");
		}
		// searchComposer.setFacade(new ContactSearchFacade(null,null));
		searchComposer.btnCreateCustomer.setVisible(false);
		searchComposer.consumeMessages(true);
		searchComposer.setItemListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Customer customer = searchComposer
						.convertToCustomer((Response) event.getTarget().getAttribute("searchResult"));
				show(customer);
			}
		});
	}

	protected void setModels() {
		// clientActions = dictionaryKeeper.getClientActions();
		clientLettersMap = DictionaryKeeper.getClientLetters();
		clientStates = dictionaryKeeper.getClientStatesMap();
		attachMap = dictionaryKeeper.getAttachMap();
		actionsMap = DictionaryKeeper.getClientActionsMap();
		clientTypes = Util.listToMap(DictionaryKeeper.getClientTypes());

		p_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));
		ap_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));
		// fp_code_nation.setModel(new
		// ListModelList(dictionaryKeeper.getNations()));

		p_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));
		ap_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));
		// fp_code_gender.setModel(new
		// ListModelList(dictionaryKeeper.getGender()));

		p_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		ap_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		// fp_code_citizenship.setModel(new
		// ListModelList(dictionaryKeeper.getCountries()));
		// aj_director_code_citizenship.setModel(new
		// ListModelList(dictionaryKeeper.getCountries()));
		// aj_accountant_code_citizenship.setModel(new
		// ListModelList(dictionaryKeeper.getCountries()));

		p_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		ap_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		// fp_type_document.setModel(new
		// ListModelList(dictionaryKeeper.getPassportTypes()));
		// aj_director_type_document.setModel(new
		// ListModelList(dictionaryKeeper.getPassportTypes()));
		// aj_accountant_type_document.setModel(new
		// ListModelList(dictionaryKeeper.getPassportTypes()));

		state.setModel(new ListModelList(DictionaryKeeper.getClientStates()));
		fstate.setModel(new ListModelList(DictionaryKeeper.getClientStates()));
		// state.setModel(new
		// ListModelList(dictionaryKeeper.getHOperation(ses_branch)));

		// fsign_registr.setModel(new
		// ListModelList(dictionaryKeeper.getSignRegistrs()));

		acode_country1.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		code_country1.setModel(new ListModelList(DictionaryKeeper.getCountries()));

		// TODO
		acode_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		// fcode_country.setModel(new
		// ListModelList(dictionaryKeeper.getCountries()));

		code_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		acode_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		code_resident1.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		acode_resident1.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		// fcode_resident.setModel(new
		// ListModelList(dictionaryKeeper.getResidentTypes()));
		code_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		acode_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));
		fcode_type.setModel(new ListModelList(DictionaryKeeper.getClientTypes()));

		code_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		acode_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		fcode_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		i_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));
		ai_form.setModel(new ListModelList(DictionaryKeeper.getVsbs()));

		j_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		aj_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		fj_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		p_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		ap_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		// fp_code_tax_org.setModel(new
		// ListModelList(dictionaryKeeper.getGni()));

		// j_code_sector_old.setModel(new
		// ListModelList(dictionaryKeeper.getCodeSectorOld()));
		// aj_code_sector_old.setModel(new
		// ListModelList(dictionaryKeeper.getCodeSectorOld()));
		// fj_code_sector_old.setModel(new
		// ListModelList(dictionaryKeeper.getCodeSectorOld()));

		j_code_sector.setModel(new ListModelList(DictionaryKeeper.getOked()));
		aj_code_sector.setModel(new ListModelList(DictionaryKeeper.getOked()));
		fj_code_sector.setModel(new ListModelList(DictionaryKeeper.getOked()));
		i_sector.setModel(new ListModelList(DictionaryKeeper.getOked()));
		ai_code_sector.setModel(new ListModelList(DictionaryKeeper.getOked()));

		j_code_organ_direct.setModel(new ListModelList(DictionaryKeeper.getSoogun()));
		aj_code_organ_direct.setModel(new ListModelList(DictionaryKeeper.getSoogun()));
		// fj_code_organ_direct.setModel(new
		// ListModelList(dictionaryKeeper.getSoogun()));
		i_organ_direct.setModel(new ListModelList(DictionaryKeeper.getSoogun()));
		ai_code_organ_direct.setModel(new ListModelList(DictionaryKeeper.getSoogun()));

		// j_code_class_credit.setModel(new
		// ListModelList(DictionaryKeeper.getCreditklass()));
		// aj_code_class_credit.setModel(new
		// ListModelList(DictionaryKeeper.getCreditklass()));
		// fj_code_class_credit.setModel(new
		// ListModelList(dictionaryKeeper.getCreditklass()));
		j_code_bank.setModel(new ListModelList(DictionaryKeeper.getBanks()));
		aj_code_bank.setModel(new ListModelList(DictionaryKeeper.getBanks()));
		// fj_code_bank.setModel(new
		// ListModelList(dictionaryKeeper.getBanks()));

		j_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		aj_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		// fj_region.setModel(new ListModelList(dictionaryKeeper.getRegions()));
		p_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		ap_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		// fp_code_adr_region.setModel(new
		// ListModelList(dictionaryKeeper.getRegions()));
		p_pass_place_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		ap_pass_place_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		// fp_pass_place_region.setModel(new
		// ListModelList(dictionaryKeeper.getRegions()));

		j_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		aj_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		// fj_distr.setModel(new
		// ListModelList(dictionaryKeeper.getDistricts()));
		p_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		ap_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		// fp_code_adr_distr.setModel(new
		// ListModelList(dictionaryKeeper.getDistricts()));
		p_pass_place_district.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
	 	ap_pass_place_district.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		// fp_pass_place_district.setModel(new
		// ListModelList(dictionaryKeeper.getDistricts()));

		j_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));
		aj_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));
		fj_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));
		i_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));
		ai_opf.setModel(new ListModelList(DictionaryKeeper.getOpf()));

		adirector_code_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		adirector_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		adirector_code_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		adirector_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		adirector_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));
		adirector_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));
		adirector_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		adirector_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		adirector_pass_place_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		adirector_pass_place_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		adirector_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));
		aaccountant_code_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		aaccountant_code_citizenship.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		aaccountant_code_resident.setModel(new ListModelList(DictionaryKeeper.getResidentTypes()));
		aaccountant_type_document.setModel(new ListModelList(DictionaryKeeper.getPassportTypes()));
		aaccountant_code_gender.setModel(new ListModelList(DictionaryKeeper.getGender()));
		aaccountant_code_nation.setModel(new ListModelList(DictionaryKeeper.getNations()));
		aaccountant_code_adr_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		aaccountant_code_adr_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		aaccountant_pass_place_region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
		aaccountant_pass_place_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
		aaccountant_code_tax_org.setModel(new ListModelList(DictionaryKeeper.getGni()));

		sap_docType.setModel(new ListModelList(ClientDictionaries.getSapTypes()));
		attch_types.setModel(new ListModelList(DictionaryKeeper.getAttachTypes()));

		swift_id.setModel(new ListModelList(DictionaryKeeper.getSwift()));
		cl_activity_type_id.setModel(new ListModelList(DictionaryKeeper.getActivityType()));
		acl_activity_type_id.setModel(new ListModelList(DictionaryKeeper.getActivityType()));
		fcl_activity_type_id.setModel(new ListModelList(DictionaryKeeper.getActivityType()));

		code_country.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		// acodeCountry.setModel(new
		// ListModelList(dictionaryKeeper.getCountries()));
		addressCountry.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		aaddressCountry.setModel(new ListModelList(DictionaryKeeper.getCountries()));
		
		if (DictionaryKeeper.getSubsidiaries_map().containsKey(ses_branch)) {
			file_name.setModel(new ListModelList(DictionaryKeeper.getSubsidiaries_map().get(ses_branch)));	
			afile_name.setModel(new ListModelList(DictionaryKeeper.getSubsidiaries_map().get(ses_branch)));			
		} else {
			file_name.setModel(new ListModelList(new LinkedList<RefData>()));
			afile_name.setModel(new ListModelList(new LinkedList<RefData>()));
		}
		if (DictionaryKeeper.getSubbranchs_map().containsKey(ses_branch)) {
			subbranch.setModel(new ListModelList(DictionaryKeeper.getSubbranchs_map().get(ses_branch)));	
			asubbranch.setModel(new ListModelList(DictionaryKeeper.getSubbranchs_map().get(ses_branch)));
		} else {
			subbranch.setModel(new ListModelList(new LinkedList<RefData>()));
			asubbranch.setModel(new ListModelList(new LinkedList<RefData>()));
		}
	}

	protected void refreshModel(int activePage, boolean showFirstPage) {
		clientJPaging.setPageSize(_pageSize);
		if ((!fsign_registr1.isChecked() && !fsign_registr3.isChecked())
				|| (fsign_registr1.isChecked() && fsign_registr3.isChecked()))
			filter.setSign_registr(0);
		else if (fsign_registr1.isChecked())
			filter.setSign_registr(1);
		else
			filter.setSign_registr(3);
		clientDao.setFilter(filter);
		List<ClientJ> clientList = clientDao.getListWithPaging(activePage, _pageSize);
		clientJPaging.setTotalSize(clientDao.getCount());
		dataGrid.setModel(new BindingListModelList(clientList, true));
		if (clientList.size() > 0) {
			dataGrid.setSelectedIndex(_oldSelectedIndex);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
			// this.currentListItem =clientList.get(_oldSelectedIndex);
			// sendSelEvt();
		}
		incl_control.setSrc(null);
	}

	public void onClick$btn_open() {
		try {
			Messagebox.show("вы действительно хотите выполнить действие: открыть?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals(Messagebox.ON_OK))
								openClient(1);
							return;
						}
					});
		} catch (InterruptedException e) {
			logger.error(CheckNull.getPstr(e));
		}
	}

	protected void initNewClient() {
		ZkUtils.clearForm(addgrd);
		id_client.setReadonly(true);
		btn_save.setLabel(Labels.getLabel("save"));
		btn_save.setAttribute("action", "open");
		btn_save.setImage("/images/save.png");
		addgrd.setAttribute("mode", "new");

		newcl.setCode_resident(ClientUtil.CODE_RESIDENT);
		newcl.setSign_registr(ClientUtil.SIGN_REGISTR_PRIMARY);
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

	protected void initNewClient_secondary() {
		ZkUtils.clearForm(addgrd);
		id_client.setReadonly(false);
		btn_save.setLabel(Labels.getLabel("save"));
		btn_save.setAttribute("action", "open");
		btn_save.setAttribute("action1", "secondary");
		btn_save.setImage("/images/save.png");
		addgrd.setAttribute("mode", "new");

		newcl.setCode_resident(ClientUtil.CODE_RESIDENT);
		newcl.setSign_registr(ClientUtil.SIGN_REGISTR_PRIMARY_NOT);
		newcl.setBranch(ses_branch);
		newcl.setCode_country(ClientUtil.COUNTRY_UZ);
		//newcl.setJ_code_bank(ses_branch);
		//newcl.setAddressCountry(ClientUtil.COUNTRY_UZ);
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
		p_pass_place_region_text.setValue(current.getP_pass_place_region());
		p_pass_place_distr_text.setValue(current.getP_pass_place_district());
		p_code_citizenship_text.setValue(current.getP_code_citizenship());
		countryValue1.setValue(current.getCode_country());
		p_code_adr_region_text.setValue(current.getP_code_adr_region());
		p_code_adr_distr_text.setValue(current.getP_code_adr_distr());
		p_code_tax_orgValue.setValue(current.getP_code_tax_org());
		bankValue.setValue(current.getJ_code_bank());
		countryValue1.setValue(current.getCode_country());
		p_code_tax_orgValue.setValue(current.getP_code_tax_org());
		code_country_value.setValue(current.getCode_country());
		((Textbox) cl_activity_type_id.getPreviousSibling()).setValue(current.getJ_type_activity());
		file_nameValue.setValue(current.getFile_name());
		subbranchValue.setValue(current.getSubbranch());
	}

	protected void showCodesForNewcl() {
		atypeValue.setValue(newcl.getCode_type());
		// TODO
		acode_country_value.setValue(newcl.getCode_country());
		aregionValue.setValue(newcl.getJ_region());
		adistrValue.setValue(newcl.getJ_distr());
		atax_orgValue.setValue(newcl.getJ_code_tax_org());
		aopfValue.setValue(newcl.getJ_opf());
		aformValue.setValue(newcl.getCode_form());
		asectorValue.setValue(newcl.getJ_code_sector());
		aorgan_directValue.setValue(newcl.getJ_code_organ_direct());
		ap_type_document_text.setValue(newcl.getP_type_document());
		ap_pass_place_region_text.setValue(newcl.getP_pass_place_region());
		ap_pass_place_distr_text.setValue(newcl.getP_pass_place_district());
		ap_code_citizenship_text.setValue(newcl.getP_code_citizenship());
		acountryValue1.setValue(newcl.getCode_country());
		ap_code_adr_region_text.setValue(newcl.getP_code_adr_region());
		ap_code_adr_distr_text.setValue(newcl.getP_code_adr_distr());
		ap_code_tax_orgValue.setValue(newcl.getP_code_tax_org());
		abankValue.setValue(newcl.getJ_code_bank());
		acl_activity_type_idValue.setValue(newcl.getJ_type_activity());
		acountryValue1.setValue(newcl.getCode_country());
		ap_code_tax_orgValue.setValue(newcl.getP_code_tax_org());
		addressCountryText.setValue(newcl.getAddressCountry());
		afile_nameValue.setValue(newcl.getFile_name());
		asubbranchValue.setValue(newcl.getSubbranch());
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

	public void showContactPerson(Person person) {
		switch (searchMode) {
		case DIR_NEW:
			newcl.setDirector(person);
			adir_caption.setLabel("Бухгалтер - " + person.getName());
			break;
		case ACC_NEW:
			newcl.setAccountant(person);
			aacc_caption.setLabel("Директор - " + person.getName());
			break;
		case DIR_FOR_CURRENT:
			current.setDirector(person);
			break;
		case ACC_FOR_CURRENT:
			current.setAccountant(person);
			break;
		default:
			break;
		}
		binder.loadAll();
		showCodesForNewcl();
		director_search_wnd.setVisible(false);
	}

	public void onDoubleClick$dataGrid$grd() {
		showFormForCurrent();
		cl_tabs.setVisible(true);
		cl_tabs.setSelectedIndex(0);
		try {
			if (currentListItem == null) {
				return;
			}
			current = clientDao.getItemByStringId(currentListItem.getBranch(), currentListItem.getId_client());
			if (current != null)
				copyOfCurrent = current.clone();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		if (current != null) {
			if (current.getCode_type() != null) {
				showIpView_frm(current.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || current.getCode_type().equals(ClientUtil.CODE_TYPE_SE));
			}
			hideTabs(!current.getState().equals("1"));
			setActionBar();
			j_small_business.setChecked(current.getJ_small_business() != null
					&& current.getJ_small_business().equals(ClientUtil.CHECKBOX_Y));
			j_sign_trade.setChecked(
					current.getJ_sign_trade() != null && current.getJ_sign_trade().equals(ClientUtil.CHECKBOX_Y));
			j_sign_dep_acc.setChecked(
					current.getJ_sign_dep_acc() != null && current.getJ_sign_dep_acc().equals(ClientUtil.CHECKBOX_Y));
			if (current.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || current.getCode_type().equals(ClientUtil.CODE_TYPE_SE)) {
				current.setP_number_tax_registration(current.getJ_number_tax_registration());
				current.setP_code_tax_org(current.getJ_code_tax_org());
			}
			showCodesForCurrent();
			j_distr.setModel(new ListModelList(DictionaryKeeper.getDistricts()));
			//Events.sendEvent(code_type, new Event(Events.ON_SELECT));
			//Events.sendEvent(code_type, new Event(Events.ON_CHANGE));
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
			// filter = new ClientJFilter();
			filter.clearFilterFields();// новый метод от 21,11,2017
		}
		onClick$btn_back();
		refreshModel(_startPageNumber, false);
	}

	public void onClick$btn_refresh() {
		if (grd.isVisible()) {
			// filter = new ClientJFilter();
			filter.clearFilterFields();// новый метод от 21,11,2017
			refreshModel(_startPageNumber, true);
		} else if (cl_tabs.isVisible() && cl_tabs.getSelectedIndex() == 0) {
			refreshCurrent();
		}
	}

	protected void refreshCurrent() {
		setCurrent(clientDao.getItemByStringId(current.getBranch(), current.getId_client()));
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
		// filter = new ClientJFilter();
		filter.clearFilterFields();// новый метод от 21,11,2017
		ZkUtils.clearForm(filterdiv);
		fsign_registr1.setChecked(false);
		fsign_registr3.setChecked(false);
		type_radio_open1.setSelectedIndex(0);
		onCheck$type_radio_open1();
		type_radio_close1.setSelectedIndex(0);
		onCheck$type_radio_close1();
	}

	public void onClick$btn_show_search_sap() {
		hideAll();
		chooseSearch.setVisible(true);
		// sap.setVisible(true);
		onCheck$chbLocalSearch();
	}

	public void onClick$btn_clear_sap_search() {
		sap_docId.setValue(null);
		sap_docType.setValue(null);
		sap_docType.setSelecteditem(null);
		sap_client_name.setValue(null);
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
	}

	public void select_code_type() {
		if (current.getCode_type() != null) {
			boolean isIp = (current.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || current.getCode_type().equals(ClientUtil.CODE_TYPE_SE));
			boolean isBankType = current.getCode_type().equals(ClientUtil.CODE_TYPE_BANK);
			showIpView_frm(isIp);
			row_OKED.setVisible(true);
			// rowIpViewPatentExp.setVisible(isIp ||
			// (newcl.getCode_type().equals("05")) );
			// rowIpViewPatenExpNew.setVisible(isIp);

			//j_view.setVisible(!isBankType);
			//j_acc_grid.setVisible(!isBankType);
			//j_other_grid.setVisible(!isBankType);
			//j_contact_grid.setVisible(!isBankType);
			//client_bank__grid_main.setVisible(isBankType);
			//client_bank_grid_acc.setVisible(isBankType);

			boolean isAllowed = current.getCode_type().equals(ClientUtil.CODE_TYPE_NON_FINANCIAL)
					|| current.getCode_type().equals(ClientUtil.CODE_TYPE_OTHER);
			// j_patent_expiration.setDisabled(!(isIp || isAllowed));
			rowIpViewPatentExp.setVisible(isIp || isAllowed);
			Label j_patent_expiration_label = (Label) j_patent_expiration.getPreviousSibling();
			j_patent_expiration_label
					.setValue(isAllowed == true ? "Срок действия аккредитация" : "Срок действия патента");
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
		// j_distr.setModel(new
		// ListModelList(ClientDictionaries.getDistrByRegion(null,
		// j_region.getValue(), alias))); //hamza comments
		j_distr.setModel(new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(j_region.getValue(), alias)));
	}

	public void onChange$j_region() {
		current.setJ_distr(null);
		j_distr.setSelecteditem(null);
		distrValue.setValue(null);

		current.setJ_region(j_region.getValue());
		regionValue.setValue(j_region.getValue());
		// j_distr.setModel(new
		// ListModelList(ClientDictionaries.getDistrByRegion(null,
		// j_region.getValue(), alias))); //hamza comments
		j_distr.setModel(new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(j_region.getValue(), alias)));
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
		if (j_soato.getValue() != null && j_soato.getValue().length() < 7)
			return;
		if (j_soato.getValue() != null && j_soato.getValue().length() > 7) {
			soatoString = j_soato.getValue().substring(0, 7);
		}
		soatoString = j_soato.getValue().substring(2, 7);
		Soato soato = DictionaryKeeper.getSoatoMap().get(soatoString);
		if (soato != null) {
			current.setJ_region(soato.getRegion());
			regionValue.setValue(soato.getRegion());
			Events.sendEvent(new Event("onInitRender", j_region));
			j_distr.setModel(new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(current.getJ_region(), alias)));
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
		boolean isIp = (newcl.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || newcl.getCode_type().equals(ClientUtil.CODE_TYPE_SE));
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

			//aj_view.setVisible(!isBankType);
			//aj_acc_grid.setVisible(!isBankType);
			//aj_other_grid.setVisible(!isBankType);
			//aj_contact_grid.setVisible(!isBankType);
			//aclient_bank_grid_main.setVisible(isBankType);
			//aclient_bank_grid_acc.setVisible(isBankType);

			adirector_grb.setVisible(!isIp );//&& !isBankType);
			aaccounter_grb.setVisible(!isIp );//&& !isBankType);
			newcl.setCode_subject(ClientUtil.CODE_SUBJECT_J);
			abankValue.setReadonly(!isBankType);
			aj_code_bank.setReadonly(!isBankType);
			row_OKED_new.setVisible(true);

			boolean isAllowed = newcl.getCode_type().equals(ClientUtil.CODE_TYPE_NON_FINANCIAL)
					|| newcl.getCode_type().equals(ClientUtil.CODE_TYPE_OTHER);
			// aj_patent_expiration.setDisabled(!(isIp || isAllowed));
			rowIpViewPatentExpNew.setVisible(isIp || isAllowed);
			Label j_patent_expiration_label = (Label) aj_patent_expiration.getPreviousSibling();
			String patent_expiration_label_value = isAllowed == true ? "Срок действия аккредитации"
					: "Срок действия патента";
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
		// aj_distr.setModel(new
		// ListModelList(ClientDictionaries.getDistrByRegion(null,
		// aj_region.getValue(), alias))); //hamza comments 2017.12.07
		aj_distr.setModel(new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(aj_region.getValue(), alias)));
	}

	public void onChange$aj_region() {
		newcl.setJ_distr(null);
		aj_distr.setSelecteditem(null);
		adistrValue.setValue(null);

		newcl.setJ_region(aj_region.getValue());
		aregionValue.setValue(aj_region.getValue());
		// aj_distr.setModel(new
		// ListModelList(ClientDictionaries.getDistrByRegion(null,
		// aj_region.getValue(), alias)));
		aj_distr.setModel(new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(aj_region.getValue(), alias)));
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
			//aj_region.setSelecteditem(soato.getRegion());
			aregionValue.setValue(soato.getRegion());
			Events.sendEvent(new Event("onInitRender", aj_region));
			// aj_distr.setModel(new
			// ListModelList(ClientDictionaries.getDistrByRegion(null,
			// newcl.getJ_region(), alias)));
			aj_distr.setModel(new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(newcl.getJ_region(), alias)));
			newcl.setJ_distr(soato.getDistr());
			//aj_distr.setSelecteditem(soato.getDistr());
			adistrValue.setValue(soato.getDistr());
			Events.sendEvent(new Event("onInitRender", aj_distr));
			// newcl.setJ_post_address(Util.getDesctiption(newcl.getJ_region(),
			// dictionaryKeeper.getRegions()) + " "
			// + Util.getDesctiption(newcl.getJ_distr(),
			// dictionaryKeeper.getDistricts()));
			newcl.setJ_code_tax_org(soato.getKod_gni());
			atax_orgValue.setValue(soato.getKod_gni());
			Events.sendEvent(new Event("onInitRender", j_code_tax_org));
			newcl.setP_code_tax_org(soato.getKod_gni());
			ap_code_tax_orgValue.setValue(soato.getKod_gni());
		}
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
			ap_pass_place_region.setDisabled(checkBox.isChecked());
			ap_pass_place_district.setDisabled(checkBox.isChecked());
			ap_pass_place_region_text.setDisabled(checkBox.isChecked());
			ap_pass_place_distr_text.setDisabled(checkBox.isChecked());
			if (checkBox.isChecked()) {
				ap_pass_place_region.setSelecteditem(null);
				ap_pass_place_district.setSelecteditem(null);
				ap_pass_place_region_text.setValue(null);
				ap_pass_place_distr_text.setValue(null);
				newcl.setP_code_adr_distr(null);
				newcl.setP_code_adr_region(null);
			}
		} else if (checkBox.getId().equalsIgnoreCase("p_pass_place_checkbox")) {
			p_pass_place_region.setDisabled(checkBox.isChecked());
			p_pass_place_district.setDisabled(checkBox.isChecked());
			p_pass_place_region_text.setDisabled(checkBox.isChecked());
			p_pass_place_distr_text.setDisabled(checkBox.isChecked());
			if (checkBox.isChecked()) {
				p_pass_place_region.setSelecteditem(null);
				p_pass_place_district.setSelecteditem(null);
				p_pass_place_region_text.setValue(null);
				p_pass_place_distr_text.setValue(null);
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
		soogunWnd.setTitle("Органы управления");
		soogunWnd.setMaximizable(true);
		soogunWnd.addEventListener("onNotifySoogun", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				soogunWnd.setVisible(false);
				Soogun soogun = (Soogun) event.getData();

				if (addgrd.isVisible()) {
					newcl.setJ_code_organ_direct(soogun.getData());
				} else if (cl_tabs.isVisible()) {
					current.setJ_code_organ_direct(soogun.getData());
				}

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
			ap_pass_place_region_text.focus();
			ap_pass_place_region_text.select();
			break;
		case P_PASS_PLACE_REGION:
			ap_pass_place_distr_text.focus();
			ap_pass_place_distr_text.select();
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
			acountryValue1.focus();
			acountryValue1.select();
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
			ap_code_tax_orgValue.focus();
			ap_code_tax_orgValue.select();
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
			countryValue1.focus();
			countryValue1.select();
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
			p_post_address_street.focus();
			p_post_address_street.select();
			break;
		case P_POST_ADDRESS_STREET:
			p_post_address_quarter.focus();
			p_post_address_quarter.select();
			break;
		case P_POST_ADDRESS_QUARTER:
			p_post_address_house.focus();
			p_post_address_house.select();
			break;
		case P_POST_ADDRESS_HOUSE:
			p_post_address_flat.focus();
			p_post_address_flat.select();
			break;
		case P_POST_ADDRESS_FLAT:
			p_post_address.focus();
			p_post_address.select();
			break;
		case P_POST_ADDRESS:
			p_number_tax_registration.focus();
			p_number_tax_registration.select();
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
		sap.setVisible(false);
		tb.setVisible(false);
		chooseSearch.setVisible(false);
	}

	protected void showFormForCurrent() {
		hideAll();
		cl_tabs.setVisible(true);
		frm.setVisible(true);
		addgrd.setVisible(false);
		actions_bar.setVisible(true);
		hideTabs(false);
	}

	protected void showFormForNewClient() {
		hideAll();
		addgrd.setVisible(true);
		tb.setVisible(true);
	}

	protected void showIpView_frm(boolean isIp) {
		ipView.setVisible(isIp);
		ipView2.setVisible(isIp);

		ipFio_cyr.setVisible(isIp);
		activity_type_row.setVisible(isIp);
		//row_OKED.setVisible(!isIp);
		row_non_resident.setVisible(!isIp);
		// ipFio.setVisible(isIp);
		okpo_label.setVisible(!isIp);
		j_okpo.setVisible(!isIp);
		// onlyForJ1.setVisible(!bol);
		// onlyForJ2.setVisible(!bol);
		// onlyForJ3.setVisible(!bol);
		onlyForJ4.setVisible(!isIp);

		// countryLabel.setVisible(!isIp);
		resLabel.setVisible(!isIp);
		// code_country_value.setVisible(!isIp);
		// code_country.setVisible(!isIp);
		code_resident.setVisible(!isIp);
		//name_row.setVisible(!isIp);
		// 05-03-2018
		j_short_name.setVisible(!isIp);
		j_short_name_label.setVisible(!isIp);
	}

	protected void showIpView_add(boolean isIp) {
		aipView.setVisible(isIp);
		aipView2.setVisible(isIp);

		aipFio_cyr.setVisible(isIp);
		// aipFio.setVisible(isIp);
		aokpo_label.setVisible(!isIp);
		aj_okpo.setVisible(!isIp);
		// aonlyForJ1.setVisible(!bol);
		// aonlyForJ2.setVisible(!bol);
		// aonlyForJ3.setVisible(!bol);
		aonlyForJ4.setVisible(!isIp);

		aresLabel.setVisible(!isIp);
		acode_resident.setVisible(!isIp);
		//aname_row.setVisible(!isIp);
		// acode_countryLabel.setVisible(!isIp);
		// acode_country_value.setVisible(!isIp);
		// acode_country.setVisible(!isIp);
		aactivity_type_row.setVisible(isIp);
		//aname_row.setVisible(!isIp);
		aj_small_business.setChecked(isIp);
		btn_get_ip.setVisible(isIp);
		//row_OKED_new.setVisible(!isIp);
		aj_short_name.setVisible(!isIp);
		// 05-03-2018
		// Label для сокращенного наименования
		aj_short_name_label.setVisible(!isIp);
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

	public void onChange$p_post_address_street() {
		onChange$p_post_address_flat();
	}

	public void onChange$p_post_address_quarter() {
		onChange$p_post_address_flat();
	}

	public void onChange$p_post_address_house() {
		onChange$p_post_address_flat();
	}

	public void onChange$p_post_address_flat() {
		p_post_address.setValue(p_code_adr_region.getText() + " " + p_code_adr_distr.getText() + " "
				+ p_post_address_street.getText() + " " + p_post_address_quarter.getText() + " "
				+ p_post_address_house.getText() + " " + p_post_address_flat.getText());
		current.setP_post_address(p_code_adr_region.getText() + " " + p_code_adr_distr.getText() + " "
				+ p_post_address_street.getText() + " " + p_post_address_quarter.getText() + " "
				+ p_post_address_house.getText() + " " + p_post_address_flat.getText());
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
					dt = dt.plusYears(25).minusDays(1);
					p_passport_date_expiration.setValue(dt.toDate());
					current.setP_passport_date_expiration(dt.toDate());
				}
			}
			if (p_type_document.getValue().equals("6")) {
				if (p_passport_date_expiration.getValue() == null) {
					dt = new DateTime(current.getP_passport_date_registration());
					dt = dt.plusYears(10).minusDays(1);
					p_passport_date_expiration.setValue(dt.toDate());
					current.setP_passport_date_expiration(dt.toDate());
					alert(dt.toDate() + "");
				}
			}
		}
	}

	public void onChange$p_pass_place_region() {
		current.setP_pass_place_district(null);
		p_pass_place_district.setValue(null);
		p_pass_place_distr_text.setValue(null);
		// p_pass_place_district.setModel(
		// new ListModelList(ClientDictionaries.getDistrByRegion(null,
		// p_pass_place_region.getValue(), alias)));
		p_pass_place_district.setModel(
				new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(p_pass_place_region.getValue(), alias)));
		p_pass_place_region_text.setValue(p_pass_place_region.getValue());
		current.setP_pass_place_region(p_pass_place_region.getValue());
	}

	public void onChange$p_pass_place_district() {
		p_pass_place_distr_text.setValue(p_pass_place_district.getValue());
		String text = p_pass_place_region.getText() + " " + p_pass_place_district.getText() + " " + "ИИБ";
		p_passport_place_registration.setText(text);
		current.setP_pass_place_district(p_pass_place_district.getValue());
		current.setP_passport_place_registration(text);
	}

	public void onChange$p_pass_place_region_text() {
		current.setP_pass_place_district(null);
		p_pass_place_district.setValue(null);
		p_pass_place_distr_text.setValue(null);
		// p_pass_place_district.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// p_pass_place_region_text.getValue(), alias)));
		p_pass_place_district.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(p_pass_place_region_text.getValue(), alias)));
		p_pass_place_region.setSelecteditem(p_pass_place_region_text.getValue());
		current.setP_pass_place_region(p_pass_place_region_text.getValue());
	}

	public void onChange$p_pass_place_distr_text() {
		p_pass_place_district.setSelecteditem(p_pass_place_distr_text.getValue());
		String text = p_pass_place_region.getText() + " " + p_pass_place_district.getText() + " " + "ИИБ";
		p_passport_place_registration.setText(text);
		current.setP_passport_place_registration(text);
		current.setP_pass_place_district(p_pass_place_distr_text.getValue());
	}

	public void onChange$p_code_adr_region() {
		// p_code_adr_distr.setModel(
		// new ListModelList(ClientDictionaries.getDistrByRegion(null,
		// p_code_adr_region.getValue(), alias)));
		p_code_adr_distr_text.setValue(null);
		p_code_adr_distr.setValue(null);
		current.setP_code_adr_distr(null);

		p_code_adr_distr.setModel(
				new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(p_code_adr_region.getValue(), alias)));
		current.setP_code_adr_region(p_code_adr_region.getValue());
		p_code_adr_region_text.setValue(p_code_adr_region.getValue());

		// onChange$p_post_address_flat();
	}

	public void onChange$p_code_adr_region_text() {
		// p_code_adr_distr.setModel(
		// new ListModelList(ClientDictionaries.getDistrByRegion(null,
		// p_code_adr_region_text.getValue(), alias)));
		p_code_adr_distr_text.setValue(null);
		p_code_adr_distr.setValue(null);
		current.setP_code_adr_distr(null);

		p_code_adr_distr.setModel(
				new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(p_code_adr_region_text.getValue(), alias)));
		p_code_adr_region.setSelecteditem(p_code_adr_region_text.getValue());
		current.setP_code_adr_region(p_code_adr_region_text.getValue());
		// onChange$p_post_address_flat();
	}

	public void onChange$p_code_adr_distr() {
		String distrId = p_code_adr_distr.getValue();
		current.setP_code_adr_distr(distrId);
		p_code_adr_distr_text.setValue(distrId);

		String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		current.setP_code_tax_org(taxId);
		p_code_tax_org.setSelecteditem(taxId);
		p_code_tax_orgValue.setValue(taxId);
		// onChange$p_post_address_flat();
	}

	public void onChange$p_code_adr_distr_text() {
		String distrId = p_code_adr_distr_text.getValue();
		current.setP_code_adr_distr(distrId);
		p_code_adr_distr.setSelecteditem(distrId);

		String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		current.setP_code_tax_org(taxId);
		p_code_tax_org.setSelecteditem(taxId);
		p_code_tax_orgValue.setValue(taxId);
		// onChange$p_post_address_flat();
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
			acode_resident.setSelecteditem("1");
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
			acode_resident.setSelecteditem("1");
			newcl.setCode_resident("1");
			ap_passport_serial.setMaxlength(9);
			ap_passport_number.setMaxlength(9);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("3")) {

		}
		if (ap_type_document.getValue().equals("4")) {
			acode_resident.setSelecteditem("2");
			newcl.setCode_resident("2");
			ap_passport_serial.setMaxlength(9);
			ap_passport_number.setMaxlength(9);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("5")) {
			acode_resident.setSelecteditem("1");
			newcl.setCode_resident("1");
			ap_passport_serial.setMaxlength(9);
			ap_passport_number.setMaxlength(9);
			Events.postEvent(ap_passport_serial, new Event("onRender"));
			Events.postEvent(ap_passport_number, new Event("onRender"));
		}
		if (ap_type_document.getValue().equals("6")) {
			acode_resident.setSelecteditem("1");
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
		ap_pass_place_district.setValue(null);
		ap_pass_place_distr_text.setValue(null);
		newcl.setP_pass_place_district(null);
		// ap_pass_place_district.setModel(
		// new ListModelList(ClientDictionaries.getDistrByRegion(null,
		// ap_pass_place_region.getValue(), alias)));
		 ap_pass_place_district.setModel(
					new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(ap_pass_place_region.getValue(), alias)));
			ap_pass_place_region_text.setValue(ap_pass_place_region.getValue());
		onInitRenderLater$ap_pass_place_region();
		//newcl.setP_pass_place_region(ap_pass_place_region.getValue());
	}

	public void onInitRenderLater$ap_pass_place_region()
	  {
		/*
	    if (ap_pass_place_district.getModel().getSize()>1) {
	    	ap_pass_place_district.setSelectedIndex(-1);
	    }
	   */
	  if (ap_pass_place_region.getValue()!=null)
	  {
		  ap_pass_place_region_text.setValue(ap_pass_place_region.getValue());
	  }
	  System.out.println("asdasdads 111111");
	  }

	public void onChange$ap_pass_place_region_text() {
		ap_pass_place_district.setValue(null);
		ap_pass_place_distr_text.setValue(null);
		newcl.setP_pass_place_district(null);
		// ap_pass_place_district.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// ap_pass_place_region_text.getValue(), alias)));
		ap_pass_place_district.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(ap_pass_place_region_text.getValue(), alias)));
		ap_pass_place_region.setSelecteditem(ap_pass_place_region_text.getValue());
		//newcl.setP_pass_place_region(ap_pass_place_region_text.getValue());
	}

	public void onChange$ap_pass_place_district() {
		newcl.setP_pass_place_district(ap_pass_place_district.getValue());
		ap_pass_place_distr_text.setValue(ap_pass_place_district.getValue());
		String text = ap_pass_place_region.getText() + " " + ap_pass_place_district.getText() + " " + "ИИБ";
		newcl.setP_passport_place_registration(text);
		ap_passport_place_registration.setText(text);
		newcl.setP_code_tax_org(DbUtils.getGniByDistrCode(ap_pass_place_district.getValue()));
	}

	public void onChange$ap_pass_place_distr_text() {
		newcl.setP_pass_place_district(ap_pass_place_distr_text.getValue());
		ap_pass_place_district.setSelecteditem(ap_pass_place_distr_text.getValue());
		String text = ap_pass_place_region.getText() + " " + ap_pass_place_district.getText() + " " + "ИИБ";
		newcl.setP_passport_place_registration(text);
		ap_passport_place_registration.setText(text);
		newcl.setP_code_tax_org(DbUtils.getGniByDistrCode(newcl.getP_pass_place_district()));
	}

	public void onChange$ap_code_adr_region() {
		ap_code_adr_distr.setValue(null);
		ap_code_adr_distr_text.setValue(null);
		newcl.setP_code_adr_distr(null);
		// ap_code_adr_distr.setModel(
		// new ListModelList(ClientDictionaries.getDistrByRegion(null,
		// ap_code_adr_region.getValue(), alias)));
		ap_code_adr_distr.setModel(
				new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(ap_code_adr_region.getValue(), alias)));
		ap_code_adr_region_text.setValue(ap_code_adr_region.getValue());
		newcl.setP_code_adr_region(ap_code_adr_region.getValue());
		// onChange$ap_post_address_flat();
	}

	public void onChange$ap_code_adr_region_text() {
		ap_code_adr_distr.setValue(null);
		ap_code_adr_distr_text.setValue(null);
		newcl.setP_code_adr_distr(null);
		// ap_code_adr_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// ap_code_adr_region_text.getValue(), alias)));
		ap_code_adr_distr.setModel(
				new ListModelList(DictionaryKeeper.getDistrByRegionFromMap(ap_code_adr_region_text.getValue(), alias)));
		ap_code_adr_region.setSelecteditem(ap_code_adr_region_text.getValue());
		newcl.setP_code_adr_region(ap_code_adr_region_text.getValue());
		// onChange$ap_post_address_flat();
	}

	public void onChange$ap_code_adr_distr() {
		String distrId = ap_code_adr_distr.getValue();
		newcl.setP_code_adr_distr(distrId);
		ap_code_adr_distr_text.setValue(distrId);

		String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		newcl.setP_code_tax_org(taxId);
		ap_code_tax_org.setSelecteditem(taxId);
		ap_code_tax_orgValue.setValue(taxId);
		// onChange$ap_post_address_flat();
	}

	public void onChange$ap_code_adr_distr_text() {
		String distrId = ap_code_adr_distr_text.getValue();
		newcl.setP_code_adr_distr(distrId);
		ap_code_adr_distr.setSelecteditem(distrId);

		String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		newcl.setP_code_tax_org(taxId);
		ap_code_tax_org.setSelecteditem(taxId);
		ap_code_tax_orgValue.setValue(taxId);
		// onChange$ap_post_address_flat();
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
	private Textbox p_last_name_cyr, p_first_name_cyr, p_patronymic_cyr;

	public void onChange$p_last_name_cyr() {
		current.setP_last_name_cyr(p_last_name_cyr.getValue());
		// 05-03-2018
		names(current);
	}

	public void onChange$p_first_name_cyr() {
		current.setP_first_name_cyr(p_first_name_cyr.getValue());
		// 05-03-2018
		names(current);
	}

	public void onChange$p_patronymic_cyr() {
		current.setP_patronymic_cyr(p_patronymic_cyr.getValue());
		// 05-03-2018
		names(current);
	}

	public void onBlur$p_patronymic_cyr() {
		Events.sendEvent(new Event(Events.ON_CHANGE, p_patronymic_cyr));
	}

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

	private void names(ClientJ client) {
		// Конкатенировать только в том случае когда наименование пустое
		// 05-03-2018
		if ((addgrd.isVisible() && StringUtils.isBlank(aname.getValue()))
				|| (!addgrd.isVisible()) && StringUtils.isBlank(name.getValue())) {

			client.setP_family(ClientUtil.transliterate(client.getP_last_name_cyr()).toUpperCase());
			client.setP_first_name(ClientUtil.transliterate(client.getP_first_name_cyr()).toUpperCase());
			client.setP_patronymic(ClientUtil.transliterate(client.getP_patronymic_cyr()).toUpperCase());

			StringBuilder sb = new StringBuilder();

			if (( client.getCode_type().equals(ClientUtil.CODE_TYPE_IP) || client.getCode_type().equals(ClientUtil.CODE_TYPE_SE)) &&
					!StringUtils.isBlank(client.getJ_opf()) && client.getJ_opf().equals("430"))
				sb.append("YATOT '");
			else
				sb.append("YATT '");


			sb.append(client.getP_family()).append(" ").append(client.getP_first_name()).append(" ")
					.append(client.getP_patronymic()).append("'");
			int len = sb.length();

			client.setJ_short_name(sb.toString().substring(0, len < 25 ? len : 25));

			// if (StringUtils.isEmpty(client.getP_first_name_cyr()))
			client.setName(sb.toString());
		}
		binder.loadAll();
	}

	public void onChange$adirector_type_document_text() {
		newcl.getDirector().setType_document(adirector_type_document_text.getValue());
		adirector_type_document.setSelecteditem(adirector_type_document_text.getValue());
		change_adirector_type_document();
	}

	public void onChange$adirector_type_document() {
		adirector_type_document_text.setValue(adirector_type_document.getValue());
		newcl.getDirector().setType_document(adirector_type_document.getValue());
		change_adirector_type_document();
	}

	public void change_adirector_type_document() {
		if (adirector_type_document.getValue().equals("1")) {
			adirector_code_resident.setSelecteditem("1");
			adirector_code_citizenship_text.setValue("860");
			adirector_code_citizenship.setSelecteditem("860");
			newcl.getDirector().setCode_resident("1");
			newcl.getDirector().setCode_citizenship("860");
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
			adirector_passport_serial.setMaxlength(2);
			adirector_passport_number.setMaxlength(7);
		}
		if (adirector_type_document.getValue().equals("2")) {
			adirector_code_resident.setSelecteditem("1");
			newcl.getDirector().setCode_resident("1");
			adirector_passport_serial.setMaxlength(9);
			adirector_passport_number.setMaxlength(9);
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
		}
		if (adirector_type_document.getValue().equals("3")) {
			newcl.getDirector().setCode_citizenship(null);
			adirector_code_citizenship_text.setValue(null);
			adirector_code_citizenship.setSelecteditem(null);
		}
		if (adirector_type_document.getValue().equals("4")) {
			adirector_code_resident.setSelecteditem("2");
			adirector_passport_number.setMaxlength(12);
			newcl.getDirector().setCode_resident("2");
			newcl.getDirector().setCode_citizenship(null);
			adirector_code_citizenship_text.setValue(null);
			adirector_code_citizenship.setSelecteditem(null);
			adirector_passport_serial.setMaxlength(9);
			adirector_passport_number.setMaxlength(9);
			Events.postEvent(adirector_passport_serial, new Event("onInitRender"));
			Events.postEvent(adirector_passport_number, new Event("onInitRender"));
		}
		if (adirector_type_document.getValue().equals("5")) {
			adirector_code_resident.setSelecteditem("1");
			newcl.getDirector().setCode_resident("1");
			newcl.getDirector().setCode_citizenship(null);
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
			newcl.getDirector().setCode_resident("1");
			newcl.getDirector().setCode_citizenship("860");
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
			newcl.getDirector().setPass_place_region(null);
			newcl.getDirector().setPass_place_district(null);
		}
	}

	public void onChange$adirector_code_citizenship_text() {
		newcl.getDirector().setCode_citizenship(adirector_code_citizenship_text.getValue());
		// newcl.getDirector().setCode_resident(newcl.getDirector().getCode_citizenship().equals("860")
		// ? "1" : "2");
		adirector_code_citizenship.setSelecteditem(newcl.getDirector().getCode_citizenship());
	}

	public void onChange$adirector_code_citizenship() {
		String country = adirector_code_citizenship.getValue();
		newcl.getDirector().setCode_citizenship(country);
		// newcl.getDirector().setCode_resident(country != null ?
		// country.equals("860") ? "1" : "2" : null);
		// adirector_code_resident.setSelecteditem(newcl.getDirector().getCode_resident());
		adirector_code_citizenship_text.setValue(newcl.getDirector().getCode_citizenship());
	}

	public void onChange$adirector_pass_place_region() {
		// adirector_pass_place_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// adirector_pass_place_region.getValue(), alias)));
		adirector_pass_place_distr.setValue(null);
		adirector_pass_place_distr_text.setValue(null);
		newcl.getDirector().setPass_place_district(null);

		adirector_pass_place_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(adirector_pass_place_region.getValue(), alias)));
		adirector_pass_place_region_text.setValue(adirector_pass_place_region.getValue());
		newcl.getDirector().setPass_place_region(adirector_pass_place_region.getValue());
	}

	public void onChange$adirector_pass_place_region_text() {
		// adirector_pass_place_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// adirector_pass_place_region_text.getValue(), alias)));
		adirector_pass_place_distr.setValue(null);
		adirector_pass_place_distr_text.setValue(null);
		newcl.getDirector().setPass_place_district(null);

		adirector_pass_place_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(adirector_pass_place_region_text.getValue(), alias)));
		adirector_pass_place_region.setSelecteditem(adirector_pass_place_region_text.getValue());
		newcl.getDirector().setPass_place_region(adirector_pass_place_region.getValue());
	}

	public void onChange$adirector_pass_place_distr() {
		adirector_pass_place_distr_text.setValue(adirector_pass_place_distr.getValue());
		adirector_passport_place_registration.setText(
				adirector_pass_place_region.getText() + " " + adirector_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getDirector().setPassport_place_registration(
				adirector_pass_place_region.getText() + " " + adirector_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getDirector().setPass_place_district(adirector_pass_place_distr.getValue());
		// newcl.getDirector().setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getDirector().getPass_place_district()));
	}

	public void onChange$adirector_pass_place_distr_text() {
		adirector_pass_place_distr.setSelecteditem(adirector_pass_place_distr_text.getValue());
		adirector_passport_place_registration.setText(
				adirector_pass_place_region.getText() + " " + adirector_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getDirector().setPassport_place_registration(
				adirector_pass_place_region.getText() + " " + adirector_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getDirector().setPass_place_district(adirector_pass_place_distr.getValue());
		// newcl.getDirector().setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getDirector().getPass_place_district()));
	}

	public void onChange$adirector_code_adr_region() {
		// adirector_code_adr_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// adirector_code_adr_region.getValue(), alias)));
		adirector_code_adr_distr.setValue(null);
		adirector_code_adr_distr_text.setValue(null);
		newcl.getDirector().setCode_adr_distr(null);

		adirector_code_adr_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(adirector_code_adr_region.getValue(), alias)));
		adirector_code_adr_region_text.setValue(adirector_code_adr_region.getValue());
		newcl.getDirector().setCode_adr_region(adirector_code_adr_region.getValue());
	}

	public void onChange$adirector_code_adr_region_text() {
		// adirector_code_adr_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// adirector_code_adr_region_text.getValue(), alias)));
		adirector_code_adr_distr.setValue(null);
		adirector_code_adr_distr_text.setValue(null);
		newcl.getDirector().setCode_adr_distr(null);

		adirector_code_adr_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(adirector_code_adr_region_text.getValue(), alias)));
		adirector_code_adr_region.setSelecteditem(adirector_code_adr_region_text.getValue());
		newcl.getDirector().setCode_adr_region(adirector_code_adr_region_text.getValue());
	}

	public void onChange$adirector_code_adr_distr() {
		String distrId = adirector_code_adr_distr.getValue();
		newcl.getDirector().setCode_adr_distr(distrId);
		adirector_code_adr_distr_text.setValue(distrId);
		newcl.getDirector().setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getDirector().getCode_adr_distr()));
	}

	public void onChange$adirector_code_adr_distr_text() {
		String distrId = adirector_code_adr_distr_text.getValue();
		newcl.getDirector().setCode_adr_distr(distrId);
		adirector_code_adr_distr.setSelecteditem(distrId);
		newcl.getDirector().setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getDirector().getCode_adr_distr()));
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
					newcl.getDirector().setPassport_date_expiration(dt.toDate());
				}
			}
			if (adirector_type_document.getValue().equals("6")) {
				if (adirector_passport_date_registration.getValue() != null) {
					dt = new DateTime(adirector_passport_date_registration.getValue());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					adirector_passport_date_expiration.setValue(dt.toDate());
					newcl.getDirector().setPassport_date_expiration(dt.toDate());
				}
			}
		}
	}

	public void onChange$aaccountant_type_document_text() {
		newcl.getAccountant().setType_document(aaccountant_type_document_text.getValue());
		aaccountant_type_document.setSelecteditem(aaccountant_type_document_text.getValue());
		// onChange$aaccountant_type_document();
		change_aaccountant_type_document();
	}

	public void onChange$aaccountant_type_document() {
		newcl.getAccountant().setType_document(aaccountant_type_document.getValue());
		aaccountant_type_document_text.setValue(aaccountant_type_document.getValue());
		change_aaccountant_type_document();
	}

	public void change_aaccountant_type_document() {
		if (aaccountant_type_document.getValue().equals("1")) {
			aaccountant_code_resident.setSelecteditem("1");
			aaccountant_code_citizenship_text.setValue("860");
			aaccountant_code_citizenship.setSelecteditem("860");
			newcl.getAccountant().setCode_resident("1");
			newcl.getAccountant().setCode_citizenship("860");
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
			aaccountant_passport_serial.setMaxlength(2);
			aaccountant_passport_number.setMaxlength(7);
		}
		if (aaccountant_type_document.getValue().equals("2")) {
			aaccountant_code_resident.setSelecteditem("1");
			newcl.getAccountant().setCode_resident("1");
			aaccountant_passport_serial.setMaxlength(9);
			aaccountant_passport_number.setMaxlength(9);
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
		}
		if (aaccountant_type_document.getValue().equals("3")) {
			newcl.getAccountant().setCode_citizenship(null);
			aaccountant_code_citizenship_text.setValue(null);
			aaccountant_code_citizenship.setSelecteditem(null);
		}
		if (aaccountant_type_document.getValue().equals("4")) {
			aaccountant_code_resident.setSelecteditem("2");
			aaccountant_passport_number.setMaxlength(12);
			newcl.getAccountant().setCode_resident("2");
			newcl.getAccountant().setCode_citizenship(null);
			aaccountant_code_citizenship_text.setValue(null);
			aaccountant_code_citizenship.setSelecteditem(null);
			aaccountant_passport_serial.setMaxlength(9);
			aaccountant_passport_number.setMaxlength(9);
			Events.postEvent(aaccountant_passport_serial, new Event("onInitRender"));
			Events.postEvent(aaccountant_passport_number, new Event("onInitRender"));
		}
		if (aaccountant_type_document.getValue().equals("5")) {
			aaccountant_code_resident.setSelecteditem("1");
			newcl.getAccountant().setCode_resident("1");
			newcl.getAccountant().setCode_citizenship(null);
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
			newcl.getAccountant().setCode_resident("1");
			newcl.getAccountant().setCode_citizenship("860");
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
			newcl.getAccountant().setPass_place_region(null);
			newcl.getAccountant().setPass_place_district(null);
		}
	}

	public void onChange$aaccountant_code_citizenship_text() {
		newcl.getAccountant().setCode_citizenship(aaccountant_code_citizenship_text.getValue());
		// newcl.getAccountant().setCode_resident(newcl.getAccountant().getCode_citizenship().equals("860")
		// ? "1" : "2");
		aaccountant_code_citizenship.setSelecteditem(newcl.getAccountant().getCode_citizenship());
	}

	public void onChange$aaccountant_code_citizenship() {
		newcl.getAccountant().setCode_citizenship(aaccountant_code_citizenship.getValue());
		// newcl.getAccountant().setCode_resident(newcl.getAccountant().getCode_citizenship().equals("860")
		// ? "1" : "2");
		// aaccountant_code_resident.setSelecteditem(newcl.getAccountant().getCode_resident());
		aaccountant_code_citizenship_text.setValue(newcl.getAccountant().getCode_citizenship());
	}

	public void onChange$aaccountant_pass_place_region() {
		// aaccountant_pass_place_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// aaccountant_pass_place_region.getValue(), alias)));
		aaccountant_pass_place_distr.setValue(null);
		aaccountant_pass_place_distr_text.setValue(null);
		newcl.getAccountant().setPass_place_district(null);

		aaccountant_pass_place_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(aaccountant_pass_place_region.getValue(), alias)));
		aaccountant_pass_place_region_text.setValue(aaccountant_pass_place_region.getValue());
		newcl.getAccountant().setPass_place_region(aaccountant_pass_place_region.getValue());
	}

	public void onChange$aaccountant_pass_place_region_text() {
		// aaccountant_pass_place_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// aaccountant_pass_place_region_text.getValue(), alias)));
		aaccountant_pass_place_distr.setValue(null);
		aaccountant_pass_place_distr_text.setValue(null);
		newcl.getAccountant().setPass_place_district(null);

		aaccountant_pass_place_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(aaccountant_pass_place_region_text.getValue(), alias)));
		aaccountant_pass_place_region.setSelecteditem(aaccountant_pass_place_region_text.getValue());
		newcl.getAccountant().setPass_place_region(aaccountant_pass_place_region.getValue());
	}

	public void onChange$aaccountant_pass_place_distr() {
		aaccountant_pass_place_distr_text.setValue(aaccountant_pass_place_distr.getValue());
		aaccountant_passport_place_registration.setText(
				aaccountant_pass_place_region.getText() + " " + aaccountant_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getAccountant().setPassport_place_registration(
				aaccountant_pass_place_region.getText() + " " + aaccountant_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getAccountant().setPass_place_district(aaccountant_pass_place_distr.getValue());
		newcl.getAccountant()
				.setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getAccountant().getPass_place_district()));
	}

	public void onChange$aaccountant_pass_place_distr_text() {
		aaccountant_pass_place_distr.setSelecteditem(aaccountant_pass_place_distr_text.getValue());
		aaccountant_passport_place_registration.setText(
				aaccountant_pass_place_region.getText() + " " + aaccountant_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getAccountant().setPassport_place_registration(
				aaccountant_pass_place_region.getText() + " " + aaccountant_pass_place_distr.getText() + " " + "ИИБ");
		newcl.getAccountant().setPass_place_district(aaccountant_pass_place_distr.getValue());
		newcl.getAccountant()
				.setCode_tax_org(DbUtils.getGniByDistrCode(newcl.getAccountant().getPass_place_district()));

	}

	public void onChange$aaccountant_code_adr_region() {
		// aaccountant_code_adr_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// aaccountant_code_adr_region.getValue(), alias)));
		aaccountant_code_adr_distr.setValue(null);
		aaccountant_code_adr_distr_text.setValue(null);
		newcl.getAccountant().setCode_adr_distr(null);

		aaccountant_code_adr_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(aaccountant_code_adr_region.getValue(), alias)));
		newcl.getAccountant().setCode_adr_region(aaccountant_code_adr_region.getValue());
		aaccountant_code_adr_region_text.setValue(aaccountant_code_adr_region.getValue());

		// binder.loadAll();
	}

	public void onChange$aaccountant_code_adr_region_text() {
		// aaccountant_code_adr_distr.setModel(new ListModelList(
		// ClientDictionaries.getDistrByRegion(null,
		// aaccountant_code_adr_region_text.getValue(), alias)));

		aaccountant_code_adr_distr.setValue(null);
		aaccountant_code_adr_distr_text.setValue(null);
		newcl.getAccountant().setCode_adr_distr(null);

		aaccountant_code_adr_distr.setModel(new ListModelList(
				DictionaryKeeper.getDistrByRegionFromMap(aaccountant_code_adr_region_text.getValue(), alias)));
		aaccountant_code_adr_region.setSelecteditem(aaccountant_code_adr_region_text.getValue());
		newcl.getAccountant().setCode_adr_region(aaccountant_code_adr_region_text.getValue());
	}

	public void onChange$aaccountant_code_adr_distr() {
		String distrId = aaccountant_code_adr_distr.getValue();
		newcl.getAccountant().setCode_adr_distr(distrId);
		aaccountant_code_adr_distr_text.setValue(distrId);
	}

	public void onChange$aaccountant_code_adr_distr_text() {
		String distrId = aaccountant_code_adr_distr_text.getValue();
		newcl.getAccountant().setCode_adr_distr(distrId);
		aaccountant_code_adr_distr.setSelecteditem(distrId);
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
					newcl.getAccountant().setPassport_date_expiration(dt.toDate());
				}
			}
			if (aaccountant_type_document.getValue().equals("6")) {
				if (aaccountant_passport_date_registration.getValue() != null) {
					dt = new DateTime(aaccountant_passport_date_registration.getValue());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					aaccountant_passport_date_expiration.setValue(dt.toDate());
					newcl.getAccountant().setPassport_date_expiration(dt.toDate());
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

		ClientJ fetchedClientFromSap = (ClientJ) fields_diff_wnd$diff_grid.getAttribute(ClientUtil.SAP_CLIENT_ATTR);
		ClientJ fetchedClientFromEbp = (ClientJ) fields_diff_wnd$diff_grid.getAttribute(ClientUtil.EBP_CLIENT_ATTR);
		if (!addgrd.isVisible()) {
			ClientUtil.setSapOrEbpData(current, fetchedClientFromSap, fetchedClientFromEbp, sapCheckedFields,
					ebpCheckedFields);
		} else {
			ClientUtil.setSapOrEbpData(newcl, fetchedClientFromSap, fetchedClientFromEbp, sapCheckedFields,
					ebpCheckedFields);
		}
		binder.loadAll();
		fields_diff_wnd.setVisible(false);
		// sendEventToChildren(addgrd,Events.ON_CHANGE);
	}

	private void hideTabs(boolean bol) {
		acc_tab.setVisible(bol);
		cl_add_tab.setVisible(bol);
		persons_tab.setVisible(bol);
		attach_tab.setVisible(bol);
		specclt_tab.setVisible(bol);
		history_tab.setVisible(bol);
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

	@Override
	public void show(List<Customer> customer) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void show(Customer customer) throws Exception {
		Customer current = new Customer();
		BusinessPartnerMappingInterface service = SAPServiceFactory.getInstance().getMappingService();
		//BusinessPartnerInterface businessPartnerService = SAPServiceFactory.getInstance().getBusinessPartnerService();
		List<Customer> mappings = service.getMapping(null, null, customer.getIdSap());
		//customer = businessPartnerService.get(null, null, customer.getIdSap());
		customer.setBranch(ses_branch);
		customer.setId(null);
		for (Customer mapping : mappings) {
			if (MappingResolver.isContactPerson(mapping.getBranch(), mapping.getId(), ses_branch)) {
				String contactId = mapping.getId().replace("A","");
				if (MappingResolver.isContactPersonExists(mapping.getBranch(), contactId,
						ses_branch)) {
					//customer = businessPartnerService.get(null,null,customer.getIdSap());
					//customer.setBranch(ses_branch);
					customer.setId(contactId);
					break;
				}
			}
		}
		Person person = Mappers.mapBpToPerson(customer);
		showContactPerson(person);
	}

	@Override
	public void create(Customer customer) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Customer getCurrentCustomer() throws Exception {
		throw new UnsupportedOperationException();
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

	public void onClick$btn_clear_dir_n() {
		Person person = new Person();
		person.setBranch(ses_branch);
		newcl.setDirector(person);
	}

	public void onClick$btn_clear_() {
		Person person = new Person();
		person.setBranch(ses_branch);
		newcl.setAccountant(person);
	}

	public void onCheck$chbLocalSearch() {
		// hideAll();
		// chooseSearch.setVisible(true);
		sap.setVisible(!chbLocalSearch.isChecked());

		if (chbLocalSearch.isChecked()) {
			filterdiv.setVisible(true);
			btn_save.setLabel(Labels.getLabel("search"));
			btn_save.setImage("/images/search.png");
			btn_save.setAttribute("action", "filter");
			fid_client.setFocus(true);
			// fjView.setOpen(false);
			// fipView.setOpen(false);
			// fipView2.setOpen(false);
			// facc_grb.setOpen(false);
			// fother_grb.setOpen(false);
			// fcontacs_grb.setOpen(false);
			tb.setVisible(true);
			btn_clear.setVisible(true);
		} else {
			filterdiv.setVisible(false);
			tb.setVisible(false);
		}
	}

	public void onChange$code_country_value() {
		code_country.setSelecteditem(code_country_value.getValue());
		current.setCode_country(code_country_value.getValue());
	}

	public void onChange$code_country() {
		code_country_value.setValue(code_country.getValue());
		current.setCode_country(code_country.getValue());
	}

	public void onChange$addressCountryText() {
		addressCountry.setSelecteditem(addressCountryText.getValue());
		current.setAddressCountry(addressCountryText.getValue());
	}

	public void onChange$addressCountry() {
		addressCountryText.setValue(addressCountry.getValue());
		current.setAddressCountry(addressCountry.getValue());
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

	public void onChange$file_name() {
		file_nameValue.setValue(file_name.getValue());
		current.setFile_name(file_name.getValue());
	}

	public void onChange$subbranch() {
		subbranchValue.setValue(subbranch.getValue());
		current.setSubbranch(subbranch.getValue());
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

	public void onChange$p_code_gender() {
		current.setP_code_gender(p_code_gender.getValue());
	}

	public void onChange$code_country1() {
		countryValue1.setValue(code_country1.getValue());
		current.setPost_address_country(code_country1.getValue());
	}

	public void onChange$p_code_tax_org() {
		p_code_tax_orgValue.setValue(p_code_tax_org.getValue());
		current.setP_code_tax_org(p_code_tax_org.getValue());
	}

	public void onChange$i_opf() {
		iopfValue.setValue(i_opf.getValue());
		current.setI_opf(i_opf.getValue());
	}

	public void onChange$i_form() {
		iformValue.setValue(i_form.getValue());
		current.setI_form(i_form.getValue());
	}

	public void onChange$i_sector() {
		isectorValue.setValue(i_sector.getValue());
		current.setI_sector(i_sector.getValue());
	}

	public void onChange$i_organ_direct() {
		iorgan_directValue.setValue(i_organ_direct.getValue());
		current.setI_organ_direct(i_organ_direct.getValue());
	}

	public void onChange$swift_id() {
		swift_idValue.setValue(swift_id.getValue());
		current.setSwift_id(swift_id.getValue());
	}

	public void onChange$director_code_nation() {
		current.getDirector().setCode_nation(director_code_nation.getValue());
	}

	public void onChange$director_code_resident() {
		current.getDirector().setCode_resident(director_code_resident.getValue());
	}

	public void onChange$director_code_gender() {
		current.getDirector().setCode_gender(director_code_gender.getValue());
	}

	public void onChange$director_code_country() {
		current.getDirector().setCode_country(director_code_country.getValue());
	}

	public void onChange$director_code_tax_org() {
		current.getDirector().setCode_tax_org(director_code_tax_org.getValue());
	}

	public void onChange$fstate() {
		filter.setState(fstate.getValue());
	}

	public void onChange$fcode_type() {
		ftypeValue.setValue(fcode_type.getValue());
		filter.setCode_type(fcode_type.getValue());
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

	public void onChange$aaddressCountry() {
		aaddressCountryText.setValue(aaddressCountry.getValue());
		newcl.setAddressCountry(aaddressCountry.getValue());
	}

	public void onChange$aj_code_organ_direct() {
		aorgan_directValue.setValue(aj_code_organ_direct.getValue());
		newcl.setJ_code_organ_direct(aj_code_organ_direct.getValue());
	}

	public void onChange$afile_name() {
		afile_nameValue.setValue(afile_name.getValue());
		newcl.setFile_name(afile_name.getValue());
	}

	public void onChange$asubbranch() {
		asubbranchValue.setValue(asubbranch.getValue());
		newcl.setSubbranch(asubbranch.getValue());
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
		acountryValue1.setValue(acode_country1.getValue());
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

	public void onChange$accountant_type_document() {
		accountant_type_document_text.setValue(accountant_type_document.getValue());
		current.getAccountant().setType_document(accountant_type_document.getValue());
	}

	public void onChange$accountant_code_nation() {
		current.getAccountant().setCode_nation(accountant_code_nation.getValue());
	}

	public void onChange$accountant_code_resident() {
		current.getAccountant().setCode_resident(accountant_code_resident.getValue());
	}

	public void onChange$accountant_code_gender() {
		current.getAccountant().setCode_gender(accountant_code_gender.getValue());
	}

	public void onChange$accountant_code_country() {
		current.getAccountant().setCode_country(accountant_code_country.getValue());
	}

	public void onChange$accountant_code_tax_org() {
		accountant_code_tax_org_text.setValue(accountant_code_tax_org.getValue());
		current.getAccountant().setCode_tax_org(accountant_code_tax_org.getValue());
	}

	public void onChange$ap_code_tax_org() {
		ap_code_tax_orgValue.setValue(ap_code_tax_org.getValue());
		newcl.setP_code_tax_org(ap_code_tax_org.getValue());
	}

	public void onChange$aj_code_bank() {
		abankValue.setValue(aj_code_bank.getValue());
		newcl.setJ_code_bank(aj_code_bank.getValue());
	}

	public void onChange$ai_opf() {
		aiopfValue.setValue(ai_opf.getValue());
		newcl.setI_opf(ai_opf.getValue());
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
		newcl.getDirector().setCode_nation(adirector_code_nation.getValue());
	}

	public void onChange$adirector_code_resident() {
		newcl.getDirector().setCode_resident(adirector_code_resident.getValue());
	}

	public void onChange$adirector_code_gender() {
		newcl.getDirector().setCode_gender(adirector_code_gender.getValue());
	}

	public void onChange$adirector_code_country() {
		adirector_code_country_text.setValue(adirector_code_country.getValue());
		newcl.getDirector().setCode_country(adirector_code_country.getValue());
	}

	public void onChange$adirector_code_tax_org() {
		adirector_code_tax_org_text.setValue(adirector_code_tax_org.getValue());
		newcl.getDirector().setCode_tax_org(adirector_code_tax_org.getValue());
	}

	public void onChange$aaccountant_code_nation() {
		newcl.getAccountant().setCode_nation(aaccountant_code_nation.getValue());
	}

	public void onChange$aaccountant_code_resident() {
		newcl.getAccountant().setCode_resident(aaccountant_code_resident.getValue());
	}

	public void onChange$aaccountant_code_gender() {
		newcl.getAccountant().setCode_gender(aaccountant_code_gender.getValue());
	}

	public void onChange$aaccountant_code_country() {
		aaccountant_code_country_text.setValue(aaccountant_code_country.getValue());
		newcl.getAccountant().setCode_country(aaccountant_code_country.getValue());
	}

	public void onChange$aaccountant_code_tax_org() {
		aaccountant_code_tax_org_text.setValue(aaccountant_code_tax_org.getValue());
		newcl.getAccountant().setCode_tax_org(aaccountant_code_tax_org.getValue());
	}

	public void onChange$type_close_id$wind_nibbd() {
		wind_nibbd$type_close_name.setSelecteditem(wind_nibbd$type_close_id.getValue());
		nibbdparam.setClose_type(wind_nibbd$type_close_id.getValue());
	}
	
	public void onChange$type_close_name$wind_nibbd() {
		wind_nibbd$type_close_id.setValue(wind_nibbd$type_close_name.getValue());
		nibbdparam.setClose_type(wind_nibbd$type_close_name.getValue());
	}


	
	public void onClick$btn_word() {
		AMedia repmd = null;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(application.getRealPath("/reports/clients_word_j.doc"));
			POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
			HWPFDocument doc = new HWPFDocument(fs);
			c = ConnectionPool.getConnection(un, pw, alias);
			ps = c.prepareStatement("select * from v_client_sap " + " where id_client=?");
			ps.setString(1, current.getId_client());
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			if (rs.next()) {
				Range range = doc.getRange();
				// range.replaceText("@BRANCH@", rs.getString("branch"));
				// range.replaceText("@ID_CLIENT@", rs.getString("id_client"));
				// range.replaceText("@NAME@", rs.getString("name"));
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					//System.out.println(
					//		rsmd.getColumnName(i) + " - " + rsmd.getColumnType(i) + " - " + rsmd.getColumnTypeName(i));
					if (!CheckNull.isEmpty(rs.getString(rsmd.getColumnName(i))))
						range.replaceText("@" + rsmd.getColumnName(i) + "@", rs.getString(rsmd.getColumnName(i)));
					else
						range.replaceText("@" + rsmd.getColumnName(i) + "@", "");
				}

				comboboxList = new ArrayList<RefData>();
				// создает список комбобоксов с значениями их
				iterate(frm);
				for (int i = 0; i <= comboboxList.size() - 1; i++) {
					if (!CheckNull.isEmpty(comboboxList.get(i).getLabel()))
						range.replaceText("@NAME_" + comboboxList.get(i).getData() + "@",
								comboboxList.get(i).getLabel());
					else
						range.replaceText("@NAME_" + comboboxList.get(i).getData() + "@", "");
				}
			}

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			doc.write(out);
			out.close();
			fs.close();
			byte[] arr = out.toByteArray();
			repmd = new AMedia("p" + current.getId_client() + ".doc", "doc", "application/msword", arr);
			iframe.setContent(repmd);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// MyLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			GeneralUtils.closeStatement(ps);
			GeneralUtils.closeResultSet(rs);
			IOUtils.closeQuietly(fileInputStream);
			ConnectionPool.close(c);
		}
	}

	public void iterate(Component comp) {
		/*
		 * do something for the component
		 */
		if (comp instanceof Combobox) {
			String myStr = ((Combobox) comp).getAnnotation("selecteditem", "default").toString();
			// myStr =[default: {value=clientmain$composer.current.j_opf}]
			// myStr =[default: {value=clientmain$composer.current.accountant.pass_place_region}]
			String beginStr = "[default: {value=clientmain$composer.current.";
			int len_beginStr = beginStr.length();

			int k = myStr.indexOf(beginStr);
			if ((k >= 0) && (myStr.indexOf(".", k + len_beginStr) == -1)) {
				String myFieldName = myStr.substring(k + len_beginStr, myStr.length() - 2);
				comboboxList.add(new RefData(myFieldName.toUpperCase(), ((Combobox) comp).getText()));
			}
		}

		List<Component> list = comp.getChildren();
		for (Component child : list) {
			iterate(child);
		}
	}

}
