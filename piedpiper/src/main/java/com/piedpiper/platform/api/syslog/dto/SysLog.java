 package com.piedpiper.platform.api.syslog.dto;
 
 import com.piedpiper.platform.core.domain.BeanDTO;
 import com.piedpiper.platform.core.properties.PlatformConstant;
 import com.piedpiper.platform.core.properties.PlatformConstant.LogType;
 import java.util.Date;
 import java.util.Map;
 import org.apache.commons.lang3.time.FastDateFormat;
 import org.springframework.util.StringUtils;
 
 
 
 
 
 
 
 
 public class SysLog
   extends BeanDTO
 {
   private static char _c = ',';
   private static FastDateFormat _f = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
   
   private static final long serialVersionUID = -1876336639096002565L;
   
   private String id;
   
   private String sysApplicationId;
   
   private String syslogUsername;
   
   private Date syslogTime;
   
   private String syslogIp;
   
   private String syslogOp;
   private String syslogModule;
   private String syslogType;
   private String syslogIsGd;
   private String syslogResult;
   private String syslogContent;
   private String syslogTable;
   private String syslogFormid;
   private String syslogSecretlevel;
   private String syslogTitle;
   private String syslogUsernameZh;
   private Date syslogTimeBegin;
   private Date syslogTimeEnd;
   
   public String toString()
   {
     StringBuffer ss = new StringBuffer();
     if (!StringUtils.isEmpty(this.syslogUsernameZh)) {
       ss.append("操作人：");
       ss.append(this.syslogUsernameZh);
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogOp)) {
       ss.append("操作类型:");
       ss.append((String)PlatformConstant.opTypeMap.get(this.syslogOp));
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogType)) {
       ss.append("日志类型:");
       ss.append((String)PlatformConstant.logTypeMap.get(this.syslogType));
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogModule)) {
       ss.append("模块名称:");
       ss.append(this.syslogModule);
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogTimeBegin)) {
       ss.append("开始时间:");
       ss.append(_f.format(this.syslogTimeBegin));
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogTimeEnd)) {
       ss.append("结束时间:");
       ss.append(_f.format(this.syslogTimeEnd));
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogContent)) {
       ss.append("日志内容：");
       ss.append(this.syslogContent);
       ss.append(_c);
     }
     if (!StringUtils.isEmpty(this.syslogIp)) {
       ss.append("操作人IP：");
       ss.append(this.syslogIp);
       ss.append(_c);
     }
     if (ss.length() == 0) {
       return "";
     }
     ss.deleteCharAt(ss.length() - 1);
     return ss.toString();
   }
   
   public void convert() {
     setSyslogType((String)PlatformConstant.logTypeMap.get(getSyslogType()));
     setSyslogOp((String)PlatformConstant.opTypeMap.get(getSyslogOp()));
     setSyslogResult((String)PlatformConstant.opResultMap.get(getSyslogResult()));
   }
   
   public Date getSyslogTimeBegin() { return this.syslogTimeBegin; }
   
   public void setSyslogTimeBegin(Date syslogTimeBegin)
   {
     this.syslogTimeBegin = syslogTimeBegin;
   }
   
   public Date getSyslogTimeEnd() {
     return this.syslogTimeEnd;
   }
   
   public void setSyslogTimeEnd(Date syslogTimeEnd) {
     this.syslogTimeEnd = syslogTimeEnd;
   }
   
   public String getId() {
     return this.id;
   }
   
   public void setId(String id) {
     this.id = id;
   }
   
   public String getSysApplicationId() {
     return this.sysApplicationId;
   }
   
   public void setSysApplicationId(String sysApplicationId) {
     this.sysApplicationId = sysApplicationId;
   }
   
   public String getSyslogUsername() {
     return this.syslogUsername;
   }
   
   public void setSyslogUsername(String syslogUsername) {
     this.syslogUsername = syslogUsername;
   }
   
   public String getSyslogUsernameZh() { return this.syslogUsernameZh; }
   
   public void setSyslogUsernameZh(String syslogUsernameZh)
   {
     this.syslogUsernameZh = syslogUsernameZh;
   }
   
   public Date getSyslogTime() { return this.syslogTime; }
   
   public void setSyslogTime(Date syslogTime)
   {
     this.syslogTime = syslogTime;
   }
   
   public String getSyslogIp() {
     return this.syslogIp;
   }
   
   public void setSyslogIp(String syslogIp) {
     this.syslogIp = syslogIp;
   }
   
   public String getSyslogOp() {
     return this.syslogOp;
   }
   
   public void setSyslogOp(String syslogOp) {
     this.syslogOp = syslogOp;
   }
   
   public String getSyslogModule() {
     return this.syslogModule;
   }
   
   public void setSyslogModule(String syslogModule) {
     this.syslogModule = syslogModule;
   }
   
   public String getSyslogType() {
     return this.syslogType;
   }
   
   public void setSyslogType(String syslogType) {
     this.syslogType = syslogType;
   }
   
   public String getSyslogIsGd() {
     return this.syslogIsGd;
   }
   
   public void setSyslogIsGd(String syslogIsGd) {
     this.syslogIsGd = syslogIsGd;
   }
   
   public String getSyslogResult() {
     return this.syslogResult;
   }
   
   public void setSyslogResult(String syslogResult) {
     this.syslogResult = syslogResult;
   }
   
   public String getSyslogTitle() {
     return this.syslogTitle;
   }
   
   public void setSyslogTitle(String syslogTitle) {
     this.syslogTitle = syslogTitle;
   }
   
   public String getSyslogContent() {
     return this.syslogContent;
   }
   
   public void setSyslogContent(String syslogContent) {
     this.syslogContent = syslogContent;
   }
   
   public String getSyslogTable() {
     return this.syslogTable;
   }
   
   public void setSyslogTable(String syslogTable) {
     this.syslogTable = syslogTable;
   }
   
   public String getSyslogFormid() {
     return this.syslogFormid;
   }
   
   public void setSyslogFormid(String syslogFormid) {
     this.syslogFormid = syslogFormid;
   }
   
   public String getSyslogSecretlevel() {
     return this.syslogSecretlevel;
   }
   
   public void setSyslogSecretlevel(String syslogSecretlevel) {
     this.syslogSecretlevel = syslogSecretlevel;
   }
   
   public String getLogFormName()
   {
     if ((this.logFormName == null) || (this.logFormName.equals(""))) {
       return "日志管理";
     }
     return this.logFormName;
   }
   
   public String getLogTitle()
   {
     if ((this.logTitle == null) || (this.logTitle.equals(""))) {
       return "系统日志信息";
     }
     return this.logTitle;
   }
   
   public PlatformConstant.LogType getLogType()
   {
     if ((this.logType == null) || (this.logType.equals(""))) {
       return PlatformConstant.LogType.safety_audit;
     }
     return this.logType;
   }
   
 
   public String returnLogAppId()
   {
     return this.sysApplicationId;
   }
 }


