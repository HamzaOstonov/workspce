package com.is.customer_.entrepreneur;

import com.is.customer_.core.composer.CustomerComposer;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.local.validator.CustomerValidator;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.sap.service.SearchBusinessPartnerInterface;
import com.is.customer_.sap.service.exception.SAPException;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by root on 04.06.2017.
 * 15:18
 */
public class IPComposer extends GenericForwardComposer {
    private final static Logger logger = Logger.getLogger(IPComposer.class);
    private Include customerInclude;
    private SessionAttributes sessionAttributes;
    private CustomerComposer composer;
    private SearchBusinessPartnerInterface searchService;
    private BusinessPartnerInterface customerService;

    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
        searchService = SAPServiceFactory.getInstance().getSearchService();
        customerService = SAPServiceFactory.getInstance().getBusinessPartnerService();
        composer = (CustomerComposer) customerInclude.getLastChild().getAttribute("customerWnd$composer");
        Customer customer = new Customer();
        customer.initCreationAttributes(sessionAttributes.getBranch());
        composer.refreshCustomer(customer);
    }

    public void onClick$createBtn() throws Exception {
        List<Customer> list = SAPServiceFactory.getInstance().getSearchService()
                .search(composer.getCustomer());

        if (list.size() > 0) {
            final String sapId = list.get(0).getIdSap();
            String caption = getCaption(sapId);
            Messagebox.show("Нашлись совпадения! Связать ? " + caption, null, Messagebox.OK | Messagebox.CANCEL,
                    Messagebox.NONE, new EventListener() {
                        @Override
                        public void onEvent(Event event) throws InterruptedException {
                            if (Messagebox.ON_OK.equals(event.getName())) {
                                composer.getCustomer().setIdSap(sapId);
                            }
                        }
                    });

        }
        String mode = null;
        if (composer.getCustomer().getIdSap() == null) {
            mode = "create";
        } else
            mode = "correct";

        composer.getCustomer().setName(composer.getCustomer().getFullName());

        CustomerValidator validator = CustomerValidator.getInstance(
                composer.getCustomer(), composer.getSessionAttributes());
        if (!validator.validate()){
            Messagebox.show(validator.getMessage());
            return;
        }
        IndividualEnterpreneur i = new IndividualEnterpreneur(composer.getCustomer(), mode);
        EventQueue eventQueue = EventQueues.lookup("IndividualEnterpreneurEQ", EventQueues.DESKTOP, true);

        eventQueue.publish(new Event(Events.ON_NOTIFY, null, i));
    }

    private String getCaption(String sapId) {
        String caption = null;
        try {
            Customer partner = customerService.get(null, null, sapId);
            caption = partner.getFullName() + " " + CustomerUtils.dateToString(partner.getP_birthday());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caption;
    }

    public void onClick$checkBtn() throws RemoteException, SAPException {
        Customer current = composer.getCustomer();
        if (current.getP_type_document().isEmpty() && current.getP_passport_serial().isEmpty() && current.getP_passport_number().isEmpty())
            return;
        else{
            List<Customer> results = searchService.search(current);
            if (results != null && results.size() > 0) {
                current = results.get(0);
                Customer customer = customerService.get(null,null,results.get(0).getIdSap());
                composer.refreshCustomer(customer);
            }
        }
    }

}
