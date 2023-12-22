package content_server.NCI.com.ipakyulibank;

public class ContentServerFileDelete_OutProxy implements content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out {
  private String _endpoint = null;
  private content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out contentServerFileDelete_Out = null;
  private String _password;
  private String _username;

  
  public ContentServerFileDelete_OutProxy() {
    _initContentServerFileDelete_OutProxy();
  }
  
  public ContentServerFileDelete_OutProxy(String endpoint) {
    _endpoint = endpoint;
    _initContentServerFileDelete_OutProxy();
  }
  
  public ContentServerFileDelete_OutProxy(String endpoint, String username, String password) {
      _endpoint = endpoint;
      _username = username;
      _password = password;
      _initContentServerFileDelete_OutProxy();
  }

  private void _initContentServerFileDelete_OutProxy() {
    try {
      contentServerFileDelete_Out = (new content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutServiceLocator()).getHTTPS_Port();
      if (contentServerFileDelete_Out != null) {
        if (_endpoint != null) {
          ((javax.xml.rpc.Stub)contentServerFileDelete_Out)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

          ((javax.xml.rpc.Stub) contentServerFileDelete_Out)._setProperty("javax.xml.rpc.security.auth.username",
                  _username);
          ((javax.xml.rpc.Stub) contentServerFileDelete_Out)._setProperty("javax.xml.rpc.security.auth.password",
                  _password);
        }
        else
          _endpoint = (String)((javax.xml.rpc.Stub)contentServerFileDelete_Out)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (contentServerFileDelete_Out != null)
      ((javax.xml.rpc.Stub)contentServerFileDelete_Out)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out getContentServerFileDelete_Out() {
    if (contentServerFileDelete_Out == null)
      _initContentServerFileDelete_OutProxy();
    return contentServerFileDelete_Out;
  }
  
  public content_server.NCI.com.ipakyulibank.ContentServerFileDeleteResponse contentServerFileDelete_Out(content_server.NCI.com.ipakyulibank.ContentServerFileDeleteRequest contentServerFileDeleteRequest) throws java.rmi.RemoteException{
    if (contentServerFileDelete_Out == null)
      _initContentServerFileDelete_OutProxy();
    return contentServerFileDelete_Out.contentServerFileDelete_Out(contentServerFileDeleteRequest);
  }
  
  
}