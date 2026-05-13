package com.is.bpri.bproductDesc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

@SuppressWarnings("serial")
public class bproduct_descViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Textbox detail_group, detail_id;
	private Paging bproduct_descPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public bproduct_descFilter filter = new bproduct_descFilter();
	private String alias;
	private String template [];

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private List<bproduct_desc> parameters = new ArrayList<bproduct_desc>();

	private bproduct_desc current = new bproduct_desc();

	public bproduct_descViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();

			String[] parameter = (String[]) param.get("ht");
			if (parameter != null) {
				_pageSize = Integer.parseInt(parameter[0]) / 36;
				dataGrid.setRows(_pageSize);
			}

			parameter = (String[]) param.get("template");
			if (this.param.get("template") != null) {
				template = (String []) this.param.get("template");
			}
			filter.setId(template[0]);
			alias = (String) session.getAttribute("alias");
			dataGrid.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					bproduct_desc pbproduct_desc = (bproduct_desc) data;
					row.setValue(pbproduct_desc);

					row.appendChild(new Listcell(pbproduct_desc.getId() + ""));
					row.appendChild(new Listcell(pbproduct_desc.getDetail_group()
							+ ""));
					row.appendChild(new Listcell(pbproduct_desc.getDetail_id() + ""));
					row.appendChild(new Listcell(pbproduct_desc.getBranch()));
				}
			});

			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onPaging$bproduct_descPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		bproduct_descPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		bproduct_descPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (bproduct_desc) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public bproduct_desc getCurrent() {
		return current;
	}

	public void setCurrent(bproduct_desc current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		// addgrd.setVisible(false);
		// fgrd.setVisible(false);
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

		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	/*
	 * public void onClick$btn_add() { onDoubleClick$dataGrid$grd();
	 * frmgrd.setVisible(false); addgrd.setVisible(true);
	 * fgrd.setVisible(false); }
	 */

	/*
	 * public void onClick$btn_search() { onDoubleClick$dataGrid$grd();
	 * frmgrd.setVisible(false); //addgrd.setVisible(false);
	 * fgrd.setVisible(true); }
	 */

	public void onClick$btn_save() {
		try {
			if (frmgrd.isVisible() && current != null) {
				parameters = bproduct_descService.getBproductDescParams(
						current.getId(), current.getDetail_group());
				current.setId(current.getId());
				current.setDetail_group(current.getDetail_group());
				current.setDetail_id(!detail_id.getValue().equals("") ? detail_id.getValue() : "0");
				// current.setBranch(branch.getValue());
				if ((detail_group.getValue().equals("117") && parameters.get(0)
						.getDetail_id().equals("0"))
						|| (detail_group.getValue().equals("117") && parameters
								.get(0).getDetail_id() == null)) {
					bproduct_descService.update(current);
					alert("Данные успешно сохранены!");
				} else {
					alert("Сохранение невозможно!");
				}
			}
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,
					dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new bproduct_descFilter();
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
