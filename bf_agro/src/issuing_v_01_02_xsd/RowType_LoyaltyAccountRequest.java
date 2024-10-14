/**
 * RowType_LoyaltyAccountRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_LoyaltyAccountRequest  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String LS_CODE;

    private java.lang.String CLIENT_ID;

    private java.lang.String LY_INVITED_BY_ID;

    public RowType_LoyaltyAccountRequest() {
    }

    public RowType_LoyaltyAccountRequest(
           java.lang.String CARD,
           java.lang.String LS_CODE,
           java.lang.String CLIENT_ID,
           java.lang.String LY_INVITED_BY_ID) {
           this.CARD = CARD;
           this.LS_CODE = LS_CODE;
           this.CLIENT_ID = CLIENT_ID;
           this.LY_INVITED_BY_ID = LY_INVITED_BY_ID;
    }


    /**
     * Gets the CARD value for this RowType_LoyaltyAccountRequest.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_LoyaltyAccountRequest.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the LS_CODE value for this RowType_LoyaltyAccountRequest.
     * 
     * @return LS_CODE
     */
    public java.lang.String getLS_CODE() {
        return LS_CODE;
    }


    /**
     * Sets the LS_CODE value for this RowType_LoyaltyAccountRequest.
     * 
     * @param LS_CODE
     */
    public void setLS_CODE(java.lang.String LS_CODE) {
        this.LS_CODE = LS_CODE;
    }


    /**
     * Gets the CLIENT_ID value for this RowType_LoyaltyAccountRequest.
     * 
     * @return CLIENT_ID
     */
    public java.lang.String getCLIENT_ID() {
        return CLIENT_ID;
    }


    /**
     * Sets the CLIENT_ID value for this RowType_LoyaltyAccountRequest.
     * 
     * @param CLIENT_ID
     */
    public void setCLIENT_ID(java.lang.String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }


    /**
     * Gets the LY_INVITED_BY_ID value for this RowType_LoyaltyAccountRequest.
     * 
     * @return LY_INVITED_BY_ID
     */
    public java.lang.String getLY_INVITED_BY_ID() {
        return LY_INVITED_BY_ID;
    }


    /**
     * Sets the LY_INVITED_BY_ID value for this RowType_LoyaltyAccountRequest.
     * 
     * @param LY_INVITED_BY_ID
     */
    public void setLY_INVITED_BY_ID(java.lang.String LY_INVITED_BY_ID) {
        this.LY_INVITED_BY_ID = LY_INVITED_BY_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_LoyaltyAccountRequest)) return false;
        RowType_LoyaltyAccountRequest other = (RowType_LoyaltyAccountRequest) obj;
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
            ((this.CLIENT_ID==null && other.getCLIENT_ID()==null) || 
             (this.CLIENT_ID!=null &&
              this.CLIENT_ID.equals(other.getCLIENT_ID()))) &&
            ((this.LY_INVITED_BY_ID==null && other.getLY_INVITED_BY_ID()==null) || 
             (this.LY_INVITED_BY_ID!=null &&
              this.LY_INVITED_BY_ID.equals(other.getLY_INVITED_BY_ID())));
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
        if (getCLIENT_ID() != null) {
            _hashCode += getCLIENT_ID().hashCode();
        }
        if (getLY_INVITED_BY_ID() != null) {
            _hashCode += getLY_INVITED_BY_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_LoyaltyAccountRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_LoyaltyAccountRequest"));
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
        elemField.setFieldName("CLIENT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLIENT_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LY_INVITED_BY_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LY_INVITED_BY_ID"));
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
