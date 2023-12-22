package com.is.payments.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by root on 24.05.2017.
 * 16:18
 */
@Data
public class Payment{
    public final static long DEAL_GROUP = 211;
    private static final String CENTRAL_BANK_MFO = "00014";

    protected PaymentActor sender;
    protected PaymentActor receiver;
    protected String cashSymbol;
    protected String cashSymbolDescription;
    protected String purpose;
    protected String purposeCode;
    protected long sum;
    protected long operationCode;
    protected String taxNumberRegistration;
    protected String treasuryAccount;
    protected String budgetAccount;

    public long calculateSum(){
        return sum * 100;
    }

    public String formatPurpose(){
        if (receiver.getBranch().equals(CENTRAL_BANK_MFO) && treasuryAccount != null) {
            String customerCredentials = String.format("%s %s%s",
                    this.receiver.getCustomer().getFullName(),
                    this.receiver.getCustomer().getP_passport_serial(),
                    this.receiver.getCustomer().getP_passport_number());
            return String.format("~%s~%s %s",
                    //this.purposeCode,
                    this.treasuryAccount,
                    this.purpose,
                    customerCredentials);
        }
        return String.format("%s %s %s %s",
                purpose,
                receiver.getCustomer().getFullName(),
                receiver.getCustomer().getP_passport_serial(),
                receiver.getCustomer().getP_passport_number());
    }

    public  void validate(){
        if (receiver.getCustomer() == null)
            throw new PaymentInvalidException("Нет связки с клиентом");
        if (StringUtils.isEmpty(sender.getBranch()))
            throw new PaymentInvalidException("Введите МФО Отправителя");
        if (StringUtils.isEmpty(receiver.getBranch()))
            throw new PaymentInvalidException("Введите МФО Получателя");
        if (sum == 0)
            throw new PaymentInvalidException("Введите сумму");
        if (StringUtils.isEmpty(sender.getAccount()))
            throw new PaymentInvalidException("Введите счет отправителя");
        if (StringUtils.isEmpty(sender.getAccount()))
            throw new PaymentInvalidException("Введите счет получателя");
        if (StringUtils.isEmpty(purpose))
            throw new PaymentInvalidException("Введите назначение платежа");

    }

    protected class PaymentInvalidException extends RuntimeException {
        public PaymentInvalidException(String message) {
            super(message);
        }
    }

    protected String getCashCode(){
        return String.format("%s%s",cashSymbol,cashSymbolDescription);
    }
}
