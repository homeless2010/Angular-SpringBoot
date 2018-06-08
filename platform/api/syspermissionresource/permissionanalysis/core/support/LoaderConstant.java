 package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.core.support;
 
 import com.piedpiper.platform.api.syslookup.SysLookupAPI;
 import com.piedpiper.platform.api.sysmenu.SysMenuAPI;
 import com.piedpiper.platform.api.sysmessage.SysMessageAPI;
 import com.piedpiper.platform.api.syspermissionresource.SysPermissionResourceAPI;
 import com.piedpiper.platform.api.sysprofile.SysProfileAPI;
 import com.piedpiper.platform.api.sysuser.SysDeptAPI;
 import com.piedpiper.platform.api.sysuser.SysGroupAPI;
 import com.piedpiper.platform.api.sysuser.SysOrgAPI;
 import com.piedpiper.platform.api.sysuser.SysPositionAPI;
 import com.piedpiper.platform.api.sysuser.SysRoleAPI;
 import com.piedpiper.platform.api.sysuser.SysUserAPI;
 import com.piedpiper.platform.api.sysuser.SysUserDeptPositionAPI;
 import com.piedpiper.platform.api.sysuser.SysUserGroupAPI;
 import com.piedpiper.platform.api.sysuser.SysUserRelationAPI;
 import com.piedpiper.platform.api.sysuser.SysUserRoleAPI;
 import com.piedpiper.platform.core.spring.SpringFactory;
 
 
 
 
 
 public abstract interface LoaderConstant
 {
   public static final SysPermissionResourceAPI sysPermisLoader = (SysPermissionResourceAPI)SpringFactory.getBean(SysPermissionResourceAPI.class);
   public static final SysDeptAPI sysDeptLoader = (SysDeptAPI)SpringFactory.getBean(SysDeptAPI.class);
   public static final SysUserDeptPositionAPI sysUserDeptPositionLoader = (SysUserDeptPositionAPI)SpringFactory.getBean(SysUserDeptPositionAPI.class);
   public static final SysUserGroupAPI sysUserGroupLoader = (SysUserGroupAPI)SpringFactory.getBean(SysUserGroupAPI.class);
   public static final SysUserRoleAPI sysUserRoleLoader = (SysUserRoleAPI)SpringFactory.getBean(SysUserRoleAPI.class);
   public static final SysUserAPI sysUserLoader = (SysUserAPI)SpringFactory.getBean(SysUserAPI.class);
   public static final SysOrgAPI sysOrgLoader = (SysOrgAPI)SpringFactory.getBean(SysOrgAPI.class);
   public static final SysGroupAPI sysGroupLoader = (SysGroupAPI)SpringFactory.getBean(SysGroupAPI.class);
   public static final SysLookupAPI sysLookupLoader = (SysLookupAPI)SpringFactory.getBean(SysLookupAPI.class);
   public static final SysPositionAPI sysPositionLoader = (SysPositionAPI)SpringFactory.getBean(SysPositionAPI.class);
   public static final SysRoleAPI sysRoleLoader = (SysRoleAPI)SpringFactory.getBean(SysRoleAPI.class);
   public static final SysProfileAPI sysProfileAPI = (SysProfileAPI)SpringFactory.getBean(SysProfileAPI.class);
   public static final SysMenuAPI sysMenuAPI = (SysMenuAPI)SpringFactory.getBean(SysMenuAPI.class);
   public static final SysMessageAPI sysMessageAPI = (SysMessageAPI)SpringFactory.getBean(SysMessageAPI.class);
   public static final SysUserRelationAPI sysUserRelationAPI = (SysUserRelationAPI)SpringFactory.getBean(SysUserRelationAPI.class);
 }


