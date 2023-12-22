package com.is.sd_books.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.SD_Error;
import com.is.utils.CheckNull;

public class ErrorService {
	
	public static List<SD_Error> getErrors(Double b_id,String alias){
		Connection c = null;
		List<SD_Error> list = new ArrayList<SD_Error>();
		try{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("SELECT * FROM SD_ERRORS WHERE BOOK_ID = ? ORDER BY V_DATE DESC");
			ps.setDouble(1, b_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				list.add(new SD_Error(rs.getDate("v_date"),rs.getString("note")));
			}
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return list;
	}
}
