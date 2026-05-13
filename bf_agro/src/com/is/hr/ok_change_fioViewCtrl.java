package com.is.hr;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
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
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;

public class ok_change_fioViewCtrl extends GenericForwardComposer{
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
    private Textbox id,branch,personal_code,family,first_name,patronymic,change_date,change_motive,emp_code,ins_date,emp_code_name;
    private Textbox aid,abranch,apersonal_code,afamily,afirst_name,apatronymic,achange_date,achange_motive,aemp_code,ains_date,aemp_code_name;
    private Textbox fid,fbranch,fpersonal_code,ffamily,ffirst_name,fpatronymic,fchange_date,fchange_motive,femp_code,fins_date,femp_code_name;
    private Paging ok_change_fioPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private int ppid=0;
    
    public ok_change_fioFilter filter;

    PagingListModel_ok_change_fio model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private ok_change_fio current = new ok_change_fio();

    public ok_change_fioViewCtrl() {
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
    filter =new ok_change_fioFilter();
    parameter = (String[]) param.get("ppid");
    if (parameter!=null){
      ppid = Integer.parseInt(parameter[0]);
      filter.setPersonal_code(ppid);
    }
    
    filter.setPersonal_code(ppid);


        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ok_change_fio pok_change_fio = (ok_change_fio) data;

                row.setValue(pok_change_fio);
                
                //row.appendChild(new Listcell(pok_change_fio.getId()+""));
                row.appendChild(new Listcell(pok_change_fio.getBranch()));
                row.appendChild(new Listcell(pok_change_fio.getPersonal_code()+""));
                row.appendChild(new Listcell(pok_change_fio.getFamily()));
                row.appendChild(new Listcell(pok_change_fio.getFirst_name()));
                row.appendChild(new Listcell(pok_change_fio.getPatronymic()));
                row.appendChild(new Listcell(pok_change_fio.getChange_date()+""));
                row.appendChild(new Listcell(pok_change_fio.getChange_motive()));
                row.appendChild(new Listcell(pok_change_fio.getEmp_code()+""));
                row.appendChild(new Listcell(pok_change_fio.getIns_date()+""));
                row.appendChild(new Listcell(pok_change_fio.getEmp_code_name()));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$ok_change_fioPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
filter.setPersonal_code(ppid);
}


private void refreshModel(int activePage){
	filter.setPersonal_code(ppid);
    ok_change_fioPaging.setPageSize(_pageSize);
model = new PagingListModel_ok_change_fio(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

ok_change_fioPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(ok_change_fio) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public ok_change_fio getCurrent() {
return current;
}

public void setCurrent(ok_change_fio current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
			filter.setPersonal_code(ppid);
            
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


/*public void onClick$btn_save() {
try{
    if(addgrd.isVisible()){
            ok_change_fioService.create(new ok_change_fio(
            
            aid.getValue(),
            abranch.getValue(),
            apersonal_code.getValue(),
            afamily.getValue(),
            afirst_name.getValue(),
            apatronymic.getValue(),
            achange_date.getValue(),
            achange_motive.getValue(),
            aemp_code.getValue(),
            ains_date.getValue(),
            aemp_code_name.getValue(),
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ok_change_fioFilter();
        
          filter.setId(fid.getValue());
          filter.setBranch(fbranch.getValue());
          filter.setPersonal_code(fpersonal_code.getValue());
          filter.setFamily(ffamily.getValue());
          filter.setFirst_name(ffirst_name.getValue());
          filter.setPatronymic(fpatronymic.getValue());
          filter.setChange_date(fchange_date.getValue());
          filter.setChange_motive(fchange_motive.getValue());
          filter.setEmp_code(femp_code.getValue());
          filter.setIns_date(fins_date.getValue());
          filter.setEmp_code_name(femp_code_name.getValue());

    }else{
        
          current.setId(id.getValue());
          current.setBranch(branch.getValue());
          current.setPersonal_code(personal_code.getValue());
          current.setFamily(family.getValue());
          current.setFirst_name(first_name.getValue());
          current.setPatronymic(patronymic.getValue());
          current.setChange_date(change_date.getValue());
          current.setChange_motive(change_motive.getValue());
          current.setEmp_code(emp_code.getValue());
          current.setIns_date(ins_date.getValue());
          current.setEmp_code_name(emp_code_name.getValue());
        ok_change_fioService.update(current);
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}*/
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new ok_change_fioFilter();
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