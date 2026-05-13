package com.is.card_to_card;

import java.io.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.zul.ListModelList;
import org.json.JSONException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
//import java.activation;
//import java.corba;
//import java.transaction;
//import java.xml.bind;
//import java.xml.ws;
//import java.xml.ws.annotation;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;
import java.util.Random;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.text.SimpleDateFormat;

//import java.nio.charset.StandardCharsets;
//import sun.misc.Base64Encoder;
//import sun.misc.BASE64Decoder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.is.ISLogger;
import com.is.account.Account;
import com.is.utils.RefCBox;

public class CardtcViewCtrl extends GenericForwardComposer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2786776090452976032L;
	private Window chacc;
	private Div frm;
	private Listbox dataGridDetails, dataGrid, chacc$acc, dataGrid2, dataGrid3, initialGrid1$dataGrid4,
			initialGrid1$dataGrid5;
	private Paging contactPaging;
	private Div grd1, grd3;
	private Vlayout grd2;
	private Window initialGrid1, initialGrid1_more;
	private Grid addgrd, frmgrd, fgrd, initialGrid;
	private Toolbarbutton btn_last, tbtn_search;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, initialGrid1$btn_exit, initialGrid1$btn_hide, initialGrid1_more$btn_exit1, btn_save;
	private Toolbarbutton tbtn_settings, tbtn_move_saldo;
	private Toolbar tb;
	private Label initialGrid1$name, initialGrid1$general_name, initialGrid1_more$namemore;
	private Textbox acc_mfo, account, acc_name, chacc$acc_filter_mask, initialGrid1_more$apimore;
	private Textbox aacc_mfo, aaccount, aacc_name, brunch;
	private Textbox facc_mfo, faccount, facc_name, mod_type, txbId_client, txbCard, txbName, txbPinfl;
	private RefCBox rcb_card_from, rcb_card_to;
	private Intbox id, aid, fid, acc_template_id, aacc_template_id, facc_template_id;
	private Paging traccPaging, protocolPaging;
	private Tab protocolTab, mainTab;
	private int _pageSize = 14;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String mbranch, alias, branch, un, pw;
//	private ListModelList userListModel;
	public CardFromApi cardfromapifilter, alias1;
	public CardFromApi apifilter;
	public Card card;
	public Card filter = new Card();
	public Card filterforcard_1 = new Card();
	public Card credit_card = new Card();
//	public Card filterforcardbalance1 = new Card();
//	public Card filterforcardbalance2 = new Card();
	public CardFilter cardfilter = new CardFilter();
	public CardFilter cardfilter2 = new CardFilter();
	public String branch_p, karta1, karta2;
	public Long amount, id_x;
	public Long transaction1_general_id, transaction2_general_id;
	public int x;
	public String client_code_1;
	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

	private Card currentFrom = new Card();
	private Card currentTo = new Card();
	private Card current0 = new Card();
	private Card current1 = new Card();
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

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	    protocolPaging = (Paging) comp.getFellow("protocolPaging"); 
	    dataGrid3 = (Listbox) comp.getFellow("dataGrid3");
	    protocolPaging.setPageSize(_pageSize);
	    protocolPaging.addEventListener("onPaging", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
			    PagingEvent pe = (PagingEvent) event;
			    refreshModel(pe.getActivePage());
			}
		});

	    binder = new AnnotateDataBinder(comp);
		binder.bindBean("currentfrom", this.currentFrom);
		binder.bindBean("currentto", this.currentTo);
		binder.bindBean("current0", this.current0);
		binder.bindBean("current1", this.current1);
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		alias = (String) session.getAttribute("alias");
		this.branch = (String) this.session.getAttribute("branch");
		branch_p = (String) this.session.getAttribute("branch");
		this.un = (String) session.getAttribute("un");
		this.pw = (String) session.getAttribute("pwd");
		String[] group_id = (String[]) this.param.get("group_id");
		dataGrid.setRows(20);
		rcb_card_from.setModel(new ListModelList(CardtcService.getCardTypes(alias)));
		rcb_card_to.setModel(new ListModelList(CardtcService.getCardTypes(alias)));
		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Card card = (Card) data;
				row.setValue(card);
				row.appendChild(new Listcell(card.getBranch()));
				row.appendChild(new Listcell(card.getCard_number()));
				row.appendChild(new Listcell(card.getClient_code()));
				row.appendChild(new Listcell(card.getAccount()));
				row.appendChild(new Listcell(card.getName()));
				row.appendChild(new Listcell(card.getExpiry()));
				row.appendChild(new Listcell(card.getStatus()));
			}
		});

		dataGrid2.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Card card = (Card) data;
				row.setValue(card);
				row.appendChild(new Listcell(card.getBranch()));
				row.appendChild(new Listcell(card.getCard_number()));
				row.appendChild(new Listcell(card.getClient_code()));
				row.appendChild(new Listcell(card.getAccount()));
				row.appendChild(new Listcell(card.getName()));
				row.appendChild(new Listcell(card.getExpiry()));
				row.appendChild(new Listcell(card.getStatus()));
			}
		});

		dataGrid3.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {

				Card card = (Card) data;
				row.setValue(card);
				row.appendChild(new Listcell(card.getId()));// ID
				row.appendChild(new Listcell(card.getBranch()));// branch // row.appendChild(new
																// Listcell(df.format(card.getBank_time())));//bank_date
																// //row.appendChild(new
																// Listcell(df.format(card.getTime())));//date_time
				row.appendChild(new Listcell(card.getBank_time()));// bank_date
				row.appendChild(new Listcell(card.getTime()));// date_time
				row.appendChild(new Listcell(card.getFromcardtype()));// fromcardtype
				row.appendChild(new Listcell(card.getFromcardnumber()));// fromcardnumber
				row.appendChild(new Listcell(card.getFromcardbranch()));// fromcardbranch
				row.appendChild(new Listcell(card.getFromcardacc()));// fromcardacc
				row.appendChild(new Listcell(card.getFromcard_client_id()));// fromcard_client_id
				row.appendChild(new Listcell(card.getFromcard_client_name()));// fromcard_client_name
				row.appendChild(new Listcell(card.getTocardtype()));// tocardtype
				row.appendChild(new Listcell(card.getTocardnumber()));// tocardnumber
				row.appendChild(new Listcell(card.getTocardbranch()));// tocardbranch
				row.appendChild(new Listcell(card.getTocardacc()));// tocardacc
				row.appendChild(new Listcell(card.getTocard_client_id()));// tocard_client_id
				row.appendChild(new Listcell(card.getTocard_client_name()));// tocard_client_name
				row.appendChild(new Listcell(card.getState()));// state
			}
		});
		dataGrid3.setModel(new ListModelList(CardtcService.getProtocolById(un, pw, alias)));
		refreshModel(this._startPageNumber);


		initialGrid1$dataGrid4.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				final Card card = (Card) data;
				row.setValue(data);
				row.appendChild(new Listcell(card.getId()));// id
				row.appendChild(new Listcell(card.getCurrency()));// protocol_id
				row.appendChild(new Listcell(card.getTime()));// date_time
				row.appendChild(new Listcell(card.getClient_code()));// code
				if (card.getName().length() > 50) {
				    Listcell listcell = new Listcell();
				    Vlayout vlayout = new Vlayout(); // Vertical layout to place items in separate lines
				    Label truncatedLabel;
				    if(card.getName().length()>=80) {
				    	truncatedLabel = new Label(card.getName().substring(0, 80) + "...");
				    } else {
				    	truncatedLabel = new Label(card.getName());
				    }
				    final Button viewMoreButton = new Button("View More");
				    viewMoreButton.setVisible(true);
				    viewMoreButton.addEventListener("onClick", new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
						    final Window win = new Window("Full Value", "normal", true);
						    win.setWidth("400px");
						    win.setHeight("250px");
						    win.setSizable(true);
						    win.setClosable(true);
						    win.setPosition("center");
						    Vlayout winLayout = new Vlayout();
						    winLayout.setHflex("1");
						    winLayout.setVflex("1");
						    winLayout.setStyle("padding:10px;");
						    Div scrollableDiv = new Div();
						    scrollableDiv.setStyle("max-height:150px; overflow-y:auto; padding-right:5px;");
						    Label descLabel = new Label(card.getName());
						    ISLogger.getLogger().error("Label descLabel = new Label(card.getName()): " + descLabel);
						    System.out.println("Label descLabel = new Label(card.getName()): " + card.getName());
						    descLabel.setMultiline(true);
						    descLabel.setStyle("white-space: pre-line; word-wrap: break-word;");
						    scrollableDiv.appendChild(descLabel);
						    Button closeButton = new Button("Exit");
						    closeButton.addEventListener("onClick", new EventListener() {
						        @Override
						        public void onEvent(Event e) throws Exception {
						            win.detach();
						        }
						    });
						    winLayout.appendChild(scrollableDiv);
						    winLayout.appendChild(closeButton);
						    win.appendChild(winLayout);
						    win.setParent(viewMoreButton.getParent().getParent());
						    win.doModal();
						}

					});
				    vlayout.appendChild(truncatedLabel);
				    vlayout.appendChild(viewMoreButton);
				    listcell.appendChild(vlayout);
				    row.appendChild(listcell);
				} else {
				    row.appendChild(new Listcell(card.getName()));
				}

			}
		});

		initialGrid1$dataGrid5.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				Card card = (Card) data;
				row.setValue(data);
				row.appendChild(new Listcell(card.getId()));// id
				row.appendChild(new Listcell(card.getFromcardacc()));// acc_cl
				row.appendChild(new Listcell(card.getFromcardbranch()));// bank_cl
				row.appendChild(new Listcell(card.getFromcard_client_name()));// name_cl
				row.appendChild(new Listcell(card.getTocardacc()));// acc_co
				row.appendChild(new Listcell(card.getTocardbranch()));// bank_co
				row.appendChild(new Listcell(card.getTocard_client_name()));// name_co
				row.appendChild(new Listcell(card.getAmount()));// summa
				row.appendChild(new Listcell(card.getPurpose()));// purpose

			}
		});
	}

	public void onPaging$traccPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
	}

	// may be changed
	public void onClick$tbtn_search() {
		cardfilter.setClient_code(txbId_client.getValue());
		cardfilter.setCard_number(txbCard.getValue());
		cardfilter.setName(txbName.getValue());
		cardfilter.setPinfl(txbPinfl.getValue());
		if (Integer.parseInt(rcb_card_from.getValue()) == 11) {// HUMO
			ListModelList modelList = new ListModelList(CardtcService.getHumoCardsOld(cardfilter, alias), true);
			dataGrid.setModel(modelList);
			karta1 = "HUMO";
			if (modelList.getSize() > 0) {
				this.currentFrom = (Card) modelList.getElementAt(0);
			}
//		} else if (Integer.parseInt(rcb_card_from.getValue()) == 12) {// UZCARD
//			ListModelList modelList = new ListModelList(CardtcService.getUzcardCardsOld(cardfilter, alias), true);
//			dataGrid.setModel(modelList);
//			karta1 = "UZCARD";
//			if (modelList.getSize() > 0) {
//				this.currentFrom = (Card) modelList.getElementAt(0);
//			}
//		} else if (Integer.parseInt(rcb_card_from.getValue()) == 13) {// UZCARD DUO
//			alert("UZCARD DUO kartalar endi keladi!");
		} else if (Integer.parseInt(rcb_card_from.getValue()) == 14) {// VISA SUM
			ListModelList modelList = new ListModelList(CardtcService.getVisaSumCardsOld(cardfilter, alias), true);
			dataGrid.setModel(modelList);
			karta1 = "VISA SUM";
			if (modelList.getSize() > 0) {
				this.currentFrom = (Card) modelList.getElementAt(0);
			}
		} else {
			alert("Yuboruvchi karta turini tanlang");
		}
		ISLogger.getLogger().error("onClick$tbtn_search end! ");
	}

	public void onSelect$dataGrid() {
		if (rcb_card_to.getValue() != "") {
			dataGrid2.setModel(new ListModelList());
			Card filter_datagrid1 = (Card) dataGrid.getSelectedItem().getValue();
			ListModelList modelList1 = null;
			ListModelList modelList_visa = null;
			if (Integer.parseInt(rcb_card_to.getValue()) == 11) {
				modelList1 = new ListModelList(CardtcService.getHumoCards(filter_datagrid1, alias), true);
				karta2 = "HUMO";
//			} else if (Integer.parseInt(rcb_card_to.getValue()) == 12) {
//				modelList1 = new ListModelList(CardtcService.getUzcardCards(filter_datagrid1, alias), true);
//				karta2 = "UZCARD";
//			} else if (Integer.parseInt(rcb_card_to.getValue()) == 13) {
//				alert("UZCARD DUO kartalar endi keladi!");
			} else if (Integer.parseInt(rcb_card_to.getValue()) == 14) {
				String vc_pinfl = CardtcService.getVisaSumCardsPinfl(filter_datagrid1, alias);
				if(vc_pinfl == null || vc_pinfl.length()!=14) {
					alert("PINFL mavjud emas");
				}
				modelList1 = new ListModelList(CardtcService.getVisaSumCards(vc_pinfl, alias), true);
				karta2 = "VISA";
			}
			dataGrid2.setModel(modelList1);
		} else {
			alert("Qabul qiluvchi karta turini tanlang");
		}

	}

	@SuppressWarnings("resource")
	public void onClick$tbtn_move_saldo()
			throws InterruptedException, JsonParseException, JsonMappingException, IOException, JSONException {
		Card debit_card = (Card) dataGrid.getSelectedItem().getValue();
		Card credit_card = this.currentTo;

		if (dataGrid2.getItems().size() == 0) {
			alert("Bu mijozda kartalar topilmadi.");
			return;
		} else if (dataGrid2.getItems().size() == 1) {
			dataGrid2.setSelectedIndex(0);
			credit_card = (Card) dataGrid2.getSelectedItem().getValue();
		} else if (dataGrid2.getItems().size() >= 2) {
			if (dataGrid2.getSelectedItem() == null || dataGrid2.getSelectedItem().getValue() == null) {
				alert("Kartalardan birini tanlang. Qatorlar soni: " + dataGrid2.getItems().size());
				return;
			} else {
				credit_card = (Card) dataGrid2.getSelectedItem().getValue();
			}

		}
		String xabar = "";
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");			
			Long protocol_id;
	        
			String xabar2 = "";
			String xabar3 = "";
			String xabar4 = "";
			String xabar5 = "";
			String xabar8 = "";
			String xabar9 = "";
			String xabar10 = "";

			Ton ton = CardtcService.InsertProtocolTable(debit_card, credit_card, branch_p, karta1, karta2, un, pw,
					alias);
			if (ton.getCode() == 0L) {
				alert(ton.getName());
				return;
			} else {
				protocol_id = (long) ton.getCode();
			}
			Ton ton_1 = new Ton();
			ton_1 = ApiService.getCardBalance(debit_card, alias);
			amount = ApiService.result();
			if (amount == 0L) {
				amount += 1000L;
			}
			
			CardtcService.InsertDetailsTable(ton_1, protocol_id, debit_card, ton_1.getCode());
			if (ton_1.getName().contains("ERROR") || amount == 0L) {
				xabar2 = "\nKarta qoldig'ini olishda xatolik: " + ton_1.getName();
				xabar += xabar2;
				alert(xabar);
				return;
			} else {
				xabar2 += "\nKarta qoldig'ini olish muvaffaqiyatli bajarildi.";
				xabar += xabar2;
			}
			
			Ton ton_2 = new Ton(0, "", "");
			Ton ton_3 = new Ton(0, "", "");
			Ton ton_4 = new Ton(0, "", "");
			ton_2 = CardtcService.TRANSACT_XUMO_VISA_01(protocol_id, debit_card, credit_card, amount, un, pw, alias);
			CardtcService.InsertDetailsTable(ton_2, protocol_id, debit_card, ton_2.getCode());
			id_x = (long) ton_2.getCode();
			if (ton_2.getCode() == 0) {
				xabar3 += "\nTRANSACT_XUMO_VISA_01 da xatolik va xato bazaga yozildi.";
				xabar += xabar3;
				alert(xabar);
				return;
			} else {
				xabar3 += "\nTRANSACT_XUMO_VISA_01 muvaffaqiyatli bajarildi.";
				xabar += xabar3;
			}

			ton_2 = CardtcService.HUMO_PAYMENT_WRITEOFF(branch, credit_card.getCard_number(), un, pw, alias);
			CardtcService.InsertDetailsTable(ton_2, protocol_id, debit_card, ton_2.getCode());
			if (ton_2.getCode() == 0) {
				xabar4 += "\nHUMO_PAYMENT_WRITEOFF da xatolik va xato bazaga yozildi.";
				xabar += xabar4;
				alert(xabar);
				return;
			} else {
				xabar4 += "\nHUMO_PAYMENT_WRITEOFF muvaffaqiyatli bajarildi.";
				xabar += xabar4;
			}
			
			ton_2 = CardtcService.TRANSACT_XUMO_VISA_02(protocol_id, debit_card, credit_card, amount, un, pw, alias);
			CardtcService.InsertDetailsTable(ton_2, protocol_id, debit_card, ton_2.getCode());
			if (ton_2.getCode() == 0) {
				xabar4 += "\nTRANSACT_XUMO_VISA_02 da xatolik va xato bazaga yozildi.";
				xabar += xabar4;
				alert(xabar);
				return;
			} else {
				xabar4 += "\nTRANSACT_XUMO_VISA_02 muvaffaqiyatli bajarildi.";
				xabar += xabar4;
			}
			
			
			String nimadirdashu = "";

			String url1 = ApiService.getListCustomers(alias);
			String data1 = ApiService.forData1(credit_card.getClient_code());
			ton_2 = ApiService.sendData(url1, data1); 
			nimadirdashu = ton_2.getNode();
			ISLogger.getLogger().error(ton_2.getCode() + " : " + ton_2.getName() + " : " + ton_2.getNode()); 
			CardtcService.InsertDetailsTable(ton_2, protocol_id, credit_card, ton_2.getCode());
			if (ton_2.getCode() == 0) {
				alert(xabar+"\nListCustomers olishda xatolik va xato bazaga yozildi.");
				return;
			} 
			ISLogger.getLogger().error("extractClientCode ga kirayotgan NIMADIRDASHU: " + nimadirdashu);
			System.out.println("IF hamda extractClientCode dan oldin");
			ton_3 = ApiService.extractClientCode(nimadirdashu);
			if (ton_3.getName() != "" && !ton_3.getName().equals("")) {// CLIENT tekshirilyapti
				ISLogger.getLogger().error(ton_3.getName() + " - bo'sh emas");
			} else {
				ISLogger.getLogger().error(ton_3.getName() + " - bo'sh");
				ton_3.setName("CLIENT topilmaganiga sabab: " + ton_3.getName());
			}

			if (nimadirdashu != null && (nimadirdashu.contains("CLIENT") && nimadirdashu.contains("STATUS")
					&& nimadirdashu.contains("CLIENT_B") && ton_3.getName() != null && ton_3.getName().length() > 0)) {// STATUS, CLIENT_B borligini tekshirish yetadi

				client_code_1 = ton_3.getName();
				ISLogger.getLogger().error("Xususiy bo'lmagan, JSON dan olingan holdagi CLIENT_CODE_1: " + client_code_1);
				client_code_1 = "00116504";
				ISLogger.getLogger().error("Xususiy bo'lgan, JSON dan olinmagan holdagi CLIENT_CODE_1: " + client_code_1);
				ton_3.setName("VisaTiGV/ListCustomers muvaffaqqiyatli amalga oshirildi: JSON dan olingan CLIENT ==> "
						+ client_code_1);
				ISLogger.getLogger().error(
						"extractClientCode ga kirgan NIMADIRDASHU dan olingan client_code_1: " + ton_3.getName());
			} else {
				ISLogger.getLogger().error(
						"ton_3.getNode() bush ekan yoki CLIENT, VALUE suzi topilmadi. mana ton_3 ni uzi : " + ton_3);
			}
			ISLogger.getLogger().error(ton_3.getCode() + " : " + ton_3.getName() + " : " + ton_3.getNode() + " : "
					+ protocol_id + " : " + credit_card.toString());
			CardtcService.InsertDetailsTable(ton_3, protocol_id, credit_card, ton_3.getCode());
			if (ton_3.getCode() == 0) {
				xabar5 += "\nCLIENT ajratib olishda xatolik va xato bazaga yozildi.";
				xabar += xabar5;
				alert(xabar);
				return;
			} else {
				xabar5 += "\nCLIENT ajratib olish muvaffaqiyatli bajarildi.";
				xabar += xabar5;
			}

			
			String url4 = ApiService.getListAccounts(alias);
			String data4 = ApiService.forData4(client_code_1);
			ton_2 = ApiService.sendData(url4, data4);
			ton_4 = ApiService.extractCardAcct(ton_2.getNode(), "UZS", "200");//jsonString, cardAcct
			ISLogger.getLogger().error("ApiService.extractCardAcct: " + ton_4.getName());
			CardtcService.InsertDetailsTable(ton_2, protocol_id, credit_card, ton_2.getCode());
			if (ton_2.getCode() == 0) {
				xabar8 += "\nVisaTiGV/ListAccounts da xatolik va xato bazaga yozildi.";
				xabar += xabar8;
				return;
			} else {
				xabar8 += "\nVisaTiGV/ListAccounts muvaffaqiyatli bajarildi.";
				xabar += xabar8;
			}
			
			String url5 = ApiService.getExecuteTransactions(alias);
			String msg = "Popolneniye " + debit_card.getName() + " na PK " + karta2 + " N" + debit_card.getCard_number(); //debit_card.getName - ism, karta2 - karta nomi,  debit_card.getCard_number() - karta nomeri
	        String time1 = formatter.format(date);
	        String time = time1.replaceAll("(\\+\\d{2})(\\d{2})$", "$1:$2");
	        ISLogger.getLogger().error(time);
			String amnt = amount.toString();
			String data5 = ApiService.forData5(ton_4.getName(), amnt, time, msg);
			ton_2 = ApiService.sendData(url5, data5);
			CardtcService.InsertDetailsTable(ton_2, protocol_id, credit_card, ton_2.getCode());
			if (ton_2.getCode() == 0) {
				xabar9 += "\nVisaTiGV/ExecuteTransaction da xatolik va xato bazaga yozildi.";
				xabar += xabar9;
				return;
			} else {
				xabar9 += "\nVisaTiGV/ExecuteTransaction muvaffaqiyatli bajarildi.";
				xabar += xabar9;
			}
			
			ton_2 = CardtcService.TRANSACT_XUMO_VISA_01(protocol_id, debit_card, credit_card, amount, un, pw, alias);
			CardtcService.InsertDetailsTable(ton_2, protocol_id, debit_card, ton_2.getCode());
			id_x = (long) ton_2.getCode();
			if (ton_2.getCode() == 0) {
				xabar3 += "\nTRANSACT_XUMO_VISA_01 da xatolik va xato bazaga yozildi.";
				xabar += xabar3;
				alert(xabar);
				return;
			} else {
				xabar3 += "\nTRANSACT_XUMO_VISA_01 muvaffaqiyatli bajarildi.";
				xabar += xabar3;
			}

			String url6 = ApiService.BlockHumoCard(alias);
			String data6 = ApiService.forData6(credit_card.getCard_number());
			ton_2 = ApiService.sendData(url6, data6);
			CardtcService.InsertDetailsTable(ton_2, protocol_id, credit_card, ton_2.getCode());
			if (ton_2.getCode() == 0) {
				xabar10 += "\nblock-card da xatolik va xato bazaga yozildi.";
				xabar += xabar10;
				return;
			} else {
				xabar10 += "\nblock-card muvaffaqiyatli bajarildi.";
				xabar += xabar10;
			}
			
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		alert(xabar);

	}

	public void onSelect$protocolTab() {
		List<Card> list = CardtcService.getProtocolById(un, pw, alias);
		dataGrid3.setModel(new ListModelList(list));
	}

	public void onDoubleClick$dataGrid3() {
		initialGrid1.setVisible(true);
		initialGrid1$dataGrid5.setVisible(false);
		initialGrid1$general_name.setVisible(false);
		Card card1 = (Card) dataGrid3.getSelectedItem().getValue();
		x = Integer.parseInt(card1.getId());
		String id = card1.getId(); // System.out.println("String id = card1.getClient_code(); ====> " + id);
		List<Card> list = CardtcService.getDetailsById(id);// karta -> protocol tablitsa karta, protokol tablitsa karta
															// -> protokol tablitsa id;
		
		initialGrid1$dataGrid4.setModel(new ListModelList(list));
		initialGrid1$name.setValue(id);
	}

	public void onDoubleClick$dataGrid4() {
		initialGrid1_more.setVisible(true);
		Card card1 = (Card) initialGrid1$dataGrid4.getSelectedItem().getValue();
		System.out.println(card1.getName());
		initialGrid1_more$apimore.setValue(card1.getName());
	}
	
	public void onClick$btn_exit1$initialGrid1_more() {
		initialGrid1_more.setVisible(false);
	}
	
	public void onClick$dataGrid4$initialGrid1() { // System.out.println("onClick$dataGrid3$initialGrid1 id ====> " +
													// x);
		Card card1 = (Card) initialGrid1$dataGrid4.getSelectedItem().getValue();
		System.out.println("CODE: " + card1.getClient_code());
		if(Integer.parseInt(card1.getClient_code())!=1 && Integer.parseInt(card1.getClient_code())!=0) {
			initialGrid1$general_name.setVisible(true);
			List<Card> list1 = CardtcService.getTransactionFromGeneral(x);
			initialGrid1$dataGrid5.setModel(new ListModelList(list1));
			initialGrid1$dataGrid5.setVisible(true);
		} else {
			initialGrid1$general_name.setVisible(false);
			initialGrid1$dataGrid5.setVisible(false);
		}
		
	}

	public void onClick$btn_hide$initialGrid1() {
		initialGrid1$dataGrid5.setVisible(false);
		initialGrid1$general_name.setVisible(false);
	}

	public void onClick$btn_exit$initialGrid1() {
		initialGrid1.setVisible(false);
		initialGrid1$dataGrid5.setModel(new ListModelList());
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
		/*
		 * if (dataGrid.getSelectedIndex() == 0) { btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); } else { btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); } if (dataGrid.getSelectedIndex() ==
		 * (model.getSize() - 1)) { btn_next.setDisabled(true);
		 * btn_last.setDisabled(true); } else { btn_next.setDisabled(false);
		 * btn_last.setDisabled(false); }
		 */
		SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
		Events.sendEvent(evt);
	}

	public void onClick$btn_add() {
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onDoubleClick$account() {
		// chacc$acc.setModel(
		// new BindingListModelList(CardtcService.getAccount(current, "", alias,
		// acc_mfo.getValue()), false));
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
		// chacc$acc.setModel(new BindingListModelList(
		// CardtcService.getAccount(current, chacc$acc_filter_mask.getValue(),
		// alias, acc_mfo.getValue()), false));
	}
	
	private void refreshModel(int activePage) {
	    if (protocolPaging == null) {
	        throw new IllegalStateException("protocolPaging is not initialized.");
	    }
	    model = new PagingListModel(activePage, _pageSize, filter, alias);  
	    _totalSize = model.getTotalSize(filter, alias); 
	    protocolPaging.setTotalSize(_totalSize);
	    dataGrid3.setModel(model);  
	    if (model.getSize() > 0) {
	        current0 = (Card) model.getElementAt(0);
	        SelectEvent evt = new SelectEvent("onSelect", dataGrid3, dataGrid3.getSelectedItems());
	        Events.sendEvent(evt);
	    }
	}

	public void onPaging$protocolPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		refreshModel(_startPageNumber);
	}
	
	public Card getCurrentFrom() {
		return currentFrom;
	}

	public void setCurrentFrom(Card currentFrom) {
		this.currentFrom = currentFrom;
	}

	public Card getCurrentTo() {
		return currentTo;
	}

	public void setCurrentTo(Card currentTo) {
		this.currentTo = currentTo;
	}

	public Card getCurrent0() {
		return current0;
	}

	public void setCurrent0(Card current0) {
		this.current0 = current0;
	}

	public Card getCurrent1() {
		return current1;
	}

	public void setCurrent1(Card current1) {
		this.current1 = current1;
	}
	
}
