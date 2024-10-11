/**
 * RowType_CalculateInstallmentPlanNew_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_CalculateInstallmentPlanNew_Request  implements java.io.Serializable {
    private java.lang.String CARD_ACCT;

    private java.lang.String CCY;

    private java.lang.String METHOD;

    private java.math.BigDecimal PERIOD;

    private java.math.BigDecimal RATE;

    private java.math.BigDecimal AMOUNT;

    public RowType_CalculateInstallmentPlanNew_Request() {
    }

    public RowType_CalculateInstallmentPlanNew_Request(
           java.lang.String CARD_ACCT,
           java.lang.String CCY,
           java.lang.String METHOD,
           java.math.BigDecimal PERIOD,
           java.math.BigDecimal RATE,
           java.math.BigDecimal AMOUNT) {
           this.CARD_ACCT = CARD_ACCT;
           this.CCY = CCY;
           this.METHOD = METHOD;
           this.PERIOD = PERIOD;
           this.RATE = RATE;
           this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CCY value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }


    /**
     * Gets the METHOD value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @return METHOD
     */
    public java.lang.String getMETHOD() {
        return METHOD;
    }


    /**
     * Sets the METHOD value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @param METHOD
     */
    public void setMETHOD(java.lang.String METHOD) {
        this.METHOD = METHOD;
    }


    /**
     * Gets the PERIOD value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @return PERIOD
     */
    public java.math.BigDecimal getPERIOD() {
        return PERIOD;
    }


    /**
     * Sets the PERIOD value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @param PERIOD
     */
    public void setPERIOD(java.math.BigDecimal PERIOD) {
        this.PERIOD = PERIOD;
    }


    /**
     * Gets the RATE value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @return RATE
     */
    public java.math.BigDecimal getRATE() {
        return RATE;
    }


    /**
     * Sets the RATE value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @param RATE
     */
    public void setRATE(java.math.BigDecimal RATE) {
        this.RATE = RATE;
    }


    /**
     * Gets the AMOUNT value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this RowType_CalculateInstallmentPlanNew_Request.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_CalculateInstallmentPlanNew_Request)) return false;
        RowType_CalculateInstallmentPlanNew_Request other = (RowType_CalculateInstallmentPlanNew_Request) obj;
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
            ((this.METHOD==null && other.getMETHOD()==null) || 
             (this.METHOD!=null &&
              this.METHOD.equals(other.getMETHOD()))) &&
            ((this.PERIOD==null && other.getPERIOD()==null) || 
             (this.PERIOD!=null &&
              this.PERIOD.equals(other.getPERIOD()))) &&
            ((this.RATE==null && other.getRATE()==null) || 
             (this.RATE!=null &&
              this.RATE.equals(other.getRATE()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT())));
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
        if (getMETHOD() != null) {
            _hashCode += getMETHOD().hashCode();
        }
        if (getPERIOD() != null) {
            _hashCode += getPERIOD().hashCode();
        }
        if (getRATE() != null) {
            _hashCode += getRATE().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_CalculateInstallmentPlanNew_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_CalculateInstallmentPlanNew_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("METHOD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "METHOD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERIOD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PERIOD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
