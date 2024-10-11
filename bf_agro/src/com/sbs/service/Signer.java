/**
 * Signer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class Signer  implements java.io.Serializable {
    private java.lang.Integer actionType;

    private java.lang.Integer confirm;

    private java.util.Calendar date;

    private java.lang.String fio;

    private java.lang.Integer orgType;

    private java.lang.String reason;

    public Signer() {
    }

    public Signer(
           java.lang.Integer actionType,
           java.lang.Integer confirm,
           java.util.Calendar date,
           java.lang.String fio,
           java.lang.Integer orgType,
           java.lang.String reason) {
           this.actionType = actionType;
           this.confirm = confirm;
           this.date = date;
           this.fio = fio;
           this.orgType = orgType;
           this.reason = reason;
    }


    /**
     * Gets the actionType value for this Signer.
     * 
     * @return actionType
     */
    public java.lang.Integer getActionType() {
        return actionType;
    }


    /**
     * Sets the actionType value for this Signer.
     * 
     * @param actionType
     */
    public void setActionType(java.lang.Integer actionType) {
        this.actionType = actionType;
    }


    /**
     * Gets the confirm value for this Signer.
     * 
     * @return confirm
     */
    public java.lang.Integer getConfirm() {
        return confirm;
    }


    /**
     * Sets the confirm value for this Signer.
     * 
     * @param confirm
     */
    public void setConfirm(java.lang.Integer confirm) {
        this.confirm = confirm;
    }


    /**
     * Gets the date value for this Signer.
     * 
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this Signer.
     * 
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the fio value for this Signer.
     * 
     * @return fio
     */
    public java.lang.String getFio() {
        return fio;
    }


    /**
     * Sets the fio value for this Signer.
     * 
     * @param fio
     */
    public void setFio(java.lang.String fio) {
        this.fio = fio;
    }


    /**
     * Gets the orgType value for this Signer.
     * 
     * @return orgType
     */
    public java.lang.Integer getOrgType() {
        return orgType;
    }


    /**
     * Sets the orgType value for this Signer.
     * 
     * @param orgType
     */
    public void setOrgType(java.lang.Integer orgType) {
        this.orgType = orgType;
    }


    /**
     * Gets the reason value for this Signer.
     * 
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this Signer.
     * 
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Signer)) return false;
        Signer other = (Signer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actionType==null && other.getActionType()==null) || 
             (this.actionType!=null &&
              this.actionType.equals(other.getActionType()))) &&
            ((this.confirm==null && other.getConfirm()==null) || 
             (this.confirm!=null &&
              this.confirm.equals(other.getConfirm()))) &&
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.fio==null && other.getFio()==null) || 
             (this.fio!=null &&
              this.fio.equals(other.getFio()))) &&
            ((this.orgType==null && other.getOrgType()==null) || 
             (this.orgType!=null &&
              this.orgType.equals(other.getOrgType()))) &&
            ((this.reason==null && other.getReason()==null) || 
             (this.reason!=null &&
              this.reason.equals(other.getReason())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getActionType() != null) {
            _hashCode += getActionType().hashCode();
        }
        if (getConfirm() != null) {
            _hashCode += getConfirm().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getFio() != null) {
            _hashCode += getFio().hashCode();
        }
        if (getOrgType() != null) {
            _hashCode += getOrgType().hashCode();
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Signer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "signer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orgType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reason");
        elemField.setXmlName(new javax.xml.namespace.QName("", "reason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
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
