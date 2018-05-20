 package com.piedpiper.platform.commons.utils.mail;
 
 
 
 public class MailClient
 {
   public static void main(String[] args)
   {
     MailClient mail = new MailClient();
     mail.sendTextMail();
     mail.sendHtmlMail();
   }
   
 
 
 
   public boolean sendTextMail()
   {
     MailSenderInfo mailInfo = new MailSenderInfo();
     mailInfo.setToAddress("wxz258@163.com");
     mailInfo.setSubject("邮箱标题测试");
     String content = "尊敬的顾客：您好，感谢您一如既往对金航数码的支持。为不断提高我们的服务水平，我们诚挚地邀请您参加满意度的问卷调研活动，请您抽出宝贵的几分钟时间完成。 您客观、坦率的意见将激励我们不断努力，为您提供更好的软件体验。 ";
     mailInfo.setContent(content);
     boolean b = SimpleMailSender.sendTextMail(mailInfo);
     return b;
   }
   
 
 
 
   public boolean sendHtmlMail()
   {
     MailSenderInfo mailInfo = new MailSenderInfo();
     mailInfo.setToAddress("wxz258@163.com");
     mailInfo.setSubject("邮箱标题测试");
     String content = "尊敬的顾客：</br>您好，</br>&nbsp;&nbsp;感谢您一如既往对金航数码的支持。为不断提高我们的服务水平，我们诚挚地邀请您参加满意度的问卷调研活动，请您抽出宝贵的几分钟时间完成。 您客观、坦率的意见将激励我们不断努力，为您提供更好的软件体验。 ";
     mailInfo.setContent(content);
     boolean b = SimpleMailSender.sendHtmlMail(mailInfo);
     return b;
   }
 }


