 package com.piedpiper.platform.api.sysshirolog.utils;
 
 import com.piedpiper.platform.api.sysresource.dto.SysResource;
 import com.piedpiper.platform.commons.utils.ComUtil;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.UnsupportedEncodingException;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.xpath.XPath;
 import javax.xml.xpath.XPathConstants;
 import javax.xml.xpath.XPathExpression;
 import javax.xml.xpath.XPathExpressionException;
 import javax.xml.xpath.XPathFactory;
 import org.apache.commons.lang.StringUtils;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.w3c.dom.Document;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class AccesscontrollistProcessPageComponentServiceImpl
 {
   private static Logger logger = LoggerFactory.getLogger(AccesscontrollistProcessPageComponentServiceImpl.class);
   
   private final ThreadLocal<String> relativeUrl = new ThreadLocal();
   private final ThreadLocal<String> rootUrl = new ThreadLocal();
   
   private static final String REGEXP_JSP_INCLUDE = "(<\\bjsp:include\\b)(.*?)(/>|>.*?</\\bjsp:include\\b>)";
   
   private final Pattern patJspInclude = Pattern.compile("(<\\bjsp:include\\b)(.*?)(/>|>.*?</\\bjsp:include\\b>)", 2);
   private static final String REGEXP_JSP_INCLUDE2 = "(<%@\\s*\\binclude\\b)(.*?)(%>)";
   private final Pattern patJspInclude2 = Pattern.compile("(<%@\\s*\\binclude\\b)(.*?)(%>)", 2);
   private static final String REGEXP_IFRAME = "(<\\biframe\\b)(.*?)(/>|>.*?</\\biframe\\b>)";
   private final Pattern patIframe = Pattern.compile("(<\\biframe\\b)(.*?)(/>|>.*?</\\biframe\\b>)", 2);
   private static final String REGEXP_PROP_PAGE = "(\\s*\\bpage\\b\\s*=\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patPage = Pattern.compile("(\\s*\\bpage\\b\\s*=\\s*[\"|'])(.*?)([\"|'])", 2);
   private static final String REGEXP_PROP_FILE = "(\\s*\\bfile\\b\\s*=\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patFile = Pattern.compile("(\\s*\\bfile\\b\\s*=\\s*[\"|'])(.*?)([\"|'])", 2);
   private static final String REGEXP_PROP_SRC = "(\\s*\\bsrc\\b\\s*=\\s*[\"|'])(.*?)([\"|'])";
   private final Pattern patSrc = Pattern.compile("(\\s*\\bsrc\\b\\s*=\\s*[\"|'])(.*?)([\"|'])", 2);
   
 
   public static AccesscontrollistProcessPageComponentServiceImpl instance;
   
 
   public boolean checkUrl(String url)
   {
     if (StringUtils.isEmpty(url)) {
       return false;
     }
     if (url.indexOf(".jsp") > 0) {
       return true;
     }
     return false;
   }
   
   public AccesscontrollistProcessPageComponentServiceImpl()
   {
     instance = this;
   }
   
 
 
 
 
 
 
   public List<SysResource> getPageComponent(String url, String applicationId)
   {
     String realurl = url;
     if (url.indexOf("?") > -1) {
       realurl = url.substring(0, url.indexOf("?"));
     }
     String temp = System.getProperty("avicit_platform.root");
     if (!temp.endsWith(File.separator)) {
       temp = temp + File.separator;
     }
     this.rootUrl.set(temp);
     String jspFile = temp + realurl.replaceAll("/", "\\\\");
     
     int i = jspFile.lastIndexOf("/");
     this.relativeUrl.set(jspFile.substring(0, i + 1));
     
     return doPageComponent(url, applicationId);
   }
   
 
 
 
 
 
 
   public String changeCharset(String str, String newCharset)
     throws UnsupportedEncodingException
   {
     if (str != null)
     {
       byte[] bs = str.getBytes();
       
       return new String(bs, newCharset);
     }
     return null;
   }
   
   private List<SysResource> doPageComponent(String url, String applicationId) {
     String realurl = url;
     if (url.indexOf("?") > -1) {
       realurl = url.substring(0, url.indexOf("?"));
     }
     String jspFile = (String)this.rootUrl.get() + realurl.replaceAll("/", "\\\\");
     File jspFileObj = new File(jspFile);
     boolean fileExist = jspFileObj.exists();
     List<SysResource> components = new ArrayList();
     StringBuilder content = new StringBuilder();
     try {
       InputStream in = null;
       if (fileExist) {
         in = new FileInputStream(jspFile);
       } else {
         url = url.replace("\\", "/");
         in = getClass().getResourceAsStream("/META-INF/resources/" + url);
         
         if (null == in) {
           return components;
         }
       }
       BufferedReader br = null;
       br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
       String str = "";
       while ((str = br.readLine()) != null) {
         content.append(str);
       }
       br.close();
       
 
 
 
       Map<String, String> map = read(content, "permissionDes");
       Iterator<String> itr = map.keySet().iterator();
       while (itr.hasNext()) {
         String key = (String)itr.next();
         SysResource resource = new SysResource();
         resource.setId(ComUtil.getId());
         resource.setVersion(new Long(1L));
         resource.setType("COMPONENT");
         resource.setSysApplicationId(applicationId);
         resource.setKey((String)map.get(key));
         resource.setValue(key);
         components.add(resource);
       }
     } catch (IOException e) {
       logger.error(e.getMessage(), e);
     } catch (Exception e1) {
       logger.error(e1.getMessage(), e1);
     }
     
 
     Matcher macher = this.patJspInclude.matcher(content.toString());
     String jspContext = "";
     while (macher.find()) {
       jspContext = macher.group(2).trim();
       String page = readPageProperty(jspContext);
       if (!"".equals(page))
       {
 
         String realPath = generateLocalPath(page);
         if (realPath.endsWith(".jsp"))
         {
 
           List<SysResource> result = doPageComponent(realPath, applicationId);
           for (SysResource res : result) {
             components.add(res);
           }
         }
       }
     }
     macher = this.patJspInclude2.matcher(content.toString());
     while (macher.find()) {
       jspContext = macher.group(2).trim();
       String file = readFileProperty(jspContext);
       if (!"".equals(file))
       {
 
         String realPath = generateLocalPath(file);
         if (realPath.endsWith(".jsp"))
         {
 
           List<SysResource> result = doPageComponent(realPath, applicationId);
           for (SysResource res : result)
             components.add(res);
         }
       }
     }
     String iframeContext = "";
     Matcher macher1 = this.patIframe.matcher(content.toString());
     while (macher.find()) {
       iframeContext = macher1.group(2).trim();
       String src = readSrcProperty(iframeContext);
       if (!"".equals(src))
       {
 
         String realPath = generateLocalPath(src);
         if (realPath.endsWith(".jsp"))
         {
 
           List<SysResource> result = doPageComponent(realPath, applicationId);
           for (SysResource res : result)
             components.add(res);
         }
       }
     }
     return components;
   }
   
   private String generateLocalPath(String path) {
     String formalPath = formatterUrl(path);
     String result = "";
     if (formalPath.charAt(0) == '/') {
       if (formalPath.length() != 1) {
         formalPath = formalPath.substring(1, formalPath.length());
         result = result + formalPath.replaceAll("/", "\\\\");
       }
     } else {
       result = result + new StringBuilder().append((String)this.relativeUrl.get()).append(formalPath).toString().replaceAll("/", "\\\\");
     }
     return result;
   }
   
   private String formatterUrl(String url) {
     int i = url.indexOf("?");
     if (i == -1) {
       return url;
     }
     return url.substring(0, i);
   }
   
   private String readPageProperty(String context) {
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
     return page;
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
     return src;
   }
   
   private String readFileProperty(String context) { if (context.trim() == "") {
       return "";
     }
     Matcher macher = this.patFile.matcher(context);
     String file = null;
     if (macher.find()) {
       file = macher.group(2).trim();
     } else {
       file = "";
     }
     return file;
   }
   
 
 
 
 
 
   public Map<String, String> read(StringBuilder content, String arrName)
   {
     Map<String, String> ret = new LinkedHashMap();
     
 
 
 
 
 
 
 
 
 
 
 
     String re1 = "(<)";
     String re2 = "(sec)";
     String re3 = "(:)";
     String re4 = "(accesscontrollist)";
     String re5 = "(.*?)";
     String re6 = "(>)";
     String re7 = "(.*?)";
     String re8 = "(</sec:accesscontrollist>)";
     String regx1 = re1 + re2 + re3 + re4 + re5 + re6 + re7 + re8;
     regxMatch(ret, regx1, 5, 7, arrName, content);
     return ret;
   }
   
   private boolean isHas2AccTag(String content, String hasString)
   {
     boolean ret = false;
     if (content.indexOf(hasString) > -1) {
       return true;
     }
     return ret;
   }
   
 
 
 
 
 
 
 
   private void putDegreeAccTag(Map<String, String> ret, String content, String hasString, String vkey)
   {
     String substr = content.substring(content.indexOf("domainObject") + 1);
     String k = getFirstAttr(content, "domainObject");
     String v = "";
     if (isHas2AccTag(substr, hasString)) {
       String betStr = substr.substring(0, substr.indexOf(hasString));
       v = getFirstAttr(betStr, vkey);
       if (StringUtils.isEmpty(v)) {
         v = k;
       }
       ret.put(k, v);
       putDegreeAccTag(ret, substr, hasString, vkey);
     } else {
       v = getFirstAttr(substr, vkey);
       if (StringUtils.isEmpty(v)) {
         v = k;
       }
       ret.put(k, v);
     }
   }
   
 
 
 
 
 
 
 
 
   private void regxMatch(Map<String, String> ret, String reg, int idGroupIdx, int showNameGroupIndex, String arrName, StringBuilder content)
   {
     Pattern p = Pattern.compile(reg, 2);
     String text = content.toString();
     Matcher macher = p.matcher(text);
     while (macher.find()) {
       String arrt = macher.group(idGroupIdx).trim();
       String arrt7 = macher.group(showNameGroupIndex).trim();
       if (isHas2AccTag(arrt7, "<sec:accesscontrollist")) {
         putDegreeAccTag(ret, arrt7, "<sec:accesscontrollist", arrName);
       }
       
 
       String domainObject = "";
       String name = "";
       
 
 
 
 
 
 
 
       Pattern pat = Pattern.compile("(\\s*domainObject\\s*=\\s*[\"|'])(\\w*)([\"|'])", 2);
       Matcher macherrr = pat.matcher(arrt);
       if (macherrr.find()) {
         domainObject = macherrr.group(2);
       } else {
         logger.warn("下面的组件没有找到！");
         logger.warn("下面的组件没有找到！");
         logger.warn("--------------[" + arrt);
         logger.warn("--------------[" + arrt7);
         continue;
       }
       Pattern paname = Pattern.compile("(\\s*permissionDes\\s*=\\s*[\"|'])(\\w*|[一-龥]*)([\"|'])", 2);
       Matcher permissionDes = paname.matcher(arrt);
       if (permissionDes.find()) {
         name = permissionDes.group(2);
       } else {
         logger.warn("下面的组件没有找到！");
         logger.warn("下面的组件没有找到！");
         logger.warn("--------------[" + arrt);
         logger.warn("--------------[" + arrt7);
       }
       
       logger.warn("--------------[" + arrt);
       logger.warn("--------------[" + arrt7);
       
       if (StringUtils.isEmpty(name)) {
         name = domainObject;
       }
       if (StringUtils.isNotEmpty(domainObject)) {
         ret.put(domainObject, name);
       }
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
   private String getFirstAttr(String str, String attrkey)
   {
     String value = "";
     String temp = str;
     value = getFirstAttrMast(temp, attrkey);
     if (StringUtils.isEmpty(value)) {
       value = getFirstAttrMast(temp, attrkey.toLowerCase());
     }
     if (StringUtils.isEmpty(value)) {
       value = getFirstAttrMast(temp, attrkey.toUpperCase());
     }
     
     return value;
   }
   
 
 
 
 
   private String getFirstAttrMast(String str, String attrkey)
   {
     String searchkey = attrkey + "=";
     String value = "";
     String temp = str;
     for (int i = 0; i < 5; i++) {
       temp = temp.replaceAll(" =", "=");
     }
     if (temp.indexOf(searchkey) > -1) {
       String sub1 = temp.substring(temp.indexOf(searchkey));
       String fstr = sub1.replaceAll("'", "\"");
       String[] sub1List = fstr.split("\"");
       if (sub1List.length > 1) {
         value = sub1List[1];
       }
     }
     
     return value;
   }
   
 
 
 
 
   @Deprecated
   public Map<String, String> readByXpath(InputStream in)
   {
     Map<String, String> ret = new LinkedHashMap();
     try {
       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       dbf.setNamespaceAware(true);
       DocumentBuilder builder = dbf.newDocumentBuilder();
       
 
 
       Document doc = builder.parse(in);
       XPathFactory factory = XPathFactory.newInstance();
       XPath xpath = factory.newXPath();
       
 
       XPathExpression expr = xpath.compile("//sec:accesscontrollist/");
       NodeList nodes = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
       for (int i = 0; i < nodes.getLength(); i++) {
         logger.info("name = " + nodes.item(i).getNodeValue());
       }
     } catch (XPathExpressionException e) {
       logger.debug(e.getLocalizedMessage(), e);
     } catch (ParserConfigurationException e) {
       logger.debug(e.getLocalizedMessage(), e);
     } catch (SAXException e) {
       logger.debug(e.getLocalizedMessage(), e);
     } catch (IOException e) {
       logger.debug(e.getLocalizedMessage(), e);
     }
     return ret;
   }
 }


