package com.is.sign.log;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Intbox;
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


//import Signlog.signlog.PagingListModel;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;

@SuppressWarnings("serial")
public class SignlogViewCtrl extends GenericForwardComposer{
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
	private Longbox id;
	private Datebox dttime;
	private Intbox group_id, deal_id, action_id; 
	private Longbox object_id;
	private Textbox sign_text, sign_data;
	private Intbox key_type;
	private Textbox key_code, key_sn, branch;
	private Intbox user_id;
	private Textbox username, scheme;
	private Intbox state;
	private Textbox err_msg;;
	private Longbox aid;
	private Datebox adttime;
	private Intbox agroup_id, adeal_id, aaction_id;
	private Longbox aobject_id;
	private Textbox asign_text, asign_data;
	private Intbox akey_type;
	private Textbox akey_code, akey_sn, abranch;
	private Intbox auser_id;
	private Textbox ausername, ascheme;
	private Intbox astate;
	private Textbox aerr_msg; ;
	private Longbox fid;
	private Datebox fdttime;
	private Intbox fgroup_id, fdeal_id, faction_id; 
	private Longbox fobject_id;
	private Textbox fsign_text, fsign_data;
	private Intbox fkey_type;
	private Textbox fkey_code, fkey_sn, fbranch;
	private Intbox fuser_id;
	private Textbox fusername, fscheme;
	private Intbox fstate;
	private Textbox ferr_msg; ;
	private Paging signlogPaging;
	private  int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;

	public Signlog current = new Signlog();
	public SignlogFilter filter = new SignlogFilter();

	PagingListModel model = null;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public SignlogViewCtrl() {
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

        dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data, int rowid) throws Exception {
        	Signlog pSignlog = (Signlog) data;
        	row.setValue(pSignlog);
        	
			                    row.appendChild(new Listcell(pSignlog.getId()+""));
			                    row.appendChild(new Listcell(df.format(pSignlog.getDttime())));
			                    row.appendChild(new Listcell(pSignlog.getGroup_id()+""));
			                    row.appendChild(new Listcell(pSignlog.getDeal_id()+""));
			                    row.appendChild(new Listcell(pSignlog.getAction_id()+""));
			                    row.appendChild(new Listcell(pSignlog.getObject_id()+""));
			                    row.appendChild(new Listcell(pSignlog.getSign_text()));
			                    row.appendChild(new Listcell(pSignlog.getSign_data()));
			                    row.appendChild(new Listcell(pSignlog.getKey_type()+""));
			                    row.appendChild(new Listcell(pSignlog.getKey_code()));
			                    row.appendChild(new Listcell(pSignlog.getKey_sn()));
			                    row.appendChild(new Listcell(pSignlog.getBranch()));
			                    row.appendChild(new Listcell(pSignlog.getUser_id()+""));
			                    row.appendChild(new Listcell(pSignlog.getUsername()));
			                    row.appendChild(new Listcell(pSignlog.getScheme()));
			                    row.appendChild(new Listcell(pSignlog.getState()+""));
			                    row.appendChild(new Listcell(pSignlog.getErr_msg()));
        }});

        refreshModel(_startPageNumber);
    }

	public void onPaging$signlogPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage){
        signlogPaging.setPageSize(_pageSize);
        model = new PagingListModel(activePage, _pageSize, filter);
	    if(_needsTotalSizeUpdate) {
	    	_totalSize = model.getTotalSize(filter);
	    	//_needsTotalSizeUpdate = false;
	    }
	    signlogPaging.setTotalSize(_totalSize);
	    dataGrid.setModel((ListModel) model);
	    sort();
	    if (model.getSize()>0){
	    	dataGrid.setSelectedIndex(0);
	    	this.current =(Signlog) model.getElementAt(0);
		    sendSelEvt();
	    }
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
	public Signlog getCurrent() {
	    return current;
	}
	
	public void setCurrent(Signlog current) {
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
		try {
			if (addgrd.isVisible()) {
				SignlogService.create(new Signlog(	
						aid.getValue(),
						adttime.getValue(),
						agroup_id.getValue(),
						adeal_id.getValue(),
						aaction_id.getValue(),
						aobject_id.getValue(),
						asign_text.getValue(),
						asign_data.getValue(),
						akey_type.getValue(),
						akey_code.getValue(),
						akey_sn.getValue(),
						abranch.getValue(),
						auser_id.getValue(),
						ausername.getValue(),
						ascheme.getValue(),
						astate.getValue(),
						aerr_msg.getValue()));
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new SignlogFilter();
				if (!CheckNull.isEmpty(fid.getValue())) {
					filter.setId(fid.getValue());
				}
				if (!CheckNull.isEmpty(fdttime.getValue())) {
					filter.setDttime(fdttime.getValue());
				}
				if (!CheckNull.isEmpty(fgroup_id.getValue())) {
					filter.setGroup_id(fgroup_id.getValue());
				}
				if (!CheckNull.isEmpty(fdeal_id.getValue())) {
					filter.setDeal_id(fdeal_id.getValue());
				}
				if (!CheckNull.isEmpty(faction_id.getValue())) {
					filter.setAction_id(faction_id.getValue());
				}
				if (!CheckNull.isEmpty(fobject_id.getValue())) {
					filter.setObject_id(fobject_id.getValue());
				}
				if (!CheckNull.isEmpty(fsign_text.getValue())) {
					filter.setSign_text(fsign_text.getValue());
				}
				if (!CheckNull.isEmpty(fsign_data.getValue())) {
					filter.setSign_data(fsign_data.getValue());
				}
				if (!CheckNull.isEmpty(fkey_type.getValue())) {
					filter.setKey_type(fkey_type.getValue());
				}
				if (!CheckNull.isEmpty(fkey_code.getValue())) {
					filter.setKey_code(fkey_code.getValue());
				}
				if (!CheckNull.isEmpty(fkey_sn.getValue())) {
					filter.setKey_sn(fkey_sn.getValue());
				}
				if (!CheckNull.isEmpty(fbranch.getValue())) {
					filter.setBranch(fbranch.getValue());
				}
				if (!CheckNull.isEmpty(fuser_id.getValue())) {
					filter.setUser_id(fuser_id.getValue());
				}
				if (!CheckNull.isEmpty(fusername.getValue())) {
					filter.setUsername(fusername.getValue());
				}
				if (!CheckNull.isEmpty(fscheme.getValue())) {
					filter.setScheme(fscheme.getValue());
				}
				if (!CheckNull.isEmpty(fstate.getValue())) {
					filter.setState(fstate.getValue());
				}
				if (!CheckNull.isEmpty(ferr_msg.getValue())) {
					filter.setErr_msg(ferr_msg.getValue());
				}
			} else {
				current.setId(id.getValue());
				current.setDttime(dttime.getValue());
				current.setGroup_id(group_id.getValue());
				current.setDeal_id(deal_id.getValue());
				current.setAction_id(action_id.getValue());
				current.setObject_id(object_id.getValue());
				current.setSign_text(sign_text.getValue());
				current.setSign_data(sign_data.getValue());
				current.setKey_type(key_type.getValue());
				current.setKey_code(key_code.getValue());
				current.setKey_sn(key_sn.getValue());
				current.setBranch(branch.getValue());
				current.setUser_id(user_id.getValue());
				current.setUsername(username.getValue());
				current.setScheme(scheme.getValue());
				current.setState(state.getValue());
				current.setErr_msg(err_msg.getValue());
				SignlogService.update(current);
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_cancel() {
        if(fgrd.isVisible()){
        	filter = new SignlogFilter();
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