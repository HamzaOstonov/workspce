package com.is.tieto_visae.tieto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.is.ConnectionPool;



public class DBUtils {
    public void alterSession(String branch, String user, String pass, String alias) throws Exception {
        Connection c = ConnectionPool.getConnection(user, pass, alias);
        PreparedStatement ps;
        CallableStatement cs;
        Statement st;
        ResultSet rs;

        ps = c.prepareStatement("SELECT user_name FROM ss_dblink_branch WHERE branch = ?");
        ps.setString(1, branch);
        rs = ps.executeQuery();

        if (rs.next()) {
            alias = rs.getString("user_name");
        } else {
            throw new Exception("Wrong branch: " + branch);
        }

        st = c.createStatement();
        st.executeUpdate("ALTER SESSION SET nls_date_format='dd.mm.yyyy'");
        st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + alias);

        cs = c.prepareCall("{call is_go_user(?,?)}");
        cs.setString(1, user.toUpperCase());
        cs.setInt(2, 99999);
        cs.execute();
        st.execute("{call info.init()}");

        rs.close();
        st.close();
        cs.close();
        ps.close();
        c.close();
    }

	public DBUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
