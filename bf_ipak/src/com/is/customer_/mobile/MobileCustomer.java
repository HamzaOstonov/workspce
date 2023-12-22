package com.is.customer_.mobile;

import com.is.customer_.core.model.Customer;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dev1 on 14.11.2018.
 */
@ToString
public class MobileCustomer {
    private Long id;
    //private String username;
    private String phone;
    private String emailAddress;
    //private String password;
    private List<Customer> binds;
    private String state;
    private Date registrationDate;

    public MobileCustomer(){
        binds = new ArrayList<Customer>();
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /*public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    public List<Customer> getBinds() {
        return binds;
    }

    public void setBinds(List<Customer> binds) {
        this.binds = binds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
