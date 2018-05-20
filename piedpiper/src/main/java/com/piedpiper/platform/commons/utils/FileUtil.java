 package com.piedpiper.platform.commons.utils;
 
 import java.io.ByteArrayOutputStream;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.URLEncoder;
 import java.nio.charset.Charset;
 import java.util.Properties;
 import javax.mail.internet.MimeUtility;
 
 
 
 
 
 
 
 
 
 
 
 
 public class FileUtil
 {
   public static String getFileExtensionName(String filename)
   {
     if ((filename != null) && (filename.length() > 0)) {
       int dot = filename.lastIndexOf('.');
       if ((dot > -1) && (dot < filename.length() - 1)) {
         return filename.substring(dot + 1);
       }
     }
     return filename;
   }
   
 
 
 
   public static String getFileNameNoEx(String filename)
   {
     if ((filename != null) && (filename.length() > 0)) {
       int dot = filename.lastIndexOf('.');
       if ((dot > -1) && (dot < filename.length())) {
         return filename.substring(0, dot);
       }
     }
     return filename;
   }
   
 
 
 
   public static String getFileIcon(String filename)
   {
     String icon = "";
     String exName = getFileExtensionName(filename);
     if (exName.equals("avi")) {
       icon = "avi.jpg";
     } else { if (exName.equals("zip"))
         return "zip.jpg";
       if (exName.equals("rar"))
         return "rar.jpg";
       if (exName.equals("exe"))
         return "exe.jpg";
       if ((exName.equals("xls")) || (exName.equals("xlsx")))
         return "excel.jpg";
       if ((exName.equals("doc")) || (exName.equals("docx")))
         return "word.jpg";
       if ((exName.equals("ppt")) || (exName.equals("pptx")))
         return "ppt.jpg";
       if (exName.equals("txt"))
         return "txt.jpg";
       if (exName.equals("chm"))
         return "chm.jpg";
       if (exName.equals("pdf"))
         return "pdf.jpg";
       if (exName.equals("jpg"))
         return "jpg.jpg";
       if (exName.equals("png"))
         return "jpg.jpg";
       if (exName.equals("bmp"))
         return "bmp.jpg";
       if (exName.equals("gif"))
         return "pic.jpg";
       if ((exName.equals("html")) || (exName.equals("htm")) || (exName.equals("xhtml"))) {
         return "html.jpg";
       }
       return "normal.jpg";
     }
     return icon;
   }
   
   public static String getFileIconCss(String filename) { String iconCss = "";
     String exName = getFileExtensionName(filename);
     if (exName.equals("zip"))
       return "nui-ico-file nui-ico-file-rar";
     if (exName.equals("rar"))
       return "nui-ico-file nui-ico-file-rar";
     if ((exName.equals("xls")) || (exName.equals("xlsx")))
       return "nui-ico-file nui-ico-file-xls";
     if ((exName.equals("doc")) || (exName.equals("docx")))
       return "nui-ico-file nui-ico-file-doc";
     if ((exName.equals("ppt")) || (exName.equals("pptx")))
       return "nui-ico-file nui-ico-file-ppt";
     if (exName.equals("txt"))
       return "nui-ico-file nui-ico-file-txt";
     if (exName.equals("chm"))
       return "nui-ico-file nui-ico-file-html";
     if ((exName.equals("html")) || (exName.equals("htm")) || (exName.equals("xhtml"))) {
       return "nui-ico-file nui-ico-file-html";
     }
     return "nui-ico-file nui-ico-file-default";
   }
   
 
 
   public static String getSep()
   {
     return System.getProperties().getProperty("file.separator");
   }
   
 
 
   public static void checkPath(String path)
     throws IOException
   {
     File filePath = new File(path);
     if (!filePath.exists()) {
       throw new IOException("无效的文件路径【" + path + "】;");
     }
   }
   
 
 
 
   public static File getDoccenterDir(String doccenterPath, String group, String file)
     throws IOException
   {
     File folder = new File(doccenterPath);
     checkPath(folder.getAbsolutePath());
     if (!folder.exists()) {
       folder.mkdir();
     }
     folder = new File(folder.getAbsoluteFile() + getSep() + "group" + group);
     if (!folder.exists()) {
       folder.mkdir();
     }
     folder = new File(folder.getAbsoluteFile() + getSep() + "file" + file);
     if (!folder.exists()) {
       folder.mkdir();
     }
     return folder.getAbsoluteFile();
   }
   
   public static File getDoccenterDir(String doccenterPath, String tmpId) throws IOException {
     File folder = new File(doccenterPath);
     checkPath(folder.getAbsolutePath());
     if (!folder.exists()) {
       folder.mkdir();
     }
     folder = new File(folder.getAbsoluteFile() + getSep() + "tmp" + tmpId);
     if (!folder.exists()) {
       folder.mkdir();
     }
     return folder.getAbsoluteFile();
   }
   
 
 
 
   public static String getFilePrettySize(Long fileSize)
   {
     long K = 1024L;
     long M = K * K;
     long G = M * K;
     long T = G * K;
     long[] dividers = { T, G, M, K, 1L };
     String[] units = { "TB", "GB", "MB", "KB", "B" };
     if (fileSize.longValue() == 0L)
       return "0B";
     if (fileSize.longValue() < 0L) {
       return "Invalid size";
     }
     
     String result = "";
     long temp = 0L;
     for (int i = 0; i < dividers.length; i++) {
       long divider = dividers[i];
       if (fileSize.longValue() >= divider) {
         temp = fileSize.longValue() / divider;
         if (temp < 1.05D) {
           result = _format(fileSize, units[i]); break;
         }
         result = _format(Long.valueOf(temp), units[i]);
         
         break;
       }
     }
     return result;
   }
   
   private static String _format(Long fileSize, String unit) { return (fileSize + " " + unit).replace(".0", ""); }
   
   public static String getFileName(String fileName, String agent)
   {
     String codedfilename = fileName;
     try {
       if ((agent != null) && (agent.indexOf("Mozilla") != -1) && (agent.indexOf("Android") != -1)) {
         try {
           codedfilename = URLEncoder.encode(fileName, "UTF-8");
         } catch (Exception e) {
           codedfilename = fileName;
         }
       } else if (agent.toLowerCase().indexOf("safari") != -1) {
         codedfilename = new String(codedfilename.getBytes("UTF-8"), "ISO8859-1");
       } else if (agent.toLowerCase().indexOf("chrome") != -1) {
         codedfilename = MimeUtility.encodeText(codedfilename, "UTF8", "B");
 
       }
       else if ((agent != null) && (agent.toLowerCase().indexOf("mozilla") != -1) && (agent.toLowerCase().indexOf("firefox") != -1)) {
         codedfilename = MimeUtility.encodeText(fileName, "GBK", "B");
       }
       else if (Charset.defaultCharset().name().indexOf("GBK") != -1) {
         codedfilename = new String(fileName.getBytes(), "ISO8859_1");
       } else {
         try {
           codedfilename = URLEncoder.encode(fileName, "utf-8");
         } catch (Exception e) {
           throw new RuntimeException(e);
         }
       }
     }
     catch (Exception e) {}
     
 
 
     return codedfilename;
   }
   
   public static byte[] InputStreamToByte(InputStream is)
     throws IOException
   {
     ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
     int ch;
     while ((ch = is.read()) != -1) {
       bytestream.write(ch);
     }
     byte[] imgdata = bytestream.toByteArray();
     bytestream.close();
     
     return imgdata;
   }
 }


