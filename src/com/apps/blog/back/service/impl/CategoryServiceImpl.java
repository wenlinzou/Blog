package com.apps.blog.back.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.dao.CategoryDao;
import com.apps.blog.back.service.CategoryService;

@Service("categoryServiceImpl")
public class CategoryServiceImpl<T> extends BaseService<T>  implements CategoryService<Category> {
	
	private final static Logger log = Logger.getLogger(CategoryServiceImpl.class);
	@Autowired
    private CategoryDao<T> categoryDao;
	
	@Override
	public CategoryDao<T> getDao() {
		return categoryDao;
	}
	
	
	@Override
	public void add(Category category) {
		if(!MyStringUtils.isNull(category.getName())){
			categoryDao.add(category);
		}
	}

	@Override
	public List<Category> queryAll() {
		return categoryDao.queryAll();
	}

	@Override
	public Category queryById(Integer id) {
		if(null!=id){
			return categoryDao.queryById(id);
		}
		return null;
	}

	@Override
	public void update(Category category) {
		if(null!=category){
			if(null!=category.getId()){
				categoryDao.update(category);
			}
		}
	}

	

}
