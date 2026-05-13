package com.is.file_reciever_srv.recievers.tieto_file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.file_reciever_srv.common.Fr_log_service;
import com.is.file_reciever_srv.ext_utils.ArchEntryFile;
import com.is.file_reciever_srv.ext_utils.ArchiveExtractor;
import com.is.file_reciever_srv.ext_utils.ExtFile;
import com.is.file_reciever_srv.ext_utils.File_header;
import com.is.file_reciever_srv.ext_utils.RecDump;
import com.is.file_reciever_srv.ext_utils.Service;
import com.is.file_reciever_srv.simple.reciever_class.Reciever_class;
import com.is.utils.CheckNull;



public class Reciever// extends Reciever_class
{

	private static Logger logger = ISLogger.getLogger();
	private static SimpleDateFormat sdf_partition = new SimpleDateFormat("MMyyyy");
	
	//@Override
	public void Recieve_file(String input_file, long fr_file_id, long file_type_id, String branch)
	{
        System.out.println("Recieves file: " + input_file);
        logger.error("Recieves file: " + input_file);		
		Connection c = null;
		com.is.LtLogger.getLogger().error("not err Recieves file: " + input_file);
		try
		{
			c = ConnectionPool.getConnection();
			logger.error("getconnection ok ");
			
			File in_file = new File(input_file);
			 
			FileReader fileReader = new FileReader(in_file);
			logger.error("fileReader ok " );
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			
			long file_id = Service.get_id(c);
			logger.error("file_id= "+file_id );
			ExtFile cur_file = new ExtFile(
					1, 
					file_id, 
					branch, 
					in_file.getName(),
					new Date(), 
					1,//"",//file_num, 
					1,//doc_cnt, 
					1,//doc_amount, 
					"",//last_file_num, 
					1,
					file_type_id,
					fr_file_id
				);
			logger.error("cur_file= "+cur_file );
			Service.save_file(c, cur_file);
			logger.error("cur_file saved " );
			
			//HashMap<Long, String> record_types = Service.get_record_types(c, Long.parseLong(cur_file.getFile_type()));
			HashMap<Long, Pattern> record_types_regexp = com.is.file_reciever_srv.recievers.tieto_file.Service.get_record_types_patterns(
					c, cur_file.getFile_type());
			int i = 0;
			logger.error("record_types_regexp get " );
			while ((line = bufferedReader.readLine()) != null)
			{
				
				if (line.length() < 1) continue;
				Long record_type = null;
				try
				{
					if(file_type_id != 18)
					    record_type = com.is.file_reciever_srv.recievers.tieto_file.Service.get_record_type_fast(record_types_regexp, line);
					//record_type = Service.get_record_type(record_types, line);
					if(file_type_id == 18) 
						record_type = 2620l;
				}
				catch(Exception e)
				{
					logger.error(CheckNull.getPstr(e));
				}
				RecDump rd = new RecDump(
				Service.get_rec_dump_id(c),
				//i,//Long recid, 
				file_id,//Long file_in_id, 
				0l,//Long file_type,
				line,//String rec_dump, 
				0l,//Long file_out_id, 
				0l,//Long general_id,
				0,//String error_code, 
				0,//int state, 
				new Date(),//Date v_date, 
				"",//String err_message
				record_type
				);
				Service.save_rec_dump(c, rd, "00183"+sdf_partition.format(new java.util.Date()));
				
				i++;
			}
			fileReader.close();
			c.commit();
			logger.error("c.commit" );

            //tieto_file.Service.parse();
			
			Fr_log_service.save_log(fr_file_id, null, "read "+i+" strings from "+in_file.getName());
			logger.info(input_file+" : read "+i+" strings from "+in_file.getName());
			
			Fr_log_service.save_log(fr_file_id, null, "reading internal file "+in_file.getName()+" complete");
			logger.info(input_file+" : reading internal file "
					+in_file.getName()+" complete");
			
			
			String arch_dir = com.is.file_reciever_srv.common.Service.get_set_value(c, "ext_archive_directory");
			com.is.file_reciever_srv.simple.reciever_class.Util.move_file_with_rename_if_same_name(
					in_file, arch_dir);
			logger.info(input_file+" : file moved to "+arch_dir);
			Fr_log_service.save_log(fr_file_id, null, "file moved to"+arch_dir);
			
		} catch (Exception e)
		{
			logger.error(com.is.utils.CheckNull.getPstr(e));
			e.printStackTrace();
		}
		finally
		{
			try{if (c != null) c.close();}catch(Exception e){}
		}
	}

}
