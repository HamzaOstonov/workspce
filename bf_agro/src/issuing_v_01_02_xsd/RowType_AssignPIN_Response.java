/**
 * RowType_AssignPIN_Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package issuing_v_01_02_xsd;

public class RowType_AssignPIN_Response  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String EXPIRY;

    private java.lang.String STAN;

    private java.lang.String ACQUIRER_ID;

    private java.lang.String TERMINAL_ID;

    private java.lang.String MERCHANT_ID;

    private java.lang.String TERMINAL_LOCATION;

    private java.lang.String APPROVAL_CODE;

    private java.math.BigDecimal RESPONSE_CODE;

    private java.util.Calendar TRANSMISSION_DATETIME;

    private java.util.Calendar TRANSACTION_DATETIME;

    public RowType_AssignPIN_Response() {
    }

    public RowType_AssignPIN_Response(
           java.lang.String CARD,
           java.lang.String EXPIRY,
           java.lang.String STAN,
           java.lang.String ACQUIRER_ID,
           java.lang.String TERMINAL_ID,
           java.lang.String MERCHANT_ID,
           java.lang.String TERMINAL_LOCATION,
           java.lang.String APPROVAL_CODE,
           java.math.BigDecimal RESPONSE_CODE,
           java.util.Calendar TRANSMISSION_DATETIME,
           java.util.Calendar TRANSACTION_DATETIME) {
           this.CARD = CARD;
           this.EXPIRY = EXPIRY;
           this.STAN = STAN;
           this.ACQUIRER_ID = ACQUIRER_ID;
           this.TERMINAL_ID = TERMINAL_ID;
           this.MERCHANT_ID = MERCHANT_ID;
           this.TERMINAL_LOCATION = TERMINAL_LOCATION;
           this.APPROVAL_CODE = APPROVAL_CODE;
           this.RESPONSE_CODE = RESPONSE_CODE;
           this.TRANSMISSION_DATETIME = TRANSMISSION_DATETIME;
           this.TRANSACTION_DATETIME = TRANSACTION_DATETIME;
    }


    /**
     * Gets the CARD value for this RowType_AssignPIN_Response.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_AssignPIN_Response.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the EXPIRY value for this RowType_AssignPIN_Response.
     * 
     * @return EXPIRY
     */
    public java.lang.String getEXPIRY() {
        return EXPIRY;
    }


    /**
     * Sets the EXPIRY value for this RowType_AssignPIN_Response.
     * 
     * @param EXPIRY
     */
    public void setEXPIRY(java.lang.String EXPIRY) {
        this.EXPIRY = EXPIRY;
    }


    /**
     * Gets the STAN value for this RowType_AssignPIN_Response.
     * 
     * @return STAN
     */
    public java.lang.String getSTAN() {
        return STAN;
    }


    /**
     * Sets the STAN value for this RowType_AssignPIN_Response.
     * 
     * @param STAN
     */
    public void setSTAN(java.lang.String STAN) {
        this.STAN = STAN;
    }


    /**
     * Gets the ACQUIRER_ID value for this RowType_AssignPIN_Response.
     * 
     * @return ACQUIRER_ID
     */
    public java.lang.String getACQUIRER_ID() {
        return ACQUIRER_ID;
    }


    /**
     * Sets the ACQUIRER_ID value for this RowType_AssignPIN_Response.
     * 
     * @param ACQUIRER_ID
     */
    public void setACQUIRER_ID(java.lang.String ACQUIRER_ID) {
        this.ACQUIRER_ID = ACQUIRER_ID;
    }


    /**
     * Gets the TERMINAL_ID value for this RowType_AssignPIN_Response.
     * 
     * @return TERMINAL_ID
     */
    public java.lang.String getTERMINAL_ID() {
        return TERMINAL_ID;
    }


    /**
     * Sets the TERMINAL_ID value for this RowType_AssignPIN_Response.
     * 
     * @param TERMINAL_ID
     */
    public void setTERMINAL_ID(java.lang.String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }


    /**
     * Gets the MERCHANT_ID value for this RowType_AssignPIN_Response.
     * 
     * @return MERCHANT_ID
     */
    public java.lang.String getMERCHANT_ID() {
        return MERCHANT_ID;
    }


    /**
     * Sets the MERCHANT_ID value for this RowType_AssignPIN_Response.
     * 
     * @param MERCHANT_ID
     */
    public void setMERCHANT_ID(java.lang.String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }


    /**
     * Gets the TERMINAL_LOCATION value for this RowType_AssignPIN_Response.
     * 
     * @return TERMINAL_LOCATION
     */
    public java.lang.String getTERMINAL_LOCATION() {
        return TERMINAL_LOCATION;
    }


    /**
     * Sets the TERMINAL_LOCATION value for this RowType_AssignPIN_Response.
     * 
     * @param TERMINAL_LOCATION
     */
    public void setTERMINAL_LOCATION(java.lang.String TERMINAL_LOCATION) {
        this.TERMINAL_LOCATION = TERMINAL_LOCATION;
    }


    /**
     * Gets the APPROVAL_CODE value for this RowType_AssignPIN_Response.
     * 
     * @return APPROVAL_CODE
     */
    public java.lang.String getAPPROVAL_CODE() {
        return APPROVAL_CODE;
    }


    /**
     * Sets the APPROVAL_CODE value for this RowType_AssignPIN_Response.
     * 
     * @param APPROVAL_CODE
     */
    public void setAPPROVAL_CODE(java.lang.String APPROVAL_CODE) {
        this.APPROVAL_CODE = APPROVAL_CODE;
    }


    /**
     * Gets the RESPONSE_CODE value for this RowType_AssignPIN_Response.
     * 
     * @return RESPONSE_CODE
     */
    public java.math.BigDecimal getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }


    /**
     * Sets the RESPONSE_CODE value for this RowType_AssignPIN_Response.
     * 
     * @param RESPONSE_CODE
     */
    public void setRESPONSE_CODE(java.math.BigDecimal RESPONSE_CODE) {
        this.RESPONSE_CODE = RESPONSE_CODE;
    }


    /**
     * Gets the TRANSMISSION_DATETIME value for this RowType_AssignPIN_Response.
     * 
     * @return TRANSMISSION_DATETIME
     */
    public java.util.Calendar getTRANSMISSION_DATETIME() {
        return TRANSMISSION_DATETIME;
    }


    /**
     * Sets the TRANSMISSION_DATETIME value for this RowType_AssignPIN_Response.
     * 
     * @param TRANSMISSION_DATETIME
     */
    public void setTRANSMISSION_DATETIME(java.util.Calendar TRANSMISSION_DATETIME) {
        this.TRANSMISSION_DATETIME = TRANSMISSION_DATETIME;
    }


    /**
     * Gets the TRANSACTION_DATETIME value for this RowType_AssignPIN_Response.
     * 
     * @return TRANSACTION_DATETIME
     */
    public java.util.Calendar getTRANSACTION_DATETIME() {
        return TRANSACTION_DATETIME;
    }


    /**
     * Sets the TRANSACTION_DATETIME value for this RowType_AssignPIN_Response.
     * 
     * @param TRANSACTION_DATETIME
     */
    public void setTRANSACTION_DATETIME(java.util.Calendar TRANSACTION_DATETIME) {
        this.TRANSACTION_DATETIME = TRANSACTION_DATETIME;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AssignPIN_Response)) return false;
        RowType_AssignPIN_Response other = (RowType_AssignPIN_Response) obj;
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
            ((this.EXPIRY==null && other.getEXPIRY()==null) || 
             (this.EXPIRY!=null &&
              this.EXPIRY.equals(other.getEXPIRY()))) &&
            ((this.STAN==null && other.getSTAN()==null) || 
             (this.STAN!=null &&
              this.STAN.equals(other.getSTAN()))) &&
            ((this.ACQUIRER_ID==null && other.getACQUIRER_ID()==null) || 
             (this.ACQUIRER_ID!=null &&
              this.ACQUIRER_ID.equals(other.getACQUIRER_ID()))) &&
            ((this.TERMINAL_ID==null && other.getTERMINAL_ID()==null) || 
             (this.TERMINAL_ID!=null &&
              this.TERMINAL_ID.equals(other.getTERMINAL_ID()))) &&
            ((this.MERCHANT_ID==null && other.getMERCHANT_ID()==null) || 
             (this.MERCHANT_ID!=null &&
              this.MERCHANT_ID.equals(other.getMERCHANT_ID()))) &&
            ((this.TERMINAL_LOCATION==null && other.getTERMINAL_LOCATION()==null) || 
             (this.TERMINAL_LOCATION!=null &&
              this.TERMINAL_LOCATION.equals(other.getTERMINAL_LOCATION()))) &&
            ((this.APPROVAL_CODE==null && other.getAPPROVAL_CODE()==null) || 
             (this.APPROVAL_CODE!=null &&
              this.APPROVAL_CODE.equals(other.getAPPROVAL_CODE()))) &&
            ((this.RESPONSE_CODE==null && other.getRESPONSE_CODE()==null) || 
             (this.RESPONSE_CODE!=null &&
              this.RESPONSE_CODE.equals(other.getRESPONSE_CODE()))) &&
            ((this.TRANSMISSION_DATETIME==null && other.getTRANSMISSION_DATETIME()==null) || 
             (this.TRANSMISSION_DATETIME!=null &&
              this.TRANSMISSION_DATETIME.equals(other.getTRANSMISSION_DATETIME()))) &&
            ((this.TRANSACTION_DATETIME==null && other.getTRANSACTION_DATETIME()==null) || 
             (this.TRANSACTION_DATETIME!=null &&
              this.TRANSACTION_DATETIME.equals(other.getTRANSACTION_DATETIME())));
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
        if (getEXPIRY() != null) {
            _hashCode += getEXPIRY().hashCode();
        }
        if (getSTAN() != null) {
            _hashCode += getSTAN().hashCode();
        }
        if (getACQUIRER_ID() != null) {
            _hashCode += getACQUIRER_ID().hashCode();
        }
        if (getTERMINAL_ID() != null) {
            _hashCode += getTERMINAL_ID().hashCode();
        }
        if (getMERCHANT_ID() != null) {
            _hashCode += getMERCHANT_ID().hashCode();
        }
        if (getTERMINAL_LOCATION() != null) {
            _hashCode += getTERMINAL_LOCATION().hashCode();
        }
        if (getAPPROVAL_CODE() != null) {
            _hashCode += getAPPROVAL_CODE().hashCode();
        }
        if (getRESPONSE_CODE() != null) {
            _hashCode += getRESPONSE_CODE().hashCode();
        }
        if (getTRANSMISSION_DATETIME() != null) {
            _hashCode += getTRANSMISSION_DATETIME().hashCode();
        }
        if (getTRANSACTION_DATETIME() != null) {
            _hashCode += getTRANSACTION_DATETIME().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_AssignPIN_Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AssignPIN_Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXPIRY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXPIRY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STAN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STAN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACQUIRER_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ACQUIRER_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TERMINAL_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TERMINAL_ID"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TERMINAL_LOCATION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TERMINAL_LOCATION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APPROVAL_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "APPROVAL_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPONSE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RESPONSE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRANSMISSION_DATETIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRANSMISSION_DATETIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRANSACTION_DATETIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRANSACTION_DATETIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
