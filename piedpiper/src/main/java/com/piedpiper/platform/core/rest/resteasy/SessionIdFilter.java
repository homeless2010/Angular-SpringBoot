 package com.piedpiper.platform.core.rest.resteasy;
 
 import com.piedpiper.platform.core.shiroSecurity.contextThread.ContextCommonHolder;
 import java.io.IOException;
 import javax.ws.rs.container.ContainerRequestContext;
 import javax.ws.rs.container.ContainerRequestFilter;
 import javax.ws.rs.ext.Provider;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 @Component
 @Provider
 public class SessionIdFilter
   implements ContainerRequestFilter
 {
   protected final Logger logger = LoggerFactory.getLogger(SessionIdFilter.class);
   
   public void filter(ContainerRequestContext requestContext) throws IOException
   {
     authenticate(requestContext);
   }
   
   protected void authenticate(ContainerRequestContext ctx) throws IOException
   {
     String sessionId = ctx.getHeaderString("SessionId");
     if ((sessionId != null) && (!"".equals(sessionId)))
     {
       ContextCommonHolder.setCookeMid(sessionId);
     }
   }
 }


