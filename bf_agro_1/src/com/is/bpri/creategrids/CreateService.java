package com.is.bpri.creategrids;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.is.ConnectionPool;
import com.is.bpri.template_c.TemplateFields;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class CreateService {
	public static int row_start=0;
	public static int row_par=0;
	
	static SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
//	private static String getUid(String uname){
//		String uid = null;
//		Connection c = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			c = ConnectionPool.getConnection();
//			ps = c.prepareStatement("select id from users where username=?");
//			ps.setString(1, uname);
//			rs = ps.executeQuery();
//			if(rs.next()){
//				uid = rs.getString(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(ps);
//			ConnectionPool.close(c);
//		}
//		return uid;
//	}
	
	protected static List<Create> getModel(Create filter){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		List<Create> list = new ArrayList<Create>();
		String str = "";		
		if(filter!=null){
			if (!CheckNull.isEmpty(filter.getTid())){			  	
			  if (CheckNull.isEmpty(str)){
			      str = " where tid = "+filter.getTid();
			  }else{
				  str = str+" and tid = "+filter.getTid();
			  }
			}
			if (!CheckNull.isEmpty(filter.getBranch())){
			  if (str==""){	
				 str = " where branch = "+filter.getBranch();
			  }else{
		         str = str+" and branch = "+filter.getBranch();
			  }
			}
			if (!CheckNull.isEmpty(filter.getDate_bank())){
			  if (str==""){	
				 str = " where date_bank = to_date('"+df.format(filter.getDate_bank())+"', 'dd.mm.yyyy')";
			  }else{
			     str = str+" and date_bank = to_date('"+df.format(filter.getDate_bank())+"', 'dd.mm.yyyy')";
			  }
			}
			if (!CheckNull.isEmpty(filter.getState())){
				if (str==""){	
				    str = " where state = "+filter.getState();
				}else{
			        str = str+" and state = "+filter.getState();
				}
			}	
		}		
		try {
			c = ConnectionPool.getConnection();
			//ps = c.prepareStatement("select id, name, tname, process, process_name, uname, branch, to_char(date_bank, 'dd.mm.yyyy') date_bank from creategrids "+str+" order by id desc");
			ps = c.prepareStatement("select * from bpr_creategrids "+str+" order by tid ASC, id desc"); //tid ASC,
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Create(
						rs.getLong(1),
						rs.getString(2),
						rs.getLong(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getDate(9),
						rs.getLong(10),
						rs.getString(11)
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
	
	public static List<CreateValues> getValues(String cid){
		List<CreateValues> list = new ArrayList<CreateValues>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_createvalues where cid=?");
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new CreateValues(
						rs.getLong(1),
						rs.getLong(2),
						rs.getLong(3),
						rs.getString(4),
						""
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
	
	public static List<TemplateFields> getFields(String tid){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TemplateFields> list = new ArrayList<TemplateFields>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_template_fields where tid=? order by tid,oid");
			ps.setString(1, tid);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(
						new TemplateFields(
								rs.getLong(1),
								rs.getLong(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getString(6),
								rs.getLong(7),
								rs.getInt(8),
								rs.getString(9)
						)
				);
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
	
	public static void update(Create current,List<CreateValues> list,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update bpr_creategrids set name=?, tid=?, process=? where id=?");
			ps.setString(1, current.getName());
			ps.setLong(2, current.getTid());
			ps.setString(3, current.getProcess());
			ps.setLong(4, current.getId());
			ps.execute();
			for (int i = 0; i < list.size(); i++) {
				ps = c.prepareStatement("update bpr_createvalues set value=? where cid=? and tid=? and oid=?");
				ps.setString(1, list.get(i).getValue());
				ps.setLong(2, current.getId());
				ps.setLong(3, list.get(i).getTid());
				ps.setLong(4, list.get(i).getOid());
				ps.execute();
			}
			c.commit();
		} catch (Exception e) {
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			rollback(c);
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void save(Create current,List<CreateValues> list,Connection c) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement cs = null;
//		ps = c.prepareStatement("SELECT bpr_SEQ_creategrids.NEXTVAL id FROM DUAL");
//		rs = ps.executeQuery();
//		if(rs.next()){
//			id = rs.getLong(1);
//		}
		ps = c.prepareStatement("insert into bpr_creategrids (id,tid,branch,date_bank) values (?,?,?,?)");
		ps.setLong(1, current.getId());
		ps.setLong(2, current.getTid());
		ps.setString(3, current.getBranch());
		ps.setDate(4, new java.sql.Date(current.getDate_bank().getTime()));
		ps.execute();
		cs = c.prepareCall("{ call Param.SetParam(?,?) }");
		ps = c.prepareStatement("insert into bpr_createvalues (cid,tid,oid,value) values (?,?,?,?)");
		System.out.println("create size = "+list.size());
		for (int i = 0; i < list.size(); i++) {
			ps.setLong(1, current.getId());
			ps.setLong(2, list.get(i).getTid());
			ps.setLong(3, list.get(i).getOid());
			ps.setString(4, list.get(i).getValue());
			System.out.println("list param = !!!"+list.get(i).getParam()+"!!!");
//			if(list.get(i).getParam()!=null&&!list.get(i).getParam().equals("")){
//				cs.setString(1, list.get(i).getParam());
//				cs.setString(2, list.get(i).getValue());
//				System.out.println("ÏÀÐÀÌ = "+list.get(i).getParam());
//				cs.execute();
//			}
			ps.addBatch();
		}
		ps.executeBatch();
		Utils.close(rs);
		Utils.close(cs);
		Utils.close(ps);

	}
	
	public static List<RefData> getReferences(String sid){
		return getRefDatas("select id,value from bpr_ss_reference_fields where sid="+sid+" order by sid,id");
	}
	
	protected static List<RefData> getTemplates(){
		return getRefDatas("select id,name from bpr_templates order by name");
	}
	
	public static List<RefData> getRefDatas(String sql){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RefData> list = new LinkedList<RefData>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(
						new RefData(
								rs.getString(1),
								rs.getString(2)
						)		
				);
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

	public static String getDay(String alias)  {
	    String res="";
	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;	    
	    try {
	            c = ConnectionPool.getConnection(alias);
	            ps = c.prepareStatement("select to_char(curdate-1, 'dd.mm.yyyy') cur_day from sets");
	            rs = ps.executeQuery();
	            while (rs.next()) {
	               res=rs.getString("cur_day");
	            }
	    } catch (SQLException e) {
	       com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	    } finally {
	       try {
		   		if(rs!=null) rs.close();
		   		if(ps!=null) ps.close();
		   } catch (Exception e2) {
				e2.printStackTrace();
		   }	      
		   ConnectionPool.close(c);
		}
	    return res;
	}
	protected static List<GridCols> getGridCols(String oid){
		List<GridCols> list = new ArrayList<GridCols>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select v.cid, v.oid, f.label_name_en, v.value, f.type_field, decode(f.type_field, 'list', (select s.value from bpr_ss_reference_fields s where s.sid = f.sid and s.id = trim(v.value)), v.value) svalue from bpr_createvalues v, template_fields f where v.oid = f.oid  and v.tid = f.tid and v.cid = ? order by v.cid, v.oid");
			ps.setString(1, oid);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new GridCols(
						rs.getLong(1),
						rs.getLong(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)));
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
	protected static void delete(Long cid, Long tid,Res res){
		System.out.println("cid - "+cid+", tid - "+tid);
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			System.out.println("1");
			ps = c.prepareStatement("delete from bpr_createvalues where tid=? and cid=?");
			ps.setLong(1, tid);
			ps.setLong(2, cid);
			System.out.println("2");
			ps.execute();
			System.out.println("3");
			ps = c.prepareStatement("delete from bpr_creategrids where tid=? and id=?");
			ps.setLong(1, tid);
			ps.setLong(2, cid);
			System.out.println("4");
			ps.execute();
			System.out.println("5");
			c.commit();			
			System.out.println("6");
		} catch (Exception e) {
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			rollback(c);
		} finally {
			close(rs);
			System.out.println("7");
			close(ps);
			System.out.println("8");
			ConnectionPool.close(c);
			System.out.println("9");
		}
	}	
	protected static void confirm(Long cid, Long tid,Res res){		
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update bpr_creategrids set state=?, state_name=? where tid=? and id=?");
			ps.setLong(1, 1);
			ps.setString(2, "Óòâåðæåí");
			ps.setLong(3, tid);
			ps.setLong(4, cid);
			ps.execute();
			c.commit();			
		} catch (Exception e) {
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			rollback(c);
		} finally {
			close(rs);
			close(ps);
			ConnectionPool.close(c);
		}
	}
	public static String getBanAndFile(long tid, int type)  {
	    String res="";
	    String SqlText="";
	    if (type==1){
	    	SqlText="select banner_text tvalue, row_start, row_par from bpr_templates where id=?";
	    }else if (type==2){
	    	SqlText="select file_name tvalue, row_start, row_par from bpr_templates where id=?";
	    }	    
	    Connection c = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;	    
	    try {
	            c = ConnectionPool.getConnection();
	            ps = c.prepareStatement(SqlText);
	            ps.setLong(1, tid);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	               res=rs.getString("tvalue");
	               row_start=rs.getInt("row_start");
	               row_par=rs.getInt("row_par");
	            }
	    } catch (SQLException e) {
	       com.is.LtLogger.getLogger().error(com.is.utils.CheckNull.getPstr(e));
	    } finally {
	       try {
		   		if(rs!=null) rs.close();
		   		if(ps!=null) ps.close();
		   } catch (Exception e2) {
				e2.printStackTrace();
		   }	      
		   ConnectionPool.close(c);
		}
	    return res;
	}	
}
