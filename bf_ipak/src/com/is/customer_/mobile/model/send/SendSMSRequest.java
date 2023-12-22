package com.is.customer_.mobile.model.send;

/**
 * Created by Dev1 on 19.12.2018.
 */
public class SendSMSRequest {
    private String phone;
    private String emp_branch;
    private int emp_id;
    private int state;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmp_branch() {
        return emp_branch;
    }

    public void setEmp_branch(String emp_branch) {
        this.emp_branch = emp_branch;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
