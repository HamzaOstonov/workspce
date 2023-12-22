package com.is.qr_online.registr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;
import com.is.ISLogger;


public class References {

	public static String ss_qr_payment_subject(String id) {

		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("SELECT name FROM ss_qr_payment_subject where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}
	
	public static String s_str(String id) {

		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select name from s_str where char_code=?");
			ps.setString(1, id+" ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}
	
	public static String s_val(String id) {

		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select s.namev name from s_val s where s.kod=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}
	
	public static String ss_qr_commission_fee(String id) {

		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select name from ss_qr_commission_fee where id=?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}

	public static String ss_qr_cod_types(int code_type) {

		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select name from ss_qr_cod_types where code_type=?");
			ps.setInt(1, code_type);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}

	public static String ss_qr_category_suppliers(String category) {
		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select id_diapazon, name_ru from ss_qr_category_suppliers where id like '%"+category+"%'");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name_ru") + "/" + rs.getString("id_diapazon");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}

	public static String ss_qr_diapazon_suppliers(String id_diapazon) {
		Connection c = null;
		String name = null;
		
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("select name from ss_qr_diapazon_suppliers where id=?");
			ps.setString(1, id_diapazon);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return name;
	}
	

}
