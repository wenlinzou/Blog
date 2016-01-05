package com.apps.blog.front.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.apps.blog.back.controller.ArticleController;

public class CountLineListener implements HttpSessionListener {
	private final static Logger log = Logger.getLogger(CountLineListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession(); 
        ServletContext application = session.getServletContext(); 
        
        // 在application范围由一个HashSet集保存所有的session 
        Set<HttpSession> sessions = (Set<HttpSession>) application.getAttribute("sessions"); 
        if (sessions == null) { 
               sessions = new HashSet<HttpSession>(); 
               application.setAttribute("sessions", sessions); 
        } 
        
        // 新创建的session均添加到HashSet集中 
        sessions.add(session); 
        // 可以在别处从application范围中取出sessions集合 
        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数” 
        log.info("当前人数:"+sessions.size());
System.out.println("当前人数:"+sessions.size());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession(); 
        ServletContext application = session.getServletContext(); 
        Set<HttpSession> sessions = (HashSet<HttpSession>) application.getAttribute("sessions"); 
        
        // 销毁的session均从HashSet集中移除 
        sessions.remove(session); 
        log.info("remove 当前人数:"+sessions.size());
System.out.println("remove 当前人数:"+sessions.size());
	}

}
