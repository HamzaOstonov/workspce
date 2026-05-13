package com.is.file_reciever_srv.recievers.tieto_files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.utils.CheckNull;

public class TietoService
{
	private static SimpleDateFormat sdf_parse = new SimpleDateFormat("yyyyMMddhhmmss");
	
	private Connection c;
	private Long fr_id;
	private String file_name;
	private Long tieto_file_id;
	
	public TietoService(Connection c, Long fr_id, String file_name)
	{
		super();
		this.c = c;
		this.fr_id = fr_id;
		this.file_name = file_name;
	}

	public void process_file() throws SQLException, IOException, NumberFormatException, ParseException
	{
		this.tieto_file_id = get_tieto_file_id(c);
		this.save_tieto_file(tieto_file_id, fr_id);
		
		File fl = new File(file_name);
		FileInputStream fis = new FileInputStream(fl);
		BufferedReader file_reader = new BufferedReader(new InputStreamReader(fis));
		String line = file_reader.readLine();
		while(line != null)
		{
			if(line.substring(0, 2).equals("00"))
				save_header_record(line);
			else if(line.substring(0, 2).equals("10"))
				save_record(line);
			else if(line.substring(0, 2).equals("99"))
				save_footer_record(line);
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
			ps = c.prepareStatement(
					"insert into tieto_expt_headers " +
					"(id, tieto_file_id, record_type, line_number, create_date, " +
					"field) values (?, ?, ?, ?, ?, ?)");
			TietoHeaderRecord rec = new TietoHeaderRecord(
					get_record_id(c), 
					tieto_file_id, 
					record.substring(0, 2).trim(), 
					Long.parseLong(record.substring(2, 10).trim()),
					sdf_parse.parse(record.substring(10, 24)),
					record.substring(24).trim()
					);
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getTieto_file_id());
			ps.setString(3, rec.getRecord_type());
			ps.setLong(4, rec.getLine_number());
			ps.setDate(5, new java.sql.Date(rec.getCreate_date().getTime()));
			ps.setString(6, rec.getField());
			ps.execute();
		}
		finally
		{
			if(ps!=null)ps.close();
		}
	}
	
	private void save_footer_record(String record) throws SQLException, NumberFormatException, ParseException
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"insert into tieto_expt_footers " +
					"(id, tieto_file_id, record_type, line_number, " +
					"create_date, field) " +
					"values (?, ?, ?, ?, ?, ?)");
			TietoFooterRecord rec = new TietoFooterRecord(
					get_record_id(c), 
					tieto_file_id, 
					record.substring(0, 2).trim(), 
					Long.parseLong(record.substring(2, 10).trim()),
					sdf_parse.parse(record.substring(10, 24)),
					record.substring(24).trim()
					);
			
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getTieto_file_id());
			ps.setString(3, rec.getRecord_type());
			ps.setLong(4, rec.getLine_number());
			ps.setDate(5, new java.sql.Date(rec.getCreate_date().getTime()));
			ps.setString(6, rec.getField());
			ps.execute();
		}
		finally
		{
			if(ps!=null)ps.close();
		}
	}
	
	private void save_record(String record) throws SQLException, NumberFormatException, ParseException
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"insert into tieto_expt_records (" +
					"id, " +
					"tieto_file_id, " +
					"state_id, " +
					"record_type, " +
					"line_number, " +
					"mfo_short, " +
					"tranz_acct, " +
					"fullname, " +
					"deb_cred, " +
					"card_number, " +
					"tran_type, " +
					"tran_amt, " +
					"currency, " +
					"tran_date, " +
					"country_code, " +
					"mcc_code, " +
					"mfo_full, " +
					"merchant_title, " +
					"passport_info, " +	
					"comission, " +
					"merchant_code, " +
					"terminal_code, " +
					"city " +
					") values (" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			TietoRecord rec = parse_record(record);
			rec.setId(get_record_id(c));
			rec.setTieto_file_id(tieto_file_id);
			rec.setState_id(1);
			
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getTieto_file_id());
			ps.setInt(3, rec.getState_id());
			ps.setString(4, rec.getRecord_type());
			ps.setLong(5, rec.getLine_number());
			ps.setString(6, rec.getMfo_short());
			ps.setString(7, rec.getTranz_acct());
			ps.setString(8, rec.getFullname());
			ps.setString(9, rec.getDeb_cred());
			ps.setString(10, rec.getCard_number());
			ps.setString(11, rec.getTran_type());
			ps.setLong(12, rec.getTran_amt());
			ps.setString(13, rec.getCurrency());
			ps.setDate(14, new java.sql.Date(rec.getTran_date().getTime()));
			ps.setString(15, rec.getCountry_code());
			ps.setString(16, rec.getMcc_code());
			ps.setString(17, rec.getMfo_full());
			ps.setString(18, rec.getMerchant_title());
			ps.setString(19, rec.getPassport_info());
			ps.setLong(20, rec.getComission());
			ps.setString(21, rec.getMerchant_code());
			ps.setString(22, rec.getTerminal_code());
			ps.setString(23, rec.getCity());


			
			ps.execute();
		}
		finally
		{
			if(ps!=null) ps.close();
		}
	}
	
	private TietoRecord parse_record(String record) throws NumberFormatException, ParseException
	{
		String[] splittedLine = record.split("\\|");	
		
		return new TietoRecord(
				null, 
				null, 
				null, 
				splittedLine[0].substring(0, 2).trim(), 
				Long.parseLong(splittedLine[0].substring(2, 10).trim()),				
				splittedLine[1].trim(),
				splittedLine[2].trim(),
				splittedLine[3].trim(),
				splittedLine[4].trim(),
				splittedLine[5].trim(),				
				splittedLine[6].trim(),				
				Long.parseLong(splittedLine[7].trim()),				
				splittedLine[8].trim(),				
				sdf_parse.parse(splittedLine[9].trim()),				
				splittedLine[10].trim(),
				splittedLine[11].trim(),
				splittedLine[12].trim(),
				splittedLine[13].trim(),
				splittedLine[14].trim(),				
				Long.parseLong(splittedLine[15].trim().equals("") ? "0" : splittedLine[15].trim()),
				splittedLine[16].trim(),
				splittedLine[17].trim(),
				splittedLine[18].trim()
				);
	}
	
	private void save_tieto_file(Long tieto_file_id, Long fr_file_id) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			 ps = c.prepareStatement("insert into tieto_files " +
			 		"(id, fr_file_id, file_type_id, state_id) values (?, ?, ?, ?)");
			 ps.setLong(1, tieto_file_id);
			 ps.setLong(2, fr_file_id);
			 ps.setLong(3, Constants.XPT_FILE_TYPE_ID);
			 ps.setLong(4, Constants.FILE_ACCEPTED);
			 ps.execute();
		}
		finally
		{
			if(ps != null) ps.close();
		}
	}
	
	private Long get_tieto_file_id(Connection c) throws SQLException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			 ps = c.prepareStatement("select seq_tieto_files.nextval res from dual");
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
			 ps = c.prepareStatement("select seq_tieto_expt_records.nextval res from dual");
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
	
	public static String getRate() {
		long rate = 0;
		String rateString = "";
		
		Connection c = null;		
		try
		{
			c = ConnectionPool.getConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT course FROM ss_course " +
					"WHERE currency = 840 " +
					"AND date_act = (SELECT MAX(date_act) FROM ss_course)");
			if(rs.next()) {
				rate = rs.getLong("course") / 100;
				rateString += rate;
			}
		}
		catch (SQLException e)
		{
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			ConnectionPool.close(c);
		}
		
		return rateString;
	}
}
