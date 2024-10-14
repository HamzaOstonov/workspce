/**
 * RowType_EmbossNewCard_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kapitalWS.issuing_v_01_02_xsd;

public class RowType_EmbossNewCard_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.util.Calendar NEW_EXPIRY;

    public RowType_EmbossNewCard_Request() {
    }

    public RowType_EmbossNewCard_Request(
           java.lang.String CARD,
           java.util.Calendar NEW_EXPIRY) {
           this.CARD = CARD;
           this.NEW_EXPIRY = NEW_EXPIRY;
    }


    /**
     * Gets the CARD value for this RowType_EmbossNewCard_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_EmbossNewCard_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the NEW_EXPIRY value for this RowType_EmbossNewCard_Request.
     * 
     * @return NEW_EXPIRY
     */
    public java.util.Calendar getNEW_EXPIRY() {
        return NEW_EXPIRY;
    }


    /**
     * Sets the NEW_EXPIRY value for this RowType_EmbossNewCard_Request.
     * 
     * @param NEW_EXPIRY
     */
    public void setNEW_EXPIRY(java.util.Calendar NEW_EXPIRY) {
        this.NEW_EXPIRY = NEW_EXPIRY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_EmbossNewCard_Request)) return false;
        RowType_EmbossNewCard_Request other = (RowType_EmbossNewCard_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.NEW_EXPIRY==null && other.getNEW_EXPIRY()==null) || 
             (this.NEW_EXPIRY!=null &&
              this.NEW_EXPIRY.equals(other.getNEW_EXPIRY())));
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
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getNEW_EXPIRY() != null) {
            _hashCode += getNEW_EXPIRY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_EmbossNewCard_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_EmbossNewCard_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_EXPIRY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_EXPIRY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
