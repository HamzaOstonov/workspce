/**
 * RowType_OrderPinEnvelope.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class RowType_OrderPinEnvelope  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.math.BigInteger NO_CHARGE;

    private java.math.BigInteger NO_EMB_SESSION;

    private java.util.Calendar NEW_EXPIRY;

    private java.math.BigInteger OP_MODE;

    public RowType_OrderPinEnvelope() {
    }

    public RowType_OrderPinEnvelope(
           java.lang.String CARD,
           java.math.BigInteger NO_CHARGE,
           java.math.BigInteger NO_EMB_SESSION,
           java.util.Calendar NEW_EXPIRY,
           java.math.BigInteger OP_MODE) {
           this.CARD = CARD;
           this.NO_CHARGE = NO_CHARGE;
           this.NO_EMB_SESSION = NO_EMB_SESSION;
           this.NEW_EXPIRY = NEW_EXPIRY;
           this.OP_MODE = OP_MODE;
    }


    /**
     * Gets the CARD value for this RowType_OrderPinEnvelope.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_OrderPinEnvelope.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the NO_CHARGE value for this RowType_OrderPinEnvelope.
     * 
     * @return NO_CHARGE
     */
    public java.math.BigInteger getNO_CHARGE() {
        return NO_CHARGE;
    }


    /**
     * Sets the NO_CHARGE value for this RowType_OrderPinEnvelope.
     * 
     * @param NO_CHARGE
     */
    public void setNO_CHARGE(java.math.BigInteger NO_CHARGE) {
        this.NO_CHARGE = NO_CHARGE;
    }


    /**
     * Gets the NO_EMB_SESSION value for this RowType_OrderPinEnvelope.
     * 
     * @return NO_EMB_SESSION
     */
    public java.math.BigInteger getNO_EMB_SESSION() {
        return NO_EMB_SESSION;
    }


    /**
     * Sets the NO_EMB_SESSION value for this RowType_OrderPinEnvelope.
     * 
     * @param NO_EMB_SESSION
     */
    public void setNO_EMB_SESSION(java.math.BigInteger NO_EMB_SESSION) {
        this.NO_EMB_SESSION = NO_EMB_SESSION;
    }


    /**
     * Gets the NEW_EXPIRY value for this RowType_OrderPinEnvelope.
     * 
     * @return NEW_EXPIRY
     */
    public java.util.Calendar getNEW_EXPIRY() {
        return NEW_EXPIRY;
    }


    /**
     * Sets the NEW_EXPIRY value for this RowType_OrderPinEnvelope.
     * 
     * @param NEW_EXPIRY
     */
    public void setNEW_EXPIRY(java.util.Calendar NEW_EXPIRY) {
        this.NEW_EXPIRY = NEW_EXPIRY;
    }


    /**
     * Gets the OP_MODE value for this RowType_OrderPinEnvelope.
     * 
     * @return OP_MODE
     */
    public java.math.BigInteger getOP_MODE() {
        return OP_MODE;
    }


    /**
     * Sets the OP_MODE value for this RowType_OrderPinEnvelope.
     * 
     * @param OP_MODE
     */
    public void setOP_MODE(java.math.BigInteger OP_MODE) {
        this.OP_MODE = OP_MODE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_OrderPinEnvelope)) return false;
        RowType_OrderPinEnvelope other = (RowType_OrderPinEnvelope) obj;
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
            ((this.OP_MODE==null && other.getOP_MODE()==null) || 
             (this.OP_MODE!=null &&
              this.OP_MODE.equals(other.getOP_MODE())));
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
        if (getOP_MODE() != null) {
            _hashCode += getOP_MODE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_OrderPinEnvelope.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_OrderPinEnvelope"));
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
        elemField.setFieldName("OP_MODE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OP_MODE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
