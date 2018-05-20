 package com.piedpiper.platform.core.rest.resteasy;
 
 import java.net.InetSocketAddress;
 import java.util.concurrent.Executors;
 import javax.net.ssl.SSLContext;
 import org.jboss.netty.bootstrap.Bootstrap;
 import org.jboss.netty.bootstrap.ServerBootstrap;
 import org.jboss.netty.channel.ChannelPipelineFactory;
 import org.jboss.netty.channel.group.ChannelGroup;
 import org.jboss.netty.channel.group.DefaultChannelGroup;
 import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
 import org.jboss.resteasy.core.SynchronousDispatcher;
 import org.jboss.resteasy.plugins.server.netty.HttpServerPipelineFactory;
 import org.jboss.resteasy.plugins.server.netty.HttpsServerPipelineFactory;
 import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
 import org.jboss.resteasy.plugins.server.netty.RequestDispatcher;
 import org.jboss.resteasy.spi.ResteasyDeployment;
 
 public class ConfigurableNettyJaxrsServer extends NettyJaxrsServer
 {
   public ConfigurableNettyJaxrsServer(int executorThreadCount)
   {
     this.executorThreadCount = executorThreadCount;
   }
   
   private final int ioWorkerCount = Runtime.getRuntime().availableProcessors() * 2;
   private int executorThreadCount = 2000;
   private SSLContext sslContext;
   private final int maxRequestSize = 10485760;
   static final ChannelGroup allChannels = new DefaultChannelGroup("NettyJaxrsServer");
   
 
 
 
 
   public Bootstrap initBootstrap()
   {
     this.bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), this.ioWorkerCount));
     return this.bootstrap;
   }
   
   public void setBootstrap(ServerBootstrap bootstrap) {
     this.bootstrap = bootstrap;
   }
   
   public void start()
   {
     this.deployment.start();
     RequestDispatcher dispatcher = new RequestDispatcher((SynchronousDispatcher)this.deployment.getDispatcher(), this.deployment.getProviderFactory(), this.domain);
     
 
     if (this.bootstrap == null) {
       initBootstrap();
     }
     ChannelPipelineFactory factory;
     if (this.sslContext == null) {
       factory = new HttpServerPipelineFactory(dispatcher, this.root, this.executorThreadCount, 10485760, true);
     } else {
       factory = new HttpsServerPipelineFactory(dispatcher, this.root, this.executorThreadCount, 10485760, true, this.sslContext);
     }
     
     this.bootstrap.setPipelineFactory(factory);
     
 
     this.channel = this.bootstrap.bind(new InetSocketAddress(this.port));
     allChannels.add(this.channel);
   }
 }


