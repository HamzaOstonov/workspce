package com.is.file_reciever_view.energo;

public class Ext_out_file_records
{
	static final long serialVersionUID = 103844514947365244L;


    private Long id;
    private Long out_file_id;
    private Long type_record_id;
    private String rec_value;
    private Long error_id;
    private String err_message;

    public Ext_out_file_records()
    {
    }

    public Ext_out_file_records( Long id, Long out_file_id, Long type_record_id, String rec_value, Long error_id, String err_message)
    {

                this.id = id;
                this.out_file_id = out_file_id;
                this.type_record_id = type_record_id;
                this.rec_value = rec_value;
                this.error_id = error_id;
                this.err_message = err_message;
        }

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getOut_file_id()
	{
		return out_file_id;
	}

	public void setOut_file_id(Long out_file_id)
	{
		this.out_file_id = out_file_id;
	}

	public Long getType_record_id()
	{
		return type_record_id;
	}

	public void setType_record_id(Long type_record_id)
	{
		this.type_record_id = type_record_id;
	}

	public String getRec_value()
	{
		return rec_value;
	}

	public void setRec_value(String rec_value)
	{
		this.rec_value = rec_value;
	}

	public Long getError_id()
	{
		return error_id;
	}

	public void setError_id(Long error_id)
	{
		this.error_id = error_id;
	}

	public String getErr_message()
	{
		return err_message;
	}

	public void setErr_message(String err_message)
	{
		this.err_message = err_message;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
    
    
}
