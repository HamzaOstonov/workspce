package kapitalWS.IssuingWS;

public class IssuingPortProxy implements kapitalWS.IssuingWS.IssuingPort {
  private String _endpoint = null;
  private String _username = null;
  private String _passwd = null;
  private kapitalWS.IssuingWS.IssuingPort issuingPort = null;
  
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
      issuingPort = (new kapitalWS.IssuingWS.IssuingServiceLocator()).getIssuing();
      if (issuingPort != null) {
          if (_endpoint != null && _username != null && _passwd != null)
          {
			((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.security.auth.username", _username);
			((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.security.auth.password", _passwd);
			((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
          }        else
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
  
  public kapitalWS.IssuingWS.IssuingPort getIssuingPort() {
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort;
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo newCustomer(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomer(connectionInfo, customerInfo, customListInfo);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo newAgreement(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Agreement(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.KeyType_Agreement mainAgreementInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Customer(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.KeyType_Customer mainCustomerInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Customer(connectionInfo, mainCustomerInfo, customListInfo);
  }
  
  public void customDataOperation(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.ListType_CustomDataOperationMerch customDataOperationInfo, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_CustomDataOperationMerch_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.customDataOperation(connectionInfo, customDataOperationInfo, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo editAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_EditAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAccount(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo editCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_EditCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCard(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo editCustomer(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_EditCustomer_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCustomer(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo editAgreement(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_EditAgreement_Request agreementInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAgreement(connectionInfo, agreementInfo);
  }
  
  public void listCustomers(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ListCustomers_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomers(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCustomerCards(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomerCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccountsByCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccountsByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccounts(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ListAccounts_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccounts(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo checkAuthentication(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_Authentication parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.checkAuthentication(connectionInfo, parameters);
  }
  
  public void requestHint(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_Hint_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_Hint_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.requestHint(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryTransactionHistory(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_TransactionHist_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryTransactionHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAccountBalanceByCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAccountBalanceByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryCardholderDataHistory(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardholderDataHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void executeTransaction(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ExecTransaction_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeTransaction(connectionInfo, parameters, responseInfo, details);
  }
  
  public void transferLoyaltyPoints(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_TransferLoyaltyPoints_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.transferLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listExpiredCards(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ExpiredCards_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listExpiredCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo relinkCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RelinkCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkCard(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo changeSGP(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ChangeSGP_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeSGP(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo confirmHint(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.confirmHint(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo register3VTSCustomer(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.register3VTSCustomer(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo addCardToStop(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addCardToStop(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo activateCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ActivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateCard(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo removeCardFromStop(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.removeCardFromStop(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo dormantAccountByCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.dormantAccountByCard(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo activateAccountByCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateAccountByCard(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo changeCustomerStatus(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeCustomerStatus(connectionInfo, parameters);
  }
  
  public void queryBillInfo(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryBillInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo createLoyaltyAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.createLoyaltyAccount(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo relinkLoyaltyAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo linkCardToLoyaltyAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.linkCardToLoyaltyAccount(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo unlinkLoyaltyAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.unlinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.VersionInfo getVersion() throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getVersion();
  }
  
  public void executeSoapBatch(javax.xml.rpc.holders.ByteArrayHolder contents) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch(contents);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo newCustomerAndAgreement(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo relinkAgreement(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAgreement(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo setLimits(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.setLimits(connectionInfo, limit_Event);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo getLimits(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getLimits(connectionInfo, limit_Event);
  }
  
  public void getLimitsBalance(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.ListType_EventBalanceRequest limit_balance_event_in, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_EventBalanceResponseHolder limit_balance_event_out) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getLimitsBalance(connectionInfo, limit_balance_event_in, responseInfo, limit_balance_event_out);
  }
  
  public void queryCardInfo(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCombiCards(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ListCombiCards_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCombiCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void renewCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RenewCard parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_RenewCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.renewCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void replaceCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ReplaceCard parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.replaceCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void duplicateCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_DuplicateCard parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_DuplicateCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.duplicateCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void orderPinEnvelope(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_OrderPinEnvelope parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_OrderPinEnvelopeHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.orderPinEnvelope(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo restoreFromArchive(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.restoreFromArchive(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo newInstantCards(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_NewInstantCards_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newInstantCards(connectionInfo, parameters);
  }
  
  public void executeSoapBatch2(java.lang.String action, java.math.BigInteger options, javax.xml.rpc.holders.StringHolder processId, javax.xml.rpc.holders.BigIntegerHolder response, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch2(action, options, processId, response, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo makeAccountDormant(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.makeAccountDormant(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo closeAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_CloseAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.closeAccount(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo embossNewCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.embossNewCard(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo deactivateCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_DeactivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.deactivateCard(connectionInfo, parameters);
  }
  
  public void listCardsByAccount(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo instantToReal(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_InstantToReal_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.instantToReal(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo enterLoyaltyAccountTransaction(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.enterLoyaltyAccountTransaction(connectionInfo, parameters);
  }
  
  public void redeemLoyaltyPoints(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPoints4Fusion(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints4Fusion(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo changeLoyaltyAccountStatus(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeLoyaltyAccountStatus(connectionInfo, parameters);
  }
  
  public void retrieveData(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, kapitalWS.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.retrieveData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void manageData(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, kapitalWS.issuing_v_01_02_xsd.ComplexType_ManageDataParameters parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_ManageData_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.manageData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPointsAsMoney(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPointsAsMoney(connectionInfo, parameters, responseInfo, details);
  }
  
  public void disallowCardRenewal(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_DisallowCardRenewal parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_DisallowCardRenewalHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.disallowCardRenewal(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo addTechAcctInitInfo(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addTechAcctInitInfo(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo relinkAllAgreements(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAllAgreements(connectionInfo, parameters);
  }
  
  public void getRealCard(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_GetRealCard_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getRealCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void getCardPinTryCounter(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo resetPINCounter(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.resetPINCounter(connectionInfo, parameters);
  }
  
  public void assignPIN(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_AssignPIN_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_AssignPIN_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.assignPIN(connectionInfo, parameters, responseInfo, details);
  }
  
  public void preparePayOff(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_PreparePayOff_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_PreparePayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.preparePayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void performPayOff(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_PerformPayOff_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_PerformPayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.performPayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryRealTimeStatement(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryRealTimeStatement(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAuthorizations(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAuthorizations(connectionInfo, parameters, responseInfo, details);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo changeAdditionalService(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeAdditionalService(connectionInfo, parameters);
  }
  
  public kapitalWS.issuing_v_01_02_xsd.OperationResponseInfo manageTSMWallet(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_manageTSMWallet_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.manageTSMWallet(connectionInfo, parameters);
  }
  
  public void getBeginBal(kapitalWS.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, kapitalWS.issuing_v_01_02_xsd.RowType_GetBeginBalance_Request parameters, kapitalWS.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, kapitalWS.issuing_v_01_02_xsd.holders.RowType_GetBeginBalance_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getBeginBal(connectionInfo, parameters, responseInfo, details);
  }
  
  
}