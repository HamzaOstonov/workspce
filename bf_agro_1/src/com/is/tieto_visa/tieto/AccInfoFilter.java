package com.is.tieto_visa.tieto;

import java.util.Date;

public class AccInfoFilter
{
	private Long account_no;
	private String client;
	private String card_acct;
	private String bank_c;
	private String groupc;
	private Date ctime;
	private String ac_status;
	private String cl_status;
	private String acc_prty;
	private String c_accnt_type;
	private String ccy;
	private Date ab_expirity;
	private String f_names;
	private String surname;
	private String city;
	private String street;
	
	public AccInfoFilter()
	{
		super();
	}
	
	public AccInfoFilter(Long account_no, String client, String card_acct,
						String bank_c, String groupc, Date ctime, String ac_status,
						String cl_status, String acc_prty, String c_accnt_type, String ccy,
						Date ab_expirity, String f_names, String surname, String city,
						String street)
	{
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
		this.bank_c = bank_c;
		this.groupc = groupc;
		this.ctime = ctime;
		this.ac_status = ac_status;
		this.cl_status = cl_status;
		this.acc_prty = acc_prty;
		this.c_accnt_type = c_accnt_type;
		this.ccy = ccy;
		this.ab_expirity = ab_expirity;
		this.f_names = f_names;
		this.surname = surname;
		this.city = city;
		this.street = street;
	}
	
	public Long getAccount_no()
	{
		return account_no;
	}
	
	public void setAccount_no(Long account_no)
	{
		this.account_no = account_no;
	}
	
	public String getClient()
	{
		return client;
	}
	
	public void setClient(String client)
	{
		this.client = client;
	}
	
	public String getCard_acct()
	{
		return card_acct;
	}
	
	public void setCard_acct(String card_acct)
	{
		this.card_acct = card_acct;
	}
	
	public String getBank_c()
	{
		return bank_c;
	}
	
	public void setBank_c(String bank_c)
	{
		this.bank_c = bank_c;
	}
	
	public String getGroupc()
	{
		return groupc;
	}
	
	public void setGroupc(String groupc)
	{
		this.groupc = groupc;
	}
	
	public Date getCtime()
	{
		return ctime;
	}
	
	public void setCtime(Date ctime)
	{
		this.ctime = ctime;
	}
	
	public String getAc_status()
	{
		return ac_status;
	}
	
	public void setAc_status(String ac_status)
	{
		this.ac_status = ac_status;
	}
	
	public String getCl_status()
	{
		return cl_status;
	}
	
	public void setCl_status(String cl_status)
	{
		this.cl_status = cl_status;
	}
	
	public String getAcc_prty()
	{
		return acc_prty;
	}
	
	public void setAcc_prty(String acc_prty)
	{
		this.acc_prty = acc_prty;
	}
	
	public String getC_accnt_type()
	{
		return c_accnt_type;
	}
	
	public void setC_accnt_type(String c_accnt_type)
	{
		this.c_accnt_type = c_accnt_type;
	}
	
	public String getCcy()
	{
		return ccy;
	}
	
	public void setCcy(String ccy)
	{
		this.ccy = ccy;
	}
	
	public Date getAb_expirity()
	{
		return ab_expirity;
	}
	
	public void setAb_expirity(Date ab_expirity)
	{
		this.ab_expirity = ab_expirity;
	}
	
	public String getF_names()
	{
		return f_names;
	}
	
	public void setF_names(String f_names)
	{
		this.f_names = f_names;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setStreet(String street)
	{
		this.street = street;
	}
}
