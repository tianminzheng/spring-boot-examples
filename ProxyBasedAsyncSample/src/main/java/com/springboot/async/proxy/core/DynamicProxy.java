package com.springboot.async.proxy.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.springboot.async.proxy.api.AsyncProxy;

public class DynamicProxy implements InvocationHandler, AsyncProxy {
	 
    //被代理的对象
    private final Object target;
 
    public DynamicProxy(Object target) {
        this.target = target;
    }
 
    @Override
    @SuppressWarnings("all")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	//提交到Executor并返回结果
    	return ThreadPoolBasedAsyncExecutor.submit(target, method, args);
    }
 
    @Override
    public Object proxy() {
        InvocationHandler handler = new DynamicProxy(target);
 
        Object result = Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
        
        return result;
    }
}