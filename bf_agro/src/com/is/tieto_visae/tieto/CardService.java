package com.is.tieto_visae.tieto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.RefData;

public class CardService {
	private KapitalWebService service;
	private static Settings settings;
	private static Logger log = Logger.getLogger(CardService.class);

	CardService() {
		service = new KapitalWebService();
	}

	public ArrayList<CardInfo> listCards(ListAccountsItem filter, ResponseInfoHolder response) {
		ArrayList<CardInfo> result;

		ListCardsFilter rightFilter = KapitalModelTransformer.getCardFilterByAcc(filter);
		result = listCards(rightFilter, response);

		return result;
	}

	private ArrayList<CardInfo> listCards(ListCardsFilter filter, ResponseInfoHolder response) {
		ArrayList<CardInfo> result;

		result = service.tietoListCards(filter, response);

		return result;
	}

	public static List<RefData> getRefillTypeOpers(String alias) {
		List<RefData> list = new ArrayList<RefData>();
		String sql = "select t.id data, t.descripption label from bf_tr_operations t where t.parent_group_id=224 and ( lower(t.descripption) like '%пополнение%' or lower(t.descripption) like '%списание%' ) order by id";
		list = com.is.utils.RefDataService.getRefData(sql, alias);
		return list;
	}

	private Settings getRefillSettings() throws SQLException {
		Connection c;
		PreparedStatement ps;
		ResultSet rs;
		String name;
		String value;
		Settings result = new Settings();
		c = ConnectionPool.getConnection();
		ps = c.prepareStatement("select * from bf_visa_refill_sets");
		rs = ps.executeQuery();
		while (rs.next()) {
			name = rs.getString("name");
			value = rs.getString("value");
			if (name.equals("PAYMENT_MODE")) {
				result.setPaymentMode(value);
			} else if (name.equals("ACC_CCY")) {
				result.setAccCcy(value);
			} else if (name.equals("TRANZ_CCY")) {
				result.setTranzType(value);
			} else if (name.equals("TRANZ_TYPE")) {
				result.setTranzType(value);
			}
		}
		return result;
	}

	public void addTransaction(ListCustomersItem customer, ListAccountsItem account, CardInfo card, Long sum,
			String branch, Long typeOper) {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			if (settings == null) {
				settings = getRefillSettings();
			}
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"insert into bf_visa_refill (id, card_acct, card, transaction_amnt, branch, client_tieto, client_bank, state_id, bank_c, groupc, payment_mode, card_acc_ccy, tran_type, tran_ccy, client_name, client_surname, type_oper, tranz_acct, account_no) values (seq_bf_visa_refill.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, account.getCard_acct());
			ps.setString(2, card.getMainInfo().getCard());
			ps.setLong(3, sum);
			ps.setString(4, branch);
			ps.setString(5, customer.getClient());
			ps.setString(6, customer.getClient_b());
			ps.setLong(7, Constants.REFILL_STATE_ADDED);
			ps.setString(8, card.getBalance().getBank_c());
			ps.setString(9, card.getBalance().getGroupc());
			ps.setString(10, settings.getPaymentMode());
			ps.setString(11, settings.getAccCcy());
			ps.setString(12, settings.getTranzType());
			ps.setString(13, settings.getTranzCcy());
			ps.setString(14, customer.getF_names());
			ps.setString(15, customer.getSurname());
			ps.setLong(16, typeOper);
			ps.setString(17, account.getTranz_acct());
			ps.setString(18, account.getAccount_no().toString());
			ps.executeQuery();
			c.commit();
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				log.error(CheckNull.getPstr(e));
			}

		}
	}

}
