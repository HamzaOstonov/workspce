package com.is.sd_books.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.Circulate;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.model.Deposit;
import com.is.utils.CheckNull;

public class CirculateService {
	private static final String sd_circulate = "SELECT" + " c.branch, c.id, c.book_id, c.oper_date" + " ,c.pc"
			+ " ,nvl(Decode(sign(c.circulate), 1,c.circulate, 0),0) circulate_cr"
			+ " ,nvl(Decode(sign(c.circulate), -1,abs(c.circulate), 0),0) circulate_db, c.saldo"
			+ " ,nvl(Decode(sign(c.prc_circulate), 1,c.prc_circulate, 0),0) prc_circulate_cr"
			+ " ,nvl(Decode(sign(c.prc_circulate), -1,abs(c.prc_circulate), 0),0) prc_circulate_db"
			+ " ,nvl(c.prc_saldo,0) prc_saldo"
			+ " ,c.general_id, c.emp, c.type_calc, c.bank_date, c.decision_id, c.group_id"
			+ " ,c.note, c.turn_code code, c.lead_filial, c.user_id" + " ,sd_clerk_all.name clerk_name"
			+ " FROM sd_circulate c"
			+ " ,(select sd_clerk_all.id,B_SER||' '||B_NUM||' '||state_sd_clerk.name name from sd_clerk_all,state_sd_clerk where sd_clerk_all.state=state_sd_clerk.id) sd_clerk_all "
			+ " WHERE c.branch    = info.GetBranch " + " and c.id_clerk_real=sd_clerk_all.id (+)"
			+ " and c.book_id=? order by c.id asc";

	private static final String sd_circulate2 = "SELECT" + " c.branch, c.id, c.book_id, c.oper_date" + " ,c.pc"
			+ " ,nvl(Decode(sign(c.circulate), 1,c.circulate, 0),0) circulate_cr"
			+ " ,nvl(Decode(sign(c.circulate), -1,abs(c.circulate), 0),0) circulate_db, c.saldo"
			+ " ,nvl(Decode(sign(c.prc_circulate), 1,c.prc_circulate, 0),0) prc_circulate_cr"
			+ " ,nvl(Decode(sign(c.prc_circulate), -1,abs(c.prc_circulate), 0),0) prc_circulate_db"
			+ " ,nvl(c.prc_saldo,0) prc_saldo"
			+ " ,c.general_id, c.emp, c.type_calc, c.bank_date, c.decision_id, c.group_id"
			+ " ,c.note, c.turn_code code, c.lead_filial, c.user_id" + " FROM sd_circulate c"
			+ " WHERE c.branch = info.GetBranch " + "and c.book_id=? order by oper_date asc";

	public static List<Circulate> getCirculate(Double bookId, Credentials cr) {

		List<Circulate> list = new ArrayList<Circulate>();
		Connection c = null;
		CallableStatement cs = null;
		String bankType = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(cr.getLogin(), cr.getPassword(), cr.getAlias());

			cs = c.prepareCall("{? = call info.GetBankType()}");

			cs.registerOutParameter(1, java.sql.Types.VARCHAR);

			cs.execute();

			bankType = cs.getString(1);

			if (bankType.equals("004")) {
				ps = c.prepareStatement(sd_circulate);
			} else {
				ps = c.prepareStatement(sd_circulate2);
			}

			ps.setDouble(1, bookId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Long generalId = rs.getLong("general_id");

				if (rs.wasNull()) {
					generalId = null;
				}

				list.add(new Circulate(rs.getString("branch"), rs.getDouble("id"), rs.getLong("book_id"),
						rs.getDate("oper_date"), rs.getDouble("pc"), rs.getBigDecimal("circulate_cr"),
						rs.getBigDecimal("circulate_db"), rs.getBigDecimal("saldo"),
						rs.getBigDecimal("prc_circulate_cr"), rs.getBigDecimal("prc_circulate_db"),
						rs.getBigDecimal("prc_saldo"), generalId, rs.getInt("emp"), rs.getString("type_calc"),
						rs.getDate("bank_date"), rs.getString("decision_id"), rs.getDouble("group_id"),
						rs.getString("note"), rs.getInt("code"), rs.getString("lead_filial"), rs.getDouble("user_id"),
						null// rs.getString("clerk_name")
				));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			;

		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static String  deleteOperation(Credentials cr, String branch_, double bookId_) {
		Connection c = null;
		String res ="ok";
		try {
			c = ConnectionPool.getConnection(cr.getLogin(), cr.getPassword(), cr.getAlias());
			c.setAutoCommit(false);
			CallableStatement cs = c.prepareCall("{call sd_ww_service.del_action (?,?)}");
			cs.setString(1, branch_);
			cs.setDouble(2, bookId_);
			cs.execute();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res = e.getMessage();
					try {
				c.rollback();
			} catch (SQLException e1) {
				ISLogger.getLogger().error(CheckNull.getPstr(e1));
			res = e1.getMessage();	
			}
		} finally {
			ConnectionPool.close(c);
		}
       return res;
	}

	public static void deleteLead(Credentials cr, String branch_, int bookId_, int circulateId_) {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(cr.getLogin(), cr.getPassword(), cr.getAlias());
			c.setAutoCommit(false);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.executeUpdate();
			cs = c.prepareCall("{call sd_ww_service.unlead(?,?,?)}");
			cs.setString(1, branch_);
			cs.setInt(2, bookId_);
			cs.setInt(3, circulateId_);
			cs.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (SQLException e1) {
				ISLogger.getLogger().error(CheckNull.getPstr(e));
			}
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static boolean isAttachedBook(String alias, double bookId, String branch) {
		Connection c = null;
		boolean result = false;
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("select count(sd_clerk_all.b_num) cnt from sd_clerk_all "
					+ " where sd_clerk_all.sd_books_id =? and branch =? and state=5 and rownum < 2");
			ps.setDouble(1, bookId);
			ps.setString(2, branch);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int st = Integer.parseInt(rs.getString("CNT"));
				if (st > 0) {
					result = true;
				} else {
					result = false;
				}
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}

		return result;
	}
	public static void retClerkBook(String alias, double bookId, String branch,int action_id,String filial){
	Connection c = null;
		try {
		c = ConnectionPool.getConnection(alias);
		CallableStatement cs = c.prepareCall("{call info.init()}");
		cs.executeUpdate();
		cs = c.prepareCall("{call sd_clerk.re_sd_clerk(?,?,?,?)}");
		cs.setString(1, branch);
		cs.setString(2, filial);
		cs.setInt(3, action_id);
		cs.setDouble(4,bookId);
		cs.executeUpdate();
		c.commit();
	} catch (SQLException e) {
		ISLogger.getLogger().error(CheckNull.getPstr(e));
	} finally {
		ConnectionPool.close(c);
	}

	}
	
	
}
