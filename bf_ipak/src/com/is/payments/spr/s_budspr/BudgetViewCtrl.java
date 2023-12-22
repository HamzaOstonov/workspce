package com.is.payments.spr.s_budspr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.payments.spr.CookieUtil;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

@SuppressWarnings("serial")
public class BudgetViewCtrl extends GenericForwardComposer{
	private Window budgetwnd;
	private  int _pageSizebudget = 5;
	private int _startPageNumberbudget = 0;
	private int _totalSizebudget = 0;
	private int _oldSelectedIndexbudget = 0;
	private Toolbarbutton budgetwnd$btn_last;
   	private Toolbarbutton budgetwnd$btn_next;
   	private Toolbarbutton budgetwnd$btn_prev;
   	private Toolbarbutton budgetwnd$btn_first;
   	private Textbox budgetwnd$faccount, budgetwnd$finn, budgetwnd$fname;
   	private Paging budgetwnd$s_budsprPaging;
   	private RefCBox budgetwnd$pagesize;
	private Listbox budgetwnd$dataGrid;
	private Grid budgetwnd$fgrd;
	public S_budspr bcurrent = new S_budspr();
	public S_budsprFilter bfilter = new S_budsprFilter();
	com.is.payments.spr.s_budspr.PagingListModel bmodel = null;
	private boolean _needsTotalSizeUpdate = true;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private List<RefData> pagesizes = new ArrayList<RefData>();
	
	public BudgetViewCtrl() {
		super('$', false, false);
	}
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("bcurrent", this.bcurrent);
    	binder.bindBean("bfilter", this.bfilter);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        if (parameter!=null){
        	//_pageSize = Integer.parseInt( parameter[0])/36;
        	//dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }
        
        init();

        budgetwnd$dataGrid.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
            	S_budspr pS_budspr = (S_budspr) data;
            	row.setValue(pS_budspr);
            	//row.appendChild(new Listcell(pS_budspr.getNci_id()));
            	//row.appendChild(new Listcell(pS_budspr.getTreasure_id()));
            	row.appendChild(new Listcell(pS_budspr.getAccount()));
            	row.appendChild(new Listcell(pS_budspr.getInn()));
            	row.appendChild(new Listcell(pS_budspr.getName()));
            	//row.appendChild(new Listcell(df.format(pS_budspr.getDate_open())));
            	//row.appendChild(new Listcell(df.format(pS_budspr.getDate_close())));
            	//row.appendChild(new Listcell(pS_budspr.getAct()));
            	//row.appendChild(new Listcell(pS_budspr.getBank_id()));
            	//row.appendChild(new Listcell(pS_budspr.getBankacc()));
            	//row.appendChild(new Listcell(pS_budspr.getBankinn()));
            	//row.appendChild(new Listcell(pS_budspr.getBankaccname()));
            }});
       
        refreshModelBudget(_startPageNumberbudget);
        budgetwnd$dataGrid.focus();
    }
    
    private void init() {
    	 pagesizes = S_budsprService.getPageSizes();
         budgetwnd$pagesize.setModel(new ListModelList(pagesizes));
         if (CheckNull.isEmpty(CookieUtil.getCookie("pagesizebudget"))) {
         	CookieUtil.setCookie("pagesizebudget", ""+_pageSizebudget);
         } else {
         	_pageSizebudget = Integer.parseInt(CookieUtil.getCookie("pagesizebudget"));
         }
         budgetwnd$pagesize.setSelecteditem(""+_pageSizebudget);
         budgetwnd$pagesize.setValue(""+_pageSizebudget);
         budgetwnd$dataGrid.setRows(_pageSizebudget+1);
    }

    public void onPaging$s_budsprPaging$budgetwnd(PagingEvent event){
	    _startPageNumberbudget = event.getActivePage();
	    refreshModelBudget(_startPageNumberbudget);
	}

	private void refreshModelBudget(int activePage){
		budgetwnd$s_budsprPaging.setPageSize(_pageSizebudget);
        bmodel = new com.is.payments.spr.s_budspr.PagingListModel(activePage, _pageSizebudget, bfilter);
	    if(_needsTotalSizeUpdate) {
	    	_totalSizebudget = bmodel.getTotalSize(bfilter);
	    	//_needsTotalSizeUpdate = false;
	    }
	    budgetwnd$s_budsprPaging.setTotalSize(_totalSizebudget);
	    budgetwnd$dataGrid.setModel((ListModel) bmodel);
	    sortbudget();
	    if (bmodel.getSize()>0){
	    	budgetwnd$dataGrid.setSelectedIndex(_oldSelectedIndexbudget);
	    	sendSelEvtBudget(true);
	    	this.bcurrent =(S_budspr) bmodel.getElementAt(_oldSelectedIndexbudget);
		    sendSelEvtBudget(true);
	    }
	    budgetwnd$dataGrid.focus();
	}
	
	public void onClick$btn_send$budgetwnd() {
		onDoubleClick$dataGrid$grd$budgetwnd();
	}
	public void onOK$dataGrid$grd$budgetwnd() {
		onDoubleClick$dataGrid$grd$budgetwnd();
	}
		
	public void onDoubleClick$dataGrid$grd$budgetwnd() {
		budgetwnd.setVisible(false);
		if (bcurrent != null && !CheckNull.isEmpty(bcurrent.getAccount())) {
			//System.out.println("sendEvent");
			Events.sendEvent("onAddBudget", self, bcurrent);
			/*
			Panel p = (Panel)Executions.getCurrent().getPar
			if (p == null) System.out.println("p = null!");
			DocumentViewCtrl d = (DocumentViewCtrl)p.getAttribute("documentmain$composer");
			if (d == null) System.out.println("d = null!");
			d.onDoubleClick$dataGrid$grd$budgetwnd(bcurrent);
			*/
			/*
			if (frmdiv.isVisible()) {
				bank_cotext.setValue(bcurrent.getBank_id());
				bank_co.setSelecteditem(bcurrent.getBank_id());
				current.setBank_co(bcurrent.getBank_id());
				acc_co.setValue(bcurrent.getBankacc());
				current.setAcc_co(bcurrent.getBankacc());
				inn_co.setValue(bcurrent.getBankinn());
				current.setInn_co(bcurrent.getBankinn());
				name_co.setValue(bcurrent.getBankaccname());
				current.setName_co(bcurrent.getBankaccname());
				budget_inn.setValue(bcurrent.getInn());
				current.setBudget_inn(bcurrent.getInn());
				budget_acctext.setValue(bcurrent.getAccount());
				current.setBudget_account(bcurrent.getInn());
				onChange$acc_co();
			} else if (adddiv.isVisible()) {
				abank_cotext.setValue(bcurrent.getBank_id());
				abank_co.setSelecteditem(bcurrent.getBank_id());
				currentadd.setBank_co(bcurrent.getBank_id());
				aacc_co.setValue(bcurrent.getBankacc());
				currentadd.setAcc_co(bcurrent.getBankacc());
				ainn_co.setValue(bcurrent.getBankinn());
				currentadd.setInn_co(bcurrent.getBankinn());
				aname_co.setValue(bcurrent.getBankaccname());
				currentadd.setName_co(bcurrent.getBankaccname());
				abudget_inn.setValue(bcurrent.getInn());
				currentadd.setBudget_inn(bcurrent.getInn());
				abudget_acctext.setValue(bcurrent.getAccount());
				currentadd.setBudget_account(bcurrent.getAccount());
				onChange$aacc_co();
			}
			*/
		}
	}
		
	public void onClick$btn_refresh$budgetwnd() {
		refreshModelBudget(_startPageNumberbudget);
	}
	
	public void onClick$btn_find$budgetwnd() {
		bfilter = new S_budsprFilter();
	    if (!CheckNull.isEmpty(budgetwnd$faccount.getValue())) {
			bfilter.setAccount(budgetwnd$faccount.getValue());
		}
	    if (!CheckNull.isEmpty(budgetwnd$finn.getValue())) {
			bfilter.setInn(budgetwnd$finn.getValue());
		}
	    if (!CheckNull.isEmpty(budgetwnd$fname.getValue())) {
			bfilter.setInn(budgetwnd$fname.getValue());
		}
	    _startPageNumberbudget = 0;
		_oldSelectedIndexbudget = 0;
		budgetwnd$s_budsprPaging.setActivePage(0);
		refreshModelBudget(_startPageNumberbudget);
	}
	
	public void onClick$btn_find_clear$budgetwnd() {
		//currclientfilter = new ClientFilter();
	    CheckNull.clearForm(budgetwnd$fgrd);
	}
	
	public void onClick$btn_find_cancel$budgetwnd() {
		bfilter = new S_budsprFilter();
		//tfilter.setBranch(current.getBranch());
		//tfilter.setClient(current.getClient_id());
		CheckNull.clearForm(budgetwnd$fgrd);
		//aliasacc = ss_dblink_branches.get(0).getLabel();
		_startPageNumberbudget = 0;
		_oldSelectedIndexbudget = 0;
		budgetwnd$s_budsprPaging.setActivePage(0);
		refreshModelBudget(_startPageNumberbudget);
	}
	
	public void onClick$btn_first$budgetwnd() {
		budgetwnd$dataGrid.setSelectedIndex(0);
		sendSelEvtBudget(true);
	}
	
	public void onClick$btn_last$budgetwnd() {
		budgetwnd$dataGrid.setSelectedIndex(bmodel.getSize()-1);
		sendSelEvtBudget(true);
	}
	
	public void onClick$btn_prev$budgetwnd() {
        if (budgetwnd$dataGrid.getSelectedIndex()!=0){
        	budgetwnd$dataGrid.setSelectedIndex(budgetwnd$dataGrid.getSelectedIndex()-1);
        	sendSelEvtBudget(true);
        }
	}
	
	public void onClick$btn_next$budgetwnd() {
        if (budgetwnd$dataGrid.getSelectedIndex()!=(bmodel.getSize()-1)){
        	budgetwnd$dataGrid.setSelectedIndex(budgetwnd$dataGrid.getSelectedIndex()+1);
        	sendSelEvtBudget(true);
        }
	}

	private void sendSelEvtBudget(Boolean sendEvent){
        if (budgetwnd$dataGrid.getSelectedIndex()==0){
        	budgetwnd$btn_first.setDisabled(true);
        	budgetwnd$btn_prev.setDisabled(true);
        }else{
        	budgetwnd$btn_first.setDisabled(false);
        	budgetwnd$btn_prev.setDisabled(false);
        }
        if(budgetwnd$dataGrid.getSelectedIndex()==(bmodel.getSize()-1)){
        	budgetwnd$btn_next.setDisabled(true);
        	budgetwnd$btn_last.setDisabled(true);
        }else{
        	budgetwnd$btn_next.setDisabled(false);
        	budgetwnd$btn_last.setDisabled(false);
        }
        _oldSelectedIndexbudget = budgetwnd$dataGrid.getSelectedIndex();
        if (sendEvent) {
	        SelectEvent evt = new SelectEvent("onSelect", budgetwnd$dataGrid,budgetwnd$dataGrid.getSelectedItems());
	        Events.sendEvent(evt);
        }
	}
	
	public void onSelect$pagesize$budgetwnd() {
		_pageSizebudget = Integer.parseInt(budgetwnd$pagesize.getValue());
		CookieUtil.setCookie("pagesizebudget", ""+_pageSizebudget);
		budgetwnd$dataGrid.setRows(_pageSizebudget+1);
        _oldSelectedIndexbudget = 0;
        _startPageNumberbudget = 0;
        budgetwnd$s_budsprPaging.setActivePage(_startPageNumberbudget);
        refreshModelBudget(_startPageNumberbudget);
	}
	
	public void sortbudget() {
		for (int i = 0; i < budgetwnd$dataGrid.getListhead().getChildren().size(); i++) {
			Listheader listheader = (Listheader)budgetwnd$dataGrid.getListhead().getChildren().get(i);
			if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
				listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
				return;
			}
			
		}
	}

}
