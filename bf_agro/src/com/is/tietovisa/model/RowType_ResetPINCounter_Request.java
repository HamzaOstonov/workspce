/**
 * RowType_ResetPINCounter_Request.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.tietovisa.model;

public class RowType_ResetPINCounter_Request  implements java.io.Serializable {
    private java.lang.String CARD;

    private java.lang.String EXPIRY;

    private java.lang.String TEXT;

    private java.math.BigInteger OFFLINE;

    public RowType_ResetPINCounter_Request() {
    }

    public RowType_ResetPINCounter_Request(
           java.lang.String CARD,
           java.lang.String EXPIRY,
           java.lang.String TEXT,
           java.math.BigInteger OFFLINE) {
           this.CARD = CARD;
           this.EXPIRY = EXPIRY;
           this.TEXT = TEXT;
           this.OFFLINE = OFFLINE;
    }


    /**
     * Gets the CARD value for this RowType_ResetPINCounter_Request.
     * 
     * @return CARD
     */
    public java.lang.String getCARD() {
        return CARD;
    }


    /**
     * Sets the CARD value for this RowType_ResetPINCounter_Request.
     * 
     * @param CARD
     */
    public void setCARD(java.lang.String CARD) {
        this.CARD = CARD;
    }


    /**
     * Gets the EXPIRY value for this RowType_ResetPINCounter_Request.
     * 
     * @return EXPIRY
     */
    public java.lang.String getEXPIRY() {
        return EXPIRY;
    }


    /**
     * Sets the EXPIRY value for this RowType_ResetPINCounter_Request.
     * 
     * @param EXPIRY
     */
    public void setEXPIRY(java.lang.String EXPIRY) {
        this.EXPIRY = EXPIRY;
    }


    /**
     * Gets the TEXT value for this RowType_ResetPINCounter_Request.
     * 
     * @return TEXT
     */
    public java.lang.String getTEXT() {
        return TEXT;
    }


    /**
     * Sets the TEXT value for this RowType_ResetPINCounter_Request.
     * 
     * @param TEXT
     */
    public void setTEXT(java.lang.String TEXT) {
        this.TEXT = TEXT;
    }


    /**
     * Gets the OFFLINE value for this RowType_ResetPINCounter_Request.
     * 
     * @return OFFLINE
     */
    public java.math.BigInteger getOFFLINE() {
        return OFFLINE;
    }


    /**
     * Sets the OFFLINE value for this RowType_ResetPINCounter_Request.
     * 
     * @param OFFLINE
     */
    public void setOFFLINE(java.math.BigInteger OFFLINE) {
        this.OFFLINE = OFFLINE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RowType_ResetPINCounter_Request)) return false;
        RowType_ResetPINCounter_Request other = (RowType_ResetPINCounter_Request) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CARD==null && other.getCARD()==null) || 
             (this.CARD!=null &&
              this.CARD.equals(other.getCARD()))) &&
            ((this.EXPIRY==null && other.getEXPIRY()==null) || 
             (this.EXPIRY!=null &&
              this.EXPIRY.equals(other.getEXPIRY()))) &&
            ((this.TEXT==null && other.getTEXT()==null) || 
             (this.TEXT!=null &&
              this.TEXT.equals(other.getTEXT()))) &&
            ((this.OFFLINE==null && other.getOFFLINE()==null) || 
             (this.OFFLINE!=null &&
              this.OFFLINE.equals(other.getOFFLINE())));
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
        if (getCARD() != null) {
            _hashCode += getCARD().hashCode();
        }
        if (getEXPIRY() != null) {
            _hashCode += getEXPIRY().hashCode();
        }
        if (getTEXT() != null) {
            _hashCode += getTEXT().hashCode();
        }
        if (getOFFLINE() != null) {
            _hashCode += getOFFLINE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

  
}
