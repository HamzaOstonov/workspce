package com.is.bpri.bproductDesc;

import java.io.Serializable;

public class bproduct_desc implements Serializable 
{
    static final long serialVersionUID = 103844514947365244L;

		
    private int id;
    private int detail_group;
    private String detail_id;
    private String branch;

    public bproduct_desc(){}

    public bproduct_desc( int id, int detail_group, String detail_id, String branch) 
    {
			
                this.id = id;
                this.detail_group = detail_group;
                this.detail_id = detail_id;
                this.branch = branch;
    }
    
    public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getDetail_group()
	{
		return detail_group;
	}
	public void setDetail_group(int detail_group)
	{
		this.detail_group = detail_group;
	}
	
	public String getDetail_id()
	{
		return detail_id;
	}
	public void setDetail_id(String detail_id)
	{
		this.detail_id = detail_id;
	}
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

}