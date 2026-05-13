package com.is.dper_info.reports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.zkoss.util.media.AMedia;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bfreport.poireports.PoiReport;
import com.is.dper_info.reports.SS_dblink_branch;
import com.is.dper_info.settings.Ss_dper_dop;
import com.is.utils.CheckNull;

public class DperReports extends PoiReport{
	private String sql="select a.act,a.sng,a.code_str,a.char_code,UPPER(a.name) name,c.veoper_inrep,c.rezident,"+
           " c.rez_count,   c.rez_sum,"+
           " c.norez_count, c.norez_sum"+
           " from"+
           " (select b.region_,b.strana_code_str,b.strana_char_code,b.strana_name,b.veoper_inrep,b.rezident,"+
           " sum(decode(b.rezident,1,b.count_,2,0)) rez_count,   sum(decode(b.rezident,1,b.summa_,2,0))/100 rez_sum,"+
           " sum(decode(b.rezident,2,b.count_,1,0)) norez_count, sum(decode(b.rezident,2,b.summa_,1,0))/100 norez_sum"+
           " from dper_bigrep b"+
           " where ses_id=USERENV(''SESSIONID'') and b.rep_sign=1"+
           " and b.kind=1"+  // 1 получать
           " group by b.region_,b.strana_code_str,b.strana_char_code,b.strana_name,b.veoper_inrep,b.rezident"+
           " ) c, s_str a"+
           " where a.code_str= c.strana_code_str (+) and a.code_str<>'860' and a.act='A'"+
           " and a.sng <8"+
           " order by a.sng,a.name,c.veoper_inrep,c.rezident";
	
	private String filial_p1 = "select sr.region_nam ,t.branch "+
			" ,t.mbranch||' '||ss.name  "+
           " ,t.mtcn , t.veoper "+
           " ,dper.GetNameVeOper(t.veoper) "+ 
           " ,decode(t.kind,0,' Получить',1,'Отправить',2,'Возврат',to_char(t.kind)) "+
           " ,t.strs||' '||ok_service.GET_S_STR(t.strs) "+
           " ,t.strr||' '||ok_service.GET_S_STR(t.strr) "+
           " ,t.REGION_OFFSHOR "+
           " ,t.distr "+
           " ,ok_service.GET_DISTRNAME(t.distr) "+ 
           " ,t.summa/power(10,report.GetScale(t.currency)) "+ 
           " ,dper.GetNameVal(t.currency) "+
           " ,info.GetCourse(t.currency,'000',1,to_date(?,'dd.mm.yyyy')) "+ 
           " ,t.v_date "+
           " ,t.client "+
           " ,t.client_name1 "+ 
           " ,t.client_name2 "+
           " ,t.client_name3 "+
           " ,t.rezident "+
           " ,t.CLIENT_GRSTR "+ 
           " ,decode(t.doc_id,1,'1 - Пасспорт гражданина Республики Узбекистан',2,'2 - Воинское удостоверение',3,'3 - Служебное удостоверение',4,'4 - Пасспорт иностранного гражданина',5,'5 - Вид на жительство',6,'6 - Биометрический пасспорт гражданина Республики Узбекистан',9,'9 - Другое', to_char(t.doc_id)) "+
           " ,t.doc_series "+
           " ,t.doc_number "+
           " ,t.doc_issue "+
           " ,t.doc_date_issue "+ 
           " ,t.client_i "+ 
           " ,t.CLIENT_I2 "+
           " ,t.CLIENT_I3 "+
           " ,t.CLIENT_I6 "+
           " ,t.CLIENT_I8 "+
           " ,t.CLIENT_I9 "+
           " ,t.CLIENT_I10 "+
           " ,nvl(to_char(t.CLIENT_I11date,'dd.mm.yyyy'),' ') "+ 
           " ,t.CLIENT_I12 "+
           " ,t.CLIENT_I13CODE_STR||' '||dper.GetNameSTR_FOR(t.CLIENT_I13CODE_STR) "+ 
    " from ";
	private String filial_p2 =".dper_info t, "+
    " s_region sr, s_distr sd, ";
	private String filial_p3 = ".SS_SUBSIDIARY ss "+
    " where t.branch=? "+
    	" and t.v_date >= to_date(?,'dd.mm.yyyy') "+
    " and t.v_date <= to_date(?,'dd.mm.yyyy') "+
    " and sd.DISTR=t.distr "+
    " and sd.REGION_ID=sr.REGION_ID "+
    " and ss.code=t.mbranch "+
    " and ss.branch=t.branch ";
    //" AND t.STATE in (?) "+
    private String filial_p4 = " order by t.branch, t.v_date, t.veoper";
	
	@Override
	public AMedia getRepmd(Map<String, Object> params, Connection c,
			String templf, String outfl) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		AMedia repmd = null;
		StringBuffer sql = null;
		
		java.util.Date fromDate = (java.util.Date) params.get("from_date");
		java.util.Date toDate = (java.util.Date) params.get("to_date");
		
		List<SS_dblink_branch> branches = new ArrayList<SS_dblink_branch>();
		branches.add(new SS_dblink_branch((String)params.get("branch"), (String)params.get("schema")));
		
		if((Boolean)params.get("allBranches")){
			branches = getBranchesFromRegion(c, (String)params.get("region_id"));
		}
		int state = (Integer)params.get("state");
		
		try {
			FileInputStream file = new FileInputStream(new File(templf));// Взяли темлейт
			HSSFWorkbook workbook = new HSSFWorkbook(file);  //открыли книгу
			HSSFSheet sheet = workbook.getSheetAt(0); // Выбрали нужный лист
			
			int rownum = 1;
	        Row row = null;
			Cell cell = null;
			
			
			for(SS_dblink_branch ss: branches){
				try{
					sql = new StringBuffer();
					sql.append(filial_p1).
						append(ss.getUser_name()).
						append(filial_p2).
						append(ss.getUser_name()).
						append(filial_p3).
						append(" AND t.STATE").
						append(state == 3?" in (3) ":" not in (3,6) ").
						append(filial_p4);
					
					ps = c.prepareStatement(sql.toString());
					ps.setDate(1, new java.sql.Date(toDate.getTime()));
					ps.setString(2, ss.getBranch());
					ps.setDate(3, new java.sql.Date(fromDate.getTime()));
					ps.setDate(4, new java.sql.Date(toDate.getTime()));
			        
					rs = ps.executeQuery();
			        
			        while (rs.next()) {
			        	row = sheet.getRow(rownum);
						if (row == null) {
							row = sheet.createRow(rownum);
						}
			        	for(int i = 0; i < 36; i++){
							cell = row.getCell(i);
							if (cell == null) {
								cell = row.createCell(i);
							}
			        		cell.setCellValue(rs.getString(i+1));
			        	}
			        	rownum++;
					}
				}catch(SQLException e){
					ISLogger.getLogger().error(CheckNull.getPstr(e));
					e.printStackTrace();
				}
			}
			
	        sheet.setForceFormulaRecalculation(true);
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
		    workbook.write(out);
		    out.close();
		    byte[] arr = out.toByteArray();
			repmd = new AMedia(outfl, "xls", "application/vnd.ms-excel", arr);
		} catch (Exception e) {
			e.printStackTrace(); ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			if(ps != null){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return repmd;
	}
	
	private List<SS_dblink_branch> getBranchesFromRegion(Connection c,String region_id){
		List<SS_dblink_branch> list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("Select * from  SS_DBLink_Branch WHERE region=? order by branch");
			ps.setString(1, region_id);
			rs = ps.executeQuery();
			list = new ArrayList<SS_dblink_branch>();
			while(rs.next()){
				list.add(new SS_dblink_branch(rs.getString("branch"), 
											rs.getString("db_alias"), 
											rs.getString("name"), 
											rs.getString("user_name"), 
											rs.getString("region")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null){try {ps.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		return list;
	}
	
}
