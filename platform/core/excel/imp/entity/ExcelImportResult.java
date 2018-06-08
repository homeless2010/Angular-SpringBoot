 package com.piedpiper.platform.core.excel.imp.entity;
 
 
 public class ExcelImportResult
 {
   private int successRecord;
   
   private int failRecord;
   
   private int totalRecord;
   
   private String errorFile;
   
   private boolean result;
   
   private String message;
   
   private String nameImport;
   private Exception exception;
   
   public Exception getException()
   {
     return this.exception;
   }
   
 
   public void setException(Exception exception)
   {
     this.exception = exception;
   }
   
 
 
 
 
 
 
   public int getSuccessRecord()
   {
     return this.successRecord;
   }
   
 
   public void setSuccessRecord(int successRecord)
   {
     this.successRecord = successRecord;
   }
   
 
   public int getFailRecord()
   {
     return this.failRecord;
   }
   
 
   public void setFailRecord(int failRecord)
   {
     this.failRecord = failRecord;
   }
   
 
   public int getTotalRecord()
   {
     return this.totalRecord;
   }
   
 
   public void setTotalRecord(int totalRecord)
   {
     this.totalRecord = totalRecord;
   }
   
 
   public String getErrorFile()
   {
     return this.errorFile;
   }
   
 
   public void setErrorFile(String errorFile)
   {
     this.errorFile = errorFile;
   }
   
 
   public boolean isResult()
   {
     return this.result;
   }
   
 
   public void setResult(boolean result)
   {
     this.result = result;
   }
   
 
   public String getMessage()
   {
     return this.message;
   }
   
 
   public void setMessage(String message)
   {
     this.message = message;
   }
   
 
   public String getNameImport()
   {
     return this.nameImport;
   }
   
 
   public void setNameImport(String nameImport)
   {
     this.nameImport = nameImport;
   }
 }


