package globus.IssuingWS;

public class IssuingPortProxy implements globus.IssuingWS.IssuingPort {
	  private String _endpoint = null;
	  private String _username = null;
	  private String _passwd = null;
  private globus.IssuingWS.IssuingPort issuingPort = null;
  
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
      issuingPort = (new globus.IssuingWS.IssuingServiceLocator()).getIssuing();
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
  
  public globus.IssuingWS.IssuingPort getIssuingPort() {
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort;
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo newCustomer(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomer(connectionInfo, customerInfo, customListInfo);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo newAgreement(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Agreement(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.KeyType_Agreement mainAgreementInfo, globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Customer(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.KeyType_Customer mainCustomerInfo, globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Customer(connectionInfo, mainCustomerInfo, customListInfo);
  }
  
  public void customDataOperation(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.ListType_CustomDataOperationMerch customDataOperationInfo, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_CustomDataOperationMerch_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.customDataOperation(connectionInfo, customDataOperationInfo, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo editAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_EditAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAccount(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo editCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_EditCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCard(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo editCustomer(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_EditCustomer_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCustomer(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo editAgreement(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_EditAgreement_Request agreementInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAgreement(connectionInfo, agreementInfo);
  }
  
  public void listCustomers(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ListCustomers_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomers(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCustomerCards(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomerCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccountsByCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccountsByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccounts(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ListAccounts_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccounts(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo checkAuthentication(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_Authentication parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.checkAuthentication(connectionInfo, parameters);
  }
  
  public void requestHint(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_Hint_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_Hint_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.requestHint(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryTransactionHistory(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_TransactionHist_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryTransactionHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAccountBalanceByCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAccountBalanceByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryCardholderDataHistory(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardholderDataHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void executeTransaction(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ExecTransaction_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeTransaction(connectionInfo, parameters, responseInfo, details);
  }
  
  public void transferLoyaltyPoints(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_TransferLoyaltyPoints_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.transferLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listExpiredCards(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ExpiredCards_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listExpiredCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo relinkCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RelinkCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkCard(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo changeSGP(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ChangeSGP_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeSGP(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo confirmHint(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.confirmHint(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo register3VTSCustomer(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.register3VTSCustomer(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo addCardToStop(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addCardToStop(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo activateCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ActivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateCard(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo removeCardFromStop(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.removeCardFromStop(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo dormantAccountByCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.dormantAccountByCard(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo activateAccountByCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateAccountByCard(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo changeCustomerStatus(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeCustomerStatus(connectionInfo, parameters);
  }
  
  public void queryBillInfo(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryBillInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo createLoyaltyAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.createLoyaltyAccount(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo relinkLoyaltyAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo linkCardToLoyaltyAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.linkCardToLoyaltyAccount(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo unlinkLoyaltyAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.unlinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.VersionInfo getVersion() throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getVersion();
  }
  
  public void executeSoapBatch(javax.xml.rpc.holders.ByteArrayHolder contents) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch(contents);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo newCustomerAndAgreement(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, globus.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo, globus.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, globus.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, globus.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo relinkAgreement(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAgreement(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo setLimits(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.setLimits(connectionInfo, limit_Event);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo getLimits(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getLimits(connectionInfo, limit_Event);
  }
  
  public void getLimitsBalance(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.ListType_EventBalanceRequest limit_balance_event_in, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_EventBalanceResponseHolder limit_balance_event_out) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getLimitsBalance(connectionInfo, limit_balance_event_in, responseInfo, limit_balance_event_out);
  }
  
  public void queryCardInfo(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCombiCards(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ListCombiCards_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCombiCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void renewCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RenewCard parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_RenewCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.renewCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void replaceCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ReplaceCard parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.replaceCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void duplicateCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_DuplicateCard parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_DuplicateCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.duplicateCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void orderPinEnvelope(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_OrderPinEnvelope parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_OrderPinEnvelopeHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.orderPinEnvelope(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo restoreFromArchive(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.restoreFromArchive(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo newInstantCards(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_NewInstantCards_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newInstantCards(connectionInfo, parameters);
  }
  
  public void executeSoapBatch2(java.lang.String action, java.math.BigInteger options, javax.xml.rpc.holders.StringHolder processId, javax.xml.rpc.holders.BigIntegerHolder response, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch2(action, options, processId, response, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo makeAccountDormant(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.makeAccountDormant(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo closeAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_CloseAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.closeAccount(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo embossNewCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.embossNewCard(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo deactivateCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_DeactivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.deactivateCard(connectionInfo, parameters);
  }
  
  public void listCardsByAccount(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo instantToReal(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_InstantToReal_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.instantToReal(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo enterLoyaltyAccountTransaction(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.enterLoyaltyAccountTransaction(connectionInfo, parameters);
  }
  
  public void redeemLoyaltyPoints(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPoints4Fusion(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints4Fusion(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo changeLoyaltyAccountStatus(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeLoyaltyAccountStatus(connectionInfo, parameters);
  }
  
  public void retrieveData(globus.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, globus.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.retrieveData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void manageData(globus.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, globus.issuing_v_01_02_xsd.ComplexType_ManageDataParameters parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_ManageData_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.manageData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPointsAsMoney(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPointsAsMoney(connectionInfo, parameters, responseInfo, details);
  }
  
  public void disallowCardRenewal(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_DisallowCardRenewal parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_DisallowCardRenewalHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.disallowCardRenewal(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo addTechAcctInitInfo(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addTechAcctInitInfo(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo relinkAllAgreements(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAllAgreements(connectionInfo, parameters);
  }
  
  public void getRealCard(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_GetRealCard_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getRealCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void getCardPinTryCounter(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo resetPINCounter(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.resetPINCounter(connectionInfo, parameters);
  }
  
  public void assignPIN(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_AssignPIN_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_AssignPIN_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.assignPIN(connectionInfo, parameters, responseInfo, details);
  }
  
  public void preparePayOff(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_PreparePayOff_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_PreparePayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.preparePayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void performPayOff(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_PerformPayOff_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_PerformPayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.performPayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryRealTimeStatement(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryRealTimeStatement(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAuthorizations(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAuthorizations(connectionInfo, parameters, responseInfo, details);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo changeAdditionalService(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeAdditionalService(connectionInfo, parameters);
  }
  
  public globus.issuing_v_01_02_xsd.OperationResponseInfo manageTSMWallet(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_manageTSMWallet_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.manageTSMWallet(connectionInfo, parameters);
  }
  
  public void getBeginBal(globus.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, globus.issuing_v_01_02_xsd.RowType_GetBeginBalance_Request parameters, globus.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, globus.issuing_v_01_02_xsd.holders.RowType_GetBeginBalance_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getBeginBal(connectionInfo, parameters, responseInfo, details);
  }
  
  
}