package com.kingdee.apusic.spider.common.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPool {
	
	private BlockingQueue<Runnable> queue;
	private ThreadPoolExecutor executor;
	
	private static int corePoolSize = 22;
	private static int keepAliveTime  = 1800;		// 半个小时，以秒计
	
	public ThreadPool() {
		queue = new LinkedBlockingQueue<Runnable>();
		executor = 	new ThreadPoolExecutor(corePoolSize,
											Integer.MAX_VALUE, 		//MAX_POOL_SIZE，由于使用的Linked的缓冲队列，该值其实无效 
											keepAliveTime, TimeUnit.SECONDS, 
											queue);
	}
	public ThreadPool(int corePoolSize,int maxPoolSize ,int keepAliveTime) {
		queue = new LinkedBlockingQueue<Runnable>();
		executor = 	new ThreadPoolExecutor(corePoolSize,
											maxPoolSize,
											keepAliveTime, TimeUnit.SECONDS, 
											queue);
	}
	
	/**
	 * 添加任务
	 * @param r
	 */
	public void addTask (Runnable r) {
		executor.execute(r);
	}
	
	public void close () {
		executor.shutdown();
	}
	
	/**
	 * 得到活动线程数
	 * @return
	 */
	public int getActiveThread () {
		return executor.getActiveCount();
	}
}
