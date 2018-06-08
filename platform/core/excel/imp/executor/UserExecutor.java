 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 import java.util.Set;
 
 public class UserExecutor
   extends AbstractExcutor
 {
   private String name;
   
   public UserExecutor(String name)
   {
     this.name = name;
     initHeader();
   }
   
   private void initHeader() {
     this.headers.add(new ExcelHeader("no", "用户编码"));
     this.headers.add(new ExcelHeader("name", "姓名"));
     this.headers.add(new ExcelHeader("loginName", "登录名"));
     this.headers.add(new ExcelHeader("loginPassword", "登录密码"));
     this.headers.add(new ExcelHeader("deptCode", "部门编码"));
     this.headers.add(new ExcelHeader("secretLevel", "密级代码"));
     this.headers.add(new ExcelHeader("roleCode", "角色编码"));
     this.headers.add(new ExcelHeader("positionCode", "岗位编码"));
     this.headers.add(new ExcelHeader("type", "类型"));
     this.headers.add(new ExcelHeader("orderBy", "排序"));
     this.headers.add(new ExcelHeader("unitCode", "集团编码"));
     this.headers.add(new ExcelHeader("isManager", "是否主管"));
     this.headers.add(new ExcelHeader("polity", "政治面貌"));
     this.headers.add(new ExcelHeader("workDate", "工作日期"));
     this.headers.add(new ExcelHeader("title", "职称"));
     this.headers.add(new ExcelHeader("sex", "性别"));
     this.headers.add(new ExcelHeader("mobile", "手机"));
     this.headers.add(new ExcelHeader("officeTel", "办公电话"));
     this.headers.add(new ExcelHeader("email", "邮件"));
     this.headers.add(new ExcelHeader("remark", "描述"));
     this.headers.add(new ExcelHeader("status", "状态"));
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


