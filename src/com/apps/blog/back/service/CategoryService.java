package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.Category;

public interface CategoryService<T> {
public void add(Category category);
	
	public List<Category> queryAll();
	
	public Category queryById(Integer id);
	public void update(Category category);
	
	public int hasCategory(Category category);
}
