/**
 * CBRUTCryptAuthServerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.sign.asclient.org.cbru.tcrypt.authsrv;

public class CBRUTCryptAuthServerLocator extends org.apache.axis.client.Service implements com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServer {

/**
 * Central Bank of Uzbekistan Authentification and Cryptography Server
 */

    public CBRUTCryptAuthServerLocator() {
    }


    public CBRUTCryptAuthServerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CBRUTCryptAuthServerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CBRUTCryptAuthServerSoap
    private java.lang.String CBRUTCryptAuthServerSoap_address = "http://194.58.80.1/tcas/CBRUTcryptAS.asmx";

    public java.lang.String getCBRUTCryptAuthServerSoapAddress() {
        return CBRUTCryptAuthServerSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CBRUTCryptAuthServerSoapWSDDServiceName = "CBRUTCryptAuthServerSoap";

    public java.lang.String getCBRUTCryptAuthServerSoapWSDDServiceName() {
        return CBRUTCryptAuthServerSoapWSDDServiceName;
    }

    public void setCBRUTCryptAuthServerSoapWSDDServiceName(java.lang.String name) {
        CBRUTCryptAuthServerSoapWSDDServiceName = name;
    }

    public com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap getCBRUTCryptAuthServerSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CBRUTCryptAuthServerSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCBRUTCryptAuthServerSoap(endpoint);
    }

    public com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap getCBRUTCryptAuthServerSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapStub _stub = new com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapStub(portAddress, this);
            _stub.setPortName(getCBRUTCryptAuthServerSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCBRUTCryptAuthServerSoapEndpointAddress(java.lang.String address) {
        CBRUTCryptAuthServerSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapStub _stub = new com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoapStub(new java.net.URL(CBRUTCryptAuthServerSoap_address), this);
                _stub.setPortName(getCBRUTCryptAuthServerSoapWSDDServiceName());
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
        if ("CBRUTCryptAuthServerSoap".equals(inputPortName)) {
            return getCBRUTCryptAuthServerSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://cbru.org/tcrypt/authsrv", "CBRUTCryptAuthServer");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://cbru.org/tcrypt/authsrv", "CBRUTCryptAuthServerSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CBRUTCryptAuthServerSoap".equals(portName)) {
            setCBRUTCryptAuthServerSoapEndpointAddress(address);
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
