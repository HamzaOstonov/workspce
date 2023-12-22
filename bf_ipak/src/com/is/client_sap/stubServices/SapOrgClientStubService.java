package com.is.client_sap.stubServices;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.utils.DbUtils;
import com.is.client_sap.abstraction.AbstractSapOrganizationService;
import com.is.client_sap.exceptions.SapException;
import com.is.clients.models.ClientJ;
import com.is.clients.models.SapLogger;
import com.is.customer_.attachments.Attachment;
import com.is.utils.CheckNull;

import client.NCI.com.ipakyulibank.cj.BPAttachmentsAttachment;
import client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex;
import search.NCI.com.ipakyulibank.BPSearchResponceOrganization;

public class SapOrgClientStubService extends AbstractSapOrganizationService<ClientJ> {
	
	private SapOrgClientStubService() {}
	
	public static SapOrgClientStubService instance() {return new SapOrgClientStubService();}
	
	@Override
	public String orgRequestNew(ClientJ client) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String orgRequestEdit(ClientJ client) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_EDIT).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String orgRequestNewUvk(ClientJ client, Map<String, Object> uvkMap) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String orgRequestEditUvk(ClientJ client, Map<String, Object> uvkMap) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_EDIT).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public String sendAttacments(ClientJ client, BPAttachmentsAttachment[] atachments) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_EDIT).state(SapLogger.STATE_EMM_MODE).build().log();
		//todo
		BPAttachmentsAttachment bpAttachment = atachments[0];
		Attachment attachment=new Attachment();

		attachment.setData(bpAttachment.getData());
		attachment.setFileName(bpAttachment.getFilename());
		attachment.setDoc_type(bpAttachment.getType());
		attachment.setDescription(bpAttachment.getDescription());
		attachment.setDoc_date(bpAttachment.getDoc_date());
		//attachment.setCreatedAt(bpAttachment.getCreated_at().getTime());
		
		saveAttachment(attachment, client);
		return "";
	}

	public void saveAttachment(Attachment attachment, ClientJ cl){
		//Connection connection = null;
		//PreparedStatement preparedStatement = null;
	    Connection c = null;
	    //CallableStatement cs = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
		int id_apps=0;
		try{
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select seq_client_apps.nextval id from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				id_apps=rs.getInt("id");
			}
			
			ps = c.prepareStatement("insert into client_apps(branch, id_client, id_apps, data, description, filename, doc_type, url, doc_date, doc_number, createdat)"+
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
				ps.setString(1, cl.getBranch());
				ps.setString(2, cl.getId_client());
			
			ps.setInt(3, id_apps);
			ps.setBytes(4, attachment.getData());
			ps.setString(5, attachment.getDescription());
			ps.setString(6, attachment.getFileName());
			ps.setString(7, attachment.getDoc_type());
			//if (attachment.getUrl()!=null)
			//	ps.setString(8,  attachment.getUrl() );
			//else
				ps.setString(8,  ""+id_apps );
			ps.setDate(9, new java.sql.Date(attachment.getDoc_date().getTime()));
			ps.setString(10, attachment.getDoc_number());
			//ps.setDate(11, new java.sql.Date(attachment.getCreatedAt().getTime()));
			//java.sql.Date date = getCurrentDatetime();
			//ps.setDate(11, date);
			ps.setTimestamp(11, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.execute();
			c.commit();
		}
		catch (Exception e){
			e.printStackTrace(); 
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		}
		finally{
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
	}

	public java.sql.Date getCurrentDatetime() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}
	@Override
	public String createIfAbsent(ClientJ client) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	}

	@Override
	public void createIfAbsentWithoutInn(ClientJ client) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE).build().log();
	}

	@Override
	public String createIfAbsentUvk(ClientJ client, Map<String, Object> uvkMap) throws SapException {
		new SapLogger.Builder().clientJ(client).action(SapLogger.ACTION_CREATE).state(SapLogger.STATE_EMM_MODE).build().log();
		return "";
	} 

	@Override
	public List<Attachment> getAttachments(ClientJ client)  throws SapException {
		//return new ArrayList<Attachment>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Attachment> attachmentList = new ArrayList<Attachment>();

		String sql;
		try {
			c = ConnectionPool.getConnection();
			sql = "select t.branch, t.id_client, t.id_apps, t.data, t.description, t.filename, t.doc_type, "
					+ "t.url, t.doc_date, t.doc_number, t.createdat from CLIENT_apps t "
					+ "where branch=? and id_client=?";
			ps = c.prepareStatement(sql);
			ps.setString(1, client.getBranch());
			ps.setString(2, client.getId_client());
			rs = ps.executeQuery();

			while (rs.next()) {
				byte[] data = rs.getBytes("data");
				attachmentList.add(new Attachment(data, rs.getString("description"), rs.getString("fileName"),
						rs.getString("doc_type"), rs.getString("url"), rs.getDate("doc_date"),
						//rs.getString("doc_number"), rs.getDate("createdat"), rs.getString("id_client"), rs.getString("branch") ));
				rs.getString("doc_number"), rs.getTimestamp("createdat"), rs.getString("id_client"), rs.getString("branch") ));
			}

		} catch (Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			DbUtils.closeResultSet(rs);
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
		return attachmentList;
	}

	@Override
	public ClientJ getMappedDetailsBySapId(String idSap) {
		return new ClientJ();
	}

	@Override
	public ClientJ getMappedDetailsByNciId(String clientId, String branch) {
		return new ClientJ();
	}

	@Override
	public ClientJ getDetailsByDoc(String docId, String docType) {
		return new ClientJ();
	}

	@Override
    public BusinessOrganizationComplex getDetailsByInn(String docId){
        return null;
    }

    @Override
    public BusinessOrganizationComplex getDetailsBySapId(String sapId){
        return null;
    }

    @Override
    public BusinessOrganizationComplex getDetailsByNciId(String id,String branch){
        return null;
    }

    @Override
    public List<BPSearchResponceOrganization> searchOrganization(String docId, String docType, String name) {
		ISLogger.getLogger().error("not err. ya zdes. local avariyniy poisk. docId="+docId+" docType="+docType+ " name="+name);
        return new ArrayList<BPSearchResponceOrganization>();
    }

	@Override
	public void deleteAttachment(ClientJ client, String docId) throws RemoteException {
		// TODO Auto-generated method stub
		//ISLogger.getLogger().error("not err. ya zdes. local. ");
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(
					"delete from client_apps where branch=? and id_client=? and id_apps=?");
			/*if (cl instanceof Customer) {
				ps.setString(1, ((Customer) cl).getBranch());
				ps.setString(2, ((Customer) cl).getId());
			} else {
				ps.setString(1, ((ClientJ) cl).getBranch());
				ps.setString(2, ((ClientJ) cl).getId_client());
			}*/

			ps.setString(1, client.getBranch());
			ps.setString(2, client.getId_client());
			ps.setString(3, docId);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}

	}

}
