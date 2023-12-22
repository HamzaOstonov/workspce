package com.is.payments.interactor;

import com.is.customer_.core.model.SessionAttributes;
import com.is.payments.entity.Payment;
import com.is.payments.entity.PaymentActor;
import com.is.payments.model.PaymentRequest;
import com.is.payments.model.PaymentResponse;
import com.is.payments.service.PaymentTransactionImpl;
import com.is.payments.service.PaymentTransactionInterface;
import general.General;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by root on 24.05.2017.
 * 16:24
 */
public class PaymentTransactionInteractor {
    private PaymentTransactionInterface paymentTransaction;

    private PaymentTransactionInteractor(SessionAttributes sessionAttributes){
        this.paymentTransaction = new PaymentTransactionImpl(sessionAttributes);
    }

    public static PaymentTransactionInteractor getInstance(SessionAttributes sessionAttributes){
        return new PaymentTransactionInteractor(sessionAttributes);
    }

    public List<General> commitPayment(PaymentRequest input) throws Exception {
        return paymentTransaction.commitTransaction(mapToEntity(input));
    }

    private Payment mapToEntity(PaymentRequest input) {
        return PaymentContext.getInstance().getInputAsPayment(input);
    }

    private PaymentResponse mapEntityToResponse(){
        return null;
    }
}
