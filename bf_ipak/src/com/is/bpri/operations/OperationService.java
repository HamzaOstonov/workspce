package com.is.bpri.operations;

import java.math.BigDecimal;
import java.sql.CallableStatement;
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

public class OperationService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "select d.*,(select l.name from state_ld l where l.deal_id=10 and l.id = d.state) state_name from v_ld d";
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}
	
	private static List<FilterField> getFilterFields(Operation filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(getCond(flfields) + "ID_CLIENT=?",filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getS_deal_id())) {
			flfields.add(new FilterField(getCond(flfields) + "s_deal_id=?", filter.getS_deal_id()));
		}
		if (!CheckNull.isEmpty(filter.getV_date())) {
			flfields.add(new FilterField(getCond(flfields) + "v_date=?", new java.sql.Date(filter.getV_date().getTime())));
		}
		if (filter.getSumma()!=null&&filter.getSumma().longValue()>0) {
			flfields.add(new FilterField(getCond(flfields) + "summa=?",filter.getSumma()));
		}
		if (filter.getDoc_num()!=null&&!CheckNull.isEmpty(filter.getDoc_num())) {
			flfields.add(new FilterField(getCond(flfields) + "doc_num=?", filter.getDoc_num()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getForm_id())) {
			flfields.add(new FilterField(getCond(flfields) + "form_id=?", filter.getForm_id()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}
	
	public static int getCount(Operation filter, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from v_ld d");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init }");
			cs.execute();
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
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		return n;
	}
	
	public static List<Operation> getOperationsFl(int pageIndex, int pageSize,
			Operation filter, String alias) {
		List<Operation> list = new ArrayList<Operation>();
		Connection c = null;
		CallableStatement cs = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);
		PreparedStatement ps = null;
		ResultSet rs = null;
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
			cs = c.prepareCall("{ call info.init }");
			cs.execute();
			System.out.println("sql = "+sql);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Operation(
						rs.getLong("id"),
						rs.getString("branch"),
						rs.getLong("form_id"),
						rs.getDate("v_date"),
						rs.getDate("d_date"),
						rs.getString("doc_num"),
						rs.getBigDecimal("realsumma"),
						rs.getDouble("rate"),
						rs.getString("bank"),
						rs.getString("account"),
						rs.getString("name"),
						rs.getString("bank_inter"),
						rs.getString("purpose"),
						rs.getInt("exp_id"),
						rs.getInt("s_deal_id"),
						rs.getInt("state"),
						rs.getString("currency"),
						rs.getBigDecimal("summa"),
						rs.getInt("prev_id"),
						rs.getInt("op_sign"),
						rs.getInt("num"),
						rs.getString("exp_name"),
						rs.getString("cash_acc"),
						rs.getString("id_client"),
						rs.getString("name_cl"),
						rs.getDate("d_date1"),
						rs.getString("doc_type_m"),
						rs.getInt("manual_op"),
						rs.getString("code_plat"),
						rs.getString("id_transh_purp"),
						rs.getString("sp36"),
						rs.getString("account_kazn"),
						rs.getString("code1"),
						rs.getString("code2"),
						rs.getString("code_cb"),
						rs.getString("state_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static void saveOperation(Operation operation,String alias,Res res){
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call proc_ld1.doOper02(?,?,?,?,?,?,?,?,?,?) }");
			cs.setString(1, operation.getBranch());
			cs.setLong(2,operation.getForm_id());
			cs.setString(3, operation.getBank());
			cs.setString(4, operation.getAccount());
			cs.setString(5, "");
			cs.setDate(6, operation.getD_date()==null?null:new java.sql.Date(operation.getD_date().getTime()));
			cs.setLong(7, operation.getNum());
			cs.setString(8, "");
			cs.setString(9, operation.getId_transh_purp());
			cs.setDouble(10, operation.getSumma().doubleValue());
			cs.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			try{ c.rollback(); } catch (Exception exception) { e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(exception)); }
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static void executeOperation(String action_id, Operation operation,String alias,Res res){
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call param.clearall() }");
			cs.execute();
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			cs = c.prepareCall("{ call param.setparam(?,?) }");
			cs.setString(1, "ACTION_ID"); cs.setString(2, action_id); cs.execute();
			System.out.println("ACTION_ID = "+action_id);
			cs.setString(1, "ACCOUNT"); cs.setString(2, operation.getAccount()); cs.execute();
			System.out.println("ACCOUNT = "+operation.getAccount());
			cs.setString(1, "CODE_PLAT"); cs.setString(2, operation.getCode_plat()); cs.execute();
			System.out.println("CODE_PLAT = "+operation.getCode_plat());
			cs.setString(1, "NUM"); cs.setInt(2, operation.getNum()); cs.execute();
			System.out.println("NUM = "+operation.getNum());
			cs.setString(1, "D_DATE"); cs.setDate(2, operation.getD_date()==null?null:new java.sql.Date(operation.getD_date().getTime())); cs.execute();
			System.out.println("D_DATE = "+operation.getD_date());
			cs.setString(1, "RATE"); cs.setDouble(2, operation.getRate()); cs.execute();
			System.out.println("RATE = "+operation.getRate());
			cs.setString(1, "BANK"); cs.setString(2, operation.getBank()); cs.execute();
			System.out.println("BANK = "+operation.getBank());
			cs.setString(1, "DOC_TYPE_M"); cs.setString(2, operation.getDoc_type_m()); cs.execute();
			System.out.println("DOC_TYPE_M = "+operation.getDoc_type_m());
			cs.setString(1, "DOC_NUM"); cs.setString(2, operation.getDoc_num()); cs.execute();
			System.out.println("DOC_NUM = "+operation.getDoc_num());
			cs.setString(1, "FORM_ID"); cs.setLong(2, operation.getForm_id()); cs.execute();
			System.out.println("FORM_ID = "+operation.getForm_id());
			cs.setString(1, "SUMMA"); cs.setBigDecimal(2, operation.getSumma().multiply(new BigDecimal(100))); cs.execute();
			System.out.println("SUMMA = "+operation.getSumma());
			cs.setString(1, "REALSUMMA"); cs.setBigDecimal(2, operation.getSumma()); cs.execute();
			cs = c.prepareCall("{ call bpr_control.executeOperation() }");
			cs.execute();
			c.commit();
		} catch (Exception e) {
			Utils.rollback(c);
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static String getTarget(Long form_id, String oper_id, String alias){
		return Utils.getData("select proc_ld.GetDefaultPurpose("+form_id+","+oper_id+") from dual", alias);
	}
	
	protected static String getAmount(String form_id, String branch, String alias){
		return Utils.getData("select ld_amount/100 from ld_char where id = "+form_id+" and branch = '"+branch+"'", alias);
	}
	
	protected static String getAccInfo(Long form_id, String branch, String alias){
		return Utils.getData("select ld.account||'#%'||acc.name from ld_account ld,account acc where ld.id = "+form_id+" and ld.branch='"+branch+"' and ld.acc_type_id = 2 and ld.account=acc.id", alias);
	}
	
	protected static BigDecimal getRateInfo(Long form_id, String branch, String alias){
		return new BigDecimal(Utils.getData("select rate from ld_rate where id = "+form_id+" and branch = '"+branch+"' and exp_id = 1", alias));
	}
	
	protected static List<String> getNameGridsFieldsVisible(String operation_id){
		List<String> list = new ArrayList<String>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select name from ss_ld_action_grid_visible where id = ?");
			ps.setString(1, operation_id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
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
	
	protected static void insertIntoGridOperation(String operation_id,List<String> list){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("insert into ss_ld_action_grid_visible (id,name) values (?,?)");
			for (int i = 0; i < list.size(); i++) {
				ps.setString(1, operation_id);
				ps.setString(2, list.get(i));
				ps.addBatch();
			}
			ps.executeBatch();
			c.commit();
		} catch (Exception e) {
			Utils.rollback(c);
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	protected static List<RefData> getS_nazn(){
		return Utils.getRefData("select code_plat, name_plat from s_nazn where act='A' order by name_plat", "");
	}
	
	public static List<RefData> getOperation_type(String alias){
		return Utils.getRefData("select id,name from ss_ld_action_desc a where a.id in (1,30) order by id", alias);
//		return Utils.getRefData("select id,name from ss_ld_action_desc a where a.id<200 or a.id>1000 order by id", alias);
	}
}
