package agroBank.IssuingWS;

public class IssuingPortProxy implements agroBank.IssuingWS.IssuingPort {
  private String _endpoint = null;
  private String _username = null;
  private String _passwd = null;
  private agroBank.IssuingWS.IssuingPort issuingPort = null;
  
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
      issuingPort = (new agroBank.IssuingWS.IssuingServiceLocator()).getIssuing();
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
  
  public agroBank.IssuingWS.IssuingPort getIssuingPort() {
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort;
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo newCustomer(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomer(connectionInfo, customerInfo, customListInfo);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo newAgreement(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newAgreement(connectionInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Agreement(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.KeyType_Agreement mainAgreementInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Agreement(connectionInfo, mainAgreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Customer(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.KeyType_Customer mainCustomerInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addInfo4Customer(connectionInfo, mainCustomerInfo, customListInfo);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo editAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_EditAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAccount(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo editCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_EditCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCard(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo editCustomer(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editCustomer(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo editAgreement(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request agreementInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.editAgreement(connectionInfo, agreementInfo);
  }
  
  public void listCustomers(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ListCustomers_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomers(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listCustomerCards(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCustomerCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccountsByCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccountsByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listAccounts(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ListAccounts_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listAccounts(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo checkAuthentication(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_Authentication parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.checkAuthentication(connectionInfo, parameters);
  }
  
  public void requestHint(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_Hint_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_Hint_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.requestHint(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryTransactionHistory(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_TransactionHist_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryTransactionHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryAccountBalanceByCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryAccountBalanceByCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void queryCardholderDataHistory(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardholderDataHistory(connectionInfo, parameters, responseInfo, details);
  }
  
  public void executeTransaction(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeTransaction(connectionInfo, parameters, responseInfo, details);
  }
  
  public void listExpiredCards(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ExpiredCards_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listExpiredCards(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo relinkCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RelinkCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkCard(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo changeSGP(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ChangeSGP_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeSGP(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo confirmHint(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.confirmHint(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo register3VTSCustomer(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.register3VTSCustomer(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo addCardToStop(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addCardToStop(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo activateCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ActivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateCard(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo removeCardFromStop(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.removeCardFromStop(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo dormantAccountByCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.dormantAccountByCard(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo activateAccountByCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.activateAccountByCard(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo changeCustomerStatus(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeCustomerStatus(connectionInfo, parameters);
  }
  
  public void queryBillInfo(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryBillInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo createLoyaltyAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.createLoyaltyAccount(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo relinkLoyaltyAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo linkCardToLoyaltyAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.linkCardToLoyaltyAccount(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo unlinkLoyaltyAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.unlinkLoyaltyAccount(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.VersionInfo getVersion() throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getVersion();
  }
  
  public void executeSoapBatch(javax.xml.rpc.holders.ByteArrayHolder contents) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch(contents);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo newCustomerAndAgreement(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.newCustomerAndAgreement(connectionInfo, customerInfo, customListInfo, agreementInfo, accountsListInfo, cardsListInfo);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo relinkAgreement(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAgreement(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo setLimits(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.setLimits(connectionInfo, limit_Event);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo getLimits(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.getLimits(connectionInfo, limit_Event);
  }
  
  public void queryCardInfo(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.queryCardInfo(connectionInfo, parameters, responseInfo, details);
  }
  
  public void renewCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RenewCard parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_RenewCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.renewCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void replaceCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ReplaceCard parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.replaceCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void duplicateCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_DuplicateCard parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_DuplicateCardHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.duplicateCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void orderPinEnvelope(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_OrderPinEnvelopeHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.orderPinEnvelope(connectionInfo, parameters, responseInfo, details);
  }
  
  public void executeSoapBatch2(java.lang.String action, java.math.BigInteger options, javax.xml.rpc.holders.StringHolder processId, javax.xml.rpc.holders.BigIntegerHolder response, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.executeSoapBatch2(action, options, processId, response, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo makeAccountDormant(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.makeAccountDormant(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo closeAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.closeAccount(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo embossNewCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.embossNewCard(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo deactivateCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_DeactivateCard_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.deactivateCard(connectionInfo, parameters);
  }
  
  public void listCardsByAccount(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.listCardsByAccount(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo instantToReal(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_InstantToReal_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.instantToReal(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo enterLoyaltyAccountTransaction(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.enterLoyaltyAccountTransaction(connectionInfo, parameters);
  }
  
  public void redeemLoyaltyPoints(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPoints4Fusion(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPoints4Fusion(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo changeLoyaltyAccountStatus(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.changeLoyaltyAccountStatus(connectionInfo, parameters);
  }
  
  public void retrieveData(agroBank.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, agroBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.retrieveData(connectionInfo, parameters, responseInfo, details);
  }
  
  public void redeemLoyaltyPointsAsMoney(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.redeemLoyaltyPointsAsMoney(connectionInfo, parameters, responseInfo, details);
  }
  
  public void disallowCardRenewal(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_DisallowCardRenewalHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.disallowCardRenewal(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo addTechAcctInitInfo(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.addTechAcctInitInfo(connectionInfo, parameters);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo relinkAllAgreements(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.relinkAllAgreements(connectionInfo, parameters);
  }
  
  public void getRealCard(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_GetRealCard_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getRealCard(connectionInfo, parameters, responseInfo, details);
  }
  
  public void getCardPinTryCounter(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.getCardPinTryCounter(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo resetPINCounter(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.resetPINCounter(connectionInfo, parameters);
  }
  
  public void assignPIN(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_AssignPIN_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_AssignPIN_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.assignPIN(connectionInfo, parameters, responseInfo, details);
  }
  
  public void preparePayOff(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Request parameters, agroBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, agroBank.issuing_v_01_02_xsd.holders.RowType_PreparePayOff_ResponseHolder details) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    issuingPort.preparePayOff(connectionInfo, parameters, responseInfo, details);
  }
  
  public agroBank.issuing_v_01_02_xsd.OperationResponseInfo performPayOff(agroBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, agroBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Request parameters) throws java.rmi.RemoteException{
    if (issuingPort == null)
      _initIssuingPortProxy();
    return issuingPort.performPayOff(connectionInfo, parameters);
  }
  
  
}