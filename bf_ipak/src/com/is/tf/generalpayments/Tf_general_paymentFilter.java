package com.is.tf.generalpayments;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tf_general_paymentFilter implements Serializable {
    static final long serialVersionUID = 2111111111222222L;

    private Long id;
	private String idn;
	private String object_type;
	private String object_id;
	private String sub_object_id;
	private String branch;
	private Long general_id;
	private Long summa;
	private Long summa_idn;
	private String client_id;
	private String account;
	private String inn;
	private Long state;



    public Tf_general_paymentFilter() {
		super();
    }

    public Tf_general_paymentFilter(Long id, String idn, String object_type, String object_id, String sub_object_id, String branch, Long general_id, Long summa, Long summa_idn, String client_id, String account, String inn, Long state) {
		super();
		this.id = id;
		this.idn = idn;
		this.object_type = object_type;
		this.object_id = object_id;
		this.sub_object_id = sub_object_id;
		this.branch = branch;
		this.general_id = general_id;
		this.summa = summa;
		this.summa_idn = summa_idn;
		this.client_id = client_id;
		this.account = account;
		this.inn = inn;
		this.state = state;


    }
    

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) { 
		this.id = id;
	} 

	public String getIdn() { 
		return idn;
	} 

	public void setIdn(String idn) { 
		this.idn = idn;
	} 

	public String getObject_type() { 
		return object_type;
	} 

	public void setObject_type(String object_type) { 
		this.object_type = object_type;
	} 

	public String getObject_id() { 
		return object_id;
	} 

	public void setObject_id(String object_id) { 
		this.object_id = object_id;
	} 

	public String getSub_object_id() { 
		return sub_object_id;
	} 

	public void setSub_object_id(String sub_object_id) { 
		this.sub_object_id = sub_object_id;
	} 

	public String getBranch() { 
		return branch;
	} 

	public void setBranch(String branch) { 
		this.branch = branch;
	} 

	public Long getGeneral_id() { 
		return general_id;
	} 

	public void setGeneral_id(Long general_id) { 
		this.general_id = general_id;
	} 

	public Long getSumma() { 
		return summa;
	} 

	public void setSumma(Long summa) { 
		this.summa = summa;
	} 

	public Long getSumma_idn() { 
		return summa_idn;
	} 

	public void setSumma_idn(Long summa_idn) { 
		this.summa_idn = summa_idn;
	} 

	public String getClient_id() { 
		return client_id;
	} 

	public void setClient_id(String client_id) { 
		this.client_id = client_id;
	} 

	public String getAccount() { 
		return account;
	} 

	public void setAccount(String account) { 
		this.account = account;
	} 

	public String getInn() { 
		return inn;
	} 

	public void setInn(String inn) { 
		this.inn = inn;
	} 

	public Long getState() { 
		return state;
	} 

	public void setState(Long state) { 
		this.state = state;
	} 

}