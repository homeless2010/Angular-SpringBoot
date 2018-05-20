 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 
 public class RoleExecutor extends AbstractExcutor
 {
   private String name;
   
   public RoleExecutor(String name)
   {
     this.name = name;
     initHeader();
   }
   
   private void initHeader() { this.headers.add(new ExcelHeader("roleName", "角色名称"));
     this.headers.add(new ExcelHeader("roleCode", "角色编码"));
     this.headers.add(new ExcelHeader("usageModifier", "使用级别"));
     this.headers.add(new ExcelHeader("desc", "描述"));
     this.headers.add(new ExcelHeader("orderBy", "排序"));
     this.headers.add(new ExcelHeader("validFlag", "状态"));
   }
   
   public void addHeader(ExcelHeader header) { this.headers.add(header); }
   
   public String getName()
   {
     return this.name;
   }
 }


