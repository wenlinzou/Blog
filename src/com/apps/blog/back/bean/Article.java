package com.apps.blog.back.bean;

import java.util.Date;

/**
 * 文章
 * 
 * @author Pet
 * 
 */
public class Article {
	private Integer id;
	private Integer pid;
	private Integer rootid;
	private String title;
	private String cont;
	private Date pdate;
	private Integer isleaf;

	private Integer click;

	private String img;

	private String shortmon;

	private Integer comments;

	private String keyword;

	private Integer isshare;//1分享 0不分享，默认不分享

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public String getImg() {
		return img;
	}

	public String getShortmon() {
		return shortmon;
	}

	public void setShortmon(String shortmon) {
		this.shortmon = shortmon;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getRootid() {
		return rootid;
	}

	public void setRootid(Integer rootid) {
		this.rootid = rootid;
	}

	public void setIsleaf(Integer isleaf) {
		this.isleaf = isleaf;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getIsleaf() {
		return isleaf;
	}

	public Integer getIsshare() {
		return isshare;
	}

	public void setIsshare(Integer isshare) {
		this.isshare = isshare;
	}

}
