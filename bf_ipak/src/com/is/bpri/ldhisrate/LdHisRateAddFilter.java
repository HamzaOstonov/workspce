package com.is.bpri.ldhisrate;

public class LdHisRateAddFilter
{
	private int id;
	private String name;
	
	
	public LdHisRateAddFilter() 
    {

    }

    public LdHisRateAddFilter(int id, String name) 
    {
			
        super();        
    	this.id = id;
        this.name = name;       
      
    }
   public int getId()
    {
    	return id;
    }
    
    public void setId(int id)
    {
    	this.id = id;
    }
    
    public String getExp_id()
    {
    	return name;
    }
    
    public void setExp_id(String name)
    {
    	this.name = name;
    }
}
