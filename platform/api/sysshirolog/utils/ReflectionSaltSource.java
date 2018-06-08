 package com.piedpiper.platform.api.sysshirolog.utils;
 
 import com.piedpiper.platform.api.session.dto.SecurityUser;
 import com.piedpiper.platform.core.exception.AuthenticationServiceException;
 import java.beans.PropertyDescriptor;
 import java.lang.reflect.Method;
 import org.springframework.beans.BeanUtils;
 import org.springframework.beans.factory.InitializingBean;
 import org.springframework.stereotype.Service;
 import org.springframework.util.Assert;
 import org.springframework.util.ReflectionUtils;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Service("avicit.shiro.saltSource")
 public class ReflectionSaltSource
   implements InitializingBean
 {
   public void afterPropertiesSet()
     throws Exception
   {
     Assert.hasText(this.userPropertyToUse, "A userPropertyToUse must be set");
   }
   
   public Object getSalt(SecurityUser user)
   {
     Method saltMethod = findSaltMethod(user);
     try
     {
       return saltMethod.invoke(user, new Object[0]);
     }
     catch (Exception exception)
     {
       throw new AuthenticationServiceException(exception.getMessage(), exception);
     }
   }
   
   private Method findSaltMethod(SecurityUser user)
   {
     Method saltMethod = ReflectionUtils.findMethod(user.getClass(), this.userPropertyToUse, new Class[0]);
     if (saltMethod == null)
     {
       PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(user.getClass(), this.userPropertyToUse);
       if (pd != null)
         saltMethod = pd.getReadMethod();
       if (saltMethod == null)
         throw new AuthenticationServiceException("Unable to find salt method on user Object. Does the class '" + user.getClass().getName() + "' have a method or getter named '" + this.userPropertyToUse + "' ?");
     }
     return saltMethod;
   }
   
   protected String getUserPropertyToUse()
   {
     return this.userPropertyToUse;
   }
   
   public void setUserPropertyToUse(String userPropertyToUse)
   {
     this.userPropertyToUse = userPropertyToUse;
   }
   
   public String toString()
   {
     return "ReflectionSaltSource[ userPropertyToUse='" + this.userPropertyToUse + "'; ]";
   }
   
   private String userPropertyToUse = "loginName";
 }


