package com.is.sd_books.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;

import com.is.ISLogger;
import com.is.sd_books.model.Credentials;
import com.is.sd_books.model.Deposit;
import com.is.sd_books.model.Sd_priv_Cond;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class DepositService {
	
	public static List<RefData> getFilials(Credentials cr,int UserId) {
		Connection c = null;
		PreparedStatement ps = null;
		List<RefData> list = new LinkedList<RefData>();
		try {
			c = ConnectionPool.getConnection(cr.getLogin(), cr.getPassword(),
					cr.getAlias());
		
			CallableStatement cs = c.prepareCall("{call info.init()}");
            cs.execute();
            ps = c.prepareStatement("SELECT f.code, f.code id, f.name, sd.regime "
					+ "FROM ss_sd_subsidiary sd, ss_subsidiary f, sd_subs_off "
					+ "WHERE f.branch = info.getBranch AND sd.branch = f.branch AND f.code = sd.code "
					+ "AND f.state = 'A' and sd_subs_off.branch = sd.branch and sd_subs_off.CODE_SUBS=sd.code "
					+ "and sd_subs_off.IS_NEW_DEP=1 and sd.code in (select u.code from ss_subsidiary_user u where u.id_user=?) order by 1 ");
			ps.setInt(1, UserId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("code"), rs.getString("name")));
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<RefData> getDeposits(Credentials cr) {
		Connection c = null;
		PreparedStatement ps = null;
		List<RefData> list = new LinkedList<RefData>();
		try {
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());
			ps = c.prepareStatement("SELECT code, code id, name FROM v_sd_open_dep order by list_order");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new RefData(rs.getString("code"), rs
						.getString("name")));
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<RefData> getOperationsForOpen(String dep,Credentials cr) {
		Connection c = null;
		PreparedStatement ps = null;
		List<RefData> operations = new LinkedList<RefData>();
		try {
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());
			ps = c.prepareStatement("SELECT code || ' ' || name as name , code "
					+ "FROM  v_sd_open_turn where v_sd_open_turn.dep=? ");
			ps.setString(1, dep);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				operations.add(new RefData(rs.getString("CODE"), rs
						.getString("NAME")));
			}
		} catch (SQLException e) {
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return operations;
	}
	
	public static List<RefData> getSdClerkBooksforOpen(Credentials cr,int userId){
	Connection c=null;
	PreparedStatement ps=null;	
	List<RefData> clerkBookslist=new LinkedList<RefData>();
   try{
	  c=ConnectionPool.getConnection(cr.getLogin(), cr.getPassword(), cr.getAlias());
	  ps=c.prepareStatement("select B_SER serial,B_NUM num from v_sd_clerk_emp where v_sd_clerk_emp.emp=?");
	  ps.setInt(1, userId); 
	  ResultSet rs=ps.executeQuery();
	  while (rs.next()) {
			clerkBookslist.add(new RefData(rs.getString("SERIAL"), rs
					.getString("NUM")));
		}
	  
       }		
   catch (SQLException e){
	   com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	   }
	finally{
       ConnectionPool.close(c);		
		}	
		return 	clerkBookslist;
	}
	
	public static Res openDep(Credentials cr, Deposit deposit){
		Res result = null;
		Connection c = null;
		CallableStatement newBookCall = null;
		CallableStatement paramCall = null;
		CallableStatement newActionCall = null;
		CallableStatement SdClerkCall=null;
		int bookId = 0;
		try {
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());
			Statement st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET NLS_NUMERIC_CHARACTERS='.,'");
			CallableStatement cs = c.prepareCall("{call info.init()}");
            cs.execute();
            c.setAutoCommit(false);
            System.out.println("CRM ");
            
//            paramCall = c.prepareCall("{? = call Param.SetParam('IS_SRM','1')}");
//            paramCall.execute();
            
            newBookCall = c.prepareCall("{call sd_ww_service.new_book(?,?,?,?,?,?,?)}");
            paramCall = c.prepareCall("{ call Param.SetParam(?,?)}");
            paramCall.setString(1,"IS_SRM");
            paramCall.setString(2, "1");
            paramCall.execute();
			
            //System.out.println("CRM "+paramCall);

			newBookCall.setString(1, cr.getBranch());

			newBookCall.setString(2, deposit.getFilial());

			newBookCall.setString(3, deposit.getDep());

			newBookCall.setString(4, deposit.getId_client());

			newBookCall.setDate(5, (Date) deposit.getOperDate());

			newBookCall.setDouble(6, deposit.getSum());
			
			newBookCall.setString(7, "");

			newBookCall.execute();

			paramCall = c.prepareCall("{? = call Param.GetParam('BOOK_ID') }");

			paramCall.registerOutParameter(1, java.sql.Types.INTEGER);

			paramCall.execute();

			bookId = paramCall.getInt(1);
			

			newActionCall = c
					.prepareCall("{call sd_ww_service.action(?,?,?,?,?,?)}");

			newActionCall.setString(1, cr.getBranch());

			newActionCall.setInt(2, bookId);

			newActionCall.setDate(3, (Date) deposit.getOperDate());

			newActionCall.setString(4, deposit.getOperTurn());

			newActionCall.setDouble(5, deposit.getSum());

			newActionCall.setInt(6, deposit.getEmp_id());

			newActionCall.execute();
			
			// Прикрепляем сберкнижки
			if(DepositService.getBankType(cr).equals("004")){
				SdClerkCall=c.prepareCall("{call sd_ww_service.sd_clerk_extr(?,?,?,?)}");
				SdClerkCall.setString(1,deposit.getFilial());
				SdClerkCall.setInt(2, bookId);
				SdClerkCall.setString(3, deposit.getP_ser());
				System.out.println("p_ser: "+deposit.getP_ser());
				SdClerkCall.setString(4,deposit.getP_num());
				System.out.println("p_num: "+deposit.getP_num());
				SdClerkCall.execute();
			}
			else {
			PreparedStatement ps = c.prepareStatement("INSERT INTO SD_CLERK_PSEVDO (BRANCH,B_SER,B_NUM,SD_BOOKS_ID) VALUES(?,?,?,?)");
			ps.setString(1, cr.getBranch());
			ps.setString(2, deposit.getP_ser());
			ps.setString(3, deposit.getP_num());
			ps.setInt(4, bookId);
			ps.executeUpdate();
			}
			
			result = new Res(bookId,"");
			c.commit();

		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			com.is.ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
			result = new Res(bookId,e.getMessage());
		} finally {
			Utils_SD.close(newBookCall);
			Utils_SD.close(paramCall);
			Utils_SD.close(newBookCall);
			Utils_SD.close(newActionCall);
			Utils_SD.close(SdClerkCall);
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static Boolean isClientConfirmed(String client_code,String alias){
		Connection c = null;
		boolean result = false;
		try{
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("SELECT STATE FROM CLIENT_P WHERE ID = ?");
			ps.setString(1, client_code);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int state = rs.getInt("state");
			if (state != 2)
				result = false;
			else 
				result = true;
		}
		catch(SQLException e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			ConnectionPool.close(c);
		}
		return result;
	}
	
	public static String getBankType(Credentials cr){
		
		Connection c=null;
		String result="";
		try{
			c=ConnectionPool.getConnection(cr.getAlias());
			PreparedStatement ps=c.prepareCall("select bank_type from s_mfo s where s.bank_id=?");
			ps.setString(1, cr.getBranch());

			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				result=rs.getString("BANK_TYPE");
			}
					
		}
		catch(Exception e){
			ISLogger.getLogger().error(CheckNull.getPstr(e));
						
		}
		finally{
			ConnectionPool.close(c);
		}
		return result;
	}

	public static boolean checkPrivCond(String alias, String dep) {
		boolean res = false;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select count(ss_depconditions.dep) cnt from ss_depconditions "
					+ " ,ss_deppar where ss_depconditions.par=ss_deppar.id and ss_depconditions.isprivate='Y' and ss_depconditions.dep=? ");
			ps.setString(1, dep);
			rs = ps.executeQuery();
			if (rs.next()) {
				int st = Integer.parseInt(rs.getString("CNT"));
				if (st > 0) {
					res = true;
				} else {
					res = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
				ISLogger.getLogger().error(CheckNull.getPstr(ex));
			}

			ConnectionPool.close(c);
		}

		return res;
	}
	public static int getCount(String alias,String dep){
		int res=-1;
		Connection c = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select count(ss_depconditions.dep) cnt "
					     			+" from ss_depconditions ,ss_deppar where ss_depconditions.par=ss_deppar.id "
					                 +" and ss_depconditions.isprivate='Y' and ss_depconditions.dep=?");
            //ps = c.prepareStatement("select count(*) cnt from  v_list_required  where v_list_required.dep=? ");
            ps.setString(1, dep);
            rs = ps.executeQuery();
            while (rs.next()){
            	res = Integer.parseInt(rs.getString("CNT"));
						}
		} catch (SQLException e) {

			com.is.ISLogger.getLogger().error("********count priv_cond_dep******** " + e.getErrorCode() + ": " + e.getMessage()
					+ com.is.utils.CheckNull.getPstr(e));
		} finally {

			try {

				if (rs != null) {

					rs.close();
				}

				if (ps != null) {

					ps.close();
				}	
            	
            }
			catch(Exception e){
				e.printStackTrace();
			                  }
		  ConnectionPool.close(c);
			       }				
	return res;
	}
	
	public static int getBookId(Credentials dr, Deposit dep){
		int bookId = 0;
		Res res=null;
		Connection c = null;
		CallableStatement newBookCall = null;
		CallableStatement paramCall = null;
		try {
			c = ConnectionPool.getConnection(dr.getLogin(),dr.getPassword(),dr.getAlias());
			Statement st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET NLS_NUMERIC_CHARACTERS='.,'");
			CallableStatement cs = c.prepareCall("{call info.init()}");
            cs.execute();
            c.setAutoCommit(false);
            System.out.println("CRM ");
            
//            paramCall = c.prepareCall("{? = call Param.SetParam('IS_SRM','1')}");
//            paramCall.execute();
            
            newBookCall = c.prepareCall("{call sd_ww_service.new_book(?,?,?,?,?,?,?)}");
            paramCall = c.prepareCall("{ call Param.SetParam(?,?)}");
            paramCall.setString(1,"IS_SRM");
            paramCall.setString(2, "1");
            paramCall.execute();
			
            //System.out.println("CRM "+paramCall);

			newBookCall.setString(1, dr.getBranch());

			newBookCall.setString(2, dep.getFilial());

			newBookCall.setString(3, dep.getDep());

			newBookCall.setString(4, dep.getId_client());

			newBookCall.setDate(5, (Date) dep.getOperDate());

			newBookCall.setDouble(6, dep.getSum());
			
			newBookCall.setString(7, "");

			newBookCall.execute();

			paramCall = c.prepareCall("{? = call Param.GetParam('BOOK_ID') }");

			paramCall.registerOutParameter(1, java.sql.Types.INTEGER);

			paramCall.execute();
			System.out.println("ParamCall: "+paramCall.toString());

			bookId = paramCall.getInt(1);
			System.out.println("BookID : "+bookId);
			
			c.commit();
					
		}
		catch(Exception e){
			e.printStackTrace();
			
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			com.is.ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			res = new Res(bookId,e.getMessage());
		} finally {
			Utils_SD.close(paramCall);
			Utils_SD.close(newBookCall);
			ConnectionPool.close(c);
		}
		return bookId;
			
		}
	public static Res openDepNew(Credentials cr, Deposit deposit,int bookId,List<Sd_priv_Cond> list){
		Res result = null;
		Connection c = null;
		CallableStatement newActionCall = null;
		CallableStatement SdClerkCall=null;
		CallableStatement newActionCall1=null;
		CallableStatement paramCall = null;
		
		try {
			c = ConnectionPool.getConnection(cr.getLogin(),cr.getPassword(),cr.getAlias());
			Statement st = c.createStatement();
			st.executeUpdate("ALTER SESSION SET NLS_NUMERIC_CHARACTERS='.,'");
			CallableStatement cs = c.prepareCall("{call info.init()}");
            cs.execute();
            c.setAutoCommit(false);
            System.out.println("CRM ");
            if(list.size()>0){
            	try {
            		for(int i=0;i<list.size();i++){
						String val=String.valueOf(list.get(i).getValue()+"");
						ISLogger.getLogger().error("Description_val_CRM:   "+val);	
						ISLogger.getLogger().warn("List_dep_priv_cond_par_CRM= "+list.get(i).getPar()+"   value="+list.get(i).getValue());
						newActionCall1=c.prepareCall("call sd_ww_service.ins_private_cond(?,?,?,?,?,?,?)");
						newActionCall1.setString(1, cr.getBranch());
						newActionCall1.setString(2,deposit.getId_client());
						newActionCall1.setInt(3,bookId);
						newActionCall1.setInt(4, Integer.valueOf(list.get(i).getPar()));
						newActionCall1.setString(5, (list.get(i).getValue()+""));
						newActionCall1.setString(6, "-1");
						newActionCall1.setString(7, String.valueOf(bookId));
						newActionCall1.execute();
            		
            	                                   }
            	     }
            	catch(Exception e){
					e.printStackTrace();
					ISLogger.getLogger().error("Oshibka: pri dobavleniya priv_cond:   "+e.getMessage());
					c.rollback();
	            	result = new Res(-1, " Ошибка при добавление индивидуальных параметров вклада! ");
	            	return result;
            	
            }
             }
//            paramCall = c.prepareCall("{? = call Param.SetParam('IS_SRM','1')}");
//            paramCall.execute();
            newActionCall = c.prepareCall("{call sd_ww_service.action(?,?,?,?,?,?)}");

			newActionCall.setString(1, cr.getBranch());

			newActionCall.setInt(2, bookId);

			newActionCall.setDate(3, (Date) deposit.getOperDate());

			newActionCall.setString(4, deposit.getOperTurn());

			newActionCall.setDouble(5, deposit.getSum());
			System.out.println("summa_action: "+deposit.getSum());

			newActionCall.setInt(6, deposit.getEmp_id());

			newActionCall.execute();
			
			// Прикрепляем сберкнижки
			if(DepositService.getBankType(cr).equals("004")){
				SdClerkCall=c.prepareCall("{call sd_ww_service.sd_clerk_extr(?,?,?,?)}");
				SdClerkCall.setString(1,deposit.getFilial());
				SdClerkCall.setInt(2, bookId);
				SdClerkCall.setString(3, deposit.getP_ser());
				System.out.println("p_ser: "+deposit.getP_ser());
				SdClerkCall.setString(4,deposit.getP_num());
				System.out.println("p_num: "+deposit.getP_num());
				SdClerkCall.execute();
			}
			else {
			PreparedStatement ps = c.prepareStatement("INSERT INTO SD_CLERK_PSEVDO (BRANCH,B_SER,B_NUM,SD_BOOKS_ID) VALUES(?,?,?,?)");
			ps.setString(1, cr.getBranch());
			ps.setString(2, deposit.getP_ser());
			ps.setString(3, deposit.getP_num());
			ps.setInt(4, bookId);
			ps.executeUpdate();
			}
			
			result = new Res(bookId,"");
			c.commit();

		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			com.is.ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
			result = new Res(bookId,e.getMessage());
		} finally {
			Utils_SD.close(paramCall);
			Utils_SD.close(newActionCall1);
			Utils_SD.close(newActionCall);
			Utils_SD.close(SdClerkCall);
			ConnectionPool.close(c);
		}
		return result;
	}
	
		
}
