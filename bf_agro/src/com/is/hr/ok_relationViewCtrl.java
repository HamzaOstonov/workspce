package com.is.hr;

// import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.Date;


import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;


import com.is.utils.CheckNull;
import com.is.utils.RefCBox;



public class ok_relationViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd,fgrddiv,addgrddiv;
    private Hbox frmgrd;
    private Grid addgrd,frmgrd1,frmgrd2,frmgrd3,frmgrd4,frmgrd5,frmgrd6,frmgrd7,frmgrd8,frmgrd9,frmgrd10,frmgrd11,frmgrd12,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_save;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Toolbar cl_tabs;
    private Include ok_relation;
    private Include ok_period;
    private Include ok_education;
    private Include ok_academic;
    private Tabbox Details_TabBox;
    private int state = -1;
    private Intbox personal_code;
    private String[] parameter1=null;
    private Intbox id,relation_birthday,emp_code, relation_deathday,dd,mm,dd_death,mm_death;
    private Intbox aid,apersonal_code,aemp_code,ins_date,arelation_birthday,add,amm;
    private Intbox fid,fpersonal_code,frelation_code,frelation_birthday,femp_code,frelation_deathday,fdd,fmm,fdd_death,fmm_death;
    private Datebox ains_date,fins_date;
    private Textbox branch,relation_family,relation_name,relation_patronymic,relation_birthplace,relation_office,relation_post,relation_home_address,cod_str_live,cod_obl_live,cod_obl_live_prim,cod_city,cod_city_prim,cod_str_live_prim,cod_str_birth,cod_str_birth_prim,cod_obl_birth,cod_obl_birth_prim,cod_city_birth,cod_city_birth_prim,arelation_deathday,add_death,amm_death,emp_code_name;
    private Textbox abranch,arelation_family,arelation_name,arelation_patronymic,arelation_birthplace,arelation_office,arelation_post,arelation_home_address,acod_obl_live_prim,acod_city_prim,acod_str_live_prim,acod_str_birth_prim,acod_obl_birth_prim,acod_city_birth_prim,aemp_code_name ;
    private Textbox fbranch,frelation_family,frelation_name,frelation_patronymic,frelation_birthplace,frelation_office,frelation_post,frelation_home_address,fcod_str_live,fcod_obl_live,fcod_obl_live_prim,fcod_city,fcod_city_prim,fcod_str_live_prim,fcod_str_birth,fcod_str_birth_prim,fcod_obl_birth,fcod_obl_birth_prim,fcod_city_birth,fcod_city_birth_prim,femp_code_name ;
    private RefCBox relation_code,arelation_code,acod_str_live,acod_city,acod_obl_live;
    private RefCBox acod_str_birth,acod_city_birth,acod_obl_birth;
    private String alias;
    private Paging ok_relationPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    //private int pid;
    private int ppid=0;
    
    

    public ok_relationFilter filter = new ok_relationFilter();
    
    private ok_relation current2 = new ok_relation();
    

    PagingListModel_ok_relation model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    SimpleDateFormat tdf = new SimpleDateFormat("dd.MM.yyyy");


private ok_relation current = new ok_relation();

    public ok_relationViewCtrl() {
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
            binder.bindBean("filter", this.filter);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }
    filter = new ok_relationFilter();
//    parameter1 = (String[]) param.get("pid");
//    //System.out.println("parameter"+ parameter1[0]);
//    if (parameter1!=null){
//      filter.setPersonal_code(Integer.valueOf(parameter1[0]));
//    }
    parameter = (String[]) param.get("ppid");
    if (parameter!=null){
      ppid = Integer.parseInt(parameter[0]);
      filter.setPersonal_code(ppid);
    }
    
    filter.setPersonal_code(ppid);
    
    alias = (String) session.getAttribute("alias");


        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ok_relation pok_relation = (ok_relation) data;

                row.setValue(pok_relation);
                
                //row.appendChild(new Listcell(pok_relation.getId()+""));
                row.appendChild(new Listcell(pok_relation.getBranch()));
                row.appendChild(new Listcell(pok_relation.getPersonal_code()+""));
                row.appendChild(new Listcell(pok_relation.getRelation_code()+""));
                row.appendChild(new Listcell(pok_relation.getRelation_family()));
                row.appendChild(new Listcell(pok_relation.getRelation_name()));
                row.appendChild(new Listcell(pok_relation.getRelation_patronymic()));
                row.appendChild(new Listcell(pok_relation.getRelation_birthday()+""));
                row.appendChild(new Listcell(pok_relation.getRelation_birthplace()));
                row.appendChild(new Listcell(pok_relation.getRelation_office()));
                row.appendChild(new Listcell(pok_relation.getRelation_post()));
                row.appendChild(new Listcell(pok_relation.getRelation_home_address()));
                row.appendChild(new Listcell(pok_relation.getEmp_code()+""));
                row.appendChild(new Listcell(pok_relation.getIns_date()+""));
                row.appendChild(new Listcell(pok_relation.getRelation_deathday()+""));
                row.appendChild(new Listcell(pok_relation.getCod_str_live()));
                row.appendChild(new Listcell(pok_relation.getCod_obl_live()));
                row.appendChild(new Listcell(pok_relation.getCod_obl_live_prim()));
                row.appendChild(new Listcell(pok_relation.getCod_city()));
                row.appendChild(new Listcell(pok_relation.getCod_city_prim()));
                row.appendChild(new Listcell(pok_relation.getCod_str_live_prim()));
                row.appendChild(new Listcell(pok_relation.getCod_str_birth()));
                row.appendChild(new Listcell(pok_relation.getCod_str_birth_prim()));
                row.appendChild(new Listcell(pok_relation.getCod_obl_birth()));
                row.appendChild(new Listcell(pok_relation.getCod_obl_birth_prim()));
                row.appendChild(new Listcell(pok_relation.getCod_city_birth()));
                row.appendChild(new Listcell(pok_relation.getCod_city_birth_prim()));
                row.appendChild(new Listcell(pok_relation.getDd()+""));
                row.appendChild(new Listcell(pok_relation.getMm()+""));
                row.appendChild(new Listcell(pok_relation.getDd_death()+""));
                row.appendChild(new Listcell(pok_relation.getMm_death()+""));
                row.appendChild(new Listcell(pok_relation.getEmp_code_name()));


    }});

        
        
        RefCBoxModels();
		refreshModel(_startPageNumber);
		if(state==1||state==2){
			btn_save.setVisible(false);
			btn_add.setVisible(false);
		} else if(state==0){
			btn_add.setVisible(true);
			btn_save.setVisible(true);
		}
}

private void RefCBoxModels() {
	arelation_code.setModel(new ListModelList(ok_relationService.getSS_ok_relation(alias)));
	relation_code.setModel(new ListModelList(ok_relationService.getSS_ok_relation(alias)));
	acod_str_live.setModel(new ListModelList(ok_relationService.getS_str(alias)));
	acod_str_birth.setModel(new ListModelList(ok_relationService.getS_str(alias)));
	acod_city.setModel(new ListModelList(ok_relationService.getS_distr(alias)));
	acod_city_birth.setModel(new ListModelList(ok_relationService.getS_distr(alias)));
	acod_obl_live.setModel(new ListModelList(ok_relationService.getS_region(alias)));
	acod_obl_birth.setModel(new ListModelList(ok_relationService.getS_region(alias)));
}

public void onPaging$ok_relationPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
filter.setPersonal_code(ppid);
}


private void refreshModel(int activePage){
	
	filter.setPersonal_code(ppid);//Id
	
    ok_relationPaging.setPageSize(_pageSize);
model = new PagingListModel_ok_relation(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

ok_relationPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(ok_relation) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public ok_relation getCurrent() {
return current;
}

public void setCurrent(ok_relation current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
	      	
			
			filter.setPersonal_code(ppid);//Id
	      	grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
            
}

//public void onSelect$Details_TabBox() {
//	if (current == null) {
//		return;
//	}
//	ok_relation.setSrc("ok_relation.zul");
//    ok_education.setSrc("ok_education.zul");
//    ok_period.setSrc("ok_period.zul");
//    ok_academic.setSrc("ok_academic.zul");
//    
//    switch (Details_TabBox.getSelectedIndex()) {
//	case 0:
//		
//		break;
//	case 1:
//		
//		ok_period.setSrc("ok_period.zul?pid=" + current.getPersonal_code());
//		break;
//	case 2:
//		ok_education.setSrc("ok_education.zul?pid=" + current.getPersonal_code());
//		break;
//	case 3:
//		ok_academic.setSrc("ok_academic.zul?pid=" + current.getPersonal_code());
//		break;
//	case 4:
//		ok_relation.setSrc("ok_relation.zul?pid=" + current.getPersonal_code());
//	break;
//		}
//    System.out.println("�������� ������"+current.getPersonal_code());
//    System.out.println("ok_period.zul?pid=" + current.getPersonal_code());
//}


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
	btn_save.setLabel("��������");
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
//    	int a = arelation_code.getSelectedIndex() + 1;    	
    	Date fdate = null;
        try {
            if(!ains_date.getValue().equals("")) {
                System.out.println("date: " + ains_date.getValue());
                String date_str = df.format(ains_date.getValue());
                fdate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(date_str);
                System.out.println("TESSSSSSSSSSSSST"+fdate.toString());
                System.out.println("date2: " + date_str);
                System.out.println(fdate);
                
                
            } else
                throw new Exception("������ ����");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        int deathday = 0;
        
        if(arelation_deathday.getValue().equals("")) {
        	deathday = 0;
        }
        
        int day_death = 0;
        if(add_death.getValue().equals("")) {
        	day_death = 0;
        }
        
        int mouth_death = 0;
        if(amm_death.getValue().equals("")) {
        	mouth_death = 0;
        }
        
        
        	ok_relationService.create(new ok_relation(
            aid.getValue(),
            abranch.getValue(),
            apersonal_code.getValue(),
            Integer.parseInt(arelation_code.getValue()),
            arelation_family.getValue(),
            arelation_name.getValue(),
            arelation_patronymic.getValue(),
            arelation_birthday.getValue(),
            arelation_birthplace.getValue(),
            arelation_office.getValue(),
            arelation_post.getValue(),
            arelation_home_address.getValue(),
            aemp_code.getValue(),
            fdate,
            deathday,
            acod_str_live.getValue(),
            acod_obl_live.getValue(),
            acod_obl_live_prim.getValue(),
            acod_city.getValue(),
            acod_city_prim.getValue(),
            acod_str_live_prim.getValue(),
            acod_str_birth.getValue(),
            acod_str_birth_prim.getValue(),
            acod_obl_birth.getValue(),
            acod_obl_birth_prim.getValue(),
            acod_city_birth.getValue(),
            acod_city_birth_prim.getValue(),
            add.getValue(),
            amm.getValue(),
            day_death,
            mouth_death,
            aemp_code_name.getValue()
            ));
            ok_relationService.create(current2);
            
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ok_relationFilter();
        if (fid == null) { 
          filter.setId(fid.getValue());
        }
          filter.setBranch(fbranch.getValue());
          if (fpersonal_code == null) { 
          filter.setPersonal_code(fpersonal_code.getValue());
          }
          if (frelation_code == null) { 
          filter.setRelation_code(frelation_code.getValue());
          }
          filter.setRelation_family(frelation_family.getValue());
          filter.setRelation_name(frelation_name.getValue());
          filter.setRelation_patronymic(frelation_patronymic.getValue());
          if (frelation_birthday == null) { 
          filter.setRelation_birthday(frelation_birthday.getValue());
          }
          filter.setRelation_birthplace(frelation_birthplace.getValue());
          filter.setRelation_office(frelation_office.getValue());
          filter.setRelation_post(frelation_post.getValue());
          filter.setRelation_home_address(frelation_home_address.getValue());
          if (femp_code == null) { 
          filter.setEmp_code(femp_code.getValue());
          }
          try {
              if (!fins_date.getValue().equals("")) {
                  String date_str = tdf.format(fins_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setIns_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          if (frelation_deathday == null) { 
          filter.setRelation_deathday(frelation_deathday.getValue());
          }
          filter.setCod_str_live(fcod_str_live.getValue());
          filter.setCod_obl_live(fcod_obl_live.getValue());
          filter.setCod_obl_live_prim(fcod_obl_live_prim.getValue());
          filter.setCod_city(fcod_city.getValue());
          filter.setCod_city_prim(fcod_city_prim.getValue());
          filter.setCod_str_live_prim(fcod_str_live_prim.getValue());
          filter.setCod_str_birth(fcod_str_birth.getValue());
          filter.setCod_str_birth_prim(fcod_str_birth_prim.getValue());
          filter.setCod_obl_birth(fcod_obl_birth.getValue());
          filter.setCod_obl_birth_prim(fcod_obl_birth_prim.getValue());
          filter.setCod_city_birth(fcod_city_birth.getValue());
          filter.setCod_city_birth_prim(fcod_city_birth_prim.getValue());
          if (fdd == null) { 
          filter.setDd(fdd.getValue());
          }
          if (fmm == null) { 
          filter.setMm(fmm.getValue());
          }
          if (fdd_death == null) { 
          filter.setDd_death(fdd_death.getValue());
          }
          if (fmm_death == null) { 
          filter.setMm_death(mm_death.getValue());
          }
          filter.setEmp_code_name(femp_code_name.getValue());

    }else{
        
          current.setId(id.getValue());
          current.setBranch(branch.getValue());
          current.setPersonal_code(personal_code.getValue());
          current.setRelation_code(Integer.parseInt(relation_code.getValue()));
          current.setRelation_family(relation_family.getValue());
          current.setRelation_name(relation_name.getValue());
          current.setRelation_patronymic(relation_patronymic.getValue());
          current.setRelation_birthday(relation_birthday.getValue());
          current.setRelation_birthplace(relation_birthplace.getValue());
          current.setRelation_office(relation_office.getValue());
          current.setRelation_post(relation_post.getValue());
          current.setRelation_home_address(relation_home_address.getValue());
          current.setEmp_code(emp_code.getValue());
          try {
              if (!ins_date.getValue().equals("")) {
                  String date_str = tdf.format(ins_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setIns_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          current.setRelation_deathday(relation_deathday.getValue());
          current.setCod_str_live(cod_str_live.getValue());
          current.setCod_obl_live(cod_obl_live.getValue());
          current.setCod_obl_live_prim(cod_obl_live_prim.getValue());
          current.setCod_city(cod_city.getValue());
          current.setCod_city_prim(cod_city_prim.getValue());
          current.setCod_str_live_prim(cod_str_live_prim.getValue());
          current.setCod_str_birth(cod_str_birth.getValue());
          current.setCod_str_birth_prim(cod_str_birth_prim.getValue());
          current.setCod_obl_birth(cod_obl_birth.getValue());
          current.setCod_obl_birth_prim(cod_obl_birth_prim.getValue());
          current.setCod_city_birth(cod_city_birth.getValue());
          current.setCod_city_birth_prim(cod_city_birth_prim.getValue());
          current.setDd(dd.getValue());
          current.setMm(mm.getValue());
          current.setDd_death(dd_death.getValue());
          current.setMm_death(mm_death.getValue());
          current.setEmp_code_name(emp_code_name.getValue());
          ok_relationService.update(current);
    }
onClick$btn_back();
refreshModel(_startPageNumber);

SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
    System.out.println("�����-�� ������");
}

}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new ok_relationFilter();
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
