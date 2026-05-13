/**
 * RowType_FilterCondition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class RowType_FilterCondition  implements java.io.Serializable {
    private java.lang.String key;

    private java.lang.String value;

    private java.math.BigInteger matchingRule;

    private java.lang.String matchingRuleOption;

    public RowType_FilterCondition() {
    }

    public RowType_FilterCondition(
           java.lang.String key,
           java.lang.String value,
           java.math.BigInteger matchingRule,
           java.lang.String matchingRuleOption) {
           this.key = key;
           this.value = value;
           this.matchingRule = matchingRule;
           this.matchingRuleOption = matchingRuleOption;
    }


    /**
     * Gets the key value for this RowType_FilterCondition.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this RowType_FilterCondition.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the value value for this RowType_FilterCondition.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this RowType_FilterCondition.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the matchingRule value for this RowType_FilterCondition.
     * 
     * @return matchingRule
     */
    public java.math.BigInteger getMatchingRule() {
        return matchingRule;
    }


    /**
     * Sets the matchingRule value for this RowType_FilterCondition.
     * 
     * @param matchingRule
     */
    public void setMatchingRule(java.math.BigInteger matchingRule) {
        this.matchingRule = matchingRule;
    }


    /**
     * Gets the matchingRuleOption value for this RowType_FilterCondition.
     * 
     * @return matchingRuleOption
     */
    public java.lang.String getMatchingRuleOption() {
        return matchingRuleOption;
    }


    /**
     * Sets the matchingRuleOption value for this RowType_FilterCondition.
     * 
     * @param matchingRuleOption
     */
    public void setMatchingRuleOption(java.lang.String matchingRuleOption) {
        this.matchingRuleOption = matchingRuleOption;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_FilterCondition)) return false;
        RowType_FilterCondition other = (RowType_FilterCondition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            ((this.matchingRule==null && other.getMatchingRule()==null) || 
             (this.matchingRule!=null &&
              this.matchingRule.equals(other.getMatchingRule()))) &&
            ((this.matchingRuleOption==null && other.getMatchingRuleOption()==null) || 
             (this.matchingRuleOption!=null &&
              this.matchingRuleOption.equals(other.getMatchingRuleOption())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        if (getMatchingRule() != null) {
            _hashCode += getMatchingRule().hashCode();
        }
        if (getMatchingRuleOption() != null) {
            _hashCode += getMatchingRuleOption().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RowType_FilterCondition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_FilterCondition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchingRule");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MatchingRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchingRuleOption");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MatchingRuleOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
