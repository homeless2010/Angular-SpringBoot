 package com.piedpiper.platform.core.shiroSecurity.cas.util;
 
 import java.io.IOException;
 import java.net.URL;
 import java.util.Map;
 import javax.servlet.http.Cookie;
 import javax.servlet.http.HttpServletRequest;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public abstract interface Http
 {
   public static final String HTTP = "http";
   public static final String HTTPS = "https";
   public static final String HTTP_WITH_SLASH = "http://";
   public static final String HTTPS_WITH_SLASH = "https://";
   public static final int HTTP_PORT = 80;
   public static final int HTTPS_PORT = 443;
   public static final String PROTOCOL_DELIMITER = "://";
   
   public abstract String addParameter(String paramString1, String paramString2, boolean paramBoolean);
   
   public abstract String addParameter(String paramString1, String paramString2, double paramDouble);
   
   public abstract String addParameter(String paramString1, String paramString2, int paramInt);
   
   public abstract String addParameter(String paramString1, String paramString2, long paramLong);
   
   public abstract String addParameter(String paramString1, String paramString2, short paramShort);
   
   public abstract String addParameter(String paramString1, String paramString2, String paramString3);
   
   public abstract String decodeURL(String paramString);
   
   public abstract String decodeURL(String paramString, boolean paramBoolean);
   
   public abstract String encodeURL(String paramString);
   
   public abstract String encodeURL(String paramString, boolean paramBoolean);
   
   public abstract String getCompleteURL(HttpServletRequest paramHttpServletRequest);
   
   public abstract String getDomain(String paramString);
   
   public abstract String getParameter(String paramString1, String paramString2);
   
   public abstract String getParameter(String paramString1, String paramString2, boolean paramBoolean);
   
   public abstract Map<String, String[]> getParameterMap(String paramString);
   
   public abstract String getProtocol(boolean paramBoolean);
   
   public abstract String getProtocol(String paramString);
   
   public abstract String getProtocol(HttpServletRequest paramHttpServletRequest);
   
   public abstract String getQueryString(String paramString);
   
   public abstract String getRequestURL(HttpServletRequest paramHttpServletRequest);
   
   public abstract boolean hasDomain(String paramString);
   
   public abstract boolean hasProxyConfig();
   
   public abstract boolean isNonProxyHost(String paramString);
   
   public abstract boolean isProxyHost(String paramString);
   
   public abstract Map<String, String[]> parameterMapFromString(String paramString);
   
   public abstract String parameterMapToString(Map<String, String[]> paramMap);
   
   public abstract String parameterMapToString(Map<String, String[]> paramMap, boolean paramBoolean);
   
   public abstract String protocolize(String paramString, boolean paramBoolean);
   
   public abstract String protocolize(String paramString, HttpServletRequest paramHttpServletRequest);
   
   public abstract String removeDomain(String paramString);
   
   public abstract String removeParameter(String paramString1, String paramString2);
   
   public abstract String removeProtocol(String paramString);
   
   public abstract void submit(String paramString)
     throws IOException;
   
   public abstract void submit(String paramString, Cookie[] paramArrayOfCookie)
     throws IOException;
   
   public abstract void submit(String paramString, boolean paramBoolean)
     throws IOException;
   
   public abstract void submit(String paramString, Cookie[] paramArrayOfCookie, boolean paramBoolean)
     throws IOException;
   
   public abstract void submit(String paramString, Cookie[] paramArrayOfCookie, Body paramBody, boolean paramBoolean)
     throws IOException;
   
   public abstract void submit(String paramString, Cookie[] paramArrayOfCookie, Map<String, String> paramMap, boolean paramBoolean)
     throws IOException;
   
   public abstract byte[] URLtoByteArray(String paramString)
     throws IOException;
   
   public abstract byte[] URLtoByteArray(String paramString, Cookie[] paramArrayOfCookie)
     throws IOException;
   
   public abstract byte[] URLtoByteArray(String paramString, boolean paramBoolean)
     throws IOException;
   
   public abstract byte[] URLtoByteArray(String paramString, Cookie[] paramArrayOfCookie, boolean paramBoolean)
     throws IOException;
   
   public abstract byte[] URLtoByteArray(String paramString, Cookie[] paramArrayOfCookie, Body paramBody, boolean paramBoolean)
     throws IOException;
   
   public abstract byte[] URLtoByteArray(String paramString, Cookie[] paramArrayOfCookie, Map<String, String> paramMap, boolean paramBoolean)
     throws IOException;
   
   public abstract String URLtoString(String paramString)
     throws IOException;
   
   public abstract String URLtoString(String paramString, Cookie[] paramArrayOfCookie)
     throws IOException;
   
   public abstract String URLtoString(String paramString, boolean paramBoolean)
     throws IOException;
   
   public abstract String URLtoString(String paramString, Cookie[] paramArrayOfCookie, boolean paramBoolean)
     throws IOException;
   
   public abstract String URLtoString(String paramString, Cookie[] paramArrayOfCookie, Body paramBody, boolean paramBoolean)
     throws IOException;
   
   public abstract String URLtoString(String paramString, Cookie[] paramArrayOfCookie, Map<String, String> paramMap, boolean paramBoolean)
     throws IOException;
   
   public abstract String URLtoString(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
     throws IOException;
   
   public abstract String URLtoString(URL paramURL)
     throws IOException;
   
   public static class Body
   {
     private String _content;
     private String _contentType;
     private String _charset;
     
     public Body(String content, String contentType, String charset)
     {
       this._content = content;
       this._contentType = contentType;
       this._charset = charset;
     }
     
     public String getContent() {
       return this._content;
     }
     
     public String getContentType() {
       return this._contentType;
     }
     
     public String getCharset() {
       return this._charset;
     }
   }
 }


