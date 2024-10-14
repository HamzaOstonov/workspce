package com.is.providers;

public class SrvPortTypeProxy implements com.is.providers.SrvPortType {
  private String _endpoint = null;
  private com.is.providers.SrvPortType srvPortType = null;
  
  public SrvPortTypeProxy() {
    _initSrvPortTypeProxy();
  }
  
  public SrvPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrvPortTypeProxy();
  }
  
  private void _initSrvPortTypeProxy() {
    try {
      srvPortType = (new com.is.providers.SrvLocator()).getSrvHttpSoap11Endpoint();
      if (srvPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srvPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srvPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srvPortType != null)
      ((javax.xml.rpc.Stub)srvPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.is.providers.SrvPortType getSrvPortType() {
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType;
  }
  
  public com.is.providers.CheckPerResp checkPer(com.is.providers.Credentials cr, java.util.Date startDate, java.util.Date endDate) throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.checkPer(cr, startDate, endDate);
  }
  
  public com.is.providers.CheckTrResp checkTr(com.is.providers.Credentials cr, java.lang.Long id) throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.checkTr(cr, id);
  }
  
  public com.is.providers.Res[] providerList() throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.providerList();
  }
  
  public com.is.providers.PayResp pay(com.is.providers.Credentials cr, com.is.providers.Payment pay, com.is.providers.AddInfo addInfo) throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.pay(cr, pay, addInfo);
  }
  
  public com.is.providers.PayResp check(com.is.providers.Credentials cr, com.is.providers.Payment pay) throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.check(cr, pay);
  }
  
  public com.is.providers.Res[] respCodes() throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.respCodes();
  }
  
  public com.is.providers.ListTrResp listTr(com.is.providers.Credentials cr, java.util.Date startDate, java.util.Date endDate) throws java.rmi.RemoteException{
    if (srvPortType == null)
      _initSrvPortTypeProxy();
    return srvPortType.listTr(cr, startDate, endDate);
  }
  
  
}