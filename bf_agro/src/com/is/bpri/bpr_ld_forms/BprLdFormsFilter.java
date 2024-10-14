package com.is.bpri.bpr_ld_forms;


public class BprLdFormsFilter 
{
   

    private int id;
    private int bpr_id;
    private String head_of_bank;

    public BprLdFormsFilter() 
    {

    }

    public BprLdFormsFilter( int id, int bpr_id, String head_of_bank) 
    {
			
                this.id = id;
                this.bpr_id	= bpr_id;
                this.head_of_bank = head_of_bank;
    }
    
    public int getId()
    {
    	return id;
    }
    
    public void setId(int id)
    {
    	
    	this.id=id;
    }
    
    public int getBpr_id()
    {
    	return bpr_id;
    }
    
    public void setBpr_id(int bpr_id)
    {
    	
    	this.bpr_id=bpr_id;
    }
    
    public String getHead_of_bank()
    {
    	return head_of_bank;
    }
    
    public void setHead_of_bank(String head_of_bank)
    {
    	
    	this.head_of_bank=head_of_bank;
    }
    

}
