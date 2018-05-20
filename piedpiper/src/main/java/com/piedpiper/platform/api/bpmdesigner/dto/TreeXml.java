 package com.piedpiper.platform.api.bpmdesigner.dto;
 
 import java.util.ArrayList;
 import java.util.List;
 
 public class TreeXml
 {
   private String id;
   private String text;
   private String child;
   private String im0;
   private String im1;
   private String im2;
   private List<UserDataXml> userdata = new ArrayList();
   private List<TreeXml> children = new ArrayList();
   
   public TreeXml(String id, String text, String im0, String im1, String im2) {
     this.id = id;
     this.text = text;
     this.im0 = im0;
     this.im1 = im1;
     this.im2 = im2;
   }
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) { this.id = id; }
   
   public String getText() {
     return this.text;
   }
   
   public void setText(String text) { this.text = text; }
   
   public String getChild() {
     return this.child;
   }
   
   public void setChild(String child) { this.child = child; }
   
   public String getIm0() {
     return this.im0;
   }
   
   public void setIm0(String im0) { this.im0 = im0; }
   
   public String getIm1() {
     return this.im1;
   }
   
   public void setIm1(String im1) { this.im1 = im1; }
   
   public String getIm2() {
     return this.im2;
   }
   
   public void setIm2(String im2) { this.im2 = im2; }
   
   public List<UserDataXml> getUserdata() {
     return this.userdata;
   }
   
   public void setUserdata(List<UserDataXml> userdata) { this.userdata = userdata; }
   
   public List<TreeXml> getChildren()
   {
     return this.children;
   }
   
   public void setChildren(List<TreeXml> children) {
     this.children = children;
   }
   
 
   public void addUserData(String name, String text)
   {
     UserDataXml userDataXml = new UserDataXml();
     userDataXml.setName(name);
     userDataXml.setText(text);
     this.userdata.add(userDataXml);
   }
   
   public void addChildren(TreeXml childrenXml) {
     this.children.add(childrenXml);
   }
   
 
 
 
   public String toString()
   {
     StringBuffer buf = new StringBuffer();
     buf.append("<item");
     buf.append(" id=\"").append(this.id).append("\"");
     buf.append(" text=\"").append(this.text).append("\"");
     buf.append(" im0=\"").append(this.im0).append("\"");
     buf.append(" im1=\"").append(this.im1).append("\"");
     buf.append(" im2=\"").append(this.im2).append("\"");
     if (this.child != null) {
       buf.append(" child=\"").append(this.child).append("\"");
     }
     buf.append(">");
     for (UserDataXml xml : this.userdata) {
       buf.append(xml.toString());
     }
     for (TreeXml xml : this.children) {
       buf.append(xml.toString());
     }
     buf.append("</item>");
     return buf.toString();
   }
 }


