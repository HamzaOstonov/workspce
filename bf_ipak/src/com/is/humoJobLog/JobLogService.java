package com.is.humoJobLog;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.SQLException;

import java.sql.Date;

import java.util.List;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;


import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

//import com.is.tietovisautils.utils.Utils;

import com.is.ISLogger;



import com.is.ConnectionPool;

//import com.rabbitmq.client.AMQP.Confirm.Select;

public class JobLogService {

	private static String psql1;
	private static String psql2;
	private static String msql;
	private static String msql2;
	// private static AddCstViewCtrl addCst = new AddCstViewCtrl();
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
	private static SimpleDateFormat mdf = new SimpleDateFormat("yyMMdd-HHmmss");
	// private static SimpleDateFormat tdf = new SimpleDateFormat("yyyy-MM-dd");
	// 1979-07-10T00:00:00

	private static List<RefData> listTypeDocument;
	private static List<RefData> listClientTypeWay4;
	private static List<RefData> listAccBal;
	private static List<RefData> listProducts;
	private static List<RefData> listBinCodes;
	private static List<RefData> listIzdBranches;
	private static List<RefData> listAccCondSets;
	private static List<RefData> listCardCondSets;
	private static List<RefData> listRiskLevels;
	private static List<RefData> listDesigns;
	private static List<RefData> listStatesNciAccount;
	private static HashMap<String, String> mapStatesNciAccount;	
	
			
	
	
	private static List<RefData> listStopCauses;
	private static HashMap<String, String> mapStopCauses;
	private static List<RefData> listStatesTieto;
	private static HashMap<String, String> mapStatesTieto;
	private static HashMap<String, List<RefData>> listSubProductByProduct;
	private static List<RefData> listTax;
	private static List<RefData> listRegion;
	private static List<RefData> listDistr;
	private static HashMap<String, List<RefData>> listDistrByRegion;

	private static List<RefData> listCountry;
	private static List<RefData> listFileStates;
	private static List<RefData> listTietoRegions;
	private static List<RefData> listTietoCountryTypes;
	private static List<RefData> listWayCountries;
	private static List<RefData> listRezCl;
	private static List<RefData> listGender;
	private static List<RefData> listNation;
    //private static String constBankC;
    //private static String constGroupC;
    public static HashMap<String, String> mapConst;
    
	static {
		JobLogService.psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
		JobLogService.psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
		JobLogService.msql = "select * from ";
		JobLogService.msql2 = "v_bf_tietovisa_client";
	}

	public JobLogService() {
		// this.cvc = new CustomerViewCtrl();
	}

	


	public static List<ExptLog> getExptLogs(Long pId_from, Long pId_to, int pStatus) {

		List<ExptLog> list = new ArrayList<ExptLog>();
		Connection c = null;
		PreparedStatement ps = null;
		String sel="select "+
       "(select e.text "+
       "   from EMPC_EXPT_RECORDS_PRC_ERRORS_humo e "+
       "  where r.id = e.record_id) err_, "+
       "  r.empc_file_id, "+
       "r.id, "+
       "r.card,  "+ 
       "r.merchant, "+
       "r.term_id, "+
       "substr(r.term_id, 3, 1) as TERM_TYPE, "+
       "substr(r.term_id, 4, 1)as OPER, "+
       "substr(r.merchant, 0, 5)as TERMINAL_BRANCH, "+
       "r.iss_mfo as CARD_BRANCH, "+
       "r.tran_type, "+
       "r.Tran_Amt, "+
       "r.Tran_Type2, "+
       "r.Tran_Amt2, "+
       "r.in_file, "+
       "r.mcc_code, "+
       "r.accnt_ccy, "+
       "r.tran_ccy, "+
       "r.country, "+
       "r.state_id, "+
       "(select w.name from empc_file_state w where w.id = r.state_id) stat_, "+
       "(select f.id || ' # ' || f.file_name "+
       "   from v_empc_files f "+
       "  where r.empc_file_id = f.id) file_, "+
       "r.abvr_name       , "+
       "r.accnt_amt       , "+
       "r.card_acct       , "+
       "r.city            , "+
       "r.client          , "+
       "r.deal_desc       , "+
       "r.deb_cred        , "+
       "r.internal_no     , "+
       "r.iss_mfo         , "+
       "r.line_number     , "+
       "r.point_code      , "+
       "r.post_date       , "+ 
       "r.proc_id         , "+
       "r.product         , "+
       "r.rec_date        , "+
       "r.record_type     , "+
       "r.ref_number      , "+
       "r.settl_cmi       , "+
       "r.slip_nr         , "+
       "r.terminal        , "+
       "r.tran_date_time  , "+
       "r.tranz_acct       "+
       "from EMPC_EXPT_RECORDS r "+
       "where r.empc_file_id between ? and ? "+
       "and r.state_id = ? "+
       "order by err_";
       
       
		try {
			c = ConnectionPool.getConnection();
			//Statement s = c.createStatement();
			//ResultSet rs = s.executeQuery("SELECT * FROM humo_oper_type");
			ps = c.prepareStatement(sel);
			ps.setLong(1, pId_from);
			ps.setLong(2, pId_to);
			ps.setInt(3, pStatus);
			
			final ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				ExptLog item = new ExptLog();
				
				item.setErr_(rs.getString("err_"));
                item.setEmpc_file_id(rs.getString("empc_file_id"));
                item.setId(rs.getString("id"));
                item.setCard(rs.getString("card"));
                item.setMerchant(rs.getString("merchant"));
                item.setTerm_id(rs.getString("term_id"));
                item.setTerm_type(rs.getString("term_type"));
                item.setOper(rs.getString("oper"));
                item.setTerminal_branch(rs.getString("terminal_branch"));
                item.setCard_branch(rs.getString("card_branch"));
                item.setTran_type(rs.getString("tran_type"));
                item.setTran_amt(rs.getString("tran_amt"));
                item.setTran_type2(rs.getString("tran_type2"));
                item.setTran_amt2(rs.getString("tran_amt2"));
                item.setIn_file(rs.getString("in_file"));
                item.setMcc_code(rs.getString("mcc_code"));
                item.setAccnt_ccy(rs.getString("accnt_ccy"));
                item.setTran_ccy(rs.getString("tran_ccy"));
                item.setCountry(rs.getString("country"));
                item.setState_id(rs.getString("state_id"));
                item.setStat_(rs.getString("stat_"));
                item.setFile_(rs.getString("file_"));
                item.setRecord_type(rs.getString("record_type"));
                item.setLine_number(rs.getString("line_number"));
                item.setClient(rs.getString("client"));
                item.setCard_acct(rs.getString("card_acct"));
                item.setSlip_nr(rs.getString("slip_nr"));
                item.setRef_number(rs.getString("ref_number"));
                item.setTran_date_time(rs.getString("tran_date_time"));
                item.setRec_date(rs.getString("rec_date"));
                item.setPost_date(rs.getString("post_date"));
                item.setDeal_desc(rs.getString("deal_desc"));
                item.setDeb_cred(rs.getString("deb_cred"));
                item.setAccnt_amt(rs.getString("accnt_amt"));
                item.setTerminal(rs.getString("terminal"));
                item.setAbvr_name(rs.getString("abvr_name"));
                item.setCity(rs.getString("city"));
                item.setProc_id(rs.getString("proc_id"));
                item.setInternal_no(rs.getString("internal_no"));
                item.setProduct(rs.getString("product"));
                item.setIss_mfo(rs.getString("iss_mfo"));
                item.setTranz_acct(rs.getString("tranz_acct"));
                item.setPoint_code(rs.getString("point_code"));
                item.setSettl_cmi(rs.getString("settl_cmi"));

				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			com.is.LtLogger.getLogger()
					.error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}
	
	
	public static boolean isTietoFilterNull (CustomerFilter filter) {
		if ( (filter.getP_family() == null || filter.getP_family().equals("") ) && 
				(filter.getP_first_name() == null || filter.getP_first_name().equals("") ) && 
				(filter.getId_tieto() == null || filter.getId_tieto().equals("") ) && 
				(filter.getT_client_b() == null || filter.getT_client_b().equals("") ) &&   
				(filter.getP_birthday() == null || filter.getP_birthday().equals("") ) &&
				(filter.getP_pinfl() == null || filter.getP_pinfl().equals("") ) &&
				(filter.getP_phone_mobile() == null || filter.getP_phone_mobile().equals("") )
			)
			return true;		
		return false;
	} 
	
	private static List<FilterField> getFilterFields(final CustomerFilter filter) {
		final List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(Long.valueOf(filter.getId()))) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "id=?", (Object) filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "id_client=?", (Object) filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "branch=?", (Object) filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "upper(name) like ?", (Object) ("%"
					+ filter.getName().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_country=?", (Object) filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_type=?", (Object) filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_resident=?", (Object) filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_subject=?", (Object) filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "sign_registr=?", (Object) filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "code_form=?", (Object) filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "date_open=?", (Object) filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "date_close =?", (Object) filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "state=?", (Object) filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_birthday=?", (Object) new Date(filter.getP_birthday()
					.getTime())));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_post_address=?", (Object) filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_type=?", (Object) filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields))
							+ "(p_passport_serial=? or p_passport_serial||p_passport_number = ?)",
					(Object) filter.getP_passport_serial()));
			flfields.add(new FilterField("", (Object) (filter
					.getP_passport_serial())));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_number=?", (Object) filter
					.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_place_registration=?", (Object) filter
					.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_date_registration=?", (Object) filter
					.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_tax_org=?", (Object) filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_number_tax_registration=?", (Object) filter
					.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_bank=?", (Object) filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_class_credit=?", (Object) filter
					.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_citizenship=?", (Object) filter
					.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_birth_place=?", (Object) filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_capacity=?", (Object) filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_capacity_status_date=?", (Object) filter
					.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_capacity_status_place=?", (Object) filter
					.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_num_certif_capacity=?", (Object) filter
					.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_phone_home=?", (Object) filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_phone_mobile=?", (Object) filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_email_address=?", (Object) filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_pension_sertif_serial=?", (Object) filter
					.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_gender=?", (Object) filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_nation=?", (Object) filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_birth_region=?", (Object) filter
					.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_birth_distr=?", (Object) filter
					.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_type_document=?", (Object) filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_passport_date_expiration=?", (Object) filter
					.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_adr_region=?", (Object) filter
					.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_code_adr_distr=?", (Object) filter
					.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_inps=?", (Object) filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_pinfl())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "p_pinfl like ?", "%" + (Object) filter.getP_pinfl()
					+ "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "(upper(name) like ?" + " or upper(p_family) like ?)",
					(Object) ("%" + filter.getP_family().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_family().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?"
							+ " or upper(p_first_name) like ?)", (Object) ("%"
							+ filter.getP_first_name().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_first_name().toUpperCase() + "%")));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(
					String.valueOf(getCond(flfields)) + "(upper(name) like ?"
							+ " or upper(p_patronymic) like ?)", (Object) ("%"
							+ filter.getP_patronymic().toUpperCase() + "%")));
			flfields.add(new FilterField("", (Object) ("%"
					+ filter.getP_patronymic().toUpperCase() + "%")));
		}
		String notNull = "";
		if (!CheckNull.isEmpty(filter.getTietoIdIsNotNull())) {
			notNull = " and tieto_customer_id is not null ";
		}
		if (!CheckNull.isEmpty(filter.getId_tieto())) {
			flfields.add(new FilterField(String.valueOf(getCond(flfields))
					+ "id_tieto=?", (Object) filter.getId_tieto()));
		}
		flfields.add(new FilterField(String.valueOf(getCond(flfields))
				+ "rownum<? " + notNull, (Object) 1001));
		/*
		 * if (!(filter.getCard() == null)) { if
		 * (!filter.getCard().matches("[0-9]+")) { if
		 * (filter.getCard().contains("%")) { flfields.add(new FilterField(
		 * String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.card like ?)", filter.getCard())); }
		 * else { flfields.add(new FilterField(
		 * String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.card = ?)", filter.getCard())); } } else
		 * { flfields.add(new FilterField( String.valueOf(getCond(flfields)) +
		 * "id_client in (select client_b from humo_cards c where c.branch = " +
		 * filter.getBranch() + " and c.real_card = ?)", filter.getCard())); } }
		 */
		return flfields;
	}

	public static int getCount(final CustomerFilter filter, final String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement inf = null;
		int n = 0;
		final List<FilterField> flFields = getFilterFields(filter);
		final StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_bf_humo_customer");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); ++i) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			ps = c.prepareStatement(sql.toString());
			for (int k = 0; k < flFields.size(); ++k) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			return n;
		} finally {
			close(rs);
			close(ps);
			close(inf);
			ConnectionPool.close(c);
		}
		ConnectionPool.close(c);
		return n;
	}
	
	 
	    
	public static List<JobLog> getCustomersFl(final int pageIndex,
			final int pageSize, final CustomerFilter filter, final String alias) {
		ISLogger.getLogger().error("getCustomersFl start! ");
		
		final List<JobLog> list = new ArrayList<JobLog>();
		Connection c = null;
		final int v_lowerbound = pageIndex + 1;
		final int v_upperbound = v_lowerbound + pageSize - 1;
		final List<FilterField> flFields = getFilterFields(filter);
		final StringBuffer sql = new StringBuffer();
		sql.append(JobLogService.psql1);
		sql.append(JobLogService.msql);
		sql.append(JobLogService.msql2);
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); ++i) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(JobLogService.psql2);
		ISLogger.getLogger().error("getCustomersFl sql string: " +	 sql.toString());
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			int params;
			for (params = 0; params < flFields.size(); ++params) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
				// ISLogger.getLogger().error("FILTER OBJECT: " +
				// flFields.get(params).getColobject());
			}
			++params;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			CallableStatement inf = null;
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			rs = ps.executeQuery();
			while (rs.next()) {
				final JobLog customer = new JobLog();

				customer.setId(rs.getLong("id"));
				customer.setBranch(rs.getString("branch"));
				customer.setId_client(rs.getString("id_client"));
				customer.setName(rs.getString("name"));

				customer.setCode_country(rs.getString("code_country"));
				customer.setCode_type(rs.getString("code_type"));
				customer.setCode_resident(rs.getString("code_resident"));
				customer.setCode_subject(rs.getString("code_subject"));
				customer.setSign_registr(rs.getInt("sign_registr"));
				customer.setCode_form(rs.getString("code_form"));
				customer.setDate_open(rs.getDate("date_open"));
				customer.setDate_close(rs.getDate("date_close"));
				customer.setState(rs.getInt("state"));
				customer.setP_birthday(rs.getDate("p_birthday"));
				customer.setP_post_address(rs.getString("p_post_address"));
				customer.setP_passport_type(rs.getString("p_passport_type"));
				customer.setP_passport_serial(rs.getString("p_passport_serial"));
				customer.setP_passport_number(rs.getString("p_passport_number"));
				customer.setP_passport_place_registration(rs
						.getString("p_passport_place_registration"));
				customer.setP_passport_date_registration(rs
						.getDate("p_passport_date_registration"));
				customer.setP_code_tax_org(rs.getString("p_code_tax_org"));
				customer.setP_number_tax_registration(rs
						.getString("p_number_tax_registration"));
				customer.setP_code_bank(rs.getString("p_code_bank"));
				customer.setP_code_class_credit(rs
						.getString("p_code_class_credit"));
				customer.setP_code_citizenship(rs
						.getString("p_code_citizenship"));
				customer.setP_birth_place(rs.getString("p_birth_place"));
				customer.setP_code_capacity(rs.getString("p_code_capacity"));
				customer.setP_capacity_status_date(rs
						.getDate("p_capacity_status_date"));
				customer.setP_capacity_status_place(rs
						.getString("p_capacity_status_place"));
				customer.setP_num_certif_capacity(rs
						.getString("p_num_certif_capacity"));
				customer.setP_phone_home(rs.getString("p_phone_home"));
				customer.setP_phone_mobile(rs.getString("p_phone_mobile"));
				customer.setP_email_address(rs.getString("p_email_address"));
				customer.setP_pension_sertif_serial(rs
						.getString("p_pension_sertif_serial"));
				customer.setP_code_gender(rs.getString("p_code_gender"));
				customer.setP_code_nation(rs.getString("p_code_nation"));
				customer.setP_code_birth_region(rs
						.getString("p_code_birth_region"));
				customer.setP_code_birth_distr(rs
						.getString("p_code_birth_distr"));
				customer.setP_type_document(rs.getString("p_type_document"));
				customer.setP_passport_date_expiration(rs
						.getDate("p_passport_date_expiration"));
				customer.setP_code_adr_region(rs.getString("p_code_adr_region"));
				customer.setP_code_adr_distr(rs.getString("p_code_adr_distr"));
				customer.setP_inps(rs.getString("p_inps"));
				customer.setP_family(rs.getString("p_family"));
				customer.setP_first_name(rs.getString("p_first_name"));
				customer.setP_patronymic(rs.getString("p_patronymic"));
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setP_zip_code(rs.getString("p_zip_code"));
				customer.setId_tieto(rs.getString("id_tieto"));
				customer.setT_r_city(rs.getString("t_city"));
				customer.setT_client_b(rs.getString("t_client_b"));
				customer.setT_cl_type(rs.getString("t_cl_type"));
				customer.setSign_record("4");
				if (rs.getString("id_tieto")!=null && !rs.getString("id_tieto").equals(""))
					customer.setSign_record("2");

				list.add(customer);
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(
					"getCustomersFl SQLException =>" + e.getMessage());
			ISLogger.getLogger().error(
					(Object) CheckNull.getPstr((Exception) e));
			e.printStackTrace();
			list.clear();
			JobLog cust = new JobLog();
			cust.setSign_error_record(true);
			cust.setName("Error getting IBS customers. Please contact administrator of system.");
			list.add(cust);
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ex) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ex2) {
			}
			ConnectionPool.close(c);
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception ex3) {
		}
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex4) {
		}
		ConnectionPool.close(c);
		ISLogger.getLogger().error("getCustomersFl list size = "+list.size());
		
		return list;
		
	}
	

	private static String getCond(final List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		}
		return " where ";
	}

    public static List<RefData> getRefData(final String sql, final String branch) {
        final List<RefData> list = new LinkedList<RefData>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            c = ConnectionPool.getConnection(branch);
            s = c.createStatement();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(new RefData(rs.getString("data"), rs.getString("label")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
            return list;
        }
        finally {
            close(rs);
            close(s);
            ConnectionPool.close(c);
        }
        close(rs);
        close(s);
        ConnectionPool.close(c);
        return list;
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
	
	/*инициалицация параметров begin*/
	
	/*инициалицация параметров end*/
	
	/* справочниклар бошланиши, spravochniklar */
	
	public static List<RefData> getFileStates(final String alias) {
		if (listFileStates == null || listFileStates.size() == 0)
			listFileStates = (List<RefData>) 
					getRefData(
							"select s.id data, s.id||' - '||s.name label from empc_file_state s order by 1",
							alias);

		return listFileStates;
	}
  

	/* справочниклар тугаши */

	/* ёрдамчи функциялар бошланиши*/

	
	
	private Calendar dateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/*private java.util.Date calendarToDate(Calendar calendar) {
		return calendar.getTime();
	}*/
	
	
	
	/* ёрдамчи функциялар тугаши*/
	
	public static void close(final CallableStatement cs) {
        try {
            if (cs != null) {
                cs.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
    
    public static void close(final ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (SQLException e) {
            ISLogger.getLogger().error((Object)CheckNull.getPstr((Exception)e));
        }
    }
}