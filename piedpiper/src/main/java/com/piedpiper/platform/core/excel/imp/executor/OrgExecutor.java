 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 import java.util.Set;
 
 
 
 
 public class OrgExecutor
   extends AbstractExcutor
 {
   private String name;
   
   public OrgExecutor(String name)
   {
     this.name = name;
     initHeader();
   }
   
   private void initHeader() { this.headers.add(new ExcelHeader("orgName", "组织名称"));
     this.headers.add(new ExcelHeader("orgCode", "组织编码"));
     this.headers.add(new ExcelHeader("orgPlace", "办公地点"));
     this.headers.add(new ExcelHeader("parentOrgId", "父组织代码"));
     this.headers.add(new ExcelHeader("validFlag", "状态"));
     this.headers.add(new ExcelHeader("post", "邮编"));
     this.headers.add(new ExcelHeader("orderBy", "排序"));
     this.headers.add(new ExcelHeader("workCalendarId", "工作日历"));
     this.headers.add(new ExcelHeader("orgDesc", "描述"));
   }
   
   public void addHeader(ExcelHeader header) { this.headers.add(header); }
   
   public String getName()
   {
     return this.name;
   }
 }


