package com.apps.blog.back.dao;

import java.util.List;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.Category;

public interface CategoryDao<T> extends BaseDao<T> {
	public void add(Category category);
	
	public List<Category> queryAll();
	
	public Category queryById(Integer id);
	public void update(Category category);
}
