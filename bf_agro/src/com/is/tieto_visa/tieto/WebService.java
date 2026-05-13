package com.is.tieto_visa.tieto;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.is.utils.CheckNull;

import visa.IssuingWS.IssuingPortProxy;
import visa.issuing_v_01_02_xsd.OperationConnectionInfo;
import visa.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request;
import visa.issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import visa.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
import visa.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import visa.issuing_v_01_02_xsd.RowType_ListCustomers_Request;
import visa.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import visa.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import visa.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;

public class WebService {

	private static Logger log = Logger.getLogger(WebService.class);

	private IssuingPortProxy issuingPortProxy;
	private WSConnectionInfo wSConnectionInfo;

	public WebService() {
		
		getConnection();
	}

	public ArrayList<ListCustomersItem> tietoListCustomers(ListCustomersFilter filter, ResponseInfoHolder response) {
		ArrayList<ListCustomersItem> customers = new ArrayList<ListCustomersItem>();
		RowType_ListCustomers_Request request = new RowType_ListCustomers_Request();
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();

		OperationConnectionInfo connectionInfo = getConnectionInfo();
		filter.setBank_c(wSConnectionInfo.getBankC());
		request = ModelTransformer.getTietoListCustomersRequest(filter);

		try {
			issuingPortProxy.listCustomers(connectionInfo, request, responseInfoHolder, resultHolder);
			customers = ModelTransformer.getListCustomersItem(resultHolder.value);
			response.value = ModelTransformer.getResponseInfo(responseInfoHolder.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		} catch (ParseException e) {
			log.error(CheckNull.getPstr(e));
		}

		return customers;
	}
	
	public ArrayList<ListAccountsItem> tietoListAccounts(ListAccountsFilter filter, ResponseInfoHolder response){
		ArrayList<ListAccountsItem> accounts = new ArrayList<ListAccountsItem>();
		RowType_ListAccounts_Request request = new RowType_ListAccounts_Request();
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();

		OperationConnectionInfo connectionInfo = getConnectionInfo();
		request = ModelTransformer.getTietoListAccountsRequest(filter);

		try {
			issuingPortProxy.listAccounts(connectionInfo, request, responseInfoHolder, resultHolder);
			accounts = ModelTransformer.getListAccountsItem(resultHolder.value);
			response.value = ModelTransformer.getResponseInfo(responseInfoHolder.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		}
		
		return accounts;
	}

	public ArrayList<CardInfo> tietoListCards(ListCardsFilter filter, ResponseInfoHolder response){
		ArrayList<ListCardsItem> cards = new ArrayList<ListCardsItem>();
		ArrayList<CardInfo> cardInfos = new ArrayList<CardInfo>(); 
		RowType_ListCardsByAccount_Request request = new RowType_ListCardsByAccount_Request();
		RowType_AccBalanceQueryByCard_Request balanceRequest = new RowType_AccBalanceQueryByCard_Request();
		CardBalanceFilter balanceFilter = new CardBalanceFilter();
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();
		OperationResponseInfoHolder balanceResponseInfoHolder = new OperationResponseInfoHolder();
		
		OperationConnectionInfo connectionInfo = getConnectionInfo();
		request = ModelTransformer.getTietoListCardsRequest(filter);
		
		try {
			issuingPortProxy.listCardsByAccount(connectionInfo, request, responseInfoHolder, resultHolder);
			cards = ModelTransformer.getListCardsItem(resultHolder.value);
			balanceFilter.setBank_c(wSConnectionInfo.getBankC());
			balanceFilter.setGroupc(wSConnectionInfo.getGroupC());
			for (ListCardsItem card : cards) {
				CardInfo addCardInfo = new CardInfo();
				CardBalance balance = new CardBalance();
				addCardInfo.setMainInfo(card);
				balanceFilter.setCard(card.getCard());
				balanceRequest = ModelTransformer.getTietoBalanceRequest(balanceFilter);
				issuingPortProxy.queryAccountBalanceByCard(connectionInfo, balanceRequest, balanceResponseInfoHolder, resultHolder);
				balance = ModelTransformer.getBalance(resultHolder.value);
				addCardInfo.setBalance(balance);
				cardInfos.add(addCardInfo);
			}
			response.value = ModelTransformer.getResponseInfo(responseInfoHolder.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		} catch (ParseException e) {
			log.error(CheckNull.getPstr(e));
		}
		
		return cardInfos;
	}
	
	private void getConnection() {
		wSConnectionInfo = new WSConnectionInfo();
		ConnectionInfoHandler connectionInfoHandler;

		connectionInfoHandler = new ConnectionInfoFromDB();
		wSConnectionInfo = connectionInfoHandler.getWSConnectionInfo();

		issuingPortProxy = new IssuingPortProxy(wSConnectionInfo.getHost(), wSConnectionInfo.getLogin(),
				wSConnectionInfo.getPassword());
	}
	
	public Boolean executeTransaction(ExecTransactionRequest filter, ResponseInfoHolder response){
		Boolean result = false;
		RowType_ExecTransaction_Request request = new RowType_ExecTransaction_Request();
		OperationConnectionInfo connectionInfo = getConnectionInfo();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
		ExecTransactionResponse returnDetails = new ExecTransactionResponse(); 
		
		filter.setBank_c(wSConnectionInfo.getBankC());
		filter.setGroupc(wSConnectionInfo.getGroupC());
		
		request = ModelTransformer.getTietoExecTransactionRequest(filter);
		try {
			issuingPortProxy.executeTransaction(connectionInfo, request, responseInfoHolder, details);
			returnDetails = ModelTransformer.getExecTransactionResponse(details.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		}
		
		return result;
	}

	private OperationConnectionInfo getConnectionInfo() {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();

		connectionInfo.setBANK_C(wSConnectionInfo.getBankC());
		connectionInfo.setGROUPC(wSConnectionInfo.getGroupC());
		connectionInfo.setEXTERNAL_SESSION_ID(WebServiceUtils.getUniqueConstant());

		return connectionInfo;
	}

}
