package com.is.nibbd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.ConnectionPool;

public class Test {
	public static void main(String[] args) {
		Connection c = null;
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			cs = c.prepareCall("{call info.init()}");
			cs.execute();
			ps = c.prepareStatement("select info.getbranch from dual");
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString(1));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

	}
}
