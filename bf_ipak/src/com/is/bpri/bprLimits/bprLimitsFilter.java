package com.is.bpri.bprLimits;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class bprLimitsFilter implements Serializable{

	private int id;
	private int bproduct_id;
    private Date date_limit;
    private Long summ_limit;

    public bprLimitsFilter(){

    }

    public bprLimitsFilter(int id, int bproduct_id, Date date_limit, Long summ_limit){
    	this.id = id;
        this.bproduct_id = bproduct_id;
        this.date_limit = date_limit;
        this.summ_limit = summ_limit;
    }
    
   public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getBproduct_id(){
		return bproduct_id;
	}

	public void setBproduct_id(int bproduct_id){
		this.bproduct_id = bproduct_id;
	}

	public Date getDate_limit(){
		return date_limit;
	}

	public void setDate_limit(Date date_limit){
		this.date_limit = date_limit;
	}

	public Long getSumm_limit(){
		return summ_limit;
	}

	public void setSumm_limit(Long summ_limit){
		this.summ_limit = summ_limit;
	}

}