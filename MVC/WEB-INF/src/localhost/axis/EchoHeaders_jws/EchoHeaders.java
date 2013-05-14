/**
 * EchoHeaders.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.axis.EchoHeaders_jws;

public interface EchoHeaders extends java.rmi.Remote {
    public void throwRuntimeException(java.lang.String param) throws java.rmi.RemoteException;
    public java.lang.String echo(java.lang.String param) throws java.rmi.RemoteException;
    public java.lang.String whoami() throws java.rmi.RemoteException;
    public void throwAxisFault(java.lang.String param) throws java.rmi.RemoteException;
    public void throwException(java.lang.String param) throws java.rmi.RemoteException;
    public java.lang.String[] list() throws java.rmi.RemoteException;
}
