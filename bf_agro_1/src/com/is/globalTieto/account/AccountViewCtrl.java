package com.is.globalTieto.account;

import com.is.globalTieto.Constants;
import com.is.globalTieto.TietoMainViewCtrl;
import com.is.globalTieto.card.CardViewCtrl;
import com.is.globalTieto.tietoModels.ListAccountsItem;
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

public class AccountViewCtrl extends GenericForwardComposer {
    private Listbox accountsList;
    @Getter
    @Setter
    private ListAccountsItem current;
    @Getter
    @Setter
    private ListCustomersItem currentCustomer;
    @Getter
    @Setter
    private TietoMainViewCtrl main;

    private AccountService service;

    private Window cardWindow;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        AnnotateDataBinder binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
    }

    public void search(){
        if(service == null){
            service = new AccountService();
        }
        ArrayList<ListAccountsItem> accounts;
        ResponseInfoHolder responseInfoHolder = new ResponseInfoHolder();

        accounts = service.listAccounts(currentCustomer, responseInfoHolder);
        refreshModel(accounts);
    }

    private void refreshModel(ArrayList<ListAccountsItem> accounts) {
        if (accounts == null) {
            accounts = new ArrayList<ListAccountsItem>();
        }
        accountsList.setItemRenderer(accountsRenderer);
        accountsList.setModel(new BindingListModelList(accounts, true));
    }

    private ListitemRenderer accountsRenderer = new ListitemRenderer() {
        @Override
        public void render(Listitem row, Object data) {
            ListAccountsItem item = (ListAccountsItem) data;
            row.setValue(item);

            row.appendChild(new Listcell((item.getAccount_no() != null) ? item.getAccount_no().toString() : ""));
            row.appendChild(new Listcell((item.getCard_acct() != null) ? item.getCard_acct() : ""));
            row.appendChild(new Listcell((item.getCcy() != null) ? item.getCcy() : ""));
        }
    };

    public void onDoubleClick$accountsList() {
        if (cardWindow == null) {
            cardWindow = (Window) Executions.createComponents(Constants.CARDS_ZUL, main.getCardsTab(), null);
        }
        CardViewCtrl cardVC = (CardViewCtrl) cardWindow.getAttribute("cardmain$composer");
        cardVC.setCurrentCustomer(currentCustomer);
        cardVC.setCurrentAccount(current);
        cardVC.setMain(main);
        cardVC.search();
        main.setTab(2);
    }
}
