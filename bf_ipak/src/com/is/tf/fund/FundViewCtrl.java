package com.is.tf.fund;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
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
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.tf.contract.ContractService;
import com.is.tf.currency.RefCurrencyBox;
import com.is.tf.currency.RefCurrencyData;
import com.is.tf.generalpayments.Tf_general_payment;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.FundResult;

public class FundViewCtrl extends GenericForwardComposer{
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
    private Toolbarbutton btn_back,btn_edit,btn_edit2,btn_delete,btn_confirm,btn_reject,btn_save,btn_confirmBuhg,btn_rejectBuhg;
    private Toolbar tb;
    private Textbox p20t35,aid_contract,id,p0t35,p2t35,p3t35,p6t35,p8t35,p23t35,p24t35,p25t35,p100t35;
    private Textbox aid,ap0t35,ap2t35,ap3t35,ap6t35,ap8t35,ap23t35,ap24t35,ap25t35,ap20t35,ap100t35;
    private Textbox fid,fp0t35,fp2t35,fp3t35,fp6t35,fp8t35, fp10t35, fp11t35, fp12t35,fp23t35,fp24t35,fp25t35,fp100t35,fp20t35;
    private Datebox fp26t35,ap26t35,p26t35,p4t35,p7t35,ap4t35,ap7t35,fp4t35,fp7t35;
    private Decimalbox p14t35,p15t35,p16t35,p17t35,p18t35,p19t35,p21t35,ap14t35,ap15t35,ap16t35,ap17t35,ap18t35,ap19t35,ap21t35,fp14t35,fp15t35,fp16t35,fp17t35,fp18t35,fp19t35,fp21t35;
    private Label p1t35,ap1t35,fp1t35;
    private Paging fundPaging;
   
    private int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private RefCBox p5t35, ap5t35, fp5t35;
    private RefCBox p9t35, ap9t35, fp9t35;
    private RefCBox p10t35, ap10t35;
    private RefCBox p11t35, ap11t35;
    private RefCBox p12t35, ap12t35;
    private RefCBox p13t35,ap13t35,fp13t35;
    private RefCurrencyBox p15t351,p15t353, ap15t351, ap15t353;
    private Checkbox checksum, achecksum;
    private Row rowp25t35,rowp26t35,rowp6t35, rowp7t35, rowp8t35, rowp10t35, rowp11t35, rowp12t35, rowp16t35, rowp17t35, rowp18t35, rowp19t35, rowp20t35, rowp21t35, rowp2t35, rowp3t35; 
    private Row arowp6t35, arowp7t35, arowp8t35, arowp10t35, arowp11t35, arowp12t35, arowp16t35, arowp17t35, arowp18t35, arowp19t35, arowp20t35, arowp21t35, arowp2t35, arowp3t35;
    private Row kur, akur;
    private List<RefData> funddest = new ArrayList<RefData>();
    private HashMap< String,String> curr_ =null;
    private List<RefData> conditions = new ArrayList<RefData>();
    private List<RefData> accredetivs = new ArrayList<RefData>();
    private List<RefData> garants = new ArrayList<RefData>();
    private List<RefData> policies = new ArrayList<RefData>();
    private List<RefData> currencies = new ArrayList<RefData>();
    private List<RefData> accrcurrencies = new ArrayList<RefData>();
    private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
    private Long idc,id_contract;
    private String otn2,aotn,otn,idn, subj, val1, val2,summa1,summa2,val22,val23;
    private String sparam1,sparam;
    private Label acbcourse,cbcourse,conr_val1,conr_val2,aconr_val2,aconr_val1,conr_val21,conr_val22,aconr_val21,aconr_val22;
    public FundFilter filter = new FundFilter();
    private Label line1;						
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;
    private Label line9;
    
    private Row abankgprow;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Fund current = new Fund();

    public FundViewCtrl() {
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
    parameter = (String[]) param.get("idn");
    if (parameter!=null){
    	idn = (parameter[0]);
    	// System.out.println("Garant  cont_idn "+idn);   
    	ap1t35.setValue(idn);
    	fp1t35.setValue(idn);
    }
    parameter = (String[]) param.get("val1");
    if (parameter!=null){
	 	val1 = (parameter[0]);
	    // System.out.println("Garant  cont_val1 "+val1);   
	 }
    parameter = (String[]) param.get("val2");
    if (parameter!=null){
	 	val2 = (parameter[0]);
	   //   System.out.println("Garant  cont_val2 "+val2);   
    }
    parameter = (String[]) param.get("subj");
    if (parameter!=null){
    	subj = (parameter[0]);
	   //   System.out.println("Garant  cont_val2 "+val2);   
    }
    parameter = (String[]) param.get("idc");
    if (parameter!=null){
 	idc = Long.parseLong((parameter[0]));
   //   System.out.println("Garant  cont_val2 "+val2);   
 }
    parameter = (String[]) param.get("summa1");
    if (parameter!=null){
 	summa1 = (parameter[0]);
     
 }
    parameter = (String[]) param.get("summa2");
    if (parameter!=null){
 	summa2 = (parameter[0]);
    //System.out.println("Contract summa2 "+summa2);   
 }
    parameter = (String[]) param.get("spr");
    if (parameter!=null){
    	sparam1 = (parameter[0]);
 }
    alias = (String)session.getAttribute("alias");
    funddest = ContractService.getFundDest("1,2", alias);
    conditions = ContractService.getConditions("1,2,3,4,5,6", alias);
    accredetivs = ContractService.getAccredetivs(idn, alias);
    garants = ContractService.getGarants(idn, alias);
    policies = ContractService.getPolicies(idn, alias);
    currencies = ContractService.getCurr_22t1_23t1(idn, alias);
    
    p5t35.setModel(new ListModelList(funddest));
    ap5t35.setModel(new ListModelList(funddest));
    fp5t35.setModel(new ListModelList(funddest));
    p9t35.setModel(new ListModelList(conditions)); 
    ap9t35.setModel(new ListModelList(conditions)); 
    fp9t35.setModel(new ListModelList(conditions));
    p10t35.setModel(new ListModelList(accredetivs)); 
    ap10t35.setModel(new ListModelList(accredetivs)); 
    p11t35.setModel(new ListModelList(garants)); 
    ap11t35.setModel(new ListModelList(garants)); 
    p12t35.setModel(new ListModelList(policies)); 
    ap12t35.setModel(new ListModelList(policies)); 
    
    p13t35.setModel((new ListModelList(currencies)));
    ap13t35.setModel((new ListModelList(currencies)));
    fp13t35.setModel((new ListModelList(currencies)));
    curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
    conr_val2.setValue(curr_.get(val2));
    conr_val1.setValue(curr_.get(val1));
    conr_val22.setValue(curr_.get(val2));
    conr_val21.setValue(curr_.get(val1));
    aconr_val2.setValue(curr_.get(val2));
    aconr_val1.setValue(curr_.get(val1));
    aconr_val22.setValue(curr_.get(val2));
    aconr_val21.setValue(curr_.get(val1));
    
    line1.setValue(Labels.getLabel("fund.p4t35tab"  ).replaceAll("<br>", "\r\n")); 
    line2.setValue(Labels.getLabel("fund.p9t35tab"  ).replaceAll("<br>", "\r\n")); 
    line3.setValue(Labels.getLabel("fund.p14t35tab" ).replaceAll("<br>", "\r\n")); 
    line4.setValue(Labels.getLabel("fund.p13t35tab" ).replaceAll("<br>", "\r\n")); 
    line5.setValue(Labels.getLabel("fund.p3t35tab"  ).replaceAll("<br>", "\r\n")); 
    line6.setValue(Labels.getLabel("fund.p2t35tab"  ).replaceAll("<br>", "\r\n")); 
    line7.setValue(Labels.getLabel("fund.p20t35tab" ).replaceAll("<br>", "\r\n")); 
    line8.setValue(Labels.getLabel("fund.p26t35tab" ).replaceAll("<br>", "\r\n"));	
    line9.setValue(Labels.getLabel("fund.p100t35tab").replaceAll("<br>", "\r\n"));	
    
    dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Fund pFund = (Fund) data;

                row.setValue(pFund);
                
                row.appendChild(new Listcell(pFund.getP4t35()+""));
                row.appendChild(new Listcell(pFund.getP9t35()+""));
                row.appendChild(new Listcell(pFund.getP14t35()+""));
                row.appendChild(new Listcell(pFund.getP13t35()+""));
                row.appendChild(new Listcell(pFund.getP3t35()+""));
                row.appendChild(new Listcell(pFund.getP2t35()+""));
                row.appendChild(new Listcell(pFund.getP20t35()+""));
                row.appendChild(new Listcell(pFund.getP26t35()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pFund.getP100t35()+""))));
                
                /*row.appendChild(new Listcell(pFund.getId()+""));
                row.appendChild(new Listcell(pFund.getP0t35()));
                row.appendChild(new Listcell(pFund.getP1t35()));
                row.appendChild(new Listcell(pFund.getP2t35()));
                row.appendChild(new Listcell(pFund.getP3t35()));
                row.appendChild(new Listcell(pFund.getP4t35()+""));
                row.appendChild(new Listcell(pFund.getP5t35()));
                row.appendChild(new Listcell(pFund.getP6t35()));
                row.appendChild(new Listcell(pFund.getP7t35()+""));
                row.appendChild(new Listcell(pFund.getP8t35()));
                row.appendChild(new Listcell(pFund.getP9t35()));
                row.appendChild(new Listcell(pFund.getP10t35()));
                row.appendChild(new Listcell(pFund.getP11t35()));
                row.appendChild(new Listcell(pFund.getP12t35()));
                row.appendChild(new Listcell(pFund.getP13t35()));
                row.appendChild(new Listcell(pFund.getP14t35()+""));
                row.appendChild(new Listcell(pFund.getP15t35()+""));
                row.appendChild(new Listcell(pFund.getP16t35()+""));
                row.appendChild(new Listcell(pFund.getP17t35()+""));
                row.appendChild(new Listcell(pFund.getP18t35()+""));
                row.appendChild(new Listcell(pFund.getP19t35()+""));
                row.appendChild(new Listcell(pFund.getP20t35()+""));
                row.appendChild(new Listcell(pFund.getP23t35()));
                row.appendChild(new Listcell(pFund.getP25t35()));
                row.appendChild(new Listcell(pFund.getP26t35()+""));
                row.appendChild(new Listcell(pFund.getP100t35()));*/
    }});
     
    if (sparam1 != null)
    {
     	  if (sparam1.equals("Go"))   /// ГО
     	  {
     		 btn_add.setVisible(false);
     	  }
    }
    refreshModel(_startPageNumber);

}

public void onPaging$fundPaging(ForwardEvent event){
	final PagingEvent pe = (PagingEvent) event.getOrigin();
	_startPageNumber = pe.getActivePage();
	refreshModel(_startPageNumber);
}

	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
	}


	private void refreshModel(int activePage)
	{
	    /*
		
		fundPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		
		if (model.getSize()>0)
		{
			dataGrid.setSelectedIndex(0);
			sendSelEvt(true);
			this.current =(Fund) model.getElementAt(0);
			sendSelEvt(true);
		}*/
		
		filter.setP1t35(idn);
	    //filter.setId_contract(idc);
		fundPaging.setPageSize(_pageSize);
	    model = new PagingListModel(activePage, _pageSize, filter, "");
	
		if(_needsTotalSizeUpdate) _totalSize = model.getTotalSize(filter, "");
		fundPaging.setTotalSize(_totalSize);
	
		dataGrid.setModel((ListModel) model);
		if (model.getSize()>0)
		{
			dataGrid.setSelectedIndex(0);
			this.current =(Fund) model.getElementAt(0);
			sendSelEvt(true);
		}
	}
	
	

public void onSelect$dataGrid$grd() {
	sendSelEvt(false);
}

//Omitted...
public Fund getCurrent() {
return current;
}

public void setCurrent(Fund current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() {
	btn_save.setVisible(false);
	btn_delete.setVisible(false);
	if (sparam1!=null){
		if (sparam1.equals("Filial"))  ///  Филиал
		    {
	    	btn_edit.setVisible(true);
	    	btn_edit2.setVisible(false);
	    	btn_confirmBuhg.setVisible(false);
	    	btn_rejectBuhg.setVisible(false);
	    	sparam1="Filial";
	    	//alert(sparam1);
	}
	  else if (sparam1.equals("Go"))   /// ГО
	  {
		btn_edit.setVisible(false);
	  	btn_edit2.setVisible(true);
	  	btn_confirmBuhg.setVisible(false);
	  	btn_rejectBuhg.setVisible(false);
	  	sparam1="Go";
	  	//alert(sparam1);
	}
	  else if (sparam1.equals("GlBuhg"))   /// Гл бухг
	{
		btn_edit.setVisible(false);
	    btn_edit2.setVisible(false);
	    btn_confirmBuhg.setVisible(true);
	    btn_rejectBuhg.setVisible(true);
	    sparam1="GlBuh";
	    //alert(sparam1);
	      }
	   } 
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
            p2t35.setReadonly(true);
            p3t35.setReadonly(true);
    	    p4t35.setDisabled(true);
    	    p5t35.setDisabled(true);
    	    p6t35.setReadonly (true);
    	    p7t35.setDisabled(true);
    	    p8t35.setReadonly(true);
    	    p9t35.setDisabled(true);
    	    p10t35.setDisabled(true);
    	    p11t35.setDisabled(true);
    	    p12t35.setDisabled(true);
    	    p13t35.setDisabled(true);
    	    p14t35.setReadonly(true);
    	    p16t35.setReadonly(true);
    	    p17t35.setReadonly(true);
    	    p15t35.setReadonly(true);
    	    p18t35.setReadonly(true);
    	    p19t35.setReadonly(true);
    	    p20t35.setReadonly(true);
    	    if ((current.getP25t35()!=null)&&(current.getP25t35()!="")) {
    	    p25t35.setReadonly(true);
    	    rowp25t35.setVisible(true);
    	    } else {
    	    	rowp25t35.setVisible(false);
    	    }
    	    if (current.getP26t35()!=null){
    	    p26t35.setDisabled(true);
    	    rowp26t35.setVisible(true);
    	    } else {
    	    	rowp26t35.setVisible(false);
    	    }
  
}

public void onClick$btn_edit() {
	
	if (frmgrd.isVisible()){
		btn_save.setVisible(true);
		btn_delete.setVisible(true);
		btn_confirm.setVisible(false);
		btn_reject.setVisible(false);
		btn_edit2.setVisible(false);
		p2t35.setReadonly(false);
        p3t35.setReadonly(false);
 	    p4t35.setDisabled(false);
 	    p5t35.setDisabled(false);
 	    p6t35.setReadonly (false);
 	    p7t35.setDisabled(false);
 	    p8t35.setReadonly(false);
 	    p9t35.setDisabled(false);
 	    p10t35.setDisabled(false);
 	    p11t35.setDisabled(false);
 	    p12t35.setDisabled(false);
 	    p13t35.setDisabled(false);
 	    p14t35.setReadonly(false);
 	    p16t35.setReadonly(false);
 	   p17t35.setReadonly(false);
 	    p15t35.setReadonly(false);
 	    p18t35.setReadonly(false);
 	    p19t35.setReadonly(false);
 	    p20t35.setReadonly(false);
 	    if ((current.getP25t35()!=null)&&(current.getP25t35()!="")) {
 	    p25t35.setReadonly(false);
 	    rowp25t35.setVisible(true);
 	    } else {
 	    	rowp25t35.setVisible(false);
 	    }
 	    if (current.getP26t35()!=null){
 	    p26t35.setDisabled(false);
 	    rowp26t35.setVisible(true);
 	    } else {
 	    	rowp26t35.setVisible(false);
 	    }
	}else
		if (addgrd.isVisible()){
			btn_save.setVisible(true);
			btn_delete.setVisible(false);
			btn_confirm.setVisible(false);
			btn_reject.setVisible(false);
			btn_edit.setVisible(false);
			btn_edit2.setVisible(false);
		}
	
}
public void onClick$btn_edit2() {
	
	if (frmgrd.isVisible()){
		btn_save.setVisible(false);
		btn_delete.setVisible(false);
		btn_confirm.setVisible(true);
		btn_reject.setVisible(true);
		btn_edit2.setVisible(false);
		p2t35.setReadonly(true);
        p3t35.setReadonly(true);
 	    p4t35.setDisabled(true);
 	    p5t35.setDisabled(true);
 	    p6t35.setReadonly (true);
 	    p7t35.setDisabled(true);
 	    p8t35.setReadonly(true);
 	    p9t35.setDisabled(true);
 	    p10t35.setDisabled(true);
 	    p11t35.setDisabled(true);
 	    p12t35.setDisabled(true);
 	    p13t35.setDisabled(true);
 	    p14t35.setReadonly(true);
 	    p16t35.setReadonly(true);
 	   p17t35.setReadonly(true);
 	    p15t35.setReadonly(true);
 	    p18t35.setReadonly(true);
 	    p19t35.setReadonly(true);
 	    p20t35.setReadonly(true);
 	    if ((current.getP25t35()!=null)&&(current.getP25t35()!="")) {
 	    p25t35.setReadonly(true);
 	    rowp25t35.setVisible(true);
 	    } else {
 	    	rowp25t35.setVisible(false);
 	    }
 	    if (current.getP26t35()!=null){
 	    p26t35.setDisabled(true);
 	    rowp26t35.setVisible(true);
 	    } else {
 	    	rowp26t35.setVisible(false);
 	     }
	  }
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
    sendSelEvt(true);
}
public void onClick$btn_last() {
    dataGrid.setSelectedIndex(model.getSize()-1);
    sendSelEvt(true);
}
public void onClick$btn_prev() {
    if (dataGrid.getSelectedIndex()!=0){
    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
    sendSelEvt(true);
    }
}
public void onClick$btn_next() {
    if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
    dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
    sendSelEvt(true);
    }
}



private void sendSelEvt(Boolean sendEvt){
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
    if (sendEvt) {
	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	    Events.sendEvent(evt);
    }
    if (current != null) {
    	if (current.getP5t35().equalsIgnoreCase("2")){
    		rowp2t35.setVisible(false);
        	rowp3t35.setVisible(false);
        	rowp6t35.setVisible(true);
			rowp7t35.setVisible(true);
			rowp8t35.setVisible(true);
		} else {
			rowp2t35.setVisible(true);
	    	rowp3t35.setVisible(true);
	    	rowp6t35.setVisible(false);
			rowp7t35.setVisible(false);
			rowp8t35.setVisible(false);
		}
    	rowp10t35.setVisible(false); 
		rowp11t35.setVisible(false); 
		rowp12t35.setVisible(false);
		if (current.getP9t35().equalsIgnoreCase("2")){
			rowp10t35.setVisible(true); 
		} else if (current.getP9t35().equalsIgnoreCase("3")) {
			rowp11t35.setVisible(true); 
		} else if (current.getP9t35().equalsIgnoreCase("4")) {
			rowp12t35.setVisible(true);
		}
		if (CheckNull.isEmpty(current.getP13t35())) {
			if (current.getP9t35().equalsIgnoreCase("2")){
				accrcurrencies = ContractService.getCurrOfAccreditiv(idn, p10t35.getValue(), alias);
				if (accrcurrencies.size() > 0) {
					p13t35.setSelecteditem(accrcurrencies.get(0).getData());
					current.setP13t35(p13t35.getValue());
				}
			} else {
				p13t35.setSelectedIndex(0);
				current.setP13t35(p13t35.getValue());
			}
		} else {
			p13t35.setSelecteditem(current.getP13t35());
			//current.setP13t35(p13t35.getValue());
		}
		coursecurrencies = ContractService.getCourseCurr_22t1_23t1(idn, df.format(current.getP4t35()), alias);
		p15t351.setModel((new ListModelList(coursecurrencies)));
		p15t353.setModel((new ListModelList(coursecurrencies)));
		if (coursecurrencies.size() < 1){
			if (CheckNull.isEmpty(val2)
				|| (val1.equalsIgnoreCase("000")?"860":val1).equalsIgnoreCase(val2.equalsIgnoreCase("000")?"860":val2)){
				//|| current.getP5t18().equalsIgnoreCase(currenciesg.get(0).getKod())
				kur.setVisible(false);
			} else {       
				kur.setVisible(true);
				//p7t183.setSelecteditem(current.getP5t18());
				//onSelect$p5t18();
			}
		}
		
		rowp16t35.setVisible(false); 
		rowp17t35.setVisible(false);
		rowp18t35.setVisible(false);
		rowp19t35.setVisible(false);
		//rowp20t35.setVisible(false);
		//rowp21t35.setVisible(false);
	    if (subj.equalsIgnoreCase("1") || subj.equalsIgnoreCase("3")) {
			rowp16t35.setVisible(true); 
			rowp17t35.setVisible(true);
		} 
	    if (subj.equalsIgnoreCase("2") || subj.equalsIgnoreCase("3")) {
			rowp18t35.setVisible(true);
			rowp19t35.setVisible(true);
	    }
	    /*
	    if (subj.equalsIgnoreCase("3")) {
			rowp20t35.setVisible(true);
			rowp21t35.setVisible(true);
		}
		*/
    }
}

public void onClick$btn_add() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    btn_save.setVisible(true);
    btn_edit.setVisible(false);
    btn_edit2.setVisible(false);
    fgrd.setVisible(false);
    //onSelect$ap5t35();
    ap1t35.setValue(idn);
    ap20t35.setValue((String)session.getAttribute("un"));
    ap4t35.setValue(new Date());
    coursecurrencies = ContractService.getCourseCurr_22t1_23t1(idn, df.format(ap4t35.getValue()), alias);
	ap15t351.setModel((new ListModelList(coursecurrencies)));
	ap15t353.setModel((new ListModelList(coursecurrencies)));
	if (coursecurrencies.size() < 1){
		if (CheckNull.isEmpty(val2)
			|| (val1.equalsIgnoreCase("000")?"860":val1).equalsIgnoreCase(val2.equalsIgnoreCase("000")?"860":val2)){
			//|| current.getP5t18().equalsIgnoreCase(currenciesg.get(0).getKod())
			akur.setVisible(false);
		} else {       
			akur.setVisible(true);
			//p7t183.setSelecteditem(current.getP5t18());
			//onSelect$p5t18();
		}
	}
	arowp2t35.setVisible(false);
	arowp3t35.setVisible(false);
	arowp16t35.setVisible(false); 
	arowp17t35.setVisible(false);
	arowp18t35.setVisible(false);
	arowp19t35.setVisible(false);
	
	
    if (subj.equalsIgnoreCase("1") || subj.equalsIgnoreCase("3")) {
		arowp16t35.setVisible(true); 
		arowp17t35.setVisible(true);
	} 
    if (subj.equalsIgnoreCase("2") || subj.equalsIgnoreCase("3")) {
		arowp18t35.setVisible(true);
		arowp19t35.setVisible(true);
    }
    
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}

public void onClick$btn_delete() {
    try{
    	final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
    	com.sbs.service.PaymentResult pay =  new com.sbs.service.PaymentResult();
    	 if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)>p14t35.doubleValue())){
		    	otn="0";
		    } else if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)<p14t35.doubleValue())){
		    	otn="1";
		    }
    	  Long.parseLong(id.getValue());
          //current.setP1t44(p1t44.getValue());
          //current.setP0t44(p0t44.getValue());
          //current.setP1t35(p1t35.getValue());
          current.setP0t35(p0t35.getValue());
          current.setP2t35(p2t35.getValue());
          current.setP3t35(p3t35.getValue());
          current.setP4t35(p4t35.getValue());
          current.setP5t35(p5t35.getValue());
          current.setP6t35(p6t35.getValue());
          current.setP7t35(p7t35.getValue());
          current.setP8t35(p8t35.getValue());
          current.setP9t35(p9t35.getValue());
          current.setP10t35(p10t35.getValue());
          current.setP11t35(p11t35.getValue());
          current.setP12t35(p12t35.getValue());
          current.setP13t35(p13t35.getValue());
          current.setP14t35(p14t35.doubleValue());
          current.setP15t35(p15t35.doubleValue());
          current.setP16t35(p16t35.doubleValue());
          current.setP17t35(p17t35.doubleValue());
          current.setP18t35(p18t35.doubleValue());
          current.setP19t35(p19t35.doubleValue());
          current.setP20t35((String)session.getAttribute("un"));
         //current.setP24t44((String)session.getAttribute("un"));
      if (current.getP100t35().equals("9")){
    	  Res res2=FundService.removeBugh(current, idc);
          if (res2.getCode()==0) {
         	 alert("Удаление произошла успешно");
         	refreshModel(_startPageNumber);
          } else {
         	 alert("Ошибка удалении:"+res2.getName()+":"+res2.getCode());
          }
      } else {
         
    	  Res res3=FundService.updateDel(current);
    	  if (res3.getCode()==0) {
          	 alert("Удаление произошла успешно");
          	refreshModel(_startPageNumber);
           } else {
          	 alert("Ошибка удалении:"+res3.getName()+":"+res3.getCode());
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



public void onClick$btn_save() {
	id_contract=idc;
try {
	if(addgrd.isVisible()){
		
		Res res=FundService.createBuhg(new Fund (
			    ap3t35.getValue(),
	            ap4t35.getValue(),
	            ap5t35.getValue(),
	            ap6t35.getValue(),
	            ap7t35.getValue(),
	            ap8t35.getValue(),
	            ap9t35.getValue(),
	            ap10t35.getValue(),
	            ap11t35.getValue(),
	            ap12t35.getValue(),
	            ap13t35.getValue(),
	            ap14t35.doubleValue(),
	            ap15t35.doubleValue(),
	            ap16t35.doubleValue(),
	            ap17t35.doubleValue(),
	            ap18t35.doubleValue(),
	            ap19t35.doubleValue(),
	            ap20t35.getValue()
	            
	), idn, id_contract);
	 
	CheckNull.clearForm(addgrd);
	 if (res.getCode()==0) 
	 {
		 refreshModel(_startPageNumber);
		 alert("Сохранение произошла успешно");
		 onClick$btn_back();
		 refresh(idn);
     } else 
     {
    	 alert("Ошибка:"+res.getName()+res.getCode());
     }
	 frmgrd.setVisible(true);
     addgrd.setVisible(false);
     fgrd.setVisible(false);
	 
	} else{
     	 
		if (current.getP2t35()!=null) {
		
		if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)>p14t35.doubleValue())){
		    	otn="0";
		    } else if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)<p14t35.doubleValue())){
		    	otn="1";
		    }
   	 //alert("summa1="+summa1+"  p16t44.doubleValue()= "+p16t44.doubleValue()+"  otn= "+otn+" p15t44.getValue()="+current.getP15t44());
          Long.parseLong(id.getValue());
          current.setP2t35(p2t35.getValue());
          current.setP3t35(p3t35.getValue());
          current.setP4t35(p4t35.getValue());
          current.setP5t35(p5t35.getValue());
          current.setP6t35(p6t35.getValue());
          current.setP7t35(p7t35.getValue());
          current.setP8t35(p8t35.getValue());
          current.setP9t35(p9t35.getValue());
          current.setP10t35(p10t35.getValue());
          current.setP11t35(p11t35.getValue());
          current.setP12t35(p12t35.getValue());
          current.setP13t35(p13t35.getValue());
          current.setP14t35(p14t35.doubleValue());
          current.setP15t35(p15t35.doubleValue());
          current.setP16t35(p16t35.doubleValue());
          current.setP17t35(p17t35.doubleValue());
          current.setP18t35(p18t35.doubleValue());
          current.setP19t35(p19t35.doubleValue());
          current.setP20t35((String)session.getAttribute("un"));
          Res res=FundService.updateCorrect(current);
          if (res.getCode()==0) {
         	 alert("Корректировка произошла успешно");
         	 fundPaging.setActivePage(0);
         	 _startPageNumber=0;
         	 refreshModel(_startPageNumber);
         	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
         	    Events.sendEvent(evt);
          } else {
         	 alert("Ошибка:"+res.getName());
          }
          
		} else{
      	 if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)>p14t35.doubleValue())){
		    	otn="0";
		    } else if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)<p14t35.doubleValue())){
		    	otn="1";
		    }
      	 
      	 	 Long.parseLong(id.getValue());
             current.setP2t35(p2t35.getValue());
             current.setP3t35(p3t35.getValue());
             current.setP4t35(p4t35.getValue());
             current.setP5t35(p5t35.getValue());
             current.setP6t35(p6t35.getValue());
             current.setP7t35(p7t35.getValue());
             current.setP8t35(p8t35.getValue());
             current.setP9t35(p9t35.getValue());
             current.setP10t35(p10t35.getValue());
             current.setP11t35(p11t35.getValue());
             current.setP12t35(p12t35.getValue());
             current.setP13t35(p13t35.getValue());
             current.setP14t35(p14t35.doubleValue());
             current.setP15t35(p15t35.doubleValue());
             current.setP16t35(p16t35.doubleValue());
             current.setP17t35(p17t35.doubleValue());
             current.setP18t35(p18t35.doubleValue());
             current.setP19t35(p19t35.doubleValue());
             current.setP20t35((String)session.getAttribute("un"));
             
             Res res=FundService.update(current);
             
             if (res.getCode()==0) {
            	 alert("Корректировка произошла успешно");
            	 fundPaging.setActivePage(0);
            	 _startPageNumber=0;
            	 refreshModel(_startPageNumber);
            	    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
            	    Events.sendEvent(evt);
             } else {
            	 alert("Ошибка:"+res.getName());
             }
             
	   }       
	}
	
} catch (Exception e) {
    e.printStackTrace();
    System.out.println("Error save   "+e+"id_contract "+id_contract);
}

}



public void onClick$btn_confirmBuhg() {
try{
   
	final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
	com.sbs.service.FundResult fund =  new com.sbs.service.FundResult();
	
	if (fgrd.isVisible()){
        filter = new FundFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP1t35(fp1t35.getValue());
          filter.setP0t35(fp0t35.getValue());
          filter.setP2t35(fp2t35.getValue());
          filter.setP3t35(fp3t35.getValue());
          filter.setP4t35(fp4t35.getValue());
          filter.setP5t35(fp5t35.getValue());
          filter.setP6t35(fp6t35.getValue());
          filter.setP7t35(fp7t35.getValue());
          filter.setP8t35(fp8t35.getValue());
          filter.setP9t35(fp9t35.getValue());
          filter.setP10t35(fp10t35.getValue());
          filter.setP11t35(fp11t35.getValue());
          filter.setP12t35(fp12t35.getValue());
          filter.setP13t35(fp13t35.getValue());
          filter.setP14t35(fp14t35.doubleValue());
          filter.setP15t35(fp15t35.doubleValue());
          filter.setP16t35(fp16t35.doubleValue());
          filter.setP17t35(fp17t35.doubleValue());
          filter.setP18t35(fp18t35.doubleValue());
          filter.setP19t35(fp19t35.doubleValue());
          filter.setP20t35(fp20t35.getValue());
          filter.setP21t35(fp21t35.doubleValue());

     } else if(frmgrd.isVisible()&&(current.getP2t35()!=null)&&(current.getP0t35().equals(1))){
	
     }
	
	
	
	
	
	
	
	
	else{
    	if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)>p14t35.doubleValue())){
	    	otn="0";
	    } else if (!CheckNull.isEmpty(p14t35.doubleValue())&&(Double.parseDouble(summa1)<p14t35.doubleValue())){
	    	otn="1";
	    }
          Long.parseLong(id.getValue());
          //current.setP1t35(p1t35.getValue());
          //current.setP0t35(p0t35.getValue());
          current.setP2t35(p2t35.getValue());
          current.setP3t35(p3t35.getValue());
          current.setP4t35(p4t35.getValue());
          current.setP5t35(p5t35.getValue());
          current.setP6t35(p6t35.getValue());
          current.setP7t35(p7t35.getValue());
          current.setP8t35(p8t35.getValue());
          current.setP9t35(p9t35.getValue());
          current.setP10t35(p10t35.getValue());
          current.setP11t35(p11t35.getValue());
          current.setP12t35(p12t35.getValue());
          current.setP13t35(p13t35.getValue());
          current.setP14t35(p14t35.doubleValue());
          current.setP15t35(p15t35.doubleValue());
          current.setP16t35(p16t35.doubleValue());
          current.setP17t35(p17t35.doubleValue());
          current.setP18t35(p18t35.doubleValue());
          current.setP19t35(p19t35.doubleValue());
          current.setP20t35(p20t35.getValue());
          //current.setP21t35(p21t35.doubleValue());
        //FundService.update(current);
          
          FundResult ar = ws.saveFund(((String)(session.getAttribute("BankINN"))), idn , getBuhgNewFund(current));
          if (ar.getStatus() == 0) {
        	  FundService.removeBugh(current, idc);
        	  refreshModel(_startPageNumber);
        	  alert("Сохранение успешно");
        	  
          } else {
        	  alert("Ошибка подтверждение Гл.Бухг:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
        	  System.out.println("Ошибка подтверждение Гл.Бухг:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
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
            filter = new FundFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}
private com.sbs.service.Fund getBuhgNewFund(Fund fun){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.Fund res = new com.sbs.service.Fund();
	  res.setP0T35(0);
	 // res.setP2T35(fun.getP2t35());
	  res.setP3T35(fun.getP3t35());
	  cal.setTime(fun.getP4t35());
	  res.setP4T35(cal);
	  res.setP5T35(Short.parseShort(fun.getP5t35()));
	  if ((fun.getP6t35()!=null)&&(!CheckNull.isEmpty(fun.getP6t35()))){
	  res.setP6T35(fun.getP6t35());
	  }
	  if ((fun.getP7t35()!=null)&&(!CheckNull.isEmpty(fun.getP7t35()))){
	  cal2.setTime(fun.getP7t35());
	  res.setP7T35(cal2);
	  }
	  if ((fun.getP8t35()!=null)&&(!CheckNull.isEmpty(fun.getP8t35()))){
	  res.setP8T35(fun.getP8t35());
	  }
	  res.setP9T35(Short.parseShort(fun.getP9t35()));
	  if ((fun.getP10t35()!=null)&&(!CheckNull.isEmpty(fun.getP10t35()))){
	  res.setP10T35(fun.getP10t35());
	  }
	  if ((fun.getP11t35()!=null)&&(!CheckNull.isEmpty(fun.getP11t35()))){
	  res.setP11T35(fun.getP11t35());
	  }
	  if ((fun.getP12t35()!=null)&&(!CheckNull.isEmpty(fun.getP12t35()))){
	  res.setP12T35(fun.getP12t35());
	  }
	  res.setP13T35(fun.getP13t35());
	  res.setP14T35(fun.getP14t35());
	  res.setP15T35(fun.getP15t35());
	  if ((fun.getP16t35()!=null)&&(fun.getP16t35()!=0.000)){
	  res.setP16T35(fun.getP16t35());
	  }
	  if ((fun.getP17t35()!=null)&&(fun.getP17t35()!=0.000)){
	  res.setP17T35(fun.getP17t35());
	  }
	  if ((fun.getP18t35()!=null)&&(fun.getP18t35()!=0.000)){
	  res.setP18T35(fun.getP18t35());
	  }
	  if ((fun.getP19t35()!=null)&&(fun.getP19t35()!=0.000)){
	  res.setP19T35(fun.getP19t35());
	  }
	  res.setP20T35(fun.getP20t35());
	  res.setP23T35(Short.parseShort(otn));
	  res.setP25T35((String)session.getAttribute("un"));
	  if (fun.getP100t35().equals("9")){
		  res.setP100T35(null);
	  }
	  return res;}
//--------------------------------------
private com.sbs.service.Fund getDelete(Fund fun){
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  com.sbs.service.Fund res = new com.sbs.service.Fund();
	  res.setP0T35(2);
	 // res.setP2T35(fun.getP2t35());
	  res.setP3T35(fun.getP3t35());
	  cal.setTime(fun.getP4t35());
	  res.setP4T35(cal);
	  res.setP5T35(Short.parseShort(fun.getP5t35()));
	  res.setP6T35(fun.getP6t35());
	  cal2.setTime(fun.getP7t35());
	  res.setP7T35(cal2);
	  res.setP8T35(fun.getP8t35());
	  res.setP9T35(Short.parseShort(fun.getP9t35()));
	  res.setP10T35(fun.getP10t35());
	  res.setP11T35(fun.getP11t35());
	  res.setP12T35(fun.getP12t35());
	  res.setP13T35(fun.getP13t35());
	  res.setP14T35(fun.getP14t35());
	  res.setP15T35(fun.getP15t35());
	  res.setP16T35(fun.getP16t35());
	  res.setP17T35(fun.getP17t35());
	  res.setP18T35(fun.getP18t35());
	  res.setP19T35(fun.getP19t35());
	  res.setP20T35(fun.getP20t35());
	  res.setP23T35(Short.parseShort(otn));
	  res.setP25T35((String)session.getAttribute("un"));
	  if (fun.getP100t35().equals("9")){
		  res.setP100T35(null);
	  }
	  return res;}
//----------------------------------------
private com.sbs.service.Fund getFund(Fund fun) throws Exception
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.Fund res = new com.sbs.service.Fund();
	  
	  res.setP0T35(0);
	  cal.setTime(df.parse(df.format(fun.getP4t35())));
	  res.setP4T35(cal);
	  res.setP5T35(Short.parseShort(fun.getP5t35()));
	  res.setP6T35(fun.getP6t35());
	  cal.setTime(df.parse(df.format(fun.getP7t35())));
	  res.setP7T35(cal);
	  res.setP8T35(fun.getP8t35());
	  res.setP9T35(Short.parseShort(fun.getP9t35()));
	  res.setP10T35(fun.getP10t35());
	  res.setP11T35(fun.getP11t35());
	  res.setP12T35(fun.getP12t35());
	  res.setP13T35(fun.getP13t35());
	  res.setP14T35(fun.getP14t35());
	  res.setP15T35(fun.getP15t35());
	  res.setP16T35(fun.getP16t35());
	  res.setP17T35(fun.getP17t35());
	  res.setP18T35(fun.getP18t35());
	  res.setP19T35(fun.getP19t35());
	  res.setP20T35((String)session.getAttribute("ufn"));
	  return res;
}

private com.sbs.service.Fund getFundCorr(Fund fun) throws Exception
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  com.sbs.service.Fund res = new com.sbs.service.Fund();
	  
	  res.setP0T35(1);
	  cal.setTime(df.parse(df.format(fun.getP4t35())));
	  res.setP4T35(cal);
	  res.setP5T35(Short.parseShort(fun.getP5t35()));
	  res.setP6T35(fun.getP6t35());
	  cal.setTime(df.parse(df.format(fun.getP7t35())));
	  res.setP7T35(cal);
	  res.setP8T35(fun.getP8t35());
	  res.setP9T35(Short.parseShort(fun.getP9t35()));
	  res.setP10T35(fun.getP10t35());
	  res.setP11T35(fun.getP11t35());
	  res.setP12T35(fun.getP12t35());
	  res.setP13T35(fun.getP13t35());
	  res.setP14T35(fun.getP14t35());
	  res.setP15T35(fun.getP15t35());
	  res.setP16T35(fun.getP16t35());
	  res.setP17T35(fun.getP17t35());
	  res.setP18T35(fun.getP18t35());
	  res.setP19T35(fun.getP19t35());
	  res.setP20T35((String)session.getAttribute("ufn"));
	  return res;
}
//=========================================================================
	public void onSelect$p5t35() {
		//System.out.println("p5t35 = "+p5t35.getValue());
		if (p5t35.getValue().equalsIgnoreCase("2")){
			rowp2t35.setVisible(false);
        	rowp3t35.setVisible(false);
        	rowp6t35.setVisible(true);
			rowp7t35.setVisible(true);
			rowp8t35.setVisible(true);
		} else {
			rowp2t35.setVisible(true);
        	rowp3t35.setVisible(true);
        	rowp6t35.setVisible(false);
			rowp7t35.setVisible(false);
			rowp8t35.setVisible(false);
		}
	}
	
	public void onSelect$ap5t35() {
		if (ap5t35.getValue().equalsIgnoreCase("2")){
			arowp2t35.setVisible(false);
        	arowp3t35.setVisible(false);
			arowp6t35.setVisible(true);
			arowp7t35.setVisible(true);
			arowp8t35.setVisible(true);
			prepareBankPaymentDetails(false);
		} else {
			arowp2t35.setVisible(true);
        	arowp3t35.setVisible(true);
        	arowp6t35.setVisible(false);
			arowp7t35.setVisible(false);
			arowp8t35.setVisible(false);
			prepareBankPaymentDetails(true);
		}
	}
	
	public void onSelect$p9t35() {
		rowp10t35.setVisible(false); 
		rowp11t35.setVisible(false); 
		rowp12t35.setVisible(false);
		if (p9t35.getValue().equalsIgnoreCase("2")){
			rowp10t35.setVisible(true); 
		} else if (p9t35.getValue().equalsIgnoreCase("3")) {
			rowp11t35.setVisible(true); 
		} else if (p9t35.getValue().equalsIgnoreCase("4")) {
			rowp12t35.setVisible(true);
		}
		if (current.getP9t35().equalsIgnoreCase("2")){
			accrcurrencies = ContractService.getCurrOfAccreditiv(idn, p10t35.getValue(), alias);
			if (accrcurrencies.size() > 0) {
				p13t35.setSelecteditem(accrcurrencies.get(0).getData());
				current.setP13t35(p13t35.getValue());
			}
		} else {
			p13t35.setSelectedIndex(0);
			current.setP13t35(p13t35.getValue());
		}
		countCourse(false);
	}
	
	public void onSelect$ap9t35() {
		arowp10t35.setVisible(false); 
		arowp11t35.setVisible(false); 
		arowp12t35.setVisible(false);
		if (ap9t35.getValue().equalsIgnoreCase("2")){
			arowp10t35.setVisible(true); 
		} else if (ap9t35.getValue().equalsIgnoreCase("3")) {
			arowp11t35.setVisible(true); 
		} else if (ap9t35.getValue().equalsIgnoreCase("4")) {
			arowp12t35.setVisible(true);
		}
		if (ap9t35.getValue().equalsIgnoreCase("2")){
			accrcurrencies = ContractService.getCurrOfAccreditiv(idn, ap10t35.getValue(), alias);
			if (accrcurrencies.size() > 0) {
				ap13t35.setSelecteditem(accrcurrencies.get(0).getData());
			}
		} else {
			ap13t35.setSelectedIndex(0);
		}
	}
	
	public void onSelect$p10t35() {
		accrcurrencies = ContractService.getCurrOfAccreditiv(idn, p10t35.getValue(), alias);
		if (accrcurrencies.size() > 0) {
			p13t35.setSelecteditem(accrcurrencies.get(0).getData());
			current.setP13t35(p13t35.getValue());
		}  else {
			p13t35.setSelectedIndex(0);
			current.setP13t35(p13t35.getValue());
		}
	}
	
	public void onSelect$ap10t35() {
		accrcurrencies = ContractService.getCurrOfAccreditiv(idn, ap10t35.getValue(), alias);
		if (accrcurrencies.size() > 0) {
			ap13t35.setSelecteditem(accrcurrencies.get(0).getData());
		}  else {
			ap13t35.setSelectedIndex(0);
		}
	}
	
	public void onInitRederAfter$p13t35() {
		if (p13t35.getModel().getSize() > 0){
			if (CheckNull.isEmpty(current.getP13t35())) {
				p13t35.setSelectedIndex(0);
				current.setP13t35(p13t35.getValue());
			} else {
				p13t35.setSelecteditem(current.getP13t35());
				current.setP13t35(p13t35.getValue());
			}
		}
	}
	
	public void onInitRederAfter$ap13t35() {
		if (ap13t35.getModel().getSize() > 0){
			ap13t35.setSelectedIndex(0);
		}
	}
	
	
	private void countCourse(Boolean setp15t35) {
		try {
			if(!CheckNull.isEmpty(p15t353.getValue()) && !CheckNull.isEmpty(p15t351.getValue())) {
				System.out.println("***"+p15t351.getValue()+" - "+p15t353.getValue());
				if (setp15t35) {
					p15t35.setValue(""+(p15t351.getCourse()/p15t353.getCourse()));
					current.setP14t35((p15t351.getCourse()/p15t353.getCourse()));
				}
				cbcourse.setValue("По курсу ЦБ: "+(p15t351.getCourse()/p15t353.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (p13t35.getValue().equalsIgnoreCase(p15t351.getValue())) {
					if (val1.equalsIgnoreCase(p15t351.getValue())) {
						db = (p16t35.doubleValue() + (p17t35.doubleValue()/p15t35.doubleValue())+
							  p18t35.doubleValue() + (p19t35.doubleValue()/p15t35.doubleValue()));
							//  p20t35.doubleValue() + (p21t35.doubleValue()/p15t35.doubleValue()));
						bool = (p14t35.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(p15t351.getValue())) {
						db = (p17t35.doubleValue() + (p16t35.doubleValue()/p15t35.doubleValue())+
							  p19t35.doubleValue() + (p18t35.doubleValue()/p15t35.doubleValue()));
							  //p21t35.doubleValue() + (p20t35.doubleValue()/p15t35.doubleValue()));
						bool = (p14t35.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else {
						checksum.setChecked(false);
						checksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				} else if (p13t35.getValue().equalsIgnoreCase(p15t353.getValue())) {
					if (val1.equalsIgnoreCase(p15t353.getValue())) {
						db = (p16t35.doubleValue() + (p17t35.doubleValue()*p15t35.doubleValue())+
							  p18t35.doubleValue() + (p19t35.doubleValue()*p15t35.doubleValue()));
							 // p20t35.doubleValue() + (p21t35.doubleValue()*p15t35.doubleValue()));
						bool = (p14t35.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(p15t353.getValue())) {
						db = (p17t35.doubleValue() + (p16t35.doubleValue()*p15t35.doubleValue())+
							  p19t35.doubleValue() + (p18t35.doubleValue()*p15t35.doubleValue()));
							  //p21t35.doubleValue() + (p20t35.doubleValue()*p15t35.doubleValue()));
						bool = (p14t35.doubleValue() == db);
						checksum.setChecked(bool);
						checksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else {
						checksum.setChecked(false);
						checksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				} else {
					checksum.setChecked(false);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void acountCourse(Boolean setap15t35) {
		try {
			if(!CheckNull.isEmpty(ap15t353.getValue()) && !CheckNull.isEmpty(ap15t351.getValue())) {
				System.out.println("***"+ap15t351.getValue()+" - "+ap15t353.getValue());
				if (setap15t35) {
					ap15t35.setValue(""+(ap15t351.getCourse()/ap15t353.getCourse()));
					//current.setP14t35((ap15t351.getCourse()/ap15t353.getCourse()));
				}
				acbcourse.setValue("По курсу ЦБ: "+(ap15t351.getCourse()/ap15t353.getCourse()));
				Boolean bool = false;
				Double db = null;
				if (ap13t35.getValue().equalsIgnoreCase(ap15t351.getValue())) {
					if (val1.equalsIgnoreCase(ap15t351.getValue())) {
						db = (ap16t35.doubleValue() + (ap17t35.doubleValue()/ap15t35.doubleValue())+
							  ap18t35.doubleValue() + (ap19t35.doubleValue()/ap15t35.doubleValue()));
							  //ap20t35.doubleValue() + (ap21t35.doubleValue()/ap15t35.doubleValue()));
						bool = (ap14t35.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(ap15t351.getValue())) {
						db = (ap17t35.doubleValue() + (ap16t35.doubleValue()/ap15t35.doubleValue())+
							  ap19t35.doubleValue() + (ap18t35.doubleValue()/ap15t35.doubleValue()));
							 // ap21t35.doubleValue() + (ap20t35.doubleValue()/ap15t35.doubleValue()));
						bool = (ap14t35.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else {
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				} else if (ap13t35.getValue().equalsIgnoreCase(ap15t353.getValue())) {
					if (val1.equalsIgnoreCase(ap15t353.getValue())) {
						db = (ap16t35.doubleValue() + (ap17t35.doubleValue()*ap15t35.doubleValue())+
							  ap18t35.doubleValue() + (ap19t35.doubleValue()*ap15t35.doubleValue()));
							  //ap20t35.doubleValue() + (ap21t35.doubleValue()*ap15t35.doubleValue()));
						bool = (ap14t35.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else if (val2.equalsIgnoreCase(ap15t353.getValue())) {
						db = (ap17t35.doubleValue() + (ap16t35.doubleValue()*ap15t35.doubleValue())+
							  ap19t35.doubleValue() + (ap18t35.doubleValue()*ap15t35.doubleValue()));
							  //ap21t35.doubleValue() + (ap20t35.doubleValue()*ap15t35.doubleValue()));
						bool = (ap14t35.doubleValue() == db);
						achecksum.setChecked(bool);
						achecksum.setLabel((bool?"Сумма гарантии полность соответствует указанному курсу!":"Сумма гарантии не соответствует указанному курсу!")+"("+db+")");
					} else {
						achecksum.setChecked(false);
						achecksum.setLabel(("Сумма гарантии не соответствует указанному курсу!"));
					}
				} else {
					achecksum.setChecked(false);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onInitRenderLater$p15t353() {
		p15t353.setSelectedIndex(coursecurrencies.size()-1);
		countCourse(false);
	}
	
	public void onSelect$p13t35() {
		countCourse(false);
	}

	public void onChange$p14t35() {
		countCourse(false);
	}
	
	public void onChange$p15t35() {
		countCourse(false);
	}
	
	public void onSelect$p15t351() {
		countCourse(false);
	}

	public void onSelect$p15t353() {
		countCourse(false);
	}

	public void onChange$p16t35() {
		countCourse(false);
	}
	
	public void onChange$p17t35() {
		countCourse(false);
	}

	public void onChange$p18t35() {
		countCourse(false);
	}

	public void onChange$p19t35() {
		countCourse(false);
	}

	public void onChange$p20t35() {
		countCourse(false);
	}

	public void onChange$p21t35() {
		countCourse(false);
	}
	
	public void onInitRenderLater$ap15t353() {
		ap15t353.setSelectedIndex(coursecurrencies.size()-1);
		acountCourse(true);
	}
	
	public void onSelect$ap13t35() {
		acountCourse(false);
	}

	public void onChange$ap14t35() {
		acountCourse(false);
	}
	
	public void onChange$ap15t35() {
		acountCourse(false);
	}
	
	public void onSelect$ap15t353() {
		acountCourse(false);
	}
	
	public void onSelect$ap15t351() {
		acountCourse(false);
	}

	public void onChange$ap16t35() {
		acountCourse(false);
	}
	
	public void onChange$ap17t35() {
		acountCourse(false);
	}

	public void onChange$ap18t35() {
		acountCourse(false);
	}

	public void onChange$ap19t35() {
		acountCourse(false);
	}

	public void onChange$ap20t35() {
		acountCourse(false);
	}

	public void onChange$ap21t35() {
		acountCourse(false);
	}
	

	private Window contractmain = null; 
	public void onClick$btn_confirm() {
		sendConfirm(1, current.getP2t35(), current);
	}

	public void onClick$btn_reject() {
		sendConfirm(0, current.getP2t35(), current);
	}


	private void sendConfirm(int action, String docnum, Object obj) {
		if (contractmain==null){
		contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
		
		}
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("inn", ((String)session.getAttribute("BankINN")));
		params.put("idn", idn);
		params.put("action", action+"");
		params.put("docnum", docnum);
		params.put("obj", obj);
		Events.sendEvent("onConfirmDocument", contractmain, params);
	}

	private void prepareBankPaymentDetails(Boolean isBank) {
		if (frm.isVisible() && addgrd.isVisible()) {
			if (isBank) {
				//Enable BankGP
				abankgprow.setVisible(true);
				//ap13t35.setDisabled(true);
				ap14t35.setReadonly(true);
				ap3t35.setReadonly(true);
				//ap13t35.setSelectedIndex(-1);
				ap14t35.setRawValue(null);
				ap3t35.setValue("");
			} else {
				//Disable BankGP
				abankgprow.setVisible(false);
				//ap13t35.setDisabled(false);
				ap14t35.setReadonly(false);
				ap3t35.setReadonly(false);
				//ap13t35.setSelectedIndex(-1);
				ap14t35.setRawValue(null);
				ap3t35.setValue("");
			}
		} else if (frm.isVisible() && frmgrd.isVisible()) {
			if (isBank) {
				//Enable BankGP
				
			} else {
				//Disable BankGP
				
			}
		}
	}
	
	public void onClick$abtn_bankgp() {
		if (CheckNull.isEmpty(ap9t35.getValue())) {
			alert("Заполните условия поступления");
			return;
		} 
		if (CheckNull.isEmpty(ap13t35.getValue())) {
			alert("Заполните валюту");
			return;
		} 
		if (ap9t35.getValue().equalsIgnoreCase("2")){
			if (CheckNull.isEmpty(ap10t35.getValue())) {
				alert("Выберите аккредитив");
				return;
			} 
		} else if (ap9t35.getValue().equalsIgnoreCase("3")) {
			if (CheckNull.isEmpty(ap11t35.getValue())) {
				alert("Выберите гарантию");
				return;
			} 
		} else if (ap9t35.getValue().equalsIgnoreCase("4")) {
			if (CheckNull.isEmpty(ap12t35.getValue())) {
				alert("Выберите полис");
				return;
			} 
		}
		sendGP("","",current,ap13t35.getValue(),"CO");
	}
	
	
	private void sendGP(String obgect_id, String sub_obgect_id, Object obj, String currency, String acc_type) {
		if (contractmain==null){
		contractmain = (Window) execution.getDesktop().getPage("contract").getFellow("contractmain");
		
		//contractmain = (Window) session.getAttribute("contractmain");
		}
		HashMap<String,Object> params = new HashMap<String, Object>();
		params.put("idn", idn);
		params.put("obgect_id", obgect_id);
		params.put("sub_obgect_id", sub_obgect_id);
		params.put("obj", obj);
		params.put("currency", currency);
		params.put("acc_type", acc_type);
		params.put("date_from", ap7t35.getValue());
		params.put("date_to", ap7t35.getValue());
		params.put("page", "fund");
		params.put("pagetype", "window");
		Events.sendEvent("onGP", contractmain, params);
	}
	
	public void onGP(Event evt) {
		try {
			HashMap<String,Object> params = (HashMap<String,Object>)evt.getData();
			Tf_general_payment gp = (Tf_general_payment)params.get("objectgp");
			alert("gp.getId() = "+gp.getId());
			ap14t35.setValue(gp.getSumma_idn().divide(new BigDecimal("100")));
			ap3t35.setValue(gp.getGeneral_id()+"");
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.is.tf.fund.FundService.remove(new Fund() ,idc);
		com.sbs.service.FundsResult fund = ws.getFunds(((String)(session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(fund, "c:/Fund1.xml");
		
		if (fund.getStatus()==0)
		{
			 for (int i=0;i<fund.getFunds().length;i++)
			 {
				com.is.tf.fund.FundService.create(fund.getFunds()[i], idn, idc);
			 }
		 } else {
			 alert("ERROR:"+fund.getErrorMsg()+":  Status="+fund.getStatus()+": GtkId="+fund.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
			 ISLogger.getLogger().warn("ERROR onSelect$Compensation:"+fund.getErrorMsg()+":  Status="+fund.getStatus()+": GtkId="+fund.getGtkId());
		 }
   	 	 refreshModel(_startPageNumber);
		
	}
}
