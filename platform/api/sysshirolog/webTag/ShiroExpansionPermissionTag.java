 package com.piedpiper.platform.api.sysshirolog.webTag;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.api.sysshirolog.utils.SecurityUtil;
 import javax.servlet.jsp.JspException;
 import org.apache.shiro.subject.PrincipalCollection;
 import org.apache.shiro.subject.Subject;
 import org.apache.shiro.web.tags.SecureTag;
 import org.springframework.util.Assert;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class ShiroExpansionPermissionTag
   extends SecureTag
 {
   private static final long serialVersionUID = -3982213069721633053L;
   public static final String DEFAULT_COMPONENT_RESOURCES_COLLECTION = "avicit.component.default";
   private String domainObject;
   private String hasPermission;
   
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
     return (getSubject() != null) && (getSubject().isPermitted(p));
   }
   
 
 
 
   public String getHasPermission()
   {
     return this.hasPermission;
   }
   
   public void setHasPermission(String hasPermission) {
     this.hasPermission = hasPermission;
   }
   
   public ShiroExpansionPermissionTag()
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
 }


