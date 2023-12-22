package com.is.customer_.search.SAPSearch;

import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.search.SAPSearch.model.Response;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * Created by root on 06.07.2017.
 * 10:16
 */
class ItemRenderer implements ListitemRenderer {
    private final EventListener itemListener;
    private final String eventName;

    public ItemRenderer(String eventName, EventListener itemListener) {
        this.itemListener = itemListener;
        this.eventName = eventName;
    }

    @Override
    public void render(Listitem item, Object data) throws Exception {
        Response searchResult = (Response) data;
        item.appendChild(new Listcell(searchResult.getSapId()));
        item.appendChild(new Listcell(searchResult.getLastNameLocal()));
        item.appendChild(new Listcell(searchResult.getFirstNameLocal()));
        item.appendChild(new Listcell(searchResult.getMiddleNameLocal()));
        item.appendChild(new Listcell(CustomerUtils.dateToString(searchResult.getBirthDay())));
        item.setAttribute("searchResult", searchResult);
        setItemListener(item);
    }

    protected void setItemListener(Listitem listitem){
        listitem.addEventListener(eventName, itemListener);
    }
}
