package roles.NCI.com.ipakyulibank;

public class RolesByEvent_OutProxy implements roles.NCI.com.ipakyulibank.RolesByEvent_Out {
  private String _endpoint = null;
  private String _username;
  private String _passwd;
  private roles.NCI.com.ipakyulibank.RolesByEvent_Out rolesByEvent_Out = null;
  
  public RolesByEvent_OutProxy() {
    _initRolesByEvent_OutProxy();
  }
  
  public RolesByEvent_OutProxy(String endpoint, String username, String passwd) {
    _endpoint = endpoint;
    _username = username;
    _passwd = passwd;
    _initRolesByEvent_OutProxy();
  }
  
  private void _initRolesByEvent_OutProxy() {
    try {
      rolesByEvent_Out = (new roles.NCI.com.ipakyulibank.RolesByEvent_OutServiceLocator()).getHTTPS_Port();
      if (rolesByEvent_Out != null) {
        if (_endpoint != null) {
          ((javax.xml.rpc.Stub)rolesByEvent_Out)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
          ((javax.xml.rpc.Stub) rolesByEvent_Out)._setProperty("javax.xml.rpc.security.auth.username", _username);
          ((javax.xml.rpc.Stub) rolesByEvent_Out)._setProperty("javax.xml.rpc.security.auth.password", _passwd);
        } else
          _endpoint = (String)((javax.xml.rpc.Stub)rolesByEvent_Out)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (rolesByEvent_Out != null)
      ((javax.xml.rpc.Stub)rolesByEvent_Out)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public roles.NCI.com.ipakyulibank.RolesByEvent_Out getRolesByEvent_Out() {
    if (rolesByEvent_Out == null)
      _initRolesByEvent_OutProxy();
    return rolesByEvent_Out;
  }
  
  public roles.NCI.com.ipakyulibank.RolesByEventResponceRelationshipsResponce[] rolesByEvent_Out(roles.NCI.com.ipakyulibank.RolesByEventReqest rolesByEventReqest) throws java.rmi.RemoteException{
    if (rolesByEvent_Out == null)
      _initRolesByEvent_OutProxy();
    return rolesByEvent_Out.rolesByEvent_Out(rolesByEventReqest);
  }
  
  
}