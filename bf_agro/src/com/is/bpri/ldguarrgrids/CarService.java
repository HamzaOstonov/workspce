package com.is.bpri.ldguarrgrids;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.LdGuarr;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.RefData;
import com.is.utils.Res;

public class CarService {
	
	public static void create(LdGuarr ldguarr,List<CarModel> car,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("SELECT SEQ_bpr_ld_guarr.NEXTVAL id FROM DUAL");
			rs = ps.executeQuery();
			if (rs.next()) {
				ldguarr.setId(rs.getLong("id"));
			}
			ps = c.prepareStatement("INSERT INTO bpr_ld_guarr " +
					"(id," +
					" bpr_id," +
					" currency," +
					" guar_id," +
					" klass_o," +
					" name," +
					" region_id," +
					" distr_id," +
					" massiv," +
					" street," +
					" home," +
					" home_num," +
					" amount," +
					" doc_num," +
					" doc_date," +
					" code_subject," +
					" inn," +
					" inn_reestr," +
					" mfo," +
					" account," +
					" end_date," +
					" name2," +
					" niki_res2," +
					" niki_gr_branch," +
					" niki_gr_code_type," +
					" niki_soogun," +
					" acomp_name," +
					" acomp_date," +
					" acomp_curr," +
					" acomp_summa," +
					" id_client," +
					" notarial_doc_numb," +
					" start_date," +
					" insc_inn," +
					" insc_num," +
					" insc_date," +
					" polis_num," +
					" polis_date," +
					" sowing_area," +
					" niki_res1," +
					" massa," +
					" address," +
					" stock_name," +
					" niki_inn," +
					" sign_depo," +
					" niki_owner," +
					" insc_date_cl," +
					" date_operation," +
					" stock_nominal_value," +
					" stock_count," +
					" stock_diskont," +
					" depositary," +
					" depositary_account," +
					" sertificate_rate," +
					" sertificate_num," +
					" sertificate_ser," +
					" insc_name," +
					" notarial_office_num," +
					" ser_eval_company," +
					" lis_num," +
					" lis_date," +
					" eval_report_num)" +
					" VALUES " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, ldguarr.getId());
			ps.setInt(2, ldguarr.getBpr_id());
			ps.setString(3, ldguarr.getCurrency());
			ps.setString(4, ldguarr.getGuar_id());
			ps.setString(5, ldguarr.getKlass_o());
			ps.setString(6, ldguarr.getName());
			ps.setString(7, ldguarr.getRegion_id());
			ps.setString(8, ldguarr.getDistr_id());
			ps.setString(9, ldguarr.getMassiv());
			ps.setString(10, ldguarr.getStreet());
			ps.setString(11, ldguarr.getHome());
			ps.setString(12, ldguarr.getHome_num());
			ps.setInt(13, ldguarr.getAmount());
			ps.setString(14, ldguarr.getDoc_num());
			ps.setDate(15, ldguarr.getDoc_date()==null?null:new java.sql.Date(ldguarr.getDoc_date().getTime()));
			ps.setString(16, ldguarr.getCode_subject());
			ps.setString(17, ldguarr.getInn());
			ps.setString(18, ldguarr.getInn_reestr());
			ps.setString(19, ldguarr.getMfo());
			ps.setString(20, ldguarr.getAccount());
			ps.setDate(21, ldguarr.getEnd_date()==null?null:new java.sql.Date(ldguarr.getEnd_date().getTime()));
			ps.setString(22, ldguarr.getName2());
			ps.setString(23, ldguarr.getNiki_res2());
			ps.setString(24, ldguarr.getNiki_gr_branch());
			ps.setString(25, ldguarr.getNiki_gr_code_type());
			ps.setString(26, ldguarr.getNiki_soogun());
			ps.setString(27, ldguarr.getAcomp_name());
			ps.setDate(28, ldguarr.getAcomp_date()==null?null:new java.sql.Date(ldguarr.getAcomp_date().getTime()));
			ps.setString(29, ldguarr.getAcomp_curr());
			ps.setLong(30, ldguarr.getAcomp_summa());
			ps.setString(31, ldguarr.getId_client());
			ps.setString(32, ldguarr.getNotarial_doc_numb());
			ps.setDate(33, ldguarr.getStart_date()==null?null:new java.sql.Date(ldguarr.getStart_date().getTime()));
			ps.setString(34, ldguarr.getInsc_inn());
			ps.setString(35, ldguarr.getInsc_num());
			ps.setDate(36, ldguarr.getInsc_date()==null?null:new java.sql.Date(ldguarr.getInsc_date().getTime()));
			ps.setString(37, ldguarr.getPolis_num());
			ps.setDate(38, ldguarr.getPolis_date()==null?null:new java.sql.Date(ldguarr.getPolis_date().getTime()));
			ps.setString(39, ldguarr.getSowing_area());
			ps.setString(40, ldguarr.getNiki_res1());
			ps.setDouble(41, ldguarr.getMassa());
			ps.setString(42, ldguarr.getAddress());
			ps.setString(43, ldguarr.getStock_name());
			ps.setString(44, ldguarr.getNiki_inn());
			ps.setString(45, ldguarr.getSign_depo());
			ps.setString(46, ldguarr.getNiki_owner());
			ps.setDate(47, ldguarr.getInsc_date_cl()==null?null:new java.sql.Date(ldguarr.getInsc_date_cl().getTime()));
			ps.setDate(48, ldguarr.getDate_operation()==null?null:new java.sql.Date(ldguarr.getDate_operation().getTime()));
			ps.setString(49, ldguarr.getStock_nominal_value());
			ps.setString(50, ldguarr.getStock_count());
			ps.setString(51, ldguarr.getStock_diskont());
			ps.setString(52, ldguarr.getDepositary());
			ps.setString(53, ldguarr.getDepositary_account());
			ps.setDouble(54, ldguarr.getSertificate_rate());
			ps.setString(55, ldguarr.getSertificate_num());
			ps.setString(56, ldguarr.getSertificate_ser());
			ps.setString(57, ldguarr.getInsc_name());
			ps.setString(58, ldguarr.getNotarial_office_num());
			ps.setString(59, ldguarr.getSer_eval_company());
			ps.setString(60, ldguarr.getLis_num());
			ps.setDate(61, ldguarr.getLis_date()==null?null:new java.sql.Date(ldguarr.getLis_date().getTime()));
			ps.setString(62, ldguarr.getEval_report_num());
			ps.executeUpdate();
			for (int i = 0; i < car.size(); i++) {
				ps = c.prepareStatement("SELECT SEQ_BPR_LD_GUAR_CAR.NEXTVAL id FROM DUAL");
				rs = ps.executeQuery();
				if (rs.next()) {
					car.get(i).setId(rs.getString("id"));
				}
				ps = c.prepareStatement("insert into BPR_LD_GUAR_CAR (" +
						" car_type," +
						" car_marka," +
						" car_model," +
						" code_country," +
						" date_made," +
						" mileage," +
						" engine_num," +
						" body_num," +
						" color," +
						" state_number," +
						" doc_ser_num," +
						" doc_date," +
						" position," +
						" chassis_number," +
						" bpr_id," +
						" id," +
						" PK_LD_GUAR)" +
						" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, car.get(i).getCar_type());
				ps.setString(2, car.get(i).getCar_marka());
				ps.setString(3, car.get(i).getCar_model());
				ps.setString(4, car.get(i).getCode_country());
				ps.setString(5, car.get(i).getDate_made());
				ps.setString(6, car.get(i).getMileage());
				ps.setString(7, car.get(i).getEngine_num());
				ps.setString(8, car.get(i).getBody_num());
				ps.setString(9, car.get(i).getColor());
				ps.setString(10, car.get(i).getState_number());
				ps.setString(11, car.get(i).getDoc_ser_num());
				ps.setDate(12, car.get(i).getDoc_date()==null?null:new java.sql.Date(car.get(i).getDoc_date().getTime()));
				ps.setString(13, car.get(i).getPosition());
				ps.setString(14, car.get(i).getChassis_number());
				ps.setString(15, car.get(i).getBpr_id()+"");
				ps.setString(16, car.get(i).getId());
				ps.setLong(17, ldguarr.getId());
				ps.executeUpdate();
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(1);
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void update(LdGuarr ldguarr,List<CarModel> oldcar,List<CarModel> car,Res res,String alias) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			if(car.size()>oldcar.size()||car.size()<oldcar.size()){
				remove(ldguarr, oldcar, res,alias);
				if(res.getCode()!=1){
					create(ldguarr, car, res,alias);
					return;
				} else {
					return;
				}
			} 
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("UPDATE bpr_ld_guarr SET " +
					" currency=?," +
					" guar_id=?," +
					" klass_o=?," +
					" name=?," +
					" region_id=?," +
					" distr_id=?," +
					" massiv=?," +
					" street=?," +
					" home=?," +
					" home_num=?," +
					" amount=?," +
					" doc_num=?," +
					" doc_date=?," +
					" code_subject=?," +
					" inn=?," +
					" inn_reestr=?," +
					" mfo=?," +
					" account=?," +
					" end_date=?," +
					" name2=?," +
					" niki_res2=?," +
					" niki_gr_branch=?," +
					" niki_gr_code_type=?," +
					" niki_soogun=?," +
					" acomp_name=?," +
					" acomp_date=?," +
					" acomp_curr=?," +
					" acomp_summa=?," +
					" id_client=?," +
					" notarial_doc_numb=?," +
					" start_date=?," +
					" insc_inn=?," +
					" insc_num=?," +
					" insc_date=?," +
					" polis_num=?," +
					" polis_date=?," +
					" sowing_area=?," +
					" niki_res1=?," +
					" massa=?," +
					" address=?," +
					" stock_name=?," +
					" niki_inn=?," +
					" sign_depo=?," +
					" niki_owner=?," +
					" insc_date_cl=?," +
					" date_operation=?," +
					" stock_nominal_value=?," +
					" stock_count=?," +
					" stock_diskont=?," +
					" depositary=?," +
					" depositary_account=?," +
					" sertificate_rate=?," +
					" sertificate_num=?," +
					" sertificate_ser=?," +
					" insc_name=?," +
					" notarial_office_num=?," +
					" ser_eval_company=?," +
					" lis_num=?," +
					" lis_date=?," +
					" eval_report_num=?" +
					"  WHERE bpr_id=? and id=? ");
			ps.setString(1, ldguarr.getCurrency());
			ps.setString(2, ldguarr.getGuar_id());
			ps.setString(3, ldguarr.getKlass_o());
			ps.setString(4, ldguarr.getName());
			ps.setString(5, ldguarr.getRegion_id());
			ps.setString(6, ldguarr.getDistr_id());
			ps.setString(7, ldguarr.getMassiv());
			ps.setString(8, ldguarr.getStreet());
			ps.setString(9, ldguarr.getHome());
			ps.setString(10, ldguarr.getHome_num());
			ps.setLong(11, ldguarr.getAmount());
			ps.setString(12, ldguarr.getDoc_num());
			ps.setDate(13, ldguarr.getDoc_date()==null?null:new java.sql.Date(ldguarr.getDoc_date().getTime()));
			ps.setString(14, ldguarr.getCode_subject());
			ps.setString(15, ldguarr.getInn());
			ps.setString(16, ldguarr.getInn_reestr());
			ps.setString(17, ldguarr.getMfo());
			ps.setString(18, ldguarr.getAccount());
			ps.setDate(19, ldguarr.getEnd_date()==null?null:new java.sql.Date(ldguarr.getEnd_date().getTime()));
			ps.setString(20, ldguarr.getName2());
			ps.setString(21, ldguarr.getNiki_res2());
			ps.setString(22, ldguarr.getNiki_gr_branch());
			ps.setString(23, ldguarr.getNiki_gr_code_type());
			ps.setString(24, ldguarr.getNiki_soogun());
			ps.setString(25, ldguarr.getAcomp_name());
			ps.setDate(26, ldguarr.getAcomp_date()==null?null:new java.sql.Date(ldguarr.getAcomp_date().getTime()));
			ps.setString(27, ldguarr.getAcomp_curr());
			ps.setLong(28, ldguarr.getAcomp_summa());
			ps.setString(29, ldguarr.getId_client());
			ps.setString(30, ldguarr.getNotarial_doc_numb());
			ps.setDate(31, ldguarr.getStart_date()==null?null:new java.sql.Date(ldguarr.getStart_date().getTime()));
			ps.setString(32, ldguarr.getInsc_inn());
			ps.setString(33, ldguarr.getInsc_num());
			ps.setDate(34, ldguarr.getInsc_date()==null?null:new java.sql.Date(ldguarr.getInsc_date().getTime()));
			ps.setString(35, ldguarr.getPolis_num());
			ps.setDate(36, ldguarr.getPolis_date()==null?null:new java.sql.Date(ldguarr.getPolis_date().getTime()));
			ps.setString(37, ldguarr.getSowing_area());
			ps.setString(38, ldguarr.getNiki_res1());
			ps.setDouble(39, ldguarr.getMassa());
			ps.setString(40, ldguarr.getAddress());
			ps.setString(41, ldguarr.getStock_name());
			ps.setString(42, ldguarr.getNiki_inn());
			ps.setString(43, ldguarr.getSign_depo());
			ps.setString(44, ldguarr.getNiki_owner());
			ps.setDate(45, ldguarr.getInsc_date_cl()==null?null:new java.sql.Date(ldguarr.getInsc_date_cl().getTime()));
			ps.setDate(46, ldguarr.getDate_operation()==null?null:new java.sql.Date(ldguarr.getDate_operation().getTime()));
			ps.setString(47, ldguarr.getStock_nominal_value());
			ps.setString(48, ldguarr.getStock_count());
			ps.setString(49, ldguarr.getStock_diskont());
			ps.setString(50, ldguarr.getDepositary());
			ps.setString(51, ldguarr.getDepositary_account());
			ps.setDouble(52, ldguarr.getSertificate_rate());
			ps.setString(53, ldguarr.getSertificate_num());
			ps.setString(54, ldguarr.getSertificate_ser());
			ps.setString(55, ldguarr.getInsc_name());
			ps.setString(56, ldguarr.getNotarial_office_num());
			ps.setString(57, ldguarr.getSer_eval_company());
			ps.setString(58, ldguarr.getLis_num());
			ps.setDate(59, ldguarr.getLis_date()==null?null:new java.sql.Date(ldguarr.getLis_date().getTime()));
			ps.setString(60, ldguarr.getEval_report_num());
			ps.setInt(61, ldguarr.getBpr_id());
			ps.setLong(62, ldguarr.getId());
			ps.executeUpdate();
			for (int i = 0; i < car.size(); i++) {
				ps = c.prepareStatement("update BPR_LD_GUAR_CAR set " +
						" car_type=?," +
						" car_marka=?," +
						" car_model=?," +
						" code_country=?," +
						" date_made=?," +
						" mileage=?," +
						" engine_num=?," +
						" body_num=?," +
						" color=?," +
						" state_number=?," +
						" doc_ser_num=?," +
						" doc_date=?," +
						" position=?," +
						" chassis_number=?" +
						" where bpr_id=? and id=?");
				ps.setString(1, car.get(i).getCar_type());
				ps.setString(2, car.get(i).getCar_marka());
				ps.setString(3, car.get(i).getCar_model());
				ps.setString(4, car.get(i).getCode_country());
				ps.setString(5, car.get(i).getDate_made());
				ps.setString(6, car.get(i).getMileage());
				ps.setString(7, car.get(i).getEngine_num());
				ps.setString(8, car.get(i).getBody_num());
				ps.setString(9, car.get(i).getColor());
				ps.setString(10, car.get(i).getState_number());
				ps.setString(11, car.get(i).getDoc_ser_num());
				ps.setDate(12, car.get(i).getDoc_date()==null?null:new java.sql.Date(car.get(i).getDoc_date().getTime()));
				ps.setString(13, car.get(i).getPosition());
				ps.setString(14, car.get(i).getChassis_number());
				ps.setInt(15, car.get(i).getBpr_id());
				ps.setString(16, car.get(i).getId());
				ps.executeUpdate();
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static void remove(LdGuarr ldGuarr,List<CarModel> car,Res res,String alias){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection(alias);
			ps = c.prepareStatement("delete from bpr_ld_guarr where id=?");
			ps.setLong(1, ldGuarr.getId());
			ps.execute();
			for (int i = 0; i < car.size(); i++) {
				ps = c.prepareStatement("delete from BPR_LD_GUAR_CAR where id=?");
				ps.setString(1, car.get(i).getId());
				ps.execute();
			}
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static List<CarModel> getCarModels(String bprid,Long id_guarr,String alias){
		Connection c = null;
		List<CarModel> list = new ArrayList<CarModel>();
		Statement st = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection(alias);
			String sql = "select * from BPR_LD_GUAR_CAR where BPR_LD_GUAR_CAR.Bpr_Id="+bprid+" and BPR_LD_GUAR_CAR.PK_LD_GUAR="+id_guarr;
			st = c.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				CarModel car = new CarModel();
				car.setId(rs.getString("id"));
				car.setCar_type(rs.getString("car_type"));
				car.setCar_marka(rs.getString("car_marka"));
				car.setCar_model(rs.getString("car_model"));
				car.setCode_country(rs.getString("code_country"));
				car.setDate_made(rs.getString("date_made"));
				car.setMileage(rs.getString("mileage"));
				car.setEngine_num(rs.getString("engine_num"));
				car.setBody_num(rs.getString("body_num"));
				car.setColor(rs.getString("color"));
				car.setState_number(rs.getString("state_number"));
				car.setDoc_ser_num(rs.getString("doc_ser_num"));
				System.out.println("rs.getString("+"doc_ser_num"+") "+rs.getString("doc_ser_num"));
				car.setDoc_date(rs.getDate("doc_date"));
				car.setPosition(rs.getString("position"));
				car.setChassis_number(rs.getString("chassis_number"));
				car.setBpr_id(rs.getInt("bpr_id"));
				list.add(car);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(st);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	public static List<RefData> getCarType(String alias){
		return Utils.getRefData("select SS_LD_GUAR_CAR_TYPE.ID data,SS_LD_GUAR_CAR_TYPE.NAME label from SS_LD_GUAR_CAR_TYPE order by data", alias);
	}
	
	public static List<RefData> getCarMarka(String car_type,String alias){
		return Utils.getRefData("select ss_ld_guar_car_marka.id data,ss_ld_guar_car_marka.name label from ss_ld_guar_car_marka where ss_ld_guar_car_marka.id_type="+car_type, alias);
	}
	
	public static List<RefData> getCarModel(String car_type,String car_marka,String alias){
		return Utils.getRefData("select ss_ld_guar_car_model.id data,ss_ld_guar_car_model.name label from ss_ld_guar_car_model where ss_ld_guar_car_model.id_marka="+car_marka+" and ss_ld_guar_car_model.id_type="+car_type, alias);
	}
	
	public static List<RefData> getCodeStr(String alias){
		return Utils.getRefData("select s_str.code_str data,s_str.name label from s_str order by label", alias);
	}
	
	public static List<RefData> getYears(String alias){
		return Utils.getRefData("select SS_LD_GUAR_YEAR.name data,SS_LD_GUAR_YEAR.Name label from SS_LD_GUAR_YEAR order by data", alias);
	}
	
	public static List<RefData> getColors(String alias){
		return Utils.getRefData("select SS_LD_GUAR_COLOR.Id data,SS_LD_GUAR_COLOR.Name label from SS_LD_GUAR_COLOR order by data", alias);
	}
	
}
