/**
 * RowType_AddTechAcctInitInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package capitalBank.issuing_v_01_02_xsd;

public class RowType_AddTechAcctInitInfo  implements java.io.Serializable {
    private java.math.BigInteger ACCOUNT_NO;

    private java.math.BigInteger ACCNT_SCHEME;

    private java.math.BigInteger TA_TYPE;

    private java.lang.String GL_CODE;

    public RowType_AddTechAcctInitInfo() {
    }

    public RowType_AddTechAcctInitInfo(
           java.math.BigInteger ACCOUNT_NO,
           java.math.BigInteger ACCNT_SCHEME,
           java.math.BigInteger TA_TYPE,
           java.lang.String GL_CODE) {
           this.ACCOUNT_NO = ACCOUNT_NO;
           this.ACCNT_SCHEME = ACCNT_SCHEME;
           this.TA_TYPE = TA_TYPE;
           this.GL_CODE = GL_CODE;
    }


    /**
     * Gets the ACCOUNT_NO value for this RowType_AddTechAcctInitInfo.
     * 
     * @return ACCOUNT_NO
     */
    public java.math.BigInteger getACCOUNT_NO() {
        return ACCOUNT_NO;
    }


    /**
     * Sets the ACCOUNT_NO value for this RowType_AddTechAcctInitInfo.
     * 
     * @param ACCOUNT_NO
     */
    public void setACCOUNT_NO(java.math.BigInteger ACCOUNT_NO) {
        this.ACCOUNT_NO = ACCOUNT_NO;
    }


    /**
     * Gets the ACCNT_SCHEME value for this RowType_AddTechAcctInitInfo.
     * 
     * @return ACCNT_SCHEME
     */
    public java.math.BigInteger getACCNT_SCHEME() {
        return ACCNT_SCHEME;
    }


    /**
     * Sets the ACCNT_SCHEME value for this RowType_AddTechAcctInitInfo.
     * 
     * @param ACCNT_SCHEME
     */
    public void setACCNT_SCHEME(java.math.BigInteger ACCNT_SCHEME) {
        this.ACCNT_SCHEME = ACCNT_SCHEME;
    }


    /**
     * Gets the TA_TYPE value for this RowType_AddTechAcctInitInfo.
     * 
     * @return TA_TYPE
     */
    public java.math.BigInteger getTA_TYPE() {
        return TA_TYPE;
    }


    /**
     * Sets the TA_TYPE value for this RowType_AddTechAcctInitInfo.
     * 
     * @param TA_TYPE
     */
    public void setTA_TYPE(java.math.BigInteger TA_TYPE) {
        this.TA_TYPE = TA_TYPE;
    }


    /**
     * Gets the GL_CODE value for this RowType_AddTechAcctInitInfo.
     * 
     * @return GL_CODE
     */
    public java.lang.String getGL_CODE() {
        return GL_CODE;
    }


    /**
     * Sets the GL_CODE value for this RowType_AddTechAcctInitInfo.
     * 
     * @param GL_CODE
     */
    public void setGL_CODE(java.lang.String GL_CODE) {
        this.GL_CODE = GL_CODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AddTechAcctInitInfo)) return false;
        RowType_AddTechAcctInitInfo other = (RowType_AddTechAcctInitInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ACCOUNT_NO==null && other.getACCOUNT_NO()==null) || 
             (this.ACCOUNT_NO!=null &&
              this.ACCOUNT_NO.equals(other.getACCOUNT_NO()))) &&
            ((this.ACCNT_SCHEME==null && other.getACCNT_SCHEME()==null) || 
             (this.ACCNT_SCHEME!=null &&
              this.ACCNT_SCHEME.equals(other.getACCNT_SCHEME()))) &&
            ((this.TA_TYPE==null && other.getTA_TYPE()==null) || 
             (this.TA_TYPE!=null &&
              this.TA_TYPE.equals(other.getTA_TYPE()))) &&
            ((this.GL_CODE==null && other.getGL_CODE()==null) || 
             (this.GL_CODE!=null &&
              this.GL_CODE.equals(other.getGL_CODE())));
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
        if (getACCOUNT_NO() != null) {
            _hashCode += getACCOUNT_NO().hashCode();
        }
        if (getACCNT_SCHEME() != null) {
            _hashCode += getACCNT_SCHEME().hashCode();
        }
        if (getTA_TYPE() != null) {
            _hashCode += getTA_TYPE().hashCode();
        }
        if (getGL_CODE() != null) {
            _hashCode += getGL_CODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_AddTechAcctInitInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AddTechAcctInitInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCNT_SCHEME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCNT_SCHEME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TA_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TA_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GL_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GL_CODE"));
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
