package com.is.bpri;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
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
import org.zkoss.zul.Hbox;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.Res;
import com.is.utils.RefCBox;

@SuppressWarnings("serial")
public class NiReqViewCtrl extends GenericForwardComposer {
	
	private Div frm;
	private Listbox dataGrid;
	private Div grd;
	private Grid addgrdl, addgrdr,fgrdl, fgrdr;
	private Hbox addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_add;
	private Textbox qw;
	private Textbox aqw;
	private RefCBox type_zm, reqtype, branch_id, shifr_id, kred_id,
			resolve_org, currency, nwp, etype, is_letter, code;
	private RefCBox atype_zm, areqtype, abranch_id, ashifr_id, akred_id,
			aresolve_org, acurrency, anwp, aetype, ais_letter, acode;
	private Paging nireqPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	public NiReqFilter filter = new NiReqFilter();
	private String alias;
	private int template;
	private String currencyTemplate;
//	private String page;
	private NiReqPagingListModel model = null;
	private AnnotateDataBinder binder;
	private LdProduct ldProduct = null;
	private int state = -1;
	private String btn_click = "";
	
	private NiReq current = new NiReq();

	public NiReqViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			alias = (String) session.getAttribute("alias");
			if (this.param.get("template") != null) {
				template = Integer.parseInt(((String[]) this.param.get("template"))[0]);
			}
			filter.setBpr_id(template);
			dataGrid.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					NiReq pNiReq = (NiReq) data;
					row.setValue(pNiReq);
					row.appendChild(new Listcell(pNiReq.getType_zm()));
					row.appendChild(new Listcell(pNiReq.getReq_type() + ""));
					row.appendChild(new Listcell(pNiReq.getBranch_id()));
					row.appendChild(new Listcell(pNiReq.getShifr_id()));
					row.appendChild(new Listcell(pNiReq.getKred_id()));
					row.appendChild(new Listcell(pNiReq.getResolve_org()));
					row.appendChild(new Listcell(pNiReq.getCurrency()));
					row.appendChild(new Listcell(pNiReq.getNwp()));
					row.appendChild(new Listcell(pNiReq.getQw()));
					row.appendChild(new Listcell(pNiReq.getEtype()));
					row.appendChild(new Listcell(pNiReq.getIsLetter().trim()));
					row.appendChild(new Listcell(pNiReq.getCode()));
					row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
						
						@Override
						public void onEvent(Event event) throws Exception {
							Listitem item = (Listitem) event.getTarget();
							current = (NiReq) item.getValue();
							doubleClick();
						}
					});
				}
			});
			type_zm.setModel(new ListModelList(com.is.bpri.NiReqService.getTypeZm(alias,"")));
			reqtype.setModel(new ListModelList(com.is.bpri.NiReqService.getReqType(alias)));
			branch_id.setModel(new ListModelList(com.is.bpri.NiReqService.getBranchId(alias)));
			shifr_id.setModel(new ListModelList(com.is.bpri.NiReqService.getShifrId(alias)));
			kred_id.setModel(new ListModelList(com.is.bpri.NiReqService.getKredId(alias)));
			resolve_org.setModel(new ListModelList(com.is.bpri.NiReqService.getResolveOrg(alias)));
			currency.setModel(new ListModelList(com.is.bpri.NiReqService.getCurrency(alias)));
			nwp.setModel(new ListModelList(com.is.bpri.NiReqService.getNwp(alias)));
			etype.setModel(new ListModelList(com.is.bpri.NiReqService.getEtype(alias)));
			is_letter.setModel(new ListModelList(com.is.bpri.NiReqService.getIsLetter(alias)));
			code.setModel(new ListModelList(com.is.bpri.NiReqService.getCode(alias)));
			atype_zm.setModel(new ListModelList(com.is.bpri.NiReqService.getTypeZm(alias,"")));
			areqtype.setModel(new ListModelList(com.is.bpri.NiReqService.getReqType(alias)));
			abranch_id.setModel(new ListModelList(com.is.bpri.NiReqService.getBranchId(alias)));
			ashifr_id.setModel(new ListModelList(com.is.bpri.NiReqService.getShifrId(alias)));
			akred_id.setModel(new ListModelList(com.is.bpri.NiReqService.getKredId(alias)));
			aresolve_org.setModel(new ListModelList(com.is.bpri.NiReqService.getResolveOrg(alias)));
			acurrency.setValue(currencyTemplate);
			anwp.setModel(new ListModelList(com.is.bpri.NiReqService.getNwp(alias)));
			aetype.setModel(new ListModelList(com.is.bpri.NiReqService.getEtype(alias)));
			ais_letter.setModel(new ListModelList(com.is.bpri.NiReqService.getIsLetter(alias)));
			acode.setModel(new ListModelList(com.is.bpri.NiReqService.getCode(alias)));
			refreshModel(_startPageNumber);
			currencyTemplate = (String) session.getAttribute("currencyTemplate");
			if(this.param.get("state")!=null){
	        	state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
	        }
	        if(state==1||state==2){
	        	btn_add.setVisible(false);
	        	btn_save.setVisible(false);
	        } else if(state==0){
	        	btn_add.setVisible(true);
	        	btn_save.setVisible(true);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}

	public void onPaging$nireqPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		nireqPaging.setPageSize(_pageSize);
		model = new NiReqPagingListModel(activePage, _pageSize, filter, alias);
		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(filter, alias);
			_needsTotalSizeUpdate = false;
		}
		nireqPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
			this.current = (NiReq) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public NiReq getCurrent() {
		return current;
	}

	public void setCurrent(NiReq current) {
		this.current = current;
	}

	private void doubleClick(){
		try {
			if(!btn_click.equals("pre_add") && !btn_click.equals("pre_search")) btn_click = "double";
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			if(current.getIs_letter()!=null){
				int temp = selectedIndex(is_letter, current.getIs_letter().trim());
				is_letter.setSelectedIndex(temp);
			}
			btn_save.setDisabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			filter.setBpr_id(template);
		} else
			doubleClick();
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
		btn_click = "pre_add";
		doubleClick();
		frmgrd.setVisible(false);
		btn_click = "add";
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		btn_click = "pre_search";
		doubleClick();
		btn_click = "search";
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}
	
	public void onSelect$atype_zm(){
		try {
			String temp = atype_zm.getValue().substring(0, 2);
			if(temp.equals("08")){
				abranch_id.setSelecteditem("64920");
				anwp.setSelecteditem("2");
				aqw.setValue("0");
				aetype.setSelecteditem("0");
				ais_letter.setSelecteditem("0 ");
				ldProduct = new LdProduct();
				ldProduct.setProd_name("Физицеское лицо");
				ldProduct.setAcceptance("1");
				ldProduct.setCres("1");
				ldProduct.setCalc_id("2");
				ldProduct.setKlassp_id("1");
				ldProduct.setStatus("1");
				ldProduct.setKlass_id("1");
				ldProduct.setIs_tax("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int selectedIndex(RefCBox refcbox,String data){
		int temp = -1;
		refcbox.setSelectedIndex(temp);
		if(data!=null){
			for (int i = 0; i < refcbox.getItemCount(); i++) {
				String tempstr = (String) refcbox.getItemAtIndex(i).getValue();
				if(tempstr.trim().equals(data)){
					temp = i;
					break;
				}
			}
		}
		return temp;
	}

	public void onClick$btn_save() {
		Res res = new Res();
		try {
			if (btn_click.equals("add")) {
				ISLogger.getLogger().error("Вошли в addgrd");
				current = new NiReq();
				current.setBpr_id(template);
				current.setType_zm(atype_zm.getValue());
				current.setReq_type(areqtype.getValue());
				current.setBranch_id(abranch_id.getValue());
				current.setShifr_id(ashifr_id.getValue());
				current.setKred_id(akred_id.getValue());
				current.setResolve_org(aresolve_org.getValue());
				current.setCurrency(currencyTemplate);
				current.setNwp(anwp.getValue());
				current.setQw(aqw.getValue());
				current.setEtype(aetype.getValue());
				current.setIsLetter(ais_letter.getValue());
				current.setCode(acode.getValue());
				ISLogger.getLogger().error("ТУТ");
				LdProduct ldProduct = null;
				if(this.ldProduct!=null){
					ldProduct = this.ldProduct;
				} else {
					ldProduct = new LdProduct();
				}
				ISLogger.getLogger().error("ТУТ");
				ldProduct.setId(template);
				ldProduct.setCurrency(currencyTemplate);
				ldProduct.setShifr_id(ashifr_id.getValue());
				ldProduct.setKred_id_cb(akred_id.getValue());
				ldProduct.setUse_branch(atype_zm.getValue());
				ISLogger.getLogger().error("Перед Create");
				NiReqService.create(current,ldProduct,alias,res);
				ISLogger.getLogger().error("Create выполнен");
			} else if (btn_click.equals("double")) {
				ISLogger.getLogger().error("Вошли");
				current.setBpr_id(template);
				current.setType_zm(type_zm.getValue());
				current.setReq_type(reqtype.getValue());
				current.setBranch_id(branch_id.getValue());
				current.setShifr_id(shifr_id.getValue());
				current.setKred_id(kred_id.getValue());
				current.setResolve_org(resolve_org.getValue());
				current.setCurrency(currency.getValue());
				current.setNwp(nwp.getValue());
				current.setQw(qw.getValue());
				current.setEtype(etype.getValue());
				current.setIsLetter(is_letter.getValue().trim());
				current.setCode(code.getValue());
				ISLogger.getLogger().error("Перед Update");
				NiReqService.update(current,alias,res);
				ISLogger.getLogger().error("После Update");
			}
			if(res.getCode()!=1){
				ISLogger.getLogger().error("Без ошибок");
				alert("Данные успешно сохранены");
				onClick$btn_back();
				btn_save.setDisabled(true);
				refreshModel(_startPageNumber);
				ISLogger.getLogger().error("RefreshModel");
				SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
				Events.sendEvent(evt);
			} else {
				ISLogger.getLogger().error("Случилась ошибка");
				alert(res.getName());
				ISLogger.getLogger().error(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
			ISLogger.getLogger().error(res.getName());
		}
	}

	public void onClick$btn_cancel() {
		if (fgrdl.isVisible() && fgrdr.isVisible()) {
			filter = new NiReqFilter();
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		Utils.clearForm(addgrdl);
		Utils.clearForm(addgrdr);
		Utils.clearForm(fgrdl);
		Utils.clearForm(fgrdr);
		refreshModel(_startPageNumber);
	}

}
