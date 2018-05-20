 package com.piedpiper.platform.core.ws;
 
 import java.util.ArrayList;
 import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 import javax.xml.bind.annotation.XmlAccessType;
 import javax.xml.bind.annotation.XmlAccessorType;
 import javax.xml.bind.annotation.XmlType;
 
 
 
 
 
 
 
 @XmlType(name="MapConvertor")
 @XmlAccessorType(XmlAccessType.FIELD)
 public class MapConvertor
 {
   private List<MapEntry> entries;
   
   public MapConvertor()
   {
     this.entries = new ArrayList();
   }
   
   public void addEntry(MapEntry entry) {
     this.entries.add(entry);
   }
   
 
   public static class MapEntry
   {
     private String key;
     private Object value;
     
     public MapEntry() {}
     
     public MapEntry(Map.Entry<String, Object> entry)
     {
       this.key = ((String)entry.getKey());
       this.value = entry.getValue();
     }
     
 
     public MapEntry(String key, Object value)
     {
       this.key = key;
       this.value = value;
     }
     
 
 
 
     public String getKey()
     {
       return this.key;
     }
     
     public void setKey(String key)
     {
       this.key = key;
     }
     
     public Object getValue()
     {
       return this.value;
     }
     
     public void setValue(Object value)
     {
       this.value = value;
     }
   }
   
   public List<MapEntry> getEntries()
   {
     return this.entries;
   }
 }


