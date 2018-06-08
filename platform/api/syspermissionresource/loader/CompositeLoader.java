 package com.piedpiper.platform.api.syspermissionresource.loader;
 
 import com.piedpiper.platform.api.syspermissionresource.SysPermissionResourceAPI;
 import com.piedpiper.platform.api.syspermissionresource.dto.SysPermissionResource;
 import com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support.CompositeLoaderI;
 import com.piedpiper.platform.api.sysuser.SysDeptAPI;
 import com.piedpiper.platform.api.sysuser.SysUserDeptPositionAPI;
 import com.piedpiper.platform.api.sysuser.SysUserGroupAPI;
 import com.piedpiper.platform.api.sysuser.SysUserRoleAPI;
 import com.piedpiper.platform.api.sysuser.dto.SysDept;
 import com.piedpiper.platform.api.sysuser.dto.SysGroup;
 import com.piedpiper.platform.api.sysuser.dto.SysPosition;
 import com.piedpiper.platform.api.sysuser.dto.SysRole;
 import java.util.ArrayList;
 import java.util.List;
 import org.springframework.stereotype.Component;
 
 
 
 @Component("CompositeLoader")
 public class CompositeLoader
   implements CompositeLoaderI
 {
   public SysPermissionResource getSysPermissionResourceByMetaData(String metaData)
   {
     return sysPermisLoader.getSysPermissionResourceByMetaData(metaData);
   }
   
   public List<String> getOwnRolesByUserId(String userId)
   {
     List<String> resultRoles = new ArrayList();
     for (SysRole role : sysUserRoleLoader.getSysRoleListBySysUserId(userId)) {
       resultRoles.add(role.getId());
     }
     return resultRoles;
   }
   
   public List<String> getOwnDeptsByUserId(String userId)
   {
     List<String> resultDepts = new ArrayList();
     for (SysDept dept : sysUserDeptPositionLoader.getSysDeptListBySysUserId(userId)) {
       resultDepts.add(dept.getId());
     }
     return resultDepts;
   }
   
   public List<String> getOwnGroupsByUserId(String userId)
   {
     List<String> resultGroups = new ArrayList();
     List<SysGroup> groups = sysUserGroupLoader.getSysGroupListBySysUserId(userId);
     if (groups == null)
       return resultGroups;
     for (SysGroup group : groups) {
       resultGroups.add(group.getId());
     }
     return resultGroups;
   }
   
   public List<String> getOwnPositionsByUserId(String userId)
   {
     List<String> resultGroups = new ArrayList();
     List<SysPosition> positions = sysUserDeptPositionLoader.getSysPositionListBySysUserId(userId);
     if (positions == null) return resultGroups;
     for (SysPosition sysPosition : positions) {
       resultGroups.add(sysPosition.getId());
     }
     return resultGroups;
   }
   
   public String getChiefDeptByUserId(String userId)
   {
     return sysUserDeptPositionLoader.getChiefDeptIdBySysUserId(userId);
   }
   
   public String getSubDeptByDeptId(String deptId)
   {
     return deptId + "," + sysDeptLoader.getAllSubSysDeptIdBySysDeptId(deptId, ",");
   }
   
   public String getCrossDeptByUserId(String userId)
   {
     return sysPermisLoader.getCrossDeptByUserId(userId);
   }
 }


