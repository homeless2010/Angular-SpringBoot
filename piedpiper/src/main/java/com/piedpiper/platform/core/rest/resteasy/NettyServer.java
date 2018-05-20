 package com.piedpiper.platform.core.rest.resteasy;
 
 import java.util.Collection;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.PreDestroy;
 import javax.ws.rs.ext.Provider;
 import org.jboss.netty.bootstrap.Bootstrap;
 import org.jboss.resteasy.spi.ResteasyDeployment;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ApplicationContext;
 import org.springframework.stereotype.Component;
 import org.springframework.util.Assert;
 
 @Component
 public class NettyServer
 {
   @Autowired
   ApplicationContext ac;
   ConfigurableNettyJaxrsServer netty;
   
   public void start(String rootResourcePath, int port, int executorThread)
   {
     ResteasyDeployment dp = new ResteasyDeployment();
     Collection<Object> providers = this.ac.getBeansWithAnnotation(Provider.class).values();
     Collection<Object> controllers = this.ac.getBeansWithAnnotation(RestEasyController.class).values();
     Assert.notEmpty(controllers);
     
     if (providers != null) {
       dp.getProviders().addAll(providers);
     }
     
     dp.getResources().addAll(controllers);
     this.netty = new ConfigurableNettyJaxrsServer(executorThread);
     this.netty.initBootstrap().setOption("reuseAddress", Boolean.valueOf(true));
     this.netty.setDeployment(dp);
     this.netty.setPort(port);
     this.netty.setRootResourcePath(rootResourcePath);
     this.netty.setSecurityDomain(null);
     this.netty.start();
   }
   
   @PreDestroy
   public void cleanUp() {
     this.netty.stop();
   }
 }


