package com.is.globalTieto;

import com.is.globalTieto.customer.CustomerViewCtrl;
import com.is.globalTieto.tietoModels.CardInfo;
import com.is.globalTieto.tietoModels.ListAccountsItem;
import com.is.globalTieto.tietoModels.ListCustomersFilter;
import com.is.globalTieto.tietoModels.ListCustomersItem;
import com.is.utils.CheckNull;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Window;

public class TietoMainViewCtrl extends GenericForwardComposer {
    private Tabbox resultBox;
    private Tabpanel customersTab;
    @Getter
    private Tabpanel accountsTab;
    @Getter
    private Tabpanel cardsTab;
    private Grid searchGrid;
    private Window customerWindow;
    @Getter
    private String branch = null;

    @Getter
    @Setter
    private ListCustomersFilter filter = new ListCustomersFilter();
    @Getter
    @Setter
    private ListCustomersItem currentCustomer = new ListCustomersItem();
    @Getter
    @Setter
    private ListAccountsItem currentAccount = new ListAccountsItem();
    @Getter
    @Setter
    private CardInfo currentCard = new CardInfo();

    public TietoMainViewCtrl() {
        super('$', false, false);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        branch = (String) session.getAttribute("branch");

        AnnotateDataBinder binder = new AnnotateDataBinder(comp);
        binder.bindBean("filter", this.filter);
        binder.loadAll();
    }

    public void onClick$btnSearch() {
        if(customerWindow == null) {
            customerWindow = (Window) Executions.createComponents(Constants.CUSTOMERS_ZUL, customersTab, null);
        }
        CustomerViewCtrl customerVC = (CustomerViewCtrl) customerWindow.getAttribute("customermain$composer");
        customerVC.setFilter(filter);
        customerVC.setMain(this);
        customerVC.search();

        resultBox.setSelectedIndex(0);
    }

    public void onClick$btnCancel() {
        CheckNull.clearForm(searchGrid);
    }

    public void setTab(int i){
        resultBox.setSelectedIndex(i);
    }
}