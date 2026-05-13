package com.is.bpri.bpr_employee;

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

public class BprEmployeeService {
	
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "select * from ss_bpr_employees";
	
	private static List<FilterField> getFilterFields(BprEmployee filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (filter.getId()!=null&&!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (filter.getLabel()!=null&&!CheckNull.isEmpty(filter.getLabel())) {
			flfields.add(new FilterField(getCond(flfields) + "label=?", filter.getLabel()));
		}
		if (filter.getValue()!=null&&!CheckNull.isEmpty(filter.getValue())) {
			flfields.add(new FilterField(getCond(flfields) + "value=?", filter.getValue()));
		}
		if (filter.getBranch()!=null&&!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}
	
	protected static int getCount(BprEmployee filter, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM ss_bpr_employees ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
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

	protected static List<BprEmployee> getBproductsFl(int pageIndex, int pageSize,
			BprEmployee filter, String alias) {
		ISLogger.getLogger().error("тут алиас "+alias);
		List<BprEmployee> list = new ArrayList<BprEmployee>();
		Connection c = null;
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
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			System.out.println(sql);
			ISLogger.getLogger().error("“ут селект "+sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new BprEmployee(
						rs.getLong("id"),
						rs.getString("label"),
						rs.getString("value"),
						rs.getString("branch")
						));
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
	
	protected static void createEmployee(BprEmployee employee,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select seq_ss_bpr_employees.nextval id from dual");
			rs = ps.executeQuery();
			if(rs.next()) id = rs.getLong(1);
			else throw new Exception("Fatal Error!");
			ps = c.prepareStatement("insert into ss_bpr_employees (id,label,value,branch) values (?,?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, employee.getLabel());
			ps.setString(3, employee.getValue());
			ps.setString(4, employee.getBranch());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			if(CheckNull.getPstr(e).contains("XPK_BPR_EMPLOYEES")){
				res.setName("“акой тип сотрудника уже указан в этом филиале");
			} else {
				res.setName(CheckNull.getPstr(e));
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	protected static void updateEmployee(BprEmployee employee,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update ss_bpr_employees set label = ?, value = ?, branch = ? where id = ?");
			ps.setString(1, employee.getLabel());
			ps.setString(2, employee.getValue());
			ps.setString(3, employee.getBranch());
			ps.setLong(4, employee.getId());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			if(CheckNull.getPstr(e).contains("XPK_BPR_EMPLOYEES")){
				res.setName("“акой тип сотрудника уже указан в этом филиале");
			} else {
				res.setName(CheckNull.getPstr(e));
			}
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	protected static void removeEmployee(BprEmployee employee){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete from ss_bpr_employees where id=?");
			ps.setLong(1, employee.getId());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	protected static List<RefData> getS_mfo(String branch){
		return Utils.getRefData("select bank_id,bank_id||' '||bank_name from s_mfo s where bank_type=(select bank_type from s_mfo where bank_id = '"+branch+"') and ACT='A' order by bank_id", "");
	}
	
	protected static List<RefData> getTypesEmployees(String module_id){
		return Utils.getRefData("select id,value from Bpr_Ss_Reference_Fields where sid=(select id from Bpr_Ss_Reference where module_id="+module_id+") order by value", "");
	}
	
	protected static String getTypeEmployeeName(String module_id,String id){
		return Utils.getData("select value from Bpr_Ss_Reference_Fields where sid=(select id from Bpr_Ss_Reference where module_id="+module_id+") and id="+id,"");
	}
	
}
