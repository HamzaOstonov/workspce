
package com.is.tf.policy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.policysumchange.Policysumchange;
import com.is.tf.policytimechange.Policytimechange;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.PolicyResult;
import com.sbs.service.PolicySumChangeResult;
import com.sbs.service.PolicyTimeChangeResult;

public class PolicyViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid,SumchangeGrid,TimechangeGrid;
    private Paging contactPaging;
    private Div grd,sum_div;
    private Grid pol_sum,pol_time,addgrd,frmgrd,fgrd,sum_grd,time_grd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search,btn_edit,btn_save2,btn_delete2,btn_add2,btn_back2;
    private Toolbarbutton btn_pol,btn_pol1,btn_pol2, btn_back, btn_delete;
    private Toolbar tb;
    private Textbox p10t33a,p8t33a,p3t33a,p5t33a,p11t34a,p13t34,p13t32,p16t32,p10t33,p5t33,p11t34,p8t33,p1t33,p3t33,p1t34,id,p1t32,p0t32,p2t32,p3t32,p4t32,p12t32;
    private Textbox ap13t32,ap16t32,aid,ap1t32,ap0t32,ap2t32,ap3t32,ap4t32,ap12t32 ;
    private Textbox fp13t32,fp16t32,fid,fp1t32,fp0t32,fp2t32,fp3t32,fp4t32,fp12t32 ;
    private Longbox aid_contract,fid_contract;
    private Label line1,line2,line3,line4,line5,line6,line7,line8,line9;
    private Label aline1,aline2,aline3,aline4,aline5,aline6,aline7;
    private Listheader list5,list6;
    private Row pol_date1_33a,pol_date2_33a,pol_date3_33a,pol_date4_33a,row_p7t33a,row_p8t33a,row_p10t34a,row_p11t34a,asum_gar1,agar_date1,agar_date2,row_kur,asum_gar2,akur,sum_gar2,kur,row_p5t22,row_p6t22,row_p9t23,row_p10t23,pol_date1,pol_date2;
    private Row pol_date1_33,pol_date2_33,pol_date3_33,pol_date4_33,row_p10t34,row_p11t34,row_p7t33,row_p8t33;
    private Datebox p11t33a,p2t33a,p4t33a,p14t34,p11t33,p4t33,p2t33,p5t32,p11t32,ap5t32,fp5t32,ap11t32,fp11t32;
    private Decimalbox p8t34a,p7t34a,p6t342a,p5t34a,p7t34,p8t34,p5t34,p3t34,p7t321,ap7t321,ap8t322,p6t342,p8t322,p4t34,p7t32,p8t32,p9t32,p10t32,fp7t32,fp8t32,fp9t32,fp10t32,ap7t32,ap8t32,ap9t32,ap10t32;
    private RefCBox p6t33a,p7t33a,p10t34a,p9t34a,p6t343a,p6t341a,p6t33,p7t33,p9t34, p10t34, p6t32,ap6t32,fp6t32;
    private Label summ1,asumm1,pol_val,pol_val2,aconr_val1,aconr_val2,acbcourse,p4t341, conr_val1, conr_val2, conr_val1a, conr_val2a, cbcourse;
    private Checkbox checksum, achecksum;
    private RefCurrencyBox ap8t321,ap8t323,p6t343,p8t321,p8t323,p6t341;
    private List<RefData> currencies = new ArrayList<RefData>();
    private List<RefData> reasons = new ArrayList<RefData>();
    private List<RefData> agriments = new ArrayList<RefData>();
    private List<RefCurrencyData> currenciesg = new ArrayList<RefCurrencyData>();
    private Paging policyPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private HashMap< String,String> curr_ =null;
    private boolean _needsTotalSizeUpdate = true;
    private long idc,gid,gidc,id_contract;
    private String alias,idn,  val1, val2,cu;
    private String  pol_P11T32,pol_P12T32,cont_type,otn,aotn,otn2,summa1,summa2;
    private int desktopHeight=0;
    
    public PolicyFilter filter = new PolicyFilter();
    
    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Policy current = new Policy();
private Policysumchange currentSum = new Policysumchange();
private Policytimechange currentTime = new Policytimechange();


    public PolicyViewCtrl() {
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
            binder.bindBean("currentSum", this.currentSum);
            binder.bindBean("currentTime", this.currentTime);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }
    parameter = (String[]) param.get("idn");
    if (parameter!=null){
 	idn = (parameter[0]);
     // System.out.println("Garant  cont_idn "+idn);   
 }
    parameter = (String[]) param.get("cont_type");
    if (parameter!=null){
    	cont_type = (parameter[0]);
     // System.out.println("Garant  cont_idn "+idn);   
 }
    parameter = (String[]) param.get("idc");
    if (parameter!=null){
 	idc = Long.parseLong((parameter[0]));
 	 //System.out.println("ID  "+idc+" idn  "+idn);
     // System.out.println("Garant  cont_idn "+idn);   
 }
    parameter = (String[]) param.get("summa1");
    if (parameter!=null){
 	summa1 = (parameter[0]);
     
 }
    parameter = (String[]) param.get("summa2");
    if (parameter!=null){
 	summa2 = (parameter[0]);
    System.out.println("Contract summa2 "+summa2);   
 }
    parameter = (String[]) param.get("val1");
    if (parameter!=null){
 	val1 = (parameter[0]);
    //System.out.println("Garant  cont_val1 "+val1);   
 }
    parameter = (String[]) param.get("val2");
    if (parameter!=null){
 	val2 = (parameter[0]);
    // System.out.println("Garant  cont_val2 "+val2);   
 }
    parameter = (String[]) param.get("pol_P11T32");
    if (parameter!=null){
    	pol_P11T32 = (parameter[0]);
  }
    parameter = (String[]) param.get("pol_P12T32");
    if (parameter!=null){
    	pol_P12T32 = (parameter[0]);
  } 
     curr_ = com.is.tf.contract.ContractService.getHCurr(alias);

 //conr_val1.setValue(val1);
 
 conr_val2.setValue(curr_.get(val2));
 conr_val1.setValue(curr_.get(val1));
 
 conr_val2a.setValue(curr_.get(val2));
 conr_val1a.setValue(curr_.get(val1));
 aconr_val1.setValue(curr_.get(val1));
 aconr_val2.setValue(curr_.get(val2));
if (pol_P12T32.equals("1")) {
	list6.setVisible(true);
} else {
	list6.setVisible(false);
}
if (pol_P11T32.equals("1")) {
	list5.setVisible(true);
} else {
	list5.setVisible(false);
}
System.out.println("current.getP12t32()="+current.getP12t32()+"  current.getP11t32()="+current.getP11t32()+"ggg="+p11t32.getValue());

 line1.setValue(Labels.getLabel("policy.p3t32").replaceAll("<br>", "\r\n"));
 line2.setValue(Labels.getLabel("policy.p5t32").replaceAll("<br>", "\r\n"));
 line3.setValue(Labels.getLabel("policy.p7t32").replaceAll("<br>", "\r\n"));
 line4.setValue(Labels.getLabel("policy.p6t32").replaceAll("<br>", "\r\n"));
 line5.setValue(Labels.getLabel("policy.p11t32tab").replaceAll("<br>", "\r\n"));
 line6.setValue(Labels.getLabel("policy.p12t32tab").replaceAll("<br>", "\r\n"));
 line7.setValue(Labels.getLabel("policy.p13t32tab").replaceAll("<br>", "\r\n"));
 line8.setValue(Labels.getLabel("policy.p17t32tab").replaceAll("<br>", "\r\n"));
 line9.setValue(Labels.getLabel("policy.p100t32").replaceAll("<br>", "\r\n"));
 
  
 
 SumchangeGrid.setItemRenderer(new ListitemRenderer(){
	    @SuppressWarnings("unchecked")
	    public void render(Listitem row, Object data) throws Exception {
	                Policysumchange pPolicysumchange = (Policysumchange) data;

	                row.setValue(pPolicysumchange);
	                
	                row.appendChild(new Listcell(pPolicysumchange.getId()+""));
	                row.appendChild(new Listcell(pPolicysumchange.getP12t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP0t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP1t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP2t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP3t34()+""));
	                row.appendChild(new Listcell(pPolicysumchange.getP4t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP5t34()+""));
	                row.appendChild(new Listcell(pPolicysumchange.getP6t34()+""));
	                row.appendChild(new Listcell(pPolicysumchange.getP7t34()+""));
	                row.appendChild(new Listcell(pPolicysumchange.getP8t34()+""));
	                row.appendChild(new Listcell(pPolicysumchange.getP9t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP10t34()));
	                row.appendChild(new Listcell(pPolicysumchange.getP13t34()));
	                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPolicysumchange.getP100t34()))));


	    }});

	    refreshModel(_startPageNumber);
	    
	    TimechangeGrid.setItemRenderer(new ListitemRenderer(){
	    	
	        @SuppressWarnings("unchecked")
	        public void render(Listitem row, Object data) throws Exception {
	                    Policytimechange pPolicytimechange = (Policytimechange) data;

	                    row.setValue(pPolicytimechange);
	                    
	                    row.appendChild(new Listcell(pPolicytimechange.getId()+""));
	                    row.appendChild(new Listcell(pPolicytimechange.getP9t33()+""));
	                    row.appendChild(new Listcell(pPolicytimechange.getP0t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP1t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP2t33()+""));
	                    row.appendChild(new Listcell(pPolicytimechange.getP3t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP4t33()+""));
	                    row.appendChild(new Listcell(pPolicytimechange.getP5t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP6t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP7t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP8t33()));
	                    row.appendChild(new Listcell(pPolicytimechange.getP10t33()));
	                    row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPolicytimechange.getP100t33()))));


	        }});

	        refreshModel(_startPageNumber);
        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Policy pPolicy = (Policy) data;

                row.setValue(pPolicy);
                row.appendChild(new Listcell(pPolicy.getP3t32()+""));
                row.appendChild(new Listcell(pPolicy.getP5t32()+""));
                row.appendChild(new Listcell(pPolicy.getP7t32()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(String.valueOf(pPolicy.getP6t32()))));
                row.appendChild(new Listcell(pPolicy.getP11t32()+""));
                row.appendChild(new Listcell(pPolicy.getP12t32()));
                row.appendChild(new Listcell(pPolicy.getP13t32()));
                row.appendChild(new Listcell(pPolicy.getP17t32()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pPolicy.getP100t32()))));

                /*
                row.appendChild(new Listcell(pPolicy.getId()+""));
                row.appendChild(new Listcell(pPolicy.getP1t32()));
                row.appendChild(new Listcell(pPolicy.getP0t32()));
                row.appendChild(new Listcell(pPolicy.getP2t32()));
                row.appendChild(new Listcell(pPolicy.getP4t32()));
                row.appendChild(new Listcell(pPolicy.getP8t32()+""));
                row.appendChild(new Listcell(pPolicy.getP9t32()+""));
                row.appendChild(new Listcell(pPolicy.getP10t32()+""));
                row.appendChild(new Listcell(pPolicy.getP16t32()));
                
                //row.appendChild(new Listcell(pPolicy.getP101t32()));
             */   
               
                currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
              
                // currencies1 = com.is.tf.contract.ContractService.getMyCurrAcr(idn, gid, alias);
                 reasons = ContractService.getReasons("4,5", alias);
                 if (agriments!=null){
                 agriments = com.is.tf.contract.ContractService.getAgreement(idn, alias);
                 p10t34.setModel((new ListModelList(agriments)));
                 p7t33.setModel((new ListModelList(agriments)));
                 }
                 p6t33.setModel((new ListModelList(reasons)));
                 p9t34.setModel((new ListModelList(reasons)));
                 p9t34a.setModel((new ListModelList(reasons)));
                 p6t32.setModel((new ListModelList(currencies)));
                 p6t33a.setModel((new ListModelList(reasons)));
                 ap6t32.setModel((new ListModelList(currencies)));
            }});
         refreshModel(_startPageNumber);
    
}

public void onPaging$policyPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
	
	 filter.setP1t32(idn);
	 filter.setId_contract(idc);
	policyPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter, "");
      //  _needsTotalSizeUpdate = false;
}


policyPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Policy) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Policy getCurrent() {
return current;
}

public void setCurrent(Policy current) {
this.current = current;
}

public Policysumchange getCurrentSum() {
	return currentSum;
	}

	public void setCurrentSum(Policysumchange currentSum) {
	this.currentSum = currentSum;
	}
	public Policytimechange getCurrentTime() {
		return currentTime;
		}

		public void setCurrentTime(Policytimechange currentTime) {
		this.currentTime = currentTime;
		}
	
		public void onClick$btn_edit() {
			btn_delete2.setVisible(true);
			btn_save2.setVisible(true);
			btn_back2.setVisible(true);
			btn_add2.setVisible(true);
			if (SumchangeGrid.isVisible()){
				p5t34a.setReadonly(false);
				p6t342a.setReadonly(false);
		    	p7t34a.setReadonly(false);
		    	p8t34a.setReadonly(false);
		    	p9t34a.setDisabled(false);
		    	p10t34a.setDisabled(false);
		    	p11t34a.setReadonly(false);
			}
			if (TimechangeGrid.isVisible()){
				p3t33a.setReadonly(false);
		    	p5t33a.setReadonly(false);
		    	p4t33a.setDisabled(false);
		    	p6t33a.setDisabled(false);
		    	p2t33a.setDisabled(false);
		    	p7t33a.setDisabled(false);
		    	p8t33a.setReadonly(false);
		    	p11t33a.setDisabled(false);
		    	
			}
		}

		public void onClick$btn_back2() {
		    if (frm.isVisible()){
		        frm.setVisible(false);
		        grd.setVisible(true);
		        btn_back.setImage("/images/file.png");
		        btn_back.setLabel(Labels.getLabel("back"));
		    }else onDoubleClick$dataGrid$grd();
		}
		
public void onDoubleClick$dataGrid$grd() {
	try{        
	grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            pol_sum.setVisible(false);
            pol_time.setVisible(false);
            fgrd.setVisible(false);
            SumchangeGrid.setVisible(true);
            TimechangeGrid.setVisible(true);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
            //pol_val.setValue(curr_.get(current.getP6t32()));
            //pol_val2.setValue(curr_.get(current.getP6t32()));
            //gid=current.getId();
            setCurrent();
            setCurrentAdd();
          
            // cu=current.getp6t32();
	 }catch (Exception e) {
			e.printStackTrace();
			alert("onDoubleClick$dataGrid$grd()="+e.getMessage());
		}
}

public void onSelect$SumchangeGrid$grd() {
    try{  
    	btn_edit.setVisible(true);
    	btn_delete2.setVisible(false);
    	btn_save2.setVisible(false);
    	btn_back2.setVisible(false);
    	btn_add2.setVisible(false);
    	p5t34a.setReadonly(true);
    	p6t342a.setReadonly(true);
    	p7t34a.setReadonly(true);
    	p8t34a.setReadonly(true);
    	p9t34a.setDisabled(true);
    	p10t34a.setDisabled(true);
    	p11t34a.setReadonly(true);
    	sum_div.setVisible(true);
    	sum_grd.setVisible(true);
    	time_grd.setVisible(false);
    	setCurrent();
    
    }catch (Exception e) {
		e.printStackTrace();
		alert("onDoubleClick$SumChangeGrid$grd()= "+e.getMessage());
	}
}

public void onSelect$TimechangeGrid$grd() {
    try{        
    	//alert("ad");
    	btn_edit.setVisible(true);
    	btn_delete2.setVisible(false);
    	btn_save2.setVisible(false);
    	btn_back2.setVisible(false);
    	btn_add2.setVisible(false);
    	sum_div.setVisible(true);
    	sum_grd.setVisible(false);
    	time_grd.setVisible(true);
    	p3t33a.setReadonly(true);
    	p5t33a.setReadonly(true);
    	p4t33a.setDisabled(true);
    	p6t33a.setDisabled(true);
    	p2t33a.setDisabled(true);
    	p7t33a.setDisabled(true);
    	p8t33a.setReadonly(true);
    	p11t33a.setDisabled(true);
    	
    	setCurrent();
    
    }catch (Exception e) {
		e.printStackTrace();
		alert("onDoubleClick$TimeChangeGrid$grd()= "+e.getMessage());
	}
}




public void onClick$btn_pol() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(true);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    pol_sum.setVisible(false);
    pol_time.setVisible(false);
    
    
    
}
public void onClick$btn_pol1() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    pol_time.setVisible(false);
    btn_pol.setVisible(true);
    pol_sum.setVisible(true);
    if (pol_sum.isVisible()){
		  InputElement[] list = {p1t34,p3t34,p4t34 };
		    for (int i=0;i<list.length;i++){
		    	   if (list[i]!=null){
		    	     list[i].setDisabled(true);
		    	   }}}
    p6t341.setSelecteditem(p8t321.getValue());
    p6t343.setSelecteditem(p8t323.getValue());
    p4t341.setValue(curr_.get(current.getP6t32()));
    p13t34.setValue((String)session.getAttribute("un"));
    //alert("current.getP8t32()  ="+p8t322.doubleValue());
    
		    }
	
public void onClick$btn_pol2() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    btn_pol.setVisible(true);
    pol_sum.setVisible(false);
    pol_time.setVisible(true);
    if (pol_time.isVisible()){
		  InputElement[] list = {p1t33,p2t33,p3t33 };
		    for (int i=0;i<list.length;i++){
		    	   if (list[i]!=null){
		    	     list[i].setDisabled(true);
		    	   }
		    }
	}
    p10t33.setValue((String)session.getAttribute("un"));
    
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
    ap1t32.setValue(idn);
    ap13t32.setValue((String)session.getAttribute("un"));
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



public void onClick$btn_save2() {
	try {
		
		
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.PolicyResult acr =  new com.sbs.service.PolicyResult();
		
		if (!CheckNull.isEmpty(p5t34a.doubleValue())||(Double.parseDouble(summa1)>p5t34a.doubleValue())){
			  otn2=("0");
		    } else if (!CheckNull.isEmpty(p5t34a.doubleValue())||(Double.parseDouble(summa1)<p5t34a.doubleValue())){
		    	otn2=("1");
		    }
			
     if (sum_grd.isVisible()){
    	 Policysumchange acrs2=new Policysumchange();
    	 //acrs2.setP0t23("0");
         //acrs2.setP1t23(currentSum.getP1t23());
         //acrs2.setP2t23(currentSum.getP2t23());
         acrs2.setP3t34(currentSum.getP3t34());
         acrs2.setP5t34(currentSum.getP5t34());
         acrs2.setP6t34(currentSum.getP6t34());
         acrs2.setP7t34(currentSum.getP7t34());
         acrs2.setP8t34(currentSum.getP8t34());
         acrs2.setP9t34(currentSum.getP9t34());
         acrs2.setP10t34(currentSum.getP10t34());
         acrs2.setP11t34(currentSum.getP11t34());
         acrs2.setP12t34(currentSum.getP12t34());
         //acrs2.setP12t23((String)session.getAttribute("un"));
         acrs2.setP14t34(currentSum.getP14t34());
         acrs2.setP17t34(otn2); 
          PolicySumChangeResult ars2 = ws.savePolicySumChange(((String)(session.getAttribute("BankINN"))), idn, current.getP3t32(),getPolSumCorrect(acrs2));
         if (ars2.getStatus() == 0) {
       	  refreshModel(_startPageNumber);
       	  alert("Сохранение успешно");
       	  System.out.println("Editing PolSumChange  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
       	  
         } else {
       	  alert("Ошибка при корректировки суммы Полиса!   Error:"+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
       	System.out.println("Ошибка при корректировки суммы Полиса!  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
         }
    } 
     
     
	else if (time_grd.isVisible()){
    	Policytimechange acrt=new Policytimechange();
    	//acrt.setP0t22("0");
        //acrt.setP1t22(current.getP2t21());
        acrt.setP2t33(currentTime.getP2t33());
        acrt.setP3t33(currentTime.getP3t33());
        acrt.setP4t33(currentTime.getP4t33());
        acrt.setP5t33(currentTime.getP5t33());
        acrt.setP6t33(currentTime.getP6t33());
        acrt.setP7t33(currentTime.getP7t33());
        acrt.setP8t33(currentTime.getP8t33());
        acrt.setP9t33(currentTime.getP9t33());
        acrt.setP11t33(currentTime.getP11t33());
        //acrt.setP8t22((String)session.getAttribute("un"));
        //acrt.setP9t22(p9t23.getValue());
        //acrt.setP10t22(p10t23.getValue());
         //acrt.setP12t22((String)session.getAttribute("ufn"));
         PolicyTimeChangeResult ar = ws.savePolicyTimeChange(((String)(session.getAttribute("BankINN"))), idn, current.getP3t32(), getPolTimeCorrect(acrt));
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Сохранение успешно");
      	  System.out.println("корректировка срока Полиса  успешно: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	  
        } else {
      	  alert("Ошибка при корректировки срока Полиса   Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	System.out.println("Ошибка при корректировки срока Полиса: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
        }
	}
     
onClick$btn_back2();

refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", SumchangeGrid,SumchangeGrid.getSelectedItems());
Events.sendEvent(evt);
	} catch(Exception e) {
    e.printStackTrace();
      }
	
	
    }






public void onClick$btn_delete2() {
	try {
		if (!CheckNull.isEmpty(p5t34a.doubleValue())||(Double.parseDouble(summa1)>p5t34a.doubleValue())){
			  otn2=("0");
		    } else if (!CheckNull.isEmpty(p5t34a.doubleValue())||(Double.parseDouble(summa1)<p5t34a.doubleValue())){
		    	otn2=("1");
		    }
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.AccreditivResult acr =  new com.sbs.service.AccreditivResult();
		//alert("test0");
		
		
		 if (sum_grd.isVisible()){
	    	 Policysumchange acrs2=new Policysumchange();
	    	 //acrs2.setP0t23("0");
	         //acrs2.setP1t23(currentSum.getP1t23());
	         //acrs2.setP2t23(currentSum.getP2t23());
	         acrs2.setP3t34(currentSum.getP3t34());
	         acrs2.setP5t34(currentSum.getP5t34());
	         acrs2.setP6t34(currentSum.getP6t34());
	         acrs2.setP7t34(currentSum.getP7t34());
	         acrs2.setP8t34(currentSum.getP8t34());
	         acrs2.setP9t34(currentSum.getP9t34());
	         acrs2.setP10t34(currentSum.getP10t34());
	         acrs2.setP11t34(currentSum.getP11t34());
	         acrs2.setP12t34(currentSum.getP12t34());
	         //acrs2.setP12t23((String)session.getAttribute("un"));
	         acrs2.setP14t34(currentSum.getP14t34());
	         acrs2.setP17t34(otn2); 
	          PolicySumChangeResult ars2 = ws.savePolicySumChange(((String)(session.getAttribute("BankINN"))), idn, current.getP3t32(),getPolSumDel(acrs2));
	         if (ars2.getStatus() == 0) {
	       	  refreshModel(_startPageNumber);
	       	  alert("Запрос на удаление передано в ГО");
	       	  System.out.println("Delete PolSumChange  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
	       	  
	         } else {
	       	  alert("Ошибка при удалении суммы Полиса!   Error:"+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
	       	System.out.println("Ошибка при удалении суммы Полиса!  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
	         }
	    } 
	     
	     
		else if (time_grd.isVisible()){
	    	Policytimechange acrt=new Policytimechange();
	    	//acrt.setP0t22("0");
	        //acrt.setP1t22(current.getP2t21());
	        acrt.setP2t33(currentTime.getP2t33());
	        acrt.setP3t33(currentTime.getP3t33());
	        acrt.setP4t33(currentTime.getP4t33());
	        acrt.setP5t33(currentTime.getP5t33());
	        acrt.setP6t33(currentTime.getP6t33());
	        acrt.setP7t33(currentTime.getP7t33());
	        acrt.setP8t33(currentTime.getP8t33());
	        acrt.setP9t33(currentTime.getP9t33());
	        acrt.setP11t33(currentTime.getP11t33());
	        //acrt.setP8t22((String)session.getAttribute("un"));
	        //acrt.setP9t22(p9t23.getValue());
	        //acrt.setP10t22(p10t23.getValue());
	         //acrt.setP12t22((String)session.getAttribute("ufn"));
	         PolicyTimeChangeResult ar = ws.savePolicyTimeChange(((String)(session.getAttribute("BankINN"))), idn, current.getP3t32(), getPolTimeDel(acrt));
	        if (ar.getStatus() == 0) {
	      	  refreshModel(_startPageNumber);
	      	  alert("Запрос на удаление передано в ГО");
	      	  System.out.println("удаление срока Полиса  успешно: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
	      	  
	        } else {
	      	  alert("Ошибка при удалении срока Полиса   Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
	      	System.out.println("Ошибка при удалении срока Полиса: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
	        }
		}
	     
	onClick$btn_back2();

	refreshModel(_startPageNumber);
	SelectEvent evt = new SelectEvent("onSelect", SumchangeGrid,SumchangeGrid.getSelectedItems());
	Events.sendEvent(evt);
		} catch(Exception e) {
	    e.printStackTrace();
	      }
		
		
	    }






public void onClick$btn_delete() {
	try {
		
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.PolicyResult acr =  new com.sbs.service.PolicyResult();
		//alert("test0");
		
		
     if (btn_delete.isVisible()){
    	 current.setP1t32(idn);
         //current.setP0t32("2");
         current.setP2t32(p2t32.getValue());
         current.setP3t32(p3t32.getValue());
         current.setP4t32(p4t32.getValue());
         current.setP5t32(p5t32.getValue());
         current.setP6t32(p6t32.getValue());
         current.setP7t32(p7t321.doubleValue());
         current.setP8t32(p8t322.doubleValue());
         current.setP9t32(p9t32.doubleValue());
         current.setP10t32(p10t32.doubleValue());
         current.setP11t32(p11t32.getValue());
         current.setP12t32(p12t32.getValue());
         current.setP13t32((String)session.getAttribute("un"));
        
        
            PolicyResult ar = ws.savePolicy(((String)(session.getAttribute("BankINN"))), idn , delPoli(current));
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Запрос на удаление передано в ГО");
      	System.out.println("удалении полиса успешно!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	  
        } else {
      	  alert("Ошибка при удалении полиса-1!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	  System.out.println("Ошибка при удалении полиса-1!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
        }
			
	}    
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
	} catch(Exception e) {
    e.printStackTrace();}
    }


public void onClick$btn_save() {
try{
	final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
	com.sbs.service.Policy poli =  new com.sbs.service.Policy();
	
	if(addgrd.isVisible()){
            
			PolicyResult pol = ws.savePolicy(((String)(session.getAttribute("BankINN"))), idn , getPolAdd(new Policy(
            ap1t32.getValue(),
            ap2t32.getValue(),
            ap3t32.getValue(),
            ap5t32.getValue(),
            ap6t32.getValue(),
            ap7t32.doubleValue(),
            ap8t322.doubleValue(),
            ap9t32.doubleValue(),
            ap10t32.doubleValue(),
            ap11t32.getValue(),
            ap12t32.getValue()
            )));
      
       	 if (pol.getStatus() == 0) {
	      	  refreshModel(_startPageNumber);
	      	  alert("Сохранение успешно");
	      	System.out.println("Good!!! Save New policy; Status:"+pol.getStatus()+"; GTKid:" +pol.getGtkId()+ "; Text:" +pol.getErrorMsg());
	        } else {
	      	  alert("Error save New policy; Status:"+pol.getStatus()+"; GTKid:" +pol.getGtkId()+ "; Text:" +pol.getErrorMsg());
	      	System.out.println("Error save New policy; Status:"+pol.getStatus()+"; GTKid:" +pol.getGtkId()+ "; Text:" +pol.getErrorMsg());
	      	  ISLogger.getLogger().error(" in save New policy:"+pol.getStatus()+"; GTKid:" +pol.getGtkId()+ "; Text:" +pol.getErrorMsg());
        
	    CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
	        }
       	 
	 } else if (pol_sum.isVisible()){
	    	Policysumchange acrs=new Policysumchange();
	    	//acrs.setP0t34("0");
	        //acrs.setP1t34(current.getP2t21());
	        //acrs.setP2t34(p2t21.getValue());
	        acrs.setP3t34(current.getP7t32());
	        acrs.setP5t34(p5t34.doubleValue());
	        acrs.setP6t34(p8t322.doubleValue());
	        acrs.setP7t34(p7t34.doubleValue());
	        acrs.setP8t34(p8t34.doubleValue());
	        acrs.setP9t34(p9t34.getValue());
	        acrs.setP10t34(p10t34.getValue());
	        acrs.setP11t34(p11t34.getValue());
	        acrs.setP14t34(p14t34.getValue());
	        //acrs.setP15t34(otn2); 
	         PolicySumChangeResult psch = ws.savePolicySumChange(((String)(session.getAttribute("BankINN"))), idn, current.getP3t32(),getPolSum(acrs));
	        if (psch.getStatus() == 0) {
	      	  refreshModel(_startPageNumber);
	      	  alert("Сохранение успешно");
	      	
	      	  System.out.println("Save PolicySumChange  Status: "+psch.getStatus()+";  GTKid:" +psch.getGtkId()+ ";  Text:" +psch.getErrorMsg());
	      	  
	        } else {
	      	  alert("Ошибка при сохранении суммы полиса!   Error:"+psch.getStatus()+";  GTKid:" +psch.getGtkId()+ ";  Text:" +psch.getErrorMsg());
	      	System.out.println("Ошибка при сохранении суммы Полиса!  Status: "+psch.getStatus()+";  GTKid:" +psch.getGtkId()+ ";  Text:" +psch.getErrorMsg());
	        }
       	 
	 } else if (pol_time.isVisible()){
	    	Policytimechange acrs=new Policytimechange();
	    	//acrs.setP0t34("0");
	        //acrs.setP1t34(current.getP2t21());
	        //acrs.setP2t34(p2t21.getValue());
	        acrs.setP4t33(p4t33.getValue());
	        acrs.setP5t33(p5t33.getValue());
	        acrs.setP6t33(p6t33.getValue());
	        acrs.setP7t33(p7t33.getValue());
	        acrs.setP8t33(p8t33.getValue());
	        acrs.setP11t33(p11t33.getValue());
	        PolicyTimeChangeResult ptch = ws.savePolicyTimeChange(((String)(session.getAttribute("BankINN"))), idn, current.getP3t32(),getPolTime(acrs));
	        if (ptch.getStatus() == 0) {
	      	  refreshModel(_startPageNumber);
	      	  alert("Сохранение успешно");
	      	  System.out.println("Save PolicyTimeChange  Status: "+ptch.getStatus()+";  GTKid:" +ptch.getGtkId()+ ";  Text:" +ptch.getErrorMsg());
	      	  
	        } else {
	      	  alert("Ошибка при сохранении даты полиса!   Error:"+ptch.getStatus()+";  GTKid:" +ptch.getGtkId()+ ";  Text:" +ptch.getErrorMsg());
	      	System.out.println("Ошибка при сохранении даты Полиса!  Status: "+ptch.getStatus()+";  GTKid:" +ptch.getGtkId()+ ";  Text:" +ptch.getErrorMsg());
	        }
    	  	 
       	 
       	 
    } else if(fgrd.isVisible()){
        filter = new PolicyFilter();
        
          //Long.parseLong(fid.getValue());
          filter.setP1t32(fp1t32.getValue());
          filter.setP0t32(fp0t32.getValue());
          filter.setP2t32(fp2t32.getValue());
          filter.setP3t32(fp3t32.getValue());
          filter.setP4t32(fp4t32.getValue());
          filter.setP5t32(fp5t32.getValue());
          filter.setP6t32(fp6t32.getValue());
          filter.setP7t32(fp7t32.doubleValue());
          filter.setP8t32(fp8t32.doubleValue());
          filter.setP9t32(fp9t32.doubleValue());
          filter.setP10t32(fp10t32.doubleValue());
          filter.setP11t32(fp11t32.getValue());
          filter.setP12t32(fp12t32.getValue());
          

    }else{
        
          //Long.parseLong(id.getValue());
          current.setP1t32(current.getP1t32());
          //current.setP0t32("1");
          current.setP2t32(p2t32.getValue());
          current.setP3t32(p3t32.getValue());
          current.setP4t32(p4t32.getValue());
          current.setP5t32(p5t32.getValue());
          current.setP6t32(p6t32.getValue());
          current.setP7t32(p7t321.doubleValue());
          current.setP8t32(p8t322.doubleValue());
          current.setP9t32(p9t32.doubleValue());
          current.setP10t32(p10t32.doubleValue());
          current.setP11t32(p11t32.getValue());
          current.setP12t32(p12t32.getValue());
          //PolicyService.update(current);
          PolicyResult ar = ws.savePolicy(((String)(session.getAttribute("BankINN"))), current.getP1t32() , getPoli(current));
          if (ar.getStatus() == 0) {
        	  refreshModel(_startPageNumber);
        	  alert("Сохранение успешно");
        	  
          } else {
        	  alert("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
          }
          
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
            filter = new PolicyFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}
private com.sbs.service.Policy getPoli(Policy acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.Policy res = new com.sbs.service.Policy();
	  res.setP0T32(1);
	  res.setP2T32(acr.getP2t32());
	  res.setP3T32(acr.getP3t32());
	  if (!CheckNull.isEmpty(acr.getP4t32())) {
		  res.setP4T32(acr.getP4t32());
		  }
	  cal.setTime(df.parse(df.format(acr.getP5t32())));
	  res.setP5T32(cal);
	  res.setP6T32(acr.getP6t32());
	  res.setP7T32(acr.getP7t32());
	  res.setP8T32(acr.getP8t32());
	  res.setP9T32(acr.getP9t32());
	  res.setP10T32(acr.getP10t32());
	  if (!CheckNull.isEmpty(acr.getP11t32())) {
		  cal2.setTime(df.parse(df.format(acr.getP11t32())));
		  res.setP11T32(cal2);
		  }
		  if (!CheckNull.isEmpty(acr.getP12t32())) {
		  res.setP12T32(Integer.parseInt(acr.getP12t32()));
		  }
	  res.setP13T32((String)session.getAttribute("un"));
	  res.setP16T32(Short.parseShort(otn)); 
	  	  
	  return res;
	  }


private com.sbs.service.Policy getPolAdd(Policy acr) throws Exception
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.Policy res = new com.sbs.service.Policy();
	  
	  res.setP0T32(0);
	  res.setP2T32(acr.getP2t32());
	  res.setP3T32(acr.getP3t32());
	  
	  if (!CheckNull.isEmpty(acr.getP4t32())) {
	  res.setP4T32(acr.getP4t32());
	  }
	  
	  cal.setTime(df.parse(df.format(acr.getP5t32())));
	  res.setP5T32(cal);
	  
	  res.setP6T32(acr.getP6t32());
	  res.setP7T32(acr.getP7t32());
	  res.setP8T32(acr.getP8t32());
	  res.setP9T32(acr.getP9t32());
	  res.setP10T32(acr.getP10t32());
	  
	  if (!CheckNull.isEmpty(acr.getP11t32())) {
	  cal2.setTime(df.parse(df.format(acr.getP11t32())));
	  res.setP11T32(cal2);
	  }
	  
	  if (!CheckNull.isEmpty(acr.getP12t32()))
	  {
		  res.setP12T32(Integer.parseInt(acr.getP12t32()));
	  }
	  
	  res.setP13T32((String)session.getAttribute("un"));
	  res.setP16T32(Short.parseShort(aotn));
	  return res;
	  }

private com.sbs.service.PolicySumChange getPolSum(Policysumchange acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.PolicySumChange res = new com.sbs.service.PolicySumChange();
	  res.setP0T34(0);
	  res.setP3T34(acr.getP3t34());
	  res.setP5T34(acr.getP5t34());
	  res.setP6T34(acr.getP6t34());
	  res.setP7T34(acr.getP7t34());
	  res.setP8T34(acr.getP8t34());
	  if (!CheckNull.isEmpty(acr.getP9t34())) {
	  res.setP9T34(Short.parseShort(acr.getP9t34()));
	  }
	  if (!CheckNull.isEmpty(acr.getP10t34())) {
		  res.setP10T34(acr.getP10t34());
		  }
	  if (!CheckNull.isEmpty(acr.getP11t34())) {
		  res.setP11T34(acr.getP11t34());
		  }
	  res.setP13T34((String)session.getAttribute("un"));    
	  cal.setTime(df.parse(df.format(acr.getP14t34())));
	  res.setP14T34(cal);
	  res.setP17T34(Short.parseShort(otn2));
	  return res;
	  }

private com.sbs.service.PolicySumChange getPolSumCorrect(Policysumchange acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.PolicySumChange res = new com.sbs.service.PolicySumChange();
	  res.setP0T34(1);
	  res.setP3T34(acr.getP3t34());
	  res.setP5T34(acr.getP5t34());
	  res.setP6T34(acr.getP6t34());
	  res.setP7T34(acr.getP7t34());
	  res.setP8T34(acr.getP8t34());
	  if (!CheckNull.isEmpty(acr.getP9t34())) {
	  res.setP9T34(Short.parseShort(acr.getP9t34()));
	  }
	  if (!CheckNull.isEmpty(acr.getP10t34())) {
		  res.setP10T34(acr.getP10t34());
		  }
	  if (!CheckNull.isEmpty(acr.getP11t34())) {
		  res.setP11T34(acr.getP11t34());
		  }
	  res.setP12T34(Integer.parseInt(acr.getP12t34()));
	  res.setP13T34((String)session.getAttribute("un"));    
	  cal.setTime(df.parse(df.format(acr.getP14t34())));
	  res.setP14T34(cal);
	  res.setP17T34(Short.parseShort(otn2));
	  return res;
	  }

private com.sbs.service.PolicySumChange getPolSumDel(Policysumchange acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.PolicySumChange res = new com.sbs.service.PolicySumChange();
	  res.setP0T34(2);
	  res.setP3T34(acr.getP3t34());
	  res.setP5T34(acr.getP5t34());
	  res.setP6T34(acr.getP6t34());
	  res.setP7T34(acr.getP7t34());
	  res.setP8T34(acr.getP8t34());
	  if (!CheckNull.isEmpty(acr.getP9t34())) {
	  res.setP9T34(Short.parseShort(acr.getP9t34()));
	  }
	  if (!CheckNull.isEmpty(acr.getP10t34())) {
		  res.setP10T34(acr.getP10t34());
		  }
	  if (!CheckNull.isEmpty(acr.getP11t34())) {
		  res.setP11T34(acr.getP11t34());
		  }
	  res.setP12T34(Integer.parseInt(acr.getP12t34()));
	  res.setP13T34((String)session.getAttribute("un"));    
	  cal.setTime(df.parse(df.format(acr.getP14t34())));
	  res.setP14T34(cal);
	  res.setP17T34(Short.parseShort(otn2));
	  return res;
	  }

private com.sbs.service.PolicyTimeChange getPolTimeCorrect(Policytimechange acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.PolicyTimeChange res = new com.sbs.service.PolicyTimeChange();
	  res.setP0T33(1);
	  cal.setTime(df.parse(df.format(acr.getP4t33())));
	  res.setP4T33(cal);
	  if (!CheckNull.isEmpty(acr.getP5t33())) {
	  res.setP5T33(Integer.parseInt(acr.getP5t33()));
	  }
	  if (!CheckNull.isEmpty(acr.getP6t33())) {
	  res.setP6T33(Short.parseShort(acr.getP6t33()));
	  }
	  if (!CheckNull.isEmpty(acr.getP7t33())) {
		  res.setP7T33(acr.getP7t33());
		  }
	  if (!CheckNull.isEmpty(acr.getP8t33())) {
		  res.setP8T33(acr.getP8t33());
		  }
	  res.setP9T33(Integer.parseInt(acr.getP9t33()));
	  res.setP10T33((String)session.getAttribute("un"));    
	  cal2.setTime(df.parse(df.format(acr.getP11t33())));
	  res.setP11T33(cal2);
	  return res;
	  }

private com.sbs.service.PolicyTimeChange getPolTimeDel(Policytimechange acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.PolicyTimeChange res = new com.sbs.service.PolicyTimeChange();
	  res.setP0T33(2);
	  cal.setTime(df.parse(df.format(acr.getP4t33())));
	  res.setP4T33(cal);
	  if (!CheckNull.isEmpty(acr.getP5t33())) {
	  res.setP5T33(Integer.parseInt(acr.getP5t33()));
	  }
	  if (!CheckNull.isEmpty(acr.getP6t33())) {
	  res.setP6T33(Short.parseShort(acr.getP6t33()));
	  }
	  if (!CheckNull.isEmpty(acr.getP7t33())) {
		  res.setP7T33(acr.getP7t33());
		  }
	  if (!CheckNull.isEmpty(acr.getP8t33())) {
		  res.setP8T33(acr.getP8t33());
		  }
	  res.setP9T33(Integer.parseInt(acr.getP9t33()));
	  res.setP10T33((String)session.getAttribute("un"));    
	  cal2.setTime(df.parse(df.format(acr.getP11t33())));
	  res.setP11T33(cal2);
	  return res;
	  }

private com.sbs.service.PolicyTimeChange getPolTime(Policytimechange acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.PolicyTimeChange res = new com.sbs.service.PolicyTimeChange();
	  res.setP0T33(0);
	  cal.setTime(df.parse(df.format(acr.getP4t33())));
	  res.setP4T33(cal);
	  if (!CheckNull.isEmpty(acr.getP5t33())) {
	  res.setP5T33(Integer.parseInt(acr.getP5t33()));
	  }
	  if (!CheckNull.isEmpty(acr.getP6t33())) {
	  res.setP6T33(Short.parseShort(acr.getP6t33()));
	  }
	  if (!CheckNull.isEmpty(acr.getP7t33())) {
		  res.setP7T33(acr.getP7t33());
		  }
	  if (!CheckNull.isEmpty(acr.getP8t33())) {
		  res.setP8T33(acr.getP8t33());
		  }
	  res.setP10T33((String)session.getAttribute("un"));    
	  cal2.setTime(df.parse(df.format(acr.getP11t33())));
	  res.setP11T33(cal2);
	  return res;
	  }

private com.sbs.service.Policy delPoli(Policy acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.Policy res = new com.sbs.service.Policy();
	  res.setP0T32(2);
	  res.setP2T32(acr.getP2t32());
	  res.setP3T32(acr.getP3t32());
	  if (!CheckNull.isEmpty(acr.getP4t32())) {
		  res.setP4T32(acr.getP4t32());
		  }
	  cal.setTime(df.parse(df.format(acr.getP5t32())));
	  res.setP5T32(cal);
	  res.setP6T32(acr.getP6t32());
	  res.setP7T32(acr.getP7t32());
	  res.setP8T32(acr.getP8t32());
	  res.setP9T32(acr.getP9t32());
	  res.setP10T32(acr.getP10t32());
	  if (!CheckNull.isEmpty(acr.getP11t32())) {
		  cal2.setTime(df.parse(df.format(acr.getP11t32())));
		  res.setP11T32(cal2);
		  }
		  if (!CheckNull.isEmpty(acr.getP12t32())||(acr.getP12t32().equals("0"))) {
		  res.setP12T32(Integer.parseInt(acr.getP12t32()));
		  }
	  res.setP13T32((String)session.getAttribute("un"));
	  res.setP16T32(Short.parseShort(otn)); 
	  return res;
	  }

public void onSelect$p9t34() {
	if (p9t34.getValue().equalsIgnoreCase("4")) {
		row_p10t34.setVisible(true);
		row_p11t34.setVisible(false);
	} else {
		row_p10t34.setVisible(false);
		row_p11t34.setVisible(true);
	}
	p10t34.setSelectedIndex(-1);
	p11t34.setValue("");
}
public void onSelect$p6t33() {
	if (p6t33.getValue().equalsIgnoreCase("4")) {
		row_p7t33.setVisible(true);
		row_p8t33.setVisible(false);
	} else {
		row_p7t33.setVisible(false);
		row_p8t33.setVisible(true);
	}
	p7t33.setSelectedIndex(-1);
	p8t33.setValue("");
}
public void onChange$p4t33() {
	if (!CheckNull.isEmpty(p4t33.getValue())) {
		pol_date4_33.setVisible(false);
	} else {
		pol_date4_33.setVisible(true);
	}
}

public void onChange$p5t33() {
	if (!CheckNull.isEmpty(p5t33.getValue())) {
		pol_date3_33.setVisible(false);
	} else {
		pol_date3_33.setVisible(true);
	}
}
/////////////////////////////////CURRENCY////////////////////////////////
private void setCurrent() {
	if (current != null) {
		
		currenciesg = com.is.tf.contract.ContractService.getMyCurrP(idn, df.format(new java.util.Date()), alias);
		System.out.println(currenciesg.size());
		ap8t321.setModel((new ListModelList(currenciesg)));
		ap8t323.setModel((new ListModelList(currenciesg)));
		p8t323.setModel((new ListModelList(currenciesg)));
		p8t321.setModel((new ListModelList(currenciesg)));
		p6t343.setModel((new ListModelList(currenciesg)));
		p6t341.setModel((new ListModelList(currenciesg)));
		if (currenciesg.size() > 0){
			if ((current.getP6t32().equalsIgnoreCase("000")?"860":current.getP6t32()).equalsIgnoreCase(val2.equalsIgnoreCase("000")?"860":val2)
				&& (CheckNull.isEmpty(val2) || (current.getP6t32().equalsIgnoreCase("000")?"860":current.getP6t32()).equalsIgnoreCase(val1.equalsIgnoreCase("000")?"860":val1))
			){
				kur.setVisible(false);
			} else {       
				kur.setVisible(true);
			
			}
		}
		if (CheckNull.isEmpty(val2)) {
			sum_gar2.setVisible(false);
		} else {
			sum_gar2.setVisible(true);
		}
		
		 if (!CheckNull.isEmpty(current.getP7t32())&&(Double.parseDouble(summa1)>current.getP7t32())){
		    	otn=("0");
		    } else if (!CheckNull.isEmpty(current.getP7t32())&&(Double.parseDouble(summa1)<current.getP7t32())){
		    	otn=("1");
		    	System.out.println("current summa1="+summa1+"  otn="+otn+"   p7t32.doubleValue()="+p7t32.doubleValue());
		    }
		 
		    SumchangeGrid.setModel(new BindingListModelList(current.getSumchanges(), true));
		    TimechangeGrid.setModel(new BindingListModelList(current.getTimechanges(), true));
		
		    if (!CheckNull.isEmpty(currentSum.getP9t34())){
		    	p9t34a.setSelecteditem(currentSum.getP9t34());
		    }
		    if ((!CheckNull.isEmpty(currentSum.getP9t34())||(p9t34a.getValue().equals("5")))){
		    	row_p10t34a.setVisible(false);
		    	row_p11t34a.setVisible(true);
		    } else if ((!CheckNull.isEmpty(currentSum.getP9t34())||(p9t34a.getValue().equals("4")))){
		    	row_p10t34a.setVisible(true);
		    	row_p11t34a.setVisible(false);
		    }
		    if (!CheckNull.isEmpty(currentTime.getP6t33())){
		    	p6t33a.setValue(currentTime.getP6t33());
		    }
		    if ((!CheckNull.isEmpty(currentTime.getP6t33())||(p6t33a.getValue().equals("5")))){
		    	row_p7t33a.setVisible(false);
		    	row_p8t33a.setVisible(true);
		    } else if ((!CheckNull.isEmpty(currentTime.getP6t33())||(p6t33a.getValue().equals("4")))){
		    	row_p7t33a.setVisible(true);
		    	row_p8t33a.setVisible(false);
		    }
		    
		    if (!CheckNull.isEmpty(current.getP11t32())&&(current.getP11t32()!=null)){
		    	pol_date1_33.setVisible(true);
		    } else {
		    	pol_date1_33.setVisible(false);
		    }
		    if ((current.getP12t32()!=null)&&(current.getP12t32()!="")){
		    	pol_date2_33.setVisible(true);
		    } else {
		    	pol_date2_33.setVisible(false);
		    }
		   
			
	}
}

public void onChange$p4t33a() {
	if (!CheckNull.isEmpty(p4t33a.getValue())){
		pol_date3_33a.setVisible(true);
		pol_date4_33a.setVisible(false);
    } else {
    	pol_date3_33a.setVisible(false);
		pol_date4_33a.setVisible(true);
    }
}
public void onChange$p5t33a() {
	if (!CheckNull.isEmpty(p5t33a.getValue())){
		pol_date3_33a.setVisible(false);
		pol_date4_33a.setVisible(true);
    } else {
    	pol_date3_33a.setVisible(true);
		pol_date4_33a.setVisible(false);
    }
}

public void onBlur$p11t32() {
	onChange$p11t32();
}
public void onOk$p11t32() {
	onChange$p11t32();
}
public void onChange$p11t32() {
	if (!CheckNull.isEmpty(current.getP11t32())) {
		pol_date2.setVisible(false);
		p12t32.setValue("");
	} else {
		
		pol_date2.setVisible(true);
		p11t32.setValue(null);
	}
}
	
public void onChange$p5t34() {
	countCourse(false);
	if (Double.parseDouble(summa1)>p5t34.doubleValue()){
		  otn2=("0");
	    } else if (Double.parseDouble(summa1)<p5t34.doubleValue()){
	    	otn2=("1");
	    }
	 //alert("summa1="+summa1+"  aotn="+aotn+"   p5t34.doubleValue()="+p5t34.doubleValue());	
}
	

public void onInitRenderLater$p8t323() 
{
	p8t323.setSelectedIndex(currenciesg.size()-1);
	p6t341.setSelecteditem(p8t321.getValue());
	p6t343.setSelecteditem(p8t323.getValue());
	countCourse(false);
}

public void onChange$p7t32() {
	if (Double.parseDouble(summa1)>p7t32.doubleValue()){
		  otn=("0");
	    } else if (Double.parseDouble(summa1)<p7t32.doubleValue()){
	    	otn=("1");
	    }
	
	
	countCourse(false);
}

public void onChange$ap8t322() {
	if (val1.equalsIgnoreCase(ap6t32.getValue())){
		asumm1.setValue("Сумма="+ap7t32.doubleValue()/ap8t322.doubleValue());
	} else {asumm1.setValue("Cумма="+ap7t32.doubleValue());}
    
}
	


public void onChange$p9t32() {
	countCourse(false);
}
/*
public void onChange$p10t32() {
	countCourse(false);
}
*/
public void onSelect$p5t32() {
	countCourse(true);
}

public void onSelect$p8t321() { 
	countCourse(true);
}

public void onClick$btn_recount() {
	countCourse(true);
}

private void countCourse(Boolean setp8t322) {
	try {
		if(!CheckNull.isEmpty(p8t323.getValue()) && !CheckNull.isEmpty(p8t321.getValue())) {
			System.out.println("***"+p8t321.getValue()+" - "+p8t323.getValue());
			if (setp8t322) {
				p8t322.setValue(""+(p8t321.getCourse()/p8t323.getCourse()));
				current.setP8t32((p8t321.getCourse()/p8t323.getCourse()));
			}
			cbcourse.setValue("По курсу ЦБ: "+(p8t321.getCourse()/p8t323.getCourse()));
			Boolean bool = false;
			Double db = null;
			if (p6t32.getValue().equalsIgnoreCase(p8t321.getValue())) {
				if (val1.equalsIgnoreCase(p8t321.getValue())) {
					db = (p9t32.doubleValue() + (p10t32.doubleValue()/p8t322.doubleValue()));
					bool = (p7t321.doubleValue() == db);
					checksum.setChecked(bool);
					summ1.setValue("Сумма="+(p8t322.doubleValue()*p7t321.doubleValue()));
					checksum.setLabel((bool?"Сумма полиса полность соответствует указанному курсу!":"Сумма полиса не соответствует указанному курсу!")+"("+db+")");
				} else if (val2.equalsIgnoreCase(p8t321.getValue())) {
					db = (p10t32.doubleValue() + (p9t32.doubleValue()/p8t322.doubleValue()));
					bool = (p7t321.doubleValue() == db);
					checksum.setChecked(bool);
					checksum.setLabel((bool?"Сумма полиса полность соответствует указанному курсу!":"Сумма полиса не соответствует указанному курсу!")+"("+db+")");
				} else {
					checksum.setChecked(false);
					checksum.setLabel(("Сумма полиса не соответствует указанному курсу!"));
				}
			} else if (p6t32.getValue().equalsIgnoreCase(p8t323.getValue())) {
				if (val1.equalsIgnoreCase(p8t323.getValue())) {
					db = (p9t32.doubleValue() + (p10t32.doubleValue()*p8t322.doubleValue()));
					bool = (p7t321.doubleValue() == db);
					checksum.setChecked(bool);
					checksum.setLabel((bool?"Сумма полиса полность соответствует указанному курсу!":"Сумма полиса не соответствует указанному курсу!")+"("+db+")");
				} else if (val2.equalsIgnoreCase(p8t323.getValue())) {
					db = (p10t32.doubleValue() + (p9t32.doubleValue()*p8t322.doubleValue()));
					bool = (p7t321.doubleValue() == db);
					checksum.setChecked(bool);
					checksum.setLabel((bool?"Сумма полиса полность соответствует указанному курсу!":"Сумма полиса не соответствует указанному курсу!")+"("+db+")");
				} else {
					checksum.setChecked(false);
					checksum.setLabel(("Сумма полиса не соответствует указанному курсу!"));
				}
			} else {
				checksum.setChecked(false);
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

private void setCurrentAdd() {        //////////////////////////////////////////
	CheckNull.clearForm(addgrd);
	ap5t32.setValue(new java.util.Date());
	ap6t32.setSelecteditem(val1);
	if (ap5t32.getValue()!=null&&ap6t32.getValue()!=null){
	    currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
        ap6t32.setModel((new ListModelList(currencies)));
	currenciesg = com.is.tf.contract.ContractService.getMyCurrA(idn, df.format(ap5t32.getValue()), alias);
	ap8t321.setModel((new ListModelList(currenciesg)));
	ap8t323.setModel((new ListModelList(currenciesg)));
	if (currenciesg.size() > 0){
		if (CheckNull.isEmpty(val2)
			|| (val1.equalsIgnoreCase("000")?"860":val1).equalsIgnoreCase(val2.equalsIgnoreCase("000")?"860":val2)){
			
			akur.setVisible(false);
		} else {       
			akur.setVisible(true);
			
		}
	}
	if (CheckNull.isEmpty(val2)) {
		asum_gar2.setVisible(false);
	} else {
		asum_gar2.setVisible(true);
		}
	
/*
	if (!CheckNull.isEmpty(current.getP11t32())) {
		agar_date2.setVisible(false);
	} else {
		agar_date2.setVisible(true);
	}
	*/
	}
	}
		
public void onBlur$ap11t32() {
	onChange$ap11t32();
}
public void onOk$ap11t32() {
	onChange$ap11t32();
}
public void onChange$ap11t32() {
	if (!CheckNull.isEmpty(ap11t32.getValue())) {
		agar_date2.setVisible(false);
	} else {
		agar_date2.setVisible(true);
	}
}

public void onInitRenderLater$ap8t323() {
	ap8t323.setSelectedIndex(currenciesg.size()-1);
	acountCourse(false);
}


public void onChange$ap7t32() {
	if (Double.parseDouble(summa1)>ap7t32.doubleValue()){
		  aotn=("0");
	    } else if (Double.parseDouble(summa1)<ap7t32.doubleValue()){
	    	aotn=("1");
	    }
	
	acountCourse(false);
}

public void onChange$ap9t32() {
	acountCourse(false);
}

public void onChange$ap10t32() {
	acountCourse(false);
}

public void onSelect$ap6t32() {
	acountCourse(true);
}

public void onSelect$ap8t321() {
	acountCourse(true);
}

public void onClick$abtn_recount() {
	acountCourse(true);
}

private void acountCourse(Boolean setap8t322) {
	try {
		if(!CheckNull.isEmpty(ap8t323.getValue()) && !CheckNull.isEmpty(ap8t321.getValue())) {
			System.out.println("***"+ap8t321.getValue()+" - "+ap8t323.getValue());
			if (setap8t322) {
				ap8t322.setValue(""+(ap8t321.getCourse()/ap8t323.getCourse()));
				current.setP8t32((ap8t321.getCourse()/ap8t323.getCourse()));
			}
			acbcourse.setValue("По курсу ЦБ: "+(ap8t321.getCourse()/ap8t323.getCourse()));
			Boolean bool = false;
			Double db = null;
			if (ap6t32.getValue().equalsIgnoreCase(ap8t321.getValue())) {
				if (val1.equalsIgnoreCase(ap8t321.getValue())) {
					db = (ap9t32.doubleValue() + (ap10t32.doubleValue()/ap8t322.doubleValue()));
					bool = (ap7t32.doubleValue() == db);
					achecksum.setChecked(bool);
					asumm1.setValue("Cумма="+"("+db+")");
					achecksum.setLabel((bool?"Сумма полность соответствует указанному курсу!":"Сумма не соответствует указанному курсу!")+"("+db+")");
				} else if (val2.equalsIgnoreCase(ap8t321.getValue())) {
					db = (ap10t32.doubleValue() + (ap9t32.doubleValue()/ap8t322.doubleValue()));
					bool = (ap7t32.doubleValue() == db);
					achecksum.setChecked(bool);
					asumm1.setValue("Cумма="+"("+db+")");
					achecksum.setLabel((bool?"Сумма  полность соответствует указанному курсу!":"Сумма  не соответствует указанному курсу!")+"("+db+")");
				} else {
					achecksum.setChecked(false);
					asumm1.setValue("Cумма="+"("+db+")");
					achecksum.setLabel(("Сумма  не соответствует указанному курсу!"));
				}
			} else if (ap6t32.getValue().equalsIgnoreCase(ap8t323.getValue())) {
				if (val1.equalsIgnoreCase(ap8t323.getValue())) {
					db = (ap9t32.doubleValue() + (ap10t32.doubleValue()*ap8t322.doubleValue()));
					bool = (ap7t32.doubleValue() == db);
					achecksum.setChecked(bool);
					achecksum.setLabel((bool?"Сумма  полность соответствует указанному курсу!":"Сумма  не соответствует указанному курсу!")+"("+db+")");
				} else if (val2.equalsIgnoreCase(ap8t323.getValue())) {
					db = (ap10t32.doubleValue() + (ap9t32.doubleValue()*ap8t322.doubleValue()));
					bool = (ap7t32.doubleValue() == db);
					achecksum.setChecked(bool);
					achecksum.setLabel((bool?"Сумма  полность соответствует указанному курсу!":"Сумма  не соответствует указанному курсу!")+"("+db+")");
				} else {
					achecksum.setChecked(false);
					achecksum.setLabel(("Сумма  не соответствует указанному курсу!"));
				}
			} else {
				achecksum.setChecked(false);
			}
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void onBlur$ap10t19() {
	onChange$ap11t32();
}
public void onOk$ap10t19() {
	onChange$ap11t32();
}
public void onChange$ap4t33() {
	if (!CheckNull.isEmpty(p4t33.getValue())) {
		pol_date4_33.setVisible(false);
	} else {
		pol_date4_33.setVisible(true);
	}
}

public void onChange$p5t19() {
	if (!CheckNull.isEmpty(p5t33.getValue())) {
		pol_date3_33.setVisible(false);
	} else {
		pol_date3_33.setVisible(true);
	}
}

private Window contractmain = null; 
public void onClick$btn_confirm() {
	sendConfirm(1, current.getP3t32(), currentSum.getP12t34()+"", currentSum);
}

public void onClick$btn_reject() {
	sendConfirm(0, current.getP3t32(), currentSum.getP12t34()+"", currentSum);
}

public void onClick$btn_confirm2() {
	sendConfirm(1, current.getP3t32(), currentTime.getP9t33()+"", currentTime);
}

public void onClick$btn_reject2() {
	sendConfirm(0, current.getP3t32(), currentTime.getP9t33()+"", currentTime);
}
public void onClick$btn_confirmM() {
	sendConfirm(1, current.getP3t32(), "", current);
}

public void onClick$btn_rejectM() {
	sendConfirm(0, current.getP3t32(), "", current);
}

private void sendConfirm(int action, String docnum, String chdocnum, Object obj) {
	if (contractmain==null){
	contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
	
	//contractmain = (Window) session.getAttribute("contractmain");
	}
	HashMap<String,Object> params = new HashMap<String, Object>();
	params.put("inn", ((String)session.getAttribute("BankINN")));
	params.put("idn", idn);
	params.put("action", action+"");
	params.put("docnum", docnum);
	params.put("chdocnum", chdocnum);
	params.put("obj", obj);
	Events.sendEvent("onConfirmDocument", contractmain, params);
}




}
