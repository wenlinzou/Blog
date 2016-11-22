package com.apps.blog.back.dao;

import java.util.List;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.ForgetUser;


public interface ForgetUserDao<T> extends BaseDao<T>{
	public void add(ForgetUser user);
	public ForgetUser queryUserByUserId(String userId);
	public Integer queryCountByUserId(String userId);
	public ForgetUser queryUser(ForgetUser user);
	
	public List<ForgetUser> queryAll();
	
	public ForgetUser queryById(Integer id);
	public void update(ForgetUser user);
}
