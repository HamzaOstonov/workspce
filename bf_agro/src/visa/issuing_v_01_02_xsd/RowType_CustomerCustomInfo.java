/**
 * RowType_CustomerCustomInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_CustomerCustomInfo  implements java.io.Serializable {
    private java.lang.String f_KEY;

    private java.lang.String f_VALUE;

    public RowType_CustomerCustomInfo() {
    }

    public RowType_CustomerCustomInfo(
           java.lang.String f_KEY,
           java.lang.String f_VALUE) {
           this.f_KEY = f_KEY;
           this.f_VALUE = f_VALUE;
    }


    /**
     * Gets the f_KEY value for this RowType_CustomerCustomInfo.
     * 
     * @return f_KEY
     */
    public java.lang.String getF_KEY() {
        return f_KEY;
    }


    /**
     * Sets the f_KEY value for this RowType_CustomerCustomInfo.
     * 
     * @param f_KEY
     */
    public void setF_KEY(java.lang.String f_KEY) {
        this.f_KEY = f_KEY;
    }


    /**
     * Gets the f_VALUE value for this RowType_CustomerCustomInfo.
     * 
     * @return f_VALUE
     */
    public java.lang.String getF_VALUE() {
        return f_VALUE;
    }


    /**
     * Sets the f_VALUE value for this RowType_CustomerCustomInfo.
     * 
     * @param f_VALUE
     */
    public void setF_VALUE(java.lang.String f_VALUE) {
        this.f_VALUE = f_VALUE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CustomerCustomInfo)) return false;
        RowType_CustomerCustomInfo other = (RowType_CustomerCustomInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.f_KEY==null && other.getF_KEY()==null) || 
             (this.f_KEY!=null &&
              this.f_KEY.equals(other.getF_KEY()))) &&
            ((this.f_VALUE==null && other.getF_VALUE()==null) || 
             (this.f_VALUE!=null &&
              this.f_VALUE.equals(other.getF_VALUE())));
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
        if (getF_KEY() != null) {
            _hashCode += getF_KEY().hashCode();
        }
        if (getF_VALUE() != null) {
            _hashCode += getF_VALUE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CustomerCustomInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CustomerCustomInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_KEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_KEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("f_VALUE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "F_VALUE"));
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
