package com.is.file_reciever_srv.recievers.EMPC.EXPT;

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

import com.is.ConnectionPool;
import com.is.tieto_globuz.fileProcessing.EmpcExptRecord;

public class EXPT_service
{
	private static SimpleDateFormat sdf_parse = new SimpleDateFormat("yyyyMMddhhmmss");
	
	private Connection c;
	private Long fr_id;
	private String file_name;
	private Long empc_file_id;
	
	public EXPT_service(Connection c, Long fr_id, String file_name)
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
					"insert into empc_expt_headers " +
					"(id, empc_file_id, record_type, line_number, create_date, " +
					"user_id, bank_c, groupc) values (?, ?, ?, ?, ?, ?, ?, ?)");
			EXPT_header_record rec = new EXPT_header_record(
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
			if(ps!=null)ps.close();
		}
	}
	
	private void save_footer_record(String record) throws SQLException, NumberFormatException, ParseException
	{
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"insert into empc_expt_footers " +
					"(id, empc_file_id, record_type, line_number, " +
					"create_date, user_id, check_sum) " +
					"values (?, ?, ?, ?, ?, ?, ?)");
			EXPT_footer_tecord rec = new EXPT_footer_tecord(
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
		PreparedStatement ps = null;
		try
		{
			ps = c.prepareStatement(
					"insert into empc_expt_records (" +
					"id," +
					"empc_file_id," +
					"state_id," +
					"record_type," +
					"line_number," +
					"client," +
					"card_acct," +
					"accnt_ccy," +
					"card," +
					"slip_nr," +
					"ref_number," +
					"tran_date_time," +
					"rec_date," +
					"post_date," +
					"deal_desc," +
					"tran_type," +
					"deb_cred," +
					"tran_ccy," +
					"tran_amt," +
					"accnt_amt,"+
					"terminal," +
					"mcc_code," +
					"merchant," +
					"abvr_name," +
					"country," +
					"city," +
					"proc_id," +
					"internal_no," +
					"product," +
					"iss_mfo," +
					"term_id," +
					"tranz_acct" +
					") values (" +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
					"?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			EXPT_record rec = parse_record(record);
			rec.setId(get_record_id(c));
			rec.setEmpc_file_id(empc_file_id);
			rec.setState_id(1);
			
			ps.setLong(1, rec.getId());
			ps.setLong(2, rec.getEmpc_file_id());
			ps.setInt(3, rec.getState_id());
			ps.setString(4, rec.getRecord_type());
			ps.setLong(5, rec.getLine_number());
			ps.setString(6, rec.getClient());
			ps.setString(7, rec.getCard_acct());
			ps.setString(8, rec.getAccnt_ccy());
			ps.setString(9, rec.getCard());
			ps.setString(10, rec.getSlip_nr());
			ps.setString(11, rec.getRef_number());
			ps.setDate(12, new java.sql.Date(rec.getTran_date_time().getTime()));
			ps.setDate(13, new java.sql.Date(rec.getRec_date().getTime()));
			ps.setDate(14, new java.sql.Date(rec.getPost_date().getTime()));
			ps.setString(15, rec.getDeal_desc());
			ps.setString(16, rec.getTran_type());
			ps.setString(17, rec.getDeb_cred());
			ps.setString(18, rec.getTran_ccy());
			ps.setLong(19, rec.getTran_amt());
			ps.setLong(20, rec.getAccnt_amt());
			ps.setString(21, rec.getTerminal());
			ps.setString(22, rec.getMcc_code());
			ps.setString(23, rec.getMerchant());
			ps.setString(24, rec.getAbvr_name());
			ps.setString(25, rec.getCountry());
			ps.setString(26, rec.getCity());
			ps.setLong(27, rec.getProc_id());
			ps.setLong(28, rec.getInternal_no());
			ps.setLong(29, rec.getProduct());
			ps.setString(30, rec.getIss_mfo());
			ps.setString(31, rec.getTerm_id());
			ps.setString(32, rec.getTranz_acct());
			
			ps.execute();
		}
		finally
		{
			if(ps!=null) ps.close();
		}
	}
	
	private EXPT_record parse_record(String record) throws NumberFormatException, ParseException
	{
		return new EXPT_record(
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
				record.substring(313).trim()
				);
	}
	
	private void save_empc_file(Long empc_file_id, Long fr_file_id) throws SQLException
	{
		PreparedStatement ps = null;
		try
		{
			 ps = c.prepareStatement("insert into empc_files " +
			 		"(id, fr_file_id, file_type_id, state_id) values (?, ?, ?, ?)");
			 ps.setLong(1, empc_file_id);
			 ps.setLong(2, fr_file_id);
			 ps.setInt(3, 2);
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
			 ps = c.prepareStatement("select seq_empc_files.nextval res from dual");
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
			 ps = c.prepareStatement("select seq_empc_expt_records.nextval res from dual");
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
