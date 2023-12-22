package com.is.bpri.bpr_ld_account;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class BprLdAccountViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
	private Textbox account;
	private Textbox aaccount,acc_order,acc_group_id,facc_order,facc_group_id;
	private RefCBox acc_type_id, aacc_type_id;
	private Paging bprldaccountPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	public BprLdAccountFilter filter = new BprLdAccountFilter();
	private String alias;
	private int template;
	private Checkbox anewacc;
	private PagingListModel model = null;
	private AnnotateDataBinder binder;
	private Row row,orderrow,forderrow,fgrouprow,grouprow,checkrow,frow;
	private BprLdAccount current = new BprLdAccount();
	private int state = -1;

	public BprLdAccountViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			System.out.println("Счета BprLdAccount.zul");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			if (this.param.get("state") != null) {
				state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
			}
			filter.setBpr_id(template);
			alias = (String) session.getAttribute("alias");
			dataGrid.setItemRenderer(new ListitemRenderer() {
				public void render(Listitem row, Object data) throws Exception {
					BprLdAccount pBprLdAccount = (BprLdAccount) data;
					row.setValue(pBprLdAccount);

					row.appendChild(new Listcell(pBprLdAccount.getAcc_type_id()+""));
					row.appendChild(new Listcell(pBprLdAccount.getAccount()));
					row.appendChild(new Listcell(pBprLdAccount.getAcc_order()));
					row.appendChild(new Listcell(pBprLdAccount.getAcc_group_id()));
				}
			});
			acc_type_id.setModel((new ListModelList(com.is.bpri.bpr_ld_account.BprLdAccountService.getAcc_type_id(alias))));
			aacc_type_id.setModel((new ListModelList(com.is.bpri.bpr_ld_account.BprLdAccountService.getAcc_type_id(alias))));
			if(state==1||state==2){
				btn_save.setVisible(false);
				btn_del.setVisible(false);
				btn_add.setVisible(false);
			} else if(state==0){
				btn_add.setVisible(true);
				btn_save.setVisible(true);
				btn_del.setVisible(true);
			}
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onPaging$bprldaccountPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		try {
			bprldaccountPaging.setPageSize(_pageSize);
			model = new PagingListModel(activePage, _pageSize, filter, alias);
			_totalSize = model.getTotalSize(filter,alias);
			bprldaccountPaging.setTotalSize(_totalSize);
			dataGrid.setModel((ListModel) model);
			if (model.getSize() > 0) {
				this.current = (BprLdAccount) model.getElementAt(0);
				sendSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onCheck$anewacc(){
		if(anewacc.isChecked()){
			row.setVisible(false);
			Utils.clearForm(row);
			grouprow.setVisible(true);
			orderrow.setVisible(true);
		} else {
			Utils.clearForm(grouprow);
			Utils.clearForm(orderrow);
			grouprow.setVisible(false);
			orderrow.setVisible(false);
			row.setVisible(true);
		}
	}

	// Omitted...
	public BprLdAccount getCurrent() {
		return current;
	}

	public void setCurrent(BprLdAccount current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		try {
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			if(current!=null){
				if(current.getIs_open()==1){
					frow.setVisible(false);
					fgrouprow.setVisible(true);
					forderrow.setVisible(true);
				} else {
					frow.setVisible(true);
					fgrouprow.setVisible(false);
					forderrow.setVisible(false);
				}
			}
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			btn_save.setDisabled(false);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onClick$btn_back() {
		try {
			if (frm.isVisible()) {
				frm.setVisible(false);
				grd.setVisible(true);
				btn_back.setImage("/images/file.png");
				btn_back.setLabel(Labels.getLabel("back"));
			} else
				onDoubleClick$dataGrid$grd();
		} catch (Exception e) {
			e.printStackTrace();
			alert(e.getMessage());
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		dataGrid.setSelectedIndex(model.getSize() - 1);
		sendSelEvt();
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() != 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (model.getSize() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	private void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (model.getSize() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		try {
			onDoubleClick$dataGrid$grd();
			frmgrd.setVisible(false);
			addgrd.setVisible(true);
			fgrd.setVisible(false);
			anewacc.setChecked(false);
			row.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onSelect$aacc_type_id(){
		String t = aacc_type_id.getValue();
		if(t.equals("108")||t.equals("107")||t.equals("105")||t.equals("95")||t.equals("94")||t.equals("92")||t.equals("90")||t.equals("88")||t.equals("84")||t.equals("")||t.equals("83")||
				t.equals("82")||t.equals("81")||t.equals("78")||t.equals("77")||t.equals("76")||t.equals("63")||t.equals("62")||t.equals("60")||t.equals("54")||t.equals("53")||t.equals("52")||t.equals("51")||t.equals("50")||
				t.equals("43")||t.equals("42")||t.equals("41")||t.equals("22")||t.equals("20")||t.equals("19")||t.equals("16")||t.equals("15")||t.equals("14")||t.equals("10")||t.equals("7")||t.equals("6")||t.equals("1")){
			checkrow.setVisible(false);
			row.setVisible(true);
		} else {
			checkrow.setVisible(true);
			onCheck$anewacc();
		}
	}

	public void onClick$btn_search() {
		try {
			onDoubleClick$dataGrid$grd();
			frmgrd.setVisible(false);
			addgrd.setVisible(false);
			fgrd.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onClick$btn_save() {
		try {
			int is_open;
			if(anewacc.isChecked()){
				is_open = 1;
			} else {
				is_open = 0;
			}
			Res res = new Res();
			if (addgrd.isVisible()) {
				current = new BprLdAccount();
				current.setAcc_type_id(aacc_type_id.getValue());
				current.setBpr_id(template);
				current.setAccount(aaccount.getValue());
				current.setIs_open(is_open);
				current.setAcc_order(acc_order.getValue());
				current.setAcc_group_id(acc_group_id.getValue());
				BprLdAccountService.create(current, alias,res);
				if(res.getCode()!=1){
					Utils.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
				}
			} else if (fgrd.isVisible()) {
				filter = new BprLdAccountFilter();

			} else {
				current.setBpr_id(template);
				current.setAcc_type_id(acc_type_id.getValue());
				current.setAccount(account.getValue());
				current.setIs_open(is_open);
				current.setAcc_order(facc_order.getValue());
				current.setAcc_group_id(facc_group_id.getValue());
				BprLdAccountService.update(current, alias,res);
			}
			if(res.getCode()!=1){
				onClick$btn_back();
				btn_save.setDisabled(true);
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(e.getMessage());
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onClick$btn_del(){
		Res res = new Res();
		try {
			BprLdAccountService.remove(current, alias, res);
			if(res.getCode()!=1){
				Utils.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
				alert("Данные успешно удалены");
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_cancel() {
		try {
			if (fgrd.isVisible()) {
				filter = new BprLdAccountFilter();
			}

			onClick$btn_back();
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			Utils.clearForm(addgrd);
			Utils.clearForm(fgrd);
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
}
