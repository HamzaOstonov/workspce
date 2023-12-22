package com.is.file_reciever_srv.recievers.cb_cash_operaions;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.common.Fr_log_service;
import com.is.file_reciever_srv.ext_utils.ArchEntryFile;
import com.is.file_reciever_srv.ext_utils.ArchiveExtractor;
import com.is.file_reciever_srv.ext_utils.ExtFile;
import com.is.file_reciever_srv.ext_utils.RecDump;
import com.is.file_reciever_srv.ext_utils.Service;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;
import com.is.utils.CheckNull;

public class Request_reciver extends Reciever_class
{
	private static Logger logger = ISLogger.getLogger();
	private static SimpleDateFormat sdf_partition = new SimpleDateFormat("MMyyyy");
	private static SimpleDateFormat sdf_dir = new SimpleDateFormat("yyyy\\MM\\dd\\");
	
	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		System.out.println("Recieves file: " + input_file);
		try
		{
			read_file(new File(input_file), fr_file_id);
		}
		catch(Exception e)
		{
			logger.error(CheckNull.getPstr(e));
		}
	}
	
	private void read_file(File in_file, long fr_file_id) throws Exception
	{
		List<ArchEntryFile> list = new ArrayList<ArchEntryFile>();
		
		list=ArchiveExtractor.getReestrFileFromArj(in_file);
		
		List<Cb_cash_request> requests = new ArrayList<Cb_cash_request>();
		List<Cb_cash_request_reversal> reversals = new ArrayList<Cb_cash_request_reversal>();
		
		Connection c = null;
		try
		{
			c = ConnectionPool.getConnection();
			
			for (ArchEntryFile archEntryFile : list) 
			{
				Fr_log_service.save_log(fr_file_id, null, "start reading internal file "+archEntryFile.getFileName());
				logger.info(in_file.getName() + " : start reading internal file "
						+archEntryFile.getFileName());
				
				long files_cnt = com.is.file_reciever_srv.simple.reciever_class.Util.check_whether_file_is_double(archEntryFile.getFileName(), 
						"ext_in_files", "file_name", c);
				if(files_cnt == 0)
				{
					if(archEntryFile.getFileName().startsWith("#K")) // if request
						requests.addAll(read_request_file(in_file, archEntryFile, fr_file_id, c));
					else if(archEntryFile.getFileName().startsWith("#O")) // if reversal
						reversals.addAll(read_reversal_file(in_file, archEntryFile, fr_file_id, c));
	//				else throw new Exception("File of unknown type:" + archEntryFile.getFileName());
				}
				
				Fr_log_service.save_log(fr_file_id, null, "reading internal file "+archEntryFile.getFileName()+" complete");
				logger.info(in_file.getName() + " : reading internal file "
						+archEntryFile.getFileName()+" complete");
			}
			c.commit();
			move_file_to_archive(in_file);
			logger.info(in_file.getName() + " : file moved to archive");
			
		}
		catch(Exception e)
		{
			c.rollback();
			throw e;
		}
		finally
		{
			if(c != null)c.close();
		}
		
//		Request_service.process_requests(requests);
//		Request_service.create_out_files();
		
//		Reversal_service.process_reversals(reversals);
	}
	
	private List<Cb_cash_request> read_request_file(File in_file, ArchEntryFile archEntryFile, long fr_file_id, Connection c) throws Exception
	{
		long file_id = Service.get_id();
		ExtFile cur_file = null;
		cur_file = new ExtFile(
				1, 
				file_id, 
				"0"+archEntryFile.getFileName().substring(2, 6), 
				archEntryFile.getFileName(), 
				Service.get_date_from_string(in_file.getName().substring(9)), 
				Service.get_10_number_from_36(in_file.getName().substring(6, 8)),//"",//file_num, 
				1,//doc_cnt, 
				1,//doc_amount, 
				"",//last_file_num, 
				0,
				16l,
				fr_file_id
			);
		Service.save_file(c, cur_file);
		
		BufferedReader ibr = new BufferedReader(
				new InputStreamReader(
						new  ByteArrayInputStream(archEntryFile.getFileByte())
						)
				);
		int i = 0;
		String inputLine = "";
		
		long record_id = Service.get_rec_dump_id();
		
		List<Cb_cash_request> requests = new ArrayList<Cb_cash_request>();
		
		for (i = 0; (inputLine = ibr.readLine()) != null; i++) 
		{
			if (inputLine.length() < 5 || i == 0) continue;
			RecDump rd = new RecDump(
			record_id,
			//i,//Long recid, 
			file_id,//Long file_in_id, 
			0l,//Long file_type,
			inputLine,//String rec_dump, 
			0l,//Long file_out_id, 
			0l,//Long general_id,
			0,//String error_code, 
			0,//int state, 
			new Date(),//Date v_date, 
			"",//String err_message
			2700l
			);
			Service.save_rec_dump(c, rd, "00181000000");
			
			Cb_cash_request current_req = Request_service.parse(inputLine);
			current_req.setId(Request_service.get_request_id());
			current_req.setExt_record_id(record_id);
			current_req.setState_id(1);
			requests.add(current_req);
			
			Request_service.save_request(current_req, c);
		}
		
		return requests;
	}
	
	private List<Cb_cash_request_reversal> read_reversal_file(File in_file, ArchEntryFile archEntryFile, long fr_file_id, Connection c) throws Exception
	{
		logger.info("reading reversal file " + archEntryFile.getFileName());
		long file_id = Service.get_id();
		ExtFile cur_file = null;
		cur_file = new ExtFile(
				1, 
				file_id, 
				"0"+archEntryFile.getFileName().substring(2, 6), 
				archEntryFile.getFileName(), 
				Service.get_date_from_string(in_file.getName().substring(9)), 
				Service.get_10_number_from_36(in_file.getName().substring(6, 8)),//"",//file_num, 
				1,//doc_cnt, 
				1,//doc_amount, 
				"",//last_file_num, 
				0,
				17l,
				fr_file_id
			);
		logger.info("1 reading reversal file " + archEntryFile.getFileName());
		Service.save_file(c, cur_file);
		logger.info("2 reading reversal file " + archEntryFile.getFileName());
		BufferedReader ibr = new BufferedReader(
				new InputStreamReader(
						new  ByteArrayInputStream(archEntryFile.getFileByte())
						)
				);
		logger.info("3 reading reversal file " + archEntryFile.getFileName());
		int i = 0;
		String inputLine = "";
		logger.info("4 reading reversal file " + archEntryFile.getFileName());
		long record_id = Service.get_rec_dump_id();
		List<Cb_cash_request_reversal> requests = new ArrayList<Cb_cash_request_reversal>();
		for (i = 0; (inputLine = ibr.readLine()) != null; i++) 
		{
			if (inputLine.length() < 5 || i == 0) continue;
			RecDump rd = new RecDump(
			record_id,
			//i,//Long recid, 
			file_id,//Long file_in_id, 
			0l,//Long file_type,
			inputLine,//String rec_dump, 
			0l,//Long file_out_id, 
			0l,//Long general_id,
			0,//String error_code, 
			0,//int state, 
			new Date(),//Date v_date, 
			"",//String err_message
			2700l
			);
			Service.save_rec_dump(c, rd, "00181000000");
			logger.info("reading reversal line " + inputLine);
			Cb_cash_request_reversal current_rev = Reversal_service.parse(inputLine);
			current_rev.setId(Request_service.get_request_id());
			current_rev.setExt_record_id(record_id);
			current_rev.setState_id(1);
			requests.add(current_rev);
			Reversal_service.save_reversal(current_rev, c);
			logger.info("reversal saved " + current_rev.toString());
		}
		return requests;
	}
	
	private void move_file_to_archive(File in_file)
	{
		String arch_dir = ConnectionPool.getValue("cb_cash_files_arch") + sdf_dir.format(new Date());
		if(! new File(arch_dir).exists()) new File(arch_dir).mkdirs();
		in_file.renameTo(new File(arch_dir + in_file.getName()));
	}
}
