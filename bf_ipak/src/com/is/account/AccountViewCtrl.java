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
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.account.model.Account;
import com.is.account.model.AccountFilter;
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
	private Toolbarbutton btn_last, btn_next, btn_prev, btn_first, btn_back, btn_add;
	private Textbox id_order, name, acc_group_idText;
	private RefCBox state, acc_bal, currency, sgn, bal, acc_group_id;
	private RefCBox fstate, facc_bal, fcurrency, fsgn, fbal, facc_group_id;
	private Textbox aclient, aacc_group_idText, aid_order, aname;
	private RefCBox astate, aacc_bal, acurrency, asgn, abal, aacc_group_id;
	private Textbox currencyValue, fcurrencyValue, acurrencyValue;
	private Textbox facc_bal_text, fclient, fclient_name;
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
			//clientName = accountService.getClientname(clientId);
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
		//if (clientId == null)
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
			// ������������ � ������ ������
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
	
	//	--  ������ "����� �����"
	public void onClick$btn_approve() {
		if(current.getId() != null) {
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

	//public void onClick$btn_search() {
	//	hideAll();
	//	if (clientId != null) {
	//		filter.setClient(clientId);
	//	}
	//	filter_div.setVisible(true);
	//}

	public void onClick$btn_search() {
	     hideAll();
		      if (clientId != null) {
		         filter.setClient(this.clientId);
		         fclient_name.setValue(this.accountService.getClientname(this.fclient.getValue(), this.branch1));
		      } else {
		         fclient_name.setValue((String)null);
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
			alert("��������� ��� ����");
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
		         this.fclient_name.setValue((String)null);
		      }

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
			//logger.error("accBalSets::::::::::: newAcc.getAcc_bal()=" + newAcc.getAcc_bal());
			acurrencyValue.setValue(newAcc.getCurrency());
			aacc_bal_text.setValue(newAcc.getAcc_bal());
			//logger.error("accBalSets::::::::::: after setValue newAcc.getAcc_bal()=" + newAcc.getAcc_bal());
			aacc_bal.setSelecteditem(newAcc.getAcc_bal());
			//logger.error("accBalSets::::::::::: after setSelecteditem newAcc.getAcc_bal()=" + newAcc.getAcc_bal());
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

	private void executeAction(final int actionId) {
		try {
			Messagebox.show("�� ������������� ������ ��������� ��������:" + actionsMap.get(actionId) + "?", "",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

						@Override
						public void onEvent(Event event) throws Exception {
							if (event.getName().equals(Messagebox.ON_OK)) {
								Res res = accountService.doAction(current, actionId);
								if (res.getCode() != 0) {
									alert(res.getName());
									return;
								}

								if (current.getSign_registr() == 1) {
									if (Util.inInts(actionId, 2, 20, 24)) {
										initNibbd(NibbdQueries.ACCOUNT_OPEN, actionId);
									} else if (actionId == 3) {
										initNibbd(NibbdQueries.ACCOUNT_CLOSE, actionId);
									} else if (actionId == 4) {
										initNibbd(NibbdQueries.ACCOUNT_BLOCK, actionId);
									} else if (actionId == 5) {
										initNibbd(NibbdQueries.ACCOUNT_UNBLOCK, actionId);
									} else if (actionId == 11) {
										initNibbd(NibbdQueries.ACCOUNT_MOVE, actionId);
									}

								}
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
