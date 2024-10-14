package com.is.bpri.bpr_ld_forms;
import java.io.Serializable;

public class BprLdForms implements Serializable{
    
	static final long serialVersionUID = 103844514947365244L;
	
    private int bpr_id;
    private String head_of_bank;
    private String is_ld;
    private String name;
    private String scoring;

    public BprLdForms(){

    }

    public BprLdForms(int bpr_id, String head_of_bank,String is_ld,String name,String scoring){
            this.bpr_id	= bpr_id;
            this.head_of_bank = head_of_bank;
            this.is_ld = is_ld;
            this.name = name;
            this.scoring = scoring;
    }

    public int getBpr_id(){
    	return bpr_id;
    }
    
    public void setBpr_id(int bpr_id){
    	
    	this.bpr_id=bpr_id;
    }
    
    public String getHead_of_bank(){
    	return head_of_bank;
    }
    
    public void setHead_of_bank(String head_of_bank){
    	this.head_of_bank=head_of_bank;
    }

	public String getIs_ld() {
		return is_ld;
	}

	public void setIs_ld(String is_ld) {
		this.is_ld = is_ld;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScoring() {
		return scoring;
	}

	public void setScoring(String scoring) {
		this.scoring = scoring;
	}
    
}
