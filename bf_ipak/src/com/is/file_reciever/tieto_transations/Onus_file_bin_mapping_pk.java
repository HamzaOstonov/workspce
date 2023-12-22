package com.is.file_reciever.tieto_transations;

import com.is.tieto.files.Ti_file_onus_tr_accmapping;

public class Onus_file_bin_mapping_pk
{
	private String bin;
	private String code;
	
	public String getBin()
	{
		return bin;
	}
	public void setBin(String bin)
	{
		this.bin = bin;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public boolean equals(Object obj)
	{
		return (obj.getClass().equals(this.getClass())&&
				((Onus_file_bin_mapping_pk)obj).bin!=null&&
				((Onus_file_bin_mapping_pk)obj).bin.equals(this.bin)&&
				((Onus_file_bin_mapping_pk)obj).code.equals(this.code));
	}
	
	public int hashCode()
	{
		return (bin==null?"binnull":bin).hashCode()*code.hashCode();
	}
	
	public Onus_file_bin_mapping_pk(String bin, String code)
	{
		super();
		this.bin = bin;
		this.code = code;
	}
	
	public Onus_file_bin_mapping_pk(Ti_file_onus_tr_accmapping mapping)
	{
		super();
		this.bin = mapping.getBin();
		this.code = mapping.getCode();
	}
}
