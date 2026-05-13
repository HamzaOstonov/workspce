/**
 * RowType_TransactionHist_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_TransactionHist_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String CARD_ACCT;

    private java.lang.String ACCNT_CCY;

    private java.util.Calendar BEGIN_DATE;

    private java.util.Calendar END_DATE;

    private java.lang.String BANK_C;

    private java.lang.String GROUPC;

    private java.lang.String LOCKING_FLAG;

    private java.lang.String SPEC_CARD_TRX;

    private java.lang.String SPEC_ACCNT_TRX;

    public RowType_TransactionHist_Request() {
    }

    public RowType_TransactionHist_Request(
           java.lang.String CARD,
           java.lang.String CARD_ACCT,
           java.lang.String ACCNT_CCY,
           java.util.Calendar BEGIN_DATE,
           java.util.Calendar END_DATE,
           java.lang.String BANK_C,
           java.lang.String GROUPC,
           java.lang.String LOCKING_FLAG,
           java.lang.String SPEC_CARD_TRX,
           java.lang.String SPEC_ACCNT_TRX) {
           this.CARD = CARD;
           this.CARD_ACCT = CARD_ACCT;
           this.ACCNT_CCY = ACCNT_CCY;
           this.BEGIN_DATE = BEGIN_DATE;
           this.END_DATE = END_DATE;
           this.BANK_C = BANK_C;
           this.GROUPC = GROUPC;
           this.LOCKING_FLAG = LOCKING_FLAG;
           this.SPEC_CARD_TRX = SPEC_CARD_TRX;
           this.SPEC_ACCNT_TRX = SPEC_ACCNT_TRX;
    }


    /**
     * Gets the CARD value for this RowType_TransactionHist_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_TransactionHist_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_TransactionHist_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_TransactionHist_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the ACCNT_CCY value for this RowType_TransactionHist_Request.
     * 
     * @return ACCNT_CCY
     */
    public java.lang.String getACCNT_CCY() {
        return ACCNT_CCY;
    }


    /**
     * Sets the ACCNT_CCY value for this RowType_TransactionHist_Request.
     * 
     * @param ACCNT_CCY
     */
    public void setACCNT_CCY(java.lang.String ACCNT_CCY) {
        this.ACCNT_CCY = ACCNT_CCY;
    }


    /**
     * Gets the BEGIN_DATE value for this RowType_TransactionHist_Request.
     * 
     * @return BEGIN_DATE
     */
    public java.util.Calendar getBEGIN_DATE() {
        return BEGIN_DATE;
    }


    /**
     * Sets the BEGIN_DATE value for this RowType_TransactionHist_Request.
     * 
     * @param BEGIN_DATE
     */
    public void setBEGIN_DATE(java.util.Calendar BEGIN_DATE) {
        this.BEGIN_DATE = BEGIN_DATE;
    }


    /**
     * Gets the END_DATE value for this RowType_TransactionHist_Request.
     * 
     * @return END_DATE
     */
    public java.util.Calendar getEND_DATE() {
        return END_DATE;
    }


    /**
     * Sets the END_DATE value for this RowType_TransactionHist_Request.
     * 
     * @param END_DATE
     */
    public void setEND_DATE(java.util.Calendar END_DATE) {
        this.END_DATE = END_DATE;
    }


    /**
     * Gets the BANK_C value for this RowType_TransactionHist_Request.
     * 
     * @return BANK_C
     */
    public java.lang.String getBANK_C() {
        return BANK_C;
    }


    /**
     * Sets the BANK_C value for this RowType_TransactionHist_Request.
     * 
     * @param BANK_C
     */
    public void setBANK_C(java.lang.String BANK_C) {
        this.BANK_C = BANK_C;
    }


    /**
     * Gets the GROUPC value for this RowType_TransactionHist_Request.
     * 
     * @return GROUPC
     */
    public java.lang.String getGROUPC() {
        return GROUPC;
    }


    /**
     * Sets the GROUPC value for this RowType_TransactionHist_Request.
     * 
     * @param GROUPC
     */
    public void setGROUPC(java.lang.String GROUPC) {
        this.GROUPC = GROUPC;
    }


    /**
     * Gets the LOCKING_FLAG value for this RowType_TransactionHist_Request.
     * 
     * @return LOCKING_FLAG
     */
    public java.lang.String getLOCKING_FLAG() {
        return LOCKING_FLAG;
    }


    /**
     * Sets the LOCKING_FLAG value for this RowType_TransactionHist_Request.
     * 
     * @param LOCKING_FLAG
     */
    public void setLOCKING_FLAG(java.lang.String LOCKING_FLAG) {
        this.LOCKING_FLAG = LOCKING_FLAG;
    }


    /**
     * Gets the SPEC_CARD_TRX value for this RowType_TransactionHist_Request.
     * 
     * @return SPEC_CARD_TRX
     */
    public java.lang.String getSPEC_CARD_TRX() {
        return SPEC_CARD_TRX;
    }


    /**
     * Sets the SPEC_CARD_TRX value for this RowType_TransactionHist_Request.
     * 
     * @param SPEC_CARD_TRX
     */
    public void setSPEC_CARD_TRX(java.lang.String SPEC_CARD_TRX) {
        this.SPEC_CARD_TRX = SPEC_CARD_TRX;
    }


    /**
     * Gets the SPEC_ACCNT_TRX value for this RowType_TransactionHist_Request.
     * 
     * @return SPEC_ACCNT_TRX
     */
    public java.lang.String getSPEC_ACCNT_TRX() {
        return SPEC_ACCNT_TRX;
    }


    /**
     * Sets the SPEC_ACCNT_TRX value for this RowType_TransactionHist_Request.
     * 
     * @param SPEC_ACCNT_TRX
     */
    public void setSPEC_ACCNT_TRX(java.lang.String SPEC_ACCNT_TRX) {
        this.SPEC_ACCNT_TRX = SPEC_ACCNT_TRX;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_TransactionHist_Request)) return false;
        RowType_TransactionHist_Request other = (RowType_TransactionHist_Request) obj;
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
            ((this.CARD_ACCT==null && other.getCARD_ACCT()==null) || 
             (this.CARD_ACCT!=null &&
              this.CARD_ACCT.equals(other.getCARD_ACCT()))) &&
            ((this.ACCNT_CCY==null && other.getACCNT_CCY()==null) || 
             (this.ACCNT_CCY!=null &&
              this.ACCNT_CCY.equals(other.getACCNT_CCY()))) &&
            ((this.BEGIN_DATE==null && other.getBEGIN_DATE()==null) || 
             (this.BEGIN_DATE!=null &&
              this.BEGIN_DATE.equals(other.getBEGIN_DATE()))) &&
            ((this.END_DATE==null && other.getEND_DATE()==null) || 
             (this.END_DATE!=null &&
              this.END_DATE.equals(other.getEND_DATE()))) &&
            ((this.BANK_C==null && other.getBANK_C()==null) || 
             (this.BANK_C!=null &&
              this.BANK_C.equals(other.getBANK_C()))) &&
            ((this.GROUPC==null && other.getGROUPC()==null) || 
             (this.GROUPC!=null &&
              this.GROUPC.equals(other.getGROUPC()))) &&
            ((this.LOCKING_FLAG==null && other.getLOCKING_FLAG()==null) || 
             (this.LOCKING_FLAG!=null &&
              this.LOCKING_FLAG.equals(other.getLOCKING_FLAG()))) &&
            ((this.SPEC_CARD_TRX==null && other.getSPEC_CARD_TRX()==null) || 
             (this.SPEC_CARD_TRX!=null &&
              this.SPEC_CARD_TRX.equals(other.getSPEC_CARD_TRX()))) &&
            ((this.SPEC_ACCNT_TRX==null && other.getSPEC_ACCNT_TRX()==null) || 
             (this.SPEC_ACCNT_TRX!=null &&
              this.SPEC_ACCNT_TRX.equals(other.getSPEC_ACCNT_TRX())));
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
        if (getCARD_ACCT() != null) {
            _hashCode += getCARD_ACCT().hashCode();
        }
        if (getACCNT_CCY() != null) {
            _hashCode += getACCNT_CCY().hashCode();
        }
        if (getBEGIN_DATE() != null) {
            _hashCode += getBEGIN_DATE().hashCode();
        }
        if (getEND_DATE() != null) {
            _hashCode += getEND_DATE().hashCode();
        }
        if (getBANK_C() != null) {
            _hashCode += getBANK_C().hashCode();
        }
        if (getGROUPC() != null) {
            _hashCode += getGROUPC().hashCode();
        }
        if (getLOCKING_FLAG() != null) {
            _hashCode += getLOCKING_FLAG().hashCode();
        }
        if (getSPEC_CARD_TRX() != null) {
            _hashCode += getSPEC_CARD_TRX().hashCode();
        }
        if (getSPEC_ACCNT_TRX() != null) {
            _hashCode += getSPEC_ACCNT_TRX().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_TransactionHist_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_TransactionHist_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_ACCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_ACCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACCNT_CCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACCNT_CCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BEGIN_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BEGIN_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("END_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "END_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BANK_C");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BANK_C"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GROUPC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GROUPC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOCKING_FLAG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LOCKING_FLAG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SPEC_CARD_TRX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SPEC_CARD_TRX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SPEC_ACCNT_TRX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SPEC_ACCNT_TRX"));
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
