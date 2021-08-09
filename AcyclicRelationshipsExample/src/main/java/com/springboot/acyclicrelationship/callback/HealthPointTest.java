package com.springboot.acyclicrelationship.callback;


import java.util.List;

import com.springboot.acyclicrelationship.callback.healthrecord.HealthRecord;
import com.springboot.acyclicrelationship.callback.healthtask.HealthTask;


public class HealthPointTest {
	
	public static void main(String[] args) {
		HealthRecord record = new HealthRecord();
		record.addTask("忌烟酒", 5);
		record.addTask("一周慢跑三次", 4);	
		record.addTask("一天喝两升水", 2);
		record.addTask("坐1小时起来活动5分钟", 2);
		record.addTask("晚上10点按时睡觉", 3);
		record.addTask("晚上8点之后不再饮食", 1);

		List<HealthTask> tasks = record.getTasks();
		for(HealthTask task : tasks) {
			Integer healthPoint = task.calculateHealthPointForTask();
			System.out.println(healthPoint);
		}		
	}
}