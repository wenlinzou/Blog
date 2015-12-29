package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.Article;




public interface ArticleService<T> {
	public List<Article> queryAll();
	public Article queryById(Integer id);
	public void update(Article article);
}
