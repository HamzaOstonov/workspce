package com.is.comjpayment;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import com.is.comcustomer.ComCustomer;
import com.is.comserviceslist.ComServicesList;
import com.is.comserviceslist.ComServicesListService;
import com.is.utils.Res;
import com.is.utils.CheckNull;

import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
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
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Row;
import org.zkoss.zul.event.PagingEvent;
import com.is.utils.RefCBox;


import com.is.providers.SrvPortTypeProxy;
import com.is.providers.AddInfo;
import com.is.providers.Credentials;
import com.is.providers.PayResp;
import com.is.providers.Payment;
import com.is.providers.BaseProvider;
import com.is.providers.Beeline;
import com.is.utils.Res;
import com.is.utils.CheckNull;

public class ComJpaymentViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid frmgrd,fgrd, faddgrd, addgrd1, adddata_grid;
    private Vbox addgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    
    private Textbox id,p_name,p_number,from_value,to_value,difference,penalty_amount,amount,full_amount,currency,provider_amount,fee_amount,dealid,state,customerj_id,document_id,transaction_id,branch_id,subbranch_id,date_complete,operation_id,parentid,parentgroupid,payment_type_id,prt_id,client_address,provider_discount_amount,budget_inn,budget_account;
    private Textbox aid,ap_name,ap_number,afrom_value,ato_value,adifference,apenalty_amount,aamount,afull_amount,acurrency,aprovider_amount,afee_amount,acustomer_id,adealid,astate,acustomerj_id,adocument_id,atransaction_id,abranch_id,asubbranch_id,adate_complete,aoperation_id,aparentid,aparentgroupid,apayment_type_id,aprt_id,aclient_address,aprovider_discount_amount,abudget_inn,abudget_account ;
    private Textbox fid,fservices_list_id,fp_name,fp_number,ffrom_date,fto_date,ffrom_value,fto_value,fdifference,fpenalty_amount,famount,ffull_amount,fcurrency,fprovider_amount,ffee_amount,fcustomer_id,fdealid,fstate,fcustomerj_id,fdocument_id,ftransaction_id,fbranch_id,fsubbranch_id,fdate_complete,foperation_id,fparentid,fparentgroupid,fpayment_type_id,fprt_id,fclient_address,fprovider_discount_amount,fbudget_inn,fbudget_account ;
    private Paging comjpaymentPaging;
    private RefCBox services_list_id, aservices_list_id, fcategory_id, fregion, fdistr, customer_id,region_id,aregion_id,district_id,adistrict_id;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private Datebox afrom_date, ato_date, from_date, to_date;
    
    public ComJpaymentFilter filter = new ComJpaymentFilter();

    private PagingListModel model = null;
    private ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private String alias;
    private static HashMap< Integer,ComServicesList> servmask = new HashMap< Integer,ComServicesList>();
    private ComServicesList csl;

    private Row rp_name;
    private Row rp_number;
    private Row rfrom_date;
    private Row rto_date;
    private Row rfrom_value;
    private Row rto_value;
    private Row rdifference;
    private Row rpenalty_amount;
    private Row ramount;
    private Row rclient_address;
    private Row rap_name;
    private Row rap_number;
    private Row rafrom_date;
    private Row rato_date;
    private Row rafrom_value;
    private Row rato_value;
    private Row radifference;
    private Row rapenalty_amount;
    private Row raamount;
    private Row raclient_address,rdistrict_id,radistrict_id;
    private String un,pwd,branch;
    
    private Row addinforow;
    private Label addinfoLabel;
    private Rows adddata_rows;
    
    private Boolean fl_set_region, fl_setdist, fl_set_category;

    private SrvPortTypeProxy munis;

   private ComJpayment current = new ComJpayment();
   private ComCustomer current_customer;

   private Toolbarbutton btn_munis, btn_munis_pay, btn_beeline_pay;
   
    public ComJpaymentViewCtrl() {
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
    alias = (String) session.getAttribute("alias");
    un = (String) session.getAttribute("un");
    pwd= (String) session.getAttribute("pwd");
    branch= (String) session.getAttribute("branch");
    servmask = ComServicesListService.getHComServicesList(alias);

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                ComJpayment pComJpayment = (ComJpayment) data;

                row.setValue(pComJpayment);
               
                row.appendChild(new Listcell(pComJpayment.getId()+""));
                row.appendChild(new Listcell(servmask.get(pComJpayment.getServices_list_id()).getName()));
                row.appendChild(new Listcell(pComJpayment.getP_name()));
                row.appendChild(new Listcell(pComJpayment.getP_number()));
                /*
                row.appendChild(new Listcell(pComJpayment.getFrom_date()));
                row.appendChild(new Listcell(pComJpayment.getTo_date()));
                row.appendChild(new Listcell(pComJpayment.getFrom_value()));
                row.appendChild(new Listcell(pComJpayment.getTo_value()));
                row.appendChild(new Listcell(pComJpayment.getDifference()));
                row.appendChild(new Listcell(pComJpayment.getPenalty_amount()));
                row.appendChild(new Listcell(pComJpayment.getAmount()));
                row.appendChild(new Listcell(pComJpayment.getFull_amount()));
                row.appendChild(new Listcell(pComJpayment.getCurrency()));
                row.appendChild(new Listcell(pComJpayment.getProvider_amount()));
                row.appendChild(new Listcell(pComJpayment.getFee_amount()));
                row.appendChild(new Listcell(pComJpayment.getCustomer_id()));
                row.appendChild(new Listcell(pComJpayment.getDealId()));
                row.appendChild(new Listcell(pComJpayment.getState()));
                row.appendChild(new Listcell(pComJpayment.getCustomerj_id()));
                row.appendChild(new Listcell(pComJpayment.getDocument_id()));
                row.appendChild(new Listcell(pComJpayment.getTransaction_id()));
                row.appendChild(new Listcell(pComJpayment.getBranch_id()));
                row.appendChild(new Listcell(pComJpayment.getSubbranch_id()));
                row.appendChild(new Listcell(pComJpayment.getDate_complete()));
                row.appendChild(new Listcell(pComJpayment.getOperation_id()));
                row.appendChild(new Listcell(pComJpayment.getParentId()));
                row.appendChild(new Listcell(pComJpayment.getParentGroupId()));
                row.appendChild(new Listcell(pComJpayment.getPayment_type_id()));
                row.appendChild(new Listcell(pComJpayment.getPrt_id()));
                row.appendChild(new Listcell(pComJpayment.getClient_address()));
                row.appendChild(new Listcell(pComJpayment.getProvider_discount_amount()));
                row.appendChild(new Listcell(pComJpayment.getBudget_inn()));
                row.appendChild(new Listcell(pComJpayment.getBudget_account()));
*/

    }});
        
        services_list_id.setModel((new ListModelList(com.is.utils.RefDataService.getService(alias))));
        aservices_list_id.setModel((new ListModelList(com.is.utils.RefDataService.getService(alias))));
        fcategory_id.setModel((new ListModelList(com.is.utils.RefDataService.getCategory(alias))));
        fregion.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
        fdistr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
        customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomer(alias))));
        
        region_id.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
        district_id.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
        aregion_id.setModel((new ListModelList(com.is.utils.RefDataService.getRegion(alias))));
        adistrict_id.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(alias))));
        
        fl_set_region=false;
        fl_setdist=false;
        fl_set_category=false;

    refreshModel(_startPageNumber);
    
    munis = new SrvPortTypeProxy("http://128.10.10.25:8080/munis/services/Srv.SrvHttpSoap11Endpoint/");

}

public void onPaging$userPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    comjpaymentPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, alias);

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

comjpaymentPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(ComJpayment) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public ComJpayment getCurrent() {
return current;
}

public void setCurrent(ComJpayment current) {
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
            
            if (current!=null){
                csl = servmask.get(current.getServices_list_id());
                
                rp_name.setVisible(GetVisible(csl.getP_name_mask()));
                rp_number.setVisible(GetVisible(csl.getP_number_mask()));
                rfrom_date.setVisible(GetVisible(csl.getFrom_date_mask()));
                rto_date.setVisible(GetVisible(csl.getTo_date_mask()));
                rfrom_value.setVisible(GetVisible(csl.getFrom_value_mask()));
                rto_value.setVisible(GetVisible(csl.getTo_value_mask()));
                rdifference.setVisible(GetVisible(csl.getDifference_mask()));
                rpenalty_amount.setVisible(GetVisible(csl.getPenalty_amount_mask()));
                rclient_address.setVisible(GetVisible(csl.getClient_address_mask()));
                
                rap_name.setVisible(GetVisible(csl.getP_name_mask()));
                rap_number.setVisible(GetVisible(csl.getP_number_mask()));
                rafrom_date.setVisible(GetVisible(csl.getFrom_date_mask()));
                rato_date.setVisible(GetVisible(csl.getTo_date_mask()));
                rafrom_value.setVisible(GetVisible(csl.getFrom_value_mask()));
                rato_value.setVisible(GetVisible(csl.getTo_value_mask()));
                radifference.setVisible(GetVisible(csl.getDifference_mask()));
                rapenalty_amount.setVisible(GetVisible(csl.getPenalty_amount_mask()));
                raclient_address.setVisible(GetVisible(csl.getClient_address_mask()));
                
                Locale loc = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
                String lang = loc.getLanguage();
                
                HashMap< String,String> addInfo = ComJpaymentService.get_addinfo(current.getId(), current.getServices_list_id(), lang, alias);
                
                Iterator it = addInfo.entrySet().iterator();
                adddata_rows.getChildren().clear();
                adddata_grid.setVisible(true);
            	while(it.hasNext())
            	{
            		Map.Entry entry = (Map.Entry) it.next(); 
            		addinforow = new Row();
            		
            		addinfoLabel = new Label();
            		addinfoLabel.setValue((String)entry.getKey());
            		
            		addinforow.appendChild(addinfoLabel);
            		
            		addinfoLabel = new Label();
            		addinfoLabel.setValue((String)entry.getValue());
            		
            		addinforow.appendChild(addinfoLabel);
            		
            		adddata_rows.appendChild(addinforow);
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
   if (current!=null){
    csl = servmask.get(current.getServices_list_id());
    if(current==null)
    {
	    rp_name.setVisible(GetVisible(csl.getP_name_mask()));
	    rp_number.setVisible(GetVisible(csl.getP_number_mask()));
	    rfrom_date.setVisible(GetVisible(csl.getFrom_date_mask()));
	    rto_date.setVisible(GetVisible(csl.getTo_date_mask()));
	    rfrom_value.setVisible(GetVisible(csl.getFrom_value_mask()));
	    rto_value.setVisible(GetVisible(csl.getTo_value_mask()));
	    rdifference.setVisible(GetVisible(csl.getDifference_mask()));
	    rpenalty_amount.setVisible(GetVisible(csl.getPenalty_amount_mask()));
	    rclient_address.setVisible(GetVisible(csl.getClient_address_mask()));
	    rdistrict_id.setVisible(GetVisible(csl.getDistrict_mask()));
	    
	    rap_name.setVisible(GetVisible(csl.getP_name_mask()));
	    rap_number.setVisible(GetVisible(csl.getP_number_mask()));
	    rafrom_date.setVisible(GetVisible(csl.getFrom_date_mask()));
	    rato_date.setVisible(GetVisible(csl.getTo_date_mask()));
	    rafrom_value.setVisible(GetVisible(csl.getFrom_value_mask()));
	    rato_value.setVisible(GetVisible(csl.getTo_value_mask()));
	    radifference.setVisible(GetVisible(csl.getDifference_mask()));
	    rapenalty_amount.setVisible(GetVisible(csl.getPenalty_amount_mask()));
	    raclient_address.setVisible(GetVisible(csl.getClient_address_mask()));
	    radistrict_id.setVisible(GetVisible(csl.getDistrict_mask()));
    }
    
    //System.out.println("test______"+current.getServices_list_id());
    
    Locale loc = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
    String lang = loc.getLanguage();
    
    HashMap< String,String> addInfo = ComJpaymentService.get_addinfo(current.getId(), current.getServices_list_id(), lang, alias);
    
    Iterator it = addInfo.entrySet().iterator();
    adddata_rows.getChildren().clear();
    adddata_grid.setVisible(true);
	while(it.hasNext())
	{
		Map.Entry entry = (Map.Entry) it.next(); 
		addinforow = new Row();
		
		addinfoLabel = new Label();
		addinfoLabel.setValue((String)entry.getKey());
		
		addinforow.appendChild(addinfoLabel);
		
		addinfoLabel = new Label();
		addinfoLabel.setValue((String)entry.getValue());
		
		addinforow.appendChild(addinfoLabel);
		
		adddata_rows.appendChild(addinforow);
	}
   }
}

private String GetConstraint(int State)
{
	if (State==3)
	{
		return "\"no empty\"";
	}
	return "";
}

private Boolean GetVisible(int State)
{
	if (State==2)
	{
		return false;
	}
	return true;
}

public void onClick$btn_add() {
    //onDoubleClick$dataGrid$grd();
	 grd.setVisible(false);
     frm.setVisible(true);
     frmgrd.setVisible(true);
     addgrd.setVisible(false);
     fgrd.setVisible(false);
     btn_back.setImage("/images/folder.png");
     btn_back.setLabel(Labels.getLabel("grid"));
	////////////////////
	
    frmgrd.setVisible(false);
    addgrd.setVisible(true);
    fgrd.setVisible(false);
    adddata_grid.setVisible(false);
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
    adddata_grid.setVisible(false);
}


public void onClick$btn_save()
{
	int new_pay_id = pay();

	onClick$btn_back();
	refreshModel(_startPageNumber);
	SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	Events.sendEvent(evt);
	if (new_pay_id != -1)
	{
		onClick$btn_first();
		//current = ComJpaymentService.
		onDoubleClick$dataGrid$grd();
	}
}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new ComJpaymentFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd1);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}


public void onSelect$services_list_id()
{
	//current.setServices_list_id(Integer.parseInt(services_list_id.getValue()));
	
	 csl = servmask.get(Integer.parseInt(services_list_id.getValue()));
	    
	    rp_name.setVisible(GetVisible(csl.getP_name_mask()));
	    rp_number.setVisible(GetVisible(csl.getP_number_mask()));
	    rfrom_date.setVisible(GetVisible(csl.getFrom_date_mask()));
	    rto_date.setVisible(GetVisible(csl.getTo_date_mask()));
	    rfrom_value.setVisible(GetVisible(csl.getFrom_value_mask()));
	    rto_value.setVisible(GetVisible(csl.getTo_value_mask()));
	    rdifference.setVisible(GetVisible(csl.getDifference_mask()));
	    rpenalty_amount.setVisible(GetVisible(csl.getPenalty_amount_mask()));
	    rclient_address.setVisible(GetVisible(csl.getClient_address_mask()));
	    rdistrict_id.setVisible(GetVisible(csl.getDistrict_mask()));
}

public void onSelect$aservices_list_id()
{
	//current.setServices_list_id(Integer.parseInt(services_list_id.getValue()));
	
	 csl = servmask.get(Integer.parseInt(aservices_list_id.getValue()));
	    
	 	rap_name.setVisible(GetVisible(csl.getP_name_mask()));
	    rap_number.setVisible(GetVisible(csl.getP_number_mask()));
	    rafrom_date.setVisible(GetVisible(csl.getFrom_date_mask()));
	    rato_date.setVisible(GetVisible(csl.getTo_date_mask()));
	    rafrom_value.setVisible(GetVisible(csl.getFrom_value_mask()));
	    rato_value.setVisible(GetVisible(csl.getTo_value_mask()));
	    radifference.setVisible(GetVisible(csl.getDifference_mask()));
	    rapenalty_amount.setVisible(GetVisible(csl.getPenalty_amount_mask()));
	    raclient_address.setVisible(GetVisible(csl.getClient_address_mask()));
	    radistrict_id.setVisible(GetVisible(csl.getDistrict_mask()));
	    
}

public void onSelect$fcategory_id()
{
	fl_set_category=true;
	if(fl_setdist)
		{
			customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerDistCat(fdistr.getValue(), fcategory_id.getValue(), alias))));
		}
	else 
		{
			if(fl_set_region) 
				customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerRegionCat(fregion.getValue(), fcategory_id.getValue(), alias))));
			else customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerCat(fcategory_id.getValue(), alias))));
		}
}

public void onSelect$fregion()
{
	fl_set_region=true;
	if(fl_set_category)
	{
		if(fl_setdist)
			customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerDistCat(fdistr.getValue(), fcategory_id.getValue(), alias))));
		else customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerRegionCat(fregion.getValue(), fcategory_id.getValue(), alias))));
	}
else 
	{
		if(fl_setdist) 
			customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerDist(fdistr.getValue(), alias))));
		else customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerRegion(fregion.getValue(), alias))));
	}
	fdistr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(fregion.getValue(), alias))));
}

public void onSelect$region_id()
{
	district_id.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(region_id.getValue(), alias))));
}

public void onSelect$aregion_id()
{
	adistrict_id.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(aregion_id.getValue(), alias))));
}

public void onSelect$fdistr()
{
	fl_setdist=true;
	if(fl_set_category)
		customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerDistCat(fdistr.getValue(), fcategory_id.getValue(), alias))));
	else customer_id.setModel((new ListModelList(com.is.utils.RefDataService.getCustomerDist(fdistr.getValue(), alias))));
}

public void onSelect$customer_id()
{
	current_customer = com.is.comcustomer.ComCustomerService.getComCustomer(Integer.parseInt(customer_id.getValue()), alias);
	aservices_list_id.setModel((new ListModelList(com.is.utils.RefDataService.getService(customer_id.getValue(), alias))));
}




////////////////////// MUNIS ////////////////////////////////
/*
public void onClick$btn_munis(){
	
	try {
		com.is.providers.Res[]  rr =  munis.providerList();
		alert("Test "+rr[0].getName());
	} catch (RemoteException e) {
		
		e.printStackTrace();
	} 
}

public void onClick$btn_munis_pay(){
	
	try {
		Credentials cr = new Credentials("admin","admin");
		AddInfo addInfo = new AddInfo();
		Payment pay = new Payment();
		pay.setBranch("00444");
		pay.setAmount(1000);
		pay.setP_name("Марфа");
		pay.setService_id(67);
		pay.setFrom_date(df.parse("12.07.2013"));
		pay.setTo_date(df.parse("12.08.2013"));
		pay.setProvider_id(7);
		
		com.is.providers.PayResp  pr =  munis.pay(cr, pay, addInfo);
		alert("Pay Resp "+pr.getRs().getName());
	} catch (Exception e) {
		
		e.printStackTrace();
	} 
}

public void onClick$btn_beeline_pay(){
	BaseProvider jopa = new Beeline();
	PayResp pr = jopa.check(new Credentials(), new Payment());
	alert("Response "+pr.getRs().getCode());
}
*/
private int pay()
{
	ComJpayment new_cjp = null;
	int cur_id;
	try
	{
		new_cjp = new ComJpayment(
				Integer.parseInt(aservices_list_id.getValue()),
				ap_name.getValue(),
				ap_number.getValue(),
				afrom_date.getValue(),
				ato_date.getValue(),
				afrom_value.getValue(),
				ato_value.getValue(),
				adifference.getValue(),
				apenalty_amount.getValue()!=""?Integer.parseInt(apenalty_amount.getValue()):0,
				aamount.getValue()!=""?Integer.parseInt(aamount.getValue()):0,		
				//customer_id.getValue()!=""?Integer.parseInt(customer_id.getValue()):0,		
				customer_id.getValue()!=""?Integer.parseInt(customer_id.getValue()):0,
				aclient_address.getValue(),
				district_id.getValue()!=""?Integer.parseInt(district_id.getValue()):0,
				csl.getOperation_id()
		);
	//	Res RR=com.is.comjpayment.ComJpaymentService.doAction(un, pwd, new_cjp, 1, alias);
	//	cur_id = Integer.parseInt(RR.getName());
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return -1;
	}
	BaseProvider provider = null;
	try 
	{
		provider = (BaseProvider) Class.forName("com.is.providers."+current_customer.getProvider_class()).newInstance();
	}
	catch (Throwable e)
	{
	    e.printStackTrace();
	}
	provider.setEndPoint(current_customer.getProvider_url());
	Credentials cr = new Credentials("test","test");
	Payment pmt;
	pmt = new_cjp.get_payment();
	pmt.setBranch(branch);
	pmt.setDistrict(current_customer.getDistr());
	pmt.setProvider_id(current_customer.getId());
	
	PayResp pr = provider.check(cr, pmt);
	//alert("Response "+pr.getRs().getCode());
	
	//if (pr.getAddInfo()!=null)
	//ComJpaymentService.set_addinfo(pr.getAddInfo(), cur_id, alias);
	//return cur_id;
	return 0;
		
}



}
