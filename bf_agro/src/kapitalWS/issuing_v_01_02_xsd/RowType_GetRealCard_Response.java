/**
 * RowType_GetRealCard_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kapitalWS.issuing_v_01_02_xsd;

public class RowType_GetRealCard_Response  implements java.io.Serializable {
    private java.lang.String RCARD;

    public RowType_GetRealCard_Response() {
    }

    public RowType_GetRealCard_Response(
           java.lang.String RCARD) {
           this.RCARD = RCARD;
    }


    /**
     * Gets the RCARD value for this RowType_GetRealCard_Response.
     * 
     * @return RCARD
     */
    public java.lang.String getRCARD() {
        return RCARD;
    }


    /**
     * Sets the RCARD value for this RowType_GetRealCard_Response.
     * 
     * @param RCARD
     */
    public void setRCARD(java.lang.String RCARD) {
        this.RCARD = RCARD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_GetRealCard_Response)) return false;
        RowType_GetRealCard_Response other = (RowType_GetRealCard_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.RCARD==null && other.getRCARD()==null) || 
             (this.RCARD!=null &&
              this.RCARD.equals(other.getRCARD())));
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
        if (getRCARD() != null) {
            _hashCode += getRCARD().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_GetRealCard_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetRealCard_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RCARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RCARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
