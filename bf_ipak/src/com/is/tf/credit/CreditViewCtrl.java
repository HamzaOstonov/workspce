package com.is.tf.credit;

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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
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
import org.zkoss.zul.impl.InputElement;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.CreditResult;

public class CreditViewCtrl extends GenericForwardComposer{
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
    private Toolbarbutton btn_confirm, btn_reject, btn_edit, btn_delete, btn_save;
    private Toolbarbutton btn_edit2, btn_confirmBuhg, btn_rejectBuhg, btn_cancel;   
    private String sparam1;
    private Toolbar tb;
    private Textbox id,p1t25,p6t25,p7t25,p9t25,p10t25,p12t25;
    private Textbox  ap17t25,aid,ap1t25,ap6t25,ap7t25,ap9t25,ap10t25,ap12t25;
    private Textbox fid,fp1t25,fp5t25,fp6t25,fp7t25,fp9t25,fp10t25,fp12t25 ;
    private Datebox p8t25,p11t25,p16t25,ap8t25,ap11t25,ap16t25,fp8t25,fp11t25,fp16t25;
    private Decimalbox p3t25,ap3t25,fp3t25,p14t25,p15t25,ap14t25,ap15t25,fp14t25,fp15t25;
    private Intbox p0t25,p13t25,ap0t25,ap13t25,fp0t25,fp4t25,fp13t25;
    private RefCBox  p5t25,ap5t25,ap4t25,p2t25,ap2t25,fp2t25,p4t25;
    private Paging creditPaging;
    private List<RefData> currencies = new ArrayList<RefData>();
    private List<RefData> reason = new ArrayList<RefData>();
    private List<RefData> agriments = new ArrayList<RefData>();
    private HashMap< String,String> curr_ =null;
    private String  idn, val1, val2,p69t1;
    private long idc;
    private Row row_ap5t25,row_ap6t25,row_ap7t25,row_ap8t25,row_ap9t25,row_ap10t25,row_ap11t25,row_ap12t25,row_p5t25,row_p6t25,row_p7t25,row_p8t25,row_p9t25,row_p10t25,row_p11t25,row_p12t25;
    private Label conr_val1,conr_val1a,conr_val2,conr_val2a,cbcourse;
    private int desktopHeight=0;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private Label line1;
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;
   
    public CreditFilter filter = new CreditFilter();
   

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Credit current = new Credit();

    public CreditViewCtrl() {
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
       
 }
    parameter = (String[]) param.get("idc");
    if (parameter!=null){
 	idc = Long.parseLong((parameter[0]));
 	   
 }
    parameter = (String[]) param.get("val1");
    if (parameter!=null){
 	val1 = (parameter[0]);
      
 }
    parameter = (String[]) param.get("val2");
    if (parameter!=null){
 	val2 = (parameter[0]);
   
 }
    parameter = (String[]) param.get("p69t1");
    if (parameter!=null) p69t1 = (parameter[0]);
    
    parameter = (String[]) param.get("spr");
    if (parameter!=null) sparam1 = (parameter[0]);
    
    filter.setP1t25(idn);
    curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
    conr_val2.setValue(curr_.get(val2));
    conr_val1.setValue(curr_.get(val1));
    conr_val2a.setValue(curr_.get(val2));
    conr_val1a.setValue(curr_.get(val1));

    line1.setValue(Labels.getLabel("credit.p16t25tab" ).replaceAll("<br>", "\r\n")); 
    line2.setValue(Labels.getLabel("credit.p3t25tab"  ).replaceAll("<br>", "\r\n")); 
    line3.setValue(Labels.getLabel("credit.p2t25tab"  ).replaceAll("<br>", "\r\n")); 
    line4.setValue(Labels.getLabel("credit.p4t25tab"  ).replaceAll("<br>", "\r\n")); 
    line5.setValue(Labels.getLabel("credit.p13t25tab" ).replaceAll("<br>", "\r\n")); 
    line6.setValue(Labels.getLabel("credit.p17t25tab" ).replaceAll("<br>", "\r\n")); 
    line7.setValue(Labels.getLabel("credit.p20t25tab" ).replaceAll("<br>", "\r\n")); 
    line8.setValue(Labels.getLabel("credit.p100t25tab").replaceAll("<br>", "\r\n"));	
	

    dataGrid.setItemRenderer(new ListitemRenderer()
    {
	    @SuppressWarnings("unchecked")
	    public void render(Listitem row, Object data) throws Exception {
	            Credit pCredit = (Credit) data;

                row.setValue(pCredit);
                
                row.appendChild(new Listcell(pCredit.getP16t25()+""));
                row.appendChild(new Listcell(pCredit.getP3t25()+""));
                row.appendChild(new Listcell(pCredit.getP2t25()+""));
                row.appendChild(new Listcell(pCredit.getP4t25()+""));
                row.appendChild(new Listcell(pCredit.getP13t25()+""));
                row.appendChild(new Listcell(pCredit.getP17t25()+""));
                row.appendChild(new Listcell(pCredit.getP20t25()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pCredit.getP100t25()+""))));
                
                /*row.appendChild(new Listcell(pCredit.getId()+""));
                row.appendChild(new Listcell(pCredit.getP0t25()+""));
                row.appendChild(new Listcell(pCredit.getP1t25()));
                row.appendChild(new Listcell(pCredit.getP2t25()));
                row.appendChild(new Listcell(pCredit.getP3t25()+""));
                row.appendChild(new Listcell(pCredit.getP4t25()+""));
                row.appendChild(new Listcell(pCredit.getP5t25()));
                row.appendChild(new Listcell(pCredit.getP6t25()));
                row.appendChild(new Listcell(pCredit.getP7t25()));
                row.appendChild(new Listcell(pCredit.getP8t25()+""));
                row.appendChild(new Listcell(pCredit.getP9t25()));
                row.appendChild(new Listcell(pCredit.getP10t25()));
                row.appendChild(new Listcell(pCredit.getP11t25()+""));
                row.appendChild(new Listcell(pCredit.getP12t25()));
                row.appendChild(new Listcell(pCredit.getP13t25()+""));
                row.appendChild(new Listcell(pCredit.getP14t25()+""));
                row.appendChild(new Listcell(pCredit.getP15t25()+""));
                row.appendChild(new Listcell(pCredit.getP16t25()+""));*/


    }});
        currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
        reason= com.is.tf.contract.ContractService.getReasons("1,4,5,6,8", alias);;
        agriments = com.is.tf.contract.ContractService.getAgreement(idn, alias);
        p2t25.setModel((new ListModelList(currencies)));
        ap2t25.setModel((new ListModelList(currencies)));
        fp2t25.setModel((new ListModelList(currencies)));
        p4t25.setModel((new ListModelList(reason)));
        ap4t25.setModel((new ListModelList(reason)));
        p5t25.setModel((new ListModelList(agriments)));
        ap5t25.setModel((new ListModelList(agriments)));
        
        if (sparam1 != null)
        {
     	  if (sparam1.equals("Go"))   /// ГО
     	  {
     		 btn_add.setVisible(false);
     	  }
        }
    
    refreshModel(_startPageNumber);

}

public void onPaging$creditPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    creditPaging.setPageSize(_pageSize);
    filter.setP1t25(idn);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter, "");
       // _needsTotalSizeUpdate = false;
}

creditPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Credit) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Credit getCurrent() {
return current;
}

public void setCurrent(Credit current) {
this.current = current;
if ((current != null)&&(current.getP4t25()==1)) {

	row_p5t25.setVisible(false);
	row_p6t25.setVisible(true);
	row_p7t25.setVisible(true);
	row_p8t25.setVisible(true);
	row_p9t25.setVisible(false);
	row_p10t25.setVisible(false);
	row_p11t25.setVisible(false);
	row_p12t25.setVisible(false);
	
	
} else if ((current != null)&&(current.getP4t25()==4)) {
	row_p5t25.setVisible(true);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(false);
	row_p10t25.setVisible(false);
	row_p11t25.setVisible(false);
	row_p12t25.setVisible(false);

} else if ((current != null)&&(current.getP4t25()==6)) {
	row_p5t25.setVisible(false);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(true);
	row_p10t25.setVisible(true);
	row_p11t25.setVisible(true);
	row_p12t25.setVisible(false);
} else if ((current != null)&&(current.getP4t25()==5)) {
	row_p5t25.setVisible(false);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(false);
	row_p10t25.setVisible(false);
	row_p11t25.setVisible(false);
	row_p12t25.setVisible(true);
} else if ((current != null)&&(current.getP4t25()==8)) {
	row_p5t25.setVisible(false);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(false);
	row_p10t25.setVisible(false);
	row_p11t25.setVisible(false);
	row_p12t25.setVisible(false);
}



}

public void onDoubleClick$dataGrid$grd() {
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            if (frmgrd.isVisible()){
      		  InputElement[] list = {id,p1t25 };
      		    for (int i=0;i<list.length;i++){
      		    	   if (list[i]!=null){
      		    	     list[i].setDisabled(true);
      		    	   }
      		    }
      	}
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
            
            if (sparam1 != null)
            {
         		 if (sparam1.equals("Filial"))  ///  Филиал
              {
         	    	btn_save.setVisible(false);
         	    	btn_cancel.setVisible(true);
         	    	btn_reject.setVisible(false);
         	    	btn_confirm.setVisible(false);
         	    	btn_edit.setVisible(true);
         	    	btn_delete.setVisible(false);
         	    	//btn_confirmBuhg.setVisible(false);
         	    	//btn_rejectBuhg.setVisible(false);
         	    	setFields(false);
         	   }
         	   else if (sparam1.equals("Go"))   /// ГО
         	   {
         	   		btn_confirm.setVisible(true);
         	   		btn_reject.setVisible(true);
         	   		btn_cancel.setVisible(true);
         	   		btn_save.setVisible(false);
         	    	btn_edit.setVisible(false);
         	    	btn_delete.setVisible(false);
         	    	//btn_confirmBuhg.setVisible(false);
         	    	//btn_rejectBuhg.setVisible(false);
         	    	setFields(true);
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


public void onClick$btn_add() 
{
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
    ap1t25.setValue(idn);
    ap16t25.setValue(new Date());
    
    ap14t25.setValue(p69t1);
    ap14t25.setDisabled(true);
    
    ap17t25.setValue((String)(session.getAttribute("un")));
    
    if (sparam1 != null)
    {
 	  if (sparam1.equals("Filial"))  ///  Филиал
      {
 	    	btn_save.setVisible(true);
 	    	btn_cancel.setVisible(true);
 	    	btn_reject.setVisible(false);
 	    	btn_confirm.setVisible(false);
 	    	btn_edit.setVisible(false);
 	    	btn_delete.setVisible(false);
 	    	//btn_confirmBuhg.setVisible(false);
 	    	//btn_rejectBuhg.setVisible(false);
 	   }
 	   else if (sparam1.equals("Go"))   /// ГО
 	   {
 		    btn_add.setDisabled(true);
 		    btn_cancel.setVisible(true);
 		    btn_save.setVisible(false);
 	    	btn_reject.setVisible(false);
 	    	btn_confirm.setVisible(false);
 	    	btn_edit.setVisible(false);
 	    	btn_delete.setVisible(false);
 	    	//btn_confirmBuhg.setVisible(false);
 	    	//btn_rejectBuhg.setVisible(false);
 	   }
    }
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}

public void onClick$btn_refresh() throws Exception 
{
	refresh(idn);
}


public void onClick$btn_save() {
try{
	final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
	com.sbs.service.CreditResult cre =  new com.sbs.service.CreditResult();
	
	if(addgrd.isVisible()){
            
		System.out.println("ap17t25.getValue()->"+ap17t25.getValue());
		
		CreditResult ar = ws.saveCredit(((String)(session.getAttribute("BankINN"))) , idn , getCre(
		new Credit(
            ap2t25.getValue(),
            ap3t25.doubleValue(),
            Integer.parseInt(ap4t25.getValue()),
            ap5t25.getValue(),
            ap6t25.getValue(),
            ap7t25.getValue(),
            ap8t25.getValue(),
            ap9t25.getValue(),
            String.valueOf(ap10t25.getValue()),
            ap11t25.getValue(),
            ap12t25.getValue(),
            ap14t25.doubleValue(),
            ap15t25.doubleValue(),
            ap16t25.getValue(),
            ap17t25.getValue()
            )));
		
        CheckNull.clearForm(addgrd);
        if (ar.getStatus() == 0) {
      	  refreshModel(_startPageNumber);
      	  alert("Сохранение успешно");
      	  refresh(idn);
      	  
        } else {
      	  alert("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
      	  System.out.println("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
        }
        
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new CreditFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP0t25(fp0t25.getValue());
          filter.setP1t25(fp1t25.getValue());
          filter.setP2t25(fp2t25.getValue());
          filter.setP3t25(fp3t25.doubleValue());
          filter.setP4t25(fp4t25.getValue());
          filter.setP5t25(fp5t25.getValue());
          filter.setP6t25(fp6t25.getValue());
          filter.setP7t25(fp7t25.getValue());
          filter.setP8t25(fp8t25.getValue());
          filter.setP9t25(fp9t25.getValue());
          filter.setP10t25(fp10t25.getValue());
          filter.setP11t25(fp11t25.getValue());
          filter.setP12t25(fp12t25.getValue());
          filter.setP13t25(fp13t25.getValue());
          filter.setP14t25(fp14t25.doubleValue());
          filter.setP15t25(fp15t25.doubleValue());
          filter.setP16t25(fp16t25.getValue());

    }else{
        
          Long.parseLong(id.getValue());
          current.setP2t25(p2t25.getValue());
          current.setP3t25(p3t25.doubleValue());
          current.setP4t25(Integer.parseInt(p4t25.getValue()));
          current.setP5t25(p5t25.getValue());
          current.setP6t25(p6t25.getValue());
          current.setP7t25(p7t25.getValue());
          current.setP8t25(p8t25.getValue());
          current.setP9t25(p9t25.getValue());
          current.setP10t25(p10t25.getValue());
          current.setP11t25(p11t25.getValue());
          current.setP12t25(p12t25.getValue());
          current.setP13t25(p13t25.getValue());
          current.setP14t25(p14t25.doubleValue());
          current.setP15t25(p15t25.doubleValue());
          current.setP16t25(p16t25.getValue());
        
          CreditResult cre1 = ws.saveCredit(((String)(session.getAttribute("BankINN"))), idn , getCreCorr(current));
          if (cre1.getStatus() == 0) {
        	  alert("Сохранение успешно");
        	  refresh(idn);
          } else {
        	  alert("Error:"+cre1.getStatus()+" - "+cre1.getErrorMsg()+ " GTKid "+cre1.getGtkId());
          }
          //CreditService.update(current);
    
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}

public void onClick$btn_delete() 
{
	try {
		
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		if (frmgrd.isVisible())
		{
			current.setP2t25(p2t25.getValue());
	          current.setP3t25(p3t25.doubleValue());
	          current.setP4t25(Integer.parseInt(p4t25.getValue()));
	          current.setP5t25(p5t25.getValue());
	          current.setP6t25(p6t25.getValue());
	          current.setP7t25(p7t25.getValue());
	          current.setP8t25(p8t25.getValue());
	          current.setP9t25(p9t25.getValue());
	          current.setP10t25(p10t25.getValue());
	          current.setP11t25(p11t25.getValue());
	          current.setP12t25(p12t25.getValue());
	          current.setP13t25(p13t25.getValue());
	          current.setP14t25(p14t25.doubleValue());
	          current.setP15t25(p15t25.doubleValue());
	          current.setP16t25(p16t25.getValue());
	        
	      CreditResult ar = ws.saveCredit(((String)(session.getAttribute("BankINN"))), idn , delCre(current));
          if (ar.getStatus() == 0) 
          {
	      	  refreshModel(_startPageNumber);
	      	  alert("Запрос на удаление передано в ГО");
	      	  refresh(idn);
      	  
          } else 
          {
      	    alert("Ошибка при удалении1!  Error:"+ar.getStatus()+";  GTKid:" +ar.getGtkId()+ ";  Text:" +ar.getErrorMsg());
      	    refreshModel(_startPageNumber);
          }
			
	    }    
		onClick$btn_back();
		  
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	} catch(Exception e) 
	{
		e.printStackTrace();
	}
}	

public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new CreditFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

private com.sbs.service.Credit getCre(Credit cre)
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  java.util.Calendar cal3 = java.util.Calendar.getInstance();
	  com.sbs.service.Credit res = new com.sbs.service.Credit();
	  
	  res.setP0T25(0);
	  res.setP2T25(cre.getP2t25());
	  res.setP3T25(cre.getP3t25());
	  res.setP4T25(Short.parseShort(String.valueOf(cre.getP4t25())));
	  
	  cal.setTime(cre.getP16t25()); 
	  res.setP16T25(cal);
	  
	  if (!CheckNull.isEmpty(cre.getP11t25())) {
		  cal2.setTime(cre.getP11t25());
		  res.setP11T25(cal2);
	  }
	  if (!CheckNull.isEmpty(cre.getP8t25())) {
		  cal3.setTime(cre.getP8t25()); 
		  res.setP8T25(cal3);
	  }
	  
	  if (!CheckNull.isEmpty(cre.getP10t25())) res.setP10T25(cre.getP10t25());
	  if (!CheckNull.isEmpty(cre.getP12t25())) res.setP12T25(cre.getP12t25());
	  if (!CheckNull.isEmpty(cre.getP5t25())) res.setP5T25(cre.getP5t25());
	  if (!CheckNull.isEmpty(cre.getP6t25())) res.setP6T25(cre.getP6t25());
	  if (!CheckNull.isEmpty(cre.getP7t25())) res.setP7T25(cre.getP7t25());
	  
	  //res.setP13T25(cre.getP13t25());
	  res.setP14T25(cre.getP14t25());
	  res.setP15T25(cre.getP15t25());
	  res.setP17T25(cre.getP17t25());
	  return res;
}

private com.sbs.service.Credit getCreCorr(Credit cre)
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  java.util.Calendar cal3 = java.util.Calendar.getInstance();
	  com.sbs.service.Credit res = new com.sbs.service.Credit();
	  
	  res.setP0T25(1);
	  res.setP2T25(cre.getP2t25());
	  res.setP3T25(cre.getP3t25());
	  res.setP4T25(Short.parseShort(String.valueOf(cre.getP4t25())));
	  
	  cal.setTime(cre.getP16t25()); 
	  res.setP16T25(cal);
	  
	  if (!CheckNull.isEmpty(cre.getP11t25())) {
		  cal2.setTime(cre.getP11t25());
		  res.setP11T25(cal2);
	  }
	  if (!CheckNull.isEmpty(cre.getP8t25())) {
		  cal3.setTime(cre.getP8t25()); 
		  res.setP8T25(cal3);
	  }
	  
	  if (!CheckNull.isEmpty(cre.getP10t25())) res.setP10T25(cre.getP10t25());
	  if (!CheckNull.isEmpty(cre.getP12t25())) res.setP12T25(cre.getP12t25());
	  if (!CheckNull.isEmpty(cre.getP5t25())) res.setP5T25(cre.getP5t25());
	  if (!CheckNull.isEmpty(cre.getP6t25())) res.setP6T25(cre.getP6t25());
	  if (!CheckNull.isEmpty(cre.getP7t25())) res.setP7T25(cre.getP7t25());
	  
	  res.setP13T25(cre.getP13t25());
	  res.setP14T25(cre.getP14t25());
	  res.setP15T25(cre.getP15t25());
	  res.setP17T25(cre.getP17t25());
	  return res;
}

private com.sbs.service.Credit delCre(Credit cre)
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  java.util.Calendar cal3 = java.util.Calendar.getInstance();
	  com.sbs.service.Credit res = new com.sbs.service.Credit();
	  
	  res.setP0T25(2);
	  res.setP2T25(cre.getP2t25());
	  res.setP3T25(cre.getP3t25());
	  res.setP4T25(Short.parseShort(String.valueOf(cre.getP4t25())));
	  
	  cal.setTime(cre.getP16t25()); 
	  res.setP16T25(cal);
	  
	  if (!CheckNull.isEmpty(cre.getP11t25())) {
		  cal2.setTime(cre.getP11t25());
		  res.setP11T25(cal2);
	  }
	  if (!CheckNull.isEmpty(cre.getP8t25())) {
		  cal3.setTime(cre.getP8t25()); 
		  res.setP8T25(cal3);
	  }
	  
	  if (!CheckNull.isEmpty(cre.getP10t25())) res.setP10T25(cre.getP10t25());
	  if (!CheckNull.isEmpty(cre.getP12t25())) res.setP12T25(cre.getP12t25());
	  if (!CheckNull.isEmpty(cre.getP5t25())) res.setP5T25(cre.getP5t25());
	  if (!CheckNull.isEmpty(cre.getP6t25())) res.setP6T25(cre.getP6t25());
	  if (!CheckNull.isEmpty(cre.getP7t25())) res.setP7T25(cre.getP7t25());
	  
	  res.setP13T25(cre.getP13t25());
	  res.setP14T25(cre.getP14t25());
	  res.setP15T25(cre.getP15t25());
	  res.setP17T25(cre.getP17t25());
	  return res;
}

public void onSelect$p4t25() {
	if (p4t25.getValue().equalsIgnoreCase("1")) {
		row_p5t25.setVisible(false);
		row_p6t25.setVisible(true);
		row_p7t25.setVisible(true);
		row_p8t25.setVisible(true);
		row_p9t25.setVisible(false);
		row_p10t25.setVisible(false);
		row_p11t25.setVisible(false);
		row_p12t25.setVisible(false);
		
		
	} else if (p4t25.getValue().equalsIgnoreCase("4")) {
		row_p5t25.setVisible(true);
		row_p6t25.setVisible(false);
		row_p7t25.setVisible(false);
		row_p8t25.setVisible(false);
		row_p9t25.setVisible(false);
		row_p10t25.setVisible(false);
		row_p11t25.setVisible(false);
		row_p12t25.setVisible(false);
	
} else if (p4t25.getValue().equalsIgnoreCase("6")) {
	row_p5t25.setVisible(false);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(true);
	row_p10t25.setVisible(true);
	row_p11t25.setVisible(true);
	row_p12t25.setVisible(false);
} else if (p4t25.getValue().equalsIgnoreCase("5")) {
	row_p5t25.setVisible(false);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(false);
	row_p10t25.setVisible(false);
	row_p11t25.setVisible(false);
	row_p12t25.setVisible(true);
} else if (p4t25.getValue().equalsIgnoreCase("8")) {
	row_p5t25.setVisible(false);
	row_p6t25.setVisible(false);
	row_p7t25.setVisible(false);
	row_p8t25.setVisible(false);
	row_p9t25.setVisible(false);
	row_p10t25.setVisible(false);
	row_p11t25.setVisible(false);
	row_p12t25.setVisible(false);
}
}

public void onSelect$ap4t25() {
	if (ap4t25.getValue().equalsIgnoreCase("1")) {
		row_ap5t25.setVisible(false);
		row_ap6t25.setVisible(true);
		row_ap7t25.setVisible(true);
		row_ap8t25.setVisible(true);
		row_ap9t25.setVisible(false);
		row_ap10t25.setVisible(false);
		row_ap11t25.setVisible(false);
		row_ap12t25.setVisible(false);
		
		
	} else if (ap4t25.getValue().equalsIgnoreCase("4")) {
		row_ap5t25.setVisible(true);
		row_ap6t25.setVisible(false);
		row_ap7t25.setVisible(false);
		row_ap8t25.setVisible(false);
		row_ap9t25.setVisible(false);
		row_ap10t25.setVisible(false);
		row_ap11t25.setVisible(false);
		row_ap12t25.setVisible(false);
	
} else if (ap4t25.getValue().equalsIgnoreCase("6")) {
	row_ap5t25.setVisible(false);
	row_ap6t25.setVisible(false);
	row_ap7t25.setVisible(false);
	row_ap8t25.setVisible(false);
	row_ap9t25.setVisible(true);
	row_ap10t25.setVisible(true);
	row_ap11t25.setVisible(true);
	row_ap12t25.setVisible(false);
} else if (ap4t25.getValue().equalsIgnoreCase("5")) {
	row_ap5t25.setVisible(false);
	row_ap6t25.setVisible(false);
	row_ap7t25.setVisible(false);
	row_ap8t25.setVisible(false);
	row_ap9t25.setVisible(false);
	row_ap10t25.setVisible(false);
	row_ap11t25.setVisible(false);
	row_ap12t25.setVisible(true);
} else if (ap4t25.getValue().equalsIgnoreCase("8")) {
	row_ap5t25.setVisible(false);
	row_ap6t25.setVisible(false);
	row_ap7t25.setVisible(false);
	row_ap8t25.setVisible(false);
	row_ap9t25.setVisible(false);
	row_ap10t25.setVisible(false);
	row_ap11t25.setVisible(false);
	row_ap12t25.setVisible(false);
}
}

public void onClick$btn_edit() 
{
	
	if (frmgrd.isVisible())
    {
	  btn_save.setVisible(true);
	  btn_delete.setVisible(true);
	  btn_confirm.setVisible(true);
	  btn_reject.setVisible(true);
	  setFields(false);
      
	} else
	if (addgrd.isVisible()){
		btn_save.setVisible(true);
		btn_delete.setVisible(true);
		btn_confirm.setVisible(true);
		btn_reject.setVisible(true);
	}
}  

//*********************************** Confirm **************************************
	private Window contractmain = null; 
	public void onClick$btn_confirm() {
		sendConfirm(1, String.valueOf(current.getP13t25()), current);
	}
	
	public void onClick$btn_reject() {
		sendConfirm(0, String.valueOf(current.getP13t25()), current);
	}
	
	private void sendConfirm(int action, String docnum, Object obj) {
		if (contractmain==null)
		{
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

	public void setFields(boolean param)
	 {
		p2t25.setDisabled(param);
	      p3t25.setDisabled(param);
	      p4t25.setDisabled(param);
	      p5t25.setDisabled(param);
	      p6t25.setDisabled(param);
	      p7t25.setDisabled(param);
	      p8t25.setDisabled(param);
	      p9t25.setDisabled(param);
	      p10t25.setDisabled(param);
	      p11t25.setDisabled(param);
	      p12t25.setDisabled(param);
	      p13t25.setDisabled(param);
	      p14t25.setDisabled(param);
	      p15t25.setDisabled(param);
	      p16t25.setDisabled(param);
	 }
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.is.tf.credit.CreditService.remove(new Credit() ,idc);
		com.sbs.service.CreditsResult debi = ws.getCredits(((String)(session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(debi, "c:/Credet1.xml");
		
		if (debi.getStatus()==0)
		{
			 for (int i=0;i<debi.getCredits().length;i++)
			 {
				com.is.tf.credit.CreditService.create(debi.getCredits()[i], idn, idc);
			 }
		 } else {
			 alert("ERROR:"+debi.getErrorMsg()+":  Status="+debi.getStatus()+": GtkId="+debi.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
			 ISLogger.getLogger().warn("ERROR onSelect$Compensation:"+debi.getErrorMsg()+":  Status="+debi.getStatus()+": GtkId="+debi.getGtkId());
		 }
   	 	 refreshModel(_startPageNumber); 
	}
}




