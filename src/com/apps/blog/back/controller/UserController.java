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
import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.service.impl.SaltImplService;
import com.apps.blog.back.service.impl.UserImplService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseAction {
	private final static Logger log = Logger.getLogger(UserController.class);

	// 接口中写自己的方法的时候用的
	@Autowired(required = false)
	private UserImplService<User> userService;
	@Autowired(required = false)
	private SaltImplService<Salt> saltService;
	
	
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
	 * @param model
	 * @return
	 * @throws Exception 
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
			if(null!=usernameTemp){
				String datapwd = usernameTemp.getPassword();
				
				//查找用户的salt
				Salt salt = saltService.querySalt(usernameTemp.getId());
				//md5(inpupwd+salt) == dataPwd
				String arrangePwd = MD5Utils.md5Salt(password, salt.getSalt());
				boolean canLogin = userService.login(arrangePwd, datapwd);
				
				if(canLogin){
					request.getSession(true).setAttribute("user", user);
					String redirctStr = "redirect:/user/queryAll.do";
					return redirctStr;
				}
			}
			/*user.setPassword(password);
			boolean canLogin = userService.login(user);
			if(canLogin){
				request.getSession(true).setAttribute("user", user);
				String redirctStr = "redirect:/user/queryAll.do";
				return redirctStr;
			}*/
		}
		return jumpJsp;
	}
	@RequestMapping("/loginOut")
	public void loginOut(HttpServletRequest request){
		//记录访问者的IP
		String userLogIP = request.getRemoteAddr();
		log.info("back-user loginout IP : " + userLogIP +" : " + IPUtils.getAddressByIP(userLogIP));
				
		request.getSession().setAttribute("user", null);
		request.getSession().removeAttribute("user");
	}
	@RequestMapping("/queryAll")
	public String queryAll(Model model){
		List<User> list = userService.queryAll();
		model.addAttribute("users", list);
		return "back/userBack";
	}
	
	@RequestMapping("/queryById")
	public String queryById(Integer id, Model model){
		if(null!=id){
			User user = userService.queryById(id);
			model.addAttribute("user", user);
		}
		return "back/usereditBack";
	}
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
}
