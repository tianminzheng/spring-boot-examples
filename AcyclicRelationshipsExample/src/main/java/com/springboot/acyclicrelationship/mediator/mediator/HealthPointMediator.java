package com.springboot.acyclicrelationship.mediator.mediator;

import com.springboot.acyclicrelationship.mediator.healthrecord.HealthRecord;
import com.springboot.acyclicrelationship.mediator.healthtask.HealthTask;

public class HealthPointMediator {
	private HealthRecord record;

	public HealthPointMediator(HealthRecord record) {
		this.record = record;
	}

	public Integer calculateHealthPointForTask(HealthTask task) {
		Integer healthLevel = record.getHealthLevel();
		Integer initialHealthPoint = task.getInitialHealthPoint();		
		
		Integer healthPoint = 12 / healthLevel + initialHealthPoint;
		return healthPoint;				
	}	
}