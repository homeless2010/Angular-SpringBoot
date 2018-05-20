 package com.piedpiper.platform.api.syspermissionresource.scantags.factory;
 
 import com.piedpiper.platform.api.syspermissionresource.scantags.entity.PermComRepositoryJ;
 import com.piedpiper.platform.api.syspermissionresource.scantags.permcominterface.PermComRepositoryI;
 import com.piedpiper.platform.api.syspermissionresource.scantags.permcominterface.RefreshPermComRepositoryI;
 
 
 class RefreshPermComRepositoryJsp
   implements RefreshPermComRepositoryI
 {
   private String pageUrl;
   
   public RefreshPermComRepositoryJsp(String pageUrl)
   {
     this.pageUrl = pageUrl;
   }
   
   public PermComRepositoryI refreshPermComRepository() {
     PermComRepositoryJ permComRepository = new PermComRepositoryJ();
     JspDataGridDefinitionReader reader = new JspDataGridDefinitionReader(permComRepository);
     reader.loadDataGridDefinitions(this.pageUrl);
     return permComRepository;
   }
 }


