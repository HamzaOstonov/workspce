/**
 * RowType_LinkCardToLoyaltyAccountRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_LinkCardToLoyaltyAccountRequest  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String LS_CODE;

    private java.math.BigInteger LY_ACCOUNT_NO;

    public RowType_LinkCardToLoyaltyAccountRequest() {
    }

    public RowType_LinkCardToLoyaltyAccountRequest(
           java.lang.String CARD,
           java.lang.String LS_CODE,
           java.math.BigInteger LY_ACCOUNT_NO) {
           this.CARD = CARD;
           this.LS_CODE = LS_CODE;
           this.LY_ACCOUNT_NO = LY_ACCOUNT_NO;
    }


    /**
     * Gets the CARD value for this RowType_LinkCardToLoyaltyAccountRequest.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_LinkCardToLoyaltyAccountRequest.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the LS_CODE value for this RowType_LinkCardToLoyaltyAccountRequest.
     * 
     * @return LS_CODE
     */
    public java.lang.String getLS_CODE() {
        return LS_CODE;
    }


    /**
     * Sets the LS_CODE value for this RowType_LinkCardToLoyaltyAccountRequest.
     * 
     * @param LS_CODE
     */
    public void setLS_CODE(java.lang.String LS_CODE) {
        this.LS_CODE = LS_CODE;
    }


    /**
     * Gets the LY_ACCOUNT_NO value for this RowType_LinkCardToLoyaltyAccountRequest.
     * 
     * @return LY_ACCOUNT_NO
     */
    public java.math.BigInteger getLY_ACCOUNT_NO() {
        return LY_ACCOUNT_NO;
    }


    /**
     * Sets the LY_ACCOUNT_NO value for this RowType_LinkCardToLoyaltyAccountRequest.
     * 
     * @param LY_ACCOUNT_NO
     */
    public void setLY_ACCOUNT_NO(java.math.BigInteger LY_ACCOUNT_NO) {
        this.LY_ACCOUNT_NO = LY_ACCOUNT_NO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_LinkCardToLoyaltyAccountRequest)) return false;
        RowType_LinkCardToLoyaltyAccountRequest other = (RowType_LinkCardToLoyaltyAccountRequest) obj;
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
            ((this.LS_CODE==null && other.getLS_CODE()==null) || 
             (this.LS_CODE!=null &&
              this.LS_CODE.equals(other.getLS_CODE()))) &&
            ((this.LY_ACCOUNT_NO==null && other.getLY_ACCOUNT_NO()==null) || 
             (this.LY_ACCOUNT_NO!=null &&
              this.LY_ACCOUNT_NO.equals(other.getLY_ACCOUNT_NO())));
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
        if (getLS_CODE() != null) {
            _hashCode += getLS_CODE().hashCode();
        }
        if (getLY_ACCOUNT_NO() != null) {
            _hashCode += getLY_ACCOUNT_NO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_LinkCardToLoyaltyAccountRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LinkCardToLoyaltyAccountRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LS_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LS_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LY_ACCOUNT_NO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LY_ACCOUNT_NO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
