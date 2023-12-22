package com.is.payments.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.customer_.core.ConnectionUtils;
import com.is.customer_.core.model.SessionAttributes;
import com.is.customer_.core.utils.GeneralUtils;
import com.is.payments.entity.BudgetAccount;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import com.is.utils.RefData;
import com.is.utils.RefDataService;

public class PaymentService {
	private Logger logger = Logger.getLogger(PaymentService.class);

	private SessionAttributes sessionAttributes;

	private PaymentService(SessionAttributes sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}

	public static PaymentService getInstance(SessionAttributes sessionAttributes) {
		return new PaymentService(sessionAttributes);
	}

	public String getSenderAccount() throws SQLException {
		Connection c = null;
		String savingsBankAccount = null;
		try {
			c = ConnectionUtils.getInstance().getConnectionByUsername(sessionAttributes.getUsername(),
					sessionAttributes.getPassword(), sessionAttributes.getSchema());
			String SQL = "select a.acc from " + "			ss_subsidiary_acc a ,ss_subsidiary_user c "
					+ "			where " + "				a.branch = c.branch and "
					+ "				a.code = c.code and " + "             a.cur = '000' and"
					+ "				c.id_user = ?";
			PreparedStatement preparedStatement = c.prepareStatement(SQL);
			preparedStatement.setInt(1, sessionAttributes.getUid());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				savingsBankAccount = resultSet.getString("acc");

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			ConnectionUtils.getInstance().close(c);
		}
		return savingsBankAccount;
	}

    public String getTransitionAccount(int template_id){
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String account = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getUsername(),
                    sessionAttributes.getPassword(),
                    sessionAttributes.getSchema());
            preparedStatement =
                    c.prepareStatement("" +
                            "SELECT ACCOUNT FROM BF_TR_ACC A " +
                                "WHERE A.BRANCH = ? AND A.ACC_TEMPLATE_ID = ?");
            preparedStatement.setString(1,sessionAttributes.getBranch());
            preparedStatement.setInt(2,template_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                account = resultSet.getString(1);
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            ConnectionPool.close(c);
        }
        return account;
    }

    public String getAccountName(String account,String branch){
        Connection c = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String accountName = null;
        try{
            c = ConnectionUtils.getInstance().getConnectionByBranch(branch);
            callableStatement = c.prepareCall("{call info.init()}");
            callableStatement.execute();
            callableStatement = c.prepareCall("{? = call card_back_transactions.getnameandinn(?,?)}");
            callableStatement.registerOutParameter(1,java.sql.Types.VARCHAR);
            callableStatement.setString(2,account);
            callableStatement.setString(3,branch);
            callableStatement.execute();
            accountName = callableStatement.getString(1);
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
        }
        finally{
            try {
                GeneralUtils.closeStatement(callableStatement);
                GeneralUtils.closeResultSet(resultSet);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            ConnectionPool.close(c);
        }
        return accountName;
    }

	public List<RefData> getCashSymbols() {
		/*String SQL = "select a.simv_id data,a.simv_name label from s_kassa a"
				+ " where a.nci_id = \'44\' "
				+ "and a.valut = \'0\' "
				+ "and a.simv_id in (\'29\',\'32\',\'04\',\'06\',\'07\',\'08\',\'11\',\'14\') "
				+ "and a.desc_code = \'00\'";*/
        String SQL = "select a.simv_id data,a.simv_id || ' ' || a.simv_name label " +
                "from s_kassa a where a.nci_id = '44' and a.valut = '0' and a.desc_code = '00'";
		return RefDataService.getRefData(SQL, sessionAttributes.getSchema());
	}

	public List<RefData> getCashSymbolDescriptions(String cashSymbol) {
		// TODO Auto-generated method stub
		String SQL = "SELECT A.DESC_CODE DATA,A.DESC_CODE || ' ' || A.SIMV_NAME LABEL "
                + "FROM S_KASSA A " + "WHERE "
				+ "A.SIMV_ID = \'" + cashSymbol + "\'" + " and VALUT = 0";
		return RefDataService.getRefData(SQL, sessionAttributes.getSchema());
	}

    public List<RefData> getPurposeCodes() {
        return RefDataService.getPurposeCode(sessionAttributes.getSchema());
    }

    public List<RefData> getBranches() throws Exception {
        List<RefData> list = new ArrayList<RefData>();
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection(sessionAttributes.getSchema());
            preparedStatement = c.prepareStatement("SELECT BANK_ID DATA,BANK_NAME LABEL FROM S_MFO WHERE ACT <> 'Z'");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new RefData(resultSet.getString("data"),resultSet.getString("label")));
            }
        }
        catch (Exception e){
            logger.error(CheckNull.getPstr(e));
            throw e;
        }
        finally {
            GeneralUtils.closeResultSet(resultSet);
            GeneralUtils.closeStatement(preparedStatement);
            ConnectionPool.close(c);
        }
        return list;
    }

    public String getCentralBankAccountName(String name_){
        String name = null;
        Connection c = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            c = ConnectionPool.getConnection();
            preparedStatement = c.prepareStatement("SELECT * FROM S_KAZNSPR WHERE ");
        } catch (SQLException e) {
            logger.error(CheckNull.getPstr(e));
        }
        finally {
            DbUtils.closeResultSet(null);
            ConnectionPool.close(c);
        }
        return name;
    }
}
