/**
 * Customer_ReqestOutBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package client.NCI.com.ipakyulibank.client_p;

public class Customer_ReqestOutBindingStub extends org.apache.axis.client.Stub implements client.NCI.com.ipakyulibank.client_p.Customer_ReqestOut {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[3];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Customer_Reqest");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ipakyulibank.com:CRM:client", "BusinessPartnerChangeReqest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessParnerComplex"), client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerResponce"));
        oper.setReturnClass(client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerChangeResponce"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContentReqest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContentReqest"), client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessParnerComplex"));
        oper.setReturnClass(client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContentResponce"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Search");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ipakyulibank.com:CRM:client", "BusinessPartnerSearchReqest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerSearchReqest"), client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "reqest"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerSearchResponce"));
        oper.setReturnClass(client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce.class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerSearchResponce"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

    }

    public Customer_ReqestOutBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public Customer_ReqestOutBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public Customer_ReqestOutBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BPAttachments>attachment");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BPAttachmentsAttachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BusinessPartnerResponceHeader>id_client_sap");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BusinessPartnerSearchReqest>reqest");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BAPIRET2");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BAPIRET2.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BPAttachments");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BPAttachmentsAttachment[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BPAttachments>attachment");
            qName2 = new javax.xml.namespace.QName("", "attachment");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessParnerComplex");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContent");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerContent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerContentReqest");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerNCI");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerNCI.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerResponce");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerResponceHeader");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponceHeader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerSearchReqest");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", ">BusinessPartnerSearchReqest>reqest");
            qName2 = new javax.xml.namespace.QName("", "reqest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerSearchResponce");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerTab");
            cachedSerQNames.add(qName);
            cls = client.NCI.com.ipakyulibank.client_p.BusinessPartnerNCI[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:client", "BusinessPartnerNCI");
            qName2 = new javax.xml.namespace.QName("", "BusinessPartner");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce customer_Reqest(client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex businessPartnerChangeReqest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://sap.com/xi/WebService/soap1.1");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Customer_Reqest"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {businessPartnerChangeReqest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce) _resp;
            } catch (java.lang.Exception _exception) {
                return (client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce) org.apache.axis.utils.JavaUtils.convert(_resp, client.NCI.com.ipakyulibank.client_p.BusinessPartnerResponce.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex getDetails(client.NCI.com.ipakyulibank.client_p.BusinessPartnerContentReqest businessPartnerContentReqest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://sap.com/xi/WebService/soap1.1");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetDetails"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {businessPartnerContentReqest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex) _resp;
            } catch (java.lang.Exception _exception) {
                return (client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex) org.apache.axis.utils.JavaUtils.convert(_resp, client.NCI.com.ipakyulibank.client_p.BusinessParnerComplex.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce search(client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchReqestReqest[] businessPartnerSearchReqest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://sap.com/xi/WebService/soap1.1");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Search"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {businessPartnerSearchReqest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce) _resp;
            } catch (java.lang.Exception _exception) {
                return (client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce) org.apache.axis.utils.JavaUtils.convert(_resp, client.NCI.com.ipakyulibank.client_p.BusinessPartnerSearchResponce.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
