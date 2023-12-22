package com.is.tieto_globuz.terminals;

public class Terminal
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
	private String acc;
	
	/**
	 * 
	 */
	public Terminal()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param terminal_id
	 * @param acceptor_id
	 * @param term_type
	 * @param point_code
	 * @param install_date
	 * @param status
	 * @param serial_nr
	 * @param inv_nr
	 * @param notes
	 */
	/**
	 * @param terminal_id
	 * @param acceptor_id
	 * @param term_type
	 * @param point_code
	 * @param install_date
	 * @param status
	 * @param serial_nr
	 * @param inv_nr
	 * @param notes
	 * @param action
	 */
	public Terminal(String terminal_id, String acceptor_id, String term_type, String point_code, String install_date, String status, String serial_nr, String inv_nr, String notes, String action, String acc)
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
		this.acc = acc;
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

	@Override
	public String toString() {
		return "Terminal [Terminal_id=" + Terminal_id + ", Acceptor_id=" + Acceptor_id + ", Term_type=" + Term_type
				+ ", Point_code=" + Point_code + ", Install_date=" + Install_date + ", Status=" + Status
				+ ", Serial_nr=" + Serial_nr + ", Inv_nr=" + Inv_nr + ", Notes=" + Notes + ", action=" + action + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Acceptor_id == null) ? 0 : Acceptor_id.hashCode());
		result = prime * result + ((Install_date == null) ? 0 : Install_date.hashCode());
		result = prime * result + ((Inv_nr == null) ? 0 : Inv_nr.hashCode());
		result = prime * result + ((Notes == null) ? 0 : Notes.hashCode());
		result = prime * result + ((Point_code == null) ? 0 : Point_code.hashCode());
		result = prime * result + ((Serial_nr == null) ? 0 : Serial_nr.hashCode());
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		result = prime * result + ((Term_type == null) ? 0 : Term_type.hashCode());
		result = prime * result + ((Terminal_id == null) ? 0 : Terminal_id.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminal other = (Terminal) obj;
		if (Acceptor_id == null) {
			if (other.Acceptor_id != null)
				return false;
		} else if (!Acceptor_id.equals(other.Acceptor_id))
			return false;
		if (Install_date == null) {
			if (other.Install_date != null)
				return false;
		} else if (!Install_date.equals(other.Install_date))
			return false;
		if (Inv_nr == null) {
			if (other.Inv_nr != null)
				return false;
		} else if (!Inv_nr.equals(other.Inv_nr))
			return false;
		if (Notes == null) {
			if (other.Notes != null)
				return false;
		} else if (!Notes.equals(other.Notes))
			return false;
		if (Point_code == null) {
			if (other.Point_code != null)
				return false;
		} else if (!Point_code.equals(other.Point_code))
			return false;
		if (Serial_nr == null) {
			if (other.Serial_nr != null)
				return false;
		} else if (!Serial_nr.equals(other.Serial_nr))
			return false;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		if (Term_type == null) {
			if (other.Term_type != null)
				return false;
		} else if (!Term_type.equals(other.Term_type))
			return false;
		if (Terminal_id == null) {
			if (other.Terminal_id != null)
				return false;
		} else if (!Terminal_id.equals(other.Terminal_id))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		return true;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}
	
	
}
