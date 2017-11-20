package com.apps.blog.back.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apps.base.BaseAction;
import com.apps.base.utils.IPUtils;
import com.apps.base.utils.MD5Utils;
import com.apps.base.utils.MyStringUtils;
import com.apps.base.utils.OneUserUtils;
import com.apps.base.utils.PropertiesUtils;
import com.apps.base.utils.SendMailUtils;
import com.apps.blog.back.bean.ForgetUser;
import com.apps.blog.back.bean.SEmail;
import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.service.ForgetUserService;
import com.apps.blog.back.service.SaltService;
import com.apps.blog.back.service.UserService;
/**
 * 用户操作类
 * @author Pet
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseAction {
	private final static Logger log = Logger.getLogger(UserController.class);

	@Autowired(required = false)
	private UserService<User> userService;
	
	@Autowired(required = false)
	private SaltService<Salt> saltService;
	
	@Autowired(required = false)
	private ForgetUserService<ForgetUser> forgetUserService;
	
	/**
	 * 检查用户登录名称是否已存在ajax
	 * @param username 用户名
	 * @param password 密码
	 * @param nickname 昵称
	 * @return 用户是否可保存
	 */
	@RequestMapping("checkName")
	@ResponseBody
	public Map<String, Object> checkName(String username, String password, String nickname){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean usernameisnull = MyStringUtils.isNull(username);
		if(!usernameisnull){
			User user = new User();
			user.setUsername(username);
			User usernameTemp = userService.queryUserByName(user);
			if(null==usernameTemp){
				map.put("canSave", true);
				map.put("jumpUrl", "user/save.do");
			} else {
				map.put("canSave", false);
				map.put("msg", "existed");
			}
		}
		return map;
	}
	
	/**
	 * 跳转到数据展示及操作页面，添加列表数据到页面。
	 * @param request
	 * @param username 用户名
	 * @param password 密码
	 * @param nickname 昵称
	 * @return 跳转至查询所有用户方法
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request, String username, String password, String nickname){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user add IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		boolean usernameisnull = MyStringUtils.isNull(username);
		boolean passwordisnull = MyStringUtils.isNull(password);
		boolean nickanamenull = MyStringUtils.isNull(nickname);
		if(!usernameisnull && ! passwordisnull){
			User user = new User();
			user.setUsername(username);
			User usernameTemp = userService.queryUserByName(user);
			if(null==usernameTemp){
				//将MD5+salt的MD5值放入password中
				String saltStr = MD5Utils.salt();
				password = MD5Utils.md5Salt(password, saltStr);
				user.setPassword(password);
				if(!nickanamenull)
					user.setNickname(nickname);
				/*userService.add(user);
				
				//获取userid
				User loginuser = userService.queryUser(user);
				Salt salt = new Salt();
				salt.setSalt(saltStr);
				salt.setUserid(loginuser.getId());
				saltService.add(salt);*/
				Salt salt = new Salt();
				userService.add(user, salt);
				
			}
		}
		String redirctStr = "redirect:/user/queryAll.do";
		return redirctStr;
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录是否成功（当前页面or成功页面）jsp
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, String username, String password){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user login IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		boolean usernameisnull = MyStringUtils.isNull(username);
		boolean passwordisnull = MyStringUtils.isNull(password);
		String jumpJsp = "back/indexLogin";
		if(!usernameisnull && ! passwordisnull){
			User user = new User();
			user.setUsername(username);
			User usernameTemp = userService.queryUserByName(user);
			if(null != usernameTemp){
				String datapwd = usernameTemp.getPassword();
				
				//查找用户的salt
				Salt salt = saltService.querySalt(usernameTemp.getId());
				//md5(inpupwd+salt) == dataPwd
				String arrangePwd = MD5Utils.md5Salt(password, salt.getSalt());
				boolean canLogin = userService.login(arrangePwd, datapwd);
				
				if(canLogin){
					//在用户登录时，把用户添加到一个ArrayList中
					//再次登录时查看ArrayList中有没有该用户，如果ArrayList中已经存在该用户，则阻止其登录
					boolean hasUser = OneUserUtils.hasOldUser(usernameTemp);
					if(!hasUser){
						request.getSession(true).setAttribute("user", usernameTemp);
						String redirctStr = "redirect:/user/queryAll.do";
						return redirctStr;
					}else{
						return jumpJsp;
					}
				}
			}
		}
		return jumpJsp;
	}
	/**
	 * 忘记密码-给该用户发送邮件：用户点击邮件，跳转到密码修改页面，输入新密码，然后将新密码输入
	 * 增加一个超时操作，点击忘记密码插入当前时间戳，用户点击邮件链接跳转到密码修改页面，记录进入跳转修改页面时间戳
	 * 两者时间增加限制
	 * @param request
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录是否成功（当前页面or成功页面）jsp
	 */
	@RequestMapping("/forgetPwd")
	@ResponseBody
	public Map<String, Object> forgetPwd(HttpServletRequest request, String username){
		Map<String, Object> map = new HashMap<String, Object>();
		
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user login IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		
		boolean usernameisnull = MyStringUtils.isNull(username);
		if(usernameisnull){
			map.put("message", "用户名不能为空！");
		}else{
			User tempUser = new User();
			tempUser.setUsername(username);
			User user = userService.queryUserByName(tempUser);
			if(null!=user){
				String email = user.getEmail();
				
				
				boolean canUpdatePwd = forgetUserService.checkUpdatePwd(user.getId().toString());
				if(canUpdatePwd){
					//将用户的id
					
					//给该用户的邮箱发邮件
					String sendToEmailName = email;
					
					SEmail semail = new SEmail();
					semail.setUsername(PropertiesUtils.getValueByName("constant_value.properties", "username"));
					semail.setPassword(PropertiesUtils.getValueByName("constant_value.properties", "password"));
					semail.setHostname(MyStringUtils.arrangeEmailHost(PropertiesUtils.getValueByName("constant_value.properties", "username")));//发送邮件host
					semail.setSendFromEmailName(PropertiesUtils.getValueByName("constant_value.properties", "username"));
					semail.setSendToEmailName(sendToEmailName);
					semail.setSubject(PropertiesUtils.getValueByName("constant_value.properties", "subject"));//标题
		
					//触发网址的修改密码页面 http://localhost:8080/WebApp/
					String path = request.getContextPath(); //WebApp
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					
					
					semail.setContent(basePath + "user/userUpdatePwd.shtml?userId="+user.getId()+"&email="+user.getEmail());//邮件内容
					SendMailUtils.sendEMail(semail);
					
					
					//保存修改密码用户信息
					forgetUserService.addOrUpdate(user.getId().toString());
					
					map.put("message", "已发送邮件至您的邮箱！注意查收");
				}else{
					map.put("message", "超过每日修改密码最高次数！");
				}
			}else{
				map.put("message", "登录用户不存在！");
			}
		}
		return map;
	}
	/**
	 * 用户修改密码页面
	 * @param request
	 * @return 修改密码jsp
	 * @throws IOException 
	 */
	@RequestMapping("/userUpdatePwd")
	public String userUpdatePwdJsp(Model model, HttpServletRequest request, String userId, String email) throws IOException{
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user loginout IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		User user = userService.queryById(userId);
		//链接的email地址为该用户的地址则跳转至密码修改页面
		if(user.getEmail().equals(email)){
			model.addAttribute("user", user);
			return "back/userupdatePwd";
		}else{
			//跳转至外网
			String url = PropertiesUtils.getValueByName("constant_value.properties", "JUMP_WEB_URL");
			return "redirect:"+url;
		}
	}
	/**
	 * 修改用户密码ajax
	 * @param request
	 * @param password 用户密码
	 * @param id 用户id
	 * @param model
	 * @return 修改成功失败
	 */
	@RequestMapping("/canUpdatePwd")
	@ResponseBody
	public Map<String, Object> canUpdatePwd(HttpServletRequest request, String password, String userid, Model model){
		
		Map<String, Object> map = new HashMap<String, Object>();
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user update passwod IP : " + userLogIP + " : " + IPUtils.getAddressByIP(userLogIP));
		
		boolean timeOK = forgetUserService.judgeUpdateTimeOver(userid);
		if(timeOK){
			//修改密码
			userService.updatePwd(userid, password);
			map.put("message", "修改成功!");
			map.put("url", "view/back/indexLogin.jsp");
			map.put("result", true);
		}else{
			//链接超时
			map.put("message", "修改失败!链接超时。。。");
			map.put("result", false);
		}
		
		return map;
	}
	
	/**
	 * 用户退出，删除session中的用户信息
	 * @param request
	 * @return 前台首页jsp
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user loginout IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		User logoutUser = (User) request.getSession().getAttribute("user");	
		if(null != logoutUser) OneUserUtils.removeLogUser(logoutUser);
		
		request.getSession().setAttribute("user", null);
		request.getSession().removeAttribute("user");
		return "redirect:/articleFront/queryAllArticlePage.shtml";
	}
	
	/**
	 * 查询所有用户信息
	 * @param model
	 * @return 所有用户信息jsp
	 */
	@RequestMapping("/queryAll")
	public String queryAll(Model model){
		List<User> list = userService.queryAll();
		model.addAttribute("users", list);
		return "back/userBack";
	}
	
	/**
	 * 根据用户id查询用户对象
	 * @param id 用户id
	 * @param model
	 * @return 用户当前jsp
	 */
	@RequestMapping("/queryById")
	public String queryById(String id, Model model){
		if(!MyStringUtils.isNull(id)){
			User user = userService.queryById(id);
			model.addAttribute("user", user);
		}
		return "back/usereditBack";
	}
	
	/**
	 * 修改用户对象
	 * @param request
	 * @param username 用户名
	 * @param nickname 昵称
	 * @param id 用户id
	 * @param model
	 * @return 用户当前jsp
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request, String username, String nickname, String id, String email, Model model){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user update IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if(!MyStringUtils.isNull(id)){
			User user = new User();
			if(!MyStringUtils.isNull(username) && null!=id){
				user.setId(id);
				user.setUsername(username);
				user.setNickname(nickname);
				user.setEmail(email);
				userService.update(user);
				String redirctStr = "redirect:/user/queryAll.do";
				return redirctStr;
			}
		}
		return "back/userBack";
	}
	
	/**
	 * 修改用户密码ajax
	 * @param request
	 * @param password 用户密码
	 * @param id 用户id
	 * @param model
	 * @return 修改成功失败
	 */
	@RequestMapping("/updatePwd")
	@ResponseBody
	public Map<String,String> updatePwd(HttpServletRequest request, String password, String id, Model model){
		Map<String, String> map = new HashMap<String, String>();
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user update passwod IP : " + userLogIP + " : " + IPUtils.getAddressByIP(userLogIP));
		
		map.put("message", "修改失败!");
		if(!MyStringUtils.isNull(id)){
			User user = new User();
			if(!MyStringUtils.isNull(password) && null!=id){
				user.setId(id);
				//将MD5+salt的MD5值放入password中,生成一个新的salt
				String saltUpdateStr = MD5Utils.salt();
				password = MD5Utils.md5Salt(password, saltUpdateStr);
				
				//update user password
				/*user.setPassword(password);
				userService.update(user);
				//update salt salt by userid
				Salt salt = new Salt();
				salt.setSalt(saltUpdateStr);
				salt.setUserid(id);
				saltService.update(salt);*/
				
				user.setPassword(password);
				Salt salt = new Salt();
				salt.setSalt(saltUpdateStr);
				salt.setUserid(id);
				userService.update(user, salt);
				
				
				map.put("message", "修改成功!");
			}
		}
		return map;
	}
	
}
