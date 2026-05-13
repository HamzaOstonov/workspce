package com.is.bpri.BprLdGr;

public class BprGrType
{
	private int id;
	private int bpr_id;
    private int oper_id;
    private int exp_id;
    private int graf_method;    
    private int num;
    private int pay_period;
    private int day;
    private int date_from;
    private int date_to;
	private String name;
	
	
	public BprGrType() 
    {

    }

	public BprGrType(int id, int bpr_id, int oper_id, int exp_id, int graf_method, int num, int pay_period, int day, int date_from, int date_to, String name)
	{
		super();
		this.id = id;
		this.bpr_id = bpr_id;
		this.oper_id = oper_id;
		this.exp_id = exp_id;
		this.graf_method = graf_method;
		this.num = num;
		this.pay_period = pay_period;
		this.day = day;
		this.date_from = date_from;
		this.date_to = date_to;
		this.name = name;
	}
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

	public int getOper_id() {
		return oper_id;
	}

	public void setOper_id(int oper_id) {
		this.oper_id = oper_id;
	}

	public int getExp_id() {
		return exp_id;
	}

	public void setExp_id(int exp_id) {
		this.exp_id = exp_id;
	}
	public int getGraf_method() {
		return graf_method;
	}

	public void setGraf_method(int graf_method) {
		this.graf_method = graf_method;
	}

	/*public int getExt_method() {
		return ext_method;
	}

	public void setExt_method(int ext_method) {
		this.ext_method = ext_method;
	}*/

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPay_period() {
		return pay_period;
	}

	public void setPay_period(int pay_period) {
		this.pay_period = pay_period;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDate_from() {
		return date_from;
	}

	public void setDate_from(int date_from) {
		this.date_from = date_from;
	}

	public int getDate_to() {
		return date_to;
	}

	public void setDate_to(int date_to) {
		this.date_to = date_to;
	}
    
    public String getGr_type()
    {
    	return name;
    }
    
    public void setGr_type(String name)
    {
    	this.name = name;
    }
}
