package com.apps.blog.back.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.base.BaseService;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Category;
import com.apps.blog.back.dao.CategoryDao;
import com.apps.blog.back.service.CategoryService;

@Service("categoryServiceImpl")
public class CategoryServiceImpl<T> extends BaseService<T> implements CategoryService<Category> {

    @Autowired
    private CategoryDao<T> categoryDao;

    @Override
    public CategoryDao<T> getDao() {
        return categoryDao;
    }

    @Override
    @Transactional
    public void add(Category category) {
        if (!MyStringUtils.isNull(category.getName())) {
            categoryDao.add(category);
        }
    }

    @Override
    public List<Category> queryAll() {
        return categoryDao.queryAll();
    }

    @Override
    public Category queryById(Integer id) {
        if (null != id) {
            return categoryDao.queryById(id);
        }
        return null;
    }

    @Override
    @Transactional
    public void update(Category category) {
        if (null != category) {
            if (null != category.getId()) {
                categoryDao.update(category);
            }
        }
    }

    @Override
    public int hasCategory(Category category) {
        return categoryDao.hasCategory(category);
    }

}
