package com.apps.base.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.DateUtils;

import com.apps.base.task.SendEmailTask;

public class TaskManager implements ServletContextListener {
	
	
	//每天的毫秒
	@SuppressWarnings("deprecation")
	public static final long PERIOD_DAY = DateUtils.MILLIS_IN_DAY;
	
	
	
	//增加天数
	private Date addDay(Date date, int num) {
		Calendar currentDay = Calendar.getInstance();
		currentDay.setTime(date);
		currentDay.add(Calendar.DAY_OF_MONTH, num);
		return currentDay.getTime();
	}
  
	//一周的毫秒
	public static final long PERIOD_WEEK = PERIOD_DAY * 7;
	
	//无延迟
	public static final long NO_DELAY = 0;
	
	//定时器
	private Timer timer;
	
	//在Web应用启动时初始化任务 
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		//定义定时器
		timer = new Timer("定时器开始", true);
		//------------启动备份任务，每月（4星期执行一次）---------
//		timer.schedule(new SendEmailTask(), NO_DELAY, 10000);//十秒执行一次
		
		//------------每天2:00执行方法----------17:01
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 0);
		
		//第一次执行任务的时间
		Date date = calendar.getTime();
		//如果第一次执行定时任务的时间需要小于当前时间
		//此时要在第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。
		//如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
		
		if(date.before(new Date())){
			date = this.addDay(date, 1);
		}
		timer.schedule(new SendEmailTask(), date, PERIOD_DAY);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//定时器销毁
		timer.cancel();
	}

}
