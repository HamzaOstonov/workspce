package com.is.useractionlog;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.is.utils.CheckNull;

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

import com.is.useractionlog.PagingListModel;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;


public class CopyOfUserActionLogViewCtrl extends GenericForwardComposer{
        private Div frm;
        private Paging contactPaging;
        private Div grd;
        private Listbox dataGrid;
        private Grid addgrd,frmgrd,fgrd;
        private Toolbarbutton btn_last;
        private Toolbarbutton btn_next;
        private Toolbarbutton btn_prev;
        private Toolbarbutton btn_first;
        private Toolbarbutton btn_add;
        private Toolbarbutton btn_search;
        private Toolbarbutton btn_back;
        private Toolbar tb;
        private Textbox id,branch,user_id,user_name,ip_address,act_type,entity_type,entity_id;
        private Textbox aid,abranch,auser_id,auser_name,aip_address,aact_type,aentity_type,aentity_id ;
        private Textbox fid,fbranch,fuser_id,fuser_name,fip_address,fact_type,fentity_type,fentity_id ;
        private Paging useractionlogPaging;
        private Datebox action_date,aaction_date,faction_date;
        private  int _pageSize = 15;
        private int _startPageNumber = 0;
        private int _totalSize = 0;
        private boolean _needsTotalSizeUpdate = true;
        private String alias;

        public UserActionLogFilter filter;

        PagingListModel model = null;
        ListModelList lmodel =null;
        private AnnotateDataBinder binder;
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


    private UserActionLog current = new UserActionLog();

        public CopyOfUserActionLogViewCtrl() {
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
        public void render(Listitem row, Object data, int index) throws Exception {
                    UserActionLog pUserActionLog = (UserActionLog) data;

                    row.setValue(pUserActionLog);
                    
                    //row.appendChild(new Listcell(pUserActionLog.getId()+""));
                    row.appendChild(new Listcell(pUserActionLog.getBranch()));
                    row.appendChild(new Listcell(pUserActionLog.getUser_id()+""));
                    row.appendChild(new Listcell(pUserActionLog.getUser_name()));
                    row.appendChild(new Listcell(pUserActionLog.getIp_address()));
                    row.appendChild(new Listcell(pUserActionLog.getAction_date()+""));
                    //row.appendChild(new Listcell(pUserActionLog.getAct_type()+""));
                    //row.appendChild(new Listcell(pUserActionLog.getEntity_type()+""));
                    row.appendChild(new Listcell(pUserActionLog.getEntity_id().length()>80 ? pUserActionLog.getEntity_id().substring(0,80): pUserActionLog.getEntity_id()));


        }});

        refreshModel(_startPageNumber);

    }

public void onPaging$useractionlogPaging(ForwardEvent event){
    final PagingEvent pe = (PagingEvent) event.getOrigin();
    _startPageNumber = pe.getActivePage();
    refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
        useractionlogPaging.setPageSize(_pageSize);
    model = new PagingListModel(activePage, _pageSize, filter );
    _totalSize = model.getTotalSize(filter);

    useractionlogPaging.setTotalSize(_totalSize);

    dataGrid.setModel((ListModel) model);
    if (model.getSize()>0){
    	dataGrid.setSelectedIndex(0);
    sendSelEvt();
    }
}



// Omitted...
public UserActionLog getCurrent() {
    return current;
}

public void setCurrent(UserActionLog current) {
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
        
    	/*
    	if(addgrd.isVisible()){
                UserActionLogService.create(new UserActionLog(
                
                aid.getValue(),
                abranch.getValue(),
                auser_id.getValue(),
                auser_name.getValue(),
                aip_address.getValue(),
                aaction_date.getValue(),
                aact_type.getValue(),
                aentity_type.getValue(),
                aentity_id.getValue()
                ),alias);
            CheckNull.clearForm(addgrd);
            frmgrd.setVisible(true);
            addgrd.setVisible(false);
            fgrd.setVisible(false);
        }else */
        
        if(fgrd.isVisible()){
            filter = new UserActionLogFilter();
            
              filter.setId(!CheckNull.isEmpty(fid.getValue())? Integer.parseInt(fid.getValue()) : 0L);
              filter.setBranch(fbranch.getValue());
              filter.setUser_id(!CheckNull.isEmpty(fuser_id.getValue())? Integer.parseInt(fuser_id.getValue()) : 0);
              filter.setUser_name(fuser_name.getValue());
              filter.setIp_address(fip_address.getValue());
              filter.setAction_date_from(faction_date.getValue());
              filter.setAct_type(!CheckNull.isEmpty(fact_type.getValue())? Integer.parseInt(fact_type.getValue()) : 0);
              filter.setEntity_type(!CheckNull.isEmpty(fentity_type.getValue())? Integer.parseInt(fentity_type.getValue()) : 0);
              filter.setEntity_id(fentity_id.getValue());
/*
        }else{
            
              current.setId(id.getValue());
              current.setBranch(branch.getValue());
              current.setUser_id(user_id.getValue());
              current.setUser_name(user_name.getValue());
              current.setIp_address(ip_address.getValue());
              current.setAction_date(action_date.getValue());
              current.setAct_type(act_type.getValue());
              current.setEntity_type(entity_type.getValue());
              current.setEntity_id(entity_id.getValue());
            UserActionLogService.updateUserActionLog(current);
            */
        }
    onClick$btn_back();
    refreshModel(_startPageNumber);
    SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    Events.sendEvent(evt);
    } catch (Exception e) {
        com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
    }

}
public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
                filter = new UserActionLogFilter();
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
