package com.is.bpri;


import java.text.SimpleDateFormat;

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
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;


public class LdProductViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Paging contactPaging;
    private Div grd;
    private Listbox dataGrid;
    private Hbox addgrd,frmgrd;
    private Grid addgrdl, addgrdr,frmgrdl,frmgrdr,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Textbox id,ld_num,crc_num,prod_name,target;
    private Textbox aid,ald_num,acrc_num,aprod_name,atarget ;
    private Textbox fid,fcurrency,fld_num,fcrc_num,fshifr_id,fprod_name,fsred_id,ftarget,fcalc_id,fterm_type,ftype_id,fkred_id,fklass_id,fstatus,fklassp_id ;
    private RefCBox currency,shifr_id,sred_id,kred_id,term_type,type_id,klass_id,status,klassp_id,calc_id;
    private RefCBox acurrency,ashifr_id,asred_id,akred_id,aterm_type,atype_id,aklass_id,astatus,aklassp_id,acalc_id;

    private Paging ldproductPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;


    public LdProductFilter filter;

    private LdPagingListModel model = null;
    private ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private String alias;


private LdProduct current = new LdProduct();

    public LdProductViewCtrl() {
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


        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                LdProduct pLdProduct = (LdProduct) data;

                row.setValue(pLdProduct);
                
                row.appendChild(new Listcell(pLdProduct.getId()+""));
                row.appendChild(new Listcell(pLdProduct.getCurrency()));
                row.appendChild(new Listcell(pLdProduct.getLd_num()));
                row.appendChild(new Listcell(pLdProduct.getCrc_num()));
                row.appendChild(new Listcell(pLdProduct.getShifr_id()));
                row.appendChild(new Listcell(pLdProduct.getProd_name()));
                row.appendChild(new Listcell(pLdProduct.getSred_id()));
                row.appendChild(new Listcell(pLdProduct.getTarget()));
                row.appendChild(new Listcell(pLdProduct.getCalc_id()+""));
                row.appendChild(new Listcell(pLdProduct.getTerm_type()+""));
                row.appendChild(new Listcell(pLdProduct.getType_id()+""));
                row.appendChild(new Listcell(pLdProduct.getKred_id()));
                row.appendChild(new Listcell(pLdProduct.getKlass_id()));
                row.appendChild(new Listcell(pLdProduct.getStatus()+""));
                row.appendChild(new Listcell(pLdProduct.getKlassp_id()));


    }});

         status.setModel((new ListModelList(com.is.bpri.LdProductService.getStatus(alias))));
         currency.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
         shifr_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSshifr(alias))));
         sred_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSsred(alias))));
         kred_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSScredit(alias))));
         klass_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSklass(alias))));
         klassp_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSklassp(alias))));
         type_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSint(alias))));
         term_type.setModel((new ListModelList(com.is.bpri.LdProductService.getSsrokkr(alias))));
         calc_id.setModel((new ListModelList(com.is.bpri.LdProductService.getCalcmetod(alias))));

         astatus.setModel((new ListModelList(com.is.bpri.LdProductService.getStatus(alias))));
         acurrency.setModel((new ListModelList(com.is.utils.RefDataService.getCurrency(alias))));
         ashifr_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSshifr(alias))));
         asred_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSsred(alias))));
         akred_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSScredit(alias))));
         aklass_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSklass(alias))));
         aklassp_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSklassp(alias))));
         atype_id.setModel((new ListModelList(com.is.bpri.LdProductService.getSint(alias))));
         aterm_type.setModel((new ListModelList(com.is.bpri.LdProductService.getSsrokkr(alias))));
         acalc_id.setModel((new ListModelList(com.is.bpri.LdProductService.getCalcmetod(alias))));
        
        
        
    refreshModel(_startPageNumber);

}

public void onPaging$ldproductPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    ldproductPaging.setPageSize(_pageSize);
model = new LdPagingListModel(activePage, _pageSize, filter, alias);


        _totalSize = model.getTotalSize(filter, alias);


ldproductPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
dataGrid.setSelectedIndex(0);  
sendSelEvt();
}
}



//Omitted...
public LdProduct getCurrent() {
return current;
}

public void setCurrent(LdProduct current) {
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
}

public void onClick$btn_search() {
    onDoubleClick$dataGrid$grd();
    frmgrd.setVisible(false);
    addgrd.setVisible(false);
    fgrd.setVisible(true);
}


public void onClick$btn_save() {
try{
    if(addgrd.isVisible()){
            LdProductService.create(new LdProduct(
            
            0,//aid.getValue(),
            acurrency.getValue(),
            ald_num.getValue(),
            acrc_num.getValue(),
            ashifr_id.getValue(),
            aprod_name.getValue(),
            asred_id.getValue(),
            atarget.getValue(),
            !acalc_id.getValue().equals("")? Integer.parseInt(acalc_id.getValue()):0,
            !aterm_type.getValue().equals("")? Integer.parseInt(aterm_type.getValue()):0,
            !atype_id.getValue().equals("")? Integer.parseInt(atype_id.getValue()):0,
            akred_id.getValue(),
            aklass_id.getValue(),
            !astatus.getValue().equals("")? Integer.parseInt(astatus.getValue()):0,
            aklassp_id.getValue()
            ),alias);
        CheckNull.clearForm(addgrdl);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new LdProductFilter();
        /*
          filter.setId(fid.getValue());
          filter.setCurrency(fcurrency.getValue());
          filter.setLd_num(fld_num.getValue());
          filter.setCrc_num(fcrc_num.getValue());
          filter.setShifr_id(fshifr_id.getValue());
          filter.setProd_name(fprod_name.getValue());
          filter.setSred_id(fsred_id.getValue());
          filter.setTarget(ftarget.getValue());
          filter.setCalc_id(fcalc_id.getValue());
          filter.setTerm_type(fterm_type.getValue());
          filter.setType_id(ftype_id.getValue());
          filter.setKred_id(fkred_id.getValue());
          filter.setKlass_id(fklass_id.getValue());
          filter.setStatus(fstatus.getValue());
          filter.setKlassp_id(fklassp_id.getValue());
          */

    }else{
        
          //current.setId(id.getValue());
          current.setCurrency(currency.getValue());
          current.setLd_num(ld_num.getValue());
          current.setCrc_num(crc_num.getValue());
          current.setShifr_id(shifr_id.getValue());
          current.setProd_name(prod_name.getValue());
          current.setSred_id(sred_id.getValue());
          current.setTarget(target.getValue());
          current.setCalc_id(!calc_id.getValue().equals("")? Integer.parseInt(calc_id.getValue()):0);
          current.setTerm_type(!term_type.getValue().equals("")? Integer.parseInt( term_type.getValue()):0);
          current.setType_id(!type_id.getValue().equals("")? Integer.parseInt( type_id.getValue()):0);
          current.setKred_id(kred_id.getValue());
          current.setKlass_id(klass_id.getValue());
          current.setStatus(!status.getValue().equals("")? Integer.parseInt( status.getValue()):0);
          current.setKlassp_id(klassp_id.getValue());
        LdProductService.update(current,alias);
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
            filter = new LdProductFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrdl);
CheckNull.clearForm(addgrdr);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

}
