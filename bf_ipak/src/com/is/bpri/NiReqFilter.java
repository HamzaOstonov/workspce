package com.is.bpri;

//import java.io.Serializable;

public class NiReqFilter 
{
    //static final long serialVersionUID = 103844514947365244L;

	private int bpr_id;	
    private String type_zm;
    private Long reqtype;
    private String branch_id;
    private String shifr_id;
    private String kred_id;
    private String resolve_org;
    private String currency;
    private String nwp;
    private String qw;
    private String etype;
    private String is_letter;
    private String code;
    private int bprId;

    public NiReqFilter() 
    {

    }

    public NiReqFilter(int bpr_id, String type_zm, Long reqtype, String branch_id, String shifr_id, String kred_id, String resolve_org, String currency, String nwp, String qw, String etype, String is_letter, String code, int bprId) 
    {
		
		super();        
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
        this.bpr_id =bpr_id;
        this.code = code;
        this.bprId = bprId;
    }
    
    public String getTypeZm()
    {
    	return type_zm;    	
    }
    
    public void setTypeZm(String type_zm)
    {
    	this.type_zm = type_zm;
    }
    
    public Long getReqType()
    {
    	return reqtype;
    }
    
    public void setReqType(Long reqtype)
    {
    	this.reqtype = reqtype;
    }
    
    public String getBranchId()
    {
    	return branch_id;
    }
    
    public void setBranchId(String branch_id)
    {
    	this.branch_id = branch_id;
    }
    
    public String getShifrId()
    {
    	return shifr_id;
    }
    
    public void setShifrId(String shifr_id)
    {
    	this.shifr_id = shifr_id;
    }
    
    public String getKredId()
    {
    	return kred_id;
    }
    
    public void setKredId(String kred_id)
    {
    	this.kred_id = kred_id;
    }
    
    public String getResolveOrg()
    {
    	return resolve_org;
    }
    
    public void setResolveOrg(String resolve_org)
    {
    	this.resolve_org = resolve_org;
    }
    
    public String getCurrency()
    {
    	return currency;
    }
    
    public void setCurrency(String currency)
    {
    	this.currency = currency;
    }
    
    public String getNwp()
    {
    	return nwp;
    }
    
    public void setNwp(String nwp)
    {
    	this.nwp = nwp;
    }
    
    public String getQw()
    {
    	return qw;
    }
    
    public void setQw(String qw)
    {
    	this.qw = qw;    	
    }
    
    public String getEtype()
    {
    	return etype;
    }
    
    public void setEtype(String etype)
    {
    	this.etype = etype;
    }
    
    public String getIsLetter()
    {
    	return is_letter;    	
    }
    
    public void setIsLetter(String is_letter)
    {
    	this.is_letter = is_letter;
    }
    
    public int getBpr_id()
    {
    	return bpr_id;    	
    }
    
    public void setBpr_id(int bpr_id)
    {
    	this.bpr_id = bpr_id;
    }
    
    public String getCode()
    {
    	return code;
    }
    
    public void setCode(String code)
    {
    	this.code = code;
    }
    
    public int getBprId()
    {
    	return bprId;    	
    }
    
    public void setBprId(int bprId)
    {
    	this.bprId = bprId;
    }

}

