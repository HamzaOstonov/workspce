package com.is.bpri.BprLdGr;

public class BprGrTypeAdd
{
	private int oper_id;
	private int exp_id;
	private String name;
	
	
	public BprGrTypeAdd() 
    {

    }

    public BprGrTypeAdd(int oper_id, int exp_id, String name) 
    {
			
        super();        
    	this.oper_id = oper_id;
    	this.exp_id = exp_id;
        this.name = name;       
      
    }
   public int getOPerId()
    {
    	return oper_id;
    }
    
    public void setOPerId(int oper_id)
    {
    	this.oper_id = oper_id;
    }
    
    public int getExpId()
    {
    	return exp_id;
    }
    
    public void setExpId(int exp_id)
    {
    	this.exp_id = exp_id;
    }
    
    public String getGr_type()
    {
    	return name;
    }
    
    public void setGr_type(String name)
    {
    	this.name = name;
    }
}
