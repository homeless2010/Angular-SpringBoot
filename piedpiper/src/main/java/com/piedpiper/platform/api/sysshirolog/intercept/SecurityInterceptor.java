package com.piedpiper.platform.api.sysshirolog.intercept;

import com.piedpiper.platform.api.sysshirolog.utils.HttpRequestResponseHolder;

public abstract interface SecurityInterceptor
{
  public abstract void beforeLogin(HttpRequestResponseHolder paramHttpRequestResponseHolder)
    throws Exception;
  
  public abstract void afterLogin(HttpRequestResponseHolder paramHttpRequestResponseHolder)
    throws Exception;
  
  public abstract void loginFailure(HttpRequestResponseHolder paramHttpRequestResponseHolder)
    throws Exception;
  
  public abstract void beforeAuthorization(HttpRequestResponseHolder paramHttpRequestResponseHolder)
    throws Exception;
  
  public abstract void afterAuthorization(HttpRequestResponseHolder paramHttpRequestResponseHolder)
    throws Exception;
  
  public abstract void authorizationFailure(HttpRequestResponseHolder paramHttpRequestResponseHolder)
    throws Exception;
}


