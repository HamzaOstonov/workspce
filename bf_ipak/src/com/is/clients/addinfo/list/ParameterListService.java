package com.is.clients.addinfo.list;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.clients.addinfo.Parameter;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

import org.apache.log4j.Logger;

public class ParameterListService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT lt.*, nvl(lt.param_visible, 1) param_visibility FROM CLIENT_ADDINFO_LIST_TEMPLATE lt ";
	private static Logger log = ISLogger.getLogger();

	public static List<ParameterList> getParameterlists(Parameter p)  {
		List<ParameterList> list = new ArrayList<ParameterList>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"SELECT * FROM (" +
						"SELECT tmpl.*, nvl(tmpl.param_visible, 1) param_visibility, lis.branch, lis.client_id, lis.param_value, lis.state " +
						"FROM " +
							"(SELECT t.*, ll.list_id FROM CLIENT_ADDINFO_LIST_TEMPLATE t, " +
								"(SELECT distinct l.list_id FROM CLIENT_ADDINFO_LIST l " +
									"where l.client_type = ? " +
									  "and l.branch = ? " +
									  "and l.client_id = ? " +
									  "and l.param_id = ? " +
									  "and l.state = 1) ll " +
								"where t.client_type = ? and t.param_id = ?) tmpl, " +
							"(SELECT * FROM CLIENT_ADDINFO_LIST l " +
								"where l.client_type = ? " +
								  "and l.branch = ? " +
								  "and l.client_id = ? " +
								  "and l.param_id = ? " +
								  "and l.state = 1) lis  " +
						"WHERE lis.client_type(+) = tmpl.client_type " +
						  "and lis.param_id(+) = tmpl.param_id " +
						  "and lis.list_id(+) = tmpl.list_id " +
						  "and lis.param_teg(+) = tmpl.param_list_teg) " +
						  "order by list_id, param_ord, param_list_teg");
			ps.setString(1, p.getClient_type());
			ps.setString(2, p.getBranch());
			ps.setString(3, p.getClient_id());
			ps.setString(4, p.getParam_id());
			ps.setString(5, p.getClient_type());
			ps.setString(6, p.getParam_id());
			ps.setString(7, p.getClient_type());
			ps.setString(8, p.getBranch());
			ps.setString(9, p.getClient_id());
			ps.setString(10, p.getParam_id());
			rs = ps.executeQuery();
			ParameterList pl = null;
			Boolean hasrows = false;
			while (rs.next()) {
				hasrows = true;
				Long listid = rs.getLong("list_id");
				String client_type = rs.getString("client_type");
				String param_id = rs.getString("param_id");
				//ISLogger.getLogger().warn("---------- listid="+listid+" "+client_type+" "+param_id+" "+rs.getInt("state")+" "+rs.getString("param_list_teg")+" "+rs.getString("param_value")); 
				//ISLogger.getLogger().warn("----------IF "+((pl != null)?pl.getList_id():"-null-")+" "+((pl != null)?((pl.getList_id() != listid)+" "+pl.getList_id().equals(listid)+" "+(pl.getList_id() == listid)):"-null-")+" "+listid);
				if (pl == null || !pl.getList_id().equals(listid)) {
					if (pl != null) {
						//ISLogger.getLogger().warn("----------ADD TO LIST "+pl.getList_id());
						list.add(pl);
					}
					//ISLogger.getLogger().warn("----------NEW PL");
					pl = new ParameterList(
							client_type, 
							rs.getString("branch"), 
							rs.getString("client_id"), 
							param_id, 
							listid, 
							rs.getInt("state"));
				}
				
				pl.addParameters(new ParameterListArray(
						client_type,
						param_id,
						rs.getString("param_list_teg"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getInt("param_mandatory"),
						rs.getInt("param_align"),
						rs.getString("param_constraints"),
						rs.getString("param_enable"),
						rs.getInt("param_visibility"),
						rs.getInt("param_visible_t"),
						rs.getString("param_actions"),
						rs.getInt("param_act_runatstart"), 
						rs.getString("param_value"),
						rs.getInt("state")));
				
			}
			if (hasrows) {
				//ISLogger.getLogger().warn("----------ADD TO LIST "+pl.getList_id());
				list.add(pl);
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	/*
	public static List<ParameterList> getParameterlist()  {
		List<ParameterList> list = new ArrayList<ParameterList>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM (SELECT tmpl.*, lis.branch, lis.client_id, lis.param_value, lis.state FROM (SELECT t.*, ll.list_id FROM CLIENT_ADDINFO_LIST_TEMPLATE t, (SELECT distinct l.list_id FROM CLIENT_ADDINFO_LIST l where l.client_type = 'J' and l.branch = '01066' and l.client_id='04715478' and l.param_id = '1' and l.state = 1) ll where t.client_type = 'J' and t.param_id = '1') tmpl, (SELECT * FROM CLIENT_ADDINFO_LIST l where l.client_type = 'J' and l.branch = '01066' and l.client_id='04715478' and l.param_id = '1' and l.state = 1) lis  where lis.client_type(+) = tmpl.client_type and lis.param_id(+) = tmpl.param_id and lis.list_id(+) = tmpl.list_id and lis.param_teg(+) = tmpl.param_list_teg)");
			while (rs.next()) {
				list.add(new ParameterList(
						rs.getString("client_type"),
						rs.getString("param_id"),
						rs.getString("param_list_teg"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getLong("param_mandatory"),
						rs.getLong("param_align"),
						rs.getString("param_constraints"),
						rs.getString("param_enable"),
						rs.getString("param_visible"),
						rs.getLong("param_visible_t"),
						rs.getString("param_actions"),
						rs.getLong("param_act_runatstart"),
						rs.getLong("list_id"),
						rs.getString("branch"),
						rs.getString("client_id"),
						rs.getString("param_value"),
						rs.getLong("state")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	*/
	public static List<ParameterListTemplate> getParameterListTemplates(Parameter p)  {
		List<ParameterListTemplate> list = new ArrayList<ParameterListTemplate>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT t.*, nvl(t.param_visible, 1) param_visibility FROM CLIENT_ADDINFO_LIST_TEMPLATE t where t.client_type = ? and t.param_id = ? order by t.param_ord");
			ps.setString(1, p.getClient_type());
			ps.setString(2, p.getParam_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ParameterListTemplate(
						rs.getString("client_type"),
						rs.getString("param_id"),
						rs.getString("param_list_teg"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getInt("param_mandatory"),
						rs.getInt("param_align"),
						rs.getString("param_constraints"),
						rs.getString("param_enable"),
						rs.getInt("param_visibility"),
						rs.getInt("param_visible_t"),
						rs.getString("param_width_t"),
						rs.getString("param_actions"),
						rs.getInt("param_act_runatstart")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<ParameterListTemplate> getParameterListTemplate()  {
		List<ParameterListTemplate> list = new ArrayList<ParameterListTemplate>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			s = c.createStatement();
			rs = s.executeQuery("SELECT t.*, nvl(t.param_visible, 1) param_visibility FROM CLIENT_ADDINFO_LIST_TEMPLATE");
			while (rs.next()) {
				list.add(new ParameterListTemplate(
						rs.getString("client_type"),
						rs.getString("param_id"),
						rs.getString("param_list_teg"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getInt("param_mandatory"),
						rs.getInt("param_align"),
						rs.getString("param_constraints"),
						rs.getString("param_enable"),
						rs.getInt("param_visibility"),
						rs.getInt("param_visible_t"),
						rs.getString("param_width_t"),
						rs.getString("param_actions"),
						rs.getInt("param_act_runatstart")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(s);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static Res doAction(String un,String pw, ParameterListTemplate parameterlisttemplate,int actionid) {
         Res res =null;
         SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
         Connection c = null;
         CallableStatement cs = null;
         CallableStatement acs = null;
         CallableStatement ccs = null;
         try {
        	 c = ConnectionPool.getConnection(un,pw);
        	 cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	 acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	 ccs = c.prepareCall("{ call Param.clearparam() }");
        	 ccs.execute();
        	 ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			if(!CheckNull.isEmpty(parameterlisttemplate.getClient_type())){
				cs.setString(1, "CLIENT_TYPE");  cs.setString(2,parameterlisttemplate.getClient_type()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_id())){
				cs.setString(1, "PARAM_ID");  cs.setString(2,parameterlisttemplate.getParam_id()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_list_teg())){
				cs.setString(1, "PARAM_LIST_TEG");  cs.setString(2,parameterlisttemplate.getParam_list_teg()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_select())){
				cs.setString(1, "PARAM_SELECT");  cs.setString(2,parameterlisttemplate.getParam_select()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_type())){
				cs.setString(1, "PARAM_TYPE");  cs.setString(2,parameterlisttemplate.getParam_type()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_mask())){
				cs.setString(1, "PARAM_MASK");  cs.setString(2,parameterlisttemplate.getParam_mask()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_def_value())){
				cs.setString(1, "PARAM_DEF_VALUE");  cs.setString(2,parameterlisttemplate.getParam_def_value()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_ord())){
				cs.setString(1, "PARAM_ORD");  cs.setLong(2,parameterlisttemplate.getParam_ord()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_mandatory())){
				cs.setString(1, "PARAM_MANDATORY");  cs.setLong(2,parameterlisttemplate.getParam_mandatory()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_align())){
				cs.setString(1, "PARAM_ALIGN");  cs.setLong(2,parameterlisttemplate.getParam_align()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_constraints())){
				cs.setString(1, "PARAM_CONSTRAINTS");  cs.setString(2,parameterlisttemplate.getParam_constraints()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_enable())){
				cs.setString(1, "PARAM_ENABLE");  cs.setString(2,parameterlisttemplate.getParam_enable()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_visible())){
				cs.setString(1, "PARAM_VISIBLE");  cs.setInt(2,parameterlisttemplate.getParam_visible()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_visible_t())){
				cs.setString(1, "PARAM_VISIBLE_T");  cs.setLong(2,parameterlisttemplate.getParam_visible_t()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_actions())){
				cs.setString(1, "PARAM_ACTIONS");  cs.setString(2,parameterlisttemplate.getParam_actions()); cs.execute();
			}
			if(!CheckNull.isEmpty(parameterlisttemplate.getParam_act_runatstart())){
				cs.setString(1, "PARAM_ACT_RUNATSTART");  cs.setLong(2,parameterlisttemplate.getParam_act_runatstart()); cs.execute();
			}

        	 acs.setInt(1, 2);
        	 acs.setInt(2, 2);
        	 acs.setInt(3,actionid);
        	 acs.execute();
        	 c.commit();
        	 ccs.execute();
        	 res = new Res(0,ccs.getString(1));
         } catch (Exception e) {
             res = new Res(-1, e.getMessage());
         } finally {
         	DbUtils.closeStmt(cs);
			 DbUtils.closeStmt(acs);
			 DbUtils.closeStmt(ccs);
        	 ConnectionPool.close(c);
         }
         return res;
    }

	public static String doAction(String un,String pw, String branch, String id,int actionid) {
		String res ="";
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		String cn;
        try {
        	c = ConnectionPool.getConnection(un,pw);
        	cs = c.prepareCall("{ call Param.SetParam(?,?) }");
        	acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
        	ccs = c.prepareCall("{ call Param.clearparam() }");
        	PreparedStatement ps = c.prepareStatement("SELECT * FROM CLIENT_ADDINFO_LIST_TEMPLATE WHERE branch=? and id=?");
        	ps.setString(1, branch);
        	ps.setString(2, id);
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		ccs.execute();
        		for (int i=1;  i<=rs.getMetaData().getColumnCount();i++){
        			cn = rs.getMetaData().getColumnName(i);
        			// System.out.println(cn+"  "+ rs.getMetaData().getColumnTypeName(i));
        			if( rs.getString(cn)!=null){
        				cs.setString(1, cn);
        				if (rs.getMetaData().getColumnTypeName(i).equals("DATE")){
        					cs.setString(2,bdf.format(rs.getDate(cn)));
        				}else{
        					cs.setString(2,rs.getString(cn));
        				}
        				cs.execute();
        			}
        		}
        		acs.setInt(1, 2);
        		acs.setInt(2, 2);
        		acs.setInt(3,actionid);
        		acs.execute();
        		c.commit();
        	}
        } catch (Exception e) {
        	// e.printStackTrace(); log.error(CheckNull.getPstr(e));
        	res = e.getMessage();
        } finally {
			DbUtils.closeStmt(cs);
			DbUtils.closeStmt(acs);
			DbUtils.closeStmt(ccs);
        	ConnectionPool.close(c);
        }
        return res;
	}

	private static String getCond(List<FilterField> flfields){
		if(flfields.size()>0){
			return " and ";
		}else
			return " where ";
	}

	private static List<FilterField> getFilterFields(ParameterListTemplateFilter filter){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(!CheckNull.isEmpty(filter.getClient_type())){
			flfields.add(new FilterField(getCond(flfields)+ "client_type = ?", filter.getClient_type()));
		}
		if(!CheckNull.isEmpty(filter.getParam_id())){
			flfields.add(new FilterField(getCond(flfields)+ "param_id = ?", filter.getParam_id()));
		}
		if(!CheckNull.isEmpty(filter.getParam_list_teg())){
			flfields.add(new FilterField(getCond(flfields)+ "param_list_teg = ?", filter.getParam_list_teg()));
		}
		if(!CheckNull.isEmpty(filter.getParam_select())){
			flfields.add(new FilterField(getCond(flfields)+ "param_select = ?", filter.getParam_select()));
		}
		if(!CheckNull.isEmpty(filter.getParam_type())){
			flfields.add(new FilterField(getCond(flfields)+ "param_type = ?", filter.getParam_type()));
		}
		if(!CheckNull.isEmpty(filter.getParam_mask())){
			flfields.add(new FilterField(getCond(flfields)+ "param_mask = ?", filter.getParam_mask()));
		}
		if(!CheckNull.isEmpty(filter.getParam_def_value())){
			flfields.add(new FilterField(getCond(flfields)+ "param_def_value = ?", filter.getParam_def_value()));
		}
		if(!CheckNull.isEmpty(filter.getParam_ord())){
			flfields.add(new FilterField(getCond(flfields)+ "param_ord = ?", filter.getParam_ord()));
		}
		if(!CheckNull.isEmpty(filter.getParam_mandatory())){
			flfields.add(new FilterField(getCond(flfields)+ "param_mandatory = ?", filter.getParam_mandatory()));
		}
		if(!CheckNull.isEmpty(filter.getParam_align())){
			flfields.add(new FilterField(getCond(flfields)+ "param_align = ?", filter.getParam_align()));
		}
		if(!CheckNull.isEmpty(filter.getParam_constraints())){
			flfields.add(new FilterField(getCond(flfields)+ "param_constraints = ?", filter.getParam_constraints()));
		}
		if(!CheckNull.isEmpty(filter.getParam_enable())){
			flfields.add(new FilterField(getCond(flfields)+ "param_enable = ?", filter.getParam_enable()));
		}
		if(!CheckNull.isEmpty(filter.getParam_visible())){
			flfields.add(new FilterField(getCond(flfields)+ "param_visible = ?", filter.getParam_visible()));
		}
		if(!CheckNull.isEmpty(filter.getParam_visible_t())){
			flfields.add(new FilterField(getCond(flfields)+ "param_visible_t = ?", filter.getParam_visible_t()));
		}
		if(!CheckNull.isEmpty(filter.getParam_actions())){
			flfields.add(new FilterField(getCond(flfields)+ "param_actions = ?", filter.getParam_actions()));
		}
		if(!CheckNull.isEmpty(filter.getParam_act_runatstart())){
			flfields.add(new FilterField(getCond(flfields)+ "param_act_runatstart = ?", filter.getParam_act_runatstart()));
		}

		flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
		return flfields;
	}

	public static int getCount(ParameterListTemplateFilter filter)  {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM CLIENT_ADDINFO_LIST_TEMPLATE ");
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for(int k=0;k<flFields.size();k++){
				ps.setObject(k+1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	public static List<ParameterListTemplate> getParameterListTemplatesFl(int pageIndex, int pageSize, ParameterListTemplateFilter filter)  {
		List<ParameterListTemplate> list = new ArrayList<ParameterListTemplate>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields =getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if(flFields.size()>0){
			for(int i=0;i<flFields.size();i++){
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for(params=0;params<flFields.size();params++){
				ps.setObject(params+1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++,v_upperbound);
			ps.setInt(params++,v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ParameterListTemplate(
						rs.getString("client_type"),
						rs.getString("param_id"),
						rs.getString("param_list_teg"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getInt("param_mandatory"),
						rs.getInt("param_align"),
						rs.getString("param_constraints"),
						rs.getString("param_enable"),
						rs.getInt("param_visibility"),
						rs.getInt("param_visible_t"),
						rs.getString("param_width_t"),
						rs.getString("param_actions"),
						rs.getInt("param_act_runatstart")));
			}
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return list;
	}

	public static ParameterListTemplate getParameterListTemplate(int parameterlisttemplateId) {
		ParameterListTemplate parameterlisttemplate = new ParameterListTemplate();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT t.*, nvl(t.param_visible, 1) param_visibility FROM CLIENT_ADDINFO_LIST_TEMPLATE t WHERE id=?");
			ps.setLong(1, parameterlisttemplateId);
			rs = ps.executeQuery();
			if (rs.next()) {
				parameterlisttemplate = new ParameterListTemplate();
				parameterlisttemplate.setClient_type(rs.getString("client_type"));
				parameterlisttemplate.setParam_id(rs.getString("param_id"));
				parameterlisttemplate.setParam_list_teg(rs.getString("param_list_teg"));
				parameterlisttemplate.setParam_select(rs.getString("param_select"));
				parameterlisttemplate.setParam_type(rs.getString("param_type"));
				parameterlisttemplate.setParam_mask(rs.getString("param_mask"));
				parameterlisttemplate.setParam_def_value(rs.getString("param_def_value"));
				parameterlisttemplate.setParam_ord(rs.getLong("param_ord"));
				parameterlisttemplate.setParam_mandatory(rs.getInt("param_mandatory"));
				parameterlisttemplate.setParam_align(rs.getInt("param_align"));
				parameterlisttemplate.setParam_constraints(rs.getString("param_constraints"));
				parameterlisttemplate.setParam_enable(rs.getString("param_enable"));
				parameterlisttemplate.setParam_visible(rs.getInt("param_visibility"));
				parameterlisttemplate.setParam_visible_t(rs.getInt("param_visible_t"));
				parameterlisttemplate.setParam_actions(rs.getString("param_actions"));
				parameterlisttemplate.setParam_act_runatstart(rs.getInt("param_act_runatstart"));
			}
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return parameterlisttemplate;
	}

	public static ParameterListTemplate create(ParameterListTemplate parameterlisttemplate)  {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT SEQ_CLIENT_ADDINFO_LIST_TEMPLATE.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				parameterlisttemplate.setParam_id(rs.getString("id"));
			}
			ps = c.prepareStatement("INSERT INTO CLIENT_ADDINFO_LIST_TEMPLATE (client_type, param_id, param_list_teg, param_select, param_type, param_mask, param_def_value, param_ord, param_mandatory, param_align, param_constraints, param_enable, param_visible, param_visible_t, param_actions, param_act_runatstart) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, parameterlisttemplate.getClient_type());
			ps.setString(2, parameterlisttemplate.getParam_id());
			ps.setString(3, parameterlisttemplate.getParam_list_teg());
			ps.setString(4, parameterlisttemplate.getParam_select());
			ps.setString(5, parameterlisttemplate.getParam_type());
			ps.setString(6, parameterlisttemplate.getParam_mask());
			ps.setString(7, parameterlisttemplate.getParam_def_value());
			ps.setLong(8, parameterlisttemplate.getParam_ord());
			ps.setLong(9, parameterlisttemplate.getParam_mandatory());
			ps.setLong(10, parameterlisttemplate.getParam_align());
			ps.setString(11, parameterlisttemplate.getParam_constraints());
			ps.setString(12, parameterlisttemplate.getParam_enable());
			ps.setInt(13, parameterlisttemplate.getParam_visible());
			ps.setLong(14, parameterlisttemplate.getParam_visible_t());
			ps.setString(15, parameterlisttemplate.getParam_actions());
			ps.setLong(16, parameterlisttemplate.getParam_act_runatstart());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return parameterlisttemplate;
	}

	public static void update(ParameterListTemplate parameterlisttemplate)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("UPDATE CLIENT_ADDINFO_LIST_TEMPLATE SET client_type = ?, param_id = ?, param_list_teg = ?, param_select = ?, param_type = ?, param_mask = ?, param_def_value = ?, param_ord = ?, param_mandatory = ?, param_align = ?, param_constraints = ?, param_enable = ?, param_visible = ?, param_visible_t = ?, param_actions = ?, param_act_runatstart = ?  WHERE id=?");
			ps.setString(1, parameterlisttemplate.getClient_type());
			ps.setString(2, parameterlisttemplate.getParam_id());
			ps.setString(3, parameterlisttemplate.getParam_list_teg());
			ps.setString(4, parameterlisttemplate.getParam_select());
			ps.setString(5, parameterlisttemplate.getParam_type());
			ps.setString(6, parameterlisttemplate.getParam_mask());
			ps.setString(7, parameterlisttemplate.getParam_def_value());
			ps.setLong(8, parameterlisttemplate.getParam_ord());
			ps.setLong(9, parameterlisttemplate.getParam_mandatory());
			ps.setLong(10, parameterlisttemplate.getParam_align());
			ps.setString(11, parameterlisttemplate.getParam_constraints());
			ps.setString(12, parameterlisttemplate.getParam_enable());
			ps.setInt(13, parameterlisttemplate.getParam_visible());
			ps.setLong(14, parameterlisttemplate.getParam_visible_t());
			ps.setString(15, parameterlisttemplate.getParam_actions());
			ps.setLong(16, parameterlisttemplate.getParam_act_runatstart());

			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
	}

	public static void remove(ParameterListTemplate parameterlisttemplate)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("DELETE FROM CLIENT_ADDINFO_LIST_TEMPLATE WHERE id=?");
			ps.setString(1, parameterlisttemplate.getParam_id());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace(); log.error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
	}

}
