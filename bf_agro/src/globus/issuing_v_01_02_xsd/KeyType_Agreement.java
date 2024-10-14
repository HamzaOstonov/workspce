/**
 * KeyType_Agreement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package globus.issuing_v_01_02_xsd;

public class KeyType_Agreement  implements java.io.Serializable {
    private java.math.BigDecimal AGRE_NOM;

    public KeyType_Agreement() {
    }

    public KeyType_Agreement(
           java.math.BigDecimal AGRE_NOM) {
           this.AGRE_NOM = AGRE_NOM;
    }


    /**
     * Gets the AGRE_NOM value for this KeyType_Agreement.
     * 
     * @return AGRE_NOM
     */
    public java.math.BigDecimal getAGRE_NOM() {
        return AGRE_NOM;
    }


    /**
     * Sets the AGRE_NOM value for this KeyType_Agreement.
     * 
     * @param AGRE_NOM
     */
    public void setAGRE_NOM(java.math.BigDecimal AGRE_NOM) {
        this.AGRE_NOM = AGRE_NOM;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KeyType_Agreement)) return false;
        KeyType_Agreement other = (KeyType_Agreement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AGRE_NOM==null && other.getAGRE_NOM()==null) || 
             (this.AGRE_NOM!=null &&
              this.AGRE_NOM.equals(other.getAGRE_NOM())));
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
        if (getAGRE_NOM() != null) {
            _hashCode += getAGRE_NOM().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(KeyType_Agreement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "KeyType_Agreement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGRE_NOM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AGRE_NOM"));
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

	@Override
	public String toString() {
		return "KeyType_Agreement ["
				+ (AGRE_NOM != null ? "AGRE_NOM=" + AGRE_NOM : "") + "]";
	}

}
