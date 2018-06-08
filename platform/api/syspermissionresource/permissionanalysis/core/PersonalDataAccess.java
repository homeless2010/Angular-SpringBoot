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
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
 
 
 
 @Component("avicit.personal")
 @Lazy
 public class PersonalDataAccess
   extends AbstractDataCondition
 {
   private static final String PERSONAL_EXPRESSION = ".CREATED_BY = ";
   private static final String OTHER_EXPRESSION = "1 = '2'";
   
   public void setNextDataAccess(AbstractDataCondition dataAccess)
   {
     this.nextDataAccess = dataAccess;
   }
   
 
 
   public String generateSqlCondition(SysPermissionResource sysPermRes, DataPermission dataPerm)
   {
     SysPermissionAccess permAcc = sysPermRes.getPermAcesByType(PERMISSION.PERSONAL);
     
     String tableName = dataPerm.TableName();
     
     SecurityUser user = ContextHolder.getContext().getLoginUser();
     SysUser sysuser = user.getUser();
     String userId = sysuser.getId();
     
     String sqlUser = "";
     
     if (!permAcc.getPresql().isEmpty()) {
       if ("1".equals(permAcc.getIssql())) {
         sqlUser = " AND EXISTS(" + permAcc.getPresql() + ")";
         this.logger.debug("个人级别：用户自定义Sql语句：" + permAcc.getPresql());
       } else {
         sqlUser = " AND " + permAcc.getPresql() + sqlUser;
         this.logger.debug("个人级别：用户自定义Sql语句：" + permAcc.getPresql());
       }
     }
     
     String returnExpression = tableName + ".CREATED_BY = " + "'" + userId + "'" + sqlUser;
     
 
     List<String> tempList = this.compositeLoader.getOwnRolesByUserId(userId);
     
     String[] tempArray = permAcc.getAccessRole().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("个人级别，角色维度捕获");
         return returnExpression;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnDeptsByUserId(userId);
     
     tempArray = permAcc.getAccessDept().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("个人级别，部门维度捕获");
         return returnExpression;
       }
     }
     
 
     tempArray = permAcc.getAccessUser().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (userId.equals(tempArray[i])) {
         this.logger.debug("个人级别，用户维度捕获");
         return returnExpression;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnGroupsByUserId(userId);
     
     tempArray = permAcc.getAccessGroup().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("个人级别，群组维度捕获");
         return returnExpression;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnPositionsByUserId(userId);
     
     tempArray = permAcc.getAccessPosition().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("个人级别，岗位维度捕获");
         return returnExpression;
       }
     }
     
     this.logger.debug("个人权限没有捕获，用户无权看该表单数据");
     return "1 = '2'";
   }
 }


