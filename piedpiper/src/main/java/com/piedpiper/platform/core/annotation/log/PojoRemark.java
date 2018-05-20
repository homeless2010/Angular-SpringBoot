package com.piedpiper.platform.core.annotation.log;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PojoRemark
{
  String table() default "";
  
  String object() default "";
  
  String name() default "";
}


