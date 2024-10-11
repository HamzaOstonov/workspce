package com.is.bpri.BprLdGr;


public class BprLdGr {
	
    //private String branch;
	private int id;
    private int bpr_id;
    private String oper_id;
    private String exp_id;
    private String graf_method;
    //private int ext_method;
    private String num;
    private String pay_period;
    private String day;
    private String date_from;
    private String date_to;
    //private Double dsumma;

    public BprLdGr() {
    	super();
    }

	public BprLdGr(int id, int bpr_id, String oper_id, String exp_id,
			String graf_method, String num, String pay_period, String day,
			String date_from, String date_to) {
		super();
		//this.branch = branch;
		this.id = id;
		this.bpr_id = bpr_id;
		this.oper_id = oper_id;
		this.exp_id = exp_id;
		this.graf_method = graf_method;
		//this.ext_method = ext_method;
		this.num = num;
		this.pay_period = pay_period;
		this.day = day;
		this.date_from = date_from;
		this.date_to = date_to;
		//this.dsumma = dsumma;
	}

	/*public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}

	public String getOper_id() {
		return oper_id;
	}

	public void setOper_id(String oper_id) {
		this.oper_id = oper_id;
	}

	public String getExp_id() {
		return exp_id;
	}

	public void setExp_id(String exp_id) {
		this.exp_id = exp_id;
	}
	public String getGraf_method() {
		return graf_method;
	}

	public void setGraf_method(String graf_method) {
		this.graf_method = graf_method;
	}

	/*public int getExt_method() {
		return ext_method;
	}

	public void setExt_method(int ext_method) {
		this.ext_method = ext_method;
	}*/

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPay_period() {
		return pay_period;
	}

	public void setPay_period(String pay_period) {
		this.pay_period = pay_period;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate_from() {
		return date_from;
	}

	public void setDate_from(String date_from) {
		this.date_from = date_from;
	}

	public String getDate_to() {
		return date_to;
	}

	public void setDate_to(String date_to) {
		this.date_to = date_to;
	}

	/*public Double getDsumma() {
		return dsumma;
	}

	public void setDsumma(Double dsumma) {
		this.dsumma = dsumma;
	}*/
	
	
    

}
