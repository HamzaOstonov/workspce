package com.is.customer_.core.composer;

import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Auxhead;
import org.zkoss.zul.Auxheader;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.customer_.core.ReferenceDictionary;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by root on 06.05.2017. 21:10
 */
public abstract class AbstractCustomerComposer extends GenericForwardComposer {
	protected Div main, div_prava;

	public RefCBox state;
	private RefCBox code_subject, code_country, p_code_citizenship, code_resident, p_type_document, p_code_gender,
			p_code_nation, p_code_class_credit, p_code_adr_region, p_pass_place_region, p_code_capacity, p_code_tax_org,
			p_code_adr_distr, p_pass_place_district, file_name, subbranch, p_code_adr_mahalla;
	protected Textbox p_code_citizenship_text, p_passport_serial, p_passport_number, p_type_document_text,
			p_pass_place_region_text, p_pass_place_district_text, p_passport_place_registration, p_code_tax_org_text,
			p_code_adr_region_text, p_code_adr_distr_text, p_post_address_flat, p_post_address, p_post_address_quarter,
			p_post_address_street, p_post_address_house, p_num_certif_capacity, p_pension_sertif_serial,
			p_capacity_status_place, p_pinfl, pasport_ser, pasport_num, pinfl, inn, p_code_adr_mahalla_text;
	protected Datebox p_birthday, birth_date, doc_date;
	private Datebox p_capacity_status_date, p_passport_date_expiration, p_passport_date_registration,
			date_open, date_open1, date_close, date_close1;
	private Radiogroup type_radio_open, type_radio_close;
	protected Auxheader aux_prava; 
	protected Auxhead au_prava;
	protected Grid grid_prava;
	protected Label lbl_prava; 
	protected Checkbox check_agreement, sign_public_official;
	private Row row_pdl_1, row_pdl_2;
	
	protected static List<RefData> nationMapList = null;
	protected String code_distr_pasp="";
	protected String code_distr_adr="";
	protected String code_mahalla_adr="";
	protected static List<RefData> documentMapList = null;
	protected static List<RefData> mahallaMapList = null;	
    
	protected SessionAttributes sessionAttributes;
	protected Customer customer;
	protected AnnotateDataBinder binder;

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public void sendOnInitToAllComboboxes(Component comp) {
		if (comp.getChildren().size() == 0) {
			if (comp instanceof RefCBox)
				sendOnInit(comp);
			return;
		}

		List<Component> list = comp.getChildren();
		for (Component component : list) {
			sendOnInitToAllComboboxes(component);
		}
	}

	// ������������� ������������
	protected void initModelsForListbox() {

		List<RefData> countryTypes = ReferenceDictionary.getCountries(sessionAttributes.getSchema());
		List<RefData> regionTypes = ReferenceDictionary.getRegions(sessionAttributes.getSchema());

		state.setModel(new ListModelList(ReferenceDictionary.getState(sessionAttributes.getSchema())));
		code_subject.setModel(new ListModelList(ReferenceDictionary.getClientTypes(sessionAttributes.getSchema())));
		code_country.setModel(new ListModelList(countryTypes));
		p_code_citizenship.setModel(new ListModelList(countryTypes));
		code_resident.setModel(new ListModelList(ReferenceDictionary.getRezKl(sessionAttributes.getSchema())));
		p_type_document
				.setModel(new ListModelList(ReferenceDictionary.getDocumentTypes(sessionAttributes.getSchema())));
		p_code_gender.setModel(new ListModelList(ReferenceDictionary.getGenderTypes(sessionAttributes.getSchema())));
		p_code_nation.setModel(new ListModelList(ReferenceDictionary.getNations(sessionAttributes.getSchema())));
		p_code_class_credit.setModel(new ListModelList(ReferenceDictionary.getSKlass(sessionAttributes.getSchema())));
		p_code_adr_region.setModel(new ListModelList(regionTypes));
		p_pass_place_region.setModel(new ListModelList(regionTypes));
		p_code_capacity.setModel(new ListModelList(ReferenceDictionary.getCapacity(sessionAttributes.getSchema())));
		p_code_tax_org.setModel(new ListModelList(ReferenceDictionary.getGNI(sessionAttributes.getSchema())));
		
		if (ReferenceDictionary.getSubsidiaries_map(sessionAttributes.getSchema()).containsKey(sessionAttributes.getBranch())) {
			file_name.setModel(new ListModelList(ReferenceDictionary.getSubsidiaries_map(sessionAttributes.getSchema()).get(sessionAttributes.getBranch())));
		} 
		if (ReferenceDictionary.getSubbranchs_map(sessionAttributes.getSchema()).containsKey(sessionAttributes.getBranch())) {
			subbranch.setModel(new ListModelList(ReferenceDictionary.getSubbranchs_map(sessionAttributes.getSchema()).get(sessionAttributes.getBranch())));
		} 
		
		if (!CheckNull.isEmpty(customer.getP_code_adr_region()))
			p_code_adr_distr.setModel(new ListModelList(getDistrictsByRegion(customer.getP_code_adr_region())));
		else
			p_code_adr_distr.setModel(new ListModelList(new ArrayList<RefData>()));
			//p_code_adr_distr.setModel(new ListModelList(getAllDistricts()));

		if (p_code_adr_mahalla!=null) {
			if (!CheckNull.isEmpty(customer.getP_code_adr_distr()))
				p_code_adr_mahalla.setModel(new ListModelList(getMahallasByDistr(customer.getP_code_adr_distr()))); 
			else
				p_code_adr_mahalla.setModel(new ListModelList(new ArrayList<RefData>()));
		}
		if (!CheckNull.isEmpty(customer.getP_pass_place_region()))
			p_pass_place_district.setModel(new ListModelList(getDistrictsByRegion(customer.getP_pass_place_region())));
		else
			p_pass_place_district.setModel(new ListModelList(new ArrayList<RefData>()));
			//p_pass_place_district.setModel(new ListModelList(getAllDistricts()));
		if (nationMapList == null)
			nationMapList = 
		 RefDataService.getRefData(
				"select trim(t.id) data, trim(t.map) label from ss_national_gcp t order by 1", sessionAttributes.getSchema());
		if (documentMapList == null)
			documentMapList = 
		 RefDataService.getRefData(
				"select trim(t.id) data, trim(t.map) label from ss_document_elgov t order by 1", sessionAttributes.getSchema());
		if (mahallaMapList == null)
			mahallaMapList = 
		 RefDataService.getRefData(
				"select kod_1c data, koduzkad label from s_spr_125 t where active='A' order by kod_1c", sessionAttributes.getSchema());
		
	}

	
	
	protected void initSessionAttributes() {
		sessionAttributes = GeneralUtils.getSessionAttributes(session);
	}

	public void set_visible_pdl_rows(Boolean isVisble) {
		row_pdl_1.setVisible(isVisble);
		row_pdl_2.setVisible(isVisble);
	}

	public void onChange$p_type_document_text() {
		String value = p_type_document_text.getValue();
		p_type_document.setSelecteditem(value);
		changeDocumentType(p_type_document.getValue());
	}

	public void onChange$p_type_document() {
		String value = p_type_document.getValue();
		changeDocumentType(value);
	}

	public void changeDocumentType(String value) {
		if (!CheckNull.isEmpty(value)) {
			int i = Integer.parseInt(value);
			p_pass_place_district.setDisabled(false);
			p_pass_place_district_text.setDisabled(false);
			p_pass_place_region.setDisabled(false);
			p_pass_place_region_text.setDisabled(false);
			switch (i) {
			case 1:
				code_resident.setSelecteditem("1");
				p_code_citizenship_text.setValue("860");
				p_code_citizenship.setSelecteditem("860");
				customer.setCode_resident("1");
				customer.setP_code_citizenship("860");
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				p_passport_serial.setMaxlength(2);
				p_passport_number.setMaxlength(7);
				break;
			case 2:
				code_resident.setSelecteditem("1");
				customer.setCode_resident("1");
				p_passport_serial.setMaxlength(9);
				p_passport_number.setMaxlength(9);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			case 3:
				p_passport_number.setMaxlength(12);
				p_passport_serial.setMaxlength(9);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			case 4:
				code_resident.setSelecteditem("2");
				p_passport_number.setMaxlength(12);
				customer.setCode_resident("2");
				// if (customer.getP_code_citizenship()!=null
				// &&
				// !customer.getP_code_citizenship().equalsIgnoreCase(value))
				// customer.setP_code_citizenship(null);

				customer.setP_pass_place_region(null);
				customer.setP_pass_place_district(null);
				p_passport_serial.setMaxlength(9);
				sendOnRender(p_passport_number);
				sendOnRender(p_passport_serial);
				p_pass_place_district.setDisabled(true);
				p_pass_place_district_text.setDisabled(true);
				p_pass_place_region.setDisabled(true);
				p_pass_place_region_text.setDisabled(true);
				break;
			case 5:
				code_resident.setSelecteditem("1");
				customer.setCode_resident("1");
				p_passport_serial.setMaxlength(9);
				p_passport_number.setMaxlength(9);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			case 6:
				code_resident.setSelecteditem("1");
				p_code_citizenship_text.setValue("860");
				p_code_citizenship.setSelecteditem("860");
				customer.setCode_resident("1");
				customer.setP_code_citizenship("860");
				p_passport_serial.setMaxlength(2);
				p_passport_number.setMaxlength(7);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			case 7:
				p_passport_number.setMaxlength(12);
				p_passport_serial.setMaxlength(9);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			case 8:
				p_passport_number.setMaxlength(12);
				p_passport_serial.setMaxlength(9);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			case 9:
				customer.setCode_resident(null);
				customer.setP_code_citizenship(null);
				p_passport_number.setMaxlength(12);
				p_passport_serial.setMaxlength(9);
				sendOnRender(p_passport_serial);
				sendOnRender(p_passport_number);
				break;
			}
		}
		if (p_type_document_text==null) {
			ISLogger.getLogger().error("p_type_document_text is null.");
		} 
		p_type_document_text.setValue(value);
		customer.setP_type_document(value);
		binder.loadAll();
	}

	private void sendOnRender(Component component) {
		Events.postEvent(component, new Event("onRender"));
	}

	public void onChange$p_pass_place_region() {
		if (!CheckNull.isEmpty(customer.getP_pass_place_district())) {
			customer.setP_pass_place_district(null);
			p_pass_place_district.setValue(null);
			p_pass_place_district_text.setValue(null);
		}
		p_pass_place_district.setModel(new ListModelList(getDistrictsByRegion(p_pass_place_region.getValue())));
		p_pass_place_region_text.setValue(p_pass_place_region.getValue());
		customer.setP_pass_place_region(p_pass_place_region.getValue());

		setConcatenationForPassplace();
	}

	public void onCheckField(Event event) {
		Checkbox checkBox = (Checkbox) ((ForwardEvent) event).getOrigin().getTarget();
		if (checkBox.getId().equalsIgnoreCase("p_pass_place_checkbox")) {
			if (checkBox.isChecked()) {
				p_pass_place_region.setDisabled(true);
				p_pass_place_district.setDisabled(true);
				p_pass_place_region_text.setDisabled(true);
				p_pass_place_district_text.setDisabled(true);
				p_pass_place_region.setSelecteditem(null);
				p_pass_place_district.setSelecteditem(null);
				p_pass_place_region_text.setValue(null);
				p_pass_place_district_text.setValue(null);
				customer.setP_pass_place_region(null);
				customer.setP_pass_place_district(null);
			} else {
				p_pass_place_region.setDisabled(false);
				p_pass_place_district.setDisabled(false);
				p_pass_place_region_text.setDisabled(false);
				p_pass_place_district_text.setDisabled(false);
			}
		} else if (checkBox.getId().equalsIgnoreCase("sign_public_official")) {
			if (checkBox.isChecked()) {
				customer.setSign_public_official("Y");
			} else {
				customer.setSign_public_official("N");
			}
		} 
	}

	private List<RefData> getDistrictsByRegion(String region_id) {
		if (region_id != null)
			//return ReferenceDictionary.getDistricts(region_id, sessionAttributes.getSchema());
		    return ReferenceDictionary.getDistrByRegion(region_id, sessionAttributes.getSchema());
		return new ArrayList<RefData>();
	}

	private List<RefData> getAllDistricts() {
		    return ReferenceDictionary.getDistricts(sessionAttributes.getSchema());
	}

	private List<RefData> getMahallasByDistr(String distr_id) {
		if (distr_id != null)
			//return ReferenceDictionary.getDistricts(region_id, sessionAttributes.getSchema());
		    return ReferenceDictionary.getMahallasByDistr(distr_id, sessionAttributes.getSchema()); 
		return new ArrayList<RefData>();
	}
	
	public void onChange$p_pass_place_region_text() {
		p_pass_place_district.setModel(new ListModelList(getDistrictsByRegion(p_pass_place_region_text.getValue())));
		p_pass_place_region.setSelecteditem(p_pass_place_region_text.getValue());
		p_pass_place_region_text.setValue(p_pass_place_region.getValue());
		customer.setP_pass_place_region(p_pass_place_region.getValue());
		if (!CheckNull.isEmpty(customer.getP_pass_place_district())) {
			customer.setP_pass_place_district(null);
			p_pass_place_district.setValue(null);
			p_pass_place_district_text.setValue(null);
		}
		// setConcatenationForPassplace();
	}

	public void onChange$p_pass_place_district() {
		customer.setP_pass_place_district(p_pass_place_district.getValue());
		p_pass_place_district_text.setValue(p_pass_place_district.getValue());
		// setConcatenationForPassplace();
	}

	public void onChange$p_pass_place_district_text() {
		p_pass_place_district.setSelecteditem(p_pass_place_district_text.getValue());
		p_pass_place_district_text.setValue(p_pass_place_district.getValue());
		customer.setP_pass_place_district(p_pass_place_district.getValue());
		// sendOnInit(p_pass_place_district);
		// setConcatenationForPassplace();
	}

	public void onAfterRender$p_pass_place_district()
	{
		if (code_distr_pasp!="")
			p_pass_place_district.setSelecteditem(code_distr_pasp);
		code_distr_pasp="";
	}
	
	public void onFocus$p_passport_place_registration() {
		setConcatenationForPassplace();
	}

	public void onChange$p_code_capacity_text(InputEvent event) {
		p_code_capacity.setSelecteditem((String) event.getValue());
		((InputElement) event.getTarget()).setText(p_code_capacity.getValue());
		changeCapacity(p_code_capacity.getValue());
	}

	public void onChange$p_code_capacity() {
		changeCapacity(p_code_capacity.getValue());
	}

	private void changeCapacity(String value) {
		if (value == null)
			return;

		if (value.equals("0803") || value.equals("0804")) {
			p_capacity_status_date.setDisabled(false);
			p_num_certif_capacity.setDisabled(false);
			p_pension_sertif_serial.setDisabled(false);
			p_capacity_status_place.setDisabled(false);
		} else {
			customer.setP_capacity_status_date(null);
			customer.setP_num_certif_capacity(null);
			customer.setP_pension_sertif_serial(null);
			customer.setP_capacity_status_place(null);
			p_capacity_status_date.setValue(null);
			p_num_certif_capacity.setValue(null);
			p_pension_sertif_serial.setValue(null);
			p_capacity_status_place.setValue(null);
			p_capacity_status_date.setDisabled(true);
			p_num_certif_capacity.setDisabled(true);
			p_pension_sertif_serial.setDisabled(true);
			p_capacity_status_place.setDisabled(true);
		}
		p_code_capacity.setSelecteditem(value);
	}

	private void changeSignPublicOfficial(String value) {
		if (value == null){
			sign_public_official.setChecked(false);
			return;
		}
		if (value.equals("Y")) {
			sign_public_official.setChecked(true);
		} else {
			sign_public_official.setChecked(false);
		}
	}

	public void onFocus$p_passport_date_expiration() {
		if (p_type_document.getValue() != null) {
			DateTime dt = null;
			if (p_type_document.getValue().equals("6")) {
				if (p_passport_date_registration.getValue() != null) {
					dt = new DateTime(p_passport_date_registration.getValue());
					dt = dt.plusYears(10);
					dt = dt.minusDays(1);
					p_passport_date_expiration.setValue(dt.toDate());
					customer.setP_passport_date_expiration(dt.toDate());
				}
			}
			// If Document fileType is birth certificate
			// then set expiration date to birth date + 16 years
			if (p_type_document.getValue().equals("7")) {
				if (p_birthday.getValue() != null) {
					dt = new DateTime(p_birthday.getValue());
					dt = dt.plusYears(16);
					p_passport_date_expiration.setValue(dt.toDate());
					customer.setP_passport_date_expiration(dt.toDate());
				}
			}
		}
	}

	private void sendOnInit(Component component) {
		try {
		Events.sendEvent(component, new Event("onInitRender"));
		} catch(Exception e) {
			//ISLogger.getLogger().error("sendOnInit err ."+e.getMessage());
		}
	}

	private void setConcatenationForPassplace() {
		String placeRegistration = String.format("%s %s%s", p_pass_place_region.getText(),
				p_pass_place_district.getText(),
				customer.getP_type_document() != null
						&& (customer.getP_type_document().equals("1") || customer.getP_type_document().equals("6"))
								? " ���" : "");
		customer.setP_passport_place_registration(placeRegistration);
		p_passport_place_registration.setText(placeRegistration);
	}

	public void onChange$p_code_adr_region() {
		customer.setP_code_adr_region(p_code_adr_region.getValue());
		p_code_adr_region_text.setValue(p_code_adr_region.getValue());
		p_code_adr_distr.setValue(null);
		customer.setP_code_adr_distr(null);
		p_code_adr_distr_text.setValue(null);
		p_code_adr_distr.setModel(new ListModelList(getDistrictsByRegion(p_code_adr_region.getValue())));
		binder.loadAll();
		changeCodeTaxByRegion(p_code_adr_region.getValue());
	}

	public void method_p_code_adr_distr() {
		Events.sendEvent(p_code_adr_distr, new Event("onInitRender"));
		if (p_code_adr_distr.getItems().size() == 0
				|| (p_code_adr_distr.getValue() != customer.getP_code_adr_distr())) {
			binder.loadComponent(p_code_adr_distr);
		}
	}

	public void onChange$p_code_adr_region_text(InputEvent event) {
		int size = p_code_adr_region.getModel().getSize();
		String value = event.getValue();
		p_code_adr_distr.setModel(new ListModelList(getDistrictsByRegion(value)));
		p_code_adr_region.setSelecteditem(value);
		p_code_adr_region_text.setValue(p_code_adr_region.getValue());
		customer.setP_code_adr_region(p_code_adr_region.getValue());
		if (!StringUtils.isEmpty(customer.getP_code_adr_distr())) {
			p_code_adr_distr.setValue(null);
			p_code_adr_distr_text.setValue(null);
			customer.setP_code_adr_distr(null);
		}
		changeCodeTaxByRegion(p_code_adr_region.getValue());
		binder.loadAll();
	}

	public void onChange$p_code_adr_distr() {
		String distrId = p_code_adr_distr.getValue();
		customer.setP_code_adr_distr(distrId);
		p_code_adr_distr_text.setValue(distrId);
		// String taxId = ReferenceDictionary.getGniCodeByDistrict(distrId);
		// p_code_tax_org.setSelecteditem(taxId);
		// p_code_tax_org_text.setValue(taxId);
		// customer.setP_code_tax_org(taxId);
		p_code_adr_mahalla.setValue(null);
		customer.setP_code_adr_mahalla(null);
		p_code_adr_mahalla_text.setValue(null);
		p_code_adr_mahalla.setModel(new ListModelList(getMahallasByDistr(p_code_adr_distr.getValue())));
		binder.loadAll();
	}


	public void onChange$p_code_adr_distr_text(InputEvent event) {
		String value = event.getValue();
		p_code_adr_distr.setSelecteditem(value);
		p_code_adr_distr_text.setValue(p_code_adr_distr.getValue());
		customer.setP_code_adr_distr(p_code_adr_distr.getValue());
		// String taxId =
		// ReferenceDictionary.getGniCodeByDistrict(p_code_adr_distr.getValue());
		// p_code_tax_org.setSelecteditem(taxId);
		// p_code_tax_org_text.setValue(taxId);
		// customer.setP_code_tax_org(taxId);
		p_code_adr_mahalla.setModel(new ListModelList(getMahallasByDistr(value)));
		if (!StringUtils.isEmpty(customer.getP_code_adr_mahalla())) {
			p_code_adr_mahalla.setValue(null);
			p_code_adr_mahalla_text.setValue(null);
			customer.setP_code_adr_mahalla(null);
		}
		binder.loadAll();
	}

	public void onChange$p_code_adr_mahalla() {
		String mahallaId = p_code_adr_mahalla.getValue();
		customer.setP_code_adr_mahalla(mahallaId);
		p_code_adr_mahalla_text.setValue(mahallaId);
	}


	public void onChange$p_code_adr_mahalla_text(InputEvent event) {
		String value = event.getValue();
		p_code_adr_mahalla.setSelecteditem(value);
		p_code_adr_mahalla_text.setValue(p_code_adr_mahalla.getValue());
		customer.setP_code_adr_mahalla(p_code_adr_mahalla.getValue());
	}
	
	public void onAfterRender$p_code_adr_distr()
	{
		if (code_distr_adr!="")
			p_code_adr_distr.setSelecteditem(code_distr_adr);
		code_distr_adr="";
	}

	public void method_p_code_adr_mahalla() {
		Events.sendEvent(p_code_adr_mahalla, new Event("onInitRender"));
		if (p_code_adr_mahalla.getItems().size() == 0
				|| (p_code_adr_mahalla.getValue() != customer.getP_code_adr_mahalla())) {
			binder.loadComponent(p_code_adr_mahalla);
		}
	}

	public void onAfterRender$p_code_adr_mahalla()
	{
		if (code_mahalla_adr!="")
			p_code_adr_mahalla.setSelecteditem(code_mahalla_adr);
		code_mahalla_adr="";
	}
	
	private void changeCodeTaxByRegion(String region) {
		p_code_tax_org.setValue(null);
		p_code_tax_org_text.setValue(null);
		customer.setP_code_tax_org(null);
		setModelForCodeTax(region);
	}

	public void onCheck$type_radio_close() {
        //date_close.setDisabled(true);
		customer.setSign_date_close(type_radio_close.getSelectedIndex());
		date_close1.setDisabled(type_radio_close.getSelectedIndex()!=2);
		date_close.setDisabled(type_radio_close.getSelectedIndex()!=1 && type_radio_close.getSelectedIndex()!=2);
    }

	public void onCheck$type_radio_open() {
        //date_open1.setDisabled(true);
		customer.setSign_date_open(type_radio_open.getSelectedIndex());
		date_open1.setDisabled(type_radio_open.getSelectedIndex()!=2);
		date_open.setDisabled(type_radio_open.getSelectedIndex()!=1 && type_radio_open.getSelectedIndex()!=2);
	}

	private void setModelForCodeTax(String region) {
		List<RefData> list = ReferenceDictionary.getGniByRegion(region, sessionAttributes.getSchema());
		p_code_tax_org.setModel(new ListModelList(new ArrayList()));
		p_code_tax_org.setModel(new ListModelList(list));
	}

	public void onFocus$p_post_address() {
		//setConcatenationForAddress();
	}

	public void initDependentComboboxes() {
		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		
		try {
			if (p_pass_place_district != null && p_code_adr_distr != null) {
				
				/*if (customer!=null) 
					ISLogger.getLogger().error("not err customer " + customer);

				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(customer);
				} catch (Exception e22) {
					str1 = "str1=error customer";
				} finally {
				}
				ISLogger.getLogger().error(
						"** not err customer  ************** " + str1);*/

				if (customer.getP_pass_place_region() !=null) 
				p_pass_place_district.setModel(new ListModelList(getDistrictsByRegion(customer.getP_pass_place_region())));
				p_code_adr_distr.setModel(new ListModelList(getDistrictsByRegion(customer.getP_code_adr_region())));
				Events.sendEvent(p_pass_place_district, new Event("onInitRender"));
				Events.sendEvent(p_code_adr_distr, new Event("onInitRender"));
				changeCapacity(customer.getP_code_capacity());
				changeDocumentType(customer.getP_type_document());
				changeSignPublicOfficial(customer.getSign_public_official());
				// setModelForCodeTax(customer.getP_code_adr_region());
				p_code_adr_mahalla.setModel(new ListModelList(getMahallasByDistr(customer.getP_code_adr_distr())));
				Events.sendEvent(p_code_adr_mahalla, new Event("onInitRender"));
				
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	protected void bindBeans(Component comp) {
		customer = new Customer();
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("customer", this.customer);
		binder.loadAll();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
