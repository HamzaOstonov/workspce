/**
 * Customer_ReqestOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.client_p;

public interface Customer_ReqestOut extends java.rmi.Remote {
    public client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce customer_Reqest(client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex businessPartnerChangeReqest) throws java.rmi.RemoteException;
    public client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex getDetails(client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest businessPartnerContentReqest) throws java.rmi.RemoteException;
    public client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce search(client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest[] businessPartnerSearchReqest) throws java.rmi.RemoteException;
}
