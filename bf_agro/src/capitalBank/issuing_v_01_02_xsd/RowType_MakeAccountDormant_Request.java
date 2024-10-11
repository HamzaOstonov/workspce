/**
 * RowType_MakeAccountDormant_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package capitalBank.issuing_v_01_02_xsd;

public class RowType_MakeAccountDormant_Request  implements java.io.Serializable {
    private java.lang.String CARD_ACCT;

    private java.lang.String CCY;

    private java.lang.String COMMENT;

    private java.lang.String FEE_CALC;

    private java.math.BigDecimal INSTL_INTER;

    public RowType_MakeAccountDormant_Request() {
    }

    public RowType_MakeAccountDormant_Request(
           java.lang.String CARD_ACCT,
           java.lang.String CCY,
           java.lang.String COMMENT,
           java.lang.String FEE_CALC,
           java.math.BigDecimal INSTL_INTER) {
           this.CARD_ACCT = CARD_ACCT;
           this.CCY = CCY;
           this.COMMENT = COMMENT;
           this.FEE_CALC = FEE_CALC;
           this.INSTL_INTER = INSTL_INTER;
    }


    /**
     * Gets the CARD_ACCT value for this RowType_MakeAccountDormant_Request.
     * 
     * @return CARD_ACCT
     */
    public java.lang.String getCARD_ACCT() {
        return CARD_ACCT;
    }


    /**
     * Sets the CARD_ACCT value for this RowType_MakeAccountDormant_Request.
     * 
     * @param CARD_ACCT
     */
    public void setCARD_ACCT(java.lang.String CARD_ACCT) {
        this.CARD_ACCT = CARD_ACCT;
    }


    /**
     * Gets the CCY value for this RowType_MakeAccountDormant_Request.
     * 
     * @return CCY
     */
    public java.lang.String getCCY() {
        return CCY;
    }


    /**
     * Sets the CCY value for this RowType_MakeAccountDormant_Request.
     * 
     * @param CCY
     */
    public void setCCY(java.lang.String CCY) {
        this.CCY = CCY;
    }


    /**
     * Gets the COMMENT value for this RowType_MakeAccountDormant_Request.
     * 
     * @return COMMENT
     */
    public java.lang.String getCOMMENT() {
        return COMMENT;
    }


    /**
     * Sets the COMMENT value for this RowType_MakeAccountDormant_Request.
     * 
     * @param COMMENT
     */
    public void setCOMMENT(java.lang.String COMMENT) {
        this.COMMENT = COMMENT;
    }


    /**
     * Gets the FEE_CALC value for this RowType_MakeAccountDormant_Request.
     * 
     * @return FEE_CALC
     */
    public java.lang.String getFEE_CALC() {
        return FEE_CALC;
    }


    /**
     * Sets the FEE_CALC value for this RowType_MakeAccountDormant_Request.
     * 
     * @param FEE_CALC
     */
    public void setFEE_CALC(java.lang.String FEE_CALC) {
        this.FEE_CALC = FEE_CALC;
    }


    /**
     * Gets the INSTL_INTER value for this RowType_MakeAccountDormant_Request.
     * 
     * @return INSTL_INTER
     */
    public java.math.BigDecimal getINSTL_INTER() {
        return INSTL_INTER;
    }


    /**
     * Sets the INSTL_INTER value for this RowType_MakeAccountDormant_Request.
     * 
     * @param INSTL_INTER
     */
    public void setINSTL_INTER(java.math.BigDecimal INSTL_INTER) {
        this.INSTL_INTER = INSTL_INTER;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_MakeAccountDormant_Request)) return false;
        RowType_MakeAccountDormant_Request other = (RowType_MakeAccountDormant_Request) obj;
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
            ((this.COMMENT==null && other.getCOMMENT()==null) || 
             (this.COMMENT!=null &&
              this.COMMENT.equals(other.getCOMMENT()))) &&
            ((this.FEE_CALC==null && other.getFEE_CALC()==null) || 
             (this.FEE_CALC!=null &&
              this.FEE_CALC.equals(other.getFEE_CALC()))) &&
            ((this.INSTL_INTER==null && other.getINSTL_INTER()==null) || 
             (this.INSTL_INTER!=null &&
              this.INSTL_INTER.equals(other.getINSTL_INTER())));
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
        if (getCOMMENT() != null) {
            _hashCode += getCOMMENT().hashCode();
        }
        if (getFEE_CALC() != null) {
            _hashCode += getFEE_CALC().hashCode();
        }
        if (getINSTL_INTER() != null) {
            _hashCode += getINSTL_INTER().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_MakeAccountDormant_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_MakeAccountDormant_Request"));
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
        elemField.setFieldName("COMMENT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COMMENT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FEE_CALC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FEE_CALC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INSTL_INTER");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INSTL_INTER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
