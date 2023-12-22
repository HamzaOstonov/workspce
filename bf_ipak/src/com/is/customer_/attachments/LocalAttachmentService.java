package com.is.customer_.attachments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.base.utils.DbUtils;
import com.is.clients.models.ClientJ;
import com.is.customer_.core.model.Customer;
import com.is.utils.CheckNull;

public class LocalAttachmentService {
	private Logger log = Logger.getLogger(LocalAttachmentService.class);
	private String schema;

	private LocalAttachmentService(String schema) {
		// TODO Auto-generated constructor stub
		this.schema = schema;

	}

	public static LocalAttachmentService getInstance(String schema) {
		return new LocalAttachmentService(schema);
	}

	public void saveAttachment(Attachment attachment, Object cl) {
		// Connection connection = null;
		// PreparedStatement preparedStatement = null;
		Connection c = null;
		// CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int id_apps = 0;
		try {
			c = ConnectionPool.getConnection(schema);
			ps = c.prepareStatement("select seq_client_apps.nextval id from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				id_apps = rs.getInt("id");
			}

			ps = c.prepareStatement(
					"insert into client_apps(branch, id_client, id_apps, data, description, filename, doc_type, url, doc_date, doc_number)"
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			if (cl instanceof Customer) {
				ps.setString(1, ((Customer) cl).getBranch());
				ps.setString(2, ((Customer) cl).getId());
			} else {
				ps.setString(1, ((ClientJ) cl).getBranch());
				ps.setString(2, ((ClientJ) cl).getId_client());
			}
			ps.setInt(3, id_apps);
			ps.setBytes(4, attachment.getData());
			ps.setString(5, attachment.getDescription());
			ps.setString(6, attachment.getFileName());
			ps.setString(7, attachment.getDoc_type());
			// if (attachment.getUrl()!=null)
			// ps.setString(8, attachment.getUrl() );
			// else
			ps.setString(8, "" + id_apps);
			ps.setDate(9, new java.sql.Date(attachment.getDoc_date().getTime()));
			ps.setString(10, attachment.getDoc_number());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
	}

	public List<Attachment> getAttachments(String branch, String id_client) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();

		String sql;
		try {
			c = ConnectionPool.getConnection(schema);
			// list=DbUtils.getActionData(c, "select deal_id, id, null name,
			// null manual from v_action_specialacc");
			sql = "select t.branch, t.id_client, t.id_apps, t.data, t.description, t.filename, t.doc_type, "
					+ "t.url, t.doc_date, t.doc_number, t.createdat from CLIENT_apps t "
					+ "where branch=? and id_client=?";
			ps = c.prepareStatement(sql);
			ps.setString(1, branch);
			ps.setString(2, id_client);
			rs = ps.executeQuery();

			while (rs.next()) {
				byte[] data = rs.getBytes("data");
				attachmentList.add(new Attachment(data, rs.getString("description"), rs.getString("fileName"),
						rs.getString("doc_type"), rs.getString("url"), rs.getDate("doc_date"),
						rs.getString("doc_number")));
			}

		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return attachmentList;
	}

	public Attachment getAttachment(String branch, String id_client, String id_apps) {
		// TODO Auto-generated method stub
		Attachment attachment = null;// = new
										// Attachment(response.getData(),null,response.getDoc_name(),null,null,null,null);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		try {
			c = ConnectionPool.getConnection(schema);
			sql = "select t.branch, t.id_client, t.id_apps, t.data, t.description, t.filename, t.doc_type, "
					+ "t.url, t.doc_date, t.doc_number, t.createdat from CLIENT_apps t "
					+ "where branch=? and id_client=? and id_apps=?";
			ps = c.prepareStatement(sql);
			ps.setString(1, branch);
			ps.setString(2, id_client);
			ps.setString(3, id_apps);
			rs = ps.executeQuery();

			while (rs.next()) {
				byte[] data = rs.getBytes("data");
				attachment = new Attachment(data, rs.getString("description"), rs.getString("fileName"),
						rs.getString("doc_type"), rs.getString("url"), rs.getDate("doc_date"),
						rs.getString("doc_number"));
				break;// esli mnogo faylov vernet
			}

		} catch (Exception e) {
			log.error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}

		return attachment;
	}

	public void deleteAttachment(String branch, String id_client, int id_app) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(schema);
			ps = c.prepareStatement(
					"delete from client_apps where branch=? and id_client=? and id_apps=?");
			/*if (cl instanceof Customer) {
				ps.setString(1, ((Customer) cl).getBranch());
				ps.setString(2, ((Customer) cl).getId());
			} else {
				ps.setString(1, ((ClientJ) cl).getBranch());
				ps.setString(2, ((ClientJ) cl).getId_client());
			}*/

			ps.setString(1, branch);
			ps.setString(2, id_client);
			ps.setInt(3, id_app);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
	}

}
