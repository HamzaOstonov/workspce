package client.NCI.com.ipakyulibank.client_p;

public class Customer_ReqestOutProxy implements client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut {
	private String _endpoint = null;
	private String _username = null;
	private String _password = null;

	private client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut customer_ReqestOut = null;

	public Customer_ReqestOutProxy() {
		_initCustomer_ReqestOutProxy();
	}

	public Customer_ReqestOutProxy(String endpoint, String username, String password) {
		_endpoint = endpoint;
		_username = username;
		_password = password;
		_initCustomer_ReqestOutProxy();
	}

	private void _initCustomer_ReqestOutProxy() {
		try {
			customer_ReqestOut = (new client.NCI.com.ipakyulibank.client_p.Customer_ReqestOutServiceLocator())
					.getHTTPS_Port();
			if (customer_ReqestOut != null) {
				if (_endpoint != null) {
					((javax.xml.rpc.Stub) customer_ReqestOut)._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);
					((javax.xml.rpc.Stub) customer_ReqestOut)._setProperty("javax.xml.rpc.security.auth.username",
							_username);
					((javax.xml.rpc.Stub) customer_ReqestOut)._setProperty("javax.xml.rpc.security.auth.password",
							_password);
				} else
					_endpoint = (String) ((javax.xml.rpc.Stub) customer_ReqestOut)
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
		if (customer_ReqestOut != null)
			((javax.xml.rpc.Stub) customer_ReqestOut)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut getCustomer_ReqestOut() {
		if (customer_ReqestOut == null)
			_initCustomer_ReqestOutProxy();
		return customer_ReqestOut;
	}

	public client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce customer_Reqest(
			client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex businessPartnerChangeReqest)
			throws java.rmi.RemoteException {
		if (customer_ReqestOut == null)
			_initCustomer_ReqestOutProxy();
		return customer_ReqestOut.customer_Reqest(businessPartnerChangeReqest);
	}

	public client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex getDetails(
			client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest businessPartnerContentReqest)
			throws java.rmi.RemoteException {
		if (customer_ReqestOut == null)
			_initCustomer_ReqestOutProxy();
		return customer_ReqestOut.getDetails(businessPartnerContentReqest);
	}

	public client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce search(
			client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest[] businessPartnerSearchReqest)
			throws java.rmi.RemoteException {
		if (customer_ReqestOut == null)
			_initCustomer_ReqestOutProxy();
		return customer_ReqestOut.search(businessPartnerSearchReqest);
	}

}