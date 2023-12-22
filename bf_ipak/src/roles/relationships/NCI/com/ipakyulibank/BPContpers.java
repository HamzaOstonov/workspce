/**
 * BPContpers.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.relationships.NCI.com.ipakyulibank;


/**
 * Данные контактных лиц. Для BUR001
 */
public class BPContpers  implements java.io.Serializable {
    /* Должность (справочник по таблице TB912) */
    private java.lang.String pafkt;

    public BPContpers() {
    }

    public BPContpers(
           java.lang.String pafkt) {
           this.pafkt = pafkt;
    }


    /**
     * Gets the pafkt value for this BPContpers.
     * 
     * @return pafkt   * Должность (справочник по таблице TB912)
     */
    public java.lang.String getPafkt() {
        return pafkt;
    }


    /**
     * Sets the pafkt value for this BPContpers.
     * 
     * @param pafkt   * Должность (справочник по таблице TB912)
     */
    public void setPafkt(java.lang.String pafkt) {
        this.pafkt = pafkt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPContpers)) return false;
        BPContpers other = (BPContpers) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pafkt==null && other.getPafkt()==null) || 
             (this.pafkt!=null &&
              this.pafkt.equals(other.getPafkt())));
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
        if (getPafkt() != null) {
            _hashCode += getPafkt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BPContpers.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContpers"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pafkt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pafkt"));
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
