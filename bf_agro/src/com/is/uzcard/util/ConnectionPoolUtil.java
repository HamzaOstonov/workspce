package com.is.uzcard.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.is.utils.CheckNull;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class duplicates <tt>ConnectionPool</tt> but with more proper implementation.
 */
public class ConnectionPoolUtil implements Serializable {

    // Constants
    private static final String DATA_RESOURCE_NAME = "jdbc/myoracle";

    // Utils
    private static final Logger log = Logger.getLogger(ConnectionPoolUtil.class);

    // Fields
    private static DataSource dataSource;
    private static ConnectionPoolUtil instance;
    private Map<String, String> schemasMap = Collections.synchronizedMap(new HashMap<String, String>());

    static {
        try {
            Context context = new InitialContext();
            Context environmentContext = (Context) context.lookup("java:/comp/env");
            dataSource = (DataSource) environmentContext.lookup(DATA_RESOURCE_NAME);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // avoid to use constructor from the outside
    private ConnectionPoolUtil() {
    }

    public static ConnectionPoolUtil getInstance() {
        if (instance == null) {
            instance = new ConnectionPoolUtil();
        }
        return instance;
    }

    /**
     * Returns plain (cleaned) connection from connection pool.
     * Auto-commit mode is always <tt>false</tt>.
     *
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }
    
    public Connection getConnection(String login, String password) throws SQLException {
		Connection connection = dataSource.getConnection(login, password);
		connection.setAutoCommit(false);
        return connection;
		
	}

    /**
     * Returns an initialized connection that has been set <tt>NLS_DATE_FORMAT</tt>, <tt>CURRENT_SCHEMA</tt>,
     * and <tt>INFO.INIT</tt>.
     *
     * @param schema triggering schema
     * @return properly initialized connection
     */
    public Connection getConnectionBySchema(String schema) throws SQLException {
        Connection connection = getConnection();
        return initBankSession(connection, schema);
    }
    
    
    public Connection getConnectionBySchema(String login, String password, String schema) throws SQLException {
    	Connection connection = getConnection(login, password);
		return initBankSession(connection, schema);
	}
    /**
     * Returns an initialized connection that has been set <tt>NLS_DATE_FORMAT</tt>, <tt>CURRENT_SCHEMA</tt>,
     * and <tt>INFO.INIT</tt>.
     *
     * @param branchId triggering schema
     * @return properly initialized connection
     */
    public Connection getConnectionByBranch(String branchId) throws SQLException {
        return getConnectionBySchema(findSchemaByBranch(branchId));
    }
    
    public Connection getConnectionByBranch(String login, String password, String branchId) throws SQLException {
    	return getConnectionBySchema(login, password, findSchemaByBranch(branchId));
		
	}

    /**
     * Uses <tt>java.util.Map</tt> for performance improvements (caching of schema that already was found).
     *
     * @return schema name of branch
     */
    public synchronized String findSchemaByBranch(String branchId) throws SQLException {
        if (branchId == null || branchId.isEmpty()) {
            throw new IllegalArgumentException("branchId may not be null");
        }
        String schema = findSchemaInMap(branchId);
        if (StringUtils.trimToEmpty(schema).isEmpty()) {
            schema = findSchemaInDB(branchId);
            putSchemaInMap(branchId, schema);
        }
        return schema;
    }

    /**
     * Properly closes a connection.
     */
    public void close(Connection connection)  {
        try {
			if (connection != null && !connection.isClosed()) {
			    connection.close();
			}
		} catch (SQLException e) {
			log.error(CheckNull.getPstr(e));
		}
    }

    // Utils
    private Connection initBankSession(Connection connection, String schema) throws SQLException {
        connection = alterSessionSetDateFormat(connection);
        connection = alterSessionSetSchema(connection, schema);
        connection = callInfoInit(connection);
        return connection;
    }

    /**
     * Sets <tt>NLS_DATE_FORMAT</tt> on current oracle session.
     */
    private Connection alterSessionSetDateFormat(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("ALTER SESSION SET NLS_DATE_FORMAT = 'dd.mm.yyyy'");
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (Throwable ignore) {
            }
        }
        return connection;
    }

    /**
     * Sets <tt>CURRENT_SCHEMA</tt> on current oracle session.
     */
    private Connection alterSessionSetSchema(Connection connection, String schema) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("ALTER SESSION SET CURRENT_SCHEMA = " + schema);
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (Throwable ignore) {
            }
        }
        return connection;
    }

    /**
     * Calls <tt>Info.Init</tt> procedure of current oracle session.
     */
    private Connection callInfoInit(Connection connection) throws SQLException {
        CallableStatement cs = null;
        try {
            cs = connection.prepareCall("{ call info.init() }");
            cs.execute();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (Throwable ignore) {
            }
        }
        return connection;
    }

    private String findSchemaInMap(String branchId) {
        return schemasMap.get(branchId);
    }

    private void putSchemaInMap(String branchId, String schema) {
        schemasMap.put(branchId, schema);
    }

    private String findSchemaInDB(String branchId) throws SQLException {
        Connection connection = getConnection();
        String result = null;
        try {
            String SQL = "SELECT t.user_name FROM ss_dblink_branch t WHERE t.branch = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, branchId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("user_name");
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            close(connection);
        }
        return result;
    }
}