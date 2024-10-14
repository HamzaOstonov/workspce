package com.is.tieto_visa.tieto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.is.ConnectionPool;
import com.is.utils.NilProvider;
import com.is.utils.RefCBox;
import com.is.utils.Res;

public class CardViewCtrl extends GenericForwardComposer {
	private Listbox cardsList;
	private visa.IssuingWS.IssuingPortProxy issuingPortProxy;
	private static NilProvider np = null;
	private static DecimalFormat nf = null;

	public CardInfo getCurrent() {
		return current;
	}

	public void setCurrent(CardInfo current) {
		this.current = current;
	}

	public ListAccountsItem getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(ListAccountsItem currentAccount) {
		this.currentAccount = currentAccount;
	}

	public ListCustomersItem getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(ListCustomersItem currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public TclientViewCtrl getMain() {
		return main;
	}

	public void setMain(TclientViewCtrl main) {
		this.main = main;
	}

	private CardInfo current;
	private ListAccountsItem currentAccount;
	private ListCustomersItem currentCustomer;
	private TclientViewCtrl main;
	private CardService service;
	private ConfirmRefillService serviceFill;
	private Textbox refillWindow$refillSum;
	// private Combobox refillWindow$payMethod;
	private RefCBox refillWindow$payMethod;
	private Window refillWindow;
	protected String alias, ses_branch, un, pw;

	private ListitemRenderer cardsRenderer = new ListitemRenderer() {
		@Override
		public void render(Listitem row, Object data) {
			CardInfo item = (CardInfo) data;
			row.setValue(item);

			row.appendChild(new Listcell((item.getMainInfo().getCard() != null) ? item.getMainInfo().getCard() : ""));
			row.appendChild(new Listcell(
					(item.getMainInfo().getExpiry() != null) ? item.getMainInfo().getExpiry().toString() : ""));
			row.appendChild(
					new Listcell((item.getMainInfo().getStatus() != null) ? item.getMainInfo().getStatus() : ""));
			row.appendChild(new Listcell((item.getBalance().getAvail_amount() != null)
					? nf.format(item.getBalance().getAvail_amount()) : ""));
			row.appendChild(new Listcell(
					(item.getBalance().getEnd_bal() != null) ? nf.format(item.getBalance().getEnd_bal()) : ""));
			row.appendChild(new Listcell((item.getBalance().getLocked_amount() != null)
					? nf.format(item.getBalance().getLocked_amount()) : ""));
		}
	};

	private void refreshCards(ArrayList<CardInfo> cards) {
		if (cards == null) {
			cards = new ArrayList<CardInfo>();
		}
		cardsList.setItemRenderer(cardsRenderer);
		cardsList.setModel(new BindingListModelList(cards, true));
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		AnnotateDataBinder binder = new AnnotateDataBinder(comp);
		binder.bindBean("current", this.current);
		binder.loadAll();
		nf = new DecimalFormat("#,###.00");
		ses_branch = (String) session.getAttribute("branch");
		alias = (String) session.getAttribute("alias");
		un = (String) session.getAttribute("un");
		pw = (String) session.getAttribute("pwd");
		// int userId = (Integer) session.getAttribute("uid");

		refillWindow$payMethod.setModel(new ListModelList(CardService.getRefillTypeOpers(alias)));

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

	}

	public void search() {
		if (service == null) {
			service = new CardService();
		}
		ArrayList<CardInfo> cards;
		ResponseInfoHolder responseInfoHolder = new ResponseInfoHolder();

		cards = service.listCards(currentAccount, responseInfoHolder);

		refreshCards(cards);
	}

	public void onDoubleClick$cardsList() {
		refillWindow.setVisible(true);
	}

	public void onClick$btnRefill$refillWindow() throws Exception {
		// int isCashPayment = 0;
		if (ZkUtils.validateForm(refillWindow)) {
			// if (refillWindow$payMethod.getSelectedIndex() == 0) {
			// isCashPayment = 1;
			// } else {
			// isCashPayment = 0;
			// }
			// service.addTransaction(currentCustomer, currentAccount, current,
			// Long.valueOf(refillWindow$refillSum.getValue()),
			// main.getBranch(), isCashPayment);
			// service.addTransaction(currentCustomer, currentAccount, current,
			// Long.valueOf(refillWindow$refillSum.getValue()),
			// main.getBranch(), new Long(refillWindow$payMethod.getValue()));
			Res res = null;
			// проверить и сконвертировать сумму
			String summa_str = refillWindow$refillSum.getValue();
			BigDecimal money= new BigDecimal(0);
			try {
				money = new BigDecimal(summa_str);
			} catch (Exception e1) {
				try {
					money = new BigDecimal(summa_str.replaceAll(",", "."));
				} catch (Exception e2) {

					try {
						money = new BigDecimal(summa_str.replaceAll(".", ","));
					} catch (Exception e3) {
						res = new Res();
						res.setCode(0);
						res.setName("ошибка преобразования числа");
						alert(res.getName());
						return;
					}
				}
			}
			if (money==null || (new BigDecimal("0")).compareTo(money)==0 || (new BigDecimal("0")).compareTo(money)==1  ) 
			{
				alert("неверная сумма");
				return;
			}
			// проверка клиента
			res = TclientService.check_user(alias, ses_branch, currentCustomer.getClient());
			if (res.getCode() == 0) {
				alert(res.getName());
				return;
			}
			// if (!res.getName().equals(currentCustomer.getClient_b()))
			// {
			// alert("Отличается код клиента " );
			// }

			ConfirmRefill fill = new ConfirmRefill();

			fill.setCard_acct(currentAccount.getCard_acct());
			fill.setCard(current.getMainInfo().getCard());

			// fill.setTransaction_amnt(new BigDecimal(new
			// Double(refillWindow$refillSum.getValue()) * 100));
			money = money.multiply(BigDecimal.valueOf(100));
			fill.setTransaction_amnt(money);
			fill.setBranch(ses_branch);
			fill.setClient_tieto(currentCustomer.getClient());
			if (currentCustomer.getClient_b() != null)
				fill.setClient_bank(currentCustomer.getClient_b());
			else
				fill.setClient_bank(res.getName());
			fill.setState("");
			fill.setBank_c(current.getBalance().getBank_c());
			fill.setGroupc(current.getBalance().getGroupc());
			fill.setClient_name(currentCustomer.getF_names());
			fill.setClient_surname(currentCustomer.getSurname());
			fill.setType_oper(new Long(refillWindow$payMethod.getValue()));
			fill.setTranz_acct(currentAccount.getTranz_acct());
			fill.setAccount_no(currentAccount.getAccount_no().toString());
			
			if (refillWindow$payMethod.getText().toUpperCase().matches("СПИСАНИЕ"))
			{
				fill.setTran_type("129");
			}
			
			if (serviceFill == null) {
				serviceFill = new ConfirmRefillService();
			}
			Connection c = null;
			c = ConnectionPool.getConnection(un, pw, alias);
			try {
				res = serviceFill.doAction(fill, 1, un, pw, alias, c);

				if (res.getCode() != -1)
					c.commit();
				else
					c.rollback();
			} finally {
				try {
					ConnectionPool.close(c);
				} catch (Exception e) {
				}
				;
			}
			if (res.getCode() == -1)
			  throw new Exception(res.getName() ); 
			// refillWindow$refillSum.setValue("");
			//

			/*
			 * if (true) { long id =123456; ResponseInfo response; Res res = new
			 * Res(); Res rs = new Res(); Connection c = null; rs.setName("Ok");
			 * Transaction_service transactionService = null; Parameters ps =
			 * new Parameters();
			 * 
			 * //ConfirmRefill current = new ConfirmRefill();
			 * 
			 * try { long operationId = current.getType_oper();
			 * 
			 * c = ConnectionPool.getConnection(un, pw, alias);
			 * transactionService = new Transaction_service(c); long trId;
			 * List<AccInfo> acc =
			 * TclientService.getAccInfo(current.client_tieto, alias,
			 * issuingPortProxy);
			 * 
			 * Map<String, Object> parameters = new HashMap<String, Object>();
			 * parameters.put("branch", current.getBranch());
			 * parameters.put("operation_id", operationId);
			 * parameters.put("parent_group_id", 224l);
			 * parameters.put("parent_deal_id", 1); parameters.put("SUMMA",
			 * current.getTransaction_amnt()); parameters.put("PARENT_ID",
			 * Long.parseLong("1")); parameters.put("CARD_ACC",
			 * current.getTranz_acct());
			 * 
			 * trId = transactionService.create_pay(operationId,
			 * current.getBranch(), current.getTransaction_amnt().longValue() ,
			 * 1l, 224l, 1, parameters);
			 * transactionService.execute_transaction_action(current.getBranch()
			 * , trId, 19); // 19 // // // 23
			 * 
			 * TrPay trp = new TrPay();
			 * trp.setAmount(current.getTransaction_amnt().intValue());
			 * trp.setAccount_no(current.getAccount_no());
			 * trp.setCard_acc(current.getCard_acct());
			 * 
			 * res = TclientService.sendPayment(trp, alias,
			 * current.getCard_acc_ccy(), issuingPortProxy); if (res.getCode()
			 * != 1) { alert("Операция не выполнена, INTERNAL_NO = > " +
			 * res.getName()); rs.setCode(-1); rs.setName(res.getName());
			 * return; } rs.setCode(0); rs.setName("Ok"); c.commit(); } catch
			 * (SQLException e) { try { c.rollback(); } catch (Exception g) {
			 * rs.setName(g.getMessage()); } rs.setCode(-1);
			 * rs.setName(e.getMessage()+" - "+rs.getName()); alert("Ошибка: " +
			 * e.getMessage()); } catch (CloneNotSupportedException e) { alert(
			 * "Ошибка: " + e.getMessage()); } }
			 */

			refillWindow.setVisible(false);
			alert("Успешно.");
		}

	}

	public void onClick$btnRefillCancel$refillWindow() {
		refillWindow.setVisible(false);
		refillWindow$refillSum.setValue("");
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

}
