package com.is.tietovisa.customer;

import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.is.ConnectionPool;
import com.is.ISLogger;

import com.is.openwayutils.account.Account;
import com.is.openwayutils.account.AccountFilter;
import com.is.openwayutils.account.AccountService;
import com.is.tietovisa.Cons;
import com.is.tietovisa.PostUtils;
import com.is.tietovisa.StringUtils;
import com.is.tietovisa.Utils;
import com.is.tietovisa.model.AccInfo;
import com.is.tietovisa.model.CardIbs;
import com.is.tietovisa.model.CardInfo;
import com.is.tietovisa.model.CommonResponse;
import com.is.tietovisa.model.Cond_card;
import com.is.tietovisa.model.EditCustomerResponse;
import com.is.tietovisa.model.ListTypeResponse;
import com.is.tietovisa.model.ListType_GenericHolder;
import com.is.tietovisa.model.NewAgreementRequest;
import com.is.tietovisa.model.NewAgreementResponse;
import com.is.tietovisa.model.NewCustomerResponse;
import com.is.tietovisa.model.Product;
import com.is.tietovisa.model.ReplaceCardResponse;
import com.is.tietovisa.model.RowType_AccountInfo;
import com.is.tietovisa.model.RowType_AccountInfo_Additional;
import com.is.tietovisa.model.RowType_AccountInfo_Base;
import com.is.tietovisa.model.RowType_AddCardToStopList_Request;
import com.is.tietovisa.model.RowType_Agreement;
import com.is.tietovisa.model.RowType_CardInfo;
import com.is.tietovisa.model.RowType_CardInfo_EMV_Data;
import com.is.tietovisa.model.RowType_CardInfo_LogicalCard;
import com.is.tietovisa.model.RowType_CardInfo_PhysicalCard;
import com.is.tietovisa.model.RowType_CardInfo_TSM_Data;
import com.is.tietovisa.model.RowType_Customer;
import com.is.tietovisa.model.RowType_EditCustomer_Request;
import com.is.tietovisa.model.RowType_RemoveCardFromStop_Request;
import com.is.tietovisa.model.RowType_ReplaceCard;
import com.is.tietovisa.model.RowType_ResetPINCounter_Request;
import com.is.tietovisa.model.Tclient;
import com.is.utils.CheckNull;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class AddCstViewCtrl extends GenericForwardComposer {
	private static final long serialVersionUID = 1L;
	public CustomerFilter filter = new CustomerFilter();
	public Customer current = new Customer();
	public Customer curr = new Customer();
	public Customer copyOfCurrent;
	public RowType_Agreement curr_agree = new RowType_Agreement();
	public AccInfo curr_acc = new AccInfo();
	public CardInfo curr_card = new CardInfo();
	public Customer curr_tieto = new Customer();
	public CardInfo new_card;

	private AnnotateDataBinder binder;

	private int _pageSize;
	private int _startPageNumber = 0;
	private int _totalSize;
	private String un;
	private String pwd;
	private String branch;
	private String alias;
	private String curip;
	private int uid;
	static String listCustomerEndpoint;
	private static HashMap<String, String> statesNciAccount = null;
	private static HashMap<String, String> statesTieto = null;
	private static HashMap<String, String> stopCausesTieto = null;
	
	private boolean add_tie;
	private boolean add_bnk;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.mm.yyyy");
	private static SimpleDateFormat sdfs = new SimpleDateFormat("MMyy");

	private Window add_everywhere, add_agree_acc_card, add_card_wnd, lock_card_wnd, reissue_card_wnd, cardActionsWindow;
	
	private Datebox add_everywhere$ap_birthday;
	// private Datebox add_everywhere$ap_passport_date_expiration;
	private Datebox add_everywhere$ap_passport_date_registration;
	private Datebox add_everywhere$ao_address_fact_date;
	private Grid add_everywhere$addgrdl;
	private Grid add_everywhere$addgrdr;
	private Grid tfgrd, add_agree_acc_card$addgrdl;
	private Grid add_card_wnd$addgrdl;

	private RefCBox add_everywhere$acode_country;
	private RefCBox add_everywhere$acode_resident;
	private RefCBox add_everywhere$ap_code_adr_distr;
	private RefCBox add_everywhere$ap_code_adr_region;
	private RefCBox add_everywhere$ap_code_citizenship;
	private RefCBox add_everywhere$ap_code_gender;
	private RefCBox add_everywhere$ap_code_nation;
	private RefCBox add_everywhere$ap_code_tax_org;
	private RefCBox add_everywhere$ap_type_document;
	
	private RefCBox lock_card_wnd$rcb_stop_cause;
	private Row lock_card_wnd$row_stop_cause;
	private Textbox lock_card_wnd$txt_comment_text;
	private Toolbarbutton lock_card_wnd$lock_card_btn, lock_card_wnd$unlock_card_btn;
	
	private RefCBox reissue_card_wnd$card_risk_level, reissue_card_wnd$card_design;
	private Datebox reissue_card_wnd$card_expiry;
	
	private RefCBox add_agree_acc_card$agree_product,
			add_agree_acc_card$agree_bincod, add_agree_acc_card$agree_branch,
			add_agree_acc_card$acc_bal;
	private RefCBox add_agree_acc_card$acc_cond_set,
			add_agree_acc_card$card_risk_level,
			add_agree_acc_card$card_cond_set, add_agree_acc_card$card_design;
	private RefCBox add_agree_acc_card$card_chip;

	// private Textbox add_everywhere$aid_client;
	private Textbox add_everywhere$ao_city;
	private Textbox add_everywhere$ap_zip_code;
	private Textbox add_everywhere$ap_birth_place;
	private Textbox add_everywhere$ap_email_address;
	private Textbox add_everywhere$ap_family;
	private Textbox add_everywhere$ap_first_name;
	// private Textbox add_everywhere$ap_inps;
	private Textbox add_everywhere$ap_number_tax_registration;
	private Textbox add_everywhere$ap_passport_number;
	private Textbox add_everywhere$ap_passport_place_registration;
	private Textbox add_everywhere$ap_passport_serial;
	private Textbox add_everywhere$ap_patronymic;
	// private Textbox add_everywhere$ap_phone_home;
	private Textbox add_everywhere$ap_phone_mobile;
	private Textbox add_everywhere$ap_post_address;
	private Textbox add_everywhere$ap_pinfl;
	// private Textbox add_everywhere$customerId;
	private Textbox add_everywhere$ao_security_name;
	private Textbox add_everywhere$ao_post_address_fact;
	private Textbox add_agree_acc_card$id_order, add_agree_acc_card$tranz_acct, add_agree_acc_card$acc_non_reduce_balance;
	//private Textbox add_agree_acc_card$o_comment_text;
	private Textbox add_card_wnd$o_rbs_number;
	//private Textbox add_card_wnd$o_comment_text;

	// private Paging bankdataPaging;
	private PagingListModel model;
	private Listbox branch_customers, tietoGrid, accGrid, cardGrid, cardActionsWindow$cardActionsListbox;

	private Textbox txbId_client;
	private Textbox txbPinfl;

	private Textbox txbPassportSerial;
	private Textbox txbName;
	private Datebox dbxB_date;
	private Textbox txbId_tieto, txbCardNumber, txbMobilePhone, txbLastName,
			txbClient_b;

	public AddCstViewCtrl() {
		super('$', false, false);
		this._pageSize = 50;
		add_tie = false;
		add_bnk = false;

	}

	public void doAfterCompose(final Component comp) throws Exception,
			ConnectException, SQLException {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		this.self.setAttribute("binder", (Object) this.binder);

		binder.bindBean("filter", this.filter);
		binder.bindBean("current", this.current);
		binder.bindBean("curr", this.curr);
		binder.bindBean("curr_acc", this.curr_acc);
		binder.bindBean("curr_card", this.curr_card);
		binder.bindBean("new_card", this.new_card);
		binder.bindBean("curr_tieto", this.curr_tieto);
		

		this.uid = (Integer) this.session.getAttribute("uid");
		this.un = (String) this.session.getAttribute("un");
		this.pwd = (String) this.session.getAttribute("pwd");
		this.branch = (String) this.session.getAttribute("branch");
		this.alias = (String) this.session.getAttribute("alias");
		this.curip = (String) session.getAttribute("curip");
		com.is.tietovisa.customer.CustomerService.initConst(alias);

		// openwayEndpoint = "http://localhost:8081/VisaTiGV/";
		listCustomerEndpoint = CustomerService.mapConst
				.get(Cons.url_listcustomers);
		/*
		 * if (tietoEndpoint!=null || tietoEndpoint.trim().equals("")) {
		 * tietoEndpoint=tietoEndpoint.trim(); if
		 * (tietoEndpoint.substring(tietoEndpoint.length()-1)!="/")
		 * tietoEndpoint=tietoEndpoint+"/"; }
		 */

		statesNciAccount = com.is.tietovisa.customer.CustomerService
				.getMapStatesNciAccount(alias);
		statesTieto = com.is.tietovisa.customer.CustomerService.getMapStatesTieto(alias);
		stopCausesTieto = com.is.tietovisa.customer.CustomerService.getMapStopCauses(alias);

		// filter.setP_pinfl("56789012340078");
		// filter.setId_client("60000001");
		filter.setEndpoint(listCustomerEndpoint);

		branch_customers.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) {
				Customer vCustomer = (Customer) data;
				row.setAttribute("row", (Object) row);
				row.setValue((Object) vCustomer);
				
				row.setAttribute("sign_record", vCustomer.getSign_record());
				// row.appendChild(new Listcell(""));

				
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getBranch())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getId_client())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getName())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getP_birthday())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getP_pinfl())));
				/*
				 * row.appendChild(new
				 * Listcell(StringUtils.secureNull(vCustomer.
				 * getP_passport_serial()) +
				 * StringUtils.secureNull(vCustomer.getP_passport_number())));
				 */
				// knopka
				if (vCustomer.getSign_record().equals("2") || vCustomer.getSign_record().equals("4")) {

					Listcell cell = new Listcell();
					Toolbarbutton btn = new Toolbarbutton();
					btn.setLabel("");
					btn.setAttribute("br_cl", (Object) vCustomer);
					btn.setImage("/images/create-16-2.png");
					btn.setTooltiptext("Открыть TIETO клиент");
					btn.addEventListener(Events.ON_CLICK, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							curr = (Customer) event.getTarget().getAttribute("br_cl");
							//2023-06-29 begin
							curr.setCode_nation_val(CustomerService.getNationText(curr.getP_code_nation(), alias));     
							curr.setCode_resident_val(CustomerService.getResidentText(curr.getCode_resident(), alias));   
							curr.setCode_adr_distr_val(CustomerService.getDistrText(curr.getP_code_adr_distr(), alias));  
							curr.setCode_adr_region_val(CustomerService.getRegionText(curr.getP_code_adr_region(), alias)); 
							curr.setCode_gender_val(CustomerService.getGenderText(curr.getP_code_gender(), alias));     
							curr.setCode_citizenship_val(CustomerService.getCountryText(curr.getP_code_citizenship(), alias));
							curr.setCode_country_val(CustomerService.getCountryText(curr.getCode_country(), alias));    
							curr.setType_document_val(CustomerService.getDocumentText(curr.getP_type_document(), alias));
							curr.setT_r_city(Utils.toTranslitNew(curr.getCode_adr_distr_val()));
							curr.setP_post_address(Utils.toTranslitNew(curr.getP_post_address()));
							curr.setP_passport_place_registration(Utils.toTranslitNew(curr.getP_passport_place_registration()));
							curr.setP_birth_place(Utils.toTranslitNew(curr.getP_birth_place()));
							
							//2023-06-29 end
							copyOfCurrent = curr.clone(curr);
                            if (curr.getP_family()!=null){
							  //curr.setP_family(curr.getP_family().replaceAll("'", ""));
							  curr.setP_family(curr.getP_family().replaceAll("[-+.^:,!'‘”?*)(_/;]",""));
							  // !'‘;,”:/-()*?_
                            }
                            if (curr.getP_first_name()!=null)
  							  //curr.setP_first_name(curr.getP_first_name().replaceAll("'", ""));
                              curr.setP_first_name(curr.getP_first_name().replaceAll("[-+.^:,!'‘”?*)(_/;]",""));
                            if (curr.getP_patronymic()!=null)
  							  //curr.setP_patronymic(curr.getP_patronymic().replaceAll("'", ""));
                            	curr.setP_patronymic(curr.getP_patronymic().replaceAll("[-+.^:,!'‘”?*)(_/;]",""));
                            if (curr.getP_passport_place_registration() !=null)
                              	curr.setP_passport_place_registration(curr.getP_passport_place_registration().replaceAll("[-+.^:,!'‘”?*)(_/;]",""));

                            //if (curr.getP_phone_mobile()!=null && curr.getP_phone_mobile().length()==12 && curr.getP_phone_mobile().substring(0,3).equals("998") )
                            //	curr.setP_phone_mobile("+"+curr.getP_phone_mobile());
                            //else
                            	curr.setP_phone_mobile("+998");
							CheckNull.clearForm(add_everywhere$addgrdl);
							CheckNull.clearForm(add_everywhere$addgrdr);

							loadRefData();
							add_everywhere.setTitle("Добавление клиента [ТИЕТО]");
							add_everywhere.setVisible(true);
							binder.loadComponent(add_everywhere);
							
						}
					});
					btn.setStyle("padding: 0;");
					cell.appendChild(btn);
					row.appendChild(cell);
				} else {
					row.appendChild(new Listcell(""));
				}

				/*row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getId_tieto())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getT_client_b())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getT_surname())
						+ " "
						+ StringUtils.secureNull(vCustomer.getT_f_names())
						+ " " + StringUtils.secureNull(vCustomer.getT_m_name())));
				row.appendChild(new Listcell(
						vCustomer.getT_b_date() != null ? df.format(vCustomer
								.getT_b_date()) : ""));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getT_id_card())
						+ "/"
						+ StringUtils.secureNull(vCustomer.getT_person_code())));
				*/
				
			}
		});

		tietoGrid.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) {
				Customer vCustomer = (Customer) data;
				row.setAttribute("row", (Object) row);
				row.setValue((Object) vCustomer);

				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getId_tieto())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getT_client_b())));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getT_surname())
						+ " "
						+ StringUtils.secureNull(vCustomer.getT_f_names())
						+ " " + StringUtils.secureNull(vCustomer.getT_m_name())));
				row.appendChild(new Listcell(
						vCustomer.getT_b_date() != null ? df.format(vCustomer
								.getT_b_date()) : ""));
				row.appendChild(new Listcell(StringUtils.secureNull(vCustomer
						.getT_id_card())
						+ "/"
						+ StringUtils.secureNull(vCustomer.getT_person_code())));

			}
		});	
		
		accGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				AccInfo pAccInfo = (AccInfo) data;

				row.setValue(pAccInfo);
				row.setAttribute("row", (Object) row);

				row.appendChild(new Listcell(""));
				row.appendChild(new Listcell(pAccInfo.getId()));
				row.appendChild(new Listcell(statesNciAccount.get(pAccInfo
						.getAc_status())));
				row.appendChild(new Listcell("" + pAccInfo.getAccount_no()));
				row.appendChild(new Listcell(pAccInfo.getCard_acct()));
				row.appendChild(new Listcell(pAccInfo.getTranz_acct()));
				row.appendChild(new Listcell(pAccInfo.getCond_set()));
				row.appendChild(new Listcell(pAccInfo.getStatus()));

				row.appendChild(new Listcell(pAccInfo.getAcc_prty()));
				row.appendChild(new Listcell(pAccInfo.getC_accnt_type()));
				row.appendChild(new Listcell(pAccInfo.getCcy()));
				row.appendChild(new Listcell(pAccInfo.getClient_b()));
				row.appendChild(new Listcell(pAccInfo.getClient()));
				row.appendChild(new Listcell(pAccInfo.getF_names()));
				row.appendChild(new Listcell(pAccInfo.getSurname()));
				row.appendChild(new Listcell(pAccInfo.getB_br_id()));
				row.appendChild(new Listcell(pAccInfo.getOffice_id()));
				row.appendChild(new Listcell(pAccInfo.getMain_row()));
				row.appendChild(new Listcell(pAccInfo.getBank_c()));
				row.appendChild(new Listcell(pAccInfo.getGroupc()));

				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));

				// knopka otkrit shetevoy kontrakt/redaktirovat
				/*
				 * Listcell edit_contract_cell = new Listcell(); Toolbarbutton
				 * btedit_contr = new Toolbarbutton();
				 * btedit_contr.setLabel("");
				 * btedit_contr.setImage("/images/config.png");
				 * btedit_contr.setAttribute("contr_acc", (Object) pAccInfo);
				 * btedit_contr
				 * .setTooltiptext("Открыть/Редактировать счетовой контракт");
				 * btedit_contr.addEventListener("onClick", (EventListener) new
				 * EventListener() { public void onEvent(final Event event)
				 * throws Exception { curr_acc = (AccInfo) event.getTarget()
				 * .getAttribute("contr_acc"); // copyOfCurrent =
				 * current.clone(current); loadRefAccData();
				 * 
				 * add_account
				 * .setTitle("Редактирование счетовой контракт [Way4]");
				 * add_account.setVisible(true);
				 * binder.loadComponent(add_account); } });
				 * edit_contract_cell.appendChild((Component) btedit_contr);
				 * row.appendChild((Component) edit_contract_cell);
				 */
				// knopka end redaktirovat shetevoy kontrakt
			}
		});

		cardGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				CardInfo pAccInfo = (CardInfo) data;

				row.setValue(pAccInfo);
				row.appendChild(new Listcell(pAccInfo.getCARD()));
				//row.appendChild(new Listcell(pAccInfo.getCurrency()));
				//kartani tuliq(yashirinmagan) variantini ham olamiz
				Res res1 = CustomerService.getRealCard_tieto(pAccInfo.getCARD(), "", CustomerService.mapConst.get(Cons.url_getrealcard));
				if (res1.getCode()==0) {
					row.appendChild(new Listcell(res1.getName()));
				} else
				{
					row.appendChild(new Listcell("Error getting realcard..."));
				}
				row.appendChild(new Listcell(pAccInfo.getCARD_NAME()));
				row.appendChild(new Listcell(pAccInfo.getBASE_SUPP()));
				//row.appendChild(new Listcell(pAccInfo.getSTATUS()));
				// --deystviya s kartami
				Listcell card_status_cell = new Listcell();
				//card_status_cell.setStyle("text-align: left");
				card_status_cell.setStyle("white-space: normal; word-wrap: break-word; width: 150px;");
				Toolbarbutton btblock_card = new Toolbarbutton();
				btblock_card.setTooltiptext("Блокировать карту");
				btblock_card.setImage("/images/lock1.png");
				btblock_card.setAttribute("card", (Object) pAccInfo);

				btblock_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								lockCardMethod(card1);
							}
						});
				if (pAccInfo.isSign_reissued())
					btblock_card.setDisabled(true);
				card_status_cell.appendChild((Component) btblock_card);
				
				Toolbarbutton btUnlock_card = new Toolbarbutton();
				btUnlock_card.setTooltiptext("Разблокировать карту");
				btUnlock_card.setImage("/images/unlock1.png");
				btUnlock_card.setAttribute("card", (Object) pAccInfo);

				btUnlock_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								unlockCardMethod(card1);

							}
						});
				if (pAccInfo.isSign_reissued())
					btUnlock_card.setDisabled(true);
				card_status_cell.appendChild((Component) btUnlock_card);

				Toolbarbutton btReissue_card = new Toolbarbutton();
				btReissue_card.setTooltiptext("Перевыпуск карту");
				btReissue_card.setImage("/images/paycheck.png");
				btReissue_card.setAttribute("card", (Object) pAccInfo);

				btReissue_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								reIssueCardMethod(card1);

							}
						});
				if (pAccInfo.isSign_reissued())
					btReissue_card.setDisabled(true);
				card_status_cell.appendChild((Component) btReissue_card);

				Toolbarbutton btResetPIN_card = new Toolbarbutton();
				btResetPIN_card.setTooltiptext("Сбросить счетчик ПИН");
				btResetPIN_card.setImage("/images/resetpin.png");
				btResetPIN_card.setAttribute("card", (Object) pAccInfo);

				btResetPIN_card.addEventListener("onClick",
						(EventListener) new EventListener() {
							public void onEvent(final Event event)
									throws Exception {
								CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
								//alert(card1.getCARD_NAME());
								//reIssueCardMethod(card1);ваыв;
								resetPINMethod(card1);
							}
						});
				if (pAccInfo.isSign_reissued())
					btResetPIN_card.setDisabled(true);
				card_status_cell.appendChild((Component) btResetPIN_card);
				
				Label lbl = new Label();
				lbl.setValue(statesTieto.get(pAccInfo.getSTATUS()));
				card_status_cell.appendChild((Component) lbl);
				

				Toolbarbutton cardActionsHistory = new Toolbarbutton();
		        cardActionsHistory.setImage("/images/folder.png");
		        cardActionsHistory.setLabel("История действий по карте");
		        cardActionsHistory.setDisabled(false);
		        cardActionsHistory.setAttribute("card", (Object) pAccInfo);
		        cardActionsHistory.addEventListener("onClick", new EventListener() {
		                public void onEvent(Event event) throws Exception {
		                	CardInfo card1 = (CardInfo) event.getTarget().getAttribute("card");
		                   if (card1 == null) {
		                      AddCstViewCtrl.this.alert("Выберите карту");
		                   } else {
		                      List<CardActions> cardActions = CustomerService.getCardActions(card1.getCARD(), AddCstViewCtrl.this.branch);
		                      if (cardActions == null) {
		                         AddCstViewCtrl.this.alert("Нет информации по действиям с данной картой");
		                         return;
		                      }

		                      AddCstViewCtrl.this.cardActionsWindow$cardActionsListbox.setModel(new BindingListModelList(cardActions, true));
		                      AddCstViewCtrl.this.cardActionsWindow.setVisible(true);
		                   }

		                }
		             });
		            
		        cardActionsHistory.setParent(card_status_cell);
		        cardActionsHistory.setVisible(true);
		        card_status_cell.appendChild(cardActionsHistory);
				
				row.appendChild((Component) card_status_cell);
				// -- end deystviya po kartam

				// --sms ulash
				Listcell card_sms_cell = new Listcell();
				//card_sms_cell.setStyle("text-align: left");
				final Longbox lbox = new Longbox();
				lbox.setMaxlength(9);
				final Label label = new Label("Телефон: +998");
				card_sms_cell.appendChild((Component) label);
				final Long phoneNumber = CustomerService.getCardSmsPhoneNumber(pAccInfo.getCARD());
				if (phoneNumber != null) {
					lbox.setValue(Long.parseLong(phoneNumber.toString().substring(3)));
					lbox.setDisabled(true);
				}
				card_sms_cell.appendChild((Component) lbox);
				final Button btnSmsOn = new Button("on");
				btnSmsOn.setTooltiptext("Подключить СМС услугу");
				btnSmsOn.setAttribute("cardInfo", (Object) pAccInfo);
				btnSmsOn.setAttribute("lbox", (Object) lbox);
				btnSmsOn.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						// Привязка телефона к карте. Подключение СМС
						// уведомлений на номер
						activateSms(event);
					}
				});
				if (pAccInfo.isSign_reissued())
					btnSmsOn.setDisabled(true);
				card_sms_cell.appendChild((Component) btnSmsOn);
				
				final Button btnSmsOff = new Button("off");
				btnSmsOff.setAttribute("cardInfo", (Object) pAccInfo);
				btnSmsOff.setAttribute("lbox", (Object) lbox);
				btnSmsOff.addEventListener("onClick", (EventListener) new EventListener() {
					public void onEvent(final Event event) throws Exception {
						// Отвязка телефона от карты. Отключение СМС уведомлений
						// с номера
						deActivateSms(event);
					}
				});		
				if (pAccInfo.isSign_reissued())
					btnSmsOff.setDisabled(true);
				card_sms_cell.appendChild((Component) btnSmsOff);
				row.appendChild((Component) card_sms_cell);
				// -- end sms uvedomleniya
				
				row.appendChild(new Listcell(stopCausesTieto.get(pAccInfo.getSTOP_CAUSE())));
				row.appendChild(new Listcell(pAccInfo.getEXPIRY1()));
				row.appendChild(new Listcell(pAccInfo.getCOND_SET()));
				row.appendChild(new Listcell(pAccInfo.getRISK_LEVEL()));
				row.appendChild(new Listcell(pAccInfo.getClient_id()));
				row.appendChild(new Listcell(pAccInfo.getAGREEMENT_KEY()));
				row.appendChild(new Listcell(pAccInfo.getACCOUNT_NO()));
				row.appendChild(new Listcell(pAccInfo.getCARD_ACCT()));
				row.appendChild(new Listcell(statesTieto.get(pAccInfo.getSTATUS2())));
				row.appendChild(new Listcell(pAccInfo.getEXPIRY2()));
				row.appendChild(new Listcell(pAccInfo.getCL_ROLE()));
				row.appendChild(new Listcell(pAccInfo.getBANK_C()));
				row.appendChild(new Listcell(pAccInfo.getGROUPC()));
				row.appendChild(new Listcell(pAccInfo.getICO_EXPORT()));

				// row.appendChild(new
				// Listcell(_tstopCauses.get(pAccInfo.getStatus1())));

				// knopka otkrit kartochniy kontrakt/redaktirovat

				// knopka end redaktirovat карточный kontrakt
			}
		});
		
	    this.cardActionsWindow$cardActionsListbox.setItemRenderer(new ListitemRenderer() {
	          public void render(Listitem row, Object data) throws Exception {
	             CardActions cardActions = (CardActions)data;
	             row.setValue(cardActions == null ? "" : cardActions);
	             row.appendChild(new Listcell(cardActions.getUser() == null ? "" : cardActions.getUser()));
	             row.appendChild(new Listcell(cardActions.getDate() == null ? "" : cardActions.getDate()));
	             row.appendChild(new Listcell(cardActions.getAction() == null ? "" : cardActions.getAction()));
	          }
	    });
		binder.loadAll();
	}

	private void lockCardMethod(CardInfo card1) {
		lock_card_wnd.setTitle("Блокировка карты");
		lock_card_wnd$row_stop_cause.setVisible(true);
		lock_card_wnd$lock_card_btn.setVisible(true);
		lock_card_wnd$unlock_card_btn.setVisible(false);
		lock_card_wnd.setAttribute("card_num", card1.getCARD());
		if (lock_card_wnd$rcb_stop_cause.getItems().size() == 0)
			lock_card_wnd$rcb_stop_cause.setModel(new ListModelList(
					//CustomerService.getListStopCauses(alias)));
					CustomerService.getListLockStopCauses(alias)));
		lock_card_wnd.setVisible(true);	
		binder.loadComponent(lock_card_wnd);
	}

	private void unlockCardMethod(CardInfo card1) {

		lock_card_wnd.setTitle("Разблокировать карту");
		lock_card_wnd$row_stop_cause.setVisible(false);
		lock_card_wnd$lock_card_btn.setVisible(false);
		lock_card_wnd$unlock_card_btn.setVisible(true);
		
		lock_card_wnd.setAttribute("card_num", card1.getCARD());
		
		lock_card_wnd.setVisible(true);	
		binder.loadComponent(lock_card_wnd);
	}
	
	private void reIssueCardMethod(CardInfo card1) {
				
		reissue_card_wnd.setAttribute("card", card1);
		if (reissue_card_wnd$card_risk_level.getItems().size() == 0)
			reissue_card_wnd$card_risk_level.setModel(new ListModelList(
					CustomerService.getRiskLevels(alias)));
		if (reissue_card_wnd$card_design.getItems().size() == 0)
			reissue_card_wnd$card_design.setModel(new ListModelList(
					CustomerService.getDesigns(alias)));
		
		System.out.println("size1="+reissue_card_wnd$card_risk_level.getItems().size()+ " , size2="+reissue_card_wnd$card_design.getItems().size());
		//int a=0;
		//while((reissue_card_wnd$card_risk_level.getItems().size() == 0 || reissue_card_wnd$card_design.getItems().size() == 0) && a<10 )
		//{
		//	try {
		//		  Thread.sleep(400);
		//		} catch (InterruptedException e) {
		//		  Thread.currentThread().interrupt();
		//		}
		//		binder.loadComponent(reissue_card_wnd);
        //     a=a+1;
        //     System.out.println("a="+a);
        //     System.out.println("size1="+reissue_card_wnd$card_risk_level.getItems().size()+ " , size2="+reissue_card_wnd$card_design.getItems().size());
		//}

		if (new_card==null)  
			new_card = new CardInfo();
		reissue_card_wnd$card_risk_level.setSelecteditem(card1.getRISK_LEVEL());
		new_card.setRISK_LEVEL(card1.getRISK_LEVEL());

		if (card1.getDesign_id()!=null) {  
			reissue_card_wnd$card_design.setSelecteditem(card1.getDesign_id());
			new_card.setDesign_id(card1.getDesign_id());
		}
		else{ 
			reissue_card_wnd$card_design.setSelecteditem( CustomerService.getDesignIdByCardCondSet( card1.getCOND_SET(), alias));
			new_card.setDesign_id(CustomerService.getDesignIdByCardCondSet( card1.getCOND_SET(), alias));
		}
		System.out.println("size1="+reissue_card_wnd$card_risk_level.getItems().size()+ " , size2="+reissue_card_wnd$card_design.getItems().size());
		binder.loadComponent(reissue_card_wnd);
		reissue_card_wnd.setVisible(true);
        System.out.println("size1="+reissue_card_wnd$card_risk_level.getItems().size()+ " , size2="+reissue_card_wnd$card_design.getItems().size());		
	}
	
	//bu komponentni visible=false qildik. shuningchun comment qilamiz
	//public void onFocus$card_expiry$reissue_card_wnd() {
	//	// birinchi marta
	//	// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
	//	// buladi ushanda
	//	// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
	//	// binder.loadcomponent qilamiz bir marta
	//	if (reissue_card_wnd$card_risk_level.getItems().size() == 0
	//			|| (reissue_card_wnd$card_risk_level.getValue() != null )) {
	//		binder.loadComponent(reissue_card_wnd);
	//	}
	//}
	
	public void onFocus$card_risk_level$reissue_card_wnd() {
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		if (reissue_card_wnd$card_risk_level.getItems().size() == 0
				|| (reissue_card_wnd$card_risk_level.getValue() != null )) {
			//System.out.println("a size1="+reissue_card_wnd$card_risk_level.getItems().size()+ " , size2="+reissue_card_wnd$card_design.getItems().size());
			binder.loadComponent(reissue_card_wnd);
		}
		//System.out.println("aa size1="+reissue_card_wnd$card_risk_level.getItems().size()+ " , size2="+reissue_card_wnd$card_design.getItems().size());
	}
	
	private void resetPINMethod(CardInfo card1) {

		boolean fl_err = false;
		String err = "";
		String v_msg = "";

       
		if (card1 == null || card1.getCARD() == null) {
			alert("Карта не выбрана!" );
			return;
		}
		
		RowType_ResetPINCounter_Request resetPINRequest = new RowType_ResetPINCounter_Request();
		resetPINRequest.setCARD(card1.getCARD());
		try{
			Date myDate=df.parse(card1.getEXPIRY1());
			//Date myDate=df.parse(card1.getEXPIRY1().substring(0, card1.getEXPIRY1().indexOf("T")));
			resetPINRequest.setEXPIRY(sdfs.format(myDate));
		} catch (ParseException e) {
			System.out.println(
					"error getting expiry1 field of card: "
							+ e.getMessage());

			ISLogger.getLogger().error(
					"error getting expiry1 field of card: "
							+ e.getMessage());
		}
		resetPINRequest.setTEXT("unlock PIN by Tieto-Visa module IS");
		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(resetPINRequest);
		} catch (Exception e) {
			System.out.println("error serialize json from ResetPINCounter: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from ResetPINCounter: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			
			return;
		}
		ISLogger.getLogger().error("ResetPINCounter query json text: " + v_json);
		System.out.println("ResetPINCounter query json text: " + v_json);

		String v_res = "";
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_resetpincounter), v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"ResetPINCounter postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(ResetPINCounter) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("ResetPINCounter response = " + v_res);
		System.out.println("ResetPINCounter response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError ResetPINCounter returning empty string.";
			this.alert(v_msg);
			return;
		}
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		CommonResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res, CommonResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"ResetPINCounter response mapper err: " + e.getMessage());
			System.out.println("ResetPINCounter response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError ResetPINCounter response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			return;
		}
		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
			
			if (rslt.getOperResponseInfo() != null && rslt.getOperResponseInfo().getResponse_code()==BigInteger.valueOf(0)){
				CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Сброшена ПИН счетчик карты No [" + card1.getCARD() + "]."  ));				
				v_msg = v_msg + "\nСброшена ПИН счетчик карты успешно! ";
			}
			else {
				v_msg = v_msg
				+ "\nОшибка сброса ПИН счетчик карты: "
				+ rslt.getOperResponseInfo().getResponse_code()+" : " + rslt.getOperResponseInfo().getError_description();
				this.alert(v_msg);
				return;
			}

			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg
					+ "\nОшибка сброса ПИН счетчик карты: "
					+ rslt.getErr_text();
			this.alert(v_msg);
			return;
		}
		this.alert(v_msg);
		//onSelect$accGrid();
	}
	
	 private void activateSms(final Event event) throws ParseException{
	    	
			//boolean fl_err = false;
			//String err = "";
			String v_msg = "";
			String v_res = "";

			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("cardInfo");
			final Longbox lbox = (Longbox) btn.getAttribute("lbox");
			if (lbox == null) {
				alert("Введите номер телефона!");
				return;
			}
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
			if (lbox.getValue() == null
					|| lbox.getValue().toString().length() != 9
					|| !lbox.getValue().toString().matches("[0-9]+")) {
				this.alert("Неверный номер телефона " + lbox.getValue());
				return;
			}
			//agar tietodagi tel raqami hozir kiritilgandan boshqa bulsa editcustomer qilib nomerni uzgartiramiz
			if (curr_tieto.getT_rmob_phone()==null || !curr_tieto.getT_rmob_phone().equals("+998"	+ lbox.getValue())) {
				String currPhone=curr_tieto.getT_rmob_phone();
				ISLogger.getLogger().error(
						"EditCustomer(mobilePhone). " + curr_tieto.getId_tieto()+" : "+ curr_tieto.getT_rmob_phone() +" -> "+ "+998" + lbox.getValue());
				
				RowType_EditCustomer_Request updClient = null;
				curr_tieto.setT_rmob_phone("+998" + lbox.getValue());
				updClient = com.is.tietovisa.customer.CustomerService.makeEditClientMobilePhone(
						curr_tieto, alias);
				PostUtils postUtils = new PostUtils();
				ObjectMapper mapper = new ObjectMapper();
				String v_json = "_";
				try {
					v_json = mapper.writerWithDefaultPrettyPrinter()
							.writeValueAsString(updClient);
				} catch (Exception e) {
					ISLogger.getLogger().error(
							"EditCustomer(mobilePhone) serialize json error: " + e.getMessage());
					v_msg = v_msg + "\nError serialize json " + e.getMessage();
				} finally {
				}
				if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
					this.alert(v_msg);
					curr_tieto.setT_rmob_phone(currPhone);
					return;
				}
				ISLogger.getLogger().error("EditCustomer(mobilePhone) query json text: " + v_json);
				
				try {
					v_res = postUtils
							.sendData(
									CustomerService.mapConst.get(Cons.url_editcustomer),
									v_json);
				} catch (Exception e) {
					ISLogger.getLogger().error(
							"EditCustomer(mobilePhone) postUtils.sendData err: " + e.getMessage());
					v_msg = v_msg + "\nError postUtils.sendData(updClient) "
							+ e.getMessage();
				}
				ISLogger.getLogger().error("EditCustomer(mobilePhone) response = " + v_res);
				if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
					this.alert(v_msg);
					curr_tieto.setT_rmob_phone(currPhone);
					return;
				}
				if ((v_res == "" || v_res.equals(""))) {
					v_msg = v_msg
							+ "\nError EditCustomer(mobilePhone) returning empty string.";
					this.alert(v_msg);
					curr_tieto.setT_rmob_phone(currPhone);
					return;
				}
				// kelgan javobdagi json-stringdan java object (klass) yasaymiz
				EditCustomerResponse editRslt = null;
				try {
					editRslt = mapper.readValue(v_res, EditCustomerResponse.class);
				} catch (Exception e) {
					ISLogger.getLogger().error(
							"EditCustomer(mobilePhone) response mapper err: "
									+ e.getMessage());
					v_msg = v_msg
							+ "\nError EditCustomer(mobilePhone) response mapper: "
							+ e.getMessage();
					this.alert(v_msg);
					curr_tieto.setT_rmob_phone(currPhone);
					return;
				}
				ISLogger.getLogger().error(editRslt.toString());
				if (editRslt.getErr_code() == "0" || editRslt.getErr_code().equals("0")) /* success */{
					v_msg = v_msg
							+ "\nКлиент(номер мобильного телефон) редактирован в TIETO! Успешный ответ из TIETO.";
				} else {
					v_msg = v_msg + "\nОшибка редактирования клиента(номер мобильного телефона) в TIETO: "
							+ editRslt.getErr_text();
					this.alert(v_msg);
					curr_tieto.setT_rmob_phone(currPhone);
					return;
				}
				//this.alert(v_msg);
			}
			//end editcustomer mobilephone
			
			String cardNumber;
			Res res = CustomerService.getRealCard_tieto(cardInfo.getCARD(), "", CustomerService.mapConst.get(Cons.url_getrealcard));
			if (res.getCode()==0) {
				cardNumber=res.getName();
			} else
			{
				this.alert("Не удалось получить номер карты " );
				return;
			}
			
			String cardLabel="";
			if (cardNumber.substring(0, 10).equals("4187800000"))
				cardLabel= "VISA Debet Classic";
            else if (cardNumber.substring(0, 10).equals("4187800030"))
            	cardLabel= "VISA Debet Gold";
            else if (cardNumber.substring(0, 10).equals("4187800060"))
            	cardLabel= "VISA Debet Platinum";
            else {
            	Cond_card cond_card= CustomerService.getCond_cardByCond( cardInfo.getCOND_SET(), alias);
            	if (cond_card.getName()!=null) 
            		cardLabel= cond_card.getName();
            	else
            		cardLabel="Card";
            }
			
			ISLogger.getLogger().error("ACTIVATE SMS:" + "\nPHONE_NUMBER: "
					+ lbox.getValue() + "\nEXPIRY: " + cardInfo.getEXPIRY1()
					+ "\nEXPIRYSTRING: " + cardInfo.getEXPIRY1().substring(5, 7)
					+ cardInfo.getEXPIRY1().substring(2, 4) + "\nSTATE_CARD: "
					+ btn.getLabel() + "\nCARD_NUMBER: " + cardNumber
					+ "\nTIETO_ID: " + cardInfo.getClient()
					+ "\nCUSTOMER_NAME: " + cardInfo.getCARD_NAME()
					+ "\nCARDLABEL: " + cardLabel
			);
	        		
			String v_xml;
			v_xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>                                      "
				+"<SOAP-ENV:Envelope                                                               "
				+"xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"                     "
				+"xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"                     "
				+"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"                          "
				+"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"                                   "
				+"xmlns:ag=\"urn:AccessGateway\">                                                  "
				+"<SOAP-ENV:Body>                                                                  "
				+"<ag:import>                                                                      "
				+"<state>on</state>                                                                "
				+"<cardholderID>TIETOID</cardholderID>                                              " //177303 //tietoid  
				+"<cardholderName>CUSTOMERNAME</cardholderName>                                "  //Dilshod Shakarov
				+"<language>ru_translit</language>                                                 "
				+"<Notification>                                                                   "
				+"<timeZone>GMT+5:00</timeZone>                                                    "
				+"<timeInterval>00:00-23:59</timeInterval>                                         "
				+"<sender></sender>                                                                "
				+"</Notification>                                                                  "
				+"<Charge>                                                                         "
				+"<agreementCharge>MONTH.FEE.OFF</agreementCharge>                                 "
				+"</Charge>                                                                        "
				+"<Card>                                                                           "
				+"<state>on</state>                                                                "
				+"<pan>CARDNUMBER</pan>                                                      " //4187800000577379
				+"<expiry>CARDEXPIRY</expiry>                                                            " //2608
				+"<label>CARDLABEL</label>                                                " //Visa Debit Classik
				+"<Service>                                                                        "
				+"<serviceID>MB-ALL</serviceID>                                                    "
				+"</Service>                                                                       "
				+"</Card>                                                                          "
				+"<Phone>                                                                          "
				+"<state>on</state>                                                                "
				+"<msisdn>PHONENUMBER</msisdn>                                                   " //+998939990988
				+"<deliveryChannel>OUTGOING_CH_1</deliveryChannel>                                 "
				+"</Phone>                                                                         "
				+"</ag:import>                                                                     "
				+"</SOAP-ENV:Body>                                                                 "
				+"</SOAP-ENV:Envelope>                                                             ";
			v_xml=v_xml.replace("TIETOID",  cardInfo.getClient());																					   
			v_xml=v_xml.replace("CUSTOMERNAME",  cardInfo.getCARD_NAME());
			v_xml=v_xml.replace("CARDNUMBER",  cardNumber);
			v_xml=v_xml.replace("CARDEXPIRY",  cardInfo.getEXPIRY1().substring(5, 7) + cardInfo.getEXPIRY1().substring(2, 4));

			v_xml=v_xml.replace("CARDLABEL",  cardLabel);
			v_xml=v_xml.replace("PHONENUMBER",  "+998"	+ lbox.getValue());

			ISLogger.getLogger().error("smsservice request = " + v_xml);
			System.out.println("smsservice request = " + v_xml);
			
			PostUtils postUtils = new PostUtils();
			try {
				v_res = postUtils.sendDataAuth(
						CustomerService.mapConst.get(Cons.url_smsservice), CustomerService.mapConst.get(Cons.smsservice_login), CustomerService.mapConst.get(Cons.smsservice_password), v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"smsservice postUtils.sendData err: " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(smsservice) "
						+ e.getMessage();
			}
			ISLogger.getLogger().error("smsservice response = " + v_res);
			System.out.println("smsservice response = " + v_res);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
			if ((v_res == "" || v_res.equals(""))) {
				v_msg = v_msg + "\nError SNS service returning empty string.";
				this.alert(v_msg);
				return;
			}


			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
			// qilamiz
			//<ag1:importResponse></ag1:importResponse>
			if (v_res.contains("<ag1:importResponse></ag1:importResponse>")) {
				CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Подключение СМС уведомлений по карте No [" + cardInfo.getCARD() + "]. Действие: СМС ON. "+"+998" + lbox.getValue()  ));
				v_msg = v_msg + "\nСМС подключено успешно! ";
				Res res1 = CustomerService.insertCardSmsState(cardInfo.getCARD(), "998"+lbox.getValue(), 1, alias );
				if (res1.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res1.getName();
					//this.alert(v_msg);
					//return;
				}
			} else {
					v_msg = v_msg
							+ "\nОшибка подключения СМС услугу : "
							+ v_res;
					this.alert(v_msg);
					return;
			}
			onSelect$tietoGrid();
			this.alert(v_msg);
		}
	
	
	 private void deActivateSms(final Event event) throws ParseException {
	    	
			boolean fl_err = false;
			String err = "";
			String v_msg = "";

			final Button btn = (Button) event.getTarget();
			final CardInfo cardInfo = (CardInfo) btn.getAttribute("cardInfo");
			final Longbox lbox = (Longbox) btn.getAttribute("lbox");
			
			if (cardInfo == null) {
				alert("Не удалось выполнить запрос. Повторите снова");
				return;
			}
			String cardNumber;
			Res res = CustomerService.getRealCard_tieto(cardInfo.getCARD(), "", CustomerService.mapConst.get(Cons.url_getrealcard));
			if (res.getCode()==0) {
				cardNumber=res.getName();
			} else
			{
				this.alert("Не удалось получить номер карты " );
				return;
			}
			
			String cardLabel="";
			if (cardNumber.substring(0, 10).equals("4187800000"))
				cardLabel= "VISA Debet Classic";
         else if (cardNumber.substring(0, 10).equals("4187800030"))
         	cardLabel= "VISA Debet Gold";
         else if (cardNumber.substring(0, 10).equals("4187800060"))
         	cardLabel= "VISA Debet Platinum";
         else {
         	Cond_card cond_card= CustomerService.getCond_cardByCond( cardInfo.getCOND_SET(), alias);
         	if (cond_card.getName()!=null) 
         		cardLabel= cond_card.getName();
         	else
         		cardLabel="Card";
         }
			
			ISLogger.getLogger().error("DEACTIVATE SMS:" + "\nPHONE_NUMBER: "
					+ lbox.getValue() + "\nEXPIRY: " + cardInfo.getEXPIRY1()
					+ "\nEXPIRYSTRING: " + cardInfo.getEXPIRY1().substring(5, 7)
					+ cardInfo.getEXPIRY1().substring(2, 4) + "\nSTATE_CARD: "
					+ btn.getLabel() + "\nCARD_NUMBER: " + cardNumber
					+ "\nTIETO_ID: " + cardInfo.getClient()
					+ "\nCUSTOMER_NAME: " + cardInfo.getCARD_NAME()
					+ "\nCARDLABEL: " + cardLabel
			);
	        		
			String v_xml;
			v_xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>                            "
				+"<SOAP-ENV:Envelope                                                   "
				+"xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"         "
				+"xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"         "
				+"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"              "
				+"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"                       "
				+"xmlns:ag=\"urn:AccessGateway\">                                      "
				+"<SOAP-ENV:Body>                                                      "
				+"<ag:import>                                                          "
				+"<state>off</state>                                                   "
				+"<cardholderID>TIETOID</cardholderID>                                  " //177303
				+"<cardholderName>CUSTOMERNAME</cardholderName>                    " //DILSHOD SHAKAROV
				+"<language>ru_translit</language>                                     "
				+"<Notification>                                                       "
				+"<timeZone>GMT+5:00</timeZone>                                        "
				+"<timeInterval>00:00-23:59</timeInterval>                             "
				+"<sender></sender>                                                    "
				+"</Notification>                                                      "
				+"<Charge>                                                             "
				+"<agreementCharge>MONTH.FEE.OFF</agreementCharge>                     "
				+"</Charge>                                                            "
				+"<Card>                                                               "
				+"<state>off</state>                                                   "
				+"<pan>CARDNUMBER</pan>                                          " //4187800030538052
				+"<expiry>CARDEXPIRY</expiry>                                                " //2606
				+"<label>CARDLABEL</label>                                             " //Gold Visa
				+"<Service>                                                            "
				+"<serviceID>MB-ALL</serviceID>                                        "
				+"</Service>                                                           "
				+"</Card>                                                              "
				+"<Phone>                                                              "
				+"<state>off</state>                                                   "
				+"<msisdn>PHONENUMBER</msisdn>                                       " //+998939990988
				+"<deliveryChannel>OUTGOING_CH_1</deliveryChannel>                     "
				+"</Phone>                                                             "
				+"</ag:import>                                                         "
				+"</SOAP-ENV:Body>                                                     "
				+"</SOAP-ENV:Envelope>                                                 ";
			
			v_xml=v_xml.replace("TIETOID",  cardInfo.getClient());																					   
			v_xml=v_xml.replace("CUSTOMERNAME",  cardInfo.getCARD_NAME());
			v_xml=v_xml.replace("CARDNUMBER",  cardNumber);
			v_xml=v_xml.replace("CARDEXPIRY",  cardInfo.getEXPIRY1().substring(5, 7) + cardInfo.getEXPIRY1().substring(2, 4));

			v_xml=v_xml.replace("CARDLABEL",  cardLabel);
			v_xml=v_xml.replace("PHONENUMBER",  "+998"	+ lbox.getValue());

			ISLogger.getLogger().error("smsservice request = " + v_xml);
			System.out.println("smsservice request = " + v_xml);
			
			PostUtils postUtils = new PostUtils();

			String v_res = "";
			try {
				v_res = postUtils.sendDataAuth(
						CustomerService.mapConst.get(Cons.url_smsservice), CustomerService.mapConst.get(Cons.smsservice_login), CustomerService.mapConst.get(Cons.smsservice_password), v_xml);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"smsservice postUtils.sendData err: " + e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(smsservice) "
						+ e.getMessage();
			}
			ISLogger.getLogger().error("smsservice response = " + v_res);
			System.out.println("smsservice response = " + v_res);

			if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
				this.alert(v_msg);
				return;
			}
			if ((v_res == "" || v_res.equals(""))) {
				v_msg = v_msg + "\nError SNS service returning empty string.";
				this.alert(v_msg);
				return;
			}


			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
			// qilamiz
			//<ag1:importResponse></ag1:importResponse>
			if (v_res.contains("<ag1:importResponse></ag1:importResponse>")) {
				CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Отключение СМС уведомлений по карте No [" + cardInfo.getCARD() + "]. Действие: СМС OFF."  ));
				v_msg = v_msg + "\nСМС отключено успешно! ";
				Res res1 = CustomerService.insertCardSmsState(cardInfo.getCARD(), "998"+lbox.getValue(), 2, alias );
				if (res1.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res1.getName();
					//this.alert(v_msg);
					//return;
				}
			} else {
					v_msg = v_msg
							+ "\nОшибка отключения СМС услугу : "
							+ v_res;
					this.alert(v_msg);
					return;
			}
			onSelect$tietoGrid();
			this.alert(v_msg);
			
		}
	

	 
	public boolean isFilterNull(CustomerFilter filter) {
		if ((filter.getId_client() == null || filter.getId_client().equals(""))
				&& (filter.getP_pinfl() == null || filter.getP_pinfl().equals(
						""))
				&& (filter.getP_first_name() == null || filter
						.getP_first_name().equals(""))
				&& (filter.getP_family() == null || filter.getP_family()
						.equals(""))
				&& (filter.getP_birthday() == null || filter.getP_birthday()
						.equals(""))
				&& (filter.getP_phone_mobile() == null || filter
						.getP_phone_mobile().equals(""))
				&& (filter.getId_tieto() == null || filter.getId_tieto()
						.equals(""))
				&& (filter.getT_client_b() == null || filter.getT_client_b()
						.equals("")))
			return true;
		else
			return false;
	}

	public void onClick$nbtn_search() {

		/*
		 * if ( (filter.getId_client()==null ||filter.getId_client().equals(""))
		 * && (filter.getP_pinfl()==null ||filter.getP_pinfl().equals("")) &&
		 * (filter.getP_first_name()==null
		 * ||filter.getP_first_name().equals("")) && (filter.getP_family
		 * ()==null ||filter.getP_family().equals("")) && (filter.getP_birthday
		 * ()==null ||filter.getP_birthday().equals("")) &&
		 * (filter.getP_phone_mobile ()==null
		 * ||filter.getP_phone_mobile().equals("")) &&
		 * (filter.getId_tieto()==null ||filter.getId_tieto().equals("")) &&
		 * (filter.getClient_b()==null ||filter.getClient_b().equals("")) ) {
		 * alert("Введите хотябы один параметр поиска!"); return; }
		 */

		
		//ISLogger.getLogger().error(	"onClick$nbtn_search start! ");

        /*ISLogger.getLogger().error(
		  "onClick$nbtn_search filter : "
				+ txbId_client.getValue()+"-"+filter.getId_client()+ " !!! " 
				+ txbName.getValue()+"-"+filter.getP_first_name()+ " !!! " 
				+ txbLastName.getValue()+"-"+filter.getP_family()+ " !!! " 
				+ dbxB_date.getValue()+"-"+filter.getP_birthday()+ " !!! " 
				+ txbPinfl.getValue()+"-"+filter.getP_pinfl()+ " !!! "	);*/

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		filter.setFilter_type("N");
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);

		ISLogger.getLogger().error(
				"onClick$nbtn_search end! ");
	}
	
	
	public void onClick$btn_clear_filter() {
		filter = new CustomerFilter();
		binder.loadComponent(tfgrd);
	}

	public void onClick$abtn_search() {

		/*
		 * if ( (filter.getId_client()==null ||filter.getId_client().equals(""))
		 * && (filter.getP_pinfl()==null ||filter.getP_pinfl().equals("")) &&
		 * (filter.getP_first_name()==null
		 * ||filter.getP_first_name().equals("")) && (filter.getP_family
		 * ()==null ||filter.getP_family().equals("")) && (filter.getP_birthday
		 * ()==null ||filter.getP_birthday().equals("")) &&
		 * (filter.getP_phone_mobile ()==null
		 * ||filter.getP_phone_mobile().equals("")) &&
		 * (filter.getId_tieto()==null ||filter.getId_tieto().equals("")) &&
		 * (filter.getClient_b()==null ||filter.getClient_b().equals("")) ) {
		 * alert("Введите хотябы один параметр поиска!"); return; }
		 */

		ISLogger.getLogger().error(
		"onClick$abtn_search start! ");

		ISLogger.getLogger().error(
		"onClick$abtn_search filter : "
				+ txbId_client.getValue()+"-"+filter.getId_client()+ " !!! " 
				+ txbName.getValue()+"-"+filter.getP_first_name()+ " !!! " 
				+ txbLastName.getValue()+"-"+filter.getP_family()+ " !!! " 
				+ dbxB_date.getValue()+"-"+filter.getP_birthday()+ " !!! " 
				+ txbPinfl.getValue()+"-"+filter.getP_pinfl()+ " !!! "	);

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		filter.setFilter_type("A");
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
		ISLogger.getLogger().error(
				"onClick$abtn_search end! ");
						

	}

	public void onClick$tbtn_search() {

		if ((filter.getP_first_name() == null || filter.getP_first_name()
				.equals(""))
				&& (filter.getP_family() == null || filter.getP_family()
						.equals(""))
				&& (filter.getP_birthday() == null || filter.getP_birthday()
						.equals(""))
				&& (filter.getP_phone_mobile() == null || filter
						.getP_phone_mobile().equals(""))
				&& (filter.getId_tieto() == null || filter.getId_tieto()
						.equals(""))
				&& (filter.getT_client_b() == null || filter.getT_client_b()
						.equals(""))) {
			alert("Введите хотябы один поле по Tieto!");
			return;
		}

		if (filter.getBranch() == null)
			filter.setBranch(branch);
		filter.setFilter_type("T");
		// this.filter.setId_client("60000001");
		// this.filter.setP_pinfl("56789012340078");
		// this.bfilter = this.filter;
		this.filter.setEndpoint(listCustomerEndpoint);
		this.refreshModel(/* this._startPageNumber */0);
	}

	private void refreshModel(final int activePage) {
		// nci bankdan va tietodan klientlar ruyxatini olish
		this.model = new PagingListModel(activePage, this._pageSize,
				this.filter, this.alias);
		//System.out.println(this.model);
		// tietodan ham olamiz va ikkala natijani birlashtirib kursatamiz
		// List<Customer> tieto_customes =
		// CustomerService.getCustomers_tieto(this.filter, this.alias,
		// openwayEndpoint,
		// true);
		// System.out.println(tieto_customes);
		this.branch_customers.setModel((ListModel) this.model);
		if (model.getSize() > 0) {
			branch_customers.setSelectedIndex(0);
			sendSelEvt();
		}
		//ISLogger.getLogger().error(	"refreshModel end! ");

	}

	public void onOkToFilter(Event event) {
		onClick$nbtn_search();
	}

	public void onClick$btn_add_everywhere() {
		curr = new Customer();
		this.add_tie = true;
		this.add_bnk = true;

		CheckNull.clearForm(this.add_everywhere$addgrdr);
		CheckNull.clearForm(this.add_everywhere$addgrdl);

		this.add_everywhere.setTitle("Создание клиента [NCIBANK] - [TIETO]");

		// loaddata qilamiz shu yerda
		loadRefData();
		//
		// current.setO_client_type("PR");
		curr.setCode_resident("1");
		curr.setCode_country("860");
		curr.setP_code_citizenship("860");
		// CustomerService.prepareFakeValues(current);

		this.add_everywhere.setVisible(true);
		binder.loadComponent(add_everywhere);

	}

	public void loadRefData() {

		if (add_everywhere$ap_type_document.getItems().size() == 0)
			add_everywhere$ap_type_document.setModel(new ListModelList(
					CustomerService.getType_document(alias)));
		if (add_everywhere$ap_code_citizenship.getItems().size() == 0)
			add_everywhere$ap_code_citizenship.setModel(new ListModelList(
					CustomerService.getCountry(this.alias)));
		if (add_everywhere$acode_country.getItems().size() == 0)
			add_everywhere$acode_country.setModel(new ListModelList(
					CustomerService.getCountry(this.alias)));
		if (add_everywhere$ap_code_gender.getItems().size() == 0)
			add_everywhere$ap_code_gender.setModel(new ListModelList(
					com.is.tietovisa.customer.CustomerService
							.getGender(this.alias)));
		if (add_everywhere$ap_code_nation.getItems().size() == 0)
			add_everywhere$ap_code_nation.setModel(new ListModelList(
					com.is.tietovisa.customer.CustomerService
							.getNation(this.alias)));
		if (add_everywhere$ap_code_adr_region.getItems().size() == 0)
			add_everywhere$ap_code_adr_region.setModel(new ListModelList(
					CustomerService.getRegion(this.alias)));
		if (add_everywhere$ap_code_tax_org.getItems().size() == 0)
			add_everywhere$ap_code_tax_org.setModel(new ListModelList(
					CustomerService.getTax(this.alias)));
		if (add_everywhere$acode_resident.getItems().size() == 0)
			add_everywhere$acode_resident.setModel(new ListModelList(
					com.is.tietovisa.customer.CustomerService
							.getRezCl(this.alias)));

	}

	public void loadRefAccData() {

		/*
		 * if (add_card_wnd$agree_product.getItems().size() == 0)
		 * add_card_wnd$agree_product.setModel(new ListModelList(
		 * CustomerService.getSubProduct_code1_way4(alias)));
		 */

	}

	public void loadRefAgreeData() {
		if (add_agree_acc_card$agree_product.getItems().size() == 0)
			add_agree_acc_card$agree_product.setModel(new ListModelList(
					CustomerService.getAgreeProducts(alias)));

		if (add_agree_acc_card$agree_bincod.getItems().size() == 0)
			add_agree_acc_card$agree_bincod.setModel(new ListModelList(
					CustomerService.getBinCodes(alias)));

		if (add_agree_acc_card$agree_branch.getItems().size() == 0)
			add_agree_acc_card$agree_branch.setModel(new ListModelList(
					CustomerService.getIzdBranches(alias)));

		if (add_agree_acc_card$acc_bal.getItems().size() == 0)
			add_agree_acc_card$acc_bal.setModel(new ListModelList(
					CustomerService.getAcc_bal(alias)));

		if (add_agree_acc_card$acc_cond_set.getItems().size() == 0)
			add_agree_acc_card$acc_cond_set.setModel(new ListModelList(
					CustomerService.getAccCondSets(alias)));

		if (add_agree_acc_card$card_cond_set.getItems().size() == 0)
			add_agree_acc_card$card_cond_set.setModel(new ListModelList(
					CustomerService.getCardCondSets(alias)));
		
		if (add_agree_acc_card$card_risk_level.getItems().size() == 0)
			add_agree_acc_card$card_risk_level.setModel(new ListModelList(
					CustomerService.getRiskLevels(alias)));
		
		if (add_agree_acc_card$card_design.getItems().size() == 0)
			add_agree_acc_card$card_design.setModel(new ListModelList(
					CustomerService.getDesigns(alias)));		
		
	}

	public void onClick$close_btn$add_everywhere() {
		this.add_everywhere.setVisible(false);
		// this.fl_edit = false;

		binder.loadComponent(add_everywhere);

	}

	public void onFocus$ao_category_client$add_everywhere() {
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		/*
		 * if (add_everywhere$acode_resident.getItems().size() == 0 ||
		 * (add_everywhere$acode_resident.getValue() != current
		 * .getCode_resident())) { binder.loadComponent(add_everywhere); }
		 */
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size
		 * ()); while (add_everywhere$acode_resident.getItems().size()==0) { try
		 * { TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); }
		 * }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}

	public void onFocus$ap_type_document$add_everywhere() {
		
		// bu narsa klient qushish tugmasini modulga kirgandan sung faqatgina
		// birinchi marta
		// bosganda kerak buladi. chunki malumotlar rcombobox ga yuklanmagan
		// buladi ushanda
		// agar spravochnik malumotlar rcombobox ga hali yuklanmagan bulsa
		// binder.loadcomponent qilamiz bir marta
		
		if (add_everywhere$ap_type_document.getItems().size() == 0 ||
		  (add_everywhere$ap_type_document.getValue() != current
		  .getP_type_document() )) { 
			binder.loadComponent(add_everywhere); 
			}
		 
		/*
		 * System.out.println("jj "+add_everywhere$acode_resident.getItems().size
		 * ()); while (add_everywhere$acode_resident.getItems().size()==0) { try
		 * { TimeUnit.SECONDS.sleep(1); } catch(InterruptedException ex) {
		 * //Thread.currentThread().interrupt();
		 * System.out.println("err openway while delay : " + ex.getMessage()); }
		 * }
		 * System.out.println("kones "+add_everywhere$acode_resident.getItems()
		 * .size());
		 */
	}
	
	public void onChange$ap_code_adr_region$add_everywhere() {
		curr.setP_code_adr_region(add_everywhere$ap_code_adr_region
				.getValue());
		add_everywhere$ap_code_adr_distr.setSelectedIndex(-1);
		add_everywhere$ap_code_adr_distr.setModel(new ListModelList(
				CustomerService.getDistrByRegion(
						add_everywhere$ap_code_adr_region.getValue(), alias)));
	}

	public void onChange$ap_code_adr_distr$add_everywhere() {
		curr.setP_code_adr_distr(add_everywhere$ap_code_adr_distr.getValue());
	}

	public void onChange$ap_code_nation$add_everywhere() {
		curr.setP_code_nation(add_everywhere$ap_code_nation.getValue());
	}

	public void onChange$ap_code_tax_org$add_everywhere() {
		curr.setP_code_tax_org(add_everywhere$ap_code_tax_org.getValue());
	}

	public void onChange$ap_code_gender$add_everywhere() {
		curr.setP_code_gender(add_everywhere$ap_code_gender.getValue());
	}

	public void onChange$acode_resident$add_everywhere() {
		curr.setCode_resident(add_everywhere$acode_resident.getValue());
	}

	public void onChange$ap_type_document$add_everywhere() {
		curr.setP_type_document(add_everywhere$ap_type_document.getValue());
	}

	public void onChange$acode_country$add_everywhere() {
		curr.setCode_country(add_everywhere$acode_country.getValue());
	}

	public void onChange$ap_code_citizenship$add_everywhere() {
		curr.setP_code_citizenship(add_everywhere$ap_code_citizenship
				.getValue());
	}

	public void onClick$btn_add_acc() {
		//if (current == null || current.getId_tieto() == null) {
		//	alert("Клиент(TIETO) не выбран");
		//	return;
		//}
		if (current == null || current.getId_client() == null) {
			alert("Клиент(НСИ Банк) не выбран");
			return;
		}
		if (curr_tieto == null || curr_tieto.getId_tieto() == null) {
			alert("Клиент(TIETO) не выбран");
			return;
		}
		
		if (curr_agree != null) {
			
		} else {
			curr_agree = new RowType_Agreement();
		}		
		if (curr_acc!=null) 
			curr_acc=curr_acc.clone(curr_acc);
		else 
  		  curr_acc = new AccInfo(); 
		curr_acc.setBranch(branch);

		if (curr_card!=null) 
			curr_card=curr_card.clone(curr_card);
		else 
  		  curr_card = new CardInfo(); 

		// loaddata qilamiz shu yerda
		loadRefAgreeData();
		//
		
		//get cookie
		//Cookie [] cookies = ((HttpServletRequest)Executions.getCurrent().getNativeRequest()).getCookies();
		//System.out.println(cookies[0].getName());
		
		this.add_agree_acc_card.setVisible(true);
		binder.loadComponent(add_agree_acc_card);

	}

	public void onClick$btn_add_card() {
		alert("www");

	}

	public void onChange$agree_product$add_agree_acc_card() {
		 curr_agree.setPRODUCT(add_agree_acc_card$agree_product.getValue());
	}
	public void onChange$agree_bincod$add_agree_acc_card() {
		 curr_agree.setBINCOD(add_agree_acc_card$agree_bincod.getValue());
	}
	public void onChange$agree_branch$add_agree_acc_card() {
		 curr_agree.setBRANCH(add_agree_acc_card$agree_branch.getValue());
	}
	public void onChange$acc_cond_set$add_agree_acc_card() {
		 curr_acc.setCond_set(add_agree_acc_card$acc_cond_set.getValue());
	}
	public void onChange$card_risk_level$add_agree_acc_card() {
		 curr_card.setRISK_LEVEL(add_agree_acc_card$card_risk_level.getValue());
	}

	public void onChange$card_cond_set$add_agree_acc_card() {
		 curr_card.setCOND_SET(add_agree_acc_card$card_cond_set.getValue());
	}
	public void onChange$card_design$add_agree_acc_card() {
		 curr_card.setDesign_id(add_agree_acc_card$card_design.getValue());
	}
	public void onChange$card_risk_level$reissue_card_wnd() {
		 curr_card.setRISK_LEVEL(reissue_card_wnd$card_risk_level.getValue());
	}
	public void onChange$card_design$reissue_card_wnd() {
		 curr_card.setDesign_id(reissue_card_wnd$card_design.getValue());
	}

	
	private void sendSelEvt() {
		/*
		 * if (dataGrid.getSelectedIndex()==0){ btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); }else{ btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); }
		 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
		 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
		 * btn_next.setDisabled(false); btn_last.setDisabled(false); }
		 */
		SelectEvent evt = new SelectEvent("onSelect", branch_customers,
				branch_customers.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onSelect$branch_customers() {
		if (current != null) {

			//list tieto customers
			List<Customer> custList = null;
			if (current.getP_pinfl()!=null && !current.getP_pinfl().equals("") )
			{
				CustomerFilter fc = new CustomerFilter();
				fc.setEndpoint(listCustomerEndpoint);
				fc.setP_pinfl(current.getP_pinfl());
				fc.setT_client_b(branch+"%"); 
				custList= CustomerService.getCustomersTietoFl(0, 20, fc, alias);
			}
			else
			{
				custList = new ArrayList<Customer>();	
			}
			tietoGrid.setModel(new BindingListModelList(custList, true));
			if (custList.size() > 0) {
				tietoGrid.setSelectedIndex(0);
				SelectEvent evt = new SelectEvent("onSelect", tietoGrid,
						tietoGrid.getSelectedItems());
				Events.sendEvent(evt);
			} else {
				curr_acc = null;
				//tietoGrid.setModel(new BindingListModelList(
				//		new ArrayList<Customer>(), true));
				cardGrid.setModel(new BindingListModelList(
						new ArrayList<CardInfo>(), true));
			}

			//end tieto customers
			
		}

	}

	public void onSelect$tietoGrid() throws ParseException {

		if (curr_tieto != null && curr_tieto.getId_tieto() != null) {

			// TIETOdan
			/*List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
					.getCards_tieto(
							BigInteger.valueOf(curr_acc.getAccount_no()), "",
							CustomerService.mapConst
									.get(Cons.url_listcardsbyaccount)); */

			List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
			.getCustomerCards_tieto(
					curr_tieto.getId_tieto(), "",
					CustomerService.mapConst
							.get(Cons.url_listcustomercards));
			
			
			cardGrid.setModel(new BindingListModelList(cardList, true));
			//

			if (cardList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
			}

		} else {
			cardGrid.setModel(new BindingListModelList(
					new ArrayList<CardInfo>(), true));
		}

	}
	
	public void onSelect$accGrid() throws ParseException{
		if (curr_acc != null && curr_acc.getAccount_no() != null) {

			// ABS dan qushimcha (BF_OPENWAY_CONTRACT_Card)
			// List<CardInfo> cardList =
			// CustomerService.getContractCardList_ABS(
			// current.getBranch(), current.getId_client(),
			// curr_acc.getProductCode1(), alias);

			// TIETOdan
			List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
					.getCards_tieto(
							BigInteger.valueOf(curr_acc.getAccount_no()), "",
							CustomerService.mapConst
									.get(Cons.url_listcardsbyaccount));

			cardGrid.setModel(new BindingListModelList(cardList, true));
			//

			if (cardList.size() > 0) {
				cardGrid.setSelectedIndex(0);
				// this.curr_acc =(AccInfo) infoList.get(0);
				SelectEvent evt = new SelectEvent("onSelect", cardGrid,
						cardGrid.getSelectedItems());
				Events.sendEvent(evt);
				// binder.loadComponent(accGrid);
			}

		} else {
			cardGrid.setModel(new BindingListModelList(
					new ArrayList<CardInfo>(), true));
		}

	}

	public void onClick$add_cl_btn$add_everywhere()
			throws JsonProcessingException {
		if (curr.getSign_record().equals("_")) {
			func_add_everywhere_0();
		} else if (curr.getSign_record().equals("1")) {
			func_add_everywhere_1();
		} else if (curr.getSign_record().equals("2") || curr.getSign_record().equals("4")) {
			//doim shu ishlaydi. qolgan variantlar ishlamaydi.
			func_add_everywhere_4();
		} else if (curr.getSign_record().equals("5")) {
			func_add_everywhere_5a();
		}
	}

	public void func_add_everywhere_0() throws JsonProcessingException {
		// insert nci, insert tieto
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String bankClientId = "";

		if (CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!add_everywhere$ap_passport_serial.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!add_everywhere$ap_passport_number.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи документа";
		}
		if (!add_everywhere$ap_passport_place_registration.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан Д.У.Л.*";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_pinfl.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (add_everywhere$ap_pinfl.getValue().length() != 14) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		}
		if (!add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9 ]*")
				|| add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		if (!add_everywhere$ap_number_tax_registration.getValue().matches(
				"[0-9]*")
				|| add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (!add_everywhere$ap_birth_place.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_gender.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПоловая принадлежность";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер мобилный телефон";
		}
		if (add_everywhere$acode_country.getValue().equals("860")) {
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод области";
			}
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод района";
			}
		}
		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_nation.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНациональность";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		curr.setName(curr.getP_family() + " " + curr.getP_first_name()
				+ " " + curr.getP_patronymic());
		curr.setName(curr.getName().trim());
		Res res = null;

		curr.setBranch(branch);
		curr.setP_code_bank(branch);

		res = com.is.tietovisa.customer.CustomerService.doAction(this.session
				.getAttribute("un").toString(), this.session
				.getAttribute("pwd").toString(), curr, 1, 2, this.alias,
				true);
		if (res.getCode() != 0) {
			this.alert("ОШИБКА\nОткрытие клиента :\n" + res.getName());
			return;
		}
		// current.setId(res.getName());
		v_msg = "Клиент добавлен в НСИ банк. ";
		bankClientId = CustomerService.getCustomersClientID(res.getName(),
				this.branch, this.alias);
		v_msg = v_msg + "Код клиента: " + bankClientId;
		curr.setId_client(bankClientId);

		// CustomerService.insertClient() ni ishlatamiz yani bf_openway_clients
		// ga yozamiz
		res = com.is.tietovisa.customer.CustomerService.insertClient(curr,
				alias);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		RowType_Customer addClient = null;

		// tietoda ochamiz
		// class yasaymiz, qiymatlar beramiz
		addClient = com.is.tietovisa.customer.CustomerService.makeAddClient(
				curr, alias);

		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(addClient);
		} catch (Exception e) {
			System.out.println("error serialize json from RowType_Customer: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from RowType_Customer: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error("NewCustomer query json text: " + v_json);
		System.out.println("NewCustomer query json text: " + v_json);

		String v_res = "";
		// tietoda yuq
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_newcustomer), v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(addClient) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("NewCustomer response = " + v_res);
		System.out.println("NewCustomer response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError NewCustomer returning empty string.";
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		NewCustomerResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res, NewCustomerResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer response mapper err: " + e.getMessage());
			System.out.println("NewCustomer response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError NewCustomer response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
			v_msg = v_msg + "\nКлиент добавлен в TIETO! ";
			if (rslt.getRt_cust() != null
					&& rslt.getRt_cust().getCLIENT() != null)
				v_msg = v_msg + " Код TIETO: " + rslt.getRt_cust().getCLIENT();

			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg
					+ "\nОшибка открытия/редактирования клиента в TIETO: "
					+ rslt.getErr_text();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		// yangi customer qushilgan bulsa tieto_idsini olib linkga yozib quyamiz
		if (rslt.getRt_cust() != null && rslt.getRt_cust().getCLIENT() != null) {
			curr.setId_tieto(rslt.getRt_cust().getCLIENT());
			res = com.is.tietovisa.customer.CustomerService
					.updateClientTietoId(curr, alias);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				if (isFilterNull(filter) && !bankClientId.equals("")) {
					filter.setId_client(bankClientId);
					txbId_client.setValue(bankClientId);
					filter.setT_client_b(branch + bankClientId);
					txbClient_b.setValue(branch + bankClientId);
					this.refreshModel(this._startPageNumber);
				}
				return;
			}
		}
		this.alert(v_msg);

		if (isFilterNull(filter) && !bankClientId.equals("")) {
			filter.setId_client(bankClientId);
			txbId_client.setValue(bankClientId);
			filter.setT_client_b(branch + bankClientId);
			txbClient_b.setValue(branch + bankClientId);
			filter.setFilter_type("A");
			this.refreshModel(this._startPageNumber);
		}
		if (!v_msg.contains("Error") && !v_msg.contains("Ошибка")) {
			this.add_everywhere.setVisible(false);
		}
	}

	public void func_add_everywhere_1() throws JsonProcessingException {
		// edit nci, edit tieto
		//hali tayyormas
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String bankClientId = "";

		if (CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!add_everywhere$ap_passport_serial.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!add_everywhere$ap_passport_number.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи документа";
		}
		if (!add_everywhere$ap_passport_place_registration.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан Д.У.Л.*";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_pinfl.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (add_everywhere$ap_pinfl.getValue().length() != 14) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		}
		if (!add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9 ]*")
				|| add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		if (!add_everywhere$ap_number_tax_registration.getValue().matches(
				"[0-9]*")
				|| add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (!add_everywhere$ap_birth_place.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_gender.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПоловая принадлежность";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер мобилный телефон";
		}
		if (add_everywhere$acode_country.getValue().equals("860")) {
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод области";
			}
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод района";
			}
		}
		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_nation.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНациональность";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		curr.setName(curr.getP_family() + " " + curr.getP_first_name()
				+ " " + curr.getP_patronymic());
		curr.setName(curr.getName().trim());
		Res res = null;

		curr.setBranch(branch);
		curr.setP_code_bank(branch);

		
		// malumotlarda uzgarish bulsa 19-deystivie-korrektirovkani
		// ishlatamiz
		if (curr.hasBankChanges(copyOfCurrent)) {

			res = com.is.tietovisa.customer.CustomerService.doAction(
					this.session.getAttribute("un").toString(),
					this.session.getAttribute("pwd").toString(), curr,
					19, 0, this.alias, true);
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nРедактирование клиента в банке:\n"
						+ res.getName());
				return;
			}
			v_msg = "Клиент отредактирован в НСИ банк. ";

		} // end if (current.hasBankChanges(copyOfCurrent))

		// CustomerService.insertClient() ni ishlatamiz yani bf_openway_clients
		// ga yozamiz
		res = com.is.tietovisa.customer.CustomerService.insertClient(curr,
				alias);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		
		RowType_EditCustomer_Request updClient = null;
		// edit at the tieto
		// class yasaymiz, qiymatlar beramiz
		updClient = com.is.tietovisa.customer.CustomerService.makeEditClient(
				curr, alias);

		PostUtils postUtils = new PostUtils();

		ObjectMapper mapper = new ObjectMapper();

		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(updClient);
		} catch (Exception e) {
			System.out
					.println("error serialize json from RowType_EditCustomer_Request: "
							+ e.getMessage());
			ISLogger.getLogger().error(
					"EditCustomer serialize json error: " + e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error("EditCustomer query json text: " + v_json);
		System.out.println("EditCustomer query json text: " + v_json);

		String v_res = "";

		try {
			v_res = postUtils
					.sendData(
							CustomerService.mapConst.get(Cons.url_editcustomer),
							v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"EditCustomer postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(updClient) "
					+ e.getMessage();
		}
		System.out.println("EditCustomer response = " + v_res);
		ISLogger.getLogger().error("EditCustomer response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg
					+ "\nError NewCustomer(EditCustomer) returning empty string.";
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		EditCustomerResponse editRslt = null;
		try {
			editRslt = mapper.readValue(v_res, EditCustomerResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer/EditCustomer response mapper err: "
							+ e.getMessage());
			System.out.println("NewCustomer/EditCustomer response mapper err: "
					+ e.getMessage());
			v_msg = v_msg
					+ "\nError NewCustomer/EditCustomer response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error(editRslt.toString());

		if (editRslt.getErr_code() == "0" || editRslt.getErr_code().equals("0")) /* success */{
			v_msg = v_msg
					+ "\nКлиент редактирован в TIETO! Успешный ответ из TIETO.";
			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg + "\nОшибка редактирования клиента в TIETO: "
					+ editRslt.getErr_text();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		this.alert(v_msg);


		if (isFilterNull(filter) && !bankClientId.equals("")) {
			filter.setId_client(bankClientId);
			txbId_client.setValue(bankClientId);
			filter.setT_client_b(branch + bankClientId);
			txbClient_b.setValue(branch + bankClientId);
			filter.setFilter_type("A");
			this.refreshModel(this._startPageNumber);
		}
		if (!v_msg.contains("Error") && !v_msg.contains("Ошибка")) {
			this.add_everywhere.setVisible(false);
		}
	}
	
	public void func_add_everywhere_4() throws JsonProcessingException {
		// insert nci, insert tieto
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String bankClientId = "";

		if (CheckNull.isEmpty(/*add_everywhere$ap_type_document.getValue()*/curr.getP_type_document())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!add_everywhere$ap_passport_serial.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!add_everywhere$ap_passport_number.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи документа";
		}
		if (!add_everywhere$ap_passport_place_registration.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан Д.У.Л.*";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_pinfl.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (add_everywhere$ap_pinfl.getValue().length() != 14) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		}
		if (!add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9 ]*")
				|| add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		/*if (!add_everywhere$ap_number_tax_registration.getValue().matches(
				"[0-9]*")
				|| add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}*/
		if (CheckNull.isEmpty(/*add_everywhere$ap_code_citizenship.getValue()*/curr.getP_code_citizenship() )) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(/*add_everywhere$acode_country.getValue()*/curr.getCode_country())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (!add_everywhere$ap_birth_place.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}
		if (CheckNull.isEmpty(/*add_everywhere$ap_code_gender.getValue()*/curr.getP_code_gender())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПоловая принадлежность";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер мобилный телефон";
		}
		//if (add_everywhere$acode_country.getValue().equals("860")) {
		if (curr.getCode_country().equals("860")) {
			if (CheckNull.isEmpty(/*add_everywhere$ap_code_adr_region.getValue()*/ curr.getP_code_adr_region())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод области";
			}
			if (CheckNull.isEmpty(/*add_everywhere$ap_code_adr_distr.getValue()*/ curr.getP_code_adr_distr())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод района";
			}
		}
		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (CheckNull.isEmpty(/*add_everywhere$acode_resident.getValue()*/curr.getCode_resident())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(/*add_everywhere$ap_code_nation.getValue()*/ curr.getP_code_nation())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНациональность";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		curr.setName(curr.getP_family() + " " + curr.getP_first_name()
				+ " " + curr.getP_patronymic());
		curr.setName(curr.getName().trim());
		Res res = null;

		curr.setBranch(branch);
		curr.setP_code_bank(branch);

		
		// malumotlarda uzgarish bulsa 19-deystivie-korrektirovkani
		// ishlatamiz
		/*if (curr.hasBankChanges(copyOfCurrent)) {

			res = com.is.tietovisa.customer.CustomerService.doAction(
					this.session.getAttribute("un").toString(),
					this.session.getAttribute("pwd").toString(), curr,
					19, 0, this.alias, true);
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nРедактирование клиента в банке:\n"
						+ res.getName());
				return;
			}
			v_msg = "Клиент отредактирован в НСИ банк. ";

		}*/ // end if (current.hasBankChanges(copyOfCurrent))

		// CustomerService.insertClient() ni ishlatamiz yani bf_openway_clients
		// ga yozamiz
		res = com.is.tietovisa.customer.CustomerService.insertClient(curr,
				alias);
		if (res.getCode() != 0) {
			v_msg = v_msg + "\nОшибка " + res.getName();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		RowType_Customer addClient = null;
		// tietoda ochamiz
		// class yasaymiz, qiymatlar beramiz
		addClient = com.is.tietovisa.customer.CustomerService.makeAddClient(
				curr, alias);

		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(addClient);
		} catch (Exception e) {
			System.out.println("error serialize json from RowType_Customer: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from RowType_Customer: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error("NewCustomer query json text: " + v_json);
		System.out.println("NewCustomer query json text: " + v_json);

		String v_res = "";
		// tietoda yuq
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_newcustomer), v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(addClient) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("NewCustomer response = " + v_res);
		System.out.println("NewCustomer response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError NewCustomer returning empty string.";
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		NewCustomerResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res, NewCustomerResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer response mapper err: " + e.getMessage());
			System.out.println("NewCustomer response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError NewCustomer response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
			v_msg = v_msg + "\nКлиент добавлен в TIETO! ";
			if (rslt.getRt_cust() != null
					&& rslt.getRt_cust().getCLIENT() != null)
				v_msg = v_msg + " Код TIETO: " + rslt.getRt_cust().getCLIENT();

			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg
					+ "\nОшибка открытия/редактирования клиента в TIETO: "
					+ rslt.getErr_text();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		// yangi customer qushilgan bulsa tieto_idsini olib linkga yozib quyamiz
		if (rslt.getRt_cust() != null && rslt.getRt_cust().getCLIENT() != null) {
			curr.setId_tieto(rslt.getRt_cust().getCLIENT());
			res = com.is.tietovisa.customer.CustomerService
					.updateClientTietoId(curr, alias);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				if (isFilterNull(filter) && !bankClientId.equals("")) {
					filter.setId_client(bankClientId);
					txbId_client.setValue(bankClientId);
					filter.setT_client_b(branch + bankClientId);
					txbClient_b.setValue(branch + bankClientId);
					this.refreshModel(this._startPageNumber);
				}
				return;
			}
		}
		this.alert(v_msg);

		if (isFilterNull(filter) && !bankClientId.equals("")) {
			filter.setId_client(bankClientId);
			txbId_client.setValue(bankClientId);
			filter.setT_client_b(branch + bankClientId);
			txbClient_b.setValue(branch + bankClientId);
			filter.setFilter_type("A");
			this.refreshModel(this._startPageNumber);
		} else  //2024.03.25 
			onSelect$branch_customers();
		
		if (!v_msg.contains("Error") && !v_msg.contains("Ошибка")) {
			this.add_everywhere.setVisible(false);
		}
	}

	
	public void func_add_everywhere_5a() throws JsonProcessingException {
		// edit only in tieto
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String bankClientId = "";

		if (CheckNull.isEmpty(add_everywhere$ap_passport_serial.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи документа";
		}
		if (!add_everywhere$ap_passport_place_registration.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан Д.У.Л.*";
		}
		if (!add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_gender.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПоловая принадлежность";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер мобилный телефон";
		}

		if (CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод области";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nКод района";
		}

		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		curr.setName(curr.getP_family() + " " + curr.getP_first_name()
				+ " " + curr.getP_patronymic());
		curr.setName(curr.getName().trim());

		v_msg = "";

		RowType_EditCustomer_Request updClient = null;
		// edit at the tieto
		// class yasaymiz, qiymatlar beramiz
		updClient = com.is.tietovisa.customer.CustomerService.makeEditClient(
				curr, alias);

		PostUtils postUtils = new PostUtils();

		ObjectMapper mapper = new ObjectMapper();

		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(updClient);
		} catch (Exception e) {
			System.out
					.println("error serialize json from RowType_EditCustomer_Request: "
							+ e.getMessage());
			ISLogger.getLogger().error(
					"EditCustomer serialize json error: " + e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error("EditCustomer query json text: " + v_json);
		System.out.println("EditCustomer query json text: " + v_json);

		String v_res = "";

		try {
			v_res = postUtils
					.sendData(
							CustomerService.mapConst.get(Cons.url_editcustomer),
							v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"EditCustomer postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(updClient) "
					+ e.getMessage();
		}
		System.out.println("EditCustomer response = " + v_res);
		ISLogger.getLogger().error("EditCustomer response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg
					+ "\nError NewCustomer(EditCustomer) returning empty string.";
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		EditCustomerResponse editRslt = null;
		try {
			editRslt = mapper.readValue(v_res, EditCustomerResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer/EditCustomer response mapper err: "
							+ e.getMessage());
			System.out.println("NewCustomer/EditCustomer response mapper err: "
					+ e.getMessage());
			v_msg = v_msg
					+ "\nError NewCustomer/EditCustomer response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error(editRslt.toString());

		if (editRslt.getErr_code() == "0" || editRslt.getErr_code().equals("0")) /* success */{
			v_msg = v_msg
					+ "\nКлиент редактирован в TIETO! Успешный ответ из TIETO.";
			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg + "\nОшибка редактирования клиента в TIETO: "
					+ editRslt.getErr_text();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}

		this.alert(v_msg);

		if (isFilterNull(filter) && !bankClientId.equals("")) {
			filter.setId_client(bankClientId);
			txbId_client.setValue(bankClientId);
			filter.setT_client_b(branch + bankClientId);
			txbClient_b.setValue(branch + bankClientId);
			this.refreshModel(this._startPageNumber);
		}
		if (!v_msg.contains("Error") && !v_msg.contains("Ошибка")) {
			this.add_everywhere.setVisible(false);
		}
	}

	public void func_add_everywhere_x() throws JsonProcessingException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		String bankClientId = "";

		if (CheckNull.isEmpty(add_everywhere$ap_type_document.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nТип документа";
		}
		if (!add_everywhere$ap_passport_serial.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_serial.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nСерия паспорта";
		}
		if (!add_everywhere$ap_passport_number.getValue().matches(
				"[a-zA-Z0-9]+")
				|| add_everywhere$ap_passport_number.getValue().length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер паспорта";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_passport_date_registration
				.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата выдачи документа";
		}
		if (!add_everywhere$ap_passport_place_registration.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_passport_place_registration.getValue()
						.length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\n Где Выдан Д.У.Л.*";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_pinfl.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		} else if (add_everywhere$ap_pinfl.getValue().length() != 14) {
			fl_err = true;
			err = String.valueOf(err) + "\nПИНФЛ";
		}
		if (!add_everywhere$ap_family.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_family.getValue().length() > 34) {
			fl_err = true;
			err = String.valueOf(err) + "\nФамилия";
		}
		if (!add_everywhere$ap_first_name.getValue().matches("[a-zA-Z0-9 ]+")
				|| add_everywhere$ap_first_name.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nИмя";
		}
		if (!add_everywhere$ap_patronymic.getValue().matches("[a-zA-Z0-9 ]*")
				|| add_everywhere$ap_patronymic.getValue().length() > 20) {
			fl_err = true;
			err = String.valueOf(err) + "\nОтчество";
		}
		if (!add_everywhere$ap_number_tax_registration.getValue().matches(
				"[0-9]*")
				|| add_everywhere$ap_number_tax_registration.getValue()
						.length() > 9) {
			fl_err = true;
			err = String.valueOf(err) + "\nИНН";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_citizenship.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГражданство";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_country.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nСтрана";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_birthday.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nДата рождения";
		}
		if (!add_everywhere$ap_birth_place.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]*")
				|| add_everywhere$ap_birth_place.getValue().length() > 200) {
			fl_err = true;
			err = String.valueOf(err) + "\nМесто рождения";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_gender.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nПоловая принадлежность";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_phone_mobile.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНомер мобилный телефон";
		}
		if (add_everywhere$acode_country.getValue().equals("860")) {
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_region.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод области";
			}
			if (CheckNull.isEmpty(add_everywhere$ap_code_adr_distr.getValue())) {
				fl_err = true;
				err = String.valueOf(err) + "\nКод района";
			}
		}
		if (CheckNull.isEmpty(add_everywhere$ao_city.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nГород";
		}
		if (!add_everywhere$ap_post_address.getValue().matches(
				"[a-zA-Z0-9\\s\\.\\,_\\/-]+")
				|| add_everywhere$ap_post_address.getValue().length() > 95) {
			fl_err = true;
			err = String.valueOf(err) + "\nПочтовый адрес";
		}
		if (CheckNull.isEmpty(add_everywhere$acode_resident.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nРезидент";
		}
		if (CheckNull.isEmpty(add_everywhere$ap_code_nation.getValue())) {
			fl_err = true;
			err = String.valueOf(err) + "\nНациональность";
		}

		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		curr.setName(curr.getP_family() + " " + curr.getP_first_name()
				+ " " + curr.getP_patronymic());
		curr.setName(curr.getName().trim());
		Res res = null;

		if (curr.getId_client() == null || curr.getId_client().equals("")) {
			curr.setBranch(branch);
			curr.setP_code_bank(branch);

			res = com.is.tietovisa.customer.CustomerService.doAction(
					this.session.getAttribute("un").toString(), this.session
							.getAttribute("pwd").toString(), curr, 1, 2,
					this.alias, true);
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nОткрытие клиента :\n" + res.getName());
				return;
			}
			// current.setId(res.getName());
			v_msg = "Клиент добавлен в НСИ банк. ";
			bankClientId = CustomerService.getCustomersClientID(res.getName(),
					this.branch, this.alias);
			v_msg = v_msg + "Код клиента: " + bankClientId;
			curr.setId_client(bankClientId);

		} else if (!curr.getSign_record().equals("5")) { // edit in the bank

			// malumotlarda uzgarish bulsa 19-deystivie-korrektirovkani
			// ishlatamiz
			if (curr.hasBankChanges(copyOfCurrent)) {

				res = com.is.tietovisa.customer.CustomerService.doAction(
						this.session.getAttribute("un").toString(),
						this.session.getAttribute("pwd").toString(), curr,
						19, 0, this.alias, true);
				if (res.getCode() != 0) {
					this.alert("ОШИБКА\nРедактирование клиента в банке:\n"
							+ res.getName());
					return;
				}
				v_msg = "Клиент отредактирован в НСИ банк. ";

			} // end if (current.hasBankChanges(copyOfCurrent))
		}

		// CustomerService.insertClient() ni ishlatamiz yani bf_openway_clients
		// ga yozamiz
		if (add_tie && add_bnk) {
			res = com.is.tietovisa.customer.CustomerService.insertClient(
					curr, alias);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка " + res.getName();
				this.alert(v_msg);
				if (isFilterNull(filter) && !bankClientId.equals("")) {
					filter.setId_client(bankClientId);
					txbId_client.setValue(bankClientId);
					filter.setT_client_b(branch + bankClientId);
					txbClient_b.setValue(branch + bankClientId);
					this.refreshModel(this._startPageNumber);
				}
				return;
			}
		}
		// String v_xml = "_";
		RowType_Customer addClient = null;
		RowType_EditCustomer_Request updClient = null;
		if (curr.getSign_record().equals("2")
				|| curr.getSign_record().equals("4")) {
			// tietoda ochamiz
			// class yasaymiz, qiymatlar beramiz
			addClient = com.is.tietovisa.customer.CustomerService
					.makeAddClient(curr, alias);
		} else { // edit at the tieto
			// class yasaymiz, qiymatlar beramiz
			updClient = com.is.tietovisa.customer.CustomerService
					.makeEditClient(curr, alias);
		}

		PostUtils postUtils = new PostUtils();

		ObjectMapper mapper = new ObjectMapper();

		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(addClient);
		} catch (Exception e) {
			System.out
					.println("error serialize json from RowType_Customer/RowType_EditCustomer_Request: "
							+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from RowType_Customer/RowType_EditCustomer_Request: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error(
				"NewCustomer/RowType_EditCustomer_Request query json text: "
						+ v_json);
		System.out
				.println("NewCustomer/RowType_EditCustomer_Request query json text: "
						+ v_json);

		String v_res = "";
		// tietoda yuq
		if (curr.getSign_record().equals("2")
				|| curr.getSign_record().equals("4")) {
			try {
				v_res = postUtils.sendData(
						CustomerService.mapConst.get(Cons.url_newcustomer),
						v_json);
			} catch (Exception e) {
				ISLogger.getLogger()
						.error("NewCustomer postUtils.sendData err: "
								+ e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(addClient) "
						+ e.getMessage();
			}
			System.out.println("NewCustomer response = " + v_res);
			ISLogger.getLogger().error("NewCustomer response = " + v_res);
		} else {
			try {
				v_res = postUtils.sendData(
						CustomerService.mapConst.get(Cons.url_editcustomer),
						v_json);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"EditCustomer postUtils.sendData err: "
								+ e.getMessage());
				v_msg = v_msg + "\nError postUtils.sendData(addClient) "
						+ e.getMessage();
			}
			System.out.println("EditCustomer response = " + v_res);
			ISLogger.getLogger().error("EditCustomer response = " + v_res);

		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg
					+ "\nError NewCustomer(EditCustomer) returning empty string.";
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error(
				"NewCustomer/EditCustomer response: " + v_res);
		System.out.println("NewCustomer/EditCustomer response: " + v_res);
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		// RowType_Customer rslt = null;
		NewCustomerResponse rslt = null;
		RowType_EditCustomer_Request editRslt = null;
		try {
			if (curr.getSign_record().equals("2")
					|| curr.getSign_record().equals("4"))
				rslt = mapper.readValue(v_res, NewCustomerResponse.class);
			else
				editRslt = mapper.readValue(v_res,
						RowType_EditCustomer_Request.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewCustomer/EditCustomer response mapper err: "
							+ e.getMessage());
			System.out.println("NewCustomer/EditCustomer response mapper err: "
					+ e.getMessage());
			v_msg = v_msg
					+ "\nError NewCustomer/EditCustomer response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			if (isFilterNull(filter) && !bankClientId.equals("")) {
				filter.setId_client(bankClientId);
				txbId_client.setValue(bankClientId);
				filter.setT_client_b(branch + bankClientId);
				txbClient_b.setValue(branch + bankClientId);
				this.refreshModel(this._startPageNumber);
			}
			return;
		}
		ISLogger.getLogger().error(rslt.toString());

		if (curr.getSign_record().equals("2")
				|| curr.getSign_record().equals("4")) {
			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va
			// return
			// qilamiz
			if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
				v_msg = v_msg
						+ "\nКлиент добавлен/редактирован в TIETO! Успешный ответ из TIETO.";
				// UserService.WayQueryLog(new UserActionsLog(uid, "uname",
				// curip, 5,
				// branch, v_xml, v_res2), alias);

			} else {
				v_msg = v_msg
						+ "\nОшибка открытия/редактирования клиента в TIETO: "
						+ rslt.getErr_text();
				this.alert(v_msg);
				if (isFilterNull(filter) && !bankClientId.equals("")) {
					filter.setId_client(bankClientId);
					txbId_client.setValue(bankClientId);
					filter.setT_client_b(branch + bankClientId);
					txbClient_b.setValue(branch + bankClientId);
					this.refreshModel(this._startPageNumber);
				}
				return;
			}

			// yangi customer qushilgan bulsa tieto_idsini olib linkga yozib
			// quyamiz
			if (rslt.getRt_cust() != null
					&& rslt.getRt_cust().getCLIENT() != null) {
				curr.setId_tieto(rslt.getRt_cust().getCLIENT());
				res = com.is.tietovisa.customer.CustomerService
						.updateClientTietoId(curr, alias);
				if (res.getCode() != 0) {
					v_msg = v_msg + "\nОшибка " + res.getName();
					this.alert(v_msg);
					if (isFilterNull(filter) && !bankClientId.equals("")) {
						filter.setId_client(bankClientId);
						txbId_client.setValue(bankClientId);
						filter.setT_client_b(branch + bankClientId);
						txbClient_b.setValue(branch + bankClientId);
						this.refreshModel(this._startPageNumber);
					}
					return;
				}
			}
			this.alert(v_msg);

		} else {

			v_msg = v_msg
					+ "\nКлиент добавлен/редактирован в TIETO! Успешный ответ из TIETO. Код тието: "
					+ editRslt.getCLIENT();
			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

			this.alert(v_msg);

		}

		if (isFilterNull(filter) && !bankClientId.equals("")) {
			filter.setId_client(bankClientId);
			txbId_client.setValue(bankClientId);
			filter.setT_client_b(branch + bankClientId);
			txbClient_b.setValue(branch + bankClientId);
			this.refreshModel(this._startPageNumber);
		}
		if (!v_msg.contains("Error") && !v_msg.contains("Ошибка")) {
			this.add_everywhere.setVisible(false);
		}
	}


	public boolean existsCard(String cardCondSet, List<CardInfo> cardList) {		
		for (int i=0; i<cardList.size(); i++) {
			if (cardList.get(i).getCOND_SET().equals(cardCondSet))
				return true;
		}
		return false;
	}
	
	public void onClick$add_acc_btn$add_agree_acc_card()
			throws JsonProcessingException , ParseException{
		// карта очиш жойи 
		//if (current.getId_tieto()==null || current.getId_tieto()=="" || current.getId_tieto().equals("")) {
		//	alert("Нет данных по ТИЕТО или код ТИЕТО пустой!");
		//	return;
		//}
		if (curr_tieto.getId_tieto()==null || curr_tieto.getId_tieto()=="" || curr_tieto.getId_tieto().equals("")) {
			alert("Нет данных по ТИЕТО или код ТИЕТО пустой!");
			return;
		} 
		
		
		boolean fl_err = false;
		String err = "";
		String v_msg = "";
		if (curr_agree.getPRODUCT()==null || curr_agree.getPRODUCT().equals("")) {
			fl_err=true;
			err=err+"/n"+"Код продукта";			
		}
		
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}

		List<CardInfo> cardList = com.is.tietovisa.customer.CustomerService
		.getCustomerCards_tieto(
				curr_tieto.getId_tieto(), "",
				CustomerService.mapConst
						.get(Cons.url_listcustomercards));
	
		Product myProduct = null; 
		myProduct=CustomerService.getProductByName(curr_agree.getPRODUCT(), alias);		
		
		String myCardCondSet=CustomerService.getCond_cardByCardname(curr_agree.getPRODUCT(), alias).getCond_set();
		if (existsCard(myCardCondSet, cardList)) {
			alert("Карта " +curr_agree.getPRODUCT()+ " сушествует. повторное открытие не разрешается!");
			return;
		}
		//счет борми,
		String myAcc="";
		AccountFilter filt = new AccountFilter();
		filt.setBranch(branch);
		filt.setAcc_bal("22618");
		filt.setCurrency("840");
		filt.setClient(current.getId_client());
		filt.setId_order(String.format("%03d", myProduct.getAcc_id_order()) );		
		// ABSdan
		List<Account> accList = AccountService.getAccountsFl(0, 100, filt,	alias);
		if (accList.size()==0) {
			//shet ochamiz
			com.is.utils.Res res = null;
			//счет очишимиз керак ва утвердит килиш
			//тодо				
			Account acc = new Account();
			acc.setBranch(branch);
			acc.setAcc_bal("22618");
			acc.setClient(current.getId_client());
			acc.setName(current.getName());
			acc.setId_order(String.format("%03d", myProduct.getAcc_id_order()));
			acc.setCurrency("840");
			acc.setSgn("P");
			acc.setBal("B");
			acc.setSign_registr(2);
			
			ISLogger.getLogger().error("open acc: "+current.getId_client()+", format: "+String.format("%03d", myProduct.getAcc_id_order())+ "-myProduct: "+myProduct.toString());
			

			// String un,String pw, Account account,int actionid, String alias,
			// Boolean selfBranch
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 1, this.alias);
			if (res.getCode() != 0) {
				this.alert("ОШИБКА\nОткрытие счета :\n" + res.getName());
				return;
			}
			acc.setId(res.getName());
			myAcc=res.getName();
			v_msg = "Счет открыть в НСИ банк. ";
			v_msg = v_msg + "Счет клиента: " + res.getName();
			// утвердит
			res = AccountService.doAction(this.session.getAttribute("un")
					.toString(), this.session.getAttribute("pwd").toString(),
					acc, 2, this.alias);
			if (res.getCode() != 0) {
				v_msg = v_msg + "\nОшибка Утвердить счета :\n" + res.getName();
				this.alert(v_msg);
				return;
			}

			v_msg = v_msg + "\nСчет утвержден в НСИ банк. ";
			
		} else {
			//shet bor uje
			myAcc=accList.get(0).getId();
			
		}
		add_agree_acc_card$tranz_acct.setValue(myAcc);
		binder.loadComponent(add_agree_acc_card);
		
		NewAgreementRequest agreeRequest = new NewAgreementRequest();
		RowType_Agreement rt_agreement = new RowType_Agreement();
		rt_agreement.setCLIENT(curr_tieto.getId_tieto());
		rt_agreement.setCITY(curr_tieto.getT_r_city());
		ISLogger.getLogger().error( "cl getT_r_street " + curr_tieto.getId_tieto() + " - " +curr_tieto.getT_r_street()+"!");
		if (curr_tieto.getT_r_street()!=null && !curr_tieto.getT_r_street().equals(""))
			rt_agreement.setSTREET(curr_tieto.getT_r_street());
		
		ISLogger.getLogger().error( "cl getT_r_emails " + curr_tieto.getId_tieto() + " - " +curr_tieto.getT_r_emails()+"!");
		if (curr_tieto.getT_r_emails()!=null && !curr_tieto.getT_r_emails().equals(""))
			rt_agreement.setE_MAILS(curr_tieto.getT_r_emails());

		rt_agreement.setDISTRIB_MODE("02");
		rt_agreement.setREP_LANG("1");
		rt_agreement.setSTATUS("10");
		//rt_agreement.setRISK_LEVEL(curr_card.getRISK_LEVEL());
		rt_agreement.setRISK_LEVEL(myProduct.getRisk_level());
		//rt_agreement.setBINCOD(curr_agree.getBINCOD());
		rt_agreement.setBINCOD(myProduct.getBin_code());
		rt_agreement.setPRODUCT(curr_agree.getPRODUCT());
		//rt_agreement.setBRANCH(curr_agree.getBRANCH());
		rt_agreement.setBRANCH( CustomerService.getBranchByMfo(branch, alias).getBranch());
		
		rt_agreement.setBANK_CODE(CustomerService.mapConst.get(Cons.bank_c));
		Calendar cal = Calendar.getInstance();
		rt_agreement.setENROLLED(cal);
		rt_agreement.setCTIME(cal);
		agreeRequest.setRt_agreement(rt_agreement);
		
		RowType_AccountInfo rt_accountInfo = new RowType_AccountInfo();
		RowType_AccountInfo_Base base_info=new RowType_AccountInfo_Base();
		//base_info.setTRANZ_ACCT(curr_acc.getTranz_acct());
		//base_info.setTRANZ_ACCT(add_agree_acc_card$tranz_acct.getValue());
		base_info.setTRANZ_ACCT(myAcc);
		
		//base_info.setCOND_SET(curr_acc.getCond_set());
		base_info.setCOND_SET(myProduct.getCond_set());
		//base_info.setNON_REDUCE_BAL( new BigDecimal(add_agree_acc_card$acc_non_reduce_balance.getValue()) );
		base_info.setNON_REDUCE_BAL( new BigDecimal(myProduct.getNon_reduce_bal()).multiply( new BigDecimal("100" )) );
		base_info.setC_ACCNT_TYPE("00");
		//base_info.setCCY(CustomerService.getCurrencyFromAccCondSet(curr_acc.getCond_set(), alias));
		base_info.setCCY(CustomerService.getCurrencyFromAccCondSet(myProduct.getCond_set(), alias));
		base_info.setCYCLE("1");
		base_info.setMIN_BAL(BigDecimal.valueOf(0));
		base_info.setSTATUS("0");
		base_info.setSTAT_CHANGE("1");
		
		rt_accountInfo.setBase_info(base_info);
		
		RowType_AccountInfo_Additional additional_info = new RowType_AccountInfo_Additional();
		rt_accountInfo.setAdditional_info(additional_info);
		agreeRequest.setRt_accountInfo(rt_accountInfo);
		
		RowType_CardInfo rt_cardInfo = new RowType_CardInfo();
		RowType_CardInfo_LogicalCard logicalCard = new RowType_CardInfo_LogicalCard();
		logicalCard.setCLIENT(curr_tieto.getId_tieto());
		logicalCard.setBASE_SUPP("1");
		//logicalCard.setCOND_SET(curr_card.getCOND_SET());
		logicalCard.setCOND_SET( myCardCondSet );
		
		//logicalCard.setRISK_LEVEL(curr_card.getRISK_LEVEL());
		logicalCard.setRISK_LEVEL(myProduct.getRisk_level());
		logicalCard.setF_NAMES(curr_tieto.getT_f_names());
		logicalCard.setSURNAME(curr_tieto.getT_surname());
		rt_cardInfo.setLogicalCard(logicalCard);
		RowType_CardInfo_PhysicalCard physicalCard = new RowType_CardInfo_PhysicalCard();
		physicalCard.setSTATUS1("0");
		physicalCard.setCARD_NAME(curr_tieto.getT_f_names()+" "+ curr_tieto.getT_surname());
		if (physicalCard.getCARD_NAME().length()>24 )
			physicalCard.setCARD_NAME(physicalCard.getCARD_NAME().substring(0,24));
		//physicalCard.setDESIGN_ID(new BigDecimal("1"));
		physicalCard.setDESIGN_ID(new BigDecimal(CustomerService.getCard_designByCardname(curr_agree.getPRODUCT(), alias).getDesign_id() ));
		rt_cardInfo.setPhysicalCard(physicalCard);
		RowType_CardInfo_EMV_Data EMV_Data = new RowType_CardInfo_EMV_Data();
		//EMV_Data.setDESIGN_ID(new BigDecimal("1"));
		EMV_Data.setDESIGN_ID(new BigDecimal(CustomerService.getCard_designByCardname(curr_agree.getPRODUCT(), alias).getDesign_id() ));
		EMV_Data.setCHIP_APP_ID(new BigDecimal("2"));
		rt_cardInfo.setEMV_Data(EMV_Data);
		RowType_CardInfo_TSM_Data TSM_Data= new RowType_CardInfo_TSM_Data();
		rt_cardInfo.setTSM_Data(TSM_Data);
		
		agreeRequest.setRt_cardInfo(rt_cardInfo);
	
		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(agreeRequest);
		} catch (Exception e) {
			System.out.println("error serialize json from NewAgreementRequest: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from NewAgreementRequest: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			
			return;
		}
		ISLogger.getLogger().error("NewAgreement query json text: " + v_json);
		System.out.println("NewAgreement query json text: " + v_json);

		String v_res = "";
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_newagreement), v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"NewAgreement postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(addClient) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("NewAgreement response = " + v_res);
		System.out.println("NewAgreement response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError NewAgreement returning empty string.";
			this.alert(v_msg);
			return;
		}

		// shuerda bir maxinasiya qilib ketamiz endi. boshqa iloj yuq
		if (v_res.contains("Invalid element in visa.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard - INSURANCE_FLAG") ) {
			//buni ham uspeshno deb hisoblaymiz

			CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Агримент, счет и карта No [" + curr_tieto.getT_f_names()+" "+ curr_tieto.getT_surname() + "] добавлены в TIETO."  ));
			v_msg = v_msg + "\nАгримент, счет и карта добавлены в TIETO успешно! ";
			//v_msg = v_msg + " Карта: " + rslt.getLt_cardInfoHolder().value.getRow(0).getLogicalCard().getCARD();

		} else {
			
			// kelgan javobdagi json-stringdan java object (klass) yasaymiz
			NewAgreementResponse rslt = null;
			try {
				rslt = mapper.readValue(v_res, NewAgreementResponse.class);
			} catch (Exception e) {
				ISLogger.getLogger().error(
						"NewAgreement response mapper err: " + e.getMessage());
				System.out.println("NewCustomer response mapper err: "
						+ e.getMessage());
				v_msg = v_msg + "\nError NewAgreement response mapper: "
						+ e.getMessage();
				this.alert(v_msg);
				return;
			}

			// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
			// qilamiz
			if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
				
				CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Агримент, счет и карта No [" + rslt.getLt_cardInfoHolder().value.getRow(0).getLogicalCard().getCARD() + "] добавлены в TIETO."  ));
				v_msg = v_msg + "\nАгримент, счет и карта добавлены в TIETO успешно! ";
				v_msg = v_msg + " Карта: " + rslt.getLt_cardInfoHolder().value.getRow(0).getLogicalCard().getCARD();

				//2024.02.29
				CardIbs cardIbs= new CardIbs();
				
				cardIbs.setClient_id(curr_tieto.getId_tieto());
				cardIbs.setClient_b(current.getId_client());
				cardIbs.setBranch(branch);
				cardIbs.setCard(rslt.getLt_cardInfoHolder().value.getRow(0).getLogicalCard().getCARD());
				cardIbs.setStatus1("0");
				cardIbs.setStatus2(rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getSTATUS2());
				cardIbs.setCard_regist_date(new java.util.Date());
				cardIbs.setExpiry1(rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getEXPIRY1().getTime());
				if (rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getEXPIRITY2()!=null)
				  cardIbs.setExpirity2(rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getEXPIRITY2().getTime());
				//else
				//	cardIbs.setExpirity2(rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getEXPIRITY2().getTime());					
				cardIbs.setRenew("N");
				//cardIbs.setRenew_date("");
				cardIbs.setCard_name(rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getCARD_NAME());
				cardIbs.setStop_cause(rslt.getLt_cardInfoHolder().value.getRow(0).getPhysicalCard().getSTOP_CAUSE());
				Res res1 = CustomerService.getRealCard_tieto(cardIbs.getCard(), "", CustomerService.mapConst.get(Cons.url_getrealcard));
				if (res1.getCode()==0) {
					cardIbs.setReal_card(res1.getName());
				} else {
					cardIbs.setReal_card("");
				}
				cardIbs.setTranz_acct(myAcc);
				cardIbs.setCard_acct(rslt.getLt_accountInfoHolder().value.getRow(0).getBase_info().getCARD_ACCT());
				cardIbs.setPinfl(current.getP_pinfl());
				cardIbs.setCond_set(rslt.getLt_cardInfoHolder().value.getRow(0).getLogicalCard().getCOND_SET());

				Res res = CustomerService.insertCardToIbs(cardIbs, alias);
				if (res.getCode() != 0) {
					v_msg = v_msg
					+ "\nНе получилось записать в visa_cards: "
					+ res.getName();
				}
			} else {
				v_msg = v_msg
						+ "\nОшибка открытия Агримент, счет и карта в TIETO: "
						+ rslt.getErr_text();
				this.alert(v_msg);
				return;
			}
			//shuergacha kelsa uspeshno degani
			//add cookie
			HttpServletResponse response = (HttpServletResponse)Executions.getCurrent().getNativeResponse();
			Cookie userCookie = new Cookie("user", "xxx123");
			response.addCookie(userCookie);
		}
		
		this.alert(v_msg);
		onSelect$branch_customers();
		this.add_agree_acc_card.setVisible(false);

	}

	public void onClick$lock_card_btn$lock_card_wnd() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";


        String card1=(String)lock_card_wnd.getAttribute("card_num");

		if (card1 == null) {
			alert("Карта не выбрана!" );
			return;
		}

		if (lock_card_wnd$rcb_stop_cause.getValue() == null
				|| lock_card_wnd$rcb_stop_cause.getValue().equals("")) {
			fl_err = true;
			err = String.valueOf(err) + "\nПричина блокировки";
		}
		//if (CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue())) {
		//	fl_err = true;
		//	err = String.valueOf(err) + "\nКоментарий(описание) блокировки";
		//}
		if ( CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue()) || !lock_card_wnd$txt_comment_text.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
		  || lock_card_wnd$txt_comment_text.getValue().length() > 160 || lock_card_wnd$txt_comment_text.getValue().length() < 8) {
	        fl_err = true;
	        err = String.valueOf(err) + "\nКоментарий(описание) блокировки: Только латинские буквы, минимум 8, максимум 160 символов.";
        }
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
		
		RowType_AddCardToStopList_Request stoplistRequest = new RowType_AddCardToStopList_Request(); 
		stoplistRequest.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		stoplistRequest.setGROUPC(CustomerService.mapConst.get(Cons.groupc));
		stoplistRequest.setCARD(card1);
		stoplistRequest.setSTOP_CAUSE(lock_card_wnd$rcb_stop_cause.getValue());
		//stoplistRequest.setSTOP_CAUSE("5"); //5-"Do not honor"
		stoplistRequest.setTEXT(lock_card_wnd$txt_comment_text.getValue());
		
		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(stoplistRequest);
		} catch (Exception e) {
			System.out.println("error serialize json from AddCardToStop: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from AddCardToStop: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			
			return;
		}
		ISLogger.getLogger().error("AddCardToStop query json text: " + v_json);
		System.out.println("AddCardToStop query json text: " + v_json);

		String v_res = "";
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_addcardtostop), v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"AddCardToStop postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(AddCardToStop) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("AddCardToStop response = " + v_res);
		System.out.println("AddCardToStop response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError AddCardToStop returning empty string.";
			this.alert(v_msg);
			return;
		}
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		CommonResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res, CommonResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"AddCardToStop response mapper err: " + e.getMessage());
			System.out.println("AddCardToStop response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError AddCardToStop response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			return;
		}
		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
			
			if (rslt.getOperResponseInfo() != null && rslt.getOperResponseInfo().getResponse_code()==BigInteger.valueOf(0)){
				CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Заблокирована карта No [" + card1 + "]. "+lock_card_wnd$txt_comment_text.getValue()  )); 				
				v_msg = v_msg + "\nКарта заблокирован успешно! ";
			}
			else {
				v_msg = v_msg
				+ "\nОшибка блокировки карты: "
				+ rslt.getOperResponseInfo().getResponse_code()+" : " + rslt.getOperResponseInfo().getError_description();
				this.alert(v_msg);
				return;
			}

			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg
					+ "\nОшибка блокировки карты: "
					+ rslt.getErr_text();
			this.alert(v_msg);
			return;
		}

		this.alert(v_msg);
		onSelect$tietoGrid();
		this.lock_card_wnd.setVisible(false);
    }
	
	public void onClick$unlock_card_btn$lock_card_wnd() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";

        String card1=(String)lock_card_wnd.getAttribute("card_num");

		if (card1 == null) {
			alert("Карта не выбрана!" );
			return;
		}

		//if (CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue())) {
		//	fl_err = true;
		//	err = String.valueOf(err) + "\nКоментарий(описание) разблокировки";
		//}
		if ( CheckNull.isEmpty(lock_card_wnd$txt_comment_text.getValue()) || !lock_card_wnd$txt_comment_text.getValue().matches("[a-zA-Z0-9\\s\\.\\,_\\/-]+")
		  || lock_card_wnd$txt_comment_text.getValue().length() > 160 || lock_card_wnd$txt_comment_text.getValue().length() < 8) {
	        fl_err = true;
	        err = String.valueOf(err) + "\nКоментарий(описание) разблокировки: Только латинские буквы, длина минимум 8, максимум 160 символов.";
        }
		if (fl_err) {
			alert("Ошибка заполнения формы:\nневерно заполнено поле " + err);
			return;
		}
		//todo
		//removeCardFromStop 
		
		RowType_RemoveCardFromStop_Request stoplistRequest = new RowType_RemoveCardFromStop_Request(); 
		stoplistRequest.setBANK_C(CustomerService.mapConst.get(Cons.bank_c));
		stoplistRequest.setGROUPC(CustomerService.mapConst.get(Cons.groupc));
		stoplistRequest.setCARD(card1);
		stoplistRequest.setTEXT(lock_card_wnd$txt_comment_text.getValue());
		
		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(stoplistRequest);
		} catch (Exception e) {
			System.out.println("error serialize json from RemoveCardFromStop: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from RemoveCardFromStop: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			
			return;
		}
		ISLogger.getLogger().error("RemoveCardFromStop query json text: " + v_json);
		System.out.println("RemoveCardFromStop query json text: " + v_json);

		String v_res = "";
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_removecardfromstop), v_json); 
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"RemoveCardFromStop postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(RemoveCardFromStop) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("RemoveCardFromStop response = " + v_res);
		System.out.println("RemoveCardFromStop response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError RemoveCardFromStop returning empty string.";
			this.alert(v_msg);
			return;
		}
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		CommonResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res, CommonResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"RemoveCardFromStop response mapper err: " + e.getMessage());
			System.out.println("RemoveCardFromStop response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError RemoveCardFromStop response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			return;
		}
		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
			
			if (rslt.getOperResponseInfo() != null && rslt.getOperResponseInfo().getResponse_code()==BigInteger.valueOf(0)){
				CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Разблокирована карта No [" + card1 + "]. "+lock_card_wnd$txt_comment_text.getValue()  ));
				v_msg = v_msg + "\nКарта разблокирован успешно! ";
			}
			else {
				v_msg = v_msg
				+ "\nОшибка разблокировки карты: "
				+ rslt.getOperResponseInfo().getResponse_code()+" : " + rslt.getOperResponseInfo().getError_description();
				this.alert(v_msg);
				return;
			}

			// UserService.WayQueryLog(new UserActionsLog(uid, "uname", curip,
			// 5,
			// branch, v_xml, v_res2), alias);

		} else {
			v_msg = v_msg
					+ "\nОшибка разблокировки карты: "
					+ rslt.getErr_text();
			this.alert(v_msg);
			return;
		}

		this.alert(v_msg);
		onSelect$tietoGrid();
		this.lock_card_wnd.setVisible(false);
    }
	
	public void onClick$reissue_card_btn$reissue_card_wnd() throws JsonProcessingException, ParseException {
		boolean fl_err = false;
		String err = "";
		String v_msg = "";

       
		CardInfo card1=(CardInfo)reissue_card_wnd.getAttribute("card");
		if (card1 == null || card1.getCARD() == null) {
			alert("Карта не выбрана!" );
			return;
		}
		
		RowType_ReplaceCard replaceCardRequest = new RowType_ReplaceCard();
		replaceCardRequest.setCARD(card1.getCARD());
		//if (reissue_card_wnd$card_expiry.getValue()!=null) {
		//	Calendar cal = Calendar.getInstance();
		//	cal.setTime(reissue_card_wnd$card_expiry.getValue());
		//	replaceCardRequest.setNEW_EXPIRY(cal);
		//}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 3);
		replaceCardRequest.setNEW_EXPIRY(cal);
		
		if (reissue_card_wnd$card_risk_level.getValue()!=null) {
			replaceCardRequest.setNEW_RISK_LEVEL(reissue_card_wnd$card_risk_level.getValue());
		}
		if (reissue_card_wnd$card_design.getValue()!=null && !reissue_card_wnd$card_design.getValue().trim().equals("") ) {
			//alert("-"+reissue_card_wnd$card_design.getValue().trim()+"-");
			replaceCardRequest.setDESIGN_ID( new BigDecimal(reissue_card_wnd$card_design.getValue()));
		}
		
		PostUtils postUtils = new PostUtils();
		ObjectMapper mapper = new ObjectMapper();
		String v_json = "_";
		try {
			v_json = mapper.writerWithDefaultPrettyPrinter()
					.writeValueAsString(replaceCardRequest);
		} catch (Exception e) {
			System.out.println("error serialize json from replaceCard: "
					+ e.getMessage());
			ISLogger.getLogger().error(
					"error serialize json from replaceCard: "
							+ e.getMessage());
			v_msg = v_msg + "\nError serialize json " + e.getMessage();
		} finally {
		}
		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			
			return;
		}
		ISLogger.getLogger().error("replaceCard query json text: " + v_json);
		System.out.println("replaceCard query json text: " + v_json);

		String v_res = "";
		try {
			v_res = postUtils.sendData(
					CustomerService.mapConst.get(Cons.url_replacecard), v_json);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"replaceCard postUtils.sendData err: " + e.getMessage());
			v_msg = v_msg + "\nError postUtils.sendData(replaceCard) "
					+ e.getMessage();
		}
		ISLogger.getLogger().error("replaceCard response = " + v_res);
		System.out.println("replaceCard response = " + v_res);

		if (v_msg.contains("Error") || v_msg.contains("Ошибка")) {
			this.alert(v_msg);
			return;
		}
		if ((v_res == "" || v_res.equals(""))) {
			v_msg = v_msg + "\nError replaceCard returning empty string.";
			this.alert(v_msg);
			return;
		}
		// kelgan javobdagi json-stringdan java object (klass) yasaymiz
		ReplaceCardResponse rslt = null;
		try {
			rslt = mapper.readValue(v_res, ReplaceCardResponse.class);
		} catch (Exception e) {
			ISLogger.getLogger().error(
					"replaceCard response mapper err: " + e.getMessage());
			System.out.println("replaceCard response mapper err: "
					+ e.getMessage());
			v_msg = v_msg + "\nError replaceCard response mapper: "
					+ e.getMessage();
			this.alert(v_msg);
			return;
		}
		// uspeshno bulsa davom etamiz, xato bulsa xatoni kursatamiz va return
		// qilamiz
		if (rslt.getErr_code() == "0" || rslt.getErr_code().equals("0")) /* success */{
			
			if (rslt.getOperResponseInfo() != null && rslt.getOperResponseInfo().getResponse_code()==BigInteger.valueOf(0)){
				v_msg = v_msg + "\nКарта перевыпушена успешно! ";
				if (rslt.getRtrc_parameters() != null && rslt.getRtrc_parameters().getNEW_CARD() !=null){
					v_msg = v_msg + "\n"+rslt.getRtrc_parameters().getNEW_CARD();
					CustomerService.UsrLog(new UserActionsLog((Long)null, branch, uid, un, CustomerService.getIp(), (java.util.Date)null, 6, 1, "Перевыпушена карта No [" + rslt.getRtrc_parameters().getNEW_CARD() + "]."  ));

					//visa_cards_history га ёзамиз

					// card1.getCARD() eski(joriy) karta
					// rslt.getRtrc_parameters().getNEW_CARD() - yangi karta
					// new java.util.Date()
					
					CardIbs newCardIbs = new CardIbs();
					
					newCardIbs.setCard(rslt.getRtrc_parameters().getNEW_CARD());
					newCardIbs.setRenew_date(new java.util.Date());
					Res res1 = CustomerService.getRealCard_tieto(newCardIbs.getCard(), "", CustomerService.mapConst.get(Cons.url_getrealcard));
					if (res1.getCode()==0) {
						newCardIbs.setReal_card(res1.getName());
					} else {
						newCardIbs.setReal_card("");
					}

					Res res = CustomerService.changeCardToIbs(card1.getCARD(), newCardIbs, alias);
					if (res.getCode() != 0) {
						v_msg = v_msg
						+ "\nНе получилось записать в visa_cards_history: "
						+ res.getName();
					}

				}
				
			}
			else {
				v_msg = v_msg
				+ "\nОшибка перевыпуска карты: "
				+ rslt.getOperResponseInfo().getResponse_code()+" : " + rslt.getOperResponseInfo().getError_description();
				this.alert(v_msg);
				return;
			}


		} else {
			v_msg = v_msg
					+ "\nОшибка перевыпуска карты: "
					+ rslt.getErr_text();
			this.alert(v_msg);
			return;
		}

		this.alert(v_msg);
		onSelect$accGrid();
		this.reissue_card_wnd.setVisible(false);
    }

	
	public void onClick$btnCloseCardActionsWindow$cardActionsWindow() {
	      this.cardActionsWindow.setVisible(false);
	}
	
	public CustomerFilter getFilter() {
		return filter;
	}

	public void setFilter(CustomerFilter filter) {
		this.filter = filter;
	}

	public Customer getCurrent() {
		return current;
	}

	public void setCurrent(Customer current) {
		this.current = current;
	}

	public Customer getCurr_tieto() {
		return curr_tieto;
	}

	public void setCurr_tieto(Customer curr_tieto) {
		this.curr_tieto = curr_tieto;
	}
	
	public AccInfo getCurr_acc() {
		return curr_acc;
	}

	public void setCurr_acc(AccInfo curr_acc) {
		this.curr_acc = curr_acc;
	}

	public CardInfo getCurr_card() {
		return curr_card;
	}

	public void setCurr_card(CardInfo curr_card) {
		this.curr_card = curr_card;
	}

	public CardInfo getNew_card() {
		return new_card;
	}

	public void setNew_card(CardInfo new_card) {
		this.new_card = new_card;
	}

	public Customer getCurr() {
		return curr;
	}

	public void setCurr(Customer curr) {
		this.curr = curr;
	}

}
