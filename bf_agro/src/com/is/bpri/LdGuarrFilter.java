package com.is.bpri;


public class LdGuarrFilter{
    		
 
	 	private String currency;
	    private String guar_id;
	    private String klass_o;
	    private int bpr_id;
	    private int id;

    public LdGuarrFilter() 
    {

    }

    public LdGuarrFilter( String currency, String guar_id, String klass_o, int bpr_id, int id) 
    {
		
        this.currency = currency;
        this.guar_id = guar_id;
        this.klass_o = klass_o;
        this.bpr_id=bpr_id;
        this.id=id;
    }
    
    public String getCurrency()
    {
    	return currency;
    }
    
    public void setCurrency(String currency)
    {
    	this.currency = currency;
    }
    
    public String getGuar_id()
    {
    	return guar_id;
    }
    
    public void setGuar_id(String guar_id)
    {
    	this.guar_id = guar_id;
    }
    
    public String getKlass_o()
    {
    	return klass_o; 
    }
    
    public void setKlass_o(String klass_o)
    {
    	this.klass_o = klass_o;
    }
    
    public int getBpr_id()
    {
    	return bpr_id; 
    }
    
    public void setBpr_id(int bpr_id)
    {
    	this.bpr_id = bpr_id;
    }
    
    public int getId()
    {
    	return id; 
    }
    
    public void setId(int id)
    {
    	this.id = id;
    }
    

}

