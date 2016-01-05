package com.apps.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;


public class BaseAction{
	
	public final static String SUCCESS ="success";  
	
	public final static String MSG ="msg";  
	
	
	public final static String DATA ="data";  
	
	public final static String LOGOUT_FLAG = "logoutFlag";  
	
	
   @InitBinder  
   protected void initBinder(WebDataBinder binder) {  
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
		 binder.registerCustomEditor(int.class,new MyEditor()); 
   }  
	 
	 /**
	  * 获取IP地址
	  * @param request
	  * @return
	  */
	 public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	 
	 /**
	  * 所有ActionMap 统一从这里获取
	  * @return
	  */
	public Map<String,Object> getRootMap(){
		Map<String,Object> rootMap = new HashMap<String, Object>();
		//添加url到 Map中
		//rootMap.putAll(URLUtils.getUrlMap());
		return rootMap;
	}
	
	public ModelAndView forword(String viewName,Map context){
		return new ModelAndView(viewName,context); 
	}
	
	public ModelAndView error(String errMsg){
		return new ModelAndView("error"); 
	}
	
	/**
	 *
	 * 提示成功信息
	 *
	 * @param message
	 *
	 */
	/*public void sendSuccessMessage(HttpServletResponse response,  String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, true);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}*/

	/**
	 *
	 * 提示失败信息
	 *
	 * @param message
	 *
	 */
	/*public void sendFailureMessage(HttpServletResponse response,String message) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(SUCCESS, false);
		result.put(MSG, message);
		HtmlUtil.writerJson(response, result);
	}*/
	
	/**
	 * 获取当前年月日时分秒。
	 * @return
	 * 
	 * @author 吴恒
	 */
	public String getNowDate(){
		return Calendar.getInstance().get(Calendar.YEAR) + "年" 
			+ (Calendar.getInstance().get(Calendar.MONTH) + 1) + "月" 
			+  Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日"
			+  Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "时"
			+  Calendar.getInstance().get(Calendar.MINUTE) + "分"
			+  Calendar.getInstance().get(Calendar.SECOND) + "秒";
	}
	
	/**
	 * 判断客户端是否有教师或者学生的用户信息。<br/>
	 * true表示有用户信息。
	 * 
	 * @param request
	 * @return
	 * 
	 * @author 吴恒
	 */
	public boolean isSession(HttpServletRequest request) {
		if (null == request.getSession(true).getAttribute("student_user") && null == request.getSession(true).getAttribute("teacher_user")) {
			return false;
		}
		return true;
	}
}
