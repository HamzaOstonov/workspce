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

public class ok_armyViewCtrl extends GenericForwardComposer{
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
    private Textbox id,branch,personal_code,army_code,fitness_army_code,staff,type_force,army_group,category_army,special_army,military_rank,number_vus,code_army_post,name_army_office,specreg_number,emp_code,ins_date,army_group_code,category_army_code,military_rank_code,staff_code,type_force_code,emp_code_name;
    private Textbox aid,abranch,apersonal_code,aarmy_code,afitness_army_code,astaff,atype_force,aarmy_group,acategory_army,aspecial_army,amilitary_rank,anumber_vus,acode_army_post,aname_army_office,aspecreg_number,aemp_code,ains_date,aarmy_group_code,acategory_army_code,amilitary_rank_code,astaff_code,atype_force_code,aemp_code_name;
    private Textbox fid,fbranch,fpersonal_code,farmy_code,ffitness_army_code,fstaff,ftype_force,farmy_group,fcategory_army,fspecial_army,fmilitary_rank,fnumber_vus,fcode_army_post,fname_army_office,fspecreg_number,femp_code,fins_date,farmy_group_code,fcategory_army_code,fmilitary_rank_code,fstaff_code,ftype_force_code,femp_code_name;
    private Paging ok_armyPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private int ppid=0;
    
    public ok_armyFilter filter= new ok_armyFilter();

    PagingListModel_ok_army model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private ok_army current = new ok_army();

    public ok_armyViewCtrl() {
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
    filter = new ok_armyFilter();
    parameter = (String[]) param.get("ppid");
    if (parameter!=null){
      ppid = Integer.parseInt(parameter[0]);
      filter.setPersonal_code(ppid);
    }
    
    filter.setPersonal_code(ppid);
    
    



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ok_army pok_army = (ok_army) data;

                row.setValue(pok_army);
                
                //row.appendChild(new Listcell(pok_army.getId()+""));
                row.appendChild(new Listcell(pok_army.getBranch()));
                row.appendChild(new Listcell(pok_army.getPersonal_code()+""));
                row.appendChild(new Listcell(pok_army.getArmy_code()+""));
                row.appendChild(new Listcell(pok_army.getFitness_army_code()+""));
                row.appendChild(new Listcell(pok_army.getStaff()));
                row.appendChild(new Listcell(pok_army.getType_force()));
                row.appendChild(new Listcell(pok_army.getArmy_group()));
                row.appendChild(new Listcell(pok_army.getCategory_army()));
                row.appendChild(new Listcell(pok_army.getSpecial_army()));
                row.appendChild(new Listcell(pok_army.getMilitary_rank()));
                row.appendChild(new Listcell(pok_army.getNumber_vus()));
                row.appendChild(new Listcell(pok_army.getCode_army_post()));
                row.appendChild(new Listcell(pok_army.getName_army_office()));
                row.appendChild(new Listcell(pok_army.getSpecreg_number()));
                row.appendChild(new Listcell(pok_army.getEmp_code()+""));
                row.appendChild(new Listcell(pok_army.getIns_date()+""));
                row.appendChild(new Listcell(pok_army.getArmy_group_code()+""));
                row.appendChild(new Listcell(pok_army.getCategory_army_code()+""));
                row.appendChild(new Listcell(pok_army.getMilitary_rank_code()+""));
                row.appendChild(new Listcell(pok_army.getStaff_code()+""));
                row.appendChild(new Listcell(pok_army.getType_force_code()+""));
                row.appendChild(new Listcell(pok_army.getEmp_code_name()));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$ok_armyPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
filter.setPersonal_code(ppid);
}


private void refreshModel(int activePage){
	filter.setPersonal_code(ppid);
    ok_armyPaging.setPageSize(_pageSize);
model = new PagingListModel_ok_army(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

ok_armyPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(ok_army) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public ok_army getCurrent() {
return current;
}

public void setCurrent(ok_army current) {
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
            ok_armyService.create(new ok_army(
            
            aid.getValue(),
            abranch.getValue(),
            apersonal_code.getValue(),
            aarmy_code.getValue(),
            afitness_army_code.getValue(),
            astaff.getValue(),
            atype_force.getValue(),
            aarmy_group.getValue(),
            acategory_army.getValue(),
            aspecial_army.getValue(),
            amilitary_rank.getValue(),
            anumber_vus.getValue(),
            acode_army_post.getValue(),
            aname_army_office.getValue(),
            aspecreg_number.getValue(),
            aemp_code.getValue(),
            ains_date.getValue(),
            aarmy_group_code.getValue(),
            acategory_army_code.getValue(),
            amilitary_rank_code.getValue(),
            astaff_code.getValue(),
            atype_force_code.getValue(),
            aemp_code_name.getValue()
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ok_armyFilter();
        
          filter.setId(fid.getValue());
          filter.setBranch(fbranch.getValue());
          filter.setPersonal_code(fpersonal_code.getValue());
          filter.setArmy_code(farmy_code.getValue());
          filter.setFitness_army_code(ffitness_army_code.getValue());
          filter.setStaff(fstaff.getValue());
          filter.setType_force(ftype_force.getValue());
          filter.setArmy_group(farmy_group.getValue());
          filter.setCategory_army(fcategory_army.getValue());
          filter.setSpecial_army(fspecial_army.getValue());
          filter.setMilitary_rank(fmilitary_rank.getValue());
          filter.setNumber_vus(fnumber_vus.getValue());
          filter.setCode_army_post(fcode_army_post.getValue());
          filter.setName_army_office(fname_army_office.getValue());
          filter.setSpecreg_number(fspecreg_number.getValue());
          filter.setEmp_code(femp_code.getValue());
          filter.setIns_date(fins_date.getValue());
          filter.setArmy_group_code(farmy_group_code.getValue());
          filter.setCategory_army_code(fcategory_army_code.getValue());
          filter.setMilitary_rank_code(fmilitary_rank_code.getValue());
          filter.setStaff_code(fstaff_code.getValue());
          filter.setType_force_code(ftype_force_code.getValue());
          filter.setEmp_code_name(femp_code_name.getValue());

    }else{
        
          current.setId(id.getValue());
          current.setBranch(branch.getValue());
          current.setPersonal_code(personal_code.getValue());
          current.setArmy_code(army_code.getValue());
          current.setFitness_army_code(fitness_army_code.getValue());
          current.setStaff(staff.getValue());
          current.setType_force(type_force.getValue());
          current.setArmy_group(army_group.getValue());
          current.setCategory_army(category_army.getValue());
          current.setSpecial_army(special_army.getValue());
          current.setMilitary_rank(military_rank.getValue());
          current.setNumber_vus(number_vus.getValue());
          current.setCode_army_post(code_army_post.getValue());
          current.setName_army_office(name_army_office.getValue());
          current.setSpecreg_number(specreg_number.getValue());
          current.setEmp_code(emp_code.getValue());
          current.setIns_date(ins_date.getValue());
          current.setArmy_group_code(army_group_code.getValue());
          current.setCategory_army_code(category_army_code.getValue());
          current.setMilitary_rank_code(military_rank_code.getValue());
          current.setStaff_code(staff_code.getValue());
          current.setType_force_code(type_force_code.getValue());
          current.setEmp_code_name(emp_code_name.getValue());
        ok_armyService.update(current);
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
            filter = new ok_armyFilter();
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
