/**
 * IssuingSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package capitalBank.IssuingWS;

public class IssuingSoapBindingStub extends org.apache.axis.client.Stub implements capitalBank.IssuingWS.IssuingPort {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[80];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
        _initOperationDesc7();
        _initOperationDesc8();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("newCustomer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CustomerInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Customer"), capitalBank.issuing_v_01_02_xsd.RowType_Customer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CustomListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CustomerCustomInfo"), capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("newAgreement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AgreementInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Agreement"), capitalBank.issuing_v_01_02_xsd.RowType_Agreement.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AccountsListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_AccountInfo"), capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CardsListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CardInfo"), capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addInfo4Agreement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "MainAgreementInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "KeyType_Agreement"), capitalBank.issuing_v_01_02_xsd.KeyType_Agreement.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AccountsListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_AccountInfo"), capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CardsListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CardInfo"), capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addInfo4Customer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "MainCustomerInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "KeyType_Customer"), capitalBank.issuing_v_01_02_xsd.KeyType_Customer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CustomListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CustomerCustomInfo"), capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("customDataOperation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CustomDataOperationInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CustomDataOperationMerch"), capitalBank.issuing_v_01_02_xsd.ListType_CustomDataOperationMerch.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomDataOperationMerch_Response"), capitalBank.issuing_v_01_02_xsd.RowType_CustomDataOperationMerch_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("editAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditAccount_Request"), capitalBank.issuing_v_01_02_xsd.RowType_EditAccount_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("editCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_EditCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("editCustomer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditCustomer_Request"), capitalBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("editAgreement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AgreementInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditAgreement_Request"), capitalBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listCustomers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCustomers_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ListCustomers_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listCustomerCards");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCustomerCards_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listAccountsByCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListAccountsByCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listAccounts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListAccounts_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ListAccounts_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkAuthentication");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Authentication"), capitalBank.issuing_v_01_02_xsd.RowType_Authentication.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("requestHint");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Request"), capitalBank.issuing_v_01_02_xsd.RowType_Hint_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Response"), capitalBank.issuing_v_01_02_xsd.RowType_Hint_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryTransactionHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransactionHist_Request"), capitalBank.issuing_v_01_02_xsd.RowType_TransactionHist_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryAccountBalanceByCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccBalanceQueryByCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryCardholderDataHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardholderDataHist_Request"), capitalBank.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeTransaction");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExecTransaction_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExecTransaction_Response"), capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("transferLoyaltyPoints");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferLoyaltyPoints"), capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferLoyaltyPoints_Response"), capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listExpiredCards");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExpiredCards_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ExpiredCards_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("relinkCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RelinkCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_RelinkCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeSGP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeSGP_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ChangeSGP_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("confirmHint");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Confirmation_Request"), capitalBank.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("register3VTSCustomer");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_3VTSCustomerReg_Request"), capitalBank.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addCardToStop");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AddCardToStopList_Request"), capitalBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activateCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ActivateCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ActivateCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeCardFromStop");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RemoveCardFromStop_Request"), capitalBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("dormantAccountByCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DormantAccountByCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activateAccountByCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ActivateAccountByCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeCustomerStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomerStatusChange_Request"), capitalBank.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryBillInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryBillInfo_Request"), capitalBank.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createLoyaltyAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountRequest"), capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("relinkLoyaltyAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountRelinkRequest"), capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("linkCardToLoyaltyAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LinkCardToLoyaltyAccountRequest"), capitalBank.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unlinkLoyaltyAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountUnlinkRequest"), capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVersion");
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "VersionInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.VersionInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "Version"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeSoapBatch");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Contents"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("newCustomerAndAgreement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CustomerInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Customer"), capitalBank.issuing_v_01_02_xsd.RowType_Customer.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CustomListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CustomerCustomInfo"), capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AgreementInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Agreement"), capitalBank.issuing_v_01_02_xsd.RowType_Agreement.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AccountsListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_AccountInfo"), capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "CardsListInfo"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CardInfo"), capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("relinkAgreement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AgreementReLinkRequest"), capitalBank.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setLimits");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Limit_Event"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_LimitEvent"), capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLimits");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Limit_Event"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_LimitEvent"), capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLimitsBalance");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "limit_balance_event_in"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_EventBalanceRequest"), capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "limit_balance_event_out"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_EventBalanceResponse"), capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryCardInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryCardInfo_Request"), capitalBank.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listCombiCards");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCombiCards_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ListCombiCards_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("renewCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RenewCard"), capitalBank.issuing_v_01_02_xsd.RowType_RenewCard.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RenewCard"), capitalBank.issuing_v_01_02_xsd.RowType_RenewCard.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("replaceCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ReplaceCard"), capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ReplaceCard"), capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("duplicateCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DuplicateCard"), capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DuplicateCard"), capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("orderPinEnvelope");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_OrderPinEnvelope"), capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_OrderPinEnvelope"), capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("restoreFromArchive");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RestoreFromArchive_Request"), capitalBank.issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("newInstantCards");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_NewInstantCards_Request"), capitalBank.issuing_v_01_02_xsd.RowType_NewInstantCards_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("executeSoapBatch2");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Action"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Options"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"), java.math.BigInteger.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProcessId"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"), java.math.BigInteger.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("makeAccountDormant");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_MakeAccountDormant_Request"), capitalBank.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("closeAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CloseAccount_Request"), capitalBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("embossNewCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EmbossNewCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deactivateCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DeactivateCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_DeactivateCard_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("listCardsByAccount");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCardsByAccount_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("instantToReal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_InstantToReal_Request"), capitalBank.issuing_v_01_02_xsd.RowType_InstantToReal_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[57] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("enterLoyaltyAccountTransaction");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EnterLoyaltyAccountTransaction"), capitalBank.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[58] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("redeemLoyaltyPoints");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RedeemLoyaltyPoints"), capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[59] = oper;

    }

    private static void _initOperationDesc7(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("redeemLoyaltyPoints4Fusion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RedeemLoyaltyPoints4Fusion"), capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[60] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeLoyaltyAccountStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeLoyaltyAccountStatus"), capitalBank.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[61] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("retrieveData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfoWA"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_RetrieveDataParameters"), capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfoWA"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_RetrieveDataDetails"), capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataDetails.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[62] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("manageData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfoWA"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_ManageDataParameters"), capitalBank.issuing_v_01_02_xsd.ComplexType_ManageDataParameters.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfoWA"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ManageData_Response"), capitalBank.issuing_v_01_02_xsd.RowType_ManageData_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[63] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("redeemLoyaltyPointsAsMoney");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RedeemLoyaltyPointsAsMoney"), capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[64] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("disallowCardRenewal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DisallowCardRenewal"), capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DisallowCardRenewal"), capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[65] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addTechAcctInitInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AddTechAcctInitInfo"), capitalBank.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[66] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("relinkAllAgreements");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RelinkAllAgreements_Request"), capitalBank.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[67] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRealCard");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetRealCard_Request"), capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetRealCard_Response"), capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[68] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCardPinTryCounter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetCardPinTryCounter_Request"), capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetCardPinTryCounter_Response"), capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[69] = oper;

    }

    private static void _initOperationDesc8(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resetPINCounter");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ResetPINCounter_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[70] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("assignPIN");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AssignPIN_Request"), capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AssignPIN_Response"), capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[71] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("preparePayOff");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PreparePayOff_Request"), capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PreparePayOff_Response"), capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[72] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("performPayOff");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PerformPayOff_Request"), capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PerformPayOff_Response"), capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[73] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryRealTimeStatement");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryRealTimeStatement_Request"), capitalBank.issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[74] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryAuthorizations");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryAuthorizations_Request"), capitalBank.issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic"), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[75] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("changeAdditionalService");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeAdditionalService_Request"), capitalBank.issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[76] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("manageTSMWallet");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_manageTSMWallet_Request"), capitalBank.issuing_v_01_02_xsd.RowType_manageTSMWallet_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[77] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBeginBal");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetBeginBalance_Request"), capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Request.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResponseInfo"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Details"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetBeginBalance_Response"), capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Response.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[78] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("transferDataToFo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ConnectionInfo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo"), capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Parameters"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferDataToFo_Request"), capitalBank.issuing_v_01_02_xsd.RowType_TransferDataToFo_Request.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo"));
        oper.setReturnClass(capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "ResponseInfo"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[79] = oper;

    }

    public IssuingSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public IssuingSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public IssuingSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
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
            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_ManageDataParameters");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ComplexType_ManageDataParameters.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_RetrieveDataDetails");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_RetrieveDataParameters");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ItemType_Generic");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ItemType_Generic.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "KeyType_Agreement");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.KeyType_Agreement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "KeyType_Customer");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.KeyType_Customer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_AccountInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_AddServInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_AddServInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CardInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CustomDataOperationMerch");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_CustomDataOperationMerch.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_CustomerCustomInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_EventBalanceRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_EventBalanceResponse");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_Generic");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_Generic.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_LimitEvent");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_ManageDataRows");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.ListType_ManageDataRows.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationConnectionInfoWA");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "OperationResponseInfoWA");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_3VTSCustomerReg_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccBalanceQueryByCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AccountInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo_Additional");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AccountInfo_Additional.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo_Base");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AccountInfo_Base.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ActivateAccountByCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ActivateCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ActivateCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AddCardToStopList_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AddServData");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AddServData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AddTechAcctInitInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Agreement");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Agreement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AgreementReLinkRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AssignPIN_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AssignPIN_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Authentication");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Authentication.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardholderDataHist_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CardInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_EMV_Data");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_EMV_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_LogicalCard");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_LogicalCard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_PhysicalCard");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_PhysicalCard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CardInfo_TSM_Data");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CardInfo_TSM_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeAdditionalService_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeLoyaltyAccountStatus");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeSGP_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ChangeSGP_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CloseAccount_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomDataOperationMerch");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CustomDataOperationMerch.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomDataOperationMerch_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CustomDataOperationMerch_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Customer");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Customer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomerCustomInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CustomerCustomInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomerStatusChange_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DeactivateCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_DeactivateCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DisallowCardRenewal");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DormantAccountByCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DuplicateCard");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditAccount_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EditAccount_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditAgreement_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EditCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EditCustomer_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EmbossNewCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EnterLoyaltyAccountTransaction");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EventBalanceRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EventBalanceRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EventBalanceResponse");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_EventBalanceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExecTransaction_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExecTransaction_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ExpiredCards_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ExpiredCards_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_FilterCondition");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_FilterCondition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Generic");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Generic.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetBeginBalance_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetBeginBalance_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetCardPinTryCounter_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetCardPinTryCounter_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetRealCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetRealCard_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Confirmation_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Hint_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_Hint_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_InstantToReal_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_InstantToReal_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitBalance");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LimitBalance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitEvent");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LimitEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LimitEvent_Limit");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LimitEvent_Limit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LinkCardToLoyaltyAccountRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListAccounts_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ListAccounts_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListAccountsByCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCardsByAccount_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCombiCards_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ListCombiCards_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCustomerCards_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ListCustomers_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ListCustomers_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountRelinkRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountUnlinkRequest");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_MakeAccountDormant_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ManageData_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ManageData_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ManageDataCol");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ManageDataCol.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_manageTSMWallet_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_manageTSMWallet_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_NewInstantCards_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_NewInstantCards_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_OrderPinEnvelope");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PerformPayOff_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PerformPayOff_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PreparePayOff_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PreparePayOff_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryAuthorizations_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryBillInfo_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryCardInfo_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryRealTimeStatement_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RedeemLoyaltyPoints");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RedeemLoyaltyPoints4Fusion");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RedeemLoyaltyPointsAsMoney");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RelinkAllAgreements_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RelinkCard_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RelinkCard_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RemoveCardFromStop_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RenewCard");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RenewCard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ReplaceCard");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ResetPINCounter_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_RestoreFromArchive_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransactionHist_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_TransactionHist_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferDataToFo_Request");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_TransferDataToFo_Request.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferLoyaltyPoints");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransferLoyaltyPoints_Response");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints_Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "VersionInfo");
            cachedSerQNames.add(qName);
            cls = capitalBank.issuing_v_01_02_xsd.VersionInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
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

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newCustomer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "newCustomer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, customerInfo.value, customListInfo.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                customerInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Customer) _output.get(new javax.xml.namespace.QName("", "CustomerInfo"));
            } catch (java.lang.Exception _exception) {
                customerInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Customer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CustomerInfo")), capitalBank.issuing_v_01_02_xsd.RowType_Customer.class);
            }
            try {
                customListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo) _output.get(new javax.xml.namespace.QName("", "CustomListInfo"));
            } catch (java.lang.Exception _exception) {
                customListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CustomListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "newAgreement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, agreementInfo.value, accountsListInfo.value, cardsListInfo.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                agreementInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Agreement) _output.get(new javax.xml.namespace.QName("", "AgreementInfo"));
            } catch (java.lang.Exception _exception) {
                agreementInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Agreement) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AgreementInfo")), capitalBank.issuing_v_01_02_xsd.RowType_Agreement.class);
            }
            try {
                accountsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo) _output.get(new javax.xml.namespace.QName("", "AccountsListInfo"));
            } catch (java.lang.Exception _exception) {
                accountsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AccountsListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class);
            }
            try {
                cardsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CardInfo) _output.get(new javax.xml.namespace.QName("", "CardsListInfo"));
            } catch (java.lang.Exception _exception) {
                cardsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CardInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CardsListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Agreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.KeyType_Agreement mainAgreementInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "addInfo4Agreement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, mainAgreementInfo, accountsListInfo.value, cardsListInfo.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                accountsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo) _output.get(new javax.xml.namespace.QName("", "AccountsListInfo"));
            } catch (java.lang.Exception _exception) {
                accountsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AccountsListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class);
            }
            try {
                cardsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CardInfo) _output.get(new javax.xml.namespace.QName("", "CardsListInfo"));
            } catch (java.lang.Exception _exception) {
                cardsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CardInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CardsListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addInfo4Customer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.KeyType_Customer mainCustomerInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "addInfo4Customer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, mainCustomerInfo, customListInfo.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                customListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo) _output.get(new javax.xml.namespace.QName("", "CustomListInfo"));
            } catch (java.lang.Exception _exception) {
                customListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CustomListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void customDataOperation(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.ListType_CustomDataOperationMerch customDataOperationInfo, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomDataOperationMerch_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "customDataOperation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, customDataOperationInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_CustomDataOperationMerch_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_CustomDataOperationMerch_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_CustomDataOperationMerch_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditAccount_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "editAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "editCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editCustomer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditCustomer_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "editCustomer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo editAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EditAgreement_Request agreementInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "editAgreement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, agreementInfo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listCustomers(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCustomers_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listCustomers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listCustomerCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCustomerCards_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listCustomerCards"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listAccountsByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListAccountsByCard_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listAccountsByCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listAccounts(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListAccounts_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listAccounts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo checkAuthentication(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_Authentication parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "checkAuthentication"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void requestHint(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_Hint_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_Hint_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "requestHint"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_Hint_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_Hint_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_Hint_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryTransactionHistory(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_TransactionHist_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryTransactionHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryAccountBalanceByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AccBalanceQueryByCard_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryAccountBalanceByCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryCardholderDataHistory(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_CardholderDataHist_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryCardholderDataHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void executeTransaction(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_ExecTransaction_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "executeTransaction"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_ExecTransaction_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void transferLoyaltyPoints(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_TransferLoyaltyPoints_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "transferLoyaltyPoints"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_TransferLoyaltyPoints_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listExpiredCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ExpiredCards_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listExpiredCards"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RelinkCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "relinkCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeSGP(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ChangeSGP_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "changeSGP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo confirmHint(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_Hint_Confirmation_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "confirmHint"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo register3VTSCustomer(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_3VTSCustomerReg_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "register3VTSCustomer"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addCardToStop(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AddCardToStopList_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "addCardToStop"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo activateCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ActivateCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "activateCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo removeCardFromStop(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RemoveCardFromStop_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "removeCardFromStop"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo dormantAccountByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DormantAccountByCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "dormantAccountByCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo activateAccountByCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ActivateAccountByCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "activateAccountByCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeCustomerStatus(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_CustomerStatusChange_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "changeCustomerStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryBillInfo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryBillInfo_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryBillInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo createLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "createLoyaltyAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountRelinkRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "relinkLoyaltyAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo linkCardToLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LinkCardToLoyaltyAccountRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "linkCardToLoyaltyAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo unlinkLoyaltyAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_LoyaltyAccountUnlinkRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "unlinkLoyaltyAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.VersionInfo getVersion() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "getVersion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.VersionInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.VersionInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.VersionInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void executeSoapBatch(javax.xml.rpc.holders.ByteArrayHolder contents) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "executeSoapBatch"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {contents.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                contents.value = (byte[]) _output.get(new javax.xml.namespace.QName("", "Contents"));
            } catch (java.lang.Exception _exception) {
                contents.value = (byte[]) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Contents")), byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newCustomerAndAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_CustomerHolder customerInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CustomerCustomInfoHolder customListInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_AgreementHolder agreementInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_AccountInfoHolder accountsListInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_CardInfoHolder cardsListInfo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "newCustomerAndAgreement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, customerInfo.value, customListInfo.value, agreementInfo.value, accountsListInfo.value, cardsListInfo.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                customerInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Customer) _output.get(new javax.xml.namespace.QName("", "CustomerInfo"));
            } catch (java.lang.Exception _exception) {
                customerInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Customer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CustomerInfo")), capitalBank.issuing_v_01_02_xsd.RowType_Customer.class);
            }
            try {
                customListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo) _output.get(new javax.xml.namespace.QName("", "CustomListInfo"));
            } catch (java.lang.Exception _exception) {
                customListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CustomListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_CustomerCustomInfo.class);
            }
            try {
                agreementInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Agreement) _output.get(new javax.xml.namespace.QName("", "AgreementInfo"));
            } catch (java.lang.Exception _exception) {
                agreementInfo.value = (capitalBank.issuing_v_01_02_xsd.RowType_Agreement) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AgreementInfo")), capitalBank.issuing_v_01_02_xsd.RowType_Agreement.class);
            }
            try {
                accountsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo) _output.get(new javax.xml.namespace.QName("", "AccountsListInfo"));
            } catch (java.lang.Exception _exception) {
                accountsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AccountsListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_AccountInfo.class);
            }
            try {
                cardsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CardInfo) _output.get(new javax.xml.namespace.QName("", "CardsListInfo"));
            } catch (java.lang.Exception _exception) {
                cardsListInfo.value = (capitalBank.issuing_v_01_02_xsd.ListType_CardInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "CardsListInfo")), capitalBank.issuing_v_01_02_xsd.ListType_CardInfo.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkAgreement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AgreementReLinkRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "relinkAgreement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo setLimits(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "setLimits"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, limit_Event.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                limit_Event.value = (capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent) _output.get(new javax.xml.namespace.QName("", "Limit_Event"));
            } catch (java.lang.Exception _exception) {
                limit_Event.value = (capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Limit_Event")), capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo getLimits(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_LimitEventHolder limit_Event) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "getLimits"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, limit_Event.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                limit_Event.value = (capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent) _output.get(new javax.xml.namespace.QName("", "Limit_Event"));
            } catch (java.lang.Exception _exception) {
                limit_Event.value = (capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Limit_Event")), capitalBank.issuing_v_01_02_xsd.ListType_LimitEvent.class);
            }
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getLimitsBalance(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceRequest limit_balance_event_in, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_EventBalanceResponseHolder limit_balance_event_out) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "getLimitsBalance"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, limit_balance_event_in});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                limit_balance_event_out.value = (capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceResponse) _output.get(new javax.xml.namespace.QName("", "limit_balance_event_out"));
            } catch (java.lang.Exception _exception) {
                limit_balance_event_out.value = (capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "limit_balance_event_out")), capitalBank.issuing_v_01_02_xsd.ListType_EventBalanceResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryCardInfo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryCardInfo_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryCardInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listCombiCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCombiCards_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listCombiCards"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void renewCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RenewCard parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_RenewCardHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "renewCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_RenewCard) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_RenewCard) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_RenewCard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void replaceCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_ReplaceCardHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "replaceCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_ReplaceCard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void duplicateCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_DuplicateCardHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "duplicateCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_DuplicateCard.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void orderPinEnvelope(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_OrderPinEnvelopeHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "orderPinEnvelope"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_OrderPinEnvelope.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo restoreFromArchive(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RestoreFromArchive_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "restoreFromArchive"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo newInstantCards(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_NewInstantCards_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "newInstantCards"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void executeSoapBatch2(java.lang.String action, java.math.BigInteger options, javax.xml.rpc.holders.StringHolder processId, javax.xml.rpc.holders.BigIntegerHolder response, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "executeSoapBatch2"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {action, options, processId.value});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                processId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "ProcessId"));
            } catch (java.lang.Exception _exception) {
                processId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ProcessId")), java.lang.String.class);
            }
            try {
                response.value = (java.math.BigInteger) _output.get(new javax.xml.namespace.QName("", "Response"));
            } catch (java.lang.Exception _exception) {
                response.value = (java.math.BigInteger) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Response")), java.math.BigInteger.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo makeAccountDormant(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_MakeAccountDormant_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "makeAccountDormant"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo closeAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_CloseAccount_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "closeAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo embossNewCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EmbossNewCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "embossNewCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo deactivateCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DeactivateCard_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "deactivateCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void listCardsByAccount(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ListCardsByAccount_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "listCardsByAccount"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo instantToReal(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_InstantToReal_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "instantToReal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo enterLoyaltyAccountTransaction(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_EnterLoyaltyAccountTransaction parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[58]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "enterLoyaltyAccountTransaction"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void redeemLoyaltyPoints(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[59]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "redeemLoyaltyPoints"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void redeemLoyaltyPoints4Fusion(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPoints4Fusion parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[60]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "redeemLoyaltyPoints4Fusion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeLoyaltyAccountStatus(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ChangeLoyaltyAccountStatus parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[61]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "changeLoyaltyAccountStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void retrieveData(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataParameters parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ComplexType_RetrieveDataDetailsHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[62]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "retrieveData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataDetails) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataDetails) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ComplexType_RetrieveDataDetails.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void manageData(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfoWA connectionInfo, capitalBank.issuing_v_01_02_xsd.ComplexType_ManageDataParameters parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoWAHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_ManageData_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[63]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "manageData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfoWA.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_ManageData_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_ManageData_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_ManageData_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void redeemLoyaltyPointsAsMoney(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RedeemLoyaltyPointsAsMoney parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[64]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "redeemLoyaltyPointsAsMoney"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void disallowCardRenewal(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_DisallowCardRenewalHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[65]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "disallowCardRenewal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_DisallowCardRenewal.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo addTechAcctInitInfo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AddTechAcctInitInfo parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[66]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "addTechAcctInitInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo relinkAllAgreements(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_RelinkAllAgreements_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[67]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "relinkAllAgreements"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getRealCard(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_GetRealCard_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[68]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "getRealCard"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_GetRealCard_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getCardPinTryCounter(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_GetCardPinTryCounter_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[69]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "getCardPinTryCounter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_GetCardPinTryCounter_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo resetPINCounter(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ResetPINCounter_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[70]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "resetPINCounter"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void assignPIN(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_AssignPIN_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[71]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "assignPIN"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_AssignPIN_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void preparePayOff(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_PreparePayOff_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[72]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "preparePayOff"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_PreparePayOff_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void performPayOff(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_PerformPayOff_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[73]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "performPayOff"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_PerformPayOff_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryRealTimeStatement(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryRealTimeStatement_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[74]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryRealTimeStatement"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void queryAuthorizations(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_QueryAuthorizations_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.ListType_GenericHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[75]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "queryAuthorizations"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.ListType_Generic) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.ListType_Generic.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo changeAdditionalService(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_ChangeAdditionalService_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[76]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "changeAdditionalService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo manageTSMWallet(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_manageTSMWallet_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[77]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "manageTSMWallet"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void getBeginBal(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Request parameters, capitalBank.issuing_v_01_02_xsd.holders.OperationResponseInfoHolder responseInfo, capitalBank.issuing_v_01_02_xsd.holders.RowType_GetBeginBalance_ResponseHolder details) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[78]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "getBeginBal"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _output.get(new javax.xml.namespace.QName("", "ResponseInfo"));
            } catch (java.lang.Exception _exception) {
                responseInfo.value = (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResponseInfo")), capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
            try {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Response) _output.get(new javax.xml.namespace.QName("", "Details"));
            } catch (java.lang.Exception _exception) {
                details.value = (capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Response) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Details")), capitalBank.issuing_v_01_02_xsd.RowType_GetBeginBalance_Response.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public capitalBank.issuing_v_01_02_xsd.OperationResponseInfo transferDataToFo(capitalBank.issuing_v_01_02_xsd.OperationConnectionInfo connectionInfo, capitalBank.issuing_v_01_02_xsd.RowType_TransferDataToFo_Request parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[79]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("urn:IssuingWS/binding", "transferDataToFo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {connectionInfo, parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (capitalBank.issuing_v_01_02_xsd.OperationResponseInfo) org.apache.axis.utils.JavaUtils.convert(_resp, capitalBank.issuing_v_01_02_xsd.OperationResponseInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
