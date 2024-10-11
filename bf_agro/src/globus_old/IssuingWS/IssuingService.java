/**
 * IssuingService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.IssuingWS;

public interface IssuingService extends javax.xml.rpc.Service {
    public java.lang.String getIssuingAddress();

    public globus.IssuingWS.IssuingPort getIssuing() throws javax.xml.rpc.ServiceException;

    public globus.IssuingWS.IssuingPort getIssuing(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
