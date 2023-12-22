/**
 * BPRelWithDate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package roles.relationships.NCI.com.ipakyulibank;

public class BPRelWithDate  implements java.io.Serializable {
    /* Вид отношения */
    private java.lang.String rel_type;

    /* Вид отношения: readable name */
    private java.lang.String rel_name;

    /* Направление отношения. Актуально только для односторонних отношений.
     * Допустимые значения:
     * «» – Отношение создается от ДП1 к ДП2
     * «X» – Отношение создается от ДП2 к ДП1 */
    private java.lang.String rel_direct_invert;

    /* Период с – срок действия */
    private java.util.Date date_from;

    /* Период по – срок действия */
    private java.util.Date date_to;

    /* Данные контактных лиц. Для BUR001 */
    private relationships.NCI.com.ipakyulibank.BPContpers contact_person;

    /* Данные держателей акций. Для BURC01 */
    private relationships.NCI.com.ipakyulibank.BPShareholder shareholder;

    public BPRelWithDate() {
    }

    public BPRelWithDate(
           java.lang.String rel_type,
           java.lang.String rel_name,
           java.lang.String rel_direct_invert,
           java.util.Date date_from,
           java.util.Date date_to,
           relationships.NCI.com.ipakyulibank.BPContpers contact_person,
           relationships.NCI.com.ipakyulibank.BPShareholder shareholder) {
           this.rel_type = rel_type;
           this.rel_name = rel_name;
           this.rel_direct_invert = rel_direct_invert;
           this.date_from = date_from;
           this.date_to = date_to;
           this.contact_person = contact_person;
           this.shareholder = shareholder;
    }


    /**
     * Gets the rel_type value for this BPRelWithDate.
     * 
     * @return rel_type   * Вид отношения
     */
    public java.lang.String getRel_type() {
        return rel_type;
    }


    /**
     * Sets the rel_type value for this BPRelWithDate.
     * 
     * @param rel_type   * Вид отношения
     */
    public void setRel_type(java.lang.String rel_type) {
        this.rel_type = rel_type;
    }


    /**
     * Gets the rel_name value for this BPRelWithDate.
     * 
     * @return rel_name   * Вид отношения: readable name
     */
    public java.lang.String getRel_name() {
        return rel_name;
    }


    /**
     * Sets the rel_name value for this BPRelWithDate.
     * 
     * @param rel_name   * Вид отношения: readable name
     */
    public void setRel_name(java.lang.String rel_name) {
        this.rel_name = rel_name;
    }


    /**
     * Gets the rel_direct_invert value for this BPRelWithDate.
     * 
     * @return rel_direct_invert   * Направление отношения. Актуально только для односторонних отношений.
     * Допустимые значения:
     * «» – Отношение создается от ДП1 к ДП2
     * «X» – Отношение создается от ДП2 к ДП1
     */
    public java.lang.String getRel_direct_invert() {
        return rel_direct_invert;
    }


    /**
     * Sets the rel_direct_invert value for this BPRelWithDate.
     * 
     * @param rel_direct_invert   * Направление отношения. Актуально только для односторонних отношений.
     * Допустимые значения:
     * «» – Отношение создается от ДП1 к ДП2
     * «X» – Отношение создается от ДП2 к ДП1
     */
    public void setRel_direct_invert(java.lang.String rel_direct_invert) {
        this.rel_direct_invert = rel_direct_invert;
    }


    /**
     * Gets the date_from value for this BPRelWithDate.
     * 
     * @return date_from   * Период с – срок действия
     */
    public java.util.Date getDate_from() {
        return date_from;
    }


    /**
     * Sets the date_from value for this BPRelWithDate.
     * 
     * @param date_from   * Период с – срок действия
     */
    public void setDate_from(java.util.Date date_from) {
        this.date_from = date_from;
    }


    /**
     * Gets the date_to value for this BPRelWithDate.
     * 
     * @return date_to   * Период по – срок действия
     */
    public java.util.Date getDate_to() {
        return date_to;
    }


    /**
     * Sets the date_to value for this BPRelWithDate.
     * 
     * @param date_to   * Период по – срок действия
     */
    public void setDate_to(java.util.Date date_to) {
        this.date_to = date_to;
    }


    /**
     * Gets the contact_person value for this BPRelWithDate.
     * 
     * @return contact_person   * Данные контактных лиц. Для BUR001
     */
    public relationships.NCI.com.ipakyulibank.BPContpers getContact_person() {
        return contact_person;
    }


    /**
     * Sets the contact_person value for this BPRelWithDate.
     * 
     * @param contact_person   * Данные контактных лиц. Для BUR001
     */
    public void setContact_person(relationships.NCI.com.ipakyulibank.BPContpers contact_person) {
        this.contact_person = contact_person;
    }


    /**
     * Gets the shareholder value for this BPRelWithDate.
     * 
     * @return shareholder   * Данные держателей акций. Для BURC01
     */
    public relationships.NCI.com.ipakyulibank.BPShareholder getShareholder() {
        return shareholder;
    }


    /**
     * Sets the shareholder value for this BPRelWithDate.
     * 
     * @param shareholder   * Данные держателей акций. Для BURC01
     */
    public void setShareholder(relationships.NCI.com.ipakyulibank.BPShareholder shareholder) {
        this.shareholder = shareholder;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPRelWithDate)) return false;
        BPRelWithDate other = (BPRelWithDate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rel_type==null && other.getRel_type()==null) || 
             (this.rel_type!=null &&
              this.rel_type.equals(other.getRel_type()))) &&
            ((this.rel_name==null && other.getRel_name()==null) || 
             (this.rel_name!=null &&
              this.rel_name.equals(other.getRel_name()))) &&
            ((this.rel_direct_invert==null && other.getRel_direct_invert()==null) || 
             (this.rel_direct_invert!=null &&
              this.rel_direct_invert.equals(other.getRel_direct_invert()))) &&
            ((this.date_from==null && other.getDate_from()==null) || 
             (this.date_from!=null &&
              this.date_from.equals(other.getDate_from()))) &&
            ((this.date_to==null && other.getDate_to()==null) || 
             (this.date_to!=null &&
              this.date_to.equals(other.getDate_to()))) &&
            ((this.contact_person==null && other.getContact_person()==null) || 
             (this.contact_person!=null &&
              this.contact_person.equals(other.getContact_person()))) &&
            ((this.shareholder==null && other.getShareholder()==null) || 
             (this.shareholder!=null &&
              this.shareholder.equals(other.getShareholder())));
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
        if (getRel_type() != null) {
            _hashCode += getRel_type().hashCode();
        }
        if (getRel_name() != null) {
            _hashCode += getRel_name().hashCode();
        }
        if (getRel_direct_invert() != null) {
            _hashCode += getRel_direct_invert().hashCode();
        }
        if (getDate_from() != null) {
            _hashCode += getDate_from().hashCode();
        }
        if (getDate_to() != null) {
            _hashCode += getDate_to().hashCode();
        }
        if (getContact_person() != null) {
            _hashCode += getContact_person().hashCode();
        }
        if (getShareholder() != null) {
            _hashCode += getShareholder().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BPRelWithDate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRelWithDate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rel_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rel_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rel_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rel_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rel_direct_invert");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rel_direct_invert"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date_from");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date_from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date_to");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date_to"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contact_person");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Contact_person"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPContpers"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareholder");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Shareholder"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPShareholder"));
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
