package com.is.payments.entity;

/**
 * Created by root on 31.05.2017.
 * 13:42
 */
public class BudgetPayment extends Payment{
    private Payment payment;

    public BudgetPayment(Payment payment) {this.payment = payment;}

    public BudgetPayment(){}

    @Override
    public String formatPurpose() {
        return String.format("~%s~%s~%s",
                budgetAccount,
                taxNumberRegistration,
                super.formatPurpose());
    }
}
