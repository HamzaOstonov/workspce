package com.is.uzcard;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
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
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Paging;
import org.zkoss.zul.event.PagingEvent;

import com.is.ISLogger;
import com.is.crm.ho.Customer;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;
import com.is.uzcard.model.AccountInfo;
import com.is.uzcard.model.AppList;
import com.is.uzcard.model.BTRT;
import com.is.uzcard.model.BTRT03;
import com.is.uzcard.model.BTRT30;
import com.is.uzcard.model.Card;
import com.is.uzcard.model.CardMaskInfo;
import com.is.uzcard.model.Credential;
import com.is.uzcard.model.InOutFile;
import com.lowagie.text.pdf.PdfPublicKeyRecipient;

/**
 * @author DEN
 *
 */
public class UzCardViewCtrl extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1278821092002467857L;

	// private Div crmDiv;
	private Div top_div;
	private Div cardListDiv;
	private Div btrtToolBar;
	private Div grdAppL01152030;

	private Listbox openAcc$dataGrid;
	private Listbox findAcc$dataGrid;
	private Listbox btrt20$cardList;
	private Listbox btrt25$cardList;
	private Listbox cardList;
	private Listbox appList01152030;
	private Listbox fileList01152030;
	private Constants.FILTER_FIELD_TYPE currentFiterField;

	private Tabbox tabbox;

	// private Button findAcc$applyFilterBtn;
	private Textbox findAcc$searchFilterTextBox;

	// private Div dynamicTable;

	private Window card_applications, app_card, app_card_customer, app_card_ch_address, app_card_ch_person_info,
			app_card_account, job, btrt03Wnd, btrt20, btrt25, btrt30Tail;
	private Window openAcc;
	private Window findAcc;
	private Div dt;

	private List<Component> fieldsGroups;
	private final static int[] btrt01Fields = { 0, /* 1, 2, */ 3, 4, 5, 6 };
	private final static int[] btrt02Fields = { 0, /* 1, 2, */ 3, 4, 5 };
	private final static int[] btrt05Fields = { 0, /* 1, 2, */ 3, 4, 5 };
	private final static int[] btrt06Fields = { 0, /* 1, 2, */ 3, 4, 5 };
	private final static int[] btrt03Fields = { 7 };
	private final static int[] btrt20Fields = { 8 };
	private final static int[] btrt25Fields = { 9 };
	private final static int[] btrt30Fields = { 4, 3, 10 };

	// private Button openAcc$buttonOpenAcc;

	private Button findAcc$btn_ok, findAcc$btn_ok_work, findAcc$btn_ok_st_emp;

	private Toolbar tb01_02_05_06, tb30;

	private Row job$not_st_emp, job$st_emp;
	private Checkbox card_applications$is_st_emp;

	private RefCBox actionComboBox;

	private Label cust;

	private Label btrt03Wnd$filial_label, btrt03Wnd$client_id_label, btrt03Wnd$last_name_label,
			btrt03Wnd$first_name_label, btrt03Wnd$patronymic_label, btrt03Wnd$account_label, btrt03Wnd$card_name_label,
			btrt03Wnd$contract_id_label, btrt03Wnd$state_label, btrt03Wnd$app_id_label;

	private Textbox // rec_number,
	card_applications$customer_id, card_applications$customer_desc, /* app_card_customer$customer_desc, */
			app_card_customer$inn, app_card_customer$okpo, app_card_ch_person_info$first_name,
			app_card_ch_person_info$second_name, app_card_ch_person_info$patronymic,
			// app_card_ch_person_info$p_proc_mode,
			/* app_card_ch_person_info$company_name, */ app_card_ch_person_info$security_id,
			app_card_ch_person_info$p_id_number, app_card_ch_person_info$p_id_series,
			app_card_ch_person_info$p_id_authority,
			// app_card_ch_person_info$_proc_mode,
			app_card_ch_address$address_line1, app_card_ch_address$address_line2, app_card_ch_address$primary_phone,
			app_card_ch_address$mobile_phone, app_card_ch_address$email, /*
																			 * app_card$card_number,
																			 * app_card$card_type,
																			 */
			app_card_account$embossed_ch_name, // app_card$def_atm_account,
			// app_card$def_pos_account, app_card$is_primary,
			app_card$company_name_card, app_card_account$account_number, /* app_card_account$account_type, */
			app_card_account$currency, job$work_id, job$work_name, job$acc_st_emp, job$inn_st_emp, job$name_st_emp,
			btrt03Wnd$account;

	private Textbox btrt20$first_name, btrt20$second_name, btrt20$patronymic, btrt20$contract_id, btrt20$p_id_series,
			btrt20$p_id_number, btrt20$embossed_ch_name;
	private Textbox btrt25$limit;
	private Textbox btrt30Tail$account_number, btrt30Tail$card_number;
	private RefCBox card_applications$contract_id, app_card_ch_person_info$sex, app_card_ch_person_info$residence,
			app_card_ch_person_info$p_id_type, app_card_ch_address$address_type, app_card_ch_address$region,
			app_card_ch_address$country, app_card_customer$vip_flag, btrt03Wnd$contract_id, btrt20$p_id_type,
			btrt20$actionId;
	private Datebox // app_card$expiration_date,
	app_card_ch_person_info$birth_date, app_card_ch_person_info$p_id_issue_date, btrt20$birth_date;
	private Paging findAcc$paging;

	private Window uz_card_main;
	/*
	 * private Paging app_card_merchant_levelPaging; private int _pageSize = 15;
	 * private int _startPageNumber = 0; private int _totalSize = 0; private
	 * boolean _needsTotalSizeUpdate = true;
	 */
	private EventQueue eq;
	// private String un, pwd, branch, alias;

	// private static Map<String, Window> blankMap = new HashMap<String,
	// Window>();

	private BTRT btrt = new BTRT();
	private BTRT03 btrt03;
	private BTRT30 btrt30;
	// private BTRT20 btrt20;
	// Bf_crm_clients crm_client;

	// public app_card_merchant_levelFilter filter;

	// PagingListModel model = null;
	ListModelList lmodel = null;
	// private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	// private app_card_merchant_level current = new app_card_merchant_level();
	private History client = null;

	private String selectedBtrt;

	private String appId = "";

	private int _pageSize = 20;

	private boolean _needsTotalSizeUpdate = true;

	private int _totalSize;

	private PagingListModelAccInfo model;

	private int _startPageNumber = 0;

	private Credential credential;

	public UzCardViewCtrl() {
		super('$', false, false);

	}

	/**
	 * Простите за всё что натворил здесь
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		String alias = (String) session.getAttribute("alias");
		String un = (String) session.getAttribute("un");
		String pwd = (String) session.getAttribute("pwd");
		String branch = (String) session.getAttribute("branch");
		credential = new Credential(un, pwd, alias, branch);
		//String[] idFromSearch = (String[]) param.get("search_clients");

		/*
		 * String[] parameters = (String[]) param.get("ht"); if (parameters !=
		 * null){
		 * 
		 * uz_card_main.setHeight(parameters[0]); }
		 */
		fieldsGroups = new ArrayList<Component>();
		fieldsGroups.add(card_applications);
		fieldsGroups.add(app_card);
		fieldsGroups.add(app_card_customer);
		fieldsGroups.add(app_card_ch_address);
		fieldsGroups.add(app_card_ch_person_info);
		fieldsGroups.add(app_card_account);
		fieldsGroups.add(job); // blankMap.put(key, value)
		fieldsGroups.add(btrt03Wnd);
		fieldsGroups.add(btrt20);
		fieldsGroups.add(btrt25);
		fieldsGroups.add(btrt30Tail);

		//card_applications$is_st_emp.setChecked(false);
		job$not_st_emp.setVisible(true);
		job$st_emp.setVisible(false);

		app_card_account$account_number.addEventListener("onCtrlKey", new EventListener() {

			@Override
			public void onEvent(Event event) throws Exception {
				if (((KeyEvent) event).getKeyCode() == Constants.KeyCode.F9)
					openSearchAccountWindow();

			}
		});

		job$work_id.addEventListener("onCtrlKey", new EventListener() {

			@Override
			public void onEvent(Event event) throws Exception {
				if (((KeyEvent) event).getKeyCode() == Constants.KeyCode.F9)
					openSearchWorkIdWindow();

			}
		});

		job$acc_st_emp.addEventListener("onCtrlKey", new EventListener() {

			@Override
			public void onEvent(Event event) throws Exception {
				if (((KeyEvent) event).getKeyCode() == Constants.KeyCode.F9)
					openSearchStateEmpAccId();

			}
		});

		app_card_customer$vip_flag.setModel(
				new ListModelList(com.is.uzcard.UzCardService.getRefData(Constants.RefDataType.VIP_FLAG, alias)));

		Events.sendEvent(app_card_customer$vip_flag, new Event("onInitRender"));
		app_card_customer$vip_flag.setSelecteditem("CVIP0");
		app_card_customer$vip_flag.setDisabled(true);

		card_applications$contract_id.setModel(new ListModelList(UzCardService.getContractIdList(alias)));
		btrt03Wnd$contract_id.setModel(new ListModelList(UzCardService.getContractIdList(alias)));

		app_card_ch_person_info$sex.setModel(
				new ListModelList(com.is.uzcard.UzCardService.getRefData(Constants.RefDataType.P_SEX, alias)));

		app_card_ch_person_info$residence.setModel(
				new ListModelList(com.is.uzcard.UzCardService.getRefData(Constants.RefDataType.REZIDENCE, alias)));
		app_card_ch_person_info$residence.setSelecteditem("RSDN1");

		app_card_ch_person_info$p_id_type.setModel(
				new ListModelList(com.is.uzcard.UzCardService.getRefData(Constants.RefDataType.P_ID_TYPE, alias)));
		Events.sendEvent(app_card_ch_person_info$p_id_type, new Event("onInitRender"));
		app_card_ch_person_info$p_id_type.setSelecteditem("IDTP1");

		app_card_ch_address$address_type.setModel(
				new ListModelList(com.is.uzcard.UzCardService.getRefData(Constants.RefDataType.ADDRESS_TYPE, alias)));
		Events.sendEvent(app_card_ch_address$address_type, new Event("onInitRender"));
		app_card_ch_address$address_type.setSelecteditem("ADDR1");

		app_card_ch_address$region.setModel(new ListModelList(com.is.uzcard.UzCardService.getRegionList(alias)));

		app_card_ch_address$country.setModel(new ListModelList(com.is.uzcard.UzCardService.getCountryList(alias)));
		Events.sendEvent(app_card_ch_address$country, new Event("onInitRender"));
		app_card_ch_address$country.setSelecteditem("860");

		btrt20$p_id_type.setModel(new ListModelList(UzCardService.getRefData(Constants.RefDataType.P_ID_TYPE, alias)));
		Events.sendEvent(btrt20$p_id_type, new Event("onInitRender"));
		btrt20$p_id_type.setSelecteditem("IDTP1");

		/*
		 * eq = EventQueues.lookup("CRM_CLIENTS", EventQueues.DESKTOP, true);
		 * eq.subscribe(new EventListener() {
		 * 
		 * @Override public void onEvent(Event event) throws Exception { client
		 * = (Customer) event.getData(); if (!desktop.isAlive() ||
		 * !self.getPage().isAlive()) { eq.unsubscribe(this); return; } if
		 * (client != null) cust.setValue(client.getName());
		 * 
		 * if (client != null) {
		 * 
		 * Link lnk = Bf_crm_clientsService.get_link_crm_br(
		 * value.getId_client(), alias); if (lnk != null) { crm_client =
		 * Bf_crm_clientsService .getBf_crm_clients(lnk.getCrm_customer_id());
		 * 
		 * fillFields(client); cust.setValue(client.getName()); // }
		 * 
		 * } else fillFields(new Customer());// очищаем форму
		 * 
		 * } });
		 */

		actionComboBox.setModel((new ListModelList(com.is.uzcard.UzCardService.getActionsList(alias))));

		openAcc$dataGrid.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				CardMaskInfo cmInfo = (CardMaskInfo) data;

				item.setValue(data);
				item.appendChild(new Listcell(cmInfo.getAcc_bal()));
				item.appendChild(new Listcell(cmInfo.getAcc_currency()));
				item.appendChild(new Listcell(cmInfo.getAcc_num()));
				item.addEventListener("onDoubleClick", new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						Listitem item = openAcc$dataGrid.getSelectedItem();
						CardMaskInfo cmInfo = (CardMaskInfo) item.getValue();
						cmInfo.setClient_id(card_applications$customer_id.getValue());
						cmInfo.setClient_name(card_applications$customer_desc.getValue());
						cmInfo.setContract_id(card_applications$contract_id.getValue());
						Res res = UzCardService.openAccount(cmInfo, credential);
						if (res.getCode() != 0) {
							alert(res.getName());
						} else {
							app_card_account$account_number.setValue(res.getName());
							openAcc.setVisible(false);
						}

					}
				});

			}

		});

		findAcc$dataGrid.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				AccountInfo accountInfo = (AccountInfo) data;

				item.setValue(data);
				item.appendChild(new Listcell(accountInfo.getAccountId()));
				item.appendChild(new Listcell(accountInfo.getAccountName()));

			}
		});

		btrt20$cardList.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				BTRT btrt20 = (BTRT) data;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				item.setValue(btrt20);
				item.appendChild(new Listcell(btrt20.getCustomer_id()));
				item.appendChild(new Listcell(btrt20.getSecond_name()));
				item.appendChild(new Listcell(btrt20.getFirst_name()));
				item.appendChild(new Listcell(btrt20.getPatronymic()));
				item.appendChild(new Listcell(dateFormat.format(btrt20.getBirth_date())));
				item.appendChild(new Listcell(btrt20.getP_id_series()));
				item.appendChild(new Listcell(btrt20.getP_id_number()));
				item.appendChild(new Listcell(btrt20.getCard_number()));
				item.appendChild(new Listcell(String.valueOf(btrt20.getApp_id().intValue())));
				item.appendChild(new Listcell(btrt20.getEmbossed_ch_name()));
				item.appendChild(new Listcell(btrt20.getContract_id()));
				item.appendChild(new Listcell(btrt20.getBranch()));

			}
		});
		cardList.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				Card card = (Card) data;
				// SimpleDateFormat dateFormat = new
				// SimpleDateFormat("dd.MM.yyyy");
				item.setValue(card);
				item.appendChild(new Listcell(card.getBranch()));
				item.appendChild(new Listcell(card.getCard_number()));
				item.appendChild(new Listcell(card.getCard_type()));
				item.appendChild(new Listcell(card.getDef_atm_account().substring(5)));
				item.appendChild(new Listcell(card.getExpiration_date()));
				item.appendChild(new Listcell(card.getCard_status()));
				item.appendChild(new Listcell(card.getContract_id()));
				item.appendChild(new Listcell(card.getHot_card_status()));

			}
		});

		btrt25$cardList.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				BTRT btrt25 = (BTRT) data;
				item.setValue(btrt25);
				item.appendChild(new Listcell(btrt25.getBranch()));
				item.appendChild(new Listcell(btrt25.getClient()));
				item.appendChild(new Listcell(btrt25.getSecond_name()));
				item.appendChild(new Listcell(btrt25.getFirst_name()));
				item.appendChild(new Listcell(btrt25.getPatronymic()));
				item.appendChild(new Listcell(btrt25.getAcc()));
				item.appendChild(new Listcell(btrt25.getCard_number()));
				item.appendChild(new Listcell(btrt25.getCardExpirationDate()));
				item.appendChild(new Listcell(btrt25.getContract_id()));
				item.appendChild(new Listcell(btrt25.getPerson_id()));

			}
		});

		appList01152030.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object data) throws Exception {
				AppList appList = (AppList) data;
				item.setValue(appList);
				item.appendChild(new Listcell(appList.getBranch()));
				item.appendChild(new Listcell(appList.getClient()));
				item.appendChild(new Listcell(appList.getFio()));
				item.appendChild(new Listcell(appList.getState_name()));
				item.appendChild(new Listcell(String.valueOf(appList.getApp_id())));
				item.appendChild(new Listcell(appList.getError()));
				item.addEventListener("onClick", new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						appId = Long.toString(((AppList) appList01152030.getSelectedItem().getValue()).getApp_id());
						fileList01152030
								.setModel(new ListModelList(ModelService.getInOutFileByAppId(credential, appId)));

					}
				});
			}
		});

		fileList01152030.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem item, Object object) throws Exception {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				InOutFile inOutFile = (InOutFile) object;
				item.setValue(inOutFile);
				item.appendChild(new Listcell(inOutFile.getSentFileName()));
				item.appendChild(new Listcell(dateFormat.format(inOutFile.getSentFileName())));
				item.appendChild(new Listcell(inOutFile.getReceivedFileName()));
				item.appendChild(new Listcell(dateFormat.format(inOutFile.getReceiveDate())));

			}
		});

		String[] parameter = (String[]) param.get("search_clients");

		if (parameter != null) {// TODO

			dt.setVisible(false);
			top_div.setVisible(false);
			tb01_02_05_06.setVisible(false);
			tb30.setVisible(false);
			// crmDiv.setVisible(false);
			// selectedBtrt = "BTRT20";
			client = UzCardService.getCustomerById(credential, /* "99347287" */parameter[0], branch);// TODO

			cardListDiv.setVisible(true);
			// List<BTRT> list = UzCardService.getBTRT20List(branch,
			// parameter[0]);
			List<Card> list = UzCardService.getcardList(parameter[0], branch);
			if (list.isEmpty()) {
				// alert("У клиента нет карт!!");
				return;
			} else {
				/*
				 * for (Component window : fieldsGroups) {// скрываем все окна в
				 * форме window.setVisible(false); }
				 */
				top_div.setVisible(false);
				cardList.setModel(new ListModelList(list));
				// fillBTRT20Form(list.get(0));
				tb01_02_05_06.setVisible(false);
				tb30.setVisible(false);
				// crmDiv.setVisible(false);
				/*
				 * dt.setMode(Window.EMBEDDED); dt.setWidth("100%");
				 * dt.setClosable(false); dt.setVisible(true); dt.setTitle("");
				 * btrt20.setTitle("Карты клиента");
				 * btrt20$cardList.setVisible(true);
				 */
			}
			// setVisibleWindows(btrt20Fields);
			// filter = new BookFilter();

			// filter.setClient_code(parameter[0]);

			// showDeposit();

			// refreshModel(0);
		}

	}

	public void onClick$btnOpenCard() {
		dt.setVisible(true);
		// selectedBtrt = getSelectedBTRT();
		selectedBtrt = "BTRT01";
		cardListDiv.setVisible(false);

		for (Component window : fieldsGroups) {// скрываем все окна в форме
			window.setVisible(false);
		}

		fillFields(client);

		setVisibleWindows(btrt01Fields);
		tb01_02_05_06.setVisible(true);
		tb30.setVisible(false);
	}

	public void onClick$btnOpenCardbtrt2() {

		// selectedBtrt = getSelectedBTRT();
		if (!UzCardService.isExistClient(client.getBranch(), client.getId_client())) {
			alert("Клиент не найден!");
			return;
		}
		dt.setVisible(true);
		selectedBtrt = "BTRT02";
		cardListDiv.setVisible(false);

		for (Component window : fieldsGroups) {// скрываем все окна в форме
			window.setVisible(false);
		}

		fillFields(client);

		setVisibleWindows(btrt02Fields);
		tb01_02_05_06.setVisible(true);
		tb30.setVisible(false);
	}

	public void onClick$btnOpenCardbtrt3() {
		dt.setVisible(true);
		// selectedBtrt = getSelectedBTRT();

		selectedBtrt = "BTRT03";
		cardListDiv.setVisible(false);

		for (Component window : fieldsGroups) {// скрываем все окна в форме
			window.setVisible(false);
		}

		// fillFields(client);
		fillBTRT03Form();

		setVisibleWindows(btrt03Fields);
		tb01_02_05_06.setVisible(false);
		tb30.setVisible(false);
	}

	public void onClick$btnOpenCardbtrt20() {

		List<BTRT> list = UzCardService.getBTRT20List(client.getBranch(), client.getId_client());
		if (list.isEmpty()) {
			dt.setVisible(false);
			alert("У клиента нет карт!!");
			return;
		} else {
			dt.setVisible(true);
			// selectedBtrt = getSelectedBTRT();

			selectedBtrt = "BTRT20";
			btrt20$cardList.setModel(new ListModelList(list));
			fillBTRT20Form(list.get(0));
			tb01_02_05_06.setVisible(false);
			tb30.setVisible(false);
			cardListDiv.setVisible(false);

			for (Component window : fieldsGroups) {// скрываем все окна в форме
				window.setVisible(false);
			}
		}
		setVisibleWindows(btrt20Fields);
	}

	public void onClick$btnOpenCardbtrt30() {

		// alert("btrt30");
		btrt30 = UzCardService.getBTRT30(client.getId_client(), credential);
		selectedBtrt = "BTRT30";
		fillBTRT30Form(btrt30);
		cardListDiv.setVisible(false);
		tb01_02_05_06.setVisible(false);
		tb30.setVisible(true);
		dt.setVisible(true);
		for (Component window : fieldsGroups) {// скрываем все окна в форме
			window.setVisible(false);
		}
		setVisibleWindows(btrt30Fields);
	}

	public void onPaging$paging$findAcc(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) ((ForwardEvent) event.getOrigin()).getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
		findAcc$paging.setPageSize(_pageSize);
		model = new PagingListModelAccInfo(activePage, _pageSize, findAcc$searchFilterTextBox.getValue(),
				credential.getBranch(), currentFiterField);

		if (_needsTotalSizeUpdate) {
			_totalSize = model.getTotalSize(findAcc$searchFilterTextBox.getValue(), credential.getBranch(),
					currentFiterField);
			_needsTotalSizeUpdate = false;
		}

		findAcc$paging.setTotalSize(_totalSize);

		findAcc$dataGrid.setModel(model);
	}

	private void fillFields(History client) {
		try {
			card_applications$customer_id.setValue(client.getId_client());
			card_applications$customer_desc.setValue(client.getName());
			app_card_customer$inn.setValue(client.getP_code_tax_org());
			app_card_customer$okpo.setValue(client.getP_pension_sertif_serial());
			if (!CheckNull.isEmpty(client.getP_first_name())) {
				app_card_ch_person_info$first_name.setValue(client.getP_first_name());
				app_card_ch_person_info$second_name.setValue(client.getP_family());
				app_card_ch_person_info$patronymic.setValue(client.getP_patronymic());
			} else if (client.getName() != null) {
				String fullName[] = client.getName().split(" ");
				app_card_ch_person_info$first_name.setValue(fullName[0]);
				app_card_ch_person_info$second_name.setValue(fullName[1]);
				StringBuilder patronymic = new StringBuilder();
				for (int i = 2; i < fullName.length; i++) {
					patronymic.append(fullName[i]);

				}
				app_card_ch_person_info$patronymic.setValue(patronymic.toString());
			}

			if (!CheckNull.isEmpty(client.getName())) {
				if (Transliterator.isCyrillic(client.getName())) {
					String names[] = Transliterator.getTransliteratedNames(client.getName());
					StringBuilder res=new StringBuilder();
					StringBuilder res1=new StringBuilder();
					for(int i=0;i<names.length;i++){
						res.append(names[i]);
					}
					if(res.toString().length()>26){
						res1.append(names[0]);
						res1.append(names[1].substring(0, 1).toUpperCase());
						res1.append(names[2].substring(0,1).toUpperCase());
						app_card_account$embossed_ch_name.setValue(res1.toString());
						ISLogger.getLogger().error("Embossed_CH_Name :"+res1.toString());
					}
					
				} else {
					app_card_account$embossed_ch_name.setValue(client.getName());
					ISLogger.getLogger().error("Embossed_CH_Name_26 :"+client.getName());
				}
			}

			app_card_ch_person_info$p_id_number.setValue(client.getP_passport_number());
			app_card_ch_person_info$p_id_series.setValue(client.getP_passport_serial());
			app_card_ch_person_info$p_id_authority.setValue(client.getP_passport_place_registration());
			app_card_ch_address$address_line1.setValue(client.getP_post_address());
			app_card_ch_address$primary_phone.setValue(client.getP_phone_home());
			app_card_ch_address$mobile_phone.setValue(client.getP_phone_mobile());
			app_card_ch_address$email.setValue(client.getP_email_address());

			app_card_ch_person_info$sex.setSelecteditem("SEXT" + client.getP_code_gender());
			app_card_ch_person_info$residence.setSelecteditem("RSDN" + client.getCode_resident());
			// app_card_ch_person_info$p_id_type.setSelecteditem(value.getP_passport_type());
			app_card_ch_address$region.setSelecteditem(client.getP_code_adr_region());
			app_card_ch_address$country.setSelecteditem(client.getCode_country());

			app_card_ch_person_info$birth_date.setValue(client.getP_birthday());
			app_card_ch_person_info$p_id_issue_date.setValue(client.getP_passport_date_registration());
			// app_card_ch_person_info$security_id.setValue(client.get);
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}

	}

	private void fillBTRT03Form() {

		btrt03 = UzCardService.getBTRT03(client.getId_client(), credential.getBranch());
		if (btrt03 != null) {
			btrt03Wnd$client_id_label.setValue(btrt03.getClient());
			btrt03Wnd$account_label.setValue(btrt03.getAcc());
			btrt03Wnd$app_id_label.setValue(String.valueOf(btrt03.getApp_id().intValue()));
			btrt03Wnd$card_name_label.setValue(btrt03.getEmbossed_ch_name());
			btrt03Wnd$contract_id_label.setValue(btrt03.getContract_id());
			btrt03Wnd$filial_label.setValue(btrt03.getBranch());
			btrt03Wnd$first_name_label.setValue(btrt03.getFirst_name());
			btrt03Wnd$last_name_label.setValue(btrt03.getSecond_name());
			btrt03Wnd$patronymic_label.setValue(btrt03.getPatronymic());
			btrt03Wnd$state_label.setValue(btrt03.getState_name());
			btrt03Wnd$account.setValue(btrt03.getAcc());
		} else {
			alert("Клиент не найден!");
			dt.setVisible(false);
			btrt03Wnd.setVisible(false);
			cardListDiv.setVisible(true);
			btrtToolBar.setVisible(true);
			/*
			 * btrt03Wnd$client_id_label.setValue("");
			 * btrt03Wnd$account_label.setValue("");
			 * btrt03Wnd$app_id_label.setValue("");
			 * btrt03Wnd$card_name_label.setValue("");
			 * btrt03Wnd$contract_id_label.setValue("");
			 * btrt03Wnd$filial_label.setValue("");
			 * btrt03Wnd$first_name_label.setValue("");
			 * btrt03Wnd$last_name_label.setValue("");
			 * btrt03Wnd$patronymic_label.setValue("");
			 * btrt03Wnd$state_label.setValue("");
			 * btrt03Wnd$account.setValue("");
			 */
		}

	}

	private void fillBTRT20Form(BTRT btrt20) {
		btrt20$contract_id.setValue(btrt20.getContract_id());
		btrt20$birth_date.setValue(btrt20.getBirth_date());
		btrt20$embossed_ch_name.setValue(btrt20.getEmbossed_ch_name());
		btrt20$first_name.setValue(btrt20.getFirst_name());
		btrt20$second_name.setValue(btrt20.getSecond_name());
		btrt20$patronymic.setValue(btrt20.getPatronymic());
		btrt20$p_id_number.setValue(btrt20.getP_id_number());
		btrt20$p_id_series.setValue(btrt20.getP_id_series());

		btrt20$actionId.setModel(
				new ListModelList(UzCardService.getRefData(Constants.RefDataType.REISSUE, credential.getAlias())));
	}

	private void fillBTRT30Form(BTRT30 btrt30) {
		app_card_ch_person_info$first_name.setValue(btrt30.getNewFirstName());
		app_card_ch_person_info$second_name.setValue(btrt30.getSecond_name());
		app_card_ch_person_info$patronymic.setValue(btrt30.getNewPatronymic());
		app_card_ch_person_info$p_id_number.setValue(btrt30.getNewP_ID_number());
		app_card_ch_person_info$p_id_series.setValue(btrt30.getNewP_ID_series());
		app_card_ch_person_info$p_id_authority.setValue(btrt30.getNewP_ID_authority());
		app_card_ch_address$address_line1.setValue(btrt30.getNewAddressLine1());
		app_card_ch_address$address_line2.setValue(btrt30.getAddress_line2());
		app_card_ch_address$primary_phone.setValue(btrt30.getPrimary_phone());
		app_card_ch_address$mobile_phone.setValue(btrt30.getMobile_phone());
		app_card_ch_address$email.setValue(btrt30.getEmail());

		app_card_ch_person_info$sex.setSelecteditem(btrt30.getSex());
		app_card_ch_person_info$residence.setSelecteditem(btrt30.getResidence());
		app_card_ch_person_info$p_id_type.setSelecteditem(btrt30.getNewP_ID_type());
		app_card_ch_address$region.setSelecteditem(btrt30.getRegion());
		app_card_ch_address$country.setSelecteditem(btrt30.getCountry());
		app_card_ch_address$address_type.setSelecteditem(btrt30.getAddress_type());

		app_card_ch_person_info$birth_date.setValue(btrt30.getBirth_date());
		btrt30Tail$account_number.setValue(btrt30.getAcc());
		btrt30Tail$card_number.setValue(btrt30.getCard_number());
		app_card_ch_person_info$security_id.setValue(btrt30.getSecurity_id());
		app_card_ch_person_info$p_id_issue_date.setValue(btrt30.getP_id_issue_date());
	}
	
	private boolean check01020506BeforeSave() {
		boolean fl_err = false;
		String err = "";
		if (app_card_ch_person_info$p_id_number.getValue() == null
				|| ((!((app_card_ch_person_info$p_id_number.getValue()).matches("[0-9]+")))
						|| (app_card_ch_person_info$p_id_number.getValue().length() > 9))) {
			fl_err = true;
			err += "\nНомер документа";
		}
		if ((!((app_card_ch_person_info$p_id_series.getValue()).matches("[a-zA-Z]+")))
				|| (app_card_ch_person_info$p_id_series.getValue().length() > 2)) {
			fl_err = true;
			err += "\nСерия документа";
		}
		if ((!((app_card_ch_person_info$p_id_authority.getValue()).matches("[a-zA-Zа-яА-Я0-9\\s\\.\\,_\\/-]+")))
				|| (app_card_ch_person_info$p_id_authority.getValue().length() > 200)) {
			fl_err = true;
			err += "\n Где Выдан *";
		}
		/*
		 * if ((!((app_card_ch_person_info$first_name.getValue()).matches(
		 * "[a-zA-Zа-яА-Я']+"))) ||
		 * (app_card_ch_person_info$first_name.getValue().length() > 34)) {
		 * fl_err = true; err += "\nИмя"; } if
		 * ((!((app_card_ch_person_info$second_name.getValue()).matches(
		 * "[a-zA-Zа-яА-Я']+"))) ||
		 * (app_card_ch_person_info$second_name.getValue().length() > 20)) {
		 * fl_err = true; err += "\nФамилия"; } if
		 * ((!((app_card_ch_person_info$patronymic.getValue()).matches(
		 * "[a-zA-Zа-яА-Я']*"))) ||
		 * (app_card_ch_person_info$patronymic.getValue().length() > 20)) {
		 * fl_err = true; err += "\nОтчество"; }
		 */
		if ((CheckNull.isEmpty(app_card_ch_person_info$p_id_type.getValue()))) {
			fl_err = true;
			err += "\nТип документа";
		} else {
			if (app_card_ch_person_info$p_id_type.getValue().equals("IDTP1")
					&& app_card_ch_person_info$p_id_number.getValue().length() != 7) {
				fl_err = true;
				err += "\nНомер паспорта должно состоит из 7 цифр";
			}
		}

		String inn = app_card_customer$inn.getValue();
		if (inn == null) {
			fl_err = true;
			err += "\nИНН незаполнено";
		}

		if (!(inn.matches("[0-9]*")) || (inn.length() != 9)) {
			fl_err = true;
			err += "\nИНН должен состоять из 9 цифр";
		}
		char c = inn.charAt(0);
		if (c == '4' || c == '5' || c == '6') {
			fl_err = true;
			err += "У физического лица ИНН должен начинаться с цифр 4,5,6";
		}

		if (!checkNOfChar(inn, 8)) {
			fl_err = true;
			err += "ИНН должен содержать не более 8 одинаковых цифр";
		}

		if ((!((app_card_customer$okpo.getValue()).matches("[0-9]*")))
				|| (app_card_customer$okpo.getValue().length() > 9)) {
			fl_err = true;
			err += "\nОКРО";
		}

		if ((CheckNull.isEmpty(app_card_ch_address$country.getValue()))) {
			fl_err = true;
			err += "\nСтрана";
		}

		if ((CheckNull.isEmpty(app_card_ch_person_info$residence.getValue()))) {
			fl_err = true;
			err += "\nРезидент";
		}

		if (app_card_ch_person_info$residence.getValue().equals("RSDN0")
				&& app_card_ch_person_info$p_id_type.getValue().equals("IDTP1")) {
			fl_err = true;
			err += "\nУ нерезидентного физического лица неправильный тип документа ";
		}

		if ((CheckNull.isEmpty(app_card_ch_person_info$p_id_issue_date.getValue()))) {
			fl_err = true;
			err += "\nДата выдачи";
		}
		if ((CheckNull.isEmpty(app_card_ch_person_info$birth_date.getValue()))) {
			fl_err = true;
			err += "\nДата рождения";
		}
		if (app_card_ch_person_info$p_id_issue_date.getValue() == null) {
			alert("Дата выдачи документа");
			return false;
		}
		if (app_card_ch_person_info$p_id_issue_date.getValue().getYear()
				- app_card_ch_person_info$birth_date.getValue().getYear() < 16) {
			fl_err = true;
			err += "\nРазность между датой получения паспорта и датой рождениия не должна быть меньше, чем 16 лет";
		}
		if (app_card_ch_person_info$birth_date.getValue().getYear() < 10) {
			fl_err = true;
			err += "\nДата рождения не должна быть раньше 01.01.1910 года";
		}
		if (app_card_ch_person_info$p_id_issue_date.getValue().getYear() < 92) {
			fl_err = true;
			err += "\nДата выдачи паспорта не должна быть раньше 01.01.1992 года";
		}

		if (!app_card_ch_person_info$p_id_series.getValue().matches("[A-Z]*")
				|| app_card_ch_person_info$p_id_series.getValue().length() != 2) {
			fl_err = true;
			err += "\nСерия паспорта должна состоять из 2 символов в верхнем регистре";
		}
		if (CheckNull.isEmpty(app_card_customer$vip_flag.getValue())) {
			fl_err = true;
			err += "\nVIP code";
		}
		if (/*
			 * (!((app_card_ch_address$address_line1.getValue()).matches(
			 * "[a-zA-Zа-яА-Я0-9\\s\\.\\,_\\/-]*"))) ||
			 */(app_card_ch_address$address_line1.getValue().length() > 200)) {
			fl_err = true;
			err += "\nУлица, дом, квартира";
		}
		if ((!((app_card_ch_address$address_line2.getValue()).matches("[a-zA-Zа-яА-Я0-9\\s\\.\\,_\\/-]*")))
				|| (app_card_ch_address$address_line2.getValue().length() > 200)) {
			fl_err = true;
			err += "\nГород";
		}
		if (card_applications$contract_id.getValue().isEmpty()) {
			fl_err = true;
			err += "\nИдентификатор контракта";
		}
		/*
		 * if(app_card_ch_per){
		 * 
		 * }
		 */

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return false;
		}

		if (!UzCardService.isAccountExists(client.getBranch(), app_card_account$account_number.getValue(),
				card_applications$customer_id.getValue())) {
			alert("Такого счёта не существует");
			return false;
		}
		return true;
	}

	public void onClick$btnSave$dt() {
		

		btrt.setFirst_name(app_card_ch_person_info$first_name.getValue());
		btrt.setSecond_name(app_card_ch_person_info$second_name.getValue());
		btrt.setPatronymic(app_card_ch_person_info$patronymic.getValue());
		btrt.setBirth_date(app_card_ch_person_info$birth_date.getValue());
		// btrt.setCompany_name(app_card_ch_person_info$company_name.getValue());
		btrt.setSecurity_id(app_card_ch_person_info$security_id.getValue());
		btrt.setSex(app_card_ch_person_info$sex.getValue());
		btrt.setResidence(app_card_ch_person_info$residence.getValue());
		btrt.setP_id_type(app_card_ch_person_info$p_id_type.getValue());
		btrt.setP_id_series(app_card_ch_person_info$p_id_series.getValue());
		btrt.setP_id_number(app_card_ch_person_info$p_id_number.getValue());
		btrt.setP_id_authority(app_card_ch_person_info$p_id_authority.getValue());
		btrt.setP_id_issue_date(app_card_ch_person_info$p_id_issue_date.getValue());
		btrt.setAddress_type(app_card_ch_address$address_type.getValue());
		btrt.setAddress_line1(app_card_ch_address$address_line1.getValue());
		btrt.setAddress_line2(app_card_ch_address$address_line2.getValue());
		btrt.setRegion(app_card_ch_address$region.getValue());
		btrt.setCountry(app_card_ch_address$country.getValue());
		btrt.setPrimary_phone(app_card_ch_address$primary_phone.getValue());
		btrt.setMobile_phone(app_card_ch_address$mobile_phone.getValue());
		btrt.setEmail(app_card_ch_address$email.getValue());
		btrt.setEmbossed_ch_name(app_card_account$embossed_ch_name.getValue());
		btrt.setCompany_name_card(app_card$company_name_card.getValue());
		btrt.setAccount_number(app_card_account$account_number.getValue());
		btrt.setCurrency(app_card_account$currency.getValue());
		btrt.setEdtWorkId(job$work_id.getValue());
		btrt.setStEmpId(job$acc_st_emp.getValue());

		btrt.setPerson_id("0");
		btrt.setCard_number("");
		btrt.setCard_type("1A");
		btrt.setIs_primary(1);
		btrt.setAccount_type("ACCTD");
		// btrt01.setExpiration_date("")

		if (selectedBtrt.equals(Constants.BTRT01)) {
			btrt.setRec_number(2.0);
			btrt.setCustomer_id(card_applications$customer_id.getValue());
			btrt.setContract_id(card_applications$contract_id.getValue());
			btrt.setVip_flag(app_card_customer$vip_flag.getValue());

			btrt.setCustomer_desc(card_applications$customer_desc.getValue());
			btrt.setInn(app_card_customer$inn.getValue());
			btrt.setOkpo(app_card_customer$okpo.getValue());
		} else if (selectedBtrt.equals(Constants.BTRT02)) {
			btrt.setRec_number(2.0);
			btrt.setContract_id(card_applications$contract_id.getValue());
			btrt.setCustomer_id(card_applications$customer_id.getValue());
			btrt.setDef_atm_account("");
			btrt.setDef_pos_account("");
		} else if (selectedBtrt.equals(Constants.BTRT05)) {
			btrt.setRec_number(2.0);
			btrt.setCustomer_id(card_applications$customer_id.getValue());
			btrt.setContract_id(card_applications$contract_id.getValue());
			btrt.setVip_flag(app_card_customer$vip_flag.getValue());

			btrt.setCustomer_desc(card_applications$customer_desc.getValue());
			btrt.setInn(app_card_customer$inn.getValue());
			btrt.setOkpo(app_card_customer$okpo.getValue());
		} else if (selectedBtrt.equals(Constants.BTRT06)) {
			btrt.setRec_number(2.0);
			btrt.setContract_id(card_applications$contract_id.getValue());
			btrt.setCustomer_id(card_applications$customer_id.getValue());
			btrt.setDef_atm_account("");
			btrt.setDef_pos_account("");
			btrt.setIs_primary(1);

		}

		Res res = com.is.uzcard.UzCardService.btrt_01_02_05_06(btrt, selectedBtrt, credential);
		if (res != null) {
			alert(res.getName());
			if (res.getCode() == 0) {
				dt.setVisible(false);
				cardListDiv.setVisible(true);
				btrtToolBar.setVisible(true);

			}
		}
	}

	private boolean checkNOfChar(String str, int n) {
		int[] arr = new int[10];
		boolean result = true;

		for (int i = 0; i < str.length(); i++)
			arr[Character.getNumericValue(str.charAt(i))]++;
		for (int b : arr) {
			if (b > n) {
				result = false;
				break;
			}

		}
		return result;

	}

	public void onClick$btnSave$btrt03Wnd$dt() {
		Res res;
		if (btrt03Wnd$contract_id.getValue().isEmpty()) {
			alert("Не выбран идентификатор контракта!");
			return;
		}
		if (btrt03Wnd$account.getValue().isEmpty()) {
			alert("Отсутствует номер счёта!");
			return;
		}
		if (!UzCardService.isAccountExists(btrt03.getBranch(), btrt03.getAcc(), btrt03.getClient(), 2)) {
			alert("Нет такого счёта!");
			return;
		}
		if (btrt03 != null) {
			res = UzCardService.btrt_03(btrt03, btrt03Wnd$contract_id.getValue(), credential);
			if (res == null) {
				dt.setVisible(false);
				cardListDiv.setVisible(true);
				btrtToolBar.setVisible(true);
				alert("Операция прошла успешно!");
			} else {
				alert(res.getName());
			}
		} else
			alert("Ошибка");
	}

	public void onClick$btnSave$btrt20$dt() throws InterruptedException {
		if (btrt20$cardList.getSelectedItem() == null) {
			alert("Выберите карту!!!");
			return;
		}
		final BTRT btrt20 = (BTRT) btrt20$cardList.getSelectedItem().getValue();

		if (btrt20$actionId.getValue().isEmpty()) {
			alert("Не выбрана команда!!!");
			return;
		}
		btrt20.setApp_type(selectedBtrt);
		btrt20.setRec_number(2.0);
		btrt20.setContract_id(btrt20$contract_id.getValue());
		btrt20.setFirst_name(btrt20$first_name.getValue());
		btrt20.setSecond_name(btrt20$second_name.getValue());
		btrt20.setPatronymic(btrt20$patronymic.getValue());
		btrt20.setBirth_date(btrt20$birth_date.getValue());
		btrt20.setP_id_type(btrt20$p_id_type.getValue());
		btrt20.setP_id_series(btrt20$p_id_series.getValue());
		btrt20.setP_id_number(btrt20$p_id_number.getValue());
		btrt20.setEmbossed_ch_name(btrt20$embossed_ch_name.getValue());
		Messagebox.show("Вы согласны отправить запрос по карте " + btrt20.getCard_number() + " ?", "УзКарт",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onOK")) {
							Res res20 = UzCardService.btrt_20(btrt20, btrt20$actionId.getValue(), credential);
							if (res20 == null) {
								dt.setVisible(false);
								alert("Операция прошла успешно!!!");
								cardListDiv.setVisible(true);
								btrtToolBar.setVisible(true);
							} else {
								alert(res20.getName());
							}

						}

					}
				});
	}

	public void onClick$btnSave$btrt25$dt() throws InterruptedException {
		if (btrt25$cardList.getSelectedItem() == null) {
			alert("Выберите карту!!!");
			return;
		}
		final BTRT btrt20 = (BTRT) btrt25$cardList.getSelectedItem().getValue();

		if (btrt25$limit.getValue().isEmpty()) {
			alert("Не заполнено поле \"Сумма лимита \"!!!");
		} else if (!(btrt25$limit.getValue().matches("[0-9]+"))) {
			alert("Поле \"Сумма лимита \" должно содержать только цифры");
		}

		Messagebox.show("Сформировать BTRT04 для счёта " + btrt20.getAccount_number() + " , сумма: "
				+ btrt25$limit.getValue() + " ?", "УзКарт", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener() {

					@Override
					public void onEvent(Event event) throws Exception {
						if (event.getName().equals("onOK")) {
							Res res25 = UzCardService.btrt_25(btrt20, btrt25$limit.getValue(), credential);
							if (res25 == null) {
								dt.setVisible(false);
								alert("Операция прошла успешно!!!");
							} else {
								alert(res25.getName());
							}

						}

					}
				});

	}

	public void onClick$btnSave30$dt() {
		Res res30 = UzCardService.btrt30(btrt30, credential);

		if (res30 == null) {
			dt.setVisible(false);
			alert("Операция прошла успешно!!!");
			cardListDiv.setVisible(true);
			btrtToolBar.setVisible(true);
		} else {
			alert(res30.getName());
		}
	}

	public void onSelect$actionComboBox(Event event) {

		if (client == null) {
			alert("Не вбран клинент!!");
			return;
		}
		dt.setVisible(true);
		selectedBtrt = getSelectedBTRT();

		for (Component window : fieldsGroups) {// скрываем все окна в форме
			window.setVisible(false);
		}

		if (selectedBtrt.equals(Constants.BTRT01)) {
			fillFields(client);
			setVisibleWindows(btrt01Fields);
			tb01_02_05_06.setVisible(true);
			tb30.setVisible(false);
		} else if (selectedBtrt.equals(Constants.BTRT03)) {
			setVisibleWindows(btrt03Fields);
			if (client != null) {
				fillBTRT03Form();
			}
			tb01_02_05_06.setVisible(false);
			tb30.setVisible(false);

		} else if (selectedBtrt.equals(Constants.BTRT02)) {
			fillFields(client);
			setVisibleWindows(btrt02Fields);
			tb01_02_05_06.setVisible(true);
			tb30.setVisible(false);
		} else if (selectedBtrt.equals(Constants.BTRT05)) {
			fillFields(client);
			setVisibleWindows(btrt05Fields);
			tb01_02_05_06.setVisible(true);
			tb30.setVisible(false);
		} else if (selectedBtrt.equals(Constants.BTRT06)) {
			fillFields(client);
			setVisibleWindows(btrt06Fields);
			tb01_02_05_06.setVisible(true);
			tb30.setVisible(false);

		} else if (selectedBtrt.equals(Constants.BTRT20)) {
			List<BTRT> list = UzCardService.getBTRT20List(credential.getBranch(), client.getId_client());
			if (list.isEmpty()) {
				dt.setVisible(false);
				alert("У клиента нет карт!!");
				return;
			} else {
				btrt20$cardList.setModel(new ListModelList(list));
				fillBTRT20Form(list.get(0));
				tb01_02_05_06.setVisible(false);
				tb30.setVisible(false);
			}
			setVisibleWindows(btrt20Fields);

		} else if (selectedBtrt.equals(Constants.BTRT25)) {
			List<BTRT> list = UzCardService.getBTRT25List(credential.getBranch(), client.getId_client());
			if (list.isEmpty()) {
				dt.setVisible(false);
				alert("У клиента нет карт!!");
				return;
			} else {
				btrt25$cardList.setModel(new ListModelList(list));
				tb01_02_05_06.setVisible(false);
				tb30.setVisible(false);
			}
			setVisibleWindows(btrt25Fields);
		} else if (selectedBtrt.equals(Constants.BTRT30)) {
			setVisibleWindows(btrt30Fields);
			btrt30 = UzCardService.getBTRT30(client.getId_client(), credential);
			fillBTRT30Form(btrt30);
			tb01_02_05_06.setVisible(false);
			tb30.setVisible(true);

		}

	}

	private String getSelectedBTRT() {
		String selectedBtrt;
		selectedBtrt = actionComboBox.getText();
		actionComboBox.setSelectedIndex(-1);
		return selectedBtrt.substring(0, selectedBtrt.indexOf("-") - 1).trim();
	}

	private void setVisibleWindows(int[] fields) {
		for (int i : fields)
			fieldsGroups.get(i).setVisible(true);
	}

	/**
	 * @param event
	 */
	public void onChange$work_id$job$dt(InputEvent event) {
		String name = UzCardService.getWorkName(credential.getBranch(), job$work_id.getValue());
		if (name != null && !name.isEmpty()) {
			job$work_name.setValue(name);
		} else {
			alert("Организация не найдена");
		}
	}

	public void onClick$save_work_name_button$job$dt() throws WrongValueException, Exception {// TODO
		if (job$work_id.getValue().isEmpty() || job$work_name.getValue().isEmpty()) {
			alert("Не заполнены необходимые поля");
			return;
		}
		if (app_card_account$account_number.getValue().isEmpty()) {
			alert("Не указан номер счёта Плательщика!");
			return;
		}
		if (!UzCardService.saveNotStateEmployer(client.getBranch(), job$work_id.getValue(), client.getId_client(),
				app_card_account$account_number.getValue()))
			;

	}

	public void onClick$save_st_name_button$job$dt() throws WrongValueException, SQLException {
		if (job$acc_st_emp.getValue().isEmpty() || job$inn_st_emp.getValue().isEmpty()) {
			alert("Не заполнены необходимые поля");
			return;
		}
		if (!UzCardService.saveStEmpAcc(job$acc_st_emp.getValue(), job$inn_st_emp.getValue(),
				card_applications$customer_id.getValue(), credential.getBranch()))
			alert("Бюджетный счёт не найден!!!");
	}

	public void onClick$buttonOpenAcc$app_card_account$dt() {
		String err = "Не заполнены поля:\n";
		boolean f_err = false;
		if (CheckNull.isEmpty(card_applications$customer_id.getValue())) {
			err += "Идентификатор Клиента\n";
			f_err = true;
		}
		if (CheckNull.isEmpty(card_applications$customer_desc.getValue())) {
			err += "ФИО\n";
			f_err = true;
		}
		if (CheckNull.isEmpty(card_applications$contract_id.getValue())) {
			err += "Идентификатор контракта\n";
			f_err = true;
		}
		if (f_err) {
			alert(err);
			return;
		}
		openAcc.setVisible(true);
		openAcc$dataGrid.setModel(new BindingListModelList(
				UzCardService.getCardMaskInfoList(card_applications$contract_id.getValue()), false));
	}

	/**
	 * 
	 */
	public void onClick$btn_ok$openAcc() {

		Listitem item = openAcc$dataGrid.getSelectedItem();
		CardMaskInfo cmInfo = (CardMaskInfo) item.getValue();
		cmInfo.setClient_id(card_applications$customer_id.getValue());
		cmInfo.setClient_name(card_applications$customer_desc.getValue());
		cmInfo.setContract_id(card_applications$contract_id.getValue());
		// String accountId = UzCardService.openAccount(cmInfo, credential);
		Res res = UzCardService.openAccount(cmInfo, credential);
		if (res.getCode() != 0) {
			alert(res.getName());
		} else {
			app_card_account$account_number.setValue(res.getName());
			openAcc.setVisible(false);
		}

		return;
	}

	public void onClick$btn_ok$findAcc() {
		Listitem listitem = findAcc$dataGrid.getSelectedItem();
		AccountInfo aInfo = (AccountInfo) listitem.getValue();
		app_card_account$account_number.setValue(aInfo.getAccountId());
		findAcc.setVisible(false);
		return;
	}

	public void onClick$applyFilterBtn$findAcc() {// TODO Связать с пейджингом
		_needsTotalSizeUpdate = true;
		refreshModel(0);
	}

	public void onClick$btn_ok_work$findAcc() {
		// findAcc$dataGrid.getPaginal().
		Listitem listitem = findAcc$dataGrid.getSelectedItem();
		AccountInfo aInfo = (AccountInfo) listitem.getValue();
		job$work_id.setValue(aInfo.getAccountId());
		job$work_name.setValue(aInfo.getAccountName());
		findAcc.setVisible(false);
		return;
	}

	public void onClick$btn_ok_st_emp$findAcc() {
		Listitem listitem = findAcc$dataGrid.getSelectedItem();
		AccountInfo aInfo = (AccountInfo) listitem.getValue();
		job$acc_st_emp.setValue(aInfo.getAccountId());
		String string = aInfo.getAccountName();
		if (string != null) {
			string = string.trim();
			job$inn_st_emp.setValue(string.substring(0, string.indexOf(" ") - 1));// TODO
																					// Проверить
			job$name_st_emp.setValue(string.substring(string.indexOf(" ")));
		}
		findAcc.setVisible(false);
	}

	private void openSearchAccountWindow() {
		if (CheckNull.isEmpty(card_applications$customer_id.getValue())) {
			alert("Не заполнено поле Идентификатор Клиента");
			return;
		}
		_needsTotalSizeUpdate = true;
		findAcc$dataGrid.getItems().clear();
		// refreshModel(_startPageNumber);
		findAcc.setVisible(true);
		findAcc$btn_ok.setVisible(true);
		findAcc$btn_ok_work.setVisible(false);
		findAcc$btn_ok_st_emp.setVisible(false);
		findAcc$dataGrid.setModel(new BindingListModelList(
				UzCardService.getAccountInfoList(credential.getBranch(), card_applications$customer_id.getValue()),
				false));
	}

	private void openSearchWorkIdWindow() {

		_needsTotalSizeUpdate = true;
		findAcc.setVisible(true);
		findAcc$btn_ok.setVisible(false);
		findAcc$btn_ok_work.setVisible(true);
		findAcc$btn_ok_st_emp.setVisible(false);
		// findAcc$dataGrid.getItems().clear();
		currentFiterField = Constants.FILTER_FIELD_TYPE.NOT_STATE_EMP;
		refreshModel(_startPageNumber);
		// findAcc$dataGrid.setModel(new BindingListModelList(UzCardService
		// .getWorkInfoList(branch), false));
	}

	private void openSearchStateEmpAccId() {
		_needsTotalSizeUpdate = true;
		findAcc.setVisible(true);
		findAcc$btn_ok.setVisible(false);
		findAcc$btn_ok_work.setVisible(false);
		findAcc$btn_ok_st_emp.setVisible(true);
		// findAcc$dataGrid.getItems().clear();
		currentFiterField = Constants.FILTER_FIELD_TYPE.STATE_EMP;
		refreshModel(_startPageNumber);
		// findAcc$dataGrid.setModel(new BindingListModelList(UzCardService
		// .getStEmpInfoList(), false));
	}

	public void onSelect$tabbox() {// TODO
		if (tabbox.getSelectedIndex() == 1) {
			if (selectedBtrt.equals("BTRT01")) {
				grdAppL01152030.setVisible(true);
				appList01152030
						.setModel(new ListModelList(ModelService.getBTRTAppList(credential, client.getId_client())));
				// fileList01152030.setModel(new
				// ListModelList(ModelService.getInOutFileByAppId(credential,
				// appId)));
			}
		}
	}

	public void onCheck$is_st_emp$card_applications$dt() {
		job$not_st_emp.setVisible(!card_applications$is_st_emp.isChecked());
		job$st_emp.setVisible(card_applications$is_st_emp.isChecked());
		job$not_st_emp.setValue("");
		job$st_emp.setValue("");
	}

	/*
	 * // Omitted... public app_card_merchant_level getCurrent() { return
	 * current; }
	 * 
	 * public void setCurrent(app_card_merchant_level current) { this.current =
	 * current; }
	 */
}
