/**
 * RowType_PerformPayOff_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_PerformPayOff_Response  implements java.io.Serializable {
    private java.lang.String CARD_ACCT;

    private java.lang.String CCY;

    public RowType_PerformPayOff_Response() {
    }

    public RowType_PerformPayOff_Response(
           java.lang.String CARD_ACCT,
           java.lang.String CCY) {
           this.CARD_ACCT = CARD_ACCT;
           this.CCY = CCY;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_PerformPayOff_Response.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_PerformPayOff_Response.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CCY value for this RowType_PerformPayOff_Response.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_PerformPayOff_Response.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_PerformPayOff_Response)) return false;
        RowType_PerformPayOff_Response other = (RowType_PerformPayOff_Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD_ACCT==null && other.getCARD_ACCT()==null) || 
             (this.CARD_ACCT!=null &&
              this.CARD_ACCT.equals(other.getCARD_ACCT()))) &&
            ((this.CCY==null && other.getCCY()==null) || 
             (this.CCY!=null &&
              this.CCY.equals(other.getCCY())));
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
        if (getCARD_ACCT() != null) {
            _hashCode += getCARD_ACCT().hashCode();
        }
        if (getCCY() != null) {
            _hashCode += getCCY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_PerformPayOff_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_PerformPayOff_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CCY"));
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
