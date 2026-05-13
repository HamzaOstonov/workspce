package com.is.globalTieto.customer;

import com.is.globalTieto.Constants;
import com.is.globalTieto.TietoMainViewCtrl;
import com.is.globalTieto.account.AccountViewCtrl;
import com.is.globalTieto.tietoModels.ListCustomersFilter;
import com.is.globalTieto.tietoModels.ListCustomersItem;
import com.is.globalTieto.tietoModels.ResponseInfoHolder;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.*;

import java.util.ArrayList;

public class CustomerViewCtrl extends GenericForwardComposer {
    @Getter
    @Setter
    private ListCustomersFilter filter;
    @Getter
    @Setter
    private ListCustomersItem current;
    @Getter
    @Setter
    private TietoMainViewCtrl main;

    private Listbox customersList;
    private Window accountWindow;

    private CustomerService service;

    private ListitemRenderer customersRenderer = new ListitemRenderer() {
        @Override
        public void render(Listitem row, Object data) {
            ListCustomersItem item = (ListCustomersItem) data;
            row.setValue(item);

            row.appendChild(new Listcell((item.getClient_b() != null) ? item.getClient_b() : ""));
            row.appendChild(new Listcell((item.getClient() != null) ? item.getClient() : ""));
            row.appendChild(new Listcell((item.getF_names() != null) ? item.getF_names() : ""));
            row.appendChild(new Listcell((item.getSurname() != null) ? item.getSurname() : ""));
            row.appendChild(new Listcell((item.getB_date() != null) ? item.getB_date().toString() : ""));
        }

    };

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        AnnotateDataBinder binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
    }

    public void search() {
        if (service == null) {
            service = new CustomerService();
        }
        ArrayList<ListCustomersItem> customers;
        ResponseInfoHolder responseInfoHolder = new ResponseInfoHolder();
        customers = service.listCustomers(filter, responseInfoHolder);

        refreshModel(customers);
    }

    private void refreshModel(ArrayList<ListCustomersItem> customers) {
        customersList.setItemRenderer(customersRenderer);
        customersList.setModel(new BindingListModelList(customers, true));
    }

    public void onDoubleClick$customersList() {
        if (accountWindow == null) {
            accountWindow = (Window) Executions.createComponents(Constants.ACCOUNTS_ZUL, main.getAccountsTab(), null);
        }
        AccountViewCtrl accountVC = (AccountViewCtrl) accountWindow.getAttribute("accountmain$composer");
        accountVC.setCurrentCustomer(current);
        accountVC.setMain(main);
        accountVC.search();
        main.setTab(1);
    }
}
