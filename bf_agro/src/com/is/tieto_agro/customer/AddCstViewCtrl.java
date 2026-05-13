package com.is.tieto_agro.customer;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.rmi.RemoteException;
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
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.tieto_globuz.tietoAccount.GlobuzAccountService.actions_for_acc;
import com.is.report.DPrint;
import com.is.tieto_agro.customer.CustomerService.link;
import com.is.tieto_agro.tieto.AccInfo;
import com.is.tieto_agro.tieto.Agreement;
import com.is.tieto_agro.tieto.CardInfo;
import com.is.tieto_agro.tieto.Tclient;
import com.is.tieto_agro.tieto.TclientFilter;
import com.is.tieto_agro.tieto.TclientService;
import com.is.tieto_agro.tieto.TietoCardSetting;
import com.is.user.UserService;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

import agroBank.issuing_v_01_02_xsd.KeyType_Agreement;
import agroBank.issuing_v_01_02_xsd.ListType_AccountInfo;
import agroBank.issuing_v_01_02_xsd.OperationConnectionInfo;
import agroBank.issuing_v_01_02_xsd.OperationResponseInfo;
import agroBank.issuing_v_01_02_xsd.RowType_AccountInfo;
import agroBank.issuing_v_01_02_xsd.RowType_AccountInfo_Additional;
import agroBank.issuing_v_01_02_xsd.RowType_AccountInfo_Base;
import agroBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request;
import agroBank.issuing_v_01_02_xsd.RowType_Agreement;
import agroBank.issuing_v_01_02_xsd.RowType_CardInfo;
import agroBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data;
import agroBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard;
import agroBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard;
import agroBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request;
import agroBank.issuing_v_01_02_xsd.RowType_Customer;
import agroBank.issuing_v_01_02_xsd.RowType_CustomerCustomInfo;
import agroBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request;
import agroBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request;
import agroBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request;
import agroBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;
import agroBank.issuing_v_01_02_xsd.RowType_ReplaceCard;
import agroBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder;
import agroBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder;
import agroBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder;
import agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import agroBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder;
import agroBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder;
import agroBank.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder;

public class AddCstViewCtrl extends GenericForwardComposer
{
//	private static NilProvider np = null;
	public static Window customermain;
	private Window addCust, addTieto, printwnd, accwnd, addCustomer,
		accounts, add_everywhere, addwnd, show_cards, blockwnd, bt_rest_confirmation_wnd;
	public CustomerFilter filter = new CustomerFilter();
	public CustomerFilter bfilter = new CustomerFilter();
	private Paging bankdataPaging;
	private com.is.tieto_agro.customer.PagingListModel bmodel = null;
	private com.is.tieto_agro.tieto.PagingListModel tmodel = null;
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
	private String curip, contract_nmb;
	private int uid;
	
	private static AccInfo cur_card = new AccInfo();
	private CardInfo blockcard = new CardInfo();
	private static HashMap<Integer, Integer> used_ids;
	
	private int agre_nom_upd;
	private AccInfo cur_acc_info;
	private String cur_contract;
	private ConfirmationRepData cfrd;
	
	private agroBank.IssuingWS.IssuingPortProxy issuingPortProxy;
	private BigDecimal agre_nom;
	private String Acc_22618 = "22618";
	private HashMap<String, String> hashMapCurr = new HashMap<String, String>();
	private int _startpageAgro = 0;
	private Textbox blockwnd$card;
	private String VISA_BANK_C, VISA_GROUPC, EMPC_BINCOD;
	
	private RefCBox productChoiceWindow$productChoice;
	private Toolbarbutton productChoiceWindow$productChoiceButton;
	private Toolbarbutton productChoiceWindow$cancelButton;
	private Window productChoiceWindow;
	private GlobuzAccount accProductChoice;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception, ConnectException, SQLException
	{
		super.doAfterCompose(comp);
		binder = new AnnotateDataBinder(comp);
		self.setAttribute("binder", binder);
		binder.bindBean("tietocl", this.tietocl);
		binder.loadAll();
		
		String[] parameter = (String[]) param.get("ht");
		if (parameter != null)
		{
			_pageSize = Integer.parseInt(parameter[0]) / 60;
			branch_customers.setRows(_pageSize);
			bank_customers.setRows(_pageSize);
		}
		
		uid = (Integer) session.getAttribute("uid");
		curip = (String) session.getAttribute("curip");
		
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		
		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		
		VISA_BANK_C = ConnectionPool.getValue("VISA_BANK_C", alias);
		VISA_GROUPC = ConnectionPool.getValue("VISA_GROUPC", alias);
		EMPC_BINCOD = TclientService.getEmpcBincodFromDB();
			
		
		
		tcust.setBranch(branch);
		contract_nmb = null;
		_tstopCauses = TclientService.getHTstopCauses(alias);
		hashMapCurr = CustomerService.getCurr(alias);
		
		add_everywhere$ap_type_document.setModel((new ListModelList(CustomerService.getType_document(alias))));
		add_everywhere$ap_code_citizenship.setModel((new ListModelList(CustomerService.getCountry(alias))));
		add_everywhere$acode_country.setModel((new ListModelList(CustomerService.getCountry(alias))));
		
		add_everywhere$ap_code_gender.setModel((new ListModelList(com.is.utils.RefDataService.getGender(alias))));
		add_everywhere$ap_code_nation.setModel((new ListModelList(com.is.utils.RefDataService.getNation(alias))));
		add_everywhere$ap_code_adr_distr.setModel((new ListModelList(CustomerService.getDistr(alias))));
		add_everywhere$acode_resident.setModel((new ListModelList(com.is.utils.RefDataService.getRezCl(alias))));
		addwnd$sproduct.setModel((new ListModelList(CustomerService.getProducts(alias, "P"))));
		
		add_everywhere$ap_code_adr_region.setModel((new ListModelList(CustomerService.getRegion(alias))));
		add_everywhere$ar_city.setModel((new ListModelList(CustomerService.getRegion(alias))));
		add_everywhere$ap_code_birth_distr.setModel((new ListModelList(CustomerService.getDistr(alias))));
		add_everywhere$ap_code_birth_region.setModel((new ListModelList(CustomerService.getRegion(alias))));
		add_everywhere$ap_code_tax_org.setModel((new ListModelList(CustomerService.getTax(alias))));
		
		productChoiceWindow$productChoice.setModel((new ListModelList(CustomerService.getProducts(alias, "P"))));
		
		try
		{
			issuingPortProxy = initNp(issuingPortProxy, alias);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			Messagebox.show("[Coonection Problem]=>" + e.getLocalizedMessage(),
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
							else return;
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
							acfilter.setAcc_bal(Acc_22618);
							acfilter.setBranch(branch);
							acfilter.setCurrency("840");
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
						bt_acc_insert.setLabel("���������� ����");
						bt_acc_insert.setAttribute("acc", pAccInfo);
						bt_acc_insert.setImage("/images/credit_card1.png");
						bt_acc_insert.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(Event event) throws Exception
							{
								GlobuzAccount acc = (GlobuzAccount) event.getTarget().getAttribute("acc");
								Res res = CustomerService.update_lnk_set_acc(un, pwd, branch, cur_branch_customer.getId_client() , acc, alias, issuingPortProxy);
								if (res.getCode() != 1)
								{
									alert(res.getName());
									return;
								}
								refreshModel(_startPageNumber);
								accounts.setVisible(false);
							}
							
						});
						h_add_acc.appendChild(bt_acc_insert);
					}
					
					Listcell h_add_acc_cell = new Listcell();
					if (pAccInfo.getState() == 2)
					{
						bt_acc_act = new Toolbarbutton();
						bt_acc_act.setLabel("������� �����");
						bt_acc_act.setAttribute("acc", pAccInfo);
						bt_acc_act.setImage("/images/credit_card1.png");
						bt_acc_act.addEventListener(Events.ON_CLICK, new EventListener()
						{
							@Override
							public void onEvent(Event event) throws Exception
							{
								
								accProductChoice = (GlobuzAccount) event.getTarget().getAttribute("acc");
								
//								GlobuzAccount acc = (GlobuzAccount) event.getTarget().getAttribute("acc");
//								
//								Res res = CustomerService.update_lnk_set_acc(un, pwd, branch, cur_branch_customer.getId_client() , acc, alias, issuingPortProxy);
//								
//								if (res.getCode() == 1)
//								{
//									String cl_n = "";
//									Customer lg_c = CustomerService.getCustomerById(cur_branch_customer.getId_client(), branch, alias);
//									
//									if (lg_c.getName() != null)
//									{
//										cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
//									}
//									
//									Res resAgreement = new Res();
//									
//									resAgreement = TclientService.addNewAgreement(lg_c, acc, tietocl, alias, issuingPortProxy, branch);
//									alert(resAgreement.getName());
//									if (resAgreement.getCode() != 1)
//									{
//										return;
//									}
//									
//								}
//								else
//								{
//									alert(res.getName());
//									return;
//								}
//								
//								// empcadd
//								
//								//
//								
//								refreshModel(_startPageNumber);
//								accounts.setVisible(false);
								
								productChoiceWindow.setVisible(true);
							}
							
						});
						h_add_acc_cell.appendChild(bt_acc_act);
					}
					
					row.setValue(pAccInfo);
					row.appendChild(new Listcell(pAccInfo.getBranch()));
					row.appendChild(new Listcell(pAccInfo.getClient()));
					row.appendChild(new Listcell(pAccInfo.getId()));
					row.appendChild(new Listcell(getFnm(pAccInfo)));
					row.appendChild(new Listcell(pAccInfo.getCurrency()));
					row.appendChild(new Listcell(df.format(pAccInfo.getDate_open())));
					row.appendChild(new Listcell(pAccInfo.getId_order()));
					row.appendChild(new Listcell(GlobuzAccountService.get_account_state_caption(pAccInfo.getState(), alias)));
					row.appendChild(h_add_acc);
					row.appendChild(h_add_acc_cell);
				}
		});
		
		show_cards$accGrid.setItemRenderer(new ListitemRenderer()
		{
			@SuppressWarnings("unchecked")
			public void render(final Listitem row, Object data) throws Exception
			{
				CardInfo cardInfo = (CardInfo) data;
				row.setValue(cardInfo);
				
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
				
				btunblock_card.setWidth("100%");
				btblock_card.setWidth("100%");
				btrefresh_card.setWidth("100%");
				btrefresh_card_app.setWidth("100%");
				bt_block_card_acc.setWidth("100%");
				bt_close_card_acc.setWidth("100%");
				bt_unblock_card_acc.setWidth("100%");
				bt_refresh_with_new.setWidth("100%");
				bt_rest_confirmation.setWidth("100%");
				
				btunblock_card.setImage("/images/+.png");
				btblock_card.setImage("/images/-.png");
				btunblock_card.setLabel("�������������� �����");
				btblock_card.setLabel("����������� �����");
				bt_rest_confirmation.setLabel("��������� � �����");
				bt_block_card_acc.setLabel("��������� � ����������");
				bt_block_card_acc.setImage("/images/+.png");
				bt_close_card_acc.setImage("/images/+.png");
				bt_close_card_acc.setLabel("�������");
				bt_unblock_card_acc.setLabel("��������� � ��������");
				bt_unblock_card_acc.setImage("/images/+.png");
				
				btrefresh_card.setImage("/images/front1.png");
				btrefresh_card.setLabel("������������� �����");
				btrefresh_card_app.setImage("/images/file.png");
				bt_rest_confirmation.setImage("/images/file.png");
				btrefresh_card_app.setLabel("��������� �� ���������� �����");
				bt_refresh_with_new.setImage("/images/front1.png");
				bt_refresh_with_new.setLabel("������������� ����� � ������ �������");
				
				btblock_card.setAttribute("card", cardInfo);
				btunblock_card.setAttribute("card", cardInfo);
				
				btrefresh_card.setAttribute("acc", cardInfo);
				btrefresh_card_app.setAttribute("acc", cardInfo);
				bt_block_card_acc.setAttribute("card", cardInfo);
				bt_close_card_acc.setAttribute("card", cardInfo);
				bt_unblock_card_acc.setAttribute("card", cardInfo);
				bt_refresh_with_new.setAttribute("acc", cardInfo);
				bt_rest_confirmation.setAttribute("acc", cardInfo);
				
				btblock_card.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event) throws Exception
							{
								blockwnd$sstopcauses.setModel(new ListModelList(TclientService.getTstopCauses(alias)));
								blockwnd.setVisible(true);
								blockcard = (CardInfo) event.getTarget().getAttribute("card");
								blockwnd$card.setValue("����� " + blockcard.getCARD());
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
							connectionInfo.setBANK_C(VISA_BANK_C);
							connectionInfo.setGROUPC(VISA_GROUPC);
							
							parameters.setBANK_C(VISA_BANK_C);
							parameters.setGROUPC(VISA_GROUPC);
							parameters.setTEXT("Card UnBlocked");
							
							blockcard = (CardInfo) event.getTarget().getAttribute("card");
							
							parameters.setCARD(blockcard.getCARD());
							//parameters.setSTOP_CAUSE("0");
							
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
							tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
							show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
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
									alert("������ ����� �� ������");
									return;
								}
								
								HashMap<String, Object> params = new HashMap<String, Object>();
								params.put("CARD_NUM", ((CardInfo) event.getTarget().getAttribute("acc")).getCARD());
								params.put("CARD_PROD_NAME", "����� �����");
								
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
									alert("������ ����� �� ������");
									return;
								}
								
								Tclient ntc;
								ntc = tietocl;
								
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(((AccInfo) event.getTarget().getAttribute("acc")).getCard_type()), alias);
								
								Res res = com.is.tieto_agro.tieto.TclientService.check_card(((AccInfo) event.getTarget().getAttribute("acc")).getCard_type(), TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
							{
								alert(res.getName());
								return;
							}
							
							contract_nmb = null;
							agre_nom_upd = Integer.parseInt(String.valueOf(((AccInfo) event.getTarget().getAttribute("acc")).getAgreement_key().toBigInteger()));
							String RT = null;
							edit_agree = true;
							// cur_contract =
							// ((AccInfo)event.getTarget().getAttribute("acc")).getContract();
							cur_acc_info = (AccInfo) event.getTarget().getAttribute("acc");
							contract_nmb = ((AccInfo) event.getTarget().getAttribute("acc")).getContract();
							open_card(((AccInfo) event.getTarget().getAttribute("acc")).getCard_type(),
									((AccInfo) event.getTarget().getAttribute("acc")).getCard_acct(),
									true,
									((AccInfo) event.getTarget().getAttribute("acc")).getTranz_acct());
							contract_nmb = null;
							
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
									connectionInfo.setBANK_C(VISA_BANK_C);
									connectionInfo.setGROUPC(VISA_GROUPC);
									
									dac.setCARD(((CardInfo) event.getTarget().getAttribute("card")).getCARD());
									dac.setDORMANT_MODE("0");
									dac.setINSTL_INTER(BigDecimal.valueOf(0));
									dac.setBANK_C(VISA_BANK_C);
									dac.setGROUPC(VISA_GROUPC);
									dac.setTEXT("[" + df.format(new Date()) + "]Blk by " + branch);
									//dac.setTEXT("Blocked");
									
									OperationResponseInfo resp = issuingPortProxy.dormantAccountByCard(connectionInfo, dac);
									
									if (resp.getResponse_code().intValue() != 0)
									{
										alert(resp.getError_action()  + " \n\r " + resp.getError_description());
										return;
									}
									
									alert("������ ����� ������� �� ����������");
									
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
									tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
									
									show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
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
									connectionInfo.setBANK_C(VISA_BANK_C);
									connectionInfo.setGROUPC(VISA_GROUPC);
									
									dac.setCARD(((CardInfo) event.getTarget().getAttribute("card")).getCARD());
									dac.setTEXT("[" + df.format(new Date()) + "]UnBlk by " + branch);
									dac.setBANK_C(VISA_BANK_C);
									dac.setGROUPC(VISA_GROUPC);
									OperationResponseInfo resp = issuingPortProxy.activateAccountByCard(connectionInfo, dac);
									
									if (resp.getResponse_code().intValue() != 0)
									{
										alert(resp.getError_action()  + " \n\r " + resp.getError_description());
										return;
									}
									
									alert("���� �����������");
									
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
									tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
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
									connectionInfo.setBANK_C(VISA_BANK_C);
									connectionInfo.setGROUPC(VISA_GROUPC);
									
									dac.setCARD_ACCT(((CardInfo) event.getTarget().getAttribute("card")).getCARD_ACCT());
									dac.setCCY(((CardInfo) event.getTarget().getAttribute("card")).getBank_account_Ccy());
									dac.setCOMMENT("[" + df.format(new Date()) + "]Closed by " + branch);
									
									OperationResponseInfo resp = issuingPortProxy.closeAccount(connectionInfo, dac);
									
									if (resp.getResponse_code().intValue() != 0)
									{
										alert(resp.getError_action()  + " \n\r " + resp.getError_description());
										return;
									}
									alert("���� ������");
									
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
									tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
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
								
								Res res = CustomerService.getTieto_branch(branch, alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								rtea.setBRANCH(res.getName());
								
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
									connectionInfo.setBANK_C(VISA_BANK_C);
									connectionInfo.setGROUPC(VISA_GROUPC);
									
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
									tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
								}
								
								// OperationResponseInfo ori = new
								// OperationResponseInfo();
								OperationResponseInfoHolder orih = new OperationResponseInfoHolder(ori);
								RowType_ReplaceCard parameters = new RowType_ReplaceCard();
								
								try
								{
									connectionInfo.setBANK_C(VISA_BANK_C);
									connectionInfo.setGROUPC(VISA_GROUPC);
									
									parameters.setCARD(((AccInfo) event.getTarget().getAttribute("acc")).getCard());
									
									RowType_ReplaceCardHolder rrch = new RowType_ReplaceCardHolder(parameters);
									
									issuingPortProxy.replaceCard(connectionInfo, parameters, orih, rrch);
									
									alert("����� ������������");
									// UserService.UsrLog(new
									// UserActionsLog(uid, un, curip, 7, 1,
									// "����� No ["+((AccInfo)event.getTarget().getAttribute("acc")).getCard()+"] ������������",
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
									tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
									show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
								}
								
							}
					
				});
				
				if (cardInfo.getBank_account_status().compareTo("0") == 0) acc_act.appendChild(bt_block_card_acc);
				if (cardInfo.getBank_account_status().compareTo("3") == 0) acc_act.appendChild(bt_close_card_acc);
				if (cardInfo.getBank_account_status().compareTo("3") == 0) acc_act.appendChild(bt_unblock_card_acc);
				
				String state = "";
				
				if (cardInfo.getBank_account_status().compareTo("0") == 0) state = "�������";
				if (cardInfo.getBank_account_status().compareTo("3") == 0) state = "�� �������";
				if (cardInfo.getBank_account_status().compareTo("4") == 0) state = "������";
				
				if (cardInfo.getSTOP_CAUSE().equals("0"))
				{
					bl_unbl.appendChild(btblock_card);
					//bl_unbl.appendChild(btrefresh_card_app);
				}
				else
				{
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
				else row.appendChild(new Listcell("�� �������"));
				
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
		});
		
		addwnd$accGrid.setItemRenderer(new ListitemRenderer()
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
							acfilter.setAcc_bal(Acc_22618);
							acfilter.setBranch(branch);
							acfilter.setCurrency("840");
							
							com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
							
							addwnd$accGrid.setModel((ListModel) acc_model);
						}
						
					});
					
					// if (pAccInfo.getId_order().compareTo("901") == 0)
					if ((act.get(i).getAction_id() == 1) ||
							(act.get(i).getAction_id() == 2) ||
							(act.get(i).getAction_id() == 20)) h_edit_cell.appendChild(bt_acc_act);
				}
				
				Listcell h_add_acc_cell = new Listcell();
				if (pAccInfo.getState() == 2)
				{
					bt_acc_act = new Toolbarbutton();
					bt_acc_act.setLabel("������� �����");
					bt_acc_act.setAttribute("acc", pAccInfo);
					bt_acc_act.setImage("/images/credit_card1.png");
					bt_acc_act.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event)
								throws Exception
						{
							Res res;
							if (CheckNull.isEmpty(addwnd$sproduct.getValue()))
							{
								alert("������� �� ������");
								return;
							}
							String new_card_acc = ((GlobuzAccount) event.getTarget().getAttribute("acc")).getId();
							BigDecimal agre_nom = null;
							String RT = null;
							if (addwnd$sproduct.getValue().compareTo("004") == 0)
							{
								Tclient ntc;
								ntc = tietocl;
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt("004"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("004", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								
								tcs = TclientService.getTietoCardSetting(Integer.parseInt("001"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("001", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								contract_nmb = null;
								RT = open_card("004", RT, false, new_card_acc);
								if (RT == null) return;
								open_card("001", RT, true, new_card_acc);
								contract_nmb = null;
							}
							else if (addwnd$sproduct.getValue().compareTo("008") == 0)
							{
								Tclient ntc;
								ntc = tietocl;
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt("008"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("008", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								tcs = TclientService.getTietoCardSetting(Integer.parseInt("002"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("002", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								contract_nmb = null;
								RT = open_card("008", RT, false, new_card_acc);
								if (RT == null) return;
								open_card("002", RT, true, new_card_acc);
								contract_nmb = null;
							}
							else if (addwnd$sproduct.getValue().compareTo("007") == 0)
							{
								Tclient ntc;
								ntc = tietocl;
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt("007"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("007", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								tcs = TclientService.getTietoCardSetting(Integer.parseInt("002"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("002", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								contract_nmb = null;
								RT = open_card("007", RT, false, new_card_acc);
								if (RT == null) return;
								open_card("002", RT, true, new_card_acc);
								contract_nmb = null;
							}
							
							else if (addwnd$sproduct.getValue().compareTo("015") == 0)
							{
								Tclient ntc;
								ntc = tietocl;
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt("015"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("015", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								tcs = TclientService.getTietoCardSetting(Integer.parseInt("002"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("002", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								contract_nmb = null;
								RT = open_card("015", RT, false, new_card_acc);
								if (RT == null) return;
								open_card("002", RT, true, new_card_acc);
								contract_nmb = null;
							}
							
							else if (addwnd$sproduct.getValue().compareTo("005") == 0)
							{
								Tclient ntc;
								ntc = tietocl;
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt("009"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("009", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								tcs = TclientService.getTietoCardSetting(Integer.parseInt("005"), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card("005", TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								contract_nmb = null;
								RT = open_card("009", RT, false, new_card_acc);
								if (RT == null) return;
								open_card("005", RT, true, new_card_acc);
								contract_nmb = null;
							}
							else
							{
								Tclient ntc;
								ntc = tietocl;
								TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(addwnd$sproduct.getValue()), alias);
								
								res = com.is.tieto_agro.tieto.TclientService.check_card(addwnd$sproduct.getValue(), TclientService.getAccInfo_active(ntc.getClient(), alias, issuingPortProxy), alias);
								if (res.getCode() != 0)
								{
									alert(res.getName());
									return;
								}
								
								contract_nmb = null;
								open_card(addwnd$sproduct.getValue(), RT, false, new_card_acc);
								contract_nmb = null;
							}
							addwnd.setVisible(false);
						}
						
					});
					// ������� ����� //h_add_acc_cell.appendChild(bt_acc_act);
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
					btbreak.setTooltiptext("��������� ������");
					btbreak.addEventListener(Events.ON_CLICK, new EventListener()
					{
						@Override
						public void onEvent(Event event) throws Exception
						{
							final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
							
							Messagebox.show("�� ������������� ������ ��������� ������ ����� � ����� ? ",
								"�������������� ������������",
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
											Res res = CustomerService.removeTc(un, pwd, lnk_id, alias);
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
					br_cell.appendChild(btbreak);
					row.appendChild(br_cell);
					
					Listcell edit_cell = new Listcell();
					btedit = new Toolbarbutton();
					btedit.setLabel("");
					btedit.setImage("/images/config.png");
					btedit.setAttribute("br_cl", pCustomer);
					btedit.setAttribute("is_br", true);
					btedit.setAttribute("ho_cl", null);
					btedit.setAttribute("ti_cl", null);
					btedit.setAttribute("is_ti", false);
					btedit.setAttribute("is_ho", false);
					btedit.setAttribute("edit_ho", false);
					btedit.setAttribute("edit_br", false);
					btedit.setAttribute("edit_ti", false);
					btedit.setTooltiptext("������������� �����");
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
							
								add_everywhere.setTitle("�������������� �������");
								fl_edit = true;
								add_everywhere.setVisible(true);
							}
					});
					edit_cell.appendChild(btedit);
					row.appendChild(edit_cell);
					
					Listcell b_edit_cell = new Listcell();
					row.setValue(pCustomer);
					
					Listcell h_edit_cell = new Listcell();
					bth = new Toolbarbutton();
					bth.setLabel("");
					bth.setImage("/images/edit.png");
					bth.setAttribute("br_cl", pCustomer);
					bth.setTooltiptext("������������� ��");
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
					btt.setLabel("");
					btt.setImage("/images/link16.png");
					btt.setAttribute("br_cl", pCustomer);
					btt.setTooltiptext("������������� tieto");
					btt.addEventListener(Events.ON_CLICK, new EventListener()
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
							add_ti = true;
							add_ho = false;
							add_br = false;
							set_default();
							fl_edit = false;
							add_everywhere.setTitle("�������� �������");
							add_everywhere.setVisible(true);
						}
						
					});
					t_edit_cell.appendChild(btt);
					
					btacc = new Toolbarbutton();
					btacc.setImage("/images/edit.png");
					btacc.setAttribute("br_cl", pCustomer);
					btacc.setTooltiptext("������� ����� �� ����� 22618");
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
							
							// cur_HO_customer = (Customer)
							// event.getTarget().getAttribute("ho_cl");
							cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
							tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
							add_ti = false;
							add_ho = false;
							add_br = true;
							
							GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
							acfilter.setClient(cur_branch_customer.getId_client());
							acfilter.setAcc_bal(Acc_22618);
							acfilter.setBranch(branch);
							acfilter.setCurrency("840");
							com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
							
							accounts$accGrid.setModel((ListModel) acc_model);
							
							accounts$btn_add.setDisabled(false);
							accounts.setVisible(true);
							
							accounts$btn_add.setVisible(false);
							accounts$btn_cancel.setVisible(false);
						}
						
					});
					acc_edit_cell.appendChild(btacc);
					
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
							Customer branch_cst = com.is.tieto_agro.customer.CustomerService.getCustomerById(lnk.Head_id, ConnectionPool.getValue("HO", alias), halias);
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
				btbreak.setTooltiptext("��������� ������");
				btbreak.setTooltip("������� ������");
				btbreak.setAttribute("ti_cl", pTclient);
				btbreak.addEventListener(Events.ON_CLICK, new EventListener()
				{
					@Override
					public void onEvent(Event event) throws Exception
					{
						final int lnk_id = (Integer) event.getTarget().getAttribute("lnk_id");
						Messagebox.show("�� ������������� ������ ��������� ������ ����� � ����� ? ",
							"�������������� ������������",
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
											
											Res res = CustomerService.removeTc(un, pwd, lnk_id, alias);
											
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
				br_cell.appendChild(btbreak);
				row.appendChild(br_cell);
				
				Listcell edit_cell = new Listcell();
				
				btedit = new Toolbarbutton();
				btedit.setLabel("");
				btedit.setImage("/images/config.png");
				btedit.setAttribute("ti_cl", pTclient);
				btedit.setAttribute("is_ti", true);
				btedit.setAttribute("br_cl", null);
				btedit.setAttribute("ho_cl", null);
				btedit.setAttribute("is_br", false);
				btedit.setAttribute("is_ho", false);
				btedit.setAttribute("edit_ho", false);
				btedit.setAttribute("edit_br", false);
				btedit.setAttribute("edit_ti", false);
				btedit.setTooltiptext("������������� �����");
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
						add_everywhere.setTitle("�������������� �������");
						fl_edit = true;
						add_everywhere.setVisible(true);
					}
					
				});
				edit_cell.appendChild(btedit);
				row.appendChild(edit_cell);
				
				Listcell h_edit_cell = new Listcell();
				bth = new Toolbarbutton();
				bth.setLabel("");
				bth.setImage("/images/edit.png");
				bth.setAttribute("ti_cl", pTclient);
				bth.setTooltiptext("������������� ��");
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
				btb.setTooltiptext("������������� ������");
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
				btacc.setImage("/images/edit.png");
				btacc.setAttribute("ti_cl", pTclient);
				btacc.setTooltiptext("������� ����� �� ����� 22618");
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
						
						// cur_HO_customer = (Customer)
						// event.getTarget().getAttribute("ho_cl");
						cur_branch_customer = (Customer) event.getTarget().getAttribute("br_cl");
						tietocl = (Tclient) event.getTarget().getAttribute("ti_cl");
						add_ti = false;
						add_ho = false;
						add_br = true;
						
						GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
						acfilter.setClient(cur_branch_customer.getId_client());
						acfilter.setAcc_bal(Acc_22618);
						acfilter.setBranch(branch);
						acfilter.setCurrency("840");
						
						com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
						accounts$accGrid.setModel((ListModel) acc_model);
						
						accounts$btn_add.setDisabled(false);
						accounts.setVisible(true);
						
						accounts$btn_add.setVisible(false);
						accounts$btn_cancel.setVisible(false);
						
						// refreshModel(_starttPageNumber);
					}
					
				});
				acc_edit_cell.appendChild(btacc);
				
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
						Customer head_cst = com.is.tieto_agro.customer.CustomerService.getCustomerById(lnk.Head_id, ConnectionPool.getValue("HO", alias), halias);
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
						Customer branch_cst = com.is.tieto_agro.customer.CustomerService.getCustomerById(lnk.Branch_id, branch, alias);
						
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
						Customer head_cst = com.is.tieto_agro.customer.CustomerService.getCustomerById(pre_ho, ConnectionPool.getValue("HO", alias), halias);
						
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
		
//		bfilter.setBranch(branch);
		
		bmodel = new com.is.tieto_agro.customer.PagingListModel(activePage, _pageSize, bfilter, alias);
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
		//������		
		
		tmodel = new com.is.tieto_agro.tieto.PagingListModel(_startpageAgro, _pageSize, tfilter, alias, issuingPortProxy);
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
		if (tietocl != null)
		{
			tcust.setTieto_customer_id(tietocl.getClient());
			tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
			
			List<AccInfo> list = new ArrayList<AccInfo>();
			show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
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
		// SelectEvent evt = new SelectEvent("onSelect", bank_customers,
		// bank_customers.getSelectedItems());
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
		
		bfilter = new CustomerFilter(filter);
		// bfilter.setDate_close(new Date());
		
		refreshModel(_startPageNumber);
	}
	
	public void onClick$btn_add_everywhere()
	{
		add_ti = true;
		add_ho = true;
		add_br = true;
		
		// add_everywhere$acode_tel.setValue("");
		CheckNull.clearForm(add_everywhere$addgrdr);
		CheckNull.clearForm(add_everywhere$addgrdl);
		
		add_everywhere.setTitle("�������� ������� [����]");
		add_everywhere$acode_resident.setSelecteditem("1");
		add_everywhere$ap_code_citizenship.setSelecteditem("860");
		add_everywhere$acode_country.setSelecteditem("860");
		
		add_everywhere.setVisible(true);
	}
	
	public void onClick$tbtn_add()
	{
		atcust = getTFromBank(current, atcust);
		atcust.setBank_c(addTieto$abank_c.getValue());
		alert("����������� � Tieto : " + atcust.getSearch_name() + " l " + addTieto$asearch_name.getValue());
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
			connectionInfo.setBANK_C(VISA_BANK_C);
			connectionInfo.setGROUPC(VISA_GROUPC);
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
			alert("������ ��������");
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
		alert("������� ���������� " + tcust.getHead_customer_id() + " � ����� " + tcust.getTieto_customer_id());
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
			alert("������ �� ������ ���� �� ���������.");
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
		
		tcust.setBank_c(VISA_BANK_C);
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
		 * alert(res.getName()); return; } Customer lg_c =
		 * CustomerService.getCustomerById(addCustomer$add_cst_id.getValue(),
		 * branch , alias); if (lg_c.getName() != null) { cl_n = lg_c.getName()
		 * + " " + lg_c.getP_birthday(); } log =
		 * "��������� ������ ������� � id  [" +
		 * cur_branch_customer.getId_client() + "] ��� ������� [" + branch +
		 * "]: ������ ������ � �� [" + addCustomer$add_cst_id.getValue() + "] ["
		 * + cl_n + "]"; alert(log); } else if (is_ti) { Res res =
		 * CustomerService.update_lnk_ho_by_ti(branch,
		 * addCustomer$add_cst_id.getValue(), tietocl.getClient(), alias); if
		 * (res.getCode() == 0) { alert(res.getName()); return; } String halias
		 * = CustomerService.get_alias_ho(alias); Customer lg_c =
		 * CustomerService.getCustomerById(addCustomer$add_cst_id.getValue(),
		 * ConnectionPool.getValue("HO", alias), halias); if (lg_c.getName() !=
		 * null) { cl_n = lg_c.getName() + " " + lg_c.getP_birthday(); } log =
		 * "��������� ������ ������� � tieto id [" + tietocl.getClient() +
		 * "] ��� ������� [" + branch + "]: ������ ������ � �� [" +
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
				 * 0) { alert(res.getName()); return; } Customer lg_c =
				 * CustomerService
				 * .getCustomerById(addCustomer$add_cst_id.getValue(), branch,
				 * alias); if (lg_c.getName() != null) { cl_n = lg_c.getName() +
				 * " " + lg_c.getP_birthday(); } log =
				 * "��������� ������ ������� � id � �� [" +
				 * cur_HO_customer.getId_client() + "] ��� ������� [" + branch +
				 * "]: ���������� ������  [" + addCustomer$add_cst_id.getValue()
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
				
				log = "��������� ������ ������� � tieto id [" + tietocl.getClient() + "] ��� [" + branch + "]  c id [" + addCustomer$add_cst_id.getValue() + "] ������� [" + cl_n + "]";
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
	/*����������� �����*/
	public void onClick$add_btn$add_everywhere()
	{
		boolean fl_err = false;
		String err = "";
		
		if ((!((add_everywhere$ap_passport_number.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_number.getValue().length() > 9))
		{
			fl_err = true;
			err += "\n����� ��������";
		}
		if ((!((add_everywhere$ap_passport_serial.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_serial.getValue().length() > 9))
		{
			fl_err = true;
			err += "\n����� ��������";
		}
		if ((!((add_everywhere$ap_passport_place_registration.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_passport_place_registration.getValue().length() > 200))
		{
			fl_err = true;
			err += "\n ��� ����� *";
		}
		if ((!((add_everywhere$ap_family.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_family.getValue().length() > 34))
		{
			fl_err = true;
			err += "\n�������";
		}
		if ((!((add_everywhere$ap_first_name.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_first_name.getValue().length() > 20))
		{
			fl_err = true;
			err += "\n���";
		}
		if ((!((add_everywhere$ap_patronymic.getValue()).matches("[a-zA-Z0-9]*"))) || (add_everywhere$ap_patronymic.getValue().length() > 20))
		{
			fl_err = true;
			err += "\n��������";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())))
		{
			fl_err = true;
			err += "\n��� ���������";
		}
		if ((!((add_everywhere$ap_number_tax_registration.getValue()).matches("[0-9]*"))) || (add_everywhere$ap_number_tax_registration.getValue().length() > 9))
		{
			fl_err = true;
			err += "\n���";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())))
		{
			fl_err = true;
			err += "\n�����������";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_country.getValue())))
		{
			fl_err = true;
			err += "\n������";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_resident.getValue())))
		{
			fl_err = true;
			err += "\n��������";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_registration.getValue())))
		{
			fl_err = true;
			err += "\n���� ������";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())))
		{
			fl_err = true;
			err += "\n���� ��������";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_tel.getValue())))
		{
			fl_err = true;
			err += "\n������ ��� ���������� ����������";
		}
		if ((!((add_everywhere$ap_post_address.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_post_address.getValue().length() > 95))
		{
			fl_err = true;
			err += "\n�������� �����";
		}
		if ((!((add_everywhere$ap_birth_place.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*"))) || (add_everywhere$ap_birth_place.getValue().length() > 200))
		{
			fl_err = true;
			err += "\n����� ��������";
		}
		
		if (fl_err)
		{
			alert("������ ���������� �����:\n������� ��������� ����:\n" + err);
			return;
		}
		
		String cur_acc = null;
		Customer new_customer = new Customer();
		String Branch_id = cur_branch_customer != null ? cur_branch_customer.getId_client() : null;
		String Tieto_id = tietocl != null ? tietocl.getClient() : null;
		
		new_customer.setP_passport_number(add_everywhere$ap_passport_number.getValue());
		new_customer.setP_passport_type(add_everywhere$ap_type_document.getValue());
		new_customer.setP_type_document(add_everywhere$ap_type_document.getValue());
		new_customer.setP_passport_serial(add_everywhere$ap_passport_serial.getValue());
		new_customer.setP_passport_place_registration(add_everywhere$ap_passport_place_registration.getValue());
		new_customer.setP_family(add_everywhere$ap_family.getValue());
		new_customer.setP_first_name(add_everywhere$ap_first_name.getValue());
		new_customer.setName(add_everywhere$ap_family.getValue() + " " + add_everywhere$ap_first_name.getValue() + " " + add_everywhere$ap_patronymic.getValue());
		new_customer.setP_birthday(add_everywhere$ap_birthday.getValue());
		new_customer.setCode_country(add_everywhere$acode_country.getValue());
		new_customer.setCode_resident(add_everywhere$acode_resident.getValue());
		new_customer.setP_post_address(add_everywhere$ap_post_address.getValue());
		new_customer.setR_CITY(add_everywhere$ar_city.getValue());
		new_customer.setAcode_tel(add_everywhere$acode_tel.getValue());
		new_customer.setP_patronymic(add_everywhere$ap_patronymic.getValue());		
		new_customer.setP_passport_date_expiration(add_everywhere$ap_passport_date_expiration.getValue());
		new_customer.setP_passport_date_registration(add_everywhere$ap_passport_date_registration.getValue());
		new_customer.setP_code_birth_region(CheckNull.isEmpty(add_everywhere$ap_code_birth_region.getValue()) ? null : add_everywhere$ap_code_birth_region.getValue());
		new_customer.setP_code_birth_distr(add_everywhere$ap_code_birth_distr.getValue());
		new_customer.setP_birth_place(add_everywhere$ap_birth_place.getValue());
		new_customer.setP_code_gender(add_everywhere$ap_code_gender.getValue());
		new_customer.setP_code_nation(add_everywhere$ap_code_nation.getValue());
		new_customer.setP_code_adr_region(add_everywhere$ap_code_adr_region.getValue());
		new_customer.setP_code_adr_distr(add_everywhere$ap_code_adr_distr.getValue());
		new_customer.setP_code_tax_org(add_everywhere$ap_code_tax_org.getValue());
		new_customer.setP_number_tax_registration(add_everywhere$ap_number_tax_registration.getValue());
		new_customer.setP_code_citizenship(add_everywhere$ap_code_citizenship.getValue());
		new_customer.setP_phone_mobile(add_everywhere$ap_phone_mobile.getValue());
		new_customer.setP_email_address(add_everywhere$ap_email_address.getValue());
		new_customer.setP_phone_home(add_everywhere$ap_phone_home.getValue());		
		new_customer.setP_inps(add_everywhere$ap_inps.getValue());
		
		new_customer.setBranch(ConnectionPool.getValue("HO", alias));
		new_customer.setP_code_bank(ConnectionPool.getValue("HO", alias));
		new_customer.setP_code_class_credit("1");
		new_customer.setP_passport_type("N");
		new_customer.setCode_subject("P");
		new_customer.setSign_registr(2);
		new_customer.setCode_form("");
		new_customer.setCode_type("08");
		new_customer.setBranch(branch);
		new_customer.setP_code_bank(branch);
		
		if(cur_branch_customer != null)
		{
			new_customer.setId(cur_branch_customer.getId());
			new_customer.setId_client(cur_branch_customer.getId_client());
		}
		
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		connectionInfo.setBANK_C(VISA_BANK_C);
		connectionInfo.setGROUPC(VISA_GROUPC);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		RowType_Customer rtc = new RowType_Customer();
		Calendar cal_p = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		
		rtc.setF_NAMES(new_customer.getP_first_name());
		rtc.setCL_TYPE("1");
		
		rtc.setCLIENT_B(Branch_id);
		rtc.setSURNAME(new_customer.getP_family());
		rtc.setM_NAME(new_customer.getP_patronymic());
		
		cal_p.setTime(new_customer.getP_passport_date_registration());
		rtc.setDOC_SINCE(cal_p);
		
		cal.setTime(new_customer.getP_birthday());
		rtc.setB_DATE(cal);
		rtc.setRESIDENT(new_customer.getCode_resident());
		rtc.setSTATUS("10");
		rtc.setSEX(new_customer.getP_code_gender());
		rtc.setSERIAL_NO(new_customer.getP_passport_serial() + new_customer.getP_passport_number());
		rtc.setID_CARD(new_customer.getP_passport_number());
		rtc.setR_CITY(new_customer.getP_code_citizenship());
		rtc.setR_STREET(new_customer.getP_birth_place());
		rtc.setR_E_MAILS(new_customer.getP_email_address());
		rtc.setR_MOB_PHONE(new_customer.getP_phone_mobile());
		rtc.setR_PHONE(new_customer.getP_phone_home());
		rtc.setR_CNTRY((new_customer.getP_code_citizenship().compareTo("860") == 0) ? "UZB" : null);
		rtc.setISSUED_BY(new_customer.getP_passport_place_registration());
		rtc.setPERSON_CODE(add_everywhere$acode_tel.getValue());
//		rtc.setDOC_TYPE("200");
		// **************************** new 20160505 evening ***********//
		rtc.setREC_DATE(calendar);
		
		OperationResponseInfo orInfo = null;
		
		RowType_CustomerHolder customerInfo = new RowType_CustomerHolder(rtc);
		ListType_CustomerCustomInfoHolder customListInfo = new ListType_CustomerCustomInfoHolder();
		
		if (!fl_edit)
		{
			// ////////////////////////////// OPEN IN BRANCH //
			// ////////////////////////
			if (add_br)
			{
				Res res = CustomerService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), 
					new_customer, 1, 2, alias, true);
				
				if (res.getCode() != 0)
				{
					alert("������\n�������� ������� :\n" + res.getName());
					fl_edit = false;
					return;
				}
				else
				{
					alert("������ �������� : " + res.getName());
					Customer cst = com.is.tieto_agro.customer.CustomerService.getCustomerById_tbl(res.getName(), branch, alias);
					Branch_id = cst.getId_client();
					
				}
				
				if (!add_ti)
				{
					refreshModel(_starttPageNumber);
					add_everywhere.setVisible(false);
					fl_edit = false;
					return;
				}
				
			}
			// ///////////////////////// ADD TO ����� ////
			// //////////////////////////////
			
			if (add_ti)
			{
				try
				{
					orInfo = issuingPortProxy.newCustomer(connectionInfo, customerInfo, customListInfo);
					
					System.out.println("Response Info output:");
					System.out.println("-------------------------------");
					System.out.println("Response code = " + orInfo.getResponse_code());
					System.out.println("Error description = " + orInfo.getError_description());
					System.out.println("Error action = " + orInfo.getError_action());
					System.out.println("-------------------------------");
					
					if (orInfo.getError_description() != null)
					{
						alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
						System.out.println("ERROR ����� add client " + orInfo.getResponse_code() + "  client " + customerInfo.value.getCLIENT());
					}
					else
					{
						alert("������ �������� � �����");
						Tieto_id = customerInfo.value.getCLIENT();
						System.out.println("������ �������� � �����" + Tieto_id);
						
					}
					
				}
				catch (RemoteException e)
				{
					// TODO Auto-generated catch block
					LtLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
				}
				
				fl_edit = false;
				
			}
			
			// ////////////////////////////// CREATE A LINK
			// ////////////////////////////
			if (add_ti && add_br)
			{
				CustomerService.create_lnk(branch, null, Branch_id, Tieto_id, cur_acc, alias);
			}
			
			if (add_ti && !add_br)
			{
				CustomerService.update_lnk_ti_by_br(branch, cur_branch_customer.getId_client(), Tieto_id, alias);
			}
		}
		
		else
		{
			if (edit_br)
			{
				Res res = CustomerService.doAction(session.getAttribute("un").toString(), session.getAttribute("pwd").toString(), new_customer, 19, 0, alias, true);
				if (res.getCode() != 0)
				{
					alert(res.getName());
					return;
				}
				edit_br = false;
				alert("������ ������������� � �����");
			}
			
			if (edit_ti)
			{
				RowType_EditCustomer_Request customerRequest = new RowType_EditCustomer_Request
					(
						Tieto_id, VISA_BANK_C,
						rtc.getCLIENT_B(), rtc.getCL_TYPE(), rtc.getCLN_CAT(), rtc.getREC_DATE(), rtc.getF_NAMES(), rtc.getSURNAME(),
						rtc.getTITLE(), rtc.getM_NAME(), rtc.getB_DATE(), rtc.getRESIDENT(), rtc.getID_CARD(), rtc.getDOC_TYPE(),
						rtc.getR_PHONE(), rtc.getEMP_NAME(), rtc.getPOSITION(), rtc.getEMP_DATE(), rtc.getWORK_PHONE(), rtc.getR_STREET(),
						rtc.getR_CITY(), rtc.getR_CNTRY(), rtc.getR_PCODE(), rtc.getC_SINCE(), rtc.getCMP_NAME(), rtc.getCMPG_NAME(), rtc.getCO_POSITON(),
						rtc.getCONTACT(), rtc.getCNT_TITLE(), rtc.getCNT_PHONE(), rtc.getCNT_FAX(), rtc.getMNG_POSIT(), rtc.getMNG_NAME(),
						rtc.getMNG_PHONE(), rtc.getMNG_TITLE(), rtc.getMNG_FAX(), rtc.getREG_NR(), rtc.getCR_STREET(), rtc.getCR_CITY(),
						rtc.getCR_CNTRY(), rtc.getCR_PCODE(), rtc.getCOMENT(), rtc.getREGION(), rtc.getPERSON_CODE(), rtc.getRESIDENT_SINCE(),
						rtc.getYEAR_INC(), rtc.getCCY_FOR_INCOM(), rtc.getIMM_PROP_VALUE(),	rtc.getSEARCH_NAME(), rtc.getMARITAL_STATUS(), rtc.getCO_CODE(),
						rtc.getEMP_CODE(), rtc.getSEX(), rtc.getSERIAL_NO(), rtc.getDOC_SINCE(),rtc.getISSUED_BY(), rtc.getEMP_ADR(), rtc.getEMP_FAX(), rtc.getEMP_E_MAILS(),
						rtc.getR_E_MAILS(), rtc.getR_MOB_PHONE(), rtc.getR_FAX(), rtc.getCNT_E_MAILS(),	rtc.getCNT_MOB_PHONE(), rtc.getMNG_MOB_PHONE(), rtc.getMNG_E_MAILS(), rtc.getCR_E_MAILS(),
						rtc.getIN_FILE_NUM(), rtc.getU_COD1(), rtc.getU_COD2(), rtc.getU_COD3(), rtc.getU_FIELD1(), rtc.getU_FIELD2(), rtc.getCALL_ID(), rtc.getHOME_NUMBER(),
						rtc.getBUILDING(), rtc.getSTREET1(), rtc.getAPARTMENT(), rtc.getMIDLE_NAME(), rtc.getSTATUS(), null, rtc.getAMEX_MEMBER_SINCE(), rtc.getDCI_MEMBER_SINCE(),
						rtc.getREWARD_NO()
					);
				
				RowType_CustomerCustomInfo[] cci = {};
				
				try
				{
					orInfo = issuingPortProxy.editCustomer(connectionInfo, customerRequest);
					
					if (orInfo.getError_description() != null)
					{
						alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
						System.out.println("resp " + orInfo.getResponse_code() + "  client " + rtc.getCLIENT());
					}
					else
					{
						alert("������ �������������� (Tieto)");
						Tieto_id = rtc.getCLIENT();
					}
				}
				catch (RemoteException e)
				{
					// TODO Auto-generated catch block
					LtLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
					alert(e.getMessage());
				}
			}
		}
		
		fl_edit = false;
		refreshModel(_startPageNumber);
		add_everywhere.setVisible(false);
	}
	
	private String open_card(String card_prod_id, String RT, boolean sec, String new_card_acc)
	{
		
		if ((new_card_acc.substring(0, 5)).compareTo("22618") != 0)
		{
			alert("�������� ���� �����:\n" + new_card_acc);
			return "";
		}
		Tclient ntc;
		ntc = tietocl;
		
		String halias = CustomerService.get_alias_ho(alias);
		
		TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(card_prod_id), alias);
		TietoCustomer tc;
		tc = com.is.tieto_agro.customer.CustomerService.getTietoCustomer(ntc.getClient(), branch, alias);
		
		BigDecimal agrnum;
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		KeyType_Agreement mainAgreementInfo = new KeyType_Agreement();
		
		ListType_CardInfoHolder cardsListInfo = new ListType_CardInfoHolder();
		
		RowType_CardInfo rtci = new RowType_CardInfo();
		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		RowType_CardInfo_EMV_Data EMV_data = new RowType_CardInfo_EMV_Data();
		
		RowType_AgreementHolder agreementInfo = new RowType_AgreementHolder();
		RowType_Agreement avalue = new RowType_Agreement();
		
		ListType_AccountInfoHolder accountsListInfo = new ListType_AccountInfoHolder();
		RowType_AccountInfo_Base base_info = new RowType_AccountInfo_Base();
		RowType_AccountInfo rtai = null;
		
		Calendar cal = Calendar.getInstance();
		
		try
		{
			
			connectionInfo.setBANK_C(tcs.getBank_c());
			connectionInfo.setGROUPC(tcs.getGroup_c());
			
			avalue.setBINCOD(tcs.getBin());
			avalue.setBANK_CODE(VISA_BANK_C);
			
			Res res = CustomerService.getTieto_branch(branch, alias);
			if (res.getCode() != 0)
			{
				alert(res.getName());
				return null;
			}
			avalue.setBRANCH(res.getName());
			
			avalue.setCITY(!CheckNull.isEmpty(ntc.getR_city()) ? ntc.getR_city() : "UZB");
			// !!!!!!!avalue.setPRODUCT("00"+tcs.getCode());
			avalue.setPRODUCT(card_prod_id);
			avalue.setREP_LANG("1");
			avalue.setRISK_LEVEL(tcs.getRisk_level());
			avalue.setSTREET(!CheckNull.isEmpty(ntc.getR_street()) ? ntc.getR_street() : "STREET");
			avalue.setSTATUS("10");
			if (contract_nmb == null) contract_nmb = CustomerService.get_contract(branch, card_prod_id, alias);
			avalue.setCONTRACT(contract_nmb);
			avalue.setENROLLED(cal);
			avalue.setDISTRIB_MODE("01");
			avalue.setCLIENT(ntc.getClient());
			// avalue.set
			avalue.setCOUNTRY(!CheckNull.isEmpty(ntc.getR_cntry()) ? ntc.getR_cntry() : "UZB");
			agreementInfo.value = avalue;
			
			base_info.setC_ACCNT_TYPE("00");
			base_info.setCCY("USD");
			base_info.setSTAT_CHANGE("1");
			base_info.setMIN_BAL(BigDecimal.valueOf(0));
			base_info.setTRANZ_ACCT(new_card_acc);
			String a = new_card_acc;
			base_info.setACC_PRTY("1");
			base_info.setCOND_SET(tcs.getFinancial_conditions());
			base_info.setCYCLE("0");
			base_info.setCRD(BigDecimal.valueOf(0));
			base_info.setNON_REDUCE_BAL(tcs.getMinimum_balance());
			base_info.setSTATUS("0");
			// base_info.set
			base_info.setLIM_INTR(BigDecimal.valueOf(0));
			if (sec) base_info.setCARD_ACCT(RT);
			
			
			rtai = new RowType_AccountInfo(base_info, new RowType_AccountInfo_Additional());
			
			RowType_AccountInfo[] rtaia = new RowType_AccountInfo[] { rtai };
			
			ListType_AccountInfo ltai = new ListType_AccountInfo();
			ltai.setRow(rtaia);
			
			accountsListInfo = new ListType_AccountInfoHolder(ltai);

			
			logicalCard.setCOND_SET(tcs.getCard_condition());
			logicalCard.setRISK_LEVEL(tcs.getRisk_level());
			logicalCard.setBASE_SUPP("1");
			logicalCard.setF_NAMES(ntc.getF_names());
			logicalCard.setSURNAME(ntc.getSurname());
			// logicalCard.set
			physicalCard.setCARD_NAME((ntc.getF_names() + " " + ntc.getSurname()).length() > 23 ? (ntc.getSurname() + " " + (ntc.getF_names()).substring(0, 1).toUpperCase()) : (ntc.getF_names() + " " + ntc.getSurname()));
			physicalCard.setSTATUS1("0");
			physicalCard.setDESIGN_ID(tcs.getDesign_id());
			// physicalCard.set
			EMV_data.setCHIP_APP_ID(tcs.getId_chip_app());
			
			// logicalCard.set
			rtci.setLogicalCard(logicalCard);
			rtci.setPhysicalCard(physicalCard);
			rtci.setEMV_Data(EMV_data);
			
			RowType_CardInfo[] rtcia = { rtci };
			
			// ListType_CardInfo lgtci = new ListType_CardInfo();
			// lgtci.setRow(rtcia);
			// cardsListInfo = new ListType_CardInfoHolder(lgtci);
			
			cardsListInfo = new ListType_CardInfoHolder();  //// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			// issuingPortProxy.editAgreement(connectionInfo, agreementInfo,
			// accountsListInfo,
			// cardsListInfo);
			RowType_EditAgreement_Request re = new RowType_EditAgreement_Request
				(
					BigDecimal.valueOf(agre_nom_upd),
					avalue.getBINCOD(),
					avalue.getBANK_CODE(),
					avalue.getBRANCH(),
					avalue.getB_BR_ID(),
					avalue.getOFFICE(),
					avalue.getOFFICE_ID(),
					avalue.getCITY(),
					avalue.getCLIENT(),
					avalue.getCOMENT(),
					avalue.getCONTRACT(),
					avalue.getCOUNTRY(),
					avalue.getCTIME(),
					avalue.getDISTRIB_MODE(),
					avalue.getENROLLED(),
					avalue.getE_MAILS(),
					avalue.getIN_FILE_NUM(),
					avalue.getISURANCE_TYPE(),
					avalue.getPOST_IND(),
					avalue.getPRODUCT(),
					avalue.getREP_LANG(),
					avalue.getRISK_LEVEL(),
					avalue.getSTATUS(),
					avalue.getSTREET(),
					avalue.getUSRID(),
					avalue.getU_COD4(),
					avalue.getU_CODE5(),
					avalue.getU_CODE6(),
					avalue.getU_FIELD3(),
					avalue.getU_FIELD4()
//					avalue.getCOMBI_TYPE()
				);
			
			re.setAGRE_NOM(BigDecimal.valueOf(agre_nom_upd));
			// ���������� � �������
			if (edit_agree)
			{
				orInfo = issuingPortProxy.editAgreement(connectionInfo, re);
				
				connectionInfo.setBANK_C(VISA_BANK_C);
				connectionInfo.setGROUPC(VISA_GROUPC);
				
				OperationResponseInfo ori = new OperationResponseInfo();
				
				mainAgreementInfo.setAGRE_NOM(BigDecimal.valueOf(agre_nom_upd));
				
				orInfo = issuingPortProxy.addInfo4Agreement(connectionInfo,
									mainAgreementInfo,
									new ListType_AccountInfoHolder(),
									cardsListInfo);
			}
			else orInfo = issuingPortProxy.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
			// System.out.println("Tieto response:"+orInfo.getResponse_code().intValue()+"||agre response:"+agreementInfo.value+"||");
			edit_agree = false;
			if ((orInfo.getError_description() != null) || (orInfo.getResponse_code().intValue() != 0))
			{
				alert(orInfo.getError_description());
				return null;
			}
			
			RT = accountsListInfo.value.getRow(0).getBase_info().getCARD_ACCT();
			if ((orInfo.getError_description() != null) || (orInfo.getResponse_code().intValue() != 0))
			{
				alert(orInfo.getError_description());
				return null;
			}
			if (agreementInfo.value != null)
			{
				agrnum = agreementInfo.value.getAGRE_NOM();
				// String new_card_num =
				// cardsListInfo.value.getRow(0).getLogicalCard().getCARD();
				String new_card_num = cardsListInfo.value.getRow(0).getLogicalCard().getCARD();
				// UserService.UsrLog(new UserActionsLog(uid, un, curip, 7, 1,
				// "������� ����� ["+new_card_num+"] ��� ������� (tieto id ["+ntc.getClient()+"]) ["+ntc.getF_names()+
				// " "+ ntc.getSurname()+"]", branch));
				System.out.println("AGRE_NOM : " + agreementInfo.value.getAGRE_NOM());
			}
			
		}
		catch (Exception e)
		{
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			alert(e.getMessage());
			e.printStackTrace();
			return null;
		}
		alert("����� �������: " + tcs.getName());
		addwnd.setVisible(false);
		return RT;
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
		if (tietocl == null)
		{
			alert("������ �� ����� �� ������");
			return;
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
		acfilter.setAcc_bal(Acc_22618);
		acfilter.setBranch(branch);
		acfilter.setCurrency("840");
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
				fill_form(cur_branch_customer, null);
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
		if (base_cl.getP_code_nation() != null) add_everywhere$ap_code_nation.setSelecteditem(base_cl.getP_code_nation());
		if (base_cl.getP_code_adr_region() != null) add_everywhere$ap_code_adr_region.setSelecteditem(base_cl.getP_code_adr_region());
		if (base_cl.getP_code_adr_distr() != null) add_everywhere$ap_code_adr_distr.setSelecteditem(base_cl.getP_code_adr_distr());
		if (base_cl.getP_code_tax_org() != null) add_everywhere$ap_code_tax_org.setSelecteditem(base_cl.getP_code_tax_org());
		if (base_cl.getP_number_tax_registration() != null) add_everywhere$ap_number_tax_registration.setValue(base_cl.getP_number_tax_registration());
		if (base_cl.getP_code_citizenship() != null) add_everywhere$ap_code_citizenship.setSelecteditem(base_cl.getP_code_citizenship());
		if (base_cl.getCode_country() != null) add_everywhere$acode_country.setSelecteditem(base_cl.getCode_country());
		if (base_cl.getCode_resident() != null) add_everywhere$acode_resident.setSelecteditem(base_cl.getCode_resident());
		if (base_cl.getP_phone_mobile() != null) add_everywhere$ap_phone_mobile.setValue(base_cl.getP_phone_mobile());
		if (base_cl.getP_email_address() != null) add_everywhere$ap_email_address.setValue(base_cl.getP_email_address());
		if (base_cl.getP_phone_home() != null) add_everywhere$ap_phone_home.setValue(base_cl.getP_phone_home());
		if (base_cl.getP_inps() != null) add_everywhere$ap_inps.setValue(base_cl.getP_inps());
		if (base_cl.getP_post_address() != null) add_everywhere$ap_post_address.setValue(base_cl.getP_post_address());
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
		 * Customer ncr =
		 * com.is.tieto_globuz.customer.CustomerService.getCustomerById
		 * (cur_branch_customer.getId_client(), branch, alias); Res res =
		 * GlobuzAccountService.doAction_create_acc_in_br( un , pwd , "20206" ,
		 * cur_branch_customer.getId_client() , "840" , null ,
		 * (ncr.getName().length() > 80 ? ncr.getName().substring(0, 79) :
		 * ncr.getName()) , 101 , alias , branch , true); if (res != null &&
		 * res.getCode() != 0) { alert("������\n�������� ����� 20206 :\n" +
		 * res.getName()); return; } else { alert("C��� ������ :" +
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
		TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(addwnd$sproduct.getValue()), alias);
		
		String cl_id = CustomerService.get_BR_by_tieto(tietocl.getClient(), alias);
		
		List<GlobuzAccount> aModel = CustomerService.get_card_accounts_new_card(cl_id, branch, addwnd$sproduct.getValue(), tietocl.getClient());
		
		addwnd$accGrid.setModel(new ListModelList(aModel));
	}
	
	public void onClick$btn_add$addwnd()
	{
		Res res = new Res();
		String cl_id;
		
		if (CheckNull.isEmpty(addwnd$sproduct.getValue()))
		{
			alert("�������� �������");
			return;
		}
		
		TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(addwnd$sproduct.getValue()), alias);
		String ord = tcs.getId_order_account();
		
		/*res = open22618_acc(tcs.getId_order_max(), ord, tcs.getAcc_name_postfix() , 
							(branch.compareTo(ConnectionPool.getValue("HO", alias)) == 0) ? tcs.getHo_acc_group() : tcs.getBr_acc_group());
		*/
		res = open22618_acc(ord, 0);
		
		alert(res.getName());
		
		GlobuzAccountFilter acfilter = new GlobuzAccountFilter();
		cl_id = CustomerService.get_BR_by_tieto(tietocl.getClient(), alias);
		
		acfilter.setClient(cl_id);		
		acfilter.setAcc_bal(Acc_22618);
		acfilter.setBranch(branch);
		acfilter.setCurrency("840");
		com.is.tieto_globuz.tietoAccount.PagingListModel acc_model = new com.is.tieto_globuz.tietoAccount.PagingListModel(0, 100, acfilter, alias);
		addwnd$accGrid.setModel((ListModel) acc_model);
		
		
	}
	
	public Res open22618_acc(String ord, int group)
	{
		Res res = new Res();
		
		try
		{
			String ccycd = "840";
			//String cl_id = tietocl.getClient_b();
			String cl_id = cur_branch_customer.getId_client();
			
			//Customer cst = CustomerService.getCustomerById(tietocl.getClient_b(), branch, alias);
			Customer cst = CustomerService.getCustomerById(cl_id, branch, alias);
			
			String customerName = cst.getName().length() > 80 ? cst.getName().substring(0, 79) : cst.getName();
			
			String ord_1 = CustomerService.Get_acc_hole(Acc_22618, ord, null, cst.getId_client(), branch, alias).getName();
			
			String customer_id = cst.getId_client();
			
			boolean brcomp = (branch.compareTo(ConnectionPool.getValue("HO", alias)) == 0);
			
			res = GlobuzAccountService.doAction_create_acc_in_br(un, pwd, Acc_22618, customer_id, ccycd, ord_1, customerName, group, alias, branch, brcomp);
			
			if (res.getCode() != 0)
			{
				alert("������\n�������� ����� 22618 :\n" + res.getName());
				return res;
			}
			else
			{
				String result = res.getName();
				res.setName("������ ���� " + result);
			}
			
			new_card_acc = res.getName();
		}
		catch (Exception e)
		{
			res.setName("ERROR => " + e.getLocalizedMessage());
			res.setCode(0);
			return res;
		}
		return res;
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
		tmpTCust = CustomerService.getTietoCustomer(tcust, alias);
		
		show_cards$accGrid.setModel(new BindingListModelList(makeList(TclientService.getAccInfo(tietocl, alias, issuingPortProxy)), false));
		
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
			alert("������ ����� �� ������");
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
			alert("������ ����� �� ������");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_CAP", params);
	}
	
	public void onClick$btn_exchange_app$show_cards()
	{
		if (tietocl == null)
		{
			alert("������ ����� �� ������");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_APPL_VISA_EXCHANGE", params);
	}
	
	public void onClick$btn_exchange_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("������ ����� �� ������");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_EXCHANGE", params);
	}
	
	public void onClick$btn_u_exchange_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("������ ����� �� ������");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_UPEXCHANGE", params);
	}
	
	public void onClick$btn_au_pt_d$show_cards()
	{
		if (tietocl == null)
		{
			alert("������ ����� �� ������");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		fill_cl_report(tietocl, "TI_DOGOVOR_VISA_PREMIUM", params);
	}
	
	public void onClick$btn_au_pt_app$show_cards()
	{
		if (tietocl == null)
		{
			alert("������ ����� �� ������");
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
			alert("������ ����� �� ������");
			return;
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		/*
		 * params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":" +
		 * ((Integer) session.getAttribute("port")).toString() +
		 * "/ti/images/logo_ipak.jpg");
		 */

		params.put("P_IMG_PATH", "http://" + session.getLocalAddr() + ":5600/ti/images/logo_ipak.jpg");
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(alias);
			
			// kjasgfj
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
			alert("������ ����� �� ������");
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
			alert("������ ����� �� ������");
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
			err += "\n����� ��������";
		}
		if ((!((add_everywhere$ap_passport_serial.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_passport_serial.getValue().length() > 9))
		{
			fl_err = true;
			err += "\n����� ��������";
		}
		if ((!((add_everywhere$ap_passport_place_registration.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_passport_place_registration.getValue().length() > 200))
		{
			fl_err = true;
			err += "\n����� ����������� ��������";
		}
		if ((!((add_everywhere$ap_family.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_family.getValue().length() > 34))
		{
			fl_err = true;
			err += "\n�������";
		}
		if ((!((add_everywhere$ap_first_name.getValue()).matches("[a-zA-Z0-9]+"))) || (add_everywhere$ap_first_name.getValue().length() > 20))
		{
			fl_err = true;
			err += "\n���";
		}
		if ((!((add_everywhere$ap_patronymic.getValue()).matches("[a-zA-Z0-9]*"))) || (add_everywhere$ap_patronymic.getValue().length() > 20))
		{
			fl_err = true;
			err += "\n��������";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())))
		{
			fl_err = true;
			err += "\n��� ���������";
		}
		if ((!((add_everywhere$ap_number_tax_registration.getValue()).matches("[0-9]*"))) || (add_everywhere$ap_number_tax_registration.getValue().length() > 9))
		{
			fl_err = true;
			err += "\n���";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())))
		{
			fl_err = true;
			err += "\n�����������";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_country.getValue())))
		{
			fl_err = true;
			err += "\n������";
		}
		if ((CheckNull.isEmpty(add_everywhere$acode_resident.getValue())))
		{
			fl_err = true;
			err += "\n��������";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_passport_date_registration.getValue())))
		{
			fl_err = true;
			err += "\n���� ����������� ��������";
		}
		if ((CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())))
		{
			fl_err = true;
			err += "\n���� ��������";
		}
		if ((!((add_everywhere$ap_post_address.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+"))) || (add_everywhere$ap_post_address.getValue().length() > 95))
		{
			fl_err = true;
			err += "\n�������� �����";
		}
		if ((!((add_everywhere$ap_birth_place.getValue()).matches("[a-zA-Z0-9\\s\\.\\,_\\/-]*"))) || (add_everywhere$ap_birth_place.getValue().length() > 200))
		{
			fl_err = true;
			err += "\n����� ��������";
		}
		
		if (fl_err)
		{
			alert("������ ���������� �����:\n������� ��������� ���� " + err);
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
		alert("������ ������");
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
	
	private static agroBank.IssuingWS.IssuingPortProxy initNp(agroBank.IssuingWS.IssuingPortProxy issuingPortProxy, String alias)
	{
//		if (np == null)
//		{
//			np = new NilProvider();
//			np.init();
//		}
		
		
		return issuingPortProxy = new agroBank.IssuingWS.IssuingPortProxy(
			ConnectionPool.getValue("TIETO_VISA_HOST", alias),
			ConnectionPool.getValue("TIETO_VISA_HOST_USERNAME", alias),
			ConnectionPool.getValue("TIETO_VISA_HOST_PASSWORD", alias)
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
	
	
	public void onClick$productChoiceButton$productChoiceWindow()
	{
		if(productChoiceWindow$productChoice.getValue() == null ||
				productChoiceWindow$productChoice.getValue().equals("")) {
			alert("�������� ��� �����!");
			return;
		}
		
		Res res = CustomerService.update_lnk_set_acc(un, pwd, branch, cur_branch_customer.getId_client() , accProductChoice, alias, issuingPortProxy);
		
		if (res.getCode() == 1)
		{
			String cl_n = "";
			Customer lg_c = CustomerService.getCustomerById(cur_branch_customer.getId_client(), branch, alias);
			
			if (lg_c.getName() != null)
			{
				cl_n = lg_c.getName() + " " + lg_c.getP_birthday();
			}
			
			Res resAgreement = new Res();
			
			resAgreement = TclientService.addNewAgreement(lg_c, accProductChoice, tietocl, alias, issuingPortProxy, branch, productChoiceWindow$productChoice.getValue());
			alert(resAgreement.getName());
			if (resAgreement.getCode() != 1)
			{
				return;
			}
			
		}
		else
		{
			alert(res.getName());
			return;
		}
		
		// empcadd
		
		//
		
		refreshModel(_startPageNumber);
		accounts.setVisible(false);
		productChoiceWindow.setVisible(false);

	}
	
	public void onClick$cancelButton$productChoiceWindow()
	{
		productChoiceWindow.setVisible(false);
	}
	
}
