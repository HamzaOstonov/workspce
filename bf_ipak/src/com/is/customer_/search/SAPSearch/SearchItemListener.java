package com.is.customer_.search.SAPSearch;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.local.service.CustomerActionInterface;
import com.is.customer_.local.service.CustomerActionService;
import com.is.customer_.mapping.MappingResolver;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.BusinessPartnerMappingInterface;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.search.SAPSearch.model.Response;
import com.is.customer_.search.searchBaseLocal.SearchService;
import com.is.customer_.ui.AbstractCustomerComponentFacade;
import com.is.customer_.ui.CustomerComposerInteractor;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import java.util.List;

/**
 * Created by root on 07.08.2017.
 * 19:33
 */
public class SearchItemListener implements EventListener {
    protected SessionAttributes sessionAttributes;
    protected AbstractCustomerComponentFacade facade;
    private BusinessPartnerMappingInterface mappingService;
    private CustomerActionInterface actionService;
    private BusinessPartnerInterface sapCustomerService;
    private SearchService searchService;

    private final static  Logger logger = Logger.getLogger(SearchItemListener.class);

    public SearchItemListener(AbstractCustomerComponentFacade facade, SessionAttributes sessionAttributes) {
        this.facade = facade;
        this.sessionAttributes = sessionAttributes;
        this.mappingService = SAPServiceFactory.getInstance().getMappingService();
        this.sapCustomerService = SAPServiceFactory.getInstance().getBusinessPartnerService();
        this.actionService = CustomerActionService.getInstance(sessionAttributes);
        this.searchService = SearchService.getInstance();
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Customer customer = convertToCustomer((Response) event.getTarget().getAttribute("searchResult"));
        customer.setEmp_id(""+sessionAttributes.getUid()); /*end 2022.07.06*/ 
        List<Customer> list = MappingResolver.sortList(
                mappingService.getMapping(null, null, customer.getIdSap()),
                sessionAttributes.getBranch());
        if (customer.isSAPCustomer()) {
            int branchCount = 0;
            Customer branchCustomer = null;
            for (int i = 0; i < list.size(); i++) {
                /*logger.error("Mapping equals " + list.get(i).getBranch()
                            + " "
                            + list.get(i).getId()
                            + " " + sessionAttributes.getBranch());*/
                if (MappingResolver.isBranchCustomer(list.get(i).getBranch(),
                        list.get(i).getId(),
                        sessionAttributes.getBranch())) {
                    branchCustomer = list.get(i);
                    if (MappingResolver.
                            isBranchCustomerExists(branchCustomer.getBranch(), branchCustomer.getId()))
                        branchCount += 1;
                }
            }
            if (branchCount == 1) {
                //logger.error("Branch count 1");
                branchCustomer = actionService.getCustomer(
                        branchCustomer.getBranch(), branchCustomer.getId());
                branchCustomer.setIdSap(customer.getIdSap());
                showBranchCustomer(branchCustomer);
            }
            else if (branchCount > 1) {
                //logger.error("Branch Count > 1");
                showMapping(list);
            }
            else {
                //logger.error("Branch Count < 1");
                Customer customer_ = sapCustomerService.get(null, null, customer.getIdSap());
                //logger.error("Search by Identification Customer " + customer_ + " ID SAP " + customer.getIdSap());
                List<Customer> results = searchService.getByIdentification(customer_, sessionAttributes.getSchema());
                //logger.error("Search By Identification results " + results.size());
                if (results != null && results.size() > 0) {
                    //logger.error("Local results  " + results.toString());
                    showMapping(results);
                } else
                    showNotBranchCustomer(customer_);
            }
        }
        else{
            showLocalCustomer(customer);
        }
    }

    private void showLocalCustomer(Customer customer) throws Exception {
        customer = actionService.getCustomer(customer.getBranch(), customer.getId());
        customer.setEmp_id(""+sessionAttributes.getUid()); /*end 2022.07.06*/ 
        showBranchCustomer(customer);
    }

    protected void showBranchCustomer(Customer branchCustomer) throws Exception {
        formatWindow();
        facade.getInteractor().show(branchCustomer);
        formatWindowTitle(branchCustomer);
    }

    protected void showNotBranchCustomer(Customer customer_) throws Exception {
        formatWindow();
        customer_.initBaseAttributes(sessionAttributes.getBranch());
        facade.getInteractor().show(customer_);
        formatWindowTitle(customer_);
    }

    protected void showMapping(List<Customer> mappings) throws Exception {
        formatWindow();
        facade.getInteractor().show(mappings);
    }

    private void formatWindow() throws InterruptedException {
        ((Window)facade.getProducedComponent()).setVisible(true);
        ((Window)facade.getProducedComponent()).doModal();
    }


    private void formatWindowTitle(Customer customer){
        ((Window) facade.getProducedComponent()).setTitle(String.format("%s дата рождения %s",
                customer.getName() == null ? customer.getFullName() : customer.getName(),
                CustomerUtils.dateToString(customer.getP_birthday())));
        ((Window) facade.getProducedComponent()).setStyle("font-weight:bold");
    }


    private Customer convertToCustomer(Response response) {
        return new CustomerBuilder()
                .setIdSap(response.getSapId())
                .setBranch(response.getBranch())
                .setId(response.getNciId())
                .setP_family_local(response.getLastNameLocal())
                .setP_first_name_local(response.getFirstNameLocal())
                .setP_patronymic_local(response.getMiddleNameLocal())
                .setP_birthday(response.getBirthDay())
                .createCustomer();
    }
}
