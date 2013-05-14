package localhost.axis.EchoHeaders_jws;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class EchoHeadersTest {
	public static void main(String[] args) throws ServiceException, RemoteException {
		
		EchoHeadersServiceLocator local = new EchoHeadersServiceLocator();
		local.getEchoHeaders().whoami();
		
		
		/*Service ser = new Service();
		Call cal = (Call) ser.createCall();
		cal.setTargetEndpointAddress("http://localhost/axis/EchoHeaders.jws");
		cal.setOperation("whoami");
		String ResponseBody=(String)cal.invoke(new Object[]{});
		System.out.println(ResponseBody);*/
	}
}
