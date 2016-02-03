package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.Comment;
import com.apps.blog.back.pager.CommentPage;




public interface CommentService<T> {
	public List<Comment> queryAll();
	public Comment queryById(Integer id);
	public void update(Comment comment);
	public void add(Comment comment);
	
	public Comment queryCommentByName(Comment comment);
	public List<Comment> queryListByArticle(Comment comment);
	
	public List<Comment> queryAllSortDate();
	public List<Comment> queryByThing(Comment comment);
	public void updateClick(Integer inputid);
	
	public List<Comment> queryListByPage(CommentPage page);
	public Integer queryCountByPage(CommentPage page);
}
