package com.is.file_reciever_srv.recievers.EMPC.EXPT;

public class EXPT_file
{
	private EXPT_record[] transaction_records;
	private EXPT_header_record[] header_records;
	private EXPT_footer_tecord[] footer_records;
	private Integer state;
	
	public EXPT_record[] getTransaction_records()
	{
		return transaction_records;
	}
	public void setTransaction_records(EXPT_record[] transaction_records)
	{
		this.transaction_records = transaction_records;
	}
	public EXPT_header_record[] getHeader_records()
	{
		return header_records;
	}
	public void setHeader_records(EXPT_header_record[] header_records)
	{
		this.header_records = header_records;
	}
	public EXPT_footer_tecord[] getFooter_records()
	{
		return footer_records;
	}
	public void setFooter_records(EXPT_footer_tecord[] footer_records)
	{
		this.footer_records = footer_records;
	}
	public Integer getState()
	{
		return state;
	}
	public void setState(Integer state)
	{
		this.state = state;
	}
	
	public EXPT_file(EXPT_record[] transaction_records,
			EXPT_header_record[] header_records,
			EXPT_footer_tecord[] footer_records, Integer state)
	{
		super();
		this.transaction_records = transaction_records;
		this.header_records = header_records;
		this.footer_records = footer_records;
		this.state = state;
	}
	
	public EXPT_file()
	{
		super();
	}
}
