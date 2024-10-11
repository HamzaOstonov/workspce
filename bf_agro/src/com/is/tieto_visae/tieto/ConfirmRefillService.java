package com.is.tieto_visae.tieto;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
//import com.is.db_utils.ConnectionUtils;
//import com.is.db_utils.DBServiceUtils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

import accounting_transaction_4.Transaction_service;

public class ConfirmRefillService {
	private KapitalWebService service;
	private static Settings settings;

	private static Logger log = Logger.getLogger(ConfirmRefillService.class);
	List<Action> aactions;

	ConfirmRefillService() {
		service = new KapitalWebService();
	}

	public Map<String, String> getRefillStates() {
		Map<String, String> result = new HashMap<String, String>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id,  max(name) nam from state_bf_visa_refill group by id order by id");
			rs = ps.executeQuery();
			while (rs.next()) {
				result.put(rs.getString("id"), rs.getString("nam"));
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			try {
				closeConnection(rs, ps, c);
			} catch (SQLException e) {
				log.error(CheckNull.getPstr(e));
			}
		}

		return result;
	}

	public Map<String, String> getOperTypes() {
		Map<String, String> result = new HashMap<String, String>();

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"select t.id, t.descripption from bf_tr_operations t where t.parent_group_id=224 order by id");
			rs = ps.executeQuery();
			while (rs.next()) {
				result.put(rs.getString("id"), rs.getString("descripption"));
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			try {
				closeConnection(rs, ps, c);
			} catch (SQLException e) {
				log.error(CheckNull.getPstr(e));
			}
		}

		return result;
	}

	public List<Action> getAvailableActionsForUser(String username, String pwd, String alias) {
		List<Action> list = new ArrayList<Action>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(username, pwd, alias);
			ps = c.prepareStatement(
					"select vtc.action_id, aa.name, vtc.state_begin, vtc.state_end, vtc.deal_id from v_trans_bf_visa_refill vtc, "
							+ "action_bf_visa_refill aa " + "where vtc.deal_id = ? " + "and aa.deal_id = vtc.deal_id "
							+ "and aa.id = vtc.action_id");
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Action(rs.getString("action_id"), rs.getString("name"), rs.getString("state_begin"),
						rs.getString("state_end"), rs.getString("deal_id")));
			}
		} catch (SQLException e) {
			com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			;
			try {
				ps.close();
			} catch (Exception e) {
			}
			;
			try {
				ConnectionPool.close(c);
			} catch (Exception e) {
			}
			;
		}
		return list;
	}

	public List<Action> getAvailableActionsForItem(ConfirmRefill fill, String username, String pwd, String alias) {
		List<Action> list = new ArrayList<Action>();
		if (aactions == null)
			aactions = getAvailableActionsForUser(username, pwd, alias);
		for (int i = 0; i < aactions.size(); i++) {
			if (aactions.get(i).getState_begin().equals(fill.getState()))
				list.add(new Action(aactions.get(i).getAction_id(), aactions.get(i).getName(),
						aactions.get(i).getState_begin(), aactions.get(i).getState_end(),
						aactions.get(i).getDeal_id()));
		}
		return list;
	}

	public Action getActionById(String id, String username, String pwd, String alias) {
		Action a = new Action("", "", "", "", "");
		if (aactions == null)
			aactions = getAvailableActionsForUser(username, pwd, alias);
		for (int i = 0; i < aactions.size(); i++) {
			if (aactions.get(i).getAction_id().equals(id))
				a = new Action(aactions.get(i).getAction_id(), aactions.get(i).getName(),
						aactions.get(i).getState_begin(), aactions.get(i).getState_end(), aactions.get(i).getDeal_id());
		}
		return a;
	}

	private Settings getRefillSettings() {
		Connection c = null;
		;
		PreparedStatement ps;
		ResultSet rs;

		Settings result = new Settings();

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bf_visa_refill_sets");
			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String value = rs.getString("value");
				if (name.equals(Constants.FIELD_PAYMENT_MODE)) {
					result.setPaymentMode(value);
				} else if (name.equals(Constants.FIELD_ACC_CCY)) {
					result.setAccCcy(value);
				} else if (name.equals(Constants.FIELD_TRANZ_CCY)) {
					result.setTranzCcy(value);
				} else if (name.equals(Constants.FIELD_TRANZ_TYPE)) {
					result.setTranzType(value);
				}
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}

		return result;
	}

	public ResponseInfo tietoRefill(String cardAccount, Long sum) {
		ExecTransactionRequest request = new ExecTransactionRequest();
		ResponseInfoHolder response = new ResponseInfoHolder();
		Settings settings = getRefillSettings();
		request.setCard_acct(cardAccount);
		request.setPayment_mode(settings.getPaymentMode());
		request.setCard_acct_ccy(settings.getAccCcy());
		request.setTran_type(settings.getTranzType());
		request.setTran_ccy(settings.getTranzCcy());
		request.setTran_amnt(BigDecimal.valueOf(sum));

		service.executeTransaction(request, response);
		return response.value;
	}

	public static int getCource(String currency, int courceType) {
		Connection c = null;
		int result = 1;
		try {

			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("{call info.initday(sysdate)}");
			ps.executeUpdate();

			ps = c.prepareStatement("select Info.GetEqual(1,?,'000',?) state from dual");
			ps.setString(1, currency);
			ps.setInt(2, courceType);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result = rs.getInt("state");
			ps.close();
			rs.close();

		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {

			ConnectionPool.close(c);
		}
		return result;
	}


	public void bankRefill(ConfirmRefill refill, long operationId, String accName, String user, String pass,
			String alias) throws SQLException, CloneNotSupportedException {
		Connection c;
		Map<String, Object> params = new HashMap<String, Object>();
		Long payId;
		// DBUtils dbUtils = new DBUtils();

		c = ConnectionPool.getConnection(user, pass, alias);
		Transaction_service transactionService = new Transaction_service(c);

		params.put("branch", refill.getBranch());
		params.put("operation_id", operationId);
		params.put("parent_group_id", Constants.PARENT_GROUP_ID);
		params.put("parent_deal_id", Constants.PARENT_DEAL_ID);
		params.put("PARENT_ID", refill.getId());
		params.put(accName, refill.getCard_acct());

		// dbUtils.alterSession(refill.getBranch(), user, pass, alias);

		payId = transactionService.create_pay(operationId, refill.getBranch(), refill.getTransaction_amnt().longValue(),
				new Long(refill.id), Constants.PARENT_GROUP_ID, Long.valueOf(Constants.PARENT_DEAL_ID).intValue(),
				params);
		transactionService.execute_transaction_action(refill.getBranch(), payId, 19);
	}

	public long getOperationId(String account, Long isCashPayment) {
		long operationId = 0;

		if (account.substring(0, 5).equals("22618") && account.substring(17, 18).equals("9")) {
			operationId = 1003;
		} else if (account.substring(0, 5).equals("22618") && isCashPayment == 1) {
			operationId = 1001;
		} else if (account.substring(0, 5).equals("22618") && isCashPayment == 0) {
			operationId = 1002;
		} else if (account.substring(0, 5).equals("22619")) {
			operationId = 1004;
		} else if (account.substring(0, 5).equals("22620")) {
			operationId = 1005;
		}

		return operationId;
	}

	public String getAccNameByOperationId(long operationId) {
		String result = "";

		if (operationId == 1001 || operationId == 1002) {
			result = "ACC_22618";
		} else if (operationId == 1003) {
			result = "ACC_22618_9";
		} else if (operationId == 1004) {
			result = "ACC_22619";
		} else {
			result = "ACC_22620";
		}

		return result;
	}

	public void setRefillState(long refillId, long stateId) {
		Connection c;
		PreparedStatement ps;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update bf_visa_refill set state = ? where id = ?");
			ps.setLong(1, stateId);
			ps.setLong(2, refillId);
			ps.executeQuery();
			c.commit();
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		}
	}

	private static String getCond(List<FilterField> filterFields) {
		if (filterFields.size() > 0) {
			return " and ";
		} else {
			return " where ";
		}
	}

	private static List<FilterField> getFilterFields(ConfirmRefillFilter filter) {
		List<FilterField> filterFields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			filterFields.add(new FilterField(getCond(filterFields) + "id = ?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getCard_acct())) {
			filterFields.add(new FilterField(getCond(filterFields) + "card_acct = ?", filter.getCard_acct()));
		}
		if (!CheckNull.isEmpty(filter.getCard())) {
			filterFields.add(new FilterField(getCond(filterFields) + "card = ?", filter.getCard()));
		}
		if (filter.getTransaction_amnt() != null) {
			filterFields
					.add(new FilterField(getCond(filterFields) + "transaction_amnt = ?", filter.getTransaction_amnt()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			filterFields.add(new FilterField(getCond(filterFields) + "branch = ?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getClient_tieto())) {
			filterFields.add(new FilterField(getCond(filterFields) + "client_tieto = ?", filter.getClient_tieto()));
		}
		if (!CheckNull.isEmpty(filter.getClient_bank())) {
			filterFields.add(new FilterField(getCond(filterFields) + "client_bank = ?", filter.getClient_bank()));
		}
		if (filter.getState() != null) {
			filterFields.add(new FilterField(getCond(filterFields) + "state = ?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getPayment_mode())) {
			filterFields.add(new FilterField(getCond(filterFields) + "payment_mode = ?", filter.getPayment_mode()));
		}
		if (!CheckNull.isEmpty(filter.getBank_c())) {
			filterFields.add(new FilterField(getCond(filterFields) + "bank_c = ?", filter.getBank_c()));
		}
		if (!CheckNull.isEmpty(filter.getGroupc())) {
			filterFields.add(new FilterField(getCond(filterFields) + "groupc = ?", filter.getGroupc()));
		}
		if (!CheckNull.isEmpty(filter.getCard_acc_ccy())) {
			filterFields.add(new FilterField(getCond(filterFields) + "card_acc_ccy = ?", filter.getCard_acc_ccy()));
		}
		if (!CheckNull.isEmpty(filter.getTran_type())) {
			filterFields.add(new FilterField(getCond(filterFields) + "tran_type = ?", filter.getTran_type()));
		}
		if (!CheckNull.isEmpty(filter.getTran_ccy())) {
			filterFields.add(new FilterField(getCond(filterFields) + "tran_ccy = ?", filter.getTran_ccy()));
		}
		if (!CheckNull.isEmpty(filter.getClient_name())) {
			filterFields.add(new FilterField(getCond(filterFields) + "client_name = ?", filter.getClient_name()));
		}
		if (!CheckNull.isEmpty(filter.getClient_surname())) {
			filterFields.add(new FilterField(getCond(filterFields) + "client_surname = ?", filter.getClient_surname()));
		}

		return filterFields;
	}

	public static int getTotalSize(ConfirmRefillFilter filter) {
		int result = 0;
		StringBuilder sql = new StringBuilder();

		if (filter == null) {
			filter = new ConfirmRefillFilter();
		}
		List<FilterField> filterFields = getFilterFields(filter);

		sql.append("SELECT COUNT(*) FROM bf_visa_refill ");

		if (filterFields.size() > 0) {
			for (FilterField filterField : filterFields) {
				sql.append(filterField.getSqlwhere());
			}
		}

		try {
			result= getSizeByFilter(sql, filterFields);
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		}

		return result;
	}

	public Res doAction(ConfirmRefill item, int action, String username, String pwd, String alias, Connection cn) {
		Res res = null;

		// Connection c = null;
		CallableStatement callableStatement = null;
		CallableStatement params = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			if (settings == null) {
				settings = getRefillSettings();
			}
			// c = ConnectionPool.getConnection(username, pwd, alias);

			callableStatement = cn.prepareCall("{call kernel.doAction(?,?,?)}");
			params = cn.prepareCall("{call param.clearparam()}");
			params.execute();

			params = cn.prepareCall("{call param.setParam(?,?)}");

			params.setString(1, "BRANCH");
			params.setString(2, item.getBranch());
			params.execute();

			params.setString(1, "ID");
			params.setString(2, item.getId());
			params.execute();

			params.setString(1, "CARD_ACCT");
			params.setString(2, item.getCard_acct());
			params.execute();

			params.setString(1, "CARD");
			params.setString(2, item.getCard());
			params.execute();

			params.setString(1, "TRANSACTION_AMNT");
			params.setBigDecimal(2, item.getTransaction_amnt());
			params.execute();

			params.setString(1, "CLIENT_TIETO");
			params.setString(2, item.getClient_tieto());
			params.execute();

			params.setString(1, "CLIENT_BANK");
			params.setString(2, item.getClient_bank());
			params.execute();

			params.setString(1, "STATE");
			params.setString(2, item.getState());
			params.execute();

			params.setString(1, "BANK_C");
			params.setString(2, item.getBank_c());
			params.execute();

			params.setString(1, "GROUPC");
			params.setString(2, item.getGroupc());
			params.execute();

			params.setString(1, "CLIENT_NAME");
			params.setString(2, item.getClient_name());
			params.execute();

			params.setString(1, "CLIENT_SURNAME");
			params.setString(2, item.getClient_surname());
			params.execute();

			params.setString(1, "TYPE_OPER");
			params.setLong(2, item.getType_oper());
			params.execute();

			params.setString(1, "TRANZ_ACCT");
			params.setString(2, item.getTranz_acct());
			params.execute();

			params.setString(1, "ACCOUNT_NO");
			params.setString(2, item.getAccount_no());
			params.execute();

			params.setString(1, "ERR_CODE");
			params.setString(2, item.getErr_code());
			params.execute();

			params.setString(1, "ERR_MSG");
			params.setString(2, item.getErr_msg());
			params.execute();

			params.setString(1, "PAYMENT_MODE");
			if (item.getPayment_mode() != null)
				params.setString(2, item.getPayment_mode());
			else
				params.setString(2, settings.getPaymentMode());
			params.execute();

			params.setString(1, "CARD_ACC_CCY");
			if (item.getCard_acc_ccy() != null)
				params.setString(2, item.getCard_acc_ccy());
			else
				params.setString(2, settings.getAccCcy());
			params.execute();

			params.setString(1, "TRAN_TYPE");
			if (item.getTran_type() != null)
				params.setString(2, item.getTran_type());
			else
				params.setString(2, settings.getTranzType());
			params.execute();

			params.setString(1, "TRAN_CCY");
			if (item.getTran_ccy() != null)
				params.setString(2, item.getTran_ccy());
			else
				params.setString(2, settings.getTranzCcy());
			params.execute();

			callableStatement.setInt(1, 224);
			callableStatement.setInt(2, 1);
			callableStatement.setInt(3, action);

			callableStatement.execute();

			ps = cn.prepareStatement("select param.getparam(?) id, param.getparam(?) state from dual");
			ps.setString(1, "ID");
			ps.setString(2, "STATE");
			rs = ps.executeQuery();
			if (rs.next()) {
				res = new Res(Integer.parseInt(rs.getString(1)), rs.getString(2));
			}
		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
			res = new Res(-1, CheckNull.getPstr(e));
			// c.rollback();
			// throw e;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			;
			try {
				ps.close();
			} catch (Exception e) {
			}
			;
			try {
				params.close();
			} catch (Exception e) {
			}
			;
			try {
				callableStatement.close();
			} catch (Exception e) {
			}
			;
		}
		return res;
	}

	public static List<ConfirmRefill> getOnePageData(int startItemNumber, int lastItemNumber,
			ConfirmRefillFilter filter) {
		List<ConfirmRefill> result = new ArrayList<ConfirmRefill>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		if (filter == null) {
			filter = new ConfirmRefillFilter();
		}
		List<FilterField> filterFields = getFilterFields(filter);

		sql.append("SELECT * FROM (SELECT p.*, ROWNUM rn FROM bf_visa_refill p ");
		for (FilterField filterField : filterFields) {
			sql.append(filterField.getSqlwhere());
		}
		sql.append(" ORDER BY p.id) WHERE rn >= ? and rn < ?");

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());

			int i;
			for (i = 0; i < filterFields.size(); ++i) {
				ps.setObject(i + 1, filterFields.get(i).getColobject());
			}
			ps.setInt(++i, startItemNumber);
			ps.setInt(++i, lastItemNumber);

			rs = ps.executeQuery();
			while (rs.next()) {
				ConfirmRefill confirmRefill = new ConfirmRefill();
				confirmRefill.setId(rs.getString("ID"));
				confirmRefill.setCard_acct(rs.getString("CARD_ACCT"));
				confirmRefill.setCard(rs.getString("CARD"));
				confirmRefill.setTransaction_amnt(BigDecimal.valueOf(rs.getLong("TRANSACTION_AMNT")));
				confirmRefill.setBranch(rs.getString("BRANCH"));
				confirmRefill.setClient_tieto(rs.getString("CLIENT_TIETO"));
				confirmRefill.setClient_bank(rs.getString("CLIENT_BANK"));
				confirmRefill.setState(rs.getString("STATE"));
				confirmRefill.setPayment_mode(rs.getString("PAYMENT_MODE"));
				confirmRefill.setBank_c(rs.getString("BANK_C"));
				confirmRefill.setGroupc(rs.getString("GROUPC"));
				confirmRefill.setCard_acc_ccy(rs.getString("CARD_ACC_CCY"));
				confirmRefill.setTran_type(rs.getString("TRAN_TYPE"));
				confirmRefill.setTran_ccy(rs.getString("TRAN_CCY"));
				confirmRefill.setClient_name(rs.getString("CLIENT_NAME"));
				confirmRefill.setClient_surname(rs.getString("CLIENT_SURNAME"));
				// confirmRefill.setIs_cash_payment(rs.getLong("IS_CASH_PAYMENT"));
				confirmRefill.setType_oper(rs.getLong("type_oper"));
				confirmRefill.setTranz_acct(rs.getString("tranz_acct"));
				confirmRefill.setAccount_no(rs.getString("account_no"));
				confirmRefill.setErr_code(rs.getString("err_code"));
				confirmRefill.setErr_msg(rs.getString("err_msg"));

				result.add(confirmRefill);
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			try {
				closeConnection(rs, ps, c);
			} catch (SQLException e) {
				log.error(CheckNull.getPstr(e));
			}
		}
		return result;
	}

	public static String GetCurAcc(String client_id, String acc_bal, String currency, String branch, Connection c) {
		String res = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement(
					"select t.id from account t where t.branch = ? and t.client=? and t.acc_bal = ? and t.currency=? and t.state=2");
			ps.setString(1, branch);
			ps.setString(2, client_id);
			ps.setString(3, acc_bal);
			ps.setString(4, currency);
			rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("id");
			}
		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
		}
		return res;
	}

	public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection c) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (c != null) {
			c.close();
		}
	}

	public static void closeConnection(ResultSet rs, ArrayList<Statement> statements, Connection c)
			throws SQLException {
		if (rs != null) {
			rs.close();
		}
		for (Statement statement : statements) {
			if (statement != null) {
				statement.close();
			}
		}
		if (c != null) {
			c.close();
		}
	}

	public static int getSizeByFilter(StringBuilder sql, List<FilterField> filterFields) throws SQLException {
		int result = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		c = ConnectionPool.getConnection();
		ps = c.prepareStatement(sql.toString());

		for (int i = 0; i < filterFields.size(); i++) {
			ps.setObject(i + 1, filterFields.get(i).getColobject());
		}
		//ps.setString(1, (String) filterFields.get(0).getColobject() );
		
		rs = ps.executeQuery();
		if (rs.next()) {
			result = rs.getInt(1);
		}

		closeConnection(rs, ps, c);

		return result;
	}
}
