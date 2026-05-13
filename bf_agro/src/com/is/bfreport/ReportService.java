package com.is.bfreport;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class ReportService {
	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	public static List<RefData> getReportGroup(int uid, String alias)  {

        List<RefData> list = new LinkedList<RefData>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select s.id as data, s.name as label from ss_deal s where s.group_id=? and id in (select distinct deal_id from ACTION_REPORT where ID in (SELECT ACTION_ID FROM ROLE_ACTIONS WHERE GROUP_ID=? AND ROLE_ID IN (SELECT ROLE_ID FROM USER_ROLES  WHERE USER_ID=?))) order by id");
                ps.setInt(1, 9);
                ps.setInt(2, 9);
                ps.setInt(3, uid);
    			ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new RefData(
                                        rs.getString("data"),
                                        rs.getString("label")));
                }
        } catch (SQLException e) {
                RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;

	}
 
	public static List<RepData> getReports(int deal_id, int uid, String alias)  {

        List<RepData> list = new LinkedList<RepData>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select ar.id as data, ar.name as label, decode(jasper,null,0,jasper) as type from action_report ar where ar.deal_id=? and ID in (SELECT ACTION_ID FROM ROLE_ACTIONS WHERE GROUP_ID=? AND ROLE_ID IN (SELECT ROLE_ID FROM USER_ROLES  WHERE USER_ID=?)) and ar.manual=1 order by id");
                ps.setInt(1, deal_id);
                ps.setInt(2, 9);
                ps.setInt(3, uid);
    			ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new RepData(
                                        rs.getString("data"),
                                        rs.getString("label"),
                                        rs.getInt("type")));
                }
        } catch (SQLException e) {
                RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;
	}
	
	private static List<Parameter> getParameters(int deal_id, int rep_id, String alias)  {

        List<Parameter> list = new ArrayList<Parameter>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select sr.*,rdp.par_value as def_par_value from state_report sr, (select * from report_defparam where deal_id = ? and action_id = ?) rdp where sr.deal_id = ? and rdp.par_name (+) = sr.par_name order by sr.id");
                ps.setInt(1, deal_id);
                ps.setInt(2, rep_id);
                ps.setInt(3, deal_id);
    			ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new Parameter(
                                        rs.getInt("id"),
                                        rs.getString("name"),
				                        rs.getString("par_name"),
				                        rs.getString("par_select"),
				                        null,
				                        null,
				                        rs.getString("def_par_value"),
				                        0));
                }
                
        } catch (SQLException e) {
                RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;

	}
	
	public static List<Route> getParameterRoutes(int deal_id, int rep_id, String alias)  {
        List<Route> list = new ArrayList<Route>();
        Connection c = null;
        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("select * from trans_report tr where tr.deal_id = ? and tr.action_id = ?");
                ps.setInt(1, deal_id);
                ps.setInt(2, rep_id);
    			ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new Route(
                                        rs.getInt("state_begin"),
                                        rs.getInt("state_end")));
                }
        } catch (SQLException e) {
                RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;
	}
	
	public static List<Parameter> createParameters(int deal_id, int rep_id, int type, String alias, String lang)  {
		List<Parameter> params = new ArrayList<Parameter>();
		if (type == 0){
			params = createParametersForDinamicJReport(deal_id, rep_id, lang, alias);
		} else {
			params = createParametersForStaticJReport(rep_id, lang, alias);
		}
		return params;
	}
	
	public static List<Parameter> createParametersForDinamicJReport(int deal_id, int rep_id, String lang, String alias)  {
		List<Parameter> params = new ArrayList<Parameter>();
		List<Integer> listParamNum = new ArrayList<Integer>();
		//Get routes of parameter
		List<Route> routes = getParameterRoutes(deal_id, rep_id, alias);
		//Get parameters
		List<Parameter> paramsAll = getParameters(deal_id, rep_id, alias);
		//Get sorted number of params 
		int num = 0;
		int i = 0;
		int i1 = 0;
		int rep_exec_id = 0;
		//¬ычисление REP_START и REP_EXEC
		for (i1 = 0; i1 < paramsAll.size(); i1++){
			if (paramsAll.get(i1).getPar_name().equalsIgnoreCase("REP_START")){
				i = paramsAll.get(i1).getId();
			} else if (paramsAll.get(i1).getPar_name().equalsIgnoreCase("REP_EXEC")){
				rep_exec_id = paramsAll.get(i1).getId();
			}
		}
		//¬ычисление очередности параметров согласно маршрутам (routes)
		while (i != rep_exec_id) {
			for (i1 = 0; i1 < routes.size(); i1++){
				if (routes.get(i1).getState_begin() == i) {
					listParamNum.add(num++,routes.get(i1).getState_begin());
					i = routes.get(i1).getState_end();
					if (i == rep_exec_id){
						listParamNum.add(num,routes.get(i1).getState_end());
					}
				}
			}
		}
		
		//Get params by number
		for (i1 = 0; i1 < listParamNum.size(); i1++){
			//System.out.println(i1+") "+listParamNum.get(i1));
			for (i = 0; i < paramsAll.size(); i++){
				if (listParamNum.get(i1) == paramsAll.get(i).getId()){
					if (paramsAll.get(i).getPar_select().indexOf("'$$'") != -1){
						paramsAll.get(i).setPar_type("DATE");
					} else if (paramsAll.get(i).getPar_select().indexOf("'$$$'") != -1){
						paramsAll.get(i).setPar_type("STRING");
					} else if (paramsAll.get(i).getPar_select().indexOf("'@@@'") != -1){
						paramsAll.get(i).setPar_type("NUMBER");
					} else{
						paramsAll.get(i).setPar_type("COMBOBOX");
					}
					//paramsAll.get(i).setPar_type("");
					paramsAll.get(i).setOrd(i1);
					params.add(i1,paramsAll.get(i));
				}
			}
		}
		return params;
	}
	
	public static List<Parameter> createParametersForStaticJReport(int rep_id, String lang, String alias)  {
		List<Parameter> params = getJParameters(rep_id, "ru", alias);
		//Get params by number
		for (int i = 0; i < params.size(); i++){
			if (!params.get(i).getPar_name().equalsIgnoreCase("REP_EXEC")) {
				if (params.get(i).getPar_type().indexOf("D") != -1){
					params.get(i).setPar_type("DATE");
				} else if (params.get(i).getPar_type().indexOf("S") != -1){
					params.get(i).setPar_type("STRING");
				} else if (params.get(i).getPar_type().indexOf("N") != -1){
					params.get(i).setPar_type("NUMBER");
				} else{
					params.get(i).setPar_type("COMBOBOX");
				}
			}
		}
				
		//Parameter par = new Parameter(30,"¬ывод отчета","¬ывод отчета","","","","");
		//params.add(params.size(),par);
		return params;
	}
	
	private static List<Parameter> getJParameters(int rep_id, String lang,String alias)  {

        List<Parameter> list = new ArrayList<Parameter>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection(alias);
                PreparedStatement ps = c.prepareStatement("SELECT * FROM BF_REPPARAMS where rep_id=? order by par_id");
                ps.setLong(1,rep_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new Parameter(
		                        		rs.getInt("par_id"),
		                                rs.getString("par_name_"+lang),
				                        rs.getString("par_name"),
				                        rs.getString("par_sel"),
				                        rs.getString("par_type"),
				                        null,
				                        rs.getString("par_def_value"),
				                        rs.getInt("par_id")));
                }
                ps = c.prepareStatement("SELECT * FROM BF_REPORTS where rep_id=?");
                ps.setLong(1,rep_id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    list.add(new Parameter(
                    		1000,//id
                    		rs.getString("rep_name_"+lang),//name
                    		"REP_EXEC",//par_name
                    		rs.getString("rep_out_file_name"),//par_select
                    		rs.getString("rep_type"),//par_type
                    		rs.getString("rep_template"),//par_value
	                        rs.getString("rep_class"),//def_par_value
	                        1000));//ord
                }
                
        } catch (SQLException e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));

        } finally {
                ConnectionPool.close(c);
        }
        return list;

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
                RLogger.getLogger().error(CheckNull.getPstr(e));
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
        		if (showErr) RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;

	}

	// Dynamic Reports - select headers & footers
	// headers
	public static List<HeaderFooter> getHeadersForDinamicJReport(Connection c, List<Parameter> params, int deal_id, int rep_id)  {
		List<HeaderFooter> headers = new LinkedList<HeaderFooter>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement("select * from report_headfoot where deal_id=? and action_id=? and par_name like 'REP_H%' order by par_name");
			ps.setInt(1, deal_id);
			ps.setInt(2, rep_id);
			rs = ps.executeQuery();
					PreparedStatement ps2 = null;
					ResultSet rs2 = null;
					while (rs.next()){
						ps2 = c.prepareStatement(setParamsInSQL(params, rs.getString("PAR_SELECT")));
						rs2 = ps2.executeQuery();
						if (rs2.next()){
							headers.add(new HeaderFooter(
								rs.getString("PAR_NAME"), 
								rs.getString("PAR_SELECT"), 
								(rs2.getString(1)==null?"":rs2.getString(1))));
						}
					}
		} catch (Exception e) {
			RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
		}
		
		return headers;
	}
	// footers
	public static List<HeaderFooter> getFootersForDinamicJReport(Connection c, List<Parameter> params, int deal_id, int rep_id)  {
		List<HeaderFooter> footers = new LinkedList<HeaderFooter>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement("select * from report_headfoot where deal_id=? and action_id=? and par_name like 'REP_F%' order by par_name");
			ps.setInt(1, deal_id);
			ps.setInt(2, rep_id);
			rs = ps.executeQuery();
					PreparedStatement ps2 = null;
					ResultSet rs2 = null;
					while (rs.next()){
						ps2 = c.prepareStatement(setParamsInSQL(params, rs.getString("PAR_SELECT")));
						rs2 = ps2.executeQuery();
						if (rs2.next()) {
							footers.add(new HeaderFooter(
								rs.getString("PAR_NAME"), 
								rs.getString("PAR_SELECT"), 
								(rs2.getString(1)==null?"":rs2.getString(1))));
						}
					}
		} catch (Exception e) {
			RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
		}
		
		return footers;
	}
	
	//=================================================================
	//=========================Help methods============================
	//=================================================================
		
	public static String setParamsInSQL(List<Parameter> params, String select){
		String res = select;
		res = setParamsInSQL_Param(params, res);
		res = setParamsInSQL_Sharp(params, res);
		return res;
	}
	
	public static String setParamsInSQL_Param(List<Parameter> params, String select){
		int k = 0; //позици€ "PARAM.GETPARAM('"
		int m = 0; //позици€ символа ',' или ')' или ' '
		int m1 = 0; //позици€ символа ',' или ')' или ' '
		int l = 0; //длина строки селекта
		String cur_param=""; // наименование замен€емого параметра
		String cur_param_val=""; // значение замен€емого параметра
		String str=select; // селект с параметрами
		try{
		    l = str.length();
		    while (k<l+1){
			    k = str.indexOf("PARAM.GETPARAM('"); 
			    if (k==-1) {
			    	k=l+1;
			    } else {
			    	//ѕоиск окончани€ наименовани€ параметра
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
			RLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return str;
	}
	
	public static String setParamsInSQL_Sharp(List<Parameter> params, String select){
		int k = 0; //позици€ символа '#'
		int m = 0; //позици€ символа ',' или ')' или ' '
		int m1 = 0; //позици€ символа ',' или ')' или ' '
		int l = 0; //длина строки селекта
		String cur_param=""; // наименование замен€емого параметра
		String cur_param_val=""; // значение замен€емого параметра
		String str=select; // селект с параметрами
		try{
		    l = str.length();
		    while (k<l+1){
			    k = str.indexOf("#"); 
			    if (k==-1) {
			    	k=l+1;
			    } else {
			    	//ѕоиск окончани€ наименовани€ параметра
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
			RLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return str;
	}
	
	private static String getParamValue(List<Parameter> params, String param){
		String param_value = "";
		Parameter par = null;
		for (int i = 0; i < params.size(); i++){
			par = (Parameter) params.get(i);
			//System.out.println(par.getPar_name()+" = '"+par.getPar_value()+"'; par_found = "+param);
			if (par.getPar_name().equals(param)){
				if (par.getPar_value()!=null){
					param_value = par.getPar_value();
				}
			}
		}
		return param_value;
	}
	//=================================================================
	//====================OLD METHODS FOR SERVLETS=====================
	//=================================================================
/*
	private static ResultSet getReportResult( List<Parameter> params ) {
		Connection c = null;
		CallableStatement cs;
		CallableStatement cs_clearParam;
		CallableStatement cs_setParam;
		String select = "";
		ResultSet rs = null;
		try{
			c = ConnectionPool.getConnection();
			
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			cs_clearParam = c.prepareCall("{ call Param.clearparam() }");
			cs_clearParam.execute();
			cs_setParam = c.prepareCall("{ call Param.SetParam(?,?) }");
			for (int i = 0; i < params.size(); i++){
				if (params.get(i).getId() != 30 && params.get(i).getId() != 0) {
					cs_setParam.setString(1, params.get(i).getPar_name());
					cs_setParam.setString(2, (params.get(i).getPar_value().equals("")? (params.get(i).getDef_par_value().equals("")?null:params.get(i).getDef_par_value()):params.get(i).getPar_value()));
					cs_setParam.execute();
				} else {
					select = "{ call "+params.get(i).getDef_par_value()+"(?) }";
				}
			}
			cs = c.prepareCall(select);
    		cs.registerOutParameter(1, OracleTypes.CURSOR);
    		cs.execute();
            rs = (ResultSet)cs.getObject(1);
            //ResultSetMetaData rsmd = rs.getMetaData();
            //columns
            
        }catch(Exception e) {
        	e.printStackTrace();
        	RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
      	  	ConnectionPool.close(c);
        }
		return rs;
	}
	
	public static OutputStream excelReport(OutputStream os, List<Parameter> params) {
		//exporter...
		JasperXlsExporterBuilder xlsExporter = export.xlsExporter(os)
                .setDetectCellType(true)
                .setIgnorePageMargins(true)
                .setWhitePageBackground(false)
                .setRemoveEmptySpaceBetweenColumns(true);
		
    	try {
    		
            ResultSet rs = getReportResult( params );
            ResultSetMetaData rsmd = rs.getMetaData();
            
			JasperReportBuilder j = report()
					.setColumnTitleStyle(Templates.columnTitleStyle)
					.addProperty(JasperProperty.EXPORT_XLS_FREEZE_ROW, "2")
					.ignorePageWidth()
					.ignorePagination()
					.pageHeader();
			
			//columns
			for (Column colum : createColumns(rsmd)) {
				j.addColumn(col.column(colum.title, colum.field, type.detectType(colum.dataType)));
			}
			
			//rs.next();
	        j
			.setDataSource(rs)
			.toXls(xlsExporter);
	        
    	} catch (SQLException e) {
	    	RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
	    	/*
	    	try{
	    		c.rollback();
	    	} catch (Exception e1) {
	    		RLogger.getLogger().error(CheckNull.getPstr(e1));
	    		e1.printStackTrace();
			}
			*/ /*
	    } catch (DRException e2) {
    		RLogger.getLogger().error(CheckNull.getPstr(e2));
    		e2.printStackTrace();
		} finally {
    	    try{
        		os.close();
        	} catch (Exception e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));
    		}
    	}
    	return os;
    }
	
	public static OutputStream pdfReport(OutputStream os, List<Parameter> params) {
		//exporter...
		JasperPdfExporterBuilder pdfExporter = export.pdfExporter(os)
                .setIgnorePageMargins(false)
                .setMetadataTitle("report")
                .setCharacterEncoding("utf-8");
    	try {
    		ResultSet rs = getReportResult( params );
            ResultSetMetaData rsmd = rs.getMetaData();
            
			JasperReportBuilder j = report()
					.setColumnTitleStyle(Templates.columnTitleStyle)
					//.ignorePageWidth()
					//.ignorePagination()
					.pageHeader()
					.columnGrid(ListType.HORIZONTAL_FLOW);
			
			//columns
			for (Column colum : createColumns(rsmd)) {
				j.addColumn(col.column(colum.title, colum.field, type.detectType(colum.dataType)));
			}
			
			//rs.next();
	        j
			.setDataSource(rs)
			.toPdf(pdfExporter);
	        
    	} catch (SQLException e) {
	    	RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
	    } catch (DRException e2) {
    		RLogger.getLogger().error(CheckNull.getPstr(e2));
    		e2.printStackTrace();
		} finally {
    	    try{
        		os.close();
        	} catch (Exception e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));
    		}
    	}
    	return os;
    }
	
	public static OutputStream docReport(OutputStream os, List<Parameter> params) {
		//exporter...
		JasperDocxExporterBuilder docxExporter = export.docxExporter(os)
                .setIgnorePageMargins(false)
                .setCharacterEncoding("utf-8");
    	try {
    		ResultSet rs = getReportResult( params );
            ResultSetMetaData rsmd = rs.getMetaData();
            
			JasperReportBuilder j = report()
					.setColumnTitleStyle(Templates.columnTitleStyle)
					//.ignorePageWidth()
					//.ignorePagination()
					.pageHeader()
					.columnGrid(ListType.HORIZONTAL_FLOW);
			
			//columns
			for (Column colum : createColumns(rsmd)) {
				j.addColumn(col.column(colum.title, colum.field, type.detectType(colum.dataType)));
			}
			
			//rs.next();
	        j
			.setDataSource(rs)
			.toDocx(docxExporter);
	        
    	} catch (SQLException e) {
	    	RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
	    } catch (DRException e2) {
    		RLogger.getLogger().error(CheckNull.getPstr(e2));
    		e2.printStackTrace();
		} finally {
    	    try{
        		os.close();
        	} catch (Exception e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));
    		}
    	}
    	return os;
    }
	
	public static OutputStream rtfReport(OutputStream os, List<Parameter> params) {
		//exporter...
		JasperRtfExporterBuilder rtfExporter = export.rtfExporter(os)
                .setIgnorePageMargins(false)
                .setCharacterEncoding("utf-8");
    	try {
    		ResultSet rs = getReportResult( params );
            ResultSetMetaData rsmd = rs.getMetaData();
            
			JasperReportBuilder j = report()
					.setColumnTitleStyle(Templates.columnTitleStyle)
					//.ignorePageWidth()
					//.ignorePagination()
					.pageHeader()
					.columnGrid(ListType.HORIZONTAL_FLOW);
			
			//columns
			for (Column colum : createColumns(rsmd)) {
				j.addColumn(col.column(colum.title, colum.field, type.detectType(colum.dataType)));
			}
			
			//rs.next();
	        j
			.setDataSource(rs)
			.toRtf(rtfExporter);
	        
    	} catch (SQLException e) {
	    	RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
	    } catch (DRException e2) {
    		RLogger.getLogger().error(CheckNull.getPstr(e2));
    		e2.printStackTrace();
		} finally {
    	    try{
        		os.close();
        	} catch (Exception e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));
    		}
    	}
    	return os;
    }
	
	public static OutputStream htmlReport(OutputStream os, List<Parameter> params) {
		//exporter...
		JasperHtmlExporterBuilder htmlExporter = export.htmlExporter(os)
                .setIgnorePageMargins(true)
                .setCharacterEncoding("utf-8")
                ;
		try {
    		ResultSet rs = getReportResult( params );
            ResultSetMetaData rsmd = rs.getMetaData();
            
			JasperReportBuilder j = report()
					.setColumnTitleStyle(Templates.columnTitleStyle)
					.ignorePageWidth()
					.ignorePagination()
					.title(Templates.createTitleComponent("HtmlReport"))
					.pageFooter(Templates.footerComponent)
					.columnGrid(ListType.HORIZONTAL_FLOW);
			
			//columns
			for (Column colum : createColumns(rsmd)) {
				j.addColumn(col.column(colum.title, colum.field, type.detectType(colum.dataType)));
			}
			
			//rs.next();
	        j
			.setDataSource(rs)
			.toHtml(htmlExporter);
	        
    	} catch (SQLException e) {
	    	RLogger.getLogger().error(CheckNull.getPstr(e));
	    	e.printStackTrace();
	    } catch (DRException e2) {
    		RLogger.getLogger().error(CheckNull.getPstr(e2));
    		e2.printStackTrace();
		} finally {
    	    try{
        		os.close();
        	} catch (Exception e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));
    		}
    	}
    	return os;
    }
		
	private static List<Column> createColumns(ResultSetMetaData rsmd) {
		List<Column> columns = new ArrayList<Column>();
		try{
			for (int i = 1; i <= rsmd.getColumnCount(); i++){
				columns.add(new Column(rsmd.getColumnLabel(i),rsmd.getColumnName(i), getDataType(rsmd.getColumnType(i),rsmd.getColumnTypeName(i))));
			}
		} catch (Exception e) {
			RLogger.getLogger().error(CheckNull.getPstr(e));
		}
		return columns;
	}
	
	private static class Column {
		private String title;
		private String field;
		private String dataType;
			 
		private Column(String title, String field, String dataType) {
			this.title = title;
			this.field = field;
			this.dataType = dataType;
		}
	}
	
	private static String getDataType(int n, String str){
		switch (n){
		case -7://BIT
			System.out.println(n+" - "+str);
			break;
		case -6://TINYINT
			System.out.println(n+" - "+str);
			break;
		case -5://BIGINT
			str = type.bigIntegerType().toString();
			break;
		case -4://LONGVARBINARY
			System.out.println(n+" - "+str);
			break;
		case -3://VARBINARY
			System.out.println(n+" - "+str);
			break;
		case -2://BINARY
			System.out.println(n+" - "+str);
			break;
		case -1://LONGVARCHAR
			str = "STRING";
			break;
		case 0://NULL
			System.out.println(n+" - "+str);
			break;
		case 1://CHAR
			str = "STRING";
			break;
		case 2://NUMBER
			str = "INTEGER";
			break;
		case 3://DECIMAL
			str = "DECIMAL";
			break;
		case 4://INTEGER
			str = "INTEGER";
			break;
		case 5://SMALLINT
			str = "INTEGER";
			break;
		case 6://FLOAT
			str = "FLOAT";
			break;
		case 7://REAL
			str = "BIGINTEGER";
			break;
		case 8://DOUBLE
			str = "DOUBLE";
			break;
		case 12://VARCHAR
			str = "STRING";
			break;
		case 91://DATE
			str = "DATE";
			break;
		case 92://TIME
			str = "timeHourToSecondType";
			break;
		case 93://TIMESTAMP
			str = "DATE";
			break;
		case 1111://OTHER
			str = "STRING";
			break;
		case 2000://JAVA_OBJECT
			System.out.println(n+" - "+str);
			break;
		case 2001://DISTINCT
			System.out.println(n+" - "+str);
			break;
		case 2002://STRUCT
			System.out.println(n+" - "+str);
			break;
		case 2004://BLOB
			System.out.println(n+" - "+str);
			break;
		case 2005://CLOB
			System.out.println(n+" - "+str);
			break;
		case 2006://REF
			System.out.println(n+" - "+str);
			break;
		default: 
			System.out.println(n+" - "+str+" converted to string");
			str = "STRING";
	        break;
		}
		return str;
	}
	
	//==========================================================================
	//This methods work with *.jasper files
	
	public static List<Report> getReport()  {

        List<Report> list = new ArrayList<Report>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                Statement s = c.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM Reports");
                while (rs.next()) {
                        list.add(new Report(
                                        rs.getLong("id"),
                                        rs.getString("rclass"),
                                        rs.getString("rfname"),
                                        rs.getString("rname")));
                }
        } catch (SQLException e) {
                RLogger.getLogger().error(CheckNull.getPstr(e));

        } finally {
                ConnectionPool.close(c);
        }
        return list;
}
    public static  Report getReport(int reportId) throws Exception {

        Report report = new Report();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM reports WHERE id=?");
                ps.setInt(1, reportId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        report = new Report();

                        report.setId(rs.getLong("id"));
                        report.setRclass(rs.getString("rclass"));
                        report.setRfname(rs.getString("rfname"));
                        report.setRname(rs.getString("rname"));
                }
        } catch (Exception e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));
                throw new Exception(e);
        } finally {
                ConnectionPool.close(c);
        }
        return report;
}
/*    public static List<Parameter> getRepPar(long rid)  {

        List<Parameter> list = new ArrayList<Parameter>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM repparams where repid=? order by parid");
                ps.setLong(1,rid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new Parameter(
                                        rs.getLong("repid"),
                                        rs.getLong("parid"),
                                        rs.getString("partype"),
                                        rs.getString("pname"),
                                        rs.getString("psel")));
                }
        } catch (SQLException e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));

        } finally {
                ConnectionPool.close(c);
        }
        return list;

}
*/ /*
    public static String  getRepPar(long rid,long pid)  {

        String res="S";
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("SELECT * FROM repparams where repid=? and parid=? ");
                ps.setLong(1,rid);
                ps.setLong(2,pid);
                ResultSet rs = ps.executeQuery();
               if (rs.next()) {
                        
            	   res =rs.getString("partype");
                }
        } catch (SQLException e) {
        		RLogger.getLogger().error(CheckNull.getPstr(e));

        } finally {
                ConnectionPool.close(c);
        }
        return res;

}
    
    private static List<Parameter> getParameters(int deal_id, int rep_id)  {

        List<Parameter> list = new ArrayList<Parameter>();
        Connection c = null;

        try {
                c = ConnectionPool.getConnection();
                PreparedStatement ps = c.prepareStatement("select sr.*,rdp.par_value as def_par_value from state_report sr, (select * from report_defparam where deal_id = ? and action_id = ?) rdp where sr.deal_id = ? and rdp.par_name (+) = sr.par_name order by sr.id");
                ps.setInt(1, deal_id);
                ps.setInt(2, rep_id);
                ps.setInt(3, deal_id);
    			ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        list.add(new Parameter(
                                        rs.getInt("id"),
                                        rs.getString("name"),
				                        rs.getString("par_name"),
				                        rs.getString("par_select"),
				                        null,
				                        null,
				                        rs.getString("def_par_value")));
                }
                
        } catch (SQLException e) {
                RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
                ConnectionPool.close(c);
        }
        return list;

	}

*/
	public static Date addDays(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_YEAR, count);
	    return cal.getTime();
	}
    
    public static int getWeekDay(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.DAY_OF_WEEK);
	}
    
    public static int getDay(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.DAY_OF_MONTH);
	}
	
    public static Date setDay(Date date, int day) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.DAY_OF_MONTH, day);
	    return  cal.getTime();
	}
	
    public static Date addMonths(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MONTH, count);
	    return cal.getTime();
	}
    
    public static int getMonth(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.MONTH);
	}
    
    public static Date setMonth(Date date, int month) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MONTH, month);
	    return  cal.getTime();
	}
	
    public static Date addYears(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.YEAR, count);
	    return cal.getTime();
	}

    public static int getYear(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.YEAR);
	}
    
    public static Date setYear(Date date, int year) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.YEAR, year);
	    return  cal.getTime();
	}
    
    public static Date addHours(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.HOUR, count);
	    return cal.getTime();
	}

    public static int getHour(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.HOUR);
	}
    
    public static Date setHour(Date date, int hour) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR, hour);
	    return  cal.getTime();
	}
    
    public static Date addMinutes(Date date, int count) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MINUTE, count);
	    return cal.getTime();
	}

    public static int getMinute(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    return  cal.get(Calendar.MINUTE);
	}
    
    public static Date setMinute(Date date, int minute) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.MINUTE, minute);
	    return  cal.getTime();
	}
    
    public static List<RefData> getS_MfoAll(String alias) {
	    return RefDataService.getRefData("select smf.bank_id data, smf.bank_id||' - '||smf.bank_name label from s_mfo smf order by smf.bank_id", alias);
	}
    
    public static Date getCurDate(String un, String pw, String alias)  {
		Date res = null;
        Connection c = null;
        try {
        	c = ConnectionPool.getConnection(un, pw, alias);
        	PreparedStatement ps = c.prepareStatement("select info.getDay curdate from dual");
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		rs.getDate("curdate");
        	}
        } catch (SQLException e) {
        	RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
        	ConnectionPool.close(c);
        }
        return res;
	}
    
    public static Date getPrevDate(String alias)  {
		Date res = null;
        Connection c = null;
        try {
        	c = ConnectionPool.getConnection(alias);
        	PreparedStatement ps = c.prepareStatement("select prev_date from branch");
        	ResultSet rs = ps.executeQuery();
        	if (rs.next()) {
        		rs.getDate("prev_date");
        	}
        } catch (SQLException e) {
        	RLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
        	ConnectionPool.close(c);
        }
        return res;
	}
}
