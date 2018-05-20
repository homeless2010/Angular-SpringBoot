 package com.piedpiper.platform.core.rest.resteasy;
 
 import com.piedpiper.platform.core.exception.ForbiddenDelException;
 import com.piedpiper.platform.core.exception.LoginException;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.core.Response.ResponseBuilder;
 import javax.ws.rs.ext.ExceptionMapper;
 import javax.ws.rs.ext.Provider;
 import org.jboss.resteasy.spi.ApplicationException;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.dao.DataAccessException;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 @Component
 @Provider
 public class AvicitRestEasyExceptionMapper
   implements ExceptionMapper<ApplicationException>
 {
   static Logger logger = LoggerFactory.getLogger("netty服务器:");
   
   public Response toResponse(ApplicationException e)
   {
     Throwable t = e.getCause();
     if (!(t instanceof LoginException))
       if ((t instanceof ForbiddenDelException)) {
         logger.warn(e.getMessage());
       } else if ((t instanceof DataAccessException)) {
         logger.error(t.getLocalizedMessage(), t);
         t = new RuntimeException("数据库异常，操作失败，请查看日志");
       } else {
         logger.error("ApplicationException异常拦截器", e);
       }
     ResponseMsg<Void> responseMsg = new ResponseMsg();
     responseMsg.setRetCode("500");
     responseMsg.setErrorDesc(t.getMessage());
     return Response.status(500).entity(responseMsg).type("application/json").build();
   }
 }


