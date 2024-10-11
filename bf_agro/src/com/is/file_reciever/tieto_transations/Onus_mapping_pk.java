package com.is.file_reciever.tieto_transations;

import com.is.tieto.files.Ti_file_onus_tr_accmapping;

public class Onus_mapping_pk
{
	private String terminal;
	private String code;
	
	public boolean equals(Object obj)
	{
		return (obj.getClass().equals(this.getClass())&&
				((Onus_mapping_pk)obj).terminal!=null&&
				((Onus_mapping_pk)obj).terminal.equals(this.terminal)&&
				((Onus_mapping_pk)obj).code.equals(this.code));
	} 
	
	public int hashCode()
	{
		return (terminal==null?"termnull":terminal).hashCode()*code.hashCode();
	}

	public Onus_mapping_pk(String terminal, String code)
	{
		super();
		this.terminal = terminal;
		this.code = code;
	}
	
	public Onus_mapping_pk(Ti_file_onus_tr_accmapping mapping)
	{
		super();
		this.terminal = mapping.getTerminal();
		this.code = mapping.getCode();
	}
}
