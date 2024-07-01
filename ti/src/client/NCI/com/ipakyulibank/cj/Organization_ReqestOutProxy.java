package client.NCI.com.ipakyulibank.cj;

public class Organization_ReqestOutProxy implements client.NCI.com.ipakyulibank.cj.Organization_ReqestOut {
    private String _endpoint = null;
    private String _username = null;
    private String _password = null;

    private client.NCI.com.ipakyulibank.cj.Organization_ReqestOut organization_ReqestOut = null;

    public Organization_ReqestOutProxy() {
        _initOrganization_ReqestOutProxy();
    }

    public Organization_ReqestOutProxy(String endpoint, String username, String password) {
        _endpoint = endpoint;
        _username = username;
        _password = password;
        _initOrganization_ReqestOutProxy();
    }

    private void _initOrganization_ReqestOutProxy() {
        try {
            organization_ReqestOut = (new client.NCI.com.ipakyulibank.cj.Organization_ReqestOutServiceLocator()).getHTTPS_Port();
            if (organization_ReqestOut != null) {
                if (_endpoint != null) {
                    ((javax.xml.rpc.Stub) organization_ReqestOut)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
                    ((javax.xml.rpc.Stub) organization_ReqestOut)._setProperty("javax.xml.rpc.security.auth.username",
                            _username);
                    ((javax.xml.rpc.Stub) organization_ReqestOut)._setProperty("javax.xml.rpc.security.auth.password",
                            _password);
                }
                else
                    _endpoint = (String) ((javax.xml.rpc.Stub) organization_ReqestOut)._getProperty("javax.xml.rpc.service.endpoint.address");
            }

        } catch (javax.xml.rpc.ServiceException serviceException) {
        }
    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (organization_ReqestOut != null)
            ((javax.xml.rpc.Stub) organization_ReqestOut)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

    }

    public client.NCI.com.ipakyulibank.cj.Organization_ReqestOut getOrganization_ReqestOut() {
        if (organization_ReqestOut == null)
            _initOrganization_ReqestOutProxy();
        return organization_ReqestOut;
    }

    public client.NCI.com.ipakyulibank.cj.BusinessPartnerResponce organization_Reqest(client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex businessOrganizationChangeReqest) throws java.rmi.RemoteException {
        if (organization_ReqestOut == null)
            _initOrganization_ReqestOutProxy();
        return organization_ReqestOut.organization_Reqest(businessOrganizationChangeReqest);
    }

    public client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex getDetails(client.NCI.com.ipakyulibank.cj.BusinessPartnerContentReqest businessPartnerContentReqest) throws java.rmi.RemoteException {
        if (organization_ReqestOut == null)
            _initOrganization_ReqestOutProxy();
        return organization_ReqestOut.getDetails(businessPartnerContentReqest);
    }

    public search.NCI.com.ipakyulibank.BPSearchResponceOrganization[] search(search.NCI.com.ipakyulibank.BPSearchReqest BPSearchReqest) throws java.rmi.RemoteException {
        if (organization_ReqestOut == null)
            _initOrganization_ReqestOutProxy();
        return organization_ReqestOut.search(BPSearchReqest);
    }


}