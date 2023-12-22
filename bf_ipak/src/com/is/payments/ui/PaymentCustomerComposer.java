package com.is.payments.ui;

import com.is.ISLogger;
import com.is.customer_.core.composer.CustomerComposer;
import com.is.customer_.core.model.Customer;
import com.is.customer_.local.reports.ExcelOfficeReport;
import com.is.customer_.local.service.CustomerActionInterface;
import com.is.customer_.local.service.CustomerActionService;
import com.is.customer_.local.validator.CustomerValidator;
import com.is.customer_.sap.service.BusinessPartnerInterface;
import com.is.customer_.sap.service.BusinessPartnerMappingInterface;
import com.is.customer_.sap.service.exception.SAPException;
import com.is.customer_.sap.service.SAPServiceFactory;
import com.is.customer_.ui.CustomerComposerInteractor;
import com.is.payments.PaymentUtils;
import com.is.payments.service.PaymentCustomerService;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by root on 22.05.2017.
 * 11:17
 */
public class PaymentCustomerComposer extends GenericForwardComposer implements CustomerComposerInteractor {
    private static final Logger logger = ISLogger.getLogger();
    private static final java.lang.String CUSTOMER_WND_ATTRIBUTE = "customerWnd$composer";
    private Tab internalControlTab;
    private Include customerInclude;
    private Include internalInclude;
    private CustomerComposer composer;
    private BusinessPartnerInterface service;
    private BusinessPartnerMappingInterface mappingService;
    private PaymentCustomerService paymentCustomerService;
    private CustomerActionInterface customerActionService;

    private Div appsDiv;
    private Window appsWnd;

    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        List children = customerInclude.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof Window) {
                composer = (CustomerComposer) ((Window) children.get(i)).getAttribute(CUSTOMER_WND_ATTRIBUTE);
            }
        }
        service = SAPServiceFactory.getInstance().getBusinessPartnerService();
        mappingService = SAPServiceFactory.getInstance().getMappingService();
        paymentCustomerService = new PaymentCustomerService(composer.getSessionAttributes());
        customerActionService = CustomerActionService.getInstance(composer.getSessionAttributes());
    }

    /*public void showCustomer(Customer customer_) {
        try {
            Customer returnCustomer = new Customer();
            if (customer_.isSAPCustomer()) {
                returnCustomer = service.get(null,null,customer_.getIdSap());
                List<Customer> list = mappingService.getMapping(null,null,customer_.getIdSap());
                for (Customer customer: list)
                    if (customer.getBranch() != null && customer.getId().contains("N")
                            && customer.getBranch().equalsIgnoreCase(composer.getSessionAttributes().getBranch())){
                        returnCustomer.setBranch(customer.getBranch());
                        returnCustomer.setId(customer.getId().replace("N",""));
                    }
            }
            else{
                returnCustomer = paymentCustomerService.getCustomer(customer_.getBranch(),customer_.getId());
            }

            composer.refreshCustomer(returnCustomer);
            if (returnCustomer.getBranch() != null && returnCustomer.getId() != null)
                showInternalControl(returnCustomer);

        } catch (SAPException e) {
            logger.error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        } catch (RemoteException e) {
            throw new RuntimeException("Проблема с подключением к веб сервису SAP CRM");
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        }
    }
*/

    public void onClick$btnConnect() throws Exception {
        CustomerValidator customerValidator = CustomerValidator.getInstance(composer.getCustomer(),
                composer.getSessionAttributes());
        if (!customerValidator.validate())
            throw new RuntimeException(customerValidator.getMessage());
        try {
            PaymentUtils.checkStopList(composer.getSessionAttributes(), composer.getCustomer());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        composer.getCustomer().setBranch(composer.getSessionAttributes().getBranch());

        Executions.getCurrent().getDesktop().setAttribute("paymentCustomer", composer.getCustomer());
        self.setVisible(false);
        Events.sendEvent(Events.ON_NOTIFY, self, composer.getCustomer());
        //sendToEQ(composer.getCustomer());
    }


    public void createCustomer(Customer customer) {
        Customer customerForCreation = new Customer();
        customerForCreation.initCreationAttributes(session.getAttribute("branch").toString());
        composer.refreshCustomer(customerForCreation);
    }

    private void sendToEQ(Customer customer) {
        //EventQueue eventQueue = EventQueues.lookup("PAYMENT_CUSTOMER",EventQueues.DESKTOP,false);
        //eventQueue.publish(new Event(Events.ON_NOTIFY, null, customer));
    }

    public void showInternalControl(Customer customer) {
        try {
            internalControlTab.setVisible(true);
            /*internalControlTab.getChildren().clear();
            internalControlTab.appendChild(new Include(
                    "clientaddinfo.zul?branch=" + branch +
                    "&client_id=" + id +
                    "&code_subject=N&alias=" + composer.getSessionAttributes().getSchema()));*/

            internalInclude.setSrc("clientaddinfo.zul?branch=" + customer.getBranch() +
                    "&client_id=" + customer.getId() +
                    "&code_subject=N&alias=" + composer.getSessionAttributes().getSchema());
        } catch (Exception e) {
            /*throw new RuntimeException("Branch " + branch
                    + " \nId " + id
                    + " \nSchema " + composer.getSessionAttributes()
                                        .getSchema());*/
            throw new RuntimeException(e.getMessage());
        }
    }

    public void onSelect$paymentTabbox(SelectEvent event) throws CloneNotSupportedException {
        Tab selectedComponent = (Tab) event.getTarget();
        if (selectedComponent.getId().equalsIgnoreCase("internalControlTab"))
            showInternalControl(composer.getCustomer());
        else if (selectedComponent.getId().equalsIgnoreCase("appsTab")) {
            if (appsWnd == null)
                appsWnd = (Window) Executions.createComponents("/customer/apps.zul", appsDiv, null);
            appsWnd.setVisible(true);
            Customer clone = (Customer) composer.getCustomer().clone();
            clone.setId("N" + composer.getCustomer().getId());
            Events.sendEvent("onUploadApps", appsWnd, clone);
        }
    }

    public void onClick$btnCorrect() throws Exception {
        CustomerValidator customerValidator = CustomerValidator.getInstance(composer.getCustomer(),
                composer.getSessionAttributes());
        if (!customerValidator.validate())
            throw new RuntimeException(customerValidator.getMessage());
        PaymentCustomerService paymentCustomerService = new PaymentCustomerService(composer.getSessionAttributes());
        paymentCustomerService.update(composer.getCustomer());
        Messagebox.show("Успешно");
    }

    @Override
    public void show(List<Customer> customer_) throws Exception {

    }

    @Override
    public void show(Customer customer_) throws Exception {
        try {
            Customer returnCustomer = new Customer();
            if (customer_.isSAPCustomer()) {
                returnCustomer = service.get(null, null, customer_.getIdSap());
                List<Customer> list = mappingService.getMapping(null, null, customer_.getIdSap());
                for (Customer customer : list)
                    if (customer.getBranch() != null && customer.getId().contains("N")
                            && customer.getBranch().equalsIgnoreCase(composer.getSessionAttributes().getBranch())) {
                        returnCustomer.setBranch(customer.getBranch());
                        returnCustomer.setId(customer.getId().replace("N", ""));
                    }
            } else {
                // Если это физический клиент
                if (customer_.isCustomer()) {
                    returnCustomer = customerActionService.getCustomer(customer_.getBranch(), customer_.getId());
                    //returnCustomer.setId(null);
                } else {
                    //Messagebox.show(customer_.toString());
                    returnCustomer = paymentCustomerService.getCustomer(customer_.getBranch(), customer_.getId());
                    returnCustomer.setCode_type(null);

                }
            }
            composer.refreshCustomer(returnCustomer);
            if (returnCustomer.getBranch() != null && returnCustomer.getId() != null &&
                    !returnCustomer.isCustomer())
                showInternalControl(returnCustomer);


        } catch (SAPException e) {
            logger.error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        } catch (RemoteException e) {
            throw new RuntimeException("Проблема с подключением к веб сервису SAP CRM");
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void create(Customer customer) throws Exception {
        Customer customer_ = new Customer();
        customer.initCreationAttributes(composer.getSessionAttributes().getBranch());
        composer.setCustomer(customer);
    }

    @Override
    public Customer getCurrentCustomer() throws Exception {
        return null;
    }
}
