package com.is.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class ok_academicService extends GenericForwardComposer{
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "SELECT * FROM ok_academic ";
	private  String alias,un,pwd;
	public List<ok_academic> getok_academic() {

		List<ok_academic> list = new ArrayList<ok_academic>();
		Connection c = null;
		
		try {
			alias = ((String) session.getAttribute("alias"));
			un = ((String) session.getAttribute("un"));
			pwd = ((String) session.getAttribute("pwd"));
			c = ConnectionPool.getConnection(un,pwd,alias);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM ok_academic");
			while (rs.next()) {
				list.add(new ok_academic(
						rs.getInt("id"), 
						rs.getString("branch"), 
						rs.getInt("personal_code"),
						rs.getInt("academic_code"), 
						rs.getInt("academic_date"), 
						rs.getInt("emp_code"),
						rs.getDate("ins_date"), 
						rs.getString("emp_code_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " AND ";
		} else
			return " WHERE ";
	}

	private static List<FilterField> getFilterFields(ok_academicFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getPersonal_code())) {
			flfields.add(new FilterField(getCond(flfields) + "personal_code=?", filter.getPersonal_code()));
		}
		if (!CheckNull.isEmpty(filter.getAcademic_code())) {
			flfields.add(new FilterField(getCond(flfields) + "academic_code=?", filter.getAcademic_code()));
		}
		if (!CheckNull.isEmpty(filter.getAcademic_date())) {
			flfields.add(new FilterField(getCond(flfields) + "academic_date=?", filter.getAcademic_date()));
		}
		if (!CheckNull.isEmpty(filter.getEmp_code())) {
			flfields.add(new FilterField(getCond(flfields) + "emp_code=?", filter.getEmp_code()));
		}
		if (!CheckNull.isEmpty(filter.getIns_date())) {
			flfields.add(new FilterField(getCond(flfields) + "ins_date=?", filter.getIns_date()));
		}
		if (!CheckNull.isEmpty(filter.getEmp_code_name())) {
			flfields.add(new FilterField(getCond(flfields) + "emp_code_name=?", filter.getEmp_code_name()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(ok_academicFilter filter) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM ok_academic ");
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

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<ok_academic> getok_academicsFl(int pageIndex, int pageSize, ok_academicFilter filter) {

		List<ok_academic> list = new ArrayList<ok_academic>();
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
				list.add(new ok_academic(
						rs.getInt("id"), 
						rs.getString("branch"), 
						rs.getInt("personal_code"),
						rs.getInt("academic_code"), 
						rs.getInt("academic_date"), 
						rs.getInt("emp_code"),
						rs.getDate("ins_date"), 
						rs.getString("emp_code_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public ok_academic getok_academic(int ok_academicId) {

		ok_academic ok_academic = new ok_academic();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(un,pwd,alias);
			PreparedStatement ps = c.prepareStatement("SELECT * FROM ok_academic WHERE id=?");
			ps.setInt(1, ok_academicId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ok_academic = new ok_academic();

				ok_academic.setId(rs.getInt("id"));
				ok_academic.setBranch(rs.getString("branch"));
				ok_academic.setPersonal_code(rs.getInt("personal_code"));
				ok_academic.setAcademic_code(rs.getInt("academic_code"));
				ok_academic.setAcademic_date(rs.getInt("academic_date"));
				ok_academic.setEmp_code(rs.getInt("emp_code"));
				ok_academic.setIns_date(rs.getDate("ins_date"));
				ok_academic.setEmp_code_name(rs.getString("emp_code_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return ok_academic;
	}

	public static ok_academic create(ok_academic ok_academic) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_OK_ACADEMIC.NEXTVAL FROM dual");
			ResultSet rs = ps.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = (rs.getInt("NEXTVAL"));
			}
			
			
			ps = c.prepareStatement(
					"INSERT INTO ok_academic (id, branch, personal_code, academic_code, academic_date, emp_code, ins_date, emp_code_name) VALUES (?,?,?,?,?,?,?,?)");

			ps.setDouble(1, id);
			ps.setString(2, ok_academic.getBranch());
			ps.setDouble(3, ok_academic.getPersonal_code());
			ps.setDouble(4, ok_academic.getAcademic_code());
			ps.setDouble(5, ok_academic.getAcademic_date());
			ps.setDouble(6, ok_academic.getEmp_code());
			ps.setTimestamp(7, new java.sql.Timestamp(ok_academic.getIns_date().getTime()));
			ps.setString(8, ok_academic.getEmp_code_name());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return ok_academic;
	}

	public static void update(ok_academic ok_academic) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(
					"UPDATE ok_academic SET id=?, branch=?, personal_code=?, academic_code=?, academic_date=?, emp_code=?, ins_date=?, emp_code_name=?,  WHERE id=?");

			ps.setDouble(1, ok_academic.getId());
			ps.setString(2, ok_academic.getBranch());
			ps.setDouble(3, ok_academic.getPersonal_code());
			ps.setDouble(4, ok_academic.getAcademic_code());
			ps.setDouble(5, ok_academic.getAcademic_date());
			ps.setDouble(6, ok_academic.getEmp_code());
			ps.setTimestamp(7, new java.sql.Timestamp(ok_academic.getIns_date().getTime()));
			ps.setString(8, ok_academic.getEmp_code_name());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static void remove(ok_academic ok_academic) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("DELETE FROM ok_academic WHERE id=?");
			ps.setDouble(1, ok_academic.getId());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}

}
