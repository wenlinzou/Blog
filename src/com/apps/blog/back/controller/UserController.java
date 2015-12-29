package com.apps.blog.back.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apps.base.BaseAction;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.service.impl.UserImplService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseAction {
	private final static Logger log = Logger.getLogger(UserController.class);

	// 接口中写自己的方法的时候用的
	@Autowired(required = false)
	private UserImplService<User> userService;
	
	/**
	 * 跳转到数据展示及操作页面，添加列表数据到页面。
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public String save(String username, String password, String nickname){
		boolean usernameisnull = MyStringUtils.isNull(username);
		boolean passwordisnull = MyStringUtils.isNull(password);
		boolean nickanamenull = MyStringUtils.isNull(nickname);
		if(!usernameisnull && ! passwordisnull){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			if(!nickanamenull)
				user.setNickname(nickname);
			userService.add(user);
		}
		String redirctStr = "redirect:/user/list.do";
		return redirctStr;
	}
	@RequestMapping("/login")
	public String login(String username, String password){
		boolean usernameisnull = MyStringUtils.isNull(username);
		boolean passwordisnull = MyStringUtils.isNull(password);
		String jumpJsp = "back/indexLogin";
		if(!usernameisnull && ! passwordisnull){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			boolean canLogin = userService.login(user);
			if(canLogin){
				String redirctStr = "redirect:/user/queryAll.do";
				return redirctStr;
			}
		}
		return jumpJsp;
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
	public String update(String username, String nickname, Integer id, Model model){
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
