package com.is.bpri.bprLimits;
import java.io.Serializable;

public class bprLimits implements Serializable{
	
    static final long serialVersionUID = 103844514947365244L;

	private int id;
	private int bproduct_id;
    private String date_limit;
    private String summ_limit;
    private String id_state;

    public bprLimits(){

    }

    public bprLimits(int id, int bproduct_id, String date_limit, String summ_limit,String id_state) {
    	this.id = id;
        this.bproduct_id = bproduct_id;
        this.date_limit = date_limit;
        this.summ_limit = summ_limit;
        this.id_state = id_state;
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

	public String getDate_limit(){
		return date_limit;
	}

	public void setDate_limit(String date_limit){
		this.date_limit = date_limit;
	}

	public String getSumm_limit(){
		return summ_limit;
	}

	public void setSumm_limit(String summ_limit){
			this.summ_limit = summ_limit;
	}

	public String getId_state() {
		return id_state;
	}

	public void setId_state(String id_state) {
		this.id_state = id_state;
	}
	
}