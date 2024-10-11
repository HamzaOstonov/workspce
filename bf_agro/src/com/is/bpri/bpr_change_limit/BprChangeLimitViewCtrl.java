package com.is.bpri.bpr_change_limit;

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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class BprChangeLimitViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_del,btn_save,btn_add;
	private Textbox month_value, procent;
	private Textbox amonth_value, aprocent,aday;
	private Paging bpr_change_limitPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public BprChangeLimitFilter filter = new BprChangeLimitFilter();
	private String alias;
	private int template;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	public BprChangeLimit current = new BprChangeLimit();
	private int state = -1;

	public BprChangeLimitViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			if (this.param.get("state") != null) {
				state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
			}
			System.out.println("ChangeLimits");
			filter.setBpr_id(template);
			alias = (String) session.getAttribute("alias");
			dataGrid.setItemRenderer(new ListitemRenderer() {
				public void render(Listitem row, Object data) throws Exception {
					BprChangeLimit pbpr_change_limit = (BprChangeLimit) data;
					row.setValue(pbpr_change_limit);
					row.appendChild(new Listcell(pbpr_change_limit.getBpr_id()));
					row.appendChild(new Listcell(pbpr_change_limit.getDay()));
					row.appendChild(new Listcell(pbpr_change_limit.getMonth_value()));
					row.appendChild(new Listcell(pbpr_change_limit.getProcent()));
				}
			});
			if(state==1||state==2){
				btn_del.setVisible(false);
				btn_save.setVisible(false);
				btn_add.setVisible(false);
			} else if(state==0){
				btn_del.setVisible(true);
				btn_save.setVisible(true);
				btn_add.setVisible(true);
			} else {
				alert("Что то пошло не так из за State = "+state);
			}
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_del(){
		BprChangeLimitService.remove(current, alias);
		onClick$btn_back();
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$bpr_change_limitPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		bpr_change_limitPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);
		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}
		bpr_change_limitPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (BprChangeLimit) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public BprChangeLimit getCurrent() {
		return current;
	}

	public void setCurrent(BprChangeLimit current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		btn_del.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_save.setDisabled(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd();
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
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		btn_del.setVisible(false);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		btn_del.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			Res res = new Res();
			if (addgrd.isVisible()) {
				if(current==null){
					current = new BprChangeLimit();
				}
				current.setBpr_id(template+"");
				current.setMonth_value(amonth_value.getValue());
				current.setProcent(aprocent.getValue());
				current.setDay(aday.getValue());
				BprChangeLimitService.create(current,res,alias);
//				BprChangeLimitService.create(
//						new BprChangeLimit(0, template, !amonth_value
//								.getValue().equals("") ? Integer
//								.parseInt(amonth_value.getValue()) : 0,
//								!aprocent.getValue().equals("") ? Integer
//										.parseInt(aprocent.getValue()) : 0),
//						alias);
				Utils.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new BprChangeLimitFilter();

				/*
				 * filter.setId(fid.getValue());
				 * filter.setBpr_id(fbpr_id.getValue());
				 * filter.setMonth_value(fmonth_value.getValue());
				 * filter.setProcent(fprocent.getValue());
				 */
			} else {
				current.setId(current.getId());
				current.setBpr_id(template+"");
				current.setMonth_value(month_value.getValue());
				current.setProcent(procent.getValue());

				BprChangeLimitService.update(current,res,alias);
			}
			if(res.getCode()!=1){
				alert("Данные успешно сохранены");
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
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new BprChangeLimitFilter();
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
