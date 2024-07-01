package com.is.comcustomer;


import java.sql.Date;
import java.text.SimpleDateFormat;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

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

public class ComCustomerViewCtrl extends GenericForwardComposer{
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
    private Textbox id,account,name,inn,agreement_number,purpose_template,budget_inn,budget_account,is_budget_org,purpose_code,category_id;
    private Textbox aid,aaccount,aname,ainn,aagreement_number,apurpose_template,abudget_inn,abudget_account,ais_budget_org,apurpose_code,acategory_id ;
    private Textbox fid,fbranch,faccount,fname,finn,fregion,fdistr,fagreement_number,fpurpose_template,fbudget_inn,fbudget_account,fis_budget_org,fpurpose_code,fcategory_id ;
    private Datebox agreement_date,aagreement_date,fagreement_date;
    private Paging comcustomerPaging;
    private RefCBox branch, abranch, region, aregion, distr, adistr;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;

    
    public ComCustomerFilter filter = new ComCustomerFilter();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private ComCustomer current = new ComCustomer();

    public ComCustomerViewCtrl() {
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
    
    alias = (String) session.getAttribute("alias");



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ComCustomer pComCustomer = (ComCustomer) data;

                row.setValue(pComCustomer);
                
                row.appendChild(new Listcell(pComCustomer.getId()+""));
                row.appendChild(new Listcell(pComCustomer.getBranch()));
                row.appendChild(new Listcell(pComCustomer.getAccount()));
                row.appendChild(new Listcell(pComCustomer.getName()));
                /*
                row.appendChild(new Listcell(pComCustomer.getInn()));
                row.appendChild(new Listcell(pComCustomer.getRegion()));
                row.appendChild(new Listcell(pComCustomer.getDistr()));
                row.appendChild(new Listcell(pComCustomer.getAgreement_number()));
                row.appendChild(new Listcell(pComCustomer.getAgreement_date()!=null ? df.format(pComCustomer.getAgreement_date()):"-----"));
                row.appendChild(new Listcell(pComCustomer.getPurpose_template()));
                row.appendChild(new Listcell(pComCustomer.getBudget_inn()));
                row.appendChild(new Listcell(pComCustomer.getBudget_account()));
                row.appendChild(new Listcell(pComCustomer.getIs_budget_org()+""));
                row.appendChild(new Listcell(pComCustomer.getPurpose_code()));
                row.appendChild(new Listcell(pComCustomer.getCategory_id()+""));
*/

    }});
        
        branch.setModel((new ListModelList(com.is.utils.RefDataService.getMfo(alias))));
        abranch.setModel((new ListModelList(com.is.utils.RefDataService.getMfo(alias))));
        region.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
        aregion.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
        distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
        adistr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));

    refreshModel(_startPageNumber);

}

public void onPaging$comcustomerPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    comcustomerPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, alias);


        _totalSize = model.getTotalSize(filter, alias);


comcustomerPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
	dataGrid.setSelectedIndex(0);
sendSelEvt();
}
}



//Omitted...
public ComCustomer getCurrent() {
return current;
}

public void setCurrent(ComCustomer current) {
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
            ComCustomerService.create(new ComCustomer(
            0,//aid.getValue(),
            abranch.getValue(),
            aaccount.getValue(),
            aname.getValue(),
            ainn.getValue(),
            aregion.getValue(),
            adistr.getValue(),
            aagreement_number.getValue(),
            aagreement_date.getValue(),
            apurpose_template.getValue(),
            abudget_inn.getValue(),
            abudget_account.getValue(),
            (ais_budget_org.getValue()=="")?0:Integer.parseInt(ais_budget_org.getValue()),
            apurpose_code.getValue(),
            (acategory_id.getValue()=="")?0:Integer.parseInt(acategory_id.getValue()),"",""
            ),alias);
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ComCustomerFilter();
        
          filter.setId(!fid.getValue().equals("") ? Integer.parseInt(fid.getValue()): 0);
          filter.setBranch(fbranch.getValue());
          filter.setAccount(faccount.getValue());
          filter.setName(fname.getValue());
          filter.setInn(finn.getValue());
          filter.setRegion(fregion.getValue());
          filter.setDistr(fdistr.getValue());
          filter.setAgreement_number(fagreement_number.getValue());
          filter.setAgreement_date(fagreement_date.getValue());
          filter.setPurpose_template(fpurpose_template.getValue());
          filter.setBudget_inn(fbudget_inn.getValue());
          filter.setBudget_account(fbudget_account.getValue());
          filter.setIs_budget_org(!fis_budget_org.getValue().equals("") ? Integer.parseInt(fis_budget_org.getValue()):0);
          filter.setPurpose_code(fpurpose_code.getValue());
          filter.setCategory_id(!fcategory_id.getValue().equals("") ? Integer.parseInt(fcategory_id.getValue()): 0);

    }else{
        
          //current.setId(id.getValue());
          current.setBranch(branch.getValue());
          current.setAccount(account.getValue());
          current.setName(name.getValue());
          current.setInn(inn.getValue());
          current.setRegion(region.getValue());
          current.setDistr(distr.getValue());
          current.setAgreement_number(agreement_number.getValue());
          current.setAgreement_date(agreement_date.getValue());
          current.setPurpose_template(purpose_template.getValue());
          current.setBudget_inn(budget_inn.getValue());
          current.setBudget_account(budget_account.getValue());
          current.setIs_budget_org(Integer.parseInt( is_budget_org.getValue()));
          current.setPurpose_code(purpose_code.getValue());
          current.setCategory_id(Integer.parseInt(category_id.getValue()));
        ComCustomerService.update(current,alias);
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
            filter = new ComCustomerFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

public void onSelect$region()
{
	distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(region.getValue(),alias))));
}

public void onSelect$aregion()
{
	adistr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(aregion.getValue(),alias))));
}

}
