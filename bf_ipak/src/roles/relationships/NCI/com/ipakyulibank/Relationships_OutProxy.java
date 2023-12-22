package roles.relationships.NCI.com.ipakyulibank;

public class Relationships_OutProxy implements relationships.NCI.com.ipakyulibank.Relationships_Out {
  private String _endpoint = null;
  private String _username;
  private String _passwd;
  private relationships.NCI.com.ipakyulibank.Relationships_Out relationships_Out = null;
  
  public Relationships_OutProxy() {
    _initRelationships_OutProxy();
  }
  
  public Relationships_OutProxy(String endpoint, String username, String passwd) {
    _endpoint = endpoint;
    _username = username;
    _passwd = passwd;
    _initRelationships_OutProxy();
  }
  
  private void _initRelationships_OutProxy() {
    try {
      relationships_Out = (new relationships.NCI.com.ipakyulibank.Relationships_OutServiceLocator()).getHTTPS_Port();
      if (relationships_Out != null) {
        if (_endpoint != null) {
          ((javax.xml.rpc.Stub)relationships_Out)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
          ((javax.xml.rpc.Stub) relationships_Out)._setProperty("javax.xml.rpc.security.auth.username", _username);
          ((javax.xml.rpc.Stub) relationships_Out)._setProperty("javax.xml.rpc.security.auth.password", _passwd);
        } else
          _endpoint = (String)((javax.xml.rpc.Stub)relationships_Out)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (relationships_Out != null)
      ((javax.xml.rpc.Stub)relationships_Out)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public relationships.NCI.com.ipakyulibank.Relationships_Out getRelationships_Out() {
    if (relationships_Out == null)
      _initRelationships_OutProxy();
    return relationships_Out;
  }
  
  public relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[] relationships_Out(relationships.NCI.com.ipakyulibank.RelationshipsReqest relationshipsReqest) throws java.rmi.RemoteException{
    if (relationships_Out == null)
      _initRelationships_OutProxy();
    return relationships_Out.relationships_Out(relationshipsReqest);
  }
  
  public relationships.NCI.com.ipakyulibank.BPRelResp[] getDetails(relationships.NCI.com.ipakyulibank.BPRelReqest[] relationshipsGetDetailsReqest) throws java.rmi.RemoteException{
    if (relationships_Out == null)
      _initRelationships_OutProxy();
    return relationships_Out.getDetails(relationshipsGetDetailsReqest);
  }
  
  
}