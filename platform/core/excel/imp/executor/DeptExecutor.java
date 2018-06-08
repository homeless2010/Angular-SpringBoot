 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 
 public class DeptExecutor extends AbstractExcutor
 {
   private String name;
   
   public DeptExecutor(String name)
   {
     this.name = name;
     initHeader();
   }
   
   private void initHeader() { this.headers.add(new ExcelHeader("deptName", "部门名称"));
     this.headers.add(new ExcelHeader("deptAlias", "部门别称"));
     this.headers.add(new ExcelHeader("deptCode", "部门编码"));
     this.headers.add(new ExcelHeader("parentDeptId", "父部门编码"));
     this.headers.add(new ExcelHeader("orgId", "所属组织编码"));
     this.headers.add(new ExcelHeader("deptDesc", "描述"));
     this.headers.add(new ExcelHeader("orderBy", "排序编号"));
     this.headers.add(new ExcelHeader("deptType", "部门类型"));
     this.headers.add(new ExcelHeader("post", "邮编"));
     this.headers.add(new ExcelHeader("deptPlace", "办公地点"));
     this.headers.add(new ExcelHeader("validFlag", "状态"));
     this.headers.add(new ExcelHeader("workCalendarId", "工作日历"));
   }
   
   public void addHeader(ExcelHeader header) { this.headers.add(header); }
   
   public String getName()
   {
     return this.name;
   }
 }


