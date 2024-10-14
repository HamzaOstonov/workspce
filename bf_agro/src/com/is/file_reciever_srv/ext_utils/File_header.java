package com.is.file_reciever_srv.ext_utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.is.file_reciever_srv.exception.Reciever_exception;
import com.is.utils.CheckNull;


public class File_header
{
	private String line_type;
	private String date;
	private String branch;
	private String prev_raice_num;
	private long number_of_lines;
	private long file_control_summ;
	
	public File_header(String header_string) throws Reciever_exception
	{
		try
		{
			Pattern p_header = Pattern.compile(
					"^(.*?)"+Character.toString((char) 29)+
					"(.*?)"+Character.toString((char) 29)+
					"(.*?)"+Character.toString((char) 29)+
					"(.*?)"+Character.toString((char) 29)+
					"(.*?)"+Character.toString((char) 29)+
					"(.*?)"+Character.toString((char) 29)
					);
			Matcher p_header_matcher = p_header.matcher(header_string); 
			p_header_matcher.find(); 
			
			this.line_type = p_header_matcher.group(1);
			this.date = p_header_matcher.group(2);
			this.branch = p_header_matcher.group(3);
			this.prev_raice_num = p_header_matcher.group(4);
			this.number_of_lines = Long.parseLong(p_header_matcher.group(5));
			this.file_control_summ = Long.parseLong(p_header_matcher.group(6));
		}
		catch(Exception e)
		{
			throw new com.is.file_reciever_srv.exception.Reciever_exception(
					"Could not parse file header (\""+header_string+"\"): "+CheckNull.getPstr(e));
		}
	}
	
	@Override
	public String toString()
	{
		return "File_header [line_type=" + line_type + ", date=" + date
				+ ", branch=" + branch + ", prev_raice_num=" + prev_raice_num
				+ ", number_of_lines=" + number_of_lines
				+ ", file_control_summ=" + file_control_summ + "]";
	}

	public File_header()
	{
		super();
	}

	public String getLine_type()
	{
		return line_type;
	}

	public String getDate()
	{
		return date;
	}

	public String getBranch()
	{
		return branch;
	}

	public String getPrev_raice_num()
	{
		return prev_raice_num;
	}

	public long getNumber_of_lines()
	{
		return number_of_lines;
	}

	public long getFile_control_summ()
	{
		return file_control_summ;
	}
}
