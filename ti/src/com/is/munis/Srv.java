/**
 * Srv.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.munis;

public interface Srv extends java.rmi.Remote {
    public com.is.munis.Res[] providerList() throws java.rmi.RemoteException;
    public com.is.munis.ListTrResp listTr(com.is.munis.Credentials arg0, java.util.Calendar arg1, java.util.Calendar arg2) throws java.rmi.RemoteException;
    public com.is.munis.PayResp check(com.is.munis.Credentials arg0, com.is.munis.Payment arg1) throws java.rmi.RemoteException;
    public com.is.munis.CheckPerResp checkPer(com.is.munis.Credentials arg0, java.util.Calendar arg1, java.util.Calendar arg2) throws java.rmi.RemoteException;
    public com.is.munis.Res[] respCodes() throws java.rmi.RemoteException;
    public com.is.munis.PayResp pay(com.is.munis.Credentials arg0, com.is.munis.Payment arg1, com.is.munis.AddInfo arg2) throws java.rmi.RemoteException;
    public com.is.munis.CheckTrResp checkTr(com.is.munis.Credentials arg0, long arg1) throws java.rmi.RemoteException;
}
