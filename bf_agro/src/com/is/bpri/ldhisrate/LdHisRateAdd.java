package com.is.bpri.ldhisrate;

public class LdHisRateAdd
{
	private int id;
	private String name;
	
	
	public LdHisRateAdd() 
    {

    }

    public LdHisRateAdd(int id, String name) 
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
