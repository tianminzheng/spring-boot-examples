package com.springboot.acyclicrelationship.cycle.healthtask;

import com.springboot.acyclicrelationship.cycle.healthrecord.HealthRecord;

public class HealthTask {	
	private HealthRecord record;
	private String taskName;
	private Integer initialHealthPoint;

	public HealthTask(HealthRecord record, String taskName, Integer initialHealthPoint) {
		this.record = record;
		this.taskName = taskName;
		this.initialHealthPoint = initialHealthPoint;
	}

	public Integer calculateHealthPointForTask() {

		//计算该任务所能获取的健康积分需要健康等级信息
		//健康等级越低积分越高，以鼓励多做健康任务
		Integer healthPointFromHealthLevel = 12 / record.getHealthLevel();
		
		//最终健康积分为初始积分加上与健康等级相关的积分
		return initialHealthPoint + healthPointFromHealthLevel;
	}

	public String getTaskName() {
		return taskName;
	}
	
	public int getInitialHealthPoint() {
		return initialHealthPoint;
	}
}