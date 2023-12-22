package com.is.tf.compensation;

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
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.refobj.RefObjCBox;
import com.is.utils.refobj.RefObjData;
import com.is.utils.refobj.XMLSerializer;
import com.sbs.service.BankServiceProxy;
import com.sbs.service.CompensationResult;

public class CompensationViewCtrl extends GenericForwardComposer
{
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
    private Textbox id,p1t42,p0t42,p3t42,p15t42,id_contract,p16t42,p19t42,p20t42,p100t42;
    private Textbox aid,ap1t42,ap0t42,ap3t42,ap15t42,aid_contract,ap16t42,ap19t42,ap20t42,ap100t42 ;
    private Textbox fid,fp1t42,fp0t42,fp2t42,fp3t42,fp15t42,fid_contract,fp16t42,fp19t42,fp20t42,fp100t42 ;
    private Datebox p21t42,ap21t42,fp21t42,p6t42,ap6t42,fp6t42;
    private RefCBox p4t42,ap4t42,fp4t42;
    private RefObjCBox p2t42, ap2t42;
    private Decimalbox p5t42,p8t42,p9t42,p10t42,p11t42,p12t42,ap5t42,ap8t42,ap9t42,ap10t42,ap11t42,ap12t42,fp5t42,fp8t42,fp9t42,fp10t42,fp11t42,fp12t42;
    private Paging compensationPaging;
    private List<RefCurrencyData> coursecurrencies = new ArrayList<RefCurrencyData>();
    private RefCurrencyBox ap8t421, ap8t423 , ap10t421, ap10t423, p8t421, p8t423, p10t421, p10t423;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias, PolNameCompany;
    private String  idn,  val1 , val2, otn1, otn2, summa1, summa2;
    private long idc;
    private List<RefObjData> polis = new ArrayList<RefObjData>();
    private HashMap< String,String> curr_ = null;
    private Label aconr_val1,aconr_val2,conr_val1,conr_val1a,conr_val2,conr_val2a,cbcourse;
    private Row row_ap8t42, row_ap10t42,row_p8t42, row_p10t42;
    private Label line1;
    private Label line2;
    private Label line3;
    private Label line4;
    private Label line5;
    private Label line6;
    private Label line7;
    private Label line8;

    
    public CompensationFilter filter = new CompensationFilter();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    private Compensation current = new Compensation();
    private Window contractmain = null; 

    /* **************************************************************************/ 
    public CompensationViewCtrl() 
    {
            super('$', false, false);
    }

	@Override
	public void doAfterCompose(Component comp) throws Exception 
	{
        super.doAfterCompose(comp);
        // TODO Auto-generated method stub
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
	    
	    String[] parameter = (String[]) param.get("ht");
	    if (parameter!=null)
	    {
	            _pageSize = Integer.parseInt( parameter[0])/36;
	            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
	    }
	
	    parameter = (String[]) param.get("idn");
	    if (parameter!=null) idn = (parameter[0]);
	    
	    parameter = (String[]) param.get("idc");
	    if (parameter!=null) idc = Long.parseLong(parameter[0]);
	    
	    parameter = (String[]) param.get("val1");
	    if (parameter!=null) val1 = (parameter[0]);
	    System.out.println("val1->"+val1);
	    
	    parameter = (String[]) param.get("val2");
	    if (parameter!=null) val2 = (parameter[0]);
	    System.out.println("val2->"+val2);
	    
	    parameter = (String[]) param.get("summa1");
	    if (parameter!=null) summa1 = (parameter[0]);
	    
	    parameter = (String[]) param.get("summa2");
	    if (parameter!=null) summa2 = (parameter[0]);
	    
	    parameter = (String[]) param.get("spr");
	    if (parameter!=null) sparam1 = (parameter[0]);
	    
	    curr_ = com.is.tf.contract.ContractService.getHCurr(alias);
	    
	    conr_val1.setValue(curr_.get(val1));
	    conr_val2.setValue(curr_.get(val2));
	    
	    aconr_val1.setValue(curr_.get(val1));
	    aconr_val2.setValue(curr_.get(val2));
	    
	    line1.setValue(Labels.getLabel("compensation.p6t42tab"  ).replaceAll("<br>", "\r\n")); 
	    line2.setValue(Labels.getLabel("compensation.p5t42tab"  ).replaceAll("<br>", "\r\n")); 
	    line3.setValue(Labels.getLabel("compensation.p4t42tab"  ).replaceAll("<br>", "\r\n")); 
	    line4.setValue(Labels.getLabel("compensation.p2t42tab"  ).replaceAll("<br>", "\r\n")); 
	    line5.setValue(Labels.getLabel("compensation.p15t42tab" ).replaceAll("<br>", "\r\n")); 
	    line6.setValue(Labels.getLabel("compensation.p16t42tab" ).replaceAll("<br>", "\r\n")); 
	    line7.setValue(Labels.getLabel("compensation.p21t42tab" ).replaceAll("<br>", "\r\n")); 
	    line8.setValue(Labels.getLabel("compensation.p100t42tab").replaceAll("<br>", "\r\n"));
	
	    dataGrid.setItemRenderer(
		    new ListitemRenderer()
		    {
			    public void render(Listitem row, Object data) throws Exception 
			    {
			                Compensation pCompensation = (Compensation) data;
			
			                row.setValue(pCompensation);
			                
			                row.appendChild(new Listcell(pCompensation.getP6t42()+""));
			                row.appendChild(new Listcell(pCompensation.getP5t42()+""));
			                row.appendChild(new Listcell(com.is.tf.contract.SPR.getVal(String.valueOf(pCompensation.getP4t42()))));
			                row.appendChild(new Listcell(pCompensation.getP2t42()));
			                row.appendChild(new Listcell(pCompensation.getP15t42()));
			                row.appendChild(new Listcell(pCompensation.getP16t42()));
			                row.appendChild(new Listcell(pCompensation.getP21t42()+""));
			                row.appendChild(new Listcell(com.is.tf.contract.SPR.getP100Value(String.valueOf(pCompensation.getP100t42()))));
		                
		        }
			}
		 );
	    
	    
	        p4t42.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
	        ap4t42.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
	        fp4t42.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
	        
	        polis = com.is.tf.contract.ContractService.getPolicy(idn, alias); 
	        p2t42.setModel((new ListModelList(polis)));
	        ap2t42.setModel((new ListModelList(polis)));
	        
	        if (sparam1 != null)
	        {
		     	  if (sparam1.equals("Go"))   /// ГО
		     	  {
		     		 btn_add.setVisible(false);
		     	  }
	        }
	    refreshModel(_startPageNumber);
	}
	
	public void onPaging$compensationPaging(ForwardEvent event){
	final PagingEvent pe = (PagingEvent) event.getOrigin();
	_startPageNumber = pe.getActivePage();
	refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage){
	    compensationPaging.setPageSize(_pageSize);
	    filter.setP1t42(idn);
	model = new PagingListModel(activePage, _pageSize, filter, "");
	
	if(_needsTotalSizeUpdate) {
	        _totalSize = model.getTotalSize(filter, "");
	     //   _needsTotalSizeUpdate = false;
	}
	
	compensationPaging.setTotalSize(_totalSize);
	
	dataGrid.setModel((ListModel) model);
	if (model.getSize()>0){
	this.current =(Compensation) model.getElementAt(0);
	sendSelEvt();
	}
	}
	//Omitted...
	public Compensation getCurrent() {
	return current;
	}
	
	public void setCurrent(Compensation current) {
	this.current = current;
	}
	
	public void onDoubleClick$dataGrid$grd() {
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
	            
	            onChange$p4t42();
	            try
	            {
	            		setAddKurs("view");
		            	p2t42.setSelecteditem(current.getP2t42());
		            	p4t42.setSelecteditem(current.getP4t42());
		            
	            } 
	            catch (Exception e)
	            {
	            	e.printStackTrace();
	            }
	}
	
	public void onClick$btn_back()
	{
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
	
	public void onClick$btn_refresh() throws Exception 
	{
		refresh(idn);
	}
	
	public void onClick$btn_add() 
	{
	    onDoubleClick$dataGrid$grd();
	    frmgrd.setVisible(false);
	    addgrd.setVisible(true);
	    fgrd.setVisible(false);
	    ap1t42.setValue(idn);
	    ap6t42.setValue(new Date());
	    ap4t42.setSelecteditem(val1);
	    ap16t42.setValue((String)(session.getAttribute("un")));
	    
	    ap8t421.setSelecteditem(val1);
	    ap8t423.setSelecteditem(val2);
	    
	    ap10t421.setSelecteditem(val1);
	    ap10t423.setSelecteditem(val2);
	    onChange$ap4t42();
	    
	    
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
	    setAddKurs("add");
	}
	
	public void setAddKurs(String action)
	{
	  try
	  {
		if (action.equals("add"))
		{
			coursecurrencies = ContractService.getCourseCurr_22t1_23t1(idn, df.format(ap6t42.getValue()), alias);
	        ap8t421.setModel((new ListModelList(coursecurrencies)));
	        ap8t423.setModel((new ListModelList(coursecurrencies)));
	        ap10t421.setModel((new ListModelList(coursecurrencies)));
	        ap10t423.setModel((new ListModelList(coursecurrencies)));
		} 
		else
		{
			coursecurrencies = ContractService.getCourseCurr_22t1_23t1(idn, df.format(p6t42.getValue()), alias);
	        p8t421.setModel((new ListModelList(coursecurrencies)));
	        p8t423.setModel((new ListModelList(coursecurrencies)));
	        p10t421.setModel((new ListModelList(coursecurrencies)));
	        p10t423.setModel((new ListModelList(coursecurrencies)));
		}
	  } catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	}
	
	public void onClick$btn_search() {
	    onDoubleClick$dataGrid$grd();
	    frmgrd.setVisible(false);
	    addgrd.setVisible(false);
	    fgrd.setVisible(true);
	}
	
	public void onClick$btn_save() {
	try{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
		com.sbs.service.CompensationResult comp =  new com.sbs.service.CompensationResult();
		
		if(addgrd.isVisible()){
	            
			CompensationResult ar = ws.saveCompensation(
					((String)(session.getAttribute("BankINN"))) , idn ,
			getComp(new Compensation(
	            ap2t42.getValue(),
	            ap3t42.getValue(),
	            ap4t42.getValue(),
	            ap5t42.doubleValue(),
	            ap6t42.getValue(),
	            ap8t42.doubleValue(),
	            ap9t42.doubleValue(),
	            ap10t42.doubleValue(),
	            ap11t42.doubleValue(),
	            ap12t42.doubleValue(),
	            ap16t42.getValue()
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
	        filter = new CompensationFilter();
	        
	          Long.parseLong(fid.getValue());
	          filter.setP1t42(fp1t42.getValue());
	          filter.setP0t42(fp0t42.getValue());
	          filter.setP2t42(fp2t42.getValue());
	          filter.setP3t42(fp3t42.getValue());
	          filter.setP4t42(fp4t42.getValue());
	          filter.setP5t42(fp5t42.doubleValue());
	          filter.setP6t42(fp6t42.getValue());
	         
	          filter.setP8t42(fp8t42.doubleValue());
	          filter.setP9t42(fp9t42.doubleValue());
	          filter.setP10t42(fp10t42.doubleValue());
	          filter.setP11t42(fp11t42.doubleValue());
	          filter.setP12t42(fp12t42.doubleValue());
	          filter.setP15t42(fp15t42.getValue());
	
	    }else{
	    	  current.setP2t42(p2t42.getValue());
	          current.setP3t42(p3t42.getValue());
	          current.setP4t42(p4t42.getValue());
	          current.setP5t42(p5t42.doubleValue());
	          current.setP6t42(p6t42.getValue());
	          current.setP8t42(p8t42.doubleValue());
	          current.setP10t42(p10t42.doubleValue());
	          current.setP11t42(p11t42.doubleValue());
	          current.setP12t42(p12t42.doubleValue());
	          current.setP15t42(p15t42.getValue());
	          current.setP16t42(p16t42.getValue());
	          
	          CompensationResult comp1 = ws.saveCompensation(((String)(session.getAttribute("BankINN"))), idn , getCompCorr(current));
	          
	          if (comp1.getStatus() == 0) {
	        	  alert("Сохранение успешно");
	        	  refresh(idn);
	          } else {
	        	  alert("Error:"+comp1.getStatus()+" - "+comp1.getErrorMsg());
	          }
	          //CompensationService.update(current);
	    }
	CheckNull.clearForm(frmgrd);
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
			  current.setP2t42(p2t42.getValue());
	          current.setP3t42(p3t42.getValue());
	          current.setP4t42(p4t42.getValue());
	          current.setP5t42(p5t42.doubleValue());
	          current.setP6t42(p6t42.getValue());
	          current.setP8t42(p8t42.doubleValue());
	          current.setP10t42(p10t42.doubleValue());
	          current.setP11t42(p11t42.doubleValue());
	          current.setP12t42(p12t42.doubleValue());
	          current.setP15t42(p15t42.getValue());
	          current.setP16t42(p16t42.getValue());
	           
	          
		      CompensationResult ar = ws.saveCompensation(((String)(session.getAttribute("BankINN"))), idn , delComp(current));
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
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch(Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	public void refresh(String idn) throws Exception
	{
		final BankServiceProxy ws = new BankServiceProxy((String)session.getAttribute("YESVO_URL"));
   	  	com.is.tf.compensation.CompensationService.remove(new com.is.tf.compensation.Compensation(), idc);
		com.sbs.service.CompensationsResult comg = ws.getCompensations(((String)(session.getAttribute("BankINN"))), idn);
		XMLSerializer.write(comg, "c:/commpens1.xml");
			
		if (comg.getStatus()==0)
		{
			 for (int i=0;i<comg.getCompensations().length;i++)
			 {
				com.is.tf.compensation.CompensationService.create(comg.getCompensations()[i], idn, idc);
			 }
		 } else {
			 alert("ERROR:"+comg.getErrorMsg()+":  Status="+comg.getStatus()+": GtkId="+comg.getGtkId()+": BankINN="+((String)session.getAttribute("BankINN")));
			 ISLogger.getLogger().warn("ERROR onSelect$Compensation:"+comg.getErrorMsg()+":  Status="+comg.getStatus()+": GtkId="+comg.getGtkId());
		 }
   	 	 refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_cancel() {
	    if(fgrd.isVisible()){
	            filter = new CompensationFilter();
	    }
	onClick$btn_back();
	frmgrd.setVisible(true);
	addgrd.setVisible(false);
	fgrd.setVisible(false);
	CheckNull.clearForm(addgrd);
	CheckNull.clearForm(fgrd);
	refreshModel(_startPageNumber);
	}
	
	private com.sbs.service.Compensation getComp(Compensation comp) throws Exception
	{
		  java.util.Calendar cal = java.util.Calendar.getInstance();
		  com.sbs.service.Compensation res = new com.sbs.service.Compensation();
		  
		  res.setP0T42(0);
		  res.setP2T42(comp.getP2t42());
		  res.setP3T42(comp.getP3t42());
		  res.setP4T42(comp.getP4t42());
		  res.setP5T42(comp.getP5t42());
		  
		  cal.setTime(df.parse(df.format(comp.getP6t42())));
		  res.setP6T42(cal);
		  
		  System.out.println("comp.getP8t42()->"+comp.getP8t42());
		  System.out.println("comp.getP10t42()->"+comp.getP10t42());
		  
		  if (!(val1.equals(comp.getP4t42())) && !CheckNull.isEmpty(comp.getP8t42()) && comp.getP8t42() > 0) res.setP8T42(comp.getP8t42());
		  if (!(val2.equals(comp.getP4t42())) && !CheckNull.isEmpty(comp.getP10t42())&& comp.getP10t42() > 0) res.setP10T42(comp.getP10t42());
		  		  
		  if (!CheckNull.isEmpty(otn1) && !CheckNull.isEmpty(comp.getP8t42())&& comp.getP8t42() > 0) res.setP19T42(Short.parseShort(otn1));
		  if (!CheckNull.isEmpty(otn2) && !CheckNull.isEmpty(comp.getP10t42())&& comp.getP10t42() > 0) res.setP20T42(Short.parseShort(otn2));
		  
		  if (!CheckNull.isEmpty(comp.getP11t42())) res.setP11T42(comp.getP11t42());
		  if (!CheckNull.isEmpty(comp.getP12t42())) res.setP12T42(comp.getP12t42());
		  res.setP16T42(comp.getP16t42());
		 return res;
	}
	
	private com.sbs.service.Compensation getCompCorr(Compensation comp) throws Exception
	{
		  java.util.Calendar cal = java.util.Calendar.getInstance();
		  com.sbs.service.Compensation res = new com.sbs.service.Compensation();
		  
		  res.setP0T42(1);
		  res.setP2T42(comp.getP2t42());
		  res.setP3T42(comp.getP3t42());
		  res.setP4T42(comp.getP4t42());
		  res.setP5T42(comp.getP5t42());
		  
		  cal.setTime(df.parse(df.format(comp.getP6t42())));
		  res.setP6T42(cal);
		  
		  onChange$p11t42();
		  onChange$p12t42();
		  
		  if (!(val1.equals(comp.getP4t42())) && !CheckNull.isEmpty(comp.getP8t42()) && comp.getP8t42() > 0) res.setP8T42(comp.getP8t42());
		  if (!(val2.equals(comp.getP4t42())) && !CheckNull.isEmpty(comp.getP10t42())&& comp.getP10t42() > 0) res.setP10T42(comp.getP10t42());
		  		  
		  if (!CheckNull.isEmpty(otn1) && !CheckNull.isEmpty(comp.getP8t42())&& comp.getP8t42() > 0) res.setP19T42(Short.parseShort(otn1));
		  if (!CheckNull.isEmpty(otn2) && !CheckNull.isEmpty(comp.getP10t42())&& comp.getP10t42() > 0) res.setP20T42(Short.parseShort(otn2));
		  
		  if (!CheckNull.isEmpty(comp.getP11t42())) res.setP11T42(comp.getP11t42());
		  if (!CheckNull.isEmpty(comp.getP12t42())) res.setP12T42(comp.getP12t42());
		  res.setP15T42(Integer.parseInt(comp.getP15t42()));
		  res.setP16T42(comp.getP16t42());
		 return res;
	}
	
	private com.sbs.service.Compensation delComp(Compensation comp) throws Exception
	{
		  java.util.Calendar cal = java.util.Calendar.getInstance();
		  com.sbs.service.Compensation res = new com.sbs.service.Compensation();
		  
		  res.setP0T42(2);
		  res.setP2T42(comp.getP2t42());
		  res.setP3T42(comp.getP3t42());
		  res.setP4T42(comp.getP4t42());
		  res.setP5T42(comp.getP5t42());
		  
		  cal.setTime(df.parse(df.format(comp.getP6t42())));
		  res.setP6T42(cal);
		  
		  onChange$p11t42();
		  onChange$p12t42();
		  
		  if (!(val1.equals(comp.getP4t42())) && !CheckNull.isEmpty(comp.getP8t42()) && comp.getP8t42() > 0) res.setP8T42(comp.getP8t42());
		  if (!(val2.equals(comp.getP4t42())) && !CheckNull.isEmpty(comp.getP10t42())&& comp.getP10t42() > 0) res.setP10T42(comp.getP10t42());
		  		  
		  if (!CheckNull.isEmpty(otn1) && !CheckNull.isEmpty(comp.getP8t42())&& comp.getP8t42() > 0) res.setP19T42(Short.parseShort(otn1));
		  if (!CheckNull.isEmpty(otn2) && !CheckNull.isEmpty(comp.getP10t42())&& comp.getP10t42() > 0) res.setP20T42(Short.parseShort(otn2));
		  
		  if (!CheckNull.isEmpty(comp.getP11t42())) res.setP11T42(comp.getP11t42());
		  if (!CheckNull.isEmpty(comp.getP12t42())) res.setP12T42(comp.getP12t42());
		  res.setP15T42(Integer.parseInt(comp.getP15t42()));
		  res.setP16T42(comp.getP16t42());
		 return res;
	}
	
	public void onChange$ap11t42() 
	{
		//acountCourse(false);
		if (ap5t42.doubleValue() > ap11t42.doubleValue())
		{
			  otn1=("0");
		} 
		else if (ap5t42.doubleValue() < ap11t42.doubleValue())
		{
		      otn1=("1");
		}
	}
	
	public void onChange$ap12t42() 
	{
		//acountCourse(false);
		if (ap5t42.doubleValue() > ap12t42.doubleValue())
		{
			  otn2=("0");
		} 
		else if (ap5t42.doubleValue() < ap12t42.doubleValue())
		{
		      otn2=("1");
		}
	}
	
	public void onChange$p11t42() 
	{
		//acountCourse(false);
		if (p5t42.doubleValue() > p11t42.doubleValue())
		{
			  otn1=("0");
		} 
		else if (p5t42.doubleValue() < p11t42.doubleValue())
		{
		      otn1=("1");
		}
	}
	
	public void onChange$p12t42() 
	{
		//acountCourse(false);
		if (p5t42.doubleValue() > p12t42.doubleValue())
		{
			  otn2=("0");
		} 
		else if (p5t42.doubleValue() < p12t42.doubleValue())
		{
		      otn2=("1");
		}
	}

	public void onChange$ap2t42() 
	{
		com.is.tf.policy.Policy pol = (com.is.tf.policy.Policy)ap2t42.getObject();
		PolNameCompany = pol.getP4t32();
		ap3t42.setValue(PolNameCompany);
	}
	
	public void onChange$p2t42() 
	{
		com.is.tf.policy.Policy pol = (com.is.tf.policy.Policy)p2t42.getObject();
		PolNameCompany = pol.getP4t32();
		p3t42.setValue(PolNameCompany);
	}
	
	public void onChange$ap6t42()
	{
		setAddKurs("add");
	}
	
	public void onChange$p6t42()
	{
		setAddKurs("view");
	}
	
	public void onChange$ap4t42() 
	{
		if (ap4t42.getValue().equals(val1)) 
			  row_ap8t42.setVisible(false);
			else row_ap8t42.setVisible(true);
		if (ap4t42.getValue().equals(val2)) row_ap10t42.setVisible(false);
			else row_ap10t42.setVisible(true);
	}
	
	public void onChange$p4t42() 
	{
		if (p4t42.getValue().equals(val1)) row_p8t42.setVisible(false);
			else row_p8t42.setVisible(true);
		if (p4t42.getValue().equals(val2)) row_p10t42.setVisible(false);
			else row_p10t42.setVisible(true);
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
	
	public void onClick$btn_confirm() {
		sendConfirm(1, String.valueOf(current.getP15t42()), current);
	}
	
	public void onClick$btn_reject() {
		sendConfirm(0, String.valueOf(current.getP15t42()), current);
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
		 p2t42.setDisabled(param);
         p3t42.setDisabled(param);
         p4t42.setDisabled(param);
         p5t42.setDisabled(param);
         p6t42.setDisabled(param);
         p8t42.setDisabled(param);
        p10t42.setDisabled(param);
        p11t42.setDisabled(param);
        p12t42.setDisabled(param);
     }
}
