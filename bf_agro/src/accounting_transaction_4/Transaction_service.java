package accounting_transaction_4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;

public class Transaction_service {
	private Connection c;
	private Bf_tr_acc_DAO bf_tr_acc_dao;
	private Bf_tr_template_DAO bf_tr_template_dao;
	private Bf_tr_pay_DAO bf_tr_pay_dao;
	private Bf_tr_paydoc_DAO bf_tr_paydoc_dao;
	private Util_DAO util_dao;
	private String current_branch;
	private static Pattern p_tag = Pattern.compile("(<@.+?@>)");

	public Long create_pay(Long operation_id, String branch, Long amount, Long parent_id, Long parend_group_id,
			Integer parent_deal_id, Map<String, Object> parameters) throws SQLException, CloneNotSupportedException {
		this.current_branch = branch;
		Long tr_pay_id = bf_tr_pay_dao.get_next_id();
		Bf_tr_template[] templates = bf_tr_template_dao.get_templates_for_operation(operation_id);
		if (isDbConnected(c))
			System.out.println(" -01 cn=open");
		
		for (int i = 0; i < templates.length; i++) {
			Long paydoc_id = bf_tr_paydoc_dao.get_next_id();
			Long current_amount = amount;
			Bf_tr_template bf_tr_template = templates[i];
            
			Bf_tr_acc bf_tr_acc_ct = (Bf_tr_acc) bf_tr_acc_dao.get_bf_tr_acc(branch, bf_tr_template.getAcc_ct())
					.clone();
			Bf_tr_acc bf_tr_acc_dt = (Bf_tr_acc) bf_tr_acc_dao.get_bf_tr_acc(branch, bf_tr_template.getAcc_dt())
					.clone();
			if (isDbConnected(c))
				System.out.println(" -02 cn=open"+" -"+i);

			bf_tr_acc_ct = fill_acc_parameters(bf_tr_acc_ct, parameters);
			if (isDbConnected(c))
				System.out.println(" -03 cn=open"+" -"+i);
			
			bf_tr_acc_dt = fill_acc_parameters(bf_tr_acc_dt, parameters);
			if (isDbConnected(c))
				System.out.println(" -04 cn=open"+" -"+i);

			bf_tr_template = fill_template_parameters(bf_tr_template, parameters);
			if (isDbConnected(c))
				System.out.println(" -05 cn=open"+" -"+i);

			/*
			 * if(bf_tr_template.getAmount()!=null &&
			 * bf_tr_template.getAmount().matches(".*[a-zA-Z]+.*"))
			 * if(parameters.containsKey(bf_tr_template.getAmount()))
			 * current_amount =
			 * ((Long)parameters.get(bf_tr_template.getAmount())*bf_tr_template.
			 * getPerc_for_tr())/100; else throw new NoSuchElementException(
			 * "value for parameter "+bf_tr_template.getAmount()+
			 * " not specified");
			 */

			// hamza edites
			if (bf_tr_template.getAmount() != null) {
				if (bf_tr_template.getAmount().matches(".*[a-zA-Z]+.*")) {
					if (parameters.containsKey(bf_tr_template.getAmount())) {
						Double tmp = ((Double) parameters.get(bf_tr_template.getAmount())
								* bf_tr_template.getPerc_for_tr()) / 100;
						current_amount = tmp.longValue();
					} else
						throw new NoSuchElementException(
								"value for parameter " + bf_tr_template.getAmount() + " not specified");
				}
				// else
				Double tmp = (new Double(bf_tr_template.getAmount()) * bf_tr_template.getPerc_for_tr()) / 100;
				current_amount = tmp.longValue();
			} else {
				Double tmp = (current_amount.doubleValue() * bf_tr_template.getPerc_for_tr()) / 100;
				current_amount = tmp.longValue();
			}
			if (isDbConnected(c))
				System.out.println(" -06 cn=open"+" -"+i);

			Matcher m = p_tag.matcher(bf_tr_template.getPurpose());
			String res_purpose = bf_tr_template.getPurpose();
			while (m.find()) {
				res_purpose = res_purpose.replace(m.group(1),
						(String) parameters.get(m.group(1).substring(2, m.group(1).length() - 2)));
			}

			long deal_group = 0l;
			if (bf_tr_template.getPay_type().intValue() == 1)
				deal_group = 3l;
			else if (bf_tr_template.getPay_type().intValue() == 2)
				deal_group = 89l;
			else if (bf_tr_template.getPay_type().intValue() == 3)
				deal_group = 14l;
			else
				throw new IllegalArgumentException("Wrong pay type:" + Integer.toString(bf_tr_template.getPay_type()));
			if (!bf_tr_acc_dt.getAcc_mfo().equals(bf_tr_acc_ct.getAcc_mfo())
					&& !bf_tr_acc_dt.getAccount().substring(5, 8).equals("000")
					&& !bf_tr_acc_ct.getAccount().substring(5, 8).equals("000"))
				deal_group = 89l;

			Long deal_id = null;
			if (deal_group == 3l) {
				if (!branch.equals(bf_tr_acc_dt.getAcc_mfo())) {
					util_dao.st.executeUpdate(
							"ALTER SESSION SET CURRENT_SCHEMA=" + util_dao.get_schema_name(bf_tr_acc_dt.getAcc_mfo()));
					util_dao.infoInit();
				}
				deal_id = util_dao.get_deal_general(bf_tr_template.getDoc_type(), bf_tr_acc_dt.getAcc_mfo(),
						bf_tr_acc_ct.getAcc_mfo());
				if (!branch.equals(bf_tr_acc_dt.getAcc_mfo())) {
					util_dao.st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + util_dao.get_schema_name(branch));
					util_dao.infoInit();
				}
			} else if (deal_group == 89l)
				deal_id = 1l;
			else
				deal_id = bf_tr_template.getTrans_type();

			System.out.println("deal_id " + deal_id + " deal_group " + deal_group + " bank_cl "
					+ bf_tr_acc_dt.getAcc_mfo() + " acc_cl " + bf_tr_acc_dt.getAccount());

			Bf_tr_paydoc paydoc = new Bf_tr_paydoc(paydoc_id, // id,
					tr_pay_id, // pay_id,
					bf_tr_acc_dt.getAcc_mfo().equals(branch) ? branch : bf_tr_acc_dt.getAcc_mfo(), // branch,
					util_dao.get_oper_date(branch), // d_date,
					bf_tr_acc_dt.getAcc_mfo(), // bank_cl,
					bf_tr_acc_dt.getAccount(), // acc_cl,
					(bf_tr_acc_dt.getAcc_name_and_inn() == null
							? util_dao.get_name_and_inn(bf_tr_acc_dt.getAcc_mfo(), bf_tr_acc_dt.getAccount(), branch)
							: bf_tr_acc_dt.getAcc_name_and_inn()), // name_cl,
					bf_tr_acc_ct.getAcc_mfo(), // bank_co,
					bf_tr_acc_ct.getAccount(), // acc_co,
					(bf_tr_acc_ct.getAcc_name_and_inn() == null
							? util_dao.get_name_and_inn(bf_tr_acc_ct.getAcc_mfo(), bf_tr_acc_ct.getAccount(), branch)
							: bf_tr_acc_ct.getAcc_name_and_inn()), // name_co,
					current_amount, // summa,
					bf_tr_template.getPurpose_code() + res_purpose, // purpose,
					bf_tr_template.getDoc_type(), // type_doc,
					bf_tr_template.getPdc(), // pdc,
					parend_group_id, // parent_group_id,
					parent_id, // parent_id,
					bf_tr_template.getCash_code(), // cash_code,
					(bf_tr_template.getId_transh_purp() == null ? 0
							: Long.parseLong(bf_tr_template.getId_transh_purp())), // id_transh_purp,
					null, // schema_name,
					(long) i, // ord,
					bf_tr_acc_dt.getAcc_mfo().equals(branch) ? branch : bf_tr_acc_dt.getAcc_mfo(), // g_branch,
					null, // g_docid,
					bf_tr_template.getPurpose_code(), // purp_code,
					0l, // pay_type,
					0l, // trans_type,
					0l, // acc_dt_id,
					0l, // acc_ct_id,
					deal_group, // deal_group_id,
					deal_id, // deal_id,
					parent_deal_id// parent_deal_id
			);
			bf_tr_paydoc_dao.insert(paydoc);
		}
		return tr_pay_id;
	}

	private Bf_tr_acc fill_acc_parameters(Bf_tr_acc bf_tr_acc, Map<String, Object> parameters) {
		if (!bf_tr_acc.getAcc_mfo().matches("[0-9]+"))
			bf_tr_acc.setAcc_mfo((String) parameters.get(bf_tr_acc.getAcc_mfo()));
		if (bf_tr_acc.getAcc_mfo() == null)
			throw new NoSuchElementException("value for parameter " + bf_tr_acc.getAcc_mfo() + " not specified");

		if (!bf_tr_acc.getAccount().matches("[0-9]+"))
			bf_tr_acc.setAccount((String) parameters.get(bf_tr_acc.getAccount()));
		if (bf_tr_acc.getAccount() == null)
			throw new NoSuchElementException("value for parameter " + bf_tr_acc.getAccount() + " not specified");

		return bf_tr_acc;
	}

	private Bf_tr_template fill_template_parameters(Bf_tr_template bf_tr_template, Map<String, Object> parameters) {
		if (bf_tr_template.getCash_code() != null && !bf_tr_template.getCash_code().matches("[0-9]+"))
			if (parameters.get(bf_tr_template.getCash_code()) == null)
				throw new NoSuchElementException(
						"value for parameter " + bf_tr_template.getCash_code() + " not specified");
			else
				bf_tr_template.setCash_code((String) parameters.get(bf_tr_template.getCash_code()));

		if (bf_tr_template.getId_transh_purp() != null && !bf_tr_template.getId_transh_purp().matches("[0-9]+"))
			if (parameters.get(bf_tr_template.getId_transh_purp()) == null)
				throw new NoSuchElementException(
						"value for parameter " + bf_tr_template.getId_transh_purp() + " not specified");
			else
				bf_tr_template.setId_transh_purp((String) parameters.get(bf_tr_template.getId_transh_purp()));

		return bf_tr_template;
	}

	public void execute_transaction_action(String branch, Long bf_tr_pay_id, Integer action_id) throws SQLException {
		Bf_tr_paydoc[] bf_tr_paydocs = bf_tr_paydoc_dao.get_paydocs_by_pay(bf_tr_pay_id);
		String current_branch = null;
		for (int i = 0; i < bf_tr_paydocs.length; i++) {
			Bf_tr_paydoc paydoc = bf_tr_paydocs[i];

			// if(!paydoc.getG_branch().equals(current_branch))
			// {
			// util_dao.st.executeUpdate("ALTER SESSION SET
			// CURRENT_SCHEMA=bank053");
			util_dao.st.executeUpdate(
					"ALTER SESSION SET CURRENT_SCHEMA=" + util_dao.get_schema_name(paydoc.getG_branch()));
			// }
			util_dao.clearparam();

			current_branch = paydoc.getG_branch();

			if (paydoc.getG_docid() != null && paydoc.getG_docid() > 0)
				util_dao.setparam("ID", paydoc.getG_docid());
			util_dao.setparam("ROOT_TRANS_DEAL_GROUP", paydoc.getParent_group_id());
			util_dao.setparam("BRANCH", paydoc.getBranch());
			util_dao.setparam("D_DATE", paydoc.getD_date());
			util_dao.setparam("BANK_CL", paydoc.getBank_cl());
			util_dao.setparam("ACC_CL", paydoc.getAcc_cl());
			util_dao.setparam("NAME_CL", paydoc.getName_cl());
			util_dao.setparam("BANK_CO", paydoc.getBank_co());
			util_dao.setparam("PDC", paydoc.getPdc());
			util_dao.setparam("CURRENCY", paydoc.getAcc_cl().substring(5, 8));
			util_dao.setparam("ACC_CO", paydoc.getAcc_co());
			util_dao.setparam("NAME_CO", paydoc.getName_co());
			util_dao.setparam("PURPOSE", paydoc.getPurpose());
			util_dao.setparam("S_DEAL_ID", paydoc.getDeal_id());// --------------
			util_dao.setparam("FX_DEAL", paydoc.getDeal_id());// ----------------
			util_dao.setparam("DEAL_ID", paydoc.getDeal_id());// ----------------
			util_dao.setparam("NAZ_PLAT", paydoc.getPurp_code());

			util_dao.setparam("PURPOSE1",
					paydoc.getPurpose().length() > 35 ? paydoc.getPurpose().substring(0, 35) : paydoc.getPurpose());
			if (paydoc.getPurpose().length() > 35)
				util_dao.setparam("PURPOSE2", paydoc.getPurpose().length() > 70 ? paydoc.getPurpose().substring(35, 70)
						: paydoc.getPurpose().substring(35));
			if (paydoc.getPurpose().length() > 70)
				util_dao.setparam("PURPOSE3", paydoc.getPurpose().length() > 105
						? paydoc.getPurpose().substring(70, 105) : paydoc.getPurpose().substring(70));
			if (paydoc.getPurpose().length() > 105)
				util_dao.setparam("PURPOSE4", paydoc.getPurpose().length() > 140
						? paydoc.getPurpose().substring(105, 140) : paydoc.getPurpose().substring(105));

			util_dao.setparam("CASH_CODE", paydoc.getCash_code());
			util_dao.setparam("SUMMA", paydoc.getSumma());
			util_dao.setparam("V_DATE", paydoc.getD_date());
			util_dao.setparam("TYPE_DOC", paydoc.getType_doc());
			util_dao.setparam("ID_TRANSH", "00".substring(paydoc.getId_transh_purp().toString().length())
					+ paydoc.getId_transh_purp().toString());
			util_dao.setparam("ID_TRANSH_PURP", "00".substring(paydoc.getId_transh_purp().toString().length())
					+ paydoc.getId_transh_purp().toString());
			util_dao.setparam("S_DEAL_ID", paydoc.getDeal_id());
			util_dao.setparam("PARENT_GROUP_ID", paydoc.getParent_group_id());
			util_dao.setparam("PARENT_ID", paydoc.getParent_id());
			long course;
			long co_summa;
			long nn_summa;
			int course_type = 1;
			if (paydoc.getDeal_group_id() == 14) {
				if (paydoc.getAcc_cl().substring(5, 8).equals("000")) {
					nn_summa = paydoc.getSumma();
					course_type = GetCourceType(paydoc.getBranch(), paydoc.getDeal_id().intValue(),
							paydoc.getAcc_co().substring(5, 8));
					co_summa = getEqual(nn_summa, paydoc.getAcc_co().substring(5, 8),
							paydoc.getAcc_cl().substring(5, 8), course_type);
					course = co_summa / nn_summa;
					
					System.out.println("1 co_summa= " + co_summa + " , nn_summa= " +nn_summa + ", course_type= " +course_type +", course="+course+". * "+paydoc.getAcc_cl().substring(5, 8)+"-"+paydoc.getAcc_co().substring(5, 8));
					
					util_dao.setparam("CL_CURRENCY", paydoc.getAcc_cl().substring(5, 8));
					util_dao.setparam("CL_SUMMA", co_summa);
					util_dao.setparam("COURSE", course);
					util_dao.setparam("CO_CURRENCY", paydoc.getAcc_co().substring(5, 8));
					util_dao.setparam("CO_SUMMA", nn_summa);
				} else {
					nn_summa = paydoc.getSumma();
					course_type = GetCourceType(paydoc.getBranch(), paydoc.getDeal_id().intValue(),
							paydoc.getAcc_cl().substring(5, 8));
					co_summa = getEqual(nn_summa, paydoc.getAcc_cl().substring(5, 8),
							paydoc.getAcc_co().substring(5, 8), course_type);
					course = co_summa / nn_summa;
					
					System.out.println("2 co_summa= " + co_summa + " , nn_summa= " +nn_summa + ", course_type= " +course_type +", course="+course+". * "+paydoc.getAcc_cl().substring(5, 8)+"-"+paydoc.getAcc_co().substring(5, 8));

					util_dao.setparam("CL_CURRENCY", paydoc.getAcc_cl().substring(5, 8));
					util_dao.setparam("CO_CURRENCY", paydoc.getAcc_co().substring(5, 8));
					util_dao.setparam("CL_SUMMA", nn_summa);
					util_dao.setparam("CO_SUMMA", co_summa);
					util_dao.setparam("COURSE", course);
				}
				util_dao.setparam("CO_ACC", paydoc.getAcc_co());
				util_dao.setparam("CO_NAME", paydoc.getName_co());
				util_dao.setparam("CL_ACC", paydoc.getAcc_cl());
				util_dao.setparam("CL_NAME", paydoc.getName_cl());
				util_dao.setparam("CL_VDATE", paydoc.getD_date());
				util_dao.setparam("CO_VDATE", paydoc.getD_date());
				util_dao.setparam("FX_DEAL", paydoc.getDeal_id());
			}

			util_dao.execute_setparam_batch();

			// CallableStatement ps = c.prepareCall("{ ? = call
			// param.Get_table}");
			// ps.registerOutParameter(1, java.sql.Types.VARCHAR);
			// ps.execute();
			// ISLogger.getLogger().error(ps.getString(1));

			//////////////////////

			util_dao.st.executeUpdate(
					"ALTER SESSION SET CURRENT_SCHEMA=" + util_dao.get_schema_name(paydoc.getG_branch()));
			util_dao.infoInit();
			/*
			 * paydoc.setDeal_id(util_dao.get_deal_general(
			 * paydoc.getType_doc(),paydoc.getBank_cl(),paydoc.getBank_co()));
			 */
			/////////////////////////////////

			if (paydoc.getDeal_group_id() == 3 && (!paydoc.getBank_co().equals(paydoc.getBank_cl()))) {
				action_id = 21;
			} else {
				action_id = 19;
			}
			try {
				util_dao.doaction(paydoc.getDeal_group_id(),
						paydoc.getDeal_group_id() == 14 ? 1 : paydoc.getDeal_id().intValue(), action_id);
				util_dao.st.executeUpdate("ALTER SESSION SET CURRENT_SCHEMA=" + util_dao.get_schema_name(branch));
				util_dao.infoInit();

			} catch (SQLException e) {
				System.out.println("doAction parameters, schema = " + util_dao.get_schema_name(paydoc.getG_branch())
						+ " first " + paydoc.getDeal_group_id() + " second = " + paydoc.getDeal_id() + " third = "
						+ action_id + "\n" + paydoc.toString());
				ISLogger.getLogger().error("doAction parameters, first = " + paydoc.getDeal_group_id() + " second = "
						+ paydoc.getDeal_id() + " third = " + action_id + "\n" + paydoc.toString());
				throw e;
			}
		}
	}

	public long getEqual(Long summa_, String curr_orig, String curr_eq, int courceType) {
		//Connection c = null;
		long result = 1;
		try {

			//c = ConnectionPool.getConnection();
			// PreparedStatement ps = c.prepareStatement("{call
			// info.initday(sysdate)}");
			// ps.executeUpdate();
			PreparedStatement ps = null;

			ps = c.prepareStatement("select Info.GetEqual(?,?,?,?) res from dual");
			ps.setLong(1, summa_);
			ps.setString(2, curr_orig);
			ps.setString(3, curr_eq);
			ps.setLong(4, courceType);

			ResultSet rs = ps.executeQuery();
			rs.next();
			result = rs.getLong("res");
			ps.close();
			rs.close();

		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			//ConnectionPool.close(c);
		}
		return result;
	}

	public Transaction_service(Connection c) throws SQLException {
		this.c = c;
		this.bf_tr_acc_dao = new Bf_tr_acc_DAO(this.c);
		this.bf_tr_template_dao = new Bf_tr_template_DAO(this.c);
		this.bf_tr_pay_dao = new Bf_tr_pay_DAO(this.c);
		this.bf_tr_paydoc_dao = new Bf_tr_paydoc_DAO(this.c);
		this.util_dao = new Util_DAO(this.c);
	}

	public void close() {
		this.bf_tr_acc_dao.close();
		this.bf_tr_template_dao.close();
		this.bf_tr_pay_dao.close();
		this.bf_tr_paydoc_dao.close();
		this.util_dao.close();
	}

	public static int GetCource(String currency, int courceType) {
		Connection c1 = null;
		int result = 1;
		try {

			c1 = ConnectionPool.getConnection();
			PreparedStatement ps = c1.prepareStatement("{call info.init()}");
			ps.executeUpdate();
			ps = c1.prepareStatement("select Info.GetEqual(1,?,'000',?) state from dual");
			ps.setString(1, currency);
			ps.setInt(2, courceType);
			ResultSet rs = ps.executeQuery();
			rs.next();
			result = rs.getInt("state");
			ps.close();
			rs.close();

		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {

			ConnectionPool.close(c1);
		}
		return result;
	}

	public static int GetCourceType(String br, int dealId, String currency) {
		Connection c1 = null;
		PreparedStatement ps = null;
		int result = 1;
		try {
			c1 = ConnectionPool.getConnection();
			ps = c1.prepareStatement(
					"select d.course_type from deal_fxdoc d where d.branch=? and d.deal_id=? and d.currency=?");
			ps.setString(1, br);
			ps.setInt(2, dealId);
			ps.setString(3, currency);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt("course_type");
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c1);
		}
		return result;
	}

	public boolean isDbConnected(Connection con) {
	    try {
	        if(!con.isClosed() || con!=null){
	            return true;
	        }
	    } catch (SQLException e) {
	        return false;
	    }
	    return false;
	}

}
