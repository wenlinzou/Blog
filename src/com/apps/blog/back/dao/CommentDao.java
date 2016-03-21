package com.apps.blog.back.dao;

import java.util.List;

import com.apps.base.BaseDao;
import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.pager.CommentPage;


public interface CommentDao<T> extends BaseDao<T>{
	public List<Comment> queryAll();
	public Comment queryById(Integer id);
	public void update(Comment comment);
	public void add(Comment comment);
	
	public Comment queryCommentByName(Comment comment);
	public List<Comment> queryListByArticle(Comment comment);
	
	
	
	public List<Comment> queryAllSortDate();
	
	public List<Comment> queryByThing(Comment article);
	
	public void updateClick(Integer inputid);
	
	public List<Comment> queryListByPage(CommentPage page);
	
	public Integer queryCountByPage(CommentPage page);
}
