package com.apps.blog.back.dao;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.Salt;


public interface SaltDao<T> extends BaseDao<T>{
	public void add(Salt salt);
	public Salt querySalt(String id);
	public void update(Salt salt);
}
