package com.is.customer_.mobile.model.findByBind;

/**
 * Created by Dev1 on 15.11.2018.
 */
public class MobileCustomerByBindRequest {
    private String id;
    private String branch;

    public MobileCustomerByBindRequest(){

    }

    public MobileCustomerByBindRequest(String branch, String id){
        this.branch = branch;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
