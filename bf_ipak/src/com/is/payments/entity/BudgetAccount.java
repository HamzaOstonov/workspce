package com.is.payments.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by root on 30.05.2017.
 * 14:53
 */

public class BudgetAccount {
    private String treasure_id;
    private String branch;
    private String account;
    private String treasureAccount;
    private String taxNumberRegistration;
    private String accountName;
    private String budgetAccountName;

    public BudgetAccount() {
    }

    public BudgetAccount(String treasure_id,
                         String branch,
                         String account,
                         String treasureAccount,
                         String taxNumberRegistration,
                         String accountName,
                         String budgetAccountName) {
        this.treasure_id = treasure_id;
        this.branch = branch;
        this.account = account;
        this.treasureAccount = treasureAccount;
        this.taxNumberRegistration = taxNumberRegistration;
        this.accountName = accountName;
        this.budgetAccountName = budgetAccountName;
    }

    public String getNameAndTaxNumber() {
        return String.format("%s%s", StringUtils.rightPad(accountName, 50, " "), taxNumberRegistration);
    }

    public String getTreasure_id() {
        return treasure_id;
    }

    public void setTreasure_id(String treasure_id) {
        this.treasure_id = treasure_id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTreasureAccount() {
        return treasureAccount;
    }

    public void setTreasureAccount(String treasureAccount) {
        this.treasureAccount = treasureAccount;
    }

    public String getTaxNumber() {
        return taxNumberRegistration;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumberRegistration = taxNumber;
    }

    public String getName() {
        return accountName;
    }

    public void setName(String name) {
        this.accountName = name;
    }

    public String getTaxNumberRegistration() {
        return taxNumberRegistration;
    }

    public void setTaxNumberRegistration(String taxNumberRegistration) {
        this.taxNumberRegistration = taxNumberRegistration;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBudgetAccountName() {
        return budgetAccountName;
    }

    public void setBudgetAccountName(String budgetAccountName) {
        this.budgetAccountName = budgetAccountName;
    }
}
