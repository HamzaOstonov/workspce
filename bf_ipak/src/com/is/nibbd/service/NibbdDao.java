package com.is.nibbd.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.clients.utils.ClientUtil;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.models.NibbdFilter;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

import lombok.Getter;

public class NibbdDao implements Dao<Nibbd> {
    private static final Logger logger = Logger.getLogger(NibbdDao.class);
    
    private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql = "select * from Nibbd ";
    private String alias;
    private String branch;
    private int count;

    @Getter
    private NibbdFilter filter;

    private NibbdDao() {
    }

    private NibbdDao(String alias, String branch) {
        this.alias = alias;
        this.branch = branch;
    }

    public static NibbdDao getInstance(String alias, String branch) {
        return new NibbdDao(alias, branch);
    }

    public static NibbdDao getInstance() {
        return new NibbdDao();
    }

    @Override
    /*public List<FilterField> getFilterFieldsOld() {
        List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getDate_from())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "query_date>=?", new java.sql.Date(filter.getDate_from().getTime())));
        }
        if (!CheckNull.isEmpty(filter.getDate_to())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "query_date<=?", new java.sql.Date(filter.getDate_to().getTime())));
        }
        if (!CheckNull.isEmpty(filter.getId_client())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "id_client = ?", filter.getId_client()));
        }
        if (!CheckNull.isEmpty(filter.getState())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "state=?", filter.getState()));
        }
        if (!CheckNull.isEmpty(filter.getQuery_num())) {
            flfields.add(new FilterField(DbUtils.getCond(flfields) + "query_no=?", filter.getQuery_num()));
        }
        flfields.add(new FilterField(DbUtils.getCond(flfields) + "rownum<?", 1001));
        return flfields;
    }*/
    public List<FilterField> getFilterFields() {
        List<FilterField> flfields = new ArrayList<FilterField>();
        if (!CheckNull.isEmpty(filter.getBranch())) {
            flfields.add(new FilterField("and n.branch=?", filter.getBranch()));
        }
        if (!CheckNull.isEmpty(filter.getDate_from())) {
            flfields.add(new FilterField("and n.query_date>=?", new java.sql.Date(filter.getDate_from().getTime())));
        }
        if (!CheckNull.isEmpty(filter.getDate_to())) {
            flfields.add(new FilterField("and n.query_date<=?", new java.sql.Date(filter.getDate_to().getTime())));
        }
        if (!CheckNull.isEmpty(filter.getId_client())) {
            flfields.add(new FilterField("and (n.id_client = ? or substr(n.parent_id,10,8) = ?)", filter.getId_client()));
            flfields.add(new FilterField("", filter.getId_client()));
        }
        if (!CheckNull.isEmpty(filter.getState())) {
            flfields.add(new FilterField("and n.state=?", filter.getState()));
        }
        if (!CheckNull.isEmpty(filter.getQuery_num())) {
            flfields.add(new FilterField("and n.query_no=?", filter.getQuery_num()));
        }
        flfields.add(new FilterField("and rownum<?", 1001));
        return flfields;
    }

    
    @Override
    public int getCount() {
        return count;
    }

    private void getCountWithConnection(Connection c) {
        PreparedStatement ps = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        List<FilterField> flFields = getFilterFields();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM Nibbd n where n.branch=info.getbranch ");
        if (flFields.size() > 0) {

            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
        }
        try {
            ps = c.prepareStatement(sql.toString());
            cs = c.prepareCall("{call info.init()}");
            cs.execute();
            for (int k = 0; k < flFields.size(); k++) {
                ps.setObject(k + 1, flFields.get(k).getColobject());
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            DbUtils.closeStmt(cs);
            ConnectionPool.close(c);
        }
    }

    @Override
    public List<Nibbd> getList() {
        List<Nibbd> list = new ArrayList<Nibbd>();
        Connection c = null;
        CallableStatement init = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int params;
        List<FilterField> flFields = getFilterFields();

        //StringBuffer sql = new StringBuffer("select * from nibbd ");
        StringBuffer sql = new StringBuffer("select n.*, nvl(c1.name,nvl(c2.name,c3.name)) name from nibbd n, client c1, client c2, client c3 where c1.branch(+)=n.branch and c1.id(+)=n.parent_id "+
        		"and c2.branch(+)=n.branch and c2.id_client(+)=n.id_client "+
        		"and c3.branch(+)=n.branch and c3.id_client(+)=substr(n.parent_id,10,8)");        
        if (flFields.size() > 0) {

            for (int i = 0; i < flFields.size(); i++) {
                sql.append(flFields.get(i).getSqlwhere());
            }
            if (filter.getDate_from() == null && filter.getDate_to() == null) {
                sql.append(" and query_date = info.getday ");
            }
            sql.append(" order by query_no ");
        }

        try {
            if (alias == null) {
                c = ConnectionPool.getConnection();
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            init = c.prepareCall("{call info.init()}");
            init.execute();
            preparedStatement = c.prepareStatement(sql.toString());
            for (params = 0; params < flFields.size(); params++) {
                preparedStatement.setObject(params + 1, flFields.get(params).getColobject());
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Nibbd(
                        resultSet.getString("branch"),
                        resultSet.getLong("str_no"),
                        resultSet.getInt("query_no"),
                        resultSet.getString("reis_no"),
                        resultSet.getString("query_inp"),
                        resultSet.getString("parent_id"),
                        resultSet.getInt("state"),
                        resultSet.getDate("query_date"),
                        resultSet.getString("id_client"),
                        resultSet.getString("query_out"),
                        resultSet.getString("name")));
            }
            getCountWithConnection(c);
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));

        } finally {
            DbUtils.closeStmt(preparedStatement);
            DbUtils.closeStmt(init);
            DbUtils.closeResultSet(resultSet);
            ConnectionPool.close(c);
        }
        return list;
    }

    @Override
    public List<Nibbd> getListWithPaging(int pageIndex, int pageSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Nibbd getItemByLongId(String branch,long itemId) {
        Connection c = null;
        Nibbd nibbd = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (alias == null) {
                c = ConnectionPool.getConnection();
            } else {
                c = ConnectionPool.getConnection(alias);
            }
            ps = c.prepareStatement("select * from nibbd where str_no=?");
            ps.setLong(1, itemId);

            rs = ps.executeQuery();
            if (rs.next()) {
                nibbd = new Nibbd(
                        rs.getString("branch"),
                        rs.getLong("str_no"),
                        rs.getInt("query_no"),
                        rs.getString("reis_no"),
                        rs.getString("query_inp"),
                        rs.getString("parent_id"),
                        rs.getInt("state"),
                        rs.getDate("query_date"),
                        rs.getString("id_client"),
                        rs.getString("query_out"),
                        "");
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return nibbd;
    }

    @Override
    public Nibbd getItemByStringId(String branch,String itemId) {
        return null;
    }

    @Override
    public Nibbd create(Nibbd nibbd) throws Exception {
        Connection c = null;
        PreparedStatement ps = null;
        CallableStatement cs = null;
        try {
            c = ConnectionPool.getConnection(alias);
            cs = c.prepareCall("{call info.init()}");
            cs.execute();

            nibbd.setBranch(branch);
            nibbd.setStr_num(getStrNo(c, branch));
            //nibbd.setReis_num(getReis(c, true));
            nibbd.setReis_num(null);

            ps = c.prepareStatement("insert into nibbd(branch,str_no,query_no," +
                    "query_inp,state,query_date,parent_id,id_client) " +
                    "values(?,?,?,?,1,info.getDay,?,?)");
            ps.setString(1, branch);
            ps.setLong(2, nibbd.getStr_num());
            ps.setInt(3, nibbd.getQuery_num());
            ps.setString(4, nibbd.makeQuery());
            ps.setString(5, nibbd.getParent_id());
            ps.setString(6, nibbd.getId_client());
            ClientUtil.isClientConfirmed(c, nibbd.getId_client());
            ps.executeQuery();
            c.commit();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return nibbd;
    }

    @Override
    public int update(Nibbd item) {
        Connection c = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("update nibbd set state=? where branch = ? and STR_NO = ? and QUERY_NO = ? and QUERY_DATE = ?");
            ps.setInt(1, item.getState());
            ps.setString(2, item.getBranch());
            ps.setLong(3, item.getStr_num());
            ps.setInt(4, item.getQuery_num());
            ps.setDate(5, item.getQuery_date() != null ? new java.sql.Date(item.getQuery_date().getTime()) : null);

            count = ps.executeUpdate();
            c.commit();

        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }

        return count;
    }

    @Override
    public int remove(Nibbd item) {
        Connection c = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            c = ConnectionPool.getConnection(alias);
            ps = c.prepareStatement("delete from nibbd where str_no=? and query_no=? and branch=?");
            ps.setLong(1, item.getStr_num());
            ps.setInt(2, item.getQuery_num());
            ps.setString(3, item.getBranch());

            count = ps.executeUpdate();
            c.commit();

        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }

        return count;
    }

    @Override
    public <T1 extends Nibbd> void setFilter(T1 filter) {
        this.filter = (NibbdFilter) filter;

    }

    private Long getStrNo(Connection c, String infoBranch) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        long res = 0;
        try {
            ps = c.prepareStatement(" select seq_nibbd_str_no_" + infoBranch + ".nextval from dual");
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            DbUtils.closeResultSet(rs);
        }

        return res;
    }

    private String getReis_(Connection c, boolean makeReis) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int reis = 0;
        try {
            if (makeReis) {
                ps = c.prepareStatement("select seq_nibbd_reis_no_" + branch + ".nextval from dual");
                rs = ps.executeQuery();
                if (rs.next()) {
                    reis = rs.getInt(1);
                }
            } else {
                ps = c.prepareStatement("select last_number from all_sequences where " +
                        "sequence_owner = ? and sequence_name = ?");
                ps.setString(1, alias);
                ps.setString(2, "SEQ_NIBBD_REIS_NO_" + branch);
                rs = ps.executeQuery();
                if (rs.next()) {
                    reis = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
        }
        return reisToStr(reis);
    }

    private String reisToStr(int reis) {
        String res = null;
        if (reis != 100) {
            res = reis < 10 ? "0" + reis : Integer.toString(reis);
        } else {
            res = "01";
        }
        return res;
    }

    @Override
    public Nibbd create(Connection c, Nibbd nibbd) throws Exception {
        PreparedStatement ps = null;
        CallableStatement cs = null;
        try {
            cs = c.prepareCall("{call info.init()}");
            cs.execute();

            nibbd.setBranch(branch);
            nibbd.setStr_num(getStrNo(c, branch));
            //nibbd.setReis_num(getReis(c, true));

            ps = c.prepareStatement("insert into nibbd(branch,str_no,query_no," +
                    "query_inp,state,query_date,parent_id,id_client) " +
                    "values(?,?,?,?,1,info.getDay,?,?)");
            ps.setString(1, branch);
            ps.setLong(2, nibbd.getStr_num());
            ps.setInt(3, nibbd.getQuery_num());
            ps.setString(4, nibbd.makeQuery());
            ps.setString(5, nibbd.getParent_id());
            ps.setString(6, nibbd.getId_client());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(cs);
            DbUtils.closeStmt(ps);
        }
        return nibbd;
    }

    @Override
    public int update(Connection c, Nibbd item) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int remove(Connection c, Nibbd item) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Nibbd getItemByLongId(Connection c,String branch, long itemId) {
        Nibbd nibbd = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = c.prepareStatement("select * from nibbd where str_no=?");
            ps.setLong(1, itemId);

            rs = ps.executeQuery();
            if (rs.next()) {
                nibbd = new Nibbd(
                        rs.getString("branch"),
                        rs.getLong("str_no"),
                        rs.getInt("query_no"),
                        rs.getString("reis_no"),
                        rs.getString("query_inp"),
                        rs.getString("parent_id"),
                        rs.getInt("state"),
                        rs.getDate("query_date"),
                        rs.getString("id_client"),
                        rs.getString("query_out"),
                        "");
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
        }
        return nibbd;
    }

    @Override
    public Nibbd getItemByStringId(Connection c, String branch,String itemId) {
        // TODO Auto-generated method stub
        return null;
    }

}
