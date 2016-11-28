package com.apps.blog.back.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.base.BaseService;
import com.apps.base.utils.MD5Utils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.ForgetUser;
import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.dao.ForgetUserDao;
import com.apps.blog.back.dao.SaltDao;
import com.apps.blog.back.dao.UserDao;
import com.apps.blog.back.service.UserService;


@Service("userServiceImpl")
public class UserServiceImpl<T> extends BaseService<T> implements UserService<User> {

	@Autowired
    private UserDao<T> userDao;
	
	@Autowired
	private SaltDao<T> saltDao;
	
	@Autowired
	private ForgetUserDao<T> forgetUserDao;
	
	@Override
	public UserDao<T> getDao() {
		return userDao;
	}
	
	
	
	@Override
	@Transactional
	public User add(User user){
		if(null != user){
			String password = user.getPassword();
			String username = user.getUsername();
			if(null!=password && !"".equals(password)){
				user.setPassword(password);
				if(null!=username && !"".equals(username)){
					userDao.add(user);
					return user;
				}
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public User add(User user, Salt salt) {
		if(null != user){
			User u = add(user);
			salt.setUserid(u.getId());
			saltDao.add(salt);
		}
		return null;
	}
	
	@Override
	@Transactional
	public void update(User user, Salt salt) {
		if(null != user){
			update(user);
			saltDao.update(salt);
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
	@Transactional
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


	@Override
	@Transactional
	public void updatePwd(String userId, String password) {
		User user = new User();
		user.setId(Integer.parseInt(userId));
	
		//将MD5+salt的MD5值放入password中,生成一个新的salt
		String saltUpdateStr = MD5Utils.salt();
		password = MD5Utils.md5Salt(password, saltUpdateStr);
		
		//update user password
		user.setPassword(password);
		update(user);
		//update salt salt by userid
		Salt salt = new Salt();
		salt.setSalt(saltUpdateStr);
		salt.setUserid(Integer.parseInt(userId));
		saltDao.update(salt);
		
		//update forgetuser updatetime
		ForgetUser fUser = new ForgetUser();
		fUser.setUserId(userId);
		fUser.setUpdateTime(new Date());
		forgetUserDao.update(fUser);
	}



	

}
