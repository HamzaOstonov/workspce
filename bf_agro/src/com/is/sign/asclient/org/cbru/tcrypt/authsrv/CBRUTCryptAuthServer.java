/**
 * CBRUTCryptAuthServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.sign.asclient.org.cbru.tcrypt.authsrv;

public interface CBRUTCryptAuthServer extends javax.xml.rpc.Service {

/**
 * Central Bank of Uzbekistan Authentification and Cryptography Server
 */
    public java.lang.String getCBRUTCryptAuthServerSoapAddress();

    public com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap getCBRUTCryptAuthServerSoap() throws javax.xml.rpc.ServiceException;

    public com.is.sign.asclient.org.cbru.tcrypt.authsrv.CBRUTCryptAuthServerSoap getCBRUTCryptAuthServerSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
