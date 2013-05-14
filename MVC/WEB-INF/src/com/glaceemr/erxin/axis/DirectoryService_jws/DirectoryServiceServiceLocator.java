/**
 * DirectoryServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.glaceemr.erxin.axis.DirectoryService_jws;

public class DirectoryServiceServiceLocator extends org.apache.axis.client.Service implements com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryServiceService {

    public DirectoryServiceServiceLocator() {
    }
    public DirectoryServiceServiceLocator(String serviceAddress) {
    	this.DirectoryService_address = serviceAddress;
    }
    public DirectoryServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DirectoryServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }
    

    // Use to get a proxy class for DirectoryService
    private java.lang.String DirectoryService_address = "http://erxin.glaceemr.com/axis/DirectoryService.jws";

    public java.lang.String getDirectoryServiceAddress() {
        return DirectoryService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DirectoryServiceWSDDServiceName = "DirectoryService";

    public java.lang.String getDirectoryServiceWSDDServiceName() {
        return DirectoryServiceWSDDServiceName;
    }

    public void setDirectoryServiceWSDDServiceName(java.lang.String name) {
        DirectoryServiceWSDDServiceName = name;
    }

    public com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryService getDirectoryService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DirectoryService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDirectoryService(endpoint);
    }

    public com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryService getDirectoryService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryServiceSoapBindingStub _stub = new com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDirectoryServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDirectoryServiceEndpointAddress(java.lang.String address) {
        DirectoryService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryServiceSoapBindingStub _stub = new com.glaceemr.erxin.axis.DirectoryService_jws.DirectoryServiceSoapBindingStub(new java.net.URL(DirectoryService_address), this);
                _stub.setPortName(getDirectoryServiceWSDDServiceName());
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
        if ("DirectoryService".equals(inputPortName)) {
            return getDirectoryService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://erxin.glaceemr.com/axis/DirectoryService.jws", "DirectoryServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://erxin.glaceemr.com/axis/DirectoryService.jws", "DirectoryService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DirectoryService".equals(portName)) {
            setDirectoryServiceEndpointAddress(address);
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
