package com.is.trtemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
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
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.trpay.TrPay;
import com.is.trpay.TrPayViewCtrl.Cur_cont;
import com.is.utils.Res;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

public class TrTemplateViewCtrl extends GenericForwardComposer{
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
    private Button save_tieto_sum;
    private Toolbar tb;
    private Textbox id,operation_id,cash_code,purpose,ord,id_transh_purp,perc_for_trans,aperc_for_trans;
    private Textbox aid,aoperation_id,acash_code,apurpose,aord,aid_transh_purp, tieto_sum;
    private Textbox fid,foperation_id,facc_dt,facc_ct,fcurrency,fdoc_type,fcash_code,fpurpose,fpurpose_code,ford,fid_transh_purp;
    private Paging trtemplatePaging;
    private RefCBox moperation_id,acc_dt, card_rounding_type, arounding_type, rounding_type,
    acc_ct, doc_type, currency, purpose_code,aoperation_type,asuboperation_type;
    private RefCBox amoperation_id,aacc_dt,aacc_ct, adoc_type, acurrency, apurpose_code,operation_type,suboperation_type;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;
    
    private Toolbarbutton bt = null;
    

//    public TrTemplate current;
    public TrTemplateFilter filter = new TrTemplateFilter();
    
    private HashMap< String,String> cacheacc = new HashMap<String,String>();

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private TrTemplate current = new TrTemplate();

    public TrTemplateViewCtrl() {
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
    String[] group_id = (String[]) param.get("group_id");
    //System.out.println("exppar:"+param.get("exppar"));
    alias = (String) session.getAttribute("alias");
    if (parameter!=null){
            _pageSize = Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(Integer.parseInt( parameter[0])/36);
    }

    List<RefData>  rrr = com.is.utils.RefDataService.getAccTmpl(alias);
    for (int i=0;i<rrr.size();i++){
    	cacheacc.put(rrr.get(i).getData(), rrr.get(i).getLabel());
    	//System.out.println(rrr.get(i).getData()+" "+rrr.get(i).getLabel());
    }

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                TrTemplate pTrTemplate = (TrTemplate) data;
                Listcell lc = new Listcell();
                
                bt = new Toolbarbutton();
                bt.setLabel("удалить");
            	bt.setImage("/images/delete.png");
            	bt.setAttribute("template_id", pTrTemplate.getId());
            	bt.addEventListener(Events.ON_CLICK, new EventListener() {

					@Override
					public void onEvent(Event event)
							throws Exception 
					{
						Connection c = null;

			            try {
			                    c = ConnectionPool.getConnection(alias);
			                    PreparedStatement ps = c.prepareStatement("DELETE FROM BF_TR_TEMPLATE WHERE id=?");
			                    ps.setInt(1, (Integer)event.getTarget().getAttribute("template_id"));
			                    ps.executeQuery();	
			                    c.commit();
			            } catch (Exception e) {
			                    e.printStackTrace();
			            } finally {
			                    ConnectionPool.close(c);
			            }
			            refreshModel(_startPageNumber);
					}
            		 
            	 });
            	lc.appendChild(bt);
                row.setValue(pTrTemplate);
                row.appendChild(new Listcell(pTrTemplate.getOrd()+""));
                row.appendChild(new Listcell(cacheacc.get( pTrTemplate.getAcc_dt()+"" )));
                row.appendChild(new Listcell(cacheacc.get( pTrTemplate.getAcc_ct()+"" )));
                row.appendChild(new Listcell(pTrTemplate.getPurpose()));
                row.appendChild(lc);
                

    }});
        
        moperation_id.setModel((new ListModelList(com.is.utils.RefDataService.getOperation(144, alias))));
        
        acc_dt.setModel((new ListModelList(com.is.utils.RefDataService.getAccTmpl(alias))));
        acc_ct.setModel((new ListModelList(com.is.utils.RefDataService.getAccTmpl(alias))));
        
        aacc_dt.setModel((new ListModelList(com.is.utils.RefDataService.getAccTmpl(alias))));
        aacc_ct.setModel((new ListModelList(com.is.utils.RefDataService.getAccTmpl(alias))));

        doc_type.setModel((new ListModelList(com.is.utils.RefDataService.getTypeDoc(alias))));
        currency.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
        purpose_code.setModel((new ListModelList(com.is.utils.RefDataService.getPurposeCode(alias))));
        
        adoc_type.setModel((new ListModelList(com.is.utils.RefDataService.getTypeDoc(alias))));
        acurrency.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
        apurpose_code.setModel((new ListModelList(com.is.utils.RefDataService.getPurposeCode(alias))));
        
        operation_type.setModel((new ListModelList(com.is.utils.RefDataService.get_operation_type(alias))));
        suboperation_type.setModel((new ListModelList(com.is.utils.RefDataService.get_sub_operation_type(alias))));
        aoperation_type.setModel((new ListModelList(com.is.utils.RefDataService.get_operation_type(alias))));
        asuboperation_type.setModel((new ListModelList(com.is.utils.RefDataService.get_sub_operation_type(alias))));

        card_rounding_type.setModel((new ListModelList(com.is.utils.RefDataService.get_rounding_type(alias))));
        rounding_type.setModel((new ListModelList(com.is.utils.RefDataService.get_rounding_type(alias))));
        arounding_type.setModel((new ListModelList(com.is.utils.RefDataService.get_rounding_type(alias))));
        //  refreshModel(_startPageNumber);

}

public void onPaging$trtemplatePaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    trtemplatePaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize,filter,alias);

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter,alias);
        //_needsTotalSizeUpdate = false;
}

trtemplatePaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(TrTemplate) model.getElementAt(0);
sendSelEvt();
}
}

public void onClick$doc_type()
{
	doc_type.select();
}
public void onClick$currency()
{
	currency.select();
}
public void onClick$purpose_code()
{
	purpose_code.select();
}
public void onClick$adoc_type()
{
	adoc_type.select();
}
public void onClick$apurpose_code()
{
	apurpose_code.select();
}

//Omitted...
public TrTemplate getCurrent() {
return current;
}

public void setCurrent(TrTemplate current) {
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
            suboperation_type.setDisabled(true);
            asuboperation_type.setDisabled(true);
}

public void onSelect$operation_type()
{
	List<RefData> d;
	d = com.is.utils.RefDataService.get_sub_operation_type(alias,Integer.parseInt(operation_type.getValue()));
	suboperation_type.setDisabled(false);
	if(d.isEmpty())
	{
		d.add(new RefData("-1", ""));
		suboperation_type.setDisabled(true);
		suboperation_type.setModel((new ListModelList(d)));
		suboperation_type.setSelecteditem("-1");
		return;
	}
	suboperation_type.setModel((new ListModelList(d)));
	
}

public void onSelect$aoperation_type()
{
	List<RefData> d;
	d = com.is.utils.RefDataService.get_sub_operation_type(alias,Integer.parseInt(aoperation_type.getValue()));
	asuboperation_type.setDisabled(false);
	if(d.isEmpty())
	{
		d.add(new RefData("-1", ""));
		asuboperation_type.setDisabled(true);
		asuboperation_type.setModel((new ListModelList(d)));
		asuboperation_type.setSelecteditem("-1");
	}
	asuboperation_type.setModel((new ListModelList(d)));
	
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
    current = new TrTemplate();
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


public void onClick$btn_save() {
	Res res1;
try{
    if(addgrd.isVisible()){
    	res1=TrTemplateService.create(new TrTemplate(
            
            0,//aid.getValue(),
            Integer.parseInt(moperation_id.getValue()),
            Integer.parseInt(aacc_dt.getValue()),
            Integer.parseInt(aacc_ct.getValue()),
            acurrency.getValue(),
            adoc_type.getValue(),
            acash_code.getValue(),
            apurpose.getValue(),
            apurpose_code.getValue(),
            Integer.parseInt(aord.getValue()),
            Integer.parseInt(aid_transh_purp.getValue()),
            Integer.parseInt(aoperation_type.getValue()),
            Integer.parseInt(
            			CheckNull.isEmpty(asuboperation_type.getValue())?"-1":asuboperation_type.getValue()
            			),
            Double.parseDouble(aperc_for_trans.getValue()),
            Integer.parseInt(arounding_type.getValue())
            ),alias);
    	if (res1.getCode()!=0)
        {
      	  alert(res1.getName());
        }
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
        
    }else if(fgrd.isVisible()){
        filter = new TrTemplateFilter();
        
          filter.setId(Integer.parseInt(fid.getValue()));
          filter.setOperation_id(Integer.parseInt(foperation_id.getValue()));
          filter.setAcc_dt(Integer.parseInt(facc_dt.getValue()));
          filter.setAcc_ct(Integer.parseInt(facc_ct.getValue()));
          filter.setCurrency(fcurrency.getValue());
          filter.setDoc_type(fdoc_type.getValue());
          filter.setCash_code(fcash_code.getValue());
          filter.setPurpose(fpurpose.getValue());
          filter.setPurpose_code(fpurpose_code.getValue());
          filter.setOrd(Integer.parseInt(ford.getValue()));

    }else{
        
          //current.setId(id.getValue());
          //current.setOperation_id(Integer.parseInt(operation_id.getValue()));
          current.setAcc_dt(Integer.parseInt(acc_dt.getValue()));
          current.setAcc_ct(Integer.parseInt(acc_ct.getValue()));
          current.setCurrency(currency.getValue());
          current.setDoc_type(doc_type.getValue());
          current.setCash_code(cash_code.getValue());
          current.setPurpose(purpose.getValue());
          current.setPurpose_code(purpose_code.getValue());
          current.setOrd(Integer.parseInt(ord.getValue()));
          current.setId_transh_purp(Integer.parseInt(id_transh_purp.getValue())) ;
          current.setPay_type(Integer.parseInt(operation_type.getValue()));
          current.setTrans_type(Integer.parseInt(
        		  CheckNull.isEmpty(suboperation_type.getValue())?"-1":suboperation_type.getValue()
        		  ));
          current.setPerc_for_tr(Double.parseDouble(perc_for_trans.getValue()));
          current.setRounding_type(Integer.parseInt(rounding_type.getValue()));
          res1=TrTemplateService.update(current,alias);
          
          if (res1.getCode()!=0)
          {
        	  alert(res1.getName());
          }
    }
   
//onClick$btn_back();
refreshModel(_startPageNumber);
onClick$btn_cancel();
//SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
//Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new TrTemplateFilter();
    }
//onClick$btn_back();
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
//frmgrd.setVisible(true);
frm.setVisible(false);
addgrd.setVisible(false);
fgrd.setVisible(false);
grd.setVisible(true);
//refreshModel(_startPageNumber);
}
public void onSelect$moperation_id(Event evt) {
	filter.setOperation_id(Integer.parseInt(moperation_id.getValue()));
	refreshModel(_startPageNumber);
	save_tieto_sum.setDisabled(false);
	tieto_sum.setDisabled(false);
	
	card_rounding_type.setDisabled(false);
	
	Tieto_operation_percent pc = TrTemplateService.getOperation_tieto_percent(
			Long.parseLong(moperation_id.getValue()),
			alias
		);
	tieto_sum.setValue(Double.toString(pc.getPercent()));
	card_rounding_type.setSelecteditem(Integer.toString(pc.getRounding_type()));
}
public void onClick$save_tieto_sum()
{
	if(Double.parseDouble(tieto_sum.getValue()) > 100)
	{
		alert ("Нельзя положить на карточку больше денег чем у Вас есть.\n\n" +
				"Задайте процент меньше и равный 100.");
		return;
	}
	
	if(Double.parseDouble(tieto_sum.getValue()) <= 0)
	{
		alert ("Нельзя положить на карточку меньше 0 или ничего!\n\n" +
				"Для этого есть списание.\n\n" +
				"Задайте процент больше 0.");
		return;
	}
	
	TrTemplateService.update_operation_set_tieto_percent(Long.parseLong(moperation_id.getValue()), 
			new Tieto_operation_percent(
			Double.parseDouble(tieto_sum.getValue()),
			Integer.parseInt(card_rounding_type.getValue())), alias);	
	
	alert("Изменения сохранены.");
}

}
