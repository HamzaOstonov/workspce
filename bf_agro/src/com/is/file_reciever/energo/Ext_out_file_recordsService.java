package com.is.file_reciever.energo;

	import java.util.ArrayList;
import java.util.LinkedList;
	import java.util.List;
import java.sql.*;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

	public class Ext_out_file_recordsService {

	        private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	        private static String psql2 =" ) s ) t where rownum <= ? ) t  where t.rwnm >= ?";
	        private static String msql ="SELECT * FROM Ext_out_file_records ";


	        public List<Ext_out_file_records> getExt_out_file_records()  {

	                List<Ext_out_file_records> list = new ArrayList<Ext_out_file_records>();
	                Connection c = null;

	                try {
	                        c = ConnectionPool.getConnection();
	                        Statement s = c.createStatement();
	                        ResultSet rs = s.executeQuery("SELECT * FROM Ext_out_file_records");
	                        while (rs.next()) {
	                                list.add(new Ext_out_file_records(
	                                                rs.getLong("id"),
	                                                rs.getLong("out_file_id"),
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

	        private static List<FilterField> getFilterFields(Ext_out_file_recordsFilter filter){
	                List<FilterField> flfields = new ArrayList<FilterField>();


	              if(!CheckNull.isEmpty(filter.getId())){
	                      flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
	              }
	              if(!CheckNull.isEmpty(filter.getOut_file_id())){
	                      flfields.add(new FilterField(getCond(flfields)+ "out_file_id=?",filter.getOut_file_id()));
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


	        public static int getCount(Ext_out_file_recordsFilter filter)  {

	            Connection c = null;
	            int n = 0;
	            List<FilterField> flFields =getFilterFields(filter);
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT count(*) ct FROM Ext_out_file_records ");
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



	        public static List<Ext_out_file_records> getExt_out_file_recordssFl(int pageIndex, int pageSize, Ext_out_file_recordsFilter filter)  {

	                List<Ext_out_file_records> list = new ArrayList<Ext_out_file_records>();
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
	                                list.add(new Ext_out_file_records(
	                                                rs.getLong("id"),
	                                                rs.getLong("out_file_id"),
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


	        public Ext_out_file_records getExt_out_file_records(int ext_out_file_recordsId) {

	                Ext_out_file_records ext_out_file_records = new Ext_out_file_records();
	                Connection c = null;

	                try {
	                        c = ConnectionPool.getConnection();
	                        PreparedStatement ps = c.prepareStatement("SELECT * FROM ext_out_file_records WHERE id=?");
	                        ps.setInt(1, ext_out_file_recordsId);
	                        ResultSet rs = ps.executeQuery();
	                        if (rs.next()) {
	                                ext_out_file_records = new Ext_out_file_records();
	                                
	                                ext_out_file_records.setId(rs.getLong("id"));
	                                ext_out_file_records.setOut_file_id(rs.getLong("out_file_id"));
	                                ext_out_file_records.setType_record_id(rs.getLong("record_type_id"));
	                                ext_out_file_records.setRec_value(rs.getString("rec_value"));
	                                ext_out_file_records.setError_id(rs.getLong("message_id"));
	                                ext_out_file_records.setErr_message(rs.getString("err_message"));
	                        }
	                } catch (Exception e) {
	                        e.printStackTrace();
	                } finally {
	                        ConnectionPool.close(c);
	                }
	                return ext_out_file_records;
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
                        "from ext_out_file_records fr, ss_ext_record_types tr, ss_ext_record_cols rc, " +
                        "ss_lang_base_names base_names, " +
                        "ss_ext_column_types ct " +
                        "where fr.record_type_id = tr.id " +  
                        "and fr.record_type_id = rc.record_type_id " +
                        "and rc.column_id = ct.id " +
                        "and base_names.id = ct.name_id " +
                        "and fr.id = ? " +
                        "order by fr.id, rc.record_type_id, rc.index_position");
	    			
	    	/*		ps = c.prepareStatement(
	    					"select base_names.name, "+
	    		              "REGEXP_SUBSTR(fr.rec_value, tr.parser_regexp, 1, rc.index_position ) str_value "+ 
	    		              "from ext_out_file_records fr, ss_ext_record_types tr, ss_ext_record_cols rc, "+
	    		              "ss_lang_base_names base_names "+
	    		              "where fr.type_record_id = tr.id  "+
	    		              "and fr.type_record_id = rc.type_record_id "+ 
	    		              "and base_names.id = rc.name_id "+
	    		              "and fr.id = ?  "+
	    		              "order by fr.id, rc.record_type_id, rc.index_position");*/
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
	        		ps = c.prepareStatement("select t.id, t.out_record_id, " +
	        				"t.branch, t.deal_group_id, t.obj_id, t.v_date " +
	        				"from ext_created_objects t where t.out_record_id = ?");
	        		ps.setLong(1, record_id);
	        		rs = ps.executeQuery();
	        		while(rs.next())
	        		{
	        			res.add(new Ext_created_object(
	        					rs.getLong("id"), 
	        					rs.getLong("out_record_id"), 
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
	        			"select t.id data, t.file_name label from ext_out_files t where t.fr_id = "+
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
}
