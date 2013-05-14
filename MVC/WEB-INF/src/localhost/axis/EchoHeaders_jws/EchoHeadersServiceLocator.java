/**
 * EchoHeadersServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.axis.EchoHeaders_jws;

public class EchoHeadersServiceLocator extends org.apache.axis.client.Service implements localhost.axis.EchoHeaders_jws.EchoHeadersService {

    public EchoHeadersServiceLocator() {
    }


    public EchoHeadersServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EchoHeadersServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EchoHeaders
    private java.lang.String EchoHeaders_address = "http://localhost/axis/EchoHeaders.jws";

    public java.lang.String getEchoHeadersAddress() {
        return EchoHeaders_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EchoHeadersWSDDServiceName = "EchoHeaders";

    public java.lang.String getEchoHeadersWSDDServiceName() {
        return EchoHeadersWSDDServiceName;
    }

    public void setEchoHeadersWSDDServiceName(java.lang.String name) {
        EchoHeadersWSDDServiceName = name;
    }

    public localhost.axis.EchoHeaders_jws.EchoHeaders getEchoHeaders() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EchoHeaders_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEchoHeaders(endpoint);
    }

    public localhost.axis.EchoHeaders_jws.EchoHeaders getEchoHeaders(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.axis.EchoHeaders_jws.EchoHeadersSoapBindingStub _stub = new localhost.axis.EchoHeaders_jws.EchoHeadersSoapBindingStub(portAddress, this);
            _stub.setPortName(getEchoHeadersWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEchoHeadersEndpointAddress(java.lang.String address) {
        EchoHeaders_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.axis.EchoHeaders_jws.EchoHeaders.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.axis.EchoHeaders_jws.EchoHeadersSoapBindingStub _stub = new localhost.axis.EchoHeaders_jws.EchoHeadersSoapBindingStub(new java.net.URL(EchoHeaders_address), this);
                _stub.setPortName(getEchoHeadersWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EchoHeaders".equals(inputPortName)) {
            return getEchoHeaders();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost/axis/EchoHeaders.jws", "EchoHeadersService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost/axis/EchoHeaders.jws", "EchoHeaders"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EchoHeaders".equals(portName)) {
            setEchoHeadersEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
