package com.is.file_reciever_srv.main;

import java.util.HashMap;
import java.util.Vector;

import com.is.file_reciever_srv.simple.stat.Observe_directory;


public class Big_reader
{
	public static void read(
			Observe_directory[] observe_directories, 
			HashMap<Integer, Vector<Integer>> types_in_directories,
			HashMap<Integer, String> file_types_patterns,
			HashMap<Integer, String> type_reciever_classes)
	{
		int directories_number = observe_directories.length;
		for (int i = 0; i < directories_number; i++)
		{
			Directory_reader.read_dir(observe_directories[i].getDir(), 
					types_in_directories.get(observe_directories[i].getId()),
					file_types_patterns, type_reciever_classes);
		}
	}
}
