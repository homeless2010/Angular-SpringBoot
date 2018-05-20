 package com.piedpiper.platform.core.threadPool;
 
 import java.util.Collection;
 import java.util.List;
 import java.util.concurrent.Callable;
 import java.util.concurrent.ExecutionException;
 import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Future;
 import java.util.concurrent.Semaphore;
 import java.util.concurrent.TimeUnit;
 
 public class MyFixedThreadPool implements ExecutorService
 {
   private Semaphore semaphore;
   
   public Semaphore getSemaphore()
   {
     return this.semaphore;
   }
   
   public void setSemaphore(Semaphore semaphore) { this.semaphore = semaphore; }
   
   private ExecutorService service = java.util.concurrent.Executors.newFixedThreadPool(5);
   
   public MyFixedThreadPool(int bound) {
     this.semaphore = new Semaphore(bound);
   }
   
   public void execute(Runnable command) {
     try {
       this.semaphore.acquire();
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     
 
     this.service.execute(command);
   }
   
 
 
 
   public void shutdown() {}
   
 
 
   public List<Runnable> shutdownNow()
   {
     return null;
   }
   
   public boolean isShutdown()
   {
     return false;
   }
   
   public boolean isTerminated()
   {
     return false;
   }
   
   public boolean awaitTermination(long timeout, TimeUnit unit)
     throws InterruptedException
   {
     return false;
   }
   
   public <T> Future<T> submit(Callable<T> task) {
     try {
       this.semaphore.acquire();
     } catch (InterruptedException e) {
       e.printStackTrace();
     }
     
     Future<T> future = this.service.submit(task);
     this.semaphore.release();
     return future;
   }
   
   public <T> Future<T> submit(Runnable task, T result)
   {
     return null;
   }
   
   public Future<?> submit(Runnable task)
   {
     return null;
   }
   
   public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
     throws InterruptedException
   {
     return null;
   }
   
 
   public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
     throws InterruptedException
   {
     return null;
   }
   
   public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
     throws InterruptedException, ExecutionException
   {
     return null;
   }
   
 
   public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
     throws InterruptedException, ExecutionException, java.util.concurrent.TimeoutException
   {
     return null;
   }
 }


