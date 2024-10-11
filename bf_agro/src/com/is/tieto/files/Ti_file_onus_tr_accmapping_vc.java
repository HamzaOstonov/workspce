package com.is.tieto.files;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import sun.print.resources.serviceui;

import com.is.cb_cash_requests.Cb_cash_request;
import com.is.cb_cash_requests.Requests_grid_content;
import com.is.utils.RefCBox;

public class Ti_file_onus_tr_accmapping_vc extends GenericForwardComposer
{
	private Listbox data_grid;
	private Window edit_wnd, new_operation_wnd;
	private Textbox edit_wnd$terminal,
					edit_wnd$code,
					edit_wnd$account_10107,
					edit_wnd$account_23510,
					edit_wnd$name,
					edit_wnd$operation_branch,
					edit_wnd$bin,
					new_operation_wnd$name,
					edit_wnd$acc_23508_510;
	
	private RefCBox edit_wnd$operation_id, edit_wnd$cup_operation_id, edit_wnd$operation_corporativ;
	
	private Ti_file_onus_tr_accmapping current_mapping;
	
	public void doAfterCompose(Component comp) throws Exception
	{
        super.doAfterCompose(comp);
        data_grid.setItemRenderer(new ListitemRenderer()
        {
            @SuppressWarnings("unchecked")
            public void render(Listitem row, Object data) throws Exception 
            {
            	Ti_file_onus_tr_accmapping content = (Ti_file_onus_tr_accmapping) data;
            	row.appendChild(new Listcell(Long.toString(content.getId())));
            	row.appendChild(new Listcell(content.getBin()));
            	row.appendChild(new Listcell(content.getTerminal()));
            	row.appendChild(new Listcell(content.getCode()));
            	row.appendChild(new Listcell(content.getAccount_10107()));
            	row.appendChild(new Listcell(content.getAccount_23510()));
            	row.appendChild(new Listcell(content.getAcc_23508_510()));
            	row.appendChild(new Listcell(Long.toString(content.getOperation_id())));
            	row.appendChild(new Listcell(content.getOperation_branch()));
            	row.appendChild(new Listcell(content.getName()));
            	
            	row.setAttribute("current_mapping", content);
            	row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
        			@Override
					public void onEvent(Event event)
							throws Exception 
					{
        				current_mapping = (Ti_file_onus_tr_accmapping)event.getTarget().getAttribute("current_mapping");
        				fill_edit_wnd();
        				edit_wnd.setVisible(true);
					}
            	});
            }
        });
        edit_wnd$operation_id.setModel(
        		(new ListModelList(Service.get_tr_operations()))
        		);
		edit_wnd$cup_operation_id.setModel(
        		(new ListModelList(Service.get_tr_operations()))
				);
		edit_wnd$operation_corporativ.setModel(
        		(new ListModelList(Service.get_tr_operations()))
				);
        data_grid.setModel(new ListModelList(Service.get_Ti_file_onus_tr_accmappings()));
	}
	
	public void onClick$btn_add()
	{
		current_mapping = null;
		fill_edit_wnd();
		edit_wnd.setTitle("Добавление");
		edit_wnd.setVisible(true);
	}
	
	private void fill_edit_wnd()
	{
		edit_wnd$bin.setValue(current_mapping == null?"":current_mapping.getBin());
		edit_wnd$terminal.setValue(current_mapping == null?"":current_mapping.getTerminal());
		edit_wnd$code.setValue(current_mapping == null?"":current_mapping.getCode());
		edit_wnd$account_10107.setValue(current_mapping == null?"":current_mapping.getAccount_10107());
		edit_wnd$account_23510.setValue(current_mapping == null?"":current_mapping.getAccount_23510());
		edit_wnd$operation_id.setSelecteditem(
				current_mapping == null?null:Long.toString(current_mapping.getOperation_id())
						);
		edit_wnd$acc_23508_510.setValue(current_mapping == null?"":current_mapping.getAcc_23508_510());
		//edit_wnd$operation_id.setValue(current_mapping == null?"":Long.toString(current_mapping.getOperation_id()));
		edit_wnd$name.setValue(current_mapping == null?"":current_mapping.getName());
		edit_wnd$operation_branch.setValue(current_mapping == null?"":current_mapping.getOperation_branch());
		edit_wnd$cup_operation_id.setSelecteditem(
				current_mapping == null?null:Long.toString(current_mapping.getCup_operation_id())
						);
		edit_wnd$operation_corporativ.setSelecteditem(
				current_mapping == null?null:Long.toString(current_mapping.getOperation_corporativ())
						);
	}
	
	private void read_form()
	{
		if(current_mapping == null)
		{
			current_mapping = new Ti_file_onus_tr_accmapping();
			current_mapping.setId(null);
		}
		current_mapping.setBin(edit_wnd$bin.getValue());
		current_mapping.setAccount_10107(edit_wnd$account_10107.getValue());
		current_mapping.setAccount_23510(edit_wnd$account_23510.getValue());
		current_mapping.setCode(edit_wnd$code.getValue());
		current_mapping.setName(edit_wnd$name.getValue());
		current_mapping.setOperation_branch(edit_wnd$operation_branch.getValue());
		current_mapping.setOperation_id(
				edit_wnd$operation_id.getValue()==null||edit_wnd$operation_id.getValue().length()<1?null:
				Long.parseLong(edit_wnd$operation_id.getValue()));
		current_mapping.setTerminal(edit_wnd$terminal.getValue());
		current_mapping.setAcc_23508_510(edit_wnd$acc_23508_510.getValue());
		current_mapping.setCup_operation_id(
				edit_wnd$cup_operation_id.getValue()==null||edit_wnd$cup_operation_id.getValue().length()<1?null:
				Long.parseLong(edit_wnd$cup_operation_id.getValue()));
		current_mapping.setOperation_corporativ(
				edit_wnd$operation_corporativ.getValue()==null||edit_wnd$operation_corporativ.getValue().length()<1?null:
				Long.parseLong(edit_wnd$operation_corporativ.getValue()));
	}
	
	public void onClick$save_tb$edit_wnd() throws Exception
	{
		read_form();
		Service.save(current_mapping);
		data_grid.setModel(new ListModelList(Service.get_Ti_file_onus_tr_accmappings()));
		edit_wnd.setVisible(false);
	}
	
	public void onClick$save_tb$new_operation_wnd() throws WrongValueException, Exception
	{
		long id = Service.create_new_operation(new_operation_wnd$name.getValue());
		edit_wnd$operation_id.setModel(
        		(new ListModelList(Service.get_tr_operations()))
        		);
		edit_wnd$cup_operation_id.setModel(
        		(new ListModelList(Service.get_tr_operations()))
				);
		edit_wnd$operation_corporativ.setModel(
        		(new ListModelList(Service.get_tr_operations()))
				);
		edit_wnd$operation_id.setSelecteditem(Long.toString(id));
		new_operation_wnd.setVisible(false);
		alert("Новая опепация \"" + new_operation_wnd$name.getValue() + "\" добавлена");
	}
	
	public void onClick$new_operation$edit_wnd()
	{
		new_operation_wnd.setVisible(true);
	}
}
