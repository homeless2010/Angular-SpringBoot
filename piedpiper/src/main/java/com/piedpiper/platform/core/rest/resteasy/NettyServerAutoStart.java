 package com.piedpiper.platform.core.rest.resteasy;
 
 import com.piedpiper.platform.commons.utils.ComUtil;
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import java.util.Collection;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.PreDestroy;
 import javax.ws.rs.ext.Provider;
 import org.jboss.netty.bootstrap.Bootstrap;
 import org.jboss.resteasy.spi.ResteasyDeployment;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.InitializingBean;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.ApplicationContext;
 import org.springframework.stereotype.Component;
 
 @Component
 public class NettyServerAutoStart implements InitializingBean
 {
   private Logger log = LoggerFactory.getLogger(NettyServerAutoStart.class);
   @Autowired
   ApplicationContext ac;
   ConfigurableNettyJaxrsServer netty;
   
   public void start(String rootResourcePath, int port, int executorThreadCount)
   {
     this.log.info("开始启动内嵌netty服务，端口=" + port);
     ResteasyDeployment dp = new ResteasyDeployment();
     Collection<Object> providers = this.ac.getBeansWithAnnotation(Provider.class).values();
     Collection<Object> controllers = this.ac.getBeansWithAnnotation(RestEasyController.class).values();
     org.springframework.util.Assert.notEmpty(controllers);
     
     if (providers != null) {
       dp.getProviders().addAll(providers);
     }
     
     dp.getResources().addAll(controllers);
     this.netty = new ConfigurableNettyJaxrsServer(executorThreadCount);
     this.netty.initBootstrap().setOption("reuseAddress", Boolean.valueOf(true));
     this.netty.setDeployment(dp);
     this.netty.setPort(port);
     this.netty.setRootResourcePath(rootResourcePath);
     this.netty.setSecurityDomain(null);
     this.netty.start();
     this.log.info("启动内嵌netty服务完成");
   }
   
   @PreDestroy
   public void cleanUp() {
     this.netty.stop();
   }
   
   public void afterPropertiesSet()
     throws Exception
   {
     String nettyServerSwitch = ComUtil.replaceNull2Space(PlatformProperties.getProperty("netty.server.switch"));
     if ("on".equals(nettyServerSwitch)) {
       String nettyServerPort = ComUtil.replaceNull2Space(PlatformProperties.getProperty("netty.server.port"));
       String executorThreadCount = ComUtil.replaceNull2Space(PlatformProperties.getProperty("netty.server.thread"));
       if ("".equals(nettyServerPort)) nettyServerPort = "10001";
       if ("".equals(executorThreadCount)) executorThreadCount = "2000";
       start("", Integer.valueOf(nettyServerPort).intValue(), Integer.valueOf(executorThreadCount).intValue());
     }
   }
 }


