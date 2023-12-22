package com.is.customer_.search.SAPSearch;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.customer_.action.Action;
import com.is.customer_.action.ActionUtils;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractor;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractorImpl;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.ui.AbstractCustomerComponentFacade;
import com.is.customer_.ui.CustomerWindowFacade;

/**
 * Created by root on 09.05.2017.
 * 16:20
 */
public class BaseSearchComposer extends GenericForwardComposer {
    private Logger logger = Logger.getLogger(SAPSearchComposer.class);

    // Components
    private Div findSAP, findLocal;
    public Toolbarbutton btnCreateCustomer, btn_show_search_sap;
    // Session and binding variables
    private SessionAttributes sessionAttributes;
    private AnnotateDataBinder binder;
    // Interactor objects
    private List<Response> results = new ArrayList<Response>();
    private SearchInteractor searchInteractor;
    private AbstractCustomerComponentFacade facade;
    private Checkbox chbBaseSearch;
    
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initSessionAttributes();
        initDependencies();
     
        List<Action> listCheckToOpenClient = ActionUtils.getCustomerActions(0, sessionAttributes.getUsername(), sessionAttributes.getPassword(), sessionAttributes.getSchema());
        btnCreateCustomer.setVisible(!listCheckToOpenClient.isEmpty());

        //Messagebox.show(Themes.getCurrentTheme());
    }

    private void initDependencies() {
        facade = CustomerWindowFacade.newInstance(
                self,
                "/customer/localcustomer.zul",
                "localcustomerWnd$composer");
        ((Window) facade.getProducedComponent()).setVisible(false);
        facade.getProducedComponent().addEventListener("onNotifyCustomer", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                Events.sendEvent(self,event);
            }
        });
        searchInteractor = new SearchInteractorImpl(sessionAttributes);
    }

    public List<Response> getResults() {
        return this.results;
    }

	public void onCheck$chbBaseSearch() {
		findSAP.setVisible(!chbBaseSearch.isChecked());
		findLocal.setVisible(chbBaseSearch.isChecked());
	}

    public void onClick$btnCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.initCreationAttributes(sessionAttributes.getBranch());
        createCustomer(customer);
    }

	public void onClick$btn_show_search_sap() {
		//hideAll();
		//chooseSearch.setVisible(true);
		//sap.setVisible(true);
		onCheck$chbBaseSearch();
	}

    public void createCustomer(Customer customer) throws Exception {
        facade.create(customer);
    }

    private boolean consumeMessages = false;

    public void consumeMessages(boolean bool) {
        this.consumeMessages = bool;
    }

    private void initSessionAttributes() {
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
    }


    public AbstractCustomerComponentFacade getFacade() {
        return facade;
    }

    public void setFacade(AbstractCustomerComponentFacade facade) {
        this.facade.getProducedComponent().detach();
        this.facade = facade;
    }


    public AnnotateDataBinder getBinder() {
        return binder;
    }

    public void setBinder(AnnotateDataBinder binder) {
        this.binder = binder;
    }

    public SessionAttributes getSessionAttributes() {
        return sessionAttributes;
    }

    public Customer convertToCustomer(Response response) {
        return new CustomerBuilder()
                .setIdSap(response.getSapId())
                .setBranch(response.getBranch())
                .setId(response.getNciId())
                .setP_family_local(response.getLastNameLocal())
                .setP_first_name_local(response.getFirstNameLocal())
                .setP_patronymic_local(response.getMiddleNameLocal())
                .setP_birthday(response.getBirthDay())
                //.setCode_type("N")
                .createCustomer();
    }

    public SearchInteractor getSearchInteractor() {
        return searchInteractor;
    }

    public void setSearchInteractor(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }
}
