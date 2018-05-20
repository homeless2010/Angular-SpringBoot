 package com.piedpiper.platform.commons.utils;
 
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;
 import org.apache.commons.lang.StringUtils;
 
 
 
 
 
 
 
 
 
 public class ViewUtil
 {
   public static String getRequestPath(HttpServletRequest request)
   {
     String path = request.getContextPath();
     
     if (path.equals("/")) {
       path = "";
     }
     String basePath = "";
     if (request.getServerPort() == 80) {
       basePath = request.getScheme() + "://" + request.getServerName() + path + "/";
     } else {
       basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
     }
     String koal_gateway_address = PlatformProperties.getProperty("koal_gateway_address");
     
     Object osCas = request.getSession().getAttribute("CasLogin".toString());
     String isCas = "false";
     if (osCas != null) {
       isCas = osCas.toString();
     }
     if (Boolean.parseBoolean(isCas)) {
       return StringUtils.isEmpty(koal_gateway_address) ? basePath : koal_gateway_address;
     }
     return basePath;
   }
 }


