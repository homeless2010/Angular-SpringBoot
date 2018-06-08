 package com.piedpiper.platform.core.mybatis.shard.converter;
 
 import net.sf.jsqlparser.statement.Statement;
 
 public abstract class AbstractSqlConverter implements SqlConverter
 {
   public String convert(Statement statement, Object params, String mapperId)
   {
     return doDeParse(doConvert(statement, params, mapperId));
   }
   
 
 
 
   protected String doDeParse(Statement statement)
   {
     return statement.toString();
   }
   
 
 
 
 
 
   protected String convertTableName(String tableName, Object params, String mapperId)
   {
     com.piedpiper.platform.core.mybatis.shard.builder.ShardConfigHolder configFactory = com.piedpiper.platform.core.mybatis.shard.builder.ShardConfigHolder.getInstance();
     com.piedpiper.platform.core.mybatis.shard.strategy.ShardStrategy strategy = configFactory.getStrategy(tableName);
     if (strategy == null) {
       return tableName;
     }
     return strategy.getTargetTableName(tableName, params, mapperId);
   }
   
   protected abstract Statement doConvert(Statement paramStatement, Object paramObject, String paramString);
 }


