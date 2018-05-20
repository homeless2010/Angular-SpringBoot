package com.piedpiper.platform.api.sysshirolog.utils;

import com.piedpiper.platform.api.sysshirolog.context.FrameworkContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface FrameworkContextRepository
{
  public abstract FrameworkContext loadFrameworkContext(HttpRequestResponseHolder paramHttpRequestResponseHolder);
  
  public abstract void saveFrameworkContext(FrameworkContext paramFrameworkContext, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
  
  public abstract void setServletContext(ServletContext paramServletContext);
}


