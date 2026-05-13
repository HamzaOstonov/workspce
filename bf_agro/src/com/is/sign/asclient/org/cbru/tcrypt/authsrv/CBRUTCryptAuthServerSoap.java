/**
 * CBRUTCryptAuthServerSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.is.sign.asclient.org.cbru.tcrypt.authsrv;

public interface CBRUTCryptAuthServerSoap extends java.rmi.Remote {

    /**
     * Получить текушую версию интерфейса
     */
    public java.lang.String getVersion() throws java.rmi.RemoteException;

    /**
     * Получить сертификат ЭЦП сервера X.509
     */
    public byte[] getCertificateSign() throws java.rmi.RemoteException;

    /**
     * Получить корневой сертификат сервера X.509
     */
    public byte[] getCertificateRoot() throws java.rmi.RemoteException;

    /**
     * Получить сертификат ЭЦП сервера X.509 в формате BASE64
     */
    public java.lang.String getCertificateSignAS() throws java.rmi.RemoteException;

    /**
     * Получить корневой сертификат сервера в формате BASE64
     */
    public java.lang.String getCertificateRootAS() throws java.rmi.RemoteException;

    /**
     * Метод создает документ PKCS#7 для строкового сообщения
     */
    public byte[] signString(java.lang.String source, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод создает документ PKCS#7 для двоичного сообщения
     */
    public byte[] signMessage(byte[] source, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод создает документ PKCS#7 в формате BASE64 для строкового
     * сообщения в формате BASE64
     */
    public java.lang.String signStringAS(java.lang.String source, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод создает документ PKCS#7 в формате BASE64 для строкового
     * сообщения с учетом кодовой страницы
     */
    public java.lang.String signStringASCodepage(java.lang.String source, java.lang.String docnum, int codepage) throws java.rmi.RemoteException;

    /**
     * Метод создает документ PKCS#7 в формате BASE64 для двоичного
     * сообщения
     */
    public java.lang.String signMessageAS(byte[] source, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет контейнер PKCS#7
     */
    public boolean verifyCms(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет контейнер PKCS#7 и добавляеи ЭЦП сервера
     */
    public byte[] verifyCmsAndSign(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет PKCS#7 контейнер с возвратом расширенных результатов
     */
    public java.lang.String[] verifyCmsEx(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет контейнер PKCS#7, созданный CAPICOM
     */
    public boolean verifyCmsCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет PKCS#7 контейнер, созданный CAPICOM, с возвратом
     * расширенных результатов
     */
    public java.lang.String[] verifyCmsExCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет документ PKCS#7, созданный AS Client
     */
    public boolean verifyCmsAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет PKCS#7 документ в формате BASE64 с возвратом
     * расширенных результатов
     */
    public java.lang.String[] verifyCmsExAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод проверяет PKCS#7 документ в формате BASE64 с возвратом
     * исходного содержимого и расширенных результатов
     */
    public java.lang.String[] verifyAndGetCmsContentAS(java.lang.String cms, java.lang.String docnum, int codepage) throws java.rmi.RemoteException;

    /**
     * Метод проверяет PKCS#7 документ в формате BASE64 с возвратом
     * исходного содержимого в управляемом формате и расширенных результатов
     */
    public java.lang.String[] verifyAndGetCmsContentASEx(java.lang.String cms, java.lang.String docnum, int codepage, int output) throws java.rmi.RemoteException;

    /**
     * Метод возвращает исходное содержимое PKCS#7 документа
     */
    public byte[] getCmsContent(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает исходное содержимое PKCS#7 документа, созданного
     * CAPICOM
     */
    public java.lang.String getCmsContentCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает исходное содержимое PKCS#7 документа в формате
     * BASE64
     */
    public java.lang.String getCmsContentAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает исходное содержимое PKCS#7 документа в формате
     * BASE64 и выбранной кодировкой
     */
    public java.lang.String getCmsContentASCodepage(java.lang.String cms, java.lang.String docnum, int codepage) throws java.rmi.RemoteException;

    /**
     * Метод возвращает сертификат пользователя (последний) из PKCS#7
     * документа
     */
    public byte[] getCmsLastCertificate(byte[] cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает сертификат пользователя (последний) из PKCS#7
     * документа, созданного CAPICOM
     */
    public byte[] getCmsLastCertificateCAPICOM(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает сертификат пользователя (последний) из PKCS#7
     * контейнера в формате BASE64
     */
    public java.lang.String getCmsLastCertificateAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает все сертификаты из PKCS#7 контейнера в формате
     * BASE64
     */
    public java.lang.String[] getCmsCertificatesAS(java.lang.String cms, java.lang.String docnum) throws java.rmi.RemoteException;

    /**
     * Метод возвращает исходное содержимое PKCS#7 документа в формате
     * BASE64 и выбранной кодировкой, а также идентификатор пользователя
     */
    public java.lang.String[] CBRUGetCmsContent(java.lang.String cms, java.lang.String docnum, int codepage) throws java.rmi.RemoteException;

    /**
     * Метод возвращает список идентификаторов и времени выполнения
     * ЭЦП всех пользователей документа PKCS#7
     */
    public java.lang.String[] CBRUGetCmsUserIdAndTimeList(java.lang.String cms) throws java.rmi.RemoteException;

    /**
     * Метод возвращает список идентификаторов и добавленные параметры
     * всех пользователей документа PKCS#7
     */
    public java.lang.String[] CBRUGetCmsUserIdAndDocList(java.lang.String cms) throws java.rmi.RemoteException;

    /**
     * Метод возвращает список идентификаторов всех пользователей
     * документа PKCS#7
     */
    public java.lang.String[] CBRUGetCmsUserIdList(java.lang.String cms) throws java.rmi.RemoteException;
}
