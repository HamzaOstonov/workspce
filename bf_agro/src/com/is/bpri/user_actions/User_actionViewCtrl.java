package com.is.bpri.user_actions;

import java.text.SimpleDateFormat;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;

import com.is.bpri.utils.Utils;
import com.is.utils.RefCBox;

@SuppressWarnings("serial")
public class User_actionViewCtrl extends GenericForwardComposer{
	
	private Div grd,frm;
	private Paging user_actionsPaging;
	private Listbox dataGrid;
	private Datebox action_date;
	private RefCBox type_action;
	private Textbox client;
	private AnnotateDataBinder binder;
	private User_actions current = new User_actions();
	private User_actionsFilter filter = new User_actionsFilter();
	private PagingListModel model = null;
	private String alias = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private int _pageSize = 20;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	
	public User_actionViewCtrl() {
		super('$',false,false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			alias = (String) session.getAttribute("alias");
			dataGrid.setItemRenderer(new ListitemRenderer() {
				
				@Override
				public void render(Listitem row, Object data) throws Exception {
					User_actions val = (User_actions) data;
					row.setValue(val);
					Listcell id = new Listcell(val.getId()+"");
					id.setVisible(false);
					Listcell user_id = new Listcell(val.getUser_id()+"");
					user_id.setVisible(false);
					Listcell act_type = new Listcell(val.getAct_type()+"");
					act_type.setVisible(false);
					row.appendChild(id);
					row.appendChild(user_id);
					row.appendChild(act_type);
					row.appendChild(new Listcell(val.getUser_name()));
					row.appendChild(new Listcell(sdf.format(val.getAction_date())));
					row.appendChild(new Listcell(val.getAct_name()));
					row.appendChild(new Listcell(val.getGange_id()==0?"":val.getGange_id()+""));
					row.appendChild(new Listcell(val.getClient_id()));
				}
			});
			type_action.setModel(new ListModelList(User_actionsService.getUser_actions(alias)));
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refreshModel(int activePage) {
		user_actionsPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize(filter, alias);
		user_actionsPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
//		if (model.getSize() > 0) {
//			sendSelEvt();
//		}
	}
	
//	private void sendSelEvt() {
//		if (dataGrid.getSelectedIndex() == 0) {
//			btn_first.setDisabled(true);
//			btn_prev.setDisabled(true);
//		} else {
//			btn_first.setDisabled(false);
//			btn_prev.setDisabled(false);
//		}
//		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
//			btn_next.setDisabled(true);
//			btn_last.setDisabled(true);
//		} else {
//			btn_next.setDisabled(false);
//			btn_last.setDisabled(false);
//		}
//		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
//		Events.sendEvent(evt);
//	}
	
	public void onClick$btn_search(){
		Utils.clearForm(frm);
		getFilterValue();
		grd.setVisible(false);
		frm.setVisible(true);
	}
	
	public void onClick$btn_canceling(){
		filter = new User_actionsFilter();
		frm.setVisible(false);
		grd.setVisible(true);
	}
	
	public void onClick$btn_saving(){
		if(action_date.getValue()!=null){
			filter.setAction_date(action_date.getText());
		}
		if(!type_action.getValue().equals("")){
			filter.setAct_type(Integer.parseInt(type_action.getValue()));
		}
		if(!client.getValue().equals("")){
			filter.setClient_id(client.getValue());
		}
		refreshModel(_startPageNumber);
		frm.setVisible(false);
		grd.setVisible(true);
	}
	
	private void getFilterValue(){
		if(filter!=null){
			action_date.setText(filter.getAction_date());
			type_action.setSelecteditem(filter.getAct_type()+"");
			client.setValue(filter.getClient_id());
		}
	}
	
}
