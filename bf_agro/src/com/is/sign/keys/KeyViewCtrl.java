package com.is.sign.keys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


//import Signlog.key.PagingListModel;
import com.is.ConnectionPool;
import com.is.sign.utils.DateUtil;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.RefRenderer;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class KeyViewCtrl extends GenericForwardComposer{
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
	private Textbox key_code, key_sn, key_certn, version, signature_algoritm;
	private Longbox key_size;
	private Textbox issuer, pkey;
	private Datebox key_expired, activate_date, deactivate_date;
	private Textbox akey_code, akey_sn, akey_certn, aversion, asignature_algoritm;
	private Longbox akey_size;
	private Textbox aissuer, apkey;
	private Datebox akey_expired, aactivate_date, adeactivate_date;
	private Textbox fkey_code, fkey_sn, fkey_certn, fversion, fsignature_algoritm;
	private Longbox fkey_size;
	private Textbox fissuer, fpkey;
	private Datebox fkey_expired, factivate_date, fdeactivate_date;
	private RefCBox sign_failure, asign_failure, fsign_failure;
	private RefCBox key_type, akey_type, fkey_type;
	private RefCBox branch, abranch, fbranch;
	private Textbox emp_id, aemp_id, femp_id;
	private RefCBox state, astate, fstate;
	private Paging keyPaging;
	private  int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String ubranch, un, alias;
	private int uid;
	private int currentkeytype = Integer.parseInt(ConnectionPool.getValue("SIGN_TYPE"));

	public Key current = new Key();
	public Key currentadd = new Key();
	public KeyFilter filter = new KeyFilter();
	
	private List<RefData> states = new ArrayList<RefData>();
	private List<RefData> branches = new ArrayList<RefData>();
	private List<RefData> keytypes = new ArrayList<RefData>();
	private List<RefData> keysignstates = new ArrayList<RefData>();

	PagingListModel model = null;
	ListModelList lmodel =null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	public KeyViewCtrl() {
		super('$', false, false);
	}
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	// TODO Auto-generated method stub
    	binder = new AnnotateDataBinder(comp);
    	binder.bindBean("current", this.current);
    	binder.bindBean("currentadd", this.currentadd);
    	binder.bindBean("filter", this.filter);
    	binder.loadAll();
        String[] parameter = (String[]) param.get("ht");
        if (parameter!=null){
        	_pageSize = Integer.parseInt( parameter[0])/36;
        	dataGrid.setRows(Integer.parseInt( parameter[0])/36);
        }
        
        ubranch = (String)session.getAttribute("branch"); 
        un = (String)session.getAttribute("un");
        uid = (Integer)session.getAttribute("uid"); 
        alias = (String)session.getAttribute("alias");

        states = KeyService.getKeyStates();
        branches = KeyService.getCurMfo(alias);
        keytypes = KeyService.getKeyTypes(alias);
        keysignstates = KeyService.getKeySignStates();
        
        state.setModel(new ListModelList(states));
        astate.setModel(new ListModelList(states));
        fstate.setModel(new ListModelList(states));
        
        branch.setModel(new ListModelList(branches));
        abranch.setModel(new ListModelList(branches));
        fbranch.setModel(new ListModelList(branches));
        
        key_type.setModel(new ListModelList(keytypes));
        akey_type.setModel(new ListModelList(keytypes));
        fkey_type.setModel(new ListModelList(keytypes));
        
        sign_failure.setModel(new ListModelList(keysignstates));
        asign_failure.setModel(new ListModelList(keysignstates));
        fsign_failure.setModel(new ListModelList(keysignstates));
        
        dataGrid.setItemRenderer(new ListitemRenderer(){
        @SuppressWarnings("unchecked")
        public void render(Listitem row, Object data) throws Exception {
        	Key pKey = (Key) data;
        	row.setValue(pKey);
            row.appendChild(new Listcell(KeyService.lvalue(pKey.getKey_type()+"", keytypes)));
            row.appendChild(new Listcell(pKey.getKey_code()));
            row.appendChild(new Listcell(pKey.getKey_sn()));
            row.appendChild(new Listcell(pKey.getKey_certn()));
            row.appendChild(new Listcell(pKey.getVersion()));
            //row.appendChild(new Listcell(pKey.getSignature_algoritm()));
            //row.appendChild(new Listcell(pKey.getKey_size()+""));
            //row.appendChild(new Listcell(pKey.getIssuer()));
            //row.appendChild(new Listcell(pKey.getPkey()));
            row.appendChild(new Listcell(KeyService.lvalue(pKey.getSign_failure()+"", keysignstates)));
            row.appendChild(new Listcell(CheckNull.isEmpty(pKey.getKey_expired())?"":df.format(pKey.getKey_expired())));
            row.appendChild(new Listcell(CheckNull.isEmpty(pKey.getActivate_date())?"":df.format(pKey.getActivate_date())));
            row.appendChild(new Listcell(CheckNull.isEmpty(pKey.getDeactivate_date())?"":df.format(pKey.getDeactivate_date())));
            //row.appendChild(new Listcell(pKey.getBranch()));
            //row.appendChild(new Listcell(pKey.getEmp_id()+""));
            row.appendChild(new Listcell(KeyService.lvalue(pKey.getState()+"", states)));
        }});

        refreshModel(_startPageNumber);
    }

	public void onPaging$keyPaging(ForwardEvent event){
	    final PagingEvent pe = (PagingEvent) event.getOrigin();
	    _startPageNumber = pe.getActivePage();
	    refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage){
        keyPaging.setPageSize(_pageSize);
        model = new PagingListModel(activePage, _pageSize, filter);
	    if(_needsTotalSizeUpdate) {
	    	_totalSize = model.getTotalSize(filter);
	    	//_needsTotalSizeUpdate = false;
	    }
	    keyPaging.setTotalSize(_totalSize);
	    dataGrid.setModel((ListModel) model);
	    sort();
	    if (model.getSize()>0){
	    	dataGrid.setSelectedIndex(0);
	    	sendSelEvt();
	    	this.current =(Key) model.getElementAt(0);
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
	
	public void onClick$btn_refresh() {
		refreshModel(_startPageNumber);
	}

	// Omitted...
	public Key getCurrent() {
	    return current;
	}
	
	public void setCurrent(Key current) {
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
		currentadd = new Key();
		KeyService.clearForm(addgrd);
		currentadd.setKey_type(currentkeytype);
		currentadd.setActivate_date(new Date());
		currentadd.setKey_expired(DateUtil.addYears(new Date(), 2));
		currentadd.setSign_failure(1L);
		currentadd.setState(1);
		
		akey_type.setSelecteditem(currentkeytype+"");
		aactivate_date.setValue(new Date());
		adeactivate_date.setValue(null);
		akey_expired.setValue(DateUtil.addYears(new Date(), 2));
		asign_failure.setSelecteditem("1");
		astate.setSelecteditem("1");
		
		onDoubleClick$dataGrid$grd();
        frmgrd.setVisible(false);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
        
        if (currentkeytype == 2) {
        	akey_code.setDisabled(true);
        } else {
        	akey_code.setDisabled(false);
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
	        if(addgrd.isVisible()){
	        	Res res = KeyService.create(new Key(
		                Integer.parseInt(akey_type.getValue()),
		                akey_code.getValue(),
		                akey_sn.getValue(),
		                akey_certn.getValue(),
		                aversion.getValue(),
		                asignature_algoritm.getValue(),
		                akey_size.getValue(),
		                aissuer.getValue(),
		                apkey.getValue(),
		                1L,
		                akey_expired.getValue(),
		                new Date(),
		                adeactivate_date.getValue(),
		                ubranch,
		                uid,
		                1
	        	));
	        	alert(res.getName());
	        	if (res.getCode() != 0) {
	        		return;
	        	}
	            CheckNull.clearForm(addgrd);
	            frmgrd.setVisible(true);
	            addgrd.setVisible(false);
	            fgrd.setVisible(false);
	        }else if(fgrd.isVisible()){
	            filter = new KeyFilter();
				if (!CheckNull.isEmpty(fkey_type.getValue())) {
					filter.setKey_type(Integer.parseInt(fkey_type.getValue()));
				}
				if (!CheckNull.isEmpty(fkey_code.getValue())) {
					filter.setKey_code(fkey_code.getValue());
				}
				if (!CheckNull.isEmpty(fkey_sn.getValue())) {
					filter.setKey_sn(fkey_sn.getValue());
				}
				if (!CheckNull.isEmpty(fkey_certn.getValue())) {
					filter.setKey_certn(fkey_certn.getValue());
				}
				if (!CheckNull.isEmpty(fversion.getValue())) {
					filter.setVersion(fversion.getValue());
				}
				if (!CheckNull.isEmpty(fsignature_algoritm.getValue())) {
					filter.setSignature_algoritm(fsignature_algoritm.getValue());
				}
				if (!CheckNull.isEmpty(fkey_size.getValue())) {
					filter.setKey_size(fkey_size.getValue());
				}
				if (!CheckNull.isEmpty(fissuer.getValue())) {
					filter.setIssuer(fissuer.getValue());
				}
				if (!CheckNull.isEmpty(fpkey.getValue())) {
					filter.setPkey(fpkey.getValue());
				}
				if (!CheckNull.isEmpty(fsign_failure.getValue())) {
					filter.setSign_failure(Long.parseLong(fsign_failure.getValue()));
				}
				if (!CheckNull.isEmpty(fkey_expired.getValue())) {
					filter.setKey_expired(fkey_expired.getValue());
				}
				if (!CheckNull.isEmpty(factivate_date.getValue())) {
					filter.setActivate_date(factivate_date.getValue());
				}
				if (!CheckNull.isEmpty(fdeactivate_date.getValue())) {
					filter.setDeactivate_date(fdeactivate_date.getValue());
				}
				if (!CheckNull.isEmpty(fbranch.getValue())) {
					filter.setBranch(fbranch.getValue());
				}
				if (!CheckNull.isEmpty(femp_id.getValue())) {
					filter.setEmp_id(Integer.parseInt(femp_id.getValue()));
				}
				if (!CheckNull.isEmpty(fstate.getValue())) {
					filter.setState(Integer.parseInt(fstate.getValue()));
				}

	        }else{
        	    //current.setKey_type(Integer.parseInt(key_type.getValue()));
        	    //current.setKey_code(key_code.getValue());
        	    //current.setKey_sn(key_sn.getValue());
	        	current.setKey_certn(key_certn.getValue());
        	    current.setVersion(version.getValue());
        	    current.setSignature_algoritm(signature_algoritm.getValue());
        	    current.setKey_size(key_size.getValue());
        	    current.setIssuer(issuer.getValue());
        	    current.setPkey(pkey.getValue());
        	    current.setSign_failure(Long.parseLong(sign_failure.getValue()));
        	    current.setKey_expired(key_expired.getValue());
        	    current.setActivate_date(activate_date.getValue());
        	    current.setDeactivate_date(deactivate_date.getValue());
        	    current.setBranch(ubranch);
        	    current.setEmp_id(uid);
        	    current.setState(Integer.parseInt(state.getValue()));
        	    Res res = KeyService.update(current);
        	    alert(res.getName());
	        	if (res.getCode() != 0) {
	        		return;
	        	}
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
        	filter = new KeyFilter();
        }
	    onClick$btn_back();
	    frmgrd.setVisible(true);
	    addgrd.setVisible(false);
	    fgrd.setVisible(false);
	    CheckNull.clearForm(addgrd);
	    CheckNull.clearForm(fgrd);
	    refreshModel(_startPageNumber);
	}
	
	public void onChange$akey_sn() {
		if (currentkeytype == 2) {
			currentadd.setKey_code(akey_sn.getValue());
			currentadd.setKey_sn(akey_sn.getValue());
			akey_code.setValue(akey_sn.getValue());
		}
	}
}