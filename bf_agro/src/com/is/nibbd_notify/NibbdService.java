package com.is.nibbd_notify;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class NibbdService {
	//private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	//private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by id";
	//private static String msql = "select * from nibbd_lock ";
	
	private static String psql1 = "SELECT t.* FROM(SELECT t.*, ROWNUM rwnm FROM (SELECT * FROM (";
	private static String psql2 = " ) s ) t WHERE ROWNUM <= ? order by id) t WHERE t.rwnm >= ?";
	private static String msql = "select * from (select * from nibbd_lock order by id)";	
	
	private static String psqlDtl1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psqlDtl2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by id";
	private static String msqlDtl = "select * from nibbd_lock_dtl ";
	

	public List<Nibbd_idx> getTrTemplate(String alias) {

		List<Nibbd_idx> list = new ArrayList<Nibbd_idx>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			Statement s = c.createStatement();
			ResultSet rs = s
					.executeQuery("select * from nibbd_lock order by id");
			while (rs.next()) {
				Nibbd_idx a = new Nibbd_idx();
				list.add(a);
			}
		} catch (SQLException e) {
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

	private static List<FilterField> getFilterFields(NibbdFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "account like ?",
					filter.getAccount()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter
					.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getCur_date())) {
			flfields.add(new FilterField(getCond(flfields) + "cur_date=?", filter
					.getCur_date()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(getCond(flfields) + "id_client=?",
					filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getInn())) {
			flfields.add(new FilterField(getCond(flfields) + "inn like ?",
					filter.getInn()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "name like ?",
					filter.getName()));
		}
		/*if (!CheckNull.isEmpty(filter.getPurpose())) {
			flfields.add(new FilterField(getCond(flfields) + "purpose=?",
					filter.getPurpose()));
		}
		if (!CheckNull.isEmpty(filter.getPurpose_code())) {
			flfields.add(new FilterField(getCond(flfields) + "purpose_code=?",
					filter.getPurpose_code()));
		}
		if (!CheckNull.isEmpty(filter.getOrd())) {
			flfields.add(new FilterField(getCond(flfields) + "ord=?", filter
					.getOrd()));
		}*/
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(NibbdFilter filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM nibbd_lock ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				Object obj = flFields.get(k).getColobject();
                if (obj instanceof java.util.Date) {
                    ps.setDate(k + 1, CheckNull.d2sql((java.util.Date) obj));
                    continue;
                }
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Nibbd_idx> getTrTemplatesFl(int pageIndex, int pageSize,
			NibbdFilter filter, String alias) {

		List<Nibbd_idx> list = new ArrayList<Nibbd_idx>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		//int v_lowerbound = pageIndex * pageSize + 1;
		
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
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			/*for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
            */
			for (params = 0; params < flFields.size(); params++) {
				Object obj = flFields.get(params).getColobject();
				if (obj instanceof java.util.Date) {
					ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
					continue;
				}
				if (obj instanceof String) {
					ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
					continue;
				}
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Nibbd_idx a = new Nibbd_idx();
				a.setId(rs.getLong("id"));
				a.setRequest_id(rs.getString("request_id"));
				a.setInn(rs.getString("inn"));
				a.setName(rs.getString("name"));
				a.setAddress(rs.getString("address"));
				a.setBank(rs.getString("bank"));
				a.setBranch(rs.getString("branch"));
				a.setAccount(rs.getString("account"));
				a.setRest_amount(rs.getLong("rest_amount"));
				a.setOpened(rs.getDate("opened"));
				a.setClosed(rs.getDate("closed"));
				a.setDebt_info(rs.getLong("debt_info"));
				a.setId_client(rs.getString("id_client"));
				a.setLast_oper_date(rs.getDate("last_oper_date"));
				a.setSys_date(rs.getDate("sys_date"));
				a.setCur_date(rs.getDate("cur_date"));
				a.setFile_id(rs.getLong("file_id"));
				a.setState(rs.getString("state"));
				list.add(a);
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public Nibbd_idx getTrTemplate(int trtemplateId, String alias) {

		Nibbd_idx trtemplate = new Nibbd_idx();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM BF_TR_TEMPLATE WHERE trtemplate_id=?");
			ps.setInt(1, trtemplateId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				trtemplate = new Nibbd_idx();

				/*
				 * trtemplate.setId(rs.getInt("id"));
				 * trtemplate.setOperation_id(rs.getInt("operation_id"));
				 * trtemplate.setAcc_dt(rs.getInt("acc_dt"));
				 * trtemplate.setAcc_ct(rs.getInt("acc_ct"));
				 * trtemplate.setCurrency(rs.getString("currency"));
				 * trtemplate.setDoc_type(rs.getString("doc_type"));
				 * trtemplate.setCash_code(rs.getString("cash_code"));
				 * trtemplate.setPurpose(rs.getString("purpose"));
				 * trtemplate.setPurpose_code(rs.getString("purpose_code"));
				 * trtemplate.setOrd(rs.getInt("ord"));
				 * trtemplate.setPay_type(rs.getInt("pay_type"));
				 * trtemplate.setTrans_type(rs.getInt("trans_type"));
				 * trtemplate.setPerc_for_tr(rs.getDouble("perc_for_tr"));
				 */
			}
		} catch (Exception e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return trtemplate;
	}

	private static List<FilterField> getFilterFieldsDtl(NibbdFilterDtl filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getId_idx())) {
			flfields.add(new FilterField(getCond(flfields) + "id_idx=?",
					filter.getId_idx()));
		}
		/*if (!CheckNull.isEmpty(filter.getAcc_dt())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_dt=?", filter
					.getAcc_dt()));
		}
		if (!CheckNull.isEmpty(filter.getAcc_ct())) {
			flfields.add(new FilterField(getCond(flfields) + "acc_ct=?", filter
					.getAcc_ct()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?",
					filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getDoc_type())) {
			flfields.add(new FilterField(getCond(flfields) + "doc_type=?",
					filter.getDoc_type()));
		}
		if (!CheckNull.isEmpty(filter.getCash_code())) {
			flfields.add(new FilterField(getCond(flfields) + "cash_code=?",
					filter.getCash_code()));
		}
		if (!CheckNull.isEmpty(filter.getPurpose())) {
			flfields.add(new FilterField(getCond(flfields) + "purpose=?",
					filter.getPurpose()));
		}
		if (!CheckNull.isEmpty(filter.getPurpose_code())) {
			flfields.add(new FilterField(getCond(flfields) + "purpose_code=?",
					filter.getPurpose_code()));
		}
		if (!CheckNull.isEmpty(filter.getOrd())) {
			flfields.add(new FilterField(getCond(flfields) + "ord=?", filter
					.getOrd()));
		}*/

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}
	
	public static int getCountDtl(NibbdFilterDtl filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFieldsDtl(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM nibbd_lock_dtl ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<RefData> getStates(String branch)
	{
		@SuppressWarnings("rawtypes")
		List list = new LinkedList();
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection(branch);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("select 0 code, 'Введен. Ждет формирование файла' name from dual union all select 1 code, 'Сформирован файл' name from dual union all select 2 code, 'Удален' name from dual union all select 3 code, 'Все' name from dual order by code ");
			while (rs.next())
				list.add(
						new RefData(rs.getString("code"),
								rs.getString("name")));
		}
		catch (SQLException e)
		{
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally
		{
			ConnectionPool.close(c);
		}
		return list;
	}
	public static String getFileName(Long file_id, String alias) {

		Connection c = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select file_name_arc from nibbd_notification_file t where id=?");
		String rr="";
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setLong(1, file_id);
			ResultSet rs = ps.executeQuery();
            if (rs.next()) {
				rr = rs.getString(1);
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return rr;
	}

	public static Nibbd_answer getAnswer(Long file_id, String request_id, String alias) {
		Connection c = null;
		String req_id=String.format("%020d", Integer.parseInt(request_id)) ;
		com.is.LtLogger.getLogger().error("req_id(1)="+req_id+". file_id(1)="+file_id);
		
		Nibbd_answer rr = new Nibbd_answer();
		StringBuffer sql = new StringBuffer();
		sql.append("select code_answer, text_answer from nibbd_notify_ans_line t where req_id=?");
		//String rr="";
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, req_id);
			ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	rr.setCode_answer(rs.getString(1));
            	rr.setText_answer(rs.getString(2));
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error("req_id="+req_id+". file_id="+file_id);
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return rr;
	}
	
	public static List<Nibbd_dtl> getNibbdDtlFl(int pageIndex, int pageSize,
			NibbdFilterDtl filter, String alias) {

		List<Nibbd_dtl> list = new ArrayList<Nibbd_dtl>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFieldsDtl(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psqlDtl1);
		sql.append(msqlDtl);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psqlDtl2);

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Nibbd_dtl a = new Nibbd_dtl();
				a.setId(rs.getLong("id"));
				a.setId_idx(rs.getLong("id_idx"));
				a.setDebt_file(rs.getString("debt_file"));
				a.setDate_file(rs.getDate("date_file"));
				a.setSid(rs.getString("sid"));
				a.setBid(rs.getString("bid"));
				a.setDoc_type(rs.getString("doc_type"));
				a.setDoc_number(rs.getString("doc_number"));
				a.setDoc_date(rs.getDate("doc_date"));
				a.setPayee_account(rs.getString("payee_account"));
				a.setDoc_amount(rs.getLong("doc_amount"));
				a.setRest_amount(rs.getLong("rest_amount"));
				a.setPurpose_type(rs.getString("purpose_type"));
				a.setPurpose_code(rs.getString("purpose_code"));
				a.setPurposes_code(rs.getString("purposes_code"));
				a.setPurpose(rs.getString("purpose"));
				a.setPayee_mfo(rs.getString("bank_co"));
				
				list.add(a);
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static Res update(Nibbd_idx trtemplate, String alias) {

		Connection c = null;

		Res res1 = new Res(-1, "Ошибка");

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("UPDATE BF_TR_TEMPLATE SET operation_id=?, acc_dt=?, acc_ct=?, currency=?, doc_type=?, cash_code=?, purpose=?, purpose_code=?, ord=?,id_transh_purp=?,pay_type=?,trans_type=?,perc_for_tr=?,pdc =?  WHERE id=?");

			/*
			 * ps.setLong(1,trtemplate.getOperation_id());
			 * ps.setLong(2,trtemplate.getAcc_dt());
			 * ps.setLong(3,trtemplate.getAcc_ct());
			 * ps.setString(4,trtemplate.getCurrency());
			 * ps.setString(5,trtemplate.getDoc_type());
			 * ps.setString(6,(trtemplate
			 * .getCash_code().equals("0"))?null:trtemplate.getCash_code());
			 * ps.setString(7,trtemplate.getPurpose());
			 * ps.setString(8,(trtemplate
			 * .getPurpose_code().equals("0"))?null:trtemplate
			 * .getPurpose_code()); ps.setInt(9,trtemplate.getOrd());
			 * 
			 * if (trtemplate.getId_transh_purp()==123456) ps.setNull(10,
			 * java.sql.Types.INTEGER); else ps.setInt(10,
			 * trtemplate.getId_transh_purp());
			 * 
			 * ps.setInt(11, trtemplate.getPay_type()); ps.setInt(12,
			 * trtemplate.getTrans_type());
			 * 
			 * ps.setDouble(13, trtemplate.getPerc_for_tr());
			 * ps.setString(14,trtemplate.getPdc());
			 */
			ps.setLong(1, trtemplate.getId());

			ps.executeUpdate();
			c.commit();
			res1.setCode(0);
		} catch (SQLException e) {
			res1.setCode(1);
			res1.setName(e.getMessage());
			if (e.getMessage().indexOf("XUK_TR_SETS_TEMLATE_2") > 0) {
				String A = Labels.getLabel("trtemplate.wrong_ord_num");
				res1.setName(A.replaceAll("#1", "" + trtemplate.getId()));
			}

			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return res1;

	}

	public static Res run_proc (String un, String pw, String alias) {

		Connection c = null;
		CallableStatement cs = null;
		Res res1 = new Res(-1, "Ошибка");

		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{call proc_specialclt.CloseDayLock()}");
			cs.execute();
			c.commit();
			res1.setCode(0);
		} catch (SQLException e) {
			res1.setCode(1);
			res1.setName(e.getMessage());
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			
			try {
				if(cs != null){
					cs.close();
				}
			} catch (SQLException e) {
				com.is.LtLogger.getLogger().error(e.getStackTrace());
				e.printStackTrace();
			}
			
			ConnectionPool.close(c);
		}
		return res1;

	}
	
	public static Res del_recs(int sign_all, String alias) {
		//CallableStatement stmt = conn.prepareCall("BEGIN getLogs(?, ?, ?); END;");
		Connection c = null;
		//CallableStatement cs = null;
		PreparedStatement ps = null;
		Res res1 = new Res(-1, "Ошибка");

		try {
			c = ConnectionPool.getConnection(alias);
			if (sign_all==1) {
			  //cs = c.prepareCall("begin delete from nibbd_lock_dtl where id_idx in (select id from nibbd_lock); delete from nibbd_lock; end;");
			  ps = c.prepareStatement("update nibbd_lock set state=2");
			} else {
				//cs = c.prepareCall("begin delete from nibbd_lock_dtl where id_idx in (select id from nibbd_lock where file_id is null); delete from nibbd_lock where file_id is null; end;");
				ps = c.prepareStatement("update nibbd_lock set state=2 where file_id is null");				
			}
				
			ps.execute();
			c.commit();
			res1.setCode(0);
		} catch (SQLException e) {
			res1.setCode(1);
			res1.setName(e.getMessage());
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				com.is.LtLogger.getLogger().error(e.getStackTrace());
				e.printStackTrace();
			}
			
			ConnectionPool.close(c);
		}
		return res1;
		
	}
	
	public static Res del_rec(Long rec_id, String alias) {
		//CallableStatement stmt = conn.prepareCall("BEGIN getLogs(?, ?, ?); END;");
		Connection c = null;
		//CallableStatement cs = null;
		PreparedStatement ps=null;
		Res res1 = new Res(-1, "Ошибка");

		try {
			c = ConnectionPool.getConnection(alias);
  		    //cs = c.prepareCall("begin delete from nibbd_lock_dtl where id_idx = ?; delete from nibbd_lock where id=?; end;");
  		    ps = c.prepareCall("update nibbd_lock set state=? where id=?");
			ps.setInt(1, 2);
			ps.setLong(2, rec_id);
				
			ps.executeUpdate();
			c.commit();
			res1.setCode(0);
		} catch (SQLException e) {
			res1.setCode(1);
			res1.setName(e.getMessage());
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			
			try {
				if(ps != null){
					ps.close();
				}
			} catch (SQLException e) {
				com.is.LtLogger.getLogger().error(e.getStackTrace());
				e.printStackTrace();
			}
			
			ConnectionPool.close(c);
		}
		return res1;
		
	}
	
}
