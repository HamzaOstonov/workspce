package com.is.humoOperation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.openwayj.Utils;
import com.is.openwayj.model.CardInfo;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class HumoOperationService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	// private static String msql
	// ="SELECT c.*,rowid rid FROM humo_oper_type c ";
	private static String msql = "SELECT c.*,rowid_ rid FROM (select t.operation_id, h.rowid rowid_, o.descripption, (select t.acc_dt || ' - ' || substr(ac.account, 1, 8) from bf_tr_acc ac where ac.acc_template_id = t.acc_dt and ac.branch = '00394') as \"DT\", (select t.acc_ct || ' - ' || substr(ac.account, 1, 8)  from bf_tr_acc ac  where ac.acc_template_id = t.acc_ct  and ac.branch = '00394') as \"KT\",  h.*  from bf_tr_template t, humo_oper_type h, bf_tr_operations o where t.operation_id = o.id and t.operation_id = h.id) c ";

	public static List<RefData> getOperType(String branch) {
		return RefDataService
				.getRefData(
						"select o.id data, o.id||o.descripption label from bf_tr_operations o where o.parent_group_id=198",
						branch);
	}

	public static List<RefData> getTerminalKind(String branch) {
		return RefDataService.getRefData(
				"select type data , label from ss_empc_terminal_kind", branch);
	}

	public static List<RefData> getBranchType(String branch) {
		return RefDataService.getRefData(
				"select id data, id||name label from ss_humo_branch_type ",
				branch);
	}

	public static List<RefData> getMccCode(String branch) {
		return RefDataService
				.getRefData(
						"select mcc data, mcc||' '||label label from SS_EMPC_MERCH_MCC",
						branch);
	}

	public static List<RefData> getTermTorg(String branch) {
		return RefDataService.getRefData(
				"select id data, id||' '||name label from SS_HUMO_TERM_TORG",
				branch);
	}

	public static HashMap<String, String> getHOperType(String branch) {
		return RefDataService
				.getHRefData(
						"select o.id data, o.id||o.descripption label from bf_tr_operations o where o.parent_group_id=198",
						branch);
	}

	public static HashMap<String, String> getHTerminalKind(String branch) {
		return RefDataService.getHRefData(
				"select type data, label from ss_empc_terminal_kind", branch);
	}

	public static HashMap<String, String> getHTermTorg(String branch) {
		return RefDataService.getHRefData(
				"select id data, id||' '||name label from SS_HUMO_TERM_TORG",
				branch);
	}

	public static List<RefData> getOperation_id(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct operation_id data, operation_id label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getDescripption(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct descripption data, descripption label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getDt(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct dt data, dt label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getKt(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct kt data, kt label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTerm_type(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct term_type data, term_type label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getOper(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct oper data, oper label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getCard_branch(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct card_branch data, card_branch label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTerm_branch(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct term_branch data, term_branch label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTr_type_expt(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct tr_type_expt data, tr_type_expt label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTr_type_b(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct tr_type_b data, tr_type_b label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getMcc(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct mcc data, mcc label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getKomis(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct komis data, komis label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTerminal(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct terminal data, terminal label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getAcc_term(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct acc_term data, acc_term label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getAccnt_ccy(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct accnt_ccy data, accnt_ccy label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTran_ccy(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct tran_ccy data, tran_ccy label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getDeb_cred(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct deb_cred data, deb_cred label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getCountry(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct country data, country label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getIn_file(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct in_file data, in_file label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getTr_type2_expt(String branch,
			HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct tr_type2_expt data, tr_type2_expt label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getMsc(String branch, HumoOperationFilter fl) {
		return getRefDataFilter(
				"SELECT distinct msc data, msc label FROM v_humo_oper_type c",
				"order by 1", branch, fl);
	}

	public static List<RefData> getRefDataFilter(String sql_01, String sql_02,
			String branch, HumoOperationFilter filter) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		// Statement s = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int params;
		List<FilterField> flFields = /*getFilterFields*/getFilterFields_without_rownum_limit(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(sql_01);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(sql_02);
		try {
			c = ConnectionPool.getConnection(branch);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;

			rs = ps.executeQuery();
			while (rs.next())
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}

		// --------
		/*
		 * int a=0; sql = new StringBuffer(); sql.append(sql_01);
		 * sql.append(sql_02); try { c = ConnectionPool.getConnection(branch);
		 * ps = c.prepareStatement(sql.toString()); rs = ps.executeQuery();
		 * while (rs.next()) { //if (!list.contains((new
		 * RefData(rs.getString("data"), rs // .getString("label"))))) { //if
		 * (a==0) { // list.add(new RefData("", "------------------")); //}
		 * //a=a+1; list.add(new RefData(rs.getString("data"), rs
		 * .getString("label"))); } //}
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } finally {
		 * ConnectionPool.close(rs); ConnectionPool.close(ps);
		 * ConnectionPool.close(c); }
		 */

		// "SELECT distinct operation_id data, operation_id label FROM v_humo_oper_type c",
		// "order by 1",

		return list;
	}

	public static void close(PreparedStatement pStatement) {
		try {
			if (pStatement != null) {
				pStatement.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}

	public static void close(CallableStatement cs) {
		try {
			if (cs != null) {
				cs.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}

	public static void close(ResultSet rSet) {
		try {
			if (rSet != null) {
				rSet.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}

	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
		}
	}

	public List<HumoOperation> getHumoOperation() {

		List<HumoOperation> list = new ArrayList<HumoOperation>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM humo_oper_type");
			while (rs.next()) {
				list.add(new HumoOperation(rs.getString("id"), rs
						.getString("term_type"), rs.getString("oper"), rs
						.getString("card_branch"), rs.getString("term_branch"),
						rs.getString("tr_type_expt"),
						rs.getString("tr_type_b"), rs.getString("mcc"), rs
								.getString("komis"), rs.getString("terminal"),
						rs.getString("acc_term"), rs.getString("accnt_ccy"), rs
								.getString("tran_ccy"), rs
								.getString("deb_cred"),
						rs.getString("country"), rs.getString("in_file"), rs
								.getString("tr_type2_expt"), rs
								.getString("msc"), ""));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(HumoOperationFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getTerm_type())) {
			flfields.add(new FilterField(getCond(flfields) + "term_type=?",
					filter.getTerm_type()));
		}
		if (!CheckNull.isEmpty(filter.getOper())) {
			flfields.add(new FilterField(getCond(flfields) + "oper=?", filter
					.getOper()));
		}
		if (!CheckNull.isEmpty(filter.getCard_branch())) {
			flfields.add(new FilterField(getCond(flfields) + "card_branch=?",
					filter.getCard_branch()));
		}
		if (!CheckNull.isEmpty(filter.getTerm_branch())) {
			flfields.add(new FilterField(getCond(flfields) + "term_branch=?",
					filter.getTerm_branch()));
		}
		if (!CheckNull.isEmpty(filter.getTr_type_expt())) {
			flfields.add(new FilterField(getCond(flfields) + "tr_type_expt=?",
					filter.getTr_type_expt()));
		}
		if (!CheckNull.isEmpty(filter.getTr_type_b())) {
			flfields.add(new FilterField(getCond(flfields) + "tr_type_b=?",
					filter.getTr_type_b()));
		}
		if (!CheckNull.isEmpty(filter.getMcc())) {
			flfields.add(new FilterField(getCond(flfields) + "mcc=?", filter
					.getMcc()));
		}
		if (!CheckNull.isEmpty(filter.getKomis())) {
			flfields.add(new FilterField(getCond(flfields) + "komis=?", filter
					.getKomis()));
		}
		if (!CheckNull.isEmpty(filter.getTerminal())) {
			flfields.add(new FilterField(getCond(flfields) + "terminal=?",
					filter.getTerminal()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_term())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_term=?",
					filter.getAcc_term()));
		}
		if (!CheckNull.isEmpty(filter.getAccnt_ccy())) {
			flfields.add(new FilterField(getCond(flfields) + "accnt_ccy=?",
					filter.getAccnt_ccy()));
		}
		if (!CheckNull.isEmpty(filter.getTran_ccy())) {
			flfields.add(new FilterField(getCond(flfields) + "tran_ccy=?",
					filter.getTran_ccy()));
		}
		if (!CheckNull.isEmpty(filter.getDeb_cred())) {
			flfields.add(new FilterField(getCond(flfields) + "deb_cred=?",
					filter.getDeb_cred()));
		}
		if (!CheckNull.isEmpty(filter.getCountry())) {
			flfields.add(new FilterField(getCond(flfields) + "country=?",
					filter.getCountry()));
		}

		if (!CheckNull.isEmpty(filter.getFiletype())) {
			flfields.add(new FilterField(getCond(flfields) + "filetype=?",
					filter.getFiletype()));
		}

		if (!CheckNull.isEmpty(filter.getRid())) {
			flfields.add(new FilterField(getCond(flfields) + "rid=?", filter
					.getRid()));
		}

		if (!CheckNull.isEmpty(filter.getIn_file())) {
			flfields.add(new FilterField(getCond(flfields) + "in_file=?",
					filter.getIn_file()));
		}

		if (!CheckNull.isEmpty(filter.getMsc())) {
			flfields.add(new FilterField(getCond(flfields) + "msc=?", filter
					.getMsc()));
		}

		if (!CheckNull.isEmpty(filter.getTr_type2_expt())) {
			flfields.add(new FilterField(getCond(flfields) + "tr_type2_expt=?",
					filter.getTr_type2_expt()));
		}

		if (!CheckNull.isEmpty(filter.getOperation_id())) {
			flfields.add(new FilterField(getCond(flfields) + "operation_id=?",
					filter.getOperation_id()));
		}

		if (!CheckNull.isEmpty(filter.getDescripption())) {
			flfields.add(new FilterField(getCond(flfields) + "descripption=?",
					filter.getDescripption()));
		}

		if (!CheckNull.isEmpty(filter.getDt())) {
			flfields.add(new FilterField(getCond(flfields) + "dt=?", filter
					.getDt()));
		}

		if (!CheckNull.isEmpty(filter.getKt())) {
			flfields.add(new FilterField(getCond(flfields) + "kt=?", filter
					.getKt()));
		}

		if (!CheckNull.isEmpty(filter.getFiletype())) {
			if (filter.getFiletype().equals("EXPT")) {
				flfields.add(new FilterField(getCond(flfields)
						+ "tr_type_b is null and id<>?", filter.getFiletype()));
			} else {
				flfields.add(new FilterField(getCond(flfields)
						+ "tr_type_expt is  null and id<>?", filter
						.getFiletype()));
			}

		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	private static List<FilterField> getFilterFields_without_rownum_limit(HumoOperationFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getTerm_type())) {
			flfields.add(new FilterField(getCond(flfields) + "term_type=?",
					filter.getTerm_type()));
		}
		if (!CheckNull.isEmpty(filter.getOper())) {
			flfields.add(new FilterField(getCond(flfields) + "oper=?", filter
					.getOper()));
		}
		if (!CheckNull.isEmpty(filter.getCard_branch())) {
			flfields.add(new FilterField(getCond(flfields) + "card_branch=?",
					filter.getCard_branch()));
		}
		if (!CheckNull.isEmpty(filter.getTerm_branch())) {
			flfields.add(new FilterField(getCond(flfields) + "term_branch=?",
					filter.getTerm_branch()));
		}
		if (!CheckNull.isEmpty(filter.getTr_type_expt())) {
			flfields.add(new FilterField(getCond(flfields) + "tr_type_expt=?",
					filter.getTr_type_expt()));
		}
		if (!CheckNull.isEmpty(filter.getTr_type_b())) {
			flfields.add(new FilterField(getCond(flfields) + "tr_type_b=?",
					filter.getTr_type_b()));
		}
		if (!CheckNull.isEmpty(filter.getMcc())) {
			flfields.add(new FilterField(getCond(flfields) + "mcc=?", filter
					.getMcc()));
		}
		if (!CheckNull.isEmpty(filter.getKomis())) {
			flfields.add(new FilterField(getCond(flfields) + "komis=?", filter
					.getKomis()));
		}
		if (!CheckNull.isEmpty(filter.getTerminal())) {
			flfields.add(new FilterField(getCond(flfields) + "terminal=?",
					filter.getTerminal()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_term())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_term=?",
					filter.getAcc_term()));
		}
		if (!CheckNull.isEmpty(filter.getAccnt_ccy())) {
			flfields.add(new FilterField(getCond(flfields) + "accnt_ccy=?",
					filter.getAccnt_ccy()));
		}
		if (!CheckNull.isEmpty(filter.getTran_ccy())) {
			flfields.add(new FilterField(getCond(flfields) + "tran_ccy=?",
					filter.getTran_ccy()));
		}
		if (!CheckNull.isEmpty(filter.getDeb_cred())) {
			flfields.add(new FilterField(getCond(flfields) + "deb_cred=?",
					filter.getDeb_cred()));
		}
		if (!CheckNull.isEmpty(filter.getCountry())) {
			flfields.add(new FilterField(getCond(flfields) + "country=?",
					filter.getCountry()));
		}

		if (!CheckNull.isEmpty(filter.getFiletype())) {
			flfields.add(new FilterField(getCond(flfields) + "filetype=?",
					filter.getFiletype()));
		}

		if (!CheckNull.isEmpty(filter.getRid())) {
			flfields.add(new FilterField(getCond(flfields) + "rid=?", filter
					.getRid()));
		}

		if (!CheckNull.isEmpty(filter.getIn_file())) {
			flfields.add(new FilterField(getCond(flfields) + "in_file=?",
					filter.getIn_file()));
		}

		if (!CheckNull.isEmpty(filter.getMsc())) {
			flfields.add(new FilterField(getCond(flfields) + "msc=?", filter
					.getMsc()));
		}

		if (!CheckNull.isEmpty(filter.getTr_type2_expt())) {
			flfields.add(new FilterField(getCond(flfields) + "tr_type2_expt=?",
					filter.getTr_type2_expt()));
		}

		if (!CheckNull.isEmpty(filter.getOperation_id())) {
			flfields.add(new FilterField(getCond(flfields) + "operation_id=?",
					filter.getOperation_id()));
		}

		if (!CheckNull.isEmpty(filter.getDescripption())) {
			flfields.add(new FilterField(getCond(flfields) + "descripption=?",
					filter.getDescripption()));
		}

		if (!CheckNull.isEmpty(filter.getDt())) {
			flfields.add(new FilterField(getCond(flfields) + "dt=?", filter
					.getDt()));
		}

		if (!CheckNull.isEmpty(filter.getKt())) {
			flfields.add(new FilterField(getCond(flfields) + "kt=?", filter
					.getKt()));
		}

		if (!CheckNull.isEmpty(filter.getFiletype())) {
			if (filter.getFiletype().equals("EXPT")) {
				flfields.add(new FilterField(getCond(flfields)
						+ "tr_type_b is null and id<>?", filter.getFiletype()));
			} else {
				flfields.add(new FilterField(getCond(flfields)
						+ "tr_type_expt is  null and id<>?", filter
						.getFiletype()));
			}

		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 10000000));

		return flfields;
	}

	public static int getCount(HumoOperationFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		// sql.append("SELECT count(*) ct FROM humo_oper_type ");
		sql.append("SELECT count(*) ct FROM (select t.operation_id, h.rowid rowid_, o.descripption, (select t.acc_dt || ' - ' || substr(ac.account, 1, 8) from bf_tr_acc ac where ac.acc_template_id = t.acc_dt and ac.branch = '00394') as \"DT\", (select t.acc_ct || ' - ' || substr(ac.account, 1, 8)  from bf_tr_acc ac  where ac.acc_template_id = t.acc_ct  and ac.branch = '00394') as \"KT\",  h.*  from bf_tr_template t, humo_oper_type h, bf_tr_operations o where t.operation_id = o.id and t.operation_id = h.id) c ");
		// String msql
		// ="SELECT c.*,rowid_ rid FROM (select t.operation_id, h.rowid rowid_, o.descripption, (select t.acc_dt || ' - ' || substr(ac.account, 1, 8) from bf_tr_acc ac where ac.acc_template_id = t.acc_dt and ac.branch = '00394') as \"DT\", (select t.acc_ct || ' - ' || substr(ac.account, 1, 8)  from bf_tr_acc ac  where ac.acc_template_id = t.acc_ct  and ac.branch = '00394') as \"KT\",  h.*  from bf_tr_template t, humo_oper_type h, bf_tr_operations o where t.operation_id = o.id and t.operation_id = h.id) c ";

		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<HumoOperation> getHumoOperationsFl(int pageIndex,
			int pageSize, HumoOperationFilter filter) {

		List<HumoOperation> list = new ArrayList<HumoOperation>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				/*
				 * list.add(new HumoOperation( rs.getString("id"),
				 * rs.getString("term_type"), rs.getString("oper"),
				 * rs.getString("card_branch"), rs.getString("term_branch"),
				 * rs.getString("tr_type_expt"), rs.getString("tr_type_b"),
				 * rs.getString("mcc"), rs.getString("komis"),
				 * rs.getString("terminal"), rs.getString("acc_term"),
				 * rs.getString("accnt_ccy"), rs.getString("tran_ccy"),
				 * rs.getString("deb_cred"), rs.getString("country"),
				 * rs.getString("rid")));
				 */

				HumoOperation item = new HumoOperation();
				item.setId(rs.getString("id"));
				item.setTerm_type(rs.getString("term_type"));
				item.setOper(rs.getString("oper"));
				item.setCard_branch(rs.getString("card_branch"));
				item.setTerm_branch(rs.getString("term_branch"));
				item.setTr_type_expt(rs.getString("tr_type_expt"));
				item.setTr_type_b(rs.getString("tr_type_b"));
				item.setMcc(rs.getString("mcc"));
				item.setKomis(rs.getString("komis"));
				item.setTerminal(rs.getString("terminal"));
				item.setAcc_term(rs.getString("acc_term"));
				item.setAccnt_ccy(rs.getString("accnt_ccy"));
				item.setTran_ccy(rs.getString("tran_ccy"));
				item.setDeb_cred(rs.getString("deb_cred"));
				item.setCountry(rs.getString("country"));
				item.setRid(rs.getString("rid"));

				item.setIn_file(rs.getString("in_file"));
				item.setTr_type2_expt(rs.getString("tr_type2_expt"));
				item.setMsc(rs.getString("msc"));
				item.setOperation_id(rs.getString("operation_id"));
				item.setDescripption(rs.getString("descripption"));
				item.setDt(rs.getString("dt"));
				item.setKt(rs.getString("kt"));
				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		System.out.println("size " + list.size());
		return list;

	}

	public HumoOperation getHumoOperation(int humooperationId) {

		HumoOperation humooperation = new HumoOperation();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM humo_oper_type WHERE id=?");
			ps.setInt(1, humooperationId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				humooperation = new HumoOperation();

				humooperation.setId(rs.getString("id"));
				humooperation.setTerm_type(rs.getString("term_type"));
				humooperation.setOper(rs.getString("oper"));
				humooperation.setCard_branch(rs.getString("card_branch"));
				humooperation.setTerm_branch(rs.getString("term_branch"));
				humooperation.setTr_type_expt(rs.getString("tr_type_expt"));
				humooperation.setTr_type_b(rs.getString("tr_type_b"));
				humooperation.setMcc(rs.getString("mcc"));
				humooperation.setKomis(rs.getString("komis"));
				humooperation.setTerminal(rs.getString("terminal"));
				humooperation.setAcc_term(rs.getString("acc_term"));
				humooperation.setAccnt_ccy(rs.getString("accnt_ccy"));
				humooperation.setTran_ccy(rs.getString("tran_ccy"));
				humooperation.setDeb_cred(rs.getString("deb_cred"));
				humooperation.setCountry(rs.getString("country"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return humooperation;
	}

	public static HumoOperation create(HumoOperation humooperation) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			/*
			 * ps =
			 * c.prepareStatement("SELECT SEQ_humooperation.NEXTVAL id FROM DUAL"
			 * ); ResultSet rs = ps.executeQuery(); if (rs.next()) {
			 * humooperation.setId(rs.getInt("id")); }
			 */
			ps = c.prepareStatement("INSERT INTO humo_oper_type (id, term_type, oper, card_branch, term_branch, tr_type_expt, tr_type_b, mcc, komis, terminal, acc_term, accnt_ccy, tran_ccy, deb_cred, country, in_file, tr_type2_expt, msc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, humooperation.getId());
			ps.setString(2, humooperation.getTerm_type());
			ps.setString(3, humooperation.getOper());
			ps.setString(4, humooperation.getCard_branch());
			ps.setString(5, humooperation.getTerm_branch());
			ps.setString(6, humooperation.getTr_type_expt());
			ps.setString(7, humooperation.getTr_type_b());
			ps.setString(8, humooperation.getMcc());
			ps.setString(9, humooperation.getKomis());
			ps.setString(10, humooperation.getTerminal());
			ps.setString(11, humooperation.getAcc_term());
			ps.setString(12, humooperation.getAccnt_ccy());
			ps.setString(13, humooperation.getTran_ccy());
			ps.setString(14, humooperation.getDeb_cred());
			ps.setString(15, humooperation.getCountry());
			ps.setString(16, humooperation.getIn_file());
			ps.setString(17, humooperation.getTr_type2_expt());
			ps.setString(18, humooperation.getMsc());

			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			ISLogger.getLogger()
			.error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return humooperation;
	}

	public static void update(HumoOperation humooperation) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("UPDATE humo_oper_type SET id=?, term_type=?, oper=?, card_branch=?, term_branch=?, tr_type_expt=?, tr_type_b=?, mcc=?, komis=?, terminal=?, acc_term=?, accnt_ccy=?, tran_ccy=?, deb_cred=?, country=?, in_file=?, tr_type2_expt=?, msc=? WHERE rowid=?");

			ps.setString(1, humooperation.getId());
			ps.setString(2, humooperation.getTerm_type());
			ps.setString(3, humooperation.getOper());
			ps.setString(4, humooperation.getCard_branch());
			ps.setString(5, humooperation.getTerm_branch());
			ps.setString(6, humooperation.getTr_type_expt());
			ps.setString(7, humooperation.getTr_type_b());
			ps.setString(8, humooperation.getMcc());
			ps.setString(9, humooperation.getKomis());
			ps.setString(10, humooperation.getTerminal());
			ps.setString(11, humooperation.getAcc_term());
			ps.setString(12, humooperation.getAccnt_ccy());
			ps.setString(13, humooperation.getTran_ccy());
			ps.setString(14, humooperation.getDeb_cred());
			ps.setString(15, humooperation.getCountry());
			ps.setString(16, humooperation.getIn_file());
			ps.setString(17, humooperation.getTr_type2_expt());
			ps.setString(18, humooperation.getMsc());
			ps.setString(19, humooperation.getRid());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			ISLogger.getLogger()
			.error(com.is.utils.CheckNull.getPstr(e));

			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void remove(HumoOperation humooperation) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM humooperation WHERE rid=?");
			ps.setString(1, humooperation.getRid());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

	public static Res del_rec(String row_id, String alias) {

		Connection c = null;

		PreparedStatement ps=null;
		Res res1 = new Res(-1, "Îøèáêà");

		try {
			c = ConnectionPool.getConnection(alias);
  		    //cs = c.prepareCall("begin delete from nibbd_lock_dtl where id_idx = ?; delete from nibbd_lock where id=?; end;");
  		    ps = c.prepareCall("DELETE FROM humo_oper_type WHERE rowid=?");
			ps.setString(1, row_id);
				
			ps.executeUpdate();
			c.commit();
			res1.setCode(0);
		} catch (SQLException e) {
			res1.setCode(1);
			res1.setName(e.getMessage());
			ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error(e.getStackTrace());
				e.printStackTrace();
			}
			
			ConnectionPool.close(c);
		}
		return res1;
		
	}
	public static Res updateTimeTable(TimeTable timeTable) {
		Res res = null;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE HUMO_PROCESSING_TASKS SET START_TIME=?, END_TIME=?, STATUS=? WHERE id=?");

			ps.setString(1, timeTable.getStart_time());
			ps.setString(2, timeTable.getEnd_time());
			ps.setString(3, timeTable.getStatus());
			ps.setString(4, timeTable.getId());

			ps.executeUpdate();
			c.commit();
			res = new Res(0, "OK");
		} catch (SQLException e) {
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error(
						(Object) CheckNull.getPstr((Exception) e));
			}
			ConnectionPool.close(c);
		}
		return res;
	}

	public static List<TimeTable> getTimeTableList(final String alias) {

		final List<TimeTable> list = new ArrayList<TimeTable>();
		Connection c = null;

		final StringBuffer sql = new StringBuffer();
		sql.append("select t.id, sst.name, t.sign_work_day, t.start_time, t.end_time, t.status from HUMO_PROCESSING_TASKS t, HUMO_PROCESSING_SS_TASK sst where t.id = sst.id order by t.id");

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			// ps.setString(1, pBranch);
			// ps.setString(2, pId_client);

			rs = ps.executeQuery();
			while (rs.next()) {
				final TimeTable card = new TimeTable();
				card.setId(rs.getString("id"));
				card.setName(rs.getString("name"));
				card.setSign_work_day(rs.getString("sign_work_day"));
				card.setStart_time(rs.getString("start_time"));
				card.setEnd_time(rs.getString("end_time"));
				card.setStatus(rs.getString("status"));
				list.add(card);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			ISLogger.getLogger().error(
					"getContractCardList_ABS SQLException =>" + e.getMessage());
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex2) {
			}
			ConnectionPool.close(c);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex3) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex4) {
		}
		ConnectionPool.close(c);
		return list;
	}

	public static Res deleteHUMO_PROCESSING_CURR_FILES(String job_type, String file_id) {
		Res res = null;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete from HUMO_PROCESSING_CURR_FILES where job_type=? and file_id=?");

			ps.setString(1, job_type);
			ps.setString(2, file_id);

			ps.executeUpdate();
			c.commit();
			res = new Res(0, "OK");
		} catch (SQLException e) {
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error(
						(Object) CheckNull.getPstr((Exception) e));
			}
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res deleteHUMO_FILE_CLEARING(String file_id) {
		Res res = null;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			//c = ConnectionPool.getConnection();
			c = getTimerConnection();
			ps = c.prepareStatement("delete from HUMO_FILE_CLEARING where file_id=?");

			ps.setString(1, file_id);

			ps.executeUpdate();
			c.commit();
			res = new Res(0, "OK");
		} catch (SQLException e) {
			res = new Res(-1, e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				ISLogger.getLogger().error(
						(Object) CheckNull.getPstr((Exception) e));
			}
			ConnectionPool.close(c);
		}
		return res;
	}
	
	 public static Connection getTimerConnection() throws SQLException {
	        try {
	            final Context context = new InitialContext();
	            final Context envContext = (Context)context.lookup("java:/comp/env");
	            final DataSource datasource = (DataSource)envContext.lookup("jdbc/clearing");
	            if (datasource == null) {
	                throw new Exception("No DataSource");
	            }
	            final PoolConfiguration p = datasource.getPoolProperties();
	            p.setRemoveAbandonedTimeout(1800);
	            datasource.setPoolProperties(p);
	            final Connection c = datasource.getConnection();
	            return c;
	        }
	        catch (Exception e) {
	            ISLogger.getLogger().error((Object)e.getStackTrace());
	            throw new RuntimeException("Database Not Available.");
	        }
	    }
}
