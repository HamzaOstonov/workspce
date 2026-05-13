package com.is.clients.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.clients.models.InternalControl;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class InternalControlDao implements Dao<InternalControl>{
	private static Logger logger = Logger.getLogger(InternalControlDao.class);
	
	private static final String LEGAL_ADDINFO = "cl_add_02";
	private static final String IP_ADDINFO = "cl_add_03";
	
	private String alias;
	
	private InternalControlDao(String alias) {
		this.alias =alias;
	}
	
	public static InternalControlDao getInstance(String alias) {
		return new InternalControlDao(alias);
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
	public List<InternalControl> getList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalControl getItem(long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InternalControl getItem(String clientId) {
		InternalControl internalcontrol = null;
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM cl_add_02 WHERE client_id=?");
                ps.setString(1, clientId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        internalcontrol = new InternalControl();
                        
                        internalcontrol.setBranch(rs.getString("branch"));
                        internalcontrol.setId(rs.getLong("id"));
                        internalcontrol.setClient_id(rs.getString("client_id"));
                        internalcontrol.setReg_organization_inform(rs.getString("reg_organization_inform"));
                        internalcontrol.setPhone(rs.getString("phone"));
                        internalcontrol.setEmp_account_open(rs.getInt("emp_account_open"));
                        internalcontrol.setEmp_account_confirm(rs.getInt("emp_account_confirm"));
                        internalcontrol.setRisk_degree(rs.getInt("risk_degree"));
                        internalcontrol.setRisk_degree_detail(rs.getString("risk_degree_detail"));
                        internalcontrol.setIs_landlord(rs.getInt("is_landlord"));
                        internalcontrol.setLandlord(rs.getString("landlord"));
                        internalcontrol.setLandlord_conract(rs.getString("landlord_conract"));
                        internalcontrol.setDate_open(rs.getDate("date_open"));
                        internalcontrol.setDate_change(rs.getDate("date_change"));
                        internalcontrol.setDate_last_save(rs.getDate("date_last_save"));
                        internalcontrol.setState(rs.getInt("state"));
                        internalcontrol.setAccount_open(rs.getString("account_open"));
                        internalcontrol.setCapital_inform(rs.getString("capital_inform"));
                        internalcontrol.setSom_opers(rs.getString("som_opers"));
                        internalcontrol.setPod_opers(rs.getString("pod_opers"));
                        internalcontrol.setAdd_data(rs.getString("add_data"));
                        internalcontrol.setSvidet(rs.getString("svidet"));
                        internalcontrol.setSoft(rs.getString("soft"));
                        internalcontrol.setDate_soft_beg(rs.getDate("date_soft_beg"));
                        internalcontrol.setDate_soft_end(rs.getDate("date_soft_end"));
                        internalcontrol.setCapital_inform_reg(rs.getString("capital_inform_reg"));
                        internalcontrol.setRisk_date(rs.getDate("risk_date"));
                        internalcontrol.setCapital_currency(rs.getString("capital_currency"));
                        internalcontrol.setIs_wrong_address(rs.getInt("is_wrong_address"));
                        internalcontrol.setNal_letter_num(rs.getString("nal_letter_num"));
                        internalcontrol.setNal_letter_date(rs.getDate("nal_letter_date"));
                        internalcontrol.setNumber_worker(rs.getInt("number_worker"));
                        internalcontrol.setSoft_m(rs.getString("soft_m"));
                        internalcontrol.setDate_soft_m_beg(rs.getDate("date_soft_m_beg"));
                        internalcontrol.setDate_soft_m_end(rs.getDate("date_soft_m_end"));
                }
        } catch (Exception e) {
        	logger.error(CheckNull.getPstr(e));
                e.printStackTrace();
        } finally {
                ConnectionPool.close(c);
        }
        return internalcontrol;
	}

	@Override
	public InternalControl create(InternalControl internalcontrol) {
		Connection c = null;
        CallableStatement param = null;
        CallableStatement proc = null;
        CallableStatement getParam = null;
        try {
                c = ConnectionPool.getConnection(alias);
                param = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
                param.execute();
                proc = c.prepareCall("{ call proc_client_add.clientAdd_02() }");
                param = c.prepareCall(SqlScripts.SET_PARAM.getSql());
                getParam = c.prepareCall(SqlScripts.GET_PARAM_ID.getSql());
                getParam.registerOutParameter(1, java.sql.Types.NUMERIC);
                
                param.setString(1, "P_ACT"); param.setString(2, "1"); param.execute();
                
                param.setString(1, "P_CLIENT_ID"); param.setString(2, internalcontrol.getClient_id()); param.execute();
                
                proc.execute();
                
                getParam.execute();
                
                internalcontrol.setId(getParam.getLong(1));
               c.commit();
        } catch (Exception e) {
        	logger.error(CheckNull.getPstr(e));
            e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return internalcontrol;
	}

	@Override
	public int update(InternalControl item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(InternalControl item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T1 extends InternalControl> void setFilter(T1 filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InternalControl create(Connection c, InternalControl internalcontrol)
			throws Exception {
        CallableStatement param = null;
        CallableStatement proc = null;
        CallableStatement getParam = null;
        try {
                param = c.prepareCall(SqlScripts.CLEAR_PARAM.getSql());
                param.execute();
                proc = c.prepareCall("{ call proc_client_add.clientAdd_02() }");
                param = c.prepareCall(SqlScripts.SET_PARAM.getSql());
                getParam = c.prepareCall(SqlScripts.GET_PARAM_ID.getSql());
                getParam.registerOutParameter(1, java.sql.Types.NUMERIC);
                
                param.setString(1, "P_ACT"); param.setString(2, "1"); param.execute();
                
                param.setString(1, "P_CLIENT_ID"); param.setString(2, internalcontrol.getClient_id()); param.execute();
                
                proc.execute();
                
                getParam.execute();
                
                internalcontrol.setId(getParam.getLong(1));
               c.commit();
        } catch (Exception e) {
        	logger.error(CheckNull.getPstr(e));
            e.printStackTrace();

        } finally {
        	DbUtils.closeStmt(getParam);
        	DbUtils.closeStmt(proc);
        	DbUtils.closeStmt(param);
        }
        return internalcontrol;
	}

	@Override
	public int update(Connection c, InternalControl item)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Connection c, InternalControl item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InternalControl getItem(Connection c, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Поиск анкеты внутреннего контроля по коду клмента и филиала
	 * 
	 * @param idClient
	 * @return InternalControl с одним полем - id 
	 * */
	@Override
	public InternalControl getItem(Connection c, String idClient) {
		PreparedStatement ps = null;
		CallableStatement init = null;
		ResultSet rs = null;
		InternalControl control = null;
		String table = null;
		try {
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.executeQuery();
			ps = c.prepareStatement("select code_type from client where id_client=?");
			ps.setString(1, idClient);
			rs = ps.executeQuery();
			if(rs.next()) {
				String codeType = rs.getString(1);
				table = codeType.equals("11") ? IP_ADDINFO : LEGAL_ADDINFO;
			}
			ps.close(); rs.close();
			
			ps = c.prepareStatement("select id from "+table+" where client_id=? and branch=info.getbranch");
			ps.setString(1, idClient);
			rs = ps.executeQuery();
			if(rs.next()) {
				control = new InternalControl(rs.getLong(1));
			}
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeStmt(init);
			DbUtils.closeStmt(ps);
		}
		
		return control;
	}
	
}
