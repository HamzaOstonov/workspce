package com.is.tieto.files;

public class Ti_file_onus_tr_accmapping
{
	private String bin;
	private Long id;
	private String terminal;
	private String code;
	private String account_10107;
	private String account_23510;
	private String name;
	private Long operation_id;
	private String operation_branch;
	private String acc_23508_510;
	private Long cup_operation_id;
	private Long operation_corporativ;
	
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getTerminal()
	{
		return terminal;
	}
	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public String getAccount_10107()
	{
		return account_10107;
	}
	public void setAccount_10107(String account_10107)
	{
		this.account_10107 = account_10107;
	}
	public String getAccount_23510()
	{
		return account_23510;
	}
	public void setAccount_23510(String account_23510)
	{
		this.account_23510 = account_23510;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Long getOperation_id()
	{
		return operation_id;
	}
	public void setOperation_id(Long operation_id)
	{
		this.operation_id = operation_id;
	}
	public String getOperation_branch()
	{
		return operation_branch;
	}
	public void setOperation_branch(String operation_branch)
	{
		this.operation_branch = operation_branch;
	}
	public String getBin()
	{
		return bin;
	}
	public void setBin(String bin)
	{
		this.bin = bin;
	}
	public String getAcc_23508_510()
	{
		return acc_23508_510;
	}
	public void setAcc_23508_510(String acc_23508_510)
	{
		this.acc_23508_510 = acc_23508_510;
	}
	public Long getCup_operation_id()
	{
		return cup_operation_id;
	}
	public void setCup_operation_id(Long cup_operation_id)
	{
		this.cup_operation_id = cup_operation_id;
	}
	public Long getOperation_corporativ()
	{
		return operation_corporativ;
	}
	public void setOperation_corporativ(Long operation_corporativ)
	{
		this.operation_corporativ = operation_corporativ;
	}
	
	public Ti_file_onus_tr_accmapping(String bin, Long id, String terminal,
			String code, String account_10107, String account_23510,
			String name, Long operation_id, String operation_branch,
			String acc_23508_510, Long cup_operation_id,
			Long operation_corporativ)
	{
		super();
		this.bin = bin;
		this.id = id;
		this.terminal = terminal;
		this.code = code;
		this.account_10107 = account_10107;
		this.account_23510 = account_23510;
		this.name = name;
		this.operation_id = operation_id;
		this.operation_branch = operation_branch;
		this.acc_23508_510 = acc_23508_510;
		this.cup_operation_id = cup_operation_id;
		this.operation_corporativ = operation_corporativ;
	}
	
	public Ti_file_onus_tr_accmapping()
	{
		super();
	}
}
