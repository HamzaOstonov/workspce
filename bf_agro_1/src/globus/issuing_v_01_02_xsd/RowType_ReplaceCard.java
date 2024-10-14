/**
 * RowType_ReplaceCard.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_ReplaceCard  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String NEW_CARD;

    private java.math.BigInteger NO_CHARGE;

    private java.math.BigInteger NO_EMB_SESSION;

    private java.util.Calendar NEW_EXPIRY;

    private java.lang.String NEW_RISK_LEVEL;

    private java.math.BigDecimal CHIP_ID;

    private java.math.BigDecimal DESIGN_ID;

    private java.lang.String OFF_COND_SET;

    private java.lang.String NEW_BIN;

    public RowType_ReplaceCard() {
    }

    public RowType_ReplaceCard(
           java.lang.String CARD,
           java.lang.String NEW_CARD,
           java.math.BigInteger NO_CHARGE,
           java.math.BigInteger NO_EMB_SESSION,
           java.util.Calendar NEW_EXPIRY,
           java.lang.String NEW_RISK_LEVEL,
           java.math.BigDecimal CHIP_ID,
           java.math.BigDecimal DESIGN_ID,
           java.lang.String OFF_COND_SET,
           java.lang.String NEW_BIN) {
           this.CARD = CARD;
           this.NEW_CARD = NEW_CARD;
           this.NO_CHARGE = NO_CHARGE;
           this.NO_EMB_SESSION = NO_EMB_SESSION;
           this.NEW_EXPIRY = NEW_EXPIRY;
           this.NEW_RISK_LEVEL = NEW_RISK_LEVEL;
           this.CHIP_ID = CHIP_ID;
           this.DESIGN_ID = DESIGN_ID;
           this.OFF_COND_SET = OFF_COND_SET;
           this.NEW_BIN = NEW_BIN;
    }


    /**
     * Gets the CARD value for this RowType_ReplaceCard.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ReplaceCard.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the NEW_CARD value for this RowType_ReplaceCard.
     * 
     * @return NEW_CARD
     */
    public java.lang.String getNEW_CARD() {
        return NEW_CARD;
    }


    /**
     * Sets the NEW_CARD value for this RowType_ReplaceCard.
     * 
     * @param NEW_CARD
     */
    public void setNEW_CARD(java.lang.String NEW_CARD) {
        this.NEW_CARD = NEW_CARD;
    }


    /**
     * Gets the NO_CHARGE value for this RowType_ReplaceCard.
     * 
     * @return NO_CHARGE
     */
    public java.math.BigInteger getNO_CHARGE() {
        return NO_CHARGE;
    }


    /**
     * Sets the NO_CHARGE value for this RowType_ReplaceCard.
     * 
     * @param NO_CHARGE
     */
    public void setNO_CHARGE(java.math.BigInteger NO_CHARGE) {
        this.NO_CHARGE = NO_CHARGE;
    }


    /**
     * Gets the NO_EMB_SESSION value for this RowType_ReplaceCard.
     * 
     * @return NO_EMB_SESSION
     */
    public java.math.BigInteger getNO_EMB_SESSION() {
        return NO_EMB_SESSION;
    }


    /**
     * Sets the NO_EMB_SESSION value for this RowType_ReplaceCard.
     * 
     * @param NO_EMB_SESSION
     */
    public void setNO_EMB_SESSION(java.math.BigInteger NO_EMB_SESSION) {
        this.NO_EMB_SESSION = NO_EMB_SESSION;
    }


    /**
     * Gets the NEW_EXPIRY value for this RowType_ReplaceCard.
     * 
     * @return NEW_EXPIRY
     */
    public java.util.Calendar getNEW_EXPIRY() {
        return NEW_EXPIRY;
    }


    /**
     * Sets the NEW_EXPIRY value for this RowType_ReplaceCard.
     * 
     * @param NEW_EXPIRY
     */
    public void setNEW_EXPIRY(java.util.Calendar NEW_EXPIRY) {
        this.NEW_EXPIRY = NEW_EXPIRY;
    }


    /**
     * Gets the NEW_RISK_LEVEL value for this RowType_ReplaceCard.
     * 
     * @return NEW_RISK_LEVEL
     */
    public java.lang.String getNEW_RISK_LEVEL() {
        return NEW_RISK_LEVEL;
    }


    /**
     * Sets the NEW_RISK_LEVEL value for this RowType_ReplaceCard.
     * 
     * @param NEW_RISK_LEVEL
     */
    public void setNEW_RISK_LEVEL(java.lang.String NEW_RISK_LEVEL) {
        this.NEW_RISK_LEVEL = NEW_RISK_LEVEL;
    }


    /**
     * Gets the CHIP_ID value for this RowType_ReplaceCard.
     * 
     * @return CHIP_ID
     */
    public java.math.BigDecimal getCHIP_ID() {
        return CHIP_ID;
    }


    /**
     * Sets the CHIP_ID value for this RowType_ReplaceCard.
     * 
     * @param CHIP_ID
     */
    public void setCHIP_ID(java.math.BigDecimal CHIP_ID) {
        this.CHIP_ID = CHIP_ID;
    }


    /**
     * Gets the DESIGN_ID value for this RowType_ReplaceCard.
     * 
     * @return DESIGN_ID
     */
    public java.math.BigDecimal getDESIGN_ID() {
        return DESIGN_ID;
    }


    /**
     * Sets the DESIGN_ID value for this RowType_ReplaceCard.
     * 
     * @param DESIGN_ID
     */
    public void setDESIGN_ID(java.math.BigDecimal DESIGN_ID) {
        this.DESIGN_ID = DESIGN_ID;
    }


    /**
     * Gets the OFF_COND_SET value for this RowType_ReplaceCard.
     * 
     * @return OFF_COND_SET
     */
    public java.lang.String getOFF_COND_SET() {
        return OFF_COND_SET;
    }


    /**
     * Sets the OFF_COND_SET value for this RowType_ReplaceCard.
     * 
     * @param OFF_COND_SET
     */
    public void setOFF_COND_SET(java.lang.String OFF_COND_SET) {
        this.OFF_COND_SET = OFF_COND_SET;
    }


    /**
     * Gets the NEW_BIN value for this RowType_ReplaceCard.
     * 
     * @return NEW_BIN
     */
    public java.lang.String getNEW_BIN() {
        return NEW_BIN;
    }


    /**
     * Sets the NEW_BIN value for this RowType_ReplaceCard.
     * 
     * @param NEW_BIN
     */
    public void setNEW_BIN(java.lang.String NEW_BIN) {
        this.NEW_BIN = NEW_BIN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ReplaceCard)) return false;
        RowType_ReplaceCard other = (RowType_ReplaceCard) obj;
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
            ((this.NEW_CARD==null && other.getNEW_CARD()==null) || 
             (this.NEW_CARD!=null &&
              this.NEW_CARD.equals(other.getNEW_CARD()))) &&
            ((this.NO_CHARGE==null && other.getNO_CHARGE()==null) || 
             (this.NO_CHARGE!=null &&
              this.NO_CHARGE.equals(other.getNO_CHARGE()))) &&
            ((this.NO_EMB_SESSION==null && other.getNO_EMB_SESSION()==null) || 
             (this.NO_EMB_SESSION!=null &&
              this.NO_EMB_SESSION.equals(other.getNO_EMB_SESSION()))) &&
            ((this.NEW_EXPIRY==null && other.getNEW_EXPIRY()==null) || 
             (this.NEW_EXPIRY!=null &&
              this.NEW_EXPIRY.equals(other.getNEW_EXPIRY()))) &&
            ((this.NEW_RISK_LEVEL==null && other.getNEW_RISK_LEVEL()==null) || 
             (this.NEW_RISK_LEVEL!=null &&
              this.NEW_RISK_LEVEL.equals(other.getNEW_RISK_LEVEL()))) &&
            ((this.CHIP_ID==null && other.getCHIP_ID()==null) || 
             (this.CHIP_ID!=null &&
              this.CHIP_ID.equals(other.getCHIP_ID()))) &&
            ((this.DESIGN_ID==null && other.getDESIGN_ID()==null) || 
             (this.DESIGN_ID!=null &&
              this.DESIGN_ID.equals(other.getDESIGN_ID()))) &&
            ((this.OFF_COND_SET==null && other.getOFF_COND_SET()==null) || 
             (this.OFF_COND_SET!=null &&
              this.OFF_COND_SET.equals(other.getOFF_COND_SET()))) &&
            ((this.NEW_BIN==null && other.getNEW_BIN()==null) || 
             (this.NEW_BIN!=null &&
              this.NEW_BIN.equals(other.getNEW_BIN())));
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
        if (getNEW_CARD() != null) {
            _hashCode += getNEW_CARD().hashCode();
        }
        if (getNO_CHARGE() != null) {
            _hashCode += getNO_CHARGE().hashCode();
        }
        if (getNO_EMB_SESSION() != null) {
            _hashCode += getNO_EMB_SESSION().hashCode();
        }
        if (getNEW_EXPIRY() != null) {
            _hashCode += getNEW_EXPIRY().hashCode();
        }
        if (getNEW_RISK_LEVEL() != null) {
            _hashCode += getNEW_RISK_LEVEL().hashCode();
        }
        if (getCHIP_ID() != null) {
            _hashCode += getCHIP_ID().hashCode();
        }
        if (getDESIGN_ID() != null) {
            _hashCode += getDESIGN_ID().hashCode();
        }
        if (getOFF_COND_SET() != null) {
            _hashCode += getOFF_COND_SET().hashCode();
        }
        if (getNEW_BIN() != null) {
            _hashCode += getNEW_BIN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_ReplaceCard.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ReplaceCard"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NO_CHARGE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NO_CHARGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NO_EMB_SESSION");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NO_EMB_SESSION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_EXPIRY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_EXPIRY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_RISK_LEVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_RISK_LEVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHIP_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHIP_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESIGN_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DESIGN_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OFF_COND_SET");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OFF_COND_SET"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NEW_BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NEW_BIN"));
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
