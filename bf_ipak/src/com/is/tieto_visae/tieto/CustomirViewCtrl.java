package com.is.tieto_visae.tieto;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Window;


public class CustomirViewCtrl extends GenericForwardComposer {
    public ListCustomersFilter getFilter() {
		return filter;
	}

	public void setFilter(ListCustomersFilter filter) {
		this.filter = filter;
	}

	public ListCustomersItem getCurrent() {
		return current;
	}

	public void setCurrent(ListCustomersItem current) {
		this.current = current;
	}

	public TclientViewCtrl getMain() {
		return main;
	}

	public void setMain(TclientViewCtrl main) {
		this.main = main;
	}

	private ListCustomersFilter filter;
    private ListCustomersItem current;
    private TclientViewCtrl main;

    private Listbox customersList;
    private Window accountWindow;

    private CustomirService service;

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
            service = new CustomirService();
        }
        ArrayList<ListCustomersItem> customers;
        ResponseInfoHolder responseInfoHolder = new ResponseInfoHolder();
        customers = service.listCustomers(filter, responseInfoHolder);

        refreshModel(customers);
    }

    private void refreshModel(ArrayList<ListCustomersItem> customers) {
        if (customers!=null)
        {
    	customersList.setItemRenderer(customersRenderer);
        customersList.setModel(new BindingListModelList(customers, true));
        }
        else
        {
        	alert("Данные не найдены.");
        }
    }

    public void onDoubleClick$customersList() {
        if (accountWindow == null) {
            accountWindow = (Window) Executions.createComponents(Constants.ACCOUNTS_ZUL, main.getAccountsTab(), null);
        }
        AccntViewCtrl accountVC = (AccntViewCtrl) accountWindow.getAttribute("accountmain$composer");
        accountVC.setCurrentCustomer(current);
        accountVC.setMain(main);
        accountVC.search();
        main.setTab(1);
    }
}
