package com.is.customer_.mobile.model.confirmation;

import com.is.customer_.mobile.model.MobileBankClient;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dev1 on 14.11.2018.
 */
@ToString
public class RegisterConfirmation {

    public String lang = "ru";
    private String phone;
    private String email;
    private String passport_serial;
    private String passport_number;
    private List<MobileBankClient> binds;
    private String emp_branch;
    private int emp_id;
    private String crmId;
    private String password;

    public RegisterConfirmation(){
        this.binds = new ArrayList<MobileBankClient>();
    }

    public RegisterConfirmation(String phone, String email, int employeeId){
        this.binds = new ArrayList<MobileBankClient>();
        this.phone = phone;
        this.email = email;
        this.emp_id = employeeId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
*/
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

    public String getPassport_serial() {
        return passport_serial;
    }

    public void setPassport_serial(String passport_serial) {
        this.passport_serial = passport_serial;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public List<MobileBankClient> getBinds() {
        return binds;
    }

    public void setBinds(List<MobileBankClient> binds) {
        this.binds = binds;
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

    public String getCrmId() {
        return crmId;
    }

    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
