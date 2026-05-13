package com.is.file_reciever.energo;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.file_reciever.File_reciever_vc;
import com.is.file_reciever.simple.Fr_file;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

public class Ext_in_file_recordsViewCtrl extends GenericForwardComposer{
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
    private Toolbarbutton btn_back, btn_remove_file, doc_action;
    private Toolbar tb;
    private Window protocols_wnd;
    private Include protocols_wnd$protocols_frame;
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
    private String un;
    private String pwd;
    private String branch;
    private int uid;

    public String current_ext_file;
    
    public String getCurrent_ext_file()
	{
		return current_ext_file;
	}
	public void setCurrent_ext_file(String current_ext_file)
	{
		this.current_ext_file = current_ext_file;
	}

	public Ext_in_file_recordsFilter filter;

    PagingListModel model = null;
    ListModelList lmodel =null;
    private AnnotateDataBinder binder;
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");


private Ext_in_file_records current = new Ext_in_file_records();

    public Ext_in_file_recordsViewCtrl() {
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
            binder.bindBean("current_ext_file", this.current_ext_file);
            binder.loadAll();
    String[] parameter = (String[]) param.get("ht");
    if (parameter!=null){
            _pageSize = 20;//Integer.parseInt( parameter[0])/36;
            dataGrid.setRows(20);//Integer.parseInt( parameter[0])/36);
    }
    String [] current_fr_id = (String[])param.get("fr_id");
    
    
    
    un = (String) session.getAttribute("un");
    pwd= (String) session.getAttribute("pwd");
    branch= (String) session.getAttribute("branch");
    uid =(Integer)session.getAttribute("uid");
    current_file_fr_id = Long.parseLong(current_fr_id[0]);
    
    List<RefData> ext_files = Ext_in_file_recordsService.getExt_files(current_file_fr_id);
//    System.out.println(ext_files);
    
    cmb_ext_file.setModel(new ListModelList(ext_files));
    
//    cmb_ext_file.addEventListener(Events.ON_RENDER, new EventListener()
//	{
//		@Override
//		public void onEvent(Event event)
//				throws Exception 
//		{
//			cmb_ext_file.setSelectedIndex(0);
//		}
//	});
    
//    current_ext_file = ext_files.get(0).getData();
    
//    System.out.println("size:"+cmb_ext_file.getModel().getSize());
//    cmb_ext_file.setSelectedIndex(0);
    
//    cmb_ext_file.

//    cmb_ext_file.setSelectedIndex(0);
//    cmb_ext_file.setSelecteditem(ext_files.get(0).getData()+"");
//    System.out.println(ext_files.get(0).getData());
//    onSelect$cmb_ext_file();
    
    
    
        dataGrid.setItemRenderer(new ListitemRenderer(){
    @SuppressWarnings("unchecked")
    public void render(Listitem row, Object data) throws Exception {
                Ext_in_file_records pExt_in_file_records = (Ext_in_file_records) data;

                row.setValue(pExt_in_file_records);
                if (pExt_in_file_records.getError_id() != 0l) row.setStyle("background-color:#FFBDBD;");
                /*row.appendChild(new Listcell(""+pExt_in_file_records.getId()));
                row.appendChild(new Listcell(""+pExt_in_file_records.getIn_file_id()));
                row.appendChild(new Listcell(""+pExt_in_file_records.getType_record_id()));*/
                row.appendChild(new Listcell(pExt_in_file_records.getRec_value()));
                //row.appendChild(new Listcell(""+pExt_in_file_records.getError_id()));
                row.appendChild(new Listcell(pExt_in_file_records.getErr_message()));


    }});
        
//    cmb_ext_file.setSelectedIndex(1);
//    onSelect$cmb_ext_file();
}

public void onPaging$ext_in_file_recordsPaging(ForwardEvent event){
final PagingEvent pe = (PagingEvent) event.getOrigin();
_startPageNumber = pe.getActivePage();
refreshModel(_startPageNumber);
}

//private void onAfterRender$cmb_ext_file()
//{
//	cmb_ext_file.setSelectedIndex(0);
//}

public void onAfterRender$cmb_ext_file(Event event)
{
	if(cmb_ext_file.getModel().getSize()>0)
	{
		cmb_ext_file.setSelectedIndex(0);
		onSelect$cmb_ext_file();
	}
}

private void refreshModel(int activePage){
    ext_in_file_recordsPaging.setPageSize(_pageSize);
model = new PagingListModel(activePage, _pageSize, filter, "");

//if(_needsTotalSizeUpdate) {
        _totalSize = model.getTotalSize(filter, "");
//        _needsTotalSizeUpdate = false;
//}

ext_in_file_recordsPaging.setTotalSize(_totalSize);

dataGrid.setModel((ListModel) model);
if (model.getSize()>0){
this.current =(Ext_in_file_records) model.getElementAt(0);
sendSelEvt();
}
}



//Omitted...
public Ext_in_file_records getCurrent() {
return current;
}

public void setCurrent(Ext_in_file_records current) {
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
            
            parsed_data.setValue(Ext_in_file_recordsService.get_file_records_details(current.getId()));
            
            Ext_in_file_recordsService.get_file_deal_id_from_file(current.getIn_file_id());
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

public void onClick$btn_remove_file()
{
	try
    {
    Messagebox.show("Отменить файл? "+
    		cmb_ext_file.getValue(),
    		"Подтверждение", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener()
       {
          public void onEvent(Event e)
             {
        	    int answer = (Integer) e.getData();
             //if (Messagebox.ON_YES.equals(e.getName()))
        	    if (answer == Messagebox.YES)
                {
            	 	Ext_in_file_recordsService.remove_file(Long.parseLong(cmb_ext_file.getValue()));
            	 	refreshModel(_startPageNumber);
                }
             else
                //if (Messagebox.ON_NO.equals(e.getName())){}
         	    if (answer == Messagebox.NO) {}
             }
       });

    }
 catch (InterruptedException e){e.printStackTrace();}
}

public void onClick$btn_save() {
try{
    if(addgrd.isVisible()){
            
        CheckNull.clearForm(addgrd);
        frmgrd.setVisible(true);
        addgrd.setVisible(false);
        fgrd.setVisible(false);
    }else if(fgrd.isVisible()){
        filter = new Ext_in_file_recordsFilter();
        
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
	filter = new Ext_in_file_recordsFilter();
    filter.setIn_file_id(Long.parseLong(cmb_ext_file.getValue()));
    
    btn_remove_file.setVisible(
    		Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue()))
    		== 0
    		);
    
    if (Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue())) == 2l)
    	doc_action.setLabel("Ввести проводки");
    if (Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue())) == 3l)
    	doc_action.setLabel("Утвердить проводки");
    if (Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue())) == 4l)
    	doc_action.setLabel("Провести проводки");
    
    refreshModel(_startPageNumber);
}
public void onClick$btn_cancel() {
    if(fgrd.isVisible()){
            filter = new Ext_in_file_recordsFilter();
    }
onClick$btn_back();
frmgrd.setVisible(true);
addgrd.setVisible(false);
fgrd.setVisible(false);
CheckNull.clearForm(addgrd);
CheckNull.clearForm(fgrd);
refreshModel(_startPageNumber);
}

public void show_created_objects(Long record_id)
{
	List<Ext_created_object> created_objects = Ext_in_file_recordsService.
		get_created_objects(record_id);
	
	//objectsGrid.getChildren().clear();
	
	objectsGrid.getChildren().clear();
	
	for(Ext_created_object current_object : created_objects)
	{
		Listitem cur_li = new Listitem();
		
		
		cur_li.setAttribute("current_object", current_object);
		cur_li.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener()
    	{
			@Override
			public void onEvent(Event event)
					throws Exception 
			{
				Ext_created_object current_object = (Ext_created_object)event.
					getTarget().getAttribute("current_object");
				String show_object_url = Ext_in_file_recordsService.get_module(
						current_object.getDeal_group_id());
				protocols_wnd$protocols_frame.setSrc(show_object_url+"?id="+
						current_object.getObject_id());
				protocols_wnd.setVisible(true);
			}
    	});
		
		Listcell lc = new Listcell(
				Ext_in_file_recordsService.get_deal_group_name(current_object.getDeal_group_id())
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
		Button btn_show_out_file = new Button();
		btn_show_out_file.setLabel("Исходящие файлы");
		btn_show_out_file.setAttribute("object_id", current_object.getId());
		btn_show_out_file.addEventListener(Events.ON_CLICK, new EventListener()
            	{
					@Override
					public void onEvent(Event event)
							throws Exception 
					{
						long object_id = (Long)event.getTarget().getAttribute("object_id");
						File_reciever_vc current_iframe = (File_reciever_vc)session.getAttribute("current_iframe");
						current_iframe.SetShow_details_wnd$file_details_frameSrc(
								"ext_out_files.zul?object_id="+
								Long.toString(object_id));
					}
            	});
		lc = new Listcell();
		lc.appendChild(btn_show_out_file);
		cur_li.appendChild(lc);
		btn_show_out_file = new Button();
		btn_show_out_file.setLabel("Протокол");
		btn_show_out_file.setAttribute("object_id", current_object.getObject_id());
		btn_show_out_file.setAttribute("deal_group_id", current_object.getDeal_group_id());
		btn_show_out_file.addEventListener(Events.ON_CLICK, new EventListener()
            	{
					@Override
					public void onEvent(Event event)
							throws Exception 
					{
						//long object_id = (Long)event.getTarget().getAttribute("object_id");
						
						protocols_wnd$protocols_frame.setSrc("ext_protocol.zul?deal_group="+
								(Long)event.getTarget().getAttribute("deal_group_id")+
								"&deal_id=1"+
								"&object_id="+(Long)event.getTarget().getAttribute("object_id")
								);
						protocols_wnd.setVisible(true);
					}
            	});
		lc = new Listcell();
		lc.appendChild(btn_show_out_file);
		cur_li.appendChild(lc);
		objectsGrid.appendChild(cur_li);
	}
	//objectsGrid
}
public void onClick$show_protocol_btn()
{
	protocols_wnd$protocols_frame.setSrc("ext_protocol.zul?deal_group="+
			Ext_in_file_recordsService.get_file_deal_id_from_file(current.getIn_file_id())+
			"&deal_id=1"+
			"&object_id="+current.getId()
			);
	protocols_wnd.setVisible(true);
}

public void onClick$close_protocols_wnd$protocols_wnd()
{
	protocols_wnd.setVisible(false);
}

public void onClick$doc_action() throws NumberFormatException, Exception
{
	Connection c = null;
	try
	{
		c = ConnectionPool.getConnection(un, pwd);
	/*	if (Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue())) == 2l)
		{
			Ext_in_file_recordsService.documents_action(Long.parseLong(cmb_ext_file.getValue()), 1l, c);
		}
		else if (Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue())) == 3l)
		{
			Ext_in_file_recordsService.documents_action(Long.parseLong(cmb_ext_file.getValue()), 2l, c);
		}
		else if (Ext_in_file_recordsService.get_ext_file_state(Long.parseLong(cmb_ext_file.getValue())) == 4l)
		{*/
	//		Ext_in_file_recordsService.documents_action(Long.parseLong(cmb_ext_file.getValue()), 3l, c);
			Ext_in_file_recordsService.fill_02_005_pre(Long.parseLong(cmb_ext_file.getValue()), c);
	//	}
		//c.rollback();
		c.commit();
	}
	catch(Exception e)
	{
		try{if(c != null)c.rollback();}catch(Exception e1){}
		throw e;
	}
	finally
	{
		try{if(c != null)c.close();}catch(Exception e){}
	}
}

public void onClick$doc_action_remove() throws Exception
{
	Connection c = null;
	try
	{
		c = ConnectionPool.getConnection(un, pwd);
		Ext_in_file_recordsService.documents_action(Long.parseLong(cmb_ext_file.getValue()), 6l, c);
		c.commit();
	}
	catch(Exception e)
	{
		try{if(c != null)c.rollback();}catch(Exception e1){}
		throw e;
	}
	finally
	{
		try{if(c != null)c.close();}catch(Exception e){}
	}
}
}

