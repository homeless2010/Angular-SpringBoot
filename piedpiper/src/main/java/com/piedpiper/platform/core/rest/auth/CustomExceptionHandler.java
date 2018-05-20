 package com.piedpiper.platform.core.rest.auth;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import java.io.IOException;
 import java.io.PrintWriter;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.web.servlet.HandlerExceptionResolver;
 import org.springframework.web.servlet.ModelAndView;
 
 
 
 
 
 
 
 
 public class CustomExceptionHandler
   implements HandlerExceptionResolver
 {
   private static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
   
   public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
   {
     logger.error("Restful Service Exception: ", ex);
     ResponseMsg<Void> out = new ResponseMsg();
     out.setRetCode("500");
     out.setErrorDesc(ex.getMessage());
     try
     {
       response.getWriter().write(JsonHelper.getInstance().writeValueAsString(out));
       
       response.addHeader("Content-Type", "application/json;charset=UTF-8");
       response.getWriter().flush();
       response.getWriter().close();
       response.flushBuffer();
     }
     catch (IOException e) {}
     
     return null;
   }
 }


