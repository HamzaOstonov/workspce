package com.is.tieto_capital.customer;

import capitalBank.issuing_v_01_02_xsd.KeyType_Agreement;
import capitalBank.issuing_v_01_02_xsd.ListType_CardInfo;
import capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo;
import capitalBank.issuing_v_01_02_xsd.OperationResponseInfo;
import capitalBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_ActivateCard_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import capitalBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_Customer;
import capitalBank.issuing_v_01_02_xsd.RowType_CustomerCustomInfo;
import capitalBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_EditAccount_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_InstantToReal_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import capitalBank.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import com.is.tieto_globuz.tietoAccount.GlobuzAccount;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountFilter;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountService;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.LtLogger;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountService.actions_for_acc;
import com.is.report.DPrint;
import com.is.tieto_capital.Constants;
import com.is.tieto_capital.ReportFields;
import com.is.tieto_capital.Reports;
import com.is.tieto_capital.cardApproval.CardApproval;
import com.is.tieto_capital.cardApproval.CardApprovalService;
import com.is.tieto_capital.customer.CustomerService.link;
import com.is.tieto_capital.tieto.AccInfo;
import com.is.tieto_capital.tieto.Agreement;
import com.is.tieto_capital.tieto.CardInfo;
import com.is.tieto_capital.tieto.Tclient;
import com.is.tieto_capital.tieto.TclientFilter;
import com.is.tieto_capital.tieto.TclientService;
import com.is.tieto_capital.tieto.TietoCardSetting;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.NilProvider;
import com.is.utils.RefCBox;
import com.is.utils.Res;

import com.is.tieto_capital.accApproval.*;

/**
 * @author dmitriy
 *
 */
public class AddCstViewCtrl extends GenericForwardComposer
{
	private static NilProvider np = null;
	public static Window customermain;
	private Window addCust, addTieto, printwnd, accwnd, addCustomer,
		accounts, add_everywhere, addwnd, show_cards, blockwnd, bt_rest_confirmation_wnd;
	public CustomerFilter filter = new CustomerFilter();
	public CustomerFilter bfilter = new CustomerFilter();
	private Paging bankdataPaging;
	private com.is.tieto_capital.customer.PagingListModel bmodel = null;
	private com.is.tieto_capital.tieto.PagingListModel tmodel = null;
	private boolean _needsTotalSizeUpdate = true;
	
	private Menupopup editPopup;
	private AnnotateDataBinder binder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
	
	private Listbox addCustomer$dataGrid, bank_customers, branch_customers, addwnd$accGrid, show_cards$accGrid, accounts$accGrid;
	
	private Textbox addTieto$aclient, addTieto$abank_c, addTieto$aclient_b, addTieto$acl_type, addTieto$acln_cat, addTieto$af_names, addTieto$asurname, addTieto$atitle, addTieto$am_name, addTieto$ar_street, addTieto$ar_city, addTieto$ar_cntry,
		addTieto$ausrid, addTieto$astatus, addTieto$asearch_name, addTieto$asex, addTieto$aserial_no, addTieto$aissued_by, blockwnd$txtstopcauses;
	
	private Textbox addCustomer$add_cst_id, addCust$ap_post_address,
		addCust$ap_passport_serial, addCust$ap_passport_number, addCust$ap_passport_place_registration,
		addCust$ap_family, addCust$ap_first_name, addCust$ap_patronymic;
	
	private RefCBox addCust$ap_code_birth_region, addCust$ap_code_adr_region, addCust$ap_code_adr_distr, blockwnd$sstopcauses;
	
	private Datebox addTieto$ab_date, addTieto$adoc_since,
			add_everywhere$ap_passport_date_registration, add_everywhere$ap_passport_date_expiration, add_everywhere$ap_birthday;
	
	private RefCBox add_everywhere$acode_resident, add_everywhere$acode_country, add_everywhere$ap_code_tax_org, 
			add_everywhere$ap_code_citizenship, add_everywhere$ap_code_gender,
			add_everywhere$ap_code_nation, add_everywhere$ap_code_birth_region, add_everywhere$ap_code_birth_distr,
			add_everywhere$ap_type_document, add_everywhere$ap_code_adr_region, add_everywhere$ap_code_adr_distr, add_everywhere$ar_city;

	private Textbox add_everywhere$ap_birth_place, add_everywhere$ap_post_address,
			add_everywhere$ap_passport_serial, add_everywhere$ap_passport_number, add_everywhere$ap_passport_place_registration,
			add_everywhere$ap_number_tax_registration, add_everywhere$ap_phone_home,
			add_everywhere$ap_phone_mobile, add_everywhere$ap_email_address, 
			add_everywhere$ap_inps, add_everywhere$ap_family, add_everywhere$ap_first_name, add_everywhere$ap_patronymic,
			add_everywhere$acode_tel;
	
	private Textbox addCustomer$ap_birth_place, addCustomer$ap_post_address, addCustomer$ap_passport_serial, 
		addCustomer$ap_passport_number, addCustomer$ap_passport_place_registration, addCustomer$ap_number_tax_registration, 
		addCustomer$ap_phone_home, addCustomer$ap_phone_mobile, addCustomer$ap_email_address, addCustomer$ap_inps, addCustomer$ap_family,
		addCustomer$ap_first_name, addCustomer$ap_patronymic;
	
	private Textbox card;
	
	private RefCBox addCustomer$acode_resident, addCustomer$acode_country, addCustomer$ap_code_tax_org,
		addCustomer$ap_code_citizenship, addCustomer$ap_code_gender, addCustomer$ap_code_nation, addCustomer$ap_code_birth_region, 
		addCustomer$ap_code_birth_distr, addCustomer$ap_type_document,
		addCustomer$ap_code_adr_region, addCustomer$ap_code_adr_distr;
	
	private Datebox addCustomer$ap_passport_date_registration, addCustomer$ap_passport_date_expiration, addCustomer$ap_birthday;
	
	private Toolbarbutton bth = null, btb = null, btt = null, btbreak = null, bt_acc_insert = null, btedit = null, btacc = null;
	private Toolbarbutton btblock_card = null, btunblock_card = null, btrefresh_card = null, bt_acc_act = null;
	private Toolbarbutton addwnd$btn_add;
	private Toolbarbutton activateCard = null;
	private Toolbarbutton addwnd$btnReport;
	
	private RefCBox addwnd$sproduct;
	private CustomerService custServ = new CustomerService();
	
	public TclientFilter tfilter = new TclientFilter();

	
	
	private Customer current = new Customer();
	private Customer tcustomer = new Customer();
	private Tclient tietocl = new Tclient();
	private TietoCustomer tcust = new TietoCustomer();
	private TietoCustomer tmpTCust;
	private Tclient atcust = new Tclient();
	
	private int _pageSize = 20;
	private int _startPageNumber = 0;
	private int _starttPageNumber = 0;
	private int _totalSize = 0;
	
	private Grid addCust$addgrdl, addCust$addgrdr, addTieto$addtgrdl, addTieto$addtgrdr, addCustomer$addgrdl, 
		addCustomer$addgrdr, add_everywhere$addgrdl, add_everywhere$addgrdr;
	
	private String un, pwd, branch, alias;
	private Iframe printwnd$rpframe;
	private RefCBox accwnd$scurracc;
	private Toolbarbutton btrefresh_card_app, bt_block_card_acc, bt_unblock_card_acc, bt_close_card_acc;
	private Toolbarbutton accounts$btn_add, accounts$btn_cancel;
	
	private int add_to_ho;
	
	private static HashMap<String, String> _tstopCauses = null;
	private Customer cur_HO_customer = new Customer();
	private Customer cur_branch_customer = new Customer();
	
	private boolean is_ti = false, is_ho = false, is_br = false, is_acc = false;
	private boolean add_ti = false, add_ho = false, add_br = false;
	private boolean edit_ti = false, edit_ho = false, edit_br = false, fl_edit = false;
	private boolean fl_filter_card_set = false, edit_agree = false;
	
	private String cur_cl_acc, new_card_acc;
	private String curip;
	private int uid;
	
	private static AccInfo cur_card = new AccInfo();
	private CardInfo blockcard = new CardInfo();
	private static HashMap<Integer, Integer> used_ids;
	
	private int agre_nom_upd;
	private AccInfo cur_acc_info;
	private String cur_contract;
	private ConfirmationRepData cfrd;
	
	private capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy;
	private BigDecimal agre_nom;
	private HashMap<String, String> hashMapCurr = new HashMap<String, String>();
	private int _startpageCapital = 0;
	private Textbox blockwnd$card;
	private String EMPC_BANK_C, EMPC_GROUPC, EMPC_BINCOD;
	
	private String access[];
	private Textbox tietoFilterCode;
	private Boolean smart;
	
	
	
	private Window openCardWindow, openAddCardWindow;
	private RefCBox openCardWindow$products, openAddCardWindow$products;
	private Toolbarbutton openCardWindow$openCardButton, openCardWindow$cancelButton, openAddCardWindow$openCardButton ,openAddCardWindow$cancelButton;
	private Textbox openAddCardWindow$holderName;
	private GlobuzAccount openCardGlobuzAccount = null;
	
	private Toolbar tbBtnAddEverywhere, tbBtnCardOperations;
	private Div addwnd$openAccFunctions;
	private Textbox passportSerial, passportNumber;
	
	private Window createLinkWnd;
	private Textbox createLinkWnd$lastFour;
	private Toolbarbutton createLinkWnd$createLinkBtn, createLinkWnd$cancelBtn;
	
	private Toolbarbutton btn_add_everywhere;
	
	private boolean instantToRealUniq = false;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception, ConnectException, SQLException
	{
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		self.setAttribute("binder", binder);
		binder.bindBean("tietocl", this.tietocl);
		binder.loadAll();
		
		String[] parameter = (String[]) param.get("ht");
		access = (String[]) param.get("access");
		smart = param.containsKey("smart")&&((String[]) param.get("smart"))[0].equals("true");

//		TclientService.msSqlTest();

		
		
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 60;
			branch_customers.setRows(_pageSize);
			bank_customers.setRows(_pageSize);
		}
		
		if(!access[0].equals("open")) {
			tbBtnAddEverywhere.setVisible(false);
			tbBtnCardOperations.setVisible(false);
		}
		
		if(smart) {
			btn_add_everywhere.setLabel("Открыть клиента в Банке");
		}
		
		uid = (Integer) session.getAttribute("uid");
		curip = (String) session.getAttribute("curip");
		
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		
		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		//alias = "BANK1088";
		
		
		
		EMPC_BANK_C = ConnectionPool.getValue("EMPC_BANK_C", alias);
		EMPC_GROUPC = ConnectionPool.getValue("EMPC_GROUPC", alias);
		EMPC_BINCOD = TclientService.getEmpcBincodFromDB();
			
		
		
		tcust.setBranch(branch);

		_tstopCauses = TclientService.getHTstopCauses(alias);
		hashMapCurr = CustomerService.getCurr(alias);
		
		add_everywhere$ap_type_document.setModel((new ListModelList(CustomerService.getType_document(alias))));
		add_everywhere$ap_code_citizenship.setModel((new ListModelList(CustomerService.getCountry(alias))));
		add_everywhere$acode_country.setModel((new ListModelList(CustomerService.getCountry(alias))));		
		add_everywhere$ap_code_gender.setModel((new ListModelList(com.is.utils.RefDataService.getGender(alias))));
		add_everywhere$ap_code_nation.setModel((new ListModelList(com.is.utils.RefDataService.getNation(alias))));
		add_everywhere$ap_code_adr_distr.setModel((new ListModelList(CustomerService.getDistr(alias))));
		add_everywhere$acode_resident.setModel((new ListModelList(com.is.utils.RefDataService.getRezCl(alias))));
		
		if(smart) {
			addwnd$sproduct.setModel(new ListModelList(CustomerService.getSmart(alias)));
		}
		else {
			addwnd$sproduct.setModel((new ListModelList(com.is.utils.RefDataService.getOfrProd(alias, "P"))));
			
			//addwnd$sproduct.setModel((new ListModelList(CustomerService.getCardTypes(alias))));
		}
		
		add_everywhere$ap_code_adr_region.setModel((new ListModelList(CustomerService.getRegion(alias))));
		add_everywhere$ar_city.setModel((new ListModelList(CustomerService.getRegion(alias))));
		add_everywhere$ap_code_birth_distr.setModel((new ListModelList(CustomerService.getDistr(alias))));
		add_everywhere$ap_code_birth_region.setModel((new ListModelList(CustomerService.getRegion(alias))));
		add_everywhere$ap_code_tax_org.setModel((new ListModelList(CustomerService.getTax(alias))));
		
		
		
		openCardWindow$products.setModel(new ListModelList(com.is.utils.RefDataService.getOfrProd(alias, "P")));
		openAddCardWindow$products.setModel(new ListModelList(com.is.utils.RefDataService.getOfrProd(alias, "P")));
		
		try
		{
			issuingPortProxy = initNp(issuingPortProxy, alias);
		}
		catch (Exception e)
		{
			Messagebox.show("Coonection Problem :\n" + e.getLocalizedMessage(),
				e.getMessage(), Messagebox.RETRY | Messagebox.CANCEL, Messagebox.ERROR,
				new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							int answer = (Integer) event.getData();
							if (answer == Messagebox.RETRY)
							{
								issuingPortProxy = initNp(issuingPortProxy, alias);
							}
							else 
							{
								return;
							}
						}
					}
				);			
		}
		
		accounts$accGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				GlobuzAccount pAccInfo = (GlobuzAccount) data;
				
				List<actions_for_acc> act = GlobuzAccountService.getactions_for_acc(pAccInfo.getState(), alias);
				
				Listcell h_edit_cell = new Listcell();
				
				for (int i = 0; i < act.size(); i++)
				{
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel(act.get(i).getName());
					bt_acc_act.setAttribute("deal_group_id", act.get(i).getDeal_group());
					bt_acc_act.setAttribute("deal_id", act.get(i).getDeal_id());
					bt_acc_act.setAttribute("action_id", act.get(i).getAction_id());
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setAttribute("capt", act.get(i).getName());
					bt_acc_act.setImage("/images/down3.png");
					bt_acc_act.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							Res res = GlobuzAccountService.doAction_acc_ho(un, pwd,
									(Integer) event.getTarget().getAttribute("deal_group_id"),
									(Integer) event.getTarget().getAttribute("deal_id"),
									(GlobuzAccount) event.getTarget().getAttribute("acc"),
									(Integer) event.getTarget().getAttribute("action_id"),
									alias, true);
							
							if (res.getCode() != 0)
							{
								alert(res.getName());
								return;
							}
							
							GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
							acfilter.setClient(cur_branch_customer.getId_client());
							acfilter.setAcc_bal(Constants.ACC_22618);
							acfilter.setBranch(branch);
							acfilter.setCurrency(Constants.CURRENCY_USD);
							if(smart) {
								acfilter.setId_order("444");		
							}
							
							com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
							accounts$accGrid.setModel((ListModel) acc_model);
						}
						
					});
					
					if ((act.get(i).getAction_id() == 1) ||
						(act.get(i).getAction_id() == 2) ||
						(act.get(i).getAction_id() == 20))
						h_edit_cell.appendChild(bt_acc_act);
					}
					
					Listcell h_add_acc = new Listcell();
					if (pAccInfo.getState() == 2)
					{
						bt_acc_insert = new Toolbarbutton();
						bt_acc_insert.setLabel("Открыть доп. карту");
						bt_acc_insert.setAttribute("acc", pAccInfo);
						bt_acc_insert.setImage("/images/credit_card1.png");
						bt_acc_insert.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(Event event) throws Exception
							{
								openCardGlobuzAccount = (GlobuzAccount) event.getTarget().getAttribute("acc");
								
								openAddCardWindow.setVisible(true);
								
//								Res res = CustomerService.update_lnk_set_acc(un, pwd, branch, cur_branch_customer.getId_client() , acc, alias, issuingPortProxy);
//								if (res.getCode() != 1)
//								{
//									alert(res.getName());
//									return;
//								}
//								refreshModel(_startPageNumber);
//								accounts.setVisible(false);
							}
							
						});
						if(!smart) {
							h_add_acc.appendChild(bt_acc_insert);
						}
					}
					
					if(smart) {
						Toolbarbutton sm = new Toolbarbutton();
						sm.setLabel("Отправить данные");
						sm.setAttribute("acc", pAccInfo);
						sm.setImage("/images/credit_card1.png");
						sm.addEventListener(Events.ON_CLICK, new EventListener() {							
							@Override
							public void onEvent(Event event) throws Exception {
								
								openCardGlobuzAccount = (GlobuzAccount) event.getTarget().getAttribute("acc");
								
								instantToRealUniq = true;
								
								
								transliteration(openCardGlobuzAccount.getClient());
								
							
								
								
								
								add_everywhere.setVisible(true);
							}
						});
						h_add_acc.appendChild(sm);
						
					}
					
					Listcell h_add_acc_cell = new Listcell();
					if (pAccInfo.getState() == 2)
					{
						bt_acc_act = new Toolbarbutton();
						bt_acc_act.setLabel("Открыть карту");
						bt_acc_act.setAttribute("acc", pAccInfo);
						bt_acc_act.setImage("/images/credit_card1.png");
						bt_acc_act.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(Event event) throws Exception {
								openCardGlobuzAccount = (GlobuzAccount) event.getTarget().getAttribute("acc");
								
								openCardWindow.setVisible(true);
								
								// код переехал
							}
							
						});
						if(!smart) {
							h_add_acc_cell.appendChild(bt_acc_act);
						}
					}
					
					row.setValue(pAccInfo);
					row.appendChild(new Listcell(pAccInfo.getBranch()));
					row.appendChild(new Listcell(pAccInfo.getClient()));
					row.appendChild(new Listcell(pAccInfo.getId()));
					row.appendChild(new Listcell(getFnm(pAccInfo)));
					row.appendChild(new Listcell(pAccInfo.getCurrency()));
					row.appendChild(new Listcell(pAccInfo.getDate_open()==null?"":df.format(pAccInfo.getDate_open())));
					row.appendChild(new Listcell(pAccInfo.getId_order()));
					row.appendChild(new Listcell(GlobuzAccountService.get_account_state_caption(pAccInfo.getState(), alias)));
					row.appendChild(h_add_acc);
					row.appendChild(h_add_acc_cell);
				}
		});
		
		
		
		
		// oneyna bolasi
		show_cards$accGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(final Listitem row, Object data) throws Exception
			{
				CardInfo cardInfo = (CardInfo) data;
				row.setValue(cardInfo);
				
				
//				if((smart && cardInfo.getCARD_ACCT().substring(17).equals("444")) ||
//				   (smart && !cardInfo.getCARD_ACCT().substring(17).equals("444")))
//				
//				{
				
					
					
					Listcell bl_unbl = new Listcell();
					Listcell acc_act = new Listcell();
					
					Toolbarbutton bt_refresh_with_new = new Toolbarbutton();
					Toolbarbutton bt_rest_confirmation = new Toolbarbutton();
					btunblock_card = new Toolbarbutton();
					btblock_card = new Toolbarbutton();
					btrefresh_card = new Toolbarbutton();
					btrefresh_card_app = new Toolbarbutton();
					bt_block_card_acc = new Toolbarbutton();
					bt_close_card_acc = new Toolbarbutton();
					bt_unblock_card_acc = new Toolbarbutton();
					activateCard = new Toolbarbutton();
					
					btunblock_card.setWidth("100%");
					btblock_card.setWidth("100%");
					btrefresh_card.setWidth("100%");
					btrefresh_card_app.setWidth("100%");
					bt_block_card_acc.setWidth("100%");
					bt_close_card_acc.setWidth("100%");
					bt_unblock_card_acc.setWidth("100%");
					bt_refresh_with_new.setWidth("100%");
					bt_rest_confirmation.setWidth("100%");
					activateCard.setWidth("100%");
					
					btunblock_card.setImage("/images/+.png");
					btblock_card.setImage("/images/-.png");
					btunblock_card.setLabel("Разблокировать карту");
					btblock_card.setLabel("Блокировать карту");
					bt_rest_confirmation.setLabel("Документы о счете");
					bt_block_card_acc.setLabel("Перевести в неактивный");
					bt_block_card_acc.setImage("/images/+.png");
					bt_close_card_acc.setImage("/images/+.png");
					bt_close_card_acc.setLabel("Закрыть");
					bt_unblock_card_acc.setLabel("Перевести в активный");
					bt_unblock_card_acc.setImage("/images/+.png");
					
					btrefresh_card.setImage("/images/front1.png");
					btrefresh_card.setLabel("Перевыпустить карту");
					btrefresh_card_app.setImage("/images/file.png");
					bt_rest_confirmation.setImage("/images/file.png");
					btrefresh_card_app.setLabel("Заявление на перевыпуск карты");
					bt_refresh_with_new.setImage("/images/front1.png");
					bt_refresh_with_new.setLabel("Перевыпустить карту с новыми данными");
					activateCard.setImage("/images/+.png");
					activateCard.setLabel("Активировать Карту");
					activateCard.setAttribute("cardInfo", cardInfo);
					
					btblock_card.setAttribute("card", cardInfo);
					btunblock_card.setAttribute("card", cardInfo);
					
					btrefresh_card.setAttribute("acc", cardInfo);
					btrefresh_card_app.setAttribute("acc", cardInfo);
					bt_block_card_acc.setAttribute("card", cardInfo);
					bt_close_card_acc.setAttribute("card", cardInfo);
					bt_unblock_card_acc.setAttribute("card", cardInfo);
					bt_refresh_with_new.setAttribute("acc", cardInfo);
					bt_rest_confirmation.setAttribute("acc", cardInfo);
					
					
					Tclient ttt = tietocl;
					
					
					activateCard.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							CardInfo cardInfo = (CardInfo) event.getTarget().getAttribute("cardInfo");
							Calendar expiry = Calendar.getInstance();
	
							OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
							RowType_ActivateCard_Request parameters = new RowType_ActivateCard_Request();
							OperationResponseInfo responseInfo = new OperationResponseInfo();
							
							try {
						    	expiry.setTime(datef.parse(cardInfo.getEXPIRY()));
						    	
								// OperationConnectionInfo
								connectionInfo.setBANK_C(EMPC_BANK_C);
								connectionInfo.setGROUPC(EMPC_GROUPC);
								
								// RowType_ActivateCard_Request
								parameters.setCARD(cardInfo.getCARD());
								parameters.setBANK_C(EMPC_BANK_C);
								parameters.setGROUPC(EMPC_GROUPC);
								parameters.setEXPIRY(expiry);
								
								responseInfo = issuingPortProxy.activateCard(connectionInfo, parameters);
								if((responseInfo.getError_description() != null) || (responseInfo.getResponse_code().intValue() != 0)) {
					
									alert("Card hasn't been activated in TIETO\n" + responseInfo.getError_description());
								}
								else {
									alert("Карта " + cardInfo.getCARD() + " активирована.");
								}
							}
							catch (Exception e) {
								LtLogger.getLogger().error("Card hasn't been activated, error:\n" + CheckNull.getPstr(e));
							}
							finally {
								tcust.setTieto_customer_id(tietocl.getClient());
								tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
								show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
							}
						}
					});
					
					
					
					btblock_card.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
								{
									blockwnd$sstopcauses.setModel(new ListModelList(TclientService.getTstopCauses(alias)));
									blockwnd.setVisible(true);
									blockcard = (CardInfo) event.getTarget().getAttribute("card");
									blockwnd$card.setValue("Карта " + blockcard.getCARD());
								}
						
					});
					
					btunblock_card.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) 
							throws Exception
						{
							OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
							OperationResponseInfo orInfo = null;
							RowType_RemoveCardFromStop_Request parameters = new RowType_RemoveCardFromStop_Request();
							
							try
							{
								connectionInfo.setBANK_C(EMPC_BANK_C);
								connectionInfo.setGROUPC(EMPC_GROUPC);
								
								parameters.setBANK_C(EMPC_BANK_C);
								parameters.setGROUPC(EMPC_GROUPC);
								parameters.setTEXT("Card UnBlocked");
								
								blockcard = (CardInfo) event.getTarget().getAttribute("card");
								
								parameters.setCARD(blockcard.getCARD());
								parameters.setSTOP_CAUSE("0");
								
								orInfo = issuingPortProxy.removeCardFromStop(connectionInfo, parameters);
								if (orInfo.getResponse_code().intValue() != 0)
								{
									alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
								}
							}
							catch (Exception e)
							{
								LtLogger.getLogger().error(CheckNull.getPstr(e));
								alert(e.getMessage());
								e.printStackTrace();
							}
							finally
							{
								tcust.setTieto_customer_id(tietocl.getClient());
								tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
								show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
							}
						}
						
					});
					
					bt_rest_confirmation.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
								{
									cfrd = new ConfirmationRepData();
									cfrd.cardInfo = (CardInfo) event.getTarget().getAttribute("acc");
									bt_rest_confirmation_wnd.setVisible(true);
								}
						
					});
					
					btrefresh_card_app.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event)
										throws Exception
								{
									if (tietocl == null)
									{
										alert("Клиент ТИЕТО не выбран");
										return;
									}
									
									HashMap<String, Object> params = new HashMap<String, Object>();
									params.put("CARD_NUM", ((CardInfo) event.getTarget().getAttribute("acc")).getCARD());
									params.put("CARD_PROD_NAME", "Карты ТИЕТО");
									
									fill_cl_report(tietocl, "TI_APPL_VISA_CAP_REPLACE", params, (CardInfo) event.getTarget().getAttribute("acc"));
								}
					});
					
					bt_refresh_with_new.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event)
										throws Exception
								{
									if (tietocl == null)
									{
										alert("Клиент ТИЕТО не выбран");
										return;
									}
									
									Tclient ntc;
									ntc = tietocl;
									
									TietoCardSetting tcs = TclientService.getTietoCardSetting(((AccInfo) event.getTarget().getAttribute("acc")).getCard_type(), alias);
									
	
								
								
								agre_nom_upd = Integer.parseInt(String.valueOf(((AccInfo) event.getTarget().getAttribute("acc")).getAgreement_key().toBigInteger()));
								String RT = null;
								edit_agree = true;
								// cur_contract =
								// ((AccInfo)event.getTarget().getAttribute("acc")).getContract();
								cur_acc_info = (AccInfo) event.getTarget().getAttribute("acc");
								
								//contract_nmb = ((AccInfo) event.getTarget().getAttribute("acc")).getContract();
								
								// ПЕРЕВЫПУСК КАРТЫ С НОВЫМИ ДАННЫМИ, НОВАЯ ФУНКЦИЯ
								/*
								open_card(((AccInfo) event.getTarget().getAttribute("acc")).getCard_type(),
										((AccInfo) event.getTarget().getAttribute("acc")).getCard_acct(),
										true,
										((AccInfo) event.getTarget().getAttribute("acc")).getTranz_acct());
								*/
								
							}
					});
					
					bt_block_card_acc.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event)
										throws Exception
								{
									
									OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
									OperationResponseInfo orInfo = null;
									
									OperationResponseInfo ori = new OperationResponseInfo();
									OperationResponseInfoHolder orih = new OperationResponseInfoHolder(ori);
									
									RowType_DormantAccountByCard_Request dac = new RowType_DormantAccountByCard_Request();
									
									try
									{
										connectionInfo.setBANK_C(EMPC_BANK_C);
										connectionInfo.setGROUPC(EMPC_GROUPC);
										
										dac.setCARD(((CardInfo) event.getTarget().getAttribute("card")).getCARD());
										dac.setDORMANT_MODE("0");
										dac.setINSTL_INTER(BigDecimal.valueOf(0));
										dac.setBANK_C(EMPC_BANK_C);
										dac.setGROUPC(EMPC_GROUPC);
										dac.setTEXT("[" + df.format(new Date()) + "]Blk by " + branch);
										//dac.setTEXT("Blocked");
										
										OperationResponseInfo resp = issuingPortProxy.dormantAccountByCard(connectionInfo, dac);
										
										if (resp.getResponse_code().intValue() != 0)
										{
											alert(resp.getError_action()  + " \n\r " + resp.getError_description());
											return;
										}
										
										alert("Статус счета изменен на неактивный");
										
									}
									catch (Exception e)
									{
										LtLogger.getLogger().error(CheckNull.getPstr(e));
										alert(e.getMessage());
										e.printStackTrace();
									}
									finally
									{
										tcust.setTieto_customer_id(tietocl.getClient());
										tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
										
										show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
									}
								}
						
					});
					
					bt_unblock_card_acc.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event)
										throws Exception
								{
									
									OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
									OperationResponseInfo orInfo = null;
									OperationResponseInfo ori = new OperationResponseInfo();
									OperationResponseInfoHolder orih = new OperationResponseInfoHolder(ori);
									RowType_ActivateAccountByCard_Request dac = new RowType_ActivateAccountByCard_Request();
									
									try
									{
										connectionInfo.setBANK_C(EMPC_BANK_C);
										connectionInfo.setGROUPC(EMPC_GROUPC);
										
										dac.setCARD(((CardInfo) event.getTarget().getAttribute("card")).getCARD());
										dac.setTEXT("[" + df.format(new Date()) + "]UnBlk by " + branch);
										dac.setBANK_C(EMPC_BANK_C);
										dac.setGROUPC(EMPC_GROUPC);
										OperationResponseInfo resp = issuingPortProxy.activateAccountByCard(connectionInfo, dac);
										
										if (resp.getResponse_code().intValue() != 0)
										{
											alert(resp.getError_action()  + " \n\r " + resp.getError_description());
											return;
										}
										
										alert("Счет активирован");
										
									}
									catch (Exception e)
									{
										LtLogger.getLogger().error(CheckNull.getPstr(e));
										alert(e.getMessage());
										e.printStackTrace();
									}
									finally
									{
										tcust.setTieto_customer_id(tietocl.getClient());
										tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
										show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
									}
								}
						
					});
					
					bt_close_card_acc.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
								{
									
									OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
									OperationResponseInfo orInfo = null;
									OperationResponseInfo ori = new OperationResponseInfo();
									OperationResponseInfoHolder orih = new OperationResponseInfoHolder(ori);
									RowType_CloseAccount_Request dac = new RowType_CloseAccount_Request();
									
									try
									{
										connectionInfo.setBANK_C(EMPC_BANK_C);
										connectionInfo.setGROUPC(EMPC_GROUPC);
										
										dac.setCARD_ACCT(((CardInfo) event.getTarget().getAttribute("card")).getCARD_ACCT());
										dac.setCCY(((CardInfo) event.getTarget().getAttribute("card")).getBank_account_Ccy());
										dac.setCOMMENT("[" + df.format(new Date()) + "]Closed by " + branch);
										
										OperationResponseInfo resp = issuingPortProxy.closeAccount(connectionInfo, dac);
										
										if (resp.getResponse_code().intValue() != 0)
										{
											alert(resp.getError_action()  + " \n\r " + resp.getError_description());
											return;
										}
										alert("Счет закрыт");
										
									}
									catch (Exception e)
									{
										LtLogger.getLogger().error(CheckNull.getPstr(e));
										alert(e.getMessage());
										e.printStackTrace();
									}
									finally
									{
										tcust.setTieto_customer_id(tietocl.getClient());
										tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
										show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
									}
								}
						
					});
					
					btrefresh_card.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
								{
									
									OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
									OperationResponseInfo orInfo = null;
									// RowType_RemoveCardFromStop_Request parameters
									// = new RowType_RemoveCardFromStop_Request();
									
									RowType_EditAgreement_Request rtea = new RowType_EditAgreement_Request();
									// rtea.setBRANCH("001");
									
									// cannot find agreement in issuingPortProxy
									
									agre_nom = ((AccInfo) event.getTarget().getAttribute("acc")).getAgreement_key();
									
									Agreement agr = TclientService.getCardAgreement(agre_nom, alias, issuingPortProxy);
									
									rtea.setAGRE_NOM(BigDecimal.valueOf(agr.getAGRE_NOM()));
									rtea.setB_BR_ID(BigDecimal.valueOf(agr.getB_BR_ID()));
									rtea.setBANK_CODE(agr.getBANK_CODE());
									rtea.setBINCOD(agr.getBINCOD());
								
									rtea.setBRANCH(branch);
									
									rtea.setCITY(agr.getCITY());
									rtea.setCLIENT(agr.getCLIENT());
									rtea.setCOMENT(agr.getCOMENT());
									rtea.setCONTRACT(agr.getCONTRACT());
									rtea.setCOUNTRY(agr.getCOUNTRY());
									Calendar cal1 = Calendar.getInstance();
									cal1.setTime(agr.getCTIME());
									rtea.setCTIME(cal1);
									rtea.setDISTRIB_MODE(agr.getDISTRIB_MODE());
									rtea.setE_MAILS(agr.getE_MAILS());
									Calendar cal2 = Calendar.getInstance();
									cal2.setTime(agr.getENROLLED());
									rtea.setENROLLED(cal2);
									rtea.setIN_FILE_NUM(BigDecimal.valueOf(agr.getIN_FILE_NUM()));
									rtea.setISURANCE_TYPE(agr.getISURANCE_TYPE());
									rtea.setOFFICE(agr.getOFFICE());
									rtea.setOFFICE_ID(BigDecimal.valueOf(agr.getOFFICE_ID()));
									rtea.setPOST_IND(agr.getPOST_IND());
									rtea.setPRODUCT(agr.getPRODUCT());
									rtea.setREP_LANG(agr.getREP_LANG());
									rtea.setRISK_LEVEL(agr.getRISK_LEVEL());
									rtea.setSTATUS(agr.getSTATUS());
									rtea.setSTREET(agr.getSTREET());
									rtea.setU_COD4(agr.getU_COD4());
									rtea.setU_CODE5(agr.getU_CODE5());
									rtea.setU_CODE6(agr.getU_CODE6());
									rtea.setU_FIELD3(agr.getU_FIELD3());
									rtea.setU_FIELD4(agr.getU_FIELD4());
									rtea.setUSRID(agr.getUSRID());
									
									OperationResponseInfo ori = new OperationResponseInfo();
									try
									{
										connectionInfo.setBANK_C(EMPC_BANK_C);
										connectionInfo.setGROUPC(EMPC_GROUPC);
										
										issuingPortProxy.editAgreement(connectionInfo, rtea);
									}
									catch (Exception e)
									{
										LtLogger.getLogger().error(CheckNull.getPstr(e));
										alert(e.getMessage());
										e.printStackTrace();
									}
									finally
									{
										tcust.setTieto_customer_id(tietocl.getClient());
										tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
										show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
									}
									
									// OperationResponseInfo ori = new
									// OperationResponseInfo();
									OperationResponseInfoHolder orih = new OperationResponseInfoHolder(ori);
									RowType_ReplaceCard parameters = new RowType_ReplaceCard();
									
									try
									{
										connectionInfo.setBANK_C(EMPC_BANK_C);
										connectionInfo.setGROUPC(EMPC_GROUPC);
										
										parameters.setCARD(((AccInfo) event.getTarget().getAttribute("acc")).getCard());
										
										RowType_ReplaceCardHolder rrch = new RowType_ReplaceCardHolder(parameters);
										
										issuingPortProxy.replaceCard(connectionInfo, parameters, orih, rrch);
										
										alert("Карта перевыпущена");
										// UserService.UsrLog(new
										// UserActionsLog(uid, un, curip, 7, 1,
										// "Карта No ["+((AccInfo)event.getTarget().getAttribute("acc")).getCard()+"] перевыпущена",
										// branch));
										
									}
									catch (Exception e)
									{
										LtLogger.getLogger().error(CheckNull.getPstr(e));
										alert(e.getMessage());
										e.printStackTrace();
									}
									finally
									{
										tcust.setTieto_customer_id(tietocl.getClient());
										tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
										show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
									}
									
								}
						
					});
					
					
					// GlobuzAccount
//					if (cardInfo.getBank_account_status().equals(Constants.ACC_ACTIVE)) {
//						acc_act.appendChild(bt_block_card_acc);
//					}
//					if (cardInfo.getBank_account_status().equals(Constants.ACC_NOT_ACTIVE)) {
//						acc_act.appendChild(bt_close_card_acc);
//					}
//					if (cardInfo.getBank_account_status().equals(Constants.ACC_NOT_ACTIVE)) {
//						acc_act.appendChild(bt_unblock_card_acc);
//					}
					
					String state = "";
					
					if (cardInfo.getBank_account_status().equals(Constants.ACC_ACTIVE)) { 
						state = "Активен";
					}
					if (cardInfo.getBank_account_status().equals(Constants.ACC_NOT_ACTIVE)) {
						state = "Не активен";
					}
					if (cardInfo.getBank_account_status().equals(Constants.ACC_CLOSED)) {
						state = "Закрыт";
					}
					
					
					// Card
					if(cardInfo.getSTATUS().equals("1") && cardInfo.getSTATUS2().equals("0")) {
						bl_unbl.appendChild(activateCard);
					}
					else if (cardInfo.getSTOP_CAUSE().equals("0")) {
						
						bl_unbl.appendChild(btblock_card);
					}
					else {
						if (cardInfo.getBank_account_status().equals(Constants.ACC_ACTIVE)) 
							bl_unbl.appendChild(btunblock_card);
						//bl_unbl.appendChild(btrefresh_card);
						//bl_unbl.appendChild(btrefresh_card_app);
						//bl_unbl.appendChild(bt_refresh_with_new);
					}
					
					row.appendChild(new Listcell(cardInfo.getCARD()));
					
					if (cardInfo.getEXPIRY() != null)
					{
						Date datet = new Date();
						datet = datef.parse(cardInfo.getEXPIRY().substring(0, 10));
						row.appendChild(new Listcell(df.format(datet) + ""));
					}
					else row.appendChild(new Listcell("Не указана"));
					
					row.appendChild(new Listcell(_tstopCauses.get(cardInfo.getSTOP_CAUSE())));
					row.appendChild(bl_unbl);
					
					Listcell lc = new Listcell();
					lc.appendChild(new Label(cardInfo.getBank_account()));
					
					//lc.appendChild(bt_rest_confirmation);
					row.appendChild(lc);
					row.appendChild(new Listcell(cardInfo.getCARD_ACCT()));
					row.appendChild(new Listcell(state));
					row.appendChild(acc_act);
				}
				
				
			//}
		});
		
		
		
		
		
		
		addwnd$accGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				final GlobuzAccount pAccInfo = (GlobuzAccount) data;
				
				List<actions_for_acc> act = GlobuzAccountService.getactions_for_acc(pAccInfo.getState(), alias);
				
				Listcell h_edit_cell = new Listcell();
				
				
				Long accStateId = CustomerService.getAccState(alias, pAccInfo.getId(), branch);
				if(access[0].equals("approve") && accStateId == 2l) {
					
					for (int i = 0; i < act.size(); i++)
					{
						// vot ono
						bt_acc_act = new Toolbarbutton();
						bt_acc_act.setLabel(act.get(i).getName());
						bt_acc_act.setAttribute("deal_group_id", act.get(i).getDeal_group());
						bt_acc_act.setAttribute("deal_id", act.get(i).getDeal_id());
						bt_acc_act.setAttribute("action_id", act.get(i).getAction_id());
						bt_acc_act.setAttribute("acc", pAccInfo);
						bt_acc_act.setImage("/images/down3.png");
						bt_acc_act.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(Event event)
									throws Exception
							{
								GlobuzAccountService.doAction_acc(un, pwd,
										(Integer) event.getTarget().getAttribute("deal_group_id"),
										(Integer) event.getTarget().getAttribute("deal_id"),
										(GlobuzAccount) event.getTarget().getAttribute("acc"),
										(Integer) event.getTarget().getAttribute("action_id"),
										alias, true);
								
								GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
								acfilter.setClient(cur_branch_customer.getId_client());
								acfilter.setAcc_bal(Constants.ACC_22618);
								acfilter.setBranch(branch);
								acfilter.setCurrency(Constants.CURRENCY_USD);
								if(smart) {
									acfilter.setId_order("444");
								}
								
								com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
								
								addwnd$accGrid.setModel((ListModel) acc_model);
							}
							
						});
						
						// if (pAccInfo.getId_order().compareTo("901") == 0)
						if ((act.get(i).getAction_id() == 1) ||
								(act.get(i).getAction_id() == 2) ||
								(act.get(i).getAction_id() == 20)) {
							h_edit_cell.appendChild(bt_acc_act);
						}
					}
				}				
				else if(access[0].equals("confirm") && accStateId == 1l) {
					Toolbarbutton buttonConfirm = new Toolbarbutton();
					buttonConfirm.setLabel("Подтвердить");
//					buttonConfirm.setAttribute("tietoAccount", value);
//					buttonConfirm.setAttribute("branch", value);
					buttonConfirm.setImage("/images/down3.png");
					buttonConfirm.addEventListener(Events.ON_CLICK, new EventListener() {
						
						@Override
						public void onEvent(Event event) throws Exception {
							
							CustomerService.updateStateId(alias, pAccInfo.getId(), branch);
							
							GlobuzAccountFilter globuzAccountFilter = new GlobuzAccountFilter();
							globuzAccountFilter.setClient(cur_branch_customer.getId_client());
							globuzAccountFilter.setAcc_bal(Constants.ACC_22618);
							globuzAccountFilter.setBranch(branch);
							globuzAccountFilter.setCurrency(Constants.CURRENCY_USD);
							if(smart) {
								globuzAccountFilter.setId_order("444");
							}
							
							com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, globuzAccountFilter, alias);
							
							addwnd$accGrid.setModel((ListModel) acc_model);							
						}
					});
					
					h_edit_cell.appendChild(buttonConfirm);
				}
				
				Listcell h_add_acc_cell = new Listcell();
				if (pAccInfo.getState() == 2)
				{
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel("Открыть карту");
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setImage("/images/credit_card1.png");
					bt_acc_act.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							if (CheckNull.isEmpty(addwnd$sproduct.getValue()))
							{
								alert("Продукт не выбран");
								return;
							}
							
							String new_card_acc = ((GlobuzAccount) event.getTarget().getAttribute("acc")).getId();
							String RT = null;
							
							

							openCard(addwnd$sproduct.getValue(), new_card_acc, null);
							if (RT == null) return;

							

							addwnd.setVisible(false);
						}
						
					});
				}
				
				row.setValue(pAccInfo);
				row.appendChild(new Listcell(pAccInfo.getBranch()));
				row.appendChild(new Listcell(pAccInfo.getClient()));
				row.appendChild(new Listcell(pAccInfo.getId()));
				
				String full_name = "";
				CharSequence ch = " null";
				CharSequence newch = " ";
				full_name = pAccInfo.getName();
				if (full_name.contains(ch))
				{
					full_name = full_name.replace(ch, newch);
				}
				row.appendChild(new Listcell(full_name));
				
				row.appendChild(new Listcell(hashMapCurr.get(pAccInfo.getCurrency())));
				row.appendChild(new Listcell(pAccInfo.getDate_open() != null ? df.format(pAccInfo.getDate_open()) : "--//--"));
				row.appendChild(new Listcell(pAccInfo.getId_order()));
				row.appendChild(new Listcell(GlobuzAccountService.get_account_state_caption(pAccInfo.getState(), alias)));
				
				row.appendChild(h_edit_cell);
				row.appendChild(h_add_acc_cell);
				
				
//				Toolbarbutton buttonReport = new Toolbarbutton();
//				buttonReport.setLabel("Печать заявления");
//				buttonReport.setAttribute("tietoAccount", pAccInfo);
//				buttonReport.setImage("/images/save.png");
//				buttonReport.addEventListener(Events.ON_CLICK, new EventListener() {
//					
//					@Override
//					public void onEvent(Event event) throws Exception {
//						
//						
//						String acc = ((GlobuzAccount) event.getTarget().getAttribute("tietoAccount")).getId();
//						
//						printReport(acc);
//						
//						
//						GlobuzAccountFilter accountFilter = new GlobuzAccountFilter();
//						accountFilter.setClient(cur_branch_customer.getId_client());
//						accountFilter.setAcc_bal(Constants.ACC_22618);
//						accountFilter.setBranch(branch);
//						accountFilter.setCurrency(Constants.CURRENCY_USD);
//						if(smart) {
//							accountFilter.setId_order("444");
//						}
//						
//						com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, accountFilter, alias);
//						
//						addwnd$accGrid.setModel((ListModel) acc_model);							
//					}
//				});
//				
//				Listcell reportCell = new Listcell();
//				reportCell.appendChild(buttonReport);
//				
//				row.appendChild(reportCell);
			}
		});
		
		// Bank clients
		branch_customers.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				boolean fl_show = true;
				Listcell acc_edit_cell = new Listcell();
				Customer pCustomer = (Customer) data;
				link lnk = CustomerService.get_link_branch(pCustomer.getId_client(), branch, alias);
				row.setAttribute("row", row);
				row.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event) throws Exception
					{
						is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;
						
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
						bank_customers.clearSelection();
					}
					
				});
				
				if (lnk != null)
				{
					if (used_ids.containsKey(lnk.id))
					{
						if (used_ids.containsKey(lnk.id))
						{
							fl_show = false;
							row.setVisible(false);
						}
						else
						{
							used_ids.put(lnk.id, 1);
							fl_show = true;
						}
					}
				}
				
				if (fl_show)
				{
					
					is_br = true;
					
					Listcell br_cell = new Listcell();
					btbreak = new Toolbarbutton();
					btbreak.setLabel("");
					btbreak.setAttribute("lnk_id", lnk);
					btbreak.setImage("/images/locked.png");
					btbreak.setTooltiptext("Разделить связку");
					btbreak.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							
							Messagebox.show("Вы действительно хотите разделить связку Банка с ТИЕТО ? ",
								"Предупреждение безопасности",
								Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
								new EventListener()
								{
									@Override
									public void onEvent(Event event) throws Exception
									{
										// TODO Auto-generated method stub
										int answer = (Integer) event.getData();
										if (answer == Messagebox.OK)
										{
											Res res = CustomerService.removeTс(un, pwd, lnk_id, alias);
											if (res.getCode() != 0)
											{
												alert(res.getName());
												return;
											}
											refreshModel(_starttPageNumber);
										}
										else return;
									}
								}
							);
						}
						
					});
//					br_cell.appendChild(btbreak);
//					row.appendChild(br_cell);
					
					Listcell edit_cell = new Listcell();
					btedit = new Toolbarbutton();
					btedit.setLabel("Редакт. везде");
					btedit.setStyle("background-color: #CCFF99; text-align: center;");
					//btedit.setImage("/images/config.png");
					btedit.setAttribute("br_cl", pCustomer);
					btedit.setAttribute("is_br", true);
					btedit.setAttribute("ho_cl", null);
					btedit.setAttribute("ti_cl", null);
					btedit.setAttribute("is_ti", false);
					btedit.setAttribute("is_ho", false);
					btedit.setAttribute("edit_ho", false);
					btedit.setAttribute("edit_br", false);
					btedit.setAttribute("edit_ti", false);
					btedit.setTooltiptext("Редактировать везде");
					btedit.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
							{
								is_ti = (Boolean) event.getTarget().getAttribute("is_ti");
								is_ho = (Boolean) event.getTarget().getAttribute("is_ho");
								is_br = (Boolean) event.getTarget().getAttribute("is_br");
								
								cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
								tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
								
								edit_ti = (Boolean) event.getTarget().getAttribute("is_ti");
								edit_br = (Boolean) event.getTarget().getAttribute("is_br");
								
								CheckNull.clearForm(add_everywhere$addgrdl);
								CheckNull.clearForm(add_everywhere$addgrdr);
								
								if (cur_branch_customer != null)
									fill_form(cur_branch_customer, tietocl);
								else if (tietocl != null) fill_form(tietocl);
							
								add_everywhere.setTitle("Редактирование клиента");
								fl_edit = true;
								add_everywhere.setVisible(true);
							}
					});
					if(access[0].equals("open") && !smart) {
						edit_cell.appendChild(btedit);
						row.appendChild(edit_cell);
					}
					else {
						row.appendChild(new Listcell());
					}
					
					Listcell b_edit_cell = new Listcell();
					row.setValue(pCustomer);
					
					Listcell h_edit_cell = new Listcell();
					bth = new Toolbarbutton();
					bth.setLabel("");
					bth.setImage("/images/edit.png");
					bth.setAttribute("br_cl", pCustomer);
					bth.setTooltiptext("Редактировать ГО");
					row.setAttribute("br_cl", pCustomer);
					bth.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
							is_ho = (Integer) event.getTarget().getAttribute("is_ho") == 1 ? true : false;
							is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;
							
							cur_HO_customer = (Customer) event.getTarget().getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
							add_ti = false;
							add_ho = true;
							add_br = false;
							set_default();
							session.setAttribute("branch_filter", ConnectionPool.getValue("HO", alias));
							session.setAttribute("alias_filter", CustomerService.get_alias_ho(alias));
							add_to_ho = 1;
							fl_edit = false;
							addCustomer.setVisible(true);
						}
						
					});
					h_edit_cell.appendChild(bth);
					
					
					
					
					
					Listcell t_edit_cell = new Listcell();
					
					btt = new Toolbarbutton();
					btt.setLabel("Открыть в ТИЕТО");
					btt.setStyle("background-color: #CCFF99; text-align: center;");
					//btt.setImage("/images/link16.png");
					btt.setAttribute("br_cl", pCustomer);
					btt.setTooltiptext("Открыть в ТИЕТО");
					btt.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event)
							throws Exception
						{
							try {
                                is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
                                is_ho = (Integer) event.getTarget().getAttribute("is_ho") == 1 ? true : false;
                                is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;

                                cur_HO_customer = (Customer) event.getTarget().getAttribute("ho_cl");
                                cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
                                tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
                                add_ti = true;
                                add_ho = false;
                                add_br = false;
                                set_default();
                                alert("Мы тут 9");
                                fl_edit = false;
                                alert("Мы тут 10");
                                alert(add_everywhere.getTitle());
                                add_everywhere.setTitle("Открытие клиента");
                                add_everywhere.setVisible(true);
                            } catch(Exception e){
							    alert(e.getMessage());
                            }
						}
						
					});
					
					
					// фича
					
					Toolbarbutton retrieveButton = new Toolbarbutton();
					retrieveButton.setLabel("Создать связку");
					retrieveButton.setStyle("background-color: #CCFF99; text-align: center;");
					retrieveButton.setTooltiptext("Создать связку клиента в Банке и в Тието");
					retrieveButton.setAttribute("br_cl", pCustomer);
					retrieveButton.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							
							cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						
							createLinkWnd.setVisible(true);					
						}
					});
					
					
					
					
					if(access[0].equals("open") && !smart) {
						t_edit_cell.appendChild(btt);
					}
					else if(access[0].equals("open") && smart && lnk == null) {
						t_edit_cell.appendChild(retrieveButton);
					}
					
					
					
					
					
					btacc = new Toolbarbutton();
					btacc.setLabel("Открыть карту");
					btacc.setStyle("background-color: #CCFF99; text-align: center;");
					//btacc.setImage("/images/edit.png");
					btacc.setAttribute("br_cl", pCustomer);
					btacc.setTooltiptext("Открыть карту на счете 22618");
					btacc.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
							// is_ho = (Integer)
							// event.getTarget().getAttribute("is_ho") == 1 ?
							// true : false;
							is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;
							
							// cur_HO_customer = (TietoCustomer)
							// event.getTarget().getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
							add_ti = false;
							add_ho = false;
							add_br = true;
							
							GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
							acfilter.setClient(cur_branch_customer.getId_client());
							acfilter.setAcc_bal(Constants.ACC_22618);
							acfilter.setBranch(branch);
							acfilter.setCurrency(Constants.CURRENCY_USD);
							if(smart) {
								acfilter.setId_order("444");
							}
							com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
							
							accounts$accGrid.setModel((ListModel) acc_model);
							
							accounts$btn_add.setDisabled(false);
							accounts.setVisible(true);
							
							accounts$btn_add.setVisible(false);
							accounts$btn_cancel.setVisible(false);
						}
						
					});
					if(access[0].equals("open")) {
						acc_edit_cell.appendChild(btacc);
					}
					
					if (lnk != null)
					{
						btbreak.setAttribute("lnk_id", lnk.id);
						if (lnk.Tieto_id != null)
						{
							is_ti = true;
							tietocl = TclientService.getTclient_by_id(lnk.Tieto_id, issuingPortProxy, alias);
							row.appendChild(new Listcell(lnk.Tieto_id));
							if (tietocl != null)
							{
								btedit.setAttribute("is_ti", true);
								btedit.setAttribute("ti_cl", tietocl);
								
								row.appendChild(new Listcell(
										(tietocl.getF_names() != null ? tietocl.getF_names() : "") + " " +
											(tietocl.getSurname() != null ? tietocl.getSurname() : "")));
								row.appendChild(new Listcell(tietocl.getB_date() != null ? df.format(tietocl.getB_date()) : "--//--"));
								
								bth.setAttribute("ti_cl", tietocl);
								btt.setAttribute("ti_cl", tietocl);
								btacc.setAttribute("ti_cl", tietocl);
								row.setAttribute("ti_cl", tietocl);
							}
							else
							{
								is_ti = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						}
						else
						{
							is_ti = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
						row.appendChild(t_edit_cell);
						
						if ((lnk.Head_id != null))
						{
							is_ho = true;
							String halias = CustomerService.get_alias_ho(alias);
							Customer branch_cst = com.is.tieto_capital.customer.CustomerService.getCustomerById(lnk.Head_id, ConnectionPool.getValue("HO", alias), halias);
							row.appendChild(new Listcell(lnk.Head_id));
							if (branch_cst != null)
							{
								btedit.setAttribute("is_hi", true);
								
								Listcell lc1 = new Listcell(branch_cst.getName() != null ? branch_cst.getName() : "--//--");
								Listcell lc2 = new Listcell((branch_cst.getP_birthday() != null) ? df.format(branch_cst.getP_birthday()) : "--//--");
								// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
								
								if (branch_cst.getState() != 2)
								{
									lc1.setStyle("background-color:#dadada;");
									lc2.setStyle("background-color:#dadada;");
									lc1.setContext(editPopup);
									lc2.setContext(editPopup);
									lc1.setAttribute("branch", branch_cst.getBranch());
									lc1.setAttribute("client_id", branch_cst.getId_client());
									lc1.setAttribute("alias", halias);
									lc2.setAttribute("branch", branch_cst.getBranch());
									lc2.setAttribute("client_id", branch_cst.getId_client());
									lc2.setAttribute("alias", halias);
								}
								
								btedit.setAttribute("ho_cl", branch_cst);
								row.appendChild(lc1);
								row.appendChild(lc2);
								bth.setAttribute("ho_cl", branch_cst);
								btt.setAttribute("ho_cl", branch_cst);
								btacc.setAttribute("ho_cl", branch_cst);
								row.setAttribute("ho_cl", branch_cst);
							}
							else
							{
								is_ho = false;
								row.appendChild(new Listcell("--//--"));
								row.appendChild(new Listcell("--//--"));
							}
						}
						else
						{
							is_ho = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
					}
					else
					{
						btbreak.setVisible(false);
						is_ho = false;
						is_ti = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						
						row.appendChild(t_edit_cell);
						
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
					}
					
					row.appendChild(h_edit_cell);
					// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
					Listcell lc1 = new Listcell(pCustomer.getId_client());
					Listcell lc2 = new Listcell(pCustomer.getName() != null ? pCustomer.getName() : "--//--");
					Listcell lc3 = new Listcell(pCustomer.getP_birthday() != null ? df.format(pCustomer.getP_birthday()) : "--//--");
					
					if (pCustomer.getState() != 2)
					{
						lc2.setStyle("background-color:#dadada;");
						lc3.setStyle("background-color:#dadada;");
						lc1.setContext(editPopup);
						lc2.setContext(editPopup);
						lc1.setAttribute("branch", pCustomer.getBranch());
						lc1.setAttribute("client_id", pCustomer.getId_client());
						lc1.setAttribute("alias", alias);
						lc2.setAttribute("branch", pCustomer.getBranch());
						lc2.setAttribute("client_id", pCustomer.getId_client());
						lc2.setAttribute("alias", alias);
					}
					
					row.appendChild(lc1);
					row.appendChild(lc2);
					row.appendChild(lc3);
					row.appendChild(new Listcell());
					
					// row.appendChild(new
					// Listcell("["+pCustomer.getBranch()+"]"));
					bth.setAttribute("is_ti", is_ti ? 1 : 0);
					bth.setAttribute("is_ho", is_ho ? 1 : 0);
					bth.setAttribute("is_br", is_br ? 1 : 0);
					btt.setAttribute("is_ti", is_ti ? 1 : 0);
					btt.setAttribute("is_ho", is_ho ? 1 : 0);
					btt.setAttribute("is_br", is_br ? 1 : 0);
					btacc.setAttribute("is_ti", is_ti ? 1 : 0);
					btacc.setAttribute("is_ho", is_ho ? 1 : 0);
					btacc.setAttribute("is_br", is_br ? 1 : 0);
					row.setAttribute("is_ti", is_ti ? 1 : 0);
					row.setAttribute("is_ho", is_ho ? 1 : 0);
					row.setAttribute("is_br", is_br ? 1 : 0);
					
					// row.appendChild(new Listcell((is_ti?"1":"0") +
					// (is_ho?"1":"0") + (is_br?"1":"0")));
					if (lnk != null)
					{
						if (lnk.Cur_acc != null)
						{
							row.appendChild(new Listcell(lnk.Cur_acc));
							is_acc = true;
							if (is_ti && is_ho && is_br)
								row.setStyle("background-color:#D2F7CA; font-weight:bold;");
							}
							else
							{
								row.appendChild(new Listcell());
							}
							
							row.appendChild(acc_edit_cell);
							/*
							 * if (is_ti && is_br && is_ho) {
							 * row.appendChild(acc_edit_cell); } else {
							 * row.appendChild(new Listcell()); }
							 */
						}
						else
						{
							row.appendChild(new Listcell());
							row.appendChild(new Listcell());
						}
						if (is_ti) btt.setVisible(false);
						if (is_ho) bth.setVisible(false);
						// if(is_br)btb.setVisible(false);
						
						// btacc.setVisible(true);
					}
					
				}
		});
		
		// Tieto
		
		//ЗДЕСЬ ФОРМИРУЕТСЯ СПИСОК КЛИЕНТОВ ТИЕТО
		bank_customers.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception
			{
				is_acc = false;
				Tclient pTclient = (Tclient) data;
				link lnk = CustomerService.get_link_tieto(pTclient.getClient(), branch, alias);
				if (lnk != null) used_ids.put(lnk.id, 1);
				
				row.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event) throws Exception
					{
						// head_customers.clearSelection();
						branch_customers.clearSelection();
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						cur_HO_customer = (Customer) event.getTarget().getAttribute("ho_cl");
					}
					
				});
				
				Listcell br_cell = new Listcell();
				
				btbreak = new Toolbarbutton();
				btbreak.setLabel("");
				btbreak.setImage("/images/locked.png");
				btbreak.setTooltiptext("Разделить связку");
				btbreak.setTooltip("Удалить связку");
				btbreak.setAttribute("ti_cl", pTclient);
				btbreak.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event) throws Exception
					{
						final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
						Messagebox.show("Вы действительно хотите разделить связку Банка с ТИЕТО ? ",
							"Предупреждение безопасности",
							Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
							new EventListener()
								{
									@Override
									public void onEvent(Event event) throws Exception
									{
										// TODO Auto-generated method stub
										int answer = (Integer) event.getData();
										if (answer == Messagebox.OK)
										{
											
											Res res = CustomerService.removeTс(un, pwd, lnk_id, alias);
											
											if (res.getCode() != 0)
											{
												alert(res.getName());
												return;
											}
											refreshModel(_starttPageNumber);
										}
										else return;
									}
								}
							);
					}
					
				});
//				br_cell.appendChild(btbreak);
//				row.appendChild(br_cell);
				
				Listcell edit_cell = new Listcell();
				
				btedit = new Toolbarbutton();
				btedit.setLabel("Редакт. везде");
				btedit.setStyle("background-color: #CCFF99; text-align: center;");
				//btedit.setImage("/images/config.png");
				btedit.setAttribute("ti_cl", pTclient);
				btedit.setAttribute("is_ti", true);
				btedit.setAttribute("br_cl", null);
				btedit.setAttribute("ho_cl", null);
				btedit.setAttribute("is_br", false);
				btedit.setAttribute("is_ho", false);
				btedit.setAttribute("edit_ho", false);
				btedit.setAttribute("edit_br", false);
				btedit.setAttribute("edit_ti", false);
				btedit.setTooltiptext("Редактировать везде");				
				btedit.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event)
							throws Exception
					{
						is_ti = (Boolean) event.getTarget().getAttribute("is_ti");
						is_ho = (Boolean) event.getTarget().getAttribute("is_ho");
						is_br = (Boolean) event.getTarget().getAttribute("is_br");
						
						cur_HO_customer = (Customer) event.getTarget().getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
						
						edit_ti = (Boolean) event.getTarget().getAttribute("is_ti");
						edit_ho = (Boolean) event.getTarget().getAttribute("is_ho");
						edit_br = (Boolean) event.getTarget().getAttribute("is_br");
						
						CheckNull.clearForm(add_everywhere$addgrdl);
						CheckNull.clearForm(add_everywhere$addgrdr);
						if (cur_branch_customer != null) fill_form(cur_branch_customer, tietocl);
						else if (cur_HO_customer != null) fill_form(cur_HO_customer, tietocl);
						else if (tietocl != null) fill_form(tietocl);
						add_everywhere.setTitle("Редактирование клиента");
						fl_edit = true;
						add_everywhere.setVisible(true);
					}
					
				});
				if(access[0].equals("open") && !smart) {
					edit_cell.appendChild(btedit);
					row.appendChild(edit_cell);
				}
				else {
					row.appendChild(new Listcell());
				}
				
				Listcell h_edit_cell = new Listcell();
				bth = new Toolbarbutton();
				bth.setLabel("");
				bth.setImage("/images/edit.png");
				bth.setAttribute("ti_cl", pTclient);
				bth.setTooltiptext("Редактировать ГО");
				bth.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event)
							throws Exception
					{
						is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
						is_ho = (Integer) event.getTarget().getAttribute("is_ho") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;
						
						cur_HO_customer = (Customer) event.getTarget().getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
						add_ti = false;
						add_ho = true;
						add_br = false;
						set_default();
						fl_edit = false;
						session.setAttribute("branch_filter", ConnectionPool.getValue("HO", alias));
						session.setAttribute("alias_filter", CustomerService.get_alias_ho(alias));
						addCustomer.setVisible(true);
						// addCustomer.
					}
					
				});
				h_edit_cell.appendChild(bth);
				
				is_ti = true;
				
				Listcell b_edit_cell = new Listcell();
				btb = new Toolbarbutton();
				btb.setLabel("");
				btb.setImage("/images/link16.png");
				btb.setAttribute("ti_cl", pTclient);
				btb.setTooltiptext("Редактировать филиал");
				btb.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event) throws Exception
					{
						is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
						is_ho = (Integer) event.getTarget().getAttribute("is_ho") == 1 ? true : false;
						is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;
						
						cur_HO_customer = (Customer) event.getTarget().getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
						add_ti = false;
						add_ho = false;
						add_br = true;
						set_default();
						fl_edit = false;
						session.setAttribute("branch_filter", branch);
						session.setAttribute("alias_filter", alias);
						addCustomer.setVisible(true);
					}
					
				});
				b_edit_cell.appendChild(btb);
				
				Listcell acc_edit_cell = new Listcell();
				btacc = new Toolbarbutton();
				btacc.setLabel("Открыть карту");
				btacc.setStyle("background-color: #CCFF99; text-align: center;");
				//btacc.setImage("/images/edit.png");
				btacc.setAttribute("ti_cl", pTclient);
				btacc.setTooltiptext("Открыть карту на счете 22618");
				btacc.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event)
							throws Exception
					{
						is_ti = (Integer) event.getTarget().getAttribute("is_ti") == 1 ? true : false;
						// is_ho = (Integer)
						// event.getTarget().getAttribute("is_ho") == 1 ? true :
						// false;
						is_br = (Integer) event.getTarget().getAttribute("is_br") == 1 ? true : false;
						
						// cur_HO_customer = (TietoCustomer)
						// event.getTarget().getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
						add_ti = false;
						add_ho = false;
						add_br = true;
						
						GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
						acfilter.setClient(cur_branch_customer.getId_client());
						acfilter.setAcc_bal(Constants.ACC_22618);
						acfilter.setBranch(branch);
						acfilter.setCurrency(Constants.CURRENCY_USD);
						if(smart) {
							acfilter.setId_order("444");
						}
						
						com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
						accounts$accGrid.setModel((ListModel) acc_model);
						
						accounts$btn_add.setDisabled(false);
						accounts.setVisible(true);
						
						accounts$btn_add.setVisible(false);
						accounts$btn_cancel.setVisible(false);
						
						// refreshModel(_starttPageNumber);
					}
					
				});
				if(access[0].equals("open")) {
					acc_edit_cell.appendChild(btacc);
				}
				
				row.setValue(pTclient);
				
				row.appendChild(new Listcell(pTclient.getClient() != null ? pTclient.getClient() : "--//--"));
				row.appendChild(new Listcell((pTclient.getF_names() != null ? pTclient.getF_names() : "") + " " + (pTclient.getSurname() != null ? pTclient.getSurname() : "")));
				row.appendChild(new Listcell(pTclient.getB_date() != null ? df.format(pTclient.getB_date()) : "--//--"));
				row.appendChild(new Listcell());
				
				if (lnk != null)
				{
					btbreak.setAttribute("lnk_id", lnk.id);
					
					if (lnk.Head_id != null)
					{
						
						is_ho = true;
						String halias = CustomerService.get_alias_ho(alias);
						Customer head_cst = com.is.tieto_capital.customer.CustomerService.getCustomerById(lnk.Head_id, ConnectionPool.getValue("HO", alias), halias);
						System.out.print("||head:" + head_cst.getName());
						
						btedit.setAttribute("is_ho", true);
						row.appendChild(new Listcell(lnk.Head_id));
						if (head_cst != null)
						{
							btedit.setAttribute("ho_cl", head_cst);
							
							Listcell lc1 = new Listcell(head_cst.getName() != null ? head_cst.getName() : "--//--");
							Listcell lc2 = new Listcell(head_cst.getP_birthday() != null ? df.format(head_cst.getP_birthday()) : "--//--");
							// HERE LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
							
							if (head_cst.getState() != 2)
							{
								lc1.setStyle("background-color:#dadada;");
								lc2.setStyle("background-color:#dadada;");
								lc1.setContext(editPopup);
								lc2.setContext(editPopup);
								lc1.setAttribute("branch", head_cst.getBranch());
								lc1.setAttribute("client_id", head_cst.getId_client());
								lc1.setAttribute("alias", halias);
								lc2.setAttribute("branch", head_cst.getBranch());
								lc2.setAttribute("client_id", head_cst.getId_client());
								lc2.setAttribute("alias", halias);
							}
							
							row.appendChild(lc1);
							// row.appendChild(new
							// Listcell(pTclient.getSurname()));
							row.appendChild(lc2);
							bth.setAttribute("ho_cl", head_cst);
							btb.setAttribute("ho_cl", head_cst);
							btacc.setAttribute("ho_cl", head_cst);
							row.setAttribute("ho_cl", head_cst);
						}
						else
						{
							// btacc.setVisible(false);
							is_ho = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
					}
					else
					{
						is_ho = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
					}
					row.appendChild(h_edit_cell);
					
					if ((lnk.Branch_id != null))// &&(lnk.Branch == branch))
					{
						Customer branch_cst = com.is.tieto_capital.customer.CustomerService.getCustomerById(lnk.Branch_id, branch, alias);
						
						row.appendChild(new Listcell(lnk.Branch_id));
						
						btedit.setAttribute("is_br", true);
						if (branch_cst != null)
						{
							btedit.setAttribute("br_cl", branch_cst);
							is_br = true;
							Listcell lc1 = new Listcell(branch_cst.getName() != null ? branch_cst.getName() : "--//--");
							Listcell lc2 = new Listcell((branch_cst.getP_birthday() != null) ? df.format(branch_cst.getP_birthday()) : "--//--");
							
							// LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
							if (branch_cst.getState() != 2)
							{
								lc1.setStyle("background-color:#dadada;");
								lc2.setStyle("background-color:#dadada;");
								lc1.setContext(editPopup);
								lc2.setContext(editPopup);
								lc1.setAttribute("branch", branch_cst.getBranch());
								lc1.setAttribute("client_id", branch_cst.getId_client());
								lc1.setAttribute("alias", alias);
								lc2.setAttribute("branch", branch_cst.getBranch());
								lc2.setAttribute("client_id", branch_cst.getId_client());
								lc2.setAttribute("alias", alias);
							}
							
							row.appendChild(lc1);
							// row.appendChild(new
							// Listcell(pTclient.getSurname()));
							row.appendChild(lc2);
							bth.setAttribute("br_cl", branch_cst);
							btb.setAttribute("br_cl", branch_cst);
							btacc.setAttribute("br_cl", branch_cst);
							row.setAttribute("br_cl", branch_cst);
						}
						else
						{
							btacc.setVisible(false);
							is_br = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
						// btb.setAttribute("cur_ho_id", lnk.Head_id);
					}
					else
					{
						btacc.setVisible(false);
						is_br = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						// row.appendChild(new Listcell(pTclient.getSurname()));
						row.appendChild(new Listcell("--//--"));
						// btb.setAttribute("cur_ho_id", "");
					}
					row.appendChild(b_edit_cell);
					
					if (lnk.Cur_acc != null)
					{
						row.appendChild(new Listcell(lnk.Cur_acc));
						is_acc = true;
						if (is_ti && is_ho && is_br)
						row.setStyle("background-color:#D2F7CA; font-weight:bold;");// e0ffe0//96c58d
					}
					else
					{
						row.appendChild(new Listcell());
					}
					
				}
				else
				{
					btbreak.setVisible(false);
					btacc.setVisible(false);
					String pre_ho = CustomerService.get_HO_by_tieto(pTclient.getClient(), alias);
					if (pre_ho != null)
					{
						is_br = false;
						is_ho = true;
						
						String halias = CustomerService.get_alias_ho(alias);
						Customer head_cst = com.is.tieto_capital.customer.CustomerService.getCustomerById(pre_ho, ConnectionPool.getValue("HO", alias), halias);
						
						row.appendChild(new Listcell(pre_ho));
						if (head_cst != null)
						{
							btedit.setAttribute("ho_cl", head_cst);
							row.appendChild(new Listcell(head_cst.getName() != null ? head_cst.getName() : "--//--"));
							row.appendChild(new Listcell(head_cst.getP_birthday() != null ? df.format(head_cst.getP_birthday()) : "--//--"));
							bth.setAttribute("ho_cl", head_cst);
							btb.setAttribute("ho_cl", head_cst);
							btacc.setAttribute("ho_cl", head_cst);
						}
						else
						{
							is_ho = false;
							row.appendChild(new Listcell("--//--"));
							row.appendChild(new Listcell("--//--"));
						}
						row.appendChild(h_edit_cell);
						
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						
						btb.setAttribute("cur_branch", branch);
						btacc.setAttribute("cur_branch", branch);
						
						btb.setAttribute("cur_tieto_id", pTclient.getClient());
						btacc.setAttribute("cur_tieto_id", pTclient.getClient());
						
						row.appendChild(b_edit_cell);
						
					}
					else
					{
						is_br = false;
						is_ho = false;
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(h_edit_cell);
						
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						row.appendChild(new Listcell("--//--"));
						btb.setAttribute("cur_branch", branch);
						btb.setAttribute("cur_tieto_id", pTclient.getClient());
						btacc.setAttribute("cur_branch", branch);
						btacc.setAttribute("cur_tieto_id", pTclient.getClient());
						row.appendChild(b_edit_cell);
						// row.appendChild(new Listcell("--//--"));
					}
					row.appendChild(new Listcell());
				}
				
				bth.setAttribute("is_ti", is_ti ? 1 : 0);
				bth.setAttribute("is_ho", is_ho ? 1 : 0);
				bth.setAttribute("is_br", is_br ? 1 : 0);
				btb.setAttribute("is_ti", is_ti ? 1 : 0);
				btb.setAttribute("is_ho", is_ho ? 1 : 0);
				btb.setAttribute("is_br", is_br ? 1 : 0);
				btacc.setAttribute("is_ti", is_ti ? 1 : 0);
				btacc.setAttribute("is_ho", is_ho ? 1 : 0);
				btacc.setAttribute("is_br", is_br ? 1 : 0);
				row.appendChild(acc_edit_cell);
				
				if (is_ho) bth.setVisible(false);
				if (is_br) btb.setVisible(false);
			}
		});
		
		onClick$tbtn_search();
	}
	
	public void onPaging$bankdataPaging(ForwardEvent event)
	{
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModelBank(_startPageNumber);
	}

	private void refreshModelBank(int activePage)
	{
		bankdataPaging.setPageSize(_pageSize);
		bfilter.setState(2);
		bfilter.setBranch(branch);
		bmodel = new com.is.tieto_capital.customer.PagingListModel(activePage, _pageSize, bfilter, alias);
		branch_customers.setModel((ListModel) bmodel);
		
		if (_needsTotalSizeUpdate)
		{
			_totalSize = bmodel.getTotalSize(bfilter, alias);
		}
		bankdataPaging.setTotalSize(_totalSize);
		if (bmodel.getSize() > 0)
		{
			sendSelEvt();
		}
	}
	
	private void refreshModelTieto(int activePage)
	{
		onClick$btn_fill_tieto();
	}
	
	public void onClick$btn_fill_tieto()
	{

		tfilter.setClient(tietoFilterCode.getValue());

		
		tmodel = new com.is.tieto_capital.tieto.PagingListModel(_startpageCapital, _pageSize, tfilter, alias, issuingPortProxy);
		bank_customers.setModel((ListModel) tmodel);
		if (tmodel.getSize() > 0)
		{
			sendtSelEvt();
		}
	}
	
	public void onClick$btn_ref_tieto()
	{
		onClick$btn_fill_tieto();
	}
	
	private void refreshModel(int activePage)
	{
		
		used_ids = new HashMap<Integer, Integer>();
		fl_filter_card_set = !CheckNull.isEmpty(card.getValue());
		
		refreshModelBank(activePage);
		
	}
	
	public Customer getCurrent()
	{
		return current;
		
	}
	
	public void setCurrent(Customer current)
	{
		this.current = current;
	}
	
	public Customer getTcustomer()
	{
		return tcustomer;
	}
	
	public void setTcustomer(Customer tcustomer)
	{
		this.tcustomer = tcustomer;
	}
	
	public Tclient getTietocl()
	{
		return tietocl;
	}
	
	public void setTietocl(Tclient tietocl)
	{
		this.tietocl = tietocl;
	}
	
	public void setAtcust(Tclient atcust)
	{
		this.atcust = atcust;
	}
	
	public void onClick$tbtn_card_oprations()
	{
		if (tietocl != null) {
			
			tcust.setTieto_customer_id(tietocl.getClient());
			tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
			
			show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
			show_cards.setVisible(true);
		}
	}
	
	public void onDoubleClick$bank_customers()
	{
		onClick$tbtn_card_oprations();
	}
	
	public void onDoubleClick$head_customers()
	{
		onClick$tbtn_card_oprations();
	}
	
	public void onDoubleClick$branch_customers()
	{
		onClick$tbtn_card_oprations();
	}
	
	private void set_cur_h_client()
	{
		refreshModel(_startPageNumber);
	}
	
	private void set_cur_t_client()
	{
		tcust.setTieto_customer_id(tietocl.getClient());
		
		refreshModel(_startPageNumber);
	}
	
	private void set_cur_b_client()
	{
		refreshModel(_startPageNumber);
	}
	
	private void sendtSelEvt()
	{
		SelectEvent evt = new SelectEvent("onSelect", bank_customers, bank_customers.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	private void sendSelEvt()
	{
		SelectEvent evt = new SelectEvent("onSelect", branch_customers, branch_customers.getSelectedItems());
		Events.sendEvent(evt);
	}
	
	public void onClick$btn_searchb()
	{
		refreshModel(_startPageNumber);
	}
	
	public void onClick$tbtn_search()
	{
		filter.setP_birthday(tfilter.getB_date());
		filter.setName(tfilter.getF_names());
		filter.setId_client(tfilter.getClient());
		
		filter.setP_passport_serial(passportSerial.getValue());
		filter.setP_passport_number(passportNumber.getValue());
		
		bfilter = new CustomerFilter(filter);
		// bfilter.setDate_close(new Date());
		
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_add_everywhere()
	{
		add_ti = true;
		add_ho = true;
		add_br = true;
		
		if(smart) {
			add_ti = false;
			add_ho = false;
		}
		
		// add_everywhere$acode_tel.setValue("");
		CheckNull.clearForm(add_everywhere$addgrdr);
		CheckNull.clearForm(add_everywhere$addgrdl);
		
		add_everywhere.setTitle("Открытие клиента [БАНК]");
		add_everywhere$acode_resident.setSelecteditem("1");
		add_everywhere$ap_code_citizenship.setSelecteditem("860");
		add_everywhere$acode_country.setSelecteditem("860");
		
		add_everywhere.setVisible(true);
	}
	
	public void onClick$tbtn_add()
	{
		atcust = getTFromBank(current, atcust);
		atcust.setBank_c(addTieto$abank_c.getValue());
		alert("Открывается в Tieto : " + atcust.getSearch_name() + " l " + addTieto$asearch_name.getValue());
		addTieto.setVisible(true);
	}
	
	public void onClick$btn_add()
	{
		tcustomer.setName(tietocl.getSearch_name());
		tcustomer.setBranch(branch);
		tcustomer.setCode_country("860");
		tcustomer.setP_code_class_credit("1");
		tcustomer.setP_passport_type("N");
		tcustomer.setCode_subject("P");
		tcustomer.setSign_registr(2);
		tcustomer.setCode_form("");
		tcustomer.setCode_type("08");
		// tcustomer.setP_passport_serial(ap_passport_serial.getValue());
		tcustomer.setP_passport_number(tietocl.getSerial_no());
		tcustomer.setP_first_name(tietocl.getF_names());
		tcustomer.setP_family(tietocl.getM_name());
		tcustomer.setP_birthday(tietocl.getB_date());
		sendSelEvt();
		tcustomer = getBFromTieto(tietocl, tcustomer);
		
		addCust.setVisible(true);
		
	}
	
	public void onClick$btn_addb()
	{
		
		tcustomer.setName(tietocl.getSearch_name());
		tcustomer.setBranch(branch);
		tcustomer.setCode_country("860");
		tcustomer.setP_code_class_credit("1");
		tcustomer.setP_passport_type("N");
		tcustomer.setCode_subject("P");
		tcustomer.setSign_registr(2);
		tcustomer.setCode_form("");
		tcustomer.setCode_type("08");
		// tcustomer.setP_passport_serial(ap_passport_serial.getValue());
		tcustomer.setP_passport_number(tietocl.getSerial_no());
		tcustomer.setP_first_name(tietocl.getF_names());
		tcustomer.setP_family(tietocl.getM_name());
		tcustomer.setP_birthday(tietocl.getB_date());
		sendSelEvt();
		addCust.setVisible(true);
		
	}
	
	public void onClick$tnbtn_add$addTieto()
	{
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		
		RowType_Customer rtc = new RowType_Customer();
		rtc.setF_NAMES(current.getP_first_name());
		rtc.setCL_TYPE("1");
		rtc.setCLIENT_B(current.getId_client());
		rtc.setSURNAME(current.getP_family());
		rtc.setM_NAME(current.getP_patronymic());
		Calendar cal = Calendar.getInstance();
		cal.setTime(current.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal);
		cal.setTime(current.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT("1");
		rtc.setSTATUS("10");
		rtc.setSEX("1");
		
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		
		RowType_CustomerCustomInfo[] cci = {};
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		
		try
		{
			connectionInfo.setBANK_C(EMPC_BANK_C);
			connectionInfo.setGROUPC(EMPC_GROUPC);
			orInfo = issuingPortProxy.newCustomer(connectionInfo, customerInfo, customListInfo);
			
			alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
			System.out.println("resp " + orInfo.getResponse_code() + "  client " + customerInfo.value.getCLIENT());
			
		}
		catch (RemoteException e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addTieto.setVisible(false);
	}
	
	public void onClick$tnbtn_cancel$addTieto()
	{
		CheckNull.clearForm(addTieto$addtgrdl);
		CheckNull.clearForm(addTieto$addtgrdr);
		addTieto.setVisible(false);
	}
	
	public void onClick$bnbtn_add$addCust()
	{
		tcustomer.setName(tcustomer.getP_first_name() + " " + tcustomer.getP_family());
		tcustomer.setP_code_class_credit("1");
		tcustomer.setP_passport_type("N");
		tcustomer.setCode_subject("P");
		tcustomer.setSign_registr(2);
		tcustomer.setCode_form("");
		tcustomer.setCode_type("08");
		tcustomer.setP_passport_serial(addCust$ap_passport_serial.getValue());
		tcustomer.setP_passport_number(addCust$ap_passport_number.getValue());
		
		Res res =
					CustomerService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), tcustomer, 1, 2, alias, true);
		if (res.getCode() != 0)
		{
			alert(res.getName());
		}
		else
		{
			filter.setP_passport_number(tcustomer.getP_passport_number());
			filter.setP_birthday(tcustomer.getP_birthday());
			refreshModel(_startPageNumber);
			addCust.setVisible(false);
			alert("Клиент добавлен");
		}
		
	}
	
	public void onClick$bnbtn_cancel$addCust()
	{
		CheckNull.clearForm(addCust$addgrdl);
		CheckNull.clearForm(addCust$addgrdr);
		// addCust$dataGrid.getItems().clear();//.setModel(null);
		// lkdsjlf
		addCust.setVisible(false);
	}
	
	public void onClick$btn_bind()
	{
		CustomerService.create(tcust, alias);
		alert("Связаны банковский " + tcust.getHead_customer_id() + " и Тието " + tcust.getTieto_customer_id());
	}
	
	public void onOK$ff_names()
	{
		onClick$tbtn_search();
	}
	
	public void onOK$fsearch_name()
	{
		onClick$tbtn_search();
	}
	
	public void onOK$fsurname()
	{
		onClick$tbtn_search();
	}
	
	public void onOK$fb_date()
	{
		onClick$tbtn_search();
	}
	
	public void onOK$fserial_no()
	{
		onClick$tbtn_search();
	}
	
	public void onOK$card()
	{
		onClick$tbtn_search();
	}
	
	/*
	 * public void onClick$btn_cancel$printwnd(){ printwnd.setVisible(false); }
	 */

	public void onClick$btn_cancel$accwnd()
	{
		accwnd.setVisible(false);
	}
	
	public void onClick$btn_addacc$accwnd()
	{
		GlobuzAccount globuzAccount = new GlobuzAccount();
		globuzAccount.setBranch(current.getBranch());
		globuzAccount.setAcc_bal("20206");
		globuzAccount.setCurrency("840");
		globuzAccount.setName(current.getName());
		globuzAccount.setId_order("001");
		globuzAccount.setSgn("P");
		globuzAccount.setBal("B");
		globuzAccount.setSign_registr(2);
		globuzAccount.setClient(current.getId_client());
		Res res = GlobuzAccountService.doAction(un, pwd, globuzAccount, 1, alias, true);
		if (res.getCode() == 0)
		{
			globuzAccount.setId(res.getName());
			res = GlobuzAccountService.doAction(un, pwd, globuzAccount, 2, alias, true);
			alert(res.getName());
		}
		else
		{
			
			alert(res.getName());
		}
		
		accwnd$scurracc.setModel((new ListModelList(com.is.utils.RefDataService.getCurrAcc(current.getId_client(), alias))));
		
		// accwnd.setVisible(false);
		// printwnd.setVisible(true);
	}
	
	public void onClick$btn_print$accwnd()
	{
		printwnd.setVisible(true);
		accwnd.setVisible(false);
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("P_BRANCH", current.getBranch());
		params.put("P_CLIENT_ID", current.getId_client());
		params.put("P_ACCOUNT", accwnd$scurracc.getValue());
		printwnd$rpframe.setContent(DPrint.getRepPdf("TI_APPLICATION.pdf", application.getRealPath("reports/TI_APPLICATION.jasper"), params, alias));
	}
	
	public void onClick$btn_prn_app()
	{
		accwnd$scurracc.setModel((new ListModelList(com.is.utils.RefDataService.getCurrAcc(current.getId_client(), alias))));
		accwnd.setVisible(true);
		
	}
	
	public void onClick$btn_prn_cap()
	{
		if (tietocl == null)
		{
			alert("Клиент не выбран либо не объединен.");
			return;
		}
		printwnd.setVisible(true);
		
		// String cl_id = CustomerService.get_HO_by_tieto(tietocl.getClient(),
		// alias);
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PHONE_PASSWORD", "PASSWORD");
		params.put("CARD_TYPE", "VISA GOLD");
		params.put("P_BRANCH", branch);
		params.put("P_CLIENT_ID", tietocl.getClient());
		
		printwnd$rpframe.setContent(DPrint.getRepPdf("TI_APPLICATION_VASA_CAP.pdf", application.getRealPath("reports/TI_APPLICATION_VASA_CAP.jasper"), params, alias));
	}
	
	public void onClick$btn_prn_prm()
	{
		printwnd.setVisible(true);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("PHONE_PASSWORD", "PASSWORD");
		params.put("CARD_TYPE", "VISA GOLD");
		params.put("P_BRANCH", current.getBranch());
		params.put("P_CLIENT_ID", current.getId_client());
		
		printwnd$rpframe.setContent(DPrint.getRepPdf("TI_DOGOVOR_VISA_PREMIUM.pdf", application.getRealPath("reports/TI_DOGOVOR_VISA_PREMIUM.jasper"), params, alias));
	}
	
	public void onSelect$ap_code_birth_region$addCust(Event evt)
	{
		addCust$ap_code_birth_region.setSelectedIndex(-1);
		addCust$ap_code_birth_region.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(addCust$ap_code_birth_region.getValue(), alias))));
	}
	
	public void onSelect$ap_code_adr_region$addCust(Event evt)
	{
		addCust$ap_code_adr_distr.setSelectedIndex(-1);
		addCust$ap_code_adr_distr.setModel((new ListModelList(com.is.utils.RefDataService.getDistr(addCust$ap_code_adr_region.getValue(), alias))));
	}
	
	// accwnd$btn_print
	
	private Tclient getTFromBank(Customer cust, Tclient tcust)
	{
		
		tcust.setBank_c(EMPC_BANK_C);
		tcust.setClient_b(cust.getBranch());
		tcust.setCl_type("1");
		tcust.setCln_cat("001");
		tcust.setRec_date(new Date());
		tcust.setF_names(cust.getP_first_name());
		tcust.setSurname(cust.getP_family());
		tcust.setTitle("Client");
		tcust.setM_name(cust.getP_patronymic());
		tcust.setB_date(cust.getP_birthday());
		tcust.setR_street(cust.getP_birth_place());
		tcust.setR_city(cust.getP_birth_place());
		tcust.setR_cntry("UZB");
		// tcust.setStatus,
		tcust.setSearch_name(cust.getName());
		tcust.setSex(cust.getP_code_gender());
		tcust.setSerial_no(cust.getP_passport_number());
		tcust.setDoc_since(cust.getP_passport_date_registration());
		tcust.setIssued_by(cust.getP_passport_place_registration());
		
		addTieto$ab_date.setValue(tcust.getB_date());
		addTieto$aclient.setValue(tcust.getClient());
		addTieto$abank_c.setValue(tcust.getBank_c());
		addTieto$aclient_b.setValue(tcust.getClient_b());
		addTieto$acl_type.setValue(tcust.getCl_type());
		addTieto$acln_cat.setValue(tcust.getCln_cat());
		addTieto$af_names.setValue(tcust.getF_names());
		addTieto$asurname.setValue(tcust.getSurname());
		addTieto$atitle.setValue(tcust.getTitle());
		addTieto$am_name.setValue(tcust.getM_name());
		addTieto$ar_street.setValue(tcust.getR_street());
		addTieto$ar_city.setValue(tcust.getR_city());
		addTieto$ar_cntry.setValue(tcust.getR_cntry());
		addTieto$ausrid.setValue(tcust.getUsrid());
		// addTieto$actime.setValue(tcust.getCtime());
		addTieto$astatus.setValue(tcust.getStatus());
		addTieto$asearch_name.setValue(tcust.getSearch_name());
		addTieto$asex.setValue(tcust.getSex());
		addTieto$aserial_no.setValue(tcust.getSerial_no());
		addTieto$adoc_since.setValue(tcust.getDoc_since());
		addTieto$aissued_by.setValue(tcust.getIssued_by());
		return tcust;
	}
	
	private Customer getBFromTieto(Tclient tcust, Customer cust)
	{
		cust.setName(tcust.getSearch_name());
		cust.setBranch(branch);
		cust.setCode_country("860");
		cust.setP_code_class_credit("1");
		cust.setP_passport_type("N");
		cust.setCode_subject("P");
		cust.setSign_registr(2);
		cust.setCode_form("");
		cust.setCode_type("08");
		cust.setP_patronymic(tcust.getSurname());
		// cust.setP_passport_serial(ap_passport_serial.getValue());
		cust.setP_passport_number(tcust.getSerial_no());
		cust.setP_passport_serial(tcust.getSerial_no());
		cust.setP_passport_place_registration(tcust.getIssued_by());
		cust.setP_first_name(tcust.getF_names());
		cust.setP_family(tcust.getM_name());
		cust.setP_birthday(tcust.getB_date());
		cust.setP_post_address(tcust.getR_street());
		// System.out.println("cust : "+cust.getP_first_name()+" l "+cust.getP_family());
		if (!CheckNull.isEmpty(cust.getP_family()))
		{
			addCust$ap_family.setValue(cust.getP_family());
		}
		if (!CheckNull.isEmpty(cust.getP_first_name()))
		{
			addCust$ap_first_name.setValue(cust.getP_first_name());
		}
		if (!CheckNull.isEmpty(cust.getP_patronymic()))
		{
			addCust$ap_patronymic.setValue(cust.getP_patronymic());
		}
		if (!CheckNull.isEmpty(cust.getP_post_address()))
		{
			addCust$ap_post_address.setValue(cust.getP_post_address());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_serial()))
		{
			addCust$ap_passport_serial.setValue(cust.getP_passport_serial());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_number()))
		{
			addCust$ap_passport_number.setValue(cust.getP_passport_number());
		}
		if (!CheckNull.isEmpty(cust.getP_passport_place_registration()))
		{
			addCust$ap_passport_place_registration.setValue(cust.getP_passport_place_registration());
		}
		
		return cust;
	}
	
	public void onClick$add_to_link$addCustomer()
	{
		String log = null;
		String cur_acc = null, cl_n = "";
		
		/*
		 * if (!add_ti && add_ho && !add_br) { if (is_br) { Res res =
		 * CustomerService.update_lnk_ho_by_br(branch,
		 * addCustomer$add_cst_id.getValue(),
		 * cur_branch_customer.getId_client(), alias); if (res.getCode() == 0) {
		 * alert(res.getName()); return; } TietoCustomer lg_c =
		 * CustomerService.getCustomerById(addCustomer$add_cst_id.getValue(),
		 * branch , alias); if (lg_c.getName() != null) { cl_n = lg_c.getName()
		 * + " " + lg_c.getP_birthday(); } log =
		 * "Обновлена связка клиента с id  [" +
		 * cur_branch_customer.getId_client() + "] для филиала [" + branch +
		 * "]: выбран клиент в ГО [" + addCustomer$add_cst_id.getValue() + "] ["
		 * + cl_n + "]"; alert(log); } else if (is_ti) { Res res =
		 * CustomerService.update_lnk_ho_by_ti(branch,
		 * addCustomer$add_cst_id.getValue(), tietocl.getClient(), alias); if
		 * (res.getCode() == 0) { alert(res.getName()); return; } String halias
		 * = CustomerService.get_alias_ho(alias); TietoCustomer lg_c =
		 * CustomerService.getCustomerById(addCustomer$add_cst_id.getValue(),
		 * ConnectionPool.getValue("HO", alias), halias); if (lg_c.getName() !=
		 * null) { cl_n = lg_c.getName() + " " + lg_c.getP_birthday(); } log =
		 * "Обновлена связка клиента с tieto id [" + tietocl.getClient() +
		 * "] для филиала [" + branch + "]: выбран клиент в ГО [" +
		 * addCustomer$add_cst_id.getValue() + "] [" + cl_n + "]"; alert(log); }
		 * }
		 */

		if (!add_ti && !add_ho && add_br)
		{
			if (is_ho)
			{
				/*
				 * Res res = CustomerService.update_lnk_br_by_ho(branch,
				 * addCustomer$add_cst_id.getValue(),
				 * cur_HO_customer.getId_client(), alias); if (res.getCode() ==
				 * 0) { alert(res.getName()); return; } TietoCustomer lg_c =
				 * CustomerService
				 * .getCustomerById(addCustomer$add_cst_id.getValue(), branch,
				 * alias); if (lg_c.getName() != null) { cl_n = lg_c.getName() +
				 * " " + lg_c.getP_birthday(); } log =
				 * "Обновлена связка клиента с id в ГО [" +
				 * cur_HO_customer.getId_client() + "] для филиала [" + branch +
				 * "]: установлен клиент  [" + addCustomer$add_cst_id.getValue()
				 * + "] [" + cl_n + "]"; alert(log);
				 */
			}
			else if (is_ti)
			{
				Res res = CustomerService.update_lnk_br_by_ti(branch, addCustomer$add_cst_id.getValue(), tietocl.getClient(), alias);
				if (res.getCode() == 0)
				{
					alert(res.getName());
					return;
				}
				
				System.out.println("addCustomer$add_cst_id.getValue()->" + addCustomer$add_cst_id.getValue());
				System.out.println("branch->" + branch);
				System.out.println("alias->" + alias);
				
				Customer lg_c = CustomerService.getCustomerById(addCustomer$add_cst_id.getValue(), branch, alias);
				if (lg_c.getName() != null)
				{
					cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
				}
				
				log = "Обновлена связка клиента с tieto id [" + tietocl.getClient() + "] для [" + branch + "]  c id [" + addCustomer$add_cst_id.getValue() + "] клиента [" + cl_n + "]";
				alert(log);
			}
		}
		
		addCustomer.setVisible(false);
		
		CheckNull.clearForm(addCust$addgrdl);
		CheckNull.clearForm(addCust$addgrdr);
		addCustomer$dataGrid.getItems().clear();// .setModel(null);
		refreshModel(_starttPageNumber);
	}
	
	public void onClick$close$addCustomer()
	{
		CheckNull.clearForm(addCust$addgrdl);
		CheckNull.clearForm(addCust$addgrdr);
		addCustomer$dataGrid.getItems().clear();// .setModel(null);
		// lkdsjlf
		// addCust.setVisible(false);
		addCustomer.setVisible(false);
	}
	
	public void onClick$close_btn$add_everywhere()
	{
		add_everywhere.setVisible(false);
		fl_edit = false;
	}
	
	/*разобраться здесь*/
	public void onClick$add_btn$add_everywhere()
	{
		boolean hasErrors = false;
		String errorText = "";
		
		if ((!((add_everywhere$ap_passport_number.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_number.getValue().length() > 9))
		{
			hasErrors = true;
			errorText += "\nНомер паспорта";
		}
		if ((!((add_everywhere$ap_passport_serial.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_serial.getValue().length() > 9))
		{
			hasErrors = true;
			errorText += "\nСерия паспорта";
		}
		if ((!((add_everywhere$ap_passport_place_registration.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_passport_place_registration.getValue().length() > 200))
		{
			hasErrors = true;
			errorText += "\nГде Выдан *";
		}
		if ((!((add_everywhere$ap_family.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_family.getValue().length() > 34))
		{
			hasErrors = true;
			errorText += "\nФамилия";
		}
		if ((!((add_everywhere$ap_first_name.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_first_name.getValue().length() > 20))
		{
			hasErrors = true;
			errorText += "\nИмя";
		}
		if ((!((add_everywhere$ap_patronymic.getValue()).matches("[a-zA-Z0-9]*"))) || (add_everywhere$ap_patronymic.getValue().length() > 20))
		{
			hasErrors = true;
			errorText += "\nОтчество";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())))
		{
			hasErrors = true;
			errorText += "\nТип документа";
		}
		if ((!((add_everywhere$ap_number_tax_registration.getValue()).matches("[0-9]*"))) || (add_everywhere$ap_number_tax_registration.getValue().length() > 9))
		{
			hasErrors = true;
			errorText += "\nИНН";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())))
		{
			hasErrors = true;
			errorText += "\nГражданство";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_country.getValue())))
		{
			hasErrors = true;
			errorText += "\nСтрана";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_resident.getValue())))
		{
			hasErrors = true;
			errorText += "\nРезидент";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_registration.getValue())))
		{
			hasErrors = true;
			errorText += "\nДата выдачи";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())))
		{
			hasErrors = true;
			errorText += "\nДата рождения";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_tel.getValue())))
		{
			hasErrors = true;
			errorText += "\nПароль для телефонных разговоров";
		}
		if ((!((add_everywhere$ap_post_address.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_post_address.getValue().length() > 95))
		{
			hasErrors = true;
			errorText += "\nПочтовый адрес";
		}
		if ((!((add_everywhere$ap_birth_place.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*"))) || (add_everywhere$ap_birth_place.getValue().length() > 200))
		{
			hasErrors = true;
			errorText += "\nМесто рождения";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_gender.getValue())))
		{
			hasErrors = true;
			errorText += "\nПол";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_expiration.getValue())))
		{
			hasErrors = true;
			errorText += "\nДействителен до";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())))
		{
			hasErrors = true;
			errorText += "\nРегион проживания";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())))
		{
			hasErrors = true;
			errorText += "\nРайон проживания";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())))
		{
			hasErrors = true;
			errorText += "\nМобильный";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_email_address.getValue())))
		{
			hasErrors = true;
			errorText += "\nEmail";
		}
		
		
		
		
		
		
		
		if (hasErrors)
		{
			alert("Ошибка заполнения формы:\nневерно заполнено поле:\n" + errorText);
			return;
		}
		
		String currentAcc = null;
		Customer newСustomer = new Customer();
		String branchCustomerId = cur_branch_customer != null ? cur_branch_customer.getId_client() : null;
		String tietoCustomerId = tietocl != null ? tietocl.getClient() : null;	
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		Calendar calPassRegistration = Calendar.getInstance();
		Calendar calBirthday = Calendar.getInstance();
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo responseInfo = new OperationResponseInfo();
		
		RowType_CustomerHolder customerInfoHolder = null;
			RowType_Customer customerInfo = new RowType_Customer();
		
		ListType_CustomerCustomInfoHolder customListInfoHolder = new ListType_CustomerCustomInfoHolder();
		
		RowType_EditCustomer_Request editCustomerRequest = null;
			
		
		newСustomer.setP_passport_number(add_everywhere$ap_passport_number.getValue());
		newСustomer.setP_passport_type(add_everywhere$ap_type_document.getValue());
		newСustomer.setP_type_document(add_everywhere$ap_type_document.getValue());
		newСustomer.setP_passport_serial(add_everywhere$ap_passport_serial.getValue());
		newСustomer.setP_passport_place_registration(add_everywhere$ap_passport_place_registration.getValue());
		newСustomer.setP_family(add_everywhere$ap_family.getValue());
		newСustomer.setP_first_name(add_everywhere$ap_first_name.getValue());
		newСustomer.setName(add_everywhere$ap_family.getValue() + " " + add_everywhere$ap_first_name.getValue() + " " + add_everywhere$ap_patronymic.getValue());
		newСustomer.setP_birthday(add_everywhere$ap_birthday.getValue());
		newСustomer.setCode_country(add_everywhere$acode_country.getValue());
		newСustomer.setCode_resident(add_everywhere$acode_resident.getValue());
		newСustomer.setP_post_address(add_everywhere$ap_post_address.getValue());
		newСustomer.setR_CITY(add_everywhere$ar_city.getValue());
		newСustomer.setAcode_tel(add_everywhere$acode_tel.getValue());
		newСustomer.setP_patronymic(add_everywhere$ap_patronymic.getValue());		
		newСustomer.setP_passport_date_expiration(add_everywhere$ap_passport_date_expiration.getValue());
		newСustomer.setP_passport_date_registration(add_everywhere$ap_passport_date_registration.getValue());
		newСustomer.setP_code_birth_region(CheckNull.isEmpty(add_everywhere$ap_code_birth_region.getValue()) ? null : add_everywhere$ap_code_birth_region.getValue());
		newСustomer.setP_code_birth_distr(add_everywhere$ap_code_birth_distr.getValue());
		newСustomer.setP_birth_place(add_everywhere$ap_birth_place.getValue());
		newСustomer.setP_code_gender(add_everywhere$ap_code_gender.getValue());
		//newСustomer.setP_code_nation(add_everywhere$ap_code_nation.getValue());
		newСustomer.setP_code_nation("01");
		newСustomer.setP_code_adr_region(add_everywhere$ap_code_adr_region.getValue());
		newСustomer.setP_code_adr_distr(add_everywhere$ap_code_adr_distr.getValue());
		newСustomer.setP_code_tax_org(add_everywhere$ap_code_tax_org.getValue());
		newСustomer.setP_number_tax_registration(add_everywhere$ap_number_tax_registration.getValue());
		newСustomer.setP_code_citizenship(add_everywhere$ap_code_citizenship.getValue());
		newСustomer.setP_phone_mobile(add_everywhere$ap_phone_mobile.getValue());
		newСustomer.setP_email_address(add_everywhere$ap_email_address.getValue());
		newСustomer.setP_phone_home(add_everywhere$ap_phone_home.getValue());		
		newСustomer.setP_inps(add_everywhere$ap_inps.getValue());		
		newСustomer.setBranch(ConnectionPool.getValue("HO", alias));
		newСustomer.setP_code_bank(ConnectionPool.getValue("HO", alias));
		newСustomer.setP_code_class_credit(Constants.P_CODE_CLASS_CREDIT);
		newСustomer.setP_passport_type(Constants.P_PASSPORT_TYPE);
		newСustomer.setCode_subject(Constants.CODE_SUBJECT);
		newСustomer.setSign_registr(Constants.SIGN_REGISTR);
		newСustomer.setCode_form(Constants.CODE_FORM);
		newСustomer.setCode_type(Constants.CODE_TYPE);
		newСustomer.setBranch(branch);
		newСustomer.setP_code_bank(branch);
		
		
		if(cur_branch_customer != null)
		{
			newСustomer.setId(cur_branch_customer.getId());
			newСustomer.setId_client(cur_branch_customer.getId_client());
		}		
		
		
		// OperationConnectionInfo
		connectionInfo.setBANK_C(EMPC_BANK_C);
		connectionInfo.setGROUPC(EMPC_GROUPC);
		
		
		// RowType_CustomerHolder
		customerInfo.setF_NAMES(newСustomer.getP_first_name());
		customerInfo.setCL_TYPE(Constants.CUSTOMER_CL_TYPE);		
		customerInfo.setCLIENT_B(branchCustomerId);
		customerInfo.setSURNAME(newСustomer.getP_family());
		//customerInfo.setM_NAME(newСustomer.getP_patronymic());	
		customerInfo.setM_NAME(newСustomer.getAcode_tel());	
		calPassRegistration.setTime(newСustomer.getP_passport_date_registration());
		customerInfo.setDOC_SINCE(calPassRegistration);		
		calBirthday.setTime(newСustomer.getP_birthday());
		customerInfo.setB_DATE(calBirthday);		
		customerInfo.setRESIDENT(newСustomer.getCode_resident());
		customerInfo.setSTATUS(Constants.CUSTOMER_STATUS);
		customerInfo.setSEX(newСustomer.getP_code_gender());
		customerInfo.setSERIAL_NO(newСustomer.getP_passport_serial() + newСustomer.getP_passport_number());
		customerInfo.setID_CARD(newСustomer.getP_passport_number());
		customerInfo.setR_CITY(newСustomer.getP_code_citizenship());
		customerInfo.setR_STREET(newСustomer.getP_birth_place());
		customerInfo.setR_E_MAILS(newСustomer.getP_email_address());
		customerInfo.setR_MOB_PHONE(newСustomer.getP_phone_mobile());
		customerInfo.setR_PHONE(newСustomer.getP_phone_home());
		customerInfo.setR_CNTRY(newСustomer.getP_code_citizenship().equals(Constants.COUNTRY_CODE) ? Constants.COUNTRY_ALPHA : null);
		customerInfo.setISSUED_BY(newСustomer.getP_passport_place_registration());
		customerInfo.setPERSON_CODE(newСustomer.getP_number_tax_registration());
		customerInfo.setDOC_TYPE(Constants.CUSTOMER_DOC_TYPE);
		customerInfo.setREC_DATE(calendar);
		
		customerInfoHolder = new RowType_CustomerHolder(customerInfo);
		
		
		if(instantToRealUniq) {
			editCustomerRequest = new RowType_EditCustomer_Request (
					tietoCustomerId, 
					EMPC_BANK_C,
					customerInfo.getCLIENT_B(),
					customerInfo.getCL_TYPE(),
					customerInfo.getCLN_CAT(),
					customerInfo.getREC_DATE(),
					customerInfo.getF_NAMES(), 
					customerInfo.getSURNAME(),
					customerInfo.getTITLE(),
					customerInfo.getM_NAME(),
					customerInfo.getB_DATE(),
					customerInfo.getRESIDENT(),
					customerInfo.getID_CARD(),
					customerInfo.getDOC_TYPE(),						
					customerInfo.getR_PHONE(),
					customerInfo.getEMP_NAME(),
					customerInfo.getPOSITION(),
					customerInfo.getEMP_DATE(),
					customerInfo.getWORK_PHONE(),
					customerInfo.getR_STREET(),						
					customerInfo.getR_CITY(),
					customerInfo.getR_CNTRY(),
					customerInfo.getR_PCODE(),
					customerInfo.getC_SINCE(),
					customerInfo.getCMP_NAME(),
					customerInfo.getCMPG_NAME(),
					customerInfo.getCO_POSITON(),						
					customerInfo.getCONTACT(),
					customerInfo.getCNT_TITLE(),
					customerInfo.getCNT_PHONE(),
					customerInfo.getCNT_FAX(),
					customerInfo.getMNG_POSIT(),
					customerInfo.getMNG_NAME(),						
					customerInfo.getMNG_PHONE(),
					customerInfo.getMNG_TITLE(),
					customerInfo.getMNG_FAX(),
					customerInfo.getREG_NR(),
					customerInfo.getCR_STREET(),
					customerInfo.getCR_CITY(),						
					customerInfo.getCR_CNTRY(),
					customerInfo.getCR_PCODE(),
					customerInfo.getCOMENT(),
					customerInfo.getREGION(),
					customerInfo.getPERSON_CODE(),
					customerInfo.getRESIDENT_SINCE(),						
					customerInfo.getYEAR_INC(),
					customerInfo.getCCY_FOR_INCOM(),
					customerInfo.getIMM_PROP_VALUE(),
					customerInfo.getSEARCH_NAME(),
					customerInfo.getMARITAL_STATUS(),
					customerInfo.getCO_CODE(),						
					customerInfo.getEMP_CODE(),
					customerInfo.getSEX(),
					customerInfo.getSERIAL_NO(),
					customerInfo.getDOC_SINCE(),
					customerInfo.getISSUED_BY(),
					customerInfo.getEMP_ADR(),
					customerInfo.getEMP_FAX(),
					customerInfo.getEMP_E_MAILS(),						
					customerInfo.getR_E_MAILS(),
					customerInfo.getR_MOB_PHONE(),
					customerInfo.getR_FAX(),
					customerInfo.getCNT_E_MAILS(),
					customerInfo.getCNT_MOB_PHONE(),
					customerInfo.getMNG_MOB_PHONE(),
					customerInfo.getMNG_E_MAILS(),
					customerInfo.getCR_E_MAILS(),						
					customerInfo.getIN_FILE_NUM(),
					customerInfo.getU_COD1(),
					customerInfo.getU_COD2(),
					customerInfo.getU_COD3(),
					customerInfo.getU_FIELD1(),
					customerInfo.getU_FIELD2(),
					customerInfo.getCALL_ID(),
					customerInfo.getHOME_NUMBER(),						
					customerInfo.getBUILDING(),
					customerInfo.getSTREET1(),
					customerInfo.getAPARTMENT(),
					customerInfo.getMIDLE_NAME(),
					customerInfo.getSTATUS(),
					null,
					customerInfo.getAMEX_MEMBER_SINCE(),
					customerInfo.getDCI_MEMBER_SINCE(),						
					customerInfo.getREWARD_NO()
			);
		
			try
			{
				responseInfo = issuingPortProxy.editCustomer(connectionInfo, editCustomerRequest);
				
				if (responseInfo.getError_description() != null)
				{
					alert(responseInfo.getError_action() + "\r\n" + responseInfo.getError_description());
					LtLogger.getLogger().error("TietoCustomer hasn't been edited in TIETO, error code:\n" +
							responseInfo.getResponse_code() + "\nTietoCustomer: " + customerInfoHolder.value.getCLIENT());
					
					fl_edit = false;
					refreshModel(_startPageNumber);
					add_everywhere.setVisible(false);
					openCardWindow.setVisible(false);
					return;
				}
				else
				{
					CardCustomerSearch info = CustomerService.getInfoFromLink(alias, tietocl.getClient());
					
					RowType_EditAccount_Request parametersAcc = new RowType_EditAccount_Request();
					parametersAcc.setCARD_ACCT(info.getCardAcct());
					parametersAcc.setTRANZ_ACCT(openCardGlobuzAccount.getId());
					parametersAcc.setCCY(Constants.AGREEMENT_CCY);
					
					
					responseInfo = issuingPortProxy.editAccount(connectionInfo, parametersAcc);
					if(responseInfo.getError_description() != null) {
						
						alert(responseInfo.getError_action() + "\r\n" + responseInfo.getError_description());	
						add_everywhere.setVisible(false);
						openCardWindow.setVisible(false);
						refreshModel(_startPageNumber);
						return;
					}
					else {
					
						
						
						String realPan = TclientService.realPan(issuingPortProxy, info.getCardPan());

						if(realPan == null) {
							alert("Ошибка запроса getRealCard");
							add_everywhere.setVisible(false);
							openCardWindow.setVisible(false);
							refreshModel(_startPageNumber);
							return;
						}
						
						Connection c = null;
						int code = 0;
						String description = null;
						
						try	{
							c = ConnectionPool.getConnection_mssql();
							CallableStatement cstmt = c.prepareCall("{Call dbo.CreateClient(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}"); 

							cstmt.setString(1, branch);
							cstmt.setString(2, openCardGlobuzAccount.getId());
							cstmt.setString(3, realPan);
							cstmt.setString(4, newСustomer.getP_family());
							cstmt.setString(5, newСustomer.getP_first_name());
							cstmt.setString(6, newСustomer.getP_patronymic());
							cstmt.setString(7, newСustomer.getP_phone_mobile());
							cstmt.setString(8, datef.format(newСustomer.getP_birthday()));
							cstmt.setString(9, CustomerService.transliterate(add_everywhere$ap_code_adr_region.getText()));
							cstmt.setString(10, CustomerService.transliterate(add_everywhere$ap_code_adr_distr.getText()));
							cstmt.setString(11, add_everywhere$ap_post_address.getValue());
							cstmt.setString(12, newСustomer.getP_passport_serial());
							cstmt.setString(13, newСustomer.getP_passport_number());
							cstmt.setString(14, newСustomer.getP_passport_place_registration());
							cstmt.setString(15, datef.format(newСustomer.getP_passport_date_registration()));
							cstmt.setString(16, datef.format(newСustomer.getP_passport_date_expiration()));
							cstmt.registerOutParameter(17, java.sql.Types.INTEGER);
							cstmt.registerOutParameter(18, java.sql.Types.VARCHAR);
							
							cstmt.execute();
							
							code = cstmt.getInt(17);
							description = cstmt.getString(18);
							
							if(code == 0) {
								c.commit();
							}
							
						}
						catch (Exception e)
						{
							alert(CheckNull.getPstr(e));
							ISLogger.getLogger().error(CheckNull.getPstr(e));
							add_everywhere.setVisible(false);
							openCardWindow.setVisible(false);
							refreshModel(_startPageNumber);
							return;
						}
						finally
						{
							ConnectionPool.close(c);
							c = null;
						}
						
						
						if(code == 0) {
							alert("Запрос выполнен успешно");
						}
						else {
							alert("Код ошибки: " + code + "\nОписание:\n" + description);
							ISLogger.getLogger().error("\nMsSql error code: " + code + "\nDescription\n" + description);
							add_everywhere.setVisible(false);
							openCardWindow.setVisible(false);
							refreshModel(_startPageNumber);
							return;
						}
						
						RowType_InstantToReal_Request parameters = new RowType_InstantToReal_Request();
						parameters.setBANK_C("01");
						parameters.setGROUPC("01");
						parameters.setCLIENT(tietocl.getClient());
						
						
						responseInfo = issuingPortProxy.instantToReal(connectionInfo, parameters);
						if(responseInfo.getError_description() != null) {
							
							alert(responseInfo.getError_action() + "\r\n" + responseInfo.getError_description());	
							return;
						}

					}
					
					fl_edit = false;
					
					add_everywhere.setVisible(false);
					openCardWindow.setVisible(false);
					refreshModel(_startPageNumber);
					return;
				}
			}
			catch (RemoteException e)
			{
				LtLogger.getLogger().error("TietoCustomer hasn't been edited, error:\n" + CheckNull.getPstr(e));
				alert("TietoCustomer hasn't been edited, error:\n" + e.getMessage());
				
				fl_edit = false;
				refreshModel(_startPageNumber);
				add_everywhere.setVisible(false);
				openCardWindow.setVisible(false);
				return;
			}
			
		}
		
		
		
		
		if (!fl_edit)
		{
			// ////////////////////////////// OPEN IN BRANCH //
			// ////////////////////////
			if (add_br)
			{
				Res res = CustomerService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), 
						newСustomer, 1, 2, alias, true);
				
				if (res.getCode() != 0)
				{
					alert("ОШИБКА\nОткрытие клиента :\n" + res.getName());
					fl_edit = false;
					return;
				}
				else
				{
					alert("Клиент добавлен : " + res.getName());
					Customer cst = com.is.tieto_capital.customer.CustomerService.getCustomerById_tbl(res.getName(), branch, alias);
					branchCustomerId = cst.getId_client();
					
				}
				
				if (!add_ti)
				{
					refreshModel(_starttPageNumber);
					add_everywhere.setVisible(false);
					fl_edit = false;
					return;
				}
				
			}

			// !------------------------------ Add new customer to TIETO ------------------------------
			if (add_ti)
			{
				try
				{
					// Sending request newCustomer
					responseInfo = issuingPortProxy.newCustomer(connectionInfo, customerInfoHolder, customListInfoHolder);
					
					if (responseInfo.getError_description() != null)
					{
						alert(responseInfo.getError_action() + "\r\n" + responseInfo.getError_description());
						LtLogger.getLogger().error("New customer hasn't been opened in TIETO, error code:\n" +
								responseInfo.getResponse_code() + "\nTietoCustomer: " + customerInfoHolder.value.getCLIENT());
					}
					else
					{
						alert("Клиент добавлен в ТИЕТО");
						tietoCustomerId = customerInfoHolder.value.getCLIENT();
						LtLogger.getLogger().info("New customer added in TIETO: " + customerInfoHolder.value.getCLIENT());						
					}					
				}
				catch (RemoteException e)
				{
					LtLogger.getLogger().error("New customer hasn't been opened, error:\n" + CheckNull.getPstr(e));
					alert("New customer hasn't been opened, error:\n" + e.getMessage());
				}
				
				fl_edit = false;
				
			}
			
			// ////////////////////////////// CREATE A LINK
			// ////////////////////////////
			if (add_ti && add_br)
			{
				CustomerService.create_lnk(branch, null, branchCustomerId, tietoCustomerId, currentAcc, alias, null, null);
			}
			
			if (add_ti && !add_br)
			{
				CustomerService.update_lnk_ti_by_br(branch, cur_branch_customer.getId_client(), tietoCustomerId, alias);
			}
		}
		
		else
		{
			if (edit_br)
			{
				// редактировать везде
				Res res = CustomerService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), newСustomer, 19, 0, alias, true);
				if (res.getCode() != 0)
				{
					alert(res.getName());
					return;
				}
				edit_br = false;
				alert("Клиент отредктирован в банке");
			}
			
			if (edit_ti)
			{
				// !------------------------------ Edit customer in TIETO ------------------------------
				
				editCustomerRequest = new RowType_EditCustomer_Request (
							tietoCustomerId, 
							EMPC_BANK_C,
							customerInfo.getCLIENT_B(),
							customerInfo.getCL_TYPE(),
							customerInfo.getCLN_CAT(),
							customerInfo.getREC_DATE(),
							customerInfo.getF_NAMES(), 
							customerInfo.getSURNAME(),
							customerInfo.getTITLE(),
							customerInfo.getM_NAME(),
							customerInfo.getB_DATE(),
							customerInfo.getRESIDENT(),
							customerInfo.getID_CARD(),
							customerInfo.getDOC_TYPE(),						
							customerInfo.getR_PHONE(),
							customerInfo.getEMP_NAME(),
							customerInfo.getPOSITION(),
							customerInfo.getEMP_DATE(),
							customerInfo.getWORK_PHONE(),
							customerInfo.getR_STREET(),						
							customerInfo.getR_CITY(),
							customerInfo.getR_CNTRY(),
							customerInfo.getR_PCODE(),
							customerInfo.getC_SINCE(),
							customerInfo.getCMP_NAME(),
							customerInfo.getCMPG_NAME(),
							customerInfo.getCO_POSITON(),						
							customerInfo.getCONTACT(),
							customerInfo.getCNT_TITLE(),
							customerInfo.getCNT_PHONE(),
							customerInfo.getCNT_FAX(),
							customerInfo.getMNG_POSIT(),
							customerInfo.getMNG_NAME(),						
							customerInfo.getMNG_PHONE(),
							customerInfo.getMNG_TITLE(),
							customerInfo.getMNG_FAX(),
							customerInfo.getREG_NR(),
							customerInfo.getCR_STREET(),
							customerInfo.getCR_CITY(),						
							customerInfo.getCR_CNTRY(),
							customerInfo.getCR_PCODE(),
							customerInfo.getCOMENT(),
							customerInfo.getREGION(),
							customerInfo.getPERSON_CODE(),
							customerInfo.getRESIDENT_SINCE(),						
							customerInfo.getYEAR_INC(),
							customerInfo.getCCY_FOR_INCOM(),
							customerInfo.getIMM_PROP_VALUE(),
							customerInfo.getSEARCH_NAME(),
							customerInfo.getMARITAL_STATUS(),
							customerInfo.getCO_CODE(),						
							customerInfo.getEMP_CODE(),
							customerInfo.getSEX(),
							customerInfo.getSERIAL_NO(),
							customerInfo.getDOC_SINCE(),
							customerInfo.getISSUED_BY(),
							customerInfo.getEMP_ADR(),
							customerInfo.getEMP_FAX(),
							customerInfo.getEMP_E_MAILS(),						
							customerInfo.getR_E_MAILS(),
							customerInfo.getR_MOB_PHONE(),
							customerInfo.getR_FAX(),
							customerInfo.getCNT_E_MAILS(),
							customerInfo.getCNT_MOB_PHONE(),
							customerInfo.getMNG_MOB_PHONE(),
							customerInfo.getMNG_E_MAILS(),
							customerInfo.getCR_E_MAILS(),						
							customerInfo.getIN_FILE_NUM(),
							customerInfo.getU_COD1(),
							customerInfo.getU_COD2(),
							customerInfo.getU_COD3(),
							customerInfo.getU_FIELD1(),
							customerInfo.getU_FIELD2(),
							customerInfo.getCALL_ID(),
							customerInfo.getHOME_NUMBER(),						
							customerInfo.getBUILDING(),
							customerInfo.getSTREET1(),
							customerInfo.getAPARTMENT(),
							customerInfo.getMIDLE_NAME(),
							customerInfo.getSTATUS(),
							null,
							customerInfo.getAMEX_MEMBER_SINCE(),
							customerInfo.getDCI_MEMBER_SINCE(),						
							customerInfo.getREWARD_NO()
					);
				
				try
				{
					responseInfo = issuingPortProxy.editCustomer(connectionInfo, editCustomerRequest);
					
					if (responseInfo.getError_description() != null)
					{
						alert(responseInfo.getError_action() + "\r\n" + responseInfo.getError_description());
						LtLogger.getLogger().error("TietoCustomer hasn't been edited in TIETO, error code:\n" +
								responseInfo.getResponse_code() + "\nTietoCustomer: " + customerInfoHolder.value.getCLIENT());
					}
					else
					{
						alert("Клиент отредактирован в ТИЕТО");
						tietoCustomerId = customerInfoHolder.value.getCLIENT();
						LtLogger.getLogger().info("TietoCustomer edited in TIETO: " + customerInfoHolder.value.getCLIENT());
					}
				}
				catch (RemoteException e)
				{
					LtLogger.getLogger().error("TietoCustomer hasn't been edited, error:\n" + CheckNull.getPstr(e));
					alert("TietoCustomer hasn't been edited, error:\n" + e.getMessage());
				}
			}
		}
		
		fl_edit = false;
		refreshModel(_startPageNumber);
		add_everywhere.setVisible(false);
	}
	
	
	/**
	 * @param cardCode
	 * @param newCardAccount
	 * @return FALSE, if card wasn't open. TRUE, if card was open.
	 */
	private boolean openCard(String cardCode, String newCardAccount, String holderName) {
		
		Res result = null;
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		
		TietoCardSetting tietoCardSetting = TclientService.getTietoCardSetting(cardCode, alias);
		CardApproval cardApproval = new CardApproval(
				null, 
				tietoCardSetting.getName(), 
				Constants.APPROVAL_TYPE_CONFIRM,
				cur_branch_customer.getId_client(), 
				branch, 
				Constants.APPROVAL_STATE_OPEN, 
				currentDate, 
				tietocl.getClient(), 
				newCardAccount,
				holderName
		);

		result = CardApprovalService.insertCardApproval(cardApproval);
		
		alert(result.getName());
		
		if(result.getCode() != 0) {
			return false;
		}
		
		return true;

	}
	
	/**
	 * @param cardCode
	 * @param newCardAccount
	 * @return FALSE, if card wasn't reissue. TRUE, if card was reissue.
	 */
	private boolean cardReissue(String cardCode, String newCardAccount) {
		
		if(!(newCardAccount.substring(0, 5)).equals(Constants.ACC_22618)) {
			alert("Неверный счет карты: " + newCardAccount);
			return false;
		}
		
		Calendar calendar = Calendar.getInstance();
		String cardName = "";
		
		Tclient tietoClient = tietocl;
		TietoCardSetting tietoCardSetting = TclientService.getTietoCardSetting(cardCode, alias);

		
		// editAgreement
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();		
		RowType_EditAgreement_Request agreementInfo = new RowType_EditAgreement_Request();
		
		// addInfo4Agreement
		KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		
		ListType_AccountInfoHolder accountInfoHolder = new ListType_AccountInfoHolder();
		
		ListType_CardInfoHolder cardsListInfoHolder = null;
		ListType_CardInfo cardsListInfo = new ListType_CardInfo();
			RowType_CardInfo[] rowCardInfoArray = null;
			RowType_CardInfo rowCardInfo = new RowType_CardInfo();
				RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
				RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
				RowType_CardInfo_EMV_Data EMV_Data = new RowType_CardInfo_EMV_Data();	
		
		OperationResponseInfo responseInfo = null;
		
		try {
			// OperationConnectionInfo
			connectionInfo.setBANK_C(tietoCardSetting.getBank_c());
			connectionInfo.setGROUPC(tietoCardSetting.getGroup_c());
			
			// RowType_EditAgreement_Request
			agreementInfo.setBINCOD(tietoCardSetting.getBin());
			agreementInfo.setBANK_CODE(EMPC_BANK_C);			
			agreementInfo.setBRANCH(branch);
			agreementInfo.setCITY(CheckNull.isEmpty(tietoClient.getR_city()) ? "UZB" : tietoClient.getR_city());			
			agreementInfo.setPRODUCT(cardCode);			
			agreementInfo.setREP_LANG(Constants.AGREEMENT_REP_LANG);			
			agreementInfo.setRISK_LEVEL(tietoCardSetting.getRisk_level());			
			agreementInfo.setSTREET(CheckNull.isEmpty(tietoClient.getR_street()) ? "STREET" : tietoClient.getR_street());			
			agreementInfo.setSTATUS(Constants.AGREEMENT_STATUS);
			agreementInfo.setCONTRACT("123");			
			agreementInfo.setENROLLED(calendar);
			agreementInfo.setDISTRIB_MODE(Constants.AGREEMENT_DISTRIB_MODE);
			agreementInfo.setCLIENT(tietoClient.getClient());
			agreementInfo.setCOUNTRY(CheckNull.isEmpty(tietoClient.getR_cntry()) ? "UZB" : tietoClient.getR_cntry());
			
			
			// Sending request editAgreement
			responseInfo = issuingPortProxy.editAgreement(connectionInfo, agreementInfo);
			if((responseInfo.getError_description() != null) || (responseInfo.getResponse_code().intValue() != 0))
			{
				alert("Card hasn't been reissued in TIETO\n" + responseInfo.getError_description());
				
				return false;
			}
			
			
			// Getting new AGRE_NOM
			mainAgreementInfo.setAGRE_NOM(agreementInfo.getAGRE_NOM());
			
			// ListType_CardInfoHolder
			logicalCard.setCOND_SET(tietoCardSetting.getCard_condition());
			logicalCard.setRISK_LEVEL(tietoCardSetting.getRisk_level());
			logicalCard.setBASE_SUPP(Constants.AGREEMENT_BASE_SUPP);
			logicalCard.setF_NAMES(tietoClient.getF_names());
			logicalCard.setSURNAME(tietoClient.getSurname());
			
			cardName = tietoClient.getF_names() + " " + tietoClient.getSurname();
			physicalCard.setCARD_NAME(
					cardName.length() <= Constants.MAX_LENGTH_OF_CARD_NAME 
					? cardName 
					: (tietoClient.getSurname() + " " + (tietoClient.getF_names()).substring(0, 1).toUpperCase())
			);
			physicalCard.setSTATUS1(Constants.AGREEMENT_STATUS1);
			physicalCard.setDESIGN_ID(tietoCardSetting.getDesign_id());
			
			EMV_Data.setCHIP_APP_ID(tietoCardSetting.getId_chip_app());
			
			rowCardInfo.setLogicalCard(logicalCard);
			rowCardInfo.setPhysicalCard(physicalCard);
			rowCardInfo.setEMV_Data(EMV_Data);
			
			rowCardInfoArray = new RowType_CardInfo[] {
					rowCardInfo
			};			
			cardsListInfo.setRow(rowCardInfoArray);
			
			cardsListInfoHolder = new ListType_CardInfoHolder(cardsListInfo);
			
			
			// Sending request addInfo4Agreement
			responseInfo = issuingPortProxy.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountInfoHolder, cardsListInfoHolder);
			if((responseInfo.getError_description() != null) || (responseInfo.getResponse_code().intValue() != 0))
			{
				alert("Card hasn't been reissued in TIETO\n" + responseInfo.getError_description());
				
				return false;
			}
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error("Card hasn't been reissued, error:\n" + CheckNull.getPstr(e));
			alert("Card hasn't been reissued, error:\n" + e.getMessage());

			return false;
		}
		
		alert("Карта перевыпущена: " + tietoCardSetting.getName());
		// Закрыть окно
		return true;
	}

	
	public void onClick$btn_cancel$addwnd()
	{
		addwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$show_cards()
	{
		show_cards.setVisible(false);
	}
	
	public void onClick$tbtn_add_card()
	{
		addwnd.setTitle("Дейсвтия со счетами");
		
		if (tietocl == null)
		{
			alert("Клиент из ТИЕТО не выбран");
			return;
		}
		
		if(!access[0].equals("open")) {
			addwnd$openAccFunctions.setVisible(false);
		}
		
		String cl_id = CustomerService.get_BR_by_tieto(tietocl.getClient(), alias);
		
		Res res = new Res();
		res = TclientService.check_user(alias, branch, tietocl.getClient());
		if (res.getCode() == 0)
		{
			alert(res.getName());
			return;
		}
		
		GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
		acfilter.setClient(cl_id);
		acfilter.setAcc_bal(Constants.ACC_22618);
		acfilter.setBranch(branch);
		acfilter.setCurrency(Constants.CURRENCY_USD);
		if(smart) {
			acfilter.setId_order("444");
		}
		com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
		
		addwnd$accGrid.setModel((ListModel) acc_model);
		
		addwnd$sproduct.setSelecteditem(null);
		addwnd$btn_add.setVisible(true);
		addwnd$sproduct.setVisible(true);
		addwnd.setVisible(true);
	}
	
	private void set_default()
	{
		CheckNull.clearForm(addCustomer$addgrdl);
		CheckNull.clearForm(addCustomer$addgrdr);
		CheckNull.clearForm(add_everywhere$addgrdl);
		CheckNull.clearForm(add_everywhere$addgrdr);
		
		if (add_ti)
		{
			if (is_ho)
			{
				fill_form(cur_HO_customer, null);
			}
			else if (is_br)
			{
                alert("Мы тут 3" + cur_branch_customer.getId_client());
				fill_form(cur_branch_customer, null);
                alert("Мы тут 3.1");
			}
		}
		else if (add_ho)
		{
			if (is_br)
			{
				fill_bank_form(cur_branch_customer);
			}
			else if (is_ti)
			{
				
			}
		}
		else if (add_br)
		{
			if (is_ho)
			{
				fill_bank_form(cur_HO_customer);
			}
			else if (is_ti)
			{
				
			}
		}
	}
	
	private void fill_bank_form(Customer base_cl)
	{
		if (base_cl.getP_passport_number() != null) addCustomer$ap_passport_number.setValue(base_cl.getP_passport_number());
		if (base_cl.getP_type_document() != null) addCustomer$ap_type_document.setSelecteditem(base_cl.getP_type_document());
		if (base_cl.getP_passport_serial() != null) addCustomer$ap_passport_serial.setValue(base_cl.getP_passport_serial());
		if (base_cl.getP_passport_date_registration() != null) addCustomer$ap_passport_date_registration.setValue(base_cl.getP_passport_date_registration());
		if (base_cl.getP_passport_place_registration() != null) addCustomer$ap_passport_place_registration.setValue(base_cl.getP_passport_place_registration());
		if (base_cl.getP_passport_date_expiration() != null) addCustomer$ap_passport_date_expiration.setValue(base_cl.getP_passport_date_expiration());
		
		if (base_cl.getP_family() != null) addCustomer$ap_family.setValue(base_cl.getP_family());
		if (base_cl.getP_first_name() != null) addCustomer$ap_first_name.setValue(base_cl.getP_first_name());
		if (base_cl.getP_patronymic() != null) addCustomer$ap_patronymic.setValue(base_cl.getP_patronymic());
		
		if (base_cl.getP_birthday() != null) addCustomer$ap_birthday.setValue(base_cl.getP_birthday());
		if (base_cl.getP_code_birth_region() != null) addCustomer$ap_code_birth_region.setSelecteditem(base_cl.getP_code_birth_region());
		if (base_cl.getP_code_birth_distr() != null) addCustomer$ap_code_birth_distr.setSelecteditem(base_cl.getP_code_birth_distr());
		if (base_cl.getP_birth_place() != null) addCustomer$ap_birth_place.setValue(base_cl.getP_birth_place());
		if (base_cl.getP_code_gender() != null) addCustomer$ap_code_gender.setSelecteditem(base_cl.getP_code_gender());
		if (base_cl.getP_code_nation() != null) addCustomer$ap_code_nation.setSelecteditem(base_cl.getP_code_nation());
		if (base_cl.getP_code_adr_region() != null) addCustomer$ap_code_adr_region.setSelecteditem(base_cl.getP_code_adr_region());
		if (base_cl.getP_code_adr_distr() != null) addCustomer$ap_code_adr_distr.setSelecteditem(base_cl.getP_code_adr_distr());
		if (base_cl.getP_code_tax_org() != null) addCustomer$ap_code_tax_org.setSelecteditem(base_cl.getP_code_tax_org());
		if (base_cl.getP_number_tax_registration() != null) addCustomer$ap_number_tax_registration.setValue(base_cl.getP_number_tax_registration());
		if (base_cl.getP_code_citizenship() != null) addCustomer$ap_code_citizenship.setSelecteditem(base_cl.getP_code_citizenship());
		if (base_cl.getCode_country() != null) addCustomer$acode_country.setSelecteditem(base_cl.getCode_country());
		if (base_cl.getCode_resident() != null) addCustomer$acode_resident.setSelecteditem(base_cl.getCode_resident());
		if (base_cl.getP_phone_mobile() != null) addCustomer$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
		if (base_cl.getP_email_address() != null) addCustomer$ap_email_address.setValue(base_cl.getP_email_address());
		if (base_cl.getP_phone_home() != null) addCustomer$ap_phone_home.setValue(base_cl.getP_phone_home());
		if (base_cl.getP_inps() != null) addCustomer$ap_inps.setValue(base_cl.getP_inps());
		if (base_cl.getP_post_address() != null) addCustomer$ap_post_address.setValue(base_cl.getP_post_address());
	}
	
	private void fill_form(Customer base_cl, Tclient tieto_cl)
	{
		if (tieto_cl != null)
		{
			fill_form(tieto_cl);
		}
		if (base_cl.getP_passport_number() != null) add_everywhere$ap_passport_number.setValue(base_cl.getP_passport_number());
        if (base_cl.getP_type_document() != null) add_everywhere$ap_type_document.setSelecteditem(base_cl.getP_type_document());
        if (base_cl.getP_passport_serial() != null) add_everywhere$ap_passport_serial.setValue(base_cl.getP_passport_serial());
        if (base_cl.getP_passport_date_registration() != null) add_everywhere$ap_passport_date_registration.setValue(base_cl.getP_passport_date_registration());
        if (base_cl.getP_passport_place_registration() != null) add_everywhere$ap_passport_place_registration.setValue(base_cl.getP_passport_place_registration());
        if (base_cl.getP_passport_date_expiration() != null) add_everywhere$ap_passport_date_expiration.setValue(base_cl.getP_passport_date_expiration());
        if (base_cl.getP_family() != null) add_everywhere$ap_family.setValue(base_cl.getP_family());
        if (base_cl.getP_first_name() != null) add_everywhere$ap_first_name.setValue(base_cl.getP_first_name());
        if (base_cl.getP_patronymic() != null) add_everywhere$ap_patronymic.setValue(base_cl.getP_patronymic());
        if (base_cl.getP_birthday() != null) add_everywhere$ap_birthday.setValue(base_cl.getP_birthday());

        if (base_cl.getP_code_birth_region() != null) add_everywhere$ap_code_birth_region.setSelecteditem(base_cl.getP_code_birth_region());

        if (base_cl.getP_code_birth_distr() != null) add_everywhere$ap_code_birth_distr.setSelecteditem(base_cl.getP_code_birth_distr());
        if (base_cl.getP_birth_place() != null) add_everywhere$ap_birth_place.setValue(base_cl.getP_birth_place());
        if (base_cl.getP_code_gender() != null) add_everywhere$ap_code_gender.setSelecteditem(base_cl.getP_code_gender());
        alert("Мы тут 15 " +  add_everywhere$ap_code_nation);
        if (base_cl.getP_code_nation() != null) add_everywhere$ap_code_nation.setSelecteditem(base_cl.getP_code_nation());
        alert("Мы тут 16");
        if (base_cl.getP_code_adr_region() != null) add_everywhere$ap_code_adr_region.setSelecteditem(base_cl.getP_code_adr_region());
        alert("Мы тут 17");
        if (base_cl.getP_code_adr_distr() != null) add_everywhere$ap_code_adr_distr.setSelecteditem(base_cl.getP_code_adr_distr());
        alert("Мы тут 18");
        if (base_cl.getP_code_tax_org() != null) add_everywhere$ap_code_tax_org.setSelecteditem(base_cl.getP_code_tax_org());
        alert("Мы тут 19");
        if (base_cl.getP_number_tax_registration() != null) add_everywhere$ap_number_tax_registration.setValue(base_cl.getP_number_tax_registration());
        alert("Мы тут 20");
        if (base_cl.getP_code_citizenship() != null) add_everywhere$ap_code_citizenship.setSelecteditem(base_cl.getP_code_citizenship());
        alert("Мы тут 21");
        if (base_cl.getCode_country() != null) add_everywhere$acode_country.setSelecteditem(base_cl.getCode_country());
        alert("Мы тут 22");
        if (base_cl.getCode_resident() != null) add_everywhere$acode_resident.setSelecteditem(base_cl.getCode_resident());
        alert("Мы тут 23");
        if (base_cl.getP_phone_mobile() != null) add_everywhere$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
        alert("Мы тут 24");
        if (base_cl.getP_email_address() != null) add_everywhere$ap_email_address.setValue(base_cl.getP_email_address());
        alert("Мы тут 25");
        if (base_cl.getP_phone_home() != null) add_everywhere$ap_phone_home.setValue(base_cl.getP_phone_home());
        alert("Мы тут 26");
        if (base_cl.getP_inps() != null) add_everywhere$ap_inps.setValue(base_cl.getP_inps());
        alert("Мы тут 27");
        if (base_cl.getP_post_address() != null) add_everywhere$ap_post_address.setValue(base_cl.getP_post_address());
        alert("Мы тут 28");
        if (base_cl.getP_code_birth_region() != null) add_everywhere$ar_city.setSelecteditem(base_cl.getP_code_birth_region());
		
	}
	
	private void fill_form(Tclient base_cl)
	{
		if (base_cl.getSerial_no() != null) add_everywhere$ap_passport_number.setValue(base_cl.getSerial_no());
		if (base_cl.getId_card() != null) add_everywhere$ap_passport_serial.setValue(base_cl.getId_card());
		if (base_cl.getDoc_since() != null) add_everywhere$ap_passport_date_registration.setValue(base_cl.getDoc_since());
		if (base_cl.getIssued_by() != null) add_everywhere$ap_passport_place_registration.setValue(base_cl.getIssued_by());
		if (base_cl.getSurname() != null) add_everywhere$ap_family.setValue(base_cl.getSurname());
		if (base_cl.getF_names() != null) add_everywhere$ap_first_name.setValue(base_cl.getF_names());
		if (base_cl.getM_name() != null) add_everywhere$ap_patronymic.setValue(base_cl.getM_name());
		
		if (base_cl.getB_date() != null) add_everywhere$ap_birthday.setValue(base_cl.getB_date());
		if (base_cl.getSex() != null) add_everywhere$ap_code_gender.setSelecteditem(base_cl.getSex());
		
		if (base_cl.getR_cntry() != null) add_everywhere$acode_country.setSelecteditem((base_cl.getR_cntry().compareTo("UZB") == 0) ? "860" : null);
		
		if (base_cl.getResident() != null) add_everywhere$acode_resident.setSelecteditem(base_cl.getResident());
		if (base_cl.getRmob_phone() != null) add_everywhere$ap_phone_mobile.setValue(base_cl.getRmob_phone());
		if (base_cl.getR_emails() != null) add_everywhere$ap_email_address.setValue(base_cl.getR_emails());
		if (base_cl.getR_phone() != null) add_everywhere$ap_phone_home.setValue(base_cl.getR_phone());
		if (base_cl.getR_street() != null) add_everywhere$ap_post_address.setValue(base_cl.getR_street());
		if (base_cl.getPersone_code() != null) add_everywhere$acode_tel.setValue(base_cl.getPersone_code());
		
	}
	
	public void onSelect$ap_code_adr_region$add_everywhere()
	{
		add_everywhere$ap_code_adr_distr.setModel((new ListModelList(
				com.is.utils.RefDataService.getDistr(add_everywhere$ap_code_adr_region.getValue(), alias))));
	}
	
	public void onSelect$ap_code_birth_region$add_everywhere()
	{
		add_everywhere$ap_code_birth_distr.setModel((new ListModelList(
				com.is.utils.RefDataService.getDistr(add_everywhere$ap_code_birth_region.getValue(), alias))));
	}
	
	public void onClick$btn_cancel$accounts()
	{
		accounts.setVisible(false);
	}
	
	public void onClick$btn_add$accounts()
	{
		/*
		 * TietoCustomer ncr =
		 * com.is.tieto_globuz.customer.CustomerService.getCustomerById
		 * (cur_branch_customer.getId_client(), branch, alias); Res res =
		 * GlobuzAccountService.doAction_create_acc_in_br( un , pwd , "20206" ,
		 * cur_branch_customer.getId_client() , "840" , null ,
		 * (ncr.getName().length() > 80 ? ncr.getName().substring(0, 79) :
		 * ncr.getName()) , 101 , alias , branch , true); if (res != null &&
		 * res.getCode() != 0) { alert("ОШИБКА\nОткрытие счета 20206 :\n" +
		 * res.getName()); return; } else { alert("Cчет открыт :" +
		 * res.getName() + res.getCode()); }
		 *//************************ old ***************************************/
		/*
		 * String tranzacc = res.getName(); accounts$btn_add.setDisabled(true);
		 * GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
		 * acfilter.setClient(cur_branch_customer.getId_client());
		 * acfilter.setAcc_bal("20206"); acfilter.setBranch(branch);
		 * acfilter.setCurrency("840"); com.is.tieto_globuz.tietoAccount.PagingListModel acc_model
		 * = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
		 * accounts$accGrid.setModel((ListModel) acc_model);
		 * accounts.setVisible(true); refreshModel(_starttPageNumber);
		 */
	}
	
	public void onSelect$sproduct$addwnd()
	{
		String cl_id = CustomerService.get_BR_by_tieto(tietocl.getClient(), alias);
		
		List<GlobuzAccount> aModel = CustomerService.get_card_accounts_new_card(cl_id, branch, addwnd$sproduct.getValue(), tietocl.getClient());
		
		addwnd$accGrid.setModel(new ListModelList(aModel));
	}
	
	// !-------------------- Place, where calling Open GlobuzAccount function --------------------
	public void onClick$btn_add$addwnd()
	{
		try
		{
			String res;
			String idOrderAccount = null;
			String clientId;
			String cardCode = addwnd$sproduct.getValue();
			
			if (CheckNull.isEmpty(cardCode))
			{
				alert("Выберите продукт.");
				return;
			}
			
			if(cardCode.equals("004")) {
				alert("Для карт Business счет открыть нельзя.");
				return;
			}
			
			TietoCardSetting tietoCardSettings = TclientService.getTietoCardSetting(cardCode, alias);
			idOrderAccount = tietoCardSettings.getId_order_account();

			

			
			GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
			clientId = CustomerService.get_BR_by_tieto(tietocl.getClient(), alias);
			
			
			
			
			
			
			Customer infoAboutCustomer = new Customer();
			infoAboutCustomer = CustomerService.getInfoAboutCustomer(clientId, alias);
			
			
			res = openAccount(idOrderAccount, 0, tietoCardSettings.getName());
			
			AccApproval accApproval = new AccApproval(
					null,
					res.substring(12),
					infoAboutCustomer.getBranch(),
					1l,
					infoAboutCustomer.getName(),
					(java.sql.Date) infoAboutCustomer.getP_birthday(),
					infoAboutCustomer.getP_passport_serial(),
					infoAboutCustomer.getP_passport_number(),
					infoAboutCustomer.getId_client(),
					tietocl.getClient(),
					"2",
					"2",
					"2",
					2l
					);
			
			
			// confirming
			CustomerService.insertAccConfirm(alias, accApproval);
			
			alert(res);
			
			
			
			acfilter.setClient(clientId);		
			acfilter.setAcc_bal(Constants.ACC_22618);
			acfilter.setBranch(branch);
			acfilter.setCurrency(Constants.CURRENCY_USD);
			if(smart) {
				acfilter.setId_order("444");
			}
			
			com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
			addwnd$accGrid.setModel((ListModel) acc_model);
		}
		catch(Exception e){ alert(CheckNull.getPstr(e)); }
	}
	
	// !-------------------- Open GlobuzAccount --------------------
	public String openAccount(String idOrderAccount, int group, String cardName) throws Exception
	{
		String currency = Constants.CURRENCY_USD;
		String clientId;
		Customer customer = null;
		String customerId;
		String customerName;
		Res idResult;
		
		Res res = new Res();
		
		//try	{			
		
		ISLogger.getLogger().error("\n\nopenAccount\ncur_branch_customer = " + cur_branch_customer.getId_client() + 
				"\ntclient = " + tietocl.getClient_b());
		
			clientId = cur_branch_customer.getId_client();
			customer = CustomerService.getCustomerById(clientId, branch, alias);
			
			customerId = customer.getId_client();
			
			customerName = customer.getName().length() > 80 
					? customer.getName().substring(0, 79) 
					: customer.getName();
					
			if(idOrderAccount.equals("444") && CustomerService.checkIdOrderAcc(alias, branch, customer.getId_client(), "444") ||
					idOrderAccount.equals("901") && CustomerService.checkIdOrderAcc(alias, branch, customer.getId_client(), "901") ||
					idOrderAccount.equals("902") && CustomerService.checkIdOrderAcc(alias, branch, customer.getId_client(), "902")) {
				alert("Лимит такого типа счетов исчерпан.");
			}
			
			
			if(idOrderAccount.equals("444") || idOrderAccount.equals("901") || idOrderAccount.equals("902")) {
				;
			}
			else {
				idOrderAccount = CustomerService.getIdOrderAcc(alias, branch, customer.getId_client()).getName();
			}
			
			
			boolean brcomp = (branch.compareTo(ConnectionPool.getValue("HO", alias)) == 0);
			

			String accName = "VISA " + cardName + " " + customerName;
			accName = accName.length() > 80 ? accName.substring(0, 79) : accName;
			
			res = GlobuzAccountService.doAction_create_acc_in_br(un, pwd, Constants.ACC_22618, customerId, currency, idOrderAccount, accName, group, alias, branch, brcomp);
			
			if (res.getCode() != 0)
			{
				alert("ОШИБКА\nОткрытие счета 22618 :\n" + res.getName());
				throw new Exception(res.getName());//return res.getName();
			}
			else
			{
				String result = res.getName();
				//res.setName("Открыт счет " + result);
				new_card_acc = res.getName();
				return("Открыт счет " + result);
			}
			
			
		//}
		
	}
	
	public void onClick$btn_block$blockwnd()
	{
		String reason = blockwnd$sstopcauses.getValue();
		String reason_text = blockwnd$txtstopcauses.getValue();
		
		Res res = TclientService.block_card(alias, issuingPortProxy, reason, reason_text, blockcard.getCARD());
		
		LtLogger.getLogger().info(res.getName());
		System.out.println(res.getName());
		if (res.getName() != null) alert(res.getName());
		
		tcust.setTieto_customer_id(tietocl.getClient());
		tmpTCust = CustomerService.getTietoCustomer(tcust.getBranch(), tcust.getTieto_customer_id(), alias);
		
		show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart)), false));
		
		blockwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$blockwnd()
	{
		blockwnd.setVisible(false);
	}
	
	public void onClick$btn_cancel$printwnd()
	{
		printwnd.setVisible(false);
	}
	
	public void onClick$btn_visa_cup_app$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("P_IMG1", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/1.jpg");
		params.put("P_IMG2", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/2.jpg");
		params.put("P_IMG3", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/3.jpg");
		params.put("P_IMG4", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/4.jpg");
		
		fill_cl_report(tietocl, "TI_APPLICATION_VISA_CAP_IM", params);
	}
	
	public void onClick$btn_visa_cup_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_CAP", params);
	}
	
	public void onClick$btn_exchange_app$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_APPL_VISA_EXCHANGE", params);
	}
	
	public void onClick$btn_exchange_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_EXCHANGE", params);
	}
	
	public void onClick$btn_u_exchange_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_UPEXCHANGE", params);
	}
	
	public void onClick$btn_au_pt_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}
	
	public void onClick$btn_au_pt_app$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_APPL_VISA_PREMIUM", params);
	}
	
	public void onClick$btn_visa_cup_app$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("P_IMG1", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/1.jpg");
		params.put("P_IMG2", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/2.jpg");
		params.put("P_IMG3", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/3.jpg");
		params.put("P_IMG4", "http://" + session.getLocalAddr() + ":5600/ti/images/card_report_images/4.jpg");
		
		fill_cl_report(tcl, "TI_APPLICATION_VISA_CAP_IM", params);
	}
	
	public void onClick$btn_visa_cup_d$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tcl, "TI_DOGOVOR_VISA_CAP", params);
	}
	
	public void onClick$btn_exchange_app$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tcl, "TI_APPL_VISA_EXCHANGE", params);
	}
	
	public void onClick$btn_exchange_d$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tcl, "TI_DOGOVOR_VISA_EXCHANGE", params);
	}
	
	public void onClick$btn_u_exchange_d$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tcl, "TI_DOGOVOR_VISA_UPEXCHANGE", params);
	}
	
	public void onClick$btn_au_pt_d$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tcl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}
	
	public void onClick$btn_au_pt_app$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tcl, "TI_APPL_VISA_PREMIUM", params);
	}
	
	public void onClick$btn_add_acc_app$add_everywhere()
	{
		Tclient tcl = get_data_from_add_everywhere();
		if (tcl == null) return;
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":5600/ti/images/logo_ipak.jpg");
		
		fill_cl_report(tcl, "TI_APPLICATION1", params);
	}
	
	public void onClick$btn_add_acc_app$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":5600/ti/images/logo_ipak.jpg");
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			
			System.out.println("tietocl.getClient()->" + tietocl.getClient());
			System.out.println("branch->" + branch);
			
			GlobuzAccount acc_20206 = custServ.getAccount(
				custServ.GetCur20206_tclient(tietocl.getClient(), branch, c), alias, branch, c);
			params.put("acc_20206", acc_20206.getId().length() < 1 ? "20206" : acc_20206.getId());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (c != null) c.close();
			}
			catch (Exception e)
			{
			}
		}
		fill_cl_report(tietocl, "TI_APPLICATION1", params);
	}
	
	public void onClick$btn_TI_APPLICATION_AUTOCARD_ACC$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":" +
				// ((Integer) session.getAttribute("port")).toString() +
				"5600" +
				"/ti/images/logo_ipak.jpg");
		
		fill_cl_report(tietocl, "TI_APPLICATION_AUTOCARD_ACC", params);
	}
	
	public void onClick$btn_TI_DOGOVOR_AUTOCARD$show_cards()
	{
		if (tietocl == null)
		{
			alert("Клиент ТИЕТО не выбран");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":" +
				((Integer) session.getAttribute("port")).toString() +
				"/ti/images/logo_ipak.jpg");
		
		fill_cl_report(tietocl, "TI_DOGOVOR_AUTOCARD", params);
	}
	
	private Tclient get_data_from_add_everywhere()
	{
		Tclient rtc = new Tclient();
		
		boolean fl_err = false;
		String err = "";
		
		if ((!((add_everywhere$ap_passport_number.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_number.getValue().length() > 9))
		{
			fl_err = true;
			err += "\nНомер паспорта";
		}
		if ((!((add_everywhere$ap_passport_serial.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_serial.getValue().length() > 9))
		{
			fl_err = true;
			err += "\nСерия паспорта";
		}
		if ((!((add_everywhere$ap_passport_place_registration.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_passport_place_registration.getValue().length() > 200))
		{
			fl_err = true;
			err += "\nМесто регистрации паспорта";
		}
		if ((!((add_everywhere$ap_family.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_family.getValue().length() > 34))
		{
			fl_err = true;
			err += "\nФамилия";
		}
		if ((!((add_everywhere$ap_first_name.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_first_name.getValue().length() > 20))
		{
			fl_err = true;
			err += "\nИмя";
		}
		if ((!((add_everywhere$ap_patronymic.getValue()).matches("[a-zA-Z0-9]*"))) || (add_everywhere$ap_patronymic.getValue().length() > 20))
		{
			fl_err = true;
			err += "\nОтчество";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())))
		{
			fl_err = true;
			err += "\nТип документа";
		}
		if ((!((add_everywhere$ap_number_tax_registration.getValue()).matches("[0-9]*"))) || (add_everywhere$ap_number_tax_registration.getValue().length() > 9))
		{
			fl_err = true;
			err += "\nИНН";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())))
		{
			fl_err = true;
			err += "\nГражданство";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_country.getValue())))
		{
			fl_err = true;
			err += "\nСтрана";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_resident.getValue())))
		{
			fl_err = true;
			err += "\nРезидент";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_registration.getValue())))
		{
			fl_err = true;
			err += "\nДата регистрации паспорта";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())))
		{
			fl_err = true;
			err += "\nДата рождения";
		}
		if ((!((add_everywhere$ap_post_address.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_post_address.getValue().length() > 95))
		{
			fl_err = true;
			err += "\nПочтовый адрес";
		}
		if ((!((add_everywhere$ap_birth_place.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*"))) || (add_everywhere$ap_birth_place.getValue().length() > 200))
		{
			fl_err = true;
			err += "\nМесто рождения";
		}
		
		if (fl_err)
		{
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return null;
		}
		
		rtc.setF_names(add_everywhere$ap_first_name.getValue());
		rtc.setSurname(add_everywhere$ap_family.getValue());
		rtc.setM_name(add_everywhere$ap_patronymic.getValue());
		rtc.setDoc_since(add_everywhere$ap_passport_date_registration.getValue());
		rtc.setB_date(add_everywhere$ap_birthday.getValue());
		rtc.setResident(add_everywhere$acode_resident.getValue());
		rtc.setSex(add_everywhere$ap_code_gender.getValue());
		rtc.setSerial_no(add_everywhere$ap_passport_serial.getValue());
		rtc.setId_card(add_everywhere$ap_passport_number.getValue());
		rtc.setR_city(add_everywhere$ar_city.getValue());
		rtc.setR_street(add_everywhere$ap_post_address.getValue());
		rtc.setR_emails(add_everywhere$ap_email_address.getValue());
		rtc.setRmob_phone(add_everywhere$ap_phone_mobile.getValue());
		rtc.setR_phone(add_everywhere$ap_phone_home.getValue());
		rtc.setR_cntry((add_everywhere$acode_country.getValue().compareTo("860") == 0) ? "UZB" : null);
		rtc.setIssued_by(add_everywhere$ap_passport_place_registration.getValue());
		rtc.setPersone_code(add_everywhere$acode_tel.getValue());
		return rtc;
	}
	
	private void fill_cl_report(Tclient tietocl, String rep_name, HashMap<String, Object> params, CardInfo cardInfos)
	{
		/******************** CLIENT DATA *******************/
		params.put("CLIENT_NAME1", tietocl.getSurname() == null ? "" : tietocl.getSurname());
		params.put("CLIENT_NAME2", tietocl.getF_names() == null ? "" : tietocl.getF_names());
		params.put("CLIENT_NAME3", tietocl.getM_name() == null ? "" : tietocl.getM_name());
		params.put("CLIENT_FULL_NAME", cardInfos.getCARD_String());
		params.put("DOC_SERIAL", tietocl.getSerial_no() == null ? "" : tietocl.getSerial_no());
		params.put("DOC_NUMBER", tietocl.getId_card() == null ? "" : tietocl.getId_card());
		params.put("DOC_DATE_ISSUE", tietocl.getDoc_since() == null ? "" : df.format(tietocl.getDoc_since()));
		params.put("DOC_ISSUE", tietocl.getIssued_by() == null ? "" : tietocl.getIssued_by());
		params.put("CLIENT_BIRTHDAY", tietocl.getB_date() == null ? "" : df.format(tietocl.getB_date()));
		params.put("PHONE_PASSWORD", tietocl.getPersone_code() == null ? "" : tietocl.getPersone_code());
		params.put("POST_ADDRESS", tietocl.getR_street() == null ? "" : tietocl.getR_street());
		params.put("CITIZENSHIP", tietocl.getR_cntry() == null ? "" : TclientService.getCountryNameISO3(tietocl.getR_cntry()));
		params.put("JOB_PLACE", "");
		params.put("WORK_PLACE", "");
		params.put("PHONE_WORK", tietocl.getR_phone() );
		params.put("EMAIL", tietocl.getR_emails() == null ? "" : tietocl.getR_emails());
		params.put("PHONE_NUMBER", tietocl.getR_phone() == null ? "" : tietocl.getR_phone());
		
		String halias = CustomerService.get_alias_ho(alias);
		
		/******************* BRANCH DATA *******************/
		params.put("BRANCH_NAME", com.is.utils.RefDataService.getMfo_name(branch, halias).get(0).getLabel());
		// params.put("BRANCH_ADDRESS",
		// com.is.utils.RefDataService.getMfo_addr(branch,
		// halias).get(0).getLabel());
		params.put("BRANCH", branch);
		params.put("CARD_DEPARTMENT_EXEC", UserService.getUser(uid, branch).getFull_name());
		params.put("BANK_PERSON", "");
		params.put("MICRO_DEPARTMENT_EXEC", "");
		
		/******************** CARD DATA ********************/
		params.put("CARD_VID", "");
		
		params.put("CUR_DATE", df.format(new java.util.Date()));
		
		printwnd$rpframe.setContent(DPrint.getRepPdf(rep_name + ".pdf", application.getRealPath("reports/" + rep_name + ".jasper"), params, alias));
		
		printwnd.setVisible(true);
		// show_cards$accGrid.
	}
	
	private void fill_cl_report(Tclient tietocl, String rep_name, HashMap<String, Object> params)
	{
		/******************** CLIENT DATA *******************/
		params.put("CLIENT_NAME1", tietocl.getSurname() == null ? "" : tietocl.getSurname());
		params.put("CLIENT_NAME2", tietocl.getF_names() == null ? "" : tietocl.getF_names());
		params.put("CLIENT_NAME3", tietocl.getM_name() == null ? "" : tietocl.getM_name());
		params.put("CLIENT_FULL_NAME", params.get("CLIENT_NAME1") + " " + params.get("CLIENT_NAME2") + " " + params.get("CLIENT_NAME3"));
		params.put("DOC_SERIAL", tietocl.getSerial_no() == null ? "" : tietocl.getSerial_no());
		params.put("DOC_NUMBER", tietocl.getId_card() == null ? "" : tietocl.getId_card());
		params.put("DOC_DATE_ISSUE", tietocl.getDoc_since() == null ? "" : df.format(tietocl.getDoc_since()));
		params.put("DOC_ISSUE", tietocl.getIssued_by() == null ? "" : tietocl.getIssued_by());
		params.put("CLIENT_BIRTHDAY", tietocl.getB_date() == null ? "" : df.format(tietocl.getB_date()));
		params.put("PHONE_PASSWORD", tietocl.getPersone_code() == null ? "" : tietocl.getPersone_code());
		params.put("POST_ADDRESS", tietocl.getR_street() == null ? "" : tietocl.getR_street());
		params.put("CITIZENSHIP", tietocl.getR_cntry() == null ? "" : TclientService.getCountryNameISO3(tietocl.getR_cntry()));
		params.put("JOB_PLACE", "");
		params.put("WORK_PLACE", "");
		params.put("PHONE_WORK", tietocl.getR_phone() );
		params.put("EMAIL", tietocl.getR_emails() == null ? "" : tietocl.getR_emails());
		params.put("PHONE_NUMBER", tietocl.getR_phone() == null ? "" : tietocl.getR_phone());
		
		String halias = CustomerService.get_alias_ho(alias);
		
		/******************* BRANCH DATA *******************/
		params.put("BRANCH_NAME", com.is.utils.RefDataService.getMfo_name(branch, halias).get(0).getLabel());
		// params.put("BRANCH_ADDRESS",
		// com.is.utils.RefDataService.getMfo_addr(branch,
		// halias).get(0).getLabel());
		params.put("BRANCH", branch);
		params.put("CARD_DEPARTMENT_EXEC", UserService.getUser(uid, branch).getFull_name());
		params.put("BANK_PERSON", "");
		params.put("MICRO_DEPARTMENT_EXEC", "");
		
		/******************** CARD DATA ********************/
		params.put("CARD_VID", "");
		
		params.put("CUR_DATE", df.format(new java.util.Date()));
		
		printwnd$rpframe.setContent(DPrint.getRepPdf(rep_name + ".pdf", application.getRealPath("reports/" + rep_name + ".jasper"), params, alias));
		
		printwnd.setVisible(true);
		// show_cards$accGrid.
	}
	
	public void onClick$acode_resident$add_everywhere()
	{
		add_everywhere$acode_resident.select();
		// add_everywhere$ap_code_citizenship.setSelecteditem(null);
		// add_everywhere$acode_country.setSelecteditem(null);
	}
	
	public void onClick$ap_code_citizenship$add_everywhere()
	{
		add_everywhere$ap_code_citizenship.select();
		// add_everywhere$acode_resident.setSelecteditem(null);
		// add_everywhere$acode_country.setSelecteditem(null);
	}
	
	public void onClick$acode_country$add_everywhere()
	{
		add_everywhere$acode_country.select();
		// add_everywhere$ap_code_citizenship.setSelecteditem(null);
		// add_everywhere$acode_resident.setSelecteditem(null);
	}
	
	public void onClick$pop_open_cl()
	{
		Res res = CustomerService.doAction_open_closed(un, pwd,
				(String) editPopup.getAttribute("client_id"),
				20,
				(String) editPopup.getAttribute("branch"),
				(String) editPopup.getAttribute("alias"),
				branch);
		if (res.getCode() != 0)
		{
			alert(res.getName());
			return;
		}
		alert("Клиент открыт");
		refreshModel(_starttPageNumber);
	}
	
	public void onOpen$editPopup(OpenEvent event)
		throws Exception
	{
		if (event.isOpen())
		{
			editPopup.setAttribute("branch", (String) event.getReference().getAttribute("branch"));
			editPopup.setAttribute("client_id", (String) event.getReference().getAttribute("client_id"));
			editPopup.setAttribute("alias", (String) event.getReference().getAttribute("alias"));
		}
		
	}
	
	public void onClick$btn_balance_confirmation_without_card_number$bt_rest_confirmation_wnd()
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		fill_account_balance_report("TI_rests", params);
	}
	
	public void onClick$btn_balance_confirmation_with_card_number$bt_rest_confirmation_wnd()
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		fill_account_balance_report("TI_rests_with_card", params);
	}
	
	public void onClick$btn_TI_small$bt_rest_confirmation_wnd()
	{
		HashMap<String, Object> params = new HashMap<String, Object>();
		fill_account_balance_report("TI_small", params);
	}
	
	private void fill_account_balance_report(String rep_name, HashMap<String, Object> params)
	{
		Connection c = null;
		
		try
		{
			c = ConnectionPool.getConnection("IY00444");
			params.put("CARD_ACCOUNT", cfrd.acc.getTranz_acct());
			
			c.prepareCall("alter session set current_schema=" + alias).execute();
			
			GlobuzAccount acc_20206 = custServ.getAccount(
				custServ.GetCur20206(
							cfrd.acc.getTranz_acct().substring(9, 17),
							branch, c), alias, branch, c);
			
			DecimalFormat df = new DecimalFormat();
			
			c.prepareCall("alter session set current_schema=IY00444").execute();
			params.put("ACCOUNT_20206", acc_20206.getId());
			GlobuzAccount card_acc = custServ.getAccount(cfrd.acc.getTranz_acct(), "IY00444", "00444", c);
			BigDecimal usd_balance = BigDecimal.valueOf(card_acc.getS_out());
			usd_balance = usd_balance.divide(BigDecimal.valueOf(100));
			params.put("USD_BALANCE", df.format(usd_balance));
			
			BigDecimal USD_BALANCE_20206 = BigDecimal.valueOf(acc_20206.getS_out());
			USD_BALANCE_20206 = USD_BALANCE_20206.divide(BigDecimal.valueOf(100));
			params.put("USD_BALANCE_20206", df.format(USD_BALANCE_20206));
			
			params.put("USD_BALANCE_STR", custServ.multimoneytostr(
					usd_balance,
					"02",
					"840",
					c));
			params.put("USD_BALANCE_STR_20206", custServ.multimoneytostr(
					USD_BALANCE_20206,
					"02",
					"840",
					c));
			params.put("USD_BALANCE_STR_20206_UZ", custServ.multimoneytostr(
					USD_BALANCE_20206,
					"100",
					"840",
					c));
			
			double rate = custServ.GetCourse("840", "000", c);
			
			BigDecimal SUM_BALANCE = BigDecimal.valueOf(card_acc.getS_out());
			SUM_BALANCE = SUM_BALANCE.multiply(BigDecimal.valueOf(rate)).divide(BigDecimal.valueOf(100));
			
			BigDecimal SUM_BALANCE_20206 = USD_BALANCE_20206;
			SUM_BALANCE_20206 = SUM_BALANCE_20206.multiply(BigDecimal.valueOf(rate)).divide(BigDecimal.valueOf(100));
			
			params.put("SUM_BALANCE", df.format(SUM_BALANCE));
			params.put("SUM_BALANCE_20206", df.format(SUM_BALANCE_20206));
			params.put("SUM_BALANCE_STR", custServ.multimoneytostr(
					SUM_BALANCE,
					"02",
					"000",
					c));
			params.put("SUM_BALANCE_STR_20206", custServ.multimoneytostr(
					SUM_BALANCE_20206,
					"02",
					"000",
					c));
			params.put("RATE", Double.toString(rate));
			// params.put("BANK_MANAGER_NAME",
			// ConnectionPool.getBranchValue("TIETO_BANK_MANAGER_NAME", alias,
			// branch));
			params.put("EMP_NAME", UserService.getUser(uid, branch).getFull_name());
			// params.put("EMP_NAME_TRANS", UserService.getUser(uid,
			// branch).getTrans_name());
			params.put("CARD_NUMBER", cfrd.acc.getCard().substring(0, 4) + "********" + cfrd.acc.getCard().substring(12));
			params.put("MONTH_UZ", get_uzbek_month(new Date()));
			params.put("YEAR_LAST_DIGIT", new SimpleDateFormat("yyyy").format(new Date()).substring(3));
			params.put("DAY_IN_MONTH", new SimpleDateFormat("dd").format(new Date()));
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (c != null) c.close();
			}
			catch (Exception e)
			{
			}
		}
		fill_cl_report(tietocl, rep_name, params);
	}
	
	public static String get_uzbek_month(Date date)
	{
		String monthString = "";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		
		switch (month)
		{
			case 0:
				monthString = "yanvar";
			break;
			case 1:
				monthString = "fevral";
			break;
			case 2:
				monthString = "mart";
			break;
			case 3:
				monthString = "aprel";
			break;
			case 4:
				monthString = "may";
			break;
			case 5:
				monthString = "iyun";
			break;
			case 6:
				monthString = "iyul";
			break;
			case 7:
				monthString = "avgust";
			break;
			case 8:
				monthString = "sentabr";
			break;
			case 9:
				monthString = "oktabr";
			break;
			case 10:
				monthString = "noyabr";
			break;
			case 11:
				monthString = "dekabr";
			break;
			default:
				monthString = "Invalid month";
			break;
		}
		
		return monthString;
	}
	
	public String getCur_cl_acc()
	{
		return cur_cl_acc;
	}
	
	public void setCur_cl_acc(String cur_cl_acc)
	{
		this.cur_cl_acc = cur_cl_acc;
	}
	
	public Customer getCur_HO_customer()
	{
		return cur_HO_customer;
	}
	
	public void setCur_HO_customer(Customer cur_HO_customer)
	{
		this.cur_HO_customer = cur_HO_customer;
	}
	
	public Customer getCur_branch_customer()
	{
		return cur_branch_customer;
	}
	
	public void setCur_branch_customer(Customer cur_branch_customer)
	{
		this.cur_branch_customer = cur_branch_customer;
	}
	
	private static capitalBank.IssuingWS.IssuingPortProxy initNp(capitalBank.IssuingWS.IssuingPortProxy issuingPortProxy, String alias)
	{
		if (np == null)
		{
			np = new NilProvider();
			np.init();
		}
		
		
		return issuingPortProxy = new capitalBank.IssuingWS.IssuingPortProxy(
			ConnectionPool.getValue("EMPC_TIETO_HOST", alias),
			ConnectionPool.getValue("EMPC_TIETO_HOST_USERNAME", alias),
			ConnectionPool.getValue("EMPC_TIETO_HOST_PASSWORD", alias)
			);

		
	}
	
	HostnameVerifier allHostsValid = new HostnameVerifier()
	{
		@Override
		public boolean verify(String arg0, SSLSession arg1)
		{
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	public static String getFnm(GlobuzAccount pAccInfo)
	{
		String full_name = "";
		CharSequence ch = " null";
		CharSequence newch = " ";
		full_name = pAccInfo.getName();
		if (full_name.contains(ch))
		{
			full_name = full_name.replace(ch, newch);
		}
		return full_name;
		
	}
	
	public List<CardInfo> makeList(List<AccInfo> list)
	{
		List<CardInfo> cardInfos = new ArrayList<CardInfo>();
		List<CardInfo> cards = new ArrayList<CardInfo>();
		
		for (int i = 0; i < list.size(); i++)
		{
			cardInfos = list.get(i).getCardlist();
			for (int j = 0; j < cardInfos.size(); j ++)
			{
				cards.add(cardInfos.get(j));
			}
		}
		
		return cards;
	}
	
	
	public void onClick$openCardButton$openCardWindow() {
		String cardCode = null;
		String userChoice;
		String newCardAccount;
		List<AccInfo> accInfoList = null;
		boolean cardExists = false;
		boolean electron = false, classic = false, gold = false, platinum = false, infinity = false;
		
		newCardAccount = openCardGlobuzAccount.getId();
		userChoice = openCardWindow$products.getValue();


		if(userChoice.equals("005") && (!newCardAccount.substring(17).equals("444")) ) {
			alert("Smart Money можно открыть только на счет %444.");
			openCardWindow.setVisible(false);
			return;
		}
		else if(userChoice.equals("008") && (!(newCardAccount.substring(17).equals("901") || newCardAccount.substring(17).equals("902")))) {
			alert("Travel можно открыть только на счет %901 или %902.");
			openCardWindow.setVisible(false);
			return;
		}
		else if(userChoice.equals("004") && (!newCardAccount.substring(0, 8).equals("22620840"))) {
			alert("Business можно открыть только на счет 22620840%.");
			openCardWindow.setVisible(false);
			return;			
		}
		else{		
		
				accInfoList = TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart);
				for (int i = 0; i < accInfoList.size(); i++) {
					
					if(newCardAccount.equals(accInfoList.get(i).getCard_acct())) {
					
						for (int j = 0; j < accInfoList.get(i).getCardlist().size(); j++) {
							
							CardInfo cardInfo = accInfoList.get(i).getCardlist().get(j);
							
							if (cardInfo.getBank_account_status().equals(Constants.BANK_ACCOUNT_STATUS)) {
								
								cardExists = true;
								if(cardInfo.getCOND_SET().equals("004")) {
									electron = true;
								}
								if(cardInfo.getCOND_SET().equals("001")) {
									classic = true;
								}
								if(cardInfo.getCOND_SET().equals("002")) {
									gold = true;
								}
								if(cardInfo.getCOND_SET().equals("013")) {
									platinum = true;
								}
								if(cardInfo.getCOND_SET().equals("012")) {
									infinity = true;
								}

								break;
							}
						}
					}
				}
				
				if(cardExists == false || infinity) {
					;
				}
				else if(platinum && userChoice.equals("007")) {
					alert("На выбранный счет карты Infinity открыть нельзя. Выберите другой тип карты.");
					return;
				}
				else if(gold && (userChoice.equals("007") || userChoice.equals("006"))) {
					alert("На выбранный счет карты Platinum, Infinity открыть нельзя. Выберите другой тип карты.");
					return;
				}
				else if(classic && (userChoice.equals("007") || userChoice.equals("006") || userChoice.equals("003"))) {
					alert("На выбранный счет карты Platinum, Infinity, Gold открыть нельзя. Выберите другой тип карты.");
					return;
				}
				else if(electron && (userChoice.equals("007") || userChoice.equals("006") || userChoice.equals("003") || userChoice.equals("002"))) {
					alert("На выбранный счет карты Platinum, Infinity, Gold, Classic открыть нельзя. Выберите другой тип карты.");
					return;
				}
		}
		
		cardCode = userChoice;
		
		Res res = CustomerService.update_lnk_set_acc(un, pwd, branch, cur_branch_customer.getId_client() , openCardGlobuzAccount, alias, issuingPortProxy);
			
		if (res.getCode() == 1) {									
												
			openCard(cardCode, openCardGlobuzAccount.getId(), null);
		}
		else {
			alert(res.getName());
			return;
		}
		
		openCardWindow.setVisible(false);
		refreshModel(_startPageNumber);
		accounts.setVisible(false);
	}
	
	public void onClick$cancelButton$openCardWindow() {
		openCardWindow.setVisible(false);
	}
	
	
	
	
	
	
	public void onClick$openCardButton$openAddCardWindow() {
		String cardCode = null;
		String userChoice;
		String newCardAccount;
		List<AccInfo> accInfoList = null;
		boolean cardExists = false;
		boolean electron = false, classic = false, gold = false, platinum = false, infinity = false;
		String holderName = null;
		
		holderName = openAddCardWindow$holderName.getValue();
		newCardAccount = openCardGlobuzAccount.getId();
		userChoice = openAddCardWindow$products.getValue();

		if(holderName == null || holderName.equals("")) {
			alert("Введите ФИО держателя карты.");
			return;
		}
		else if(holderName.length() > 21) {
			alert("ФИО держателя карты не может превышать длину 22 символа.");
			return;
		}

		if(userChoice.equals("005") && (!newCardAccount.substring(17).equals("444")) ) {
			alert("Smart Money можно открыть только на счет %444.");
			openAddCardWindow.setVisible(false);
			return;
		}
		else if(userChoice.equals("008") && (!(newCardAccount.substring(17).equals("901") || newCardAccount.substring(17).equals("902")))) {
			alert("Travel можно открыть только на счет %901 или %902.");
			openAddCardWindow.setVisible(false);
			return;
		}
		else if(userChoice.equals("004") && (!newCardAccount.substring(0, 8).equals("22620840"))) {
			alert("Business можно открыть только на счет 22620840%.");
			openAddCardWindow.setVisible(false);
			return;			
		}
		else{		
		
				accInfoList = TclientService.getAccInfo(tietocl, alias, issuingPortProxy, smart);
				for (int i = 0; i < accInfoList.size(); i++) {
					
					if(newCardAccount.equals(accInfoList.get(i).getCard_acct())) {
					
						for (int j = 0; j < accInfoList.get(i).getCardlist().size(); j++) {
							
							CardInfo cardInfo = accInfoList.get(i).getCardlist().get(j);
							
							if (cardInfo.getBank_account_status().equals(Constants.BANK_ACCOUNT_STATUS)) {
								
								cardExists = true;
								if(cardInfo.getCOND_SET().equals("004")) {
									electron = true;
								}
								if(cardInfo.getCOND_SET().equals("001")) {
									classic = true;
								}
								if(cardInfo.getCOND_SET().equals("002")) {
									gold = true;
								}
								if(cardInfo.getCOND_SET().equals("013")) {
									platinum = true;
								}
								if(cardInfo.getCOND_SET().equals("012")) {
									infinity = true;
								}

								break;
							}
						}
					}
				}
				
				if(cardExists == false) {
					alert("Доп. карту при отсутствии основной карты открыть нельзя.");
					openAddCardWindow.setVisible(false);
					return;		
				}
				if(infinity) {
					;
				}
				else if(platinum && userChoice.equals("007")) {
					alert("На выбранный счет карты Infinity открыть нельзя. Выберите другой тип карты.");
					return;
				}
				else if(gold && (userChoice.equals("007") || userChoice.equals("006"))) {
					alert("На выбранный счет карты Platinum, Infinity открыть нельзя. Выберите другой тип карты.");
					return;
				}
				else if(classic && (userChoice.equals("007") || userChoice.equals("006") || userChoice.equals("003"))) {
					alert("На выбранный счет карты Platinum, Infinity, Gold открыть нельзя. Выберите другой тип карты.");
					return;
				}
				else if(electron && (userChoice.equals("007") || userChoice.equals("006") || userChoice.equals("003") || userChoice.equals("002"))) {
					alert("На выбранный счет карты Platinum, Infinity, Gold, Classic открыть нельзя. Выберите другой тип карты.");
					return;
				}
		}
		
		cardCode = userChoice;
		
		Res res = CustomerService.update_lnk_set_acc(un, pwd, branch, cur_branch_customer.getId_client() , openCardGlobuzAccount, alias, issuingPortProxy);
			
		if (res.getCode() == 1) {									
												
			openCard(cardCode, openCardGlobuzAccount.getId(), holderName);
		}
		else {
			alert(res.getName());
			return;
		}
		
		openAddCardWindow.setVisible(false);
		refreshModel(_startPageNumber);
		accounts.setVisible(false);
	}
	
	public void onClick$cancelButton$openAddCardWindow() {
		openAddCardWindow.setVisible(false);
	}
	
	
	
	public void onClick$createLinkBtn$createLinkWnd() {
		
		CardCustomerSearch info = new CardCustomerSearch();
		
		String first, last;
		first = "427833";
		last = createLinkWnd$lastFour.getValue();
		String cardNumber = "";
		
		if(first.equals("") || last.equals("")) {
			alert("Зполните все поля.");
			return;
		}
			
		cardNumber = first + "%" + last;
		info = TclientService.retrieveFunction(issuingPortProxy, cardNumber);	
		
		if(info.getTietoClientId() == null) {
			alert("Клиент с такой картой в ТИЕТО не найден.");
			return;
		}
		
		CustomerService.create_lnk(branch, null, cur_branch_customer.getId_client(), info.getTietoClientId(), null, alias, info.getCardAcct(), info.getCardPan());
		
		alert("Связка создана.");
		
		createLinkWnd.setVisible(false);
		refreshModel(_startPageNumber);
	}
	
	public void onClick$cancelBtn$createLinkWnd() {
		createLinkWnd.setVisible(false);
	}
	
	
	private void transliteration(String client) {
		
		Customer customer = new Customer();
		
		CheckNull.clearForm(add_everywhere$addgrdl);
		CheckNull.clearForm(add_everywhere$addgrdr);
		
		customer = CustomerService.getInfoAboutCustomer(client, alias);
		
		
//		add_everywhere$ap_family.setValue(CustomerService.transliterate(customer.getP_family()));
//		add_everywhere$ap_first_name.setValue(CustomerService.transliterate(customer.getP_first_name()));
		add_everywhere$ap_patronymic.setValue(CustomerService.transliterate(customer.getP_patronymic()));
		add_everywhere$ap_type_document.setSelecteditem(customer.getP_type_document());
		add_everywhere$ap_passport_serial.setValue(customer.getP_passport_serial());
		add_everywhere$ap_passport_number.setValue(customer.getP_passport_number());
		add_everywhere$ap_passport_date_registration.setValue(customer.getP_passport_date_registration());
		add_everywhere$ap_passport_date_expiration.setValue(customer.getP_passport_date_expiration());
		add_everywhere$ap_passport_place_registration.setValue(CustomerService.transliterate(customer.getP_passport_place_registration()));
		add_everywhere$ap_birthday.setValue(customer.getP_birthday());
		add_everywhere$ap_code_birth_region.setSelecteditem(customer.getP_code_birth_region());
		add_everywhere$ap_code_birth_distr.setSelecteditem(customer.getP_code_birth_distr());
		add_everywhere$ap_birth_place.setValue(customer.getP_birth_place());
		add_everywhere$ap_code_gender.setSelecteditem(customer.getP_code_gender());

		add_everywhere$ap_code_citizenship.setSelecteditem("860");
		add_everywhere$acode_resident.setSelecteditem(customer.getCode_resident());
		add_everywhere$acode_country.setSelecteditem(customer.getCode_country());
		add_everywhere$ap_code_adr_region.setSelecteditem(customer.getP_code_adr_region());
		add_everywhere$ap_code_adr_distr.setSelecteditem(customer.getP_code_adr_distr());
		add_everywhere$ap_phone_mobile.setValue(customer.getP_phone_mobile());
		add_everywhere$ap_email_address.setValue(customer.getP_email_address());
		add_everywhere$ap_phone_home.setValue(customer.getP_phone_home());
		add_everywhere$acode_tel.setValue(customer.getAcode_tel());
		add_everywhere$ar_city.setSelecteditem(customer.getR_CITY());
		add_everywhere$ap_post_address.setValue(CustomerService.transliterate(customer.getP_post_address()));
		add_everywhere$ap_code_tax_org.setSelecteditem(customer.getP_code_tax_org());
		add_everywhere$ap_number_tax_registration.setValue(customer.getP_number_tax_registration());


		
	}
	
	
	
	
//	public void printReport(String tietoAccount) {
//		TietoCustomer customerInfo = CustomerService.getInfoAboutCustomer(tietocl.getClient_b(), alias);
//
//		ReportFields fields = new ReportFields();
//		
//		
//		fields.setMfo(branch);
//		fields.setFio(customerInfo.getName() == null ? "" : customerInfo.getName());
//		fields.setAccount(tietoAccount == null ? "" : tietoAccount);
//		fields.setAddress(customerInfo.getP_post_address() == null ? "" : customerInfo.getP_post_address());
//		fields.setHome_phone(tietocl.getR_phone() == null ? "" : tietocl.getR_phone());
//		fields.setE_mail(tietocl.getR_emails() == null ? "" : tietocl.getR_emails());
//		fields.setMobile_phone(tietocl.getRmob_phone() == null ? "" : tietocl.getRmob_phone());
//		
//		String passportSerialNumber = customerInfo.getP_passport_serial() + customerInfo.getP_passport_number();
//		
//		fields.setPassport_serial(passportSerialNumber == null ? "" : passportSerialNumber);
//		fields.setPassport_from(customerInfo.getP_passport_place_registration() == null ? "" : customerInfo.getP_passport_place_registration());
//		fields.setPassport_date(customerInfo.getP_passport_date_registration() == null ? "" : df.format(customerInfo.getP_passport_date_registration()));
//		fields.setCustomer_birthday(customerInfo.getP_birthday() == null ? "" : df.format(customerInfo.getP_birthday()));
//		
//		Reports report = new Reports();
//		
//		String fileName = tietoAccount;
//		fileName += ".docx";
//		report.getRepotClient("C:\\TietoTemplates\\Form.docx", fileName, fields);
//	}
	
	
	public void onClick$btnReport$addwnd() {
		Customer customerInfo = CustomerService.getInfoAboutCustomer(tietocl.getClient_b(), alias);

		ReportFields fields = new ReportFields();
		
		
		fields.setMfo(branch);
		fields.setFio(customerInfo.getName() == null ? "" : customerInfo.getName());
		fields.setAddress(customerInfo.getP_post_address() == null ? "" : customerInfo.getP_post_address());
		fields.setHome_phone(tietocl.getR_phone() == null ? "" : tietocl.getR_phone());
		fields.setE_mail(tietocl.getR_emails() == null ? "" : tietocl.getR_emails());
		fields.setMobile_phone(tietocl.getRmob_phone() == null ? "" : tietocl.getRmob_phone());
		
		fields.setAccount("");
		
		String passportSerialNumber = customerInfo.getP_passport_serial() + customerInfo.getP_passport_number();
		
		fields.setPassport_serial(passportSerialNumber == null ? "" : passportSerialNumber);
		fields.setPassport_from(customerInfo.getP_passport_place_registration() == null ? "" : customerInfo.getP_passport_place_registration());
		fields.setPassport_date(customerInfo.getP_passport_date_registration() == null ? "" : df.format(customerInfo.getP_passport_date_registration()));
		fields.setCustomer_birthday(customerInfo.getP_birthday() == null ? "" : df.format(customerInfo.getP_birthday()));
		
		Reports report = new Reports();
		
		String fileName = "Visa";
		fileName += ".docx";
		report.getRepotClient("C:\\TietoTemplates\\Form.docx", fileName, fields);
	}
}
