 package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.CompositeLoaderI;
 import com.piedpiper.platform.api.sysuser.SysUserAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 
 @Component
 class SecretAnalyze implements AccessAnalyze
 {
   private Logger logger = LoggerFactory.getLogger(SecretAnalyze.class);
   private static final String SECRETLEVEL = ".secret_level";
   CompositeLoaderI compositeLoader = (CompositeLoaderI)SpringFactory.getBean("CompositeLoader");
   
 
   public String generateSqlCondition(SysPermissionResource sysPermRes, DataPermission dataPerm)
   {
     if (!"1".equals(sysPermRes.getIsSecret())) {
       this.logger.debug("没有启用密级控制");
       return "";
     }
     
     SecurityUser user = com.piedpiper.platform.api.sysshirolog.context.ContextHolder.getContext().getLoginUser();
     SysUser sysuser = user.getUser();
     String userId = sysuser.getId();
     
 
     String secretLevel = CompositeLoaderI.sysUserLoader.getSysUserById(userId).getSecretLevel();
     this.logger.debug("密级权限解析完成，当前登陆人" + userId + "的权限为" + secretLevel);
     return dataPerm.TableName() + ".secret_level" + "<= '" + secretLevel + "'";
   }
 }


