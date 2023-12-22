package com.is.payments.interactor;

import com.is.ISLogger;
import com.is.payments.PaymentUtils;
import com.is.payments.entity.*;
import com.is.payments.model.PaymentRequest;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

/**
 * Created by root on 27.05.2017.
 * 14:33
 */
public class PaymentContext {
    private Logger logger = ISLogger.getLogger();

    private static PaymentContext paymentContext;

    public static PaymentContext getInstance(){
        if (paymentContext == null)
            paymentContext = new PaymentContext();
        return paymentContext;
    }

    public Payment getInputAsPayment(PaymentRequest input){
        Payment payment = input.isBudget() ? new BudgetPayment() : new Payment();

        payment.setCashSymbol(input.getCashSymbol());
        payment.setCashSymbolDescription(input.getCashSymbolDescription());
        payment.setPurpose(input.getPurposePayment());
        payment.setPurposeCode(input.getPurposeCode());
        payment.setSum(input.getSum());
        payment.setBudgetAccount(input.getBudgetAccount());
        payment.setSender(
                new PaymentActor(
                        input.getBranchSend(),
                        input.getAccountSend(),
                        input.getAccountSendName()
                )
        );
        payment.setReceiver(
                new PaymentActor(
                        input.getBranchReceive(),
                        input.getAccountReceive(),
                        input.getCustomer(),
                        input.getAccountReceiveName()
                )
        );
        payment.setTaxNumberRegistration(input.getTaxNumberRegistration());
        payment.setTreasuryAccount(input.getTreasuryAccount());
        payment.setOperationCode(Long.valueOf(input.getPaymentType()));
        return payment;

    }

    private int parseIntegerToString(String type_) {
        int type = 0;
        try {
            type = Integer.parseInt(type_);
        }
        catch (NumberFormatException e){
            logger.error(CheckNull.getPstr(e));
        }
        return type;
    }
}
