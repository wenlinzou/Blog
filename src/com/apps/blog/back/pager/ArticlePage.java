package com.apps.blog.back.pager;

import java.util.Date;

import com.apps.base.BasePage;

public class ArticlePage extends BasePage{
	private Integer id;
	private Integer pid;
	private Integer rootid;
	private String title;
	private String cont;
	private Date pdate;
	private Integer isleaf;
	
	private Integer click;
	
	
	
	public Integer getClick() {
		return click;
	}
	public void setClick(Integer click) {
		this.click = click;
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
	
}
