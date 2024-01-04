package com.is.clients.controllers;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.is.client_personmap.PersonMapService;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.Client;
import com.is.clients.services.ClientJAttachmentService;
import com.is.clients.services.ClientJService;
import com.is.delta_relations.DeltaRelation;
import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.*;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.account.AccountViewCtrl;
import com.is.base.Validator;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.client_personmap.controllers.PersonMapViewCtrl;
import com.is.client_personmap.model.Person;
import com.is.client_sap.Mappers;
import com.is.client_sap.RelationHandler;
import com.is.client_sap.SapFactory;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_spec.SpecCltViewCtrl;
import com.is.clients.BfModules;
import com.is.clients.controllers.renderers.AttachmentRenderer;
import com.is.clients.controllers.renderers.ClientRenderer;
import com.is.clients.controllers.renderers.HistoryRenderer;
import com.is.clients.controllers.renderers.SapSearchRenderer;
import com.is.clients.ebp.EbpMappers;
import com.is.clients.ebp.EbpService;
import com.is.clients.models.ClientJ;
import com.is.clients.models.ResInn;
import com.is.clients.sap.SapHandler;
import com.is.clients.services.ClientDictionaries;
import com.is.clients.services.ServiceFactory;
import com.is.clients.utils.ClientUtil;
import com.is.clients.validation.CheckClient;
import com.is.customer_.action.ActionImages;
import com.is.customer_.attachments.Attachment;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.entrepreneur.IndividualEnterpreneur;
import com.is.customer_.sap.EmergencyMode;
import com.is.nibbd.NibbdController;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.util.NibbdQueries;
import com.is.nibbd.util.NibbdUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

import client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

@SuppressWarnings("serial")
public class ClientJViewCtrl extends AbstractClientController {
    private static Logger logger = Logger.getLogger(ClientJViewCtrl.class);
    private static final boolean isDebugEnabled = true;

    private AbstractSapOrganizationService<ClientJ> organizationService;
    private AbstractSapOrganizationService<ClientJ> sapOrganizationService;
    private AbstractSapOrganizationService<ClientJ> localOrganizationService;

    enum CltPaths {
        LEGAL_ENTITY("Юридический"),
        // IP("Индивидуальный предприниматель"),
        LEGAL_ENTITY_NIBBD("Юридический, имеющий основной счет в другом банке");
        // IP_ENTITY_NIBBD("Индивидуальный предприниматель, имеющий основной
        // счет в другом банке");

        private String desc;

        private CltPaths(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    private Window dp_wnd, wind_approve;
    private Include dp_wnd$incl_cp;
    private Textbox account, currency, id_order, type_close_id, id_doc, acc, inn, pinfl;
    private RefCBox type_close_name;
    private Datebox date_doc;
    private Row wind_approve$innRow, wind_approve$pinRow;

    private ServiceFactory serviceFactory;

    private EventQueue eq;

    private String err_msg;

    public ClientJViewCtrl() {
        super();
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
        userId = (Integer) session.getAttribute("uid");

        serviceFactory = ServiceFactory.getInstance(alias, un, pw);
        eq = EventQueues.lookup("IndividualEnterpreneurEQ", EventQueues.DESKTOP, true);
        initServices();

        if (mode == null) {
            initRenderers();
            // refreshModel(_startPageNumber, true); //Хамза, заремил в
            // 24,11,2017.
            // потому что вытягивает все клиенты при запуске страницы, если надо
            // будет потом можно выташить по определенному филтру
            hideAll();
            grd.setVisible(true);
            setAvailableTabs(userId);
        } else if (mode.equals(ClientUtil.MODE_DELTA)) {
            initDeltaMode();
        } else if (mode.equals(ClientUtil.MODE_NIBBD)) {
            parameter = (String[]) param.get("queryOut");
            if (parameter != null) {
                initForNibbd(parameter[0]);
            }
        }
        // кнопка НИББД
        Connection c = null;
        try {
            c = ConnectionPool.getConnection(alias);
            if (!ClientUtil.isModuleAllowed(c, "TNibbdForm", un))
                btn_nibbd.setVisible(false);
        } catch (SQLException e) {
            logger.error(e.getStackTrace());
        } finally {
            ConnectionPool.close(c);
        }
        //
        ClientJ cltemp = new ClientJ();
        cltemp.setSign_registr(1);
        cltemp.setState("0");
        Map<Integer, String> checkActionToOpen = dictionaryKeeper.getAvailableActionsMap(cltemp, un, pw, alias);
        btn_new.setVisible(checkActionToOpen.containsKey(1));
        // onClick$btn_show_search();
        onClick$btn_show_search_sap();
    }

    private void setAvailableTabs(int userId) {
        int[] moduleIds = clientService.getAvailableModules(userId);
        if (moduleIds == null || moduleIds.length == 0) {
            return;
        }
        cl_add_tab.setVisible(Util.inInts(BfModules.ADDINFO.getModuleId(), moduleIds));
        acc_tab.setVisible(Util.inInts(BfModules.ACCOUNT.getModuleId(), moduleIds));
        specclt_tab.setVisible(Util.inInts(BfModules.SPEC_CLT.getModuleId(), moduleIds));
    }

    private void initServices() {
        dictionaryKeeper = serviceFactory.getDictionaryKeeper();
        dictionaryKeeper.initLists();
        clientService = serviceFactory.getClientJService();
        clientDao = serviceFactory.getDaoFactory().getClientDao();
        organizationService = SapFactory.instance().getOrganizationService();
        sapOrganizationService = SapFactory.instance().getSapOrganizationService();
        localOrganizationService = SapFactory.instance().getLocalOrganizationService();
        initSearchComposer();
        drawRadioButtons();
        setModels();
    }

    private void drawRadioButtons() {
        Radio radio = null;
        for (int i = 0; i < CltPaths.values().length; i++) {
            CltPaths path = CltPaths.values()[i];
            Div d = new Div();
            d.setStyle("padding-top: 5px;padding-bottom: 5px;");
            radio = new Radio(path.getDesc());
            // radio.setChecked(i==0);
            radio.setValue(path.toString());
            radio.addEventListener(Events.ON_OK, new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    onClick$btn_choose$clt_dlg_wnd();
                }
            });
            d.appendChild(radio);
            clt_dlg_wnd$paths.appendChild(d);
            if (i == 0)
                clt_dlg_wnd$paths.setSelectedItem(radio);
        }
    }

    private void initDeltaMode() {
        hideAll();
        top_tb.setVisible(false);
        String[] id_client = (String[]) param.get("client_id");

        filter.setId_client(id_client[0]);
        refreshModel(0, false);
        if (dataGrid.getItems().size() == 0) {
            alert("Клиент не найден!");
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
        // Events.sendEvent(new Event(Events.ON_SELECT, acode_type));
        Events.sendEvent(new Event(Events.ON_CHANGE, acode_type));

        aj_code_bank.setDisabled(false);
        top_tb.setVisible(false);
        addgrd.setAttribute("mode", "new");
        btn_save.setAttribute("action", "open");
        tb.setVisible(true);
        btn_clear.setVisible(false);
    }

    private void initRenderers() {
        dataGrid.setItemRenderer(new ClientRenderer(clientStates, clientTypes));
        history.setItemRenderer(new HistoryRenderer());
        sap_list.setItemRenderer(new SapSearchRenderer());
        // atachments.setItemRenderer(new AttachmentRenderer(attachMap));
        String schema = (String) session.getAttribute("alias");
        // final AttachmentRenderer attachmentRenderer = new
        // AttachmentRenderer(schema);
        final AttachmentRenderer attachmentRenderer = new AttachmentRenderer(schema, clientmain, sapOrganizationService,
                localOrganizationService);
        atachments.setItemRenderer(attachmentRenderer);
        // if (current != null)
        // attachmentRenderer.setClientJ(current);
    }

    private void initCLientFromNibbd(String nibbdQueryOut) {
        String[] tilda = nibbdQueryOut.split("~");
        String[] fields = tilda[2].split(";");

        newcl = new ClientJ();

        newcl.setBranch(ses_branch);
        newcl.setSign_registr(ClientUtil.SIGN_REGISTR_PRIMARY_NOT);

        newcl.setCode_type(clientLettersMap.get(fields[10]));
        newcl.setId_client(fields[3]);
        newcl.setName(fields[4]);
        newcl.setJ_number_registration_doc(fields[7]);
        newcl.setJ_place_regist_name(fields[8]);
        Events.sendEvent(new Event("onInitRender", acode_country));
        Events.sendEvent(new Event("onInitRender", acode_type));
        if (newcl.getCode_type().equals("11")) {
            if (fields[2].length()==14) {
            	newcl.setP_pinfl(fields[2]);	
            } else {
        	newcl.setP_number_tax_registration(fields[2]);
            }
            newcl.setP_code_citizenship(fields[6]);
            newcl.setP_code_adr_region(fields[22]);
            newcl.setP_code_adr_distr(fields[23]);
            newcl.setCode_resident(NibbdUtils.convertResident(fields[5], false));
            Events.sendEvent(new Event("onInitRender", ap_code_citizenship));
            Events.sendEvent(new Event("onInitRender", acode_resident1));
            Events.sendEvent(new Event("onInitRender", ap_code_adr_region));
            Events.sendEvent(new Event("onInitRender", ap_code_adr_distr));
        }
        if (fields[2].length()==14) {} else {
        newcl.setJ_number_tax_registration(fields[2]);
        }
        newcl.setCode_country(fields[6]);
        newcl.setCode_resident(NibbdUtils.convertResident(fields[5], false));
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
        // Не проставлять окед для ИП
        if (!newcl.isIP())
            newcl.setJ_code_sector(fields[17]);
        else
            newcl.setJ_code_sector(null);
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
                initPersons();
                break;
            case 2:
                incl_control.setSrc("clientaddinfo.zul?branch=" + ses_branch + "&client_id=" + current.getId_client()
                        + "&code_subject=J&alias=" + alias);
                break;
            case 3:
                initAccounts();
                break;
            case 4:
                initSpecclt();
                break;
            case 5:
                refreshAtachmentList();
            /*
			 * { //onClick$btn_getFile(); appsDiv.getChildren().clear(); appsWnd
			 * = (Window) Executions.createComponents("/customer/apps.zul",
			 * appsDiv, null); appsWnd.setClosable(true);
			 * appsWnd.setVisible(true); Events.sendEvent("onUploadApps",
			 * appsWnd, current); //hamza 22.10.2017 }
			 */
                break;
            case 6:
                history.setModel(new ListModelList(
                        ClientDictionaries.getClientHistory(current.getId_client(), current.getBranch(), alias)));
                break;
            case 7:
            	//initClAddMy();
            	
            	includeAdditionalClientInfo.setSrc("clientcrmaddinfo.zul?client_id=" + current.getId_client() + "&branch="
        				+ ses_branch + "&alias=" + alias);
            	
                //incl_control.setSrc("clientaddinfo.zul?branch=" + ses_branch + "&client_id=" + current.getId_client()
                //        + "&code_subject=J&alias=" + alias);
                
                break;
            default:
                break;
        }
    }

    public void onClick$btn_new() {
        mode = null;
        newcl = new ClientJ();
        clt_dlg_wnd$paths.setSelectedIndex(0);
        clt_dlg_wnd.setVisible(true);
        clt_dlg_wnd$paths.setFocus(true);
        // initNewClient();
        // aj_small_business.setChecked(false);
        // aj_sign_trade.setChecked(false);
    }

    public void onClick$btn_choose$clt_dlg_wnd() {
        if (clt_dlg_wnd$paths.getSelectedItem() == null)
            return;
        boolean foundInSAP = isFoundInSAP();
        String selectedPath = clt_dlg_wnd$paths.getSelectedItem().getValue();
        switch (CltPaths.valueOf(selectedPath)) {
            case LEGAL_ENTITY:
                if (foundInSAP)
                    openClient(1);
                else
                    initNewClient();
                break;
            case LEGAL_ENTITY_NIBBD:
                if (foundInSAP)
                    openClient(50);
                else
                    initNibbd(NibbdQueries.IDENTIFICATION);
                break;
            default:
                break;
        }
        clt_dlg_wnd.setVisible(false);
    }

	/*
	 * public void onOK$div_radio_grp$clt_dlg_wnd() {
	 * onClick$btn_choose$clt_dlg_wnd(); }
	 */

	/*
	 * public void onOK$paths$clt_dlg_wnd() { onClick$btn_choose$clt_dlg_wnd();
	 * }
	 */
	/*
	 * public void onOK$clt_dlg_wnd() { onClick$btn_choose$clt_dlg_wnd(); }
	 */

    public boolean isFoundInSAP() {
        String action = (String) btn_save.getAttribute("action");
        return action != null && action.equalsIgnoreCase("open_sap");
    }

    public void onClick$btn_save() throws Exception {
        String action = (String) btn_save.getAttribute("action");
        if (action.equals("open")) {
            onClick$btn_open();
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

    public void onClick$btn_get_ip() {
        eq.subscribe(new EventListener() {

            @Override
            public void onEvent(Event arg0) throws Exception {
                if (!self.getPage().isAlive()) {
                    eq.unsubscribe(this);
                    return;
                }
                if (arg0.getName().equals(Events.ON_NOTIFY)) {
                    newcl.setIndividualEnterpreneurClean((IndividualEnterpreneur) arg0.getData());
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

    public void onClick$btn_find_sap() {
        sapDocId = sap_docId.getValue();
        List<BPSearchResponceOrganization> listFromSap = organizationService.searchOrganization(sapDocId,
                sap_docType.getValue(), sap_client_name.getValue());
        if (listFromSap != null && !listFromSap.isEmpty()) {
            sap_list.setModel(new BindingListModelList(listFromSap, true));
        } else {
            sap_list.setModel(new ListModelList());
            alert("Поиск не дал результатов");
        }
    }

    public void onClick$btn_find_ip() {
        if (SapHandler.findBpForIP(newcl)) {
            alert("found bp!");
            binder.loadAll();
        }
    }

    public void onDoubleClick$sap_list$sap() {
        if (sap_list.getModel() == null) {
            return;
        }
        BPSearchResponceOrganization searchResp = (BPSearchResponceOrganization) sap_list.getSelectedItem().getValue();
        ZkUtils.clearForm(frm);

        BusinessOrganizationComplex sapClient = organizationService.getDetailsBySapId(searchResp.getId_client_sap());
        if (sapClient != null && sapClient.getAttachments() != null) {
            // atachments.setModel(new
            // BindingListModelList(Arrays.asList(sapClient.getAttachments()),
            // true));
            atachments.setModel(new BindingListModelList(convertToLocalAttachments(sapClient.getAttachments()), true));
        }
        ClientJ fetchedFromSap = Mappers.mapToClientJ(sapClient);
        // filter = new ClientJFilter(); //хамза, комментировал в 21,11,2017
        filter.clearFilterFields();// новый метод от 21,11,2017
        filter.setJ_number_tax_registration(fetchedFromSap.getJ_number_tax_registration());
        if (fetchedFromSap.getJ_number_tax_registration() == null
                || fetchedFromSap.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
            filter.setId_client(sapClient.getOrganization().getNibdd_id());
        }
        if (fetchedFromSap.getJ_number_tax_registration() == null
                && sapClient.getOrganization().getNibdd_id() == null) {
            filter.setJ_number_tax_registration("no inn");
        }
        refreshModel(0, true);
        if (dataGrid.getItems().size() > 0) {
            onDoubleClick$dataGrid$grd();
            current.setId_sap(fetchedFromSap.getId_sap());
            current.setId_client_in_sap(fetchedFromSap.getId_client());
            if (current.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
                SapHandler.fetchIp(current, sapClient.getGeneral().getId_client_sap());
            }
            try {
                RelationHandler.instance(ses_branch).fetchDirectors(sapClient.getBp_list(), newcl);
            } catch (Exception e) {
                // alert(e.getMessage());
            }
        } else {
            setNewcl(fetchedFromSap);

            if (newcl.getCode_type() != null && newcl.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
                showIpView_add(true);
                SapHandler.fetchIp(newcl, sapClient.getGeneral().getId_client_sap());
            } else {
                try {
                    RelationHandler.instance(ses_branch).fetchDirectors(sapClient.getBp_list(), newcl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Events.sendEvent(new Event(Events.ON_SELECT, acode_type));
            // Events.sendEvent(new Event(Events.ON_CHANGE, acode_type));
            initNewClient();
            tb.setVisible(false);
        }

        binder.loadAll();
        // tb.setVisible(true);
        btn_save.setLabel(Labels.getLabel("save"));
        btn_save.setAttribute("action", "open_sap");
    }

    public void onClick$btn_getFile() {
        refreshAtachmentList();
    }

    private ClientJAttachmentService clientJAttachmentService = new ClientJAttachmentService();

    public void onClick$btn_deleteFile() throws InterruptedException {
        Messagebox.show("Вы действительно хотите выполнить удалить документ :" ,"",
                Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

                    @Override
                    public void onEvent(Event event) throws Exception {
                        if (event.getName().equals(Messagebox.ON_OK)) {
                            Listitem item = atachments.getSelectedItem();
                            Attachment attachment = (Attachment) item.getValue();
                            clientJAttachmentService.delete(attachment.getUrl());
                            refreshAtachmentList();
                        }
                    }
                });
    }

    private List<Attachment> convertToLocalAttachments(
            client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment[] attachments) {
        List<Attachment> attachmentList = new ArrayList<Attachment>();
        if (attachments != null)
            for (int i = 0; i < attachments.length; i++) {
                BPAttachmentsAttachment bpAttachment = attachments[i];
                Attachment attachment = new Attachment();
                attachment.setDoc_type(bpAttachment.getType());
                attachment.setDoc_date(bpAttachment.getDoc_date());
                attachment.setDoc_number(bpAttachment.getDoc_number());
                attachment.setFileName(
                        bpAttachment.getFilename() == null ? bpAttachment.getName() : bpAttachment.getFilename());
                attachment.setDescription(bpAttachment.getDescription());
                attachment.setUrl(bpAttachment.getURL());
                attachment.setCreatedAt(CustomerUtils.toDate(bpAttachment.getCreated_at()));
                attachmentList.add(attachment);
            }
        return attachmentList;
    }

	/*
	 * public void onUpload$btn_sendFile(UploadEvent event) { try { Media media
	 * = event.getMedia(); if (CheckNull.isEmpty(attch_types.getValue())) {
	 * Messagebox.show("Выберите тип документа"); return; } if (media.getName()
	 * == null || media.getName().length() >= 18) { Messagebox.show(
	 * "Длина имени файла не должна быть больше либо равно 18 знакам: " +
	 * media.getName()); return; } if (!FileUtil.isValidName(media.getName())) {
	 * Messagebox.show("Неверное название файла"); return; } if (media != null)
	 * { byte[] data =
	 * org.apache.commons.io.IOUtils.toByteArray(media.getStreamData());
	 * 
	 * BPAttachmentsAttachment[] attachments = {new BPAttachmentsAttachment(
	 * data, null, attch_types.getValue(), media.getName(), media.getName(),
	 * null, null, null, null)};
	 * 
	 * organizationService.sendAttacments(current, attachments); } } catch
	 * (Exception e) { e.printStackTrace(); alert(e.getMessage()); return; }
	 * refreshAtachmentList(); alert("Файл Загружен"); }
	 */

    public void onChange$attch_types(InputEvent event) {
        RefCBox rcombobox = (RefCBox) event.getTarget();
        if (event.getValue() == null || event.getValue().isEmpty())
            btn_sendFile.setDisabled(true);
        else
            btn_sendFile.setDisabled(false);
    }

    public void onUpload$btn_sendFile(UploadEvent event) throws Exception {
        Media media = event.getMedia();
        if (CheckNull.isEmpty(attch_types.getValue())) {
            Messagebox.show("Выберите тип документа");
            return;
        }
        if (CheckNull.isEmpty(documentDate.getValue())) {
            Messagebox.show("Выберите дата");
            return;
        }

        // if (media.getName() == null || media.getName().length() >= 18) {
        // Messagebox.show("Длина имени файла не должна быть больше либо равно
        // 18 знакам: " + media.getName());
        // return;
        // }
        // if (!FileUtil.isValidName(media.getName())) {
        // Messagebox.show("Неверное название файла");
        // return;
        // }
        if (media != null) {
            byte[] data = org.apache.commons.io.IOUtils.toByteArray(media.getStreamData());

            BPAttachmentsAttachment[] attachments = {new BPAttachmentsAttachment(data, description.getValue(),
                    attch_types.getValue(), media.getName(), media.getName(), null, null, documentDate.getValue(),
                    CustomerUtils.toCalendar(new Date()))};

            if (!EmergencyMode.isTrue) {
                sapOrganizationService.sendAttacments(current, attachments);
            }
            // local save attachments
            localOrganizationService.sendAttacments(current, attachments);

            ZkUtils.clearForm(attch_types);
            ZkUtils.clearForm(documentDate);
            ZkUtils.clearForm(description);

        }
        refreshAtachmentList();
        Messagebox.show("Файл Загружен");
    }

    public void onClick$btn_nibbd() {
        initNibbd(null);
    }
    
//	--  Кнопка "Утвердить"
	public void onClick$btn_approve() {
		type_close_name.setModel(new ListModelList(ClientJService.getCloseType(alias)));
		
		if(current.getName() != null) {
			wind_approve.setVisible(true);
		} else {
			wind_approve.setVisible(false);
		}
		
	}
	
//	--  Кнопка - 'Запросить НИББД'
	@SuppressWarnings("unused")
	public void onClick$btn_send$wind_approve() {
		
//		ClientJ clientJ = new ClientJ();
		String clientJ = null;
		ResInn resInn = null;
		List<ResInn> resList = new ArrayList<ResInn>();
		String strInn = "";

		try {
			
			if (current.getCode_type().equals("11")) {
				clientJ = current.getP_pinfl();
			} else {
				clientJ = current.getJ_number_tax_registration();
			}
			resInn = ClientJService.sendInn(clientJ, un, pw);
			resList.add(resInn);
			strInn = "" +resList;
			if (resInn.getCode().equals("02000")) {
				alert(strInn);
			} else if (resInn == null) {
				alert("Нет соединение с адресом: " + ClientJService.getUrl());
			} else {
				alert("Код: " + resInn.getCode() + " Cообщение: " + resInn.getMessage());
				ISLogger.getLogger().error("ResInn objectMapper.readValue. content: " + resInn.getCode());
				ISLogger.getLogger().error("ResInn objectMapper.readValue error: " + resInn.getMessage());
			}
			
//			if(account.getValue() == null){
//			    alert("Пустой ввод не разрешен в поле '''Код счета клиента''' ");
//			} else if(currency.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Валюта''' ");
//			} else if(id_order.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Порядковый номер счета''' ");
//			} else if(type_close_id.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Вид закрытия''' ");
//			} else if(type_close_name.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Вид закрытия''' ");
//			} else if(id_doc.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Номер документа основания''' ");
//			} else if(date_doc.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Дата документа основания''' ");
//			} else if(acc.getValue() == null) {
//				alert("Пустой ввод не разрешен в поле '''Лицевой счет''' ");
//			}
		} catch (Exception e) {
			alert("Нет соединение с адресом: " + ClientJService.getUrl()+strInn);
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		}

	}
	
//	--  Меню кнопка - 'Идентификация субъекта(ЮЛ) по ИНН'
	public void onClick$btn_subInn() {
		wind_approve.setVisible(true);
		wind_approve$pinRow.setVisible(false);
	}
	
//	--  Меню кнопка - 'Идентификация субъекта(ЮЛ) по ПИНФЛ'
	public void onClick$btn_subPinfl() {
		if(current.getCode_type().equals("11")) {
			wind_approve.setVisible(true);
			wind_approve$innRow.setVisible(false);
		} else {
			wind_approve.setVisible(false);
			alert("Этот пункт для клиентов ЯТТ");
		}
		
	}

    public void onClick$btn_fetch_ebp() {
        if (CheckNull.isEmpty(newcl.getCode_type())) {
            alert("Введите тип клиента");
            return;
        }

        ClientJ ebpClient = null;
        String codeType = newcl.getCode_type();

        try {
            if (codeType.equals(ClientUtil.CODE_TYPE_IP)) {
                //ebpClient = EbpMappers
                //        .mapIndividualDetails(EbpService.individualDetails(null, newcl.getJ_number_tax_registration()));

                if (CheckNull.isEmpty(newcl.getP_pinfl())) {
                    alert("Введите ПИНФЛ");
                    return;
                }
            	
            	ebpClient = EbpMappers
                        .mapIndividualDetails(EbpService.individualDetails(null, newcl.getP_pinfl() ));
            	
                // binder.loadAll();
            } else {

                if (CheckNull.isEmpty(newcl.getJ_number_tax_registration())) {
                    alert("Введите ИНН");
                    return;
                }
            	
            	ebpClient = EbpMappers.mapLegalEntityDetails(
                        EbpService.legalEntityDetails(null, newcl.getJ_number_tax_registration()));
            }
            if (ebpClient != null) {
                // Сохраняем старые значения
                String code_organ_direct = newcl.getJ_code_organ_direct();
                String code_country_address = newcl.getAddressCountry();
                String pinfl = newcl.getP_pinfl();                
          logger.error("ebpClient.getP_pinfl="+ebpClient.getP_pinfl() +", ebpClient.getJ_number_tax_registration="+ebpClient.getJ_number_tax_registration() );
                newcl.setClientData(ebpClient);
                newcl.setCode_type(codeType);
                newcl.setBranch(ses_branch);
                if (newcl.getJ_code_bank() == null) {
                    newcl.setJ_code_bank(ses_branch);
                }
                // newcl.setSign_registr(ClientUtil.SIGN_REGISTR_PRIMARY);
                aj_soato.setValue(newcl.getJ_soato());
                Events.sendEvent(new Event(Events.ON_CHANGE, aj_soato));
                newcl.setJ_post_address(ebpClient.getJ_post_address());
                // newcl.setP_family(ebpClient.getP_family());
                // newcl.setP_last_name_cyr(ebpClient.getP_last_name_cyr());
                newcl.setJ_code_organ_direct(code_organ_direct);
                newcl.setAddressCountry(code_country_address);
           logger.error("newcl.getP_pinfl="+newcl.getP_pinfl() +", newcl.getJ_number_tax_registration="+newcl.getJ_number_tax_registration() );
                if (newcl.getP_pinfl()==null || newcl.getP_pinfl()=="null") 
                	newcl.setP_pinfl(pinfl);
                if (newcl.getJ_number_tax_registration()==null || newcl.getJ_number_tax_registration()=="null")
                	newcl.setJ_number_tax_registration("000000000");
                	
                binder.loadAll();
                showCodesForNewcl();
            } else {
                throw new RuntimeException("Клиент не найден!");
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        }
    }

    public void onClick$btn_fetch_sap() {
        if (CheckNull.isEmpty(newcl.getJ_number_tax_registration())) {
            alert("Введите ИНН");
            return;
        }
        BusinessOrganizationComplex boc = organizationService.getDetailsByInn(newcl.getJ_number_tax_registration());
        if (boc != null) {
            newcl.setClientData(Mappers.mapToClientJ(boc));
            newcl.setBranch(ses_branch);
            if (newcl.getJ_code_bank() == null) {
                newcl.setJ_code_bank(ses_branch);
            }
            newcl.setSign_registr(ClientUtil.SIGN_REGISTR_PRIMARY);
            try {
                if (newcl.getCode_type().equals(ClientUtil.CODE_TYPE_IP)) {
                    // if(boc.getBp_list() != null && boc.getBp_list().length !=
                    // 0) {
                    if (newcl.getJ_code_tax_org() != null)
                        newcl.setP_code_tax_org(newcl.getJ_code_tax_org());
                    showIpView_add(true);
                    SapHandler.fetchIp(newcl, boc.getGeneral().getId_client_sap());
                    // }
                } else {
                    RelationHandler.instance(ses_branch).fetchDirectors(boc.getBp_list(), newcl);
                }
            } catch (Exception e) {
                alert(e.getMessage());
            }
            aj_soato.setValue(newcl.getJ_soato());
            Events.sendEvent(new Event(Events.ON_CHANGE, aj_soato));

            binder.loadAll();
            showCodesForNewcl();
            Events.sendEvent(new Event(Events.ON_CHANGE, acode_type));
        }
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

    @Override
    protected void openClient(int action) {
        if (newcl.getCode_type() == null)
            throw new RuntimeException("Выберите тип клиента ");

        // iterate(addgrd);
        if (!check_some_fields_to_open()) {
            alert(err_msg);
            return;
        }

        newcl.setJ_sign_trade(aj_sign_trade.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
        newcl.setJ_small_business(aj_small_business.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
        newcl.setCode_subject(ClientUtil.CODE_SUBJECT_J);
        newcl.setJ_sign_dep_acc(aj_sign_dep_acc.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
        boolean isNibbd = false;
        if ((mode != null && mode.equals(ClientUtil.MODE_NIBBD)) || action == 50) {
            isNibbd = true;
        }

        Validator<ClientJ> checkHandler = CheckClient.checkCleanClient(alias, ClientUtil.ACTION_OPEN);
        newcl.setEmp_id(""+userId);
        if (!checkHandler.isValid(newcl)) {
            alert(checkHandler.getMessage());
            return;
        }
        //logger.error("SIGN R " + newcl.getSign_registr());
        newcl.resolveSignRegistry(isNibbd);
        Res res = clientService.doAction(newcl, action);
        if (res == null || res.getCode() != 0) {
            alert(res.getCode() + " " + res.getName());
            return;
        }
        String currentId = res.getName();
        filter.clearFilterFields();// новый метод от 21,11,2017
        filter.setId_client(currentId);


        refreshModel(_startPageNumber, true);
        onDoubleClick$dataGrid$grd();
    }

    void confirmAction(final int action) {
        if (!cl_tabs.isVisible() || current == null) {
            alert("Выберите клиента");
            return;
        }
        try {
            Messagebox.show("вы действительно хотите выполнить действие:" + actionsMap.get(action) + "?", "",
                    Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

                        @Override
                        public void onEvent(Event event) throws Exception {
                            if (event.getName().equals(Messagebox.ON_OK)) {

                                try {
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
        if (action == 4 || action == 19 || action == ClientUtil.ACTION_CONFIRM_CLOSED) {
            current.setJ_sign_trade(j_sign_trade.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
            current.setJ_small_business(j_small_business.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
            if (action == 4 && !current.hasObjectiveChanges(copyOfCurrent)) {
                alert("Нет измененных данных для изменения объективных данных");
                return;
            }
            if (action == 19 && current.hasObjectiveChanges(copyOfCurrent)) {
                alert("Затронуты объективные данные");
                current.rollBackObjectiveChanges(copyOfCurrent);
                current.setName(copyOfCurrent.getName());
                binder.loadAll();
                return;
            }
            if ((action == ClientUtil.ACTION_CHANGE || action == ClientUtil.ACTION_CONFIRM_CLOSED)
                    && !CheckClient.isTaxNumberValid(current.getJ_number_tax_registration(), alias)) {
                alert("Неверный ИНН");
                return;
            }

            Validator<ClientJ> check = CheckClient.checkExisting(alias, action);
            if (!check.isValid(current)) {
                alert("Ошибка вводных данных :" + check.getMessage());
                return;
            }
            // hamza 2017-11-23
            if (!check_some_fields_to_edit()) {
                alert(err_msg);
                return;
            }

        }
        Res doAction = null;
        boolean runNibbd = (current.getSign_registr() == 1) && (action == 2 || action == 4);
        current.setEmp_id(""+userId);
        doAction = clientService.doAction(current, action);
        if (doAction != null && (doAction.getCode() != 0)) {
            alert(doAction.getCode() + " " + doAction.getName());
            return;
        }
        // filter = new ClientJFilter();
        filter.clearFilterFields();// новый метод от 21,11,2017
        filter.setJ_number_tax_registration(current.getJ_number_tax_registration());
        if (current.getJ_number_tax_registration() == null) {
        	filter.setP_pinfl(current.getP_pinfl());
        	//filter.setCode_type(current.getCode_type());
        } else {
        	if (current.getJ_number_tax_registration()=="000000000" || current.getJ_number_tax_registration().equals("000000000"))
        	{
        		filter.setP_pinfl(current.getP_pinfl());
        	}
        }
        
        refreshModel(0, true);
        onDoubleClick$dataGrid$grd();
        if (runNibbd) {
            initNibbd(action == 2 ? NibbdQueries.CLIENT_OPEN : NibbdQueries.CLIENT_CHANGE);
        }
    }

    private void initAccounts() {
        if (account_wnd == null) {
            // account_wnd = (Window)
            // Executions.createComponents(ClientUtil.ACCOUNT_SRC,
            // account_parent, null);
            HashMap map = new HashMap<String, String>();
            map.put("clientId", current.getId_client());
            account_wnd = (Window) Executions.createComponents(
                    ClientUtil.ACCOUNT_SRC, account_parent, map);
        }
        AccountViewCtrl accountVC = (AccountViewCtrl) account_wnd.getAttribute("accountmain$composer");
        accountVC.init(current);
    }

    private void initNibbd(NibbdQueries query) {
        if (nibbd_wnd == null) {
            nibbd_wnd = (Window) Executions.createComponents(ClientUtil.NIBBD_SRC, nibbd_modal, null);
            nibbd_wnd.addEventListener(Events.ON_NOTIFY, new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    Nibbd nibbd = (Nibbd) event.getData();
                    nibbd_modal.setVisible(false);
                    if (nibbd != null) {
                        mode = ClientUtil.MODE_NIBBD;
                        initCLientFromNibbd(nibbd.getQuery_out());
                        showFormForNewClient();
                        // Events.sendEvent(new Event(Events.ON_SELECT,
                        // acode_type));
                        Events.sendEvent(new Event(Events.ON_CHANGE, acode_type));
                        btn_save.setLabel(Labels.getLabel("save"));
                        btn_save.setAttribute("action", "open");
                        btn_save.setImage("/images/save.png");
                        addgrd.setAttribute("mode", "new");
                    } else
                        Messagebox.show("Nibbd Query Out is Null");
                }
            });
        }
        NibbdController nibbdC = (NibbdController) nibbd_wnd.getAttribute("nibbdmain$composer");
        if (query != null) {
            nibbdC.initClientAction(current, query);
        }
        nibbd_modal.setVisible(true);
    }

    private void initPersons() {
        if (persons_wnd == null) {
            persons_wnd = (Window) Executions.createComponents(ClientUtil.PERSON_MAP_SRC, persons_div, null);
        }
        PersonMapViewCtrl personsVC = (PersonMapViewCtrl) persons_wnd.getAttribute("personmainmain$composer");
        personsVC.init(serviceFactory, current);
    }

    private void initSpecclt() {
        if (specclt_wnd == null) {
            specclt_wnd = (Window) Executions.createComponents(ClientUtil.SPECCLT_SRC, specclt_div, null);
        }
        SpecCltViewCtrl speccltVC = (SpecCltViewCtrl) specclt_wnd.getAttribute("specharmain$composer");
        speccltVC.init(current, serviceFactory);
    }

    // private void refreshAtachmentList() {
    public void refreshAtachmentList() {
        try {
            atachments.setModel(new BindingListModelList(organizationService.getAttachments(current), true));
        } catch (Exception e) {
            alert(e.getMessage());
        }
    }

    protected void setActionBar() {
        Map<Integer, String> availableActionsMap = dictionaryKeeper.getAvailableActionsMap(current, un, pw, alias);
        Toolbarbutton button = null;
        actions_bar.getChildren().clear();
        for (Map.Entry<Integer, String> entry : availableActionsMap.entrySet()) {
            //logger.info("Current State -> " + entry.getValue() + " =" + entry.getKey());
            if (entry.getKey() == 1 || entry.getKey() > 26) {
                continue;
            }
            button = new Toolbarbutton(entry.getValue());
            button.setAttribute("action", entry.getKey());
            button.setImage(ActionImages.getImageForOrganizations(entry.getKey()));
            button.addEventListener(Events.ON_CLICK, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    confirmAction((Integer) arg0.getTarget().getAttribute("action"));
                }
            });
            actions_bar.appendChild(button);
        }
        button = new Toolbarbutton("Сравнить с данными SAP и ЕБП");
        button.setImage("/images/z-sap.png");
        button.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event arg0) throws Exception {
                compare(current);
            }
        });
        actions_bar.appendChild(button);
      
        // --- в эксел
        button = new Toolbarbutton("в MS WORD");
        button.setImage("/images/word.png");
        button.addEventListener(Events.ON_CLICK, new EventListener() {
            @Override
            public void onEvent(Event arg0) throws Exception {
                onClick$btn_word();
            }
        });
        actions_bar.appendChild(button);
        //
        if (mode != null && mode.equals(ClientUtil.MODE_DELTA)) {
            button = new Toolbarbutton("Отправить в SAP");
            button.addEventListener(Events.ON_CLICK, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    if (current == null) {
                        alert("client = null");
                        return;
                    }
                    try {
                        organizationService.createIfAbsent(current);
                        alert("Успешно!");
                    } catch (Exception e) {
                        alert(e.getMessage());
                    }
                }
            });
            actions_bar.appendChild(button);
        }

    }

    public static void main(String[] args) {
        String codeType = "11";
        System.out.println(codeType.equals(ClientUtil.CODE_TYPE_IP));
    }

    public String toXmlString(ClientJ clientJ) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Object.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To
            // format
            // XML

            StringWriter sw = new StringWriter();
            m.marshal(clientJ, sw);
            xmlString = sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public void onCheck$isAccountantCheckbox(CheckEvent checkEvent) {
        if (checkEvent.isChecked()) {
            newcl.setAccountant(newcl.getDirector());
            binder.loadAll();
        } else {
            newcl.setAccountant(new Person());
            binder.loadAll();
        }
    }

    public void compare(ClientJ currentCustomer) {
        ClientJ fetchedFromSap = null;
        ClientJ ebpClient = null;
        try {
            if (!addgrd.isVisible()) {
                if (currentCustomer.getId_sap() != null) {
                    fetchedFromSap = Mappers
                            .mapToClientJ(organizationService.getDetailsBySapId(currentCustomer.getId_sap()));
                } else {
                    fetchedFromSap = Mappers.mapToClientJ(organizationService
                            .getDetailsByNciId(currentCustomer.getId_client(), currentCustomer.getBranch()));
                }
                ebpClient = EbpService.getClientFromEbp(currentCustomer);
            } else {
                fetchedFromSap = Mappers.mapToClientJ(
                        organizationService.getDetailsByInn(currentCustomer.getJ_number_tax_registration()));
                ebpClient = EbpService.getClientFromEbp(currentCustomer);
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        }

        fields_diff_wnd$diff_rows.getChildren().clear();
        fields_diff_wnd$diff_grid.setAttribute(ClientUtil.SAP_CLIENT_ATTR, fetchedFromSap);
        fields_diff_wnd$diff_grid.setAttribute(ClientUtil.EBP_CLIENT_ATTR, ebpClient);

        ClientUtil.fillRowsIfThereChanges(currentCustomer, fetchedFromSap, ebpClient, fields_diff_wnd$diff_rows);
        fields_diff_wnd.setVisible(fields_diff_wnd$diff_rows.getChildren().size() > 0);
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
        Messagebox.show("вы действительно хотите выполнить действие: открыть?", "", Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION, new EventListener() {

                    @Override
                    public void onEvent(Event event) throws Exception {
                        if (event.getName().equals(Messagebox.ON_OK))
                            // openClient(1);
                            Messagebox.show("B52");
                        return;
                    }
                });
    }

    public boolean check_some_fields_to_open() {
        err_msg = "";
        if (!CheckNull.isEmpty(acode_country_value.getValue()) && CheckNull.isEmpty(acode_country.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна";
            return false;
        }
        if (!CheckNull.isEmpty(aaddressCountryText.getValue()) && CheckNull.isEmpty(aaddressCountry.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна";
            return false;
        }
        if (!CheckNull.isEmpty(aregionValue.getValue()) && CheckNull.isEmpty(aj_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона";
            return false;
        }
        if (!CheckNull.isEmpty(adistrValue.getValue()) && CheckNull.isEmpty(aj_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района";
            return false;
        }
        if (!CheckNull.isEmpty(atax_orgValue.getValue()) && CheckNull.isEmpty(aj_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой.";
            return false;
        }
        if (!CheckNull.isEmpty(aopfValue.getValue()) && CheckNull.isEmpty(aj_opf.getValue())) {
            err_msg = "Неправильное значение в поле:  Код ОПФ";
            return false;
        }
        if (!CheckNull.isEmpty(aformValue.getValue()) && CheckNull.isEmpty(acode_form.getValue())) {
            err_msg = "Неправильное значение в поле:  Код формы собственности";
            return false;
        }
        if (!code_type.getValue().equalsIgnoreCase(ClientUtil.CODE_TYPE_IP)
                && !CheckNull.isEmpty(asectorValue.getValue()) && CheckNull.isEmpty(aj_code_sector.getValue())
                && !asectorValue.getValue().equals("0")) {
            err_msg = "Неправильное значение в поле:  ОКЕД ";
            return false;
        }
        if (!CheckNull.isEmpty(acl_activity_type_idValue.getValue())
                && CheckNull.isEmpty(acl_activity_type_id.getValue())) {
            err_msg = "Неправильное значение в поле:  Вид деятельности";
            return false;
        }
        if (!CheckNull.isEmpty(aorgan_directValue.getValue()) && CheckNull.isEmpty(aj_code_organ_direct.getValue())) {
            err_msg = "Неправильное значение в поле:  Код орган управления";
            return false;
        }
        if (!CheckNull.isEmpty(atype_non_residentValue.getValue())
                && CheckNull.isEmpty(atype_non_resident.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип нерезидентности";
            return false;
        }
        if (!CheckNull.isEmpty(ap_type_document_text.getValue()) && CheckNull.isEmpty(ap_type_document.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип документа ";
            return false;
        }
        if (!CheckNull.isEmpty(ap_pass_place_region_text.getValue())
                && CheckNull.isEmpty(ap_pass_place_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона ";
            return false;
        }
        if (!CheckNull.isEmpty(ap_pass_place_distr_text.getValue())
                && CheckNull.isEmpty(ap_pass_place_district.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района ";
            return false;
        }
        if (!CheckNull.isEmpty(ap_code_citizenship_text.getValue())
                && CheckNull.isEmpty(ap_code_citizenship.getValue())) {
            err_msg = "Неправильное значение в поле:  Код гражданство ";
            return false;
        }
        if ((!CheckNull.isEmpty(acode_type.getValue()) && acode_type.getValue().equals(ClientUtil.CODE_TYPE_IP))
                && !CheckNull.isEmpty(acountryValue1.getValue()) && CheckNull.isEmpty(acode_country1.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна-местожителство";
            return false;
        }
        if (!CheckNull.isEmpty(ap_code_adr_region_text.getValue())
                && CheckNull.isEmpty(ap_code_adr_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона-местожителство ";
            return false;
        }
        if (!CheckNull.isEmpty(ap_code_adr_distr_text.getValue()) && CheckNull.isEmpty(ap_code_adr_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района-местожителство";
            return false;
        }
        if (!CheckNull.isEmpty(ap_code_tax_orgValue.getValue()) && CheckNull.isEmpty(ap_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой для ФЛ. ";
            return false;
        }
        if (!CheckNull.isEmpty(abankValue.getValue()) && CheckNull.isEmpty(aj_code_bank.getValue())) {
            err_msg = "Неправильное значение в поле:  Код банка";
            return false;
        }
        if (!CheckNull.isEmpty(aiopfValue.getValue()) && CheckNull.isEmpty(ai_opf.getValue())) {
            err_msg = "Неправильное значение в поле:  Код ОПФ";
            return false;
        }
        if (!CheckNull.isEmpty(aiformValue.getValue()) && CheckNull.isEmpty(ai_form.getValue())) {
            err_msg = "Неправильное значение в поле:  Форма собственности ";
            return false;
        }
        if (!CheckNull.isEmpty(aisectorValue.getValue()) && CheckNull.isEmpty(ai_sector.getValue())) {
            err_msg = "Неправильное значение в поле:  Код сектора ";
            return false;
        }
        if (!CheckNull.isEmpty(aiorgan_directValue.getValue()) && CheckNull.isEmpty(ai_organ_direct.getValue())) {
            err_msg = "Неправильное значение в поле:  Код орган управления ";
            return false;
        }
        if (!CheckNull.isEmpty(aswift_idValue.getValue()) && CheckNull.isEmpty(aswift_id.getValue())) {
            err_msg = "Неправильное значение в поле:  Код свифт";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_type_document_text.getValue())
                && CheckNull.isEmpty(adirector_type_document.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип документа директора ";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_pass_place_region_text.getValue())
                && CheckNull.isEmpty(adirector_pass_place_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона директора";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_pass_place_distr_text.getValue())
                && CheckNull.isEmpty(adirector_pass_place_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района директора ";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_code_citizenship_text.getValue())
                && CheckNull.isEmpty(adirector_code_citizenship.getValue())) {
            err_msg = "Неправильное значение в поле:  Код гражданство директора";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_code_country_text.getValue())
                && CheckNull.isEmpty(adirector_code_country.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна директора";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_code_adr_region_text.getValue())
                && CheckNull.isEmpty(adirector_code_adr_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона директора ";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_code_adr_distr_text.getValue())
                && CheckNull.isEmpty(adirector_code_adr_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района директора";
            return false;
        }
        if (!CheckNull.isEmpty(adirector_code_tax_org_text.getValue())
                && CheckNull.isEmpty(adirector_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой директора";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_type_document_text.getValue())
                && CheckNull.isEmpty(aaccountant_type_document.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип документ бухгалтера ";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_pass_place_region_text.getValue())
                && CheckNull.isEmpty(aaccountant_pass_place_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_pass_place_distr_text.getValue())
                && CheckNull.isEmpty(aaccountant_pass_place_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_code_citizenship_text.getValue())
                && CheckNull.isEmpty(aaccountant_code_citizenship.getValue())) {
            err_msg = "Неправильное значение в поле:  Код гражданство бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_code_country_text.getValue())
                && CheckNull.isEmpty(aaccountant_code_country.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страны бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_code_adr_region_text.getValue())
                && CheckNull.isEmpty(aaccountant_code_adr_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона адреса бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_code_adr_distr_text.getValue())
                && CheckNull.isEmpty(aaccountant_code_adr_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района адреса бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(aaccountant_code_tax_org_text.getValue())
                && CheckNull.isEmpty(aaccountant_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой бухгалтера";
            return false;
        }
        return true;
    }

    public boolean check_some_fields_to_edit() {
        err_msg = "";
        if (!CheckNull.isEmpty(code_country_value.getValue()) && CheckNull.isEmpty(code_country.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна";
            return false;
        }
        if (!CheckNull.isEmpty(addressCountryText.getValue()) && CheckNull.isEmpty(addressCountry.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна";
            return false;
        }
        if (!CheckNull.isEmpty(regionValue.getValue()) && CheckNull.isEmpty(j_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона";
            return false;
        }
        if (!CheckNull.isEmpty(distrValue.getValue()) && CheckNull.isEmpty(j_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района";
            return false;
        }
        if (!CheckNull.isEmpty(tax_orgValue.getValue()) && CheckNull.isEmpty(j_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой.";
            return false;
        }
        if (!CheckNull.isEmpty(opfValue.getValue()) && CheckNull.isEmpty(j_opf.getValue())) {
            err_msg = "Неправильное значение в поле:  Код ОПФ";
            return false;
        }
        if (!CheckNull.isEmpty(formValue.getValue()) && CheckNull.isEmpty(code_form.getValue())) {
            err_msg = "Неправильное значение в поле:  Код формы собственности";
            return false;
        }
        if (!CheckNull.isEmpty(sectorValue.getValue()) && CheckNull.isEmpty(j_code_sector.getValue())
                && !sectorValue.getValue().equals("0")) {
            err_msg = "Неправильное значение в поле:  ОКЕД ";
            return false;
        }
        if (!CheckNull.isEmpty(cl_activity_type_idValue.getValue())
                && CheckNull.isEmpty(cl_activity_type_id.getValue())) {
            err_msg = "Неправильное значение в поле:  Вид деятельности";
            return false;
        }
        if (!CheckNull.isEmpty(organ_directValue.getValue()) && CheckNull.isEmpty(j_code_organ_direct.getValue())) {
            err_msg = "Неправильное значение в поле:  Код орган управления";
            return false;
        }
        if (!CheckNull.isEmpty(type_non_residentValue.getValue()) && CheckNull.isEmpty(type_non_resident.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип нерезидентности";
            return false;
        }
        if (!CheckNull.isEmpty(p_type_document_text.getValue()) && CheckNull.isEmpty(p_type_document.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип документа ";
            return false;
        }
        if (!CheckNull.isEmpty(p_pass_place_region_text.getValue())
                && CheckNull.isEmpty(p_pass_place_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона ";
            return false;
        }
        if (!CheckNull.isEmpty(p_pass_place_distr_text.getValue())
                && CheckNull.isEmpty(p_pass_place_district.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района ";
            return false;
        }
        if (!CheckNull.isEmpty(p_code_citizenship_text.getValue())
                && CheckNull.isEmpty(p_code_citizenship.getValue())) {
            err_msg = "Неправильное значение в поле:  Код гражданство ";
            return false;
        }
        if (!CheckNull.isEmpty(countryValue1.getValue()) && CheckNull.isEmpty(code_country1.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна ";
            return false;
        }
        if (!CheckNull.isEmpty(p_code_adr_region_text.getValue()) && CheckNull.isEmpty(p_code_adr_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона-местожителство ";
            return false;
        }
        if (!CheckNull.isEmpty(p_code_adr_distr_text.getValue()) && CheckNull.isEmpty(p_code_adr_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района-местожителство";
            return false;
        }
        if (!CheckNull.isEmpty(p_code_tax_orgValue.getValue()) && CheckNull.isEmpty(p_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой для ФЛ. ";
            return false;
        }
        if (!CheckNull.isEmpty(bankValue.getValue()) && CheckNull.isEmpty(j_code_bank.getValue())) {
            err_msg = "Неправильное значение в поле:  Код банка";
            return false;
        }
        if (!CheckNull.isEmpty(iopfValue.getValue()) && CheckNull.isEmpty(i_opf.getValue())) {
            err_msg = "Неправильное значение в поле:  Код ОПФ";
            return false;
        }
        if (!CheckNull.isEmpty(iformValue.getValue()) && CheckNull.isEmpty(i_form.getValue())) {
            err_msg = "Неправильное значение в поле:  Форма собственности ";
            return false;
        }
        if (!CheckNull.isEmpty(isectorValue.getValue()) && CheckNull.isEmpty(i_sector.getValue())) {
            err_msg = "Неправильное значение в поле:  Код сектора ";
            return false;
        }
        if (!CheckNull.isEmpty(iorgan_directValue.getValue()) && CheckNull.isEmpty(i_organ_direct.getValue())) {
            err_msg = "Неправильное значение в поле:  Код орган управления ";
            return false;
        }
        if (!CheckNull.isEmpty(swift_idValue.getValue()) && CheckNull.isEmpty(swift_id.getValue())) {
            err_msg = "Неправильное значение в поле:  Код свифт";
            return false;
        }
        if (!CheckNull.isEmpty(director_type_document_text.getValue())
                && CheckNull.isEmpty(director_type_document.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип документа директора ";
            return false;
        }
        if (!CheckNull.isEmpty(director_pass_place_region_text.getValue())
                && CheckNull.isEmpty(director_pass_place_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона директора";
            return false;
        }
        if (!CheckNull.isEmpty(director_pass_place_distr_text.getValue())
                && CheckNull.isEmpty(director_pass_place_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района директора ";
            return false;
        }
        if (!CheckNull.isEmpty(director_code_citizenship_text.getValue())
                && CheckNull.isEmpty(director_code_citizenship.getValue())) {
            err_msg = "Неправильное значение в поле:  Код гражданство директора";
            return false;
        }
        if (!CheckNull.isEmpty(director_code_country_text.getValue())
                && CheckNull.isEmpty(director_code_country.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страна директора";
            return false;
        }
        if (!CheckNull.isEmpty(director_code_adr_region_text.getValue())
                && CheckNull.isEmpty(director_code_adr_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона директора ";
            return false;
        }
        if (!CheckNull.isEmpty(director_code_adr_distr_text.getValue())
                && CheckNull.isEmpty(director_code_adr_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района директора";
            return false;
        }
        if (!CheckNull.isEmpty(director_code_tax_org_text.getValue())
                && CheckNull.isEmpty(director_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой директора";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_type_document_text.getValue())
                && CheckNull.isEmpty(accountant_type_document.getValue())) {
            err_msg = "Неправильное значение в поле:  Тип документ бухгалтера ";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_pass_place_region_text.getValue())
                && CheckNull.isEmpty(accountant_pass_place_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_pass_place_distr_text.getValue())
                && CheckNull.isEmpty(accountant_pass_place_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_code_citizenship_text.getValue())
                && CheckNull.isEmpty(accountant_code_citizenship.getValue())) {
            err_msg = "Неправильное значение в поле:  Код гражданство бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_code_country_text.getValue())
                && CheckNull.isEmpty(accountant_code_country.getValue())) {
            err_msg = "Неправильное значение в поле:  Код страны бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_code_adr_region_text.getValue())
                && CheckNull.isEmpty(accountant_code_adr_region.getValue())) {
            err_msg = "Неправильное значение в поле:  Код региона адреса бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_code_adr_distr_text.getValue())
                && CheckNull.isEmpty(accountant_code_adr_distr.getValue())) {
            err_msg = "Неправильное значение в поле:  Код района адреса бухгалтера";
            return false;
        }
        if (!CheckNull.isEmpty(accountant_code_tax_org_text.getValue())
                && CheckNull.isEmpty(accountant_code_tax_org.getValue())) {
            err_msg = "Неправильное значение в поле:  Код налоговой бухгалтера";
            return false;
        }

        return true;
    }

}
