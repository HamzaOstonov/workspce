package com.is.file_reciever_view.simple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.is.utils.RefData;

public class File_types
{
	private HashMap<Long, File_type> file_types;
	
	public File_types()
	{
		super();
		this.file_types = new HashMap<Long, File_type>();
	}

	public File_type get_file_type(Long id)
	{
		return this.file_types.get(id);
	}
	
	public List<RefData> get_ref_data()
	{
		List<RefData> list = new LinkedList();
		
		for (File_type cur_ft : this.file_types.values())
		{
//			System.out.println("rt"+this.file_types.size());
			 list.add(
			          new RefData(Long.toString(cur_ft.getId()), 
			          cur_ft.getComment()+" ("+cur_ft.getFilename_pattern()+")"));
		}
	
		return list;
	}
	
	public void load(Connection c)
	{
		this.file_types = File_type_service.getFile_types(c);
	}
}
