package com.springboot.acyclicrelationship.mediator.healthtask;




public class HealthTask {	
	private String taskName;
	private Integer initialHealthPoint;

	public HealthTask(String taskName, Integer initialHealthPoint) {
		this.taskName = taskName;
		this.initialHealthPoint = initialHealthPoint;
	}

	public String getTaskName() {
		return taskName;
	}

	public Integer getInitialHealthPoint() {
		return initialHealthPoint;
	}
}