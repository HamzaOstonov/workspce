package com.is.bpri;

public class Bproduct {
	
    private long id;
    private String branch;
    private String customer;
    private int prodid;
    private String vdate;
    private String currency;
    private String amount;
    private int emp_id;
    private int state;
    private String full_name;
    private String bpr_name;
    private String state_name;
    private String card_number;
    private int ni_req_id;

    public Bproduct() {

    }

//	public Bproduct(int id, String branch, String customer, int prodid,String vdate, String currency,
//			String amount, int emp_id, int state,String full_name,String bpr_name,String state_name,String card_number,int ni_req_id
//			) {
//		super();
//		this.id = id;
//		this.branch = branch;
//		this.customer = customer;
//		this.prodid = prodid;
//		this.vdate = vdate;
//		this.currency = currency;
//		this.amount = amount;
//		this.emp_id = emp_id;
//		this.state = state;
//		this.full_name = full_name;
//		this.bpr_name = bpr_name;
//		this.state_name = state_name;
//		this.card_number = card_number;
//		this.ni_req_id = ni_req_id;
//	}
    
	public Bproduct(long id, String branch, String customer, int prodid,String vdate, String currency,
			String amount, int emp_id, int state,String full_name,String bpr_name,String state_name,int ni_req_id
			) {
		super();
		this.id = id;
		this.branch = branch;
		this.customer = customer;
		this.prodid = prodid;
		this.vdate = vdate;
		this.currency = currency;
		this.amount = amount;
		this.emp_id = emp_id;
		this.state = state;
		this.full_name = full_name;
		this.bpr_name = bpr_name;
		this.state_name = state_name;
		this.ni_req_id = ni_req_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getVdate() {
		return vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getBpr_name() {
		return bpr_name;
	}

	public void setBpr_name(String bpr_name) {
		this.bpr_name = bpr_name;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public int getNi_req_id() {
		return ni_req_id;
	}

	public void setNi_req_id(int ni_req_id) {
		this.ni_req_id = ni_req_id;
	}
	@Override
	public String toString() {
		return "Bproduct [" + (id != 0 ? "id=" + id + ", " : "")
				+ (branch != null ? "branch=" + branch + ", " : "")
				+ (customer != null ? "customer=" + customer + ", " : "")
				+ (prodid != 0 ? "prodid=" + prodid +", " : "")
				+(vdate!=null? "vdate="+vdate+", ":"")
				+(currency!=null? "currency="+currency+", ":"")
				+(amount!=null? "amount="+amount+", ":"")
				+(emp_id!=0? "emp_id="+emp_id+", ":"")
				+(state!=0? "state="+state+", ":"")
				+(full_name!=null? "full_name="+full_name+", ":"")
				+(bpr_name!=null? "bpr_name="+bpr_name+", ":"")
				+(state_name!=null? "state_name="+state_name+", ":"")
				+(card_number!=null? "card_number="+card_number+", ":"")
				+(ni_req_id!=0? "ni_req_id="+ni_req_id:"")
				+ "]";
	}
	
}
