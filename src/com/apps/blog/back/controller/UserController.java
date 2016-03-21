package com.apps.blog.back.controller;


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
import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.bean.User;
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
				userService.add(user);
				
				//获取userid
				User loginuser = userService.queryUser(user);
				Salt salt = new Salt();
				salt.setSalt(saltStr);
				salt.setUserid(loginuser.getId());
				saltService.add(salt);
				
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
	public String queryById(Integer id, Model model){
		if(null!=id){
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
	public String update(HttpServletRequest request, String username, String nickname, Integer id, Model model){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user update IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
		
		if(null!=id){
			User user = new User();
			if(!MyStringUtils.isNull(username) && null!=id){
				user.setId(id);
				user.setUsername(username);
				user.setNickname(nickname);
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
	public Map<String,String> updatePwd(HttpServletRequest request, String password,Integer id, Model model){
		Map<String, String> map = new HashMap<String, String>();
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user update passwod IP : " + userLogIP + " : " + IPUtils.getAddressByIP(userLogIP));
		
		map.put("message", "修改失败!");
		if(null!=id){
			User user = new User();
			if(!MyStringUtils.isNull(password) && null!=id){
				user.setId(id);
				//将MD5+salt的MD5值放入password中,生成一个新的salt
				String saltUpdateStr = MD5Utils.salt();
				password = MD5Utils.md5Salt(password, saltUpdateStr);
				
				//update user password
				user.setPassword(password);
				userService.update(user);
				//update salt salt by userid
				Salt salt = new Salt();
				salt.setSalt(saltUpdateStr);
				salt.setUserid(id);
				saltService.update(salt);
				
				map.put("message", "修改成功!");
			}
		}
		return map;
	}
}
