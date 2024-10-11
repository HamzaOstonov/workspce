package com.is.globalTieto.card;

import com.is.globalTieto.TietoMainViewCtrl;
import com.is.globalTieto.tietoModels.*;
import com.is.globalTieto.utils.ZkUtils;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.*;

import java.util.ArrayList;

public class CardViewCtrl extends GenericForwardComposer {
    private Listbox cardsList;
    @Getter
    @Setter
    private CardInfo current;
    @Getter
    @Setter
    private ListAccountsItem currentAccount;
    @Getter
    @Setter
    private ListCustomersItem currentCustomer;
    @Getter
    @Setter
    private TietoMainViewCtrl main;
    private CardService service;
    private Textbox refillWindow$refillSum;
    private Combobox refillWindow$payMethod;
    private Window refillWindow;

    private ListitemRenderer cardsRenderer = new ListitemRenderer() {
        @Override
        public void render(Listitem row, Object data) {
            CardInfo item = (CardInfo) data;
            row.setValue(item);

            row.appendChild(new Listcell((item.getMainInfo().getCard() != null) ? item.getMainInfo().getCard() : ""));
            row.appendChild(new Listcell((item.getMainInfo().getExpiry() != null) ? item.getMainInfo().getExpiry().toString() : ""));
            row.appendChild(new Listcell((item.getMainInfo().getStatus() != null) ? item.getMainInfo().getStatus() : ""));
            row.appendChild(new Listcell((item.getBalance().getEnd_bal() != null) ? item.getBalance().getEnd_bal().toString() : ""));
        }
    };

    private void refreshCards(ArrayList<CardInfo> cards) {
        if (cards == null) {
            cards = new ArrayList<CardInfo>();
        }
        cardsList.setItemRenderer(cardsRenderer);
        cardsList.setModel(new BindingListModelList(cards, true));
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        AnnotateDataBinder binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
    }

    public  void search(){
        if(service == null){
            service = new CardService();
        }
        ArrayList<CardInfo> cards;
        ResponseInfoHolder responseInfoHolder = new ResponseInfoHolder();

        cards = service.listCards(currentAccount, responseInfoHolder);
        refreshCards(cards);
    }

    public void onDoubleClick$cardsList() {
        refillWindow.setVisible(true);
    }

    public void onClick$btnRefill$refillWindow() {
        int isCashPayment = 0;
        if(ZkUtils.validateForm(refillWindow)){
            if(refillWindow$payMethod.getSelectedIndex() == 0){
                isCashPayment = 1;
            }else{
                isCashPayment = 0;
            }
            service.addTransaction(currentCustomer, currentAccount, current, Long.valueOf(refillWindow$refillSum.getValue()), main.getBranch(), isCashPayment);
//            refillWindow$refillSum.setValue("");
            refillWindow.setVisible(false);
        }
    }

    public void onClick$btnRefillCancel$refillWindow() {
        refillWindow.setVisible(false);
        refillWindow$refillSum.setValue("");
    }
    
}
