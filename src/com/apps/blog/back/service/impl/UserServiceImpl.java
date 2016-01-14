package com.apps.blog.back.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.dao.UserDao;
import com.apps.blog.back.service.UserService;


@Service("userServiceImpl")
public class UserServiceImpl<T> extends BaseService<T> implements UserService<User> {

	private final static Logger log = Logger.getLogger(UserServiceImpl.class);
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
				user.setPassword(password);
				if(null!=username && !"".equals(username)){
					userDao.add(user);
				}
			}
		}
	}
	@Override
	public boolean login(String inputpwd, String datapwd){
		boolean canLogin = MyStringUtils.slowEquals(inputpwd.getBytes(), datapwd.getBytes());
		return canLogin;
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


	@Override
	public User queryUser(User user) {
		return userDao.queryUser(user);
	}


	@Override
	public User queryUserByName(User user) {
		return userDao.queryUserByName(user);
	}

}
