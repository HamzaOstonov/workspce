package com.is.bpri.bpr_ld_account;


public class BprLdAccountFilter  
{	
	//private int id;	
    private int bpr_id;
    private Long acc_type_id;
    private String account;

    public BprLdAccountFilter() 
    {

    }

    public BprLdAccountFilter( int bpr_id, Long acc_type_id, String account) //int id,
    {	
    	//this.id = id;
        this.bpr_id	= bpr_id;
        this.acc_type_id = acc_type_id;
        this.account = account;
    }
    
    /*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/
    
    public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}

	 public Long getAcc_type_id() {
			return acc_type_id;
		}

		public void setAcc_type_id(Long acc_type_id) {
			this.acc_type_id = acc_type_id;
		}
		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}	
	

}