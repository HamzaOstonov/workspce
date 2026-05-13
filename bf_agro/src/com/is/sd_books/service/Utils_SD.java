package com.is.sd_books.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zul.Constraint;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class Utils_SD {
	
	private static boolean bool = true;
	private static int allsize = 0;
	private static int size = 0;
	
	public static void clearForm(Object comp) {
	    Constraint ct;
	    try{
	    	if (comp instanceof org.zkoss.zul.Textbox) {
	    		ct =((org.zkoss.zul.Textbox)comp).getConstraint();
	    		((org.zkoss.zul.Textbox)comp).setConstraint("");
	    		((org.zkoss.zul.Textbox)comp).setValue(null);
	    		((org.zkoss.zul.Textbox)comp).setConstraint(ct);
	    	} else if (comp instanceof org.zkoss.zul.Intbox) {
	    		ct =((org.zkoss.zul.Intbox)comp).getConstraint();
	    		((org.zkoss.zul.Intbox)comp).setConstraint("");
	    		((org.zkoss.zul.Intbox)comp).setValue(null);
	    		((org.zkoss.zul.Intbox)comp).setConstraint(ct);
	    	} else if (comp instanceof org.zkoss.zul.Longbox) {
	    		ct =((org.zkoss.zul.Longbox)comp).getConstraint();
	    		((org.zkoss.zul.Longbox)comp).setConstraint("");
	    		((org.zkoss.zul.Longbox)comp).setValue(null);
	    		((org.zkoss.zul.Longbox)comp).setConstraint(ct);
	    	} else if (comp instanceof org.zkoss.zul.Decimalbox) {
	    		ct =((org.zkoss.zul.Decimalbox)comp).getConstraint();
	    		((org.zkoss.zul.Decimalbox)comp).setConstraint("");
	    		((org.zkoss.zul.Decimalbox)comp).setValue(BigDecimal.ZERO);
	    		((org.zkoss.zul.Decimalbox)comp).setRawValue(null);
	    		((org.zkoss.zul.Decimalbox)comp).setConstraint(ct);
	    	} else if (comp instanceof org.zkoss.zul.Datebox) {
	    		ct =((Datebox)comp).getConstraint();
	    		((org.zkoss.zul.Datebox)comp).setConstraint("");
	    		((org.zkoss.zul.Datebox)comp).setText(null);
	    		((org.zkoss.zul.Datebox)comp).setConstraint(ct);
	    	} else if (comp instanceof com.is.utils.RefCBox) {
	    		ct =((com.is.utils.RefCBox)comp).getConstraint();
	    		((com.is.utils.RefCBox)comp).setConstraint("");
	    		((com.is.utils.RefCBox)comp).setValue(null);
	    		((com.is.utils.RefCBox)comp).setConstraint(ct);
	    	} else if (comp instanceof org.zkoss.zul.Doublebox) {
		        ct =((Doublebox)comp).getConstraint();
		        ((org.zkoss.zul.Doublebox)comp).setConstraint("");
		        ((org.zkoss.zul.Doublebox)comp).setText(null);
		        ((org.zkoss.zul.Doublebox)comp).setConstraint(ct);
	    	} else if (comp instanceof org.zkoss.zul.Div) {
	    		for (Object obj : ((org.zkoss.zul.Div)comp).getChildren()) {
	    			clearForm(obj);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Hbox) {
	    		for (Object obj : ((org.zkoss.zul.Hbox)comp).getChildren()) {
	    			clearForm(obj);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Vbox) {
	    		for (Object obj : ((org.zkoss.zul.Vbox)comp).getChildren()) {
	    			clearForm(obj);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Grid) {
	    		if(((org.zkoss.zul.Grid)comp).getRows()!=null){
	    			for (Object obj : ((org.zkoss.zul.Grid)comp).getRows().getChildren()) {
	    				clearForm(obj);
	    			}
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Row) {
	    		for (Object obj : ((org.zkoss.zul.Row)comp).getChildren()) {
	    			clearForm(obj);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Cell) {
	    		for (Object obj : ((org.zkoss.zul.Cell)comp).getChildren()) {
	    			clearForm(obj);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Groupbox) {
	    		for (Object obj : ((org.zkoss.zul.Groupbox)comp).getChildren()) {
	    			clearForm(obj);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Window) {
		        for (Object obj : ((org.zkoss.zul.Window)comp).getChildren()) {
			          clearForm(obj);
		        };
	    	} else if (comp instanceof org.zkoss.zul.Rows) {
		        for (Object obj : ((org.zkoss.zul.Rows)comp).getChildren()) {
			          clearForm(obj);
		        };
	    	} else if (comp instanceof org.zkoss.zul.Tabbox) {
		        for (Object obj : ((org.zkoss.zul.Tabbox)comp).getChildren()) {
			          clearForm(obj);
		        };
	    	} else if (comp instanceof org.zkoss.zul.Tabpanel) {
		        for (Object obj : ((org.zkoss.zul.Tabpanel)comp).getChildren()) {
			          clearForm(obj);
		        };
	    	} else if (comp instanceof org.zkoss.zul.Tabpanels) {
		        for (Object obj : ((org.zkoss.zul.Tabpanels)comp).getChildren()) {
			          clearForm(obj);
		        };
	    	}
	    } catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	public static void ReadOnly(Object comp,boolean bool) {
	    try{
	    	if (comp instanceof com.is.utils.RefCBox) {
	    		((com.is.utils.RefCBox)comp).setReadonly(bool);
	    		((com.is.utils.RefCBox)comp).setButtonVisible(!bool);
	    	} else if (comp instanceof org.zkoss.zul.Intbox) {
	    		((org.zkoss.zul.Intbox)comp).setReadonly(bool);
	    	} else if (comp instanceof org.zkoss.zul.Longbox) {
	    		((org.zkoss.zul.Longbox)comp).setReadonly(bool);
	    	} else if (comp instanceof org.zkoss.zul.Decimalbox) {
	    		((org.zkoss.zul.Decimalbox)comp).setReadonly(bool);
	    	} else if (comp instanceof org.zkoss.zul.Datebox) {
	    		((org.zkoss.zul.Datebox)comp).setReadonly(bool);
	    		((org.zkoss.zul.Datebox)comp).setButtonVisible(!bool);
	    	} else if (comp instanceof org.zkoss.zul.Doublebox) {
		        ((org.zkoss.zul.Doublebox)comp).setReadonly(bool);
	    	} else if (comp instanceof org.zkoss.zul.Textbox) {
	    		((org.zkoss.zul.Textbox)comp).setReadonly(bool);
	    	} else if (comp instanceof org.zkoss.zul.Div) {
	    		for (Object obj : ((org.zkoss.zul.Div)comp).getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Hbox) {
	    		for (Object obj : ((org.zkoss.zul.Hbox)comp).getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Vbox) {
	    		for (Object obj : ((org.zkoss.zul.Vbox)comp).getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Grid) {
	    		for (Object obj : ((org.zkoss.zul.Grid)comp).getRows().getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Row) {
	    		for (Object obj : ((org.zkoss.zul.Row)comp).getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Cell) {
	    		for (Object obj : ((org.zkoss.zul.Cell)comp).getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Groupbox) {
	    		for (Object obj : ((org.zkoss.zul.Groupbox)comp).getChildren()) {
	    			ReadOnly(obj,bool);
	    		};
	    	} else if (comp instanceof org.zkoss.zul.Window) {
		        for (Object obj : ((org.zkoss.zul.Window)comp).getChildren()) {
		        	ReadOnly(obj,bool);
		        };
	    	}
	    } catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("finally")
	public static boolean filledFields(Object comp) {
	    try{
	    	if (comp instanceof com.is.utils.RefCBox) {
	    		if(((com.is.utils.RefCBox)comp).getValue().equals("")){
	    			((com.is.utils.RefCBox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Textbox) {
	    		if(((org.zkoss.zul.Textbox)comp).getValue().equals("")){
	    			((org.zkoss.zul.Textbox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Intbox) {
	    		if(((org.zkoss.zul.Intbox)comp).getValue()==null){
	    			((org.zkoss.zul.Intbox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Longbox) {
	    		if(((org.zkoss.zul.Longbox)comp).getValue()==null){
	    			((org.zkoss.zul.Longbox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Decimalbox) {
	    		if(((org.zkoss.zul.Decimalbox)comp).getValue()==null){
	    			((org.zkoss.zul.Decimalbox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Datebox) {
	    		if(((org.zkoss.zul.Datebox)comp).getValue()==null){
	    			((org.zkoss.zul.Datebox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Doublebox) {
	    		if(((org.zkoss.zul.Doublebox)comp).getValue()==null){
	    			((org.zkoss.zul.Doublebox)comp).focus();
	    			bool = false;
	    			return bool;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Div) {
	    		for (Object obj : ((org.zkoss.zul.Div)comp).getChildren()) {
	    				filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Hbox) {
	    		for (Object obj : ((org.zkoss.zul.Hbox)comp).getChildren()) {
		    			filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Vbox) {
	    		for (Object obj : ((org.zkoss.zul.Vbox)comp).getChildren()) {
		    			filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Grid) {
	    		for (Object obj : ((org.zkoss.zul.Grid)comp).getRows().getChildren()) {
		    			filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Row) {
	    		for (Object obj : ((org.zkoss.zul.Row)comp).getChildren()) {
		    			filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Cell) {
	    		for (Object obj : ((org.zkoss.zul.Cell)comp).getChildren()) {
		    			filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Groupbox) {
	    		for (Object obj : ((org.zkoss.zul.Groupbox)comp).getChildren()) {
		    			filledFields(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Window) {
		        for (Object obj : ((org.zkoss.zul.Window)comp).getChildren()) {
			        	filledFields(obj);
	    		}
	    	} 
	    } catch(Exception e){
	    	bool = false;
	    	e.printStackTrace();
	    } finally {
	    	boolean temp = bool;
	    	return temp;
	    }
	}
	
	public static Date getInfoDate(String alias,Res res){
		Date date = new Date();
		Connection c = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetDay from dual");
			if(rs.next()){
				date = rs.getDate(1);
			}
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils_SD.close(rs);
			Utils_SD.close(st);
			Utils_SD.close(cs);
			ConnectionPool.close(c);
		}
		return date;
	}
	
	public static String getInfoEmp(String alias,Res res){
		String emp = null;
		Connection c = null;
		ResultSet rs = null;
		Statement st = null;
		CallableStatement cs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			rs = st.executeQuery("select info.GetEmpId from dual");
			if(rs.next()){
				emp = rs.getString(1);
			}
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils_SD.close(rs);
			Utils_SD.close(st);
			Utils_SD.close(cs);
			ConnectionPool.close(c);
		}
		return emp;
	}
	
	@SuppressWarnings("finally")
	public static boolean isEmpty(Object comp) {
	    try{
	    	if (comp instanceof com.is.utils.RefCBox) {
	    		allsize++;
	    		if(((com.is.utils.RefCBox)comp).getValue().equals("")){
	    			((com.is.utils.RefCBox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Textbox) {
	    		allsize++;
	    		if(((org.zkoss.zul.Textbox)comp).getValue().equals("")){
	    			((org.zkoss.zul.Textbox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Intbox) {
	    		allsize++;
	    		if(((org.zkoss.zul.Intbox)comp).getValue()==null){
	    			((org.zkoss.zul.Intbox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Longbox) {
	    		allsize++;
	    		if(((org.zkoss.zul.Longbox)comp).getValue()==null){
	    			((org.zkoss.zul.Longbox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Decimalbox) {
	    		allsize++;
	    		if(((org.zkoss.zul.Decimalbox)comp).getValue()==null){
	    			((org.zkoss.zul.Decimalbox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Datebox) {
	    		allsize++;
	    		if(((org.zkoss.zul.Datebox)comp).getValue()==null){
	    			((org.zkoss.zul.Datebox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Doublebox) {
	    		allsize++;
	    		if(((org.zkoss.zul.Doublebox)comp).getValue()==null){
	    			((org.zkoss.zul.Doublebox)comp).focus();
	    			size++;
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Div) {
	    		for (Object obj : ((org.zkoss.zul.Div)comp).getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Hbox) {
	    		for (Object obj : ((org.zkoss.zul.Hbox)comp).getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Vbox) {
	    		for (Object obj : ((org.zkoss.zul.Vbox)comp).getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Grid) {
	    		for (Object obj : ((org.zkoss.zul.Grid)comp).getRows().getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Row) {
	    		for (Object obj : ((org.zkoss.zul.Row)comp).getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Cell) {
	    		for (Object obj : ((org.zkoss.zul.Cell)comp).getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Groupbox) {
	    		for (Object obj : ((org.zkoss.zul.Groupbox)comp).getChildren()) {
	    			isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Window) {
		        for (Object obj : ((org.zkoss.zul.Window)comp).getChildren()) {
			        	isEmpty(obj);
	    		}
	    	} else if (comp instanceof org.zkoss.zul.Rows){
	    		for (Object obj : ((org.zkoss.zul.Rows)comp).getChildren()) {
	    			isEmpty(obj);
    		}
	    	}
	    } catch(Exception e){
	    	e.printStackTrace();
	    } finally {
	    	boolean temp = false;
	    	if(size==allsize){
	    		temp = true;
	    	}
	    	return temp;
	    }
	}
	
	public static java.sql.Date parseUtilToSqlDate(Date date){
		return date==null?null:new java.sql.Date(date.getTime());
	}
	
	public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }
    }
    
    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }
    }
    
    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }
    }
    
    public static void close(CallableStatement cs) {
        try {
            if (cs != null) {
                cs.close();
            }
        } catch (SQLException e) {
            com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
        }
    }
    
    public static List<RefData> getRefData(String sql, String branch){
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try{
			if(branch!=null&&!branch.equals("")){
				c = ConnectionPool.getConnection(branch);
			} else {
				c = ConnectionPool.getConnection();
			}
			s = c.createStatement();
			rs = s.executeQuery(sql);
			System.out.println(sql);
			while (rs.next())
				list.add(
						new RefData(
								rs.getString(1),
									rs.getString(2)));
		} catch (SQLException e) {
			com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			close(rs);
			close(s);
			ConnectionPool.close(c);
		}
		return list;
	}
  
    public static String getData(String sql,String alias){
    	String data = "";
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		if(alias.equals("")){
    			c = ConnectionPool.getConnection();
    		} else {
    			c = ConnectionPool.getConnection(alias);
    		}
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			close(ps);
			close(rs);
			ConnectionPool.close(c);
		}
    	return data;
    }
    
    public static String getData(String sql, String alias, Connection c){
    	String data = "";
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		if(c == null){
    			if(alias==null || alias.equals(""))
    				c = ConnectionPool.getConnection();
    			else c = ConnectionPool.getConnection(alias);
    		}
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				data = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			close(ps);
			close(rs);
		}
    	return data;
    }
    
    public static void rollback(Connection c){
    	try {
			if(c!=null){
				c.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
    }
    
    public static String getAlias(String branch){
		String alias = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select user_name from ss_dblink_branch where branch=?");
			ps.setString(1, branch);
			rs = ps.executeQuery();
			if(rs.next()) alias = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
		return alias;
	}
    
	@SuppressWarnings("unchecked")
	public static void clearRows(Rows rows){
		List<Row> r_rows = rows.getChildren();
		int r_size = r_rows.size()-1;
		for (int i = r_size; i >= 0; i--) {
			rows.removeChild(r_rows.get(i));
		}
	}

	public static boolean isBool() {
		return bool;
	}

	public static void setBool(boolean bool) {
		Utils_SD.bool = bool;
	}

	public static int getAllsize() {
		return allsize;
	}

	public static void setAllsize(int allsize) {
		Utils_SD.allsize = allsize;
	}

	public static int getSize() {
		return size;
	}

	public static void setSize(int size) {
		Utils_SD.size = size;
	}
	
}
