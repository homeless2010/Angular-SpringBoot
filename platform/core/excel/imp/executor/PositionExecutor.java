 package com.piedpiper.platform.core.excel.imp.executor;
 
 import com.piedpiper.platform.core.excel.imp.entity.ExcelHeader;
 import java.util.Set;
 
 
 
 
 public class PositionExecutor
   extends AbstractExcutor
 {
   private String name;
   
   public PositionExecutor(String name)
   {
     this.name = name;
     initHeader();
   }
   
   private void initHeader() { this.headers.add(new ExcelHeader("positionCode", "岗位编码"));
     this.headers.add(new ExcelHeader("positionName", "岗位名称"));
     this.headers.add(new ExcelHeader("positionDesc", "描述"));
     this.headers.add(new ExcelHeader("orderBy", "排序编号"));
   }
   
   public void addHeader(ExcelHeader header) { this.headers.add(header); }
   
   public String getName()
   {
     return this.name;
   }
 }


