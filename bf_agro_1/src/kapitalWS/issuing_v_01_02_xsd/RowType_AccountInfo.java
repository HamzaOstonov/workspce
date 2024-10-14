/**
 * RowType_AccountInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kapitalWS.issuing_v_01_02_xsd;

public class RowType_AccountInfo  implements java.io.Serializable {
    private kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info;

    private kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Additional additional_info;

    public RowType_AccountInfo() {
    }

    public RowType_AccountInfo(
           kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info,
           kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Additional additional_info) {
           this.base_info = base_info;
           this.additional_info = additional_info;
    }


    /**
     * Gets the base_info value for this RowType_AccountInfo.
     * 
     * @return base_info
     */
    public kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Base getBase_info() {
        return base_info;
    }


    /**
     * Sets the base_info value for this RowType_AccountInfo.
     * 
     * @param base_info
     */
    public void setBase_info(kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Base base_info) {
        this.base_info = base_info;
    }


    /**
     * Gets the additional_info value for this RowType_AccountInfo.
     * 
     * @return additional_info
     */
    public kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Additional getAdditional_info() {
        return additional_info;
    }


    /**
     * Sets the additional_info value for this RowType_AccountInfo.
     * 
     * @param additional_info
     */
    public void setAdditional_info(kapitalWS.issuing_v_01_02_xsd.RowType_AccountInfo_Additional additional_info) {
        this.additional_info = additional_info;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AccountInfo)) return false;
        RowType_AccountInfo other = (RowType_AccountInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.base_info==null && other.getBase_info()==null) || 
             (this.base_info!=null &&
              this.base_info.equals(other.getBase_info()))) &&
            ((this.additional_info==null && other.getAdditional_info()==null) || 
             (this.additional_info!=null &&
              this.additional_info.equals(other.getAdditional_info())));
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
        if (getBase_info() != null) {
            _hashCode += getBase_info().hashCode();
        }
        if (getAdditional_info() != null) {
            _hashCode += getAdditional_info().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_AccountInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base_info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "base_info"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo_Base"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional_info");
        elemField.setXmlName(new javax.xml.namespace.QName("", "additional_info"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AccountInfo_Additional"));
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
