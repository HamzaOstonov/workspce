package com.is.compaymentscheck;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.is.utils.CheckNull;

import oracle.sql.TIMESTAMP;

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


public class ComPaymentsCheckViewCtrl extends GenericForwardComposer{
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
    private Textbox id,provider_id,trans_count,amount,state;
    private Textbox aid,aprovider_id,atrans_count,aamount,astate;
    private Textbox fid,fprovider_id,ftrans_count,famount,fstate;
    private Datebox from_time,to_time,exec_time,ffrom_time,fto_time,fexec_time,afrom_time,ato_time,aexec_time;
    private Paging compaymentscheckPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private String alias;

    
    public ComPaymentsCheckFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat tdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");


private ComPaymentsCheck current = new ComPaymentsCheck();

    public ComPaymentsCheckViewCtrl() {
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
                ComPaymentsCheck pComPaymentsCheck = (ComPaymentsCheck) data;

                row.setValue(pComPaymentsCheck);
                
                row.appendChild(new Listcell(Long.toString(pComPaymentsCheck.getId())));
                row.appendChild(new Listcell(Integer.toString(pComPaymentsCheck.getProvider_id())));
                row.appendChild(new Listcell(tdf.format(pComPaymentsCheck.getFrom_time())));
                row.appendChild(new Listcell(tdf.format(pComPaymentsCheck.getTo_time())));
                row.appendChild(new Listcell(tdf.format(pComPaymentsCheck.getExec_time())));
                row.appendChild(new Listcell(Integer.toString(pComPaymentsCheck.getTrans_count())));
                row.appendChild(new Listcell(Long.toString(pComPaymentsCheck.getAmount())));
                row.appendChild(new Listcell(Integer.toString(pComPaymentsCheck.getState())));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$userPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    compaymentscheckPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize,filter, alias);

//if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
       // _needsTotalSizeUpdate = false;
//}

compaymentscheckPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
	dataGrid.setSelectedIndex(0);
sendSelEvt();
}
}



//Omitted...
public ComPaymentsCheck getCurrent() {
return current;
}

public void setCurrent(ComPaymentsCheck current) {
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
            ComPaymentsCheckService.create(new ComPaymentsCheck(
            Long.valueOf("0"),//,aid.getValue(),
            Integer.parseInt( aprovider_id.getValue()),
            afrom_time.getValue(),
            ato_time.getValue(),
            aexec_time.getValue(),
            Integer.parseInt(atrans_count.getValue()),
            Long.parseLong(aamount.getValue()),
            Integer.parseInt(astate.getValue())
            ),alias);
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new ComPaymentsCheckFilter();
        
          filter.setId(Long.parseLong(fid.getValue()));
          filter.setProvider_id(Integer.parseInt(fprovider_id.getValue()));
          filter.setFrom_time(ffrom_time.getValue());
          filter.setTo_time(fto_time.getValue());
          filter.setExec_time(fexec_time.getValue());
          filter.setTrans_count(Integer.parseInt(ftrans_count.getValue()));
          filter.setAmount(Long.parseLong(famount.getValue()));
          filter.setState(Integer.parseInt(fstate.getValue()));

    }else{
        
          current.setId(Long.parseLong(id.getValue()));
          current.setProvider_id(Integer.parseInt(provider_id.getValue()));
          current.setFrom_time(from_time.getValue());
          current.setTo_time(to_time.getValue());
          current.setExec_time(exec_time.getValue());
          current.setTrans_count(Integer.parseInt(trans_count.getValue()));
          current.setAmount(Long.parseLong(amount.getValue()));
          current.setState(Integer.parseInt(state.getValue()));
        ComPaymentsCheckService.update(current,alias);
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
            filter = new ComPaymentsCheckFilter();
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
