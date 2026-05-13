package com.is.tieto_visa.tieto;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.kernel.parameter.Parameters;
import com.is.tieto_visa.customer.Customer;
import com.is.tieto_visa.customer.CustomerFilter;
import com.is.tieto_visa.customer.CustomerService;
import com.is.tieto_visa.customer.CustomerService.link;
import com.is.tieto_visa.customer.TietoCustomer;
import com.is.trpay.TrPay;
import com.is.user.Action;
import com.is.utils.CheckNull;
import com.is.utils.NilProvider;
import com.is.utils.RefCBox;
import com.is.utils.Res;

import accounting_transaction_4.Transaction_service;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.type.OrientationEnum;
import visa.issuing_v_01_02_xsd.OperationConnectionInfo;
import visa.issuing_v_01_02_xsd.OperationResponseInfo;
import visa.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request;
import visa.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request;

public class TclientViewCtrlOld extends GenericForwardComposer {
	private visa.IssuingWS.IssuingPortProxy issuingPortProxy;
	private static NilProvider np = null;
	private GeneralInfo generalInfo = new GeneralInfo();
	private Window paywnd, blockwnd, printwnd, addwnd;
	private Iframe printwnd$rpframe;
	private Decimalbox paywnd$amnt;
	private Div frm;
	private Listbox dataGrid, accGrid, paywnd$payGrid;
	private Label paywnd$add_currency_type;
	private Div grd;
	private Grid addgrd, frmgrd, fgrd;
	private Grid lfrmgrd, mfrmgrd, rfrmgrd;
	private Toolbarbutton paywnd$btn_block, paywnd$btn_unblock;
	private Toolbarbutton paywnd$fbt, paywnd$lock, paywnd$application;
	private Toolbar paywnd$pay_tlb;
	private Textbox client, bank_c, client_b, cl_type, cln_cat, rec_date, f_names, surname, title, m_name, b_date,
			r_street, r_city, r_cntry, usrid, ctime, status, search_name, sex, serial_no, doc_since, issued_by,
			status_change_date, blockwnd$txtstopcauses, paywnd$search_name, paywnd$address;
	private Textbox aclient, abank_c, aclient_b, acl_type, acln_cat, arec_date, af_names, asurname, atitle, am_name,
			ab_date, ar_street, ar_city, ar_cntry, ausrid, actime, astatus, asearch_name, asex, aserial_no, adoc_since,
			aissued_by, astatus_change_date, paywnd$curacc;
	private Textbox fclient, fbank_c, fclient_b, fcl_type, fcln_cat, ff_names, fsurname, ftitle, fm_name, fr_street,
			fr_city, fr_cntry, fusrid, fctime, fstatus, fsearch_name, fsex, fserial_no, fdoc_since, fissued_by,
			fstatus_change_date, fcard, paywnd$inc_ord_num;
	private Paging tclientPaging;
	private Datebox fb_date, frec_date;
	private RefCBox paywnd$scurracc, blockwnd$sstopcauses, addwnd$sproduct, paywnd$list_amnt;
	private int _pageSize = 10;
	private int _startPageNumber = 0;
	private int _totalSize = 0;
	private boolean _needsTotalSizeUpdate = true;
	private String un, pwd, branch, alias;
	private int uid;
	private TclientFilter filter = new TclientFilter();
	private Tclient current = new Tclient();
	private Customer customer = new Customer();
	private CardInf cardinfo = new CardInf();
	private CustomerFilter customerFilter = new CustomerFilter();
	private AccInfo accinfo = new AccInfo();
	private com.is.tieto_visa.customer.PagingListModel CustPagingListModel;

	PagingListModel model = null;
	ListModelList lmodel = null;
	private AnnotateDataBinder binder;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
	private SimpleDateFormat expdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));

	private DecimalFormat dmf = new DecimalFormat("0.00##");
	private visa.IssuingWS.IssuingPortProxy pp = null;
	private static HashMap<String, String> _tstopCauses = new HashMap<String, String>();

	private int pay_card_doc_id;
	private String curip, cur_act;
	private NumberFormat nf = NumberFormat.getInstance();
	private String VISA_BANK_C, VISA_GROUPC, VISA_BINCOD;

	public TclientViewCtrlOld() {
		super('$', false, false);
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// TODO Auto-generated method stub
		binder = new AnnotateDataBinder(comp);

		binder.bindBean("current", this.current);
		binder.bindBean("cardinfo", this.cardinfo);

		binder.loadAll();

		String[] parameter = (String[]) param.get("ht");
		if (parameter != null) {
			_pageSize = Integer.parseInt(parameter[0]) / 60;
			// dataGrid.setRows(Integer.parseInt(parameter[0]) / 60);
		}

		nf.setGroupingUsed(false);
		un = (String) session.getAttribute("un");
		pwd = (String) session.getAttribute("pwd");
		uid = (Integer) session.getAttribute("uid");

		branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");

		VISA_BANK_C = ConnectionPool.getValue("VISA_BANK_C", alias);
		VISA_GROUPC = ConnectionPool.getValue("VISA_GROUPC", alias);
		VISA_BINCOD = TclientService.getEmpcBincodFromDB();
		TclientService tclientService = new TclientService(alias);

		curip = (String) session.getAttribute("curip");
		_tstopCauses = TclientService.getHTstopCauses(alias);

		try {
			issuingPortProxy = initNp(issuingPortProxy, alias);
		} catch (Exception e) {
			e.printStackTrace();

			Messagebox.show("[Coonection Problem]=>" + e.getLocalizedMessage(), e.getMessage(),
					Messagebox.RETRY | Messagebox.CANCEL, Messagebox.ERROR, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							// TODO Auto-generated method stub
							int answer = (Integer) event.getData();
							if (answer == Messagebox.RETRY) {
								issuingPortProxy = initNp(issuingPortProxy, alias);
							} else
								return;
						}
					});

		}

		addwnd$sproduct.setModel((new ListModelList(com.is.utils.RefDataService.getOfrProd(alias))));

		dataGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Tclient pTclient = (Tclient) data;

				row.setValue(pTclient);
				row.appendChild(new Listcell(pTclient.getClient_b()));
				row.appendChild(new Listcell(pTclient.getClient()));
				row.appendChild(new Listcell(pTclient.getF_names()));
				row.appendChild(new Listcell(pTclient.getSurname()));

				String listDate = pTclient.getB_date() == null ? "" : (df.format(pTclient.getB_date())).toString();
				row.appendChild(new Listcell(listDate));

			}
		});

		accGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				CardInf cInfo = (CardInf) data;

				row.setValue(cInfo);
				row.appendChild(new Listcell(cInfo.getCARD()));
				row.appendChild(new Listcell(cInfo.getEXPIRY().substring(0, 10)));
				row.appendChild(new Listcell(cInfo.getCARD_ACCT()));
				row.appendChild(new Listcell(cInfo.getBank_account()));
				row.appendChild(new Listcell(_tstopCauses.get(cInfo.getBank_account_status())));

				Double amt = Double.valueOf(0);
				if (cInfo.getACCOUNT_AVAIL_AMOUNT()!=null)
				amt = cInfo.getACCOUNT_AVAIL_AMOUNT().doubleValue() / 100;

				row.appendChild(new Listcell(amt == 0 ? "0" : customFormat("###,###,###,###,###.00", amt)));

			}
		});

		paywnd$payGrid.setItemRenderer(new ListitemRenderer() {
			@SuppressWarnings("unchecked")
			public void render(Listitem row, Object data) throws Exception {
				Bf_tieto_trans bf_tieto_trans = (Bf_tieto_trans) data;

				row.setValue(bf_tieto_trans);

				row.appendChild(new Listcell(bf_tieto_trans.getBRANCH()));
				row.appendChild(new Listcell(bf_tieto_trans.getAccount()));
				row.appendChild(new Listcell(df.format(bf_tieto_trans.getTRDATE())));
				row.appendChild(new Listcell(TclientService.getStateDesc(bf_tieto_trans.getSTATE(), alias)));
				row.appendChild(new Listcell(bf_tieto_trans.getSUMMA() + ""));
			}
		});

		// refreshModel(_startPageNumber); //hamza zaremil

	}

	/*
	 * public void onPaging$tclientPaging(ForwardEvent event) { final
	 * PagingEvent pe = (PagingEvent) event.getOrigin(); _startPageNumber =
	 * pe.getActivePage(); refreshModel(_startPageNumber); }
	 */

	private void refreshModel(int activePage) {
		// tclientPaging.setPageSize(_pageSize);
		// _pageSize = TclientService.getCount(filter, alias, issuingPortProxy);

		model = new PagingListModel(activePage, _pageSize, filter, alias, issuingPortProxy);

		dataGrid.setModel((ListModel) model);

		/*
		 * if (_needsTotalSizeUpdate) { _totalSize = model.getTotalSize(filter,
		 * alias); }
		 */
		// tclientPaging.setTotalSize(_totalSize);
		if (model.getSize() > 0) {
			sendSelEvt();
		}
	}

	// Omitted...
	public Tclient getCurrent() {
		return current;
	}

	public CardInf getCardinfo() {
		return cardinfo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCurrent(Tclient current) {
		this.current = current;
	}

	public void setCardinfo(CardInf cardinfo) {
		this.cardinfo = cardinfo;
	}

	public AccInfo getAccinfo() {
		return accinfo;
	}

	public void setAccinfo(AccInfo accinfo) {
		this.accinfo = accinfo;
	}

	public TclientFilter getFilter() {
		return filter;
	}

	public void setFilter(TclientFilter filter) {
		this.filter = filter;
	}

	/*
	 * public void onDoubleClick$dataGrid$grd() { }
	 */

	public void onClick$btn_back() {
		if (frm.isVisible()) {
			frm.setVisible(false);
			grd.setVisible(true);
			// btn_back.setImage("/images/file.png");
			// btn_back.setLabel(Labels.getLabel("back"));
		} // else onDoubleClick$dataGrid$grd();
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
		 * if (dataGrid.getSelectedIndex()==0){ btn_first.setDisabled(true);
		 * btn_prev.setDisabled(true); }else{ btn_first.setDisabled(false);
		 * btn_prev.setDisabled(false); }
		 * if(dataGrid.getSelectedIndex()==(model.getSize()-1)){
		 * btn_next.setDisabled(true); btn_last.setDisabled(true); }else{
		 * btn_next.setDisabled(false); btn_last.setDisabled(false); }
		 * SelectEvent evt = new SelectEvent("onSelect",
		 * dataGrid,dataGrid.getSelectedItems()); Events.sendEvent(evt);
		 */
	}

	public void onClick$btn_add() {
		// onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(true);
		fgrd.setVisible(false);
	}

	public void onClick$btn_search() {
		// onDoubleClick$dataGrid$grd();
		frmgrd.setVisible(false);
		addgrd.setVisible(false);
		fgrd.setVisible(true);
	}

	public void onClick$btn_save() {
		try {
			filter = new TclientFilter();

			// filter.setClient(fclient.getValue());
			filter.setClient(fclient_b.getValue());
			filter.setF_names(ff_names.getValue());
			filter.setSurname(fsurname.getValue());

			refreshModel(_startPageNumber);
			SelectEvent evt = new SelectEvent("onSelect", dataGrid, dataGrid.getSelectedItems());
			Events.sendEvent(evt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onClick$btn_cancel() {

		filter = new TclientFilter();

		CheckNull.clearForm(fgrd);
		refreshModel(_startPageNumber);
	}

	public void onSelect$dataGrid() {
		if (current != null) {
			Res res = new Res();

			String F_names = current.getF_names() == null ? "" : current.getF_names();
			String Surname = current.getSurname() == null ? "" : current.getSurname();
			String M_name = current.getM_name() == null ? "" : current.getM_name();
			String Full_name = F_names + " " + Surname + " " + M_name;

			if (current.getSearch_name() == null)
				current.setSearch_name(Full_name);

			System.out.println("current.getSearch_name()->" + current.getSearch_name());
			System.out.println("alias->" + alias);
			System.out.println("branch->" + branch);
			System.out.println("current.getClient()->" + current.getClient());
			System.out.println("Calling TclientService.check_user....");
			LtLogger.getLogger().error("not err 0");
			res = TclientService.check_user(alias, branch, current.getClient());

			if (res.getCode() == 0)
				alert(res.getName());

			// List<CardInfo> cardInfos = TclientService.getAccInfo(current,
			// alias, issuingPortProxy).get(0).getCardlist();
			LtLogger.getLogger().error("not err 1");
			List<AccInfo> accInfos = TclientService.getAccInfo(current.getClient(), alias, issuingPortProxy);
			LtLogger.getLogger().error("not err accInfos.size()=" + accInfos.size() + "");
			if (accInfos.size() > 0) {
				List<CardInf> cardInfos = accInfos.get(0).getCardlist();
				accGrid.setModel(new BindingListModelList(cardInfos, false));
			} else {
				LtLogger.getLogger().error("not err 3");
				alert("accInfos пустая");
			}
		}
	}

	public void onSelect$accGrid() {
		this.cardinfo = (CardInf) accGrid.getSelectedItem().getValue();
	}

	public void onDoubleClick$accGrid() {
		Res res = new Res();
		res = TclientService.check_user(alias, branch, current.getClient());
		if (res.getCode() == 0) {
			alert(res.getName());
			List<CardInf> cardInfos = new ArrayList<CardInf>();
			if (current.getClient() != null)
				cardInfos = TclientService.getAccInfo(current.getClient(), alias, issuingPortProxy).get(0).getCardlist();
			accGrid.setModel(new BindingListModelList(cardInfos, false));
			return;
		}

		link lnk = CustomerService.get_link_tieto(current.getClient(), branch, alias);
		paywnd$curacc.setValue(lnk.Cur_acc);

		CardInf selectedCard = (CardInf) accGrid.getSelectedItem().getValue();

		paywnd$payGrid.setModel(
				new ListModelList(TclientService.getBf_tieto_trans(selectedCard.getBank_account(), branch, alias)));
		// paywnd$list_amnt.setSelectedIndex(-1);
		paywnd$amnt.setValue(BigDecimal.valueOf(0));
		// System.out.println("branch=");
		// paywnd$list_amnt.setModel((new
		// ListModelList(TclientService.getPayments4Tieto(branch,
		// selectedCard.getBank_account(), alias))));
		paywnd$search_name.setValue(current.getSearch_name());
		paywnd$address.setValue(current.getR_street());
		paywnd$search_name.setReadonly(true);
		paywnd$address.setReadonly(true);
		paywnd$lock.setImage("/images/locked.png");

		List<Action> actions;
		actions = TclientService.getActions2(uid, branch, alias, "");
		paywnd$pay_tlb.getChildren().clear();
		for (int i = 0; i < actions.size(); i++) {
			paywnd$fbt = new Toolbarbutton();
			paywnd$fbt.setLabel(actions.get(i).getName());
			paywnd$fbt.setImage(actions.get(i).getIcon());
			paywnd$fbt.setAttribute("act_desc", actions.get(i).getName());
			// paywnd$fbt.setAttribute("state_id", states.get(i).getId());
			paywnd$fbt.setAttribute("deal_id", actions.get(i).getDeal_id());
			paywnd$fbt.setAttribute("act_id", actions.get(i).getId());
			// paywnd$fbt.setAttribute("rep_type_id",
			// actions.get(i).getRep_type_id());
			paywnd$fbt.setWidth("1000px");
			paywnd$fbt.addEventListener(Events.ON_CLICK, new EventListener() {
				@Override
				public void onEvent(Event event) {
					cur_act = (String) event.getTarget().getAttribute("act_desc");
					pay((Integer) event.getTarget().getAttribute("act_id"),
							(Integer) event.getTarget().getAttribute("act_id"),
							(Integer) event.getTarget().getAttribute("act_id"));
				}
			});
			paywnd$pay_tlb.appendChild(paywnd$fbt);
		}


		paywnd.setVisible(true);
	}

	public void onClick$btn_cancel$paywnd() {
		paywnd$amnt.setValue(BigDecimal.ZERO);
		paywnd.setVisible(false);
	}

/*
 	private void pay(int deal_id, int rep_type_id, int act_id) {
		Res res = new Res();
		res = TclientService.check_user(alias, branch, current.getClient());
		if (res.getCode() == 0) {
			alert(res.getName());
			accGrid.setModel(
					new BindingListModelList(TclientService.getAccInfo(current, alias, issuingPortProxy), false));
			return;
		}

		res = TclientService.check_allowed_card_action(144, deal_id, act_id, accinfo.getProduct(), alias);
		if (res.getCode() != 0) {
			alert(res.getName());
			return;
		}

		try {

			TrPay trp = new TrPay();
			trp.setAccount_no(accinfo.getAccount_no() + "");
			trp.setAmount(paywnd$amnt.getValue().intValue());

			
			 // alert("AMOUNT at form:"+paywnd$amnt.getValue()+
			 // "\ndoubleamount:"+paywnd$amnt.getValue().doubleValue()+
			 // "\namount *100:"+trp.getAmount()+ "\namount setparam:"
			 // +(long)trp.getAmount());
			 

			trp.setCard_acc(accinfo.getTranz_acct());
			// System.out.println(accinfo.getProduct()+":
			// "+accinfo.getProduct_name());
			trp.setCur_acc(paywnd$curacc.getValue());
			trp.setCl_name(current.getSearch_name());
			// trp.setIn_name(paywnd$search_name.getValue());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(accinfo.getCard());
			trp.setEmp_id(uid);
			trp.setDoc_num(paywnd$inc_ord_num.getValue());
			// trp.setIn_address(paywnd$address.getValue());
			// trp.setCard_acc("22618840999177381001");
			// trp.setCur_acc("20206840299177381001");

			Res rs = TrPayService.doAction(un, pwd, trp, act_id, deal_id, alias);
			if (rs.getCode() < 0) {
				alert(rs.getName());
			} else {
				TrPay trp_res = TrPayService.getTrPay(Integer.parseInt(rs.getName()), alias);
				trp_res.setCl_name(current.getSearch_name());
				// trp_res.setIn_name(paywnd$search_name.getValue());
				// trp_res.setIn_address(paywnd$address.getValue());
				if (rep_type_id != 0)
					printp(trp_res, TclientService.get_report_file(deal_id, act_id, alias));

				String oper_desc = TrPayService.getOperation_desc(trp_res.getOperation_id(), alias);

				String log = "Операция [" + oper_desc + "] id [" + trp_res.getId() + "] действие [" + cur_act
						+ "] подгруппы [" + TrPayService.getDeal_desc(deal_id, alias) + "] No карты ["
						+ trp_res.getPan() + "] счет карты [" + trp_res.getCard_acc() + "] " + "(Отправитель ["
						+ paywnd$search_name.getValue() + "] Адрес [" + paywnd$address.getValue() + "])";
				// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1,
				// log, branch));

				pay_card_doc_id = Integer.parseInt(rs.getName());
				System.out.println("new document for card pay id:" + pay_card_doc_id);
			}

		} catch (Exception e) {
			alert(e.getMessage());
			LtLogger.getLogger().error("__________________PAY_ERROR(Tclient_VC s 550):\n" + CheckNull.getPstr(e));
			e.printStackTrace();
		}

		paywnd.setVisible(false);
		onSelect$dataGrid();
	}
  */
	
	
	public void onSelect$list_amnt$paywnd() {
		long genid = Long.parseLong(paywnd$list_amnt.getValue());

		if (CheckNull.isEmpty(genid) || genid == 0) {
			alert("Выберите документ для оплаты!");
			return;
		} else {
			generalInfo = TclientService.getSummOfPayment(alias, genid);
			if (generalInfo.getID() > 0) {
				paywnd$amnt.setValue(generalInfo.getSUMMA().toString());
				paywnd$inc_ord_num.setValue(generalInfo.getDOC_NUM());
			} else {
				alert("Сумма не указана!");
				return;
			}
		}
	}

	public void onClick$btn_pay$paywnd() {
		Res res = TclientService.check_user(alias, branch, current.getClient());
		if (res.getCode() == 0) {
			alert("Клиент не объединен");
			return;
		}

		Long TIETOID = Long.parseLong("0");
		int STATE = 2;

		Long account_no = 222222L, operationId;
		Res rs = new Res();
		Connection c = null;
		rs.setName("Ok");
		Transaction_service transactionService = null;
		Parameters ps = new Parameters();

		try {
			if (paywnd$amnt.getValue().intValue() == 0) {
				alert("Сумма должна быть больше 0, выберите другой документ для пополнения");
				return;
			}

			TrPay trp = new TrPay();
			trp.setAccount_no(cardinfo.getACCOUNT_NO() + "");
			//trp.setAmount(paywnd$amnt.getValue().intValue());
			trp.setAmount(BigDecimal.valueOf(paywnd$amnt.getValue().intValue()));
			trp.setCard_acc(cardinfo.getBank_account());
			trp.setCur_acc(paywnd$scurracc.getValue());
			trp.setCl_name(current.getSearch_name());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(cardinfo.getCARD());
			trp.setEmp_id(uid);
			trp.setDoc_num(generalInfo.getDOC_NUM());

			// res = TclientService.checkBfTietoTrans();
			// if (res.getCode() == 1) {

			// c = ConnectionPool.getConnection(alias);
			c = ConnectionPool.getConnection(un, pwd, alias);
			transactionService = new Transaction_service(c);
			long trId;
			List<AccInfo> acc = TclientService.getAccInfo(current.getClient(), alias, issuingPortProxy);
			/*
			 * for (int i = 0; i < acc.size(); i++) { if
			 * (acc.get(i).getTranz_acct().equals(ce + ".getAcc_ct()")) {
			 * account_no = acc.get(i).getAccount_no(); card_acct =
			 * acc.get(i).getCard_acct(); com.is.ISLogger.getLogger().warn(
			 * "=====     account_no=" + account_no + "  card_acct=" + card_acct
			 * + " BANK_SCHEMA=" + BANK_SCHEMA + "  tieto_id=" + tieto_id); } }
			 */

			ObjectMapper objectMapper = new ObjectMapper();
			String str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(acc);
			com.is.ISLogger.getLogger().error("** HAMZA Object  **************" + str1);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("branch", "00444");
			parameters.put("operation_id", Long.valueOf("224002"));
			parameters.put("parent_group_id", 224l);
			parameters.put("parent_deal_id", 1);
			parameters.put("SUMMA", "1500");
			parameters.put("PARENT_ID", Long.parseLong("1"));
			parameters.put("CARD_ACC", "22618840999141210001");
			// parameters.put("DT_CARD_ACC", "ce.getAcc_dt()");
			// parameters.put("CT_CARD_ACC", "ce.getAcc_ct()");
			// parameters.put("AMNTCOUR", "ce.getAmount() * GetCource(840, 4)");
			operationId = Long.parseLong("224002");
			//trId = transactionService.create_pay(operationId, "00444", 1500L, 1l, 224l, 1, parameters);
			//transactionService.execute_transaction_action("00444", trId, 19); // 19
																				// //
																				// 23
			rs.setCode(0);
			rs.setName("Ok");
			c.commit();

			//res = TclientService.sendPayment(trp, alias, cardinfo.getBank_account_Ccy(), issuingPortProxy);
			if (res.getCode() != 1) {
				alert("Операция не выполнена, INTERNAL_NO = > " + res.getName());
				return;
			} else {
				Float summa = paywnd$amnt.getValue().floatValue() / 100;
				alert("Произведено пополнение на сумму " + summa + " [" + cardinfo.getBank_account_Ccy()
						+ "] Номер транзакции = > " + res.getName());
				TIETOID = Long.parseLong(res.getName());
				STATE = 1;
			}

		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		} finally {
			if (c != null)
				ConnectionPool.close(c);
		}
		paywnd.setVisible(false);
		// onSelect$dataGrid();
	}

	private void pay(int deal_id, int rep_type_id, int act_id) {
		Res res = new Res();
		res = TclientService.check_user(alias, branch, current.getClient());
		if (res.getCode() == 0) {
			alert("Клиент не объединен");
			return;
		}

		Long TIETOID = Long.parseLong("0");
		int STATE = 2;

		Long account_no = 222222L, operationId=new Long(act_id);
		Res rs = new Res();
		Connection c = null;
		rs.setName("Ok");
		Transaction_service transactionService = null;
		Parameters ps = new Parameters();

		try {
			if (paywnd$amnt.getValue().intValue() == 0) {
				alert("Сумма должна быть больше 0");
				return;
			}

			TrPay trp = new TrPay();
			trp.setAccount_no(cardinfo.getACCOUNT_NO() + "");
			//trp.setAmount(paywnd$amnt.getValue().intValue());
			trp.setAmount(BigDecimal.valueOf(paywnd$amnt.getValue().intValue()));
			trp.setCard_acc(cardinfo.getBank_account());
			trp.setCur_acc(paywnd$scurracc.getValue());
			trp.setCl_name(current.getSearch_name());
			trp.setBranch(branch);
			trp.setOperation_id(1);
			trp.setPan(cardinfo.getCARD());
			trp.setEmp_id(uid);
			trp.setDoc_num(generalInfo.getDOC_NUM());

			// res = TclientService.checkBfTietoTrans();
			// if (res.getCode() == 1) {

			// c = ConnectionPool.getConnection(alias);
			c = ConnectionPool.getConnection(un, pwd, alias);
			transactionService = new Transaction_service(c);
			long trId;
			List<AccInfo> acc = TclientService.getAccInfo(current.getClient(), alias, issuingPortProxy);
			/*
			 * for (int i = 0; i < acc.size(); i++) { if
			 * (acc.get(i).getTranz_acct().equals(ce + ".getAcc_ct()")) {
			 * account_no = acc.get(i).getAccount_no(); card_acct =
			 * acc.get(i).getCard_acct(); com.is.ISLogger.getLogger().warn(
			 * "=====     account_no=" + account_no + "  card_acct=" + card_acct
			 * + " BANK_SCHEMA=" + BANK_SCHEMA + "  tieto_id=" + tieto_id); } }
			 */

			ObjectMapper objectMapper = new ObjectMapper();
			String str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(acc);
			com.is.ISLogger.getLogger().error("** HAMZA Object  **************" + str1);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("branch", branch);
			parameters.put("operation_id", operationId);
			parameters.put("parent_group_id", 224l);
			parameters.put("parent_deal_id", 1);
			parameters.put("SUMMA", paywnd$amnt.getValue());
			parameters.put("PARENT_ID", Long.parseLong("1"));
			parameters.put("CARD_ACC", trp.getCard_acc());

			//trId = transactionService.create_pay(operationId, branch, new Long(trp.getAmount()), 1l, 224l, 1, parameters);
			trId = transactionService.create_pay(operationId, branch, trp.getAmount().longValue(), 1l, 224l, 1, parameters);
			transactionService.execute_transaction_action(branch, trId, 19); // 19
																				// //
																				// 23
			rs.setCode(0);
			rs.setName("Ok");
			c.commit();

			//res = TclientService.sendPayment(trp, alias, cardinfo.getBank_account_Ccy(), issuingPortProxy);
			if (res.getCode() != 1) {
				alert("Операция не выполнена, INTERNAL_NO = > " + res.getName());
				return;
			} else {
				Float summa = paywnd$amnt.getValue().floatValue() / 100;
				alert("Произведено пополнение на сумму " + summa + " [" + cardinfo.getBank_account_Ccy()
						+ "] Номер транзакции = > " + res.getName());
				TIETOID = Long.parseLong(res.getName());
				STATE = 1;
			}

		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		} finally {
			if (c != null)
				ConnectionPool.close(c);
		}
		paywnd.setVisible(false);
		onSelect$dataGrid();
	}

	
	public void onClick$btn_block$paywnd() {
		blockwnd$sstopcauses.setModel(new ListModelList(TclientService.getTstopCauses(alias)));
		// blockwnd$txtstopcauses.setValue("");
		blockwnd.setVisible(true);
	}

	public void onClick$btn_cancel$blockwnd() {
		blockwnd.setVisible(false);
	}

	public void onClick$btn_block$blockwnd() {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_AddCardToStopList_Request parameters = new RowType_AddCardToStopList_Request();

		try {
			connectionInfo.setBANK_C(ConnectionPool.getValue("VISA_BANK_C", alias));
			connectionInfo.setGROUPC(ConnectionPool.getValue("VISA_GROUPC", alias));

			parameters.setBANK_C(ConnectionPool.getValue("VISA_BANK_C", alias));
			parameters.setGROUPC(ConnectionPool.getValue("VISA_GROUPC", alias));
			parameters.setSTOP_CAUSE(blockwnd$sstopcauses.getValue());// parameters.setSTOP_CAUSE("1");
			parameters.setTEXT(blockwnd$txtstopcauses.getValue());
			parameters.setCARD(accinfo.getCard());

			orInfo = pp.addCardToStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0) {
				alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
			}
			// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1,
			// "Карта No ["+accinfo.getCard()+"] заблокирована", branch));

		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		}

		paywnd.setVisible(false);
		blockwnd.setVisible(false);
		onSelect$dataGrid();
	}

	public void onClick$btn_unblock$paywnd() {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();
		OperationResponseInfo orInfo = null;
		RowType_RemoveCardFromStop_Request parameters = new RowType_RemoveCardFromStop_Request();

		try {
			connectionInfo.setBANK_C(ConnectionPool.getValue("VISA_BANK_C", alias));
			connectionInfo.setGROUPC(ConnectionPool.getValue("VISA_GROUPC", alias));

			parameters.setBANK_C(ConnectionPool.getValue("VISA_BANK_C", alias));
			parameters.setGROUPC(ConnectionPool.getValue("VISA_GROUPC", alias));
			parameters.setTEXT("Card UnBlocked!!!!");
			parameters.setCARD(accinfo.getCard());

			orInfo = pp.removeCardFromStop(connectionInfo, parameters);
			if (orInfo.getResponse_code().intValue() != 0) {
				alert(orInfo.getError_action() + "\r\n" + orInfo.getError_description());
			}
			// UserService.UsrLog(new UserActionsLog(uid, un, curip, 6, 1,
			// "Карта No ["+accinfo.getCard()+"] разблокирована", branch));
		} catch (Exception e) {
			alert(e.getMessage());
			e.printStackTrace();
		}

		paywnd.setVisible(false);
		onSelect$dataGrid();
	}

	public void onOK$fclient() {
		onClick$btn_save();
	}

	public void onOK$fcard() {
		onClick$btn_save();
	}

	public void onOK$fbank_c() {
		onClick$btn_save();
	}

	public void onOK$fclient_b() {
		onClick$btn_save();
	}

	public void onOK$fcl_type() {
		onClick$btn_save();
	}

	public void onOK$fcln_cat() {
		onClick$btn_save();
	}

	public void onOK$frec_date() {
		onClick$btn_save();
	}

	public void onOK$ff_names() {
		onClick$btn_save();
	}

	public void onOK$fsearch_name() {
		onClick$btn_save();
	}

	public void onOK$fsurname() {
		onClick$btn_save();
	}

	public void onOK$fb_date() {
		onClick$btn_save();
	}

	public void onOK$fserial_no() {
		onClick$btn_save();
	}

	public void printp(TrPay trp, String report_file) {
		printwnd.setVisible(true);
		// printwnd$btn_print.setVisible(false);

		JasperPrint jasperPrint;
		AMedia repmd = null;

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("TR_PAY_ID", trp.getId());
		params.put("V_DATE", df.format(trp.getDate_created()));
		// params.put("CLIENT_NAME1", current.getF_names());
		params.put("CLIENT_NAME1", trp.getCl_name());
		params.put("CLIENT_NAME2", "");
		params.put("CLIENT_NAME3", "");
		// params.put("CLIENT_NAME2", current.getM_name());
		// params.put("CLIENT_NAME3", current.getSurname());
		// params.put("INCLIENT_NAME1", trp.getIn_name());
		params.put("INCLIENT_NAME2", "");
		params.put("INCLIENT_NAME3", "");
		params.put("T_CURRENCY", "USD");
		params.put("TDP_NUM", trp.getDoc_num());
		// params.put("POST_ADDRESS", trp.getIn_address());
		//params.put("SUMMA6", Double.toString(trp.getAmount() / 100));
		params.put("SUMMA6", Double.toString(trp.getAmount().intValue() / 100));
		params.put("ESUMMA6", nf.format(trp.getEqv_amount() / 100));
		//params.put("PSUMMA6", com.is.utils.CheckNull.F2Money(trp.getAmount() / 100, "доллар", "центов"));
		params.put("PSUMMA6", com.is.utils.CheckNull.F2Money(trp.getAmount().intValue() / 100, "доллар", "центов"));
		params.put("TVEOPER", accinfo.getTranz_acct());
		params.put("OPENDOPER", trp.getCur_acc());
		params.put("ACCDOPER1", com.is.tieto_visa.tieto.TclientService.getkass_acc(branch, alias));
		params.put("BRANCH_NAME", com.is.utils.RefDataService.getMfo_name(branch, alias).get(0).getLabel());

		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);
			jasperPrint = JasperFillManager.fillReport(application.getRealPath(report_file), params, c);
			jasperPrint.setLeftMargin(0);
			jasperPrint.setRightMargin(0);
			jasperPrint.setTopMargin(0);
			jasperPrint.setBottomMargin(0);
			jasperPrint.setPageHeight(595);// (461);//
			jasperPrint.setPageWidth(842);// (652);//
			jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);
			final byte[] buf = JasperRunManager.runReportToPdf(application.getRealPath(report_file), params, c);
			final java.io.InputStream mediais = new ByteArrayInputStream(buf);

			repmd = new AMedia(current.getF_names() + "_" + current.getM_name() + ".pdf", "pdf", "application/pdf",
					mediais);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (repmd != null) {
			printwnd$rpframe.setContent(repmd);

		}
	}

	public void onClick$btn_cancel$printwnd() {
		printwnd.setVisible(false);
	}

	public void onClick$btn_add$addwnd() {

		TietoCardSetting tcs = TclientService.getTietoCardSetting(Integer.parseInt(addwnd$sproduct.getValue()), alias);
		TietoCustomer tc;
		tc = com.is.tieto_visa.customer.CustomerService.getTietoCustomer(current.getClient(), branch, alias);
	}

	public void onClick$btn_cancel$addwnd() {
		addwnd.setVisible(false);
	}

	public void onClick$lock$paywnd() {
		if (paywnd$lock.getImage().compareTo("/images/opened.png") == 0) {
			paywnd$search_name.setReadonly(true);
			paywnd$address.setReadonly(true);
			paywnd$lock.setImage("/images/locked.png");
			return;
		}
		paywnd$search_name.setReadonly(false);
		paywnd$address.setReadonly(false);
		paywnd$lock.setImage("/images/opened.png");
	}

	private static visa.IssuingWS.IssuingPortProxy initNp(visa.IssuingWS.IssuingPortProxy issuingPortProxy,
			String alias) {
		if (np == null) {
			np = new NilProvider();
			np.init();
		}

		return issuingPortProxy = new visa.IssuingWS.IssuingPortProxy(ConnectionPool.getValue("TIETO_VISA_HOST", alias),
				ConnectionPool.getValue("TIETO_VISA_HOST_USERNAME", alias),
				ConnectionPool.getValue("TIETO_VISA_HOST_PASSWORD", alias));

	}

	public String customFormat(String pattern, double value) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		return output;
	}
}
