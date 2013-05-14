package com.mycare.actions.utils;

import java.io.File;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMyMail{
	String a;	
	public static void main(String[] args) {		
			String[] reciep={"bovas@glenwoodsystems.com"};
			String[] ccreciep=null;//{"bovas1990@gmail.com"};
			String[] bccreciep={"bovas1990@gmail.com","krrishbovas.fire@gmail.com"};
			//String[] attachments=null;
			String[] attachments=null;//{"/media/Mine/KRRISH/wallpapers/enjoy-life-.jpg"};
			SendMyMail.sendMail("sam@glenwoodmicro.net",reciep,ccreciep,bccreciep,"","Status???",attachments);
	}
	public static void sendMail(String fromAdd,String[] recipients,String[] cc,String[] bcc,String message,String subject,String attachments[]){
		try{
			//creating resource bundle for props
			ResourceBundle bundle = ResourceBundle.getBundle("com.mycare.actions.utils.mailConfig");
			final Properties props = new Properties();

			Enumeration<String> e = bundle.getKeys();
			while(e.hasMoreElements()){
				String keyElt = e.nextElement().toString();
				props.put(keyElt,bundle.getString(keyElt));
			}
			props.put("mail.smtp.auth", "true");	//adding authentication
			//props.put("mail,smtp.port", "465");
			//props.put("mail.smtp.starttls.enable","true");
			props.put("mail.from",fromAdd);
			
			InetAddress localMachine = InetAddress.getLocalHost();  
			String ipAddress = localMachine.getHostAddress();
			
						
			//props.put("mail.smtp.user", "smtpmail12@gmail.com");			
			//props.put("mail.smtp.port", "465");
			//props.put("mail.smtp.starttls.enable","true");
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//props.put("mail.smtp.socketFactory.fallback", "false");
			
			//Creating session object
			Session session = null;
			try{
					session = Session.getDefaultInstance(props,			
					new javax.mail.Authenticator(){
						protected PasswordAuthentication getPasswordAuthentication(){
							return new PasswordAuthentication(props.getProperty("username"),props.getProperty("password"));
						} 	
					}	
				);
			}catch(Exception e1){
				session = Session.getInstance(props,			
						new javax.mail.Authenticator(){
							protected PasswordAuthentication getPasswordAuthentication(){
								return new PasswordAuthentication(props.getProperty("username"),props.getProperty("password"));
							} 	
						}	
					);
			}
			InternetAddress inet = new InternetAddress(fromAdd);			
			inet.setPersonal("Notifications");
			
			//creating message with the session
			MimeMessage sendMessage = new MimeMessage(session);
			sendMessage.setFrom(inet);
			sendMessage.setRecipients(RecipientType.TO,getRecipients(recipients));
			//sendMessage.setRecipients(RecipientType.CC,getRecipients(cc));			
			//sendMessage.setRecipients(RecipientType.BCC,getRecipients(bcc));
			sendMessage.setSubject(subject);											
			
			if(attachments!=null){
				MimeMultipart multiPart = new MimeMultipart();
				MimeBodyPart bodyPart = new MimeBodyPart();
				bodyPart.setText(message);
				multiPart.addBodyPart(bodyPart);
				for(int fileCount=0;fileCount<attachments.length;fileCount++){
					File file= new File(attachments[fileCount]);
					bodyPart = new MimeBodyPart();
					DataSource dataSource= new FileDataSource(file);
					bodyPart.setDataHandler(new DataHandler(dataSource));
					bodyPart.setFileName(file.getName());
					multiPart.addBodyPart(bodyPart);
				}
				sendMessage.setContent(multiPart);
			}else{
				sendMessage.setText(message);
			}								
			
			Transport.send(sendMessage);
			System.out.println("Mail sent successfully...");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static InternetAddress[] getRecipients(String[] recip)throws Exception{
		InternetAddress[] recipients = new InternetAddress[recip.length];
		for(int count=0;count<recip.length;count++){
			recipients[count] = new InternetAddress(recip[count]); 
		}
		return recipients;
	}
}