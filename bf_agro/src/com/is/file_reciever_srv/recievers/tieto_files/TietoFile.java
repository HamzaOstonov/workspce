package com.is.file_reciever_srv.recievers.tieto_files;

public class TietoFile
{
	private TietoRecord[] transaction_records;
	private TietoHeaderRecord[] header_records;
	private TietoFooterRecord[] footer_records;
	private Integer state;
	
	public TietoRecord[] getTransaction_records()
	{
		return transaction_records;
	}
	public void setTransaction_records(TietoRecord[] transaction_records)
	{
		this.transaction_records = transaction_records;
	}
	public TietoHeaderRecord[] getHeader_records()
	{
		return header_records;
	}
	public void setHeader_records(TietoHeaderRecord[] header_records)
	{
		this.header_records = header_records;
	}
	public TietoFooterRecord[] getFooter_records()
	{
		return footer_records;
	}
	public void setFooter_records(TietoFooterRecord[] footer_records)
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
	
	public TietoFile(TietoRecord[] transaction_records,
			TietoHeaderRecord[] header_records,
			TietoFooterRecord[] footer_records, Integer state)
	{
		super();
		this.transaction_records = transaction_records;
		this.header_records = header_records;
		this.footer_records = footer_records;
		this.state = state;
	}
	
	public TietoFile()
	{
		super();
	}
}
