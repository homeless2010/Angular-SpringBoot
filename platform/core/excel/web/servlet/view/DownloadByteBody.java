 package com.piedpiper.platform.core.excel.web.servlet.view;
 
 import java.io.PrintWriter;
 import java.net.URLEncoder;
 import java.util.Map;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.springframework.web.servlet.view.AbstractView;
 
 
 
 
 public class DownloadByteBody
   extends AbstractView
 {
   protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
     throws Exception
   {
     response.setContentType("application/octet-stream;charset=UTF-8");
     
     response.addHeader("Pragma", "no-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     
     byte[] byteBody = (byte[])model.get("byte");
     String name = (String)model.get("name");
     if (byteBody == null) {
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       out.write("<script type='text/javascript'>");
       out.write(" parent.$.messager.alert('系统提示', '没有找到该文件', 'warning') ");
       out.write("</script>");
       out.close();
       return;
     }
     response.setHeader("Connection", "close");
     response.setHeader("Accept-Ranges", "bytes");
     
     response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(name, "UTF-8"));
     response.getOutputStream().write(byteBody);
     response.getOutputStream().flush();
     response.getOutputStream().close();
   }
 }


