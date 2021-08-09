package com.springboot.acyclicrelationship.mediator.healthrecord;

import java.util.*;

import com.springboot.acyclicrelationship.mediator.healthtask.HealthTask;

public class HealthRecord {
	private List<HealthTask> tasks = new ArrayList<HealthTask>();
	
	public Integer getHealthLevel() {
		
		//根据健康任务数量来判断健康等级
		//任务越多说明越不健康，健康等级就越低
		if(tasks.size() > 5) {
			return 1;
		} 		
		if(tasks.size() < 2) {
			return 3;
		}		
		return 2;
	}

	public void addTask(String taskName, Integer initialHealthPoint) {
		HealthTask task = new HealthTask(taskName, initialHealthPoint);
		tasks.add(task);
	}
	
	public List<HealthTask> getTasks() {
		return tasks;
	}
}
