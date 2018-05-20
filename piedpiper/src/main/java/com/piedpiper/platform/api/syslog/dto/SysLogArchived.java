 package com.piedpiper.platform.api.syslog.dto;
 
 import com.piedpiper.platform.core.domain.BeanDTO;
 import java.util.Date;
 
 
 
 
 
 
 
 
 
 public class SysLogArchived
   extends BeanDTO
 {
   private static final long serialVersionUID = -5175042814178268708L;
   private String id;
   private String archiveName;
   private String archiveTableName;
   private Date archiveDate;
   private String sysAppId;
   
   public String getSysAppId()
   {
     return this.sysAppId;
   }
   
   public void setSysAppId(String sysAppId) {
     this.sysAppId = sysAppId;
   }
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getArchiveName() {
     return this.archiveName;
   }
   
   public void setArchiveName(String archiveName) {
     this.archiveName = archiveName;
   }
   
   public String getArchiveTableName() {
     return this.archiveTableName;
   }
   
   public void setArchiveTableName(String archiveTableName) {
     this.archiveTableName = archiveTableName;
   }
   
   public Date getArchiveDate() {
     return this.archiveDate;
   }
   
   public void setArchiveDate(Date archiveDate) {
     this.archiveDate = archiveDate;
   }
   
   public String getLogFormName()
   {
     if ((this.logFormName == null) || (this.logFormName.equals(""))) {
       return "日志归档管理";
     }
     return this.logFormName;
   }
   
 
   public String getLogTitle()
   {
     if ((this.logTitle == null) || (this.logTitle.equals(""))) {
       return "系统日志归档";
     }
     return this.logTitle;
   }
   
   public String returnLogAppId()
   {
     return this.sysAppId;
   }
 }


