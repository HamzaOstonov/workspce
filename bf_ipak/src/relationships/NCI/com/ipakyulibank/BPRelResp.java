/**
 * BPRelResp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package relationships.NCI.com.ipakyulibank;

public class BPRelResp  implements java.io.Serializable {
    /* Данные делового партнера 1 */
    private java.lang.String bp_id_01;

    /* Данные делового партнера 1: readable */
    private java.lang.String bp_id_01_name;

    /* Данные делового партнера 1: type */
    private java.lang.String bp_id_01_type;

    /* Данные делового партнера 2 */
    private java.lang.String bp_id_02;

    /* Данные делового партнера 2: readable */
    private java.lang.String bp_id_02_name;

    /* Данные делового партнера 2: type */
    private java.lang.String bp_id_02_type;

    /* Виды отношений, которые требуется создать между ДП */
    private relationships.NCI.com.ipakyulibank.BPRelWithDate bp_relationships;

    public BPRelResp() {
    }

    public BPRelResp(
           java.lang.String bp_id_01,
           java.lang.String bp_id_01_name,
           java.lang.String bp_id_01_type,
           java.lang.String bp_id_02,
           java.lang.String bp_id_02_name,
           java.lang.String bp_id_02_type,
           relationships.NCI.com.ipakyulibank.BPRelWithDate bp_relationships) {
           this.bp_id_01 = bp_id_01;
           this.bp_id_01_name = bp_id_01_name;
           this.bp_id_01_type = bp_id_01_type;
           this.bp_id_02 = bp_id_02;
           this.bp_id_02_name = bp_id_02_name;
           this.bp_id_02_type = bp_id_02_type;
           this.bp_relationships = bp_relationships;
    }


    /**
     * Gets the bp_id_01 value for this BPRelResp.
     * 
     * @return bp_id_01   * Данные делового партнера 1
     */
    public java.lang.String getBp_id_01() {
        return bp_id_01;
    }


    /**
     * Sets the bp_id_01 value for this BPRelResp.
     * 
     * @param bp_id_01   * Данные делового партнера 1
     */
    public void setBp_id_01(java.lang.String bp_id_01) {
        this.bp_id_01 = bp_id_01;
    }


    /**
     * Gets the bp_id_01_name value for this BPRelResp.
     * 
     * @return bp_id_01_name   * Данные делового партнера 1: readable
     */
    public java.lang.String getBp_id_01_name() {
        return bp_id_01_name;
    }


    /**
     * Sets the bp_id_01_name value for this BPRelResp.
     * 
     * @param bp_id_01_name   * Данные делового партнера 1: readable
     */
    public void setBp_id_01_name(java.lang.String bp_id_01_name) {
        this.bp_id_01_name = bp_id_01_name;
    }


    /**
     * Gets the bp_id_01_type value for this BPRelResp.
     * 
     * @return bp_id_01_type   * Данные делового партнера 1: type
     */
    public java.lang.String getBp_id_01_type() {
        return bp_id_01_type;
    }


    /**
     * Sets the bp_id_01_type value for this BPRelResp.
     * 
     * @param bp_id_01_type   * Данные делового партнера 1: type
     */
    public void setBp_id_01_type(java.lang.String bp_id_01_type) {
        this.bp_id_01_type = bp_id_01_type;
    }


    /**
     * Gets the bp_id_02 value for this BPRelResp.
     * 
     * @return bp_id_02   * Данные делового партнера 2
     */
    public java.lang.String getBp_id_02() {
        return bp_id_02;
    }


    /**
     * Sets the bp_id_02 value for this BPRelResp.
     * 
     * @param bp_id_02   * Данные делового партнера 2
     */
    public void setBp_id_02(java.lang.String bp_id_02) {
        this.bp_id_02 = bp_id_02;
    }


    /**
     * Gets the bp_id_02_name value for this BPRelResp.
     * 
     * @return bp_id_02_name   * Данные делового партнера 2: readable
     */
    public java.lang.String getBp_id_02_name() {
        return bp_id_02_name;
    }


    /**
     * Sets the bp_id_02_name value for this BPRelResp.
     * 
     * @param bp_id_02_name   * Данные делового партнера 2: readable
     */
    public void setBp_id_02_name(java.lang.String bp_id_02_name) {
        this.bp_id_02_name = bp_id_02_name;
    }


    /**
     * Gets the bp_id_02_type value for this BPRelResp.
     * 
     * @return bp_id_02_type   * Данные делового партнера 2: type
     */
    public java.lang.String getBp_id_02_type() {
        return bp_id_02_type;
    }


    /**
     * Sets the bp_id_02_type value for this BPRelResp.
     * 
     * @param bp_id_02_type   * Данные делового партнера 2: type
     */
    public void setBp_id_02_type(java.lang.String bp_id_02_type) {
        this.bp_id_02_type = bp_id_02_type;
    }


    /**
     * Gets the bp_relationships value for this BPRelResp.
     * 
     * @return bp_relationships   * Виды отношений, которые требуется создать между ДП
     */
    public relationships.NCI.com.ipakyulibank.BPRelWithDate getBp_relationships() {
        return bp_relationships;
    }


    /**
     * Sets the bp_relationships value for this BPRelResp.
     * 
     * @param bp_relationships   * Виды отношений, которые требуется создать между ДП
     */
    public void setBp_relationships(relationships.NCI.com.ipakyulibank.BPRelWithDate bp_relationships) {
        this.bp_relationships = bp_relationships;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPRelResp)) return false;
        BPRelResp other = (BPRelResp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bp_id_01==null && other.getBp_id_01()==null) || 
             (this.bp_id_01!=null &&
              this.bp_id_01.equals(other.getBp_id_01()))) &&
            ((this.bp_id_01_name==null && other.getBp_id_01_name()==null) || 
             (this.bp_id_01_name!=null &&
              this.bp_id_01_name.equals(other.getBp_id_01_name()))) &&
            ((this.bp_id_01_type==null && other.getBp_id_01_type()==null) || 
             (this.bp_id_01_type!=null &&
              this.bp_id_01_type.equals(other.getBp_id_01_type()))) &&
            ((this.bp_id_02==null && other.getBp_id_02()==null) || 
             (this.bp_id_02!=null &&
              this.bp_id_02.equals(other.getBp_id_02()))) &&
            ((this.bp_id_02_name==null && other.getBp_id_02_name()==null) || 
             (this.bp_id_02_name!=null &&
              this.bp_id_02_name.equals(other.getBp_id_02_name()))) &&
            ((this.bp_id_02_type==null && other.getBp_id_02_type()==null) || 
             (this.bp_id_02_type!=null &&
              this.bp_id_02_type.equals(other.getBp_id_02_type()))) &&
            ((this.bp_relationships==null && other.getBp_relationships()==null) || 
             (this.bp_relationships!=null &&
              this.bp_relationships.equals(other.getBp_relationships())));
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
        if (getBp_id_01() != null) {
            _hashCode += getBp_id_01().hashCode();
        }
        if (getBp_id_01_name() != null) {
            _hashCode += getBp_id_01_name().hashCode();
        }
        if (getBp_id_01_type() != null) {
            _hashCode += getBp_id_01_type().hashCode();
        }
        if (getBp_id_02() != null) {
            _hashCode += getBp_id_02().hashCode();
        }
        if (getBp_id_02_name() != null) {
            _hashCode += getBp_id_02_name().hashCode();
        }
        if (getBp_id_02_type() != null) {
            _hashCode += getBp_id_02_type().hashCode();
        }
        if (getBp_relationships() != null) {
            _hashCode += getBp_relationships().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BPRelResp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelResp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_id_01");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_id_01"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_id_01_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_id_01_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_id_01_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_id_01_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_id_02");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_id_02"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_id_02_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_id_02_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_id_02_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_id_02_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_relationships");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_relationships"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelWithDate"));
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
