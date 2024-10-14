package com.is.bpri.bpr_ld_account;
import java.io.Serializable;

public class BprLdAccount implements Serializable 
{
    static final long serialVersionUID = 103844514947365244L;

    //private int id;	
    private int bpr_id;
    private String acc_type_id;
    private String account;
    private int is_open;
    private String acc_order;
    private String acc_group_id;
    
    public BprLdAccount(){

    }

    public BprLdAccount(int bpr_id, String acc_type_id, String account,int is_open,String acc_order,String acc_group_id){ 
        this.bpr_id	= bpr_id;
        this.acc_type_id = acc_type_id;
        this.account = account;
        this.is_open = is_open;
        this.acc_order = acc_order;
        this.acc_group_id = acc_group_id;
    }
    
    public int getBpr_id() {
		return bpr_id;
	}

	public void setBpr_id(int bpr_id) {
		this.bpr_id = bpr_id;
	}

	public String getAcc_type_id() {
		return acc_type_id;
	}

	public void setAcc_type_id(String acc_type_id) {
		this.acc_type_id = acc_type_id;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getIs_open() {
		return is_open;
	}

	public void setIs_open(int is_open) {
		this.is_open = is_open;
	}

	public String getAcc_order() {
		return acc_order;
	}

	public void setAcc_order(String acc_order) {
		this.acc_order = acc_order;
	}

	public String getAcc_group_id() {
		return acc_group_id;
	}

	public void setAcc_group_id(String acc_group_id) {
		this.acc_group_id = acc_group_id;
	}	
	
}