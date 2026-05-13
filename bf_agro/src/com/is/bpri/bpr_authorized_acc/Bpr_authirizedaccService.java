package com.is.bpri.bpr_authorized_acc;

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
import com.is.utils.Res;

public class Bpr_authirizedaccService {
	
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="SELECT * FROM bpr_authorized_acc";

   private static String getCond(List<FilterField> flfields){
    	if(flfields.size()>0){
    		return " and ";
    	}else
        return " where ";
    }

    private static List<FilterField> getFilterFields(Bpr_authirizedaccFilter filter){
    	List<FilterField> flfields = new ArrayList<FilterField>();
    	if(filter.getBpr_id()!=null){
    		flfields.add(new FilterField(getCond(flfields)+ "bpr_id=?",filter.getBpr_id()));
    	}
        if(filter.getBranch()!=null){
        	flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
        }
        if(filter.getAcc()!=null){
        	flfields.add(new FilterField(getCond(flfields)+ "acc=?",filter.getAcc()));
        }
        flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
        return flfields;
    }
    
    protected static String getAccounts(String acc,String branch,String alias){
    	Connection c = null;
    	String returnAcc = "";
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select kernel.fckey(?,?) from dual");
			ps.setString(1, acc);
			ps.setString(2, branch); 
			rs = ps.executeQuery();
			if(rs.next()){
				returnAcc = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return returnAcc;
    }

    public static int getCount(Bpr_authirizedaccFilter filter)  {
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



    public static List<Bpr_authirizedacc> getbpr_specialfrmsFl(int pageIndex, int pageSize, Bpr_authirizedaccFilter filter)  {
    	List<Bpr_authirizedacc> list = new ArrayList<Bpr_authirizedacc>();
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
        sql.append(psql2);
        PreparedStatement ps = null;
        ResultSet rs = null;
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
            		list.add(new Bpr_authirizedacc(
            				rs.getInt("id"),
                            	rs.getInt("bpr_id"),
                                	rs.getString("branch").trim(),
                                    	rs.getString("acc").trim()));
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

    public static Bpr_authirizedacc create(Bpr_authirizedacc bpr_specialfrm,Res res)  {
    	Connection c = null;
        PreparedStatement ps = null;
        try {
        	c = ConnectionPool.getConnection(); 
            ps = c.prepareStatement("SELECT SEQ_BPR_authorized_acc.NEXTVAL id FROM DUAL");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	bpr_specialfrm.setId(rs.getInt("id"));
            }
            ps = c.prepareStatement("INSERT INTO bpr_authorized_acc (id,bpr_id, branch, acc) VALUES (?,?,?,?)");
            ps.setInt(1, bpr_specialfrm.getId());
            ps.setInt(2,bpr_specialfrm.getBpr_id());
            ps.setString(3,bpr_specialfrm.getBranch().trim());
            ps.setString(4,bpr_specialfrm.getAcc().trim());
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
        return bpr_specialfrm;
    }

    public static void update(Bpr_authirizedacc bpr_specialfrm,Res res)  {
    	Connection c = null;
    	PreparedStatement ps = null;
    	try {
        	c = ConnectionPool.getConnection();
            ps = c.prepareStatement("UPDATE bpr_authorized_acc SET bpr_id=?, branch=?, acc=?  WHERE id=?");
            ps.setInt(1,bpr_specialfrm.getBpr_id());
            ps.setString(2,bpr_specialfrm.getBranch().trim());
            ps.setString(3,bpr_specialfrm.getAcc().trim());
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

    public static void remove(Bpr_authirizedacc bpr_specialfrm,String alias,Res res){
    	Connection c = null;
    	PreparedStatement ps = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("DELETE FROM bpr_authorized_acc WHERE id=?");
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
