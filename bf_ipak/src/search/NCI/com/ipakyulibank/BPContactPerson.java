/**
 * BPContactPerson.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package search.NCI.com.ipakyulibank;

public class BPContactPerson  implements java.io.Serializable {
    private java.lang.String relationship_type;

    private java.lang.String position;

    private java.lang.String surname_global;

    private java.lang.String name_global;

    private java.lang.String mid_name_global;

    private java.lang.String surname_local;

    private java.lang.String name_local;

    private java.lang.String midname_local;

    private java.lang.String sex;

    private java.util.Date birthday;

    public BPContactPerson() {
    }

    public BPContactPerson(
           java.lang.String relationship_type,
           java.lang.String position,
           java.lang.String surname_global,
           java.lang.String name_global,
           java.lang.String mid_name_global,
           java.lang.String surname_local,
           java.lang.String name_local,
           java.lang.String midname_local,
           java.lang.String sex,
           java.util.Date birthday) {
           this.relationship_type = relationship_type;
           this.position = position;
           this.surname_global = surname_global;
           this.name_global = name_global;
           this.mid_name_global = mid_name_global;
           this.surname_local = surname_local;
           this.name_local = name_local;
           this.midname_local = midname_local;
           this.sex = sex;
           this.birthday = birthday;
    }


    /**
     * Gets the relationship_type value for this BPContactPerson.
     * 
     * @return relationship_type
     */
    public java.lang.String getRelationship_type() {
        return relationship_type;
    }


    /**
     * Sets the relationship_type value for this BPContactPerson.
     * 
     * @param relationship_type
     */
    public void setRelationship_type(java.lang.String relationship_type) {
        this.relationship_type = relationship_type;
    }


    /**
     * Gets the position value for this BPContactPerson.
     * 
     * @return position
     */
    public java.lang.String getPosition() {
        return position;
    }


    /**
     * Sets the position value for this BPContactPerson.
     * 
     * @param position
     */
    public void setPosition(java.lang.String position) {
        this.position = position;
    }


    /**
     * Gets the surname_global value for this BPContactPerson.
     * 
     * @return surname_global
     */
    public java.lang.String getSurname_global() {
        return surname_global;
    }


    /**
     * Sets the surname_global value for this BPContactPerson.
     * 
     * @param surname_global
     */
    public void setSurname_global(java.lang.String surname_global) {
        this.surname_global = surname_global;
    }


    /**
     * Gets the name_global value for this BPContactPerson.
     * 
     * @return name_global
     */
    public java.lang.String getName_global() {
        return name_global;
    }


    /**
     * Sets the name_global value for this BPContactPerson.
     * 
     * @param name_global
     */
    public void setName_global(java.lang.String name_global) {
        this.name_global = name_global;
    }


    /**
     * Gets the mid_name_global value for this BPContactPerson.
     * 
     * @return mid_name_global
     */
    public java.lang.String getMid_name_global() {
        return mid_name_global;
    }


    /**
     * Sets the mid_name_global value for this BPContactPerson.
     * 
     * @param mid_name_global
     */
    public void setMid_name_global(java.lang.String mid_name_global) {
        this.mid_name_global = mid_name_global;
    }


    /**
     * Gets the surname_local value for this BPContactPerson.
     * 
     * @return surname_local
     */
    public java.lang.String getSurname_local() {
        return surname_local;
    }


    /**
     * Sets the surname_local value for this BPContactPerson.
     * 
     * @param surname_local
     */
    public void setSurname_local(java.lang.String surname_local) {
        this.surname_local = surname_local;
    }


    /**
     * Gets the name_local value for this BPContactPerson.
     * 
     * @return name_local
     */
    public java.lang.String getName_local() {
        return name_local;
    }


    /**
     * Sets the name_local value for this BPContactPerson.
     * 
     * @param name_local
     */
    public void setName_local(java.lang.String name_local) {
        this.name_local = name_local;
    }


    /**
     * Gets the midname_local value for this BPContactPerson.
     * 
     * @return midname_local
     */
    public java.lang.String getMidname_local() {
        return midname_local;
    }


    /**
     * Sets the midname_local value for this BPContactPerson.
     * 
     * @param midname_local
     */
    public void setMidname_local(java.lang.String midname_local) {
        this.midname_local = midname_local;
    }


    /**
     * Gets the sex value for this BPContactPerson.
     * 
     * @return sex
     */
    public java.lang.String getSex() {
        return sex;
    }


    /**
     * Sets the sex value for this BPContactPerson.
     * 
     * @param sex
     */
    public void setSex(java.lang.String sex) {
        this.sex = sex;
    }


    /**
     * Gets the birthday value for this BPContactPerson.
     * 
     * @return birthday
     */
    public java.util.Date getBirthday() {
        return birthday;
    }


    /**
     * Sets the birthday value for this BPContactPerson.
     * 
     * @param birthday
     */
    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPContactPerson)) return false;
        BPContactPerson other = (BPContactPerson) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.relationship_type==null && other.getRelationship_type()==null) || 
             (this.relationship_type!=null &&
              this.relationship_type.equals(other.getRelationship_type()))) &&
            ((this.position==null && other.getPosition()==null) || 
             (this.position!=null &&
              this.position.equals(other.getPosition()))) &&
            ((this.surname_global==null && other.getSurname_global()==null) || 
             (this.surname_global!=null &&
              this.surname_global.equals(other.getSurname_global()))) &&
            ((this.name_global==null && other.getName_global()==null) || 
             (this.name_global!=null &&
              this.name_global.equals(other.getName_global()))) &&
            ((this.mid_name_global==null && other.getMid_name_global()==null) || 
             (this.mid_name_global!=null &&
              this.mid_name_global.equals(other.getMid_name_global()))) &&
            ((this.surname_local==null && other.getSurname_local()==null) || 
             (this.surname_local!=null &&
              this.surname_local.equals(other.getSurname_local()))) &&
            ((this.name_local==null && other.getName_local()==null) || 
             (this.name_local!=null &&
              this.name_local.equals(other.getName_local()))) &&
            ((this.midname_local==null && other.getMidname_local()==null) || 
             (this.midname_local!=null &&
              this.midname_local.equals(other.getMidname_local()))) &&
            ((this.sex==null && other.getSex()==null) || 
             (this.sex!=null &&
              this.sex.equals(other.getSex()))) &&
            ((this.birthday==null && other.getBirthday()==null) || 
             (this.birthday!=null &&
              this.birthday.equals(other.getBirthday())));
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
        if (getRelationship_type() != null) {
            _hashCode += getRelationship_type().hashCode();
        }
        if (getPosition() != null) {
            _hashCode += getPosition().hashCode();
        }
        if (getSurname_global() != null) {
            _hashCode += getSurname_global().hashCode();
        }
        if (getName_global() != null) {
            _hashCode += getName_global().hashCode();
        }
        if (getMid_name_global() != null) {
            _hashCode += getMid_name_global().hashCode();
        }
        if (getSurname_local() != null) {
            _hashCode += getSurname_local().hashCode();
        }
        if (getName_local() != null) {
            _hashCode += getName_local().hashCode();
        }
        if (getMidname_local() != null) {
            _hashCode += getMidname_local().hashCode();
        }
        if (getSex() != null) {
            _hashCode += getSex().hashCode();
        }
        if (getBirthday() != null) {
            _hashCode += getBirthday().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BPContactPerson.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:search", "BPContactPerson"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationship_type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relationship_type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("position");
        elemField.setXmlName(new javax.xml.namespace.QName("", "position"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surname_global");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surname_global"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name_global");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name_global"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mid_name_global");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mid_name_global"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surname_local");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surname_local"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name_local");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name_local"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("midname_local");
        elemField.setXmlName(new javax.xml.namespace.QName("", "midname_local"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sex");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthday");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return fileType metadata object
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
