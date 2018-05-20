 package com.piedpiper.platform.api.bpmdesigner.dto;
 
 public class UserDataXml
 {
   private String name;
   private String text;
   
   public String getName() {
     return this.name;
   }
   
   public void setName(String name) { this.name = name; }
   
   public String getText() {
     return this.text;
   }
   
   public void setText(String text) { this.text = text; }
   
   public String toString()
   {
     StringBuffer buf = new StringBuffer();
     buf.append("<userdata");
     buf.append(" name=\"").append(this.name).append("\"");
     buf.append(">");
     buf.append(this.text);
     buf.append("</userdata>");
     return buf.toString();
   }
 }


