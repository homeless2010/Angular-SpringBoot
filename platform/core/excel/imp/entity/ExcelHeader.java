 package com.piedpiper.platform.core.excel.imp.entity;
 
 
 
 
 
 
 public class ExcelHeader
 {
   private final String key;
   
 
 
 
   private final String value;
   
 
 
 
 
   public ExcelHeader(String key, String value)
   {
     this.key = key;
     this.value = value;
   }
   
   public String getKey() {
     return this.key;
   }
   
   public String getValue() {
     return this.value;
   }
   
 
   public boolean equals(Object obj)
   {
     if (!(obj instanceof ExcelHeader)) {
       return false;
     }
     ExcelHeader o = (ExcelHeader)obj;
     return (this.key.equals(o.key)) && (this.value.equals(o.value));
   }
   
   public int hashCode() {
     return this.key.hashCode() + 29 * this.value.hashCode();
   }
   
   public String toString() {
     return this.value;
   }
 }


