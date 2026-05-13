package com.is.clients.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.base.utils.Util;
import com.is.clients.models.FounderForInternalControl;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class FounderForInternalControlDao implements Dao<FounderForInternalControl> {
	private static Logger logger = Logger.getLogger(FounderForInternalControlDao.class);
	private String alias;
	
	private FounderForInternalControlDao(String alias) {
		this.alias = alias;
	}
	
	public static FounderForInternalControlDao instance(String alias) {
		return new FounderForInternalControlDao(alias);
	}
	
	@Override
	public <T1 extends FounderForInternalControl> void setFilter(T1 filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FilterField> getFilterFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FounderForInternalControl> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FounderForInternalControl getItem(long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FounderForInternalControl getItem(Connection c, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FounderForInternalControl getItem(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FounderForInternalControl getItem(Connection c, String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FounderForInternalControl create(FounderForInternalControl item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FounderForInternalControl create(Connection c, FounderForInternalControl item) throws Exception {
		PreparedStatement ps = null;
		CallableStatement init = null;
		ResultSet rs = null;
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.executeQuery();
			
			ps = c.prepareStatement("select nvl(max(id),1)+1 id from cl_Add_02_founders where id_cl_Add=? and branch=info.getbranch");
			ps.setLong(1, item.getId_cl_add());
			rs = ps.executeQuery();
			if(rs.next()) {
				item.setId(rs.getInt(1));
			}
			ps.close();
			
			ps = c.prepareStatement("insert into cl_add_02_founders(BRANCH, ID_CL_ADD, TYPE_CLIENT,"+
															        "ID, NAME, ADDRESS,"+//6
															        "PART_OF_CAPITAL, SUM_A, SUM_B,"+//9
															        "DOC_TYPE, DOC_SER, DOC_NUMBER,"+//12
															        "DOC_DATE, DOC_ORGANIZATION, DOC_END_DATE,"+//15
															        "SVIDET, NUM_REESTR, DATE_REGISTR,"+//18
															        "REGISTR_ORGAN, ADRES_ORGAN, INN,"+//21
															        "DIRECTOR_NAME, PHONE, PHONE_WORK,"+//24
															        "PHONE_MOBILE, STR)" +//26
											      "values(info.GetBranch,?,?,"
																      + "?,?,?,"
																      + "?,?,?,"
																      + "?,?,?,"
																      + "?,?,?,"
																      + "?,?,?,"
																      + "?,?,?,"
																      + "?,?,?,"
																      + "?,?)");
			ps.setLong(1, item.getId_cl_add());
			ps.setInt(2, item.getType_client());
			ps.setInt(3, item.getId());
			ps.setString(4, item.getName());
			ps.setString(5, item.getAddress());
			ps.setString(6, item.getPart_of_capital());
			ps.setBigDecimal(7, item.getSum_a());
			ps.setBigDecimal(8, item.getSum_b());
			ps.setString(9, item.getDoc_type());
			ps.setString(10, item.getDoc_ser());
			ps.setString(11, item.getDoc_number());
			ps.setDate(12, Util.sqlDate(item.getDoc_date()));
			ps.setString(13, item.getDoc_organization());
			ps.setDate(14, Util.sqlDate(item.getDoc_end_date()));
			ps.setString(15, item.getSvidet());
			ps.setString(16, item.getNum_reestr());
			ps.setDate(17, Util.sqlDate(item.getDate_registr()));
			ps.setString(18, item.getRegistr_organ());
			ps.setString(19, item.getAdres_organ());
			ps.setString(20, item.getInn());
			ps.setString(21, item.getDirector_name());
			ps.setString(22, item.getPhone());
			ps.setString(23, item.getPhone_work());
			ps.setString(24, item.getPhone_mobile());
			ps.setString(25, item.getStr());
			ps.executeUpdate();
			
			
		
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
			DbUtils.closeResultSet(rs);
		}
		return item;
	}

	@Override
	public int update(FounderForInternalControl item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Connection c, FounderForInternalControl founder) throws Exception {
		PreparedStatement ps = null;
		CallableStatement init = null;
		int count = 0;
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("UPDATE cl_add_02_founders SET type_client=?, name=?, address=?, "
					+ "part_of_capital=?, sum_a=?, sum_b=?, "
					+ "doc_type=?, doc_ser=?, doc_number=?, "
					+ "doc_date=?, doc_organization=?, svidet=?, "
					+ "num_reestr=?, date_registr=?, registr_organ=?, "
					+ "adres_organ=?, inn=?, director_name=?, "
					+ "phone=?, phone_work=?, phone_mobile=?, "
					+ "state=?, str=?, doc_end_date=?  "
					+ "WHERE id_cl_add = ? and branch=info.getBranch and id=?");
            
            ps.setInt(1,founder.getType_client());
            ps.setString(2,founder.getName());
            ps.setString(3,founder.getAddress());
            ps.setString(4,founder.getPart_of_capital());
            ps.setBigDecimal(5,founder.getSum_a());
            ps.setBigDecimal(6,founder.getSum_b());
            ps.setString(7,founder.getDoc_type());
            ps.setString(8,founder.getDoc_ser());
            ps.setString(9,founder.getDoc_number());
            ps.setDate(10, Util.sqlDate(founder.getDoc_date()));
            ps.setString(11,founder.getDoc_organization());
            ps.setString(12,founder.getSvidet());
            ps.setString(13,founder.getNum_reestr());
            ps.setDate(14,Util.sqlDate(founder.getDate_registr()));
            ps.setString(15,founder.getRegistr_organ());
            ps.setString(16,founder.getAdres_organ());
            ps.setString(17,founder.getInn());
            ps.setString(18,founder.getDirector_name());
            ps.setString(19,founder.getPhone());
            ps.setString(20,founder.getPhone_work());
            ps.setString(21,founder.getPhone_mobile());
            ps.setDouble(22,founder.getState());
            ps.setString(23,founder.getStr());
            ps.setDate(24, Util.sqlDate(founder.getDoc_end_date()));
            
            ps.setLong(25, founder.getId_cl_add());
            ps.setInt(26, founder.getId());
            count = ps.executeUpdate();
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		return count;
	}

	@Override
	public int remove(FounderForInternalControl item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Connection c, FounderForInternalControl founder) {
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int count = 0;
		try {
			cs = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			cs.execute();
			ps = c.prepareStatement("delete  from CL_ADD_02_FOUNDERS " +
					"where branch=info.getBranch " +
					"and id_cl_add=? " +
					"and id=?");
			ps.setLong(1, founder.getId_cl_add());
			ps.setLong(2, founder.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(ps);
		}
		
		return count;
	}

}
