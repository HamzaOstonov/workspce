package com.is.bpri;

import java.io.Serializable;

public class NiReq implements Serializable 
{
    static final long serialVersionUID = 103844514947365244L;

	private int bpr_id;	
    private String type_zm;
    private String reqtype;
    private String branch_id;
    private String shifr_id;
    private String kred_id;
    private String resolve_org;
    private String currency;
    private String nwp;
    private String qw;
    private String etype;
    private String is_letter;
    private String shifr;
    private String code;

    public NiReq(){
    	
    }

    public NiReq(int bpr_id, String type_zm, String reqtype, String branch_id, String shifr_id, String kred_id, String resolve_org, String currency, String nwp, String qw, String etype, String is_letter, String code){
		super();
		this.bpr_id = bpr_id;
		this.type_zm = type_zm;
		this.reqtype = reqtype;
		this.branch_id = branch_id;
		this.shifr_id = shifr_id;
		this.kred_id = kred_id;
		this.resolve_org = resolve_org;
		this.currency = currency;
		this.nwp = nwp;
		this.qw = qw;
		this.etype = etype;
		this.is_letter = is_letter;
		this.code = code;
	}
    
    public String getReq_type(){
    	return reqtype;
    }
    
    public void setReq_type(String reqtype){
    	this.reqtype = reqtype;
    }
    
    public String getResolve_org(){
    	return resolve_org;
    }
    
    public void setResolve_org(String resolve_org){
    	this.resolve_org = resolve_org;
    }
    
    public String getCurrency(){
    	return currency;
    }
    
    public void setCurrency(String currency){
    	this.currency = currency;
    }
    
    public String getNwp(){
    	return nwp;
    }
    
    public void setNwp(String nwp){
    	this.nwp = nwp;
    }
    
    public String getQw(){
    	return qw;
    }
    
    public void setQw(String qw){
    	this.qw = qw;    	
    }
    
    public String getEtype(){
    	return etype;
    }
    
    public void setEtype(String etype){
    	this.etype = etype;
    }
    
    public String getIsLetter(){
    	return is_letter;    	
    }
    
    public void setIsLetter(String is_letter){
    	this.is_letter = is_letter;
    }
    
    public int getBpr_id(){
    	return bpr_id;    	
    }
    
    public void setBpr_id(int bpr_id){
    	this.bpr_id = bpr_id;
    }

	public String getType_zm(){
		return type_zm;
	}

	public void setType_zm(String type_zm){
		this.type_zm = type_zm;
	}

	public String getBranch_id(){
		return branch_id;
	}

	public void setBranch_id(String branch_id){
		this.branch_id = branch_id;
	}

	public String getShifr_id(){
		return shifr_id;
	}

	public void setShifr_id(String shifr_id){
		this.shifr_id = shifr_id;
	}

	public String getKred_id(){
		return kred_id;
	}

	public void setKred_id(String kred_id){
		this.kred_id = kred_id;
	}

	public String getIs_letter(){
		return is_letter;
	}

	public void setIs_letter(String is_letter){
		this.is_letter = is_letter;
	}

	public String getShifr(){
		return shifr;
	}

	public void setShifr(String shifr){
		this.shifr = shifr;
	}
   
    public String getCode(){
    	return code;
    }
    
    public void setCode(String code){
    	this.code = code;
    }
}

