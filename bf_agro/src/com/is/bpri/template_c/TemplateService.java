package com.is.bpri.template_c;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.utils.MyComboModel;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class TemplateService {	
	protected static List<TemplateModel> getModel(Long bpr_id){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TemplateModel> list = new ArrayList<TemplateModel>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_templates where id=?");
			ps.setLong(1, bpr_id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new TemplateModel(
						rs.getLong("id"),
						rs.getString("BANNER_TEXT"),
						rs.getString("file_name")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}	
	protected static List<TemplateFields> getFields(Long id){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TemplateFields> list = new ArrayList<TemplateFields>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from BPR_template_fields where tid=? order by oid");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new TemplateFields(
						rs.getLong(1),
						rs.getLong(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getLong(7),
						rs.getInt(8),
						rs.getString(9)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}	
	protected static List<ListValues> getValues(Long tid,Long oid){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ListValues> list = new ArrayList<ListValues>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from BPR_template_fields_settings where tid=? and oid=? order by id");
			ps.setLong(1, tid);
			ps.setLong(2, oid);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new ListValues(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}	
	protected static List<ListValues> getValues(Long tid){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ListValues> list = new ArrayList<ListValues>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from BPR_template_fields_settings where tid=? order by id");
			ps.setLong(1, tid);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new ListValues(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}	
	protected static void save(TemplateModel template,List<TemplateFields> fields,int btn_click,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			saving(c, template, fields, btn_click, res);
			c.commit();
		} catch (Exception e) {
			Utils.rollback(c);
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}	
	private static boolean saving(Connection c,TemplateModel template,List<TemplateFields> fields,int btn_click,Res res){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long id = null;
		boolean result = false;
		Long field_id = null;
		try {
			if(template.getId()==null||template.getId()==0L){
				ps = c.prepareStatement("SELECT BPR_SEQ_templates.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if(rs.next()){
					id = rs.getLong(1);
				}
			} else {
				id = template.getId();
			}
			ps = c.prepareStatement("insert into bpr_templates (id,banner_text, file_name) values (?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, template.getName());
			ps.setString(3, template.getFile_name());			
			ps.execute();
			/*if(btn_click==2){
				ps = c.prepareStatement("insert into modules (id,parentid,mtype,name_ru,mname,name_uz,name_en,extparam) values (?,?,?,?,?,?,?,?)");
				ps.setLong(1, 90000+id);
				ps.setLong(2, 90000);
				ps.setInt(3, 1);
				ps.setString(4, template.getName());
				ps.setString(5, "Zuls/Bpmn/dynamicModules.zul");
				ps.setString(6, template.getName());
				ps.setString(7, template.getName());
				ps.setLong(8, id);
				ps.execute();
			}*/
			for (int i = 0; i < fields.size(); i++) {
				ps = c.prepareStatement("SELECT BPR_SEQ_template_fields.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if(rs.next()){
					field_id = rs.getLong(1);
				}
				ps = c.prepareStatement("insert into BPR_template_fields (tid,oid,label_name,type_field,sid,label_name_en,conformity_id,required_field,model) values (?,?,?,?,?,?,?,?,?)");
				ps.setLong(1, id);
				System.out.println("id = "+id);
				ISLogger.getLogger().error("id = "+id);
				ps.setLong(2, field_id);
				System.out.println("oid = "+field_id);
				ps.setString(3, fields.get(i).getLabel_name());
				ps.setString(4, fields.get(i).getType_field());
				String sid = fields.get(i).getSid();
				if(sid==null||sid.equals("null")){ sid = "-1"; }
				ps.setString(5, sid);
				System.out.println("sid = "+fields.get(i).getSid());
				ps.setString(6, fields.get(i).getLabel_name());
				ps.setLong(7, fields.get(i).getConformity_id());
				ISLogger.getLogger().error("getConformity_id = "+fields.get(i).getConformity_id());
				ps.setInt(8, fields.get(i).getRequired_field());
				ps.setString(9, fields.get(i).getModel());
				ISLogger.getLogger().error("getModel = "+fields.get(i).getModel());
				ps.execute();
			}
			ps.executeBatch();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			Utils.rollback(c);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(-1);
			res.setName(e.getMessage());
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return result;
	}	
	protected static void update(TemplateModel template,List<TemplateFields> fields, Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String field_id = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("update BPR_templates set banner_text=?, file_name=? where id=?");
			ps.setString(1, template.getName());
			ps.setString(2, template.getFile_name());
			ps.setLong(3, template.getId());
			ps.execute();
			for (int i = 0; i < fields.size(); i++) {
				if(fields.get(i).getOid()==null){
					ps = c.prepareStatement("SELECT BPR_SEQ_template_fields.NEXTVAL id FROM DUAL");
					rs = ps.executeQuery();
					if(rs.next()){
						field_id = rs.getString(1);
					}
					ps = c.prepareStatement("insert into BPR_template_fields (tid,oid,label_name,type_field,sid,label_name_en,conformity_id,required_field,model) values (?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, template.getId());
					ISLogger.getLogger().error("TID = "+template.getId());
					ps.setString(2, field_id);
					ps.setString(3, fields.get(i).getLabel_name());
					ps.setString(4, fields.get(i).getType_field());
					ps.setString(5, fields.get(i).getSid().equals("null")?"-1":fields.get(i).getSid());
					ps.setString(6, fields.get(i).getLabel_name());
					ps.setLong(7, fields.get(i).getConformity_id());
					ISLogger.getLogger().error("getConformity_id = "+fields.get(i).getConformity_id());
					ps.setInt(8, fields.get(i).getRequired_field());
					ps.setString(9, fields.get(i).getModel());
					ISLogger.getLogger().error("getModel = "+fields.get(i).getModel());
					ps.execute();
				} else {
					System.out.println("update!!!");
					ps = c.prepareStatement("update BPR_template_fields set label_name=?,type_field=?,sid=?,label_name_en=?,conformity_id=?,required_field=?,model=? where tid=? and oid=?");
					ps.setString(1, fields.get(i).getLabel_name());
					ps.setString(2, fields.get(i).getType_field());
					ps.setString(3, fields.get(i).getSid());
					ps.setString(4, fields.get(i).getLabel_name());
					ps.setLong(5, fields.get(i).getConformity_id());
					ISLogger.getLogger().error("getConformity_id = "+fields.get(i).getConformity_id());
					System.out.println("!!! = "+fields.get(i).getLabel_name());
					ps.setInt(6, fields.get(i).getRequired_field());
					ps.setString(7, fields.get(i).getModel());
					ISLogger.getLogger().error("getModel = "+fields.get(i).getModel());
					ps.setLong(8, fields.get(i).getTid());
					ISLogger.getLogger().error("TID = "+fields.get(i).getTid());
					ps.setLong(9, fields.get(i).getOid());
					ps.execute();
				}
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(-1);
			res.setName(CheckNull.getPstr(e));
			Utils.rollback(c);
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}	
	protected static Long usedTemplate(TemplateModel current){
		Long size = 0l;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from BPR_creategrids where tid=?");
			ps.setLong(1, current.getId());
			rs = ps.executeQuery();
			if(rs.next()){
				size = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return size;
	}
	
	public static void remove(Long tid)  {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete from BPR_template_fields where tid=?");
			ps.setLong(1, tid);
			ps.executeUpdate();
			ps = c.prepareStatement("delete from BPR_templates where id=?");
			ps.setLong(1, tid);
			ps.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	protected static void removeField(Long tid,String oid){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete from bpr_template_fields where tid=? and oid=?");
			ps.setLong(1, tid);
			ps.setString(2, oid);
			ps.execute();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void templateMethod(String path){
		InputStream is = null;
		Connection c = null;
		try {
			is = new FileInputStream(path);
			c = ConnectionPool.getConnection();
			XSSFWorkbook myExel = new XSSFWorkbook(is);
			XSSFSheet sheet = myExel.getSheetAt(0);
			XSSFRow row;
			int index = 0;
			while (true) {
				row = sheet.getRow(index);
				if(row==null){ break; }
				else {
					Cell table_name = row.getCell(0);
					Cell table_field_name = row.getCell(1);
					Cell label_name = row.getCell(2);
					Cell type_field = row.getCell(3);
					String type = "";
					if(type_field.getStringCellValue().equalsIgnoreCase("char")||type_field.getStringCellValue().equalsIgnoreCase("varchar2")){
						type = "string";
					} else {
						type = type_field.getStringCellValue();
					}
					Cell max_length = row.getCell(5);
//					Cell attribute_cell = row.getCell(5);
//					String attribute = "";
//					if(attribute_cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
//						attribute = Math.round(attribute_cell.getNumericCellValue())+"";
//					} else {
//						attribute = attribute_cell.getStringCellValue();
//					}
					String length = "";
					if(max_length.getCellType()==Cell.CELL_TYPE_NUMERIC){
						length = Math.round(max_length.getNumericCellValue())+"";
					} else {
						length = max_length.getStringCellValue();
					}
						insert(index+1L,
								label_name.getStringCellValue(),
								type,
								table_field_name.getStringCellValue(),
								length,
								table_name.getStringCellValue(),
								c);
				}
				index++;
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.rollback(c);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			if(is!=null){try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
			ConnectionPool.close(c);
		}
	}

	private static void insert(Long id,String field_name_ru,String type_field,String field_name_table,String field_lenght,String table_name,Connection c) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = c.prepareStatement("insert into create_guarr_grid (order_id,name_field,type_field,table_name_field,max_lenght_field,guarr_id,table_name) values (?,?,?,?,?,?,?)");
			ps.setLong(1, id);
			ps.setString(2, field_name_ru);
			ps.setString(3, type_field);
			ps.setString(4, field_name_table);
			ps.setString(5, field_lenght);
			ps.setString(6, "21");
			ps.setString(7, table_name);
			ps.execute();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}
	
	protected static List<Ss_bpr_scoring_anket> getSystemRequiredId(String target_bpr){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String temp = target_bpr.equals("J")?" and id not in (4,5,6) ":"";
		List<Ss_bpr_scoring_anket> list = new ArrayList<Ss_bpr_scoring_anket>();
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select id,type_field,label from ss_bpr_scoring_anket a where a.system_required=1 "+temp+" order by id");
			rs = ps.executeQuery();
			while(rs.next()) list.add(new Ss_bpr_scoring_anket(rs.getString(1), rs.getString(2), rs.getString(3))) ;
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static List<MyComboModel> getConformity(){
		List<MyComboModel> all_models = Utils.getComboModel("select id,label from ss_bpr_scoring_anket where attribute=1 order by table_name,label", "scoring", "");
		List<MyComboModel> emp_model = Utils.getComboModel("select id,value||' (Сотрудник)' from Bpr_Ss_Reference_Fields where sid=(select id from Bpr_Ss_Reference where module_id=44)", "employee", "");
		for(MyComboModel model : emp_model){
			all_models.add(model);
		}
		return all_models;
	}
	
	protected static boolean isSystemRequired(String id,String bpr_id){
		return Utils.getData("select a.system_required from bpr_template_fields f,ss_bpr_scoring_anket a where f.tid="+bpr_id+" and f.model='scoring' and (f.conformity_id is not null and f.conformity_id !=0) and f.conformity_id=a.id and f.conformity_id="+id,"").equals("1");
	}
}
