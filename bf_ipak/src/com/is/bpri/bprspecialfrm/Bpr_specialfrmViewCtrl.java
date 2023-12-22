package com.is.bpri.bprspecialfrm;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
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
import org.zkoss.zul.Paging;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class Bpr_specialfrmViewCtrl extends GenericForwardComposer{
    
	private Div frm;
    private Listbox dataGrid;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
    private RefCBox bpr_spec,bpr_spec_value;
    private RefCBox abpr_spec,abpr_spec_value;
    private RefCBox fbpr_spec,fbpr_spec_value;
    private Paging bpr_specialfrmPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private boolean _needsTotalSizeUpdate = true;
    private int bpr_id1;
    private String alias;
    private int state = -1;
    private int bpr_type;
    
    public Bpr_specialfrmFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private Bpr_specialfrm current = new Bpr_specialfrm();

    public Bpr_specialfrmViewCtrl() {
            super('$', false, false);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
       	super.doAfterCompose(comp);
        try {
        	binder = new AnnotateDataBinder(comp);
            binder.bindBean("current", this.current);
            binder.loadAll();
            alias = (String) session.getAttribute("alias");
            dataGrid.setItemRenderer(new ListitemRenderer(){
            	@Override
            	public void render(Listitem row, Object data) throws Exception {
            		try {
            			Bpr_specialfrm pbpr_specialfrm = (Bpr_specialfrm) data;

                		row.setValue(pbpr_specialfrm);
                		Listcell lc = new Listcell(pbpr_specialfrm.getId()+"");
                		lc.setVisible(false);
                		row.appendChild(lc);
            			row.appendChild(new Listcell(pbpr_specialfrm.getBpr_id()+""));
            			row.appendChild(new Listcell(pbpr_specialfrm.getBpr_spec()));
            			row.appendChild(new Listcell(pbpr_specialfrm.getBpr_spec_value()));
					} catch (Exception e) {
						e.printStackTrace();
					}
        	}});
            if(this.param.get("bpr_id")!=null){
            	bpr_id1 = Integer.parseInt(((String[]) this.param.get("bpr_id"))[0]);
            }
            if(this.param.get("state")!=null){
            	state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
            }
            if(this.param.get("bpr_type")!=null){
            	bpr_type = Integer.parseInt(((String[]) this.param.get("bpr_type"))[0]);
            }
            filter = new Bpr_specialfrmFilter();
            filter.setBpr_id(bpr_id1);
            RefCBoxModels();
        	refreshModel(_startPageNumber);
        	if(state==1||state==2){
        		btn_save.setVisible(false);
        		btn_del.setVisible(false);
        		btn_add.setVisible(false);
        	} else if (state==0){
        		btn_add.setVisible(true);
        		btn_save.setVisible(true);
        		btn_del.setVisible(true);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void onPaging$bpr_specialfrmPaging(ForwardEvent event){
    	final PagingEvent pe = (PagingEvent) event.getOrigin();
    	_startPageNumber = pe.getActivePage();
    	refreshModel(_startPageNumber);
    }
    
    private void RefCBoxModels(){
    	bpr_spec.setModel(new ListModelList(Bpr_specialfrmService.getAnketNum(bpr_type,alias)));
    	abpr_spec.setModel(new ListModelList(Bpr_specialfrmService.getAnketNum(bpr_type,alias)));
    	fbpr_spec.setModel(new ListModelList(Bpr_specialfrmService.getAnketNum(bpr_type,alias)));
    }
    
    public void onSelect$bpr_spec(){
    	choiseAnket(bpr_spec.getValue());
    }
    
    public void onSelect$abpr_spec(){
    	choiseAnket(abpr_spec.getValue());
    }
    
    public void onSelect$fbpr_spec(){
    	choiseAnket(fbpr_spec.getValue());
    }
    
    private void choiseAnket(String value){
    	if(value.equals("19")){
    		bpr_spec_value.setModel(new ListModelList(Bpr_specialfrmService.getAnket19(alias)));
    		abpr_spec_value.setModel(new ListModelList(Bpr_specialfrmService.getAnket19(alias)));
    		fbpr_spec_value.setModel(new ListModelList(Bpr_specialfrmService.getAnket19(alias)));
    	} else if(value.equals("36")||value.equals("115")){
    		bpr_spec_value.setModel(new ListModelList(Bpr_specialfrmService.getAnket36(alias)));
    		abpr_spec_value.setModel(new ListModelList(Bpr_specialfrmService.getAnket36(alias)));
    		fbpr_spec_value.setModel(new ListModelList(Bpr_specialfrmService.getAnket36(alias)));
    	} 
    }
    
    public void onInitRenderLater$bpr_spec(){
    	if(current!=null){
    		bpr_spec.setSelecteditem(current.getBpr_spec());
        	choiseAnket(bpr_spec.getValue());
    	}
    }
    
    public void onInitRenderLater$bpr_spec_value(){
    	if(current!=null){
    		bpr_spec_value.setSelecteditem(current.getBpr_spec_value());
    	}
    }
    
    private void refreshModel(int activePage){
    	bpr_specialfrmPaging.setPageSize(_pageSize);
    	model = new PagingListModel(activePage, _pageSize, filter, "");
    	if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize();
        _needsTotalSizeUpdate = false;
    	}
    	filter.setBpr_id(bpr_id1);
    	bpr_specialfrmPaging.setTotalSize(_totalSize);
    	dataGrid.setModel((ListModel) model);
    	if (model.getSize()>0){
    		this.current =(Bpr_specialfrm) model.getElementAt(0);
    		sendSelEvt();
    	}
    }

    //Omitted...
    public Bpr_specialfrm getCurrent() {
    	return current;
    }

    public void setCurrent(Bpr_specialfrm current) {
    	this.current = current;
    }

    public void onDoubleClick$dataGrid$grd() {
    	if(current!=null&&!current.getBpr_spec().equals("114")&&!current.getBpr_spec().equals("116")){
    		grd.setVisible(false);
            frm.setVisible(true);
            frmgrd.setVisible(true);
            bpr_spec_value.setSelecteditem(current.getBpr_spec_value());
            addgrd.setVisible(false);
            fgrd.setVisible(false);
            btn_back.setImage("/images/folder.png");
            btn_back.setLabel(Labels.getLabel("grid"));
            btn_save.setDisabled(false);
            onSelect$abpr_spec();
            onSelect$fbpr_spec();
            onSelect$bpr_spec();
        }
   	}

    
    private void spec114Ctrl(boolean bool){
    	btn_save.setDisabled(bool);
		btn_del.setDisabled(bool);
		bpr_spec.setReadonly(bool);
		bpr_spec_value.setReadonly(bool);
		abpr_spec.setReadonly(bool);
		abpr_spec_value.setReadonly(bool);
		fbpr_spec.setReadonly(bool);
		fbpr_spec_value.setReadonly(bool);
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
        if(current!=null){
        	if(current.getBpr_spec().equals("114")&&current.getBpr_spec().equals("116")){
            	spec114Ctrl(true);
            } else {
            	spec114Ctrl(false);
            }
        }
    }


    public void onClick$btn_add() {
    	grd.setVisible(false);
    	frm.setVisible(true);
    	btn_back.setImage("/images/folder.png");
        btn_back.setLabel(Labels.getLabel("grid"));
        btn_save.setDisabled(false);
    	frmgrd.setVisible(false);
    	addgrd.setVisible(true);
    	fgrd.setVisible(false);
    	spec114Ctrl(false);
    }

    public void onClick$btn_search() {
    	onDoubleClick$dataGrid$grd();
    	frmgrd.setVisible(false);
    	addgrd.setVisible(false);
    	fgrd.setVisible(true);
    	btn_save.setDisabled(false);
    }


    public void onClick$btn_save() {
    	try{
    		Res res = new Res();
    		if(addgrd.isVisible()){
    			if(current==null){
    				current = new Bpr_specialfrm();
    			}
    			current.setBpr_id(bpr_id1);
    			current.setBpr_spec(abpr_spec.getValue());
    			current.setBpr_spec_value(abpr_spec_value.getValue());
    			Bpr_specialfrmService.create(current,res);
    			Utils.clearForm(addgrd);
    			frmgrd.setVisible(true);
    			addgrd.setVisible(false);
    			fgrd.setVisible(false);
    		}else if(fgrd.isVisible()){
    			filter = new Bpr_specialfrmFilter();
    			filter.setBpr_spec(fbpr_spec.getValue());
    			filter.setBpr_spec_value(fbpr_spec_value.getValue());
    		}else{
    			current.setBpr_id(bpr_id1);
    			current.setBpr_spec(bpr_spec.getValue());
    			current.setBpr_spec_value(bpr_spec_value.getValue());
    			Bpr_specialfrmService.update(current,res);
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
			Bpr_specialfrmService.remove(current,alias,res);
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
            filter = new Bpr_specialfrmFilter();
    	}
    	onClick$btn_back();
    	frmgrd.setVisible(true);
    	addgrd.setVisible(false);
    	fgrd.setVisible(false);
    	Utils.clearForm(addgrd);
    	Utils.clearForm(fgrd);
    	refreshModel(_startPageNumber);
    }
}
