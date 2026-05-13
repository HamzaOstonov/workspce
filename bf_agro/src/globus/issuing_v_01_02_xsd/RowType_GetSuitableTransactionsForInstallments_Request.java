/**
 * RowType_GetSuitableTransactionsForInstallments_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_GetSuitableTransactionsForInstallments_Request  implements java.io.Serializable {
    private java.lang.String CARD_ACCT;

    private java.lang.String CCY;

    private java.lang.String CODE;

    private java.lang.String OPEN_MODE;

    private java.util.Calendar DATE_FROM;

    private java.util.Calendar DATE_TO;

    private java.lang.String MCC_CODE;

    private java.lang.String TRAN_TYPE;

    public RowType_GetSuitableTransactionsForInstallments_Request() {
    }

    public RowType_GetSuitableTransactionsForInstallments_Request(
           java.lang.String CARD_ACCT,
           java.lang.String CCY,
           java.lang.String CODE,
           java.lang.String OPEN_MODE,
           java.util.Calendar DATE_FROM,
           java.util.Calendar DATE_TO,
           java.lang.String MCC_CODE,
           java.lang.String TRAN_TYPE) {
           this.CARD_ACCT = CARD_ACCT;
           this.CCY = CCY;
           this.CODE = CODE;
           this.OPEN_MODE = OPEN_MODE;
           this.DATE_FROM = DATE_FROM;
           this.DATE_TO = DATE_TO;
           this.MCC_CODE = MCC_CODE;
           this.TRAN_TYPE = TRAN_TYPE;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CCY value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }


    /**
     * Gets the CODE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return CODE
     */
    public java.lang.String getCODE() {
        return CODE;
    }


    /**
     * Sets the CODE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param CODE
     */
    public void setCODE(java.lang.String CODE) {
        this.CODE = CODE;
    }


    /**
     * Gets the OPEN_MODE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return OPEN_MODE
     */
    public java.lang.String getOPEN_MODE() {
        return OPEN_MODE;
    }


    /**
     * Sets the OPEN_MODE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param OPEN_MODE
     */
    public void setOPEN_MODE(java.lang.String OPEN_MODE) {
        this.OPEN_MODE = OPEN_MODE;
    }


    /**
     * Gets the DATE_FROM value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return DATE_FROM
     */
    public java.util.Calendar getDATE_FROM() {
        return DATE_FROM;
    }


    /**
     * Sets the DATE_FROM value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param DATE_FROM
     */
    public void setDATE_FROM(java.util.Calendar DATE_FROM) {
        this.DATE_FROM = DATE_FROM;
    }


    /**
     * Gets the DATE_TO value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return DATE_TO
     */
    public java.util.Calendar getDATE_TO() {
        return DATE_TO;
    }


    /**
     * Sets the DATE_TO value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param DATE_TO
     */
    public void setDATE_TO(java.util.Calendar DATE_TO) {
        this.DATE_TO = DATE_TO;
    }


    /**
     * Gets the MCC_CODE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return MCC_CODE
     */
    public java.lang.String getMCC_CODE() {
        return MCC_CODE;
    }


    /**
     * Sets the MCC_CODE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param MCC_CODE
     */
    public void setMCC_CODE(java.lang.String MCC_CODE) {
        this.MCC_CODE = MCC_CODE;
    }


    /**
     * Gets the TRAN_TYPE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @return TRAN_TYPE
     */
    public java.lang.String getTRAN_TYPE() {
        return TRAN_TYPE;
    }


    /**
     * Sets the TRAN_TYPE value for this RowType_GetSuitableTransactionsForInstallments_Request.
     * 
     * @param TRAN_TYPE
     */
    public void setTRAN_TYPE(java.lang.String TRAN_TYPE) {
        this.TRAN_TYPE = TRAN_TYPE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_GetSuitableTransactionsForInstallments_Request)) return false;
        RowType_GetSuitableTransactionsForInstallments_Request other = (RowType_GetSuitableTransactionsForInstallments_Request) obj;
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
            ((this.CODE==null && other.getCODE()==null) || 
             (this.CODE!=null &&
              this.CODE.equals(other.getCODE()))) &&
            ((this.OPEN_MODE==null && other.getOPEN_MODE()==null) || 
             (this.OPEN_MODE!=null &&
              this.OPEN_MODE.equals(other.getOPEN_MODE()))) &&
            ((this.DATE_FROM==null && other.getDATE_FROM()==null) || 
             (this.DATE_FROM!=null &&
              this.DATE_FROM.equals(other.getDATE_FROM()))) &&
            ((this.DATE_TO==null && other.getDATE_TO()==null) || 
             (this.DATE_TO!=null &&
              this.DATE_TO.equals(other.getDATE_TO()))) &&
            ((this.MCC_CODE==null && other.getMCC_CODE()==null) || 
             (this.MCC_CODE!=null &&
              this.MCC_CODE.equals(other.getMCC_CODE()))) &&
            ((this.TRAN_TYPE==null && other.getTRAN_TYPE()==null) || 
             (this.TRAN_TYPE!=null &&
              this.TRAN_TYPE.equals(other.getTRAN_TYPE())));
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
        if (getCODE() != null) {
            _hashCode += getCODE().hashCode();
        }
        if (getOPEN_MODE() != null) {
            _hashCode += getOPEN_MODE().hashCode();
        }
        if (getDATE_FROM() != null) {
            _hashCode += getDATE_FROM().hashCode();
        }
        if (getDATE_TO() != null) {
            _hashCode += getDATE_TO().hashCode();
        }
        if (getMCC_CODE() != null) {
            _hashCode += getMCC_CODE().hashCode();
        }
        if (getTRAN_TYPE() != null) {
            _hashCode += getTRAN_TYPE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_GetSuitableTransactionsForInstallments_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_GetSuitableTransactionsForInstallments_Request"));
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
        elemField.setFieldName("CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATE_FROM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATE_FROM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DATE_TO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DATE_TO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("TRAN_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRAN_TYPE"));
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
