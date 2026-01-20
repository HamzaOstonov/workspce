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
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractor;
import com.is.customer_.search.SAPSearch.boundaries.SearchInteractorImpl;
import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.ui.AbstractCustomerComponentFacade;
import com.is.customer_.ui.CustomerWindowFacade;
import com.is.utils.RefCBox;

/**
 * Created by root on 09.05.2017.
 * 16:20
 */
public class SAPSearchComposer extends GenericForwardComposer {
    private Logger logger = Logger.getLogger(SAPSearchComposer.class);

    // Components
    public Div userFormDiv, resultDiv;
    private Grid userFormGrid;
    private RefCBox documentType;
    public Listbox resultListbox;
    private Textbox documentNumber;
    private Checkbox isBranchSearch;
    public Toolbarbutton btnCreateCustomer;
    // Session and binding variables
    private SessionAttributes sessionAttributes;
    private Input userForm;
    private AnnotateDataBinder binder;
    // Interactor objects
    private List<Response> results = new ArrayList<Response>();
    private SearchInteractor searchInteractor;
    private AbstractCustomerComponentFacade facade;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        bindBeans(comp);
        initSessionAttributes();
        initDependencies();
        prepareUI();
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

    private void prepareUI() {
        self.addEventListener(Events.ON_OK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                if (userFormDiv.isVisible() && facade != null &&
                        facade.getProducer() != null && facade.getProducer().getProducedComponent() != null &&
                        !facade.getProducedComponent().isVisible())
                    onClick$btnSubmit();
            }
        });
        initModels();
        resultListbox.setItemRenderer(
                new ItemRenderer(
                        Events.ON_CLICK,
                        new SearchItemListener(
                                facade,
                                sessionAttributes)));
    }

    public List<Response> getResults() {
        return this.results;
    }

    private Button btnSubmit;

    public void onClick$btnSubmit() throws InterruptedException {
        binder.loadAll();
        search(userForm);
    }

    public void search(Input userForm) throws InterruptedException {
        resultDiv.setVisible(true);
        try {
            if (userForm.isEmpty())
                return;

            results = searchInteractor.search(userForm);
            if (results.size() == 0) {
                if (!consumeMessages)
                    Messagebox.show("Деловых партнеров не найдено. Delovix partnerov ne naydeno!");
                //onClick$btnCreateCustomer();
            } else if (results.get(0).getCount() > results.size())
                if (!consumeMessages)
                    Messagebox.show("Найденных записе больше 100. Naydennyx zapisey bolshe 100");

            resultListbox.setModel(new ListModelList(results));
        } catch (SearchInteractorImpl.SearchInteractionException e) {
            Messagebox.show(e.getMessage());
        } catch (Exception e) {
            Messagebox.show(e.getMessage());
        }
    }

    public void onClick$btnCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.initCreationAttributes(sessionAttributes.getBranch());
        createCustomer(customer);
    }

    public void createCustomer(Customer customer) throws Exception {
        //logger.error("Creating customer");
        facade.create(customer);
        if (facade != null && facade.getProducedComponent() != null)
            facade.getProducedComponent().setVisible(true);
    }

    public void onClick$btnClear() {
        Utils.clear(userFormGrid);
        userForm.clear();
        resultListbox.getItems().clear();
    }

    private boolean consumeMessages = false;

    public void consumeMessages(boolean bool) {
        this.consumeMessages = bool;
    }

    private void bindBeans(Component comp) {
        userForm = new Input();
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("userForm", this.userForm);
        binder.loadAll();
    }

    private void initModels() {
        documentType.setModel(new ListModelList(Utils.getDocuments_(sessionAttributes.getSchema())));
    }

    private void initSessionAttributes() {
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
    }

    public Input getUserForm() {
        return userForm;
    }

    public void setUserForm(Input userForm) {
        this.userForm = userForm;
    }

    public AbstractCustomerComponentFacade getFacade() {
        return facade;
    }

    public void setFacade(AbstractCustomerComponentFacade facade) {
        this.facade.getProducedComponent().detach();
        this.facade = facade;
    }

    public void setItemListener(String eventName, EventListener itemListener) {
        resultListbox.setItemRenderer(new ItemRenderer(eventName, itemListener));
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
