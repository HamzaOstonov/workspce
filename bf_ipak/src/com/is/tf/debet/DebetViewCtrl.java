package com.is.tf.debet;

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
import com.sbs.service.DebetResult;

public class DebetViewCtrl extends GenericForwardComposer{
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
    private Textbox id,p1t24,p5t24,p6t24,p8t24,p9t24,p11t24;
    private Textbox ap100t24,ap17t24,ap19t24,ap18t24,aid,ap1t24,ap5t24,ap6t24,ap8t24,ap9t24,ap11t24 ;
    private Textbox fid,fp1t24,fp5t24,fp6t24,fp8t24,fp9t24,fp11t24;
    private Datebox p7t24,p10t24,p12t24,p16t24,ap7t24,ap10t24,ap12t24,ap16t24,fp7t24,fp10t24,fp12t24,fp16t24;
    private Decimalbox p3t24,p14t24,p15t24,ap3t24,ap14t24,ap15t24,fp3t24,fp14t24,fp15t24;
    private RefCBox ap4t24,p4t24,p2t24,ap2t24,fp2t24;
    private Intbox p0t24,p13t24,ap0t24,ap13t24,fp0t24,fp4t24,fp13t24 ;
    private Label aconr_val1,aconr_val2,conr_val1,conr_val1a,conr_val2,conr_val2a,cbcourse;
    private Paging debetPaging;
    private Row row_ap5t24,row_ap6t24,row_ap7t24,row_ap8t24,row_ap9t24,row_ap10t24,row_ap11t24,row_ap12t24,row_p5t24,row_p6t24,row_p7t24,row_p8t24,row_p9t24,row_p10t24,row_p11t24,row_p12t24;
    private List<RefData> currencies = new ArrayList<RefData>();
    private List<RefData> reason = new ArrayList<RefData>();
    private HashMap< String,String> curr_ =null;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private String  idn, val1, val2,p65t1,p67t1;
    private long gid,idc;
    private int desktopHeight=0;
    private Label line1;
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;
    
    public DebetFilter filter = new DebetFilter();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Debet current = new Debet();

    public DebetViewCtrl() {
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
 }
    parameter = (String[]) param.get("idc");
    if (parameter!=null){
 	idc = Long.parseLong((parameter[0]));
 	 //System.out.println("ID  "+idc+" idn  "+idn);
     // System.out.println("Garant  cont_idn "+idn);   
 }
    parameter = (String[]) param.get("val1");
    if (parameter!=null){
 	val1 = (parameter[0]);
    // System.out.println("Garant  cont_val1 "+val1);   
 }
    parameter = (String[]) param.get("p65t1");
    if (parameter!=null) p65t1 = (parameter[0]);
    
    parameter = (String[]) param.get("p67t1");
    if (parameter!=null) p67t1 = (parameter[0]);
    
    parameter = (String[]) param.get("spr");
    if (parameter!=null) sparam1 = (parameter[0]);
        
    parameter = (String[]) param.get("val2");
    if (parameter!=null){
 	val2 = (parameter[0]);
   //   System.out.println("Garant  cont_val2 "+val2);   
 }
    curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
    conr_val2.setValue(curr_.get(val2));
    conr_val1.setValue(curr_.get(val1));
    aconr_val2.setValue(curr_.get(val2));
    aconr_val1.setValue(curr_.get(val1));

    filter.setP1t24(idn);
    
    line1.setValue(Labels.getLabel("debet.p16t24tab" ).replaceAll("<br>", "\r\n")); 
    line2.setValue(Labels.getLabel("debet.p3t24tab"  ).replaceAll("<br>", "\r\n")); 
    line3.setValue(Labels.getLabel("debet.p2t24tab"  ).replaceAll("<br>", "\r\n")); 
    line4.setValue(Labels.getLabel("debet.p4t24tab"  ).replaceAll("<br>", "\r\n")); 
    line5.setValue(Labels.getLabel("debet.p13t24tab" ).replaceAll("<br>", "\r\n")); 
    line6.setValue(Labels.getLabel("debet.p17t24tab" ).replaceAll("<br>", "\r\n")); 
    line7.setValue(Labels.getLabel("debet.p20t24tab" ).replaceAll("<br>", "\r\n")); 
    line8.setValue(Labels.getLabel("debet.p100t24tab").replaceAll("<br>", "\r\n"));	
	
    //filter.setId_contract(idc);
        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception 
    {
                Debet pDebet = (Debet) data;

                row.setValue(pDebet);
                
                row.appendChild(new Listcell(pDebet.getP16t24()+""));
                row.appendChild(new Listcell(pDebet.getP3t24()+""));
                row.appendChild(new Listcell(pDebet.getP2t24()+""));
                row.appendChild(new Listcell(pDebet.getP4t24()+""));
                row.appendChild(new Listcell(pDebet.getP13t24()+""));
                row.appendChild(new Listcell(pDebet.getP17t24()+""));
                row.appendChild(new Listcell(pDebet.getP20t24()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pDebet.getP100t24()+""))));
                
                
                /*row.appendChild(new Listcell(pDebet.getId()+""));
                row.appendChild(new Listcell(pDebet.getP0t24()+""));
                row.appendChild(new Listcell(pDebet.getP1t24()));
                row.appendChild(new Listcell(pDebet.getP2t24()));
                row.appendChild(new Listcell(pDebet.getP3t24()+""));
                row.appendChild(new Listcell(pDebet.getP4t24()+""));
                row.appendChild(new Listcell(pDebet.getP5t24()));
                row.appendChild(new Listcell(pDebet.getP6t24()));
                row.appendChild(new Listcell(pDebet.getP7t24()+""));
                row.appendChild(new Listcell(pDebet.getP8t24()));
                row.appendChild(new Listcell(pDebet.getP9t24()));
                row.appendChild(new Listcell(pDebet.getP10t24()+""));
                row.appendChild(new Listcell(pDebet.getP11t24()));
                row.appendChild(new Listcell(pDebet.getP12t24()+""));
                row.appendChild(new Listcell(pDebet.getP13t24()+""));
                row.appendChild(new Listcell(pDebet.getP14t24()+""));
                row.appendChild(new Listcell(pDebet.getP15t24()+""));
                row.appendChild(new Listcell(pDebet.getP16t24()+""));*/


    }});
        currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
        reason= com.is.tf.contract.ContractService.getReasons("1,2,6,7", alias);;
        p2t24.setModel((new ListModelList(currencies)));
        ap2t24.setModel((new ListModelList(currencies)));
        fp2t24.setModel((new ListModelList(currencies)));
        p4t24.setModel((new ListModelList(reason)));
        ap4t24.setModel((new ListModelList(reason)));
        
        if (sparam1 != null)
        {
     	  if (sparam1.equals("Go"))   /// ГО
     	  {
     		 btn_add.setVisible(false);
     	  }
        }
        
    refreshModel(_startPageNumber);

}

public void onPaging$debetPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    debetPaging.setPageSize(_pageSize);
    filter.setP1t24(idn);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter, "");
    //    _needsTotalSizeUpdate = false;
}

debetPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Debet) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Debet getCurrent() {
return current;
}

public void setCurrent(Debet current) {
this.current = current;

if ((current != null)&&(current.getP4t24()==1)) {
	row_p5t24.setVisible(true);
	row_p6t24.setVisible(true);
	row_p7t24.setVisible(true);
	row_p8t24.setVisible(false);
	row_p9t24.setVisible(false);
	row_p10t24.setVisible(false);
	
} else if ((current != null)&&(current.getP4t24()==2)) {
	row_p5t24.setVisible(false);
	row_p6t24.setVisible(false);
	row_p7t24.setVisible(false);
	row_p8t24.setVisible(false);
	row_p9t24.setVisible(false);
	row_p10t24.setVisible(false);
	row_p11t24.setVisible(true);
	row_p12t24.setVisible(true);

} else if ((current != null)&&(current.getP4t24()==6)) {
row_p5t24.setVisible(false);
row_p6t24.setVisible(false);
row_p7t24.setVisible(false);
row_p8t24.setVisible(true);
row_p9t24.setVisible(true);
row_p10t24.setVisible(true);
}
}



public void onDoubleClick$dataGrid$grd() {
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            if (frmgrd.isVisible()){
        		  InputElement[] list = {id,p1t24 };
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


public void onClick$btn_add() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
    ap1t24.setValue(idn);
    
    ap14t24.setValue(p65t1); ap14t24.setDisabled(true);
    ap15t24.setValue(p67t1); ap15t24.setDisabled(true);
    
    
    ap17t24.setValue((String)(session.getAttribute("un")));
    ap16t24.setValue(new Date());
    
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
	com.sbs.service.DebetResult deb =  new com.sbs.service.DebetResult();
	
	if(addgrd.isVisible()){
        
		DebetResult ar = ws.saveDebet(
				((String)(session.getAttribute("BankINN"))) , idn , getDeb(
		    new Debet(
            ap2t24.getValue(),
            ap3t24.doubleValue(),
            Integer.parseInt(ap4t24.getValue()),
            ap5t24.getValue(),
            ap6t24.getValue(),
            ap8t24.getValue(),
            ap9t24.getValue(),
            ap11t24.getValue(),
            ap14t24.doubleValue(),
            ap15t24.doubleValue(),
            ap16t24.getValue(),
            ap17t24.getValue()
            )));
        CheckNull.clearForm(addgrd);
        if (ar.getStatus() == 0) 
        {
      	  refreshModel(_startPageNumber);
      	  alert("Сохранение успешно");
      	  refresh(idn);
      	  
        } 
        else 
        {
      	  alert("Error:"+ar.getStatus()+"; GTKid:" +ar.getGtkId()+ "; Text:" +ar.getErrorMsg());
        }
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new DebetFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP0t24(fp0t24.getValue());
          filter.setP1t24(fp1t24.getValue());
          filter.setP2t24(fp2t24.getValue());
          filter.setP3t24(fp3t24.doubleValue());
          filter.setP4t24(fp4t24.getValue());
          filter.setP5t24(fp5t24.getValue());
          filter.setP6t24(fp6t24.getValue());
          filter.setP7t24(fp7t24.getValue());
          filter.setP8t24(fp8t24.getValue());
          filter.setP9t24(fp9t24.getValue());
          filter.setP10t24(fp10t24.getValue());
          filter.setP11t24(fp11t24.getValue());
          filter.setP12t24(fp12t24.getValue());
          filter.setP13t24(fp13t24.getValue());
          filter.setP14t24(fp14t24.doubleValue());
          filter.setP15t24(fp15t24.doubleValue());
          filter.setP16t24(fp16t24.getValue());

    }else{
        
          Long.parseLong(id.getValue());
          current.setP0t24(p0t24.getValue());
          current.setP1t24(p1t24.getValue());
          current.setP2t24(p2t24.getValue());
          current.setP3t24(p3t24.doubleValue());
          current.setP4t24(Integer.parseInt(p4t24.getValue()));
          current.setP5t24(p5t24.getValue());
          current.setP6t24(p6t24.getValue());
          current.setP7t24(p7t24.getValue());
          current.setP8t24(p8t24.getValue());
          current.setP9t24(p9t24.getValue());
          current.setP10t24(p10t24.getValue());
          current.setP11t24(p11t24.getValue());
          current.setP12t24(p12t24.getValue());
          current.setP13t24(p13t24.getValue());
          current.setP14t24(p14t24.doubleValue());
          current.setP15t24(p15t24.doubleValue());
          current.setP16t24(p16t24.getValue());
       
          DebetResult deb1 = ws.saveDebet(((String)(session.getAttribute("BankINN"))), idn ,  getDebCorr(current));
          if (deb1.getStatus() == 0) 
          {
        	  refreshModel(_startPageNumber);
        	  alert("Сохранение успешно");
        	  refresh(idn);
          } else {
        	  alert("Error:"+deb1.getStatus()+" - "+deb1.getErrorMsg()+ " GTKid "+deb1.getGtkId());
          }
          frmgrd.setVisible(true);
          addgrd.setVisible(false);
          fgrd.setVisible(false);
        
    
    }
          // DebetService.update(current);
    
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
			
			if (frmgrd.isVisible())
			{
			  Long.parseLong(id.getValue());
	          current.setP2t24(p2t24.getValue());
	          current.setP3t24(p3t24.doubleValue());
	          current.setP4t24(Integer.parseInt(p4t24.getValue()));
	          current.setP5t24(p5t24.getValue());
	          current.setP6t24(p6t24.getValue());
	          current.setP7t24(p7t24.getValue());
	          current.setP8t24(p8t24.getValue());
	          current.setP9t24(p9t24.getValue());
	          current.setP10t24(p10t24.getValue());
	          current.setP11t24(p11t24.getValue());
	          current.setP12t24(p12t24.getValue());
	          current.setP13t24(p13t24.getValue());
	          current.setP14t24(p14t24.doubleValue());
	          current.setP15t24(p15t24.doubleValue());
	          current.setP16t24(p16t24.getValue());
		  	
	          final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
	          DebetResult ar = ws.saveDebet(((String)(session.getAttribute("BankINN"))), idn , delDeb(current));
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
            filter = new DebetFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

private com.sbs.service.Debet getDeb(Debet deb) throws Exception
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  java.util.Calendar cal3 = java.util.Calendar.getInstance();
	  java.util.Calendar cal4 = java.util.Calendar.getInstance();
	  com.sbs.service.Debet res = new com.sbs.service.Debet();
	  
	  res.setP0T24(0);
	  res.setP2T24(deb.getP2t24());
	  
	  if (!CheckNull.isEmpty(deb.getP16t24())) {
		  cal.setTime(df.parse(df.format(deb.getP16t24())));
		  res.setP16T24(cal);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP12t24())) {
		  cal2.setTime(df.parse(df.format(deb.getP12t24())));
		  res.setP12T24(cal2);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP7t24())) {
		  cal3.setTime(df.parse(df.format(deb.getP7t24())));
		  res.setP7T24(cal3);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP10t24())) {
		  cal4.setTime(df.parse(df.format(deb.getP10t24())));
		  res.setP10T24(cal4);
	  }
	  
	  res.setP3T24(deb.getP3t24());
	  
	  if (!CheckNull.isEmpty(deb.getP4t24())) res.setP4T24(Short.parseShort(String.valueOf(deb.getP4t24())));
	  if (!CheckNull.isEmpty(deb.getP5t24())) res.setP5T24(deb.getP5t24());
	  if (!CheckNull.isEmpty(deb.getP6t24())) res.setP6T24(deb.getP6t24());
	  if (!CheckNull.isEmpty(deb.getP11t24())) res.setP11T24(deb.getP11t24());
	  
	  res.setP13T24(deb.getP13t24());
	  
	  if (!CheckNull.isEmpty(deb.getP14t24())) res.setP14T24(deb.getP14t24());
	  if (!CheckNull.isEmpty(deb.getP15t24())) res.setP15T24(deb.getP15t24());
	  
	  res.setP17T24(deb.getP17t24());
	 
	  return res;
}

private com.sbs.service.Debet getDebCorr(Debet deb) throws Exception
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  java.util.Calendar cal3 = java.util.Calendar.getInstance();
	  java.util.Calendar cal4 = java.util.Calendar.getInstance();
	  com.sbs.service.Debet res = new com.sbs.service.Debet();
	  
	  res.setP0T24(1);
	  res.setP2T24(deb.getP2t24());
	  
	  if (!CheckNull.isEmpty(deb.getP16t24())) {
		  cal.setTime(df.parse(df.format(deb.getP16t24())));
		  res.setP16T24(cal);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP12t24())) {
		  cal2.setTime(df.parse(df.format(deb.getP12t24())));
		  res.setP12T24(cal2);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP7t24())) {
		  cal3.setTime(df.parse(df.format(deb.getP7t24())));
		  res.setP7T24(cal3);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP10t24())) {
		  cal4.setTime(df.parse(df.format(deb.getP10t24())));
		  res.setP10T24(cal4);
	  }
	  
	  res.setP3T24(deb.getP3t24());
	  if (!CheckNull.isEmpty(deb.getP4t24())) res.setP4T24(Short.parseShort(String.valueOf(deb.getP4t24())));
	  if (!CheckNull.isEmpty(deb.getP5t24())) res.setP5T24(deb.getP5t24());
	  if (!CheckNull.isEmpty(deb.getP6t24())) res.setP6T24(deb.getP6t24());
	  if (!CheckNull.isEmpty(deb.getP11t24())) res.setP11T24(deb.getP11t24());
	  
	  res.setP13T24(deb.getP13t24());
	  res.setP17T24(deb.getP17t24());
	 
	  return res;
}

private com.sbs.service.Debet delDeb(Debet deb) throws Exception
{
	  java.util.Calendar cal = java.util.Calendar.getInstance();
	  java.util.Calendar cal2 = java.util.Calendar.getInstance();
	  java.util.Calendar cal3 = java.util.Calendar.getInstance();
	  java.util.Calendar cal4 = java.util.Calendar.getInstance();
	  com.sbs.service.Debet res = new com.sbs.service.Debet();
	  
	  res.setP0T24(2);
	  res.setP2T24(deb.getP2t24());
	  
	  if (!CheckNull.isEmpty(deb.getP16t24())) {
		  cal.setTime(df.parse(df.format(deb.getP16t24())));
		  res.setP16T24(cal);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP12t24())) {
		  cal2.setTime(df.parse(df.format(deb.getP12t24())));
		  res.setP12T24(cal2);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP7t24())) {
		  cal3.setTime(df.parse(df.format(deb.getP7t24())));
		  res.setP7T24(cal3);
	  }
	  
	  if (!CheckNull.isEmpty(deb.getP10t24())) {
		  cal4.setTime(df.parse(df.format(deb.getP10t24())));
		  res.setP10T24(cal4);
	  }
	  
	  res.setP3T24(deb.getP3t24());
	  if (!CheckNull.isEmpty(deb.getP4t24())) res.setP4T24(Short.parseShort(String.valueOf(deb.getP4t24())));
	  if (!CheckNull.isEmpty(deb.getP5t24())) res.setP5T24(deb.getP5t24());
	  if (!CheckNull.isEmpty(deb.getP6t24())) res.setP6T24(deb.getP6t24());
	  if (!CheckNull.isEmpty(deb.getP11t24())) res.setP11T24(deb.getP11t24());
	  
	  res.setP13T24(deb.getP13t24());
	  res.setP17T24(deb.getP17t24());
	 
	  return res;
}

public void onSelect$p4t24() {
	if (p4t24.getValue().equalsIgnoreCase("1")) {
		row_p5t24.setVisible(true);
		row_p6t24.setVisible(true);
		row_p7t24.setVisible(true);
		row_p8t24.setVisible(false);
		row_p9t24.setVisible(false);
		row_p10t24.setVisible(false);
		row_p11t24.setVisible(false);
		row_p12t24.setVisible(false);
		
	} else if (p4t24.getValue().equalsIgnoreCase("2")) {
		row_p5t24.setVisible(false);
		row_p6t24.setVisible(false);
		row_p7t24.setVisible(false);
		row_p8t24.setVisible(false);
		row_p9t24.setVisible(false);
		row_p10t24.setVisible(false);
		row_p11t24.setVisible(true);
		row_p12t24.setVisible(true);
	
} else if (p4t24.getValue().equalsIgnoreCase("6")) {
	row_p5t24.setVisible(false);
	row_p6t24.setVisible(false);
	row_p7t24.setVisible(false);
	row_p8t24.setVisible(true);
	row_p9t24.setVisible(true);
	row_p10t24.setVisible(true);
	row_p11t24.setVisible(false);
	row_p12t24.setVisible(false);
}
}
public void onSelect$ap4t24() {
	if (ap4t24.getValue().equalsIgnoreCase("1")) {
		row_ap5t24.setVisible(true);
		row_ap6t24.setVisible(true);
		row_ap7t24.setVisible(true);
		row_ap8t24.setVisible(false);
		row_ap9t24.setVisible(false);
		row_ap10t24.setVisible(false);
		row_ap11t24.setVisible(false);
		row_ap12t24.setVisible(false);
		
	} else if (ap4t24.getValue().equalsIgnoreCase("2")) {
		row_ap5t24.setVisible(false);
		row_ap6t24.setVisible(false);
		row_ap7t24.setVisible(false);
		row_ap8t24.setVisible(false);
		row_ap9t24.setVisible(false);
		row_ap10t24.setVisible(false);
		row_ap11t24.setVisible(true);
		row_ap12t24.setVisible(true);
	
} else if (ap4t24.getValue().equalsIgnoreCase("6")) {
	row_ap5t24.setVisible(false);
	row_ap6t24.setVisible(false);
	row_ap7t24.setVisible(false);
	row_ap8t24.setVisible(true);
	row_ap9t24.setVisible(true);
	row_ap10t24.setVisible(true);
	row_ap11t24.setVisible(false);
	row_ap12t24.setVisible(false);
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
			
		      p2t24 .setDisabled(false);
	          p3t24 .setDisabled(false);
	          p4t24 .setDisabled(false);
	          p5t24 .setDisabled(false);
	          p6t24 .setDisabled(false);
	          p7t24 .setDisabled(false);
	          p8t24 .setDisabled(false);
	          p9t24 .setDisabled(false);
	          p10t24.setDisabled(false);
	          p11t24.setDisabled(false);
	          p12t24.setDisabled(false);
	          p13t24.setDisabled(false);
	          p14t24.setDisabled(false);
	          p15t24.setDisabled(false);
	          p16t24.setDisabled(false);
	        
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
		sendConfirm(1, String.valueOf(current.getP13t24()), current);
	}
	
	public void onClick$btn_reject() {
		sendConfirm(0, String.valueOf(current.getP13t24()), current);
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
		 p2t24 .setDisabled(param);
         p3t24 .setDisabled(param);
         p4t24 .setDisabled(param);
         p5t24 .setDisabled(param);
         p6t24 .setDisabled(param);
         p7t24 .setDisabled(param);
         p8t24 .setDisabled(param);
         p9t24 .setDisabled(param);
         p14t24.setDisabled(param);
         p15t24.setDisabled(param);
         p16t24.setDisabled(param);
	 }
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.is.tf.debet.DebetService.remove(new Debet() ,idc);
		com.sbs.service.DebetsResult debi = ws.getDebets(((String)(session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(debi, "c:/Debet1.xml");
		
		if (debi.getStatus()==0)
		{
			 for (int i=0;i<debi.getDebets().length;i++)
			 {
				com.is.tf.debet.DebetService.create(debi.getDebets()[i], idn, idc);
			 }
		 } else {
			 alert("ERROR:"+debi.getErrorMsg()+":  Status="+debi.getStatus()+": GtkId="+debi.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
			 ISLogger.getLogger().warn("ERROR onSelect$Compensation:"+debi.getErrorMsg()+":  Status="+debi.getStatus()+": GtkId="+debi.getGtkId());
		 }
   	 	 refreshModel(_startPageNumber);
		
	}
}



