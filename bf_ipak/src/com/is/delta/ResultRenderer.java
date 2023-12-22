package com.is.delta;

import com.is.customer_.core.utils.CustomerUtils;
import com.is.delta.di.CustomerEventListenerContext;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * Created by root on 22.04.2017.
 * 16:56
 */
public class ResultRenderer implements ListitemRenderer {

    private final CustomerEventListenerContext customerContext;
    private final Controller composer;

    public ResultRenderer(Controller composer) {
        this.composer = composer;
        this.customerContext = CustomerEventListenerContext.getInstance();
    }

    @Override
    public void render(Listitem listitem, Object o) throws Exception {
        DELTARecord data = (DELTARecord) o;
        listitem.appendChild(new Listcell(data.getBranch()));
        listitem.appendChild(new Listcell(data.getClient_id()));
        listitem.appendChild(new Listcell(DictionaryService.getCustomerType(data.getCustomer_type())));
        listitem.appendChild(new Listcell(data.getName()));
        listitem.appendChild(new Listcell(
                DictionaryService.getActionName(data.getCustomer_type(), data.getAction_type())));
        listitem.appendChild(new Listcell(DictionaryService.getStateName(data.getState())));
        listitem.appendChild(new Listcell(data.getMessage()));
        listitem.appendChild(new Listcell(CustomerUtils.dateToString(data.getV_date())));
        listitem.addEventListener(Events.ON_DOUBLE_CLICK, customerContext.
                getImplementation(composer, data));
    }
}
