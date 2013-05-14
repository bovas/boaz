/**
 * AdvanceERxService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.glaceemr.erxin.axis.AdvanceERxService_jws;

public interface AdvanceERxService extends java.rmi.Remote {
    public void invoke(java.lang.Object msgContext) throws java.rmi.RemoteException;
    public java.lang.String attachment(java.lang.String fileName, java.lang.String binaryData) throws java.rmi.RemoteException;
    public java.util.Vector getFormularyDetail(java.lang.String brandName, java.lang.String form, java.lang.String strength, java.lang.String route, java.lang.String drugName, java.lang.String pageNo, java.util.Vector details) throws java.rmi.RemoteException;
    public java.util.Vector getFormularyDetailWithNDCCode(java.lang.String NDCCode, java.util.Vector details) throws java.rmi.RemoteException;
    public java.util.Vector getTherapeuticAlternativeDetails(java.lang.String drugName, java.lang.String form, java.lang.String strength, java.lang.String teCode, java.lang.String ndc_Code, java.util.Vector details) throws java.rmi.RemoteException;
    public java.util.Vector getTherapeuticDrugDetail(java.lang.String drugName, java.lang.String form, java.lang.String strength, java.lang.String teCode, DefaultNamespace.DatabaseConn dbUtils, java.lang.String payerId, java.lang.String alternativeId, java.lang.String ndcCode, java.lang.String coverageId, java.lang.String formularyId) throws java.rmi.RemoteException;
    public java.util.Vector getDrugDetails(java.lang.String NDCCode, java.lang.String payerId, java.lang.String alternativeId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchAlternativeDetail(java.lang.String alternativeId, java.lang.String NDCCode, java.lang.String payerId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchCopayDetail(java.lang.String copayId, java.lang.String NDCCode, java.lang.String payerId, java.lang.String forStatus, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchCoverageDetail(java.lang.String coverageId, java.lang.String NDCCode, java.lang.String payerId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchExclusion(java.lang.String NDCCode, java.lang.String payerId, java.lang.String coverageId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchStatus(java.lang.String formularyId, java.lang.String NDCCode, java.lang.String payerId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchStatus(java.lang.String formularyId, java.lang.String payerId, java.lang.String drugType, java.lang.String proStatus, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException;
    public java.util.Vector searchDrug(java.lang.String brandName, java.lang.String form, java.lang.String strength, java.lang.String route, java.lang.String drugName, DefaultNamespace.DatabaseConn dbUtils, java.lang.String formularyId, java.lang.String coverageId, java.lang.String payerId) throws java.rmi.RemoteException;
    public java.lang.String sendTransaction(java.lang.String urlString, java.lang.String transaction, boolean isXML) throws java.rmi.RemoteException;
    public java.lang.String stripNonValidXMLCharacters(java.lang.String in) throws java.rmi.RemoteException;
    public java.lang.String getAdvanceeRxEncodedCredentials() throws java.rmi.RemoteException;
    public java.lang.String decode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException;
    public java.lang.Object decode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException;
    public java.lang.String encode(java.lang.String value, java.lang.String charset) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException;
    public java.lang.String encode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException;
    public java.lang.Object encode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException;
    public java.lang.String getDefaultCharset() throws java.rmi.RemoteException;
}
