/**
 * ListType_ManageDataRows.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package visa.issuing_v_01_02_xsd;

public class ListType_ManageDataRows  implements java.io.Serializable {
    private visa.issuing_v_01_02_xsd.RowType_ManageDataCol[] col;

    public ListType_ManageDataRows() {
    }

    public ListType_ManageDataRows(
           visa.issuing_v_01_02_xsd.RowType_ManageDataCol[] col) {
           this.col = col;
    }


    /**
     * Gets the col value for this ListType_ManageDataRows.
     * 
     * @return col
     */
    public visa.issuing_v_01_02_xsd.RowType_ManageDataCol[] getCol() {
        return col;
    }


    /**
     * Sets the col value for this ListType_ManageDataRows.
     * 
     * @param col
     */
    public void setCol(visa.issuing_v_01_02_xsd.RowType_ManageDataCol[] col) {
        this.col = col;
    }

    public visa.issuing_v_01_02_xsd.RowType_ManageDataCol getCol(int i) {
        return this.col[i];
    }

    public void setCol(int i, visa.issuing_v_01_02_xsd.RowType_ManageDataCol _value) {
        this.col[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListType_ManageDataRows)) return false;
        ListType_ManageDataRows other = (ListType_ManageDataRows) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.col==null && other.getCol()==null) || 
             (this.col!=null &&
              java.util.Arrays.equals(this.col, other.getCol())));
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
        if (getCol() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCol());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCol(), i);
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
        new org.apache.axis.description.TypeDesc(ListType_ManageDataRows.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "ListType_ManageDataRows"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("col");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Col"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:issuing_v_01_02_xsd", "RowType_ManageDataCol"));
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
