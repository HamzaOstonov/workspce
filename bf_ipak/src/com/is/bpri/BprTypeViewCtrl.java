package com.is.bpri;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.main_scoring.MainScoringCtrl;
import com.is.bpri.utils.Utils;
import com.is.clienta.ClientA;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

@SuppressWarnings("serial")
public class BprTypeViewCtrl extends GenericForwardComposer {
	private Div frm;
	private Window bprtypemain;
	private Listbox dataGrid;
	private Div grd;
//	private Grid addgrdl, fgrdl;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_save,btn_del,btn_cancel,btn_delete;
	private Toolbarbutton btn_saving,btn_close,btn_recreate;
	private Textbox aname, provision, aprovision,name;
	private RefCBox acurrency, currency, deal_id, adeal_id, bpr_type,
			abpr_type,dname,astate,mfo,region,fmfo,fregion,atarget_clients,target_clients;
	private Paging bprtypePaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	public BprTypeFilter filter;
	private String alias;
	private Include nireq;
	private Include ldproduct;
	private Include hisrate;
	private Include ldguarr;
	private Include ldgr;
	private Include bprLdForms;
	private Include bprLdAccount;
	private Include bprChangeLimit;
	private Include bprspecialfrm;
	private Include authorizedacc;
	private Include scoringInc;
	private Include scoringappInc;
	private Row provrow,provisrow,addrow,searchrow,staterow;
	private String page;
//	private Tabbox tabbox;
	private Groupbox bprtype_tab, nireq_tab, ho_tab, ldhisrate_tab,scoringtab,ldguarr_tab,
			ldgr_tab, ldforms_tab, ldaccount_tab, changelimit_tab,bpr_specialfrm,authorizedacctab,scoringapp;
	private String btn = "";
	private String activeGP = null;

	public String name_scoring_cod="";
	public String name_scoring_name="";
	
	private ClientA client_obj = null;
	
	BprTypePagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	private String branch = "";

	private BprType current = new BprType();

	private void visibleTabs(boolean bool) {
		nireq_tab.setVisible(bool);
		ho_tab.setVisible(bool);
		ldhisrate_tab.setVisible(bool);
		ldguarr_tab.setVisible(bool);
		ldgr_tab.setVisible(bool);
		ldforms_tab.setVisible(bool);
		ldaccount_tab.setVisible(bool);
		bpr_specialfrm.setVisible(bool);
		authorizedacctab.setVisible(bool);
		scoringapp.setVisible(bool);
		changelimit_tab.setVisible(bool);
//		if(current!=null){
//			if((current.getBpr_type() == 1 || current.getBpr_type() == 4 || current.getBpr_type() == 5) && bool == true){
//				changelimit_tab.setVisible(true);
//			} else {
//				changelimit_tab.setVisible(false);
//			}
//		}
	}

	public BprTypeViewCtrl() {
		super('$', false, false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception { // шаблоны
		super.doAfterCompose(comp);
		try {
			binder = new AnnotateDataBinder(comp);
			binder.bindBean("current", this.current);
			binder.loadAll();
			page = bprtypemain.getUuid();
			alias = (String) session.getAttribute("alias");
			filter = new BprTypeFilter();
			filter.setState(999);
			branch = (String) session.getAttribute("branch");
			System.out.println("BprTypeViewCtrl Шаблоны");
			dataGrid.setItemRenderer(new ListitemRenderer() {
				public void render(Listitem row, Object data) throws Exception {
					BprType pBprType = (BprType) data;
					row.setValue(pBprType);
					row.addEventListener(Events.ON_DOUBLE_CLICK,
							new EventListener() {

								@Override
								public void onEvent(Event event) throws Exception {
									doubleClickDataGrid();
								}
							});
					row.appendChild(new Listcell(pBprType.getBpr_type()+""));
					row.appendChild(new Listcell(pBprType.getBpr_id()+""));
					row.appendChild(new Listcell(pBprType.getName()));
					row.appendChild(new Listcell(pBprType.getCurrency()));
					row.appendChild(new Listcell(pBprType.getDeal_id()));
					row.appendChild(new Listcell(pBprType.getMfo()));
					row.appendChild(new Listcell(BprTypeService.getRegionName(pBprType.getRegion_id())));
					row.appendChild(new Listcell(pBprType.getProvision()));
					row.appendChild(new Listcell(pBprType.getState_name()));
				}
			});
			fmfo.setModel(new ListModelList(com.is.bpri.BprTypeService.getBranchs(branch)));
			mfo.setModel(new ListModelList(com.is.bpri.BprTypeService.getBranchs(branch)));
			fregion.setModel(new ListModelList(com.is.bpri.BprTypeService.getRegions()));
			region.setModel(new ListModelList(com.is.bpri.BprTypeService.getRegions()));
			currency.setModel(new ListModelList(com.is.bpri.BprTypeService
					.getCurrency(alias)));
			acurrency.setModel(new ListModelList(com.is.bpri.BprTypeService
					.getCurrency(alias)));
			deal_id.setModel(new ListModelList(com.is.bpri.BprTypeService
					.getDeal_id(alias)));
			adeal_id.setModel(new ListModelList(com.is.bpri.BprTypeService
					.getDeal_id(alias)));
			dname.setModel(new ListModelList(com.is.bpri.BproductService.getProdId("",BproductService.getRegionFromBranch_2(
					session.getAttribute("un").toString(),
					session.getAttribute("pwd").toString(),branch,alias),"",alias)));
			provrow.setVisible(false);
			astate.setModel(new ListModelList(com.is.bpri.BprTypeService.getBprTypeState(alias)));
			atarget_clients.setModel(new ListModelList(BprTypeService.getTarget_clients()));
			target_clients.setModel(new ListModelList(BprTypeService.getTarget_clients()));
			try {
				bpr_type.setModel(new ListModelList(com.is.bpri.BprTypeService
						.getBprType("")));
				abpr_type.setModel(new ListModelList(com.is.bpri.BprTypeService
						.getBprType("")));
			} catch (Exception e) {
				e.printStackTrace();
				ISLogger.getLogger().error("Тут ошибка в модели");
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
			changelimit_tab.setVisible(false);
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			e.printStackTrace();
		}
	}

	public void onPaging$bprtypePaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	public void onSelect$region(){
		mfo.setModel(new ListModelList(BprTypeService.getBranchsFromRegion(branch, region.getValue())));
	}
	
	public void onSelect$fregion(){
		fmfo.setModel(new ListModelList(BprTypeService.getBranchsFromRegion(branch, fregion.getValue())));
	}
	
	public void onClick$btn_saving() throws InterruptedException{
		Messagebox.show("Вы уверены что хотите утвердить шаблон?", "Предупреждение", Messagebox.YES|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
			
			
			
			@Override
			public void onEvent(Event evt) throws Exception {
				if(evt.getName().equals("onYes")){
					name_scoring_name=(String) session.getAttribute("name_scoring_name");
					name_scoring_cod=(String) session.getAttribute("name_scoring_cod");
					System.out.println("proc: "+name_scoring_cod);
					System.out.println("name2: "+name_scoring_name);
					
					if(current.getState()!=1){
						Res res = new Res();
						BprTypeService.confirmationBpr_type(current.getBpr_id()+"", alias, res);
						if(res.getCode()!=1){
							current.setState(1);
							BprTypeService.update(current, alias,res);
							if(name_scoring_name!=null || !name_scoring_name.isEmpty()) {
								
								BprTypeService.updateScoringType(alias, current.getBpr_id(), 1,name_scoring_cod, name_scoring_name);
							  	
							}
							if(res.getCode()!=1){
								alert("Шаблон утверждён");
								btn_saving.setVisible(false);
								btn_close.setVisible(false);
								refreshModel(_startPageNumber);
								onClick$btn_back();
							} else {
								alert(res.getName());
							}
						} else {
							alert(res.getName());
						}
					} else {
						alert("Шаблон уже утвержден");
					}
				}
			}
		});
	}
	
	public void onClick$btn_close() throws InterruptedException{
		Messagebox.show("Вы уверены что хотите закрыть шаблон?", "Предупреждение!", Messagebox.YES|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
			
		
			
			@Override
			public void onEvent(Event evt) throws Exception {
				if(evt.getName().equals("onYes")){
				
					if(current.getState()!=2){
						Res res = new Res();
						current.setState(2);
						BprTypeService.update(current, alias,res);
						if(res.getCode()!=1){
							alert("Шаблон закрыт");
							btn_saving.setVisible(false);
							btn_close.setVisible(false);
							refreshModel(_startPageNumber);
							onClick$btn_back();
						} else {
							alert(res.getName());
						}
					} else {
						alert("Шаблон уже закрыт");
					}
				}
			}
		});
	}
	
	private void refreshModel(int activePage) {
		bprtypePaging.setPageSize(_pageSize);
		model = new BprTypePagingListModel(activePage, _pageSize, filter, alias);
		_totalSize = model.getTotalSize(filter,alias);
		bprtypePaging.setTotalSize(_totalSize);
		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (BprType) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public BprType getCurrent() {
		return current;
	}

	public void setCurrent(BprType current) {
		this.current = current;
	}

	private void doubleClickDataGrid() {
		try {
			Listitem temp = dataGrid.getSelectedItem();
			if (temp != null) {
				grd.setVisible(false);
				frm.setVisible(true);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
				visibleTabs(true);
				btn_del.setVisible(true);
				btn_cancel.setVisible(true);
				btn_delete.setVisible(true);
				btn_back.setImage("/images/folder.png");
				btn_back.setLabel(Labels.getLabel("grid"));
				fmfo.setModel(new ListModelList(com.is.bpri.BprTypeService.getBranchs(branch)));
				if (current != null) {
					scoringtab.setVisible(true);
					if(fregion==null){
						System.out.println("null!!!");
					}
					fregion.setSelecteditem(current.getRegion_id());
					if (current.getBpr_type() == 1 || current.getBpr_type() == 3 || current.getBpr_type() == 6) {
						provisrow.setVisible(false);
						if(current.getBpr_type() == 3 || current.getBpr_type() == 6){
							authorizedacctab.setVisible(true);
							changelimit_tab.setVisible(false);
						} else {
							changelimit_tab.setVisible(true);
							authorizedacctab.setVisible(false);
						}
					} else {
						if(current.getBpr_type() == 2){
							changelimit_tab.setVisible(false);
						} else {
							changelimit_tab.setVisible(true);
						}
						provisrow.setVisible(true);
						authorizedacctab.setVisible(false);
					}
				}
				session.setAttribute("btn_clicks", "doubleClick");
				onSelect$name();
				onInitRenderLater$deal_id();
				onSelect$bpr_type();
				bprtype_tab.setOpen(true);
//				tabbox.setSelectedIndex(0);
				btn = "double";
				if(current.getState()!=1){
					btn_saving.setVisible(true);
				}
				if(current.getState()!=2){
					btn_close.setVisible(true);
				}
				if(current.getState()>0){
					btn_recreate.setVisible(true);
					btn_save.setVisible(false);
				} else {
					btn_recreate.setVisible(false);
					btn_save.setVisible(true);
				}
			} else {
				alert("Ничего не выбрано");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			btn_del.setVisible(false);
			btn_cancel.setVisible(false);
			btn_save.setVisible(false);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
			btn_saving.setVisible(false);
			btn_close.setVisible(false);
			btn_recreate.setVisible(false);
		} else
			doubleClickDataGrid();
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
		if(current!=null){
			doubleClickDataGrid();
		}
	}
	
	private void ddClick(){
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fmfo.setModel(new ListModelList(com.is.bpri.BprTypeService.getBranchs(branch)));
		addrow.setVisible(true);
		fgrd.setVisible(false);
		btn_del.setVisible(false);
		btn_cancel.setVisible(true);
		btn_save.setVisible(true);
		staterow.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		searchrow.setVisible(false);
	}

	public void onClick$btn_add() {
		try {
			// onDoubleClick$dataGrid$grd();
			ddClick();
//			includeload("add");
			session.setAttribute("btn_clicks", "add");
			// ---------------------------------------------------------------------
			visibleTabs(false);
//			tabbox.setSelectedTab(bprtype_tab);
			frmgrd.setVisible(false);
			addgrd.setVisible(true);
			mfo.setModel(new ListModelList(com.is.bpri.BprTypeService.getBranchs(branch)));
			fgrd.setVisible(false);
			provrow.setVisible(false);
			btn_save.setLabel(Labels.getLabel("save"));
			btn = "add";
			btn_recreate.setVisible(false);
			mfo.setValue("");
			region.setValue("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClick$btn_search() {
		ddClick();
		if(filter==null){
			filter = new BprTypeFilter();
		}
		getFilterValue();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		provrow.setVisible(true);
		visibleTabs(false);
		btn_save.setLabel(Labels.getLabel("search"));
		addrow.setVisible(false);
		searchrow.setVisible(true);
		staterow.setVisible(true);
		btn = "search";
		btn_recreate.setVisible(false);
	}
	
	private void getFilterValue(){
		if(filter!=null){
			abpr_type.setSelecteditem(filter.getBpr_type()+"");
			dname.setSelecteditem(filter.getBpr_id()+"");
			acurrency.setSelecteditem(filter.getCurrency());
			adeal_id.setSelecteditem(filter.getDeal_id());
			if(filter.getProvision()!=null){
				aprovision.setValue(filter.getProvision()+"");
			}
			astate.setSelecteditem(filter.getState()+"");
		}
	}

	
	public void onClick$btn_save() throws WrongValueException, SQLException {
		
		Res res = new Res();
	
		name_scoring_name=(String) session.getAttribute("name_scoring_name");
		name_scoring_cod=(String) session.getAttribute("name_scoring_cod");
		System.out.println("cod1: "+name_scoring_cod);
		System.out.println("name2: "+name_scoring_name);
		
		ISLogger.getLogger().error("Была нажата кнопка save");
		try {
			if (btn.equals("add")) {
					current = new BprType();
					if(abpr_type.getValue().equals("")||abpr_type.getValue()==null){
						alert("Не выбран тип продукта");
						return;
					}
					int bprtype = Integer.parseInt(abpr_type.getValue());
					current.setBpr_type(bprtype);
					current.setName(aname.getValue());
					current.setDeal_id(adeal_id.getValue());
					current.setCurrency(acurrency.getValue());
					current.setProvision(aprovision.getValue());
					current.setMfo(mfo.getValue());
					current.setRegion_id(region.getValue());
					current.setTarget_clients(atarget_clients.getValue());
					BprTypeService.create(current, alias,res);
					System.out.println("cod: "+name_scoring_cod+" name: "+name_scoring_name);
					//BprTypeService.insertScoringType(alias, current.getBpr_id(), 1,name_scoring_cod, name_scoring_name);
					BprTypeService.createScoringType(alias, current.getBpr_id(), 1,name_scoring_cod, name_scoring_name);
					Utils.clearForm(addgrd);
				onClick$btn_back();
				grd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (btn.equals("search")) {
				filter = new BprTypeFilter();
				if(dname.getValue()!=null&&!dname.getValue().equals("")){
					filter.setBpr_id(Integer.parseInt(dname.getValue()));
				}
				if(abpr_type.getValue()!=null&&!abpr_type.getValue().equals("")){
					filter.setBpr_type(Integer.parseInt(abpr_type.getValue()));
				}
				filter.setCurrency(acurrency.getValue());
				filter.setDeal_id(adeal_id.getValue());
				filter.setMfo(mfo.getValue());
				filter.setRegion_id(region.getValue());
				if(aprovision.getValue()!=null&&!aprovision.getValue().equals("")){
					filter.setProvision(Integer.parseInt(aprovision.getValue()));
				}
				if(astate.getValue()!=null&&!astate.getValue().equals("")){
					filter.setState(Integer.parseInt(astate.getValue()));
				}
				onClick$btn_back();
			} else {
				current.setBpr_type(Integer.parseInt(bpr_type.getValue()));
				current.setName(name.getValue());
				current.setCurrency(currency.getValue());
				current.setDeal_id(deal_id.getValue());
				current.setProvision(provision.getValue());
				current.setMfo(fmfo.getValue());
				current.setRegion_id(fregion.getValue());
				current.setTarget_clients(target_clients.getValue());
				BprTypeService.update(current, alias,res);
				onClick$btn_back();
				grd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			}
			if(res.getCode()!=1){
				if(btn.equals("add")||btn.equals("double")){
					alert("Данные успешно сохранены");
				}
			} else {
				alert(res.getName());
				return;
			}
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_del(){
		try {
			if(current!=null){
				Messagebox.show("Вы действительно хотите удалить шаблон\n"+current.getName(), "Предупреждение",Messagebox.OK|Messagebox.NO, Messagebox.EXCLAMATION, new EventListener() {
					
					@Override
					public void onEvent(Event event) throws Exception {
						if(event.getName().equals("onOK")){
							Res res = new Res();
							BprTypeService.remove(current.getBpr_id(), alias, res);
							if(res.getCode()!=1){
								if(btn_del.isVisible()){
									Utils.clearForm(addgrd);
									onClick$btn_back();
									grd.setVisible(true);
									addgrd.setVisible(false);
									fgrd.setVisible(false);
								}
								refreshModel(_startPageNumber);
								SelectEvent evt = new SelectEvent("onSelect", dataGrid,dataGrid.getSelectedItems());
								Events.sendEvent(evt);
							} else {
								alert(res.getName());
							}
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_delete(){
		onClick$btn_del();
	}

	public void onClick$btn_cancel() {
		if (filter!=null) {
			filter = new BprTypeFilter();
			filter.setState(999);
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		Utils.clearForm(addgrd);
		Utils.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_recreate(){
		Res res = new Res();
		try {
			BprTypeService.reCreate(current.getBpr_id()+"", alias, res);
			if(res.getCode()!=1){
				alert("Новый шаблон успешно создан");
				onClick$btn_back();
				refreshModel(_startPageNumber);
			} else {
				alert(res.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}

	public void onSelect$name() {
		session.setAttribute("currencyTemplate", currency.getValue());
	}

	private void visibleTBButtons(boolean bool){
		btn_del.setVisible(bool);
		btn_save.setVisible(bool);
		btn_cancel.setVisible(bool);
		btn_delete.setVisible(bool);
	}
	
	private void groupBoxClose(){
		List<?> list = frm.getChildren();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof Groupbox){
				if(!((Groupbox) list.get(i)).getId().equals(activeGP)){
					((Groupbox) list.get(i)).setOpen(false);
				}
			}
		}
	}
	
	public void onOpen$changelimit_tab(){
		if(changelimit_tab.isOpen()){
			try {
				if (!session.getAttribute("btn_clicks").equals("add")) {
					bprChangeLimit.setSrc(null);
					visibleTBButtons(false);
					bprChangeLimit.setSrc("BprChangeLimit.zul?template=" + getCurrentProductName()+"&state="+current.getState()+"&target="+current.getTarget_clients());
					activeGP = changelimit_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$bprtype_tab(){
		if(bprtype_tab.isOpen()){
			visibleTBButtons(true);
			activeGP = bprtype_tab.getId();
			groupBoxClose();
		}
	}
	
	public void onOpen$ldaccount_tab(){
		if(ldaccount_tab.isOpen()){
			try {
				if (!session.getAttribute("btn_clicks").equals("add")) {
					bprLdAccount.setSrc(null);
					visibleTBButtons(false);
					bprLdAccount.setSrc("BprLdAccount.zul?template=" + getCurrentProductName()+"&state="+current.getState());
					activeGP = ldaccount_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$ldforms_tab(){
		if(ldforms_tab.isOpen()){
			try {
				if (!session.getAttribute("btn_clicks").equals("add")) {
					bprLdForms.setSrc(null);
					visibleTBButtons(false);
					bprLdForms.setSrc("BprLdForms.zul?template=" + getCurrentProductName()+"&state="+current.getState());
					activeGP = ldforms_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$ldgr_tab(){
		if(ldgr_tab.isOpen()){
			try {
				if (!session.getAttribute("btn_clicks").equals("add")) {
					ldgr.setSrc(null);
					visibleTBButtons(false);
					ldgr.setSrc("BprLdGr.zul?template=" + getCurrentProductName()+"&state="+current.getState());
					activeGP = ldgr_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$ldhisrate_tab(){
		if(ldhisrate_tab.isOpen()){
			try {
				if (!session.getAttribute("btn_clicks").equals("add")) {
					hisrate.setSrc(null);
					visibleTBButtons(false);
					hisrate.setSrc("LdHisRate.zul?template=" + getCurrentProductName()+"&state="+current.getState());
					activeGP = ldhisrate_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$nireq_tab(){
		if(nireq_tab.isOpen()){
			try {
				if (!session.getAttribute("btn_clicks").equals("add")) {
					nireq.setSrc(null);
					visibleTBButtons(false);
					nireq.setSrc("NiReq.zul?template=" + getCurrentProductName() + "&page=" + page+"&state="+current.getState());  // Кредитная
					activeGP = nireq_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$ho_tab(){
		if(ho_tab.isOpen()){
			if (!session.getAttribute("btn_clicks").equals("add")) {
				ldproduct.setSrc(null);
				visibleTBButtons(false);
				ldproduct.setSrc("LdProduct.zul?template=" + getCurrentProductName() + "&page=" + page+"&state="+current.getState()); // Общая
				// характеристика
				activeGP = ho_tab.getId();
				groupBoxClose();
			}
		} 
	}
	
//	public void onClick$ho_tab(){
//		try {
//			if (!session.getAttribute("btn_clicks").equals("add")) {
//				ldproduct.setSrc(null);
//				visibleTBButtons(false);
//				ldproduct.setSrc("LdProduct.zul?template=" + getCurrentProductName() + "&page=" + page+"&state="+current.getState()); // Общая
//				// характеристика
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void onOpen$scoringtab(){
		if(scoringtab.isOpen()){
			try {
				System.out.println("btn_clic: "+session.getAttribute("btn_clicks"));
				if(!session.getAttribute("btn_clicks").equals("add")){
					scoringInc.setSrc(null);
					visibleTBButtons(false);
					scoringInc.setSrc("bpr/main_scoring.zul?template="+current.getBpr_id()+"&state="+current.getState()+"&bpr_type="+current.getBpr_type());
					activeGP = scoringtab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$ldguarr_tab(){
		if(ldguarr_tab.isOpen()){
			try {
				if(!session.getAttribute("btn_clicks").equals("add")){
					ldguarr.setSrc(null);
					visibleTBButtons(false);
					String temp = "0";
					temp = getCurrentProductName();
					ldguarr.setSrc("LdGuarr_2.zul?template=" + temp+"&state="+current.getState());
					activeGP = ldguarr_tab.getId();
					groupBoxClose();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$bpr_specialfrm(){
		if(bpr_specialfrm.isOpen()){
			try {
				bprspecialfrm.setSrc(null);
				String temp = "0";
				visibleTBButtons(false);
				temp = getCurrentProductName();
				bprspecialfrm.setSrc("/bpr/bprspecialfrm.zul?bpr_id="+temp+"&state="+current.getState()+"&bpr_type="+current.getBpr_type());
				activeGP = bpr_specialfrm.getId();
				groupBoxClose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$authorizedacctab(){
		if(authorizedacctab.isOpen()){
			try {
				authorizedacc.setSrc(null);
				visibleTBButtons(false);
				String temp = "0";
				temp = getCurrentProductName();
				authorizedacc.setSrc("/bpr/authorized_acc.zul?bpr_id="+temp+"&state="+current.getState()+"&bpr_type="+current.getBpr_type());
				activeGP = authorizedacctab.getId();
				groupBoxClose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onOpen$scoringapp(){
		if(scoringapp.isOpen()){
			try {
				scoringappInc.setSrc(null);
				visibleTBButtons(false);
				scoringappInc.setSrc("/bpr/scoring/template_c.zul?bpr_id="+current.getBpr_id()+"&state="+current.getState()+"&bpr_type="+current.getBpr_type()+"&target="+current.getTarget_clients());
				activeGP = scoringapp.getId();
				groupBoxClose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void onInitRenderLater$deal_id() {
		if (current != null)
			deal_id.setSelecteditem(current.getDeal_id());
	}

	public String getCurrentProductName() {
		String res = current.getBpr_id()+"";
		return res;
	}

	public void onSelect$abpr_type() {
		if (abpr_type.getValue().equals("2")) {
			provrow.setVisible(true);
		} else {
			provrow.setVisible(false);
		}
	}
	
	public void onSelect$bpr_type() {
		if (bpr_type.getValue().equals("2")) {
			provisrow.setVisible(true);
		} else {
			provisrow.setVisible(false);
		}
	}

	public void onComission(Event evt) {
		ldproduct.setSrc(null);
		String val = getCurrentProductName();
		ldproduct.setSrc("LdProduct.zul?template=" + val + "&page=" + page+"&state="+current.getState());
	}
}
