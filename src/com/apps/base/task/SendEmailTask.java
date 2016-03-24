package com.apps.base.task;

import java.util.TimerTask;

import org.apache.log4j.Logger;



public class SendEmailTask extends TimerTask {
	private final static Logger log = Logger.getLogger(SendEmailTask.class);
	private static boolean isRunning = false;

	@Override
	public void run() {
		if(!isRunning){
			isRunning = true;
			log.info("开始执行任务");
			
			log.info("任务执行完成");
			isRunning = false;
		}else{
			log.info("上个任务执行尚未结束……");
		}

	}

}
