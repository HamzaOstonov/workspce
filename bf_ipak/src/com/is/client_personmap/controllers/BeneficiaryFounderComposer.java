
package com.is.client_personmap.controllers;

import java.util.List;

import com.is.client_personmap.dao.PersonDao;
import org.joda.time.DateTime;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import com.is.base.CommonDictionaries;
import com.is.base.Validator;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.client_personmap.PersonMapService;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.PersonValidator;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.Mappers;
import com.is.client_sap.SapFactory;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.services.SapCustomerService;
import com.is.clients.dao.DaoFactory;
import com.is.clients.services.ClientDictionaries;
import com.is.clients.utils.ClientUtil;
import com.is.clients.utils.ModuleMode;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.zkoss.zk.ui.WrongValueException;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.customer_.core.model.PhysicalAutoCompleteModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import relationships.NCI.com.ipakyulibank.BPRelResp;

@SuppressWarnings("serial")
public class BeneficiaryFounderComposer extends AbstractFounderController<Person> {
    private Window fields_diff_wnd;
    private Rows fields_diff_wnd$diff_rows;
    private Checkbox fields_diff_wnd$check_all_sap;
    private Textbox passport_serial, passport_number;
    @SuppressWarnings("unused")
    private RefCBox code_subject, branch, state, type_document, code_nation, code_citizenship, code_resident,
            code_gender, pass_place_region, pass_place_distr, code_country, code_adr_region, code_adr_distr,
            code_capacity, code_bank, code_class_credit, code_tax_org, add_riskdegree, add_state;
    @SuppressWarnings("unused")
    private Datebox passport_date_registration, passport_date_expiration, capacity_status_date, birthday;
    private Textbox passport_place_registration;
    private Textbox type_document_text, pass_place_region_text, pass_place_distr_text, code_citizenship_text, code_country_text,
            code_adr_region_text, code_adr_distr_text, code_tax_org_text;

    private Checkbox pass_place_checkbox;

    private Window match_wnd;
    private Listbox match_wnd$matchesListbox;
	protected static List<RefData> nationMapList = null;
	protected Checkbox sign_public_official;
	protected String code_distr_pasp="";
	protected String code_distr_adr="";

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", current);
        binder.loadAll();
        ses_branch = (String) session.getAttribute("branch");
        alias = (String) session.getAttribute("alias");
        ses_uId = ""+(Integer) session.getAttribute("uid");

        personMapDao = DaoFactory.getInstance(alias).getPersonMapDao();
        founderDao = DaoFactory.getInstance(alias).getPersonDao();
        personMapService = PersonMapService.instance(alias);

        current = PersonMap.benefFounderInstance(ses_branch);

        match_wnd$matchesListbox.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem listitem, Object o) throws Exception {
                Customer person = (Customer) o;
                listitem.appendChild(new Listcell(person.getFullName()));
                listitem.appendChild(new Listcell(CustomerUtils.dateToString(person.getP_birthday())));
                listitem.appendChild(new Listcell(person.getP_type_document()));
                listitem.appendChild(new Listcell(person.getP_passport_serial()));
                listitem.appendChild(new Listcell(person.getP_passport_number()));
                listitem.setValue(person);
                listitem.addEventListener(Events.ON_CLICK, new EventListener() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        Customer value = (Customer) ((Listitem) event.getTarget()).getValue();
                        showChanges(Mappers.mapBpToPerson(value));
                        match_wnd.setVisible(false);
                    }
                });
            }
        });
    }

    @Override
    protected void initCurrentFounder() {
        current.setPerson_kind(PersonMapUtil.PERSONKIND_BENEFICIARY);
        current.setPerson_type(PersonMapUtil.PERSONTYPE_P);
        current.setClient_id(clientId);
        current.setNumber_tax_registration(clientInn);
        current.setBranch(ses_branch);
    }

    @Override
    protected void getFounderFromSap(String idSap) {
        if (idSap == null) {
            return;
        }
        try {
            Customer bpartner = SAPServiceFactory.getInstance().getBusinessPartnerService()
                    .get(null, null, idSap);
            Person person = Mappers.mapBpToPerson(bpartner);
            person.setBranch(ses_branch);
            person.setId(null);
            current.setPerson(person);
            List<Customer> list = SAPServiceFactory.getInstance().
                    getMappingService().getMapping(null, null, idSap);
            //logger.error("List size " + list.size());
            //logger.error("IdSap " + idSap);
            for (Customer bp : list) {
                if (bp.getBranch() != null && bp.getId() != null &&
                        bp.getBranch().equals(ses_branch)
                        && bp.getId().startsWith("A")) {
                    //current.setPerson_id(bp.getId().substring(1));
                    String union_id = bp.getId().substring(1);
                    current.setUnion_id(union_id);
                    current.getPerson().setUnion_id(union_id);
                    current.setBranch(ses_branch);
                    current.setPerson_type(PersonMapUtil.PERSONTYPE_P);
                    PersonDao dao = (PersonDao) DaoFactory.getInstance(alias).getPersonDao();
                    Person localPerson = dao.getByUnionId(ses_branch, union_id);
                    if (localPerson != null
                            && localPerson.getBranch() != null
                            && localPerson.getBranch().equalsIgnoreCase(ses_branch)) {
                        current.setPerson(localPerson);
                        current.setPerson_id(localPerson.getId());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }
    }

    @Override
    protected void renderComboboxes() {
        code_country_text.setValue(current.getPerson().getCode_country());
        code_citizenship_text.setValue(current.getPerson().getCode_citizenship());
        code_resident.setValue(current.getPerson().getCode_resident());
        type_document_text.setValue(current.getPerson().getType_document());
        code_adr_region_text.setValue(current.getPerson().getCode_adr_region());
        code_adr_distr_text.setValue(current.getPerson().getCode_adr_distr());
        pass_place_distr_text.setValue(current.getPerson().getPass_place_district());
        pass_place_region_text.setValue(current.getPerson().getPass_place_region());
        code_tax_org_text.setValue(current.getPerson().getCode_tax_org());
        Events.sendEvent(code_country, new Event("onInitRender"));
        Events.sendEvent(code_citizenship, new Event("onInitRender"));
        Events.sendEvent(code_resident, new Event("onInitRender"));
        Events.sendEvent(type_document, new Event("onInitRender"));
        Events.sendEvent(code_gender, new Event("onInitRender"));
        Events.sendEvent(code_nation, new Event("onInitRender"));
        Events.sendEvent(code_adr_region, new Event("onInitRender"));
        Events.sendEvent(code_adr_distr, new Event("onInitRender"));
        Events.sendEvent(pass_place_region, new Event("onInitRender"));
        Events.sendEvent(pass_place_distr, new Event("onInitRender"));
        Events.sendEvent(code_tax_org, new Event("onInitRender"));
        Events.sendEvent(currency, new Event("onInitRender"));
        if (current.getPerson().getCode_country() == null) {
            current.getPerson().setCode_country("860");
        }
        //onSelect$type_document();
        binder.loadAll();
    }

    @Override
    protected void setModels() {
        currency.setModel(new ListModelList(CommonDictionaries.getCurrencies(null, alias)));
        code_country.setModel(new ListModelList(dictionaryKeeper.getCountries()));
        code_citizenship.setModel(new ListModelList(dictionaryKeeper.getCountries()));
        code_resident.setModel(new ListModelList(dictionaryKeeper.getResidentTypes()));
        type_document.setModel(new ListModelList(dictionaryKeeper.getPassportTypes()));
        code_gender.setModel(new ListModelList(dictionaryKeeper.getGender()));
        code_nation.setModel(new ListModelList(dictionaryKeeper.getNations()));
        code_adr_region.setModel(new ListModelList(dictionaryKeeper.getRegions()));
        code_adr_distr.setModel(new ListModelList(dictionaryKeeper.getDistricts()));
        pass_place_region.setModel(new ListModelList(dictionaryKeeper.getRegions()));
        pass_place_distr.setModel(new ListModelList(dictionaryKeeper.getDistricts()));
        code_tax_org.setModel(new ListModelList(dictionaryKeeper.getGni()));
        
		if (nationMapList == null)
			nationMapList = 
		 RefDataService.getRefData(
				"select trim(t.id) data, trim(t.map) label from ss_national_gcp t order by 1", alias);
        

    }

    @Override
    protected void checkFounderInSap() {
        Customer customer = new Customer();
        customer.setP_type_document(current.getPerson().getType_document());
        customer.setP_passport_number(current.getPerson().getPassport_number());
        try {
            List<Customer> list = SAPServiceFactory.getInstance().getSearchService().search(customer);
            if (!list.isEmpty()) {
                Person sapPerson = Mappers.mapBpToPerson(SAPServiceFactory.getInstance().getBusinessPartnerService().get(null, null, list.get(0).getIdSap()));
                PersonMapUtil.setSapDataIfNull(current.getPerson(), sapPerson);
                renderComboboxes();

            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }

    }

    @Override
    protected void compareFields() {
        Person sapPerson = null;
        try {
            fields_diff_wnd$diff_rows.getChildren().clear();
            if (current.getPerson().getIdSap() != null) {
                sapPerson = SapFactory.instance().
                        getCustomerService()
                        .getDetailsByIdSap(current.getPerson().getIdSap());
            } else {
                /*sapPerson =
                        SapFactory.instance()
                                 .getCustomerService()
                                .getDetailsByIdNci(current.getPerson().getBranch(), current.getPerson().getId());*/
                Customer customer = new Customer();
                customer.setP_type_document(current.getPerson().getType_document());
                customer.setP_passport_serial(current.getPerson().getPassport_serial());
                customer.setP_passport_number(current.getPerson().getPassport_number());
                final List<Customer> list = SapFactory.instance().getCustomerService().searchBusinessPartners(customer);
                if (list != null && list.size() > 1) {
                    Messagebox.show("Нашлись совпадения по документу. Просмотреть?", null, Messagebox.OK | Messagebox.CANCEL, Messagebox.NONE,
                            new EventListener() {

                                @Override
                                public void onEvent(Event event) throws Exception {
                                    if (Messagebox.ON_OK.equalsIgnoreCase(event.getName())) {
                                        match_wnd.setVisible(true);
                                        match_wnd$matchesListbox.setModel(new ListModelList(list));
                                    }
                                }
                            });

                }
                else if (list != null && list.size() == 1){
                    sapPerson = Mappers.mapBpToPerson(list.get(0));
                    sapPerson.setId(null);
                    showChanges(sapPerson);
                }
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }

    }

    private void showChanges(Person person){
        fields_diff_wnd$diff_rows.getChildren().clear();
        PersonMapUtil.showChangesForPerson(current.getPerson(), person, fields_diff_wnd$diff_rows);
        fields_diff_wnd$diff_rows.setAttribute(PersonMapUtil.SAP_PERSON_ATTR, person);
        fields_diff_wnd.setVisible(true);
    }

    public void set_sign_public_official() {
    	sign_public_official.setChecked(current.getPerson().getSign_public_official() != null &&
                    current.getPerson().getSign_public_official().equalsIgnoreCase("Y"));
    }
    
    @Override
    protected void executeFounderAction(final int action) {
        try {
            // Обязательно!!!
            String fullName = current.getPerson().concatenateFullName();
            current.getPerson().setName(fullName);
            current.getPerson().setBranch(ses_branch);//2023.04.12
            current.getPerson().setParent_id_client_j(current.getClient_id()); //2023.04.12
            current.getPerson().setPerson_role(current.getPerson_kind()); //2023.04.12
            current.getPerson().setEmp_id(ses_uId);//2023.04.12
            // 05-03-2018
            boolean isFullCheck = current.getPerson().getState() == PersonMapService.STATE_CONFIRMED;
            Validator<Person> validator =
                    PersonValidator.
                            instance(alias, isFullCheck);
            if (!validator.isValid(current.getPerson())) {
                alert(validator.getMessage());
                return;
            }
            if (action == PersonMapService.ACTION_CREATE ||
                    action == PersonMapService.ACTION_CONFIRM_HOP) {
                current.getPerson().setId(null);
            }
            current.getPerson().setBranch(ses_branch);
            current.getPerson().setSign_public_official( sign_public_official.isChecked() ? "Y" : "N");
            Res res = personMapService.personAction(current.getPerson(), action);

            if (res != null && res.getCode() != 0) {
                alert(res.getName());
                return;
            }
            alert("Успешно!");
            if (Util.inInts(action, PersonMapService.ACTION_CONFIRM, PersonMapService.ACTION_CREATE, PersonMapService.ACTION_CONFIRM_HOP)) {
                current.setPerson(founderDao.getItemByStringId(null, res.getName()));
                current.setPerson_id(current.getPerson().getId());
                current.setUnion_id(current.getPerson().getUnion_id());
            } else {
                current.setPerson(founderDao.getItemByStringId(null, current.getPerson_id()));
                current.setPerson_id(current.getPerson().getId());
                current.setUnion_id(current.getPerson().getUnion_id());
            }
            current.getPerson().setIdSap(current.getIdSap());
            setActionBar();
            setRelationActionBar();
            renderComboboxes();
        } catch (SapCustomerService.FounderDuplicationException e) {
            try {
                final Customer customer = SAPServiceFactory.getInstance()
                        .getBusinessPartnerService()
                        .get(null, null, e.getDuplicationCustomer().getIdSap());
                Messagebox.show("Вы действительно хотите связать текущего клиента с золотой записью",
                        String.format("%s %s", customer.getFullName(),
                                CustomerUtils.dateToString(customer.getP_birthday())),
                        Messagebox.OK | Messagebox.CANCEL, Messagebox.NONE,
                        new EventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                if (Messagebox.ON_OK.equalsIgnoreCase(event.getName())) {
                                    current.getPerson().setIdSap(customer.getIdSap());
                                    executeFounderAction(action);
                                    binder.loadAll();
                                }
                            }
                        });
            } catch (Exception e1) {
                logger.error(CheckNull.getPstr(e));
            }
        }
    }

    public void onClick$btn_apply$fields_diff_wnd() {
        try {
            List<String> sapCheckedFields = ZkUtils.
                    getCheckedRowsByAttribute(
                            fields_diff_wnd$diff_rows,
                            ClientUtil.SAP_COLUMN,
                            ClientUtil.COLUMN_ATTR,
                            ClientUtil.FIELD_ATTR);

            Person fetchedClientFromSap = (Person)
                    fields_diff_wnd$diff_rows.getAttribute(PersonMapUtil.SAP_PERSON_ATTR);
            String unionId = fetchedClientFromSap.getUnion_id();
            String currentUnionId = current.getPerson().getUnion_id();
            String id = current.getPerson().getId();
            PersonMapUtil.setSapData(current.getPerson(), fetchedClientFromSap, sapCheckedFields);

            if (unionId == null) {
                current.getPerson().setId(id);
                current.getPerson().setUnion_id(currentUnionId);
                current.setPerson_id(id);
            } else {
                current.getPerson().setId(id);
                current.setPerson_id(id);
                current.getPerson().setUnion_id(unionId);
            }

            binder.loadAll();
            fields_diff_wnd.setVisible(false);
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }
    }

    public void onChange$type_document_text() {
        current.getPerson().setType_document(type_document_text.getValue());
        type_document.setSelecteditem(type_document_text.getValue());
        onSelect$type_document();
    }

    public void onSelect$type_document() {
        if (type_document.getValue().equals("1")) {
            code_resident.setSelecteditem("1");
            code_citizenship_text.setValue("860");
            code_citizenship.setSelecteditem("860");
            current.getPerson().setCode_resident("1");
            current.getPerson().setCode_citizenship("860");
            Events.postEvent(passport_serial, new Event("onInitRender"));
            Events.postEvent(passport_number, new Event("onInitRender"));
            passport_serial.setMaxlength(2);
            passport_number.setMaxlength(7);
        }
        if (type_document.getValue().equals("2")) {
            code_resident.setSelecteditem("1");
            current.getPerson().setCode_resident("1");
            passport_serial.setMaxlength(9);
            passport_number.setMaxlength(9);
            Events.postEvent(passport_serial, new Event("onInitRender"));
            Events.postEvent(passport_number, new Event("onInitRender"));
        }
        if (type_document.getValue().equals("3")) {
            current.getPerson().setCode_citizenship(null);
            code_citizenship_text.setValue(null);
            code_citizenship.setSelecteditem(null);
        }
        if (type_document.getValue().equals("4")) {
            code_resident.setSelecteditem("2");
            passport_number.setMaxlength(12);
            current.getPerson().setCode_resident("2");
            current.getPerson().setCode_citizenship(null);
            code_citizenship_text.setValue(null);
            code_citizenship.setSelecteditem(null);
            passport_serial.setMaxlength(9);
            //passport_number.setMaxlength(9);
            Events.postEvent(passport_serial, new Event("onInitRender"));
            Events.postEvent(passport_number, new Event("onInitRender"));
        }
        if (type_document.getValue().equals("5")) {
            code_resident.setSelecteditem("1");
            current.getPerson().setCode_resident("1");
            //current.getPerson().setCode_citizenship(null);
            //code_citizenship_text.setValue(null);
            //code_citizenship.setSelecteditem(null);
            passport_serial.setMaxlength(9);
            passport_number.setMaxlength(9);
            Events.postEvent(passport_serial, new Event("onInitRender"));
            Events.postEvent(passport_number, new Event("onInitRender"));
        }
        if (type_document.getValue().equals("6")) {
            code_resident.setSelecteditem("1");
            code_citizenship_text.setValue("860");
            code_citizenship.setSelecteditem("860");
            current.getPerson().setCode_resident("1");
            current.getPerson().setCode_citizenship("860");
            passport_serial.setMaxlength(2);
            passport_number.setMaxlength(7);
            Events.postEvent(passport_serial, new Event("onInitRender"));
            Events.postEvent(passport_number, new Event("onInitRender"));
        }
        if (type_document.getValue().equals("9")) {
        }
        type_document_text.setValue(type_document.getValue());
        current.getPerson().setType_document(type_document.getValue());
    }

    public void onCheck$pass_place_checkbox() {
        pass_place_region.setDisabled(pass_place_checkbox.isChecked());
        pass_place_region_text.setDisabled(pass_place_checkbox.isChecked());
        pass_place_distr.setDisabled(pass_place_checkbox.isChecked());
        pass_place_distr_text.setDisabled(pass_place_checkbox.isChecked());
        if (pass_place_checkbox.isChecked()) {
            current.getPerson().setPass_place_region(null);
            current.getPerson().setPass_place_district(null);
        }
    }

    public void onChange$code_citizenship_text() {
        current.getPerson().setCode_citizenship(code_citizenship_text.getValue());
        current.getPerson().setCode_resident(current.getPerson().getCode_citizenship().equals("860") ? "1" :
                current.getPerson().getCode_resident());
        code_citizenship.setSelecteditem(current.getPerson().getCode_citizenship());
    }

    //	public void onSelect$code_citizenship() {
//		ISLogger.getLogger().error("::::::::::::::::::::::::::::::::::::::::onSelect$code_citizenship");
//		current.getPerson().setCode_resident(code_citizenship.getValue().equals("860") ? "1" : "2");
//		current.getPerson().setCode_citizenship(code_citizenship.getValue());
//		code_citizenship_text.setValue(current.getPerson().getCode_citizenship());
//	}
    public void onChange$code_citizenship() {
        current.getPerson().setCode_citizenship(code_citizenship.getValue());
        current.getPerson().setCode_resident(current.getPerson().getCode_citizenship().equals("860") ? "1" :
                current.getPerson().getCode_resident());
        code_resident.setSelecteditem(current.getPerson().getCode_resident());
        code_citizenship_text.setValue(current.getPerson().getCode_citizenship());
    }

    public void onChange$pass_place_region() {
        pass_place_distr
                .setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, pass_place_region.getValue(), alias)));
        pass_place_region_text.setValue(pass_place_region.getValue());
        current.getPerson().setPass_place_region(pass_place_region.getValue());
    }

    public void onChange$pass_place_region_text() {
        String regionCode = pass_place_region_text.getValue();
        pass_place_region.setSelecteditem(regionCode);
        if (!CheckNull.isEmpty(pass_place_region.getValue())) {
            pass_place_distr.setModel(
                    new ListModelList(ClientDictionaries.getDistrByRegion(null, pass_place_region_text.getValue(), alias)));
            pass_place_region.setSelecteditem(pass_place_region_text.getValue());
            current.getPerson().setPass_place_region(pass_place_region.getValue());
        }
    }

    // Район
    public void onChange$pass_place_distr() {
        pass_place_distr_text.setValue(pass_place_distr.getValue());
        passport_place_registration
                .setText(pass_place_region.getText() + " " + pass_place_distr.getText() + " " + "ИИБ");
        current.getPerson().setPassport_place_registration(
                pass_place_region.getText() + " " + pass_place_distr.getText() + " " + "ИИБ");
        current.getPerson().setPass_place_district(pass_place_distr.getValue());
    }

    public void onChange$pass_place_distr_text() {
        String distrCode = pass_place_distr_text.getValue();
        pass_place_distr.setSelecteditem(distrCode);
        if (!CheckNull.isEmpty(pass_place_distr.getValue())) {
            passport_place_registration.setText(pass_place_region.getText() + " " + pass_place_distr.getText() + " " + "ИИБ");
            current.getPerson().setPassport_place_registration(
                    pass_place_region.getText() + " " + pass_place_distr.getText() + " " + "ИИБ");
            current.getPerson().setPass_place_district(pass_place_distr.getValue());
        }
    }

    // Местожительство

    // Адрес
    public void onChange$code_adr_region() {
        code_adr_distr
                .setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, code_adr_region.getValue(), alias)));
        current.getPerson().setCode_adr_region(code_adr_region.getValue());
        code_adr_region_text.setValue(code_adr_region.getValue());
    }


    public void onChange$code_adr_region_text() {
        String distrCode = code_adr_distr_text.getValue();
        code_adr_distr.setSelecteditem(distrCode);
        if (!CheckNull.isEmpty(code_adr_distr.getValue())) {
            code_adr_distr
                    .setModel(new ListModelList(ClientDictionaries.getDistrByRegion(null, code_adr_region_text.getValue(), alias)));
            code_adr_region.setSelecteditem(code_adr_region_text.getValue());
            current.getPerson().setCode_adr_region(code_adr_region_text.getValue());
        }
    }

    public void onChange$code_adr_distr() {
        String distrId = code_adr_distr.getValue();
        current.getPerson().setCode_adr_distr(distrId);
        code_adr_distr_text.setValue(distrId);
    }

    public void onChange$code_adr_distr_text() {
        String distrCode = code_adr_distr_text.getValue();
        code_adr_distr.setSelecteditem(distrCode);
        if (!CheckNull.isEmpty(code_adr_distr.getValue())) {
            current.getPerson().setCode_adr_distr(distrCode);
            code_adr_distr.setSelecteditem(distrCode);
        }

    }

    public void onFocus$passport_date_expiration() {
        if (type_document.getValue() != null) {
            DateTime dt = null;
            if (type_document.getValue().equals("1")) {
                if (birthday.getValue() != null) {
                    dt = new DateTime(birthday.getValue());
                    dt = dt.plusYears(25);
                    dt = dt.minusDays(1);
                    passport_date_expiration.setValue(dt.toDate());
                    current.getPerson().setPassport_date_expiration(dt.toDate());
                }
            }
            if (type_document.getValue().equals("6")) {
                if (passport_date_registration.getValue() != null) {
                    dt = new DateTime(passport_date_registration.getValue());
                    dt = dt.plusYears(10);
                    dt = dt.minusDays(1);
                    passport_date_expiration.setValue(dt.toDate());
                    current.getPerson().setPassport_date_expiration(dt.toDate());
                }
            }
        }
    }

    public void onCheck$check_all_sap$fields_diff_wnd() {
        ZkUtils.checkAllRows(fields_diff_wnd$diff_rows, fields_diff_wnd$check_all_sap.isChecked());
    }

    @Override
    protected FounderCapital getFounderCapitalForDiff() throws Exception {
        AbstractSapRelationService relationsService = SapFactory.instance().getRelationService();
        BPRelResp[] relationSAP = relationsService.getRelationsByIdAndBranchWithoutPrefix(
                String.format("A%s", current.getPerson().getUnion_id()), current.getBranch());
        return Mappers.mapResponseToCapital(current,
                relationSAP);
    }

    public void creation(Person person) {
        mode = ModuleMode.CREATION;
        current = new PersonMap();
        initCurrentFounder();
        if (person != null && person.getBranch() != null && person.getId() != null) {
            Person localPerson = founderDao.getItemByStringId(person.getBranch(), person.getId());
            current.setPerson(localPerson);
            current.setPerson_id(localPerson.getId());
            current.setBranch(localPerson.getBranch());
        } else
            current.setBranch(ses_branch);
        current.setClient_id(clientId);
        if (current.getCapital().getPart_of_capital() == null) {
            current.getCapital().setPart_of_capital(PersonMapUtil.getFounderPercentageRemainder(clientId, ses_branch, personMapDao));
            current.getCapital().setPart_of_capital_old(current.getCapital().getPart_of_capital());
        }
        renderComboboxes();
        setActionBar();
        setRelationActionBar();
    }
    
    public void onAfterRender$pass_place_distr()
	{
		if (code_distr_pasp!="")
			pass_place_distr.setSelecteditem(code_distr_pasp);
		code_distr_pasp="";
	}

	public void onAfterRender$p_code_adr_distr()
	{
		if (code_distr_adr!="")
			code_adr_distr.setSelecteditem(code_distr_adr);
		code_distr_adr="";
	}
    

    @SuppressWarnings("deprecation")
    private static void sendUrlRequest(String urlString) throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(urlString);

		HttpResponse response = client.execute(request);
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());

		System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
    }
    
    public void onClick$fill_client_form_btn(){
		//logger.error("not err start ");
		
    	try {
        	String number = passport_number.getValue();
        	String seria = passport_serial.getValue();
        	if(number!=null&&!number.equals("")&&seria!=null&&!seria.equals("")){
        		StringBuilder url = new StringBuilder(ConnectionPool.getValue("GetPhysicalServiceUrl")+"?");
        		String branch = (String) session.getAttribute("branch");
        		url.append("bank="+CustomerUtils.getBankType(branch)+"");
        		url.append("&lang=3");
        		url.append("&branch="+branch);
        		url.append("&passport_seria="+seria);
        		url.append("&passport_number="+number);
        		Long id = CustomerUtils.getNextId();
        		url.append("&id="+id);
        		//System.out.println(url.toString());
        		//logger.error("not err url: "+url.toString());   
        		//String str1 = "";
        		//ObjectMapper objectMapper = new ObjectMapper();
        		
        		try {
        			sendUrlRequest(url.toString());
        			PhysicalAutoCompleteModel customerComplete = CustomerUtils.getCustomerModel(id);
        			if(customerComplete!=null){
        				
        				//logger.error("not err "+"ind customerComplete.getLast_name() = "+customerComplete.getLast_name());
        				
        				/*try {
        					str1 = objectMapper.writerWithDefaultPrettyPrinter()
        							.writeValueAsString(current);
        				} catch (Exception e22) {
        					str1 = "str1=error current";
        				} finally {
        				}
        				ISLogger.getLogger().error(
        						"** not err current ***** " + str1);
        				*/
        				current.getPerson().setFamily_local(customerComplete.getLast_name());
        				current.getPerson().setFirst_name_local(customerComplete.getFirst_name());
        				current.getPerson().setPatronymic_local(customerComplete.getPatronym());
        				current.getPerson().setFamily(customerComplete.getSurname());
        				current.getPerson().setFirst_name(customerComplete.getGivenname());
        				if(customerComplete.getGive_place() != null && customerComplete.getGive_place().length() == 5){
        					//String give_place_region = customerComplete.getGive_place().substring(0, 2);
        					//customer.setP_pass_place_region(give_place_region);
        					PhysicalAutoCompleteModel temp = CustomerUtils.getSomeData(customerComplete.getGive_place());
        					pass_place_region_text.setValue(temp.getTemp_region());
        					current.getPerson().setPass_place_region(temp.getTemp_region());
        					if (temp.getTemp_district()!=null) 
            				    code_distr_pasp=temp.getTemp_district();
        					pass_place_distr_text.setValue(temp.getTemp_district());
        					current.getPerson().setPass_place_district(temp.getTemp_district());
        					current.getPerson().setPassport_place_registration(CustomerUtils.toTranslitNew(temp.getGive_place())+" IIB");
        					if (temp.getInn_registrated_gni()!=null){ 
        						code_tax_org_text.setValue(temp.getInn_registrated_gni());
        						current.getPerson().setCode_tax_org(temp.getInn_registrated_gni());
        					}
        				}
        				current.getPerson().setPassport_date_registration(customerComplete.getDate_issue());
        				current.getPerson().setPassport_date_expiration(customerComplete.getDate_expiry());
                        
        				//customer.setP_code_nation(customerComplete.getNationality());
        				if (customerComplete.getNationality()!=null) {
          				  for (int i = 0; i < nationMapList.size(); i++) {
          					  if ( (nationMapList.get(i)).getData().equals(customerComplete.getNationality()) || (nationMapList.get(i)).getData() == customerComplete.getNationality() ) {
          						current.getPerson().setCode_nation(nationMapList.get(i).getLabel());
          						break;
          					  }
          				  }
          				}
        				
        				current.getPerson().setCode_gender(customerComplete.getSex()!=null ? customerComplete.getSex()+"" : null);
        				current.getPerson().setBirthday(customerComplete.getBirth_date());
        				if (customerComplete.getBirth_place()!=null)
        				current.getPerson().setBirth_place(CustomerUtils.toTranslitNew(customerComplete.getBirth_place()));
        				code_adr_region_text.setValue(customerComplete.getDomicile_region());
        				current.getPerson().setCode_adr_region(customerComplete.getDomicile_region());
        				//initModelsForListbox();
    					if (customerComplete.getDomicile_district()!=null) 
        				    code_distr_adr=customerComplete.getDomicile_district();

    					code_adr_distr_text.setValue(customerComplete.getDomicile_district());
    					current.getPerson().setCode_adr_distr(customerComplete.getDomicile_district());
        				String place_desc = customerComplete.getDomicile_place_desc() == null ? "" : customerComplete.getDomicile_place_desc();
        				String street_desc = customerComplete.getDomicile_street_desc() == null ? "" : customerComplete.getDomicile_street_desc();
        				String address = customerComplete.getDomicile_address() == null ? "" : customerComplete.getDomicile_address();
        				address += customerComplete.getDomicile_house() == null ? "" : ", " +customerComplete.getDomicile_house();
        				address += customerComplete.getDomicile_flat() == null ? "" : ", " +customerComplete.getDomicile_flat();
        				
        				current.getPerson().setPost_address(CustomerUtils.toTranslitNew(place_desc + " " + street_desc + " " + address));
        				current.getPerson().setNumber_tax_registration(customerComplete.getInn());
        				
        				current.getPerson().setInps(customerComplete.getPin());
        				//current.getPerson().setPinfl(customerComplete.getPin());
        				binder.loadAll();
        			} 
        		} catch (Exception e) {
					e.printStackTrace();
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
				
        	} else alert("Серия или номер паспорта не заполнены");
		} catch (WrongValueException e) {
			alert("Серия или номер паспорта не заполнены");
			e.printStackTrace();
		}
    }
    

}
