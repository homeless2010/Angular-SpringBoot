 package com.piedpiper.platform.core.mybatis.shard.converter;
 
 import java.util.List;
 import net.sf.jsqlparser.schema.Table;
 import net.sf.jsqlparser.statement.Statement;
 import net.sf.jsqlparser.statement.update.Update;
 
 public class UpdateSqlConverter
   extends AbstractSqlConverter
 {
   protected Statement doConvert(Statement statement, Object params, String mapperId)
   {
     if (!(statement instanceof Update)) {
       throw new IllegalArgumentException("The argument statement must is instance of Update.");
     }
     
     Update update = (Update)statement;
     List<Table> list = update.getTables();
     if ((list != null) && (list.size() > 0)) {
       for (int i = 0; i < list.size(); i++) {
         Table table = (Table)list.get(i);
         table.setName(convertTableName(table.getName(), params, mapperId));
       }
     }
     
 
 
 
     return update;
   }
 }


