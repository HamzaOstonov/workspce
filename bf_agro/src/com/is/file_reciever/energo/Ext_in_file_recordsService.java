package com.is.file_reciever.energo;

	import general.General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
	import java.util.List;
import java.sql.*;

import accounting_transaction.TransactionService;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

	public class Ext_in_file_recordsService {

	        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	        private static String psql2 =" ) s order by s.message_id desc) t where rownum <= ? ) t  where t.rwnm >= ?";
	        private static String msql ="SELECT * FROM Ext_in_file_records ";


	        public List<Ext_in_file_records> getExt_in_file_records()  {

	                List<Ext_in_file_records> list = new ArrayList<Ext_in_file_records>();
	                Connection c = null;

	                try {
	                        c = ConnectionPool.getConnection();
	                        Statement s = c.createStatement();
	                        ResultSet rs = s.executeQuery("SELECT * FROM Ext_in_file_records");
	                        while (rs.next()) {
	                                list.add(new Ext_in_file_records(
	                                                rs.getLong("id"),
	                                                rs.getLong("in_file_id"),
	                                                rs.getLong("type_record_id"),
	                                                rs.getString("rec_value"),
	                                                rs.getLong("message_id"),
	                                                rs.getString("err_message")));
	                        }
	                } catch (SQLException e) {
	                        e.printStackTrace();
	                } finally {
	                        ConnectionPool.close(c);
	                }
	                return list;

	        }



	     

	        private static String getCond(List<FilterField> flfields){
	                if(flfields.size()>0){
	                        return " and ";
	                }else
	                return " where ";
	        }

	        private static List<FilterField> getFilterFields(Ext_in_file_recordsFilter filter){
	                List<FilterField> flfields = new ArrayList<FilterField>();


	              if(!CheckNull.isEmpty(filter.getId())){
	                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
	              }
	              if(!CheckNull.isEmpty(filter.getIn_file_id())){
	                      flfields.add(new FilterField(getCond(flfields)+ "in_file_id=?",filter.getIn_file_id()));
	              }
	              if(!CheckNull.isEmpty(filter.getType_record_id())){
	                      flfields.add(new FilterField(getCond(flfields)+ "type_record_id=?",filter.getType_record_id()));
	              }
	              if(!CheckNull.isEmpty(filter.getRec_value())){
	                      flfields.add(new FilterField(getCond(flfields)+ " rec_value like(?)",filter.getRec_value()));
	              }
	              if(!CheckNull.isEmpty(filter.getError_id())){
	                      flfields.add(new FilterField(getCond(flfields)+ "message_id=?",filter.getError_id()));
	              }
	              if(!CheckNull.isEmpty(filter.getErr_message())){
	                      flfields.add(new FilterField(getCond(flfields)+ "err_message=?",filter.getErr_message()));
	              }
	             // flfields.add(new FilterField(getCond(flfields)+ " in_file_id = (select ext_fl.id from ext_in_files ext_fl where ext_fl.fr_id = ?) ",filter.getCurrent_file_fr_id()));

	              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",100001));

	                return flfields;
	        }


	        public static int getCount(Ext_in_file_recordsFilter filter)  {

	            Connection c = null;
	            int n = 0;
	            List<FilterField> flFields =getFilterFields(filter);
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT count(*) ct FROM Ext_in_file_records ");
	            if(flFields.size()>0){

	                    for(int i=0;i<flFields.size();i++){
	                            sql.append(flFields.get(i).getSqlwhere());
	                    }
	            }
	            try {
	                    c = ConnectionPool.getConnection();
	                    PreparedStatement ps = c.prepareStatement(sql.toString());

	                        for(int k=0;k<flFields.size();k++){
	                        ps.setObject(k+1, flFields.get(k).getColobject());
	                        }
	                        ResultSet rs = ps.executeQuery();

	                    if (rs.next()) {
	                        n = rs.getInt(1);
	                    }
	            } catch (SQLException e) {
	                    e.printStackTrace();

	            } finally {
	                    ConnectionPool.close(c);
	            }
	            return n;

	    }



	        public static List<Ext_in_file_records> getExt_in_file_recordssFl(int pageIndex, int pageSize, Ext_in_file_recordsFilter filter)  {

	                List<Ext_in_file_records> list = new ArrayList<Ext_in_file_records>();
	                Connection c = null;
	                int v_lowerbound = pageIndex + 1;
	                int v_upperbound = v_lowerbound + pageSize - 1;
	                int params;
	                List<FilterField> flFields =getFilterFields(filter);

	                StringBuffer sql = new StringBuffer();
	                sql.append(psql1);
	                sql.append(msql);
	                if(flFields.size()>0){

	                        for(int i=0;i<flFields.size();i++){
	                                sql.append(flFields.get(i).getSqlwhere());
	                        }
	                }
	                sql.append(psql2);


	                try {
	                        c = ConnectionPool.getConnection();
	                        PreparedStatement ps = c.prepareStatement(sql.toString());
	                        for(params=0;params<flFields.size();params++){
	                        ps.setObject(params+1, flFields.get(params).getColobject());
	                        }
	                        params++;
	                        ps.setInt(params++,v_upperbound);
	                        ps.setInt(params++,v_lowerbound);

	                        ResultSet rs = ps.executeQuery();
	                        while (rs.next()) {
	                                list.add(new Ext_in_file_records(
	                                                rs.getLong("id"),
	                                                rs.getLong("in_file_id"),
	                                                rs.getLong("record_type_id"),
	                                                rs.getString("rec_value"),
	                                                rs.getLong("message_id"),
	                                                rs.getString("err_message")));
	                        }
	                } catch (SQLException e) {
	                        e.printStackTrace();

	                } finally {
	                        ConnectionPool.close(c);
	                }
	                return list;

	        }


	        public Ext_in_file_records getExt_in_file_records(int ext_in_file_recordsId) {

	                Ext_in_file_records ext_in_file_records = new Ext_in_file_records();
	                Connection c = null;

	                try {
	                        c = ConnectionPool.getConnection();
	                        PreparedStatement ps = c.prepareStatement("SELECT * FROM ext_in_file_records WHERE id=?");
	                        ps.setInt(1, ext_in_file_recordsId);
	                        ResultSet rs = ps.executeQuery();
	                        if (rs.next()) {
	                                ext_in_file_records = new Ext_in_file_records();
	                                
	                                ext_in_file_records.setId(rs.getLong("id"));
	                                ext_in_file_records.setIn_file_id(rs.getLong("in_file_id"));
	                                ext_in_file_records.setType_record_id(rs.getLong("type_record_id"));
	                                ext_in_file_records.setRec_value(rs.getString("rec_value"));
	                                ext_in_file_records.setError_id(rs.getLong("message_id"));
	                                ext_in_file_records.setErr_message(rs.getString("err_message"));
	                        }
	                } catch (Exception e) {
	                        e.printStackTrace();
	                } finally {
	                        ConnectionPool.close(c);
	                }
	                return ext_in_file_records;
	        }
	        
	        public static String get_file_records_details(long record_id)
	    	{
	    		String res = "";
	    		
	    		Connection c = null;
	    		PreparedStatement ps = null;
	    		ResultSet rs = null;
	    		try
	    		{
	    			c = ConnectionPool.getConnection();
	    			ps = c.prepareStatement(
	    					"select base_names.name, " +
                        "REGEXP_SUBSTR(fr.rec_value, tr.parser_regexp, 1, rc.index_position ) str_value " + 
                        "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_record_cols rc, " +
                        "ss_lang_base_names base_names, " +
                        "ss_ext_column_types ct " +
                        "where fr.record_type_id = tr.id " +  
                        "and fr.record_type_id = rc.record_type_id " +
                        "and rc.column_id = ct.id " +
                        "and base_names.id = ct.name_id " +
                        "and fr.id = ? " +
                        "order by fr.id, rc.record_type_id, rc.index_position");
	    			ps.setLong(1, record_id);
	    			rs = ps.executeQuery();
	    			while (rs.next())
	    			{
	    				res += rs.getString("name") + ": " + rs.getString("str_value") + "\n";
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	    		}
	    		finally
	    		{
	    			try{if (c!= null) c.close();} catch (Exception e){}
	    			try{if (ps!= null) ps.close();} catch (Exception e){}
	    			try{if (rs!= null) rs.close();} catch (Exception e){}
	    		}
	    		
	    		return res;
	    	}
	        
	        public static List<Ext_created_object> get_created_objects (Long record_id)
	        {
	        	List<Ext_created_object> res = new ArrayList<Ext_created_object>();
	        	Connection c = null;
	        	PreparedStatement ps = null;
	        	ResultSet rs = null;
	        	try
	        	{
	        		c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("select t.id, t.in_record_id, " +
	        				"t.branch, t.deal_group_id, t.obj_id, t.v_date " +
	        				"from ext_created_objects t where t.in_record_id = ?");
	        		ps.setLong(1, record_id);
	        		rs = ps.executeQuery();
	        		while(rs.next())
	        		{
	        			res.add(new Ext_created_object(
	        					rs.getLong("id"), 
	        					rs.getLong("in_record_id"), 
	        					rs.getString("branch"), 
	        					rs.getLong("deal_group_id"), 
	        					rs.getLong("obj_id"), 
	        					rs.getDate("v_date")
	        					));
	        		}
	        	}
	        	catch(SQLException e)
	        	{
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        	}
	        	finally
	        	{
	        		try{if(rs!=null)rs.close();}catch(Exception e){}
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        	return res;
	        }
	        public static String get_deal_group_name(Long id)
	        {
	        	String res = "";
	        	Connection c = null;
	        	PreparedStatement ps = null;
	        	ResultSet rs = null;
	        	try
	        	{
	        		c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("select t.name from deal_group t " +
	        				"where t.id = ?");
	        		ps.setLong(1, id);
	        		rs = ps.executeQuery();
	        		rs.next();
	        		res = rs.getString("name");
	        	}
	        	catch(SQLException e)
	        	{
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        	}
	        	finally
	        	{
	        		try{if(rs!=null)rs.close();}catch(Exception e){}
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        	return res;
	        }
	        
	        public static List<RefData> getExt_files(Long fr_file_id)
	        {
	        	return getRefData(
	        			"select t.id data, t.file_name label from ext_in_files t where t.fr_id = "+
	        			fr_file_id);
	        }
	        
	        public static List<RefData> getRefData(String sql)
	        {
	          List list = new LinkedList();
	          Connection c = null;
	          try
	          {
	            c = ConnectionPool.getConnection();
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(sql);
	            while (rs.next())
	              list.add(
	                new RefData(rs.getString("data"), 
	                rs.getString("label")));
	          }
	          catch (SQLException e) {
	            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	          } finally {
	            ConnectionPool.close(c);
	          }
	          return list;
	        }
	        
	        public static long get_file_deal_id_from_file(long in_file_id)
	        {
	        	long res = 0;
	        	
	        	Connection c = null;
	        	PreparedStatement ps = null;
	        	ResultSet rs = null;
	        	try
	        	{
	        		c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("select ft.deal_group_id from ss_ext_file_types ft " +
	        				"where ft.id = (select t.file_type_id from ext_in_files t where t.id = ?)");
	        		ps.setLong(1, in_file_id);
	        		rs = ps.executeQuery();
	        		rs.next();
	        		res = rs.getLong("deal_group_id");
	        	}
	        	catch(SQLException e)
	        	{
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        	}
	        	finally
	        	{
	        		try{if(rs!=null)rs.close();}catch(Exception e){}
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        	return res;
	        }
	        
	        public static boolean remove_file(long in_file_id)
	        {
	        	Connection c = null;
	        	PreparedStatement ps = null;
	        	try
	        	{
	        		c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("update ext_in_files t set t.state_id = 3 where t.id = ?");
	        		ps.setLong(1, in_file_id);
	        		ps.executeQuery();
	        		c.commit();
	        		return true;
	        	}
	        	catch(SQLException e)
	        	{
	        		try{if(c!=null)c.rollback();}catch(Exception e1s){}
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        		return false;
	        	}
	        	finally
	        	{
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        }
	        
	        public static long get_ext_file_state(long in_file_id)
	        {
	        	long res = 0;
	        	
	        	Connection c = null;
	        	PreparedStatement ps = null;
	        	ResultSet rs = null;
	        	try
	        	{
	        		c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("select t.state_id from ext_in_files t where t.id = ? and t.file_type_id = 12");
	        		ps.setLong(1, in_file_id);
	        		rs = ps.executeQuery();
	        		if (rs.next())
	        		res = rs.getLong("state_id");
	        		else return -1;
	        	}
	        	catch(SQLException e)
	        	{
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        	}
	        	finally
	        	{
	        		try{if(rs!=null)rs.close();}catch(Exception e){}
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        	return res;
	        }
	        
	        public static String get_module(Long deal_group)
	        {
	        	String res = null;
	        	
	        	Connection c = null;
	        	PreparedStatement ps = null;
	        	ResultSet rs = null;
	        	try
	        	{
	        		c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("select m.mname from bf_ui_deal_group_modules t, bf_modules m where t.deal_group_id = ? and m.id = t.bf_module_id");
	        		ps.setLong(1, deal_group);
	        		rs = ps.executeQuery();
	        		if (rs.next())
	        		res = rs.getString("mname");
	        	}
	        	catch(SQLException e)
	        	{
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        	}
	        	finally
	        	{
	        		try{if(rs!=null)rs.close();}catch(Exception e){}
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        	return res;
	        }
	        
	        public static long get_created_object_id(Connection c) throws SQLException
	    	{
	    		long res = 0;
	    		PreparedStatement ps = c.prepareStatement("select seq_ext_create_objects.nextval res from dual");
	    		ResultSet rs = ps.executeQuery();
	    		rs.next();
	    		res = rs.getLong("res");
	    		try{if (ps != null) ps.close();}catch(Exception e){}
	    		try{if (rs != null) rs.close();}catch(Exception e){}
	    		return res;
	    	}
	        
	        public static void documents_action(Long Ext_file_in_id, Long action_id, Connection c) throws Exception
	        {
	        	//Connection c = null;
	        	PreparedStatement ps = null;
	        	PreparedStatement ps_paydocs = null;
	        	ResultSet rs = null;
	        	ResultSet rs_paydocs = null;
	        	Statement st = null;
	        	CallableStatement cs = null;
	        	HashMap<String, Object> initialised_statements = null;
	        	try
	        	{
	        		//c = ConnectionPool.getConnection();
	        		ps = c.prepareStatement("select distinct pay_id, rec_id from "+
"(select pd.pay_id, rc.id rec_id from bf_tr_paydocs pd, ext_created_objects obj, ext_in_file_records rc where rc.id = obj.in_record_id and rc.in_file_id = ? "+
"and pd.pay_id = obj.obj_id order by pd.id)");
	        		
	        		ps_paydocs = c.prepareStatement("select pd.* from bf_tr_paydocs pd, bf_tr_pay pay, ext_created_objects o, ext_in_file_records t "+
"where o.in_record_id = t.id and t.in_file_id = ? and o.deal_group_id = 185 and pay.id = o.obj_id and pd.pay_id = pay.id "+
"order by pd.id ");
	        		ps_paydocs.setLong(1, Ext_file_in_id);
	        		rs_paydocs = ps_paydocs.executeQuery();
	        		
	        		
	        		ps.setLong(1, Ext_file_in_id);
	        		rs = ps.executeQuery();
	        		
	        		st = c.createStatement();
			        st.executeUpdate("alter session set nls_date_format='dd.mm.yyyy'");
			        st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA="+ConnectionPool.getValue("HO_SCHEMA"));
			        cs = c.prepareCall("{ call info.init() }");
			        cs.execute();
	        		PreparedStatement ps_update = null;
	        		ps_update = c.prepareStatement("insert into ext_created_objects (id, in_record_id, branch, deal_group_id, obj_id, v_date) " +
							"values (" +
							"?, ?, ?, ?, ?, systimestamp)");
					
	        		initialised_statements = 
	        			accounting_transaction.Service.init_before_action_general_doc(c);
	        		TransactionService ts = new TransactionService();
	        		ts.init(c);
	        		
	        		accounting_transaction.Service.action_general_doc(action_id, c, initialised_statements, rs_paydocs);
/*	        		
	        		while (rs.next())
	        		{
	        			long tmpl_id = rs.getLong("pay_id");
	        			System.out.println("Pay_id: "+rs.getLong("pay_id"));
	        			try
	        			{
	        			//	List<General> out_docs = ts.input_general_documents_in_operation(
	        			//			tmpl_id, c, initialised_statements);
	        				
	        				List<General> out_docs = accounting_transaction.Service.action_general_doc(tmpl_id, 
	        						action_id, c, initialised_statements);
	        				
	        				for(General gen : out_docs)
	        				{
								ps_update.setLong(1, get_created_object_id(c));
								ps_update.setLong(2, rs.getLong("rec_id"));//in_record_id,
								ps_update.setString(3, gen.getBranch());//branch,
								ps_update.setLong(4, gen.getDeal_group_id());
								ps_update.setLong(5, gen.getId());//obj_id,
								ps_update.execute();
	        				}
	        			}
	        			catch(Exception e)
	        			{
	        				put_protocol(//c, 
	        						tmpl_id, e.getMessage(), 33);
	        			//	if (!(e.getMessage().contains("ORA-20000")))
	        				{
	        					throw e;
	        				}
	        			}
	        			
	        		}*/
	        		
	        		ps_update.close();
	        		ps_update = null;
	        		if (action_id == 1l)
	        			ps_update = c.prepareStatement("update ext_in_files t set t.state_id = 3 where t.id = ?");
	        		if (action_id == 2l)
	        			ps_update = c.prepareStatement("update ext_in_files t set t.state_id = 4 where t.id = ?");
	        		if (action_id == 3l)
	        			ps_update = c.prepareStatement("update ext_in_files t set t.state_id = 5 where t.id = ?");
	        		if (action_id == 6l)
	        			ps_update = c.prepareStatement("update ext_in_files t set t.state_id = 6 where t.id = ?");
	        		ps_update.setLong(1, Ext_file_in_id);
	        		ps_update.execute();
	        	}
	        	catch(Exception e)
	        	{
//	        		try{if(c!=null)c.rollback();}catch(Exception e1){}
	        		com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	        		throw e;
	        	}
	        	finally
	        	{
	        		try{if(initialised_statements!=null)
	        		accounting_transaction.Service.close_afer_action_general_doc(
	        				initialised_statements);
	        		}catch(Exception e){}
	        		try{if(rs!=null)rs.close();}catch(Exception e){}
	        		try{if(ps!=null)ps.close();}catch(Exception e){}
	        		try{if(rs_paydocs!=null)rs_paydocs.close();}catch(Exception e){}
	        		try{if(ps_paydocs!=null)ps_paydocs.close();}catch(Exception e){}
	        //		try{if(c!=null)c.close();}catch(Exception e){}
	        	}
	        }
	        
	        public static void put_protocol(Connection c, long object_id, String message, long message_id)
	        {
	     	   CallableStatement cs = null;
	     	try
	     	{
	     		cs = c.prepareCall("{call proc_external_file.put_protocol(?, ?, ?)}");
	     		cs.setLong(1, object_id);
	     		cs.setString(2, message);
	     		cs.setLong(3, message_id);
	     		cs.execute();
	     	}
	     	catch (SQLException e)
	     	{
	     		com.is.LtLogger.getLogger().error(e.getStackTrace());
	     		e.printStackTrace();
	     	}
	     	finally
	     	{
	     		try{if(cs!=null)cs.close();}catch(Exception e){}
	     	}
	        }
	        public static void put_protocol(long object_id, String message, long message_id)
	        {
	     	   Connection c = null;
	     	   try
	     	   {
	     		   c = ConnectionPool.getConnection();
	     		   put_protocol(c, object_id, message, message_id);
	     		   c.commit();
	     	   }
	     	   catch (Exception e)
	     	   {
	     		  com.is.LtLogger.getLogger().error(e.getStackTrace());
	     			e.printStackTrace();
	     	   }
	     	   finally
	     	   {
	     		   try{if(c!=null)c.close();}catch(Exception e){}
	     	   }
	        }
	        
	        public static void fill_02_005_pre(Long ext_in_file_id, Connection c ) throws SQLException
	        {
	        	PreparedStatement ps = null;
	        	try
	        	{
	        		ps = c.prepareStatement(
	        				"insert into ui_02_005_pre " +
							"select t.branch, trunc(t.v_date) v_date, 34 parent_group_id, " +
							"acc22618prep.str_value account, " +
							"code_str_prep.str_value code_str, " +
							"vid_usl_prep.str_value vid_usl, " +
							"cod_pay_prep.str_value cod_pay, " +
							"pd.summa,  " +
							"kod_mcc_prep.str_value kod_mcc, " +
							"pd.g_docid general_id " +
							"from ext_created_objects t, ext_in_file_records fr, bf_tr_paydocs pd, " +
							"(select ct.code, fr.id record_id, ct.id col_id, fr.id " +
							          "REGEXP_SUBSTR(replace(fr.rec_value,';;','; ;'), tr.parser_regexp, 1, rc.index_position ) str_value " + 
							      "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_column_types ct, ss_ext_record_cols rc  " +
							     "where fr.record_type_id = tr.id  " +
							       "and fr.record_type_id = rc.record_type_id " + 
							       "and rc.column_id      = ct.id " +
							       "and ct.code = '22618') acc22618prep, " +
							"(select ct.code, fr.id record_id, ct.id col_id, fr.id " +
							          ",REGEXP_SUBSTR(replace(fr.rec_value,';;','; ;'), tr.parser_regexp, 1, rc.index_position ) str_value " + 
							      "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_column_types ct, ss_ext_record_cols rc  " +
							     "where fr.record_type_id = tr.id  " +
							       "and fr.record_type_id = rc.record_type_id " + 
							       "and rc.column_id      = ct.id " +
							       "and ct.code = 'COUNTRY') code_str_prep, " +
							"(select ct.code, fr.id record_id, ct.id col_id, fr.id " +
							          ",REGEXP_SUBSTR(replace(fr.rec_value,';;','; ;'), tr.parser_regexp, 1, rc.index_position ) str_value " + 
							      "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_column_types ct, ss_ext_record_cols rc  " +
							     "where fr.record_type_id = tr.id  " +
							       "and fr.record_type_id = rc.record_type_id " + 
							       "and rc.column_id      = ct.id " +
							       "and ct.code = 'KOD VIDA USLUG') vid_usl_prep, " +
							"(select ct.code, fr.id record_id, ct.id col_id, fr.id " +
							          ",REGEXP_SUBSTR(replace(fr.rec_value,';;','; ;'), tr.parser_regexp, 1, rc.index_position ) str_value " + 
							      "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_column_types ct, ss_ext_record_cols rc  " +
							     "where fr.record_type_id = tr.id  " +
							       "and fr.record_type_id = rc.record_type_id " +
							       "and rc.column_id      = ct.id " +
							       "and ct.code = 'PRIZNAK') cod_pay_prep, " +
							"(select ct.code, fr.id record_id, ct.id col_id, fr.id " +
							          ",REGEXP_SUBSTR(replace(fr.rec_value,';;','; ;'), tr.parser_regexp, 1, rc.index_position ) str_value " + 
							      "from ext_in_file_records fr, ss_ext_record_types tr, ss_ext_column_types ct, ss_ext_record_cols rc  " +
							     "where fr.record_type_id = tr.id  " +
							       "and fr.record_type_id = rc.record_type_id " + 
							       "and rc.column_id      = ct.id " +
							       "and ct.code = 'MCC') kod_mcc_prep " +
							"where t.deal_group_id = 185 and t.in_record_id = fr.id and fr.in_file_id = ? " + 
							"and pd.pay_id = t.obj_id and pd.g_docid is not null " +
							"and acc22618prep.id = fr.id " +
							"and code_str_prep.id = fr.id " +
							"and vid_usl_prep.id = fr.id " +
							"and cod_pay_prep.id = fr.id " +
							"and kod_mcc_prep.id = fr.id;"
	        				);
	        		ps.setLong(1, ext_in_file_id);
	        		ps.executeQuery();
	        	}
	        	catch(SQLException e)
	        	{
	        		throw e;
	        	}
	        }
}
