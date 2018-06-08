 package com.piedpiper.platform.core.mybatis.pagehelper;
 
 import com.piedpiper.platform.core.rest.msg.PageParameter;
 import java.util.List;
 import java.util.Properties;
 import org.apache.ibatis.executor.Executor;
 import org.apache.ibatis.mapping.MappedStatement;
 import org.apache.ibatis.mapping.SqlSource;
 import org.apache.ibatis.plugin.Interceptor;
 import org.apache.ibatis.plugin.Intercepts;
 import org.apache.ibatis.plugin.Invocation;
 import org.apache.ibatis.plugin.Plugin;
 import org.apache.ibatis.session.RowBounds;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 @Intercepts({@org.apache.ibatis.plugin.Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
 public class PageHelper
   implements Interceptor
 {
   private static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal();
   
   private SqlUtil SQLUTIL;
   
   private boolean offsetAsPageNum = false;
   
   private boolean rowBoundsWithCount = false;
   
   private boolean pageSizeZero = false;
   
   private boolean reasonable = false;
   
 
 
 
 
 
   public static void startPage(int pageNum, int pageSize)
   {
     startPage(pageNum, pageSize, true);
   }
   
   public static void startPage(PageParameter pageParameter) {
     LOCAL_PAGE.set(new Page(pageParameter.getPage(), pageParameter.getRows(), true));
   }
   
 
 
 
 
 
 
   public static void startPage(int pageNum, int pageSize, boolean count)
   {
     LOCAL_PAGE.set(new Page(pageNum, pageSize, count));
   }
   
 
 
 
 
 
   private Page getPage(RowBounds rowBounds)
   {
     Page page = (Page)LOCAL_PAGE.get();
     
     LOCAL_PAGE.remove();
     if (page == null) {
       if (this.offsetAsPageNum) {
         page = new Page(rowBounds.getOffset(), rowBounds.getLimit(), this.rowBoundsWithCount);
       } else {
         page = new Page(rowBounds, this.rowBoundsWithCount);
       }
     }
     
     page.setReasonable(this.reasonable);
     return page;
   }
   
 
 
 
 
 
   public Object intercept(Invocation invocation)
     throws Throwable
   {
     Object[] args = invocation.getArgs();
     RowBounds rowBounds = (RowBounds)args[2];
     if ((LOCAL_PAGE.get() == null) && (rowBounds == RowBounds.DEFAULT)) {
       return invocation.proceed();
     }
     
     MappedStatement ms = (MappedStatement)args[0];
     
     args[2] = RowBounds.DEFAULT;
     
     Page page = getPage(rowBounds);
     
     if ((this.pageSizeZero) && (page.getPageSize() == 0))
     {
       Object result = invocation.proceed();
       
       page.addAll((List)result);
       
       page.setPageNum(1);
       
       page.setPageSize(page.size());
       
       page.setTotal(page.size());
       
       return page;
     }
     SqlSource sqlSource = ((MappedStatement)args[0]).getSqlSource();
     
     if (page.isCount())
     {
       this.SQLUTIL.processCountMappedStatement(ms, sqlSource, args);
       
       Object result = invocation.proceed();
       
       page.setTotal(((Integer)((List)result).get(0)).intValue());
       if (page.getTotal() == 0L) {
         return page;
       }
     }
     
     if ((page.getPageSize() > 0) && (((rowBounds == RowBounds.DEFAULT) && (page.getPageNum() > 0)) || (rowBounds != RowBounds.DEFAULT)))
     {
 
 
       this.SQLUTIL.processPageMappedStatement(ms, sqlSource, page, args);
       
       Object result = invocation.proceed();
       
       page.addAll((List)result);
     }
     
     return page;
   }
   
 
 
 
 
 
 
   public Object plugin(Object target)
   {
     if ((target instanceof Executor)) {
       return Plugin.wrap(target, this);
     }
     return target;
   }
   
 
 
 
 
 
 
   public void setProperties(Properties p)
   {
     String dialect = p.getProperty("dialect");
     this.SQLUTIL = new SqlUtil(dialect);
     
     String offsetAsPageNum = p.getProperty("offsetAsPageNum");
     this.offsetAsPageNum = Boolean.parseBoolean(offsetAsPageNum);
     
     String rowBoundsWithCount = p.getProperty("rowBoundsWithCount");
     this.rowBoundsWithCount = Boolean.parseBoolean(rowBoundsWithCount);
     
     String pageSizeZero = p.getProperty("pageSizeZero");
     this.pageSizeZero = Boolean.parseBoolean(pageSizeZero);
     
     String reasonable = p.getProperty("reasonable");
     this.reasonable = Boolean.parseBoolean(reasonable);
   }
 }


