package capitalBank.IssuingWS;

public class IssuingPortProxy implements capitalBank.IssuingWS.IssuingPort {
  private String _endpoint = null;
  private String _username = null;
  private String _passwd = null;
  private capitalBank.IssuingWS.IssuingPort issuingPort = null;
  
  public IssuingPortProxy() {
    _initIssuingPortProxy();
  }
  
  public IssuingPortProxy(String endpoint, String username, String passwd) {
	    _endpoint = endpoint;
	    _username = username;
	    _passwd = passwd;
    _initIssuingPortProxy();
  }
  
  private void _initIssuingPortProxy() {
    try {
      issuingPort = (new capitalBank.IssuingWS.IssuingServiceLocator()).getIssuing();
      if (issuingPort != null) {
          if (_endpoint != null)
          {
              ((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.security.auth.username", _username);
              ((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.security.auth.password", _passwd);
          	((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
          } 
          else
          _endpoint = (String)((javax.xml.rpc.Stub)issuingPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (issuingPort != null)
      ((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public capitalBank.IssuingWS.IssuingPort getIssuingPort() {
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort;
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newCustomer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomer(connectionInfo, customerInfo, customListInfo);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Agreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.KeyType_Agreement mainAgreementInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Customer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.KeyType_Customer mainCustomerInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Customer(connectionInfo, mainCustomerInfo, customListInfo);
  }
  
  public void customDataOperation(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.ListType_CustomDataOperationMerch customDataOperationInfo, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomDataOperationMerch_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.customDataOperation(connectionInfo, customDataOperationInfo, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAccount(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCard(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editCustomer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCustomer(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request agreementInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAgreement(connectionInfo, agreementInfo);
  }
  
  public void listCustomers(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCustomers_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomers(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCustomerCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomerCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccountsByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccountsByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccounts(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListAccounts_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccounts(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo checkAuthentication(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_Authentication parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.checkAuthentication(connectionInfo, parameters);
  }
  
  public void requestHint(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_Hint_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_Hint_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.requestHint(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryTransactionHistory(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_TransactionHist_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryTransactionHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAccountBalanceByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAccountBalanceByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryCardholderDataHistory(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardholderDataHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void executeTransaction(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeTransaction(connectionInfo, parameters, responseInfo, details);
  }
  
  public void transferLoyaltyPoints(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_TransferLoyaltyPoints_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.transferLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listExpiredCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ExpiredCards_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listExpiredCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RelinkCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkCard(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeSGP(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ChangeSGP_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeSGP(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo confirmHint(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.confirmHint(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo register3VTSCustomer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.register3VTSCustomer(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addCardToStop(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addCardToStop(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo activateCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ActivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateCard(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo removeCardFromStop(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.removeCardFromStop(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo dormantAccountByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.dormantAccountByCard(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo activateAccountByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateAccountByCard(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeCustomerStatus(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeCustomerStatus(connectionInfo, parameters);
  }
  
  public void queryBillInfo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryBillInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo createLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.createLoyaltyAccount(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo linkCardToLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.linkCardToLoyaltyAccount(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo unlinkLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.unlinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.VersionInfo getVersion() throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getVersion();
  }
  
  public void executeSoapBatch(javax.xml.rpc.holders.ByteArrayHolder contents) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch(contents);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newCustomerAndAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAgreement(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo setLimits(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.setLimits(connectionInfo, limit_Event);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo getLimits(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getLimits(connectionInfo, limit_Event);
  }
  
  public void getLimitsBalance(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceRequest limit_balance_event_in, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_EventBalanceResponseHolder limit_balance_event_out) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getLimitsBalance(connectionInfo, limit_balance_event_in, responseInfo, limit_balance_event_out);
  }
  
  public void queryCardInfo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCombiCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCombiCards_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCombiCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void renewCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RenewCard parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_RenewCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.renewCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void replaceCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.replaceCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void duplicateCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_DuplicateCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.duplicateCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void orderPinEnvelope(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_OrderPinEnvelopeHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.orderPinEnvelope(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo restoreFromArchive(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.restoreFromArchive(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newInstantCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_NewInstantCards_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newInstantCards(connectionInfo, parameters);
  }
  
  public void executeSoapBatch2(java.lang.String action, java.math.BigInteger options, javax.xml.rpc.holders.StringHolder processId, javax.xml.rpc.holders.BigIntegerHolder response, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch2(action, options, processId, response, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo makeAccountDormant(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.makeAccountDormant(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo closeAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.closeAccount(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo embossNewCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.embossNewCard(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo deactivateCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DeactivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.deactivateCard(connectionInfo, parameters);
  }
  
  public void listCardsByAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo instantToReal(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_InstantToReal_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.instantToReal(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo enterLoyaltyAccountTransaction(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.enterLoyaltyAccountTransaction(connectionInfo, parameters);
  }
  
  public void redeemLoyaltyPoints(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPoints4Fusion(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints4Fusion(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeLoyaltyAccountStatus(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeLoyaltyAccountStatus(connectionInfo, parameters);
  }
  
  public void retrieveData(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.retrieveData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void manageData(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, capitalBank.issuing_v_01_02_xsd.ComplexType_ManageDataParameters parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_ManageData_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.manageData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPointsAsMoney(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPointsAsMoney(connectionInfo, parameters, responseInfo, details);
  }
  
  public void disallowCardRenewal(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_DisallowCardRenewalHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.disallowCardRenewal(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addTechAcctInitInfo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addTechAcctInitInfo(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkAllAgreements(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAllAgreements(connectionInfo, parameters);
  }
  
  public void getRealCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getRealCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void getCardPinTryCounter(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo resetPINCounter(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.resetPINCounter(connectionInfo, parameters);
  }
  
  public void assignPIN(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_AssignPIN_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.assignPIN(connectionInfo, parameters, responseInfo, details);
  }
  
  public void preparePayOff(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_PreparePayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.preparePayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void performPayOff(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_PerformPayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.performPayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryRealTimeStatement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryRealTimeStatement(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAuthorizations(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAuthorizations(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeAdditionalService(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeAdditionalService(connectionInfo, parameters);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo manageTSMWallet(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_manageTSMWallet_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.manageTSMWallet(connectionInfo, parameters);
  }
  
  public void getBeginBal(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_GetBeginBalance_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getBeginBal(connectionInfo, parameters, responseInfo, details);
  }
  
  public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo transferDataToFo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_TransferDataToFo_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.transferDataToFo(connectionInfo, parameters);
  }
  
  
}