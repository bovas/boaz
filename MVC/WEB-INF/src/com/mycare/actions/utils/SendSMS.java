package com.mycare.actions.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;





public class SendSMS {
	Properties props = null;
	Properties GlaceProp = new Properties();
	public SendSMS() throws Exception{
		 this.props = new Properties();		 		 
		 GlaceProp.load(new FileInputStream("/home/bovas/Workspace2nd/MVC/WEB-INF/src/com/mycare/actions/utils/GlaceMessageQueue.properties"));
		 this.props.setProperty("java.naming.factory.initial", GlaceProp.getProperty("INITIAL_CONTEXT_FACTORY"));
		 this.props.setProperty("java.naming.provider.url", GlaceProp.getProperty("PROVIDER_URL"));
		 this.props.setProperty("queue.MyQueue", GlaceProp.getProperty("QUEUENAME"));		
	}		        	
	public static void main(String[] args)throws Exception{
		SendSMS sms = new SendSMS();
		sms.prepareMessage("9994723478","Hello");
	}
	public void prepareMessage(String phoneNumber,String message) throws IOException, Exception{		 		
		 SMSBean bean = new SMSBean(phoneNumber, message, "glace", -1, 10);
		 sendMessage(bean);
	}	
	public void sendMessage(MessageBean bean)throws Exception{
		Context ctx = new InitialContext(this.props);		 		 
		 QueueConnectionFactory qFactory = (QueueConnectionFactory)ctx.lookup("QueueConnectionFactory");		 		 
		 QueueConnection qConn = qFactory.createQueueConnection();
		 QueueSession session = qConn.createQueueSession(false, 1);
		 Queue que = (Queue)ctx.lookup("MyQueue");
		 QueueSender sender = session.createSender(que);
		 ObjectMessage obj = session.createObjectMessage(null);
		 obj.setJMSCorrelationID("glace");
		 obj.setObject(bean);
		 sender.send(obj);
		 sender.close();
	}
}
