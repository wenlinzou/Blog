package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.Article;
import com.apps.blog.back.pager.ArticlePage;




public interface ArticleService<T> {
	public List<Article> queryAll();
	public List<Article> queryAllComment();
	
	public Article queryById(Integer id);
	public void update(Article article);
	public void add(Article article);
	
	
	public List<Article> queryAllSortDate();
	public List<Article> queryByThing(Article article);
	public void updateClick(Integer inputid);
	
	public List<Article> queryListByPage(ArticlePage page);
	public Integer queryCountByPage(ArticlePage page);
}
