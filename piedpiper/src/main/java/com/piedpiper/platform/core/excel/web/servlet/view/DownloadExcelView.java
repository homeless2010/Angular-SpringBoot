 package com.piedpiper.platform.core.excel.web.servlet.view;
 
 import com.piedpiper.platform.core.excel.export.IExport;
 import java.io.OutputStream;
 import java.io.PrintWriter;
 import java.net.URLEncoder;
 import java.util.Map;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.commons.logging.Log;
 import org.apache.poi.ss.usermodel.Workbook;
 import org.springframework.web.servlet.view.AbstractView;
 
 
 
 
 
 
 public class DownloadExcelView
   extends AbstractView
 {
   protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
     throws Exception
   {
     response.setContentType("application/octet-stream;charset=UTF-8");
     
     response.addHeader("Pragma", "no-cache");
     response.setHeader("Cache-Control", "no-cache");
     response.setCharacterEncoding("UTF-8");
     IExport export = null;
     Object obj = model.get("export");
     if (!(obj instanceof IExport))
     {
 
 
 
 
 
 
 
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       out.write("<script type='text/javascript'>");
       out.write(" parent.$.messager.alert('系统提示', '导出文件出错了！请联系管理员', 'warning') ");
       out.write("</script>");
       out.close();
       throw new RuntimeException(obj.toString() + "没有实现接口：" + IExport.class + "的方法");
     }
     export = (IExport)obj;
     long startTime = System.currentTimeMillis();
     Workbook wb = export.exportData();
     this.logger.info("导出Excel耗费时间:" + (System.currentTimeMillis() - startTime) + " ms");
     response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(new StringBuilder().append(export.getFileName()).append(".xls").toString(), "UTF-8"));
     OutputStream os = response.getOutputStream();
     wb.write(os);
     os.flush();
     os.close();
   }
 }


