package com.is.customer_.mobile.model.confirmation;

/**
 * Created by Dev1 on 12.12.2018.
 */
public class UpdateStateConfirmation {
    private long id;
    private String phone;
    private String state;
    private String emp_branch;
    private int emp_id;
    private String password;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
