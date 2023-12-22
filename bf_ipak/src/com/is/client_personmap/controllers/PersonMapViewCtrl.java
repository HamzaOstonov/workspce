package com.is.client_personmap.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ISLogger;
import com.is.client_sap.SapUtil;
import com.lowagie.text.ListItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.is.base.Dao;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.RelationHandler;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapFactory;
import com.is.clients.controllers.renderers.SapSearchRenderer;
import com.is.clients.models.ClientJ;
import com.is.clients.services.ClientDictionaries;
import com.is.clients.services.DictionaryKeeper;
import com.is.clients.services.ServiceFactory;
import com.is.clients.utils.ClientUtil;
import com.is.clients.utils.ModuleMode;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.sap.EmergencyMode;
import com.is.searchSap.SearchResponse;
import com.is.searchSap.SearchSap;
import com.is.searchSap.UserForm;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

import lombok.Getter;
import lombok.Setter;
import relationships.NCI.com.ipakyulibank.BPContpers;
import relationships.NCI.com.ipakyulibank.BPRelResp;
import relationships.NCI.com.ipakyulibank.BPShareholder;
import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

@SuppressWarnings("serial")
public class PersonMapViewCtrl extends GenericForwardComposer {
    private static Logger logger = Logger.getLogger(PersonMapViewCtrl.class);

    private Div grd, frm, search_client;
    private Div contact_person, founders, search_org, beneficiaries;
    private Window legalFounder_wnd, individFounder_wnd, beneficiaryFounder_wnd;
    private Div legalFounder_wnd$legal_div, individFounder_wnd$individ_div, beneficiaryFounder_wnd$beneficiary_div;
    private Window individ_wnd, legal_wnd, benef_wnd;
    private Window dp_wnd;
    private Window founder_type_wnd;
    private Radiogroup founder_type_wnd$type_radio;
    private Tabbox tabs;
    private Tab tab_beneficiaries;
    private Include dp_wnd$incl_cp;
    private Listbox contactPersonList, resultListbox, sap_list;

    private Textbox sap_docId;
    private RefCBox sap_docType;
    private Textbox sap_client_name;
    private RefCBox type_document1;

    private Listbox founders_list, beneficiaries_list;
    private Textbox foundersInfo;

    private ListModelList lmodel = null;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public Person personFromSap = new Person();
    @Getter
    @Setter
    public PersonMap currentCP = new PersonMap();
    @Getter
    @Setter
    private PersonMap currentFounder = new PersonMap();
    
    private PersonMap currentBeneficiary = new PersonMap();
    
    public UserForm bpFilter = new UserForm();
    private String idSap = null;

    private AnnotateDataBinder binder;
    private HashMap<String, String> personKinds;
    private String clientId;
    private String clientInn;
    private String branch;
    private ClientJ client;
    private Map<Integer, String> statesMap;
    // private List<PersonMap> foundersList;
    private ServiceFactory serviceFactory;
    private Dao<PersonMap> personMapDao;
    private Dao<Person> personDao;
    // private Dao<Person> founderPersonDao;
    private Dao<LegalEntity> legalEntityDao;
    private RelationHandler personSync;

    public PersonMapViewCtrl() {
        super('$', false, false);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.currentCP);
        binder.bindBean("currentMap", this.currentFounder);
        binder.bindBean("currentBeneficiary", this.currentBeneficiary);
        binder.bindBean("bpFilter", this.bpFilter);
        binder.loadAll();

        branch = (String) session.getAttribute("branch");
        
        if (!CustomerUtils.isAtaccamaOn()) {
          if (tab_beneficiaries!=null) 
        	  tab_beneficiaries.setVisible(false);
        }
        
        dp_wnd.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                dp_wnd.setVisible(false);
                refresh();
                event.stopPropagation();
            }
        });

    }

    public void init(ServiceFactory factory, ClientJ client) {
        this.serviceFactory = factory;
        this.clientId = client.getId_client();
        this.clientInn = client.getJ_number_tax_registration();
        this.client = client;
        instantiateServices();
        initRenderers();
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabs.setSelectedIndex(0);
        onClick$btn_list();

        EventQueues.lookup("refreshList", EventQueues.SESSION, true).subscribe(new EventListener() {

            @Override
            public void onEvent(Event arg0) throws Exception {
                if (arg0.getName().equals(Events.ON_NOTIFY)) {
                    refresh();
                }
            }
        });
    }

    private void instantiateServices() {
        personKinds = serviceFactory.getDictionaryKeeper().getPersonKindMap();
        personMapDao = serviceFactory.getDaoFactory().getPersonMapDao();
        personDao = serviceFactory.getDaoFactory().getPersonDao();
        legalEntityDao = serviceFactory.getDaoFactory().getLegalEntityDao();
        personSync = RelationHandler.instance(branch);

        sap_docType.setModel(new ListModelList(ClientDictionaries.getSapTypes()));
        type_document1.setModel(new ListModelList(
                DictionaryKeeper.getPassportTypes()));

        statesMap = serviceFactory.getPersonMapService().getStates();
    }

    private void initRenderers() {
        founders_list.setItemRenderer(
                new PersonMapRenderer(serviceFactory.getDaoFactory().getPersonMapDao(), personKinds, statesMap));
        sap_list.setItemRenderer(new SapSearchRenderer());

        contactPersonList.setItemRenderer(
                new PersonMapRenderer(serviceFactory.getDaoFactory().getPersonMapDao(), personKinds, statesMap));

        beneficiaries_list.setItemRenderer(
                new PersonMapRenderer(serviceFactory.getDaoFactory().getPersonMapDao(), personKinds, statesMap));
        
        resultListbox.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem arg0, Object arg1) throws Exception {
                final Person client = (Person) arg1;
                arg0.setValue(client);
                arg0.appendChild(new Listcell(client.getId()));
                arg0.appendChild(new Listcell(client.getIdSap()));
                arg0.appendChild(new Listcell(client.getFamily_local()));
                arg0.appendChild(new Listcell(client.getFirst_name_local()));
                arg0.appendChild(new Listcell(client.getPatronymic_local()));
                arg0.appendChild(new Listcell(client.getBirthday() != null ? df.format(client.getBirthday()) : null));
                arg0.setAttribute("client", client);
                arg0.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
                    @Override
                    public void onEvent(Event arg0) throws Exception {
                        int selIndex = tabs.getSelectedIndex();
                        Person person = ((Person) arg0.getTarget().getAttribute("client"));
                        idSap = person.getIdSap();
                        if (selIndex == 0) {
                            //logger.error("Click result item ");
                            //logger.error("'" + idSap + "'");
                            //logger.error(person);
                            if (idSap != null && !idSap.trim().isEmpty()) {
                                dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_P_SRC + "?clientJId=" + clientId + "&inn="
                                        + clientInn + "&idSap=" + idSap + "&action=createContactPerson");
                                dp_wnd.setVisible(true);
                            } else {
                                dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_P_SRC + "?clientJId=" + clientId + "&inn="
                                        + clientInn + "&personId=" + person.getId() + "&personBranch="
                                        + person.getBranch() + "&position=" + null + "&idSap=" + currentCP.getIdSap()
                                        + "&old=" + currentCP.isOld() + "&action=showContactPerson");
                                dp_wnd.setVisible(true);
                            }
                        } else if (selIndex == 1) {
                            initIndividualFounder(idSap, ModuleMode.CREATION, person);
                        }
                    }
                });
            }
        });
    }

    private void refresh() {
        try {
            //logger.error("PersonMap onRefresh " + branch);
            personMapDao.setFilter(new PersonMap(clientId));
            List<PersonMap> personmapList = personMapDao.getList();
            //logger.error("PersonMap List -> " + personmapList.size());

            List<PersonMap> foundersList = PersonMapUtil.pullFounders(personmapList);
            List<PersonMap> contactList = PersonMapUtil.pullContactPersons(personmapList);
            List<PersonMap> beneficiariesList = PersonMapUtil.pullBeneficiaries(personmapList);
            
            //logger.error("Contact List size -> " + contactList.size());

            contactPersonList.setModel(new BindingListModelList(contactList, true));
            founders_list.setModel(new BindingListModelList(foundersList, true));
            beneficiaries_list.setModel(new BindingListModelList(beneficiariesList, true));
            if (EmergencyMode.isTrue) {
                //founders_list.setModel(new BindingListModelList(foundersList, true));
                //contactPersonList.setModel(new BindingListModelList(contactList, true));
            } else {
                /*BPRelResp[] relations = SapFactory.instance().getRelationService()
						.getRelationsByIdAndBranch(client.getId_client(), branch);
				BusinessOrganizationComplex boc = SapFactory.instance().getOrganizationService()
						.getDetailsByNciId(clientId, branch);
				founders_list.setModel(new BindingListModelList(
						personSync.splitFounders(foundersList, relations, boc.getGeneral().getId_client_sap()), true));
				contactPersonList.setModel(
						new BindingListModelList(personSync.slpitContactPersons(contactList, relations), true));*/
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }
        dp_wnd$incl_cp.setSrc(null);
    }

    public void onClick$btn_refresh() throws Exception {
        refresh();
        onClick$btn_list();
    }

    public void onSelect$tabs() {
        onClick$btn_list();
    }

    // 05-03-2018
    public void onClick$btn_add() {
        if (tabs.getSelectedTab().getId().equals("tab_cp")) {
            dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_P_SRC + "?clientJId=" + clientId + "&inn="
                    + clientInn + "&action=createContactPerson");
            dp_wnd.setVisible(true);
        } else if (tabs.getSelectedTab().getId().equals("tab_founders")) {
            founder_type_wnd.setVisible(true);
        } else if (tabs.getSelectedTab().getId().equals("tab_beneficiaries")) {
        	 initBeneficiaryFounder(null, ModuleMode.CREATION, null);;
        }
    }

    public void onClick$btn_add_from_sap() {
        hideAll();
        String selIndex = tabs.getSelectedTab().getId();
        if (selIndex.equals("tab_cp")) {
            showPersonSearch();
        } else if (selIndex.equals("tab_founders")) {
            founder_type_wnd.setVisible(true);
        }
    }

    public void onClick$btn_list() {
        hideAll();
        if (tabs.getSelectedTab().getId().equals("tab_cp")) {
            contact_person.setVisible(true);
            grd.setVisible(true);
            frm.setVisible(false);
        } else if (tabs.getSelectedTab().getId().equals("tab_founders")) {
            founders.setVisible(true);
        } else if (tabs.getSelectedTab().getId().equals("tab_beneficiaries")) {
        	beneficiaries.setVisible(true);
        }
    }

    public void onDoubleClick$contactPersonList$grd() {
    	String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

        if (currentCP != null) {
            if (contactPersonList.getModel() == null || contactPersonList.getModel().getSize() != 0) {
        		
                dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_P_SRC + "?clientJId=" + clientId + "&inn=" + clientInn
                        + "&personId=" + currentCP.getPerson_id() + "&personBranch=" + currentCP.getBranch()
                        + "&position=" + currentCP.getPerson_kind() + "&idSap=" + currentCP.getIdSap() + "&old="
                        + currentCP.isOld() + "&action=showContactPerson");
                dp_wnd.setVisible(true);
            }
        } //else
        //{
        //	logger.error("not err onDoubleClick$contactPersonList$grd currentCP is null");	
        //}
    }

    public void onDoubleClick$founders_list$founders() {
        if (currentFounder != null) {
            if (currentFounder.getPerson_type() == null)
                return;
            if (currentFounder.getPerson_type().equals("P")) {
                initIndividualFounder(currentFounder.getIdSap(),
                        currentFounder.isFromSap() ? ModuleMode.NCI_CREATE_SAP_EDIT : ModuleMode.EDIT, null);
            } else if (currentFounder.getPerson_type().equals("J")) {
                initLegalFounder(currentFounder.getIdSap(),
                        currentFounder.isFromSap() ? ModuleMode.NCI_CREATE_SAP_EDIT : ModuleMode.EDIT, null);
            }
        }
    }
    
    
    public void onDoubleClick$beneficiaries_list$beneficiaries() {
        if (currentBeneficiary != null) {
            if (currentBeneficiary.getPerson_type() == null)
                return;
            if (currentBeneficiary.getPerson_type().equals("P")) {
            	if (currentBeneficiary.getPerson_kind().equals(PersonMapUtil.PERSONKIND_BENEFICIARY)) 
            		initBeneficiaryFounder(currentBeneficiary.getIdSap(),
                    		currentBeneficiary.isFromSap() ? ModuleMode.NCI_CREATE_SAP_EDIT : ModuleMode.EDIT, null);
            	 else 
            	
                   initIndividualFounder(currentBeneficiary.getIdSap(),
                		currentBeneficiary.isFromSap() ? ModuleMode.NCI_CREATE_SAP_EDIT : ModuleMode.EDIT, null);
                
            } else if (currentBeneficiary.getPerson_type().equals("J")) {
                initLegalFounder(currentBeneficiary.getIdSap(),
                		currentBeneficiary.isFromSap() ? ModuleMode.NCI_CREATE_SAP_EDIT : ModuleMode.EDIT, null);
            }
        }
    }
    

    public void onDoubleClick$sap_list$search_org() throws InterruptedException {
        if (sap_list.getSelectedItem() == null) {
            return;
        }
        if (!EmergencyMode.isTrue) {
            BPSearchResponceOrganization searchResp = (BPSearchResponceOrganization) sap_list.getSelectedItem()
                    .getValue();
            if (searchResp != null) {
                initLegalFounder(searchResp.getId_client_sap(), ModuleMode.CREATION, null);
            }
        } else {
            LegalEntity legalEntity = (LegalEntity) sap_list.getSelectedItem().getValue();
            initLegalFounder(null, ModuleMode.CREATION, legalEntity);
        }
        binder.loadAll();
    }

    public void onClick$btn_find_individual() {
        try {
            List<SearchResponse> list = new ArrayList<SearchResponse>();
            List<Person> personList = new ArrayList<Person>();
            // Disabled search on local database
            /*if (!bpFilter.isEmpty()) {
                Person person = new Person();
                person.setBranch(this.branch);
                person.setBirthday(bpFilter.getBirthday());
                person.setFamily(bpFilter.getLastName());
                person.setFirst_name(bpFilter.getFirstName());
                person.setPatronymic(bpFilter.getMiddleName());
                person.setPassport_serial(bpFilter.getDocumentSerial());
                person.setPassport_number(bpFilter.getDocumentNumber());
                person.setType_document(bpFilter.getDocumentType());
                personDao.setFilter(person);
                personList = personDao.getList();
                resultListbox.setModel(new BindingListModelList(personList, true));
            }*/
            if (!EmergencyMode.isTrue){
                list = SearchSap.getInstance().getSearchResults(bpFilter);
                if (list == null || list.size() == 0) {
                    suggestCreatingMessageBox();
                } else {
                    //resultListbox.setModel(new BindingListModelList(PersonMapUtil.makePersonList(list), true));
                    personList.addAll(PersonMapUtil.makePersonList(list));
                    resultListbox.setModel(new BindingListModelList(personList, true));
                    resultListbox.setVisible(true);
                }
            }
            else{
                Person person = new Person();
                person.setBranch(this.branch);
                person.setBirthday(bpFilter.getBirthday());
                person.setFamily(bpFilter.getLastName());
                person.setFirst_name(bpFilter.getFirstName());
                person.setPatronymic(bpFilter.getMiddleName());
                person.setPassport_serial(bpFilter.getDocumentSerial());
                person.setPassport_number(bpFilter.getDocumentNumber());
                person.setType_document(bpFilter.getDocumentType());
                personDao.setFilter(person);
                personList = personDao.getList();
                resultListbox.setModel(new BindingListModelList(personList, true));
                if (list == null || list.size() == 0) {
                    suggestCreatingMessageBox();
                } else {
                    //resultListbox.setModel(new BindingListModelList(PersonMapUtil.makePersonList(list), true));
                    personList.addAll(PersonMapUtil.makePersonList(list));
                    resultListbox.setModel(new BindingListModelList(personList, true));
                    resultListbox.setVisible(true);
                }
            }
            binder.loadAll();
        } catch (Exception e) {
            alert(e.getMessage());
            logger.error(CheckNull.getPstr(e));
        }
    }

    public void suggestCreatingMessageBox() {
        resultListbox.setModel(new ListModelList());
        try {
            Messagebox.show("Деловых партнеров не найдено. Открыть нового ДП", "", Messagebox.OK | Messagebox.CANCEL,
                    Messagebox.QUESTION, new EventListener() {

                        @Override
                        public void onEvent(Event event) throws Exception {
                            if (event.getName().equals(Messagebox.ON_OK)) {
                                if (tabs.getSelectedTab().getId().equals("tab_cp")) {
                                    dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_P_SRC + "?clientJId=" + clientId + "&inn="
                                            + clientInn + "&action=createContactPerson");
                                    dp_wnd.setVisible(true);
                                } else if (tabs.getSelectedTab().getId().equals("tab_founders")) {
                                    initIndividualFounder(null, ModuleMode.CREATION, null);
                                }

                            }
                        }
                    });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClick$btn_find_legal() {
        if (EmergencyMode.isTrue) {
            LegalEntity legalEntity = new LegalEntity();

            legalEntity.setNumber_tax_registration(sap_docId.getValue());
            legalEntity.setName(sap_client_name.getValue());

            legalEntityDao.setFilter(legalEntity);

            List<LegalEntity> legalEntities = null;
            if (!StringUtils.isEmpty(sap_client_name.getValue()) || !StringUtils.isEmpty(sap_docId.getValue()))
                legalEntities = legalEntityDao.getList();

            if (legalEntities != null && legalEntities.size() > 0) {
                sap_list.setModel(new BindingListModelList(legalEntities, true));
            } else
                suggestLegalEntityMessagebox();
        } else {
            List<BPSearchResponceOrganization> listFromSap = SapFactory.instance().getOrganizationService()
                    .searchOrganization(sap_docId.getValue(), sap_docType.getValue(), sap_client_name.getValue());
            if (listFromSap != null && !listFromSap.isEmpty()) {
                sap_list.setModel(new BindingListModelList(listFromSap, true));
            } else {
                suggestLegalEntityMessagebox();
            }
        }
    }

    public void suggestLegalEntityMessagebox() {
        try {
            Messagebox.show("Деловых партнеров не найдено. Открыть нового ДП?", "", Messagebox.OK | Messagebox.CANCEL,
                    Messagebox.QUESTION, new EventListener() {

                        @Override
                        public void onEvent(Event event) throws Exception {
                            if (event.getName().equals(Messagebox.ON_OK)) {
                                initLegalFounder(null, ModuleMode.CREATION, null);
                            }
                        }
                    });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClick$btn_get_type$founder_type_wnd() {
        founder_type_wnd.setVisible(false);
        if (founder_type_wnd$type_radio.getSelectedItem() == null) {
            return;
        }
        String selItem = founder_type_wnd$type_radio.getSelectedItem().getValue();
        hideAll();
        if (selItem.equals("0")) {
            showPersonSearch();
        } else if (selItem.equals("1")) {
            showFounderSearch();
        }
    }

    public void onClick$btn_clear() {
        onClearForm();
    }

    public void onClick$btn_clear_legal() {
        sap_docId.setText(null);
        sap_docType.setValue(null);
        sap_client_name.setValue(null);
        onClearForm();
    }

    private void onClearForm() {
        resultListbox.setModel(new ListModelList(new ArrayList()));
        sap_list.setModel(new ListModelList(new ArrayList()));
        bpFilter = new UserForm();
        binder.loadAll();
    }

    private void initIndividualFounder(String idSap, ModuleMode mode, Person person) {
        if (individ_wnd == null) {
            individ_wnd = (Window) Executions.createComponents(ClientUtil.PERSON_SRC, individFounder_wnd$individ_div,
                    null);
            individFounder_wnd.addEventListener(Events.ON_CLOSE, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    individFounder_wnd.setVisible(false);
                    arg0.stopPropagation();
                    refresh();
                }
            });
        }
        IndividualFounderComposer individVC = (IndividualFounderComposer) individ_wnd
                .getAttribute("indFoundermain$composer");
        individVC.init(serviceFactory.getDictionaryKeeper(), clientId, clientInn);
        switch (mode) {
            case CREATION:
                if (EmergencyMode.isTrue) {
                    individVC.creation(person);
                } else {
                    if (idSap == null){
                        individVC.creation(person);
                    }
                    else {
                        individVC.creation(idSap);
                    }
                }
                break;
            case EDIT:
                individVC.localView(currentFounder);
                break;
            case NCI_CREATE_SAP_EDIT:
                individVC.sap(currentFounder);
                break;
            default:
                break;
        }
        individFounder_wnd.setVisible(true);
    }

    private void initBeneficiaryFounder(String idSap, ModuleMode mode, Person person) {
        if (benef_wnd == null) {
        	benef_wnd = (Window) Executions.createComponents(ClientUtil.BENEFICIARY_SRC, beneficiaryFounder_wnd$beneficiary_div,
                    null);
        	beneficiaryFounder_wnd.addEventListener(Events.ON_CLOSE, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                	beneficiaryFounder_wnd.setVisible(false);
                    arg0.stopPropagation();
                    refresh();
                }
            });
        }
        
        BeneficiaryFounderComposer benefVC = (BeneficiaryFounderComposer) benef_wnd
                .getAttribute("beneficiarymain$composer");
        benefVC.init(serviceFactory.getDictionaryKeeper(), clientId, clientInn);
        switch (mode) {
            case CREATION:
                if (EmergencyMode.isTrue) {
                	benefVC.creation(person);
                } else {
                    if (idSap == null){
                    	benefVC.creation(person);
                    }
                    else {
                    	benefVC.creation(idSap);
                    }
                }
                break;
            case EDIT:
            	benefVC.localView(currentBeneficiary);
            	benefVC.set_sign_public_official();
                break;
            case NCI_CREATE_SAP_EDIT:
            	benefVC.sap(currentBeneficiary);
                break;
            default:
                break;
        }
        beneficiaryFounder_wnd.setVisible(true);
    }

    private void initLegalFounder(String idSap, ModuleMode mode, LegalEntity legalEntity) {
        if (legal_wnd == null) {
            legal_wnd = (Window) Executions.createComponents(ClientUtil.LEGAL_SRC, legalFounder_wnd$legal_div, null);
            legalFounder_wnd.addEventListener(Events.ON_CLOSE, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    legalFounder_wnd.setVisible(false);
                    arg0.stopPropagation();
                    refresh();
                }
            });
        }
        LegalFounderComposer legalVC = (LegalFounderComposer) legal_wnd.getAttribute("legalFoundermain$composer");
        legalVC.init(serviceFactory.getDictionaryKeeper(), clientId, clientInn);
        switch (mode) {
            case CREATION:
                if (EmergencyMode.isTrue)
                    legalVC.creation(legalEntity);
                else
                    legalVC.creation(idSap);
                break;
            case EDIT:
                legalVC.localView(currentFounder);
                break;
            case NCI_CREATE_SAP_EDIT:
                legalVC.sap(currentFounder);
                break;
            default:
                break;
        }
        legalFounder_wnd.setVisible(true);
    }

    private void hideAll() {
        contact_person.setVisible(false);
        founders.setVisible(false);
        search_org.setVisible(false);
        search_client.setVisible(false);
        beneficiaries.setVisible(false);
    }

    private void showPersonSearch() {
        hideAll();
        search_client.setVisible(true);
        resultListbox.setModel(new ListModelList());
        //bpFilter = new UserForm();
        binder.loadAll();
    }

    private void showFounderSearch() {
        hideAll();
        search_org.setVisible(true);
    }

    private Window sapFoundersWnd;
    private Listbox sapFoundersWnd$founders_list;

    public void onClick$btnShowSAPFounders() {
        try {
            sapFoundersWnd.setVisible(true);

            List<BPRelResp> list = Arrays.asList(SapFactory.instance().getRelationService()
                    .getRelationsByIdAndBranch(client.getId_client(), client.getBranch()));
            sapFoundersWnd$founders_list.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem listitem, Object o) throws Exception {
                    final BPRelResp relation = (BPRelResp) o;
                    if (relation.getBp_relationships().getRel_type()
                            .equalsIgnoreCase(SapEnum.REL_TYPE_FOUNDER.getSapValue())) {
                        final String id = relation.getBp_id_01();
                        BPShareholder shareHolder = relation.getBp_relationships().getShareholder();
                        //alert(relation.getBp_id_01_type());
                        //alert(id);
                        //alert(relation.getBp_id_01_name());
                        listitem.appendChild(new Listcell(relation.getBp_id_01_name()));
                        listitem.appendChild(new Listcell(shareHolder.getCmpy_part_per().toString()));
                        listitem.appendChild(new Listcell(shareHolder.getCmpy_part_amo().toString()));
                        listitem.appendChild(new Listcell(shareHolder.getCmpy_part_cur()));
                        listitem.addEventListener(Events.ON_CLICK, new EventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                if (relation.getBp_id_01_type().equalsIgnoreCase(SapEnum.CLIENT_ORG.getSapValue())) {
                                    initLegalFounder(id, ModuleMode.CREATION, null);
                                } else {
                                    initIndividualFounder(id, ModuleMode.CREATION, null);
                                }
                            }
                        });
                    }
                }
            });
            sapFoundersWnd$founders_list.setModel(new ListModelList(list));
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }
    }

    private Window sapContactPersons;
    private Listbox sapContactPersons$list;

    public void onClick$btnShowSAPContactPersons() {
        try {
            sapContactPersons.setVisible(true);
            final List<BPRelResp> list = Arrays.asList(SapFactory.instance().getRelationService().getRelationsByIdAndBranch(client.getId_client(), client.getBranch()));
            sapContactPersons$list.setItemRenderer(new ListitemRenderer() {
                @Override
                public void render(Listitem listitem, Object o) throws Exception {
                    BPRelResp relation = (BPRelResp) o;
                    String relationFromSAP = relation.getBp_relationships().getRel_type();
                    if ((relationFromSAP.equalsIgnoreCase(SapEnum.REL_TYPE_DIRECTOR.getSapValue())
                            || relationFromSAP.equalsIgnoreCase(SapEnum.REL_TYPE_ACCOUNTANT.getSapValue())) && !StringUtils.isBlank(relation.getBp_id_02())) {
                        BPContpers contactPerson = relation.getBp_relationships().getContact_person();
                        listitem.appendChild(new Listcell(relation.getBp_id_02()));
                        listitem.appendChild(new Listcell(relation.getBp_id_02_name()));
                        listitem.appendChild(new Listcell(personKinds.get(relation.getBp_id_02_type())));
                        listitem.setValue(relation.getBp_id_02());
                        listitem.addEventListener(Events.ON_CLICK, new EventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                String id = (String) ((Listitem) event.getTarget()).getValue();
                                dp_wnd$incl_cp.setSrc(ClientUtil.CLIENT_P_SRC + "?clientJId=" + clientId + "&inn="
                                        + clientInn + "&idSap=" + id + "&action=createContactPerson");
                                dp_wnd.setVisible(true);
                            }
                        });
                    }
                }
            });
            sapContactPersons$list.setModel(new ListModelList(list));
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }
    }

    public UserForm getBpFilter() {
        return bpFilter;
    }

    public void setBpFilter(UserForm bpFilter) {
        this.bpFilter = bpFilter;
    }

	public PersonMap getCurrentBeneficiary() {
		return currentBeneficiary;
	}

	public void setCurrentBeneficiary(PersonMap currentBeneficiary) {
		this.currentBeneficiary = currentBeneficiary;
	}
    
    
}
