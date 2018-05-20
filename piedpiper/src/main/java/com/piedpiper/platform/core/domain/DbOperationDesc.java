 package com.piedpiper.platform.core.domain;
 
 import javax.persistence.Column;
 
 @javax.persistence.Entity
 @javax.persistence.Table(name="BPM_OPERATE_DESC")
 public class DbOperationDesc { private String dbid;
   private String keyid;
   private String operateDesc;
   private String attribute01;
   private String attribute02;
   
   @javax.persistence.Id
   @Column(name="DBID_", length=50, nullable=false)
   public String getDbid() { return this.dbid; }
   
   public void setDbid(String dbid)
   {
     this.dbid = dbid;
   }
   
   @Column(name="KEYID_", length=50, nullable=true)
   public String getKeyid() {
     return this.keyid;
   }
   
   public void setKeyid(String keyid) {
     this.keyid = keyid;
   }
   
   @Column(name="OPE_DESC", length=50, nullable=true)
   public String getOperateDesc() {
     return this.operateDesc;
   }
   
   public void setOperateDesc(String operateDesc) {
     this.operateDesc = operateDesc;
   }
   
   @Column(name="ATTRIBUTE02", length=50, nullable=true)
   public String getAttribute02() {
     return this.attribute02;
   }
   
   public void setAttribute02(String attribute02) {
     this.attribute02 = attribute02;
   }
   
   @Column(name="ATTRIBUTE01", length=50, nullable=true)
   public String getAttribute01() {
     return this.attribute02;
   }
   
   public void setAttribute01(String attribute01) {
     this.attribute02 = attribute01;
   }
 }


