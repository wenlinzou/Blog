package com.apps.blog.back.dao;

import java.util.List;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.Share;

public interface ShareDao<T> extends BaseDao<T> {
	public List<Share> queryAll();

	public Share queryById(Integer id);
	
	public Share queryByArticleId(Integer id);

	public void update(Share share);

	public void add(Share share);

}
