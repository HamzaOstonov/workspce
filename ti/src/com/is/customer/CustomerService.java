package com.is.customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.python.antlr.PythonParser.break_stmt_return;
import org.zkoss.zkplus.databind.BindingListModelList;

import com.is.ConnectionPool;
import com.is.LtLogger;
import com.is.tieto.AccInfo;
import com.is.tieto.Tclient;
import com.is.tieto.TclientService;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.Res;

public class CustomerService {
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static String msql = "SELECT * FROM ";
	private static String msql2 = "v_bfcustomer";

	public static class link {
		public int id;
		public String Branch;
		public String Tieto_id;
		public String Head_id;
		public String Branch_id;
		public String Cur_acc;

		public link(int Id, String branch, String tieto_id, String head_id,
				String branch_id, String cur_acc) {
			super();
			id = Id;
			Branch = branch;
			Tieto_id = tieto_id;
			Head_id = head_id;
			Branch_id = branch_id;
			Cur_acc = cur_acc;
		}
	}

	public static link get_link_tieto(String Tieto_id, String Branch,
			String alias) {
		link res = null;
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;

		try {
			c = ConnectionPool.getConnection(alias);
			// Statement s = c.createStatement();
			// ResultSet rs = s.executeQuery("SELECT * FROM v_client");
			ps = c
					.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id, t.head_customer_id head_id, t.cur_acc cur_acc from bf_tieto_customers t where t.tieto_customer_id=? and t.branch=?");

			ps.setString(1, Tieto_id);
			ps.setString(2, Branch);

			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"),
						rs.getString("tieto_id"), rs.getString("head_id"),
						rs.getString("bank_id"), rs.getString("cur_acc"));
			}

		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res getTieto_branch(String branch, int userId, String alias) {
		Res res = new Res();
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select BF.GET_TIETO_BRANCH(?,?) res from dual");

			ps.setString(1, branch);
			ps.setInt(2, userId);

			rs = ps.executeQuery();
			while (rs.next()) {
				res.setCode(0);
				res.setName(rs.getString("res"));
			}

		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res.setCode(-1);
			res.setName(e.getMessage());
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);

			ConnectionPool.close(c);
		}
		return res;
	}

	public static link get_link_ho(String Client_id, String Branch, String alias) {
		link res = null;
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;

		try {
			c = ConnectionPool.getConnection(alias);
			// Statement s = c.createStatement();
			// ResultSet rs = s.executeQuery("SELECT * FROM v_client");
			ps = c
					.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id, t.head_customer_id head_id, t.cur_acc cur_acc from bf_tieto_customers t where t.head_customer_id=? and t.branch=?");

			ps.setString(1, Client_id);
			ps.setString(2, Branch);

			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"),
						rs.getString("tieto_id"), rs.getString("head_id"),
						rs.getString("bank_id"), rs.getString("cur_acc"));
			}

		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);

			ConnectionPool.close(c);
		}
		return res;
	}

	public static link get_link_branch(String Client_id, String Branch,
			String alias) {
		link res = null;
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;
		try {
			c = ConnectionPool.getConnection(alias);
			// Statement s = c.createStatement();
			// ResultSet rs = s.executeQuery("SELECT * FROM v_client");
			ps = c
					.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id, t.head_customer_id head_id, t.cur_acc cur_acc from bf_tieto_customers t where t.bank_customer_id=? and t.branch=?");

			ps.setString(1, Client_id);
			ps.setString(2, Branch);

			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"),
						rs.getString("tieto_id"), rs.getString("head_id"),
						rs.getString("bank_id"), rs.getString("cur_acc"));
			}

		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);

			ConnectionPool.close(c);
		}
		return res;
	}

	public static link get_link_tieto(String Tieto_id, String alias) {
		link res = null;
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;

		try {
			c = ConnectionPool.getConnection(alias);
			// Statement s = c.createStatement();
			// ResultSet rs = s.executeQuery("SELECT * FROM v_client");
			ps = c
					.prepareStatement("select t.id id, t.branch branch, t.bank_customer_id bank_id, t.tieto_customer_id tieto_id, t.head_customer_id head_id, t.cur_acc cur_acc from bf_tieto_customers t where t.tieto_customer_id=?");

			ps.setString(1, Tieto_id);

			rs = ps.executeQuery();
			while (rs.next()) {
				res = new link(rs.getInt("id"), rs.getString("branch"),
						rs.getString("tieto_id"), rs.getString("head_id"),
						rs.getString("bank_id"), rs.getString("cur_acc"));
			}

		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);

			ConnectionPool.close(c);
		}
		return res;
	}

	public static String get_alias_ho(String alias) {
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;

		String res = "";

		try {
			c = ConnectionPool.getConnection(alias);
			// Statement s = c.createStatement();
			// ResultSet rs = s.executeQuery("SELECT * FROM v_client");
			ps = c.prepareStatement("select user_name from ss_dblink_branch t where t.branch = (select bs.VALUE from bf_sets bs where bs.id = 'HO')");

			rs = ps.executeQuery();
			rs.next();
			res = rs.getString("user_name");

		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);
			ConnectionPool.close(c);
		}
		return res;
	}

	public static List<Customer> getCustomer(String pn, String alias) {

		List<Customer> list = new ArrayList<Customer>();
		Connection c = null;
		ResultSet rs =null;
		PreparedStatement ps =null;

		try {
			c = ConnectionPool.getConnection(alias);
			// Statement s = c.createStatement();
			// ResultSet rs = s.executeQuery("SELECT * FROM v_client");
			ps = c.prepareStatement("SELECT * FROM v_bfcustomer where p_passport_number=?");
			ps.setString(1, pn);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Customer(rs.getLong("id"), rs.getString("branch"),
						rs.getString("id_client"), rs.getString("name"), rs
								.getString("code_country"), rs
								.getString("code_type"), rs
								.getString("code_resident"), rs
								.getString("code_subject"), rs
								.getInt("sign_registr"), rs
								.getString("code_form"), rs
								.getDate("date_open"),
						rs.getDate("date_close"), rs.getInt("state"), rs
								.getDate("p_birthday"), rs
								.getString("p_post_address"), rs
								.getString("p_passport_type"), rs
								.getString("p_passport_serial"), rs
								.getString("p_passport_number"), rs
								.getString("p_passport_place_registration"), rs
								.getDate("p_passport_date_registration"), rs
								.getString("p_code_tax_org"), rs
								.getString("p_number_tax_registration"), rs
								.getString("p_code_bank"), rs
								.getString("p_code_class_credit"), rs
								.getString("p_code_citizenship"), rs
								.getString("p_birth_place"), rs
								.getString("p_code_capacity"), rs
								.getDate("p_capacity_status_date"), rs
								.getString("p_capacity_status_place"), rs
								.getString("p_num_certif_capacity"), rs
								.getString("p_phone_home"), rs
								.getString("p_phone_mobile"), rs
								.getString("p_email_address"), rs
								.getString("p_pension_sertif_serial"), rs
								.getString("p_code_gender"), rs
								.getString("p_code_nation"), rs
								.getString("p_code_birth_region"), rs
								.getString("p_code_birth_distr"), rs
								.getString("p_type_document"), rs
								.getDate("p_passport_date_expiration"), rs
								.getString("p_code_adr_region"), rs
								.getString("p_code_adr_distr"), rs
								.getString("p_inps"), rs.getString("p_pinfl"),
						rs.getString("p_family"), rs.getString("p_first_name"),
						rs.getString("p_patronymic")));
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {

			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);
			ConnectionPool.close(c);
		}
		return list;

	}

	public static Res doAction(String un, String pw, Customer customer,
			int actionid, int utv_actionid, String alias, Boolean selfBranch) {
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		String halias = CustomerService.get_alias_ho(alias);
		CallableStatement inf = null;
		CallableStatement getp = null;
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		ResultSet rs =null;
		PreparedStatement ps =null;


		try {

			if ((halias.compareTo(alias) == 0) && (!selfBranch)) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();

			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			getp = c.prepareCall("{? = call Param.GetParam(?) }");
			getp.registerOutParameter(1, java.sql.Types.VARCHAR);

			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.VARCHAR);

			if (customer.getId() != 0) {
				cs.setString(1, "ID");
				cs.setLong(2, customer.getId());
				cs.execute();
			}
			cs.setString(1, "BRANCH");
			cs.setString(2, customer.getBranch());
			cs.execute();
			if (customer.getId() != 0) {
				cs.setString(1, "ID_CLIENT");
				cs.setString(2, customer.getId_client());
				cs.execute();
			}
			cs.setString(1, "NAME");
			cs.setString(2, customer.getName().toUpperCase());
			cs.execute();
			cs.setString(1, "CODE_COUNTRY");
			cs.setString(2, customer.getCode_country());
			cs.execute();
			cs.setString(1, "CODE_TYPE");
			cs.setString(2, customer.getCode_type());
			cs.execute();
			cs.setString(1, "CODE_RESIDENT");
			cs.setString(2, customer.getCode_resident());
			cs.execute();
			cs.setString(1, "CODE_SUBJECT");
			cs.setString(2, customer.getCode_subject());
			cs.execute();
			cs.setString(1, "SIGN_REGISTR");
			cs.setString(2, customer.getSign_registr() + "");
			cs.execute();
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			cs.setString(1, "DATE_OPEN");
			cs.setString(
					2,
					customer.getDate_open() != null ? bdf.format(customer
							.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(
					2,
					customer.getDate_close() != null ? bdf.format(customer
							.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, customer.getState() + "");
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(
					2,
					customer.getP_birthday() != null ? bdf.format(customer
							.getP_birthday()) : null);
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS");
			cs.setString(2, customer.getP_post_address());
			cs.execute();
			cs.setString(1, "P_PASSPORT_TYPE");
			cs.setString(2, customer.getP_passport_type());
			cs.execute();
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, customer.getP_passport_serial());
			cs.execute();
			cs.setString(1, "P_PASSPORT_NUMBER");
			cs.setString(2, customer.getP_passport_number());
			cs.execute();
			cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
			cs.setString(2, customer.getP_passport_place_registration());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
			cs.setString(
					2,
					customer.getP_passport_date_registration() != null ? bdf
							.format(customer.getP_passport_date_registration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_TAX_ORG");
			cs.setString(2, customer.getP_code_tax_org());
			cs.execute();
			cs.setString(1, "P_NUMBER_TAX_REGISTRATION");
			cs.setString(2, customer.getP_number_tax_registration());
			cs.execute();
			cs.setString(1, "P_CODE_BANK");
			cs.setString(2, customer.getP_code_bank());
			cs.execute();
			cs.setString(1, "P_CODE_CLASS_CREDIT");
			cs.setString(2, customer.getP_code_class_credit());
			cs.execute();
			cs.setString(1, "P_CODE_CITIZENSHIP");
			cs.setString(2, customer.getP_code_citizenship());
			cs.execute();
			cs.setString(1, "P_BIRTH_PLACE");
			cs.setString(2, customer.getP_birth_place());
			cs.execute();
			cs.setString(1, "P_CODE_CAPACITY");
			cs.setString(2, customer.getP_code_capacity());
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_DATE");
			cs.setString(
					2,
					customer.getP_capacity_status_date() != null ? bdf
							.format(customer.getP_capacity_status_date())
							: null);
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_PLACE");
			cs.setString(2, customer.getP_capacity_status_place());
			cs.execute();
			cs.setString(1, "P_NUM_CERTIF_CAPACITY");
			cs.setString(2, customer.getP_num_certif_capacity());
			cs.execute();
			cs.setString(1, "P_PHONE_HOME");
			cs.setString(2, customer.getP_phone_home());
			cs.execute();
			cs.setString(1, "P_PHONE_MOBILE");
			cs.setString(2, customer.getP_phone_mobile());
			cs.execute();
			cs.setString(1, "P_EMAIL_ADDRESS");
			cs.setString(2, customer.getP_email_address());
			cs.execute();
			cs.setString(1, "P_PENSION_SERTIF_SERIAL");
			cs.setString(2, customer.getP_pension_sertif_serial());
			cs.execute();
			cs.setString(1, "P_CODE_GENDER");
			cs.setString(2, customer.getP_code_gender());
			cs.execute();
			cs.setString(1, "P_CODE_NATION");
			cs.setString(2, customer.getP_code_nation());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_REGION");
			cs.setString(2, customer.getP_code_birth_region());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_DISTR");
			cs.setString(2, customer.getP_code_birth_distr());
			cs.execute();
			cs.setString(1, "P_TYPE_DOCUMENT");
			cs.setString(2, customer.getP_type_document());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_EXPIRATION");
			cs.setString(
					2,
					customer.getP_passport_date_expiration() != null ? bdf
							.format(customer.getP_passport_date_expiration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_ADR_REGION");
			cs.setString(2, customer.getP_code_adr_region());
			cs.execute();
			cs.setString(1, "P_CODE_ADR_DISTR");
			cs.setString(2, customer.getP_code_adr_distr());
			cs.execute();
			cs.setString(1, "P_INPS");
			cs.setString(2, customer.getP_inps());
			cs.execute();
			cs.setString(1, "P_PINFL");
			cs.setString(2, customer.getP_pinfl());
			cs.execute();
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family().toUpperCase());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name().toUpperCase());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic().toUpperCase());
			cs.execute();

			// cs.setString(1, "P_FAMILY"); cs.setString(2,"aaa"); cs.execute();
			// cs.setString(1, "P_FIRST_NAME"); cs.setString(2,"bbb");
			// cs.execute();
			// cs.setString(1, "P_PATRONYMIC"); cs.setString(2,"ccc");
			// cs.execute();

			// getp = c.prepareCall("{? = call Param.GetParam(?) }");
			// getp.setString(2, "P_FAMILY");
			// getp.execute();
			// res = new Res(0,getp.getString(1));

			acs.setInt(1, 1);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();

			ccs.execute();
			String id = ccs.getString(1);
			
			if (utv_actionid != 0) {
				// ccs.execute();
				// String id = ccs.getString(1);

				ps = c
						.prepareStatement("select t.id_client from v_bfcustomer t where t.id = ?");
				ps.setString(1, id);
				rs = ps.executeQuery();

				rs.next();
				String cl_id = rs.getString("id_client");

				/*
				 * cs.setString(1, "ID"); cs.setString(2, cl_id); cs.execute();
				 */

				cs.setString(1, "ID_CLIENT");
				cs.setString(2, cl_id);
				cs.execute();

				acs.setInt(3, utv_actionid);
				acs.execute();
			}

			c.commit();
			// c.rollback();
			ccs.execute();
			res = new Res(0, id);

			// customer.setId(Long.getLong(ccs.getString(1)));

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
		} finally {
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);
			CustomerUtils.closeCStatement(acs);
			CustomerUtils.closeCStatement(ccs);
			CustomerUtils.closeCStatement(cs);
			CustomerUtils.closeCStatement(getp);
			CustomerUtils.closeCStatement(inf);

			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res doAction_utv(String un, String pw, Customer customer,
			int actionid, String alias, Boolean selfBranch) {
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		String halias = CustomerService.get_alias_ho(alias);
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		CallableStatement inf = null;
		CallableStatement getp = null;
		
		try {
			if ((halias.compareTo(alias) == 0) && (!selfBranch)) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			
			inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			
			//client_desc
			updateCustomerDescription(c, customer);
						
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			getp = c.prepareCall("{? = call Param.GetParam(?) }");
			getp.registerOutParameter(1, java.sql.Types.VARCHAR);
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.VARCHAR);

			cs.setString(1, "ID");
			cs.setLong(2, customer.getId());
			cs.execute();
			cs.setString(1, "BRANCH");
			cs.setString(2, customer.getBranch());
			cs.execute();
			cs.setString(1, "ID_CLIENT");
			cs.setString(2, customer.getId_client());
			cs.execute();
			cs.setString(1, "NAME");
			cs.setString(2, customer.getName());
			cs.execute();
			cs.setString(1, "CODE_COUNTRY");
			cs.setString(2, customer.getCode_country());
			cs.execute();
			cs.setString(1, "CODE_TYPE");
			cs.setString(2, customer.getCode_type());
			cs.execute();
			cs.setString(1, "CODE_RESIDENT");
			cs.setString(2, customer.getCode_resident());
			cs.execute();
			cs.setString(1, "CODE_SUBJECT");
			cs.setString(2, customer.getCode_subject());
			cs.execute();
			cs.setString(1, "SIGN_REGISTR");
			cs.setString(2, customer.getSign_registr() + "");
			cs.execute();
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			cs.setString(1, "DATE_OPEN");
			cs.setString(
					2,
					customer.getDate_open() != null ? bdf.format(customer
							.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(
					2,
					customer.getDate_close() != null ? bdf.format(customer
							.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, customer.getState() + "");
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(
					2,
					customer.getP_birthday() != null ? bdf.format(customer
							.getP_birthday()) : null);
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS");
			cs.setString(2, customer.getP_post_address());
			cs.execute();
			cs.setString(1, "P_PASSPORT_TYPE");
			cs.setString(2, customer.getP_passport_type());
			cs.execute();
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, customer.getP_passport_serial());
			cs.execute();
			cs.setString(1, "P_PASSPORT_NUMBER");
			cs.setString(2, customer.getP_passport_number());
			cs.execute();
			cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
			cs.setString(2, customer.getP_passport_place_registration());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
			cs.setString(
					2,
					customer.getP_passport_date_registration() != null ? bdf
							.format(customer.getP_passport_date_registration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_TAX_ORG");
			cs.setString(2, customer.getP_code_tax_org());
			cs.execute();
			cs.setString(1, "P_NUMBER_TAX_REGISTRATION");
			cs.setString(2, customer.getP_number_tax_registration());
			cs.execute();
			cs.setString(1, "P_CODE_BANK");
			cs.setString(2, customer.getP_code_bank());
			cs.execute();
			cs.setString(1, "P_CODE_CLASS_CREDIT");
			cs.setString(2, customer.getP_code_class_credit());
			cs.execute();
			cs.setString(1, "P_CODE_CITIZENSHIP");
			cs.setString(2, customer.getP_code_citizenship());
			cs.execute();
			cs.setString(1, "P_BIRTH_PLACE");
			cs.setString(2, customer.getP_birth_place());
			cs.execute();
			cs.setString(1, "P_CODE_CAPACITY");
			cs.setString(2, customer.getP_code_capacity());
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_DATE");
			cs.setString(
					2,
					customer.getP_capacity_status_date() != null ? bdf
							.format(customer.getP_capacity_status_date())
							: null);
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_PLACE");
			cs.setString(2, customer.getP_capacity_status_place());
			cs.execute();
			cs.setString(1, "P_NUM_CERTIF_CAPACITY");
			cs.setString(2, customer.getP_num_certif_capacity());
			cs.execute();
			cs.setString(1, "P_PHONE_HOME");
			cs.setString(2, customer.getP_phone_home());
			cs.execute();
			cs.setString(1, "P_PHONE_MOBILE");
			cs.setString(2, customer.getP_phone_mobile());
			cs.execute();
			cs.setString(1, "P_EMAIL_ADDRESS");
			cs.setString(2, customer.getP_email_address());
			cs.execute();
			cs.setString(1, "P_PENSION_SERTIF_SERIAL");
			cs.setString(2, customer.getP_pension_sertif_serial());
			cs.execute();
			cs.setString(1, "P_CODE_GENDER");
			cs.setString(2, customer.getP_code_gender());
			cs.execute();
			cs.setString(1, "P_CODE_NATION");
			cs.setString(2, customer.getP_code_nation());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_REGION");
			cs.setString(2, customer.getP_code_birth_region());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_DISTR");
			cs.setString(2, customer.getP_code_birth_distr());
			cs.execute();
			cs.setString(1, "P_TYPE_DOCUMENT");
			cs.setString(2, customer.getP_type_document());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_EXPIRATION");
			cs.setString(
					2,
					customer.getP_passport_date_expiration() != null ? bdf
							.format(customer.getP_passport_date_expiration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_ADR_REGION");
			cs.setString(2, customer.getP_code_adr_region());
			cs.execute();
			cs.setString(1, "P_CODE_ADR_DISTR");
			cs.setString(2, customer.getP_code_adr_distr());
			cs.execute();
			cs.setString(1, "P_INPS");
			cs.setString(2, customer.getP_inps());
			cs.execute();
			cs.setString(1, "P_PINFL");
			cs.setString(2, customer.getP_pinfl());
			cs.execute();
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic());
			cs.execute();

			acs.setInt(1, 1);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();

			c.commit();
			//c.rollback();
			ccs.execute();
			res = new Res(0, ccs.getString(1));

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (Exception g) {
				res = new Res(-1, g.getMessage());
				return res;
			}
			res = new Res(-1, e.getMessage());
		} finally {
			
			CustomerUtils.closeResultSet(rs);
			CustomerUtils.closePStatement(ps);

			ConnectionPool.close(c);
		}
		return res;
	}

	public static Res doAction_open_closed(String un, String pw,
			String client_id, int actionid, String branch, String alias,
			String self_branch) {
		Res res = null;
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		String halias = CustomerService.get_alias_ho(alias);
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;

		try {
			if ((halias.compareTo(alias) == 0)
					&& (self_branch.compareTo(ConnectionPool.getValue("HO",
							alias)) != 0)) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			// c = ConnectionPool.getConnection(un,pw, alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			PreparedStatement inf = c.prepareCall("{ call info.init() }");
			inf.execute();
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");
			ccs.execute();
			ccs = c.prepareCall("{? = call Param.getparam('ID') }");
			ccs.registerOutParameter(1, java.sql.Types.VARCHAR);

			Customer customer = getCustomerById(client_id, branch, alias);

			cs.setString(1, "ID");
			cs.setLong(2, customer.getId());
			cs.execute();
			cs.setString(1, "BRANCH");
			cs.setString(2, customer.getBranch());
			cs.execute();
			cs.setString(1, "ID_CLIENT");
			cs.setString(2, customer.getId_client());
			cs.execute();
			cs.setString(1, "NAME");
			cs.setString(2, customer.getName());
			cs.execute();
			cs.setString(1, "CODE_COUNTRY");
			cs.setString(2, customer.getCode_country());
			cs.execute();
			cs.setString(1, "CODE_TYPE");
			cs.setString(2, customer.getCode_type());
			cs.execute();
			cs.setString(1, "CODE_RESIDENT");
			cs.setString(2, customer.getCode_resident());
			cs.execute();
			cs.setString(1, "CODE_SUBJECT");
			cs.setString(2, customer.getCode_subject());
			cs.execute();
			cs.setString(1, "SIGN_REGISTR");
			cs.setString(2, customer.getSign_registr() + "");
			cs.execute();
			cs.setString(1, "CODE_FORM");
			cs.setString(2, customer.getCode_form());
			cs.execute();
			cs.setString(1, "DATE_OPEN");
			cs.setString(
					2,
					customer.getDate_open() != null ? bdf.format(customer
							.getDate_open()) : null);
			cs.execute();
			cs.setString(1, "DATE_CLOSE");
			cs.setString(
					2,
					customer.getDate_close() != null ? bdf.format(customer
							.getDate_close()) : null);
			cs.execute();
			cs.setString(1, "STATE");
			cs.setString(2, customer.getState() + "");
			cs.execute();
			cs.setString(1, "P_BIRTHDAY");
			cs.setString(
					2,
					customer.getP_birthday() != null ? bdf.format(customer
							.getP_birthday()) : null);
			cs.execute();
			cs.setString(1, "P_POST_ADDRESS");
			cs.setString(2, customer.getP_post_address());
			cs.execute();
			cs.setString(1, "P_PASSPORT_TYPE");
			cs.setString(2, customer.getP_passport_type());
			cs.execute();
			cs.setString(1, "P_PASSPORT_SERIAL");
			cs.setString(2, customer.getP_passport_serial());
			cs.execute();
			cs.setString(1, "P_PASSPORT_NUMBER");
			cs.setString(2, customer.getP_passport_number());
			cs.execute();
			cs.setString(1, "P_PASSPORT_PLACE_REGISTRATION");
			cs.setString(2, customer.getP_passport_place_registration());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_REGISTRATION");
			cs.setString(
					2,
					customer.getP_passport_date_registration() != null ? bdf
							.format(customer.getP_passport_date_registration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_TAX_ORG");
			cs.setString(2, customer.getP_code_tax_org());
			cs.execute();
			cs.setString(1, "P_NUMBER_TAX_REGISTRATION");
			cs.setString(2, customer.getP_number_tax_registration());
			cs.execute();
			cs.setString(1, "P_CODE_BANK");
			cs.setString(2, customer.getP_code_bank());
			cs.execute();
			cs.setString(1, "P_CODE_CLASS_CREDIT");
			cs.setString(2, customer.getP_code_class_credit());
			cs.execute();
			cs.setString(1, "P_CODE_CITIZENSHIP");
			cs.setString(2, customer.getP_code_citizenship());
			cs.execute();
			cs.setString(1, "P_BIRTH_PLACE");
			cs.setString(2, customer.getP_birth_place());
			cs.execute();
			cs.setString(1, "P_CODE_CAPACITY");
			cs.setString(2, customer.getP_code_capacity());
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_DATE");
			cs.setString(
					2,
					customer.getP_capacity_status_date() != null ? bdf
							.format(customer.getP_capacity_status_date())
							: null);
			cs.execute();
			cs.setString(1, "P_CAPACITY_STATUS_PLACE");
			cs.setString(2, customer.getP_capacity_status_place());
			cs.execute();
			cs.setString(1, "P_NUM_CERTIF_CAPACITY");
			cs.setString(2, customer.getP_num_certif_capacity());
			cs.execute();
			cs.setString(1, "P_PHONE_HOME");
			cs.setString(2, customer.getP_phone_home());
			cs.execute();
			cs.setString(1, "P_PHONE_MOBILE");
			cs.setString(2, customer.getP_phone_mobile());
			cs.execute();
			cs.setString(1, "P_EMAIL_ADDRESS");
			cs.setString(2, customer.getP_email_address());
			cs.execute();
			cs.setString(1, "P_PENSION_SERTIF_SERIAL");
			cs.setString(2, customer.getP_pension_sertif_serial());
			cs.execute();
			cs.setString(1, "P_CODE_GENDER");
			cs.setString(2, customer.getP_code_gender());
			cs.execute();
			cs.setString(1, "P_CODE_NATION");
			cs.setString(2, customer.getP_code_nation());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_REGION");
			cs.setString(2, customer.getP_code_birth_region());
			cs.execute();
			cs.setString(1, "P_CODE_BIRTH_DISTR");
			cs.setString(2, customer.getP_code_birth_distr());
			cs.execute();
			cs.setString(1, "P_TYPE_DOCUMENT");
			cs.setString(2, customer.getP_type_document());
			cs.execute();
			cs.setString(1, "P_PASSPORT_DATE_EXPIRATION");
			cs.setString(
					2,
					customer.getP_passport_date_expiration() != null ? bdf
							.format(customer.getP_passport_date_expiration())
							: null);
			cs.execute();
			cs.setString(1, "P_CODE_ADR_REGION");
			cs.setString(2, customer.getP_code_adr_region());
			cs.execute();
			cs.setString(1, "P_CODE_ADR_DISTR");
			cs.setString(2, customer.getP_code_adr_distr());
			cs.execute();
			cs.setString(1, "P_INPS");
			cs.setString(2, customer.getP_inps());
			cs.execute();
			cs.setString(1, "P_PINFL");
			cs.setString(2, customer.getP_pinfl());
			cs.execute();
			cs.setString(1, "P_FAMILY");
			cs.setString(2, customer.getP_family());
			cs.execute();
			cs.setString(1, "P_FIRST_NAME");
			cs.setString(2, customer.getP_first_name());
			cs.execute();
			cs.setString(1, "P_PATRONYMIC");
			cs.setString(2, customer.getP_patronymic());
			cs.execute();

			acs.setInt(1, 1);
			acs.setInt(2, 2);
			acs.setInt(3, actionid);
			acs.execute();

			// c.rollback();
			c.commit();
			ccs.execute();
			res = new Res(0, ccs.getString(1));

		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			res = new Res(-1, e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static String doAction(String un, String pw, String branch,
			String id, int actionid, String alias, Boolean selfBranch) {
		String res = "";
		SimpleDateFormat bdf = new SimpleDateFormat("dd.MM.yyyy");
		Connection c = null;
		String halias = CustomerService.get_alias_ho(alias);
		CallableStatement cs = null;
		CallableStatement acs = null;
		CallableStatement ccs = null;
		String cn;
		try {
			if ((halias.compareTo(alias) == 0) && (!selfBranch)) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			// c = ConnectionPool.getConnection(un,pw,alias);
			cs = c.prepareCall("{ call Param.SetParam(?,?) }");
			acs = c.prepareCall("{ call kernel.doAction(?,?,?) }");
			ccs = c.prepareCall("{ call Param.clearparam() }");

			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM v_bfcustomer WHERE branch=? and id=?");
			ps.setString(1, branch);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ccs.execute();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					cn = rs.getMetaData().getColumnName(i);
					// System.out.println(cn+"  "+
					// rs.getMetaData().getColumnTypeName(i));
					if (rs.getString(cn) != null) {
						cs.setString(1, cn);
						if (rs.getMetaData().getColumnTypeName(i)
								.equals("DATE")) {
							cs.setString(2, bdf.format(rs.getDate(cn)));
						} else {
							cs.setString(2, rs.getString(cn));
						}
						cs.execute();
					}
				}

				acs.setInt(1, 2);
				acs.setInt(2, 2);
				acs.setInt(3, actionid);
				acs.execute();
				c.commit();
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			// e.printStackTrace();
			res = e.getMessage();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	protected static void updateCustomerDescription(Connection c, Customer customer) throws SQLException {
        if (!isCustomerDescriptionExists(c, customer)) {
            insertCustomerDescription(c, customer);
            return;
        }
        PreparedStatement ps = c.prepareStatement("UPDATE CLIENT_DESC "
                + "SET BRANCH = ?," +
                "LAST_NAME_CYR = ?," +
                "FIRST_NAME_CYR = ?," +
                "PATRONYMIC_CYR = ?,CODE_TYPE = ?,PASS_PLACE_REGION = ?, PASS_PLACE_DISTRICT = ?, POST_ADDRESS_STREET = ?,POST_ADDRESS_HOUSE = ?,POST_ADDRESS_FLAT = ?,POST_ADDRESS_QUARTER = ?"
                + "WHERE BRANCH = ? and ID_CLIENT = ?");
        ps.setString(1, customer.getBranch());
        ps.setString(2, customer.getP_family_local() != null ? customer.getP_family_local().toUpperCase() : null);
        ps.setString(3, customer.getP_first_name_local() != null ?
                customer.getP_first_name_local().toUpperCase() : null);
        ps.setString(4, customer.getP_patronymic_local() != null ?
                customer.getP_patronymic_local().toUpperCase() : null);
        ps.setString(5, customer.getCode_type());
        ps.setString(6, customer.getP_pass_place_region());
        ps.setString(7, customer.getP_pass_place_district());
        ps.setString(8, customer.getP_post_address_street());
        ps.setString(9, customer.getP_post_address_house());
        ps.setString(10, customer.getP_post_address_flat());
        ps.setString(11, customer.getP_post_address_quarter());
        ps.setString(12, customer.getBranch());
        ps.setString(13, customer.getId_client());
        ps.executeUpdate();
        ps.close();
    }


   protected static boolean isCustomerDescriptionExists(Connection c, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = c
                .prepareStatement("SELECT COUNT(*) FROM CLIENT_DESC WHERE BRANCH = ? AND ID_CLIENT = ?");
        preparedStatement.setString(1, customer.getBranch());
        preparedStatement.setString(2, customer.getId_client());
        ResultSet resultSet = preparedStatement.executeQuery();

        int count = 0;

        if (resultSet.next())
            count = resultSet.getInt(1);

        preparedStatement.close();
        resultSet.close();

        if (count > 0)
            return true;

        return false;
    }

   private static void insertCustomerDescription(Connection c, Customer customer) throws SQLException {
        PreparedStatement ps = c.prepareStatement("INSERT INTO CLIENT_DESC"
                + " (BRANCH,ID_CLIENT,LAST_NAME_CYR,FIRST_NAME_CYR,PATRONYMIC_CYR,CODE_TYPE,PASS_PLACE_REGION,PASS_PLACE_DISTRICT,POST_ADDRESS_STREET,POST_ADDRESS_HOUSE,POST_ADDRESS_FLAT,POST_ADDRESS_QUARTER)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, customer.getBranch());
        ps.setString(2, customer.getId_client());
        ps.setString(3, customer.getP_family_local() != null ?
                customer.getP_family_local().toUpperCase() : null);
        ps.setString(4, customer.getP_first_name_local() != null ?
                customer.getP_first_name_local().toUpperCase() : null);
        ps.setString(5, customer.getP_patronymic_local() != null ?
                customer.getP_patronymic_local().toUpperCase() : null);
        ps.setString(6, customer.getCode_type());
        ps.setString(7, customer.getP_pass_place_region());
        ps.setString(8, customer.getP_pass_place_district());
        ps.setString(9, customer.getP_post_address_street());
        ps.setString(10, customer.getP_post_address_house());
        ps.setString(11, customer.getP_post_address_flat());
        ps.setString(12, customer.getP_post_address_quarter());
        ps.executeUpdate();
        ps.close();
    }

   protected void deleteCustomerDescription(Connection c, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = c
                .prepareStatement("DELETE FROM CLIENT_DESC WHERE BRANCH = ? and ID_CLIENT = ?");
        preparedStatement.setString(1, customer.getBranch());
        preparedStatement.setString(2, customer.getId_client());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFields(CustomerFilter filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id=?", filter
					.getId()));
		}
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter
					.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId_client())) {
			flfields.add(new FilterField(getCond(flfields) + "id_client=?",
					filter.getId_client()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "upper(name) like ?", "%"
					+ filter.getName().toUpperCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(getCond(flfields) + "code_country=?",
					filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(getCond(flfields) + "code_type=?",
					filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(getCond(flfields) + "code_resident=?",
					filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(getCond(flfields) + "code_subject=?",
					filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(getCond(flfields) + "sign_registr=?",
					filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(getCond(flfields) + "code_form=?",
					filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(new FilterField(getCond(flfields) + "date_open=?",
					filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(getCond(flfields) + "date_close=?",
					filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter
					.getState()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(getCond(flfields) + "p_birthday=?",
					new java.sql.Date(filter.getP_birthday().getTime())));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(new FilterField(
					getCond(flfields) + "p_post_address=?", filter
							.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_passport_type=?", filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_passport_serial=?", filter.getP_passport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_passport_number=?", filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_passport_place_registration=?", filter
					.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_passport_date_registration=?", filter
					.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(
					getCond(flfields) + "p_code_tax_org=?", filter
							.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_number_tax_registration=?", filter
					.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_bank=?",
					filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_class_credit=?", filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_citizenship=?", filter.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(getCond(flfields) + "p_birth_place=?",
					filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_capacity=?", filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_capacity_status_date=?", filter
					.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_capacity_status_place=?", filter
					.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_num_certif_capacity=?", filter
					.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(getCond(flfields) + "p_phone_home=?",
					filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(
					getCond(flfields) + "p_phone_mobile=?", filter
							.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_email_address=?", filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_pension_sertif_serial=?", filter
					.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_gender=?",
					filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_nation=?",
					filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_birth_region=?", filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_birth_distr=?", filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_type_document=?", filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_passport_date_expiration=?", filter
					.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_adr_region=?", filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "p_code_adr_distr=?", filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(getCond(flfields) + "p_inps=?", filter
					.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_pinfl())) {
			flfields.add(new FilterField(getCond(flfields) + "p_pinfl=?", filter
					.getP_pinfl()));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "(upper(name) like '%"
					+ filter.getP_family().toUpperCase()
					+ "%' or upper(p_family) like ?)", "%"
					+ filter.getP_family().toUpperCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "(upper(name) like '%"
					+ filter.getP_first_name().toUpperCase()
					+ "%' or upper(p_first_name) like ?)", "%"
					+ filter.getP_first_name().toUpperCase() + "%"));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			flfields.add(new FilterField(getCond(flfields)
					+ "(upper(name) like '%"
					+ filter.getP_patronymic().toUpperCase()
					+ "%' or upper(p_patronymic) like ?)", "%"
					+ filter.getP_patronymic().toUpperCase() + "%"));
		}

		// flfields.add(new FilterField(getCond(flfields)+ "rownum<?",1001));

		return flfields;
	}

	public static int getCount(CustomerFilter filter, String alias) {

		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM v_bfcustomer ");
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement(sql.toString());

			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return n;

	}

	public static List<Customer> getCustomersFl(int pageIndex, int pageSize,
			CustomerFilter filter, String alias) {

		List<Customer> list = new ArrayList<Customer>();
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(filter);

		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		sql.append(alias + "." + msql2);
		if (flFields.size() > 0) {

			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(psql2);

		// System.out.println(sql.toString());
		try {
			c = ConnectionPool.getConnection(alias);
			// System.out.println(sql);
			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				// System.out.println(flFields.get(params).getColobject());
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);

			// System.out.println(v_upperbound);
			// System.out.println(v_lowerbound);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Customer(rs.getLong("id"), rs.getString("branch"),
						rs.getString("id_client"), rs.getString("name"), rs
								.getString("code_country"), rs
								.getString("code_type"), rs
								.getString("code_resident"), rs
								.getString("code_subject"), rs
								.getInt("sign_registr"), rs
								.getString("code_form"), rs
								.getDate("date_open"),
						rs.getDate("date_close"), rs.getInt("state"), rs
								.getDate("p_birthday"), rs
								.getString("p_post_address"), rs
								.getString("p_passport_type"), rs
								.getString("p_passport_serial"), rs
								.getString("p_passport_number"), rs
								.getString("p_passport_place_registration"), rs
								.getDate("p_passport_date_registration"), rs
								.getString("p_code_tax_org"), rs
								.getString("p_number_tax_registration"), rs
								.getString("p_code_bank"), rs
								.getString("p_code_class_credit"), rs
								.getString("p_code_citizenship"), rs
								.getString("p_birth_place"), rs
								.getString("p_code_capacity"), rs
								.getDate("p_capacity_status_date"), rs
								.getString("p_capacity_status_place"), rs
								.getString("p_num_certif_capacity"), rs
								.getString("p_phone_home"), rs
								.getString("p_phone_mobile"), rs
								.getString("p_email_address"), rs
								.getString("p_pension_sertif_serial"), rs
								.getString("p_code_gender"), rs
								.getString("p_code_nation"), rs
								.getString("p_code_birth_region"), rs
								.getString("p_code_birth_distr"), rs
								.getString("p_type_document"), rs
								.getDate("p_passport_date_expiration"), rs
								.getString("p_code_adr_region"), rs
								.getString("p_code_adr_distr"), rs
								.getString("p_inps"), rs.getString("p_pinfl"),
						rs.getString("p_family"), rs.getString("p_first_name"),
						rs.getString("p_patronymic")));
			}
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static Customer getCustomer1(int customerId, String alias) {

		Customer customer = new Customer();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM v_bfcustomer WHERE id=?");
			ps.setInt(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();

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
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setP_family(rs.getString("p_family"));
				customer.setP_first_name(rs.getString("p_first_name"));
				customer.setP_patronymic(rs.getString("p_patronymic"));
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return customer;
	}

	public static Customer getCustomerById(String customerId, String alias) {

		Customer customer = new Customer();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM v_bfcustomer WHERE id_client=?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();

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
				customer.setP_pinfl(rs.getString("p_pinfl"));
				// customer.setP_family(rs.getString("p_family"));////////////////////////////////////////////
				// customer.setP_first_name(rs.getString("p_first_name"));
				// customer.setP_patronymic(rs.getString("p_patronymic"));
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return customer;
	}

	public static Customer getCustomerById(String customerId, String branch,
			String alias) {

		Customer customer = null;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM v_bfcustomer WHERE id_client=? and branch=?");
			ps.setString(1, customerId);
			ps.setString(2, branch);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();

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
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setP_family((rs.getString("p_family") == null ? ""
						: rs.getString("p_family")));
				// customer.setP_family(null);
				customer.setP_first_name((rs.getString("p_first_name") == null ? ""
						: rs.getString("p_first_name")));
				customer.setP_patronymic((rs.getString("p_patronymic") == null ? ""
						: rs.getString("p_patronymic")));
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return customer;
	}

	public static Customer getCustomerById_tbl(String customerId,
			String branch, String alias) {

		Customer customer = null;
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM v_bfcustomer WHERE id=? and branch=?");
			ps.setString(1, customerId);
			ps.setString(2, branch);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer();

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
				customer.setP_pinfl(rs.getString("p_pinfl"));
				customer.setP_family(rs.getString("p_family"));
				customer.setP_first_name(rs.getString("p_first_name"));
				customer.setP_patronymic(rs.getString("p_patronymic"));
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return customer;
	}

	public static Res removeTc(String un, String pw, int id, String alias) {

		Res res = null;
		Connection c = null;
		String halias = CustomerService.get_alias_ho(alias);

		try {
			if (halias.compareTo(alias) == 0) {
				c = ConnectionPool.getConnection(alias);
			} else {
				c = ConnectionPool.getConnection(un, pw, alias);
			}
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("DELETE FROM bf_tieto_customers where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			c.commit();
			// c.rollback();

			res = new Res(0, "");
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
			res = new Res(-1, e.getMessage());
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static void Tc(TietoCustomer tietocustomer, String alias) {

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("UPDATE bf_tieto_customers SET bank_customer_id=?, head_customer_id=? WHERE branch=? and tieto_customer_id=?");
			ps.setString(1, tietocustomer.getBank_customer_id());
			ps.setString(2, tietocustomer.getHead_customer_id());
			ps.setString(3, tietocustomer.getBranch());
			ps.setString(4, tietocustomer.getTieto_customer_id());
			ps.executeUpdate();
			c.commit();
		} catch (SQLException e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}

	}

	public static TietoCustomer create(TietoCustomer tietocustomer, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("INSERT INTO bf_tieto_customers (branch, bank_customer_id, tieto_customer_id, head_customer_id ) VALUES (?,?,?,?)");

			ps.setString(1, tietocustomer.getBranch());
			ps.setString(
					2,
					!CheckNull.isEmpty(tietocustomer.getBank_customer_id()) ? tietocustomer
							.getBank_customer_id() : tietocustomer
							.getHead_customer_id());
			ps.setString(3, tietocustomer.getTieto_customer_id());
			ps.setString(4, tietocustomer.getHead_customer_id());
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
		return tietocustomer;
	}

	public static void create_lnk(String branch, String HO_id,
			String branch_id, String tieto_id, String cur_acc, String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			check_mapping(branch, HO_id, branch_id, null, c);

			ps = c.prepareStatement("INSERT INTO bf_tieto_customers (id,branch, bank_customer_id, tieto_customer_id, head_customer_id, cur_acc) VALUES (seq_bf_tieto_customers.Nextval, ?,?,?,?,?)");

			ps.setString(1, branch);
			ps.setString(2, branch_id);
			ps.setString(3, tieto_id);
			ps.setString(4, HO_id);
			ps.setString(5, cur_acc);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void update_lnk_ti_by_ho(String branch, String HO_id,
			String tieto_id, String alias) {

		String tieto = get_tieto_by_ho(HO_id, alias);
		Connection c = null;
		PreparedStatement ps = null;

		if (get_link_ho(HO_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				check_mapping(branch, HO_id, null, null, c);
				ps = c.prepareStatement("insert into bf_tieto_customers "
						+ "(id, tieto_customer_id, branch, head_customer_id) "
						+ "values (seq_bf_tieto_customers.Nextval, ?,?,?)");

				ps.setString(1, tieto == null ? tieto_id : tieto);
				ps.setString(2, branch);
				ps.setString(3, HO_id);

				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();

			} finally {
				ConnectionPool.close(c);
			}
			return;
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_tieto_customers set tieto_customer_id = ? where branch = ? and head_customer_id = ?");

			ps.setString(1, tieto == null ? tieto_id : tieto);
			ps.setString(2, branch);
			ps.setString(3, HO_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void update_lnk_ti_by_br(String branch, String br_id,
			String tieto_id, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_branch(br_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);

				check_mapping(branch, null, br_id, null, c);
				ps = c.prepareStatement("insert into bf_tieto_customers (id, tieto_customer_id, branch, bank_customer_id) values(seq_bf_tieto_customers.Nextval, ?,?,?)");

				ps.setString(1, tieto_id);
				ps.setString(2, branch);
				ps.setString(3, br_id);
				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();

			} finally {
				ConnectionPool.close(c);
			}
			return;
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_tieto_customers set tieto_customer_id = ? where branch = ? and bank_customer_id = ?");

			ps.setString(1, tieto_id);
			ps.setString(2, branch);
			ps.setString(3, br_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void update_lnk_ho_by_ti(String branch, String ho_id,
			String tieto_id, String alias) {

		String ho = get_HO_by_tieto(tieto_id, alias);
		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_tieto(tieto_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				check_mapping(branch, ho_id, null, null, c);
				ps = c.prepareStatement("insert into bf_tieto_customers (id,head_customer_id, branch, tieto_customer_id) values(seq_bf_tieto_customers.Nextval, ?,?,?)");

				ps.setString(1, ho == null ? ho_id : ho);
				ps.setString(2, branch);
				ps.setString(3, tieto_id);
				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();

			} finally {
				ConnectionPool.close(c);
			}
			return;
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_tieto_customers set head_customer_id = ? where branch = ? and tieto_customer_id = ?");

			ps.setString(1, ho == null ? ho_id : ho);
			ps.setString(2, branch);
			ps.setString(3, tieto_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	private static void check_mapping(String branch, String HO_customer_id,
			String branch_customer_id, Long mapping_id, Connection c)
			throws SQLException {
		return;
		/*
		 * PreparedStatement ps = null; ResultSet rs = null;
		 * if((HO_customer_id.startsWith("0") ||
		 * branch_customer_id.startsWith("0"))&& (HO_customer_id != null &&
		 * branch_customer_id != null && HO_customer_id != branch_customer_id))
		 * throw new IllegalArgumentException(
		 * "�������� ���������� ������ �������� �� � ������");
		 * 
		 * try { if(mapping_id != null) { ps =
		 * c.prepareStatement("select * from bf_tieto_customers c where c.id = ?"
		 * ); ps.setLong(1, mapping_id); } else if(branch_customer_id != null) {
		 * ps = c.prepareStatement(
		 * "select * from bf_tieto_customers c where c.branch_customer_id = ? and branch = ?"
		 * ); ps.setString(1, branch_customer_id); ps.setString(2, branch); }
		 * else if(HO_customer_id != null) { ps = c.prepareStatement(
		 * "select * from bf_tieto_customers c where c.head_customer_id = ? and branch = ?"
		 * ); ps.setString(1, HO_customer_id); ps.setString(2, branch); } rs =
		 * ps.executeQuery(); if(!rs.next()) return; String old_ho_customer_id =
		 * rs.getString("head_customer_id"); String old_branch_customer_id =
		 * rs.getString("branch_customer_id");
		 * if((HO_customer_id.startsWith("0") ||
		 * branch_customer_id.startsWith("0") ||
		 * old_ho_customer_id.startsWith("0") ||
		 * old_branch_customer_id.startsWith("0"))&&( HO_customer_id != null &&
		 * old_branch_customer_id != null && HO_customer_id !=
		 * old_branch_customer_id || branch_customer_id != null &&
		 * old_ho_customer_id != null && branch_customer_id !=
		 * old_ho_customer_id)) throw new IllegalArgumentException(
		 * "�������� ���������� ������ �������� �� � ������"); } finally {
		 * try{if(ps != null) ps.close();}catch(Exception e){} try{if(rs !=
		 * null) rs.close();}catch(Exception e){} }
		 */
	}

	public static void update_lnk_ho_by_br(String branch, String ho_id,
			String br_id, String alias) {

		String tieto = get_tieto_by_ho(ho_id, alias);
		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_branch(br_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				check_mapping(branch, ho_id, br_id, null, c);
				ps = c.prepareStatement("insert into bf_tieto_customers (id,head_customer_id, branch, bank_customer_id"
						+ (tieto != null ? ", tieto_customer_id" : "")
						+ ") values(seq_bf_tieto_customers.Nextval, ?,?,?"
						+ (tieto != null ? ",?" : "") + ")");

				ps.setString(1, ho_id);
				ps.setString(2, branch);
				ps.setString(3, br_id);
				if (tieto != null)
					ps.setString(4, tieto);
				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();

			} finally {
				ConnectionPool.close(c);
			}
			return;
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_tieto_customers set head_customer_id = ?"
					+ (tieto != null ? ", tieto_customer_id='" + tieto + "'"
							: "")
					+ " where branch = ? and bank_customer_id = ?");

			ps.setString(1, ho_id);
			ps.setString(2, branch);
			ps.setString(3, br_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void update_lnk_br_by_ti(String branch, String br,
			String tieto_id, String alias) {

		String ho = get_HO_by_tieto(tieto_id, alias);
		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_tieto(tieto_id, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				check_mapping(null, ho, branch, Long.parseLong(br), c);
				ps = c.prepareStatement("insert into bf_tieto_customers(id, bank_customer_id, branch, tieto_customer_id"
						+ (ho == null ? "" : ", head_customer_id")
						+ ") values (seq_bf_tieto_customers.Nextval, ?,?,?"
						+ (ho == null ? "" : ",?") + ")");

				ps.setString(1, br);
				ps.setString(2, branch);
				ps.setString(3, tieto_id);
				if (ho == null)
					ps.setString(4, ho);
				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();

			} finally {
				ConnectionPool.close(c);
			}
			return;
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_tieto_customers set bank_customer_id = ?"
					+ (ho == null ? "" : ", head_customer_id = '" + ho + "'")
					+ " where branch = ? and tieto_customer_id = ?");

			ps.setString(1, br);
			ps.setString(2, branch);
			ps.setString(3, tieto_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	public static void update_lnk_br_by_ho(String branch, String br, String ho,
			String alias) {
		String tieto = get_tieto_by_ho(ho, alias);
		Connection c = null;
		PreparedStatement ps = null;
		if (get_link_ho(ho, branch, alias) == null) {
			try {
				c = ConnectionPool.getConnection(alias);
				ps = c.prepareStatement("insert into bf_tieto_customers(id,bank_customer_id, branch, head_customer_id"
						+ (tieto != null ? ", tieto_customer_id" : "")
						+ ") values (seq_bf_tieto_customers.Nextval, ?,?,?"
						+ (tieto != null ? ",?" : "") + ")");

				ps.setString(1, br);
				ps.setString(2, branch);
				ps.setString(3, ho);
				if (tieto != null)
					ps.setString(4, tieto);
				ps.executeUpdate();
				c.commit();
			} catch (Exception e) {
				LtLogger.getLogger().error(CheckNull.getPstr(e));
				e.printStackTrace();

			} finally {
				ConnectionPool.close(c);
			}
			return;
		}

		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("update bf_tieto_customers set bank_customer_id = ?"
					+ (tieto != null ? ", tieto_customer_id = '" + tieto + "'"
							: "")
					+ " where branch = ? and head_customer_id = ?");

			ps.setString(1, br);
			ps.setString(2, branch);
			ps.setString(3, ho);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	public static TietoCustomer getTietoCustomer(TietoCustomer tietocustomerId,
			String alias) {

		TietoCustomer tietocustomer = new TietoCustomer();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM bf_tieto_customers WHERE branch=? and tieto_customer_id=?");
			ps.setString(1, tietocustomerId.getBranch());
			ps.setString(2, tietocustomerId.getTieto_customer_id());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tietocustomer = new TietoCustomer();

				tietocustomer.setBranch(rs.getString("branch"));
				tietocustomer.setBank_customer_id(rs
						.getString("bank_customer_id"));
				tietocustomer.setTieto_customer_id(rs
						.getString("tieto_customer_id"));
				tietocustomer.setHead_customer_id(rs
						.getString("head_customer_id"));
			} else {

				ps = c.prepareStatement("SELECT distinct tieto_customer_id,head_customer_id FROM bf_tieto_customers WHERE tieto_customer_id=?");
				ps.setString(1, tietocustomer.getTieto_customer_id());
				rs = ps.executeQuery();
				if (rs.next()) {
					tietocustomer = new TietoCustomer();
					tietocustomer.setTieto_customer_id(rs
							.getString("tieto_customer_id"));
					tietocustomer.setHead_customer_id(rs
							.getString("head_customer_id"));
				}

			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return tietocustomer;
	}

	public static TietoCustomer getTietoCustomer(String tietocustomerId,
			String branch_id, String alias) {

		TietoCustomer tietocustomer = new TietoCustomer();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM bf_tieto_customers WHERE branch=? and tieto_customer_id=?");
			ps.setString(1, branch_id);
			ps.setString(2, tietocustomerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tietocustomer = new TietoCustomer();

				tietocustomer.setBranch(rs.getString("branch"));
				tietocustomer.setBank_customer_id(rs
						.getString("bank_customer_id"));
				tietocustomer.setTieto_customer_id(rs
						.getString("tieto_customer_id"));
				tietocustomer.setHead_customer_id(rs
						.getString("head_customer_id"));
			} else {

				ps = c.prepareStatement("SELECT distinct tieto_customer_id,head_customer_id FROM bf_tieto_customers WHERE tieto_customer_id=?");
				ps.setString(1, tietocustomer.getTieto_customer_id());
				rs = ps.executeQuery();
				if (rs.next()) {
					tietocustomer = new TietoCustomer();
					tietocustomer.setTieto_customer_id(rs
							.getString("tieto_customer_id"));
					tietocustomer.setHead_customer_id(rs
							.getString("head_customer_id"));
				}

			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return tietocustomer;
	}

	public static TietoCustomer getTietoCustomer_by_head(
			String head_customerId, String branch_id, String alias) {

		TietoCustomer tietocustomer = new TietoCustomer();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM bf_tieto_customers WHERE branch=? and head_customer_id=?");
			ps.setString(1, branch_id);
			ps.setString(2, head_customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tietocustomer = new TietoCustomer();

				tietocustomer.setBranch(rs.getString("branch"));
				tietocustomer.setBank_customer_id(rs
						.getString("bank_customer_id"));
				tietocustomer.setTieto_customer_id(rs
						.getString("tieto_customer_id"));
				tietocustomer.setHead_customer_id(rs
						.getString("head_customer_id"));
			} else {

				ps = c.prepareStatement("SELECT distinct tieto_customer_id,head_customer_id FROM bf_tieto_customers WHERE tieto_customer_id=?");
				ps.setString(1, tietocustomer.getTieto_customer_id());
				rs = ps.executeQuery();
				if (rs.next()) {
					tietocustomer = new TietoCustomer();
					tietocustomer.setTieto_customer_id(rs
							.getString("tieto_customer_id"));
					tietocustomer.setHead_customer_id(rs
							.getString("head_customer_id"));
				}

			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return tietocustomer;
	}

	public static TietoCustomer getbranchCustomer_by_head(
			String head_customerId, String branch_id, String alias) {

		TietoCustomer tietocustomer = new TietoCustomer();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("SELECT * FROM bf_tieto_customers WHERE branch=? and head_customer_id=?");
			ps.setString(1, branch_id);
			ps.setString(2, head_customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tietocustomer = new TietoCustomer();

				tietocustomer.setBranch(rs.getString("branch"));
				tietocustomer.setBank_customer_id(rs
						.getString("bank_customer_id"));
				tietocustomer.setTieto_customer_id(rs
						.getString("tieto_customer_id"));
				tietocustomer.setHead_customer_id(rs
						.getString("head_customer_id"));
			} else {

				ps = c.prepareStatement("SELECT distinct tieto_customer_id,head_customer_id FROM bf_tieto_customers WHERE tieto_customer_id=?");
				ps.setString(1, tietocustomer.getTieto_customer_id());
				rs = ps.executeQuery();
				if (rs.next()) {
					tietocustomer = new TietoCustomer();
					tietocustomer.setTieto_customer_id(rs
							.getString("tieto_customer_id"));
					tietocustomer.setHead_customer_id(rs
							.getString("head_customer_id"));
				}

			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return tietocustomer;
	}

	public static String get_HO_by_tieto(String tieto_id, String alias) {
		String res = null;

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c
					.prepareStatement("Select * " + "From   (Select * "
							+ "From   BF_TIETO_CUSTOMERS T "
							+ "Where  T.TIETO_CUSTOMER_ID = ? "
							+ "and T.BRANCH = '00444' " + "UNION ALL "
							+ "Select * " + "From   BF_TIETO_CUSTOMERS T "
							+ "Where  T.TIETO_CUSTOMER_ID = ?) "
							+ "Where  ROWNUM = 1 ");
			ps.setString(1, tieto_id);
			ps.setString(2, tieto_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("head_customer_id");
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return res;
	}

	public static String get_tieto_by_ho(String ho_id, String alias) {
		String res = null;

		Connection c = null;

		try {
			c = ConnectionPool.getConnection(alias);
			PreparedStatement ps = c.prepareStatement("Select * "
					+ "From   (Select * " + "From   BF_TIETO_CUSTOMERS T "
					+ "Where  T.head_customer_id = ? "
					+ "and T.BRANCH = '00444' " + "UNION ALL " + "Select * "
					+ "From   BF_TIETO_CUSTOMERS T "
					+ "Where  T.head_customer_id = ?) " + "Where  ROWNUM = 1 ");
			ps.setString(1, ho_id);
			ps.setString(2, ho_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res = rs.getString("TIETO_CUSTOMER_ID");
			}
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}

		return res;
	}

	public static String get_contract(String branch, String card_code,
			String alias) {
		String res = null;
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(alias);

			PreparedStatement proc = c
					.prepareStatement("select BF.GET_CONTRACT_NUMBER(?,?) from dual");

			proc.setString(1, branch);
			proc.setString(2, card_code);

			ResultSet rs = proc.executeQuery();
			if (rs.next())
				res = new String(rs.getString(1));

		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return res;
	}

	public static void update_lnk_set_acc(String un, String pwd, String branch,
			String br_id, String acc, String alias) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(un, pwd, alias);
			ps = c.prepareStatement("update bf_tieto_customers set cur_acc = ? where branch = ? and head_customer_id = ?");

			ps.setString(1, acc);
			ps.setString(2, branch);
			ps.setString(3, br_id);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			LtLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();

		} finally {
			ConnectionPool.close(c);
		}
	}

	// public static String get

}
