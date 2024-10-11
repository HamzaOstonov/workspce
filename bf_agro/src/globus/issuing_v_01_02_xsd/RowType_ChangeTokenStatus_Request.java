/**
 * RowType_ChangeTokenStatus_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ChangeTokenStatus_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String TOKEN;

    private java.lang.String TOKEN_REF;

    private java.lang.String NEW_STATUS;

    private java.lang.String REASON;

    public RowType_ChangeTokenStatus_Request() {
    }

    public RowType_ChangeTokenStatus_Request(
           java.lang.String CARD,
           java.lang.String TOKEN,
           java.lang.String TOKEN_REF,
           java.lang.String NEW_STATUS,
           java.lang.String REASON) {
           this.CARD = CARD;
           this.TOKEN = TOKEN;
           this.TOKEN_REF = TOKEN_REF;
           this.NEW_STATUS = NEW_STATUS;
           this.REASON = REASON;
    }


    /**
     * Gets the CARD value for this RowType_ChangeTokenStatus_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ChangeTokenStatus_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the TOKEN value for this RowType_ChangeTokenStatus_Request.
     * 
     * @return TOKEN
     */
    public java.lang.String getTOKEN() {
        return TOKEN;
    }


    /**
     * Sets the TOKEN value for this RowType_ChangeTokenStatus_Request.
     * 
     * @param TOKEN
     */
    public void setTOKEN(java.lang.String TOKEN) {
        this.TOKEN = TOKEN;
    }


    /**
     * Gets the TOKEN_REF value for this RowType_ChangeTokenStatus_Request.
     * 
     * @return TOKEN_REF
     */
    public java.lang.String getTOKEN_REF() {
        return TOKEN_REF;
    }


    /**
     * Sets the TOKEN_REF value for this RowType_ChangeTokenStatus_Request.
     * 
     * @param TOKEN_REF
     */
    public void setTOKEN_REF(java.lang.String TOKEN_REF) {
        this.TOKEN_REF = TOKEN_REF;
    }


    /**
     * Gets the NEW_STATUS value for this RowType_ChangeTokenStatus_Request.
     * 
     * @return NEW_STATUS
     */
    public java.lang.String getNEW_STATUS() {
        return NEW_STATUS;
    }


    /**
     * Sets the NEW_STATUS value for this RowType_ChangeTokenStatus_Request.
     * 
     * @param NEW_STATUS
     */
    public void setNEW_STATUS(java.lang.String NEW_STATUS) {
        this.NEW_STATUS = NEW_STATUS;
    }


    /**
     * Gets the REASON value for this RowType_ChangeTokenStatus_Request.
     * 
     * @return REASON
     */
    public java.lang.String getREASON() {
        return REASON;
    }


    /**
     * Sets the REASON value for this RowType_ChangeTokenStatus_Request.
     * 
     * @param REASON
     */
    public void setREASON(java.lang.String REASON) {
        this.REASON = REASON;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ChangeTokenStatus_Request)) return false;
        RowType_ChangeTokenStatus_Request other = (RowType_ChangeTokenStatus_Request) obj;
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
            ((this.TOKEN==null && other.getTOKEN()==null) || 
             (this.TOKEN!=null &&
              this.TOKEN.equals(other.getTOKEN()))) &&
            ((this.TOKEN_REF==null && other.getTOKEN_REF()==null) || 
             (this.TOKEN_REF!=null &&
              this.TOKEN_REF.equals(other.getTOKEN_REF()))) &&
            ((this.NEW_STATUS==null && other.getNEW_STATUS()==null) || 
             (this.NEW_STATUS!=null &&
              this.NEW_STATUS.equals(other.getNEW_STATUS()))) &&
            ((this.REASON==null && other.getREASON()==null) || 
             (this.REASON!=null &&
              this.REASON.equals(other.getREASON())));
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
        if (getTOKEN() != null) {
            _hashCode += getTOKEN().hashCode();
        }
        if (getTOKEN_REF() != null) {
            _hashCode += getTOKEN_REF().hashCode();
        }
        if (getNEW_STATUS() != null) {
            _hashCode += getNEW_STATUS().hashCode();
        }
        if (getREASON() != null) {
            _hashCode += getREASON().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ChangeTokenStatus_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ChangeTokenStatus_Request"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOKEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TOKEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TOKEN_REF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TOKEN_REF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_STATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_STATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REASON");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REASON"));
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
