/**
 * ComplexType_RetrieveDataParameters.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package agroBank.issuing_v_01_02_xsd;

public class ComplexType_RetrieveDataParameters  implements java.io.Serializable {
    private java.lang.String queryName;

    private java.lang.String queryVariant;

    private agroBank.issuing_v_01_02_xsd.RowType_FilterCondition[] filterCondition;

    public ComplexType_RetrieveDataParameters() {
    }

    public ComplexType_RetrieveDataParameters(
           java.lang.String queryName,
           java.lang.String queryVariant,
           agroBank.issuing_v_01_02_xsd.RowType_FilterCondition[] filterCondition) {
           this.queryName = queryName;
           this.queryVariant = queryVariant;
           this.filterCondition = filterCondition;
    }


    /**
     * Gets the queryName value for this ComplexType_RetrieveDataParameters.
     * 
     * @return queryName
     */
    public java.lang.String getQueryName() {
        return queryName;
    }


    /**
     * Sets the queryName value for this ComplexType_RetrieveDataParameters.
     * 
     * @param queryName
     */
    public void setQueryName(java.lang.String queryName) {
        this.queryName = queryName;
    }


    /**
     * Gets the queryVariant value for this ComplexType_RetrieveDataParameters.
     * 
     * @return queryVariant
     */
    public java.lang.String getQueryVariant() {
        return queryVariant;
    }


    /**
     * Sets the queryVariant value for this ComplexType_RetrieveDataParameters.
     * 
     * @param queryVariant
     */
    public void setQueryVariant(java.lang.String queryVariant) {
        this.queryVariant = queryVariant;
    }


    /**
     * Gets the filterCondition value for this ComplexType_RetrieveDataParameters.
     * 
     * @return filterCondition
     */
    public agroBank.issuing_v_01_02_xsd.RowType_FilterCondition[] getFilterCondition() {
        return filterCondition;
    }


    /**
     * Sets the filterCondition value for this ComplexType_RetrieveDataParameters.
     * 
     * @param filterCondition
     */
    public void setFilterCondition(agroBank.issuing_v_01_02_xsd.RowType_FilterCondition[] filterCondition) {
        this.filterCondition = filterCondition;
    }

    public agroBank.issuing_v_01_02_xsd.RowType_FilterCondition getFilterCondition(int i) {
        return this.filterCondition[i];
    }

    public void setFilterCondition(int i, agroBank.issuing_v_01_02_xsd.RowType_FilterCondition _value) {
        this.filterCondition[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComplexType_RetrieveDataParameters)) return false;
        ComplexType_RetrieveDataParameters other = (ComplexType_RetrieveDataParameters) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.queryName==null && other.getQueryName()==null) || 
             (this.queryName!=null &&
              this.queryName.equals(other.getQueryName()))) &&
            ((this.queryVariant==null && other.getQueryVariant()==null) || 
             (this.queryVariant!=null &&
              this.queryVariant.equals(other.getQueryVariant()))) &&
            ((this.filterCondition==null && other.getFilterCondition()==null) || 
             (this.filterCondition!=null &&
              java.util.Arrays.equals(this.filterCondition, other.getFilterCondition())));
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
        if (getQueryName() != null) {
            _hashCode += getQueryName().hashCode();
        }
        if (getQueryVariant() != null) {
            _hashCode += getQueryVariant().hashCode();
        }
        if (getFilterCondition() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFilterCondition());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFilterCondition(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComplexType_RetrieveDataParameters.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ComplexType_RetrieveDataParameters"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QueryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryVariant");
        elemField.setXmlName(new javax.xml.namespace.QName("", "QueryVariant"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filterCondition");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FilterCondition"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_FilterCondition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
