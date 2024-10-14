package com.is.tieto_visae.tieto;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
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

		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo);
		} catch (Exception e22) {
			str1 = " str1=error connectionInfo";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err connectionInfo  **************" + str1);
		log.error("** not err connectionInfo  **************" + str1);
		
		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(issuingPortProxy);
		} catch (Exception e22) {
			str1 = " str1=error issuingPortProxy";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err issuingPortProxy  **************" + str1);
		log.error("** not err issuingPortProxy  **************" + str1);
		
		
		return customers;
	}

	public ArrayList<ListAccountsItem> tietoListAccounts(ListAccountsFilter filter, ResponseInfoHolder response) {
		ArrayList<ListAccountsItem> accounts = new ArrayList<ListAccountsItem>();
		RowType_ListAccounts_Request request;
		ListType_GenericHolder resultHolder = new ListType_GenericHolder();
		OperationResponseInfoHolder responseInfoHolder = new OperationResponseInfoHolder();

		OperationConnectionInfo connectionInfo = getConnectionInfo();
		request = KapitalModelTransformer.getTietoListAccountsRequest(filter);


		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo);
		} catch (Exception e22) {
			str1 = " str1=error connectionInfo";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err connectionInfo  **************" + str1);

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(filter);
		} catch (Exception e22) {
			str1 = " str1=error filter";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err filter  **************" + str1);

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
		} catch (Exception e22) {
			str1 = " str1=error request";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err request  **************" + str1);


		try {
			issuingPortProxy.listAccounts(connectionInfo, request, responseInfoHolder, resultHolder);

			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultHolder);
			} catch (Exception e22) {
				str1 = " str1=error resultHolder";
			} finally {
			}
			com.is.ISLogger.getLogger().error("** not err resultHolder  **************" + str1);
			
			accounts = KapitalModelTransformer.getListAccountsItem(resultHolder.value);

			try {
				str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(accounts);
			} catch (Exception e22) {
				str1 = " str1=error accounts";
			} finally {
			}
			com.is.ISLogger.getLogger().error("** not err accounts  **************" + str1);
			
			response.value = KapitalModelTransformer.getResponseInfo(responseInfoHolder.value);
			
			com.is.ISLogger.getLogger().error("** not err ok accounts  ************** !" );
			
		} catch (RemoteException e) {
			com.is.ISLogger.getLogger().error("** not err no ok accounts (( ************** !" );
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

		String str1 = "";
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(connectionInfo);
		} catch (Exception e22) {
			str1 = " str1=error connectionInfo";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err bal connectionInfo  **************" + str1);

		try {
			str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
		} catch (Exception e22) {
			str1 = " str1=error request";
		} finally {
		}
		com.is.ISLogger.getLogger().error("** not err bal request  **************" + str1);

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
				
				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultHolder);
				} catch (Exception e22) {
					str1 = " str1=error resultHolder";
				} finally {
				}
				com.is.ISLogger.getLogger().error("** not err bal1 resultHolder  **************" + str1);
				
				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(balanceRequest);
				} catch (Exception e22) {
					str1 = " str1=error balanceRequest";
				} finally {
				}
				com.is.ISLogger.getLogger().error("** not err bal1 balanceRequest  **************" + str1);

				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(balanceResponseInfoHolder);
				} catch (Exception e22) {
					str1 = " str1=error balanceResponseInfoHolder";
				} finally {
				}
				com.is.ISLogger.getLogger().error("** not err bal1 balanceResponseInfoHolder  **************" + str1);

				try {
					str1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultHolder);
				} catch (Exception e22) {
					str1 = " str1=error resultHolder";
				} finally {
				}
				com.is.ISLogger.getLogger().error("** not err bal1 resultHolder  **************" + str1);

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

		com.is.ISLogger.getLogger().error("** not err wSConnectionInfo  ************** " + wSConnectionInfo);
		com.is.ISLogger.getLogger().error("** not err wSConnectionInfo.getHost()  ************** " + wSConnectionInfo.getHost());
		com.is.ISLogger.getLogger().error("** not err wSConnectionInfo.getLogin()  ************** " + wSConnectionInfo.getLogin());
		
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
