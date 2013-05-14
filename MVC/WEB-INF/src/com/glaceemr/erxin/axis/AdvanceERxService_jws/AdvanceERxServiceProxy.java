package com.glaceemr.erxin.axis.AdvanceERxService_jws;

public class AdvanceERxServiceProxy implements com.glaceemr.erxin.axis.AdvanceERxService_jws.AdvanceERxService {
  private String _endpoint = null;
  private com.glaceemr.erxin.axis.AdvanceERxService_jws.AdvanceERxService advanceERxService = null;
  
  public AdvanceERxServiceProxy() {
    _initAdvanceERxServiceProxy();
  }
  
  public AdvanceERxServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initAdvanceERxServiceProxy();
  }
  
  private void _initAdvanceERxServiceProxy() {
    try {
      advanceERxService = (new com.glaceemr.erxin.axis.AdvanceERxService_jws.AdvanceERxServiceServiceLocator()).getAdvanceERxService();
      if (advanceERxService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)advanceERxService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)advanceERxService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (advanceERxService != null)
      ((javax.xml.rpc.Stub)advanceERxService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.glaceemr.erxin.axis.AdvanceERxService_jws.AdvanceERxService getAdvanceERxService() {
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService;
  }
  
  public void invoke(java.lang.Object msgContext) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    advanceERxService.invoke(msgContext);
  }
  
  public java.lang.String attachment(java.lang.String fileName, java.lang.String binaryData) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.attachment(fileName, binaryData);
  }
  
  public java.util.Vector getFormularyDetail(java.lang.String brandName, java.lang.String form, java.lang.String strength, java.lang.String route, java.lang.String drugName, java.lang.String pageNo, java.util.Vector details) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getFormularyDetail(brandName, form, strength, route, drugName, pageNo, details);
  }
  
  public java.util.Vector getFormularyDetailWithNDCCode(java.lang.String NDCCode, java.util.Vector details) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getFormularyDetailWithNDCCode(NDCCode, details);
  }
  
  public java.util.Vector getTherapeuticAlternativeDetails(java.lang.String drugName, java.lang.String form, java.lang.String strength, java.lang.String teCode, java.lang.String ndc_Code, java.util.Vector details) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getTherapeuticAlternativeDetails(drugName, form, strength, teCode, ndc_Code, details);
  }
  
  public java.util.Vector getTherapeuticDrugDetail(java.lang.String drugName, java.lang.String form, java.lang.String strength, java.lang.String teCode, DefaultNamespace.DatabaseConn dbUtils, java.lang.String payerId, java.lang.String alternativeId, java.lang.String ndcCode, java.lang.String coverageId, java.lang.String formularyId) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getTherapeuticDrugDetail(drugName, form, strength, teCode, dbUtils, payerId, alternativeId, ndcCode, coverageId, formularyId);
  }
  
  public java.util.Vector getDrugDetails(java.lang.String NDCCode, java.lang.String payerId, java.lang.String alternativeId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getDrugDetails(NDCCode, payerId, alternativeId, dbUtils);
  }
  
  public java.util.Vector searchAlternativeDetail(java.lang.String alternativeId, java.lang.String NDCCode, java.lang.String payerId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchAlternativeDetail(alternativeId, NDCCode, payerId, dbUtils);
  }
  
  public java.util.Vector searchCopayDetail(java.lang.String copayId, java.lang.String NDCCode, java.lang.String payerId, java.lang.String forStatus, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchCopayDetail(copayId, NDCCode, payerId, forStatus, dbUtils);
  }
  
  public java.util.Vector searchCoverageDetail(java.lang.String coverageId, java.lang.String NDCCode, java.lang.String payerId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchCoverageDetail(coverageId, NDCCode, payerId, dbUtils);
  }
  
  public java.util.Vector searchExclusion(java.lang.String NDCCode, java.lang.String payerId, java.lang.String coverageId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchExclusion(NDCCode, payerId, coverageId, dbUtils);
  }
  
  public java.util.Vector searchStatus(java.lang.String formularyId, java.lang.String NDCCode, java.lang.String payerId, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchStatus(formularyId, NDCCode, payerId, dbUtils);
  }
  
  public java.util.Vector searchStatus(java.lang.String formularyId, java.lang.String payerId, java.lang.String drugType, java.lang.String proStatus, DefaultNamespace.DatabaseConn dbUtils) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchStatus(formularyId, payerId, drugType, proStatus, dbUtils);
  }
  
  public java.util.Vector searchDrug(java.lang.String brandName, java.lang.String form, java.lang.String strength, java.lang.String route, java.lang.String drugName, DefaultNamespace.DatabaseConn dbUtils, java.lang.String formularyId, java.lang.String coverageId, java.lang.String payerId) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.searchDrug(brandName, form, strength, route, drugName, dbUtils, formularyId, coverageId, payerId);
  }
  
  public java.lang.String sendTransaction(java.lang.String urlString, java.lang.String transaction, boolean isXML) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.sendTransaction(urlString, transaction, isXML);
  }
  
  public java.lang.String stripNonValidXMLCharacters(java.lang.String in) throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.stripNonValidXMLCharacters(in);
  }
  
  public java.lang.String getAdvanceeRxEncodedCredentials() throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getAdvanceeRxEncodedCredentials();
  }
  
  public java.lang.String decode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.decode(value);
  }
  
  public java.lang.Object decode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.decode(value);
  }
  
  public java.lang.String encode(java.lang.String value, java.lang.String charset) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.encode(value, charset);
  }
  
  public java.lang.String encode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.encode(value);
  }
  
  public java.lang.Object encode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.encode(value);
  }
  
  public java.lang.String getDefaultCharset() throws java.rmi.RemoteException{
    if (advanceERxService == null)
      _initAdvanceERxServiceProxy();
    return advanceERxService.getDefaultCharset();
  }
  
  
}