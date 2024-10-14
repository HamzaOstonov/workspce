package com.is.base;

import java.sql.Connection;
import java.sql.SQLException;

import com.is.ConnectionPool;

public class LongConnection {
	private Connection con;
	private String alias;
	
	private LongConnection() {}
	private LongConnection(String alias) {this.alias = alias;}
	
	public static LongConnection getInstance(String alias) {
		return new LongConnection( alias);
	}
	
	public Connection getConnection() throws SQLException {
		if(con == null || con.isClosed()) {
			if(alias == null) {
				con = ConnectionPool.getConnection();
			} else {
				con = ConnectionPool.getConnection(alias);
			}
		}
		return con;
	}
	
	public void closeCon() {
		if(con != null) {
			ConnectionPool.close(con);
		}
	}
}
