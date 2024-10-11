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

public class RealestateService {
	
	public static void create(LdGuarr current,List<RealestateCadastr> list,String alias,Res res){
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
				if(!list.get(i).getBool()){
					ps = c.prepareStatement("SELECT SEQ_BPR_LD_GUAR_CADASTRE.NEXTVAL id FROM DUAL");
					rs = ps.executeQuery();
					if(rs.next()){
						list.get(i).setId_cadastre(rs.getInt("id"));
					}
					ps = c.prepareStatement("SELECT SEQ_BPR_LD_GUAR_BLOCKS.NEXTVAL id FROM DUAL");
					rs = ps.executeQuery();
					if(rs.next()){
						list.get(i).setId_blocks(rs.getInt("id"));
					}
					ps = c.prepareStatement("SELECT SEQ_BPR_LD_FOR_PK.NEXTVAL id FROM DUAL");
					rs = ps.executeQuery();
					if(rs.next()){
						list.get(i).setPk(rs.getString("id"));
					}
					ps = c.prepareStatement("INSERT INTO BPR_LD_GUAR_BLOCKS " +
						" (id_blocks," +
						" id_nn," +
						" block_name," +
						" description," +
						" square," +
						" cost," +
						" bpr_id," +
						" pk," +
						" PK_LD_GUAR)" +
						" VALUES " +
						" (?,?,?,?,?,?,?,?,?)");
					ps.setInt(1, list.get(i).getId_blocks());
					ps.setString(2, list.get(i).getId_nn());
					ps.setString(3, list.get(i).getBlock_name());
					ps.setString(4, list.get(i).getDescription());
					ps.setString(5, list.get(i).getLd_square());
					ps.setString(6, list.get(i).getCost());
					ps.setString(7, list.get(i).getBpr_id());
					ps.setString(8, list.get(i).getPk());
					ps.setLong(9, current.getId());
					ps.executeUpdate();
					ps = c.prepareStatement("INSERT INTO BPR_LD_GUAR_CADASTRE " +
						" (id_cadastre," +
						" cadastre_type," +
						" certificate_num," +
						" certificate_date," +
						" cadastre_num," +
						" reyestr_num," +
						" square," +
						" ovnership," +
						" bpr_id," +
						" pk," +
						" PK_LD_GUAR)" +
						" VALUES " +
						" (?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1, list.get(i).getId_cadastre());
					ps.setString(2, list.get(i).getCadastre_type());
					ps.setString(3, list.get(i).getCertificate_num());
					ps.setDate(4, list.get(i).getCertificate_date()==null?null:new java.sql.Date(list.get(i).getCertificate_date().getTime()));
					ps.setString(5, list.get(i).getCadastre_num());
					ps.setString(6, list.get(i).getReyestr_num());
					ps.setString(7, list.get(i).getSquare());
					ps.setString(8, list.get(i).getOvnership());
					ps.setString(9, list.get(i).getBpr_id());
					ps.setString(10, list.get(i).getPk());
					ps.setLong(11, current.getId());
					ps.executeUpdate();
				}
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
	
	public static void update(LdGuarr current,List<RealestateCadastr> oldcol,List<RealestateCadastr> newcol,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			if(newcol.size()>oldcol.size()||newcol.size()<oldcol.size()){
				remove(current, oldcol, alias, res);
				if(res.getCode()!=1){
					create(current, newcol, alias, res);
					return;
				}
			} 
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
					" start_date=?," +
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
			ps.setLong(39, current.getId());
			ps.executeUpdate();
			for (int i = 0; i < oldcol.size(); i++) {
				ps = c.prepareStatement("UPDATE BPR_LD_GUAR_BLOCKS SET " +
						" id_nn=?," +
						" block_name=?," +
						" description=?," +
						" square=?," +
						" cost=? " +
						" WHERE id_blocks=?");
				ps.setString(1, newcol.get(i).getId_nn());
				ps.setString(2, newcol.get(i).getBlock_name());
				ps.setString(3, newcol.get(i).getDescription());
				ps.setString(4, newcol.get(i).getLd_square());
				ps.setString(5, newcol.get(i).getCost());
				ps.setInt(6, oldcol.get(i).getId_blocks());
				ps.executeUpdate();
				ps = c.prepareStatement("UPDATE BPR_LD_GUAR_CADASTRE SET " +
						" cadastre_type=?," +
						" certificate_num=?," +
						" certificate_date=?," +
						" cadastre_num=?," +
						" reyestr_num=?," +
						" square=?," +
						" ovnership=? " +
						" WHERE id_cadastre=?");
				ps.setString(1, newcol.get(i).getCadastre_type());
				ps.setString(2, newcol.get(i).getCertificate_num());
				ps.setDate(3, newcol.get(i).getCertificate_date()==null?null:new java.sql.Date(oldcol.get(i).getCertificate_date().getTime()));
				ps.setString(4, newcol.get(i).getCadastre_num());
				ps.setString(5, newcol.get(i).getReyestr_num());
				ps.setString(6, newcol.get(i).getSquare());
				ps.setString(7, newcol.get(i).getOvnership());
				ps.setInt(8, oldcol.get(i).getId_cadastre());
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

	public static void remove(LdGuarr current,List<RealestateCadastr> list,String alias,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_ld_guarr where id=?");
			ps.setLong(1, current.getId());
			ps.execute();
			for (int i = 0; i < list.size(); i++) {
				ps = c.prepareStatement("delete from BPR_LD_GUAR_CADASTRE where id_cadastre=?");
				ps.setInt(1, list.get(i).getId_cadastre());
				ps.execute();
				ps = c.prepareStatement("delete from BPR_LD_GUAR_BLOCKS where id_blocks=?");
				ps.setInt(1, list.get(i).getId_blocks());
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
	
	public static List<RealestateCadastr> getList(int bprid,Long id_guarr,String branch){
		List<RealestateCadastr> list = new LinkedList<RealestateCadastr>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(branch);
			s = c.createStatement();
			rs = s.executeQuery("select * from Bpr_Ld_Guar_Blocks,BPR_LD_GUAR_CADASTRE where BPR_LD_GUAR_CADASTRE.Bpr_Id="+bprid+" and Bpr_Ld_Guar_Blocks.Bpr_Id="+bprid+" and BPR_LD_GUAR_CADASTRE.Pk_Ld_Guar="+id_guarr+" and Bpr_Ld_Guar_Blocks.Pk_Ld_Guar="+id_guarr+" and  BPR_LD_GUAR_CADASTRE.Bpr_Id=Bpr_Ld_Guar_Blocks.Bpr_Id and BPR_LD_GUAR_CADASTRE.Pk=Bpr_Ld_Guar_Blocks.Pk");
			while (rs.next()) {
				RealestateCadastr temp = new RealestateCadastr();
				temp.setId_cadastre(rs.getInt("id_cadastre"));
				temp.setOvnership(rs.getString("ovnership"));
				temp.setSquare(rs.getString("square"));
				temp.setReyestr_num(rs.getString("reyestr_num"));
				temp.setCadastre_num(rs.getString("cadastre_num"));
				temp.setCertificate_date(rs.getDate("certificate_date"));
				temp.setCertificate_num(rs.getString("certificate_num"));
				temp.setCadastre_type(rs.getString("cadastre_type"));
				temp.setId_blocks(rs.getInt("id_blocks"));
				temp.setId_nn(rs.getString("id_nn"));
				temp.setBlock_name(rs.getString("block_name"));
				temp.setDescription(rs.getString("description"));
				temp.setLd_square(rs.getString("square"));
				temp.setCost(rs.getString("cost"));
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
