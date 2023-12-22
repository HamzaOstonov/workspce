package com.is.client_personmap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.is.ConnectionPool;
import org.zkoss.zk.ui.Executions;

import com.is.ISLogger;

import relationships.NCI.com.ipakyulibank.BPContent;
//insert into sap_sent_event_events (id,request_id,event_id) values(?,?,?);
//insert into sap_sent_event_requests (id,branch,v_date,state_id,client_type,client_id,parent_group_id,parent_id,emp_id) values()
public class RoleLogger {
	private static final String REQ_SEQ = "select seq_sap_sent_event_requests.nextval from dual";
	private static final String REQ_INSERT = "insert into sap_sent_event_requests (id, branch, v_date, state_id, client_type, client_id, emp_id) values(?,?,?, ?,?,?, ?)";
	private static final String EVENT_SEQ = "select seq_sap_sent_event_events.nextval from dual";
	private static final String EVENT_INSERT = "insert into sap_sent_event_events (id,request_id,event_id) values(?,?,?)";
	
	
	public RoleLogger() {}
	
	public static void logErrorRole(BPContent content, String[] roles) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer uid = (Integer) Executions.getCurrent().getSession().getAttribute("uid");
		long requestId = 0;
		long eventId = 0;
		try {
			c = ConnectionPool.getConnection();
			for (String role : roles) {
				ps = c.prepareStatement(REQ_SEQ);			
				rs = ps.executeQuery();
				if (rs.next()) {
					requestId = rs.getLong(1);
				}
				ps.close(); rs.close();
				ps = c.prepareStatement(REQ_INSERT);
				ps.setLong(1, requestId);
				ps.setString(2, content.getBranch());
				ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
				ps.setInt(4, 1);
				ps.setString(5, content.getBp_type());
				ps.setString(6, content.getBp_extnum());
				ps.setInt(7, uid);
				ps.executeUpdate();
				
				ps.close(); rs.close();
				
				ps = c.prepareStatement(EVENT_SEQ);			
				rs = ps.executeQuery();
				if (rs.next()) {
					eventId = rs.getLong(1);
				}
				ps.close(); rs.close();
				ps = c.prepareStatement(EVENT_INSERT);
				ps.setLong(1, eventId);
				ps.setLong(2, requestId);
				ps.setString(3, role);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			ISLogger.getLogger().error(e.getCause().getMessage(), e);
		} finally {
			ConnectionPool.close(c);
		}
	}

}
