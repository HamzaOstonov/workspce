package com.is.bpri.bpr_ld_forms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class BprLdFormsService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select bpr_ld_forms.bpr_id,bpr_ld_forms.head_of_bank,SS_TYPE_ANS.Name,bpr_ld_forms.is_ld,bpr_ld_forms.state from bpr_ld_forms,SS_TYPE_ANS where bpr_ld_forms.is_ld=SS_TYPE_ANS.CODE (+) ";

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " and ";
	}

	private static List<FilterField> getFilterFields(BprLdFormsFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getHead_of_bank())) {
			flfields.add(new FilterField(getCond(flfields) + "head_of_bank=?",
					filter.getHead_of_bank()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(BprLdFormsFilter filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_ld_forms ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<BprLdForms> getBprLdFormssFl(int pageIndex,
			int pageSize, BprLdFormsFilter filter, String alias) {
		List<BprLdForms> list = new ArrayList<BprLdForms>();
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
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprLdForms(
						rs.getInt("bpr_id"),
						rs.getString("head_of_bank"),
						rs.getString("is_ld"),
						rs.getString("name"),
						rs.getString("state")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;

	}
	
	public static List<RefData> getSS_TYPE_ANS(String alias){
		return Utils.getRefData("select SS_TYPE_ANS.Code data, SS_TYPE_ANS.Code||' '||SS_TYPE_ANS.Name label from SS_TYPE_ANS", alias);
	}

	public static void create(BprLdForms bprldforms,String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO bpr_ld_forms (bpr_id, head_of_bank,is_ld,state) VALUES (?,?,?,?) ");
			ps.setInt(1, bprldforms.getBpr_id());
			ps.setString(2, bprldforms.getHead_of_bank());
			ps.setString(3, bprldforms.getIs_ld());
			ps.setString(4, bprldforms.getScoring());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static void update(BprLdForms bprldforms,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_forms SET head_of_bank=?, is_ld=?, state=?  WHERE bpr_id=?");
			ps.setString(1, bprldforms.getHead_of_bank());
			ps.setString(2, bprldforms.getIs_ld());
			ps.setString(3, bprldforms.getScoring());
			ps.setInt(4, bprldforms.getBpr_id());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static Res remove(BprLdForms bprldforms) {
		Connection c = null;
		Res res = new Res();
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("DELETE FROM TF_bprldforms WHERE bpr_id=?");
			ps.setLong(1, bprldforms.getBpr_id());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

}
