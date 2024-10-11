package com.is.file_reciever_srv.recievers.visa.onus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;

import com.is.ConnectionPool;
import com.is.ISLogger;


public class Onus_service
{
	private static SimpleDateFormat sdf_parse = new SimpleDateFormat("yyyyMMddhhmmss");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private Connection c;
	private Long fr_id;
	private String file_name;
	private Long empc_file_id;
	
	public Onus_service(Connection c, Long fr_id, String file_name)
	{
		super();
		this.c = c;
		this.fr_id = fr_id;
		this.file_name = file_name;
	}

	public void process_file() throws SQLException, IOException, NumberFormatException, ParseException
	{
		this.empc_file_id = get_empc_file_id(c);
		this.save_empc_file(empc_file_id, fr_id);
		
		File fl = new File(file_name);
		FileInputStream fis = new FileInputStream(fl);
		BufferedReader file_reader = new BufferedReader(new InputStreamReader(fis));
		String line = file_reader.readLine();
		while(line != null)
		{
			//if(line.substring(0, 2).equals("00"))
			//	save_header_record(line);
			//else if(line.substring(0, 2).equals("10"))
				save_record(line);
			//else if(line.substring(0, 2).equals("99"))
			//	save_footer_record(line);
			line = file_reader.readLine();
		}
		file_reader.close();
		fis.close();
	}
	
	private void save_header_record(String record) throws SQLException, NumberFormatException, ParseException
	{
		PreparedStatement ps = null;
		try
		{
			ISLogger.getLogger().info("EMPC file reciever: EXPT_service line 65 ");
			ps = c.prepareStatement(
					"insert into visa_onus_headers " +
					"(id, empc_file_id, record_type, line_number, create_date, " +
					"user_id, bank_c, groupc) values (?, ?, ?, ?, ?, ?, ?, ?)");
			Onus_header_record rec = new Onus_header_record(
					get_record_id(c), 
					empc_file_id, 
					record.substring(0, 2).trim(), 
					Long.parseLong(record.substring(2, 10).trim()),
					sdf_parse.parse(record.substring(10, 24)),
					record.substring(24, 30).trim(),
					record.substring(30, 32).trim(),
					record.substring(32, 34).trim()
					);
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getEmpc_file_id());
			ps.setString(3, rec.getRecord_type());
			ps.setLong(4, rec.getLine_number());
			ps.setDate(5, new java.sql.Date(rec.getCreate_date().getTime()));
			ps.setString(6, rec.getUser_id());
			ps.setString(7, rec.getBank_c());
			ps.setString(8, rec.getGroupc());
			ps.execute();
		}
		finally
		{
			ISLogger.getLogger().info("EMPC file reciever: EXPT_service line 92 ");
			if(ps!=null)ps.close();
		}
	}
	
	private void save_footer_record(String record) throws SQLException, NumberFormatException, ParseException
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"insert into visa_onus_footers " +
					"(id, empc_file_id, record_type, line_number, " +
					"create_date, user_id, check_sum) " +
					"values (?, ?, ?, ?, ?, ?, ?)");
			Onus_footer_record rec = new Onus_footer_record(
					get_record_id(c), 
					empc_file_id, 
					record.substring(0, 2).trim(), 
					Long.parseLong(record.substring(2, 10).trim()),
					sdf_parse.parse(record.substring(10, 24)),
					record.substring(24, 30).trim(),
					Math.round(Double.parseDouble(record.substring(30).trim())*100d)
					);
			
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getEmpc_file_id());
			ps.setString(3, rec.getRecord_type());
			ps.setLong(4, rec.getLine_number());
			ps.setDate(5, new java.sql.Date(rec.getCreate_date().getTime()));
			ps.setString(6, rec.getUser_id());
			ps.setLong(7, rec.getCheck_sum());
			ps.execute();
		}
		finally
		{
			if(ps!=null)ps.close();
		}
	}
	
	private void save_record(String record) throws SQLException, NumberFormatException, ParseException
	{
		//Long hamza= Long.parseLong("000000088.93");//xato berdi , java.lang.NumberFormatException: For input string: "000000088.93"
		//System.out.println("aa1="+hamza);
		
		//hamza= Long.parseLong("000000088,93");
		//System.out.println("aa2="+hamza);
		
	
		PreparedStatement ps = null;
		Onus_record record2 = null;
		try
		{
				ps = c.prepareStatement(
					"insert into visa_onus_records (" +
					"id," +
					"visa_file_id," +
					"state_id," +
					"record_type," +
					"line_number," +
					
					"send_cmi," +
					"TERM_ID," +
					"rec_cmi," +
					"TRANZ_ACCT," +
					"TRAN_AMT," +
					"TRAN_AMT2," +
					"TRAN_TYPE," +
					"TRAN_TYPE2," +
					"DEB_CRED," +

					"code_by_type," +
					"point_code," +
					"MCC_CODE," +
					"COUNTRY," +
					"TRAN_CCY," +
					"TRAN_DATE_TIME," +
					"MERCHANT," +
					"CARD," +
					"merch_country," +
					"branch," +

					"branch2" +

					") values (" +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?) ");
					
			
			
			Onus_record rec = parse_record(record);
			record2=rec;
			rec.setId(get_record_id(c));
			rec.setEmpc_file_id(empc_file_id);
			rec.setState_id(1);
			
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getEmpc_file_id());
			ps.setInt(3, rec.getState_id());
			ps.setString(4, rec.getRecord_type());
			ps.setLong(5, 0L);

			ps.setString(6, rec.getSend_cmi());
			ps.setString(7, rec.getTerm_id());
			ps.setString(8, rec.getRec_cmi());
			ps.setString(9, rec.getTranz_acct());
			ps.setLong(10, rec.getTran_amt());
			ps.setString(11, rec.getTran_amt2());
			ps.setString(12, rec.getTran_type());
			ps.setString(13, rec.getTran_type2());
			ps.setString(14, rec.getDeb_cred());
			ps.setString(15, rec.getCode_by_type());
			ps.setString(16, rec.getPoint_code());
			ps.setString(17, rec.getMcc_code());
			ps.setString(18, rec.getCountry());
			ps.setString(19, rec.getTran_ccy());
			ps.setDate(20, new java.sql.Date(rec.getTran_date_time().getTime()));
			ps.setString(21, rec.getMerchant());
			ps.setString(22, rec.getCard());
			ps.setString(23, rec.getMerch_country());
			ps.setString(24, rec.getBranch());
			ps.setString(25, rec.getBranch2());
			
			//ps.setQueryTimeout(3000);
			ps.execute();
			
		}
			catch(Exception e){
			
			ISLogger.getLogger().error("Ошибка в строке >>> "+record2.toString());
			ISLogger.getLogger().error(e);
			
			}
		finally
		{
			if(ps!=null) ps.close();
		}
	}
	
	private Onus_record parse_record(String record) throws NumberFormatException, ParseException
	{
		/*int size=0;
		 size=record.length();
		
		if(size==347 ){*/
		
		//Long hamza= Long.parseLong("000000088.93");
		//System.out.println("aa1="+hamza);
		
		//hamza= Long.parseLong("000000088,93");
		//System.out.println("aa2="+hamza);
		
		
		Onus_record rs = new Onus_record();
        String[] splitLn = record.split("\\;");	
		rs.setSend_cmi(splitLn[0]);
		rs.setTerm_id(splitLn[1]);
		rs.setRec_cmi(splitLn[2]);
		rs.setTranz_acct(splitLn[3]);
		rs.setTran_amt((long)(Float.parseFloat(splitLn[4])*100));
		rs.setTran_amt2(splitLn[5]);
		rs.setTran_type(splitLn[6]);
		rs.setTran_type2(splitLn[7]);
		rs.setDeb_cred(splitLn[8]);

		rs.setCode_by_type(splitLn[10]);
		rs.setPoint_code(splitLn[11]);
		rs.setMcc_code(splitLn[12]);
		rs.setCountry(splitLn[13]);
		rs.setTran_ccy(splitLn[14]);
		rs.setTran_date_time(sdf.parse(splitLn[15]));
		rs.setMerchant(splitLn[16]);
		rs.setCard(splitLn[17]);
		rs.setMerch_country(splitLn[18]);
		rs.setBranch(splitLn[19]);

		rs.setBranch2(splitLn[21]);
		
		

		return rs;
		/*
		}else {
			
			return new Onus_record(
					null, 
					null, 
					null, 
					record.substring(0, 2).trim(), 
					Long.parseLong(record.substring(2, 10).trim()),
					record.substring(10, 18).trim(),
					record.substring(18, 52).trim(),
					record.substring(52, 55).trim(),
					record.substring(55, 74).trim(),
					record.substring(74, 82).trim(),
					record.substring(82, 94).trim(),
					sdf_parse.parse(record.substring(94, 108)),
					sdf_parse.parse(record.substring(108, 122)),
					sdf_parse.parse(record.substring(122, 136)),
					record.substring(136, 161).trim(),
					record.substring(161, 164).trim(),
					record.substring(164, 165).trim(),
					record.substring(165, 168).trim(),
					Math.round(Double.parseDouble(record.substring(168, 181).trim())*100d),
					Math.round(Double.parseDouble(record.substring(181, 194).trim())*100d),
					record.substring(194, 195).trim(),
					record.substring(195, 199).trim(),
					record.substring(199, 214).trim(),
					record.substring(214, 241).trim(),
					record.substring(241, 244).trim(),
					record.substring(244, 264).trim(),
					Long.parseLong(record.substring(264, 278).trim()),
					Long.parseLong(record.substring(278, 290).trim()),
					Long.parseLong(record.substring(290, 300).trim()),
					record.substring(300, 305).trim(),
					record.substring(305, 313).trim(),
					record.substring(313,347).trim(),
					record.substring(347,350).trim(),
					record.substring(350,363).trim().replace(".", "")
					
					);
		}*/
	}
	
	private void save_empc_file(Long empc_file_id, Long fr_file_id) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			 ps = c.prepareStatement("insert into visa_files " +
			 		"(id, fr_file_id, file_type_id, state_id) values (?, ?, ?, ?)");
			 ps.setLong(1, empc_file_id);
			 ps.setLong(2, fr_file_id);
			 ps.setInt(3, 1);
			 ps.setInt(4, 1);
			 ps.execute();
		}
		finally
		{
			if(ps != null) ps.close();
		}
	}
	
	private Long get_empc_file_id(Connection c) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			 ps = c.prepareStatement("select seq_visa_files.nextval res from dual");
			 rs = ps.executeQuery();
			 rs.next();
			 return rs.getLong("res");
		}
		finally
		{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}
	}
	
	private Long get_record_id(Connection c) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			 ps = c.prepareStatement("select seq_visa_file_record.nextval res from dual");
			 rs = ps.executeQuery();
			 rs.next();
			 return rs.getLong("res");
		}
		finally
		{
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		}
	}


}
