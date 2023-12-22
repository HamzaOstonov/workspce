package com.is.user.addinfo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;

public class ParameterService {
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql ="SELECT * FROM (select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*, decode(a.branch, null, 1, 0) isnew   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id) ";
	
	public List<Parameter> getParameter()  {
		List<Parameter> list = new ArrayList<Parameter>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*, decode(a.branch, null, 1, 0) isnew   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id");
			while (rs.next()) {
				list.add(new Parameter(
						rs.getString("branch"),
						rs.getLong("user_id"),
						rs.getString("param_value"),
						rs.getLong("param_group_id"),
						rs.getLong("param_id"),
						rs.getString("param_name"),
						rs.getString("param_name_en"),
						rs.getString("param_name_ru"),
						rs.getString("param_name_uz"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getInt("param_mandatory"),
						rs.getInt("isnew")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<ParameterGroup> getParametergroup()  {
		List<ParameterGroup> list = new ArrayList<ParameterGroup>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ps = c.prepareStatement("SELECT * FROM SS_USER_PARAM_GROUPS g where exists (SELECT 'x' FROM USER_ADDINFO_TEMPLATE t where g.id = t.param_group_id) order by ord");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ParameterGroup(
						rs.getLong("id"),
						rs.getString("name_en"),
						rs.getString("name_ru"),
						rs.getString("name_uz"),
						rs.getInt("is_open"),
						rs.getInt("ord")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<Parameter> getParameters(String branch, int user_id)  {
		List<Parameter> list = new ArrayList<Parameter>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select "+  
									"  decode(a.branch, null, ?, a.branch) branch,  "+  
									"  decode(a.user_id, null, ?, a.user_id) user_id,  "+  
									"  a.param_value,  "+  
									"   t.*,  "+  
									"  decode(a.branch, null, 1, 0) isnew  "+  
									"from USER_ADDINFO_TEMPLATE t,  "+  
									"    (select * from USER_ADDINFO ca where ca.branch = ? and ca.user_id = ?) a "+   
									"where   "+  
									"  a.param_group_id(+) = t.param_group_id  "+  
									"  and a.param_id(+) = t.param_id  "+  
									"order by t.param_group_id, t.param_ord");
			ps.setString(1, branch);
			ps.setLong(2, user_id);
			ps.setString(3, branch);
			ps.setLong(4, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Parameter(
						rs.getString("branch"),
						rs.getLong("user_id"),
						rs.getString("param_value"),
						rs.getLong("param_group_id"),
						rs.getLong("param_id"),
						rs.getString("param_name"),
						rs.getString("param_name_en"),
						rs.getString("param_name_ru"),
						rs.getString("param_name_uz"),
						rs.getString("param_select"),
						rs.getString("param_type"),
						rs.getString("param_mask"),
						rs.getString("param_def_value"),
						rs.getLong("param_ord"),
						rs.getInt("param_mandatory"),
						rs.getInt("isnew")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<Parameter> getParametersByGroup(List<Parameter> parameters, ParameterGroup group)  {
		List<Parameter> list = new ArrayList<Parameter>();
		for (Parameter parameter : parameters) {
			if (parameter.getParam_group_id() == group.getId()) {
				list.add(parameter);
			}
		}
		return list;
	}
	
	public static String setParamsInSQL(List<Parameter> params, String select){
		String res = select;
		res = setParamsInSQL_Param(params, res);
		res = setParamsInSQL_Sharp(params, res);
		return res;
	}
	
	public static String setParamsInSQL_Param(List<Parameter> params, String select){
		int k = 0; //позиция "PARAM.GETPARAM('"
		int m = 0; //позиция символа ',' или ')' или ' '
		int m1 = 0; //позиция символа ',' или ')' или ' '
		int l = 0; //длина строки селекта
		String cur_param=""; // наименование заменяемого параметра
		String cur_param_val=""; // значение заменяемого параметра
		String str=select; // селект с параметрами
		try{
		    l = str.length();
		    while (k<l+1){
			    k = str.indexOf("PARAM.GETPARAM('"); 
			    if (k==-1) {
			    	k=l+1;
			    } else {
			    	//Поиск окончания наименования параметра
			    	m = str.indexOf("')",k);
			    	/*
			    	m1 = str.indexOf(",",k);
			    	if ((m > m1 && m1 != -1) || m == -1) {
			    		m = m1;
			    	}
			    	m1 = str.indexOf(")",k);
			    	if ((m > m1 && m1 != -1) || m == -1) {
			    		m = m1;
			    	}
			    	*/
			    	if (m == -1) {
			    		m = l;
			    		throw new Exception("SELECT PARAMS NOT TRUE! param:"+str.substring(k+16,m)+"; SQL = "+select);
			    	}
			    	//System.out.println("*** k = "+k+" m = "+m);
			    	cur_param=str.substring(k+16,m);
			    	cur_param_val = getParamValue(params, cur_param);
			    	//System.out.println("*** '"+cur_param+"' = '"+cur_param_val+"' "+"PARAM.GETPARAM('"+cur_param+"')");
			    	str = str.replace("PARAM.GETPARAM('"+cur_param+"')", (cur_param_val==null?"null":"'"+cur_param_val+"'"));
			    	//str = str.replaceAll("PARAM.GETPARAM('"+cur_param+"')", (cur_param_val==null?"null":"'"+cur_param_val+"'"));
			    	//System.out.println("*** '"+str);
			    	/*if (cur_param_val.equals("")){
						str = "";
						k=l+1;
					}*/
			    }
		    }
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return str;
	}
	
	public static String setParamsInSQL_Sharp(List<Parameter> params, String select){
		int k = 0; //позиция символа '#'
		int m = 0; //позиция символа ',' или ')' или ' '
		int m1 = 0; //позиция символа ',' или ')' или ' '
		int l = 0; //длина строки селекта
		String cur_param=""; // наименование заменяемого параметра
		String cur_param_val=""; // значение заменяемого параметра
		String str=select; // селект с параметрами
		try{
		    l = str.length();
		    while (k<l+1){
			    k = str.indexOf("#"); 
			    if (k==-1) {
			    	k=l+1;
			    } else {
			    	//Поиск окончания наименования параметра
			    	m = str.indexOf(" ",k);
			    	m1 = str.indexOf(",",k);
			    	if ((m > m1 && m1 != -1) || m == -1) {
			    		m = m1;
			    	}
			    	m1 = str.indexOf(")",k);
			    	if ((m > m1 && m1 != -1) || m == -1) {
			    		m = m1;
			    	}
			    	if (m == -1) {
			    		m = l;
			    	}
			    	//System.out.println("*** k = "+k+" m = "+m);
			    	cur_param=str.substring(k+1,m);
			    	cur_param_val = getParamValue(params, cur_param);
			    	//System.out.println("*** "+cur_param+" = "+cur_param_val);
			    	str = str.replaceAll("#"+cur_param, (cur_param_val==null?"null":"'"+cur_param_val+"'"));
			    	/*if (cur_param_val.equals("")){
						str = "";
						k=l+1;
					}*/
			    }
		    }
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return str;
	}
	
	private static String getParamValue(List<Parameter> params, String param){
		String param_value = "";
		Parameter par = null;
		for (int i = 0; i < params.size(); i++){
			par = (Parameter) params.get(i);
			//System.out.println(par.getPar_name()+" = '"+par.getPar_value()+"'; par_found = "+param);
			if (par.getParam_name().equals(param)){
				if (par.getParam_value()!=null){
					param_value = par.getParam_value();
				}
			}
		}
		return param_value;
	}
	
	public static List<RefData> getListForCombobox(String str, String alias)  {

        List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        try {
                c = ConnectionPool.getConnection(alias);
                CallableStatement cs = c.prepareCall("{ call info.init() }");
                cs.execute();
                PreparedStatement ps = c.prepareStatement(str);
                ResultSet rs = ps.executeQuery();
                //System.out.println(str);
                //System.out.println(alias);
                while (rs.next()) {
                	//System.out.println("111");
                	list.add(new RefData(
                                        rs.getString(1),
                                        rs.getString(2)));
                }
        } catch (SQLException e) {
        		e.printStackTrace();
                ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;

	}
	
	public static List<RefData> getListForCombobox(String str, Boolean showErr, String alias)  {

        List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        try {
                c = ConnectionPool.getConnection(alias);
                CallableStatement cs = c.prepareCall("{ call info.init() }");
                cs.execute();
                PreparedStatement ps = c.prepareStatement(str);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new RefData(
                                        rs.getString("kod"),
                                        rs.getString("name")));
                }
        } catch (SQLException e) {
        		if (showErr) ISLogger.getLogger().error(CheckNull.getPstr(e));
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

        private static List<FilterField> getFilterFields(ParameterFilter filter){
                List<FilterField> flfields = new ArrayList<FilterField>();

                if(!CheckNull.isEmpty(filter.getBranch())){
                	flfields.add(new FilterField(getCond(flfields)+ "branch = ?", filter.getBranch()));
          }
                if(!CheckNull.isEmpty(filter.getUser_id())){
                	flfields.add(new FilterField(getCond(flfields)+ "client_id = ?", filter.getUser_id()));
          }
                if(!CheckNull.isEmpty(filter.getParam_value())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_value = ?", filter.getParam_value()));
          }
                if(!CheckNull.isEmpty(filter.getParam_group_id())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_group_id = ?", filter.getParam_group_id()));
          }
                if(!CheckNull.isEmpty(filter.getParam_id())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_id = ?", filter.getParam_id()));
          }
                if(!CheckNull.isEmpty(filter.getParam_name_en())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_name_en = ?", filter.getParam_name_en()));
          }
                if(!CheckNull.isEmpty(filter.getParam_name_ru())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_name_ru = ?", filter.getParam_name_ru()));
          }
                if(!CheckNull.isEmpty(filter.getParam_name_uz())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_name_uz = ?", filter.getParam_name_uz()));
          }
                if(!CheckNull.isEmpty(filter.getParam_select())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_select = ?", filter.getParam_select()));
          }
                if(!CheckNull.isEmpty(filter.getParam_type())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_type = ?", filter.getParam_type()));
          }
                if(!CheckNull.isEmpty(filter.getParam_mask())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_mask = ?", filter.getParam_mask()));
          }
                if(!CheckNull.isEmpty(filter.getParam_def_value())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_def_value = ?", filter.getParam_def_value()));
          }
                if(!CheckNull.isEmpty(filter.getParam_ord())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_ord = ?", filter.getParam_ord()));
          }
                if(!CheckNull.isEmpty(filter.getParam_mandatory())){
                	flfields.add(new FilterField(getCond(flfields)+ "param_mandatory = ?", filter.getParam_mandatory()));
          }


              flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

                return flfields;
        }


        public int getCount(ParameterFilter filter)  {

            Connection c = null;
            int n = 0;
            List<FilterField> flFields =getFilterFields(filter);
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT count(*) ct FROM (select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*, decode(a.branch, null, 1, 0) isnew   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id) ");
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



        public static List<Parameter> getParametersFl(int pageIndex, int pageSize, ParameterFilter filter)  {

                List<Parameter> list = new ArrayList<Parameter>();
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
                                list.add(new Parameter(
                                		rs.getString("branch"),
                						rs.getLong("user_id"),
                						rs.getString("param_value"),
                						rs.getLong("param_group_id"),
                						rs.getLong("param_id"),
                						rs.getString("param_name"),
                						rs.getString("param_name_en"),
                						rs.getString("param_name_ru"),
                						rs.getString("param_name_uz"),
                						rs.getString("param_select"),
                						rs.getString("param_type"),
                						rs.getString("param_mask"),
                						rs.getString("param_def_value"),
                						rs.getLong("param_ord"),
                						rs.getInt("param_mandatory"),
                						rs.getInt("isnew")));
                        }
                } catch (SQLException e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return list;

        }


        public Parameter getParameter(int parameterId) {

                Parameter parameter = new Parameter();
                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("SELECT * FROM select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id WHERE id=?");
                        ps.setInt(1, parameterId);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                parameter = new Parameter();
                                parameter.setBranch(rs.getString("branch"));
                                parameter.setUser_id(rs.getLong("User_id"));
                                parameter.setParam_value(rs.getString("param_value"));
                                parameter.setParam_group_id(rs.getLong("param_group_id"));
                                parameter.setParam_id(rs.getLong("param_id"));
                                parameter.setParam_name_en(rs.getString("param_name_en"));
                                parameter.setParam_name_ru(rs.getString("param_name_ru"));
                                parameter.setParam_name_uz(rs.getString("param_name_uz"));
                                parameter.setParam_select(rs.getString("param_select"));
                                parameter.setParam_type(rs.getString("param_type"));
                                parameter.setParam_mask(rs.getString("param_mask"));
                                parameter.setParam_def_value(rs.getString("param_def_value"));
                                parameter.setParam_ord(rs.getLong("param_ord"));
                                parameter.setParam_mandatory(rs.getInt("param_mandatory"));
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
                return parameter;
        }
/*
        public static Parameter create(Parameter parameter)  {

                Connection c = null;
                PreparedStatement ps = null;
                try {
                        c = ConnectionPool.getConnection();
                        ps = c.prepareStatement("SELECT SEQ_select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id.NEXTVAL id FROM DUAL");
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                                parameter.setId(rs.getLong("id"));
                        }
                        ps = c.prepareStatement("INSERT INTO select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id (branch, client_id, param_value, client_type, param_group_id, param_id, param_name_en, param_name_ru, param_name_uz, param_select, param_type, param_mask, param_def_value, param_ord, param_mandatory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, parameter.getBranch());
                ps.setString(2, parameter.getClient_id());
                ps.setString(3, parameter.getParam_value());
                ps.setString(4, parameter.getClient_type());
                ps.setLong(5, parameter.getParam_group_id());
                ps.setLong(6, parameter.getParam_id());
                ps.setString(7, parameter.getParam_name_en());
                ps.setString(8, parameter.getParam_name_ru());
                ps.setString(9, parameter.getParam_name_uz());
                ps.setString(10, parameter.getParam_select());
                ps.setString(11, parameter.getParam_type());
                ps.setString(12, parameter.getParam_mask());
                ps.setString(13, parameter.getParam_def_value());
                ps.setLong(14, parameter.getParam_ord());
                ps.setLong(15, parameter.getParam_mandatory());

                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();

                } finally {
                        ConnectionPool.close(c);
                }
                return parameter;
        }

        public static void update(Parameter parameter)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("UPDATE select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id SET branch = ?, client_id = ?, param_value = ?, client_type = ?, param_group_id = ?, param_id = ?, param_name_en = ?, param_name_ru = ?, param_name_uz = ?, param_select = ?, param_type = ?, param_mask = ?, param_def_value = ?, param_ord = ?, param_mandatory = ?  WHERE id=?");
                ps.setString(1, parameter.getBranch());
                ps.setString(2, parameter.getClient_id());
                ps.setString(3, parameter.getParam_value());
                ps.setString(4, parameter.getClient_type());
                ps.setLong(5, parameter.getParam_group_id());
                ps.setLong(6, parameter.getParam_id());
                ps.setString(7, parameter.getParam_name_en());
                ps.setString(8, parameter.getParam_name_ru());
                ps.setString(9, parameter.getParam_name_uz());
                ps.setString(10, parameter.getParam_select());
                ps.setString(11, parameter.getParam_type());
                ps.setString(12, parameter.getParam_mask());
                ps.setString(13, parameter.getParam_def_value());
                ps.setLong(14, parameter.getParam_ord());
                ps.setLong(15, parameter.getParam_mandatory());

                        ps.executeUpdate();
                        c.commit();
                } catch (SQLException e) {
                        e.printStackTrace();
                        throw new Exception(e);
                } finally {
                        ConnectionPool.close(c);
                }

        }

        public static void remove(Parameter parameter)  {

                Connection c = null;

                try {
                        c = ConnectionPool.getConnection();
                        PreparedStatement ps = c.prepareStatement("DELETE FROM select    decode(a.branch, null, '01066', a.branch) branch,    decode(a.client_id, null, '00000099', a.client_id) client_id,    a.param_value,    t.*   from USER_ADDINFO_TEMPLATE t,       (select * from USER_ADDINFO ca where ca.client_type = 'P' and ca.branch = '01066' and ca.client_id='00000099') a where t.client_type = 'P'   and a.client_type(+) = t.client_type   and a.param_group_id(+) = t.param_group_id   and a.param_id(+) = t.param_id WHERE id=?");
                        ps.setInt(1, parameter.getId());
                        ps.executeUpdate();
                        c.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        ConnectionPool.close(c);
                }
        }
*/
    
    public static Boolean saveAddInfo(List<Parameter> parameters)  { 
    	Boolean res = false;
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection();
	    	for (Parameter parameter : parameters) {
				if (parameter.getIsnew() == 1) {
					res = create(c, parameter);
				} else {
					res = update(c, parameter);
				}
				if (res == false) {
					c.rollback();
		    		return res;
				}
			}
	    	c.commit();
    	} catch (Exception e) {
    		try{
    			c.rollback();
    		} catch (Exception ex) {
				ex.printStackTrace();
			}
    	} finally {
    		ConnectionPool.close(c);
    	}
    	return res;
    }
    
    public static Boolean create(Connection c, Parameter parameter)  {
    	Boolean res = false;
    	//Connection c = null;
    	PreparedStatement ps = null;
    	try {
    		//c = ConnectionPool.getConnection();
    		ps = c.prepareStatement("INSERT INTO USER_ADDINFO (branch, user_id, param_group_id, param_id, param_value) VALUES (?, ?, ?, ?, ?)");
    		ps.setString(1, parameter.getBranch());
    		ps.setLong(2, parameter.getUser_id());
    		ps.setLong(3, parameter.getParam_group_id());
    		ps.setLong(4, parameter.getParam_id());
    		ps.setString(5, parameter.getParam_value());
    		ps.executeUpdate();
    		//c.commit();
    		res = true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		/*try{
    			c.rollback();
    		} catch (Exception ex) {
				ex.printStackTrace();
			}*/
    		res = false;
    	} finally {
    		//ConnectionPool.close(c);
    	}
    	return res;
    }

    public static Boolean update(Connection c, Parameter parameter)  {
    	Boolean res = false;
    	//Connection c = null;
    	try {
    		//c = ConnectionPool.getConnection();
    		PreparedStatement ps = c.prepareStatement("UPDATE USER_ADDINFO SET param_value = ? WHERE branch = ? and user_id = ? and param_group_id = ? and param_id = ?");
    		ps.setString(1, parameter.getParam_value());
            ps.setString(2, parameter.getBranch());
            ps.setLong(3, parameter.getUser_id());
            ps.setLong(4, parameter.getParam_group_id());
            ps.setLong(5, parameter.getParam_id());
            ps.executeUpdate();
            //c.commit();
            res = true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		/*try{
    			c.rollback();
    		} catch (Exception ex) {
				ex.printStackTrace();
			}*/
    		res = false;
    	} finally {
    		//ConnectionPool.close(c);
    	}
    	return res;
    }


}
