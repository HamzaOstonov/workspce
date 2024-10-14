package com.is.bpri.ldguarrgrids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.LdGuarr;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.Res;

public class EquipmentService {
	public static void create(LdGuarr current,List<LdEquipment> list,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_bpr_ld_guarr.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				current.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO bpr_ld_guarr " +
					" (id," +
					" id_client," +
					" name," +
					" region_id," +
					" distr_id," +
					" massiv," +
					" street," +
					" home," +
					" home_num," +
					" currency," +
					" amount," +
					" doc_num," +
					" doc_date," +
					" klass_o," +
					" code_subject," +
					" inn_reestr," +
					" niki_res1," +
					" notarial_doc_numb," +
					" name2," +
					" notarial_office_num," +
					" insc_name," +
					" insc_inn," +
					" insc_date," +
					" polis_num," +
					" polis_date," +
					" insc_date_cl," +
					" acomp_name," +
					" ser_eval_company," +
					" lis_num," +
					" lis_date," +
					" eval_report_num," +
					" acomp_date," +
					" acomp_summa," +
					" acomp_curr," +
					" economical_zone," +
					" cadastr_org_region," +
					" cadastr_org_distr," +
					" date_operation," +
					" bpr_id," +
					" guar_id," +
					" insc_num," +
					" start_date)" +
					" VALUES " +
					" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, current.getId());
			ps.setString(2, current.getId_client());
			ps.setString(3, current.getName());
			ps.setString(4, current.getRegion_id());
			ps.setString(5, current.getDistr_id());
			ps.setString(6, current.getMassiv());
			ps.setString(7, current.getStreet());
			ps.setString(8, current.getHome());
			ps.setString(9, current.getHome_num());
			ps.setString(10, current.getCurrency());
			ps.setInt(11, current.getAmount());
			ps.setString(12, current.getDoc_num());
			ps.setDate(13, current.getDoc_date()==null?null:new java.sql.Date(current.getDoc_date().getTime()));
			ps.setString(14, current.getKlass_o());
			ps.setString(15, current.getCode_subject());
			ps.setString(16, current.getInn_reestr());
			ps.setString(17, current.getNiki_res1());
			ps.setString(18, current.getNotarial_doc_numb());
			ps.setString(19, current.getName2());
			ps.setString(20, current.getNotarial_office_num());
			ps.setString(21, current.getInsc_name());
			ps.setString(22, current.getInsc_inn());
			ps.setDate(23, current.getInsc_date()==null?null:new java.sql.Date(current.getInsc_date().getTime()));
			ps.setString(24, current.getPolis_num());
			ps.setDate(25, current.getPolis_date()==null?null:new java.sql.Date(current.getPolis_date().getTime()));
			ps.setDate(26, current.getInsc_date_cl()==null?null:new java.sql.Date(current.getInsc_date_cl().getTime()));
			ps.setString(27, current.getAcomp_name());
			ps.setString(28, current.getSer_eval_company());
			ps.setString(29, current.getLis_num());
			ps.setDate(30, current.getLis_date()==null?null:new java.sql.Date(current.getLis_date().getTime()));
			ps.setString(31, current.getEval_report_num());
			ps.setDate(32, current.getAcomp_date()==null?null:new java.sql.Date(current.getAcomp_date().getTime()));
			ps.setLong(33, current.getAcomp_summa());
			ps.setString(34, current.getAcomp_curr());
			ps.setString(35, current.getEconomical_zone());
			ps.setString(36, current.getCadastr_org_region());
			ps.setString(37, current.getCadastr_org_distr());
			ps.setDate(38, current.getDate_operation()==null?null:new java.sql.Date(current.getDate_operation().getTime()));
			ps.setInt(39, current.getBpr_id());
			ps.setString(40, current.getGuar_id());
			ps.setString(41, current.getInsc_num());
			ps.setDate(42, current.getStart_date()==null?null:new java.sql.Date(current.getStart_date().getTime()));
			ps.executeUpdate();
			for (int i = 0; i < list.size(); i++) {
				ps = c.prepareStatement("SELECT SEQ_BPR_LD_GUAR_EQUIPMENT.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if(rs.next()){
					list.get(i).setId(rs.getInt("id"));
				}
				ps = c.prepareStatement("INSERT INTO BPR_LD_GUAR_EQUIPMENT " +
						" (id," +
						" bpr_id," +
						" id_nn," +
						" eq_type," +
						" name," +
						" country," +
						" date_made," +
						" date_operation," +
						" manufacturer," +
						" invent_num," +
						" reason," +
						" doc_num," +
						" doc_date," +
						" price_market," +
						" price_zalog," +
						" eq_type_text," +
						" country_text," +
						" PK_LD_GUAR)" +
						" VALUES " +
						" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, list.get(i).getId());
				ps.setString(2, list.get(i).getBpr_id());
				ps.setString(3, list.get(i).getId_nn());
				ps.setString(4, list.get(i).getEq_type());
				ps.setString(5, list.get(i).getName());
				ps.setString(6, list.get(i).getCountry());
				ps.setString(7, list.get(i).getDate_made());
				ps.setDate(8, list.get(i).getDate_operation()==null?null:new java.sql.Date(list.get(i).getDate_operation().getTime()));
				ps.setString(9, list.get(i).getManufacturer());
				ps.setString(10, list.get(i).getInvent_num());
				ps.setString(11, list.get(i).getReason());
				ps.setString(12, list.get(i).getDoc_num());
				ps.setDate(13, list.get(i).getDoc_date()==null?null:new java.sql.Date(list.get(i).getDoc_date().getTime()));
				ps.setString(14, list.get(i).getPrice_market());
				ps.setString(15, list.get(i).getPrice_zalog());
				ps.setString(16, list.get(i).getEq_type_text());
				ps.setString(17, list.get(i).getCountry_text());
				ps.setLong(18, current.getId());
				ps.executeUpdate();
			}
			c.commit();
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void update(LdGuarr current,List<LdEquipment> oldcol,List<LdEquipment> newcol,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			if(newcol.size()>oldcol.size()||newcol.size()<oldcol.size()){
				remove(current, oldcol, alias, res);
				if(res.getCode()!=1){
					create(current, newcol, alias, res);
					return;
				} else {
					return;
				}
			} 
			System.out.println("Прошел условие");
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_guarr SET " +
					" id_client=?," +
					" name=?," +
					" region_id=?," +
					" distr_id=?," +
					" massiv=?," +
					" street=?," +
					" home=?," +
					" home_num=?," +
					" currency=?," +
					" amount=?," +
					" doc_num=?," +
					" doc_date=?," +
					" klass_o=?," +
					" code_subject=?," +
					" inn_reestr=?," +
					" niki_res1=?," +
					" notarial_doc_numb=?," +
					" name2=?," +
					" notarial_office_num=?," +
					" insc_name=?," +
					" insc_inn=?," +
					" insc_date=?," +
					" polis_num=?," +
					" polis_date=?," +
					" insc_date_cl=?," +
					" acomp_name=?," +
					" ser_eval_company=?," +
					" lis_num=?," +
					" lis_date=?," +
					" eval_report_num=?," +
					" acomp_date=?," +
					" acomp_summa=?," +
					" acomp_curr=?," +
					" economical_zone=?," +
					" cadastr_org_region=?," +
					" cadastr_org_distr=?," +
					" date_operation=?," +
					" insc_num=?," +
					" start_date=?" +
					" WHERE id=? ");
			ps.setString(1, current.getId_client());
			ps.setString(2, current.getName());
			ps.setString(3, current.getRegion_id());
			ps.setString(4, current.getDistr_id());
			ps.setString(5, current.getMassiv());
			ps.setString(6, current.getStreet());
			ps.setString(7, current.getHome());
			ps.setString(8, current.getHome_num());
			ps.setString(9, current.getCurrency());
			ps.setInt(10, current.getAmount());
			ps.setString(11, current.getDoc_num());
			ps.setDate(12, current.getDoc_date()==null?null:new java.sql.Date(current.getDoc_date().getTime()));
			ps.setString(13, current.getKlass_o());
			ps.setString(14, current.getCode_subject());
			ps.setString(15, current.getInn_reestr());
			ps.setString(16, current.getNiki_res1());
			ps.setString(17, current.getNotarial_doc_numb());
			ps.setString(18, current.getName2());
			ps.setString(19, current.getNotarial_office_num());
			ps.setString(20, current.getInsc_name());
			ps.setString(21, current.getInsc_inn());
			ps.setDate(22, current.getInsc_date()==null?null:new java.sql.Date(current.getInsc_date().getTime()));
			ps.setString(23, current.getPolis_num());
			ps.setDate(24, current.getPolis_date()==null?null:new java.sql.Date(current.getPolis_date().getTime()));
			ps.setDate(25, current.getInsc_date_cl()==null?null:new java.sql.Date(current.getInsc_date_cl().getTime()));
			ps.setString(26, current.getAcomp_name());
			ps.setString(27, current.getSer_eval_company());
			ps.setString(28, current.getLis_num());
			ps.setDate(29, current.getLis_date()==null?null:new java.sql.Date(current.getLis_date().getTime()));
			ps.setString(30, current.getEval_report_num());
			ps.setDate(31, current.getAcomp_date()==null?null:new java.sql.Date(current.getAcomp_date().getTime()));
			ps.setLong(32, current.getAcomp_summa());
			ps.setString(33, current.getAcomp_curr());
			ps.setString(34, current.getEconomical_zone());
			ps.setString(35, current.getCadastr_org_region());
			ps.setString(36, current.getCadastr_org_distr());
			ps.setDate(37, current.getDate_operation()==null?null:new java.sql.Date(current.getDate_operation().getTime()));
			ps.setString(38, current.getInsc_num());
			ps.setDate(39, current.getStart_date()==null?null:new java.sql.Date(current.getStart_date().getTime()));
			ps.setLong(40, current.getId());
			ps.executeUpdate();
			for (int i = 0; i < oldcol.size(); i++) {
				ps = c.prepareStatement("UPDATE BPR_LD_GUAR_EQUIPMENT SET " +
						" id_nn=?," +
						" eq_type=?," +
						" name=?," +
						" country=?," +
						" date_made=?," +
						" date_operation=?," +
						" manufacturer=?," +
						" invent_num=?," +
						" reason=?," +
						" doc_num=?," +
						" doc_date=?," +
						" price_market=?," +
						" price_zalog=?," +
						" eq_type_text=?," +
						" country_text=? " +
						" WHERE id=?");
				ps.setString(1, newcol.get(i).getId_nn());
				ps.setString(2, newcol.get(i).getEq_type());
				ps.setString(3, newcol.get(i).getName());
				ps.setString(4, newcol.get(i).getCountry());
				ps.setString(5, newcol.get(i).getDate_made());
				ps.setDate(6, newcol.get(i).getDate_operation()==null?null:new java.sql.Date(oldcol.get(i).getDate_operation().getTime()));
				ps.setString(7, newcol.get(i).getManufacturer());
				ps.setString(8, newcol.get(i).getInvent_num());
				ps.setString(9, newcol.get(i).getReason());
				ps.setString(10, newcol.get(i).getDoc_num());
				ps.setDate(11, newcol.get(i).getDoc_date()==null?null:new java.sql.Date(oldcol.get(i).getDoc_date().getTime()));
				ps.setString(12, newcol.get(i).getPrice_market());
				ps.setString(13, newcol.get(i).getPrice_zalog());
				ps.setString(14, newcol.get(i).getEq_type_text());
				ps.setString(15, newcol.get(i).getCountry_text());
				ps.setInt(16, oldcol.get(i).getId());
				ps.executeUpdate();
			}
			c.commit();
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void remove(LdGuarr current,List<LdEquipment> list,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_ld_guarr where id=?");
			ps.setLong(1, current.getId());
			ps.execute();
			for (int i = 0; i < list.size(); i++) {
				ps = c.prepareStatement("delete from BPR_LD_GUAR_EQUIPMENT where id=?");
				ps.setInt(1, list.get(i).getId());
				ps.execute();
			}
			c.commit();
		} catch (Exception e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static List<LdEquipment> getList(int bprid,Long id_guarr,String branch){
		List<LdEquipment> list = new LinkedList<LdEquipment>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(branch);
			s = c.createStatement();
			rs = s.executeQuery("select * from BPR_LD_GUAR_EQUIPMENT where BPR_LD_GUAR_EQUIPMENT.bpr_id="+bprid+" and BPR_LD_GUAR_EQUIPMENT.PK_LD_GUAR="+id_guarr);
			while (rs.next()) {
				LdEquipment temp = new LdEquipment();
				temp.setId(rs.getInt("id"));
				temp.setId_nn(rs.getString("id_nn"));
				temp.setEq_type(rs.getString("eq_type"));
				temp.setName(rs.getString("name"));
				temp.setCountry(rs.getString("country"));
				temp.setDate_made(rs.getString("date_made"));
				temp.setDate_operation(rs.getDate("date_operation"));
				temp.setManufacturer(rs.getString("manufacturer"));
				temp.setInvent_num(rs.getString("invent_num"));
				temp.setReason(rs.getString("reason"));
				temp.setDoc_num(rs.getString("doc_num"));
				temp.setDoc_date(rs.getDate("doc_date"));
				temp.setPrice_market(rs.getString("price_market"));
				temp.setPrice_zalog(rs.getString("price_zalog"));
				temp.setEq_type_text(rs.getString("eq_type_text"));
				temp.setCountry_text(rs.getString("country_text"));
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(s);
			ConnectionPool.close(c);
		}
		return list;
	}
}
