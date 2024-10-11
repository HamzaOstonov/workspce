package com.is.bpri.operations;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.BproductService;
import com.is.bpri.operations.PagingListModel;
import com.is.bpri.utils.Utils;
//import com.is.creditanket.CreditService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class OperationViewCntrl extends GenericForwardComposer {
	
	private static final long serialVersionUID = 1L;

	private RefCBox bank_co,operation_type,type_doc,kod_pod,type_target;
	private Textbox account_co,type_doc_num,type_target_code,target,account,name_account;
	private Intbox num;
	private Decimalbox summa,rate;
	private String alias,branch;
	private Listbox lb;
	private Paging lb_paging;
	private PagingListModel model = null;
	private Operation current = new Operation(),filter = new Operation();
	private int _pageSize = 20;
	private int _totalSize = 0;
	private int _startPageNumber = 0;
	private Div list_div,grid_div,operation_grids,divmain;
	private Longbox form_id;
	private String ext_param = null;
	private Grid operationGrid;
	private HashMap<String, Grid> grid_map = new HashMap<String, Grid>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private AnnotateDataBinder bind;
	private Datebox date_curr,date_operation;
	private Button btn_operation_save;
	private Rows mainGrid;
	
	public OperationViewCntrl() {
		super('$',false,false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		bind = new AnnotateDataBinder(comp);
		bind.bindBean("current", this.current);
		bind.loadAll();
		alias = (String) session.getAttribute("alias");
		branch = (String) session.getAttribute("branch");
		setModels();
		lb.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem li, Object data) throws Exception {
				try {
					Operation operation = (Operation) data;
					li.setValue(operation);
					addEventsListitem(li);
					li.appendChild(new Listcell(operation.getId()+""));
					li.appendChild(new Listcell(operation.getForm_id()+""));
					li.appendChild(new Listcell(operation.getId_client()));
					li.appendChild(new Listcell(operation.getPurpose()));
					li.appendChild(new Listcell(operation.getSumma()==null?"":operation.getSumma().toString()));
					li.appendChild(new Listcell(operation.getDoc_num()));
					li.appendChild(new Listcell(sdf.format(operation.getV_date())));
					li.appendChild(new Listcell(operation.getState_name()));
				} catch (Exception e) {
					e.printStackTrace();
					ISLogger.getLogger().error(CheckNull.getPstr(e));
				}
			}
		});
//		initGrids();
		refreshModel(_startPageNumber);
		setVisibleComponents(true);
	}
	
	public void initFromBpr(String form_id){
		System.out.println("FORM_ID = "+form_id);
		if(form_id!=null&&!form_id.equals("")){
			filter.setForm_id(Long.parseLong(form_id));
			_pageSize = 5;
			refreshModel(_startPageNumber);
		}
	}
	
	private void setModels(){
		bank_co.setModel(new ListModelList(BproductService.getSMfo()));
		type_doc.setModel(new ListModelList(BproductService.getType_doc(alias)));
		operation_type.setModel(new ListModelList(OperationService.getOperation_type(alias)));
		type_target.setModel(new ListModelList(OperationService.getS_nazn()));
	}
	
	private void setVisibleComponents(boolean bool){
		list_div.setVisible(bool);
		grid_div.setVisible(!bool);
	}
	
	private void refreshModel(int activePage){
		try {
			filter.setBranch(branch);
			lb_paging.setPageSize(_pageSize);
			model = new PagingListModel(activePage, _pageSize, filter, alias);
			_totalSize = model.getTotalSize(filter, alias);
			lb_paging.setTotalSize(_totalSize);
			lb.setModel((ListModel) model);
			if (model.getSize() > 0) {
				sendSelEvt();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onChange$type_target_code(){
		type_target.setSelecteditem(type_target_code.getValue());
	}
	
	public void onOK$type_target_code(){
		type_target.setSelecteditem(type_target_code.getValue());
	}
	
	private void sendSelEvt(){
		SelectEvent evt = new SelectEvent("onSelect", lb,lb.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onPaging$lb_paging(ForwardEvent event) {
		try {
			final PagingEvent pe = (PagingEvent) event.getOrigin();
			_startPageNumber = pe.getActivePage();
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onClick$btn_operation_add(){
		setVisibleComponents(false);
		Utils.clearForm(mainGrid);
		if(filter!=null){
			form_id.setValue(filter.getForm_id());
		}
		Res res = new Res();
		date_operation.setValue(Utils.getInfoDate(alias, res));
		date_curr.setValue(Utils.getInfoDate(alias, res));
		account.setReadonly(true);
		name_account.setReadonly(true);
		rate.setReadonly(true);
	}
	
	public void onClick$btn_operation_cancel(){
		setVisibleComponents(true);
	}
	
	public void onClick$btn_operation_save(){
		try {
//			bind.saveAll();
			if(form_id.getValue()!=null){
				current.setBranch(branch);
				current.setForm_id(form_id.getValue());
				current.setBank(bank_co.getValue());
				current.setAccount(account_co.getValue());
				current.setD_date(date_curr.getValue());
				if(num.getValue()!=null)
				current.setNum(num.getValue());
				current.setId_transh_purp(type_target.getValue());
				current.setSumma(summa.getValue());
				current.setCode_plat(type_target.getValue());
				Res res = new Res();
				OperationService.executeOperation(operation_type.getValue(),current, alias, res);
				if(res.getCode()==1){
					alert(res.getName());
				} else {
					setVisibleComponents(true);
					alert("Îïåðàöèÿ '"+operation_type.getText()+"' âûïîëíåíà óñïåøíî");
					refreshModel(_startPageNumber);
				}
			} else {
				alert("Íå óêàçàí íîìåð àíêåòû");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
	}
	
	public void onChange$operation_type(){
		Utils.clearForm(mainGrid);
		List<?> list = mainGrid.getChildren();
		List<String> ids = OperationService.getNameGridsFieldsVisible(operation_type.getValue());
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) instanceof Row){
				Row row = (Row) list.get(i);
				row.setVisible(ids.contains(row.getId()));
			}
		}
		btn_operation_save.setLabel("Âûïîëíèòü îïåðàöèþ '"+operation_type.getText()+"'");
		if(form_id.getValue()!=null){
			String[] acc_info = OperationService.getAccInfo(form_id.getValue(), branch, alias).split("#%");
			System.out.println("size = "+acc_info.length);
			if(acc_info!=null){ account.setValue(acc_info[0]); name_account.setValue(acc_info[1]); }
			rate.setValue(OperationService.getRateInfo(form_id.getValue(), branch, alias));
			if(operation_type.getValue().equals("30")) summa.setValue(OperationService.getAmount(form_id.getValue()+"", branch, alias));
			else { BigDecimal s = null; summa.setValue(s); }
			target.setValue(OperationService.getTarget(form_id.getValue(), operation_type.getValue(), alias));
		}
//		insertIntoGrid();
	}
	
//	private void insertIntoGrid(){ ÍÅ ÓÄÀËßÒÜ - ÍÓÆÍÎ ÄËß ÏÎÑËÅÄÓÞÙÈÕ ÈÍÑÅÐÒÎÂ ÎÏÅÐÀÖÈÉ
//		List<?> list = mainGrid.getChildren();
//		List<String> ids = new ArrayList<String>();
//		for (int i = 0; i < list.size(); i++) {
//			if(list.get(i) instanceof Row){
//				Row row = (Row) list.get(i);
//				if(row.isVisible()){
//					ids.add(row.getId());
//				}
//			}
//		}
//		OperationService.insertIntoGridOperation(operation_type.getValue(), ids);
//	}
	
	private void addEventsListitem(Listitem li){
		li.addEventListener(Events.ON_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				Listitem li = (Listitem) evt.getTarget();
				current = (Operation) li.getValue();
				bind.loadAll();
			}
		});
		li.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
			
			@Override
			public void onEvent(Event evt) throws Exception {
				setVisibleComponents(false);
				getValues();
				onChange$operation_type();
			}
		});
	}
	
	private void getValues(){
		Listitem li = lb.getSelectedItem();
		current = (Operation) li.getValue();
		operation_type.setSelecteditem(current.getS_deal_id()+"");
		form_id.setValue(current.getForm_id());
		type_doc.setSelecteditem(current.getDoc_type_m());
		if(filter!=null){
			form_id.setValue(filter.getForm_id());
		}
	}

	public Operation getCurrent() {
		return current;
	}

	public void setCurrent(Operation current) {
		this.current = current;
	}
}
