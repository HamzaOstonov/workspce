/**
 * Srv.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.providers;

public interface Srv extends javax.xml.rpc.Service {
    public java.lang.String getSrvHttpSoap11EndpointAddress();

    public com.is.providers.SrvPortType getSrvHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException;

    public com.is.providers.SrvPortType getSrvHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
