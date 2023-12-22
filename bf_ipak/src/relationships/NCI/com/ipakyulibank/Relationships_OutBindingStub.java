/**
 * Relationships_OutBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package relationships.NCI.com.ipakyulibank;

public class Relationships_OutBindingStub extends org.apache.axis.client.Stub implements relationships.NCI.com.ipakyulibank.Relationships_Out {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[2];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Relationships_Out");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ipakyulibank.com:CRM:relationships", "RelationshipsReqest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "RelationshipsReqest"), relationships.NCI.com.ipakyulibank.RelationshipsReqest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "RelationshipsResponce"));
        oper.setReturnClass(relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:ipakyulibank.com:CRM:relationships", "RelationshipsResponce"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "RelationshipsResponce"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetDetails");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:ipakyulibank.com:CRM:relationships", "RelationshipsGetDetailsReqest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelReqestTab"), relationships.NCI.com.ipakyulibank.BPRelReqest[].class, false, false);
        param.setItemQName(new javax.xml.namespace.QName("", "BPRelReqest"));
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelRespTab"));
        oper.setReturnClass(relationships.NCI.com.ipakyulibank.BPRelResp[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("urn:ipakyulibank.com:CRM:relationships", "RelationshipsGetDetailsResp"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "BPRelResp"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

    }

    public Relationships_OutBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public Relationships_OutBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public Relationships_OutBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", ">BPShareholder>cmpy_part_amo");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", ">BPShareholder>cmpy_part_per");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", ">BPShareholder>shares_numb");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", ">RelationshipsResponce>RelationshipsResponce");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContent");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPContent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContpers");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPContpers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRel");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPRel.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelReqest");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPRelReqest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelReqestTab");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPRelReqest[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelReqest");
            qName2 = new javax.xml.namespace.QName("", "BPRelReqest");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelResp");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPRelResp.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelRespTab");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPRelResp[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelResp");
            qName2 = new javax.xml.namespace.QName("", "BPRelResp");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelWithDate");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPRelWithDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPShareholder");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.BPShareholder.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "IdentNums");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.IdentNums.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "RelationshipsReqest");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.RelationshipsReqest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "RelationshipsResponce");
            cachedSerQNames.add(qName);
            cls = relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", ">RelationshipsResponce>RelationshipsResponce");
            qName2 = new javax.xml.namespace.QName("", "RelationshipsResponce");
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

    public relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[] relationships_Out(relationships.NCI.com.ipakyulibank.RelationshipsReqest relationshipsReqest) throws java.rmi.RemoteException {
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
        _call.setOperationName(new javax.xml.namespace.QName("", "Relationships_Out"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {relationshipsReqest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[]) org.apache.axis.utils.JavaUtils.convert(_resp, relationships.NCI.com.ipakyulibank.RelationshipsResponceRelationshipsResponce[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public relationships.NCI.com.ipakyulibank.BPRelResp[] getDetails(relationships.NCI.com.ipakyulibank.BPRelReqest[] relationshipsGetDetailsReqest) throws java.rmi.RemoteException {
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
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {relationshipsGetDetailsReqest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (relationships.NCI.com.ipakyulibank.BPRelResp[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (relationships.NCI.com.ipakyulibank.BPRelResp[]) org.apache.axis.utils.JavaUtils.convert(_resp, relationships.NCI.com.ipakyulibank.BPRelResp[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
