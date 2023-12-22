package com.is.sd_books.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.ISLogger;
import com.is.sd_books.model.SD_Book;
import com.is.sd_books.model.SD_BookFilter;
import com.is.sd_books.model.Credentials;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class FrmService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";

	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";

	private static String msql = "SELECT * FROM v_sd_books_on_account ";

	public static List<SD_Book> getSdBooksF1(int pageIndex, int pageSize, SD_BookFilter filter, String alias) {

		List<SD_Book> list = new ArrayList<SD_Book>();

		Connection c = null;

		int v_lowerbound = pageIndex + 1;

		int v_upperbound = v_lowerbound + pageSize - 1;

		int params;

		List<FilterField> flFields = getFilterFields(filter);

		StringBuilder sql = new StringBuilder();

		sql.append(psql1);

		sql.append(msql);

		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}

		sql.append("order by b_id desc");
		
		sql.append(psql2);

		
        
		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			PreparedStatement ps = c.prepareStatement(sql.toString());
	//		System.out.println("sql :"+sql);
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new SD_Book(rs.getString("branch"), rs.getLong("id"), rs.getDate("open_date"),
						rs.getString("name"), rs.getDouble("client_id"), rs.getString("client_code"),
						rs.getString("resident_code"), rs.getString("pass_type"), rs.getString("pass_ser"),
						rs.getString("pass_num"), rs.getString("pass_reg"), rs.getDate("pass_date"),
						rs.getDate("born_date"), rs.getString("address"), rs.getDate("ins_date"),
						rs.getDouble("emp_code"), rs.getDouble("out_id"), rs.getString("filial"), rs.getString("dep"),
						rs.getLong("num"), rs.getDouble("b_id"), rs.getString("type_calc"), rs.getDate("date_open"),
						rs.getDate("date_close"), rs.getBigDecimal("saldo"), rs.getLong("state"), rs.getInt("state_id"),
						rs.getString("account"), rs.getString("type_calc_show"), rs.getInt("regime"),
						rs.getString("code_citizenship"), rs.getString("birth_place"), rs.getString("code_adr_region"),
						rs.getString("code_adr_distr"), rs.getString("phone_home"), rs.getString("phone_mobile"),
						rs.getString("dog_num"), rs.getDate("dog_dat"), rs.getString("type_document"),
						rs.getString("name_pas"), rs.getDate("passport_date_expiration"), rs.getDate("deadline"),
						rs.getString("is_need_book"), rs.getString("p_ser"), rs.getString("p_num"),
						rs.getString("p_st_name"), rs.getString("first_code"), rs.getString("last_code")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static SD_Book getSdBook(HashMap<String, Object> param, Credentials cr) {
		SD_Book book = new SD_Book();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(cr.getLogin(), cr.getPassword(), cr.getAlias());
			PreparedStatement ps = c.prepareStatement("SELECT * FROM V_SD_BOOKS_ON_ACCOUNT WHERE B_ID = ?");
			ps.setDouble(1, (Double) param.get("bookid_"));
			ps.setString(2, String.valueOf(param.get("branch_")));
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				book = new SD_Book(rs.getString("branch"), rs.getLong("id"), rs.getDate("open_date"),
						rs.getString("name"), rs.getDouble("client_id"), rs.getString("client_code"),
						rs.getString("resident_code"), rs.getString("pass_type"), rs.getString("pass_ser"),
						rs.getString("pass_num"), rs.getString("pass_reg"), rs.getDate("pass_date"),
						rs.getDate("born_date"), rs.getString("address"), rs.getDate("ins_date"),
						rs.getDouble("emp_code"), rs.getDouble("out_id"), rs.getString("filial"), rs.getString("dep"),
						rs.getLong("num"), rs.getDouble("b_id"), rs.getString("type_calc"), rs.getDate("date_open"),
						rs.getDate("date_close"), rs.getBigDecimal("saldo"), rs.getLong("state"), rs.getInt("state_id"),
						rs.getString("account"), rs.getString("type_calc_show"), rs.getInt("regime"),
						rs.getString("code_citizenship"), rs.getString("birth_place"), rs.getString("code_adr_region"),
						rs.getString("code_adr_distr"), rs.getString("phone_home"), rs.getString("phone_mobile"),
						rs.getString("dog_num"), rs.getDate("dog_dat"), rs.getString("type_document"),
						rs.getString("name_pas"), rs.getDate("passport_date_expiration"), rs.getDate("deadline"),
						rs.getString("is_need_book"), rs.getString("p_ser"), rs.getString("p_num"),
						rs.getString("p_st_name"), rs.getString("first_code"), rs.getString("last_code"));
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return book;
	}

	public static int getCount(SD_BookFilter filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_sd_books_on_account ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
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

	public static List<FilterField> getFilterFields(SD_BookFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getOpen_date())) {
			flfields.add(new FilterField(getCond(flfields) + "open_date=?", filter.getOpen_date()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "name=?", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getClient_id())) {
			flfields.add(new FilterField(getCond(flfields) + "client_id=?", filter.getClient_id()));
		}
		if (!CheckNull.isEmpty(filter.getClient_code())) {
			flfields.add(new FilterField(getCond(flfields) + "client_code=?", filter.getClient_code()));
		}
		if (!CheckNull.isEmpty(filter.getResident_code())) {
			flfields.add(new FilterField(getCond(flfields) + "resident_code=?", filter.getResident_code()));
		}
		if (!CheckNull.isEmpty(filter.getPass_type())) {
			flfields.add(new FilterField(getCond(flfields) + "pass_type=?", filter.getPass_type()));
		}
		if (!CheckNull.isEmpty(filter.getPass_ser())) {
			flfields.add(new FilterField(getCond(flfields) + "pass_ser=?", filter.getPass_ser()));
		}
		if (!CheckNull.isEmpty(filter.getPass_num())) {
			flfields.add(new FilterField(getCond(flfields) + "pass_num=?", filter.getPass_num()));
		}
		if (!CheckNull.isEmpty(filter.getPass_reg())) {
			flfields.add(new FilterField(getCond(flfields) + "pass_reg=?", filter.getPass_reg()));
		}
		if (!CheckNull.isEmpty(filter.getPass_date())) {
			flfields.add(new FilterField(getCond(flfields) + "pass_date=?", filter.getPass_date()));
		}
		if (!CheckNull.isEmpty(filter.getBorn_date())) {
			flfields.add(new FilterField(getCond(flfields) + "born_date=?", filter.getBorn_date()));
		}
		if (!CheckNull.isEmpty(filter.getAddress())) {
			flfields.add(new FilterField(getCond(flfields) + "address=?", filter.getAddress()));
		}
		if (!CheckNull.isEmpty(filter.getIns_date())) {
			flfields.add(new FilterField(getCond(flfields) + "ins_date=?", filter.getIns_date()));
		}
		if (!CheckNull.isEmpty(filter.getEmp_code())) {
			flfields.add(new FilterField(getCond(flfields) + "emp_code=?", filter.getEmp_code()));
		}
		if (!CheckNull.isEmpty(filter.getOut_id())) {
			flfields.add(new FilterField(getCond(flfields) + "out_id=?", filter.getOut_id()));
		}
		if (!CheckNull.isEmpty(filter.getFilial())) {
			flfields.add(new FilterField(getCond(flfields) + "filial=?", filter.getFilial()));
		}
		if (!CheckNull.isEmpty(filter.getDep())) {
			flfields.add(new FilterField(getCond(flfields) + "dep=?", filter.getDep()));
		}
		if (!CheckNull.isEmpty(filter.getNum())) {
			flfields.add(new FilterField(getCond(flfields) + "num=?", filter.getNum()));
		}
		if (!CheckNull.isEmpty(filter.getB_id())) {
			flfields.add(new FilterField(getCond(flfields) + "b_id=?", filter.getB_id()));
		}
		if (!CheckNull.isEmpty(filter.getType_calc())) {
			flfields.add(new FilterField(getCond(flfields) + "type_calc=?", filter.getType_calc()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(new FilterField(getCond(flfields) + "date_open=?", filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(getCond(flfields) + "date_close=?", filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getSaldo())) {
			flfields.add(new FilterField(getCond(flfields) + "saldo=?", filter.getSaldo()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getState_id())) {
			flfields.add(new FilterField(getCond(flfields) + "state_id=?", filter.getState_id()));
		}
		if (!CheckNull.isEmpty(filter.getAccount())) {
			flfields.add(new FilterField(getCond(flfields) + "account=?", filter.getAccount()));
		}
		if (!CheckNull.isEmpty(filter.getType_calc_show())) {
			flfields.add(new FilterField(getCond(flfields) + "type_calc_show=?", filter.getType_calc_show()));
		}
		if (!CheckNull.isEmpty(filter.getRegime())) {
			flfields.add(new FilterField(getCond(flfields) + "regime=?", filter.getRegime()));
		}
		if (!CheckNull.isEmpty(filter.getCode_citizenship())) {
			flfields.add(new FilterField(getCond(flfields) + "code_citizenship=?", filter.getCode_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getBirth_place())) {
			flfields.add(new FilterField(getCond(flfields) + "birth_place=?", filter.getBirth_place()));
		}
		if (!CheckNull.isEmpty(filter.getCode_adr_region())) {
			flfields.add(new FilterField(getCond(flfields) + "code_adr_region=?", filter.getCode_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getCode_adr_distr())) {
			flfields.add(new FilterField(getCond(flfields) + "code_adr_distr=?", filter.getCode_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getPhone_home())) {
			flfields.add(new FilterField(getCond(flfields) + "phone_home=?", filter.getPhone_home()));
		}
		if (!CheckNull.isEmpty(filter.getPhone_mobile())) {
			flfields.add(new FilterField(getCond(flfields) + "phone_mobile=?", filter.getPhone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getDog_num())) {
			flfields.add(new FilterField(getCond(flfields) + "dog_num=?", filter.getDog_num()));
		}
		if (!CheckNull.isEmpty(filter.getDog_dat())) {
			flfields.add(new FilterField(getCond(flfields) + "dog_dat=?", filter.getDog_dat()));
		}
		if (!CheckNull.isEmpty(filter.getType_document())) {
			flfields.add(new FilterField(getCond(flfields) + "type_document=?", filter.getType_document()));
		}
		if (!CheckNull.isEmpty(filter.getName_pas())) {
			flfields.add(new FilterField(getCond(flfields) + "name_pas=?", filter.getName_pas()));
		}
		if (!CheckNull.isEmpty(filter.getPassport_date_expiration())) {
			flfields.add(new FilterField(getCond(flfields) + "passport_date_expiration=?",
					filter.getPassport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getDeadline())) {
			flfields.add(new FilterField(getCond(flfields) + "deadline=?", filter.getDeadline()));
		}
		if (!CheckNull.isEmpty(filter.getIs_need_book())) {
			flfields.add(new FilterField(getCond(flfields) + "is_need_book=?", filter.getIs_need_book()));
		}
		if (!CheckNull.isEmpty(filter.getP_ser())) {
			flfields.add(new FilterField(getCond(flfields) + "p_ser=?", filter.getP_ser()));
		}
		if (!CheckNull.isEmpty(filter.getP_num())) {
			flfields.add(new FilterField(getCond(flfields) + "p_num=?", filter.getP_num()));
		}
		if (!CheckNull.isEmpty(filter.getP_st_name())) {
			flfields.add(new FilterField(getCond(flfields) + "p_st_name=?", filter.getP_st_name()));
		}
		if (!CheckNull.isEmpty(filter.getFirst_code())) {
			flfields.add(new FilterField(getCond(flfields) + "first_code=?", filter.getFirst_code()));
		}
		if (!CheckNull.isEmpty(filter.getLast_code())) {
			flfields.add(new FilterField(getCond(flfields) + "last_code=?", filter.getLast_code()));
		}

		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	public static List<RefData> getOperTurn(String code, String alias) {
		Connection c = null;
		List<RefData> list = new ArrayList<RefData>();
		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			PreparedStatement ps = c
					.prepareStatement("SELECT CODE || ' '|| NAME AS LABEL,CODE DATA FROM V_SD_OPER_TURN WHERE DEP = ?");
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("data"), rs.getString("label")));
			}
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<RefData> getDeps(String alias ) {
		return RefDataService.getRefData("SELECT CODE DATA,NAME LABEL FROM SS_SD", alias);
	}

	// Список Сберкасс
	public static List<RefData> getFilials(String alias,int userId) {
		Connection c = null;
		List<RefData> list = new ArrayList<RefData>();
		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			PreparedStatement ps = c.prepareStatement("SELECT f.code, f.code id, f.name, "
					+ "sd.regime FROM ss_sd_subsidiary sd, ss_subsidiary f, sd_subs_off "
					+ "WHERE f.branch = info.getBranch AND sd.branch = f.branch AND f.code = sd.code "
					+ "AND f.state = 'A' and sd_subs_off.branch = sd.branch and sd_subs_off.CODE_SUBS=sd.code "
					+ "and sd_subs_off.IS_NEW_DEP=1 and sd.code in (select u.code from ss_subsidiary_user u where u.id_user=?) order by 1");
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("CODE"), rs.getString("name")));
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<RefData> getStates(String alias) {
		return RefDataService.getRefData("SELECT CODE DATA, NAME LABEL FROM SS_SD_STATES", alias);
	}

	// Список Индивидуальных параметров
	public static List<RefData> getPrivCondParms1(String alias, String dep) {
		Connection c = null;
		List<RefData> list = new ArrayList<RefData>();
		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			PreparedStatement ps = c.prepareStatement(
					"select ss_depconditions.par AS DATA,ss_deppar.name as LABEL from ss_depconditions "
							+ " ,ss_deppar where ss_depconditions.par=ss_deppar.id and ss_depconditions.isprivate='Y' and ss_depconditions.dep=? ");
			ps.setString(1, dep);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("DATA"), rs.getString("LABEL")));
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		}

		finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	@SuppressWarnings({"unchecked", "null"})
	public static List<RefData> getSdPrCondValues(String alias, String id, int BookId,String name_Dep) {
		System.out.println("parametres : "+alias+"  ,Id_par : "+id+"  , book_id :  "+BookId);
		Connection c = null;
		CallableStatement css = null;
		CallableStatement ccs = null;
		String sql="";
		String res = "";
		StringBuffer sb=new StringBuffer();
		String bId = String.valueOf(BookId);
		List<RefData> values = new ArrayList<RefData>();
		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();
			PreparedStatement ps = c.prepareStatement("select s.description from ss_deppar s where s.id=? ");
			ps.setInt(1, Integer.valueOf(id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("description");
			//	System.out.println("Values_1: "+res.toString());
			//	sb.append('"');
			//	sb.append(" ");
			//	sb.append(res);
			//	sb.append(" ");
			//	sb.append('"');
			//	sql=sb.toString();
			//	System.out.println("sqlsql: "+sql);
			} else {
				res = "";
			}
			
			

			if (res!= null) {
				ccs = c.prepareCall("{ call Param.clearparam() }");
				ccs.execute();
				if(res.contains("SD_BOOK_ID")||res.contains("DEP")){
				css = c.prepareCall("{ call Param.SetParam(?,?) }");;
				setParam("SD_BOOK_ID", bId+"",css);
				System.out.println("SD_BOOK_ID_: "+bId);
				setParam("DEP", name_Dep+"",css);
				System.out.println("DEP_: "+name_Dep);
				css.execute();
				}
				ps = c.prepareStatement(res);
				rs = ps.executeQuery();
		//		System.out.println("Values_2: "+rs.getString(1)+" -----: "+rs.getString(2));
				while (rs.next()) {
					System.out.println("Values_2: "+rs.getString(1)+" -----: "+rs.getString(2));
					values.add(new RefData(rs.getString(1), rs.getString(2)));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));

		} finally {
			ConnectionPool.close(c);
		}
		System.out.println("Values_Znacheniye: "+values.size()+" "+values.toString());
		return values;

	}
	
	public static void setParam(String paramName, String paramValue, CallableStatement cs) throws Exception{
		ISLogger.getLogger().error("PARAM = "+paramName+"\n"+"Value = "+paramValue);
		cs.setString(1, paramName);
		cs.setString(2, paramValue);
		cs.execute();
	}
}
