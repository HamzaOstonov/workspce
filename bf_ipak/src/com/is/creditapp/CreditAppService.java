package com.is.creditapp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;
import com.is.utils.Res;

public class CreditAppService {
	
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="select * from (select ni_req.*,ni_req_n_code.code,state_ni_req.name from ni_req,ni_req_n_code,state_ni_req where ni_req.id=ni_req_n_code.id and ni_req.branch=ni_req_n_code.branch and state_ni_req.id(+)=ni_req.state order by ni_req.id desc) ";
    private static DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    private static DecimalFormat decf = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	private static List<RefData> s_mfo = null;
    private static List<RefData> s_rezkl = null;
    private static List<RefData> cl_type = null;
    private static List<RefData> sing_client = null;
    private static List<RefData> s_soogun = null;
    private static List<RefData> s_klass = null;
    private static List<RefData> is_letter = null;
    private static List<RefData> agency = null;
    private static List<RefData> reason = null;
    
    public static void doActionForCreditApp(CreditApp current,Integer action,String alias,Res res){
    	Connection c = null;
    	CallableStatement cs = null;
    	CallableStatement css = null;
    	CallableStatement csin=null;
    	try {
			c = ConnectionPool.getConnection(alias);
		//	c = ConnectionPool.getConnection(user,passw,branch);
		//	System.out.println("trtrtrtr"+user+"  ,pass : "+passw+"  ,branch :"+branch);
			
			cs = c.prepareCall("{ call param.clearparam() }");
			cs.execute();
			csin = c.prepareCall("{call info.init()}");
			csin.executeUpdate();
			cs = c.prepareCall("{ call Param.setParam(?,?) }");
			css = c.prepareCall("{ call kernel.doaction(?,?,?) }");
			if(action!=6&&action!=2){
				char[] array = current.getAmount().toCharArray();
				String amount = "";
				for (int j = 0; j < array.length; j++) {
					char d = array[j];
					if(d=='.'){
						d = ',';
					}
					amount += d;
					System.out.println(amount);
				}
				cs.setString(1, "AMOUNT"); cs.setString(2, amount); cs.execute();
				ISLogger.getLogger().error("AMOUNT = "+amount);
				cs.setString(1, "BANK_INPS"); cs.setString(2, current.getBank_inps()); cs.execute();
				ISLogger.getLogger().error("BANK_INPS = " + current.getBank_inps());
				cs.setString(1, "BRANCH_ID"); cs.setString(2, current.getBranch_id()); cs.execute();
				ISLogger.getLogger().error("BRANCH_ID = "+current.getBranch_id());
				cs.setString(1, "CLIENT_TYPE"); cs.setString(2, current.getClient_type()); cs.execute();
				ISLogger.getLogger().error("CLIENT_TYPE = " + current.getClient_type());
				cs.setString(1, "CODE"); cs.setString(2, current.getCode()); cs.execute();
				ISLogger.getLogger().error("CODE = "+current.getCode());
				cs.setString(1, "CURRENCY"); cs.setString(2, current.getCurrency()); cs.execute();
				ISLogger.getLogger().error("CURRENCY = "+current.getCurrency());
				cs.setString(1, "DBADDRESS"); cs.setString(2, current.getDbaddress()); cs.execute();
				ISLogger.getLogger().error("DBADDRESS = "+current.getDbaddress());
				cs.setString(1, "DBBRANCH"); cs.setString(2, current.getDbbranch()); cs.execute();
				ISLogger.getLogger().error("DBBRANCH = "+current.getDbbranch());
				cs.setString(1, "DBINN"); cs.setString(2, current.getDbinn()); cs.execute();
				ISLogger.getLogger().error("DBINN = "+current.getDbinn());
				cs.setString(1, "ETYPE"); cs.setString(2, current.getEtype()); cs.execute();
				ISLogger.getLogger().error("ETYPE = "+current.getEtype());
				cs.setString(1, "FAC_CLASS_ID"); cs.setString(2, current.getFac_class_id()); cs.execute();
				ISLogger.getLogger().error("FAC_CLASS_ID = "+current.getFac_class_id());
				cs.setString(1, "FAC_J_CODE_ORGAN_DIRECT"); cs.setString(2, current.getFac_j_code_organ_direct()); cs.execute();
				ISLogger.getLogger().error("FAC_J_CODE_ORGAN_DIRECT = "+current.getFac_j_code_organ_direct());
				cs.setString(1, "FAC_RESIDENT"); cs.setString(2, current.getFac_resident()); cs.execute();
				ISLogger.getLogger().error("FAC_RESIDENT = "+current.getFac_resident());
				cs.setString(1, "INN_ORG"); cs.setString(2, current.getInn_org()); cs.execute();
				ISLogger.getLogger().error("INN_ORG = "+current.getInn_org());
				cs.setString(1, "INN_PASSPORT"); cs.setString(2, current.getInn_passport()); cs.execute();
				ISLogger.getLogger().error("INN_PASSPORT = "+current.getInn_passport());
				cs.setString(1, "IS_LETTER"); cs.setString(2, current.getIs_letter()); cs.execute();
				ISLogger.getLogger().error("IS_LETTER = "+current.getIs_letter());
				cs.setString(1, "KRED_ID"); cs.setString(2, current.getKred_id()); cs.execute();
				ISLogger.getLogger().error("KRED_ID = "+current.getKred_id());
				cs.setString(1, "NAME_ORG"); cs.setString(2, current.getName_org()); cs.execute();
				ISLogger.getLogger().error("NAME_ORG = "+current.getName_org());
				cs.setString(1, "NWP"); cs.setString(2, current.getNwp()); cs.execute();
				ISLogger.getLogger().error("NWP = "+current.getNwp());
				cs.setString(1, "QW"); cs.setString(2, current.getQw()); cs.execute();
				ISLogger.getLogger().error("QW = "+current.getQw());
//				res.setCode(1);
//				res.setName("!!!"+current.getQw()+"!!!");
//				if(true){ 
//					Utils.close(css);
//					Utils.close(cs);
//					ConnectionPool.close(c);
//					return; }
				cs.setString(1, "RAGENCY"); cs.setString(2, current.getRagency()); cs.execute();
				ISLogger.getLogger().error("Ragency = "+current.getRagency());
				cs.setString(1, "RDOC"); cs.setString(2, current.getRdoc()); cs.execute();
				ISLogger.getLogger().error("RDOC = "+current.getRdoc());
				cs.setString(1, "REQNUM"); cs.setString(2, current.getReqnum()); cs.execute();
				ISLogger.getLogger().error("REQNUM = "+current.getReqnum());
				cs.setString(1, "REQTYPE"); cs.setString(2, current.getReqtype()); cs.execute();
				ISLogger.getLogger().error("REQTYPE = "+current.getReqtype());
				cs.setString(1, "RESOLVE_ORG"); cs.setString(2, current.getResolve_org()); cs.execute();
				ISLogger.getLogger().error("RESOLVE_ORG = "+current.getResolve_org());
				cs.setString(1, "REZKL"); cs.setString(2, current.getRezkl()); cs.execute();
				ISLogger.getLogger().error("REZKL = "+current.getRezkl());
				cs.setString(1, "RREASON"); cs.setString(2, current.getRreason()); cs.execute();
				ISLogger.getLogger().error("RREASON = "+current.getRreason());
				cs.setString(1, "RTXT"); cs.setString(2, current.getRtxt()); cs.execute();
				ISLogger.getLogger().error("RTXT = "+current.getRtxt());
				cs.setString(1, "RDATE"); cs.setString(2, current.getRdate()==null?null:sdf.format(current.getRdate())); cs.execute();
				ISLogger.getLogger().error("RDATE = "+(current.getRdate()==null?null:sdf.format(current.getRdate())));
				cs.setString(1, "SHIFR_ID"); cs.setString(2, current.getShifr_id().trim()); cs.execute();
				ISLogger.getLogger().error("SHIFR_ID = "+current.getShifr_id());
				cs.setString(1, "SIGN_CLIENT"); cs.setString(2, current.getSign_client()); cs.execute();
				ISLogger.getLogger().error("SIGN_CLIENT = "+current.getSign_client());
				cs.setString(1, "TYPE_ZM"); cs.setString(2, current.getType_zm()); cs.execute();
				ISLogger.getLogger().error("TYPE_ZM = "+current.getType_zm());
				cs.setString(1, "END_DATE"); cs.setString(2, current.getEnd_date()==null?null:sdf.format(current.getEnd_date())); cs.execute();
				ISLogger.getLogger().error("END_DATE = "+(current.getEnd_date()==null?null:sdf.format(current.getEnd_date())));
				cs.setString(1, "V_DATE"); cs.setString(2, current.getV_date()==null?null:sdf.format(current.getV_date())); cs.execute();
				ISLogger.getLogger().error("V_DATE = "+(current.getV_date()==null?null:sdf.format(current.getV_date())));
			}
			cs.setString(1, "ID"); cs.setString(2, current.getId()==null?"":current.getId()+""); cs.execute();
			ISLogger.getLogger().error("ID = "+current.getId());
			cs.setString(1, "BRANCH"); cs.setString(2, current.getBranch()); cs.execute();
			ISLogger.getLogger().error("BRANCH = " + current.getBranch());
			cs.setString(1, "ID_CLIENT"); cs.setString(2, current.getId_client()); cs.execute();
			ISLogger.getLogger().error("ID_CLIENT = "+current.getId_client());
			css.setInt(1, 44);
			css.setInt(2, 1);
			css.setInt(3, action);
			System.out.println(action);
			css.execute();
			c.commit();
    	} catch (Exception e) {
    		Utils.rollback(c);
    		res.setCode(1);
    		res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(css);
			Utils.close(cs);
			Utils.close(csin);
			ConnectionPool.close(c);
		}
    }
    
    private static String getCond(List<FilterField> flfields){
    	if(flfields.size()>0){
    		return " and ";
    	} else return " where ";
    }

    private static List<FilterField> getFilterFields(CreditAppFilter filter){
    	List<FilterField> flfields = new ArrayList<FilterField>();
    	if(!CheckNull.isEmpty(filter.getId())){
    		flfields.add(new FilterField(getCond(flfields)+ "id=?",filter.getId()));
    	}
        if(!CheckNull.isEmpty(filter.getBranch())){
        	flfields.add(new FilterField(getCond(flfields)+ "branch=?",filter.getBranch()));
        }
        if(!CheckNull.isEmpty(filter.getId_client())){
        	flfields.add(new FilterField(getCond(flfields)+ "id_client=?",filter.getId_client()));
        }
        if(!CheckNull.isEmpty(filter.getReqnum())){
        	flfields.add(new FilterField(getCond(flfields)+ "reqnum=?",filter.getReqnum()));
        }
        if(!CheckNull.isEmpty(filter.getReqtype())){
        	flfields.add(new FilterField(getCond(flfields)+ "reqtype=?",filter.getReqtype()));
        }
        if(!CheckNull.isEmpty(filter.getBranch_id())){
        	flfields.add(new FilterField(getCond(flfields)+ "branch_id=?",filter.getBranch_id()));
        }
        if(!CheckNull.isEmpty(filter.getKred_id())){
        	flfields.add(new FilterField(getCond(flfields)+ "kred_id=?",filter.getKred_id()));
        }
        if(!CheckNull.isEmpty(filter.getShifr_id())){
        	flfields.add(new FilterField(getCond(flfields)+ "shifr_id=?",filter.getShifr_id()));
        }
        if(!CheckNull.isEmpty(filter.getCurrency())){
        	flfields.add(new FilterField(getCond(flfields)+ "currency=?",filter.getCurrency()));
        }
        if(!CheckNull.isEmpty(filter.getAmount())){
        	flfields.add(new FilterField(getCond(flfields)+ "amount=?",filter.getAmount()));
        }
        if(!CheckNull.isEmpty(filter.getEnd_date())){
        	flfields.add(new FilterField(getCond(flfields)+ "end_date=?",filter.getEnd_date()));
        }
        if(!CheckNull.isEmpty(filter.getDbinn())){
        	flfields.add(new FilterField(getCond(flfields)+ "dbinn=?",filter.getDbinn()));
        }
        if(!CheckNull.isEmpty(filter.getDbbranch())){
        	flfields.add(new FilterField(getCond(flfields)+ "dbbranch=?",filter.getDbbranch()));
        }
        if(!CheckNull.isEmpty(filter.getDbaddress())){
        	flfields.add(new FilterField(getCond(flfields)+ "dbaddress=?",filter.getDbaddress()));
        }
        if(!CheckNull.isEmpty(filter.getV_date())){
        	flfields.add(new FilterField(getCond(flfields)+ "v_date=?",filter.getV_date()));
        }
        if(!CheckNull.isEmpty(filter.getResolve_org())){
        	flfields.add(new FilterField(getCond(flfields)+ "resolve_org=?",filter.getResolve_org()));
        }
        if(!CheckNull.isEmpty(filter.getIs_letter())){
        	flfields.add(new FilterField(getCond(flfields)+ "is_letter=?",filter.getIs_letter()));
        }
        if(!CheckNull.isEmpty(filter.getSign_client())){
        	flfields.add(new FilterField(getCond(flfields)+ "sign_client=?",filter.getSign_client()));
        }
        if(!CheckNull.isEmpty(filter.getRezkl())){
        	flfields.add(new FilterField(getCond(flfields)+ "rezkl=?",filter.getRezkl()));
        }
        if(!CheckNull.isEmpty(filter.getClient_type())){
        	flfields.add(new FilterField(getCond(flfields)+ "client_type=?",filter.getClient_type()));
        }
        if(!CheckNull.isEmpty(filter.getInn_passport())){
        	flfields.add(new FilterField(getCond(flfields)+ "inn_passport=?",filter.getInn_passport()));
        }
        if(!CheckNull.isEmpty(filter.getFac_resident())){
        	flfields.add(new FilterField(getCond(flfields)+ "fac_resident=?",filter.getFac_resident()));
        }
        if(!CheckNull.isEmpty(filter.getFac_j_code_organ_direct())){
        	flfields.add(new FilterField(getCond(flfields)+ "fac_j_code_organ_direct=?",filter.getFac_j_code_organ_direct()));
        }
        if(!CheckNull.isEmpty(filter.getFac_class_id())){
        	flfields.add(new FilterField(getCond(flfields)+ "fac_class_id=?",filter.getFac_class_id()));
        }
        if(!CheckNull.isEmpty(filter.getInn_org())){
        	flfields.add(new FilterField(getCond(flfields)+ "inn_org=?",filter.getInn_org()));
        }
        if(!CheckNull.isEmpty(filter.getName_org())){
        	flfields.add(new FilterField(getCond(flfields)+ "name_org=?",filter.getName_org()));
        }
        if(!CheckNull.isEmpty(filter.getNwp())){
        	flfields.add(new FilterField(getCond(flfields)+ "nwp=?",filter.getNwp()));
        }
        if(!CheckNull.isEmpty(filter.getQw())){
        	flfields.add(new FilterField(getCond(flfields)+ "qw=?",filter.getQw()));
        }
        if(!CheckNull.isEmpty(filter.getEtype())){
        	flfields.add(new FilterField(getCond(flfields)+ "etype=?",filter.getEtype()));
        }
        if(!CheckNull.isEmpty(filter.getType_zm())){
        	flfields.add(new FilterField(getCond(flfields)+ "type_zm=?",filter.getType_zm()));
        }
        if(!CheckNull.isEmpty(filter.getBank_inps())){
        	flfields.add(new FilterField(getCond(flfields)+ "bank_inps=?",filter.getBank_inps()));
        }
        if(!CheckNull.isEmpty(filter.getCode())){
        	flfields.add(new FilterField(getCond(flfields)+ "code=?",filter.getCode()));
        }
        flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));
        return flfields;
    }


    protected static int getCount(CreditAppFilter filter,String alias)  {
    	Connection c = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT count(*) ct FROM (select ni_req.*,ni_req_n_code.code from ni_req,ni_req_n_code where ni_req.id=ni_req_n_code.id and ni_req.branch=ni_req_n_code.branch) ");
        if(flFields.size()>0){
        	for(int i=0;i<flFields.size();i++){
        		sql.append(flFields.get(i).getSqlwhere());
        	}
        }
        try {
        	c = ConnectionPool.getConnection(alias);
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

    protected static List<CreditApp> getni_reqsFl(int pageIndex, int pageSize, CreditAppFilter filter,String alias)  {
    	List<CreditApp> list = new ArrayList<CreditApp>();
        Connection c = null;
    	dfs.setGroupingSeparator(' ');
    	decf = new DecimalFormat("###,###,###.00",dfs);
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
        	c = ConnectionPool.getConnection(alias);
        	PreparedStatement ps = c.prepareStatement(sql.toString());
        	for(params=0;params<flFields.size();params++){
        		ps.setObject(params+1, flFields.get(params).getColobject());
        	}
        	params++;
        	ps.setInt(params++,v_upperbound);
        	ps.setInt(params++,v_lowerbound);
        	ResultSet rs = ps.executeQuery();
        	while (rs.next()) {
        		list.add(new CreditApp(
        				rs.getLong("id"),
        				rs.getString("branch"),
        				rs.getString("id_client"),
        				rs.getString("reqnum"),
        				rs.getString("reqtype"),
        				rs.getString("branch_id"),
        				rs.getString("kred_id"),
        				rs.getString("shifr_id"),
        				rs.getString("currency"),
        				decf.format(rs.getDouble("amount")/100),
        				rs.getDate("end_date"),
        				rs.getString("dbinn"),
        				rs.getString("dbbranch"),
        				rs.getString("dbaddress"),
        				rs.getDate("v_date"),
        				rs.getString("resolve_org"),
        				rs.getString("is_letter"),
        				rs.getString("sign_client"),
        				rs.getString("rezkl"),
        				rs.getString("client_type"),
        				rs.getString("inn_passport"),
        				rs.getString("fac_resident")==null?rs.getString("fac_resident"):rs.getString("fac_resident").trim(),
        				rs.getString("fac_j_code_organ_direct"),
        				rs.getString("fac_class_id"),
        				rs.getString("inn_org"),
        				rs.getString("name_org"),
        				rs.getString("nwp"),
        				rs.getString("qw"),
        				rs.getString("etype"),
        				rs.getString("type_zm"),
        				rs.getString("bank_inps"),
        				rs.getString("code"),
        				rs.getString("state"),
        				rs.getString("name"),
        				rs.getDate("rdate"),
        				rs.getString("ragency"),
        				rs.getString("rreason"),
        				rs.getString("rtxt"),
        				rs.getString("rdoc")));
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	ConnectionPool.close(c);
        }
        return list;
    }

    protected static void create(CreditApp ni_req,String alias,Res res)  {
    	Connection c = null;
    	PreparedStatement ps = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		ps = c.prepareStatement("SELECT SEQ_ni_req.NEXTVAL id FROM DUAL");
    		ResultSet rs = ps.executeQuery();
    		if (rs.next()) {
    			ni_req.setId(rs.getLong("id"));
    		}
    		ps = c.prepareStatement("INSERT INTO ni_req (id, branch, id_client, reqnum, reqtype, branch_id, kred_id, shifr_id, currency, amount, end_date, dbinn, dbbranch, dbaddress, v_date, resolve_org, is_letter, sign_client, rezkl, client_type, inn_passport, fac_resident, fac_j_code_organ_direct, fac_class_id, inn_org, name_org, nwp, qw, etype, type_zm, bank_inps,state ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    		ps.setLong(1,ni_req.getId());
    		ps.setString(2,ni_req.getBranch());
    		ps.setString(3,ni_req.getId_client());
    		ps.setString(4,ni_req.getReqnum());
    		ps.setString(5,ni_req.getReqtype());
    		ps.setString(6,ni_req.getBranch_id());
    		ps.setString(7,ni_req.getKred_id());
    		ps.setString(8,ni_req.getShifr_id());
    		ps.setString(9,ni_req.getCurrency());
    		ps.setDouble(10,Double.parseDouble(ni_req.getAmount())*100);
    		ps.setDate(11,ni_req.getEnd_date()==null?null:new java.sql.Date(ni_req.getEnd_date().getTime()));
    		ps.setString(12,ni_req.getDbinn());
    		ps.setString(13,ni_req.getDbbranch());
    		ps.setString(14,ni_req.getDbaddress());
    		ps.setDate(15,ni_req.getV_date()==null?null:new java.sql.Date(ni_req.getV_date().getTime()));
    		ps.setString(16,ni_req.getResolve_org());
    		ps.setString(17,ni_req.getIs_letter().trim());
    		ps.setString(18,ni_req.getSign_client());
    		ps.setString(19,ni_req.getRezkl());
    		ps.setString(20,ni_req.getClient_type());
    		ps.setString(21,ni_req.getInn_passport());
    		ps.setString(22,ni_req.getFac_resident());
    		ps.setString(23,ni_req.getFac_j_code_organ_direct());
    		ps.setString(24,ni_req.getFac_class_id());
    		ps.setString(25,ni_req.getInn_org());
    		ps.setString(26,ni_req.getName_org());
    		ps.setString(27,ni_req.getNwp());
    		ps.setString(28,ni_req.getQw());
    		ps.setString(29,ni_req.getEtype());
    		ps.setString(30,ni_req.getType_zm());
    		ps.setString(31,ni_req.getBank_inps());
    		ps.setString(32, ni_req.getState());
    		ps.executeUpdate();
    		ps = c.prepareStatement("insert into ni_req_n_code (id,code,branch) values (?,?,?)");
    		ps.setLong(1, ni_req.getId());
    		ps.setString(2, ni_req.getCode());
    		ps.setString(3, ni_req.getBranch());
    		ps.execute();
    		c.commit();
    	} catch (Exception e) {
    		res.setCode(1);
    		res.setName(CheckNull.getPstr(e));
    		e.printStackTrace();
    		try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	} finally {
    		ConnectionPool.close(c);
    	}
    }

    protected static void update(CreditApp ni_req,String alias,Res res)  {
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		PreparedStatement ps = c.prepareStatement("UPDATE ni_req SET branch=?, id_client=?, reqnum=?, reqtype=?, branch_id=?, kred_id=?, shifr_id=?, currency=?, amount=?, end_date=?, dbinn=?, dbbranch=?, dbaddress=?, v_date=?, resolve_org=?, is_letter=?, sign_client=?, rezkl=?, client_type=?, inn_passport=?, fac_resident=?, fac_j_code_organ_direct=?, fac_class_id=?, inn_org=?, name_org=?, nwp=?, qw=?, etype=?, type_zm=?, bank_inps=?,state=?  WHERE id=?");
    		ps.setString(1,ni_req.getBranch());
    		ps.setString(2,ni_req.getId_client());
    		ps.setString(3,ni_req.getReqnum());
    		ps.setString(4,ni_req.getReqtype());
    		ps.setString(5,ni_req.getBranch_id());
    		ps.setString(6,ni_req.getKred_id());
    		ps.setString(7,ni_req.getShifr_id());
    		ps.setString(8,ni_req.getCurrency());
    		if(ni_req.getAmount()!=null){
        		ps.setDouble(9,Double.parseDouble(ni_req.getAmount())*100);
    		} else {
        		ps.setLong(9,java.sql.Types.NUMERIC);
    		}
    		ps.setDate(10,ni_req.getEnd_date()==null?null:new java.sql.Date(ni_req.getEnd_date().getTime()));
    		ps.setString(11,ni_req.getDbinn());
    		ps.setString(12,ni_req.getDbbranch());
    		ps.setString(13,ni_req.getDbaddress());
    		ps.setDate(14,ni_req.getV_date()==null?null:new java.sql.Date(ni_req.getV_date().getTime()));
    		ps.setString(15,ni_req.getResolve_org());
    		ps.setString(16,ni_req.getIs_letter()==null?null:ni_req.getIs_letter().trim());
    		ps.setString(17,ni_req.getSign_client());
    		ps.setString(18,ni_req.getRezkl());
    		ps.setString(19,ni_req.getClient_type());
    		ps.setString(20,ni_req.getInn_passport());
    		ps.setString(21,ni_req.getFac_resident());
    		ps.setString(22,ni_req.getFac_j_code_organ_direct());
    		ps.setString(23,ni_req.getFac_class_id());
    		ps.setString(24,ni_req.getInn_org());
    		ps.setString(25,ni_req.getName_org());
    		ps.setString(26,ni_req.getNwp());
    		ps.setString(27,ni_req.getQw());
    		ps.setString(28,ni_req.getEtype());
    		ps.setString(29,ni_req.getType_zm());
    		ps.setString(30,ni_req.getBank_inps());
    		ps.setString(31, ni_req.getState());
    		ps.setLong(32,ni_req.getId());
    		ps.executeUpdate();
    		ps = c.prepareStatement("update ni_req_n_code SET code=? where branch=? and id=?");
    		ps.setString(1, ni_req.getCode());
    		ps.setString(2, ni_req.getBranch());
    		ps.setLong(3, ni_req.getId());
    		ps.executeUpdate();
    		c.commit();
    	} catch (SQLException e) {
    		e.printStackTrace();
    		res.setCode(1);
    		res.setName(CheckNull.getPstr(e));
    		try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	} finally {
    		ConnectionPool.close(c);
    	}
    }
    
    public static void updateState(String rdoc,String rtxt,String preason,String ragency,Date date,String state,String id,String branch,String alias,Res res){
    	Connection c = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("update ni_req set state=?, rdoc=?, rdate=?, ragency=?, rreason=?, rtxt=?,branch=? where id=?");
			ps.setString(1, state);
			ps.setString(2, rdoc);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			ps.setString(4, ragency);
			ps.setString(5, preason);
			ps.setString(6, rtxt);
			ps.setString(7, branch);
			ps.setString(8, id);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
    }

    protected static void remove(CreditApp ni_req,String alias)  {
    	Connection c = null;
    	try {
    		c = ConnectionPool.getConnection(alias);
    		PreparedStatement ps = c.prepareStatement("DELETE FROM ni_req WHERE id=?");
    		ps.setLong(1, ni_req.getId());
    		ps.executeUpdate();
    		c.commit();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		ConnectionPool.close(c);
    	}
    }
    
    protected static void refreshCredit(String branch,String alias,Res res){
    	Connection c = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{ call proc_ni_req.CheckState(?) }");
			cs.setString(1, branch);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
    }
    
    protected static void refreshClientData(String cl_code,String alias,Res res){
    	System.out.println("3");
    	Connection c = null;
    	System.out.println("4");
    	try {
    		System.out.println("5");
			c = ConnectionPool.getConnection(alias);
			System.out.println("6");
			CallableStatement cs = c.prepareCall("{ call CreditAppControl.refresh_cl_datas(?) }");
			System.out.println("7");
			cs.setString(1, cl_code);
			System.out.println("8");
			cs.execute();
			System.out.println("9");
			c.commit();
			System.out.println("10");
		} catch (Exception e) {
			System.out.println("exeption");
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
    }
    
    protected static List<RefData> getS_mfo(String alias){
    	if(s_mfo==null){
    		s_mfo = RefDataService.getRefData("select s_mfo.bank_id data,s_mfo.bank_id||' - '||s_mfo.bank_name label from s_mfo", alias);
    	}
    	return s_mfo;
    }
    
    protected static List<RefData> getS_rezkl(String alias){
    	if(cl_type==null){
    		cl_type = RefDataService.getRefData("select SS_LD_CLIENT_TYPE.ID data,SS_LD_CLIENT_TYPE.NAME label from SS_LD_CLIENT_TYPE", alias);
    	}
    	return cl_type;
    }
    
    protected static List<RefData> getClient_type(String alias){
    	if(s_rezkl==null){
    		s_rezkl = RefDataService.getRefData("select s_rezkl.kod_rez data,s_rezkl.type_rez label from s_rezkl", alias);
    	}
    	return s_rezkl;
    }
    
    protected static List<RefData> getSign_client(String alias){
    	if(sing_client==null){
    		sing_client = RefDataService.getRefData("select ss_ld_sign_client.id data,ss_ld_sign_client.name label from ss_ld_sign_client", alias);
    	}
    	return sing_client;
    }
    
    protected static List<RefData> getS_soogun(String alias){
    	if(s_soogun==null){
    		s_soogun = RefDataService.getRefData("select s_soogun.soogu data,s_soogun.soogu1 label from s_soogun order by data", alias);
    	}
    	return s_soogun;
    }
    
    protected static List<RefData> getS_klass(String alias){
    	if(s_klass==null){
    		s_klass = RefDataService.getRefData("select s_klass.klass_id data,s_klass.klass_name label from s_klass", alias);
    	}
    	return s_klass;
    }
    
    protected static List<RefData> getIs_letter(String alias){
    	if(is_letter==null){
        	is_letter = RefDataService.getRefData("select '0' data, '0 - да' label from dual union all select '1' code, '1 - нет' name from dual", alias);
    	}
    	return is_letter;
    }
    
    public static List<RefData> getAgency(String alias){
    	if(agency==null){
    		agency = RefDataService.getRefData("select SS_NI_AGENCY.Id data,SS_NI_AGENCY.NAME label from SS_NI_AGENCY order by data", alias);
    	}
    	return agency;
    }
    
    public static List<RefData> getReason(String alias){
    	if(reason==null){
    		reason = RefDataService.getRefData("select ss_ni_reason.id data,ss_ni_reason.name label from ss_ni_reason order by data", alias);
    	}
    	return reason;
    }
    
    protected static List<RefData> getTypeClient(String cl_code,String branch){ // ---------- Тип клиента
		return Utils.getRefData("select client.code_type data,client.code_resident label from client where client.id_client='"+cl_code+"'",branch);
	}
    
    protected static List<RefData> getJClient(String cl_code,String branch){ // ---------- Тип клиента
		return Utils.getRefData("select client_j.number_tax_registration data,client_j.name label from client_j where id='"+cl_code+"'",branch);
	}
    
    protected static List<RefData> getPClient(String cl_code,String mfo,String branch){ // ---------- Тип клиента
		return Utils.getRefData("select client_p.passport_serial||client_p.passport_number data,client_p.name label from client_p where id='"+cl_code+"' and branch='"+mfo+"'",branch);
	}
    
    protected static List<RefData> getWorkFromClientId(String branch,String client_id){
    	return Utils.getRefData("select j.number_tax_registration, j.name from client_j j where exists (select * from card_client_work w where w.branch="+branch+" and w.employee_id="+client_id+" and j.branch=w.branch and j.id=w.customer_id)", "");
    }
    
    protected static String getNikiState(String id,String branch,String alias){
    	String temp = null;
    	try {
        	temp = Utils.getData("select * from (select state_name from (select * from ldr2_01 union all select * from ldr2_01_arh union all select * from ldr2_02 union all select * from ldr2_02_arh) n where n.niki_id="+id+" and n.branch='"+branch+"' order by n.id desc) where rownum=1",alias);
        	if(temp==null||temp.equals("")){ temp = "Не создан"; }
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
    	return temp;
    }
    
    public static String getKatmInfo(String branch,long claim_id){
		String res = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select k.data from katm_analyze k where k.branch =? and k.claim_id =? and rownum=1 ");
			ps.setString(1, branch);
			ps.setLong(2, claim_id);
			rs = ps.executeQuery();
			if(rs.next()){
			res = rs.getString("DATA");
		//	System.out.println("res: "+res);
		//	ISLogger.getLogger().error("res1122 :"+res);
		      			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;			
	}
			
}
