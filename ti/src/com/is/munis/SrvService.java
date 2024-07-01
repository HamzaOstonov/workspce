/**
 * SrvService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public interface SrvService extends javax.xml.rpc.Service {
    public java.lang.String getSrvPortAddress();

    public com.is.munis.Srv getSrvPort() throws javax.xml.rpc.ServiceException;

    public com.is.munis.Srv getSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
