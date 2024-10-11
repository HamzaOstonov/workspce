package com.is.bpri.bpr_employee;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class BprEmployeeController extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private int _pageSize = 10,_totalSize = 0,_startPageNumber = 0;
	private String alias;
	private BprEmployee filter,current = new BprEmployee();
	private PagingListModel model;
	private Listbox dataGrid;
	private Paging employeePaging;
	private AnnotateDataBinder binder;
	private Div grd,addgrd;
	private Textbox name_employee;
	private RefCBox type_employee;
	private String action = "";
	private RefCBox branch_employee;
	private String branch;
	
	public BprEmployeeController() {
		super('$',false,false);
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		branch = (String) session.getAttribute("branch");
		dataGrid.setItemRenderer(new ListitemRenderer() {
			
			@Override
			public void render(Listitem row, Object data) throws Exception {
				try {
					BprEmployee emp = (BprEmployee) data;
					row.setValue(emp);
					row.appendChild(new Listcell(emp.getBranch()));
					row.appendChild(new Listcell(BprEmployeeService.getTypeEmployeeName("44", emp.getLabel())));
					row.appendChild(new Listcell(emp.getValue()));
					Button btn = new Button();
					btn.setAttribute("emp", emp);
					btn.setLabel("Удалить");
					btn.addEventListener(Events.ON_CLICK, new EventListener() {
						
						@Override
						public void onEvent(Event evt) throws Exception {
							final BprEmployee emp = (BprEmployee) evt.getTarget().getAttribute("emp");
							Messagebox.show("Вы уверены что хотите удалить сотрудника?", "Предупреждение", Messagebox.YES|Messagebox.NO, Messagebox.QUESTION, new EventListener() {
								
								@Override
								public void onEvent(Event event) throws Exception {
									if(event.getName().equals("onYes"))BprEmployeeService.removeEmployee(emp);
									refreshModel(_startPageNumber);
								}
							});
						}
					});
					Listcell lc_btn_del = new Listcell();
					lc_btn_del.appendChild(btn);
					row.appendChild(lc_btn_del);
					row.addEventListener(Events.ON_CLICK, new EventListener() {
						
						@Override
						public void onEvent(Event evt) throws Exception {
							try {
								Listitem li = (Listitem) evt.getTarget();
								BprEmployee employee = (BprEmployee) li.getValue();
								current.setBranch(employee.getBranch());
								current.setLabel(employee.getLabel());
								current.setValue(employee.getValue());
								current.setId(employee.getId());
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					row.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {
						
						@Override
						public void onEvent(Event evt) throws Exception {
							try {
								branch_employee.setSelecteditem(current.getBranch());
								type_employee.setSelecteditem(current.getLabel());
								name_employee.setValue(current.getValue());
								hideGrd();
								action = "double";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		type_employee.setModel(new ListModelList(BprEmployeeService.getTypesEmployees("44")));
		branch_employee.setModel(new ListModelList(BprEmployeeService.getS_mfo(branch)));
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage) {
		try {
			employeePaging.setPageSize(_pageSize);
			model = new PagingListModel(activePage, _pageSize, filter, alias);
			_totalSize = model.getTotalSize(filter,alias);
			employeePaging.setTotalSize(_totalSize);
			dataGrid.setModel((ListModel) model);
			if (model.getSize() > 0) {
				this.current = (BprEmployee) model.getElementAt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
		}
	}
	
	public void onPaging$employeePaging(ForwardEvent event) {
		try {
			final PagingEvent pe = (PagingEvent) event.getOrigin();
			_startPageNumber = pe.getActivePage();
			refreshModel(_startPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_add(){
		try {
			Utils.clearForm(addgrd);
			hideGrd();
			action = "add";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onClick$btn_cancel(){
		grd.setVisible(true);
		addgrd.setVisible(false);
	}
	
	public void onClick$btn_save(){
		current.setLabel(type_employee.getValue());
		current.setValue(name_employee.getValue());
		current.setBranch(branch_employee.getValue());
		Res res = new Res();
		if(action.equals("add")){
			BprEmployeeService.createEmployee(current,res);
		} else if(action.equals("double")){
			BprEmployeeService.updateEmployee(current,res);
		}
		if(res.getCode()==1){
			alert(res.getName());
		} else {
			refreshModel(_startPageNumber);
			onClick$btn_cancel();
		}
	}
	
	private void hideGrd(){
		grd.setVisible(false);
		addgrd.setVisible(true);
	}
}
