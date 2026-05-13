package com.is.tieto_visae.tieto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.kernel.parameter.Parameters;
import com.is.report.DPrint;
import com.is.tieto_visae.customer.Customer;
import com.is.tieto_visae.customer.CustomerService;
import com.is.trpay.TrPay;
import com.is.utils.NilProvider;
import com.is.utils.Res;

import accounting_transaction_4.Transaction_service;
import java.util.Locale;

public class ConfirmRefillViewCtrl extends GenericForwardComposer {

	private static final long serialVersionUID = 1L;
	private visa.IssuingWS.IssuingPortProxy issuingPortProxy;
	private static NilProvider np = null;
	private TclientFilter filter_cl = new TclientFilter();
	private Tclient current_cl = new Tclient();
	private Customer customer = new Customer();
	private CardInfo cardinfo = new CardInfo();
	// private CustomerFilter customerFilter = new CustomerFilter();
	private AccInfo accinfo = new AccInfo();
	private static DecimalFormat nf = null;
	private SimpleDateFormat longdf = new SimpleDateFormat("dd MMMM yyyy",
			new Locale("ru"));
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private static HashMap<String, String> sval = null;

	private Listbox mainList;

	private ConfirmRefill current = new ConfirmRefill();

	public ConfirmRefill getCurrent() {
		return current;
	}

	public void setCurrent(ConfirmRefill current) {
		this.current = current;
	}

	private ConfirmRefillFilter filter;
	private final static int pageSize = 12;
	private ConfirmRefillPaging paging = new ConfirmRefillPaging(pageSize);
	private Paging confirmRefillPaging;
	private Window printwnd;
	private Iframe printwnd$rpframe;
	private Toolbarbutton btn_cancel$printwnd;
	private ConfirmRefillService service;

	private Map<String, String> states;
	private Map<String, String> opertypes;
	private Map<Long, ConfirmRefill> currents = new HashMap<Long, ConfirmRefill>();
	private String[] access;
	private String user, pass, alias, ses_branch;
	private int currentPageNumber = 0;

	public ConfirmRefillViewCtrl() {
		super('$', false, false);
		service = new ConfirmRefillService();
		states = service.getRefillStates();
		opertypes = service.getOperTypes();
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		nf = new DecimalFormat("#,###.00");

		AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		access = (String[]) this.param.get("access");
		alias = (String) session.getAttribute("alias");
		user = (String) session.getAttribute("un");
		pass = (String) session.getAttribute("pwd");
		ses_branch = (String) session.getAttribute("branch");

		filter = new ConfirmRefillFilter();
		filter.setBranch(ses_branch);
		// if ((access[0].equals("approve"))) {
		// filter.setState(Constants.REFILL_STATE_ADDED.toString());
		// } else if(access[0].equals("confirm")){
		// filter.setState(Constants.REFILL_STATE_CONFIRMED.toString());
		// }

		mainList.setItemRenderer(new ListitemRenderer() {

			@Override
			public void render(Listitem row, Object data) {
				ConfirmRefill confirmRefill = (ConfirmRefill) data;
				row.setValue(confirmRefill);
				row.appendChild(new Listcell(StringUtils
						.secureNull(confirmRefill.getCard())));
				row.appendChild(new Listcell(StringUtils
						.secureNull(confirmRefill.getClient_name())));
				row.appendChild(new Listcell(StringUtils
						.secureNull(confirmRefill.getClient_surname())));
				// row.appendChild(new
				// Listcell(StringUtils.secureNull(confirmRefill.getTransaction_amnt())));
				// row.appendChild(new Listcell(
				// (confirmRefill.getTransaction_amnt() != null) ? (new Double
				// (confirmRefill.getTransaction_amnt().doubleValue()/100)).toString()
				// : ""));
				row.appendChild(new Listcell((confirmRefill
						.getTransaction_amnt() != null) ? nf
						.format(new Double(confirmRefill.getTransaction_amnt()
								.doubleValue() / 100)) : ""));

				// nf.format(item.getBalance().getAvail_amount())
				row.appendChild(new Listcell(
						confirmRefill.getState() != null ? states
								.get(confirmRefill.getState()) : ""));
				row.appendChild(new Listcell(confirmRefill.getErr_msg()));
				row.appendChild(new Listcell(
						confirmRefill.getType_oper() != null ? opertypes
								.get(confirmRefill.getType_oper().toString())
								: ""));

				currents.put(new Long(confirmRefill.getId()), confirmRefill);

				Listcell listcell = new Listcell();

				List<Action> availableActions = service
						.getAvailableActionsForItem(confirmRefill, user, pass,
								alias);

				for (int i = 0; i < availableActions.size(); i++) {
					Button commonBtn = new Button();
					commonBtn.setLabel(availableActions.get(i).getName());
					commonBtn.addEventListener(Events.ON_CLICK, commonAction);
					commonBtn.setAttribute("id", confirmRefill.getId());
					commonBtn.setAttribute("action_id", availableActions.get(i)
							.getAction_id());
					listcell.appendChild(commonBtn);
				}
				row.appendChild(listcell);
			}

		});

		refreshModel();

		try {
			issuingPortProxy = initNp(issuingPortProxy, alias);
		} catch (Exception e) {
			e.printStackTrace();

			Messagebox.show("[Coonection Problem]=>" + e.getLocalizedMessage(),
					e.getMessage(), Messagebox.RETRY | Messagebox.CANCEL,
					Messagebox.ERROR, new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							// TODO Auto-generated method stub
							int answer = (Integer) event.getData();
							if (answer == Messagebox.RETRY) {
								issuingPortProxy = initNp(issuingPortProxy,
										alias);
							} else
								return;
						}
					});

		}

	}

	private EventListener commonAction = new EventListener() {

		@Override
		public void onEvent(Event event) throws NumberFormatException,
				Exception {
			String id = (String) event.getTarget().getAttribute("id");
			String action_id = (String) event.getTarget().getAttribute(
					"action_id");

			ConfirmRefill currfill = currents.get(new Long(id));

			// service.setRefillState(id, Constants.REFILL_STATE_CONFIRMED);
			// ConfirmRefill refill = new ConfirmRefill();
			// refill.setBranch(ses_branch);
			// refill.setId(id);
			Connection c = ConnectionPool.getConnection(user, pass, alias);
			try {
				Res res = service.doAction(currfill,
						Integer.parseInt(action_id), user, pass, alias, c);
				if (res.getName().equals("3"))// если проведен, пополняем виза
				{
					res = Provodka_I_Popolnenie(currfill, c);
				}

				// if (isDbConnected(c))
				// System.out.println(" +01 cn=open");

				if (res.getCode() != -1) {
					if (c != null) {
						c.commit();
					}
					alert("Успешно.");
				} else {
					// if (isDbConnected(c))
					// System.out.println(" +02 cn=open");

					if (c != null) {
						c.rollback();
					}
					alert(res.getName());

					SimpleDateFormat sdf = new SimpleDateFormat(
							"dd.MM.yyyy HH:mm:ss");
					Timestamp timestamp = new Timestamp(
							System.currentTimeMillis());
					String sss = sdf.format(timestamp)
							+ ". Действие:"
							+ service.getActionById(action_id, user, pass,
									alias).getName() + ". Ошибка: "
							+ res.getName();
					if (sss.length() >= 1000)
						sss = sss.substring(1, 999);
					currfill.setErr_msg(sss);
					WriteErrMsg(currfill);
				}

			} catch (Exception e) {
				alert("Ошибка " + e.getMessage());

				if (c != null) {
					c.rollback();
				}
			} finally {
				// if (isDbConnected(c))
				// System.out.println(" +03 cn=open");

				try {
					if (c != null)
						ConnectionPool.close(c);
				} catch (Exception e) {
				}
				;
			}
			// if (isDbConnected(c))
			// System.out.println(" +04 cn=open");

			refreshModel();
			// if (isDbConnected(c))
			// System.out.println(" +05 cn=open");

		}

	};

	private EventListener confirmAction = new EventListener() {

		@Override
		public void onEvent(Event event) {
			long id = (Long) event.getTarget().getAttribute("id");
			service.setRefillState(id, Constants.REFILL_STATE_CONFIRMED);

			refreshModel();
		}

	};

	public Res Provodka_I_Popolnenie(ConfirmRefill fill, Connection cn) {
		// long id = (Long) event.getTarget().getAttribute("id");
		ResponseInfo response;
		Res res = new Res();
		Res rs = new Res();
		// Connection c = null;
		// rs.setName("Ok");
		Transaction_service transactionService = null;
		Parameters ps = new Parameters();

		try {
			long operationId = fill.getType_oper();

			// c = ConnectionPool.getConnection(user, pass, alias);
			transactionService = new Transaction_service(cn);
			long trId;
			// List<AccInfo> acc = TclientService.getAccInfo(fill.client_tieto,
			// alias, issuingPortProxy);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("branch", fill.getBranch());
			parameters.put("operation_id", operationId);
			parameters.put("parent_group_id", 224l);
			parameters.put("parent_deal_id", 1);
			parameters.put("SUMMA", fill.getTransaction_amnt());
			parameters.put("PARENT_ID", Long.parseLong("1"));
			parameters.put("CARD_ACC", fill.getTranz_acct());
			parameters.put("TVISA_CARD_ACC", fill.getTranz_acct());			
			String acc20206 = ConfirmRefillService
					.GetCurAcc(fill.getClient_bank(), "20206", "840",
							fill.getBranch(), cn);
			parameters.put("CUR_ACC", acc20206);
			parameters.put("TVISA_CUR_ACC", acc20206);			
			// 23104840 можно только для ИП
			String acc23104 = ConfirmRefillService
					.GetCurAcc(fill.getClient_bank(), "23104", "840",
							fill.getBranch(), cn);
			parameters.put("ACC_TRAN", acc23104);
			parameters.put("TVISA_ACC_TRAN", acc23104);			
			if (isDbConnected(cn))
				System.out.println(" 000 cn=open");

			trId = transactionService.create_pay(operationId, fill.getBranch(),
					fill.getTransaction_amnt().longValue(), 1l, 224l, 1,
					parameters);
			if (isDbConnected(cn))
				System.out.println(" 001 cn=open");

			transactionService.execute_transaction_action(fill.getBranch(),
					trId, 19); // 23
			if (isDbConnected(cn))
				System.out.println(" 002 cn=open");

			TrPay trp = new TrPay();
			//trp.setAmount(fill.getTransaction_amnt().intValue());
			/*com.is.ISLogger.getLogger().error("not err start");
			try {
				trp.setAmount(new BigDecimal(fill.getTransaction_amnt().intValue()));	
			}
			catch(Exception e) {
				com.is.ISLogger.getLogger().error("not err  trp.setAmount(new BigDecimal(fill.getTransaction_amnt().intValue()))");
			}
			try {
				trp.setAmount(fill.getTransaction_amnt());	
			}
			catch(Exception e) {
				com.is.ISLogger.getLogger().error("not err  fill.getTransaction_amnt()");
			}
			com.is.ISLogger.getLogger().error("not err start 2");
			*/
			trp.setAmount(fill.getTransaction_amnt());
			//com.is.ISLogger.getLogger().error("not err start 3");
			
			trp.setAccount_no(fill.getAccount_no());
			trp.setCard_acc(fill.getTranz_acct());

			// res = TclientService.sendPayment(trp, alias,
			// fill.getCard_acc_ccy(), issuingPortProxy);
			res = TclientService
					.sendPayment(trp, alias, fill, issuingPortProxy);

			if (res.getCode() != 1) {
				// alert("Операция не выполнена, INTERNAL_NO = > " +
				// res.getName());
				rs.setCode(-1);
				rs.setName(res.getName());
				return rs;
			}
			rs.setCode(0);
			rs.setName("Ok");
		} catch (SQLException e) {
			rs.setCode(-1);
			rs.setName(e.getMessage() + " - " + rs.getName());
		} catch (CloneNotSupportedException e) {
			rs.setCode(-1);
			rs.setName(e.getMessage());
		}
		return rs;
	}

	public boolean isDbConnected(Connection con) {
		try {
			if (!con.isClosed() || con != null) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	public Res WriteErrMsg(ConfirmRefill fill) {
		// long id = (Long) event.getTarget().getAttribute("id");
		Res res = new Res();
		Res rs = new Res();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(user, pass, alias);
			res = service.doAction(fill, 7, user, pass, alias, c);
			if (res.getCode() != -1) {
				if (c != null)
					c.commit();
			} else {
				if (c != null)
					c.rollback();
			}
		} catch (SQLException e) {
			rs.setCode(-1);
			rs.setName(e.getMessage() + " - " + rs.getName());
		} finally {
			if (c != null)
				ConnectionPool.close(c);
		}
		return rs;
	}

	private void refreshModel() {
		confirmRefillPaging.setPageSize(pageSize);
		confirmRefillPaging.setTotalSize(paging.getTotalSize(filter));
		mainList.setModel(new BindingListModelList(paging.getPageData(
				currentPageNumber, filter), true));
	}

	public void onPaging$confirmRefillPaging(ForwardEvent event) {
		PagingEvent pagingEvent = (PagingEvent) event.getOrigin();
		currentPageNumber = pagingEvent.getActivePage();
		refreshModel();
	}

	private static visa.IssuingWS.IssuingPortProxy initNp(
			visa.IssuingWS.IssuingPortProxy issuingPortProxy, String alias) {
		if (np == null) {
			np = new NilProvider();
			np.init();
		}

		return issuingPortProxy = new visa.IssuingWS.IssuingPortProxy(
				ConnectionPool.getValue("TIETO_VISA_HOST", alias),
				ConnectionPool.getValue("TIETO_VISA_HOST_USERNAME", alias),
				ConnectionPool.getValue("TIETO_VISA_HOST_PASSWORD", alias));

	}

	public void onClick$btn_print_selected() {
		HashMap<String, Object> params = new HashMap<String, Object>();
		// System.out.println("REPORT BRANCH:" + ses_branch);
		// System.out.println("REPORT alias:" + alias);

		params.put("P_PAYID", 1);
		params.put("P_BRANCH", ses_branch);

		printwnd$rpframe.setContent(DPrint.getRepPdf(user, pass,
				"TI_hamza.pdf",
				application.getRealPath("reports/TI_hamza.jasper"), params,
				alias));
		printwnd.setVisible(true);

	}

	public void onClick$btn_cancel$printwnd() {
		printwnd.setVisible(false);
	}

	public void onClick$btn_print_1() {

		customer = CustomerService.getCustomerByIdClient(current.getBranch(),
				current.getClient_bank(), alias);

		HashMap<String, Object> params = new HashMap<String, Object>();
		// System.out.println("REPORT BRANCH:" + ses_branch);
		// System.out.println("REPORT alias:" + alias);
		// params.put("P_PAYID", 1);
		params.put("P_BRANCH", ses_branch);
		params.put("PPASSPORT", "паспорт passport aka");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		params.put("PV_DATE", longdf.format(timestamp));
		params.put("PCLIENT_NAME1", customer.getP_family());
		params.put("PCLIENT_NAME2", customer.getP_first_name());
		params.put("PCLIENT_NAME3", customer.getP_patronymic());
		params.put(
				"PPASSPORT",
				customer.getP_passport_serial()
						+ " "
						+ customer.getP_passport_number()
						+ ", "
						+ customer.getP_passport_place_registration()
						+ ", "
						+ sdf.format(customer.getP_passport_date_registration()));
		params.put("PPOST_ADDRESS", customer.getP_post_address());
		// if (sval == null)
		// sval=CustomerService.getCurr(alias);
		params.put("PT_CURRENCY", "Доллары США");
		params.put("PSUMMA6", String.format ("%.2f", current.getTransaction_amnt().doubleValue()/100));
		double d =  current.getTransaction_amnt().doubleValue()/100 * (double)ConfirmRefillService.getCource("840", 4);		
		params.put("PSUMMAE6", String.format ("%.2f", d) );
		
		params.put(
				"PPURPOSE",
				"Взнос средств на СКС пласт. карты Visa Classic (дебетовая), открытая на имя "
						+ customer.getP_family() + " "
						+ customer.getP_first_name() + " "
						+ customer.getP_patronymic() + " счет СКС: "
						+ current.getTranz_acct());

		
		Connection c=null; 
		try {
			c = ConnectionPool.getConnection(alias);
			params.put("POPENDOPER", ConfirmRefillService.GetCurAcc(
					current.getClient_bank(), "20206", "840",
					current.getBranch(), c));
		} catch (Exception e) {
		} finally {
			try {
				if (c != null)
					ConnectionPool.close(c);
			} catch (Exception e) {
			}
			;
		}
		params.put("PBRANCH_NAME", "АКБ \"Капиталбанк\"");

		printwnd$rpframe.setContent(DPrint.getRepPdf(user, pass,
				"TI_PRIHOD_ORDER_kap.pdf",
				application.getRealPath("reports/TI_PRIHOD_ORDER_kap.jasper"),
				params, alias));
		printwnd.setVisible(true);

	}

}
