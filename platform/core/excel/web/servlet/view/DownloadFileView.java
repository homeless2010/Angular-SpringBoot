 package com.piedpiper.platform.core.excel.web.servlet.view;
 
 import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.net.URLEncoder;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.io.IOUtils;
 import org.springframework.web.servlet.view.AbstractView;
 
 
 
 
 
 public class DownloadFileView
   extends AbstractView
 {
   protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
     throws Exception
   {
     response.setContentType("application/octet-stream;charset=UTF-8");
     
     response.addHeader("Pragma", "no-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     
     File file = (File)model.get("filePath");
     if (file.exists()) {
       response.setHeader("Connection", "close");
       response.setHeader("Accept-Ranges", "bytes");
     } else {
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       out.write("<script type='text/javascript'>");
       out.write(" parent.$.messager.alert('系统提示', '所要下载的文件不存在！请联系管理员', 'warning') ");
       out.write("</script>");
       out.close();
       return;
     }
     response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
     BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
     OutputStream os = response.getOutputStream();
     IOUtils.copy(bis, response.getOutputStream());
     os.flush();
     os.close();
   }
 }


