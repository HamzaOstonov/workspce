package com.is.bpri.scoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.main_scoring.MainScoringService;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class ScoringService {
	
	protected static List<Scoring> getParams(int bpr_type,String alias) throws SQLException{
		List<Scoring> list = new LinkedList<Scoring>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from bpr_create_scoring_grd where group_id=-1 and bpr_type=? and mandatory=1 order by group_id,id");
//			ps.setInt(1, group_id);
			ps.setInt(1, bpr_type);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Scoring(
						rs.getInt("group_id"), 
							rs.getInt("id"), 
								rs.getString("name"),
									rs.getString("name_ru"),
										rs.getString("name_en"),
											rs.getString("name_uz"),
												rs.getString("selects"),
													rs.getString("type"),
														rs.getString("def_value"),
															rs.getString("ord"),
																rs.getString("mandatory"),
																	rs.getString("param_type")));
			}
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
	
	protected static List<RefData> getList(String sql,String alias) throws Exception{
		List<RefData> list = new LinkedList<RefData>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		c = ConnectionPool.getConnection(alias);
		ps = c.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			list.add(new RefData(
					rs.getString(1),
						rs.getString(2)));
		}
		Utils.close(rs);
		Utils.close(ps);
		ConnectionPool.close(c);
		return list;
	}
	
	protected static List<Scoring> getModel(int bpr_id,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Scoring> list = new LinkedList<Scoring>();
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("select * from bpr_create_scoring_grd where bpr_create_scoring_grd.bpr_id=?");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Scoring(
							rs.getInt("group_id"),
								rs.getInt("id"),
									rs.getString("name"),
										rs.getString("name_ru"),
											rs.getString("name_en"),
												rs.getString("name_uz"),
													rs.getString("selects"),
														rs.getString("type"),
															rs.getString("def_value"),
																rs.getString("ord"),
																	rs.getString("mandatory"),
																		rs.getString("param_type"),
																			rs.getString("bpr_id")));
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
	
	protected static void create(List<Scoring> list,Integer bpr_id,Integer scoring_type,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			MainScoringService.insertScoringType(c, bpr_id, scoring_type);
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setGroup_id(1);
				String tmp = "";
				ps = c.prepareStatement("insert into bpr_create_scoring_grd (group_id,id,name,type,param_type,ord,mandatory,bpr_id,def_value,name_ru,name_en,name_uz)" +
						" VALUES " +
						" (?,?,?,?,?,?,?,?,?,?,?,?)");
				if(list.get(i).getOrd()!=null&&list.get(i).getOrd().equals("7")){
					if(!list.get(i).getDef_value().equals("")){
						ISLogger.getLogger().error("DEFING = "+list.get(i).getDef_value());
						if(list.get(i).getDef_value().contains(" ")){
							list.get(i).setDef_value(list.get(i).getDef_value().replace(" ", ""));
						}
						String split [] = list.get(i).getDef_value().split(",");
						String def_value = "";
						for (int j = 0; j < split.length; j++) {
							def_value += split[j].trim();
							if(split[j].contains("-")){
								if(split[j].length() != 9){
									res.setCode(1);
									res.setName("Неверно указанн формат маски "+split[j]);
									return;
								}
							} else {
								if(split[j].length() != 5){
									res.setCode(1);
									res.setName("Неверно указанн формат маски "+split[j]);
									return;
								}
							}
							if(j<split.length-1){
								def_value += ",";
							}
						};
						list.get(i).setDef_value(def_value);
					}
				}
				if((i+1)==5){
					ISLogger.getLogger().error("5й = "+list.get(i).getDef_value());
				}
				if((i+1)==5&&(list.get(i).getDef_value()==null||list.get(i).getDef_value().equals("")||list.get(i).getDef_value().equals("null"))){
					list.get(i).setDef_value("0");
				}
				ps.setInt(1, list.get(i).getGroup_id());
				ISLogger.getLogger().error("group_id = "+list.get(i).getGroup_id());
				ps.setInt(2, (i+1));
				ISLogger.getLogger().error("id = "+(i+1));
				ps.setString(3, list.get(i).getName());
				ISLogger.getLogger().error("NAME = "+list.get(i).getName());
				ps.setString(4, list.get(i).getType());
				ISLogger.getLogger().error("TYPE = "+list.get(i).getType());
				ps.setString(5, list.get(i).getParam_type());
				ISLogger.getLogger().error("getParam_type = "+list.get(i).getParam_type());
				ps.setString(6, list.get(i).getOrd());
				ISLogger.getLogger().error("getOrd = "+list.get(i).getOrd());
				ps.setString(7, list.get(i).getMandatory());
				ISLogger.getLogger().error("getMandatory = "+list.get(i).getMandatory());
				ps.setString(8, list.get(i).getBpr_id());
				ISLogger.getLogger().error("BPR ID = "+list.get(i).getBpr_id());
				ps.setString(9, list.get(i).getDef_value()+tmp);
				ISLogger.getLogger().error("DEF VALUE = "+list.get(i).getDef_value());
				ps.setString(10, list.get(i).getName_ru());
				ps.setString(11, list.get(i).getName_en());
				ps.setString(12, list.get(i).getName_uz());
				ps.execute();
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			Utils.rollback(c);
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	protected static void update(List<Scoring> list,Integer bpr_id,Integer scoring_type,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			MainScoringService.updateScoringType(c, bpr_id, scoring_type);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getOrd()!=null&&list.get(i).getOrd().equals("7")){
					if(list.get(i).getDef_value().contains(" ")){
						list.get(i).setDef_value(list.get(i).getDef_value().replace(" ", ""));
					}
					String split [] = list.get(i).getDef_value().split(",");
					String def_value = "";
					for (int j = 0; j < split.length; j++) {
						def_value += split[j].trim();
						if(split[j].contains("-")){
							if(split[j].length()!=9){
								res.setCode(1);
								res.setName("Неверно указанн формат маски "+split[j]);
								return;
							}
						} else {
							if(split[j].length()!=5){
								res.setCode(1);
								res.setName("Неверно указанн формат маски "+split[j]);
								return;
							}
						}
						if(j<split.length-1){
							def_value += ",";
						}
					};
					list.get(i).setDef_value(def_value);
				}
				if((i+1)==5){
					ISLogger.getLogger().error("5й = "+list.get(i).getDef_value());
				}
				ps = c.prepareStatement("update bpr_create_scoring_grd set bpr_create_scoring_grd.def_value=? where bpr_create_scoring_grd.bpr_id=? and bpr_create_scoring_grd.id=?");
				ps.setString(1, list.get(i).getDef_value());
				ISLogger.getLogger().error("def value = "+list.get(i).getDef_value());
				ps.setString(2, list.get(i).getBpr_id());
				ISLogger.getLogger().error("BPR ID = "+list.get(i).getBpr_id());
				ps.setInt(3, list.get(i).getId());
				ISLogger.getLogger().error("ID = "+list.get(i).getId());
				ps.execute();
			}
			c.commit();
		} catch (Exception e) {
			Utils.rollback(c);
			e.printStackTrace();
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
}
