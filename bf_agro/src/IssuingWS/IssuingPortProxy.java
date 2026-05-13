package IssuingWS;

public class IssuingPortProxy implements IssuingWS.IssuingPort {
  private String _endpoint = null;
  private String _username = null;
  private String _passwd = null;
  private IssuingWS.IssuingPort issuingPort = null;
  
  public IssuingPortProxy() {
    _initIssuingPortProxy();
  }
  
  public IssuingPortProxy(String endpoint) {
    _endpoint = endpoint;
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
      issuingPort = (new IssuingWS.IssuingServiceLocator()).getIssuing();
      if (issuingPort != null) {
        if (_endpoint != null)
        {
	          ((javax.xml.rpc.Stub)issuingPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
	          ((javax.xml.rpc.Stub) issuingPort)._setProperty("javax.xml.rpc.security.auth.username", _username);
	          ((javax.xml.rpc.Stub) issuingPort)._setProperty("javax.xml.rpc.security.auth.password", _passwd);
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
  
  public IssuingWS.IssuingPort getIssuingPort() {
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort;
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo newCustomer(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomer(connectionInfo, customerInfo, customListInfo);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo newAgreement(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo addInfo4Agreement(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.KeyType_Agreement mainAgreementInfo, issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo addInfo4Customer(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.KeyType_Customer mainCustomerInfo, issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Customer(connectionInfo, mainCustomerInfo, customListInfo);
  }
  
  public void customDataOperation(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.ListType_CustomDataOperationMerch customDataOperationInfo, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_CustomDataOperationMerch_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.customDataOperation(connectionInfo, customDataOperationInfo, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo editAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_EditAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAccount(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo editCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_EditCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCard(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo editCustomer(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_EditCustomer_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCustomer(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo editAgreement(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_EditAgreement_Request agreementInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAgreement(connectionInfo, agreementInfo);
  }
  
  public void listCustomers(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ListCustomers_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomers(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCustomerCards(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ListCustomerCards_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomerCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccountsByCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccountsByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccounts(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ListAccounts_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccounts(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo checkAuthentication(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_Authentication parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.checkAuthentication(connectionInfo, parameters);
  }
  
  public void requestHint(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_Hint_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_Hint_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.requestHint(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryTransactionHistory(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_TransactionHist_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryTransactionHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAccountBalanceByCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAccountBalanceByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryCardholderDataHistory(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_CardholderDataHist_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardholderDataHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void executeTransaction(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ExecTransaction_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeTransaction(connectionInfo, parameters, responseInfo, details);
  }
  
  public void transferLoyaltyPoints(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_TransferLoyaltyPoints_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.transferLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listExpiredCards(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ExpiredCards_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listExpiredCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo relinkCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RelinkCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkCard(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo changeSGP(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ChangeSGP_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeSGP(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo confirmHint(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.confirmHint(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo register3VTSCustomer(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.register3VTSCustomer(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo addCardToStop(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_AddCardToStopList_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addCardToStop(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo activateCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ActivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateCard(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo removeCardFromStop(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.removeCardFromStop(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo dormantAccountByCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.dormantAccountByCard(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo activateAccountByCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateAccountByCard(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo changeCustomerStatus(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeCustomerStatus(connectionInfo, parameters);
  }
  
  public void queryBillInfo(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_QueryBillInfo_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryBillInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo createLoyaltyAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.createLoyaltyAccount(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo relinkLoyaltyAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo linkCardToLoyaltyAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.linkCardToLoyaltyAccount(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo unlinkLoyaltyAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.unlinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.VersionInfo getVersion() throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getVersion();
  }
  
  public void executeSoapBatch(javax.xml.rpc.holders.ByteArrayHolder contents) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch(contents);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo newCustomerAndAgreement(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo, issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo relinkAgreement(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_AgreementReLinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAgreement(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo setLimits(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.setLimits(connectionInfo, limit_Event);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo getLimits(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getLimits(connectionInfo, limit_Event);
  }
  
  public void queryCardInfo(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_QueryCardInfo_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public void renewCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RenewCard parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_RenewCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.renewCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void replaceCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ReplaceCard parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.replaceCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void duplicateCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_DuplicateCard parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_DuplicateCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.duplicateCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void orderPinEnvelope(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_OrderPinEnvelope parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_OrderPinEnvelopeHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.orderPinEnvelope(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo restoreFromArchive(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.restoreFromArchive(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo newInstantCards(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_NewInstantCards_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newInstantCards(connectionInfo, parameters);
  }
  
  public void executeSoapBatch2(java.lang.String action, java.math.BigInteger options, javax.xml.rpc.holders.StringHolder processId, javax.xml.rpc.holders.BigIntegerHolder response, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch2(action, options, processId, response, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo makeAccountDormant(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.makeAccountDormant(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo closeAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_CloseAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.closeAccount(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo embossNewCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_EmbossNewCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.embossNewCard(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo deactivateCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_DeactivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.deactivateCard(connectionInfo, parameters);
  }
  
  public void listCardsByAccount(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo instantToReal(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_InstantToReal_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.instantToReal(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo enterLoyaltyAccountTransaction(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.enterLoyaltyAccountTransaction(connectionInfo, parameters);
  }
  
  public void redeemLoyaltyPoints(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPoints4Fusion(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints4Fusion(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo changeLoyaltyAccountStatus(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeLoyaltyAccountStatus(connectionInfo, parameters);
  }
  
  public void retrieveData(issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.retrieveData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void manageData(issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, issuing_v_01_02_xsd.ComplexType_ManageDataParameters parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_ManageData_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.manageData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPointsAsMoney(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPointsAsMoney(connectionInfo, parameters, responseInfo, details);
  }
  
  public void disallowCardRenewal(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_DisallowCardRenewal parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_DisallowCardRenewalHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.disallowCardRenewal(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo addTechAcctInitInfo(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addTechAcctInitInfo(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo relinkAllAgreements(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAllAgreements(connectionInfo, parameters);
  }
  
  public void getRealCard(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_GetRealCard_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getRealCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void getCardPinTryCounter(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo resetPINCounter(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ResetPINCounter_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.resetPINCounter(connectionInfo, parameters);
  }
  
  public void assignPIN(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_AssignPIN_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_AssignPIN_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.assignPIN(connectionInfo, parameters, responseInfo, details);
  }
  
  public void preparePayOff(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_PreparePayOff_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_PreparePayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.preparePayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void performPayOff(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_PerformPayOff_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_PerformPayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.performPayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryRealTimeStatement(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryRealTimeStatement(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAuthorizations(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAuthorizations(connectionInfo, parameters, responseInfo, details);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo changeAdditionalService(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeAdditionalService(connectionInfo, parameters);
  }
  
  public issuing_v_01_02_xsd.OperationResponseInfo manageTSMWallet(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_manageTSMWallet_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.manageTSMWallet(connectionInfo, parameters);
  }
  
  public void getBeginBal(issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, issuing_v_01_02_xsd.RowType_GetBeginBalance_Request parameters, issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, issuing_v_01_02_xsd.holders.RowType_GetBeginBalance_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getBeginBal(connectionInfo, parameters, responseInfo, details);
  }
  
  
}