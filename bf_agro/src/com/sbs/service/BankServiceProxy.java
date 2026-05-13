package com.sbs.service;

public class BankServiceProxy implements com.sbs.service.BankService {
  private String _endpoint = null;
  private com.sbs.service.BankService bankService = null;
  
  public BankServiceProxy() {
    _initBankServiceProxy();
  }
  
  public BankServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initBankServiceProxy();
  }
  
  private void _initBankServiceProxy() {
    try {
      bankService = (new com.sbs.service.BankServiceServiceLocator()).getBankServicePort();
      if (bankService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)bankService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)bankService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (bankService != null)
      ((javax.xml.rpc.Stub)bankService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sbs.service.BankService getBankService() {
    if (bankService == null)
      _initBankServiceProxy();
    return bankService;
  }
  
  public com.sbs.service.PolicyResult getPolicy(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p3T32) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPolicy(bankInn, p1T1, p3T32);
  }
  
  public com.sbs.service.CommissionGNKResult getCommissionGNK(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p9T28) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCommissionGNK(bankInn, p1T1, p9T28);
  }
  
  public com.sbs.service.CommissionsGNKResult getCommissionsGNK(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCommissionsGNK(bankInn, p1T1);
  }
  
  public com.sbs.service.CommissionGNKResult saveCommissionGNK(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.CommissionGNK commissionGNK) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveCommissionGNK(bankInn, p1T1, commissionGNK);
  }
  
  public com.sbs.service.TransferResult confirmTransfer(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p8T29, java.lang.Short action, java.lang.String fio) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.confirmTransfer(bankInn, p1T1, p8T29, action, fio);
  }
  
  public com.sbs.service.DelegateResult confirmDelegate(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p20T30, java.lang.Short action, java.lang.String fio) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.confirmDelegate(bankInn, p1T1, p20T30, action, fio);
  }
  
  public com.sbs.service.PolicyTimeChangeResult savePolicyTimeChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p3T32, com.sbs.service.PolicyTimeChange policyTimeChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePolicyTimeChange(bankInn, p1T1, p3T32, policyTimeChange);
  }
  
  public com.sbs.service.PolicySumChangeResult savePolicySumChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p3T32, com.sbs.service.PolicySumChange policySumChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePolicySumChange(bankInn, p1T1, p3T32, policySumChange);
  }
  
  public com.sbs.service.PaymentsRefResult getPaymentsRef(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPaymentsRef(bankInn, p1T1);
  }
  
  public com.sbs.service.PaymentRefResult savePaymentRef(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.PaymentRef paymentRef) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePaymentRef(bankInn, p1T1, paymentRef);
  }
  
  public com.sbs.service.PaymentRefSumChangeResult savePaymentRefSumChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p2T37, com.sbs.service.PaymentRefSumChange paymentRefSumChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePaymentRefSumChange(bankInn, p1T1, p2T37, paymentRefSumChange);
  }
  
  public com.sbs.service.CompensationResult getCompensation(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p15T42) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCompensation(bankInn, p1T1, p15T42);
  }
  
  public com.sbs.service.CompensationsResult getCompensations(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCompensations(bankInn, p1T1);
  }
  
  public com.sbs.service.CompensationResult saveCompensation(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Compensation compensation) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveCompensation(bankInn, p1T1, compensation);
  }
  
  public com.sbs.service.MovesFromImResult getMovesFromIm(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMovesFromIm(bankInn, p1T1);
  }
  
  public com.sbs.service.MovesFromExResult getMovesFromEx(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMovesFromEx(bankInn, p1T1);
  }
  
  public com.sbs.service.Record1Result dicAgreementState(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicAgreementState(bankInn);
  }
  
  public com.sbs.service.Record2Result dicBenefitsInfo(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicBenefitsInfo(bankInn);
  }
  
  public com.sbs.service.Record2Result dicBenefitsType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicBenefitsType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicIndustrialZone(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicIndustrialZone(bankInn);
  }
  
  public com.sbs.service.Record2Result dicContractSubject(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicContractSubject(bankInn);
  }
  
  public com.sbs.service.Record5Result dicContractType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicContractType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicPaymentSource(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicPaymentSource(bankInn);
  }
  
  public com.sbs.service.Record2Result dicPenaltyType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicPenaltyType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicTransferType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicTransferType(bankInn);
  }
  
  public com.sbs.service.Record3Result dicPaymentMethod(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicPaymentMethod(bankInn);
  }
  
  public com.sbs.service.Record2Result dicTransportType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicTransportType(bankInn);
  }
  
  public com.sbs.service.Record3Result dicEnterpriseType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicEnterpriseType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicServiceType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicServiceType(bankInn);
  }
  
  public com.sbs.service.Record6Result dicRecordState(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicRecordState(bankInn);
  }
  
  public com.sbs.service.ContractResult getContract(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getContract(bankInn, p1T1);
  }
  
  public com.sbs.service.ContractResult getHistory(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getHistory(bankInn, p1T1);
  }
  
  public com.sbs.service.GarantResult getGarant(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p3T18) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getGarant(bankInn, p1T1, p3T18);
  }
  
  public com.sbs.service.GarantsResult getGarants(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getGarants(bankInn, p1T1);
  }
  
  public com.sbs.service.GarantResult saveGarant(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Garant garant) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveGarant(bankInn, p1T1, garant);
  }
  
  public com.sbs.service.AccreditivResult getAccreditiv(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p2T21) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getAccreditiv(bankInn, p1T1, p2T21);
  }
  
  public com.sbs.service.DebetResult getDebet(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p13T24) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getDebet(bankInn, p1T1, p13T24);
  }
  
  public com.sbs.service.DebetsResult getDebets(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getDebets(bankInn, p1T1);
  }
  
  public com.sbs.service.DebetResult saveDebet(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Debet debet) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveDebet(bankInn, p1T1, debet);
  }
  
  public com.sbs.service.CreditResult getCredit(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p13T25) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCredit(bankInn, p1T1, p13T25);
  }
  
  public com.sbs.service.CreditsResult getCredits(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCredits(bankInn, p1T1);
  }
  
  public com.sbs.service.CreditResult saveCredit(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Credit credit) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveCredit(bankInn, p1T1, credit);
  }
  
  public com.sbs.service.PenaltyResult getPenalty(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p7T26) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPenalty(bankInn, p1T1, p7T26);
  }
  
  public com.sbs.service.PenaltiesResult getPenalties(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPenalties(bankInn, p1T1);
  }
  
  public com.sbs.service.PenaltyResult savePenalty(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Penalty penalty) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePenalty(bankInn, p1T1, penalty);
  }
  
  public com.sbs.service.CommissionResult getCommission(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p6T27) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCommission(bankInn, p1T1, p6T27);
  }
  
  public com.sbs.service.DebetInfoResult getDebetInfo(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p7T31) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getDebetInfo(bankInn, p1T1, p7T31);
  }
  
  public com.sbs.service.DebetsInfoResult getDebetsInfo(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getDebetsInfo(bankInn, p1T1);
  }
  
  public com.sbs.service.DebetInfoResult saveDebetInfo(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.DebetInfo debetInfo) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveDebetInfo(bankInn, p1T1, debetInfo);
  }
  
  public com.sbs.service.PoliciesResult getPolicies(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPolicies(bankInn, p1T1);
  }
  
  public com.sbs.service.PolicyResult savePolicy(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Policy policy) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePolicy(bankInn, p1T1, policy);
  }
  
  public com.sbs.service.FundResult getFund(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p2T35) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getFund(bankInn, p1T1, p2T35);
  }
  
  public com.sbs.service.FundsResult getFunds(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getFunds(bankInn, p1T1);
  }
  
  public com.sbs.service.FundResult saveFund(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Fund fund) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveFund(bankInn, p1T1, fund);
  }
  
  public com.sbs.service.RefundExpResult getRefundExp(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p13T36) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getRefundExp(bankInn, p1T1, p13T36);
  }
  
  public com.sbs.service.RefundsExpResult getRefundsExp(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getRefundsExp(bankInn, p1T1);
  }
  
  public com.sbs.service.RefundExpResult saveRefundExp(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.RefundExp refundExp) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveRefundExp(bankInn, p1T1, refundExp);
  }
  
  public com.sbs.service.PaymentRefResult getPaymentRef(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p2T37) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPaymentRef(bankInn, p1T1, p2T37);
  }
  
  public com.sbs.service.TaxResult getTax(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p11T39) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getTax(bankInn, p1T1, p11T39);
  }
  
  public com.sbs.service.TaxesResult getTaxes(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getTaxes(bankInn, p1T1);
  }
  
  public com.sbs.service.TaxResult saveTax(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Tax tax) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveTax(bankInn, p1T1, tax);
  }
  
  public com.sbs.service.MoveToExResult getMoveToEx(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p33T40) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMoveToEx(bankInn, p1T1, p33T40);
  }
  
  public com.sbs.service.MovesToExResult getMovesToEx(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMovesToEx(bankInn, p1T1);
  }
  
  public com.sbs.service.MoveToExResult saveMoveToEx(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.MoveToEx moveToEx) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveMoveToEx(bankInn, p1T1, moveToEx);
  }
  
  public com.sbs.service.SaleResult getSale(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p26T43) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getSale(bankInn, p1T1, p26T43);
  }
  
  public com.sbs.service.SalesResult getSales(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getSales(bankInn, p1T1);
  }
  
  public com.sbs.service.SaleResult saveSale(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Sale sale) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveSale(bankInn, p1T1, sale);
  }
  
  public com.sbs.service.PaymentResult getPayment(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p22T44) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPayment(bankInn, p1T1, p22T44);
  }
  
  public com.sbs.service.PaymentsResult getPayments(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getPayments(bankInn, p1T1);
  }
  
  public com.sbs.service.PaymentResult savePayment(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Payment payment) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.savePayment(bankInn, p1T1, payment);
  }
  
  public com.sbs.service.RefundImpResult getRefundImp(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p12T45) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getRefundImp(bankInn, p1T1, p12T45);
  }
  
  public com.sbs.service.RefundsImpResult getRefundsImp(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getRefundsImp(bankInn, p1T1);
  }
  
  public com.sbs.service.RefundImpResult saveRefundImp(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.RefundImp refundImp) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveRefundImp(bankInn, p1T1, refundImp);
  }
  
  public com.sbs.service.MoveToImResult getMoveToIm(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p36T47) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMoveToIm(bankInn, p1T1, p36T47);
  }
  
  public com.sbs.service.MovesToImResult getMovesToIm(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMovesToIm(bankInn, p1T1);
  }
  
  public com.sbs.service.MoveToImResult saveMoveToIm(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.MoveToIm moveToIm) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveMoveToIm(bankInn, p1T1, moveToIm);
  }
  
  public com.sbs.service.LeaseResult getLease(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p5T50) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getLease(bankInn, p1T1, p5T50);
  }
  
  public com.sbs.service.LeasesResult getLeases(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getLeases(bankInn, p1T1);
  }
  
  public com.sbs.service.LeaseResult saveLease(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Lease lease) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveLease(bankInn, p1T1, lease);
  }
  
  public com.sbs.service.MoveFromImResult getMoveFromIm(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p29T53) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMoveFromIm(bankInn, p1T1, p29T53);
  }
  
  public com.sbs.service.MoveFromExResult getMoveFromEx(java.lang.String bankInn, java.lang.String p1T1, java.lang.Integer p23T52) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getMoveFromEx(bankInn, p1T1, p23T52);
  }
  
  public com.sbs.service.Result saveConfirm(java.lang.String bankInn, java.lang.String contractIdn, java.lang.String docType, java.lang.String docNum, java.lang.String chDocNum, java.lang.Short confirm, java.lang.String reason, java.lang.String responsibleName) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveConfirm(bankInn, contractIdn, docType, docNum, chDocNum, confirm, reason, responsibleName);
  }
  
  public com.sbs.service.DicVersionResult dicVersions(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicVersions(bankInn);
  }
  
  public com.sbs.service.RecordResult dicRefer(java.lang.String bankInn, java.lang.String name) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicRefer(bankInn, name);
  }
  
  public com.sbs.service.Record2Result dicBasis(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicBasis(bankInn);
  }
  
  public com.sbs.service.Record2Result dicCarryType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicCarryType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicConditions(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicConditions(bankInn);
  }
  
  public com.sbs.service.Record3Result dicCountry(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicCountry(bankInn);
  }
  
  public com.sbs.service.Record3Result dicBank(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicBank(bankInn);
  }
  
  public com.sbs.service.Record2Result dicDebetModes(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicDebetModes(bankInn);
  }
  
  public com.sbs.service.Record4Result dicErrors(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicErrors(bankInn);
  }
  
  public com.sbs.service.Record4Result dicGnk(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicGnk(bankInn);
  }
  
  public com.sbs.service.Record3Result dicYeisDocs(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicYeisDocs(bankInn);
  }
  
  public com.sbs.service.Record2Result dicExportType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicExportType(bankInn);
  }
  
  public com.sbs.service.Record5Result dicIncoterms(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicIncoterms(bankInn);
  }
  
  public com.sbs.service.Record3Result dicPost(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicPost(bankInn);
  }
  
  public com.sbs.service.Record2Result dicSaleReason(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicSaleReason(bankInn);
  }
  
  public com.sbs.service.Record2Result dicSaleSource(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicSaleSource(bankInn);
  }
  
  public com.sbs.service.Record2Result dicSaleType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicSaleType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicReasons(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicReasons(bankInn);
  }
  
  public com.sbs.service.Record3Result dicRegions(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicRegions(bankInn);
  }
  
  public com.sbs.service.Record2Result dicFundSource(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicFundSource(bankInn);
  }
  
  public com.sbs.service.Record2Result dicFundType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicFundType(bankInn);
  }
  
  public com.sbs.service.Record2Result dicFundDest(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicFundDest(bankInn);
  }
  
  public com.sbs.service.Record3Result dicTnved(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicTnved(bankInn);
  }
  
  public com.sbs.service.Record2Result dicGoodsType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicGoodsType(bankInn);
  }
  
  public com.sbs.service.Record5Result dicUnit(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicUnit(bankInn);
  }
  
  public com.sbs.service.Record3Result dicService(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicService(bankInn);
  }
  
  public com.sbs.service.Record2Result dicLoanType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicLoanType(bankInn);
  }
  
  public com.sbs.service.Record3Result dicDealType(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicDealType(bankInn);
  }
  
  public com.sbs.service.Record3Result dicProc(java.lang.String bankInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.dicProc(bankInn);
  }
  
  public com.sbs.service.ContractStatusResult getContractStatus(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getContractStatus(bankInn, p1T1);
  }
  
  public com.sbs.service.ContractsResult getContractsByInn(java.lang.String bankInn, java.lang.String clientInn) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getContractsByInn(bankInn, clientInn);
  }
  
  public com.sbs.service.GarantSumChangeResult saveGarantSumChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p3T18, com.sbs.service.GarantSumChange garantSumChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveGarantSumChange(bankInn, p1T1, p3T18, garantSumChange);
  }
  
  public com.sbs.service.GarantTimeChangeResult saveGarantTimeChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p3T18, com.sbs.service.GarantTimeChange garantTimeChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveGarantTimeChange(bankInn, p1T1, p3T18, garantTimeChange);
  }
  
  public com.sbs.service.AccreditivesResult getAccreditives(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getAccreditives(bankInn, p1T1);
  }
  
  public com.sbs.service.AccreditivResult saveAccreditiv(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Accreditiv accreditiv) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveAccreditiv(bankInn, p1T1, accreditiv);
  }
  
  public com.sbs.service.AccreditivSumChangeResult saveAccreditivSumChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p2T21, com.sbs.service.AccreditivSumChange accreditivSumChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveAccreditivSumChange(bankInn, p1T1, p2T21, accreditivSumChange);
  }
  
  public com.sbs.service.AccreditivTimeChangeResult saveAccreditivTimeChange(java.lang.String bankInn, java.lang.String p1T1, java.lang.String p2T21, com.sbs.service.AccreditivTimeChange accreditivTimeChange) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveAccreditivTimeChange(bankInn, p1T1, p2T21, accreditivTimeChange);
  }
  
  public com.sbs.service.CommissionsResult getCommissions(java.lang.String bankInn, java.lang.String p1T1) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.getCommissions(bankInn, p1T1);
  }
  
  public com.sbs.service.CommissionResult saveCommission(java.lang.String bankInn, java.lang.String p1T1, com.sbs.service.Commission commission) throws java.rmi.RemoteException{
    if (bankService == null)
      _initBankServiceProxy();
    return bankService.saveCommission(bankInn, p1T1, commission);
  }
  
  
}