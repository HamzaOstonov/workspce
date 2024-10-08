package com.is.card_to_card;

import java.text.SimpleDateFormat;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
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
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.account.Account;
import com.is.customer_.core.ReferenceDictionary;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;

public class CardtcViewCtrl extends GenericForwardComposer {
	private Window chacc;
	private Div frm;
	private Listbox dataGrid, chacc$acc, dataGrid2;
	private Paging contactPaging;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Toolbarbutton btn_last;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, btn_save;
	private Toolbar tb;
	private Textbox id, acc_template_id, acc_mfo, account, acc_name, chacc$acc_filter_mask;
	private Textbox aid, aacc_template_id, aacc_mfo, aaccount, aacc_name;
	private Textbox fid, facc_template_id, facc_mfo, faccount, facc_name, mod_type, txbId_client, txbCard,
			txbName, txbPinfl;
	private RefCBox rcb_card_from, rcb_card_to;

	private Paging traccPaging;
	private int _pageSize = 14;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String mbranch, alias, branch;

	// public TrAcc current;
	public TrAccFilter filter = new TrAccFilter();

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private TrAcc current = new TrAcc();
	private Account currentacc = new Account();

	public Account getCurrentacc() {
		return currentacc;
	}

	public void setCurrentacc(Account currentacc) {
		this.currentacc = currentacc;
	}

	public CardtcViewCtrl() {
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
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		alias = (String) session.getAttribute("alias");
		
		//this.uid = (Integer) this.session.getAttribute("uid");
		//this.un = (String) this.session.getAttribute("un");
		//this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");

		
		String[] group_id = (String[]) this.param.get("group_id");
		// if (parameter!=null){
		// _pageSize = Integer.parseInt( parameter[0])/36;
		dataGrid.setRows(20);
		// }
		
		rcb_card_from.setModel(new ListModelList(CardtcService.getCardTypes(alias)));
		rcb_card_to.setModel(new ListModelList(CardtcService.getCardTypes(alias)));

		// mbranch = (String) session.getAttribute("branch");
		// if
		// (mod_type.getValue().compareTo("usual")==0)filter.setBranch(mbranch);
		//
		//
		// dataGrid.setItemRenderer(new ListitemRenderer(){
		// @SuppressWarnings("unchecked")
		// public void render(Listitem row, Object data) throws Exception {
		// TrAcc pTrAcc = (TrAcc) data;
		//
		// row.setValue(pTrAcc);
		// /*
		// row.appendChild(new Listcell(pTrAcc.getId()));
		// row.appendChild(new Listcell(pTrAcc.getBranch()));
		// row.appendChild(new Listcell(pTrAcc.getAcc_template_id()));
		// */
		// row.appendChild(new Listcell(pTrAcc.getAcc_mfo()));
		// row.appendChild(new Listcell(pTrAcc.getAccount()));
		// row.appendChild(new Listcell(pTrAcc.getAcc_name()));
		//
		//
		// }});

		// refreshModel(_startPageNumber);

		// chacc$acc.setItemRenderer(new ListitemRenderer(){
		// @SuppressWarnings("unchecked")
		// public void render(Listitem row, Object data) throws Exception {
		// Account pAccount = (Account) data;
		// row.setValue(pAccount);
		// row.appendChild(new Listcell(pAccount.getBranch()));
		// row.appendChild(new Listcell(pAccount.getId()));
		// row.appendChild(new Listcell(pAccount.getName()));
		//
		// }});

	}

	public void onPaging$traccPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		traccPaging.setPageSize(_pageSize);
		model = new PagingListModel(activePage, _pageSize, filter, alias);

		// if(_needsTotalSizeUpdate) {
		_totalSize = model.getTotalSize(filter, alias);
		// _needsTotalSizeUpdate = false;
		// }

		traccPaging.setTotalSize(_totalSize);

		dataGrid.setModel((ListModel) model);
		if (model.getSize() > 0) {
			this.current = (TrAcc) model.getElementAt(0);
			sendSelEvt();
		}
	}

	// Omitted...
	public TrAcc getCurrent() {
		return current;
	}

	public void setCurrent(TrAcc current) {
		this.current = current;
	}

	public void onDoubleClick$dataGrid$grd() {
		grd.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		if ((current != null) && (current.getAccount() == null)) {
			current.setAccount("");
		}
		// if(!current.getAcc_mfo().equals(current.getBranch()))

		// if((!current.getBranch().equals(current.getAcc_mfo()))&&(mbranch.compareTo(ConnectionPool.getValue("HO",
		// alias))!=0))
		if ((current != null) && (!current.getBranch().equals(current.getAcc_mfo()))
				&& (mbranch.compareTo(ConnectionPool.getValue("HO", alias)) != 0)
				&& mod_type.getValue().compareTo("usual") == 0) {
			btn_save.setDisabled(true);
			acc_mfo.setDisabled(true);
			account.setDisabled(true);
			acc_name.setDisabled(true);
		} else {
			btn_save.setDisabled(false);
			acc_mfo.setDisabled(false);
			account.setDisabled(false);
			acc_name.setDisabled(false);
		}
		account.setDisabled((current != null) && (current.getAccount().toUpperCase().contains("ACC")));
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
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$tbtn_search() {


		if (filter.getBranch() == null)
			filter.setBranch(branch);
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		//filter.setFilter_type("N");
		//this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);

		ISLogger.getLogger().error(
				"onClick$tbtn_search end! ");
	}
	
	public void onClick$btn_save() {
		try {

			if (addgrd.isVisible()) {
				CardtcService.create(new TrAcc(

						0, // aid.getValue(),
						alias, // abranch.getValue(),
						Integer.parseInt(aacc_template_id.getValue()), aacc_mfo.getValue(), aaccount.getValue(),
						aacc_name.getValue()), alias);
				CheckNull.clearForm(addgrd);
				frmgrd.setVisible(true);
				addgrd.setVisible(false);
				fgrd.setVisible(false);
			} else if (fgrd.isVisible()) {
				filter = new TrAccFilter();

				// filter.setId(fid.getValue());
				filter.setBranch(fbranch.getValue());
				if (mod_type.getValue().compareTo("usual") == 0)
					filter.setBranch(mbranch);
				// filter.setAcc_template_id(Integer.parseInt(
				// facc_template_id.getValue()));
				filter.setAcc_mfo(facc_mfo.getValue());
				filter.setAccount(faccount.getValue());
				filter.setAcc_name(facc_name.getValue());

			} else {

				// current.setId(Integer.parseInt(id.getValue()));
				// current.setBranch(branch.getValue());
				// current.setAcc_template_id(Integer.parseInt(acc_template_id.getValue()));
				current.setAcc_mfo(acc_mfo.getValue());
				current.setAccount(account.getValue());
				current.setAcc_name(acc_name.getValue());
				CardtcService.update(current, alias);
			}

			onClick$btn_back();
			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}

	}

	public void onClick$btn_cancel() {
		if (fgrd.isVisible()) {
			filter = new TrAccFilter();
			if (mod_type.getValue().compareTo("usual") == 0)
				filter.setBranch(mbranch);
		}
		onClick$btn_back();
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		CheckNull.clearForm(addgrd);
		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

	public void onDoubleClick$account() {
		chacc$acc.setModel(
				new BindingListModelList(CardtcService.getAccount(current, "", alias, acc_mfo.getValue()), false));
		chacc.setVisible(true);
	}

	public void onCtrlKey$account(Event event) {
		onDoubleClick$account();
		// chacc$acc.setModel(new
		// BindingListModelList(TrAccService.getAccount(current,alias),false));
		// chacc.setVisible(true);
	}

	public void onDoubleClick$acc$chacc() {
		account.setText(currentacc.getId());
		acc_mfo.setText(currentacc.getBranch());
		acc_name.setText(currentacc.getName());
		chacc.setVisible(false);
	}

	public void onClick$acc_filter$chacc() {
		chacc$acc.setModel(new BindingListModelList(
				CardtcService.getAccount(current, chacc$acc_filter_mask.getValue(), alias, acc_mfo.getValue()), false));
	}

}
