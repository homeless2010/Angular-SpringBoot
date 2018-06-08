 package com.piedpiper.platform.api.syspermissionresource.scantags.factory;
 
 import com.piedpiper.platform.api.syspermissionresource.scantags.entity.DataGridDefinitions;
 import com.piedpiper.platform.api.syspermissionresource.scantags.entity.DataGridDefinitions.DataGridField;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import org.apache.commons.lang.StringUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 
 
 
 
 
 
 
 
 class JspDataGridDefinitionReader
 {
   private Logger logger = LoggerFactory.getLogger(JspDataGridDefinitionReader.class);
   
   private final Map<String, Object> map;
   
   private String relativeUrl;
   private String rootUrl;
   private static final String REGEXP_NOTE = "<%--.*?--%>";
   private static final String RGEEXP_TABLE = "(<\\btable\\b)(.*?)(>)(.*?)(</\\btable\\b>)";
   private final Pattern patTable;
   private static final String REGEXP_TH = "(<\\bth\\b)(.*?)(>)(.*?)(</\\bth\\b>)";
   private final Pattern patTh;
   private static final String REGEXP_PROP_ID = "(\\s*\\bid\\b\\s*=\\s*\"|')(\\w*)(\"|')";
   private final Pattern patId;
   private static final String REGEXP_PROP_DATAPERMISSION = "(\\s*\\bdatapermission\\b\\s*=\\s*[\"|'])(\\w*)([\"|'])";
   private final Pattern patDataPermission;
   private static final String REGEXP_PROP_OPTS = "(\\s*\\bdata-options\\b\\s*=\\s*\")(.*?)(\")";
   private final Pattern patOpts;
   private static final String REGEXP_PROP_EDITOR = "(\\s*\\beditor\\b\\s*=\\s*\")(.*?)(\")";
   private final Pattern pateditorP;
   private static final String REGEXP_PROP_PAGE = "(\\s*\\bpage\\b\\s*=\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patPage;
   private static final String REGEXP_PROP_FILE = "(\\s*\\bfile\\b\\s*=\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patFile;
   private static final String REGEXP_PROP_SRC = "(\\s*\\bsrc\\b\\s*=\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patSrc;
   private static final String REGEXP_FIELD_URL = "(\\s*\\burl\\b\\s*:\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patURL;
   private static final String REGEXP_FIELD_FIELD = "(\\s*\\bfield\\b\\s*:\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patField;
   private static final String REGEXP_FIELD_EDITOR = "(\\s*\\beditor\\b\\s*:\\s*\\{)(.*?)(\\}$)";
   private final Pattern pateditorF;
   private static final String REGEXP_FIELD_TYPE = "(\\s*\\btype\\b\\s*:\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patType;
   private static final String REGEXP_JSP_INCLUDE = "(<\\bjsp:include\\b)(.*?)(/>|>.*?</\\bjsp:include\\b>)";
   private final Pattern patJspInclude;
   private static final String REGEXP_JSP_INCLUDE2 = "(<%@\\s*\\binclude\\b)(.*?)(%>)";
   private final Pattern patJspInclude2;
   private static final String REGEXP_IFRAME = "(<\\biframe\\b)(.*?)(/>|>.*?</\\biframe\\b>)";
   private final Pattern patIframe;
   
   public JspDataGridDefinitionReader(Map<String, Object> map)
   {
     this.map = map;
     this.patId = Pattern.compile("(\\s*\\bid\\b\\s*=\\s*\"|')(\\w*)(\"|')", 2);
     this.patDataPermission = Pattern.compile("(\\s*\\bdatapermission\\b\\s*=\\s*[\"|'])(\\w*)([\"|'])", 2);
     this.patOpts = Pattern.compile("(\\s*\\bdata-options\\b\\s*=\\s*\")(.*?)(\")", 2);
     this.patURL = Pattern.compile("(\\s*\\burl\\b\\s*:\\s*[\"|'])(.*?)([\"|'])", 2);
     this.patTable = Pattern.compile("(<\\btable\\b)(.*?)(>)(.*?)(</\\btable\\b>)", 2);
     this.patTh = Pattern.compile("(<\\bth\\b)(.*?)(>)(.*?)(</\\bth\\b>)", 2);
     this.patField = Pattern.compile("(\\s*\\bfield\\b\\s*:\\s*[\"|'])(.*?)([\"|'])", 2);
     this.pateditorP = Pattern.compile("(\\s*\\beditor\\b\\s*=\\s*\")(.*?)(\")", 2);
     this.pateditorF = Pattern.compile("(\\s*\\beditor\\b\\s*:\\s*\\{)(.*?)(\\}$)", 2);
     this.patType = Pattern.compile("(\\s*\\btype\\b\\s*:\\s*[\"|'])(.*?)([\"|'])", 2);
     this.patJspInclude = Pattern.compile("(<\\bjsp:include\\b)(.*?)(/>|>.*?</\\bjsp:include\\b>)", 2);
     this.patJspInclude2 = Pattern.compile("(<%@\\s*\\binclude\\b)(.*?)(%>)", 2);
     this.patPage = Pattern.compile("(\\s*\\bpage\\b\\s*=\\s*[\"|'])(.*?)([\"|'])", 2);
     this.patFile = Pattern.compile("(\\s*\\bfile\\b\\s*=\\s*[\"|'])(.*?)([\"|'])", 2);
     this.patIframe = Pattern.compile("(<\\biframe\\b)(.*?)(/>|>.*?</\\biframe\\b>)", 2);
     this.patSrc = Pattern.compile("(\\s*\\bsrc\\b\\s*=\\s*[\"|'])(.*?)([\"|'])", 2);
   }
   
   public int loadDataGridDefinitions(String url) {
     this.rootUrl = System.getProperty("avicit_platform.root");
     if (!this.rootUrl.endsWith(File.separator)) {
       this.rootUrl += File.separator;
     }
     String jspFile = this.rootUrl + url.replaceAll("/", "\\\\");
     int i = url.lastIndexOf("/");
     this.relativeUrl = url.substring(0, i + 1);
     readResource(jspFile);
     return this.map.size();
   }
   
   private void doloadDataGridDefinitions(String context)
   {
     readNowPage(context);
     
     readJspInclude(context);
     
     readIframe(context);
   }
   
 
 
   private void readNowPage(String context)
   {
     Matcher macher = this.patTable.matcher(context);
     while (macher.find())
     {
       String tableInfo = macher.group(2);
       this.logger.debug("获得表头信息####" + tableInfo);
       if (!StringUtils.contains(tableInfo, "datapermission"))
       {
         this.logger.debug("表头信息####" + tableInfo + "###没有权限信息!");
       }
       else {
         DataGridDefinitions definition = new DataGridDefinitions();
         String id = readIdProperty(tableInfo);
         definition.setDataGridId(id);
         
         String dataPermission = readDataPermissionProperty(tableInfo);
         definition.setDataPermission(dataPermission);
         
         String opts = readOptsProperty(tableInfo);
         definition.setOpts(opts);
         
         String url = readUrlField(opts);
         definition.setUrl(formatterUrl(url));
         
 
         String tableContext = macher.group(4).trim();
         Matcher macherTh = this.patTh.matcher(tableContext);
         List<DataGridDefinitions.DataGridField> list = new ArrayList();
         while (macherTh.find()) {
           DataGridDefinitions.DataGridField dataGridField = new DataGridDefinitions.DataGridField();
           dataGridField.setDes(macherTh.group(4).trim());
           String thInfo = macherTh.group(2).trim();
           String thOpts = readOptsProperty(thInfo);
           String fieldName = readFieldField(thOpts);
           dataGridField.setFiledName(fieldName);
           
           String editor = readEditorProperty(thInfo);
           if ("".equals(editor)) {
             editor = readEditorField(thOpts);
           }
           
           String type = readTypeField(editor);
           dataGridField.setType(type);
           
           list.add(dataGridField);
         }
         definition.setFieldList(list);
         if (this.map.containsKey(dataPermission)) {}
         
 
         this.map.put(dataPermission, definition);
       }
     }
   }
   
 
 
 
 
   private void readJspInclude(String context)
   {
     Matcher macher = this.patJspInclude.matcher(context);
     String jspContext = "";
     while (macher.find()) {
       jspContext = macher.group(2).trim();
       String page = readPageProperty(jspContext);
       if ("".equals(page)) {
         this.logger.debug("page信息####" + jspContext + "###page信息为空!");
       }
       else {
         String realPath = generateLocalPath(page, false);
         if (realPath.endsWith(".jsp"))
         {
 
           readResource(realPath);
         }
       }
     }
     macher = this.patJspInclude2.matcher(context);
     while (macher.find()) {
       jspContext = macher.group(2).trim();
       String file = readFileProperty(jspContext);
       if ("".equals(file)) {
         this.logger.debug("file信息####" + jspContext + "###file信息为空!");
       }
       else {
         String realPath = generateLocalPath(file, false);
         if (realPath.endsWith(".jsp"))
         {
 
           readResource(realPath);
         }
       }
     }
   }
   
 
   private void readIframe(String context)
   {
     String iframeContext = "";
     Matcher macher = this.patIframe.matcher(context);
     while (macher.find()) {
       iframeContext = macher.group(2).trim();
       String src = readSrcProperty(iframeContext);
       if ("".equals(src)) {
         this.logger.debug("src信息####" + iframeContext + "###src信息为空!");
       }
       else {
         String realPath = generateLocalPath(src, true);
         if (realPath.endsWith(".jsp"))
         {
 
           readResource(realPath);
         }
       }
     }
   }
   
 
 
 
 
   private String generateLocalPath(String path, boolean type)
   {
     String formalPath = formatterUrl(path);
     String result = this.rootUrl;
     if (type) {
       result = result + formalPath.replaceAll("/", "\\\\");
       return result;
     }
     if (formalPath.charAt(0) == '/') {
       if (formalPath.length() != 1) {
         formalPath = formalPath.substring(1, formalPath.length());
         result = result + formalPath.replaceAll("/", "\\\\");
       }
     } else {
       result = result + new StringBuilder().append(this.relativeUrl).append(formalPath).toString().replaceAll("/", "\\\\");
     }
     return result;
   }
   
 
 
 
 
   private String readFileProperty(String context)
   {
     if (context.trim() == "") {
       return "";
     }
     Matcher macher = this.patFile.matcher(context);
     String file = null;
     if (macher.find()) {
       file = macher.group(2).trim();
     } else {
       file = "";
     }
     this.logger.debug("读取" + context + "中file属性为：" + file);
     return file;
   }
   
   private String readSrcProperty(String context) { if ("".equals(context.trim())) {
       return "";
     }
     Matcher macher = this.patSrc.matcher(context);
     String src = null;
     if (macher.find()) {
       src = macher.group(2).trim();
     } else {
       src = "";
     }
     this.logger.debug("读取" + context + "中page属性为：" + src);
     return src;
   }
   
 
 
 
   private String readPageProperty(String context)
   {
     if (context.trim() == "") {
       return "";
     }
     Matcher macher = this.patPage.matcher(context);
     String page = null;
     if (macher.find()) {
       page = macher.group(2).trim();
     } else {
       page = "";
     }
     this.logger.debug("读取" + context + "中page属性为：" + page);
     return page;
   }
   
 
 
 
   private String readIdProperty(String context)
   {
     if (context.trim() == "") {
       return "";
     }
     Matcher macherId = this.patId.matcher(context);
     String id = null;
     if (macherId.find()) {
       id = macherId.group(2).trim();
     } else {
       id = "";
     }
     this.logger.debug("读取" + context + "中id属性为：" + id);
     return id;
   }
   
 
   private String readDataPermissionProperty(String context)
   {
     if (context.trim() == "") {
       return "";
     }
     Matcher macherDg = this.patDataPermission.matcher(context);
     String datagrid = macherDg.find() ? macherDg.group(2).trim() : "";
     this.logger.debug("读取" + context + "中DataPermission属性为：" + datagrid);
     return datagrid;
   }
   
 
 
 
   private String readEditorProperty(String context)
   {
     if (context.trim() == "") {
       return "";
     }
     Matcher matcher = this.pateditorP.matcher(context);
     String editor = matcher.find() ? matcher.group(2).trim() : "";
     this.logger.debug("读取" + context + "中editor属性为：" + editor);
     return editor;
   }
   
 
 
 
 
 
 
   private String readOptsProperty(String context)
   {
     if (context.trim() == "") {
       return "";
     }
     Matcher matcherOpt = this.patOpts.matcher(context);
     String opts = matcherOpt.find() ? matcherOpt.group(2).trim() : "";
     this.logger.debug("读取" + context + "中data-options属性为：" + opts);
     return opts;
   }
   
 
 
 
 
 
 
   private String readFieldField(String opts)
   {
     if (opts.trim() == "") {
       return "";
     }
     Matcher matcher = this.patField.matcher(opts);
     String field = matcher.find() ? matcher.group(2).trim() : "";
     this.logger.debug("读取属性" + opts + "中field字段为：" + field);
     return field;
   }
   
 
 
 
 
   private String readEditorField(String opts)
   {
     if (opts.trim() == "") {
       return "";
     }
     Matcher matcher = this.pateditorF.matcher(opts);
     String editor = matcher.find() ? matcher.group(2).trim() : "";
     this.logger.debug("读取属性" + opts + "中editor字段为：" + editor);
     return editor;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
   private String readTypeField(String opts)
   {
     if (opts.trim() == "") {
       return "";
     }
     Matcher matcher = this.patType.matcher(opts);
     String type = matcher.find() ? matcher.group(2).trim() : "";
     this.logger.debug("读取属性" + opts + "中type字段为：" + type);
     return type;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
   private String readUrlField(String opts)
   {
     if (opts.trim() == "") {
       return "";
     }
     Matcher matcherUrl = this.patURL.matcher(opts);
     
     String url = matcherUrl.find() ? matcherUrl.group(2).trim() : "";
     this.logger.debug("读取属性" + opts + "中url字段为：" + url);
     return formatterUrl(url);
   }
   
 
 
 
 
   private String formatterUrl(String url)
   {
     int i = url.indexOf("?");
     if (i == -1) {
       return url;
     }
     return url.substring(0, i);
   }
   
   private void readResource(String filePath)
   {
     File jspFileObj = new File(filePath);
     boolean fileExist = jspFileObj.exists();
     
     InputStream in = null;
     BufferedReader br = null;
     StringBuilder content = new StringBuilder();
     try {
       if (fileExist) {
         this.logger.debug("读取磁盘路径文件:" + jspFileObj.getAbsolutePath());
         in = new FileInputStream(jspFileObj);
       }
       else if (filePath.startsWith(this.rootUrl)) {
         filePath = filePath.substring(this.rootUrl.length(), filePath.length());
         in = getClass().getResourceAsStream("/META-INF/resources/" + filePath.replace("\\", "/"));
         this.logger.debug("读取jar包文件:/META-INF/resources/" + filePath);
       }
       
 
       br = new BufferedReader(new InputStreamReader(in, "utf-8"));
       String str = "";
       while ((str = br.readLine()) != null) {
         content.append(str);
       }
       in.close();
       this.logger.debug("开始解析页面###" + filePath);
       doloadDataGridDefinitions(content.toString().replaceAll("<%--.*?--%>", ""));
       this.logger.debug("页面###" + filePath + "解析结束"); return;
     }
     catch (FileNotFoundException e) {
       this.logger.debug(e.getMessage(), e);
     } catch (UnsupportedEncodingException e) {
       this.logger.debug(e.getMessage(), e);
     } catch (IOException e) {
       this.logger.debug(e.getMessage(), e);
     } finally {
       try {
         in.close();
       } catch (IOException e) {
         this.logger.debug(e.getMessage(), e);
       }
     }
   }
 }


