package com.is.tieto_globuz.tieto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccInfo
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
	private String tranz_acct;
	private String card;
	private List<CardInfo> cardlist;
	

	private String status1;
	private String status2;
	private String card_type;
	private String product;
	private String product_name;
	private String agre_nom;
	private String contract;
	private BigDecimal agreement_key;
	
	public AccInfo()
	{
		super();
	}
	
	public AccInfo(Long account_no, String client, String card_acct,
						Date ctime, String ac_status,
						String cl_status, String acc_prty, String c_accnt_type, String ccy,
						Date ab_expirity, String f_names, String surname, String city,
						String street, String product_name, BigDecimal agreement_key, String contract)
	{
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
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
		this.product_name = product_name;
		this.agreement_key = agreement_key;
		this.contract = contract;
	}
	
	public AccInfo(Long account_no, String client, String card_acct,
						Date ctime, String ac_status,
						String cl_status, String acc_prty, String c_accnt_type, String ccy,
						Date ab_expirity, String f_names, String surname, String city,
						String street, String tranz_acct, String card, String status1,
						String status2, String product_name, BigDecimal agreement_key, String contract)
	{
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
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
		this.tranz_acct = tranz_acct;
		this.card = card;
		this.status1 = status1;
		this.status2 = status2;
		this.product_name = product_name;
		this.agreement_key = agreement_key;
		this.contract = contract;
	}
	
	public AccInfo(Long account_no, String client, String card_acct,
						Date ctime, String ac_status,
						String cl_status, String acc_prty, String c_accnt_type, String ccy,
						Date ab_expirity, String f_names, String surname, String city,
						String street, String tranz_acct, String card, String status1,
						String status2, String product, String product_name, BigDecimal agreement_key, String agre_nom,
						String contract)
	{
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
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
		this.tranz_acct = tranz_acct;
		this.card = card;
		this.status1 = status1;
		this.status2 = status2;
		this.card_type = product;
		this.product = product;
		this.product_name = product_name;
		this.agreement_key = agreement_key;
		this.agre_nom = agre_nom;
		this.contract = contract;
	}
	
	public String getContract()
	{
		return contract;
	}
	
	public void setContract(String contract)
	{
		this.contract = contract;
	}
	
	public BigDecimal getAgreement_key()
	{
		return agreement_key;
	}
	
	public void setAgreement_key(BigDecimal agreement_key)
	{
		this.agreement_key = agreement_key;
	}
	
	public String getProduct_name()
	{
		return product_name;
	}
	
	public void setProduct_name(String product_name)
	{
		this.product_name = product_name;
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
	
	public String getProduct()
	{
		return product;
	}
	
	public String getAgre_nom()
	{
		return agre_nom;
	}
	
	public void setAgre_nom(String agre_nom)
	{
		this.agre_nom = agre_nom;
	}
	
	public void setProduct(String product)
	{
		this.product = product;
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
	
	public String getTranz_acct()
	{
		return tranz_acct;
	}
	
	public void setTranz_acct(String tranz_acct)
	{
		this.tranz_acct = tranz_acct;
	}
	
	public String getCard()
	{
		return card;
	}
	
	public void setCard(String card)
	{
		this.card = card;
	}
	
	public String getStatus1()
	{
		return status1;
	}
	
	public void setStatus1(String status1)
	{
		this.status1 = status1;
	}
	
	public String getStatus2()
	{
		return status2;
	}
	
	public void setStatus2(String status2)
	{
		this.status2 = status2;
	}
	
	public void setCard_type(String Card_type)
	{
		this.card_type = Card_type;
	}
	
	public String getCard_type()
	{
		return card_type;
	}
	
	public List<CardInfo> getCardlist()
	{
		return this.cardlist;
	}

	public void setCardlist(List<CardInfo> cardlist)
	{
		this.cardlist = cardlist;
	}

	@Override
	public String toString() {
		return "AccInfo [account_no=" + account_no + ", client=" + client
				+ ", card_acct=" + card_acct + ", bank_c=" + bank_c
				+ ", groupc=" + groupc + ", ctime=" + ctime + ", ac_status="
				+ ac_status + ", cl_status=" + cl_status + ", acc_prty="
				+ acc_prty + ", c_accnt_type=" + c_accnt_type + ", ccy=" + ccy
				+ ", ab_expirity=" + ab_expirity + ", f_names=" + f_names
				+ ", surname=" + surname + ", city=" + city + ", street="
				+ street + ", tranz_acct=" + tranz_acct + ", card=" + card
				+ ", cardlist=" + cardlist + ", status1=" + status1
				+ ", status2=" + status2 + ", card_type=" + card_type
				+ ", product=" + product + ", product_name=" + product_name
				+ ", agre_nom=" + agre_nom + ", contract=" + contract
				+ ", agreement_key=" + agreement_key + "]";
	}
	
	
	
}
