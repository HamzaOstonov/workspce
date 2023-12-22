package com.is.customer_.search.searchBaseLocal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.CustomerBuilder;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;

public class SearchService {
	private static final Logger logger = Logger.getLogger(SearchService.class);
	private static final String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from (";
	private static final String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ?";
	private static final String msql = "SELECT * FROM V_CLIENT_SAP ";
	public static final String SEARCH_BY_IDENTIFICATION_SQL = "SELECT * FROM V_CLIENT_SAP A WHERE  "
			+ " A.P_PASSPORT_SERIAL=? AND A.P_PASSPORT_NUMBER = ? AND A.CODE_SUBJECT = 'P'";

	private SearchService() {
	}

	public static SearchService getInstance() {
		return new SearchService();
	}

	public List<Customer> getByIdentification(Customer customer, String schema) {
		// logger.error("Search By Identification Service");
		Connection c = null;
		CallableStatement init = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Customer> list = new ArrayList<Customer>();
		String idSAP = customer.getIdSap();
		/*
		 * if (CustomerUtils.isEmpty(customer.getP_type_document()) ||
		 * CustomerUtils.isEmpty(customer.getP_passport_serial()) ||
		 * CustomerUtils.isEmpty(customer.getP_passport_number())) return list;
		 */
		try {
			c = ConnectionPool.getConnection(schema);
			init = c.prepareCall("{call info.init()}");
			init.execute();

			preparedStatement = c.prepareStatement(SEARCH_BY_IDENTIFICATION_SQL);
			// preparedStatement.setString(1,customer.getP_type_document());
			preparedStatement.setString(1, customer.getP_passport_serial());
			preparedStatement.setString(2, customer.getP_passport_number());

			resultSet = preparedStatement.executeQuery();
			/*
			 * logger.error("Search by Identification type document " +
			 * customer.getP_type_document()); logger.error(
			 * "Search by Identification passport serial " +
			 * customer.getP_passport_serial()); logger.error(
			 * "Search by Identification passport number " +
			 * customer.getP_passport_number());
			 */
			while (resultSet.next()) {
				Customer customer_ = new Customer();
				customer_.setBranch(resultSet.getString("branch"));
				customer_.setId(resultSet.getString("id_client"));
				customer_.setIdSap(idSAP);
				list.add(customer_);
			}
		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			GeneralUtils.closeStatement(init);
			GeneralUtils.closeResultSet(resultSet);
			GeneralUtils.closeStatement(preparedStatement);
			ConnectionPool.close(c);
		}
		return list;
	}

	public List<Customer> initSearch(Customer customer, int pageIndex, int pageSize, String alias) {
		String idSAP = customer.getIdSap();
		List<Customer> list = new ArrayList<Customer>();
		customer.setP_post_address(customer.getP_post_address() != null ? customer.getP_post_address().trim() : null);
		customer.setP_passport_place_registration(customer.getP_passport_place_registration() != null
				? customer.getP_passport_place_registration().trim() : customer.getP_passport_place_registration());
        customer.setP_passport_type(null);
        
		Connection c = null;
		int v_lowerbound = pageIndex + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		int params;
		List<FilterField> flFields = getFilterFields(customer);
		// logger.error("FILTER FIELDS SIZE " + flFields.size());
		StringBuffer sql = new StringBuffer();
		sql.append(psql1);
		sql.append(msql);
		if (flFields.size() > 0) {
			for (int i = 0; i < flFields.size(); i++) {
				sql.append(flFields.get(i).getSqlwhere());
			}
		}
		sql.append(" and code_subject = \'P\' order by id desc");
		sql.append(psql2);

		try {
			c = ConnectionPool.getConnection(alias);
			CallableStatement cs = c.prepareCall("{call info.init()}");
			cs.execute();

			//logger.info("Select QUERY " + sql.toString());

			PreparedStatement ps = c.prepareStatement(sql.toString());
			for (params = 0; params < flFields.size(); params++) {
				Object obj = flFields.get(params).getColobject();
				if (obj instanceof java.util.Date) {
					ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
					continue;
				}
				if (obj instanceof String) {
					// ps.setString(params + 1, ((String) obj).toUpperCase());
					ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
					continue;
				}
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			// logger.error("SQL " + sql + " " + customer);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Customer partner = mapResultSetToCustomer(rs);
				partner.setIdSap(idSAP);
				list.add(partner);
			}
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	private static String getCond(List<FilterField> flfields) {
		if (flfields.size() > 0) {
			return " and ";
		} else
			return " where ";
	}

	private static List<FilterField> getFilterFieldsAndrei(Customer filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		/*
		 * if (!CheckNull.isEmpty(filter.getId())) { flfields.add(new
		 * FilterField(getCond(flfields) + "id=?", filter.getId())); }
		 */
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id_client=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getName())) {
			flfields.add(new FilterField(getCond(flfields) + "name like upper(?)", filter.getName()));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(getCond(flfields) + "code_country=?", filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(getCond(flfields) + "code_type=?", filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(getCond(flfields) + "code_resident=?", filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(getCond(flfields) + "code_subject=?", filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(getCond(flfields) + "code_form=?", filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(new FilterField(getCond(flfields) + "date_open=?", filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(getCond(flfields) + "date_close=?", filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getKod_err())) {
			flfields.add(new FilterField(getCond(flfields) + "kod_err=?", filter.getKod_err()));
		}
		if (!CheckNull.isEmpty(filter.getFile_name())) {
			flfields.add(new FilterField(getCond(flfields) + "file_name=?", filter.getFile_name()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(getCond(flfields) + "p_birthday=?", filter.getP_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_post_address like upper(?)", filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_type=?", filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_passport_serial like ?", filter.getP_passport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_passport_number like ?", filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_place_registration like upper(?)",
					filter.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_date_registration=?",
					filter.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_tax_org=?", filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_number_tax_registration like ?",
					filter.getP_number_tax_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_bank=?", filter.getP_code_bank()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_class_credit=?", filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_citizenship=?", filter.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(getCond(flfields) + "p_birth_place=?", filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_capacity=?", filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(getCond(flfields) + "p_capacity_status_date=?",
					filter.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(getCond(flfields) + "p_capacity_status_place=?",
					filter.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_num_certif_capacity=?", filter.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(getCond(flfields) + "p_phone_home=?", filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(getCond(flfields) + "p_phone_mobile=?", filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(getCond(flfields) + "p_email_address=?", filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(getCond(flfields) + "p_pension_sertif_serial=?",
					filter.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_gender=?", filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_nation=?", filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_birth_region=?", filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_birth_distr=?", filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(getCond(flfields) + "p_type_document=?", filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_date_expiration=?",
					filter.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_adr_region=?", filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_adr_distr=?", filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(getCond(flfields) + "p_inps=?", filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_pinfl())) {
			flfields.add(new FilterField(getCond(flfields) + "p_pinfl=?", filter.getP_pinfl()));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			// String field = includeLikeOperator(filter.getP_family()) == true
			// ? "p_family LIKE ?" : "p_family=?";
			flfields.add(new FilterField(getCond(flfields) + "p_family like upper(?)", filter.getP_family()));
			// flfields.add(new FilterField(getCond(flfields) + field,
			// filter.getP_family()));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			/*
			 * String field = includeLikeOperator(filter.getP_first_name()) ==
			 * true ? "p_first_name LIKE ?" : "p_first_name=?";
			 */
			flfields.add(new FilterField(getCond(flfields) + "p_first_name like upper(?)", filter.getP_first_name()));
			// flfields.add(new FilterField(getCond(flfields) + field,
			// filter.getP_first_name()));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			/*
			 * String field = includeLikeOperator(filter.getP_patronymic()) ==
			 * true ? "p_patronymic LIKE ?" : "p_patronymic=?";
			 */
			flfields.add(new FilterField(getCond(flfields) + "p_patronymic like upper(?)", filter.getP_patronymic()));
			// flfields.add(new FilterField(getCond(flfields) + field,
			// filter.getP_patronymic()));
		}
		if (!CheckNull.isEmpty(filter.getP_family_local())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_last_name_cyr like upper(?)", filter.getP_family_local()));
			/*
			 * String field = includeLikeOperator(filter.getP_last_name_cyr())
			 * == true ? "p_last_name_cyr LIKE ?" : "p_last_name_cyr=?";
			 * flfields.add(new FilterField(getCond(flfields) + field,
			 * filter.getP_last_name_cyr()));
			 */
		}
		if (!CheckNull.isEmpty(filter.getP_first_name_local())) {
			flfields.add(new FilterField(getCond(flfields) + "p_first_name_cyr like upper(?)",
					filter.getP_first_name_local()));
			/*
			 * String field = includeLikeOperator(filter.getP_first_name_cyr())
			 * == true ? "p_first_name_cyr LIKE ?" : "p_first_name_cyr = ?";
			 * flfields.add(new FilterField(getCond(flfields) + field,
			 * filter.getP_first_name_cyr()));
			 */
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic_local())) {
			flfields.add(new FilterField(getCond(flfields) + "p_patronymic_cyr like upper(?)",
					filter.getP_patronymic_local()));
			/*
			 * String field = includeLikeOperator(filter.getP_patronymic_cyr())
			 * == true ? "p_patronymic_cyr LIKE ?" : "p_patronymic_cyr=?";
			 * flfields.add(new FilterField(getCond(flfields) + field,
			 * filter.getP_patronymic_cyr()));
			 */
		}
		if (!CheckNull.isEmpty(filter.getP_pass_place_region()))
			flfields.add(new FilterField(getCond(flfields) + "p_pass_place_region=?", filter.getP_pass_place_region()));

		if (!CheckNull.isEmpty(filter.getP_pass_place_district()))
			flfields.add(
					new FilterField(getCond(flfields) + "p_pass_place_district", filter.getP_pass_place_district()));

		if (!CheckNull.isEmpty(filter.getP_post_address_street()))
			flfields.add(
					new FilterField(getCond(flfields) + "p_post_address_street", filter.getP_post_address_street()));
		if (!CheckNull.isEmpty(filter.getP_post_address_house()))
			flfields.add(new FilterField(getCond(flfields) + "p_post_address_house", filter.getP_post_address_house()));
		if (!CheckNull.isEmpty(filter.getP_post_address_flat()))
			flfields.add(new FilterField(getCond(flfields) + "p_post_address_flat", filter.getP_post_address_flat()));
		if (!CheckNull.isEmpty(filter.getP_post_address_flat()))
			flfields.add(new FilterField(getCond(flfields) + "p_post_address_street_code",
					filter.getP_post_address_street_code()));
		if (!CheckNull.isEmpty(filter.getP_post_address_flat()))
			flfields.add(
					new FilterField(getCond(flfields) + "p_post_address_quarter", filter.getP_post_address_quarter()));
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	private static List<FilterField> getFilterFields(Customer filter) {
		List<FilterField> flfields = new ArrayList<FilterField>();

		/*
		 * if (!CheckNull.isEmpty(filter.getId())) { flfields.add(new
		 * FilterField(getCond(flfields) + "id=?", filter.getId())); }
		 */
		if (!CheckNull.isEmpty(filter.getBranch())) {
			flfields.add(new FilterField(getCond(flfields) + "branch=?", filter.getBranch()));
		}
		if (!CheckNull.isEmpty(filter.getId())) {
			flfields.add(new FilterField(getCond(flfields) + "id_client=?", filter.getId()));
		}
		if (!CheckNull.isEmpty(filter.getName_search())) {
			flfields.add(new FilterField(
					getCond(flfields)
							+ "( upper(name) like upper(?) or upper(p_family||' '||p_first_name||' '||p_patronymic) like upper(?) or upper(p_last_name_cyr||' '||p_first_name_cyr||' '||p_patronymic_cyr) like upper(?) ) ",
					filter.getName_search()));
			flfields.add(new FilterField("", filter.getName_search())); // dva s
																		// lishnem
																		// razom
																		// delayu.
																		// parametr
																		// doljen
																		// peredatsya
																		// tri
																		// raz
			flfields.add(new FilterField("", filter.getName_search()));
		}
		if (!CheckNull.isEmpty(filter.getCode_country())) {
			flfields.add(new FilterField(getCond(flfields) + "code_country=?", filter.getCode_country()));
		}
		if (!CheckNull.isEmpty(filter.getCode_type())) {
			flfields.add(new FilterField(getCond(flfields) + "code_type=?", filter.getCode_type()));
		}
		if (!CheckNull.isEmpty(filter.getCode_resident())) {
			flfields.add(new FilterField(getCond(flfields) + "code_resident=?", filter.getCode_resident()));
		}
		if (!CheckNull.isEmpty(filter.getCode_subject())) {
			flfields.add(new FilterField(getCond(flfields) + "code_subject=?", filter.getCode_subject()));
		}
		if (!CheckNull.isEmpty(filter.getSign_registr())) {
			flfields.add(new FilterField(getCond(flfields) + "sign_registr=?", filter.getSign_registr()));
		}
		if (!CheckNull.isEmpty(filter.getCode_form())) {
			flfields.add(new FilterField(getCond(flfields) + "code_form=?", filter.getCode_form()));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_open()) && filter.getSign_date_open() == 1
				&& !CheckNull.isEmpty(filter.getDate_open())) {
			flfields.add(new FilterField(getCond(flfields) + "date_open=?", filter.getDate_open()));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_open()) && filter.getSign_date_open() == 2
				&& !CheckNull.isEmpty(filter.getDate_open()) && !CheckNull.isEmpty(filter.getDate_open1())) {
			flfields.add(new FilterField(getCond(flfields) + "date_open between ? and ?", filter.getDate_open()));
			flfields.add(new FilterField("", filter.getDate_open1()));
		}

		if (!CheckNull.isEmpty(filter.getSign_date_close()) && filter.getSign_date_close() == 1
				&& !CheckNull.isEmpty(filter.getDate_close())) {
			flfields.add(new FilterField(getCond(flfields) + "date_close=?", filter.getDate_close()));
		}
		if (!CheckNull.isEmpty(filter.getSign_date_close()) && filter.getSign_date_close() == 2
				&& !CheckNull.isEmpty(filter.getDate_close()) && !CheckNull.isEmpty(filter.getDate_close1())) {
			flfields.add(new FilterField(getCond(flfields) + "date_close between ? and ?", filter.getDate_close()));
			flfields.add(new FilterField("", filter.getDate_close1()));
		}

		if (!CheckNull.isEmpty(filter.getState())) {
			flfields.add(new FilterField(getCond(flfields) + "state=?", filter.getState()));
		}
		if (!CheckNull.isEmpty(filter.getKod_err())) {
			flfields.add(new FilterField(getCond(flfields) + "kod_err=?", filter.getKod_err()));
		}
		if (!CheckNull.isEmpty(filter.getFile_name())) {
			flfields.add(new FilterField(getCond(flfields) + "file_name=?", filter.getFile_name()));
		}
		if (!CheckNull.isEmpty(filter.getP_birthday())) {
			flfields.add(new FilterField(getCond(flfields) + "p_birthday=?", filter.getP_birthday()));
		}
		if (!CheckNull.isEmpty(filter.getP_post_address())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_post_address like upper(?)", filter.getP_post_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_type())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_type=?", filter.getP_passport_type()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_serial())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_serial=?", filter.getP_passport_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_number())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_number like ?", filter.getP_passport_number()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_place_registration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_place_registration like upper(?)",
					filter.getP_passport_place_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_registration())) {
			// if
			// (!CheckNull.isEmpty(filter.getP_passport_date_registration1())) {
			// flfields.add(new FilterField(getCond(flfields) +
			// "p_passport_date_registration between ? and ?",
			// filter.getP_passport_date_registration()));
			// flfields.add(new FilterField("",
			// filter.getP_passport_date_registration1()));
			// }
			// else
			flfields.add(new FilterField(getCond(flfields) + "p_passport_date_registration=?",
					filter.getP_passport_date_registration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_tax_org())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_tax_org=?", filter.getP_code_tax_org()));
		}
		if (!CheckNull.isEmpty(filter.getP_number_tax_registration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_number_tax_registration like ?",
					filter.getP_number_tax_registration()));
		}
		/*if (!CheckNull.isEmpty(filter.getP_code_bank())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_bank=?", filter.getP_code_bank()));
		}*/
		if (!CheckNull.isEmpty(filter.getP_code_class_credit())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_class_credit=?", filter.getP_code_class_credit()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_citizenship())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_citizenship=?", filter.getP_code_citizenship()));
		}
		if (!CheckNull.isEmpty(filter.getP_birth_place())) {
			flfields.add(new FilterField(getCond(flfields) + "p_birth_place=?", filter.getP_birth_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_capacity())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_capacity=?", filter.getP_code_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_date())) {
			flfields.add(new FilterField(getCond(flfields) + "p_capacity_status_date=?",
					filter.getP_capacity_status_date()));
		}
		if (!CheckNull.isEmpty(filter.getP_capacity_status_place())) {
			flfields.add(new FilterField(getCond(flfields) + "p_capacity_status_place=?",
					filter.getP_capacity_status_place()));
		}
		if (!CheckNull.isEmpty(filter.getP_num_certif_capacity())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_num_certif_capacity=?", filter.getP_num_certif_capacity()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_home())) {
			flfields.add(new FilterField(getCond(flfields) + "p_phone_home=?", filter.getP_phone_home()));
		}
		if (!CheckNull.isEmpty(filter.getP_phone_mobile())) {
			flfields.add(new FilterField(getCond(flfields) + "p_phone_mobile=?", filter.getP_phone_mobile()));
		}
		if (!CheckNull.isEmpty(filter.getP_email_address())) {
			flfields.add(new FilterField(getCond(flfields) + "p_email_address=?", filter.getP_email_address()));
		}
		if (!CheckNull.isEmpty(filter.getP_pension_sertif_serial())) {
			flfields.add(new FilterField(getCond(flfields) + "p_pension_sertif_serial=?",
					filter.getP_pension_sertif_serial()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_gender())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_gender=?", filter.getP_code_gender()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_nation())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_nation=?", filter.getP_code_nation()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_region())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_birth_region=?", filter.getP_code_birth_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_birth_distr())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_birth_distr=?", filter.getP_code_birth_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_type_document())) {
			flfields.add(new FilterField(getCond(flfields) + "p_type_document=?", filter.getP_type_document()));
		}
		if (!CheckNull.isEmpty(filter.getP_passport_date_expiration())) {
			flfields.add(new FilterField(getCond(flfields) + "p_passport_date_expiration=?",
					filter.getP_passport_date_expiration()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_region())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_adr_region=?", filter.getP_code_adr_region()));
		}
		if (!CheckNull.isEmpty(filter.getP_code_adr_distr())) {
			flfields.add(new FilterField(getCond(flfields) + "p_code_adr_distr=?", filter.getP_code_adr_distr()));
		}
		if (!CheckNull.isEmpty(filter.getP_inps())) {
			flfields.add(new FilterField(getCond(flfields) + "p_inps=?", filter.getP_inps()));
		}
		if (!CheckNull.isEmpty(filter.getP_pinfl())) {
			flfields.add(new FilterField(getCond(flfields) + "p_pinfl=?", filter.getP_pinfl()));
		}
		if (!CheckNull.isEmpty(filter.getP_id_nibbd())) {
			flfields.add(new FilterField(getCond(flfields) + "p_id_nibbd=?", filter.getP_id_nibbd()));
		}
		if (!CheckNull.isEmpty(filter.getP_family())) {
			// String field = includeLikeOperator(filter.getP_family()) == true
			// ? "p_family LIKE ?" : "p_family=?";
			flfields.add(new FilterField(getCond(flfields) + "p_family like upper(?)", filter.getP_family()));
			// flfields.add(new FilterField(getCond(flfields) + field,
			// filter.getP_family()));
		}
		if (!CheckNull.isEmpty(filter.getP_first_name())) {
			/*
			 * String field = includeLikeOperator(filter.getP_first_name()) ==
			 * true ? "p_first_name LIKE ?" : "p_first_name=?";
			 */
			flfields.add(new FilterField(getCond(flfields) + "p_first_name like upper(?)", filter.getP_first_name()));
			// flfields.add(new FilterField(getCond(flfields) + field,
			// filter.getP_first_name()));
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic())) {
			/*
			 * String field = includeLikeOperator(filter.getP_patronymic()) ==
			 * true ? "p_patronymic LIKE ?" : "p_patronymic=?";
			 */
			flfields.add(new FilterField(getCond(flfields) + "p_patronymic like upper(?)", filter.getP_patronymic()));
			// flfields.add(new FilterField(getCond(flfields) + field,
			// filter.getP_patronymic()));
		}
		if (!CheckNull.isEmpty(filter.getP_family_local())) {
			flfields.add(
					new FilterField(getCond(flfields) + "p_last_name_cyr like upper(?)", filter.getP_family_local()));
			/*
			 * String field = includeLikeOperator(filter.getP_last_name_cyr())
			 * == true ? "p_last_name_cyr LIKE ?" : "p_last_name_cyr=?";
			 * flfields.add(new FilterField(getCond(flfields) + field,
			 * filter.getP_last_name_cyr()));
			 */
		}
		if (!CheckNull.isEmpty(filter.getP_first_name_local())) {
			flfields.add(new FilterField(getCond(flfields) + "p_first_name_cyr like upper(?)",
					filter.getP_first_name_local()));
			/*
			 * String field = includeLikeOperator(filter.getP_first_name_cyr())
			 * == true ? "p_first_name_cyr LIKE ?" : "p_first_name_cyr = ?";
			 * flfields.add(new FilterField(getCond(flfields) + field,
			 * filter.getP_first_name_cyr()));
			 */
		}
		if (!CheckNull.isEmpty(filter.getP_patronymic_local())) {
			flfields.add(new FilterField(getCond(flfields) + "p_patronymic_cyr like upper(?)",
					filter.getP_patronymic_local()));
			/*
			 * String field = includeLikeOperator(filter.getP_patronymic_cyr())
			 * == true ? "p_patronymic_cyr LIKE ?" : "p_patronymic_cyr=?";
			 * flfields.add(new FilterField(getCond(flfields) + field,
			 * filter.getP_patronymic_cyr()));
			 */
		}
		if (!CheckNull.isEmpty(filter.getP_pass_place_region()))
			flfields.add(new FilterField(getCond(flfields) + "p_pass_place_region=?", filter.getP_pass_place_region()));

		if (!CheckNull.isEmpty(filter.getP_pass_place_district()))
			flfields.add(
					new FilterField(getCond(flfields) + "p_pass_place_district", filter.getP_pass_place_district()));

		if (!CheckNull.isEmpty(filter.getP_post_address_street()))
			flfields.add(
					new FilterField(getCond(flfields) + "p_post_address_street", filter.getP_post_address_street()));
		if (!CheckNull.isEmpty(filter.getP_post_address_house()))
			flfields.add(new FilterField(getCond(flfields) + "p_post_address_house", filter.getP_post_address_house()));
		if (!CheckNull.isEmpty(filter.getP_post_address_flat()))
			flfields.add(new FilterField(getCond(flfields) + "p_post_address_flat", filter.getP_post_address_flat()));
		if (!CheckNull.isEmpty(filter.getP_post_address_flat()))
			flfields.add(new FilterField(getCond(flfields) + "p_post_address_street_code",
					filter.getP_post_address_street_code()));
		if (!CheckNull.isEmpty(filter.getP_post_address_flat()))
			flfields.add(
					new FilterField(getCond(flfields) + "p_post_address_quarter", filter.getP_post_address_quarter()));

		// if (!CheckNull.isEmpty(filter.getDoc_number_search()))
		// {
		// flfields.add(
		// new FilterField(getCond(flfields) + "(p_number_tax_registration like
		// ? or p_inps like ? or p_passport_number like ?)",
		// filter.getDoc_number_search()));
		// flfields.add(
		// new FilterField("", filter.getDoc_number_search()));
		// flfields.add(
		// new FilterField("", filter.getDoc_number_search()));
		// }
		flfields.add(new FilterField(getCond(flfields) + "rownum<?", 1001));

		return flfields;
	}

	private static boolean includeLikeOperator(String field) {
		return field.contains("%") ? true : false;
	}

	public int getCount(Customer filter, String alias) {
		filter.setP_post_address(filter.getP_post_address() != null ? filter.getP_post_address().trim() : null);
		filter.setP_passport_place_registration(filter.getP_passport_place_registration() != null
				? filter.getP_passport_place_registration().trim() : filter.getP_passport_place_registration());
		filter.setP_passport_type(null);
		
		Connection c = null;
		int n = 0;
		List<FilterField> flFields = getFilterFields(filter);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ct FROM V_CLIENT_SAP ");
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

			//for (int k = 0; k < flFields.size(); k++) {
			//	ps.setObject(k + 1, flFields.get(k).getColobject());
			//} //commented 2017.12.12
			
			for (int params = 0; params < flFields.size(); params++) {
				Object obj = flFields.get(params).getColobject();
				if (obj instanceof java.util.Date) {
					ps.setDate(params + 1, CheckNull.d2sql((java.util.Date) obj));
					continue;
				}
				if (obj instanceof String) {
					// ps.setString(params + 1, ((String) obj).toUpperCase());
					ps.setString(params + 1, ((String) obj).toUpperCase().replace("*", "%"));
					continue;
				}
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
		return n;
	}

	private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
		return new CustomerBuilder().setLongId(resultSet.getLong("id")).setBranch(resultSet.getString("branch"))
				.setId(resultSet.getString("id_client")).setName(resultSet.getString("name"))
				.setCode_country(resultSet.getString("code_country")).setCode_type(resultSet.getString("code_type"))
				.setCode_resident(resultSet.getString("code_resident"))
				.setCode_subject(resultSet.getString("code_subject")).setSign_registr(resultSet.getInt("sign_registr"))
				.setCode_form(resultSet.getString("code_form")).setDate_open(resultSet.getDate("date_open"))
				.setDate_close(resultSet.getDate("date_close")).setState(resultSet.getString("state"))
				.setKod_err(resultSet.getInt("kod_err")).setFile_name(resultSet.getString("file_name"))
				.setP_birthday(resultSet.getDate("p_birthday")).setP_post_address(resultSet.getString("p_post_address"))
				.setP_passport_type(resultSet.getString("p_passport_type"))
				.setP_passport_serial(resultSet.getString("p_passport_serial"))
				.setP_passport_number(resultSet.getString("p_passport_number"))
				.setP_passport_place_registration(resultSet.getString("p_passport_place_registration"))
				.setP_passport_date_registration(resultSet.getDate("p_passport_date_registration"))
				.setP_code_tax_org(resultSet.getString("p_code_tax_org"))
				.setP_number_tax_registration(resultSet.getString("p_number_tax_registration"))
				.setP_code_bank(resultSet.getString("p_code_bank"))
				.setP_code_class_credit(resultSet.getString("p_code_class_credit"))
				.setP_code_citizenship(resultSet.getString("p_code_citizenship"))
				.setP_birth_place(resultSet.getString("p_birth_place"))
				.setP_code_capacity(resultSet.getString("p_code_capacity"))
				.setP_capacity_status_date(resultSet.getDate("p_capacity_status_date"))
				.setP_capacity_status_place(resultSet.getString("p_capacity_status_place"))
				.setP_num_certif_capacity(resultSet.getString("p_num_certif_capacity"))
				.setP_pension_sertif_serial(resultSet.getString("p_pension_sertif_serial"))
				.setP_phone_home(resultSet.getString("p_phone_home"))
				.setP_phone_mobile(resultSet.getString("p_phone_mobile"))
				.setP_email_address(resultSet.getString("p_email_address"))
				.setP_code_gender(resultSet.getString("p_code_gender"))
				.setP_code_nation(resultSet.getString("p_code_nation"))
				.setP_code_birth_region(resultSet.getString("p_code_birth_region"))
				.setP_code_birth_distr(resultSet.getString("p_code_birth_distr"))
				.setP_type_document(resultSet.getString("p_type_document"))
				.setP_passport_date_expiration(resultSet.getDate("p_passport_date_expiration"))
				.setP_code_adr_region(resultSet.getString("p_code_adr_region"))
				.setP_code_adr_distr(resultSet.getString("p_code_adr_distr")).setP_inps(resultSet.getString("p_inps")).setP_pinfl(resultSet.getString("p_pinfl"))
				.setP_family(resultSet.getString("p_family")).setP_first_name(resultSet.getString("p_first_name"))
				.setP_patronymic(resultSet.getString("p_patronymic"))
				.setP_family_local(resultSet.getString("p_last_name_cyr"))
				.setP_first_name_local(resultSet.getString("p_first_name_cyr"))
				.setP_patronymic_local(resultSet.getString("p_patronymic_cyr"))
				.setP_pass_place_region(resultSet.getString("p_pass_place_region"))
				.setP_pass_place_district(resultSet.getString("p_pass_place_district"))
				.setP_post_address_quarter(resultSet.getString("p_post_address_quarter"))
				.setP_post_address_street(resultSet.getString("p_post_address_street"))
				.setP_post_address_house(resultSet.getString("p_post_address_house"))
				.setP_post_address_flat(resultSet.getString("p_post_address_flat"))
				.setP_drive_permit_num(resultSet.getString("p_drive_permit_num"))
				.setP_drive_permit_ser(resultSet.getString("p_drive_permit_ser"))
				.setP_drive_permit_reg_d(resultSet.getDate("p_drive_permit_reg_d"))
				.setP_drive_permit_exp_d(resultSet.getDate("p_drive_permit_exp_d"))
				.setP_drive_permit_place(resultSet.getString("p_drive_permit_place"))
				.setP_agreement(resultSet.getString("p_agreement"))
				.setSign_100(resultSet.getString("sign_100")).setJ_327(resultSet.getString("j_327")).createCustomer();
	}
}
