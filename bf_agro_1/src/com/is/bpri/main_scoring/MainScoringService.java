package com.is.bpri.main_scoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.is.bpri.utils.MyComboModel;
import com.is.bpri.utils.Utils;

public class MainScoringService {

	protected static List<MyComboModel> getScoringModel(int bpr_type){
		String temp = "";
		if(bpr_type == 1) temp = " where id = "+bpr_type;
		else { temp = " where id <> 1"; }
		return Utils.getComboModel("select id,name,path from ss_scoring_type"+temp, null, "");
	}
	
	protected static String getValue(int bpr_id){
		return Utils.getData("select id_scoring from bpr_scoring_type where bpr_id = "+bpr_id, "");
	}
	
	public static void insertScoringType(Connection c,Integer bpr_id,Integer scoring_type) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			ps = c.prepareStatement("select count(*) from bpr_scoring_type where bpr_id = ?");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
			if(cnt<1){
				ps = c.prepareStatement("insert into bpr_scoring_type (bpr_id,id_scoring) values (?,?)");
				ps.setInt(1, bpr_id);
				ps.setInt(2, scoring_type);
				ps.execute();
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}
	
	public static void updateScoringType(Connection c,Integer bpr_id,Integer scoring_type) throws Exception {
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("update bpr_scoring_type set id_scoring = ? where bpr_id = ?");
			ps.setInt(1, scoring_type);
			ps.setInt(2, bpr_id);
			ps.execute();
		} finally {
			Utils.close(ps);
		}
	}
	
}
