package com.is.card_to_card;

import java.text.SimpleDateFormat;
import java.util.List;
import org.zkoss.zul.ListModelList;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

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
	private Listbox dataGridDetails, dataGrid, chacc$acc, dataGrid2, dataGrid3, initialGrid1$dataGrid4;
	private Paging contactPaging;
	private Div grd1, grd3;
	private Vlayout grd2;
	private Window initialGrid1;
	private Grid addgrd, frmgrd, fgrd, initialGrid;
	private Toolbarbutton btn_last, tbtn_search;
	private Toolbarbutton btn_next;
	private Toolbarbutton btn_prev;
	private Toolbarbutton btn_first;
	private Toolbarbutton btn_add;
	private Toolbarbutton btn_search;
	private Toolbarbutton btn_back, initialGrid1$btn_exit, btn_save;
	private Toolbarbutton tbtn_settings, tbtn_move_saldo;
	private Toolbar tb;
	private Label initialGrid1$name;
	private Textbox acc_mfo, account, acc_name, chacc$acc_filter_mask;
	private Textbox aacc_mfo, aaccount, aacc_name, brunch;
	private Textbox facc_mfo, faccount, facc_name, mod_type, txbId_client, txbCard, txbName, txbPinfl;
	private RefCBox rcb_card_from, rcb_card_to;
	private Intbox id, aid, fid, acc_template_id, aacc_template_id, facc_template_id;
	private Paging traccPaging;
	private int _pageSize = 14;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String mbranch, alias, branch, un, pw;
//	private ListModelList userListModel;
	public CardFromApi cardfromapifilter, alias1;
	public CardFromApi apifilter;
	public Card filter = new Card();
	public Card filterforcard_1 = new Card();
	public Card filterforcard_2 = new Card();
	public CardFilter cardfilter = new CardFilter();
	public CardFilter cardfilter2 = new CardFilter();
	public String API_error;
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

	/**
	 *
	 *
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		binder = new AnnotateDataBinder(comp);
		binder.bindBean("currentfrom", this.currentFrom);
		binder.bindBean("currentto", this.currentTo);
		binder.bindBean("current0", this.current0);
		binder.bindBean("current1", this.current1);
//		dataGrid4 = (Listbox) comp.getFellow("dataGrid4");
		binder.loadAll();
		String[] parameter = (String[]) param.get("ht");
		alias = (String) session.getAttribute("alias");
		this.branch = (String) this.session.getAttribute("branch");
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
				row.appendChild(new Listcell(card.getClient_code()));
				row.appendChild(new Listcell(card.getName()));
				row.appendChild(new Listcell(card.getCard_number()));
				row.appendChild(new Listcell(card.getContract()));
				row.appendChild(new Listcell(card.getCurrency()));
			}
		});

//		dataGrid4.setItemRenderer(new ListitemRenderer() {
//			@SuppressWarnings("unchecked")
//			public void render(Listitem row, Object data) throws Exception {
//
//				Card card = (Card) data;
//				row.setValue(card);
//				row.appendChild(new Listcell(card.getClient_code()));
//                row.appendChild(new Listcell(card.getName()));
//                row.appendChild(new Listcell(card.getCard_number()));
//                row.appendChild(new Listcell(card.getStatus()));
//			}
//		});

//		dataGrid4.setItemRenderer(new ListitemRenderer() {
//		@SuppressWarnings("unchecked")
//			public void render(Listitem row, Object data) throws Exception {
//				Card card = (Card) data;
//				row.setValue(card);
//				row.appendChild(new Listcell(card.getClient_code()));
//				row.appendChild(new Listcell(card.getName()));
//				row.appendChild(new Listcell(card.getCard_number()));
//				row.appendChild(new Listcell(card.getStatus()));
//			}
//		});

		initialGrid1$dataGrid4.setItemRenderer(new ListitemRenderer() {
			@Override
			public void render(Listitem row, Object data) throws Exception {
				Card card = (Card) data;
				row.setValue(data);
				row.appendChild(new Listcell(card.getClient_code()));
				row.appendChild(new Listcell(card.getName()));
				row.appendChild(new Listcell(card.getCard_number()));
				row.appendChild(new Listcell(card.getStatus()));
				row.appendChild(new Listcell(card.getCurrency()));

			}
		});

		rcb_card_from.setValue("UZCARD");
		rcb_card_to.setValue("HUMO");
		txbName.setValue("MIRZAEV BAKHTIYOR");
		List<Card> list = CardtcService.getProtocolByCardNumber();
		ListModelList userListModel = new ListModelList(list);
		dataGrid3.setModel(userListModel);
		System.out.println("dataGrid4: " + initialGrid1$dataGrid4);
		System.out.println("dataGrid4 is " + (initialGrid1$dataGrid4 == null ? "null" : "not null"));
	}

	public void onPaging$traccPaging(ForwardEvent event) {
		final PagingEvent pe = (PagingEvent) event.getOrigin();
		_startPageNumber = pe.getActivePage();
		// refreshModel(_startPageNumber);
	}

	private void refreshModel(int activePage) {
//		 traccPaging.setPageSize(_pageSize);
//		model = new PagingListModel(activePage, _pageSize, filter, alias);
//		if (_needsTotalSizeUpdate) {
//			_totalSize = model.getTotalSize(filter, alias);
//			_needsTotalSizeUpdate = false;
//		}
//		 traccPaging.setTotalSize(_totalSize);
//		dataGrid.setModel((ListModel) model);
//		if (model.getSize() > 0) {
//			this.current = (TrAcc) model.getElementAt(0);
//			this.current = (Card) model.getElementAt(0);
//			sendSelEvt();
//		}
	}

	public void onClick$tbtn_search() {
		cardfilter.setClient_code(txbId_client.getValue());
		cardfilter.setCard_number(txbCard.getValue());
		cardfilter.setName(txbName.getValue());
		cardfilter.setPinfl(txbPinfl.getValue());
		System.out.println("-=-=-=-=-=->>>>>>> start ");
		System.out.println(cardfilter.getClient_code() + " - " + cardfilter.getCard_number() + " - "
				+ cardfilter.getName() + " - " + cardfilter.getPinfl());
		if (Integer.parseInt(rcb_card_from.getValue()) == 11) {
			ListModelList modelList = new ListModelList(CardtcService.getHumoCardsOld(cardfilter, alias), true);
			dataGrid.setModel(modelList);
			if (modelList.getSize() > 0) {
				this.currentFrom = (Card) modelList.getElementAt(0);
			}
		} else if (Integer.parseInt(rcb_card_from.getValue()) == 12) {
			ListModelList modelList = new ListModelList(CardtcService.getUzcardCardsOld(cardfilter, alias), true);
			dataGrid.setModel(modelList);
			if (modelList.getSize() > 0) {
				this.currentFrom = (Card) modelList.getElementAt(0);
			}
		} else if (Integer.parseInt(rcb_card_from.getValue()) == 13) {
			alert("UZCARD DUO kartalar endi keladi!");
		} else if (Integer.parseInt(rcb_card_from.getValue()) == 14) {
			ListModelList modelList = new ListModelList(CardtcService.getVisaSumCardsOld(cardfilter, alias), true);
			dataGrid.setModel(modelList);
			if (modelList.getSize() > 0) {
				this.currentFrom = (Card) modelList.getElementAt(0);
			}
		}
		ISLogger.getLogger().error("onClick$tbtn_search end! ");
		System.out.println("onClick$tbtn_search ====>>>> " + rcb_card_from.getValue());
	}

	public void onSelect$dataGrid() {
		if (rcb_card_to.getValue() != "") {
			dataGrid2.setModel(new ListModelList());
			Card filter_datagrid1 = (Card) dataGrid.getSelectedItem().getValue();
			filterforcard_1 = (Card) filter_datagrid1;

			System.out.println("filter_datagrid1");
			System.out.println(toString(filter_datagrid1));
			System.out.println("filterforcard_1");
			System.out.println(toString(filterforcard_1));

			if (Integer.parseInt(rcb_card_to.getValue()) == 11) {
				ListModelList modelList1 = new ListModelList(CardtcService.getHumoCards(filter_datagrid1, alias), true);
				System.out.println(toStringFilterCardNumber(CardtcService.getHumoCards(filter_datagrid1, alias)));
				dataGrid2.setModel(modelList1);
				if (modelList1.getSize() > 0) {
					this.currentFrom = (Card) modelList1.getElementAt(0);
				}
			} else if (Integer.parseInt(rcb_card_to.getValue()) == 12) {
				ListModelList modelList2 = new ListModelList(CardtcService.getUzcardCards(filter_datagrid1, alias),
						true);
				System.out.println(toStringFilterCardNumber(CardtcService.getUzcardCards(filter_datagrid1, alias)));
				dataGrid2.setModel(modelList2);
				if (modelList2.getSize() > 0) {
					this.currentFrom = (Card) modelList2.getElementAt(0);
				}
			} else if (Integer.parseInt(rcb_card_to.getValue()) == 13) {
				alert("UZCARD DUO kartalar endi keladi!");
			} else if (Integer.parseInt(rcb_card_to.getValue()) == 14) {
				ListModelList modelList4 = new ListModelList(CardtcService.getVisaSumCards(filter_datagrid1, alias),
						true);
				System.out.println(toStringFilterCardNumber(CardtcService.getVisaSumCards(filter_datagrid1, alias)));
				dataGrid2.setModel(modelList4);
				if (modelList4.getSize() > 0) {
					this.currentFrom = (Card) modelList4.getElementAt(0);
				}
			}
		} else {
			alert("Qabul qiluvchi karta turini tanlang");
		}
	}

	public void onSelect$dataGrid2() {
		Card filter_datagrid2 = (Card) dataGrid2.getSelectedItem().getValue();
		filterforcard_2 = (Card) filter_datagrid2; // istalgan qator tanlandi
		System.out.println("filterforcard_1");
		System.out.println(toString(filterforcard_1));
		System.out.println("filterforcard_2");
		System.out.println(toString(filterforcard_2));
	}

	public void onClick$btn_exit$initialGrid1() {
		initialGrid1.setVisible(false);
	}

	public void onDoubleClick$dataGrid3() {
		initialGrid1.setVisible(true);
		Card card1 = (Card) dataGrid3.getSelectedItem().getValue();
		List<Card> list = CardtcService.getDetailsByName(card1);
		initialGrid1$dataGrid4.setModel(new ListModelList(list));
		initialGrid1$name.setValue(card1.getClient_code());
//		initialGrid1$name.setValue(CardtcService.getId(card1));
	}

	public String toStringCardNumber(Card selectedCard) {
		return selectedCard.getCard_number(); // faqat karta
	}

	public String toStringFilterCardNumber(List<Card> list) {
		StringBuilder sb = new StringBuilder("");
		for (Card card : list) {
			sb.append(card.getCard_number()); // faqat karta
		}
		return sb.toString();
	}

	public String toString(Card selectedCard) { // umumiy
		return "Card {" + "branch='" + selectedCard.getBranch() + '\'' + ", card_number='"
				+ selectedCard.getCard_number() + '\'' + ", account='" + selectedCard.getAccount() + '\'' + ", name='"
				+ selectedCard.getName() + '\'' + ", expiry='" + selectedCard.getExpiry() + '\'' + ", status='"
				+ selectedCard.getStatus() + '\'' + '}';
	}

	public String toStringApi(CardFromApi selectedCard) { // umumiy
		return "Card {" + "branch='" + selectedCard.getId() + '\'' + ", card_number='" + selectedCard.getUserId() + '\''
				+ ", account='" + selectedCard.getTitle() + '\'' + ", name='" + selectedCard.isCompleted() + '\'' + '}';
	}

	public String toStringFilter(List<Card> list) {// umumiy
		StringBuilder sb = new StringBuilder("Cards List:\n");
		for (Card card : list) {
			sb.append("Card {").append("branch='").append(card.getBranch()).append('\'').append(", card_number='")
					.append(card.getCard_number()).append('\'').append(", account='").append(card.getAccount())
					.append('\'').append(", name='").append(card.getName()).append('\'').append(", expiry='")
					.append(card.getExpiry()).append('\'').append(", status='").append(card.getStatus()).append('\'')
					.append("}\n");
		}
		return sb.toString();
	}

	public void onClick$tbtn_move_saldo() throws InterruptedException {
		CardtcService.InsertToProtocolTable(filterforcard_1, filterforcard_2);
		CardtcService.InsertToDetailsTable(filterforcard_1, CardtcService.getIdFromDetails());

		System.out.println(CardtcService.getIdFromDetails() + " ======== ID");
	}

	public void onDoubleClick$dataGrid$grd1() {
		grd2.setVisible(false);
		frm.setVisible(true);
		frmgrd.setVisible(true);
		addgrd.setVisible(false);
		fgrd.setVisible(false);
		btn_back.setImage("/images/folder.png");
		btn_back.setLabel(Labels.getLabel("grid"));
		// if ((current != null) && (current.getAccount() == null)) {
		// current.setAccount("");
		// }
		// if(!current.getAcc_mfo().equals(current.getBranch()))

		// if((!current.getBranch().equals(current.getAcc_mfo()))&&(mbranch.compareTo(ConnectionPool.getValue("HO",
		// alias))!=0))
		// if ((current != null) &&
		// (!current.getBranch().equals(current.getAcc_mfo()))
		// && (mbranch.compareTo(ConnectionPool.getValue("HO", alias)) != 0)
		// && mod_type.getValue().compareTo("usual") == 0) {
		// btn_save.setDisabled(true);
		// acc_mfo.setDisabled(true);
		// account.setDisabled(true);
		// acc_name.setDisabled(true);
		// } else {
		// btn_save.setDisabled(false);
		// acc_mfo.setDisabled(false);
		// account.setDisabled(false);
		// acc_name.setDisabled(false);
		// }
		// account.setDisabled((current != null) &&
		// (current.getAccount().toUpperCase().contains("ACC")));

	}

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd1.setVisible(true);
			btn_back.setImage("/images/file.png");
			btn_back.setLabel(Labels.getLabel("back"));
		} else
			onDoubleClick$dataGrid$grd1();
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

//	public void onClick$btn_cancel() {
//		if (fgrd.isVisible()) {
//			filter = new Card();
//			if (mod_type.getValue().compareTo("usual") == 0)
//				filter.setBranch(mbranch);
//		}
//		onClick$btn_back();
//		frmgrd.setVisible(true);
//		addgrd.setVisible(false);
//		fgrd.setVisible(false);
//		CheckNull.clearForm(addgrd);
//		CheckNull.clearForm(fgrd);
//		// refreshModel(_startPageNumber);
//	}

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
