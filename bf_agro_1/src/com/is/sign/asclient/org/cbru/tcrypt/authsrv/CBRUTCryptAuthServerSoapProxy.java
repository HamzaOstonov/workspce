package com.is.sign.asclient.org.cbru.tcrypt.authsrv;

public class CBRUTCryptAuthServerSoapProxy implements com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap {
  private String _endpoint = null;
  private com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap cBRUTCryptAuthServerSoap = null;
  
  public CBRUTCryptAuthServerSoapProxy() {
    _initCBRUTCryptAuthServerSoapProxy();
  }
  
  public CBRUTCryptAuthServerSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initCBRUTCryptAuthServerSoapProxy();
  }
  
  private void _initCBRUTCryptAuthServerSoapProxy() {
    try {
      cBRUTCryptAuthServerSoap = (new com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerLocator()).getCBRUTCryptAuthServerSoap();
      if (cBRUTCryptAuthServerSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cBRUTCryptAuthServerSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cBRUTCryptAuthServerSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cBRUTCryptAuthServerSoap != null)
      ((javax.xml.rpc.Stub)cBRUTCryptAuthServerSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap getCBRUTCryptAuthServerSoap() {
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap;
  }
  
  public java.lang.String getVersion() throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getVersion();
  }
  
  public byte[] getCertificateSign() throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCertificateSign();
  }
  
  public byte[] getCertificateRoot() throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCertificateRoot();
  }
  
  public java.lang.String getCertificateSignAS() throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCertificateSignAS();
  }
  
  public java.lang.String getCertificateRootAS() throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCertificateRootAS();
  }
  
  public byte[] signString(java.lang.String source, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.signString(source, docnum);
  }
  
  public byte[] signMessage(byte[] source, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.signMessage(source, docnum);
  }
  
  public java.lang.String signStringAS(java.lang.String source, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.signStringAS(source, docnum);
  }
  
  public java.lang.String signStringASCodepage(java.lang.String source, java.lang.String docnum, int codepage) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.signStringASCodepage(source, docnum, codepage);
  }
  
  public java.lang.String signMessageAS(byte[] source, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.signMessageAS(source, docnum);
  }
  
  public boolean verifyCms(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCms(cms, docnum);
  }
  
  public byte[] verifyCmsAndSign(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCmsAndSign(cms, docnum);
  }
  
  public java.lang.String[] verifyCmsEx(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCmsEx(cms, docnum);
  }
  
  public boolean verifyCmsCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCmsCAPICOM(cms, docnum);
  }
  
  public java.lang.String[] verifyCmsExCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCmsExCAPICOM(cms, docnum);
  }
  
  public boolean verifyCmsAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCmsAS(cms, docnum);
  }
  
  public java.lang.String[] verifyCmsExAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyCmsExAS(cms, docnum);
  }
  
  public java.lang.String[] verifyAndGetCmsContentAS(java.lang.String cms, java.lang.String docnum, int codepage) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyAndGetCmsContentAS(cms, docnum, codepage);
  }
  
  public java.lang.String[] verifyAndGetCmsContentASEx(java.lang.String cms, java.lang.String docnum, int codepage, int output) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.verifyAndGetCmsContentASEx(cms, docnum, codepage, output);
  }
  
  public byte[] getCmsContent(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsContent(cms, docnum);
  }
  
  public java.lang.String getCmsContentCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsContentCAPICOM(cms, docnum);
  }
  
  public java.lang.String getCmsContentAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsContentAS(cms, docnum);
  }
  
  public java.lang.String getCmsContentASCodepage(java.lang.String cms, java.lang.String docnum, int codepage) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsContentASCodepage(cms, docnum, codepage);
  }
  
  public byte[] getCmsLastCertificate(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsLastCertificate(cms, docnum);
  }
  
  public byte[] getCmsLastCertificateCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsLastCertificateCAPICOM(cms, docnum);
  }
  
  public java.lang.String getCmsLastCertificateAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsLastCertificateAS(cms, docnum);
  }
  
  public java.lang.String[] getCmsCertificatesAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.getCmsCertificatesAS(cms, docnum);
  }
  
  public java.lang.String[] CBRUGetCmsContent(java.lang.String cms, java.lang.String docnum, int codepage) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.CBRUGetCmsContent(cms, docnum, codepage);
  }
  
  public java.lang.String[] CBRUGetCmsUserIdAndTimeList(java.lang.String cms) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.CBRUGetCmsUserIdAndTimeList(cms);
  }
  
  public java.lang.String[] CBRUGetCmsUserIdAndDocList(java.lang.String cms) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.CBRUGetCmsUserIdAndDocList(cms);
  }
  
  public java.lang.String[] CBRUGetCmsUserIdList(java.lang.String cms) throws java.rmi.RemoteException{
    if (cBRUTCryptAuthServerSoap == null)
      _initCBRUTCryptAuthServerSoapProxy();
    return cBRUTCryptAuthServerSoap.CBRUGetCmsUserIdList(cms);
  }
  
  
}