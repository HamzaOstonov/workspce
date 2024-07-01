/**
 * BPSearchResponceOrganization.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package search.NCI.com.ipakyulibank;

public class BPSearchResponceOrganization  implements java.io.Serializable {
    private java.lang.String id_client_sap;

    private java.lang.String id_client_nci;

    private java.lang.String branch;

    private java.lang.String name;

    private java.lang.String name_short;

    private java.lang.String type;
    
    private java.lang.String opf;

    private java.lang.String inn;
    
    private search.NCI.com.ipakyulibank.BPContactPerson[] contactPerson;

    public BPSearchResponceOrganization() {
    }

    public BPSearchResponceOrganization(
           java.lang.String id_client_sap,
           java.lang.String id_client_nci,
           java.lang.String branch,
           java.lang.String name,
           java.lang.String name_short,
           java.lang.String type,
           java.lang.String opf,
           java.lang.String inn,
           search.NCI.com.ipakyulibank.BPContactPerson[] contactPerson) {
           this.id_client_sap = id_client_sap;
           this.id_client_nci = id_client_nci;
           this.branch = branch;
           this.name = name;
           this.name_short = name_short;
           this.type = type;
           this.opf = opf;
           this.inn = inn;
           this.contactPerson = contactPerson;
    }


    /**
     * Gets the id_client_sap value for this BPSearchResponceOrganization.
     * 
     * @return id_client_sap
     */
    public java.lang.String getId_client_sap() {
        return id_client_sap;
    }


    /**
     * Sets the id_client_sap value for this BPSearchResponceOrganization.
     * 
     * @param id_client_sap
     */
    public void setId_client_sap(java.lang.String id_client_sap) {
        this.id_client_sap = id_client_sap;
    }


    /**
     * Gets the id_client_nci value for this BPSearchResponceOrganization.
     * 
     * @return id_client_nci
     */
    public java.lang.String getId_client_nci() {
        return id_client_nci;
    }


    /**
     * Sets the id_client_nci value for this BPSearchResponceOrganization.
     * 
     * @param id_client_nci
     */
    public void setId_client_nci(java.lang.String id_client_nci) {
        this.id_client_nci = id_client_nci;
    }


    /**
     * Gets the branch value for this BPSearchResponceOrganization.
     * 
     * @return branch
     */
    public java.lang.String getBranch() {
        return branch;
    }


    /**
     * Sets the branch value for this BPSearchResponceOrganization.
     * 
     * @param branch
     */
    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }


    /**
     * Gets the name value for this BPSearchResponceOrganization.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this BPSearchResponceOrganization.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the name_short value for this BPSearchResponceOrganization.
     * 
     * @return name_short
     */
    public java.lang.String getName_short() {
        return name_short;
    }


    /**
     * Sets the name_short value for this BPSearchResponceOrganization.
     * 
     * @param name_short
     */
    public void setName_short(java.lang.String name_short) {
        this.name_short = name_short;
    }


    /**
     * Gets the fileType value for this BPSearchResponceOrganization.
     * 
     * @return fileType
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the fileType value for this BPSearchResponceOrganization.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the opf value for this BPSearchResponceOrganization.
     * 
     * @return opf
     */
    public java.lang.String getOpf() {
        return opf;
    }


    /**
     * Sets the opf value for this BPSearchResponceOrganization.
     * 
     * @param opf
     */
    public void setOpf(java.lang.String opf) {
        this.opf = opf;
    }


    /**
     * Gets the contactPerson value for this BPSearchResponceOrganization.
     * 
     * @return contactPerson
     */
    public search.NCI.com.ipakyulibank.BPContactPerson[] getContactPerson() {
        return contactPerson;
    }


    /**
     * Sets the contactPerson value for this BPSearchResponceOrganization.
     * 
     * @param contactPerson
     */
    public void setContactPerson(search.NCI.com.ipakyulibank.BPContactPerson[] contactPerson) {
        this.contactPerson = contactPerson;
    }

    public search.NCI.com.ipakyulibank.BPContactPerson getContactPerson(int i) {
        return this.contactPerson[i];
    }

    public void setContactPerson(int i, search.NCI.com.ipakyulibank.BPContactPerson _value) {
        this.contactPerson[i] = _value;
    }

    
    public void setInn(java.lang.String inn) {
        this.inn = inn;
    }

    public java.lang.String getInn() {
        return inn;
    }


    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BPSearchResponceOrganization)) return false;
        BPSearchResponceOrganization other = (BPSearchResponceOrganization) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id_client_sap==null && other.getId_client_sap()==null) || 
             (this.id_client_sap!=null &&
              this.id_client_sap.equals(other.getId_client_sap()))) &&
            ((this.id_client_nci==null && other.getId_client_nci()==null) || 
             (this.id_client_nci!=null &&
              this.id_client_nci.equals(other.getId_client_nci()))) &&
            ((this.branch==null && other.getBranch()==null) || 
             (this.branch!=null &&
              this.branch.equals(other.getBranch()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.name_short==null && other.getName_short()==null) || 
             (this.name_short!=null &&
              this.name_short.equals(other.getName_short()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.opf==null && other.getOpf()==null) || 
             (this.opf!=null &&
              this.opf.equals(other.getOpf()))) &&
            ((this.contactPerson==null && other.getContactPerson()==null) || 
             (this.contactPerson!=null &&
              java.util.Arrays.equals(this.contactPerson, other.getContactPerson())));
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
        if (getId_client_sap() != null) {
            _hashCode += getId_client_sap().hashCode();
        }
        if (getId_client_nci() != null) {
            _hashCode += getId_client_nci().hashCode();
        }
        if (getBranch() != null) {
            _hashCode += getBranch().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getName_short() != null) {
            _hashCode += getName_short().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getOpf() != null) {
            _hashCode += getOpf().hashCode();
        }
        if (getContactPerson() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContactPerson());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContactPerson(), i);
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
        new org.apache.axis.description.TypeDesc(BPSearchResponceOrganization.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:search", ">BPSearchResponce>organization"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_client_sap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_client_sap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_client_nci");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id_client_nci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branch");
        elemField.setXmlName(new javax.xml.namespace.QName("", "branch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name_short");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name_short"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fileType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "opf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactPerson");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactPerson"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:ipakyulibank.com:NCI:search", "BPContactPerson"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
