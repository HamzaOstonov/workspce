package com.is.uzcard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;
import com.is.uzcard.model.AppList;
import com.is.uzcard.model.Credential;
import com.is.uzcard.model.InOutFile;

public class ModelService {

	public static List<AppList> getBTRTAppList(Credential credential, String clientId) {
		Connection connection = null;
		List<AppList> result = new ArrayList<AppList>();

		try {
			connection = ConnectionPool.getConnection(credential.getUn(), credential.getPwd(), credential.getAlias());
			PreparedStatement pStatement = connection.prepareStatement(Constants.SQL.btrt01appList);
			pStatement.setString(1, credential.getBranch());
			pStatement.setString(2, clientId);

			ResultSet rSet = pStatement.executeQuery();
			while (rSet.next()) {
				AppList appList = new AppList();
				appList.setApp_id(rSet.getLong("c_app_id"));
				appList.setBranch(rSet.getString("branch"));
				appList.setClient(rSet.getString("customer_id"));
				// appList.setError("");
				appList.setFio(rSet.getString("customer_desc"));
				appList.setState_name(rSet.getString("s_name"));
				result.add(appList);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(connection);
		}
		return result;
	}

	public static List<InOutFile> getInOutFileByAppId(Credential credential, String appId) {
		Connection connection = null;
		List<InOutFile> list = new ArrayList<InOutFile>();

		try {
			connection = ConnectionPool.getConnection(credential.getUn(), credential.getPwd(), credential.getAlias());
			PreparedStatement pStatement = connection.prepareStatement(
					"select a.app_id,o.file_name o_file_name,o.date_out,i.file_name i_file_name,i.date_in"
							+ "from card_applications a,card_file_out o, card_file_in i"
							+ "where a.branch=? and a.app_id=? and a.app_type='BTRT01' and  a.state in (3,4,5) and  a.file_out_id=o.id(+)  and a.file_in_id=i.id(+)");
			ResultSet rSet = pStatement.executeQuery();
			if(rSet.next()){
				InOutFile inOutFile = new InOutFile();
				inOutFile.setAppID(rSet.getString("app_id"));
				inOutFile.setSentFileName(rSet.getString("o_file_name"));
				inOutFile.setSendDate(rSet.getDate("date_out"));
				inOutFile.setReceivedFileName(rSet.getString("i_file_name"));
				inOutFile.setReceiveDate(rSet.getDate("date_in"));
				list.add(inOutFile);
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(connection);
		}
		return list;
	}
}
