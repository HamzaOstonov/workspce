package com.is.tietovisa.model;


import java.util.Date;

import com.is.tietovisa.customer.Customer;

public class AccInfo {

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
    private String status1;
    private String status2;
    private String stop_cause;
    private String card_type;
    private String product;
    private String product_name;
    private String agre_nom;
    private int agreement_key;
    private String cond_set;
    private String status;
    private String b_br_id;
    private String client_b;
    private String office_id;
    private String main_row;
    private String card_name;


    private String contractNumber;
    private String contractName;
    private String cbsNumber;
    private String currency;
    private String commentText;
    private String productCode1;

    private String dateOpen;

    private String clientNumber;
    private String socialNumber;
    private String clientType;
    private String clientCategory;
    private String acc_bal;
    private String id_order;
    private String branch;
    private String id;
    
    //private String agree_bincod;
    //private String agree_product;
    //private String agree_branch;
    
    
    private boolean way_exist;
    private String rbsNumberWay;
    private String rbsNumberIbs;
    private boolean sign_error_record;
    
    public AccInfo() {
    	super();
    }

	public AccInfo(Long account_no, String client, String card_acct,
			Date ctime, String ac_status,
			String cl_status, String acc_prty, String c_accnt_type, String ccy,
			Date ab_expirity, String f_names, String surname, String city,
			String street, String product_name, int agreement_key, String contract,
			String stop_cause) {
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
		//this.bank_c = bank_c;
		//this.groupc = groupc;
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
		//this.contract = contract;
		this.stop_cause = stop_cause;
	}

	public AccInfo(Long account_no, String client, String card_acct,
			Date ctime, String ac_status,
			String cl_status, String acc_prty, String c_accnt_type, String ccy,
			Date ab_expirity, String f_names, String surname, String city,
			String street, String tranz_acct, String card, String status1,
			String status2, String product_name, int agreement_key, String contract,
			String stop_cause) {
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
		//this.bank_c = bank_c;
		//this.groupc = groupc;
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
		//this.contract = contract;
		this.stop_cause = stop_cause;
	}
	
	public AccInfo(Long account_no, String client, String card_acct,
			Date ctime, String ac_status,
			String cl_status, String acc_prty, String c_accnt_type, String ccy,
			Date ab_expirity, String f_names, String surname, String city,
			String street, String tranz_acct, String card, String status1,
			String status2, String product, String product_name, int agreement_key, String agre_nom,
			String contract, String stop_cause) {
		super();
		this.account_no = account_no;
		this.client = client;
		this.card_acct = card_acct;
		//this.bank_c = bank_c;
		//this.groupc = groupc;
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
		//this.contract = contract;
		this.stop_cause = stop_cause;
	}

	public void copyTietoFields (AccInfo old, AccInfo newAcc) {
		//Customer newCust=new Customer();


		newAcc.setCard_acct(old.getCard_acct());
		newAcc.setTranz_acct(old.getTranz_acct());
		newAcc.setStatus(old.getStatus());
		newAcc.setAcc_prty(old.getAcc_prty());
		newAcc.setC_accnt_type(old.getC_accnt_type());
		newAcc.setCcy(old.getCcy());
		newAcc.setCond_set(old.getCond_set());
		newAcc.setClient_b(old.getClient_b());
		newAcc.setClient(old.getClient());
		newAcc.setF_names(old.getF_names());
		newAcc.setSurname(old.getSurname());
		newAcc.setB_br_id(old.getB_br_id());
		newAcc.setOffice_id(old.getOffice_id());
		newAcc.setMain_row(old.getMain_row());
		newAcc.setAccount_no(old.getAccount_no());
		newAcc.setBank_c(old.getBank_c());
		newAcc.setGroupc(old.getGroupc());
		
	}
	
	public AccInfo clone (AccInfo old) {
		AccInfo newAcc=new AccInfo();

		newAcc.setAccount_no(old.getAccount_no());
		newAcc.setClient(old.getClient());
		newAcc.setCard_acct(old.getCard_acct());
		newAcc.setBank_c(old.getBank_c());
		newAcc.setGroupc(old.getGroupc());
		newAcc.setCtime(old.getCtime());
		newAcc.setAc_status(old.getAc_status());
		newAcc.setCl_status(old.getCl_status());
		newAcc.setAcc_prty(old.getAcc_prty());
		newAcc.setC_accnt_type(old.getC_accnt_type());
		newAcc.setCcy(old.getCcy());
		newAcc.setAb_expirity(old.getAb_expirity());
		newAcc.setF_names(old.getF_names());
		newAcc.setSurname(old.getSurname());
		newAcc.setCity(old.getCity());
		newAcc.setStreet(old.getStreet());
		newAcc.setTranz_acct(old.getTranz_acct());
		newAcc.setCard(old.getCard());
		newAcc.setStatus1(old.getStatus1());
		newAcc.setStatus2(old.getStatus2());
		newAcc.setStop_cause(old.getStop_cause());
		newAcc.setCard_type(old.getCard_type());
		newAcc.setProduct(old.getProduct());
		newAcc.setProduct_name(old.getProduct_name());
		newAcc.setAgre_nom(old.getAgre_nom());
		newAcc.setAgreement_key(old.getAgreement_key());
		newAcc.setCond_set(old.getCond_set());
		newAcc.setStatus(old.getStatus());
		newAcc.setB_br_id(old.getB_br_id());
		newAcc.setClient_b(old.getClient_b());
		newAcc.setOffice_id(old.getOffice_id());
		newAcc.setMain_row(old.getMain_row());
		newAcc.setContractNumber(old.getContractNumber());
		newAcc.setContractName(old.getContractName());
		newAcc.setCbsNumber(old.getCbsNumber());
		newAcc.setCurrency(old.getCurrency());
		newAcc.setCommentText(old.getCommentText());
		newAcc.setProductCode1(old.getProductCode1());
		
		//newAcc.setAgree_bincod(old.getAgree_bincod());
		//newAcc.setAgree_product(old.getAgree_product());
		//newAcc.setAgree_branch(old.getAgree_branch());
		
		return newAcc;
	}
	
	public String getCond_set() {
		return cond_set;
	}

	public String getStatus() {
		return status;
	}

	public String getB_br_id() {
		return b_br_id;
	}

	public String getClient_b() {
		return client_b;
	}

	public void setCond_set(String cond_set) {
		this.cond_set = cond_set;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setB_br_id(String b_br_id) {
		this.b_br_id = b_br_id;
	}

	public void setClient_b(String client_b) {
		this.client_b = client_b;
	}

	public String getContractNumber()
	{
		return contractNumber;
	}

	public String getStop_cause()
	{
		return stop_cause;
	}

	public void setStop_cause(String stop_cause)
	{
		this.stop_cause = stop_cause;
	}


	public int getAgreement_key() {
		return agreement_key;
	}

	public void setAgreement_key(int agreement_key) {
		this.agreement_key = agreement_key;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Long getAccount_no() {
		return account_no;
	}

	public void setAccount_no(Long account_no) {
		this.account_no = account_no;
	}

	public String getClient() {
		return client;
	}

	public String getProduct() {
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

	public void setProduct(String product) {
		this.product = product;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCard_acct() {
		return card_acct;
	}

	public void setCard_acct(String card_acct) {
		this.card_acct = card_acct;
	}

	public String getBank_c() {
		return bank_c;
	}

	public void setBank_c(String bank_c) {
		this.bank_c = bank_c;
	}

	public String getGroupc() {
		return groupc;
	}

	public void setGroupc(String groupc) {
		this.groupc = groupc;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getAc_status() {
		return ac_status;
	}

	public void setAc_status(String ac_status) {
		this.ac_status = ac_status;
	}

	public String getCl_status() {
		return cl_status;
	}

	public void setCl_status(String cl_status) {
		this.cl_status = cl_status;
	}

	public String getAcc_prty() {
		return acc_prty;
	}

	public void setAcc_prty(String acc_prty) {
		this.acc_prty = acc_prty;
	}

	public String getC_accnt_type() {
		return c_accnt_type;
	}

	public void setC_accnt_type(String c_accnt_type) {
		this.c_accnt_type = c_accnt_type;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Date getAb_expirity() {
		return ab_expirity;
	}

	public void setAb_expirity(Date ab_expirity) {
		this.ab_expirity = ab_expirity;
	}

	public String getF_names() {
		return f_names;
	}

	public void setF_names(String f_names) {
		this.f_names = f_names;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTranz_acct() {
		return tranz_acct;
	}

	public void setTranz_acct(String tranz_acct) {
		this.tranz_acct = tranz_acct;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}
 
	public void setCard_type(String Card_type) {
		this.card_type = Card_type;
	}
	
	public String getCard_type() {
		return card_type;
	}

	public String getCbsNumber() {
		return cbsNumber;
	}

	public void setCbsNumber(String cbsNumber) {
		this.cbsNumber = cbsNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	

	public String getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}


	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getSocialNumber() {
		return socialNumber;
	}

	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getClientCategory() {
		return clientCategory;
	}

	public void setClientCategory(String clientCategory) {
		this.clientCategory = clientCategory;
	}

	public String getAcc_bal() {
		return acc_bal;
	}

	public void setAcc_bal(String acc_bal) {
		this.acc_bal = acc_bal;
	}

	public String getId_order() {
		return id_order;
	}

	public void setId_order(String id_order) {
		this.id_order = id_order;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setProductCode1(String productCode1) {
		this.productCode1 = productCode1;
	}

	public String getProductCode1() {
		return productCode1;
	}

	public void setWay_exist(boolean way_exist) {
		this.way_exist = way_exist;
	}

	public boolean isWay_exist() {
		return way_exist;
	}

	public void setRbsNumberIbs(String rbsNumber) {
		this.rbsNumberIbs = rbsNumber;
	}

	public String getRbsNumberIbs() {
		return rbsNumberIbs;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setRbsNumberWay(String rbsNumberWay) {
		this.rbsNumberWay = rbsNumberWay;
	}

	public String getRbsNumberWay() {
		return rbsNumberWay;
	}

	public void setSign_error_record(boolean sign_error_record) {
		this.sign_error_record = sign_error_record;
	}

	public boolean isSign_error_record() {
		return sign_error_record;
	}

	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}

	public String getOffice_id() {
		return office_id;
	}

	public void setMain_row(String main_row) {
		this.main_row = main_row;
	}

	public String getMain_row() {
		return main_row;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_name() {
		return card_name;
	}

	/*public void setAgree_bincod(String agree_bincod) {
		this.agree_bincod = agree_bincod;
	}

	public String getAgree_bincod() {
		return agree_bincod;
	}

	public void setAgree_product(String agree_product) {
		this.agree_product = agree_product;
	}

	public String getAgree_product() {
		return agree_product;
	}

	public void setAgree_branch(String agree_branch) {
		this.agree_branch = agree_branch;
	}

	public String getAgree_branch() {
		return agree_branch;
	}*/
    
}
