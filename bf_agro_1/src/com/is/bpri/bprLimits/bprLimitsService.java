package com.is.bpri.bprLimits;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class bprLimitsService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select  bl.id, bl.bproduct_id, bl.date_limit, bl.summ_limit,bl.id_state from bpr_limits bl, bproduct b where bl.bproduct_id = b.id";
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private static DecimalFormat decf = new DecimalFormat("###,###,###.00");
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " and ";
	}

	private static List<FilterField> getFilterFields(bprLimitsFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBproduct_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bproduct_id=?",
					filter.getBproduct_id()));
		}
		if (!CheckNull.isEmpty(filter.getDate_limit())) {
			flfields.add(new FilterField(getCond(flfields) + "date_limit=?",
					filter.getDate_limit()));
		}
		if (!CheckNull.isEmpty(filter.getSumm_limit())) {
			flfields.add(new FilterField(getCond(flfields) + "summ_limit=?",
					filter.getSumm_limit()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<? order by date_limit", 1001));

		return flfields;
	}

	public static int getCount(bprLimitsFilter filter) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct from bpr_limits bl, bproduct b where bl.bproduct_id = b.id ");
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

	public static List<bprLimits> getbprLimitssFl(int pageIndex, int pageSize,
			bprLimitsFilter filter) {

		List<bprLimits> list = new ArrayList<bprLimits>();
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
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new bprLimits(
						rs.getInt("id"),
							rs.getInt("bproduct_id"),
							rs.getDate("date_limit")!=null?sdf.format(new Date(rs.getDate("date_limit").getTime())):"",
									decf.format(rs.getDouble("summ_limit")/100),
										rs.getString("id_state")));
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
	
	protected static void create(bprLimits current,int uid,String name,String client,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer id = null;
		Integer id_us = null;
		Date date = null;
		CallableStatement cs = null;
		Statement st = null;
		try {
			
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if(rs.next()){
				date = rs.getDate(1);
			}
			Long curr_time = Math.round(sdf.parse(current.getDate_limit()).getTime()/1000d/3600d/24d);
			Long info_time = Math.round(date.getTime()/1000d/3600d/24d);
			if(curr_time < info_time){
				res.setName("Дата лимита не может быть меньше даты текущего опер дня \""+sdf.format(date)+"\"");
				throw new Exception("Дата лимита не может быть меньше даты текущего опер. дня \""+sdf.format(date)+"\"");
			}
			Utils.close(rs);
			if(Long.parseLong(current.getSumm_limit())==0){
				ps = c.prepareStatement("delete from bpr_limits where bproduct_id = ? and date_limit > ?");
				ps.setInt(1,current.getBproduct_id());
				ps.setDate(2, new java.sql.Date(sdf.parse(current.getDate_limit()).getTime()));
				ps.execute();
				Utils.close(ps);
			}
			ps = c.prepareStatement("SELECT SEQ_bpr_limits.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			ps = c.prepareStatement("insert into bpr_limits (id,bproduct_id,date_limit,summ_limit,id_state) values (?,?,?,?,0)");
			ps.setInt(1, id);
			ps.setInt(2, current.getBproduct_id());
			ps.setDate(3, new java.sql.Date(sdf.parse(current.getDate_limit()).getTime()));
			ps.setLong(4, Long.parseLong(current.getSumm_limit())*100);
			ps.execute();
			Utils.close(ps);
			ps = c.prepareStatement("SELECT SEQ_BPR_USER_ACTIONS_LOG.NEXTVAL bpr_id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()){
				id_us = rs.getInt(1);
			}
			ps = c.prepareStatement("insert into BPR_USER_ACTIONS_LOG (id,USER_ID,USER_NAME,ACTION_DATE,ACT_TYPE,change_id,client_id) values (?,?,?,?,?,?,?)");
			ps.setInt(1, id_us);
			ps.setInt(2, uid);
			ps.setString(3, name);
			ps.setDate(4, date==null?null:new java.sql.Date(date.getTime()));
			ps.setInt(5, 1);
			ps.setString(6, id+"");
			ps.setString(7, client);
			ps.execute();
			c.commit();
//			c.rollback();
		} catch (Exception e) {
			res.setCode(1);
			if(res.getName()==null || res.getName().equals("")) res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			Utils.rollback(c);
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(st);
			ConnectionPool.close(c);
		}
	}

	protected static void update(bprLimits bprlimits,int uid,String name,String client,String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		Statement st = null;
		ResultSet rs = null;
		Date date = new Date();
		Integer id = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if(rs.next()){
				date = rs.getDate(1);
			}
			ps = c.prepareStatement(" UPDATE bpr_limits SET date_limit=?, summ_limit=? WHERE id=? ");
			ps.setDate(1,new java.sql.Date(sdf.parse(bprlimits.getDate_limit()).getTime()));
			ps.setLong(2, Long.parseLong(decf.parse(bprlimits.getSumm_limit())+"")*100);
			ps.setInt(3, bprlimits.getId());
			ps.executeUpdate();
			ps = c.prepareStatement("SELECT SEQ_BPR_USER_ACTIONS_LOG.NEXTVAL bpr_id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			ps = c.prepareStatement("insert into BPR_USER_ACTIONS_LOG (id,USER_ID,USER_NAME,ACTION_DATE,ACT_TYPE,change_id,client_id) values (?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2, uid);
			ps.setString(3, name);
			ps.setDate(4, date==null?null:new java.sql.Date(date.getTime()));
			ps.setInt(5, 2);
			ps.setString(6, bprlimits.getId()+"");
			ps.setString(7, client);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Utils.close(ps);
			Utils.close(st);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static void remove(bprLimits bpr,Integer uid,String name,String client,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		Integer id = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		Date date = new Date();
		Statement st = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_limits where id=?");
			ps.setInt(1, bpr.getId());
			ps.execute();
			ps = c.prepareStatement("SELECT SEQ_BPR_USER_ACTIONS_LOG.NEXTVAL bpr_id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
			}
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if(rs.next()){
				date = rs.getDate(1);
			}
			ps = c.prepareStatement("insert into BPR_USER_ACTIONS_LOG (id,USER_ID,USER_NAME,ACTION_DATE,ACT_TYPE,change_id,client_id) values (?,?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setInt(2, uid);
			ps.setString(3, name);
			ps.setDate(4, date==null?null:new java.sql.Date(date.getTime()));
			ps.setInt(5, 3);
			ps.setInt(6, bpr.getId());
			ps.setString(7, client);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(st);
			Utils.close(ps);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static void refresh(int bpr_type,int id,int uid,String name,String client,String alias,Res res){
		Connection c = null;
		String temp = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		Integer id_us = null;
		Date date = null;
		Statement st = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select bproduct_desc.detail_id from bproduct_desc where bproduct_desc.id=? and bproduct_desc.detail_group=10");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				temp = rs.getString(1);
			}
			String procedure = "";
			if(bpr_type==4){
				procedure = "fill_bpr_limits";
			} else if(bpr_type==5){
				procedure = "fill_flimits";
			} else if(bpr_type==1){
				procedure = "bpr_limits_for_1";
				temp = id+"";
			} else {
				res.setCode(1);
				res.setName("Неверный тип продукта для данной функции");
				return;
			}
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			cs = c.prepareCall("{ call brod_reaction."+procedure+"(?) }");
			cs.setString(1, temp);
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if(rs.next()){
				date = rs.getDate(1);
			}
			ps = c.prepareStatement("SELECT SEQ_BPR_USER_ACTIONS_LOG.NEXTVAL bpr_id FROM DUAL");
			rs = ps.executeQuery();
			if(rs.next()){
				id_us = rs.getInt(1);
			}
			ps = c.prepareStatement("insert into BPR_USER_ACTIONS_LOG (id,USER_ID,USER_NAME,ACTION_DATE,ACT_TYPE,change_id,client_id) values (?,?,?,?,?,?,?)");
			ps.setInt(1, id_us);
			ps.setInt(2, uid);
			ps.setString(3, name);
			ps.setDate(4, date==null?null:new java.sql.Date(date.getTime()));
			ps.setInt(5, 5);
			ps.setString(6, id+"");
			ps.setString(7, client);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(cs);
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

}
