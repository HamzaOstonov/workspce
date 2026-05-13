package com.is.bpri.bprspecialfrm;

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

public class Bpr_specialfrmService {
	
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM bpr_specialfrm ";
    private static List<RefData> Anket19 = null;
    private static List<RefData> Anket36 = null;

    private static String getCond(List<FilterField> flfields){
    	if(flfields.size()>0){
    		return " and ";
    	}else
        return " where ";
    }

    private static List<FilterField> getFilterFields(Bpr_specialfrmFilter filter){
    	List<FilterField> flfields = new ArrayList<FilterField>();
    	if(!CheckNull.isEmpty(filter.getBpr_id())){
    		flfields.add(new FilterField(getCond(flfields)+ "bpr_id=?",filter.getBpr_id()));
    	}
        if(!CheckNull.isEmpty(filter.getBpr_spec())){
        	flfields.add(new FilterField(getCond(flfields)+ "bpr_spec=?",filter.getBpr_spec()));
        }
        if(!CheckNull.isEmpty(filter.getBpr_spec_value())){
        	flfields.add(new FilterField(getCond(flfields)+ "bpr_spec_value=?",filter.getBpr_spec_value()));
        }
        flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
        return flfields;
    }


    public static int getCount(Bpr_specialfrmFilter filter)  {
    	Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM bpr_specialfrm ");
        if(flFields.size()>0){
        	for(int i=0;i<flFields.size();i++){
        		sql.append(flFields.get(i).getSqlwhere());
        	}
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	c = ConnectionPool.getConnection();
            ps = c.prepareStatement(sql.toString());
            for(int k=0;k<flFields.size();k++){
            	ps.setObject(k+1, flFields.get(k).getColobject());
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



    public static List<Bpr_specialfrm> getbpr_specialfrmsFl(int pageIndex, int pageSize, Bpr_specialfrmFilter filter)  {
    	List<Bpr_specialfrm> list = new ArrayList<Bpr_specialfrm>();
        Connection c = null;
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
        PreparedStatement ps = null;
        ResultSet rs = null;
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
            		list.add(new Bpr_specialfrm(
            				rs.getInt("id"),
                            	rs.getInt("bpr_id"),
                                	rs.getString("bpr_spec").trim(),
                                    	rs.getString("bpr_spec_value").trim()));
            	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	Utils.close(rs);
        	Utils.close(ps);
        	ConnectionPool.close(c);
        }
        return list;
    }


    public Bpr_specialfrm getbpr_specialfrm(int bpr_specialfrmId) {
    	Bpr_specialfrm bpr_specialfrm = new Bpr_specialfrm();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	c = ConnectionPool.getConnection();
        	ps = c.prepareStatement("SELECT * FROM bpr_specialfrm WHERE bpr_specialfrm_id=?");
        	ps.setInt(1, bpr_specialfrmId);
        	rs = ps.executeQuery();
        	if (rs.next()) {
        		bpr_specialfrm = new Bpr_specialfrm();
        		bpr_specialfrm.setId(rs.getInt("id"));
        		bpr_specialfrm.setBpr_id(rs.getInt("bpr_id"));
        		bpr_specialfrm.setBpr_spec(rs.getString("bpr_spec"));
        		bpr_specialfrm.setBpr_spec_value(rs.getString("bpr_spec_value"));
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	Utils.close(rs);
        	Utils.close(ps);
        	ConnectionPool.close(c);
        }
        return bpr_specialfrm;
    }
    
    public static List<RefData> getAnketNum(int bpr_type,String alias){
    	String sql = "";
    	if(bpr_type==1||bpr_type==4||bpr_type==5){
    		sql = "select ss_specialfrm.id data,ss_specialfrm.id||' - '||ss_specialfrm.name label from ss_specialfrm where ss_specialfrm.id in(19,36,115) order by id";
    	} else {
    		sql = "select ss_specialfrm.id data,ss_specialfrm.id||' - '||ss_specialfrm.name label from ss_specialfrm where ss_specialfrm.id in(19,36) order by id";
    	}
    	return Utils.getRefData(sql, alias);
    }
    
    public static List<RefData> getAnket19(String alias){
    	if(Anket19==null){
    		Anket19 = Utils.getRefData("select SS_PLD_CRED_DEP.ID data,SS_PLD_CRED_DEP.ID||' - '||SS_PLD_CRED_DEP.NAME_RUS label from SS_PLD_CRED_DEP order by data", alias);
    	}
    	return Anket19;
    }
    
    public static List<RefData> getAnket36(String alias){
    	if(Anket36==null){
    		Anket36 = Utils.getRefData("select SS_TYPE_ANS.CODE data,SS_TYPE_ANS.CODE||' - '||SS_TYPE_ANS.NAME label from SS_TYPE_ANS order by data", alias);
    	}
    	return  Anket36;
    }

    public static Bpr_specialfrm create(Bpr_specialfrm bpr_specialfrm,Res res)  {
    	Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	c = ConnectionPool.getConnection();
            ps = c.prepareStatement("SELECT SEQ_bpr_specialfrm.NEXTVAL id FROM DUAL");
            rs = ps.executeQuery();
            if (rs.next()) {
            	bpr_specialfrm.setId(rs.getInt("id"));
            }
            ps = c.prepareStatement("INSERT INTO bpr_specialfrm (id,bpr_id, bpr_spec, bpr_spec_value) VALUES (?,?,?,?)");
            ps.setInt(1, bpr_specialfrm.getId());
            ps.setInt(2,bpr_specialfrm.getBpr_id());
            ps.setString(3,bpr_specialfrm.getBpr_spec().trim());
            ps.setString(4,bpr_specialfrm.getBpr_spec_value().trim());
            ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            res.setCode(1);
            res.setName(CheckNull.getPstr(e));
            ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
        	Utils.close(rs);
        	Utils.close(ps);
        	ConnectionPool.close(c);
        }
        return bpr_specialfrm;
    }

    public static void update(Bpr_specialfrm bpr_specialfrm,Res res)  {
    	Connection c = null;
    	PreparedStatement ps = null;
        try {
        	c = ConnectionPool.getConnection();
            ps = c.prepareStatement("UPDATE bpr_specialfrm SET bpr_id=?, bpr_spec=?, bpr_spec_value=?  WHERE id=?");
            ps.setInt(1,bpr_specialfrm.getBpr_id());
            ps.setString(2,bpr_specialfrm.getBpr_spec().trim());
            ps.setString(3,bpr_specialfrm.getBpr_spec_value().trim());
            ps.setInt(4, bpr_specialfrm.getId());
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

    public static void remove(Bpr_specialfrm bpr_specialfrm,String alias,Res res){
    	Connection c = null;
    	PreparedStatement ps = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("DELETE FROM bpr_specialfrm WHERE bpr_specialfrm.id=?");
            ps.setInt(1, bpr_specialfrm.getId());
            ps.executeUpdate();
            c.commit();
    	} catch (Exception e) {
    		res.setCode(1);
    		res.setName(CheckNull.getPstr(e));
    		e.printStackTrace();
    	} finally {
    		Utils.close(ps);
    		ConnectionPool.close(c);
        }
    }
}
