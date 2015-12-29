package com.apps.blog.back.dao;

import java.util.List;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.Article;


public interface ArticleDao<T> extends BaseDao<T>{
	public List<Article> queryAll();
	public Article queryById(Integer id);
	public void update(Article article);
	public void add(Article article);
}
