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
import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.dao.CommentDao;
import com.apps.blog.back.pager.ArticlePage;
import com.apps.blog.back.pager.CommentPage;
import com.apps.blog.back.service.CommentService;


@Service("commentServiceImpl")
public class CommentServiceImpl<T> extends BaseService<T> implements CommentService<T> {

	private final static Logger log = Logger.getLogger(CommentServiceImpl.class);
	@Autowired
    private CommentDao<T> commentDao;
	
	@Override
	public CommentDao<T> getDao() {
		return commentDao;
	}

	@Override
	public List<Comment> queryAll() {
		return commentDao.queryAll();
	}

	@Override
	public Comment queryById(Integer id) {
		if(null!=id){
			return commentDao.queryById(id);
		}
		return null;
	}

	@Override
	public void update(Comment comment) {
		if(null!=comment){
			commentDao.update(comment);
		}
	}

	@Override
	public void add(Comment c) {
		if(null != c && null!=c.getArticleid() && null!=c.getComment() && null!=c.getVisitname()){
			commentDao.add(c);
		}
	}

	public Comment queryCommentByName(Comment c){
		return commentDao.queryCommentByName(c);
	}
	
	@Override
	public List<Comment> queryListByArticle(Comment comment) {
		return commentDao.queryListByArticle(comment);
	}
	
	@Override
	public List<Comment> queryAllSortDate() {
		return commentDao.queryAllSortDate();
	}

	@Override
	public List<Comment> queryByThing(Comment comment) {
		if(null!=comment){
			return commentDao.queryByThing(comment);
		}
		return null;
	}

	@Override
	public void updateClick(Integer inputid) {
		if(null!=inputid)
			commentDao.updateClick(inputid);
		
	}

	@Override
	public List<Comment> queryListByPage(CommentPage page) {
		if(page!=null){
			Integer totalCount = queryCountByPage(page);
			page.getPager().setRowCount(totalCount);
			List<Comment> lists = commentDao.queryListByPage(page);
			
			return lists;
		}else{
			return null;
		}
	}

	@Override
	public Integer queryCountByPage(CommentPage page) {
		return commentDao.queryCountByPage(page);
	}
	
	

	

}
