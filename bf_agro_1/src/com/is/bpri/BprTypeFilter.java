package com.is.bpri;


public class BprTypeFilter{
	 
	private int bpr_type;	
	private Integer bpr_id;  
	private String bpr_name;
	private String deal_id;
	private String currency;
	private Integer provision;
	private String mfo;
	private String region_id;
	private Integer state;
	private String state_name;
	
	public BprTypeFilter() {

	}

	public BprTypeFilter(Integer bpr_type, Integer bpr_id, String bpr_name, String deal_id, String currency, Integer provision,String mfo,String region_id,Integer state,String state_name) {
		this.bpr_type = bpr_type;
	    this.bpr_id = bpr_id;
	    this.bpr_name = bpr_name;
	    this.currency = currency;
	    this.deal_id = deal_id;
	    this.provision = provision;
	    this.mfo = mfo;
	    this.region_id = region_id;
		this.state = state;
		this.state_name = state_name;
	}
	    
	public Integer getBpr_id(){
		return bpr_id;
	}
	    
	public void setBpr_id(Integer bpr_id){
		this.bpr_id = bpr_id;
	}
	
	public Integer getBpr_type(){
		return bpr_type;
	}
	
	public void setBpr_type(Integer bpr_type){
		this.bpr_type = bpr_type;
	}
	    
	public String getName(){
		return bpr_name;
	}
	    
	public void setName(String bpr_name){
		this.bpr_name = bpr_name;
	}
	    
	public String getCurrency(){
		return currency;
	}
	    
	public void setCurrency(String currency){
		this.currency = currency;
	}
	    
	public String getDeal_id(){
		return deal_id;
	}
	    
	public void setDeal_id(String deal_id){
		this.deal_id = deal_id;
	}
	    
	public Integer getProvision(){
		return provision;
	}
	    
	public void setProvision(Integer provision){
		this.provision = provision;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public String getRegion_id() {
		return region_id;
	}

	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	
}
