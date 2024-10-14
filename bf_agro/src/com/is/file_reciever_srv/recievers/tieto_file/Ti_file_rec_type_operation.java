package com.is.file_reciever_srv.recievers.tieto_file;

public class Ti_file_rec_type_operation
{
	private long id;
	private long ss_ext_record_type_id;
	private long bf_tr_operation_id;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getSs_ext_record_type_id()
	{
		return ss_ext_record_type_id;
	}
	public void setSs_ext_record_type_id(long ss_ext_record_type_id)
	{
		this.ss_ext_record_type_id = ss_ext_record_type_id;
	}
	public long getBf_tr_operation_id()
	{
		return bf_tr_operation_id;
	}
	public void setBf_tr_operation_id(long bf_tr_operation_id)
	{
		this.bf_tr_operation_id = bf_tr_operation_id;
	}
	
	public Ti_file_rec_type_operation(long id, long ss_ext_record_type_id,
			long bf_tr_operation_id)
	{
		super();
		this.id = id;
		this.ss_ext_record_type_id = ss_ext_record_type_id;
		this.bf_tr_operation_id = bf_tr_operation_id;
	}
	
	public Ti_file_rec_type_operation()
	{
		super();
	}
}
