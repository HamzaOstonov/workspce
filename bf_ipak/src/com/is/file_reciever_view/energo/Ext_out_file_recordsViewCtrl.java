package com.is.file_reciever_view.energo;

import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class Ext_out_file_recordsViewCtrl extends GenericForwardComposer{
    private Div frm;
    private Listbox dataGrid, objectsGrid;
    private Paging contactPaging;
    private Div grd;
    private Grid addgrd,frmgrd,fgrd;
    private RefCBox cmb_ext_file;
    private Toolbarbutton btn_last;
    private Toolbarbutton btn_next;
    private Toolbarbutton btn_prev;
    private Toolbarbutton btn_first;
    private Toolbarbutton btn_add;
    private Toolbarbutton btn_search;
    private Toolbarbutton btn_back;
    private Toolbar tb;
    private Label arch_name;
    private Label rec_value;
    private Label err_message, parsed_data;
    private Textbox id,in_file_id,type_record_id,error_id;
    private Textbox aid,ain_file_id,atype_record_id,arec_value,aerror_id,aerr_message ;
    private Textbox fid,fin_file_id,ftype_record_id,frec_value,ferror_id,ferr_message ;
    private Paging ext_in_file_recordsPaging;
    private  int _pageSize = 15;
    private int _startPageNumber = 0;
    private int _totalSize = 0;
    private long current_file_fr_id = 0;
    private boolean _needsTotalSizeUpdate = true;

    
    public Ext_out_file_recordsFilter filter;

    Out_file_PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Ext_out_file_records current = new Ext_out_file_records();

    public Ext_out_file_recordsViewCtrl() {
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
            _pageSize = 20;//Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(20);//Integer.parseInt( parameter[0])/36);
    }
    String [] current_fr_id = (String[])param.get("fr_id");
    current_file_fr_id = Long.parseLong(current_fr_id[0]);
    
    cmb_ext_file.setModel(new ListModelList(
    		Ext_out_file_recordsService.getExt_files(current_file_fr_id)));

        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Ext_out_file_records pExt_out_file_records = (Ext_out_file_records) data;

                row.setValue(pExt_out_file_records);
                
                /*row.appendChild(new Listcell(""+pExt_in_file_records.getId()));
                row.appendChild(new Listcell(""+pExt_in_file_records.getIn_file_id()));
                row.appendChild(new Listcell(""+pExt_in_file_records.getType_record_id()));*/
                row.appendChild(new Listcell(pExt_out_file_records.getRec_value()));
                //row.appendChild(new Listcell(""+pExt_in_file_records.getError_id()));
                row.appendChild(new Listcell(pExt_out_file_records.getErr_message()));


    }});
        
//    cmb_ext_file.setSelectedIndex(1);
//    onSelect$cmb_ext_file();
}

public void onAfterRender$cmb_ext_file(Event event)
{
	if(cmb_ext_file.getModel().getSize()>0)
	{
		cmb_ext_file.setSelectedIndex(0);
		onSelect$cmb_ext_file();
	}
}

public void onPaging$ext_in_file_recordsPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}


private void refreshModel(int activePage){
    ext_in_file_recordsPaging.setPageSize(_pageSize);
model = new Out_file_PagingListModel(activePage, _pageSize, filter, "");

//if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter, "");
//        _needsTotalSizeUpdate = false;
//}

ext_in_file_recordsPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Ext_out_file_records) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Ext_out_file_records getCurrent() {
return current;
}

public void setCurrent(Ext_out_file_records current) {
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
            
            parsed_data.setValue(Ext_out_file_recordsService.get_file_records_details(current.getId()));
            show_created_objects(current.getId());
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
            
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new Ext_out_file_recordsFilter();
        
          filter.setRec_value(frec_value.getValue().length()>0?frec_value.getValue():null);
          filter.setErr_message(ferr_message.getValue().length()>0?Long.parseLong(ferr_message.getValue()):null);
    }
onClick$btn_back();
refreshModel(_startPageNumber);
SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
Events.sendEvent(evt);
} catch (Exception e) {
    e.printStackTrace();
}

}
public void onSelect$cmb_ext_file()
{
	filter = new Ext_out_file_recordsFilter();
    filter.setOut_file_id(Long.parseLong(cmb_ext_file.getValue()));
    refreshModel(_startPageNumber);
}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new Ext_out_file_recordsFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

public void onClick$show_objects()
{
	Listitem cur_li = new Listitem();
	objectsGrid.appendChild(cur_li);
	Listcell lc = new Listcell();
}
public void show_created_objects(Long record_id)
{
	List<Ext_created_object> created_objects = Ext_out_file_recordsService.
		get_created_objects(record_id);
	for(Ext_created_object current_object : created_objects)
	{
		Listitem cur_li = new Listitem();
		
		Listcell lc = new Listcell(
				Ext_out_file_recordsService.get_deal_group_name(current_object.getDeal_group_id())
				);
		cur_li.appendChild(lc);
		lc = new Listcell(
				Long.toString(current_object.getObject_id())
				);
		cur_li.appendChild(lc);
		lc = new Listcell(
				current_object.getBranch()
				);
		cur_li.appendChild(lc);
		objectsGrid.appendChild(cur_li);
	}
	//objectsGrid
}
}

