package com.is.comserviceslist;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.is.utils.CheckNull;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
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
import com.is.utils.RefCBox;
import org.zkoss.zul.Label;
import com.is.comcustomer.ComCustomerService;
import com.is.comcustomer.ComCustomer;


public class ComServicesListViewCtrl  extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox state,name,dealid,parentid,parentgroupid;
    
    private static HashMap<Integer, ComCustomer> customers = new HashMap<Integer,ComCustomer>();
    
    private Label id;
    private Textbox astate,aname,adealid,aparentid,aparentgroupid;
    private Textbox fid,fcustomerj_id,fstate,fname,fp_name_mask,fp_number_mask,ffrom_date_mask,fto_date_mask,ffrom_value_mask,fto_value_mask,fdifference_mask,fpenalty_amount_mask,foperation_id,fdealid,fparentid,fparentgroupid,fclient_address_mask,fpay_cat_id ;
    private Paging comserviceslistPaging;
    private  int _pageSize = 15;
    private RefCBox p_name_mask, p_number_mask, from_date_mask, to_date_mask;
    private RefCBox from_value_mask, to_value_mask, difference_mask, customerj_id;
    private RefCBox penalty_amount_mask, operation_id, client_address_mask, pay_cat_id;
    
    private RefCBox ap_name_mask, ap_number_mask, afrom_date_mask, ato_date_mask;
    private RefCBox afrom_value_mask, ato_value_mask, adifference_mask, acustomerj_id,adistrict_mask;
    private RefCBox apenalty_amount_mask, aoperation_id, aclient_address_mask, apay_cat_id,district_mask;
    
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private ListModelList FieldsStates;
    
    public ComServicesListFilter filter = new ComServicesListFilter();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private ComServicesList current = new ComServicesList();

    public ComServicesListViewCtrl() {
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
    String[] group_id = (String[]) param.get("group_id");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }
    alias = (String) session.getAttribute("alias");
    
    customers = ComCustomerService.getHCustomer(alias);
    
    
    FieldsStates = new ListModelList(com.is.utils.RefDataService.getFieldState(alias));

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ComServicesList pComServicesList = (ComServicesList) data;

                row.setValue(pComServicesList);
                
                row.appendChild(new Listcell(pComServicesList.getId()+""));
                row.appendChild(new Listcell(customers.get(pComServicesList.getCustomerj_id()).getName()));
                
                row.appendChild(new Listcell(pComServicesList.getState()+""));
                row.appendChild(new Listcell(pComServicesList.getName()));
                /*
                row.appendChild(new Listcell(pComServicesList.getP_name_mask()));
                row.appendChild(new Listcell(pComServicesList.getP_number_mask()));
                row.appendChild(new Listcell(pComServicesList.getFrom_date_mask()));
                row.appendChild(new Listcell(pComServicesList.getTo_date_mask()));
                row.appendChild(new Listcell(pComServicesList.getFrom_value_mask()));
                row.appendChild(new Listcell(pComServicesList.getTo_value_mask()));
                row.appendChild(new Listcell(pComServicesList.getDifference_mask()));
                row.appendChild(new Listcell(pComServicesList.getPenalty_amount_mask()));
                row.appendChild(new Listcell(pComServicesList.getOperation_id()));
                row.appendChild(new Listcell(pComServicesList.getDealId()));
                row.appendChild(new Listcell(pComServicesList.getParentId()));
                row.appendChild(new Listcell(pComServicesList.getParentGroupId()));
                row.appendChild(new Listcell(pComServicesList.getClient_address_mask()));
                row.appendChild(new Listcell(pComServicesList.getPay_cat_id()));
*/

    }});
        
    p_name_mask.setModel(FieldsStates);
    p_number_mask.setModel(FieldsStates);
    from_date_mask.setModel(FieldsStates);
    to_date_mask.setModel(FieldsStates);
    from_value_mask.setModel(FieldsStates);
    to_value_mask.setModel(FieldsStates);
    difference_mask.setModel(FieldsStates);
    penalty_amount_mask.setModel(FieldsStates);
    district_mask.setModel(FieldsStates);
    operation_id.setModel(new ListModelList(com.is.utils.RefDataService.getOperation(Integer.parseInt(group_id[0]), alias)));
    client_address_mask.setModel(FieldsStates);
    pay_cat_id.setModel(new ListModelList(com.is.utils.RefDataService.getCategory(alias)));
    customerj_id.setModel(new ListModelList(com.is.utils.RefDataService.getCustomer(alias)));
    
    ap_name_mask.setModel(FieldsStates);
    ap_number_mask.setModel(FieldsStates);
    afrom_date_mask.setModel(FieldsStates);
    ato_date_mask.setModel(FieldsStates);
    afrom_value_mask.setModel(FieldsStates);
    ato_value_mask.setModel(FieldsStates);
    adifference_mask.setModel(FieldsStates);
    apenalty_amount_mask.setModel(FieldsStates);
    adistrict_mask.setModel(FieldsStates);
    aoperation_id.setModel(new ListModelList(com.is.utils.RefDataService.getOperation(Integer.parseInt(group_id[0]), alias)));
    aclient_address_mask.setModel(FieldsStates);
    apay_cat_id.setModel(new ListModelList(com.is.utils.RefDataService.getCategory(alias)));
    acustomerj_id.setModel(new ListModelList(com.is.utils.RefDataService.getCustomer(alias)));
    
    ap_name_mask.setSelecteditem("1");
    
    refreshModel(_startPageNumber);

}

public void onPaging$comserviceslistPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    comserviceslistPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, alias);


        _totalSize = model.getTotalSize(filter, alias);

comserviceslistPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
	dataGrid.setSelectedIndex(0);
sendSelEvt();
}
}



//Omitted...
public ComServicesList getCurrent() {
return current;
}

public void setCurrent(ComServicesList current) {
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
    if(addgrd.isVisible()){
            ComServicesListService.create(new ComServicesList(
            0,//aid.getValue(),
            Integer.parseInt(acustomerj_id.getValue()),
            Integer.parseInt(astate.getValue()),
            aname.getValue(),
            Integer.parseInt(ap_name_mask.getValue()),
            Integer.parseInt(ap_number_mask.getValue()),
            Integer.parseInt(afrom_date_mask.getValue()),
            Integer.parseInt(ato_date_mask.getValue()),
            Integer.parseInt(afrom_value_mask.getValue()),
            Integer.parseInt(ato_value_mask.getValue()),
            Integer.parseInt(adifference_mask.getValue()),
            0,//Integer.parseInt(apenalty_amount_mask.getValue()),
            Integer.parseInt(aoperation_id.getValue()),
            0,//Integer.parseInt(adealid.getValue()),
            0,//Integer.parseInt( aparentid.getValue()),
            0,//Integer.parseInt(aparentgroupid.getValue()),
            Integer.parseInt(aclient_address_mask.getValue()),
            Integer.parseInt(apay_cat_id.getValue()),
            Integer.parseInt(adistrict_mask.getValue())
            ),alias);
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ComServicesListFilter();
        
          filter.setId(!fid.getValue().equals("") ? Integer.parseInt(fid.getValue()):0);
          filter.setCustomerj_id(!fcustomerj_id.getValue().equals("") ? Integer.parseInt( fcustomerj_id.getValue()):0);
          filter.setState(!fstate.getValue().equals("") ? Integer.parseInt(fstate.getValue()):0);
          filter.setName( fname.getValue());
          filter.setP_name_mask(!fp_name_mask.getValue().equals("") ? Integer.parseInt(fp_name_mask.getValue()):0);
          filter.setP_number_mask(!fp_number_mask.getValue().equals("") ? Integer.parseInt( fp_number_mask.getValue()):0);
          filter.setFrom_date_mask(!ffrom_date_mask.getValue().equals("") ? Integer.parseInt( ffrom_date_mask.getValue()):0);
          filter.setTo_date_mask(!fto_date_mask.getValue().equals("") ? Integer.parseInt( fto_date_mask.getValue()):0);
          filter.setFrom_value_mask(!ffrom_value_mask.getValue().equals("") ? Integer.parseInt( ffrom_value_mask.getValue()):0);
          filter.setTo_value_mask(!fto_value_mask.getValue().equals("") ? Integer.parseInt( fto_value_mask.getValue()):0);
          filter.setDifference_mask(!fdifference_mask.getValue().equals("") ? Integer.parseInt( fdifference_mask.getValue()):0);
          //filter.setPenalty_amount_mask(!fpenalty_amount_mask.getValue().equals("") ? Integer.parseInt( fpenalty_amount_mask.getValue()):0);
          filter.setOperation_id(!foperation_id.getValue().equals("") ? Integer.parseInt( foperation_id.getValue()):0);
          //filter.setDeal_id(!fdealid.getValue().equals("") ? Integer.parseInt( fdealid.getValue()):0);
         // filter.setParent_id(!fparentid.getValue().equals("") ? Integer.parseInt( fparentid.getValue()):0);
          //filter.setParent_group_id(!fparentgroupid.getValue().equals("") ? Integer.parseInt( fparentgroupid.getValue()):0);
          filter.setClient_address_mask(!fclient_address_mask.getValue().equals("") ? Integer.parseInt( fclient_address_mask.getValue()):0);
          filter.setPay_cat_id(!fpay_cat_id.getValue().equals("") ? Integer.parseInt( fpay_cat_id.getValue()):0);

    }else{
        
          //current.setId(id.getValue());
          current.setCustomerj_id(Integer.parseInt(customerj_id.getValue()));
          current.setState(Integer.parseInt(state.getValue()));
          current.setName(name.getValue());
          current.setP_name_mask(Integer.parseInt(p_name_mask.getValue()));
          current.setP_number_mask(Integer.parseInt(p_number_mask.getValue()));
          current.setFrom_date_mask(Integer.parseInt(from_date_mask.getValue()));
          current.setTo_date_mask(Integer.parseInt(to_date_mask.getValue()));
          current.setFrom_value_mask(Integer.parseInt(from_value_mask.getValue()));
          current.setTo_value_mask(Integer.parseInt(to_value_mask.getValue()));
          current.setDifference_mask(Integer.parseInt(difference_mask.getValue()));
          //current.setPenalty_amount_mask(Integer.parseInt(penalty_amount_mask.getValue()));
          current.setOperation_id(Integer.parseInt(operation_id.getValue()));
          //current.setDealId(dealid.getValue());
          //current.setParentId(parentid.getValue());
          //current.setParentGroupId(parentgroupid.getValue());
          current.setClient_address_mask(Integer.parseInt(client_address_mask.getValue()));
          current.setPay_cat_id(Integer.parseInt(pay_cat_id.getValue()));
          current.setDistrict_mask(Integer.parseInt(district_mask.getValue()));
        ComServicesListService.update(current,alias);
    }
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
            filter = new ComServicesListFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}
}
