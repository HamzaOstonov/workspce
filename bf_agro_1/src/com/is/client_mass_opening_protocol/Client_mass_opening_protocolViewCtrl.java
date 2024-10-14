package com.is.client_mass_opening_protocol;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
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
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;
//import com.is.Rates.RatesService;
import com.is.utils.CheckNull;
import com.is.utils.Res;
public class Client_mass_opening_protocolViewCtrl extends GenericForwardComposer{
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
    private Textbox id,client_id,score_res,opening_res,opening_uzcard,opening_humo,status_score,status_opening,status_uzcard,status_humo;
    private Textbox aid,aclient_id,ascore_res,aopening_res,aopening_uzcard,aopening_humo,astatus_score,astatus_opening,astatus_uzcard,astatus_humo ;
    private Textbox fid,fclient_id,fscore_res,fopening_res,fopening_uzcard,fopening_humo,fstatus_score,fstatus_opening,fstatus_uzcard,fstatus_humo ;
    private Paging client_mass_opening_protocolPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;

    
    public Client_mass_opening_protocolFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Client_mass_opening_protocol current = new Client_mass_opening_protocol();

    public Client_mass_opening_protocolViewCtrl() {
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
                Client_mass_opening_protocol pClient_mass_opening_protocol = (Client_mass_opening_protocol) data;

                row.setValue(pClient_mass_opening_protocol);
                
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getId()+""));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getClient_id()));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getScore_res()));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getOpening_res()));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getOpening_uzcard()));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getOpening_humo()));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getStatus_score()+""));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getStatus_opening()+""));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getStatus_uzcard()+""));
                row.appendChild(new Listcell(pClient_mass_opening_protocol.getStatus_humo()+""));


    }});

    refreshModel(_startPageNumber);

}

public void onPaging$client_mass_opening_protocolPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    client_mass_opening_protocolPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
}

client_mass_opening_protocolPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Client_mass_opening_protocol) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Client_mass_opening_protocol getCurrent() {
return current;
}

public void setCurrent(Client_mass_opening_protocol current) {
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

/*
public void onClick$btn_save() {
try{
    if(addgrd.isVisible()){
            Client_mass_opening_protocolService.create(new Client_mass_opening_protocol(
            
            aid.getValue(),
            aclient_id.getValue(),
            ascore_res.getValue(),
            aopening_res.getValue(),
            aopening_uzcard.getValue(),
            aopening_humo.getValue(),
            astatus_score.getValue(),
            astatus_opening.getValue(),
            astatus_uzcard.getValue(),
            astatus_humo.getValue(),
            ));
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new Client_mass_opening_protocolFilter();
        
          filter.setId(fid.getValue());
          filter.setClient_id(fclient_id.getValue());
          filter.setScore_res(fscore_res.getValue());
          filter.setOpening_res(fopening_res.getValue());
          filter.setOpening_uzcard(fopening_uzcard.getValue());
          filter.setOpening_humo(fopening_humo.getValue());
          filter.setStatus_score(fstatus_score.getValue());
          filter.setStatus_opening(fstatus_opening.getValue());
          filter.setStatus_uzcard(fstatus_uzcard.getValue());
          filter.setStatus_humo(fstatus_humo.getValue());

    }else{
        
          current.setId(id.getValue());
          current.setClient_id(client_id.getValue());
          current.setScore_res(score_res.getValue());
          current.setOpening_res(opening_res.getValue());
          current.setOpening_uzcard(opening_uzcard.getValue());
          current.setOpening_humo(opening_humo.getValue());
          current.setStatus_score(status_score.getValue());
          current.setStatus_opening(status_opening.getValue());
          current.setStatus_uzcard(status_uzcard.getValue());
          current.setStatus_humo(status_humo.getValue());
        Client_mass_opening_protocolService.update(current);
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}*/
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new Client_mass_opening_protocolFilter();
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

