 package com.piedpiper.platform.api.quartz.impl;
 
 import com.piedpiper.platform.api.quartz.QuartzAPI;
 import com.piedpiper.platform.api.quartz.dto.Job;
 import com.piedpiper.platform.api.quartz.dto.JobCalendarToJob;
 import com.piedpiper.platform.api.quartz.dto.Variable;
 import com.piedpiper.platform.core.rest.client.RestClient;
 import com.piedpiper.platform.core.rest.client.RestClientConfig;
 import com.piedpiper.platform.core.rest.msg.Muti3Bean;
 import com.piedpiper.platform.core.rest.msg.Muti5Bean;
 import com.piedpiper.platform.core.rest.msg.ResponseMsg;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.List;
 import java.util.Map;
 import javax.ws.rs.core.GenericType;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.util.Assert;
 import org.springframework.util.StringUtils;
 
 
 
 
 
 
 
 
 
 
 public class QuartzAPImpl
   implements QuartzAPI, Serializable
 {
   private static final long serialVersionUID = 3550418086814634114L;
   private static final Logger log = LoggerFactory.getLogger(QuartzAPImpl.class);
   
 
   public static final String BASE_REST_URL = "/api/platform6/quartz/jobMaintainRest";
   
 
 
   public Job getJob(String id)
   {
     Job job = null;
     if (StringUtils.hasText(id)) {
       String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/getJobById/v1";
       ResponseMsg<Job> responseMsg = RestClient.doPost(url, id, new GenericType() {});
       if (responseMsg.getRetCode().equals("200")) {
         job = (Job)responseMsg.getResponseBody();
       } else {
         log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       }
     }
     return job;
   }
   
 
 
 
 
 
 
 
 
   public Map<String, Object> loadJobs(int pageNo, int pageSize, String searchName, String searchGroup, String searchStatus)
   {
     Muti5Bean<Integer, Integer, String, String, String> mb = new Muti5Bean();
     
     mb.setDto1(Integer.valueOf(pageNo));
     mb.setDto2(Integer.valueOf(pageSize));
     mb.setDto3(searchName);
     mb.setDto4(searchGroup);
     mb.setDto5(searchStatus);
     
     Map<String, Object> rmap = null;
     String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/loadJobsByPage/v1";
     ResponseMsg<Map<String, Object>> responseMsg = RestClient.doPost(url, mb, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       rmap = (Map)responseMsg.getResponseBody();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return rmap;
   }
   
 
 
 
 
   public Map<String, Object> loadJobsByGroup(String group)
   {
     Map<String, Object> rmap = null;
     String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/loadJobsByGroup/v1";
     ResponseMsg<Map<String, Object>> responseMsg = RestClient.doPost(url, group, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       rmap = (Map)responseMsg.getResponseBody();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return rmap;
   }
   
 
 
 
 
 
   public boolean addJob(Job job, String jobCalendarIds, List<Variable> variableList)
     throws Exception
   {
     boolean isSuccess = false;
     Map<String, Object> rmap = null;
     Muti3Bean<Job, String, List<Variable>> bean = new Muti3Bean();
     bean.setDto1(job);
     bean.setDto2(jobCalendarIds);
     bean.setDto3(variableList);
     String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/addJob/v1";
     ResponseMsg<Map<String, Object>> responseMsg = RestClient.doPost(url, bean, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       rmap = (Map)responseMsg.getResponseBody();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     if (rmap != null) {
       isSuccess = ((Boolean)rmap.get("flag")).booleanValue();
     }
     return isSuccess;
   }
   
 
 
 
 
 
   public boolean updateJob(Job job, String jobCalendarIds, List<Variable> variableList)
     throws Exception
   {
     boolean isSuccess = false;
     Map<String, Object> rmap = null;
     Muti3Bean<Job, String, List<Variable>> bean = new Muti3Bean();
     bean.setDto1(job);
     bean.setDto2(jobCalendarIds);
     bean.setDto3(variableList);
     String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/updateJob/v1";
     ResponseMsg<Map<String, Object>> responseMsg = RestClient.doPost(url, bean, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       rmap = (Map)responseMsg.getResponseBody();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     if (rmap != null) {
       isSuccess = ((Boolean)rmap.get("flag")).booleanValue();
     }
     return isSuccess;
   }
   
 
 
 
 
   public boolean deleteJobById(String id)
     throws Exception
   {
     Assert.notNull(id, "必须指定需要删除的任务的主键");
     boolean isSuccess = false;
     String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/deleteJobById/v1";
     ResponseMsg<Boolean> responseMsg = RestClient.doPost(url, id, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       isSuccess = ((Boolean)responseMsg.getResponseBody()).booleanValue();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     return isSuccess;
   }
   
 
 
 
 
   public Collection<Variable> findJobVariables(String jobId)
   {
     Collection<Variable> jobVariablesList = null;
     if (StringUtils.hasText(jobId)) {
       String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/findJobVariables/v1";
       ResponseMsg<Collection<Variable>> responseMsg = RestClient.doPost(url, jobId, new GenericType() {});
       if (responseMsg.getRetCode().equals("200")) {
         jobVariablesList = (Collection)responseMsg.getResponseBody();
       } else {
         log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
       }
     }
     if (jobVariablesList == null) {
       jobVariablesList = new ArrayList();
     }
     return jobVariablesList;
   }
   
 
 
 
 
   public List<JobCalendarToJob> findJobCalendarToJob(String jobId)
   {
     List<JobCalendarToJob> jobCalendarsList = null;
     String url = RestClientConfig.getRestHost("quartz") + "/api/platform6/quartz/jobMaintainRest" + "/findJobCalendarToJob/v1";
     ResponseMsg<List<JobCalendarToJob>> responseMsg = RestClient.doPost(url, jobId, new GenericType() {});
     if (responseMsg.getRetCode().equals("200")) {
       jobCalendarsList = (List)responseMsg.getResponseBody();
     } else {
       log.error("url:" + url + ",error:" + responseMsg.getErrorDesc());
     }
     if (jobCalendarsList == null) {
       jobCalendarsList = new ArrayList();
     }
     return jobCalendarsList;
   }
 }


