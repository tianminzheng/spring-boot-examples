package com.springboot.async.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@RestController
public class WebAsyncTaskController {

	@Autowired
	private ThreadPoolTaskExecutor executor;


	@RequestMapping(value = "case1", method = RequestMethod.GET)
	public WebAsyncTask<String> taskFinishedNormally() {
		System.out.println("The main Thread name is " + Thread.currentThread().getName());

		// 模拟开启一个异步任务
		WebAsyncTask<String> task1 = new WebAsyncTask<String>(5 * 1000L, () -> {
			System.out.println("The working Thread name is " + Thread.currentThread().getName());
			Thread.sleep(2 * 1000L);
			return "task executed！";
		});

		// 任务执行完成时调用该方法
		task1.onCompletion(() -> {
			System.out.println("task finished！");
		});

		// 可以继续执行其他操作
		System.out.println("task can do other things！");
		return task1;
	}

	@RequestMapping(value = "case2", method = RequestMethod.GET)
	public WebAsyncTask<String> taskWithTimeout() {
		System.out.println("The main Thread name is " + Thread.currentThread().getName());

		// 模拟开启一个异步任务
		WebAsyncTask<String> task = new WebAsyncTask<String>(5 * 1000L, () -> {
			System.out.println("The working Thread name is " + Thread.currentThread().getName());
			Thread.sleep(10 * 1000L);
			return "task executed！";
		});

		// 任务超时调用该方法
		task.onTimeout(() -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println("task timeout occured！");
			return "task timeout！";
		});

		// 任务执行完成时调用该方法
		task.onCompletion(() -> {
			System.out.println("task finished！");
		});

		// 可以继续执行其他操作
		System.out.println("task can do other things！");
		return task;
	}

	@RequestMapping(value = "case3", method = RequestMethod.GET)
	public WebAsyncTask<String> taskWithError() {
		System.out.println("The main Thread name is " + Thread.currentThread().getName());

		// 模拟开启一个异步任务
		WebAsyncTask<String> task = new WebAsyncTask<String>(5 * 1000L, () -> {
			System.out.println("The working Thread name is " + Thread.currentThread().getName());
			int num = 1 / 0;
			System.err.println(num);
			return "";
		});

		// 发生异常时调用该方法
		task.onError(() -> {
			System.err.println(Thread.currentThread().getName());
			System.err.println("task error occured！");
			return "";
		});
		
		// 任务执行完成时调用该方法
		task.onCompletion(() -> {
			System.out.println("task finished！");
		});

		// 可以继续执行其他操作
		System.out.println("task can do other things！");
		return task;
	}

	@RequestMapping(value = "case4", method = RequestMethod.GET)
	public WebAsyncTask<String> taskWithThreadPool() {
		System.err.println("The main Thread name is " + Thread.currentThread().getName());

		// 模拟开启一个异步任务
		WebAsyncTask<String> task1 = new WebAsyncTask<String>(10 * 1000L, executor, () -> {
			System.out.println("The working Thread name is " + Thread.currentThread().getName());
			Thread.sleep(5000L);
			return "task executed！";
		});

		// 任务执行完成时调用该方法
		task1.onCompletion(() -> {
			System.out.println("task finished！");
		});

		System.out.println("task can do other things！");
		return task1;
	}
}
