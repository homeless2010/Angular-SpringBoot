package com.piedpiper.platform.core.shiroSecurity.passwordencoder;

import org.springframework.dao.DataAccessException;

public abstract interface PasswordEncoder
{
  public abstract String encodePassword(String paramString, Object paramObject)
    throws DataAccessException;
  
  public abstract boolean isPasswordValid(String paramString1, String paramString2, Object paramObject)
    throws DataAccessException;
}


