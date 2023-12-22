package com.is.tf.Accreditivtimechange;

import java.text.SimpleDateFormat;

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

public class AccreditivtimechangeViewCtrl extends GenericForwardComposer{
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
    private Toolbar tb;
    private Textbox id,p0t22,p1t22,p2t22,p3t22,p4t22,p5t22,p6t22,id_contract,p8t22,p9t22,p10t22,p100t22;
    private Textbox aid,ap0t22,ap1t22,ap2t22,ap3t22,ap4t22,ap5t22,ap6t22,aid_contract,ap8t22,ap9t22,ap10t22,ap100t22;
    private Textbox fid,fp0t22,fp1t22,fp2t22,fp3t22,fp4t22,fp5t22,fp6t22,fid_contract,fp8t22,fp9t22,fp10t22,fp100t22;
    private Datebox p11t22,ap11t22,fp11t22;
    private Intbox p7t22,ap7t22,fp7t22;
    private Paging accreditivtimechangePaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;

    
    public AccreditivtimechangeFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Accreditivtimechange current = new Accreditivtimechange();

    public AccreditivtimechangeViewCtrl() {
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



        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Accreditivtimechange pAccreditivtimechange = (Accreditivtimechange) data;

                row.setValue(pAccreditivtimechange);
                
                row.appendChild(new Listcell(pAccreditivtimechange.getId()+""));
                row.appendChild(new Listcell(pAccreditivtimechange.getP0t22()));
                row.appendChild(new Listcell(pAccreditivtimechange.getP1t22()));
                row.appendChild(new Listcell(pAccreditivtimechange.getP2t22()));
                row.appendChild(new Listcell(pAccreditivtimechange.getP3t22()));
                row.appendChild(new Listcell(pAccreditivtimechange.getP4t22()));
                row.appendChild(new Listcell(pAccreditivtimechange.getP5t22()));
                row.appendChild(new Listcell(pAccreditivtimechange.getP6t22()));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$accreditivtimechangePaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    accreditivtimechangePaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

accreditivtimechangePaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Accreditivtimechange) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Accreditivtimechange getCurrent() {
return current;
}

public void setCurrent(Accreditivtimechange current) {
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
            AccreditivtimechangeService.create(new Accreditivtimechange(
            
            Long.parseLong(aid.getValue()),
            ap0t22.getValue(),
            ap1t22.getValue(),
            ap2t22.getValue(),
            ap3t22.getValue(),
            ap4t22.getValue(),
            ap5t22.getValue(),
            ap6t22.getValue(),
            Long.parseLong(id_contract.getValue()),
            ap8t22.getValue(),
            ap7t22.getValue(),
            ap9t22.getValue(),
            ap10t22.getValue(),
            ap11t22.getValue(),
            ap100t22.getValue()
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new AccreditivtimechangeFilter();
        
          Long.parseLong(fid.getValue());
          filter.setP0t22(fp0t22.getValue());
          filter.setP1t22(fp1t22.getValue());
          filter.setP2t22(fp2t22.getValue());
          filter.setP3t22(fp3t22.getValue());
          filter.setP4t22(fp4t22.getValue());
          filter.setP5t22(fp5t22.getValue());
          filter.setP6t22(fp6t22.getValue());

    }else{
        
          Long.parseLong(id.getValue());
          current.setP0t22(p0t22.getValue());
          current.setP1t22(p1t22.getValue());
          current.setP2t22(p2t22.getValue());
          current.setP3t22(p3t22.getValue());
          current.setP4t22(p4t22.getValue());
          current.setP5t22(p5t22.getValue());
          current.setP6t22(p6t22.getValue());
          Long.parseLong(id_contract.getValue());
          current.setP8t22(p8t22.getValue());
          current.setP7t22(p7t22.getValue());
          current.setP9t22(p9t22.getValue());
          current.setP10t22(p10t22.getValue());
          current.setP11t22(p11t22.getValue());
          current.setP100t22(p100t22.getValue());
        AccreditivtimechangeService.update(current);
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
            filter = new AccreditivtimechangeFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}


}
