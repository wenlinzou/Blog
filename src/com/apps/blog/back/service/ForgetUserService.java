package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.ForgetUser;




public interface ForgetUserService<T> {

	public void add(String userId);
	public ForgetUser queryUserByUserId(String userId);
	public Integer queryCountByUserId(String userId);
	
	public List<ForgetUser> queryAll();
	public ForgetUser queryById(Integer id);
	public void update(ForgetUser user);
	
	public boolean addOrUpdate(String userId);
	
	public boolean checkUpdatePwd(String userId);
	
	public boolean judgeUpdateTimeOver(String userId);
	
	
}
