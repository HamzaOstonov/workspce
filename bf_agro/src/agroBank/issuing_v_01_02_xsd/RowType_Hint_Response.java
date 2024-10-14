/**
 * RowType_Hint_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_Hint_Response  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String HINT_QUESTION;

    private java.util.Calendar EXPIRY1;

    private java.lang.String STATUS;

    public RowType_Hint_Response() {
    }

    public RowType_Hint_Response(
           java.lang.String CARD,
           java.lang.String HINT_QUESTION,
           java.util.Calendar EXPIRY1,
           java.lang.String STATUS) {
           this.CARD = CARD;
           this.HINT_QUESTION = HINT_QUESTION;
           this.EXPIRY1 = EXPIRY1;
           this.STATUS = STATUS;
    }


    /**
     * Gets the CARD value for this RowType_Hint_Response.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_Hint_Response.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the HINT_QUESTION value for this RowType_Hint_Response.
     * 
     * @return HINT_QUESTION
     */
    public java.lang.String getHINT_QUESTION() {
        return HINT_QUESTION;
    }


    /**
     * Sets the HINT_QUESTION value for this RowType_Hint_Response.
     * 
     * @param HINT_QUESTION
     */
    public void setHINT_QUESTION(java.lang.String HINT_QUESTION) {
        this.HINT_QUESTION = HINT_QUESTION;
    }


    /**
     * Gets the EXPIRY1 value for this RowType_Hint_Response.
     * 
     * @return EXPIRY1
     */
    public java.util.Calendar getEXPIRY1() {
        return EXPIRY1;
    }


    /**
     * Sets the EXPIRY1 value for this RowType_Hint_Response.
     * 
     * @param EXPIRY1
     */
    public void setEXPIRY1(java.util.Calendar EXPIRY1) {
        this.EXPIRY1 = EXPIRY1;
    }


    /**
     * Gets the STATUS value for this RowType_Hint_Response.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this RowType_Hint_Response.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_Hint_Response)) return false;
        RowType_Hint_Response other = (RowType_Hint_Response) obj;
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
            ((this.HINT_QUESTION==null && other.getHINT_QUESTION()==null) || 
             (this.HINT_QUESTION!=null &&
              this.HINT_QUESTION.equals(other.getHINT_QUESTION()))) &&
            ((this.EXPIRY1==null && other.getEXPIRY1()==null) || 
             (this.EXPIRY1!=null &&
              this.EXPIRY1.equals(other.getEXPIRY1()))) &&
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS())));
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
        if (getHINT_QUESTION() != null) {
            _hashCode += getHINT_QUESTION().hashCode();
        }
        if (getEXPIRY1() != null) {
            _hashCode += getEXPIRY1().hashCode();
        }
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_Hint_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_Hint_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HINT_QUESTION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HINT_QUESTION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXPIRY1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXPIRY1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STATUS"));
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
