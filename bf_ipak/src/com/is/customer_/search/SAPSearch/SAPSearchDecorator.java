package com.is.customer_.search.SAPSearch;

import com.is.ISLogger;
import com.is.customer_.search.SAPSearch.model.Input;
import com.is.customer_.core.model.Customer;
import com.is.utils.CheckNull;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import java.util.Iterator;

/**
 * Created by root on 18.05.2017.
 * 11:50
 */
public class SAPSearchDecorator {
    public static final String SAPSEARCH_ZUL = "/customer/SAPSearch.zul";
    public static final String SEARCH_WND_COMPOSER = "SAPSearchWnd$composer";
    private Window searchWnd;
    private Component component;
    private SAPSearchComposer composer;

    private SAPSearchDecorator() {
    }

    public static SAPSearchDecorator getInstance() {
        return new SAPSearchDecorator();
    }

    public void init(Window window) {
        attachToWindow(window);
        initSearchComposer();
    }

    public Window getWindow(){
        return searchWnd;
    }

    private void attachToWindow(Window window) {
        searchWnd = (Window) Executions.createComponents(SAPSEARCH_ZUL, window, null);
        searchWnd.setVisible(false);
        searchWnd.setHeight("100%");
        searchWnd.setWidth("100%");
        searchWnd.setPosition("center,top");
        searchWnd.setContentStyle("overflow:auto");
        searchWnd.setTitle("Поиск Клиента");
        searchWnd.setBorder("normal");
        searchWnd.setClosable(true);

        searchWnd.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                searchWnd.setVisible(false);
                event.stopPropagation();
            }
        });
    }

    private void initSearchComposer() {
        composer = (SAPSearchComposer) searchWnd.getAttribute(SEARCH_WND_COMPOSER);
        component = composer.getFacade().getProducedComponent();
        Iterator listenerIterator = component.getListenerIterator(Events.ON_CLOSE);
        while (listenerIterator.hasNext()) {
            listenerIterator.next();
            listenerIterator.remove();
        }

        component.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                component.setVisible(false);
                searchWnd.setVisible(false);
                event.stopPropagation();
            }
        });
    }

    public void showCustomer(Customer customer) throws InterruptedException {
        searchWnd.setVisible(true);
        searchWnd.doModal();
        composer.setUserForm(formUserForm(customer));
        composer.consumeMessages(true);
        composer.onClick$btnSubmit();
        composer.userFormDiv.setVisible(false);

        if (composer.getResults().size() == 0) {
            try {
                customer.initCreationAttributes(Executions.
                        getCurrent().
                        getSession().
                        getAttribute("branch").toString());
                customer.setId(null);
                composer.createCustomer(customer);
            } catch (Exception e) {
                ISLogger.getLogger().error(CheckNull.getPstr(e));
            }
        }
    }

    private Input formUserForm(Customer customer) {
        Input userForm = new Input();
        userForm.setDocumentNumber(customer.getP_passport_number());
        userForm.setDocumentType(customer.getP_type_document());
        userForm.setDocumentSerial(customer.getP_passport_serial());
        return userForm;
    }
}
