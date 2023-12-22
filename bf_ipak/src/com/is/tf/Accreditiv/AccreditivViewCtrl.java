package com.is.tf.Accreditiv;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.tf.Accreditivsumchange.Accreditivsumchange;
import com.is.tf.Accreditivtimechange.Accreditivtimechange;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.user.User;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.AccreditivResult;
import com.sbs.service.AccreditivSumChangeResult;
import com.sbs.service.AccreditivTimeChangeResult;
import com.sbs.service.BankServiceProxy;

public class AccreditivViewCtrl extends GenericForwardComposer{
	private Textbox win$tb_username,postsets$tb_host,postsets$tb_from,postsets$tb_un,postsets$tb_pwd;
	private Div frm;
    private Listbox dataGrid,SumchangeGrid,TimechangeGrid;
    private Paging contactPaging;
    private Div grd,sum_div;
    private Grid addgrd,frmgrd,fgrd,acr_sum,acr_time,sum_grd,sum_grd2,time_grd;
    private Toolbarbutton btn_last,btn_confirm33,btn_edit,btn_save2,btn_delete2,btn_add2,btn_back2;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search,btn_delete;
    private Toolbarbutton btn_confirmM,btn_rejectM;
    private Toolbarbutton btn_back,btn_acr,btn_acr1,btn_acr2;
    private Toolbar tb,tb1,tb2;
    private Toolbarbutton btn_refresh;
    
    private Textbox p6t222,p3t222,p10t23a,p12t23a,p16t23a,p100t23a,p1t23a,p3t22,ap9t21,un,p7t22,p8t22,p6t22,p10t23,p1t22,p2t22,p1t23,id,p0t21,p1t21,p2t21,p9t21,p10t21,p14t21,p15t21,p16t21;
    private Textbox aid,ap0t21,ap1t21,ap2t21,ap10t21,ap14t21,ap15t21,ap16t21;
    private Textbox fid,fp0t21,fp1t21,fp2t21,fp9t21,fp10t21,fp11t21,fp12t21,fp13t21,fp14t21,fp15t21,fp16t21;
    private Datebox p11t222,p3t21,ap3t21,fp3t21,p17t21,ap17t21,fp17t21;
    private Row row_p11t21,row_p5t222,row_p6t222,row_p7t23a,row_p9t23a,row_p10t23a,row_ap11t21,row_ap12t21,row_ap13t21,row_p12t21,row_p13t21,agar_date2,asum_gar2,akur,sum_gar2,kur,row_p5t22,row_p6t22,row_p9t23,row_p10t23;
    private RefCBox p5t222,p4t222,p8t23a,p9t23a,ap11t21,ap12t21,ap13t21,p11t21,p12t21,p13t21,p4t22,p8t23,p5t22,p9t23,p2t23,p4t21,ap4t21,fp4t21;
    private Label acbcourse,aconr_val1,aconr_val2,p3t231,conr_val1,conr_val1a,conr_val2,conr_val2a,cbcourse;
    private Decimalbox p5t23a,p6t23a,p4t23a,p7t23,p6t23,p4t23,ap6t212,p6t212,p5t23,p3t23,p5t21,p6t21,p7t21,p8t21,ap5t21,ap6t21,ap7t21,ap8t21,fp5t21,fp6t21,fp7t21,fp8t21;
    private RefCurrencyBox p5t231,p5t232,p6t211,p6t213,ap6t211,ap6t213;
    private Checkbox checksum, achecksum;
    private Paging accreditivPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private List<RefData> currencies = new ArrayList<RefData>();
    private List<RefData> reasons = new ArrayList<RefData>();
    private List<RefData> YesNo = new ArrayList<RefData>();
    private List<RefData> agriments = new ArrayList<RefData>();
    private List<RefCurrencyData> currenciesg = new ArrayList<RefCurrencyData>();
    private boolean _needsTotalSizeUpdate = true;
    private HashMap< String,String> curr_ =null;
    private String alias;
    private String  cont_type,otn,aotn,otn2,otn3,summa1,summa2,idn, val1, val2,cu;
    private long gid,idc,id_contract,aid_contract,fid_contract;
    private int desktopHeight=0;
    private Tab tab_acrsum, tab_acrtime;
    private Include include_arcsum,include_arctime;
    private int itype = 0;
    public AccreditivFilter filter = new AccreditivFilter();
    private Label line1;						
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Accreditiv current = new Accreditiv();
private Accreditivsumchange currentSum = new Accreditivsumchange();
private Accreditivtimechange currentTime = new Accreditivtimechange();

    public AccreditivViewCtrl() {
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
    //alias= (String) session.getAttribute("alias");
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

    curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
    filter.setP1t21(idn);
    filter.setId_contract(idc);
    conr_val2.setValue(curr_.get(val2));
    conr_val1.setValue(curr_.get(val1));
    conr_val2a.setValue(curr_.get(val2));
    conr_val1a.setValue(curr_.get(val1));
    aconr_val1.setValue(curr_.get(val1));
    aconr_val2.setValue(curr_.get(val2));
    
    if (CheckNull.isEmpty(p5t21.doubleValue())&&(Double.parseDouble(summa1)>p5t21.doubleValue())){
    	otn="0";
    } else if (CheckNull.isEmpty(p5t21.doubleValue())&&(Double.parseDouble(summa1)<p5t21.doubleValue())){
    	otn="1";
    }
    if ((cont_type.equals("02"))||(cont_type.equals("05"))){
    	row_ap11t21.setVisible(true);
     } else { 
    	 row_ap11t21.setVisible(false);
     }
   
    SumchangeGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
                    Accreditivsumchange pAccreditivsumchange = (Accreditivsumchange) data;

                    row.setValue(pAccreditivsumchange);
                    
                    row.appendChild(new Listcell(pAccreditivsumchange.getId()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP11t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP0t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP1t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP2t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP3t23()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP4t23()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP5t23()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP6t23()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP7t23()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP8t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP9t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP10t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP12t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP15t23()));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP16t23()+""));
                    row.appendChild(new Listcell(pAccreditivsumchange.getP100t23()));
        }});
    	refreshModel(_startPageNumber);
        TimechangeGrid.setItemRenderer(new ListitemRenderer(){
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception {
                        Accreditivtimechange pAccreditivtimechange = (Accreditivtimechange) data;

                        row.setValue(pAccreditivtimechange);
                        
                        row.appendChild(new Listcell(pAccreditivtimechange.getId()+""));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP7t22()+""));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP0t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP1t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP2t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP3t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP4t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP5t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP6t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP8t22()));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP11t22()+""));
                        row.appendChild(new Listcell(pAccreditivtimechange.getP100t22()));
         }});

            refreshModel(_startPageNumber);

    line1.setValue(Labels.getLabel("accreditiv.p2t21tab"  ).replaceAll("<br>", "\r\n")); 
    line2.setValue(Labels.getLabel("accreditiv.p3t21tab"  ).replaceAll("<br>", "\r\n")); 
    line3.setValue(Labels.getLabel("accreditiv.p5t21tab"  ).replaceAll("<br>", "\r\n")); 
    line4.setValue(Labels.getLabel("accreditiv.p4t21tab"  ).replaceAll("<br>", "\r\n")); 
    line5.setValue(Labels.getLabel("accreditiv.p9t21tab"  ).replaceAll("<br>", "\r\n")); 
    line6.setValue(Labels.getLabel("accreditiv.p10t21tab" ).replaceAll("<br>", "\r\n")); 
    line7.setValue(Labels.getLabel("accreditiv.p17t21tab" ).replaceAll("<br>", "\r\n")); 
    line8.setValue(Labels.getLabel("accreditiv.p100t21tab").replaceAll("<br>", "\r\n"));        
            
    dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Accreditiv pAccreditiv = (Accreditiv) data;
                //Accreditivsumchange pAccreditivsumchange = (Accreditivsumchange) data1;
                //Accreditivtimechange pAccreditivtimechange = (Accreditivtimechange) data2;

                row.setValue(pAccreditiv);
                
                row.appendChild(new Listcell(pAccreditiv.getP2t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP3t21()+""));
                
                //if (!CheckNull.isEmpty(pAccreditiv.getP5t21()))
                	 row.appendChild(new Listcell(pAccreditiv.getP5t21()+""));
                //else row.appendChild(new Listcell(pAccreditivsumchange.getP4t23()+""));
                
                row.appendChild(new Listcell(pAccreditiv.getP4t21()+""));
                
                //if (!CheckNull.isEmpty(pAccreditiv.getP9t21()))
                	 row.appendChild(new Listcell(pAccreditiv.getP9t21()+""));
                //else row.appendChild(new Listcell(pAccreditivtimechange.getP3t22()));
                
                row.appendChild(new Listcell(pAccreditiv.getP10t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP17t21()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pAccreditiv.getP100t21()+""))));
                
                /*row.appendChild(new Listcell(pAccreditiv.getId()+""));
                row.appendChild(new Listcell(pAccreditiv.getP0t21()));
                row.appendChild(new Listcell(pAccreditiv.getP1t21()));
                row.appendChild(new Listcell(pAccreditiv.getP2t21()));
                row.appendChild(new Listcell(pAccreditiv.getP3t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP4t21()));
                row.appendChild(new Listcell(pAccreditiv.getP5t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP6t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP7t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP8t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP9t21()));
                row.appendChild(new Listcell(pAccreditiv.getId_contract()+""));
                row.appendChild(new Listcell(pAccreditiv.getP10t21()));
                row.appendChild(new Listcell(pAccreditiv.getP11t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP12t21()));
                row.appendChild(new Listcell(pAccreditiv.getP13t21()));
                row.appendChild(new Listcell(pAccreditiv.getP14t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP15t21()));
                row.appendChild(new Listcell(pAccreditiv.getP16t21()));
                row.appendChild(new Listcell(pAccreditiv.getP17t21()+""));
                row.appendChild(new Listcell(pAccreditiv.getP100t21()+""));*/
    }});

    currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
   // currencies1 = com.is.tf.contract.ContractService.getMyCurrAcr(idn, gid, alias);
    reasons = ContractService.getReasons("4,5", alias);
    YesNo=ContractService.getYesNo();
    if (agriments!=null){
    agriments = com.is.tf.contract.ContractService.getAgreement(idn, alias);
    p9t23.setModel((new ListModelList(agriments)));
    p5t222.setModel((new ListModelList(agriments)));
    p9t23a.setModel((new ListModelList(agriments)));
    p5t22.setModel((new ListModelList(agriments)));
    }
    p8t23.setModel((new ListModelList(reasons)));
    p8t23a.setModel((new ListModelList(reasons)));
    p4t222.setModel((new ListModelList(reasons)));
    p4t22.setModel((new ListModelList(reasons)));
    p4t21.setModel((new ListModelList(currencies)));
    ap4t21.setModel((new ListModelList(currencies)));
    fp4t21.setModel((new ListModelList(currencies)));
    p11t21.setModel((new ListModelList(YesNo)));
    p12t21.setModel((new ListModelList(YesNo)));
    p13t21.setModel((new ListModelList(YesNo)));
    ap11t21.setModel((new ListModelList(YesNo)));
    ap12t21.setModel((new ListModelList(YesNo)));
    ap13t21.setModel((new ListModelList(YesNo)));
   
    
    //System.out.println("Contract summa1 "+summa1+" current.getP5t21()="+current.getP5t21()+"  p5t21.doubleValue()="+p5t21.doubleValue());  
    // cu=current.getP5t18();
   /*
    p4t21.setModel((new ListModelList(com.is.tf.contract.ContractService.getMyCurrAcr(idn, gid,alias))));
    ap4t21.setModel((new ListModelList(com.is.tf.contract.ContractService.getMyCurrAcr(idn, gid,alias))));
    fp4t21.setModel((new ListModelList(com.is.tf.contract.ContractService.getMyCurrAcr(idn, gid,alias))));
    p2t23.setModel((new ListModelList(com.is.tf.contract.ContractService.getMyCurrAcr(idn, gid,alias))));
    
    p5t22.setModel((new ListModelList(com.is.tf.contract.ContractService.getAcrAgrim(idn, gid,alias))));
     */  
    refreshModel(_startPageNumber);
    
    
}



public void onPaging$accreditivPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    accreditivPaging.setPageSize(_pageSize);
    filter.setP1t21(idn);
	model = new PagingListModel(activePage, _pageSize, filter, "");
	
	if(_needsTotalSizeUpdate) {
	        _totalSize = model.getTotalSize(filter, "");
	     //   _needsTotalSizeUpdate = false;
	}
	
	accreditivPaging.setTotalSize(_totalSize);
	
	dataGrid.setModel((ListModel) model);
	
	
	if (model.getSize()>0){
	this.current =(Accreditiv) model.getElementAt(0);
	sendSelEvt();
}
}



//Omitted...
public Accreditiv getCurrent() {
return current;
}

public void setCurrent(Accreditiv current) {
this.current = current;
}

public Accreditivsumchange getCurrentSum() {
	return currentSum;
	}

	public void setCurrentSum(Accreditivsumchange currentSum) {
	this.currentSum = currentSum;
	}

	public Accreditivtimechange getCurrentTime() {
		return currentTime;
		}

		public void setCurrentTime(Accreditivtimechange currentTime) {
		this.currentTime = currentTime;
		}

public void onDoubleClick$dataGrid$grd() {
    try{         
    	
	grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            acr_sum.setVisible(false);
            acr_time.setVisible(false);
            tab_acrsum.setVisible(true);
            tab_acrtime.setVisible(true);
            SumchangeGrid.setVisible(true);
            TimechangeGrid.setVisible(true);
            tb1.setVisible(true);
            tb2.setVisible(true);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
           // gid=idc;
            setCurrent();
            setCurrentAdd();
            //p3t231.setValue(curr_.get(current.getP4t21()));
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
    	p4t23a.setReadonly(true);
    	p5t23a.setReadonly(true);
    	p6t23a.setReadonly(true);
    	p8t23a.setDisabled(true);
    	p10t23a.setReadonly(true);
    	sum_div.setVisible(true);
    	sum_grd.setVisible(true);
    	//sum_grd2.setVisible(true);
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
    	p3t222.setReadonly(true);
    	p6t222.setReadonly(true);
    	p5t222.setReadonly(true);
    	p4t222.setDisabled(true);
    	p10t23a.setReadonly(true);
    	setCurrent();
    
    }catch (Exception e) {
		e.printStackTrace();
		alert("onDoubleClick$TimeChangeGrid$grd()= "+e.getMessage());
	}
}

public void onClick$btn_refresh() throws Exception 
{
	refresh(idn);
}
public void onClick$btn_back() {
    if (frm.isVisible()){
        frm.setVisible(false);
        grd.setVisible(true);
        btn_back.setImage("/images/file.png");
        btn_back.setLabel(Labels.getLabel("back"));
    }else onDoubleClick$dataGrid$grd();
}

public void onClick$btn_back2() {
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
    /////////////////////////
        p10t21.setValue((String)session.getAttribute("un"));
    

    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
}



public void onClick$btn_edit() {
	btn_delete2.setVisible(true);
	btn_save2.setVisible(true);
	btn_back2.setVisible(true);
	btn_add2.setVisible(true);
	if (SumchangeGrid.isVisible()){
	p4t23a.setReadonly(false);
	p5t23a.setReadonly(false);
	p6t23a.setReadonly(false);
	p10t23a.setReadonly(false);
	p8t23a.setDisabled(false);
	}
	if (TimechangeGrid.isVisible()){
	p3t222.setReadonly(false);
	p6t222.setReadonly(false);
	p5t222.setReadonly(false);
	p4t222.setDisabled(false);
	p10t23a.setReadonly(false);
	}
}




public void onClick$btn_add() {
    try {
    	if (ap11t21.getValue()!=null&&(cont_type.equals("02")||(cont_type.equals("05")))) {
    		row_ap11t21.setVisible(true);
    	} else {
    		row_ap11t21.setVisible(false);
    	}
    onDoubleClick$dataGrid$grd();
    ap1t21.setValue(idn);
    ap10t21.setValue((String)session.getAttribute("un"));
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    tab_acrsum.setVisible(false);
    tab_acrtime.setVisible(false);
    SumchangeGrid.setVisible(false);
    TimechangeGrid.setVisible(false);
    tb1.setVisible(false);
    tb2.setVisible(false);
    fgrd.setVisible(false);
    if (addgrd.isVisible()){
    btn_delete.setVisible(false);
    } else {
    	btn_delete.setVisible(true);
    }

} catch (Exception e) {
	e.printStackTrace();
	alert("onClick$btn_add()="+e.getMessage());
}
}

public void onClick$btn_acr() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(true);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    acr_sum.setVisible(false);
    acr_time.setVisible(false);
    tab_acrsum.setVisible(true);
    tab_acrtime.setVisible(true);
    
    
    
}
public void onClick$btn_acr1() {
    onDoubleClick$dataGrid$grd();
    un.setValue((String)session.getAttribute("un"));
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(false);
    acr_sum.setVisible(true);
    //setCurrent();
    if (acr_sum.isVisible()){
		  InputElement[] list = {p1t23,p2t23,p3t23,p5t231,p5t23,p5t232 };
		    for (int i=0;i<list.length;i++){
		    	   if (list[i]!=null){
		    	     list[i].setDisabled(true);
		    	   }
		    }
	}

    
    acr_time.setVisible(false);
    btn_acr.setVisible(true);
    p5t231.setSelecteditem(p6t211.getValue());
    p5t232.setSelecteditem(p6t213.getValue());
    
}
public void onClick$btn_acr2() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    p8t22.setValue((String)session.getAttribute("un"));
    fgrd.setVisible(false);
    acr_sum.setVisible(false);
    acr_time.setVisible(true);
    if (acr_time.isVisible()){
		  InputElement[] list = {p1t22,p2t22};
		    for (int i=0;i<list.length;i++){
		    	   if (list[i]!=null){
		    	     list[i].setDisabled(true);
		    	   }
		    }
	}

    btn_acr.setVisible(true);
    
    
}
public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}


private java.util.Calendar Calendar(Date P3t21) {
	// TODO Auto-generated method stub
	return null;
}


public void onClick$btn_save2() {
	try {
		
		
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.AccreditivResult acr =  new com.sbs.service.AccreditivResult();
		
		if (!CheckNull.isEmpty(p4t23a.doubleValue())||(Double.parseDouble(summa1)>p4t23a.doubleValue())){
			  otn2=("0");
		    } else if (!CheckNull.isEmpty(p4t23a.doubleValue())||(Double.parseDouble(summa1)<p4t23a.doubleValue())){
		    	otn2=("1");
		    }
			
     if (sum_grd.isVisible()){
    	 Accreditivsumchange acrs2=new Accreditivsumchange();
    	 //acrs2.setP0t23("0");
         //acrs2.setP1t23(currentSum.getP1t23());
         //acrs2.setP2t23(currentSum.getP2t23());
         acrs2.setP3t23(currentSum.getP3t23());
         acrs2.setP4t23(currentSum.getP4t23());
         acrs2.setP5t23(currentSum.getP5t23());
         acrs2.setP6t23(currentSum.getP6t23());
         acrs2.setP7t23(currentSum.getP7t23());
         acrs2.setP8t23(currentSum.getP8t23());
         acrs2.setP9t23(currentSum.getP9t23());
         acrs2.setP10t23(currentSum.getP10t23());
         acrs2.setP11t23(currentSum.getP11t23());
         acrs2.setP12t23((String)session.getAttribute("un"));
         acrs2.setP15t23(otn2); 
          AccreditivSumChangeResult ars2 = ws.saveAccreditivSumChange(((String)(session.getAttribute("BankINN"))), idn, current.getP2t21(),getAcrSumCorrect(acrs2));
         if (ars2.getStatus() == 0) {
       	  refreshModel(_startPageNumber);
       	  alert("Сохранение успешно");
       	refresh(idn);
       	  System.out.println("Editing AcrSumChange  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
       	  
         } else {
       	  alert("Ошибка при корректировки суммы аккредитива!   Error:"+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
       	System.out.println("Ошибка при корректировки суммы аккредитива!  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
         }
    } 
     
     
	else if (time_grd.isVisible()){
    	Accreditivtimechange acrt=new Accreditivtimechange();
    	//acrt.setP0t22("0");
        //acrt.setP1t22(current.getP2t21());
        acrt.setP2t22(currentTime.getP2t22());
        acrt.setP3t22(currentTime.getP3t22());
        acrt.setP4t22(currentTime.getP4t22());
        acrt.setP5t22(currentTime.getP5t22());
        acrt.setP6t22(currentTime.getP6t22());
        acrt.setP7t22(currentTime.getP7t22());
        //acrt.setP8t22((String)session.getAttribute("un"));
        //acrt.setP9t22(p9t23.getValue());
        //acrt.setP10t22(p10t23.getValue());
         //acrt.setP12t22((String)session.getAttribute("ufn"));
         AccreditivTimeChangeResult ar = ws.saveAccreditivTimeChange(((String)(session.getAttribute("BankINN"))), idn, current.getP2t21(), getAcrTimeCorrect(acrt));
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Сохранение успешно");
      	refresh(idn);
      	  System.out.println("Дата аккредитива сохранена успешно: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	  
        } else {
      	  alert("Ошибка при сохранении срока аккредитива   Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	System.out.println("Ошибка при сохранении срока аккредитива: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
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
		if (!CheckNull.isEmpty(p4t23a.doubleValue())||(Double.parseDouble(summa1)>p4t23a.doubleValue())){
			  otn2=("0");
		    } else if (!CheckNull.isEmpty(p4t23a.doubleValue())||(Double.parseDouble(summa1)<p4t23a.doubleValue())){
		    	otn2=("1");
		    }
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.AccreditivResult acr =  new com.sbs.service.AccreditivResult();
		//alert("test0");
		
		
     if (sum_grd.isVisible()){
    	 Accreditivsumchange acrs2=new Accreditivsumchange();
    	 //acrs2.setP0t23("0");
         //acrs2.setP1t23(currentSum.getP1t23());
         //acrs2.setP2t23(currentSum.getP2t23());
         acrs2.setP3t23(currentSum.getP3t23());
         acrs2.setP4t23(currentSum.getP4t23());
         acrs2.setP5t23(currentSum.getP5t23());
         acrs2.setP6t23(currentSum.getP6t23());
         acrs2.setP7t23(currentSum.getP7t23());
         acrs2.setP8t23(currentSum.getP8t23());
         acrs2.setP9t23(currentSum.getP9t23());
         acrs2.setP10t23(currentSum.getP10t23());
         acrs2.setP11t23(currentSum.getP11t23());
         acrs2.setP12t23((String)session.getAttribute("un"));
         acrs2.setP15t23(otn2); 
        //current.setP17t21(p17t21.getValue());
        //System.out.println("session.getAttribute(ufn)="+session.getAttribute("ufn")+"session.getAttribute(un)="+p10t21.getAttribute("un").toString());
  	
         AccreditivSumChangeResult ars2 = ws.saveAccreditivSumChange(((String)(session.getAttribute("BankINN"))), idn, current.getP2t21(),AcrSumDelete(acrs2));
         if (ars2.getStatus() == 0) {
       	  refreshModel(_startPageNumber);
       	  alert("Удалено успешно");
       	refresh(idn);
       	  System.out.println("Delete AcrSumChange  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
       	  
         } else {
       	  alert("Ошибка при удалении суммы аккредитива!   Error:"+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
       	System.out.println("Ошибка при удалении суммы аккредитива!  Status: "+ars2.getStatus()+";  GTKid:" +ars2.getGtkId()+ ";  Text:" +ars2.getErrorMsg());
         }
    } 
    
     else if (time_grd.isVisible()){
     	Accreditivtimechange acrt=new Accreditivtimechange();
     	//acrt.setP0t22("0");
         //acrt.setP1t22(current.getP2t21());
         acrt.setP2t22(currentTime.getP2t22());
         acrt.setP3t22(currentTime.getP3t22());
         acrt.setP4t22(currentTime.getP4t22());
         acrt.setP5t22(currentTime.getP5t22());
         acrt.setP6t22(currentTime.getP6t22());
         acrt.setP7t22(currentTime.getP7t22());
         //acrt.setP8t22((String)session.getAttribute("un"));
         //acrt.setP9t22(p9t23.getValue());
         //acrt.setP10t22(p10t23.getValue());
          //acrt.setP12t22((String)session.getAttribute("ufn"));
          AccreditivTimeChangeResult ar = ws.saveAccreditivTimeChange(((String)(session.getAttribute("BankINN"))), idn, current.getP2t21(), getAcrTimeDelete(acrt));
         if (ar.getStatus() == 0) {
       	  refreshModel(_startPageNumber);
       	  alert("Удалено успешно");
       	refresh(idn);
       	  System.out.println("Дата аккредитива удалена успешно: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
       	  
         } else {
       	  alert("Ошибка при удалении срока аккредитива   Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
       	System.out.println("Ошибка при удалении срока аккредитива: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
         }
 	}
     
onClick$btn_back2();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
	} catch(Exception e) {
    e.printStackTrace();}
    }




public void onClick$btn_delete() {
	try {
		
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.AccreditivResult acr =  new com.sbs.service.AccreditivResult();
		//alert("test0");
		
		
     if (frmgrd.isVisible()){
    	current.setP0t21("2");
        current.setP1t21(p1t21.getValue());
        current.setP2t21(p2t21.getValue());
        current.setP3t21(p3t21.getValue());
        current.setP4t21(p4t21.getValue());
        current.setP5t21(p5t21.doubleValue());
        current.setP6t21(p6t212.doubleValue());
        current.setP7t21(p7t21.doubleValue());
        current.setP8t21(p8t21.doubleValue());
        current.setP9t21(p9t21.getValue());
        //current.setP10t21(p10t21.getValue());
        current.setP10t21((String)session.getAttribute("un"));
        //current.setP10t21(p10t21.getAttribute("un").toString());
        //p10t21.setValue(current.getP10t21());
        //current.setP11t21(CheckNull.isEmpty(p11t21.getValue())?0:(p11t21.getValue()));
        current.setP11t21(CheckNull.isEmpty(p11t21.getValue())?"":p11t21.getValue());
        current.setP12t21(CheckNull.isEmpty(p12t21.getValue())?"":p12t21.getValue());
        current.setP13t21(CheckNull.isEmpty(p13t21.getValue())?"":p13t21.getValue());
        current.setP16t21(otn);
        //current.setP17t21(p17t21.getValue());
        //System.out.println("session.getAttribute(ufn)="+session.getAttribute("ufn")+"session.getAttribute(un)="+p10t21.getAttribute("un").toString());
  	
        AccreditivResult ar = ws.saveAccreditiv(((String)(session.getAttribute("BankINN"))), p1t21.getValue() , delAcr(current));
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Запрос на удаление передано в ГО");
      	refresh(idn);
      	  
        } else {
      	  alert("Ошибка при удалении1!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
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
	try {
		
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.AccreditivResult acr =  new com.sbs.service.AccreditivResult();
		//alert("test0");
	
			if(addgrd.isVisible()){
				
				 AccreditivResult ar = ws.saveAccreditiv(((String)(session.getAttribute("BankINN"))), idn , getAcr1(new Accreditiv(
            //Long.parseLong(aid.getValue()),
            //ap0t21.getValue(),
            //ap1t21.getValue(),
            ap2t21.getValue(),
            ap3t21.getValue(),
            ap4t21.getValue(),
            ap5t21.doubleValue(),
            ap6t212.doubleValue(),
            ap7t21.doubleValue(),
            ap8t21.doubleValue(),
            ap9t21.getValue(),
            //Long.parseLong(aid_contract.getValue()),
            //ap10t21.getValue(),
            ap11t21.getValue(),
            ap12t21.getValue(),
            ap13t21.getValue()
            //ap16t21.getValue()
            //ap17t21.getValue()
            )));
				    if (CheckNull.isEmpty(ap5t21.doubleValue())&&(Double.parseDouble(summa1)>ap5t21.doubleValue())){
				    	aotn="0";
				    } else if (CheckNull.isEmpty(ap5t21.doubleValue())&&(Double.parseDouble(summa1)<ap5t21.doubleValue())){
				    	aotn="1";
				    }
				 if (ar.getStatus() == 0) {
			      	  refreshModel(_startPageNumber);
			      	  alert("Сохранение успешно");
			      	refresh(idn);
			      	  
			        } else {
			      	  alert("Error save New accred; Status:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
			      	System.out.println("Error save New accred; Status:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
			      	  ISLogger.getLogger().error(" in save New accred:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
			      	  
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
        
       
        }
        
    } else if (acr_sum.isVisible()){
    	if (!CheckNull.isEmpty(p4t23.doubleValue())&&(Double.parseDouble(summa1)>p4t23.doubleValue())){
	    	otn2=("0");
	    } else if (!CheckNull.isEmpty(p4t23.doubleValue())&&(Double.parseDouble(summa1)<p4t23.doubleValue())){
	    	otn2=("1");
	    	System.out.println("  otn2="+otn2+"   p5t21.doubleValue()="+p5t21.doubleValue());
	    }
    	Accreditivsumchange acrs=new Accreditivsumchange();
    	acrs.setP0t23("0");
        acrs.setP1t23(current.getP2t21());
        acrs.setP2t23(p2t21.getValue());
        acrs.setP3t23(p5t21.doubleValue());
        acrs.setP4t23(p4t23.doubleValue());
        acrs.setP5t23(p6t212.doubleValue());
        acrs.setP6t23(p6t23.doubleValue());
        acrs.setP7t23(p7t23.doubleValue());
        acrs.setP8t23(p8t23.getValue());
        acrs.setP9t23(p9t23.getValue());
        acrs.setP10t23(p10t23.getValue());
        acrs.setP12t23((String)session.getAttribute("un"));
        acrs.setP15t23(otn2); 
         AccreditivSumChangeResult ars = ws.saveAccreditivSumChange(((String)(session.getAttribute("BankINN"))), idn, current.getP2t21(),getAcrSum(acrs));
        if (ars.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Сохранение успешно");
      	  System.out.println("Save AcrSumChange  Status: "+ars.getStatus()+";  GTKid:" +ars.getGtkId()+ ";  Text:" +ars.getErrorMsg());
      	  
        } else {
      	  alert("Ошибка при сохранении суммы аккредитива!   Error:"+ars.getStatus()+";  GTKid:" +ars.getGtkId()+ ";  Text:" +ars.getErrorMsg());
      	System.out.println("Ошибка при сохранении суммы аккредитива!  Status: "+ars.getStatus()+";  GTKid:" +ars.getGtkId()+ ";  Text:" +ars.getErrorMsg());
        }
	/*	
    } else if (btn_delete.isVisible()){
    	current.setP0t21("2");
        current.setP1t21(p1t21.getValue());
        current.setP2t21(p2t21.getValue());
        current.setP3t21(p3t21.getValue());
        current.setP4t21(p4t21.getValue());
        current.setP5t21(p5t21.doubleValue());
        current.setP6t21(p6t212.doubleValue());
        current.setP7t21(p7t21.doubleValue());
        current.setP8t21(p8t21.doubleValue());
        current.setP9t21(p9t21.getValue());
        //current.setP10t21(p10t21.getValue());
        current.setP10t21((String)session.getAttribute("un"));
        //current.setP10t21(p10t21.getAttribute("un").toString());
        //p10t21.setValue(current.getP10t21());
        //current.setP11t21(CheckNull.isEmpty(p11t21.getValue())?0:(p11t21.getValue()));
        current.setP11t21(CheckNull.isEmpty(p11t21.getValue())?0:Integer.parseInt(p11t21.getValue()));
        current.setP12t21(CheckNull.isEmpty(p12t21.getValue())?null:p12t21.getValue());
        current.setP13t21(CheckNull.isEmpty(p13t21.getValue())?null:p13t21.getValue());
        current.setP16t21(CheckNull.isEmpty(otn)?null:otn);
        //current.setP17t21(p17t21.getValue());
        //System.out.println("session.getAttribute(ufn)="+session.getAttribute("ufn")+"session.getAttribute(un)="+p10t21.getAttribute("un").toString());
  	
        AccreditivResult ar = ws.saveAccreditiv(((String)(session.getAttribute("BankINN"))), p1t21.getValue() , delAcr(current));
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Удаление успешно");
      	  
        } else {
      	  alert("Ошибка при удалении2!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
        }
		*/	
	}else if (acr_time.isVisible()){
    	Accreditivtimechange acrt=new Accreditivtimechange();
    	acrt.setP0t22("0");
        acrt.setP1t22(current.getP2t21());
        acrt.setP2t22(current.getP9t21());
        acrt.setP3t22(p3t22.getValue());
        acrt.setP4t22(p4t22.getValue());
        acrt.setP5t22(p5t22.getValue());
        acrt.setP6t22(p6t22.getValue());
        //acrt.setP7t22(Integer.parseInt(p7t22.getValue()));
        acrt.setP8t22((String)session.getAttribute("un"));
        //acrt.setP9t22(p9t23.getValue());
        //acrt.setP10t22(p10t23.getValue());
         //acrt.setP12t22((String)session.getAttribute("ufn"));
         AccreditivTimeChangeResult ar = ws.saveAccreditivTimeChange(((String)(session.getAttribute("BankINN"))), idn, current.getP2t21(), getAcrTime(acrt));
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Сохранение успешно");
      	  System.out.println("Дата аккредитива сохранена успешно: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	  
        } else {
      	  alert("Ошибка при сохранении срока аккредитива   Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	System.out.println("Ошибка при сохранении срока аккредитива: "+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
        }
	}
    
    
    
    
    else if(fgrd.isVisible()){
        filter = new AccreditivFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP0t21(fp0t21.getValue());
          filter.setP1t21(fp1t21.getValue());
          //filter.setP2t21(fp2t21.getValue());
          _startPageNumber=0;
          filter.setP3t21(fp3t21.getValue());
          filter.setP4t21(fp4t21.getValue());
          filter.setP5t21(fp5t21.doubleValue());
          filter.setP6t21(fp6t21.doubleValue());
          filter.setP7t21(fp7t21.doubleValue());
          filter.setP8t21(fp8t21.doubleValue());
          filter.setP9t21(fp9t21.getValue());
          filter.setP10t21(fp10t21.getValue());
          

    }else {
    	
    	
          //Long.parseLong(id.getValue());
          current.setP0t21("1");
          current.setP1t21(p1t21.getValue());
          current.setP2t21(p2t21.getValue());
          current.setP3t21(p3t21.getValue());
          current.setP4t21(p4t21.getValue());
          current.setP5t21(p5t21.doubleValue());
          current.setP6t21(p6t212.doubleValue());
          current.setP7t21(p7t21.doubleValue());
          current.setP8t21(p8t21.doubleValue());
          current.setP9t21(p9t21.getValue());
          //current.setP10t21(p10t21.getValue());
          current.setP10t21(((User)session.getAttribute("current_user")).getFull_name());
          //current.setP10t21(p10t21.getAttribute("un").toString());
          //p10t21.setValue(current.getP10t21());
          //current.setP11t21(CheckNull.isEmpty(p11t21.getValue())?0:(p11t21.getValue()));
          current.setP11t21(CheckNull.isEmpty(p11t21.getValue())?null:p11t21.getValue());
          current.setP12t21(CheckNull.isEmpty(p12t21.getValue())?null:p12t21.getValue());
          current.setP13t21(CheckNull.isEmpty(p13t21.getValue())?null:p13t21.getValue());
          current.setP16t21(CheckNull.isEmpty(otn)?null:otn);
          //current.setP17t21(p17t21.getValue());
          //System.out.println("session.getAttribute(ufn)="+session.getAttribute("ufn")+"session.getAttribute(un)="+p10t21.getAttribute("un").toString());
    	
          AccreditivResult ar = ws.saveAccreditiv(((String)(session.getAttribute("BankINN"))), p1t21.getValue() , getAcr2(current));
          if (ar.getStatus() == 0) {
        	  refreshModel(_startPageNumber);
        	  alert("Сохранение успешно");
        	  refresh(idn);
        	  
        	  
          } else {
        	  alert("Ошибка при сохранении!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
          }
    	 
         //  AccreditivService.update(current);
  	
    
    
    
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
	} catch(Exception e) {
    e.printStackTrace();
	alert("ERROR:"+e.getMessage());
	 ISLogger.getLogger().error("ERROR Save accred:"+e.getMessage());
    }}
	
	

public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new AccreditivFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);}


private com.sbs.service.Accreditiv delAcr(Accreditiv acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  
	  com.sbs.service.Accreditiv res = new com.sbs.service.Accreditiv();
	  res.setP0T21(2);
	  res.setP2T21(acr.getP2t21());
	  cal.setTime(acr.getP3t21());
	  res.setP3T21(cal);
	  res.setP4T21(acr.getP4t21());
	  res.setP5T21(acr.getP5t21());
	  res.setP6T21(acr.getP6t21());
	  res.setP7T21(acr.getP7t21());
	  res.setP8T21(acr.getP8t21());
	  res.setP9T21(Integer.parseInt(acr.getP9t21()));
	  res.setP10T21((String)session.getAttribute("un"));
	  if (!CheckNull.isEmpty(acr.getP11t21())) {
	  res.setP11T21(Short.parseShort(acr.getP11t21()));
	  }
	  if (!CheckNull.isEmpty(acr.getP12t21())) {
	  res.setP12T21(Short.parseShort(acr.getP12t21()));
	  }
	  if (!CheckNull.isEmpty(acr.getP13t21())) {
		  res.setP13T21(Short.parseShort(acr.getP13t21()));
	  }
	  
	  res.setP16T21(Short.parseShort(otn));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
	  return res;
	  
}

private com.sbs.service.AccreditivSumChange AcrSumDelete(Accreditivsumchange acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.AccreditivSumChange res = new com.sbs.service.AccreditivSumChange();
	  res.setP0T23(2);
	  res.setP3T23(acr.getP3t23());
	  res.setP4T23(acr.getP4t23());
	  res.setP5T23(acr.getP5t23());
	  res.setP6T23(acr.getP6t23());
	  if (!CheckNull.isEmpty(acr.getP7t23())) {
	  res.setP7T23(acr.getP7t23());
	  }
	  if (!CheckNull.isEmpty(acr.getP8t23())) {
	  res.setP8T23(Short.parseShort(acr.getP8t23()));
	  }
	  if (!CheckNull.isEmpty(acr.getP9t23())) {
		  res.setP9T23(acr.getP9t23());
	  }
	  if (!CheckNull.isEmpty(acr.getP10t23())) {
	  res.setP10T23(acr.getP10t23());
	  }
	  res.setP11T23(Integer.parseInt(acr.getP11t23()));
	  res.setP12T23((String)session.getAttribute("un"));
	  res.setP15T23(Short.parseShort(otn2));
	  //res.setP13T23(Short.parseShort(acr.getP13t23()));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
	  return res;
	  }


  private com.sbs.service.Accreditiv getAcr1(Accreditiv acr) throws Exception {
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.Accreditiv res = new com.sbs.service.Accreditiv();
	  res.setP0T21(0);
	  res.setP2T21(acr.getP2t21());
	  cal.setTime(df.parse(df.format(acr.getP3t21())));
	  res.setP3T21(cal);
	  res.setP4T21(acr.getP4t21());
	  res.setP5T21(acr.getP5t21());
	  if ((acr.getP6t21()!=null)||(acr.getP6t21()!=0.0)) {
	  res.setP6T21(acr.getP6t21());
	  }
	  res.setP7T21(acr.getP7t21());
	  if (!CheckNull.isEmpty(acr.getP8t21())) {
	  res.setP8T21(acr.getP8t21());
	  }
	  res.setP9T21(Integer.parseInt(acr.getP9t21()));
	  res.setP10T21((String)session.getAttribute("un"));
	  if (!CheckNull.isEmpty(acr.getP11t21())) {
	  res.setP11T21(Short.parseShort(acr.getP11t21()));
	  } 
	  if (!CheckNull.isEmpty(acr.getP12t21())) {
		  res.setP12T21(Short.parseShort(acr.getP12t21()));
		  }
		  if (!CheckNull.isEmpty(acr.getP13t21())) {
			  res.setP13T21(Short.parseShort(acr.getP13t21()));
		  }res.setP16T21(Short.parseShort(aotn));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
		  System.out.println("acr.getP6t21()   "+acr.getP6t21());
	  return res;
	  
  }
  
  private com.sbs.service.Accreditiv getAcr2(Accreditiv acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.Accreditiv res = new com.sbs.service.Accreditiv();
	  res.setP0T21(1);
	  res.setP2T21(acr.getP2t21());
	  cal.setTime(df.parse(df.format(acr.getP3t21())));
	  res.setP3T21(cal);
	  res.setP4T21(acr.getP4t21());
	  res.setP5T21(acr.getP5t21());
	  res.setP6T21(acr.getP6t21());
	  res.setP7T21(acr.getP7t21());
	  res.setP8T21(acr.getP8t21());
	  res.setP9T21(Integer.parseInt(acr.getP9t21()));
	  res.setP10T21((String)session.getAttribute("un"));
	  if (!CheckNull.isEmpty(acr.getP11t21())) {
	  res.setP11T21(Short.parseShort(acr.getP11t21()));
	  }
	  if (!CheckNull.isEmpty(acr.getP12t21())) {
		  res.setP12T21(Short.parseShort(acr.getP12t21()));
		  }
	  if (!CheckNull.isEmpty(acr.getP13t21())) {
			  res.setP13T21(Short.parseShort(acr.getP13t21()));
		  }
	  res.setP16T21(Short.parseShort(otn));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
	  return res;
	  
  }

  private com.sbs.service.Accreditiv saveAcr(Accreditiv acr) throws Exception{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.Accreditiv res = new com.sbs.service.Accreditiv();
	  res.setP0T21(0);
	  res.setP2T21(acr.getP2t21());
	  cal.setTime(df.parse(df.format(acr.getP3t21())));
	  res.setP3T21(cal);
	  res.setP4T21(acr.getP4t21());
	  res.setP5T21(acr.getP5t21());
	  res.setP6T21(acr.getP6t21());
	  res.setP7T21(acr.getP7t21());
	  res.setP8T21(acr.getP8t21());
	  res.setP9T21(Integer.parseInt(acr.getP9t21()));
	  res.setP10T21((String)session.getAttribute("un"));
	  if (!CheckNull.isEmpty(acr.getP11t21())) {
		  res.setP11T21(Short.parseShort(acr.getP11t21()));
		  }
		  if (!CheckNull.isEmpty(acr.getP12t21())) {
			  res.setP12T21(Short.parseShort(acr.getP12t21()));
			  }
		  if (!CheckNull.isEmpty(acr.getP13t21())) {
				  res.setP13T21(Short.parseShort(acr.getP13t21()));
			  }
	  res.setP16T21(Short.parseShort(otn));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
	  return res;}
  
  private com.sbs.service.AccreditivSumChange getAcrSumCorrect(Accreditivsumchange acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.AccreditivSumChange res = new com.sbs.service.AccreditivSumChange();
	  res.setP0T23(1);
	  res.setP3T23(acr.getP3t23());
	  res.setP4T23(acr.getP4t23());
	  res.setP5T23(acr.getP5t23());
	  res.setP6T23(acr.getP6t23());
	  if (!CheckNull.isEmpty(acr.getP7t23())) {
	  res.setP7T23(acr.getP7t23());
	  }
	  if (!CheckNull.isEmpty(acr.getP8t23())) {
	  res.setP8T23(Short.parseShort(acr.getP8t23()));
	  }
	  if (!CheckNull.isEmpty(acr.getP9t23())) {
		  res.setP9T23(acr.getP9t23());
	  }
	  if (!CheckNull.isEmpty(acr.getP10t23())) {
	  res.setP10T23(acr.getP10t23());
	  }
	  res.setP11T23(Integer.parseInt(acr.getP11t23()));
	  res.setP12T23((String)session.getAttribute("un"));
	  res.setP15T23(Short.parseShort(otn2));
	  //res.setP13T23(Short.parseShort(acr.getP13t23()));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
	  return res;}
  
  private com.sbs.service.AccreditivSumChange getAcrSum(Accreditivsumchange acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.AccreditivSumChange res = new com.sbs.service.AccreditivSumChange();
	  res.setP0T23(0);
	  res.setP3T23(acr.getP3t23());
	  res.setP4T23(acr.getP4t23());
	  res.setP5T23(acr.getP5t23());
	  res.setP6T23(acr.getP6t23());
	  res.setP7T23(acr.getP7t23());
	  res.setP8T23(Short.parseShort(acr.getP8t23()));
	  if (!CheckNull.isEmpty(acr.getP9t23())) {
		  res.setP9T23(acr.getP9t23());
	  }
	  res.setP10T23(acr.getP10t23());
	  //res.setP11T23(1);
	  res.setP12T23((String)session.getAttribute("un"));
	  res.setP15T23(Short.parseShort(otn2));
	  //res.setP13T23(Short.parseShort(acr.getP13t23()));
	  //cal.setTime(acr.getP17t21());
	  //res.setP17T21(cal);
	  return res;}
  
  private com.sbs.service.AccreditivTimeChange getAcrTime(Accreditivtimechange acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.AccreditivTimeChange res = new com.sbs.service.AccreditivTimeChange();
	  res.setP0T22(Integer.parseInt(acr.getP0t22()));
	  res.setP3T22(Integer.parseInt(acr.getP3t22()));
	  res.setP4T22(Integer.parseInt(acr.getP4t22()));
	  if (!CheckNull.isEmpty(acr.getP5t22())) {
		  res.setP5T22(acr.getP5t22());
	  }
	  res.setP6T22(acr.getP6t22());
	  //res.setP7T22(acr.getP7t22());
	  res.setP8T22((String)session.getAttribute("un"));
	  return res;
	  }
  private com.sbs.service.AccreditivTimeChange getAcrTimeCorrect(Accreditivtimechange acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.AccreditivTimeChange res = new com.sbs.service.AccreditivTimeChange();
	  res.setP0T22(1);
	  res.setP3T22(Integer.parseInt(acr.getP3t22()));
	  res.setP4T22(Integer.parseInt(acr.getP4t22()));
	  if (!CheckNull.isEmpty(acr.getP5t22())) {
		  res.setP5T22(acr.getP5t22());
	  }
	  if (!CheckNull.isEmpty(acr.getP6t22())) {
	  res.setP6T22(acr.getP6t22());
	  }
	  res.setP7T22(acr.getP7t22());
	  res.setP8T22((String)session.getAttribute("un"));
	  return res;
	  }
  
  private com.sbs.service.AccreditivTimeChange getAcrTimeDelete(Accreditivtimechange acr){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.AccreditivTimeChange res = new com.sbs.service.AccreditivTimeChange();
	  res.setP0T22(2);
	  res.setP3T22(Integer.parseInt(acr.getP3t22()));
	  res.setP4T22(Integer.parseInt(acr.getP4t22()));
	  if (!CheckNull.isEmpty(acr.getP5t22())) {
		  res.setP5T22(acr.getP5t22());
	  }
	  if (!CheckNull.isEmpty(acr.getP6t22())) {
	  res.setP6T22(acr.getP6t22());
	  }
	  res.setP7T22(acr.getP7t22());
	  res.setP8T22((String)session.getAttribute("un"));
	  return res;
	  }
  
  public void onSelect$p8t23() {
		if (p8t23.getValue().equalsIgnoreCase("4")) {
			row_p9t23.setVisible(true);
			row_p10t23.setVisible(false);
			//p10t23.setSelectedIndex(-1);
			System.out.println("1--p10t23.setValue()="+p10t23.getValue()+" p9t23.getValue()="+p9t23.getValue());
		} else {
			row_p9t23.setVisible(false);
			row_p10t23.setVisible(true);
			//p9t23.setValue("");
			System.out.println("2---p10t23.setValue()="+p10t23.getValue()+" p9t23.getValue()="+p9t23.getValue());
		}
		//p9t23.setSelectedIndex(-1);
		//p10t23.setValue("");
		
	}
  public void onSelect$p11t21() {
		if (p11t21.getValue().equalsIgnoreCase("1")) {
			row_p12t21.setVisible(true);
			row_p13t21.setVisible(false);
		} else if (p11t21.getValue().equalsIgnoreCase("2")){
			row_p12t21.setVisible(false);
			row_p13t21.setVisible(true);
		} else if (p11t21.getValue().equalsIgnoreCase("3")){
			row_p12t21.setVisible(true);
			row_p13t21.setVisible(false);
		p12t21.setSelectedIndex(-1);
		p13t21.setSelectedIndex(-1);
	}
  }
  public void onSelect$ap11t21() {
		if (ap11t21.getValue().equalsIgnoreCase("1")) {
			row_ap12t21.setVisible(true);
			row_ap13t21.setVisible(false);
		} else if (ap11t21.getValue().equalsIgnoreCase("2")){
			row_ap12t21.setVisible(false);
			row_ap13t21.setVisible(true);
		} else if (ap11t21.getValue().equalsIgnoreCase("3")){
			row_ap12t21.setVisible(true);
			row_ap13t21.setVisible(false);
		ap12t21.setSelectedIndex(-1);
		ap13t21.setSelectedIndex(-1);
	}
}

  public void onSelect$p4t22() {
		if (p4t22.getValue().equalsIgnoreCase("4")) {
			row_p5t22.setVisible(true);
			row_p6t22.setVisible(false);
			//p6t22.setValue("");
			
		} else {
			row_p5t22.setVisible(false);
			row_p6t22.setVisible(true);
			//p5t22.setSelectedIndex(-1);
		}
		p5t22.setSelectedIndex(-1);
		p6t22.setValue("");
	}
  
////////////////////Curensy//////////////////
  private void setCurrent() {
		if (current != null) {
			
			currenciesg = com.is.tf.contract.ContractService.getMyCurrA(idn, df.format(new java.util.Date()), alias);
			//System.out.println(currenciesg.size());
			p6t213.setModel((new ListModelList(currenciesg)));
			//ap6t211.setModel((new ListModelList(currenciesg)));
			//ap6t213.setModel((new ListModelList(currenciesg)));
			p6t211.setModel((new ListModelList(currenciesg)));
			p5t232.setModel((new ListModelList(currenciesg)));
			p5t231.setModel((new ListModelList(currenciesg)));
			if (currenciesg.size() > 0){
				if ((current.getP4t21().equalsIgnoreCase("000")?"860":current.getP4t21()).equalsIgnoreCase(val2.equalsIgnoreCase("000")?"860":val2)
					&& (CheckNull.isEmpty(val2) || (current.getP4t21().equalsIgnoreCase("000")?"860":current.getP4t21()).equalsIgnoreCase(val1.equalsIgnoreCase("000")?"860":val1))
				){
					kur.setVisible(false);
				} else {       
					kur.setVisible(true);
				
				}
			}
			
			if (current.getP11t21()!=null&&(cont_type.equals("02")||(cont_type.equals("05")))) {
	    		row_p11t21.setVisible(true);
	    	} else {
	    		row_p11t21.setVisible(false);
	    	}
			if (CheckNull.isEmpty(val2)) {
				sum_gar2.setVisible(false);
			} else {
				sum_gar2.setVisible(true);
			}
		    if (!CheckNull.isEmpty(current.getP5t21())&&(Double.parseDouble(summa1)>current.getP5t21())){
		    	otn=("0");
		    } else if (!CheckNull.isEmpty(current.getP5t21())&&(Double.parseDouble(summa1)<current.getP5t21())){
		    	otn=("1");
		    	System.out.println("current summa1="+summa1+"  otn="+otn+"   p5t21.doubleValue()="+p5t21.doubleValue());
		    }
		    
		    
		    SumchangeGrid.setModel(new BindingListModelList(current.getSumchanges(), true));
		    TimechangeGrid.setModel(new BindingListModelList(current.getTimechanges(), true));
		    if (!CheckNull.isEmpty(currentSum.getP8t23())){
		    	p8t23a.setValue(currentSum.getP8t23());
		    }
		    if ((!CheckNull.isEmpty(currentSum.getP8t23())||(p8t23a.getValue().equals("5")))){
		    	row_p9t23a.setVisible(false);
		    	row_p10t23a.setVisible(true);
		    } else if ((!CheckNull.isEmpty(currentSum.getP8t23())||(p8t23a.getValue().equals("4")))){
		    	row_p9t23a.setVisible(true);
		    	row_p10t23a.setVisible(false);
		    }
		    if ((CheckNull.isEmpty(currentSum.getP7t23()))||(currentSum.getP7t23().equals(0.0))){
		    	row_p7t23a.setVisible(false);
		    } else {
		    	row_p7t23a.setVisible(true);
		    }
		    
		    if (!CheckNull.isEmpty(currentTime.getP4t22())){
		    	p4t222.setValue(currentTime.getP4t22());
		    }
		    if ((!CheckNull.isEmpty(currentTime.getP4t22())||(p4t222.getValue().equals("5")))){
		    	row_p5t222.setVisible(false);
		    	row_p6t222.setVisible(true);
		    } else if ((!CheckNull.isEmpty(currentTime.getP4t22())||(p4t222.getValue().equals("4")))){
		    	row_p5t222.setVisible(true);
		    	row_p6t222.setVisible(false);
		    }
		    
		}
		
	}
  
	public void onChange$p8t23a() {
		if (p8t23a.getValue().equals("5")){
	    	row_p9t23a.setVisible(false);
	    	row_p10t23a.setVisible(true);
	    } else if (p8t23a.getValue().equals("4")){
	    	row_p9t23a.setVisible(true);
	    	row_p10t23a.setVisible(false);
	    }
	}

	public void onChange$p4t222() {
		if (p4t222.getValue().equals("5")){
			row_p5t222.setVisible(false);
			row_p6t222.setVisible(true);
	    } else if (p4t222.getValue().equals("4")){
	    	row_p5t222.setVisible(true);
	    	row_p6t222.setVisible(false);
	    }
	}

	public void onInitRenderLater$p6t213() {
		p6t213.setSelectedIndex(currenciesg.size()-1);
		p5t231.setSelecteditem(p6t211.getValue());
		p5t232.setSelecteditem(p6t213.getValue());
		countCourse(false);
	}

	public void onChange$p5t21() {
		countCourse(false);
	}

	public void onChange$p7t21() {
		countCourse(false);
	}

	public void onChange$p8t21() {
		countCourse(false);
	}

	public void onSelect$p4t21() {
		countCourse(true);
	}

	public void onSelect$p6t211() { 
		countCourse(true);
	}

	public void onClick$btn_recount() {
		countCourse(true);
	}

	private void countCourse(Boolean setp6t212) {
		try {
			if(!CheckNull.isEmpty(p6t213.getValue()) && !CheckNull.isEmpty(p6t211.getValue())) {
				System.out.println("***"+p6t211.getValue()+" - "+p6t213.getValue());
				if (setp6t212) {
					p6t212.setValue(""+(p6t211.getCourse()/p6t213.getCourse()));
					current.setP6t21((p6t211.getCourse()/p6t213.getCourse()));
				}
				cbcourse.setValue("По курсу ЦБ: "+(p6t211.getCourse()/p6t213.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (p4t21.getValue().equalsIgnoreCase(p6t211.getValue())) {
					if (val1.equalsIgnoreCase(p6t211.getValue())) {
						db = (p7t21.doubleValue() + (p8t21.doubleValue()/p6t212.doubleValue()));
						bool = (p5t21.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(p6t211.getValue())) {
						db = (p8t21.doubleValue() + (p7t21.doubleValue()/p6t212.doubleValue()));
						bool = (p5t21.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else {
						checksum.setChecked(false);
						checksum.setLabel(("Сумма аккредитива не соответствует указанному курсу!"));
					}
				} else if (p4t21.getValue().equalsIgnoreCase(p6t213.getValue())) {
					if (val1.equalsIgnoreCase(p6t213.getValue())) {
						db = (p7t21.doubleValue() + (p8t21.doubleValue()*p6t212.doubleValue()));
						bool = (p5t21.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(p6t213.getValue())) {
						db = (p8t21.doubleValue() + (p7t21.doubleValue()*p6t212.doubleValue()));
						bool = (p5t21.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else {
						checksum.setChecked(false);
						checksum.setLabel(("Сумма аккредитива не соответствует указанному курсу!"));
					}
				} else {
					checksum.setChecked(false);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setCurrentAdd() {
		CheckNull.clearForm(addgrd);
		ap3t21.setValue(new java.util.Date());
		ap4t21.setSelecteditem(val1);
		if (ap3t21.getValue()!=null&&ap4t21.getValue()!=null){
		currenciesg = com.is.tf.contract.ContractService.getMyCurrA(idn, df.format(ap3t21.getValue()), alias);
		ap6t211.setModel((new ListModelList(currenciesg)));
		ap6t213.setModel((new ListModelList(currenciesg)));
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
		}
		
	/*
		if (!CheckNull.isEmpty(ap9t21.getValue())) {
			agar_date2.setVisible(false);
		} else {
			agar_date2.setVisible(true);
		}
		*/
	}		
/*
	public void onBlur$ap10t18() {
		onChange$ap10t18();
	}
	public void onOk$ap10t18() {
		onChange$ap10t18();
	}

	public void onChange$ap10t18() {
		if (!CheckNull.isEmpty(current.getP10t18())) {
			agar_date2.setVisible(false);
		} else {
			agar_date2.setVisible(true);
		}
	}
*/
	public void onInitRenderLater$ap6t213() {
		ap6t213.setSelectedIndex(currenciesg.size()-1);
		acountCourse(false);
	}

	
	public void onChange$ap5t21() {
		acountCourse(false);
		if (Double.parseDouble(summa1)>ap5t21.doubleValue()){
			  aotn=("0");
		    } else if (Double.parseDouble(summa1)<ap5t21.doubleValue()){
		    	aotn=("1");
		    }
		 //alert("summa1="+summa1+"  aotn="+aotn+"   ap5t21.doubleValue()="+ap5t21.doubleValue());	
	}
	
	public void onChange$p4t23a() {
		countCourse(false);
		if (Double.parseDouble(summa1)>p4t23a.doubleValue()){
			  otn2=("0");
		    } else if (Double.parseDouble(summa1)<p4t23a.doubleValue()){
		    	otn2=("1");
		    }
		 //alert("summa1="+summa1+"  otn2="+otn2);	
	}
	

	public void onChange$ap7t21() {
		acountCourse(false);
	}

	public void onChange$ap8t21() {
		acountCourse(false);
	}

	public void onSelect$ap4t21() {
		acountCourse(true);
	}

	public void onSelect$ap6t211() {
		acountCourse(true);
	}
	
	public void onClick$abtn_recount() {
		acountCourse(true);
	}

	private void acountCourse(Boolean setap6t212) {
		try {
			if(!CheckNull.isEmpty(ap6t213.getValue()) && !CheckNull.isEmpty(ap6t211.getValue())) {
				//System.out.println("***"+ap6t211.getValue()+" - "+ap6t213.getValue());
				if (setap6t212) {
					ap6t212.setValue(""+(ap6t211.getCourse()/ap6t213.getCourse()));
					current.setP6t21((ap6t211.getCourse()/ap6t213.getCourse()));
				}
				acbcourse.setValue("По курсу ЦБ: "+(ap6t211.getCourse()/ap6t213.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (ap4t21.getValue().equalsIgnoreCase(ap6t211.getValue())) {
					if (val1.equalsIgnoreCase(ap6t211.getValue())) {
						db = (ap7t21.doubleValue() + (ap8t21.doubleValue()/ap6t212.doubleValue()));
						bool = (ap5t21.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(ap6t211.getValue())) {
						db = (ap8t21.doubleValue() + (ap7t21.doubleValue()/ap6t212.doubleValue()));
						bool = (ap5t21.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else {
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма аккредитива не соответствует указанному курсу!"));
					}
				} else if (ap4t21.getValue().equalsIgnoreCase(ap6t213.getValue())) {
					if (val1.equalsIgnoreCase(ap6t213.getValue())) {
						db = (ap7t21.doubleValue() + (ap8t21.doubleValue()*ap6t212.doubleValue()));
						bool = (ap5t21.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(ap6t213.getValue())) {
						db = (ap8t21.doubleValue() + (ap7t21.doubleValue()*ap6t212.doubleValue()));
						bool = (ap5t21.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма аккредитива полность соответствует указанному курсу!":"Сумма аккредитива не соответствует указанному курсу!")+"("+db+")");
					} else {
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма аккредитива не соответствует указанному курсу!"));
					}
				} else {
					achecksum.setChecked(false);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/*
	public void onBlur$ap10t19() {
		onChange$ap10t18();
	}
	public void onOk$ap10t19() {
		onChange$ap10t18();
	}
	public void onChange$p4t19() {
		if (!CheckNull.isEmpty(p4t19.getValue())) {
			agar_date4_19.setVisible(false);
		} else {
			agar_date4_19.setVisible(true);
		}
	}

	public void onChange$p5t19() {
		if (!CheckNull.isEmpty(p5t19.getValue())) {
			agar_date3_19.setVisible(false);
		} else {
			agar_date3_19.setVisible(true);
		}
	}
*/
	
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.is.tf.Accreditiv.AccreditivService.remove(new Accreditiv() , idc);
		com.sbs.service.AccreditivesResult Pay = ws.getAccreditives(((String)session.getAttribute("BankINN")), idn);
		XMLSerializer.write(Pay, "c:/acr1.xml");
		try
		{
			if (Pay.getStatus()==0)
			{
				for (int i=0;i<Pay.getAccreditives().length;i++)
				{
					com.is.tf.Accreditiv.AccreditivService.create2(Pay.getAccreditives()[i], idn, idc) ;
				}
			} 
			else 
			{
				 alert("Ошибка при обновлении:"+Pay.getErrorMsg()+":  Status="+Pay.getStatus()+": GtkId="+Pay.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
				 ISLogger.getLogger().warn("ERROR refresh:"+Pay.getErrorMsg()+":  Status="+Pay.getStatus()+": GtkId="+Pay.getGtkId());
			}
		}
		catch (Exception e)
		{
			alert("Записи отсутствуют!");
			e.printStackTrace();
		}
		refreshModel(_startPageNumber);

	}
	
	private Window contractmain = null; 
	public void onClick$btn_confirmM() {
		sendConfirm(1, current.getP2t21(), "",current);
	}
	
	public void onClick$btn_rejectM() {
		sendConfirm(0, current.getP2t21(), "",current);
	}
	public void onClick$btn_confirm() {
		sendConfirm(1, current.getP2t21(), currentSum.getP11t23()+"", currentSum);
	}
	
	public void onClick$btn_reject() {
		sendConfirm(0, current.getP2t21(), currentSum.getP11t23()+"", currentSum);
	}
	
	public void onClick$btn_confirm2() {
		sendConfirm(1, current.getP2t21(), currentTime.getP7t22()+"", currentTime);
	}
	
	public void onClick$btn_reject2() {
		sendConfirm(0, current.getP2t21(), currentTime.getP7t22()+"", currentTime);
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
	private void sendConfirm2(int action, String docnum, Object obj) {
		if (contractmain==null){
		contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
		
		//contractmain = (Window) session.getAttribute("contractmain");
		}
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("inn", ((String)session.getAttribute("BankINN")));
		params.put("idn", idn);
		params.put("action", action+"");
		params.put("docnum", docnum);
		params.put("obj", obj);
		Events.sendEvent("onConfirmDocument", contractmain, params);
	}
}
