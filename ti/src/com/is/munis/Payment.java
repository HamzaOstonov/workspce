/**
 * Payment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public class Payment  implements java.io.Serializable {
    private int amount;

    private java.lang.String branch;

    private java.lang.String currency;

    private java.lang.String district;

    private java.util.Calendar from_date;

    private java.lang.String from_value;

    private long id;

    private long munis_id;

    private java.lang.String p_name;

    private java.lang.String p_number;

    private int provider_id;

    private int service_id;

    private int state;

    private java.lang.String terminal_id;

    private java.util.Calendar time_stamp;

    private java.util.Calendar to_date;

    private java.lang.String to_value;

    private long tr_id;

    public Payment() {
    }

    public Payment(
           int amount,
           java.lang.String branch,
           java.lang.String currency,
           java.lang.String district,
           java.util.Calendar from_date,
           java.lang.String from_value,
           long id,
           long munis_id,
           java.lang.String p_name,
           java.lang.String p_number,
           int provider_id,
           int service_id,
           int state,
           java.lang.String terminal_id,
           java.util.Calendar time_stamp,
           java.util.Calendar to_date,
           java.lang.String to_value,
           long tr_id) {
           this.amount = amount;
           this.branch = branch;
           this.currency = currency;
           this.district = district;
           this.from_date = from_date;
           this.from_value = from_value;
           this.id = id;
           this.munis_id = munis_id;
           this.p_name = p_name;
           this.p_number = p_number;
           this.provider_id = provider_id;
           this.service_id = service_id;
           this.state = state;
           this.terminal_id = terminal_id;
           this.time_stamp = time_stamp;
           this.to_date = to_date;
           this.to_value = to_value;
           this.tr_id = tr_id;
    }


    /**
     * Gets the amount value for this Payment.
     * 
     * @return amount
     */
    public int getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this Payment.
     * 
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }


    /**
     * Gets the branch value for this Payment.
     * 
     * @return branch
     */
    public java.lang.String getBranch() {
        return branch;
    }


    /**
     * Sets the branch value for this Payment.
     * 
     * @param branch
     */
    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }


    /**
     * Gets the currency value for this Payment.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this Payment.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }


    /**
     * Gets the district value for this Payment.
     * 
     * @return district
     */
    public java.lang.String getDistrict() {
        return district;
    }


    /**
     * Sets the district value for this Payment.
     * 
     * @param district
     */
    public void setDistrict(java.lang.String district) {
        this.district = district;
    }


    /**
     * Gets the from_date value for this Payment.
     * 
     * @return from_date
     */
    public java.util.Calendar getFrom_date() {
        return from_date;
    }


    /**
     * Sets the from_date value for this Payment.
     * 
     * @param from_date
     */
    public void setFrom_date(java.util.Calendar from_date) {
        this.from_date = from_date;
    }


    /**
     * Gets the from_value value for this Payment.
     * 
     * @return from_value
     */
    public java.lang.String getFrom_value() {
        return from_value;
    }


    /**
     * Sets the from_value value for this Payment.
     * 
     * @param from_value
     */
    public void setFrom_value(java.lang.String from_value) {
        this.from_value = from_value;
    }


    /**
     * Gets the id value for this Payment.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this Payment.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the munis_id value for this Payment.
     * 
     * @return munis_id
     */
    public long getMunis_id() {
        return munis_id;
    }


    /**
     * Sets the munis_id value for this Payment.
     * 
     * @param munis_id
     */
    public void setMunis_id(long munis_id) {
        this.munis_id = munis_id;
    }


    /**
     * Gets the p_name value for this Payment.
     * 
     * @return p_name
     */
    public java.lang.String getP_name() {
        return p_name;
    }


    /**
     * Sets the p_name value for this Payment.
     * 
     * @param p_name
     */
    public void setP_name(java.lang.String p_name) {
        this.p_name = p_name;
    }


    /**
     * Gets the p_number value for this Payment.
     * 
     * @return p_number
     */
    public java.lang.String getP_number() {
        return p_number;
    }


    /**
     * Sets the p_number value for this Payment.
     * 
     * @param p_number
     */
    public void setP_number(java.lang.String p_number) {
        this.p_number = p_number;
    }


    /**
     * Gets the provider_id value for this Payment.
     * 
     * @return provider_id
     */
    public int getProvider_id() {
        return provider_id;
    }


    /**
     * Sets the provider_id value for this Payment.
     * 
     * @param provider_id
     */
    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }


    /**
     * Gets the service_id value for this Payment.
     * 
     * @return service_id
     */
    public int getService_id() {
        return service_id;
    }


    /**
     * Sets the service_id value for this Payment.
     * 
     * @param service_id
     */
    public void setService_id(int service_id) {
        this.service_id = service_id;
    }


    /**
     * Gets the state value for this Payment.
     * 
     * @return state
     */
    public int getState() {
        return state;
    }


    /**
     * Sets the state value for this Payment.
     * 
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }


    /**
     * Gets the terminal_id value for this Payment.
     * 
     * @return terminal_id
     */
    public java.lang.String getTerminal_id() {
        return terminal_id;
    }


    /**
     * Sets the terminal_id value for this Payment.
     * 
     * @param terminal_id
     */
    public void setTerminal_id(java.lang.String terminal_id) {
        this.terminal_id = terminal_id;
    }


    /**
     * Gets the time_stamp value for this Payment.
     * 
     * @return time_stamp
     */
    public java.util.Calendar getTime_stamp() {
        return time_stamp;
    }


    /**
     * Sets the time_stamp value for this Payment.
     * 
     * @param time_stamp
     */
    public void setTime_stamp(java.util.Calendar time_stamp) {
        this.time_stamp = time_stamp;
    }


    /**
     * Gets the to_date value for this Payment.
     * 
     * @return to_date
     */
    public java.util.Calendar getTo_date() {
        return to_date;
    }


    /**
     * Sets the to_date value for this Payment.
     * 
     * @param to_date
     */
    public void setTo_date(java.util.Calendar to_date) {
        this.to_date = to_date;
    }


    /**
     * Gets the to_value value for this Payment.
     * 
     * @return to_value
     */
    public java.lang.String getTo_value() {
        return to_value;
    }


    /**
     * Sets the to_value value for this Payment.
     * 
     * @param to_value
     */
    public void setTo_value(java.lang.String to_value) {
        this.to_value = to_value;
    }


    /**
     * Gets the tr_id value for this Payment.
     * 
     * @return tr_id
     */
    public long getTr_id() {
        return tr_id;
    }


    /**
     * Sets the tr_id value for this Payment.
     * 
     * @param tr_id
     */
    public void setTr_id(long tr_id) {
        this.tr_id = tr_id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Payment)) return false;
        Payment other = (Payment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.amount == other.getAmount() &&
            ((this.branch==null && other.getBranch()==null) || 
             (this.branch!=null &&
              this.branch.equals(other.getBranch()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.district==null && other.getDistrict()==null) || 
             (this.district!=null &&
              this.district.equals(other.getDistrict()))) &&
            ((this.from_date==null && other.getFrom_date()==null) || 
             (this.from_date!=null &&
              this.from_date.equals(other.getFrom_date()))) &&
            ((this.from_value==null && other.getFrom_value()==null) || 
             (this.from_value!=null &&
              this.from_value.equals(other.getFrom_value()))) &&
            this.id == other.getId() &&
            this.munis_id == other.getMunis_id() &&
            ((this.p_name==null && other.getP_name()==null) || 
             (this.p_name!=null &&
              this.p_name.equals(other.getP_name()))) &&
            ((this.p_number==null && other.getP_number()==null) || 
             (this.p_number!=null &&
              this.p_number.equals(other.getP_number()))) &&
            this.provider_id == other.getProvider_id() &&
            this.service_id == other.getService_id() &&
            this.state == other.getState() &&
            ((this.terminal_id==null && other.getTerminal_id()==null) || 
             (this.terminal_id!=null &&
              this.terminal_id.equals(other.getTerminal_id()))) &&
            ((this.time_stamp==null && other.getTime_stamp()==null) || 
             (this.time_stamp!=null &&
              this.time_stamp.equals(other.getTime_stamp()))) &&
            ((this.to_date==null && other.getTo_date()==null) || 
             (this.to_date!=null &&
              this.to_date.equals(other.getTo_date()))) &&
            ((this.to_value==null && other.getTo_value()==null) || 
             (this.to_value!=null &&
              this.to_value.equals(other.getTo_value()))) &&
            this.tr_id == other.getTr_id();
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
        _hashCode += getAmount();
        if (getBranch() != null) {
            _hashCode += getBranch().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getDistrict() != null) {
            _hashCode += getDistrict().hashCode();
        }
        if (getFrom_date() != null) {
            _hashCode += getFrom_date().hashCode();
        }
        if (getFrom_value() != null) {
            _hashCode += getFrom_value().hashCode();
        }
        _hashCode += new Long(getId()).hashCode();
        _hashCode += new Long(getMunis_id()).hashCode();
        if (getP_name() != null) {
            _hashCode += getP_name().hashCode();
        }
        if (getP_number() != null) {
            _hashCode += getP_number().hashCode();
        }
        _hashCode += getProvider_id();
        _hashCode += getService_id();
        _hashCode += getState();
        if (getTerminal_id() != null) {
            _hashCode += getTerminal_id().hashCode();
        }
        if (getTime_stamp() != null) {
            _hashCode += getTime_stamp().hashCode();
        }
        if (getTo_date() != null) {
            _hashCode += getTo_date().hashCode();
        }
        if (getTo_value() != null) {
            _hashCode += getTo_value().hashCode();
        }
        _hashCode += new Long(getTr_id()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Payment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://munis.is.com/", "payment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("district");
        elemField.setXmlName(new javax.xml.namespace.QName("", "district"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "from_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "from_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("munis_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "munis_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("p_number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "p_number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provider_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "provider_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("service_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "service_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminal_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminal_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("time_stamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "time_stamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to_date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "to_date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "to_value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tr_id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tr_id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
