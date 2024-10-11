/**
 * RowType_GetInstallmentConditionsForRequest_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_GetInstallmentConditionsForRequest_Request  implements java.io.Serializable {
    private java.lang.String CARD_ACCT;

    private java.lang.String CCY;

    private java.math.BigDecimal AMOUNT;

    private java.lang.String MCC_CODE;

    private java.lang.String MERCHANT_ID;

    public RowType_GetInstallmentConditionsForRequest_Request() {
    }

    public RowType_GetInstallmentConditionsForRequest_Request(
           java.lang.String CARD_ACCT,
           java.lang.String CCY,
           java.math.BigDecimal AMOUNT,
           java.lang.String MCC_CODE,
           java.lang.String MERCHANT_ID) {
           this.CARD_ACCT = CARD_ACCT;
           this.CCY = CCY;
           this.AMOUNT = AMOUNT;
           this.MCC_CODE = MCC_CODE;
           this.MERCHANT_ID = MERCHANT_ID;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CCY value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }


    /**
     * Gets the AMOUNT value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the MCC_CODE value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @return MCC_CODE
     */
    public java.lang.String getMCC_CODE() {
        return MCC_CODE;
    }


    /**
     * Sets the MCC_CODE value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @param MCC_CODE
     */
    public void setMCC_CODE(java.lang.String MCC_CODE) {
        this.MCC_CODE = MCC_CODE;
    }


    /**
     * Gets the MERCHANT_ID value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @return MERCHANT_ID
     */
    public java.lang.String getMERCHANT_ID() {
        return MERCHANT_ID;
    }


    /**
     * Sets the MERCHANT_ID value for this RowType_GetInstallmentConditionsForRequest_Request.
     * 
     * @param MERCHANT_ID
     */
    public void setMERCHANT_ID(java.lang.String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_GetInstallmentConditionsForRequest_Request)) return false;
        RowType_GetInstallmentConditionsForRequest_Request other = (RowType_GetInstallmentConditionsForRequest_Request) obj;
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
              this.CCY.equals(other.getCCY()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT()))) &&
            ((this.MCC_CODE==null && other.getMCC_CODE()==null) || 
             (this.MCC_CODE!=null &&
              this.MCC_CODE.equals(other.getMCC_CODE()))) &&
            ((this.MERCHANT_ID==null && other.getMERCHANT_ID()==null) || 
             (this.MERCHANT_ID!=null &&
              this.MERCHANT_ID.equals(other.getMERCHANT_ID())));
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
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        if (getMCC_CODE() != null) {
            _hashCode += getMCC_CODE().hashCode();
        }
        if (getMERCHANT_ID() != null) {
            _hashCode += getMERCHANT_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_GetInstallmentConditionsForRequest_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetInstallmentConditionsForRequest_Request"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MCC_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MCC_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MERCHANT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MERCHANT_ID"));
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
