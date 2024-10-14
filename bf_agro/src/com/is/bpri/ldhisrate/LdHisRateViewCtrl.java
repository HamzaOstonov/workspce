package com.is.bpri.ldhisrate;

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
import org.zkoss.zul.Doublebox;
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
import org.zkoss.zul.Hbox;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class LdHisRateViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Listbox dataGridRates,addDataGridRates;
	private Div grd;
	// private Vbox ;
	private Hbox addgrd, mainBox;
	private Grid addgrdl, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
	private Doublebox arate,frate,rate;
	private Textbox fexp_id, frate_type, frate_method, fpay_method,
			frate_id;
	private RefCBox exp_id, rate_type, rate_method, pay_method,
//	expId,
	rate_id;
	private RefCBox aexp_id, arate_type, arate_method, apay_method, 
//	aexpId,
			arate_id;
	private Paging ldhisratePaging;
	private Paging prsPaging;
	private Paging addPrsPaging;
	private int _pageSize = 15;
	private int _pageSizeprs = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public LdHisRateFilter filter = new LdHisRateFilter();
	public ParamsFilter prsfilter = new ParamsFilter();
	public LdHisRateAddFilter addRatefilter = new LdHisRateAddFilter();
	private String alias;
	private int template;
	static List<RefData> Exp_id_ = null;
	static List<RefData> Rate_type_ = null;
	static List<RefData> Rate_method_ = null;
	static List<RefData> Pay_method_ = null;
	static List<RefData> ExpId_ = null;
	static List<RefData> Rate_id_ = null;
	// private String exp_idTemp;

	LdHisRatePagingListModel model = null;
	PrsPagingListModel prsmodel = null;
	LdHisRateAddPagingListModel addPrsmodel = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	// SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private int id = -1;
	public LdHisRate current = new LdHisRate();
	public LdHisRate currentForUpdate = new LdHisRate();
	public Params currentRate = new Params();
	public LdHisRateAdd currentAddRate = new LdHisRateAdd();
	private int state = -1;

	public LdHisRateViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.bindBean("currentRate", this.currentRate);
			binder.loadAll();
			alias = (String) session.getAttribute("alias");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			filter.setBpr_id(template);
			prsfilter.setBpr_id(template);
			dataGrid.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					try {
						LdHisRate pLdHisRate = (LdHisRate) data;
						row.setValue(pLdHisRate);
						System.out.println("Render 1");
						row.appendChild(new Listcell(pLdHisRate.getExp_id() + ""));
						row.appendChild(new Listcell(pLdHisRate.getRate_type() + ""));
						row.appendChild(new Listcell(pLdHisRate.getRate_method() + ""));
						row.appendChild(new Listcell(pLdHisRate.getPay_method() + ""));
						row.appendChild(new Listcell(pLdHisRate.getRate_id() + ""));
						row.appendChild(new Listcell(pLdHisRate.getRate() + ""));
					} catch (Exception e) {
						e.printStackTrace();
						alert(CheckNull.getPstr(e));
					}
				}
			});

			dataGridRates.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					Params prs = (Params) data;
					row.setValue(prs);
					System.out.println("Render 2");
					row.appendChild(new Listcell(prs.getId_() + ""));
					row.appendChild(new Listcell(prs.getExp_id()));
					Listcell lc = new Listcell(prs.getExp_id_()+"");
					lc.setVisible(false);
					row.appendChild(lc);
					Listcell rate_type = new Listcell(prs.getRate_type()+"");
					rate_type.setVisible(false);
					row.appendChild(rate_type);
					Listcell rate_method = new Listcell(prs.getRate_method()+"");
					rate_method.setVisible(false);
					row.appendChild(rate_method);
					Listcell pay_method = new Listcell(prs.getPay_method()+"");
					pay_method.setVisible(false);
					row.appendChild(pay_method);
					Listcell expid = new Listcell(prs.getExpId()+"");
					expid.setVisible(false);
					row.appendChild(expid);
					Listcell rate_id = new Listcell(prs.getRate_id()+"");
					rate_id.setVisible(false);
					row.appendChild(rate_id);
					Listcell rate = new Listcell(prs.getRate()+"");
					rate.setVisible(false);
					row.appendChild(rate);
				}
			});
			addDataGridRates.setItemRenderer(new ListitemRenderer(){
				
				public void render(Listitem row, Object data) throws Exception {
							LdHisRateAdd prs = (LdHisRateAdd) data;
							row.setValue(prs);
							row.appendChild(new Listcell(prs.getId() + ""));
							row.appendChild(new Listcell(prs.getExp_id()));
							if(prs.getId()==11){
								row.setVisible(false);
							}
				}
			});
			rate_type.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getRate_type(alias)));
			exp_id.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getExp_id(alias)));
			rate_method.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getRate_method(alias)));
			pay_method.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getPay_method(alias)));
//			expId.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getExpId(alias)));
			rate_id.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getRate_id(alias)));
			aexp_id.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getExp_id(alias)));
			arate_type.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getRate_type(alias)));
			arate_method.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getRate_method(alias)));
			apay_method.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getPay_method(alias)));
//			aexpId.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getExpId(alias)));
			arate_id.setModel(new ListModelList(com.is.bpri.ldhisrate.LdHisRateService.getRate_id(alias)));
			refreshModel(_startPageNumber);
			refreshModelPrs(_startPageNumber);
			refreshModeladdPrs(_startPageNumber);
			if(this.param.get("state")!=null){
	        	state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
	        }
	        if(state==1||state==2){
	        	btn_save.setVisible(false);
	        	btn_del.setVisible(false);
	        	btn_add.setVisible(false);
	        } else if(state==0){
	        	btn_add.setVisible(true);
	        	btn_save.setVisible(true);
	        	btn_del.setVisible(true);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

	}
	
	public void onPaging$ldhisratePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	public void onPaging$prsPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModelPrs(_startPageNumber);
	}

	public void onPaging$addPrsPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModeladdPrs(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		try {
			ldhisratePaging.setPageSize(_pageSize);
			model = new LdHisRatePagingListModel(activePage, _pageSize, filter,
					alias);
			if (_needsTotalSizeUpdate) {
				_totalSize = model.getTotalSize();
				_needsTotalSizeUpdate = false;
			}
			ldhisratePaging.setTotalSize(_totalSize);
			dataGrid.setModel((ListModel) model);
			if (model.getSize() > 0) {
				this.current = (LdHisRate) model.getElementAt(0);
				sendSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refreshModelPrs(int activePage) {
		try {
			prsPaging.setPageSize(_pageSizeprs);
			prsmodel = new PrsPagingListModel(activePage, _pageSizeprs, prsfilter,alias);
			_totalSize = prsmodel.getTotalSize();
			prsPaging.setTotalSize(_totalSize);
			dataGridRates.setModel((ListModel) prsmodel);
			if (prsmodel.getSize() > 0) {
				this.currentRate = (Params) prsmodel.getElementAt(0);
				sendPrsSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	private void refreshModeladdPrs(int activePage) {
		try {
			addPrsPaging.setPageSize(_pageSizeprs);
			addPrsmodel = new LdHisRateAddPagingListModel(activePage, _pageSizeprs,
					addRatefilter, alias);

			_totalSize = addPrsmodel.getTotalSize(null, alias);

			addPrsPaging.setTotalSize(_totalSize);

			addDataGridRates.setModel((ListModel) addPrsmodel);
			if (addPrsmodel.getSize() > 0) {
				this.currentAddRate = (LdHisRateAdd) addPrsmodel.getElementAt(0);
				sendAddSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	// Omitted...
	public LdHisRate getCurrent() {
		return current;
	}

	public LdHisRate getCurrentForUpdate() {
		currentForUpdate = getCurrent();
		return currentForUpdate;
	}

	public void setCurrent(LdHisRate current) {
		this.current = current;
	}

	public Params getCurrentRates() {
		return currentRate;
	}

	public void setCurrentRates(Params currentRate) {
		this.currentRate = currentRate;
	}
	
	// exp_id, rate_type, rate_method, pay_method, expId, rate_id;
	
	public void onSelect$exp_id(){
		System.out.println("exp_id = "+exp_id.getValue());
	}
	
	public void onSelect$rate_type(){
		System.out.println("rate_type = "+rate_type.getValue());
	}
	
	public void onSelect$rate_method(){
		System.out.println("rate_method = "+rate_method.getValue());
	}
	
	public void onSelect$pay_method(){
		System.out.println("pay_method = "+pay_method.getValue());
	}
	
	public void onSelect$rate_id(){
		System.out.println("rate_id = "+rate_id.getValue());
	}

	public void onDoubleClick$dataGrid$grd() {
		try {
			grd.setVisible(false);
			frm.setVisible(true);
			mainBox.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			refreshModelPrs(_startPageNumber);
			currentForUpdate = getCurrent();
			btn_save.setDisabled(false);
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onSelect$addDataGridRates(Event evt) {
		Listitem listitem = null;
		List<Listcell> list = null;
		if(addDataGridRates.getModel()!=null&&addDataGridRates.getSelectedItem()!=null){
				listitem = addDataGridRates.getSelectedItem();
				list = listitem.getChildren();
				aexp_id.setSelecteditem(list.get(0).getLabel());
//				aexpId.setSelecteditem(list.get(0).getLabel());
		}
		getCurrentForUpdate();
		if (arate_type.getModel() != null && arate_type.getModel().getSize() > 0) {
			arate_type.setSelecteditem(((RefData) arate_type.getModel().getElementAt(0)).getData());
		}
		if (arate_method.getModel() != null	&& arate_method.getModel().getSize() > 0) {
			arate_method.setSelecteditem(((RefData) arate_method.getModel().getElementAt(0)).getData());
		}
		if (apay_method.getModel() != null && apay_method.getModel().getSize() > 0) {
			apay_method.setSelecteditem(((RefData) apay_method.getModel().getElementAt(0)).getData());
		}
		if (arate_id.getModel() != null && arate_id.getModel().getSize() > 0) {
			arate_id.setSelecteditem(((RefData) arate_id.getModel().getElementAt(0)).getData());
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
			alert(CheckNull.getPstr(e));
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

		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
				dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	private void sendPrsSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", dataGridRates,
				dataGridRates.getSelectedItems());
		Events.sendEvent(evt);
	}

	private void sendAddSelEvt() {
		SelectEvent evt = new SelectEvent("onSelect", addDataGridRates,
				addDataGridRates.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		try {
			onDoubleClick$dataGrid$grd();
			mainBox.setVisible(false);
			addgrd.setVisible(true);
			fgrd.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		mainBox.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
		// .setVisible(false);
	}

	public void onClick$btn_del(){
		Res res = new Res();
		try {
			if(id==-1){
				id = current.getId();
			}
			LdHisRateService.remove(id, alias, res);
			if(res.getCode()!=1){
				alert("Данные успешно удалены");
				onClick$btn_back();
				refreshModel(_startPageNumber);
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
				id = -1;
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onSelect$dataGridRates(){
		Listitem li = dataGridRates.getSelectedItem();
		if(li!=null){
			List<Listcell> lc = li.getChildren();
			id = Integer.parseInt(lc.get(0).getLabel());
		}
	}

	public void onClick$btn_save() {
		Res res = new Res();
		try {
			if (addgrd.isVisible()) {
				current = new LdHisRate();
				current.setBpr_id(template);
				current.setExp_id(aexp_id.getValue());
				current.setRate_type(arate_type.getValue());
				current.setRate_method(arate_method.getValue());
				current.setPay_method(apay_method.getValue());
				current.setBprId(template);
//				current.setExpId(aexpId.getValue());
				current.setRate_id(arate_id.getValue());
				if(arate.getValue()!=null){
					current.setRate(arate.getValue());
				}
				LdHisRateService.create(current,alias,res);
			} else if (fgrd.isVisible()) {
				filter = new LdHisRateFilter();

				if (CheckNull.isEmpty(exp_id.getValue())) {
					filter.setExp_id(!fexp_id.getValue().equals("") ? Integer.parseInt(fexp_id.getValue()) : 0);
				}
				if (CheckNull.isEmpty(rate_type.getValue())) {
					filter.setRate_type(!frate_type.getValue().equals("") ? Integer.parseInt(frate_type.getValue()) : 0);
				}
				if (CheckNull.isEmpty(rate_method.getValue())) {
					filter.setRate_method(!frate_method.getValue().equals("") ? Integer.parseInt(frate_method.getValue()) : 0);
				}
				if (CheckNull.isEmpty(pay_method.getValue())) {
					filter.setPay_method(!fpay_method.getValue().equals("") ? Integer.parseInt(fpay_method.getValue()) : 0);
				}
				if (CheckNull.isEmpty(rate_id.getValue())) {
					filter.setRate_id(!frate_id.getValue().equals("") ? Integer.parseInt(frate_id.getValue()) : 0);
				}
				if (CheckNull.isEmpty(rate.getValue())) {
					filter.setRate(frate.getValue());
				}
			} else {
				if (currentForUpdate == null) {
					refreshModel(_startPageNumber);
				}
				if(rate==null){
					System.out.println("RATE NULL");
				}
				System.out.println("ID "+currentForUpdate.getId());
				currentForUpdate.setId(currentForUpdate.getId());
				currentForUpdate.setBpr_id(template);
				currentForUpdate.setExp_id(exp_id.getValue()); 
				currentForUpdate.setRate_type(rate_type.getValue());
				currentForUpdate.setRate_method(rate_method.getValue());
				currentForUpdate.setPay_method(pay_method.getValue());
				currentForUpdate.setId(currentForUpdate.getId());
				currentForUpdate.setBprId(template);
//				currentForUpdate.setExpId(expId.getValue());
				currentForUpdate.setRate_id(rate_id.getValue());
				currentForUpdate.setRate(rate.getValue());
				LdHisRateService.update(currentForUpdate,alias,res);
			}
			if(res.getCode()!=1){
				Utils.clearForm(addgrdl);
				mainBox.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
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
			filter = new LdHisRateFilter();
		}
		onClick$btn_back();
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		Utils.clearForm(addgrdl);
		Utils.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
}
