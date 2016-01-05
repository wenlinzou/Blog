package com.apps.blog.back.dao;

import java.util.List;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.User;


public interface UserDao<T> extends BaseDao<T>{
	public void add(User user);
//	public int login(User user);
	public User queryUserByName(User user);
	public User queryUser(User user);
	
	public List<User> queryAll();
	
	public User queryById(Integer id);
	public void update(User user);
}
