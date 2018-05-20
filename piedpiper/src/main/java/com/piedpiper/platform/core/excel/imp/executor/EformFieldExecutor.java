 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 import java.util.Set;
 
 public class EformFieldExecutor
   extends AbstractExcutor
 {
   private String name;
   
   public EformFieldExecutor(String name)
   {
     this.name = name;
     initHeader();
   }
   
   private void initHeader() {
     this.headers.add(new ExcelHeader("colLabel", "字段中文名"));
     this.headers.add(new ExcelHeader("colName", "字段名"));
     this.headers.add(new ExcelHeader("colType", "字段类型"));
     this.headers.add(new ExcelHeader("colLength", "长度"));
     this.headers.add(new ExcelHeader("colDecimal", "小数位数"));
     this.headers.add(new ExcelHeader("colIsDisplay", "是否可见"));
     this.headers.add(new ExcelHeader("colIsMust", "是否必著"));
     this.headers.add(new ExcelHeader("colIsVisible", "是否显示"));
     this.headers.add(new ExcelHeader("colIsEdit", "是否编辑"));
     this.headers.add(new ExcelHeader("colIsTabVisible", "是否列表显示"));
     this.headers.add(new ExcelHeader("colIsDetail", "是否详细显示"));
     this.headers.add(new ExcelHeader("colIsSearch", "查询字段"));
     this.headers.add(new ExcelHeader("colDropdownType", "下拉类型"));
     this.headers.add(new ExcelHeader("colOrder", "顺序"));
     this.headers.add(new ExcelHeader("remark", "备注"));
   }
   
   public void addHeader(ExcelHeader header)
   {
     this.headers.add(header);
   }
   
   public String getName()
   {
     return this.name;
   }
 }


