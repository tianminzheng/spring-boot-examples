package com.springboot.async.proxy.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.springboot.async.proxy.api.AsyncResult;

public abstract class AbstractAsyncResult<T> implements AsyncResult<T> {
	 
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }
 
    @Override
    public boolean isCancelled() {
        return false;
    }
 
    @Override
    public boolean isDone() {
        return false;
    }
 
    @Override
    public T get() throws InterruptedException, ExecutionException {
        try {
            return this.get(10 * 1000, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
 
}