package com.is.bpri;

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
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class LdProductService {

	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from bpr_ld_char ";

	static List<RefData> Status_ = null;
	static List<RefData> State_ = null;
	static List<RefData> Scredit_ = null;
	static List<RefData> SScredit_ = null;
	static List<RefData> Sshifr_ = null;
	static List<RefData> Ssred_ = null;
	static List<RefData> Sklass_ = null;
	static List<RefData> Ssrokkr_ = null;
	static List<RefData> Sklassp_ = null;
	static List<RefData> Sint_ = null;
	static List<RefData> Calcmetod_ = null;
	static List<RefData> SS_type_ans_ = null;
	static List<RefData> Kred_id_cb_ = null;
	static List<RefData> Acceptance_ = null;
	static List<RefData> Cres_ = null;
	static List<RefData> Is_tax_ = null;
	static List<RefData> Use_branch_ = null;
	static List<RefData> Currency_ = null;

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(LdProductFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?",
					filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getLd_num())) {
			flfields.add(new FilterField(getCond(flfields) + "ld_num=?", filter
					.getLd_num()));
		}
		if (!CheckNull.isEmpty(filter.getCrc_num())) {
			flfields.add(new FilterField(getCond(flfields) + "crc_num=?",
					filter.getCrc_num()));
		}
		if (!CheckNull.isEmpty(filter.getShifr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "shifr_id=?",
					filter.getShifr_id()));
		}
		if (!CheckNull.isEmpty(filter.getProd_name())) {
			flfields.add(new FilterField(getCond(flfields) + "prod_name=?",
					filter.getProd_name()));
		}
		if (!CheckNull.isEmpty(filter.getSred_id())) {
			flfields.add(new FilterField(getCond(flfields) + "sred_id=?",
					filter.getSred_id()));
		}
		if (!CheckNull.isEmpty(filter.getTarget())) {
			flfields.add(new FilterField(getCond(flfields) + "target=?", filter
					.getTarget()));
		}
		if (!CheckNull.isEmpty(filter.getCalc_id())) {
			flfields.add(new FilterField(getCond(flfields) + "calc_id=?",
					filter.getCalc_id()));
		}
		if (!CheckNull.isEmpty(filter.getTerm_type())) {
			flfields.add(new FilterField(getCond(flfields) + "term_type=?",
					filter.getTerm_type()));
		}
		if (!CheckNull.isEmpty(filter.getKred_id())) {
			flfields.add(new FilterField(getCond(flfields) + "kred_id=?",
					filter.getKred_id()));
		}
		if (!CheckNull.isEmpty(filter.getKlass_id())) {
			flfields.add(new FilterField(getCond(flfields) + "klass_id=?",
					filter.getKlass_id()));
		}
		if (!CheckNull.isEmpty(filter.getStatus())) {
			flfields.add(new FilterField(getCond(flfields) + "status=?", filter
					.getStatus()));
		}
		if (!CheckNull.isEmpty(filter.getKlassp_id())) {
			flfields.add(new FilterField(getCond(flfields) + "klassp_id=?",
					filter.getKlassp_id()));
		}
		if (!CheckNull.isEmpty(filter.getKred_id_cb())) {
			flfields.add(new FilterField(getCond(flfields) + "kred_id_cb=?",
					filter.getKred_id_cb()));
		}
		if (!CheckNull.isEmpty(filter.getAcceptance())) {
			flfields.add(new FilterField(getCond(flfields) + "acceptance=?",
					filter.getAcceptance()));
		}
		if (!CheckNull.isEmpty(filter.getCres())) {
			flfields.add(new FilterField(getCond(flfields) + "cres=?", filter
					.getCres()));
		}
		if (!CheckNull.isEmpty(filter.getIs_tax())) {
			flfields.add(new FilterField(getCond(flfields) + "is_tax=?", filter
					.getIs_tax()));
		}
		if (!CheckNull.isEmpty(filter.getLd_amount())) {
			flfields.add(new FilterField(getCond(flfields) + "ld_amount=?",
					filter.getLd_amount()));
		}
		if (!CheckNull.isEmpty(filter.getUse_branch())) {
			flfields.add(new FilterField(getCond(flfields) + "use_branch=?",
					filter.getUse_branch()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(LdProductFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_ld_char ");
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
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

	public static List<LdProduct> getLdProductsFl(int pageIndex, int pageSize,
			LdProductFilter filter, String alias) {
		List<LdProduct> list = new ArrayList<LdProduct>();
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
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new LdProduct(rs.getInt("bpr_id"),
							rs.getString("currency")!=null?rs.getString("currency").trim():"",
							rs.getString("ld_num")!=null?rs.getString("ld_num").trim():"", 
							rs.getString("crc_num")!=null?rs.getString("crc_num").trim():"",
							rs.getString("shifr_id")!=null?rs.getString("shifr_id").trim():"",
							rs.getString("prod_name")!=null?rs.getString("prod_name").trim():"",
							rs.getString("sred_id")!=null?rs.getString("sred_id").trim():"",
							rs.getString("target")!=null?rs.getString("target").trim():"",
							rs.getString("calc_id")!=null?rs.getString("calc_id").trim():"",
							rs.getString("term_type")!=null?rs.getString("term_type").trim():"",
							rs.getString("kred_id")!=null?rs.getString("kred_id").trim():"",
							rs.getString("klass_id")!=null?rs.getString("klass_id").trim():"",
							rs.getString("status")!=null?rs.getString("status").trim():"",
							rs.getString("klassp_id")!=null?rs.getString("klassp_id").trim():"",
							rs.getString("kred_id_cb")!=null?rs.getString("kred_id_cb").trim():"",
							rs.getString("acceptance")!=null?rs.getString("acceptance").trim():"",
							rs.getString("cres")!=null?rs.getString("cres").trim():"",
							rs.getString("is_tax")!=null?rs.getString("is_tax").trim():"",
							rs.getString("use_branch")!=null?rs.getString("use_branch").trim():"",
							rs.getString("ld_amount")));
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

	public LdProduct getLdProduct(int ldproductId) {

		LdProduct ldproduct = new LdProduct();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM bpr_ld_char WHERE id=?");
			ps.setInt(1, ldproductId);
			rs = ps.executeQuery();
			if (rs.next()) {
				ldproduct = new LdProduct();
				ldproduct.setId(rs.getInt("id"));
				ldproduct.setCurrency(rs.getString("currency"));
				ldproduct.setLd_num(rs.getString("ld_num"));
				ldproduct.setCrc_num(rs.getString("crc_num"));
				ldproduct.setShifr_id(rs.getString("shifr_id"));
				ldproduct.setProd_name(rs.getString("prod_name"));
				ldproduct.setSred_id(rs.getString("sred_id"));
				ldproduct.setTarget(rs.getString("target"));
				ldproduct.setCalc_id(rs.getString("calc_id"));
				ldproduct.setTerm_type(rs.getString("term_type"));
				ldproduct.setKred_id(rs.getString("kred_id"));
				ldproduct.setKlass_id(rs.getString("klass_id"));
				ldproduct.setStatus(rs.getString("status"));
				ldproduct.setKlassp_id(rs.getString("klassp_id"));
				ldproduct.setKred_id_cb(rs.getString("kred_id_cb"));
				ldproduct.setAcceptance(rs.getString("acceptance"));
				ldproduct.setCres(rs.getString("cres"));
				ldproduct.setIs_tax(rs.getString("is_tax"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return ldproduct;
	}

	public static void create(LdProduct ldproduct, String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO bpr_ld_char (bpr_id, currency, shifr_id, prod_name, sred_id, target, calc_id, term_type, kred_id, klass_id, status, klassp_id, kred_id_cb, acceptance, cres, is_tax, use_branch, ld_amount) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, ldproduct.getId());
			ISLogger.getLogger().error(ldproduct.getId());
			ps.setString(2, ldproduct.getCurrency());
			ISLogger.getLogger().error(ldproduct.getCurrency());
			ps.setString(3, ldproduct.getShifr_id());
			ISLogger.getLogger().error(ldproduct.getShifr_id());
			ps.setString(4, ldproduct.getProd_name());
			ISLogger.getLogger().error(ldproduct.getProd_name());
			ps.setString(5, ldproduct.getSred_id());
			ISLogger.getLogger().error(ldproduct.getSred_id());
			ps.setString(6, ldproduct.getTarget());
			ISLogger.getLogger().error(ldproduct.getTarget());
			ps.setString(7, ldproduct.getCalc_id());
			ISLogger.getLogger().error(ldproduct.getCalc_id());
			ps.setString(8, ldproduct.getTerm_type());
			ISLogger.getLogger().error(ldproduct.getTerm_type());
			ps.setString(9, ldproduct.getKred_id());
			ISLogger.getLogger().error(ldproduct.getKred_id());
			ps.setString(10, ldproduct.getKlass_id());
			ISLogger.getLogger().error(ldproduct.getKlass_id());
			ps.setString(11, ldproduct.getStatus());
			ISLogger.getLogger().error(ldproduct.getStatus());
			ps.setString(12, ldproduct.getKlassp_id());
			ISLogger.getLogger().error(ldproduct.getKlassp_id());
			ps.setString(13, ldproduct.getKred_id_cb());
			ISLogger.getLogger().error(ldproduct.getKred_id_cb());
			ps.setString(14, ldproduct.getAcceptance());
			ISLogger.getLogger().error(ldproduct.getAcceptance());
			ps.setString(15, ldproduct.getCres());
			ISLogger.getLogger().error(ldproduct.getCres());
			ps.setString(16, ldproduct.getIs_tax());
			ISLogger.getLogger().error(ldproduct.getIs_tax());
			ps.setString(17, ldproduct.getUse_branch());
			ISLogger.getLogger().error(ldproduct.getUse_branch());
			ps.setString(18, ldproduct.getLd_amount());
			ISLogger.getLogger().error(ldproduct.getLd_amount());
			ps.executeUpdate();
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

	public static Res update(LdProduct ldproduct,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_char SET currency=?, shifr_id=?, prod_name=?, sred_id=?, target=?, calc_id=?, term_type=?, kred_id=?, klass_id=?, status=?, klassp_id=?, kred_id_cb=?, acceptance=?, cres=?, is_tax=?, use_branch=?, ld_amount=?  WHERE bpr_id=?");
			ps.setString(1, ldproduct.getCurrency());
			ps.setString(2, ldproduct.getShifr_id());
			ps.setString(3, ldproduct.getProd_name());
			ps.setString(4, ldproduct.getSred_id());
			ps.setString(5, ldproduct.getTarget());
			ps.setString(6, ldproduct.getCalc_id());
			ps.setString(7, ldproduct.getTerm_type());
			ps.setString(8, ldproduct.getKred_id());
			ps.setString(9, ldproduct.getKlass_id());
			ps.setString(10, ldproduct.getStatus());
			ps.setString(11, ldproduct.getKlassp_id());
			ps.setString(12, ldproduct.getKred_id_cb());
			ps.setString(13, ldproduct.getAcceptance());
			ps.setString(14, ldproduct.getCres());
			ps.setString(15, ldproduct.getIs_tax());
			ps.setString(16, ldproduct.getUse_branch());
			ps.setString(17, ldproduct.getLd_amount());
			ps.setInt(18, ldproduct.getId());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static List<RefData> getStatus(String branch) // / Статус кредита
	{
		if (Status_ == null) {
			Status_ = Utils
					.getRefData(
							"select id data, id||' - '||name label from ss_ld_status order by data",
							branch);
		}
		return Status_;

	}

	public static List<RefData> getState(String branch) // / Состояние кредита
	{
		if (State_ == null) {
			State_ = Utils
					.getRefData(
							"select id data, id ||' - '||name  label from state_ldform order by data",
							branch);
		}
		return State_;

	}

	public static List<RefData> getScredit(String branch) // виды кредитования
	{
		if (Scredit_ == null) {
			Scredit_ = Utils
					.getRefData(
							"select kred_id data, kred_id ||' - '||kred_name  label from s_credit order by data",
							branch);
		}
		return Scredit_;

	}

	public static List<RefData> getSScredit(String branch) // / Шифр кредита
															// Свой
	{
		if (SScredit_ == null) {
			SScredit_ = Utils
					.getRefData(
							"select kred_id data, kred_id ||' - '||kred_name  label from ss_credit order by data",
							branch);
		}
		return SScredit_;

	}

	public static List<RefData> getSshifr(String branch) // / Шифр целевого
															// назначения
	{
		if (Sshifr_ == null) {
			Sshifr_ = Utils
					.getRefData(
							"select kod_gr || kod_pgr || shifr_id data, kod_gr || kod_pgr || shifr_id || ' - ' || shifr_name label from s_shifr where shifr_id not like '%0' order by data",
							branch);
		}
		return Sshifr_;

	}

	public static List<RefData> getSsred(String branch) // / средство
														// обеспечения
	{
		if (Ssred_ == null) {
			Ssred_ = Utils
					.getRefData(
							"select sred_id  data , sred_id || ' - ' ||sred_name  label from s_sred where sred_id not like '%0'order by data ",
							branch);
		}
		return Ssred_;

	}

	public static List<RefData> getSklass(String branch) // / класc кредита
	{
		if (Sklass_ == null) {
			Sklass_ = Utils
					.getRefData(
							"select klass_id data , klass_name label from s_klass order by data",
							branch);
		}
		return Sklass_;
	}

	public static List<RefData> getSsrokkr(String branch) // /срок кредита
	{
		if (Ssrokkr_ == null) {
			Ssrokkr_ = Utils
					.getRefData(
							"select kod_kr data, kod_kr|| '-' || type_kr label from s_srokkr where act!='Z' order by data",
							branch);
		}
		return Ssrokkr_;
	}

	public static List<RefData> getSklassp(String branch){ // /Класс качества
		if (Sklassp_ == null) {
			Sklassp_ = getRefData("select klassp_id data, klass_name label from s_klassp order by data",branch);
		}
		return Sklassp_;
	}

	public static List<RefData> getSint(String branch){ // /Интервал срочности
		if (Sint_ == null) {
			Sint_ = Utils.getRefData("select kod_int data, name_int label from s_int order by data",branch);
		}
		return Sint_;
	}

	public static List<RefData> getCalcmetod(String branch) // /Метод начисления
															// процентов
	{
		if (Calcmetod_ == null) {
			Calcmetod_ = Utils
					.getRefData(
							"select id data, name label from ss_ld_calc_method order by data",
							branch);
		}
		return Calcmetod_;
	}

	public static List<RefData> getSS_type_ans(String branch) // /Статус
																// наращивания
																// процентов
	{
		if (SS_type_ans_ == null) {
			SS_type_ans_ = Utils
					.getRefData(
							"select code data,name label from ss_type_ans order by data",
							branch);
		}
		return SS_type_ans_;

	}

	public static List<RefData> getKred_id_cb(String branch) // /Шифр
																// кредитования
																// ЦБ
	{
		if (Kred_id_cb_ == null) {
			Kred_id_cb_ = Utils
					.getRefData(
							"select kred_id data, kred_id ||' - '||kred_name label from s_credit  where kred_gr not like '00' order by data",
							branch);
		}
		return Kred_id_cb_;
	}

	public static List<RefData> getAcceptance(String branch) // /Требует акцепт
																// платеж
	{

		if (Acceptance_ == null) {
			Acceptance_ = Utils
					.getRefData(
							"select code data, code ||' - '||name label from ss_type_ans order by data",
							branch);
		}
		return Acceptance_;

	}

	public static List<RefData> getCres(String branch) // /Начисление резерва от
	{
		if (Cres_ == null) {
			Cres_ = Utils
					.getRefData(
							"select id data, id ||' - '||name label from ss_ld_reserv order by data",
							branch);
		}
		return Cres_;
	}

	public static List<RefData> getIs_tax(String branch) // /Фонд льготного
															// кредитования
	{
		if (Is_tax_ == null) {
			Is_tax_ = Utils
					.getRefData(
							"select code data, code ||' - '||name label from ss_type_ans order by data",
							branch);
		}
		return Is_tax_;

	}

	public static List<RefData> getUse_branch(String branch) // ---------- Тип
	// заемшика
	// -------------------
	{
		if (Use_branch_ == null) {
			Use_branch_ = Utils
					.getRefData(
							"select kod_zm||kod_pod data, kod_zm||kod_pod ||' - '|| name_zm label from s_type_zm order by data",
							branch);
		}
		return Use_branch_;

	}

	public static List<RefData> getCurrency(String branch) // ---------- Валюты
	// -------------------
	{
		if (Currency_ == null) {
			Currency_ = Utils
					.getRefData(
							"select kod data, kod ||' - '|| namev label from s_val order by data",
							branch);
		}
		return Currency_;

	}

	// -----------------конец Справочников------------------------

	public static List<RefData> getRefData(String sql, String branch){
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try{
			c = ConnectionPool.getConnection(branch);
			s = c.createStatement();
			rs = s.executeQuery(sql);
			while (rs.next())
				list.add(
						new RefData(rs.getString("data").trim(),
								rs.getString("label").trim()));
		}
		catch (Exception e){
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		}
		finally{
			Utils.close(rs);
			Utils.close(s);
			ConnectionPool.close(c);
		}
		return list;
	}

}
