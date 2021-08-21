package com.springboot.async.proxy.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.springboot.async.proxy.api.AsyncExecutor;
import com.springboot.async.proxy.api.AsyncResult;

public class ThreadPoolBasedAsyncExecutor extends ThreadPoolExecutor implements AsyncExecutor {
	     
    private static volatile boolean isInit = false;
 
    private static volatile boolean isDestroy = false;
 
    private static ExecutorService executorService = null;

    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
 
    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }
 
    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }
 
    public ThreadPoolBasedAsyncExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
   
 
    @SuppressWarnings("all")
    public static <T> AsyncResult<T> submit(Object target, Method method, Object[] objects) {
        // 初始化的判断
        if(!isInit) {
            init();
        }
        //通过线程池提交
        Future future =  executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    method.invoke(target, objects);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                	e.printStackTrace();
                } catch (InvocationTargetException e) {
                	e.printStackTrace();
                }
            }
        });
 
        FutureBasedAsyncResult<T> asyncResult = new FutureBasedAsyncResult<>();
        asyncResult.setFuture(future);
        return asyncResult;
    }


    private static synchronized void init() {
        try {
            if(isInit) {
                return;
            }
 
            executorService = Executors.newFixedThreadPool(10);
            updateExecutorStatus(true);
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
 
    private static synchronized void destroy() {
        if(isDestroy) {
            return;
        }
 
        executorService = null;
        updateExecutorStatus(false);
    }
 
    private static void updateExecutorStatus(final boolean initStatus) {
        isInit = initStatus;
        isDestroy = !isInit;
    }
}