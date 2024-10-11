package com.is.sd_books.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.model.Percentage;

public class PrcService {

	private final static String sqlPercentage = "SELECT id,v_date,branch,book_id,code, "
			+ "nvl(Decode(sign(turn), 1,turn, 0),0) turn_cr, "
			+ "nvl(Decode(sign(turn), -1,abs(turn),0),0) turn_db, "
			+ "saldo, pc,emp,bank_date,general_id,fx_code,note "
			+ "FROM SD_NIGHT_CHARGE c WHERE BOOK_ID = ? order by v_date asc";

	public static List<Percentage> getAccruedPercentage(double book_id,Credentials cr) {
		Connection c = null;

		PreparedStatement ps = null;

		List<Percentage> list = new ArrayList<Percentage>();

		try {
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());

			ps = c.prepareStatement(sqlPercentage);

			ps.setDouble(1, book_id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Percentage(rs.getLong("id"), rs.getDate("v_date"),
						rs.getString("branch"), rs.getLong("book_id"), rs
								.getInt("code"), rs.getDouble("saldo"), rs
								.getInt("pc"), rs.getLong("emp"), rs
								.getDate("bank_date"),
						rs.getLong("general_id"), rs.getInt("fx_code"), rs
								.getString("note"), rs.getDouble("turn_cr"), rs
								.getDouble("turn_db")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}

		return list;
	}
	
	
}
