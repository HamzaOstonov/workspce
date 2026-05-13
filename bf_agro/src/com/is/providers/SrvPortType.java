/**
 * SrvPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.providers;

public interface SrvPortType extends java.rmi.Remote {
    public com.is.providers.CheckPerResp checkPer(com.is.providers.Credentials cr, java.util.Date startDate, java.util.Date endDate) throws java.rmi.RemoteException;
    public com.is.providers.CheckTrResp checkTr(com.is.providers.Credentials cr, java.lang.Long id) throws java.rmi.RemoteException;
    public com.is.providers.Res[] providerList() throws java.rmi.RemoteException;
    public com.is.providers.PayResp pay(com.is.providers.Credentials cr, com.is.providers.Payment pay, com.is.providers.AddInfo addInfo) throws java.rmi.RemoteException;
    public com.is.providers.PayResp check(com.is.providers.Credentials cr, com.is.providers.Payment pay) throws java.rmi.RemoteException;
    public com.is.providers.Res[] respCodes() throws java.rmi.RemoteException;
    public com.is.providers.ListTrResp listTr(com.is.providers.Credentials cr, java.util.Date startDate, java.util.Date endDate) throws java.rmi.RemoteException;
}
