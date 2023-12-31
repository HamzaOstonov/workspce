/**
 * BankServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class BankServiceServiceLocator extends org.apache.axis.client.Service implements com.sbs.service.BankServiceService {

    public BankServiceServiceLocator() {
    }


    public BankServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BankServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BankServicePort
    private java.lang.String BankServicePort_address = "http://213.206.37.125:8084/yeisvo_bank/service";

    public java.lang.String getBankServicePortAddress() {
        return BankServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BankServicePortWSDDServiceName = "BankServicePort";

    public java.lang.String getBankServicePortWSDDServiceName() {
        return BankServicePortWSDDServiceName;
    }

    public void setBankServicePortWSDDServiceName(java.lang.String name) {
        BankServicePortWSDDServiceName = name;
    }

    public com.sbs.service.BankService getBankServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BankServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBankServicePort(endpoint);
    }

    public com.sbs.service.BankService getBankServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sbs.service.BankServicePortBindingStub _stub = new com.sbs.service.BankServicePortBindingStub(portAddress, this);
            _stub.setPortName(getBankServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBankServicePortEndpointAddress(java.lang.String address) {
        BankServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sbs.service.BankService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sbs.service.BankServicePortBindingStub _stub = new com.sbs.service.BankServicePortBindingStub(new java.net.URL(BankServicePort_address), this);
                _stub.setPortName(getBankServicePortWSDDServiceName());
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
        if ("BankServicePort".equals(inputPortName)) {
            return getBankServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.sbs.com/", "BankServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.sbs.com/", "BankServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BankServicePort".equals(portName)) {
            setBankServicePortEndpointAddress(address);
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
