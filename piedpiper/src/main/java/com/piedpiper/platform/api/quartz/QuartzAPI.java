package com.piedpiper.platform.api.quartz;

import com.piedpiper.platform.api.quartz.dto.Job;
import com.piedpiper.platform.api.quartz.dto.JobCalendarToJob;
import com.piedpiper.platform.api.quartz.dto.Variable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract interface QuartzAPI
{
  public abstract Job getJob(String paramString);
  
  public abstract Map<String, Object> loadJobs(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3);
  
  public abstract Map<String, Object> loadJobsByGroup(String paramString);
  
  public abstract boolean addJob(Job paramJob, String paramString, List<Variable> paramList)
    throws Exception;
  
  public abstract boolean updateJob(Job paramJob, String paramString, List<Variable> paramList)
    throws Exception;
  
  public abstract boolean deleteJobById(String paramString)
    throws Exception;
  
  public abstract Collection<Variable> findJobVariables(String paramString);
  
  public abstract List<JobCalendarToJob> findJobCalendarToJob(String paramString);
}


