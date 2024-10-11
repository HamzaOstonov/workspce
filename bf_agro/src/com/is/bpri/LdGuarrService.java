package com.is.bpri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;
import com.is.utils.RefData;

public class LdGuarrService {
	
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "SELECT * FROM v_bpr_ld_guarr";
	private static List<RefData> Currency_ = null;
	private static List<RefData> SingDepo_ = null;
	private static List<RefData> Guar_id_ = null;
	private static List<RefData> Klass_o_ = null;
	private static List<RefData> Region_ = null;
	private static List<RefData> TypeClient_ = null;
	private static List<RefData> Resident_ = null;
	private static List<RefData> Code_ = null; 
	private static List<RefData> TypeClientPor_ = null; 
	private static List<RefData> CodeOrg_ = null;
	private static List<RefData> NameCompany_ = null;
	
	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(LdGuarrFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?",
					filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getGuar_id())) {
			flfields.add(new FilterField(getCond(flfields) + "guar_id=?",
					filter.getGuar_id()));
		}
		if (!CheckNull.isEmpty(filter.getKlass_o())) {
			flfields.add(new FilterField(getCond(flfields) + "klass_o=?",
					filter.getKlass_o()));
		}
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(LdGuarrFilter filter) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_bpr_ld_guarr ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
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

	public static List<LdGuarr> getLdGuarrsFl(int pageIndex, int pageSize,
			LdGuarrFilter filter) {
		List<LdGuarr> list = new ArrayList<LdGuarr>();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new LdGuarr(
                        rs.getLong("id"),
                        rs.getInt("bpr_id"),
                        rs.getString("guar_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("doc_num"),
                        rs.getDate("doc_date"),
                        rs.getString("currency"),
                        rs.getInt("amount"),
                        rs.getString("klass_o"),
                        rs.getInt("canbuy"),
                        rs.getInt("hasdepo"),
                        rs.getString("bank_name"),
                        rs.getInt("hasdoc"),
                        rs.getString("code_subject"),
                        rs.getString("notarial_doc_numb"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("inn"),
                        rs.getString("mfo"),
                        rs.getString("name2"),
                        rs.getString("account"),
                        rs.getString("stock_nominal_value"),
                        rs.getString("stock_count"),
                        rs.getString("stock_diskont"),
                        rs.getString("stock_name"),
                        rs.getString("sign_depo"),
                        rs.getString("insc_name"),
                        rs.getString("insc_inn"),
                        rs.getString("insc_num"),
                        rs.getDate("insc_date"),
                        rs.getDate("insc_date_cl"),
                        rs.getString("niki_res1"),
                        rs.getString("niki_res2"),
                        rs.getString("niki_gr_branch"),
                        rs.getString("niki_gr_code_type"),
                        rs.getString("niki_inn"),
                        rs.getString("niki_soogun"),
                        rs.getString("acomp_name"),
                        rs.getDate("acomp_date"),
                        rs.getString("acomp_curr"),
                        rs.getLong("acomp_summa"),
                        rs.getString("niki_owner"),
                        rs.getString("polis_num"),
                        rs.getDate("polis_date"),
                        rs.getString("region_id"),
                        rs.getString("distr_id"),
                        rs.getString("massiv"),
                        rs.getString("street"),
                        rs.getString("home"),
                        rs.getString("home_num"),
                        rs.getString("economical_zone"),
                        rs.getString("cadastr_org_region"),
                        rs.getString("cadastr_org_distr"),
                        rs.getString("notarial_office_num"),
                        rs.getString("ser_eval_company"),
                        rs.getString("lis_num"),
                        rs.getDate("lis_date"),
                        rs.getString("eval_report_num"),
                        rs.getString("id_client"),
                        rs.getDate("date_operation"),
                        rs.getString("depositary"),
                        rs.getString("depositary_account"),
                        rs.getString("sowing_area"),
                        rs.getDouble("massa"),
                        rs.getString("sertificate_num"),
                        rs.getString("sertificate_ser"),
                        rs.getDouble("sertificate_rate"),
                        rs.getString("inn_reestr"),
                        rs.getString("cadastr_place_region"),
                        rs.getInt("cadastr_place_town"),
                        rs.getString("cadastr_place_distr"),
                        rs.getString("cadastr_place_adres"),
                        rs.getString("cadastr_place_x"),
                        rs.getString("cadastr_place_y"),
                        rs.getInt("building_type"),
                        rs.getInt("building_year"),
                        rs.getInt("building_square"),
                        rs.getInt("build_constr_type"),
                        rs.getInt("building_kind"),
                        rs.getInt("building_num"),
                        rs.getString("region_nam"),
                        rs.getString("distr_name"),
                        rs.getString("cl_name"),
                        rs.getString("type_rez"),
                        rs.getString("klass_name"),
                        rs.getString("guar_name"),
                        rs.getString("name_k2")));
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

	public static void create(LdGuarr ldguarr,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_bpr_ld_guarr.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				ldguarr.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO bpr_ld_guarr " +
					"(id," +
					" bpr_id," +
					" currency," +
					" guar_id," +
					" klass_o," +
					" name," +
					" region_id," +
					" distr_id," +
					" massiv," +
					" street," +
					" home," +
					" home_num," +
					" amount," +
					" doc_num," +
					" doc_date," +
					" code_subject," +
					" inn," +
					" inn_reestr," +
					" mfo," +
					" account," +
					" end_date," +
					" name2," +
					" niki_res2," +
					" niki_gr_branch," +
					" niki_gr_code_type," +
					" niki_soogun," +
					" acomp_name," +
					" acomp_date," +
					" acomp_curr," +
					" acomp_summa," +
					" id_client," +
					" notarial_doc_numb," +
					" start_date," +
					" insc_inn," +
					" insc_num," +
					" insc_date," +
					" polis_num," +
					" polis_date," +
					" sowing_area," +
					" niki_res1," +
					" massa," +
					" address," +
					" stock_name," +
					" niki_inn," +
					" sign_depo," +
					" niki_owner," +
					" insc_date_cl," +
					" date_operation," +
					" stock_nominal_value," +
					" stock_count," +
					" stock_diskont," +
					" depositary," +
					" depositary_account," +
					" sertificate_rate," +
					" sertificate_num," +
					" sertificate_ser)" +
					" VALUES " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, ldguarr.getId());
			ps.setInt(2, ldguarr.getBpr_id());
			ps.setString(3, ldguarr.getCurrency());
			ps.setString(4, ldguarr.getGuar_id());
			ps.setString(5, ldguarr.getKlass_o());
			ps.setString(6, ldguarr.getName());
			ps.setString(7, ldguarr.getRegion_id());
			ps.setString(8, ldguarr.getDistr_id());
			ps.setString(9, ldguarr.getMassiv());
			ps.setString(10, ldguarr.getStreet());
			ps.setString(11, ldguarr.getHome());
			ps.setString(12, ldguarr.getHome_num());
			if(ldguarr.getAmount()>0){
				ps.setInt(13, ldguarr.getAmount());
			} else {
				ps.setNull(13, java.sql.Types.NUMERIC);
			}
			ps.setString(14, ldguarr.getDoc_num());
			ps.setDate(15, ldguarr.getDoc_date()==null?null:new java.sql.Date(ldguarr.getDoc_date().getTime()));
			ps.setString(16, ldguarr.getCode_subject());
			ps.setString(17, ldguarr.getInn());
			ps.setString(18, ldguarr.getInn_reestr());
			ps.setString(19, ldguarr.getMfo());
			ps.setString(20, ldguarr.getAccount());
			ps.setDate(21, ldguarr.getEnd_date()==null?null:new java.sql.Date(ldguarr.getEnd_date().getTime()));
			ps.setString(22, ldguarr.getName2());
			ps.setString(23, ldguarr.getNiki_res2());
			ps.setString(24, ldguarr.getNiki_gr_branch());
			ps.setString(25, ldguarr.getNiki_gr_code_type());
			ps.setString(26, ldguarr.getNiki_soogun());
			ps.setString(27, ldguarr.getAcomp_name());
			ps.setDate(28, ldguarr.getAcomp_date()==null?null:new java.sql.Date(ldguarr.getAcomp_date().getTime()));
			ps.setString(29, ldguarr.getAcomp_curr());
			if(ldguarr.getAcomp_summa()>0){
				ps.setLong(30, ldguarr.getAcomp_summa());
			} else {
				ps.setNull(30, java.sql.Types.NUMERIC);
			}
			ps.setString(31, ldguarr.getId_client());
			ps.setString(32, ldguarr.getNotarial_doc_numb());
			ps.setDate(33, ldguarr.getStart_date()==null?null:new java.sql.Date(ldguarr.getStart_date().getTime()));
			ps.setString(34, ldguarr.getInsc_inn());
			ps.setString(35, ldguarr.getInsc_num());
			ps.setDate(36, ldguarr.getInsc_date()==null?null:new java.sql.Date(ldguarr.getInsc_date().getTime()));
			ps.setString(37, ldguarr.getPolis_num());
			ps.setDate(38, ldguarr.getPolis_date()==null?null:new java.sql.Date(ldguarr.getPolis_date().getTime()));
			ps.setString(39, ldguarr.getSowing_area());
			ps.setString(40, ldguarr.getNiki_res1());
			ps.setDouble(41, ldguarr.getMassa());
			ps.setString(42, ldguarr.getAddress());
			ps.setString(43, ldguarr.getStock_name());
			ps.setString(44, ldguarr.getNiki_inn());
			ps.setString(45, ldguarr.getSign_depo());
			ps.setString(46, ldguarr.getNiki_owner());
			ps.setDate(47, ldguarr.getInsc_date_cl()==null?null:new java.sql.Date(ldguarr.getInsc_date_cl().getTime()));
			ps.setDate(48, ldguarr.getDate_operation()==null?null:new java.sql.Date(ldguarr.getDate_operation().getTime()));
			ps.setString(49, ldguarr.getStock_nominal_value());
			ps.setString(50, ldguarr.getStock_count());
			ps.setString(51, ldguarr.getStock_diskont());
			ps.setString(52, ldguarr.getDepositary());
			ps.setString(53, ldguarr.getDepositary_account());
			ps.setDouble(54, ldguarr.getSertificate_rate());
			ps.setString(55, ldguarr.getSertificate_num());
			ps.setString(56, ldguarr.getSertificate_ser());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	public static void update(LdGuarr ldguarr,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_guarr SET " +
					" currency=?," +
					" guar_id=?," +
					" klass_o=?," +
					" name=?," +
					" region_id=?," +
					" distr_id=?," +
					" massiv=?," +
					" street=?," +
					" home=?," +
					" home_num=?," +
					" amount=?," +
					" doc_num=?," +
					" doc_date=?," +
					" code_subject=?," +
					" inn=?," +
					" inn_reestr=?," +
					" mfo=?," +
					" account=?," +
					" end_date=?," +
					" name2=?," +
					" niki_res2=?," +
					" niki_gr_branch=?," +
					" niki_gr_code_type=?," +
					" niki_soogun=?," +
					" acomp_name=?," +
					" acomp_date=?," +
					" acomp_curr=?," +
					" acomp_summa=?," +
					" id_client=?," +
					" notarial_doc_numb=?," +
					" start_date=?," +
					" insc_inn=?," +
					" insc_num=?," +
					" insc_date=?," +
					" polis_num=?," +
					" polis_date=?," +
					" sowing_area=?," +
					" niki_res1=?," +
					" massa=?," +
					" address=?," +
					" stock_name=?," +
					" niki_inn=?," +
					" sign_depo=?," +
					" niki_owner=?," +
					" insc_date_cl=?," +
					" date_operation=?," +
					" stock_nominal_value=?," +
					" stock_count=?," +
					" stock_diskont=?," +
					" depositary=?," +
					" depositary_account=?," +
					" sertificate_rate=?," +
					" sertificate_num=?," +
					" sertificate_ser=?" +
					"  WHERE bpr_id=? and id=? ");
			ps.setString(1, ldguarr.getCurrency());
			ps.setString(2, ldguarr.getGuar_id());
			ps.setString(3, ldguarr.getKlass_o());
			ps.setString(4, ldguarr.getName());
			ps.setString(5, ldguarr.getRegion_id());
			ps.setString(6, ldguarr.getDistr_id());
			ps.setString(7, ldguarr.getMassiv());
			ps.setString(8, ldguarr.getStreet());
			ps.setString(9, ldguarr.getHome());
			ps.setString(10, ldguarr.getHome_num());
			if(ldguarr.getAmount()>0){
				ps.setInt(11, ldguarr.getAmount());
			} else {
				ps.setNull(11, java.sql.Types.NUMERIC);
			}
			ps.setString(12, ldguarr.getDoc_num());
			ps.setDate(13, ldguarr.getDoc_date()==null?null:new java.sql.Date(ldguarr.getDoc_date().getTime()));
			ps.setString(14, ldguarr.getCode_subject());
			ps.setString(15, ldguarr.getInn());
			ps.setString(16, ldguarr.getInn_reestr());
			ps.setString(17, ldguarr.getMfo());
			ps.setString(18, ldguarr.getAccount());
			ps.setDate(19, ldguarr.getEnd_date()==null?null:new java.sql.Date(ldguarr.getEnd_date().getTime()));
			ps.setString(20, ldguarr.getName2());
			ps.setString(21, ldguarr.getNiki_res2());
			ps.setString(22, ldguarr.getNiki_gr_branch());
			ps.setString(23, ldguarr.getNiki_gr_code_type());
			ps.setString(24, ldguarr.getNiki_soogun());
			ps.setString(25, ldguarr.getAcomp_name());
			ps.setDate(26, ldguarr.getAcomp_date()==null?null:new java.sql.Date(ldguarr.getAcomp_date().getTime()));
			ps.setString(27, ldguarr.getAcomp_curr());
			if(ldguarr.getAcomp_summa()>0){
				ps.setLong(28, ldguarr.getAcomp_summa());
			} else {
				ps.setNull(28, java.sql.Types.NUMERIC);
			}
			ps.setString(29, ldguarr.getId_client());
			ps.setString(30, ldguarr.getNotarial_doc_numb());
			ps.setDate(31, ldguarr.getStart_date()==null?null:new java.sql.Date(ldguarr.getStart_date().getTime()));
			ps.setString(32, ldguarr.getInsc_inn());
			ps.setString(33, ldguarr.getInsc_num());
			ps.setDate(34, ldguarr.getInsc_date()==null?null:new java.sql.Date(ldguarr.getInsc_date().getTime()));
			ps.setString(35, ldguarr.getPolis_num());
			ps.setDate(36, ldguarr.getPolis_date()==null?null:new java.sql.Date(ldguarr.getPolis_date().getTime()));
			ps.setString(37, ldguarr.getSowing_area());
			ps.setString(38, ldguarr.getNiki_res1());
			ps.setDouble(39, ldguarr.getMassa());
			ps.setString(40, ldguarr.getAddress());
			ps.setString(41, ldguarr.getStock_name());
			ps.setString(42, ldguarr.getNiki_inn());
			ps.setString(43, ldguarr.getSign_depo());
			ps.setString(44, ldguarr.getNiki_owner());
			ps.setDate(45, ldguarr.getInsc_date_cl()==null?null:new java.sql.Date(ldguarr.getInsc_date_cl().getTime()));
			ps.setDate(46, ldguarr.getDate_operation()==null?null:new java.sql.Date(ldguarr.getDate_operation().getTime()));
			ps.setString(47, ldguarr.getStock_nominal_value());
			ps.setString(48, ldguarr.getStock_count());
			ps.setString(49, ldguarr.getStock_diskont());
			ps.setString(50, ldguarr.getDepositary());
			ps.setString(51, ldguarr.getDepositary_account());
			ps.setDouble(52, ldguarr.getSertificate_rate());
			ps.setString(53, ldguarr.getSertificate_num());
			ps.setString(54, ldguarr.getSertificate_ser());
			ps.setInt(55, ldguarr.getBpr_id());
			ps.setLong(56, ldguarr.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void remove(LdGuarr ldGuarr,Res res,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_ld_guarr where id=?");
			ps.setLong(1, ldGuarr.getId());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}

	// --------------------------------------Справочники------------------
	public static List<RefData> getCurrency(String branch) {
		if (Currency_ == null) {
			Currency_ = Utils.getRefData("select kod data, kod||' - '|| namev label from s_val order by data",branch);
		}
		return Currency_;
	}
	
	public static List<RefData> getSing_Depo(String branch) {
		if (SingDepo_ == null) {
			SingDepo_ = Utils.getRefData("select 1 data, 'Депозиты'  label FROM DUAL UNION ALL select 2 CODE, 'Драг.металлы NAME' FROM DUAL UNION ALL select 3 CODE, 'Иностранная валюта' NAME FROM DUAL",branch);
		}
		return SingDepo_;
	}
	
	public static List<RefData> getRegion(String alias){
		if(Region_== null){
			Region_ = Utils.getRefData("select s_region.region_id data,s_region.region_id||' - '||s_region.region_nam label from s_region order by s_region.region_id", alias);
		}
		return Region_;
	}
	
	public static List<RefData> getTypeClient(String alias){
		if(TypeClient_ == null){
			TypeClient_ = Utils.getRefData("select 'P' data, 'Физическое лицо' label from dual union all select 'J' code, 'Юридическое лицо' name from dual", alias);
		}
		return TypeClient_;
	}
	
	public static List<RefData> getDistr(String alias,String regid){
		return Utils.getRefData("select S_DISTR.Distr data,S_DISTR.Distr||' - '||S_DISTR.DISTR_NAME label from S_DISTR where S_DISTR.Region_Id='"+regid+"'", alias);
	}
	
	public static List<RefData> getResident(String alias){
		if(Resident_ == null){
			Resident_ = Utils.getRefData("select S_REZKL.Kod_Rez data,S_REZKL.Kod_Rez||' - '||S_REZKL.Type_Rez label from S_REZKL order by S_REZKL.Kod_Rez", alias);
		}
		return Resident_;
	}
	
	public static List<RefData> getNotarialNumb(String alias){
		return Utils.getRefData("select ss_ld_guar_notarius.name data, ss_ld_guar_notarius.name label from ss_ld_guar_notarius order by distr_id,name", alias);
	}
	
	public static List<RefData> getEq_type(String alias){
		return Utils.getRefData("select SS_LD_GUAR_EQ_TYPE.Id data,SS_LD_GUAR_EQ_TYPE.NAME label from SS_LD_GUAR_EQ_TYPE order by data", alias);
	}
	
	public static List<RefData> getS_str(String alias){
		return Utils.getRefData("select s_str.code_str data,s_str.name label from s_str order by label", alias);
	}
	
	public static List<RefData> getYears(String alias){
		return Utils.getRefData("select ss_ld_guar_year.id data,ss_ld_guar_year.name label from ss_ld_guar_year order by label", alias);
	}
	
	public static List<RefData> getInscName(String alias){
		return Utils.getRefData("select ss_ld_guar_insc_main.name data, ss_ld_guar_insc_main.name label from ss_ld_guar_insc_main order by label", alias);
	}
	
	public static List<RefData> getCode(String alias){
		if(Code_ == null){
			Code_ = Utils.getRefData("select S_SPR_OKED.Code data,S_SPR_OKED.Code||' - '||S_SPR_OKED.Name_Ru label from S_SPR_OKED order by S_SPR_OKED.Code", alias);
		}
		return Code_;
	}
	
	public static List<RefData> getTypeClientPor(String alias){
		if(TypeClientPor_ == null){
			TypeClientPor_ = Utils.getRefData("select S_TYPEKL.Kod_k data,S_TYPEKL.Kod_k||' - '||S_TYPEKL.Name_K2 label from S_TYPEKL order by S_TYPEKL.Kod_k", alias);
		}
		return TypeClientPor_;
	}
	
	public static List<RefData> getCodeOrg(String alias){
		if(CodeOrg_ == null){
			CodeOrg_ = Utils.getRefData("select S_SOOGUN.Soogu data,S_SOOGUN.Soogu||' - '||S_SOOGUN.Soogu1 label from S_SOOGUN order by S_SOOGUN.Soogu", alias);
		}
		return CodeOrg_;
	}
	
	public static List<RefData> getNameCompany(String alias){
		if(NameCompany_ == null){
			NameCompany_ = Utils.getRefData("select ss_ld_guar_acomp.name data,ss_ld_guar_acomp.name label from ss_ld_guar_acomp order by ss_ld_guar_acomp.name", alias);
		}
		return NameCompany_;
	}
	
	public static List<RefData> getOvnership(String alias){
		return Utils.getRefData("select ss_ld_guar_ovnership.id data, ss_ld_guar_ovnership.name label from ss_ld_guar_ovnership", alias);
	}

	public static List<RefData> getGuar_id(String branch) {
		if (Guar_id_ == null) {
			Guar_id_ = Utils.getRefData("select guar_id data, guar_id||' - '|| guar_name label from s_obesp where guar_id not like '%0' order by data ",branch);
		}
		return Guar_id_;
	}

	public static List<RefData> getKlass_o(String branch) {
		if (Klass_o_ == null) {
			Klass_o_ = Utils.getRefData("select klass_o data, klass_o|| ' - ' || klass_name label from s_klasso",branch);
		}
		return Klass_o_;
	}
	
	public static List<RefData> getClientName(String cl_id,String branch,String alias){
		return Utils.getRefData("select client.code_subject data,client.name label from client where client.id_client='"+cl_id+"' and client.branch='"+branch+"'", alias);
	}
	
	public static String getResidentResult(String data,String branch,String alias){
		return getResultString("select client.code_resident from client where client.id_client=? and client.branch=?", data, branch, alias);
	}

	// ------------------------------------Конец справочников-----------------
	
	public static String getResultString(String sql,String data,String branch,String alias){
		String result = "";
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql);
			ps.setString(1, data);
			if(!branch.equals("")){
				ps.setString(2, branch);
			}
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return result;
	}

}
