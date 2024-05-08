package com.is.client_personmap.controllers;

import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import client.NCI.com.ipakyulibank.cj.BusinessPartnerNCI;
import com.is.ISLogger;
import com.is.base.CommonDictionaries;
import com.is.base.Validator;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.client_personmap.LegalEntityValidator;
import com.is.client_personmap.PersonMapService;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.dao.LegalEntityDao;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.Mappers;
import com.is.client_sap.SapEnum;
import com.is.client_sap.SapFactory;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.client_sap.services.SapCustomerService;
import com.is.clients.dao.DaoFactory;
import com.is.clients.ebp.EbpMappers;
import com.is.clients.ebp.EbpService;
import com.is.clients.models.Soato;
import com.is.clients.services.ClientDictionaries;
import com.is.clients.utils.ClientUtil;
import com.is.clients.utils.ModuleMode;
import com.is.customer_.sap.EmergencyMode;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import relationships.NCI.com.ipakyulibank.BPRelResp;

import java.util.List;

@SuppressWarnings("serial")
public class LegalFounderComposer extends AbstractFounderController<LegalEntity> {
    private Window fields_diff_wnd;
    private Rows fields_diff_wnd$diff_rows;
    private Checkbox fields_diff_wnd$check_all_sap;
    private Checkbox fields_diff_wnd$check_all_ebp;
    private Textbox name, short_name, soato;
    private Textbox
            countryValue, tax_orgValue, opfValue, organ_directValue, sectorValue,
            code_adr_regionValue, code_adr_distrValue;
    @SuppressWarnings("unused")
    private RefCBox code_country, code_resident, code_type, code_form, state, code_tax_org, code_sector_old, code_sector,
            code_organ_direct, code_class_credit, code_bank, region, distr, opf, activity_type;
    @SuppressWarnings("unused")
    private RefCBox p_code_tax_org, p_code_gender, p_type_document, code_adr_region, code_adr_distr, p_code_citizenship;
    private Row onlyForJ1,regionRow, onlyForJ2,countryRow, onlyForJ3, numberRegistrationRow;
    private Rows mainRows;
    private Checkbox small_business;
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", current);
        binder.loadAll();

        alias = (String) session.getAttribute("alias");
        ses_branch = (String) session.getAttribute("branch");
        ses_uId = ""+(Integer) session.getAttribute("uid");
        String[] parameters = (String[]) param.get("mode");
        if (parameters != null && parameters[0].equals(ModuleMode.DELTA.getAction())) {
            mode = ModuleMode.DELTA;
            initNibbdMode();
        }

        founderDao = DaoFactory.getInstance(alias).getLegalEntityDao();
        personMapDao = DaoFactory.getInstance(alias).getPersonMapDao();
        personMapService = PersonMapService.instance(alias);

        current = PersonMap.legalFounderInstance(ses_branch);
    }

    private void initNibbdMode() {
        String[] prams = (String[]) param.get("id");
        if (prams == null || prams.length == 0) {
            alert("Лицо не найдено!");
            return;
        }
        setCurrent(PersonMap.legalFounderInstance(ses_branch));
        getCurrent().setLegalEntity(founderDao.getItemByStringId(null, prams[0]));
        renderComboboxes();
        action_bar.getChildren().clear();
        Toolbarbutton button = new Toolbarbutton("Отправить в SAP");
        button.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event arg0) throws Exception {
                try {
                    SapFactory.instance().getLegalEntityService().createIfAbsent(current.getLegalEntity());
                } catch (Exception e) {
                    alert(e.getMessage());
                }
            }
        });
        action_bar.appendChild(button);
    }

    @Override
    protected void initCurrentFounder() {
        current.setPerson_kind(PersonMapUtil.PERSONKIND_FOUNDER);
        current.setPerson_type(PersonMapUtil.PERSONTYPE_J);
        current.setClient_id(clientId);
        current.setNumber_tax_registration(clientInn);
        current.setBranch(ses_branch);
    }


    @Override
    protected void getFounderFromSap(String idSap) {
        if (idSap == null || current.getLegalEntity().getIdSap() != null) {
            return;
        }
        BusinessOrganizationComplex boc = SapFactory.instance().getOrganizationService().getDetailsBySapId(idSap);
        current.setLegalEntity(Mappers.mapBocToLegal(boc));
        if (boc != null && boc.getBp_list() != null) {
            for (BusinessPartnerNCI bp : boc.getBp_list()) {
                if (bp.getBranch().equals(ses_branch) && bp.getClient_id().startsWith("A")) {
                    String union_id = bp.getClient_id().substring(1);
                    current.getLegalEntity().setUnion_id(union_id);
                    current.setBranch(ses_branch);
                    current.setPerson_type(PersonMapUtil.PERSONTYPE_J);
                    current.setUnion_id(union_id);
                    LegalEntityDao dao = (LegalEntityDao) DaoFactory.instance().getLegalEntityDao();
                    LegalEntity locallegal = dao.getByUnionId(alias, union_id);
                    if (locallegal != null) {
                        current.getLegalEntity().setState(locallegal.getState());
                        current.setPerson_id(locallegal.getId());
                        current.setLegalEntity(locallegal);
                        logger.error("Get Founder From SAP " + current.getLegalEntity());
                    }
                    return;
                }
            }
        }
    }

    @Override
    protected void renderComboboxes() {
        try {
            refreshCurrent();
            binder.loadAll();
            onChange$code_resident();
            //Messagebox.show(current.getLegalEntity().toString());
        } catch (Exception e) {
            try {
                Messagebox.show(e.getMessage());
            } catch (InterruptedException e1) {

            }
        }
    }

    protected void rendersComboboxes() {
        //refreshCurrent();
        //binder.loadAll();
        countryValue.setValue(current.getLegalEntity().getCode_country());
        tax_orgValue.setValue(current.getLegalEntity().getCode_tax_org());
        sectorValue.setValue(current.getLegalEntity().getCode_sector());
        organ_directValue.setValue(current.getLegalEntity().getCode_organ_direct());
        opfValue.setValue(current.getLegalEntity().getOpf());
        if (current.getLegalEntity().getCode_resident() == null && current.getLegalEntity().getCode_country() != null) {

        }
        //Events.sendEvent(code_form, new Event("onInitRender"));
        Events.sendEvent(code_tax_org, new Event("onInitRender"));
        Events.sendEvent(code_sector, new Event("onInitRender"));
        Events.sendEvent(code_organ_direct, new Event("onInitRender"));
        Events.sendEvent(opf, new Event("onInitRender"));
        Events.sendEvent(currency, new Event("onInitRender"));
        Events.sendEvent(code_country, new Event("onInitRender"));
        Events.sendEvent(code_resident, new Event("onInitRender"));
        Events.sendEvent(code_type, new Event("onInitRender"));
        Events.sendEvent(code_form, new Event("onInitRender"));
        Events.sendEvent(code_adr_region, new Event("onInitRender"));
        Events.sendEvent(code_adr_distr, new Event("onInitRender"));
        binder.loadAll();
    }

    @Override
    protected void checkFounderInSap() {

    }

    @Override
    protected void compareFields() throws Exception {
        LegalEntity sapLe = null;
        LegalEntity ebpLe = null;
        try {
            if (!EmergencyMode.isTrue) {
                sapLe = SapFactory.instance().getLegalEntityService()
                        .getDetailsByDoc(
                                current.getLegalEntity()
                                        .getNumber_tax_registration(),
                                SapEnum.DOC_TYPE_INN.getSapValue());
            }
            ebpLe = EbpMappers.mapLegalEntityDetailsToLocalLE(
                    EbpService.legalEntityDetails(
                            current.getBranch(), current.getLegalEntity().getNumber_tax_registration()));
            ;
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        fields_diff_wnd$diff_rows.getChildren().clear();
        fields_diff_wnd$diff_rows.setAttribute(ClientUtil.SAP_CLIENT_ATTR, sapLe);
        fields_diff_wnd$diff_rows.setAttribute(ClientUtil.EBP_CLIENT_ATTR, ebpLe);
        PersonMapUtil.fillRowsIfThereChanges(
                current.getLegalEntity()
                , sapLe
                , ebpLe
                , fields_diff_wnd$diff_rows);
        fields_diff_wnd.setVisible(true);
    }

    @Override
    protected void executeFounderAction(int action) {
        //logger.error("At start founder action " + current.getLegalEntity());
        try {
            Validator<LegalEntity> validator =
                    LegalEntityValidator.instance(alias,
                            current.getLegalEntity().getState()
                                    == PersonMapService.STATE_CONFIRMED);
            if (!validator.isValid(current.getLegalEntity())) {
                alert(validator.getMessage());
                return;
            }
            //logger.error("After validation " + current.getLegalEntity());
            current.setClient_id(clientId);
            current.getLegalEntity().setBranch(ses_branch);
            current.getLegalEntity().setParent_id_client_j(clientId);
            current.getLegalEntity().setEmp_id(ses_uId);
            
            if (small_business!=null)
                current.getLegalEntity().setSmall_business(small_business.isChecked() ? ClientUtil.CHECKBOX_Y : ClientUtil.CHECKBOX_N);
            
            // Если создается поставить id null
            if (action == PersonMapService.ACTION_CREATE ||
                    action == PersonMapService.ACTION_CONFIRM_HOP) {
                current.getLegalEntity().setId(null);
            }
            //logger.error("Before action " + current.getLegalEntity());

            Res res = personMapService.leAction(current.getLegalEntity(), action);
            if (res != null && res.getCode() != 0) {
                alert(res.getName());
                return;
            }
            alert("Успешно!");
            if (Util.inInts(action, PersonMapService.ACTION_CONFIRM, PersonMapService.ACTION_CREATE, PersonMapService.ACTION_CONFIRM_HOP)) {
                current.setLegalEntity(founderDao.getItemByStringId(null, res.getName()));
                current.setPerson_id(current.getLegalEntity().getId());
                current.setUnion_id(current.getLegalEntity().getUnion_id());
            } else {
                current.setLegalEntity(founderDao.getItemByStringId(null, current.getPerson_id()));
                current.setPerson_id(current.getLegalEntity().getId());
                current.setUnion_id(current.getLegalEntity().getUnion_id());
            }
            current.getLegalEntity().setIdSap(current.getIdSap());
            setActionBar();
            setRelationActionBar();
            renderComboboxes();
        } catch (SapCustomerService.FounderDuplicationException e) {

        }
    }

    @Override
    protected void setModels() {
        currency.setModel(new ListModelList(CommonDictionaries.getCurrencies(null, alias)));
        code_country.setModel(new ListModelList(dictionaryKeeper.getCountries()));
        code_form.setModel(new ListModelList(dictionaryKeeper.getVsbs()));
        code_tax_org.setModel(new ListModelList(dictionaryKeeper.getGni()));
        code_sector.setModel(new ListModelList(dictionaryKeeper.getOked()));
        code_organ_direct.setModel(new ListModelList(dictionaryKeeper.getSoogun()));
        opf.setModel(new ListModelList(dictionaryKeeper.getOpf()));
        code_resident.setModel(new ListModelList(dictionaryKeeper.getResidentTypes()));
        code_adr_region.setModel(new ListModelList(dictionaryKeeper.getRegions()));
        code_adr_distr.setModel(new ListModelList(dictionaryKeeper.getDistricts()));
        code_type.setModel(new ListModelList(dictionaryKeeper.getClientTypes()));
        activity_type.setModel(new ListModelList(dictionaryKeeper.getActivityType()));
    }

    public void onBlur$name() {
        int len = name.getValue().length();
        short_name.setValue(name.getValue().substring(0, len < 25 ? len : 25));
        current.getLegalEntity().setShort_name(short_name.getValue());
    }

    public void onChange$countryValue() {
        current.getLegalEntity().setCode_country(countryValue.getValue());
        if (current.getLegalEntity().getCode_country().equals("860"))
            current.getLegalEntity().setCode_resident("1");
        code_country.setSelecteditem(current.getLegalEntity().getCode_country());
        code_resident.setSelecteditem(current.getLegalEntity().getCode_resident());
//		Events.sendEvent(code_resident, new Event("onInitRender"));
    }

    public void onChange$code_country() {
        current.getLegalEntity().setCode_country(code_country.getValue());
        if (current.getLegalEntity().getCode_country().equals("860"))
            current.getLegalEntity().setCode_resident("1");
        code_resident.setSelecteditem(current.getLegalEntity().getCode_resident());
        countryValue.setValue(current.getLegalEntity().getCode_country());
//		Events.sendEvent(code_resident, new Event("onInitRender"));
    }

    public void onCheck$check_all_sap$fields_diff_wnd() {
        ZkUtils.checkAllRowsUnderSpecificColumn(ClientUtil.COLUMN_ATTR, ClientUtil.SAP_COLUMN, fields_diff_wnd$diff_rows, fields_diff_wnd$check_all_sap.isChecked());
    }

    public void onCheck$check_all_ebp$fields_diff_wnd() {
        ZkUtils.checkAllRowsUnderSpecificColumn(ClientUtil.COLUMN_ATTR, ClientUtil.EBP_COLUMN, fields_diff_wnd$diff_rows, fields_diff_wnd$check_all_ebp.isChecked());
    }

    public void onClick$btn_apply$fields_diff_wnd() {
        List<String> sapCheckedFields = ZkUtils.getCheckedRowsByAttribute(fields_diff_wnd$diff_rows, ClientUtil.SAP_COLUMN, ClientUtil.COLUMN_ATTR, ClientUtil.FIELD_ATTR);
        List<String> ebpCheckedFields = ZkUtils.getCheckedRowsByAttribute(fields_diff_wnd$diff_rows, ClientUtil.EBP_COLUMN, ClientUtil.COLUMN_ATTR, ClientUtil.FIELD_ATTR);

        LegalEntity fetchedClientFromSap =
                (LegalEntity) fields_diff_wnd$diff_rows.getAttribute(ClientUtil.SAP_CLIENT_ATTR);
        LegalEntity fetchedClientFromEbp = (LegalEntity) fields_diff_wnd$diff_rows.getAttribute(ClientUtil.EBP_CLIENT_ATTR);
        PersonMapUtil.setSapOrEbpData(current.getLegalEntity()
                , fetchedClientFromSap
                , fetchedClientFromEbp
                , sapCheckedFields
                , ebpCheckedFields);
        String union_id = fetchedClientFromSap.getUnion_id();
        String currentUnionId = current.getPerson().getUnion_id();
        String id = current.getPerson_id();
        if (union_id == null){
            current.getLegalEntity().setId(id);
            current.getLegalEntity().setUnion_id(currentUnionId);
        }
        else{
            current.getLegalEntity().setId(id);
            current.getLegalEntity().setUnion_id(union_id);
        }
        binder.loadAll();
        fields_diff_wnd.setVisible(false);
        refreshCurrent();
    }

    public void onChange$code_adr_regionValue(InputEvent event) {
        current.getLegalEntity().setPost_address_region(event.getValue());
        code_adr_region.setSelecteditem(event.getValue());
        code_adr_regionValue.setValue(code_adr_region.getValue());
        code_adr_distr.setModel(new ListModelList(
                ClientDictionaries
                        .getDistrByRegion(null, code_adr_regionValue.getValue(), alias)));
    }

    public void onChange$code_adr_region() {
        current.getLegalEntity().setPost_address_distr(code_adr_region.getValue());
        code_adr_regionValue.setValue(code_adr_region.getValue());
        code_adr_distr.setModel(new ListModelList(
                ClientDictionaries
                        .getDistrByRegion(null, code_adr_regionValue.getValue(), alias)));
    }

    public void onChange$soato() {
        String value = soato.getValue();
        if (value == null || value.length() < 7)
            return;

        LegalEntity legalEntity = current.getLegalEntity();
        if (legalEntity != null) {
            String key = substringSoato(value);
            Soato soato = dictionaryKeeper.getSoatoMap().get(key);

            legalEntity.setPost_address_region(soato.getRegion());
            legalEntity.setPost_address_distr(soato.getDistr());
            legalEntity.setCode_tax_org(soato.getKod_gni());

            binder.loadAll();
        }
    }

    private String substringSoato(String value) {
        return value.substring(2, 7);
    }

    private void refreshCurrent() {
        try {
            code_adr_distr.setModel(new ListModelList(
                    ClientDictionaries.
                            getDistrByRegion(
                                    null,
                                    current.getLegalEntity().getPost_address_region(),
                                    alias)));
            ZkUtils.sendOnInitToRCombobox(self);
            Events.sendEvent(Events.ON_CHANGE, soato, null);
            code_adr_distr.setSelecteditem(current.getLegalEntity().getPost_address_distr());
            
			small_business.setChecked(current.getLegalEntity().getSmall_business() != null
					&& current.getLegalEntity().getSmall_business().equals(ClientUtil.CHECKBOX_Y));

        } catch (Exception e) {

        }
    }

    @Override
    protected FounderCapital getFounderCapitalForDiff() throws Exception {
        AbstractSapRelationService relationsService = SapFactory.instance().getRelationService();
        BPRelResp[] relationSAP = relationsService.getRelationsByIdAndBranchWithoutPrefix(
                String.format("A%s", current.getLegalEntity().getUnion_id()), current.getBranch());
        return Mappers.mapResponseToCapital(current,
                relationSAP);
    }

    public void creation(LegalEntity legalEntity) {
        mode = ModuleMode.CREATION;
        current = new PersonMap();
        initCurrentFounder();
        if (legalEntity != null && legalEntity.getBranch() != null && legalEntity.getId()!=null) {
            LegalEntity localLe = founderDao.getItemByStringId(legalEntity.getBranch(), legalEntity.getId());
            current.setLegalEntity(localLe);
            current.setPerson_id(localLe.getId());
            current.setBranch(localLe.getBranch());
        }
        else
            current.setBranch(ses_branch);

        current.setClient_id(clientId);
        //getFounderFromSap(idSap);
        //current.setIdSap(idSap);
        //logger.error("" + current);
        if (current.getCapital().getPart_of_capital() == null) {
            current.getCapital().setPart_of_capital(PersonMapUtil.getFounderPercentageRemainder(clientId, ses_branch, personMapDao));
            current.getCapital().setPart_of_capital_old(current.getCapital().getPart_of_capital());
        }
        renderComboboxes();
        setActionBar();
        setRelationActionBar();
    }


    public void onChange$code_resident(){
        boolean isResident = code_resident.getValue() != null && code_resident.getValue().equalsIgnoreCase("1");
        List children = mainRows.getChildren();
        for (Object child : children) {
            if (child instanceof Row &&
                    !(child.equals(numberRegistrationRow) || child.equals(onlyForJ2) || child.equals(countryRow) || child.equals(onlyForJ3))){
                ((Row) child).setVisible(isResident);
            }
        }
        code_tax_org.setVisible(isResident);
        code_tax_org.getPreviousSibling().setVisible(isResident);
        code_tax_org.getParent().getPreviousSibling().setVisible(isResident);
        //code_type.setVisible(isResident);
        //code_type.getPreviousSibling().setVisible(isResident);
        //onlyForJ1.setVisible(isResident);
        /*code_type.setVisible(isResident);
        code_type.getPreviousSibling().setVisible(isResident);
        okpo.setVisible(isResident);
        okpo.getPreviousSibling().setVisible(isResident);
        reestrNumOld.setVisible(isResident);
        reestrNumOld.getPreviousSibling().setVisible(isResident);
        region.setVisible(isResident);
        region.getPreviousSibling().setVisible(isResident);
        distr.setVisible(isResident);
        distr.getPreviousSibling().setVisible(isResident);*/
    }
}
