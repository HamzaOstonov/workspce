package com.is.account.spec;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.account.model.Account;
import com.is.base.Action;
import com.is.base.CommonDictionaries;
import com.is.base.paging.PagingListModel;
import com.is.clients.controllers.renderers.HistoryRenderer;
import com.is.clients.services.ClientDictionaries;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;

import lombok.Getter;
import lombok.Setter;

public class SpecAccViewCtrl extends GenericForwardComposer {
	private Window spec_history;
	private Listbox dataGrid,spec_history$history;
	private Paging contactPaging;
	private Div grd, addgrd, frm, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_del;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back;
	private Toolbarbutton btn_save;
	private Toolbar tb;
	private Textbox branch, 
					acc, 
					prim,
					acc_bal_text,
					currency_text,
					id_specialText,
					code_currencyText,
					branch_slaveText;
	private RefCBox id_special, 
					branch_slave, 
					acc_bal, 
					code_currency, 
					currency;
	
	private Textbox fbranch, 
					facc, 
					fprim,
					facc_bal_text,
					fcurrency_text,
					fid_specialText,
					fcode_currencyText,
					fbranch_slaveText;
	private RefCBox fid_special, 
					fbranch_slave,
					facc_bal, 
					fcode_currency, 
					fcurrency;
	
	private Textbox abranch, 
					aacc, 
					aprim,
					aacc_bal_text,
					acurrency_text,
					aid_specialText,
					acode_currencyText,
					abranch_slaveText;
	private RefCBox aid_special, 
					abranch_slave,
					aacc_bal, 
					acode_currency, 
					acurrency;
	
	private Decimalbox sum_acc, asum_acc, fsum_acc;
	
	private Paging specialaccPaging;
	private int _pageSize = 15;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;

	private PagingListModel<SpecAcc> model = null;
	private SpecAccDao specAccDao;
	private ListModelList lmodel = null;
	private AnnotateDataBinder specAccBinder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	@Getter @Setter private SpecAcc current = new SpecAcc();
	@Getter @Setter private SpecAcc newSpecAcc = new SpecAcc();
	@Getter @Setter public SpecAccFilter filter = new SpecAccFilter();
	private Account curacc = new Account();
	private String accountId;
	private String clientId;
	private String alias;
	private String branch1;
	private String username;
	private String pwd;
	private final String ACTION_NEW = "new";
	private final String ACTION_UPDATE = "update";
	private final String ACTION_DELETE = "delete";
	private final String ACTION_FILTER = "filter";

	public SpecAccViewCtrl() {
		super('$', false, false);
	}

	/**
 *
 *
 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		specAccBinder = new AnnotateDataBinder(comp);
		specAccBinder.bindBean("current", this.current);
		specAccBinder.bindBean("newSpecAcc", this.newSpecAcc);
		specAccBinder.bindBean("filter", this.filter);
		specAccBinder.loadAll();
		String[] parameter = null;
		// if (parameter != null) {
		// _pageSize = Integer.parseInt(parameter[0]) / 36;
		// dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		// }
		parameter = (String[]) param.get("acc");
		if (parameter != null) {
			accountId = parameter[0];
		}
		parameter = (String[]) param.get("clientId");
		if (parameter != null) {
			clientId = parameter[0];
		}
		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");
		username = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		 
		//specAccDao = SpecAccDao.getInstance(alias);
		specAccDao = SpecAccDao.getInstance(alias, username, pwd);
		
		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				SpecAcc pspecialacc = (SpecAcc) data;

				row.setValue(pspecialacc);

				row.appendChild(new Listcell(pspecialacc
						.getId_special()));
				row.appendChild(new Listcell(pspecialacc.getAcc()));
				row.appendChild(new Listcell(pspecialacc.getName()));
				row.appendChild(new Listcell(pspecialacc.getCode_currency()));
				row.appendChild(new Listcell(
						pspecialacc.getSum_acc() != null ? pspecialacc
								.getSum_acc().toPlainString() : "0"));

			}
		});
		
		setModels();
		if(accountId == null) {
			refreshModel(_startPageNumber);
		}
		spec_history$history.setItemRenderer(new HistoryRenderer());
		//действи€ 
		List<Action> aa ;
		int userId = (Integer) session.getAttribute("uid");
		aa=SpecAccService.getAction(alias, userId);
		for(Action a : aa){
	        if (a.getId()==1)
	        	btn_add.setDisabled(false);
	        else if (a.getId()==2)
	        	btn_del.setDisabled(false);
        }
        
	}
	
	public void init(Account account) {
		clientId=account.getClient();
		accountId = account.getId();
		curacc = account;
		filter = new SpecAccFilter();
		filter.setAcc(accountId);
		refreshModel(_startPageNumber);
	}
	
	private void setModels(){
		List<RefData> accBalList = null;
		List<RefData> currencies = null;
		List<RefData> specAcc = null;
		List<RefData> mfo = null; 
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			accBalList = CommonDictionaries.getAccBal(c,alias);
			currencies = CommonDictionaries.getCurrencies(c,alias);
			specAcc = SpecAccService.getSpecAccTypes(c);
			mfo = ClientDictionaries.getMfo(c,alias); 
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		acc_bal.setModel(new ListModelList(accBalList));
		facc_bal.setModel(new ListModelList(accBalList));
		aacc_bal.setModel(new ListModelList(accBalList));
		
		currency.setModel(new ListModelList(currencies));
		fcurrency.setModel(new ListModelList(currencies));
		acurrency.setModel(new ListModelList(currencies));
		
		code_currency.setModel(new ListModelList(currencies));
		fcode_currency.setModel(new ListModelList(currencies));
		acode_currency.setModel(new ListModelList(currencies));
		
		id_special.setModel(new ListModelList(specAcc));
		fid_special.setModel(new ListModelList(specAcc));
		aid_special.setModel(new ListModelList(specAcc));
		
		branch_slave.setModel(new ListModelList(mfo));
		fbranch_slave.setModel(new ListModelList(mfo));
		abranch_slave.setModel(new ListModelList(mfo));
	}
	public void onPaging$specialaccPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		specialaccPaging.setPageSize(_pageSize);
		specAccDao.setFilter(filter);
		model = new PagingListModel<SpecAcc>(activePage, _pageSize, specAccDao);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize();
			_needsTotalSizeUpdate = false;
		}

		specialaccPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (SpecAcc) model.getElementAt(0);
			sendSelEvt();
		}
	}

	public void onDoubleClick$dataGrid$grd() {
		if(curacc != null) {
			setAccountInfo(current);
		}
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		specAccBinder.loadAll();
		hideAll();
		btn_save.setImage("/images/save.png");
		btn_save.setLabel(Labels.getLabel("save"));
		btn_save.setAttribute("action", ACTION_UPDATE);
		btn_save.setVisible(false);
		btn_back.setVisible(true);
		frm.setVisible(true);
	}

	public void onClick$btn_add() {
		newSpecAcc = new SpecAcc();
		newSpecAcc.setBranch(branch1);
		newSpecAcc.setAcc(accountId);
		//if(curacc != null) {
			setAccountInfo(newSpecAcc);
		//}
		specAccBinder.loadAll();
		hideAll();
		btn_save.setImage("/images/save.png");
		btn_save.setLabel(Labels.getLabel("save"));
		btn_save.setAttribute("action", ACTION_NEW);
		btn_save.setVisible(true);
		addgrd.setVisible(true);
	}
	
	public void onClick$btn_back() {
		if (!grd.isVisible()) {
			hideAll();
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

	public void onClick$btn_del() throws Exception {
		specAccDao.remove(current);
		refreshModel(_startPageNumber);
	}
	
	
	public void onClick$btn_search() {
		hideAll();
		btn_save.setImage("/images/search.png");
		btn_save.setLabel(Labels.getLabel("search"));
		btn_save.setAttribute("action", ACTION_FILTER);
		btn_save.setVisible(true);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() throws Exception {
		String buttonAction = (String) btn_save.getAttribute("action");
		if(buttonAction.equals(ACTION_NEW)) {
			if(CheckNull.isEmpty(newSpecAcc.getId_special()) 
					|| CheckNull.isEmpty(newSpecAcc.getCode_currency())) {
				alert("«аполните пол€: тип характеристики, валюта");
				return;
			}
			specAccDao.create(newSpecAcc);
			refreshModel(_startPageNumber);
			onClick$btn_back();
		} else if(buttonAction.equals(ACTION_UPDATE)) {
			specAccDao.update(current);
		} else if(buttonAction.equals(ACTION_DELETE)) {
			//specAccDao.remove(current);
		} else if(buttonAction.equals(ACTION_FILTER)) {
			refreshModel(_startPageNumber);
			onClick$btn_back();
		}
//		onClick$btn_back();
//		refreshModel(_startPageNumber);
//		SelectEvent evt = new SelectEvent("onSelect", dataGrid,
//				dataGrid.getSelectedItems());
//		Events.sendEvent(evt);

	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new SpecAccFilter();
		}
		onClick$btn_back();
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		refreshModel(_startPageNumber);
	}
	public void onClick$btn_history(){
		spec_history$history.setModel(new ListModelList(SpecAccService.getHistory(accountId, branch1, alias)));
		spec_history.setVisible(true);
	}
	private void setAccountInfo(SpecAcc specAcc) {
		specAcc.setClient(curacc.getClient());
		specAcc.setName(curacc.getName());
		specAcc.setId_order(curacc.getId_order());
		specAcc.setAcc_bal(curacc.getAcc_bal());
		specAcc.setCurrency(curacc.getCurrency());
	}
	private void hideAll() {
		grd.setVisible(false);
		frm.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_save.setVisible(false);
	}
}
