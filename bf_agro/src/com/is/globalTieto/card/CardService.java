package com.is.globalTieto.card;

import com.is.ConnectionPool;
import com.is.globalTieto.Constants;
import com.is.globalTieto.modelTransform.KapitalModelTransformer;
import com.is.globalTieto.tietoModels.*;
import com.is.globalTieto.webServices.KapitalWebService;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CardService {
    private KapitalWebService service;
    private Settings settings;
    private static Logger log = Logger.getLogger(CardService.class);

    CardService(){
        service = new KapitalWebService();
    }

    public ArrayList<CardInfo> listCards(ListAccountsItem filter, ResponseInfoHolder response) {
        ArrayList<CardInfo> result;

        ListCardsFilter rightFilter = KapitalModelTransformer.getCardFilterByAcc(filter);
        result = listCards(rightFilter, response);

        return result;
    }

    private ArrayList<CardInfo> listCards(ListCardsFilter filter, ResponseInfoHolder response) {
        ArrayList<CardInfo> result;

        result = service.tietoListCards(filter, response);

        return result;
    }

    private Settings getRefillSettings() throws SQLException {
        Connection c;
        PreparedStatement ps;
        ResultSet rs;
        String name;
        String value;
        Settings result = new Settings();
        c = ConnectionPool.getConnection();
        ps = c.prepareStatement("SELECT * FROM bf_ti_refill_sets");
        rs = ps.executeQuery();
        while (rs.next()){
            name = rs.getString("name");
            value = rs.getString("value");
            if(name.equals("PAYMENT_MODE")){
                result.setPaymentMode(value);
            }else if(name.equals("ACC_CCY")){
                result.setAccCcy(value);
            }else if(name.equals("TRANZ_CCY")){
                result.setTranzType(value);
            }else if(name.equals("TRANZ_TYPE")){
                result.setTranzType(value);
            }
        }

        return result;
    }

    public void addTransaction(ListCustomersItem customer, ListAccountsItem account, CardInfo card, Long sum,
                               String branch, int isCashPayment) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            if(settings == null){
                settings = getRefillSettings();
            }
            c = ConnectionPool.getConnection();
            ps = c.prepareStatement(
                    "INSERT INTO bf_tieto_refill (ID, CARD_ACCT, CARD, TRANSACTION_AMNT, BRANCH, CLIENT_TIETO, CLIENT_BANK, STATE_ID, BANK_C, GROUPC, PAYMENT_MODE, CARD_ACC_CCY, TRAN_TYPE, TRAN_CCY, CLIENT_NAME, CLIENT_SURNAME, IS_CASH_PAYMENT) VALUES(seq_bf_tieto_refill.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, account.getCard_acct());
            ps.setString(2, card.getMainInfo().getCard());
            ps.setLong(3, sum);
            ps.setString(4, branch);
            ps.setString(5, customer.getClient());
            ps.setString(6, customer.getClient_b());
            ps.setLong(7, Constants.REFILL_STATE_ADDED);
            ps.setString(8, card.getBalance().getBank_c());
            ps.setString(9, card.getBalance().getGroupc());
            ps.setString(10, settings.getPaymentMode());
            ps.setString(11, settings.getAccCcy());
            ps.setString(12, settings.getTranzType());
            ps.setString(13, settings.getTranzCcy());
            ps.setString(14, customer.getF_names());
            ps.setString(15, customer.getSurname());
            ps.setInt(16, isCashPayment);
            ps.executeQuery();
            c.commit();
        } catch (SQLException e) {
            log.error(CheckNull.getPstr(e));
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                log.error(CheckNull.getPstr(e));
            }

        }
    }

}
