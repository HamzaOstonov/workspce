package com.is.customer_.search.searchBaseLocal;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.ReferenceDecoder;



class LocalItemRenderer implements ListitemRenderer {
    private final EventListener itemListener;
    private final String eventName;

    public LocalItemRenderer(String eventName, EventListener itemListener) {
        this.itemListener = itemListener;
        this.eventName = eventName;
    }

    @Override
    public void render(Listitem item, Object data) throws Exception {
		final Customer _data = (Customer) data;
		item.appendChild(new Listcell(_data.getId()));
		item.appendChild(new Listcell(ReferenceDecoder.getStateName(_data.getState())));
		item.appendChild(new Listcell(_data.getP_family()));
		item.appendChild(new Listcell(_data.getP_first_name()));
		item.appendChild(new Listcell(_data.getP_patronymic()));
		item.appendChild(new Listcell(CustomerUtils.dateToString(_data.getP_birthday())));
		item.appendChild(new Listcell(_data.getP_passport_serial()));
		item.appendChild(new Listcell(_data.getP_passport_number()));
		item.appendChild(new Listcell(_data.getP_post_address()));

		item.setAttribute("searchResult", _data);
        setItemListener(item);
        
    }    
    
    
    protected void setItemListener(Listitem listitem){
        listitem.addEventListener(eventName, itemListener);
    }
}
