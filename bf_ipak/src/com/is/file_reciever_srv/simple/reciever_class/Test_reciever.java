package com.is.file_reciever_srv.simple.reciever_class;

import java.io.File;

import universal_reader.Universal_reader;

public class Test_reciever extends Reciever_class
{
	@Override
	public void Recieve_file(String input_file, long fr_file_id)
	{
		System.out.println("Recieves file: " + input_file);
	}
}
