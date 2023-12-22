package com.is.tf.debetinfo;

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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.DebetInfoResult;

public class DebetinfoViewCtrl extends GenericForwardComposer{
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
    private Textbox id,p6t31;
    private Textbox ap8t31,ap11t31,ap12t31,ap1t31,aid,ap6t31 ;
    private Textbox fid,fp6t31 ;
    private Decimalbox p3t31,ap3t31,fp3t31;
    private Datebox  ap13t31;
    private RefCBox ap4t31,p4t31,p2t31,fp2t31,ap2t31;
    private Intbox p0t31,p7t31,fp0t31,ap0t31,fp4t31,ap7t31,fp7t31;
    private List<RefData> currencies = new ArrayList<RefData>();
    private List<RefData> reason = new ArrayList<RefData>();
    private HashMap< String,String> curr_ =null;
    
    private Paging debetinfoPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    private long gid,idc;
    private String  idn, val1, val2,cu;
    private Label line1;						
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;
    
    public DebetinfoFilter filter= new DebetinfoFilter();
    
    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Debetinfo current = new Debetinfo();

    public DebetinfoViewCtrl() {
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
    parameter = (String[]) param.get("val2");
    if (parameter!=null){
 	val2 = (parameter[0]);
   //   System.out.println("Garant  cont_val2 "+val2);   
 }
    parameter = (String[]) param.get("spr");
    if (parameter!=null) sparam1 = (parameter[0]);
    
    curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
    filter.setP1t31(idn);
    
    line1.setValue(Labels.getLabel("debetinfo.p4t31tab"  ).replaceAll("<br>", "\r\n")); 
    line2.setValue(Labels.getLabel("debetinfo.p3t31tab"  ).replaceAll("<br>", "\r\n")); 
    line3.setValue(Labels.getLabel("debetinfo.p2t31tab"  ).replaceAll("<br>", "\r\n")); 
    line4.setValue(Labels.getLabel("debetinfo.p6t31tab"  ).replaceAll("<br>", "\r\n")); 
    line5.setValue(Labels.getLabel("debetinfo.p7t31tab"  ).replaceAll("<br>", "\r\n")); 
    line6.setValue(Labels.getLabel("debetinfo.p8t31tab"  ).replaceAll("<br>", "\r\n")); 
    line7.setValue(Labels.getLabel("debetinfo.p13t31tab" ).replaceAll("<br>", "\r\n")); 
    line8.setValue(Labels.getLabel("debetinfo.p100t31tab").replaceAll("<br>", "\r\n"));	

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Debetinfo pDebetInfo = (Debetinfo) data;

                row.setValue(pDebetInfo);
                
                row.appendChild(new Listcell(pDebetInfo.getP4t31()+""));
                row.appendChild(new Listcell(pDebetInfo.getP3t31()+""));
                row.appendChild(new Listcell(pDebetInfo.getP2t31()+""));
                row.appendChild(new Listcell(pDebetInfo.getP6t31()+""));
                row.appendChild(new Listcell(pDebetInfo.getP7t31()+""));
                row.appendChild(new Listcell(pDebetInfo.getP8t31()+""));
                row.appendChild(new Listcell(pDebetInfo.getP13t31()+""));
                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pDebetInfo.getP100t31()+""))));
                
                /*
	                row.appendChild(new Listcell(pDebetInfo.getId()+""));
	                row.appendChild(new Listcell(pDebetInfo.getP1t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP0t31()+""));
	                row.appendChild(new Listcell(pDebetInfo.getP2t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP3t31()+""));
	                row.appendChild(new Listcell(pDebetInfo.getP5t31()+""));
	                row.appendChild(new Listcell(pDebetInfo.getP6t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP7t31()+""));
	                row.appendChild(new Listcell(pDebetInfo.getP100t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP4t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP8t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP11t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP12t31()));
	                row.appendChild(new Listcell(pDebetInfo.getP13t31()+""));
                */
    }});
        currencies = com.is.tf.contract.ContractService.getMyCurr(idn, alias);
        reason= com.is.tf.contract.ContractService.getDebetmodes(alias);
        p2t31.setModel((new ListModelList(currencies)));
        ap2t31.setModel((new ListModelList(currencies)));
        //fp2t31.setModel((new ListModelList(currencies)));
        p4t31.setModel((new ListModelList(reason)));
        ap4t31.setModel((new ListModelList(reason)));
        
        if (sparam1 != null)
        {
	     	  if (sparam1.equals("Go"))   /// ГО
	     	  {
	     		 btn_add.setVisible(false);
	     	  }
        }
        
    refreshModel(_startPageNumber);

}

public void onPaging$debetinfoPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


	private void refreshModel(int activePage)
	{
	    debetinfoPaging.setPageSize(_pageSize);
	    filter.setP1t31(idn);
	    model = new PagingListModel(activePage, _pageSize, filter, "");
	
		if(_needsTotalSizeUpdate) _totalSize = model.getTotalSize(filter, "");
		debetinfoPaging.setTotalSize(_totalSize);
	
		dataGrid.setModel((ListModel) model);
		if (model.getSize()>0)
		{
			this.current =(Debetinfo) model.getElementAt(0);
			sendSelEvt();
		}
	}



//Omitted...
public Debetinfo getCurrent() {
return current;
}

public void setCurrent(Debetinfo current) {
this.current = current;
}

public void onDoubleClick$dataGrid$grd() 
{
            grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
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
    ap1t31.setValue(idn);
    ap8t31.setValue((String)(session.getAttribute("un")));
    
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
	com.sbs.service.DebetInfoResult dinf =  new com.sbs.service.DebetInfoResult();
    
	if(addgrd.isVisible())
	{
		DebetInfoResult ar = ws.saveDebetInfo(
				((String)(session.getAttribute("BankINN"))) , idn , getDebinf(
		  new Debetinfo(
		    ap2t31.getValue(),
            Double.parseDouble(String.valueOf(ap3t31.getValue())),
            ap4t31.getValue(),
            ap6t31.getValue(),
            ap8t31.getValue()
            ))
            );
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
        filter = new DebetinfoFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP0t31(fp0t31.getValue());
          filter.setP2t31(fp2t31.getValue());
          filter.setP3t31(fp3t31.doubleValue());
          filter.setP5t31(fp4t31.getValue());
          filter.setP6t31(fp6t31.getValue());
          filter.setP7t31(fp7t31.getValue());

    }else{
    	
	      current.setP2t31(p2t31.getValue());
	      current.setP3t31(p3t31.doubleValue());
	      current.setP4t31(p4t31.getValue());
	      current.setP6t31(p6t31.getValue());
	      current.setP7t31(p7t31.getValue());
     
          DebetInfoResult di = ws.saveDebetInfo(((String)(session.getAttribute("BankINN"))), idn , getDebinfCorr(current));
          
          if (di.getStatus() == 0) 
          {
        	  refreshModel(_startPageNumber);
        	  alert("Сохранение успешно");
        	  refresh(idn);
          } else 
        	  alert("Error:"+di.getStatus()+" - "+di.getErrorMsg()+ " GTKid "+ di.getGtkId());
          
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
		  current.setP2t31(p2t31.getValue());
	      current.setP3t31(p3t31.doubleValue());
	      current.setP4t31(p4t31.getValue());
	      current.setP6t31(p6t31.getValue());
	      current.setP7t31(p7t31.getValue());
	     
	      DebetInfoResult ar = ws.saveDebetInfo(((String)(session.getAttribute("BankINN"))), idn , delDebinf(current));
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
            filter = new DebetinfoFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

	private com.sbs.service.DebetInfo getDebinf(Debetinfo dinf)
	{
		  java.util.Calendar cal = java.util.Calendar.getInstance();
		  com.sbs.service.DebetInfo res = new com.sbs.service.DebetInfo();
		  
		  res.setP0T31(0);
		  if (!CheckNull.isEmpty(dinf.getP2t31()))  res.setP2T31(dinf.getP2t31());
		  
		  res.setP3T31(dinf.getP3t31());
		  
		  if (!CheckNull.isEmpty(dinf.getP4t31())) res.setP4T31(Short.parseShort(dinf.getP4t31()));
		  
		  res.setP6T31(dinf.getP6t31());
		  //res.setP7T31(dinf.getP7t31());
		  res.setP8T31((String)session.getAttribute("un"));
		  return res;
	}
	
	private com.sbs.service.DebetInfo getDebinfCorr(Debetinfo dinf)
	{
		  java.util.Calendar cal = java.util.Calendar.getInstance();
		  com.sbs.service.DebetInfo res = new com.sbs.service.DebetInfo();
		  
		  res.setP0T31(1);
		  if (!CheckNull.isEmpty(dinf.getP2t31()))  res.setP2T31(dinf.getP2t31());
		  
		  res.setP3T31(dinf.getP3t31());
		  
		  if (!CheckNull.isEmpty(dinf.getP4t31())) res.setP4T31(Short.parseShort(dinf.getP4t31()));
		  
		  res.setP6T31(dinf.getP6t31());
		  res.setP7T31(dinf.getP7t31());
		  res.setP8T31((String)session.getAttribute("un"));
		  return res;
	}
	
	private com.sbs.service.DebetInfo delDebinf(Debetinfo dinf)
	{
		  java.util.Calendar cal = java.util.Calendar.getInstance();
		  com.sbs.service.DebetInfo res = new com.sbs.service.DebetInfo();
		  
		  res.setP0T31(2);
		  if (!CheckNull.isEmpty(dinf.getP2t31()))  res.setP2T31(dinf.getP2t31());
		  
		  res.setP3T31(dinf.getP3t31());
		  
		  if (!CheckNull.isEmpty(dinf.getP4t31())) res.setP4T31(Short.parseShort(dinf.getP4t31()));
		  
		  res.setP6T31(dinf.getP6t31());
		  res.setP7T31(dinf.getP7t31());
		  res.setP8T31((String)session.getAttribute("un"));
		  return res;
	}
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.is.tf.debetinfo.DebetinfoService.remove(new Debetinfo() ,idc);
		com.sbs.service.DebetsInfoResult debi = ws.getDebetsInfo(((String)(session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(debi, "c:/Debetinf1.xml");
		
		if (debi.getStatus()==0)
		{
			 for (int i=0;i<debi.getDebetsInfo().length;i++)
			 {
				com.is.tf.debetinfo.DebetinfoService.create(debi.getDebetsInfo()[i], idn, idc);
			 }
		 } else {
			 alert("ERROR:"+debi.getErrorMsg()+":  Status="+debi.getStatus()+": GtkId="+debi.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
			 ISLogger.getLogger().warn("ERROR onSelect$Compensation:"+debi.getErrorMsg()+":  Status="+debi.getStatus()+": GtkId="+debi.getGtkId());
		 }
   	 	 refreshModel(_startPageNumber);
		
	}

	
	//*********************************** Confirm **************************************
	private Window contractmain = null; 
	public void onClick$btn_confirm() {
		sendConfirm(1, String.valueOf(current.getP7t31()), current);
	}
	
	public void onClick$btn_reject() {
		sendConfirm(0, String.valueOf(current.getP7t31()), current);
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
	
	public void setFields(boolean param)
	 {
		p2t31.setDisabled(param);
		p3t31.setDisabled(param);
		p4t31.setDisabled(param);
		p6t31.setDisabled(param);
	 }

}
