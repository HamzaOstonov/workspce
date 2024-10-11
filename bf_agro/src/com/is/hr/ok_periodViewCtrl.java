package com.is.hr;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.zkoss.zul.api.Intbox;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class ok_periodViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd,fgrddiv,addgrddiv;
    private Grid addgrd,fgrd,frmgrd1,frmgrd2,frmgrd3,frmgrd4,frmgrd5,frmgrd6,frmgrd7;
    private Toolbarbutton btn_last;
    private Hbox frmgrd;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
   // private Toolbarbutton btn_add; 
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbarbutton btn_save;
    
    private Intbox aid,apersonal_code,atype_period_code,aarticle_code,aemp_code,abase_move_code,apost_code,adepartment_code,astag_code;
    
    private Datebox  ain_office_date,in_office_date,out_office_date,aout_office_date,adate_pr_off,fout_office_date,fin_office_date,ains_date,fbasis_date,abasis_date,fins_date,date_pr_kvl,date_utv_km,adate_pr_kvl, fdate_pr_kvl ,fdate_attest, fdate_pr_off,date_pr_off,ins_date  ,adate_attest ,fdate_utv_km, adate_utv_km, date_attest ;
    private Toolbar tb;
    private Textbox id,branch,personal_code,motive_out,type_period_code,office_name,office_address,established_post,article_code,basis_num,basis_date,emp_code,base_move_code,established_department,priz_system,cod_bank,cod_type_prn,cod_pr_off,pr_off,numb_utv_km,numb_pr_kvl,resh_attest,doljn_id,num_pr_off,post_code,department_code,department,stag_code,emp_code_name;
    private Textbox abranch,amotive_out, acod_type_prn,acod_pr_off,adoljn_id,acod_bank,aoffice_name,aoffice_address,abasis_num,aestablished_post,aestablished_department,apriz_system,apr_off,anumb_utv_km,anumb_pr_kvl,aresh_attest,anum_pr_off,adepartment,aemp_code_name;
    private Textbox fid,fbranch,fpersonal_code,foffice_name,foffice_address,festablished_post,fmotive_out,ftype_period_code,farticle_code,fbasis_num,femp_code,fbase_move_code,festablished_department,fpriz_system,fcod_bank,fcod_type_prn,fcod_pr_off,fpr_off,fnumb_utv_km,fnumb_pr_kvl,fresh_attest,fdoljn_id,fnum_pr_off,fpost_code,fdepartment_code,fdepartment,fstag_code,femp_code_name;
    private Paging ok_periodPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private int ppid=0;

    
    public ok_periodFilter filter;

    PagingListModel_ok_period model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat tdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

private ok_period current = new ok_period();

    public ok_periodViewCtrl() {
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

    filter = new ok_periodFilter();
    parameter = (String[]) param.get("ppid");
    if (parameter!=null){
      ppid = Integer.parseInt(parameter[0]);
      filter.setPersonal_code(ppid);
    }
    
    filter.setPersonal_code(ppid);

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ok_period pok_period = (ok_period) data;

                row.setValue(pok_period);
                
                //row.appendChild(new Listcell(pok_period.getId()+""));
                row.appendChild(new Listcell(pok_period.getBranch()));
                row.appendChild(new Listcell(pok_period.getPersonal_code()+""));
                row.appendChild(new Listcell(pok_period.getIn_office_date()+""));
                row.appendChild(new Listcell(pok_period.getOut_office_date()+""));
                row.appendChild(new Listcell(pok_period.getOffice_name()));
                row.appendChild(new Listcell(pok_period.getOffice_address()));
                row.appendChild(new Listcell(pok_period.getEstablished_post()));
                row.appendChild(new Listcell(pok_period.getMotive_out()));
                row.appendChild(new Listcell(pok_period.getType_period_code()+""));
                row.appendChild(new Listcell(pok_period.getArticle_code()+""));
                row.appendChild(new Listcell(pok_period.getBasis_num()));
                row.appendChild(new Listcell(pok_period.getBasis_date()+""));
                row.appendChild(new Listcell(pok_period.getEmp_code()+""));
                row.appendChild(new Listcell(pok_period.getIns_date()+""));
                row.appendChild(new Listcell(pok_period.getBase_move_code()+""));
                row.appendChild(new Listcell(pok_period.getEstablished_department()));
                row.appendChild(new Listcell(pok_period.getPriz_system()));
                row.appendChild(new Listcell(pok_period.getCod_bank()));
                row.appendChild(new Listcell(pok_period.getCod_type_prn()));
                row.appendChild(new Listcell(pok_period.getCod_pr_off()));
                row.appendChild(new Listcell(pok_period.getPr_off()));
                row.appendChild(new Listcell(pok_period.getDate_utv_km()+""));
                row.appendChild(new Listcell(pok_period.getNumb_utv_km()));
                row.appendChild(new Listcell(pok_period.getDate_pr_kvl()+""));
                row.appendChild(new Listcell(pok_period.getNumb_pr_kvl()));
                row.appendChild(new Listcell(pok_period.getDate_attest()+""));
                row.appendChild(new Listcell(pok_period.getResh_attest()));
                row.appendChild(new Listcell(pok_period.getDoljn_id()));
                row.appendChild(new Listcell(pok_period.getNum_pr_off()));
                row.appendChild(new Listcell(pok_period.getDate_pr_off()+""));
                row.appendChild(new Listcell(pok_period.getPost_code()+""));
                row.appendChild(new Listcell(pok_period.getDepartment_code()+""));
                row.appendChild(new Listcell(pok_period.getDepartment()));
                row.appendChild(new Listcell(pok_period.getStag_code()+""));
                row.appendChild(new Listcell(pok_period.getEmp_code_name()));


    }});
           
    refreshModel(_startPageNumber);

}

public void onPaging$ok_periodPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
	filter.setPersonal_code(ppid);
    ok_periodPaging.setPageSize(_pageSize);
model = new PagingListModel_ok_period(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

ok_periodPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(ok_period) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public ok_period getCurrent() {
return current;
}

public void setCurrent(ok_period current) {
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
   btn_save.setLabel("��������");
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
    	
    	
    	Date fdate = null;
        try {
            if(!ain_office_date.getValue().equals("")) {
                System.out.println("date: " + ain_office_date.getValue());
                String date_str = df.format(ain_office_date.getValue());
                fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                System.out.println("TESSSSSSSSSSSSST"+fdate.toString());
                System.out.println("date2: " + date_str);
                System.out.println(fdate);
                
                
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //=====================================================
    
        Date out_office_date = null;
        try {
        	if(!aout_office_date.getValue().equals("")) {
                System.out.println("date: " + aout_office_date.getValue());
                String date_str = df.format(aout_office_date.getValue());
                out_office_date = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                System.out.println("TESSSSSSSSSSSSST"+out_office_date.toString());
                System.out.println("date2: " + date_str);
                System.out.println( out_office_date );
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        //====================================================
        
        Date basis_date = null;
        try {
        	if(!abasis_date.getValue().equals("")) {
                System.out.println("date: " + abasis_date.getValue());
                String date_str = df.format(abasis_date.getValue());
                basis_date = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                System.out.println("TESSSSSSSSSSSSST"+basis_date.toString());
                System.out.println("date2: " + date_str);
                System.out.println(basis_date);
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //================================���
        Date ins_date = null;
        try {
        	if(!ains_date.getValue().equals("")) {
                System.out.println("date: " + ains_date.getValue());
                String date_str = tdf.format(ains_date.getValue());
                ins_date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(date_str);
                System.out.println("TESSSSSSSSSSSSST"+ins_date.toString());
                System.out.println("date2: " + date_str);
                System.out.println(ins_date);
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //======================================
        Date date_utv_km = null;
        try{
        if(!adate_utv_km.getValue().equals("")) {
            System.out.println("date: " + adate_utv_km.getValue());
            String date_str = df.format(adate_utv_km.getValue());
            date_utv_km = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
            System.out.println("TESSSSSSSSSSSSST"+date_utv_km.toString());
            System.out.println("date2: " + date_str);
            System.out.println(date_utv_km);
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        //======================================
        Date date_pr_kvl = null;
        try{
        if(!adate_pr_kvl.getValue().equals("")) {
            System.out.println("date: " + adate_pr_kvl.getValue());
            String date_str = df.format(adate_pr_kvl.getValue());
            date_pr_kvl = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
            System.out.println("TESSSSSSSSSSSSST"+date_pr_kvl.toString());
            System.out.println("date2: " + date_str);
            System.out.println(date_pr_kvl);
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //=-=======================================
        Date date_attest = null;
        try {
        	if(!adate_attest.getValue().equals("")) {
                System.out.println("date: " + adate_attest.getValue());
                String date_str = df.format(adate_attest.getValue());
                date_attest = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                System.out.println("TESSSSSSSSSSSSST"+date_attest.toString());
                System.out.println("date2: " + date_str);
                System.out.println(date_attest);
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //=========================================
        Date date_pr_off = null;
        try{
        if(!adate_pr_off.getValue().equals("")) {
            System.out.println("date: " + adate_pr_off.getValue());
            String date_str = df.format(adate_pr_off.getValue());
            date_pr_off = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
            System.out.println("TESSSSSSSSSSSSST"+date_pr_off.toString());
            System.out.println("date2: " + date_str);
            System.out.println(date_pr_off);
            } else
                throw new Exception("������ ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("id ������"+aid.getValue());
            ok_periodService.create(new ok_period(
                     aid.getValue(),           
                      abranch.getValue(),
                   apersonal_code.getValue(),
                      fdate,
                      out_office_date,
                      aoffice_name.getValue(),
                      aoffice_address.getValue(),
                      aestablished_post.getValue(),
                      amotive_out.getValue(),
                      atype_period_code.getValue(),
                      aarticle_code.getValue(),
                      abasis_num.getValue(),
                      basis_date,
                      aemp_code.getValue(),
                     ins_date,
                  abase_move_code.getValue(),
                      aestablished_department.getValue(),
                      apriz_system.getValue(),
                      acod_bank.getValue(),
                      acod_type_prn.getValue(),
                      acod_pr_off.getValue(),
                      apr_off.getValue(),
                      date_utv_km,
                      anumb_utv_km.getValue(),
                      date_pr_kvl,
                      anumb_pr_kvl.getValue(),
                      date_attest,
                      aresh_attest.getValue(),
                      adoljn_id.getValue(),
                      anum_pr_off.getValue(),
                      date_pr_off,
                     apost_code.getValue(),
                      adepartment_code.getValue(),
                      adepartment.getValue(),
                     astag_code.getValue(),
                      aemp_code_name.getValue()
                      ));
            
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
       
     
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ok_periodFilter();
        
          filter.setId(Double.parseDouble(fid.getValue()));
          filter.setBranch(fbranch.getValue());
          
          filter.setPersonal_code(Integer.parseInt(fpersonal_code.getValue()));
          try {
              if (!fin_office_date.getValue().equals("")) {
                  String date_str = tdf.format(fin_office_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setIn_office_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          //
          try {
              if (!fout_office_date.getValue().equals("")) {
                  String date_str = tdf.format(fout_office_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setOut_office_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
                   
          filter.setOffice_name(foffice_name.getValue());
          filter.setOffice_address(foffice_address.getValue());
          filter.setEstablished_post(festablished_post.getValue());
          filter.setMotive_out(fmotive_out.getValue());
          
          filter.setType_period_code(Double.parseDouble(ftype_period_code.getValue()));
          
          filter.setArticle_code(Double.parseDouble(farticle_code.getValue()));
          
          filter.setBasis_num(fbasis_num.getValue());
          try {
              if (!fbasis_date.getValue().equals("")) {
                  String date_str = tdf.format(fbasis_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setBasis_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }          
          
          filter.setEmp_code(Double.parseDouble(femp_code.getValue()));
          try {
              if (!fins_date.getValue().equals("")) {
                  String date_str = tdf.format(fins_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setIns_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
                 
          filter.setBase_move_code(Double.parseDouble(fbase_move_code.getValue()));         
          filter.setEstablished_department(festablished_department.getValue());
          filter.setPriz_system(fpriz_system.getValue());
          filter.setCod_bank(fcod_bank.getValue());
          filter.setCod_type_prn(fcod_type_prn.getValue());
          filter.setCod_pr_off(fcod_pr_off.getValue());
          filter.setPr_off(fpr_off.getValue());
             
          try {
              if (!fdate_utv_km.getValue().equals("")) {
                  String date_str = tdf.format(fdate_utv_km.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setDate_utv_km(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          filter.setNumb_utv_km(fnumb_utv_km.getValue());
          
        
          try {
              if (!fdate_pr_kvl.getValue().equals("")) {
                  String date_str = tdf.format(fdate_pr_kvl.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setDate_pr_kvl(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          filter.setNumb_pr_kvl(fnumb_pr_kvl.getValue());
          
       

          try {
              if (!fdate_attest.getValue().equals("")) {
                  String date_str = tdf.format(fdate_attest.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setDate_attest(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          filter.setResh_attest(fresh_attest.getValue());
          filter.setDoljn_id(fdoljn_id.getValue());
          filter.setNum_pr_off(fnum_pr_off.getValue());
          
        
          try {
              if (!fdate_pr_off.getValue().equals("")) {
                  String date_str = tdf.format(fdate_pr_off.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  filter.setDate_pr_off(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          filter.setPost_code(Double.parseDouble(fpost_code.getValue()));
          
          filter.setDepartment_code(Double.parseDouble(fdepartment_code.getValue()));
          
          filter.setDepartment(fdepartment.getValue());
          filter.setStag_code(Double.parseDouble(fstag_code.getValue()));
          filter.setEmp_code_name(femp_code_name.getValue());

    }else{
    	current.setEmp_code(Integer.parseInt(emp_code.getValue()));
          current.setId(Integer.parseInt(id.getValue()));
          current.setBranch(branch.getValue());
          current.setPersonal_code(Integer.parseInt(personal_code.getValue()));
         
          try {
              if (!in_office_date.getValue().equals("")) {
                  String date_str = tdf.format(in_office_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setIn_office_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
         
          try {
              if (!out_office_date.getValue().equals("")) {
                  String date_str = tdf.format(out_office_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setOut_office_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          current.setOffice_name(office_name.getValue());
          current.setOffice_address(office_address.getValue());
          current.setEstablished_post(established_post.getValue());
          current.setMotive_out(motive_out.getValue());
          current.setType_period_code(Integer.parseInt(type_period_code.getValue()));
          current.setArticle_code(Integer.parseInt(article_code.getValue()));
          current.setBasis_num(basis_num.getValue());
          
          
          
          try {
              if (!basis_date.getValue().equals("")) {
                  String date_str = tdf.format(basis_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setBasis_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setEmp_code(Integer.parseInt(emp_code.getValue()));
          
          
          try {
              if (!ins_date.getValue().equals("")) {
                  String date_str = tdf.format(ins_date.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setIns_date(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setBase_move_code(Integer.parseInt(base_move_code.getValue()));
          current.setEstablished_department(established_department.getValue());
          current.setPriz_system(priz_system.getValue());
          current.setCod_bank(cod_bank.getValue());
          current.setCod_type_prn(cod_type_prn.getValue());
          current.setCod_pr_off(cod_pr_off.getValue());
          current.setPr_off(pr_off.getValue());
        
          
          try {
              if (!date_utv_km.getValue().equals("")) {
                  String date_str = tdf.format(date_utv_km.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setDate_utv_km(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setNumb_utv_km(numb_utv_km.getValue());
       
          
          try {
              if (!date_pr_kvl.getValue().equals("")) {
                  String date_str = tdf.format(date_pr_kvl.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setDate_pr_kvl(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setNumb_pr_kvl(numb_pr_kvl.getValue());
          
          
          try {
              if (!date_attest.getValue().equals("")) {
                  String date_str = tdf.format(date_attest.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setDate_attest(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setResh_attest(resh_attest.getValue());
          current.setDoljn_id(doljn_id.getValue());
          current.setNum_pr_off(num_pr_off.getValue());
        
          
          try {
              if (!date_pr_off.getValue().equals("")) {
                  String date_str = tdf.format(date_pr_off.getValue());
                  Date fdate = new SimpleDateFormat("dd.MM.yyyy").parse(date_str);
                  current.setDate_pr_off(fdate);
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
          
          current.setPost_code(Integer.parseInt(post_code.getValue()));
          current.setDepartment_code(Integer.parseInt(department_code.getValue()));
          current.setDepartment(department.getValue());
          current.setStag_code(Integer.parseInt(stag_code.getValue()));
          current.setEmp_code_name(emp_code_name.getValue());
        ok_periodService.update(current);
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
            filter = new ok_periodFilter();
    }
onClick$btn_back();
addgrd.setVisible(false);
fgrd.setVisible(false);
frmgrd.setVisible(true);

CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}



}
