package com.apps.blog.back.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.dao.ArticleDao;
import com.apps.blog.back.service.ArticleService;


@Service("articleImplService")
public class ArticleImplService<T> extends BaseService<T> implements ArticleService<T> {

	private final static Logger log = Logger.getLogger(ArticleImplService.class);
	@Autowired
    private ArticleDao<T> articleDao;
	
	@Override
	public ArticleDao<T> getDao() {
		return articleDao;
	}

	@Override
	public List<Article> queryAll() {
		return articleDao.queryAll();
	}

	@Override
	public Article queryById(Integer id) {
		if(null!=id){
			return articleDao.queryById(id);
		}
		return null;
	}

	@Override
	public void update(Article article) {
		if(null!=article){
			articleDao.update(article);
		}
	}


}
