 package com.piedpiper.platform.api.sysaccesscontrol.dto;
 
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import java.io.Serializable;
 
 
 
 public class Resource
   implements Serializable
 {
   private static final long serialVersionUID = 6975913400464177941L;
   private String id;
   private String resource;
   private String orgIdentity;
   public static final String MENU = "MENU";
   public static final String COMPONENT = "COMPONENT";
   public static final String MODULE = "M";
   public static final String WEBSERVICE = "WEBSERVICE";
   public static final String BUSINESS_DATA_CONDITION = "B";
   
   @JsonIgnore
   public String getOrgIdentity()
   {
     return this.orgIdentity;
   }
   
   public void setOrgIdentity(String orgIdentity) {
     this.orgIdentity = orgIdentity;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   public void setId(String id)
   {
     this.id = id;
   }
   
 
 
   public void setResource(String resource)
   {
     this.resource = resource;
   }
   
 
 
   public String getId()
   {
     return this.id;
   }
   
 
 
   public String getResource()
   {
     return this.resource;
   }
 }


