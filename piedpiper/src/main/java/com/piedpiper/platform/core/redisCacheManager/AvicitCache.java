package com.piedpiper.platform.core.redisCacheManager;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface AvicitCache
{
  String prefix();
  
  Class<?> beanClass();
}


