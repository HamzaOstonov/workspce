package com.is.tieto_globuz.customer;

import globus.IssuingWS.IssuingPortProxy;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.is.tieto_globuz.Utils;

public class RealCardTimer extends TimerTask {

    public void run() {
        try {
            IssuingPortProxy issuingPortProxy = CustomerService.initNp();
            String bankC = Utils.getValue("HUMO_BANK_C");
            String groupC = Utils.getValue("HUMO_GROUPC");
            ArrayList<String> cardList = CustomerService.getCardsWithoutRealCard();
            for (String card : cardList) {
                CustomerService.insertRealCard(card,
                        CustomerService.getRealCardNumber(card, issuingPortProxy, bankC, groupC));
            }
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
