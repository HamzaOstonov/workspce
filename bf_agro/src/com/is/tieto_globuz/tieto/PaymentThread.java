package com.is.tieto_globuz.tieto;

import java.util.List;

import org.zkoss.zul.Label;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.is.ISLogger;
import com.is.utils.Res;

public class PaymentThread implements Runnable {
    private PacketPayment packetPayment;
    private Label label;
    private int uid;
    private String alias, un, pwd;

    public PaymentThread() {
        super();
    }

    public PaymentThread(PacketPayment packetPayment, Label label, int uid, String alias,
            String un, String pwd) {
        super();
        this.packetPayment = packetPayment;
        this.label = label;
        this.uid = uid;
        this.alias = alias;
        this.un = un;
        this.pwd = pwd;
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println("packetPayment: "+objectMapper.writeValueAsString(packetPayment));
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (packetPayment.getState() == 0) {
            Res res = new Res();
            res = TclientService.fillCardsInBranch(packetPayment, alias, un, pwd);
            if (res.getCode() != 0) {
                packetPayment.setState(0);
                TclientService.updateExcelState(packetPayment);
                return;
            } else {
                boolean isEqual = true;
                packetPayment.setState(19);
                TclientService.updateExcelState(packetPayment);
                List<AccrualEmployee> paymentList = TclientService.getPaymentList(packetPayment, isEqual, alias);
                ISLogger.getLogger().error("packetPayment " + packetPayment.getName() + " amount " + paymentList.size() + " started");
                int successfulPaymentsCounter = TclientService.sendFillToHumo(packetPayment,
                        paymentList, uid, alias, un, pwd);
                if (successfulPaymentsCounter != paymentList.size()) {
                    ISLogger.getLogger().error("successful not equal to list size");
                    if(successfulPaymentsCounter == 0) {
                        ISLogger.getLogger().error("successful 0");
                        packetPayment.setState(5);
                        TclientService.updateExcelState(packetPayment);
                    }else {
                        ISLogger.getLogger().error("successful not 0");
                        packetPayment.setState(4);
                        TclientService.updateExcelState(packetPayment);
                    }
                } else {
                    ISLogger.getLogger().error("successful all");
                    packetPayment.setState(6);
                    TclientService.updateExcelState(packetPayment);
                }
            }
        } else if (packetPayment.getState() == 3 || packetPayment.getState() == 4
                || packetPayment.getState() == 5 || packetPayment.getState() == 19) {
            System.out.println("packetPayment state not 0");
            boolean isEqual = false;
            List<AccrualEmployee> paymentList = TclientService.getPaymentList(packetPayment, isEqual, alias);
            System.out.println("packetPayment state not 0");
            int successfulPaymentsCounter = TclientService.sendFillToHumo(packetPayment,
                    paymentList, uid, alias, un, pwd);
            if (successfulPaymentsCounter != paymentList.size()) {
                ISLogger.getLogger().error("successful not equal to list size");
                if(successfulPaymentsCounter == 0) {
                    ISLogger.getLogger().error("successful 0");
                    packetPayment.setState(5);
                    TclientService.updateExcelState(packetPayment);
                }else {
                    ISLogger.getLogger().error("successful not 0");
                    packetPayment.setState(4);
                    TclientService.updateExcelState(packetPayment);
                }
            } else {
                ISLogger.getLogger().error("successful all");
                packetPayment.setState(6);
                TclientService.updateExcelState(packetPayment);
            }
        }

    }

    public PacketPayment getPacketPayment() {
        return packetPayment;
    }

    public Label getLabel() {
        return label;
    }

    public int getUid() {
        return uid;
    }

    public String getAlias() {
        return alias;
    }

    public String getUn() {
        return un;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPacketPayment(PacketPayment packetPayment) {
        this.packetPayment = packetPayment;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
