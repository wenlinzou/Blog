package com.apps.blog.back.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Article;
import com.apps.blog.back.dao.ArticleDao;
import com.apps.blog.back.pager.ArticlePage;
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

	@Override
	public void add(Article article) {
		if(null!=article){
			articleDao.add(article);
		}
	}

	@Override
	public List<Article> queryAllSortDate() {
		return articleDao.queryAllSortDate();
	}

	@Override
	public List<Article> queryByThing(Article article) {
		if(null!=article){
			return articleDao.queryByThing(article);
		}
		return null;
	}

	@Override
	public void updateClick(Integer inputid) {
		if(null!=inputid)
			articleDao.updateClick(inputid);
		
	}

	@Override
	public List<Article> queryListByPage(ArticlePage page) {
		if(page!=null){
			Integer totalCount = queryCountByPage(page);
			page.getPager().setRowCount(totalCount);
			List<Article> lists = articleDao.queryListByPage(page);
			
			return lists;
		}else{
			return null;
		}
	}

	@Override
	public Integer queryCountByPage(ArticlePage page) {
		return articleDao.queryCountByPage(page);
	}
	
	
	public List<String> getAllDate(List<Article> articleList){
		List<Date> dates = new ArrayList<Date>(); 
		for (int i = 0; i < articleList.size(); i++) {
			Article a = articleList.get(i);
			dates.add(a.getPdate());
		}
		List<String> dateList = MyStringUtils.queryAllDiffMonth(dates);
		return dateList;
	}

}
