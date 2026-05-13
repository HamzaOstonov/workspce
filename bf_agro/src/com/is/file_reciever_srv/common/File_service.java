package com.is.file_reciever_srv.common;

import java.io.File;

import com.is.ConnectionPool;


public class File_service
{
	public static void create_files()
	{
		File fl = new File(ConnectionPool.getValue("ext_out_directory"));
	    fl.mkdirs();
	    fl = new File(ConnectionPool.getValue("ext_tmp_out_directory"));
	    fl.mkdirs();
	    fl = new File(ConnectionPool.getValue("ext_wrong_string_control_summ_dir"));
	    fl.mkdirs();
	    fl = new File(ConnectionPool.getValue("ext_archive_directory"));
	    fl.mkdirs();
	    fl = new File(ConnectionPool.getValue("ext_double_directory"));
	    fl.mkdirs();
	    fl = new File(ConnectionPool.getValue("ext_wrong_branch_dir"));
	    fl.mkdirs();
	    fl = new File(ConnectionPool.getValue("GNI_CM_OUT"));
	    fl.mkdirs();
	}
}
