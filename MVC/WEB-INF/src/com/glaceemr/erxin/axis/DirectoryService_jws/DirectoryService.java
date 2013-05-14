/**
 * DirectoryService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.glaceemr.erxin.axis.DirectoryService_jws;

public interface DirectoryService extends java.rmi.Remote {
    public java.lang.String getResponse(java.lang.Object input, java.lang.String isDirectoryService) throws java.rmi.RemoteException;
    public java.lang.String getUTCTime() throws java.rmi.RemoteException;
    public java.lang.String insertData(java.lang.String SPI, java.lang.String URL, java.lang.String accountId) throws java.rmi.RemoteException;
    public java.lang.String convertToDoubleDigit(int value) throws java.rmi.RemoteException;
    public java.lang.String writeFiles1(java.lang.String str, java.lang.String isDirectoryService, java.lang.String accountId) throws java.rmi.RemoteException;
    public java.lang.String writeFiles(java.lang.String str, java.lang.String isDirectoryService, java.lang.String accountId) throws java.rmi.RemoteException;
    public java.lang.String getEncodedCredentials() throws java.rmi.RemoteException;
    public java.lang.String decode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException;
    public java.lang.Object decode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException;
    public java.lang.String encode(java.lang.String value, java.lang.String charset) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException;
    public java.lang.String encode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException;
    public java.lang.Object encode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException;
    public java.lang.String getDefaultCharset() throws java.rmi.RemoteException;
}
