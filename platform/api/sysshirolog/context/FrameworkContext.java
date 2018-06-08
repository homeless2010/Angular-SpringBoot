 package com.piedpiper.platform.api.sysshirolog.context;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import java.io.Serializable;
 import java.util.Map;
 import java.util.concurrent.ConcurrentHashMap;
 import org.apache.shiro.SecurityUtils;
 import org.apache.shiro.subject.Subject;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public abstract class FrameworkContext
   implements Serializable
 {
   private static final long serialVersionUID = -2554263226946218229L;
   protected SecurityUser loginUser;
   protected String loginUsername;
   private boolean needSave = false;
   
   public void resetStatus() {
     this.needSave = false;
   }
   
 
 
   private Map<String, Object> contextMap = new ConcurrentHashMap();
   
   public boolean isNeedSave() {
     return this.needSave;
   }
   
 
 
 
 
   public void setContextValue(String key, Object value)
   {
     if (!this.needSave) {
       this.needSave = true;
     }
     this.contextMap.put(key, value);
   }
   
 
 
 
 
 
   public <T> T getContextValue(String key)
   {
     return (T)this.contextMap.get(key);
   }
   
 
 
 
   public void removeContextValue(String key)
   {
     if (!this.needSave) {
       this.needSave = true;
     }
     this.contextMap.remove(key);
   }
   
 
 
 
   public SecurityUser getLoginUser()
   {
     return this.loginUser;
   }
   
   public void setLoginUser(SecurityUser loginUser) {
     if (!this.needSave) {
       this.needSave = true;
     }
     this.loginUser = loginUser;
   }
   
   public String getLoginUsername() {
     return this.loginUsername;
   }
   
   public void setLoginUsername(String loginUsername) {
     if (!this.needSave) {
       this.needSave = true;
     }
     this.loginUsername = loginUsername;
   }
   
   public boolean isLogin() {
     return SecurityUtils.getSubject().isAuthenticated();
   }
   
 
 
 
   public int hashCode()
   {
     int prime = 31;
     int result = 1;
     result = 31 * result + (this.contextMap == null ? 0 : this.contextMap.hashCode());
     
     result = 31 * result + (this.loginUser == null ? 0 : this.loginUser.hashCode());
     
     result = 31 * result + (this.loginUsername == null ? 0 : this.loginUsername.hashCode());
     
     return result;
   }
   
   public boolean equals(Object obj)
   {
     if (this == obj)
       return true;
     if (obj == null)
       return false;
     if (getClass() != obj.getClass())
       return false;
     FrameworkContext other = (FrameworkContext)obj;
     if (this.contextMap == null) {
       if (other.contextMap != null)
         return false;
     } else if (!this.contextMap.equals(other.contextMap))
       return false;
     if (this.loginUser == null) {
       if (other.loginUser != null)
         return false;
     } else if (!this.loginUser.equals(other.loginUser))
       return false;
     if (this.loginUsername == null) {
       if (other.loginUsername != null)
         return false;
     } else if (!this.loginUsername.equals(other.loginUsername))
       return false;
     return true;
   }
 }


