package content_server.NCI.com.ipakyulibank;

public class ContentServer_ReqestOutProxy implements content_server.NCI.com.ipakyulibank.ContentServer_ReqestOut {
    private String _endpoint = null;
    private content_server.NCI.com.ipakyulibank.ContentServer_ReqestOut contentServer_ReqestOut = null;
    private String _password;
    private String _username;

    public ContentServer_ReqestOutProxy() {
        _initContentServer_ReqestOutProxy();
    }

    public ContentServer_ReqestOutProxy(String endpoint, String username, String password) {
        _endpoint = endpoint;
        _username = username;
        _password = password;
        _initContentServer_ReqestOutProxy();
    }

    private void _initContentServer_ReqestOutProxy() {
        try {
            contentServer_ReqestOut = (new content_server.NCI.com.ipakyulibank.ContentServer_ReqestOutServiceLocator())
                    .getHTTPS_Port();
            if (contentServer_ReqestOut != null) {
                if (_endpoint != null) {
                    ((javax.xml.rpc.Stub) contentServer_ReqestOut)
                            ._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
                    ((javax.xml.rpc.Stub) contentServer_ReqestOut)._setProperty("javax.xml.rpc.security.auth.username",
                            _username);
                    ((javax.xml.rpc.Stub) contentServer_ReqestOut)._setProperty("javax.xml.rpc.security.auth.password",
                            _password);
                } else
                    _endpoint = (String) ((javax.xml.rpc.Stub) contentServer_ReqestOut)
                            ._getProperty("javax.xml.rpc.service.endpoint.address");
            }

        } catch (javax.xml.rpc.ServiceException serviceException) {
        }
    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (contentServer_ReqestOut != null)
            ((javax.xml.rpc.Stub) contentServer_ReqestOut)._setProperty("javax.xml.rpc.service.endpoint.address",
                    _endpoint);

    }

    public content_server.NCI.com.ipakyulibank.ContentServer_ReqestOut getContentServer_ReqestOut() {
        if (contentServer_ReqestOut == null)
            _initContentServer_ReqestOutProxy();
        return contentServer_ReqestOut;
    }

    public content_server.NCI.com.ipakyulibank.ContentServerResponce contentServer_ReqestOut(
            content_server.NCI.com.ipakyulibank.ContentServerReqest contentServerReqest)
            throws java.rmi.RemoteException {
        if (contentServer_ReqestOut == null)
            _initContentServer_ReqestOutProxy();
        return contentServer_ReqestOut.contentServer_ReqestOut(contentServerReqest);
    }

}