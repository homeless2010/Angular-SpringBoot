 package com.piedpiper.platform.core.rest.resteasy;
 
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import javax.ws.rs.BadRequestException;
 import javax.ws.rs.ForbiddenException;
 import javax.ws.rs.InternalServerErrorException;
 import javax.ws.rs.NotAcceptableException;
 import javax.ws.rs.NotAllowedException;
 import javax.ws.rs.NotAuthorizedException;
 import javax.ws.rs.NotFoundException;
 import javax.ws.rs.NotSupportedException;
 import javax.ws.rs.ServiceUnavailableException;
 import javax.ws.rs.WebApplicationException;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.core.Response.ResponseBuilder;
 import javax.ws.rs.ext.ExceptionMapper;
 import javax.ws.rs.ext.Provider;
 import org.jboss.resteasy.plugins.providers.FileRangeException;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 
 
 
 
 
 @Component
 @Provider
 public class AvicitWsRsExceptionMapper
   implements ExceptionMapper<WebApplicationException>
 {
   static Logger logger = LoggerFactory.getLogger(AvicitWsRsExceptionMapper.class);
   
   public Response toResponse(WebApplicationException e) {
     logger.error("WebApplicationException异常拦截器", e);
     int retCode = 500;
     ResponseMsg<Void> responseMsg = new ResponseMsg();
     responseMsg.setErrorDesc(e.getMessage());
     if ((e instanceof InternalServerErrorException)) {
       retCode = 500;
     } else if ((e instanceof NotFoundException)) {
       retCode = 404;
     } else if ((e instanceof NotSupportedException)) {
       retCode = 415;
     } else if ((e instanceof BadRequestException)) {
       retCode = 400;
     } else if ((e instanceof ForbiddenException)) {
       retCode = 403;
     } else if ((e instanceof NotAcceptableException)) {
       retCode = 406;
     } else if ((e instanceof NotAllowedException)) {
       retCode = 405;
     } else if ((e instanceof NotAuthorizedException)) {
       retCode = 401;
     } else if ((e instanceof FileRangeException)) {
       retCode = 206;
     } else if ((e instanceof ServiceUnavailableException)) {
       retCode = 503;
     }
     responseMsg.setRetCode(retCode + "");
     return Response.status(retCode).entity(responseMsg).type("application/json").build();
   }
 }


