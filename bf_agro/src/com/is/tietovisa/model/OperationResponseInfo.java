package com.is.tietovisa.model;

public class OperationResponseInfo  implements java.io.Serializable {
    private java.math.BigInteger response_code;

    private java.lang.String error_description;

    private java.lang.String error_action;

    private java.lang.String EXTERNAL_SESSION_ID;

    public OperationResponseInfo() {
    }

    public OperationResponseInfo(
           java.math.BigInteger response_code,
           java.lang.String error_description,
           java.lang.String error_action,
           java.lang.String EXTERNAL_SESSION_ID) {
           this.response_code = response_code;
           this.error_description = error_description;
           this.error_action = error_action;
           this.EXTERNAL_SESSION_ID = EXTERNAL_SESSION_ID;
    }


    /**
     * Gets the response_code value for this OperationResponseInfo.
     * 
     * @return response_code
     */
    public java.math.BigInteger getResponse_code() {
        return response_code;
    }


    /**
     * Sets the response_code value for this OperationResponseInfo.
     * 
     * @param response_code
     */
    public void setResponse_code(java.math.BigInteger response_code) {
        this.response_code = response_code;
    }


    /**
     * Gets the error_description value for this OperationResponseInfo.
     * 
     * @return error_description
     */
    public java.lang.String getError_description() {
        return error_description;
    }


    /**
     * Sets the error_description value for this OperationResponseInfo.
     * 
     * @param error_description
     */
    public void setError_description(java.lang.String error_description) {
        this.error_description = error_description;
    }


    /**
     * Gets the error_action value for this OperationResponseInfo.
     * 
     * @return error_action
     */
    public java.lang.String getError_action() {
        return error_action;
    }


    /**
     * Sets the error_action value for this OperationResponseInfo.
     * 
     * @param error_action
     */
    public void setError_action(java.lang.String error_action) {
        this.error_action = error_action;
    }


    /**
     * Gets the EXTERNAL_SESSION_ID value for this OperationResponseInfo.
     * 
     * @return EXTERNAL_SESSION_ID
     */
    public java.lang.String getEXTERNAL_SESSION_ID() {
        return EXTERNAL_SESSION_ID;
    }


    /**
     * Sets the EXTERNAL_SESSION_ID value for this OperationResponseInfo.
     * 
     * @param EXTERNAL_SESSION_ID
     */
    public void setEXTERNAL_SESSION_ID(java.lang.String EXTERNAL_SESSION_ID) {
        this.EXTERNAL_SESSION_ID = EXTERNAL_SESSION_ID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OperationResponseInfo)) return false;
        OperationResponseInfo other = (OperationResponseInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.response_code==null && other.getResponse_code()==null) || 
             (this.response_code!=null &&
              this.response_code.equals(other.getResponse_code()))) &&
            ((this.error_description==null && other.getError_description()==null) || 
             (this.error_description!=null &&
              this.error_description.equals(other.getError_description()))) &&
            ((this.error_action==null && other.getError_action()==null) || 
             (this.error_action!=null &&
              this.error_action.equals(other.getError_action()))) &&
            ((this.EXTERNAL_SESSION_ID==null && other.getEXTERNAL_SESSION_ID()==null) || 
             (this.EXTERNAL_SESSION_ID!=null &&
              this.EXTERNAL_SESSION_ID.equals(other.getEXTERNAL_SESSION_ID())));
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
        if (getResponse_code() != null) {
            _hashCode += getResponse_code().hashCode();
        }
        if (getError_description() != null) {
            _hashCode += getError_description().hashCode();
        }
        if (getError_action() != null) {
            _hashCode += getError_action().hashCode();
        }
        if (getEXTERNAL_SESSION_ID() != null) {
            _hashCode += getEXTERNAL_SESSION_ID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
