/**
 * IdentNums.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.relationships.NCI.com.ipakyulibank;

public class IdentNums  implements java.io.Serializable {
   
    private java.lang.String ident_type;

  
    private java.lang.String ident_id;

    
    private java.lang.String ident_series;

  
    private java.lang.String ident_num;

    public IdentNums() {
    }

    public IdentNums(
           java.lang.String ident_type,
           java.lang.String ident_id,
           java.lang.String ident_series,
           java.lang.String ident_num) {
           this.ident_type = ident_type;
           this.ident_id = ident_id;
           this.ident_series = ident_series;
           this.ident_num = ident_num;
    }


    /**
     * Gets the ident_type value for this IdentNums.
     * 
     * @return ident_type   * вид идентификатора
     */
    public java.lang.String getIdent_type() {
        return ident_type;
    }


    /**
     * Sets the ident_type value for this IdentNums.
     * 
     * @param ident_type   * вид идентификатора
     */
    public void setIdent_type(java.lang.String ident_type) {
        this.ident_type = ident_type;
    }


    /**
     * Gets the ident_id value for this IdentNums.
     * 
     * @return ident_id   
     */
    public java.lang.String getIdent_id() {
        return ident_id;
    }


    /**
     * Sets the ident_id value for this IdentNums.
     * 
     * @param ident_id   *
     */
    public void setIdent_id(java.lang.String ident_id) {
        this.ident_id = ident_id;
    }


    /**
     * Gets the ident_series value for this IdentNums.
     * 
     * @return ident_series   *
     */
    public java.lang.String getIdent_series() {
        return ident_series;
    }


    /**
     * Sets the ident_series value for this IdentNums.
     * 
     * @param ident_series   
     */
    public void setIdent_series(java.lang.String ident_series) {
        this.ident_series = ident_series;
    }


    /**
     * Gets the ident_num value for this IdentNums.
     * 
     * @return ident_num  
     */
    public java.lang.String getIdent_num() {
        return ident_num;
    }


    /**
     * Sets the ident_num value for this IdentNums.
     * 
     * @param ident_num   *
     */
    public void setIdent_num(java.lang.String ident_num) {
        this.ident_num = ident_num;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IdentNums)) return false;
        IdentNums other = (IdentNums) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ident_type==null && other.getIdent_type()==null) || 
             (this.ident_type!=null &&
              this.ident_type.equals(other.getIdent_type()))) &&
            ((this.ident_id==null && other.getIdent_id()==null) || 
             (this.ident_id!=null &&
              this.ident_id.equals(other.getIdent_id()))) &&
            ((this.ident_series==null && other.getIdent_series()==null) || 
             (this.ident_series!=null &&
              this.ident_series.equals(other.getIdent_series()))) &&
            ((this.ident_num==null && other.getIdent_num()==null) || 
             (this.ident_num!=null &&
              this.ident_num.equals(other.getIdent_num())));
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
        if (getIdent_type() != null) {
            _hashCode += getIdent_type().hashCode();
        }
        if (getIdent_id() != null) {
            _hashCode += getIdent_id().hashCode();
        }
        if (getIdent_series() != null) {
            _hashCode += getIdent_series().hashCode();
        }
        if (getIdent_num() != null) {
            _hashCode += getIdent_num().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IdentNums.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "IdentNums"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ident_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ident_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ident_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ident_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ident_series");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ident_series"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ident_num");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ident_num"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
