package com.is.bpri;

//import java.text.SimpleDateFormat;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.RefCBox;
import org.zkoss.zul.Hbox;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class LdGuarrViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Row klassrow,currow;
	private Hbox  frmgrd;
	private Grid addgrdl, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_del,btn_add;
	private Toolbar savetb;
	private RefCBox currency, guar_id, klass_o,distr_id;
	private RefCBox acurrency, aklass_o;
	private Paging ldguarrPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private LdGuarrFilter filter = new LdGuarrFilter();
	private String branch;
	private String alias;
	private int template;
	private String currencyTemplate;
	private String clickbtn = "";
	private Include include = null;
	private int state = -1;

	private LdGuarrPagingListModel model = null;
	private AnnotateDataBinder binder;
//	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private LdGuarr current = new LdGuarr();
	

	public LdGuarrViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			branch = (String) session.getAttribute("branch");
			alias = (String) session.getAttribute("alias");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			if (this.param.get("state") != null) {
				state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
			}
			filter.setBpr_id(template);
			dataGrid.setItemRenderer(new ListitemRenderer() {
				public void render(Listitem row, Object data) throws Exception {
					try {
						LdGuarr pLdGuarr = (LdGuarr) data;
						row.setValue(pLdGuarr);
						row.appendChild(new Listcell(pLdGuarr.getBpr_id() + ""));
						row.appendChild(new Listcell(pLdGuarr.getId_client()));
						row.appendChild(new Listcell(pLdGuarr.getCurrency()));
						row.appendChild(new Listcell(pLdGuarr.getGuar_name()));
						row.appendChild(new Listcell(pLdGuarr.getKlass_name()));
						row.appendChild(new Listcell(pLdGuarr.getName()));
//						row.appendChild(new Listcell(pLdGuarr.getRegion_nam()));
//						row.appendChild(new Listcell(pLdGuarr.getDistr_name()));
//						row.appendChild(new Listcell(pLdGuarr.getMassiv()));
//						row.appendChild(new Listcell(pLdGuarr.getStreet()));
//						row.appendChild(new Listcell(pLdGuarr.getHome()));
//						row.appendChild(new Listcell(pLdGuarr.getHome_num()));
//						row.appendChild(new Listcell(pLdGuarr.getAmount()+""));
//						row.appendChild(new Listcell(pLdGuarr.getDoc_num()));
//						row.appendChild(new Listcell(pLdGuarr.getDoc_date()==null?"":df.format(pLdGuarr.getDoc_date())));
//						row.appendChild(new Listcell(pLdGuarr.getCl_name()));
//						row.appendChild(new Listcell(pLdGuarr.getInn()));
//						row.appendChild(new Listcell(pLdGuarr.getInn_reestr()));
//						row.appendChild(new Listcell(pLdGuarr.getMfo()));
//						row.appendChild(new Listcell(pLdGuarr.getAccount()));
//						row.appendChild(new Listcell(pLdGuarr.getEnd_date()==null?"":df.format(pLdGuarr.getEnd_date())));
//						row.appendChild(new Listcell(pLdGuarr.getName2()));
//						row.appendChild(new Listcell(pLdGuarr.getType_rez()));
//						row.appendChild(new Listcell(pLdGuarr.getNiki_gr_branch()));
//						row.appendChild(new Listcell(pLdGuarr.getName_k2()));
//						row.appendChild(new Listcell(pLdGuarr.getNiki_soogun()));
//						row.appendChild(new Listcell(pLdGuarr.getAcomp_name()));
//						row.appendChild(new Listcell(pLdGuarr.getAcomp_date()==null?"":df.format(pLdGuarr.getAcomp_date())));
//						row.appendChild(new Listcell(pLdGuarr.getAcomp_curr()));
//						row.appendChild(new Listcell(pLdGuarr.getAcomp_summa()+""));
//						row.appendChild(new Listcell(pLdGuarr.getNotarial_doc_numb()));
//						row.appendChild(new Listcell(pLdGuarr.getStart_date()==null?"":df.format(pLdGuarr.getStart_date())));
//						row.appendChild(new Listcell(pLdGuarr.getInsc_inn()));
//						row.appendChild(new Listcell(pLdGuarr.getInsc_num()));
//						row.appendChild(new Listcell(pLdGuarr.getInsc_date()==null?"":df.format(pLdGuarr.getInsc_date())));
//						row.appendChild(new Listcell(pLdGuarr.getPolis_num()));
//						row.appendChild(new Listcell(pLdGuarr.getPolis_date()==null?"":df.format(pLdGuarr.getPolis_date())));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			refCboxsetModels();
			acurrency.setSelecteditem(currencyTemplate);
			refreshModel(_startPageNumber);
			currencyTemplate = (String) session.getAttribute("currencyTemplate");
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
			ISLogger.getLogger().error("LdGuarrViewCtrl.doAfterCompose ошибка");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
			e.printStackTrace();
		}
	}

	public void onPaging$ldguarrPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refCboxsetModels(){
		guar_id.setModel(new ListModelList(com.is.bpri.LdGuarrService.getGuar_id(alias)));
	}
	
	private void refreshModel(int activePage) {
		ldguarrPaging.setPageSize(_pageSize);
		model = new LdGuarrPagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize(filter,alias);
		ldguarrPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			current = (LdGuarr) model.getElementAt(0);
			sendSelEvt();
		}
	}
	
	// Omitted...
	public LdGuarr getCurrent() {
		return current;
	}

	public void setCurrent(LdGuarr current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		try {
			addgrdl.setVisible(true);
			guar_id.setSelecteditem(current.getGuar_id());
			include.setSrc(null);
			onSelect$guar_id();
			btnclickdouble();
			clickbtn = "double";
			sessionSetAttributs();
		} catch (Exception e) {
			ISLogger.getLogger().error("LdGuarrViewCtrl.onDoubleClick$dataGrid$grd ошибка");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
			e.printStackTrace();
		}
	}
	
	private void sessionSetAttributs(){
		session.setAttribute("clickbtn", clickbtn);
		session.setAttribute("current", current);
		session.setAttribute("addgrdl", addgrdl);
		session.setAttribute("frm", frm);
		session.setAttribute("grd", grd);
		session.setAttribute("btn_back", btn_back);
		session.setAttribute("ldguarrPaging", ldguarrPaging);
		session.setAttribute("_pageSize", _pageSize);
		session.setAttribute("filter", filter);
		session.setAttribute("_totalSize", _totalSize);
		session.setAttribute("dataGrid", dataGrid);
	}
	
	public void onInitRenderLater$distr_id(){
		if(session.getAttribute("clickbtn").equals("double")){
			distr_id.setSelecteditem(current.getDistr_id());
		}
	}
	
	public void onInitRenderLater$aklass_o(){
		if(session.getAttribute("clickbtn").equals("double")){
			aklass_o.setSelecteditem(current.getKlass_o());
		}
	}
	
	public void onInitRenderLater$acurrency(){
		if(session.getAttribute("clickbtn").equals("double")){
			acurrency.setSelecteditem(current.getCurrency());
		}
	}
	
	private void btnclickdouble(){
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(false);
		addgrdl.setVisible(true);
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

		SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		try {
			include.setSrc(null);
			clickbtn = "add";
			session.setAttribute("clickbtn", clickbtn);
			currow.setVisible(false);
			klassrow.setVisible(false);
			Utils.clearForm(addgrdl);
			frmgrd.setVisible(false);
			addgrdl.setVisible(true);
			guar_id.setSelectedIndex(-1);
			savetb.setVisible(false);
			onSelect$guar_id();
			btnclickdouble();
		} catch (Exception e) {
			ISLogger.getLogger().error("LdGuarrViewCtrl.onClick$btn_add ошибка");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
			e.printStackTrace();
		}
	}

	public void onClick$btn_search() {
		btnclickdouble();
		frmgrd.setVisible(false);
		addgrdl.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public  Res savingProcess(LdGuarr current){
		Res res = new Res();
		if(res.getCode()!=1){
			try {
				Messagebox.show("Данные успешно сохраненны");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Messagebox.show(res.getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public void onClick$btn_save() {
		try {
			Res res = new Res();
				if(current==null){
					current = new LdGuarr();
				}
				current.setBpr_id(template);
				current.setGuar_id(guar_id.getValue());
				current.setKlass_o(klassrow.isVisible()?aklass_o.getValue():klass_o.getValue());
				current.setCurrency(currow.isVisible()?acurrency.getValue():currency.getValue());
				if(clickbtn.equals("add")){
					LdGuarrService.create(current,res,alias);
				} else if (clickbtn.equals("double")){
					LdGuarrService.update(current,res,alias);
				}
				Utils.clearForm(addgrdl);
				btn_save.setDisabled(true);
				afterSavengProcess(res);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("LdGuarrViewCtrl.onClick$btn_save ошибка");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onClick$btn_del(){
		try {
			Res res = new Res();
			LdGuarrService.remove(current,res,alias);
			afterSavengProcess(res);
		} catch (Exception e) {
			e.printStackTrace();
			alert(CheckNull.getPstr(e));
		}
	}
	
	public void afterSavengProcess(Res res){
		if(res.getCode()!=1){
			alert("Данные успешно сохранены");
			frmgrd.setVisible(true);
			addgrdl.setVisible(false);
//			fgrd.setVisible(false);
			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} else {
			alert(res.getName());
			return;
		}
	}
	
	public void onSelect$guar_id(){
		try {
			clickbtn = "add";
			session.setAttribute("clickbtn", clickbtn);
			sessionSetAttributs();
			clickbtn = "add";
			if(guar_id.getValue().equals("21")||guar_id.getValue().equals("22")||guar_id.getValue().equals("73")||guar_id.getValue().equals("75")||guar_id.getValue().equals("74")){
				includeLoad("realestate.zul");
			} else if(guar_id.getValue().equals("23")){
				includeLoad("car.zul");
			} else if(guar_id.getValue().equals("24")){
				includeLoad("equipment.zul");
			} else if(guar_id.getValue().equals("25")){
				includeLoad("deposits.zul");
			} else if(guar_id.getValue().equals("26")){
				includeLoad("inventories.zul");
			} else if(guar_id.getValue().equals("27")){
				includeLoad("products.zul");
			} else if(guar_id.getValue().equals("28")||guar_id.getValue().equals("29")||guar_id.getValue().equals("31")||guar_id.getValue().equals("36")){
				includeLoad("statesecurities.zul");
			} else if(guar_id.getValue().equals("32")||guar_id.getValue().equals("33")){
				includeLoad("futureharvest.zul");
			} else if(guar_id.getValue().equals("34")){
				includeLoad("certificatesofdeposit.zul");
			} else if(guar_id.getValue().equals("35")||guar_id.getValue().equals("38")){
				includeLoad("nonpayacc.zul");
			} else if(guar_id.getValue().equals("37")){
				includeLoad("cattle.zul");
			} else if(guar_id.getValue().equals("39")){
				includeLoad("jewerly.zul");
			} else if(guar_id.getValue().equals("43")||guar_id.getValue().equals("41")||guar_id.getValue().equals("42")){
				if(currencyTemplate!=null){
//					currency.setSelecteditem(currencyTemplate);
				}
				includeLoad("garant.zul");
			} else if(guar_id.getValue().equals("51")||guar_id.getValue().equals("72")){
				includeLoad("spolis.zul");
			} else if(guar_id.getSelectedIndex()==-1){
				klassrow.setVisible(false);
				currow.setVisible(false);
				include.setVisible(false);
				include.setSrc(null);
				savetb.setVisible(true);
			} else {
				savetb.setVisible(true);
				include.setSrc(null);
				include.setVisible(false);
				acurrency.setModel(new ListModelList(com.is.bpri.LdGuarrService.getCurrency(alias)));
				aklass_o.setModel(new ListModelList(com.is.bpri.LdGuarrService.getKlass_o(alias)));
				klassrow.setVisible(true);
				currow.setVisible(true);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error("LdGuarrViewCtrl.onSelect$guar_id ошибка");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}
	
	private void includeLoad(String zul){
		savetb.setVisible(false);
		klassrow.setVisible(false);
		currow.setVisible(false);
		include.setSrc(null);
		include.setVisible(true);
		session.setAttribute("current", current);
		include.setSrc("/bpr/Grids/"+zul+"?branch="+branch+"&alias="+alias+"&guar_id="+guar_id.getValue()+"&bprid="+template+"&state="+state);
	}

	public void onClick$btn_cancel() {
		try {
//			if (fgrd.isVisible()) {
				filter = new LdGuarrFilter();
				filter.setBpr_id(template);
//			}
			onClick$btn_back();
			frmgrd.setVisible(true);
			addgrdl.setVisible(false);
//			fgrd.setVisible(false);
			Utils.clearForm(addgrdl);
			Utils.clearForm(fgrd);
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
