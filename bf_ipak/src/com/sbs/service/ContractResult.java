/**
 * ContractResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class ContractResult  extends com.sbs.service.Result  implements java.io.Serializable {
    private com.sbs.service.Contract contract;

    public ContractResult() {
    }

    public ContractResult(
           java.lang.String errorMsg,
           int gtkId,
           int status,
           java.util.Calendar timeStamp,
           com.sbs.service.Contract contract) {
        super(
            errorMsg,
            gtkId,
            status,
            timeStamp);
        this.contract = contract;
    }


    /**
     * Gets the contract value for this ContractResult.
     * 
     * @return contract
     */
    public com.sbs.service.Contract getContract() {
        return contract;
    }


    /**
     * Sets the contract value for this ContractResult.
     * 
     * @param contract
     */
    public void setContract(com.sbs.service.Contract contract) {
        this.contract = contract;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContractResult)) return false;
        ContractResult other = (ContractResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.contract==null && other.getContract()==null) || 
             (this.contract!=null &&
              this.contract.equals(other.getContract())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getContract() != null) {
            _hashCode += getContract().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContractResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "contractResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contract");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "contract"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return fileType metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
