package com.is.bpri.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class ReferenceSerivce {
	
	protected static List<Reference> getModel(String module_id){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reference> list = new ArrayList<Reference>();
		String temp = "";
		if(module_id!=null){
			temp = "where module_id="+module_id;
		}
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_ss_reference "+temp+" order by id desc");
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Reference(
						rs.getLong(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static List<ReferenceFields> getFields(Long id){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ReferenceFields> list = new ArrayList<ReferenceFields>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_ss_reference_fields where sid=? order by id");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new ReferenceFields(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static void save(Reference reference,List<ReferenceFields> fields,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			saving(c, reference, fields, res);
			c.commit();
		} catch (Exception e) {
			rollback(c);
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
	}
	
	private static boolean saving(Connection c,Reference reference,List<ReferenceFields> fields,Res res){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		String field_id = null;
		boolean result = false;
		try {
			if(reference.getId()==null||reference.getId()==0L){
				ps = c.prepareStatement("SELECT bpr_SEQ_ss_reference.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if(rs.next()){
					id = rs.getLong(1);
				}
			} else {
				id = reference.getId();
			}
			ps = c.prepareStatement("insert into bpr_ss_reference (id,name,uname,module_id) values (?,?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, reference.getName());
			ps.setString(3, reference.getUname());
			ps.setString(4, reference.getModule_id());
			ps.execute();
			for (int i = 0; i < fields.size(); i++) {
				ps = c.prepareStatement("SELECT bpr_SEQ_ss_reference_fields.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if(rs.next()){
					field_id = rs.getString(1);
				}
				ps = c.prepareStatement("insert into bpr_ss_reference_fields (sid,id,value) values (?,?,?)");
				ps.setLong(1, id);
				ps.setString(2, field_id);
				ps.setString(3, fields.get(i).getValue());
				ps.execute();
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			rollback(c);
		} finally {
			close(rs);
			close(ps);
		}
		return result;
	}
	
	protected static void update(Reference reference,List<ReferenceFields> fields, Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String field_id = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update bpr_ss_reference set name=? where id=?");
			ps.setString(1, reference.getName());
			ps.setLong(2, reference.getId());
			ps.execute();
			for (int i = 0; i < fields.size(); i++) {
				if(fields.get(i).getId().equals("")||fields.get(i).getId()==null){
					ps = c.prepareStatement("SELECT bpr_SEQ_ss_reference_fields.NEXTVAL id FROM DUAL");
					rs = ps.executeQuery();
					if(rs.next()){
						field_id = rs.getString(1);
					}
					ps = c.prepareStatement("insert into bpr_ss_reference_fields (sid,id,value) values (?,?,?)");
					ps.setLong(1, reference.getId());
					ps.setString(2, field_id);
					ps.setString(3, fields.get(i).getValue());
					ps.execute();
				} else {
					ps = c.prepareStatement("update bpr_ss_reference_fields set value=? where sid=? and id=?");
					ps.setString(1, fields.get(i).getValue());
					ps.setString(2, fields.get(i).getSid());
					ps.setString(3, fields.get(i).getId());
					ps.execute();
				}
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			rollback(c);
		} finally {
			close(ps);
			ConnectionPool.close(c);
		}
	}
	
	private static void close(PreparedStatement ps){
		try {
			if(ps!=null)ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void close(ResultSet rs){
		try {
			if(rs!=null)rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void rollback(Connection c){
		try {
			if(c!=null){
				c.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected static Long usedReference(Reference current){
		Long size = 0l;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(oid) count_oid from bpr_template_fields where sid=?");
			ps.setLong(1, current.getId());
			rs = ps.executeQuery();
			if(rs.next()){
				size = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
		return size;
	}
	public static void remove(Long tid)  {
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("delete from bpr_ss_reference_fields where sid=?");
			ps.setLong(1, tid);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
		try {
			c = ConnectionPool.getConnection();
			PreparedStatement ps = c.prepareStatement("delete from bpr_ss_reference where id=?");
			ps.setLong(1, tid);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}		
	}	
}
