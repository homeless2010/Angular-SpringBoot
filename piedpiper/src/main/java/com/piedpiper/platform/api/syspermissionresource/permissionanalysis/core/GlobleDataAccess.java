 package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.syspermissionresource.dto.PERMISSION;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionAccess;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation.DataPermission;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.AbstractDataCondition;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.CompositeLoaderI;
 import com.piedpiper.platform.api.sysshirolog.context.ContextHolder;
 import com.piedpiper.platform.api.sysshirolog.context.FrameworkContext;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import java.util.List;
 import org.slf4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 
 
 
 @Component("avicit.globleDataAccess")
 @Lazy
 public class GlobleDataAccess
   extends AbstractDataCondition
 {
   private static final String CLOBEL_EXPRESSION = "1 = '1'";
   
   @Autowired
   @Qualifier("avicit.crossDeptLeader")
   public void setNextDataAccess(AbstractDataCondition dataAccess)
   {
     this.nextDataAccess = dataAccess;
   }
   
 
   public String generateSqlCondition(SysPermissionResource sysPermRes, DataPermission dataPerm)
   {
     if (sysPermRes == null) {
       this.logger.debug("数据库中没有资源，采取不控制策略");
       return "1 = '1'";
     }
     
     SysPermissionAccess permAcc = sysPermRes.getPermAcesByType(PERMISSION.GLOBLE);
     
     SecurityUser user = ContextHolder.getContext().getLoginUser();
     SysUser sysuser = user.getUser();
     String userId = sysuser.getId();
     
     String sqlUser = "";
     
     if (!permAcc.getPresql().isEmpty()) {
       if ("1".equals(permAcc.getIssql())) {
         sqlUser = " AND EXISTS(" + permAcc.getPresql() + ")";
         this.logger.debug("全局级别：用户自定义Sql语句：" + permAcc.getPresql());
       } else {
         sqlUser = " AND " + permAcc.getPresql();
         this.logger.debug("全局级别：用户自定义Sql条件：" + permAcc.getPresql());
       }
     }
     
 
 
     List<String> tempList = this.compositeLoader.getOwnRolesByUserId(userId);
     
     String[] tempArray = permAcc.getAccessRole().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("角色维度捕获Sql=1 = '1'" + sqlUser);
         return "1 = '1'" + sqlUser;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnDeptsByUserId(userId);
     
     tempArray = permAcc.getAccessDept().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("部门维度捕获Sql=1 = '1'" + sqlUser);
         return "1 = '1'" + sqlUser;
       }
     }
     
 
     tempArray = permAcc.getAccessUser().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (userId.equals(tempArray[i])) {
         this.logger.debug("用户维度捕获Sql=1 = '1'" + sqlUser);
         return "1 = '1'" + sqlUser;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnGroupsByUserId(userId);
     
     tempArray = permAcc.getAccessGroup().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("群组维度捕获Sql=1 = '1'" + sqlUser);
         return "1 = '1'" + sqlUser;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnPositionsByUserId(userId);
     
     tempArray = permAcc.getAccessPosition().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("岗位维度捕获Sql=1 = '1'" + sqlUser);
         return "1 = '1'" + sqlUser;
       }
     }
     
     this.logger.debug("全局权限没有捕获，进入下级过滤");
     return this.nextDataAccess.generateSqlCondition(sysPermRes, dataPerm);
   }
 }


