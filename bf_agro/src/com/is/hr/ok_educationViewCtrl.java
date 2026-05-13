package com.is.hr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
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

public class ok_educationViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,fgrd;
    private Hbox frmgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbarbutton btn_save;
    private Combobox code;
    private Toolbar tb;
    private Intbox id,personal_code,education_code,basis_code,institution_code,begin_date,end_date,qualification_code,emp_code,ins_date,nostra_date,diplom_date,begin_date_mm,begin_date_dd,end_date_mm,end_date_dd,education_end,education_count_code,vid_education_code;
    private Textbox branch,diplom_num,profession_personal,cod_vuz_prim,curs,fakultet,nostra,nostra_series,nostra_number,education_city,emp_code_name;
    private Intbox aid,apersonal_code,aeducation_code,abasis_code,ainstitution_code,abegin_date,aend_date,aqualification_code,aemp_code,abegin_date_mm,abegin_date_dd,aend_date_mm,aend_date_dd,aeducation_end,aeducation_count_code,avid_education_code;
    private Textbox abranch,adiplom_num,aprofession_personal,acod_vuz_prim,acurs,afakultet,anostra,anostra_series,anostra_number,aeducation_city,aemp_code_name;
    private Intbox fid,fpersonal_code,feducation_code,fbasis_code,finstitution_code,fbegin_date,fend_date,fqualification_code,femp_code,fbegin_date_mm,fbegin_date_dd,fend_date_mm,fend_date_dd,feducation_end,feducation_count_code,fvid_education_code;
    private Textbox fbranch,fdiplom_num,fprofession_personal,fcod_vuz_prim,fcurs,ffakultet,fnostra,fnostra_series,fnostra_number,feducation_city,femp_code_name;
    
    private Paging ok_educationPaging;
    private Datebox ains_date,fins_date;
    private Datebox anostra_date,fnostra_date;
    private Datebox adiplom_date,fdiplom_date;
    
    
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private int ppid=0;
    
    public ok_educationFilter filter;
    private ok_education current2 = new ok_education();
    
    
    PagingListModel_ok_education model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    
private ok_education current = new ok_education();

    public ok_educationViewCtrl() {
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
    filter = new ok_educationFilter();
    parameter = (String[]) param.get("ppid");
    if (parameter!=null){
      ppid = Integer.parseInt(parameter[0]);
      filter.setPersonal_code(ppid);
    }
    
    filter.setPersonal_code(ppid);


        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ok_education pok_education = (ok_education) data;

                row.setValue(pok_education);
                
                //row.appendChild(new Listcell(pok_education.getId()+""));
                row.appendChild(new Listcell(pok_education.getBranch()));
                row.appendChild(new Listcell(pok_education.getPersonal_code()+""));
                row.appendChild(new Listcell(pok_education.getEducation_code()+""));
                row.appendChild(new Listcell(pok_education.getBasis_code()+""));
                row.appendChild(new Listcell(pok_education.getInstitution_code()+""));
                row.appendChild(new Listcell(pok_education.getBegin_date()+""));
                row.appendChild(new Listcell(pok_education.getEnd_date()+""));
                row.appendChild(new Listcell(pok_education.getProfession_personal()+""));
                row.appendChild(new Listcell(pok_education.getQualification_code()+""));
                row.appendChild(new Listcell(pok_education.getDiplom_num()));
                row.appendChild(new Listcell(pok_education.getEmp_code()+""));
                row.appendChild(new Listcell(pok_education.getIns_date()+""));
                row.appendChild(new Listcell(pok_education.getCod_vuz_prim()));
                row.appendChild(new Listcell(pok_education.getCurs()));
                row.appendChild(new Listcell(pok_education.getFakultet()));
                row.appendChild(new Listcell(pok_education.getNostra()));
                row.appendChild(new Listcell(pok_education.getNostra_series()));
                row.appendChild(new Listcell(pok_education.getNostra_number()));
                row.appendChild(new Listcell(pok_education.getNostra_date()+""));
                row.appendChild(new Listcell(pok_education.getDiplom_date()+""));
                row.appendChild(new Listcell(pok_education.getBegin_date_mm()+""));
                row.appendChild(new Listcell(pok_education.getBegin_date_dd()+""));
                row.appendChild(new Listcell(pok_education.getEnd_date_mm()+""));
                row.appendChild(new Listcell(pok_education.getEnd_date_dd()+""));
                row.appendChild(new Listcell(pok_education.getEducation_end()+""));
                row.appendChild(new Listcell(pok_education.getEducation_city()+""));
                row.appendChild(new Listcell(pok_education.getEducation_count_code()+""));
                row.appendChild(new Listcell(pok_education.getVid_education_code()+""));
                row.appendChild(new Listcell(pok_education.getEmp_code_name()));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$ok_educationPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
	filter.setPersonal_code(ppid);
	
    ok_educationPaging.setPageSize(_pageSize);
model = new PagingListModel_ok_education(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

ok_educationPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(ok_education) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public ok_education getCurrent() {
return current;
}

public void setCurrent(ok_education current) {
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
	btn_add.setLabel("��������");
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


public void onClick$btn_save() throws Exception {
try{
    if(addgrd.isVisible()){
    		int a = code.getSelectedIndex() + 1;
    		Date fdate = null;
    		Date ndate = null;
    		Date ddate = null;
    		try {
                if(!ains_date.getValue().equals("")) {
                    System.out.println("date: " + ains_date.getValue());
                    String date_str = sdf.format(ains_date.getValue());
                    fdate = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS").parse(date_str);
                    System.out.println("TESSSSSSSSSSSSST"+fdate.toString());
                    System.out.println("date2: " + date_str);
                    System.out.println(fdate);
                    
                    
                } else
                    throw new Exception("������ ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
    		
    		try {
                if(!anostra_date.getValue().equals("")) {
                    String date_str = sdf.format(anostra_date.getValue());
                    ndate = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS").parse(date_str);              
                } else
                    throw new Exception("������ ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
    		
    		
    		try {
                if(!adiplom_date.getValue().equals("")) {
                    String date_str = sdf.format(adiplom_date.getValue());
                    ddate = new SimpleDateFormat("dd.MM.yyyy HH:MM:SS").parse(date_str);                   
                    
                } else
                    throw new Exception("������ ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
    		
    		
    		
            ok_educationService.create(new ok_education(
            
            aid.getValue(),
            abranch.getValue(),
            apersonal_code.getValue(),
            aeducation_code.getValue(),
            abasis_code.getValue(),
            ainstitution_code.getValue(),
            abegin_date.getValue(),
            aend_date.getValue(),
            aprofession_personal.getValue(),
            aqualification_code.getValue(),
            adiplom_num.getValue(),
            aemp_code.getValue(),
            fdate,
            acod_vuz_prim.getValue(),
            acurs.getValue(),
            afakultet.getValue(),
            anostra.getValue(),
            anostra_series.getValue(),
            anostra_number.getValue(),
            ndate,
            ddate,
            abegin_date_mm.getValue(),
            abegin_date_dd.getValue(),
            aend_date_mm.getValue(),
            aend_date_dd.getValue(),
            aeducation_end.getValue(),
            aeducation_city.getValue(),
            aeducation_count_code.getValue(),
            avid_education_code.getValue(),
            aemp_code_name.getValue()
            ));
            CheckNull.clearForm(addgrd);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ok_educationFilter();
        
        if (fid == null) { 
            filter.setId(fid.getValue());
          }

        if (fbranch == null) { 
        	filter.setBranch(fbranch.getValue());
          }
          
          if(fpersonal_code == null) {
        	  filter.setPersonal_code(fpersonal_code.getValue());
          }
          if(fpersonal_code == null) {
        	  filter.setPersonal_code(fpersonal_code.getValue());
          }
          if(feducation_code == null) {
        	  filter.setEducation_code(feducation_code.getValue());
          }
          if(fbasis_code == null) {
        	  filter.setBasis_code(fbasis_code.getValue());
          }
          
          filter.setInstitution_code(finstitution_code.getValue());
          filter.setBegin_date(fbegin_date.getValue());
          filter.setEnd_date(end_date.getValue());
          filter.setProfession_personal(fprofession_personal.getValue());
          filter.setQualification_code(fqualification_code.getValue());
          filter.setDiplom_num(fdiplom_num.getValue());
          if(femp_code == null) {
        	  filter.setEmp_code(femp_code.getValue());
          }
          
          try {
              if (!fins_date.getValue().equals("")) {
                  String date_str = df.format(fins_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setIns_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          
          filter.setCod_vuz_prim(fcod_vuz_prim.getValue());
          filter.setCurs(fcurs.getValue());
          filter.setFakultet(ffakultet.getValue());
          filter.setNostra(fnostra.getValue());
          filter.setNostra_series(fnostra_series.getValue());
          filter.setNostra_number(fnostra_number.getValue());
          
          try {
              if (!fnostra_date.getValue().equals("")) {
                  String date_str = df.format(fnostra_date.getValue());
                  Date ndate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setNostra_date(ndate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          try {
              if (!fdiplom_date.getValue().equals("")) {
                  String date_str = df.format(fdiplom_date.getValue());
                  Date ddate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setDiplom_date(ddate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
         
          filter.setBegin_date_mm(fbegin_date_mm.getValue());
          filter.setBegin_date_dd(fbegin_date_dd.getValue());
          filter.setEnd_date_mm(fend_date_mm.getValue());
          filter.setEnd_date_dd(fend_date_dd.getValue());
          filter.setEducation_end(feducation_end.getValue());
          filter.setEducation_city(feducation_city.getValue());
          filter.setEducation_count_code(feducation_count_code.getValue());
          filter.setVid_education_code(fvid_education_code.getValue());
          filter.setEmp_code_name(femp_code_name.getValue());

    }else{
        
          current.setId(id.getValue());
          current.setBranch(branch.getValue());
          current.setPersonal_code(personal_code.getValue());
          current.setEducation_code(education_code.getValue());
          current.setBasis_code(basis_code.getValue());
          current.setInstitution_code(institution_code.getValue());
          current.setBegin_date(begin_date.getValue());
          current.setEnd_date(end_date.getValue());
          current.setProfession_personal(profession_personal.getValue());
          current.setQualification_code(qualification_code.getValue());
          current.setDiplom_num(diplom_num.getValue());
          current.setEmp_code(emp_code.getValue());
          try {
              if (!ins_date.getValue().equals("")) {
                  String date_str = df.format(ins_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setIns_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setCod_vuz_prim(cod_vuz_prim.getValue());
          current.setCurs(curs.getValue());
          current.setFakultet(fakultet.getValue());
          current.setNostra(nostra.getValue());
          current.setNostra_series(nostra_series.getValue());
          current.setNostra_number(nostra_number.getValue());
          try {
              if (!nostra_date.getValue().equals("")) {
                  String date_str = df.format(nostra_date.getValue());
                  Date ndate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setNostra_date(ndate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          try {
              if (!diplom_date.getValue().equals("")) {
                  String date_str = df.format(diplom_date.getValue());
                  Date ddate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setDiplom_date(ddate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
         
          current.setBegin_date_mm(begin_date_mm.getValue());
          current.setBegin_date_dd(begin_date_dd.getValue());
          current.setEnd_date_mm(end_date_mm.getValue());
          current.setEnd_date_dd(end_date_dd.getValue());
          current.setEducation_end(education_end.getValue());
          current.setEducation_city(education_city.getValue());
          current.setEducation_count_code(education_count_code.getValue());
          current.setVid_education_code(vid_education_code.getValue());
          current.setEmp_code_name(emp_code_name.getValue());
        ok_educationService.update(current);
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
            filter = new ok_educationFilter();
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
