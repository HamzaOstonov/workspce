/**
 * Organization_ReqestOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.cj;

public interface Organization_ReqestOut extends java.rmi.Remote {
    public client.NCI.com.ipakyulibank.cj.BusinessPartnerResponce organization_Reqest(client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex businessOrganizationChangeReqest) throws java.rmi.RemoteException;
    public client.NCI.com.ipakyulibank.cj.BusinessOrganizationComplex getDetails(client.NCI.com.ipakyulibank.cj.BusinessPartnerContentReqest businessPartnerContentReqest) throws java.rmi.RemoteException;
    public search.NCI.com.ipakyulibank.BPSearchResponceOrganization[] search(search.NCI.com.ipakyulibank.BPSearchReqest BPSearchReqest) throws java.rmi.RemoteException;
}
