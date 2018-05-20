 package com.piedpiper.platform.api.syspermissionresource.scantags.factory;
 
 import com.piedpiper.platform.api.syspermissionresource.scantags.permcominterface.RefreshPermComRepositoryI;
 
 
 
 
 
 
 
 
 
 
 public class RefreshPermComRepositoryFactory
 {
   public RefreshPermComRepositoryI createPermComRepositoryByUrl(String pageUrl)
   {
     RefreshPermComRepositoryI refreshPermCom = null;
     if ((pageUrl.endsWith(".d")) || (pageUrl.endsWith(".d7")))
       throw new RuntimeException("dorado页面");
     if ((pageUrl.indexOf(".d?") > 0) || (pageUrl.indexOf(".d7?") > 0)) {
       throw new RuntimeException("非dorado页面");
     }
     
     if (pageUrl.endsWith(".jsp")) {
       int p = pageUrl.lastIndexOf(".");
       if (p > -1) {
         refreshPermCom = new RefreshPermComRepositoryJsp(pageUrl);
       } else {
         throw new RuntimeException("非jsp页面");
       }
     } else if (pageUrl.indexOf(".jsp?") > 0) {
       int p = pageUrl.lastIndexOf(".");
       if (p > -1) {
         pageUrl = pageUrl.substring(0, pageUrl.lastIndexOf(".") + 3);
         refreshPermCom = new RefreshPermComRepositoryJsp(pageUrl);
       } else {
         throw new RuntimeException("非jsp页面");
       }
     }
     return refreshPermCom;
   }
 }


