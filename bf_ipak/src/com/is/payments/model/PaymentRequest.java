package com.is.payments.model;

import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.Session;

@Data
public class PaymentRequest {
    private String personName;
    private String branchSend;
    private String branchReceive;
    private String accountSend;
    private String accountReceive;
    private String accountSendName;
    private String accountReceiveName;
    private String purposePayment;
    private String purposeCode;
    private long sum;
    private String cashSymbol;
    private String cashSymbolDescription;
    private String paymentType;
    private Customer customer;
    private String treasuryAccount;
    private String taxNumberRegistration;
    private boolean isBudget;
    private boolean isIntercard;
    private String budgetAccount;
    private String budgetAccountName;

    public void clearCurrent(){
        this.setBudget(false);
        this.setIntercard(false);
        this.setBudgetAccount(null);
        this.setBudgetAccountName(null);
        this.setTaxNumberRegistration(null);
        this.setBranchReceive(null);
        this.setAccountReceive(null);
        this.setTreasuryAccount(null);
        this.setAccountReceiveName(null);
    }
}
