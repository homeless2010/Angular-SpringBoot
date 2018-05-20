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
 import java.util.HashSet;
 import java.util.Set;
 import org.slf4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.context.annotation.Lazy;
 import org.springframework.stereotype.Component;
 
 
 
 @Component("avicit.crossDeptLeader")
 @Lazy
 public class CrossDeptLeaderDataAccess
   extends AbstractDataCondition
 {
   private static final String CROSSDEPT_EXPRESS = "EXISTS(SELECT 1 FROM sys_user_dept_position avicit WHERE avicit.user_id={USERID} AND avicit.is_chief_dept='1' {OTHER})";
   private static final String CROSSDEPT_DEPTID = "avicit.dept_id='{ID}'";
   
   @Autowired
   public void setNextDataAccess(@Qualifier("avicit.deptLeader") AbstractDataCondition dataAccess)
   {
     this.nextDataAccess = dataAccess;
   }
   
 
   public String generateSqlCondition(SysPermissionResource sysPermRes, DataPermission dataPerm)
   {
     SysPermissionAccess permAcc = sysPermRes.getPermAcesByType(PERMISSION.CROSSDEPTLEADER);
     
     SecurityUser user = ContextHolder.getContext().getLoginUser();
     SysUser sysuser = user.getUser();
     String userId = sysuser.getId();
     
     String sqlUser = "";
     
     if (!permAcc.getPresql().isEmpty()) {
       if ("1".equals(permAcc.getIssql())) {
         sqlUser = " AND EXISTS(" + permAcc.getPresql() + ")";
         this.logger.debug("跨部门领导级别：用户自定义Sql语句：" + permAcc.getPresql());
       } else {
         sqlUser = " AND " + permAcc.getPresql() + sqlUser;
         this.logger.debug("跨部门领导级别：用户自定义Sql语句：" + permAcc.getPresql());
       }
     }
     
 
 
     String[] tempArray = permAcc.getAccessUser().split(",");
     for (int i = 0; i < tempArray.length; i++) {
       if (userId.equals(tempArray[i])) {
         return getConditonByUserId(userId, dataPerm);
       }
     }
     this.logger.debug("跨部门领导权限没有捕获，进入下级过滤");
     return this.nextDataAccess.generateSqlCondition(sysPermRes, dataPerm);
   }
   
 
 
 
 
   private String getConditonByUserId(String userId, DataPermission dataPerm)
   {
     StringBuilder sb = new StringBuilder("");
     
     Set<String> allCrossDepts = new HashSet();
     String[] crossDepts = this.compositeLoader.getCrossDeptByUserId(userId).split(",");
     for (String crossdept : crossDepts) {
       for (String dept : this.compositeLoader.getSubDeptByDeptId(crossdept).split(",")) {
         allCrossDepts.add(dept);
       }
     }
     
 
     sb.append("AND (");
     for (String dept : crossDepts) {
       sb.append("avicit.dept_id='{ID}'".replace("{ID}", dept));
       sb.append("OR ");
     }
     sb.delete(sb.lastIndexOf("OR"), sb.length() - 1);
     sb.append(")");
     String result = "EXISTS(SELECT 1 FROM sys_user_dept_position avicit WHERE avicit.user_id={USERID} AND avicit.is_chief_dept='1' {OTHER})".replace("{USERID}", dataPerm.TableName() + ".CREATED_BY");
     result = result.replace("{OTHER}", sb.toString());
     this.logger.debug("跨部门领导权限SQL=" + result);
     return result;
   }
 }


