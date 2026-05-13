package com.is.tieto_visa.tieto;

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

import com.fasterxml.jackson.databind.ObjectMapper;


public class AccntViewCtrl extends GenericForwardComposer {
    private Listbox accountsList;
    public ListAccountsItem getCurrent() {
		return current;
	}

	public void setCurrent(ListAccountsItem current) {
		this.current = current;
	}

	public ListCustomersItem getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(ListCustomersItem currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public TclientViewCtrl getMain() {
		return main;
	}

	public void setMain(TclientViewCtrl main) {
		this.main = main;
	}

	private ListAccountsItem current;
    private ListCustomersItem currentCustomer;
    private TclientViewCtrl main;

    private AccntService service;

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
            service = new AccntService();
        }
        ArrayList<ListAccountsItem> accounts;
        ResponseInfoHolder responseInfoHolder = new ResponseInfoHolder();


        String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentCustomer);
		} catch (Exception e22) {
			str1 = " str1=error currentCustomer";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err currentCustomer  **************" + str1);
		
        accounts = service.listAccounts(currentCustomer, responseInfoHolder);

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(accounts);
		} catch (Exception e22) {
			str1 = " str1=error accounts";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err accounts  **************" + str1);

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
