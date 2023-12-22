package com.is.tieto_globuz.terminals;

public class TerminalFilter
{
	private String Terminal_id;
	private String Acceptor_id;
	private String Term_type;
	private String Point_code;
	private String Install_date;
	private String Status;
	private String Serial_nr;
	private String Inv_nr;
	private String Notes;
	private String action;
	
	/**
	 * 
	 */
	public TerminalFilter()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TerminalFilter(String terminal_id, String acceptor_id, String term_type, String point_code, String install_date, String status, String serial_nr, String inv_nr, String notes, String action)
	{
		super();
		this.Terminal_id = terminal_id;
		this.Acceptor_id = acceptor_id;
		this.Term_type = term_type;
		this.Point_code = point_code;
		this.Install_date = install_date;
		this.Status = status;
		this.Serial_nr = serial_nr;
		this.Inv_nr = inv_nr;
		this.Notes = notes;
		this.action = action;
	}
	
	public String getTerminal_id()
	{
		return this.Terminal_id;
	}
	
	public void setTerminal_id(String terminal_id)
	{
		this.Terminal_id = terminal_id;
	}
	
	public String getAcceptor_id()
	{
		return this.Acceptor_id;
	}
	
	public void setAcceptor_id(String acceptor_id)
	{
		this.Acceptor_id = acceptor_id;
	}
	
	public String getTerm_type()
	{
		return this.Term_type;
	}
	
	public void setTerm_type(String term_type)
	{
		this.Term_type = term_type;
	}
	
	public String getPoint_code()
	{
		return this.Point_code;
	}
	
	public void setPoint_code(String point_code)
	{
		this.Point_code = point_code;
	}
	
	public String getInstall_date()
	{
		return this.Install_date;
	}
	
	public void setInstall_date(String install_date)
	{
		this.Install_date = install_date;
	}
	
	public String getStatus()
	{
		return this.Status;
	}
	
	public void setStatus(String status)
	{
		this.Status = status;
	}
	
	public String getSerial_nr()
	{
		return this.Serial_nr;
	}
	
	public void setSerial_nr(String serial_nr)
	{
		this.Serial_nr = serial_nr;
	}
	
	public String getInv_nr()
	{
		return this.Inv_nr;
	}
	
	public void setInv_nr(String inv_nr)
	{
		this.Inv_nr = inv_nr;
	}
	
	public String getNotes()
	{
		return this.Notes;
	}
	
	public void setNotes(String notes)
	{
		this.Notes = notes;
	}

	public String getAction()
	{
		return this.action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
	
	
}

