package com.piedpiper.platform.api.syspermissionresource.permissionanalysis.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface CustomBean
{
  String BeanName() default "avicit";
  
  boolean IsDefault() default true;
}


