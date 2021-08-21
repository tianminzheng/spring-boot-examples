package com.springboot.async.proxy.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureBasedAsyncResult<T> extends AbstractAsyncResult<T> {
	
    private Future<T> future;
 
    private Object value;
 
    @Override
    public Object getResult() {
        // 直接返回结果
        if(future == null) {
            return this.getValue();
        }
 
        try {
            T t = future.get();
            // 这里拿到的 AsyncResult 对象
            if(null != t) {
                return ((FutureBasedAsyncResult)t).getValue();
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
 
    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return future.get(timeout, unit);
    }
 
    public Object getValue() {
        return this.value;
    }
 
    public void setValue(Object value) {
        this.value = value;
    }
 
    public void setFuture(Future<T> future) {
        this.future = future;
    }
 
}