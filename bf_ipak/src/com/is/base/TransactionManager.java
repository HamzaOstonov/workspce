package com.is.base;

import java.sql.Connection;
import java.sql.SQLException;

import com.is.ConnectionPool;

public class TransactionManager {
	private Connection con;
	private boolean isClosed;
	private String alias;
	
	private TransactionManager() {}
	private TransactionManager(String alias) {this.alias = alias;}
	
	public static TransactionManager getInstance(String alias) {
		return new TransactionManager( alias);
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
