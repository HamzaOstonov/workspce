/**
 * BPShareholder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.relationships.NCI.com.ipakyulibank;

public class BPShareholder  implements java.io.Serializable {

    private java.math.BigDecimal cmpy_part_per;


    private java.math.BigDecimal cmpy_part_amo;


    private java.lang.String cmpy_part_cur;

    
    private java.lang.String cmpy_control;

   
    private java.math.BigDecimal shares_numb;

    public BPShareholder() {
    }

    public BPShareholder(
           java.math.BigDecimal cmpy_part_per,
           java.math.BigDecimal cmpy_part_amo,
           java.lang.String cmpy_part_cur,
           java.lang.String cmpy_control,
           java.math.BigDecimal shares_numb) {
           this.cmpy_part_per = cmpy_part_per;
           this.cmpy_part_amo = cmpy_part_amo;
           this.cmpy_part_cur = cmpy_part_cur;
           this.cmpy_control = cmpy_control;
           this.shares_numb = shares_numb;
    }


    /**
     * Gets the cmpy_part_per value for this BPShareholder.
     * 
     * @return cmpy_part_per   * 
     */
    public java.math.BigDecimal getCmpy_part_per() {
        return cmpy_part_per;
    }


    /**
     * Sets the cmpy_part_per value for this BPShareholder.
     * 
     * @param cmpy_part_per   *
     */
    public void setCmpy_part_per(java.math.BigDecimal cmpy_part_per) {
        this.cmpy_part_per = cmpy_part_per;
    }


    /**
     * Gets the cmpy_part_amo value for this BPShareholder.
     * 
     * @return cmpy_part_amo   * 
     */
    public java.math.BigDecimal getCmpy_part_amo() {
        return cmpy_part_amo;
    }


    /**
     * Sets the cmpy_part_amo value for this BPShareholder.
     * 
     * @param cmpy_part_amo   *
     */
    public void setCmpy_part_amo(java.math.BigDecimal cmpy_part_amo) {
        this.cmpy_part_amo = cmpy_part_amo;
    }


    /**
     * Gets the cmpy_part_cur value for this BPShareholder.
     * 
     * @return cmpy_part_cur   *
     */
    public java.lang.String getCmpy_part_cur() {
        return cmpy_part_cur;
    }


    /**
     * Sets the cmpy_part_cur value for this BPShareholder.
     * 
     */
    public void setCmpy_part_cur(java.lang.String cmpy_part_cur) {
        this.cmpy_part_cur = cmpy_part_cur;
    }


    /**
     * Gets the cmpy_control value for this BPShareholder.
     * 
     * @return cmpy_control   * 
     */
    public java.lang.String getCmpy_control() {
        return cmpy_control;
    }


    /**
     * Sets the cmpy_control value for this BPShareholder.
     * 
     * @param cmpy_control   * 
     */
    public void setCmpy_control(java.lang.String cmpy_control) {
        this.cmpy_control = cmpy_control;
    }


    /**
     * Gets the shares_numb value for this BPShareholder.
     * 
     * @return shares_numb   *
     */
    public java.math.BigDecimal getShares_numb() {
        return shares_numb;
    }


    /**
     * Sets the shares_numb value for this BPShareholder.
     * 
     * @param shares_numb   * 
     */
    public void setShares_numb(java.math.BigDecimal shares_numb) {
        this.shares_numb = shares_numb;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPShareholder)) return false;
        BPShareholder other = (BPShareholder) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cmpy_part_per==null && other.getCmpy_part_per()==null) || 
             (this.cmpy_part_per!=null &&
              this.cmpy_part_per.equals(other.getCmpy_part_per()))) &&
            ((this.cmpy_part_amo==null && other.getCmpy_part_amo()==null) || 
             (this.cmpy_part_amo!=null &&
              this.cmpy_part_amo.equals(other.getCmpy_part_amo()))) &&
            ((this.cmpy_part_cur==null && other.getCmpy_part_cur()==null) || 
             (this.cmpy_part_cur!=null &&
              this.cmpy_part_cur.equals(other.getCmpy_part_cur()))) &&
            ((this.cmpy_control==null && other.getCmpy_control()==null) || 
             (this.cmpy_control!=null &&
              this.cmpy_control.equals(other.getCmpy_control()))) &&
            ((this.shares_numb==null && other.getShares_numb()==null) || 
             (this.shares_numb!=null &&
              this.shares_numb.equals(other.getShares_numb())));
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
        if (getCmpy_part_per() != null) {
            _hashCode += getCmpy_part_per().hashCode();
        }
        if (getCmpy_part_amo() != null) {
            _hashCode += getCmpy_part_amo().hashCode();
        }
        if (getCmpy_part_cur() != null) {
            _hashCode += getCmpy_part_cur().hashCode();
        }
        if (getCmpy_control() != null) {
            _hashCode += getCmpy_control().hashCode();
        }
        if (getShares_numb() != null) {
            _hashCode += getShares_numb().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BPShareholder.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPShareholder"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpy_part_per");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cmpy_part_per"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpy_part_amo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cmpy_part_amo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpy_part_cur");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cmpy_part_cur"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpy_control");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cmpy_control"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shares_numb");
        elemField.setXmlName(new javax.xml.namespace.QName("", "shares_numb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
