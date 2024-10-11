package com.is.nibbd_liquidation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;



import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;


public class LiqService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? order by id_fl";
	private static String msql = "select * from v_liquidation_files ";
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private static List<RefData> typesNotify;		
	private static List<RefData> listCloseTypes;
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(FilterRecord filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch like ?", filter
					.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getDate_time())) {
			//flfields.add(new FilterField(getCond(flfields) + "trunc(date_time)>=to_date(?,'dd.mm.yyyy')", filter.getDate_time()));
			flfields.add(new FilterField(getCond(flfields) + "trunc(date_time)>=?", sqlDate(filter.getDate_time())));
		}
		if (!CheckNull.isEmpty(filter.getDate_time1())) {
			//flfields.add(new FilterField(getCond(flfields) + "trunc(date_time)<=to_date(?,'dd.mm.yyyy')", filter.getDate_time1()));
			flfields.add(new FilterField(getCond(flfields) + "trunc(date_time)<=?", sqlDate(filter.getDate_time1())));
		}
	    if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "name like ?",
					filter.getName()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	public static int getCount(FilterRecord filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_liquidation_files ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();
			
			/*
            for (params = 0; params < flFields.size(); params++) {
                Object obj = flFields.get(params).getColobject();
                if (obj instanceof java.util.Date) {
                    ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
                    continue;
                }
                if (obj instanceof String) {
                    //ps.setString(params + 1, ((String) obj).toUpperCase());
                	ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
                    continue;
                }
                ps.setObject(params + 1, flFields.get(params).getColobject());
            }
			rs = ps.executeQuery(); 
			*/
			
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<GeneralRecord> getRecordsFl(int pageIndex, int pageSize,
			FilterRecord filter, String alias) {

		List<GeneralRecord> list = new ArrayList<GeneralRecord>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GeneralRecord tmp = new GeneralRecord();
    			tmp.setBranch(rs.getString("branch"));
    			tmp.setCode_country(rs.getString("code_country"));
    			tmp.setCode_notification(rs.getString("code_notification"));
    			tmp.setDate_reason(rs.getString("date_reason"));
    			tmp.setDate_time(rs.getString("date_time"));
    			tmp.setFio(rs.getString("fio"));
    			tmp.setFr_file_id(rs.getString("fr_file_id"));
    			tmp.setId_dtl(rs.getString("id_dtl"));
    			tmp.setId_fl(rs.getString("id_fl"));
    			tmp.setId_fr_file(rs.getString("id_fr_file"));
    			tmp.setId_line(rs.getString("id_line"));
    			tmp.setId_notification(rs.getString("id_notification"));
    			tmp.setInn_liquidator_jur(rs.getString("inn_liquidator_jur"));
    			tmp.setInn_subject(rs.getString("inn_subject"));
    			tmp.setLine_id(rs.getString("line_id"));
    			tmp.setName(rs.getString("name"));
    			tmp.setNotify_file_id(rs.getString("notify_file_id"));
    			tmp.setNotify_line_id(rs.getString("notify_line_id"));
    			tmp.setPasport_ser_num(rs.getString("pasport_ser_num"));
    			tmp.setPinfl_liquidator_phys(rs.getString("pinfl_liquidator_phys"));
    			tmp.setReason(rs.getString("reason"));
    			tmp.setSsilka(rs.getString("ssilka"));
    			tmp.setText_notification(rs.getString("text_notification"));
    			tmp.setTime_notification(rs.getString("time_notification"));
				list.add(tmp);
			}
		} catch (SQLException e) {
			ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}
	
	public static Res doAction_close(final String un, final String pw,
			final String branch, final String id, String close_type, String closed_doc_n, String closed_doc_d, 
			final String alias) {
		Res res = null;
		Connection c = null;
		
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(un, pw, alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ps = c.prepareStatement("SELECT * FROM v_client WHERE branch=? and id_client=?");
			ps.setString(1, branch);
			ps.setString(2, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				ccs.execute();
				//v_client dagi parametrlarni param.setparam qilamiz
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
					final String cn = rs.getMetaData().getColumnName(i);
					ISLogger.getLogger().error(cn);
					if (rs.getString(cn) != null) {
						cs.setString(1, cn);
						if (rs.getMetaData().getColumnTypeName(i)
								.equals("DATE")) {
							cs.setString(2, df.format(rs.getDate(cn)));
							ISLogger.getLogger().error(cn+" : : "+df.format(rs.getDate(cn)));
						} else {
							cs.setString(2, rs.getString(cn));
							ISLogger.getLogger().error(cn+" : "+rs.getString(cn));
						}
						cs.execute();
					}
				}

				//qushimcha parametrlar
				cs.setString(1, "P_CAPACITY_STATUS_PLACE");
				cs.setString(2, close_type);
				cs.execute();
				
				cs.setString(1, "P_NUM_CERTIF_CAPACITY");
				cs.setString(2, closed_doc_n);
				cs.execute();
				
				cs.setString(1, "P_CAPACITY_STATUS_DATE");
				cs.setString(2, closed_doc_d);
				cs.execute();

				
				acs.setInt(1, 1);
				acs.setInt(2, rs.getInt("SIGN_REGISTR"));
				acs.setInt(3, 3);
				acs.execute();
				c.commit();
				//c.rollback();
				res = new Res(0, id);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error((Object) CheckNull.getPstr(e));
			
			res = new Res(-1, e.getMessage());
			return res;
		} finally {
			utils_close(acs);
			utils_close(rs);
			utils_close(ps);
			utils_close(ccs);
			utils_close(cs);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return res;
	}
	
	
	  public static void utils_close(final CallableStatement cs) {
	        try {
	            if (cs != null) {
	                cs.close();
	            }
	        }
	        catch (SQLException e) {
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	        }
	    }
	    
	  public static void utils_close(final PreparedStatement ps) {
	        try {
	            if (ps != null) {
	                ps.close();
	            }
	        }
	        catch (SQLException e) {
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	        }
	    }
	    
	    public static void utils_close(final Statement st) {
	        try {
	            if (st != null) {
	                st.close();
	            }
	        }
	        catch (SQLException e) {
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	        }
	    }
	    
	    public static void utils_close(final ResultSet rs) {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        }
	        catch (SQLException e) {
	            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
	        }
	    }


	public static String getFileName(Long file_id, String alias) {

		Connection c = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select file_name_arc from nibbd_notification_file t where id=?");
		String rr="";
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setLong(1, file_id);
			ResultSet rs = ps.executeQuery();
            if (rs.next()) {
				rr = rs.getString(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return rr;
	}	
	
	public static Customer getClientInfo(String branch, String inn, String alias) {
		//inn buyicha klient haqida malumot olish, status ni olish
		
		Connection c = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select c.id_client, c.state, c.name from client c, client_j cj where c.branch=cj.branch and c.id_client=cj.id and cj.branch=? and cj.number_tax_registration=?");
		Customer rr = new Customer();
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());
            ps.setString(1, branch);
            ps.setString(2, inn);
			ResultSet rs = ps.executeQuery();
            if (rs.next()) {
				rr.setId(rs.getString(1));
				rr.setState(rs.getString(2));
				rr.setName(rs.getString(3));
			}
		} catch (SQLException e) {
			ISLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		return rr;
	}	
	
	public static List<RefData> getClose_types(final String alias) {
		if (listCloseTypes==null || listCloseTypes.size()==0){
			Connection c = null;    
	    	try {
	    		c = ConnectionPool.getConnection();
	    		listCloseTypes = (List<RefData>) getRefData(c,	"select close_type_id data, close_type_name label from ss_spr_204 order by 1");
	    	} catch (SQLException var8) {
	    		ISLogger.getLogger().error("var8: "+var8.getMessage());
	            var8.printStackTrace();
	    		ISLogger.getLogger().error("var8: "+var8.getCause());
	        } finally {
	            ConnectionPool.close(c);
	        }			
		}
		return listCloseTypes;
	}
	
	public static List<GeneralRecord> getReport1(Date dateFrom, Date dateTo, String branch) {
	    
    	List<GeneralRecord> list = new ArrayList();
    	//List<RefData> listB = new ArrayList();
        Connection c = null;    
        PreparedStatement ps=null;
        ResultSet rs = null;
        String sql1 ="";
    	try {
    		c = ConnectionPool.getConnection();
    	
    		sql1 = "select dtl.id id_dtl, dtl.notify_line_id, dtl.id_notification, dtl.date_reason, dtl.reason, dtl.inn_liquidator_jur, dtl.inn_subject, dtl.code_country, dtl.pinfl_liquidator_phys, dtl.pasport_ser_num, dtl.ssilka, dtl.fio, "+
    		       "line.id id_line, line.notify_file_id, line.line_id, line.code_notification, line.text_notification, line.time_notification, "+
    		       "fl.id id_fl, fl.fr_file_id, fl.branch, fr_file.id id_fr_file, fr_file.name, fr_file.date_time from nibbd_notify_line_dtl dtl, nibbd_notify_line line, nibbd_notify_file fl, fr_file "+
                   "where dtl.notify_line_id(+)=line.id and line.notify_file_id(+)=fl.id "+
                   "and fl.fr_file_id=fr_file.id and fl.branch like ? and trunc(fr_file.date_time) between to_date(?,'dd.mm.yyyy') and to_date(?,'dd.mm.yyyy')";
    		ps = c.prepareStatement(sql1);

    		ps.setString(1, branch);
    		ps.setString(2, df.format(dateFrom));
    		ps.setString(3, df.format(dateTo));
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			GeneralRecord tmp = new GeneralRecord();
    			
    			tmp.setBranch(rs.getString("branch"));
    			tmp.setCode_country(rs.getString("code_country"));
    			tmp.setCode_notification(rs.getString("code_notification"));
    			tmp.setDate_reason(rs.getString("date_reason"));
    			tmp.setDate_time(rs.getString("date_time"));
    			tmp.setFio(rs.getString("fio"));
    			tmp.setFr_file_id(rs.getString("fr_file_id"));
    			tmp.setId_dtl(rs.getString("id_dtl"));
    			tmp.setId_fl(rs.getString("id_fl"));
    			tmp.setId_fr_file(rs.getString("id_fr_file"));
    			tmp.setId_line(rs.getString("id_line"));
    			tmp.setId_notification(rs.getString("id_notification"));
    			tmp.setInn_liquidator_jur(rs.getString("inn_liquidator_jur"));
    			tmp.setInn_subject(rs.getString("inn_subject"));
    			tmp.setLine_id(rs.getString("line_id"));
    			tmp.setName(rs.getString("name"));
    			tmp.setNotify_file_id(rs.getString("notify_file_id"));
    			tmp.setNotify_line_id(rs.getString("notify_line_id"));
    			tmp.setPasport_ser_num(rs.getString("pasport_ser_num"));
    			tmp.setPinfl_liquidator_phys(rs.getString("pinfl_liquidator_phys"));
    			tmp.setReason(rs.getString("reason"));
    			tmp.setSsilka(rs.getString("ssilka"));
    			tmp.setText_notification(rs.getString("text_notification"));
    			tmp.setTime_notification(rs.getString("time_notification"));
    					
    			list.add(tmp);
    		}
    	} catch (SQLException var8) {
    		ISLogger.getLogger().error("var8: "+var8.getMessage());
            var8.printStackTrace();
    		ISLogger.getLogger().error("var8: "+var8.getCause());
        } finally {
            ConnectionPool.close(c);
        }
        return list;
    }

	public static void setTypesNotify(List<RefData> typesNotify) {
		LiqService.typesNotify = typesNotify;
	}
	
	public static List<RefData> getTypesNotify() {
		if (typesNotify==null) {
			Connection c = null;    
	    	try {
	    		c = ConnectionPool.getConnection();
	    		typesNotify = (List<RefData>) getRefData(c,	"select id data, name label from ss_type_nibbd_notify order by 1");
	    	} catch (SQLException var8) {
	    		ISLogger.getLogger().error("var8: "+var8.getMessage());
	            var8.printStackTrace();
	    		ISLogger.getLogger().error("var8: "+var8.getCause());
	        } finally {
	            ConnectionPool.close(c);
	        }			
		}
		return typesNotify;
	}

	public static java.sql.Date sqlDate(java.util.Date date) {
		return date != null ? new java.sql.Date(date.getTime()) : null;
	}
	
	public static List<RefData> getRefData(Connection c,String sql)
	{
		List<RefData> list = new LinkedList<RefData>();
		PreparedStatement s = null;
		ResultSet rs = null;
		try
		{
			s = c.prepareStatement(sql);
			rs = s.executeQuery();
			while (rs.next())
				list.add(
						new RefData(rs.getString("data"),
								rs.getString("label")));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
			closeResultSet(rs);
			closeStmt(s);
		}
		return list;
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
	}
	
	public static void closeStmt(Statement st) {
		try {
			if(st != null){
				st.close();
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(e.getStackTrace());
			e.printStackTrace();
		}
	}
	

}
