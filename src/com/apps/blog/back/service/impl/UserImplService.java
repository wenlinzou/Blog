package com.apps.blog.back.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.base.utils.MD5Utils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.dao.UserDao;
import com.apps.blog.back.service.UserService;


@Service("userImplService")
public class UserImplService<T> extends BaseService<T> implements UserService<User> {

	private final static Logger log = Logger.getLogger(UserImplService.class);
	@Autowired
    private UserDao<T> userDao;
	
	@Override
	public UserDao<T> getDao() {
		return userDao;
	}
	
	@Override
	public void add(User user){
		if(null != user){
			String password = user.getPassword();
			String username = user.getUsername();
			if(null!=password && !"".equals(password)){
				user.setPassword(MD5Utils.md5(password));
				if(null!=username && !"".equals(username)){
					userDao.add(user);
				}
			}
		}
	}
	@Override
	public boolean login(User user){
		boolean isLogin = false;
		if(null != user){
			String password = user.getPassword();
			String username = user.getUsername();
			if(!MyStringUtils.isNull(username) && !MyStringUtils.isNull(password)){
				String md5str = MD5Utils.md5(password);
				user.setPassword(md5str);
				int loginOk = userDao.login(user);
				if(loginOk>0)
					isLogin=true;
			}
		}
		return isLogin;
	}

	@Override
	public List<User> queryAll() {
		
		return userDao.queryAll();
	}

	@Override
	public User queryById(Integer id) {
		if(null!=id){
			return userDao.queryById(id);
		}
		return null;
	}

	@Override
	public void update(User user) {
		if(null!=user){
			userDao.update(user);
		}
	}

}
