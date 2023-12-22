package com.is.nibbd;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.is.account.model.Account;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.base.utils.ZkUtils;
import com.is.clients.dao.ClientDao;
import com.is.clients.models.ClientJ;
import com.is.clients.services.ServiceFactory;
import com.is.clients.validation.CheckClient;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.models.NibbdFilter;
import com.is.nibbd.models.SsNibbd;
import com.is.nibbd.reis.reisServices.MakerService;
import com.is.nibbd.reis.reishandlers.ReisHandler;
import com.is.nibbd.reis.reishandlers.ReisMaker;
import com.is.nibbd.renderers.NibbdRenderer;
import com.is.nibbd.renderers.ProtocolRenderer;
import com.is.nibbd.service.NibbdDao;
import com.is.nibbd.service.NibbdDictionaries;
import com.is.nibbd.service.NibbdService;
import com.is.nibbd.service.ProtocolService;
import com.is.nibbd.util.NibbdQueries;
import com.is.nibbd.util.NibbdUtils;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.RefData;
import com.is.utils.Res;

import lombok.Getter;
import lombok.Setter;

public class NibbdController extends GenericForwardComposer {

	private static Logger logger = Logger.getLogger(NibbdController.class);

	private Window client_modal, filter_modal;

	private Tabbox tabs;
	private Include client_modal$incl_client;
	private Listbox dataGrid, protocolData;
	private Grid client_grid, account_grid, filter_modal$filter_grd;
	private Radiogroup client_queries, account_queries;
	private Textbox id_client, number_tax_registration, reasoning, acc_bal,
			currency, id_order, old_acc, new_acc, pinfl;
	private Textbox id_client_acc, acc_bal_acc, currency_acc, id_order_acc,
			account_actionValue, code_head_organization_acc,
			inn_head_organization_acc, reasoning_acc, client_account, new_bank,
			block_signValue;

	private RefCBox account_action, block_sign;
	private Datebox query_date_acc, last_account_movement;
	private Datebox date_registration, query_date;
	private RefCBox filter_modal$state;
	private Toolbarbutton btn_client, btn_receive, btn_send, btn_client_change,
			btn_delete;
	private AnnotateDataBinder binder;
	@Getter
	@Setter
	private Nibbd clientQuery = new Nibbd();
	@Getter
	@Setter
	private Nibbd accountQuery = new Nibbd();
	@Getter
	@Setter
	private Nibbd current = new Nibbd();
	@Getter
	@Setter
	private NibbdFilter filter = new NibbdFilter();
	private ClientJ client;
	private Account account;
	private Dao<Nibbd> nibbdDao;
	private Map<String, String> nibbdStates;
	private Map<String, String> clientCodeLetter;
	private Map<String, String> clientLetterCode;
	private List<SsNibbd> ssnibbdList;

	private NibbdService nibbdService;
	private NibbdDictionaries nibbdDictionaries;
	private ReisHandler reisMaker;
	private ProtocolService protocolService;

	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	SimpleDateFormat dfWithTime = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private String alias;
	private String branch;
	private String un, pw;
	private Date operDay;
	private int actionAccount;
	private boolean isModalView;

	public NibbdController() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.bindBean("filter", this.filter);
		binder.bindBean("clientQuery", this.clientQuery);
		binder.bindBean("accountQuery", this.accountQuery);
		binder.loadAll();

		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");

		initServices();
		buildRadioBar();
		dataGrid.setItemRenderer(new NibbdRenderer(nibbdStates, df));
		protocolData.setItemRenderer(new ProtocolRenderer(dfWithTime));

		defaultView();
	}

	private void defaultView() {
		filter.setDate_from(operDay);
		filter.setDate_to(operDay);
		filter.setBranch(branch);
		refreshModel();
		// logger.error("defaultView account_queries = : " +
		// account_queries.getChildren().size());
		// logger.error("defaultView client_queries = : " +
		// client_queries.getChildren().size());
		account_queries.setSelectedIndex(NibbdQueries.ACCOUNT_OPEN
				.getRadioOrder());
		client_queries.setSelectedIndex(NibbdQueries.IDENTIFICATION
				.getRadioOrder());
		Events.sendEvent(new Event(Events.ON_CHECK, client_queries));
		Events.sendEvent(new Event(Events.ON_CHECK, account_queries));
		client_grid.setWidth("50%");
		account_grid.setWidth("50%");
		isModalView = false;
	}

	private void initServices() {
		nibbdDao = NibbdDao.getInstance(alias, branch);
		nibbdService = NibbdService.getInstatance(alias, un, pw);
		reisMaker = ReisMaker.getInstance(MakerService.getInstance(alias,
				branch));
		protocolService = ProtocolService.getInstance(alias);
		nibbdDictionaries = NibbdDictionaries.instance(alias);

		setModels();
	}

	public void initClientAction(ClientJ cl, NibbdQueries query) {
		isModalView = true;
		this.client = cl;
		// 2018-01-09
		if (this.client.getJ_code_sector() == null)
			this.client.setJ_code_sector("0");
		//
		// //2021-08-09
		// if (this.client.getCode_type()=="11" ||
		// this.client.getCode_type().equals("11")) {
		// if (this.client.getP_pinfl() != null )
		// this.client.setJ_number_tax_registration(this.client.getP_pinfl());
		// }

		client_queries.setSelectedIndex(1);
		tabs.setSelectedIndex(0);
		client_queries.setSelectedIndex(query.getRadioOrder());
		Events.sendEvent(new Event(Events.ON_CHECK, client_queries));
		Events.sendEvent(new Event(Events.ON_SELECT, tabs));
		client_grid.setWidth("100%");
		account_grid.setWidth("100%");
		binder.loadAll();
	}

	public void initAccountAction(Account acc, int actionAccount,
			NibbdQueries query) {
		isModalView = true;
		this.account = acc;
		this.actionAccount = actionAccount;
		tabs.setSelectedIndex(1);
		account_queries.setSelectedIndex(query.getRadioOrder());
		Events.sendEvent(new Event(Events.ON_CHECK, account_queries));
		Events.sendEvent(new Event(Events.ON_SELECT, tabs));
		client_grid.setWidth("100%");
		account_grid.setWidth("100%");
		binder.loadAll();
	}

	public void setClient(ClientJ client) {
		this.client = client;
		binder.loadAll();
	}

	private void setModels() {
		operDay = DbUtils.getOperDay(alias);
		clientCodeLetter = nibbdDictionaries.getClientCodeLetter();
		clientLetterCode = nibbdDictionaries.getClientLetterCode();
		List<RefData> nibbdStatesList = nibbdDictionaries.getNibbdStatesList();
		nibbdStates = Util.listToMap(nibbdStatesList);
		ssnibbdList = nibbdDictionaries.getSsnibbdList();
		filter_modal$state.setModel(new ListModelList(nibbdStatesList));
	}

	private void refreshModel() {
		nibbdDao.setFilter(filter);
		dataGrid.setModel(new BindingListModelList(nibbdDao.getList(), true));
	}

	public void onSelect$dataGrid() {
		if (current != null) {
			btn_receive.setVisible(NibbdUtils.canReceiveQuery(current));
			btn_send.setVisible(NibbdUtils.canSendQuery(current));
			btn_client_change.setVisible(NibbdUtils.canOpenClient(current));
			btn_client.setVisible(NibbdUtils.canOpenClient(current));
			btn_delete.setVisible(NibbdUtils.canDelete(current));
		}
	}

	public void onClick$btn_post() {
		int queryNum = 0;
		if ((client_queries.getSelectedItem() == null)
				|| (account_queries.getSelectedItem() == null)) {
			return;
		}
		queryNum = Integer
				.parseInt(tabs.getSelectedIndex() == 0 ? client_queries
						.getSelectedItem().getValue() : account_queries
						.getSelectedItem().getValue());
		if (!isModalView && Util.inInts(queryNum, 1, 2, 5, 6, 7)) {
			alert("Запросы 1, 2, 5, 6, 7 формируются только при "
					+ "взаимодействии с модулями 'Счета' и 'Клиенты'");
			return;
		}
		if (!ZkUtils.validateForm(tabs.getSelectedIndex() == 0 ? client_grid
				: account_grid)) {
			if (tabs.getSelectedIndex() == 0 && ( clientQuery.getPinfl() != null || !(clientQuery.getNumber_tax_registration() == null || clientQuery.getNumber_tax_registration()=="000000000" || clientQuery.getNumber_tax_registration().equals("000000000")))) // client_grid
			{
			} else {
				alert("Заполните поля");
				return;
			}
		}
		// logger.error("Nibbd Controller " +
		// current.getNumber_tax_registration());

		if (queryNum == 0 && clientQuery.getPinfl() == null) {

			if (!CheckClient.isTaxNumberValid(
					clientQuery.getNumber_tax_registration(), alias)) {
				alert("Неверный ИНН");
				return;
			}
		}

		try {
			if (tabs.getSelectedIndex() == 0) {
				if (queryNum == NibbdQueries.CLIENT_STOP.getQueryNumber()) {
					return;
				}
				clientQuery.setQuery_num(queryNum);
				nibbdDao.create(clientQuery);
			} else if (tabs.getSelectedIndex() == 1) {
				accountQuery.setQuery_num(queryNum);
				nibbdDao.create(accountQuery);
				// AccountService.getInstance(branch, alias, un,
				// pw).stubNibbd(account);
			}
		} catch (Exception e) {
			alert(e.getMessage());
			return;
		}
		ZkUtils.clearForm(tabs.getSelectedIndex() == 0 ? client_grid
				: account_grid);
		if (tabs.getSelectedIndex() == 0) {
			clientQuery = new Nibbd();
			client_queries.setSelectedIndex(-1);
		} else if (tabs.getSelectedIndex() == 1) {
			accountQuery = new Nibbd();
			account_queries.setSelectedIndex(-1);
		}
		refreshModel();
	}

	public void onCheck$client_queries() {
		int selitem = Integer.parseInt(client_queries.getSelectedItem()
				.getValue());

		ZkUtils.clearForm(client_grid);
		switch (NibbdQueries.getQueryType(selitem)) {
		case ACCOUNTS_OUSIDE_FILIAL:
			clientQuery = new Nibbd();
			ZkUtils.disableFormWithConstraint(client_grid, true);
			ZkUtils.applyAttributesForDisabled(id_client, false);
			ZkUtils.applyAttributesForDisabled(acc_bal, false);
			break;
		case CLIENT_CHANGE:
			clientQuery = new Nibbd(client);
			ZkUtils.disableFormWithConstraint(client_grid, false);
			ZkUtils.applyAttributesForDisabled(acc_bal, true);
			ZkUtils.applyAttributesForDisabled(currency, true);
			ZkUtils.applyAttributesForDisabled(id_order, true);
			ZkUtils.applyAttributesForDisabled(query_date, true);
			clientQuery.setOld_acc("0");
			clientQuery.setNew_acc("0");
			if (client != null) {
				clientQuery.setCode_type_cyr(clientCodeLetter.get(client
						.getCode_type()));
				clientQuery.setCode_type(client.getCode_type());
			}
			break;
		case CLIENT_OPEN:
			clientQuery = new Nibbd(client);
			ZkUtils.disableFormWithConstraint(client_grid, false);
			ZkUtils.applyAttributesForDisabled(id_client, true);
			ZkUtils.applyAttributesForDisabled(old_acc, true);
			ZkUtils.applyAttributesForDisabled(new_acc, true);
			ZkUtils.applyAttributesForDisabled(reasoning, true);
			ZkUtils.applyAttributesForDisabled(currency, true);
			ZkUtils.applyAttributesForDisabled(id_order, true);
			ZkUtils.applyAttributesForDisabled(query_date, true);
			clientQuery.setCurrency("000");
			clientQuery.setId_order("001");

			if (client != null) {
				clientQuery.setCode_type_cyr(clientCodeLetter.get(client
						.getCode_type()));
				clientQuery.setCode_type(client.getCode_type());
			}
			break;
		case CLIENT_STOP:
			clientQuery = new Nibbd();
			ZkUtils.disableFormWithConstraint(client_grid, true);
			ZkUtils.applyAttributesForDisabled(id_client, false);
			ZkUtils.applyAttributesForDisabled(date_registration, false);
			ZkUtils.applyAttributesForDisabled(reasoning, false);
			ZkUtils.applyAttributesForDisabled(query_date, false);
			clientQuery.setQuery_date(operDay);
			break;
		case IDENTIFICATION:
			clientQuery = new Nibbd();
			ZkUtils.disableFormWithConstraint(client_grid, true);
			ZkUtils.applyAttributesForDisabled(number_tax_registration, false);
			ZkUtils.applyAttributesForDisabled(pinfl, false);
			break;
		default:
			break;
		}
		binder.loadAll();
	}

	public void onCheck$account_queries() {
		int selitem = Integer.parseInt(account_queries.getSelectedItem()
				.getValue());
		accountQuery = new Nibbd(account);
		ZkUtils.clearForm(account_grid);
		account_action.setModel(new ListModelList(NibbdUtils.getQuerySets(
				selitem, ssnibbdList)));
		block_sign.setModel(new ListModelList(new ListModelList(NibbdUtils
				.getQuerySets(selitem, ssnibbdList))));

		accountQuery.setBlock_sign(null);
		accountQuery.setAccount_action(null);
		switch (NibbdQueries.getQueryType(selitem)) {
		case ACCOUNT_BLOCK:
			ZkUtils.disableFormWithConstraint(account_grid, false);
			ZkUtils.applyAttributesForDisabled(account_actionValue, true);
			ZkUtils.applyAttributesForDisabled(account_action, true);
			ZkUtils.applyAttributesForDisabled(code_head_organization_acc, true);
			ZkUtils.applyAttributesForDisabled(inn_head_organization_acc, true);
			ZkUtils.applyAttributesForDisabled(client_account, true);
			ZkUtils.applyAttributesForDisabled(new_bank, true);
			accountQuery.setBlock_sign("4");
			accountQuery.setQuery_date(operDay);
			accountQuery.setLast_account_movement(operDay);
			accountQuery.setAcc_bal("00000");
			accountQuery.setCurrency("VVV");
			accountQuery.setId_order("000");
			Events.sendEvent(new Event("onInitRender", block_sign));
			break;
		case ACCOUNT_MOVE:
			ZkUtils.disableFormWithConstraint(account_grid, true);
			ZkUtils.applyAttributesForDisabled(id_client_acc, false);
			ZkUtils.applyAttributesForDisabled(account_actionValue, false);
			ZkUtils.applyAttributesForDisabled(account_action, false);
			ZkUtils.applyAttributesForDisabled(client_account, false);
			ZkUtils.applyAttributesForDisabled(new_bank, false);
			accountQuery.setAccount_action("1");
			Events.sendEvent(new Event("onInitRender", account_action));
			break;
		case ACCOUNT_CLOSE:
			ZkUtils.disableFormWithConstraint(account_grid, true);
			ZkUtils.applyAttributesForDisabled(id_client_acc, false);
			ZkUtils.applyAttributesForDisabled(client_account, false);
			ZkUtils.applyAttributesForDisabled(block_signValue, false);
			ZkUtils.applyAttributesForDisabled(block_sign, false);
			break;
		case ACCOUNT_OPEN:
			ZkUtils.disableFormWithConstraint(account_grid, false);
			ZkUtils.applyAttributesForDisabled(reasoning_acc, true);
			ZkUtils.applyAttributesForDisabled(query_date_acc, true);
			ZkUtils.applyAttributesForDisabled(client_account, true);
			ZkUtils.applyAttributesForDisabled(new_bank, true);
			ZkUtils.applyAttributesForDisabled(block_sign, true);
			ZkUtils.applyAttributesForDisabled(block_signValue, true);
			ZkUtils.applyAttributesForDisabled(last_account_movement, true);
			accountQuery.setAccount_action("0");
			if (actionAccount != 0 && client != null) {
				if (client.getSign_registr() == 1 && actionAccount == 20
						&& client.getJ_account().equals(account.getId())) {
					accountQuery.setAccount_action("5");
				} else if (client.getSign_registr() == 3 && actionAccount == 24) {
					List<RefData> list = NibbdUtils.getQuerySets(selitem,
							ssnibbdList);
					list.remove(0);
					block_sign.setModel(new ListModelList(new ListModelList(
							list)));
				}
				accountQuery.setCode_head_organization(client
						.getJ_code_head_organization());
				accountQuery.setInn_head_organization(client
						.getJ_inn_head_organization());
			}
			Events.sendEvent(new Event("onInitRender", account_action));
			break;
		case ACCOUNT_UNBLOCK:
			ZkUtils.disableFormWithConstraint(account_grid, true);
			ZkUtils.applyAttributesForDisabled(id_client_acc, false);
			ZkUtils.applyAttributesForDisabled(acc_bal_acc, false);
			ZkUtils.applyAttributesForDisabled(currency_acc, false);
			ZkUtils.applyAttributesForDisabled(id_order_acc, false);
			ZkUtils.applyAttributesForDisabled(reasoning_acc, false);
			ZkUtils.applyAttributesForDisabled(query_date_acc, false);
			ZkUtils.applyAttributesForDisabled(block_sign, false);
			ZkUtils.applyAttributesForDisabled(block_signValue, false);
			accountQuery.setBlock_sign("14");
			accountQuery.setQuery_date(operDay);
			accountQuery.setAcc_bal("00000");
			accountQuery.setCurrency("VVV");
			accountQuery.setId_order("000");
			Events.sendEvent(new Event("onInitRender", block_sign));
			break;
		default:
			break;
		}
		binder.loadAll();
	}

	public void onClick$btn_make_reis() {
		reisMaker.handle();
		refreshModel();
	}

	public void onClick$btn_delete() {
		if (current == null) {
			return;
		}
		try {
			if (nibbdDao.remove(current) > 0) {
				if (!CheckNull.isEmpty(current.getParent_id())) {
					nibbdService.rollbackDoAction(current);
				}
				refreshModel();
			}
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		}
	}

	public void onClick$btn_receive() {
		if (current == null) {
			return;
		}
		current.setState(3);
		try {
			nibbdDao.update(current);
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		}
		refreshModel();
	}

	public void onClick$btn_send() {
		if (current == null) {
			return;
		}
		current.setState(2);
		try {
			nibbdDao.update(current);
		} catch (Exception e) {
			alert(e.getMessage());
		}
		refreshModel();
	}

	public void onClick$btn_client() {
		Events.sendEvent(Events.ON_NOTIFY, self, current);
		// client_modal$incl_client.setSrc(null);
		// client_modal$incl_client.setSrc("client_j.zul?mode=nibbd&queryOut="+current.getQuery_out());
		// client_modal.setVisible(true);
	}

	public void onClick$btn_client_change() {
		String[] arr = current.getQuery_out().split("~");
		String[] fields = arr[2].split(";");

		String branch = DbUtils.getBranchBySchema(alias);
		ClientJ client = ClientDao.getInstance().getItemByStringId(branch,
				fields[3]);
		if (client == null || client.getState().equals(2)) {
			alert("Клиент не существует или находится в сосотянии \"Утвержден\"");
			return;
		}

		client.setCode_type(clientLetterCode.get(fields[10]));
		NibbdUtils.setClientAttrFromQueryOut(client, current.getQuery_out());

		Res res = ServiceFactory.getInstance(alias, un, pw).getClientJService()
				.doAction(client, 27);
		if (res != null && res.getCode() != 0) {
			alert(res.getName());
		}
	}

	public void onClick$btn_show_filter() {
		filter_modal.setVisible(true);
	}

	public void onClick$btn_find$filter_modal() {
		filter_modal.setVisible(false);
		refreshModel();
	}

	public void onClick$btn_clear$filter_modal() {
		filter = new NibbdFilter();
		ZkUtils.clearForm(filter_modal$filter_grd);
		filter.setDate_from(operDay);
		filter.setDate_to(operDay);
	}

	public void onClick$btn_refresh() {
		refreshModel();
	}

	private void buildRadioBar() {
		Radio radioitem = null;
		client_queries.getChildren().clear();
		account_queries.getChildren().clear();
		for (NibbdQueries n : NibbdQueries.values()) {
			radioitem = new Radio();
			radioitem.setValue(Integer.toString(n.getQueryNumber()));
			radioitem.setLabel("Запрос №" + n.getQueryNumber());
			radioitem.setTooltiptext(n.getText());
			if (n.getType() == NibbdUtils.TYPE_CLIENT) {
				client_queries.appendChild(radioitem);
			} else {
				account_queries.appendChild(radioitem);
			}
		}
		// logger.error("defaultView after buildRadioBar account_queries = : " +
		// account_queries.getChildren().size());
		// logger.error("defaultView after buildRadioBar client_queries = : " +
		// client_queries.getChildren().size());
	}

}
