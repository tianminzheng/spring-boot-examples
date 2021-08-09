package com.springboot.acyclicrelationship.transfer.healthtask;

import com.springboot.acyclicrelationship.transfer.handler.HealthLevelHandler;

public class HealthTask {	
	private String taskName;
	private Integer initialHealthPoint;

	public HealthTask(String taskName, Integer initialHealthPoint) {
		this.taskName = taskName;
		this.initialHealthPoint = initialHealthPoint;
	}
	
	public Integer calculateHealthPointForTask(HealthLevelHandler handler) {		
		Integer healthPointFromHealthLevel = 12 / handler.getHealthLevel();		
		return initialHealthPoint + healthPointFromHealthLevel;
	}

	public String getTaskName() {
		return taskName;
	}
}