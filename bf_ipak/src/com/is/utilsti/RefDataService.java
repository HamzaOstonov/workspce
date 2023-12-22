package com.is.utilsti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;

public class RefDataService {
	private static HashMap<String, String> _tstopCauses = null;
	private static HashMap<String, String> _trState = null;

	public static HashMap<String, String> getHTstopCauses() {
		if (_tstopCauses == null) {
			_tstopCauses = getHRefTData("select cause data,name label from izd_stop_causes where bank_c='01'  order by cause");
		}
		return _tstopCauses;
	}

	/*
	 * public static List<RefData> get_deal_id(String branch, int group_id) {
	 * return getRefData(
	 * "select id data, id||'  '||name label from ss_deal where group_id = ?",
	 * Integer.toString(group_id), branch); }
	 */

	public static List<RefData> get_deal_id(String branch, int group_id) {
		return getRefData(
				"select id data, id||'  '||name label from ss_deal where group_id = ?",
				Integer.toString(group_id), branch);
	}

	public static List<RefData> get_payments_states(String alias) {
		return getRefData(
				"Select TP.STATE||'k'||TP.DEAL_ID||'j'||op.id as data,"
						+ " OP.DESCRIPPTION ||  ' - ' ||st.name as label"
						+ " From   BF_TR_OPERATIONS OP,"
						+ " BF_TR_PAY        TP,"
						+ " bf_tr_state      st"
						+ " Where  TP.DEAL_GROUP = 144"
						+ " and TP.OPERATION_ID = OP.ID"
						+ " and st.deal_id = tp.deal_id"
						+ " and st.id = tp.state"
						+ " group by tp.state,tp.deal_id,op.id ,st.name, op.descripption"
						+ " order by op.id", alias);
		// return
		// getRefData("select st.id||'k'||st.deal_id data, st.name label from bf_tr_state st where st.flag_client_view = st.flag_client_view order by data",
		// alias);
	}

	public static List<RefData> get_ipak_Mfo(String branch) {
		return getRefData(
				"select smf.bank_id data, smf.bank_id||' '||smf.bank_name label from s_mfo smf where smf.bank_type = (select smf1.bank_type from s_mfo smf1 where smf1.bank_id =  (select VALUE from bf_sets bs where bs.id ='HO'))",
				branch);
	}

	public static List<RefData> get_operation_type(String branch) {
		return getRefData(
				"select t.id data, t.name label from BF_TR_PAYTYPE t", branch);
	}

	public static List<RefData> get_rounding_type(String branch) {
		return getRefData(
				"select t.id data, t.name label from bf_rounding_type t",
				branch);
	}

	public static List<RefData> get_sub_operation_type(String branch, int arg2) {
		return getRefData(
				"select t.VALUE data, t.LABEL label from V_BF_SS_PAYTYPE t where t.PAYTYPE = ?",
				Integer.toString(arg2), branch);
	}

	public static List<RefData> get_sub_operation_type(String branch) {
		return getRefData(
				"select t.VALUE data, t.LABEL label from V_BF_SS_PAYTYPE t",
				branch);
	}

	public static List<RefData> getPurposeCode(String alias) {
		return getRefData(
				"select '00000' as data,'Без кода назначения платежа' as label from dual union all select code_plat data, code_plat||'      ('||name_plat||')' label from S_NAZN where ACT='A'",
				alias);
	}

	public static List<RefData> getTypeDoc(String alias) {
		return getRefData(
				"select td1.kod_doc data, td1.kod_doc||'  '||td1.naim_doc label from S_TYPEDOC td1 where ACT='A' union all select td2.kod_doc data, td2.kod_doc||'  '||td2.naim_doc label from SS_TYPEDOC td2 where ACT='A' order by data",
				alias);
	}

	public static List<RefData> getCurrency(String alias) {
		return getRefData(
				"select kod data, kod||'  '||kod_b||'  '||namev label from S_VAL order by data",
				alias);
	}

	// //////////////////////////////////////////

	public static List<RefData> getOfrProd(String branch) {
		return getRefData(
				"select code data, name label from BF_TIETO_CARD_SETTING where flag_user_view='1'  order by code",
				branch);
	}

	/*
	 * public static List<RefData> getOfrProd(String branch, String client_type)
	 * { return getRefData(
	 * "select code data, name label from BF_TIETO_CARD_SETTING where flag_user_view='1' and FLAG_CLIENT_TYPE='"
	 * +client_type+"' order by code", branch); }
	 */

	public static List<RefData> getOfrProd(String branch, String client_type,
			String user_branch, int uid) {
		return getRefData(
				"select c.code data, c.name label from BF_TIETO_CARD_SETTING c where c.flag_user_view='1' and c.FLAG_CLIENT_TYPE='"
						+ client_type
						+ "' "
						+ " and c.code in ("
						+ " select ca.card_code from BF_TIETO_CARD_ACTIONS ca where exists"
						+ " (select a.* from bf_actions a where a.mid=12 and a.deal_id=4 and"
						+ " exists(select null from bf_role_actions r , bf_user_roles ur"
						+ " where r.mid=a.mid "
						+ " and r.actionid=a.id"
						+ " and r.deal_id=4"
						+ " and ur.roleid = r.roleid and ur.branch = '"
						+ user_branch
						+ "' and ur.userid = "
						+ uid
						+ " "
						+ " ) and a.id = ca.action_id and a.deal_id = ca.deal_id))",
				branch);
	}

	public static List<RefData> getService(String alias) {
		return getRefData(
				"select id data, name label from BF_COM_SERVICES_LIST order by data",
				alias);
	}

	public static List<RefData> getService(String Cust_id, String alias) {
		return getRefData(
				"select id data, name label from BF_COM_SERVICES_LIST where customerj_id='"
						+ Cust_id + "' order by data", alias);
	}

	public static List<RefData> getTstopCauses() {
		return getRefTData("select cause data,name label from izd_stop_causes where bank_c='01'  order by cause");
	}

	public static List<RefData> getFieldState(String alias) {
		return getRefData(
				"select id data, description label from bf_com_mask_states order by data",
				alias);
	}

	public static List<RefData> getCategory(String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CATEGORY order by data",
				alias);
	}

	public static List<RefData> getCustomer(String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CUSTOMERS order by data",
				alias);
	}

	// ///////////////////////////////////
	public static List<RefData> getCustomerDist(String dist_id, String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CUSTOMERS where distr='"
						+ dist_id + "' order by data", alias);
	}

	public static List<RefData> getCustomerRegion(String reg_id, String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CUSTOMERS where region='"
						+ reg_id + "' order by data", alias);
	}

	// ////////
	public static List<RefData> getCustomerDistCat(String dist_id,
			String cat_id, String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CUSTOMERS where distr='"
						+ dist_id + "' and category_id='" + cat_id
						+ "' order by data", alias);
	}

	public static List<RefData> getCustomerRegionCat(String reg_id,
			String cat_id, String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CUSTOMERS where region='"
						+ reg_id + "' and category_id='" + cat_id
						+ "' order by data", alias);
	}

	public static List<RefData> getCustomerCat(String cat_id, String alias) {
		return getRefData(
				"select id data, name label from BF_COM_CUSTOMERS where category_id='"
						+ cat_id + "' order by data", alias);
	}

	// /////////////////////////////////////

	public static List<RefData> getTrState(String branch) {
		return getRefData(
				"select id data,name label from BF_TR_STATE order by id",
				branch);
	}

	public static HashMap<String, String> getHTrState(String branch) {
		if (_trState == null) {
			_trState = getHRefData(
					"select id||'v'||deal_id data,name label from BF_TR_STATE order by id",
					branch);
		}
		return _trState;
	}

	public static List<RefData> getCurrAcc(String key, String branch) {
		return getRefData(
				"select id data,id||' '||name label from account where acc_bal='20206' and currency='840' and client=? and state=2",
				key, branch);
	}

	public static List<RefData> getCardAcc(String key, String branch) {
		return getRefData(
				"select id data,id||' '||name label from account where acc_bal='22618' and currency='840' and client=? and state=2",
				key, branch);
	}

	public static List<RefData> getCurrAccUZS(String branch, String id_client, String alias) {
		return getRefData(
				"select id data,id||' '||name label from account where branch=? and acc_bal='20206' and currency='000' and client=? and acc_group_id=100 and state=2",
				branch, id_client, alias);
	}

	public static List<RefData> getAccTmpl(String branch) {
		return getRefData(
				"select id data, acc_name label from BF_TR_ACC_TEMPLATE  order by id",
				branch);
	}

	public static List<RefData> getOfrProd() {
		return getRefTData("select code data, name label from izd_offered_products where bank_c='01'  and groupc='02'  order by code");
	}

	public static List<RefData> getOperation(int parent_group, String branch) {
		return getRefData(
				"select id data, descripption label from BF_TR_OPERATIONS where parent_group_id="
						+ parent_group + "  order by id", branch);
	}

	public static List<RefData> getCountry(String branch) {
		return getRefData(
				"select code_str data, code_str||' '||name label from s_str where act <> 'Z' order by code_str",
				branch);
	}

	public static List<RefData> getTax(String branch) {
		return getRefData(
				"select gni_id data, gni_id||' '||name_gni label from s_gni where act <> 'Z' order by gni_id",
				branch);
	}

	public static List<RefData> getClassCR(String branch) {
		return getRefData(
				"select klass_id data, klass_name label from s_klass where act <> 'Z' order by klass_id",
				branch);
	}

	public static List<RefData> getDistr(String branch) {
		return getRefData(
				"select distr data, distr||' '||distr_name label, region_id from s_distr where act <> 'Z' order by distr",
				branch);
	}

	public static List<RefData> getDistr(String key, String branch) {
		return getRefData(
				"select distr data, distr||' '||distr_name label, region_id from s_distr where act <> 'Z' and region_id=? order by distr",
				key, branch);
	}

	public static List<RefData> getRegion(String branch) {
		return getRefData(
				"select region_id data, region_id||' '||region_nam label from s_region where act <> 'Z' order by region_id",
				branch);
	}

	public static List<RefData> getCapacity(String branch) {
		return getRefData(
				"select kod_kr data, name_kr label from s_krfl where act <> 'Z' order by kod_kr",
				branch);
	}

	public static List<RefData> getGender(String branch) {
		return getRefData(
				"select CODE_SEX data, NAME_SEX label from S_SEX where act<>'Z' order by CODE_SEX",
				branch);
	}

	public static List<RefData> getNation(String branch) {
		return getRefData(
				"select nat_id data, nat_name label from s_nation where act <> 'Z' order by nat_id",
				branch);
	}

	public static List<RefData> getType_document(String branch) {
		return getRefData(
				"select kod_pas data, kod_pas||' '||name_pas label from s_passport where act <> 'Z' order by kod_pas",
				branch);
	}

	public static List<RefData> getType_client(String branch) {
		return getRefData(
				"select kod_k data, name_k2 label from S_TYPEKL where act <> 'Z' order by kod_k",
				branch);
	}

	public static List<RefData> getMfo(String branch) {
		return getRefData(
				"select bank_id data, bank_name label from S_mfo order by bank_id",
				branch);
	}

	public static List<RefData> getMfo_name(String mfo, String branch) {
		return getRefData(
				"select bank_id data, bank_name label from S_mfo where bank_id=?",
				mfo, branch);
	}

	public static List<RefData> getMfo_addr(String mfo, String branch) {
		return getRefData(
				"select bank_id data, bank_adress label from S_mfo where bank_id=?",
				mfo, branch);
	}

	public static List<RefData> getAccess(String branch) {
		return getRefData(
				"select id data, name label from ss_access order by id", branch);
	}

	public static List<RefData> getRezCl(String branch) {
		return getRefData(
				"select kod_rez data, type_rez label from S_REZKL order by kod_rez",
				branch);
	}

	public static List<RefData> getUserState(String branch) {
		return getRefData(
				"select id data, name label from s_user_state order by id",
				branch);
	}

	public static List<RefData> getUsers(String branch) {
		return getRefData(
				"select username data, name label from users order by name",
				branch);
	}

	public static List<RefData> getUsers(String usr, String branch) {
		return getRefData(
				"select username data, name label from users where mngr='"
						+ usr + "' order by name", branch);
	}

	public static List<RefData> get_nation_map(String branch) {
		return getRefData(
				"select trim(t.id) data, trim(t.map) label from ss_national_gcp t order by 1", branch);
	}

	public static List<RefData> getRefData(String sql, String branch) {

		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(branch);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;

	}

	public static List<RefData> getRefData(String sql, String key, String branch) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(branch);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, key);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<RefData> getRefData(String sql, String key1, String key2, String branch) {
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;

		try {
			c = ConnectionPool.getConnection(branch);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, key1);
			ps.setString(2, key2);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static HashMap<String, String> getHRefData(String sql, String branch) {

		HashMap<String, String> list = new HashMap<String, String>();
		Connection c = null;
		try {
			c = ConnectionPool.getConnection(branch);
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.put(rs.getString("data"), rs.getString("label"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static List<RefData> getRefTData(String sql) {

		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		try {
			c = ConnectionPool.getTConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.add(new RefData(rs.getString("data"), rs
						.getString("label")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

	public static HashMap<String, String> getHRefTData(String sql) {

		HashMap<String, String> list = new HashMap<String, String>();
		Connection c = null;
		try {
			c = ConnectionPool.getTConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				list.put(rs.getString("data"), rs.getString("label"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		return list;
	}

}