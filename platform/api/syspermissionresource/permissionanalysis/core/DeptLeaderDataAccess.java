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
 
 
 
 
 
 
 @Component("avicit.deptLeader")
 @Lazy
 public class DeptLeaderDataAccess
   extends AbstractDataCondition
 {
   private static final String DEPTLEADER_EXPRESS = "EXISTS(SELECT 1 FROM sys_user_dept_position avicit WHERE avicit.user_id={USERID} AND avicit.is_chief_dept='1' {OTHER})";
   private static final String DEPTLEADER_DEPTID = "avicit.dept_id='{ID}'";
   
   @Autowired
   public void setNextDataAccess(@Qualifier("avicit.personal") AbstractDataCondition dataAccess)
   {
     this.nextDataAccess = dataAccess;
   }
   
 
 
 
   public String generateSqlCondition(SysPermissionResource sysPermRes, DataPermission dataPerm)
   {
     SysPermissionAccess permAcc = sysPermRes.getPermAcesByType(PERMISSION.DEPTLEADER);
     
     SecurityUser user = ContextHolder.getContext().getLoginUser();
     SysUser sysuser = user.getUser();
     String userId = sysuser.getId();
     
     String sqlUser = "";
     
     if (!permAcc.getPresql().isEmpty()) {
       if ("1".equals(permAcc.getIssql())) {
         sqlUser = " AND EXISTS(" + permAcc.getPresql() + ")";
         this.logger.debug("部门领导级别：用户自定义Sql语句：" + permAcc.getPresql());
       } else {
         sqlUser = " AND " + permAcc.getPresql() + sqlUser;
         this.logger.debug("部门领导级别：用户自定义Sql语句：" + permAcc.getPresql());
       }
     }
     
 
 
     List<String> tempList = this.compositeLoader.getOwnRolesByUserId(userId);
     
     String[] tempArray = permAcc.getAccessRole().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("部门领导级别，角色维度捕获");
         return getConditonByUserId(userId, dataPerm) + sqlUser;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnDeptsByUserId(userId);
     
     tempArray = permAcc.getAccessDept().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("部门领导级别，部门维度捕获");
         return getConditonByUserId(userId, dataPerm) + sqlUser;
       }
     }
     
 
     tempArray = permAcc.getAccessUser().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (userId.equals(tempArray[i])) {
         this.logger.debug("部门领导级别，用户维度捕获");
         return getConditonByUserId(userId, dataPerm) + sqlUser;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnGroupsByUserId(userId);
     
     tempArray = permAcc.getAccessGroup().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("部门领导级别，群组维度捕获");
         return getConditonByUserId(userId, dataPerm) + sqlUser;
       }
     }
     
 
     tempList = this.compositeLoader.getOwnPositionsByUserId(userId);
     
     tempArray = permAcc.getAccessPosition().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (tempList.contains(tempArray[i])) {
         this.logger.debug("部门领导级别，岗位维度捕获");
         return getConditonByUserId(userId, dataPerm) + sqlUser;
       }
     }
     
     this.logger.debug("部门领导权限没有捕获，进入下级过滤");
     return this.nextDataAccess.generateSqlCondition(sysPermRes, dataPerm);
   }
   
 
 
 
 
   private String getConditonByUserId(String userId, DataPermission dataPerm)
   {
     StringBuilder sb = new StringBuilder("");
     String deptId = this.compositeLoader.getChiefDeptByUserId(userId);
     String[] allDepts = this.compositeLoader.getSubDeptByDeptId(deptId).split(",");
     sb.append("AND (");
     for (String dept : allDepts) {
       sb.append("avicit.dept_id='{ID}'".replace("{ID}", dept));
       sb.append("OR ");
     }
     sb.delete(sb.lastIndexOf("OR"), sb.length() - 1);
     sb.append(")");
     String result = "EXISTS(SELECT 1 FROM sys_user_dept_position avicit WHERE avicit.user_id={USERID} AND avicit.is_chief_dept='1' {OTHER})".replace("{USERID}", dataPerm.TableName() + ".CREATED_BY");
     result = result.replace("{OTHER}", sb.toString());
     return result;
   }
 }


