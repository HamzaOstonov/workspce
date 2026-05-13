package com.is.bpri;


import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
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
import org.zkoss.zul.Vbox;
import org.zkoss.zul.event.PagingEvent;

import com.is.crm.ho.Customer;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;


public class BproductViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Paging contactPaging;
    private Div grd;
    private Listbox dataGrid;
    private Vbox addgrd;
    private Grid addgrdl,frmgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox id,branch,customer,prodid,vdate,currency,amount,emp_id,state;
    private Textbox aid,abranch,acustomer,aprodid,avdate,acurrency,aamount,aemp_id,astate ;
    private Textbox fid,fbranch,fcustomer,fprodid,fvdate,fcurrency,famount,femp_id,fstate ;
    private Paging bproductPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private EventQueue eq;


    public BproductFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Bproduct current = new Bproduct();

    public BproductViewCtrl() {
            super('$', false, false);
    }
/**
 *
 *
 */
@Override
public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        // TODO Auto-generated method stub
            binder = new AnnotateDataBinder(comp);
            binder.bindBean("current", this.current);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }
    
    
    eq = EventQueues.lookup("CRM_CLIENTS", EventQueues.DESKTOP, true);
    eq.subscribe(new EventListener() {
        public void onEvent(Event event) throws Exception {
            com.is.crm.ho.Customer value = (Customer) event.getData();
            //lbl.setValue(value);
            acustomer.setValue( value.getId_client());
            alert("Выбран "+value!=null ? value.getName():"" );
        }
    });



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Bproduct pBproduct = (Bproduct) data;

                row.setValue(pBproduct);
                
                row.appendChild(new Listcell(pBproduct.getId()+""));
                row.appendChild(new Listcell(pBproduct.getBranch()));
                row.appendChild(new Listcell(pBproduct.getCustomer()+""));
                row.appendChild(new Listcell(pBproduct.getProdid()+""));
                row.appendChild(new Listcell(pBproduct.getVdate()+""));
                row.appendChild(new Listcell(pBproduct.getCurrency()));
                row.appendChild(new Listcell(pBproduct.getAmount()+""));
                row.appendChild(new Listcell(pBproduct.getEmp_id()+""));
                row.appendChild(new Listcell(pBproduct.getState()+""));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$bproductPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    bproductPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, alias);


        _totalSize = model.getTotalSize(filter, alias);


bproductPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
dataGrid.setSelectedIndex(0);  
sendSelEvt();
}
}



//Omitted...
public Bproduct getCurrent() {
return current;
}

public void setCurrent(Bproduct current) {
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
            BproductService.createBproduct(new Bproduct(
            
            aid.getValue(),
            abranch.getValue(),
            acustomer.getValue(),
            aprodid.getValue(),
            avdate.getValue(),
            acurrency.getValue(),
            aamount.getValue(),
            aemp_id.getValue(),
            astate.getValue(),
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new BproductFilter();
        
          filter.setId(fid.getValue());
          filter.setBranch(fbranch.getValue());
          filter.setCustomer(fcustomer.getValue());
          filter.setProdid(fprodid.getValue());
          filter.setVdate(fvdate.getValue());
          filter.setCurrency(fcurrency.getValue());
          filter.setAmount(famount.getValue());
          filter.setEmp_id(femp_id.getValue());
          filter.setState(fstate.getValue());

    }else{
        
          current.setId(id.getValue());
          current.setBranch(branch.getValue());
          current.setCustomer(customer.getValue());
          current.setProdid(prodid.getValue());
          current.setVdate(vdate.getValue());
          current.setCurrency(currency.getValue());
          current.setAmount(amount.getValue());
          current.setEmp_id(emp_id.getValue());
          current.setState(state.getValue());
        BproductService.updateBproduct(current);
    }
    */
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new BproductFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
//CheckNull.clearForm(addgrdl);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}


}
