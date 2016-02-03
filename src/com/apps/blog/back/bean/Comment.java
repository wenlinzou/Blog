package com.apps.blog.back.bean;

import java.util.Date;
/**
 * 文章评论
 * @author Pet
 *
 */
public class Comment {
	private Integer id;
	private Integer articleid;
	private String comment;
	private Date date;
	private String visitname;
	private String email;
	private Integer isshow;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getVisitname() {
		return visitname;
	}
	public void setVisitname(String visitname) {
		this.visitname = visitname;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIsshow() {
		return isshow;
	}
	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	
	
}
