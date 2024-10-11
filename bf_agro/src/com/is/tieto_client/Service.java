package com.is.tieto_client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.is.ConnectionPool;

public class Service {
	
@SuppressWarnings("unchecked")
public static Vector<Tieto_client> getClient() throws SQLException{
		Vector<Tieto_client> Result = new Vector<Tieto_client>();
		Connection C0;
		C0 = ConnectionPool.getTConnection();
		PreparedStatement ps = C0.prepareStatement(psql1);
		ResultSet rs = ps.executeQuery();
		
		
		
		
		while (rs.next()){
			
			
			Tieto_client tc = new Tieto_client();
			
			tc.setCLIENT(rs.getString("CLIENT"));
			tc.setBANK_C(rs.getString("BANK_C"));
			tc.setREC_DATE(rs.getDate("REC_DATE"));
			tc.setF_NAMES(rs.getString("F_NAMES"));
			tc.setSURNAME(rs.getString("SURNAME"));
			tc.setTITLE(rs.getString("TITLE"));
			tc.setM_NAME(rs.getString("M_NAME"));
			tc.setB_DATE(rs.getDate("B_DATE"));
			tc.setRESIDENT(rs.getString("RESIDENT"));
			tc.setR_PHONE(rs.getInt("R_PHONE"));
			Result.add(tc);
		//	Result=(Vector<Tieto_client>)rs;
			
			
		}

return Result;

	}

	private static String psql1 ="select t.client, " +
			"t.bank_c, " +
			"t.rec_date, " +
			"t.f_names, " +
			"t.surname, " +
			"t.title, " +
			"t.m_name, " +
			"t.b_date, " +
			"t.resident, " +
			"t.r_phone " +
			"from izd_clients t " +
			"where rownum < 20"; 

	
	}







