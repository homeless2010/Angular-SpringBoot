 package com.piedpiper.platform.api.syssso.filter;
 
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 
 
 
 
 
 
 
 
 
 public abstract class BaseConfigFilter
   extends BaseFilter
 {
   protected Log getLog()
   {
     return this._log;
   }
   
   protected boolean isFilterEnabled() {
     return this._filterEnabled;
   }
   
   private Log _log = LogFactory.getLog(getClass());
   
   private boolean _filterEnabled = getFilterFlg();
   
 
 
   private boolean getFilterFlg()
   {
     return true;
   }
 }


