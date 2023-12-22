package com.is.customer_.mapping;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.customer_.local.service.CustomerActionInterface;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import java.util.List;

/**
 * Created by root on 31.05.2017.
 * 16:56
 */
public class MappingRenderer implements ListitemRenderer {
    private final String eventName;
    private Logger logger = Logger.getLogger(MappingRenderer.class);
    private final EventListener itemListener;
    private final SessionAttributes sessionAttributes;
    private CustomerActionInterface localCustomerService;

    public MappingRenderer(String eventName, EventListener itemListener, CustomerActionInterface service, SessionAttributes sessionAttributes) {
        this.eventName = eventName;
        this.itemListener = itemListener;
        this.localCustomerService = service;
        this.sessionAttributes = sessionAttributes;
    }

    @Override
    public void render(Listitem item, Object data) throws Exception {
        try {
            Customer customer = (Customer) data;
            if (customer == null)
                return;
            String idSAP = customer.getIdSap();
            try {
                customer = localCustomerService.getCustomer(customer.getBranch(), customer.getId());
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
                return;
            }
            customer.setIdSap(idSAP);
            if (MappingResolver.isCustomer(customer.getBranch(), customer.getId())) {
                item.appendChild(new Listcell(customer.getBranch()));
                item.appendChild(new Listcell(customer.getId()));
                item.appendChild(new Listcell(ReferenceDecoder.getStateName(customer.getState())));
                item.appendChild(new Listcell(customer.getName()));
                item.appendChild(new Listcell(CustomerUtils.dateToString(customer.getP_birthday())));
                item.appendChild(new Listcell(customer.getP_type_document()));
                item.appendChild(new Listcell(customer.getP_passport_serial()));
                item.appendChild(new Listcell(customer.getP_passport_number()));
                item.appendChild(new Listcell(customer.getP_post_address()));
                if (MappingResolver.isBranchCustomer(customer.getBranch(), customer.getId(),
                        sessionAttributes.getBranch())) {
                    item.setAttribute("customer", customer);
                    item.addEventListener(eventName, itemListener);
                } else
                    item.setDisabled(true);
            }
        }
        catch(Exception e){
            logger.error(CheckNull.getPstr(e));
        }
    }

    private int getUndisabledCount(Listitem item) {
        int count = 0;
        List<Listitem> items = item.getListbox().getItems();
        for (Listitem listitem: items){
            if (!listitem.isDisabled())
                count += 1;
        }
        return count;
    }

    private int getBranchCount(ListModel listModel){
        int count = 0;
        for (int i = 0; i < listModel.getSize(); i++){
            Customer customer = (Customer) listModel.getElementAt(i);
            if (customer != null &&
                    MappingResolver.isBranchCustomer(
                            customer.getBranch(),
                            customer.getId(),
                            sessionAttributes.getBranch()))
                count += 1;
        }
        return count;
    }
}
