package com.springboot.acyclicrelationship.callback.healthrecord;


import java.util.*;

import com.springboot.acyclicrelationship.callback.handler.HealthLevelHandler;
import com.springboot.acyclicrelationship.callback.healthtask.HealthTask;

public class HealthRecord implements HealthLevelHandler {
	private List<HealthTask> tasks = new ArrayList<HealthTask>();

	@Override
	public Integer getHealthLevel() {
		if(tasks.size() > 5) {
			return 1;
		} 		
		if(tasks.size() < 2) {
			return 3;
		}		
		return 2;
	}
	
	public void addTask(String taskName, Integer initialHealthPoint) {
		HealthTask task = new HealthTask(taskName, initialHealthPoint, this);
		tasks.add(task);
	}	
	
	public List<HealthTask> getTasks() {
		return tasks;
	}
}
