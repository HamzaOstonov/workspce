/**
 * RowType_QueryAuthorizations_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_QueryAuthorizations_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.util.Calendar BEGIN_DATE;

    private java.util.Calendar END_DATE;

    private java.lang.String AUT_TYPE;

    private java.lang.String RESPONSE_TYPE;

    private java.lang.String RESPONSE_CODE;

    private java.lang.String LOCKING_FLAG;

    public RowType_QueryAuthorizations_Request() {
    }

    public RowType_QueryAuthorizations_Request(
           java.lang.String CARD,
           java.util.Calendar BEGIN_DATE,
           java.util.Calendar END_DATE,
           java.lang.String AUT_TYPE,
           java.lang.String RESPONSE_TYPE,
           java.lang.String RESPONSE_CODE,
           java.lang.String LOCKING_FLAG) {
           this.CARD = CARD;
           this.BEGIN_DATE = BEGIN_DATE;
           this.END_DATE = END_DATE;
           this.AUT_TYPE = AUT_TYPE;
           this.RESPONSE_TYPE = RESPONSE_TYPE;
           this.RESPONSE_CODE = RESPONSE_CODE;
           this.LOCKING_FLAG = LOCKING_FLAG;
    }


    /**
     * Gets the CARD value for this RowType_QueryAuthorizations_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_QueryAuthorizations_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the BEGIN_DATE value for this RowType_QueryAuthorizations_Request.
     * 
     * @return BEGIN_DATE
     */
    public java.util.Calendar getBEGIN_DATE() {
        return BEGIN_DATE;
    }


    /**
     * Sets the BEGIN_DATE value for this RowType_QueryAuthorizations_Request.
     * 
     * @param BEGIN_DATE
     */
    public void setBEGIN_DATE(java.util.Calendar BEGIN_DATE) {
        this.BEGIN_DATE = BEGIN_DATE;
    }


    /**
     * Gets the END_DATE value for this RowType_QueryAuthorizations_Request.
     * 
     * @return END_DATE
     */
    public java.util.Calendar getEND_DATE() {
        return END_DATE;
    }


    /**
     * Sets the END_DATE value for this RowType_QueryAuthorizations_Request.
     * 
     * @param END_DATE
     */
    public void setEND_DATE(java.util.Calendar END_DATE) {
        this.END_DATE = END_DATE;
    }


    /**
     * Gets the AUT_TYPE value for this RowType_QueryAuthorizations_Request.
     * 
     * @return AUT_TYPE
     */
    public java.lang.String getAUT_TYPE() {
        return AUT_TYPE;
    }


    /**
     * Sets the AUT_TYPE value for this RowType_QueryAuthorizations_Request.
     * 
     * @param AUT_TYPE
     */
    public void setAUT_TYPE(java.lang.String AUT_TYPE) {
        this.AUT_TYPE = AUT_TYPE;
    }


    /**
     * Gets the RESPONSE_TYPE value for this RowType_QueryAuthorizations_Request.
     * 
     * @return RESPONSE_TYPE
     */
    public java.lang.String getRESPONSE_TYPE() {
        return RESPONSE_TYPE;
    }


    /**
     * Sets the RESPONSE_TYPE value for this RowType_QueryAuthorizations_Request.
     * 
     * @param RESPONSE_TYPE
     */
    public void setRESPONSE_TYPE(java.lang.String RESPONSE_TYPE) {
        this.RESPONSE_TYPE = RESPONSE_TYPE;
    }


    /**
     * Gets the RESPONSE_CODE value for this RowType_QueryAuthorizations_Request.
     * 
     * @return RESPONSE_CODE
     */
    public java.lang.String getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }


    /**
     * Sets the RESPONSE_CODE value for this RowType_QueryAuthorizations_Request.
     * 
     * @param RESPONSE_CODE
     */
    public void setRESPONSE_CODE(java.lang.String RESPONSE_CODE) {
        this.RESPONSE_CODE = RESPONSE_CODE;
    }


    /**
     * Gets the LOCKING_FLAG value for this RowType_QueryAuthorizations_Request.
     * 
     * @return LOCKING_FLAG
     */
    public java.lang.String getLOCKING_FLAG() {
        return LOCKING_FLAG;
    }


    /**
     * Sets the LOCKING_FLAG value for this RowType_QueryAuthorizations_Request.
     * 
     * @param LOCKING_FLAG
     */
    public void setLOCKING_FLAG(java.lang.String LOCKING_FLAG) {
        this.LOCKING_FLAG = LOCKING_FLAG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_QueryAuthorizations_Request)) return false;
        RowType_QueryAuthorizations_Request other = (RowType_QueryAuthorizations_Request) obj;
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
            ((this.BEGIN_DATE==null && other.getBEGIN_DATE()==null) || 
             (this.BEGIN_DATE!=null &&
              this.BEGIN_DATE.equals(other.getBEGIN_DATE()))) &&
            ((this.END_DATE==null && other.getEND_DATE()==null) || 
             (this.END_DATE!=null &&
              this.END_DATE.equals(other.getEND_DATE()))) &&
            ((this.AUT_TYPE==null && other.getAUT_TYPE()==null) || 
             (this.AUT_TYPE!=null &&
              this.AUT_TYPE.equals(other.getAUT_TYPE()))) &&
            ((this.RESPONSE_TYPE==null && other.getRESPONSE_TYPE()==null) || 
             (this.RESPONSE_TYPE!=null &&
              this.RESPONSE_TYPE.equals(other.getRESPONSE_TYPE()))) &&
            ((this.RESPONSE_CODE==null && other.getRESPONSE_CODE()==null) || 
             (this.RESPONSE_CODE!=null &&
              this.RESPONSE_CODE.equals(other.getRESPONSE_CODE()))) &&
            ((this.LOCKING_FLAG==null && other.getLOCKING_FLAG()==null) || 
             (this.LOCKING_FLAG!=null &&
              this.LOCKING_FLAG.equals(other.getLOCKING_FLAG())));
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
        if (getBEGIN_DATE() != null) {
            _hashCode += getBEGIN_DATE().hashCode();
        }
        if (getEND_DATE() != null) {
            _hashCode += getEND_DATE().hashCode();
        }
        if (getAUT_TYPE() != null) {
            _hashCode += getAUT_TYPE().hashCode();
        }
        if (getRESPONSE_TYPE() != null) {
            _hashCode += getRESPONSE_TYPE().hashCode();
        }
        if (getRESPONSE_CODE() != null) {
            _hashCode += getRESPONSE_CODE().hashCode();
        }
        if (getLOCKING_FLAG() != null) {
            _hashCode += getLOCKING_FLAG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_QueryAuthorizations_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_QueryAuthorizations_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BEGIN_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BEGIN_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("END_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "END_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AUT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPONSE_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RESPONSE_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPONSE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RESPONSE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOCKING_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOCKING_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
