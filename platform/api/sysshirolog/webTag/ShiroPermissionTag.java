 package com.piedpiper.platform.api.sysshirolog.webTag;
 
 import com.piedpiper.platform.api.session.SessionHelper;
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.sysresource.dto.SysResource;
 import com.piedpiper.platform.api.sysshirolog.utils.SecurityUtil;
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import com.piedpiper.platform.core.redisCacheManager.BaseCacheManager;
 import com.piedpiper.platform.core.spring.SpringFactory;
 import com.fasterxml.jackson.core.type.TypeReference;
 import java.util.ArrayList;
 import java.util.List;
 import javax.servlet.jsp.JspException;
 import org.apache.shiro.subject.PrincipalCollection;
 import org.apache.shiro.subject.Subject;
 import org.apache.shiro.web.tags.SecureTag;
 import org.springframework.util.Assert;
 
 
 
 
 
 
 
 
 
 
 public class ShiroPermissionTag
   extends SecureTag
 {
   private static final long serialVersionUID = 5860305694311042988L;
   public static final String DEFAULT_COMPONENT_RESOURCES_COLLECTION = "avicit.component.default";
   private BaseCacheManager baseCacheManager = (BaseCacheManager)SpringFactory.getBean("baseCacheManager");
   
 
   private String domainObject;
   
 
   private String hasPermission;
   
 
   private String permissionDes;
   
 
   protected void verifyAttributes()
     throws JspException
   {
     String permission = getDomainObject();
     if ((permission == null) || (permission.length() == 0))
     {
       String msg = "The 'domainObject' tag attribute must be set.";
       throw new JspException(msg);
     }
   }
   
 
 
 
   public int onDoStartTag()
     throws JspException
   {
     String p = getDomainObject();
     boolean show = showTagBody(p);
     return !show ? 0 : 1;
   }
   
   private boolean showTagBody(String p) {
     return isPermitted(p);
   }
   
   protected boolean isPermitted(String p)
   {
     if (isPermittedAdmin())
       return true;
     if (!SecurityUtil.checkUrlInPermiss(p))
       return true;
     if (PlatformProperties.defaultPageAuthIsGranted())
     {
       List<String> defaultCommont = Assembly(getSysResourceByCoditionDefaultPermiss());
       if (defaultCommont.contains(p)) {
         return true;
       }
     }
     return (getSubject() != null) && (getSubject().isPermitted(p));
   }
   
 
   private List<SysResource> getSysResourceByCoditionDefaultPermiss()
   {
     List<SysResource> resourcelist = this.baseCacheManager.getAllFromCache("avicit.component.default_" + SessionHelper.getApplicationId(), new TypeReference() {});
     return resourcelist;
   }
   
   public String getHasPermission() {
     return this.hasPermission;
   }
   
   public void setHasPermission(String hasPermission) {
     this.hasPermission = hasPermission;
   }
   
   public ShiroPermissionTag()
   {
     this.domainObject = null;
     this.hasPermission = null;
   }
   
   public String getDomainObject()
   {
     return this.domainObject;
   }
   
   public void setDomainObject(String domainObject) {
     this.domainObject = domainObject;
   }
   
   private boolean isPermittedAdmin()
   {
     SecurityUser shiroUser = (SecurityUser)getSubject().getPrincipals().getPrimaryPrincipal();
     Assert.notNull(shiroUser, "当前用户不能为null");
     return SecurityUtil.isPlatformAdministratorByUserId(shiroUser.getUserId());
   }
   
   private List<String> Assembly(List<SysResource> sysResourceByCoditionDefaultPermiss)
   {
     List<String> resultlist = new ArrayList();
     for (SysResource resource : sysResourceByCoditionDefaultPermiss) {
       if (resource.getValue() != null)
         resultlist.add(resource.getValue());
     }
     return resultlist;
   }
   
   public String getPermissionDes() {
     return this.permissionDes;
   }
   
   public void setPermissionDes(String permissionDes) {
     this.permissionDes = permissionDes;
   }
 }


