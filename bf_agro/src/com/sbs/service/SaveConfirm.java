/**
 * SaveConfirm.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sbs.service;

public class SaveConfirm  implements java.io.Serializable {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String contractIdn;

    private java.lang.String docType;

    private java.lang.String docNum;

    private java.lang.String chDocNum;

    private java.lang.String agrmNum;

    private java.lang.Short confirm;

    private java.lang.String reason;

    public SaveConfirm() {
    }

    public SaveConfirm(
           java.lang.String username,
           java.lang.String password,
           java.lang.String contractIdn,
           java.lang.String docType,
           java.lang.String docNum,
           java.lang.String chDocNum,
           java.lang.String agrmNum,
           java.lang.Short confirm,
           java.lang.String reason) {
           this.username = username;
           this.password = password;
           this.contractIdn = contractIdn;
           this.docType = docType;
           this.docNum = docNum;
           this.chDocNum = chDocNum;
           this.agrmNum = agrmNum;
           this.confirm = confirm;
           this.reason = reason;
    }


    /**
     * Gets the username value for this SaveConfirm.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this SaveConfirm.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this SaveConfirm.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this SaveConfirm.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the contractIdn value for this SaveConfirm.
     * 
     * @return contractIdn
     */
    public java.lang.String getContractIdn() {
        return contractIdn;
    }


    /**
     * Sets the contractIdn value for this SaveConfirm.
     * 
     * @param contractIdn
     */
    public void setContractIdn(java.lang.String contractIdn) {
        this.contractIdn = contractIdn;
    }


    /**
     * Gets the docType value for this SaveConfirm.
     * 
     * @return docType
     */
    public java.lang.String getDocType() {
        return docType;
    }


    /**
     * Sets the docType value for this SaveConfirm.
     * 
     * @param docType
     */
    public void setDocType(java.lang.String docType) {
        this.docType = docType;
    }


    /**
     * Gets the docNum value for this SaveConfirm.
     * 
     * @return docNum
     */
    public java.lang.String getDocNum() {
        return docNum;
    }


    /**
     * Sets the docNum value for this SaveConfirm.
     * 
     * @param docNum
     */
    public void setDocNum(java.lang.String docNum) {
        this.docNum = docNum;
    }


    /**
     * Gets the chDocNum value for this SaveConfirm.
     * 
     * @return chDocNum
     */
    public java.lang.String getChDocNum() {
        return chDocNum;
    }


    /**
     * Sets the chDocNum value for this SaveConfirm.
     * 
     * @param chDocNum
     */
    public void setChDocNum(java.lang.String chDocNum) {
        this.chDocNum = chDocNum;
    }


    /**
     * Gets the agrmNum value for this SaveConfirm.
     * 
     * @return agrmNum
     */
    public java.lang.String getAgrmNum() {
        return agrmNum;
    }


    /**
     * Sets the agrmNum value for this SaveConfirm.
     * 
     * @param agrmNum
     */
    public void setAgrmNum(java.lang.String agrmNum) {
        this.agrmNum = agrmNum;
    }


    /**
     * Gets the confirm value for this SaveConfirm.
     * 
     * @return confirm
     */
    public java.lang.Short getConfirm() {
        return confirm;
    }


    /**
     * Sets the confirm value for this SaveConfirm.
     * 
     * @param confirm
     */
    public void setConfirm(java.lang.Short confirm) {
        this.confirm = confirm;
    }


    /**
     * Gets the reason value for this SaveConfirm.
     * 
     * @return reason
     */
    public java.lang.String getReason() {
        return reason;
    }


    /**
     * Sets the reason value for this SaveConfirm.
     * 
     * @param reason
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SaveConfirm)) return false;
        SaveConfirm other = (SaveConfirm) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.contractIdn==null && other.getContractIdn()==null) || 
             (this.contractIdn!=null &&
              this.contractIdn.equals(other.getContractIdn()))) &&
            ((this.docType==null && other.getDocType()==null) || 
             (this.docType!=null &&
              this.docType.equals(other.getDocType()))) &&
            ((this.docNum==null && other.getDocNum()==null) || 
             (this.docNum!=null &&
              this.docNum.equals(other.getDocNum()))) &&
            ((this.chDocNum==null && other.getChDocNum()==null) || 
             (this.chDocNum!=null &&
              this.chDocNum.equals(other.getChDocNum()))) &&
            ((this.agrmNum==null && other.getAgrmNum()==null) || 
             (this.agrmNum!=null &&
              this.agrmNum.equals(other.getAgrmNum()))) &&
            ((this.confirm==null && other.getConfirm()==null) || 
             (this.confirm!=null &&
              this.confirm.equals(other.getConfirm()))) &&
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
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getContractIdn() != null) {
            _hashCode += getContractIdn().hashCode();
        }
        if (getDocType() != null) {
            _hashCode += getDocType().hashCode();
        }
        if (getDocNum() != null) {
            _hashCode += getDocNum().hashCode();
        }
        if (getChDocNum() != null) {
            _hashCode += getChDocNum().hashCode();
        }
        if (getAgrmNum() != null) {
            _hashCode += getAgrmNum().hashCode();
        }
        if (getConfirm() != null) {
            _hashCode += getConfirm().hashCode();
        }
        if (getReason() != null) {
            _hashCode += getReason().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaveConfirm.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.sbs.com/", "saveConfirm"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractIdn");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contractIdn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "docNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chDocNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chDocNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agrmNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agrmNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confirm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
