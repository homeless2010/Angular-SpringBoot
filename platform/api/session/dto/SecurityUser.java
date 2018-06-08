 package com.piedpiper.platform.api.session.dto;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.sysmenu.SysMenuAPI;
 import com.piedpiper.platform.api.sysmenu.dto.SysMenuVo;
 import com.piedpiper.platform.api.sysshirolog.context.ContextHolder;
 import com.piedpiper.platform.api.sysuser.dto.SysDept;
 import com.piedpiper.platform.api.sysuser.dto.SysGroup;
 import com.piedpiper.platform.api.sysuser.dto.SysPosition;
 import com.piedpiper.platform.api.sysuser.dto.SysRole;
 import com.piedpiper.platform.api.sysuser.dto.SysUser;
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import com.piedpiper.platform.core.shiroSecurity.shiroCache.ShiroCacheManager;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import com.fasterxml.jackson.annotation.JsonIgnore;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import org.apache.shiro.SecurityUtils;
 import org.apache.shiro.authz.SimpleAuthorizationInfo;
 import org.apache.shiro.cache.Cache;
 import org.apache.shiro.subject.PrincipalCollection;
 import org.apache.shiro.subject.Subject;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class SecurityUser
   implements Serializable
 {
   private static final long serialVersionUID = -7601440433130927169L;
   public static final String DEFAULT_MENU_RESOURCES_COLLECTION = "avicit.menu.default";
   private SysUser user;
   private Collection<SysRole> roles = new ArrayList();
   
   private Collection<SysDept> depts = new ArrayList();
   
   private Collection<SysGroup> groups = new ArrayList();
   
   private Collection<SysPosition> positions = new ArrayList();
   
 
   private String applicationId;
   
 
   private String currentLoginIp;
   
   private Date currentLoginDateTime;
   
 
   public SecurityUser(SysUser sysUser)
   {
     this.user = sysUser;
   }
   
   public SecurityUser() {
     System.out.println();
   }
   
   public SysUser getUser() { return this.user; }
   
   public void setUser(SysUser user)
   {
     this.user = user;
   }
   
   public Collection<SysRole> getRoles() {
     return this.roles;
   }
   
   public void setRoles(Collection<SysRole> roles) {
     this.roles = roles;
   }
   
   public Collection<SysDept> getDepts() {
     return this.depts;
   }
   
   public void setDepts(Collection<SysDept> depts) {
     this.depts = depts;
   }
   
 
   public Collection<SysPosition> getPositions()
   {
     return this.positions;
   }
   
   public void setPositions(Collection<SysPosition> positions) {
     this.positions = positions;
   }
   
   public Collection<SysGroup> getGroups() {
     return this.groups;
   }
   
   public void setGroups(Collection<SysGroup> groups) {
     this.groups = groups;
   }
   
 
 
 
 
   public String getApplicationId()
   {
     return this.applicationId;
   }
   
   public void setApplicationId(String applicationId) {
     this.applicationId = applicationId;
   }
   
 
   public String getCurrentLoginIp()
   {
     return this.currentLoginIp;
   }
   
   public void setCurrentLoginIp(String currentLoginIp) {
     this.currentLoginIp = currentLoginIp;
   }
   
 
 
   public String getLoginName()
   {
     return this.user.getLoginName();
   }
   
 
 
   public String getUserId()
   {
     return this.user.getId();
   }
   
 
 
 
 
 
 
 
 
 
 
   public boolean equals(Object rhs)
   {
     if ((rhs instanceof SecurityUser)) {
       return getLoginName().equals(((SecurityUser)rhs).getLoginName());
     }
     return false;
   }
   
   public Date getCurrentLoginDateTime() {
     return this.currentLoginDateTime;
   }
   
   public void setCurrentLoginDateTime(Date currentLoginDateTime) {
     this.currentLoginDateTime = currentLoginDateTime;
   }
   
 
 
 
   public int hashCode()
   {
     if (getLoginName() != null) {
       return getLoginName().hashCode();
     }
     return 1;
   }
   
 
   public void setLoginName(String loginName) {}
   
   @JsonIgnore
   public Collection<SysMenuVo> getMenus()
   {
     return getMenus(SessionHelper.getApplicationId());
   }
   
   public Collection<SysMenuVo> getMenus(String appId) {
     SysMenuAPI sysmenuloader = (SysMenuAPI)SpringFactory.getBean(SysMenuAPI.class);
     SecurityUser shiroUser = (SecurityUser)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
     Set<String> permiss = new HashSet();
     if (shiroUser != null)
     {
       SimpleAuthorizationInfo info = (SimpleAuthorizationInfo)ShiroCacheManager.instance.getCache(null).get(shiroUser);
       if (info == null)
         refalshShiroCaceh();
       if (info != null) {
         permiss = info.getStringPermissions();
       }
     }
     
     List<SysMenuVo> allMenus = sysmenuloader.getAllSubObjectInPermisson(appId, SessionHelper.getLoginName(ContextHolder.getRequest()), SessionHelper.getLoginSysUserId(ContextHolder.getRequest()), SessionHelper.getCurrentUserLanguageCode(ContextHolder.getRequest()), PlatformProperties.defaultPageAuthIsGranted(), permiss);
     return allMenus;
   }
   
   private void refalshShiroCaceh() { SecurityUtils.getSubject().isPermitted("shuaxinquanxia"); }
 }


