package com.is.file_reciever_srv.recievers.visa.onus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import com.is.ConnectionPool;
import com.is.ISLogger;

import com.is.file_reciever_srv.recievers.visa.Visa_file_reciever;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;

import com.is.utils.CheckNull;

public class Onus_reciever extends Reciever_class {

	@Override
	public void Recieve_file(String input_file, long fr_file_id) {

		ISLogger.getLogger().info("EMPC file reciever: EXPT_reciver line 18 ");
		Connection c = null;
		try {
			 c = ConnectionPool.getConnection();
			// c =getTimerConnection();

			// ISLogger.getLogger().info("qwerty1");
			Onus_service serv = new Onus_service(c, fr_file_id, input_file);
			serv.process_file();
			Visa_file_reciever.move_file_to_archive(input_file);
			c.commit();
			ISLogger.getLogger().info(
					"EMPC file reciever: EXPT_reciver line 29 ");
			// c.rollback();
		} catch (Exception e) {
			if (c != null)
				try {
					c.rollback();
				} catch (Exception e1) {
				}
			ISLogger.getLogger().error(
					"recieving EMPC file " + input_file + " error "
							+ CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (Exception e1) {
				}
		}
	}

	// public static Connection getTimerConnection() throws SQLException {
	// try {
	// Context context = new InitialContext();
	// Context envContext = (Context) context.lookup("java:/comp/env");
	// DataSource datasource = (DataSource)
	// envContext.lookup("jdbc/myoracle_config");
	// if (datasource == null) {
	// throw new Exception("No DataSource");
	// }
	//
	// PoolConfiguration p = datasource.getPoolProperties();
	// p.setRemoveAbandonedTimeout(1800);
	// datasource.setPoolProperties(p);
	// Connection c = datasource.getConnection();
	//
	// return c;
	// } catch (Exception e) {
	// ISLogger.getLogger().error(e.getStackTrace());
	// throw new RuntimeException("Database Not Available.");
	// }
	// }
	//
	// public static void resetTimerConnection() throws SQLException {
	// try {
	// Context context = new InitialContext();
	// Context envContext = (Context) context.lookup("java:/comp/env");
	// DataSource datasource = (DataSource)
	// envContext.lookup("jdbc/myoracle_config");
	// if (datasource == null) {
	// throw new Exception("No DataSource");
	// }
	//
	// PoolConfiguration p = datasource.getPoolProperties();
	// p.setRemoveAbandonedTimeout(60);
	// datasource.setPoolProperties(p);
	//
	//
	//
	//
	//
	//
	// } catch (Exception e) {
	// ISLogger.getLogger().error(e.getStackTrace());
	// throw new RuntimeException("Database Not Available.");
	// }
	// }

}
