package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.User;




public interface UserService<T> {

	public void add(User user);
	public boolean login(String inputpwd, String datapwd);
	public User queryUser(User user);
	public User queryUserByName(User user);
	
	public List<User> queryAll();
	public User queryById(Integer id);
	public void update(User user);
}
