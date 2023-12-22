/**
 * RelationshipsReqest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.relationships.NCI.com.ipakyulibank;

public class RelationshipsReqest  implements java.io.Serializable {
    /* Данные делового партнера 1 */
    private relationships.NCI.com.ipakyulibank.BPContent bp_01;

    /* Данные делового партнера 2 */
    private relationships.NCI.com.ipakyulibank.BPContent bp_02;

    /* Виды отношений, которые требуется создать между ДП */
    private relationships.NCI.com.ipakyulibank.BPRel bp_relationships;

    public RelationshipsReqest() {
    }

    public RelationshipsReqest(
           relationships.NCI.com.ipakyulibank.BPContent bp_01,
           relationships.NCI.com.ipakyulibank.BPContent bp_02,
           relationships.NCI.com.ipakyulibank.BPRel bp_relationships) {
           this.bp_01 = bp_01;
           this.bp_02 = bp_02;
           this.bp_relationships = bp_relationships;
    }


    /**
     * Gets the bp_01 value for this RelationshipsReqest.
     * 
     * @return bp_01   * Данные делового партнера 1
     */
    public relationships.NCI.com.ipakyulibank.BPContent getBp_01() {
        return bp_01;
    }


    /**
     * Sets the bp_01 value for this RelationshipsReqest.
     * 
     * @param bp_01   * Данные делового партнера 1
     */
    public void setBp_01(relationships.NCI.com.ipakyulibank.BPContent bp_01) {
        this.bp_01 = bp_01;
    }


    /**
     * Gets the bp_02 value for this RelationshipsReqest.
     * 
     * @return bp_02   * Данные делового партнера 2
     */
    public relationships.NCI.com.ipakyulibank.BPContent getBp_02() {
        return bp_02;
    }


    /**
     * Sets the bp_02 value for this RelationshipsReqest.
     * 
     * @param bp_02   * Данные делового партнера 2
     */
    public void setBp_02(relationships.NCI.com.ipakyulibank.BPContent bp_02) {
        this.bp_02 = bp_02;
    }


    /**
     * Gets the bp_relationships value for this RelationshipsReqest.
     * 
     * @return bp_relationships   * Виды отношений, которые требуется создать между ДП
     */
    public relationships.NCI.com.ipakyulibank.BPRel getBp_relationships() {
        return bp_relationships;
    }


    /**
     * Sets the bp_relationships value for this RelationshipsReqest.
     * 
     * @param bp_relationships   * Виды отношений, которые требуется создать между ДП
     */
    public void setBp_relationships(relationships.NCI.com.ipakyulibank.BPRel bp_relationships) {
        this.bp_relationships = bp_relationships;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RelationshipsReqest)) return false;
        RelationshipsReqest other = (RelationshipsReqest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bp_01==null && other.getBp_01()==null) || 
             (this.bp_01!=null &&
              this.bp_01.equals(other.getBp_01()))) &&
            ((this.bp_02==null && other.getBp_02()==null) || 
             (this.bp_02!=null &&
              this.bp_02.equals(other.getBp_02()))) &&
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
        if (getBp_01() != null) {
            _hashCode += getBp_01().hashCode();
        }
        if (getBp_02() != null) {
            _hashCode += getBp_02().hashCode();
        }
        if (getBp_relationships() != null) {
            _hashCode += getBp_relationships().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RelationshipsReqest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "RelationshipsReqest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_01");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_01"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContent"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_02");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_02"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContent"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bp_relationships");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bp_relationships"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRel"));
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
