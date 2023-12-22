package com.is.delta;

import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.sap.EmergencyMode;
import com.is.delta.core.Criteria;
import com.is.delta.core.PagingDao;
import com.is.utils.RefCBox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import org.zkoss.zul.event.PagingEvent;

/**
 * Created by root on 22.04.2017.
 * 13:30
 */
public class Controller extends GenericForwardComposer {
    // --- Components
    public Window includeWnd;
    public Include includeWnd$includePage;
    private Paging paging;
    private Listbox resultListbox;
    private RefCBox customer_type,action_type,state_type;
    private Label statusbar;
    // ---
    private SessionAttributes sessionAttributes;
    private AnnotateDataBinder binder;
    private DELTARecord current = new DELTARecord();
    private DELTARecordFilter filter = new DELTARecordFilter();
    private int _startPageNumber = 0;
    private final int _pageSize = 15;
    private boolean _needsTotalSizeUpdate = true;

    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        bindBeans(comp);
        init();
        verifyAccess();
        includeWnd.addEventListener(Events.ON_CLOSE, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                includeWnd.setVisible(false);
                event.stopPropagation();
                refreshModel(_startPageNumber);
            }
        });
    }

    private void init() {
        initSessionAttributes();
        initModels();
        resultListbox.setItemRenderer(new ResultRenderer(this));
    }

    private void bindBeans(Component comp) {
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", current);
        binder.bindBean("filter", filter);
        binder.loadAll();
    }

    /**
     * Session attributes initialization
     * schema,branch,user id and etc
     *
     * @param session
     */
    private void initSessionAttributes() {
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
        //filter.setUser_id(sessionAttributes.getUid());
        filter.setBranch(sessionAttributes.getBranch());
    }

    private void initModels() {
        customer_type.setModel(new ListModelList(DictionaryService.getCustomerTypes(sessionAttributes.getSchema())));
        state_type.setModel(new ListModelList(DictionaryService.getStateTypes(sessionAttributes.getSchema())));
    }

    private void verifyAccess() {

    }

    public void onPaging$paging(ForwardEvent event){
        PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
        int startPageNumber = pagingEvent.getActivePage();
        refreshModel(startPageNumber);
    }

    private void refreshModel(int _startPageNumber) {
        if (customer_type.getValue() != null
                && customer_type.getValue().equals("02"))
            refreshJ(_startPageNumber);
        else if (customer_type.getValue() != null && customer_type.getValue().equalsIgnoreCase("01"))
            refreshP(_startPageNumber);
        else if (customer_type.getValue() != null && customer_type.getValue().equalsIgnoreCase("04"))
            refreshOther(_startPageNumber);
    }

    private void refreshOther(int startPageNumber) {
        PagingDao service = PagingDaoAddinfoService.getInstance(sessionAttributes);
        Criteria criteria = new Criteria.Builder()
                                .pageIndex(startPageNumber)
                                .pageSize(_pageSize)
                                .filter(filter)
                                .build();
        int count = service.getCount(criteria);
        ListModelList list = new ListModelList(service.getData(criteria));
        paging.setPageSize(_pageSize);
        paging.setTotalSize(service.getCount(criteria));
        resultListbox.setModel(new ListModelList(list));
        statusbar.setValue(String.format("Общее количество записей %d", count));
    }

    private void refreshP(int _startPageNumber) {
        PagingDaoService service = PagingDaoService.getInstance(sessionAttributes);
        Criteria criteria = new Criteria.Builder().
                pageIndex(_startPageNumber).
                pageSize(_pageSize).
                filter(filter).
                build();
        int count = service.getCount(criteria);
        ListModelList list = new ListModelList(service.getData(criteria));
        paging.setPageSize(_pageSize);
        paging.setTotalSize(service.getCount(criteria));
        resultListbox.setModel(new ListModelList(list));
        statusbar.setValue(String.format("Общее количество записей %d", count));
    }

    private void refreshJ(int _startPageNumber) {
        PagingDao<DELTARecord> service = PagingJDao.getInstance(sessionAttributes);
        Criteria criteria = new Criteria.Builder().
                pageIndex(_startPageNumber).
                pageSize(_pageSize).
                filter(filter).
                build();
        int count = service.getCount(criteria);
        paging.setPageSize(_pageSize);
        paging.setTotalSize(count);
        resultListbox.setModel(new ListModelList(service.getData(criteria)));
        statusbar.setValue(String.format("Общее количество записей %d", count));
    }

    public void onClick$btn_refresh(){
        refreshModel(_startPageNumber);
    }

    public void onChange$customer_type(){
        filter.setCustomer_type(customer_type.getValue());
        action_type.setModel(new ListModelList(DictionaryService.getActionTypes(customer_type.getValue(),
                                                    sessionAttributes.getSchema())));
    }

    public void onClick$btnTurnOnEmergencyMode() throws InterruptedException {
        EmergencyMode.turnOnEmergencyMode();
        com.is.customer_.sap.EmergencyMode.turnOnEmergencyMode();
        Messagebox.show("Аварийный режим включен");
    }

    public void onClick$btnTurnOffEmergencyMode() throws InterruptedException {
        EmergencyMode.turnOffEmergencyMode();
        com.is.customer_.sap.EmergencyMode.turnOffEmergencyMode();
        Messagebox.show("Аварийный режим выключен");
    }
    /**
     * Getters for binding
     *
     * @return
     */
    public DELTARecord getCurrent() {
        return current;
    }
    /**
     * Setter for binding
     *
     * @param current
     */
    public void setCurrent(DELTARecord current) {
        this.current = current;
    }

    public DELTARecordFilter getFilter() {
        return filter;
    }

    public void setFilter(DELTARecordFilter filter) {
        this.filter = filter;
    }

}
