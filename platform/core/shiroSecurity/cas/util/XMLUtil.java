 package com.piedpiper.platform.core.shiroSecurity.cas.util;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStreamWriter;
 import java.io.PrintStream;
 import java.io.PrintWriter;
 import java.io.StringReader;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.transform.Transformer;
 import javax.xml.transform.TransformerFactory;
 import javax.xml.transform.dom.DOMSource;
 import javax.xml.transform.stream.StreamResult;
 import org.w3c.dom.Document;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.InputSource;
 import org.xml.sax.SAXException;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class XMLUtil
 {
   public static void removeChildren(Node node)
   {
     NodeList childNodes = node.getChildNodes();
     int length = childNodes.getLength();
     for (int i = length - 1; i > -1; i--) {
       node.removeChild(childNodes.item(i));
     }
   }
   
 
 
 
   public static Document loadDocument(String file)
   {
     Document doc = null;
     try {
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       factory.setNamespaceAware(false);
       factory.setValidating(false);
       DocumentBuilder builder = factory.newDocumentBuilder();
       
       doc = builder.parse(new FileInputStream(file));
     } catch (ParserConfigurationException pce) {
       System.err.println(pce);
       
       System.exit(1);
     } catch (SAXException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return doc;
   }
   
 
 
 
 
 
   public static Document loadDocument(InputSource is)
   {
     Document doc = null;
     try {
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       factory.setNamespaceAware(false);
       factory.setValidating(false);
       DocumentBuilder builder = factory.newDocumentBuilder();
       
       doc = builder.parse(is);
     } catch (ParserConfigurationException pce) {
       System.err.println(pce);
       
       System.exit(1);
     } catch (SAXException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return doc;
   }
   
 
 
 
 
 
   public static Document loadXmlString(String xmlStr)
   {
     Document doc = null;
     try {
       DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       factory.setNamespaceAware(false);
       factory.setValidating(false);
       DocumentBuilder builder = factory.newDocumentBuilder();
       
       doc = builder.parse(new InputSource(new StringReader(xmlStr)));
     } catch (ParserConfigurationException pce) {
       System.err.println(pce);
       
       System.exit(1);
     } catch (SAXException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
     return doc;
   }
   
 
 
 
 
 
 
 
   public static void write2xml(String url, Document doc)
   {
     PrintWriter pw = null;
     
     try
     {
       createFolder(url);
       FileOutputStream fos = new FileOutputStream(url);
       OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
       pw = new PrintWriter(osw);
       
       TransformerFactory tf = TransformerFactory.newInstance();
       
 
       Transformer transformer = tf.newTransformer();
       transformer.setOutputProperty("encoding", "UTF-8");
       transformer.setOutputProperty("indent", "yes");
       transformer.setOutputProperty("standalone", "yes");
       transformer.setOutputProperty("method", "xml");
       
       transformer.transform(new DOMSource(doc), new StreamResult(pw));
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       pw.close();
     }
   }
   
 
 
   private static void createFolder(String url)
   {
     if ((url != null) && (url.indexOf("/") != -1) && (url.indexOf("/") != 0)) {
       new File(url.substring(0, url.indexOf("/"))).mkdirs();
     }
   }
 }


