package com.springboot.acyclicrelationship.transfer.healthrecord;



import java.util.*;

import com.springboot.acyclicrelationship.transfer.handler.HealthLevelHandler;
import com.springboot.acyclicrelationship.transfer.healthtask.HealthTask;


public class HealthRecord {
	private List<HealthTask> tasks = new ArrayList<HealthTask>();
	
	public void addTask(String taskName, Integer initialHealthPoint) {
		HealthTask task = new HealthTask(taskName, initialHealthPoint);
		tasks.add(task);
	}	

	public HealthLevelHandler getHealthPointHandler() {
		return new HealthLevelHandler(new Integer(tasks.size()));
	}	
	
	public List<HealthTask> getTasks() {
		return tasks;
	}
}
