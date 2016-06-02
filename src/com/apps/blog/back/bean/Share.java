package com.apps.blog.back.bean;

import java.util.Date;

/**
 * 文章分享
 * 
 * @author Pet
 * 
 */
public class Share {
	private Integer id;
	private Integer articleid;
	private Date shareDate;
	private String code;
	private Integer isshare;//1分享 0不分享

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

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsshare() {
		return isshare;
	}

	public void setIsshare(Integer isshare) {
		this.isshare = isshare;
	}

}
