package com.is.bpri.bpr_ld_forms;

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
public class BprLdFormsViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_add;
	private RefCBox is_ld,ais_ld,ascoring,scoring,fscoring;
	private Paging bprldformsPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public BprLdFormsFilter filter = new BprLdFormsFilter();
	private String alias;
	private int template;
	private int state = -1;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private BprLdForms current = new BprLdForms();

	public BprLdFormsViewCtrl() {
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
			filter.setBpr_id(template);
			alias = (String) session.getAttribute("alias");
			dataGrid.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					BprLdForms pBprLdForms = (BprLdForms) data;
					row.setValue(pBprLdForms);
					Listcell lc = new Listcell(pBprLdForms.getIs_ld());
					lc.setVisible(false);
					row.appendChild(new Listcell(pBprLdForms.getBpr_id() + ""));
					row.appendChild(lc);
					row.appendChild(new Listcell(pBprLdForms.getName()));
					row.appendChild(new Listcell(pBprLdForms.getScoring()));
				}
			});
			RefCBoxModels();
			refreshModel(_startPageNumber);
			if(state==1||state==2){
				btn_save.setVisible(false);
				btn_add.setVisible(false);
			} else if(state==0){
				btn_add.setVisible(true);
				btn_save.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onPaging$bprldformsPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void RefCBoxModels(){
		is_ld.setModel(new ListModelList(BprLdFormsService.getSS_TYPE_ANS(alias)));
		ais_ld.setModel(new ListModelList(BprLdFormsService.getSS_TYPE_ANS(alias)));
		ascoring.setModel(new ListModelList(BprLdFormsService.getSS_TYPE_ANS(alias)));
		scoring.setModel(new ListModelList(BprLdFormsService.getSS_TYPE_ANS(alias)));
		fscoring.setModel(new ListModelList(BprLdFormsService.getSS_TYPE_ANS(alias)));
	}

	private void refreshModel(int activePage) {
		try {
			bprldformsPaging.setPageSize(_pageSize);
			model = new PagingListModel(activePage, _pageSize, filter, alias);

			if (_needsTotalSizeUpdate) {
				_totalSize = model.getTotalSize();
				_needsTotalSizeUpdate = false;
			}

			bprldformsPaging.setTotalSize(_totalSize);

			dataGrid.setModel((ListModel) model);
			if (model.getSize() > 0) {
				this.current = (BprLdForms) model.getElementAt(0);
				sendSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	// Omitted...
	public BprLdForms getCurrent() {
		return current;
	}

	public void setCurrent(BprLdForms current) {
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
		btn_save.setDisabled(false);
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

		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
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
			Res res = new Res();
			if (addgrd.isVisible()) {
				current = new BprLdForms();
				current.setBpr_id(template);
				current.setIs_ld(ais_ld.getValue());
				current.setScoring(ascoring.getValue());
				BprLdFormsService.create(current, alias,res);
				if(res.getCode()!=1){
					Utils.clearForm(addgrd);
					frmgrd.setVisible(true);
					addgrd.setVisible(false);
					fgrd.setVisible(false);
				} else {
					alert(res.getName());
				}
			} else if (fgrd.isVisible()) {
				filter = new BprLdFormsFilter();
			} else {
				current.setBpr_id(template);
				current.setIs_ld(is_ld.getValue());
				current.setScoring(scoring.getValue());
				BprLdFormsService.update(current,res,alias);
			}
			if(res.getCode()!=1){
				onClick$btn_back();
				btn_save.setDisabled(true);
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
				alert("Данные успешно сохранены");
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new BprLdFormsFilter();
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
