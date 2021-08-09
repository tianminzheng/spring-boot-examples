package com.springboot.acyclicrelationship.callback.healthtask;

import com.springboot.acyclicrelationship.callback.handler.HealthLevelHandler;

public class HealthTask {	
	private String taskName;
	private Integer initialHealthPoint;
	private HealthLevelHandler handler;

	public HealthTask(String taskName, Integer initialHealthPoint, HealthLevelHandler handler) {
		this.taskName = taskName;
		this.initialHealthPoint = initialHealthPoint;
		this.handler = handler;
	}
	
	public Integer calculateHealthPointForTask() {		
		Integer healthPointFromHealthLevel = 12 / handler.getHealthLevel();		
		return initialHealthPoint + healthPointFromHealthLevel;
	}

	public String getTaskName() {
		return taskName;
	}
}