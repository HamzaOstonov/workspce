package com.is.client_personmap.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.is.base.Validator;
import com.is.client_personmap.LegalEntityValidator;
import com.is.client_personmap.PersonValidator;
import com.is.client_personmap.model.Person;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Toolbar;

import com.is.base.Dao;
import com.is.base.utils.ZkUtils;
import com.is.client_personmap.PersonMapService;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.services.DictionaryKeeper;
import com.is.clients.utils.ClientUtil;
import com.is.clients.utils.ModuleMode;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractFounderController<T> extends GenericForwardComposer {
    private static final long serialVersionUID = 1L;
    protected Window relations_diff_wnd;
    protected Rows relations_diff_wnd$diff_rows;

    protected static Logger logger = Logger.getLogger(AbstractFounderController.class);

    
    protected String alias;
    protected String ses_branch;
    protected String ses_uId;
    protected String clientId;
    protected String clientInn;   
    @Getter
    @Setter
    protected PersonMap current = new PersonMap();
    protected Dao<PersonMap> personMapDao;
    protected Dao<T> founderDao;
    protected ModuleMode mode;
    protected Checkbox is_director;
    protected RefCBox currency;
    protected Textbox currency_value;
    protected Toolbar action_bar;
    protected Toolbar rel_action_bar;
    protected Checkbox is_beneficiary;
    
    protected AnnotateDataBinder binder;
    protected DictionaryKeeper dictionaryKeeper;
    protected PersonMapService personMapService;

    public AbstractFounderController() {
        super('$', false, false);
    }

    public void localView(PersonMap personMap) {
        mode = ModuleMode.EDIT;
        setCurrent(personMap);
        if (personMap.getPerson_kind().equals("3") && personMap.getPerson_type().equals("P") )
          is_beneficiary.setVisible(false);
        current.setFounder(founderDao.getItemByStringId(null, current.getPerson_id()));
        if (current.getPerson_kind().equals(PersonMapUtil.PERSONKIND_FOUNDER)) {
            is_director.setChecked(current.getCapital().getIs_director() != null &&
                    current.getCapital().getIs_director().equalsIgnoreCase("X"));
        }
        if (current.getCapital().getPart_of_capital() == null
                || current.getCapital().getPart_of_capital().equals(BigDecimal.ZERO)) {
            current.getCapital().setPart_of_capital(PersonMapUtil.getFounderPercentageRemainder
                    (clientId, ses_branch, personMapDao));
            current.getCapital().setPart_of_capital_old(current.getCapital().getPart_of_capital());
        }
        renderComboboxes();
        //logger.error("Legal Entity at local view " + current.getLegalEntity());
        //logger.error("Personmap " + current);
        setActionBar();
        setRelationActionBar();
    }

    public void creation(String idSap) {
        mode = ModuleMode.CREATION;
        current = new PersonMap();
        initCurrentFounder();
        getFounderFromSap(idSap);
        current.setIdSap(idSap);
        current.setClient_id(clientId);
        if (current.getCapital().getPart_of_capital() == null) {
            current.getCapital().setPart_of_capital(PersonMapUtil.getFounderPercentageRemainder(clientId, ses_branch, personMapDao));
            current.getCapital().setPart_of_capital_old(current.getCapital().getPart_of_capital());
        }

        renderComboboxes();
        setActionBar();
        setRelationActionBar();
    }

    public void sap(PersonMap personMap) {
        mode = ModuleMode.NCI_CREATE_SAP_EDIT;
        setCurrent(personMap);
        initCurrentFounder();
        getFounderFromSap(personMap.getIdSap());
        current.setIdSap(personMap.getIdSap());
        // 05-03-2018
        if (current.getCapital().getPart_of_capital() == null) {
            current.getCapital().setPart_of_capital(
                    PersonMapUtil.getFounderPercentageRemainder(clientId, ses_branch, personMapDao));
            current.getCapital().setPart_of_capital_old(current.getCapital().getPart_of_capital());
            is_director.setChecked(current.isExceedsPartOfCapital());
        }
        renderComboboxes();
        setActionBar();
        setRelationActionBar();
    }

    protected abstract void setModels();

    protected abstract void renderComboboxes();

    protected abstract void getFounderFromSap(String idSap);

    protected abstract void executeFounderAction(final int action);

    protected abstract void checkFounderInSap();

    protected abstract void compareFields() throws Exception;

    public void init(DictionaryKeeper dictionaryKeeper, String clientId, String clientInn) {
        this.dictionaryKeeper = dictionaryKeeper;
        this.clientId = clientId;
        this.clientInn = clientInn;
        setModels();
    }

    protected abstract void initCurrentFounder();

    private BigDecimal getUpperBound(BigDecimal partOld, BigDecimal percentageRemainder) {
        boolean positiveRemainder = percentageRemainder.compareTo(BigDecimal.ZERO) > 0;
        if (partOld == null && percentageRemainder.compareTo(BigDecimal.ZERO) == 0) {

            return percentageRemainder;

        } else if (partOld != null && percentageRemainder.compareTo(BigDecimal.ZERO) == 0) {

            return partOld;

        } else if (partOld == null && positiveRemainder) {

            return percentageRemainder;

        } else if (partOld == null && !positiveRemainder) {

            return BigDecimal.ZERO;

        } else if (partOld != null && !positiveRemainder) {

            return partOld.subtract(percentageRemainder.abs());

        } else if (partOld != null && positiveRemainder) {

            return partOld.add(percentageRemainder);

        }
        return BigDecimal.ZERO;
    }

    public void onChange$currency_value() {
        current.getCapital().setCurrency(currency_value.getValue());
        currency.setSelecteditem(currency_value.getValue());
    }

    protected void setActionBar() {
        action_bar.getChildren().clear();

        Map<Integer, String> actions = personMapService.getAvailableActionsForFace(current.founderState());
        Toolbarbutton button = null;

        for (Map.Entry<Integer, String> action : actions.entrySet()) {
            button = new Toolbarbutton(action.getValue());
            button.setAttribute("action", action.getKey());
            button.addEventListener(Events.ON_CLICK, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    executeFounderAction((Integer) arg0.getTarget().getAttribute("action"));
                }
            });
            action_bar.appendChild(button);
        }
        button = new Toolbarbutton("Сравнить с данными SAP и EBP");
        button.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event arg0) throws Exception {
                compareFields();
            }
        });
        action_bar.appendChild(button);
    }

    protected void setRelationActionBar() {
        rel_action_bar.getChildren().clear();
        Map<Integer, String> actions = personMapService.getAvailableActionsForRelation(current.getState());
        Toolbarbutton button = null;
        for (Map.Entry<Integer, String> action : actions.entrySet()) {
            button = new Toolbarbutton(action.getValue() + " отношение");
            button.setAttribute("relAction", action.getKey());
            button.addEventListener(Events.ON_CLICK, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    executeRelationAction((Integer) arg0.getTarget().getAttribute("relAction"));
                }
            });
            rel_action_bar.appendChild(button);
        }
        if (current.isFromSap()) {
            button = new Toolbarbutton("Аннулировать отношение");
            button.addEventListener(Events.ON_CLICK, new EventListener() {

                @Override
                public void onEvent(Event arg0) throws Exception {
                    current.getCapital().setPart_of_capital(BigDecimal.ZERO);
                    current.setIdSap(current.getIdSap());
                    Res res = personMapService.relationAction(current, PersonMapService.ACTION_ANNUL);
                    if (res != null && res.getCode() != 0) {
                        alert(res.getName());
                        return;
                    }
                    alert("Успешно!");
                    action_bar.getChildren().clear();
                    rel_action_bar.getChildren().clear();
                    renderComboboxes();
                }
            });
            rel_action_bar.appendChild(button);
        }
        // Утвержден
        if (current.getState() == 3) {
            button = new Toolbarbutton("Сравнить отношения");
            button.addEventListener(Events.ON_CLICK, new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    compareRelations();
                }
            });
            rel_action_bar.appendChild(button);
        }
        // Введен, Утвержден
        if ( current.getPerson_type().equals("P") && current.getPerson_kind().equals("3") && (current.getState() == 1 || current.getState() == 3)) {
            if (CustomerUtils.isAtaccamaOn()) {
        	  button = new Toolbarbutton("Дублировать учредителя как бенефициарный собственник");
              button.addEventListener(Events.ON_CLICK, new EventListener() {
                  @Override
                  public void onEvent(Event event) throws Exception {
                  	createBeneficiaryFromFounder();
                  }
              });
              rel_action_bar.appendChild(button);
            }
        }
       
    }

    protected void executeRelationAction(int action) {
        // Если процент долевоого участия больше 10
        int compared = current.getCapital().getPart_of_capital().compareTo(BigDecimal.TEN);
        boolean greaterThan10 = compared > 0;
        current.getCapital().setIs_director(greaterThan10 ? "X" : "");
        current.getCapital().setCurrency(currency.getValue());

        if (CheckNull.isEmpty(current.getCapital().getCurrency())) {
            alert("Ошибка ввода: введите валюту");
            return;
        }

        if (current.getCapital().getPart_of_capital() == null) {
            alert("Ошибка ввода: введите процент ");
            return;
        }
        BigDecimal percentageRemainder = PersonMapUtil.getFounderPercentageRemainder(clientId, ses_branch, personMapDao);
        BigDecimal highOrder = null;
        BigDecimal partOld = current.getCapital().getPart_of_capital_old();

        highOrder = getUpperBound(partOld, percentageRemainder);
        if (current.getCapital().getPart_of_capital().compareTo(highOrder) > 0) {
            alert("Процент долевого участия в капитале не должен превышать " + percentageRemainder);
            return;
        }
        if (current.getPerson_kind()==null)
          current.setPerson_kind(PersonMapUtil.PERSONKIND_FOUNDER);
        current.getPerson().setBranch(ses_branch);
        current.setBranch(ses_branch);
        current.setClient_id(clientId);
        current.setNumber_tax_registration(clientInn);
        //current.setSignCreationBeneficiaryToo(is_beneficiary.isChecked()); 
        /*Validator validator = current.getPerson_kind().equalsIgnoreCase(PersonMapUtil.PERSONTYPE_P) ?
                PersonValidator.instance(alias, true) : LegalEntityValidator.instance(alias,true);
        if (action == PersonMapService.ACTION_CONFIRM){
            if (!validator.isValid(current.getPerson_kind().equalsIgnoreCase(PersonMapUtil.PERSONTYPE_P) ? current.getPerson() : current.getLegalEntity())){
                alert(validator.getMessage());
                return;
            }
        }*/
        
        Res res = personMapService.relationAction(current, action);
        if (res != null && res.getCode() != 0) {
            alert(res.getName());
            return;
        }

        FounderCapital tmpCapital = current.getCapital();
        if (action == PersonMapService.ACTION_CREATE || action == PersonMapService.ACTION_CONFIRM_HOP) {
            current.setId(Long.parseLong(res.getName()));
        }
        setCurrent(personMapDao.getItemByLongId(null, current.getId()));
        current.setCapital(tmpCapital);
        if (current.getPerson_kind().equals("3") && ( is_beneficiary!=null && is_beneficiary.isChecked() ) && action==1) { //sozdanie otnoshenie 
            try {
            	
                //agar является бенефициар га галочка куйилган булса
                //shuerga bitta person yasayman
                //person ni action qilib bazaga uzataman
                //va personmap yasayman
                //personmap ni action qilib bazaga uzataman
            	
            	//shuerga bitta person yasayman            	
            	
            	Person personBenef = (Person)current.getPerson().clone();
            	personBenef.setId(null);
            	personBenef.setUnion_id(null);
                //person ni action qilib bazaga uzataman

            	res = personMapService.personAction(personBenef, 1); //1-sozdat uchreditel
                if (res != null && res.getCode() != 0) {
                     alert(res.getName());
                     return;
                }
                //va personmap yasayman
                PersonMap personMapBenef = new PersonMap();
                personMapBenef.setBranch(current.getBranch());
                personMapBenef.setClient_id(current.getClient_id());
                personMapBenef.setNumber_tax_registration(current.getNumber_tax_registration());
                personMapBenef.setPerson_id(personBenef.getId());
                personMapBenef.setPerson_type("P");
                personMapBenef.setPerson_kind("6"); //benefisiarniy sobstvennik
                personMapBenef.setPerson(personBenef);
                FounderCapital benefCapital = new FounderCapital();
                benefCapital.setSum_a(current.getCapital().getSum_a());
                benefCapital.setSum_b(current.getCapital().getSum_b());
                benefCapital.setPart_of_capital(current.getCapital().getPart_of_capital());
                benefCapital.setCurrency(current.getCapital().getCurrency());
                personMapBenef.setCapital(benefCapital);

                //personmap ni action qilib bazaga uzataman

                res = personMapService.relationAction(personMapBenef, 1); //sozdat otnoshenie dlya benefisiarniy sobstvennik
                if (res != null && res.getCode() != 0) {
                    alert(res.getName());
                    return;
                }
                is_beneficiary.setChecked(false);
                is_beneficiary.setVisible(false);
            } catch (Exception e) {
             
               logger.error("является бенефициар га галочка куйилган булса " + e.getMessage());
               alert(e.getMessage());
            }
        } //end benefisiar
        alert("Успешно!");
        setRelationActionBar();

        //logger.error("After Relation Action");
    }

    protected void compareRelations() throws Exception {
        FounderCapital sapCapital = getFounderCapitalForDiff();
        relations_diff_wnd$diff_rows.getChildren().clear();
        relations_diff_wnd$diff_rows.setAttribute(ClientUtil.SAP_CLIENT_ATTR, sapCapital);
        PersonMapUtil.fillRowsIfThereChanges(
                current.getCapital()
                , sapCapital
                , relations_diff_wnd$diff_rows);
        relations_diff_wnd.setVisible(true);
    }

    protected void createBeneficiaryFromFounder() throws Exception {

    	try {
    	
        	Person personBenef = (Person)current.getPerson().clone();
        	personBenef.setId(null);
        	personBenef.setUnion_id(null);
            //person ni action qilib bazaga uzataman

        	Res res = personMapService.personAction(personBenef, 1); //1-sozdat uchreditel
            if (res != null && res.getCode() != 0) {
                 alert(res.getName());
                 return;
            }
            //va personmap yasayman
            PersonMap personMapBenef = new PersonMap();
            personMapBenef.setBranch(current.getBranch());
            personMapBenef.setClient_id(current.getClient_id());
            personMapBenef.setNumber_tax_registration(current.getNumber_tax_registration());
            personMapBenef.setPerson_id(personBenef.getId());
            personMapBenef.setPerson_type("P");
            personMapBenef.setPerson_kind("6"); //benefisiarniy sobstvennik
            personMapBenef.setPerson(personBenef);
            FounderCapital benefCapital = new FounderCapital();
            benefCapital.setPart_of_capital(current.getCapital().getPart_of_capital());
            benefCapital.setSum_a(current.getCapital().getSum_a());
            benefCapital.setSum_b(current.getCapital().getSum_b());
            benefCapital.setCurrency(current.getCapital().getCurrency());
            personMapBenef.setCapital(benefCapital);

            //personmap ni action qilib bazaga uzataman

            res = personMapService.relationAction(personMapBenef, 1); //sozdat otnoshenie dlya benefisiarniy sobstvennik
            if (res != null && res.getCode() != 0) {
                alert(res.getName());
                return;
            }
            alert("Успешно");
    
    	} catch (Exception e) {
    		logger.error("дублирование учредителя как бенефициар " + e.getMessage());
    		alert(e.getMessage());
    	}

       
    }

    protected abstract FounderCapital getFounderCapitalForDiff() throws Exception;

    public void onClick$btn_apply$relations_diff_wnd() throws NoSuchFieldException {
        List<String> sapCheckedFields =
                ZkUtils
                        .getCheckedRowsByAttribute
                                (relations_diff_wnd$diff_rows,
                                        ClientUtil.SAP_COLUMN,
                                        ClientUtil.COLUMN_ATTR,
                                        ClientUtil.FIELD_ATTR);
        FounderCapital fetchedCapital =
                (FounderCapital)
                        relations_diff_wnd$diff_rows.getAttribute(ClientUtil.SAP_CLIENT_ATTR);
        FounderCapital currentCapital = current.getCapital();
        currentCapital.setSum_a(fetchedCapital.getSum_a());
        currentCapital.setCurrency(fetchedCapital.getCurrency());
        currentCapital.setPart_of_capital(fetchedCapital.getPart_of_capital());
        binder.loadAll();
        relations_diff_wnd.setVisible(false);
    }

    public void onCheck$check_all_sap$relations_diff_wnd(CheckEvent event) {
        ZkUtils.checkAllRowsUnderSpecificColumn(
                ClientUtil.COLUMN_ATTR,
                ClientUtil.SAP_COLUMN,
                relations_diff_wnd$diff_rows,
                event.isChecked());
    }

    public void onChange$part_of_capital(){
        is_director.setChecked(current.isExceedsPartOfCapital());
    }
}
