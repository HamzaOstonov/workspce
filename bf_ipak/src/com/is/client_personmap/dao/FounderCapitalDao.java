package com.is.client_personmap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.base.Dao;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.PersonMapUtil;
import com.is.client_personmap.model.FounderCapital;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class FounderCapitalDao implements Dao<FounderCapital>{
	private Logger logger = Logger.getLogger(FounderCapitalDao.class);
	private String alias;
	
	
	private FounderCapitalDao(String alias) {
		this.alias = alias;
	}
	
	public static FounderCapitalDao instance(String alias) {
		return new FounderCapitalDao(alias);
	}
	
	@Override
	public <T1 extends FounderCapital> void setFilter(T1 filter) {
		throw new UnsupportedOperationException();
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
	public List<FounderCapital> getList() {
		throw new UnsupportedOperationException();
	}
	@Override
	public List<FounderCapital> getListWithPaging(int pageIndex, int pageSize) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FounderCapital getItemByLongId(String branch,long itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FounderCapital getItemByLongId(Connection c, String branch,long itemId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		FounderCapital capital = null;
		try {
			ps = c.prepareStatement("select * from founder_capital where id_person_map=?");
			ps.setLong(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()) {
				
				capital = new FounderCapital(rs.getLong("id_person_map"), null, rs.getBigDecimal("sum_a"), rs.getBigDecimal("sum_b"),
						rs.getString("currency"), rs.getString("is_director"), rs.getBigDecimal("shares_number"));
				String stringPart = rs.getString("part_of_capital");
				capital.setPart_of_capital(PersonMapUtil.makeDecimalFromStringIfPossible(stringPart));
				capital.setPart_of_capital_old(capital.getPart_of_capital());
			}
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
		
		return capital;
	}

	@Override
	public FounderCapital getItemByStringId(String branch,String itemId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FounderCapital getItemByStringId(Connection c,String branch, String itemId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		FounderCapital capital = null;
		try {
			ps = c.prepareStatement("select * from founder_capital where id_founder=?");
			ps.setString(1, itemId);
			rs = ps.executeQuery();
			if(rs.next()) {
				capital = new FounderCapital(rs.getLong("id_person_map"), null, rs.getBigDecimal("sum_a"), rs.getBigDecimal("sum_b"),
						rs.getString("currency"), rs.getString("is_director"), rs.getBigDecimal("shares_number"));
				String stringPart = rs.getString("part_of_capital");
				capital.setPart_of_capital(PersonMapUtil.makeDecimalFromStringIfPossible(stringPart));
				capital.setPart_of_capital_old(capital.getPart_of_capital());
			}
			
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
		}
		
		return capital;
	}

	@Override
	public FounderCapital create(FounderCapital item) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public FounderCapital create(Connection c, FounderCapital item) throws Exception {
		PreparedStatement ps = null;
		FounderCapital capital = null;
		try {
			ps = c.prepareStatement("insert into founder_capital(part_of_capital," +
					"sum_a,sum_b," +
					"currency,is_director," +
					"shares_number,id_person_map)" +
					"values(?,?,?,?,?,?,?)");
			ps.setString(1, item.getPart_of_capital() != null ? item.getPart_of_capital().toPlainString() : null);
			ps.setBigDecimal(2, item.getSum_a());
			ps.setBigDecimal(3, item.getSum_b());
			ps.setString(4, item.getCurrency());
			ps.setString(5, item.getIs_director());
			ps.setBigDecimal(6, item.getShares_number());
			ps.setLong(7, item.getIdPersonMap());
			ps.executeUpdate();
		} finally {
			DbUtils.closeStmt(ps);
		}
		
		return capital;
	}

	@Override
	public int update(FounderCapital item) throws Exception {
		return 0;
	}

	@Override
	public int update(Connection c, FounderCapital item) throws Exception {
		PreparedStatement ps = null;
		int count = 0;
		try {
			ps = c.prepareStatement("update founder_capital set part_of_capital=?," +
					"sum_a=?," +
					"currency=?,is_director=?," +
					"shares_number=? " +
					"where id_person_map=?");
			ps.setString(1, item.getPart_of_capital() != null ? item.getPart_of_capital().toPlainString() : null);
			ps.setBigDecimal(2, item.getSum_a());
			ps.setString(3, item.getCurrency());
			ps.setString(4, item.getIs_director());
			ps.setBigDecimal(5, item.getShares_number());
			ps.setLong(6, item.getIdPersonMap());
			count = ps.executeUpdate();
		} finally {
			DbUtils.closeStmt(ps);
		}
		
		return count;
	}

	@Override
	public int remove(FounderCapital item) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int remove(Connection c, FounderCapital item) throws Exception {
		PreparedStatement ps = null;
		int count = 0;
		try {
			ps = c.prepareStatement("delete from founder_capital where id_person_map=?");
			ps.setLong(1, item.getIdPersonMap());
			count = ps.executeUpdate();
			
		} finally {
			DbUtils.closeStmt(ps);
		}
		
		return count;
	}

}
