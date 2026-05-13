package com.is.sd_books.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.sd_books.model.SD_Book;
import com.is.sd_books.model.Credentials;

public class OperationService {
	private Credentials credentials;

	private OperationService(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public static OperationService getInstance(Credentials credentials){
		return new OperationService(credentials);
	}
	
	public void openOperation(double bookId, Date operDate, int operCode,
			double summa, int emp_id) {
		Connection c = null;
		try {
			System.out.println("openOperation");
			c = ConnectionPool.getConnection(credentials.getLogin(),
					credentials.getPassword(), credentials.getAlias());
			System.out.println("openOperation4546");
			CallableStatement cs = c
					.prepareCall(" {call sd_ww_service.action(?,?,?,?,?,?)} ");
			cs.setString(1, credentials.getBranch());
			cs.setDouble(2, bookId);
			cs.setDate(3, operDate);
			cs.setInt(4, operCode);
			cs.setDouble(5, summa);
			cs.setInt(6, emp_id);
			System.out.println("openOperation123"+credentials.getBranch()+" ,"+bookId+", "+operDate+" ,"+operCode+" ,"+summa+" ,"+emp_id);
			cs.execute();
			c.commit();
			
		} catch (SQLException e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			System.out.println("oshibka: "+e);
		} finally {
			ConnectionPool.close(c);
		}
	}

	
	public void leadOperation(SD_Book book) {
		Connection c = null;
		CallableStatement cs=null;
	
		try {
			c = ConnectionPool.getConnection(credentials.getLogin(),
					credentials.getPassword(), credentials.getAlias());
						cs = c.prepareCall("{call sd_ww_service.lead(?,?,?)}");
			cs.setString(1, book.getFilial());
			cs.setString(2, book.getDep());
			cs.setLong(3, book.getNum());
			cs.execute();
			c.commit();
	
			ISLogger.getLogger().warn("getFilial:"+book.getFilial()+":getDep()"+book.getDep()+":getNum"+book.getNum());				
			System.out.println("getFilial:"+book.getFilial()+":getDep()"+book.getDep()+":getNum"+book.getNum());
		} catch (SQLException e) {
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			
		} finally {
			ConnectionPool.close(c, cs);
			
		}
	}
	public static String getBankType_(Credentials cr) {
        Connection c = null;
        String res = "000";
        try {
          c = ConnectionPool.getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT bank_type value FROM s_mfo WHERE bank_id=? and act='A'");
            ps.setString(1, cr.getBranch());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getString("value");
            }
        } catch (Exception e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        } finally {
          ConnectionPool.close(c);
        }
        return res;
    }
	
	public double offerSD(double bookId, Date operDate,int operCode){
		Connection c = null;
		CallableStatement cs=null;
		Double result=0.00d;
	try{
		c = ConnectionPool.getConnection(credentials.getLogin(),
				credentials.getPassword(), credentials.getAlias());
		CallableStatement paramcall = c.prepareCall("{call info.init()}");
        paramcall.execute();
        cs = c.prepareCall("{?= call sd_ww_service.offer(?,?,?,?)}");
        cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
        cs.setString(2, credentials.getBranch());
		cs.setDouble(3, bookId);
		cs.setDate(4, operDate);
		cs.setInt(5,operCode);
		cs.execute();
		result=cs.getDouble(1);
		//c.commit();       
		
		
	} catch(SQLException e){
		ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		
	}
	finally {
		ConnectionPool.close(c,cs);
		
	}
	ISLogger.getLogger().warn("Result Percentage="+result);
	 return result;
	 }
	
	public String getUserId(Credentials cd){
		Connection c = null;
		CallableStatement cs=null;
		String result="";
		  try {
			  c = ConnectionPool.getConnection();
	            PreparedStatement ps = c.prepareStatement("select v.id from v_bf_bank_users v where v.BRANCH=? and upper(v.USER_NAME)=?");
	            ps.setString(1, cd.getBranch());
	            ps.setString(2, cd.getLogin().toUpperCase());
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                result = rs.getString("id");
	            }
	        } catch (Exception e) {
	            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        } finally {
	          ConnectionPool.close(c);
	        }
		  ISLogger.getLogger().warn("Result-- Login="+result);
		  ISLogger.getLogger().warn("Result-- Uid="+result);
		  return result;
			}
	
	public String checkCloseDep(String dep,int operCode,double bookId){
	Connection c = null;
	CallableStatement paramCall=null;
	CallableStatement cs = null;
	String res = "";
	try{
		c = ConnectionPool.getConnection(credentials.getLogin(),
				credentials.getPassword(), credentials.getAlias());
		 paramCall = c.prepareCall("{call info.init()}");
         paramCall.execute(); 
         cs = c.prepareCall("{?= call sd_ww_service.change_action(?,?,?)}");
         cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
		 cs.setString(2, dep);
		 cs.setInt(3, operCode);
		 cs.setDouble(4, bookId);
		 cs.execute();
		res = cs.getString(1);
		c.commit();
	}
		
		catch(Exception e){
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));			
		}
	finally{
		 Utils_SD.close(paramCall);
		 Utils_SD.close(cs);
		 ConnectionPool.close(c);
	}	
	ISLogger.getLogger().warn("Result CheckDepClose="+res+", un: "+credentials.getLogin());
	 return res;	
		
	}
	
	public String checkOnDelete(String dep,int OperCode,double BookId,long id_cir,int new_type){
		Connection c = null;
		CallableStatement paramCall=null;
		CallableStatement cs = null;
		String res = "";
		try{
			c = ConnectionPool.getConnection(credentials.getLogin(),
					credentials.getPassword(), credentials.getAlias());
			 paramCall = c.prepareCall("{call info.init()}");
	         paramCall.execute(); 
	         cs = c.prepareCall("{?= call sd_ww_service.change_action(?,?,?,?,?)}");
	         cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			 cs.setString(2, dep);
			 cs.setInt(3, OperCode);
			 cs.setDouble(4, BookId);
			 cs.setLong(5, id_cir);
			 cs.setInt(6, new_type);
			 cs.execute();
			res = cs.getString(1);
			c.commit();
		}
			
			catch(Exception e){
				ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));			
			}
		finally{
			 Utils_SD.close(paramCall);
			 Utils_SD.close(cs);
			 ConnectionPool.close(c);
		}	
		ISLogger.getLogger().warn("Result CheckDepOnDelete="+res+", un: "+credentials.getLogin());
		 return res;	
	}
	
		}
