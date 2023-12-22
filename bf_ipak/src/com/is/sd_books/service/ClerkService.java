package com.is.sd_books.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.sd_books.model.Clerk_Book;


public class ClerkService {
	
	public static void updateClerk(String branch,double book_id,String p_ser,String p_num,String alias){
		Connection c = null;
		try{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("INSERT INTO SD_CLERK_PSEVDO (BRANCH,B_SER,B_NUM,SD_BOOKS_ID) (?,?,?,?)");
			ps.setString(1, branch);
			ps.setString(2, p_ser);
			ps.setString(3, p_num);
			ps.setDouble(4, book_id);
			ps.executeUpdate();
		}
		catch (SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
	}
	public static List<Clerk_Book> showClerkbook(String branch,double book_id,String alias){
		Connection c=null;
		List<Clerk_Book> list=new ArrayList<Clerk_Book>();
		try{
			c=ConnectionPool.getConnection(alias);
			PreparedStatement ps=c.prepareCall("select B_SER, B_NUM from v_sd_clerk_book v where v.branch=? and v.sd_books_id=? ");
			ps.setString(1,branch);
			ps.setDouble(2, book_id);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
           list.add(new Clerk_Book(rs.getString("B_SER"),rs.getString("B_NUM")));
        	      	   
           }
			
		}
		catch(Exception e){
			ISLogger.getLogger().error(e.getMessage());
		}
		finally{
			
			ConnectionPool.close(c);
		}

		return list;
	}
}
