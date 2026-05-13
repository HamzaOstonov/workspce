package com.is.bpri.ldhisrate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class LdHisRateService {
	
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";

	private static String msql = " SELECT * FROM (select be.id, be.bpr_id,be.exp_id,be.rate_type,be.rate_method, be.pay_method, br.id idRate, br.bpr_id data, br.exp_id dataExp, br.rate_id, br.rate from bpr_ld_exp be, bpr_ld_rate br where be.id=br.id and be.bpr_id = br.bpr_id) ";
	private static String ratesql = " SELECT * FROM (select distinct et.id exp_id_val, et.name, be.id, be.bpr_id, be.exp_id, be.rate_type, be.rate_method, be.pay_method, br.id idRate, br.bpr_id data, br.exp_id dataExp, br.rate_id, br.rate from bpr_ld_exp be, bpr_ld_rate br, ss_ld_exp_type et where et.id = be.exp_id and et.id = br.exp_id and be.id = br.id and be.bpr_id = br.bpr_id and et.id<13 order by be.bpr_id) ";
	private static String addratesql = " SELECT * FROM (SELECT et.id, et.name FROM ss_ld_exp_type et where id<13) ";

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(LdHisRateFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getExp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "exp_id=?", filter
					.getExp_id()));
		}
		if (!CheckNull.isEmpty(filter.getRate_type())) {
			flfields.add(new FilterField(getCond(flfields) + "rate_type=?",
					filter.getRate_type()));
		}
		if (!CheckNull.isEmpty(filter.getRate_method())) {
			flfields.add(new FilterField(getCond(flfields) + "rate_method=?",
					filter.getRate_method()));
		}
		if (!CheckNull.isEmpty(filter.getPay_method())) {
			flfields.add(new FilterField(getCond(flfields) + "pay_method=?",
					filter.getPay_method()));
		}
		if (!CheckNull.isEmpty(filter.getBprId())) {
			flfields.add(new FilterField(getCond(flfields) + "bprid=?", filter
					.getBprId()));
		}
		if (!CheckNull.isEmpty(filter.getExpId())) {
			flfields.add(new FilterField(getCond(flfields) + "expId=?", filter
					.getExpId()));
		}
		if (!CheckNull.isEmpty(filter.getRate_id())) {
			flfields.add(new FilterField(getCond(flfields) + "rate_id=?",
					filter.getRate_id()));
		}
		if (!CheckNull.isEmpty(filter.getRate())) {
			flfields.add(new FilterField(getCond(flfields) + "rate=?", filter
					.getRate()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	private static List<FilterField> getFilterFieldsPrs(ParamsFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId_())) {
			flfields.add(new FilterField(getCond(flfields) + "id_=?", filter
					.getId_()));
		}
		if (!CheckNull.isEmpty(filter.getExp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter
					.getExp_id()));
		}

		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}

		if (!CheckNull.isEmpty(filter.getExp_id_())) {
			flfields.add(new FilterField(getCond(flfields) + "exp_id=?", filter
					.getExp_id_()));
		}
		if (!CheckNull.isEmpty(filter.getRate_type())) {
			flfields.add(new FilterField(getCond(flfields) + "rate_type=?",
					filter.getRate_type()));
		}
		if (!CheckNull.isEmpty(filter.getRate_method())) {
			flfields.add(new FilterField(getCond(flfields) + "rate_method=?",
					filter.getRate_method()));
		}
		if (!CheckNull.isEmpty(filter.getPay_method())) {
			flfields.add(new FilterField(getCond(flfields) + "pay_method=?",
					filter.getPay_method()));
		}
		if (!CheckNull.isEmpty(filter.getBprId())) {
			flfields.add(new FilterField(getCond(flfields) + "bprid=?", filter
					.getBprId()));
		}
		if (!CheckNull.isEmpty(filter.getExpId())) {
			flfields.add(new FilterField(getCond(flfields) + "expId=?", filter
					.getExpId()));
		}
		if (!CheckNull.isEmpty(filter.getRate_id())) {
			flfields.add(new FilterField(getCond(flfields) + "rate_id=?",
					filter.getRate_id()));
		}
		if (!CheckNull.isEmpty(filter.getRate())) {
			flfields.add(new FilterField(getCond(flfields) + "rate=?", filter
					.getRate()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	private static List<FilterField> getFilterFieldsAddRate(
			LdHisRateAddFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getExp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter
					.getExp_id()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(LdHisRateFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_ld_exp ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());

			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return n;
	}

	public static int getCountPrs(ParamsFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFieldsPrs(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM ss_ld_exp_type");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());

			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());

			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return n;
	}

	public static int getCountAddRate(LdHisRateAddFilter filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFieldsAddRate(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM ss_ld_exp_type ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<LdHisRate> getLdHisRatesFl(int pageIndex, int pageSize,
			LdHisRateFilter filter, String alias) {
		List<LdHisRate> list = new ArrayList<LdHisRate>();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			System.out.println("sql 1 = "+sql);
			for (params = 0; params < flFields.size(); params++) {
				System.out.println("index = "+(params+1)+" value "+flFields.get(params).getColobject());
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new LdHisRate(rs.getInt("id"),
										rs.getInt("id"),
											rs.getInt("bpr_id"),
												rs.getString("exp_id"),
													rs.getString("rate_type"),
														rs.getString("rate_method"),
															rs.getString("pay_method"),
																rs.getInt("bpr_id"),
																	rs.getString("exp_id"),
																		rs.getString("rate_id"),
																			rs.getDouble("rate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<Params> getLdHisRatesFlPrs(int pageIndex, int pageSize,
			ParamsFilter filter, String alias) {
		List<Params> list = new ArrayList<Params>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFieldsPrs(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(ratesql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			System.out.println("sql 2 = "+sql);
			for (params = 0; params < flFields.size(); params++) {
				System.out.println("index = "+(params+1)+" value "+flFields.get(params).getColobject());
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Params(rs.getInt("id"), rs.getString("name"), rs
						.getInt("id"), rs.getInt("id"), rs.getInt("bpr_id"), rs
						.getInt("exp_id"), rs.getInt("rate_type"), rs
						.getInt("rate_method"), rs.getInt("pay_method"), rs
						.getInt("bpr_id"), rs.getInt("exp_id"), rs
						.getInt("rate_id"), rs.getDouble("rate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<LdHisRateAdd> getLdHisRatesFlAddRate(int pageIndex,
			int pageSize, LdHisRateAddFilter filter, String alias) {

		// System.out.println("getLdHisRatesFlPrs");
		List<LdHisRateAdd> list = new ArrayList<LdHisRateAdd>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFieldsAddRate(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(addratesql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			System.out.println("sql 3 = "+sql);
			for (params = 0; params < flFields.size(); params++) {
				System.out.println("index = "+(params+1)+" value "+flFields.get(params).getColobject());
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new LdHisRateAdd(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;

	}

	public static void create(LdHisRate ldhisrate, String alias, Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps1 = c.prepareStatement("SELECT SEQ_bpr_ld_rate.NEXTVAL Id FROM DUAL");
			rs1 = ps1.executeQuery();
			if (rs1.next()) {
				ldhisrate.setIdRate(rs1.getInt("Id"));
			}
			ps = c.prepareStatement("INSERT INTO bpr_ld_exp (id, bpr_id, exp_id, rate_type, rate_method, pay_method ) VALUES (?,?,?,?,?,?)");
			ps1 = c.prepareStatement("INSERT INTO bpr_ld_rate (id, bpr_id, exp_id, rate_id, rate) VALUES (?,?,?,?,?)");
			ps.setInt(1, ldhisrate.getIdRate());
			ps.setInt(2, ldhisrate.getBpr_id());
			ps.setString(3, ldhisrate.getExp_id());
			ps.setString(4, ldhisrate.getRate_type());
			ps.setString(5, ldhisrate.getRate_method());
			ps.setString(6, ldhisrate.getPay_method());
			ps1.setInt(1, ldhisrate.getIdRate());
			ps1.setInt(2, ldhisrate.getBprId());
			ps1.setString(3, ldhisrate.getExp_id());
			ps1.setString(4, ldhisrate.getRate_id());
			if(ldhisrate.getRate()==null){
				ps1.setString(5, null);
			} else {
				ps1.setDouble(5, ldhisrate.getRate());
			}
			ps.executeUpdate();
			ps1.executeUpdate();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(rs1);
			Utils.close(ps);
			Utils.close(ps1);
			ConnectionPool.close(c);
		}
	}

	public static void update(LdHisRate ldhisrate, String alias, Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_exp SET exp_id=?, rate_type=?, rate_method=?, pay_method=?  WHERE bpr_id=? and id=?");
			ps1 = c.prepareStatement("UPDATE bpr_ld_rate SET  exp_id=?, rate_id=?, rate=? WHERE bpr_id=? and id=?");
			ps.setString(1, ldhisrate.getExp_id());
			ps.setString(2, ldhisrate.getRate_type());
			ps.setString(3, ldhisrate.getRate_method());
			ps.setString(4, ldhisrate.getPay_method());
			ps.setInt(5, ldhisrate.getBpr_id());
			ps.setInt(6, ldhisrate.getId());
			ps1.setString(1, ldhisrate.getExp_id());
			ps1.setString(2, ldhisrate.getRate_id());
			ps1.setDouble(3, ldhisrate.getRate());
			ps1.setInt(4, ldhisrate.getBprId());
			ps1.setInt(5, ldhisrate.getId());
			ps.executeUpdate();
			ps1.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			Utils.close(ps1);
			ConnectionPool.close(c);
		}
	}
	
	public static void remove(int id,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_ld_exp where bpr_ld_exp.id=?");
			ps.setInt(1, id);
			ps.execute();
			ps = c.prepareStatement("delete from bpr_ld_rate where bpr_ld_rate.id=?");
			ps.setInt(1, id);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	// ------------------------Справочники---------------------------

	public static List<RefData> getExp_id(String branch){
		return Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_exp_type where id < 13 order by id",branch);
	}

	public static List<RefData> getRate_type(String branch){
		return Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_rate_type order by data",branch);
	}

	public static List<RefData> getRate_method(String branch){
		return Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_rate_method order by data",branch);
	}

	public static List<RefData> getPay_method(String branch){
		return Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_pay_method order by data",branch);
	}

	public static List<RefData> getExpId(String branch){
		return Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_exp_type where id < 201",branch);
	}

	public static List<RefData> getRate_id(String branch){
		return Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_rate order by data",branch);
	}

	// --------------------------Конец
	// справочников-------------------------------
}
