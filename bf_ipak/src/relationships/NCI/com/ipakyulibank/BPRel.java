/**
 * BPRel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package relationships.NCI.com.ipakyulibank;

public class BPRel  implements java.io.Serializable {
    /* “” – Добавить; “D” - Удалить */
    private java.lang.String mode;

    /* Вид отношения */
    private java.lang.String rel_type;

    /* Направление отношения. Актуально только для односторонних отношений.
     * Допустимые значения:
     * «» – Отношение создается от ДП1 к ДП2
     * «X» – Отношение создается от ДП2 к ДП1 */
    private java.lang.String rel_direct_invert;

    private java.lang.String profile_author;

    /* Данные контактных лиц. Для BUR001 */
    private relationships.NCI.com.ipakyulibank.BPContpers contact_person;

    /* Данные держателей акций. Для BURC01 */
    private relationships.NCI.com.ipakyulibank.BPShareholder shareholder;

    public BPRel() {
    }

    public BPRel(
           java.lang.String mode,
           java.lang.String rel_type,
           java.lang.String rel_direct_invert,
           java.lang.String profile_author,
           relationships.NCI.com.ipakyulibank.BPContpers contact_person,
           relationships.NCI.com.ipakyulibank.BPShareholder shareholder) {
           this.mode = mode;
           this.rel_type = rel_type;
           this.rel_direct_invert = rel_direct_invert;
           this.profile_author = profile_author;
           this.contact_person = contact_person;
           this.shareholder = shareholder;
    }


    /**
     * Gets the mode value for this BPRel.
     * 
     * @return mode   * “” – Добавить; “D” - Удалить
     */
    public java.lang.String getMode() {
        return mode;
    }


    /**
     * Sets the mode value for this BPRel.
     * 
     * @param mode   * “” – Добавить; “D” - Удалить
     */
    public void setMode(java.lang.String mode) {
        this.mode = mode;
    }


    /**
     * Gets the rel_type value for this BPRel.
     * 
     * @return rel_type   * Вид отношения
     */
    public java.lang.String getRel_type() {
        return rel_type;
    }


    /**
     * Sets the rel_type value for this BPRel.
     * 
     * @param rel_type   * Вид отношения
     */
    public void setRel_type(java.lang.String rel_type) {
        this.rel_type = rel_type;
    }


    /**
     * Gets the rel_direct_invert value for this BPRel.
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
     * Sets the rel_direct_invert value for this BPRel.
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
     * Gets the profile_author value for this BPRel.
     * 
     * @return profile_author
     */
    public java.lang.String getProfile_author() {
        return profile_author;
    }


    /**
     * Sets the profile_author value for this BPRel.
     * 
     * @param profile_author
     */
    public void setProfile_author(java.lang.String profile_author) {
        this.profile_author = profile_author;
    }


    /**
     * Gets the contact_person value for this BPRel.
     * 
     * @return contact_person   * Данные контактных лиц. Для BUR001
     */
    public relationships.NCI.com.ipakyulibank.BPContpers getContact_person() {
        return contact_person;
    }


    /**
     * Sets the contact_person value for this BPRel.
     * 
     * @param contact_person   * Данные контактных лиц. Для BUR001
     */
    public void setContact_person(relationships.NCI.com.ipakyulibank.BPContpers contact_person) {
        this.contact_person = contact_person;
    }


    /**
     * Gets the shareholder value for this BPRel.
     * 
     * @return shareholder   * Данные держателей акций. Для BURC01
     */
    public relationships.NCI.com.ipakyulibank.BPShareholder getShareholder() {
        return shareholder;
    }


    /**
     * Sets the shareholder value for this BPRel.
     * 
     * @param shareholder   * Данные держателей акций. Для BURC01
     */
    public void setShareholder(relationships.NCI.com.ipakyulibank.BPShareholder shareholder) {
        this.shareholder = shareholder;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPRel)) return false;
        BPRel other = (BPRel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mode==null && other.getMode()==null) || 
             (this.mode!=null &&
              this.mode.equals(other.getMode()))) &&
            ((this.rel_type==null && other.getRel_type()==null) || 
             (this.rel_type!=null &&
              this.rel_type.equals(other.getRel_type()))) &&
            ((this.rel_direct_invert==null && other.getRel_direct_invert()==null) || 
             (this.rel_direct_invert!=null &&
              this.rel_direct_invert.equals(other.getRel_direct_invert()))) &&
            ((this.profile_author==null && other.getProfile_author()==null) || 
             (this.profile_author!=null &&
              this.profile_author.equals(other.getProfile_author()))) &&
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
        if (getMode() != null) {
            _hashCode += getMode().hashCode();
        }
        if (getRel_type() != null) {
            _hashCode += getRel_type().hashCode();
        }
        if (getRel_direct_invert() != null) {
            _hashCode += getRel_direct_invert().hashCode();
        }
        if (getProfile_author() != null) {
            _hashCode += getProfile_author().hashCode();
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
        new org.apache.axis.description.TypeDesc(BPRel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:relationships", "BPRel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rel_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "rel_type"));
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
        elemField.setFieldName("profile_author");
        elemField.setXmlName(new javax.xml.namespace.QName("", "profile_author"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
