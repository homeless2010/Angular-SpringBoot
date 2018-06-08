 package com.piedpiper.platform.core.mybatis.shard.converter;
 
 import net.sf.jsqlparser.schema.Table;
 import net.sf.jsqlparser.statement.Statement;
 import net.sf.jsqlparser.statement.delete.Delete;
 import org.slf4j.Logger;
 
 public class DeleteSqlConverter extends AbstractSqlConverter
 {
   static Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteSqlConverter.class);
   
   protected Statement doConvert(Statement statement, Object params, String mapperId)
   {
     if (!(statement instanceof Delete)) {
       throw new IllegalArgumentException("The argument statement must is instance of Delete.");
     }
     
     Delete delete = (Delete)statement;
     String name = delete.getTable().getName();
     
     delete.getTable().setName(convertTableName(name, params, mapperId));
     
 
     return delete;
   }
 }


