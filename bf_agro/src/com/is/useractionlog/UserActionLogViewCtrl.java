package com.is.useractionlog;

import java.text.SimpleDateFormat;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;


@SuppressWarnings("serial")
public class UserActionLogViewCtrl extends GenericForwardComposer {
	private Window detailsWindow1, detailsWindow2, detailsWindow3, detailsWindow4;
	private Listbox dataDetailsGrid1, dataDetailsGrid2, dataDetailsGrid3, dataDetailsGrid4;
	private Toolbarbutton btn_last1;
	private Toolbarbutton btn_next1;
	private Toolbarbutton btn_prev1;
	private Toolbarbutton btn_first1;
	private Textbox txtusername1, txtusername2, txtusername3, txtusername4;
	private Datebox txtdate1, txtdate2, txtdate3, txtdate4, txtdate5;
	private Datebox detailsWindow1$action_date, detailsWindow2$v_date, detailsWindow2$date_time, 
	detailsWindow3$actiondate, detailsWindow4$v_date, detailsWindow4$date_time;
	private Textbox detailsWindow1$id, detailsWindow1$branch, detailsWindow1$user_id, detailsWindow1$user_name,
	detailsWindow1$ip_address, detailsWindow1$act_type, detailsWindow1$entity_type, detailsWindow1$entity_id;
	private Textbox detailsWindow2$branch, detailsWindow2$user_id, detailsWindow2$user_name, detailsWindow2$user_mac,
	detailsWindow2$user_ip, detailsWindow2$type_log, detailsWindow2$log_text,  detailsWindow2$group_id;
	private Textbox detailsWindow3$branch, detailsWindow3$user_name, detailsWindow3$action, detailsWindow3$user_ip, 
	detailsWindow3$user_mac, detailsWindow3$userhost, detailsWindow3$userport, detailsWindow3$clientver, detailsWindow3$appip;
	private Textbox detailsWindow4$id, detailsWindow4$rep_deal_id, detailsWindow4$rep_id, detailsWindow4$par_name,
	detailsWindow4$par_value, detailsWindow4$user_id, detailsWindow4$comp_name; 
	private Paging userActionsLogPaging1, userActionsLogPaging2, userActionsLogPaging3, userActionsLogPaging4;
	private String alias, branch1, un, pw;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize1 = 0;
	private int _totalSize2 = 0;
	private int _totalSize3 = 0;
	public List<UserActionLog> UserActionLogList;
	public UserActionLog current = new UserActionLog();
	public UserActionLogFilter filter = new UserActionLogFilter();
	public List<UserActionLog> UserActionLog;

	PagingListModel model1 = null;
	PagingListModel model2 = null;
	PagingListModel model3 = null;
	PagingListModel model4 = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat dft = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	public UserActionLogViewCtrl() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		userActionsLogPaging1 = (Paging) comp.getFellow("userActionsLogPaging1");
		dataDetailsGrid1 = (Listbox) comp.getFellow("dataDetailsGrid1");
		userActionsLogPaging1.setPageSize(_pageSize);
		
		userActionsLogPaging1.addEventListener("onPaging", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				PagingEvent pe = (PagingEvent) event;
				refreshModel(pe.getActivePage());
			}
		});
		

		
		
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		alias = (String) session.getAttribute("alias");
		this.branch1 = (String) this.session.getAttribute("branch");
		this.un = (String) session.getAttribute("un");
		this.pw = (String) session.getAttribute("pwd");
		dataDetailsGrid1.setRows(20);

		dataDetailsGrid1.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				UserActionLog pUserActionsLog = (UserActionLog) data;
				row.setValue(pUserActionsLog);
				row.appendChild(new Listcell(pUserActionsLog.getId() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getBranch() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_id() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_name() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getIp_address() + ""));
				row.appendChild(new Listcell(dft.format(pUserActionsLog.getAction_date().getTime()).toString() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getAct_type() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getEntity_type() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getEntity_id() + ""));
			}
		});
		
		dataDetailsGrid2.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				UserActionLog pUserActionsLog = (UserActionLog) data;
				row.setValue(pUserActionsLog);
				row.appendChild(new Listcell(pUserActionsLog.getBranch() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_id() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_name() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_mac() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_ip() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getType_log() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getLog_text() + ""));
				row.appendChild(new Listcell(dft.format(pUserActionsLog.getV_date().getTime()) + ""));
				row.appendChild(new Listcell(dft.format(pUserActionsLog.getDate_time().getTime()) + ""));
				row.appendChild(new Listcell(pUserActionsLog.getGroup_id() + ""));
			}
		});
		
		dataDetailsGrid3.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				UserActionLog pUserActionsLog = (UserActionLog) data;
				row.setValue(pUserActionsLog);
				row.appendChild(new Listcell(pUserActionsLog.getBranch() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUsername() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getAction() + ""));
				row.appendChild(new Listcell(dft.format(pUserActionsLog.getActiondate().getTime()) + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUserip() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUsermac() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUserhost() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUserport() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getAppip() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getClientver() + ""));
			}
		});
		
		dataDetailsGrid4.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				UserActionLog pUserActionsLog = (UserActionLog) data;
				row.setValue(pUserActionsLog);
				row.appendChild(new Listcell(pUserActionsLog.getId() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getRep_deal_id() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getRep_id() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getPar_name() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getPar_value() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getUser_id() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getComp_name() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getV_date() + ""));
				row.appendChild(new Listcell(pUserActionsLog.getDate_time() + ""));
			}
		});
		
		List<UserActionLog> result1 = UserActionLogService.getUserActionsLog();
		List<UserActionLog> result2 = UserActionLogService.getGeneralLog();
		List<UserActionLog> result3 = UserActionLogService.getLogProtocol();
		List<UserActionLog> result4 = UserActionLogService.getReportHistory();

		System.out.println("Data1 fetched. Rows: " + (result1 != null ? result1.size() : 0));
		System.out.println("Data2 fetched. Rows: " + (result2 != null ? result2.size() : 0));
		System.out.println("Data3 fetched. Rows: " + (result3 != null ? result3.size() : 0));
		System.out.println("Data4 fetched. Rows: " + (result4 != null ? result4.size() : 0));


		ListModelList model1 = new ListModelList(result1);
		ListModelList model2 = new ListModelList(result2);
		ListModelList model3 = new ListModelList(result3);
		ListModelList model4 = new ListModelList(result4);
		
		dataDetailsGrid1.setModel(model1);
		dataDetailsGrid2.setModel(model2);		
		dataDetailsGrid3.setModel(model3);
		dataDetailsGrid4.setModel(model4);

	}

	public void onPaging$userActionsLogPaging1(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	public void onPaging$userActionsLogPaging2(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$userActionsLogPaging3(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	public void onPaging$userActionsLogPaging4(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	private void refreshModel(int activePage) {
		if (userActionsLogPaging1 == null) {
			throw new IllegalStateException("userActionsLogPaging1 is not initialized.");
		}
		if (userActionsLogPaging2 == null) {
			throw new IllegalStateException("userActionsLogPaging2 is not initialized.");
		}
		if (userActionsLogPaging3 == null) {
			throw new IllegalStateException("userActionsLogPaging3 is not initialized.");
		}
		
		PagingListModel model1 = new PagingListModel(activePage, activePage, alias, branch1);
		PagingListModel model2 = new PagingListModel(activePage, activePage, alias, branch1);
		PagingListModel model3 = new PagingListModel(activePage, activePage, alias, branch1);

		
		_totalSize1 = model1.getTotalSize(filter, alias);
		_totalSize2 = model2.getTotalSize(filter, alias);
		_totalSize2 = model3.getTotalSize(filter, alias);

		userActionsLogPaging1.setTotalSize(_totalSize1);
		userActionsLogPaging2.setTotalSize(_totalSize2);
		userActionsLogPaging3.setTotalSize(_totalSize3);

		dataDetailsGrid1.setModel(model1);
		dataDetailsGrid2.setModel(model2);
		dataDetailsGrid2.setModel(model3);

		if (model1.getSize() > 0) {
			current = (com.is.useractionlog.UserActionLog) model1.getElementAt(0);
			SelectEvent evt = new SelectEvent("onSelect", dataDetailsGrid1, dataDetailsGrid1.getSelectedItems());
			Events.sendEvent(evt);
		}
		
		if (model2.getSize() > 0) {
			current = (com.is.useractionlog.UserActionLog) model2.getElementAt(0);
			SelectEvent evt = new SelectEvent("onSelect", dataDetailsGrid2, dataDetailsGrid2.getSelectedItems());
			Events.sendEvent(evt);
		}
		
		if (model3.getSize() > 0) {
			current = (com.is.useractionlog.UserActionLog) model3.getElementAt(0);
			SelectEvent evt = new SelectEvent("onSelect", dataDetailsGrid3, dataDetailsGrid3.getSelectedItems());
			Events.sendEvent(evt);
		}
	}

	public void sort() {
		for (int i = 0; i < dataDetailsGrid1.getListhead().getChildren().size(); i++) {
			Listheader listheader = (Listheader) dataDetailsGrid1.getListhead().getChildren().get(i);
			if (!listheader.getSortDirection().equalsIgnoreCase("natural")) {
				listheader.sort(listheader.getSortDirection().equalsIgnoreCase("ascending"), true);
				return;
			}
		}
	}

	// Omitted...
	public UserActionLog getCurrent() {
		return current;
	}

	public void setCurrent(UserActionLog current) {
		this.current = current;
	}
	
	public void onClick$tbtn_search1() {
		System.out.println("txtusername.getValue(): " + txtusername1.getValue());
		System.out.println("txttodate.getValue(): " + txtdate1.getValue());
		
		java.util.Date utilDate = txtdate1.getValue();
		java.sql.Date sqlDate = null;
		if (utilDate != null) {
		    sqlDate = new java.sql.Date(utilDate.getTime());
		}
		
		List<UserActionLog> result = UserActionLogService.getSearchedUserActionLog(txtusername1.getValue(), sqlDate);
		System.out.println("Data fetched. Rows: " + (result != null ? result.size() : 0));
		ListModelList model = new ListModelList(result);
		dataDetailsGrid1.setModel(model);
	}
	
	public void onClick$tbtn_search2() {
		System.out.println("txtusername2.getValue(): " + txtusername2.getValue());
		System.out.println("txtdate2.getValue(): " + txtdate2.getValue());
		
		java.util.Date utilDate = txtdate2.getValue();
		java.sql.Date sqlDate = null;
		if (utilDate != null) {
		    sqlDate = new java.sql.Date(utilDate.getTime());
		}
		
		List<UserActionLog> result = UserActionLogService.getSearchedUserGeneralLog(txtusername2.getValue(), sqlDate);
		System.out.println("Data fetched. Rows: " + (result != null ? result.size() : 0));
		ListModelList model = new ListModelList(result);
		dataDetailsGrid2.setModel(model);
	}
	
	public void onClick$tbtn_search3() {
		System.out.println("txtusername3.getValue(): " + txtusername3.getValue());
		System.out.println("txtdate3.getValue(): " + txtdate3.getValue());
		
		java.util.Date utilDate = txtdate3.getValue();
		java.sql.Date sqlDate = null;
		if (utilDate != null) {
		    sqlDate = new java.sql.Date(utilDate.getTime());
		}
		
		List<UserActionLog> result = UserActionLogService.getSearchedUserLogProtocol(txtusername3.getValue(), sqlDate);
		System.out.println("Data fetched. Rows: " + (result != null ? result.size() : 0));
		ListModelList model = new ListModelList(result);
		dataDetailsGrid3.setModel(model);
	}
	
	public void onClick$tbtn_search4() {
		System.out.println("txtusername4.getValue(): " + txtusername4.getValue());
		System.out.println("txtdate4.getValue(): " + txtdate4.getValue());
		System.out.println("txtdate4.getValue(): " + txtdate5.getValue());
		
		java.util.Date utilDate1 = txtdate4.getValue();
		java.util.Date utilDate2 = txtdate4.getValue();
		java.sql.Date sqlDate1 = null;
		java.sql.Date sqlDate2 = null;
		if (utilDate1 != null && utilDate2 != null) {
		    sqlDate1 = new java.sql.Date(utilDate1.getTime());		    
		    sqlDate2 = new java.sql.Date(utilDate2.getTime());
		}
		
		List<UserActionLog> result = UserActionLogService.getSearchedReportHistory(txtusername4.getValue(), sqlDate1, sqlDate2);
		System.out.println("Data fetched. Rows: " + (result != null ? result.size() : 0));
		ListModelList model = new ListModelList(result);
		dataDetailsGrid4.setModel(model);
	}
	
	public void onDoubleClick$dataDetailsGrid1() {
	    System.out.println("onDoubleClick$dataDetailsGrid1$grd 2 marta bosildi");
	    
	    UserActionLog selectedLog = (UserActionLog) dataDetailsGrid1.getSelectedItem().getValue();
	    
	    System.out.println(selectedLog.getId() + " " + selectedLog.getBranch() + " " + 
	                      selectedLog.getUser_id() + " " + selectedLog.getUser_name() + " " +
	                      selectedLog.getIp_address() + " " + selectedLog.getAction_date() + " " + 
	                      selectedLog.getAct_type() + " " + selectedLog.getEntity_type() + " " +
	                      selectedLog.getEntity_id());
	    
	        detailsWindow1$id.setValue(selectedLog.getId());
	        detailsWindow1$branch.setValue(selectedLog.getBranch());
	        detailsWindow1$user_id.setValue(selectedLog.getUser_id());
	        detailsWindow1$user_name.setValue(selectedLog.getUser_name());
	        detailsWindow1$ip_address.setValue(selectedLog.getIp_address());
	        detailsWindow1$action_date.setValue((java.util.Date) (selectedLog.getAction_date() != null ? 
	        		selectedLog.getAction_date() : ""));
	        detailsWindow1$act_type.setValue(selectedLog.getAct_type());
	        detailsWindow1$entity_type.setValue(selectedLog.getEntity_type());
	        detailsWindow1$entity_id.setValue(selectedLog.getEntity_id());
    
	    detailsWindow1.setVisible(true);
	    setCurrent();
	}
	
	public void onDoubleClick$dataDetailsGrid2() {
	    System.out.println("onDoubleClick$dataDetailsGrid2 2 marta bosildi");
	    
	    UserActionLog selectedLog = (UserActionLog) dataDetailsGrid2.getSelectedItem().getValue();
	    
	    System.out.println(selectedLog.getId() + " " + selectedLog.getBranch() + " " + 
	                      selectedLog.getUser_id() + " " + selectedLog.getUser_name() + " " +
	                      selectedLog.getIp_address() + " " + selectedLog.getAction_date() + " " + 
	                      selectedLog.getAct_type() + " " + selectedLog.getEntity_type() + " " +
	                      selectedLog.getEntity_id());
	    
	        detailsWindow2$branch.setValue(selectedLog.getBranch());
	        detailsWindow2$user_id.setValue(selectedLog.getUser_id());
	        detailsWindow2$user_name.setValue(selectedLog.getUser_name());
	        detailsWindow2$user_mac.setValue(selectedLog.getUser_mac());
	        detailsWindow2$user_ip.setValue(selectedLog.getIp_address());
	        detailsWindow2$type_log.setValue(selectedLog.getType_log());
	        detailsWindow2$log_text.setValue(selectedLog.getLog_text());
	        detailsWindow2$v_date.setValue((java.util.Date) (selectedLog.getV_date() != null ? 
	        		selectedLog.getV_date() : ""));
	        detailsWindow2$date_time.setValue((java.util.Date) (selectedLog.getDate_time() != null ? 
	        		selectedLog.getDate_time() : ""));
	        detailsWindow2$group_id.setValue(selectedLog.getGroup_id());

		    detailsWindow2.setVisible(true);
	    
	    setCurrent();
	}
	
	public void onDoubleClick$dataDetailsGrid3() {
	    System.out.println("onDoubleClick$dataDetailsGrid3 2 marta bosildi");
	    
	    UserActionLog selectedLog = (UserActionLog) dataDetailsGrid3.getSelectedItem().getValue();
	    
	    System.out.println(selectedLog.getId() + " " + selectedLog.getBranch() + " " + 
	                      selectedLog.getUser_id() + " " + selectedLog.getUser_name() + " " +
	                      selectedLog.getIp_address() + " " + selectedLog.getAction_date() + " " + 
	                      selectedLog.getAct_type() + " " + selectedLog.getEntity_type() + " " +
	                      selectedLog.getEntity_id());
	    
	        detailsWindow3$branch.setValue(selectedLog.getBranch());
	        detailsWindow3$user_name.setValue(selectedLog.getUser_name());
	        detailsWindow3$action.setValue(selectedLog.getAction());
	        detailsWindow3$actiondate.setValue((java.util.Date) (selectedLog.getActiondate() != null ? 
	        		selectedLog.getActiondate() : ""));
	        detailsWindow3$user_ip.setValue(selectedLog.getIp_address());
	        detailsWindow3$user_mac.setValue(selectedLog.getUser_mac());
	        detailsWindow3$userhost.setValue(selectedLog.getUserhost());
	        detailsWindow3$userport.setValue(selectedLog.getUserport());
	        detailsWindow3$appip.setValue(selectedLog.getAppip());
	        detailsWindow3$clientver.setValue(selectedLog.getClientver());
		    detailsWindow3.setVisible(true);

	    setCurrent();
	}
	
	public void onDoubleClick$dataDetailsGrid4() {
	    System.out.println("onDoubleClick$dataDetailsGrid4 2 marta bosildi");
	    
	    UserActionLog selectedLog = (UserActionLog) dataDetailsGrid4.getSelectedItem().getValue();
	    
	    System.out.println(selectedLog.getId() + " " + selectedLog.getBranch() + " " + 
	                      selectedLog.getUser_id() + " " + selectedLog.getUser_name() + " " +
	                      selectedLog.getIp_address() + " " + selectedLog.getAction_date() + " " + 
	                      selectedLog.getAct_type() + " " + selectedLog.getEntity_type() + " " +
	                      selectedLog.getEntity_id());
	    
	        detailsWindow4$id.setValue(selectedLog.getId());
	        detailsWindow4$rep_deal_id.setValue(selectedLog.getRep_deal_id());
	        detailsWindow4$rep_id.setValue(selectedLog.getRep_id());
	        detailsWindow4$par_name.setValue(selectedLog.getPar_name());
	        detailsWindow4$par_value.setValue(selectedLog.getPar_value());
	        detailsWindow4$user_id.setValue(selectedLog.getUser_id());
	        detailsWindow4$comp_name.setValue(selectedLog.getComp_name());
	        detailsWindow4$v_date.setValue((java.util.Date) (selectedLog.getV_date() != null ? 
	        		selectedLog.getV_date() : ""));
	        detailsWindow4$date_time.setValue((java.util.Date) (selectedLog.getDate_time() != null ? 
	        		selectedLog.getDate_time() : ""));
		    detailsWindow4.setVisible(true);

	    setCurrent();
	}
	
	public void onClick$btn_exit1$detailsWindow1() {
		detailsWindow1.setVisible(false);
	}

	public void onClick$btn_exit2$detailsWindow2() {
		detailsWindow2.setVisible(false);
	}
	
	public void onClick$btn_exit3$detailsWindow3() {
		detailsWindow3.setVisible(false);
	}
	
	public void onClick$btn_exit4$detailsWindow4() {
		detailsWindow4.setVisible(false);
	}
	
	public void onClick$btn_first() {
		dataDetailsGrid1.setSelectedIndex(0);
		sendSelEvt(true);
	}

	public void onClick$btn_last() {
		dataDetailsGrid1.setSelectedIndex(model1.getSize() - 1);
		sendSelEvt(true);
	}

	public void onClick$btn_prev() {
		if (dataDetailsGrid1.getSelectedIndex() != 0) {
			dataDetailsGrid1.setSelectedIndex(dataDetailsGrid1.getSelectedIndex() - 1);
			sendSelEvt(true);
		}
	}

	public void onClick$btn_next() {
		if (dataDetailsGrid1.getSelectedIndex() != (model1.getSize() - 1)) {
			dataDetailsGrid1.setSelectedIndex(dataDetailsGrid1.getSelectedIndex() + 1);
			sendSelEvt(true);
		}
	}

	private void sendSelEvt(Boolean sendEvt) {
		if (dataDetailsGrid1.getSelectedIndex() == 0) {
			btn_first1.setDisabled(true);
			btn_prev1.setDisabled(true);
		} else {
			btn_first1.setDisabled(false);
			btn_prev1.setDisabled(false);
		}
		if (dataDetailsGrid1.getSelectedIndex() == (model1.getSize() - 1)) {
			btn_next1.setDisabled(true);
			btn_last1.setDisabled(true);
		} else {
			btn_next1.setDisabled(false);
			btn_last1.setDisabled(false);
		}
		if (sendEvt) {
			SelectEvent evt = new SelectEvent("onSelect", dataDetailsGrid1, dataDetailsGrid1.getSelectedItems());
			Events.sendEvent(evt);
		}
		setCurrent();
	}

	private void setCurrent() {
		if (current != null) {
			while (current.getAddinfo().size() < 9) {
				current.getAddinfo().add(null);
			}
		}
	}
}