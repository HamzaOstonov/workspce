package com.is.useractionlog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

@SuppressWarnings("serial")
public class UserActionLogViewCtrl extends GenericForwardComposer{
	private Div frm;
  	private Paging contactPaging;
   	private Div grd, frmgrddiv;
   	private Listbox dataGrid;
   	private Grid addgrd,frmgrd,fgrd, frmgrdaddinfo;
   	private Toolbarbutton btn_last;
   	private Toolbarbutton btn_next;
   	private Toolbarbutton btn_prev;
   	private Toolbarbutton btn_first;
   	private Toolbarbutton btn_add;
   	private Toolbarbutton btn_search;
   	private Toolbarbutton btn_back, btn_save;
   	private Toolbar tb;
	private Longbox id;
	private Textbox user_id, user_name, ip_address;
	private Datebox action_date;
	private RefCBox act_type, entity_type;
	private Longbox aid;
	private Textbox auser_id, auser_name, aip_address;
	private Datebox aaction_date;
	private Longbox aact_type, aentity_type ;
	private Longbox fid;
	private Textbox fuser_name, fip_address;
	private Datebox faction_date_from, faction_date_to;
	private RefCBox fact_type, fentity_type ;
	private RefCBox branch, fbranch;
	private Textbox entity_id, aentity_id, fentity_id;
	private Intbox fuser_id;
	private Paging userActionsLogPaging;
	private  int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private List<RefData> actions = new ArrayList<RefData>();
	private List<RefData> entities = new ArrayList<RefData>();
	
	public UserActionLog current = new UserActionLog();
	public UserActionLogFilter filter = new UserActionLogFilter();

	PagingListModel model = null;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat dft = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	
	public UserActionLogViewCtrl() {
		super('$', false, false);
	}
    
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
        
        actions = UserActionLogService.getActions();
        entities = UserActionLogService.getEntities();
        
        act_type.setModel(new ListModelList(actions));
        entity_type.setModel(new ListModelList(entities));
        fact_type.setModel(new ListModelList(actions));
        fentity_type.setModel(new ListModelList(entities));
        
        dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data, int index) throws Exception {
        	UserActionLog pUserActionsLog = (UserActionLog) data;
        	row.setValue(pUserActionsLog);
        	row.appendChild(new Listcell(pUserActionsLog.getId()+""));
        	row.appendChild(new Listcell(pUserActionsLog.getBranch()));
        	row.appendChild(new Listcell(pUserActionsLog.getUser_id()+""));
        	row.appendChild(new Listcell(pUserActionsLog.getUser_name()));
        	row.appendChild(new Listcell(pUserActionsLog.getIp_address()));
        	row.appendChild(new Listcell(dft.format(pUserActionsLog.getAction_date())));
        	row.appendChild(new Listcell(UserActionLogService.lvalue(pUserActionsLog.getAct_type()+"", actions)));
        	row.appendChild(new Listcell(UserActionLogService.lvalue(pUserActionsLog.getEntity_type()+"", entities)));
        	row.appendChild(new Listcell(pUserActionsLog.getEntity_id()+""));
        }});
        
        frmgrdaddinfo.setRowRenderer(new RowRenderer() {
			public void render(Row row, Object data, int index) throws Exception {
				if (data == null) {
					Label lbl = new Label("...");
					row.appendChild(lbl);
					Textbox t = new Textbox("...");
					t.setReadonly(true);
					t.setMold("rounded");
					t.setWidth("300px");
					row.appendChild(t);
				} else {
					final UserActionsAddinfo rowData = (UserActionsAddinfo) data;
					row.setValue(rowData);
					Label lbl = new Label(rowData.getA_key());
					row.appendChild(lbl);
					Textbox t = new Textbox(rowData.getA_value());
					t.setReadonly(true);
					t.setMold("rounded");
					t.setWidth("300px");
					row.appendChild(t);
				}
			}
		});
        
        refreshModel(_startPageNumber);
    }

	public void onPaging$userActionsLogPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage){
        userActionsLogPaging.setPageSize(_pageSize);
        model = new PagingListModel(activePage, _pageSize, filter);
	    if(_needsTotalSizeUpdate) {
	    	_totalSize = model.getTotalSize(filter);
	    	//_needsTotalSizeUpdate = false;
	    }
	    userActionsLogPaging.setTotalSize(_totalSize);
	    dataGrid.setModel((ListModel) model);
	    sort();
	    if (model.getSize()>0){
	    	dataGrid.setSelectedIndex(0);
	    	sendSelEvt(true);
	    	this.current =(UserActionLog) model.getElementAt(0);
		    sendSelEvt(true);
	    }
	}
	
	public void onSelect$dataGrid$grd() {
		sendSelEvt(false);
	}
	
	public void sort() {
		for (int i = 0; i < dataGrid.getListhead().getChildren().size(); i++) {
			Listheader listheader = (Listheader)dataGrid.getListhead().getChildren().get(i);
			if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
				listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
				return;
			}
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
        frmgrddiv.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
        btn_back.setImage("/images/folder.png");
        btn_back.setLabel(Labels.getLabel("grid"));
        btn_save.setVisible(false);
        setCurrent();
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
        sendSelEvt(true);
	}
	
	public void onClick$btn_last() {
        dataGrid.setSelectedIndex(model.getSize()-1);
        sendSelEvt(true);
	}
	
	public void onClick$btn_prev() {
        if (dataGrid.getSelectedIndex()!=0){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()-1);
	        sendSelEvt(true);
        }
	}
	
	public void onClick$btn_next() {
        if (dataGrid.getSelectedIndex()!=(model.getSize()-1)){
	        dataGrid.setSelectedIndex(dataGrid.getSelectedIndex()+1);
	        sendSelEvt(true);
        }
	}

	private void sendSelEvt(Boolean sendEvt){
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
        if (sendEvt) {
	        SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
	        Events.sendEvent(evt);
        }
        setCurrent();
	}

	public void onClick$btn_add() {
        onDoubleClick$dataGrid$grd();
        frmgrddiv.setVisible(false);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
        onDoubleClick$dataGrid$grd();
        frmgrddiv.setVisible(false);
        addgrd.setVisible(false);
        fgrd.setVisible(true);
        btn_save.setVisible(true);
	}


	public void onClick$btn_save() {
	    try{
	        if(addgrd.isVisible()){
	        	/*
	        	UserActionsLogService.create(new UserActionsLog(
		                aid.getValue(),
		                auser_id.getValue(),
		                auser_name.getValue(),
		                aip_address.getValue(),
		                aaction_date.getValue(),
		                aact_type.getValue(),
		                aentity_type.getValue(),
		                aentity_id.getValue(),
		                0L,
		                ""
	        	));
	            CheckNull.clearForm(addgrd);
	            frmgrddiv.setVisible(true);
	            addgrd.setVisible(false);
	            fgrd.setVisible(false);
	            */
	        }else if(fgrd.isVisible()){
	            filter = new UserActionLogFilter();
				if (!CheckNull.isEmpty(fid.getValue())) {
					filter.setId(fid.getValue());
				}
				if (!CheckNull.isEmpty(fbranch.getValue())) {
					filter.setBranch(fbranch.getValue());
				}
				if (!CheckNull.isEmpty(fuser_id.getValue())) {
					filter.setUser_id(fuser_id.getValue());
				}
				if (!CheckNull.isEmpty(fuser_name.getValue())) {
					filter.setUser_name(fuser_name.getValue());
				}
				if (!CheckNull.isEmpty(fip_address.getValue())) {
					filter.setIp_address(fip_address.getValue());
				}
				if (!CheckNull.isEmpty(faction_date_from.getValue())) {
					filter.setAction_date_from(faction_date_from.getValue());
				}
				if (!CheckNull.isEmpty(faction_date_to.getValue())) {
					filter.setAction_date_to(faction_date_to.getValue());
				}
				if (!CheckNull.isEmpty(fact_type.getValue())) {
					filter.setAct_type(Integer.parseInt(fact_type.getValue()));
				}
				if (!CheckNull.isEmpty(fentity_type.getValue())) {
					filter.setEntity_type(Integer.parseInt(fentity_type.getValue()));
				}
				if (!CheckNull.isEmpty(fentity_id.getValue())) {
					filter.setEntity_id(fentity_id.getValue());
				}

	        }else{
		        /*
	        	current.setId(id.getValue());
		        current.setUser_id(user_id.getValue());
		        current.setUser_name(user_name.getValue());
		        current.setIp_address(ip_address.getValue());
		        current.setAction_date(action_date.getValue());
		        current.setAction_type(act_type.getValue());
		        current.setEntity_type(entity_type.getValue());
		        current.setEntity_id(entity_id.getValue());
				
	            UserActionsLogService.update(current);
	            */
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
        	filter = new UserActionLogFilter();
        }
	    onClick$btn_back();
	    frmgrddiv.setVisible(true);
	    addgrd.setVisible(false);
	    fgrd.setVisible(false);
	    CheckNull.clearForm(addgrd);
	    CheckNull.clearForm(fgrd);
	    refreshModel(_startPageNumber);
	}
	
	private void setCurrent() {
		if (current != null) {
			while (current.getAddinfo().size() < 9) {
				current.getAddinfo().add(null);
			}
			frmgrdaddinfo.setModel(new ListModelList(current.getAddinfo()));
		}
	}
}