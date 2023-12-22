package com.is.nibbd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.nibbd.models.NibbdReaction;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import org.apache.log4j.Logger;

public class NibbdReactionDao implements Dao<NibbdReaction> {
    private static final Logger logger = Logger.getLogger(NibbdReactionDao.class);
    private String alias;

    private static final String UPDATE_ONE_RECORD = "update nibbd_returned_clients set state=? where id_client=? and branch=?";

    private NibbdReactionDao(String alias) {
        this.alias = alias;
    }

    private NibbdReactionDao() {
    }

    public static NibbdReactionDao getInstance() {
        return new NibbdReactionDao();
    }

    public static NibbdReactionDao getInstance(String alias) {
        return new NibbdReactionDao(alias);
    }

    @Override
    public List<FilterField> getFilterFields() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<NibbdReaction> getList() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<NibbdReaction> list = new ArrayList<NibbdReaction>();
        try {
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement("select * from nibbd_returned_clients where state=0");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new NibbdReaction(rs.getLong(
                        "id"),
                        rs.getString("id_client"),
                        rs.getString("branch"),
                        rs.getInt("state"), rs.getInt("emp_id")));
            }
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeResultSet(rs);
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }
        return list;
    }

    @Override
    public List<NibbdReaction> getListWithPaging(int pageIndex, int pageSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NibbdReaction getItemByLongId(String branch, long itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NibbdReaction getItemByStringId(String branch, String itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NibbdReaction create(NibbdReaction item) {
//		Connection c = null;
//		PreparedStatement ps = null;
//		try {
//			if(alias == null){
//				c = ConnectionPool.getConnection();
//			} else {
//				c = ConnectionPool.getConnection(alias);
//			}
//			ps = c.prepareStatement("insert into nibbd_reaction(nibbd_str,state) values(?,1)");
//			ps.setLong(1, item.getNibbd_str());
//			ps.executeUpdate();
//			c.commit();
//		} catch (Exception e) {
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//			e.printStackTrace();
//		} finally {
//			ConnectionPool.close(c);
//		}
//		
//		return item;
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(NibbdReaction item) {
        Connection c = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            if (alias == null) {
                c = ConnectionPool.getConnection();
            } else {
                c = ConnectionPool.getConnection(alias);
            }

            ps = c.prepareStatement("update nibbd_returned_clients set state= ? where id_client=? and branch=?");
            ps.setInt(1,item.getState());
            ps.setString(2, item.getId_client());
            ps.setString(3, item.getBranch());

            count = ps.executeUpdate();
            c.commit();
        } catch (Exception e) {
            logger.error(CheckNull.getPstr(e));
        } finally {
            DbUtils.closeStmt(ps);
            ConnectionPool.close(c);
        }

        return count;
    }

    @Override
    public int remove(NibbdReaction item) {
        return 0;
    }

    @Override
    public <T1 extends NibbdReaction> void setFilter(T1 filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NibbdReaction create(Connection c, NibbdReaction item)
            throws Exception {
//		PreparedStatement ps = null;
//		try {
//			ps = c.prepareStatement("insert into nibbd_reaction(nibbd_str,state, query_date) values(?,1, info.getday)");
//			ps.setLong(1, item.getNibbd_str());
//			ps.executeUpdate();
//			c.commit();
//		} catch (Exception e) {
//			ISLogger.getLogger().error(CheckNull.getPstr(e));
//			e.printStackTrace();
//		} finally {
//			DbUtils.closeStmt(ps);
//		}
//		
//		return item;
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Connection c, NibbdReaction item)
            throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public int remove(Connection c, NibbdReaction item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NibbdReaction getItemByLongId(Connection c, String branch, long itemId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NibbdReaction getItemByStringId(Connection c, String branch, String itemId) {
        throw new UnsupportedOperationException();
    }
}	
