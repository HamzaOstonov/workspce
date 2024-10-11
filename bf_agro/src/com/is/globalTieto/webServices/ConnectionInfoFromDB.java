package com.is.globalTieto.webServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.is.globalTieto.Constants;
import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.globalTieto.tietoModels.WSConnectionInfo;
import com.is.utils.CheckNull;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConnectionInfoFromDB implements ConnectionInfoHandler {
	private static final Logger logger = Logger.getLogger(ConnectionInfoFromDB.class);

	@Override
	public WSConnectionInfo getWSConnectionInfo() {

		WSConnectionInfo connectionInfo = null;

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String value;
		String name;

		try {
			connectionInfo = new WSConnectionInfo();

			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select name, value from bf_ti_connection_sets where name in (?, ?, ?, ?, ?)");
			ps.setString(1, Constants.FIELD_HOST);
			ps.setString(2, Constants.FIELD_LOGIN);
			ps.setString(3, Constants.FIELD_PASSWORD);
			ps.setString(4, Constants.FIELD_GROUPC);
			ps.setString(5, Constants.FIELD_BANK_C);

			rs = ps.executeQuery();

			while (rs.next()) {
				value = rs.getString("value");
				name = rs.getString("name");

				if (name.equals(Constants.FIELD_HOST)) {
					connectionInfo.setHost(value);
				}
				if (name.equals(Constants.FIELD_LOGIN)) {
					connectionInfo.setLogin(value);
				}
				if (name.equals(Constants.FIELD_PASSWORD)) {
					connectionInfo.setPassword(value);
				}
				if (name.equals(Constants.FIELD_BANK_C)) {
					connectionInfo.setBankC(value);
				}
				if (name.equals(Constants.FIELD_GROUPC)) {
					connectionInfo.setGroupC(value);
				}
			}

		} catch (Exception e) {
			logger.error(CheckNull.getPstr(e));
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				logger.error(CheckNull.getPstr(e));
			}
		}

		return connectionInfo;
	}

}
