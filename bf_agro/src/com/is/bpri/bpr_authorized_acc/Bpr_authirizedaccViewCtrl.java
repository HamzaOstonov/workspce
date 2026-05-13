package com.is.bpri.bpr_authorized_acc;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;
import com.is.ISLogger;
import com.is.bpri.BproductService;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class Bpr_authirizedaccViewCtrl extends GenericForwardComposer{
    
	private Div frm;
    private Listbox dataGrid;
    private Div grd;
    private Grid addgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
    private RefCBox abranch;
    private RefCBox fbranch;
    private Textbox faccount,aaccount;
    private Paging bpr_authorizedPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private int bpr_id1;
    private String alias;
    private int state = -1;
//    private int bpr_type;
    private String action = "";
    
    public Bpr_authirizedaccFilter filter;

    private com.is.bpri.bpr_authorized_acc.PagingListModel model = null;
    private AnnotateDataBinder binder;
    private Bpr_authirizedacc current = new Bpr_authirizedacc();

    public Bpr_authirizedaccViewCtrl() {
            super('$', false, false);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        binder = new AnnotateDataBinder(comp);
        binder.bindBean("current", this.current);
        binder.loadAll();
        alias = (String) session.getAttribute("alias");
        dataGrid.setItemRenderer(new ListitemRenderer(){
        	@Override
        	public void render(Listitem row, Object data) throws Exception {
        		Bpr_authirizedacc pbpr_specialfrm = (Bpr_authirizedacc) data;

        		row.setValue(pbpr_specialfrm);
    			row.appendChild(new Listcell(pbpr_specialfrm.getId()+""));
    			row.appendChild(new Listcell(pbpr_specialfrm.getBranch()));
    			row.appendChild(new Listcell(pbpr_specialfrm.getAcc()));
    	}});
        if(this.param.get("bpr_id")!=null){
        	bpr_id1 = Integer.parseInt(((String[]) this.param.get("bpr_id"))[0]);
        }
        if(this.param.get("state")!=null){
        	state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
        }
        filter = new Bpr_authirizedaccFilter();
        filter.setBpr_id(bpr_id1);
        RefCBoxModels();
    	refreshModel(_startPageNumber);
    	if(state==1||state==2){
    		btn_save.setVisible(false);
    		btn_del.setVisible(false);
    		btn_add.setVisible(false);
    	} else if (state==0){
    		btn_save.setVisible(true);
    		btn_del.setVisible(true);
    		btn_add.setVisible(true);
    	}
    }

    public void onPaging$bpr_specialfrmPaging(ForwardEvent event){
    	final PagingEvent pe = (PagingEvent) event.getOrigin();
    	_startPageNumber = pe.getActivePage();
    	refreshModel(_startPageNumber);
    }
    
    private void RefCBoxModels(){
    	abranch.setModel(new ListModelList(BproductService.getSMFO(alias)));
    	fbranch.setModel(new ListModelList(BproductService.getSMFO(alias)));
    }
    
    private void refreshModel(int activePage){
    	bpr_authorizedPaging.setPageSize(_pageSize);
    	model = new com.is.bpri.bpr_authorized_acc.PagingListModel(activePage, _pageSize, filter, "");
    	if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
    	}
    	filter.setBpr_id(bpr_id1);
    	bpr_authorizedPaging.setTotalSize(_totalSize);
    	dataGrid.setModel((ListModel) model);
    	if (model.getSize()>0){
    		this.current =(Bpr_authirizedacc) model.getElementAt(0);
    		sendSelEvt();
    	}
    }

    //Omitted...
    public Bpr_authirizedacc getCurrent() {
    	return current;
    }

    public void setCurrent(Bpr_authirizedacc current) {
    	this.current = current;
    }

    public void onDoubleClick$dataGrid$grd() {
    	grd.setVisible(false);
        frm.setVisible(true);
        addgrd.setVisible(true);
        fgrd.setVisible(false);
        btn_back.setImage("/images/folder.png");
        btn_back.setLabel(Labels.getLabel("grid"));
        btn_save.setDisabled(false);
        action = "double";
        if(current!=null){
        	abranch.setSelecteditem(current.getBranch());
        	aaccount.setValue(current.getAcc());
        }
    }
    
    public void onClick$btn_back(){
    	if (frm.isVisible()){
    		filter.setBpr_id(bpr_id1);
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
    	addgrd.setVisible(true);
    	fgrd.setVisible(false);
    	Utils.clearForm(addgrd);
    	action = "add";
    }

    public void onClick$btn_search() {
    	onDoubleClick$dataGrid$grd();
    	addgrd.setVisible(false);
    	fgrd.setVisible(true);
    	btn_save.setDisabled(false);
    	action = "search";
    }
    
    private void addGrdVisible(String acc,Res res){
    	current.setBpr_id(bpr_id1);
		current.setBranch(abranch.getValue());
		current.setAcc(acc);
		Bpr_authirizedaccService.create(current,res);
		Utils.clearForm(addgrd);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
    }

    public void onClick$btn_save() {
    	try{
    		Res res = new Res();
    		String realAcc = "";
    		if(action.equals("add")){
    			realAcc = Bpr_authirizedaccService.getAccounts(aaccount.getValue(), abranch.getValue(), alias);
    			if(!realAcc.equals(aaccount.getValue())){
    				aaccount.setValue(realAcc);
    				Messagebox.show("Неверный счет!\nВозможно должен быть этот? = "+realAcc, "Предупреждение", Messagebox.YES|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
    					
    					@Override
    					public void onEvent(Event evt) throws Exception {
    						Res res = new Res();
    						if(evt.getName().equals("onYes")){
    							addGrdVisible(aaccount.getValue(),res);
    							onClick$btn_back();
    			        		refreshModel(_startPageNumber);
    			        		SelectEvent evtv = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
    			        		Events.sendEvent(evtv);
    			        		btn_save.setDisabled(true);
    						}
    					}
    				});
    				return;
    			} else {
        			addGrdVisible(aaccount.getValue(),res);
    			}
    		}else if(action.equals("search")){
    			filter = new Bpr_authirizedaccFilter();
    			filter.setBranch(fbranch.getValue());
    			filter.setAcc(faccount.getValue());
    		}else{
    			current.setBpr_id(bpr_id1);
    			current.setAcc(aaccount.getValue());
    			current.setBranch(abranch.getValue());
    			Bpr_authirizedaccService.update(current,res);
    		}
    		if(res.getCode()!=1){
    			onClick$btn_back();
        		refreshModel(_startPageNumber);
        		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        		Events.sendEvent(evt);
        		btn_save.setDisabled(true);
    		} else {
    			alert(res.getName());
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
    	}
    }
    
    public void onClick$btn_del(){
    	Res res = new Res();
    	try {
    		Bpr_authirizedaccService.remove(current,alias,res);
			if(res.getCode()!=1){
				onClick$btn_back();
        		refreshModel(_startPageNumber);
        		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
        		Events.sendEvent(evt);
        		alert("Данные успешно сохранены");
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}
    }
    
    public void onClick$btn_cancel() {
    	if(fgrd.isVisible()){
            filter = new Bpr_authirizedaccFilter();
    	}
    	onClick$btn_back();
    	addgrd.setVisible(false);
    	fgrd.setVisible(false);
    	Utils.clearForm(addgrd);
    	Utils.clearForm(fgrd);
    	refreshModel(_startPageNumber);
    }
}
