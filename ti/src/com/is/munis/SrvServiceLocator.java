/**
 * SrvServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public class SrvServiceLocator extends org.apache.axis.client.Service implements com.is.munis.SrvService {

    public SrvServiceLocator() {
    }


    public SrvServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvPort
    private java.lang.String SrvPort_address = "http://localhost:8080/mn/services/SrvPort";

    public java.lang.String getSrvPortAddress() {
        return SrvPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvPortWSDDServiceName = "SrvPort";

    public java.lang.String getSrvPortWSDDServiceName() {
        return SrvPortWSDDServiceName;
    }

    public void setSrvPortWSDDServiceName(java.lang.String name) {
        SrvPortWSDDServiceName = name;
    }

    public com.is.munis.Srv getSrvPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvPort(endpoint);
    }

    public com.is.munis.Srv getSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.is.munis.SrvServiceSoapBindingStub _stub = new com.is.munis.SrvServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvPortEndpointAddress(java.lang.String address) {
        SrvPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.is.munis.Srv.class.isAssignableFrom(serviceEndpointInterface)) {
                com.is.munis.SrvServiceSoapBindingStub _stub = new com.is.munis.SrvServiceSoapBindingStub(new java.net.URL(SrvPort_address), this);
                _stub.setPortName(getSrvPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SrvPort".equals(inputPortName)) {
            return getSrvPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://munis.is.com/", "SrvService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://munis.is.com/", "SrvPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvPort".equals(portName)) {
            setSrvPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
