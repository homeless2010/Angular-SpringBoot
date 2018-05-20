 package com.piedpiper.platform.core.spring;
 
 import com.piedpiper.platform.core.properties.PlatformProperties;
 import java.io.PrintStream;
 import java.rmi.AlreadyBoundException;
 import java.rmi.NoSuchObjectException;
 import java.rmi.NotBoundException;
 import java.rmi.Remote;
 import java.rmi.RemoteException;
 import java.rmi.registry.LocateRegistry;
 import java.rmi.registry.Registry;
 import java.rmi.server.RMIClientSocketFactory;
 import java.rmi.server.RMIServerSocketFactory;
 import java.rmi.server.UnicastRemoteObject;
 import org.apache.commons.logging.Log;
 import org.springframework.beans.factory.DisposableBean;
 import org.springframework.beans.factory.InitializingBean;
 import org.springframework.remoting.rmi.RmiBasedExporter;
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 public class PlatformRmiServiceExporter
   extends RmiBasedExporter
   implements InitializingBean, DisposableBean
 {
   private String serviceName;
   private int servicePort = 0;
   
   private RMIClientSocketFactory clientSocketFactory;
   
   private RMIServerSocketFactory serverSocketFactory;
   
   private Registry registry;
   
   private String registryHost;
   
   private int registryPort = 1099;
   
   private RMIClientSocketFactory registryClientSocketFactory;
   
   private RMIServerSocketFactory registryServerSocketFactory;
   
   private boolean alwaysCreateRegistry = false;
   
   private boolean replaceExistingBinding = true;
   
   private Remote exportedObject;
   
   private boolean createdRegistry = false;
   
 
 
 
 
   public void setServiceName(String serviceName)
   {
     this.serviceName = serviceName;
   }
   
 
 
 
   public void setServicePort(int servicePort)
   {
     this.servicePort = servicePort;
   }
   
 
 
 
 
 
 
 
 
   public void setClientSocketFactory(RMIClientSocketFactory clientSocketFactory)
   {
     this.clientSocketFactory = clientSocketFactory;
   }
   
 
 
 
 
 
 
 
 
   public void setServerSocketFactory(RMIServerSocketFactory serverSocketFactory)
   {
     this.serverSocketFactory = serverSocketFactory;
   }
   
 
 
 
 
 
 
 
 
 
 
 
 
 
   public void setRegistry(Registry registry)
   {
     this.registry = registry;
   }
   
 
 
 
 
   public void setRegistryHost(String registryHost)
   {
     this.registryHost = registryHost;
   }
   
 
 
 
 
 
   public void setRegistryPort(int registryPort)
   {
     this.registryPort = registryPort;
   }
   
 
 
 
 
 
 
 
 
   public void setRegistryClientSocketFactory(RMIClientSocketFactory registryClientSocketFactory)
   {
     this.registryClientSocketFactory = registryClientSocketFactory;
   }
   
 
 
 
 
 
 
 
 
   public void setRegistryServerSocketFactory(RMIServerSocketFactory registryServerSocketFactory)
   {
     this.registryServerSocketFactory = registryServerSocketFactory;
   }
   
 
 
 
 
 
 
   public void setAlwaysCreateRegistry(boolean alwaysCreateRegistry)
   {
     this.alwaysCreateRegistry = alwaysCreateRegistry;
   }
   
 
 
 
 
 
 
 
 
   public void setReplaceExistingBinding(boolean replaceExistingBinding)
   {
     this.replaceExistingBinding = replaceExistingBinding;
   }
   
   public void afterPropertiesSet() throws RemoteException
   {
     prepare();
   }
   
 
 
 
   public void prepare()
     throws RemoteException
   {
     String isStart = PlatformProperties.getProperty("rmi.start");
     if ((isStart != null) && (isStart.equals("true"))) {
       realPrepare();
     } else {
       System.out.println("======禁止启动RMI服务");
     }
   }
   
   public void realPrepare() throws RemoteException {
     checkService();
     
     if (this.serviceName == null) {
       throw new IllegalArgumentException("Property 'serviceName' is required");
     }
     
 
     if ((this.clientSocketFactory instanceof RMIServerSocketFactory)) {
       this.serverSocketFactory = ((RMIServerSocketFactory)this.clientSocketFactory);
     }
     if (((this.clientSocketFactory != null) && (this.serverSocketFactory == null)) || ((this.clientSocketFactory == null) && (this.serverSocketFactory != null)))
     {
       throw new IllegalArgumentException("Both RMIClientSocketFactory and RMIServerSocketFactory or none required");
     }
     
 
 
     if ((this.registryClientSocketFactory instanceof RMIServerSocketFactory)) {
       this.registryServerSocketFactory = ((RMIServerSocketFactory)this.registryClientSocketFactory);
     }
     if ((this.registryClientSocketFactory == null) && (this.registryServerSocketFactory != null)) {
       throw new IllegalArgumentException("RMIServerSocketFactory without RMIClientSocketFactory for registry not supported");
     }
     
 
     this.createdRegistry = false;
     
 
     if (this.registry == null) {
       this.registry = getRegistry(this.registryHost, this.registryPort, this.registryClientSocketFactory, this.registryServerSocketFactory);
       
       this.createdRegistry = true;
     }
     
 
     this.exportedObject = getObjectToExport();
     
     if (this.logger.isInfoEnabled()) {
       this.logger.info("Binding service '" + this.serviceName + "' to RMI registry: " + this.registry);
     }
     
 
     if (this.clientSocketFactory != null) {
       UnicastRemoteObject.exportObject(this.exportedObject, this.servicePort, this.clientSocketFactory, this.serverSocketFactory);
     }
     else
     {
       UnicastRemoteObject.exportObject(this.exportedObject, this.servicePort);
     }
     
     try
     {
       if (this.replaceExistingBinding) {
         this.registry.rebind(this.serviceName, this.exportedObject);
       }
       else {
         this.registry.bind(this.serviceName, this.exportedObject);
       }
     }
     catch (AlreadyBoundException ex)
     {
       unexportObjectSilently();
       throw new IllegalStateException("Already an RMI object bound for name '" + this.serviceName + "': " + ex.toString());
 
     }
     catch (RemoteException ex)
     {
       unexportObjectSilently();
       throw ex;
     }
   }
   
 
 
 
 
 
 
 
 
 
 
 
   protected Registry getRegistry(String registryHost, int registryPort, RMIClientSocketFactory clientSocketFactory, RMIServerSocketFactory serverSocketFactory)
     throws RemoteException
   {
     if (registryHost != null)
     {
       if (this.logger.isInfoEnabled()) {
         this.logger.info("Looking for RMI registry at port '" + registryPort + "' of host [" + registryHost + "]");
       }
       Registry reg = LocateRegistry.getRegistry(registryHost, registryPort, clientSocketFactory);
       testRegistry(reg);
       return reg;
     }
     
 
     return getRegistry(registryPort, clientSocketFactory, serverSocketFactory);
   }
   
 
 
 
 
 
 
 
 
 
 
   protected Registry getRegistry(int registryPort, RMIClientSocketFactory clientSocketFactory, RMIServerSocketFactory serverSocketFactory)
     throws RemoteException
   {
     if (clientSocketFactory != null) {
       if (this.alwaysCreateRegistry) {
         this.logger.info("Creating new RMI registry");
         return LocateRegistry.createRegistry(registryPort, clientSocketFactory, serverSocketFactory);
       }
       if (this.logger.isInfoEnabled()) {
         this.logger.info("Looking for RMI registry at port '" + registryPort + "', using custom socket factory");
       }
       synchronized (LocateRegistry.class)
       {
         try {
           Registry reg = LocateRegistry.getRegistry(null, registryPort, clientSocketFactory);
           testRegistry(reg);
           return reg;
         }
         catch (RemoteException ex) {
           this.logger.debug("RMI registry access threw exception", ex);
           this.logger.info("Could not detect RMI registry - creating new one");
           
           return LocateRegistry.createRegistry(registryPort, clientSocketFactory, serverSocketFactory);
         }
       }
     }
     
 
     return getRegistry(registryPort);
   }
   
 
 
 
 
 
   protected Registry getRegistry(int registryPort)
     throws RemoteException
   {
     if (this.alwaysCreateRegistry) {
       this.logger.info("Creating new RMI registry");
       return LocateRegistry.createRegistry(registryPort);
     }
     if (this.logger.isInfoEnabled()) {
       this.logger.info("Looking for RMI registry at port '" + registryPort + "'");
     }
     synchronized (LocateRegistry.class)
     {
       try {
         Registry reg = LocateRegistry.getRegistry(registryPort);
         testRegistry(reg);
         return reg;
       }
       catch (RemoteException ex) {
         this.logger.debug("RMI registry access threw exception", ex);
         this.logger.info("Could not detect RMI registry - creating new one");
         
         return LocateRegistry.createRegistry(registryPort);
       }
     }
   }
   
 
 
 
 
 
 
   protected void testRegistry(Registry registry)
     throws RemoteException
   {
     registry.list();
   }
   
 
 
   public void destroy()
     throws RemoteException
   {
     if (this.logger.isInfoEnabled()) {
       this.logger.info("Unbinding RMI service '" + this.serviceName + "' from registry" + (this.createdRegistry ? " at port '" + this.registryPort + "'" : ""));
     }
     try
     {
       this.registry.unbind(this.serviceName);
     }
     catch (NotBoundException ex) {
       if (this.logger.isWarnEnabled()) {
         this.logger.warn("RMI service '" + this.serviceName + "' is not bound to registry" + (this.createdRegistry ? " at port '" + this.registryPort + "' anymore" : ""), ex);
       }
     }
     finally
     {
       unexportObjectSilently();
     }
   }
   
 
   private void unexportObjectSilently()
   {
     try
     {
       UnicastRemoteObject.unexportObject(this.exportedObject, true);
     }
     catch (NoSuchObjectException ex) {
       if (this.logger.isWarnEnabled()) {
         this.logger.warn("RMI object for service '" + this.serviceName + "' isn't exported anymore", ex);
       }
     }
   }
 }


