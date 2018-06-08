 package com.piedpiper.platform.core.exception.handler;
 
 import com.fasterxml.jackson.core.JsonGenerationException;
 import com.fasterxml.jackson.databind.JsonMappingException;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.web.servlet.ModelAndView;
 import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
 
 
 
 
 
 
 
 public class CustomSimpleMappingExceptionResolver
   extends SimpleMappingExceptionResolver
 {
   private static Logger log = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);
   
   private ObjectMapper objectMapper = new ObjectMapper();
   
   private Map<String, String> exceptionMappingCodePrompt = new HashMap();
   
   public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
   {
     if (!isAjaxSumbit(request)) {
       ModelAndView mav = new ModelAndView();
       mav.setViewName("/login/500");
       return mav;
     }
     
     return doResolveException(request, response, handler, ex);
   }
   
   protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
   {
     String viewName = determineViewName(ex, request);
     if (viewName != null) {
       if (!isAjaxSumbit(request))
       {
 
 
         Integer statusCode = determineStatusCode(request, viewName);
         if (statusCode != null) {
           applyStatusCodeIfPossible(request, response, statusCode.intValue());
         }
         return getModelAndView(viewName, ex, request);
       }
       response.setCharacterEncoding("UTF-8");
       response.setContentType("text/xml;charset=UTF-8");
       response.setStatus(500);
       Map<String, String> hashMap = new HashMap();
       hashMap.put("code", "错误");
       hashMap.put("message", getCodeMessagePrompt(ex) + " : " + ex.getMessage());
       PrintWriter writer = null;
       try {
         writer = response.getWriter();
         writer.write(this.objectMapper.writeValueAsString(hashMap));
         writer.flush();
       }
       catch (JsonGenerationException e) {
         e.printStackTrace();
       }
       catch (JsonMappingException e) {
         e.printStackTrace();
       }
       catch (IOException e) {
         e.printStackTrace();
       } finally {
         writer.close();
       }
       return null;
     }
     
 
     return null;
   }
   
 
 
 
 
 
 
 
   private boolean isAjaxSumbit(HttpServletRequest request)
   {
     return ((request.getHeader("accept") != null) && (request.getHeader("accept").indexOf("application/json") > -1)) || ((request.getHeader("X-Requested-With") != null) && (request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
   }
   
   public void setExceptionMappingCodePrompt(HashMap<String, String> exceptionMappingCodePrompt) {
     this.exceptionMappingCodePrompt = exceptionMappingCodePrompt;
   }
   
   private String getCodeMessagePrompt(Exception ex) {
     if ((ex.getClass() != null) && (ex.getClass().toString().trim().length() > 0)) {
       String promptKey = ex.getClass().toString().substring("class ".length());
       return (String)this.exceptionMappingCodePrompt.get(promptKey);
     }
     return "";
   }
 }


