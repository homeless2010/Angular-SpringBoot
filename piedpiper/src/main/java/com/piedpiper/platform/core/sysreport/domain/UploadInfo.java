 package com.piedpiper.platform.core.sysreport.domain;
 
 import java.io.InputStream;
 import java.io.Serializable;
 import java.util.Date;
 
 
 
 
 
 
 public class UploadInfo
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private String id = null;
   private String fileName = null;
   private String location = null;
   private int size = 0;
   private String contentType = null;
   private Date uploadTime = null;
   private String uploadUser = null;
   private InputStream content = null;
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) { this.id = id; }
   
   public String getFileName() {
     return this.fileName;
   }
   
   public void setFileName(String fileName) { this.fileName = fileName; }
   
   public String getLocation() {
     return this.location;
   }
   
   public void setLocation(String location) { this.location = location; }
   
   public int getSize() {
     return this.size;
   }
   
   public void setSize(int size) { this.size = size; }
   
   public String getContentType() {
     return this.contentType;
   }
   
   public void setContentType(String contentType) { this.contentType = contentType; }
   
   public Date getUploadTime() {
     return this.uploadTime;
   }
   
   public void setUploadTime(Date uploadTime) { this.uploadTime = uploadTime; }
   
   public String getUploadUser() {
     return this.uploadUser;
   }
   
   public void setUploadUser(String uploadUser) { this.uploadUser = uploadUser; }
   
   public InputStream getContent() {
     return this.content;
   }
   
   public void setContent(InputStream content) { this.content = content; }
 }


