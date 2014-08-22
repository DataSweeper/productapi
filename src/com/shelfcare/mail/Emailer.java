package com.shelfcare.mail;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.shelfcare.myapp.action.Search;

public class Emailer extends ActionSupport {

   private static final Logger logger = Logger.getLogger(Emailer.class);
   
   private String from;
   private String password;
   private String to;
   private String subject;
   private String body;
   
   static Properties properties = new Properties();

   Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.5.100", 80));
   
   static
   {
      properties.put("mail.smtp.host", "smtp.gmail.com");
      properties.put("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.socketFactory.class",
                     "javax.net.ssl.SSLSocketFactory");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.port", "465");
      
   }
   
   
   public String execute() 
   {
      String ret = SUCCESS;
      try
      {
    	    logger.info("inside try");
            Session session = Session.getDefaultInstance(properties,  
            new javax.mail.Authenticator() {
            protected PasswordAuthentication 
            getPasswordAuthentication() {
            return new 
            PasswordAuthentication(from , password);
            }});
         logger.info("message : " + from + " " + password);
         logger.info("session : " + session);
         
         Message message = new MimeMessage(session);
         
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
         message.setSubject(subject);
         message.setText(body);
         
         logger.info("subject :"+ subject + "body " + body);
         Transport.send(message);
         logger.info("Mail has beeb send");
      }
      catch(Exception e)
      {
         ret = ERROR;
         e.printStackTrace();
      }
      return ret;
   }

   public String getFrom() {
      return from;
   }

   public void setFrom(String from) {
      this.from = from;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getTo() {
      return to;
   }

   public void setTo(String to) {
      this.to = to;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public String getBody() {
      return body;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public static Properties getProperties() {
      return properties;
   }

   public static void setProperties(Properties properties) {
      Emailer.properties = properties;
   }
}