 package com.piedpiper.platform.core.excel.export.headersource;
 
 import com.piedpiper.platform.commons.utils.JsonHelper;
 import com.piedpiper.platform.core.excel.export.entity.DataGridHeader;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.lang.ArrayUtils;
 import org.apache.commons.lang3.StringUtils;
 import org.springframework.util.Assert;
 
 
 
 
 
 
 
 
 
 public class DefaultExportDataGridHeaderSource
   implements IDefaultDataGridHeader
 {
   private String[] unexportColumn;
   private String dataGridHeadersJson;
   private boolean isHidden = false;
   
   private final String keyField = "field";
   private final String keyIndex = "index";
   private final String keyTitle = "title";
   private final String keyWidth = "width";
   private final String keyRowspan = "rowspan";
   private final String keyColspan = "colspan";
   private final String keyAlign = "align";
   private final String keyHalign = "halign";
   private final String keyHidden = "hidden";
   private final String keyCheckbox = "checkbox";
   
 
 
 
   private boolean hasRowNum = true;
   
 
 
 
   public List<DataGridHeader> getDataGridHeader()
   {
     Assert.notNull(this.dataGridHeadersJson, "导出字段不允许为空！");
     
     Map<String, Object>[] map = (Map[])JsonHelper.getInstance().readValue(this.dataGridHeadersJson, Map[].class);
     return dealUnexportHeader(convertMap2Oject(map));
   }
   
 
 
 
   private List<DataGridHeader> dealUnexportHeader(List<DataGridHeader> headers)
   {
     if ((this.unexportColumn == null) || (this.unexportColumn.length == 0)) {
       return headers;
     }
     List<DataGridHeader> result = new ArrayList();
     for (DataGridHeader header : headers)
     {
       if ((!ArrayUtils.contains(this.unexportColumn, header.getField())) && 
       
 
 
         (!Boolean.valueOf(header.getCheckbox()).booleanValue()))
       {
 
         result.add(header); }
     }
     return result;
   }
   
   private List<DataGridHeader> convertMap2Oject(Map<String, Object>[] map) {
     List<DataGridHeader> dataGridHeaders = new ArrayList(map.length);
     for (Map<String, Object> m : map) {
       DataGridHeader header = new DataGridHeader();
       getClass(); if (m.containsKey("field")) {
         getClass();header.setField(m.get("field").toString());
       }
       getClass(); if (m.containsKey("title")) {
         getClass();header.setTitle(m.get("title").toString());
       }
       getClass(); if (m.containsKey("align")) {
         getClass();header.setAlign(m.get("align").toString());
       }
       getClass(); if (m.containsKey("halign")) {
         getClass();header.setHalign(m.get("halign").toString());
       }
       getClass(); if (m.containsKey("index")) {
         getClass();header.setIndex(m.get("index").toString());
       }
       getClass(); if (m.containsKey("width")) {
         getClass();header.setWidth(m.get("width").toString());
       }
       getClass(); if (m.containsKey("checkbox")) {
         getClass();header.setCheckbox(m.get("checkbox").toString());
       }
       getClass(); if (m.containsKey("colspan")) {
         getClass();header.setColspan(m.get("colspan").toString());
       }
       getClass(); if (m.containsKey("rowspan")) {
         getClass();header.setRowspan(m.get("rowspan").toString());
       }
       getClass(); if (m.containsKey("hidden")) {
         getClass();header.setHidden(m.get("hidden").toString());
       }
       dataGridHeaders.add(header);
     }
     return dataGridHeaders;
   }
   
   public String[] getUnexportColumn()
   {
     return this.unexportColumn;
   }
   
 
 
 
   public void setUnexportColumn(String[] unexportColumn)
   {
     this.unexportColumn = unexportColumn;
   }
   
 
 
 
 
   public void setUnexportColumn(String string, char split)
   {
     this.unexportColumn = StringUtils.split(string, split);
   }
   
 
 
 
   public void setUnexportColumn(String string)
   {
     setUnexportColumn(string, ',');
   }
   
   public boolean getHasRowNum()
   {
     return this.hasRowNum;
   }
   
 
 
 
   public void setDataGridHeaders(String dataGridHeadersJson)
   {
     this.dataGridHeadersJson = dataGridHeadersJson;
   }
   
 
 
 
   public void setHasRowNum(boolean hasRowNum)
   {
     this.hasRowNum = hasRowNum;
   }
   
   public boolean exportHidden()
   {
     return this.isHidden;
   }
   
 
 
   public void setHidden(boolean isHidden)
   {
     this.isHidden = isHidden;
   }
 }


