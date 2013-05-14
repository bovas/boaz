package com.glaceemr.erxin.axis.DirectoryService_jws;

public class DirectoryServiceProxy implements com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryService {
  private String _endpoint = null;
  private com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryService directoryService = null;
  
  public DirectoryServiceProxy() {
    _initDirectoryServiceProxy();
  }
  
  public DirectoryServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDirectoryServiceProxy();
  }
  
  private void _initDirectoryServiceProxy() {
    try {
      directoryService = (new com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryServiceServiceLocator()).getDirectoryService();
      if (directoryService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)directoryService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)directoryService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (directoryService != null)
      ((javax.xml.rpc.Stub)directoryService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryService getDirectoryService() {
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService;
  }
  
  public java.lang.String getResponse(java.lang.Object input, java.lang.String isDirectoryService) throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.getResponse(input, isDirectoryService);
  }
  
  public java.lang.String getUTCTime() throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.getUTCTime();
  }
  
  public java.lang.String insertData(java.lang.String SPI, java.lang.String URL, java.lang.String accountId) throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.insertData(SPI, URL, accountId);
  }
  
  public java.lang.String convertToDoubleDigit(int value) throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.convertToDoubleDigit(value);
  }
  
  public java.lang.String writeFiles1(java.lang.String str, java.lang.String isDirectoryService, java.lang.String accountId) throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.writeFiles1(str, isDirectoryService, accountId);
  }
  
  public java.lang.String writeFiles(java.lang.String str, java.lang.String isDirectoryService, java.lang.String accountId) throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.writeFiles(str, isDirectoryService, accountId);
  }
  
  public java.lang.String getEncodedCredentials() throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.getEncodedCredentials();
  }
  
  public java.lang.String decode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.decode(value);
  }
  
  public java.lang.Object decode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.DecoderException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.decode(value);
  }
  
  public java.lang.String encode(java.lang.String value, java.lang.String charset) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.encode(value, charset);
  }
  
  public java.lang.String encode(java.lang.String value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.encode(value);
  }
  
  public java.lang.Object encode(java.lang.Object value) throws java.rmi.RemoteException, org.apache.commons.codec.EncoderException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.encode(value);
  }
  
  public java.lang.String getDefaultCharset() throws java.rmi.RemoteException{
    if (directoryService == null)
      _initDirectoryServiceProxy();
    return directoryService.getDefaultCharset();
  }
  
  
}