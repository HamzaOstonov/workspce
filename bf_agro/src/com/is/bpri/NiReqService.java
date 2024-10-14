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
import com.is.utils.RefData;
import com.is.utils.Res;

public class NiReqService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static String msql = "select * from ( select bn.*, c.code from bpr_ni_req bn, bpr_ni_req_n_code c where c.bpr_id = bn.bpr_id) ";
	static List<RefData> TypeZm_ = null;
	static List<RefData> ReqType_ = null;
	static List<RefData> BranchId_ = null;
	static List<RefData> ShifrId_ = null;
	static List<RefData> ResolveOrg_ = null;
	static List<RefData> Currency_ = null;
	static List<RefData> Nwp_ = null;
	static List<RefData> Etype_ = null;
	static List<RefData> IsLetter_ = null;
	static List<RefData> Code_ = null;
	static List<RefData> KredId_ = null;

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(NiReqFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getTypeZm())) {
			flfields.add(new FilterField(getCond(flfields) + "type_zm=?",
					filter.getTypeZm()));
		}
		if (!CheckNull.isEmpty(filter.getReqType())) {
			flfields.add(new FilterField(getCond(flfields) + "reqtype=?",
					filter.getReqType()));
		}
		if (!CheckNull.isEmpty(filter.getBranchId())) {
			flfields.add(new FilterField(getCond(flfields) + "branch_id=?",
					filter.getBranchId()));
		}
		if (!CheckNull.isEmpty(filter.getShifrId())) {
			flfields.add(new FilterField(getCond(flfields) + "shifr_id=?",
					filter.getShifrId()));
		}
		if (!CheckNull.isEmpty(filter.getKredId())) {
			flfields.add(new FilterField(getCond(flfields) + "kred_id=?",
					filter.getKredId()));
		}
		if (!CheckNull.isEmpty(filter.getResolveOrg())) {
			flfields.add(new FilterField(getCond(flfields) + "resolve_org=?",
					filter.getResolveOrg()));
		}
		if (!CheckNull.isEmpty(filter.getCurrency())) {
			flfields.add(new FilterField(getCond(flfields) + "currency=?",
					filter.getCurrency()));
		}
		if (!CheckNull.isEmpty(filter.getNwp())) {
			flfields.add(new FilterField(getCond(flfields) + "nwp=?", filter
					.getNwp()));
		}
		if (!CheckNull.isEmpty(filter.getQw())) {
			flfields.add(new FilterField(getCond(flfields) + "qw=?", filter
					.getQw()));
		}
		if (!CheckNull.isEmpty(filter.getEtype())) {
			flfields.add(new FilterField(getCond(flfields) + "etype=?", filter
					.getEtype()));
		}
		if (!CheckNull.isEmpty(filter.getIsLetter())) {
			flfields.add(new FilterField(getCond(flfields) + "is_letter=?",
					filter.getIsLetter()));
		}
		if (!CheckNull.isEmpty(filter.getBpr_id())) {
			flfields.add(new FilterField(getCond(flfields) + "bpr_id=?", filter
					.getBpr_id()));
		}
		if (!CheckNull.isEmpty(filter.getCode())) {
			flfields.add(new FilterField(getCond(flfields) + "code=?", filter
					.getCode()));
		}
		if (!CheckNull.isEmpty(filter.getBprId())) {
			flfields.add(new FilterField(getCond(flfields) + "bprId=?", filter
					.getBprId()));
		}
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));
		return flfields;
	}

	public static int getCount(NiReqFilter filter, String alias) {
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM bpr_ni_req");
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

	public static List<NiReq> getNiReqsFl(int pageIndex, int pageSize,
			NiReqFilter filter, String alias) {
		List<NiReq> list = new ArrayList<NiReq>();
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
				list.add(new NiReq(rs.getInt("bpr_id"),
						rs.getString("type_zm"),
						rs.getString("reqtype"),
						rs.getString("branch_id"),
						rs.getString("shifr_id"),
						rs.getString("kred_id"),
						rs.getString("resolve_org"),
						rs.getString("currency"),
						rs.getString("nwp"),
						rs.getString("qw"),
						rs.getString("etype"),
						rs.getString("is_letter"),
						rs.getString("code")));
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

	public NiReq getNiReq(int nireqId) {
		NiReq nireq = new NiReq();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("SELECT * FROM bpr_ni_req WHERE id=? ");
			ps.setInt(1, nireqId);
			rs = ps.executeQuery();
			if (rs.next()) {
				nireq = new NiReq();
				nireq.setBpr_id(rs.getInt("bpr_id"));
				nireq.setType_zm(rs.getString("type_zm"));
				nireq.setReq_type(rs.getString("reqtype"));
				nireq.setBranch_id(rs.getString("branch_id"));
				nireq.setShifr_id(rs.getString("shifr_id"));
				nireq.setKred_id(rs.getString("kred_id"));
				nireq.setResolve_org(rs.getString("resolve_org"));
				nireq.setCurrency(rs.getString("currency"));
				nireq.setNwp(rs.getString("nwp"));
				nireq.setQw(rs.getString("qw"));
				nireq.setEtype(rs.getString("etype"));
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return nireq;
	}

	public static void create(NiReq nireq,LdProduct ldproduct ,String alias,Res res) {
		ISLogger.getLogger().error("Вошли в метод Create");
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			ISLogger.getLogger().error("Вошли в блок трай");
			c = ConnectionPool.getConnection(alias);
			ISLogger.getLogger().error("Установили соеденение схема = "+alias);
			ps = c.prepareStatement(" INSERT INTO bpr_ni_req (bpr_id, type_zm, reqtype, branch_id, shifr_id, kred_id, resolve_org, currency, nwp, qw, etype, is_letter) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,trim(?)) ");
			ps1 = c.prepareStatement("INSERT INTO bpr_ni_req_n_code(bpr_id, code) VALUES(?,?) ");
			ISLogger.getLogger().error("Создали инсерты");
			ps.setInt(1, nireq.getBpr_id());
			ps.setString(2, nireq.getType_zm());
			ps.setString(3, nireq.getReq_type());
			ps.setString(4, nireq.getBranch_id());
			ps.setString(5, nireq.getShifr_id());
			ps.setString(6, nireq.getKred_id());
			ps.setString(7, nireq.getResolve_org());
			ps.setString(8, nireq.getCurrency());
			ps.setString(9, nireq.getNwp());
			ps.setString(10, nireq.getQw());
			ps.setString(11, nireq.getEtype());
			ps.setString(12, nireq.getIsLetter());
			ps1.setInt(1, nireq.getBpr_id());
			ISLogger.getLogger().error(nireq.getBpr_id()+" NIREQ BPRID");
			ps1.setString(2, nireq.getCode());
			ISLogger.getLogger().error("Заполнили инсерты");
			ps.executeUpdate();
			ps1.executeUpdate();
			ISLogger.getLogger().error("Выполнили инсерты");
			ps = c.prepareStatement("INSERT INTO bpr_ld_char (bpr_id, currency, shifr_id, prod_name, sred_id, target, calc_id, term_type, kred_id, klass_id, status, klassp_id, kred_id_cb, acceptance, cres, is_tax, use_branch) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ISLogger.getLogger().error("Создали ещё 1 инсерт");
			ps.setInt(1, ldproduct.getId());
			ps.setString(2, ldproduct.getCurrency());
			ps.setString(3, ldproduct.getShifr_id());
			ps.setString(4, ldproduct.getProd_name());
			ps.setString(5, ldproduct.getSred_id());
			ps.setString(6, ldproduct.getTarget());
			ps.setString(7, ldproduct.getCalc_id());
			ps.setString(8, ldproduct.getTerm_type());
			ps.setString(9, ldproduct.getKred_id());
			ps.setString(10, ldproduct.getKlass_id());
			ps.setString(11, ldproduct.getStatus());
			ps.setString(12, ldproduct.getKlassp_id());
			ps.setString(13, ldproduct.getKred_id_cb());
			ps.setString(14, ldproduct.getAcceptance());
			ps.setString(15, ldproduct.getCres());
			ps.setString(16, ldproduct.getIs_tax());
			ps.setString(17, ldproduct.getUse_branch());
			ISLogger.getLogger().error("Заполнили инсерт");
			ps.executeUpdate();
			ISLogger.getLogger().error("Выполнили инсерт");
			c.commit();
			ISLogger.getLogger().error("Закоммитили");
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ISLogger.getLogger().error("Ошибка");
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			Utils.close(ps1);
			ISLogger.getLogger().error("Закрыли соеденение");
			ConnectionPool.close(c);
		}
	}

	public static void update(NiReq nireq, String alias,Res res) {
		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement(" UPDATE bpr_ni_req SET  type_zm=?, reqtype=?, branch_id=?, shifr_id=?, kred_id=?, resolve_org=?, currency=?, nwp=?, qw=?, etype=?, is_letter=trim(?)  WHERE bpr_id=? ");
			ps1 = c.prepareStatement(" UPDATE bpr_ni_req_n_code SET  code=? WHERE bpr_id=? ");
			ps.setString(1, nireq.getType_zm());
			ps.setString(2, nireq.getReq_type());
			ps.setString(3, nireq.getBranch_id());
			ps.setString(4, nireq.getShifr_id());
			ps.setString(5, nireq.getKred_id());
			ps.setString(6, nireq.getResolve_org());
			ps.setString(7, nireq.getCurrency());
			ps.setString(8, nireq.getNwp());
			ps.setString(9, nireq.getQw());
			ps.setString(10, nireq.getEtype());
			ps.setString(11, nireq.getIsLetter().trim());
			ps.setInt(12, nireq.getBpr_id());
			ps1.setString(1, nireq.getCode());
			ps1.setInt(2, nireq.getBpr_id());
			ps.executeUpdate();
			ps1.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			Utils.close(ps1);
			ConnectionPool.close(c);
		}
	}

	public static Res remove(NiReq nireq, String alias) {
		Connection c = null;
		Res res = new Res();
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("DELETE FROM bpr_ni_req WHERE bpr_id=?");
			ps.setString(1, nireq.getBranch_id());
			ps.executeUpdate();
			c.commit();
			res.setCode(1);
			res.setName("Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(0);
			res.setName(e.getLocalizedMessage());
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	// -----------------------Справочники---------------------

	public static List<RefData> getTypeZm(String branch,String temp){ // ---------- Тип заемшика
		if(!temp.equals("")) temp = " and "+temp;
		ISLogger.getLogger().error("select kod_zm||kod_pod data, kod_zm||kod_pod ||' - '|| name_zm label from s_type_zm where act='A' "+temp+" order by data");
		ISLogger.getLogger().error("UPDATED");
		return Utils.getRefData("select kod_zm||kod_pod data, kod_zm||kod_pod ||' - '|| name_zm label from s_type_zm where act='A' "+temp+" order by data",branch);
	}

	public static List<RefData> getReqType(String branch){ // ---------- Признак
		if (ReqType_ == null) {
			ReqType_ = Utils.getRefData("select id data, id ||' - '|| name label from ss_ni_type order by data",branch);
		}
		return ReqType_;
	}

	public static List<RefData> getBranchId(String branch){ // ---------- Код отрасли заявки
		if (BranchId_ == null) {
			BranchId_ = Utils.getRefData("select code data, code||'-'||name_ru label from s_spr_oked where active='A' order by data",branch);
		}
		return BranchId_;
	}

	public static List<RefData> getShifrId(String branch){ // ---------- Цель кредита
		if (ShifrId_ == null) {
			ShifrId_ = Utils.getRefData("select kod_gr||kod_pgr||shifr_id data, kod_gr||kod_pgr||shifr_id ||' - '|| shifr_name label from s_shifr where shifr_id not like '%0' and act='A' order by data",branch);
		}
		return ShifrId_;
	}

	public static List<RefData> getResolveOrg(String branch){ // ---------- Орган банка рассмотревший кредитную заявку
		if (ResolveOrg_ == null) {
			ResolveOrg_ = Utils.getRefData("select id data, id ||' - '|| name label from ss_ld_resolve_org order by data",branch);
		}
		return ResolveOrg_;
	}

	public static List<RefData> getCurrency(String branch){ // ---------- Валюты
		if (Currency_ == null) {
			Currency_ = Utils.getRefData("select kod data, kod ||' - '|| namev label from s_val where act='A' order by data",branch);
		}
		return Currency_;
	}

	public static List<RefData> getNwp(String branch){ // ---------- Признак новообразования нового объекта
		if (Nwp_ == null) {
			Nwp_ = Utils.getRefData("select id data, id ||' - '|| name label from ss_workplace order by data",branch);
		}
		return Nwp_;
	}

	public static List<RefData> getEtype(String branch){ // Классификатор предприятия
		if (Etype_ == null) {
			Etype_ = Utils.getRefData("select id data, id ||' - '|| name label from ss_enterp_type order by data",branch);
		}
		return Etype_;
	}

	public static List<RefData> getIsLetter(String branch){ 	// Согласие на отправку 10 запроса НИКИ
		if (IsLetter_ == null) {
			IsLetter_ = Utils.getRefData("select id data, id||'-'||name label from ss_ld_yes_no order by data ",branch);
		}
		return IsLetter_;
	}

	public static List<RefData> getCode(String branch){	// Код государственного нормативного акта
		if (Code_ == null) {
			Code_ = Utils.getRefData("select n_code data, n_code ||' - '|| n_name label from s_codeact where act='A' order by data",branch);
		}
		return Code_;
	}

	public static List<RefData> getKredId(String branch){ // Вид кредита
		if (KredId_ == null) {
			KredId_ = Utils.getRefData("select kred_id data, kred_id ||' - '|| kred_name label from s_credit where kred_gr not like '00' and act='A' order by data",branch);
		}
		return KredId_;
	}

	// -------------------------------- Конец Справочники
}
