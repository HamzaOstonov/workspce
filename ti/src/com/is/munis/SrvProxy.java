package com.is.munis;

public class SrvProxy implements com.is.munis.Srv {
  private String _endpoint = null;
  private com.is.munis.Srv srv = null;
  
  public SrvProxy() {
    _initSrvProxy();
  }
  
  public SrvProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrvProxy();
  }
  
  private void _initSrvProxy() {
    try {
      srv = (new com.is.munis.SrvServiceLocator()).getSrvPort();
      if (srv != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srv)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srv)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srv != null)
      ((javax.xml.rpc.Stub)srv)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.is.munis.Srv getSrv() {
    if (srv == null)
      _initSrvProxy();
    return srv;
  }
  
  public com.is.munis.Res[] providerList() throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.providerList();
  }
  
  public com.is.munis.ListTrResp listTr(com.is.munis.Credentials arg0, java.util.Calendar arg1, java.util.Calendar arg2) throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.listTr(arg0, arg1, arg2);
  }
  
  public com.is.munis.PayResp check(com.is.munis.Credentials arg0, com.is.munis.Payment arg1) throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.check(arg0, arg1);
  }
  
  public com.is.munis.CheckPerResp checkPer(com.is.munis.Credentials arg0, java.util.Calendar arg1, java.util.Calendar arg2) throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.checkPer(arg0, arg1, arg2);
  }
  
  public com.is.munis.Res[] respCodes() throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.respCodes();
  }
  
  public com.is.munis.PayResp pay(com.is.munis.Credentials arg0, com.is.munis.Payment arg1, com.is.munis.AddInfo arg2) throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.pay(arg0, arg1, arg2);
  }
  
  public com.is.munis.CheckTrResp checkTr(com.is.munis.Credentials arg0, long arg1) throws java.rmi.RemoteException{
    if (srv == null)
      _initSrvProxy();
    return srv.checkTr(arg0, arg1);
  }
  
  
}