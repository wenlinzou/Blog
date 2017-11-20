package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.bean.User;




public interface UserService<T> {

	public User add(User user);
	public boolean login(String inputpwd, String datapwd);
	public User queryUser(User user);
	public User queryUserByName(User user);
	
	public List<User> queryAll();
	public User queryById(String id);
	public void update(User user);
	
	public void updatePwd(String userId, String password);
	
	public User add(User user, Salt salt);//save user and salt
	public void update(User user, Salt salt);//update user and salt
}
