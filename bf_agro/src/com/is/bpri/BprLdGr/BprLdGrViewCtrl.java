package com.is.bpri.BprLdGr;

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
import org.zkoss.zul.Hbox;
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
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class BprLdGrViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid, dataGridGrType, addGrType;
	private Div grd;
	private Grid addgrdl, fgrdl, addgrdl1, addgrdl2, addgrdl3;
	private Hbox addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
	private Textbox day, num;
	private Textbox anum, aday;
	private Textbox foper_id, fexp_id, fgraf_method, fnum,
			fpay_period, fday, fdate_from, fdate_to;
	private RefCBox graf_method, pay_period, apay_period, agraf_method,
			adate_from, adate_to, exp_id, oper_id, aoper_id;
	private Paging bprldgrPaging;
	private Paging grTypePaging;
	private Paging addgrTypePaging;
	private int _pageSize = 15;
	private int _pageSizeGrType = 20;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public BprLdGrFilter filter = new BprLdGrFilter();
	public BprGrTypeFilter grTypeFilter = new BprGrTypeFilter();
	public BprGrTypeFilterAdd addgrTypeFilter = new BprGrTypeFilterAdd();
	private String alias;
	private int template;
	private int id = -1;
	private int state = -1;

	private RefCBox date_from, date_to;

	BprLdGrPagingListModel model = null;
	BprGrTypePagingListModel grTypemodel = null;
	PagingListModel addgrTypemodel = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	public BprLdGr current = new BprLdGr();
	public BprGrType currentGrType = new BprGrType();

	public BprGrTypeAdd currentGrTypeAdd = new BprGrTypeAdd();

	public BprLdGrViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.bindBean("currentGrType", this.currentGrType);
			binder.loadAll();
			alias = (String) session.getAttribute("alias");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			if (this.param.get("state") != null) {
				state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
			}
			filter.setBpr_id(template);
			grTypeFilter.setBpr_id(template);
			dataGrid.setItemRenderer(new ListitemRenderer() {
				
				public void render(Listitem row, Object data) throws Exception {
					BprLdGr pBprLdGr = (BprLdGr) data;
					row.setValue(pBprLdGr);

					row.appendChild(new Listcell(pBprLdGr.getBpr_id()+""));
					row.appendChild(new Listcell(pBprLdGr.getOper_id()));
					row.appendChild(new Listcell(pBprLdGr.getExp_id()));
					row.appendChild(new Listcell(pBprLdGr.getGraf_method()));
					row.appendChild(new Listcell(pBprLdGr.getNum()));
					row.appendChild(new Listcell(pBprLdGr.getPay_period()));
					row.appendChild(new Listcell(pBprLdGr.getDay()));
					row.appendChild(new Listcell(pBprLdGr.getDate_from()));
					row.appendChild(new Listcell(pBprLdGr.getDate_to()));
				}
			});

			dataGridGrType.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					BprGrType pBprGrType = (BprGrType) data;
					row.setValue(pBprGrType);
					
					row.appendChild(new Listcell(pBprGrType.getBpr_id() + ""));
					row.appendChild(new Listcell(pBprGrType.getOper_id() + ""));
					row.appendChild(new Listcell(pBprGrType.getGr_type()));
					row.appendChild(new Listcell(pBprGrType.getExp_id() + ""));
					row.appendChild(new Listcell(pBprGrType.getGraf_method() + ""));
					row.appendChild(new Listcell(pBprGrType.getNum() + ""));
					row.appendChild(new Listcell(pBprGrType.getPay_period() + ""));
					row.appendChild(new Listcell(pBprGrType.getDay() + ""));
					row.appendChild(new Listcell(pBprGrType.getDate_from() + ""));
					row.appendChild(new Listcell(pBprGrType.getDate_to() + ""));
					row.appendChild(new Listcell(pBprGrType.getId()+""));
				}
			});

			addGrType.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					BprGrTypeAdd pBprGrTypeAdd = (BprGrTypeAdd) data;
					row.setValue(pBprGrTypeAdd);

					row.appendChild(new Listcell(pBprGrTypeAdd.getOPerId() + ""));
					row.appendChild(new Listcell(pBprGrTypeAdd.getExpId() + ""));
					row.appendChild(new Listcell(pBprGrTypeAdd.getGr_type()));

				}
			});

			oper_id.setModel((new ListModelList(BprLdGrService.getOperId(alias))));
			graf_method.setModel((new ListModelList(BprLdGrService.getGrafMethod(alias))));
			pay_period.setModel((new ListModelList(BprLdGrService.getPayPeriod(alias))));
			date_from.setModel((new ListModelList(BprLdGrService.getDateType(alias))));
			date_to.setModel((new ListModelList(BprLdGrService.getDateType(alias))));
			aoper_id.setModel((new ListModelList(BprLdGrService.getOperId(alias))));
			agraf_method.setModel((new ListModelList(BprLdGrService.getGrafMethod(alias))));
			apay_period.setModel((new ListModelList(BprLdGrService.getPayPeriod(alias))));
			adate_from.setModel((new ListModelList(BprLdGrService.getDateType(alias))));
			adate_to.setModel((new ListModelList(BprLdGrService.getDateType(alias))));
			refreshModel(_startPageNumber);
			refreshGrTypeModel(_startPageNumber);
			refreshaddGrTypeModel(_startPageNumber);
			adate_to.setDisabled(true);
			date_to.setDisabled(true);
			if(state==1||state==2){
				btn_save.setVisible(false);
				btn_add.setVisible(false);
				btn_del.setVisible(false);
			} else if(state==0){
				btn_add.setVisible(true);
				btn_save.setVisible(true);
				btn_del.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onInitRenderLater$date_to(){
		date_to.setSelecteditem("2");
	}
	
	public void onInitRenderLater$adate_to(){
		adate_to.setSelecteditem("2");
	}
	
	public void onPaging$bprldgrPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	public void onPaging$grTypePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshGrTypeModel(_startPageNumber);
	}

	public void onPaging$addgrTypePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		// refreshaddGrTypeModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		bprldgrPaging.setPageSize(_pageSize);
		model = new BprLdGrPagingListModel(activePage, _pageSize, filter, alias);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		bprldgrPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (BprLdGr) model.getElementAt(0);
			// dataGrid.setSelectedIndex(0);
			sendSelEvt();
		}
	}

	private void refreshGrTypeModel(int activePage) {
		grTypePaging.setPageSize(_pageSizeGrType);
		grTypemodel = new BprGrTypePagingListModel(activePage, _pageSizeGrType,
				grTypeFilter, alias);

		_totalSize = grTypemodel.getTotalSize();

		grTypePaging.setTotalSize(_totalSize);

		dataGridGrType.setModel((ListModel) grTypemodel);
		if (grTypemodel.getSize() > 0) {
			this.currentGrType = (BprGrType) grTypemodel.getElementAt(0);
			// dataGridGrType.setSelectedIndex(0);
			sendGrTypeSelEvt();
		}
	}

	private void refreshaddGrTypeModel(int activePage) {
		addgrTypePaging.setPageSize(_pageSizeGrType);
		addgrTypemodel = new PagingListModel(activePage, _pageSizeGrType,
				addgrTypeFilter, alias);

		_totalSize = addgrTypemodel.getTotalSize();

		addgrTypePaging.setTotalSize(_totalSize);

		addGrType.setModel((ListModel) addgrTypemodel);
		if (addgrTypemodel.getSize() > 0) {
			// this.current =(BprLdGr) model.getElementAt(0);
			sendaddGrTypeSelEvt();
		}
	}

	// Omitted...
	public BprLdGr getCurrent() {
		return current;
	}

	public void setCurrent(BprLdGr current) {
		this.current = current;
	}

	public BprGrType getCurrentGrType() {
		return currentGrType;
	}

	public void setCurrentGrType(BprGrType currentGrType) {
		this.currentGrType = currentGrType;
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
		refreshGrTypeModel(_startPageNumber);
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

	private void sendGrTypeSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", dataGridGrType,
				dataGridGrType.getSelectedItems());
		Events.sendEvent(evt);
	}

	private void sendaddGrTypeSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", addGrType,
				addGrType.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		// CheckNull.clearForm(addgrd);
		Utils.clearForm(addgrdl);
		Utils.clearForm(addgrdl1);
		Utils.clearForm(addgrdl2);
		Utils.clearForm(addgrdl3);
		fgrd.setVisible(false);
		adate_to.setSelecteditem("2");
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		int exp_id_param = 0;
		try {
			if (addgrd.isVisible()) {
				
				if(!aoper_id.getValue().equals("")){
					if (aoper_id.getValue().equals("1")||aoper_id.getValue().equals("2")) {
						exp_id_param = 0;
					} else if (aoper_id.getValue().equals("4")) {
						exp_id_param = 1;
					}
				} else {
					alert("Выберите Вид графика");
					return;
				}
				if(adate_to.getValue()==null||adate_to.getValue().equals("")
						||adate_from.getValue()==null||adate_from.getValue().equals("")
						||aoper_id.getValue()==null||aoper_id.getValue().equals("")
						||agraf_method.getValue()==null||agraf_method.getValue().equals("")){
					alert("Не заполнены все обязательные поля");
					return;
				}
				current = new BprLdGr();
				current.setBpr_id(template);
				current.setDate_from(adate_from.getValue());
				current.setDate_to(adate_to.getValue());
				current.setDay(aday.getValue());
				current.setExp_id(exp_id_param+"");
				current.setGraf_method(agraf_method.getValue());
				current.setNum(anum.getValue());
				current.setOper_id(aoper_id.getValue());
				current.setPay_period(apay_period.getValue());
				BprLdGrService.create(current,alias);
				Utils.clearForm(addgrdl);
				Utils.clearForm(addgrdl1);
				Utils.clearForm(addgrdl2);
				Utils.clearForm(addgrdl3);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new BprLdGrFilter();

				if (CheckNull.isEmpty(oper_id.getValue())) {
					filter.setOper_id(!foper_id.getValue().equals("") ? Integer
							.parseInt(foper_id.getValue()) : 0);
				}
				if (CheckNull.isEmpty(exp_id.getValue())) {
					filter.setExp_id(!fexp_id.getValue().equals("") ? Integer
							.parseInt(fexp_id.getValue()) : 0);
				}
				if (CheckNull.isEmpty(graf_method.getValue())) {
					filter.setGraf_method(!fgraf_method.getValue().equals("") ? Integer
							.parseInt(fgraf_method.getValue()) : 0);
				}
				if (CheckNull.isEmpty(num.getValue())) {
					filter.setNum(!fnum.getValue().equals("") ? Integer
							.parseInt(fnum.getValue()) : 0);
				}
				if (CheckNull.isEmpty(pay_period.getValue())) {
					filter.setPay_period(!fpay_period.getValue().equals("") ? Integer
							.parseInt(fpay_period.getValue()) : 0);
				}
				if (CheckNull.isEmpty(day.getValue())) {
					filter.setDay(!fday.getValue().equals("") ? Integer
							.parseInt(fday.getValue()) : 0);
				}
				if (CheckNull.isEmpty(date_from.getValue())) {
					filter.setDate_from(!fdate_from.getValue().equals("") ? Integer
							.parseInt(fdate_from.getValue()) : 0);
				}
				if (CheckNull.isEmpty(date_to.getValue())) {
					filter.setDate_to(!fdate_to.getValue().equals("") ? Integer
							.parseInt(fdate_to.getValue()) : 0);
				}

			} else {
				current.setBpr_id(template);
				current.setDate_from(date_from.getValue());
				current.setDate_to(date_to.getValue());
				current.setDay(day.getValue());
				current.setExp_id(current.getExp_id());
				current.setGraf_method(graf_method.getValue());
				current.setId(current.getId());
				current.setNum(num.getValue());
				current.setOper_id(oper_id.getValue());
				current.setPay_period(pay_period.getValue());
				BprLdGrService.updateLdGr(current, alias);
			}
			alert("Данные успешно сохранены");
			onClick$btn_back();
			btn_save.setDisabled(true);
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_del(){
		Res res = new Res();
		try {
			if(id==-1){
				id = current.getId();
			}
			System.out.println("id "+id);
			BprLdGrService.remove(id, alias, res);
			if(res.getCode()!=1){
				alert("Данные успешно удалены");
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new BprLdGrFilter();
		}

		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		Utils.clearForm(addgrdl);
		Utils.clearForm(addgrdl1);
		Utils.clearForm(addgrdl2);
		Utils.clearForm(addgrdl3);
		Utils.clearForm(fgrdl);
		refreshModel(_startPageNumber);
	}

	@SuppressWarnings("unchecked")
	public void onSelect$dataGridGrType(){
		Listitem li = dataGridGrType.getSelectedItem();
		if(li!=null){
			List<Listcell> lc = li.getChildren();
			id = Integer.parseInt(lc.get(lc.size()-1).getLabel());
			System.out.println("ID "+id);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onSelect$addGrType() {
		Listitem listitem = null;
		List<Listcell> list = null;
		if(addGrType.getModel()!=null&&addGrType.getSelectedItem()!=null){
				listitem = addGrType.getSelectedItem();
				list = listitem.getChildren();
				aoper_id.setSelecteditem(list.get(0).getLabel());
		}
	}
}
