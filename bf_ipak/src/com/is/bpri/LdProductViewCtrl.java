package com.is.bpri;

import java.math.BigDecimal;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Decimalbox;
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
public class LdProductViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Div grd;
	private Listbox dataGrid;
	private Hbox addgrd, frmgrd,fgrd;
	private Grid addgrdl, addgrdr, fgrdl, fgrdr;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back,btn_save,btn_add;
	private Textbox prod_name, target;
	private Decimalbox ald_amount,ld_amount;
	private Textbox  aprod_name, atarget;
	private RefCBox currency, shifr_id, sred_id, kred_id, term_type,
			klass_id, status, klassp_id, calc_id, kred_id_cb, acceptance, cres,
			is_tax;
	private RefCBox acurrency, ashifr_id, asred_id, akred_id, aterm_type,
			aklass_id, astatus, aklassp_id, acalc_id, akred_id_cb,
			aacceptance, acres, ais_tax, use_branch, ause_branch;
	private String currencyTemplate;
	private Paging ldproductPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;

	public LdProductFilter filter = new LdProductFilter();

	private LdPagingListModel model = null;
	private AnnotateDataBinder binder;
	// private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private String alias;
	private int template;
	private int state = -1;

	private LdProduct current = new LdProduct();

	public LdProductViewCtrl() {
		super('$', false, false);
	}

	/**
 *
 *
 */
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
			filter.setId(template);
			dataGrid.setItemRenderer(new ListitemRenderer() {

				public void render(Listitem row, Object data) throws Exception {
					LdProduct pLdProduct = (LdProduct) data;

					row.setValue(pLdProduct);

					row.appendChild(new Listcell(pLdProduct.getId() + ""));
					row.appendChild(new Listcell(pLdProduct.getCurrency().trim()));
					row.appendChild(new Listcell(pLdProduct.getShifr_id()));
					row.appendChild(new Listcell(pLdProduct.getProd_name()));
					row.appendChild(new Listcell(pLdProduct.getSred_id().trim()));
					row.appendChild(new Listcell(pLdProduct.getTarget().trim()));
					row.appendChild(new Listcell(pLdProduct.getCalc_id().trim()));
					row.appendChild(new Listcell(pLdProduct.getTerm_type().trim()));
					row.appendChild(new Listcell(pLdProduct.getKred_id().trim()));
					row.appendChild(new Listcell(pLdProduct.getKlass_id().trim()));
					row.appendChild(new Listcell(pLdProduct.getStatus().trim()));
					row.appendChild(new Listcell(pLdProduct.getKlassp_id().trim()));
					row.appendChild(new Listcell(pLdProduct.getKred_id_cb().trim()));
					row.appendChild(new Listcell(pLdProduct.getAcceptance().trim()));
					row.appendChild(new Listcell(pLdProduct.getCres().trim()));
					row.appendChild(new Listcell(pLdProduct.getIs_tax().trim()));
					row.appendChild(new Listcell(pLdProduct.getUse_branch().trim()));
					row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
						
						@Override
						public void onEvent(Event evt) throws Exception {
							onDoubleClick();
						}
					});
				}
			});

			status.setModel((new ListModelList(com.is.bpri.LdProductService
					.getStatus(alias))));
			currency.setModel((new ListModelList(com.is.bpri.LdProductService
					.getCurrency(alias))));
			shifr_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSshifr(alias))));
			sred_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSsred(alias))));
			kred_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSScredit(alias))));
			klass_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSklass(alias))));
			klassp_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSklassp(alias))));
			term_type.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSsrokkr(alias))));
			calc_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getCalcmetod(alias))));
			kred_id_cb.setModel(new ListModelList(com.is.bpri.LdProductService
					.getKred_id_cb(alias)));
			acceptance.setModel(new ListModelList(com.is.bpri.LdProductService
					.getAcceptance(alias)));
			cres.setModel(new ListModelList(com.is.bpri.LdProductService
					.getCres(alias)));
			is_tax.setModel(new ListModelList(com.is.bpri.LdProductService
					.getIs_tax(alias)));
			use_branch.setModel(new ListModelList(com.is.bpri.LdProductService
					.getUse_branch(alias)));

			astatus.setModel((new ListModelList(com.is.bpri.LdProductService
					.getStatus(alias))));
			acurrency.setValue(currencyTemplate);
			ashifr_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSshifr(alias))));
			asred_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSsred(alias))));
			akred_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSScredit(alias))));
			aklass_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSklass(alias))));
			aklassp_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSklassp(alias))));
			aterm_type.setModel((new ListModelList(com.is.bpri.LdProductService
					.getSsrokkr(alias))));
			acalc_id.setModel((new ListModelList(com.is.bpri.LdProductService
					.getCalcmetod(alias))));
			akred_id_cb.setModel(new ListModelList(com.is.bpri.LdProductService
					.getKred_id_cb(alias)));
			aacceptance.setModel(new ListModelList(com.is.bpri.LdProductService
					.getAcceptance(alias)));
			acres.setModel(new ListModelList(com.is.bpri.LdProductService
					.getCres(alias)));
			ais_tax.setModel(new ListModelList(com.is.bpri.LdProductService
					.getIs_tax(alias)));
			ause_branch.setModel(new ListModelList(com.is.bpri.LdProductService
					.getUse_branch(alias)));
			currencyTemplate = (String) session.getAttribute("currencyTemplate");
			if(this.param.get("state")!=null){
	        	state = Integer.parseInt(((String[]) this.param.get("state"))[0]);
	        }
	        if(state==1||state==2){
	        	btn_save.setVisible(false);
	        	btn_add.setVisible(false);
	        } else if(state==0){
	        	btn_add.setVisible(true);
	        	btn_save.setVisible(true);
	        }
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
			alert(e.getMessage());
			ISLogger.getLogger().error("LDPRODUCT doaftercompose");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

	}

	public void onPaging$ldproductPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		ldproductPaging.setPageSize(_pageSize);
		model = new LdPagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize(filter, alias);
		ldproductPaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			dataGrid.setSelectedIndex(0);
			sendSelEvt();
			if(state==0){
				btn_add.setVisible(false);
			}
		} else {
			if(state==0){
				btn_add.setVisible(true);
			}
		}
	}

	// Omitted...
	public LdProduct getCurrent() {
		return current;
	}

	public void setCurrent(LdProduct current) {
		this.current = current;
	}
	
	private void onDoubleClick(){
		try {
			System.out.println("Прииивет");
			grd.setVisible(false);
			frm.setVisible(true);
			frmgrd.setVisible(true);
			addgrd.setVisible(false);
			fgrd.setVisible(false);
			btn_back.setImage("/images/folder.png");
			btn_back.setLabel(Labels.getLabel("grid"));
			btn_save.setDisabled(false);
			if(current!=null){
				if(current.getLd_amount()!=null){
					ld_amount.setValue(new BigDecimal(current.getLd_amount()).divide(new BigDecimal(100)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void onDoubleClick$dataGrid() {
//		try {
//			System.out.println("Прииивет");
//			grd.setVisible(false);
//			frm.setVisible(true);
//			frmgrd.setVisible(true);
//			addgrd.setVisible(false);
//			fgrd.setVisible(false);
//			btn_back.setImage("/images/folder.png");
//			btn_back.setLabel(Labels.getLabel("grid"));
//			btn_save.setDisabled(false);
//			if(current!=null){
//				if(current.getLd_amount()!=null){
//					ld_amount.setValue(new BigDecimal(current.getLd_amount()).divide(new BigDecimal(100)));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void onFocus$currency(){
//		currentGet();
//	}
//	
//	public void onFocus$shifr_id(){
//		currentGet();
//	}
//	
//	public void onFocus$prod_name(){
//		currentGet();
//	}
//	
//	public void onFocus$sred_id(){
//		currentGet();
//	}
//	
//	public void onFocus$target(){
//		currentGet();
//	}
//	
//	public void onFocus$kred_id_cb(){
//		currentGet();
//	}
//	
//	public void onFocus$acceptance(){
//		currentGet();
//	}
//	
//	public void onFocus$cres(){
//		currentGet();
//	}
//	
//	public void onFocus$calc_id(){
//		currentGet();
//	}
//	
//	public void onFocus$term_type(){
//		currentGet();
//	}
//	
//	public void onFocus$kred_id(){
//		currentGet();
//	}
//	
//	public void onFocus$klass_id(){
//		currentGet();
//	}
//	
//	public void onFocus$status(){
//		currentGet();
//	}
//	
//	public void onFocus$klassp_id(){
//		currentGet();
//	}
//	
//	public void onFocus$is_tax(){
//		currentGet();
//	}
//	
//	public void onFocus$use_branch(){
//		currentGet();
//	}
	
	public void onSelect$currency(){
		current.setCurrency(currency.getValue().trim());
	}
	
	public void onSelect$shifr_id(){
		current.setShifr_id(shifr_id.getValue().trim());
	}
	
	public void onSelect$sred_id(){
		current.setSred_id(sred_id.getValue().trim());
	}
	
	public void onSelect$kred_id_cb(){
		current.setKred_id(kred_id_cb.getValue().trim());
		System.out.println("сука");
	}
	
	public void onSelect$acceptance(){
		current.setAcceptance(acceptance.getValue().trim());
	}
	
	public void onSelect$cres(){
		current.setCres(cres.getValue().trim());
	}
	
	public void onSelect$calc_id(){
		current.setCalc_id(calc_id.getValue().trim());
	}
	
	public void onSelect$term_type(){
		current.setTerm_type(term_type.getValue().trim());
	}
	
	public void onSelect$kred_id(){
		current.setKred_id(kred_id.getValue().trim());
	}
	
	public void onSelect$klass_id(){
		current.setKlass_id(klass_id.getValue().trim());
	}
	
	public void onSelect$status(){
		current.setStatus(status.getValue().trim());
	}
	
	public void onSelect$klassp_id(){
		current.setKlassp_id(klassp_id.getValue().trim());
	}
	
	public void onSelect$is_tax(){
		current.setIs_tax(is_tax.getValue().trim());
	}
	
	public void onSelect$use_branch(){
		current.setUse_branch(use_branch.getValue().trim());
	}
	
	public void onChange$prod_name(){
		current.setProd_name(prod_name.getValue().trim());
	}
	
	public void onChange$target(){
		current.setTarget(target.getValue().trim());
	}
	
//	private void currentGet(){
//		currency.setSelecteditem(current.getCurrency().trim());
//		shifr_id.setSelecteditem(current.getShifr_id().trim());
//		prod_name.setValue(current.getProd_name().trim());
//		sred_id.setSelecteditem(current.getSred_id().trim());
//		target.setValue(current.getTarget().trim());
////		kred_id_cb.setSelecteditem(current.getKred_id_cb().trim());
//		acceptance.setSelecteditem(current.getAcceptance().trim());
//		cres.setSelecteditem(current.getCres().trim());
//		calc_id.setSelecteditem(current.getCalc_id().trim());
//		term_type.setSelecteditem(current.getTerm_type().trim());
//		kred_id.setSelecteditem(current.getKred_id().trim());
//		klass_id.setSelecteditem(current.getKlass_id().trim());
//		status.setSelecteditem(current.getStatus().trim());
//		klassp_id.setSelecteditem(current.getKlassp_id().trim());
//		is_tax.setSelecteditem(current.getIs_tax().trim());
//		use_branch.setSelecteditem(current.getUse_branch().trim());
//	}
	
	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			filter.setId(template);
		} else
			onDoubleClick();
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
			onDoubleClick();
			frmgrd.setVisible(false);
			addgrd.setVisible(true);
			fgrd.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_search() {
		onDoubleClick();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		grd.setVisible(true);
	}

	public void onClick$btn_save() {
		Res res = new Res();
		try {
			if (addgrd.isVisible()) {
				LdProductService.create(
						new LdProduct(
								template,
								currencyTemplate,
								"",
								"",
								ashifr_id.getValue(),
								aprod_name.getValue(),
								asred_id.getValue(),
								atarget.getValue(),
								acalc_id.getValue(),
								aterm_type.getValue(),
								akred_id.getValue(),
								aklass_id.getValue(),
								astatus.getValue(),
								aklassp_id.getValue(),
								akred_id_cb.getValue(),
								aacceptance.getValue(),
								acres.getValue(),
								ais_tax.getValue(),
								ause_branch.getValue(),
								ald_amount.getValue()+""), alias,res);
				if(res.getCode()==1){
					alert(res.getName());
					return;
				}
				Utils.clearForm(addgrdl);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else {
				current.setId(template);
				current.setCurrency(currency.getValue());
				current.setLd_num("");
				current.setShifr_id(shifr_id.getValue());
				current.setProd_name(prod_name.getValue());
				current.setSred_id(sred_id.getValue());
				current.setTarget(target.getValue());
				current.setCalc_id(calc_id.getValue());
				current.setTerm_type(term_type.getValue());
				current.setKred_id(kred_id.getValue());
				current.setKlass_id(klass_id.getValue());
				current.setStatus(status.getValue());
				current.setKlassp_id(klassp_id.getValue());
				current.setKred_id_cb(kred_id_cb.getValue());
				current.setAcceptance(acceptance.getValue());
				current.setCres(cres.getValue());
				current.setIs_tax(is_tax.getValue());
				current.setUse_branch(use_branch.getValue());
				if(ld_amount!=null&&ld_amount.getValue()!=null){
					current.setLd_amount((ld_amount.getValue().multiply(new BigDecimal(100)))+"");
				} else {
					current.setLd_amount(null);
				}
				LdProductService.update(current,res,alias);
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
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error("LDPRODUCT Во время onClick$btn_save\n");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}

	}

	public void onClick$btn_cancel() {
		if (fgrdr.isVisible() && fgrdl.isVisible()) {
			filter = new LdProductFilter();
			filter.setId(template);
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
