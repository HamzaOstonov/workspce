package com.is.globalTieto.webServices;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.is.globalTieto.modelTransform.KapitalModelTransformer;
import com.is.globalTieto.tietoModels.CardBalance;
import com.is.globalTieto.tietoModels.CardBalanceFilter;
import com.is.globalTieto.tietoModels.CardInfo;
import com.is.globalTieto.tietoModels.ExecTransactionRequest;
import com.is.globalTieto.tietoModels.ExecTransactionResponse;
import com.is.globalTieto.tietoModels.ListAccountsFilter;
import com.is.globalTieto.tietoModels.ListAccountsItem;
import com.is.globalTieto.tietoModels.ListCardsFilter;
import com.is.globalTieto.tietoModels.ListCardsItem;
import com.is.globalTieto.tietoModels.ListCustomersFilter;
import com.is.globalTieto.tietoModels.ListCustomersItem;
import com.is.globalTieto.tietoModels.ResponseInfoHolder;
import com.is.globalTieto.tietoModels.WSConnectionInfo;
import com.is.globalTieto.utils.WebServiceUtils;
import com.is.utils.CheckNull;

import kapitalWS.IssuingWS.IssuingPortProxy;
import kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo;
import kapitalWS.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ExecTransaction_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ListAccounts_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request;
import kapitalWS.issuing_v_01_02_xsd.RowType_ListCustomers_Request;
import kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder;
import kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder;
import kapitalWS.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder;

public class KapitalWebService {

	private static Logger log = Logger.getLogger(WebService.class);

	private IssuingPortProxy issuingPortProxy;
	private WSConnectionInfo wSConnectionInfo;

	public KapitalWebService() {
		
		getConnection();
	}

	public ArrayList<ListCustomersItem> tietoListCustomers(ListCustomersFilter filter, ResponseInfoHolder response) {
		ArrayList<ListCustomersItem> customers = new ArrayList<ListCustomersItem>();
		RowType_ListCustomers_Request request;
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();

		OperationConnectionInfo connectionInfo = getConnectionInfo();
		filter.setBank_c(wSConnectionInfo.getBankC());
		request = KapitalModelTransformer.getTietoListCustomersRequest(filter);

		try {
			issuingPortProxy.listCustomers(connectionInfo, request, responseInfoHolder, resultHolder);
			customers = KapitalModelTransformer.getListCustomersItem(resultHolder.value);
			response.value = KapitalModelTransformer.getResponseInfo(responseInfoHolder.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		} catch (ParseException e) {
			log.error(CheckNull.getPstr(e));
		}

		return customers;
	}

	public ArrayList<ListAccountsItem> tietoListAccounts(ListAccountsFilter filter, ResponseInfoHolder response) {
		ArrayList<ListAccountsItem> accounts = new ArrayList<ListAccountsItem>();
		RowType_ListAccounts_Request request;
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();

		OperationConnectionInfo connectionInfo = getConnectionInfo();
		request = KapitalModelTransformer.getTietoListAccountsRequest(filter);

		try {
			issuingPortProxy.listAccounts(connectionInfo, request, responseInfoHolder, resultHolder);
			accounts = KapitalModelTransformer.getListAccountsItem(resultHolder.value);
			response.value = KapitalModelTransformer.getResponseInfo(responseInfoHolder.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		}

		return accounts;
	}

	public ArrayList<CardInfo> tietoListCards(ListCardsFilter filter, ResponseInfoHolder response) {
		ArrayList<ListCardsItem> cards;
		ArrayList<CardInfo> cardInfos = new ArrayList<CardInfo>();
		RowType_ListCardsByAccount_Request request;
		RowType_AccBalanceQueryByCard_Request balanceRequest;
		CardBalanceFilter balanceFilter = new CardBalanceFilter();
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();
		OperationResponseInfoHolder balanceResponseInfoHolder = new OperationResponseInfoHolder();

		OperationConnectionInfo connectionInfo = getConnectionInfo();
		request = KapitalModelTransformer.getTietoListCardsRequest(filter);

		try {
			issuingPortProxy.listCardsByAccount(connectionInfo, request, responseInfoHolder, resultHolder);
			cards = KapitalModelTransformer.getListCardsItem(resultHolder.value);
			balanceFilter.setBank_c(wSConnectionInfo.getBankC());
			balanceFilter.setGroupc(wSConnectionInfo.getGroupC());
			for (ListCardsItem card : cards) {
				CardInfo addCardInfo = new CardInfo();
				CardBalance balance;
				addCardInfo.setMainInfo(card);
				balanceFilter.setCard(card.getCard());
				balanceRequest = KapitalModelTransformer.getTietoBalanceRequest(balanceFilter);
				issuingPortProxy.queryAccountBalanceByCard(connectionInfo, balanceRequest, balanceResponseInfoHolder,
						resultHolder);
				balance = KapitalModelTransformer.getBalance(resultHolder.value);
				addCardInfo.setBalance(balance);
				cardInfos.add(addCardInfo);
			}
			response.value = KapitalModelTransformer.getResponseInfo(responseInfoHolder.value);
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

	public void executeTransaction(ExecTransactionRequest filter, ResponseInfoHolder response) {
//		Boolean result = false;
		RowType_ExecTransaction_Request request;
		OperationConnectionInfo connectionInfo = getConnectionInfo();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();
		RowType_ExecTransaction_ResponseHolder details = new RowType_ExecTransaction_ResponseHolder();
//		ExecTransactionResponse returnDetails = new ExecTransactionResponse();

		filter.setBank_c(wSConnectionInfo.getBankC());
		filter.setGroupc(wSConnectionInfo.getGroupC());

		request = KapitalModelTransformer.getTietoExecTransactionRequest(filter);
		try {
			issuingPortProxy.executeTransaction(connectionInfo, request, responseInfoHolder, details);
//			returnDetails = KapitalModelTransformer.getExecTransactionResponse(details.value);
			response.value = KapitalModelTransformer.getResponseInfo(responseInfoHolder.value);
		} catch (RemoteException e) {
			log.error(CheckNull.getPstr(e));
		}

//		return result;
	}

	private OperationConnectionInfo getConnectionInfo() {
		OperationConnectionInfo connectionInfo = new OperationConnectionInfo();

		connectionInfo.setBANK_C(wSConnectionInfo.getBankC());
		connectionInfo.setGROUPC(wSConnectionInfo.getGroupC());
		connectionInfo.setEXTERNAL_SESSION_ID(WebServiceUtils.getUniqueConstant());

		return connectionInfo;
	}

}
