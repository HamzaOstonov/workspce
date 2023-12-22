package com.is.client_p;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

@SuppressWarnings("serial")

public class Client_pViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Listbox dataGrid;
	private Paging contactPaging;
	private Window client_pmain;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, btn_001;
	private Toolbar tb, tb_actions;
	private Textbox branch, id_client, name, code_country, code_type, code_resident, code_subject, sign_registr,
			code_form, date_open, date_close, state, kod_err, file_name, p_birthday, p_post_address, p_passport_type,
			p_passport_serial, p_passport_number, p_passport_place_registration, p_passport_date_registration,
			p_code_tax_org, p_number_tax_registration, p_code_bank, p_code_class_credit, p_code_citizenship,
			p_birth_place, p_code_capacity, p_capacity_status_date, p_capacity_status_place, p_num_certif_capacity,
			p_phone_home, p_phone_mobile, p_email_address, p_pension_sertif_serial, p_code_gender, p_code_nation,
			p_code_birth_region, p_code_birth_distr, p_type_document, p_passport_date_expiration, p_code_adr_region,
			p_code_adr_distr, p_inps, p_family, p_first_name, p_patronymic, p_sign_vip;
	private Textbox abranch, aid_client, aname, acode_country, acode_type, acode_resident, acode_subject, asign_registr,
			acode_form, adate_open, adate_close, astate, akod_err, afile_name, ap_birthday, ap_post_address,
			ap_passport_type, ap_passport_serial, ap_passport_number, ap_passport_place_registration,
			ap_passport_date_registration, ap_code_tax_org, ap_number_tax_registration, ap_code_bank,
			ap_code_class_credit, ap_code_citizenship, ap_birth_place, ap_code_capacity, ap_capacity_status_date,
			ap_capacity_status_place, ap_num_certif_capacity, ap_phone_home, ap_phone_mobile, ap_email_address,
			ap_pension_sertif_serial, ap_code_gender, ap_code_nation, ap_code_birth_region, ap_code_birth_distr,
			ap_type_document, ap_passport_date_expiration, ap_code_adr_region, ap_code_adr_distr, ap_inps, ap_family,
			ap_first_name, ap_patronymic, ap_sign_vip;
	private Textbox fbranch, fid_client, fname, fcode_country, fcode_type, fcode_resident, fcode_subject, fsign_registr,
			fcode_form, fdate_open, fdate_close, fstate, fkod_err, ffile_name, fp_birthday, fp_post_address,
			fp_passport_type, fp_passport_serial, fp_passport_number, fp_passport_place_registration,
			fp_passport_date_registration, fp_code_tax_org, fp_number_tax_registration, fp_code_bank,
			fp_code_class_credit, fp_code_citizenship, fp_birth_place, fp_code_capacity, fp_capacity_status_date,
			fp_capacity_status_place, fp_num_certif_capacity, fp_phone_home, fp_phone_mobile, fp_email_address,
			fp_pension_sertif_serial, fp_code_gender, fp_code_nation, fp_code_birth_region, fp_code_birth_distr,
			fp_type_document, fp_passport_date_expiration, fp_code_adr_region, fp_code_adr_distr, fp_inps, fp_family,
			fp_first_name, fp_patronymic, fp_sign_vip;
	private RefCBox state_cbox, p_code_citizenship_cbox, p_type_document_cbox, p_code_nation_cbox, p_code_gender_cbox,
			p_code_adr_region_cbox, p_code_adr_distr_cbox, p_code_capacity_cbox, p_code_bank_cbox,
			p_code_class_credit_cbox, p_code_tax_org_cbox, code_type_cbox, code_country_cbox, code_resident_cbox;
	private RefCBox astate_cbox, ap_code_citizenship_cbox, ap_type_document_cbox, ap_code_nation_cbox,
			ap_code_gender_cbox, ap_code_adr_region_cbox, ap_code_adr_distr_cbox, ap_code_capacity_cbox,
			ap_code_bank_cbox, ap_code_class_credit_cbox, ap_code_tax_org_cbox, acode_type_cbox, acode_country_cbox,
			acode_resident_cbox;
	private RefCBox fstate_cbox, fp_code_citizenship_cbox, fp_type_document_cbox, fp_code_nation_cbox,
			fp_code_gender_cbox, fp_code_adr_region_cbox, fp_code_adr_distr_cbox, fp_code_capacity_cbox,
			fp_code_bank_cbox, fp_code_class_credit_cbox, fp_code_tax_org_cbox, fcode_type_cbox, fcode_country_cbox,
			fcode_resident_cbox;
	private Datebox p_birthday1, fp_birthday1, ap_birthday1, p_passport_date_registration1,
			fp_passport_date_registration1, ap_passport_date_registration1, p_passport_date_expiration1,
			fp_passport_date_expiration1, ap_passport_date_expiration1, p_capacity_status_date1,
			fp_capacity_status_date1, ap_capacity_status_date1;

	private Paging client_pPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String alias, branch1;
	private Connection conn;

	private List<RefData> statelist = new ArrayList<RefData>();
	private List<RefData> citizenshiplist = new ArrayList<RefData>();
	private List<RefData> typedocumentlist = new ArrayList<RefData>();
	private List<RefData> nationlist = new ArrayList<RefData>();
	private List<RefData> genderlist = new ArrayList<RefData>();
	private List<RefData> regionlist = new ArrayList<RefData>();
	private List<RefData> distrlist = new ArrayList<RefData>();
	private List<RefData> capacitylist = new ArrayList<RefData>();
	private List<RefData> banklist = new ArrayList<RefData>();
	private List<RefData> creditlist = new ArrayList<RefData>();
	private List<RefData> taxlist = new ArrayList<RefData>();
	private List<RefData> codetypelist = new ArrayList<RefData>();
	private List<RefData> residentlist = new ArrayList<RefData>();

	public String un;
	public String pwd;
	// public String alias;
	// private Map<Integer,String> actionsMap;
	private Map<Integer, String> clientActions;

	public Client_pFilter filter;
	public Client_p newclient;

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Client_p current = new Client_p();
	private Client_p tclient; // = new Client_p();

	public Client_p getTclient() {
		return tclient;
	}

	public void setTclient(Client_p tclient) {
		this.tclient = tclient;
	}

	public Client_pViewCtrl() {
		super('$', false, false);
	}

	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 36;
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}

		branch1 = (String) session.getAttribute("branch");
		conn = (Connection) session.getAttribute("connection");
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		// alias = (String) session.getAttribute("alias");
		conn = ConnectionPool.getConnection(un, pwd, alias);

		// filter.setBranch(branch1);

		statelist = Client_pService.getStateList(alias);
		state_cbox.setModel(new ListModelList(statelist));
		fstate_cbox.setModel(new ListModelList(statelist));
		astate_cbox.setModel(new ListModelList(statelist));

		citizenshiplist = Client_pService.getCodeCitizenshipList(alias);
		p_code_citizenship_cbox.setModel(new ListModelList(citizenshiplist));
		fp_code_citizenship_cbox.setModel(new ListModelList(citizenshiplist));
		ap_code_citizenship_cbox.setModel(new ListModelList(citizenshiplist));

		typedocumentlist = Client_pService.getTypeDocumentList(alias);
		p_type_document_cbox.setModel(new ListModelList(typedocumentlist));
		fp_type_document_cbox.setModel(new ListModelList(typedocumentlist));
		ap_type_document_cbox.setModel(new ListModelList(typedocumentlist));

		nationlist = Client_pService.getCodeNationList(alias);
		p_code_nation_cbox.setModel(new ListModelList(nationlist));
		fp_code_nation_cbox.setModel(new ListModelList(nationlist));
		ap_code_nation_cbox.setModel(new ListModelList(nationlist));

		genderlist = Client_pService.getCodeGenderList(alias);
		p_code_gender_cbox.setModel(new ListModelList(genderlist));
		fp_code_gender_cbox.setModel(new ListModelList(genderlist));
		ap_code_gender_cbox.setModel(new ListModelList(genderlist));

		regionlist = Client_pService.getCodeAdrRegionList(alias);
		p_code_adr_region_cbox.setModel(new ListModelList(regionlist));
		fp_code_adr_region_cbox.setModel(new ListModelList(regionlist));
		ap_code_adr_region_cbox.setModel(new ListModelList(regionlist));

		distrlist = Client_pService.getCodeAdrDistrList(alias);
		p_code_adr_distr_cbox.setModel(new ListModelList(distrlist));
		fp_code_adr_distr_cbox.setModel(new ListModelList(distrlist));
		ap_code_adr_distr_cbox.setModel(new ListModelList(distrlist));

		capacitylist = Client_pService.getCodeCapacityList(alias);
		p_code_capacity_cbox.setModel(new ListModelList(capacitylist));
		fp_code_capacity_cbox.setModel(new ListModelList(capacitylist));
		ap_code_capacity_cbox.setModel(new ListModelList(capacitylist));

		banklist = Client_pService.getCodeBankList(alias);
		p_code_bank_cbox.setModel(new ListModelList(banklist));
		fp_code_bank_cbox.setModel(new ListModelList(banklist));
		ap_code_bank_cbox.setModel(new ListModelList(banklist));

		creditlist = Client_pService.getCodeClassCreditList(alias);
		p_code_class_credit_cbox.setModel(new ListModelList(creditlist));
		fp_code_class_credit_cbox.setModel(new ListModelList(creditlist));
		ap_code_class_credit_cbox.setModel(new ListModelList(creditlist));

		taxlist = Client_pService.getCodeTaxOrgList(alias);
		p_code_tax_org_cbox.setModel(new ListModelList(taxlist));
		fp_code_tax_org_cbox.setModel(new ListModelList(taxlist));
		ap_code_tax_org_cbox.setModel(new ListModelList(taxlist));

		codetypelist = Client_pService.getCodeTypeList(alias);
		code_type_cbox.setModel(new ListModelList(codetypelist));
		fcode_type_cbox.setModel(new ListModelList(codetypelist));
		acode_type_cbox.setModel(new ListModelList(codetypelist));

		code_country_cbox.setModel(new ListModelList(citizenshiplist));
		fcode_country_cbox.setModel(new ListModelList(citizenshiplist));
		acode_country_cbox.setModel(new ListModelList(citizenshiplist));

		residentlist = Client_pService.getCodeResidentList(alias);
		code_resident_cbox.setModel((new ListModelList(residentlist)));
		fcode_resident_cbox.setModel((new ListModelList(residentlist)));
		acode_resident_cbox.setModel((new ListModelList(residentlist)));

		clientActions = Client_pService.getClientActionsMap(alias);
		Toolbarbutton tbtn = new Toolbarbutton("File");
		tb_actions.appendChild(tbtn);
		//tb.appendChild(tbtn);
		initActionBar(current);

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Client_p pclient_p = (Client_p) data;

				row.setValue(pclient_p);

				row.appendChild(new Listcell(pclient_p.getId()));
				row.appendChild(new Listcell(pclient_p.getBranch()));
				row.appendChild(new Listcell(pclient_p.getId_client()));
				row.appendChild(new Listcell(pclient_p.getName()));
				row.appendChild(new Listcell(pclient_p.getCode_country()));
				row.appendChild(new Listcell(pclient_p.getCode_type()));
				row.appendChild(new Listcell(pclient_p.getCode_resident()));
				row.appendChild(new Listcell(pclient_p.getCode_subject()));
				row.appendChild(new Listcell(pclient_p.getSign_registr()));
				row.appendChild(new Listcell(pclient_p.getCode_form()));
				// row.appendChild(new Listcell(pclient_p.getDate_open()));
				// row.appendChild(new Listcell(pclient_p.getDate_close()));
				row.appendChild(new Listcell(pclient_p.getState()));
				row.appendChild(new Listcell(pclient_p.getKod_err()));
				row.appendChild(new Listcell(pclient_p.getFile_name()));
				// row.appendChild(new Listcell(pclient_p.getP_birthday()));
				row.appendChild(new Listcell(pclient_p.getP_post_address()));
				row.appendChild(new Listcell(pclient_p.getP_passport_type()));
				row.appendChild(new Listcell(pclient_p.getP_passport_serial()));
				row.appendChild(new Listcell(pclient_p.getP_passport_number()));
				row.appendChild(new Listcell(pclient_p.getP_passport_place_registration()));
				// row.appendChild(new
				// Listcell(pclient_p.getP_passport_date_registration()));
				row.appendChild(new Listcell(pclient_p.getP_code_tax_org()));
				row.appendChild(new Listcell(pclient_p.getP_number_tax_registration()));
				row.appendChild(new Listcell(pclient_p.getP_code_bank()));
				row.appendChild(new Listcell(pclient_p.getP_code_class_credit()));
				row.appendChild(new Listcell(pclient_p.getP_code_citizenship()));
				row.appendChild(new Listcell(pclient_p.getP_birth_place()));
				row.appendChild(new Listcell(pclient_p.getP_code_capacity()));
				// row.appendChild(new
				// Listcell(pclient_p.getP_capacity_status_date()));
				row.appendChild(new Listcell(pclient_p.getP_capacity_status_place()));
				row.appendChild(new Listcell(pclient_p.getP_num_certif_capacity()));
				row.appendChild(new Listcell(pclient_p.getP_phone_home()));
				row.appendChild(new Listcell(pclient_p.getP_phone_mobile()));
				row.appendChild(new Listcell(pclient_p.getP_email_address()));
				row.appendChild(new Listcell(pclient_p.getP_pension_sertif_serial()));
				row.appendChild(new Listcell(pclient_p.getP_code_gender()));
				row.appendChild(new Listcell(pclient_p.getP_code_nation()));
				row.appendChild(new Listcell(pclient_p.getP_code_birth_region()));
				row.appendChild(new Listcell(pclient_p.getP_code_birth_distr()));
				row.appendChild(new Listcell(pclient_p.getP_type_document()));
				// row.appendChild(new
				// Listcell(pclient_p.getP_passport_date_expiration()));
				row.appendChild(new Listcell(pclient_p.getP_code_adr_region()));
				row.appendChild(new Listcell(pclient_p.getP_code_adr_distr()));
				row.appendChild(new Listcell(pclient_p.getP_inps()));
				row.appendChild(new Listcell(pclient_p.getP_family()));
				row.appendChild(new Listcell(pclient_p.getP_first_name()));
				row.appendChild(new Listcell(pclient_p.getP_patronymic()));
				row.appendChild(new Listcell(pclient_p.getP_sign_vip()));
			}
		});

		refreshModel(_startPageNumber);
	}

	public void onPaging$client_pPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		client_pPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, "");

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		client_pPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (Client_p) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public Client_p getCurrent() {
		return current;
	}

	public void setCurrent(Client_p current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		if (current != null) {
			state_cbox.setSelecteditem(current.getState());
		}
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	private void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		tclient = new Client_p();
		tclient.setState("0");

		fgrd.setVisible(false);
		// astate_cbox.setValue("0");
		// astate_cbox.setSelectedIndex(0);
		astate_cbox.setSelecteditem(tclient.getState());
		// на время

		aname.setValue("hamza hamza");
		ap_family.setValue("FAMILY");
		ap_first_name.setValue("FIRSTNAME");
		ap_patronymic.setValue("HH");
		ap_code_adr_region.setValue("06");
		ap_code_adr_distr.setValue("030");
		ap_code_capacity.setValue("0801");
		ap_type_document.setValue("1");
		ap_post_address.setValue("1");
		ap_passport_number.setValue("1234567");
		//

		initActionBar2();
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			if (addgrd.isVisible()) {
				// aid.setValue("00112233");
				// ap_family.setValue("RRRRRRRRRRRRRRRRRRRRR");
				// alert("aid="+aid.getValue()+"
				// ap_family-"+ap_family.getValue());
				newclient = new Client_p();
				newclient.setName(aname.getValue());
				newclient.setId(null);
				newclient.setCode_country(acode_country.getValue());
				newclient.setCode_type(acode_type.getValue());
				newclient.setCode_resident(acode_resident_cbox.getValue());
				newclient.setCode_subject("P");
				newclient.setSign_registr("2");
				// acode_form.getValue(),
				// adate_open.getValue(),
				// adate_close.getValue(),
				newclient.setState("0");
				// akod_err.getValue(),
				// afile_name.getValue(),
				newclient.setP_birthday(ap_birthday1.getValue());
				// newclient.setP_birthday(df.parse(ap_birthday1.getValue()));
				newclient.setP_post_address(ap_post_address.getValue());
				newclient.setP_passport_type("N");
				newclient.setP_passport_serial(ap_passport_serial.getValue());
				newclient.setP_passport_number(ap_passport_number.getValue());
				;
				newclient.setP_passport_place_registration(ap_passport_place_registration.getValue());
				;
				newclient.setP_passport_date_registration(ap_passport_date_registration1.getValue());
				;
				newclient.setP_code_tax_org(ap_code_tax_org.getValue());
				;
				newclient.setP_number_tax_registration(ap_number_tax_registration.getValue());
				newclient.setP_code_bank(branch1);
				// ap_code_class_credit.getValue(),
				newclient.setP_code_citizenship(ap_code_citizenship_cbox.getValue());
				newclient.setP_birth_place(ap_birth_place.getValue());
				newclient.setP_code_capacity(ap_code_capacity.getValue());
				newclient.setP_capacity_status_date(ap_capacity_status_date1.getValue());
				newclient.setP_capacity_status_place(ap_capacity_status_place.getValue());
				newclient.setP_num_certif_capacity(ap_num_certif_capacity.getValue());
				newclient.setP_phone_home(ap_phone_home.getValue());
				newclient.setP_phone_mobile(ap_phone_mobile.getValue());
				newclient.setP_email_address(ap_email_address.getValue());
				newclient.setP_pension_sertif_serial(ap_pension_sertif_serial.getValue());
				newclient.setP_code_gender(ap_code_gender.getValue());
				newclient.setP_code_nation(ap_code_nation.getValue());
				// newclient.setP_code_birth_region(ap_code_birth_region.getValue());
				// newclient.setP_code_birth_distr(ap_code_birth_distr.getValue());
				newclient.setP_type_document(ap_type_document.getValue());
				newclient.setP_passport_date_expiration(ap_passport_date_expiration1.getValue());
				newclient.setP_code_adr_region(ap_code_adr_region.getValue());
				newclient.setP_code_adr_distr(ap_code_adr_distr.getValue());
				newclient.setP_inps(ap_inps.getValue());
				newclient.setP_family(ap_family.getValue());
				newclient.setP_first_name(ap_first_name.getValue());
				newclient.setP_patronymic(ap_patronymic.getValue());
				// newclient.setP_sign_vip(ap_sign_vip.getValue());
				Res res = Client_pService.doAction(newclient, 1, alias, un, pwd);
				if (res.getCode() != 0) {
					alert(res.getName());
					return;
				}

				// String Id= Client_pService.getParamID( alias, un, pwd);
				newclient.setId(Client_pService.getParamID(alias, un, pwd));
				newclient = Client_pService.getclient_p(Integer.parseInt(newclient.getId()));
				aid_client.setValue(newclient.getId_client());
				astate_cbox.setSelecteditem(newclient.getState());
				initActionBar(newclient);

				// alert("aid_client="+aid_client);
				// ap_family.setValue("RRRRRRRRRRRRRRRRRRRRR");
				// CheckNull.clearForm(addgrd);
				// frmgrd.setVisible(true);
				// addgrd.setVisible(false);
				// fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new Client_pFilter();

				filter.setId(fid_client.getValue());
				// filter.setBranch(fbranch.getValue());
				// filter.setId_client(fid_client.getValue());
				filter.setName(fname.getValue());
				// filter.setCode_country(fcode_country.getValue());
				// filter.setCode_type(fcode_type.getValue());
				// filter.setCode_resident(fcode_resident.getValue());
				// filter.setCode_subject(fcode_subject.getValue());
				// filter.setSign_registr(fsign_registr.getValue());
				// filter.setCode_form(fcode_form.getValue());
				// filter.setDate_open(fdate_open.getValue());
				// filter.setDate_close(fdate_close.getValue());
				filter.setState(fstate_cbox.getValue());
				// filter.setKod_err(fkod_err.getValue());
				// filter.setFile_name(ffile_name.getValue());
				// filter.setP_birthday(fp_birthday.getValue());
				filter.setP_post_address(fp_post_address.getValue());
				// filter.setP_passport_type(fp_passport_type.getValue());
				// filter.setP_passport_serial(fp_passport_serial.getValue());
				// filter.setP_passport_number(fp_passport_number.getValue());
				// filter.setP_passport_place_registration(fp_passport_place_registration.getValue());
				// filter.setP_passport_date_registration(fp_passport_date_registration.getValue());
				// filter.setP_code_tax_org(fp_code_tax_org.getValue());
				// filter.setP_number_tax_registration(fp_number_tax_registration.getValue());
				// filter.setP_code_bank(fp_code_bank.getValue());
				// filter.setP_code_class_credit(fp_code_class_credit.getValue());
				// filter.setP_code_citizenship(fp_code_citizenship.getValue());
				// filter.setP_birth_place(fp_birth_place.getValue());
				// filter.setP_code_capacity(fp_code_capacity.getValue());
				// filter.setP_capacity_status_date(fp_capacity_status_date.getValue());
				// filter.setP_capacity_status_place(fp_capacity_status_place.getValue());
				// filter.setP_num_certif_capacity(fp_num_certif_capacity.getValue());
				// filter.setP_phone_home(fp_phone_home.getValue());
				// filter.setP_phone_mobile(fp_phone_mobile.getValue());
				// filter.setP_email_address(fp_email_address.getValue());
				// filter.setP_pension_sertif_serial(fp_pension_sertif_serial.getValue());
				// filter.setP_code_gender(fp_code_gender.getValue());
				// filter.setP_code_nation(fp_code_nation.getValue());
				// filter.setP_code_birth_region(fp_code_birth_region.getValue());
				// filter.setP_code_birth_distr(fp_code_birth_distr.getValue());
				// filter.setP_type_document(fp_type_document.getValue());
				// filter.setP_passport_date_expiration(fp_passport_date_expiration.getValue());
				// filter.setP_code_adr_region(fp_code_adr_region.getValue());
				// filter.setP_code_adr_distr(fp_code_adr_distr.getValue());
				filter.setP_inps(fp_inps.getValue());
				filter.setP_family(fp_family.getValue());
				filter.setP_first_name(fp_first_name.getValue());
				filter.setP_patronymic(fp_patronymic.getValue());
				// filter.setP_sign_vip(fp_sign_vip.getValue());

			} else {

				// current.setId(id.getValue());
				current.setBranch(branch.getValue());
				current.setId_client(id_client.getValue());
				current.setName(name.getValue());
				current.setCode_country(code_country.getValue());
				current.setCode_type(code_type.getValue());
				current.setCode_resident(code_resident.getValue());
				current.setCode_subject(code_subject.getValue());
				current.setSign_registr(sign_registr.getValue());
				current.setCode_form(code_form.getValue());
				// current.setDate_open(date_open.getValue());
				// current.setDate_close(date_close.getValue());
				current.setState(state.getValue());
				current.setKod_err(kod_err.getValue());
				current.setFile_name(file_name.getValue());
				// current.setP_birthday(p_birthday.getValue());
				current.setP_post_address(p_post_address.getValue());
				current.setP_passport_type(p_passport_type.getValue());
				current.setP_passport_serial(p_passport_serial.getValue());
				current.setP_passport_number(p_passport_number.getValue());
				current.setP_passport_place_registration(p_passport_place_registration.getValue());
				// current.setP_passport_date_registration(p_passport_date_registration.getValue());
				current.setP_code_tax_org(p_code_tax_org.getValue());
				current.setP_number_tax_registration(p_number_tax_registration.getValue());
				current.setP_code_bank(p_code_bank.getValue());
				current.setP_code_class_credit(p_code_class_credit.getValue());
				current.setP_code_citizenship(p_code_citizenship.getValue());
				current.setP_birth_place(p_birth_place.getValue());
				current.setP_code_capacity(p_code_capacity.getValue());
				// current.setP_capacity_status_date(p_capacity_status_date.getValue());
				current.setP_capacity_status_place(p_capacity_status_place.getValue());
				current.setP_num_certif_capacity(p_num_certif_capacity.getValue());
				current.setP_phone_home(p_phone_home.getValue());
				current.setP_phone_mobile(p_phone_mobile.getValue());
				current.setP_email_address(p_email_address.getValue());
				current.setP_pension_sertif_serial(p_pension_sertif_serial.getValue());
				current.setP_code_gender(p_code_gender.getValue());
				current.setP_code_nation(p_code_nation.getValue());
				current.setP_code_birth_region(p_code_birth_region.getValue());
				current.setP_code_birth_distr(p_code_birth_distr.getValue());
				current.setP_type_document(p_type_document.getValue());
				// current.setP_passport_date_expiration(p_passport_date_expiration.getValue());
				current.setP_code_adr_region(p_code_adr_region.getValue());
				current.setP_code_adr_distr(p_code_adr_distr.getValue());
				current.setP_inps(p_inps.getValue());
				current.setP_family(p_family.getValue());
				current.setP_first_name(p_first_name.getValue());
				current.setP_patronymic(p_patronymic.getValue());
				current.setP_sign_vip(p_sign_vip.getValue());
				Client_pService.update(current);
			}
			if (!addgrd.isVisible()) {
				onClick$btn_back();
			}
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initActionBar(Client_p cl) {
		Map<Integer, String> availableActions = Client_pService.getAvailableActions(cl, alias, un, pwd);
		// actionsMap = Util.actionListToMapInt(current.getSign_registr(),
		// clientActions);
		Toolbarbutton button;
		tb_actions.getChildren().clear();
		//tb.getChildren().clear();
		button = new Toolbarbutton("Новый");
		button.setAttribute("action", 0);
		button.addEventListener(Events.ON_CLICK, new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// executeAction((Integer)arg0.getTarget().getAttribute("action"));
				onClick$btn_add();
			}
		});
		tb_actions.appendChild(button);
		//tb.appendChild(button);

		for (Map.Entry<Integer, String> entry : availableActions.entrySet()) {
			if (entry.getKey() == 1) {
				continue;
			}
			button = new Toolbarbutton(entry.getValue());
			button.setAttribute("action", entry.getKey());
			button.addEventListener(Events.ON_CLICK, new EventListener() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					executeAction((Integer) arg0.getTarget().getAttribute("action"));
				}
			});
			tb_actions.appendChild(button);
			//tb.appendChild(button);
		}
	}

	private void initActionBar2() {

		Toolbarbutton button;
		tb_actions.getChildren().clear();
		//tb.getChildren().clear();

		button = new Toolbarbutton("Отменить");
		button.setAttribute("action", 0);
		button.addEventListener(Events.ON_CLICK, new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// executeAction((Integer)arg0.getTarget().getAttribute("action"));
				onClick$btn_cancel();
			}
		});
		tb_actions.appendChild(button);
		//tb.appendChild(button);

		button = new Toolbarbutton("Открыть");
		button.setAttribute("action", 1);
		button.addEventListener(Events.ON_CLICK, new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// executeAction((Integer)arg0.getTarget().getAttribute("action"));
				// onClick$btn_add();
				onClick$btn_save();
			}
		});
		tb_actions.appendChild(button);
		//tb.appendChild(button);
	}

	private void executeAction_2(final int actionId) {
		try {
			Messagebox.show("вы действительно хотите выполнить действие:" + clientActions.get(actionId) + "?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals(Messagebox.ON_OK)) {
								// Res res = Client_pService.doAction(current,
								// actionId, alias, un, pwd);
								// if(res.getCode()!=0){
								// alert(res.getName());
								// return;
								// }
								// current =
								// Client_pService.getclient_p(Integer.parseInt(current.getId()));
								// state_cbox.setSelecteditem(current.getState());

								Client_p file = null;
								Listitem row = (Listitem) event.getTarget().getAttribute("row");

								// Long fileId = (Long)
								// event.getTarget().getAttribute("fileId");

								row.getChildren().clear();
								file = Client_pService.getclient_p(Integer.parseInt(current.getId()));
								fillRow(row, file);
								row.appendChild(new Listcell(""));

							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void fillRow(Listitem row, Client_p pFile) {
		String fileName = pFile.getName();

		row.setValue(pFile);
		row.setStyle("height:40px;");
		row.appendChild(new Listcell(pFile.getId().toString()));
		row.appendChild(new Listcell(fileName));
		row.appendChild(new Listcell(df.format(pFile.getDate_open())));
		row.appendChild(new Listcell(pFile.getId_client()));
	}

	private void executeAction(final int actionId) {
		try {
			Messagebox.show("вы действительно хотите выполнить действие:" + clientActions.get(actionId) + "?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals(Messagebox.ON_OK)) {
								Res res;
								if (tclient != null) {
									res = Client_pService.doAction(tclient, actionId, alias, un, pwd);
								} else {
									res = Client_pService.doAction(current, actionId, alias, un, pwd);
								}
								// Res res = Client_pService.doAction(current,
								// actionId);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}

								current = Client_pService.getclient_p(Integer.parseInt(current.getId()));
								state_cbox.setSelecteditem(current.getState());
								initActionBar(current);
								// sendSelEvt();

							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void onClick$dataGrid() {

		// if (!current.getCode_type().equals("08"))
		// {
		// btn_001.setVisible(false);
		// btn_001.setVisible(!btn_001.isVisible());
		// }
		// else
		// {
		// btn_001.setVisible(true);
		// }
		initActionBar(current);
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new Client_pFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
		initActionBar(current);
	}

	public void onSelect$code_citizenship_cbox() {
		p_code_citizenship.setValue(p_code_citizenship_cbox.getValue());
	}

	// public void onSelect$acode_resident_cbox()
	// {
	// tclient.setValue(acode_resident_cbox.getValue());
	// }

	public void onSelect$code_type_cbox() {
		code_type.setValue(code_type_cbox.getValue());
	}

	public void onChange$code_type() {
		code_type_cbox.setSelecteditem(code_type.getValue());
	}

	public void onSelect$acode_type_cbox() {
		acode_type.setValue(acode_type_cbox.getValue());
	}

	public void onChange$acode_type() {
		acode_type_cbox.setSelecteditem(acode_type.getValue());
	}

	public void onSelect$fcode_type_cbox() {
		fcode_type.setValue(fcode_type_cbox.getValue());
	}

	public void onChange$fcode_type() {
		fcode_type_cbox.setSelecteditem(fcode_type.getValue());
	}

	public void onSelect$p_code_citizenship_cbox() {
		p_code_citizenship.setValue(p_code_citizenship_cbox.getValue());
	}

	public void onChange$p_code_citizenship() {
		p_code_citizenship_cbox.setSelecteditem(p_code_citizenship.getValue());
	}

	public void onSelect$ap_code_citizenship_cbox() {
		ap_code_citizenship.setValue(ap_code_citizenship_cbox.getValue());
	}

	public void onChange$ap_code_citizenship() {
		ap_code_citizenship_cbox.setSelecteditem(ap_code_citizenship.getValue());
	}

	public void onSelect$fp_code_citizenship_cbox() {
		fp_code_citizenship.setValue(fp_code_citizenship_cbox.getValue());
	}

	public void onChange$fp_code_citizenship() {
		fp_code_citizenship_cbox.setSelecteditem(fp_code_citizenship.getValue());
	}

	public void onSelect$code_resident_cbox() {
		code_resident.setValue(code_resident_cbox.getValue());
	}

	public void onChange$code_resident() {
		code_resident_cbox.setSelecteditem(code_resident.getValue());
	}

	// public void onSelect$acode_resident_cbox() {
	// acode_resident.setValue(acode_resident_cbox.getValue());
	// }

	public void onChange$acode_resident() {
		acode_resident_cbox.setSelecteditem(acode_resident.getValue());
	}

	public void onSelect$fcode_resident_cbox() {
		fcode_resident.setValue(fcode_resident_cbox.getValue());
	}

	public void onChange$fcode_resident() {
		fcode_resident_cbox.setSelecteditem(fcode_resident.getValue());
	}

	public void onSelect$p_type_document_cbox() {
		p_type_document.setValue(p_type_document_cbox.getValue());
	}

	public void onChange$p_type_document() {
		p_type_document_cbox.setSelecteditem(p_type_document.getValue());
	}

	public void onSelect$ap_type_document_cbox() {
		ap_type_document.setValue(ap_type_document_cbox.getValue());
	}

	public void onChange$ap_type_document() {
		ap_type_document_cbox.setSelecteditem(ap_type_document.getValue());
	}

	public void onSelect$fp_type_document_cbox() {
		fp_type_document.setValue(fp_type_document_cbox.getValue());
	}

	public void onChange$fp_type_document() {
		fp_type_document_cbox.setSelecteditem(fp_type_document.getValue());
	}

	public void onSelect$p_code_nation_cbox() {
		p_code_nation.setValue(p_code_nation_cbox.getValue());
	}

	public void onChange$p_code_nation() {
		p_code_nation_cbox.setSelecteditem(p_code_nation.getValue());
	}

	public void onSelect$ap_code_nation_cbox() {
		ap_code_nation.setValue(ap_code_nation_cbox.getValue());
	}

	public void onChange$ap_code_nation() {
		// ap_code_nation_cbox.setValue(ap_code_nation.getValue());
		ap_code_nation_cbox.setSelecteditem(ap_code_nation.getValue());
	}

	public void onSelect$fp_code_nation_cbox() {
		fp_code_nation.setValue(fp_code_nation_cbox.getValue());
	}

	public void onChange$fp_code_nation() {
		fp_code_nation_cbox.setSelecteditem(fp_code_nation.getValue());
	}

	public void onSelect$p_code_gender_cbox() {
		p_code_gender.setValue(p_code_gender_cbox.getValue());
	}

	public void onChange$p_code_gender() {
		p_code_gender_cbox.setSelecteditem(p_code_gender.getValue());
	}

	public void onSelect$ap_code_gender_cbox() {
		ap_code_gender.setValue(ap_code_gender_cbox.getValue());
	}

	public void onChange$ap_code_gender() {
		ap_code_gender_cbox.setSelecteditem(ap_code_gender.getValue());
	}

	public void onSelect$fp_code_gender_cbox() {
		fp_code_gender.setValue(fp_code_gender_cbox.getValue());
	}

	public void onChange$fp_code_gender() {
		fp_code_gender_cbox.setSelecteditem(fp_code_gender.getValue());
	}

	public void onSelect$code_country_cbox() {
		code_country.setValue(code_country_cbox.getValue());
	}

	public void onChange$code_country() {
		code_country_cbox.setSelecteditem(code_country.getValue());
	}

	public void onSelect$acode_country_cbox() {
		acode_country.setValue(acode_country_cbox.getValue());
	}

	public void onChange$acode_country() {
		acode_country_cbox.setSelecteditem(acode_country.getValue());
	}

	public void onSelect$fcode_country_cbox() {
		fcode_country.setValue(fcode_country_cbox.getValue());
	}

	public void onChange$fcode_country() {
		fcode_country_cbox.setSelecteditem(fcode_country.getValue());
	}

	public void onSelect$p_code_adr_region_cbox() {
		p_code_adr_region.setValue(p_code_adr_region_cbox.getValue());
	}

	public void onChange$p_code_adr_region() {
		p_code_adr_region_cbox.setSelecteditem(p_code_adr_region.getValue());
	}

	public void onSelect$ap_code_adr_region_cbox() {
		ap_code_adr_region.setValue(ap_code_adr_region_cbox.getValue());
	}

	public void onChange$ap_code_adr_region() {
		ap_code_adr_region_cbox.setSelecteditem(ap_code_adr_region.getValue());
	}

	public void onSelect$fp_code_adr_region_cbox() {
		fp_code_adr_region.setValue(fp_code_adr_region_cbox.getValue());
	}

	public void onChange$fp_code_adr_region() {
		fp_code_adr_region_cbox.setSelecteditem(fp_code_adr_region.getValue());
	}

	public void onSelect$p_code_adr_distr_cbox() {
		p_code_adr_distr.setValue(p_code_adr_distr_cbox.getValue());
	}

	public void onChange$p_code_adr_distr() {
		p_code_adr_distr_cbox.setSelecteditem(p_code_adr_distr.getValue());
	}

	public void onSelect$ap_code_adr_distr_cbox() {
		ap_code_adr_distr.setValue(ap_code_adr_distr_cbox.getValue());
	}

	public void onChange$ap_code_adr_distr() {
		ap_code_adr_distr_cbox.setSelecteditem(ap_code_adr_distr.getValue());
	}

	public void onSelect$fp_code_adr_distr_cbox() {
		fp_code_adr_distr.setValue(fp_code_adr_distr_cbox.getValue());
	}

	public void onChange$fp_code_adr_distr() {
		fp_code_adr_distr_cbox.setSelecteditem(fp_code_adr_distr.getValue());
	}

	public void onSelect$p_code_capacity_cbox() {
		p_code_capacity.setValue(p_code_capacity_cbox.getValue());
	}

	public void onChange$p_code_capacity() {
		p_code_capacity_cbox.setSelecteditem(p_code_capacity.getValue());
	}

	public void onSelect$ap_code_capacity_cbox() {
		ap_code_capacity.setValue(ap_code_capacity_cbox.getValue());
	}

	public void onChange$ap_code_capacity() {
		ap_code_capacity_cbox.setSelecteditem(ap_code_capacity.getValue());
	}

	public void onSelect$fp_code_capacity_cbox() {
		fp_code_capacity.setValue(fp_code_capacity_cbox.getValue());
	}

	public void onChange$fp_code_capacity() {
		fp_code_capacity_cbox.setSelecteditem(fp_code_capacity.getValue());
	}

	public void onSelect$p_code_bank_cbox() {
		p_code_bank.setValue(p_code_bank_cbox.getValue());
	}

	public void onChange$p_code_bank() {
		p_code_bank_cbox.setSelecteditem(p_code_bank.getValue());
	}

	public void onSelect$ap_code_bank_cbox() {
		ap_code_bank.setValue(ap_code_bank_cbox.getValue());
	}

	public void onChange$ap_code_bank() {
		ap_code_bank_cbox.setSelecteditem(ap_code_bank.getValue());
	}

	public void onSelect$fp_code_bank_cbox() {
		fp_code_bank.setValue(fp_code_bank_cbox.getValue());
	}

	public void onChange$fp_code_bank() {
		fp_code_bank_cbox.setSelecteditem(fp_code_bank.getValue());
	}

	public void onSelect$p_code_class_credit_cbox() {
		p_code_class_credit.setValue(p_code_class_credit_cbox.getValue());
	}

	public void onChange$p_code_class_credit() {
		p_code_class_credit_cbox.setSelecteditem(p_code_class_credit.getValue());
	}

	public void onSelect$ap_code_class_credit_cbox() {
		ap_code_class_credit.setValue(ap_code_class_credit_cbox.getValue());
	}

	public void onChange$ap_code_class_credit() {
		ap_code_class_credit_cbox.setSelecteditem(ap_code_class_credit.getValue());
	}

	public void onSelect$fp_code_class_credit_cbox() {
		fp_code_class_credit.setValue(fp_code_class_credit_cbox.getValue());
	}

	public void onChange$fp_code_class_credit() {
		fp_code_class_credit_cbox.setSelecteditem(fp_code_class_credit.getValue());
	}

	public void onSelect$p_code_tax_org_cbox() {
		p_code_tax_org.setValue(p_code_tax_org_cbox.getValue());
	}

	public void onChange$p_code_tax_org() {
		p_code_tax_org_cbox.setSelecteditem(p_code_tax_org.getValue());
	}

	public void onSelect$ap_code_tax_org_cbox() {
		ap_code_tax_org.setValue(ap_code_tax_org_cbox.getValue());
	}

	public void onChange$ap_code_tax_org() {
		ap_code_tax_org_cbox.setSelecteditem(ap_code_tax_org.getValue());
	}

	public void onSelect$fp_code_tax_org_cbox() {
		fp_code_tax_org.setValue(fp_code_tax_org_cbox.getValue());
	}

	public void onChange$fp_code_tax_org() {
		fp_code_tax_org_cbox.setSelecteditem(fp_code_tax_org.getValue());
	}

}
