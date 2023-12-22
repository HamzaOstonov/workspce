package com.is.openwayutils.accounting;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.openwayutils.utils.CheckNull;
import com.is.openwayutils.utils.RefCBox;

public class AccountingViewCtrl extends GenericForwardComposer{
    private Window chacc;
	private Div frm;
    private Listbox dataGrid;
//    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Hbox frmgrd;
    private Textbox branch,id,operation_id,sets_id,doc_type,acc_dt,acc_ct,acc_dt_name,acc_ct_name,purpose,purpose_code,cash_code,cash_sub_code,ord,mfo_ct,inn_ct;
    private Textbox abranch,aid,aoperation_id,asets_id,adoc_type,aacc_dt,aacc_ct,aacc_dt_name,aacc_ct_name,apurpose,apurpose_code,acash_code,acash_sub_code,aord,amfo_ct,ainn_ct;
    private Textbox fid,fsets_id,fdoc_type,facc_dt,facc_ct,facc_dt_name,facc_ct_name,fpurpose,fpurpose_code,fcash_code,fcash_sub_code,ford,fmfo_ct,finn_ct ;
    private Paging accountingPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private RefCBox fbranch,foperation_id;

    //public Accounting current;
    public AccountingFilter filter = new AccountingFilter();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private String alias;


private Accounting current = new Accounting();

    public AccountingViewCtrl() {
            super('$', false, false);
    }
/**
 *
 *
 */

public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // TODO Auto-generated method stub
            binder = new AnnotateDataBinder(comp);
            binder.bindBean("current", this.current);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    String[] group_id = (String[]) param.get("group_id");
    alias = (String) session.getAttribute("alias");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Accounting pAccounting = (Accounting) data;

                row.setValue(pAccounting);
                
                row.appendChild(new Listcell(pAccounting.getBranch()));
               // row.appendChild(new Listcell(pAccounting.getId()));
               // row.appendChild(new Listcell(pAccounting.getOperation_id()));
               // row.appendChild(new Listcell(pAccounting.getSets_id()));
               // row.appendChild(new Listcell(pAccounting.getDoc_type()));
                row.appendChild(new Listcell(pAccounting.getAcc_dt()));
                row.appendChild(new Listcell(pAccounting.getAcc_ct()));
               // row.appendChild(new Listcell(pAccounting.getAcc_dt_name()));
               // row.appendChild(new Listcell(pAccounting.getAcc_ct_name()));
                row.appendChild(new Listcell(pAccounting.getPurpose()));
               // row.appendChild(new Listcell(pAccounting.getPurpose_code()));
              // row.appendChild(new Listcell(pAccounting.getCash_code()));
              //  row.appendChild(new Listcell(pAccounting.getCash_sub_code()));
                row.appendChild(new Listcell("¹ "+pAccounting.getOrd()));
              //  row.appendChild(new Listcell(pAccounting.getMfo_ct()));
              //  row.appendChild(new Listcell(pAccounting.getInn_ct()));


    }});

    refreshModel(_startPageNumber);
    
    
    fbranch.setModel((new ListModelList(com.is.openwayutils.utils.RefDataService.getMfo(alias))));
    foperation_id.setModel((new ListModelList(com.is.openwayutils.utils.RefDataService.getOperation(Integer.parseInt(group_id[0]), alias))));


}

	public void onPaging$userPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}


	private void refreshModel(int activePage) {
		
		accountingPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		
		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter, alias);
			// _needsTotalSizeUpdate = false;
		}
		
		accountingPaging.setTotalSize(_totalSize);
		
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (Accounting) model.getElementAt(0);
			sendSelEvt();
		}
		/*
		*/
	}



//Omitted...
public Accounting getCurrent() {
return current;
}

public void setCurrent(Accounting current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
}




public void onClick$btn_back() {
    if (frm.isVisible()){
        frm.setVisible(false);
        grd.setVisible(true);
        btn_back.setImage("/images/file.png");
        btn_back.setLabel(Labels.getLabel("back"));
    }else onDoubleClick$dataGrid$grd();
}

public void onClick$btn_first() {
    dataGrid.setSelectedIndex(0);
    sendSelEvt();
}
public void onClick$btn_last() {
    dataGrid.setSelectedIndex(model.getSize()-1);
    sendSelEvt();
}
public void onClick$btn_prev() {
    if (dataGrid.getSelectedIndex()!=0){
    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
    sendSelEvt();
    }
}
public void onClick$btn_next() {
    if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
    sendSelEvt();
    }
}



private void sendSelEvt(){
    if (dataGrid.getSelectedIndex()==0){
            btn_first.setDisabled(true);
            btn_prev.setDisabled(true);
    }else{
            btn_first.setDisabled(false);
            btn_prev.setDisabled(false);
    }
    if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
            btn_next.setDisabled(true);
            btn_last.setDisabled(true);
    }else{
            btn_next.setDisabled(false);
            btn_last.setDisabled(false);
    }
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
}


public void onClick$btn_add() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}


public void onClick$btn_save() {
try{
    /*
	if(addgrd.isVisible()){
            AccountingService.create(new Accounting(
            
            abranch.getValue(),
            aid.getValue(),
            aoperation_id.getValue(),
            asets_id.getValue(),
            adoc_type.getValue(),
            aacc_dt.getValue(),
            aacc_ct.getValue(),
            aacc_dt_name.getValue(),
            aacc_ct_name.getValue(),
            apurpose.getValue(),
            apurpose_code.getValue(),
            acash_code.getValue(),
            acash_sub_code.getValue(),
            aord.getValue(),
            amfo_ct.getValue(),
            ainn_ct.getValue()
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new AccountingFilter();
        
          filter.setBranch(fbranch.getValue());
          filter.setId(fid.getValue());
          filter.setOperation_id(foperation_id.getValue());
          filter.setSets_id(fsets_id.getValue());
          filter.setDoc_type(fdoc_type.getValue());
          filter.setAcc_dt(facc_dt.getValue());
          filter.setAcc_ct(facc_ct.getValue());
          filter.setAcc_dt_name(facc_dt_name.getValue());
          filter.setAcc_ct_name(facc_ct_name.getValue());
          filter.setPurpose(fpurpose.getValue());
          filter.setPurpose_code(fpurpose_code.getValue());
          filter.setCash_code(fcash_code.getValue());
          filter.setCash_sub_code(fcash_sub_code.getValue());
          filter.setOrd(ford.getValue());
          filter.setMfo_ct(fmfo_ct.getValue());
          filter.setInn_ct(finn_ct.getValue());

    }else{
        
          current.setBranch(branch.getValue());
          current.setId(id.getValue());
          current.setOperation_id(operation_id.getValue());
          current.setSets_id(sets_id.getValue());
          current.setDoc_type(doc_type.getValue());
          current.setAcc_dt(acc_dt.getValue());
          current.setAcc_ct(acc_ct.getValue());
          current.setAcc_dt_name(acc_dt_name.getValue());
          current.setAcc_ct_name(acc_ct_name.getValue());
          current.setPurpose(purpose.getValue());
          current.setPurpose_code(purpose_code.getValue());
          current.setCash_code(cash_code.getValue());
          current.setCash_sub_code(cash_sub_code.getValue());
          current.setOrd(ord.getValue());
          current.setMfo_ct(mfo_ct.getValue());
          current.setInn_ct(inn_ct.getValue());
        AccountingService.updateAccounting(current);
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
*/
} catch (Exception e) {
    e.printStackTrace();
}

}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new AccountingFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

public void onCtrlKey$acc_dt(Event event){
	//int keyCode = ((KeyEvent) event).getKeyCode();
	chacc.setVisible(true);
}

public void onSelect$foperation_id(Event evt) {
	filter.setOperation_id(Long.parseLong(foperation_id.getValue()));
	//onClick$btn_back();
	refreshModel(_startPageNumber);
}

}

