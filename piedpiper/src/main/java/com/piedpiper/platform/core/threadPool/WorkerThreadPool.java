package com.piedpiper.platform.core.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerThreadPool extends ThreadPoolExecutor {
	static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

	private static final AtomicLong sequnceCounter = new AtomicLong();

	private static final AtomicLong threadCounter = new AtomicLong();

	private static final AtomicLong rejectCounter = new AtomicLong();

	private static Logger logger = LoggerFactory.getLogger(WorkerThreadPool.class);

	private static final BlockingQueue<Runnable> workQueue = new PriorityBlockingQueue();

	public WorkerThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, KEEP_ALIVE_TIME_UNIT, workQueue, createThreadFactory(),
				createRejectedExecutionHandler());
	}

	private static ThreadFactory createThreadFactory() {
		ThreadFactory factory = new ThreadFactory() {
			public Thread newThread(Runnable r) {
				String name = "AsyncWorker-" + WorkerThreadPool.threadCounter.incrementAndGet();

				return new Thread(r, name);
			}
		};
		return factory;
	}

	private static RejectedExecutionHandler createRejectedExecutionHandler() {
		RejectedExecutionHandler handler = new RejectedExecutionHandler() {
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				WorkerThreadPool.rejectCounter.incrementAndGet();
				WorkerThreadPool.logger.warn("AsyncWorkerThreadPool.Rejected:" + r.toString());
			}
		};
		return handler;
	}

	public final Future<?> submitRunnable(Runnable task, Priority p) {
		if (task == null)
			throw new NullPointerException();
		if (threadCounter.get() % 1000L == 0L) {
			log();
		}
		RunnableFuture ftask = new ComparableFutureTask(task, null, p, sequnceCounter.incrementAndGet());
		execute(ftask);
		return ftask;
	}

	public final <T> Future<T> submitCall(Callable<T> task, Priority p) {
		if (task == null)
			throw new NullPointerException();
		if (threadCounter.get() % 1000L == 0L) {
			log();
		}
		RunnableFuture ftask = new ComparableFutureTask(task, p, sequnceCounter.incrementAndGet());
		execute(ftask);
		return ftask;
	}

	public final <T> Future<T> submitRunnable(Runnable task, T result, Priority p) {
		if (task == null) {
			throw new NullPointerException();
		}
		if (threadCounter.get() % 1000L == 0L) {
			log();
		}
		RunnableFuture ftask = new ComparableFutureTask(task, result, p, sequnceCounter.incrementAndGet());
		execute(ftask);
		return ftask;
	}

	private void log() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getClass()).append("counter:").append(threadCounter.get()).append("rejected:")
				.append(rejectCounter.get());
		sb.append("active:").append(getActiveCount()).append("Completed:").append(getCompletedTaskCount())
				.append("taskCnt");
		sb.append(getTaskCount());
		logger.debug(sb.toString());
	}

	class ComparableFutureTask<R> extends FutureTask<R> implements Comparable<Comparable<R>> {
		final WorkerThreadPool.Priority priority;
		final long sequnce;

		public ComparableFutureTask(Runnable runnable, R result, Priority priority, long sequnce) {
			super(runnable, result);
			this.priority = priority;
			this.sequnce = sequnce;
		}

		public ComparableFutureTask(Callable callable, Priority priority, long sequnce) {
			super(callable);
			this.priority = priority;
			this.sequnce = sequnce;
		}

		public int compareTo(Comparable<R> o) {
			if (!(o instanceof ComparableFutureTask)) {
				throw new IllegalStateException("!instanceof PriorityLevel");
			}
			ComparableFutureTask another = (ComparableFutureTask) o;
			int diff = another.priority.ordinal() - this.priority.ordinal();
			if (diff != 0) {
				return diff;
			}
			return (int) (this.sequnce - another.sequnce);
		}
	}

	public static enum Priority {
		LOW, NORMAL, HIGH, IMMEDIATE;
	}
}