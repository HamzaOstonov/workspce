package com.is.file_reciever_srv.recievers.EMPC.entity;

public class EMPC_file_record
{
	private long EMPC_file_id;

	public long getEMPC_file_id()
	{
		return EMPC_file_id;
	}

	public void setEMPC_file_id(long eMPC_file_id)
	{
		EMPC_file_id = eMPC_file_id;
	}

	public EMPC_file_record(long eMPC_file_id)
	{
		super();
		EMPC_file_id = eMPC_file_id;
	}

	public EMPC_file_record()
	{
		super();
	}
}
