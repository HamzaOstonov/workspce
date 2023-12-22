package com.is.creditanket;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.creditanket.grids_models.GuarrGrid;
import com.is.creditanket.table_models.CurrentCredit;
import com.is.creditanket.table_models.Ld_char;
import com.is.creditanket.table_models.Ld_exp;
import com.is.creditanket.table_models.Ld_forms;
import com.is.creditanket.table_models.Ld_gr;
import com.is.creditanket.table_models.Ld_graf;
import com.is.creditanket.table_models.Ld_guar_blocks;
import com.is.creditanket.table_models.Ld_guar_cadastre;
import com.is.creditanket.table_models.Ld_guar_car;
import com.is.creditanket.table_models.Ld_guar_equipment;
import com.is.creditanket.table_models.Ld_guarr;
import com.is.creditanket.table_models.Ld_rate;
import com.is.creditanket.table_models.V_ld_account;
import com.is.creditapp.CreditApp;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class CreditService {
	
	private static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private static String psql1 ="select t.* from(select t.*,rownum rwnm from (select * from (";
    private static String psql2 =" ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
    private static String msql ="select ld_char.branch,ld_char.id,ld_char.niki_id,ld_forms.d_date,ld_forms.client,client.name,ld_char.currency,ld_char.ld_amount/100 amount,state_ldform.name cname,ld_forms.state,ss_ld_status.name sname,ld_char.status from ld_char,ld_forms,client,state_ldform,ss_ld_status where ld_forms.branch=ld_char.branch and ld_char.id=ld_forms.id and client.id_client=ld_forms.client and client.branch=ld_forms.branch and ss_ld_status.id=ld_char.status and ld_forms.state=state_ldform.id ";
    private static char format = new DecimalFormat().getDecimalFormatSymbols().getDecimalSeparator();
	private static char not_format = format==','?'.':',';
    
    private static String getCond(List<FilterField> flfields){
    	if(flfields.size()>0){
    		return " and ";
    	} else return " and ";
    }
    
    private static List<FilterField> getFilterFields(CurrentCredit credit){
    	List<FilterField> flfields = new ArrayList<FilterField>();
    	if(credit!=null&&credit.getLd_char()!=null&&!CheckNull.isEmpty(credit.getLd_char().getBranch())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_char.branch=?",credit.getLd_char().getBranch()));
    	} if(credit!=null&&credit.getLd_char()!=null&&!CheckNull.isEmpty(credit.getLd_char().getId())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_char.id=?",credit.getLd_char().getId()));
    	} if(credit!=null&&credit.getLd_char()!=null&&!CheckNull.isEmpty(credit.getLd_char().getNiki_id())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_char.niki_id=?",credit.getLd_char().getNiki_id()));
    	} if(credit!=null&&credit.getLd_forms()!=null&&!CheckNull.isEmpty(credit.getLd_forms().getD_date())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_forms.d_date=?",credit.getLd_forms().getD_date()));
    	} if(credit!=null&&credit.getLd_forms()!=null&&!CheckNull.isEmpty(credit.getLd_forms().getClient())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_forms.client=?",credit.getLd_forms().getClient()));
    	} if(credit!=null&&credit.getLd_char()!=null&&!CheckNull.isEmpty(credit.getLd_char().getCurrency())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_char.currency=?",credit.getLd_char().getCurrency()));
    	} if(credit!=null&&credit.getLd_char()!=null&&!CheckNull.isEmpty(credit.getLd_char().getLd_amount())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_char.ld_amount=(?)*100",credit.getLd_char().getLd_amount()));
    	} if(credit!=null&&credit.getLd_forms()!=null&&!CheckNull.isEmpty(credit.getLd_forms().getState())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_forms.state=?",credit.getLd_forms().getState()));
    	} if(credit!=null&&credit.getLd_char()!=null&&!CheckNull.isEmpty(credit.getLd_char().getStatus())){
    		flfields.add(new FilterField(getCond(flfields)+ "ld_char.status=?",credit.getLd_char().getStatus()));
    	}
    	return flfields;
    }
    
    protected static int getCount(CurrentCredit filter,String alias)  {
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
        int n = 0;
        List<FilterField> flFields =getFilterFields(filter);
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*) ct from ld_char,ld_forms,client,state_ldform,ss_ld_status where ld_forms.branch=ld_char.branch and ld_char.id=ld_forms.id and client.id_client=ld_forms.client and client.branch=ld_forms.branch and ss_ld_status.id=ld_char.status and ld_forms.state=state_ldform.id ");
        if(flFields.size()>0){
        	for(int i=0;i<flFields.size();i++){
        		sql.append(flFields.get(i).getSqlwhere());
        	}
        }
        try {
        	c = ConnectionPool.getConnection(alias);
        	ps = c.prepareStatement(sql.toString());
        	for(int k=0;k<flFields.size();k++){
        		ps.setObject(k+1, flFields.get(k).getColobject());
        	}
        	rs = ps.executeQuery();
        	if (rs.next()) {
        		n = rs.getInt(1);
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        	ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
        	Utils.close(rs);
        	Utils.close(ps);
        	ConnectionPool.close(c);
        }
        return n;
    }
    
    protected static List<CurrentCredit> getCreditFl(int pageIndex, int pageSize, CurrentCredit filter,String alias)  {
    	List<CurrentCredit> list = new ArrayList<CurrentCredit>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
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
        sql.append(" order by id desc ");
        sql.append(psql2);
        try {
        	c = ConnectionPool.getConnection(alias);
        	ps = c.prepareStatement(sql.toString());
        	System.out.println(sql);
        	for(params=0;params<flFields.size();params++){
        		ps.setObject(params+1, flFields.get(params).getColobject());
        	}
        	params++;
        	ps.setInt(params++,v_upperbound);
        	ps.setInt(params++,v_lowerbound);
        	rs = ps.executeQuery();
        	while (rs.next()) {
        		CurrentCredit credit = new CurrentCredit();
        		Ld_char ld_char = new Ld_char();
        		Ld_forms ld_forms = new Ld_forms();
        		ld_char.setBranch(rs.getString("branch"));
        		ld_char.setId(rs.getLong("id"));
        		ld_char.setNiki_id(rs.getLong("niki_id"));
        		ld_forms.setD_date(rs.getDate("d_date"));
        		ld_forms.setClient(rs.getString("client"));
        		ld_char.setCurrency(rs.getString("currency"));
        		ld_char.setLd_amount(rs.getDouble("amount"));
        		ld_forms.setState(rs.getInt("state"));
        		ld_char.setStatus(rs.getLong("status"));
        		credit.setLd_char(ld_char);
        		credit.setLd_forms(ld_forms);
        		list.add(credit);
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        	ISLogger.getLogger().error(CheckNull.getPstr(e));
        } finally {
        	Utils.close(rs);
        	Utils.close(ps);
        	ConnectionPool.close(c);
        }
        return list;
    }
    
    protected static CurrentCredit getCurrentCredit(Long id, String branch, String alias){
    	CurrentCredit credit = new CurrentCredit();
    	Connection c = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			credit.setLd_char(getLdChar(id,branch,c));
			credit.setLd_forms(getLdForms(id,branch,c));
			credit.setLd_guarr(getLdGuarr(id,branch,c));
			credit.setLd_exp(getLdExp(id,branch,c));
			credit.setLd_gr(getLdGr(id, branch, c));
			credit.setV_ld_account(getVLdAccount(id,branch,c));
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
    	return credit;
    }
    
    private static Ld_char getLdChar(Long id, String branch, Connection c) throws Exception{
    	Ld_char ld_char = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			ps = c.prepareStatement("select * from ld_char where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				ld_char = new Ld_char();
				ld_char.setAcceptance(rs.getInt("acceptance"));
				ld_char.setAl_num(rs.getString("al_num"));
				ld_char.setAmount(rs.getDouble("AMOUNT")/100);
				ld_char.setAmount_inv(rs.getDouble("AMOUNT_INV"));
				ld_char.setArrears_reason(rs.getString("ARREARS_REASON"));
				ld_char.setBranch(branch);
				ld_char.setCalc_id(rs.getLong("CALC_ID"));
				ld_char.setCl_num(rs.getInt("CL_NUM"));
				ld_char.setCrc_date(rs.getDate("CRC_DATE"));
				ld_char.setCrc_num(rs.getString("CRC_NUM"));	
				ld_char.setCres(rs.getInt("cres"));
				ld_char.setCurrency(rs.getString("CURRENCY"));
				ld_char.setCurrency_inv(rs.getString("CURRENCY_INV"));
				ld_char.setDate_begin(rs.getDate("DATE_BEGIN"));
				ld_char.setDate_close(rs.getDate("DATE_CLOSE"));
				ld_char.setDate_end(rs.getDate("DATE_END"));
				ld_char.setDate_fee(rs.getDate("DATE_FEE"));
				ld_char.setEq_date(rs.getDate("EQ_DATE"));
				ld_char.setEq_num(rs.getString("EQ_NUM"));
				ld_char.setFaza_abr(rs.getInt("FAZA_ABR"));
				ld_char.setForeign_means(rs.getInt("FOREIGN_MEANS"));
				ld_char.setId(id);
				ld_char.setIs_inv(rs.getString("IS_INV"));
				ld_char.setIs_tax(rs.getInt("IS_TAX"));
				ld_char.setKlass_id(rs.getString("KLASS_ID"));
				ld_char.setKlassp_id(rs.getString("KLASSP_ID"));
				ld_char.setKod_fin(rs.getString("KOD_FIN"));
				ld_char.setKod_int(rs.getString("KOD_INT"));
				ld_char.setKred_id(rs.getString("KRED_ID"));
				ld_char.setKred_id_cb(rs.getString("KRED_ID_CB").trim());
				ld_char.setLd_amount(rs.getDouble("LD_AMOUNT")/100);
				ld_char.setLd_date(rs.getDate("LD_DATE"));
				ld_char.setLd_num(rs.getString("LD_NUM"));
				ld_char.setLd_period(rs.getInt("LD_PERIOD"));
				ld_char.setLd_type(rs.getInt("LD_TYPE"));
				ld_char.setLdays(rs.getInt("LDAYS"));
				ld_char.setNiki_id(rs.getLong("NIKI_ID"));
				ld_char.setP_date(rs.getDate("P_DATE"));
				ld_char.setP_num(rs.getString("P_NUM"));
				ld_char.setPk(rs.getLong("PK"));
				ld_char.setProd_name(rs.getString("PROD_NAME"));
				ld_char.setShifr_id(rs.getString("SHIFR_ID"));
				ld_char.setSred_id(rs.getString("SRED_ID"));
				ld_char.setStatus(rs.getLong("STATUS"));
				ld_char.setStatus_prc(rs.getInt("STATUS_PRC"));
				ld_char.setT_author(rs.getString("T_AUTHOR"));
				ld_char.setT_date(rs.getDate("T_DATE"));
				ld_char.setT_type(rs.getInt("T_TYPE"));
				ld_char.setTarget(rs.getString("TARGET"));
				ld_char.setTax_rate(rs.getDouble("TAX_RATE"));
				ld_char.setTerm_type(rs.getLong("TERM_TYPE"));
				ld_char.setType_id(rs.getLong("TYPE_ID"));
				ld_char.setUse_branch(rs.getString("USE_BRANCH"));
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
    	return ld_char;
    }
    
    private static Ld_forms getLdForms(Long id,String branch,Connection c) throws Exception{
    	Ld_forms ld_form = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			ps = c.prepareStatement("select * from ld_forms f where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				ld_form = new Ld_forms();
				ld_form.setAcc_state(rs.getInt("ACC_STATE"));
				ld_form.setBranch(branch);
				ld_form.setClient(rs.getString("CLIENT"));
				ld_form.setD_confirm(rs.getDate("D_CONFIRM"));
				ld_form.setD_date(rs.getDate("D_DATE"));
				ld_form.setDate_start(rs.getDate("DATE_START"));
				ld_form.setDoc_date_st(rs.getDate("DOC_DATE_ST"));
				ld_form.setDoc_name_st(rs.getString("DOC_NAME_ST"));
				ld_form.setDoc_num_st(rs.getString("DOC_NUM_ST"));
				ld_form.setEmp_count(rs.getInt("EMP_COUNT"));
				ld_form.setEmp_id(rs.getInt("EMP_ID"));
				ld_form.setHead_of_bank(rs.getString("HEAD_OF_BANK"));
				ld_form.setId(id);
				ld_form.setIs_building(rs.getInt("IS_BUILDING"));
				ld_form.setIs_ld(rs.getInt("IS_LD"));
				ld_form.setIs_sec(rs.getInt("IS_SEC"));
				ld_form.setKlass_id(rs.getString("KLASS_ID"));
				ld_form.setKred_id(rs.getString("KRED_ID"));
				ld_form.setLd_type(rs.getInt("LD_TYPE"));
				ld_form.setPhone_chief(rs.getString("PHONE_CHIEF"));
				ld_form.setPhone_dir(rs.getString("PHONE_DIR"));
				ld_form.setSec_amount(rs.getLong("SEC_AMOUNT"));
				ld_form.setStage_chief(rs.getDouble("STAGE_CHIEF"));
				ld_form.setStage_dir(rs.getDouble("STAGE_DIR"));
				ld_form.setState(rs.getInt("STATE"));
				ld_form.setState_sap(rs.getInt("STATE_SAP"));
				ld_form.setStatus_build_up_prc(rs.getInt("STATUS_BUILD_UP_PRC"));
				ld_form.setSub_addr(rs.getString("SUB_ADDR"));
				ld_form.setSub_name(rs.getString("SUB_NAME"));
				ld_form.setTransh(rs.getString("TRANSH"));
				ld_form.setType_id(rs.getLong("TYPE_ID"));
				ld_form.setUse_id(rs.getInt("USE_ID"));
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return ld_form;
    }
    
    public static String getGuarrValue(String type_field,String table_name,String field_name,Long id,String branch,Long pk) throws SQLException{
    	String value = "";
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sub_field = table_name.equalsIgnoreCase("ld_guarr")||table_name.equalsIgnoreCase("LD_MAT")?"pk":"pk_ld_guar";
    	Statement st = null;
    	field_name = type_field.equalsIgnoreCase("date")?"to_char("+field_name+",'dd.MM.yyyy')":field_name;
    	try {
			c = ConnectionPool.getConnection();
			st = c.createStatement();
	        st.executeUpdate("ALTER SESSION SET NLS_DATE_FORMAT='dd.mm.yyyy'");
			ps = c.prepareStatement("select "+field_name+" from "+table_name+" where id = ? and branch = ? and "+sub_field+" = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.setLong(3, pk);
			rs = ps.executeQuery();
			if(rs.next()){
				value = rs.getString(1);
			};
    	} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			if(st!=null){st.close();}
			ConnectionPool.close(c);
		}
    	return value;
    }
    
    private static Ld_guarr[] getLdGuarr(Long id,String branch,Connection c) throws Exception{
    	Ld_guarr[] guarrs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Integer cnt = null;
    	try {
    		ps = c.prepareStatement("select count(*) from ld_guarr where id = ? and branch = ?");
    		ps.setLong(1, id);
    		ps.setString(2, branch);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			cnt = rs.getInt(1);
    		}
			ps = c.prepareStatement("select * from ld_guarr where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			guarrs = new Ld_guarr[cnt];
			cnt = 0;
			while(rs.next()){
				guarrs[cnt] = new Ld_guarr();
//				guarrs[cnt].setAccount(rs.getString("ACCOUNT"));
//				guarrs[cnt].setAcomp_curr(rs.getString("ACOMP_CURR"));
//				guarrs[cnt].setAcomp_date(rs.getDate("ACOMP_DATE"));
//				guarrs[cnt].setAcomp_name(rs.getString("ACOMP_NAME"));
//				guarrs[cnt].setAcomp_summa(rs.getDouble("ACOMP_SUMMA"));
//				guarrs[cnt].setAddress(rs.getString("ADDRESS"));
//				guarrs[cnt].setAmount(rs.getDouble("AMOUNT"));
//				guarrs[cnt].setBank_name(rs.getString("BANK_NAME"));
				guarrs[cnt].setBranch(branch);
//				guarrs[cnt].setBuild_constr_type(rs.getInt("BUILD_CONSTR_TYPE"));
//				guarrs[cnt].setBuilding_kind(rs.getInt("BUILDING_KIND"));
//				guarrs[cnt].setBuilding_num(rs.getInt("BUILDING_NUM"));
//				guarrs[cnt].setBuilding_square(rs.getInt("BUILDING_SQUARE"));
//				guarrs[cnt].setBuilding_type(rs.getInt("BUILDING_TYPE"));
//				guarrs[cnt].setBuilding_year(rs.getInt("BUILDING_YEAR"));
//				guarrs[cnt].setCadastr_org_distr(rs.getString("CADASTR_ORG_DISTR"));
//				guarrs[cnt].setCadastr_org_region(rs.getString("CADASTR_ORG_REGION"));
//				guarrs[cnt].setCadastr_place_adres(rs.getString("CADASTR_PLACE_ADRES"));
//				guarrs[cnt].setCadastr_place_distr(rs.getString("CADASTR_PLACE_DISTR"));
//				guarrs[cnt].setCadastr_place_region(rs.getString("CADASTR_PLACE_REGION"));
//				guarrs[cnt].setCadastr_place_town(rs.getInt("CADASTR_PLACE_TOWN"));
//				guarrs[cnt].setCadastr_place_x(rs.getString("CADASTR_PLACE_X"));
//				guarrs[cnt].setCadastr_place_y(rs.getString("CADASTR_PLACE_Y"));
//				guarrs[cnt].setCanbuy(rs.getLong("CANBUY"));
//				guarrs[cnt].setCode_subject(rs.getString("CODE_SUBJECT"));
//				guarrs[cnt].setCurrency(rs.getString("CURRENCY"));
//				guarrs[cnt].setDate_operation(rs.getDate("DATE_OPERATION"));
//				guarrs[cnt].setDepositary(rs.getString("DEPOSITARY"));
//				guarrs[cnt].setDepositary_account(rs.getString("DEPOSITARY_ACCOUNT"));
//				guarrs[cnt].setDistr_id(rs.getString("DISTR_ID"));
//				guarrs[cnt].setDoc_date(rs.getDate("DOC_DATE"));
//				guarrs[cnt].setDoc_num(rs.getString("DOC_NUM"));
//				guarrs[cnt].setEconomical_zone(rs.getInt("ECONOMICAL_ZONE"));
//				guarrs[cnt].setEnd_date(rs.getDate("END_DATE"));
//				guarrs[cnt].setEval_report_num(rs.getString("EVAL_REPORT_NUM"));
				guarrs[cnt].setGuar_id(rs.getString("GUAR_ID"));
//				guarrs[cnt].setHasdepo(rs.getInt("HASDEPO"));
//				guarrs[cnt].setHasdoc(rs.getInt("HASDOC"));
//				guarrs[cnt].setHome(rs.getString("HOME"));
//				guarrs[cnt].setHome_num(rs.getString("HOME_NUM"));
				guarrs[cnt].setId(id);
//				guarrs[cnt].setId_client(rs.getString("ID_CLIENT"));
//				guarrs[cnt].setInn(rs.getString("INN"));
//				guarrs[cnt].setInn_reestr(rs.getString("INN_REESTR"));
//				guarrs[cnt].setInsc_date(rs.getDate("INSC_DATE"));
//				guarrs[cnt].setInsc_date_cl(rs.getDate("INSC_DATE_CL"));
//				guarrs[cnt].setInsc_inn(rs.getString("INSC_INN"));
//				guarrs[cnt].setInsc_name(rs.getString("INSC_NAME"));
//				guarrs[cnt].setInsc_num(rs.getString("INSC_NUM"));
//				guarrs[cnt].setKlass_o(rs.getString("KLASS_O"));
//				guarrs[cnt].setLis_date(rs.getDate("LIS_DATE"));
//				guarrs[cnt].setLis_num(rs.getString("LIS_NUM"));
//				guarrs[cnt].setMassa(rs.getDouble("MASSA"));
//				guarrs[cnt].setMassiv(rs.getString("MASSIV"));
//				guarrs[cnt].setMfo(rs.getString("MFO"));
//				guarrs[cnt].setName(rs.getString("NAME"));
//				guarrs[cnt].setName2(rs.getString("NAME2"));
//				guarrs[cnt].setNiki_gr_branch(rs.getString("NIKI_GR_BRANCH"));
//				guarrs[cnt].setNiki_gr_code_type(rs.getString("NIKI_GR_CODE_TYPE"));
//				guarrs[cnt].setNiki_inn(rs.getString("NIKI_INN"));
//				guarrs[cnt].setNiki_owner(rs.getString("NIKI_OWNER"));
//				guarrs[cnt].setNiki_res1(rs.getString("NIKI_RES1"));
//				guarrs[cnt].setNiki_res2(rs.getString("NIKI_RES2"));
//				guarrs[cnt].setNiki_soogun(rs.getString("NIKI_SOOGUN"));
//				guarrs[cnt].setNotarial_doc_numb(rs.getString("NOTARIAL_DOC_NUMB"));
//				guarrs[cnt].setNotarial_office_num(rs.getString("NOTARIAL_OFFICE_NUM"));
				guarrs[cnt].setPk(rs.getLong("PK"));
//				guarrs[cnt].setPolis_date(rs.getDate("POLIS_DATE"));
//				guarrs[cnt].setPolis_num(rs.getString("POLIS_NUM"));
//				guarrs[cnt].setRegion_id(rs.getString("REGION_ID"));
//				guarrs[cnt].setSer_eval_company(rs.getString("SER_EVAL_COMPANY"));
//				guarrs[cnt].setSertificate_num(rs.getString("SERTIFICATE_NUM"));
//				guarrs[cnt].setSertificate_rate(rs.getDouble("SERTIFICATE_RATE"));
//				guarrs[cnt].setSertificate_ser(rs.getString("SERTIFICATE_SER"));
//				guarrs[cnt].setSign_depo(rs.getInt("SIGN_DEPO"));
//				guarrs[cnt].setSowing_area(rs.getInt("SOWING_AREA"));
//				guarrs[cnt].setStart_date(rs.getDate("START_DATE"));
//				guarrs[cnt].setStock_count(rs.getLong("STOCK_COUNT"));
//				guarrs[cnt].setStock_diskont(rs.getLong("STOCK_DISKONT"));
//				guarrs[cnt].setStock_name(rs.getString("STOCK_NAME"));
//				guarrs[cnt].setStock_nominal_value(rs.getLong("STOCK_NOMINAL_VALUE"));
//				guarrs[cnt].setStreet(rs.getString("STREET"));
//				guarrs[cnt].setZr_term(rs.getInt("ZR_TERM"));
//				guarrs[cnt].setLd_guar_car(getCar(id, branch, guarrs[cnt].getPk(), c));
//				guarrs[cnt].setLd_guar_blocks(getBlock(id, branch, guarrs[cnt].getPk(), c));
//				guarrs[cnt].setLd_guar_cadastre(getCadastre(id, branch, guarrs[cnt].getPk(), c));
//				guarrs[cnt].setLd_guar_equipment(getEquipment(id, branch, guarrs[cnt].getPk(), c));
				++cnt;
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
    	return guarrs;
    }
    
//    private static Ld_guar_blocks getBlock(Long id,String branch,Long pk,Connection c) throws Exception{
//    	Ld_guar_blocks block = null;
//    	PreparedStatement ps = null;
//    	ResultSet rs = null;
//    	try {
//			ps = c.prepareStatement("select * from ld_guar_blocks where id = ? and branch = ? and pk_ld_guar = ?");
//			ps.setLong(1, id);
//			ps.setString(2, branch);
//			ps.setLong(3, pk);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				block = new Ld_guar_blocks();
//				block.setBranch(branch);
//				block.setBlock_name(rs.getString("BLOCK_NAME"));
//				block.setCost(rs.getLong("COST"));
//				block.setDescription(rs.getString("DESCRIPTION"));
//				block.setId(id);
//				block.setId_nn(rs.getLong("ID_NN"));
//				block.setPk(rs.getLong("PK"));
//				block.setPk_ld_guar(pk);
//				block.setSquare(rs.getDouble("SQUARE"));
//			}
//    	} finally {
//			Utils.close(rs);
//			Utils.close(ps);
//		}
//    	return block;
//    }
//    
//    private static Ld_guar_cadastre getCadastre(Long id,String branch,Long pk,Connection c) throws Exception{
//    	Ld_guar_cadastre cadastre = null;
//    	PreparedStatement ps = null;
//    	ResultSet rs = null;
//    	try {
//			ps = c.prepareStatement("select * from ld_guar_cadastre where id = ? and branch = ? and pk_ld_guar = ?");
//			ps.setLong(1, id);
//			ps.setString(2, branch);
//			ps.setLong(3, pk);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				cadastre = new Ld_guar_cadastre();
//				cadastre.setBranch(branch);
//				cadastre.setCadastre_num(rs.getString("CADASTRE_NUM"));
//				cadastre.setCadastre_type(rs.getInt("CADASTRE_TYPE"));
//				cadastre.setCertificate_date(rs.getDate("CERTIFICATE_DATE"));
//				cadastre.setCertificate_num(rs.getString("CERTIFICATE_NUM"));
//				cadastre.setId(id);
//				cadastre.setOvnership(rs.getInt("OVNERSHIP"));
//				cadastre.setPk(rs.getLong("PK"));
//				cadastre.setPk_ld_guar(pk);
//				cadastre.setReyestr_num(rs.getString("REYESTR_NUM"));
//				cadastre.setSquare(rs.getDouble("SQUARE"));
//			}
//		} finally {
//			Utils.close(rs);
//			Utils.close(ps);
//		}
//    	return cadastre;
//    }
//    
//    private static Ld_guar_car getCar(Long id,String branch,Long pk,Connection c) throws Exception{
//    	Ld_guar_car car = null;
//    	PreparedStatement ps = null;
//    	ResultSet rs = null;
//    	try {
//			ps = c.prepareStatement("select * from ld_guar_car where id = ? and branch = ? and pk_ld_guar = ?");
//			ps.setLong(1, id);
//			ps.setString(2, branch);
//			ps.setLong(3, pk);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				car = new Ld_guar_car();
//				car.setBody_num(rs.getString("BODY_NUM"));
//				car.setBranch(branch);
//				car.setCar_marka(rs.getLong("CAR_MARKA"));
//				car.setCar_model(rs.getLong("CAR_MODEL"));
//				car.setCar_type(rs.getLong("CAR_TYPE"));
//				car.setChassis_number(rs.getString("CHASSIS_NUMBER"));
//				car.setCode_country(rs.getString("CODE_COUNTRY"));
//				car.setColor(rs.getInt("COLOR"));
//				car.setDate_made(rs.getInt("DATE_MADE"));
//				car.setDoc_date(rs.getDate("DOC_DATE"));
//				car.setDoc_ser_num(rs.getString("DOC_SER_NUM"));
//				car.setEngine_num(rs.getString("ENGINE_NUM"));
//				car.setId(id);
//				car.setMileage(rs.getLong("MILEAGE"));
//				car.setPk(rs.getLong("PK"));
//				car.setPk_ld_guar(pk);
//				car.setPosition(rs.getInt("POSITION"));
//				car.setState_number(rs.getString("STATE_NUMBER"));
//			}
//		} finally {
//			Utils.close(rs);
//			Utils.close(ps);
//		}
//    	return car;
//    }
//    
//    private static Ld_guar_equipment getEquipment(Long id,String branch,Long pk,Connection c) throws Exception{
//    	Ld_guar_equipment equipment = null;
//    	PreparedStatement ps = null;
//    	ResultSet rs = null;
//    	try {
//			ps = c.prepareStatement("select * from ld_guar_equipment where id = ? and branch = ? and pk_ld_guar = ?");
//			ps.setLong(1, id);
//			ps.setString(2, branch);
//			ps.setLong(3, pk);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				equipment = new Ld_guar_equipment();
//				equipment.setBranch(branch);
//				equipment.setCountry(rs.getString("COUNTRY"));
//				equipment.setDate_made(rs.getInt("DATE_MADE"));
//				equipment.setDate_operation(rs.getDate("DATE_OPERATION"));
//				equipment.setDoc_date(rs.getDate("DOC_DATE"));
//				equipment.setDoc_num(rs.getString("DOC_NUM"));
//				equipment.setEq_place_adres(rs.getString("EQ_PLACE_ADRES"));
//				equipment.setEq_place_distr(rs.getString("EQ_PLACE_DISTR"));
//				equipment.setEq_place_region(rs.getString("EQ_PLACE_REGION"));
//				equipment.setEq_place_town(rs.getInt("EQ_PLACE_TOWN"));
//				equipment.setEq_place_x(rs.getString("EQ_PLACE_X"));
//				equipment.setEq_place_y(rs.getString("EQ_PLACE_Y"));
//				equipment.setEq_type(rs.getString("EQ_TYPE"));
//				equipment.setId(id);
//				equipment.setId_nn(rs.getLong("ID_NN"));
//				equipment.setInvent_num(rs.getString("INVENT_NUM"));
//				equipment.setManufacturer(rs.getString("MANUFACTURER"));
//				equipment.setName(rs.getString("NAME"));
//				equipment.setPk(rs.getLong("PK"));
//				equipment.setPk_ld_guar(pk);
//				equipment.setPrice_market(rs.getDouble("PRICE_MARKET"));
//				equipment.setPrice_zalog(rs.getDouble("PRICE_ZALOG"));
//				equipment.setReason(rs.getString("REASON"));
//			}
//		} finally {
//			Utils.close(rs);
//			Utils.close(ps);
//		}
//    	return equipment;
//    }
    
    protected static Ld_exp[] getLdExp(Long id,String branch,Connection c) throws Exception{
    	Ld_exp[] exp = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int cnt = 0;
    	try {
			ps = c.prepareStatement("select count(*) from ld_exp where id=? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			System.out.println("CNT - "+cnt);
			ps = c.prepareStatement("select * from ld_exp where id=? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			exp = new Ld_exp[cnt];
			cnt = 0;
			while(rs.next()){
				exp[cnt] = new Ld_exp();
				exp[cnt].setBranch(branch);;
				exp[cnt].setExp_id(rs.getLong("EXP_ID"));
				exp[cnt].setExt_days(rs.getLong("EXT_DAYS"));
				exp[cnt].setId(id);
				exp[cnt].setPay_method(rs.getLong("PAY_METHOD"));
				exp[cnt].setRate_method(rs.getLong("RATE_METHOD"));
				exp[cnt].setRate_type(rs.getLong("RATE_TYPE"));
				exp[cnt].setLd_rates(getLdRates(id, branch, exp[cnt].getExp_id(), c));
				System.out.println("INDEX = "+cnt);
				++cnt;
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
    	return exp;
    }
    
    private static Ld_rate[] getLdRates(Long id,String branch,Long exp_id,Connection c) throws Exception{
    	Ld_rate[] rates = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int cnt = 0;
    	try {
    		ps = c.prepareStatement("select count(*) from ld_rate where id= ? and branch= ? and exp_id = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.setLong(3, exp_id);
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			ps = c.prepareStatement("select * from ld_rate where id= ? and branch= ? and exp_id = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.setLong(3, exp_id);
			rs = ps.executeQuery();
			rates = new Ld_rate[cnt];
			cnt = 0;
			while(rs.next()){
				rates[cnt] = new Ld_rate();
				rates[cnt].setAct(rs.getString("ACT"));
				rates[cnt].setBranch(branch);
				rates[cnt].setCoeff(rs.getDouble("COEFF"));
				rates[cnt].setDate_close(rs.getDate("DATE_CLOSE"));
				rates[cnt].setDate_open(rs.getDate("DATE_OPEN"));
				rates[cnt].setExp_id(exp_id);
				rates[cnt].setId(id);
				rates[cnt].setRate(rs.getDouble("RATE"));
				rates[cnt].setRate_id(rs.getLong("RATE_ID"));
				rates[cnt].setNew(false);
				++cnt;
			}
    	} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
    	return rates;
    }
    
    protected static void deleteLdExp(Ld_exp exp,Connection c) throws Exception{
    	PreparedStatement ps = null;
    	try {
    		deleteLdRate(exp, c);
    		ps = c.prepareStatement("delete from ld_exp where branch = ? and id = ? and exp_id = ?");
			ps.setString(1, exp.getBranch());
			ps.setLong(2, exp.getId());
			ps.setLong(3, exp.getExp_id());
			ps.execute();
		} finally {
			Utils.close(ps);
		}
    }
    
    protected static void deleteLdRate(Ld_exp exp,Connection c) throws Exception{
    	PreparedStatement ps = null;
    	try {
			ps = c.prepareStatement("delete from ld_rate where branch = ? and id = ? and exp_id = ? and act='A'");
			ps.setString(1, exp.getBranch());
			ps.setLong(2, exp.getId());
			ps.setLong(3, exp.getExp_id());
			ps.execute();
		} finally {
			Utils.close(ps);
		}
    }
    
    private static Ld_gr[] getLdGr(Long id,String branch,Connection c) throws Exception{
    	Ld_gr[] grs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int cnt = 0;
    	try {
    		ps = c.prepareStatement("select count(*) from ld_gr where id = ? and branch = ? and oper_id != 3");
    		ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
    		ps = c.prepareStatement("select * from ld_gr where id = ? and branch = ? and oper_id != 3");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			grs = new Ld_gr[cnt];
			cnt = 0;
			while(rs.next()){
				grs[cnt] = new Ld_gr();
				grs[cnt].setBranch(branch);
				grs[cnt].setDate_from(rs.getDate("DATE_FROM"));
				grs[cnt].setDate_to(rs.getDate("DATE_TO"));
				grs[cnt].setDay(rs.getInt("DAY"));
				grs[cnt].setDsumma(rs.getDouble("DSUMMA"));
				grs[cnt].setExp_id(rs.getString("EXP_ID"));
				grs[cnt].setExt_method(rs.getInt("EXT_METHOD"));
				grs[cnt].setGraf_method(rs.getInt("GRAF_METHOD"));
				grs[cnt].setId(id);
				grs[cnt].setNum(rs.getInt("NUM"));
				grs[cnt].setOper_id(rs.getLong("OPER_ID"));
				grs[cnt].setPay_period(rs.getInt("PAY_PERIOD"));
				grs[cnt].setPk(rs.getLong("PK"));
				grs[cnt].setLdgrafs(getLdGraf(id,branch,grs[cnt].getOper_id(),c));
				++cnt;
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
    	return grs;
    }
    
    private static Ld_graf[] getLdGraf(Long id,String branch,Long oper_id,Connection c) throws Exception{
    	Ld_graf[] grafs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	PreparedStatement pss = null;
    	ResultSet rss = null;
    	int cnt = 0;
    	try {
			ps = c.prepareStatement("select count(*) from ld_graf where id = ? and branch = ? and oper_id= ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.setLong(3, oper_id);
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			ps = c.prepareStatement("select * from ld_graf where id = ? and branch = ? and oper_id= ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.setLong(3, oper_id);
			rs = ps.executeQuery();
			grafs = new Ld_graf[cnt];
			cnt = 0;
			while(rs.next()){
				grafs[cnt] = new Ld_graf();
				grafs[cnt].setBranch(branch);
				grafs[cnt].setExp_id(rs.getLong("EXP_ID"));
				grafs[cnt].setId(id);
				grafs[cnt].setNum(rs.getLong("NUM"));
				grafs[cnt].setOper_id(oper_id);
				grafs[cnt].setRepay(rs.getInt("REPAY"));
				grafs[cnt].setStatus(rs.getLong("STATUS"));
				grafs[cnt].setSumma(rs.getDouble("SUMMA"));
				grafs[cnt].setV_date(rs.getDate("V_DATE"));
				pss = c.prepareStatement("select sum(summa) from ld_graf where id = ? and branch = ? and oper_id= ?");
				pss.setLong(1, id);
				pss.setString(2, branch);
				pss.setLong(3, oper_id);
				rss = pss.executeQuery();
				BigDecimal sum = null;
				if(rss.next()) sum = new BigDecimal(rss.getDouble(1));
				grafs[cnt].setSum(sum);
				++cnt;
			}
    	} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(rss);
			Utils.close(pss);
		}
    	return grafs;
    }
    
    private static V_ld_account[] getVLdAccount(Long id,String branch,Connection c) throws Exception{
    	V_ld_account[] acc = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int cnt = 0;
    	try {
    		ps = c.prepareStatement("select count(*) from v_ld_account_forms f where id = ? and branch = ? and account is not null order by ord");
    		ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
    		ps = c.prepareStatement("select f.ord,f.name,f.bal,f.account,f.nstate,f.saldo1,f.saldo,f.acc_type_id from v_ld_account_forms f where id = ? and branch = ? and account is not null order by ord");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			acc = new V_ld_account[cnt];
			cnt = 0;
			while(rs.next()){
				acc[cnt] = new V_ld_account();
				acc[cnt].setAcc_type_id(rs.getString("ACC_TYPE_ID"));
				acc[cnt].setAccount(rs.getString("ACCOUNT"));
				acc[cnt].setBal(rs.getString("BAL"));
				acc[cnt].setName(rs.getString("NAME"));
				acc[cnt].setNstate(rs.getString("NSTATE"));
				acc[cnt].setOrd(rs.getLong("ORD"));
				acc[cnt].setSaldo(rs.getString("SALDO"));
				acc[cnt].setSaldo1(rs.getString("SALDO1"));
				++cnt;
			}
    	} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
    	return acc;
    }
    
    protected static String getClientName(String client_code, String branch, String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String name = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select name from client where id_client=? and branch = ?");
			ps.setString(1, client_code);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				name = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return name;
    }
    
    protected static CreditApp getNi_req(Long id,String branch,String alias){
    	CreditApp app = null;
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select ni_req.*,ni_req_n_code.code,state_ni_req.name from ni_req,ni_req_n_code,state_ni_req where ni_req.id=ni_req_n_code.id and ni_req.branch=ni_req_n_code.branch and state_ni_req.id(+)=ni_req.state and ni_req.id=? and ni_req.branch=? and ni_req.state=2");
			ps.setLong(1, id);
			ps.setString(2, branch);
			rs = ps.executeQuery();
			if(rs.next()){
				app = new CreditApp(
        				rs.getLong("id"),
        				rs.getString("branch"),
        				rs.getString("id_client"),
        				rs.getString("reqnum"),
        				rs.getString("reqtype"),
        				rs.getString("branch_id"),
        				rs.getString("kred_id"),
        				rs.getString("shifr_id"),
        				rs.getString("currency"),
        				rs.getDouble("amount")/100+"",
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
        				rs.getString("rdoc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
    	return app;
    }
    
    protected static void getStatus(HashMap<Long, String> status,String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select id,name from ss_ld_status");
			rs = ps.executeQuery();
			while(rs.next()){
				status.put(rs.getLong(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
    }
    
    protected static void getState(HashMap<Integer, String> state,String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select id,name from state_ldform");
			rs = ps.executeQuery();
			while(rs.next()){
				state.put(rs.getInt(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
    }
    
    public static List<GuarrGrid> getGuarrGrid(String guarr_id){
    	List<GuarrGrid> guarrGrid = new ArrayList<GuarrGrid>();
    	Connection c = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from create_guarr_grid where guarr_id=? order by order_id");
			ps.setString(1, guarr_id);
			rs = ps.executeQuery();
			while(rs.next()){
				guarrGrid.add(new GuarrGrid(
							rs.getLong(1),
							rs.getString("name_field"),
							rs.getString("type_field"),
							rs.getString("table_name_field"),
							rs.getLong("max_lenght_field"),
							rs.getString("guarr_id"),
							rs.getString("model_field"),
							rs.getString("sub_table"),
							rs.getString("table_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
    	return guarrGrid;
    }
    
    protected static void confirmCredit(CurrentCredit current,String alias,String action) {
    	Connection c = null;
    	CallableStatement clear_param = null;
    	CallableStatement set_param = null;
    	CallableStatement do_action = null;
    	CallableStatement init = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			clear_param = c.prepareCall("{ call param.clearparam() }");
			clear_param.execute();
			init = c.prepareCall("{ call info.init() }");
			init.execute();
			set_param = c.prepareCall("{ call Param.setParam(?,?) }");
			do_action = c.prepareCall("{ call kernel.doaction(4,1,"+action+") }");
			if(current.getLd_forms()!=null){
				Ld_forms forms = current.getLd_forms();
				Ld_char ld_char = current.getLd_char();
				if(forms.getEmp_id()!=null){
					set_param.setString(1, "EMP_ID");
					set_param.setInt(2, current.getLd_forms().getEmp_id());
					set_param.addBatch();
				}
				if(forms.getHead_of_bank()!=null){
					set_param.setString(1, "HEAD_OF_BANK");
					set_param.setString(2, forms.getHead_of_bank());
					set_param.addBatch();
				}
				if(ld_char.getBranch()!=null){
					System.out.println("branch = "+ld_char.getBranch());
					set_param.setString(1, "BRANCH");
					set_param.setString(2, ld_char.getBranch());
					set_param.addBatch();
				}
				if(forms.getIs_ld()!=null){
					set_param.setString(1, "IS_LD");
					set_param.setInt(2, forms.getIs_ld());
					set_param.addBatch();
				}
				if(forms.getEmp_count()!=null){
					set_param.setString(1, "EMP_COUNT");
					set_param.setInt(2,forms.getEmp_count());
					set_param.addBatch();
				}
				if(forms.getSub_name()!=null){
					set_param.setString(1, "SUB_NAME");
					set_param.setString(2, forms.getSub_name());
					set_param.addBatch();
				}
				if(forms.getSub_addr()!=null){
					set_param.setString(1, "SUB_ADDR");
					set_param.setString(2, forms.getSub_addr());
					set_param.addBatch();
				}
				if(forms.getPhone_dir()!=null){
					set_param.setString(1, "PHONE_DIR");
					set_param.setString(2, forms.getPhone_dir());
					set_param.addBatch();
				}
				if(forms.getPhone_chief()!=null){
					set_param.setString(1, "PHONE_CHIEF");
					set_param.setString(2, forms.getPhone_chief());
					set_param.addBatch();
				}
				if(forms.getStage_dir()!=null){
					set_param.setString(1, "STAGE_DIR");
					set_param.setDouble(2, forms.getStage_dir());
					set_param.addBatch();
				}
				if(ld_char.getId()!=null){
					System.out.println("ID = "+ld_char.getId());
					set_param.setString(1, "ID");
					set_param.setLong(2, ld_char.getId());
					set_param.addBatch();
				}
				if(forms.getStage_chief()!=null){
					set_param.setString(1, "STAGE_CHIEF");
					set_param.setDouble(2, forms.getStage_chief());
					set_param.addBatch();
				}
				if(forms.getIs_sec()!=null){
					set_param.setString(1, "IS_SEC");
					set_param.setInt(2, forms.getIs_sec());
					set_param.addBatch();
				}
				if(forms.getSec_amount()!=null){
					set_param.setString(1, "SEC_AMOUNT");
					set_param.setLong(2, forms.getSec_amount());
					set_param.addBatch();
				}
				if(forms.getType_id()!=null){
					set_param.setString(1, "TYPE_ID");
					set_param.setLong(2, forms.getType_id());
					set_param.addBatch();
				}
			}
			set_param.executeBatch();
			do_action.execute();
			c.commit();
    	} catch (Exception e){
    		Utils.rollback(c);
    		e.printStackTrace();
    		ISLogger.getLogger().error(CheckNull.getPstr(e));
    	} finally {
    		Utils.close(init);
			Utils.close(set_param);
			Utils.close(clear_param);
			ConnectionPool.close(c);
		}
    }
    
    protected static void saveCredit(CurrentCredit current, Res res, String alias){
    	Connection c = null;
    	CallableStatement cs = null;
        Statement st = null;
        Long form_id = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			st = c.createStatement();
			st.executeUpdate("alter session set NLS_NUMERIC_CHARACTERS = '"+format+not_format+"'");
			form_id = saveChar(current, c);
			cs = c.prepareCall("{ ? = call Param.getparam('ID') }");
			cs.registerOutParameter(1, java.sql.Types.NUMERIC);
			cs.execute();
			System.out.println("form_id = "+form_id);
			for (int i = 0; i < current.getLd_guarr().length; i++) {
				current.getLd_guarr()[i].setId(form_id);
				current.getLd_guarr()[i].setBranch(current.getLd_char().getBranch());
				saveGuarr(current.getLd_guarr()[i], c);
			}
			for (int i = 0; i < current.getLd_exp().length; i++) {
				current.getLd_exp()[i].setId(form_id);
				System.out.println("current.getLd_exp().length = "+current.getLd_exp().length);
				saveLd_exp(current.getLd_exp()[i],c);
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			Utils.rollback(c);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(st);
			Utils.close(cs);
			ConnectionPool.close(c);
		}
    }
    
    private static String generateParams(String proc,int num){
    	StringBuilder sb = new StringBuilder();
    	sb.append("{call "+proc+"(");
    	for (int i = 0; i < num; i++) {
			sb.append("?");
			if((i+1)==num){
				sb.append(")}");
			} else {
				sb.append(",");
			}
		}
    	return sb.toString();
    }
    
    private static Long saveChar(CurrentCredit current,Connection c) throws Exception{
    	CallableStatement cs = null;
    	Ld_char ld_char = current.getLd_char();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Long id = null;
    	try {
			if(ld_char.getId()==null){
				throw new Exception("Создание анкеты с нуля временно не доступно");
//				ps = c.prepareStatement("select seq_ldform.nextval from dual");
//	    		rs = ps.executeQuery();
//	    		if(rs.next()){
//	    			id = rs.getLong(1);
//	    		}
//	    		ps = c.prepareStatement("insert into ld_forms(branch,id,client,ld_type,type_id,state,d_date,d_confirm,head_of_bank,emp_id,is_ld) values (?,?,?,?,?,?,?,?,?,?,?)");
//	    		ps.setString(1, ld_char.getBranch());
//	    		ps.setLong(2, id);
//	    		ps.setString(3, .getC)
			} else {
				id = ld_char.getId();
			}
    		cs = c.prepareCall(generateParams("proc_ldform.savechar", 46));
			cs.setLong(1,id);
			cs.setString(2, ld_char.getEq_num());
			ld_char.getBranch();
			cs.setDate(3, Utils.parseUtilToSqlDate(ld_char.getEq_date()));
			cs.setString(4, ld_char.getCurrency());
			cs.setDouble(5, ld_char.getAmount()*100);
			cs.setString(6, ld_char.getP_num());
			cs.setDate(7, Utils.parseUtilToSqlDate(ld_char.getP_date()));
			cs.setString(8, ld_char.getCrc_num());
			cs.setDate(9, Utils.parseUtilToSqlDate(ld_char.getCrc_date()));
			cs.setString(10, ld_char.getT_author());
			cs.setDate(11, Utils.parseUtilToSqlDate(ld_char.getT_date()));
			cs.setInt(12, ld_char.getT_type());
			cs.setString(13, ld_char.getIs_inv());
			cs.setString(14, ld_char.getCurrency_inv());
			if(ld_char.getAmount_inv()==null){
				cs.setNull(15, java.sql.Types.NUMERIC);
			} else {
				cs.setDouble(15, ld_char.getAmount_inv()*100);
			}
			cs.setString(16, ld_char.getLd_num());
			cs.setDate(17, Utils.parseUtilToSqlDate(ld_char.getLd_date()));
			cs.setInt(18, ld_char.getCl_num());
			cs.setString(19, ld_char.getShifr_id());
			cs.setString(20, ld_char.getProd_name());
			cs.setString(21, ld_char.getSred_id());
			cs.setString(22, ld_char.getTarget());
			cs.setDouble(23, ld_char.getLd_amount()*100);
			cs.setDate(24, Utils.parseUtilToSqlDate(ld_char.getDate_begin()));
			cs.setDate(25, Utils.parseUtilToSqlDate(ld_char.getDate_end()));
			cs.setLong(26, ld_char.getCalc_id());
			cs.setDate(27, Utils.parseUtilToSqlDate(ld_char.getDate_fee()));
			cs.setLong(28, ld_char.getTerm_type());
			cs.setLong(29, ld_char.getType_id());
			cs.setString(30, ld_char.getKred_id());
			cs.setString(31, ld_char.getKred_id_cb());
			cs.setString(32, ld_char.getKlass_id());
			cs.setInt(33, ld_char.getIs_tax());
			if(ld_char.getTax_rate()==null){
				cs.setNull(34, java.sql.Types.NUMERIC);
			} else {
				cs.setDouble(34, ld_char.getTax_rate());
			}
			cs.setString(35, ld_char.getAl_num());
			cs.setString(36, ld_char.getKlassp_id());
			cs.setLong(37, ld_char.getStatus());
			cs.setInt(38, ld_char.getAcceptance());
			cs.setLong(39, ld_char.getNiki_id());
			cs.setString(40, ld_char.getUse_branch());
			cs.setString(41, null);
			cs.setInt(42, ld_char.getCres());
			cs.setDate(43,Utils.parseUtilToSqlDate(ld_char.getDate_close()));
			cs.setString(44, ld_char.getKod_fin());
			if(ld_char.getFaza_abr()==null){
				cs.setNull(45, java.sql.Types.NUMERIC);
			} else {
				cs.setInt(45, ld_char.getFaza_abr());
			}
			cs.setString(46, null);
			cs.execute();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(cs);
		}
		return id;
    }
    
    private static void saveGuarr(Ld_guarr ld_guarr,Connection c) throws Exception{
    	CallableStatement ps_setparam = null;
    	CallableStatement save_guar = null;
    	CallableStatement ps_getparam = null;
    	try{
    		ps_getparam = c.prepareCall("{ ? = call Param.getparam(?) }");
    		ps_getparam.registerOutParameter(1, java.sql.Types.NUMERIC);
    		save_guar = c.prepareCall("{ call proc_ldform.SaveGR(?) }");
    		System.out.println(ld_guarr.getId()==null?"":ld_guarr.getId().toString());
    		save_guar.setString(1, ld_guarr.getId()==null?"":ld_guarr.getId().toString());
    		ps_setparam = c.prepareCall("{ call param.ClearParam() }");
    		ps_setparam.execute();
    		ps_setparam = c.prepareCall("{ call param.setParam(?, ?) }");
    		if(ld_guarr.getPk()!=null) { ps_setparam.setString(1, "PK"); ps_setparam.setString(2, ld_guarr.getPk().toString()); ps_setparam.addBatch();}                                                           //Long
			if(ld_guarr.getBranch()!=null) { ps_setparam.setString(1, "BRANCH"); ps_setparam.setString(2, ld_guarr.getBranch()); ps_setparam.addBatch();}                                               //String
			if(ld_guarr.getId()!=null) { 
				ps_setparam.setString(1, "ID");
				ps_setparam.setString(2, ld_guarr.getId().toString());
				ps_setparam.addBatch();
			}                                                           //Long
			if(ld_guarr.getGuar_id()!=null) { ps_setparam.setString(1, "GUAR_ID"); ps_setparam.setString(2, ld_guarr.getGuar_id()); ps_setparam.addBatch();}                                            //String
			if(ld_guarr.getName()!=null) { ps_setparam.setString(1, "NAME"); ps_setparam.setString(2, ld_guarr.getName()); ps_setparam.addBatch();}                                                     //String
			if(ld_guarr.getAddress()!=null) { ps_setparam.setString(1, "ADDRESS"); ps_setparam.setString(2, ld_guarr.getAddress()); ps_setparam.addBatch();}                                            //String
			if(ld_guarr.getDoc_num()!=null) { ps_setparam.setString(1, "DOC_NUM"); ps_setparam.setString(2, ld_guarr.getDoc_num()); ps_setparam.addBatch();}                                            //String
			if(ld_guarr.getDoc_date()!=null) { ps_setparam.setString(1, "DOC_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getDoc_date())); ps_setparam.addBatch();}                                        //Date
			if(ld_guarr.getCurrency()!=null) { ps_setparam.setString(1, "CURRENCY"); ps_setparam.setString(2, ld_guarr.getCurrency()); ps_setparam.addBatch();}                                         //String
			if(ld_guarr.getAmount()!=null) {
				String amount = (BigDecimal.valueOf(ld_guarr.getAmount()).divide(BigDecimal.valueOf(100l)).toString());
				amount = amount.replace(not_format, format);
				ps_setparam.setString(1, "AMOUNT");
				ps_setparam.setString(2, amount);
				ps_setparam.addBatch();
			}                                               //Long
			if(ld_guarr.getKlass_o()!=null) { ps_setparam.setString(1, "KLASS_O"); ps_setparam.setString(2, ld_guarr.getKlass_o()); ps_setparam.addBatch();}                                            //String
			if(ld_guarr.getCanbuy()!=null) { ps_setparam.setString(1, "CANBUY"); ps_setparam.setString(2, ld_guarr.getCanbuy().toString()); ps_setparam.addBatch();}                                               //Long
			if(ld_guarr.getHasdepo()!=null) { ps_setparam.setString(1, "HASDEPO"); ps_setparam.setString(2, ld_guarr.getHasdepo().toString()); ps_setparam.addBatch();}                                            //Integer
			if(ld_guarr.getBank_name()!=null) { ps_setparam.setString(1, "BANK_NAME"); ps_setparam.setString(2, ld_guarr.getBank_name()); ps_setparam.addBatch();}                                      //String
			if(ld_guarr.getHasdoc()!=null) { ps_setparam.setString(1, "HASDOC"); ps_setparam.setString(2, ld_guarr.getHasdoc().toString()); ps_setparam.addBatch();}                                               //Integer
			if(ld_guarr.getCode_subject()!=null) { ps_setparam.setString(1, "CODE_SUBJECT"); ps_setparam.setString(2, ld_guarr.getCode_subject()); ps_setparam.addBatch();}                             //String
			if(ld_guarr.getNotarial_doc_numb()!=null) { ps_setparam.setString(1, "NOTARIAL_DOC_NUMB"); ps_setparam.setString(2, ld_guarr.getNotarial_doc_numb()); ps_setparam.addBatch();}              //String
			if(ld_guarr.getStart_date()!=null) { ps_setparam.setString(1, "START_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getStart_date())); ps_setparam.addBatch();}                                   //Date
			if(ld_guarr.getEnd_date()!=null) { ps_setparam.setString(1, "END_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getEnd_date())); ps_setparam.addBatch();}                                         //Date
			if(ld_guarr.getInn()!=null) { ps_setparam.setString(1, "INN"); ps_setparam.setString(2, ld_guarr.getInn()); ps_setparam.addBatch();}                                                        //String
			if(ld_guarr.getMfo()!=null) { ps_setparam.setString(1, "MFO"); ps_setparam.setString(2, ld_guarr.getMfo()); ps_setparam.addBatch();}                                                        //String
			if(ld_guarr.getName2()!=null) { ps_setparam.setString(1, "NAME2"); ps_setparam.setString(2, ld_guarr.getName2()); ps_setparam.addBatch();}                                                  //String
			if(ld_guarr.getAccount()!=null) { ps_setparam.setString(1, "ACCOUNT"); ps_setparam.setString(2, ld_guarr.getAccount()); ps_setparam.addBatch();}                                            //String
			if(ld_guarr.getStock_nominal_value()!=null) { ps_setparam.setString(1, "STOCK_NOMINAL_VALUE"); ps_setparam.setString(2, ld_guarr.getStock_nominal_value().toString()); ps_setparam.addBatch();}        //Long
			if(ld_guarr.getStock_count()!=null) { ps_setparam.setString(1, "STOCK_COUNT"); ps_setparam.setString(2, ld_guarr.getStock_count().toString()); ps_setparam.addBatch();}                                //Long
			if(ld_guarr.getStock_diskont()!=null) { ps_setparam.setString(1, "STOCK_DISKONT"); ps_setparam.setString(2, ld_guarr.getStock_diskont().toString()); ps_setparam.addBatch();}                          //Long
			if(ld_guarr.getStock_name()!=null) { ps_setparam.setString(1, "STOCK_NAME"); ps_setparam.setString(2, ld_guarr.getStock_name()); ps_setparam.addBatch();}                                   //String
			if(ld_guarr.getSign_depo()!=null) { ps_setparam.setString(1, "SIGN_DEPO"); ps_setparam.setString(2, ld_guarr.getSign_depo().toString()); ps_setparam.addBatch();}                                      //Integer
			if(ld_guarr.getInsc_name()!=null) { ps_setparam.setString(1, "INSC_NAME"); ps_setparam.setString(2, ld_guarr.getInsc_name()); ps_setparam.addBatch();}                                      //String
			if(ld_guarr.getInsc_inn()!=null) { ps_setparam.setString(1, "INSC_INN"); ps_setparam.setString(2, ld_guarr.getInsc_inn()); ps_setparam.addBatch();}                                         //String
			if(ld_guarr.getInsc_num()!=null) { ps_setparam.setString(1, "INSC_NUM"); ps_setparam.setString(2, ld_guarr.getInsc_num()); ps_setparam.addBatch();}                                         //String
			if(ld_guarr.getInsc_date()!=null) { ps_setparam.setString(1, "INSC_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getInsc_date())); ps_setparam.addBatch();}                                      //Date
			if(ld_guarr.getInsc_date_cl()!=null) { ps_setparam.setString(1, "INSC_DATE_CL"); ps_setparam.setString(2, df.format(ld_guarr.getInsc_date_cl())); ps_setparam.addBatch();}                             //Date
			if(ld_guarr.getNiki_res1()!=null) { ps_setparam.setString(1, "NIKI_RES1"); ps_setparam.setString(2, ld_guarr.getNiki_res1()); ps_setparam.addBatch();}                                      //String
			if(ld_guarr.getNiki_res2()!=null) { ps_setparam.setString(1, "NIKI_RES2"); ps_setparam.setString(2, ld_guarr.getNiki_res2()); ps_setparam.addBatch();}                                      //String
			if(ld_guarr.getNiki_gr_branch()!=null) { ps_setparam.setString(1, "NIKI_GR_BRANCH"); ps_setparam.setString(2, ld_guarr.getNiki_gr_branch()); ps_setparam.addBatch();}                       //String
			if(ld_guarr.getNiki_gr_code_type()!=null) { ps_setparam.setString(1, "NIKI_GR_CODE_TYPE"); ps_setparam.setString(2, ld_guarr.getNiki_gr_code_type()); ps_setparam.addBatch();}              //String
			if(ld_guarr.getNiki_inn()!=null) { ps_setparam.setString(1, "NIKI_INN"); ps_setparam.setString(2, ld_guarr.getNiki_inn()); ps_setparam.addBatch();}                                         //String
			if(ld_guarr.getNiki_soogun()!=null) { ps_setparam.setString(1, "NIKI_SOOGUN"); ps_setparam.setString(2, ld_guarr.getNiki_soogun()); ps_setparam.addBatch();}                                //String
			if(ld_guarr.getAcomp_name()!=null) { ps_setparam.setString(1, "ACOMP_NAME"); ps_setparam.setString(2, ld_guarr.getAcomp_name()); ps_setparam.addBatch();}                                   //String
			if(ld_guarr.getAcomp_date()!=null) { ps_setparam.setString(1, "ACOMP_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getAcomp_date())); ps_setparam.addBatch();}                                   //Date
			if(ld_guarr.getAcomp_curr()!=null) { ps_setparam.setString(1, "ACOMP_CURR"); ps_setparam.setString(2, ld_guarr.getAcomp_curr()); ps_setparam.addBatch();}                                   //String
			if(ld_guarr.getAcomp_summa()!=null) { 
				String amount = (BigDecimal.valueOf(ld_guarr.getAcomp_summa()).divide(BigDecimal.valueOf(100l))).toString();
				amount = amount.replace(not_format, format);
				ps_setparam.setString(1, "ACOMP_SUMMA"); 
				ps_setparam.setString(2, amount); 
				ps_setparam.addBatch();
			}                                
			if(ld_guarr.getNiki_owner()!=null) { ps_setparam.setString(1, "NIKI_OWNER"); ps_setparam.setString(2, ld_guarr.getNiki_owner()); ps_setparam.addBatch();}                                   //String
			if(ld_guarr.getPolis_num()!=null) { ps_setparam.setString(1, "POLIS_NUM"); ps_setparam.setString(2, ld_guarr.getPolis_num()); ps_setparam.addBatch();}                                      //String
			if(ld_guarr.getPolis_date()!=null) { ps_setparam.setString(1, "POLIS_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getPolis_date())); ps_setparam.addBatch();}                                   //Date
			if(ld_guarr.getRegion_id()!=null) { ps_setparam.setString(1, "REGION_ID"); ps_setparam.setString(2, ld_guarr.getRegion_id()); ps_setparam.addBatch();}                              //String
			if(ld_guarr.getDistr_id()!=null) { ps_setparam.setString(1, "DISTR_ID"); ps_setparam.setString(2, ld_guarr.getDistr_id().length()>3?ld_guarr.getDistr_id().substring(ld_guarr.getDistr_id().length()-3):ld_guarr.getDistr_id()); ps_setparam.addBatch();}                                         //String
			if(ld_guarr.getMassiv()!=null) { ps_setparam.setString(1, "MASSIV"); ps_setparam.setString(2, ld_guarr.getMassiv()); ps_setparam.addBatch();}                                               //String
			if(ld_guarr.getStreet()!=null) { ps_setparam.setString(1, "STREET"); ps_setparam.setString(2, ld_guarr.getStreet()); ps_setparam.addBatch();}                                               //String
			if(ld_guarr.getHome()!=null) { ps_setparam.setString(1, "HOME"); ps_setparam.setString(2, ld_guarr.getHome()); ps_setparam.addBatch();}                                                     //String
			if(ld_guarr.getHome_num()!=null) { ps_setparam.setString(1, "HOME_NUM"); ps_setparam.setString(2, ld_guarr.getHome_num()); ps_setparam.addBatch();}                                         //String
			if(ld_guarr.getEconomical_zone()!=null) { ps_setparam.setString(1, "ECONOMICAL_ZONE"); ps_setparam.setString(2, ld_guarr.getEconomical_zone().toString()); ps_setparam.addBatch();}                    //Long
			if(ld_guarr.getCadastr_org_region()!=null) { ps_setparam.setString(1, "CADASTR_ORG_REGION"); ps_setparam.setString(2, ld_guarr.getCadastr_org_region()); ps_setparam.addBatch();}           //String
			if(ld_guarr.getCadastr_org_distr()!=null) { ps_setparam.setString(1, "CADASTR_ORG_DISTR"); ps_setparam.setString(2, ld_guarr.getCadastr_org_distr()); ps_setparam.addBatch();}              //String
			if(ld_guarr.getNotarial_office_num()!=null) { ps_setparam.setString(1, "NOTARIAL_OFFICE_NUM"); ps_setparam.setString(2, ld_guarr.getNotarial_office_num()); ps_setparam.addBatch();}        //String
			if(ld_guarr.getSer_eval_company()!=null) { ps_setparam.setString(1, "SER_EVAL_COMPANY"); ps_setparam.setString(2, ld_guarr.getSer_eval_company()); ps_setparam.addBatch();}                 //String
			if(ld_guarr.getLis_num()!=null) { ps_setparam.setString(1, "LIS_NUM"); ps_setparam.setString(2, ld_guarr.getLis_num()); ps_setparam.addBatch();}                                            //String
			if(ld_guarr.getLis_date()!=null) { ps_setparam.setString(1, "LIS_DATE"); ps_setparam.setString(2, df.format(ld_guarr.getLis_date())); ps_setparam.addBatch();}                                         //Date
			if(ld_guarr.getEval_report_num()!=null) { ps_setparam.setString(1, "EVAL_REPORT_NUM"); ps_setparam.setString(2, ld_guarr.getEval_report_num()); ps_setparam.addBatch();}                    //String
			if(ld_guarr.getId_client()!=null) { ps_setparam.setString(1, "ID_CLIENT"); ps_setparam.setString(2, ld_guarr.getId_client()); ps_setparam.addBatch();}                                      //String
			if(ld_guarr.getDate_operation()!=null) { ps_setparam.setString(1, "DATE_OPERATION"); ps_setparam.setString(2, df.format(ld_guarr.getDate_operation())); ps_setparam.addBatch();}                       //Date
			if(ld_guarr.getDepositary()!=null) { ps_setparam.setString(1, "DEPOSITARY"); ps_setparam.setString(2, ld_guarr.getDepositary()); ps_setparam.addBatch();}                                   //String
			if(ld_guarr.getDepositary_account()!=null) { ps_setparam.setString(1, "DEPOSITARY_ACCOUNT"); ps_setparam.setString(2, ld_guarr.getDepositary_account()); ps_setparam.addBatch();}           //String
			if(ld_guarr.getSowing_area()!=null) { ps_setparam.setString(1, "SOWING_AREA"); ps_setparam.setString(2, ld_guarr.getSowing_area().toString()); ps_setparam.addBatch();}                                //Long
			if(ld_guarr.getMassa()!=null) {
				String massa = ld_guarr.getMassa().toString();
				massa = massa.replace(not_format, format);
				ps_setparam.setString(1, "MASSA");
				ps_setparam.setString(2, massa);
				ps_setparam.addBatch();
			}                                                  
			if(ld_guarr.getSertificate_num()!=null) { ps_setparam.setString(1, "SERTIFICATE_NUM"); ps_setparam.setString(2, ld_guarr.getSertificate_num()); ps_setparam.addBatch();}                    //String
			if(ld_guarr.getSertificate_ser()!=null) { ps_setparam.setString(1, "SERTIFICATE_SER"); ps_setparam.setString(2, ld_guarr.getSertificate_ser()); ps_setparam.addBatch();}                    //String
			if(ld_guarr.getSertificate_rate()!=null) { ps_setparam.setString(1, "SERTIFICATE_RATE"); ps_setparam.setString(2, ld_guarr.getSertificate_rate().toString()); ps_setparam.addBatch();}                 //Long
			if(ld_guarr.getInn_reestr()!=null) { ps_setparam.setString(1, "INN_REESTR"); ps_setparam.setString(2, ld_guarr.getInn_reestr()); ps_setparam.addBatch();}                                   //String
			if(ld_guarr.getCadastr_place_region()!=null) { ps_setparam.setString(1, "CADASTR_PLACE_REGION"); ps_setparam.setString(2, ld_guarr.getCadastr_place_region()); ps_setparam.addBatch();}     //String
			if(ld_guarr.getCadastr_place_town()!=null) { ps_setparam.setString(1, "CADASTR_PLACE_TOWN"); ps_setparam.setString(2, ld_guarr.getCadastr_place_town().toString()); ps_setparam.addBatch();}           //Integer
			if(ld_guarr.getCadastr_place_distr()!=null) { ps_setparam.setString(1, "CADASTR_PLACE_DISTR"); ps_setparam.setString(2, ld_guarr.getCadastr_place_distr()); ps_setparam.addBatch();}        //String
			if(ld_guarr.getCadastr_place_adres()!=null) { ps_setparam.setString(1, "CADASTR_PLACE_ADRES"); ps_setparam.setString(2, ld_guarr.getCadastr_place_adres()); ps_setparam.addBatch();}        //String
			if(ld_guarr.getCadastr_place_x()!=null) { ps_setparam.setString(1, "CADASTR_PLACE_X"); ps_setparam.setString(2, ld_guarr.getCadastr_place_x()); ps_setparam.addBatch();}                    //String
			if(ld_guarr.getCadastr_place_y()!=null) { ps_setparam.setString(1, "CADASTR_PLACE_Y"); ps_setparam.setString(2, ld_guarr.getCadastr_place_y()); ps_setparam.addBatch();}                    //String
			if(ld_guarr.getBuilding_type()!=null) { ps_setparam.setString(1, "BUILDING_TYPE"); ps_setparam.setString(2, ld_guarr.getBuilding_type().toString()); ps_setparam.addBatch();}                          //Integer
			if(ld_guarr.getBuilding_year()!=null) { ps_setparam.setString(1, "BUILDING_YEAR"); ps_setparam.setString(2, ld_guarr.getBuilding_year().toString()); ps_setparam.addBatch();}                          //Integer
			if(ld_guarr.getBuilding_square()!=null) { ps_setparam.setString(1, "BUILDING_SQUARE"); ps_setparam.setString(2, ld_guarr.getBuilding_square().toString()); ps_setparam.addBatch();}                    //Long
			if(ld_guarr.getBuild_constr_type()!=null) { ps_setparam.setString(1, "BUILD_CONSTR_TYPE"); ps_setparam.setString(2, ld_guarr.getBuild_constr_type().toString()); ps_setparam.addBatch();}              //Integer
			if(ld_guarr.getBuilding_kind()!=null) { ps_setparam.setString(1, "BUILDING_KIND"); ps_setparam.setString(2, ld_guarr.getBuilding_kind().toString()); ps_setparam.addBatch();}                          //Integer
			if(ld_guarr.getBuilding_num()!=null) { ps_setparam.setString(1, "BUILDING_NUM"); ps_setparam.setString(2, ld_guarr.getBuilding_num().toString()); ps_setparam.addBatch();}                             //Integer
			ps_setparam.executeBatch();
			save_guar.execute();
			ps_getparam.setString(2, "PK");
			ps_getparam.execute();
			Long pk_ld_guar = ps_getparam.getLong(1);
			if (ld_guarr.getLd_guar_blocks()!=null){
				ld_guarr.getLd_guar_blocks().setPk_ld_guar(pk_ld_guar);
				ld_guarr.getLd_guar_blocks().setId(ld_guarr.getId());
				saveGuarBlocks(ld_guarr.getLd_guar_blocks(), c);
			} if (ld_guarr.getLd_guar_cadastre()!=null){
				ld_guarr.getLd_guar_cadastre().setPk_ld_guar(pk_ld_guar);
				ld_guarr.getLd_guar_cadastre().setId(ld_guarr.getId());
				saveGuarCadastre(ld_guarr.getLd_guar_cadastre(), c);
			} if (ld_guarr.getLd_guar_car()!=null){
				ld_guarr.getLd_guar_car().setPk_ld_guar(pk_ld_guar);
				ld_guarr.getLd_guar_car().setId(ld_guarr.getId());
				ld_guarr.getLd_guar_car().setBranch(ld_guarr.getBranch());
				saveGuarCar(ld_guarr.getLd_guar_car(), c);
			} if (ld_guarr.getLd_guar_equipment()!=null){
				ld_guarr.getLd_guar_equipment().setPk_ld_guar(pk_ld_guar);
				ld_guarr.getLd_guar_equipment().setId(ld_guarr.getId());
				saveGuarEquipment(ld_guarr.getLd_guar_equipment(), c);
			}
    	} finally {
    		Utils.close(ps_setparam);
    		Utils.close(save_guar);
    		Utils.close(ps_getparam);
    	}
    }
    
    private static void saveGuarBlocks(Ld_guar_blocks item,Connection c) throws Exception{
    	CallableStatement ps_setparam = null;
    	CallableStatement cs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		ps_setparam = c.prepareCall("{ call param.ClearParam() }");
    		ps_setparam.execute();
    		ps_setparam = c.prepareCall("{ call param.setParam(?, ?) }");
    		cs = c.prepareCall("{ call proc_ldform.SaveGUAR_BLOCKS(?) }");
    		cs.setNull(1, java.sql.Types.NUMERIC);
    		if(item.getPk_ld_guar()!=null){
    			ps = c.prepareStatement("select b.pk from ld_guar_blocks b where b.pk_ld_guar = ? and b.branch = ?");
    			ps.setLong(1, item.getPk_ld_guar());
    			ps.setString(2, item.getBranch());
    			rs = ps.executeQuery();
    			if(rs.next()){
    				item.setPk(rs.getLong("pk"));
    			}
    		}
			if(item.getBranch()!=null) { ps_setparam.setString(1, "P_BRANCH"); ps_setparam.setString(2, item.getBranch()); ps_setparam.addBatch();}                                                             //String
			ps_setparam.setString(1, "P_ID"); 
			ps_setparam.setString(2, item.getId().toString());
			ps_setparam.addBatch();
			if(item.getPk()!=null) { 
				ps_setparam.setString(1, "P_PK"); 
				ps_setparam.setString(2, item.getPk().toString()); 
				ps_setparam.addBatch();
				cs.setLong(1, item.getPk());
			}   
			ps_setparam.setString(1, "P_PK_LD_GUAR");
			ps_setparam.setString(2, item.getPk_ld_guar().toString());
			ps_setparam.addBatch();
			if(item.getId_nn()!=null) { ps_setparam.setString(1, "P_ID_NN"); ps_setparam.setString(2, item.getId_nn().toString()); ps_setparam.addBatch();}                                                                //Long
			if(item.getBlock_name()!=null) { ps_setparam.setString(1, "P_BLOCK_NAME"); ps_setparam.setString(2, item.getBlock_name()); ps_setparam.addBatch();}                                                 //String
			if(item.getDescription()!=null) { ps_setparam.setString(1, "P_DESCRIPTION"); ps_setparam.setString(2, item.getDescription()); ps_setparam.addBatch();}                                              //String
			if(item.getCost()!=null) { ps_setparam.setString(1, "P_COST"); ps_setparam.setString(2, Long.toString(item.getCost())); ps_setparam.addBatch();}                                                                   //Long
			if(item.getSquare()!=null) { 
				String square = item.getSquare().toString();
				square = square.replace(not_format, format);
				ps_setparam.setString(1, "P_SQUARE");
				ps_setparam.setString(2, square);
				ps_setparam.addBatch();
			}   
			ps_setparam.executeBatch();
			cs.execute();
   		} finally {
   			Utils.close(cs);
   			Utils.close(ps_setparam);
   			Utils.close(rs);
   			Utils.close(ps);
    	}
    }
    
    private static void saveGuarCadastre(Ld_guar_cadastre item,Connection c) throws Exception{
    	CallableStatement ps_setparam = null;
    	CallableStatement cs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		ps_setparam = c.prepareCall("{ call param.ClearParam() }");
    		ps_setparam.execute();
    		ps_setparam = c.prepareCall("{ call param.setParam(?, ?) }");
    		cs = c.prepareCall("{ call proc_ldform.SaveGUAR_CADASTRE(?) }");
    		cs.setNull(1, java.sql.Types.NUMERIC);
    		if(item.getPk_ld_guar()!=null){
    			ps = c.prepareStatement("select b.pk from ld_guar_cadastre b where b.pk_ld_guar = ? and b.branch = ? and b.cadastre_type = ?");
    			ps.setLong(1, item.getPk_ld_guar());
    			ps.setString(2, item.getBranch());
    			ps.setInt(3, item.getCadastre_type());
    			rs = ps.executeQuery();
    			if(rs.next()){
    				item.setPk(rs.getLong("pk"));
    			}
    		}
			if(item.getBranch()!=null) { ps_setparam.setString(1, "P_BRANCH"); ps_setparam.setString(2, item.getBranch()); ps_setparam.addBatch();}                                                             //String
			ps_setparam.setString(1, "P_ID"); 
			ps_setparam.setString(2, item.getId().toString());
			ps_setparam.addBatch();
			if(item.getPk()!=null) { 
				ps_setparam.setString(1, "P_PK"); 
				ps_setparam.setString(2, item.getPk().toString()); 
				ps_setparam.addBatch();
				cs.setLong(1, item.getPk());
			}   
			ps_setparam.setString(1, "P_PK_LD_GUAR");
			ps_setparam.setString(2, item.getPk_ld_guar().toString());
			ps_setparam.addBatch();
			if(item.getCadastre_num()!=null) { ps_setparam.setString(1, "P_CADASTRE_NUM"); ps_setparam.setString(2, item.getCadastre_num().toString()); ps_setparam.addBatch();}                                                       //Long
			if(item.getCadastre_type()!=null) { ps_setparam.setString(1, "P_CADASTRE_TYPE"); ps_setparam.setString(2, item.getCadastre_type().toString()); ps_setparam.addBatch();}                                                    //Long
			if(item.getCertificate_date()!=null) { ps_setparam.setString(1, "P_CERTIFICATE_DATE"); ps_setparam.setString(2, df.format(item.getCertificate_date())); ps_setparam.addBatch();}                                                    //Long
			if(item.getCertificate_num()!=null) { ps_setparam.setString(1, "P_CERTIFICATE_NUM"); ps_setparam.setString(2, item.getCertificate_num().toString()); ps_setparam.addBatch();}                                                    //Integer
			if(item.getOvnership()!=null) { ps_setparam.setString(1, "P_OVNERSHIP"); ps_setparam.setString(2, item.getOvnership().toString()); ps_setparam.addBatch();}                                                          //Long
			if(item.getReyestr_num()!=null) { ps_setparam.setString(1, "P_REYESTR_NUM"); ps_setparam.setString(2, item.getReyestr_num()); ps_setparam.addBatch();}                                                 //String
			if(item.getSquare()!=null) { 
				String square = item.getSquare().toString();
				square = square.replace(not_format, format);
				ps_setparam.setString(1, "P_SQUARE");
				ps_setparam.setString(2, square);
				ps_setparam.addBatch();
			}   
			ps_setparam.executeBatch();
			cs.execute();
   		} finally {
   			Utils.close(cs);
   			Utils.close(ps_setparam);
   			Utils.close(rs);
   			Utils.close(ps);
    	}
    }
    
    private static void saveGuarCar(Ld_guar_car item,Connection c) throws Exception{
    	CallableStatement ps_setparam = null;
    	CallableStatement cs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		ps_setparam = c.prepareCall("{ call param.ClearParam() }");
    		ps_setparam.execute();
    		ps_setparam = c.prepareCall("{ call param.setParam(?, ?) }");
    		cs = c.prepareCall("{ call proc_ldform.SaveGUAR_CAR(?) }");
    		cs.setNull(1, java.sql.Types.NUMERIC);
    		if(item.getPk_ld_guar()!=null){
    			ps = c.prepareStatement("select b.pk from ld_guar_car b where b.pk_ld_guar = ? and b.branch = ?");
    			System.out.println("pk_ld_guarr = "+item.getPk_ld_guar());
    			ps.setLong(1, item.getPk_ld_guar());
    			System.out.println("branch = "+item.getBranch());
    			ps.setString(2, item.getBranch());
    			rs = ps.executeQuery();
    			if(rs.next()){
    				item.setPk(rs.getLong("pk"));
    				System.out.println("PK = "+item.getPk());
    			}
    		}
			if(item.getBranch()!=null) { ps_setparam.setString(1, "P_BRANCH"); ps_setparam.setString(2, item.getBranch()); ps_setparam.addBatch();}                                                             //String
			ps_setparam.setString(1, "P_ID"); 
			ps_setparam.setString(2, item.getId().toString());
			ps_setparam.addBatch();
			if(item.getPk()!=null) { 
				ps_setparam.setString(1, "P_PK"); 
				ps_setparam.setString(2, item.getPk().toString()); 
				ps_setparam.addBatch();
				cs.setLong(1, item.getPk());
			}   
			ps_setparam.setString(1, "P_PK_LD_GUAR");
			ps_setparam.setString(2, item.getPk_ld_guar().toString());
			ps_setparam.addBatch();
			if(item.getCar_type()!=null) { ps_setparam.setString(1, "P_CAR_TYPE"); ps_setparam.setString(2, item.getCar_type().toString()); ps_setparam.addBatch();}                                                       //Long
			if(item.getCar_marka()!=null) { ps_setparam.setString(1, "P_CAR_MARKA"); ps_setparam.setString(2, item.getCar_marka().toString()); ps_setparam.addBatch();}                                                    //Long
			if(item.getCar_model()!=null) { ps_setparam.setString(1, "P_CAR_MODEL"); ps_setparam.setString(2, item.getCar_model().toString()); ps_setparam.addBatch();}                                                    //Long
			if(item.getDate_made()!=null) { ps_setparam.setString(1, "P_DATE_MADE"); ps_setparam.setString(2, item.getDate_made().toString()); ps_setparam.addBatch();}                                                    //Integer
			if(item.getMileage()!=null) { ps_setparam.setString(1, "P_MILEAGE"); ps_setparam.setString(2, item.getMileage().toString()); ps_setparam.addBatch();}                                                          //Long
			if(item.getEngine_num()!=null) { ps_setparam.setString(1, "P_ENGINE_NUM"); ps_setparam.setString(2, item.getEngine_num()); ps_setparam.addBatch();}                                                 //String
			if(item.getBody_num()!=null) { ps_setparam.setString(1, "P_BODY_NUM"); ps_setparam.setString(2, item.getBody_num()); ps_setparam.addBatch();}                                                       //String
			if(item.getColor()!=null) { ps_setparam.setString(1, "P_COLOR"); ps_setparam.setString(2, item.getColor().toString()); ps_setparam.addBatch();}                                                                //Integer
			if(item.getState_number()!=null) { ps_setparam.setString(1, "P_STATE_NUMBER"); ps_setparam.setString(2, item.getState_number()); ps_setparam.addBatch();}                                           //String
			if(item.getDoc_ser_num()!=null) { ps_setparam.setString(1, "P_DOC_SER_NUM"); ps_setparam.setString(2, item.getDoc_ser_num()); ps_setparam.addBatch();}                                              //String
			if(item.getDoc_date()!=null) { ps_setparam.setString(1, "P_DOC_DATE"); ps_setparam.setString(2, df.format(item.getDoc_date())); ps_setparam.addBatch();}                                                       //Date
			if(item.getCode_country()!=null) { ps_setparam.setString(1, "P_CODE_COUNTRY"); ps_setparam.setString(2, item.getCode_country()); ps_setparam.addBatch();}                                           //String
			if(item.getPosition()!=null) { ps_setparam.setString(1, "P_POSITION"); ps_setparam.setString(2, item.getPosition().toString()); ps_setparam.addBatch();}                                                       //Integer
			if(item.getChassis_number()!=null) { ps_setparam.setString(1, "P_CHASSIS_NUMBER"); ps_setparam.setString(2, item.getChassis_number()); ps_setparam.addBatch();}     
			ps_setparam.executeBatch();
			cs.execute();
   		} finally {
   			Utils.close(cs);
   			Utils.close(ps_setparam);
   			Utils.close(rs);
   			Utils.close(ps);
    	}
    }
    
    private static void saveGuarEquipment(Ld_guar_equipment item,Connection c) throws Exception{
    	CallableStatement ps_setparam = null;
    	CallableStatement cs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
    		ps_setparam = c.prepareCall("{ call param.ClearParam() }");
    		ps_setparam.execute();
    		ps_setparam = c.prepareCall("{ call param.setParam(?, ?) }");
    		cs = c.prepareCall("{ call proc_ldform.SaveGUAR_EQUIPMENT(?) }");
    		cs.setNull(1, java.sql.Types.NUMERIC);
    		if(item.getPk_ld_guar()!=null){
    			ps = c.prepareStatement("select b.pk from ld_guar_equipment b where b.pk_ld_guar = ? and b.branch = ?");
    			ps.setLong(1, item.getPk_ld_guar());
    			ps.setString(2, item.getBranch());
    			rs = ps.executeQuery();
    			if(rs.next()){
    				item.setPk(rs.getLong("pk"));
    			}
    		}
			if(item.getBranch()!=null) { ps_setparam.setString(1, "P_BRANCH"); ps_setparam.setString(2, item.getBranch()); ps_setparam.addBatch();}                                                             //String
			ps_setparam.setString(1, "P_ID"); 
			ps_setparam.setString(2, item.getId().toString());
			ps_setparam.addBatch();
			if(item.getPk()!=null) { 
				ps_setparam.setString(1, "P_PK"); 
				ps_setparam.setString(2, item.getPk().toString()); 
				ps_setparam.addBatch();
				cs.setLong(1, item.getPk());
			}   
			ps_setparam.setString(1, "P_PK_LD_GUAR");
			ps_setparam.setString(2, item.getPk_ld_guar().toString());
			ps_setparam.addBatch();
			if(item.getId_nn()!=null) { ps_setparam.setString(1, "P_ID_NN"); ps_setparam.setString(2, item.getId_nn().toString()); ps_setparam.addBatch();}                                                                //Long
			if(item.getName()!=null) { ps_setparam.setString(1, "P_NAME"); ps_setparam.setString(2, item.getName()); ps_setparam.addBatch();}                                                                   //String
			if(item.getManufacturer()!=null) { ps_setparam.setString(1, "P_MANUFACTURER"); ps_setparam.setString(2, item.getManufacturer()); ps_setparam.addBatch();}                                           //String
			if(item.getInvent_num()!=null) { ps_setparam.setString(1, "P_INVENT_NUM"); ps_setparam.setString(2, item.getInvent_num()); ps_setparam.addBatch();}                                                 //String
			if(item.getReason()!=null) { ps_setparam.setString(1, "P_REASON"); ps_setparam.setString(2, item.getReason()); ps_setparam.addBatch();}                                                             //String
			if(item.getDoc_num()!=null) { ps_setparam.setString(1, "P_DOC_NUM"); ps_setparam.setString(2, item.getDoc_num()); ps_setparam.addBatch();}                                                          //String
			if(item.getDoc_date()!=null) { ps_setparam.setString(1, "P_DOC_DATE"); ps_setparam.setString(2, df.format(item.getDoc_date())); ps_setparam.addBatch();}                                                       //Date
			if(item.getPrice_market()!=null) { 
				String price_market = BigDecimal.valueOf(item.getPrice_market()).divide(BigDecimal.valueOf(100l)).toString();
				price_market = price_market.replace(not_format, format);
				ps_setparam.setString(1, "P_PRICE_MARKET"); 
				ps_setparam.setString(2,price_market); 
				ps_setparam.addBatch();}                                           //Long
			if(item.getPrice_zalog()!=null) {
				String price_zalog = BigDecimal.valueOf(item.getPrice_zalog()).divide(BigDecimal.valueOf(100l)).toString();
				price_zalog = price_zalog.replace(not_format, format);
				ps_setparam.setString(1, "P_PRICE_ZALOG"); 
				ps_setparam.setString(2, price_zalog); 
				ps_setparam.addBatch();}                                              //Long
			if(item.getDate_made()!=null) { ps_setparam.setString(1, "P_DATE_MADE"); ps_setparam.setString(2, item.getDate_made().toString()); ps_setparam.addBatch();}                                                    //Integer
			if(item.getDate_operation()!=null) { ps_setparam.setString(1, "P_DATE_OPERATION"); ps_setparam.setString(2, df.format(item.getDate_operation())); ps_setparam.addBatch();}                                     //Date
			if(item.getEq_type()!=null) { ps_setparam.setString(1, "P_EQ_TYPE"); ps_setparam.setString(2, item.getEq_type()); ps_setparam.addBatch();}                                                          //String
			if(item.getCountry()!=null) { ps_setparam.setString(1, "P_COUNTRY"); ps_setparam.setString(2, item.getCountry()); ps_setparam.addBatch();}                                                          //String
			if(item.getEq_place_region()!=null) { ps_setparam.setString(1, "P_EQ_PLACE_REGION"); ps_setparam.setString(2, item.getEq_place_region()); ps_setparam.addBatch();}                                  //String
			if(item.getEq_place_town()!=null) { ps_setparam.setString(1, "P_EQ_PLACE_TOWN"); ps_setparam.setString(2, item.getEq_place_town().toString()); ps_setparam.addBatch();}                                        //Integer
			if(item.getEq_place_distr()!=null) { ps_setparam.setString(1, "P_EQ_PLACE_DISTR"); ps_setparam.setString(2, item.getEq_place_distr()); ps_setparam.addBatch();}                                     //String
			if(item.getEq_place_adres()!=null) { ps_setparam.setString(1, "P_EQ_PLACE_ADRES"); ps_setparam.setString(2, item.getEq_place_adres()); ps_setparam.addBatch();}                                     //String
			if(item.getEq_place_x()!=null) { ps_setparam.setString(1, "P_EQ_PLACE_X"); ps_setparam.setString(2, item.getEq_place_x()); ps_setparam.addBatch();}                                                 //String
			if(item.getEq_place_y()!=null) { ps_setparam.setString(1, "P_EQ_PLACE_Y"); ps_setparam.setString(2, item.getEq_place_y()); ps_setparam.addBatch();}    
			ps_setparam.executeBatch();
			cs.execute();
   		} finally {
   			Utils.close(cs);
   			Utils.close(ps_setparam);
   			Utils.close(rs);
   			Utils.close(ps);
    	}
    }
    
    protected static void saveLd_exp(Ld_exp exp,Connection c) throws Exception{
    	CallableStatement ps_setparam = null;
    	CallableStatement cs = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			ps_setparam = c.prepareCall("{ call param.ClearParam() }");
    		ps_setparam.execute();
    		ps_setparam = c.prepareCall("{ call param.setParam(?, ?) }");
    		cs = c.prepareCall("{ call bpr_control.insertLdExp() }");
    		ps_setparam.setString(1, "P_BRANCH"); ps_setparam.setString(2, exp.getBranch()); ps_setparam.execute();
    		ps_setparam.setString(1, "P_ID"); ps_setparam.setLong(2, exp.getId()); ps_setparam.execute();
    		ps_setparam.setString(1, "P_EXP_ID"); ps_setparam.setLong(2, exp.getExp_id()); ps_setparam.execute();
    		ps_setparam.setString(1, "P_RATE_TYPE"); ps_setparam.setLong(2, exp.getRate_type()); ps_setparam.execute();
    		ps_setparam.setString(1, "P_EXP_DAY"); ps_setparam.setString(2, exp.getExt_days()==null?null:exp.getExt_days()+""); ps_setparam.execute();
    		ps_setparam.setString(1, "P_RATE_METOD"); ps_setparam.setLong(2, exp.getRate_method()); ps_setparam.execute();
    		ps_setparam.setString(1, "P_PAY_METOD"); ps_setparam.setLong(2, exp.getPay_method()); ps_setparam.execute();
    		cs.execute();
    		if(exp.getLd_rates()!=null){
    			for (int i = 0; i < exp.getLd_rates().length; i++) {
    				Ld_rate rate = exp.getLd_rates()[i];
    				saveLdRate(exp, rate, c);
    			}
    		}
		} finally {
			Utils.close(rs);
			Utils.close(ps_setparam);
			Utils.close(cs);
			Utils.close(ps);
		}
    }
    
    protected static void saveLdRate(Ld_exp exp,Ld_rate rate,Connection c) throws Exception{
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	PreparedStatement del_ps = null;
    	PreparedStatement check_ps = null;
    	try {
    		check_ps = c.prepareStatement("select * from ld_rate where id = ? and branch = ? and exp_id = ?");
			check_ps.setLong(1, exp.getId());
			check_ps.setString(2, exp.getBranch());
			check_ps.setLong(3, exp.getExp_id());
			rs = check_ps.executeQuery();
			if(rs.next()){
				check_ps = c.prepareStatement("update ld_rate set act='Z' where id = ? and branch = ? and exp_id = ?");
				check_ps.setLong(1, exp.getId());
				check_ps.setString(2, exp.getBranch());
				check_ps.setLong(3, exp.getExp_id());
				check_ps.execute();
			}
			if(rate.getDate_close()!=null){
				del_ps = c.prepareStatement("delete from ld_rate where BRANCH=? and ID=? and EXP_ID=? and DATE_OPEN=?");
				del_ps.setString(1, exp.getBranch());
				del_ps.setLong(2, exp.getId());
				del_ps.setLong(3, exp.getExp_id());
				del_ps.setDate(4, new java.sql.Date(rate.getDate_open().getTime()));
				del_ps.execute();
			}
			if(rate.isNew()){
    			ps = c.prepareStatement("insert into ld_rate(branch,id,exp_id,rate_id,rate,coeff,date_open,date_close,act) values (?,?,?,?,?,?,?,?,?)");
				ps.setString(1, exp.getBranch());
				ps.setLong(2, exp.getId());
				ps.setLong(3, exp.getExp_id());
				ps.setLong(4, rate.getRate_id());
				ps.setDouble(5, rate.getRate());
				if(rate.getCoeff()!=null){
    				ps.setDouble(6, rate.getCoeff());
				} else {
					ps.setNull(6, java.sql.Types.NUMERIC);
				}
				ps.setDate(7, rate.getDate_open()==null?null:new java.sql.Date(rate.getDate_open().getTime()));
				ps.setDate(8, rate.getDate_close()==null?null:new java.sql.Date(rate.getDate_close().getTime()));
				ps.setString(9, "A");
			} else {
				ps = c.prepareStatement("update ld_rate set rate_id = ?, rate = ?, coeff = ?, date_open = ?, date_close = ? where id = ? and branch = ? and exp_id = ?");
				ps.setLong(1, rate.getRate_id());
				ps.setDouble(2, rate.getRate());
				if(rate.getCoeff()!=null){
					ps.setDouble(3, rate.getCoeff());
				} else {
					ps.setNull(3, java.sql.Types.NUMERIC);
				}
				ps.setDate(4, rate.getDate_open()==null?null:new java.sql.Date(rate.getDate_open().getTime()));
				ps.setDate(5, rate.getDate_close()==null?null:new java.sql.Date(rate.getDate_close().getTime()));
				ps.setLong(6, exp.getId());
				ps.setString(7, exp.getBranch());
				ps.setLong(8, exp.getExp_id());
			}
			ps.execute();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			Utils.close(del_ps);
			Utils.close(check_ps);
		}
    }
    
    protected static void deleteForm(Long id,String branch,String alias){
    	Connection c = null;
    	PreparedStatement ps = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from ld_char where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.execute();
			ps = c.prepareStatement("delete from ld_forms where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.execute();
			ps = c.prepareStatement("delete from ld_exp where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.execute();
			ps = c.prepareStatement("delete from ld_rate where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.execute();
			ps = c.prepareStatement("delete from ld_gr where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.execute();
			ps = c.prepareStatement("delete from ld_graf where id = ? and branch = ?");
			ps.setLong(1, id);
			ps.setString(2, branch);
			ps.execute();
			c.commit();
    	} catch (Exception e) {
			Utils.rollback(c);
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
    }
    
    protected static void fillGraf(Long id,Long oper_id,String exp_id,String alias){
    	Connection c = null;
    	CallableStatement cs = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			cs = c.prepareCall("{ call proc_ld.FillGraf(?,?,?) }");
			cs.setLong(1, id);
			cs.setLong(2, oper_id);
			cs.setString(3, exp_id);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
    }
    
    protected static void recallGraf(Long id,String alias){
    	Connection c = null;
    	CallableStatement cs = null;
    	try {
			c = ConnectionPool.getConnection(alias);
			cs = c.prepareCall("{ call info.init() }");
			cs.execute();
			cs = c.prepareCall("{ call proc_ld_ext.RecalcGraf(?) }");
			cs.setLong(1, id);
			cs.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			Utils.rollback(c);
		} finally {
			Utils.close(cs);
			ConnectionPool.close(c);
		}
    }
    
    protected static List<RefData> getBranch(String alias){
    	return Utils.getRefData("select bank_id,bank_id||' - '||bank_name from s_mfo order by bank_id", alias);
    }
    
    protected static List<RefData> getSs_type_ans(String alias){
    	return Utils.getRefData("select code data,name label from SS_TYPE_ANS order by data", alias);
    }
    
    protected static List<RefData> getSs_ent_acc(String alias){
    	return Utils.getRefData("select id data,name label from SS_ENT_ACC order by label", alias);
    }
    
    protected static List<RefData> getCurrency(String alias){
    	return Utils.getRefData("select kod data,kod||' - '||kod_b from S_VAL where act='A' order by data", alias);
    }
    
    protected static List<RefData> getStatus(String alias){
    	return Utils.getRefData("select id data,name label from ss_ld_status order by label", alias);
    }
    
    protected static List<RefData> getCalc_method(String alias){
    	return Utils.getRefData("select id data,name label from ss_ld_calc_method", alias);
    }
    
    protected static List<RefData> getShifr_id(String alias){
    	return Utils.getRefData("select kod_gr||kod_pgr||shifr_id data,kod_gr||kod_pgr||shifr_id||' - '||shifr_name from S_SHIFR where shifr_id!='00' and act='A' order by data", alias);
    }
    
    protected static List<RefData> getSred_id(String alias){
    	return Utils.getRefData("select sred_id data,sred_name label from S_SRED where act='A' and sred_gr!='00' order by sred_gr,label", alias);
    }
    
    protected static List<RefData> getTerm_type(String alias){
    	return Utils.getRefData("select kod_kr data,type_kr label from S_SROKKR where act='A'", alias);
    }
    
    protected static List<RefData> getType_id(String alias){
    	return Utils.getRefData("select type_id data,name label from ss_ld_type where ld_type = 1 order by type_id", alias);
    }
    
    protected static List<RefData> getKred_id(String alias){
    	return Utils.getRefData("select kred_id data,kred_name label from ss_credit where act='A' order by label", alias);
    }
    
    protected static List<RefData> getKred_id_cb(String alias) {
		return Utils.getRefData("select kred_id data,kred_name label from s_credit where act='A' and kred_gr!='00' order by label", alias);
	}
    
    protected static List<RefData> getUse_branch(String alias){
    	return Utils.getRefData("select kod_zm||kod_pod data, name_zm label from S_TYPE_ZM where act='A'", alias);
    }
    
    protected static List<RefData> getKlass_id(String alias){
    	return Utils.getRefData("select klass_id data,klass_name label from S_KLASS where act='A' order by label", alias);
    }
    
    protected static List<RefData> getKlassp_id(String alias){
    	return Utils.getRefData("select klassp_id data,klass_name label from S_KLASSP where act='A' order by label", alias);
    }
    
    protected static List<RefData> getCres(String alias){
    	return Utils.getRefData("select id data,name label from ss_ld_reserv order by label", alias);
    }
    
    protected static List<RefData> getKod_fin(String alias){
    	return Utils.getRefData("select kod_fin data,name_fin label from S_FINORG where act='A' order by label", alias);
    }
    
    public static List<RefData> getTypes_obesp(){
    	return Utils.getRefData("select guar_id,guar_name from s_obesp where act='A' and guar_gr not in ('00')","");
    }
    
    public static List<RefData> getLdExpType(){
    	return Utils.getRefData("select id,id ||' - '|| name from ss_ld_exp_type order by id", "");
    }
    
    public static List<RefData> getOper_id(){
    	return Utils.getRefData("select id,id||' - '||name from V_SS_LD_GR_TYPE order by id", "");
    }
    
    public static List<RefData> getRepay(){
    	return Utils.getRefData("select 1 id,'Срочное погашение' name from dual union all select 8 id ,'Досрочное погашение' name from dual", "");
    }
    
    protected static String getNikiStateName(String form_id,String branch,String alias){
    	String temp = Utils.getData("select * from (select state_name from (select * from ldr2_04 union all select * from ldr2_04_arh union all select * from ldr2_03 union all select * from ldr2_03_arh) n where n.ld_forms_id="+form_id+" and n.branch='"+branch+"' order by n.id desc) where rownum=1", alias);
    	if(temp==null||temp.equals("")){ temp = "Не создан"; }
    	return temp;
    }
    
    protected static String getNikiState(String form_id,String branch,String alias){
    	String temp = Utils.getData("select * from (select state_code from (select * from ldr2_04 union all select * from ldr2_04_arh union all select * from ldr2_03 union all select * from ldr2_03_arh) n where n.ld_forms_id="+form_id+" and n.branch='"+branch+"' order by n.id desc) where rownum=1", alias);
    	if(temp==null||temp.equals("")){ temp = "-1"; }
    	return temp;
    }
}
