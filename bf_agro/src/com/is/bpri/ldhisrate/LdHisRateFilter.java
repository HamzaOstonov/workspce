package com.is.bpri.ldhisrate;


public class LdHisRateFilter{
   	
	private int id;
	private int Id;	
	private int bpr_id;	
    private int bprid;
    private int exp_id;
    private int rate_type;
    private int rate_method;
    private int pay_method;
    private int expId;
    private int rate_id;
    private Double rate;

    public LdHisRateFilter(){

    }

    public LdHisRateFilter(int id, int Id, int bpr_id, int exp_id, int rate_type, int rate_method, int pay_method, int bprid, int expId,int rate_id, Double rate){
		super();   
        this.id=id;
        this.id=Id;
        this.bpr_id = bpr_id;
        this.bprid = bprid;
    	this.exp_id = exp_id;
        this.rate_type = rate_type;
        this.rate_method = rate_method;
        this.pay_method = pay_method;
        this.expId = expId;
        this.rate_id = rate_id;
        this.rate = rate;
    }
    
    public int getExp_id(){
    	return exp_id;
    }
    
    public void setExp_id(int exp_id){
    	this.exp_id = exp_id;
    }
    
    public int getRate_type(){
    	return rate_type;
    }
    
    public void setRate_type(int rate_type){
    	this.rate_type = rate_type;
    }
    
    public int getRate_method(){
    	return rate_method;
    }
    
    public void setRate_method(int rate_method){
    	this.rate_method = rate_method;
    }
    
    public int getPay_method(){
    	return pay_method;
    }
    
    public void setPay_method(int pay_method){
    	this.pay_method = pay_method;
    }
    
  
    public int getRate_id(){
    	return rate_id;
    }
    
    public void setRate_id(int rate_id){
    	this.rate_id = rate_id;
    }
    
    public Double getRate(){
    	return rate;
    }

    public void setRate(Double rate){
    	this.rate = rate;
    }
    
    public int getBpr_id(){
    	return bpr_id;
    }
    
    public void setBpr_id(int bpr_id){
    	this.bpr_id = bpr_id;
    }
    
    public int getBprId(){
    	return bprid;
    }
    
    public void setBprId(int bprid){
    	this.bprid = bprid;
    }
    public int getExpId(){
    	return expId;
    }
    
    public void setExpId(int expId){
    	this.expId = expId;
    }
    
    public int getId(){
    	return id;
    }
    
    public void setId(int id){
    	this.id = id;
    }
    
    public int getIdRate(){
    	return Id;
    }
    
    public void setIdRate(int Id){
    	this.Id = Id;
    }
}


