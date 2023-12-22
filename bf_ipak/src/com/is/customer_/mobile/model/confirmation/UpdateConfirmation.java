package com.is.customer_.mobile.model.confirmation;

import com.is.customer_.mobile.model.MobileBankClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev1 on 16.11.2018.
 */
public class UpdateConfirmation {
    private Long id;

    private String phone;

    private String email;

    private List<MobileBankClient> bank_clients;

    private String emp_branch;

    private int emp_id;

    private String password;

    private String crmId;

    public UpdateConfirmation(){
        bank_clients = new ArrayList<MobileBankClient>();
    }

    public List<MobileBankClient> getBank_clients() {
        return bank_clients;
    }

    public void setBank_clients(List<MobileBankClient> bank_clients) {
        this.bank_clients = bank_clients;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_branch() {
        return emp_branch;
    }

    public void setEmp_branch(String emp_branch) {
        this.emp_branch = emp_branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }
}
