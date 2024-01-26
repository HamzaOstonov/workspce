package com.is.account;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.account.model.Account;
import com.is.account.model.AccountFilter;
import com.is.account.model.NibbdParam;
import com.is.account.model.SAccount;
import com.is.account.sevice.AccountDao;
import com.is.account.sevice.AccountDictionaries;
import com.is.account.sevice.AccountService;
import com.is.account.spec.SpecAccViewCtrl;
import com.is.account.util.AccountEnum;
import com.is.account.util.AccountUtil;
import com.is.base.Dao;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.clients.controllers.renderers.HistoryRenderer;
import com.is.clients.models.ClientJ;
import com.is.nibbd.NibbdController;
import com.is.nibbd.util.NibbdQueries;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
public class AccountViewCtrl extends GenericForwardComposer {

	private static Logger logger = Logger.getLogger(AccountViewCtrl.class);
	private Window nibbd_wnd, nibbd_modal, spec_acc_wnd, wind_nibbd;
	private Div filter_div, new_acc, spec_acc_parent;
	private Tabbox acc_tabs;
	private Div grd;
	private Listbox dataGrid, history;
	private Toolbar action_bar;
	private Toolbarbutton btn_last, btn_next, btn_prev, btn_first, btn_back, btn_add, wind_nibbd$btn_send;
	private Textbox id_order, name, acc_group_idText;
	private RefCBox state, acc_bal, currency, sgn, bal, acc_group_id;
	private RefCBox fstate, facc_bal, fcurrency, fsgn, fbal, facc_group_id;
	private Textbox aclient, aacc_group_idText, aid_order, aname;
	private RefCBox astate, aacc_bal, acurrency, asgn, abal, aacc_group_id, wind_nibbd$close_type_name, wind_nibbd$lock_type_name, wind_nibbd$lock_source_name, wind_nibbd$locked_regnum_name, wind_nibbd$locked_regnum_nibbd;
	private Textbox currencyValue, fcurrencyValue, acurrencyValue, wind_nibbd$close_type, wind_nibbd$lock_type, wind_nibbd$lock_source;
	private Textbox facc_bal_text, fclient, fclient_name;
	private Row wind_nibbd$codeBankRow, wind_nibbd$closeTypeRow, wind_nibbd$lockTypeRow, wind_nibbd$lockSourceRow,
			wind_nibbd$lockDocNRow, wind_nibbd$lockDocDRow, wind_nibbd$unLockLockId0Row, wind_nibbd$unLockDocNRow,
			wind_nibbd$unLockDocDRow, wind_nibbd$unLockLockIdRow;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat dfWithTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private String alias, branch1;
	private String un;
	private String pw;
	private String clientId;
	private String clientName;

	public static final String ACCOUNT_EVENT = "onNotifyAccount";

	private Map<Integer, String> actionsMap;
	private Map<String, String> statesMap;
	private List<SAccount> saccList;
	private AccountService accountService;
	private Dao<Account> accountDao;
	private AccountDictionaries accountDictionaries;
	@Getter
	@Setter
	public Account current = new Account();
	@Getter
	@Setter
	public Account currentListItem = new Account();
	@Getter
	@Setter
	private AccountFilter filter = new AccountFilter();
	@Getter
	@Setter
	private Account newAcc = new Account();
	private ClientJ currentClient;
	private String selectedAccount;
	public NibbdParam nibbdparam = new NibbdParam();

	public AccountViewCtrl() {
		super('$', false, false);
	}

	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("currentListItem", this.currentListItem);
		binder.bindBean("filter", this.filter);
		binder.bindBean("newAcc", this.newAcc);
		binder.bindBean("nibbdparam", this.nibbdparam);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");

		if (parameter != null) {
			dataGrid.setRows(Integer.parseInt(parameter[0]) / 36);
		}

		alias = (String) session.getAttribute("alias");
		branch1 = (String) session.getAttribute("branch");
		un = session.getAttribute("un").toString();
		pw = session.getAttribute("pwd").toString();
		initServices();

		parameter = (String[]) param.get("clientId");
		if (parameter != null) {
			clientId = parameter[0];
			// clientName = accountService.getClientname(clientId);
		}
		if (clientId == null) {
			final Execution execution = Executions.getCurrent();
			clientId = (String) execution.getArg().get("clientId");
		}
		if (clientId != null)
			clientName = accountService.getClientname(branch1, clientId);

		dataGrid.setItemRenderer(new ListitemRenderer() {
			public void render(Listitem row, Object data) throws Exception {
				Account pAccount = (Account) data;

				row.setValue(pAccount);

				row.appendChild(new Listcell(pAccount.getBranch()));
				row.appendChild(new Listcell(pAccount.getId()));
				row.appendChild(new Listcell(pAccount.getName()));
				row.appendChild(new Listcell(pAccount.getDt().toPlainString()));
				row.appendChild(new Listcell(pAccount.getCt().toPlainString()));
				row.appendChild(new Listcell(pAccount.getS_out().toPlainString()));
				row.appendChild(new Listcell(
						!CheckNull.isEmpty(pAccount.getL_date()) ? df.format(pAccount.getL_date()) : null));
				row.appendChild(new Listcell(statesMap.get(Integer.toString(pAccount.getState()))));
				if (selectedAccount != null && selectedAccount.equals(pAccount.getId())) {
					dataGrid.setSelectedItem(row);
					setCurrentListItem(pAccount);
					initActionBar();
				}
			}
		});
		history.setItemRenderer(new HistoryRenderer());

		Account acc = new Account();
		acc.setSign_registr(1);
		acc.setState(0);
		Map<Integer, String> checkToOpen = accountService.getAvailableActions(acc);
		btn_add.setVisible(checkToOpen.containsKey(1));
		// if (clientId == null)
		refreshModel();
	}

	private void initServices() {
		accountDao = AccountDao.getInstance(alias);
		accountService = AccountService.getInstance(branch1, alias, un, pw);
		accountDictionaries = AccountDictionaries.instance(alias);
		setModels();
	}

	public void init(ClientJ client) {
		this.currentClient = client;
		clientId = client.getId_client();
		aacc_bal.setModel(new ListModelList(AccountUtil.sortByClientType(currentClient.getCode_type(), saccList)));
		acc_bal.setModel(new ListModelList(AccountUtil.sortByClientType(currentClient.getCode_type(), saccList)));
		clientName = accountService.getClientname(client.getBranch(), client.getId_client());
		refreshModel();
		hideAll();
		grd.setVisible(true);
	}

	private void setModels() {
		saccList = accountDictionaries.getSaccList();
		statesMap = accountDictionaries.getStatesMap();
		actionsMap = accountDictionaries.getActionsMap();
		state.setModel(new ListModelList(accountDictionaries.getStates()));
		astate.setModel(new ListModelList(accountDictionaries.getStates()));
		fstate.setModel(new ListModelList(accountDictionaries.getStates()));

		currency.setModel(new ListModelList(accountDictionaries.getCurrencies()));
		acurrency.setModel(new ListModelList(accountDictionaries.getCurrencies()));
		fcurrency.setModel(new ListModelList(accountDictionaries.getCurrencies()));

		acc_group_id.setModel(new ListModelList(accountDictionaries.getAccGroups()));
		aacc_group_id.setModel(new ListModelList(accountDictionaries.getAccGroups()));
		facc_group_id.setModel(new ListModelList(accountDictionaries.getAccGroups()));

		acc_bal.setModel(new ListModelList(accountDictionaries.getAccBalList()));
		aacc_bal.setModel(new ListModelList(accountDictionaries.getAccBalList()));
		facc_bal.setModel(new ListModelList(accountDictionaries.getAccBalList()));

		sgn.setModel(new ListModelList(accountDictionaries.getSgnList()));
		asgn.setModel(new ListModelList(accountDictionaries.getSgnList()));
		fsgn.setModel(new ListModelList(accountDictionaries.getSgnList()));

		bal.setModel(new ListModelList(accountDictionaries.getBalList()));
		abal.setModel(new ListModelList(accountDictionaries.getBalList()));
		fbal.setModel(new ListModelList(accountDictionaries.getBalList()));
	}

	private void refreshModel() {
		if (clientId != null) {
			filter.setClient(clientId);
		}
		accountDao.setFilter(filter);
		dataGrid.setModel(new BindingListModelList(accountDao.getList(), true));
	}

	public void onClick$btn_refresh() {
		if (grd.isVisible()) {
			refreshModel();
		} else if (acc_tabs.isVisible() && acc_tabs.getSelectedIndex() == 0) {
			onDoubleClick$dataGrid$grd();
		}
	}

	public void onDoubleClick$dataGrid$grd() {
		hideAll();
		/*
		 * if (currentListItem == null) logger.error(
		 * "not err Current List item "); else if (currentListItem.getId() ==
		 * null) logger.error("not err currentListItem.getId() is null "); else
		 * logger.error("not err currentListItem.getId()= " +
		 * currentListItem.getId()); if (current == null) logger.error(
		 * "not err Current "); if (selectedAccount == null) logger.error(
		 * "not err SelectedAccount "); if (currentClient == null) logger.error(
		 * "not err currentClient ");
		 */

		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		acc_tabs.setVisible(true);
		if (currentListItem == null) {
			return;
		}
		if (current == null)
			return;
		if (selectedAccount != null) {
			setCurrent(accountDao.getItemByStringId(branch1, selectedAccount));
			selectedAccount = null;
		} else {
			setCurrent(accountDao.getItemByStringId(branch1, currentListItem.getId()));
			// Возвращаемся к списку счетов
			if (current == null) {
				init(this.currentClient);
				return;
			}
		}
		this.current.setClient_name(clientName);
		Events.sendEvent(ACCOUNT_EVENT, self, current);
		binder.loadAll();
		initActionBar();
	}

	public void onSelect$acc_tabs() {
		switch (acc_tabs.getSelectedIndex()) {
		case 1:
			initSpecAcc();
			break;
		case 2:
			history.setModel(new ListModelList(accountService.getHistory(current.getId())));
		default:
			break;
		}
	}
	//
	// private void setTabs(){
	// incl_spec.setSrc("account_spec.zul?acc="+current.getId()+"&clientId="+current.getClient());
	// history.setModel(new
	// ListModelList(accountService.getHistory(current.getId())));
	// }

	public void onClick$btn_back() {
		if (!grd.isVisible()) {
			acc_tabs.setVisible(false);
			filter_div.setVisible(false);
			new_acc.setVisible(false);
			grd.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else {
			onDoubleClick$dataGrid$grd();
		}
	}

	public void onClick$btn_first() {
		dataGrid.setSelectedIndex(0);
		sendSelEvt();
	}

	public void onClick$btn_last() {
		if (dataGrid.getSelectedIndex() > 0) {
			dataGrid.setSelectedIndex(dataGrid.getItems().size() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_prev() {
		if (dataGrid.getSelectedIndex() > 0) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() - 1);
			sendSelEvt();
		}
	}

	public void onClick$btn_next() {
		if (dataGrid.getSelectedIndex() != (dataGrid.getItems().size() - 1)) {
			dataGrid.setSelectedIndex(dataGrid.getSelectedIndex() + 1);
			sendSelEvt();
		}
	}

	protected void sendSelEvt() {
		if (dataGrid.getSelectedIndex() == 0) {
			btn_first.setDisabled(true);
			btn_prev.setDisabled(true);
		} else {
			btn_first.setDisabled(false);
			btn_prev.setDisabled(false);
		}
		if (dataGrid.getSelectedIndex() == (dataGrid.getItems().size() - 1)) {
			btn_next.setDisabled(true);
			btn_last.setDisabled(true);
		} else {
			btn_next.setDisabled(false);
			btn_last.setDisabled(false);
		}
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
		onDoubleClick$dataGrid$grd();
	}

	private Textbox aclient_name;

	// -- кнопка показать ниббд
	public void onClick$btn_nibbd() {
		if (current.getId() != null) {
			wind_nibbd.setVisible(true);
		} else {
			wind_nibbd.setVisible(true);
		}
	}

	public void onClick$btn_add() {
		hideAll();
		ZkUtils.clearForm(new_acc);
		new_acc.setVisible(true);
		newAcc = new Account();
		if (currentClient != null) {
			newAcc.setClient(currentClient.getId_client());
			newAcc.setClient_name(currentClient.getName());
			newAcc.setName(currentClient.getName());
			aclient.setValue(currentClient.getId_client());
			aclient_name.setValue(currentClient.getName());
		} else {
			newAcc.setClient(clientId);
			newAcc.setClient_name(clientName);
			newAcc.setName(clientName);
			aclient.setValue(clientId);
			aclient_name.setValue(clientName);
		}

		newAcc.setDt(BigDecimal.ZERO);
		newAcc.setCt(BigDecimal.ZERO);
		newAcc.setS_in(BigDecimal.ZERO);
		newAcc.setS_out(BigDecimal.ZERO);
		newAcc.setCt_tmp(BigDecimal.ZERO);
		newAcc.setDt_tmp(BigDecimal.ZERO);
		newAcc.setS_in_tmp(BigDecimal.ZERO);
		newAcc.setS_out_tmp(BigDecimal.ZERO);
		aacc_bal_text.setFocus(true);
	}

	// public void onClick$btn_search() {
	// hideAll();
	// if (clientId != null) {
	// filter.setClient(clientId);
	// }
	// filter_div.setVisible(true);
	// }

	public void onClick$btn_search() {
		hideAll();
		if (clientId != null) {
			filter.setClient(this.clientId);
			fclient_name.setValue(this.accountService.getClientname(this.fclient.getValue(), this.branch1));
		} else {
			fclient_name.setValue((String) null);
		}

		this.filter_div.setVisible(true);
	}

	public void onClick$btn_cancel_filter() {
		filter = new AccountFilter();
		if (clientId != null) {
			filter.setClient(clientId);
		}
		selectedAccount = null;
		refreshModel();
		onClick$btn_back();
	}

	public void onClick$btn_find() {
		onClick$btn_back();
		selectedAccount = null;
		refreshModel();
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_new() {
		hideAll();
		newAcc = new Account();
		new_acc.setVisible(true);
	}

	public void onClick$btn_save() {
		if (CheckNull.isEmpty(aacc_bal.getValue()) || CheckNull.isEmpty(aclient.getValue())
				|| CheckNull.isEmpty(acurrency.getValue()) || CheckNull.isEmpty(aid_order.getValue())
				|| CheckNull.isEmpty(aname.getValue())) {
			alert("Заполните все поля");
			return;
		}

		Res res = accountService.doAction(newAcc, 1);
		if (res.getCode() != 0) {
			alert(res.getName());
			return;
		}
		selectedAccount = res.getName();
		refreshModel();
		onDoubleClick$dataGrid$grd();
	}

	public void onClick$btn_cancel() {
		onClick$btn_back();
	}

	public void onChange$acc_bal_text() {
		acc_bal.setSelecteditem(acc_bal_text.getValue());
		onChange$acc_bal();
	}

	public void onChange$acc_bal() {
		current.setAcc_bal(acc_bal.getValue());
		accBalSets();
		newIdOrder();
	}

	public void onChange$currencyValue() {
		current.setCurrency(currencyValue.getValue());
		newIdOrder();
	}

	public void onChange$currency() {
		current.setCurrency(currency.getValue());
		newIdOrder();
	}

	public void onChange$facc_bal_text() {
		filter.setAcc_bal(facc_bal_text.getValue());
		// accBalSets();
		// newIdOrder();
	}

	public void onChange$facc_bal() {
		filter.setAcc_bal(facc_bal.getValue());
		// accBalSets();
		// newIdOrder();
	}

	public void onChange$fcurrencyValue() {
		filter.setCurrency(fcurrencyValue.getValue());
		// newIdOrder();
	}

	public void onChange$fcurrency() {
		filter.setCurrency(fcurrency.getValue());
		// newIdOrder();
	}

	public void onChange$fstate() {
		if (fstate.getValue() != null)
			filter.setState(Integer.parseInt(fstate.getValue()));
		// accBalSets();
		// newIdOrder();
	}

	public void onChange$fsgn() {
		this.filter.setSgn(this.fsgn.getValue());
	}

	public void onChange$fbal() {
		this.filter.setBal(this.fbal.getValue());
	}

	public void onChange$fclient() {
		if (!CheckNull.isEmpty(this.fclient.getValue()) && this.fclient.getValue().trim().length() == 8) {
			this.fclient_name.setValue(this.accountService.getClientname(this.fclient.getValue(), this.branch1));
		} else {
			this.fclient_name.setValue((String) null);
		}
	}

	public void onChange$close_type$wind_nibbd() {
		wind_nibbd$close_type_name.setSelecteditem(wind_nibbd$close_type.getValue());
		nibbdparam.setClose_type(wind_nibbd$close_type.getValue());
	}

	public void onChange$close_type_name$wind_nibbd() {
		wind_nibbd$close_type.setValue(wind_nibbd$close_type_name.getValue());
		nibbdparam.setClose_type(wind_nibbd$close_type_name.getValue());
	}

	public void onChange$lock_type$wind_nibbd() {
		wind_nibbd$lock_type_name.setSelecteditem(wind_nibbd$lock_type.getValue());
		nibbdparam.setLock_type(wind_nibbd$lock_type.getValue());
	}

	public void onChange$lock_type_name$wind_nibbd() {
		wind_nibbd$lock_type.setValue(wind_nibbd$lock_type_name.getValue());
		nibbdparam.setLock_type(wind_nibbd$lock_type_name.getValue());
	}

	public void onChange$lock_source$wind_nibbd() {
		wind_nibbd$lock_source_name.setSelecteditem(wind_nibbd$lock_source.getValue());
		nibbdparam.setLock_source(wind_nibbd$lock_source.getValue());
	}

	public void onChange$lock_source_name$wind_nibbd() {
		wind_nibbd$lock_source.setValue(wind_nibbd$lock_source_name.getValue());
		nibbdparam.setLock_source(wind_nibbd$lock_source_name.getValue());
	}

	
	private Textbox acc_bal_text, aacc_bal_text;

	public void onChange$aacc_bal_text() {
		logger.error("onChange$onChange$aacc_bal_text ::::::::: aacc_bal_text.getValue() " + aacc_bal_text.getValue());
		aacc_bal.setSelecteditem(aacc_bal_text.getValue());
		logger.error("onChange$onChange$aacc_bal_text ::::::::: aacc_bal.getValue() " + aacc_bal.getValue());

		onChange$aacc_bal();
	}

	public void onChange$aacc_bal() {
		newAcc.setAcc_bal(aacc_bal.getValue());
		accBalSets();
		newIdOrder();
	}

	public void onChange$acurrencyValue() {
		newAcc.setCurrency(acurrencyValue.getValue());
		newIdOrder();
	}

	public void onChange$acurrency() {
		newAcc.setCurrency(acurrency.getValue());
		newIdOrder();
	}

	private void accBalSets() {
		if (new_acc.isVisible()) {
			newAcc.setSgn(AccountUtil.getSgn(newAcc.getAcc_bal(), saccList));
			newAcc.setBal(AccountUtil.getBal(newAcc.getAcc_bal()));
			newAcc.setSign_registr(AccountUtil.getSignRegistr(newAcc.getAcc_bal(), saccList));
			// logger.error("accBalSets::::::::::: newAcc.getAcc_bal()=" +
			// newAcc.getAcc_bal());
			acurrencyValue.setValue(newAcc.getCurrency());
			aacc_bal_text.setValue(newAcc.getAcc_bal());
			// logger.error("accBalSets::::::::::: after setValue
			// newAcc.getAcc_bal()=" + newAcc.getAcc_bal());
			aacc_bal.setSelecteditem(newAcc.getAcc_bal());
			// logger.error("accBalSets::::::::::: after setSelecteditem
			// newAcc.getAcc_bal()=" + newAcc.getAcc_bal());
			asgn.setSelecteditem(newAcc.getSgn());
			abal.setSelecteditem(newAcc.getBal());
		} else if (grd.isVisible()) {
			current.setSgn(AccountUtil.getSgn(current.getAcc_bal(), saccList));
			current.setBal(AccountUtil.getBal(current.getAcc_bal()));
			current.setSign_registr(AccountUtil.getSignRegistr(current.getAcc_bal(), saccList));

			currencyValue.setValue(current.getCurrency());
			acc_bal_text.setValue(current.getAcc_bal());
			acc_bal.setSelecteditem(current.getAcc_bal());
			sgn.setSelecteditem(current.getSgn());
			bal.setSelecteditem(current.getBal());
		}
	}

	private void newIdOrder() {
		if (new_acc.isVisible()) {
			if (CheckNull.isEmpty(newAcc.getAcc_bal()) || CheckNull.isEmpty(newAcc.getCurrency())
					|| CheckNull.isEmpty(newAcc.getClient())) {
				return;
			}
			newAcc.setId_order(accountService.getNewAccOrder(newAcc));
			aid_order.setValue(newAcc.getId_order());
		} else if (grd.isVisible()) {
			if (CheckNull.isEmpty(current.getAcc_bal()) || CheckNull.isEmpty(current.getCurrency())
					|| CheckNull.isEmpty(current.getClient())) {
				return;
			}
			current.setId_order(accountService.getNewAccOrder(current));
			id_order.setValue(current.getId_order());
		}
	}

	private void hideAll() {
		acc_tabs.setVisible(false);
		filter_div.setVisible(false);
		new_acc.setVisible(false);
		grd.setVisible(false);
	}

	public void hideRows() {
		wind_nibbd$codeBankRow.setVisible(false);
		wind_nibbd$closeTypeRow.setVisible(false);
		wind_nibbd$lockTypeRow.setVisible(false);
		wind_nibbd$lockSourceRow.setVisible(false);
		wind_nibbd$lockDocNRow.setVisible(false);
		wind_nibbd$lockDocDRow.setVisible(false);
		wind_nibbd$unLockLockId0Row.setVisible(false);
		wind_nibbd$unLockDocNRow.setVisible(false);
		wind_nibbd$unLockDocDRow.setVisible(false);
		wind_nibbd$unLockLockIdRow.setVisible(false);
	}

	private void executeAction(final int actionId) {
		if (current.getSign_registr() == 1 && (actionId == 3 || actionId == 4 || actionId == 5 || actionId == 11)) {

			// nibbd onlayn
			// deystivitelno deb savol bermasdan utishni istadik
			// nibbd oynani ochamiz
			hideRows();
			wind_nibbd.setVisible(true);
			if (actionId == 11) {
				// moveMainAccount
				wind_nibbd$codeBankRow.setVisible(true);
				wind_nibbd$btn_send.setAttribute("queryType", "moveMainAccount");
			} else if (actionId == 3) {
				// closeMainAccount
				wind_nibbd$closeTypeRow.setVisible(true);
				wind_nibbd$close_type_name.setModel(new ListModelList(accountDictionaries.getCloseTypeList()));
				wind_nibbd$btn_send.setAttribute("queryType", "closeMainAccount");

			} else if (actionId == 4) {
				// lockaccount
				wind_nibbd$lockTypeRow.setVisible(true);
				wind_nibbd$lock_type_name.setModel(new ListModelList(accountDictionaries.getLockTypeList()));
                //select lock_type_id id, lock_type_name name from ss_spr_203 order by 1
				wind_nibbd$lockSourceRow.setVisible(true);
				//select lock_source_id id, lock_source_name name from ss_spr_207 order by 1
				wind_nibbd$lock_source_name.setModel(new ListModelList(accountDictionaries.getLockSourceList()));
				wind_nibbd$lockDocNRow.setVisible(true);
				wind_nibbd$lockDocDRow.setVisible(true);
			} else if (actionId == 5) {
				//unlockaccount
				wind_nibbd$unLockLockId0Row.setVisible(true);
				wind_nibbd$locked_regnum_name.setModel(new ListModelList(accountService.getLockedRegnumList(current.getBranch(), current.getId())));
				
				
				wind_nibbd$unLockLockIdRow.setVisible(true);
				wind_nibbd$unLockDocNRow.setVisible(true);
				wind_nibbd$unLockDocDRow.setVisible(true);
				wind_nibbd$unLockLockIdRow.setVisible(true);
				//todo;
			}

			// changeTypeSubject-Регистрация изменения реквизита «Тип клиента»
			// субъекта в НИББД
			// wind_nibbd$coaRow.setVisible(true);
			// wind_nibbd$btn_send.setAttribute("queryType",
			// "changeTypeSubject");
			// }
			wind_nibbd$btn_send.setAttribute("actionId", actionId);
			binder.loadComponent(wind_nibbd);

		} else {

			try {
				Messagebox.show("вы действительно хотите выполнить действие:" + actionsMap.get(actionId) + "?", "",
						Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getName().equals(Messagebox.ON_OK)) {
									Res res = accountService.doAction(current, actionId);
									if (res.getCode() != 0) {
										alert(res.getName());
										return;
									}

									// if (current.getSign_registr() == 1) {
									// if (Util.inInts(actionId, 2, 20, 24)) {
									// initNibbd(NibbdQueries.ACCOUNT_OPEN,
									// actionId);
									// } else if (actionId == 3) {
									// initNibbd(NibbdQueries.ACCOUNT_CLOSE,
									// actionId);
									// } else if (actionId == 4) {
									// initNibbd(NibbdQueries.ACCOUNT_BLOCK,
									// actionId);
									// } else if (actionId == 5) {
									// initNibbd(NibbdQueries.ACCOUNT_UNBLOCK,
									// actionId);
									// } else if (actionId == 11) {
									// initNibbd(NibbdQueries.ACCOUNT_MOVE,
									// actionId);
									// }
									// }

									selectedAccount = current.getId();
									refreshModel();
									onDoubleClick$dataGrid$grd();
								}
							}
						});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	// -- Кнопка - 'Запросить НИББД'
	@SuppressWarnings("unused")
	public void onClick$btn_send$wind_nibbd() throws Exception {

		// wind_nibbd$res_grid.getRows().getChildren().clear();
		// String queryType =
		// (String)wind_nibbd$btn_send.getAttribute("queryType");
		// if (queryType.equals("inn") || queryType.equals("pin") ) {
		//
		// }
		int action = (Integer) wind_nibbd$btn_send.getAttribute("actionId");
		// proverkalar kerak shuerga manimcha

		executeActionNibbd(action);

	}

	private void executeActionNibbd(int actionId) {

		// bu erda ham ehtimol myDoaction kerakdir
		// currentga shuerda nibb uchun kerakli polyalarni beramiz
		if (actionId == 3 && current.getSign_registr() == 1) {
			// zakrit
			current.setNibbd_codebank(nibbdparam.getClose_type());
		}  else if (actionId==4) {
			// lock
			// cdsAcc.FieldByName('nibbd_codebank').Value:=lpad(edtNibbdLockType.text,2,' ')+lpad(edtNibbdLockSource.Text,2,' ')+lpad(edtNibbdLockDocN.Text,20,' ')+edtNibbdLockDocD.Text
			current.setNibbd_codebank(String.format("%1$2s", nibbdparam.getLock_type()) + String.format("%1$2s", nibbdparam.getLock_source()) + String.format("%1$20s", nibbdparam.getLock_doc_n()) + df.format(nibbdparam.getLock_doc_d()));
			
		}
			// setJuridicalAccount, setIndividualAccount, setBudgetAccount
			// current.setP_capacity_status_place(nibbdparam.getCoa());
			// } else if (action==32) {
			// //{changeTypeSubject-Регистрация изменения реквизита «Тип
			// клиента» субъекта в НИББД}
			// current.setP_capacity_status_place(nibbdparam.getCoa());
			// } else if (action==3) {
			// //{closeSubject-Регистрация прекращения деятельности субъекта}
			// current.setP_capacity_status_place(nibbdparam.getClose_type());
			// current.setP_num_certif_capacity(nibbdparam.getClosed_doc_n());
			// current.setP_capacity_status_date(nibbdparam.getClosed_doc_d());

		// }

		Res res = accountService.doAction(current, actionId);
		if (res.getCode() != 0) {
			alert(res.getName());
			return;
		}

		// if (current.getSign_registr() == 1) {
		// if (Util.inInts(actionId, 2, 20, 24)) {
		// initNibbd(NibbdQueries.ACCOUNT_OPEN, actionId);
		// } else if (actionId == 3) {
		// initNibbd(NibbdQueries.ACCOUNT_CLOSE, actionId);
		// } else if (actionId == 4) {
		// initNibbd(NibbdQueries.ACCOUNT_BLOCK, actionId);
		// } else if (actionId == 5) {
		// initNibbd(NibbdQueries.ACCOUNT_UNBLOCK, actionId);
		// } else if (actionId == 11) {
		// initNibbd(NibbdQueries.ACCOUNT_MOVE, actionId);
		// }
		// }

		selectedAccount = current.getId();
		refreshModel();
		onDoubleClick$dataGrid$grd();
		wind_nibbd.setVisible(false);
	}

	private void initNibbd(NibbdQueries query, int action) {
		if (nibbd_wnd == null) {
			nibbd_wnd = (Window) Executions.createComponents("nibbd.zul", nibbd_modal, null);
		}
		NibbdController nibbdC = (NibbdController) nibbd_wnd.getAttribute("nibbdmain$composer");
		// if(query == NibbdQueries.ACCOUNT_OPEN) {
		nibbdC.setClient(currentClient);
		// }
		nibbdC.initAccountAction(current, action, query);

		nibbd_modal.setVisible(true);

	}

	private void initSpecAcc() {
		if (spec_acc_wnd == null) {
			spec_acc_wnd = (Window) Executions.createComponents("account_spec.zul", spec_acc_parent, null);
		}
		SpecAccViewCtrl specAccCtrl = (SpecAccViewCtrl) spec_acc_wnd.getAttribute("specAccmain$composer");

		specAccCtrl.init(current);
	}

	private void initActionBar() {
		Map<Integer, String> availableActions = accountService.getAvailableActions(current);
		// actionsMap = Util.actionListToMapInt(current.getSign_registr(),
		// accountActions);
		Toolbarbutton button;
		action_bar.getChildren().clear();
		for (Map.Entry<Integer, String> entry : availableActions.entrySet()) {
			if (entry.getKey() == 1) {
				continue;
			}
			button = new Toolbarbutton(entry.getValue());
			button.setAttribute("action", entry.getKey());
			button.addEventListener(Events.ON_CLICK, new EventListener() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					executeAction((Integer) arg0.getTarget().getAttribute("action"));
				}
			});
			action_bar.appendChild(button);
		}
	}

	public void onOkFieldNew(Event e) {
		String fieldId = (((ForwardEvent) e).getOrigin().getTarget()).getId();
		fieldId = fieldId.substring(1);
		switch (AccountEnum.valueOf(fieldId.toUpperCase())) {
		case ACC_BAL_TEXT:
			acurrencyValue.setFocus(true);
			acurrencyValue.select();
			break;
		case CURRENCYVALUE:
			aacc_group_idText.setFocus(true);
			aacc_group_idText.select();
			break;
		case ACC_GROUP_IDTEXT:
			aname.setFocus(true);
			aname.select();
			break;
		case NAME:
			onClick$btn_save();
			break;
		default:
			break;
		}
	}

	public void onOkField(Event e) {
		String fieldId = (((ForwardEvent) e).getOrigin().getTarget()).getId();
		switch (AccountEnum.valueOf(fieldId.toUpperCase())) {
		case ACC_BAL_TEXT:
			currencyValue.setFocus(true);
			currencyValue.select();
			break;
		case CURRENCYVALUE:
			acc_group_idText.setFocus(true);
			acc_group_idText.select();
			break;
		case ACC_GROUP_IDTEXT:
			name.setFocus(true);
			name.select();
			break;
		case NAME:
			onClick$btn_save();
			break;
		default:
			break;
		}
	}
}
