/**
 * ContentServerFileDelete_OutServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package content_server.NCI.com.ipakyulibank;

public class ContentServerFileDelete_OutServiceLocator extends org.apache.axis.client.Service implements content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutService {

    public ContentServerFileDelete_OutServiceLocator() {
    }


    public ContentServerFileDelete_OutServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ContentServerFileDelete_OutServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for HTTPS_Port
    private java.lang.String HTTPS_Port_address = "https://podev02.ipakyulibank.uz:51001/XISOAPAdapter/MessageServlet?senderParty=&senderService=NCI_D&receiverParty=&receiverService=&interface=ContentServerFileDelete_Out&interfaceNamespace=urn%3Aipakyulibank.com%3ANCI%3Acontent_server";

    public java.lang.String getHTTPS_PortAddress() {
        return HTTPS_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HTTPS_PortWSDDServiceName = "HTTPS_Port";

    public java.lang.String getHTTPS_PortWSDDServiceName() {
        return HTTPS_PortWSDDServiceName;
    }

    public void setHTTPS_PortWSDDServiceName(java.lang.String name) {
        HTTPS_PortWSDDServiceName = name;
    }

    public content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out getHTTPS_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTTPS_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTTPS_Port(endpoint);
    }

    public content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out getHTTPS_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub _stub = new content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub(portAddress, this);
            _stub.setPortName(getHTTPS_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTTPS_PortEndpointAddress(java.lang.String address) {
        HTTPS_Port_address = address;
    }


    // Use to get a proxy class for HTTP_Port
    private java.lang.String HTTP_Port_address = "http://podev02.ipakyulibank.uz:51000/XISOAPAdapter/MessageServlet?senderParty=&senderService=NCI_D&receiverParty=&receiverService=&interface=ContentServerFileDelete_Out&interfaceNamespace=urn%3Aipakyulibank.com%3ANCI%3Acontent_server";

    public java.lang.String getHTTP_PortAddress() {
        return HTTP_Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String HTTP_PortWSDDServiceName = "HTTP_Port";

    public java.lang.String getHTTP_PortWSDDServiceName() {
        return HTTP_PortWSDDServiceName;
    }

    public void setHTTP_PortWSDDServiceName(java.lang.String name) {
        HTTP_PortWSDDServiceName = name;
    }

    public content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out getHTTP_Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(HTTP_Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getHTTP_Port(endpoint);
    }

    public content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out getHTTP_Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub _stub = new content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub(portAddress, this);
            _stub.setPortName(getHTTP_PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setHTTP_PortEndpointAddress(java.lang.String address) {
        HTTP_Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out.class.isAssignableFrom(serviceEndpointInterface)) {
                content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub _stub = new content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub(new java.net.URL(HTTPS_Port_address), this);
                _stub.setPortName(getHTTPS_PortWSDDServiceName());
                return _stub;
            }
            if (content_server.NCI.com.ipakyulibank.ContentServerFileDelete_Out.class.isAssignableFrom(serviceEndpointInterface)) {
                content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub _stub = new content_server.NCI.com.ipakyulibank.ContentServerFileDelete_OutBindingStub(new java.net.URL(HTTP_Port_address), this);
                _stub.setPortName(getHTTP_PortWSDDServiceName());
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
        if ("HTTPS_Port".equals(inputPortName)) {
            return getHTTPS_Port();
        }
        else if ("HTTP_Port".equals(inputPortName)) {
            return getHTTP_Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:content_server", "ContentServerFileDelete_OutService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:content_server", "HTTPS_Port"));
            ports.add(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:content_server", "HTTP_Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("HTTPS_Port".equals(portName)) {
            setHTTPS_PortEndpointAddress(address);
        }
        else 
if ("HTTP_Port".equals(portName)) {
            setHTTP_PortEndpointAddress(address);
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
