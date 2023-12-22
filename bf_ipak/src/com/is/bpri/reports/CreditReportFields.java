package com.is.bpri.reports;


import java.sql.SQLData;

public class CreditReportFields {
	private String adress;
	private String inn;
	private String bname;
	private String wsumma;
	private String HOMEPHONE;
	private String MOBPHONE;
	private SQLData date;
	private String term_contract;
	private String ld_date;
	private String close_date;
	private int ld_rate;
	private String prop;
	private String HeadOfBank;
	private String chiefAccountant_name;
	public String getChiefAccountant_name() {
		return chiefAccountant_name;
	}

	public void setChiefAccountant_name(String chiefAccountant_name) {
		this.chiefAccountant_name = chiefAccountant_name;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getBank_Post_Adress() {
		return Bank_Post_Adress;
	}

	public void setBank_Post_Adress(String bank_Post_Adress) {
		Bank_Post_Adress = bank_Post_Adress;
	}

	public String getBank_Tax_Number() {
		return Bank_Tax_Number;
	}

	public void setBank_Tax_Number(String bank_Tax_Number) {
		Bank_Tax_Number = bank_Tax_Number;
	}

	public String getBank_Phone() {
		return Bank_Phone;
	}

	public void setBank_Phone(String bank_Phone) {
		Bank_Phone = bank_Phone;
	}
	private String Account;
	private String Bank_Post_Adress;
	private String Bank_Tax_Number;
	private String Bank_Phone;
	
	
	public String getHeadOfBank() {
		return HeadOfBank;
	}

	public void setHeadOfBank(String headOfBank) {
		HeadOfBank = headOfBank;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public int getLd_rate() {
		return ld_rate;
	}

	public void setLd_rate(int ld_rate) {
		this.ld_rate = ld_rate;
	}

	public String getLd_date() {
		return ld_date;
	}

	public void setLd_date(String ld_date) {
		this.ld_date = ld_date;
	}

	public String getClose_date() {
		return close_date;
	}

	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
	
	
	public String getTerm_contract() {
		return term_contract;
	}

	public void setTerm_contract(String term_contract) {
		this.term_contract = term_contract;
	}

	public CreditReportFields(){		
	};
	
	public CreditReportFields(String adress,String inn,String bname,String wsumma,String HOMEPHONE,String MOBPHONE,SQLData date,String term_contract,String ld_date,String close_date,int ld_rate,String prop,String HeadOfBank,String chiefAccountant_name,
			String Account,String Bank_Post_Adress,String Bank_Tax_Number,String Bank_Phone){
		super();
		this.adress=adress;
		this.inn=inn;
		this.bname=bname;
		this.wsumma=wsumma;
		this.HOMEPHONE=HOMEPHONE;
		this.MOBPHONE=MOBPHONE;		
		this.date=date;
		this.term_contract=term_contract;
		this.ld_date=ld_date;
		this.close_date=close_date;
		this.ld_rate=ld_rate;
		this.prop=prop;
		this.HeadOfBank=HeadOfBank;
		this.chiefAccountant_name=chiefAccountant_name;
		this.Account=Account;
		this.Bank_Post_Adress=Bank_Post_Adress;
		this.Bank_Tax_Number=Bank_Tax_Number;
		this.Bank_Phone=Bank_Phone;
	}
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getInn() {
		return inn;
	}
	public void setInn(String inn) {
		this.inn = inn;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getWsumma() {
		return wsumma;
	}
	public void setWsumma(String wsumma) {
		this.wsumma = wsumma;
	}
	public String getHOMEPHONE() {
		return HOMEPHONE;
	}
	public void setHOMEPHONE(String hOMEPHONE) {
		HOMEPHONE = hOMEPHONE;
	}
	public String getMOBPHONE() {
		return MOBPHONE;
	}
	public void setMOBPHONE(String mOBPHONE) {
		MOBPHONE = mOBPHONE;
	}
	public String getPASSPORT() {
		return PASSPORT;
	}
	public void setPASSPORT(String pASSPORT) {
		PASSPORT = pASSPORT;
	}
	public String getPSINFO() {
		return PSINFO;
	}
	public void setPSINFO(String pSINFO) {
		PSINFO = pSINFO;
	}
	public String getFIO() {
		return FIO;
	}
	public void setFIO(String fIO) {
		FIO = fIO;
	}
	public String getCardacc() {
		return cardacc;
	}
	public void setCardacc(String cardacc) {
		this.cardacc = cardacc;
	}
	public SQLData getDate() {
		return date;
	}

	public void setDate(SQLData date) {
		this.date = date;
	}
	private String PASSPORT;
	private String PSINFO;
	private String FIO;
	private String cardacc;
	
	

}
