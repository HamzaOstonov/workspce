package com.is.soato;


import com.is.clientcrm.DictionaryKeeper;
import com.is.utils.RefCBox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

import java.util.List;


public class SoatoViewController extends GenericForwardComposer {
    private AnnotateDataBinder binder;

    private Soato current = new Soato();
    private Soato filter = new Soato();

    private SoatoService service;

    private final int _pageSize = 15;
    private Paging paging;

    private Listbox resultListbox;
    private RefCBox region;
    private RefCBox distr;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        binder = new AnnotateDataBinder(component);
        binder.bindBean("current", this.current);
        binder.bindBean("filter",this.filter);
        binder.loadAll();
        service = new SoatoService();
        resultListbox.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem listitem, Object o) throws Exception {
                Soato soato = (Soato) o;
                listitem.appendChild(new Listcell(soato.getKod_soat()));
                listitem.appendChild(new Listcell(soato.getKod_gni()));
                listitem.appendChild(new Listcell(soato.getRegion()));
                listitem.appendChild(new Listcell(soato.getRegion_name()));
                listitem.appendChild(new Listcell(soato.getDistr()));
                listitem.appendChild(new Listcell(soato.getDistr_name()));
                listitem.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        onClick$btnSelect();
                    }
                });
            }
        });
        region.setModel(new ListModelList(DictionaryKeeper.getRegions()));
        self.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                self.detach();
                event.stopPropagation();
            }
        });
        refreshModel(0);
    }

    public void onPaging$paging(ForwardEvent event) throws InterruptedException {
        PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
        int startPageNumber = pagingEvent.getActivePage();
        refreshModel(startPageNumber);
    }

    private void refreshModel(int startPageNumber) {
        Criteria criteria = new Criteria.Builder().
                pageIndex(startPageNumber).
                pageSize(_pageSize).
                filter(this.filter).
                build();
        paging.setPageSize(_pageSize);
        paging.setTotalSize(service.getCount(criteria));
        List<Soato> list = service.getData(criteria);
        resultListbox.setModel(new BindingListModelList(list,true));
    }

    public void onClick$btnRefresh() throws InterruptedException {
        refreshModel(0);
        /*String toString = "";
        for (int i = 0; i < filter.getFilterFields().size(); i++){
            toString = filter.getFilterFields().get(i).toString() + "\n";
        }
        toString = filter.getRegion();
        Messagebox.show(toString + "\n" + filter.getFilterFields().size());*/
    }

    public void onClick$btnClear(){
        filter = new Soato();
        binder.loadAll();
    }

    public void onClick$btnSelect() throws Exception {
        if (current == null)
            throw new Exception("Current is null");
        Soato soato = (Soato) current.clone();
        soato.setKod_soat("17" + soato.getKod_soat());
        Events.sendEvent("onNotifySoato",self,soato);
        self.detach();
    }

    public void onChange$region_value(InputEvent event){
        filter.setRegion(event.getValue());
        distr.setModel(new BindingListModelList(DictionaryKeeper.getDistrict_by_region(
                event.getValue()),true));
    }

    public void onChange$region(){
        filter.setRegion(region.getValue());
        distr.setModel(new BindingListModelList(DictionaryKeeper.getDistrict_by_region(
                region.getValue()),true));
        binder.loadAll();
    }

    public Soato getCurrent() {
        return current;
    }

    public void setCurrent(Soato current) {
        this.current = current;
    }

    public Soato getFilter() {
        return filter;
    }

    public void setFilter(Soato filter) {
        this.filter = filter;
    }
}
