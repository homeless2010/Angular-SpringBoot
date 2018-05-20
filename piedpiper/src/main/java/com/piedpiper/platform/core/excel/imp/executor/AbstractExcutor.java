 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 import com.piedpiper.platform.core.excel.imp.inf.IHeaderInfo;
 import com.piedpiper.platform.core.excel.imp.inf.ImportOpt;
 import com.piedpiper.platform.core.excel.imp.inf.Validate;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 
 public abstract class AbstractExcutor
   implements IHeaderInfo
 {
   private Map<String, Validate> validators = new HashMap();
   
   protected Set<ExcelHeader> headers = new HashSet();
   
 
   private ImportOpt importOpt;
   
 
   public void addValidate(Validate v)
   {
     this.validators.put(v.getField(), v);
   }
   
   public Map<String, Validate> getValidators() {
     return this.validators;
   }
   
   public ImportOpt getImportOpt() {
     return this.importOpt;
   }
   
   public void setImportOpt(ImportOpt pot) {
     this.importOpt = pot;
   }
   
   public List<ExcelHeader> getHeaders()
   {
     return new ArrayList(this.headers);
   }
   
   public abstract String getName();
 }


