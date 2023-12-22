package com.is.bpri;

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

public class BprTypeService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from (select ss_bpr_type.*,ss_bpr_type_state.name from ss_bpr_type,ss_bpr_type_state where ss_bpr_type_state.id=ss_bpr_type.state)";
	static List<RefData> BprName_ = null;
	static List<RefData> Currency_ = null;
	static List<RefData> Deal_id_ = null;
	static List<RefData> BprType_ = null;

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(BprTypeFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getBpr_type())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_type=?",
					filter.getBpr_type()));
		}
		if (filter.getBpr_id()!=null) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_name=?",
					filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getDeal_id())) {
			flfields.add(new FilterField(getCond(flfields) + "deal_id=?",
					filter.getDeal_id()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?",
					filter.getCurrency()));
		}
		if (filter.getProvision()!=null) {
			flfields.add(new FilterField(getCond(flfields) + "provision=?",filter.getProvision()));
		}
		if (filter.getState()!=null) {
			if(filter.getState()!=999){
				flfields.add(new FilterField(getCond(flfields) + "state=?",filter.getState()));
			}
		}
		if (filter.getMfo()!=null) {
			flfields.add(new FilterField(getCond(flfields) + "mfo=?",filter.getMfo()));
		}
		if (filter.getRegion_id()!=null) {
			flfields.add(new FilterField(getCond(flfields) + "region=?",filter.getRegion_id()));
		}
		if(filter.getState()!=null&&filter.getState()==999){
			flfields.add(new FilterField(getCond(flfields) + "rownum<? and state in (0,1) order by bpr_id desc", 1001));
		} else {
			flfields.add(new FilterField(getCond(flfields) + "rownum<? order by bpr_id desc", 1001));
		}
		return flfields;
	}

	public static int getCount(BprTypeFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM ss_bpr_type ");
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

	public static List<BprType> getBprTypesFl(int pageIndex, int pageSize,
			BprTypeFilter filter, String alias) {
		List<BprType> list = new ArrayList<BprType>();
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
				list.add(new BprType(rs.getInt("bpr_type"),
						rs.getInt("bpr_id"),
						rs.getString("bpr_name"),
						rs.getString("deal_id"),
						rs.getString("currency"),
						rs.getString("provision"),
						rs.getString("mfo"),
						rs.getString("region"),
						rs.getInt("state"),
						rs.getString("name"),
						rs.getString("target_clients")));
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

	//*******************************casper
	public static int isEmptyProc(int i){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int isEmtpeBpr_id = 0;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from bpr_scoring_type where bpr_id=?");
			ps.setInt(1, i);
			rs = ps.executeQuery();
			if(rs.next()){
				isEmtpeBpr_id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return isEmtpeBpr_id;
	}
	
	public static void createScoringType(String alias,Integer bpr_id,Integer scoring_type, String scoring_type_code, String scoring_type_name) throws Exception {
		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
		
				ps = c.prepareStatement("insert into bpr_scoring_type (bpr_id,id_scoring) values (?,?)");
				ps.setInt(1, bpr_id);
				ps.setInt(2, scoring_type);
				
				ps.execute();
				c.commit();
			 
		} catch (Exception e) {
			
			ISLogger.getLogger().error("А вот тут ошибка createScoringType");
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void insertScoringType(String alias,Integer bpr_id,Integer scoring_type, String scoring_type_code, String scoring_type_name) throws Exception {
	
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
		
				ps = c.prepareStatement("insert into bpr_scoring_type (bpr_id,id_scoring,scoring_code,scoring_name) values (?,?,?,?)");
				ps.setInt(1, bpr_id);
				ps.setInt(2, scoring_type);
				ps.setString(3, scoring_type_code);
				ps.setString(4, scoring_type_name);
				ps.execute();
				c.commit();
			 
		} catch (Exception e) {
			
			ISLogger.getLogger().error("А вот тут ошибка insertScoringType");
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	
	public static void updateScoringType(String alias,Integer bpr_id,Integer scoring_type,String scoring_type_code, String scoring_type_name) throws Exception {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bpr_scoring_type set id_scoring = ?, scoring_code = ?, scoring_name = ? where bpr_id = ?");
			ps.setInt(1, scoring_type);
			ps.setString(2, scoring_type_code);
			ps.setString(3, scoring_type_name);
			ps.setInt(4, bpr_id);
			ps.execute();
			c.commit();
		}catch (Exception e) {
			
			ISLogger.getLogger().error("А вот тут ошибка updateScoringType");
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	//************************konets casper
	
	public static void create(BprType bprtype, String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		int id = -1;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_ss_bprtype.NEXTVAL bpr_id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				bprtype.setBpr_id(rs.getInt("bpr_id"));
			}
			ps = c.prepareStatement("INSERT INTO ss_bpr_type (bpr_type, bpr_id, bpr_name, deal_id, currency, provision, state,mfo,region,target_clients) VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, bprtype.getBpr_type());
			ps.setInt(2, bprtype.getBpr_id());
			ps.setString(3, bprtype.getName());
			ps.setString(4, bprtype.getDeal_id());
			ps.setString(5, bprtype.getCurrency());
			ps.setString(6, bprtype.getProvision()==null?"":bprtype.getProvision());
			ps.setInt(7, 0);
			ps.setString(8, bprtype.getMfo()==null||bprtype.getMfo().equals("00")?"":bprtype.getMfo());
			ps.setString(9, bprtype.getRegion_id()==null||bprtype.getRegion_id().equals("00")?"":bprtype.getRegion_id());
			ps.setString(10, bprtype.getTarget_clients());
			ps.executeUpdate();
			ps = c.prepareStatement("SELECT SEQ_bpr_specialfrm.NEXTVAL id FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
            	id = rs.getInt("id");
            }
            ps = c.prepareStatement("INSERT INTO bpr_specialfrm (id,bpr_id, bpr_spec, bpr_spec_value) VALUES (?,?,?,?)");
            ps.setInt(1, id);
            ps.setInt(2, bprtype.getBpr_id());
            ps.setInt(3, 114);
            ps.setInt(4, bprtype.getBpr_type());
            ps.execute();
            ps = c.prepareStatement("SELECT SEQ_bpr_specialfrm.NEXTVAL id FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
            	id = rs.getInt("id");
            }
            ps = c.prepareStatement("INSERT INTO bpr_specialfrm (id,bpr_id, bpr_spec, bpr_spec_value) VALUES (?,?,?,?)");
            ps.setInt(1, id);
            ps.setInt(2, bprtype.getBpr_id());
            ps.setInt(3, 116);
            ps.setInt(4, bprtype.getBpr_id());
            ps.execute();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			ISLogger.getLogger().error("А вот тут ошибка");
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static void update(BprType bprtype, String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE ss_bpr_type SET bpr_name=?, deal_id=?, currency=?, provision=?, state=?, bpr_type=?, mfo=?, region=?, target_clients=?  WHERE bpr_id=? ");
			ps.setString(1, bprtype.getName());
			ps.setString(2, bprtype.getDeal_id());
			ps.setString(3, bprtype.getCurrency());
			ps.setString(4, bprtype.getProvision());
			ps.setInt(5, bprtype.getState());
			ps.setInt(6, bprtype.getBpr_type());
			ps.setString(7, bprtype.getMfo()==null||bprtype.getMfo().equals("00")?"":bprtype.getMfo());
			ps.setString(8, bprtype.getRegion_id()==null||bprtype.getRegion_id().equals("00")?"":bprtype.getRegion_id());
			ps.setString(9, bprtype.getTarget_clients());
			ps.setInt(10, bprtype.getBpr_id());
			ps.executeUpdate();
			ps = c.prepareStatement("UPDATE bpr_specialfrm set bpr_specialfrm.bpr_spec_value=? where bpr_specialfrm.bpr_id=? and bpr_specialfrm.bpr_spec=114");
			ps.setInt(1, bprtype.getBpr_type());
			ps.setInt(2, bprtype.getBpr_id());
			ps.execute();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			Utils.rollback(c);
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void remove(int id,String alias,Res res){
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection();
			cs = c.prepareCall("{ call bpr_control.bprtype_del(?) }");
			cs.setInt(1, id);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			if(e.getMessage().contains("ORA-20000: ")){
				char [] chartemp = e.getMessage().toCharArray();
				String str = "";
				for (int i = 11; i < chartemp.length; i++) {
					str += chartemp[i];
				}
				String temp[] = str.split("ORA-06512: ");
				res.setCode(1);
				res.setName(temp[0]);
			} else {
				e.printStackTrace();
				res.setCode(1);
				res.setName(CheckNull.getPstr(e));
			}
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}

	public static List<RefData> getBprName(String branch) {
		if (BprName_ == null) {
			BprName_ = Utils.getRefData("select bpr_id data, bpr_id ||' - '|| bpr_name label from ss_bpr_type order by data ",branch);
		}
		return BprName_;
	}

	public static List<RefData> getCurrency(String branch){ // ---------- Валюты
		if (Currency_ == null) {
			Currency_ = Utils.getRefData("select kod data, kod ||' - '|| namev label from s_val order by data",branch);
		}
		return Currency_;
	}

	public static List<RefData> getDeal_id(String branch){ // ---------- Валюты
		if (Deal_id_ == null) {
			Deal_id_ = Utils.getRefData("select  deal_id data, deal_id ||'-'|| name label from ss_bpr_deal_id_type ",branch);
		}
		return Deal_id_;
	}

	public static List<RefData> getBprType(String branch){ // ---------- Валюты
		return Utils.getRefData("select s.id data, s.id||'-'||s.name label from ss_deal s where s.group_id=193 ",branch);
	}
	
	public static List<RefData> getBprTypeState(String branch){ // ---------- Валюты
		return Utils.getRefData("select ss_bpr_type_state.id data,ss_bpr_type_state.name label from ss_bpr_type_state order by data",branch);
	}
	
	public static void confirmationBpr_type(String bpr_id,String alias,Res res){
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call bpr_control.bpr_confirm(?) }");
			cs.setString(1, bpr_id);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(errorFix(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static void reCreate(String bpr_id,String alias,Res res) throws SQLException{
		Connection c = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call bpr_control.recreate(?) }");
			cs.setString(1, bpr_id);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			c.rollback();
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
	}
	
	protected static List<RefData> getBranchs(String branch){
		return Utils.getRefData("select '00' bank_id, 'ВСЕ ФИЛИАЛЫ' bank_id from dual union all select bank_id,bank_id from s_mfo where bank_type='"+getBank_type(branch)+"' and act='A'", "");
	}
	
	protected static List<RefData> getRegions(){
		return Utils.getRefData("select '00' region_id, 'ВСЕ РЕГИОНЫ' region_nam from dual union all select region_id,region_nam from s_region where act='A'", "");
	}
	
	protected static List<RefData> getBranchsFromRegion(String branch,String region_id){
		return Utils.getRefData("select bank_id,bank_id from s_mfo where bank_type='"+getBank_type(branch)+"' and act='A' and region_id='"+region_id+"'", "");
	}
	
	protected static List<RefData> getTarget_clients(){
		return Utils.getRefData("select 'P' id,'Для физ. лиц' from dual union all select 'J' id,'Для юр. лиц' from dual", "");
	}
	
	protected static String getRegionName(String region_id){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String region_name = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select region_nam from s_region where region_id=?");
			ps.setString(1, region_id);
			rs = ps.executeQuery();
			if(rs.next()){
				region_name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return region_name;
	}
	
	public static String getBank_type(String branch){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bank_type = "";
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select bank_type from s_mfo where bank_id=?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				bank_type = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return bank_type;
	}
	
	private static String errorFix(Exception e){
		String temp = e.getMessage();
		if(temp.contains("ORA-20000: ")){
			char[] tempchar = temp.toCharArray();
			temp = "";
			for (int i = 10; i < tempchar.length; i++) {
				temp+= tempchar[i];
			}
			temp = temp.split("ORA")[0];
		} else {
			temp = CheckNull.getPstr(e);
		}
		return temp;
	}
}