/**
 * RowType_AssignPIN_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_AssignPIN_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String EXPIRY;

    private java.lang.String PINBLOCK;

    private java.lang.String STAN;

    private java.util.Calendar TRANSACTION_DATETIME;

    private java.lang.String CARD_SECURE_CODE;

    private java.lang.String ACQUIRER_ID;

    private java.lang.String TERMINAL_ID;

    private java.lang.String MERCHANT_ID;

    private java.lang.String TERMINAL_LOCATION;

    public RowType_AssignPIN_Request() {
    }

    public RowType_AssignPIN_Request(
           java.lang.String CARD,
           java.lang.String EXPIRY,
           java.lang.String PINBLOCK,
           java.lang.String STAN,
           java.util.Calendar TRANSACTION_DATETIME,
           java.lang.String CARD_SECURE_CODE,
           java.lang.String ACQUIRER_ID,
           java.lang.String TERMINAL_ID,
           java.lang.String MERCHANT_ID,
           java.lang.String TERMINAL_LOCATION) {
           this.CARD = CARD;
           this.EXPIRY = EXPIRY;
           this.PINBLOCK = PINBLOCK;
           this.STAN = STAN;
           this.TRANSACTION_DATETIME = TRANSACTION_DATETIME;
           this.CARD_SECURE_CODE = CARD_SECURE_CODE;
           this.ACQUIRER_ID = ACQUIRER_ID;
           this.TERMINAL_ID = TERMINAL_ID;
           this.MERCHANT_ID = MERCHANT_ID;
           this.TERMINAL_LOCATION = TERMINAL_LOCATION;
    }


    /**
     * Gets the CARD value for this RowType_AssignPIN_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_AssignPIN_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the EXPIRY value for this RowType_AssignPIN_Request.
     * 
     * @return EXPIRY
     */
    public java.lang.String getEXPIRY() {
        return EXPIRY;
    }


    /**
     * Sets the EXPIRY value for this RowType_AssignPIN_Request.
     * 
     * @param EXPIRY
     */
    public void setEXPIRY(java.lang.String EXPIRY) {
        this.EXPIRY = EXPIRY;
    }


    /**
     * Gets the PINBLOCK value for this RowType_AssignPIN_Request.
     * 
     * @return PINBLOCK
     */
    public java.lang.String getPINBLOCK() {
        return PINBLOCK;
    }


    /**
     * Sets the PINBLOCK value for this RowType_AssignPIN_Request.
     * 
     * @param PINBLOCK
     */
    public void setPINBLOCK(java.lang.String PINBLOCK) {
        this.PINBLOCK = PINBLOCK;
    }


    /**
     * Gets the STAN value for this RowType_AssignPIN_Request.
     * 
     * @return STAN
     */
    public java.lang.String getSTAN() {
        return STAN;
    }


    /**
     * Sets the STAN value for this RowType_AssignPIN_Request.
     * 
     * @param STAN
     */
    public void setSTAN(java.lang.String STAN) {
        this.STAN = STAN;
    }


    /**
     * Gets the TRANSACTION_DATETIME value for this RowType_AssignPIN_Request.
     * 
     * @return TRANSACTION_DATETIME
     */
    public java.util.Calendar getTRANSACTION_DATETIME() {
        return TRANSACTION_DATETIME;
    }


    /**
     * Sets the TRANSACTION_DATETIME value for this RowType_AssignPIN_Request.
     * 
     * @param TRANSACTION_DATETIME
     */
    public void setTRANSACTION_DATETIME(java.util.Calendar TRANSACTION_DATETIME) {
        this.TRANSACTION_DATETIME = TRANSACTION_DATETIME;
    }


    /**
     * Gets the CARD_SECURE_CODE value for this RowType_AssignPIN_Request.
     * 
     * @return CARD_SECURE_CODE
     */
    public java.lang.String getCARD_SECURE_CODE() {
        return CARD_SECURE_CODE;
    }


    /**
     * Sets the CARD_SECURE_CODE value for this RowType_AssignPIN_Request.
     * 
     * @param CARD_SECURE_CODE
     */
    public void setCARD_SECURE_CODE(java.lang.String CARD_SECURE_CODE) {
        this.CARD_SECURE_CODE = CARD_SECURE_CODE;
    }


    /**
     * Gets the ACQUIRER_ID value for this RowType_AssignPIN_Request.
     * 
     * @return ACQUIRER_ID
     */
    public java.lang.String getACQUIRER_ID() {
        return ACQUIRER_ID;
    }


    /**
     * Sets the ACQUIRER_ID value for this RowType_AssignPIN_Request.
     * 
     * @param ACQUIRER_ID
     */
    public void setACQUIRER_ID(java.lang.String ACQUIRER_ID) {
        this.ACQUIRER_ID = ACQUIRER_ID;
    }


    /**
     * Gets the TERMINAL_ID value for this RowType_AssignPIN_Request.
     * 
     * @return TERMINAL_ID
     */
    public java.lang.String getTERMINAL_ID() {
        return TERMINAL_ID;
    }


    /**
     * Sets the TERMINAL_ID value for this RowType_AssignPIN_Request.
     * 
     * @param TERMINAL_ID
     */
    public void setTERMINAL_ID(java.lang.String TERMINAL_ID) {
        this.TERMINAL_ID = TERMINAL_ID;
    }


    /**
     * Gets the MERCHANT_ID value for this RowType_AssignPIN_Request.
     * 
     * @return MERCHANT_ID
     */
    public java.lang.String getMERCHANT_ID() {
        return MERCHANT_ID;
    }


    /**
     * Sets the MERCHANT_ID value for this RowType_AssignPIN_Request.
     * 
     * @param MERCHANT_ID
     */
    public void setMERCHANT_ID(java.lang.String MERCHANT_ID) {
        this.MERCHANT_ID = MERCHANT_ID;
    }


    /**
     * Gets the TERMINAL_LOCATION value for this RowType_AssignPIN_Request.
     * 
     * @return TERMINAL_LOCATION
     */
    public java.lang.String getTERMINAL_LOCATION() {
        return TERMINAL_LOCATION;
    }


    /**
     * Sets the TERMINAL_LOCATION value for this RowType_AssignPIN_Request.
     * 
     * @param TERMINAL_LOCATION
     */
    public void setTERMINAL_LOCATION(java.lang.String TERMINAL_LOCATION) {
        this.TERMINAL_LOCATION = TERMINAL_LOCATION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_AssignPIN_Request)) return false;
        RowType_AssignPIN_Request other = (RowType_AssignPIN_Request) obj;
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
            ((this.PINBLOCK==null && other.getPINBLOCK()==null) || 
             (this.PINBLOCK!=null &&
              this.PINBLOCK.equals(other.getPINBLOCK()))) &&
            ((this.STAN==null && other.getSTAN()==null) || 
             (this.STAN!=null &&
              this.STAN.equals(other.getSTAN()))) &&
            ((this.TRANSACTION_DATETIME==null && other.getTRANSACTION_DATETIME()==null) || 
             (this.TRANSACTION_DATETIME!=null &&
              this.TRANSACTION_DATETIME.equals(other.getTRANSACTION_DATETIME()))) &&
            ((this.CARD_SECURE_CODE==null && other.getCARD_SECURE_CODE()==null) || 
             (this.CARD_SECURE_CODE!=null &&
              this.CARD_SECURE_CODE.equals(other.getCARD_SECURE_CODE()))) &&
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
              this.TERMINAL_LOCATION.equals(other.getTERMINAL_LOCATION())));
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
        if (getPINBLOCK() != null) {
            _hashCode += getPINBLOCK().hashCode();
        }
        if (getSTAN() != null) {
            _hashCode += getSTAN().hashCode();
        }
        if (getTRANSACTION_DATETIME() != null) {
            _hashCode += getTRANSACTION_DATETIME().hashCode();
        }
        if (getCARD_SECURE_CODE() != null) {
            _hashCode += getCARD_SECURE_CODE().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_AssignPIN_Request.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_AssignPIN_Request"));
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
        elemField.setFieldName("PINBLOCK");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PINBLOCK"));
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
        elemField.setFieldName("TRANSACTION_DATETIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TRANSACTION_DATETIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD_SECURE_CODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD_SECURE_CODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
