/**
 * RowType_DuplicateCard.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class RowType_DuplicateCard  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.math.BigInteger NO_CHARGE;

    private java.math.BigInteger NO_EMB_SESSION;

    private java.util.Calendar NEW_EXPIRY;

    private java.lang.String NEW_RISK_LEVEL;

    public RowType_DuplicateCard() {
    }

    public RowType_DuplicateCard(
           java.lang.String CARD,
           java.math.BigInteger NO_CHARGE,
           java.math.BigInteger NO_EMB_SESSION,
           java.util.Calendar NEW_EXPIRY,
           java.lang.String NEW_RISK_LEVEL) {
           this.CARD = CARD;
           this.NO_CHARGE = NO_CHARGE;
           this.NO_EMB_SESSION = NO_EMB_SESSION;
           this.NEW_EXPIRY = NEW_EXPIRY;
           this.NEW_RISK_LEVEL = NEW_RISK_LEVEL;
    }


    /**
     * Gets the CARD value for this RowType_DuplicateCard.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_DuplicateCard.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the NO_CHARGE value for this RowType_DuplicateCard.
     * 
     * @return NO_CHARGE
     */
    public java.math.BigInteger getNO_CHARGE() {
        return NO_CHARGE;
    }


    /**
     * Sets the NO_CHARGE value for this RowType_DuplicateCard.
     * 
     * @param NO_CHARGE
     */
    public void setNO_CHARGE(java.math.BigInteger NO_CHARGE) {
        this.NO_CHARGE = NO_CHARGE;
    }


    /**
     * Gets the NO_EMB_SESSION value for this RowType_DuplicateCard.
     * 
     * @return NO_EMB_SESSION
     */
    public java.math.BigInteger getNO_EMB_SESSION() {
        return NO_EMB_SESSION;
    }


    /**
     * Sets the NO_EMB_SESSION value for this RowType_DuplicateCard.
     * 
     * @param NO_EMB_SESSION
     */
    public void setNO_EMB_SESSION(java.math.BigInteger NO_EMB_SESSION) {
        this.NO_EMB_SESSION = NO_EMB_SESSION;
    }


    /**
     * Gets the NEW_EXPIRY value for this RowType_DuplicateCard.
     * 
     * @return NEW_EXPIRY
     */
    public java.util.Calendar getNEW_EXPIRY() {
        return NEW_EXPIRY;
    }


    /**
     * Sets the NEW_EXPIRY value for this RowType_DuplicateCard.
     * 
     * @param NEW_EXPIRY
     */
    public void setNEW_EXPIRY(java.util.Calendar NEW_EXPIRY) {
        this.NEW_EXPIRY = NEW_EXPIRY;
    }


    /**
     * Gets the NEW_RISK_LEVEL value for this RowType_DuplicateCard.
     * 
     * @return NEW_RISK_LEVEL
     */
    public java.lang.String getNEW_RISK_LEVEL() {
        return NEW_RISK_LEVEL;
    }


    /**
     * Sets the NEW_RISK_LEVEL value for this RowType_DuplicateCard.
     * 
     * @param NEW_RISK_LEVEL
     */
    public void setNEW_RISK_LEVEL(java.lang.String NEW_RISK_LEVEL) {
        this.NEW_RISK_LEVEL = NEW_RISK_LEVEL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_DuplicateCard)) return false;
        RowType_DuplicateCard other = (RowType_DuplicateCard) obj;
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
              this.NEW_RISK_LEVEL.equals(other.getNEW_RISK_LEVEL())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_DuplicateCard.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_DuplicateCard"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CARD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
