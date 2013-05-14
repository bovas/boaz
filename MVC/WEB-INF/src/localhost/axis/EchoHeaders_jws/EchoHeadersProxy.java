package localhost.axis.EchoHeaders_jws;

public class EchoHeadersProxy implements localhost.axis.EchoHeaders_jws.EchoHeaders {
  private String _endpoint = null;
  private localhost.axis.EchoHeaders_jws.EchoHeaders echoHeaders = null;
  
  public EchoHeadersProxy() {
    _initEchoHeadersProxy();
  }
  
  public EchoHeadersProxy(String endpoint) {
    _endpoint = endpoint;
    _initEchoHeadersProxy();
  }
  
  private void _initEchoHeadersProxy() {
    try {
      echoHeaders = (new localhost.axis.EchoHeaders_jws.EchoHeadersServiceLocator()).getEchoHeaders();
      if (echoHeaders != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)echoHeaders)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)echoHeaders)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (echoHeaders != null)
      ((javax.xml.rpc.Stub)echoHeaders)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public localhost.axis.EchoHeaders_jws.EchoHeaders getEchoHeaders() {
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    return echoHeaders;
  }
  
  public void throwRuntimeException(java.lang.String param) throws java.rmi.RemoteException{
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    echoHeaders.throwRuntimeException(param);
  }
  
  public java.lang.String echo(java.lang.String param) throws java.rmi.RemoteException{
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    return echoHeaders.echo(param);
  }
  
  public java.lang.String whoami() throws java.rmi.RemoteException{
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    return echoHeaders.whoami();
  }
  
  public void throwAxisFault(java.lang.String param) throws java.rmi.RemoteException{
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    echoHeaders.throwAxisFault(param);
  }
  
  public void throwException(java.lang.String param) throws java.rmi.RemoteException{
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    echoHeaders.throwException(param);
  }
  
  public java.lang.String[] list() throws java.rmi.RemoteException{
    if (echoHeaders == null)
      _initEchoHeadersProxy();
    return echoHeaders.list();
  }
  
  
}