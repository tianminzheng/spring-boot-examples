package com.springboot.async.proxy.main;

import java.util.concurrent.ExecutionException;

import com.springboot.async.proxy.api.AsyncProxy;
import com.springboot.async.proxy.business.DemoService;
import com.springboot.async.proxy.business.DemoServiceImpl;
import com.springboot.async.proxy.core.DynamicProxy;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println(String.format("Main Thread：%s", Thread.currentThread().getName()));
		
		DemoService demoService = new DemoServiceImpl();
		
		AsyncProxy dynamicProxy = new DynamicProxy(demoService);
		DemoService target = (DemoService)dynamicProxy.proxy();
		target.perform();
	}
}
