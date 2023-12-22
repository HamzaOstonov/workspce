
package com.is.batch;

import com.is.account.model.Account;
import com.is.customer_.core.ReferenceDictionary;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.CustomerUtils;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.customer_.core.utils.ReferenceDecoder;
import com.is.customer_.search.SAPSearch.SAPSearchDecorator;
import com.is.delta.core.Criteria;
import com.is.utils.RefCBox;
import org.apache.log4j.Logger;
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
 * Created by root on 06.05.2017.
 * 16:53
 */
public class BatchComposer extends GenericForwardComposer {
    /*private Window includeWnd;
    private Include includeWnd$customerIncl_;
    private Include includeWnd$sapSearchInclude_;*/
    private Paging paging;
    private Listbox resultListbox;
    private RefCBox state, accountType;
    private Textbox accountBal, accountNum;
    private SessionAttributes sessionAttributes;
    private AnnotateDataBinder binder;
    private int _startPageNumber = 0;
    private final int _pageSize = 15;
    private boolean _needsTotalSizeUpdate = true;
    private BatchFilter current;
    private BatchService service;
    private Logger logger = Logger.getLogger(BatchComposer.class);
    private SAPSearchDecorator searchDecorator;

    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
        bindBeans(comp);
        initSessionAttributes();
        service = BatchService.getInstance(sessionAttributes);
        accountType.setModel(new ListModelList(service.getTypeAccounts(sessionAttributes.getSchema())));
        state.setModel(new ListModelList(ReferenceDictionary.getState(sessionAttributes.getSchema())));

        searchDecorator = SAPSearchDecorator.getInstance();
        searchDecorator.init((Window) self);

        resultListbox.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem item, Object data) throws Exception {
                final Batch batch = (Batch) data;
                item.appendChild(new Listcell(batch.getBranch()));
                item.appendChild(new Listcell(batch.getOrganizationId()));
                item.appendChild(new Listcell(batch.getOrganizationName()));
                item.appendChild(new Listcell(batch.getId_client()));
                item.appendChild(new Listcell(ReferenceDecoder.getStateName(batch.getState())));
                item.appendChild(new Listcell(batch.getEmbossed_ch_name()));
                item.appendChild(new Listcell(CustomerUtils.dateToString(batch.getBirth_date())));
                item.appendChild(new Listcell(batch.getMessage()));

                item.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
                    @Override
                    public void onEvent(Event event) throws Exception {
                        Customer customer = service.getCustomer(batch);
                        searchDecorator.showCustomer(customer);
                        searchDecorator.getWindow().addEventListener("onNotifyCustomer", new EventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                Customer customer = (Customer) event.getData();
                                if (customer != null
                                        && customer.getBranch() != null
                                        && customer.getId() != null) {
                                    batch.setId_client(customer.getId());
                                    batch.setState(customer.getState());
                                    service.update(batch);
                                }
                            }
                        });
                        /*EventQueues.remove("CUSTOMER_SAP_EQ");
                        final EventQueue eventQueue = EventQueues.lookup("CUSTOMER_SAP_EQ",true);
                        eventQueue.subscribe(new EventListener() {
                            @Override
                            public void onEvent(Event event) throws Exception {
                                if (event.getName().equalsIgnoreCase(Events.ON_NOTIFY)){
                                    if (!desktop.isAlive() || self.getPage() == null){
                                        eventQueue.unsubscribe(this);
                                        return;
                                    }
                                    Customer customer = (Customer) event.getData();
                                    if (customer != null
                                            && customer.getBranch() != null
                                            && customer.getId()!=null) {
                                        batch.setId_client(customer.getId());
                                        batch.setState(customer.getState());
                                        service.update(batch);
                                    }
                                    //EventQueues.remove("CUSTOMER_SAP_EQ");
                                }
                            }
                        });*/
                    }
                });
            }
        });
        refreshModel(0);
    }

    private void bindBeans(Component comp) {
        current = new BatchFilter();
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
    }

    public void onPaging$paging(ForwardEvent event) {
        PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
        int startPageNumber = pagingEvent.getActivePage();
        refreshModel(startPageNumber);
    }

    private void refreshModel(int startPageNumber) {
        Criteria criteria = new Criteria.Builder().
                pageIndex(startPageNumber).
                pageSize(_pageSize).
                filter(this.current).
                build();
        paging.setPageSize(_pageSize);
        paging.setTotalSize(service.getCount(criteria));
        resultListbox.setModel(new ListModelList(service.getData(criteria)));
    }

    private void initSessionAttributes() {
        sessionAttributes = GeneralUtils.getSessionAttributes(session);
        this.current.setBranch(sessionAttributes.getBranch());
    }

    public void onClick$packageOpen() throws Exception {
        service.packageOpen();
        refreshModel(_startPageNumber);
    }

    public void onClick$packageConfirmation() throws Exception {
        service.packageConfirmation();
        refreshModel(_startPageNumber);
    }

    public void onClick$btnRefresh() {
        refreshModel(_startPageNumber);
    }


    public void onChange$accountType() {
        String value = accountType.getValue();
        Account account = service.getBalanceAccount(value, sessionAttributes.getSchema());
        accountBal.setValue(account.getAcc_bal());
        accountNum.setValue(account.getId_order());
    }

    public BatchFilter getCurrent() {
        return current;
    }

    public void setCurrent(BatchFilter current) {
        this.current = current;
    }

    public void onClick$packageAccountOpen() throws Exception {
        String value = accountType.getValue();
        Account account = service.getBalanceAccount(value, sessionAttributes.getSchema());
        if (value == null || value.isEmpty())
            throw new RuntimeException("Заполните тип открываемого счета");

        service.packageAccount(account);

        Messagebox.show("Успешно");
    }

    public void onClick$packageAccountConfirmation() throws Exception {
        service.packageAccountConfirmation();
        ;
        Messagebox.show("Успешно");
    }
}
