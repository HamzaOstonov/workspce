package com.is.account.spec;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class SpecAccDao implements Dao<SpecAcc> {
    private Logger logger = Logger.getLogger(SpecAccDao.class);
	private String alias;
	private String username;
	private String pwd;
	private SpecAccFilter filter;
	private int count;
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="select /*+ ordered index(a xpk_specialacc) */ * from specialacc";
							//"a.*, b.name,b.acc_bal,b.currency,b.id_order from specialacc a, account b"
    
    private SpecAccDao(String alias) {
    	this.alias = alias;
    }

    private SpecAccDao(String alias, String username, String pwd) {
    	this.alias = alias;
    	this.username = username;
    	this.pwd = pwd;
    }
    
    public static SpecAccDao getInstance(String alias) {
    	return new SpecAccDao(alias);
    }

    public static SpecAccDao getInstance(String alias, String username, String pwd) {
    	return new SpecAccDao(alias, username, pwd);
    }

	@Override
	public List<FilterField> getFilterFields() {
		List<FilterField> flfields = new ArrayList<FilterField>();


        if(!CheckNull.isEmpty(filter.getBranch())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "branch=?",filter.getBranch()));
        }
        if(!CheckNull.isEmpty(filter.getId_special())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "id_special=?",filter.getId_special()));
        }
        if(!CheckNull.isEmpty(filter.getAcc())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "acc=?",filter.getAcc()));
        }
        if(!CheckNull.isEmpty(filter.getCode_currency())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "code_currency=?",filter.getCode_currency()));
        }
        if(!CheckNull.isEmpty(filter.getBranch_slave())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "branch_slave=?",filter.getBranch_slave()));
        }
        if(filter.getSum_acc() != null){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "sum_acc=?",filter.getSum_acc()));
        }
        if(!CheckNull.isEmpty(filter.getPrim())){
                flfields.add(new FilterField(DbUtils.getCond(flfields)+ "prim=?",filter.getPrim()));
        }

        flfields.add(new FilterField(DbUtils.getCond(flfields)+ "rownum<?",1001));

          return flfields;
	}

	@Override
	public int getCount() {
		return count;
	}
	
	private void getCountWithConnection(Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
        List<FilterField> flFields =getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM specialacc ");
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
//                sql.append(" and a.branch = b.branch and a.acc = b.id ");
        }
        try {
                ps = c.prepareStatement(sql.toString());

                    for(int k=0;k<flFields.size();k++){
                    ps.setObject(k+1, flFields.get(k).getColobject());
                    }
                    rs = ps.executeQuery();

                if (rs.next()) {
                    count = rs.getInt(1);
                }
        } catch (SQLException e) {
       	 ISLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();

        } finally {
        	DbUtils.closeResultSet(rs);
        	DbUtils.closeStmt(ps);
        }
	}
	
	@Override
	public List<SpecAcc> getList() {
		return null;
	}
	
	@Override
	public List<SpecAcc> getListWithPaging(int pageIndex, int pageSize) {
		List<SpecAcc> list = new ArrayList<SpecAcc>();
        Connection c = null;
        int v_lowerbound = pageIndex + 1;
        int v_upperbound = v_lowerbound + pageSize - 1;
        int params;
        List<FilterField> flFields =getFilterFields();

        StringBuffer sql = new StringBuffer();
        sql.append(psql1);
        sql.append(msql);
        if(flFields.size()>0){

                for(int i=0;i<flFields.size();i++){
                        sql.append(flFields.get(i).getSqlwhere());
                }
//                sql.append(" and a.branch = b.branch and a.acc = b.id ");
        }
        sql.append(psql2);


        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement(sql.toString());
                for(params=0;params<flFields.size();params++){
                ps.setObject(params+1, flFields.get(params).getColobject());
                }
                params++;
                ps.setInt(params++,v_upperbound);
                ps.setInt(params++,v_lowerbound);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new SpecAcc(
                                        rs.getString("branch"),
                                        rs.getString("id_special"),
                                        rs.getString("acc"),
                                        rs.getString("code_currency"),
                                        rs.getString("branch_slave"),
                                        rs.getBigDecimal("sum_acc"),
                                        rs.getString("prim"),
                                        "",//rs.getString("name"),
                                        "",//rs.getString("acc_bal"),
                                        "",//rs.getString("currency"),
                                        ""));//rs.getString("id_order")
                }
                getCountWithConnection(c);
        } catch (SQLException e) {
       	 ISLogger.getLogger().error(CheckNull.getPstr(e));
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return list;
	}

	@Override
	public SpecAcc getItemByLongId(String branch,long itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpecAcc getItemByStringId(String branch,String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpecAcc create(SpecAcc specacc) throws Exception {
		/*Connection c = null;
        PreparedStatement ps = null;
        try {
                c = ConnectionPool.getConnection(alias);
                ps = c.prepareStatement("INSERT INTO specialacc (branch, id_special, acc, code_currency, branch_slave, sum_acc, prim ) VALUES (?,?,?,?,?,?,?)");
                
                ps.setString(1,specacc.getBranch());
                ps.setString(2,specacc.getId_special());
                ps.setString(3,specacc.getAcc());
                ps.setString(4,specacc.getCode_currency());
                ps.setString(5,specacc.getBranch_slave());
                ps.setBigDecimal(6,specacc.getSum_acc());
                ps.setString(7,specacc.getPrim());
                ps.executeUpdate();
                c.commit();
        } catch (Exception e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }*/
		doAction(specacc, 1);
        return specacc;
	}

	@Override
	public int update(SpecAcc specacc) {
		Connection c = null;
		int count = 0;
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("UPDATE specialacc SET branch=?, id_special=?, acc=?, code_currency=?, branch_slave=?, sum_acc=?, prim=?  WHERE id=?");
                
                ps.setString(1,specacc.getBranch());
                ps.setString(2,specacc.getId_special());
                ps.setString(3,specacc.getAcc());
                ps.setString(4,specacc.getCode_currency());
                ps.setString(5,specacc.getBranch_slave());
                ps.setBigDecimal(6,specacc.getSum_acc());
                ps.setString(7,specacc.getPrim());
                count = ps.executeUpdate();
                c.commit();
        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
                ConnectionPool.close(c);
        }
        return count;
	}

	@Override
	public int remove(SpecAcc item) throws Exception {
		/*		 Connection c = null;
		 int count = 0;
         try {
                 c = ConnectionPool.getConnection(alias);
                 PreparedStatement ps = c.prepareStatement("DELETE FROM specialacc WHERE id=?");
                 ps.setString(1, item.getId_special());
                 count = ps.executeUpdate();
                 c.commit();
         } catch (Exception e) {
                 e.printStackTrace();
         } finally {
                 ConnectionPool.close(c);
         }
         return count;
		 */
		doAction(item, 2);
		return 0;
	}
	
	public void doAction(SpecAcc item, int action) throws Exception {

	    Connection c = null;
	    CallableStatement callableStatement = null;
	    CallableStatement params = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    try {
	        //c = ConnectionPool.getConnection(alias);
	        c=ConnectionPool.getConnection(username, pwd, alias);
	        
	        //callableStatement = c.prepareCall("{call info.init()}");
	        //callableStatement.execute();

	        callableStatement = c.prepareCall("{call kernel.doAction(?,?,?)}");
	        params = c.prepareCall("{call param.clearparam()}");
	        params.execute();

	        params = c.prepareCall("{call param.setParam(?,?)}");

            params.setString(1, "BRANCH");
            params.setString(2, item.getBranch());
            params.execute();

            params.setString(1, "ACC");
            params.setString(2, item.getAcc());
            params.execute();

            params.setString(1, "ID_SPECIAL");
            params.setString(2, item.getId_special());
            params.execute();

            params.setString(1, "CODE_CURRENCY");
            params.setString(2, item.getCode_currency());
            params.execute();

            params.setString(1, "BRANCH_SLAVE");
            params.setString(2, item.getBranch_slave());
            params.execute();
            
            params.setString(1,"SUM_ACC");
            params.setBigDecimal(2, item.getSum_acc());
            params.execute();

            params.setString(1, "PRIM");
            params.setString(2, item.getPrim());
            params.execute();

            callableStatement.setInt(1, 217);
            callableStatement.setInt(2, 1);
            callableStatement.setInt(3, action);

            callableStatement.execute();
            c.commit();

	    } catch (Exception e) {
	        logger.error(CheckNull.getPstr(e));
	        throw e;
	    } finally {
	        DbUtils.closeStmt(params);
	        DbUtils.closeStmt(callableStatement);
	        ConnectionPool.close(c);
	    }

	}	
	@Override
	public <T1 extends SpecAcc> void setFilter(T1 filter) {
		this.filter = (SpecAccFilter)filter;
	}
	@Override
	public SpecAcc create(Connection c, SpecAcc item)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(Connection c, SpecAcc item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int remove(Connection c, SpecAcc item) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public SpecAcc getItemByLongId(Connection c,String branch, long itemId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SpecAcc getItemByStringId(Connection c, String branch,String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
