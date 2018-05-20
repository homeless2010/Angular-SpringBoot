 package com.piedpiper.platform.commons.utils.mail;
 
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import java.util.Properties;
 
 
 
 
 
 
 public class MailSenderInfo
 {
   private String mailServerHost = PlatformProperties.getProperty("mail.host");
   
   private String mailServerPort = PlatformProperties.getProperty("mail.port");
   
   private String userName = PlatformProperties.getProperty("mail.username");
   private String password = PlatformProperties.getProperty("mail.password");
   
   String validateStr = PlatformProperties.getProperty("mail.validate");
   private boolean validate = (this.validateStr != null) && (this.validateStr.equals("true"));
   
   private String fromAddress = PlatformProperties.getProperty("mail.from");
   
   private String toAddress;
   
   private String subject;
   
   private String content;
   
   private String[] attachFileNames;
   
 
   public Properties getProperties()
   {
     Properties p = new Properties();
     p.put("mail.smtp.host", this.mailServerHost);
     p.put("mail.smtp.port", this.mailServerPort);
     p.put("mail.smtp.auth", this.validate ? "true" : "false");
     return p;
   }
   
   public String getMailServerHost() { return this.mailServerHost; }
   
   public void setMailServerHost(String mailServerHost) {
     this.mailServerHost = mailServerHost;
   }
   
   public String getMailServerPort() { return this.mailServerPort; }
   
   public void setMailServerPort(String mailServerPort) {
     this.mailServerPort = mailServerPort;
   }
   
   public boolean isValidate() { return this.validate; }
   
   public void setValidate(boolean validate) {
     this.validate = validate;
   }
   
   public String[] getAttachFileNames() { return this.attachFileNames; }
   
   public void setAttachFileNames(String[] fileNames) {
     this.attachFileNames = fileNames;
   }
   
   public String getFromAddress() { return this.fromAddress; }
   
   public void setFromAddress(String fromAddress) {
     this.fromAddress = fromAddress;
   }
   
   public String getPassword() { return this.password; }
   
   public void setPassword(String password) {
     this.password = password;
   }
   
   public String getToAddress() { return this.toAddress; }
   
   public void setToAddress(String toAddress) {
     this.toAddress = toAddress;
   }
   
   public String getUserName() { return this.userName; }
   
   public void setUserName(String userName) {
     this.userName = userName;
   }
   
   public String getSubject() { return this.subject; }
   
   public void setSubject(String subject) {
     this.subject = subject;
   }
   
   public String getContent() { return this.content; }
   
   public void setContent(String textContent) {
     this.content = textContent;
   }
 }


