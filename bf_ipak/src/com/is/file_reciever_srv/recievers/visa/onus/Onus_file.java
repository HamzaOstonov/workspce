package com.is.file_reciever_srv.recievers.visa.onus;

public class Onus_file
{
	private Onus_record[] transaction_records;
	private Onus_header_record[] header_records;
	private Onus_footer_record[] footer_records;
	private Integer state;
	
	public Onus_record[] getTransaction_records()
	{
		return transaction_records;
	}
	public void setTransaction_records(Onus_record[] transaction_records)
	{
		this.transaction_records = transaction_records;
	}
	public Onus_header_record[] getHeader_records()
	{
		return header_records;
	}
	public void setHeader_records(Onus_header_record[] header_records)
	{
		this.header_records = header_records;
	}
	public Onus_footer_record[] getFooter_records()
	{
		return footer_records;
	}
	public void setFooter_records(Onus_footer_record[] footer_records)
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
	
	public Onus_file(Onus_record[] transaction_records,
			Onus_header_record[] header_records,
			Onus_footer_record[] footer_records, Integer state)
	{
		super();
		this.transaction_records = transaction_records;
		this.header_records = header_records;
		this.footer_records = footer_records;
		this.state = state;
	}
	
	public Onus_file()
	{
		super();
	}
}
